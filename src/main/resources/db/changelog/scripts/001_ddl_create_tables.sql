CREATE TABLE IF NOT EXISTS orders(
    id SERIAL PRIMARY KEY,
    order_number VARCHAR(25) NOT NULL UNIQUE,
    producer_id BIGINT NOT NULL,
    status VARCHAR(30) NOT NULL CHECK (status IN ('UNKNOWN', 'ORDERED', 'TRANSIT', 'DELIVERED', 'DELAYED', 'CANCELED')),
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS orders_content(
    id SERIAL PRIMARY KEY,
    order_id BIGINT REFERENCES orders(id) NOT NULL ON DELETE CASCADE,
    socks_id BIGINT NOT NULL,
    quantity INT NOT NULL CHECK(quantity > 0),
    UNIQUE(order_id, socks_id)
);