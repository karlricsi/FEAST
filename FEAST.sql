-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: feast
-- ------------------------------------------------------
-- Server version	5.7.23

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
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `food_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL,
  `food_name` varchar(50) NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`food_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `menu_categories` (`category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,1,'Csigaleves',400),(2,1,'Ultraderm krémleves',340),(3,2,'Rántott kefe',730),(4,2,'Töltött mák',850),(5,2,'Totymékos kavics',690),(6,2,'Zanzásított marhapörkölt',1060),(7,3,'Papírgaluska',340),(8,3,'Primör nokedli',420),(9,3,'Sült tarhonya',390),(10,4,'Kakaós csuszpájzreszelék',550),(11,4,'Vaníliás epe',620),(12,5,'Sörmentes alkohol 0,5l',300),(13,5,'Instant víz 3dl',160);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_categories`
--

DROP TABLE IF EXISTS `menu_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu_categories` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_categories`
--

LOCK TABLES `menu_categories` WRITE;
/*!40000 ALTER TABLE `menu_categories` DISABLE KEYS */;
INSERT INTO `menu_categories` VALUES (1,'Leves'),(2,'Főétel'),(3,'Köret'),(4,'Desszert'),(5,'Ital');
/*!40000 ALTER TABLE `menu_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_items` (
  `order_id` int(11) NOT NULL,
  `food_id` int(11) NOT NULL,
  `price` double NOT NULL,
  `quantity` int(11) NOT NULL,
  KEY `food_id` (`food_id`),
  KEY `order_id_idx` (`order_id`),
  CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (3,1,400,1),(3,10,550,1),(2,1,400,1),(2,5,690,1),(2,7,340,1),(4,4,850,1),(4,7,340,1),(5,13,160,1),(6,2,340,1),(6,3,730,1),(6,11,620,1),(7,9,390,1),(7,10,550,1),(7,13,160,1),(8,1,400,1),(8,4,850,1),(8,7,340,1),(9,3,730,1),(9,9,390,1),(9,11,620,1),(9,13,160,1),(10,5,690,1),(10,7,340,1),(10,10,550,1),(10,13,160,1),(11,4,850,1),(11,11,620,1),(12,1,400,1),(12,5,690,1),(13,2,340,1),(13,4,850,1),(13,9,390,1),(13,11,620,1),(13,13,160,2),(14,1,400,1),(14,4,850,1),(14,10,550,1),(15,2,340,1),(17,2,340,1),(15,11,620,1),(15,13,160,1),(19,1,400,1),(19,4,850,1),(16,3,730,1),(16,7,340,1),(16,10,550,1),(16,13,160,1),(18,2,340,1),(18,5,690,1),(18,9,390,1),(18,10,550,1),(17,13,160,1),(21,3,730,1),(21,9,390,1),(20,2,340,1),(20,4,850,1),(20,9,390,1),(20,11,620,1),(22,3,730,1),(22,7,340,1),(25,1,400,1),(25,4,850,1),(25,10,550,1),(20,13,160,1),(23,2,340,1),(23,5,690,1),(23,8,420,1),(23,10,550,1),(23,12,300,1),(29,2,340,1),(29,4,850,1),(29,10,550,1),(30,1,400,1),(30,3,730,1),(30,7,340,1),(30,13,160,1),(31,6,1060,1),(31,9,390,1),(31,12,300,1),(32,5,690,1),(32,7,340,1),(32,12,300,1),(33,2,340,1),(33,5,690,1),(33,7,340,1),(33,11,620,1),(34,2,340,1),(34,3,730,1),(34,7,340,1),(34,12,300,1),(35,3,730,1),(35,9,390,1),(35,10,550,1),(35,12,300,1),(36,1,400,1),(36,4,850,1),(36,7,340,1),(36,10,550,1),(38,3,730,1),(38,9,390,1),(38,12,300,1),(39,1,400,1),(39,6,1060,1),(39,7,340,1),(39,12,300,1);
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `closed` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (2,3,'2020-10-18',1),(3,2,'2020-10-18',1),(4,4,'2020-10-18',1),(5,5,'2020-10-18',1),(6,1,'2020-10-19',1),(7,5,'2020-10-19',1),(8,3,'2020-10-19',1),(9,2,'2020-10-19',1),(10,2,'2020-10-20',1),(11,4,'2020-10-20',1),(12,5,'2020-10-20',1),(13,3,'2020-10-20',1),(14,1,'2020-10-20',1),(15,2,'2020-10-21',1),(16,5,'2020-10-21',1),(17,1,'2020-10-21',1),(18,3,'2020-10-21',1),(19,4,'2020-10-21',1),(20,2,'2020-10-22',1),(21,4,'2020-10-22',1),(22,5,'2020-10-22',1),(23,3,'2020-10-23',1),(25,1,'2020-10-23',1),(29,2,'2020-10-24',1),(30,4,'2020-10-24',1),(31,5,'2020-10-24',1),(32,1,'2020-10-24',1),(33,2,'2020-10-25',1),(34,4,'2020-10-25',1),(35,3,'2020-10-25',1),(36,2,'2020-10-26',1),(38,4,'2020-10-26',1),(39,1,'2020-10-26',1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Szög Elek'),(2,'Erős M. Ágnes'),(3,'Lapos Elemér'),(4,'Kala Pál'),(5,'Kia Bálint');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-26 16:18:43
