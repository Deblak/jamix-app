DELETE FROM t_accounts;
DELETE FROM t_offers;
DELETE FROM t_goals;
DELETE FROM t_styles;
DELETE FROM t_instruments;

INSERT INTO t_accounts (username, email, password) VALUES 
('test', 'test@test.com', 'Test001!');

--thanks to https://mimo-international.com/MIMO
INSERT INTO t_instruments (instrument_name) VALUES
('Voix'),
-- instruments vent
('Flûte'), ('Clarinette'),('Hautbois'),('Trompette'),('Cors'),('Accordéon'),('Cornemuse'),('Trombone'),('Clairon'),('Flûte de pan'),('Tuba'),('Trompe de chasse'),('Harmonica'),('Saxophone'),('Lyre'),
-- instruments percussion
('Tambour'),('Cloche'),('Xylophone'),('Cymbale'),('Castagnettes'),('Batterie'), ('Cajón'),
-- instruments cordes
('Cithare'),('Violon'),('Luth'),('Guitare'),('Guitare basse'),('Guitare électrique'),('Banjo'),('Basse électrique'),('Ukulélé'),('Harpe'),('Violoncelle'),('Mandoline'),('Contrebasse'),
-- instruments clavier
('Piano'),('Clavecin'),('Synthétiseur'),('Orgue'), ('Clavier'),('Autre');

INSERT INTO t_styles (style_name) VALUES
('Ambiant'), ('Blues'), ('Bossa Nova'), ('Chanson française'), ('Country'), ('Dubstep'), ('Musique africaine'), ('Musique classique'), ('Musique irlandaise'), ('Musique orientale'), ('Musique latine'), ('Electro'), ('Flamenco'), ('Funk'), ('Folk'), ('Gospel'), ('Hip-hop'), ('House'), ('Indie'), ('Jazz'), ('J-pop'), ('K-pop'), ('Lo-fi'), ('Métal'), ('Pop'), ('Pop Rock'), ('Rap'), ('Reggae'), ('Rock'), ('RnB'), ('Soul'), ('Techno'), ('Autre');

INSERT INTO t_goals (goal_type) VALUES
('Entraînement'), ('Jam Session'), ('Monter un groupe');

INSERT INTO t_offers (offer_title, offer_desc, city, zip_code, contact_email, id_instrument, id_style, id_goal) VALUES
-- 1
('Cherche musiciens jazz',
 'Je cherche des musiciens jazz, funk ou rock pour jouer en groupe et progresser ensemble.',
 'Cognac', '16100', 'sophie.leclerc38@mail.fr',
 (SELECT id FROM t_instruments WHERE instrument_name = 'Clavier'), 
 (SELECT id FROM t_styles WHERE style_name = 'Jazz'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Jam Session')),

-- 2
('ZOOMBEE X cherche son bassiste',
 'Salut ! ZOOMBEE X recherche son/sa bassiste en vue de concerts. Répertoire de compos disponibles et nouvelles à venir. Style punk/rock/grunge.',
 'Vesoul', '70000', 'julien.martin16@mail.net',
 (SELECT id FROM t_instruments WHERE instrument_name = 'Guitare basse'), 
 (SELECT id FROM t_styles WHERE style_name = 'Rock'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Monter un groupe')),
 
-- 3
('Cherche musiciens jazz',
 'Je cherche des musiciens jazz, funk ou rock pour jouer en groupe et progresser ensemble.',
 'Cognac', '16100', 'sophie.petit38@mail.fr',
 (SELECT id FROM t_instruments WHERE instrument_name = 'Clavier'), 
 (SELECT id FROM t_styles WHERE style_name = 'Jazz'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Jam Session')),

-- 4
('Groupe de Tourcoing cherche son chanteur',
 'Notre groupe de reprises cherche un chanteur à l’aise en Français et en Anglais.',
 'Tourcoing', '59599', 'antoine.bernard13@mail.org', 
 (SELECT id FROM t_instruments WHERE instrument_name ='Voix'), 
 (SELECT id FROM t_styles WHERE style_name ='Pop Rock'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Monter un groupe')),

-- 5
('Musicien cherche musicien',
 'Je suis percussionniste (timbales latines) et cherche d’autres musiciens pour former un groupe de musique latine.',
 'Dijon', '21231', 'lucas.perrin@mail.fr', 
 (SELECT id FROM t_instruments WHERE instrument_name ='Autre'), 
 (SELECT id FROM t_styles WHERE style_name ='Musique latine'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Jam Session')),

-- 6
('Bassiste cherche groupe',
 'Bassiste cherche groupe région Epernay/Reims. Dispo à partir de septembre !',
 'Lyon', '69000', 'thomas.moreau@mail.net', 
 (SELECT id FROM t_instruments WHERE instrument_name ='Basse électrique'), 
 (SELECT id FROM t_styles WHERE style_name ='Rock'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Monter un groupe')),

-- 7
('Musiciens bénévoles',
 'Cherche musiciens retraités pour jouer dans des maisons de retraite.',
 'Roubaix', '59512', 'rhythmic_beat@mail.org', 
 (SELECT id FROM t_instruments WHERE instrument_name ='Accordéon'), 
 (SELECT id FROM t_styles WHERE style_name ='Chanson française'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Jam Session')),

-- 8
('Musicien cherche groupe',
 'Joueur de Cajon cherche musiciens pour jouer tous styles en toute simplicité.',
 'Auxerre', '89000', 'basslover@mail.com', 
 (SELECT id FROM t_instruments WHERE instrument_name ='Cajón'), 
 (SELECT id FROM t_styles WHERE style_name ='Blues'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Jam Session')),

-- 9
('Musicien classique cherche partenaire',
 'Je suis violoncelliste et cherche un partenaire pour répétitions de musique classique.',
 'Pontivy', '56300', 'pixel_rider@mail.org', 
 (SELECT id FROM t_instruments WHERE instrument_name ='Violoncelle'), 
 (SELECT id FROM t_styles WHERE style_name ='Musique classique'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Entraînement')),

-- 10
('Création d’un quatuor',
 'Pianiste cherche musiciens confirmés pour quatuor classique baroque.',
 'Montpellier', '34000', 'harmonic_soul@gmail.net', 
 (SELECT id FROM t_instruments WHERE instrument_name ='Piano'), 
 (SELECT id FROM t_styles WHERE style_name ='Musique classique'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Monter un groupe')),

-- 11
('Guitariste cherche groupe de rock',
 'Guitariste rock cherche des musiciens pour créer un groupe, compositions originales et concerts à prévoir.',
 'Bordeaux', '33000', 'antoine.lambert@mail.fr', 
 (SELECT id FROM t_instruments WHERE instrument_name ='Guitare électrique'), 
 (SELECT id FROM t_styles WHERE style_name ='Rock'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Monter un groupe')),

-- 12
('Cherche musicien(ne) pour groupe indie',
 'Nous recherchons un(e) guitariste pour rejoindre notre groupe indie et créer ensemble.',
 'Nantes', '44000', 'lucie.martin23@mail.net', 
 (SELECT id FROM t_instruments WHERE instrument_name ='Guitare électrique'), 
 (SELECT id FROM t_styles WHERE style_name ='Indie'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Monter un groupe')),

-- 13
('Formation groupe métal',
 'Cherche des musiciens pour monter un groupe de métal, influence heavy et thrash.',
 'Lille', '59000', 'frederic.garnier@mail.com', 
 (SELECT id FROM t_instruments WHERE instrument_name ='Batterie'), 
 (SELECT id FROM t_styles WHERE style_name ='Métal'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Monter un groupe')),

-- 14
('Recrutement groupe pop/rock',
 'Guitariste, bassiste et batteur cherchent chanteur(se) pour former un groupe pop/rock.',
 'Rennes', '35000', 'julie.mayor@mail.fr', 
 (SELECT id FROM t_instruments WHERE instrument_name ='Voix'), 
 (SELECT id FROM t_styles WHERE style_name ='Pop Rock'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Monter un groupe')),

-- 15
('Musiciens pour projet folk',
 'Je suis violoniste et je cherche d’autres musiciens pour créer un groupe de musique folk.',
 'Strasbourg', '67000', 'elise.robert@mail.org', 
 (SELECT id FROM t_instruments WHERE instrument_name ='Violon'), 
 (SELECT id FROM t_styles WHERE style_name ='Folk'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Monter un groupe')),

-- 16
('Séance d’entraînement jazz',
 'Musiciens de jazz cherchent à se retrouver régulièrement pour des sessions d’entraînement et de pratique.',
 'Paris', '75000', 'jazzlover@mail.net', 
 (SELECT id FROM t_instruments WHERE instrument_name ='Piano'), 
 (SELECT id FROM t_styles WHERE style_name ='Jazz'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Entraînement')),

-- 17
('Cours et entraînements pour musiciens débutants',
 'Je propose des séances de travail pour les musiciens débutants souhaitant progresser dans un cadre structuré.',
 'Lyon', '69000', 'musiccoach@mail.com', 
 (SELECT id FROM t_instruments WHERE instrument_name ='Batterie'), 
 (SELECT id FROM t_styles WHERE style_name ='Pop'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Entraînement')),

-- 18
('Jam session jazz et improvisation',
 'Musiciens de jazz souhaitant faire une jam session et improviser ensemble tous styles bienvenus.',
 'Marseille', '13000', 'peter.davis@mail.org', 
 (SELECT id FROM t_instruments WHERE instrument_name ='Saxophone'), 
 (SELECT id FROM t_styles WHERE style_name ='Jazz'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Jam Session')),

-- 19
('Jam session acoustique',
 'Rejoignez-nous pour une jam session acoustique, tous instruments et tous niveaux bienvenus.',
 'Toulouse', '31000', 'acoustic_vibes@mail.com', 
 (SELECT id FROM t_instruments WHERE instrument_name ='Guitare'), 
 (SELECT id FROM t_styles WHERE style_name ='Autre'), 
 (SELECT id FROM t_goals WHERE goal_type = 'Jam Session')),

-- 20
('Séance de jam rock',
 'Musiciens rock ou punk, tous niveaux, venez participer à une session de jam pour partager de l’énergie et de la créativité.',
 'Lyon', '69000', 'rocknrollsession@mail.fr', 
(SELECT id FROM t_instruments WHERE instrument_name ='Batterie'), 
(SELECT id FROM t_styles WHERE style_name ='Rock'), 
(SELECT id FROM t_goals WHERE goal_type = 'Jam Session'));
