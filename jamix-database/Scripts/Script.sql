CREATE TABLE t_instruments(
   instrument_name VARCHAR(25),
   PRIMARY KEY(instrument_name)
);

CREATE TABLE t_styles(
   style_name VARCHAR(25),
   PRIMARY KEY(style_name)
);

CREATE TABLE t_goals(
   goal_type VARCHAR(16),
   PRIMARY KEY(goal_type)
);

CREATE TABLE t_places(
   city VARCHAR(50),
   zip_code CHAR(5),
   PRIMARY KEY(city, zip_code)
);

CREATE TABLE t_roles(
   role_name VARCHAR(8),
   default_role LOGICAL NOT NULL,
   PRIMARY KEY(role_name)
);

CREATE TABLE t_accounts(
   email VARCHAR(320),
   username VARCHAR(20) NOT NULL,
   password VARCHAR(72) NOT NULL,
   role_name VARCHAR(8) NOT NULL,
   PRIMARY KEY(email),
   FOREIGN KEY(role_name) REFERENCES t_roles(role_name)
);

CREATE TABLE t_portfolios(
   band_name VARCHAR(50),
   biography VARCHAR(600),
   email VARCHAR(320) NOT NULL,
   PRIMARY KEY(band_name),
   FOREIGN KEY(email) REFERENCES t_accounts(email)
);

CREATE TABLE t_musics(
   band_name VARCHAR(50),
   musical_title VARCHAR(50),
   album_title VARCHAR(50),
   band VARCHAR(50),
   music_file VARCHAR(50) NOT NULL,
   PRIMARY KEY(band_name, musical_title),
   FOREIGN KEY(band_name) REFERENCES t_portfolios(band_name)
);

CREATE TABLE t_offers(
   offer_title VARCHAR(200),
   contact_email VARCHAR(255),
   offer_desc VARCHAR(500) NOT NULL,
   offer_create_date DATE NOT NULL,
   offer_img VARCHAR(41),
   city VARCHAR(50) NOT NULL,
   zip_code CHAR(5) NOT NULL,
   style_name VARCHAR(25) NOT NULL,
   goal_type VARCHAR(16) NOT NULL,
   instrument_name VARCHAR(25) NOT NULL,
   email VARCHAR(320) NOT NULL,
   PRIMARY KEY(offer_title, contact_email),
   FOREIGN KEY(city, zip_code) REFERENCES t_places(city, zip_code),
   FOREIGN KEY(style_name) REFERENCES t_styles(style_name),
   FOREIGN KEY(goal_type) REFERENCES t_goals(goal_type),
   FOREIGN KEY(instrument_name) REFERENCES t_instruments(instrument_name),
   FOREIGN KEY(email) REFERENCES t_accounts(email)
);
