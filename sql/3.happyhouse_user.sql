-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema happyhouse
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema happyhouse
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `happyhouse` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;
USE `happyhouse` ;


-- -----------------------------------------------------
-- Table `happyhouse`.`user`
-- -----------------------------------------------------
--DROP TABLE IF EXISTS `happyhouse`.`user` ;
--
--CREATE TABLE IF NOT EXISTS `happyhouse`.`user` (
--  `userno` INT NOT NULL AUTO_INCREMENT,
--  `userid` VARCHAR(45) NOT NULL,
--  PRIMARY KEY (`userno`),
--  INDEX `userid_idx` (`userid` ASC) VISIBLE)
--ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `happyhouse`.`userinfo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `happyhouse`.`userinfo` ;

CREATE TABLE IF NOT EXISTS `happyhouse`.`userinfo` (
  `id` VARCHAR(45) NOT NULL,
  `pw` VARCHAR(45) NOT NULL,
  `name` VARCHAR(30) NOT NULL,
  `email` CHAR(50) NOT NULL,
  `phone` VARCHAR(33) NOT NULL,
  PRIMARY KEY(`id`)
);
ENGINE = InnoDB;

insert into userinfo(id, pw, name, email, phone) values('admin', 'admin', '관리자', 'admin@ssafy.com', '010-0000-0000');

select * from userinfo;

-- -----------------------------------------------------
-- Table `happyhouse`.`interestregion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `happyhouse`.`interestregion` ;


CREATE TABLE IF NOT EXISTS `happyhouse`.`interestregion` (
  `id` VARCHAR(45) NOT NULL,
  `dongcode` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`, `dongcode`),
  INDEX `id_idx` (`id` ASC) VISIBLE,
  INDEX `dongcode_idx` (`dongcode` ASC) VISIBLE,
  CONSTRAINT
    FOREIGN KEY (`dongcode`)
    REFERENCES `happyhouse`.`baseaddress` (`dongCode`) ON DELETE CASCADE, --동코드가 사라지면 기존에 그 지역을 관심지역으로 해 놓은 사람의 찜목록도 사라지도록
  CONSTRAINT
    FOREIGN KEY (`id`)
    REFERENCES `happyhouse`.`userinfo` (`id`)  ON DELETE CASCADE ); --회원 탈퇴하면 그 회원의 찜 목록도 사라지도록
ENGINE = InnoDB
AUTO_INCREMENT = 3597
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
