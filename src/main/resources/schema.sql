CREATE TABLE IF NOT EXISTS Category
(
    id INT AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS Book
(
    id INT AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    publicationYear INT NOT NULL,
    isbn VARCHAR(255) NOT NULL,
    price DECIMAL(6, 2) NOT NULL,
    category INT,
    PRIMARY KEY(id)
);

ALTER TABLE Book ADD FOREIGN KEY (category) REFERENCES Category(id);