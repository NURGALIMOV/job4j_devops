--liquibase formatted sql
--changeset inurgalimov:create_calc_events_table
CREATE TABLE calc_events
(
    id         SERIAL PRIMARY KEY,
    user_id    BIGINT NOT NULL,
    first      INTEGER NOT NULL,
    second     INTEGER NOT NULL,
    result     INTEGER NOT NULL,
    create_date TIMESTAMP NOT NULL,
    type       VARCHAR(50) NOT NULL
);

