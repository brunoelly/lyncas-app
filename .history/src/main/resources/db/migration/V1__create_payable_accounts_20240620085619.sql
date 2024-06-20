CREATE DATABASE IF NOT EXISTS PAYABLE_ACCOUNTS;

CREATE TABLE IF NOT EXISTS PAYABLE_ACCOUNTS.payable_accounts (
    id SERIAL PRIMARY KEY,
    due_date DATE NOT NULL,
    payment_date DATE,
    amount DECIMAL(10, 2) NOT NULL,
    account_description VARCHAR(255),
    payment_status VARCHAR(50)
);