-- V1__Create_order_schema.sql

CREATE TABLE orders (
                        id BIGSERIAL PRIMARY KEY,
                        customer_id UUID NOT NULL,
                        restaurant_id  BIGINT NOT NULL,
                        table_id BIGINT,
                        order_time TIMESTAMP NOT NULL,
                        status VARCHAR(20) NOT NULL
);

CREATE TABLE order_item (
                            id BIGSERIAL PRIMARY KEY,
                            order_id BIGINT NOT NULL REFERENCES orders(id) ON DELETE CASCADE,
                            product_id BIGINT NOT NULL,
                            product_name VARCHAR(255) NOT NULL,
                            price NUMERIC(10, 2) NOT NULL,
                            quantity INTEGER NOT NULL
);

CREATE INDEX idx_order_customer_id ON orders(customer_id);
CREATE INDEX idx_order_restaurant_id ON orders(restaurant_id);
CREATE INDEX idx_order_item_order_id ON order_item(order_id);
