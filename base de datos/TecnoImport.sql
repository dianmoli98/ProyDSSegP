DROP DATABASE IF EXISTS TecnoImport;

CREATE DATABASE TecnoImport;

-- Working above Software Design DataBase, this one is 
--  project advancing and have to be approved for all participants
-- ---------------------------------
-- 12.08.2019

use TecnoImport;
-- MySQL dump 10.13  Distrib 5.7.17, for windows10.12 (x86_64)
-- IPv4 Address : 192.168.56.1(Preferred)
-- HostName: DESKTOP-JFAQJ3B    Database: TecnoImport
-- ------------------------------------------------------
-- Server version	5.6.20


DROP TABLE IF EXISTS Persona;
CREATE TABLE Persona(
  `cedula` varchar(11) NOT NULL ,
  `nombre` varchar(30) NULL DEFAULT "CONSUMIDOR FINAL",
  `apellido` varchar(30) NULL DEFAULT "CONSUMIDOR FINAL",
  PRIMARY KEY(`cedula`),
  UNIQUE KEY `cedula_unique` (`cedula`)
  )ENGINE=InnoDB DEFAULT CHARSET=latin1;
  
DROP TABLE IF EXISTS TipoUsuario;
CREATE TABLE TipoUsuario(
  `id_tipoUsuario` INTEGER(6) NOT NULL AUTO_INCREMENT ,
  `descripcion` varchar(45) NOT NULL,
  PRIMARY KEY(`id_tipoUsuario`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=100;


  
DROP TABLE IF EXISTS Usuario;
CREATE TABLE Usuario(
  `cedula` varchar(10) NOT NULL,
  `usuario` varchar(45) NOT NULL,
  `clave` varchar(45) NOT NULL,
  `TipoUsuario` integer(6) NOT NULL,
  `isAdmin` BOOLEAN NULL DEFAULT False,
  PRIMARY KEY(`cedula`),
  CONSTRAINT `_cedula` FOREIGN KEY (`cedula`) REFERENCES `Persona` (`cedula`) ,
  CONSTRAINT `_TipoUsuario` FOREIGN KEY (`TipoUsuario`) REFERENCES `TipoUsuario` (`id_tipoUsuario`) 
 
 )ENGINE=InnoDB DEFAULT CHARSET=latin1;
  
  
DROP TABLE IF EXISTS Repartidor;
CREATE TABLE Repartidor(
  `cedula` varchar(10) NOT NULL,
  `MatriculaVehiculo` varchar(10) NOT NULL,
  `Telefono` varchar(15) NOT NULL,
  `id_jefeBodega` varchar(10) NOT NULL,
  PRIMARY KEY(`cedula`),
  CONSTRAINT `_id_jefeBodega` FOREIGN KEY (`id_jefeBodega`) REFERENCES `Usuario` (`cedula`),
  CONSTRAINT `__cedula` FOREIGN KEY (`cedula`) REFERENCES `Persona` (`cedula`) 
  )ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS Matriz;
CREATE TABLE Matriz(
  `id_matriz` varchar(4) NOT NULL ,
  `direccion` varchar(100) NOT NULL,
  `tipoLocalidad` varchar(20) NULL DEFAULT "Matriz",
  PRIMARY KEY(`id_matriz`)
  )ENGINE=InnoDB DEFAULT CHARSET=latin1;
  
  
  DROP TABLE IF EXISTS Cliente;
CREATE TABLE Cliente(
  `cedula` varchar(11) NULL DEFAULT "99999999999",
  `direccion` varchar(100) NULL DEFAULT "COMPRA DIRECTA",
  `telefono` varchar(30) NULL DEFAULT "COMPRA DIRECTA",
  UNIQUE KEY `cedula_unique` (`cedula`),
  CONSTRAINT `_1_cedula` FOREIGN KEY (`cedula`) REFERENCES `Persona` (`cedula`) 
  )ENGINE=InnoDB DEFAULT CHARSET=latin1;
  
  DROP TABLE IF EXISTS Ruta;
  CREATE TABLE Ruta(
	`id_ruta` integer(8) NOT NULL AUTO_INCREMENT,
    `Realizado` character(1) NULL DEFAULT "F",
    `id_repartidor` varchar(11) NOT NULL,
    `id_jefeBodega` varchar(11) NOT NULL,
    PRIMARY KEY(`id_ruta`),
    CONSTRAINT `_id_repartidor` FOREIGN KEY (`id_repartidor`) REFERENCES `Repartidor`(`cedula`),
    CONSTRAINT `__id_jefeBodega` FOREIGN KEY (`id_jefeBodega`) REFERENCES `Usuario`(`cedula`)
    )ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS FormaPago;
CREATE TABLE FormaPago(
  `id_formaPago` integer(3)  NOT NULL AUTO_INCREMENT ,
  `descripcion` varchar(100) NOT NULL,
  PRIMARY KEY(`id_formaPago`)
  )ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS Compra;
  CREATE TABLE Compra(
	`id_compra` INTEGER(10)  NULL AUTO_INCREMENT,
    `fecha` DATE,
    `nro_Factura` varchar(8) NOT NULL,
    `totalImpuesto` FLOAT NOT NULL,
    `sub_totalExcento` FLOAT NOT NULL,
    `totalCompra` FLOAT NOT NULL,
    `id_vendedor` varchar(11) NOT NULL,
    `forma_pago` INTEGER(3) NOT NULL,
    UNIQUE KEY(`id_compra`),
    CONSTRAINT `__id_vendedor` FOREIGN KEY (`id_vendedor`) REFERENCES `Usuario`(`cedula`) ON DELETE NO ACTION,
    CONSTRAINT `__forma_pago` FOREIGN KEY (`forma_pago`) REFERENCES `FormaPago`(`id_formaPago`)
    )ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;
    
DROP TABLE IF EXISTS Producto;
  CREATE TABLE Producto(
	`id_producto` INTEGER(6) NOT NULL AUTO_INCREMENT,
    `precio` float DEFAULT 0.00,
    `nombre` varchar(20) NOT NULL,
    `categoria` varchar(20) NULL DEFAULT "SIN ASIGNACION",
    `descripcion` varchar(20) NULL DEFAULT "SIN DESCRIPCION",
    PRIMARY KEY(`id_producto`)
    )ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;    

DROP TABLE IF EXISTS Impuesto;
  CREATE TABLE Impuesto(
	`id_impuesto` INTEGER(3) NOT NULL AUTO_INCREMENT,
    `valor` int DEFAULT 0,
    `descripcion` varchar(20) NULL DEFAULT "SIN IVA",
    PRIMARY KEY(`id_impuesto`)
    )ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1; 
    
    
DROP TABLE IF EXISTS DetalleCompra;
  CREATE TABLE DetalleCompra(
	`id_DetalleCompra` INTEGER(6) NOT NULL AUTO_INCREMENT,
    `idCompra` INTEGER(10) NOT NULL,
    `idProducto` INTEGER(6) NOT NULL,
    `idImpuesto` INTEGER(3) NOT NULL,
    `cantidad` float(4) NOT NULL,
    PRIMARY KEY(`id_DetalleCompra`),
    CONSTRAINT `__idCompra` FOREIGN KEY (`IdCompra`) REFERENCES `Compra`(`id_compra`),
    CONSTRAINT `__idProducto` FOREIGN KEY (`idProducto`) REFERENCES `Producto`(`id_producto`),
    CONSTRAINT `__idImpuesto` FOREIGN KEY (`idImpuesto`) REFERENCES `Impuesto`(`id_impuesto`)
    )ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;
    
    DROP TABLE IF EXISTS Cotizacion;
  CREATE TABLE Cotizacion(
	`id_cotizacion` INTEGER(6) NOT NULL AUTO_INCREMENT,
    `fecha` DATE,
    `detalle` varchar(50) NOT NULL,
    `id_cliente` varchar(6) NOT NULL,
    `id_vendedor` varchar(5) NOT NULL,
    PRIMARY KEY(`id_cotizacion`),
    CONSTRAINT `_id_vendedor` FOREIGN KEY (`id_vendedor`) REFERENCES `Usuario`(`cedula`) ON DELETE NO ACTION,
    CONSTRAINT `_id_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `Cliente`(`cedula`)
    )ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;
    
    DROP TABLE IF EXISTS DetalleCotizacion;
  CREATE TABLE DetalleCotizacion(
	`id_DetalleCompra` INTEGER(6) NOT NULL AUTO_INCREMENT,
    `id_cotizacion` INTEGER(6) NOT NULL,
    `id_Producto` INTEGER(6) NOT NULL,
    `cantidad` float(4) NOT NULL,
    PRIMARY KEY(`id_DetalleCompra`),
    CONSTRAINT `__idCotizacion` FOREIGN KEY (`Id_cotizacion`) REFERENCES `Cotizacion`(`id_cotizacion`),
    CONSTRAINT `_idProducto` FOREIGN KEY (`id_Producto`) REFERENCES `Producto`(`id_producto`)
    )ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;
    
    DROP TABLE IF EXISTS Stock;
  CREATE TABLE Stock(
	`id_stock` INTEGER(6) NOT NULL AUTO_INCREMENT,
    `id_producto` INTEGER(6) DEFAULT 0,
    `descripcion` varchar(50) NULL DEFAULT "SIN ASIGNACION",
    `id_matriz` varchar(6) NOT NULL,
    PRIMARY KEY(`id_stock`),
    CONSTRAINT `_id_producto` FOREIGN KEY (`id_producto`) REFERENCES `Producto`(`id_producto`),
    CONSTRAINT `_id_matriz` FOREIGN KEY (`id_matriz`) REFERENCES `Matriz`(`id_matriz`)
    )ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;  
    
     DROP TABLE IF EXISTS Pedido;
  CREATE TABLE Pedido(
	`id_pedido` INTEGER(10) NOT NULL AUTO_INCREMENT,
    `id_matriz` varchar(6) NOT NULL,
    `id_cliente` varchar(6) NOT NULL,
    `id_vendedor` varchar(6) NOT NULL,
    `id_jefeBodega` varchar(6) NOT NULL,
    `id_ruta` INTEGER(8) NOT NULL,
    PRIMARY KEY(`id_pedido`),
    CONSTRAINT `__id_matriz`  FOREIGN KEY (`id_matriz`) REFERENCES `Matriz`(`id_matriz`),
    CONSTRAINT `_1_id_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `Cliente`(`cedula`),
    CONSTRAINT `_1_id_vendedor` FOREIGN KEY (`id_vendedor`) REFERENCES `Usuario`(`cedula`),
    CONSTRAINT `_1_id_jefeBodega` FOREIGN KEY (`id_jefeBodega`) REFERENCES `Usuario`(`cedula`) ,
    CONSTRAINT `_1_id_ruta` FOREIGN KEY (`id_ruta`) REFERENCES `Ruta`(`id_ruta`)
    )ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;  
    
    -- Here ending all database structure 
    -- 15.08.2019
    -- -----------------------------
    DROP VIEW IF EXISTS ConsultarUsuario;
    CREATE VIEW ConsultarUsuario AS
    SELECT Usuario.* FROM Usuario;
    
#trigger que permita crear el usuario una vez que se ingresa un usuario a la tabla Usuario
/*DELIMITER ||
CREATE TRIGGER crear_user AFTER INSERT ON Usuario
 FOR EACH ROW
 BEGIN
	SET @userio=(SELECT Usuario.usuario FROM Usuario WHERE usuario=new.usuario);
    SET @contrasena=(SELECT clave FROM Usuario WHERE clave=new.clave);
	create user userio@'%' identified by "contrasena";
	grant all privileges on *.* to userio@'%' with grant option;
	flush privileges;
END ||
DELIMITER ;
*/
create user 'jfmorale'@'%' identified by '0950165811';
grant all privileges on *.* to 'jfmorale'@'%' with grant option;
flush privileges;