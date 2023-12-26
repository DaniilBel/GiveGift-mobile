package com.example.givegiftdesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.givegiftdesign.giftidea.GiftBlock;
import com.example.givegiftdesign.giftidea.NewGiftConstructor;
import com.example.givegiftdesign.request.Idea;
import com.example.givegiftdesign.request.IdeaClient;
import com.example.givegiftdesign.request.RequestData;
import com.example.givegiftdesign.preference.PreferenceActivity;
import com.example.givegiftdesign.data.Account;
import com.example.givegiftdesign.request.RequestDataApi;
import com.example.givegiftdesign.request.Request;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "1";
    /**
     * При запуске приложения запрашивается инфа с веба и инициализиует все необходимые поля
     * например 'предпочтения', 'аккаунт' и тп
     */
    private Request request;

    /**
     * Блок для отображения полученной идеи для подарка
     */
    private ArrayList<GiftBlock> giftBlocks;

    /**
     * В этот слой добавляются сгенерированные идеи для подарка
     */
    LinearLayoutCompat mainLayout;
    ImageButton maskot;
    ProgressBar progressBar;

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this.
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    /**
     * Точка входа в приложение
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Уведомления
        createNotificationChannel();

        Intent intent = new Intent(MainActivity.this, Reminder.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        long timeAtSys = System.currentTimeMillis();
        long twoSecs = 5 * 1000;

        alarmManager.set(AlarmManager.RTC_WAKEUP,
                timeAtSys + twoSecs,
                pendingIntent);
        //

        // Заполняем временными интересами и ценами
//        Account.tempInterests();

        // Тут производится запрос
        /**
         * При запуске приложения запрашивается инфа с веба и инициализиует все необходимые поля
         * например 'предпочтения', 'аккаунт' и тп
         */
//        request = new Request();
//        request.req();
        //

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.baseline_account_circle_24));
        setSupportActionBar(toolbar);

        mainLayout = findViewById(R.id.gift_layout);
        setMascot(R.mipmap.greetings_foreground);
        setProgressBar();
        dancingMascot();

        // Кнопка для генерации идей на основе предпочтений
        Button giftIdeaBtn = findViewById(R.id.gift_idea);
        giftIdeaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cleanScreen();
                setMascot(R.mipmap.gift_foreground);
                setProgressBar();
                dancingMascot();
                progressBar.setVisibility(View.VISIBLE);

                RequestData requestData = new RequestData(Account.getInterests(), Account.getPrice_range());
                sendNetworkRequest(requestData);
            }
        });
        //

        // Кнопка очистки экрана
        FloatingActionButton fab_clean = findViewById(R.id.clear);
        fab_clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanScreen();
                setMascot(R.mipmap.greetings_foreground);
                setProgressBar();
                dancingMascot();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

        // Кнопка для перехода в activity, где происходит выбор предпочтений и цены
        FloatingActionButton fab_pref = findViewById(R.id.fab);
        fab_pref.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, PreferenceActivity.class)));
    }

    /**
     * Отправляет POST запрос с предпочтениями и ценовым диапазоном
     * на <a href="https://faq-givegift-req.ru/">...</a>, откуда потом приходят идеи для подарков
     *
     * @param requestData - объект с предпочтениями и ценовым диапазоном
     */
    private void sendNetworkRequest(RequestData requestData) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Результат запроса от gpt можно ожидать несколько секунд, так что время
        // ожидания увеличено
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .build();

        // Json builder
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        //
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://faq-givegift-req.ru/")
                .client(okHttpClient)
//                        .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson));

        Retrofit retrofit = builder.build();

        RequestDataApi modalApi = retrofit.create(RequestDataApi.class);
        Call<RequestData> dataModalCall = modalApi.createPost(requestData);

        Log.d("Call", "body: " + new GsonBuilder().setPrettyPrinting().create().toJson(requestData));

        dataModalCall.enqueue(new Callback<RequestData>() {
            @Override
            public void onResponse(Call<RequestData> call, Response<RequestData> response) {
                Log.d("Call", "enter");
                Toast.makeText(MainActivity.this, "Data added to API: ",
                        Toast.LENGTH_SHORT).show();

                String responseString = "Response code: " + response.code();
                Toast.makeText(MainActivity.this, responseString, Toast.LENGTH_SHORT).show();
            }

            // Я прост ваще тут хз как это пофиксить
            @Override
            public void onFailure(Call<RequestData> callIgnored, Throwable t) {
                Log.w("Failure", "failure: " + t);

                IdeaClient client = retrofit.create(IdeaClient.class);
                Call<List<Idea>> call = client.ideasForUser();

                call.enqueue(new Callback<List<Idea>>() {
                    @Override
                    public void onResponse(Call<List<Idea>> call, Response<List<Idea>> response) {
                        if (response.isSuccessful()) {
                            try {
                                Log.d("Callback", "response: " + response.body().size());
                                Log.d("Callback body", "response: " + response.body().get(0));

                                List<Idea> ideas = response.body();

                                ViewGroup parent = (ViewGroup) maskot.getParent();
                                parent.removeView(maskot);
                                progressBar.setVisibility(View.GONE);

                                for (Idea idea : ideas) {

                                    GiftBlock gb = new GiftBlock(idea.getImg_link(),
                                            idea.getTitle(),
                                            idea.getMarket_link());

                                    NewGiftConstructor newGiftConstructor = new NewGiftConstructor(
                                            getLayoutInflater().inflate(R.layout.activity_main_gift, mainLayout, false),
                                            MainActivity.this
                                    );

                                    View giftIdeaView = newGiftConstructor.giftViewParams(gb);
                                    mainLayout.addView(giftIdeaView);

                                }
                            } catch (Exception e) {
                                Log.e("Error", e.getMessage());
                                cleanScreen();
                                setMascot(R.mipmap.mascot_error_foreground);
                            }
                        } else {
                            Log.d("Response code", "response code " + response.code());
                            cleanScreen();
                            setMascot(R.mipmap.mascot_error_foreground);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Idea>> call, Throwable t) {
                        Log.w("Failure", "failure: " + t);
                        cleanScreen();
                        setMascot(R.mipmap.mascot_error_foreground);
                    }
                });
            }
        });
    }

    private void cleanScreen() {
        @SuppressLint("CutPasteId") ViewGroup giftLayout = findViewById(R.id.gift_layout);
        giftLayout.removeAllViews();
    }

    private void setMascot(int resId) {
        LinearLayoutCompat giftLayout = findViewById(R.id.gift_layout);

        maskot = new ImageButton(this);
        maskot.setId(View.generateViewId());
        maskot.setLayoutParams(new LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT
        ));
        maskot.setScaleX(2.5f);
        maskot.setScaleY(2.5f);
        maskot.setImageResource(resId);
        maskot.setContentDescription(String.valueOf(R.string.maskot));
        maskot.setBackground(null);

        giftLayout.addView(maskot);
    }

    private void setProgressBar() {
        LinearLayoutCompat giftLayout = findViewById(R.id.gift_layout);
        giftLayout.addView(getLayoutInflater().inflate(R.layout.activity_main_progress_bar, giftLayout, false));
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.INVISIBLE);
    }

    /**
     * Отображение маскота, по которому можно кликать
     */
    @SuppressLint("ClickableViewAccessibility")
    private void dancingMascot() {
        // Ципа dancing
        final boolean[] isChanged = {false};
        maskot.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN && isChanged[0]) {
                    maskot.setImageResource(R.mipmap.greetings_foreground);
                    isChanged[0] = false;
                } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    maskot.setImageResource(R.mipmap.gift_foreground);
                    isChanged[0] = true;
                }
                return false;
            }
        });
    }

    /**
     * Меню с возможностью перехода по activity профиль, друзья и тп
     *
     * @param item The menu item that was selected.
     * @return - true/false
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getOrder()) {
            case 1:
                Log.d("Item profile", "clicked");
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
//            case 2:
//                Log.d("Item friends", "clicked");
//                startActivity(new Intent(this, FriendsActivity.class));
//                return true;
            case 2:
                Log.d("Exit", "clicked");
                startActivity(new Intent(this, LoginActivity.class));
                return true;
        }

        return false;
    }

    /**
     * Просто создается меню для доступа к профилю, друзьям и выходу
     *
     * @param menu The options menu in which you place your items.
     * @return - true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}