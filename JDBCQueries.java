/*
Mir Afgan Talpur
*/

import java.math.BigDecimal;
import java.sql.*;

public class JDBCQueries {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Database Systems and Concepts", "postgres", "admin")) {
            System.out.println("Lab 7, 8: JDBC!");
            System.out.println("Connected to PostgreSQL database!");

            // Part 1: Insertions, No Variables
            insertNoVariables(connection);

            // Part 2: Insertions, With Variables
            insertWithVariables(connection);


            // Queries with NO Variables
            // 1. d) All the directors whose surname start with letter “A” and “D”
            queryOneNoVariables(connection);

            // 1. b) Find number of unique countries in which actors from 'The Prestige' were born
            queryTwoNoVariables(connection);

            // 1. c) Number of actors with brown eyes
            queryThreeNoVariables(connection);

            // 1. d) Number of movies with Andy Serkis as an actor
            queryFourNoVariables(connection);


            // Queries with Variables
            // 1. d) All the directors whose surname start with letter “A” and “D”
            queryOneWithVariables(connection);

            // 1. b) Find number of unique countries in which actors from 'The Prestige' were born
            queryTwoWithVariables(connection);

            // 1. c) Number of actors with brown eyes
            queryThreeWithVariables(connection);

            // 1. d) Number of movies with Andy Serkis as an actor
            queryFourWithVariables(connection);

        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }

    public static void insertNoVariables(Connection connection){
        try {
            // Part 1: Insertions, No Variables
            Statement statement = connection.createStatement();

            String[] inserts = new String[19];
            inserts[0] = "INSERT INTO Public.\"Movie\" (movie_id,movie_title,release_date,rating,budget,gross) VALUES (15,'The Lion King','2019-07-19',0.0,200000000.00,NULL)";
            inserts[1] = "INSERT INTO Public.\"Location\" (address_id, city, state, country) VALUES (42,'Edwards Air Force Base', 'California', 'United States')";
            inserts[2] = "INSERT INTO Public.\"Person\" (country_code_national_id, address_id, first_name, last_name, date_of_birth, eye_colour) VALUES ('US-18',42,'Donald','Glover','1983-09-25','Brown')";
            inserts[3] = "INSERT INTO Public.\"Actor\" (actor_id, country_code_national_id, address_id, performing_name) VALUES (15,'US-18',42,'Donald Glover')";
            inserts[4] = "INSERT INTO Public.\"Director\" (director_id,country_code_national_id, address_id, number_of_films_directed) VALUES (12,'US-18',42,1)";
            inserts[5] = "INSERT INTO Public.\"Producer\" (producer_id,country_code_national_id, address_id, number_of_films_produced) VALUES (12,'US-18',42,1)";
            inserts[6] = "INSERT INTO Public.\"Writer\" (writer_id,country_code_national_id,address_id,number_of_films_written) VALUES (11,'US-18',42,1)";
            inserts[7] = "INSERT INTO Public.\"Genre and Rating\" (film_category_id,film_genre,age_rating) VALUES (33,'Animated','All Ages')";
            inserts[8] = "INSERT INTO Public.\"Directors of Movies\" (movie_id,director_id,country_code_national_id,address_id) VALUES (15,12,'US-18',42)";
            inserts[9] = "INSERT INTO Public.\"Actors in Movies\" (actor_id,country_code_national_id,address_id,movie_id) VALUES (15,'US-18',42,15)";
            inserts[10] = "INSERT INTO Public.\"Producers of Movies\" (producer_id,country_code_national_id,address_id,movie_id) VALUES (12,'US-18',42,15)";
            inserts[11] = "INSERT INTO Public.\"Writers of Movies\" (writer_id,country_code_national_id,address_id,movie_id) VALUES (11,'US-18',42,15)";
            inserts[12] = "INSERT INTO public.\"Categorization of Movies\"(movie_id,film_category_id) VALUES (15,33)";
            inserts[13] = "INSERT INTO public.\"Locations of Movies\"(movie_id,address_id) VALUES (15,26)";
            inserts[14] = "INSERT INTO public.\"Quotes\"(movie_id,quote_id,quote_description) VALUES (15,15,'I just can''t wait to be king!')";
            inserts[15] = "INSERT INTO public.\"Award\"(award_id, movie_id, award_organization, award_name, award_recpient, year_received) VALUES (12,15,'Academy of Motion Picture Arts and Sciences','Best Picture','Donald Glover',2019)";
            inserts[16] = "INSERT INTO public.\"Theatre\"(theatre_id,address_id,size,name,phone_number) VALUES (11,42,21,'Air Force Movies','416-666-6666')";
            inserts[17] = "INSERT INTO public.\"Showtime\"(showtime_id,movie_id,theatre_id,theatre_room,type_of_showtime,time_of_show) VALUES (11,15,11,4,'IMAX 3D','18:30:00')";
            inserts[18] = "INSERT INTO public.\"Ticket\"(ticket_id,showtime_id,movie_id,theatre_id,price,seat_location) VALUES (11,11,15,11,10.00,'M16')";

            for (int i = 0; i < 16; i++) {
                statement.executeUpdate(inserts[i]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertWithVariables(Connection connection) {
        try {
            // Part 2: Insertions, With Variables
            PreparedStatement prepInsertMovie = connection.prepareStatement("INSERT INTO Public.\"Movie\" (movie_id,movie_title,release_date,rating,budget,gross) VALUES(?,?,?,?,?,?)");
            PreparedStatement prepInsertLocation = connection.prepareStatement("INSERT INTO Public.\"Location\" (address_id, city, state, country) VALUES(?,?,?,?)");
            PreparedStatement prepInsertPerson = connection.prepareStatement("INSERT INTO Public.\"Person\" (country_code_national_id, address_id, first_name, last_name, date_of_birth, eye_colour) VALUES(?,?,?,?,?,?)");
            PreparedStatement prepInsertActor = connection.prepareStatement("INSERT INTO Public.\"Actor\" (actor_id, country_code_national_id, address_id, performing_name) VALUES(?,?,?,?)");
            PreparedStatement prepInsertDirector = connection.prepareStatement("INSERT INTO Public.\"Director\" (director_id,country_code_national_id, address_id, number_of_films_directed) VALUES(?,?,?,?)");
            PreparedStatement prepInsertProducer = connection.prepareStatement("INSERT INTO Public.\"Producer\" (producer_id,country_code_national_id, address_id, number_of_films_produced) VALUES(?,?,?,?)");
            PreparedStatement prepInsertWriter = connection.prepareStatement("INSERT INTO Public.\"Writer\" (writer_id,country_code_national_id, address_id,number_of_films_written) VALUES(?,?,?,?)");
            PreparedStatement prepInsertGenreAndRating = connection.prepareStatement("INSERT INTO Public.\"Genre and Rating\" (film_category_id,film_genre,age_rating) VALUES(?,?,?)");
            PreparedStatement prepInsertDirectorsOfMovies = connection.prepareStatement("INSERT INTO Public.\"Directors of Movies\" (movie_id,director_id,country_code_national_id,address_id) VALUES(?,?,?,?)");
            PreparedStatement prepInsertActorsInMovies = connection.prepareStatement("INSERT INTO  Public.\"Actors in Movies\" (actor_id,country_code_national_id,address_id,movie_id) VALUES(?,?,?,?)");
            PreparedStatement prepInsertProducersOfMovies = connection.prepareStatement("INSERT INTO Public.\"Producers of Movies\" (producer_id,country_code_national_id,address_id,movie_id) VALUES(?,?,?,?)");
            PreparedStatement prepInsertWritersOfMovies = connection.prepareStatement("INSERT INTO Public.\"Writers of Movies\" (writer_id,country_code_national_id,address_id,movie_id) VALUES(?,?,?,?)");
            PreparedStatement prepInsertCategorizationOfMovies = connection.prepareStatement("INSERT INTO public.\"Categorization of Movies\"(movie_id,film_category_id) VALUES(?,?)");
            PreparedStatement prepInsertLocationsOfMovies = connection.prepareStatement("INSERT INTO public.\"Locations of Movies\"(movie_id,address_id) VALUES(?,?)");
            PreparedStatement prepInsertQuotes = connection.prepareStatement("INSERT INTO public.\"Quotes\"(movie_id,quote_id,quote_description) VALUES(?,?,?)");
            PreparedStatement prepInsertAward = connection.prepareStatement("INSERT INTO public.\"Award\"(award_id, movie_id, award_organization, award_name, award_recpient, year_received) VALUES(?,?,?,?,?,?)");
            PreparedStatement prepInsertTheatre = connection.prepareStatement("INSERT INTO public.\"Theatre\"(theatre_id,address_id,size,name,phone_number) VALUES(?,?,?,?,?)");
            PreparedStatement prepInsertShowtime = connection.prepareStatement("INSERT INTO public.\"Showtime\"(showtime_id,movie_id,theatre_id,theatre_room,type_of_showtime,time_of_show) VALUES(?,?,?,?,?,?)");
            PreparedStatement prepInsertTicket = connection.prepareStatement("INSERT INTO public.\"Ticket\"(ticket_id,showtime_id,movie_id,theatre_id,price,seat_location) VALUES(?,?,?,?,?,?)");


            prepInsertMovie.setInt(1,16);
            prepInsertMovie.setString(2,"The Hobbit: Tue Battle of the Five Armies");
            prepInsertMovie.setDate(3, new java.sql.Date(2014-12-17));
            prepInsertMovie.setDouble(4,7.4);
            prepInsertMovie.setBigDecimal(5,new BigDecimal(250000000));
            prepInsertMovie.setBigDecimal(6,null);
            prepInsertMovie.executeUpdate();

            prepInsertLocation.setInt(1,43);
            prepInsertLocation.setString(2,"Alert");
            prepInsertLocation.setString(3,"Nunavut");
            prepInsertLocation.setString(4,"Canada");
            prepInsertLocation.executeUpdate();

            prepInsertPerson.setString(1,"CA-1");
            prepInsertPerson.setInt(2,43);
            prepInsertPerson.setString(3,"Afgan");
            prepInsertPerson.setString(4,"Talpur");
            prepInsertPerson.setDate(5, Date.valueOf("1990-01-01"));
            prepInsertPerson.setString(6,"Brown");
            prepInsertPerson.executeUpdate();

            prepInsertActor.setInt(1,16);
            prepInsertActor.setString(2,"CA-1");
            prepInsertActor.setInt(3,43);
            prepInsertActor.setString(4,"Gan");
            prepInsertActor.executeUpdate();

            prepInsertDirector.setInt(1,13);
            prepInsertDirector.setString(2,"CA-1");
            prepInsertDirector.setInt(3,43);
            prepInsertDirector.setInt(4,5);
            prepInsertDirector.executeUpdate();

            prepInsertProducer.setInt(1,13);
            prepInsertProducer.setString(2,"CA-1");
            prepInsertProducer.setInt(3,43);
            prepInsertProducer.setInt(4,5);
            prepInsertProducer.executeUpdate();

            prepInsertWriter.setInt(1,12);
            prepInsertWriter.setString(2,"CA-1");
            prepInsertWriter.setInt(3,43);
            prepInsertWriter.setInt(4,5);
            prepInsertWriter.executeUpdate();

            prepInsertGenreAndRating.setInt(1,34);
            prepInsertGenreAndRating.setString(2,"Animation");
            prepInsertGenreAndRating.setString(3,"PG");
            prepInsertGenreAndRating.executeUpdate();

            prepInsertDirectorsOfMovies.setInt(1,15);
            prepInsertDirectorsOfMovies.setInt(2,13);
            prepInsertDirectorsOfMovies.setString(3,"CA-1");
            prepInsertDirectorsOfMovies.setInt(4,43);
            prepInsertDirectorsOfMovies.executeUpdate();

            prepInsertActorsInMovies.setInt(1,16);
            prepInsertActorsInMovies.setString(2,"CA-1");
            prepInsertActorsInMovies.setInt(3,43);
            prepInsertActorsInMovies.setInt(4,15);
            prepInsertActorsInMovies.executeUpdate();

            prepInsertProducersOfMovies.setInt(1,13);
            prepInsertProducersOfMovies.setString(2,"CA-1");
            prepInsertProducersOfMovies.setInt(3,43);
            prepInsertProducersOfMovies.setInt(4,15);
            prepInsertProducersOfMovies.executeUpdate();

            prepInsertWritersOfMovies.setInt(1,12);
            prepInsertWritersOfMovies.setString(2,"CA-1");
            prepInsertWritersOfMovies.setInt(3,43);
            prepInsertWritersOfMovies.setInt(4,15);
            prepInsertWritersOfMovies.executeUpdate();

            prepInsertCategorizationOfMovies.setInt(1,15);
            prepInsertCategorizationOfMovies.setInt(2,34);
            prepInsertCategorizationOfMovies.executeUpdate();

            prepInsertLocationsOfMovies.setInt(1,15);
            prepInsertLocationsOfMovies.setInt(2,41);
            prepInsertLocationsOfMovies.executeUpdate();

            prepInsertQuotes.setInt(1,15);
            prepInsertQuotes.setInt(2,16);
            prepInsertQuotes.setString(3,"Hakuna Matata");
            prepInsertQuotes.executeUpdate();

            prepInsertAward.setInt(1,13);
            prepInsertAward.setInt(2,15);
            prepInsertAward.setString(3,"Academy of Motion Picture Arts and Sciences");
            prepInsertAward.setString(4,"Best Director");
            prepInsertAward.setString(5,"Afgan Talpur");
            prepInsertAward.setInt(6,2019);
            prepInsertAward.executeUpdate();

            prepInsertTheatre.setInt(1,12);
            prepInsertTheatre.setInt(2,43);
            prepInsertTheatre.setInt(3,33);
            prepInsertTheatre.setString(4,"Alert Cineplex");
            prepInsertTheatre.setString(5,"123-456-5432");
            prepInsertTheatre.executeUpdate();

            prepInsertShowtime.setInt(1,12);
            prepInsertShowtime.setInt(2,15);
            prepInsertShowtime.setInt(3,12);
            prepInsertShowtime.setInt(4,6);
            prepInsertShowtime.setString(5,"Regular 3D");
            prepInsertShowtime.setTime(6,Time.valueOf("20:00:00"));
            prepInsertShowtime.executeUpdate();

            prepInsertTicket.setInt(1,12);
            prepInsertTicket.setInt(2,12);
            prepInsertTicket.setInt(3,15);
            prepInsertTicket.setInt(4,12);
            prepInsertTicket.setBigDecimal(5,new BigDecimal(11.50));
            prepInsertTicket.setString(6,"N10");
            prepInsertTicket.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void queryOneNoVariables(Connection connection) {
        try {
            // 1. d) All the directors whose surname start with letter “A” and “D”
            Statement query1 = connection.createStatement();
            String query1a = "SELECT last_name,first_name\n" +
                    "FROM  \"Director\", \"Person\"\n" +
                    "WHERE \"Director\".country_code_national_id = \"Person\".country_code_national_id AND\n" +
                    "(last_name LIKE 'A%' OR last_name LIKE 'D%')";
            ResultSet resultSet1 = query1.executeQuery(query1a);
            System.out.println("\nQuery 1. a):");
            System.out.printf("%-45.45s %-45.45s%n","Last Name", "First Name");
            while (resultSet1.next()) {
                System.out.printf("%-45.45s  %-45.45s%n", resultSet1.getString("last_name"), resultSet1.getString("first_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void queryTwoNoVariables(Connection connection) {
        try {
            // 1. b) Find number of unique countries in which actors from 'The Prestige' were born
            Statement query2 = connection.createStatement();
            String query2a = "SELECT COUNT(DISTINCT country) AS number_of_countries_of_actors, movie_title\n" +
                    "FROM \"Person\", \"Location\", \"Movie\", \"Actor\", \"Actors in Movies\"\n" +
                    "WHERE \"Actors in Movies\".actor_id = \"Actor\".actor_id AND\n" +
                    "\"Actors in Movies\".movie_id = \"Movie\".movie_id AND\n" +
                    "\"Person\".country_code_national_id = \"Actor\".country_code_national_id AND\n" +
                    "\"Person\".address_id = \"Location\".address_id AND\n" +
                    "\"Movie\".movie_title = 'The Prestige' \n" +
                    "GROUP BY movie_title;";
            ResultSet resultSet2 = query2.executeQuery(query2a);
            System.out.println("\nQuery 2. a):");
            System.out.printf("%-45.45s %-45.45s%n","Number of Countries", "Movie Title");
            while (resultSet2.next()) {
                System.out.printf("%-45.45s %-45.45s%n", resultSet2.getString("number_of_countries_of_actors"), resultSet2.getString("movie_title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void queryThreeNoVariables(Connection connection) {
        try {
            // 1. c) Number of actors with brown eyes
            Statement query3 = connection.createStatement();
            String query3a = "SELECT COUNT(eye_colour) AS number_of_brown_eyes, eye_colour\n" +
                    "FROM \"Person\", \"Actor\"\n" +
                    "WHERE \"Person\".country_code_national_id = \"Actor\".country_code_national_id AND\n" +
                    "eye_colour = 'Brown'\n" +
                    "GROUP BY eye_colour;";
            ResultSet resultSet3 = query3.executeQuery(query3a);
            System.out.println("\nQuery 3. a):");
            System.out.printf("%-45.45s %-45.45s%n","Numver of Actors with Brown Eyes", "Eye Colour");
            while (resultSet3.next()) {
                System.out.printf("%-45.45s  %-45.45s%n", resultSet3.getString("number_of_brown_eyes"), resultSet3.getString("eye_colour"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void queryFourNoVariables(Connection connection) {
        try {
            // 1. d) Number of movies with Andy Serkis as an actor
            Statement query4 = connection.createStatement();
            String query4a = "SELECT COUNT(\"Actor\".actor_id) AS number_of_movies_with_actor, performing_name\n" +
                    "FROM \"Actor\", \"Movie\", \"Actors in Movies\"\n" +
                    "WHERE \"Actors in Movies\".actor_id = \"Actor\".actor_id AND\n" +
                    "\"Actors in Movies\".movie_id = \"Movie\".movie_id AND\n" +
                    "\"Actor\".performing_name = 'Andy Serkis'\n" +
                    "GROUP BY performing_name;";
            ResultSet resultSet4 = query4.executeQuery(query4a);
            System.out.println("\nQuery 4. a):");
            System.out.printf("%-45.45s %-45.45s%n","Number of Movies with Actor", "Actor Name");
            while (resultSet4.next()) {
                System.out.printf("%-45.45s  %-45.45s%n", resultSet4.getString("number_of_movies_with_actor"), resultSet4.getString("performing_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void queryOneWithVariables(Connection connection) {
        try {
            // 1. d) All the directors whose surname start with letter “A” and “D”
            String query1b = "SELECT last_name,first_name\n" +
                    "FROM  \"Director\", \"Person\"\n" +
                    "WHERE \"Director\".country_code_national_id = \"Person\".country_code_national_id AND\n" +
                    "(last_name LIKE ? OR last_name LIKE ?)";
            PreparedStatement query1bstat = connection.prepareStatement(query1b);
            query1bstat.setString(1,"A%");
            query1bstat.setString(2,"D%");
            ResultSet resultSet1b = query1bstat.executeQuery();
            System.out.println("\nQuery 1. b):");
            System.out.printf("%-45.45s %-45.45s%n","Last Name", "First Name");
            while (resultSet1b.next()) {
                System.out.printf("%-45.45s  %-45.45s%n", resultSet1b.getString("last_name"), resultSet1b.getString("first_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void queryTwoWithVariables(Connection connection) {
        try {
            // 1. b) Find number of unique countries in which actors from 'The Prestige' were born
            String query2b =
                    "SELECT COUNT(DISTINCT country) AS number_of_countries_of_actors, movie_title\n" +
                            "FROM \"Person\", \"Location\", \"Movie\", \"Actor\", \"Actors in Movies\"\n" +
                            "WHERE \"Actors in Movies\".actor_id = \"Actor\".actor_id AND\n" +
                            "\"Actors in Movies\".movie_id = \"Movie\".movie_id AND\n" +
                            "\"Person\".country_code_national_id = \"Actor\".country_code_national_id AND\n" +
                            "\"Person\".address_id = \"Location\".address_id AND\n" +
                            "\"Movie\".movie_title = ? \n" +
                            "GROUP BY movie_title;";
            PreparedStatement query2bstat = connection.prepareStatement(query2b);
            query2bstat.setString(1,"The Prestige");
            ResultSet resultSet2b = query2bstat.executeQuery();
            System.out.println("\nQuery 2. b):");
            System.out.printf("%-45.45s %-45.45s%n","Number of Countries", "Movie Title");
            while (resultSet2b.next()) {
                System.out.printf("%-45.45s %-45.45s%n", resultSet2b.getString("number_of_countries_of_actors"), resultSet2b.getString("movie_title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void queryThreeWithVariables(Connection connection) {
        try {
            // 1. c) Number of actors with brown eyes
            String query3b = "SELECT COUNT(eye_colour) AS number_of_brown_eyes, eye_colour\n" +
                    "FROM \"Person\", \"Actor\"\n" +
                    "WHERE \"Person\".country_code_national_id = \"Actor\".country_code_national_id AND\n" +
                    "eye_colour = ?\n" +
                    "GROUP BY eye_colour;";
            PreparedStatement query3stat = connection.prepareStatement(query3b);
            query3stat.setString(1,"Brown");
            ResultSet resultSet3b = query3stat.executeQuery();
            System.out.println("\nQuery 3. b):");
            System.out.printf("%-45.45s %-45.45s%n","Numver of Actors with Brown Eyes", "Eye Colour");
            while (resultSet3b.next()) {
                System.out.printf("%-45.45s  %-45.45s%n", resultSet3b.getString("number_of_brown_eyes"), resultSet3b.getString("eye_colour"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void queryFourWithVariables(Connection connection) {
        try {
            // 1. d) Number of movies with Andy Serkis as an actor
            String query4b = "SELECT COUNT(\"Actor\".actor_id) AS number_of_movies_with_actor, performing_name\n" +
                    "FROM \"Actor\", \"Movie\", \"Actors in Movies\"\n" +
                    "WHERE \"Actors in Movies\".actor_id = \"Actor\".actor_id AND\n" +
                    "\"Actors in Movies\".movie_id = \"Movie\".movie_id AND\n" +
                    "\"Actor\".performing_name = ?\n" +
                    "GROUP BY performing_name;";
            PreparedStatement query4bstat = connection.prepareStatement(query4b);
            query4bstat.setString(1, "Andy Serkis");
            ResultSet resultSet4b = query4bstat.executeQuery();
            System.out.println("\nQuery 4. b):");
            System.out.printf("%-45.45s %-45.45s%n","Number of Movies with Actor", "Actor Name");
            while (resultSet4b.next()) {
                System.out.printf("%-45.45s  %-45.45s%n", resultSet4b.getString("number_of_movies_with_actor"), resultSet4b.getString("performing_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
