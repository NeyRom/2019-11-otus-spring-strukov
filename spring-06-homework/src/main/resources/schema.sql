-- Authors --
CREATE TABLE authors (
	id identity NOT NULL,
	first_name varchar(50) NOT NULL,
	last_name varchar(50) NOT NULL,
	middle_name varchar(50) NULL,
	PRIMARY KEY (id)
);

-- Genres --
CREATE TABLE genres (
	id identity NOT NULL,
	title varchar(30) NOT NULL,
	PRIMARY KEY (id)
);

-- Books --
CREATE TABLE books (
	id identity NOT NULL,
	title varchar(255) NOT NULL,
	isbn char(13) NOT NULL,
	release_date date NOT NULL,
	author_id int8 NOT NULL,
	genre_id int4 NOT NULL,
	PRIMARY KEY (id)
);

-- Comments --
CREATE TABLE comments (
	id identity NOT NULL,
	content varchar(255) NOT NULL,
	book_id bigint references books (id) on delete cascade,
	PRIMARY KEY (id)
);
