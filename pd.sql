-- MySQL dump 10.13  Distrib 8.0.19, for Linux (x86_64)
--
-- Host: localhost    Database: pd
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (43),(43),(43),(43),(43);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partner`
--

DROP TABLE IF EXISTS `partner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partner` (
  `partner_id` int NOT NULL,
  `partner_code` varchar(255) DEFAULT NULL,
  `partner_name` varchar(255) DEFAULT NULL,
  `partner_type_id` int DEFAULT NULL,
  PRIMARY KEY (`partner_id`),
  KEY `FKm4xttvug5m5q7iruv47llnbjo` (`partner_type_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partner`
--

LOCK TABLES `partner` WRITE;
/*!40000 ALTER TABLE `partner` DISABLE KEYS */;
INSERT INTO `partner` VALUES (3,'AA','American Airlines',1),(4,'5J','Cebu Pacific',1),(5,'DL','Delta Airways',1),(6,'VA','Virgin Autralia',1),(7,'TRAVELSTART','Travel Start',2),(8,'BILLIGFLUG','Billigflug',2),(9,'LOGITRAVEL','Logitravel',2);
/*!40000 ALTER TABLE `partner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partner_restriction`
--

DROP TABLE IF EXISTS `partner_restriction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partner_restriction` (
  `partner_restriction_id` int NOT NULL,
  `partner_restriction_value` varchar(255) DEFAULT NULL,
  `partner_id` int DEFAULT NULL,
  `restriction_type_id` int DEFAULT NULL,
  PRIMARY KEY (`partner_restriction_id`),
  KEY `FK1iq726orf5fn7lc3c2vc5nmpm` (`partner_id`),
  KEY `FKt7v42iuwhef66am7nc39hmaa9` (`restriction_type_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partner_restriction`
--

LOCK TABLES `partner_restriction` WRITE;
/*!40000 ALTER TABLE `partner_restriction` DISABLE KEYS */;
INSERT INTO `partner_restriction` VALUES (24,'One-Way Type',3,13),(25,'Round-Trip Type',3,14),(26,'Open-Jaw Type',3,15),(27,'Multi-City Type',3,16),(28,'Economy',3,17),(29,'Premium Economy',3,18),(30,'Business',3,19),(31,'First Class',3,20),(32,'Site Link',3,21),(33,'Shallow Link',3,22),(34,'Deep-Link',3,23),(40,'DL, AC, AF',3,37),(41,'5J, VA',3,38),(42,'4 Hours',3,39);
/*!40000 ALTER TABLE `partner_restriction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partner_type`
--

DROP TABLE IF EXISTS `partner_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partner_type` (
  `partner_type_id` int NOT NULL,
  `partner_type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`partner_type_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partner_type`
--

LOCK TABLES `partner_type` WRITE;
/*!40000 ALTER TABLE `partner_type` DISABLE KEYS */;
INSERT INTO `partner_type` VALUES (1,'Carrier'),(2,'OTA');
/*!40000 ALTER TABLE `partner_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restriction`
--

DROP TABLE IF EXISTS `restriction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restriction` (
  `restriction_id` int NOT NULL,
  `restriction_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`restriction_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restriction`
--

LOCK TABLES `restriction` WRITE;
/*!40000 ALTER TABLE `restriction` DISABLE KEYS */;
INSERT INTO `restriction` VALUES (10,'Trip Type'),(11,'Cabin Type'),(12,'Link Type'),(35,'Interline'),(36,'Lay-Over');
/*!40000 ALTER TABLE `restriction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restriction_type`
--

DROP TABLE IF EXISTS `restriction_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restriction_type` (
  `restriction_type_id` int NOT NULL,
  `restriction_type_name` varchar(255) DEFAULT NULL,
  `restriction_id` int DEFAULT NULL,
  PRIMARY KEY (`restriction_type_id`),
  KEY `FKlwrcy4veg6xvka6x910io2gyg` (`restriction_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restriction_type`
--

LOCK TABLES `restriction_type` WRITE;
/*!40000 ALTER TABLE `restriction_type` DISABLE KEYS */;
INSERT INTO `restriction_type` VALUES (13,'One-Way',10),(14,'Round-Trip',10),(15,'Open-Jaw',10),(16,'Multi-City',10),(17,'Economy',11),(18,'Premium-Economy',11),(19,'Business',11),(20,'First-Class',11),(21,'Site-Link',12),(22,'Shallow-Link',12),(23,'Deep-Link',12),(37,'Allowed Interlines',35),(38,'Prohibited Interlines',35),(39,'Max Allowed Lay-Over',36);
/*!40000 ALTER TABLE `restriction_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-28 11:06:32