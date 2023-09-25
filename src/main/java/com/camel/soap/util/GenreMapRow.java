package com.camel.soap.util;

import org.example.testwsdlfile.GenreType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapRow implements RowMapper<GenreType> {
    @Override
    public GenreType mapRow(ResultSet rs, int rowNum) throws SQLException {
        GenreType genreType = new GenreType();

        genreType.setId(rs.getString("id"));
        genreType.setDescription(rs.getString("description"));
        genreType.setName(rs.getString("name"));
        return genreType;
    }
}
