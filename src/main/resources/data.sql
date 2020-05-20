
INSERT INTO userlogin ( username, password )
VALUES 	('Test1', '{bcrypt}$2a$10$rG7uubqFpwAINYR72Q.If.PmHOcY5oj89bkUnSHboouI5jnQHqE6m'),
        ('Test2', '{bcrypt}$2a$10$ydxSJ7BRB2xoWQQoXU9GvuUlFa8I3MJFhN2Lt8hLgBpJUuqH11Wbe'),
        ('Test3', '{bcrypt}$2a$10$uSHePvhAnascrWciVMxCq.oEW7tK0YVqL6YN.n0QXYy.JLjDMTQzK'),
        ('Test4', '{bcrypt}$2a$10$GuZhKZTGpUZKtKdsEst8nu8UKciDzJxMkEtr/M6GTlyPkJpFjH6E.'),
        ('Test5', '{bcrypt}$2a$10$YtE.vu5TOhKf4ATbVE3NTOrJPKBMvkINbsN0C3SwLMFPMQuLE/lHi'),
        ('Test6', '{bcrypt}$2a$10$n/zg2ojtYqqQPdh9sPHP0e5LAAuSMLw4nflf2nBe.XzC6ChWvpsDC'),
        ('Test7', '{bcrypt}$2a$10$S0OVzS5bSgRpw0TD7tzHiOpJqKgE8XhMubFOYOLGolAAnarF1LdyG'),
        ('Test8', '{bcrypt}$2a$10$uPkkKMn5PndIKK541ypz9uofSFLdHnSK/O/NIbFOlB0/Mljd/209a'),
        ('Test9', '{bcrypt}$2a$10$r1mKKcorjoAoKud5dMxWMuRzGZGmP7Ypm9DG1nOAb4EgnRA.nq6fG');

INSERT INTO appuser (loginid, firstname, lastname, isactive, description, tags, email, phonenumber, age, sex )
VALUES (1, 'Kaniel', 'Deller', true , null, ARRAY[ 'bier', 'jogging', 'laufen', 'gaertnern' ], 'ggg@ggg.ch', '0773736366', 44, 'unicorn' ),
       (2, 'Habian', 'Fauser', true, null, ARRAY[ 'bier', 'coden', 'bleh','bluh','blah' ], 'ggg@ggg.ch', '0773736366', 11, 'helicopter' ),
       (3, 'Marhad', 'Fehta', false , 'Clean',  ARRAY[ 'bier', 'bloh', 'bleh','bluh','backend' ], 'ggg@ggg.ch', '0773736366', 33, 'female' ),
	   (4, 'Maurent', 'Letzger', true , 'blah',  ARRAY[ 'bier', 'tauchen', 'baeume','bluh','backend' ], 'ggg@ggg.ch', '0773736366', 4, 'male' ),
	   (5, 'Melix', 'Forgener', true , 'blah',  ARRAY[ 'bier', 'heuen', 'c++','coden','backend' ], 'ggg@ggg.ch', '0773736366', 414, 'snowflake' ),
	   (6, 'FliegtImmerRaus', 'FliegtImmerRaus', true , 'blah',  ARRAY[ 'c', 'c','c','c' ], 'ggg@ggg.ch', '0773736366', 99, 'male' ),
	   (7, 'Daphael', 'ras Gupta', true , 'blah',  ARRAY[ 'bier', 'testTest8s', 'photographieren', 'datenbank','coden','cleancode' ], 'ggg@ggg.ch', '0773736366', 44, 'male' ),
	   (8, 'Chomas', 'Torbat', false , 'blah', ARRAY[ 'testTest8s' ], 'ggg@ggg.ch', '0773736366', 86, 'unicorn' );

INSERT INTO appgroup ( ownerid, name, isactive, description, tags, email, phonenumber, location )
VALUES (1, 'Feierabend Bier', true, 'Wir wollen den Feierabend Feiern!', ARRAY[ 'bier', 'feiern', 'party', 'abend', 'bar' ], 'ggg@ggg.ch', '0773736366', 'diskworld' ),
       (4, 'Rennen', true, 'In Rapperswil am see entlang.', ARRAY[ 'matchesAll', 'jogging', 'beweglichkeit', 'yoga','training','natur' ], 'ggg@ggg.ch', '0773736366', 'hinderpfupfiken' ),
	   (4, 'C++', false, null, ARRAY[ 'matchesAll', 'emoji', 'C++','coden','backend', 'cleancode' ], 'ggg@ggg.ch', '0773736366', 'caldera' ),
	   (3, 'Jogging in Thun', true, null, ARRAY[ 'jogging', 'natur', 'aepfel', 'rennen' ], 'ggg@ggg.ch', '0773736366', null ),
	   (3, 'Jogging', true, 'Wir rennen in Rapperswil vom Bahnhof aus.', ARRAY[ 'matchesAll', 'jogging', 'tauchen', 'kraulen','schwimmen','brustschwimmen' ], 'ggg@ggg.ch', '0773736366', 'bottom of the ocean' ),
	   (3, 'Extreme Debugging', true, 'weeeeeeeeeeeeeeee!!!!', ARRAY[ 'matchesAll', 'jogging', 'c++', 'coden','wandern','baeume', 'heuen' ], 'ggg@ggg.ch', '0773736366', 'rivendell' ),
	   (4, 'TestGruppe', false, null, ARRAY[ 'testTest8s', 'a', 'a','a','a' ], 'ggg@ggg.ch', '0773736366', 'omashu' ),
	   (4, 'Hai-Fischen', true, 'Ice cold', ARRAY[ 'matchesAll', 'testTest8s' ], 'ggg@ggg.ch', '0773736366', 'mordor' );

INSERT INTO socialskill (name)
VALUES 	('Leading'),
		('Communication'),
		('Mentoring'),
		('Negotiation');

INSERT INTO socialskillrating ( skillid, userid, rating )
VALUES 	(1, 1, 4),
		(1, 2, 3),
		(1, 3, 1),
		(2, 1, 5),
		(3, 3, 4),
		(3, 1, 4);

INSERT INTO match ( userid, groupid, useraccept, groupaccept )
VALUES 	(1, 1, false, true),
		(1, 2, null, true),
		(1, 3, null, null),
		(1, 4, null, true),
		(1, 5, null, true),
		(1, 6, null, true),
		(1, 7, null, null),
		(1, 8, true, null),
		(2, 1, true, null),
		(2, 2, true, true),
		(2, 3, true, true),
		(2, 4, null, null),
		(2, 5, null, null),
		(2, 6, null, null),
		(2, 7, null, true),
		(2, 8, null, null),
		(3, 1, null, null),
		(3, 2, true, false),
		(3, 3, null, null),
		(3, 4, true, false),
		(3, 5, null, null),
		(3, 6, null, null),
		(3, 7, null, true),
		(3, 8, null, null),
		(4, 1, null, null),
		(4, 2, true, false),
		(4, 3, true, true),
		(4, 4, null, null),
		(4, 5, null, null),
		(4, 6, null, false),
		(4, 7, null, false),
		(4, 8, null, null),
		(5, 1, null, null),
		(5, 2, false, null),
		(5, 3, true, null),
		(5, 4, false, null),
		(5, 5, null, null),
		(5, 6, null, null),
		(5, 7, null, null),
		(5, 8, null, true),
		(6, 1, null, null),
		(6, 2, null, null),
		(6, 3, true, true),
		(6, 4, false, false),
		(6, 5, null, null),
		(6, 6, null, null),
		(6, 7, null, null),
		(6, 8, null, null),
		(7, 1, null, null),
		(7, 2, null, null),
		(7, 3, null, null),
		(7, 4, null, null),
		(7, 5, true, null),
		(7, 6, null, null),
		(7, 7, null, null),
		(7, 8, null, null),
		(8, 1, null, null),
		(8, 2, null, null),
		(8, 3, null, null),
		(8, 4, null, true),
		(8, 5, true, true),
		(8, 6, null, true),
		(8, 7, null, null),
		(8, 8, null, null);








