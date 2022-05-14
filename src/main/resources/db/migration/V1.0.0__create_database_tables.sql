
CREATE SEQUENCE movie_movie_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE producer_producer_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE studio_studio_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE movie (
  movie_id BIGINT NOT NULL,
  title VARCHAR(255) NOT NULL,
  winner BOOLEAN NOT NULL,
  year INTEGER NOT NULL
);

ALTER TABLE movie ADD CONSTRAINT pk_movie PRIMARY KEY (movie_id);

CREATE INDEX idx_movie_year ON movie (year);

CREATE TABLE movie_producer (
  movie_id BIGINT NOT NULL,
  producer_id BIGINT NOT NULL
);

ALTER TABLE movie_producer ADD CONSTRAINT pk_movie_producer PRIMARY KEY (movie_id, producer_id);

CREATE TABLE movie_studio (
  movie_id BIGINT NOT NULL,
  studio_id BIGINT NOT NULL
);

ALTER TABLE movie_studio ADD CONSTRAINT pk_movie_studio PRIMARY KEY (movie_id, studio_id);

CREATE TABLE producer (
  producer_id BIGINT NOT NULL,
  name VARCHAR(255) NOT NULL
);

ALTER TABLE producer ADD CONSTRAINT pk_producer PRIMARY KEY (producer_id);

CREATE TABLE studio (
  studio_id BIGINT NOT NULL,
  name VARCHAR(255) NOT NULL
);

ALTER TABLE studio ADD CONSTRAINT pk_studio PRIMARY KEY (studio_id);

ALTER TABLE movie_producer ADD CONSTRAINT fk_producer_movie_producer FOREIGN KEY (producer_id) REFERENCES producer;
ALTER TABLE movie_producer ADD CONSTRAINT fk_movie_movie_producer FOREIGN KEY (movie_id) REFERENCES movie;
ALTER TABLE movie_studio ADD CONSTRAINT fk_studio_movie_studio FOREIGN KEY (studio_id) REFERENCES studio;
ALTER TABLE movie_studio ADD CONSTRAINT fk_movie_movie_studio FOREIGN KEY (movie_id) REFERENCES movie;