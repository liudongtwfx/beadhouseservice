-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: demo
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `beadhouseelderaccident`
--

DROP TABLE IF EXISTS `beadhouseelderaccident`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `beadhouseelderaccident` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `elderIdNumber` varchar(18) NOT NULL,
  `accidenttype` enum('SuddenIllness','Fracture','Scald','Bumps','Extra') NOT NULL,
  `reason` varchar(50) DEFAULT NULL,
  `happenTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `beadhouseid` int(11) NOT NULL,
  `principleman` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `beadhouseid` (`beadhouseid`),
  CONSTRAINT `beadhouseelderaccident_ibfk_1` FOREIGN KEY (`beadhouseid`) REFERENCES `beadhouseinfo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `beadhouseelderaccident`
--

LOCK TABLES `beadhouseelderaccident` WRITE;
/*!40000 ALTER TABLE `beadhouseelderaccident` DISABLE KEYS */;
INSERT INTO `beadhouseelderaccident` VALUES (3,'fsfs','Scald','fsfs','2017-05-08 00:59:00','2017-05-08 01:02:00',108,NULL),(4,'dssas','Fracture','sasa','2017-05-16 19:51:00','2017-05-16 19:51:00',108,NULL);
/*!40000 ALTER TABLE `beadhouseelderaccident` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-04 20:25:56
