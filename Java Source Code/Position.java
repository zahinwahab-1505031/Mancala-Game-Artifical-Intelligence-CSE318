/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala;

import java.util.Arrays;

/**
 *
 * @author Zahin
 */
public class Position {
    int row;
    int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public String toString(){
        return "Row: " + row + " Column: "+ col;
    }
    public Position myclone(){
        return new Position(this.row,this.col);
    }
     @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + row;
        hash = 53 * hash + col;
        return hash;
    }
     public boolean equals(Object o){
        if(o==null) return false;
        else if(!(o instanceof Position)) return false;
        Position st = (Position) o;
        if(this.row == st.row && this.col==st.col) return true;
        return false;
      
    }
}
