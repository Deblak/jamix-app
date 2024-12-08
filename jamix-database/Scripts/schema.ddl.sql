DROP TABLE IF EXISTS t_offers;
DROP TABLE IF EXISTS t_users;

CREATE TABLE t_offers(
	id_offer INT GENERATED ALWAYS AS IDENTITY,
	offer_title VARCHAR(200),
	offer_desc VARCHAR(600) NOT NULL,
	offer_create_date DATE DEFAULT CURRENT_DATE,
	CONSTRAINT t_offers_pkey PRIMARY KEY(offer_title)
);

 CREATE TABLE t_users(
	id int GENERATED ALWAYS AS IDENTITY,
	username varchar(255),
	email varchar(320),
	password varchar(72),
	CONSTRAINT t_users_pkey PRIMARY KEY (id),
	CONSTRAINT t_users_username_ukey UNIQUE (username),
	CONSTRAINT t_users_email_ukey UNIQUE (email)
 );