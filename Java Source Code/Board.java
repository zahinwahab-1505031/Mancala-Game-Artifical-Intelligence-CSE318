/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala;

import java.util.HashMap;

/**
 *
 * @author Zahin
 */
public class Board {
    int NUM_OF_ROWS; // 2
    int NUM_OF_COLUMNS; //8
    public HashMap<Position, Hole> map;
    public Board(){
        this.map = new HashMap<Position,Hole>();
        this.NUM_OF_ROWS = 2;
        this.NUM_OF_COLUMNS = Main.NUM_OF_MANCALA + Main.NUM_OF_HOLES;
        this.initializeBoard();
        
    }
    public void printBoard(){
         for(int i = 0; i < NUM_OF_ROWS ; i++) {
            for (int j = 0; j < NUM_OF_COLUMNS; j++) {
                int stones = map.get(new Position(i,j)).noOfStones;
               if(map.get(new Position(i,j)).isValid == true) System.out.print(stones+" ");
               else System.out.print("- ");
            }
             System.out.println("");
        }
    }
    public void initializeBoard(){
        for(int i = 0; i < NUM_OF_ROWS ; i++) {
            for (int j = 1; j < NUM_OF_COLUMNS-1; j++) {
                Hole temp =  new Hole(Main.NUM_OF_STONES, i, j, false, true);
                map.put(new Position(i,j), temp);
            }
        }
        //player0 er mancala 0,0
        //player1 er mancala 1,7
        Hole mancala1 = new Hole(0,0,0,true,true);
        map.put(new Position(0,0), mancala1);
        Hole mancala2 = new Hole (0,1,0,true,false); //imaginary mancala for convenience
        map.put(new Position(1,0), mancala2);
        Hole mancala3 = new Hole (0,0,NUM_OF_COLUMNS-1,true,false);
        map.put(new Position(0,NUM_OF_COLUMNS-1), mancala3);//imaginary mancala
        Hole mancala4 = new Hole (0,1,NUM_OF_COLUMNS-1,true,true); 
        map.put(new Position(1,NUM_OF_COLUMNS-1), mancala4);
    }
    
    public int getWinner(){
        if(map.get(new Position(0,0)).noOfStones>map.get(new Position(1,NUM_OF_COLUMNS-1)).noOfStones)
            return 0;
        else return 1;
    }
    public Board myclone(){
       Board temp = new Board();
       temp.map = new HashMap<Position,Hole>();
       for(Position pos:this.map.keySet()){
            temp.map.put(pos.myclone(),this.map.get(pos).myclone());
        }
       return temp;
    }
    public int isThisSideEmpty(int myrow,int opponentsrow){
          boolean flag = true;
        for(int j=1;j<NUM_OF_COLUMNS-1;j++){
            if(map.get(new Position(opponentsrow,j)).noOfStones!=0){
                flag = false;
                break;
                       
            }
            
            
        }
        int stones = 0;
        if(flag==true){
            for(int j=1;j<NUM_OF_COLUMNS-1;j++){
            stones = map.get(new Position(myrow,j)).noOfStones;
            }
            return stones;
        }
        return stones;
    }
    public boolean isAnySideEmpty() {
        
        //iterating 0th ROW
        boolean flag = true;
        for(int j=1;j<NUM_OF_COLUMNS-1;j++){
            if(map.get(new Position(0,j)).noOfStones!=0){
                flag = false;
                break;
                       
            }
            if(flag==false) break;
            
        }
        if(flag==true){
            for(int j=1;j<NUM_OF_COLUMNS-1;j++){
            int stones = map.get(new Position(1,j)).noOfStones;
             map.get(new Position(1,j)).removeStone();
             map.get(new Position(1,NUM_OF_COLUMNS-1)).addStone(stones);
            }
            return flag;
        }
        //iterating 1st ROW
        flag=true;
        for(int j=1;j<NUM_OF_COLUMNS-1;j++){
            if(map.get(new Position(1,j)).noOfStones!=0){
                flag = false;
                return flag;
                       
            }
           
        }
         for(int j=1;j<NUM_OF_COLUMNS-1;j++){
            int stones = map.get(new Position(0,j)).noOfStones;
             map.get(new Position(0,j)).removeStone();
             map.get(new Position(0,0)).addStone(stones);
            }
        return flag;
        
       
        
    }
    
    
    
}
