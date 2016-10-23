package pl.molasym.photoGrade.repository;

import pl.molasym.photoGrade.entities.User;
import pl.molasym.photoGrade.exceptions.UserNotFoundException;

import java.util.List;

/**
 * Created by moliq on 23.10.16.
 */
public interface UserRepository {

    public User getUserByUserId(long id) throws UserNotFoundException;
    public boolean validateLoginUser(String login, String password);
    public boolean checkIfUsernameExists(String username);


}
