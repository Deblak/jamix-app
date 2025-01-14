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
('Tambour'),('Cloche'),('Xylophone'),('Cymbale'),('Castagnettes'),('Batterie'), ('Cajon'),
-- instruments cordes
('Cithare'),('Violon'),('Luth'),('Guitare'),('Guitare basse'),('Guitare électrique'),('Banjo'),('Basse électrique'),('Ukulélé'),('Harpe'),('Violoncelle'),('Mandoline'),('Contrebasse'),
-- instruments clavier
('Piano'),('Clavecin'),('Synthétiseur'),('Orgue'), ('Clavier'),('Autre');

INSERT INTO t_styles (style_name) VALUES
('Blues'), ('Chanson française'), ('Country'), ('Dubstep'), ('Musique classique'), ('Musique latine'),('Electro'), ('Funk'), ('Folk'),('Gospel'), ('Indie'), ('Jazz'), ('J-pop'), ('Métal'), ('Pop'), ('Pop Rock'), ('Rap'), ('Reggae'), ('Rock'), ('RnB'), ('Soul'), ('Autre');

INSERT INTO t_goals (goal_name) VALUES
('Entraînement'), ('Jam Session'), ('Monter un groupe');

INSERT INTO t_offers (offer_title, offer_desc, city, zip_code, contact_email, instrument_name, style_name, goal_name) VALUES
-- 1
('Cherche chanteur H/F',
 'Bonjour à tous ! Nous cherchons à créer un groupe donc il nous manque... l''intitulé de l''annonce ! N''hésitez pas à prendre contact si vous voulez plus d’informations !',
 'Poitiers', '86000', 'emma.durand86@mail.com', 'Voix', 'Pop Rock', 'Monter un groupe'),

-- 2
('ZOOMBEE X cherche son bassiste',
 'Salut ! ZOOMBEE X recherche son/sa bassiste en vue de concerts. Répertoire de compos disponibles et nouvelles à venir. Style punk/rock/grunge.',
 'Vesoul', '70000', 'julien.martin16@mail.net', 'Guitare basse', 'Rock', 'Monter un groupe'),

-- 3
('Cherche musiciens jazz',
 'Je cherche des musiciens jazz, funk ou rock pour jouer en groupe et progresser ensemble.',
 'Cognac', '16100', 'sophie.leclerc38@mail.fr', 'Clavier', 'Jazz', 'Jam Session'),

-- 4
('Groupe de Tourcoing cherche son chanteur',
 'Notre groupe de reprises cherche un chanteur à l’aise en Français et en Anglais.',
 'Tourcoing', '59599', 'antoine.bernard13@mail.org', 'Voix', 'Pop Rock', 'Monter un groupe'),

-- 5
('Musicien cherche musicien',
 'Je suis percussionniste (timbales latines) et cherche d’autres musiciens pour former un groupe de musique latine.',
 'Dijon', '21231', 'lucas.perrin@mail.fr', 'Autre', 'Musique latine', 'Jam Session'),

-- 6
('Bassiste cherche groupe',
 'Bassiste cherche groupe région Epernay/Reims. Dispo à partir de septembre !',
 'Lyon', '69000', 'thomas.moreau@mail.net', 'Basse électrique', 'Rock', 'Monter un groupe'),

-- 7
('Musiciens bénévoles',
 'Cherche musiciens retraités pour jouer dans des maisons de retraite.',
 'Roubaix', '59512', 'rhythmic_beat@mail.org', 'Accordéon', 'Chanson française', 'Jam Session'),

-- 8
('Musicien cherche groupe',
 'Joueur de Cajon cherche musiciens pour jouer tous styles en toute simplicité.',
 'Auxerre', '89000', 'basslover@mail.com', 'Cajon', 'Blues', 'Jam Session'),

-- 9
('Musicien classique cherche partenaire',
 'Je suis violoncelliste et cherche un partenaire pour répétitions de musique classique.',
 'Pontivy', '56300', 'pixel_rider@mail.org', 'Violoncelle', 'Musique classique', 'Entraînement'),

-- 10
('Création d’un quatuor',
 'Pianiste cherche musiciens confirmés pour quatuor classique baroque.',
 'Montpellier', '34000', 'harmonic_soul@gmail.net', 'Piano', 'Musique classique', 'Monter un groupe'),

-- 11
('Guitariste cherche groupe de rock',
 'Guitariste rock cherche des musiciens pour créer un groupe, compositions originales et concerts à prévoir.',
 'Bordeaux', '33000', 'antoine.lambert@mail.fr', 'Guitare électrique', 'Rock', 'Monter un groupe'),

-- 12
('Cherche musicien(ne) pour groupe indie',
 'Nous recherchons un(e) guitariste pour rejoindre notre groupe indie et créer ensemble.',
 'Nantes', '44000', 'lucie.martin23@mail.net', 'Guitare électrique', 'Indie', 'Monter un groupe'),

-- 13
('Formation groupe métal',
 'Cherche des musiciens pour monter un groupe de métal, influence heavy et thrash.',
 'Lille', '59000', 'frederic.garnier@mail.com', 'Batterie', 'Métal', 'Monter un groupe'),

-- 14
('Recrutement groupe pop/rock',
 'Guitariste, bassiste et batteur cherchent chanteur(se) pour former un groupe pop/rock.',
 'Rennes', '35000', 'julie.mayor@mail.fr', 'Voix', 'Pop Rock', 'Monter un groupe'),

-- 15
('Musiciens pour projet folk',
 'Je suis violoniste et je cherche d’autres musiciens pour créer un groupe de musique folk.',
 'Strasbourg', '67000', 'elise.robert@mail.org', 'Violon', 'Folk', 'Monter un groupe'),

-- 16
('Séance d’entraînement jazz',
 'Musiciens de jazz cherchent à se retrouver régulièrement pour des sessions d’entraînement et de pratique.',
 'Paris', '75000', 'jazzlover@mail.net', 'Piano', 'Jazz', 'Entraînement'),

-- 17
('Cours et entraînements pour musiciens débutants',
 'Je propose des séances de travail pour les musiciens débutants souhaitant progresser dans un cadre structuré.',
 'Lyon', '69000', 'musiccoach@mail.com', 'Batterie', 'Pop', 'Entraînement'),

-- 18
('Jam session jazz et improvisation',
 'Musiciens de jazz souhaitant faire une jam session et improviser ensemble tous styles bienvenus.',
 'Marseille', '13000', 'peter.davis@mail.org', 'Saxophone', 'Jazz', 'Jam Session'),

-- 19
('Jam session acoustique',
 'Rejoignez-nous pour une jam session acoustique, tous instruments et tous niveaux bienvenus.',
 'Toulouse', '31000', 'acoustic_vibes@mail.com', 'Guitare', 'Autre', 'Jam Session'),

-- 20
('Séance de jam rock',
 'Musiciens rock ou punk, tous niveaux, venez participer à une session de jam pour partager de l’énergie et de la créativité.',
 'Lyon', '69000', 'rocknrollsession@mail.fr', 'Batterie', 'Rock', 'Jam Session');
