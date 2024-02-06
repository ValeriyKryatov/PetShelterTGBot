-- liquibase formatted sql

-- changeset Valeriy Kryatov:1
CREATE TABLE animals
(
    id        BIGSERIAL PRIMARY KEY,
    nick_name VARCHAR                           NOT NULL,
    pet_type  VARCHAR                           NOT NULL,
    color     VARCHAR                           NOT NULL,
    sex       VARCHAR                           NOT NULL,
    age       BIGSERIAL CHECK (animals.age > 0) NOT NULL,
    breed     VARCHAR
);

CREATE TABLE users
(
    id            BIGSERIAL PRIMARY KEY,
    first_name    VARCHAR   NOT NULL,
    last_name     VARCHAR   NOT NULL,
    telegram_id   BIGSERIAL NOT NULL,
    telegram_nick VARCHAR   NOT NULL,
    phone_number  VARCHAR   NOT NULL,
    email         VARCHAR   NOT NULL,
    address       VARCHAR   NOT NULL,
    car_number    VARCHAR,
    shelter_type  VARCHAR   NOT NULL,
    user_type     VARCHAR   NOT NULL,
    user_status   VARCHAR   NOT NULL
);

CREATE TABLE adopters
(
    id         BIGSERIAL PRIMARY KEY,
    user_id    BIGSERIAL,
    animal_id  BIGSERIAL NOT NULL,
    shelter_id BIGSERIAL NOT NULL
);

CREATE TABLE shelters
(
    id                 BIGSERIAL PRIMARY KEY,
    address_shelter    VARCHAR NOT NULL,
    time_work          VARCHAR NOT NULL,
    driving_directions VARCHAR NOT NULL,
    phone_shelter      VARCHAR NOT NULL,
    phone_security     VARCHAR NOT NULL,
    shelter_type       VARCHAR NOT NULL
);

CREATE TABLE reports
(
    id                       BIGSERIAL PRIMARY KEY,
    chat_id                  BIGSERIAL NOT NULL,
    name_user                VARCHAR,
    date_report              DATE,
    date_end_of_probation    DATE,
    status_report            INTEGER,
    report_text              VARCHAR,
    photo_animal             BYTEA,
    animals_flag             VARCHAR,
    animal_diet              VARCHAR,
    well_being_and_addiction VARCHAR
);