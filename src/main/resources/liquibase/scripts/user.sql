-- liquibase formatted sql

-- changeset esimonaytene:1
CREATE INDEX student_name_index ON student (name);

- changeset esimonaytene:2
CREATE INDEX faculty_nc_idx ON faculty (name, color);