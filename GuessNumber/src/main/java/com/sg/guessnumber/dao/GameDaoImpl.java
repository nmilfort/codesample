/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessnumber.dao;

import com.sg.guessnumber.dto.Game;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
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
public class GameDaoImpl implements GameDao {

//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public GuessNumDaoImpl(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
    
    @Autowired
    JdbcTemplate jdbc;
//    
//    private static DataSource getDataSource() throws SQLException {
//        MysqlDataSource ds = new MysqlDataSource();
//        ds.setServerName("localhost");
//        ds.setDatabaseName("GameDB");
//        ds.setUser("root");
//        ds.setPassword("rootroot");
//        ds.setServerTimezone("America/Chicago");
//        ds.setUseSSL(false);
//        ds.setAllowPublicKeyRetrieval(true);
//
//        return ds;
//    }

    @Override
    public Game getGameById(int gameId) {
        try {
            final String GET_GAME_BY_ID = "SELECT * FROM game WHERE id = ?";
            return jdbc.queryForObject(GET_GAME_BY_ID, new GameMapper(), gameId);
        } catch(DataAccessException ex) {
            return null;
        }     
    }

    @Override
    public List<Game> getAllGames() {
        final String GET_ALL_GAMES = "SELECT * FROM game";
        return jdbc.query(GET_ALL_GAMES, new GameMapper());    
    }

    @Override
    @Transactional
    public Game addGame(Game Game) {
        final String INSERT_GAME = "INSERT INTO game(ans, finished) " +
                "VALUES(?,?)";
        // because I set the ans as ArrayList so now have to do sth to turn it into 4 digit string to the database
        StringBuilder sb = new StringBuilder();
        ArrayList ans = Game.getAnswer();
            for (Object s : ans) {
                sb.append(s);
                sb.append(" ");
        }
        String str = sb.toString().replaceAll(" ", "");
        jdbc.update(INSERT_GAME,
                str,
                Game.isStatus());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        Game.setGameId(newId);

        return Game;
    }

    @Override
    public void updateGame(Game Game) {
        final String UPDATE_GAME = "UPDATE game SET finished = ?, " +
                " WHERE id = ?";
        jdbc.update(UPDATE_GAME,
                Game.isStatus(),
                Game.getGameId());
    }
    
    public static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            //some steps to convert it as ArrayList
            String strAns = rs.getString("ans");
            
            ArrayList arrListAns = new ArrayList<>();
            arrListAns.add(strAns.charAt(0));
            arrListAns.add(strAns.charAt(1));
            arrListAns.add(strAns.charAt(2));
            arrListAns.add(strAns.charAt(3));
            
            // setting up a new game
            game.setGameId(rs.getInt("id"));
            game.setAnswer(arrListAns);
            game.setStatus(rs.getBoolean("finished"));

            return game;
        }
    }
    
}
