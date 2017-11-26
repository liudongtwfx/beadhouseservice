-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: localhost    Database: demo
-- ------------------------------------------------------
-- Server version	5.7.19

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
-- Table structure for table `beadhouseimageinfo`
--

DROP TABLE IF EXISTS `beadhouseimageinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `beadhouseimageinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `imagedescription` varchar(100) NOT NULL,
  `imagepath` varchar(50) NOT NULL,
  `imagepriority` tinyint(4) NOT NULL,
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `beadhouseid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `beadhouseimagehouseno` (`beadhouseid`),
  CONSTRAINT `beadhouseimageinfo_ibfk_1` FOREIGN KEY (`beadhouseid`) REFERENCES `beadhouseinfo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `beadhouseimageinfo`
--

LOCK TABLES `beadhouseimageinfo` WRITE;
/*!40000 ALTER TABLE `beadhouseimageinfo` DISABLE KEYS */;
INSERT INTO `beadhouseimageinfo` VALUES (12,'养老院图文','ovs843546f.jpg',1,'2017-09-03 02:10:01',108),(13,'养老院风景','jdmbf4o8l1.jpg',2,'2017-09-04 11:28:49',108),(14,'额为','82g4crrr5p.jpg',10,'2017-09-03 02:10:29',108),(15,'内部结构','b6a9g5q8vp.jpg',10,'2017-09-07 09:40:16',184),(16,'健身设施','lwt0i4ucpl.jpg',3,'2017-09-07 09:39:16',184),(17,'外面风景','w4tpqdgl1x.jpg',1,'2017-09-07 09:39:42',184),(18,'屋内设施','52bo8sfpt1.jpg',1,'2017-09-07 11:11:24',121),(19,'门外风景','n2pa03qwth.jpg',3,'2017-09-07 11:12:14',121),(20,'俯瞰图','t1309fvrf6.jpg',3,'2017-09-07 11:13:08',121);
/*!40000 ALTER TABLE `beadhouseimageinfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-26 13:25:05
