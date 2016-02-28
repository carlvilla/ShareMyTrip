DELETE FROM TAPPLICATIONS;
DELETE FROM TRATINGS;
DELETE FROM TSEATS;
DELETE FROM TTRIPS;
DELETE FROM TUSERS;

INSERT INTO TUSERS VALUES(306,'user1@mail.com','user1','Fernando','user1',0,'Alvarez');
INSERT INTO TUSERS VALUES(307,'user2@mail.com','user2','Luisa','user2',0,'Perez');
INSERT INTO TUSERS VALUES(308,'correo@uniovi.es','pedro','Pedro','pedro',0,'Chen');
INSERT INTO TUSERS VALUES(309,'usuario1@uniovi.es','usuario1','usuario1','usuario1',0,'usuario1');
INSERT INTO TUSERS VALUES(312,'usuario2@gmail.com','usuario2','usuario2','usuario2',0,'usuario2');
INSERT INTO TUSERS VALUES(313,'usuario3@uniovi.es','usuario3','usuario3','usuario3',0,'usuario3');

INSERT INTO TTRIPS VALUES(29,'2016-02-19 08:20:10.000000',1,'2016-02-18 08:20:10.000000','Buen rollo4','address10','Oviedo','country10','state10',43.34343E0,-6.53434E0,'zipcode10','2016-02-19 08:20:10.000000','address11','Madrid','country11','state11',43.34343E0,-6.53434E0,'zipcode11',50.0E0,5,0,306);
INSERT INTO TTRIPS VALUES(30,'2016-02-19 08:20:10.000000',4,'2016-02-18 08:20:10.000000','Buen rollo5','address12','Barcelona','country12','state12',43.34343E0,-6.53434E0,'zipcode12','2016-02-19 08:20:10.000000','address13','Valencia','country13','state13',43.34343E0,-6.53434E0,'zipcode13',50.0E0,5,0,307);
INSERT INTO TTRIPS VALUES(31,'2020-03-05 10:00:00.000000',1,'2020-03-03 10:00:00.000000','Persona habladora','calle1','Oviedo','Spain','Asturias',43.34343E0,-6.53434E0,'39484','2020-03-05 10:30:00.000000','Calle2','Madrid','Spain','Madrid',43.34343E0,-6.53434E0,'43513',50.0E0,10,0,309);
INSERT INTO TTRIPS VALUES(32,'2020-03-10 11:00:00.000000',2,'2020-03-09 22:00:00.000000','Buen rollo','calle1','Gijon','Spain','Asturias',43.34343E0,-6.53434E0,'83744','2020-03-10 10:30:00.000000','Calle2','Oviedo','Spain','Asturias',43.34343E0,-6.53434E0,'54233',50.0E0,5,0,312);
INSERT INTO TTRIPS VALUES(33,'2020-03-01 12:00:00.000000',3,'2020-02-29 10:00:00.000000','Buen rollo','calle1','Oviedo','Spain','Asturias',43.34343E0,-6.53434E0,'73682','2020-03-01 11:20:00.000000','Calle2','Ribadesella','Spain','Asturias',43.34343E0,-6.53434E0,'45314',50.0E0,5,0,313);

INSERT INTO TTRIPS VALUES(34,'2020-03-01 12:00:00.000000',3,'2020-02-29 10:00:00.000000','Buen rollo','calle1','Oviedo','Spain','Asturias',43.34343E0,-6.53434E0,'73682','2020-03-01 11:20:00.000000','Calle2','Llanes','Spain','Asturias',43.34343E0,-6.53434E0,'45314',50.0E0,5,0,312);