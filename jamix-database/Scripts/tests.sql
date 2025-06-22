SELECT * FROM t_offers;
SELECT * FROM t_accounts;
SELECT * FROM t_locations WHERE zip_code LIKE '59%';
SELECT * FROM t_roles;
SELECT * FROM t_instruments;
SELECT * FROM t_goals;
SELECT * FROM t_styles;

INSERT INTO t_accounts (username, email, password) VALUES 
('Test', 'test@test.com', 'Azertyuiop01!'); --bad practice
DELETE FROM t_accounts WHERE id = 3;

SELECT role_name, LENGTH(role_name), HEX(role_name) FROM t_roles;

DELETE FROM t_accounts
WHERE email = 'postma2@test.com';










