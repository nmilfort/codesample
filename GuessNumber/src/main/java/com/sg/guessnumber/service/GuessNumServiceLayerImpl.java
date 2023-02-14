/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessnumber.service;

import com.sg.guessnumber.dao.DaoException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import com.sg.guessnumber.dao.GameDao;
import com.sg.guessnumber.dao.RoundsDao;
import com.sg.guessnumber.dto.Game;
import com.sg.guessnumber.dto.Rounds;
import org.springframework.stereotype.Service;

/**
 *
 * @author leemiukuen
 */
@Service
public class GuessNumServiceLayerImpl implements GuessNumServiceLayer{
    
    GameDao gameDao;
    RoundsDao roundsDao;

    @Autowired
    public GuessNumServiceLayerImpl(GameDao gameDao, RoundsDao roundsDao) {
        this.gameDao = gameDao;
        this.roundsDao = roundsDao;
    }
    
    @Override
    public Game generateAnswer() {
        // generate a 4 non-repeated numbers answer in an arrayList
        ArrayList<Integer> ans = new ArrayList<>(4);
        Random ran = new Random();
        
            while (ans.size() < 4) {
                int num = ran.nextInt(10);
                if (!ans.contains(num)) {
                    ans.add(num);
                }
            }
        //POST answer to game database
        Game newGame = new Game();
            newGame.setAnswer(ans);
            newGame.setStatus(false);
        gameDao.addGame(newGame);
        
        return newGame;
    }
    
    @Override
    public void guessValidate(String guess) throws InputValidationException {

        List<String> arrGuess = new ArrayList<>(Arrays.asList(guess.split(""))); //[1, 2, 3, 4]
        String checkNum = guess.replaceAll("[0-9]", ""); // should be nothing left if string contains only numbers
        // check if string is a 4-digit numbers
            if (guess.length() != 4 || checkNum.length() > 0) {
                throw new InputValidationException (
                    "Error: Only accept 4 digits number");
            }

        // check duplicated number , using set because set cannot have duplicate elements
        Set <String> checkSet = new HashSet<>();
        for (String g : arrGuess) { 
            if (checkSet.add(g) == false) {
                throw new InputValidationException (
                    "Error: No repeated");
            }
        }
    }

    @Override
    public List<Integer> gameLogic(String guess, Game game) {
        ArrayList ans = game.getAnswer();
        List<String> arrGuess = new ArrayList<>(Arrays.asList(guess.split(""))); //[1, 2, 3, 4]
        int eMatch = 0;
        int totalMatch = 0;
        
        // count exact matches
        for (int i = 0; i < ans.size(); i++ ) {
            String ansGet = ans.get(i).toString();
            String guessGet = arrGuess.get(i);
            if (ansGet.equals(guessGet)) {
                eMatch += 1;
            } else {
                
            } // count total matches
            if (arrGuess.contains(ansGet)) {
                totalMatch += 1;
            } else {
                
            }
        }
        int pMatch = totalMatch - eMatch;
        List<Integer> matchNum = new ArrayList<> ();
        matchNum.add(eMatch);
        matchNum.add(pMatch);
        
        return matchNum;
    }

    @Override
    public List<Game> getGames() throws DaoException, NoDataFoundException {
        return gameDao.getAllGames();
    }

    @Override
    public Game getGame(int gameId) throws DaoException, NoDataFoundException {
        try {
            return gameDao.getGameById(gameId);
        } catch (NullPointerException e) {
            throw new NoDataFoundException("No order found for this ID.");
        }    }

    @Override
    public List<Rounds> getRounds(int gameId) throws DaoException, NoDataFoundException {
        try {
            return roundsDao.getRoundsById(gameId);
        } catch (NullPointerException e) {
            throw new NoDataFoundException("No order found for this ID.");
        }    }
}
