/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Zahin
 */
public class Player {
    public int whichRow;
    public boolean isAI;
    public List<Hole> playersBoard;
    public HashMap<Integer,Hole> map;
    public int playerID;
    public Board board;
    public boolean extraMoveEarned = false;
    public int num_extraMoveEarned = 0;
    public Scanner sc;
    public int indexOfOpponentMancala;
    public int indexOfMyMancala;
    public int []indexOfInvalidMancala;
    public int heuristic;
    public Player(boolean isAI, int playerID, Board board,int heuristic) {
        this.isAI = isAI;
        this.playerID = playerID;
        this.board = board;
        this.whichRow = playerID;
        this.heuristic = heuristic;
        this.sc = new Scanner(System.in);
        
        indexOfInvalidMancala = new int[2];
        
        playersBoard = new ArrayList<Hole>();
        map = new HashMap<Integer,Hole>();
        initializeAntiClockWiseBoard();
    }
    public Player myclone(Board board){
        Player temp = new Player(this.isAI,this.playerID,board,this.heuristic);
        return temp;
    }
    public void initializeAntiClockWiseBoard(){
         int counter = 0;
         int k=0;
        if(playerID==0) {

            for(int i = board.NUM_OF_COLUMNS - 1 ; i >= 0 ; i--) {
                Hole holeToAdd = board.map.get(new Position(0,i));
                 if(i==0) indexOfMyMancala = counter;
                if(holeToAdd.isValid==false) {
                    indexOfInvalidMancala[k] = counter;
                    k++;
                }
                playersBoard.add(holeToAdd);
                map.put(counter, holeToAdd);
                counter++;
            }

            for(int i = 0; i < board.NUM_OF_COLUMNS; i++) {
                Hole holeToAdd = board.map.get(new Position(1,i));
                
                if(holeToAdd.isValid==false) {
                    indexOfInvalidMancala[k] = counter;
                    k++;
                }
                if(i==board.NUM_OF_COLUMNS-1) indexOfOpponentMancala = counter;
                playersBoard.add(holeToAdd);
                map.put(counter, holeToAdd);
                counter++;
            }
        }
        else if(playerID==1) {
            counter = 0;
            for(int i = 0; i < board.NUM_OF_COLUMNS; i++) {
                Hole holeToAdd = board.map.get(new Position(1,i));
               
                if(holeToAdd.isValid==false) {
                    indexOfInvalidMancala[k] = counter;
                    k++;
                }
                if(i==board.NUM_OF_COLUMNS-1) indexOfMyMancala = counter;
                playersBoard.add(holeToAdd);
              
                map.put(counter, holeToAdd);
                counter++;
            }

            for(int i = board.NUM_OF_COLUMNS - 1 ; i >= 0 ; i--) {
                Hole holeToAdd = board.map.get(new Position(0,i));
                
                if(holeToAdd.isValid==false) {
                    indexOfInvalidMancala[k] = counter;
                    k++;
                }
                if(i==0) {
                    indexOfOpponentMancala = counter;
                }
                playersBoard.add(holeToAdd);
                map.put(counter, holeToAdd);
                counter++;
            }
        }
    }
    public void printPlayerBoard(){
        for(int idx:map.keySet()){
            System.out.println("<"+idx+ ","+ map.get(idx)+">");
        }
    }
    public int getScore(){
        if(playerID==0) return board.map.get(new Position(0,0)).noOfStones;
        else return board.map.get(new Position(1,board.NUM_OF_COLUMNS-1)).noOfStones;
    }
    public int FalseExecMove(int column) {
        Hole hole = board.map.get(new Position(whichRow,column));
        int stones = hole.noOfStones;
        int index = 0; 
        if(whichRow==1) index = column;
        else index = board.NUM_OF_COLUMNS -1 - column;
        int start = (index + 1)%(board.NUM_OF_COLUMNS*board.NUM_OF_ROWS);
        
        
        int i = start;
        int prev = i;
        int prev_stone = 0;
        while(stones!=0) {
            if(i!=indexOfOpponentMancala&&i!=indexOfInvalidMancala[0]&&i!=indexOfInvalidMancala[1])
            { 
                prev = i;
                prev_stone = map.get(i).noOfStones;
              //  map.get(i).addStone();
                stones--;
            }
            
            i = (i+1)%(board.NUM_OF_COLUMNS*board.NUM_OF_ROWS);
            
           
        }
           int stones_opposite = 0;
        if(prev!=indexOfMyMancala && prev_stone==0){
           
         stones_opposite = map.get(board.NUM_OF_COLUMNS*board.NUM_OF_ROWS-1-prev).noOfStones;
            
        
        }
        
        if(extraMoveEarned==true) num_extraMoveEarned++;
        return stones_opposite+1;
        
        
         
    }
    public void execMove(Player opponent,int column) {
        Hole hole = board.map.get(new Position(whichRow,column));
        int stones = hole.noOfStones;
        int index = 0; 
        if(whichRow==1) index = column;
        else index = board.NUM_OF_COLUMNS -1 - column;
        int start = (index + 1)%(board.NUM_OF_COLUMNS*board.NUM_OF_ROWS);
        
        hole.removeStone();
        
        int i = start;
        int prev = i;
        int prev_stone = 0;
        while(stones!=0) {
            if(i!=indexOfOpponentMancala&&i!=indexOfInvalidMancala[0]&&i!=indexOfInvalidMancala[1])
            { 
                prev = i;
                prev_stone = map.get(i).noOfStones;
                map.get(i).addStone();
                stones--;
            }
            
            i = (i+1)%(board.NUM_OF_COLUMNS*board.NUM_OF_ROWS);
            
           
        }
       
        if(prev==indexOfMyMancala)extraMoveEarned = true;
        else if(prev!=indexOfMyMancala && prev_stone==0){
            map.get(prev).removeStone();
            if(playerID==0) board.map.get(new Position(0,0)).addStone();
            else if(playerID==1) board.map.get(new Position(1,board.NUM_OF_COLUMNS-1)).addStone();
            int stones_opposite =  map.get(board.NUM_OF_COLUMNS*board.NUM_OF_ROWS-1-prev).noOfStones;
             map.get(board.NUM_OF_COLUMNS*board.NUM_OF_ROWS-1-prev).removeStone();
            if(playerID==0) board.map.get(new Position(0,0)).addStone(stones_opposite);
            else if(playerID==1) board.map.get(new Position(1,board.NUM_OF_COLUMNS-1)).addStone(stones_opposite);
            extraMoveEarned = true;
        
        }
        
        if(extraMoveEarned==true) num_extraMoveEarned++;
        
        
         
    }
     public void move(Player opponent){
//        System.out.println("========================B E F O R E=======================");
//        board.printBoard();
//        System.out.println("==========================================================");
//        
if(this.isAI==true) {
    int max = -99999999;
    long startTime = System.currentTimeMillis();
for(int depth=1;;depth++) {
        long nowTime = System.currentTimeMillis();
       if((nowTime - startTime) >20) break;
            Node node = new Node(null,this.board, this, opponent,0);
            node.getSuccessors(true);
            max = -99999999;
        for(Node n : node.children) {
                n.hval = AdversarialSearch.alphaBeta(n, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
                //System.out.println("hval"+n.hval);
                if(n.hval >= max) {
                    max = n.move;
                }
       }
    }
      // System.out.println("The choice taken by MiniMax was : " + max);
       if(max!=-99999999) execMove(opponent,max);
      
        }
        else {
         
            System.out.println("Input your move:");
            int col = Integer.parseInt(sc.nextLine());
        
            if(col>=1 && col<(board.NUM_OF_COLUMNS-1)) {
                if(board.map.get(new Position(whichRow,col)).noOfStones!=0) {
                    execMove(opponent,col);
                   
                }
            }
        }
//        System.out.println("========================A F T E R=======================");
//        board.printBoard();
//        System.out.println("==========================================================");
//    
     }
    
    
    
    
    
    
    
    
}
