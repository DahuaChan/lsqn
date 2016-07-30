drop database if exists lsqn;
create database lsqn;
use lsqn;
create table author (
	a_id int auto_increment primary key,
	a_email varchar(255),
	a_name varchar(255),
	a_nickname varchar(255),
	a_password varchar(255)
);
create table admin (
	admin_id int auto_increment primary key,
	admin_account varchar(255),
	admin_password varchar(255),
	admin_type int
);
create table message (
	msg_id int auto_increment primary key,
	msg_title varchar(255),
	msg_content text,
	msg_status int,
	msg_date datetime NOT NULL,
	a_id int,
	admin_id int,
	foreign key(a_id) references author(a_id) on delete cascade on update cascade,
	foreign key(admin_id) references admin(admin_id) on delete cascade on update cascade
);
use lsqn;
insert into admin(admin_account,admin_password,admin_type) values("dahua","000000",1);
insert into author(a_email,a_name,a_nickname,a_password) values("313921056@qq.com","达华","一万年不是尽头","000000");
