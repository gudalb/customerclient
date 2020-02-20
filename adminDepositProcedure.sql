USE inl3;

DROP PROCEDURE IF EXISTS inl3.admin_deposit;
DELIMITER $$

CREATE PROCEDURE admin_deposit(
  IN customer_id_param int,
  IN account_id int,
  IN deposit_sum double
)
sp: BEGIN

  DECLARE accountIDcustomerIDcount int default 0;

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


  SELECT count(customer.id) FROM customer 
  INNER JOIN account
  ON customer.id = account.customer_id
  WHERE customer.id = customer_id_param
  AND account.id = account_id
  INTO accountIDcustomerIDcount;

  SET autocommit = 0;
  START TRANSACTION;

    IF accountIDcustomerIDcount = 0 THEN
      SELECT("Account does not exists with customer") AS message;
      leave sp;
    END IF;

    UPDATE account
    SET balance = balance + deposit_sum
    WHERE id = account_id
    AND customer_id = customer_id_param;
    SELECT("Transaction completed") AS message;
  COMMIT;
  SET autocommit = 1;

END$$
DELIMITER ;