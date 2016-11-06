package pl.molasym.photoGrade.repository;

import pl.molasym.photoGrade.entities.User;
import pl.molasym.photoGrade.exceptions.UserNotFoundException;

import java.util.List;

/**
 * Created by moliq on 23.10.16.
 */
public interface UserRepository {

    public User getUserByUserId(long id) throws UserNotFoundException;
    public User validateLoginUser(String email, String password);
    public boolean checkIfUsernameExists(String username);
    public User getUserByEmail(String mail);
    public void registerNewUser(User user);


}
