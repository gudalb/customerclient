use inl3;

drop procedure if exists transaction;
delimiter //
create procedure transaction(in customerID int, accountID int, amount double)
sp:	begin

declare accountIDcustomerIDcount int default 0;
declare currentbalance double default 0;
declare balanceAfter double default 0;

declare exit handler for sqlexception
begin
	rollback;
	select("rollback due to sqlexception");
	end;

declare exit handler for sqlwarning
begin
rollback;
select("rollback due to sqlwarning");
end;

declare exit handler for 1062
begin
rollback;
select("rollback due to broken constraint");
end;


select balance from account 
where customer_id like customerID
and id like accountID
into currentbalance;


select count(customer.id) from customer 
inner join account
on customer.id = account.customer_id
where customer.id = customerID
and account.id = accountID
into accountIDcustomerIDcount;

set balanceAfter = currentbalance + amount;

start transaction;

	if accountIDcustomerIDcount = 0 then
		select("Account does not exits with customer") as message;
		leave sp;
	end if;
		if currentbalance = 0 then
		select("Account balance is 0") as message;
		leave sp;
	end if;
		if balanceAfter < 0 then
		select("Insufficient funds") as message;
		leave sp;
	end if;

	update account
	set balance = balance + amount
	where id = accountID
	and customer_id = customerID;

	select("Transaction completed") as message;

commit;


end//
delimiter ;