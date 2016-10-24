# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.15)
# Database: job_portal
# Generation Time: 2016-10-24 09:58:43 +0000
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

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
  PRIMARY KEY (`e_id`),
  UNIQUE KEY `UK_af534w03av8srcldugewrmpbi` (`email_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;

INSERT INTO `employee` (`e_id`, `email_id`, `deleted`, `name`)
VALUES
	(12,'sai.chandra@practo.com','0','Sai Chandra Sekhar D'),
	(14,'anoop.singh@practo.com','\0','Anoop Singh'),
	(15,'abhilash.sunkam@practo.com','\0','Abhilash Sunkam');

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
	(1),
	(1),
	(1),
	(1),
	(1);

/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table job
# ------------------------------------------------------------

DROP TABLE IF EXISTS `job`;

CREATE TABLE `job` (
  `j_id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `is_closed` bit(1) NOT NULL,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `posted_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `j_c_id` int(11) NOT NULL,
  `posted_by` int(11) NOT NULL,
  `recruit_id` int(11) DEFAULT NULL,
  `deleted` char(1) NOT NULL DEFAULT '0',
  `team_id` int(11) NOT NULL,
  PRIMARY KEY (`j_id`),
  KEY `FKbj9paao6uqcnumgx5tho91tou` (`j_c_id`),
  KEY `FKje89f52om3i8fm69e2iin6hsn` (`posted_by`),
  KEY `recruit_id` (`recruit_id`),
  KEY `team_id` (`team_id`),
  CONSTRAINT `FKbj9paao6uqcnumgx5tho91tou` FOREIGN KEY (`j_c_id`) REFERENCES `category` (`c_id`),
  CONSTRAINT `FKje89f52om3i8fm69e2iin6hsn` FOREIGN KEY (`posted_by`) REFERENCES `employee` (`e_id`),
  CONSTRAINT `job_ibfk_1` FOREIGN KEY (`recruit_id`) REFERENCES `employee` (`e_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `job_ibfk_2` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=latin1;

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;

INSERT INTO `job` (`j_id`, `description`, `is_closed`, `last_modified`, `posted_on`, `j_c_id`, `posted_by`, `recruit_id`, `deleted`, `team_id`)
VALUES
	(186,'Relatively fresher required for Java J2EE, Spring Boot',b'0','2016-10-23 14:56:34','2016-10-20 11:02:46',1,12,NULL,'0',1),
	(187,'Java, Spring, Hibernate. Experience 3 years',b'0','2016-10-23 11:04:51','2016-10-20 20:02:22',1,12,NULL,'0',2),
	(188,'Automation testing updated',b'0','2016-10-23 11:03:59','2016-10-20 20:08:06',2,12,NULL,'0',1),
	(189,'Evadokadu',b'0','2016-10-23 14:56:35','2016-10-21 10:27:57',1,12,NULL,'0',2),
	(190,'Angular JS, CSS 3, HTML 5',b'0','2016-10-23 10:59:51','2016-10-21 16:08:19',1,12,NULL,'0',4),
	(194,'Sample',b'0','2016-10-22 16:10:56','2016-10-21 17:15:31',2,12,NULL,'0',1),
	(197,'New Java based team. analytics and reports',b'0','2016-10-23 11:01:38','2016-10-23 10:52:20',1,12,NULL,'0',3),
	(198,'Html 5 and CSS3 proficient developer',b'0','2016-10-23 11:07:16','2016-10-23 11:07:16',1,12,NULL,'\0',1),
	(199,'Automation testing and manual testing',b'0','2016-10-24 13:11:04','2016-10-24 13:11:04',2,12,NULL,'\0',4);

/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table job_application
# ------------------------------------------------------------

DROP TABLE IF EXISTS `job_application`;

CREATE TABLE `job_application` (
  `j_app_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `applied_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `applied_by` int(11) NOT NULL,
  `job_id` int(11) NOT NULL,
  `deleted` char(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`j_app_id`),
  KEY `FKdepcvxeq3gyb4438ws0qjycc7` (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=latin1;

LOCK TABLES `job_application` WRITE;
/*!40000 ALTER TABLE `job_application` DISABLE KEYS */;

INSERT INTO `job_application` (`j_app_id`, `applied_on`, `applied_by`, `job_id`, `deleted`)
VALUES
	(28,'2016-10-20 20:30:26',14,188,'0'),
	(30,'2016-10-20 20:51:02',14,186,'0'),
	(31,'2016-10-22 14:16:25',14,189,'0'),
	(41,'2016-10-22 14:48:23',12,186,'1'),
	(42,'2016-10-22 15:14:10',12,194,'1'),
	(44,'2016-10-22 15:19:11',12,186,'1'),
	(45,'2016-10-22 15:19:12',12,188,'1'),
	(46,'2016-10-22 15:19:13',12,187,'1'),
	(47,'2016-10-22 15:19:14',12,189,'1'),
	(48,'2016-10-22 15:19:14',12,190,'1'),
	(49,'2016-10-22 15:19:15',12,194,'1'),
	(50,'2016-10-22 15:19:23',12,194,'1'),
	(51,'2016-10-22 15:20:19',12,186,'1'),
	(52,'2016-10-22 15:20:19',12,187,'1'),
	(53,'2016-10-22 15:20:20',12,188,'1'),
	(54,'2016-10-22 15:20:20',12,189,'1'),
	(55,'2016-10-22 15:20:20',12,190,'1'),
	(56,'2016-10-22 15:20:21',12,194,'1'),
	(57,'2016-10-22 15:20:30',12,186,'1'),
	(58,'2016-10-22 15:20:49',12,186,'1'),
	(59,'2016-10-22 15:20:52',12,187,'1'),
	(60,'2016-10-22 15:20:55',12,188,'1'),
	(61,'2016-10-22 15:21:33',12,189,'1'),
	(62,'2016-10-22 15:21:35',12,190,'1'),
	(63,'2016-10-22 15:21:37',12,194,'1'),
	(64,'2016-10-22 15:30:37',12,186,'1'),
	(65,'2016-10-22 15:30:40',12,187,'1'),
	(66,'2016-10-22 15:30:42',12,188,'1'),
	(67,'2016-10-22 15:30:43',12,189,'1'),
	(68,'2016-10-22 15:30:45',12,190,'1'),
	(69,'2016-10-22 15:30:47',12,194,'1'),
	(70,'2016-10-22 15:31:17',12,186,'1'),
	(71,'2016-10-22 15:32:11',12,186,'1'),
	(72,'2016-10-22 15:32:13',12,187,'1'),
	(73,'2016-10-22 15:32:14',12,188,'1'),
	(74,'2016-10-22 15:32:16',12,189,'1'),
	(75,'2016-10-22 15:32:18',12,190,'1'),
	(76,'2016-10-22 15:32:19',12,194,'1'),
	(77,'2016-10-23 11:10:50',12,190,'1'),
	(78,'2016-10-23 11:10:56',12,188,'1'),
	(79,'2016-10-23 11:11:00',12,187,'1'),
	(80,'2016-10-23 11:34:44',12,198,'1'),
	(81,'2016-10-23 11:39:50',12,197,'1'),
	(82,'2016-10-23 11:47:53',12,194,'1'),
	(83,'2016-10-23 11:49:27',12,190,'1'),
	(84,'2016-10-23 12:12:56',12,188,'1'),
	(85,'2016-10-23 12:18:35',12,198,'1'),
	(86,'2016-10-24 12:02:05',15,198,'\0');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;

INSERT INTO `user_role` (`id`, `e_id`, `r_id`)
VALUES
	(9,12,1),
	(11,14,2),
	(12,15,2);

/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
