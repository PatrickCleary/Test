/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

/**
 *
 * @author Ryan1
 */

import java.awt.*;

import javax.swing.*;

public class TicTacToeMain{  
    public static void main(String[] args){
        JFrame frame = new JFrame("TicTacToe");   
        Container c = frame.getContentPane();   
        TicTacToe p = new TicTacToe();   
        c.add(p);   
        frame.pack();   
        frame.add(p);   
        frame.setVisible(true);   
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    }
}

    

