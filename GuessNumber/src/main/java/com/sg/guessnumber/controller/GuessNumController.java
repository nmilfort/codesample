/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessnumber.controller;

import com.sg.guessnumber.dao.DaoException;
import com.sg.guessnumber.dao.GameDao;
import com.sg.guessnumber.dao.RoundsDao;
import com.sg.guessnumber.dto.Game;
import com.sg.guessnumber.dto.Rounds;
import com.sg.guessnumber.service.GuessNumServiceLayer;
import com.sg.guessnumber.service.InputValidationException;
import com.sg.guessnumber.service.NoDataFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author leemiukuen
 */
@RestController
@RequestMapping("")
public class GuessNumController {
    GuessNumServiceLayer service;
    GameDao gameDao;
    RoundsDao roundsDao;
    
    @Autowired
    public GuessNumController(GuessNumServiceLayer service, GameDao gameDao, RoundsDao roundsDao) {
        this.service = service;
        this.gameDao = gameDao;
        this.roundsDao = roundsDao;
    }

    @CrossOrigin
    @PostMapping("/begin")
    public ResponseEntity<Game> generateAnswer(){
        Game newGame = service.generateAnswer();
        if (newGame == null) {
            return ResponseEntity.notFound().build();
        } else {
            return new ResponseEntity<>(newGame, HttpStatus.CREATED);
        }
    }
    
    @CrossOrigin
    @PostMapping("/guess")
    private ResponseEntity guessGame(@RequestBody int gameId, String guess) throws InputValidationException, DaoException, NoDataFoundException {
        List<Integer> epMatch = new ArrayList<> (); // {exact macthes, partial matches}
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String msg = "";
        ResponseEntity response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        
        try {
            Game game = service.getGame(gameId);//Integer.parseInt(gameId));
            System.out.println("game");

            if (game == null) {
                msg = "IDs do not match";
                response = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(msg);
            } else {
                try {
                    if(epMatch.get(0) != 4) {
                        service.guessValidate(guess);
                        epMatch = service.gameLogic(guess, game);

                            // POST each guess into database
                            Rounds eachRound = new Rounds();
                                eachRound.setResult("E: " + epMatch.get(0) + ", P: " + epMatch.get(1));
                                eachRound.setGuess(guess);
                                eachRound.setDate(timestamp);
                            roundsDao.addRounds(eachRound);

                        msg = "\"E: \" + epMatch.get(0) + \", P: \" + epMatch.get(1)";
                        response = ResponseEntity.status(HttpStatus.CREATED).body(msg);
                    } else {
                        //update game status
                        Game finishedGame = new Game();
                            finishedGame.setAnswer(game.getAnswer());
                            finishedGame.setGameId(game.getGameId());
                            finishedGame.setStatus(true);
                        gameDao.updateGame(finishedGame);
                        msg = "You win!";
                        response = ResponseEntity.status(HttpStatus.CREATED).body(msg);
                    }
                } catch (InputValidationException e) {
                    response = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e);
                }
            }
        } catch(NumberFormatException e){
            response = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e);
        }
        return response;
    }
    
    @CrossOrigin
    @GetMapping("/games")
    public List<Game> getGames()throws
            DaoException,
            NoDataFoundException {
            List<Game> games = service.getGames();
            return games;
    }
    
    @CrossOrigin
    @GetMapping("/game/{id}")
    public ResponseEntity<Game> getGame(@PathVariable int id) throws DaoException, NoDataFoundException {
//        try {
            Game game = service.getGame(id);
            if(game == null) {
                return new ResponseEntity(null, HttpStatus.NOT_FOUND);
            }
                return ResponseEntity.ok(game);
//        } catch (DaoException e) {
//            errorMessage(e.getMessage());
//        }
    }
    
    @CrossOrigin
    @GetMapping("/rounds/{id}")
    private ResponseEntity <List<Rounds>> getRounds(@PathVariable int id) throws DaoException, NoDataFoundException {
//        try {
            List<Rounds> rounds = service.getRounds(id);
            if(rounds == null) {
                return new ResponseEntity(null, HttpStatus.NOT_FOUND);
            }
                return ResponseEntity.ok(rounds);
//        } catch (NoDataFoundException e) {
//            errorMessage(e.getMessage());
//        }
    }
    
}
