-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema tajodb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema tajodb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tajodb` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema tajodb
-- -----------------------------------------------------
USE `tajodb` ;

-- -----------------------------------------------------
-- Table `tajodb`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tajodb`.`user` ;

CREATE TABLE IF NOT EXISTS `tajodb`.`user` (
  `userid` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NULL,
  `nickname` VARCHAR(45) NULL,
  PRIMARY KEY (`userid`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tajodb`.`course`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tajodb`.`course` ;

CREATE TABLE IF NOT EXISTS `tajodb`.`course` (
  `courseid` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `from` VARCHAR(45) NULL,
  `to` VARCHAR(45) NULL,
  `location` VARCHAR(45) NULL,
  `img` VARCHAR(45) NULL,
  `time` VARCHAR(45),
  `distance` INT,
  `viewCnt` int default 0,
  
  PRIMARY KEY (`courseid`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tajodb`.`groupBoard`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tajodb`.`groupBoard` ;

CREATE TABLE IF NOT EXISTS `tajodb`.`groupBoard` (
  `groupid` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `content` VARCHAR(45) NULL,
  `date` DATETIME NULL,
  `course_courseid` INT NOT NULL,
  PRIMARY KEY (`groupid`),
  INDEX `fk_groupBoard_course1_idx` (`course_courseid` ASC) VISIBLE,
  CONSTRAINT `fk_groupBoard_course1`
    FOREIGN KEY (`course_courseid`)
    REFERENCES `tajodb`.`course` (`courseid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tajodb`.`record`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tajodb`.`record` ;

CREATE TABLE IF NOT EXISTS `tajodb`.`record` (
  `userid` VARCHAR(45) NOT NULL,
  `distance` INT default 0,
  `ridedate` DATETIME NULL,
  CONSTRAINT `fk_record_user1`
    FOREIGN KEY (`userid`)
    REFERENCES `tajodb`.`user` (`userid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tajodb`.`courseReview`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tajodb`.`courseReview` ;

CREATE TABLE IF NOT EXISTS `tajodb`.`courseReview` (
  `courseid` INT NOT NULL,
  `reviewid` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(100) NULL,
  `writer` VARCHAR(100) NULL,
  `star` INT NULL,
  INDEX `fk_courseReview_course1_idx` (`courseid` ASC) VISIBLE,
  PRIMARY KEY (`reviewid`),
  CONSTRAINT `fk_courseReview_course1`
    FOREIGN KEY (`courseid`)
    REFERENCES `tajodb`.`course` (`courseid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tajodb`.`favorite`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tajodb`.`favorite` ;

CREATE TABLE IF NOT EXISTS `tajodb`.`favorite` (
  `userid` VARCHAR(45) NOT NULL,
  `courseid` INT NOT NULL,
  PRIMARY KEY (`userid`, `courseid`),
  INDEX `fk_user_has_groupBoard_groupBoard1_idx` (`courseid` ASC) VISIBLE,
  INDEX `fk_user_has_groupBoard_user_idx` (`userid` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_groupBoard_user`
    FOREIGN KEY (`userid`)
    REFERENCES `tajodb`.`user` (`userid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_groupBoard_groupBoard1`
    FOREIGN KEY (`courseid`)
    REFERENCES `tajodb`.`course` (`courseid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tajodb`.`group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tajodb`.`group` ;

CREATE TABLE IF NOT EXISTS `tajodb`.`group` (
  `userid` VARCHAR(45) NOT NULL,
  `groupid` INT NOT NULL,
  PRIMARY KEY (`userid`, `groupid`),
  INDEX `fk_user_has_groupBoard_groupBoard2_idx` (`groupid` ASC) VISIBLE,
  INDEX `fk_user_has_groupBoard_user1_idx` (`userid` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_groupBoard_user1`
    FOREIGN KEY (`userid`)
    REFERENCES `tajodb`.`user` (`userid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_groupBoard_groupBoard2`
    FOREIGN KEY (`groupid`)
    REFERENCES `tajodb`.`groupBoard` (`groupid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
