CREATE TABLE validation_products
(
    id   SERIAL PRIMARY KEY,
    code VARCHAR(255) NOT NULL UNIQUE
);

CREATE UNIQUE INDEX idx_validation_products_code_unique ON validation_products (code);

CREATE TABLE validation
(
    id             SERIAL PRIMARY KEY,
    order_id       VARCHAR(255)             NOT NULL,
    transaction_id VARCHAR(255)             NOT NULL,
    success        BOOLEAN                  NOT NULL,
    created_at     TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at     TIMESTAMP WITH TIME ZONE NOT NULL
);
