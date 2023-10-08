create schema if not exists event;

SET search_path TO event;

DROP TABLE IF EXISTS birthdays;
DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users
(
    id        SERIAL PRIMARY KEY,
    user_id VARCHAR(100) not null unique
);

CREATE TABLE IF NOT EXISTS events
(
    id          SERIAL PRIMARY KEY,
    user_id     INTEGER      not null,
    event_name  VARCHAR(100) not null,
    notify      boolean      NOT NULL DEFAULT FALSE,
    date        date         not null,
    description text,
    UNIQUE (event_name, date)
);

CREATE TABLE IF NOT EXISTS birthdays
(
    id          SERIAL PRIMARY KEY,
    user_id     INTEGER      not null,
    first_name  VARCHAR(100) not null,
    last_name   VARCHAR(100) not null,
    notify      boolean      NOT NULL DEFAULT FALSE,
    date        date         not null,
    description text,
    UNIQUE (first_name, last_name, date)
);


ALTER TABLE birthdays
    ADD FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE events
    ADD FOREIGN KEY (user_id) REFERENCES users (id);




