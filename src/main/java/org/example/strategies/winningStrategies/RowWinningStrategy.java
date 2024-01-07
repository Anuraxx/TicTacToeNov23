package org.example.strategies.winningStrategies;

import org.example.models.Board;
import org.example.models.Move;
import org.example.models.Symbol;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy {
    private Map<Integer, Map<Symbol, Integer>> rowMaps = new HashMap<>();

    public RowWinningStrategy(){}

    private RowWinningStrategy(RowWinningStrategy rowWinningStrategy){
        for(Map.Entry<Integer, Map<Symbol, Integer>> en : rowWinningStrategy.rowMaps.entrySet()){
            rowMaps.put(en.getKey(), new HashMap<>(en.getValue()));
        }
    }

    @Override
    public boolean checkWinner(Move move, Board board) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();

        if (!rowMaps.containsKey(row)) {
            rowMaps.put(row, new HashMap<>());
        }

        Map<Symbol, Integer> currentRowMap = rowMaps.get(row);

        if (currentRowMap.containsKey(symbol)) {
            currentRowMap.put(symbol,
                    currentRowMap.get(symbol) + 1);
        } else {
            currentRowMap.put(symbol, 1);
        }

        return currentRowMap.get(symbol) == board.getDimension();
    }

    @Override
    public RowWinningStrategy clone() throws CloneNotSupportedException {
        return new RowWinningStrategy(this);
    }
}
