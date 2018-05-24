package edu.upenn.cis350.hwk1;

import java.util.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Search {
    
    public static int findCarrots(int[][] matrix) {
    	
    	int[][] temp = matrix;
        
        int width = matrix[0].length;
        int height = matrix.length;
        
        int startRow = -1;
        int startCol = -1;
        
      //find center when exists
        if (width % 2 == 1 && height % 2 == 1) {
            startRow = height/2 + 1;
            startCol = width/2 + 1;
        }
        
        //find center when not exists
        
        else if (width % 2 == 0 && height % 2 == 0) {
            int carrots = -1;
            for (int i = width/2; i <=width/2 + 1; i++) {
                for (int j = height/2; j <= height/2 + 1; j++) {
                    if (matrix[j][i] > carrots) {
                        carrots = matrix[j][i];
                        startRow = j;
                        startCol = i;
                    }
                }
            }
        }
        
        else if (width % 2 == 0 && height % 2 != 0) {
            if (matrix[height/2 + 1][width/2] > matrix[height/2 + 1][width/2 + 1]) {
                startRow = height / 2 + 1;
                startCol = width / 2;
            }
            else {
                startRow = height/2 + 1;
                startCol = width/2 + 1;
            }
        }
        
        else if (height % 2 == 0 && width % 2 != 0) {
            if (matrix[height/2][width/2] > matrix[height/2 + 1][width/2]) {
                startRow = height / 2;
                startCol = width / 2 + 1;
            }
            else {
                startRow = height/2 + 1;
                startCol = width/2 + 1;
            }
        }
        
        boolean noneLeft = false;
        int carrotsEaten = 0;
        int currRow = startRow - 1;
        int currCol = startCol - 1;
        int currCarrots = 0;
        int nextRow = 0;
        int nextCol = 0;
        
        
        //start simulation
        while (!noneLeft) {
            //eat current carrots
            carrotsEaten += temp[currRow][currCol];
            //make matrix position 0
            temp[currRow][currCol] = 0;
            //look up 
            if (currRow - 1 >= 0) {
            		if (temp[currRow - 1][currCol] > currCarrots) {
                    currCarrots = temp[currRow - 1][currCol];
                    nextRow = currRow - 1;
                    nextCol = currCol;
            		}
            }
            //look right 
            if (currCol + 1 < temp[0].length) {
                if (temp[currRow][currCol + 1] > currCarrots) {
                    nextRow = currRow;
                    nextCol = currCol + 1;
                    currCarrots = temp[currRow][currCol + 1];
                }
            }
            //look down
            if (currRow + 1 < temp.length) {
                if (temp[currRow + 1][currCol] > currCarrots) {
                    nextRow = currRow + 1;
                    nextCol = currCol;
                    currCarrots = temp[currRow + 1][currCol];
                }
            }
            //look left
            if (currCol - 1 >= 0) {
                if (temp[currRow][currCol - 1] > currCarrots) {
                    nextRow = currRow;
                    nextCol = currCol - 1;
                    currCarrots = temp[currRow][currCol - 1];
                }
            }
            //check if carrots not zero
            if (currCarrots == 0) {
                noneLeft = true;
            }
            //update position for next
            currRow = nextRow;
            currCol = nextCol;
            
            currCarrots = 0;
        }
        
        return carrotsEaten;
    }
    
    public static void main(String[] args) {
    	int[][] matrix = new int[4][5];
    	matrix[0][0] = 5;
    	matrix[0][1] = 7;
    	matrix[0][2] = 8;
    	matrix[0][3] = 6;
    	matrix[0][4] = 3;
    	matrix[1][0] = 0;
    	matrix[1][1] = 0;
    	matrix[1][2] = 7;
    	matrix[1][3] = 0;
    	matrix[1][4] = 4;
    	matrix[2][0] = 4;
    	matrix[2][1] = 6;
    	matrix[2][2] = 3;
    	matrix[2][3] = 4;
    	matrix[2][4] = 9;
    	matrix[3][0] = 3;
    	matrix[3][1] = 1;
    	matrix[3][2] = 0;
    	matrix[3][3] = 5;
    	matrix[3][4] = 8;
    	System.out.println(findCarrots(matrix));
    	
    	// 5 7 8 6 3
    	// 0 0 7 0 4 
    	// 4 6 3 4 9 
    	// 3 1 0 5 8
    	
    	
    }
}
