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
-- Table structure for table `beadhousecomment`
--

DROP TABLE IF EXISTS `beadhousecomment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `beadhousecomment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `beadhouseid` int(11) NOT NULL,
  `reply_id` int(11) DEFAULT NULL,
  `commentor` int(11) NOT NULL,
  `anonymous` tinyint(1) NOT NULL DEFAULT '0',
  `replyed` tinyint(1) NOT NULL DEFAULT '0',
  `score` float(2,1) NOT NULL DEFAULT '0.0',
  PRIMARY KEY (`id`),
  KEY `comment_beadhouseId` (`beadhouseid`),
  KEY `commentreply_Id` (`reply_id`),
  KEY `commentor_Id` (`commentor`),
  CONSTRAINT `beadhousecomment_ibfk_1` FOREIGN KEY (`beadhouseid`) REFERENCES `beadhouseinfo` (`id`),
  CONSTRAINT `beadhousecomment_ibfk_2` FOREIGN KEY (`commentor`) REFERENCES `vipusers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `beadhousecomment`
--

LOCK TABLES `beadhousecomment` WRITE;
/*!40000 ALTER TABLE `beadhousecomment` DISABLE KEYS */;
INSERT INTO `beadhousecomment` VALUES (3,'这家养老院不错','2017-08-24 20:37:17',108,0,3,0,0,1.0),(4,'干净整洁，地理位置也不错','2017-08-25 21:09:10',108,0,3,0,0,1.0),(5,'环境不错啊','2017-08-25 21:19:31',108,0,3,0,0,1.0),(6,'环境不错啊，值得去','2017-08-25 21:26:00',108,0,3,0,0,1.0),(7,'环境不错啊','2017-08-25 21:30:25',108,0,3,0,0,1.0),(8,'环境不错，值得去','2017-08-25 21:35:20',108,0,3,0,0,1.0),(9,'环境不错哦','2017-08-25 21:41:04',108,0,3,0,0,1.0),(10,'环境不错哦','2017-08-25 21:47:00',108,0,3,0,0,1.0),(11,'环境不错','2017-08-26 00:31:38',108,0,3,0,0,1.0),(12,'这家养老院不错','2017-08-26 00:44:11',108,0,3,0,0,1.0),(13,'环境优雅','2017-08-26 00:51:09',108,0,3,0,0,1.0),(14,'这家养老院不错','2017-08-26 00:55:42',108,0,3,0,0,1.0),(15,'环境很优雅','2017-08-26 05:43:36',108,0,3,0,0,1.0),(16,'怎么没有值啊','2017-08-26 05:57:26',108,0,3,0,0,0.0),(17,'怎么没有值啊','2017-08-26 06:03:10',108,0,3,0,0,0.0),(18,'为什么没有内容','2017-08-26 06:12:08',108,0,3,0,0,0.0),(19,'怎么没有内容啊','2017-08-26 06:31:18',108,0,3,0,0,0.0),(20,'怎么没有内容啊','2017-08-26 06:35:40',108,0,3,0,0,0.0),(21,'没有内容啊','2017-08-26 06:39:12',108,0,3,0,0,0.0),(22,'有东西吗？','2017-08-26 06:44:18',108,0,3,0,0,0.0),(23,'发的发的发的发的','2017-08-26 06:50:16',108,0,3,0,0,0.0),(24,'我的妈啊','2017-08-26 06:53:38',108,0,3,0,0,0.0),(25,'养老院不错啊','2017-08-26 06:56:56',108,0,3,0,0,1.0),(26,'环境不错','2017-08-27 04:40:40',108,0,3,0,0,1.0);
/*!40000 ALTER TABLE `beadhousecomment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-03 11:07:08
