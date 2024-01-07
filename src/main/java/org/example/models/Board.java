package org.example.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Board implements Cloneable{
    private int dimension;
    private List<List<Cell>> board;

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }

    public Board(int n) {
        //Initialize a board of size * size.
        this.dimension = n;
        board = new ArrayList<>(); // ROWS

        for (int i = 0; i < n; i++) {
            board.add(new ArrayList<>()); //COLS

            for (int j = 0; j < n; j++) {
                board.get(i).add(new Cell(i, j));
            }
        }
    }

    private Board(Board board) throws CloneNotSupportedException {
        this.dimension = board.dimension;
        this.board = new ArrayList<>();
        //this.board.addAll(board.board);
        for (int i = 0; i < dimension; i++) {
            List<Cell> row = new ArrayList<>();
            for (int j = 0; j < dimension; j++) {
                row.add(board.board.get(i).get(j).clone());
            }
            this.board.add(row);
        }
    }

    public void displayBoard() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                Cell cell = board.get(i).get(j);
                if (cell.getCellState().equals(CellState.EMPTY)) {
                    System.out.print("| |");
                } else {
                    System.out.print("|" + cell.getPlayer().getSymbol().getSymbol() + "|");
                }
            }
            System.out.println();
        }
    }

    @Override
    public Board clone() throws CloneNotSupportedException {
        return new Board(this);
    }
}
