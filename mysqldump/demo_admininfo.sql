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
-- Table structure for table `admininfo`
--

DROP TABLE IF EXISTS `admininfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admininfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employeeid` varchar(8) NOT NULL,
  `chinesename` varchar(12) NOT NULL,
  `telephonenumber` varchar(11) NOT NULL,
  `emailaddress` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(100) NOT NULL,
  `addtime` date NOT NULL,
  `department` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `employeeid` (`employeeid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admininfo`
--

LOCK TABLES `admininfo` WRITE;
/*!40000 ALTER TABLE `admininfo` DISABLE KEYS */;
INSERT INTO `admininfo` VALUES (1,'CR12536','刘东','13121931651','619529419@qq.com','liu6891333','$2a$10$s7GqqYX84hrAYG7VubPiVu4uRA0Cnx2/CFSdtbX/sK9KIS0Awea7q','2017-05-26',''),(2,'HY0000','陈锋','13121981765','liudong@qq.com','root','$2a$10$HJvTwsEwdWiovrN/3O9D/eW3wOe3ADyuOvrkjCMjyulRq34hmu31m','2017-10-25',''),(3,'HY0001','胡东方','13121981721','hudongfang@126.com','useradmin','$2a$10$1FSPewW.KpFYJSe3R28Tvu8F.awYMc2w5o5l4NufqL0UB8pPoA5NC','2017-10-25',''),(4,'HY0002','李暮达','13121981789','limuda@126.com','beadhouseadmin','$2a$10$o/M18dVSxJMtwIwMh/Uh/.nWh5AxKoNAnrK1./1YZt8UpfnwYSuR2','2017-10-25',''),(5,'HY0003','林晶晶','13121931543','linjingjing@126.com','articleadmin','$2a$10$bpWbNT5WpOMn/ykldDfUUulCpIhxxgrXr5whjQKE.h9Pejj.B8XVG','2017-10-25',''),(6,'HY0004','何家达','13121931787','hejiada@126.com','leisuregroupadmin','$2a$10$CM5VxKWAaoSbfDt9YazQ9e6BIXAMOY8lzVlwjc9hBaaATM60a98He','2017-10-25',''),(7,'HY0005','张延','13121896464','zhangyan@126.com','sysadmin','$2a$10$B45upYdMAMikUfdZSazqyu9ggXyiPKhvbAMjYFjbo7WO6v3K3PVjO','2017-10-25',''),(8,'HY1001','杨结实','13129876451','987623212@qq.com','yangjieshi','$2a$10$AJlHZ.U1Rtuq2fWxgOFppuqMn5wWjzsvLSC9gs3hy27qa.VlJnssy','2017-10-25','useradmin');
/*!40000 ALTER TABLE `admininfo` ENABLE KEYS */;
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
