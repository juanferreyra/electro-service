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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,1,'Lucas','Avelda?o','Malvinas Argentinas','Londres 312','153214211','juangabrielferreyra93@gmail.com','2016-04-29 23:45:10',0,1),(2,2,'Agustina','Denapoli','Tigre','Tacuari 342','152315213','juangabrielferreyra93@gmail.com','2016-04-29 23:54:42',0,1),(3,3,'Robert','Quilmes','Malvinas Argentinas','Guayaquil 932','153524252','juangabrielferreyra93@gmail.com','2016-05-03 19:57:48',0,1),(4,4,'Claudia','Alfonso','San Isidro','Stephenson 932','1543265223','juangabrielferreyra93@gmail.com','2016-05-03 19:57:48',0,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flete`
--

LOCK TABLES `flete` WRITE;
/*!40000 ALTER TABLE `flete` DISABLE KEYS */;
INSERT INTO `flete` VALUES (1,1,'Ricado Nu','Toyota Hilux','FTV897','15648597','2016-08-17','2016-05-17 11:48:02',1,1),(2,2,'Nicolas Acosta','Toyota Corolla ','WRA789','157497897','2016-09-17','2016-05-17 11:50:43',0,1),(3,3,'Juan Martin Verrelli','Audi A6','ESE164','15487897','2016-10-17','2016-05-17 11:50:43',0,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hojaruta`
--

LOCK TABLES `hojaruta` WRITE;
/*!40000 ALTER TABLE `hojaruta` DISABLE KEYS */;
INSERT INTO `hojaruta` VALUES (17,1,'2016-05-27 02:05:15',1,3),(18,2,'2016-05-27 02:17:12',1,3);
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
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hojaruta_ingreso`
--

LOCK TABLES `hojaruta_ingreso` WRITE;
/*!40000 ALTER TABLE `hojaruta_ingreso` DISABLE KEYS */;
INSERT INTO `hojaruta_ingreso` VALUES (60,17,7,0,'2016-05-27 02:05:15'),(61,17,11,0,'2016-05-27 02:05:15'),(62,17,10,0,'2016-05-27 02:05:15'),(63,17,9,0,'2016-05-27 02:05:15'),(64,17,12,0,'2016-05-27 02:05:15'),(65,17,8,0,'2016-05-27 02:05:15'),(66,17,14,0,'2016-05-27 02:05:15'),(67,17,18,0,'2016-05-27 02:05:15'),(68,18,14,0,'2016-05-27 02:17:12'),(69,18,18,0,'2016-05-27 02:17:12'),(70,18,20,0,'2016-05-27 02:17:12');
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
  `monto_envio` float DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `idusuario` int(11) DEFAULT NULL,
  `habilitado` tinyint(1) DEFAULT NULL,
  `tecnico_asignado` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingreso`
--

LOCK TABLES `ingreso` WRITE;
/*!40000 ALTER TABLE `ingreso` DISABLE KEYS */;
INSERT INTO `ingreso` VALUES (7,1,'Rasrsarara',0,0,'dsaasrsa',1,0,'Direccion Alternativa',4324,1,'2016-05-06 23:05:39',0,1,NULL),(8,2,'Samsung Galaxy s3',2,1,'FDasfafsafsaLasdsad ddescr iocnrocnrocnnsofd',1,1,'Lavallolr 2341',2431.21,1,'2016-05-15 22:22:46',0,1,NULL),(9,3,'Test de carga de presupuesto',1,0,'Descripcion de falla del test de presupuesto',1,0,'',0,1,'2016-05-16 18:39:11',0,1,NULL),(10,1,'Raras',0,0,'ewqrqrqrw',1,1,'Lavallor 23123',3000,1,'2016-05-16 19:12:47',0,1,NULL),(11,4,'Lg storm',3,2,'Se rompio el vibrador del telefono',1,0,'',0,1,'2016-05-16 19:35:47',0,1,NULL),(12,1,'Zarazaza2',1,0,'Test definitivo de recorrido',1,0,'',0,1,'2016-05-16 22:42:15',0,1,NULL),(13,1,'dsadsa',0,0,'cdasdad',1,0,'',0,1,'2016-05-17 00:42:01',0,1,NULL),(14,2,'Auricular Sony',1,2,'No anda el derecho',1,0,'',0,1,'2016-05-17 13:58:29',0,1,NULL),(15,1,'zadsadsa',0,0,'dsadasd',1,0,'',0,1,'2016-05-18 18:17:29',0,1,NULL),(16,2,'dsadsad',0,0,'dsadadsafa',1,0,'',0,1,'2016-05-18 18:23:40',0,1,NULL),(17,4,'LG',3,0,'ASdasdad falla',1,0,'',123.44,1,'2016-05-18 18:25:44',0,1,NULL),(18,2,'dasdsad',0,0,'dsadsadasd',1,0,'Guayaquil 884',13213,1,'2016-05-18 18:26:33',0,1,NULL),(19,2,'dasdadas test ',0,0,'dadasd',0,0,'',0,1,'2016-05-18 21:44:15',0,1,NULL),(20,3,'Ultimo test',0,0,'dadsadasdsa del ultimo test',1,1,'Calle falsa 123',800,1,'2016-05-18 23:03:21',0,1,NULL),(21,3,'Producto Test 100',2,1,'Descripcion del test',1,0,'',2190,1,'2016-05-18 23:32:05',0,1,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingreso_log`
--

LOCK TABLES `ingreso_log` WRITE;
/*!40000 ALTER TABLE `ingreso_log` DISABLE KEYS */;
INSERT INTO `ingreso_log` VALUES (29,7,3,'2016-05-15 21:39:05',3,1),(30,8,1,'2016-05-15 22:22:46',0,1),(31,8,3,'2016-05-15 22:23:40',3,1),(32,9,1,'2016-05-16 18:39:11',0,1),(33,9,3,'2016-05-16 18:40:04',3,1),(34,10,1,'2016-05-16 19:12:47',0,1),(36,11,1,'2016-05-16 19:35:47',0,1),(38,12,1,'2016-05-16 22:42:15',0,1),(40,7,4,'2016-05-17 00:23:31',3,1),(41,7,5,'2016-05-17 00:32:37',3,1),(43,10,4,'2016-05-17 00:40:27',1,1),(44,10,5,'2016-05-17 00:40:34',1,1),(46,12,4,'2016-05-17 00:41:29',3,1),(47,12,5,'2016-05-17 00:41:32',3,1),(49,13,1,'2016-05-17 00:42:01',0,1),(50,8,4,'2016-05-17 13:57:21',3,1),(51,8,5,'2016-05-17 13:57:23',3,1),(53,9,4,'2016-05-17 13:57:35',3,1),(54,9,5,'2016-05-17 13:57:38',3,1),(56,14,1,'2016-05-17 13:58:29',0,1),(57,14,3,'2016-05-17 13:59:15',3,1),(58,14,4,'2016-05-17 14:00:23',3,1),(59,14,5,'2016-05-17 14:00:30',3,1),(61,13,3,'2016-05-18 17:50:29',3,1),(62,13,4,'2016-05-18 17:50:38',3,1),(63,13,5,'2016-05-18 17:50:53',3,1),(65,15,1,'2016-05-18 18:17:29',0,1),(66,16,1,'2016-05-18 18:23:40',0,1),(67,17,1,'2016-05-18 18:25:44',0,1),(68,18,1,'2016-05-18 18:26:33',0,1),(69,18,3,'2016-05-18 18:27:12',3,1),(70,18,4,'2016-05-18 18:27:24',3,1),(71,18,5,'2016-05-18 18:27:29',3,1),(74,16,3,'2016-05-18 18:31:14',3,1),(75,16,4,'2016-05-18 18:32:28',3,1),(76,16,5,'2016-05-18 18:32:30',3,1),(78,19,1,'2016-05-18 21:44:15',0,1),(95,20,1,'2016-05-18 23:03:21',0,1),(96,20,3,'2016-05-18 23:06:40',3,1),(97,20,4,'2016-05-18 23:06:52',3,1),(98,20,5,'2016-05-18 23:06:56',3,1),(102,21,1,'2016-05-18 23:32:05',0,1),(103,19,3,'2016-05-27 00:13:46',3,1),(104,19,4,'2016-05-27 00:13:56',3,1),(141,14,12,'2016-05-27 02:09:27',3,1);
/*!40000 ALTER TABLE `ingreso_log` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marca_producto`
--

LOCK TABLES `marca_producto` WRITE;
/*!40000 ALTER TABLE `marca_producto` DISABLE KEYS */;
INSERT INTO `marca_producto` VALUES (0,'MOTOROLA','2016-04-30 01:53:08',0,1),(1,'SANYO','2016-04-29 23:58:10',0,1),(2,'SAMSUNG','2016-04-29 23:58:10',0,1),(3,'EXO','2016-04-29 23:58:10',0,1);
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
  `importe_total` float DEFAULT NULL,
  `importe_validado` float DEFAULT NULL,
  `idusuario` int(11) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `habilitado` tinyint(1) DEFAULT NULL,
  `estado` enum('NUEVA','RECIBIDA','CANCELADA') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orden_compra`
--

LOCK TABLES `orden_compra` WRITE;
/*!40000 ALTER TABLE `orden_compra` DISABLE KEYS */;
INSERT INTO `orden_compra` VALUES (1,2,4050,NULL,1,'2016-06-01 17:03:36',1,'NUEVA'),(2,2,168815,NULL,1,'2016-06-01 17:05:40',1,'NUEVA'),(3,1,5400,NULL,1,'2016-06-01 17:08:19',1,'RECIBIDA'),(4,1,810325,NULL,1,'2016-06-01 18:32:00',1,'CANCELADA'),(5,1,168815,NULL,1,'2016-06-01 19:46:34',1,'NUEVA');
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
  `precio_unitario` float DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `habilitado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orden_compra_repuestos`
--

LOCK TABLES `orden_compra_repuestos` WRITE;
/*!40000 ALTER TABLE `orden_compra_repuestos` DISABLE KEYS */;
INSERT INTO `orden_compra_repuestos` VALUES (1,1,1,4,1350,'2016-06-01 17:01:42',1),(2,1,1,4,1350,'2016-06-01 17:01:54',1),(3,1,1,3,1350,'2016-06-01 17:03:36',1),(4,2,1,6,1350,'2016-06-01 17:05:40',1),(5,2,2,5,32143,'2016-06-01 17:05:40',1),(6,3,1,4,1350,'2016-06-01 17:08:19',1),(7,4,1,5,1350,'2016-06-01 18:32:00',1),(8,4,2,25,32143,'2016-06-01 18:32:00',1),(9,5,2,5,32143,'2016-06-01 19:46:34',1),(10,5,1,6,1350,'2016-06-01 19:46:34',1);
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
  `importe_mano_obra` float DEFAULT NULL,
  `importe_total` float DEFAULT NULL,
  `fecha_vencimiento` date DEFAULT NULL,
  `idusuario` int(11) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `habilitado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presupuesto`
--

LOCK TABLES `presupuesto` WRITE;
/*!40000 ALTER TABLE `presupuesto` DISABLE KEYS */;
INSERT INTO `presupuesto` VALUES (3,6,'descripcion_ brere','dsadadescripcion_tecnica',123,12.32,30000,'2016-04-10',1,'2016-05-10 18:59:22',1),(5,7,'Descr Breve','Descr Tecnica',20,180.5,171515,'2016-05-21',3,'2016-05-15 21:39:05',1),(6,8,'Dedscrionb Ffrebfregfe','Descrioncaiucnn Frewfafdsfsa',9,1123130,198258,'2016-05-29',3,'2016-05-15 22:23:40',1),(7,9,'Descripcion breve del test de carga presuuesto	','Descripcion tenica del teste de carga de presupuesto',9,120,34843,'2016-06-16',3,'2016-05-16 18:40:04',1),(8,10,'dacres	','dasdfafda',8,123,5400,'2016-06-16',3,'2016-05-16 19:13:16',1),(9,11,'desdes','fdsafdsafsaf',9,441,1350,'2016-05-19',3,'2016-05-16 19:36:12',1),(10,12,'Test 1	','Tesd132',9,12,13500,'2016-09-17',3,'2016-05-16 22:42:59',1),(11,14,'Desdsed	d	','desdsefasdf',8,80.5,128572,'2016-06-17',3,'2016-05-17 13:59:15',1),(12,13,'dsadasd','fdgewqtwqrwe',8,12,2700,'2016-05-19',3,'2016-05-18 17:50:29',1),(13,18,'Arreglado','dsadasd',8,123,68336,'2016-08-20',3,'2016-05-18 18:27:12',1),(14,16,'dsadsad','sadfdsafs',8,123,2700,'2016-05-19',3,'2016-05-18 18:31:14',1),(15,20,'test 1','test2',9,300,64286,'2016-07-18',3,'2016-05-18 23:06:40',1),(16,19,'asfs','fsfsafads',9,19,2700,'2016-05-21',3,'2016-05-27 00:13:46',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presupuesto_repuestos`
--

LOCK TABLES `presupuesto_repuestos` WRITE;
/*!40000 ALTER TABLE `presupuesto_repuestos` DISABLE KEYS */;
INSERT INTO `presupuesto_repuestos` VALUES (8,5,1,8,'2016-05-15 21:39:05',1),(9,5,2,5,'2016-05-15 21:39:05',1),(10,6,1,4,'2016-05-15 22:23:40',1),(11,6,2,6,'2016-05-15 22:23:40',1),(12,7,1,2,'2016-05-16 18:40:04',1),(13,7,2,1,'2016-05-16 18:40:04',1),(14,8,1,4,'2016-05-16 19:13:16',1),(15,9,1,1,'2016-05-16 19:36:12',1),(16,10,1,10,'2016-05-16 22:42:59',1),(17,11,2,4,'2016-05-17 13:59:15',1),(18,12,1,2,'2016-05-18 17:50:29',1),(19,13,1,3,'2016-05-18 18:27:12',1),(20,13,2,2,'2016-05-18 18:27:12',1),(21,14,1,2,'2016-05-18 18:31:14',1),(22,15,2,2,'2016-05-18 23:06:40',1),(23,16,1,2,'2016-05-27 00:13:46',1);
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
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES (1,'TECNOSUR',234356565,'Esmeralda 2411','tecnos@gmail.com','Ramiro Fleita','153424241','fleitram@gmail.com','tecnos_pedidos@gmail.com','2016-05-31 00:21:06',1,1),(2,'CENTRO TECNO',423143251,'Guayaquil 4342','centrotecno@gmail.com','Esteban Flores','1523515215432','esteban@gmail.com','pedidos@gmail.com','2016-06-01 16:06:36',1,1);
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
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor_marca`
--

LOCK TABLES `proveedor_marca` WRITE;
/*!40000 ALTER TABLE `proveedor_marca` DISABLE KEYS */;
INSERT INTO `proveedor_marca` VALUES (1,1,0),(2,1,2);
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
  `tecnico_asignado` varchar(20) DEFAULT NULL,
  `fecha_reparacion` datetime DEFAULT NULL,
  `horas` int(11) DEFAULT NULL,
  `valor_estimado` int(11) DEFAULT NULL,
  `descripcion_final` varchar(50) DEFAULT NULL,
  `ingreso_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reparaciones`
--

LOCK TABLES `reparaciones` WRITE;
/*!40000 ALTER TABLE `reparaciones` DISABLE KEYS */;
INSERT INTO `reparaciones` VALUES (1,'JOAQUIN TELECHEA',NULL,0,0,'se reparo correctamente',20);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reparaciones_repuestos`
--

LOCK TABLES `reparaciones_repuestos` WRITE;
/*!40000 ALTER TABLE `reparaciones_repuestos` DISABLE KEYS */;
INSERT INTO `reparaciones_repuestos` VALUES (1,1,2,2,NULL,1);
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
  `precio` float DEFAULT NULL,
  `stock_minimo` int(11) DEFAULT NULL,
  `idmarca` int(11) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `idusuario` int(11) DEFAULT NULL,
  `habilitado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repuesto`
--

LOCK TABLES `repuesto` WRITE;
/*!40000 ALTER TABLE `repuesto` DISABLE KEYS */;
INSERT INTO `repuesto` VALUES (1,'Botonera Galaxy S3',1350,5,0,'2016-04-30 00:05:20',0,1),(2,'Modulo Pantalla',32143,4,2,'2016-04-30 00:05:20',0,1),(3,'Teclado p3',300,3,1,'2016-05-29 00:29:08',0,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;




--
-- Dumping data for table `tipo_producto`
--

LOCK TABLES `tipo_producto` WRITE;
/*!40000 ALTER TABLE `tipo_producto` DISABLE KEYS */;
INSERT INTO `tipo_producto` VALUES (0,'AIRE ACONDICIONADO','2016-04-30 00:00:23',0,1),(1,'CELULAR','2016-04-30 00:00:23',0,1),(2,'TELEVISOR','2016-04-30 00:00:23',0,1);
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
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `idperfil` int(11) DEFAULT NULL,
  `habilitado` tinyint(4) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'ADMINISTRADOR','ADM','ADMINISTRADOR',0,1,'2016-04-29 23:35:33'),(2,'Usuario2','user2','user2',1,1,'2016-05-18 23:39:19'),(3,'Usuario3','user3','user3',2,1,'2016-05-18 23:39:19');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

DROP TABLE IF EXISTS `itemStock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itemStock`(

`id` int(4) NOT NULL AUTO_INCREMENT PRIMARY KEY,

`InsumoId` int(4),

`Existencias` int(6),

`habilitado`tinyint(1))




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-01 20:54:46
