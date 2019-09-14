-- Mir Afgan Talpur --

-- TASK ONE --
-- 1. a) Find directors from England
SELECT first_name, last_name, country
FROM  "Director", "Person", "Location"
WHERE "Director".country_code_national_id = "Person".country_code_national_id AND
			"Location".address_id = "Director".address_id AND
				country='England'; -- change this line to: country='Canada'; if you want Canadian directors


-- 1. b) Find movies directed by Christopher Nolan
SELECT first_name, last_name, movie_title
FROM  "Director", "Person", "Directors of Movies", "Movie"
WHERE "Director".country_code_national_id = "Person".country_code_national_id AND
			"Directors of Movies".director_id = "Director".director_id AND
				"Directors of Movies".movie_id = "Movie".movie_id AND
					first_name='Christopher' AND last_name='Nolan'; -- change this line to: first_name='David' AND last_name='Lynch'; if you want Canadian directors


-- 1. c) Find actors from movies costing less than $1M USD, in many currencies
SELECT performing_name, movie_title,
									budget AS budget_usd,
									budget*1.31 AS budget_cad,
									budget*113 AS budget_jpy,
									budget*65.76 AS budget_rub,
									budget*.88 AS budget_eur,
									budget*1.01 AS budget_chf
FROM  "Actor", "Actors in Movies", "Movie"
WHERE "Actors in Movies".actor_id = "Actor".actor_id AND
		"Actors in Movies".movie_id = "Movie".movie_id AND
			CAST(budget AS decimal) > 1000000;


-- 1. d) All the directors whose surname start with letter “A” and “D”
SELECT last_name,first_name
FROM  "Director", "Person"
WHERE "Director".country_code_national_id = "Person".country_code_national_id AND
		(last_name LIKE 'A%' OR last_name LIKE 'D%')


-- 1. e) All the drama movies that feature actors who are younger than 40
SELECT performing_name, AGE(date_of_birth) as actor_age, movie_title, film_genre
FROM  "Actor", "Person", "Movie","Actors in Movies", "Categorization of Movies", "Genre and Rating"
WHERE CAST(date_part('year',AGE(date_of_birth)) as integer) < 40 AND -- find people younger than 40
			"Person".country_code_national_id = "Actor".country_code_national_id AND -- narrow down to people who are actors
			"Actors in Movies".actor_id = "Actor".actor_id AND -- find movies where this actor is in
			"Actors in Movies".movie_id = "Movie".movie_id AND
			"Categorization of Movies".movie_id = "Movie".movie_id AND -- make sure movie is a drama movie
			"Categorization of Movies".film_category_id = "Genre and Rating".film_category_id AND
			film_genre='Drama'; -- change this line to: film_genre='Comedy'; if you want comedy

-- 1. f) Produce pairs of actors with blue eyes in alphabetical pairs
SELECT p1.last_name, p1.eye_colour AS actor_one_eye_colour, p2.last_name, p2.eye_colour AS actor_one_eye_colour
FROM "Person" p1, "Person" p2, "Actor" a1, "Actor" a2
WHERE p1.country_code_national_id = a1.country_code_national_id AND
	  p2.country_code_national_id = a2.country_code_national_id AND
	  p1.eye_colour = 'Blue' AND
	  p2.eye_colour = 'Blue' AND
	  p1.last_name < p2.last_name;


-- TASK TWO --
-- 1. a) Average age of actors
SELECT AVG(AGE(date_of_birth)) AS average_actor_age
FROM "Person", "Actor"
WHERE "Actor".country_code_national_id = "Person".country_code_national_id;

-- 1. b) Find number of unique countries in which actors from 'The Prestige' were born
SELECT COUNT(DISTINCT country) AS number_of_countries_of_actors, movie_title
FROM "Person", "Location", "Movie", "Actor", "Actors in Movies"
WHERE "Actors in Movies".actor_id = "Actor".actor_id AND
	  "Actors in Movies".movie_id = "Movie".movie_id AND
	  "Person".country_code_national_id = "Actor".country_code_national_id AND
	  "Person".address_id = "Location".address_id AND
	  "Movie".movie_title = 'The Prestige' -- remove this line if you want to see unique countries of birth of actors for every movie
GROUP BY movie_title; -- Want to show movie title in result

-- 1. c) Number of actors with brown eyes
SELECT COUNT(eye_colour) AS number_of_brown_eyes, eye_colour
FROM "Person", "Actor"
WHERE "Person".country_code_national_id = "Actor".country_code_national_id AND
	  eye_colour = 'Brown' -- change this line to  eye_colour = 'Green' for number of actors with greeen eyes
GROUP BY eye_colour;

-- 1. d) Number of movies with Andy Serkis as an actor
SELECT COUNT("Actor".actor_id) AS number_of_movies_with_actor, performing_name
FROM "Actor", "Movie", "Actors in Movies"
WHERE "Actors in Movies".actor_id = "Actor".actor_id AND
	  "Actors in Movies".movie_id = "Movie".movie_id AND
	  "Actor".performing_name = 'Andy Serkis'
GROUP BY performing_name;

-- 1. e) Min, Max, and Avg for each genre of movie
SELECT film_genre, COUNT(film_genre) AS film_genre_occurences, MIN(CAST(budget AS decimal)) AS minimum_budget, MAX(CAST(budget AS decimal)) AS maximum_budget, ROUND(AVG(CAST(budget AS numeric)),2) AS average_budget
FROM "Movie", "Genre and Rating", "Categorization of Movies"
WHERE "Categorization of Movies".movie_id = "Movie".movie_id AND
	  "Categorization of Movies".film_category_id = "Genre and Rating".film_category_id
GROUP BY film_genre

-- 1. f) Find ratings of movies with directors with brown eyes or born in London
SELECT ROUND(AVG(CAST(rating as numeric)),2) AS average_rating
FROM "Movie", "Director", "Directors of Movies", "Person", "Location"
WHERE "Directors of Movies".movie_id="Movie".movie_id AND
	  "Directors of Movies".director_id="Director".director_id AND
	  "Director".country_code_national_id = "Person".country_code_national_id AND
	  "Person".address_id = "Location".address_id
	  AND (eye_colour = 'Brown' OR city='London') -- change line to AND (eye_colour = 'Blue' OR city='Toronto') if you want blue eyes or born in Toronto

-- 1. g) Find all the movies that have actors born in at least two different countries
SELECT movie_title, COUNT(DISTINCT country) AS number_of_countries_of_actors
FROM "Person", "Location", "Movie", "Actor", "Actors in Movies"
WHERE "Actors in Movies".actor_id = "Actor".actor_id AND
	  "Actors in Movies".movie_id = "Movie".movie_id AND
	  "Person".country_code_national_id = "Actor".country_code_national_id AND
	  "Person".address_id = "Location".address_id
GROUP BY movie_title HAVING COUNT(DISTINCT country)>=2;

-- 1. h) Find all the actors who have appeared with Chrisitan Bale in a movie, and the number of times they have
SELECT performing_name, COUNT(performing_name) as number_of_appearances_alongside_christian_bale -- counts number of times they've appeared with Christian Bale
FROM "Actor", "Movie", "Actors in Movies"
WHERE "Actor".actor_id = "Actors in Movies".actor_id AND
	  "Movie".movie_id = "Actors in Movies".movie_id AND
	  "Actor".actor_id <> 1 AND -- finds all the actors who are in any Christian Bale movie, other than Christan Bale himself
	  "Actors in Movies".movie_id IN (SELECT "Actors in Movies".movie_id
											  					  FROM "Actor", "Movie", "Actors in Movies"
											  					  WHERE "Actor".actor_id = "Actors in Movies".actor_id AND
								  								  "Movie".movie_id = "Actors in Movies".movie_id AND
											  					  "Actor".actor_id = 1) -- subquery finds movies that star Christian Bale
GROUP BY performing_name;
