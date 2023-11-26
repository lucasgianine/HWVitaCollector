DROP DATABASE IF EXISTS vita;
CREATE DATABASE IF NOT EXISTS vita;


USE vita ;

CREATE TABLE IF NOT EXISTS Hospital (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  telefone VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  cnpj VARCHAR(255) NOT NULL,
  codHospital VARCHAR(255) NOT NULL,
  site VARCHAR(255) NOT NULL,
  categoria VARCHAR(10) NOT NULL, 
  CONSTRAINT chkCategoria CHECK(categoria IN('Publico', 'Particular'))
) AUTO_INCREMENT = 200;

CREATE TABLE IF NOT EXISTS Endereco (
  id INT PRIMARY KEY AUTO_INCREMENT,
  fkHospital INT NOT NULL,
  estado VARCHAR(255) NOT NULL,
  cidade VARCHAR(255) NOT NULL,
  cep VARCHAR(255) NOT NULL,
  logradouro VARCHAR(255) NOT NULL,
  numEndereco VARCHAR(255) NOT NULL,
  complemento VARCHAR(255) NULL,
  FOREIGN KEY (fkHospital) REFERENCES Hospital (id)
  ) AUTO_INCREMENT = 100;

CREATE TABLE IF NOT EXISTS Funcionario (
  id INT PRIMARY KEY AUTO_INCREMENT,
  fkHospital INT NOT NULL,
  email VARCHAR(255) NOT NULL,
  senha VARCHAR(255) NOT NULL,
  nome VARCHAR(255) NOT NULL,
  telefone VARCHAR(255) NOT NULL,
  funcao VARCHAR(255) NOT NULL,
  FOREIGN KEY (fkHospital) REFERENCES Hospital (id)
)AUTO_INCREMENT = 300;


CREATE TABLE IF NOT EXISTS ParametrosAlerta(
id INT PRIMARY KEY auto_increment,
fkHospital INT NOT NULL,
maxTempProcessador VARCHAR(50),
maxUsoProcessador VARCHAR(50),
maxUsoMemoria VARCHAR(50),
minLivreDisco VARCHAR(50),
maxTempoDeAtividade VARCHAR(50),
minQtdUsb VARCHAR(50),
porcentagemMaximaRamProcesso VARCHAR(50),
tempoParaAlertaSec VARCHAR(50),
FOREIGN KEY (fkHospital) references Hospital (id)
);

CREATE TABLE IF NOT EXISTS Maquina (
  uuid VARCHAR(255) PRIMARY KEY  ,
  fkHospital INT NOT NULL,
  localidade VARCHAR(255),
  responsavel VARCHAR(255),
  FOREIGN KEY (fkHospital) REFERENCES Hospital (id)
)AUTO_INCREMENT = 400;

CREATE TABLE IF NOT EXISTS ProcessoRegistro (
id INT primary key NOT NULL auto_increment,
fkMaquina  VARCHAR(255) NOT NULL,
nome VARCHAR (255),
dtRegistro DATETIME,
threads INT,
usoMemoriaRam VARCHAR(255),
FOREIGN KEY (fkMaquina) REFERENCES Maquina (uuid)
);

CREATE TABLE IF NOT EXISTS DiscoRegistro (
id INT primary key auto_increment,
fkMaquina VARCHAR(255) NOT NULL,
modelo VARCHAR(255),		
dtRegistro DATETIME,
armazenamentoTotal VARCHAR(255),
armazenamentoLivre VARCHAR(255),
FOREIGN KEY (fkMaquina) REFERENCES Maquina (uuid)
);

CREATE TABLE IF NOT EXISTS CpuRegistro (
id INT primary key auto_increment,
fkMaquina VARCHAR(255) NOT NULL,
dtRegistro DATETIME,
temperatura VARCHAR(50),
usoPorcentagem VARCHAR(50),
FOREIGN KEY (fkMaquina) REFERENCES Maquina (uuid)
);

CREATE TABLE IF NOT EXISTS MemoriaRegistro(
id INT primary key auto_increment,
fkMaquina VARCHAR(255) NOT NULL,
dtRegistro DATETIME,
qtdTotal VARCHAR(45),
usoMemoria VARCHAR(45),
FOREIGN KEY (fkMaquina) references Maquina (uuid)
);

CREATE TABLE IF NOT EXISTS SistemaRegistro(
id INT PRIMARY KEY auto_increment,
fkMaquina VARCHAR(255) NOT NULL,
dtRegistro DATETIME,
tempoDeAtividadeSistema VARCHAR(45),
qtdDispositivosUsb INT,
FOREIGN KEY (fkMaquina) references Maquina (uuid)
);



CREATE TABLE IF NOT EXISTS Ocorrencia (
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  fkMaquina VARCHAR(255) NOT NULL,
  dtOcorrencia DATETIME,
  categoria VARCHAR(255),
  constraint chkCategoriaOcorrencia CHECK(categoria IN ('hardware','software')),
  componente VARCHAR(255),
  metrica VARCHAR(255),
  descricao VARCHAR(255),
  FOREIGN KEY (fkMaquina) REFERENCES Maquina (uuid)
);

CREATE TABLE IF NOT EXISTS Chamado(
id INT PRIMARY KEY auto_increment,
fkMaquina VARCHAR(255) NOT NULL,
fkResponsavel int NOT NULL,
titulo VARCHAR(100),
descricao VARCHAR(255),
setor VARCHAR(255),
isClosed BOOLEAN,
foreign key (fkMaquina) references Maquina(uuid),
foreign key (fkResponsavel) references Funcionario (id)
);


INSERT INTO Hospital VALUES 
(NULL,"Alberto Einstein", "11961707443","alberteinstein@hospital.com","60.765.823/0001-30","824fd9d8-6134-11ee-8c99-0242ac120002", "https://www.einstein.br/", "Particular"),
(NULL,"HOSPITAL MUNICIPAL NOSSA SENHORA DA LAPA", "3438131231","hospitalapa@hospital.com","18.278.069/0001-47","7e6de880-6136-11ee-8c99-0242ac120002", "https://postosdesaude.com.br/mg/vazante/hospitais-publicos/hospital-municipal-nossa-senhora-da-lapa-1-4951", "Publico");


INSERT INTO Endereco VALUES
(NULL,200,"Sao Paulo","Sao Paulo","01414952","Rua do hospital albert eintein","900","Do outro lado da rua é o outro lado da rua do hospital"),
(NULL,201,"Sao Paulo","Sao Paulo","01445652","Rua do hospital da lapa","1452","");


INSERT INTO Funcionario VALUES
(null,200,"lucassantos@gmail.com","senha@123","Lucão","12312312","Gerente"),
(null,200,"leo@gmail.com","senha@123","Leo","12312312","Suporte");

INSERT INTO ParametrosAlerta VALUES 
(null,200,'75','95','90','9126805504','172800','2','60','30');
desc ParametrosAlerta;
