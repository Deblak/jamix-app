SELECT * FROM t_offers;
SELECT * FROM t_accounts;
SELECT * FROM t_locations WHERE zip_code LIKE '59%';
SELECT * FROM t_roles;
SELECT * FROM t_instruments;
SELECT * FROM t_goals;
SELECT * FROM t_styles;
SELECT * FROM t_portfolios;
SELECT * FROM t_musics;

INSERT INTO t_accounts (username, email, password) VALUES 
('Test', 'test@test.com', 'Azertyuiop01!'); --bad practice
DELETE FROM t_accounts WHERE id = 3;

DELETE FROM t_accounts
WHERE email = 'postma2@test.com';

ALTER TABLE t_offers
ADD CONSTRAINT t_offers_img_ukey UNIQUE (offer_img);

ALTER TABLE t_musics
RENAME COLUMN compositor TO composer;

ALTER TABLE t_musics
ADD COLUMN album_cover VARCHAR(41),
ADD CONSTRAINT t_musics_cover_ukey UNIQUE (album_cover);

ALTER TABLE t_portfolios
ADD COLUMN portfolio_img VARCHAR(41),
ADD CONSTRAINT t_portfolios_img_ukey UNIQUE (portfolio_img);

ALTER TABLE t_portfolios
ADD COLUMN tagline VARCHAR(120);

ALTER TABLE t_portfolios
ALTER COLUMN biography TYPE VARCHAR(800);

ALTER TABLE t_musics
RENAME COLUMN music_file TO music_preview;

ALTER TABLE t_musics
ADD CONSTRAINT t_musics_preview_ukey UNIQUE (music_preview);

ALTER TABLE t_musics
    ALTER COLUMN composer SET NOT NULL;

ALTER TABLE t_musics
    ALTER COLUMN musical_title SET NOT NULL;


