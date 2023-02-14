/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessnumber.dto;

import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author leemiukuen
 */
public class Rounds {
    private int roundId;
    private String guess;
    private String result;
    private Timestamp date;

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.roundId;
        hash = 79 * hash + Objects.hashCode(this.guess);
        hash = 79 * hash + Objects.hashCode(this.result);
        hash = 79 * hash + Objects.hashCode(this.date);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rounds other = (Rounds) obj;
        if (this.roundId != other.roundId) {
            return false;
        }
        if (!Objects.equals(this.guess, other.guess)) {
            return false;
        }
        if (!Objects.equals(this.result, other.result)) {
            return false;
        }
        return Objects.equals(this.date, other.date);
    }

}
