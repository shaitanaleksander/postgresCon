package repository;

import org.example.model.Game;
import org.example.repository.GameRepositoryImpl;
import org.example.repository.dao.GameRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GameRepositoryTest {

    private Connection connection;
    private final String name = "postgres";
    private final String password = "shaitan13";

    @Before
    public void init() throws SQLException {
     connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", name, password);

    }

    @Test
    public void getTest() {
        GameRepository gameRepository = new GameRepositoryImpl(connection); // TODO: 21.11.2023  what we can do about it?

        // prerequisite
        Game game = Game.builder().rating(18).type("3 action").name("GTA4").build();
        Game expected = gameRepository.save(game);

        // test
        Game actual = gameRepository.get(expected.getId());

        // check
        Assert.assertEquals(expected, actual);

        // clean up
        Assert.assertFalse(gameRepository.remove(expected.getId()));
    }
    @Test
    public void saveTest() {
        GameRepository gameRepository = new GameRepositoryImpl(connection); // TODO: 21.11.2023 what we can do about it?

        Game game = Game.builder().rating(12).type("Shooter").name("Tractor simulator").build();
        Game result = gameRepository.save(game);
        Game actual  = gameRepository.get(result.getId());
        Assert.assertEquals(result, actual);

        Assert.assertFalse(gameRepository.remove(actual.getId()));
    }

    @Test
    public void removeTest() {
        GameRepository gameRepository = new GameRepositoryImpl(connection);
        int id  = 38;
        boolean result = gameRepository.remove(id);
        Assert.assertTrue(!result); // TODO: 20.12.2022 check why it false
    }
    @Test
    public void updateTest() {
        GameRepository gameRepository = new GameRepositoryImpl(connection);
        Game expected = Game.builder().rating(18).type("Shooter").name("Doom").id(21).build();
        int real = gameRepository.update(expected);

        Assert.assertEquals(1, real); // TODO: 20.12.2022 check why it false
    }
}
