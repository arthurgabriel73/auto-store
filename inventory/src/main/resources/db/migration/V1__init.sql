CREATE TABLE inventory
(
    id                 SERIAL PRIMARY KEY,
    product_code       VARCHAR(255) NOT NULL,
    available_quantity INTEGER      NOT NULL
);

CREATE TABLE activities
(
    id             SERIAL PRIMARY KEY,
    inventory_id   INTEGER                  NOT NULL,
    order_id       VARCHAR(255)             NOT NULL,
    transaction_id VARCHAR(255)             NOT NULL,
    order_quantity INTEGER                  NOT NULL,
    old_quantity   INTEGER                  NOT NULL,
    new_quantity   INTEGER                  NOT NULL,
    created_at     TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at     TIMESTAMP WITH TIME ZONE NOT NULL,
    CONSTRAINT fk_activities_inventory FOREIGN KEY (inventory_id) REFERENCES inventory (id)
);

CREATE TABLE products
(
    id         SERIAL PRIMARY KEY,
    code       VARCHAR(255)   NOT NULL UNIQUE,
    unit_value NUMERIC(10, 2) NOT NULL,
    category   VARCHAR(255)   NOT NULL,
    details    JSONB          NOT NULL
);
