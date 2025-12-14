DELETE FROM book WHERE isbn in ('9783140464079', '9780155658110');
INSERT INTO book (isbn, title, author, publication_date, available) VALUES ('9783140464079', 'Le Petit Prince', 'Antoine de Saint-Exupéry', '1943-04-01', 1);
INSERT INTO book (isbn, title, author, publication_date, available) VALUES ('9780155658110', '1984', 'George Orwell', '1949-06-08', 1);

DELETE FROM "user" WHERE uuid in ('468103a7-23de-4d94-87ab-8e7af0e3a553', 'b609a0dd-ecf4-4db0-9882-26798a7cf37f');
INSERT INTO "user" (uuid, name, email, password, role) VALUES
                                         ('468103a7-23de-4d94-87ab-8e7af0e3a553', 'Jean Dupont', 'jean.dupont@example.com', 'azerty', 'ADMINISTRATOR'),
                                         ('b609a0dd-ecf4-4db0-9882-26798a7cf37f', 'Marie Martin', 'marie.martin@example.com', 'azerty', 'CUSTOMER');
