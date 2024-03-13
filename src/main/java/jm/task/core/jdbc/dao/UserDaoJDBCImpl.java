package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static String CREATE = "CREATE TABLE IF NOT EXISTS Users (" +
            "id SERIAL PRIMARY KEY," +
            "name VARCHAR(20)," +
            "lastName VARCHAR(20)," +
            "age SMALLINT" +
            ");";
    private static String SAVE = "INSERT INTO Users(name, lastName, age)" +
            "VALUES (?, ?, ?);";
    private static String DROP = "DROP TABLE IF EXISTS Users;";
    private static String REMOVE = "DELETE FROM Users WHERE id = ?;";
    private static String GET = "SELECT * FROM Users;";
    private static String CLEAN = "TRUNCATE TABLE Users;";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE);)
        {
            statement.executeUpdate();
            System.out.println("Таблица Users создана!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        try(Connection connection = Util.getConnection();
            PreparedStatement statement = connection.prepareStatement(DROP);)
        {
            statement.executeUpdate();
            System.out.println("Таблица Users удалена!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void saveUser(String name, String lastName, byte age) {
        try(Connection connection = Util.getConnection();
            PreparedStatement statement = connection.prepareStatement(SAVE);)
        {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу данных ");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement(REMOVE);)
        {
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("User под id - " + id + " удален!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET);)
        {
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                byte age = rs.getByte("age");

                userList.add(new User(id, name, lastName, age));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for(User user : userList){
            System.out.println(user);
        }

        return userList;
    }

    public void cleanUsersTable() {
        try(Connection connection = Util.getConnection();
            PreparedStatement statement = connection.prepareStatement(CLEAN);)
        {
            statement.executeUpdate();
            System.out.println("Таблица Users очищена!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
