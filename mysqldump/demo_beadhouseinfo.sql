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
-- Table structure for table `beadhouseinfo`
--

DROP TABLE IF EXISTS `beadhouseinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `beadhouseinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fullname` varchar(255) NOT NULL,
  `locationId` varchar(6) NOT NULL,
  `longitude` double(9,6) NOT NULL,
  `latitude` double(9,6) NOT NULL,
  `fullLocation` varchar(100) NOT NULL,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=401 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `beadhouseinfo`
--

LOCK TABLES `beadhouseinfo` WRITE;
/*!40000 ALTER TABLE `beadhouseinfo` DISABLE KEYS */;
INSERT INTO `beadhouseinfo` VALUES (1,'山西省临汾市侯马市德隆养老中心','141081',0.000000,0.000000,'',NULL),(2,'湖南省永州市冷水滩区福恒养老院','431103',0.000000,0.000000,'',NULL),(3,'陕西省宝鸡市陇　县隆富养老中心','610327',0.000000,0.000000,'',NULL),(4,'河南省郑州市新郑市和富养老院','410184',0.000000,0.000000,'',NULL),(5,'山西省太原市娄烦县和泰养老中心','140123',0.000000,0.000000,'',NULL),(6,'广西壮族自治区桂林市荔蒲县祥富养老院','450331',0.000000,0.000000,'',NULL),(7,'湖北省荆州市江陵县福庆养老中心','421024',0.000000,0.000000,'',NULL),(8,'安徽省巢湖市庐江县泰康养老中心','341421',0.000000,0.000000,'',NULL),(9,'湖北省荆州市公安县兴祥养老院','421022',0.000000,0.000000,'',NULL),(10,'黑龙江省伊春市上甘岭区泰福养老中心','230716',0.000000,0.000000,'',NULL),(11,'四川省凉山彝族自治州甘洛县德恒养老中心','513435',0.000000,0.000000,'',NULL),(12,'河北省唐山市路北区富隆养老中心','130203',0.000000,0.000000,'',NULL),(13,'山东省滨州市市辖区兴和养老院','371601',0.000000,0.000000,'',NULL),(14,'北京市市辖区石景山区康祥养老院','110107',0.000000,0.000000,'',NULL),(15,'河南省安阳市安阳县泰庆养老中心','410522',0.000000,0.000000,'',NULL),(16,'江苏省泰州市高港区兴福养老院','321203',0.000000,0.000000,'',NULL),(17,'江西省九江市湖口县泰隆养老中心','360429',0.000000,0.000000,'',NULL),(18,'河南省濮阳市清丰县恒富养老院','410922',0.000000,0.000000,'',NULL),(19,'广西壮族自治区贺州市八步区隆康养老中心','451102',0.000000,0.000000,'',NULL),(20,'四川省成都市邛崃市兴富养老中心','510183',0.000000,0.000000,'',NULL),(21,'广东省肇庆市德庆县隆兴养老中心','441226',0.000000,0.000000,'',NULL),(22,'广东省梅州市梅　县福康养老院','441421',0.000000,0.000000,'',NULL),(23,'山东省荷泽市定陶县兴和养老院','371727',0.000000,0.000000,'',NULL),(24,'江西省新余市分宜县恒和养老中心','360521',0.000000,0.000000,'',NULL),(25,'广西壮族自治区百色市西林县富祥养老院','451030',0.000000,0.000000,'',NULL),(26,'河南省濮阳市范　县隆福养老中心','410926',0.000000,0.000000,'',NULL),(27,'四川省自贡市荣　县祥庆养老中心','510321',0.000000,0.000000,'',NULL),(28,'广西壮族自治区桂林市资源县泰兴养老中心','450329',0.000000,0.000000,'',NULL),(29,'湖北省孝感市云梦县庆兴养老院','420923',0.000000,0.000000,'',NULL),(30,'广西壮族自治区桂林市平乐县和隆养老中心','450330',0.000000,0.000000,'',NULL),(31,'广东省湛江市遂溪县和德养老中心','440823',0.000000,0.000000,'',NULL),(32,'河南省平顶山市卫东区和康养老中心','410403',0.000000,0.000000,'',NULL),(33,'浙江省台州市仙居县兴德养老院','331024',0.000000,0.000000,'',NULL),(34,'江苏省泰州市海陵区庆恒养老中心','321202',0.000000,0.000000,'',NULL),(35,'西藏自治区阿里地区噶尔县富福养老院','542523',0.000000,0.000000,'',NULL),(36,'北京市市辖区石景山区和福养老中心','110107',0.000000,0.000000,'',NULL),(37,'山东省枣庄市峄城区和富养老院','370404',0.000000,0.000000,'',NULL),(38,'广东省云浮市郁南县祥和养老院','445322',0.000000,0.000000,'',NULL),(39,'四川省遂宁市蓬溪县福庆养老院','510921',0.000000,0.000000,'',NULL),(40,'广西壮族自治区南宁市隆安县泰富养老院','450123',0.000000,0.000000,'',NULL),(41,'安徽省六安市金寨县兴康养老中心','341524',0.000000,0.000000,'',NULL),(42,'广东省江门市蓬江区祥和养老院','440703',0.000000,0.000000,'',NULL),(43,'云南省昆明市安宁市和德养老院','530181',0.000000,0.000000,'',NULL),(44,'安徽省池州市石台县兴隆养老院','341722',0.000000,0.000000,'',NULL),(45,'陕西省延安市甘泉县恒富养老中心','610627',0.000000,0.000000,'',NULL),(46,'福建省漳州市市辖区和康养老院','350601',0.000000,0.000000,'',NULL),(47,'山西省晋城市城　区祥康养老中心','140502',0.000000,0.000000,'',NULL),(48,'山东省烟台市牟平区恒兴养老院','370612',0.000000,0.000000,'',NULL),(49,'广东省阳江市阳春市富隆养老中心','441781',0.000000,0.000000,'',NULL),(50,'安徽省亳州市利辛县富恒养老中心','341623',0.000000,0.000000,'',NULL),(51,'安徽省六安市裕安区德康养老院','341503',0.000000,0.000000,'',NULL),(52,'青海省海南藏族自治州贵德县泰庆养老中心','632523',0.000000,0.000000,'',NULL),(53,'湖南省娄底市市辖区兴康养老院','431301',0.000000,0.000000,'',NULL),(54,'广东省阳江市阳东县福祥养老院','441723',0.000000,0.000000,'',NULL),(55,'山西省临汾市市辖区庆祥养老院','141001',0.000000,0.000000,'',NULL),(56,'江西省赣州市瑞金市康福养老中心','360781',0.000000,0.000000,'',NULL),(57,'山西省长治市黎城县祥恒养老院','140426',0.000000,0.000000,'',NULL),(58,'河北省张家口市怀来县庆隆养老中心','130730',0.000000,0.000000,'',NULL),(59,'江西省吉安市永新县福隆养老中心','360830',0.000000,0.000000,'',NULL),(60,'新疆维吾尔自治区博尔塔拉蒙古自治州精河县和德养老院','652722',0.000000,0.000000,'',NULL),(61,'广西壮族自治区贺州市市辖区兴庆养老院','451101',0.000000,0.000000,'',NULL),(62,'上海市市辖区松江区隆康养老中心','310117',0.000000,0.000000,'',NULL),(63,'安徽省黄山市祁门县兴福养老院','341024',0.000000,0.000000,'',NULL),(64,'甘肃省天水市清水县恒德养老中心','620521',0.000000,0.000000,'',NULL),(65,'辽宁省大连市甘井子区德隆养老院','210211',0.000000,0.000000,'',NULL),(66,'浙江省温州市泰顺县恒德养老中心','330329',0.000000,0.000000,'',NULL),(67,'四川省乐山市峨眉山市德庆养老中心','511181',0.000000,0.000000,'',NULL),(68,'陕西省汉中市汉台区隆祥养老院','610702',0.000000,0.000000,'',NULL),(69,'湖北省恩施土家族苗族自治州来凤县恒德养老院','422827',0.000000,0.000000,'',NULL),(70,'内蒙古自治区呼和浩特市托克托县康祥养老院','150122',0.000000,0.000000,'',NULL),(71,'湖南省岳阳市云溪区和恒养老院','430603',0.000000,0.000000,'',NULL),(72,'黑龙江省大庆市市辖区德恒养老中心','230601',0.000000,0.000000,'',NULL),(73,'吉林省延边朝鲜族自治州敦化市泰庆养老院','222403',0.000000,0.000000,'',NULL),(74,'浙江省丽水市松阳县富隆养老中心','331124',0.000000,0.000000,'',NULL),(75,'广东省江门市开平市祥德养老院','440783',0.000000,0.000000,'',NULL),(76,'山西省晋城市城　区庆德养老院','140502',0.000000,0.000000,'',NULL),(77,'甘肃省临夏回族自治州积石山保安族东乡族撒拉族自治县祥福养老院','622927',0.000000,0.000000,'',NULL),(78,'安徽省巢湖市和　县祥恒养老中心','341424',0.000000,0.000000,'',NULL),(79,'安徽省铜陵市狮子山区庆兴养老中心','340703',0.000000,0.000000,'',NULL),(80,'内蒙古自治区赤峰市克什克腾旗康福养老院','150425',0.000000,0.000000,'',NULL),(81,'吉林省吉林市昌邑区康泰养老中心','220202',0.000000,0.000000,'',NULL),(82,'黑龙江省牡丹江市海林市泰兴养老院','231083',0.000000,0.000000,'',NULL),(83,'安徽省淮北市相山区祥福养老院','340603',0.000000,0.000000,'',NULL),(84,'新疆维吾尔自治区塔城地区额敏县祥隆养老中心','654221',0.000000,0.000000,'',NULL),(85,'河南省周口市鹿邑县富隆养老中心','411628',0.000000,0.000000,'',NULL),(86,'重庆市县石柱土家族自治县和兴养老中心','500240',0.000000,0.000000,'',NULL),(87,'湖北省襄樊市樊城区泰恒养老中心','420606',0.000000,0.000000,'',NULL),(88,'辽宁省葫芦岛市南票区富泰养老院','211404',0.000000,0.000000,'',NULL),(89,'宁夏回族自治区固原市海原县祥福养老中心','640522',0.000000,0.000000,'',NULL),(90,'黑龙江省佳木斯市同江市和德养老院','230881',0.000000,0.000000,'',NULL),(91,'湖南省长沙市市辖区祥福养老院','430101',0.000000,0.000000,'',NULL),(92,'安徽省安庆市桐城市恒福养老院','340881',0.000000,0.000000,'',NULL),(93,'四川省甘孜藏族自治州色达县祥恒养老中心','513333',0.000000,0.000000,'',NULL),(94,'河北省石家庄市高邑县祥庆养老中心','130127',0.000000,0.000000,'',NULL),(95,'广东省佛山市三水区福和养老院','440607',0.000000,0.000000,'',NULL),(96,'安徽省宿州市砀山县福德养老院','341321',0.000000,0.000000,'',NULL),(97,'黑龙江省绥化市市辖区恒隆养老院','231201',0.000000,0.000000,'',NULL),(98,'内蒙古自治区赤峰市林西县恒隆养老院','150424',0.000000,0.000000,'',NULL),(99,'重庆市县铜梁县兴祥养老中心','500224',0.000000,0.000000,'',NULL),(100,'山西省忻州市岢岚县康恒养老院','140929',0.000000,0.000000,'',NULL),(101,'云南省大理白族自治州永平县德恒养老院','532928',0.000000,0.000000,'',NULL),(102,'广东省湛江市坡头区和富养老中心','440804',0.000000,0.000000,'',NULL),(103,'湖北省武汉市青山区泰庆养老院','420107',0.000000,0.000000,'',NULL),(104,'甘肃省定西市市辖区德康养老中心','621101',0.000000,0.000000,'',NULL),(105,'四川省阿坝藏族羌族自治州红原县和德养老中心','513233',0.000000,0.000000,'',NULL),(106,'西藏自治区拉萨市尼木县隆德养老院','540123',0.000000,0.000000,'',NULL),(107,'湖北省十堰市张湾区福泰养老院','420303',0.000000,0.000000,'',NULL),(108,'北京市市辖区东城区福兴养老中心','110101',116.406452,39.956079,'北京市北京市东城区安定门西滨河路22号','该养老院建立于2009年。'),(109,'江西省上饶市玉山县富祥养老院','361123',0.000000,0.000000,'',NULL),(110,'山西省忻州市宁武县和兴养老中心','140925',0.000000,0.000000,'',NULL),(111,'贵州省贵阳市息烽县德康养老院','520122',0.000000,0.000000,'',NULL),(112,'山东省济宁市兖州市富祥养老中心','370882',0.000000,0.000000,'',NULL),(113,'广西壮族自治区柳州市三江侗族自治县隆祥养老中心','450226',0.000000,0.000000,'',NULL),(114,'黑龙江省伊春市金山屯区庆福养老院','230709',0.000000,0.000000,'',NULL),(115,'河北省石家庄市藁城市泰福养老中心','130182',0.000000,0.000000,'',NULL),(116,'贵州省毕节地区赫章县和康养老院','522428',0.000000,0.000000,'',NULL),(117,'山西省大同市浑源县庆康养老院','140225',0.000000,0.000000,'',NULL),(118,'重庆市市辖区北碚区富和养老院','500109',0.000000,0.000000,'',NULL),(119,'河北省张家口市桥东区泰和养老院','130702',0.000000,0.000000,'',NULL),(120,'山东省济南市市中区富恒养老中心','370103',0.000000,0.000000,'',NULL),(121,'湖南省娄底市娄星区祥恒养老中心','431302',0.000000,0.000000,'',NULL),(122,'湖南省益阳市桃江县兴和养老中心','430922',0.000000,0.000000,'',NULL),(123,'山东省威海市文登市康福养老院','371081',0.000000,0.000000,'',NULL),(124,'山东省日照市市辖区祥隆养老院','371101',0.000000,0.000000,'',NULL),(125,'山东省济南市长清区兴庆养老院','370113',0.000000,0.000000,'',NULL),(126,'新疆维吾尔自治区和田地区于田县恒德养老院','653226',0.000000,0.000000,'',NULL),(127,'新疆维吾尔自治区乌鲁木齐市天山区庆康养老中心','650102',0.000000,0.000000,'',NULL),(128,'湖北省宜昌市枝江市康恒养老中心','420583',0.000000,0.000000,'',NULL),(129,'河北省邯郸市临漳县隆康养老中心','130423',0.000000,0.000000,'',NULL),(130,'西藏自治区那曲地区索　县恒和养老中心','542427',0.000000,0.000000,'',NULL),(131,'吉林省吉林市桦甸市庆恒养老中心','220282',0.000000,0.000000,'',NULL),(132,'福建省福州市台江区德庆养老中心','350103',0.000000,0.000000,'',NULL),(133,'西藏自治区那曲地区那曲县康恒养老中心','542421',0.000000,0.000000,'',NULL),(134,'河北省唐山市路南区隆福养老院','130202',0.000000,0.000000,'',NULL),(135,'广东省韶关市浈江区隆德养老中心','440204',0.000000,0.000000,'',NULL),(136,'青海省玉树藏族自治州治多县康泰养老院','632724',0.000000,0.000000,'',NULL),(137,'湖南省益阳市安化县康富养老院','430923',0.000000,0.000000,'',NULL),(138,'四川省广元市市中区祥和养老院','510802',0.000000,0.000000,'',NULL),(139,'福建省南平市顺昌县康恒养老院','350721',0.000000,0.000000,'',NULL),(140,'云南省丽江市古城区福恒养老中心','530702',0.000000,0.000000,'',NULL),(141,'辽宁省葫芦岛市建昌县庆隆养老中心','211422',0.000000,0.000000,'',NULL),(142,'河南省新乡市原阳县隆兴养老中心','410725',0.000000,0.000000,'',NULL),(143,'福建省漳州市长泰县隆兴养老院','350625',0.000000,0.000000,'',NULL),(144,'内蒙古自治区赤峰市巴林左旗兴福养老院','150422',0.000000,0.000000,'',NULL),(145,'贵州省安顺市平坝县富兴养老中心','520421',0.000000,0.000000,'',NULL),(146,'江苏省南京市江宁区庆泰养老中心','320115',0.000000,0.000000,'',NULL),(147,'贵州省安顺市关岭布依族苗族自治县和隆养老中心','520424',0.000000,0.000000,'',NULL),(148,'浙江省金华市东阳市恒隆养老院','330783',0.000000,0.000000,'',NULL),(149,'吉林省辽源市龙山区兴德养老院','220402',0.000000,0.000000,'',NULL),(150,'吉林省通化市通化县福兴养老院','220521',0.000000,0.000000,'',NULL),(151,'山东省潍坊市青州市兴福养老中心','370781',0.000000,0.000000,'',NULL),(152,'黑龙江省哈尔滨市呼兰区兴和养老院','230111',0.000000,0.000000,'',NULL),(153,'四川省阿坝藏族羌族自治州马尔康县祥庆养老院','513229',0.000000,0.000000,'',NULL),(154,'江苏省无锡市市辖区恒康养老院','320201',0.000000,0.000000,'',NULL),(155,'四川省成都市邛崃市隆泰养老院','510183',0.000000,0.000000,'',NULL),(156,'广西壮族自治区贵港市港北区福庆养老院','450802',0.000000,0.000000,'',NULL),(157,'江西省宜春市袁州区泰恒养老院','360902',0.000000,0.000000,'',NULL),(158,'黑龙江省牡丹江市穆棱市祥康养老院','231085',0.000000,0.000000,'',NULL),(159,'安徽省滁州市凤阳县恒富养老中心','341126',0.000000,0.000000,'',NULL),(160,'黑龙江省绥化市兰西县泰富养老中心','231222',0.000000,0.000000,'',NULL),(161,'山东省济宁市梁山县富祥养老院','370832',0.000000,0.000000,'',NULL),(162,'重庆市市辖区南岸区隆恒养老中心','500108',0.000000,0.000000,'',NULL),(163,'河北省张家口市宣化区恒祥养老院','130705',0.000000,0.000000,'',NULL),(164,'河北省石家庄市平山县恒福养老院','130131',0.000000,0.000000,'',NULL),(165,'湖南省邵阳市大祥区德庆养老院','430503',0.000000,0.000000,'',NULL),(166,'广东省深圳市龙岗区祥富养老院','440307',0.000000,0.000000,'',NULL),(167,'山东省滨州市无棣县德隆养老中心','371623',0.000000,0.000000,'',NULL),(168,'湖南省郴州市永兴县庆恒养老中心','431023',0.000000,0.000000,'',NULL),(169,'江西省宜春市市辖区恒泰养老中心','360901',0.000000,0.000000,'',NULL),(170,'辽宁省大连市沙河口区德泰养老中心','210204',0.000000,0.000000,'',NULL),(171,'甘肃省平凉市崆峒区富祥养老院','620802',0.000000,0.000000,'',NULL),(172,'江西省吉安市安福县和福养老中心','360829',0.000000,0.000000,'',NULL),(173,'河北省保定市清苑县富泰养老中心','130622',0.000000,0.000000,'',NULL),(174,'内蒙古自治区通辽市科尔沁左翼中旗德和养老院','150521',0.000000,0.000000,'',NULL),(175,'江西省南昌市西湖区恒隆养老院','360103',0.000000,0.000000,'',NULL),(176,'河南省三门峡市湖滨区隆康养老中心','411202',0.000000,0.000000,'',NULL),(177,'西藏自治区阿里地区措勤县祥兴养老院','542527',0.000000,0.000000,'',NULL),(178,'重庆市市辖区南岸区福兴养老中心','500108',0.000000,0.000000,'',NULL),(179,'陕西省宝鸡市陈仓区德富养老中心','610304',0.000000,0.000000,'',NULL),(180,'陕西省铜川市耀州区富恒养老中心','610204',0.000000,0.000000,'',NULL),(181,'河南省商丘市睢阳区福富养老院','411403',0.000000,0.000000,'',NULL),(182,'山东省滨州市沾化县德和养老中心','371624',0.000000,0.000000,'',NULL),(183,'江苏省常州市金坛市祥泰养老中心','320482',0.000000,0.000000,'',NULL),(184,'长治市怡乐养老中心','140427',113.354370,35.953204,'山西省长治市壶关县S327',''),(185,'黑龙江省哈尔滨市阿城市兴隆养老院','230181',0.000000,0.000000,'',NULL),(186,'河南省平顶山市石龙区富德养老中心','410404',0.000000,0.000000,'',NULL),(187,'福建省莆田市秀屿区兴和养老院','350305',0.000000,0.000000,'',NULL),(188,'山东省临沂市河东区祥和养老院','371312',0.000000,0.000000,'',NULL),(189,'四川省攀枝花市东　区恒康养老院','510402',0.000000,0.000000,'',NULL),(190,'福建省莆田市城厢区祥康养老中心','350302',0.000000,0.000000,'',NULL),(191,'湖南省衡阳市常宁市隆和养老院','430482',0.000000,0.000000,'',NULL),(192,'山西省忻州市繁峙县庆富养老院','140924',0.000000,0.000000,'',NULL),(193,'安徽省宣城市市辖区兴隆养老院','341801',0.000000,0.000000,'',NULL),(194,'四川省雅安市芦山县福康养老院','511826',0.000000,0.000000,'',NULL),(195,'北京市市辖区宣武区祥德养老院','110104',0.000000,0.000000,'',NULL),(196,'辽宁省丹东市元宝区恒福养老院','210602',0.000000,0.000000,'',NULL),(197,'山东省德州市夏津县德和养老中心','371427',0.000000,0.000000,'',NULL),(198,'河北省邢台市南和县富泰养老院','130527',0.000000,0.000000,'',NULL),(199,'内蒙古自治区包头市达尔罕茂明安联合旗泰恒养老院','150223',0.000000,0.000000,'',NULL),(200,'河南省商丘市虞城县富祥养老中心','411425',0.000000,0.000000,'',NULL),(201,'陕西省宝鸡市千阳县兴福养老院','610328',0.000000,0.000000,'',NULL),(202,'江西省赣州市龙南县兴德养老中心','360727',0.000000,0.000000,'',NULL),(203,'湖南省长沙市浏阳市德隆养老院','430181',0.000000,0.000000,'',NULL),(204,'河北省保定市蠡　县泰富养老中心','130635',0.000000,0.000000,'',NULL),(205,'广西壮族自治区梧州市蝶山区富康养老中心','450404',0.000000,0.000000,'',NULL),(206,'河北省邢台市南宫市兴和养老院','130581',0.000000,0.000000,'',NULL),(207,'河南省许昌市市辖区和富养老中心','411001',0.000000,0.000000,'',NULL),(208,'山西省晋中市市辖区富福养老中心','140701',0.000000,0.000000,'',NULL),(209,'广西壮族自治区钦州市灵山县泰隆养老院','450721',0.000000,0.000000,'',NULL),(210,'湖北省咸宁市通山县祥隆养老中心','421224',0.000000,0.000000,'',NULL),(211,'新疆维吾尔自治区和田地区皮山县泰祥养老院','653223',0.000000,0.000000,'',NULL),(212,'四川省成都市大邑县庆隆养老院','510129',0.000000,0.000000,'',NULL),(213,'山东省济宁市汶上县和恒养老院','370830',0.000000,0.000000,'',NULL),(214,'广东省韶关市武江区和祥养老院','440203',0.000000,0.000000,'',NULL),(215,'天津市市辖区河西区富福养老中心','120103',0.000000,0.000000,'',NULL),(216,'辽宁省沈阳市于洪区庆恒养老中心','210114',0.000000,0.000000,'',NULL),(217,'新疆维吾尔自治区克孜勒苏柯尔克孜自治州阿图什市恒兴养老中心','653001',0.000000,0.000000,'',NULL),(218,'四川省甘孜藏族自治州巴塘县隆庆养老院','513335',0.000000,0.000000,'',NULL),(219,'湖南省株洲市炎陵县祥德养老院','430225',0.000000,0.000000,'',NULL),(220,'云南省昭通市巧家县德庆养老院','530622',0.000000,0.000000,'',NULL),(221,'四川省广安市武胜县和康养老中心','511622',0.000000,0.000000,'',NULL),(222,'山东省荷泽市郓城县隆恒养老中心','371725',0.000000,0.000000,'',NULL),(223,'广西壮族自治区玉林市博白县和康养老院','450923',0.000000,0.000000,'',NULL),(224,'黑龙江省齐齐哈尔市泰来县祥和养老院','230224',0.000000,0.000000,'',NULL),(225,'河北省张家口市宣化县兴康养老中心','130721',0.000000,0.000000,'',NULL),(226,'河南省新乡市卫辉市泰和养老院','410781',0.000000,0.000000,'',NULL),(227,'新疆维吾尔自治区伊犁哈萨克自治州特克斯县泰祥养老院','654027',0.000000,0.000000,'',NULL),(228,'重庆市市辖区南岸区康和养老中心','500108',0.000000,0.000000,'',NULL),(229,'辽宁省锦州市黑山县福富养老院','210726',0.000000,0.000000,'',NULL),(230,'河北省唐山市遵化市富福养老中心','130281',0.000000,0.000000,'',NULL),(231,'山东省潍坊市潍城区泰隆养老中心','370702',0.000000,0.000000,'',NULL),(232,'河南省安阳市汤阴县兴富养老院','410523',0.000000,0.000000,'',NULL),(233,'江苏省泰州市姜堰市兴福养老中心','321284',0.000000,0.000000,'',NULL),(234,'辽宁省鞍山市铁东区德恒养老中心','210302',0.000000,0.000000,'',NULL),(235,'湖北省襄樊市枣阳市福兴养老院','420683',0.000000,0.000000,'',NULL),(236,'福建省漳州市龙文区德泰养老中心','350603',0.000000,0.000000,'',NULL),(237,'内蒙古自治区呼伦贝尔市满洲里市泰庆养老院','150781',0.000000,0.000000,'',NULL),(238,'山东省潍坊市市辖区德隆养老中心','370701',0.000000,0.000000,'',NULL),(239,'甘肃省平凉市灵台县祥泰养老中心','620822',0.000000,0.000000,'',NULL),(240,'浙江省台州市天台县富福养老中心','331023',0.000000,0.000000,'',NULL),(241,'西藏自治区昌都地区察雅县隆和养老院','542126',0.000000,0.000000,'',NULL),(242,'辽宁省丹东市市辖区庆泰养老院','210601',0.000000,0.000000,'',NULL),(243,'新疆维吾尔自治区喀什地区塔什库尔干塔吉克自治县恒泰养老中心','653131',0.000000,0.000000,'',NULL),(244,'辽宁省沈阳市辽中县康富养老中心','210122',0.000000,0.000000,'',NULL),(245,'河北省邯郸市复兴区祥德养老中心','130404',0.000000,0.000000,'',NULL),(246,'河南省周口市郸城县泰恒养老中心','411625',0.000000,0.000000,'',NULL),(247,'重庆市县忠　县泰恒养老中心','500233',0.000000,0.000000,'',NULL),(248,'河北省邯郸市复兴区德康养老中心','130404',0.000000,0.000000,'',NULL),(249,'西藏自治区拉萨市当雄县兴庆养老院','540122',0.000000,0.000000,'',NULL),(250,'河北省秦皇岛市市辖区祥德养老中心','130301',0.000000,0.000000,'',NULL),(251,'河北省张家口市阳原县隆德养老院','130727',0.000000,0.000000,'',NULL),(252,'内蒙古自治区乌兰察布市集宁区恒隆养老院','150902',0.000000,0.000000,'',NULL),(253,'安徽省亳州市蒙城县兴泰养老中心','341622',0.000000,0.000000,'',NULL),(254,'山西省忻州市河曲县泰庆养老院','140930',0.000000,0.000000,'',NULL),(255,'江西省上饶市余干县康富养老院','361127',0.000000,0.000000,'',NULL),(256,'山西省忻州市河曲县康兴养老院','140930',0.000000,0.000000,'',NULL),(257,'西藏自治区日喀则地区定日县和康养老院','542324',0.000000,0.000000,'',NULL),(258,'上海市市辖区普陀区隆德养老院','310107',0.000000,0.000000,'',NULL),(259,'重庆市县綦江县恒庆养老院','500222',0.000000,0.000000,'',NULL),(260,'湖北省荆州市松滋市祥德养老中心','421087',0.000000,0.000000,'',NULL),(261,'河北省承德市双滦区兴庆养老院','130803',0.000000,0.000000,'',NULL),(262,'广东省韶关市武江区和泰养老中心','440203',0.000000,0.000000,'',NULL),(263,'广西壮族自治区桂林市荔蒲县康祥养老院','450331',0.000000,0.000000,'',NULL),(264,'吉林省白山市市辖区庆泰养老中心','220601',0.000000,0.000000,'',NULL),(265,'山东省济宁市兖州市恒兴养老中心','370882',0.000000,0.000000,'',NULL),(266,'新疆维吾尔自治区和田地区皮山县康兴养老中心','653223',0.000000,0.000000,'',NULL),(267,'贵州省安顺市关岭布依族苗族自治县康德养老中心','520424',0.000000,0.000000,'',NULL),(268,'广西壮族自治区柳州市柳江县隆德养老院','450221',0.000000,0.000000,'',NULL),(269,'黑龙江省大庆市肇源县和隆养老院','230622',0.000000,0.000000,'',NULL),(270,'江苏省徐州市丰　县兴庆养老院','320321',0.000000,0.000000,'',NULL),(271,'吉林省长春市绿园区兴康养老院','220106',0.000000,0.000000,'',NULL),(272,'湖北省省直辖行政单位仙桃市隆和养老院','429004',0.000000,0.000000,'',NULL),(273,'河南省开封市南关区兴和养老中心','410205',0.000000,0.000000,'',NULL),(274,'江苏省盐城市市辖区康富养老中心','320901',0.000000,0.000000,'',NULL),(275,'辽宁省阜新市太平区泰康养老中心','210904',0.000000,0.000000,'',NULL),(276,'天津市县蓟　县富庆养老中心','120225',0.000000,0.000000,'',NULL),(277,'河南省郑州市上街区隆祥养老院','410106',0.000000,0.000000,'',NULL),(278,'四川省眉山市彭山县和泰养老院','511422',0.000000,0.000000,'',NULL),(279,'福建省福州市台江区福隆养老院','350103',0.000000,0.000000,'',NULL),(280,'四川省阿坝藏族羌族自治州汶川县恒隆养老中心','513221',0.000000,0.000000,'',NULL),(281,'江苏省盐城市盐都区祥德养老院','320903',0.000000,0.000000,'',NULL),(282,'新疆维吾尔自治区喀什地区泽普县隆富养老院','653124',0.000000,0.000000,'',NULL),(283,'新疆维吾尔自治区伊犁哈萨克自治州巩留县泰祥养老中心','654024',0.000000,0.000000,'',NULL),(284,'河北省邯郸市魏　县和康养老中心','130434',0.000000,0.000000,'',NULL),(285,'浙江省杭州市下城区康泰养老中心','330103',0.000000,0.000000,'',NULL),(286,'宁夏回族自治区银川市金凤区德恒养老院','640106',0.000000,0.000000,'',NULL),(287,'云南省文山壮族苗族自治州麻栗坡县福泰养老院','532624',0.000000,0.000000,'',NULL),(288,'云南省文山壮族苗族自治州广南县福恒养老院','532627',0.000000,0.000000,'',NULL),(289,'黑龙江省佳木斯市市辖区和隆养老院','230801',0.000000,0.000000,'',NULL),(290,'云南省大理白族自治州祥云县隆泰养老院','532923',0.000000,0.000000,'',NULL),(291,'河南省洛阳市伊川县富德养老中心','410329',0.000000,0.000000,'',NULL),(292,'宁夏回族自治区银川市永宁县隆兴养老中心','640121',0.000000,0.000000,'',NULL),(293,'西藏自治区日喀则地区昂仁县德兴养老中心','542327',0.000000,0.000000,'',NULL),(294,'西藏自治区那曲地区申扎县福富养老中心','542426',0.000000,0.000000,'',NULL),(295,'安徽省安庆市枞阳县泰庆养老中心','340823',0.000000,0.000000,'',NULL),(296,'河南省周口市鹿邑县福祥养老中心','411628',0.000000,0.000000,'',NULL),(297,'四川省内江市市中区福兴养老中心','511002',0.000000,0.000000,'',NULL),(298,'青海省海东地区平安县富隆养老院','632121',0.000000,0.000000,'',NULL),(299,'山西省太原市尖草坪区兴福养老院','140108',0.000000,0.000000,'',NULL),(300,'河南省驻马店市西平县德康养老院','411721',0.000000,0.000000,'',NULL),(301,'广东省肇庆市怀集县祥庆养老中心','441224',0.000000,0.000000,'',NULL),(302,'广东省汕尾市陆河县兴富养老院','441523',0.000000,0.000000,'',NULL),(303,'吉林省通化市辉南县兴庆养老中心','220523',0.000000,0.000000,'',NULL),(304,'黑龙江省哈尔滨市宾　县祥泰养老中心','230125',0.000000,0.000000,'',NULL),(305,'山东省济南市槐荫区庆福养老院','370104',0.000000,0.000000,'',NULL),(306,'青海省海北藏族自治州刚察县德康养老中心','632224',0.000000,0.000000,'',NULL),(307,'上海市市辖区卢湾区隆福养老院','310103',0.000000,0.000000,'',NULL),(308,'河南省商丘市睢阳区兴隆养老院','411403',0.000000,0.000000,'',NULL),(309,'湖北省荆门市东宝区泰隆养老中心','420802',0.000000,0.000000,'',NULL),(310,'安徽省巢湖市无为县富泰养老院','341422',0.000000,0.000000,'',NULL),(311,'浙江省丽水市景宁畲族自治县泰祥养老院','331127',0.000000,0.000000,'',NULL),(312,'四川省巴中市平昌县德康养老中心','511923',0.000000,0.000000,'',NULL),(313,'内蒙古自治区乌兰察布市卓资县富兴养老院','150921',0.000000,0.000000,'',NULL),(314,'湖南省邵阳市新宁县康富养老院','430528',0.000000,0.000000,'',NULL),(315,'陕西省商洛市镇安县泰德养老院','611025',0.000000,0.000000,'',NULL),(316,'浙江省台州市市辖区富祥养老中心','331001',0.000000,0.000000,'',NULL),(317,'天津市市辖区南开区庆富养老中心','120104',0.000000,0.000000,'',NULL),(318,'青海省黄南藏族自治州尖扎县庆兴养老中心','632322',0.000000,0.000000,'',NULL),(319,'陕西省咸阳市渭城区泰庆养老中心','610404',0.000000,0.000000,'',NULL),(320,'甘肃省庆阳市宁　县祥庆养老院','621026',0.000000,0.000000,'',NULL),(321,'河北省张家口市崇礼县康和养老中心','130733',0.000000,0.000000,'',NULL),(322,'青海省果洛藏族自治州久治县泰福养老中心','632625',0.000000,0.000000,'',NULL),(323,'甘肃省张掖市山丹县康恒养老中心','620725',0.000000,0.000000,'',NULL),(324,'河南省鹤壁市鹤山区兴庆养老中心','410602',0.000000,0.000000,'',NULL),(325,'西藏自治区拉萨市林周县庆祥养老院','540121',0.000000,0.000000,'',NULL),(326,'安徽省安庆市潜山县富康养老中心','340824',0.000000,0.000000,'',NULL),(327,'山东省济南市市辖区和祥养老中心','370101',0.000000,0.000000,'',NULL),(328,'浙江省金华市义乌市祥泰养老中心','330782',0.000000,0.000000,'',NULL),(329,'山东省东营市东营区和庆养老中心','370502',0.000000,0.000000,'',NULL),(330,'山东省烟台市招远市富泰养老院','370685',0.000000,0.000000,'',NULL),(331,'山东省德州市齐河县庆恒养老中心','371425',0.000000,0.000000,'',NULL),(332,'贵州省黔南布依族苗族自治州瓮安县祥兴养老中心','522725',0.000000,0.000000,'',NULL),(333,'黑龙江省绥化市庆安县和隆养老院','231224',0.000000,0.000000,'',NULL),(334,'山东省德州市宁津县富恒养老中心','371422',0.000000,0.000000,'',NULL),(335,'安徽省芜湖市市辖区富恒养老院','340201',0.000000,0.000000,'',NULL),(336,'河南省平顶山市汝州市福恒养老院','410482',0.000000,0.000000,'',NULL),(337,'湖北省荆门市钟祥市富祥养老院','420881',0.000000,0.000000,'',NULL),(338,'山东省泰安市宁阳县泰富养老院','370921',0.000000,0.000000,'',NULL),(339,'山西省朔州市怀仁县康泰养老中心','140624',0.000000,0.000000,'',NULL),(340,'河北省邯郸市邯郸县祥康养老院','130421',0.000000,0.000000,'',NULL),(341,'河南省洛阳市涧西区和恒养老院','410305',0.000000,0.000000,'',NULL),(342,'广西壮族自治区百色市那坡县祥泰养老中心','451026',0.000000,0.000000,'',NULL),(343,'广东省汕头市金平区恒富养老院','440511',0.000000,0.000000,'',NULL),(344,'陕西省渭南市华阴市祥泰养老中心','610582',0.000000,0.000000,'',NULL),(345,'西藏自治区昌都地区八宿县隆兴养老院','542127',0.000000,0.000000,'',NULL),(346,'安徽省宿州市砀山县德庆养老院','341321',0.000000,0.000000,'',NULL),(347,'云南省玉溪市元江哈尼族彝族傣族自治县富庆养老院','530428',0.000000,0.000000,'',NULL),(348,'湖南省永州市冷水滩区隆泰养老院','431103',0.000000,0.000000,'',NULL),(349,'湖南省衡阳市雁峰区康福养老中心','430406',0.000000,0.000000,'',NULL),(350,'内蒙古自治区呼伦贝尔市新巴尔虎右旗康泰养老中心','150727',0.000000,0.000000,'',NULL),(351,'四川省甘孜藏族自治州色达县富庆养老院','513333',0.000000,0.000000,'',NULL),(352,'贵州省黔东南苗族侗族自治州剑河县福恒养老中心','522629',0.000000,0.000000,'',NULL),(353,'黑龙江省绥化市北林区福康养老中心','231202',0.000000,0.000000,'',NULL),(354,'四川省遂宁市安居区和恒养老院','510904',0.000000,0.000000,'',NULL),(355,'广东省汕尾市陆河县祥德养老中心','441523',0.000000,0.000000,'',NULL),(356,'河南省驻马店市遂平县福和养老中心','411728',0.000000,0.000000,'',NULL),(357,'山西省大同市天镇县德康养老中心','140222',0.000000,0.000000,'',NULL),(358,'河南省开封市龙亭区和康养老中心','410202',0.000000,0.000000,'',NULL),(359,'陕西省延安市吴旗县和隆养老中心','610626',0.000000,0.000000,'',NULL),(360,'贵州省六盘水市盘　县恒泰养老中心','520222',0.000000,0.000000,'',NULL),(361,'西藏自治区拉萨市尼木县隆康养老中心','540123',0.000000,0.000000,'',NULL),(362,'四川省绵阳市北川羌族自治县庆恒养老中心','510726',0.000000,0.000000,'',NULL),(363,'甘肃省天水市北道区和福养老院','620503',0.000000,0.000000,'',NULL),(364,'浙江省杭州市淳安县庆隆养老院','330127',0.000000,0.000000,'',NULL),(365,'山西省朔州市怀仁县福兴养老中心','140624',0.000000,0.000000,'',NULL),(366,'湖北省黄石市西塞山区康恒养老中心','420203',0.000000,0.000000,'',NULL),(367,'天津市市辖区北辰区福德养老院','120113',0.000000,0.000000,'',NULL),(368,'重庆市市辖区双桥区福兴养老院','500111',0.000000,0.000000,'',NULL),(369,'四川省甘孜藏族自治州色达县富庆养老中心','513333',0.000000,0.000000,'',NULL),(370,'甘肃省陇南市宕昌县和泰养老院','621223',0.000000,0.000000,'',NULL),(371,'湖南省娄底市市辖区庆和养老中心','431301',0.000000,0.000000,'',NULL),(372,'四川省甘孜藏族自治州德格县祥福养老中心','513330',0.000000,0.000000,'',NULL),(373,'贵州省黔西南布依族苗族自治州安龙县恒隆养老中心','522328',0.000000,0.000000,'',NULL),(374,'云南省文山壮族苗族自治州麻栗坡县康富养老院','532624',0.000000,0.000000,'',NULL),(375,'江苏省宿迁市市辖区隆康养老院','321301',0.000000,0.000000,'',NULL),(376,'安徽省六安市金安区兴祥养老中心','341502',0.000000,0.000000,'',NULL),(377,'四川省甘孜藏族自治州道孚县泰富养老院','513326',0.000000,0.000000,'',NULL),(378,'黑龙江省绥化市北林区康泰养老中心','231202',0.000000,0.000000,'',NULL),(379,'内蒙古自治区赤峰市松山区祥康养老院','150404',0.000000,0.000000,'',NULL),(380,'广东省清远市连山壮族瑶族自治县庆祥养老院','441825',0.000000,0.000000,'',NULL),(381,'河南省三门峡市湖滨区祥兴养老院','411202',0.000000,0.000000,'',NULL),(382,'河南省平顶山市卫东区兴和养老院','410403',0.000000,0.000000,'',NULL),(383,'河北省秦皇岛市抚宁县恒和养老中心','130323',0.000000,0.000000,'',NULL),(384,'广东省江门市恩平市祥兴养老中心','440785',0.000000,0.000000,'',NULL),(385,'黑龙江省鸡西市鸡冠区恒德养老中心','230302',0.000000,0.000000,'',NULL),(386,'广东省广州市增城市富福养老中心','440183',0.000000,0.000000,'',NULL),(387,'西藏自治区昌都地区边坝县泰和养老中心','542133',0.000000,0.000000,'',NULL),(388,'浙江省金华市义乌市隆庆养老院','330782',0.000000,0.000000,'',NULL),(389,'湖南省张家界市市辖区富庆养老中心','430801',0.000000,0.000000,'',NULL),(390,'贵州省遵义市遵义县和祥养老院','520321',0.000000,0.000000,'',NULL),(391,'山西省大同市阳高县泰康养老中心','140221',0.000000,0.000000,'',NULL),(392,'贵州省黔南布依族苗族自治州龙里县恒庆养老中心','522730',0.000000,0.000000,'',NULL),(393,'浙江省杭州市余杭区和庆养老中心','330110',0.000000,0.000000,'',NULL),(394,'河南省新乡市牧野区康泰养老中心','410711',0.000000,0.000000,'',NULL),(395,'广西壮族自治区桂林市兴安县康福养老中心','450325',0.000000,0.000000,'',NULL),(396,'安徽省合肥市长丰县兴和养老中心','340121',0.000000,0.000000,'',NULL),(397,'河南省信阳市市辖区兴泰养老院','411501',0.000000,0.000000,'',NULL),(398,'吉林省白城市镇赉县祥德养老中心','220821',0.000000,0.000000,'',NULL),(399,'山东省滨州市滨城区和富养老院','371602',0.000000,0.000000,'',NULL),(400,'浙江省宁波市奉化市隆德养老中心','330283',0.000000,0.000000,'',NULL);
/*!40000 ALTER TABLE `beadhouseinfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-04 20:25:54
