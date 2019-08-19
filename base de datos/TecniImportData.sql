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

INSERT INTO Matriz(id_matriz,direccion,tipoLocalidad) VALUES
("0001","Av. Victor Emilio Estrada y Circunvalacion Sur","Matriz"),
("0002","Av. Francisco de Orellana y Calle Sexta","Sucursal"),
("0003","Km 16 1/2 via a Daule","Bodega");

INSERT INTO Usuario(cedula,usuario,clave,TipoUsuario,matriz_id,isAdmin) VALUES
("0950165811","jfmorale","0950165811",1,"0002",false),
("0950165860","pbmoral","pbmoral",2,"0002",false),
("1306944461","celema","guayaquil",3,"0002",true),
("0348288833","jasaman","348288833",1,"0003",false),
("0930074422","dmendoza","lister",1,"0002",true);

INSERT INTO Repartidor(cedula,MatriculaVehiculo,telefono,id_jefeBodega) VALUES
("1304858388","ABC-3232","0940832978","0950165860"),
("0930210399","HFS-1212","0956734988","0950165860"),
("0952859322","GBE-5050","0939443454","0950165860");

INSERT INTO Cliente(cedula,direccion,telefono) VALUES
("0946274823","Cdla. Primavera 1. Secap Duran","0979367299"),
("1204758399","Alborada IX Villa 6 Mz G","0938433423"),
("0947586902","Cdla La Chala, Callejon 1 Mz 11","0948769889"),
("1304858388","Cdla Cisne 2, 10 y Francisco Segura","0948654345"),
("0947284555","Av. Victor Emilio Estrada y Las Monjas","0958993348"),
("0943083432","Guasmo Sur Solar ZY Vlla 3432","0923174323");

INSERT INTO Ruta(id_ruta,Realizado,id_repartidor,id_jefeBodega) VALUES
(default,"F","1304858388","0950165860"),
(default,"F","0930210399","0950165860");

INSERT INTO pedido(id_pedido, id_matriz, id_cliente, id_vendedor, id_jefeBodega, id_ruta) VALUES
(default,NULL, "0946274823" , "0950165811","0950165860",1),
(default,NULL, "0943083432" , "0950165811","0950165860",2),
(default,NULL, "0947284555" , "0950165811","0950165860",NULL),
(default,NULL, "0943083432" , "0950165811","0950165860",NULL),
(default,NULL, "0947284555" , "0950165811","0950165860",1),
(default,NULL, "0947586902" , "0950165811","0950165860",NULL),
(default,NULL, "0946274823" , "0950165811","0950165860",NULL);

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

INSERT INTO Matriz(id_matriz,direccion,tipoLocalidad) VALUES
("0004","Av. Olmedo y Boyaca","Bodega"),
("0005","Venezuela y Antepara","Sucursal"),
("0006","Km Via a la Costa","Matriz");

INSERT INTO stock(id_stock,id_producto,stock,id_matriz) VALUES
(default,4,760,"0004"),
(default,4,126,"0005"),
(default,6,134,"0006");

drop user if exists jasaman; 
create user 'jasaman'@'%' identified by '348288833';
grant all privileges on *.* to 'jasaman'@'%' with grant option;
flush privileges;

drop user if exists dmendoza;
create user 'dmendoza'@'%' identified by 'lister';
grant all privileges on *.* to 'dmendoza'@'%' with grant option;
flush privileges;

drop user if exists pbmoral;
create user 'pbmoral'@'%' identified by 'pbmoral';
grant all privileges on *.* to 'pbmoral'@'%' with grant option;
flush privileges;

drop user if exists celema;
create user 'celema'@'%' identified by 'guayaquil';
grant all privileges on *.* to 'celema'@'%' with grant option;
flush privileges;

drop user if exists jfmorale;
create user 'jfmorale'@'%' identified by '0950165811';
grant all privileges on *.* to 'jfmorale'@'%' with grant option;
flush privileges;
