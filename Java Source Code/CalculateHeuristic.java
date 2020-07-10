/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Zahin
 */
public class CalculateHeuristic {

    public static int HeuristicFunction1(Board board, Player player) {
        if (player.playerID == 0) {
            return board.map.get(new Position(0, 0)).noOfStones - board.map.get(new Position(1, board.NUM_OF_COLUMNS - 1)).noOfStones;
        } else if (player.playerID == 1) {
            return board.map.get(new Position(1, board.NUM_OF_COLUMNS - 1)).noOfStones - board.map.get(new Position(0, 0)).noOfStones;
        }
        return 0;
    }

    public static int HeuristicFunction2(Board board, Player player, int w1, int w2) {
        int hval1 = HeuristicFunction1(board, player);
        int stone_0 = 0;
        int stone_1 = 0;
        int hval2 = 0;

        for (int i = 1; i < board.NUM_OF_COLUMNS - 1; i++) {
            stone_0 += board.map.get(new Position(0, i)).noOfStones;
        }
        for (int i = 1; i < board.NUM_OF_COLUMNS - 1; i++) {
            stone_1 += board.map.get(new Position(1, i)).noOfStones;
        }
        if (player.playerID == 0) {
            hval2 = stone_0 - stone_1;
        } else if (player.playerID == 1) {
            hval2 = stone_1 - stone_0;
        }
        return w1 * hval1 + w2 * hval2;

    }

    public static int HeuristicFunction3(Board board, Player player, int w1, int w2, int w3) {
        int hval1 = HeuristicFunction1(board, player);
        int stone_0 = 0;
        int stone_1 = 0;
        int hval2 = 0;
        int extraMoves = 0;
        for (int i = 1; i < board.NUM_OF_COLUMNS - 1; i++) {
            stone_0 += board.map.get(new Position(0, i)).noOfStones;
        }
        for (int i = 1; i < board.NUM_OF_COLUMNS - 1; i++) {
            stone_1 += board.map.get(new Position(1, i)).noOfStones;
        }
        if (player.playerID == 0) {
            hval2 = stone_0 - stone_1;
        } else if (player.playerID == 1) {
            hval2 = stone_1 - stone_0;
        }
        if (player.playerID == 0) {
            for (int i = 1; i < board.NUM_OF_COLUMNS - 1; i++) {
                if (board.map.get(new Position(0, i)).noOfStones == i) {
                    extraMoves++;
                }
            }
        } else if (player.playerID == 1) {
            for (int i = 1; i < board.NUM_OF_COLUMNS - 1; i++) {
                if (board.map.get(new Position(1, i)).noOfStones == (board.NUM_OF_COLUMNS - 1 - i)) {
                    extraMoves++;
                }
            }
        }
        int hval3 = extraMoves;
       
        //int hval3 = player.num_extraMoveEarned;
        return w1 * hval1 + w2 * hval2 + w3 * hval3;

    }

    public static int HeuristicFunction4(Board board, Player player, int w1, int w2, int w3, int w4) {
        int hval1 = HeuristicFunction1(board, player);
        int stone_0 = 0;
        int stone_1 = 0;
        int hval2 = 0;

        for (int i = 1; i < board.NUM_OF_COLUMNS - 1; i++) {
            stone_0 += board.map.get(new Position(0, i)).noOfStones;
        }
        for (int i = 1; i < board.NUM_OF_COLUMNS - 1; i++) {
            stone_1 += board.map.get(new Position(1, i)).noOfStones;
        }
        if (player.playerID == 0) {
            hval2 = stone_0 - stone_1;
        } else if (player.playerID == 1) {
            hval2 = stone_1 - stone_0;
        }
        int extraMoves = 0;
        if (player.playerID == 0) {
            for (int i = 1; i < board.NUM_OF_COLUMNS - 1; i++) {
                if (board.map.get(new Position(0, i)).noOfStones == i) {
                    extraMoves++;
                }
            }
        } else if (player.playerID == 1) {
            for (int i = 1; i < board.NUM_OF_COLUMNS - 1; i++) {
                if (board.map.get(new Position(1, i)).noOfStones == (board.NUM_OF_COLUMNS - 1 - i)) {
                    extraMoves++;
                }
            }
        }
        int hval3 = extraMoves;
        //   int hval4 = player.FalseExecMove(player, hval3);
        ArrayList<Integer> choicesMovement = new ArrayList<>();
        for (int i = 1; i < board.NUM_OF_COLUMNS - 1; i++) {
            if (board.map.get(new Position(player.whichRow, i)).noOfStones != 0) {
                choicesMovement.add(i);
            }
        }
        int stones_captured=0;
        for(int move : choicesMovement) {
                stones_captured+=player.FalseExecMove(move);
        }
        if(player.whichRow == 0)
             stones_captured+= board.isThisSideEmpty(0, 1);
        else 
            stones_captured+= board.isThisSideEmpty(0, 1);
        int hval4 = stones_captured;

//        if(player.playerID==0) hval4 = board.map.get(new Position(0,0)).noOfStones;
//        else hval4 = board.map.get(new Position(1,board.NUM_OF_COLUMNS-1)).noOfStones;
//        
        /// else if(player.playerID==1) {
        // return board.map.get(new Position(1,board.NUM_OF_COLUMNS-1)).
        return w1 * hval1 + w2 * hval2 + w3 * hval3 + w4 * hval4;

    }

    public static int getHeuristic(Board board, Player player) {
        Random rand = new Random();
        if (player.heuristic == 1) {
            return HeuristicFunction1(board, player);
        } else if (player.heuristic == 2) {
            int w1 = 4;//rand.nextInt(3)+1;
            int w2 = 3;//rand.nextInt(7)+1;
            return HeuristicFunction2(board, player, w1, w2);
        } else if (player.heuristic == 3) {
            int w1 = 12;// rand.nextInt(2)+1;
            int w2 = 3;//rand.nextInt(8)+1;
            int w3 = 9;// rand.nextInt(5)+1;
            return HeuristicFunction3(board, player, w1, w2, w3);
        } else {
            int w1 =12;
            int w2 =3;
            int w3 =9;
            int w4 = 4;
            return HeuristicFunction4(board, player, w1, w2, w3, w4);
        }
    }
}
