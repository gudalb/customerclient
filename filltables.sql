use inl3;

insert into customer(name, password, ssnumber)
values("albin", "123", "123");

insert into customer(name, password, ssnumber)
values("fred", "123", "456");

insert into customer(name, password, ssnumber)
values("klas", "123", "789");

insert into staff(name, password)
values("greta", "123");

insert into staff(name, password)
values("greta", "123");

insert into staff(name, password)
values("greta", "123");


insert into account(customer_id, balance, interest_rate)
	values(1,1000,0.5);


insert into account(customer_id, balance, interest_rate)
	values(1,2500,0.5);


insert into account(customer_id, balance, interest_rate)
	values(2,1000,0.5);


insert into account(customer_id, balance, interest_rate)
	values(2,45000,0.5);


insert into account(customer_id, balance, interest_rate)
	values(3,500,0.5);


insert into loan(customer_id, amount, interest_rate, staff_approved_id)
	values(1,25000,3,1);


insert into loan(customer_id, amount, interest_rate, staff_approved_id)
	values(2,37000,3,null);


