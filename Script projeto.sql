CREATE DATABASE	Biblioteca;
USE Biblioteca;

CREATE TABLE membros (
    cpf char(11) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    telefone VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE cartoes (
    cartao_id INT AUTO_INCREMENT PRIMARY KEY,
    numero_cartao VARCHAR(20) UNIQUE NOT NULL,
    cpf_membro char(11) NOT NULL,
    FOREIGN KEY (cpf_membro) REFERENCES membros(cpf)
);

CREATE TABLE autores (
    autor_id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(90) UNIQUE NOT NULL
);

CREATE TABLE livros (
    livro_id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    autor_id INT NOT NULL,
    FOREIGN KEY (autor_id) REFERENCES autores(autor_id)
);

CREATE TABLE emprestimos (
    emprestimo_id INT AUTO_INCREMENT PRIMARY KEY,
    cpf_membro char(11) NOT NULL,
    livro_id INT NOT NULL,
    data_emprestimo DATE NOT NULL,
    data_devolucao DATE,
    FOREIGN KEY (cpf_membro) REFERENCES membros(cpf),
    FOREIGN KEY (livro_id) REFERENCES livros(livro_id)
);






