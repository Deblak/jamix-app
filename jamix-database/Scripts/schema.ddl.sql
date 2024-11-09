DROP TABLE IF EXISTS t_ads;

CREATE TABLE t_ads(
	id_ad INT GENERATED ALWAYS AS IDENTITY,
	ad_title VARCHAR(200),
	ad_desc VARCHAR(600) NOT NULL,
	ad_create_date DATE DEFAULT CURRENT_DATE,
	CONSTRAINT t_ads_pkey PRIMARY KEY(ad_title)
);


CREATE TABLE t_ads(
   ad_title VARCHAR(200),
   ad_desc VARCHAR(500) NOT NULL,
   ad_create_date DATE NOT NULL,
   PRIMARY KEY(ad_title)
);





