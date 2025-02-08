DROP TABLE IF EXISTS t_offers;
DROP TABLE IF EXISTS t_goals;
DROP TABLE IF EXISTS t_styles;
DROP TABLE IF EXISTS t_instruments;
DROP TABLE IF EXISTS t_accounts;

 CREATE TABLE t_accounts(
	id INT GENERATED ALWAYS AS IDENTITY,
	username varchar(20),
	email varchar(320),
	password varchar(72),
	CONSTRAINT t_accounts_pkey PRIMARY KEY (id),
	CONSTRAINT t_accounts_email_ukey UNIQUE (email)
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

CREATE TABLE t_offers(
	id INT GENERATED ALWAYS AS IDENTITY,
	offer_title VARCHAR(200),
	offer_desc VARCHAR(600) NOT NULL,
	city VARCHAR(50),
    zip_code char(5),
    contact_email VARCHAR(255) NOT NULL,
	offer_create_date DATE DEFAULT CURRENT_DATE,
	offer_img VARCHAR(41),
	id_instrument INT NOT NULL,
   	id_style INT NOT NULL,
   	id_goal INT NOT NULL,
   	id_account INT NOT NULL,
	CONSTRAINT t_offers_pkey PRIMARY KEY(id),
	CONSTRAINT t_offers_ukey UNIQUE (offer_title, contact_email),
	CONSTRAINT t_instruments_fkey FOREIGN KEY(id_instrument) REFERENCES t_instruments(id),
   	CONSTRAINT t_styles_fkey FOREIGN KEY(id_style) REFERENCES t_styles(id),
   	CONSTRAINT t_goals_fkey FOREIGN KEY(id_goal) REFERENCES t_goals(id),
   	CONSTRAINT t_offers_accounts_fkey FOREIGN KEY (id_account) REFERENCES t_accounts(id)
);