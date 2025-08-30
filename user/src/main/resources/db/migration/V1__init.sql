CREATE TABLE address
(
    id           SERIAL PRIMARY KEY,
    street       VARCHAR(100) NOT NULL,
    number       VARCHAR(100) NOT NULL,
    neighborhood VARCHAR(100),
    city         VARCHAR(100) NOT NULL,
    state        VARCHAR(100) NOT NULL,
    zip_code     VARCHAR(100),
    complement   VARCHAR(100),
    country      VARCHAR(100) NOT NULL
);

CREATE TABLE users
(
    id         SERIAL PRIMARY KEY,
    full_name  VARCHAR(255)             NOT NULL,
    cpf        VARCHAR(14)              NOT NULL UNIQUE,
    email      VARCHAR(255)             NOT NULL UNIQUE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    address_id INTEGER                  NOT NULL,
    CONSTRAINT fk_user_address FOREIGN KEY (address_id) REFERENCES address (id)
);
