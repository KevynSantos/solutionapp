create database if not exists solution;

use solution;
 
create table if not exists usuarios(
	id int auto_increment primary key,
	nome varchar(250),
	cpf varchar(15),
	cep varchar(10),
	logradouro varchar(400),
	bairro varchar(50),
	cidade varchar(50),
	estado varchar(2),
	data_criacao datetime,
	data_atualizacao datetime
)