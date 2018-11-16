CREATE DATABASE  IF NOT EXISTS `impacttracker` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `impacttracker`;
-- MySQL dump 10.13  Distrib 8.0.12, for macos10.13 (x86_64)
--
-- Host: localhost    Database: impacttracker
-- ------------------------------------------------------
-- Server version	8.0.12

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
-- Table structure for table `commitments`
--

DROP TABLE IF EXISTS `commitments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `commitments` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_u_id` int(11) NOT NULL,
  `c_r_id` int(11) NOT NULL,
  `c_start_date` date NOT NULL,
  `c_end_date` date NOT NULL,
  `c_amount` int(11) DEFAULT NULL,
  PRIMARY KEY (`c_id`),
  KEY `commitments_u_fk` (`c_u_id`),
  KEY `commitments_r_fk` (`c_r_id`),
  CONSTRAINT `commitments_r_fk` FOREIGN KEY (`c_r_id`) REFERENCES `reductions` (`r_id`),
  CONSTRAINT `commitments_u_fk` FOREIGN KEY (`c_u_id`) REFERENCES `user` (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commitments`
--

LOCK TABLES `commitments` WRITE;
/*!40000 ALTER TABLE `commitments` DISABLE KEYS */;
INSERT INTO `commitments` VALUES (1,1,1,'2018-12-01','2018-12-31',18);
/*!40000 ALTER TABLE `commitments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `impacts`
--

DROP TABLE IF EXISTS `impacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `impacts` (
  `i_id` int(11) NOT NULL AUTO_INCREMENT,
  `i_r_id` int(11) NOT NULL,
  `i_impact_per_unit` decimal(10,4) NOT NULL,
  `i_impact_unit` varchar(45) NOT NULL,
  `i_impact_type` varchar(45) NOT NULL,
  `i_reduction_unit` varchar(45) NOT NULL,
  PRIMARY KEY (`i_id`),
  KEY `impacts_r_fk` (`i_r_id`),
  CONSTRAINT `impacts_r_fk` FOREIGN KEY (`i_r_id`) REFERENCES `reductions` (`r_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `impacts`
--

LOCK TABLES `impacts` WRITE;
/*!40000 ALTER TABLE `impacts` DISABLE KEYS */;
INSERT INTO `impacts` VALUES (1,1,2000.0000,'gallon','water use','pound'),(2,3,0.0004,'ton','co2 emissions','mile'),(3,4,2.3000,'ton','co2 emissions','ton');
/*!40000 ALTER TABLE `impacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reductions`
--

DROP TABLE IF EXISTS `reductions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `reductions` (
  `r_id` int(11) NOT NULL AUTO_INCREMENT,
  `r_reduction` varchar(45) NOT NULL,
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reductions`
--

LOCK TABLES `reductions` WRITE;
/*!40000 ALTER TABLE `reductions` DISABLE KEYS */;
INSERT INTO `reductions` VALUES (1,'vegan'),(2,'vegetarianism'),(3,'driving'),(4,'palm oil consumption');
/*!40000 ALTER TABLE `reductions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_name` varchar(45) NOT NULL,
  `u_password` varchar(45) NOT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Bogus123','password1');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-16 17:50:42
