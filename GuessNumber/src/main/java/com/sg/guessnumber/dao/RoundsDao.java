/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.guessnumber.dao;

import com.sg.guessnumber.dto.Rounds;
import java.util.List;

/**
 *
 * @author leemiukuen
 */
public interface RoundsDao {
    
    List getRoundsById(int RoundId);
    Rounds addRounds(Rounds Rounds);
    
}
