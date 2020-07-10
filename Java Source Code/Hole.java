/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala;

/**
 *
 * @author Zahin
 */
public class Hole {
    public int noOfStones;
    public int row;
    public int col;
    public boolean isMancala;
    public boolean isValid;
    
    public Hole(int noOfStones, int row, int col, boolean isMancala,boolean isValid) {
        this.noOfStones = noOfStones;
        this.row = row;
        this.col = col;
        this.isMancala = isMancala;
        this.isValid = isValid;
        
    }
    public void addStone (){
        this.noOfStones+=1;
    } 
    public void addStone (int a){
        this.noOfStones+=a;
    }
    public void removeStone(){
        this.noOfStones = 0;
    }
    public Hole myclone(){
        return new Hole(this.noOfStones, this.row, this.col,this.isMancala,this.isValid);
        
    }
    public String toString(){
        return "("+row+","+col+")--"+noOfStones;
    }
    
           
}
