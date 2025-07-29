DROP TABLE IF EXISTS t_offers;
DROP TABLE IF EXISTS t_goals;
DROP TABLE IF EXISTS t_styles;
DROP TABLE IF EXISTS t_instruments;
DROP TABLE IF EXISTS t_locations;
DROP TABLE IF EXISTS t_musics;
DROP TABLE IF EXISTS t_portfolios;
DROP TABLE IF EXISTS t_accounts;
DROP TABLE IF EXISTS t_roles;

CREATE TABLE t_roles(
	id INT GENERATED ALWAYS AS IDENTITY,
	role_name VARCHAR(8),
    default_role BOOLEAN NOT NULL,
	CONSTRAINT t_roles_pkey PRIMARY KEY (id)
);

 CREATE TABLE t_accounts(
	id INT GENERATED ALWAYS AS IDENTITY,
	id_role INT NOT NULL,
	username varchar(20),
	email varchar(320),
	password varchar(72),
	CONSTRAINT t_accounts_pkey PRIMARY KEY (id),
	CONSTRAINT t_roles_fkey FOREIGN KEY (id_role) REFERENCES t_roles(id),
	CONSTRAINT t_accounts_email_ukey UNIQUE (email)
 );
 
 CREATE TABLE t_portfolios(
 	id INT GENERATED ALWAYS AS IDENTITY,
 	id_account INT NOT NULL,
 	band_name VARCHAR(50) NOT NULL,
 	tagline VARCHAR(120),
 	biography VARCHAR(800),
 	portfolio_img VARCHAR(41),
 	CONSTRAINT t_portfolios_pkey PRIMARY KEY (id),
 	CONSTRAINT t_portfolios_ukey UNIQUE (band_name),
 	CONSTRAINT t_portfolios_img_ukey UNIQUE (portfolio_img),
 	CONSTRAINT t_accounts_fkey FOREIGN KEY (id_account) REFERENCES t_accounts(id)
);

CREATE TABLE t_musics(
	id INT GENERATED ALWAYS AS IDENTITY,
	id_portfolio INT NOT NULL,
 	musical_title VARCHAR(50) NOT NULL,
 	album_title VARCHAR(50),
 	composer VARCHAR(50) NOT NULL,
 	music_preview VARCHAR(50) NOT NULL,
 	album_cover VARCHAR(41),
 	CONSTRAINT t_musics_pkey PRIMARY KEY (id),
 	CONSTRAINT t_portfolios_fkey FOREIGN KEY (id_portfolio) REFERENCES t_portfolios(id),
 	CONSTRAINT t_musics_ukey UNIQUE (id_portfolio, musical_title),
 	CONSTRAINT t_musics_cover_ukey UNIQUE (album_cover),
 	CONSTRAINT t_musics_preview_ukey UNIQUE (music_preview)
);

CREATE TABLE t_instruments (
    id INT GENERATED ALWAYS AS IDENTITY,
    instrument_name VARCHAR(25) NOT NULL,
    CONSTRAINT t_instruments_pkey PRIMARY KEY(id),
    CONSTRAINT t_instruments_name_ukey UNIQUE (instrument_name)
);

CREATE TABLE t_styles (
    id INT GENERATED ALWAYS AS IDENTITY,
    style_name VARCHAR(25) NOT NULL,
    CONSTRAINT t_styles_pkey PRIMARY KEY(id),
    CONSTRAINT t_styles_name_ukey UNIQUE (style_name)
);

CREATE TABLE t_goals (
    id INT GENERATED ALWAYS AS IDENTITY,
    goal_type VARCHAR(16) NOT NULL,
    CONSTRAINT t_goals_pkey PRIMARY KEY(id),
    CONSTRAINT t_goals_type_ukey UNIQUE (goal_type)
);

CREATE TABLE t_locations(
   id INT GENERATED ALWAYS AS IDENTITY,
   city VARCHAR(50) NOT NULL,
   zip_code CHAR(5) NOT NULL,
   CONSTRAINT t_locations_pkey PRIMARY KEY(id),
   CONSTRAINT t_locations_zip_code_ukey UNIQUE (city, zip_code)
);

CREATE TABLE t_offers(
    id INT GENERATED ALWAYS AS IDENTITY,
	offer_title VARCHAR(200) NOT NULL,
	offer_desc VARCHAR(600) NOT NULL,
	contact_email VARCHAR(255) NOT NULL,
	offer_create_date DATE NOT NULL DEFAULT CURRENT_DATE,
	offer_img VARCHAR(41),
	id_location INT NOT NULL,
	id_instrument INT NOT NULL,
   	id_style INT NOT NULL,
   	id_goal INT NOT NULL,
   	id_account INT NOT NULL,
    CONSTRAINT t_offers_pkey PRIMARY KEY(id),
    CONSTRAINT t_offers_ukey UNIQUE (offer_title, contact_email),
    CONSTRAINT t_offers_img_ukey UNIQUE (offer_img),
    CONSTRAINT t_locations_fkey FOREIGN KEY(id_location) REFERENCES t_locations(id),
	CONSTRAINT t_instruments_fkey FOREIGN KEY(id_instrument) REFERENCES t_instruments(id),
   	CONSTRAINT t_styles_fkey FOREIGN KEY(id_style) REFERENCES t_styles(id),
   	CONSTRAINT t_goals_fkey FOREIGN KEY(id_goal) REFERENCES t_goals(id),
   	CONSTRAINT t_accounts_fkey FOREIGN KEY (id_account) REFERENCES t_accounts(id)
);