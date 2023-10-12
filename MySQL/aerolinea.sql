-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: aerolinea
-- ------------------------------------------------------
-- Server version	5.7.43-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (41555444,'Perez','Parana','perez@gmail.com','1998-10-19','Luis',41555444,'2025-05-05'),(42565474,'Morales','Santa fe','morales@gmail.com','1997-11-12','Daniel',41565474,'2026-07-06'),(42568478,'Lopez','Mendoza','lopez@gmail.com','1993-11-12','Jose',41568478,'2027-08-06');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `pasaje`
--

LOCK TABLES `pasaje` WRITE;
/*!40000 ALTER TABLE `pasaje` DISABLE KEYS */;
INSERT INTO `pasaje` VALUES (1,173500,1,41555444,1);
/*!40000 ALTER TABLE `pasaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `presupuestos_entregados`
--

LOCK TABLES `presupuestos_entregados` WRITE;
/*!40000 ALTER TABLE `presupuestos_entregados` DISABLE KEYS */;
INSERT INTO `presupuestos_entregados` VALUES (1,173500,41555444,1);
/*!40000 ALTER TABLE `presupuestos_entregados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `vuelo`
--

LOCK TABLES `vuelo` WRITE;
/*!40000 ALTER TABLE `vuelo` DISABLE KEYS */;
INSERT INTO `vuelo` VALUES (1,'Mendoza','registrado','2023-10-13 10:15:00.000000',9,15,'Sauce viejo','nacional'),(2,'Brasil','reprogramado','2023-10-14 23:30:00.000000',9,15,'Sauce viejo','internacional'),(3,'Buenos Aires','cancelado','2023-10-12 12:15:00.000000',9,15,'Sauce viejo','nacional');
/*!40000 ALTER TABLE `vuelo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-12 19:20:06
