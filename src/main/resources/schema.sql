DROP TABLE IF EXISTS socialskillrating;
DROP TABLE IF EXISTS socialskill;
DROP TABLE IF EXISTS match;
DROP TABLE IF EXISTS appgroup;
DROP TABLE IF EXISTS appuser;
DROP TABLE IF EXISTS userlogin;

CREATE TABLE  userlogin
(
    loginid  	 SERIAL PRIMARY KEY,
    username	 varchar(30) NOT NULL,
    password     varchar(80) NOT NULL
);

CREATE TABLE  appuser
(
    userid		 SERIAL PRIMARY KEY,
	loginid		 integer     NULL   REFERENCES userlogin (loginid) ON DELETE CASCADE,
	isactive	 boolean default true  NOT NULL,
    firstname    varchar(30) NOT NULL,
    lastname     varchar(30) NOT NULL,
    description  varchar(200) NULL,
	tags  		 text[] NOT NULL,
	email        varchar(80) NULL,
	phonenumber  varchar(20) NULL,
	age          integer NULL,
	sex          varchar(20) NULL
);

CREATE TABLE  appgroup
(
	groupid		SERIAL PRIMARY KEY,
    ownerid	 	integer     NOT NULL   REFERENCES appuser (userid) ON DELETE CASCADE,
	isactive	 boolean default true  NOT NULL,
    name   		 varchar(30) NOT NULL,
    description  varchar(200) NULL,
	tags  		 text[] NOT NULL,
	email        varchar(80) NULL,
	phonenumber  varchar(20) NULL,
	location     varchar(30) NULL
);


CREATE TABLE  match
(
	matchid			SERIAL PRIMARY KEY,
	userid		 	integer     NOT NULL   REFERENCES appuser (userid) ON DELETE CASCADE,
	groupid		 	integer     NOT NULL   REFERENCES appgroup (groupid) ON DELETE CASCADE,
	useraccept		boolean default null,
	groupaccept		boolean default null
);

CREATE TABLE  socialskill
(
	skillid  	 	SERIAL PRIMARY KEY,
	name			char(30)	NOT NULL
);

CREATE TABLE  socialskillrating
(
	skillid  	 	integer     NOT NULL   REFERENCES socialskill (skillid),
	userid			integer     NOT NULL   REFERENCES appuser (userid),
	rating			integer

);
