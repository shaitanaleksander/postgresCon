package org.example.repository;

import org.example.model.Game;
import org.example.repository.dao.GameRepository;

import java.sql.*;
import java.util.ArrayList;

public class GameRepositoryImpl implements GameRepository {

    private final Connection connection;
    private static final String selectAll = "Select * from games where id = ?";

    private static final String save =
            """
                    INSERT INTO public.games(
                    name, type, rating)
                    VALUES (?, ?, ?)
            """;

    private static final String remove =
            """
                    DELETE FROM public.games
                    WHERE id = ?
                                
            """;

    private static final String update =
            """
                    UPDATE public.games
                    SET name=?, type=?, rating=?
                    WHERE  id = ?;
                                        
            """;

    public GameRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Game get(int id) {

        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from games where name = " + id);
            resultSet.next();

            return Game.builder()
                    .name(resultSet.getString("name"))
                    .rating(resultSet.getInt("rating"))
                    .type(resultSet.getString("type"))
                    .id(resultSet.getInt("id"))
                    .build();

        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean save(Game game) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(save);

            preparedStatement.setString(1, game.getName());
            preparedStatement.setString(2, game.getType());
            preparedStatement.setInt(3, game.getRating());
            return preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean remove(int id) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(remove);

            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Game game) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, game.getName());
            preparedStatement.setString(2, game.getType());
            preparedStatement.setInt(3, game.getRating());
            preparedStatement.setInt(4, game.getId());

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
