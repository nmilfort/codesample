/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.guessnumber.service;

import com.sg.guessnumber.dao.DaoException;
import com.sg.guessnumber.dto.Game;
import com.sg.guessnumber.dto.Rounds;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author leemiukuen
 */
@Service
public interface GuessNumServiceLayer {
    
    public Game generateAnswer();
    
    public void guessValidate(String guess) throws InputValidationException;
    
    public List<Integer> gameLogic(String guess, Game game);
    
    public List<Game> getGames() throws DaoException, NoDataFoundException;
    
    public Game getGame(int gameId) throws DaoException, NoDataFoundException;
    
    public List<Rounds> getRounds(int gameId) throws DaoException, NoDataFoundException;
}
