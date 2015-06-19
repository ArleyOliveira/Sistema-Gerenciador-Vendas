--- Script Sql ---

create table usuario(
	cpf VARCHAR(15) not null  primary key,
	rg VARCHAR(15) not null,
	nome VARCHAR(100) not null,
	idade INTEGER,
	telefone varchar(15),
	celular varchar(15),
	endereco varchar(100),
	login varchar(50) not null,
	senha varchar(35) not null,
	permissao integer default 5,
	f boolean default true
);

create table cliente(
	cpf VARCHAR(15) not null  primary key,
	rg VARCHAR(15) not null,
	nome VARCHAR(100) not null,
	idade INTEGER,
	telefone varchar(15),
	celular varchar(15),
	endereco varchar(100),
	status boolean default true
	f boolean default true
);

-- produto --
create table produto(
	codigo integer identity,
	descricao varchar(200) not null,  
	estoque int not null,
	valorUnitario double not null,
	quantidadeMinima integer default 0
);
--Vendas ---
CREATE TABLE VENDA(
	CODIGO IDENTITY NOT NULL,
	DATA DATE,
	VALORTOTAL DOUBLE,
	USUARIO VARCHAR(15),
	USUARIONOME VARCHAR(100),
	CLIENTE VARCHAR(15),
	CLIENTENOME VARCHAR(100)	
);

CREATE TABLE ITENSCOMPRA(
	PRODUTOCODIGO INT NOT NULL,
	DESCRICAO VARCHAR(200),
	VALORUNITARIO DOUBLE NOT NULL,
	VALORTOTAL DOUBLE NOT NULL,
	VENDA INTEGER NOT NULL,
	FOREIGN KEY(VENDA) REFERENCES VENDA(CODIGO)
);


CREATE TABLE CONTA(
	CODIGO INTEGER IDENTITY NOT NULL,
	STATUS BOOLEAN DEFAULT TRUE,
	VALORTOTAL DOUBLE NOT NULL,
	DATA DATE DEFAULT CURRENT_DATE,
	VENDA INTEGER NOT NULL,
	FOREIGN KEY(VENDA) REFERENCES VENDA(CODIGO)
);

CREATE TABLE CAIXADIARIO(
	CODIGO INTEGER IDENTITY NOT NULL,
	DATA DATE DEFAULT CURRENT_DATE,
	STATUS BOOLEAN DEFAULT TRUE,
	VALORINICIAL DOUBLE DEFAULT 0.0,
	VALORFINAL DOUBLE
);

CREATE TABLE LIQUIDACAO(
	CONTA INTEGER NOT NULL,
	CAIXADIARIO INTEGER NOT NULL,
	VALORPAGO DOUBLE NOT NULL,
	FOREIGN KEY (CONTA) REFERENCES CONTA(CODIGO),
	FOREIGN KEY (CAIXADIARIO) REFERENCES CAIXADIARIO(CODIGO)
);


