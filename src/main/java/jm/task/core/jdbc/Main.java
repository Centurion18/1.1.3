package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl user = new UserDaoJDBCImpl();

        user.createUsersTable();

        user.saveUser("Джапар", "Тохиров", (byte) 24);
        user.saveUser("Джапар", "Тохиров", (byte) 24);
        user.saveUser("Джапар", "Тохиров", (byte) 24);
        user.saveUser("Джапар", "Тохиров", (byte) 24);

//        user.removeUserById(1);

        user.getAllUsers();

        user.cleanUsersTable();
//
        user.dropUsersTable();
    }
}
