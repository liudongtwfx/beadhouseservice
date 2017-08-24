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
-- Table structure for table `vipusers`
--

DROP TABLE IF EXISTS `vipusers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vipusers` (
  `id` int(11) NOT NULL,
  `emailAddress` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `telephoneNumber` varchar(15) NOT NULL,
  `addTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vipusers`
--

LOCK TABLES `vipusers` WRITE;
/*!40000 ALTER TABLE `vipusers` DISABLE KEYS */;
INSERT INTO `vipusers` VALUES (1,'619529419@qq.com','$2a$10$oLLj7vNZeZYedDswR81XceqgqdHn4dOS2kAkxAwEhfyAy9O14JOr.','liudongtwfx','13121931651','2017-01-16 08:23:50'),(2,'liudongtwfx@126.com','$2a$10$IikzDbi8JUhw5uBFLezty.e0FTSLGdMMuTSReLIhNpPkDG7pJ.YVW','liudong','13121931658','2017-01-16 08:23:50'),(3,'612323232@qq.com','$2a$10$rS9z6mZ6PICSI22yoh6b.uDtH8HME1a9.TlOy9ID2Otv46drVLgN2','liu6891333','13121931650','2017-01-16 08:23:50'),(4,'612121212@qq.com','$2a$10$hts8JxdWKZ9pdMhyDRGU5.plOZH6xTUVB00nokocA8QYneNdbZPsG','liudongtwfx11','13121931652','2017-01-16 08:36:06'),(5,'liudong@qq.com','$2a$10$2jjCwXgxoTRqcIVefr/O0OWrXaU.VTSTDEBTtHsKLAte/oe5FlKBC','jfksjk','131219313232','2017-02-26 08:40:32');
/*!40000 ALTER TABLE `vipusers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-24 17:16:03
