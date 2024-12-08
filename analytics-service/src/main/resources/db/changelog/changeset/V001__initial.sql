CREATE TABLE company (
    id SERIAL PRIMARY KEY,
    status VARCHAR(55),
    name VARCHAR(255)
);

CREATE TABLE company_name (
    id SERIAL PRIMARY KEY,
    company_id BIGINT NOT NULL REFERENCES company(id) ON DELETE CASCADE,
    name VARCHAR(255)
);