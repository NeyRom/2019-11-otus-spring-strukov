-- Genres populating --
INSERT INTO public.genres (id, title)
	VALUES (1,'драма');
INSERT INTO public.genres (id, title)
	VALUES (2,'приключения');
INSERT INTO public.genres (id, title)
	VALUES (3,'пьеса');
INSERT INTO public.genres (id, title)
	VALUES (4,'поэма');
INSERT INTO public.genres (id, title)
	VALUES (5,'комедия');

-- Authors populating --
INSERT INTO public.authors (id, first_name, last_name, middle_name)
    VALUES (1, 'Александр', 'Пушкин', 'Сергеевич');
INSERT INTO public.authors (id,first_name,last_name,middle_name)
	VALUES (2,'Теодор','Драйзер','');
INSERT INTO public.authors (id,first_name,last_name,middle_name)
	VALUES (3,'Джеймс Фенимор','Купер','');
INSERT INTO public.authors (id,first_name,last_name,middle_name)
	VALUES (4,'Николай','Гоголь','Васильевич');

-- Books populating --
INSERT INTO public.books (title,isbn,release_date,author_id,genre_id)
	VALUES ('Евгений Онегин','9785699956531','2017-05-04',1,1);
INSERT INTO public.books (title,isbn,release_date,author_id,genre_id)
	VALUES ('Финансист','9785446717262','2018-07-11',2,1);
INSERT INTO public.books (title,isbn,release_date,author_id,genre_id)
	VALUES ('Последний из могикан','9785171202736','2020-01-18',3,2);
INSERT INTO public.books (title,isbn,release_date,author_id,genre_id)
	VALUES ('Мёртвые души','9785170878895','2015-03-15',4,4);
INSERT INTO public.books (title,isbn,release_date,author_id,genre_id)
	VALUES ('Ревизор','9785926827436','2018-10-06',4,5);
