/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessnumber.dao;

import com.sg.guessnumber.dto.Rounds;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author leemiukuen
 */
@Repository
public class RoundDaoImpl implements RoundsDao {
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Rounds> getRoundsById(int RoundId) {
        try {
            final String GET_ROUNDS_BY_ID = "SELECT * FROM rounds WHERE id = ?";
            return jdbc.query(GET_ROUNDS_BY_ID, new RoundsMapper(), RoundId);
        } catch(DataAccessException ex) {
            return null;
        }    
    }

    @Override
    @Transactional
    public Rounds addRounds(Rounds Rounds) {
        final String INSERT_ROUNDS = "INSERT INTO rounds(guess, result, timeDate) " +
                "VALUES(?, ?, ?)";
        jdbc.update(INSERT_ROUNDS,
                Rounds.getGuess(),
                Rounds.getResult(),
                Rounds.getDate());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        Rounds.setRoundId(newId);

        return Rounds;
    }
    
    public static final class RoundsMapper implements RowMapper<Rounds> {

        @Override
        public Rounds mapRow(ResultSet rs, int index) throws SQLException {
            Rounds rounds = new Rounds();
            rounds.setRoundId(rs.getInt("id"));
            rounds.setGuess(rs.getString("guess"));
            rounds.setResult(rs.getString("result"));
            rounds.setDate(rs.getTimestamp("timeDate"));

            return rounds;
        }
    }
}
