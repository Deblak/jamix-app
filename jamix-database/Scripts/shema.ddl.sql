CREATE TABLE t_instruments (
   instrument_name VARCHAR(25) NOT NULL,
   CONSTRAINT t_instruments_pkey PRIMARY KEY(instrument_name)
);
CREATE TABLE t_styles (
   style_name VARCHAR(25) NOT NULL,
   CONSTRAINT t_styles_pkey PRIMARY KEY(style_name)
);
CREATE TABLE t_goals (
   goal_type VARCHAR(16) NOT NULL,
   CONSTRAINT t_goals_pkey PRIMARY KEY(goal_type)
);
CREATE TABLE t_locations(
   city VARCHAR(50) NOT NULL,
   zip_code CHAR(5) NOT NULL,
   CONSTRAINT t_locations_pkey PRIMARY KEY(city, zip_code)
);
CREATE TABLE t_roles(
   role_name VARCHAR(8) NOT NULL,
   default_role BOOLEAN NOT NULL,
   CONSTRAINT t_roles_pkey PRIMARY KEY(role_name)
);
CREATE TABLE t_accounts(
   email VARCHAR(320) NOT NULL,
   username VARCHAR(20) NOT NULL,
   password VARCHAR(72) NOT NULL,
   role_name VARCHAR(8) NOT NULL,
   CONSTRAINT t_accounts_pkey PRIMARY KEY(email),
   CONSTRAINT t_roles_fkey FOREIGN KEY(role_name) REFERENCES t_roles(role_name)
);
CREATE TABLE t_portfolios(
   band_name VARCHAR(50) NOT NULL,
   biography VARCHAR(800),
   tagline VARCHAR(120),
   portfolio_img VARCHAR(41),
   email VARCHAR(320) NOT NULL,
   CONSTRAINT t_portfolios_pkey PRIMARY KEY(band_name),
   CONSTRAINT t_portfolios_img_ukey UNIQUE (portfolio_img),
   CONSTRAINT t_accounts_fkey FOREIGN KEY(email) REFERENCES t_accounts(email)
);
CREATE TABLE t_musics(
   band_name VARCHAR(50) NOT NULL,
   musical_title VARCHAR(50) NOT NULL,
   album_title VARCHAR(50),
   composer VARCHAR(50) NOT NULL,
   music_preview VARCHAR(50) NOT NULL,
   album_cover VARCHAR(41),
   CONSTRAINT t_musics_pkey PRIMARY KEY(band_name, musical_title),
   CONSTRAINT t_musics_cover_ukey UNIQUE (album_cover),
   CONSTRAINT t_musics_preview_ukey UNIQUE (music_preview),
   CONSTRAINT t_portfolios_fkey FOREIGN KEY(band_name) REFERENCES t_portfolios(band_name)
);
CREATE TABLE t_offers(
   offer_title VARCHAR(200) NOT NULL,
   contact_email VARCHAR(255) NOT NULL,
   offer_desc VARCHAR(600) NOT NULL,
   offer_create_date DATE NOT NULL,
   offer_img VARCHAR(41),
   city VARCHAR(50) NOT NULL,
   zip_code CHAR(5) NOT NULL,
   style_name VARCHAR(25) NOT NULL,
   goal_type VARCHAR(16) NOT NULL,
   instrument_name VARCHAR(25) NOT NULL,
   email VARCHAR(320) NOT NULL,
   CONSTRAINT t_offers_pkey PRIMARY KEY(offer_title, contact_email),
   CONSTRAINT t_offers_img_ukey UNIQUE (offer_img),
   CONSTRAINT t_locations_fkey FOREIGN KEY(city, zip_code) REFERENCES t_locations(city, zip_code),
   CONSTRAINT t_styles_fkey FOREIGN KEY(style_name) REFERENCES t_styles(style_name),
   CONSTRAINT t_goals_fkey FOREIGN KEY(goal_type) REFERENCES t_goals(goal_type),
   CONSTRAINT t_instruments_fkey FOREIGN KEY(instrument_name) REFERENCES t_instruments(instrument_name),
   CONSTRAINT t_accounts_fkey FOREIGN KEY(email) REFERENCES t_accounts(email)
);