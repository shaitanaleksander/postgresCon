package org.example.service;

import org.example.model.Game;
import org.example.repository.dao.GameRepository;

import java.util.List;

public class GameService {

    private GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }


    public Game findById(int id) {
        return this.gameRepository.get(id);
    }

    public List<Game> getAllWithDiscount(float discount) {
        return gameRepository
                .findAll()
                .stream()
                .peek(game -> game.setPrice((int) (game.getPrice() * discount)))
                .toList();
    }
}
