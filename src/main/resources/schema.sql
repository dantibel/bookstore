CREATE TABLE IF NOT EXISTS category
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS book
(
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    publication_year INT NOT NULL,
    isbn VARCHAR(255) NOT NULL,
    price DECIMAL(6, 2) NOT NULL,
    category INT REFERENCES category(id)
);
