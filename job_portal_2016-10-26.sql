# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.15)
# Database: job_portal
# Generation Time: 2016-10-26 16:05:24 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table category
# ------------------------------------------------------------

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) NOT NULL,
  PRIMARY KEY (`c_id`),
  UNIQUE KEY `UK_lroeo5fvfdeg4hpicn4lw7x9b` (`category_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;

INSERT INTO `category` (`c_id`, `category_name`)
VALUES
	(4,'Administrator'),
	(1,'Developer'),
	(3,'Human Resources'),
	(2,'Tester');

/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table employee
# ------------------------------------------------------------

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `e_id` int(11) NOT NULL AUTO_INCREMENT,
  `email_id` varchar(255) NOT NULL,
  `deleted` char(1) NOT NULL DEFAULT '0',
  `name` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`e_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;

INSERT INTO `employee` (`e_id`, `email_id`, `deleted`, `name`)
VALUES
	(14,'anoop.singh@practo.com','\0','Anoop Singh'),
	(15,'abhilash.sunkam@practo.com','\0','Abhilash Sunkam'),
	(16,'vikrant.mahajan@practo.com','\0','Vikrant Mahajan'),
	(20,'sai.chandra@practo.com','\0','Sai Chandra Sekhar Dandu');

/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table hibernate_sequence
# ------------------------------------------------------------

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;

INSERT INTO `hibernate_sequence` (`next_val`)
VALUES
	(12),
	(12),
	(12),
	(12),
	(12);

/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table job
# ------------------------------------------------------------

DROP TABLE IF EXISTS `job`;

CREATE TABLE `job` (
  `j_id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `is_closed` bit(1) NOT NULL,
  `last_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `posted_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `j_c_id` int(11) NOT NULL,
  `posted_by` int(11) NOT NULL,
  `recruit_id` int(11) DEFAULT NULL,
  `deleted` char(1) NOT NULL DEFAULT '0',
  `team_id` int(11) NOT NULL,
  PRIMARY KEY (`j_id`),
  KEY `FKbj9paao6uqcnumgx5tho91tou` (`j_c_id`),
  KEY `recruit_id` (`recruit_id`),
  KEY `team_id` (`team_id`),
  KEY `posted_by` (`posted_by`),
  CONSTRAINT `FKbj9paao6uqcnumgx5tho91tou` FOREIGN KEY (`j_c_id`) REFERENCES `category` (`c_id`),
  CONSTRAINT `job_ibfk_1` FOREIGN KEY (`recruit_id`) REFERENCES `employee` (`e_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `job_ibfk_2` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`),
  CONSTRAINT `job_ibfk_3` FOREIGN KEY (`posted_by`) REFERENCES `employee` (`e_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;

INSERT INTO `job` (`j_id`, `description`, `is_closed`, `last_modified`, `posted_on`, `j_c_id`, `posted_by`, `recruit_id`, `deleted`, `team_id`)
VALUES
	(2,'Spring Boot, hibernate 5',b'0','2016-10-26 20:54:36','2016-10-26 15:21:25',1,20,NULL,'0',2),
	(4,'Spring Boot',b'0','2016-10-26 20:55:48','2016-10-26 20:37:17',1,20,NULL,'1',3),
	(6,'HTML5 CSS3',b'0','2016-10-26 21:22:36','2016-10-26 20:53:49',1,20,NULL,'1',1),
	(8,'asfsdfdvfg',b'0','2016-10-26 21:23:49','2016-10-26 21:23:13',1,20,NULL,'1',4),
	(9,'Insta Team Urgent Requirement',b'0','2016-10-26 21:25:48','2016-10-26 21:24:17',1,20,NULL,'1',2),
	(206,'J2EE Spring Update',b'0','2016-10-26 20:54:05','2016-10-25 20:30:17',2,20,NULL,'1',3),
	(207,'J2EE Spring Boot Updated',b'0','2016-10-26 20:37:02','2016-10-25 20:30:51',3,20,NULL,'0',4);

/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table job_application
# ------------------------------------------------------------

DROP TABLE IF EXISTS `job_application`;

CREATE TABLE `job_application` (
  `j_app_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `applied_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `applied_by` int(11) NOT NULL,
  `job_id` int(11) NOT NULL,
  `deleted` char(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`j_app_id`),
  KEY `job_id` (`job_id`),
  CONSTRAINT `job_application_ibfk_1` FOREIGN KEY (`job_id`) REFERENCES `job` (`j_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `job_application` WRITE;
/*!40000 ALTER TABLE `job_application` DISABLE KEYS */;

INSERT INTO `job_application` (`j_app_id`, `applied_on`, `applied_by`, `job_id`, `deleted`)
VALUES
	(5,'2016-10-26 20:41:59',15,4,'\0'),
	(7,'2016-10-26 20:55:21',15,6,'\0'),
	(10,'2016-10-26 21:24:29',15,9,'1'),
	(11,'2016-10-26 21:25:27',15,9,'\0');

/*!40000 ALTER TABLE `job_application` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `r_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) NOT NULL,
  PRIMARY KEY (`r_id`),
  UNIQUE KEY `UK_iubw515ff0ugtm28p8g3myt0h` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;

INSERT INTO `role` (`r_id`, `role_name`)
VALUES
	(1,'Admin'),
	(2,'Employee');

/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table team
# ------------------------------------------------------------

DROP TABLE IF EXISTS `team`;

CREATE TABLE `team` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;

INSERT INTO `team` (`id`, `name`)
VALUES
	(1,'Fabric'),
	(2,'Insta'),
	(3,'Querent'),
	(4,'Saphire');

/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `e_id` int(11) NOT NULL,
  `r_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `e_id` (`e_id`),
  KEY `r_id` (`r_id`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`e_id`) REFERENCES `employee` (`e_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`r_id`) REFERENCES `role` (`r_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;

INSERT INTO `user_role` (`id`, `e_id`, `r_id`)
VALUES
	(11,14,2),
	(12,15,2),
	(13,16,2),
	(16,20,1);

/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
