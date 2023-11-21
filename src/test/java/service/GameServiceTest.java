package service;

import mock.GameRepositoryMock;
import org.example.model.Game;
import org.example.service.GameService;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class GameServiceTest {



    @Test
    public void FindByIdTest() {

      GameService gameService = new GameService(new GameRepositoryMock());
      int id = 23;

        Game game = gameService.findById(23);

        Assert.assertEquals(id,game.getId());

    }


    @Test
    public void getAllWithDiscountTest() {
        int initialPrice = 100;

        List<Game> games = List.of(
                Game.builder().price(initialPrice).build()
        );
        GameService gameService = new GameService(new GameRepositoryMock(games));

        float discount = 0.2f;

        List<Game> withDiscount = gameService.getAllWithDiscount(discount);

        System.out.println(initialPrice);
        System.out.println(withDiscount.getFirst().getPrice());

    }
}
