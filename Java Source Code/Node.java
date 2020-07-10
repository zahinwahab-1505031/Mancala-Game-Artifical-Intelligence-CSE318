/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zahin
 */
public class Node {
    public Node parent;
    public List<Node> children;
    public Board board;
    public Player player;
    public Player opponent;
    public int move;
    public int hval;
    public Node(Node parent, Board board, Player player, Player opponent,int move) {
        this.parent = parent;
        this.board = board.myclone();
        this.player = player.myclone(this.board);
        this.opponent = opponent.myclone(this.board);
        this.move = move;
        this.children = new ArrayList<Node>();
    }
    public void getSuccessors(boolean isMaximizingPlayer) {
        ArrayList<Integer> choicesMovement = new ArrayList<>();
        if(isMaximizingPlayer) {
            for(int i = 1; i < board.NUM_OF_COLUMNS - 1 ; i++) {
                if(board.map.get(new Position(player.whichRow, i)).noOfStones!=0) {
                    choicesMovement.add(i);
                }
            }
            for(int move : choicesMovement) {
                Node n = new Node(this, board, player, opponent, move);
                children.add(n);
                n.player.execMove(opponent,move);
                
                
            }
        } else {
            for(int i = 1; i < board.NUM_OF_COLUMNS - 1 ; i++) {
                if(board.map.get(new Position(opponent.whichRow, i)).noOfStones!=0) {
                    choicesMovement.add(i);
                }
            }
            for(int move : choicesMovement) {
                Node n = new Node(this, board, player, opponent, move);
                children.add(n);
                n.player.execMove(player,move);
                
            }
        }
    }
    
    
}
