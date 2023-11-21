package mock;

import org.example.model.Game;
import org.example.repository.dao.GameRepository;

import java.util.List;

public class GameRepositoryMock implements GameRepository {

    private List<Game> games;

    public GameRepositoryMock(List<Game> games) {
        this.games = games;
    }
    public GameRepositoryMock() {
    }

    @Override
    public Game save(Game game) {
        return null;
    }

    @Override
    public Game get(int id) {
        return Game.builder().id(id).build();
    }

    @Override
    public boolean remove(int id) {
        return false;
    }

    @Override
    public int update(Game game) {
        return 0;
    }

    @Override
    public List<Game> findAll() {
        return this.games;

    }
}
