package com.sg.guessnumber.view;

import java.util.Scanner;
import org.springframework.stereotype.Component;

/**
 *
 * @author leemiukuen
 */

@Component
public class UserIOConsoleImpl implements UserIO
{
    
    private final Scanner console = new Scanner(System.in);
    
    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public String readString(String msg) {
        this.print(msg);
        return console.nextLine();
    }

    @Override
    public int readInt(String msg) {  
        while(true) {
            try {
                return Integer.parseInt(this.readString(msg));
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
    }

    @Override
    public int readInt(String msg, int min, int max) {
        int returnValue;
        
        do{
            returnValue = this.readInt(msg);
        }while(returnValue < min || returnValue > max);      
        return returnValue;
    }

    @Override
    public double readDouble(String msg) {
        while(true){
            try {
                return Double.parseDouble(this.readString(msg));
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
    }

    @Override
    public double readDouble(String msg, double min, double max) {
        float returnValue;
        
        do{
            returnValue = readFloat(msg);
        }while(returnValue < min || returnValue > max);
        
        return returnValue;
    }

    @Override
    public float readFloat(String msg) {
        while(true){
            try {
                return Float.parseFloat(this.readString(msg));
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
    }

    @Override
    public float readFloat(String msg, float min, float max) {
        float returnValue;
        
        do{
            returnValue = this.readFloat(msg);
        }while(returnValue < min || returnValue > max);
        return returnValue;
    }

    @Override
    public long readLong(String msg) {
        while(true){
            try {
                return Long.parseLong(this.readString(msg));
            } catch (NumberFormatException e) {
                this.print("Inputn error. Please try again.");
            }
        }
    }

    @Override
    public long readLong(String msg, long min, long max) {
        long returnValue;
        
        do{
            returnValue = this.readLong(msg);
        }while(returnValue < min || returnValue > max);
        return returnValue;
    }

}
