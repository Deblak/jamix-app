DROP TABLE IF EXISTS t_offers;
DROP TABLE IF EXISTS t_goals;
DROP TABLE IF EXISTS t_styles;
DROP TABLE IF EXISTS t_instruments;
DROP TABLE IF EXISTS t_accounts;

 CREATE TABLE t_accounts(
	id int GENERATED ALWAYS AS IDENTITY,
	username varchar(20),
	email varchar(320),
	password varchar(72),
	CONSTRAINT t_accounts_pkey PRIMARY KEY (id),
	CONSTRAINT t_accounts_email_ukey UNIQUE (email),
	CONSTRAINT t_accounts_username_ukey UNIQUE (username)
 );

CREATE TABLE t_instruments (
    id_instrument INT GENERATED ALWAYS AS IDENTITY,
    instrument_name VARCHAR(25) NOT NULL,
    CONSTRAINT t_instruments_pkey PRIMARY KEY(id_instrument),
    CONSTRAINT t_instruments_name_ukey UNIQUE (instrument_name)

);

CREATE TABLE t_styles (
    id_style INT GENERATED ALWAYS AS IDENTITY,
    style_name VARCHAR(25) NOT NULL,
    CONSTRAINT t_styles_pkey PRIMARY KEY(id_style),
    CONSTRAINT t_styles_name_ukey UNIQUE (style_name)

);

CREATE TABLE t_goals (
    id_goal INT GENERATED ALWAYS AS IDENTITY,
    goal_name VARCHAR(16) NOT NULL,
    CONSTRAINT t_goals_pkey PRIMARY KEY(id_goal),
    CONSTRAINT t_goals_name_ukey UNIQUE (goal_name)

);

CREATE TABLE t_offers(
	id_offer INT GENERATED ALWAYS AS IDENTITY,
	offer_title VARCHAR(200),
	offer_desc VARCHAR(600) NOT NULL,
	city VARCHAR(50),
    zip_code char(5),
    contact_email VARCHAR(255) NOT NULL,
	offer_create_date DATE DEFAULT CURRENT_DATE,
	instrument_name VARCHAR(25) NOT NULL,
   	style_name VARCHAR(25) NOT NULL,
   	goal_name VARCHAR(16) NOT NULL,
	CONSTRAINT t_offers_pkey PRIMARY KEY(id_offer),
	CONSTRAINT t_offers_ukey UNIQUE (offer_title, contact_email),
	CONSTRAINT t_instruments_fkey FOREIGN KEY(instrument_name) REFERENCES t_instruments(instrument_name),
   	CONSTRAINT t_styles_fkey FOREIGN KEY(style_name) REFERENCES t_styles(style_name),
   	CONSTRAINT t_goals_fkey FOREIGN KEY(goal_name) REFERENCES t_goals(goal_name)
);