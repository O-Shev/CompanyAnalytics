CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Enum types
CREATE TYPE company_status_enum AS ENUM ('ACTIVE', 'INACTIVE', 'BLOCKED');

-- Table: company
CREATE TABLE company (
     id SERIAL PRIMARY KEY,
     name VARCHAR(128) NOT NULL,
     status company_status_enum NOT NULL,
     registration_number UUID UNIQUE DEFAULT uuid_generate_v4(),
     contact_info VARCHAR(255),
     industry VARCHAR(128),
     created_at timestamptz DEFAULT current_timestamp,
     updated_at timestamptz DEFAULT current_timestamp
);

-- Table: address
CREATE TABLE address (
    id SERIAL PRIMARY KEY,
    country VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    street VARCHAR(255) NOT NULL,
    zip VARCHAR(20) NOT NULL,
    CONSTRAINT unique_address UNIQUE (country, city, street, zip)
);

-- Table: address_category
CREATE TABLE address_category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE
);

-- Many-to-Many table: company_address
CREATE TABLE company_address (
    company_id BIGINT NOT NULL REFERENCES company(id) ON DELETE CASCADE,
    address_id BIGINT NOT NULL REFERENCES address(id) ON DELETE CASCADE,
    PRIMARY KEY (company_id, address_id)
);

-- Many-to-Many table: address_category_mapping
CREATE TABLE address_category_mapping (
    address_id BIGINT NOT NULL REFERENCES address(id) ON DELETE CASCADE,
    category_id BIGINT NOT NULL REFERENCES address_category(id) ON DELETE CASCADE,
    PRIMARY KEY (address_id, category_id)
);
