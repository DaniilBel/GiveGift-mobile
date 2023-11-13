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
  
-- GiftBlock.java <- лежит инфа об элементе, в котором хранится картина, описание и ссылка на подарок
  
-- MainActivity.java <- главный класс, с которого стартует приложение

-- FriendsActivity.java <- логика окна с друзьями. Переход осуществляется через меню

-- ProfileActivity.java <- логика окна с профилем. Переход осуществляется через меню

-- PreferenceActivity.java <- окно с предпочтениями. Переход осуществляется через FloatingActionButton (внизу)

- res
  
-- drawable* <- иконки
  
-- layout <- лежат окна приложения

--- activity_main.xml <- главное окно, где будут располагаться элементы с подарками

--- activity_friends.xml <- окно с друзьями

--- activity_preference.xml <- окно с предпочтениями

--- activity_profile.xml <- окно с профилем

-- menu

--- main_menu.xml <- элементы в выпадающем меню

-- mipmap-* <- хранятся изображения с разными разрешениями для разных устройств

-- values

--- colors.xml <- захардкоженные цвета

--- strings.xml <- захардкоженные строки

--- themes.xml <- задаются темы и свойства для элементов, чтоб повторений не было. В некторых источниках пишут styles.xml

-- values-night <- стили для ночной темы

--- themes.xml

-- values-ru <- локализация на русский (если она вообще будет)(PS: на будущее)

--- strings.xml

-- xml

--- backup-rules.xml

--- data_extraction_rules.xml


- AndroidManfest.xml <- указание параметров сборки и в целом без него проект не запустится)


app -> src

- androidTest <- тесты, которые запускаются на телефоне
  
- test <- можно спокойно писать свои юнит тесты
  

![Alt text](https://github.com/DaniilBel/GiveGift-mobile/blob/newHorizons/mobapp1.jpg?raw=true "Mobile")

![Alt text](https://github.com/DaniilBel/GiveGift-mobile/blob/newHorizons/mobapp2.jpg?raw=true "Mobile")
