package org.example.service;

import org.example.model.Game;

public class GameSingleton {

    private static Game game;


    public static Game get() {
        if(game == null) game =  Game.builder().build();
        return game;
    }

}
