/*
SQLyog Community Edition- MySQL GUI v6.07
Host - 5.0.11-beta-nt : Database - school
*********************************************************************
Server version : 5.0.11-beta-nt
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

create database if not exists `school`;

USE `school`;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(32) default NULL,
  `author` varchar(32) default NULL,
  `price` float default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `book` */

insert  into `book`(`id`,`name`,`author`,`price`) values (1,'水浒传','施耐庵',98),(2,'红楼梦','曹雪芹',99),(3,'三国志','罗贯中',78),(4,'西游记','吴承恩',88);

/*Table structure for table `usr` */

DROP TABLE IF EXISTS `usr`;

CREATE TABLE `usr` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(32) default NULL,
  `password` varchar(32) default NULL,
  `balance` float default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `usr` */

insert  into `usr`(`id`,`name`,`password`,`balance`) values (1,'admin','123',100),(2,'stu','123',888);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
