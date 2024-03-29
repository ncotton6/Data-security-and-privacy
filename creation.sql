-- MySQL Script generated by MySQL Workbench
-- 05/11/16 22:40:38
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`users` ;

CREATE TABLE IF NOT EXISTS `mydb`.`users` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `username` VARBINARY(1024) NOT NULL,
  `first_name` VARBINARY(1024) NOT NULL,
  `last_name` VARBINARY(1024) NOT NULL,
  `email` VARBINARY(1024) NOT NULL,
  `password` VARBINARY(1024) NOT NULL,
  `role` INT NULL,
  `joinedOn` VARBINARY(1024) NOT NULL,
  PRIMARY KEY (`idUser`),
  INDEX `role` (`role` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`product` ;

CREATE TABLE IF NOT EXISTS `mydb`.`product` (
  `idproduct` INT NOT NULL AUTO_INCREMENT,
  `name` VARBINARY(1024) NOT NULL,
  `description` VARBINARY(1024) NOT NULL,
  `active` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idproduct`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`prices`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`prices` ;

CREATE TABLE IF NOT EXISTS `mydb`.`prices` (
  `amount` INT NOT NULL,
  `date` TIMESTAMP NOT NULL,
  `changedBy` INT NOT NULL,
  `idProduct` INT NOT NULL,
  PRIMARY KEY (`idProduct`, `date`),
  INDEX `fk_user_idx` (`changedBy` ASC),
  CONSTRAINT `fk_product`
    FOREIGN KEY (`idProduct`)
    REFERENCES `mydb`.`product` (`idproduct`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user`
    FOREIGN KEY (`changedBy`)
    REFERENCES `mydb`.`users` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`productOrder`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`productOrder` ;

CREATE TABLE IF NOT EXISTS `mydb`.`productOrder` (
  `idorder` INT NOT NULL AUTO_INCREMENT,
  `product_id` INT NOT NULL,
  `qty` INT NOT NULL,
  `fulfilled` TINYINT(1) NOT NULL DEFAULT 0,
  `fulfilledBy` INT NULL,
  PRIMARY KEY (`idorder`, `product_id`),
  INDEX `fk_productOrder_product_idx` (`product_id` ASC),
  INDEX `fk_prodcutOrder_fulfilled_idx` (`fulfilledBy` ASC),
  CONSTRAINT `fk_productOrder_product`
    FOREIGN KEY (`product_id`)
    REFERENCES `mydb`.`product` (`idproduct`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_productOrder_fulfilled`
    FOREIGN KEY (`fulfilledBy`)
    REFERENCES `mydb`.`users` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`orders` ;

CREATE TABLE IF NOT EXISTS `mydb`.`orders` (
  `idUser` INT NOT NULL,
  `idOrder` INT NOT NULL,
  `date` VARBINARY(1024) NOT NULL,
  PRIMARY KEY (`idUser`, `idOrder`),
  INDEX `fk_users_has_order_order1_idx` (`idOrder` ASC),
  INDEX `fk_users_has_order_users1_idx` (`idUser` ASC),
  CONSTRAINT `fk_users_has_order_users1`
    FOREIGN KEY (`idUser`)
    REFERENCES `mydb`.`users` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_order_order1`
    FOREIGN KEY (`idOrder`)
    REFERENCES `mydb`.`productOrder` (`idorder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`session`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`session` ;

CREATE TABLE IF NOT EXISTS `mydb`.`session` (
  `idsession` INT NOT NULL AUTO_INCREMENT,
  `uuid` VARBINARY(1024) NULL,
  `userId` INT NOT NULL,
  `createdOn` DATETIME(2) NOT NULL,
  UNIQUE INDEX `uuid_UNIQUE` (`uuid` ASC),
  INDEX `fk_users_idx` (`userId` ASC),
  PRIMARY KEY (`idsession`),
  CONSTRAINT `fk_users`
    FOREIGN KEY (`userId`)
    REFERENCES `mydb`.`users` (`idUser`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`roles` ;

CREATE TABLE IF NOT EXISTS `mydb`.`roles` (
  `idRole` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`idRole`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`hireQueue`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`hireQueue` ;

CREATE TABLE IF NOT EXISTS `mydb`.`hireQueue` (
  `idHire` INT NOT NULL AUTO_INCREMENT,
  `idUser` INT NOT NULL,
  `requestedRole` INT NOT NULL,
  `managerSignOff` INT NULL,
  `hrSignOff` INT NULL,
  PRIMARY KEY (`idHire`),
  INDEX `fk_user1_idx` (`idUser` ASC),
  INDEX `fk_user2_idx` (`managerSignOff` ASC),
  INDEX `fk_user3_idx` (`hrSignOff` ASC),
  INDEX `fk_role_idx` (`requestedRole` ASC),
  CONSTRAINT `fk_user1`
    FOREIGN KEY (`idUser`)
    REFERENCES `mydb`.`users` (`idUser`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user2`
    FOREIGN KEY (`managerSignOff`)
    REFERENCES `mydb`.`users` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user3`
    FOREIGN KEY (`hrSignOff`)
    REFERENCES `mydb`.`users` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_role`
    FOREIGN KEY (`requestedRole`)
    REFERENCES `mydb`.`roles` (`idRole`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `mydb` ;

-- -----------------------------------------------------
-- procedure createUser
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`createUser`;

DELIMITER $$
USE `mydb`$$
CREATE PROCEDURE `createUser` 
(
	IN username VARBINARY(1024),
    IN first_name VARBINARY(1024),
    IN last_name VARBINARY(1024),
    IN email VARBINARY(1024),
    IN password VARBINARY(1024),
    IN paramkey VARCHAR(100)  
)
BEGIN

    set @aeskey = CONCAT(paramkey,'test');

	INSERT INTO users (username,first_name,last_name,email,password,joinedOn)
    values (
		AES_ENCRYPT(username,@aeskey),
        AES_ENCRYPT(first_name,@aeskey),
        AES_ENCRYPT(last_name,@aeskey),
        AES_ENCRYPT(email,@aeskey),
        AES_ENCRYPT(password,@aeskey),
        AES_ENCRYPT(NOW(),@aeskey)
    );
    
    select LAST_INSERT_ID();

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure createProduct
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`createProduct`;

DELIMITER $$
USE `mydb`$$
CREATE PROCEDURE `createProduct` 
(
	IN name VARBINARY(1024),
    IN description VARBINARY(1024),
    IN active bool,
    IN price INT,
    IN user INT,
    IN paramkey VARCHAR(100)
)
BEGIN
	
    DECLARE pid INT;
    set @aeskey = CONCAT(paramkey,'test');

	-- Check that the user has the right to update
    
    -- Insert into products
    insert into product (name,description,active)
    values (
		AES_ENCRYPT(name,@aeskey),
        AES_ENCRYPT(description,@aeskey),
        active
    );
    
    SET @pid = LAST_INSERT_ID();
    
    -- Insert into prices
    insert into prices(amount,date,changedBy,idProduct)
    values (
		price,
		NOW(),
        user,
        @pid
    );
    
    
    select @pid;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure requestHire
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`requestHire`;

DELIMITER $$
USE `mydb`$$
CREATE PROCEDURE `requestHire` 
(
	IN userId INT,
    IN roleId INT
)
BEGIN

	INSERT into hireQueue (idUser,requestedRole)
    values (userId,roleId);
    
    SELECT LAST_INSERT_ID();

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure createSession
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`createSession`;

DELIMITER $$
USE `mydb`$$
CREATE PROCEDURE `createSession` 
(
	IN uuid VARBINARY(1024),
    IN userId INT,
    IN paramkey VARCHAR(100)
)
BEGIN

	DECLARE aeskey BLOB default 'test';
    set @aeskey = CONCAT(paramkey,'test');

	INSERT INTO session (uuid,userId,createdOn)
    values (
		AES_ENCRYPT(uuid,@aeskey),
        userId,
        NOW()
    );

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure updatePrice
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`updatePrice`;

DELIMITER $$
USE `mydb`$$
CREATE PROCEDURE `updatePrice` (
	IN amount INT,
    IN changedBy INT,
    IN idProduct INT
)
BEGIN
    
    insert into prices (amount,date,changedBy,idProduct)
    values (amount,NOW(),changedBy,idProduct);

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure placeOrder
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`placeOrder`;

DELIMITER $$
USE `mydb`$$
CREATE PROCEDURE `placeOrder` 
(
	IN idProduct INT,
    IN qty INT,
    IN userId INT,
    IN paramKey VARCHAR(100)
)
BEGIN

	DECLARE orderId INT;
	DECLARE aeskey BLOB default 'test';
    set @aeskey = CONCAT(paramKey,'test');
    
    insert into productOrder(product_id,qty)
    values (idProduct,qty);
    
    set @orderId = LAST_INSERT_ID();
    
    insert into orders(idUser,idOrder,date)
    values (userId,@orderID,AES_ENCRYPT(NOW(),@aeskey));
    
    select @orderID;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getUser
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`getUser`;

DELIMITER $$
USE `mydb`$$
CREATE PROCEDURE `getUser` (
	IN userId INT,
    IN paramKey VARCHAR(100)
)
BEGIN

	DECLARE aeskey VARCHAR(150);
    set @aeskey = CONCAT(paramKey,'test');
    
	select 
		idUser,
        AES_DECRYPT(username,@aeskey) as 'username',
        AES_DECRYPT(first_name,@aeskey) as 'first_name',
        AES_DECRYPT(last_name,@aeskey) as 'last_name',
        AES_DECRYPT(email,@aeskey) as 'email',
        AES_DECRYPT(password,@aeskey) as 'password',
        role,
        AES_DECRYPT(joinedON,@aeskey) as 'joinedON'
        from users where idUser = userId;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getSession
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`getSession`;

DELIMITER $$
USE `mydb`$$
CREATE PROCEDURE `getSession` 
(
	IN uid INT,
    IN paramkey VARCHAR(100)
)
BEGIN

    set @aeskey = CONCAT(paramkey,'test');
    
    select idsession, AES_DECRYPT(uuid,@aeskey) as 'uuid', userId, AES_DECRYPT(createdOn,@aeskey) as 'createdOn' from session where userId = uid;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getHire
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`getHire`;

DELIMITER $$
USE `mydb`$$
CREATE PROCEDURE `getHire` ()
BEGIN

	SELECT * FROM hireQueue;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getProducts
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`getProducts`;

DELIMITER $$
USE `mydb`$$
CREATE PROCEDURE `getProducts` (
	IN paramkey VARCHAR(100)
)
BEGIN

	DECLARE aeskey BLOB;
    set @aeskey = CONCAT(paramkey,'test');

	SELECT 
    p.idProduct,
    AES_DECRYPT(p.name,@aeskey) as 'name',
    AES_DECRYPT(p.description,@aeskey) as 'description',
    p.active,
    pr.amount,
    pr.date,
    pr.changedBy
    FROM product as p
    left join prices as pr
    on p.idProduct = pr.idProduct;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getProduct
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`getProduct`;

DELIMITER $$
USE `mydb`$$
CREATE PROCEDURE `getProduct` (
    IN productId INT,
	IN paramkey VARCHAR(100)
)
BEGIN

	DECLARE aeskey BLOB;
    set @aeskey = CONCAT(paramkey,'test');

	SELECT 
    p.idProduct,
    AES_DECRYPT(p.name,@aeskey) as 'name',
    AES_DECRYPT(p.description,@aeskey) as 'description',
    p.active,
    pr.amount,
    pr.date,
    pr.changedBy
    FROM product as p
    left join prices as pr
    on p.idProduct = pr.idProduct
    where p.idProduct = productId;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure fulFillOrder
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`fulFillOrder`;

DELIMITER $$
USE `mydb`$$
CREATE PROCEDURE `fulFillOrder` (
	IN orderId INT,
    IN fulfillerID INT
)
BEGIN

	IF 1 = (select count(*) from productOrder where idOrder = orderId and fulfilledBy is NULL) THEN
		UPDATE productOrder
		set fulfilled = True,
		fulfilledBy = fulfillerID
		where idOrder = orderId;
	END IF;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure managerHireSignOff
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`managerHireSignOff`;

DELIMITER $$
USE `mydb`$$
CREATE PROCEDURE `managerHireSignOff` (
	IN userId INT,
    IN managerId INT,
    IN roleId INT
)
BEGIN

	update hireQueue
    set managerSignOff = managerId
    where idUser = userId
    and requestedRole = roleId;
    
    IF 1 = (select count(*) from hireQueue where idUser = userId and requestedRole = roleId and hrSignOff is not null and managerSignOff is not null)
    THEN
		update users
        set role = roleId
        where idUser = userId;
    END IF;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure hrHireSignOff
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`hrHireSignOff`;

DELIMITER $$
USE `mydb`$$
CREATE PROCEDURE `hrHireSignOff` (
	IN userId INT,
    IN hrId INT,
    IN roleId INT
)
BEGIN

	update hireQueue
    set hrSignOff = hrId
    where idUser = userId
    and requestedRole = roleId;
    
    IF 1 = (select count(*) from hireQueue where idUser = userId and requestedRole = roleId and hrSignOff is not null and managerSignOff is not null)
    THEN
		update users
        set role = roleId
        where idUser = userId;
    END IF;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure updateProduct
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`updateProduct`;

DELIMITER $$
USE `mydb`$$
CREATE PROCEDURE `updateProduct` (
	IN pid INT,
    IN name VARBINARY(1024),
    IN description varbinary(1024),
    IN active BOOL,
    IN paramkey VARCHAR(512)
)
BEGIN

	set @aeskey = CONCAT(paramkey,'test');

	update product
    set 
    name = AES_ENCRYPT(name,@aeskey),
    description = AES_ENCRYPT(description,@aeskey),
    active = active
    where
    idProduct = pid;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure fireEmployee
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`fireEmployee`;

DELIMITER $$
USE `mydb`$$
CREATE PROCEDURE `fireEmployee` (
	IN userId INT
)
BEGIN

	UPDATE users
    set role = null
    where idUser = userId;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getOrders
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`getOrders`;

DELIMITER $$
USE `mydb`$$
CREATE PROCEDURE `getOrders` ()
BEGIN

	select * from productOrder where fulfilled = False;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getUserPassword
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`getUserPassword`;

DELIMITER $$
USE `mydb`$$
CREATE PROCEDURE `getUserPassword` (
	IN uname VARBINARY(1024),
    IN paramKey varchar(100)
)
BEGIN

	set @aeskey = CONCAT(paramKey,'test');

	select 
		idUser,
        AES_DECRYPT(username,@aeskey) as 'username',
        AES_DECRYPT(password,@aeskey) as 'password'
        from users where AES_DECRYPT(username,@aeskey) = uname;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getUserFromSession
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`getUserFromSession`;

DELIMITER $$
USE `mydb`$$
CREATE PROCEDURE `getUserFromSession` (
	IN puuid VARBINARY(1024),
    IN paramKey varchar(100)
)
BEGIN
	
    set @aeskey = CONCAT(paramkey, 'test');
    
    delete from session where createdOn <= DATE_SUB(NOW(), interval 1 hour);

	select 
    idsession,
    AES_DECRYPT(uuid,@aeskey) as 'uuid',
    userId,
    createdOn
    from session where  AES_DECRYPT(uuid,@aeskey) = puuid;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure updateUser
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`updateUser`;

DELIMITER $$
USE `mydb`$$
CREATE PROCEDURE `updateUser` (
	IN uID int,
	IN firstName VARBINARY(1024),
    IN lastName varbinary(1024),
    IN pemail varbinary(1024),
    IN paramkey varchar(100)
)
BEGIN
	
	set @aeskey = CONCAT(paramkey,'test');
    
	update users
    set
    first_name = AES_ENCRYPT(firstName,@aeskey),
    last_name = AES_ENCRYPT(lastName,@aeskey),
    email = AES_ENCRYPT(pemail,@aeskey)
    where idUser = uID;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure changePassword
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`changePassword`;

DELIMITER $$
USE `mydb`$$
CREATE PROCEDURE `changePassword` (
	IN uId int,
    IN passw VARBINARY(1024),
    IN paramkey VARCHAR(100)
)
BEGIN

	set @aeskey = CONCAT(paramkey,'test');

	update users
    set password = AES_ENCRYPT(passw,@aeskey)
    where idUser = uId;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getUsers
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`getUsers`;

DELIMITER $$
USE `mydb`$$
CREATE PROCEDURE `getUsers` (
	IN paramkey varchar(100)
)
BEGIN

	set @aeskey = CONCAT(paramkey,'test');

	select 
    idUser,
    AES_DECRYPT(username,@aeskey) as 'username',
    AES_DECRYPT(first_name,@aeskey) as 'first_name',
    AES_DECRYPT(last_name,@aeskey) as 'last_name',
    AES_DECRYPT(email,@aeskey) as 'email',
    AES_DECRYPT(password,@aeskey) as 'password',
    role,
    AES_DECRYPT(joinedOn,@aeskey) as 'joinedOn'
    from users;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure deleteSession
-- -----------------------------------------------------

USE `mydb`;
DROP procedure IF EXISTS `mydb`.`deleteSession`;

DELIMITER $$
USE `mydb`$$
CREATE PROCEDURE `deleteSession` (
	IN sessionID VARBINARY(1024),
    IN paramKey varchar(100)
)
BEGIN

	set @aeskey = CONCAT(paramKey,'test');

	delete from session
    where AES_DECRYPT(uuid,@aeskey) = sessionID;

END$$

DELIMITER ;
SET SQL_MODE = '';
GRANT USAGE ON *.* TO customer;
 DROP USER customer;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'customer' IDENTIFIED BY 'customer';

GRANT EXECUTE ON procedure `mydb`.`createSession` TO 'customer';
GRANT EXECUTE ON procedure `mydb`.`createUser` TO 'customer';
GRANT EXECUTE ON procedure `mydb`.`getProduct` TO 'customer';
GRANT EXECUTE ON procedure `mydb`.`getUser` TO 'customer';
GRANT EXECUTE ON procedure `mydb`.`placeOrder` TO 'customer';
GRANT EXECUTE ON procedure `mydb`.`getProducts` TO 'customer';
GRANT EXECUTE ON procedure `mydb`.`requestHire` TO 'customer';
GRANT EXECUTE ON procedure `mydb`.`getUserPassword` TO 'customer';
GRANT EXECUTE ON procedure `mydb`.`getUserFromSession` TO 'customer';
GRANT EXECUTE ON procedure `mydb`.`updateUser` TO 'customer';
GRANT EXECUTE ON procedure `mydb`.`changePassword` TO 'customer';
GRANT EXECUTE ON procedure `mydb`.`deleteSession` TO 'customer';
SET SQL_MODE = '';
GRANT USAGE ON *.* TO employee;
 DROP USER employee;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'employee' IDENTIFIED BY 'employee';

GRANT EXECUTE ON procedure `mydb`.`fulFillOrder` TO 'employee';
GRANT EXECUTE ON procedure `mydb`.`getOrders` TO 'employee';
GRANT EXECUTE ON procedure `mydb`.`createSession` TO 'employee';
GRANT EXECUTE ON procedure `mydb`.`createUser` TO 'employee';
GRANT EXECUTE ON procedure `mydb`.`getProduct` TO 'employee';
GRANT EXECUTE ON procedure `mydb`.`getUser` TO 'employee';
GRANT EXECUTE ON procedure `mydb`.`placeOrder` TO 'employee';
GRANT EXECUTE ON procedure `mydb`.`getProducts` TO 'employee';
GRANT EXECUTE ON procedure `mydb`.`requestHire` TO 'employee';
GRANT EXECUTE ON procedure `mydb`.`getUserPassword` TO 'employee';
GRANT EXECUTE ON procedure `mydb`.`getUserFromSession` TO 'employee';
GRANT EXECUTE ON procedure `mydb`.`updateUser` TO 'employee';
GRANT EXECUTE ON procedure `mydb`.`changePassword` TO 'employee';
GRANT EXECUTE ON procedure `mydb`.`deleteSession` TO 'employee';
SET SQL_MODE = '';
GRANT USAGE ON *.* TO manager;
 DROP USER manager;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'manager' IDENTIFIED BY 'manager';

GRANT EXECUTE ON procedure `mydb`.`fireEmployee` TO 'manager';
GRANT EXECUTE ON procedure `mydb`.`getHire` TO 'manager';
GRANT EXECUTE ON procedure `mydb`.`managerHireSignOff` TO 'manager';
GRANT EXECUTE ON procedure `mydb`.`getUsers` TO 'manager';
GRANT EXECUTE ON procedure `mydb`.`fulFillOrder` TO 'manager';
GRANT EXECUTE ON procedure `mydb`.`getOrders` TO 'manager';
GRANT EXECUTE ON procedure `mydb`.`createSession` TO 'manager';
GRANT EXECUTE ON procedure `mydb`.`createUser` TO 'manager';
GRANT EXECUTE ON procedure `mydb`.`getProduct` TO 'manager';
GRANT EXECUTE ON procedure `mydb`.`getUser` TO 'manager';
GRANT EXECUTE ON procedure `mydb`.`placeOrder` TO 'manager';
GRANT EXECUTE ON procedure `mydb`.`getProducts` TO 'manager';
GRANT EXECUTE ON procedure `mydb`.`requestHire` TO 'manager';
GRANT EXECUTE ON procedure `mydb`.`getUserPassword` TO 'manager';
GRANT EXECUTE ON procedure `mydb`.`getUserFromSession` TO 'manager';
GRANT EXECUTE ON procedure `mydb`.`updateUser` TO 'manager';
GRANT EXECUTE ON procedure `mydb`.`changePassword` TO 'manager';
GRANT EXECUTE ON procedure `mydb`.`deleteSession` TO 'manager';
GRANT EXECUTE ON procedure `mydb`.`updatePrice` TO 'manager';
GRANT EXECUTE ON procedure `mydb`.`createProduct` TO 'manager';
GRANT EXECUTE ON procedure `mydb`.`updateProduct` TO 'manager';
SET SQL_MODE = '';
GRANT USAGE ON *.* TO HR;
 DROP USER HR;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'HR' IDENTIFIED BY 'hr';

GRANT EXECUTE ON procedure `mydb`.`hrHireSignOff` TO 'HR';
GRANT EXECUTE ON procedure `mydb`.`getHire` TO 'HR';
GRANT EXECUTE ON procedure `mydb`.`createSession` TO 'HR';
GRANT EXECUTE ON procedure `mydb`.`createUser` TO 'HR';
GRANT EXECUTE ON procedure `mydb`.`getProduct` TO 'HR';
GRANT EXECUTE ON procedure `mydb`.`getUser` TO 'HR';
GRANT EXECUTE ON procedure `mydb`.`placeOrder` TO 'HR';
GRANT EXECUTE ON procedure `mydb`.`getProducts` TO 'HR';
GRANT EXECUTE ON procedure `mydb`.`requestHire` TO 'HR';
GRANT EXECUTE ON procedure `mydb`.`getUserPassword` TO 'HR';
GRANT EXECUTE ON procedure `mydb`.`getUserFromSession` TO 'HR';
GRANT EXECUTE ON procedure `mydb`.`updateUser` TO 'HR';
GRANT EXECUTE ON procedure `mydb`.`changePassword` TO 'HR';
GRANT EXECUTE ON procedure `mydb`.`deleteSession` TO 'HR';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `mydb`.`roles`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`roles` (`idRole`, `name`, `description`) VALUES (1, 'manager', 'manager');
INSERT INTO `mydb`.`roles` (`idRole`, `name`, `description`) VALUES (2, 'hr', 'hr');
INSERT INTO `mydb`.`roles` (`idRole`, `name`, `description`) VALUES (3, 'employee', 'employee');

COMMIT;

USE `mydb`;

DELIMITER $$

USE `mydb`$$
DROP TRIGGER IF EXISTS `mydb`.`prices_BEFORE_INSERT` $$
USE `mydb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `mydb`.`prices_BEFORE_INSERT` BEFORE INSERT ON `prices` FOR EACH ROW
BEGIN

	IF NEW.amount <= 0 THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Amount cannot be less than or equal to zero';
	END IF;

END$$


USE `mydb`$$
DROP TRIGGER IF EXISTS `mydb`.`prices_BEFORE_UPDATE` $$
USE `mydb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `mydb`.`prices_BEFORE_UPDATE` BEFORE UPDATE ON `prices` FOR EACH ROW
BEGIN

	SIGNAL sqlstate '45000' set message_text = 'CANNOT UPDATE PRICES';

END$$


USE `mydb`$$
DROP TRIGGER IF EXISTS `mydb`.`prices_BEFORE_DELETE` $$
USE `mydb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `mydb`.`prices_BEFORE_DELETE` BEFORE DELETE ON `prices` FOR EACH ROW
BEGIN

	SIGNAL sqlstate '45000' set message_text = 'CANNOT DELETE PRICES';

END$$


USE `mydb`$$
DROP TRIGGER IF EXISTS `mydb`.`Roles_BEFORE_INSERT` $$
USE `mydb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `mydb`.`Roles_BEFORE_INSERT` BEFORE INSERT ON `Roles` FOR EACH ROW
BEGIN

	SIGNAL SQLSTATE '45000' SET message_text = 'ROLES CANNOT BE CHANGED';

END$$


USE `mydb`$$
DROP TRIGGER IF EXISTS `mydb`.`Roles_BEFORE_UPDATE` $$
USE `mydb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `mydb`.`Roles_BEFORE_UPDATE` BEFORE UPDATE ON `Roles` FOR EACH ROW
BEGIN

	SIGNAL SQLSTATE '45000' SET message_text = 'ROLES CANNOT BE CHANGED';

END$$


DELIMITER ;
