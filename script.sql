/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 4.1.22-community-nt : Database - banco
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`banco` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `banco`;

/*Table structure for table `agencias` */

DROP TABLE IF EXISTS `agencias`;

CREATE TABLE `agencias` (
  `numero` varchar(10) NOT NULL default '',
  `nome` varchar(30) NOT NULL default '',
  `cidade` varchar(30) NOT NULL default '',
  PRIMARY KEY  (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `agencias` */

insert  into `agencias`(`numero`,`nome`,`cidade`) values ('123','AGÊNCIA 1','CASCAVEL'),('3838','AGÊNCIA CENTRO','CASCAVEL');

/*Table structure for table `contas` */

DROP TABLE IF EXISTS `contas`;

CREATE TABLE `contas` (
  `numero` varchar(20) NOT NULL default '',
  `agencia` varchar(10) NOT NULL default '',
  `tipo` int(11) default NULL,
  `nome_titular` varchar(80) NOT NULL default '',
  `idade` int(11) default NULL,
  `cpf` varchar(20) NOT NULL default '',
  `dt_abertura` date default '0000-00-00',
  `senha_acesso` varchar(64) NOT NULL default '',
  `senha_op` varchar(8) NOT NULL default '',
  `status` int(11) default NULL,
  PRIMARY KEY  (`numero`),
  KEY `Fk_ContaAgencia` (`agencia`),
  CONSTRAINT `Fk_ContaAgencia` FOREIGN KEY (`agencia`) REFERENCES `agencias` (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `contas` */

insert  into `contas`(`numero`,`agencia`,`tipo`,`nome_titular`,`idade`,`cpf`,`dt_abertura`,`senha_acesso`,`senha_op`,`status`) values ('00001-01','3838',0,'LUCAS',20,'01','2016-09-27','8ad59666b15de50943e74e00856c812a2bc2ce5c71111f0287540964c04c79ad','123456',0),('00001-02','123',1,'MATHEUS',21,'02','2016-09-28','435733d0e0a5efeb01faf4d9272f877d7878cc58f5c6cd1284ddeb3d906719a9','123456',0),('00001-03','3838',2,'WILLIAM',30,'03','2016-09-28','f475a5e1b1f7c05cd4fe7dbc297522bdb141441522313ad4062e7023e58a29e9','123456',0);

/*Table structure for table `contas_movimento` */

DROP TABLE IF EXISTS `contas_movimento`;

CREATE TABLE `contas_movimento` (
  `id` int(11) NOT NULL auto_increment,
  `conta_numero` varchar(20) default NULL,
  `tipo_movimento` char(2) default NULL,
  `data` date default NULL,
  `hora` time default NULL,
  `valor` decimal(10,2) default NULL,
  `descricao` varchar(80) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `contas_movimento` */

insert  into `contas_movimento`(`id`,`conta_numero`,`tipo_movimento`,`data`,`hora`,`valor`,`descricao`) values (72,'00001-01','D','2016-09-28','00:02:53',1000.00,'Depósito'),(73,'00001-01','S','2016-09-28','00:03:23',-50.00,'Saque'),(74,'00001-01','S','2016-09-28','00:03:39',-100.00,'Saque'),(75,'00001-01','PG','2016-09-28','00:03:59',-100.00,'Pagamento do documento 342323'),(76,'00001-01','TS','2016-09-28','00:11:51',-10.00,'Transferência para a conta 00001-02 agência 123'),(77,'00001-02','TE','2016-09-28','00:11:51',10.00,'Transferência recebida da conta 00001-01 agência 3838'),(78,'00001-03','D','2016-09-28','00:19:41',2.00,'Depósito'),(79,'00001-02','D','2016-09-28','00:20:07',200.00,'Depósito'),(82,'00001-01','S','2016-09-28','00:27:04',-50.00,'Saque');

/*Table structure for table `profissionais` */

DROP TABLE IF EXISTS `profissionais`;

CREATE TABLE `profissionais` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(30) NOT NULL default '',
  `nome` varchar(80) NOT NULL default '',
  `idade` int(11) default NULL,
  `senha_acesso` varchar(32) NOT NULL default '',
  `senha_op` varchar(8) NOT NULL default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `profissionais` */

insert  into `profissionais`(`id`,`username`,`nome`,`idade`,`senha_acesso`,`senha_op`) values (5,'LUCAS','LUCAS DA SILVA MEDEIROS',20,'e10adc3949ba59abbe56e057f20f883e','12345678'),(9,'ADMIN','ADMIN',30,'e00cf25ad42683b3df678c61f42c6bda','12345678');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
