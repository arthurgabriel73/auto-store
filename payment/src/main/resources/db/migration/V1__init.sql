CREATE TABLE payments
(
    id             SERIAL PRIMARY KEY,
    order_id       VARCHAR(255)             NOT NULL,
    transaction_id VARCHAR(255)             NOT NULL,
    total_items    INTEGER                  NOT NULL,
    total_amount   DECIMAL(10, 2)           NOT NULL,
    status         VARCHAR(50)              NOT NULL,
    created_at     TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at     TIMESTAMP WITH TIME ZONE NOT NULL
)
