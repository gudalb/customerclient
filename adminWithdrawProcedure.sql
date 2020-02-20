USE inl3;

DROP PROCEDURE IF EXISTS inl3.admin_withdraw;
DELIMITER $$

CREATE PROCEDURE admin_withdraw(
	IN customer_id_param int,
  IN account_id int,
  IN withdraw_sum double
)
sp: BEGIN

  DECLARE accountIDcustomerIDcount int default 0;
  DECLARE currentbalance double default 0;
  DECLARE balanceAfter double default 0;

  DECLARE exit handler for sqlexception
  BEGIN
    ROLLBACK;
    SELECT("ROLLBACK due to sqlexception");
  END;

  DECLARE exit handler for sqlwarning
  BEGIN
    ROLLBACK;
    SELECT("ROLLBACK due to sqlwarning");
  END;

  DECLARE exit handler for 1062
  BEGIN
    ROLLBACK;
    SELECT("ROLLBACK due to broken constraint");
  END;

  SELECT balance FROM account 
  WHERE customer_id LIKE customer_id_param
  AND id LIKE account_id
  INTO currentbalance;

  SELECT count(customer.id) FROM customer 
  INNER JOIN account
  ON customer.id = account.customer_id
  WHERE customer.id = customer_id_param
  AND account.id = account_id
  INTO accountIDcustomerIDcount;

  SET balanceAfter = currentbalance - withdraw_sum;

  SET autocommit = 0;
  START TRANSACTION;

    IF accountIDcustomerIDcount = 0 THEN
      SELECT("Account does not exists with customer") AS message;
      leave sp;
    END IF;
      IF currentbalance = 0 THEN
      SELECT("Account balance is 0") AS message;
      leave sp;
    END IF;
      IF balanceAfter < 0 THEN
      SELECT("Insufficient funds") AS message;
      leave sp;
    END IF;

    UPDATE account
    SET balance = balance - withdraw_sum
    WHERE id = account_id
    AND customer_id = customer_id_param;

	  SELECT("Transaction completed") AS message;

  COMMIT;
  SET autocommit = 1;

END$$
DELIMITER ;