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
public class AdversarialSearch {
     public static int alphaBeta(Node node, int depth, int alpha, int beta, boolean isMaximizingPlayer) {
         
        if(depth == 0 || node == null) {
//            System.out.println("in terminal :");
//            node.board.printBoard();
//            System.out.println("heur->"+ CalculateHeuristic.getHeuristic(node.board, node.player));
            return CalculateHeuristic.getHeuristic(node.board, node.player);
        }

        if(isMaximizingPlayer==true) {
            node.getSuccessors(true);
            int value=-999999;
            for(Node n : node.children) {
                value = alphaBeta(n, depth - 1, alpha, beta, false);
                alpha = Math.max(alpha, value);
                if(alpha >= beta) {
                    break;
                }
            }
            return value;
        } else {
            node.getSuccessors(false);
            int value=+999999;
            for(Node n : node.children) {
                value = alphaBeta(n, depth - 1, alpha, beta, true);
                beta = Math.min(beta, value);
                if(alpha >= beta) {
                    break;
                }
            }
            return value;
        }
    }
}
