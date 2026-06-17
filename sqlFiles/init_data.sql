DROP SEQUENCE IF EXISTS SEQ_CAT_CATID CASCADE;
DROP SEQUENCE IF EXISTS SEQ_ITEMS_ITEMID CASCADE;
DROP SEQUENCE IF EXISTS SEQ_PURCHASE_ID CASCADE;
DROP SEQUENCE IF EXISTS SEQ_PUR_DETAIL_ID CASCADE;

CREATE SEQUENCE SEQ_CAT_CATID;
CREATE SEQUENCE SEQ_ITEMS_ITEMID;
CREATE SEQUENCE SEQ_PURCHASE_ID;
CREATE SEQUENCE SEQ_PUR_DETAIL_ID;

ALTER SEQUENCE public.SEQ_CAT_CATID OWNER TO ecsite;
ALTER SEQUENCE public.SEQ_ITEMS_ITEMID OWNER TO ecsite;
ALTER SEQUENCE public.SEQ_PURCHASE_ID OWNER TO ecsite;
ALTER SEQUENCE public.SEQ_PUR_DETAIL_ID OWNER TO ecsite;

DROP TABLE IF EXISTS items_in_cart;
DROP TABLE IF EXISTS purchase_details;
DROP TABLE IF EXISTS purchases;
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS administrators;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id		VARCHAR(255)	PRIMARY KEY,
	password	VARCHAR(255)		NOT NULL,
	name		VARCHAR(32),
	address		VARCHAR(255)
);
ALTER TABLE public.users OWNER TO ecsite;
CREATE TABLE categories (
	category_id		INTEGER			PRIMARY KEY DEFAULT NEXTVAL('SEQ_CAT_CATID'),
	name			VARCHAR(255)	NOT NULL
);
ALTER TABLE public.categories OWNER TO ecsite;
CREATE TABLE items (
	item_id			INTEGER			PRIMARY KEY DEFAULT NEXTVAL('SEQ_ITEMS_ITEMID'),
	name			VARCHAR(128)	NOT NULL,
	manufacturer	VARCHAR(32),
	category_id		INTEGER			NOT NULL,
	color			VARCHAR(16),
	price			INTEGER			NOT NULL DEFAULT 0,
	stock			INTEGER			NOT NULL DEFAULT 0,
	recommended		BOOLEAN			NOT NULL DEFAULT FALSE,
	FOREIGN KEY (category_id) REFERENCES categories (category_id)
);
ALTER TABLE public.items OWNER TO ecsite;

CREATE TABLE items_in_cart (
	user_id		VARCHAR(255),
	item_id		INTEGER,
	amount		INTEGER		NOT NULL,
	booked_date	DATE		NOT NULL,
	PRIMARY KEY (user_id, item_id),
	FOREIGN KEY (user_id) REFERENCES users (user_id),
	FOREIGN KEY (item_id) REFERENCES items (item_id)
);
ALTER TABLE public.items_in_cart OWNER TO ecsite;

CREATE TABLE purchases (
	purchase_id		INTEGER			PRIMARY KEY DEFAULT NEXTVAL('SEQ_PURCHASE_ID'),
	purchased_user	VARCHAR(255)	NOT NULL,
	purchased_date	DATE			NOT NULL,
	destination		VARCHAR(255),
	cancel			BOOLEAN			NOT NULL DEFAULT FALSE,
	FOREIGN KEY (purchased_user) REFERENCES users (user_id)
);
ALTER TABLE public.purchases OWNER TO ecsite;

CREATE TABLE purchase_details (
	purchase_detail_id	INTEGER	PRIMARY KEY DEFAULT NEXTVAL('SEQ_PUR_DETAIL_ID'),
	purchase_id			INTEGER		NOT NULL,
	item_id				INTEGER		NOT NULL,
	amount				INTEGER		NOT NULL,
	FOREIGN KEY (purchase_id) REFERENCES purchases (purchase_id),
	FOREIGN KEY (item_id) REFERENCES items (item_id)
);
ALTER TABLE public.purchase_details OWNER TO ecsite;

CREATE TABLE administrators (
	admin_id		VARCHAR(255)	PRIMARY KEY,
	password		VARCHAR(255)		NOT NULL,
	name			VARCHAR(32)
);
ALTER TABLE public.administrators OWNER TO ecsite;

INSERT INTO administrators (admin_id, password, name) VALUES ('admin', 'admin', '管理者');


INSERT INTO  public.users(user_id,password,name,address) values ('user1','userpass1','鳥取一郎','鳥取県鳥取市河原町６丁目１０７');
INSERT INTO  public.users(user_id,password,name,address) values ('user2','userpass2','鳥取二郎','鳥取県鳥取市河原町６丁目１０７');
INSERT INTO  public.users(user_id,password,name,address) values ('user3','userpass3','鳥取三郎','鳥取県鳥取市河原町６丁目１０７');
INSERT INTO  public.users(user_id,password,name,address) values ('user4','userpass4','鳥取四郎','鳥取県鳥取市河原町６丁目１０７');

INSERT INTO public.categories (category_id,name) VALUES (0,'すべて');
INSERT INTO public.categories (category_id,name) VALUES (1,'帽子');
INSERT INTO public.categories (category_id,name) VALUES (2,'鞄');

INSERT INTO public.items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('麦わら帽子','日本帽子製造',1,'黄色',4980,12,FALSE);
INSERT INTO public.items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ストローハット','(株)ストローハットジャパン',1,'茶色',3480,15,TRUE);
INSERT INTO public.items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('子ども用麦わら帽子','東京帽子店',1,'赤色',2980,3,FALSE);
INSERT INTO public.items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ストローハット PART2','(株)ストローハットジャパン',1,'青色',4480,6,FALSE);
INSERT INTO public.items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('野球帽','日本帽子製造',1,'緑色',2500,17,TRUE);
INSERT INTO public.items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ニットキャップ','日本帽子製造',1,'紺色',1800,9,FALSE);
INSERT INTO public.items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ハンチング帽','日本帽子製造',1,'黄色',1980,20,FALSE);
INSERT INTO public.items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ストローハット PART3','(株)ストローハットジャパン',1,'茶色',5480,2,TRUE);
INSERT INTO public.items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ターバン','東京帽子店',1,'赤色',4580,1,FALSE);
INSERT INTO public.items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ベレー帽','東京帽子店',1,'青色',3200,8,FALSE);
INSERT INTO public.items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('マジック用ハット','東京帽子店',1,'緑色',650,17,TRUE);
INSERT INTO public.items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('鞄A','東京鞄店',2,'青色',1980,18,TRUE);
INSERT INTO public.items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('鞄B','東京鞄店',2,'緑色',4980,15,FALSE);
INSERT INTO public.items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('鞄E','(株)鞄',2,'紺色',2200,3,FALSE);
INSERT INTO public.items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('鞄G','日本鞄製造',2,'黄色',2980,6,FALSE);
INSERT INTO public.items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('鞄H','日本鞄製造',2,'茶色',780,17,TRUE);
INSERT INTO public.items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('鞄F','(株)鞄',2,'赤色',2500,9,TRUE);
INSERT INTO public.items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('鞄C','東京鞄店',2,'青色',1800,20,TRUE);
INSERT INTO public.items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('鞄D','東京鞄店',2,'緑色',1980,2,FALSE);
INSERT INTO public.items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('鞄I','日本鞄製造',2,'茶色',690,1,FALSE);

INSERT INTO public.purchases(purchased_user, purchased_date, destination, cancel)VALUES('user1', '2026-06-16', null, false);
INSERT INTO public.purchases(purchased_user, purchased_date, destination, cancel)VALUES('user2', '2026-06-16', null, false);
INSERT INTO public.purchases(purchased_user, purchased_date, destination, cancel)VALUES('user3', '2026-06-16', null, false);

INSERT INTO public.purchase_details( purchase_id, item_id, amount) VALUES (1,  7, 4);
INSERT INTO public.purchase_details( purchase_id, item_id, amount) VALUES (1,  6, 1);
INSERT INTO public.purchase_details( purchase_id, item_id, amount) VALUES (1,  5, 2);
INSERT INTO public.purchase_details( purchase_id, item_id, amount) VALUES (2,  1, 3);
INSERT INTO public.purchase_details( purchase_id, item_id, amount) VALUES (3,  2, 1);
INSERT INTO public.purchase_details( purchase_id, item_id, amount) VALUES (3,  3, 1);
INSERT INTO public.purchase_details( purchase_id, item_id, amount) VALUES (3,  4, 2);
INSERT INTO public.purchase_details( purchase_id, item_id, amount) VALUES (3,  5, 2);


ALTER TABLE categories ALTER COLUMN "category_id" SET DEFAULT nextval('SEQ_CAT_CATID');
ALTER TABLE items  ALTER COLUMN "item_id" SET DEFAULT nextval('SEQ_ITEMS_ITEMID');
ALTER TABLE purchases ALTER COLUMN "purchase_id" SET DEFAULT nextval('SEQ_PURCHASE_ID');
ALTER TABLE purchase_details ALTER COLUMN "purchase_detail_id" SET DEFAULT nextval('SEQ_PUR_DETAIL_ID');

INSERT INTO public.items_in_cart (user_id, item_id, amount, booked_date) VALUES ('user1', 5, 3, '2026-06-16');

