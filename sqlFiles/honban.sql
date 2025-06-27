DELETE FROM items_in_cart;
DELETE FROM purchase_details;
DELETE FROM items;
DELETE FROM purchases;
DELETE FROM categories;
DELETE FROM users;
DELETE FROM administrators;


/*既にテーブルが構築されている想定*/

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


ALTER TABLE categories ALTER COLUMN "category_id" SET DEFAULT nextval('SEQ_CAT_CATID');
ALTER TABLE items  ALTER COLUMN "item_id" SET DEFAULT nextval('SEQ_ITEMS_ITEMID');
ALTER TABLE purchases ALTER COLUMN "purchase_id" SET DEFAULT nextval('SEQ_PURCHASE_ID');
ALTER TABLE purchase_details ALTER COLUMN "purchase_detail_id" SET DEFAULT nextval('SEQ_PUR_DETAIL_ID');

INSERT INTO administrators (admin_id, password, name) VALUES ('admin', 'admin', '管理者');
INSERT INTO public.users(user_id,password,name,address) VALUES ('user','userpass','五十嵐優一','北海道函館市五稜郭町４４');
INSERT INTO public.users(user_id,password,name,address) VALUES ('user2','userpass2','徳川日葵','東京都江東区三好４丁目１−１');
INSERT INTO public.users(user_id,password,name,address) VALUES ('user3','userpass3','甲斐龍一','鳥取県鳥取市河原町3-20');
INSERT INTO public.users(user_id,password,name,address) VALUES ('Dr','Mundo','ドクタームンド','不明');

INSERT INTO categories (category_id,name) VALUES (0,'すべて');
INSERT INTO categories (category_id,name) VALUES (1,'帽子');
INSERT INTO categories (category_id,name) VALUES (2,'鞄');
INSERT INTO categories (category_id,name) VALUES (3,'靴');
INSERT INTO categories (category_id,name) VALUES (4,'武器');
INSERT INTO categories (category_id,name) VALUES (5,'鎧');
INSERT INTO categories (category_id,name) VALUES (6,'特殊');

INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('麦わら帽子','(株)フーシャ村',1,'黄色',4980,12,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('Mキャップ','(株)ブラザーズ',1,'赤色',3480,15,TRUE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('シャンプーハット','美容院',1,'赤色',2980,3,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ライオンマスク','プロレスラー',1,'青色',4480,6,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ストローハット','日本帽子製造',1,'緑色',2500,17,TRUE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ニットキャップ','日本帽子製造',1,'紺色',1800,9,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ハンチング帽','日本帽子製造',1,'黄色',1980,20,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('穴あき帽子','フレンチ',1,'茶色',5480,2,TRUE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('パナマハット','(株)黄猿',1,'赤色',4580,1,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('海軍ニット帽','(株)青大将',1,'青色',3200,8,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('マジックハット','東京帽子店',1,'緑色',650,17,TRUE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ガラスの靴','シンデレラ',3,'透明',1100000,1,TRUE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ショルダーバッグ','東京鞄店',2,'緑色',4980,15,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ビーチサンダル','ラルフーローラン',2,'紺色',2200,3,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('バックパック','エニエスベー',2,'黄色',2980,6,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('エコバッグ','環境保護',2,'茶色',780,17,TRUE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('超高級鞄','(株)ボッテガ',2,'赤色',250000,5,TRUE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('鞄BAG','東京鞄店',2,'青色',1800,20,TRUE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ハンドバッグ','(株)MS',2,'緑色',34000,2,TRUE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ルブタンのスニーカー','Christian Louboutin',3,'茶色',192500,1,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('先人の道標','(株)Riot',2,'茶色',690,1,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ラバドンデスキャップ','(株)Riot',1,'茶色',690,1,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('心の鋼','(株)Riot',6,'白',3000,3,TRUE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ワーモグアーマー','(株)Riot',5,'緑',3100,3,TRUE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('タイタンハイドラ','(株)Riot',4,'茶色',3300,9,TRUE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('覇王のブラッドメイル','(株)Riot',5,'紫',3300,4,TRUE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('スピリットビサージュ','(株)Riot',4,'緑',2700,8,TRUE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('スイフトネスブーツ','(株)Riot',3,'緑',1000,10,TRUE);


insert into items_in_cart values('user',1,5,'2020/10/20');
insert into items_in_cart values('user',2,1,'2020/10/20');
insert into items_in_cart values('user',3,3,'2020/10/20');

INSERT INTO purchases (purchased_user, purchased_date, destination, cancel) values('user', '2020/10/20', '東京都', false);
INSERT INTO purchase_details (purchase_id, item_id, amount) values(1, 1, 1);
INSERT INTO purchase_details (purchase_id, item_id, amount) values(1, 3, 3);

INSERT INTO purchases (purchased_user, purchased_date, destination, cancel) values('user2', '2018/11/24', '鳥取県', false);
INSERT INTO purchase_details (purchase_id, item_id, amount) values(2, 2, 2);

INSERT INTO purchases (purchased_user, purchased_date, destination, cancel) values('user', '2010/05/20', '東京都', false);
INSERT INTO purchase_details (purchase_id, item_id, amount) values(3, 4, 10);

INSERT INTO purchases (purchased_user, purchased_date, destination, cancel) values('user2', '2019/03/18', '鳥取県', true);
INSERT INTO purchase_details (purchase_id, item_id, amount) values(4, 1, 8);

