DROP DATABASE IF EXISTS inl3;
create database inl3;
	USE inl3;

	create table customer
		(id int not null auto_increment,
			name varchar(30) not null,
			ssnumber varchar(12) not null,
			password varchar(30) not null,
			primary key (id));

	create table staff
		(id int not null auto_increment,
			name varchar(30) not null,
			password varchar(30) not null,
			primary key(id));


	create table account
		(id int not null auto_increment,
			customer_id int not null,
			balance double default 0,
			interest_rate double default 0,
			primary key (id),
			foreign key (customer_id) references customer(id));

	create table loan
		(id int not null auto_increment,
			customer_id int not null,
			amount double not null,
			interest_rate double default 0,
			staff_approved_id int,
			primary key (id),
			foreign key (customer_id) references customer(id),
			foreign key (staff_approved_id) references staff(id));
            
	create table paymentPlan (
		id int NOT NULL auto_increment,
		loan_id int NOT NULL,
		years double NOT NULL,
		PRIMARY KEY(id), 
		FOREIGN KEY (loan_id) REFERENCES loan(id)
);