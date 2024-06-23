CREATE DATABASE	Biblioteca;
USE Biblioteca;

CREATE TABLE membros (
    cpf char(11) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    telefone VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE cartoes (
    cartao_id INT PRIMARY KEY,
    numero_cartao INT UNIQUE NOT NULL,
    cpf_membro CHAR(11) NOT NULL,
    FOREIGN KEY (cpf_membro) REFERENCES membros(cpf)
);

CREATE TABLE autores (
    autor_id INT PRIMARY KEY,
    nome VARCHAR(90) UNIQUE NOT NULL
);

CREATE TABLE livros (
    livro_id INT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    autor_id INT NOT NULL,
    FOREIGN KEY (autor_id) REFERENCES autores(autor_id)
);

CREATE TABLE emprestimos (
    emprestimo_id INT PRIMARY KEY,
    cartao_id INT NOT NULL,
    livro_id INT NOT NULL,
    data_emprestimo VARCHAR(30) NOT NULL,
    data_devolucao VARCHAR(30) NOT NULL,
    FOREIGN KEY (cartao_id) REFERENCES cartoes(cartao_id),
    FOREIGN KEY (livro_id) REFERENCES livros(livro_id)
);

select * from autores;
select * from membros;
select * from cartoes;
select * from livros;
select * from emprestimos;
SET SQL_SAFE_UPDATES = 0;
delete from autores where autor_id = 211;
delete from livros where autor_id = 211;
delete from emprestimos where cartao_id = 427;
truncate table livros;
truncate table autores;
select * from livros;


select cartao_id from cartoes where cpf_membro = 123456789;













