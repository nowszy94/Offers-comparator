create database offers_comparatorDB;
use offers_comparatorDB;

CREATE TABLE `users` (
                         `user_id` INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
                         `login` VARCHAR(50) NOT NULL UNIQUE,
                         `password` VARCHAR(250) NOT NULL,
                         `email` VARCHAR(250) NOT NULL UNIQUE,
                         `token` VARCHAR(50),
                         `active` BOOLEAN NOT NULL DEFAULT FLASE,
                         `points` INT NOT NULL DEFAULT 0,
                         `last_update` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `role` VARCHAR(50) NOT NULL DEFAULT 'USER'
);

CREATE INDEX users_login
ON users (login, password,email);


CREATE TABLE logger(
`log_id` INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
`date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
`description` TEXT,
 `user_id` INT UNSIGNED NOT NULL,
 `source` VARCHAR(50) NOT NULL,
 `type` VARCHAR(20) NOT NULL,
 FOREIGN KEY (`user_id`) REFERENCES users (`user_id`)
);


/*
todo problems with remotemysql service
delimiter $$
CREATE
    TRIGGER  user_add_trg
    AFTER INSERT ON users FOR EACH ROW
    BEGIN
        INSERT INTO logger (`description`, `user_id`, `source`,`type`)
        VALUES (CONCAT('New user has been added: ', new.login), new.user_id,'users','INFO');
    END$$
delimiter ;


delimiter $$
CREATE
    TRIGGER  user_updt_trg
    AFTER UPDATE ON users FOR EACH ROW
    BEGIN
        IF OLD.login <> NEW.login THEN
            INSERT INTO logger (`description`, `user_id`, `source`,`type`)
            VALUES (CONCAT('User login has been updated. Before: ', old.login,' After: ', new.login), old.user_id,'users','INFO');
        END IF;
        IF OLD.password <> NEW.password THEN
            INSERT INTO logger (`description`, `user_id`, `source`,`type`)
            VALUES ('User password has been updated.', old.user_id,'users','INFO');
        END IF;
        IF OLD.email <> NEW.email THEN
            INSERT INTO logger (`description`, `user_id`, `source`,`type`)
            VALUES (CONCAT('User email has been updated. Before: ', old.email,' After: ', new.email), old.user_id,'users','INFO');
        END IF;
        IF OLD.active <> NEW.active THEN
            INSERT INTO logger (`description`, `user_id`, `source`,`type`)
            VALUES (CONCAT('User status has been updated. Before: ', old.active,' After: ', new.active), old.user_id,'users','INFO');
        END IF;
    END$$
delimiter ;


delimiter $$
CREATE
    TRIGGER  user_dlt_trg
    BEFORE DELETE ON users FOR EACH ROW
    BEGIN
        INSERT INTO logger (`description`, `user_id`, `source`,`type`)
        VALUES (CONCAT('Recipe has been deleted: ', old.login),old.user_id,'users','INFO');
    END$$
delimiter ;

delimiter $$
CREATE
    TRIGGER  logger_capacity_limit
    AFTER INSERT ON logger FOR EACH ROW
    BEGIN
        call logger_capacity_limit();
    end$$
delimiter ;


delimiter $$

CREATE PROCEDURE logger_capacity_limit ()
    BEGIN
 	    IF (SELECT COUNT (*) FROM logger WHERE `date` <=(CURRENT_DATE()- INTERVAL 1 YEAR)>=1) THEN
        DELETE FROM logger WHERE `date`<=(CURRENT_DATE()- INTERVAL 1 YEAR);
        END IF;
    END$$
delimiter ;*/


