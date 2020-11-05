-- MySQL dump 10.13  Distrib 8.0.18, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: boot_template
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `DATABASECHANGELOG`
--

DROP TABLE IF EXISTS `DATABASECHANGELOG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `DATABASECHANGELOG` (
  `ID` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `AUTHOR` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `FILENAME` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `MD5SUM` varchar(35) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `COMMENTS` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `TAG` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `LIQUIBASE` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `CONTEXTS` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `LABELS` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DATABASECHANGELOG`
--

LOCK TABLES `DATABASECHANGELOG` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOG` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOG` VALUES ('1593562788','ThanhLoyal','./changeLog.xml','2020-07-01 02:59:27',1,'EXECUTED','8:8174a0b71efbadda13c28cd1e5ba21a9','createTable tableName=user','',NULL,'3.8.9',NULL,NULL,'3572367096'),('1594008125','ThanhLoyal','./changeLog.xml','2020-07-06 04:04:15',2,'EXECUTED','8:f89c03fdef81eb8d943d130e9e80cf7f','addForeignKeyConstraint baseTableName=user, constraintName=fk_user_createdBy, referencedTableName=user','',NULL,'3.8.9',NULL,NULL,'4008254294'),('1594008278','ThanhLoyal','./changeLog.xml','2020-07-06 04:05:12',3,'EXECUTED','8:3175bc852b7f6b3ba302478e943e131b','addForeignKeyConstraint baseTableName=user, constraintName=fk_user_lastModifiedBy, referencedTableName=user','',NULL,'3.8.9',NULL,NULL,'4008311565'),('1594116739','ThanhLoyal','./changeLog.xml','2020-07-07 10:14:58',4,'EXECUTED','8:575ec8e0409ca037783f2abf3160b1fa','createTable tableName=user_group','',NULL,'3.8.9',NULL,NULL,'4116897746'),('1594116842','ThanhLoyal','./changeLog.xml','2020-07-07 10:14:59',5,'EXECUTED','8:8c99ddd5925696c404fea60fb20ddb1f','addForeignKeyConstraint baseTableName=user_group, constraintName=fk_userGroup_createdBy, referencedTableName=user','',NULL,'3.8.9',NULL,NULL,'4116897746'),('1594116850','ThanhLoyal','./changeLog.xml','2020-07-07 10:14:59',6,'EXECUTED','8:f813b4ad624ec2fbfb0ba986e1a593e8','addForeignKeyConstraint baseTableName=user_group, constraintName=fk_userGroup_lastModifiedBy, referencedTableName=user','',NULL,'3.8.9',NULL,NULL,'4116897746'),('1594124520','ThanhLoyal','./changeLog.xml','2020-07-07 12:22:17',7,'EXECUTED','8:a3ed344ca8689e89bc489d28fe3c468d','createTable tableName=user_group','',NULL,'3.8.9',NULL,NULL,'4124536944'),('1594170536','ThanhLoyal','./changeLog.xml','2020-07-08 01:13:17',8,'EXECUTED','8:955d5570698ae5e1f1e08860a04f6365','createTable tableName=menu','',NULL,'3.8.9',NULL,NULL,'4170797589'),('1594170670','ThanhLoyal','./changeLog.xml','2020-07-08 01:13:18',9,'EXECUTED','8:a30d58039412bae07b2df8b1d46cd72c','addForeignKeyConstraint baseTableName=menu, constraintName=fk_menu_createdBy, referencedTableName=user','',NULL,'3.8.9',NULL,NULL,'4170797589'),('1594170689','ThanhLoyal','./changeLog.xml','2020-07-08 01:13:18',10,'EXECUTED','8:42817713ce60e01be2ade1436df1573b','addForeignKeyConstraint baseTableName=menu, constraintName=fk_menu_lastModifiedBy, referencedTableName=user','',NULL,'3.8.9',NULL,NULL,'4170797589'),('1594176416','ThanhLoyal','./changeLog.xml','2020-07-08 02:50:58',11,'EXECUTED','8:3e1ad005348d02cb8f7b41c0654e1e4d','createTable tableName=menu','',NULL,'3.8.9',NULL,NULL,'4176658711'),('1594176417','ThanhLoyal','./changeLog.xml','2020-07-08 03:16:04',12,'EXECUTED','8:1be806d780af3caf5d34ac99fc0b5fa9','createTable tableName=menu','',NULL,'3.8.9',NULL,NULL,'4178164243'),('1594176418','ThanhLoyal','./changeLog.xml','2020-07-08 03:17:38',13,'EXECUTED','8:7af7644ee0c3cfa391b1bf57e1a9dbaa','createTable tableName=menu','',NULL,'3.8.9',NULL,NULL,'4178257721'),('1594204154','ThanhLoyal','./changeLog.xml','2020-07-08 10:33:54',14,'EXECUTED','8:148ba081ab0161832d1b4de365b52398','createTable tableName=user_group_menu_map','',NULL,'3.8.9',NULL,NULL,'4204434521'),('1594204409','ThanhLoyal','./changeLog.xml','2020-07-08 10:33:54',15,'EXECUTED','8:b11288cebc52c50753f57756c9e4449f','addUniqueConstraint constraintName=uidx_Group_Menu, tableName=user_group_menu_map','',NULL,'3.8.9',NULL,NULL,'4204434521'),('1594394415','ThanhLoyal','./changeLog.xml','2020-07-10 15:39:53',16,'EXECUTED','8:e411346be295f33343d5b367d0f98b3b','createTable tableName=login_session','',NULL,'3.8.9',NULL,NULL,'4395593046'),('1594394416','ThanhLoyal','./changeLog.xml','2020-07-11 02:56:11',17,'EXECUTED','8:c31ba464523981c082689a1b4834b30b','createTable tableName=login_session','',NULL,'3.8.9',NULL,NULL,'4436171711'),('1598104448','ThanhLoyal','./changeLog.xml','2020-08-22 13:59:41',18,'EXECUTED','8:1adc3fc57532ba9d2702554ed7733436','createTable tableName=user_user_group_map','',NULL,'3.8.9',NULL,NULL,'8104780448'),('1598104576','ThanhLoyal','./changeLog.xml','2020-08-22 13:59:41',19,'EXECUTED','8:0200c3357e0773c9de3013d4de82bbf0','addUniqueConstraint constraintName=uidx_user_group_map, tableName=user_user_group_map','',NULL,'3.8.9',NULL,NULL,'8104780448'),('1598104690','ThanhLoyal','./changeLog.xml','2020-08-22 13:59:42',20,'EXECUTED','8:66debc38e3e6931adfae8dbd391651ea','addForeignKeyConstraint baseTableName=user_user_group_map, constraintName=fk_user_user_group_map_user_id, referencedTableName=user','',NULL,'3.8.9',NULL,NULL,'8104780448'),('1598104693','ThanhLoyal','./changeLog.xml','2020-08-22 13:59:42',21,'EXECUTED','8:731347a08946d8e482a672913637a8e8','addForeignKeyConstraint baseTableName=user_user_group_map, constraintName=fk_user_user_group_map_group_id, referencedTableName=user_group','',NULL,'3.8.9',NULL,NULL,'8104780448');
/*!40000 ALTER TABLE `DATABASECHANGELOG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DATABASECHANGELOGLOCK`
--

DROP TABLE IF EXISTS `DATABASECHANGELOGLOCK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `DATABASECHANGELOGLOCK` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DATABASECHANGELOGLOCK`
--

LOCK TABLES `DATABASECHANGELOGLOCK` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOGLOCK` VALUES (1,_binary '\0',NULL,NULL);
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_session`
--

DROP TABLE IF EXISTS `login_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login_session` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `access_token_id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `refresh_token_id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ip_address` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_agent` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=144 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Sessions that user login to system';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_session`
--

LOCK TABLES `login_session` WRITE;
/*!40000 ALTER TABLE `login_session` DISABLE KEYS */;
INSERT INTO `login_session` VALUES (1,1,'13142ada-86fb-492b-b751-6d8d4344950b','4549a236-69dd-4b57-9930-b5486cbdb12a','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-11 02:59:39'),(2,1,'faa76543-eff7-4d4d-8d5f-f5c6f78b2a52','1fd9b384-5d84-46dc-a191-af72df589327','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-11 09:02:11'),(3,2,'ef915711-28d2-4a65-bdab-576f89fcfdf8','cb2bbbea-7e20-4119-9e00-bd69b6cbab06','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-11 09:18:35'),(4,1,'d76e3b63-1778-4fb4-9f2f-d9e2e2513910','8f87a6c2-7375-4123-868b-4ffcb166fa60','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-11 09:20:52'),(5,1,'45249c56-ff04-473b-aba2-0a5f3628b3e0','165b778c-ab78-40c4-9b43-14925e7f206d','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-11 09:25:15'),(6,1,'7b9f14e0-4849-413e-914d-784cf98a3f10','4f5a413d-7f1e-4dc7-b332-9bed93b60399','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-11 09:25:54'),(7,1,'339ebede-3d66-4716-ab36-d9e3a598737b','3c4a9ec1-614a-4ed8-bd59-0c9e4f064e17','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-11 09:43:18'),(8,1,'4479311c-9adf-4454-b2f7-149ff29c40e7','30538128-b001-4e77-8fe2-74b979bbeedf','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-11 10:01:14'),(9,1,'7673b876-dd99-4557-9869-8ea6e3f067c4','f463da8c-f0cf-4fdc-bb7c-6eaafa1ceda6','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-11 10:41:37'),(10,1,'1904332d-ac90-4d7e-b3b6-555e131d8266','96fba142-64bd-414a-aa7b-91959c94cd26','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-11 10:49:04'),(11,1,'70edd5f5-e80a-4501-8e52-a9d3c12c0992','57cb85c8-a0db-4d04-aa38-4e71c13860ae','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-11 11:01:25'),(12,1,'7da5c01d-a207-408b-b16e-f3852cd09c6f','292ff6dd-f17f-492e-92fd-6fa867b97220','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-11 11:12:40'),(13,1,'263b28ad-0c2f-4de3-aa90-22a0231dc35d','e615fb52-d340-4d93-ac30-42249075a8dc','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-11 11:22:14'),(14,1,'453d56db-1d7c-4790-a24c-f40974f530be','9345bcd2-aa0b-4a3b-a8e8-0f9ef86da662','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-11 11:30:34'),(15,1,'23f60aa6-2f9c-42b8-9704-c4f973db9228','c48bf1ef-b800-4391-8db0-d2761e65a4ce','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-11 15:12:15'),(16,1,'cd021bf4-3aa1-48c2-8745-e6cde233e617','44be0291-8208-4638-a2b4-80662bbd9958','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-11 15:46:08'),(17,1,'f5485bdd-dbc9-410a-8dba-fee02ecb7ff0','c6c87907-f871-40d4-9fbb-ef19b1aeabec','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-12 03:01:59'),(18,1,'53a5b16d-20e2-489d-990d-d6897c01fc6f','5027bcf4-3e2d-4ff0-8edd-e4ee7d5d593b','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 01:39:30'),(19,1,'1b16c0f8-a941-49d2-8609-9ac9bd522036','cb2859f4-511c-48c5-b89f-fd0c7a4b3fd1','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 01:39:49'),(20,1,'0f265732-3ec1-4cfb-bbd0-bd872f6bcafc','0297b374-49fa-4ba0-aa12-8c2ff112642f','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 02:50:28'),(21,1,'957a0c1a-e556-4437-9f33-fb0251d6f9c3','9b4503ce-86dd-49df-916e-6e6ffd20074a','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 02:50:35'),(22,1,'ba829a95-c0ef-4599-ada8-70e5f4d7b49d','91fa8c16-f859-4722-917f-4afabd123e3a','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 02:50:51'),(23,1,'93498eb9-4ac4-459c-b21b-a402ad366e59','bf55bf77-a06f-4ee2-acb0-3cc2daae4278','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 02:51:09'),(24,1,'27d60990-b2a8-429d-a2af-1764061b3da7','d447fb03-392f-46f0-ae73-80bbae885269','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 02:51:17'),(25,1,'e6039cc8-0b17-4f39-a695-b1760547a300','2317aa86-ceb3-4ed5-8ac3-24f99d30499f','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 03:32:22'),(26,1,'1adccdd0-21de-4b12-b3ac-1545c7d63c86','c1cd3d3f-efa1-4c26-9cd6-eabd7f58e7c1','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 03:43:40'),(27,1,'8995f10c-ee4a-41c9-b041-f763d32cf88e','2614d16b-fe9e-43da-bb6e-5c51501cdebe','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 03:50:09'),(28,1,'1de9b893-1736-439f-aab6-f8fb03b03eb1','c4e11899-c0a5-4dd5-808d-3ca0322b3726','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 03:52:04'),(29,1,'3f6256f4-8af1-4248-9dd9-afb879b7c487','5920250b-8221-43f7-a4c5-8c67d8477800','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 04:13:43'),(30,1,'d5219d9c-add1-43f0-a2ea-2445a9324015','7bf6c56e-10f2-48f2-b957-69a6253e5a10','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 04:17:35'),(31,1,'81ac34e6-e887-4a20-931c-204b3c95478d','7097fb04-97ce-4251-ae0a-aa9bb87201af','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 04:19:59'),(32,1,'4dc0a822-3463-4652-a7f9-3674e97beda1','9ff09a72-6406-47f1-b3a1-603094b207c9','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 04:20:32'),(33,1,'06763d91-d4a1-4f88-804a-a576875136a5','d43713a3-0f89-4130-8837-a2cc9005a3b2','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 04:22:08'),(34,1,'62699db8-b403-4697-8d52-341723d6f7b1','31b72a39-004c-4000-ab0b-ec8007509d54','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 04:23:04'),(35,1,'89ce0879-fef1-4b42-aab9-f8b1e7b4a45b','39e72cc2-4acf-44cc-853e-1d9599a3c4c4','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 04:23:20'),(36,1,'b1d265e6-e68e-4fcf-aae7-19e806954486','b65d088e-0029-45ec-a642-621f40eb689c','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 04:23:36'),(37,1,'2e0dca77-b61c-452b-801c-a901c40f5074','29fc1345-3d47-48af-88af-06a251455d8c','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 04:24:33'),(38,1,'c44b9477-1747-460c-ae72-1f025a278d98','19e34c86-2de4-4af9-a8e1-6ebaec99532e','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 04:37:54'),(39,1,'8f8e444e-9e98-400f-8c73-9b4fa2e4f160','9b3a8fca-c4fc-4f74-ac8b-45f1acf5ce9d','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 04:42:38'),(40,1,'36d0c227-2dd3-44a9-a8ba-304cbc4a3754','415b5d76-e0a5-4d4a-8c35-8ccf5595ee28','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 04:46:43'),(41,1,'1243e6d1-5b47-46c7-8d9f-006be6fb8704','c703bbef-c338-4e17-8e78-585ada2bfaec','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 04:50:57'),(42,1,'9ca436f3-d80f-4593-acde-51dd5710c709','86086a97-2c99-43c8-9e16-7f8e2a5b326e','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 04:54:26'),(43,1,'b3fd4bae-be2e-4f04-8a9e-17d25644167c','4cdc5c36-7df8-4884-a0cf-ba1dfbe90395','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 06:44:22'),(44,1,'a7352cc5-ce10-4722-b35b-9aa7b1a5a99f','ec632cb0-f79c-4457-85ba-a6349268a15b','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 06:46:09'),(45,1,'3ff7704b-d5b5-4bde-8e2e-5eef92117390','d9269abf-08f9-4b4a-87bf-78435cf1b085','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 06:49:09'),(46,1,'91bba85f-8716-4c61-8ffe-ed5fa76b70e0','8b0b2a31-ee6f-47e5-be7f-aefde9e5ee22','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 06:50:46'),(47,1,'b6a3f9dc-e57f-4dc3-907f-6e28122a6cd9','f58ea9f1-65c3-4b1e-a2b2-d8b6f0ddc868','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 06:54:40'),(48,1,'8dc4ba0a-6d0b-40d2-b21b-4a67a81d47e2','57b03303-0f81-47a4-b132-7c2445fa903e','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 06:55:48'),(49,1,'cbd3d18d-c2a5-4e1e-8ceb-e7a010bb3859','5cc57e4a-2da4-47e7-9ae0-85e3b91ccbfd','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 06:57:22'),(50,1,'c3025311-5fee-42df-9eea-97c17b868fbb','dbf549b5-d3aa-407a-b831-1231629472f9','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 07:00:40'),(51,1,'7d078ae6-9589-498f-af5d-7b758677a53c','66beb539-eb17-4485-aed0-13b1bb9e2992','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 07:00:58'),(52,1,'b09cf54e-7e8b-4b17-9057-a1bc939ff562','99d686c8-306c-47e9-b70a-c68220d36747','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 07:01:38'),(53,1,'ee0c0891-73c0-4b3e-82ff-b13cce7c74ac','a308b67c-7c6b-435f-af52-16122a20dad6','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 07:07:27'),(54,1,'ed2f7fe2-5264-422c-9921-b7aac9449f5c','b7e782c9-673e-43ad-a4ac-f86ee5b0f1d6','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 09:32:56'),(55,1,'015d667f-a9cd-475a-9419-63f777b3576d','2cd36969-19eb-47ef-92db-7cde46b378c3','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-07-14 15:58:54'),(56,1,'746be400-1673-4018-9ff4-1693796dbbaa','1de96de8-383e-4392-bc49-8afd6b422a36','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-17 09:37:16'),(57,1,'4dd7866e-ae96-407f-8445-f74ac87f97a3','0d65421c-3cb1-4c68-8b7f-621f072fe5be','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-17 09:37:25'),(58,1,'f7a3ee4d-944f-413b-85d5-72a0739cdbe4','e6cb37aa-18c0-4fac-90b2-6ddd5793d134','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-17 09:46:08'),(59,1,'edb40916-ac47-4684-bb3a-bef469072520','5f9f246d-81db-4c1d-bf36-3d5ee9ae48e9','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-17 16:08:26'),(60,1,'7d63dec9-2f96-406a-a915-690d0bbdf1be','4d41b922-f856-4a9b-b6a5-70c95155b265','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-18 07:30:41'),(61,1,'a2d15cff-f67d-4010-b47c-61e76e6fddf8','5799692c-5a5c-4360-91a0-688cfbc3f9ac','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-18 08:01:38'),(62,1,'395872a9-b315-4adb-8883-a0c0162811d8','2d76fc11-d709-4e1e-955d-c0f5da5db45b','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-18 10:06:08'),(63,1,'4e518689-4153-4b6a-a220-d6da33678256','cbd60e28-6422-4de7-8145-39b55f9bdc46','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-18 10:33:56'),(64,1,'a6475763-0cf6-4e54-85e3-84326f53cbd6','b81d5a52-16b7-4bb6-8401-a387481663f9','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-18 10:42:20'),(65,1,'9e5219e1-d5a2-4536-afaa-ec1e2077e7d1','c42603e1-4726-46ba-90f6-42172bbb9e83','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-18 10:47:55'),(66,1,'b11714c2-30b0-493c-b650-17cdc23ab0a6','5f810b63-e75b-4daf-b333-d4766fba2dbf','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-18 10:50:58'),(67,1,'75bfad6e-024f-48f4-8a2d-c0058c5e7865','81400af4-24ca-436d-a721-ba164023e535','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-18 10:52:20'),(68,1,'5409786e-0b45-498d-8099-7c862e9119a3','a6678f69-b759-40b4-a495-ca0d9cbb8b72','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-18 11:06:49'),(69,1,'00ab6853-3379-4f0b-a0a4-c5fe8913d123','0b680a32-cd3b-452b-bc36-26ccd2195b1d','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-18 11:07:55'),(70,1,'7e3e2873-3ed8-4543-8b5e-f72676b210e6','564f0543-0ac9-4896-93a3-ed4e58eb4745','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-18 11:40:51'),(71,1,'ac56e228-e485-4abe-8864-37717fd8b97d','c62ad9b1-e32e-456a-af5e-174ace5f453f','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-18 11:55:24'),(72,1,'686424af-977d-4174-adda-0867c0432568','6ef621d4-de03-4cf7-9888-ddccbcfa7f7e','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-18 15:33:27'),(73,1,'55b608a4-b79a-40ba-9418-7e558725d86c','ebdab32c-ad43-403d-a47f-93d6101b1fcf','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-18 15:49:03'),(74,1,'c67a2f47-cadb-49b5-b693-25577ef2e84a','d2cf9a47-6b84-4287-b59d-38c0cfff3e8b','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-19 02:10:01'),(75,1,'6ac4f11c-1b2c-426f-8963-d9149e0f7036','8d7dee88-2fe9-4ebc-afcf-d273a7cf8cc5','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-19 02:12:52'),(76,1,'5953c509-d6cc-4f0d-b7b6-70d229600097','71ebaf0a-95d1-47d1-8193-1bbb738cf552','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-19 02:13:36'),(77,1,'80187daa-4c1d-4550-b732-6a5939b497e3','34b4990a-2b82-4cd9-b9ad-72b05b3d4087','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-19 02:14:53'),(78,1,'411f641b-f1fe-470f-b05c-958250b2dccc','7a9addb1-607c-46cf-964b-a9878f383e94','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-19 02:24:22'),(79,1,'69c15fc8-8c62-4924-b357-e7c5e0cdcf0b','99b9fb63-1d93-44c5-851f-71184e21fcfb','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-19 03:02:36'),(80,1,'1cf4ca6d-341e-47fa-bf0e-6b4b2e4a4c1e','0978f37e-afa9-400b-9874-bcff9a74f273','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-19 03:33:20'),(81,1,'448706f5-d84b-408b-afc7-032767980abe','0de60f30-096d-4b3d-8f85-006d5823a5f1','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-19 03:33:27'),(82,1,'d915f2c1-5948-4dc2-a076-3c55ff2e74fc','3a7b0f83-c383-4008-87a2-7ab26cd49c9a','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-19 03:43:27'),(83,1,'4637cff8-f3fd-4082-9d8f-5ddd8e9d44fe','fdbda1c7-483f-4e56-8549-deb0ec4963ae','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-19 03:43:47'),(84,1,'737152a6-1f8e-4699-92a6-1433d1676675','93f6aca5-0d3c-42bc-a737-b69cfe9fe1e2','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-19 11:43:23'),(85,1,'243092b7-fa0b-40a8-be76-e5839f6be2e9','421801e0-864a-45ac-a0bf-b350e52393a6','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-19 11:57:41'),(86,1,'d45c6a88-e8c7-413f-88de-e774e9652249','9c098e0a-e622-41bf-aad2-e517137618f1','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-19 14:11:23'),(87,1,'a5e61613-6413-4e8b-a988-248d9ff8b395','33b95db9-2a7e-4706-9cd2-5dbc108d1698','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-19 14:31:57'),(88,1,'38894b4c-f380-4f97-90d4-cb93f0640dfe','8f276160-e912-462f-b993-872248716d5d','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-19 14:41:16'),(89,1,'c1a7e9c6-4924-42ec-8dee-846746bc8122','52b71126-ebd3-426e-a5ed-b7d62294c45e','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-19 15:21:01'),(90,1,'d9bb375b-4a32-428e-9e08-934cfdb16960','a4d80826-9324-4ec9-9bcb-8255c997caff','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-19 15:52:32'),(91,1,'21e2ecc6-3a02-49c3-af9e-fb7d1681cd86','bf58b77a-2ee5-41d9-9d5b-2924f2b11aba','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-19 16:05:50'),(92,1,'44b17288-e542-4dc7-8996-7d07fa3aac95','be986656-d0ed-4a0a-b248-1d46451f179f','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-20 00:36:00'),(93,1,'dab7b7a4-a2ce-40be-b3f0-600ba32a163a','0a9bd7ad-e15c-4486-bd3e-f144818ab718','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-20 00:42:18'),(94,1,'a5ab70d0-fc4b-4913-a80e-6f8cb20c438b','a375a3f2-cf48-4812-8ed2-bb57911c0049','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-20 14:23:38'),(95,1,'f9b57aa7-b0b2-472e-b0f0-61c6d5ab0ff2','a3bcfa42-4391-4910-a0dc-d5fec9867434','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-20 14:35:26'),(96,1,'fd370158-a54f-46de-8a1c-9169b4571a20','bd40d1e7-4f35-4ded-b645-dbfcd13a37db','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-20 14:46:43'),(97,1,'609bbb74-477b-4480-8837-988380cbc6cb','63c2c55b-280a-4970-ba2e-b5b38bc6cf91','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-20 15:14:45'),(98,1,'5e234b20-1061-416e-95ca-20caeaab75f3','d25478f3-53ab-413a-a5a4-1468b0cf65bc','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-20 15:30:25'),(99,1,'bbac6299-3758-4501-9c88-89f048d4a03c','e0968e9d-0b26-42f1-956b-9bce83560370','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-21 01:34:15'),(100,1,'0aa26f22-2e5a-4688-852d-ed1447488e99','378c8285-85af-4a1c-98b8-f154a6c8dfcf','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-21 15:58:16'),(101,1,'11ad5fed-ed9d-4102-a81a-e99d3bf9b85c','61264e0d-37f2-4795-8b57-af15e0e25e50','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-21 17:13:29'),(102,1,'be8cacc2-fbc1-4814-91cb-6da1ad1249a2','2dadf006-0374-4053-9ee4-a3ac6f1d54fb','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-22 02:17:50'),(103,1,'d9c17956-b061-4390-8123-d5920f56015f','4ee3ba5c-44dd-4190-a6c4-3e6fe117e474','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-22 02:50:18'),(104,1,'bbbedf4f-deb4-45e6-aaba-fe333e33c1ac','d7cdac32-b6ff-44dd-9704-a26cc6801888','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-22 03:04:01'),(105,1,'e4b84931-4c6d-4739-b8d5-92f376c715fe','adb620c2-0c54-442b-a1c8-e6b668bb5894','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-22 03:59:27'),(106,1,'1d30a2f6-02f9-4078-8aa5-c27665ee58b2','694513e5-c93f-461f-b96b-4de7ffec3e08','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-22 11:33:50'),(107,1,'fac2e288-2cfb-4c51-8f50-3cdfbffbb772','ea1c056b-110f-48ed-a44c-c5f455cbe776','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 09:46:21'),(108,1,'33dc6f72-d0c5-4eb3-9db2-59551092c4ff','1140342a-303b-4d2f-9c4b-4634a873fa92','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 12:29:03'),(109,1,'19cde2a5-0148-48d7-ac09-52b1440f7c7c','61872282-28e5-4388-8d3c-2be9ba8db06e','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 12:31:36'),(110,1,'42cbd7e6-f9d7-414c-858c-c6e427ad60f2','5c75a5b1-c964-4da8-907f-f39f6b892759','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 12:32:12'),(111,1,'edf9b5c9-a52b-4112-a8ed-74ed6477e9a5','db89258c-c266-4bd9-8c15-adec6429217a','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 12:35:06'),(112,1,'8ec0c04f-8bf0-41b3-ba8e-cef8791de06f','fb4a6638-005c-4c57-80f3-c6d6d8728b02','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 12:36:08'),(113,1,'ddad9cfa-63f7-4346-a022-618c5ece342b','4b4b2d27-0756-4917-9413-df82fa7d2317','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 12:36:52'),(114,1,'97bbff4c-3689-4799-9e88-b4b892831512','bd1157fd-ece3-43e7-8cec-535f71ec800e','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 12:40:47'),(115,1,'2d69ef2c-acaf-4fdd-8157-41fb29158c59','0b5cc811-9871-455b-9a72-6d22ef7d03ce','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 12:49:19'),(116,1,'236c6f25-e6a0-4326-9f8d-97529e71af1b','d782cf88-bbf7-4301-88aa-b1776f6775c8','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 12:51:04'),(117,1,'13e62626-a372-4f69-b271-caebb3f06535','f5a446f6-c970-4fc3-aa06-bcf8c3c7a126','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 12:51:39'),(118,1,'012a253f-dfc2-4850-86db-2a37ece1e912','dc706f71-e767-43b1-8c83-0e13562a9563','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 13:38:11'),(119,1,'e5e2412e-bf1a-4b24-b4aa-491ec7a72ead','cfbf5611-4813-4e92-b978-0c094ca23e19','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 13:53:56'),(120,1,'871e4c71-dc88-444a-9568-ad5969f7bb00','a0377f9e-7751-4493-9cea-542a331cd306','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 13:57:15'),(121,1,'0c780ab8-233e-4650-bcc9-cc317c71c5a1','c65f0266-5799-4d39-9f09-c85b525b9ecb','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 14:00:35'),(122,1,'e1aa260b-f08e-45af-9555-e330693832f5','e913f4df-fcdd-428e-8fa2-be7bf19cd409','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 14:01:26'),(123,1,'e087e7dd-098e-45b0-9a5a-245c273977dc','83c8db83-bfb8-4636-bb04-422d73755c49','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 14:06:03'),(124,1,'16765501-f4c6-4960-8657-e382688a6c6e','b0c74ad0-2a00-4a3f-9f75-3fd2625dbe53','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 14:06:23'),(125,1,'8f8df45e-4e46-4aa6-b914-e695d897b944','02aa8ec4-dabe-4f02-a16c-9910d400febd','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 14:24:22'),(126,1,'1258201c-f58a-48ce-bc0a-bd88eb5a7ab7','f1aa88d7-2c18-4789-b262-a74a26f08f44','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 14:30:31'),(127,1,'2090add3-6850-455c-a08c-576496bad793','7345e22f-2ccf-4cbe-8ced-d581497b21bc','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 14:34:11'),(128,1,'17619cdc-62c4-47d5-a9fb-4026642fb5f7','cc0e761c-719b-4e97-8c30-e83cd438c1cb','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 14:34:40'),(129,1,'5a57d2b3-8aed-4ea4-bf7b-9ac99b1181da','60f397f0-a3df-4e52-90a7-907978cd889a','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 14:36:41'),(130,1,'dce004a3-3994-460a-a83a-13e38ec3384e','f803ea66-3534-4653-b303-e359084fa3c4','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 14:38:15'),(131,1,'0b306507-c8a8-4cbf-b7ac-846d09896dc0','50e11a0d-0686-430d-9e60-94f1eb0c8e8c','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 14:40:10'),(132,1,'b924ce3e-96be-43e5-9ea6-f4e429fca200','2b49d2c9-ac48-4db3-a827-b9518528dbcb','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 14:44:17'),(133,1,'badfbed2-10e9-4baa-b0a3-d1b324c150d6','62c983ea-5194-4b31-ba89-ddf64a745c6b','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 14:45:28'),(134,1,'792195ec-bc67-4ceb-bd57-f902e6d4dc3d','4d0ecf23-3d52-42fe-9a0e-50a91208c50a','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 14:49:37'),(135,1,'f7aff175-6695-480d-ae1d-a695a94eba55','b79c55ad-b8cc-4c1d-affc-264f88fe37e6','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 14:51:44'),(136,1,'61ee1987-673c-4c66-8bb1-a76341ee8eb0','81c91138-bead-4219-bc66-8fd05545384e','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 15:17:46'),(137,1,'b94de9a0-7522-496b-a681-d83235d08c05','943ecd2a-d2fc-4c25-9d33-837bbb66440f','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 15:29:12'),(138,1,'195414f3-0079-47e5-8cd5-11c057ebcd0c','3c191f6f-1f0a-4279-8da2-fd229f743d58','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 15:51:12'),(139,1,'e9810477-89de-4282-9f47-9a309d263aa2','86357184-33b3-43d3-bf97-1d73882147f4','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 15:54:20'),(140,1,'017f3894-87b4-4f7c-aa21-d235bc723b23','6a8171a5-6332-4e10-b9b3-d631e3ce0780','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 15:56:58'),(141,1,'e2f81415-05b5-47e0-8601-3163af6130db','ed258616-2b29-4dab-a13c-acca5b9e5f2b','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 15:59:30'),(142,1,'740212b3-561f-4915-a500-8b13f5a218fb','465cbcd1-95e6-4467-becd-c596aede9f1b','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 16:01:02'),(143,1,'696a11d8-4588-4d9b-b481-a04dd583e4bb','dfffd217-175d-4a5c-b09e-555c479155fe','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36','2020-08-23 16:03:05');
/*!40000 ALTER TABLE `login_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `level` smallint(6) NOT NULL,
  `order` smallint(6) NOT NULL,
  `parent_code` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_by` int(11) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` int(11) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uidx_Menu_Code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Menu of application';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'SystemSettings',1,999,NULL,'Cài đặt hệ thống',1,'2020-07-08 03:18:27',1,'2020-07-08 03:18:33'),(2,'Users',2,1,'SystemSettings','Người dùng',1,'2020-07-08 03:52:56',1,'2020-07-08 03:53:00'),(3,'UserGroups',2,2,'SystemSettings','Nhóm người dùng',1,'2020-07-08 04:12:37',1,'2020-07-08 04:12:40'),(4,'Sales',1,1,NULL,'Bán hàng',1,'2020-07-08 04:13:03',1,'2020-07-08 04:13:06'),(5,'Leads',2,1,'Sales','Cơ hội bán hàng',1,'2020-07-08 04:47:36',1,'2020-07-08 04:47:38'),(6,'SaleOrders',2,2,'Sales','Đơn bán hàng',1,'2020-07-08 04:57:39',1,'2020-07-08 04:57:42');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `given_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `surname` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `locale` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `time_zone` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `enabled` bit(1) DEFAULT b'1',
  `created_by` int(11) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` int(11) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `User_Username_Unique` (`username`),
  KEY `fk_user_createdBy` (`created_by`),
  KEY `fk_user_lastModifiedBy` (`last_modified_by`),
  CONSTRAINT `fk_user_createdBy` FOREIGN KEY (`created_by`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_user_lastModifiedBy` FOREIGN KEY (`last_modified_by`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='System users';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','$2a$10$bYna9PNwfi9vLoNhR..xvO3MzR/oGVY2J8xsmloYK1jga7QPev5Sy','admin@zamio.vn','0988123456','Thomas','Nguyễn','vi','Asia/Ho_Chi_Minh',_binary '',1,'2020-07-01 03:00:35',1,'2020-07-01 03:00:42'),(2,'john','$2a$10$bYna9PNwfi9vLoNhR..xvO3MzR/oGVY2J8xsmloYK1jga7QPev5Sy','jas2@gmail.com','09012342','Johnanthan','Does','en_US','Asia/Shanghai',_binary '\0',1,'2020-07-01 06:41:20',1,'2020-07-01 06:41:24'),(3,'alice','1','alice@gmail.com',NULL,'Alice','Wood',NULL,NULL,_binary '',1,'2020-07-04 16:10:47',1,'2020-07-04 16:10:47'),(4,'bob','1','bob@gmail.com','0334759686','Bob','Brown','en_US','America/New_York',_binary '',1,'2020-07-04 16:14:15',1,'2020-07-04 16:14:15'),(5,'critian','1','critian@hotmail.com',NULL,'Critia','Brain','vi',NULL,_binary '',1,'2020-07-05 14:45:50',1,'2020-07-05 14:47:02'),(6,'dan','1',NULL,NULL,'Daniel','Hauer','vi',NULL,_binary '',1,'2020-07-05 14:59:56',1,'2020-07-05 14:59:56'),(7,'emily','1','emily.nguyen@zamio.net',NULL,'Emily','Nguyễn','vi','Asia/Ho_Chi_Minh',_binary '',1,'2020-07-05 15:00:46',1,'2020-07-05 15:00:46'),(12,'ashtyn','1','ashtyn@gmail.com',NULL,'Ashtyn','Bednar','en_US','America/New_York',_binary '',7,'2020-07-06 06:57:29',7,'2020-07-06 07:37:37');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_group`
--

DROP TABLE IF EXISTS `user_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_by` int(11) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` int(11) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uidx_UserGroup_Name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Users groups';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_group`
--

LOCK TABLES `user_group` WRITE;
/*!40000 ALTER TABLE `user_group` DISABLE KEYS */;
INSERT INTO `user_group` VALUES (1,'Administrator','Nhóm người dùng quản trị hệ thống',1,'2020-07-07 15:27:55',1,'2020-07-08 11:34:34'),(2,'Sales manager','Managers of sale department',1,'2020-07-09 02:18:16',1,'2020-07-09 02:18:16'),(3,'Sale employee','Employee of sale department',1,'2020-07-09 02:29:05',1,'2020-07-09 14:45:57'),(4,'Stock manager','Manager of stock module',1,'2020-07-09 15:04:45',1,'2020-07-09 15:04:45'),(5,'Stock employee','Normal user of stock module',1,'2020-07-09 15:06:43',1,'2020-07-09 15:06:43'),(6,'Stock helper','',1,'2020-07-09 15:10:29',1,'2020-07-09 15:10:29'),(7,'Stock fixer',NULL,1,'2020-07-09 15:11:05',1,'2020-07-09 15:11:05'),(8,'Driver','Truck drivers',1,'2020-07-09 15:35:43',1,'2020-07-09 15:35:43'),(9,'Transporter',NULL,1,'2020-07-09 15:37:41',1,'2020-07-09 15:37:41'),(10,'Fan taker',NULL,1,'2020-07-09 15:37:54',1,'2020-07-09 15:37:54'),(11,'Light bulb','Brilliant',1,'2020-07-09 15:38:33',1,'2020-07-09 15:39:44'),(12,'Chrome maker',NULL,1,'2020-07-09 15:47:02',1,'2020-07-09 15:47:02'),(13,'Booker',NULL,1,'2020-07-09 15:50:02',1,'2020-07-09 15:50:02'),(14,'Water',NULL,1,'2020-07-09 15:50:24',1,'2020-07-09 15:50:24'),(15,'Rotator','Rotator',1,'2020-07-09 15:54:01',1,'2020-07-09 15:55:24');
/*!40000 ALTER TABLE `user_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_group_menu_map`
--

DROP TABLE IF EXISTS `user_group_menu_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_group_menu_map` (
  `group_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  UNIQUE KEY `uidx_Group_Menu` (`group_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Many to many relation between user groups and menus';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_group_menu_map`
--

LOCK TABLES `user_group_menu_map` WRITE;
/*!40000 ALTER TABLE `user_group_menu_map` DISABLE KEYS */;
INSERT INTO `user_group_menu_map` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(2,4),(2,5),(2,6),(5,2),(11,4),(11,5),(11,6),(15,2),(15,6);
/*!40000 ALTER TABLE `user_group_menu_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_user_group_map`
--

DROP TABLE IF EXISTS `user_user_group_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_user_group_map` (
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  UNIQUE KEY `uidx_user_group_map` (`user_id`,`group_id`),
  KEY `fk_user_user_group_map_group_id` (`group_id`),
  CONSTRAINT `fk_user_user_group_map_group_id` FOREIGN KEY (`group_id`) REFERENCES `user_group` (`id`),
  CONSTRAINT `fk_user_user_group_map_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='ManyToMany relationship table between user and userGroup';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_user_group_map`
--

LOCK TABLES `user_user_group_map` WRITE;
/*!40000 ALTER TABLE `user_user_group_map` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_user_group_map` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-03 15:17:41
