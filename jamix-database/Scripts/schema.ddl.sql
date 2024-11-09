DROP TABLE IF EXISTS t_offers;

CREATE TABLE t_offers(
	id_offer INT GENERATED ALWAYS AS IDENTITY,
	offer_title VARCHAR(200),
	offer_desc VARCHAR(600) NOT NULL,
	offer_create_date DATE DEFAULT CURRENT_DATE,
	CONSTRAINT t_offers_pkey PRIMARY KEY(offer_title)
);





