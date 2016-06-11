﻿SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `agendaFacil` ;
CREATE SCHEMA IF NOT EXISTS `agendaFacil` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `agendaFacil` ;

-- -----------------------------------------------------
-- Table `agendaFacil`.`TIPO_SERVICO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `agendaFacil`.`TIPO_SERVICO` ;

CREATE TABLE IF NOT EXISTS `agendaFacil`.`TIPO_SERVICO` (
  `TSE_PK_ID` INT NOT NULL AUTO_INCREMENT,
  `TSE_DESCRICAO` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`TSE_PK_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `agendaFacil`.`CIDADE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `agendaFacil`.`CIDADE` ;

CREATE TABLE IF NOT EXISTS `agendaFacil`.`CIDADE` (
  `CID_PK_ID` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`CID_PK_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `agendaFacil`.`ENDERECO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `agendaFacil`.`ENDERECO` ;

CREATE TABLE IF NOT EXISTS `agendaFacil`.`ENDERECO` (
  `END_PK_ID` INT NOT NULL AUTO_INCREMENT,
  `END_DESCRICAO` VARCHAR(100) NOT NULL,
  `CID_FK_ID` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`END_PK_ID`),
  CONSTRAINT `fk_ENDERECO_CIDADE1`
    FOREIGN KEY (`CID_FK_ID`)
    REFERENCES `agendaFacil`.`CIDADE` (`CID_PK_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_ENDERECO_CIDADE1_idx` ON `agendaFacil`.`ENDERECO` (`CID_FK_ID` ASC);


-- -----------------------------------------------------
-- Table `agendaFacil`.`PESSOA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `agendaFacil`.`PESSOA` ;

CREATE TABLE IF NOT EXISTS `agendaFacil`.`PESSOA` (
  `PES_PK_ID` INT NOT NULL AUTO_INCREMENT,
  `PES_TIPO` VARCHAR(45) NOT NULL,
  `PES_NOME` VARCHAR(45) NOT NULL,
  `PES_SEXO` VARCHAR(1) NOT NULL,
  `PES_DATA_NASC` DATE NOT NULL,
  `PES_CPF` INT(11) NOT NULL,
  `PES_CNPJ` INT(14) NOT NULL,
  `PES_RG` INT NOT NULL,
  `PES_RAZAO_SOCIAL` VARCHAR(100) NOT NULL,
  `PES_FUNCAO` VARCHAR(45) NOT NULL,
  `END_FK_ID` INT NOT NULL,
  PRIMARY KEY (`PES_PK_ID`),
  CONSTRAINT `fk_PESSOA_ENDERECO1`
    FOREIGN KEY (`END_FK_ID`)
    REFERENCES `agendaFacil`.`ENDERECO` (`END_PK_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_PESSOA_ENDERECO1_idx` ON `agendaFacil`.`PESSOA` (`END_FK_ID` ASC);


-- -----------------------------------------------------
-- Table `agendaFacil`.`SERVIÇO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `agendaFacil`.`SERVIÇO` ;

CREATE TABLE IF NOT EXISTS `agendaFacil`.`SERVIÇO` (
  `SER_SER_ID` INT NOT NULL AUTO_INCREMENT,
  `SER_PRO_NOME` VARCHAR(100) NOT NULL,
  `SER_CLI_NOME` VARCHAR(100) NULL,
  `SER_DATA` DATE NULL,
  `SER_HORARIO` TIME NULL,
  `SER_DESC` INT NOT NULL,
  `TIS_FK_TSE_IDTSE` INT NOT NULL,
  `PES_FK_ID_PROFISSIONAL` INT NOT NULL,
  `PES_FK_ID_CLIENTE` INT NOT NULL,
  PRIMARY KEY (`SER_SER_ID`),
  CONSTRAINT `fk_SERVIÇO_TIPO_SERVICO1`
    FOREIGN KEY (`TES_FK_TSE_IDTSE`)
    REFERENCES `agendaFacil`.`TIPO_SERVICO` (`TSE_PK_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SERVIÇO_PESSOA1`
    FOREIGN KEY (`PES_FK_ID_PROFISSIONAL`)
    REFERENCES `agendaFacil`.`PESSOA` (`PES_PK_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SERVIÇO_PESSOA2`
    FOREIGN KEY (`PES_FK_ID_CLIENTE`)
    REFERENCES `agendaFacil`.`PESSOA` (`PES_PK_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_SERVIÇO_TIPO_SERVICO1_idx` ON `agendaFacil`.`SERVIÇO` (`TES_FK_TSE_IDTSE` ASC);

CREATE INDEX `fk_SERVIÇO_PESSOA1_idx` ON `agendaFacil`.`SERVIÇO` (`PES_FK_ID_PROFISSIONAL` ASC);

CREATE INDEX `fk_SERVIÇO_PESSOA2_idx` ON `agendaFacil`.`SERVIÇO` (`PES_FK_ID_CLIENTE` ASC);


-- -----------------------------------------------------
-- Table `agendaFacil`.`TELEFONE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `agendaFacil`.`TELEFONE` ;

CREATE TABLE IF NOT EXISTS `agendaFacil`.`TELEFONE` (
  `TEL_PK_ID` INT NOT NULL,
  `TEL_TIPO` INT(2) NOT NULL,
  `TEL_NUMERO` INT(11) NULL,
  `PES_FK_ID` INT NOT NULL,
  PRIMARY KEY (`TEL_PK_ID`),
  CONSTRAINT `fk_TELEFONE_PESSOA1`
    FOREIGN KEY (`PES_FK_ID`)
    REFERENCES `agendaFacil`.`PESSOA` (`PES_PK_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_TELEFONE_PESSOA1_idx` ON `agendaFacil`.`TELEFONE` (`PES_FK_ID` ASC);


-- -----------------------------------------------------
-- Table `agendaFacil`.`ESTADO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `agendaFacil`.`ESTADO` ;

CREATE TABLE IF NOT EXISTS `agendaFacil`.`ESTADO` (
  `EST_PK_ID` VARCHAR(2) NOT NULL,
  `CID_FK_ID` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`EST_PK_ID`),
  CONSTRAINT `fk_ESTADO_CIDADE1`
    FOREIGN KEY (`CID_FK_ID`)
    REFERENCES `agendaFacil`.`CIDADE` (`CID_PK_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_ESTADO_CIDADE1_idx` ON `agendaFacil`.`ESTADO` (`CID_FK_ID` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
