DELETE FROM t_offers;
DELETE FROM t_locations;
DELETE FROM t_goals;
DELETE FROM t_styles;
DELETE FROM t_instruments;
DELETE FROM t_accounts;
DELETE FROM t_roles;

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

INSERT INTO t_locations (city, zip_code) VALUES ('Cognac', '16100'), ('Lille', '59000'), ('Pontivy', '56300'), ('Vesoul', '70000'), ('Tourcoing', '59200'),
('Dijon', '21000'), ('Lyon', '69001'), ('Roubaix', '59100'), ('Auxerre', '89000'), ('Montpellier', '34000'), ('Bordeaux', '33000'), ('Nantes', '44000'),
 ('Rennes', '35000'), ('Strasbourg', '67000'), ('Paris', '75001'), ('Marseille', '13001'), ('Toulouse', '31000');

INSERT INTO t_roles (role_name, default_role) VALUES 
('MUSICIAN', true),
('ADMIN', false);