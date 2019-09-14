/*
Created: 2018-09-26
Modified: 2018-10-30
Model: PostgreSQL 10
Database: PostgreSQL 10
*/


-- Create tables section -------------------------------------------------

-- Table Actor

CREATE TABLE "Actor"(
 "actor_id" Bigint NOT NULL,
 "address_id" Bigint NOT NULL,
 "country_code_national_id" Text NOT NULL,
 "performing_name" Name NOT NULL
)
;

-- Add keys for table Actor

ALTER TABLE "Actor" ADD CONSTRAINT "Key1" PRIMARY KEY ("actor_id","country_code_national_id","address_id")
;

-- Table Director

CREATE TABLE "Director"(
 "director_id" Bigint NOT NULL,
 "address_id" Bigint NOT NULL,
 "country_code_national_id" Text NOT NULL,
 "number_of_films_directed" Bigint NOT NULL
)
;

-- Add keys for table Director

ALTER TABLE "Director" ADD CONSTRAINT "Key2" PRIMARY KEY ("director_id","country_code_national_id","address_id")
;

-- Table Movie

CREATE TABLE "Movie"(
 "movie_id" Bigint NOT NULL,
 "movie_title" Text NOT NULL,
 "release_date" Date NOT NULL,
 "rating" Real NOT NULL,
 "budget" Money NOT NULL,
 "gross" Money
)
;

-- Add keys for table Movie

ALTER TABLE "Movie" ADD CONSTRAINT "Key3" PRIMARY KEY ("movie_id")
;

-- Table Person

CREATE TABLE "Person"(
 "country_code_national_id" Text NOT NULL,
 "address_id" Bigint NOT NULL,
 "first_name" Name NOT NULL,
 "last_name" Name NOT NULL,
 "date_of_birth" Date NOT NULL,
 "eye_colour" Text
)
;

-- Add keys for table Person

ALTER TABLE "Person" ADD CONSTRAINT "Key4" PRIMARY KEY ("country_code_national_id","address_id")
;

-- Table Location

CREATE TABLE "Location"(
 "address_id" Bigint NOT NULL,
 "city" Text NOT NULL,
 "state" Text NOT NULL,
 "country" Text NOT NULL
)
;

-- Add keys for table Location

ALTER TABLE "Location" ADD CONSTRAINT "Key5" PRIMARY KEY ("address_id")
;

-- Table Reviewer

CREATE TABLE "Reviewer"(
 "username" Text NOT NULL,
 "country_code_national_id" Text NOT NULL,
 "associated_publication" Text,
 "address_id" Bigint
)
;

-- Create indexes for table Reviewer

CREATE INDEX "IX_Relationship31" ON "Reviewer" ("country_code_national_id","address_id")
;

-- Add keys for table Reviewer

ALTER TABLE "Reviewer" ADD CONSTRAINT "Key6" PRIMARY KEY ("username")
;

-- Table Theatre

CREATE TABLE "Theatre"(
 "theatre_id" Bigint NOT NULL,
 "address_id" Bigint NOT NULL,
 "size" Bigint NOT NULL,
 "name" Text NOT NULL,
 "phone_number" Text NOT NULL
)
;

-- Create indexes for table Theatre

CREATE INDEX "IX_Relationship3" ON "Theatre" ("address_id")
;

-- Add keys for table Theatre

ALTER TABLE "Theatre" ADD CONSTRAINT "Key8" PRIMARY KEY ("theatre_id")
;

ALTER TABLE "Theatre" ADD CONSTRAINT "phone_number" UNIQUE ("phone_number")
;

-- Table Genre and Rating

CREATE TABLE "Genre and Rating"(
 "film_category_id" Bigint NOT NULL,
 "film_genre" Text NOT NULL,
 "age_rating" Text NOT NULL
)
;

-- Add keys for table Genre and Rating

ALTER TABLE "Genre and Rating" ADD CONSTRAINT "Key9" PRIMARY KEY ("film_category_id")
;

-- Table Directors of Movies

CREATE TABLE "Directors of Movies"(
 "director_id" Bigint NOT NULL,
 "movie_id" Bigint NOT NULL,
 "country_code_national_id" Text NOT NULL,
 "address_id" Bigint NOT NULL
)
;

-- Add keys for table Directors of Movies

ALTER TABLE "Directors of Movies" ADD CONSTRAINT "Key10" PRIMARY KEY ("director_id","movie_id","country_code_national_id","address_id")
;

-- Table Locations of Movies

CREATE TABLE "Locations of Movies"(
 "movie_id" Bigint NOT NULL,
 "address_id" Bigint NOT NULL
)
;

-- Add keys for table Locations of Movies

ALTER TABLE "Locations of Movies" ADD CONSTRAINT "Key11" PRIMARY KEY ("movie_id","address_id")
;

-- Table Categorization of Movies

CREATE TABLE "Categorization of Movies"(
 "film_category_id" Bigint NOT NULL,
 "movie_id" Bigint NOT NULL
)
;

-- Add keys for table Categorization of Movies

ALTER TABLE "Categorization of Movies" ADD CONSTRAINT "Key12" PRIMARY KEY ("film_category_id","movie_id")
;

-- Table Actors in Movies

CREATE TABLE "Actors in Movies"(
 "actor_id" Bigint NOT NULL,
 "movie_id" Bigint NOT NULL,
 "country_code_national_id" Text NOT NULL,
 "address_id" Bigint NOT NULL
)
;

-- Add keys for table Actors in Movies

ALTER TABLE "Actors in Movies" ADD CONSTRAINT "Key14" PRIMARY KEY ("actor_id","movie_id","country_code_national_id","address_id")
;

-- Table Ticket

CREATE TABLE "Ticket"(
 "ticket_id" Bigint NOT NULL,
 "showtime_id" Bigint NOT NULL,
 "movie_id" Bigint NOT NULL,
 "theatre_id" Bigint NOT NULL,
 "price" Money NOT NULL,
 "seat_location" Text NOT NULL
)
;

-- Add keys for table Ticket

ALTER TABLE "Ticket" ADD CONSTRAINT "Key15" PRIMARY KEY ("ticket_id","showtime_id","movie_id","theatre_id")
;

-- Table Showtime

CREATE TABLE "Showtime"(
 "showtime_id" Bigint NOT NULL,
 "movie_id" Bigint NOT NULL,
 "theatre_id" Bigint NOT NULL,
 "theatre_room" Bigint NOT NULL,
 "type_of_showtime" Text NOT NULL,
 "time_of_show" Time NOT NULL
)
;

-- Add keys for table Showtime

ALTER TABLE "Showtime" ADD CONSTRAINT "Key16" PRIMARY KEY ("showtime_id","movie_id","theatre_id")
;

-- Table Producer

CREATE TABLE "Producer"(
 "producer_id" Bigint NOT NULL,
 "address_id" Bigint NOT NULL,
 "country_code_national_id" Text NOT NULL,
 "number_of_films_produced" Bigint NOT NULL
)
;

-- Add keys for table Producer

ALTER TABLE "Producer" ADD CONSTRAINT "Key17" PRIMARY KEY ("producer_id","country_code_national_id","address_id")
;

-- Table Producers of Movies

CREATE TABLE "Producers of Movies"(
 "producer_id" Bigint NOT NULL,
 "movie_id" Bigint NOT NULL,
 "country_code_national_id" Text NOT NULL,
 "address_id" Bigint NOT NULL
)
;

-- Add keys for table Producers of Movies

ALTER TABLE "Producers of Movies" ADD CONSTRAINT "Key18" PRIMARY KEY ("producer_id","country_code_national_id","movie_id","address_id")
;

-- Table Writer

CREATE TABLE "Writer"(
 "writer_id" Bigint NOT NULL,
 "address_id" Bigint NOT NULL,
 "country_code_national_id" Text NOT NULL,
 "number_of_films_written" Bigint NOT NULL
)
;

-- Add keys for table Writer

ALTER TABLE "Writer" ADD CONSTRAINT "Key19" PRIMARY KEY ("writer_id","country_code_national_id","address_id")
;

-- Table Writers of Movies

CREATE TABLE "Writers of Movies"(
 "movie_id" Bigint NOT NULL,
 "writer_id" Bigint NOT NULL,
 "country_code_national_id" Text NOT NULL,
 "address_id" Bigint NOT NULL
)
;

-- Add keys for table Writers of Movies

ALTER TABLE "Writers of Movies" ADD CONSTRAINT "Key20" PRIMARY KEY ("movie_id","writer_id","country_code_national_id","address_id")
;

-- Table Quotes

CREATE TABLE "Quotes"(
 "quote_id" Bigint NOT NULL,
 "movie_id" Bigint NOT NULL,
 "quote_description" Text NOT NULL
)
;

-- Add keys for table Quotes

ALTER TABLE "Quotes" ADD CONSTRAINT "Key21" PRIMARY KEY ("quote_id","movie_id")
;

-- Table Award

CREATE TABLE "Award"(
 "award_id" Bigint NOT NULL,
 "movie_id" Bigint NOT NULL,
 "award_organization" Text NOT NULL,
 "award_name" Text NOT NULL,
 "award_recpient" Text NOT NULL,
 "year_received" Bigint NOT NULL
)
;

-- Add keys for table Award

ALTER TABLE "Award" ADD CONSTRAINT "Key22" PRIMARY KEY ("award_id","movie_id")
;
-- Create foreign keys (relationships) section ------------------------------------------------- 

ALTER TABLE "Person" ADD CONSTRAINT "was born in" FOREIGN KEY ("address_id") REFERENCES "Location" ("address_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Theatre" ADD CONSTRAINT "is located in" FOREIGN KEY ("address_id") REFERENCES "Location" ("address_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Directors of Movies" ADD CONSTRAINT "directs" FOREIGN KEY ("director_id", "country_code_national_id", "address_id") REFERENCES "Director" ("director_id", "country_code_national_id", "address_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Directors of Movies" ADD CONSTRAINT "is directed by" FOREIGN KEY ("movie_id") REFERENCES "Movie" ("movie_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Locations of Movies" ADD CONSTRAINT "is filmed in" FOREIGN KEY ("movie_id") REFERENCES "Movie" ("movie_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Locations of Movies" ADD CONSTRAINT "is setting of" FOREIGN KEY ("address_id") REFERENCES "Location" ("address_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Categorization of Movies" ADD CONSTRAINT "categorizes" FOREIGN KEY ("film_category_id") REFERENCES "Genre and Rating" ("film_category_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Categorization of Movies" ADD CONSTRAINT "is categorized under" FOREIGN KEY ("movie_id") REFERENCES "Movie" ("movie_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Actors in Movies" ADD CONSTRAINT "acts in" FOREIGN KEY ("actor_id", "country_code_national_id", "address_id") REFERENCES "Actor" ("actor_id", "country_code_national_id", "address_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Actors in Movies" ADD CONSTRAINT "stars" FOREIGN KEY ("movie_id") REFERENCES "Movie" ("movie_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Actor" ADD CONSTRAINT "works as a" FOREIGN KEY ("country_code_national_id", "address_id") REFERENCES "Person" ("country_code_national_id", "address_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Director" ADD CONSTRAINT "works as a" FOREIGN KEY ("country_code_national_id", "address_id") REFERENCES "Person" ("country_code_national_id", "address_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Reviewer" ADD CONSTRAINT "acts as a" FOREIGN KEY ("country_code_national_id", "address_id") REFERENCES "Person" ("country_code_national_id", "address_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Ticket" ADD CONSTRAINT "gives access to" FOREIGN KEY ("showtime_id", "movie_id", "theatre_id") REFERENCES "Showtime" ("showtime_id", "movie_id", "theatre_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Showtime" ADD CONSTRAINT "has" FOREIGN KEY ("movie_id") REFERENCES "Movie" ("movie_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Showtime" ADD CONSTRAINT "is shown at" FOREIGN KEY ("theatre_id") REFERENCES "Theatre" ("theatre_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Producer" ADD CONSTRAINT "works as a" FOREIGN KEY ("country_code_national_id", "address_id") REFERENCES "Person" ("country_code_national_id", "address_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Producers of Movies" ADD CONSTRAINT "produces" FOREIGN KEY ("producer_id", "country_code_national_id", "address_id") REFERENCES "Producer" ("producer_id", "country_code_national_id", "address_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Producers of Movies" ADD CONSTRAINT "is produced by" FOREIGN KEY ("movie_id") REFERENCES "Movie" ("movie_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Writer" ADD CONSTRAINT "works as a" FOREIGN KEY ("country_code_national_id", "address_id") REFERENCES "Person" ("country_code_national_id", "address_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Writers of Movies" ADD CONSTRAINT "is written by" FOREIGN KEY ("movie_id") REFERENCES "Movie" ("movie_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Writers of Movies" ADD CONSTRAINT "writes" FOREIGN KEY ("writer_id", "country_code_national_id", "address_id") REFERENCES "Writer" ("writer_id", "country_code_national_id", "address_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Quotes" ADD CONSTRAINT "from" FOREIGN KEY ("movie_id") REFERENCES "Movie" ("movie_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Award" ADD CONSTRAINT "receives" FOREIGN KEY ("movie_id") REFERENCES "Movie" ("movie_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;




