CREATE TABLE human (
id SERIAL,
name TEXT PRIMARY KEY,
age INTEGER,
license BOOLEAN,
car_id SERIAL REFERENCES cars(id)
);

CREATE TABLE car (
id SERIAL,
brand TEXT,
model INTEGER
cost NUMERIC(6)
);