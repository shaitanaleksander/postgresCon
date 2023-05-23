package org.example.service;

import org.example.repository.dao.GameRepository;

public class GameService {

    private GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
}
