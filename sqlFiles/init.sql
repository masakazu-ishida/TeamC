DELETE FROM items_in_cart;
DELETE FROM items;
DELETE FROM purchase_details;
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
INSERT INTO public.users(user_id,password,name,address) VALUES ('user','userpass','鳥取一郎','鳥取県鳥取市河原町６丁目１０７');

INSERT INTO categories (category_id,name) VALUES (0,'すべて');
INSERT INTO categories (category_id,name) VALUES (1,'帽子');
INSERT INTO categories (category_id,name) VALUES (2,'鞄');

INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('麦わら帽子','日本帽子製造',1,'黄色',4980,12,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ストローハット','(株)ストローハットジャパン',1,'茶色',3480,15,TRUE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('子ども用麦わら帽子','東京帽子店',1,'赤色',2980,3,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ストローハット PART2','(株)ストローハットジャパン',1,'青色',4480,6,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('野球帽','日本帽子製造',1,'緑色',2500,17,TRUE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ニットキャップ','日本帽子製造',1,'紺色',1800,9,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ハンチング帽','日本帽子製造',1,'黄色',1980,20,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ストローハット PART3','(株)ストローハットジャパン',1,'茶色',5480,2,TRUE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ターバン','東京帽子店',1,'赤色',4580,1,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('ベレー帽','東京帽子店',1,'青色',3200,8,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('マジック用ハット','東京帽子店',1,'緑色',650,17,TRUE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('鞄A','東京鞄店',2,'青色',1980,18,TRUE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('鞄B','東京鞄店',2,'緑色',4980,15,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('鞄E','(株)鞄',2,'紺色',2200,3,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('鞄G','日本鞄製造',2,'黄色',2980,6,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('鞄H','日本鞄製造',2,'茶色',780,17,TRUE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('鞄F','(株)鞄',2,'赤色',2500,9,TRUE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('鞄C','東京鞄店',2,'青色',1800,20,TRUE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('鞄D','東京鞄店',2,'緑色',1980,2,FALSE);
INSERT INTO items (name,manufacturer,category_id,color,price,stock,recommended) VALUES ('鞄I','日本鞄製造',2,'茶色',690,1,FALSE);


insert into items_in_cart values('user',1,5,'2020/10/20');
insert into items_in_cart values('user',2,1,'2020/10/20');
insert into items_in_cart values('user',3,3,'2020/10/20');

