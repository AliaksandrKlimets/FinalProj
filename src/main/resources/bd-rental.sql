SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema car_rental
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema car_rental
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `car_rental` DEFAULT CHARACTER SET utf8 ;
USE `car_rental` ;

-- -----------------------------------------------------
-- Table `car_rental`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `car_rental`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT COMMENT 'Уникальный идентификатор пользователя',
  `login` VARCHAR(20) NOT NULL COMMENT 'Логин пользователя',
  `password` VARCHAR(30) NOT NULL COMMENT 'Пароль пользователя, который будет хешироваться.',
  `role` ENUM('admin', 'user') NOT NULL COMMENT 'Роль пользователя. Так как роли в моей системе всего две - я выбрал тип данных перечисление. Роль user задается при регистрации нового пользователя, тогда же заполняется информация о пользователе, которая хранится в таблице users_id. Регистрация администратора может производитья только внутри системы, логин и пароль высылаются работнику на почту.',
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC))
ENGINE = InnoDB
COMMENT = 'Таблица содержит информацию о логие, пароле и роли пользователя. Если роль пользователя администратор, то его зарегистрировали внутри системы и выдали пароль и логин лично.';


-- -----------------------------------------------------
-- Table `car_rental`.`user_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `car_rental`.`user_info` (
  `user_id` INT NOT NULL AUTO_INCREMENT COMMENT 'Данное поле является первичным ключем в своей таблице, а так же FK поля user_id из таблицы users. По данному полю будет производиться связь 1 к 1 с таблицей users.',
  `name` VARCHAR(15) NOT NULL COMMENT 'Имя пользователя. Задается при регистрации.',
  `surname` VARCHAR(20) NOT NULL COMMENT 'Фамилия пользователя. Задается при регистрации.',
  `email` VARCHAR(40) NOT NULL COMMENT 'E-mail пользователя. Задается при регистрации.',
  `phone` VARCHAR(15) NOT NULL COMMENT 'Телефон пользователя. Задается при регистрации.',
  `birth_date` DATE NULL COMMENT 'По желанию при регистрации пользователь может задать дату рождения.',
  `registration_date` DATE NOT NULL COMMENT 'Дата регистрации. Задается системой в момент создания аккаунта.',
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_users_info_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `car_rental`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Дополнительная информация о пользователях.';


-- -----------------------------------------------------
-- Table `car_rental`.`car`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `car_rental`.`car` (
  `car_id` INT NOT NULL AUTO_INCREMENT COMMENT 'Уникальный идентификатор машины.',
  `car_model` VARCHAR(45) NOT NULL COMMENT 'Модель и марка машины.',
  `year_of_issue` VARCHAR(4) NOT NULL COMMENT 'Год выпуска машины.',
  `consumption` VARCHAR(45) NOT NULL COMMENT 'Расход топлива машины.',
  `engine_capacity` DOUBLE NOT NULL COMMENT 'Объем двигателя.',
  `car_type` ENUM('COUPE', 'HATCHBACK', 'UNIVERSAL', 'PICKUP', 'CROSSOVER') NOT NULL,
  `transmission` VARCHAR(45) NOT NULL,
  `fuel_type` ENUM('PETROL', 'DIESEL', 'GAS') NOT NULL,
  `image` VARCHAR(100) NOT NULL,
  `add_info` TEXT NOT NULL,
  PRIMARY KEY (`car_id`),
  UNIQUE INDEX `car_model_UNIQUE` (`car_model` ASC))
ENGINE = InnoDB
COMMENT = 'Таблица содержащая информацию об авто.';


-- -----------------------------------------------------
-- Table `car_rental`.`service_cost`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `car_rental`.`service_cost` (
  `car_id` INT NOT NULL,
  `cost_per_day` DOUBLE NOT NULL COMMENT 'стоимость услуги с выбранным авто за час.',
  `two_to_seven_days` DOUBLE NOT NULL COMMENT 'Стоимость услуги с данным авто в промежуток 1-7 дней.',
  `eight_to_fifteen_days` DOUBLE NOT NULL COMMENT 'Стоимость услуги в промежуток 8-15 дней.',
  `sixteen_and_more` DOUBLE NOT NULL COMMENT 'Стоимость услуги с данным авто при заказе более чем 16 дней.',
  INDEX `fk_service_cost_car1_idx` (`car_id` ASC),
  CONSTRAINT `fk_service_cost_car1`
    FOREIGN KEY (`car_id`)
    REFERENCES `car_rental`.`car` (`car_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Таблица, хранящая информацию об услугах. Дабы не дублировать записи в таблице orders первичный ключ тут синтетический. Информация об услугах представлена в виде наименования услуги, машины, которая идет с данной услугой, а так же информация о ценах в разные временные промежутки.';


-- -----------------------------------------------------
-- Table `car_rental`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `car_rental`.`order` (
  `order_id` INT NOT NULL AUTO_INCREMENT COMMENT 'Идентификатор заказа.',
  `user_id` INT NULL COMMENT 'Идентификатор пользователя, сделавшего заказ.',
  `car_id` INT NULL COMMENT 'Идентификатор услуги, через который можно получить информацию об автомобиле и наименовании услуги.',
  `passport_number` VARCHAR(10) NOT NULL COMMENT 'Номер паспорта.',
  `identification_number` VARCHAR(20) NOT NULL COMMENT 'Идентификационный номер паспорта.',
  `date_of_expiry` DATE NOT NULL COMMENT 'Срок годноти паспорта.',
  `service_start` DATE NOT NULL COMMENT 'Начало действия услуги.',
  `service_end` DATE NOT NULL COMMENT 'Окончание действия услуги.',
  `service_cost` DOUBLE NOT NULL COMMENT 'Стоимость заказа.',
  `order_state` ENUM('WAITING', 'ACCEPT', 'DECLINE') NOT NULL COMMENT 'Состояние заказа. Администратор может и отказать.',
  `decline_reason` TEXT NOT NULL COMMENT 'Если он это сделал (отказал), он указывает причину, по которой он это сделал.',
  `payment_state` ENUM('UNPAID', 'PAID') NOT NULL COMMENT 'Статус оплаты.',
  PRIMARY KEY (`order_id`),
  INDEX `fk_orders_users1_idx` (`user_id` ASC),
  INDEX `fk_order_car1_idx` (`car_id` ASC),
  CONSTRAINT `fk_orders_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `car_rental`.`user` (`user_id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_order_car1`
    FOREIGN KEY (`car_id`)
    REFERENCES `car_rental`.`car` (`car_id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Таблица, храняая информацию о заказах. Польователь оставляет заявку за оказание услуги, указывает свои паспортные данные, указывает период оказания услуги, затем администратор либо одобряет заявку, либо отклоняет ее. Затем клиент получает счет, оформляется договор, он платит, все счастливы.';


-- -----------------------------------------------------
-- Table `car_rental`.`fine`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `car_rental`.`fine` (
  `fine_id` INT NOT NULL AUTO_INCREMENT COMMENT 'Идентификатор счета за порчу.',
  `user_id` INT NULL COMMENT 'Идентификатор пользователя, который сделал плохо.',
  `car_id` INT NULL COMMENT 'Идетификатор машинки, которая пострадала :(',
  `cause` TEXT NOT NULL COMMENT 'Причина, по которой выставлен счет.',
  `repair_bill` DOUBLE NOT NULL COMMENT 'Счет, который выставляет администратор.',
  `payment_state` ENUM('UNPAID', 'PAID') NOT NULL COMMENT 'Состояние оплаты.',
  `due_date` DATE NOT NULL COMMENT 'Дедлайн оплаты, после него цена растет.',
  PRIMARY KEY (`fine_id`, `user_id`),
  INDEX `fk_fines_users1_idx` (`user_id` ASC),
  INDEX `fk_fines_cars2_idx` (`car_id` ASC),
  CONSTRAINT `fk_fines_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `car_rental`.`user` (`user_id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_fines_cars2`
    FOREIGN KEY (`car_id`)
    REFERENCES `car_rental`.`car` (`car_id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Таблица, хранящая информацию о штрафах, выписанных пользователям, в связи с порчей авто или чем-то подобным. Хранится информация об обидчике(пользователь) и о том, кого обидели (авто). Так же указывается причина, выставляется счет, дата, до которой надо оплатить, а так же есть статус оплаты.';


-- -----------------------------------------------------
-- Table `car_rental`.`ordered_car`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `car_rental`.`ordered_car` (
  `car_order_id` INT NOT NULL AUTO_INCREMENT,
  `car_id` INT NOT NULL,
  `user_id` INT NULL,
  `begin_date` DATETIME NOT NULL,
  `end_date` DATETIME NOT NULL,
  PRIMARY KEY (`car_order_id`),
  INDEX `fk_ordered_car_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_ordered_car_car1`
    FOREIGN KEY (`car_id`)
    REFERENCES `car_rental`.`car` (`car_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ordered_car_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `car_rental`.`user` (`user_id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
