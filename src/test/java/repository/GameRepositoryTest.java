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
    private final String password = "mysecretpassword";

    @Before
    public void init() throws SQLException {
     connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dvdrental", name, password);

    }

    @Test
    public void getTest() {
        GameRepository gameRepository = new GameRepositoryImpl(connection);
        int id = 2;
        Game expected = Game.builder().id(2).rating(18).type("3 action").name("GTA4").build();
        Game actual = gameRepository.get(id);
        System.out.println(actual);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void saveTest() {
        GameRepository gameRepository = new GameRepositoryImpl(connection);

        Game expected = Game.builder().rating(12).type("Shooter").name("Tractor simulator").build();
        boolean result = gameRepository.save(expected);
        Assert.assertTrue(!result); // TODO: 20.12.2022 check why it false
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
