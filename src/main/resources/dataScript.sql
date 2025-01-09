INSERT INTO user(gender,birthday,address,email,user_name,password,role,phone,status)
VALUES (true,'2002-04-07','04 Le Van Viet','cuong@gmail.com','cuong','1','customer','0938472721',1);
INSERT INTO user(gender,birthday,address,email,user_name,password,role,phone,status)
VALUES (false,'2002-08-04','18 Le Van Viet','hong@gmail.com','hong','1','customer','0938472722',1);
INSERT INTO user(gender,birthday,address,email,user_name,password,role,phone,status)
VALUES (true,'2000-07-01','06 Le Van Viet','nam@gmail.com','nam','1','customer','0938472723',1);
INSERT INTO user(gender,birthday,address,email,user_name,password,role,phone,status)
VALUES (true,'2006-01-20','09 La Xuan Oai','tien@gmail.com','manager','1','manager','0938472724',1);
INSERT INTO user(gender,birthday,address,email,user_name,password,role,phone,status)
VALUES (true,'2006-01-20','111 Le Viet Luong','dat@gmail.com','dat','1','customer','0938472725',1);
INSERT INTO user(gender,birthday,address,email,user_name,password,role,phone,status)
VALUES (false,'2001-11-08','14 Xuan Huong','linh@gmail.com','admin','1','admin','0938472726',1);

INSERT INTO blog(blog_name,blog_content,user_id,create_day,img_path) VALUES ('blog 1','Blog number 1',4,'2020-10-22',null);
INSERT INTO blog(blog_name,blog_content,user_id,create_day,img_path) VALUES ('blog 2','Blog number 2',4,'2020-11-22',null);
INSERT INTO blog(blog_name,blog_content,user_id,create_day,img_path) VALUES ('blog 3','Blog number 3',4,'2021-08-29',null);

INSERT INTO item_type(item_type_name, status) VALUES ('mai',1);
INSERT INTO item_type(item_type_name, status) VALUES ('nen',1);
INSERT INTO item_type(item_type_name, status) VALUES ('lau',1);
INSERT INTO item_type(item_type_name, status) VALUES ('cua so',1);

INSERT INTO item(item_type_id,item_name,price_item, status) VALUES (1,'mai ngoi',40000000,1);
INSERT INTO item(item_type_id,item_name,price_item, status) VALUES (1,'mai ton',20000000,1);
INSERT INTO item(item_type_id,item_name,price_item, status) VALUES (3,'lau 1',80000000,1);
INSERT INTO item(item_type_id,item_name,price_item, status) VALUES (3,'lau 2',80000000,1);
INSERT INTO item(item_type_id,item_name,price_item, status) VALUES (3,'lau 3',80000000,1);
INSERT INTO item(item_type_id,item_name,price_item, status) VALUES (3,'lau 4',80000000,1);
INSERT INTO item(item_type_id,item_name,price_item, status) VALUES (2,'dat',10000000,1);
INSERT INTO item(item_type_id,item_name,price_item, status) VALUES (2,'gach',20000000,1);
INSERT INTO item(item_type_id,item_name,price_item, status) VALUES (4,'cua so kieu Nhat',10000000,1);
INSERT INTO item(item_type_id,item_name,price_item, status) VALUES (4,'cua so kieu Han',11000000,1);
INSERT INTO item(item_type_id,item_name,price_item, status) VALUES (4,'cua so kieu Trung',9000000,1);

INSERT INTO material_type(type_name, status) VALUES ('sat',1);
INSERT INTO material_type(type_name, status) VALUES ('thep',1);
INSERT INTO material_type(type_name, status) VALUES ('xi mang',1);
INSERT INTO material_type(type_name, status) VALUES ('cat',1);
INSERT INTO material_type(type_name, status) VALUES ('gach',1);

INSERT INTO material(material_name,material_type_id,unit_price, status) VALUES ('sat 1',1, 12000,1);
INSERT INTO material(material_name,material_type_id,unit_price, status) VALUES ('sat 2',1, 11500,1);
INSERT INTO material(material_name,material_type_id,unit_price, status) VALUES ('sat 3',1, 13000,1);
INSERT INTO material(material_name,material_type_id,unit_price, status) VALUES ('thep 1',2, 10000,1);
INSERT INTO material(material_name,material_type_id,unit_price, status) VALUES ('thep 2',2, 11000,1);
INSERT INTO material(material_name,material_type_id,unit_price, status) VALUES ('xi mang 1',3, 89000,1);
INSERT INTO material(material_name,material_type_id,unit_price, status) VALUES ('xi mang 2',3, 92000,1);
INSERT INTO material(material_name,material_type_id,unit_price, status) VALUES ('xi mang 3',3, 108000,1);
INSERT INTO material(material_name,material_type_id,unit_price, status) VALUES ('cat 1',4, 135000,1);
INSERT INTO material(material_name,material_type_id,unit_price, status) VALUES ('cat 2',4, 120000,1);
INSERT INTO material(material_name,material_type_id,unit_price, status) VALUES ('gach 1',5, 1200,1);
INSERT INTO material(material_name,material_type_id,unit_price, status) VALUES ('gach 2',5, 3000,1);

INSERT INTO combo_building(combo_building_name,unit_price,type,status) VALUES ('tho 1',3200000, 0,1);
INSERT INTO combo_building(combo_building_name,unit_price,type,status) VALUES ('tho 2',3500000, 0,1);
INSERT INTO combo_building(combo_building_name,unit_price,type,status) VALUES ('hoan thien cafe',5000000, 1,1);
INSERT INTO combo_building(combo_building_name,unit_price,type,status) VALUES ('hoan thien nha pho',5500000, 1,1);
INSERT INTO combo_building(combo_building_name,unit_price,type,status) VALUES ('hoan thien biet thu',7000000, 1,1);
INSERT INTO combo_building(combo_building_name,unit_price,type,status) VALUES ('tron goi cafe 1',8000000, 2,1);
INSERT INTO combo_building(combo_building_name,unit_price,type,status) VALUES ('tron goi cafe 2',8400000, 2,1);
INSERT INTO combo_building(combo_building_name,unit_price,type,status) VALUES ('tron goi nha pho 1',8200000, 2,1);
INSERT INTO combo_building(combo_building_name,unit_price,type,status) VALUES ('tron goi biet thu 1',10000000, 2,1);

INSERT INTO combo_detail(combo_building_id,material_id) VALUES (1,7);
INSERT INTO combo_detail(combo_building_id,material_id) VALUES (1,10);
INSERT INTO combo_detail(combo_building_id,material_id) VALUES (1,11);
INSERT INTO combo_detail(combo_building_id,material_id) VALUES (2,8);
INSERT INTO combo_detail(combo_building_id,material_id) VALUES (2,10);
INSERT INTO combo_detail(combo_building_id,material_id) VALUES (2,12);
INSERT INTO combo_detail(combo_building_id,material_id) VALUES (3,1);
INSERT INTO combo_detail(combo_building_id,material_id) VALUES (3,3);
INSERT INTO combo_detail(combo_building_id,material_id) VALUES (4,1);
INSERT INTO combo_detail(combo_building_id,material_id) VALUES (4,6);
INSERT INTO combo_detail(combo_building_id,material_id) VALUES (5,6);
INSERT INTO combo_detail(combo_building_id,material_id) VALUES (5,4);
INSERT INTO combo_detail(combo_building_id,material_id) VALUES (6,1);
INSERT INTO combo_detail(combo_building_id,material_id) VALUES (6,5);
INSERT INTO combo_detail(combo_building_id,material_id) VALUES (6,7);
INSERT INTO combo_detail(combo_building_id,material_id) VALUES (6,10);
INSERT INTO combo_detail(combo_building_id,material_id) VALUES (6,11);
INSERT INTO combo_detail(combo_building_id,material_id) VALUES (7,3);
INSERT INTO combo_detail(combo_building_id,material_id) VALUES (7,6);
INSERT INTO combo_detail(combo_building_id,material_id) VALUES (7,8);
INSERT INTO combo_detail(combo_building_id,material_id) VALUES (7,10);
INSERT INTO combo_detail(combo_building_id,material_id) VALUES (7,11);
INSERT INTO combo_detail(combo_building_id,material_id) VALUES (8,3);
INSERT INTO combo_detail(combo_building_id,material_id) VALUES (8,6);
INSERT INTO combo_detail(combo_building_id,material_id) VALUES (8,9);
INSERT INTO combo_detail(combo_building_id,material_id) VALUES (8,10);
INSERT INTO combo_detail(combo_building_id,material_id) VALUES (8,11);
INSERT INTO combo_detail(combo_building_id,material_id) VALUES (9,3);
INSERT INTO combo_detail(combo_building_id,material_id) VALUES (9,6);
INSERT INTO combo_detail(combo_building_id,material_id) VALUES (9,9);
INSERT INTO combo_detail(combo_building_id,material_id) VALUES (9,10);
INSERT INTO combo_detail(combo_building_id,material_id) VALUES (9,11);

INSERT INTO building(length,width,status) VALUES (10,50,-1);
INSERT INTO building(length,width,status) VALUES (5,70,2);
INSERT INTO building(length,width,status) VALUES (12,50,0);
INSERT INTO building(length,width,status) VALUES (8,70,1);
INSERT INTO building(length,width,status) VALUES (5,80,1);
INSERT INTO building(length,width,status) VALUES (10,40,-1);
INSERT INTO building(length,width,status) VALUES (10,50,2);
INSERT INTO building(length,width,status) VALUES (10,58,1);
INSERT INTO building(length,width,status) VALUES (12,40,1);
INSERT INTO building(length,width,status) VALUES (17,60,2);
INSERT INTO building(length,width,status) VALUES (13,58,1);

INSERT INTO building_detail(building_id,item_id) VALUES (1,1);
INSERT INTO building_detail(building_id,item_id) VALUES (1,3);
INSERT INTO building_detail(building_id,item_id) VALUES (1,7);
INSERT INTO building_detail(building_id,item_id) VALUES (1,9);
INSERT INTO building_detail(building_id,item_id) VALUES (2,2);
INSERT INTO building_detail(building_id,item_id) VALUES (2,3);
INSERT INTO building_detail(building_id,item_id) VALUES (2,7);
INSERT INTO building_detail(building_id,item_id) VALUES (2,9);
INSERT INTO building_detail(building_id,item_id) VALUES (3,2);
INSERT INTO building_detail(building_id,item_id) VALUES (3,4);
INSERT INTO building_detail(building_id,item_id) VALUES (3,7);
INSERT INTO building_detail(building_id,item_id) VALUES (3,9);
INSERT INTO building_detail(building_id,item_id) VALUES (4,3);
INSERT INTO building_detail(building_id,item_id) VALUES (4,3);
INSERT INTO building_detail(building_id,item_id) VALUES (4,8);
INSERT INTO building_detail(building_id,item_id) VALUES (4,9);
INSERT INTO building_detail(building_id,item_id) VALUES (5,2);
INSERT INTO building_detail(building_id,item_id) VALUES (5,4);
INSERT INTO building_detail(building_id,item_id) VALUES (5,7);
INSERT INTO building_detail(building_id,item_id) VALUES (5,10);
INSERT INTO building_detail(building_id,item_id) VALUES (6,1);
INSERT INTO building_detail(building_id,item_id) VALUES (6,5);
INSERT INTO building_detail(building_id,item_id) VALUES (6,8);
INSERT INTO building_detail(building_id,item_id) VALUES (6,9);
INSERT INTO building_detail(building_id,item_id) VALUES (7,2);
INSERT INTO building_detail(building_id,item_id) VALUES (7,3);
INSERT INTO building_detail(building_id,item_id) VALUES (7,7);
INSERT INTO building_detail(building_id,item_id) VALUES (7,10);
INSERT INTO building_detail(building_id,item_id) VALUES (8,1);
INSERT INTO building_detail(building_id,item_id) VALUES (8,3);
INSERT INTO building_detail(building_id,item_id) VALUES (8,7);
INSERT INTO building_detail(building_id,item_id) VALUES (8,10);
INSERT INTO building_detail(building_id,item_id) VALUES (9,1);
INSERT INTO building_detail(building_id,item_id) VALUES (9,6);
INSERT INTO building_detail(building_id,item_id) VALUES (9,8);
INSERT INTO building_detail(building_id,item_id) VALUES (9,9);
INSERT INTO building_detail(building_id,item_id) VALUES (10,2);
INSERT INTO building_detail(building_id,item_id) VALUES (10,5);
INSERT INTO building_detail(building_id,item_id) VALUES (10,7);
INSERT INTO building_detail(building_id,item_id) VALUES (10,9);
INSERT INTO building_detail(building_id,item_id) VALUES (11,1);
INSERT INTO building_detail(building_id,item_id) VALUES (11,4);
INSERT INTO building_detail(building_id,item_id) VALUES (11,8);
INSERT INTO building_detail(building_id,item_id) VALUES (11,9);

INSERT INTO request_contract(building_id,request_date,status,combo_building_id,user_id) VALUES (1,'2023-10-22',1,1,1);
INSERT INTO request_contract(building_id,request_date,status,combo_building_id,user_id) VALUES (2,'2024-01-25',0,2,1);
INSERT INTO request_contract(building_id,request_date,status,combo_building_id,user_id) VALUES (3,'2023-10-10',1,3,1);
INSERT INTO request_contract(building_id,request_date,status,combo_building_id,user_id) VALUES (4,'2023-11-22',1,4,3);
INSERT INTO request_contract(building_id,request_date,status,combo_building_id,user_id) VALUES (5,'2023-10-22',1,5,5);
INSERT INTO request_contract(building_id,request_date,status,combo_building_id,user_id) VALUES (6,'2023-10-02',1,6,3);
INSERT INTO request_contract(building_id,request_date,status,combo_building_id,user_id) VALUES (7,'2023-01-21',1,7,2);
INSERT INTO request_contract(building_id,request_date,status,combo_building_id,user_id) VALUES (8,'2023-07-03',1,8,2);
INSERT INTO request_contract(building_id,request_date,status,combo_building_id,user_id) VALUES (9,'2023-09-29',1,9,2);
INSERT INTO request_contract(building_id,request_date,status,combo_building_id,user_id) VALUES (10,'2023-02-05',1,1,5);
INSERT INTO request_contract(building_id,request_date,status,combo_building_id,user_id) VALUES (11,'2023-04-07',1,3,5);