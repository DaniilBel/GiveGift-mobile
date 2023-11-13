package com.example.givegiftdesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.view.Menu;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    private ImageView giftImageView;
    private TextView giftDescription;
    private Button giftSearch;

    // Тестовый объект
    private GiftBlock giftBlock() {
        return new GiftBlock(
                "https://i.imgur.com/67tSocD.jpeg",
                "",
                "https://imgur.com/gallery/67tSocD"
        );
    }

    LinearLayoutCompat mainLayout;

    // Люблю попугов
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainLayout = findViewById(R.id.gift_layout);

        Button giftIdeaBtn = findViewById(R.id.gift_idea);
        giftIdeaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardView cardView = new CardView(MainActivity.this);
                LinearLayout linearLayout = new LinearLayout(MainActivity.this);
                ImageView imageView = new ImageView(MainActivity.this);
                TextView textView = new TextView(MainActivity.this);
                Button button = new Button(MainActivity.this);

                GiftBlockConstructor giftBlockConstructor = new GiftBlockConstructor(
                        cardView,
                        linearLayout,
                        imageView,
                        textView,
                        button,
                        getResources()
                );

                View giftIdeaView = giftBlockConstructor.giftViewParams(giftBlock());
                mainLayout.addView(giftIdeaView);
            }
        });

//        giftImageView = findViewById(R.id.gift_image_one);
//        giftDescription = findViewById(R.id.gift_desc_one);
//        giftSearch = findViewById(R.id.gift_search_one);
//
//        displayGiftBlockInfo(giftBlock());
//
//        giftImageView = findViewById(R.id.gift_image_two);
//        giftDescription = findViewById(R.id.gift_desc_two);
//        giftSearch = findViewById(R.id.gift_search_two);
//
//        displayGiftBlockInfo(giftBlock());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, PreferenceActivity.class)));
    }

    private void displayGiftBlockInfo(GiftBlock gb) {
        // Временный код
        // Показывает как получить картинку из инета и заменить её в activity_main
        Picasso.get().load(gb.getImageUrl()).into(giftImageView);
        giftDescription.setText(gb.getDescription());

        // Логика кнопки может находиться в другом месте
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getOrder()) {
            case 1:
                Log.d("Item profile", "clicked");
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            case 2:
                Log.d("Item friends", "clicked");
                startActivity(new Intent(this, FriendsActivity.class));
                return true;
            case 3:
                Log.d("Exit", "clicked");
                startActivity(new Intent(this, RegistryActivity.class));
                return true;
        }

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}