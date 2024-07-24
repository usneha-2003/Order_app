CREATE TABLE customer
(
    customer_id SERIAL PRIMARY KEY,
    customer_name varchar(30),
    customer_email varchar(80)
);

CREATE TABLE product
(
    	product_id SERIAL PRIMARY KEY,
	product_name varchar(30),
	product_price int,
	product_quantity int
);

CREATE TABLE orders
(
    order_id SERIAL PRIMARY KEY,
    order_date date,
    order_status varchar(20),
    customer_id int REFERENCES customer (customer_id)
);

CREATE TABLE order_details
(
    order_details_id SERIAL PRIMARY KEY,
    order_id int REFERENCES orders (order_id),
    product_id int REFERENCES product (product_id),
    quantity int,
    total_price int
);

CREATE TABLE payment
(
    payment_id SERIAL PRIMARY KEY,
    payment_date date,
    amount_paid int,
    order_id int REFERENCES orders (order_id)
);
