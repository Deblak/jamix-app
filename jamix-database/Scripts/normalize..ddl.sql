DELETE FROM t_accounts;
DELETE FROM t_offers;
DELETE FROM t_goals;
DELETE FROM t_styles;
DELETE FROM t_instruments;

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

CREATE TABLE t_offers(
   offer_title VARCHAR(200),
   contact_email VARCHAR(255),
   offer_desc VARCHAR(600) NOT NULL,
   offer_create_date DATE NOT NULL,
   city VARCHAR(50),
   zip_code CHAR(5),
   offer_img VARCHAR(50),
   style_name VARCHAR(25) NOT NULL,
   goal_type VARCHAR(16) NOT NULL,
   instrument_name VARCHAR(25) NOT NULL,
   email VARCHAR(320) NOT NULL,
   PRIMARY KEY(offer_title, contact_email),
   FOREIGN KEY(style_name) REFERENCES t_styles(style_name),
   FOREIGN KEY(goal_type) REFERENCES t_goals(goal_type),
   FOREIGN KEY(instrument_name) REFERENCES t_instruments(instrument_name),
   FOREIGN KEY(email) REFERENCES t_accounts(email)
);

SELECT * FROM t_offers;
