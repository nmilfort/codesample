DROP DATABASE IF EXISTS gameDB;
CREATE DATABASE gameDB;

USE gameDB;

CREATE TABLE game(
    id INT PRIMARY KEY AUTO_INCREMENT,
    ans VARCHAR(4) NOT NULL,
    finished boolean DEFAULT false
);

CREATE TABLE rounds(
    id INT PRIMARY KEY AUTO_INCREMENT,
    guess VARCHAR(4) NOT NULL,
    result VARCHAR(10),
    timeDate datetime,
    gameId INT NOT NULL,
    FOREIGN KEY (gameId) REFERENCES game(id)
);