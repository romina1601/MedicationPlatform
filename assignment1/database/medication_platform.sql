-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: medication_platform
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `caregiver`
--

DROP TABLE IF EXISTS `caregiver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `caregiver` (
  `caregiver_id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `birth_date` varchar(45) NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`caregiver_id`),
  KEY `FK5mwwj8918mq4jc4nrgxfkk3rc` (`username`),
  CONSTRAINT `FK5mwwj8918mq4jc4nrgxfkk3rc` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caregiver`
--

LOCK TABLES `caregiver` WRITE;
/*!40000 ALTER TABLE `caregiver` DISABLE KEYS */;
INSERT INTO `caregiver` VALUES (1,'Str. caregiver test 1.1','24-12-1989','M','Caregiver 1','caregiver1'),(2,'Str caregiver 2','21-12-1987','M','Caregiver 2','caregiver2'),(3,'Str. caregiver 3','30-10-1987','M','Caregiver 3','caregiver3'),(4,'Str. Caregiver 4','24-09-1963','F','Caregiver 4','caregiver4'),(5,'Str. Caregiver nr.5','12-03-1956','M','Caregiver 5','caregiver5');
/*!40000 ALTER TABLE `caregiver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `doctor` (
  `doctor_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `specialization` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`doctor_id`),
  KEY `FKlf997jtlk4y1dbg8t9a84mlvf` (`username`),
  CONSTRAINT `FKlf997jtlk4y1dbg8t9a84mlvf` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES (1,'Doctor 1','ORL','doctor1'),(2,'Doctor 3','ORL','doctor3'),(3,'Doctor 4','Cardio','doctor4');
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grpc_medication`
--

DROP TABLE IF EXISTS `grpc_medication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `grpc_medication` (
  `grpc_medication_id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`grpc_medication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grpc_medication`
--

LOCK TABLES `grpc_medication` WRITE;
/*!40000 ALTER TABLE `grpc_medication` DISABLE KEYS */;
INSERT INTO `grpc_medication` VALUES (1,'Augmentin',6,'TAKEN'),(7,'Nurofen',6,'NOT TAKEN'),(8,'Brufen',6,'TAKEN'),(9,'Augmentin',5,'TAKEN');
/*!40000 ALTER TABLE `grpc_medication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medication`
--

DROP TABLE IF EXISTS `medication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `medication` (
  `medication_id` int(11) NOT NULL AUTO_INCREMENT,
  `dosage` varchar(255) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `side_effects` varchar(200) NOT NULL,
  `intake_interval` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`medication_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medication`
--

LOCK TABLES `medication` WRITE;
/*!40000 ALTER TABLE `medication` DISABLE KEYS */;
INSERT INTO `medication` VALUES (1,'max. 2/day','Augmentin','Headaches','08:00-10:30'),(7,'max 5/day','Nurofen','Nausea','09:00-09:43'),(8,'max. 5/day','Brufen','Sleepiness','09:05-10:30');
/*!40000 ALTER TABLE `medication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medication_plan`
--

DROP TABLE IF EXISTS `medication_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `medication_plan` (
  `medication_plan_id` int(11) NOT NULL AUTO_INCREMENT,
  `end_date` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `start_date` varchar(255) DEFAULT NULL,
  `patient_id` int(11) NOT NULL,
  PRIMARY KEY (`medication_plan_id`),
  KEY `FKsbssmqwap7gwimwcnwmolheef` (`patient_id`),
  CONSTRAINT `FKsbssmqwap7gwimwcnwmolheef` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medication_plan`
--

LOCK TABLES `medication_plan` WRITE;
/*!40000 ALTER TABLE `medication_plan` DISABLE KEYS */;
INSERT INTO `medication_plan` VALUES (1,'25-12-2019','Medplan1','24-12-2019',6),(3,'31-12-2019','Med plan 3','30-12-2019',6),(5,'31-12-2019','Med Plan 4','12-03-2019',6),(7,'26-12-2019','Med Plan1','25-12-2019',14),(8,'29-12-2019','Headache treatment','27-12-2019',6);
/*!40000 ALTER TABLE `medication_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medication_plan_medication`
--

DROP TABLE IF EXISTS `medication_plan_medication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `medication_plan_medication` (
  `medication_plan_id` int(11) NOT NULL,
  `medication_id` int(11) NOT NULL,
  KEY `FKlhl5cqrr88k97wt4yqh2ilwd3` (`medication_id`),
  KEY `FKefo7csbflemynrnmfcx9salmp` (`medication_plan_id`),
  CONSTRAINT `FKefo7csbflemynrnmfcx9salmp` FOREIGN KEY (`medication_plan_id`) REFERENCES `medication_plan` (`medication_plan_id`),
  CONSTRAINT `FKlhl5cqrr88k97wt4yqh2ilwd3` FOREIGN KEY (`medication_id`) REFERENCES `medication` (`medication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medication_plan_medication`
--

LOCK TABLES `medication_plan_medication` WRITE;
/*!40000 ALTER TABLE `medication_plan_medication` DISABLE KEYS */;
INSERT INTO `medication_plan_medication` VALUES (3,7),(3,8),(1,1);
/*!40000 ALTER TABLE `medication_plan_medication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monitored_data`
--

DROP TABLE IF EXISTS `monitored_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `monitored_data` (
  `monitored_data_id` int(11) NOT NULL AUTO_INCREMENT,
  `activity` varchar(255) DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `behavior` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`monitored_data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9072 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monitored_data`
--

LOCK TABLES `monitored_data` WRITE;
/*!40000 ALTER TABLE `monitored_data` DISABLE KEYS */;
INSERT INTO `monitored_data` VALUES (8841,'Toileting','2011-11-28 08:23:36',4,'2011-11-28 08:21:24','NORMAL'),(8842,'Showering','2011-11-28 09:33:00',4,'2011-11-28 08:25:44','NOT NORMAL'),(8843,'Breakfast','2011-11-28 08:43:00',6,'2011-11-28 08:34:23','NORMAL'),(8844,'Grooming','2011-11-28 08:51:13',6,'2011-11-28 08:49:48','NORMAL'),(8845,'Toileting','2011-11-28 12:07:31',6,'2011-11-28 11:06:04','NOT NORMAL'),(8846,'Leaving','2011-11-29 11:29:09',6,'2011-11-28 11:09:31','NOT NORMAL'),(8847,'Spare_Time/TV','2011-11-28 12:21:40',6,'2011-11-28 11:38:40','NORMAL'),(8848,'Toileting','2011-11-28 12:27:07',4,'2011-11-28 12:22:38','NORMAL'),(8849,'Lunch','2011-11-28 13:04:00',4,'2011-11-28 12:27:11','NORMAL'),(8850,'Spare_Time/TV','2011-11-28 18:20:00',6,'2011-11-28 13:07:01','NORMAL'),(8851,'Snack','2011-11-28 18:20:59',6,'2011-11-28 18:20:55','NORMAL'),(8852,'Spare_Time/TV','2011-11-29 00:06:00',6,'2011-11-28 18:21:15','NORMAL'),(8853,'Sleeping','2011-11-29 16:31:00',6,'2011-11-29 00:16:00','NOT NORMAL'),(8854,'Toileting','2011-11-29 09:36:55',6,'2011-11-29 09:31:55','NORMAL'),(8855,'Grooming','2011-11-29 09:48:54',4,'2011-11-29 09:37:38','NORMAL'),(8856,'Breakfast','2011-11-29 10:18:00',6,'2011-11-29 10:08:28','NORMAL'),(8857,'Grooming','2011-11-29 10:22:00',6,'2011-11-29 10:19:01','NORMAL'),(8858,'Spare_Time/TV','2011-11-29 10:24:59',6,'2011-11-29 10:22:38','NORMAL'),(8859,'Snack','2011-11-29 11:25:32',6,'2011-11-29 11:25:29','NORMAL'),(8860,'Spare_Time/TV','2011-11-29 13:12:26',6,'2011-11-29 11:25:38','NORMAL'),(8861,'Toileting','2011-11-29 14:14:57',4,'2011-11-29 13:13:28','NOT NORMAL'),(8862,'Lunch','2011-11-29 13:45:54',4,'2011-11-29 13:14:33','NORMAL'),(8863,'Spare_Time/TV','2011-11-29 14:17:58',6,'2011-11-29 13:52:04','NORMAL'),(8864,'Toileting','2011-11-29 14:31:27',6,'2011-11-29 14:18:00','NORMAL'),(8865,'Spare_Time/TV','2011-11-29 15:08:07',6,'2011-11-29 14:34:17','NORMAL'),(8866,'Toileting','2011-11-29 15:09:29',6,'2011-11-29 15:08:58','NORMAL'),(8867,'Spare_Time/TV','2011-11-29 16:57:22',6,'2011-11-29 15:43:00','NORMAL'),(8868,'Leaving','2011-11-30 18:23:38',6,'2011-11-29 17:02:15','NOT NORMAL'),(8869,'Spare_Time/TV','2011-11-29 23:19:47',4,'2011-11-29 18:28:00','NORMAL'),(8870,'Sleeping','2011-11-30 08:07:31',4,'2011-11-29 23:22:33','NORMAL'),(8871,'Showering','2011-11-30 08:18:33',4,'2011-11-30 08:14:45','NORMAL'),(8872,'Breakfast','2011-11-30 08:35:00',6,'2011-11-30 08:22:59','NORMAL'),(8873,'Grooming','2011-11-30 08:41:13',4,'2011-11-30 08:39:30','NORMAL'),(8874,'Spare_Time/TV','2011-11-30 11:04:59',4,'2011-11-30 08:41:38','NORMAL'),(8875,'Snack','2011-11-30 11:05:31',6,'2011-11-30 11:05:27','NORMAL'),(8876,'Spare_Time/TV','2011-11-30 12:09:34',6,'2011-11-30 11:05:38','NORMAL'),(8877,'Toileting','2011-11-30 12:11:48',6,'2011-11-30 12:11:16','NORMAL'),(8878,'Spare_Time/TV','2011-11-30 12:34:09',6,'2011-11-30 12:13:45','NORMAL'),(8879,'Lunch','2011-11-30 13:00:24',6,'2011-11-30 12:39:42','NORMAL'),(8880,'Toileting','2011-11-30 14:40:24',6,'2011-11-30 14:40:01','NORMAL'),(8881,'Snack','2011-11-30 14:41:09',6,'2011-11-30 14:41:05','NORMAL'),(8882,'Spare_Time/TV','2011-11-30 16:00:38',4,'2011-11-30 14:41:15','NORMAL'),(8883,'Toileting','2011-11-30 16:01:47',4,'2011-11-30 16:01:44','NORMAL'),(8884,'Spare_Time/TV','2011-11-30 16:43:20',6,'2011-11-30 16:01:54','NORMAL'),(8885,'Toileting','2011-11-30 16:57:34',6,'2011-11-30 16:57:08','NORMAL'),(8886,'Spare_Time/TV','2011-11-30 17:37:10',6,'2011-11-30 16:57:39','NORMAL'),(8887,'Toileting','2011-11-30 17:38:01',6,'2011-11-30 17:37:36','NORMAL'),(8888,'Leaving','2011-11-30 18:05:12',6,'2011-11-30 17:46:44','NORMAL'),(8889,'Grooming','2011-11-30 18:13:48',6,'2011-11-30 18:13:39','NORMAL'),(8890,'Sleeping','2011-12-01 08:50:45',4,'2011-11-30 23:37:52','NORMAL'),(8891,'Grooming','2011-12-01 08:55:32',6,'2011-12-01 08:55:03','NORMAL'),(8892,'Showering','2011-12-01 09:12:22',6,'2011-12-01 08:58:37','NORMAL'),(8893,'Breakfast','2011-12-01 09:29:49',6,'2011-12-01 09:17:05','NORMAL'),(8894,'Spare_Time/TV','2011-12-01 12:29:02',6,'2011-12-01 09:30:06','NORMAL'),(8895,'Lunch','2011-12-01 12:55:02',4,'2011-12-01 12:29:37','NORMAL'),(8896,'Spare_Time/TV','2011-12-01 14:26:17',4,'2011-12-01 13:09:00','NORMAL'),(8897,'Toileting','2011-12-01 14:27:04',4,'2011-12-01 14:26:35','NORMAL'),(8898,'Spare_Time/TV','2011-12-01 14:46:40',6,'2011-12-01 14:27:03','NORMAL'),(8899,'Grooming','2011-12-01 15:27:13',6,'2011-12-01 15:17:23','NORMAL'),(8900,'Leaving','2011-12-01 16:49:51',6,'2011-12-01 15:29:29','NORMAL'),(8901,'Toileting','2011-12-01 17:29:59',6,'2011-12-01 17:28:51','NORMAL'),(8902,'Spare_Time/TV','2011-12-01 21:52:52',6,'2011-12-01 17:30:01','NORMAL'),(8903,'Grooming','2011-12-01 21:53:28',4,'2011-12-01 21:53:00','NORMAL'),(8904,'Spare_Time/TV','2011-12-01 23:18:32',4,'2011-12-01 21:54:32','NORMAL'),(8905,'Grooming','2011-12-01 23:18:57',6,'2011-12-01 23:18:44','NORMAL'),(8906,'Spare_Time/TV','2011-12-02 00:08:17',6,'2011-12-01 23:19:09','NORMAL'),(8907,'Grooming','2011-12-02 00:13:56',6,'2011-12-02 00:09:33','NORMAL'),(8908,'Sleeping','2011-12-02 10:15:19',6,'2011-12-02 00:15:55','NORMAL'),(8909,'Toileting','2011-12-02 10:20:40',4,'2011-12-02 10:16:00','NORMAL'),(8910,'Grooming','2011-12-02 11:20:59',6,'2011-12-02 10:20:41','NOT NORMAL'),(8911,'Showering','2011-12-02 10:25:40',6,'2011-12-02 10:21:19','NORMAL'),(8912,'Breakfast','2011-12-02 11:35:49',4,'2011-12-02 10:27:47','NORMAL'),(8913,'Lunch','2011-12-02 13:20:00',6,'2011-12-02 12:47:00','NORMAL'),(8914,'Spare_Time/TV','2011-12-02 13:55:12',6,'2011-12-02 13:24:05','NORMAL'),(8915,'Grooming','2011-12-02 13:57:55',4,'2011-12-02 13:56:02','NORMAL'),(8916,'Spare_Time/TV','2011-12-02 14:36:05',4,'2011-12-02 13:58:00','NORMAL'),(8917,'Toileting','2011-12-02 14:47:27',4,'2011-12-02 14:37:27','NORMAL'),(8918,'Spare_Time/TV','2011-12-02 17:39:08',4,'2011-12-02 14:47:39','NORMAL'),(8919,'Snack','2011-12-02 17:40:50',6,'2011-12-02 17:40:44','NORMAL'),(8920,'Spare_Time/TV','2011-12-02 19:26:09',6,'2011-12-02 17:41:14','NORMAL'),(8921,'Toileting','2011-12-02 19:44:37',6,'2011-12-02 19:27:09','NORMAL'),(8922,'Spare_Time/TV','2011-12-02 23:41:08',6,'2011-12-02 19:46:31','NORMAL'),(8923,'Sleeping','2011-12-03 09:56:20',6,'2011-12-02 23:47:02','NORMAL'),(8924,'Toileting','2011-12-03 09:59:15',6,'2011-12-03 09:57:15','NORMAL'),(8925,'Grooming','2011-12-03 10:01:59',6,'2011-12-03 09:59:33','NORMAL'),(8926,'Breakfast','2011-12-03 10:19:30',6,'2011-12-03 10:10:35','NORMAL'),(8927,'Spare_Time/TV','2011-12-03 11:28:36',6,'2011-12-03 10:21:30','NORMAL'),(8928,'Grooming','2011-12-03 11:29:59',6,'2011-12-03 11:29:00','NORMAL'),(8929,'Spare_Time/TV','2011-12-03 12:01:20',6,'2011-12-03 11:29:48','NORMAL'),(8930,'Grooming','2011-12-03 12:14:43',6,'2011-12-03 12:11:10','NORMAL'),(8931,'Leaving','2011-12-03 15:48:37',4,'2011-12-03 12:18:11','NORMAL'),(8932,'Spare_Time/TV','2011-12-03 21:53:24',4,'2011-12-03 15:50:14','NORMAL'),(8933,'Toileting','2011-12-03 21:54:51',4,'2011-12-03 21:54:00','NORMAL'),(8934,'Spare_Time/TV','2011-12-04 01:16:13',6,'2011-12-03 21:55:18','NORMAL'),(8935,'Sleeping','2011-12-04 10:37:49',6,'2011-12-04 01:19:17','NORMAL'),(8936,'Toileting','2011-12-04 10:45:44',6,'2011-12-04 10:39:45','NORMAL'),(8937,'Grooming','2011-12-04 10:47:28',6,'2011-12-04 10:46:11','NORMAL'),(8938,'Showering','2011-12-04 10:54:55',6,'2011-12-04 10:50:56','NORMAL'),(8939,'Breakfast','2011-12-04 10:59:48',6,'2011-12-04 10:56:08','NORMAL'),(8940,'Toileting','2011-12-04 12:03:42',6,'2011-12-04 12:00:12','NORMAL'),(8941,'Leaving','2011-12-04 14:31:01',6,'2011-12-04 12:11:50','NORMAL'),(8942,'Spare_Time/TV','2011-12-04 15:25:39',6,'2011-12-04 14:34:00','NORMAL'),(8943,'Snack','2011-12-04 15:27:08',6,'2011-12-04 15:27:03','NORMAL'),(8944,'Spare_Time/TV','2011-12-04 15:43:56',6,'2011-12-04 15:27:16','NORMAL'),(8945,'Grooming','2011-12-04 15:45:59',4,'2011-12-04 15:45:00','NORMAL'),(8946,'Spare_Time/TV','2011-12-04 16:57:52',4,'2011-12-04 16:08:28','NORMAL'),(8947,'Toileting','2011-12-04 16:59:33',4,'2011-12-04 16:58:06','NORMAL'),(8948,'Snack','2011-12-04 16:59:59',4,'2011-12-04 16:59:53','NORMAL'),(8949,'Spare_Time/TV','2011-12-04 20:20:59',4,'2011-12-04 17:00:04','NORMAL'),(8950,'Toileting','2011-12-04 20:22:10',6,'2011-12-04 20:21:10','NORMAL'),(8951,'Spare_Time/TV','2011-12-04 23:28:09',6,'2011-12-04 20:22:19','NORMAL'),(8952,'Grooming','2011-12-04 23:31:39',6,'2011-12-04 23:31:34','NORMAL'),(8953,'Sleeping','2011-12-05 09:48:55',6,'2011-12-04 23:35:10','NORMAL'),(8954,'Toileting','2011-12-05 09:53:31',6,'2011-12-05 09:53:01','NORMAL'),(8955,'Showering','2011-12-05 10:08:56',6,'2011-12-05 10:01:23','NORMAL'),(8956,'Breakfast','2011-12-05 10:24:37',6,'2011-12-05 10:14:49','NORMAL'),(8957,'Spare_Time/TV','2011-12-05 12:26:46',4,'2011-12-05 11:46:29','NORMAL'),(8958,'Grooming','2011-12-05 12:28:42',4,'2011-12-05 12:28:36','NORMAL'),(8959,'Leaving','2011-12-05 12:55:06',4,'2011-12-05 12:38:07','NORMAL'),(8960,'Lunch','2011-12-05 13:40:59',6,'2011-12-05 12:57:51','NORMAL'),(8961,'Grooming','2011-12-05 13:43:59',6,'2011-12-05 13:42:25','NORMAL'),(8962,'Toileting','2011-12-05 13:44:16',6,'2011-12-05 13:44:00','NORMAL'),(8963,'Spare_Time/TV','2011-12-05 14:02:10',6,'2011-12-05 13:44:56','NORMAL'),(8964,'Toileting','2011-12-05 14:36:32',4,'2011-12-05 14:06:32','NORMAL'),(8965,'Spare_Time/TV','2011-12-05 15:14:32',4,'2011-12-05 14:37:15','NORMAL'),(8966,'Leaving','2011-12-05 16:57:16',6,'2011-12-05 15:17:07','NORMAL'),(8967,'Spare_Time/TV','2011-12-05 17:24:02',6,'2011-12-05 17:01:42','NORMAL'),(8968,'Snack','2011-12-05 17:24:16',4,'2011-12-05 17:24:11','NORMAL'),(8969,'Spare_Time/TV','2011-12-05 18:07:19',4,'2011-12-05 17:24:22','NORMAL'),(8970,'Grooming','2011-12-05 18:07:47',6,'2011-12-05 18:07:32','NORMAL'),(8971,'Spare_Time/TV','2011-12-05 19:06:25',6,'2011-12-05 18:09:00','NORMAL'),(8972,'Toileting','2011-12-05 19:06:43',6,'2011-12-05 19:06:35','NORMAL'),(8973,'Grooming','2011-12-05 19:07:00',4,'2011-12-05 19:06:44','NORMAL'),(8974,'Spare_Time/TV','2011-12-05 22:38:24',4,'2011-12-05 19:07:14','NORMAL'),(8975,'Toileting','2011-12-05 22:40:41',4,'2011-12-05 22:39:01','NORMAL'),(8976,'Sleeping','2011-12-06 08:07:20',6,'2011-12-05 22:42:37','NORMAL'),(8977,'Toileting','2011-12-06 09:08:53',6,'2011-12-06 09:08:00','NORMAL'),(8978,'Grooming','2011-12-06 09:11:45',6,'2011-12-06 09:09:49','NORMAL'),(8979,'Showering','2011-12-06 09:25:57',6,'2011-12-06 09:12:19','NORMAL'),(8980,'Breakfast','2011-12-06 09:35:26',6,'2011-12-06 09:30:19','NORMAL'),(8981,'Spare_Time/TV','2011-12-06 12:37:50',6,'2011-12-06 09:39:34','NORMAL'),(8982,'Lunch','2011-12-06 13:15:58',4,'2011-12-06 12:40:53','NORMAL'),(8983,'Grooming','2011-12-06 13:20:32',4,'2011-12-06 13:18:35','NORMAL'),(8984,'Spare_Time/TV','2011-12-06 15:11:04',4,'2011-12-06 13:21:46','NORMAL'),(8985,'Toileting','2011-12-06 15:19:04',4,'2011-12-06 15:13:04','NORMAL'),(8986,'Spare_Time/TV','2011-12-06 16:06:26',6,'2011-12-06 15:19:34','NORMAL'),(8987,'Grooming','2011-12-06 16:07:15',6,'2011-12-06 16:07:03','NORMAL'),(8988,'Snack','2011-12-06 17:22:55',6,'2011-12-06 17:22:50','NORMAL'),(8989,'Grooming','2011-12-06 17:26:20',6,'2011-12-06 17:25:56','NORMAL'),(8990,'Spare_Time/TV','2011-12-06 22:07:38',6,'2011-12-06 17:40:28','NORMAL'),(8991,'Grooming','2011-12-06 22:07:57',6,'2011-12-06 22:07:47','NORMAL'),(8992,'Spare_Time/TV','2011-12-06 22:54:50',6,'2011-12-06 22:08:10','NORMAL'),(8993,'Toileting','2011-12-06 22:57:53',6,'2011-12-06 22:57:29','NORMAL'),(8994,'Sleeping','2011-12-07 08:45:40',6,'2011-12-06 22:59:56','NORMAL'),(8995,'Toileting','2011-12-07 08:49:26',6,'2011-12-07 08:46:15','NORMAL'),(8996,'Grooming','2011-12-07 09:04:19',4,'2011-12-07 08:50:38','NORMAL'),(8997,'Showering','2011-12-07 09:11:32',4,'2011-12-07 09:05:51','NORMAL'),(8998,'Breakfast','2011-12-07 09:20:04',4,'2011-12-07 09:12:17','NORMAL'),(8999,'Spare_Time/TV','2011-12-07 11:56:10',4,'2011-12-07 09:22:58','NORMAL'),(9000,'Lunch','2011-12-07 12:39:30',4,'2011-12-07 12:03:34','NORMAL'),(9001,'Spare_Time/TV','2011-12-07 13:28:03',4,'2011-12-07 12:41:59','NORMAL'),(9002,'Grooming','2011-12-07 13:31:41',6,'2011-12-07 13:31:23','NORMAL'),(9003,'Toileting','2011-12-07 13:31:46',6,'2011-12-07 13:31:42','NORMAL'),(9004,'Spare_Time/TV','2011-12-07 14:11:28',6,'2011-12-07 13:32:15','NORMAL'),(9005,'Grooming','2011-12-07 14:21:09',6,'2011-12-07 14:15:26','NORMAL'),(9006,'Grooming','2011-12-07 16:14:59',6,'2011-12-07 16:14:56','NORMAL'),(9007,'Toileting','2011-12-07 16:15:03',6,'2011-12-07 16:15:00','NORMAL'),(9008,'Spare_Time/TV','2011-12-07 17:21:46',6,'2011-12-07 16:15:51','NORMAL'),(9009,'Snack','2011-12-07 17:28:46',6,'2011-12-07 17:23:55','NORMAL'),(9010,'Snack','2011-12-07 18:38:18',4,'2011-12-07 18:37:50','NORMAL'),(9011,'Toileting','2011-12-07 18:39:44',4,'2011-12-07 18:39:16','NORMAL'),(9012,'Spare_Time/TV','2011-12-07 18:42:35',4,'2011-12-07 18:40:29','NORMAL'),(9013,'Spare_Time/TV','2011-12-07 21:19:30',6,'2011-12-07 18:52:23','NORMAL'),(9014,'Toileting','2011-12-07 21:19:56',6,'2011-12-07 21:19:40','NORMAL'),(9015,'Spare_Time/TV','2011-12-07 23:32:32',6,'2011-12-07 21:20:16','NORMAL'),(9016,'Sleeping','2011-12-08 09:14:29',4,'2011-12-07 23:39:41','NORMAL'),(9017,'Grooming','2011-12-08 09:17:48',6,'2011-12-08 09:17:37','NORMAL'),(9018,'Toileting','2011-12-08 09:17:58',4,'2011-12-08 09:17:49','NORMAL'),(9019,'Grooming','2011-12-08 09:18:13',6,'2011-12-08 09:18:02','NORMAL'),(9020,'Showering','2011-12-08 09:24:17',4,'2011-12-08 09:19:22','NORMAL'),(9021,'Breakfast','2011-12-08 09:34:01',6,'2011-12-08 09:25:12','NORMAL'),(9022,'Spare_Time/TV','2011-12-08 11:41:19',4,'2011-12-08 09:36:09','NORMAL'),(9023,'Grooming','2011-12-08 12:05:01',6,'2011-12-08 12:04:49','NORMAL'),(9024,'Leaving','2011-12-08 14:21:41',4,'2011-12-08 12:10:03','NORMAL'),(9025,'Grooming','2011-12-08 20:13:49',4,'2011-12-08 20:13:35','NORMAL'),(9026,'Spare_Time/TV','2011-12-08 21:12:37',6,'2011-12-08 20:14:14','NORMAL'),(9027,'Grooming','2011-12-08 21:14:27',6,'2011-12-08 21:14:09','NORMAL'),(9028,'Spare_Time/TV','2011-12-08 23:03:07',6,'2011-12-08 21:14:35','NORMAL'),(9029,'Grooming','2011-12-08 23:05:43',6,'2011-12-08 23:04:24','NORMAL'),(9030,'Sleeping','2011-12-09 08:49:07',6,'2011-12-08 23:16:42','NORMAL'),(9031,'Toileting','2011-12-09 08:52:27',6,'2011-12-09 08:51:56','NORMAL'),(9032,'Grooming','2011-12-09 08:52:50',6,'2011-12-09 08:52:30','NORMAL'),(9033,'Showering','2011-12-09 08:58:21',6,'2011-12-09 08:54:58','NORMAL'),(9034,'Breakfast','2011-12-09 09:08:15',6,'2011-12-09 09:00:13','NORMAL'),(9035,'Spare_Time/TV','2011-12-09 10:32:57',6,'2011-12-09 09:09:35','NORMAL'),(9036,'Grooming','2011-12-09 10:42:15',6,'2011-12-09 10:33:21','NORMAL'),(9037,'Leaving','2011-12-09 14:33:56',4,'2011-12-09 10:44:16','NORMAL'),(9038,'Spare_Time/TV','2011-12-09 14:59:52',6,'2011-12-09 14:45:53','NORMAL'),(9039,'Toileting','2011-12-09 15:04:57',4,'2011-12-09 15:01:37','NORMAL'),(9040,'Spare_Time/TV','2011-12-09 15:59:19',6,'2011-12-09 15:06:38','NORMAL'),(9041,'Grooming','2011-12-09 15:59:27',4,'2011-12-09 15:59:25','NORMAL'),(9042,'Spare_Time/TV','2011-12-09 16:19:03',6,'2011-12-09 16:02:18','NORMAL'),(9043,'Leaving','2011-12-09 16:48:36',4,'2011-12-09 16:32:02','NORMAL'),(9044,'Spare_Time/TV','2011-12-09 19:04:31',6,'2011-12-09 17:19:54','NORMAL'),(9045,'Spare_Time/TV','2011-12-09 22:16:21',4,'2011-12-09 19:04:59','NORMAL'),(9046,'Grooming','2011-12-09 22:16:49',4,'2011-12-09 22:16:31','NORMAL'),(9047,'Spare_Time/TV','2011-12-09 22:46:13',4,'2011-12-09 22:17:05','NORMAL'),(9048,'Sleeping','2011-12-10 07:26:17',4,'2011-12-09 22:50:47','NORMAL'),(9049,'Toileting','2011-12-10 07:35:48',4,'2011-12-10 07:28:00','NORMAL'),(9050,'Grooming','2011-12-10 07:46:27',4,'2011-12-10 07:37:16','NORMAL'),(9051,'Showering','2011-12-10 07:52:42',4,'2011-12-10 07:47:44','NORMAL'),(9052,'Breakfast','2011-12-10 08:02:10',4,'2011-12-10 07:55:18','NORMAL'),(9053,'Spare_Time/TV','2011-12-10 10:34:09',4,'2011-12-10 08:03:20','NORMAL'),(9054,'Grooming','2011-12-10 10:53:39',4,'2011-12-10 10:52:54','NORMAL'),(9055,'Leaving','2011-12-10 15:07:03',6,'2011-12-10 11:04:03','NORMAL'),(9056,'Spare_Time/TV','2011-12-10 18:16:51',6,'2011-12-10 15:11:23','NORMAL'),(9057,'Grooming','2011-12-10 18:23:16',6,'2011-12-10 18:21:54','NORMAL'),(9058,'Leaving','2011-12-10 22:56:05',6,'2011-12-10 18:25:40','NORMAL'),(9059,'Spare_Time/TV','2011-12-11 00:26:29',6,'2011-12-10 23:01:41','NORMAL'),(9060,'Grooming','2011-12-11 00:28:44',6,'2011-12-11 00:27:23','NORMAL'),(9061,'Sleeping','2011-12-11 09:57:34',6,'2011-12-11 00:31:15','NORMAL'),(9062,'Toileting','2011-12-11 10:01:27',6,'2011-12-11 09:58:17','NORMAL'),(9063,'Grooming','2011-12-11 10:02:39',6,'2011-12-11 10:02:25','NORMAL'),(9064,'Showering','2011-12-11 10:18:42',6,'2011-12-11 10:02:56','NORMAL'),(9065,'Breakfast','2011-12-11 10:30:09',6,'2011-12-11 10:21:57','NORMAL'),(9066,'Lunch','2011-12-11 13:26:17',6,'2011-12-11 12:34:12','NORMAL'),(9067,'Toileting','2011-12-11 13:30:14',6,'2011-12-11 13:28:59','NORMAL'),(9068,'Grooming','2011-12-11 13:43:30',6,'2011-12-11 13:41:34','NORMAL'),(9069,'Spare_Time/TV','2011-12-11 19:41:48',6,'2011-12-11 13:43:51','NORMAL'),(9070,'Toileting','2011-11-30 16:01:47',4,'2011-11-30 16:01:44','NORMAL'),(9071,'Spare_Time/TV','2011-11-30 16:43:20',6,'2011-11-30 16:01:54','NORMAL');
/*!40000 ALTER TABLE `monitored_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `patient` (
  `patient_id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `birth_date` varchar(45) NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `medical_record` varchar(255) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `caregiver_id` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`patient_id`),
  KEY `FKnndtgmyav1fge29lfaxxscopd` (`username`),
  KEY `FKrtror6nlor0yjjgcq1csgcp3d` (`caregiver_id`),
  CONSTRAINT `FKnndtgmyav1fge29lfaxxscopd` FOREIGN KEY (`username`) REFERENCES `users` (`username`),
  CONSTRAINT `FKrtror6nlor0yjjgcq1csgcp3d` FOREIGN KEY (`caregiver_id`) REFERENCES `caregiver` (`caregiver_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (2,'str.test2','24-12-1990','M','None','Pacient 2',1,'patient2'),(3,'Str.. test 3','15-10-1986','F','None','Patient 3',2,'patient3'),(4,'str. test 4','12-09-1985','M','None','New Patient 4',3,'patient4'),(5,'Str. Test','25-11-1976','M','Just for test','Patient 5',3,'patient5'),(6,'str patient 6','25-12-1984','F','none','Patient 6',2,'patient6'),(7,'gdf','gfds','df','gsdf','hdfg',1,'gfdsg'),(8,'dfgsg','gdfs','gdfsg','gdfs','gdfs',1,'gdfs'),(9,'dfsaf','fsda','fsdaf','sdf','fgds',1,'sdfa'),(14,'Str. Obs','25-12-1989','F','Miopie','Patient 22',2,'patient20');
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recommendation`
--

DROP TABLE IF EXISTS `recommendation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `recommendation` (
  `recommendation_id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(200) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`recommendation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recommendation`
--

LOCK TABLES `recommendation` WRITE;
/*!40000 ALTER TABLE `recommendation` DISABLE KEYS */;
INSERT INTO `recommendation` VALUES (1,'Sleep more',6),(2,'Drink more water',5),(3,'Sleep less',6);
/*!40000 ALTER TABLE `recommendation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `users` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('caregiver1','1234','CAREGIVER'),('caregiver2','$2a$10$lqDcjb3vB10L5H3tWkODUOhAY.jylL7L0q6x3ka347wLsEwsbONYe','CAREGIVER'),('caregiver3','$2a$10$wJUr8q5jsEEf74zDT1SnAOaNoypr47o4Vuvo5HItSl1vQ6WDHnzc6','CAREGIVER'),('caregiver4','$2a$10$V9bVFRjGloLe3O1zzZccOOrfEaih/DQp1TkUOUIivcV.ics64omii','CAREGIVER'),('caregiver5','$2a$10$9.2g0EoNA3H3iH/JzaTFRO4ngOchXpwrylw3bBaivvArcmxsZ6P5e','CAREGIVER'),('doctor1','abcd','DOCTOR'),('doctor2','$2a$10$1EUg.GxiKtWr/fc0XEqdWur5vddFjulmWTkHXX0RLBbjZej4v9fHm','DOCTOR'),('doctor3','abcdef','DOCTOR'),('doctor4','$2a$10$m3alyzFVwEcsZ798E66HCeHcQqtdFeiiRwQ0QaxX0Asz5QgwC7stS','DOCTOR'),('gdfs','$2a$10$.oMMm0A8Izr5k8fLkyQbyuMwl9ZxkVk9xpPXYlU10efLugY5Fq89.','PATIENT'),('gfdsg','$2a$10$OZpngnyjk38n.CBPjP5VZeUXOUxafS.49O33J8ZpOhX2lt8v6uyBy','PATIENT'),('patient2','4321','PATIENT'),('patient20','$2a$10$Vvglxo22YdGB5lTqmOoCIOv5l5o9oWIU/OGvRwo7.N3FH0IoA0YHm','PATIENT'),('patient3','43215','PATIENT'),('patient4','4563','PATIENT'),('patient5','1234','PATIENT'),('patient6','$2a$10$PhSh8Ji3sAOf/6O2lPRD5.nuDsqTL8XiprQ5MFfIHArfi4ZlffHcm','PATIENT'),('sdaf','$2a$10$H/CRVuVOHoXJJSOYSyob7ulpcoBalrmts203bn1fiYT7LNzkFn1GO','PATIENT'),('sdfa','$2a$10$So9bIo/aEnK9LQ.71Jyqp.1kAjn35hRnSx0GmCw4oU802d1hPle2.','PATIENT');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-12 15:02:25
