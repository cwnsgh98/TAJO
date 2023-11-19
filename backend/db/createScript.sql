-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema tajodb
-- -----------------------------------------------------
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`user` ;

CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `userid` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`userid`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`course`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`course` ;

CREATE TABLE IF NOT EXISTS `mydb`.`course` (
  `courseid` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `content` VARCHAR(45) NULL,
  PRIMARY KEY (`courseid`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`groupBoard`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`groupBoard` ;

CREATE TABLE IF NOT EXISTS `mydb`.`groupBoard` (
  `groupid` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `content` VARCHAR(45) NULL,
  `date` DATETIME NULL,
  `course_courseid` INT NOT NULL,
  PRIMARY KEY (`groupid`),
  INDEX `fk_groupBoard_course1_idx` (`course_courseid` ASC) VISIBLE,
  CONSTRAINT `fk_groupBoard_course1`
    FOREIGN KEY (`course_courseid`)
    REFERENCES `mydb`.`course` (`courseid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`record`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`record` ;

CREATE TABLE IF NOT EXISTS `mydb`.`record` (
  `userid` VARCHAR(45) NOT NULL,
  `distance` INT NULL,
  `time` INT NULL,
  `cal` INT NULL,
  `lastride` DATETIME NULL,
  PRIMARY KEY (`userid`),
  CONSTRAINT `fk_record_user1`
    FOREIGN KEY (`userid`)
    REFERENCES `mydb`.`user` (`userid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`courseReview`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`courseReview` ;

CREATE TABLE IF NOT EXISTS `mydb`.`courseReview` (
  `course_courseid` INT NOT NULL,
  `reviewid` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(45) NULL,
  `writer` VARCHAR(45) NULL,
  `write_date` DATETIME NULL DEFAULT now(),
  `start` INT NULL,
  INDEX `fk_courseReview_course1_idx` (`course_courseid` ASC) VISIBLE,
  PRIMARY KEY (`reviewid`),
  CONSTRAINT `fk_courseReview_course1`
    FOREIGN KEY (`course_courseid`)
    REFERENCES `mydb`.`course` (`courseid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`favorite`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`favorite` ;

CREATE TABLE IF NOT EXISTS `mydb`.`favorite` (
  `userid` VARCHAR(45) NOT NULL,
  `courseid` INT NOT NULL,
  PRIMARY KEY (`userid`, `courseid`),
  INDEX `fk_user_has_groupBoard_groupBoard1_idx` (`courseid` ASC) VISIBLE,
  INDEX `fk_user_has_groupBoard_user_idx` (`userid` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_groupBoard_user`
    FOREIGN KEY (`userid`)
    REFERENCES `mydb`.`user` (`userid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_groupBoard_groupBoard1`
    FOREIGN KEY (`courseid`)
    REFERENCES `mydb`.`course` (`courseid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`group` ;

CREATE TABLE IF NOT EXISTS `mydb`.`group` (
  `userid` VARCHAR(45) NOT NULL,
  `groupid` INT NOT NULL,
  PRIMARY KEY (`userid`, `groupid`),
  INDEX `fk_user_has_groupBoard_groupBoard2_idx` (`groupid` ASC) VISIBLE,
  INDEX `fk_user_has_groupBoard_user1_idx` (`userid` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_groupBoard_user1`
    FOREIGN KEY (`userid`)
    REFERENCES `mydb`.`user` (`userid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_groupBoard_groupBoard2`
    FOREIGN KEY (`groupid`)
    REFERENCES `mydb`.`groupBoard` (`groupid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
