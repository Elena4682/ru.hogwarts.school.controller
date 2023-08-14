ALTER TABLE student
ADD CONSTRAINT age_constraint CHECK (age > 16);

ALTER  TABLE student
ALTER COLUMN name SET NOT NULL;

ALTER TABLE faculty
ADD CONSTRAINT name_color_unique UNIQUE (name, color);

ALTER TABLE student
ADD CHECK (age == NULL) ADD age 20;