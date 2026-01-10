--liquibase formatted sql
--changeset inurgalimov:add_columns_to_users_table
ALTER TABLE users
    ADD COLUMN first_arg   INTEGER,
    ADD COLUMN second_arg  INTEGER,
    ADD COLUMN result      INTEGER;