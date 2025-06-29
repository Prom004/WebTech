-- PostgreSQL table creation script for e-commerce application

-- Table: category
CREATE TABLE category (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    modified_by VARCHAR(255),
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    image_url VARCHAR(255)
);

-- Table: product
CREATE TABLE product (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    modified_by VARCHAR(255),
    product_name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    manufactured_date DATE NOT NULL,
    expiration_date DATE,
    stock_state VARCHAR(255) NOT NULL,
    quantity INTEGER NOT NULL DEFAULT 0,
    image_url VARCHAR(255),
    category_id UUID,
    CONSTRAINT fk_product_category FOREIGN KEY(category_id) REFERENCES category(id)
);

-- Table: customer
CREATE TABLE customer (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    modified_by VARCHAR(255),
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(255) NOT NULL UNIQUE
);

-- Table: orders
CREATE TABLE orders (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(255),
    modified_by VARCHAR(255),
    customer_id UUID,
    order_date TIMESTAMP,
    total_amount DOUBLE PRECISION,
    CONSTRAINT fk_orders_customer FOREIGN KEY(customer_id) REFERENCES customer(id)
);

-- Join Table: order_products
CREATE TABLE order_products (
    order_id UUID NOT NULL,
    product_id UUID NOT NULL,
    PRIMARY KEY (order_id, product_id),
    CONSTRAINT fk_order_products_order FOREIGN KEY(order_id) REFERENCES orders(id) ON DELETE CASCADE,
    CONSTRAINT fk_order_products_product FOREIGN KEY(product_id) REFERENCES product(id) ON DELETE CASCADE
);
