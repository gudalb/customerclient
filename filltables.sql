use inl3;
-- customers
insert into customer(name, password, ssnumber)
values("albin", "123", "123");

insert into customer(name, password, ssnumber)
values("fred", "123", "456");

insert into customer(name, password, ssnumber)
values("klas", "123", "789");

insert into customer(name, password, ssnumber)
values("Erik", "123", "900101");

insert into customer(name, password, ssnumber)
values("Stefan", "123", "900102");

insert into customer(name, password, ssnumber)
values("Jan", "123", "900103");

-- admins
insert into staff(name, password)
values("Albin", "123");

insert into staff(name, password)
values("Patrik", "123");

insert into staff(name, password)
values("Daniel", "123");

-- Customer accounts
insert into account(customer_id, balance, interest_rate)
	values(1,1000,0.5);

insert into account(customer_id, balance, interest_rate)
	values(1,2500,3.3);

insert into account(customer_id, balance, interest_rate)
	values(2,10000,5);

insert into account(customer_id, balance, interest_rate)
	values(2,45000,0.2);

insert into account(customer_id, balance, interest_rate)
	values(3,500,0.5);

-- Customer loans
insert into loan(customer_id, amount, interest_rate, staff_approved_id)
	values(1,25000,3,1);

insert into loan(customer_id, amount, interest_rate, staff_approved_id)
	values(2,37000,3,null);
    
insert into loan(customer_id, amount, interest_rate, staff_approved_id)
	values(3,45000,3.35,null);

insert into loan(customer_id, amount, interest_rate, staff_approved_id)
	values(4,75000,3.35,1);
    
insert into loan(customer_id, amount, interest_rate, staff_approved_id)
	values(4,12000,5,null);
    
insert into loan(customer_id, amount, interest_rate, staff_approved_id)
	values(4,4500,4.5,null);

insert into loan(customer_id, amount, interest_rate, staff_approved_id)
	values(5,33000,3,2);
    
insert into loan(customer_id, amount, interest_rate, staff_approved_id)
	values(5,80500,1.2,null);
    
insert into loan(customer_id, amount, interest_rate, staff_approved_id)
	values(5,17500,1.1,null);

insert into loan(customer_id, amount, interest_rate, staff_approved_id)
	values(6,500,10,3);

insert into loan(customer_id, amount, interest_rate, staff_approved_id)
	values(6,14500,4,null);
    
insert into loan(customer_id, amount, interest_rate, staff_approved_id)
	values(6,110000,0.4,null);
    
-- customer paymentplans
insert into paymentPlan(loan_id, years)
	values(1, 4);

insert into paymentPlan(loan_id, years)
	values(2, 5);

insert into paymentPlan(loan_id, years)
	values(3, 7);

insert into paymentPlan(loan_id, years)
	values(4, 6);
    
insert into paymentPlan(loan_id, years)
	values(5, 2);

insert into paymentPlan(loan_id, years)
	values(6, 5);
    
insert into paymentPlan(loan_id, years)
	values(7, 6);
    
insert into paymentPlan(loan_id, years)
	values(8, 10);
    
insert into paymentPlan(loan_id, years)
	values(9, 3);
    
insert into paymentPlan(loan_id, years)
	values(10, 2);
    
insert into paymentPlan(loan_id, years)
	values(11, 7);
    
insert into paymentPlan(loan_id, years)
	values(12, 10);