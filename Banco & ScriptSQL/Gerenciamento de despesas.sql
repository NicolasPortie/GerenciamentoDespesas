CREATE TABLE usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nome_usuario VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    senha VARCHAR(20) NOT NULL
);

CREATE TABLE categoria (
    id_categoria INT PRIMARY KEY AUTO_INCREMENT,
    nome_categoria VARCHAR(30) NOT NULL, -- mudar no codigo
    id_usuario INT NOT NULL,
    CONSTRAINT fk_usuario_categoria FOREIGN KEY (id_usuario)
        REFERENCES usuario (id_usuario)
);

CREATE TABLE despesa (
    id_despesa INT PRIMARY KEY AUTO_INCREMENT,
    data_despesa DATE NOT NULL,
    descricao VARCHAR(100) NOT NULL, 
    valor FLOAT NOT NULL,
    id_categoria INT NOT NULL,
    id_usuario INT NOT NULL,
    CONSTRAINT fk_categoria_despesa FOREIGN KEY (id_categoria)
        REFERENCES categoria (id_categoria),
    CONSTRAINT fk_usuario_despesa FOREIGN KEY (id_usuario)
        REFERENCES usuario (id_usuario)
);
