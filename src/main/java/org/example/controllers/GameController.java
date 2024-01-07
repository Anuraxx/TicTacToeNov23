package org.example.controllers;

import org.example.exceptions.GameNotFoundException;
import org.example.models.Game;
import org.example.models.GameState;
import org.example.models.Player;
import org.example.strategies.winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameController {

    private Map<Integer, List<Game>> gameStates = new HashMap<>();

    public Game startGame(int id, int boardDimension, List<Player> players,
                          List<WinningStrategy> winningStrategies) throws Exception {
        Game game = Game.getBuilder()
                .setDimension(boardDimension)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .build();

        game.setId(id);

        gameStates.put(game.getId(), gameStates.getOrDefault(game.getId(), new ArrayList<>()));
        gameStates.get(game.getId()).add(game.clone());

        return game;
    }

    public void makeMove(Game game) throws CloneNotSupportedException {
        game.makeMove();
        Game copy = game.clone();
        gameStates.get(copy.getId()).add(copy);
    }

    public void displayBoard(Game game) {
        game.printBoard();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }

    public Game undo(Game game) throws GameNotFoundException, CloneNotSupportedException {
        if(!gameStates.containsKey(game.getId())){
            throw new GameNotFoundException("Game not found");
        }else{
            int n = gameStates.get(game.getId()).size();
            if(n < 2){
                System.out.println("Undo not possible");
                return game;
            }else{
                gameStates.get(game.getId()).remove(n-1);
                return gameStates.get(game.getId()).get(n-2).clone();
            }
        }
    }


    public GameState getGameState(Game game) {
        return game.getGameState();
    }
}
