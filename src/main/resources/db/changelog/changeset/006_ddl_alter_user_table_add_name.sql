--liquibase formatted sql
--changeset inurgalimov:add_user_name_column
ALTER TABLE users ADD COLUMN name TEXT NOT NULL;