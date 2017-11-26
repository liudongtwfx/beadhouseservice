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
-- Table structure for table `authapplyinfo`
--

DROP TABLE IF EXISTS `authapplyinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authapplyinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `applydepartment` varchar(20) NOT NULL,
  `leaderpass` enum('TOBEAPPROVE','YES','NO') NOT NULL DEFAULT 'TOBEAPPROVE',
  `departmentpass` enum('TOBEAPPROVE','YES','NO') NOT NULL DEFAULT 'TOBEAPPROVE',
  `applyreason` varchar(200) NOT NULL DEFAULT '工作需要',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `applyadminname` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authapplyinfo`
--

LOCK TABLES `authapplyinfo` WRITE;
/*!40000 ALTER TABLE `authapplyinfo` DISABLE KEYS */;
INSERT INTO `authapplyinfo` VALUES (1,'leisuregroupadmin','TOBEAPPROVE','TOBEAPPROVE','需要休闲团数据','2017-10-25 01:12:37','2017-10-25 09:12:37','yangjieshi'),(2,'sysadmin','TOBEAPPROVE','TOBEAPPROVE','需要查看管理数据','2017-10-25 01:27:09','2017-10-25 09:27:08','yangjieshi'),(3,'articleadmin','TOBEAPPROVE','TOBEAPPROVE','需要查看攻略','2017-10-25 01:37:54','2017-10-25 09:37:53','yangjieshi');
/*!40000 ALTER TABLE `authapplyinfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-26 13:25:03
