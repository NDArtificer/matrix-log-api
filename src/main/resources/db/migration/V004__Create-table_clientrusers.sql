create table client_user (
	
	id bigint not null auto_increment,
	username varchar(50) not null,
	password varchar(50) not null,
	
	primary key (id)
	
);