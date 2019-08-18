
use TecnoImport;
-- Here it's gonna be charged with datas for getting sure 
-- all made before were correct
-- -----------------------
INSERT INTO Persona(cedula,nombre,apellido) VALUES
("0950165811","Francisco","Morales"),
("0950165860","Pablo","Morales"),
("1306944461","Selena","Mendoza"),
("0930074422","Darwin","Mendoza"),
("0946274823","Julio","Murrieta"),
("1204758399","Enrique","Dominguez"),
("0947586902","Luis","Lopez"),
("0952859322","Ivan","Gutierrez"),
("1304858388","Andres","Salvatierra"),
("0348288833","Javier","Samaniego"),
("0943083432","Jennifer","Jara"),
("0947284555","Mariuxi","Ordones"),
("0930210399","Julia","Cuenca");

INSERT INTO TipoUsuario(id_tipoUsuario,descripcion) VALUES
(1,"VENDEDOR"),
(2,"JEFE DE BODEGA"),
(3,"GERENTE");

INSERT INTO Usuario(cedula,usuario,clave,TipoUsuario,isAdmin) VALUES
("0950165811","jfmorale","0950165811",1,false),
("0950165860","pbmoral","pbmoral",2,false),
("1306944461","celema","guayaquil",3,false),
("0348288833","jasaman","348288833",2,false),
("0930074422","dmendoza","lister",1,false);

INSERT INTO Repartidor(cedula,MatriculaVehiculo,telefono,id_jefeBodega) VALUES
("1304858388","ABC-3232","0940832978","0950165860"),
("0930210399","HFS-1212","0956734988","0348288833"),
("0952859322","GBE-5050","0939443454","0950165860");

INSERT INTO Matriz(id_matriz,direccion,tipoLocalidad) VALUES
("0001","Av. Victor Emilio Estrada y Circunvalacion Sur","Matriz"),
("0002","Av. Francisco de Orellana y Calle Sexta","Sucursal"),
("0003","Km 16 1/2 via a Daule","Bodega");

INSERT INTO Cliente(cedula,direccion,telefono) VALUES
("0946274823","Cdla. Primavera 1. Secap Duran","0979367299"),
("1204758399","Alborada IX Villa 6 Mz G","0938433423"),
("0947586902","Cdla La Chala, Callejon 1 Mz 11","0948769889"),
("1304858388","Cdla Cisne 2, 10 y Francisco Segura","0948654345"),
("0947284555","Av. Victor Emilio Estrada y Las Monjas","0958993348"),
("0943083432","Guasmo Sur Solar ZY Vlla 3432","0923174323");

INSERT INTO Ruta(id_ruta,Realizado,id_repartidor,id_jefeBodega) VALUES
(1,"F","1304858388","0950165860");

INSERT INTO FormaPago(id_formaPago,descripcion) VALUES
(1,"TARJETA DE CREDITO/DEBITO"),
(2,"EFECTIVO");

INSERT INTO Compra(id_compra,fecha,nro_Factura,totalImpuesto,sub_totalExcento,TotalCompra,id_vendedor,forma_pago) VALUES
(1,"2019-08-16","12345678",12.00,95.00,107.00,"0950165811",1),
(2,"2019-08-16","23456789",11.20,83.12,94.32,"0950165811",2),
(3,"2019-08-15","34567890",2.34,8.90,11.24,"0348288833",2);

INSERT INTO Producto(id_producto,precio,nombre,categoria,descripcion) VALUES
(default,120,"Celular Xiaomi Redmi","Celular","Modelo 5 Plus"),
(default,950,"CPU XTRATECH","Computadoras","Intel Core I5"),
(default,500,"Impresora HP","Impresoras","tinta continua");

INSERT INTO stock(id_stock,id_producto,descripcion,id_matriz) VALUES
(default,1,"Celulares Xiaomi Redmi","0001"),
(default,2,"CPU XTRATECH","0003"),
(default,3,"Impresora HP tinta continua","0002");

DELETE FROM stock
WHERE descripcion="Celulares Xiaomi Redmi";
DELETE FROM stock
WHERE descripcion="CPU XTRATECH";
DELETE FROM stock
WHERE descripcion="Impresora HP tinta continua";
ALTER TABLE stock CHANGE descripcion Stock int(50);
INSERT INTO stock(id_stock,id_producto,stock,id_matriz) VALUES
(default,1,600,"0001"),
(default,2,26,"0003"),
(default,3,100,"0002");

INSERT INTO Producto(id_producto,precio,nombre,categoria,descripcion) VALUES
(default,520,"Celular Samsung J7","Celular","Doble camara"),
(default,950,"Computadora HP","Computadoras","Intel Core I7"),
(default,670,"Impresora Epson","Impresoras","tinta de cartucho");

INSERT INTO stock(id_stock,id_producto,stock,id_matriz) VALUES
(default,4,760,"0003"),
(default,6,126,"0003"),
(default,5,134,"0001");
