create table client (
	
	id bigint not null auto_increment,
	name varchar(200) not null,
	email varchar(255) not null,
	phone varchar (20) not null,
	
	primary key (id)
	
);