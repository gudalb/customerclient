USE inl3;

DROP PROCEDURE IF EXISTS inl3.delete_customer;
DELIMITER $$

CREATE PROCEDURE delete_customer(
	IN customer_id_param int
)
BEGIN 

  DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
		SELECT("rollback due to sqlexception") as error;
        ROLLBACK;
    END;
    
  SET autocommit = 0;

  START TRANSACTION;

    DELETE FROM account
    WHERE customer_id = customer_id_param;

    DELETE FROM loan
    WHERE customer_id = customer_id_param;

    DELETE FROM customer
    WHERE id = customer_id_param;
  COMMIT;
  SET autocommit = 1;
END$$ 

DELIMITER ;