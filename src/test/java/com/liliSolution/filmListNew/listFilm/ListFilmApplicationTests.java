package com.liliSolution.filmListNew.listFilm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class ListFilmApplicationTests {

	@Autowired
	private DataSource dataSource;

	//Conexão com o banco de dados;
	@Test
	void testConnection() throws SQLException {
		try (var connection = dataSource.getConnection()){
			System.out.println("Conectou essa budega *-*");
		}
	}
}
