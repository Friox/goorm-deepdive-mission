DROP TABLE IF EXISTS loan;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS users;

CREATE TABLE books
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    isbn         VARCHAR(13)  NOT NULL,
    title        VARCHAR(255) NOT NULL,
    author       VARCHAR(255) NOT NULL,
    publish_date date         NOT NULL,
    CONSTRAINT pk_books PRIMARY KEY (id)
);

CREATE TABLE loan
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    user_id     BIGINT NOT NULL,
    book_id     BIGINT NOT NULL,
    start_date  date   NOT NULL,
    end_date    date   NOT NULL,
    return_date date NULL,
    CONSTRAINT pk_loan PRIMARY KEY (id)
);

CREATE TABLE users
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    username VARCHAR(255) NULL,
    password VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NULL,
    realname VARCHAR(255) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE books
    ADD CONSTRAINT uc_books_isbn UNIQUE (isbn);

-- CREATE UNIQUE INDEX idx_books_isbn
--     ON books (isbn);

ALTER TABLE users
    ADD CONSTRAINT uc_users_username UNIQUE (username);

ALTER TABLE loan
    ADD CONSTRAINT FK_LOAN_ON_BOOK FOREIGN KEY (book_id) REFERENCES books (id);

ALTER TABLE loan
    ADD CONSTRAINT FK_LOAN_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);