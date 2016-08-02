drop database if exists lsqn;
create database lsqn;
use lsqn;
create table author (
	a_id int auto_increment primary key,
	a_email varchar(255) NOT NULL,
	a_name varchar(255),
	a_nickname varchar(255),
	a_password varchar(255) NOT NULL
);
create table admin (
	admin_id int auto_increment primary key,
	admin_account varchar(255) not null,
	admin_password varchar(255) not null,
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
insert into admin(admin_account,admin_password,admin_type) values("admin","000000",1);
