CREATE TABLE Category
(
    id INT PRIMARY KEY,
    name VARCHAR(255),
)

CREATE TABLE Book
(
    id INT PRIMARY KEY,
    title VARCHAR(255),
    author VARCHAR(255),
    publicationYear INT,
    isbn VARCHAR(255),
    price DECIMAL(6, 2),
    category INT,
)

ALTER TABLE Book ADD FOREIGN KEY (category) REFERENCES Category(id);