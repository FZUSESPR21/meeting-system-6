package Dao;

import domain.User;

import java.util.List;

public interface UserDao {
    public boolean findUser(User user);
    public boolean addUser(User user);
}
