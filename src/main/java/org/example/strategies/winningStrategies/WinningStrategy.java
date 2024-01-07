package org.example.strategies.winningStrategies;

import org.example.models.Board;
import org.example.models.Move;

public interface WinningStrategy extends Cloneable {
    boolean checkWinner(Move move, Board board);

    WinningStrategy clone() throws CloneNotSupportedException;
}
