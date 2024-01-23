# Запуск проекта

File -> New -> New project -> New project from VCS

URL - https://github.com/spbu-math-cs/GiveGift-mobile

# Работа с проектом

git remote add origin https://github.com/spbu-math-cs/GiveGift-mobile

git checkout -b newHorizons

git add .

git commit -m "mmm"

git pull origin newHorizons


Либо это можно сделать через IDE

# Структура проекта

app -> src -> main ->

- java/com/example/givegiftdesign

-- preference <- детали реализации для окна с выбором предпочтений

--- PreferenceBlock.java <- конструирование блока с предпочтением

--- Price.java <- логика для ценового диапазона

-- profilwscreen <- 

--- adapter <- 

---- CollectionAdapter.java <- 

---- PostAdapter.java <- 

--- api <- 

---- MyApi.java <- интерфейс

---- MyretrofitClient.java <- 

--- model <-

---- UserCollection.java <- 

---- UserData.java <- 

---- UserPost.java <- 

-- request <- происходит работа с вебом

--- Account.java <- содержит всё необходимое для аккаунта

--- Request.java <- производит запрос в веб и конструирует данные

--- ServerApi.java <- интерфейс. Нужен для запросов

-- FriendsActivity.java <- логика окна с друзьями. Переход осуществляется через меню
  
-- GiftBlock.java <- лежит инфа об элементе, в котором хранится картина, описание и ссылка на подарок

-- GiftBlockConstructor.java <- создает view блок сгенерированного подарка
  
-- MainActivity.java <- главный класс, с которого стартует приложение

-- PreferenceActivity.java <- окно с предпочтениями. Переход осуществляется через FloatingActionButton (внизу)

-- ProfileActivity.java <- логика окна с профилем. Переход осуществляется через меню

-- RegistryActivity.java <- 

- res
  
-- drawable* <- иконки
  
-- layout <- лежат окна приложения

--- activity_registry.xml <- 

--- activity_friends.xml <- окно с друзьями

--- activity_main.xml <- главное окно, где будут располагаться элементы с подарками

--- activity_main_footer.xml <- низ главного окна

--- activity_main_gift.xml <- include блока с подарком (используется как шаблон)

--- activity_main_toolbar.xml <- поле с надписью и меню-аккаунтом

--- activity_preference.xml <- окно с предпочтениями

--- activity_preference_block.xml <- include блока с предпочтением (используется как шаблон)

--- activity_preference_footer.xml <- низ окна с предпочтениями

--- activity_preference_preferences.xml <- поле с установкой предпочтений

--- activity_preference_price.xml <- поле с установкой цены

--- activity_profile.xml <- окно с профилем

--- collection_layout.xml <- 

--- post_layout.xml <- 

-- menu

--- main_menu.xml <- элементы в выпадающем меню в main activity

--- pref_menu.xml <- элементы для выбора предпочтений в preference activity

-- mipmap-* <- хранятся изображения с разными разрешениями для разных устройств

-- values

--- colors.xml <- захардкоженные цвета

--- dimens.xml <- размерности элементов для работы с разными разрешениями

--- strings* <- захардкоженные строки (для англ и рус версии)

--- themes* <- задаются темы и свойства для элементов, чтоб повторений не было. В некоторых источниках пишут styles.xml

-- values-night <- стили для ночной темы

--- themes.xml

-- xml

--- backup-rules.xml

--- data_extraction_rules.xml


- AndroidManfest.xml <- указание параметров сборки и в целом без него проект не запустится)


app -> src

- androidTest <- тесты, которые запускаются на телефоне
  
- test <- можно спокойно писать свои юнит тесты
  

![Alt text](https://github.com/DaniilBel/GiveGift-mobile/blob/newHorizons/mobapp1.jpg?raw=true "Mobile")

![Alt text](https://github.com/DaniilBel/GiveGift-mobile/blob/newHorizons/mobapp2.jpg?raw=true "Mobile")

![Alt text](https://github.com/DaniilBel/GiveGift-mobile/blob/newHorizons/mobapp3.jpg?raw=true "Mobile")
