-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: 20161_service_g2
-- ------------------------------------------------------
-- Server version	5.7.9

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nrodoc` int(11) DEFAULT NULL,
  `nombre` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `apellido` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `localidad` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `direccion` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `telefono` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `mail` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `idusuario` int(11) DEFAULT NULL,
  `habilitado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,1,'Lucas','Avelda?o','Malvinas Argentinas','Londres 312','153214211','juangabrielferreyra93@gmail.com','2016-04-29 23:45:10',0,1),(2,2,'Agustina','De Napoli','Tigre','Tacuari 342','152315213','juangabrielferreyra93@gmail.com','2016-04-29 23:54:42',0,1),(3,3,'Robert','Quilmes','Malvinas Argentinas','Guayaquil 932','153524252','juangabrielferreyra93@gmail.com','2016-05-03 19:57:48',0,1),(4,4,'Claudia','Alfonso','San Isidro','Stephenson 932','1543265223','juangabrielferreyra93@gmail.com','2016-05-03 19:57:48',0,1),(5,5,'Lucas','Guayco','bella vista','gaspar campos','1565545432','juangabrielferreyra93@gmail.com','2016-06-03 19:01:11',0,1),(6,37360567,'Martin','Nuñez','Malvinas Argentinas','Miraflores 123','1546477879','madarnu@hotmail.com','2016-06-07 11:47:02',0,1);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado`
--

DROP TABLE IF EXISTS `estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `detalle` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `destalle_largo` longtext COLLATE latin1_spanish_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado`
--

LOCK TABLES `estado` WRITE;
/*!40000 ALTER TABLE `estado` DISABLE KEYS */;
INSERT INTO `estado` VALUES (1,'NUEVO','Producto a reparar ingresado'),(2,'PRESUPUESTANDO','En proceso de presupuestado'),(3,'PRESUPUESTADO','Producto listo para informar'),(4,'INFORMADO','Producto en espera de aceptacion'),(5,'ACEPTADO','Listo para reparar'),(6,'ASIGNADO','Un tecnico se decide asignar una orden'),(7,'REPARADO','Listo para avisar al cliente'),(8,'IRREPARABLE','Listo para comunicar al cliente que es irreparable'),(9,'RECHAZADO','Producto rechazado por el cliente'),(10,'AVISO DE RETIRO','Ya se informo al '),(11,'ENTREGADO','Ya se realizo la entrega el producto al cliente'),(12,'NO ENTREGADO','Se intento realizar la entrega al cliente y no se pudo');
/*!40000 ALTER TABLE `estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flete`
--

DROP TABLE IF EXISTS `flete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flete` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nrodoc` int(11) DEFAULT NULL,
  `nombre_conductor` varchar(45) DEFAULT NULL,
  `modelo` varchar(45) DEFAULT NULL,
  `patente` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `vto_licencia` date DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `idusuario` int(11) DEFAULT NULL,
  `habilitado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flete`
--

LOCK TABLES `flete` WRITE;
/*!40000 ALTER TABLE `flete` DISABLE KEYS */;
INSERT INTO `flete` VALUES (1,1,'Ricado Nu','Toyota Hilux','FTV897','15648597','2016-08-17','2016-05-17 11:48:02',1,1),(2,2,'Nicolas Acosta','Toyota Corolla ','WRA789','157497897','2016-09-17','2016-05-17 11:50:43',1,1),(3,3,'Juan Martin Verrelli','Audi A6','ESE164','15487897','2016-10-17','2016-05-17 11:50:43',1,1),(4,3,'Nicolas Acosta','Toyota Corolla ','WRA788','157497897','2016-09-17','2016-06-03 19:03:30',1,1);
/*!40000 ALTER TABLE `flete` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hojaruta`
--

DROP TABLE IF EXISTS `hojaruta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hojaruta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idflete` int(11) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `habilitado` tinyint(1) DEFAULT NULL,
  `idusuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hojaruta`
--

LOCK TABLES `hojaruta` WRITE;
/*!40000 ALTER TABLE `hojaruta` DISABLE KEYS */;
INSERT INTO `hojaruta` VALUES (1,4,'2016-06-07 12:49:28',1,1),(2,1,'2016-06-13 19:47:27',1,1),(3,2,'2016-06-13 22:57:24',1,1);
/*!40000 ALTER TABLE `hojaruta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hojaruta_ingreso`
--

DROP TABLE IF EXISTS `hojaruta_ingreso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hojaruta_ingreso` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idhojaruta` int(11) DEFAULT NULL,
  `idingreso` int(11) DEFAULT NULL,
  `en_entrega` tinyint(1) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hojaruta_ingreso`
--

LOCK TABLES `hojaruta_ingreso` WRITE;
/*!40000 ALTER TABLE `hojaruta_ingreso` DISABLE KEYS */;
INSERT INTO `hojaruta_ingreso` VALUES (1,1,23,0,'2016-06-07 12:49:28'),(2,2,26,0,'2016-06-13 19:47:27'),(3,2,22,0,'2016-06-13 19:47:27'),(4,2,27,0,'2016-06-13 19:47:27'),(5,2,28,0,'2016-06-13 19:47:27'),(6,2,29,0,'2016-06-13 19:47:27'),(7,2,32,0,'2016-06-13 19:47:27'),(8,2,33,0,'2016-06-13 19:47:27'),(9,2,34,0,'2016-06-13 19:47:27'),(10,2,38,0,'2016-06-13 19:47:27'),(11,2,31,0,'2016-06-13 19:47:27'),(12,3,22,0,'2016-06-13 22:57:24');
/*!40000 ALTER TABLE `hojaruta_ingreso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingreso`
--

DROP TABLE IF EXISTS `ingreso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingreso` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idcliente` int(11) DEFAULT NULL,
  `descripcion_producto` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `idmarca` int(11) DEFAULT NULL,
  `idtipo_producto` int(11) DEFAULT NULL,
  `descripcion_falla` longtext COLLATE latin1_spanish_ci,
  `envio` tinyint(1) DEFAULT NULL,
  `envio_default` tinyint(1) DEFAULT NULL,
  `direccion_alternativa` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `monto_envio` decimal(15,2) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `idusuario` int(11) DEFAULT NULL,
  `habilitado` tinyint(1) DEFAULT NULL,
  `tecnico_asignado` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingreso`
--

LOCK TABLES `ingreso` WRITE;
/*!40000 ALTER TABLE `ingreso` DISABLE KEYS */;
INSERT INTO `ingreso` VALUES (22,1,'Razr i',0,1,'Se le rompio la antena Wiffi',1,1,'Guayaquil 884',300.00,11,'2016-06-04 20:24:54',0,1,NULL),(23,6,'E XT1527',0,1,'Se le rompio el display',1,1,'Mirafloes 325',300.50,11,'2016-06-07 11:50:18',0,1,NULL),(24,1,'ui',0,0,'hjuiiu',0,0,'',0.00,8,'2016-06-07 21:32:37',0,1,NULL),(25,2,'fsafsa',0,0,'dsad',0,0,'',0.00,7,'2016-06-07 21:37:29',0,1,NULL),(26,2,'Microondas',0,0,'No anda el arrancador	',1,0,'',309.00,11,'2016-06-12 15:11:20',0,1,NULL),(27,5,'Pc 3',1,1,'display',1,0,'',234.00,11,'2016-06-12 15:13:26',0,1,NULL),(28,1,'Galaxy s3',2,1,'Audio roto',1,0,'',324.00,11,'2016-06-12 15:15:22',0,1,NULL),(29,1,'Plancha electronica',4,1,'Cable roto',1,0,'',400.00,11,'2016-06-12 15:16:18',0,1,NULL),(30,1,'CPU',3,2,'No prende	',1,0,'',500.00,6,'2016-06-12 15:17:06',0,1,NULL),(31,1,'Dual DIMM 2 chanel audio',1,3,'No lee CD',1,0,'',342.00,11,'2016-06-12 15:18:48',0,1,NULL),(32,3,'Produc 1',2,4,'Descripcion de product 1',1,0,'',3232.00,11,'2016-06-12 15:20:37',0,1,NULL),(33,3,'Procuto 2',0,0,'Zarazazaza',1,0,'',5000.00,11,'2016-06-12 15:21:02',0,1,NULL),(34,4,'Procuto 4',0,0,'Desdescronoincro',1,0,'',4005.00,11,'2016-06-12 15:21:29',0,1,NULL),(35,2,'Cafetera',0,4,'No calienta el agua',0,0,'',0.00,7,'2016-06-12 15:22:19',0,1,NULL),(36,4,'R35 Slim',4,4,'A la maquina de coser se le rompiio el sistema de rodamiento donde el cable hace girar la maquina',0,0,'',0.00,8,'2016-06-12 15:23:41',0,1,NULL),(37,4,'Joistick',1,2,'No le funciona el boton start',1,0,'',3245.00,6,'2016-06-12 15:24:23',0,1,NULL),(38,1,'Borderim',3,4,'Cortadora de pasto no gira fuerte, por lo tanto no corta el pasto',1,0,'',355.00,11,'2016-06-12 15:25:29',0,1,NULL);
/*!40000 ALTER TABLE `ingreso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingreso_log`
--

DROP TABLE IF EXISTS `ingreso_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingreso_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idingreso` int(11) DEFAULT NULL,
  `idestado` int(11) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `idusuario` int(11) DEFAULT NULL,
  `habilitado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=266 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingreso_log`
--

LOCK TABLES `ingreso_log` WRITE;
/*!40000 ALTER TABLE `ingreso_log` DISABLE KEYS */;
INSERT INTO `ingreso_log` VALUES (150,22,1,'2016-06-04 20:24:54',0,1),(151,23,1,'2016-06-07 11:50:17',0,1),(152,23,3,'2016-06-07 12:24:10',1,1),(153,23,4,'2016-06-07 12:24:21',1,1),(154,23,5,'2016-06-07 12:24:24',1,1),(155,23,6,'2016-06-07 12:42:52',1,1),(158,22,3,'2016-06-07 20:03:22',1,1),(159,22,4,'2016-06-07 20:03:41',1,1),(160,22,5,'2016-06-07 20:03:44',1,1),(161,22,6,'2016-06-07 21:24:27',4,1),(163,24,1,'2016-06-07 21:32:37',0,1),(164,24,3,'2016-06-07 21:34:22',4,1),(165,24,4,'2016-06-07 21:34:32',4,1),(166,24,5,'2016-06-07 21:34:38',4,1),(167,24,6,'2016-06-07 21:35:50',4,1),(169,25,1,'2016-06-07 21:37:29',0,1),(170,25,3,'2016-06-07 21:38:17',4,1),(171,25,4,'2016-06-07 21:38:24',4,1),(172,25,5,'2016-06-07 21:38:26',4,1),(173,25,6,'2016-06-07 21:39:16',4,1),(175,26,1,'2016-06-12 15:11:20',0,1),(176,27,1,'2016-06-12 15:13:26',0,1),(177,28,1,'2016-06-12 15:15:22',0,1),(178,29,1,'2016-06-12 15:16:18',0,1),(179,30,1,'2016-06-12 15:17:06',0,1),(180,31,1,'2016-06-12 15:18:48',0,1),(181,32,1,'2016-06-12 15:20:37',0,1),(182,33,1,'2016-06-12 15:21:02',0,1),(183,34,1,'2016-06-12 15:21:29',0,1),(184,35,1,'2016-06-12 15:22:19',0,1),(185,36,1,'2016-06-12 15:23:41',0,1),(186,37,1,'2016-06-12 15:24:22',0,1),(187,38,1,'2016-06-12 15:25:29',0,1),(188,26,3,'2016-06-12 15:27:24',1,1),(189,27,3,'2016-06-12 15:28:01',1,1),(190,28,3,'2016-06-12 15:28:32',1,1),(191,29,3,'2016-06-12 15:29:06',1,1),(192,30,3,'2016-06-13 18:56:26',1,1),(193,31,3,'2016-06-13 18:57:06',1,1),(194,32,3,'2016-06-13 18:57:39',1,1),(195,33,3,'2016-06-13 18:58:24',1,1),(196,34,3,'2016-06-13 18:58:52',1,1),(197,35,3,'2016-06-13 18:59:32',1,1),(198,36,3,'2016-06-13 19:00:07',1,1),(199,37,3,'2016-06-13 19:00:41',1,1),(200,26,4,'2016-06-13 19:06:43',1,1),(201,26,5,'2016-06-13 19:06:47',1,1),(202,27,4,'2016-06-13 19:06:53',1,1),(203,27,5,'2016-06-13 19:06:57',1,1),(204,28,4,'2016-06-13 19:07:04',1,1),(205,28,5,'2016-06-13 19:07:06',1,1),(206,29,4,'2016-06-13 19:07:12',1,1),(207,29,5,'2016-06-13 19:07:14',1,1),(208,30,4,'2016-06-13 19:07:20',1,1),(209,30,5,'2016-06-13 19:07:23',1,1),(210,31,4,'2016-06-13 19:07:29',1,1),(211,31,5,'2016-06-13 19:07:32',1,1),(212,32,4,'2016-06-13 19:07:38',1,1),(213,32,5,'2016-06-13 19:07:41',1,1),(214,33,4,'2016-06-13 19:07:46',1,1),(215,33,5,'2016-06-13 19:07:50',1,1),(216,34,4,'2016-06-13 19:07:57',1,1),(217,34,5,'2016-06-13 19:07:59',1,1),(218,35,4,'2016-06-13 19:08:25',1,1),(219,35,5,'2016-06-13 19:08:28',1,1),(220,36,4,'2016-06-13 19:08:34',1,1),(221,36,5,'2016-06-13 19:08:37',1,1),(222,37,4,'2016-06-13 19:08:47',1,1),(223,37,5,'2016-06-13 19:08:50',1,1),(224,26,6,'2016-06-13 19:10:34',1,1),(225,27,6,'2016-06-13 19:10:41',1,1),(226,28,6,'2016-06-13 19:10:47',1,1),(227,29,6,'2016-06-13 19:10:54',1,1),(228,30,6,'2016-06-13 19:11:00',1,1),(229,31,6,'2016-06-13 19:11:07',1,1),(230,32,6,'2016-06-13 19:11:12',1,1),(231,33,6,'2016-06-13 19:11:19',1,1),(232,34,6,'2016-06-13 19:11:24',1,1),(233,35,6,'2016-06-13 19:11:31',1,1),(234,36,6,'2016-06-13 19:11:37',1,1),(235,37,6,'2016-06-13 19:11:43',1,1),(246,38,3,'2016-06-13 19:28:19',1,1),(247,38,4,'2016-06-13 19:28:30',1,1),(248,38,5,'2016-06-13 19:28:34',1,1),(249,38,6,'2016-06-13 19:28:47',1,1),(262,22,7,'2016-06-13 20:31:10',1,1),(263,22,11,'2016-06-13 22:57:38',1,1),(264,23,7,'2016-06-14 16:50:47',1,1),(265,23,11,'2016-06-14 16:51:32',1,1);
/*!40000 ALTER TABLE `ingreso_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_stock`
--

DROP TABLE IF EXISTS `item_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `InsumoId` int(11) NOT NULL,
  `Existencias` int(11) NOT NULL,
  `habilitado` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_stock`
--

LOCK TABLES `item_stock` WRITE;
/*!40000 ALTER TABLE `item_stock` DISABLE KEYS */;
INSERT INTO `item_stock` VALUES (1,1,38,1),(2,2,82,1),(3,3,11,1),(4,4,749,1),(5,5,155,1),(6,6,10,1),(7,7,-8,1),(9,12,-6,1);
/*!40000 ALTER TABLE `item_stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marca_producto`
--

DROP TABLE IF EXISTS `marca_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marca_producto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `detalle` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `idusuario` int(11) DEFAULT NULL,
  `habilitado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marca_producto`
--

LOCK TABLES `marca_producto` WRITE;
/*!40000 ALTER TABLE `marca_producto` DISABLE KEYS */;
INSERT INTO `marca_producto` VALUES (0,'MOTOROLA','2016-04-30 01:53:08',1,1),(1,'SANYO','2016-04-29 23:58:10',1,1),(2,'SAMSUNG','2016-04-29 23:58:10',1,1),(3,'EXONN','2016-04-29 23:58:10',1,1),(4,'CYBER','2016-06-03 19:04:19',1,1),(5,'Marca1','2016-06-07 21:14:40',0,1);
/*!40000 ALTER TABLE `marca_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orden_compra`
--

DROP TABLE IF EXISTS `orden_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orden_compra` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idproveedor` int(11) DEFAULT NULL,
  `importe_total` decimal(15,2) DEFAULT NULL,
  `idusuario` int(11) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `habilitado` tinyint(1) DEFAULT NULL,
  `estado` enum('NUEVA','RECIBIDA','CANCELADA') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orden_compra`
--

LOCK TABLES `orden_compra` WRITE;
/*!40000 ALTER TABLE `orden_compra` DISABLE KEYS */;
INSERT INTO `orden_compra` VALUES (12,2,90000.03,1,'2016-06-07 18:34:42','2016-06-07 18:35:08',1,'RECIBIDA'),(13,2,13424.00,1,'2016-06-07 18:36:45','2016-06-07 18:37:08',1,'RECIBIDA'),(14,2,325.00,1,'2016-06-07 19:57:25','2016-06-07 19:57:37',1,'RECIBIDA'),(15,1,999.32,4,'2016-06-07 21:16:55','2016-06-07 21:19:31',1,'RECIBIDA'),(16,2,9.11,4,'2016-06-07 21:22:24','2016-06-07 21:23:22',1,'RECIBIDA'),(17,2,1234.00,4,'2016-06-07 21:30:56','2016-06-07 21:30:56',1,'NUEVA'),(18,2,125.55,1,'2016-06-14 16:51:50','2016-06-14 16:52:07',1,'RECIBIDA');
/*!40000 ALTER TABLE `orden_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orden_compra_repuestos`
--

DROP TABLE IF EXISTS `orden_compra_repuestos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orden_compra_repuestos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idorden_compra` int(11) DEFAULT NULL,
  `idrepuesto` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `cantidad_real` int(11) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `habilitado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orden_compra_repuestos`
--

LOCK TABLES `orden_compra_repuestos` WRITE;
/*!40000 ALTER TABLE `orden_compra_repuestos` DISABLE KEYS */;
INSERT INTO `orden_compra_repuestos` VALUES (14,7,1,5,5,'2016-06-05 19:54:13',1),(15,8,1,5,5,'2016-06-05 19:56:12',1),(16,9,2,2,3,'2016-06-07 13:15:33',1),(17,9,1,2,3,'2016-06-07 13:15:33',1),(18,9,3,3,4,'2016-06-07 13:15:33',1),(19,10,1,2,10,'2016-06-07 13:48:53',1),(20,10,2,2,10,'2016-06-07 13:48:53',1),(21,10,5,3,10,'2016-06-07 13:48:53',1),(22,11,1,2,29,'2016-06-07 14:00:49',1),(23,11,3,1,15,'2016-06-07 14:00:49',1),(24,11,2,1,70,'2016-06-07 14:00:49',1),(25,11,4,1,89,'2016-06-07 14:00:49',1),(26,11,5,1,150,'2016-06-07 14:00:49',1),(27,11,6,1,15,'2016-06-07 14:00:49',1),(28,11,7,1,15,'2016-06-07 14:00:49',1),(29,12,12,4,4,'2016-06-07 18:33:04',1),(30,12,7,2,2,'2016-06-07 18:33:04',1),(31,12,5,4,4,'2016-06-07 18:33:04',1),(32,12,4,257,257,'2016-06-07 18:33:04',1),(33,12,4,410,257,'2016-06-07 18:34:41',1),(34,12,12,492,4,'2016-06-07 18:34:42',1),(35,13,12,82,82,'2016-06-07 18:36:45',1),(36,14,12,7,7,'2016-06-07 19:57:25',1),(37,15,2,36,36,'2016-06-07 21:16:55',1),(38,15,1,6,6,'2016-06-07 21:16:55',1),(39,16,1,5,4,'2016-06-07 21:22:24',1),(40,17,12,73,73,'2016-06-07 21:30:56',1),(41,18,1,2,2,'2016-06-14 16:51:50',1),(42,18,3,2,2,'2016-06-14 16:51:50',1);
/*!40000 ALTER TABLE `orden_compra_repuestos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfil`
--

DROP TABLE IF EXISTS `perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perfil` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `detalle` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfil`
--

LOCK TABLES `perfil` WRITE;
/*!40000 ALTER TABLE `perfil` DISABLE KEYS */;
INSERT INTO `perfil` VALUES (1,'ADMINISTRADOR'),(2,'ADMINISTRATIVO'),(3,'TECNICO');
/*!40000 ALTER TABLE `perfil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfil_permiso`
--

DROP TABLE IF EXISTS `perfil_permiso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perfil_permiso` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idperfil` int(11) DEFAULT NULL,
  `idpermiso` int(11) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfil_permiso`
--

LOCK TABLES `perfil_permiso` WRITE;
/*!40000 ALTER TABLE `perfil_permiso` DISABLE KEYS */;
INSERT INTO `perfil_permiso` VALUES (1,1,1,'2016-04-29 23:43:41'),(2,1,2,'2016-04-29 23:43:41'),(3,2,1,'2016-04-29 23:43:41'),(4,3,2,'2016-04-29 23:43:41');
/*!40000 ALTER TABLE `perfil_permiso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permiso`
--

DROP TABLE IF EXISTS `permiso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permiso` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `detalle_corto` varchar(45) DEFAULT NULL,
  `descripcion` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permiso`
--

LOCK TABLES `permiso` WRITE;
/*!40000 ALTER TABLE `permiso` DISABLE KEYS */;
INSERT INTO `permiso` VALUES (1,'INGRESAR_PRODUCTO','Puede ingresar una orden de trabajo'),(2,'PRESUPUESTAR','Puede crear un presupuesto');
/*!40000 ALTER TABLE `permiso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `presupuesto`
--

DROP TABLE IF EXISTS `presupuesto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `presupuesto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idingreso` int(11) DEFAULT NULL,
  `descripcion_breve` longtext COLLATE latin1_spanish_ci,
  `descripcion_tecnica` longtext COLLATE latin1_spanish_ci,
  `horas_dedicadas` int(11) DEFAULT NULL,
  `importe_mano_obra` decimal(15,2) DEFAULT NULL,
  `importe_total` decimal(15,2) DEFAULT NULL,
  `fecha_vencimiento` date DEFAULT NULL,
  `idusuario` int(11) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `habilitado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presupuesto`
--

LOCK TABLES `presupuesto` WRITE;
/*!40000 ALTER TABLE `presupuesto` DISABLE KEYS */;
INSERT INTO `presupuesto` VALUES (1,23,'Se cambiaria el modulo	','Se cambiaria el modulo rtfg',8,120.00,67624.00,'2016-06-30',1,'2016-06-07 12:24:10',1),(2,22,'243','214',8,123.50,34243.70,'2016-06-19',1,'2016-06-07 20:03:22',1),(3,24,'ewarewqr','weqrwrq',8,12.00,161.00,'2016-06-18',4,'2016-06-07 21:34:22',1),(4,25,'dsa','dsada',8,231.00,17850.00,'2016-06-25',4,'2016-06-07 21:38:17',1),(5,26,'Descripcion','Descripcion',9,123.00,33543.00,'2016-06-25',1,'2016-06-12 15:27:24',1),(6,27,'Descripcion','Descripcion',9,3003.00,1696.00,'2016-06-30',1,'2016-06-12 15:28:01',1),(7,28,'Descripcion','Descripcion',10,380.00,33893.00,'2016-06-29',1,'2016-06-12 15:28:32',1),(8,29,'Descripcion','Descripcion',8,300.00,32935.00,'2016-06-22',1,'2016-06-12 15:29:06',1),(9,30,'eedad','dads',9,124.00,1400.00,'2016-06-30',1,'2016-06-13 18:56:26',1),(10,31,'dsadasd','asfdfasf',8,12.00,33566.00,'2016-06-23',1,'2016-06-13 18:57:06',1),(11,32,'Re	r','RE3',8,157.00,2745.00,'2016-06-29',1,'2016-06-13 18:57:39',1),(12,33,'asdsad','asdffsa',8,126.00,4546.00,'2016-06-30',1,'2016-06-13 18:58:24',1),(13,34,'arsrsr','resrtest',9,145.00,4141.00,'2016-06-30',1,'2016-06-13 18:58:52',1),(14,35,'csavfdsg','gdsgf',9,146.00,3473.00,'2016-06-15',1,'2016-06-13 18:59:32',1),(15,36,'sadfsa','sadfs',9,152.00,2073.00,'2016-06-30',1,'2016-06-13 19:00:07',1),(16,37,'ddsfda','fdsafd',12,425.00,300.00,'2016-06-23',1,'2016-06-13 19:00:41',1),(17,38,'dads','safsd',9,1432.00,4050.00,'2016-06-24',1,'2016-06-13 19:28:19',1);
/*!40000 ALTER TABLE `presupuesto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `presupuesto_repuestos`
--

DROP TABLE IF EXISTS `presupuesto_repuestos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `presupuesto_repuestos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idpresupuesto` int(11) DEFAULT NULL,
  `idrepuesto` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `habilitado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presupuesto_repuestos`
--

LOCK TABLES `presupuesto_repuestos` WRITE;
/*!40000 ALTER TABLE `presupuesto_repuestos` DISABLE KEYS */;
INSERT INTO `presupuesto_repuestos` VALUES (1,1,1,2,'2016-06-07 12:24:10',1),(2,1,2,2,'2016-06-07 12:24:10',1),(3,1,3,2,'2016-06-07 12:24:10',1),(4,1,4,1,'2016-06-07 12:24:10',1),(5,1,5,1,'2016-06-07 12:24:10',1),(6,2,2,1,'2016-06-07 20:03:22',1),(7,2,3,7,'2016-06-07 20:03:22',1),(8,3,12,7,'2016-06-07 21:34:22',1),(9,4,7,17,'2016-06-07 21:38:17',1),(10,5,2,1,'2016-06-12 15:27:24',1),(11,5,6,2,'2016-06-12 15:27:24',1),(12,6,3,2,'2016-06-12 15:28:01',1),(13,6,5,2,'2016-06-12 15:28:01',1),(14,6,7,1,'2016-06-12 15:28:01',1),(15,7,2,1,'2016-06-12 15:28:32',1),(16,7,6,1,'2016-06-12 15:28:32',1),(17,7,7,1,'2016-06-12 15:28:32',1),(18,8,2,1,'2016-06-12 15:29:06',1),(19,8,12,4,'2016-06-12 15:29:06',1),(20,8,6,1,'2016-06-12 15:29:06',1),(21,9,6,2,'2016-06-13 18:56:26',1),(22,10,2,1,'2016-06-13 18:57:06',1),(23,10,5,1,'2016-06-13 18:57:06',1),(24,10,6,2,'2016-06-13 18:57:06',1),(25,11,1,2,'2016-06-13 18:57:39',1),(26,11,4,3,'2016-06-13 18:57:39',1),(27,12,1,1,'2016-06-13 18:58:24',1),(28,12,7,3,'2016-06-13 18:58:24',1),(29,12,12,2,'2016-06-13 18:58:24',1),(30,13,1,3,'2016-06-13 18:58:52',1),(31,13,4,3,'2016-06-13 18:58:52',1),(32,13,5,2,'2016-06-13 18:58:52',1),(33,14,1,1,'2016-06-13 18:59:32',1),(34,14,7,2,'2016-06-13 18:59:32',1),(35,14,12,1,'2016-06-13 18:59:32',1),(36,15,1,1,'2016-06-13 19:00:07',1),(37,15,5,1,'2016-06-13 19:00:07',1),(38,15,6,1,'2016-06-13 19:00:07',1),(39,16,3,1,'2016-06-13 19:00:41',1),(40,17,1,3,'2016-06-13 19:28:19',1);
/*!40000 ALTER TABLE `presupuesto_repuestos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `razon_social` varchar(45) DEFAULT NULL,
  `cuit` int(11) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `mail` varchar(45) DEFAULT NULL,
  `contacto_nombre` varchar(45) DEFAULT NULL,
  `contacto_telefono` varchar(45) DEFAULT NULL,
  `contacto_mail` varchar(45) DEFAULT NULL,
  `mail_para_pedidos` varchar(45) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `idusuario` int(11) DEFAULT NULL,
  `habilitado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES (1,'TECNOSUR',234356565,'Esmeralda 2412','tecnos@gmail.com','Ramiro Fleita','153424241','fleitram@gmail.com','tecnos_pedidos@gmail.com','2016-06-07 21:15:07',1,1),(2,'CENTRO TECNO',423143251,'Guayaquil 4342','centrotecno@gmail.com','Esteban Flores','1523515215432','esteban@gmail.com','juangabrielferreyra93@gmail.com','2016-06-07 18:31:57',1,1);
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor_marca`
--

DROP TABLE IF EXISTS `proveedor_marca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedor_marca` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idproveedor` int(11) DEFAULT NULL,
  `idmarca` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor_marca`
--

LOCK TABLES `proveedor_marca` WRITE;
/*!40000 ALTER TABLE `proveedor_marca` DISABLE KEYS */;
INSERT INTO `proveedor_marca` VALUES (11,2,0),(12,2,3),(13,2,2),(14,2,1),(15,2,4),(16,1,0),(17,1,2),(18,1,5);
/*!40000 ALTER TABLE `proveedor_marca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reparaciones`
--

DROP TABLE IF EXISTS `reparaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reparaciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tecnico_asignado` varchar(45) DEFAULT NULL,
  `fecha_reparacion` datetime DEFAULT NULL,
  `horas` int(11) DEFAULT NULL,
  `valor_estimado` decimal(15,2) DEFAULT NULL,
  `descripcion_final` longtext,
  `ingreso_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reparaciones`
--

LOCK TABLES `reparaciones` WRITE;
/*!40000 ALTER TABLE `reparaciones` DISABLE KEYS */;
INSERT INTO `reparaciones` VALUES (8,'NOMBRE_JEFE APELLIDO_JEFE',NULL,0,0.00,'Correctamente lo esperado',22),(9,'NOMBRE_JEFE APELLIDO_JEFE','2016-06-14 16:50:47',NULL,0.00,'dsadasdasda',23);
/*!40000 ALTER TABLE `reparaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reparaciones_repuestos`
--

DROP TABLE IF EXISTS `reparaciones_repuestos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reparaciones_repuestos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idreparacion` int(11) DEFAULT NULL,
  `idrepuesto` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `habilitado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reparaciones_repuestos`
--

LOCK TABLES `reparaciones_repuestos` WRITE;
/*!40000 ALTER TABLE `reparaciones_repuestos` DISABLE KEYS */;
INSERT INTO `reparaciones_repuestos` VALUES (45,8,2,7,NULL,1),(46,8,3,1,NULL,1),(47,9,1,1,NULL,1),(48,9,2,1,NULL,1),(49,9,3,2,NULL,1),(50,9,4,2,NULL,1),(51,9,5,2,NULL,1);
/*!40000 ALTER TABLE `reparaciones_repuestos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repuesto`
--

DROP TABLE IF EXISTS `repuesto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `repuesto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `detalle` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `precio` decimal(15,2) DEFAULT NULL,
  `stock_minimo` int(11) DEFAULT NULL,
  `idmarca` int(11) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `idusuario` int(11) DEFAULT NULL,
  `habilitado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repuesto`
--

LOCK TABLES `repuesto` WRITE;
/*!40000 ALTER TABLE `repuesto` DISABLE KEYS */;
INSERT INTO `repuesto` VALUES (1,'Botonera Galaxy S3',1350.00,5,0,'2016-04-30 00:05:20',0,1),(2,'Modulo Pantalla',32143.00,4,2,'2016-04-30 00:05:20',0,1),(3,'Teclado p3',300.00,3,1,'2016-05-29 00:29:08',0,1),(4,'Lamparita',15.00,250,1,'2016-06-03 19:07:15',0,1),(5,'Otra Lamparita',23.00,45,2,'2016-06-03 19:07:39',0,1),(6,'Fuente 450-os',700.00,10,3,'2016-06-07 13:31:24',0,1),(7,'Disco Rigido',1050.00,10,1,'2016-06-07 13:39:40',0,1),(12,'Cooler',23.00,80,4,'2016-06-07 18:30:46',0,1);
/*!40000 ALTER TABLE `repuesto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_producto`
--

DROP TABLE IF EXISTS `tipo_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_producto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `detalle` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `idusuario` int(11) DEFAULT NULL,
  `habilitado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_producto`
--

LOCK TABLES `tipo_producto` WRITE;
/*!40000 ALTER TABLE `tipo_producto` DISABLE KEYS */;
INSERT INTO `tipo_producto` VALUES (0,'AIRE ACONDICIONADOS','2016-04-30 00:00:23',1,1),(1,'CELULAR','2016-04-30 00:00:23',1,1),(2,'TELEVISOR LED','2016-04-30 00:00:23',1,0),(3,'PC','2016-06-12 15:13:39',0,1),(4,'ESTEREO','2016-06-12 15:18:01',0,1),(5,'ELECTRODOMESTICOS','2016-06-12 15:19:46',0,1);
/*!40000 ALTER TABLE `tipo_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nick` varchar(45) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `idperfil` int(11) DEFAULT NULL,
  `habilitado` tinyint(4) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'jefe','NOMBRE_JEFE','APELLIDO_JEFE','jefe',1,1,'2016-04-29 23:35:33'),(2,'administrativo','NOMBRE_ADMINISTRATIVO','APELLIDO_ADMINISTRATIVO','administrativo',2,1,'2016-05-18 23:39:19'),(3,'tecnico','NOMBRE_TECNICO','APELLIDO_TECNICO','tecnico',3,1,'2016-05-18 23:39:19'),(4,'agus','Agustina','De Napoli','123',1,1,'2016-06-07 21:12:02'),(5,'kak','asd','ads','1',3,0,'2016-06-07 21:13:05');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-14 16:56:30
