create database db_reto;

CREATE TABLE db_reto.Empleado(
id int(5) NOT NULL auto_increment primary key,
nombre varchar(30) NOT NULL,
apellidos varchar(30) NOT NULL,
numero_documento varchar(10) NOT NULL,
correo varchar(30),
telefono varchar(30) NOT NULL,
activo boolean NOT NULL,
salario float NOT NULL,
id_departamento int(5));

CREATE TABLE db_reto.Departamento(
id int(5) NOT NULL auto_increment primary key,
nombre varchar(30) NOT NULL,
descripcion varchar(100) NOT NULL,
codigo int(5) NOT NULL);

CREATE TABLE db_reto.Funciones(
id int(5) NOT NULL auto_increment primary key,
nombre varchar(30) NOT NULL,
descripcion varchar(100) NOT NULL);

ALTER TABLE db_reto.Empleado 
ADD CONSTRAINT FK_departamento 
FOREIGN KEY (id_departamento) 
REFERENCES db_reto.Departamento (id); 

ALTER TABLE db_reto.Funciones
ADD CONSTRAINT FK_departamento_funciones
FOREIGN KEY (id_departamento) 
REFERENCES db_reto.Departamento (id); 

alter table db_reto.empleado
rename column numeroDocumento to numero_documento;

INSERT INTO db_reto.Empleado (nombre, apellidos, numero_documento, correo, telefono, activo, salario) 
VALUES ('Santi','Ramirez','102045','sebas@corre.com','275420','1','100000000');

INSERT INTO db_reto.Departamento (nombre, descripcion, codigo) 
VALUES ('Antioquia', 'Departamento de Colombia','5');

INSERT INTO db_reto.Departamento (nombre, descripcion, codigo) 
VALUES ('Cundinamarca', 'Departamento de Colombia','4');

INSERT INTO db_reto.Funciones (nombre, descripcion, id_departamento) 
VALUES ('Analista Developer', 'Analisis Desarrollo de software','1');

select nombre from db_reto.departamento;
