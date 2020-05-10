-- Genres populating --
INSERT INTO genres (title)
	VALUES ('драма');
INSERT INTO genres (title)
	VALUES ('приключения');
INSERT INTO genres (title)
	VALUES ('пьеса');
INSERT INTO genres (title)
	VALUES ('поэма');
INSERT INTO genres (title)
	VALUES ('комедия');

-- Authors populating --
INSERT INTO authors (first_name,last_name,middle_name)
	VALUES ('Теодор','Драйзер','');
INSERT INTO authors (first_name, last_name, middle_name)
    VALUES ('Александр', 'Пушкин', 'Сергеевич');
INSERT INTO authors (first_name,last_name,middle_name)
	VALUES ('Джеймс Фенимор','Купер','');
INSERT INTO authors (first_name,last_name,middle_name)
	VALUES ('Николай','Гоголь','Васильевич');

-- Books populating --
INSERT INTO books (title,isbn,release_date,author_id,genre_id)
	VALUES ('Евгений Онегин','9785699956531','2017-05-04',2,1);
INSERT INTO books (title,isbn,release_date,author_id,genre_id)
	VALUES ('Финансист','9785446717262','2018-07-11',1,1);
INSERT INTO books (title,isbn,release_date,author_id,genre_id)
	VALUES ('Последний из могикан','9785171202736','2020-01-18',3,2);
INSERT INTO books (title,isbn,release_date,author_id,genre_id)
	VALUES ('Мёртвые души','9785170878895','2015-03-15',4,4);
INSERT INTO books (title,isbn,release_date,author_id,genre_id)
	VALUES ('Ревизор','9785926827436','2018-10-06',4,5);
INSERT INTO books (title,isbn,release_date,author_id,genre_id)
	VALUES ('Титан','9785446717279','2012-09-18',1,1);
	
-- Comments populating --
INSERT INTO comments (content,book_id)
	VALUES ('Пушкин - наше все',1);
INSERT INTO comments (content,book_id)
	VALUES ('Николай Васильевич жжот!',5);
INSERT INTO comments (content,book_id)
	VALUES ('Аффтар пеши исчо!',2);
INSERT INTO comments (content,book_id)
	VALUES ('Пушкин - ас',1);
INSERT INTO comments (content,book_id)
	VALUES ('Мощно задвинул!',3);