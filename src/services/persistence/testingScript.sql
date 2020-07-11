/* Script per pulire il db */
DELETE FROM tournament.match;
ALTER TABLE tournament.match AUTO_INCREMENT = 1;
DELETE from day;
ALTER TABLE day AUTO_INCREMENT = 1;
DELETE FROM board;
ALTER TABLE board AUTO_INCREMENT = 1;
DELETE FROM tournament.group;
ALTER TABLE tournament.group AUTO_INCREMENT = 1;
DELETE FROM team;
ALTER TABLE team AUTO_INCREMENT = 1;
DELETE FROM tournament;
ALTER TABLE tournament AUTO_INCREMENT = 1;
DELETE FROM player;
ALTER TABLE player AUTO_INCREMENT = 1;
DELETE FROM stadium;

/* Script per riempire il db */
INSERT INTO `stadium` VALUES ('Allianz','Turin','50000'),('Artemio Franchi','Firenze','60000'),('Giuseppe Meazza','Milan','70000'),
							 ('Olimpico','Turin','60000'),('Provinciale','Trapani','7500'),('San Paolo','Napoli','70000');

INSERT INTO `manager` VALUES ('admin','admin_name','admin_surname','21232f297a57a5a743894a0e4a801fc3');

INSERT INTO `tournament` VALUES (1,'League1','admin',2),(2,'Knock1','admin',3),(3,'Knock2','admin',3),(4,'Knock3','admin',3),(5,'Mixed1','admin',1);

INSERT INTO `board` VALUES (1,2,1),(2,3,1),(3,4,0);

INSERT INTO `group` VALUES (1,1,0),(2,5,0);

INSERT INTO `day` VALUES (1,1,'2020-06-03 11:00:00',1,NULL),(2,2,'2020-06-10 11:00:00',1,NULL),(3,3,'2020-06-17 11:00:00',1,NULL),
						 (4,4,'2020-06-24 11:00:00',1,NULL),(5,5,'2020-07-01 11:00:00',1,NULL),(6,6,'2020-07-08 11:00:00',1,NULL),(7,7,'2020-07-15 11:00:00',1,NULL),
						 (8,1,'2020-06-03 11:00:00',NULL,1),(9,2,'2020-06-10 11:00:00',NULL,1),(10,1,'2020-06-03 11:00:00',NULL,2),(11,2,'2020-06-10 11:00:00',NULL,2),
						 (12,3,'2020-06-17 11:00:00',NULL,2),(13,1,'2020-06-03 11:00:00',NULL,3),(14,1,'2020-06-03 11:00:00',2,NULL),(15,2,'2020-06-10 11:00:00',2,NULL),
						 (16,3,'2020-06-17 11:00:00',2,NULL);
                         
INSERT INTO `team` VALUES (1,'Inter',1,'Giuseppe Meazza',0,0,0,1,NULL),(2,'Juventus',1,'Allianz',0,0,0,1,NULL),
						  (3,'Milan',1,'Giuseppe Meazza',0,0,0,1,NULL),(4,'Lazio',1,'Olimpico',0,0,0,1,NULL),(5,'Napoli',1,'San Paolo',0,0,0,1,NULL),
						  (6,'Fiorentina',1,'Artemio Franchi',0,0,0,1,NULL),(7,'Torino',1,'Olimpico',0,0,0,1,NULL),(8,'Trapani',1,'Provinciale',0,0,0,1,NULL),
						  (9,'Team1',2,NULL,3,4,2,NULL,1),(10,'Team2',2,NULL,6,5,1,NULL,1),(11,'Team3',2,NULL,0,0,0,NULL,1),(12,'Team4',2,NULL,0,0,0,NULL,1),
						  (13,'Team1',3,NULL,0,0,0,NULL,2),(14,'Team2',3,NULL,0,0,0,NULL,2),(15,'Team3',3,NULL,0,0,0,NULL,2),(16,'Team4',3,NULL,0,0,0,NULL,2),
						  (17,'Team5',3,NULL,0,0,0,NULL,2),(18,'Team6',3,NULL,0,0,0,NULL,2),(19,'Team7',3,NULL,0,0,0,NULL,2),(20,'Team8',3,NULL,0,0,0,NULL,2),
						  (21,'Team1',4,NULL,0,0,0,NULL,3),(22,'Team2',4,NULL,0,0,0,NULL,3),(23,'Team3',4,NULL,0,0,0,NULL,3),(24,'Team4',4,NULL,0,0,0,NULL,3),
						  (25,'Team5',4,NULL,0,0,0,NULL,3),(26,'Team6',4,NULL,0,0,0,NULL,3),(27,'Team7',4,NULL,0,0,0,NULL,3),(28,'Team8',4,NULL,0,0,0,NULL,3),
						  (29,'Team9',4,NULL,0,0,0,NULL,3),(30,'Team10',4,NULL,0,0,0,NULL,3),(31,'Team11',4,NULL,0,0,0,NULL,3),(32,'Team12',4,NULL,0,0,0,NULL,3),
						  (33,'Team13',4,NULL,0,0,0,NULL,3),(34,'Team14',4,NULL,0,0,0,NULL,3),(35,'Team15',4,NULL,0,0,0,NULL,3),(36,'Team16',4,NULL,0,0,0,NULL,3),
						  (37,'T1',5,NULL,0,0,0,2,NULL),(38,'T2',5,NULL,0,0,0,2,NULL),(39,'T3',5,NULL,0,0,0,2,NULL),(40,'T4',5,NULL,0,0,0,2,NULL);					
                         
INSERT INTO `match` VALUES (1,1,NULL,2,6,0,0,0),(2,1,NULL,8,1,0,0,0),(3,1,NULL,5,4,0,0,0),(4,1,NULL,3,7,0,0,0),
						   (5,2,NULL,6,1,0,0,0),(6,2,NULL,4,2,0,0,0),(7,2,NULL,7,8,0,0,0),(8,2,NULL,3,5,0,0,0),(9,3,NULL,4,6,0,0,0),(10,3,NULL,1,7,0,0,0),
						   (11,3,NULL,2,3,0,0,0),(12,3,NULL,8,5,0,0,0),(13,4,NULL,6,7,0,0,0),(14,4,NULL,3,4,0,0,0),(15,4,NULL,5,1,0,0,0),(16,4,NULL,8,2,0,0,0),
						   (17,5,NULL,3,6,0,0,0),(18,5,NULL,7,5,0,0,0),(19,5,NULL,4,8,0,0,0),(20,5,'Giuseppe Meazza',1,2,0,0,0),(21,6,NULL,6,5,0,0,0),
						   (22,6,NULL,8,3,0,0,0),(23,6,NULL,2,7,0,0,0),(24,6,NULL,1,4,0,0,0),(25,7,NULL,8,6,0,0,0),(26,7,NULL,5,2,0,0,0),(27,7,NULL,3,1,0,0,0),
						   (28,7,NULL,7,4,0,0,0),(29,8,NULL,10,12,3,0,1),(30,8,NULL,11,9,0,3,1),(31,9,NULL,10,9,2,1,1),(32,10,NULL,18,16,2,0,1),(33,10,NULL,15,17,2,0,1),
						   (34,10,NULL,13,19,1,0,1),(35,10,NULL,20,14,1,0,1),(36,11,NULL,18,15,3,2,1),(37,11,NULL,13,20,2,1,1),(38,12,NULL,18,13,4,0,1),
						   (39,13,NULL,29,25,0,0,0),(40,13,NULL,23,36,0,0,0),(41,13,NULL,33,34,0,0,0),(42,13,NULL,22,31,0,0,0),(43,13,NULL,26,35,0,0,0),
						   (44,13,NULL,27,21,0,0,0),(45,13,NULL,24,32,0,0,0),(46,13,NULL,30,28,0,0,0),(47,14,NULL,39,37,0,0,0),(48,14,NULL,40,38,0,0,0),
						   (49,15,NULL,37,38,0,0,0),(50,15,NULL,40,39,0,0,0),(51,16,NULL,40,37,0,0,0),(52,16,NULL,38,39,0,0,0);

INSERT INTO `player` VALUES (1,1,1,'Samir','Handanovic',1),(2,1,2,'Diego','Godin',2),(3,1,37,'Milan','Skriniar',2),(4,1,6,'Stefan','De Vrij',2),
							(5,1,9,'Romelu','Lukaku',4),(6,1,10,'Lautaro','Martinez',4),(7,2,1,'Gigi','Buffon',1),(8,2,19,'Leonardo','Bonucci',2),(9,2,10,'Paulo','Dybala',4),
							(10,2,7,'Cristiano','Ronaldo',4),(11,2,9,'Gonzalo','Higuain',4),(12,2,12,'Alex','Sandro',2);
