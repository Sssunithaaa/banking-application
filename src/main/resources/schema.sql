CREATE TABLE user (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       fullname VARCHAR(255) NOT NULL,
                       type VARCHAR(50) NOT NULL, -- Account type, e.g., 'Savings Peso'
                       number VARCHAR(20) NOT NULL UNIQUE, -- Account number
                       balance DECIMAL(15, 2) NOT NULL DEFAULT 0.0,
                       is_admin BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE transaction (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              user_id BIGINT NOT NULL,
                              title VARCHAR(255) NOT NULL,
                              amount DECIMAL(15, 2) NOT NULL,
                              type VARCHAR(50) NOT NULL, -- e.g., 'credit' or 'debit'
                              date DATE NOT NULL,
                              FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE budget (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         user_id BIGINT NOT NULL,
                         title VARCHAR(255) NOT NULL,
                         amount DECIMAL(15, 2) NOT NULL,
                         FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
