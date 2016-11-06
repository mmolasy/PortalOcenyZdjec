package pl.molasym.photoGrade.service;

import pl.molasym.photoGrade.entities.User;
import pl.molasym.photoGrade.exceptions.UserAlreadyRegistered;
import pl.molasym.photoGrade.exceptions.UserNotFoundException;
import pl.molasym.photoGrade.exceptions.UserWrongCredentials;

/**
 * Created by moliq on 24.10.16.
 */
public interface UserService {

    public User getUserByUserId(long id) throws UserNotFoundException;
    public User validateLoginUser(String email, String password) throws UserWrongCredentials;
    public boolean checkIfUsernameExists(String username);
    public User getUserByEmail(String mail);
    public void registerNewUser(User user) throws UserAlreadyRegistered;
}
