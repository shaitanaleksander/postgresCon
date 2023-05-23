package org.example.repository.dao;

import org.example.model.Game;

public interface GameRepository {

    boolean save(Game game);

    Game get(int id);

    boolean remove(int id);

    int update(Game game);

}
