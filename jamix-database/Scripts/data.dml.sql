DELETE FROM t_accounts;
DELETE FROM t_offers;
DELETE FROM t_goals;
DELETE FROM t_styles;
DELETE FROM t_instruments;

--thanks to https://mimo-international.com/MIMO
INSERT INTO t_instruments (instrument_name) VALUES
('Voix'),
-- instruments vent
('Flûte'), ('Clarinette'),('Hautbois'),('Trompette'),('Cors'),('Accordéon'),('Cornemuse'),
('Trombone'),('Clairon'),('Flûte de pan'),('Tuba'),('Trompe de chasse'),('Harmonica'),('Saxophone'),('Lyre'),
-- instruments percussion
('Tambour'),('Cloche'),('Xylophone'),('Cymbale'),('Castagnettes'),('Batterie'), ('Cajón'),
-- instruments cordes
('Cithare'),('Violon'),('Luth'),('Guitare'),('Guitare basse'),('Guitare électrique'),
('Banjo'),('Basse électrique'),('Ukulélé'),('Harpe'),('Violoncelle'),('Mandoline'),('Contrebasse'),
-- instruments clavier
('Piano'),('Clavecin'),('Synthétiseur'),('Orgue'), ('Clavier'),('Autre');

INSERT INTO t_styles (style_name) VALUES
('Ambiant'), ('Blues'), ('Bossa Nova'), ('Chanson française'), ('Country'), ('Dubstep'), 
('Musique africaine'), ('Musique classique'), ('Musique irlandaise'), ('Musique orientale'), ('Musique latine'), 
('Electro'), ('Flamenco'), ('Funk'), ('Folk'), ('Gospel'), ('Hip-hop'), ('House'), ('Indie'), ('Jazz'), 
('J-pop'), ('K-pop'), ('Lo-fi'), ('Métal'), ('Pop'), ('Pop Rock'), ('Rap'), ('Reggae'), ('Rock'), 
('RnB'), ('Soul'), ('Techno'), ('Autre');

INSERT INTO t_goals (goal_type) VALUES
('Entraînement'), ('Jam Session'), ('Monter un groupe');


--pictures
-- (Warning) Run business logic and Postman to create user 'vuejs@test.com'
INSERT INTO t_offers (offer_title, offer_desc, city, zip_code, contact_email, offer_img, id_instrument, id_style, id_goal, id_account) VALUES
('Cherche musiciens jazz',
 'Je cherche des musiciens jazz, funk ou rock pour jouer en groupe et progresser ensemble.',
 'Cognac', '16100', 'sophie.vue@picturemail.fr',
 '1cce5c2c-d3bd-48ed-be4e-a4be6ed60174.jpg',
 (SELECT id FROM t_instruments WHERE instrument_name = 'Clavier'), 
 (SELECT id FROM t_styles WHERE style_name = 'Jazz'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Jam Session'),
 (SELECT id FROM t_accounts WHERE email = 'vuejs@test.com')),
 
 ('Formation groupe métal',
 'Cherche des musiciens pour monter un groupe de métal, influence heavy et thrash.',
 'Lille', '59000', 'frederic.vue@picturemail.com', 
 'c2a70c79-609b-4871-a3df-8fe7478ee062.jpg',
 (SELECT id FROM t_instruments WHERE instrument_name ='Batterie'), 
 (SELECT id FROM t_styles WHERE style_name ='Métal'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Monter un groupe'),
 (SELECT id FROM t_accounts WHERE email = 'vuejs@test.com')),
 
 ('Musicien classique cherche partenaire',
 'Je suis violoncelliste et cherche un partenaire pour répétitions de musique classique.',
 'Pontivy', '56300', 'pixelvue@picturemail.org', 
 '9d17d9ad-25e8-4faa-af18-373041466909.jpg',
 (SELECT id FROM t_instruments WHERE instrument_name ='Violoncelle'), 
 (SELECT id FROM t_styles WHERE style_name ='Musique classique'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Entraînement'),
 (SELECT id FROM t_accounts WHERE email = 'vuejs@test.com'));

-- no pictures
-- (Warning) Run business logic and Postman to create user 'postman@test.com'
INSERT INTO t_offers (offer_title, offer_desc, city, zip_code, contact_email, offer_img, id_instrument, id_style, id_goal, id_account) VALUES
('ZOOMBEE X cherche son bassiste',
 'Salut ! ZOOMBEE X recherche son/sa bassiste en vue de concerts. Répertoire de compos disponibles et nouvelles à venir. Style punk/rock/grunge.',
 'Vesoul', '70000', 'julien.martin16@mail.net',
 NULL,
 (SELECT id FROM t_instruments WHERE instrument_name = 'Guitare basse'), 
 (SELECT id FROM t_styles WHERE style_name = 'Rock'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Monter un groupe'),
 (SELECT id FROM t_accounts WHERE email = 'postman@test.com')),

('Cherche musiciens funk',
 'Je cherche des musiciens jazz, funk ou rock pour jouer en groupe et progresser ensemble.',
 'Cognac', '16100', 'sophie.petit38@mail.fr',
 NULL,
 (SELECT id FROM t_instruments WHERE instrument_name = 'Clavier'), 
 (SELECT id FROM t_styles WHERE style_name = 'Jazz'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Jam Session'),
 (SELECT id FROM t_accounts WHERE email = 'postman@test.com')),

('Groupe de Tourcoing cherche son chanteur',
 'Notre groupe de reprises cherche un chanteur à l’aise en Français et en Anglais.',
 'Tourcoing', '59599', 'antoine.bernard13@mail.org', 
 NULL,
 (SELECT id FROM t_instruments WHERE instrument_name ='Voix'), 
 (SELECT id FROM t_styles WHERE style_name ='Pop Rock'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Monter un groupe'),
 (SELECT id FROM t_accounts WHERE email = 'postman@test.com')),

('Musicien cherche musicien',
 'Je suis percussionniste (timbales latines) et cherche d’autres musiciens pour former un groupe de musique latine.',
 'Dijon', '21231', 'lucas.perrin@mail.fr',
 NULL,
 (SELECT id FROM t_instruments WHERE instrument_name ='Autre'), 
 (SELECT id FROM t_styles WHERE style_name ='Musique latine'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Jam Session'),
 (SELECT id FROM t_accounts WHERE email = 'postman@test.com')),

('Bassiste cherche groupe',
 'Bassiste cherche groupe région Epernay/Reims. Dispo à partir de septembre !',
 'Lyon', '69000', 'thomas.moreau@mail.net',
 NULL,
 (SELECT id FROM t_instruments WHERE instrument_name ='Basse électrique'), 
 (SELECT id FROM t_styles WHERE style_name ='Rock'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Monter un groupe'),
 (SELECT id FROM t_accounts WHERE email = 'postman@test.com')),

('Musiciens bénévoles',
 'Cherche musiciens retraités pour jouer dans des maisons de retraite.',
 'Roubaix', '59512', 'rhythmic_beat@mail.org',
 NULL,
 (SELECT id FROM t_instruments WHERE instrument_name ='Accordéon'), 
 (SELECT id FROM t_styles WHERE style_name ='Chanson française'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Jam Session'),
 (SELECT id FROM t_accounts WHERE email = 'postman@test.com')),

('Musicien cherche groupe',
 'Joueur de Cajon cherche musiciens pour jouer tous styles en toute simplicité.',
 'Auxerre', '89000', 'basslover@mail.com',
 NULL,
 (SELECT id FROM t_instruments WHERE instrument_name ='Cajón'), 
 (SELECT id FROM t_styles WHERE style_name ='Blues'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Jam Session'),
 (SELECT id FROM t_accounts WHERE email = 'postman@test.com')),

('Création d’un quatuor',
 'Pianiste cherche musiciens confirmés pour quatuor classique baroque.',
 'Montpellier', '34000', 'harmonic_soul@gmail.net',
 NULL,
 (SELECT id FROM t_instruments WHERE instrument_name ='Piano'), 
 (SELECT id FROM t_styles WHERE style_name ='Musique classique'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Monter un groupe'),
 (SELECT id FROM t_accounts WHERE email = 'postman@test.com')),

('Guitariste cherche groupe de rock',
 'Guitariste rock cherche des musiciens pour créer un groupe, compositions originales et concerts à prévoir.',
 'Bordeaux', '33000', 'antoine.lambert@mail.fr',
 NULL,
 (SELECT id FROM t_instruments WHERE instrument_name ='Guitare électrique'), 
 (SELECT id FROM t_styles WHERE style_name ='Rock'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Monter un groupe'),
 (SELECT id FROM t_accounts WHERE email = 'postman@test.com')),

('Cherche musicien(ne) pour groupe indie',
 'Nous recherchons un(e) guitariste pour rejoindre notre groupe indie et créer ensemble.',
 'Nantes', '44000', 'lucie.martin23@mail.net',
 NULL,
 (SELECT id FROM t_instruments WHERE instrument_name ='Guitare électrique'), 
 (SELECT id FROM t_styles WHERE style_name ='Indie'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Monter un groupe'),
 (SELECT id FROM t_accounts WHERE email = 'postman@test.com')),

('Recrutement groupe pop/rock',
 'Guitariste, bassiste et batteur cherchent chanteur(se) pour former un groupe pop/rock.',
 'Rennes', '35000', 'julie.mayor@mail.fr',
 NULL,
 (SELECT id FROM t_instruments WHERE instrument_name ='Voix'), 
 (SELECT id FROM t_styles WHERE style_name ='Pop Rock'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Monter un groupe'),
 (SELECT id FROM t_accounts WHERE email = 'postman@test.com')),

('Musiciens pour projet folk',
 'Je suis violoniste et je cherche d’autres musiciens pour créer un groupe de musique folk.',
 'Strasbourg', '67000', 'elise.robert@mail.org',
 NULL,
 (SELECT id FROM t_instruments WHERE instrument_name ='Violon'), 
 (SELECT id FROM t_styles WHERE style_name ='Folk'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Monter un groupe'),
 (SELECT id FROM t_accounts WHERE email = 'postman@test.com')),

('Séance d’entraînement jazz',
 'Musiciens de jazz cherchent à se retrouver régulièrement pour des sessions d’entraînement et de pratique.',
 'Paris', '75000', 'jazzlover@mail.net',
 NULL,
 (SELECT id FROM t_instruments WHERE instrument_name ='Piano'), 
 (SELECT id FROM t_styles WHERE style_name ='Jazz'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Entraînement'),
 (SELECT id FROM t_accounts WHERE email = 'postman@test.com')),

('Cours et entraînements pour musiciens débutants',
 'Je propose des séances de travail pour les musiciens débutants souhaitant progresser dans un cadre structuré.',
 'Lyon', '69000', 'musiccoach@mail.com',
 NULL,
 (SELECT id FROM t_instruments WHERE instrument_name ='Batterie'), 
 (SELECT id FROM t_styles WHERE style_name ='Pop'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Entraînement'),
 (SELECT id FROM t_accounts WHERE email = 'postman@test.com')),

('Jam session jazz et improvisation',
 'Musiciens de jazz souhaitant faire une jam session et improviser ensemble tous styles bienvenus.',
 'Marseille', '13000', 'peter.davis@mail.org',
 NULL,
 (SELECT id FROM t_instruments WHERE instrument_name ='Saxophone'), 
 (SELECT id FROM t_styles WHERE style_name ='Jazz'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Jam Session'),
 (SELECT id FROM t_accounts WHERE email = 'postman@test.com')),

('Jam session acoustique',
 'Rejoignez-nous pour une jam session acoustique, tous instruments et tous niveaux bienvenus.',
 'Toulouse', '31000', 'acoustic_vibes@mail.com',
 NULL,
 (SELECT id FROM t_instruments WHERE instrument_name ='Guitare'), 
 (SELECT id FROM t_styles WHERE style_name ='Autre'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Jam Session'),
 (SELECT id FROM t_accounts WHERE email = 'postman@test.com')),

('Séance de jam rock',
 'Musiciens rock ou punk, tous niveaux, venez participer à une session de jam pour partager de l’énergie et de la créativité.',
 'Lyon', '69000', 'rocknrollsession@mail.fr',
 NULL,
(SELECT id FROM t_instruments WHERE instrument_name ='Batterie'), 
(SELECT id FROM t_styles WHERE style_name ='Rock'), 
(SELECT id FROM t_goals WHERE goal_type = 'Jam Session'),
 (SELECT id FROM t_accounts WHERE email = 'postman@test.com'));
