package com.camel.soap.service;

import com.camel.soap.util.GenreMapRow;
import org.example.testwsdlfile.Game;
import org.example.testwsdlfile.GenreType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GenreService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void createData(GenreType object){
        SqlParameterSource namedQuery = new MapSqlParameterSource()
                .addValue("id", UUID.randomUUID())
                .addValue("name", object.getName())
                .addValue("description", object.getDescription());

        String sql = "INSERT INTO genre(id,name,description) values (:id,:name,:description)";

        System.out.println("Inside create data");
        namedParameterJdbcTemplate.update(sql,namedQuery);
    }

    public Iterable<GenreType> getAll(){
        String sql = "SELECT * FROM genre";
        return jdbcTemplate.queryForList(sql, GenreType.class);
    }

    public GenreType getOne(String id){
        System.out.println("id");
        System.out.println(id);
        String sql = "SELECT * FROM genre WHERE id = :id";
        SqlParameterSource namedQuery = new MapSqlParameterSource()
                .addValue("id", id);

        System.out.println("Before select");

        GenreType genreType = namedParameterJdbcTemplate.queryForObject(sql, namedQuery, new GenreMapRow());

        System.out.println("After Select");

        return genreType;
    }
}
