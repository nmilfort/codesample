/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.guessnumber.view;

/**
 *
 * @author leemiukuen
 */
public interface UserIO {
    
    void print(String msg);
    
    String readString(String msg);
    
    int readInt(String msg);
    
    int readInt(String msg, int min, int max);
    
    double readDouble(String msg);
    
    double readDouble(String msg, double min, double max);
    
    float readFloat(String msg);
    
    float readFloat(String msg, float min, float max);
    
    long readLong(String msg);
    
    long readLong(String msg, long min, long max);
}