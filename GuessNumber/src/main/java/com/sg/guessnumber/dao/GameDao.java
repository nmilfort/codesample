/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.guessnumber.dao;

import com.sg.guessnumber.dto.Game;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author leemiukuen
 */
public interface GameDao {
    
    Game getGameById(int gameId);
    List<Game> getAllGames();
    Game addGame(Game Game);
    void updateGame(Game Game); //update the status of game processing or finished
    
}
