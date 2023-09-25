package com.camel.soap.service;

import org.example.testwsdlfile.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void createData(Game object){
        SqlParameterSource namedQuery = new MapSqlParameterSource()
                .addValue("id", UUID.randomUUID())
                .addValue("name", object.getName())
                .addValue("score", object.getScore())
                .addValue("description", object.getDescription())
                .addValue("genre_id", object.getGenre().getId());

        String sql = "INSERT INTO GAME(id,name,score,description,genre_id) values (:id,:name,:score,:description,:genre_id)";

        namedParameterJdbcTemplate.update(sql,namedQuery);
    }
}
