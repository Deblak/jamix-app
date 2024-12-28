DROP TABLE IF EXISTS t_goals;
DROP TABLE IF EXISTS t_styles;
DROP TABLE IF EXISTS t_instruments;
DROP TABLE IF EXISTS t_offers;
DROP TABLE IF EXISTS t_users;

 CREATE TABLE t_users(
	id int GENERATED ALWAYS AS IDENTITY,
	username varchar(255),
	email varchar(320),
	password varchar(72),
	CONSTRAINT t_users_pkey PRIMARY KEY (id),
	CONSTRAINT t_users_username_ukey UNIQUE (username),
	CONSTRAINT t_users_email_ukey UNIQUE (email)
 );
 -- taille max du mail

CREATE TABLE t_offers(
	id_offer INT GENERATED ALWAYS AS IDENTITY,
	offer_title VARCHAR(200),
	offer_desc VARCHAR(600) NOT NULL,
	city VARCHAR(50),
    zip_code char(5),
    contact_email VARCHAR(255) NOT NULL,
	offer_create_date DATE DEFAULT CURRENT_DATE,
	CONSTRAINT t_offers_pkey PRIMARY KEY(offer_title)
);
--taille max desc

CREATE TABLE t_instruments (
    id_instrument INT GENERATED ALWAYS AS IDENTITY,
    instrument_name VARCHAR(25) NOT NULL,
    CONSTRAINT t_instruments_pkey PRIMARY KEY(instrument_name)
);
--taille max instrument name

CREATE TABLE t_styles (
    id_style INT GENERATED ALWAYS AS IDENTITY,
    style_name VARCHAR(25) NOT NULL,
    CONSTRAINT t_styles_pkey PRIMARY KEY(style_name)
);

CREATE TABLE t_goals (
    id_goal INT GENERATED ALWAYS AS IDENTITY,
    goal_name VARCHAR(16) NOT NULL,
    CONSTRAINT t_goals_pkey PRIMARY KEY(goal_name)
);