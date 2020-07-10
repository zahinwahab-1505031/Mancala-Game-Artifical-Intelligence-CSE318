/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala;

import com.sun.corba.se.impl.util.RepositoryId;
import java.util.Random;

/**
 *
 * @author Zahin
 */
public class Game {
    Board board;
    int choice;
    int rounds;

    public Game(int choice,int rounds) {
        this.choice = choice;
        this.board = new Board();
        this.rounds = rounds;
    }
    public void Go(){
        if(choice==1) PlayHumanvsAI();
        else PlayAIvsAI();
    }
    public void PlayHumanvsAI(){
        Random rand = new Random();
        //int n = rand.nextInt(1);
        int n = 1;
        Player human=null;
        Player AI = null;
        boolean player1_turn = false;
        if(n==1) {
             human = new Player(false,1,board,0);
             AI = new Player(true,0,board,2);
             player1_turn = true;
        }
        else if(n==0) {
             human = new Player(false,0,board,0);
             AI = new Player(true,1,board,2);
        }
        board.printBoard();
        while(board.isAnySideEmpty()!=true){
            
            if(player1_turn==true){
                System.out.println("Your turn: ");
                human.move(AI);
               
                if(human.extraMoveEarned==true) {
                    human.extraMoveEarned=false;
                }
                else player1_turn = false;
            }
            else {
                System.out.println("Bot's turn: ");
                AI.move(human);
                 if(AI.extraMoveEarned==true) {
                    AI.extraMoveEarned=false;
                }
                 else player1_turn = true;
            }
        }
        board.printBoard();
        int score1 = human.getScore();
        int score2 = AI.getScore();
        if(score1>score2) System.out.println("YOU WIN");
        else if(score2>score1) System.out.println("BOT WIN");
        else System.out.println("DRAW");
        
      
    }
    public void PlayAIvsAI(){
        int Bot1_wins=0;
        int Bot2_wins=0;
        for(int i=0;i<10;i++){
            System.out.println("Round no. "+i);
            board = new Board();
        Random rand = new Random();
        int n = rand.nextInt(100);
            //System.out.println(n);
        Player AI1=null,AI2=null;  
        boolean player1_turn = false;
        if(true) {
            System.out.println("Player1 is first");
            player1_turn = true;
             AI1 = new Player(true,1,board,1);
             AI2 = new Player(true,0,board,4);
             
        }
        else  {
            System.out.println("Player2 is first");
            player1_turn = false;
             AI1 = new Player(true,0,board,1);
             AI2 = new Player(true,1,board,4);
             
        }
      /// board.printBoard();
        while(board.isAnySideEmpty()!=true){
            
            if(player1_turn==true){
               // System.out.println("Bot #1's turn: ");
                AI1.move(AI1);
                if(AI1.extraMoveEarned==true) {
                    AI1.extraMoveEarned=false;
                }
                else player1_turn = false;
            }
            else {
              //  System.out.println("Bot #2's turn: ");
                AI2.move(AI2);
                if(AI2.extraMoveEarned==true) {
                    AI2.extraMoveEarned=false;
                }
                else player1_turn = true;
            }
        }
      //  board.printBoard();
        int score1 = AI1.getScore();
        int score2 = AI2.getScore();
        if(score1>score2) {
            Bot1_wins++;
            System.out.println("BOT 1 WIN");
        }
        else if(score2>score1) {
            Bot2_wins++;
            System.out.println("BOT 2 WIN");
        }
        else System.out.println("DRAW");
        }
        System.out.println("FINAL RESULTS: BOT1: "+Bot1_wins+" BOT2: "+Bot2_wins);
        
        if(Bot1_wins>Bot2_wins) System.out.println("BOT 1 WINS MORE");
        else if(Bot1_wins < Bot2_wins) System.out.println("BOT 2 WINS MORE");
    }
    
    
   
}
