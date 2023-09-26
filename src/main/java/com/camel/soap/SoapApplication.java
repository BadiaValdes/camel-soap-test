package com.camel.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class SoapApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(SoapApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		jdbcTemplate.execute("DROP TABLE IF EXISTS game");
		jdbcTemplate.execute("DROP TABLE IF EXISTS genre");

		System.out.println("HERE");

		jdbcTemplate.execute("CREATE TABLE genre (id varchar(255) PRIMARY KEY, name varchar(150) NOT NULL, description varchar(150))");
		jdbcTemplate.execute("CREATE TABLE " +
				"game (id varchar(255) PRIMARY KEY, " +
				"name varchar(150) NOT NULL, " +
				"score integer DEFAULT 1," +
				"description varchar(150), " +
				"genre_id varchar(255) NOT NULL, " +
				"FOREIGN KEY (genre_id) references  genre(id))");


		jdbcTemplate.execute("CREATE PROCEDURE changeName(idG varchar) language plpgsql as $$ " +
				"BEGIN " +
				"UPDATE genre SET name = 'azul' WHERE id = idG;" +
				"commit;" +
				"END;$$");

	}
}
