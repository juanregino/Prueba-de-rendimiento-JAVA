CREATE DATABASE demodaoutlet;
USE demodaoutlet ;

CREATE TABLE tienda (
id INT PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(20) NOT NULL ,
ubicacion VARCHAR(20) NOT NULL
);

CREATE TABLE producto (
id INT PRIMARY KEY AUTO_INCREMENT ,
nombre VARCHAR(20) NOT NULL  ,
precio DECIMAL NOT  NULL,
id_tienda INT ,
FOREIGN KEY (id_tienda) REFERENCES tienda(id) ON DELETE CASCADE
);

CREATE TABLE cliente (
id INT PRIMARY KEY AUTO_INCREMENT ,
nombre VARCHAR(20) NOT NULL  ,
apellido VARCHAR(40) NOT NULL,
email  VARCHAR(40) NOT NULL
);
CREATE TABLE compra (
id INT PRIMARY KEY AUTO_INCREMENT ,
id_cliente INT   ,
id_producto INT,
fecha_compra DATE NOT NULL,
cantidad INT NOT NULL,
FOREIGN KEY (id_producto) REFERENCES producto(id) ON DELETE CASCADE,
FOREIGN KEY (id_cliente) REFERENCES cliente(id) ON DELETE CASCADE
);

ALTER TABLE producto ADD COLUMN stock INT NOT NULL ;



INSERT INTO tienda (id ,nombre,ubicacion) values(1 , "branchos" , "local-2" );
INSERT INTO tienda (id , nombre, ubicacion) values( 2 , "pilatos" , "local-10");
INSERT INTO tienda (id , nombre, ubicacion) values( 3 , "koaj" , "local-20");
INSERT INTO tienda (id , nombre, ubicacion) values( 4 , "Diesel" , "local-30");
INSERT INTO tienda (id , nombre, ubicacion) values( 5 , "Levis" , "local-50");
