/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala;

import java.util.Scanner;

/**
 *
 * @author Zahin
 */
public class Main {
    public static int NUM_OF_HOLES = 6; //on each side
    public static int NUM_OF_MANCALA = 2;
    public static int NUM_OF_STONES = 4;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
       // System.out.println("1.Player vs Bot 2.Bot vs Bot");
        int choice =2; //Integer.parseInt(sc.nextLine());
        Game game = new Game(choice,100);
        game.Go();
    }
    
}
