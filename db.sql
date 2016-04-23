CREATE TABLE `ciente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipodoc` int(11) DEFAULT NULL,
  `nrodoc` int(11) DEFAULT NULL,
  `nombre` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `apellido` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `direccion` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `telefono` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `mail` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `idusuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;



CREATE TABLE `marca_producto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `detalle` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `idusuario` int(11) DEFAULT NULL,
  `habilitado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;



CREATE TABLE `tipo_producto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `detalle` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `idusuario` int(11) DEFAULT NULL,
  `habilitado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;