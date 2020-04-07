DROP TABLE IF EXISTS public.authors;
DROP TABLE IF EXISTS public.genres;
DROP TABLE IF EXISTS public.books;

-- Authors --
CREATE TABLE public.authors (
	id bigserial NOT NULL,
	first_name varchar(50) NOT NULL,
	last_name varchar(50) NOT NULL,
	middle_name varchar(50) NULL,
	CONSTRAINT authors_pk PRIMARY KEY (id)
);
CREATE INDEX authors_id_idx ON public.authors (id);

-- Genres --
CREATE TABLE public.genres (
	id serial NOT NULL,
	title varchar(30) NOT NULL,
	CONSTRAINT genres_pk PRIMARY KEY (id)
);
CREATE INDEX genres_id_idx ON public.genres (id);

-- Books --
CREATE TABLE public.books (
	id bigserial NOT NULL,
	title varchar(255) NOT NULL,
	isbn char(13) NOT NULL,
	release_date date NOT NULL,
	author_id int8 NOT NULL,
	genre_id int4 NOT NULL,
	CONSTRAINT books_pk PRIMARY KEY (id)
);
CREATE INDEX books_id_idx ON public.books (id);