/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessnumber.view;

import com.sg.guessnumber.dto.Game;
import com.sg.guessnumber.dto.Rounds;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leemiukuen
 */
public class GuessNumView {
    
    private UserIO io;
    static DateTimeFormatter isoFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    static DateTimeFormatter collapsedFormatter = DateTimeFormatter.ofPattern("MMddyyyy");
    
    public GuessNumView(UserIO io) {
        this.io = io;
    }
    
    public void displayMenu() {
        io.print("* * * * * * * * * * * * * * * * * ");
        io.print("* <<Guess the Number>>");
        io.print("* 1. Start Game");
        io.print("* 2. Get all Games");
        io.print("* 3. Get a Game");
        io.print("* 4. Get all Rounds");
        io.print("* 5. Quit");
    }
    
    public int getMenuChoice() {
        return io.readInt("Please select from the options above", 1, 5);
    }
    public void displayGameStart() {
        io.print("Game Start");
    }
    public String getGuess() {
        return io.readString("Please guess a number. Must be 4-digit number && every digit is different");
    }
    public void showAns(ArrayList ans) {
            io.print("Ans: "+ans.get(0)+ans.get(1)+ans.get(2)+ans.get(3));
    }
    public void displayEPMatch(List<Integer> epMatch) {
        if (epMatch.get(0) == 4) {
            io.print("You win!");
        } else {
            io.print("E: "+ epMatch.get(0) + ", P: " + epMatch.get(1));
        }
    }
    public String getGameId() {
        return io.readString("Please enter the game ID");
    }
    public void displayGames(List<Game> games) {
        for (Game game : games) {
            io.print("Game ID: " + game.getGameId());
            io.print("State: " + game.isStatus());
            if (game.isStatus()) {
                io.print("Ans: " + game.getAnswer());
            } else {
                io.print("Game is not finished, I cannot give you the ans");
            }
        }
        io.readString("Press enter to return to main menu.");
    }
    public void displayGame(Game game) {
        io.print("Game ID: " + game.getGameId());
        io.print("State: " + game.isStatus());
        if (game.isStatus()) {
            io.print("Ans: " + game.getAnswer());
        } else {
            io.print("Game is not finished, I cannot give you the ans");
        }
    }
    public void displayRounds(List<Rounds> rounds) {
        for (Rounds round : rounds) {
            io.print("Round ID: " + round.getRoundId());
            io.print("Guess: " + round.getGuess());
            io.print("Result: " + round.getResult());
            io.print("TimeStamp: " + round.getDate());
        }
        io.readString("Press enter to return to main menu.");
    }
    
    
    
    public void quitMessage() {
        io.print("Goodbye!");
    }
    
    public void displayErrorMessage(String message) {
        io.print("*ERROR: " + message);
    }
}
