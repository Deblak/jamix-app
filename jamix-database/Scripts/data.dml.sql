DELETE FROM t_users;
DELETE FROM t_goals;
DELETE FROM t_styles;
DELETE FROM t_instruments;
DELETE FROM t_offers;

INSERT INTO t_users (username, email, password) VALUES 
('test', 'test@test.com', 'test');

INSERT INTO t_offers (offer_title, offer_desc, city, zip_code, contact_email) VALUES
--1
('Cherche chanteur H/F', 
'Bonjour à tous ! Nous cherchons à créer un groupe donc il nous manque... l''intitulé de l''annonce! 
N''hésitez pas à prendre contact si vous voulez plus d’informations!', 'Poitiers', 86000, 'emma.durand86@mail.com'),
--2
('ZOOMBEE X cherche son bassiste',
'Salut !
ZOOMBEE X recherche son/sa bassiste en vue de concerts. Répertoire de compos disponibles et nouvelles à venir. Style punk/rock/grunge.
Salle de répétition avec sono aux alentours de Vesoul. Ambiance détendue assurée.
Lignes de basses ouvertes à ta personnalité, Bienvenu(e) à toi!', 'Cognac', 16100, 'julien.martin16@mail.net'),
--3
('Cherche musiciens jazz', 
'Bonjour,
Je cherche des musiciens jazz ou autre comme le funk ou rock on verra sur place. Je suis amateur et non professionnel on commence tous par un début non 😉. 
Je cherche des personnes qui voudrais comme moi jouer en groupe pour permettre de faire des reprise ou créé des morceaux et ce perfectionner fur à mesure.
Bien musicalement.', 'Grenoble', 38000, 'sophie.leclerc38@mail.fr'),
--4
('Groupe de Tourcoing cherche son chanteur',
'Salut les chanteurs !
Notre groupe de reprises cherche un chanteur à l’aise en Français et en Anglais.
Je dis bien un chanteur car il y a déjà une chanteuse et l’idée est de monter des duo... on en parle quand vous voulez.
Qui viendrait s’amuser avec nous, AVEC ASSIDUITÉ, tous les vendredis soir à Tourcoing ?
Le style est pop-rock des années 70-80, Free, Rolling Stones, 4 non-Blondes… tu vois le genre.
Nous n’avons pas d’ambition professionnelle mais même si nous jouons surtout pour le plaisir, nous désirons planifier quelques prestations dans l’année ! 
A bientôt !','Marseille', 13000, 'antoine.bernard13@mail.org'),
--5
('Musicien cherche musicien', 
'Bonjour,
Je m’appel Romain, j’habite à côté de Tours.
Je suis percussionniste (timbales latines). Je cherche d’autres musiciens pour former un groupe ou juste pratiquer ensemble sans prise de tête.
Style : cumbia salsa vallenato musique latine merengue batchata
Secteur Touraine Maine et loire', 'Dijon', 21231, 'lucas.perrin@mail.fr'),
--6
('Bassiste cherche groupe',
'Tchuss! Pour cause changement de département, bassiste cherche groupe région epernay/reims. Influence variée, mais plus axé sur rock, rock alternatif et variétés!
Dispo à partir de septembre de cette année!
Hésitez pas à me contacter si intéressé!
Musicalement', 'Lyon', 69000, 'thomas.moreau@mail.net'),
--7
('Musiciens',
'Cherche musicien retraite amateur accordeonniste ou synthe pour rejoindre petit groupe de benevoles pour aller dans maison de retraite.', 'Roubaix', 59512, 'rhythmic_beat@mail.org'),
--8
('Musiciens cherche groupe',
'Joueur de Cajon cherche formation musicale tous styles.
Joueur de Cajon assez bon niveau, cherche musiciens pour jouer tous styles de musiques en toute simplicité.
Répétition sur Auxerre et alentours.
Je suis ouvert à tous projets… N’hésitez pas à me contacter par mail.
À bientôt peut être ?', 'Rennes', 35000, 'basslover@mail.com'),
--9
('Musicien classique cherche musicienne-ien pour répéter',
'Bonjour,
Je suis musicien classique, je joue surtout du Violoncelle.
Je cherche musicienne ou musicien jouant d''un instrument (flute, violon, clarinette, etc...) pour répéter en duo à Pontivy.
J''ai un bon niveau musical, en violoncelle mais aussi composition, et j''ai joué souvent en duos et musique orchestrale, ou en solo. Je trouve qu''ici il y a peu d''échanges entre musiciens classiques, c''est dommage...
Merci de me contacter et me dire quel instrument vous jouez, ce que vous aimeriez jouer, bref à voir en messages
Je peux répéter en fin de journée
À bientôt!', 'Rouan', 76000, 'pixel_rider@mail.org'),
--10
('Création d''un quatuor',
'Pianiste diplômée cherche musiciens confirmés (violon, violoncelle, flûte traversière, hautbois, harpe) pour former un quatuor classique "musique de chambre baroque".
Pas sérieux s''abstenir.', 'Montpellier',34000, 'harmonic_soul@gmail.net')
;

--thanks to https://mimo-international.com/MIMO
INSERT INTO t_instruments (instrument_name) VALUES
-- instruments vent
('Flûte'), ('Clarinette'),('Hautbois'),('Trompette'),('Cors'),('Accordéon'),('Cornemuse'),('Trombone'),('Clairon'),('Flûte de pan'),('Tuba'),('Trompe de chasse'),('Harmonica'),('Saxophone'),('Lyre'),
-- instruments percussion
('Tambour'),('Cloche'),('Xylophone'),('Cymbale'),('Castagnettes'),('Batterie'),
-- instruments cordes
('Cithare'),('Violon'),('Luth'),('Guitare'),('Guitare basse'),('Guitare électrique'),('Banjo'),('Basse électrique'),('Ukulélé'),('Harpe'),('Violoncelle'),('Mandoline'),('Contrebasse'),
-- instruments clavier
('Piano'),('Clavecin'),('Synthétiseur'),('Orgue'), ('Autre');

INSERT INTO t_styles (style_name) VALUES
('Blues'), ('Chanson française'), ('Country'), ('Dubstep'), ('Musique classique'), ('Electro'), ('Funk'), ('Gospel'), ('Indie'), ('Jazz'), ('J-pop'), ('Metal'), ('Pop'), ('Pop Rock'), ('Rap'), ('Reggae'), ('Rock'), ('RnB'), ('Soul'), ('Autre');

INSERT INTO t_goals (goal_name) VALUES
('Entraînement'), ('Jam Session'), ('Monter un groupe');



