DROP DATABASE IF EXISTS comicshop;

CREATE DATABASE comicshop
    LC_COLLATE 'ru_RU.utf8' LC_CTYPE 'ru_RU.utf8'
    TEMPLATE template0;

\c comicshop;

DROP TABLE IF EXISTS order_item;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS order_details;
DROP TABLE IF EXISTS users;


CREATE TABLE users (
	user_id BIGSERIAL NOT NULL PRIMARY KEY,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	password VARCHAR(64) NOT NULL,
	salt VARCHAR(32) NOT NULL,
	phone VARCHAR(30) NOT NULL,
	address1 VARCHAR(50),
	address2 VARCHAR(50),
	city VARCHAR(50),
	zip VARCHAR(50),
	country VARCHAR(50),
	role SMALLINT NOT NULL DEFAULT '0'
);

CREATE TABLE order_details (
	order_details_id BIGSERIAL NOT NULL PRIMARY KEY,
	user_id BIGINT NOT NULL,
	order_date VARCHAR(64) NOT NULL,
	total_amount DECIMAL(19, 2) NOT NULL DEFAULT '0.00',
	order_status SMALLINT NOT NULL DEFAULT '0',
	CONSTRAINT fk_user FOREIGN KEY(user_id) 
	  REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE categories (
	category_id BIGSERIAL NOT NULL PRIMARY KEY,
	category_name VARCHAR(50) NOT NULL
);

CREATE TABLE products (
	product_id BIGSERIAL NOT NULL PRIMARY KEY,
	category_id BIGINT,
	product_name VARCHAR(50) NOT NULL,
	product_desc VARCHAR(5000) NOT NULL DEFAULT '',
	product_price DECIMAL(7,2) NOT NULL DEFAULT '0.00',
	product_img_url VARCHAR(100) NOT NULL,
	product_quantity INT NOT NULL DEFAULT '0',
	CONSTRAINT fk_category FOREIGN KEY(category_id) 
	  REFERENCES categories(category_id) ON DELETE SET NULL
);

CREATE TABLE order_item (
	item_id BIGSERIAL NOT NULL PRIMARY KEY,
	order_details_id BIGINT NOT NULL,
	product_id BIGINT NOT NULL,
	quantity INT NOT NULL DEFAULT '0',
	CONSTRAINT fk_order_details FOREIGN KEY(order_details_id) 
	  REFERENCES order_details(order_details_id) ON DELETE CASCADE,
	CONSTRAINT fk_product FOREIGN KEY(product_id) 
	  REFERENCES products(product_id) ON DELETE CASCADE
);

insert into users("first_name", "last_name", "email", "password", "salt", "phone", "role") 
	values('admin', 'comicshop', 'admin@cs.kz', 'rXbpWe9YglYwLMUKw5cDyifUiS0sj0MsdbT0hZU/Bec=', 'qvwMecriGmeEXv0H3tw7nQ==', '87476752274', '1');


insert into categories(category_name) Values('Комиксы');
insert into categories(category_name) Values('Манга');
insert into categories(category_name) Values('Фигурки');

insert into products ("category_id", "product_name", "product_desc", "product_price", "product_img_url", "product_quantity") 
	values ('1', 
			'Веном. Край, где обитают убийцы', 
			'Описание Агент Веном выходит на улицы ради старой доброй борьбы с преступностью… Но криминал не сдаётся, и Лорд Огр, вожак уголовного мира Филадельфии, объявляет войну самозваному защитнику города! Хладнокровные убийцы лезут из всех щелей, стремясь получить награду за голову Венома, его личность раскрывают, и теперь Флэшу Томпсону понадобится любая помощь — хотя бы для того, чтобы выжить. Положение ещё больше усложняется, когда в схватку вступает новая симбиотическая сущность: доблестный поступок Юджина случайно приводит к рождению Мании! Можно ли справиться с этой новой опасностью? И кто такая Мания — друг или враг? Издание содержит выпуски «Веном» № 36-42', 
			'3000', 
			'/catalog/venom.jpg', 
			'2');

insert into products ("category_id", "product_name", "product_desc", "product_price", "product_img_url", "product_quantity") 
	values ('1', 
			'Мстители #57. Первое появление Вижна', 
			'Описание Совсем скоро увидит свет первый сериал в рамках MCU, который расскажет о злоключениях одной из самых необычных семейных пар в истории комиксов — Алой Ведьмы и синтезоида Вижна. Мы же предлагаем вам стать свидетелем первого появление Вижна в комиксах! Созданный Альтроном, он должен был уничтожить Мстителей, но в итоге стал одним из самых могущественных участников команды. Как это произошло? Узнаете на страницах этого комикса, созданного Роем Томасом и Джоном Бушемой!', 
			'4500', 
			'/catalog/mstiteli.jpg', 
			'2');

insert into products ("category_id", "product_name", "product_desc", "product_price", "product_img_url", "product_quantity") 
	values ('1', 
			'Рик и Морти. Книга 2. Нужно больше приключений', 
			'Описание Если вы следите за невероятными приключениями бедолаги Морти и его деда-алкоголика, учёного и по совместительству межгалактического преступника Рика Санчеза и устали ждать выхода третьего сезона, то эта книга определенно поможет вам скоротать время в ожидании. Приготовьтесь к новой порции безумия, но теперь уже в комиксах, потому что таких историй вы ещё не слышали и не видели! Сюжет комикса не дублирует мультсериал и не оставит равнодушным ни одного любителя отборного интеллектуально-трешёвого юмора и научной фантастики.', 
			'2700', 
			'/catalog/rikmorti.jpg', 
			'1');

insert into products ("category_id", "product_name", "product_desc", "product_price", "product_img_url", "product_quantity") 
	values ('1', 
			'Рыцари Marvel. Каратель. Том 1', 
			'Описание С возвращением, Фрэнк! Каратель возвращается! В этот раз над его историей поработали сценарист Гарт Эннис и художник Стив Диллон, авторы другого признанного произведения, “Проповедника”. В серии, известной своим шокирующим уровнем насилия и жестокости, Фрэнк Касл доводит войну против бандитов до невиданных высот — к ужасу Ма Нуччи и всей её преступной семьи. В этой книге вас ждут Кретин Дэйв, мистер Бампо, Мышь Джоан, чудовищный Русский, детектив Соуп и оружие в виде белого медведя. Непонятно, на что рассчитывали Сорвиголова и Человек-паук, когда решили полезть в этот дурдом, но им придётся страдать вместе с остальными. И в качестве бонуса — первая история Энниса про Карателя, в которой Фрэнк Касл уничтожает вселенную Marvel. В книгу входят комиксы “Каратель” (2000) #1–12, “Каратель” (2001) #1–5 и “Каратель уничтожает вселенную Marvel”.', 
			'3433', 
			'/catalog/karatel.jpg', 
			'1');

insert into products ("category_id", "product_name", "product_desc", "product_price", "product_img_url", "product_quantity") 
	values ('3', 
			'Статуя Batman Premium Format Exclusive', 
			'Описание Sideshow Collectibles с гордостью представляет ‘эксклюзивную статую DC Comics Premium Format  Темного рыцаря Бэтмена. Крестоносец в плаще высотой более 60  сантиметров стоит на каменной горгульи, нависая над коварными преступниками Готэм-Сити. Представленная в масштабе 1 к 4, динамичная и впечатляющая фигура индивидуально расписана вручную и отделана с качеством и вниманием к деталям, которые являются торговой маркой продукта ручной работы Sideshow Collectibles. Фигурка Batman Premium Format с двумя уникальными скульптурами головы – поистине необычное дополнение к любой коллекции.', 
			'3433', 
			'/catalog/batman.png', 
			'1');

insert into products ("category_id", "product_name", "product_desc", "product_price", "product_img_url", "product_quantity") 
	values ('3', 
			'Фигурка MY HERO ACADEMIA BFC', 
			'Описание Кацуки Бакуго (Bakugō Katsuki), или же Каччан, как называли его друзья детства (Каттян в официальном русском издании), является студентом Класса 1-A академии Юэй и дейтерагонистом сериала. Кацуки – грубый, высокомерный, агрессивный и вспыльчивый человек, который может показаться антигероем или даже злодеем тем, кто видит его впервые. Он вел себя так с самого детства, в котором его уверенность в себе подогревалась излишними похвалами со стороны людей и превосходством в силе многих сверстников, в том числе и Изуку, над которым он долгое время издевался из-за того, что тот был “беспричудным”. Однако после поступления в академию Юэй,его характер начал меняться в лучшую сторону, т.к. именно там произошли поворотные моменты в его жизни. После первого проигрыша в жизни, который случился во время “Испытания битвой”, в сражении против Изуку; после того, как на его пути встали действительно серьезные соперники он стал несколько иначе смотреть на свою жизнь, заметно меняясь в характере, хотя грубость и относительно высокомерие никуда не делось.', 
			'4570', 
			'/catalog/hero.jpeg', 
			'1');

insert into products ("category_id", "product_name", "product_desc", "product_price", "product_img_url", "product_quantity") 
	values ('3', 
			'Фигурка NECA Predator', 
			'Оригинальная фигурка NECA',
			'1200', 
			'/catalog/predator.jpg', 
			'1');

insert into products ("category_id", "product_name", "product_desc", "product_price", "product_img_url", "product_quantity") 
	values ('3', 
			'Статуя Superman Premium', 
			'Описание Sideshow Collectibles представляет фигурку Супермена премиум-формата. Супермен, возможно, самый известный супергерой в поп-культуре, и Sideshow оживляет его в потрясающем масштабе 1 к 4. Расположенная в замороженном святилище Крепости Одиночества, фигура Супермена премиум-формата высотой более 60 сантиметров создана с беспрецедентным вниманием к деталям. Человек из стали, культовый супергерой DC Comics, теперь может быть частью вашей коллекции.',
			'1200', 
			'/catalog/superman.jpg', 
			'1');

insert into products ("category_id", "product_name", "product_desc", "product_price", "product_img_url", "product_quantity") 
	values ('3', 
			'Фигурка SWORD ART ONLINE ESPRESTO', 
			'Описание Кадзуто Киригая ( Kazuto Kirigaya ) является главным героем серии лайт-новел «Sword Art Online». Он был выбран в качестве одного из 1000 бета-тестеров для закрытого бета-тестирования “Sword Art Online”, первой VRMMORPG. По словам Асуны, Кирито довольно прямолинеен (возможно, из-за небольшого количества друзей и затворнического образа жизни). Многим людям он показался даже грубым. Иногда кажется, что он любит нервировать и раздражать людей. Однако, Кирито — добрый человек, которого просто часто неправильно понимают. Несмотря на сложившееся мнение о нем как о «Битере», он отказывается оставлять других игроков в беде. Кадзуто имеет талант к программированию: он сумел собрать робота из компьютерного хлама еще в начальной школе, в возрасте десяти лет влез в реестр окружной больницы.',
			'1900', 
			'/catalog/sword.jpeg', 
			'1');

insert into products ("category_id", "product_name", "product_desc", "product_price", "product_img_url", "product_quantity") 
	values ('2', 
			'Стальной Алхимик. Книга 9', 
			'Описание На фоне политических интриг в Централе, вызванных известиями об истинной натуре Кинга Брэдли, Обжорство жаждет сожрать Роя Мустанга, но закусывает в итоге не им. Проглоченные Обжорством Эд Элрик и «принц-недоумок» Лин оказываются в очень странном месте, «по колено в кровище». Раскрыть Эду глаза на истинное предназначение места готов Зависть, заодно он поделится и другими фактами. Какое же он чудовище! Победить его в истинном облике будет непросто. В это же время оставшийся один на один с Обжорством Ал требует, чтобы гомункул отвёл его к своему истинному отцу. Лицо отца станет большим сюрпризом для Элриков…',
			'3000', 
			'/catalog/alhimik.jpg', 
			'4');

insert into products ("category_id", "product_name", "product_desc", "product_price", "product_img_url", "product_quantity") 
	values ('2', 
			'Токийский гуль: re. Книга 4', 
			'Описание Популярная писательница Сэн Такацуки в прямом эфире признается, что она гуль. Ее последний роман, ставящий под сомнение мировые устои, медленно, но верно будоражит весь Токио. Тем временем ККГ сосредотачивает все свои силы на уничтожении «Древа Аогири». Их главная цель – Одноглазый Король. Чтобы убить его, Комитет организует высадку на остров Русиму, базу аогирийцев, и одновременно запускает план обороны «Кохлеи». Надежную защиту тюрьмы должна обеспечить команда Аримы, но у Хайсэ Сасаки свои планы…',
			'3000', 
			'/catalog/tokiiski_gul.jpg', 
			'4');

	