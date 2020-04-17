-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema pd
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema pd
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pd` ;
USE `pd` ;

-- -----------------------------------------------------
-- Table `pd`.`partner`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pd`.`partner` (
  `partner_id` INT NOT NULL,
  `partner_code` VARCHAR(45) NULL,
  `partner_name` VARCHAR(45) NULL,
  PRIMARY KEY (`partner_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pd`.`partner_restriction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pd`.`partner_restriction` (
  `partner_restriction_id` INT NOT NULL,
  `partner_restriction_value` VARCHAR(45) NULL,
  `partner_id` INT NULL,
  PRIMARY KEY (`partner_restriction_id`),
  INDEX `partnerId_idx` (`partner_id` ASC) VISIBLE,
  CONSTRAINT `partner_id`
    FOREIGN KEY (`partner_id`)
    REFERENCES `pd`.`partner` (`partner_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pd`.`restriction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pd`.`restriction` (
  `restriction_id` INT NOT NULL,
  `restriction_name` VARCHAR(45) NULL,
  PRIMARY KEY (`restriction_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pd`.`restriction_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pd`.`restriction_type` (
  `restriction_type_id` INT NOT NULL,
  `restriction_id` INT NULL,
  `restriction_type_name` VARCHAR(45) NULL,
  PRIMARY KEY (`restriction_type_id`),
  INDEX `restriction_id_idx` (`restriction_id` ASC) VISIBLE,
  CONSTRAINT `restriction_id`
    FOREIGN KEY (`restriction_id`)
    REFERENCES `pd`.`restriction` (`restriction_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
