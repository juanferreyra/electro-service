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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hojaruta`
--

LOCK TABLES `hojaruta` WRITE;
/*!40000 ALTER TABLE `hojaruta` DISABLE KEYS */;
INSERT INTO `hojaruta` VALUES (1,4,'2016-06-07 12:49:28',1,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hojaruta_ingreso`
--

LOCK TABLES `hojaruta_ingreso` WRITE;
/*!40000 ALTER TABLE `hojaruta_ingreso` DISABLE KEYS */;
INSERT INTO `hojaruta_ingreso` VALUES (1,1,23,0,'2016-06-07 12:49:28');
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingreso`
--

LOCK TABLES `ingreso` WRITE;
/*!40000 ALTER TABLE `ingreso` DISABLE KEYS */;
INSERT INTO `ingreso` VALUES (22,1,'Razr i',0,1,'Se le rompio la antena Wiffi',1,1,'Guayaquil 884',300,1,'2016-06-04 20:24:54',0,1,NULL),(23,6,'E XT1527',0,1,'Se le rompio el display',1,1,'Mirafloes 325',300.5,11,'2016-06-07 11:50:18',0,1,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=158 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingreso_log`
--

LOCK TABLES `ingreso_log` WRITE;
/*!40000 ALTER TABLE `ingreso_log` DISABLE KEYS */;
INSERT INTO `ingreso_log` VALUES (150,22,1,'2016-06-04 20:24:54',0,1),(151,23,1,'2016-06-07 11:50:17',0,1),(152,23,3,'2016-06-07 12:24:10',1,1),(153,23,4,'2016-06-07 12:24:21',1,1),(154,23,5,'2016-06-07 12:24:24',1,1),(155,23,6,'2016-06-07 12:42:52',1,1),(156,23,7,'2016-06-07 12:48:16',1,1),(157,23,11,'2016-06-07 13:10:57',1,1);
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
INSERT INTO `item_stock` VALUES (1,1,39,1),(2,2,80,1),(3,3,15,1),(4,4,756,1),(5,5,164,1),(6,6,15,1),(7,7,17,1),(9,12,0,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marca_producto`
--

LOCK TABLES `marca_producto` WRITE;
/*!40000 ALTER TABLE `marca_producto` DISABLE KEYS */;
INSERT INTO `marca_producto` VALUES (0,'MOTOROLA','2016-04-30 01:53:08',1,1),(1,'SANYO','2016-04-29 23:58:10',1,1),(2,'SAMSUNG','2016-04-29 23:58:10',1,1),(3,'EXONN','2016-04-29 23:58:10',1,1),(4,'CYBER','2016-06-03 19:04:19',1,1);
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
  `idusuario` int(11) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `habilitado` tinyint(1) DEFAULT NULL,
  `estado` enum('NUEVA','RECIBIDA','CANCELADA') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orden_compra`
--

LOCK TABLES `orden_compra` WRITE;
/*!40000 ALTER TABLE `orden_compra` DISABLE KEYS */;
INSERT INTO `orden_compra` VALUES (13,2,134243,1,'2016-06-07 18:36:45','2016-06-07 18:37:08',1,'RECIBIDA'),(12,2,900000,1,'2016-06-07 18:34:42','2016-06-07 18:35:08',1,'RECIBIDA');
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
) ENGINE=MyISAM AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orden_compra_repuestos`
--

LOCK TABLES `orden_compra_repuestos` WRITE;
/*!40000 ALTER TABLE `orden_compra_repuestos` DISABLE KEYS */;
INSERT INTO `orden_compra_repuestos` VALUES (14,7,1,5,5,'2016-06-05 19:54:13',1),(15,8,1,5,5,'2016-06-05 19:56:12',1),(16,9,2,2,3,'2016-06-07 13:15:33',1),(17,9,1,2,3,'2016-06-07 13:15:33',1),(18,9,3,3,4,'2016-06-07 13:15:33',1),(19,10,1,2,10,'2016-06-07 13:48:53',1),(20,10,2,2,10,'2016-06-07 13:48:53',1),(21,10,5,3,10,'2016-06-07 13:48:53',1),(22,11,1,2,29,'2016-06-07 14:00:49',1),(23,11,3,1,15,'2016-06-07 14:00:49',1),(24,11,2,1,70,'2016-06-07 14:00:49',1),(25,11,4,1,89,'2016-06-07 14:00:49',1),(26,11,5,1,150,'2016-06-07 14:00:49',1),(27,11,6,1,15,'2016-06-07 14:00:49',1),(28,11,7,1,15,'2016-06-07 14:00:49',1),(29,12,12,4,4,'2016-06-07 18:33:04',1),(30,12,7,2,2,'2016-06-07 18:33:04',1),(31,12,5,4,4,'2016-06-07 18:33:04',1),(32,12,4,257,257,'2016-06-07 18:33:04',1),(33,12,4,410,257,'2016-06-07 18:34:41',1),(34,12,12,492,4,'2016-06-07 18:34:42',1),(35,13,12,82,82,'2016-06-07 18:36:45',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presupuesto`
--

LOCK TABLES `presupuesto` WRITE;
/*!40000 ALTER TABLE `presupuesto` DISABLE KEYS */;
INSERT INTO `presupuesto` VALUES (1,23,'Se cambiaria el modulo	','Se cambiaria el modulo rtfg',8,120,67624,'2016-06-30',1,'2016-06-07 12:24:10',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presupuesto_repuestos`
--

LOCK TABLES `presupuesto_repuestos` WRITE;
/*!40000 ALTER TABLE `presupuesto_repuestos` DISABLE KEYS */;
INSERT INTO `presupuesto_repuestos` VALUES (1,1,1,2,'2016-06-07 12:24:10',1),(2,1,2,2,'2016-06-07 12:24:10',1),(3,1,3,2,'2016-06-07 12:24:10',1),(4,1,4,1,'2016-06-07 12:24:10',1),(5,1,5,1,'2016-06-07 12:24:10',1);
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
INSERT INTO `proveedor` VALUES (1,'TECNOSUR',234356565,'Esmeralda 2411','tecnos@gmail.com','Ramiro Fleita','153424241','fleitram@gmail.com','tecnos_pedidos@gmail.com','2016-05-31 00:21:06',1,1),(2,'CENTRO TECNO',423143251,'Guayaquil 4342','centrotecno@gmail.com','Esteban Flores','1523515215432','esteban@gmail.com','juangabrielferreyra93@gmail.com','2016-06-07 18:31:57',1,1);
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
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor_marca`
--

LOCK TABLES `proveedor_marca` WRITE;
/*!40000 ALTER TABLE `proveedor_marca` DISABLE KEYS */;
INSERT INTO `proveedor_marca` VALUES (1,1,0),(2,1,2),(14,2,1),(13,2,2),(12,2,3),(11,2,0),(15,2,4);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reparaciones`
--

LOCK TABLES `reparaciones` WRITE;
/*!40000 ALTER TABLE `reparaciones` DISABLE KEYS */;
INSERT INTO `reparaciones` VALUES (1,'JOAQUIN TELECHEA',NULL,0,0,'se reparo correctamente',20),(2,'JOAQUIN TELECHEA',NULL,0,0,'',18),(3,'JOAQUIN TELECHEA',NULL,0,0,'',16);
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reparaciones_repuestos`
--

LOCK TABLES `reparaciones_repuestos` WRITE;
/*!40000 ALTER TABLE `reparaciones_repuestos` DISABLE KEYS */;
INSERT INTO `reparaciones_repuestos` VALUES (1,1,2,2,NULL,1),(2,2,2,1,NULL,1),(3,2,1,3,NULL,1),(4,3,1,2,NULL,1),(5,4,1,1,NULL,1),(6,4,2,1,NULL,1),(7,4,3,2,NULL,1),(8,4,4,2,NULL,1),(9,4,5,2,NULL,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repuesto`
--

LOCK TABLES `repuesto` WRITE;
/*!40000 ALTER TABLE `repuesto` DISABLE KEYS */;
INSERT INTO `repuesto` VALUES (1,'Botonera Galaxy S3',1350,5,0,'2016-04-30 00:05:20',0,1),(2,'Modulo Pantalla',32143,4,2,'2016-04-30 00:05:20',0,1),(3,'Teclado p3',300,3,1,'2016-05-29 00:29:08',0,1),(4,'Lamparita',15,250,1,'2016-06-03 19:07:15',0,1),(5,'Otra Lamparita',23,45,2,'2016-06-03 19:07:39',0,1),(6,'Fuente 450-os',700,10,3,'2016-06-07 13:31:24',0,1),(7,'Disco Rigido',1050,10,1,'2016-06-07 13:39:40',0,1),(12,'Cooler',23,80,4,'2016-06-07 18:30:46',0,1);
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
INSERT INTO `tipo_producto` VALUES (0,'AIRE ACONDICIONADOS','2016-04-30 00:00:23',1,1),(1,'CELULAR','2016-04-30 00:00:23',1,1),(2,'TELEVISOR LED','2016-04-30 00:00:23',1,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'jefe','NOMBRE_JEFE','APELLIDO_JEFE','jefe',1,1,'2016-04-29 23:35:33'),(2,'administrativo','NOMBRE_ADMINISTRATIVO','APELLIDO_ADMINISTRATIVO','administrativo',2,1,'2016-05-18 23:39:19'),(3,'tecnico','NOMBRE_TECNICO','APELLIDO_TECNICO','tecnico',3,1,'2016-05-18 23:39:19');
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

-- Dump completed on 2016-06-07 18:39:43
