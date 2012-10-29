create table PHOTO (
 	pid BIGINT  AUTO_INCREMENT PRIMARY KEY,
	longitude float,
	latitude float,
	radius float,
	photo varchar(50),
	comment TEXT null,
	user BIGINT,
	ts datetime,
	status char(1) default 'P', 
	site char(1) default 'B'
);

create table USER (
 	uid BIGINT  AUTO_INCREMENT PRIMARY KEY,
	idtel varchar(30) null,
	gtoken varchar(30) null,
	email varchar(30) null,
	password varchar(30) null,
	rank int default 0
);
