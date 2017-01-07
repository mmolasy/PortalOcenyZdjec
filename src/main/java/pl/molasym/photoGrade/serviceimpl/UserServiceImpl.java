package pl.molasym.photoGrade.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.molasym.photoGrade.entities.User;
import pl.molasym.photoGrade.exceptions.UserAlreadyRegistered;
import pl.molasym.photoGrade.exceptions.UserNotFoundException;
import pl.molasym.photoGrade.exceptions.UserWrongCredentials;
import pl.molasym.photoGrade.repository.UserRepository;
import pl.molasym.photoGrade.service.UserService;

import java.util.List;

/**
 * Created by moliq on 24.10.16.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userDAO;


    public User getUserByUserId(long id) throws UserNotFoundException{
        User user = userDAO.getUserByUserId(id);
        if(user == null)
            throw new UserNotFoundException();
        return userDAO.getUserByUserId(id);
    }

    public User validateLoginUser(String email, String password) throws UserWrongCredentials {
        User user = userDAO.validateLoginUser(email,password);
        if(user == null)
            throw new UserWrongCredentials("Wrong email or password");

        return user;
    }

    public User getUserByEmail(String mail) throws UserNotFoundException {
        User user = userDAO.getUserByEmail(mail);
        if(user == null)
            throw new UserNotFoundException();
        return user;
    }
    public void registerNewUser(User user) throws UserAlreadyRegistered {
        User found = userDAO.getUserByEmail(user.getEmail());
        if(found != null) {
            throw new UserAlreadyRegistered("E-mail already exist on DB");
        }
        userDAO.registerNewUser(user);
    }
    public boolean areFriends(User userOne, User userTwo) throws UserNotFoundException {
        if(userOne == null || userTwo == null)
            throw new UserNotFoundException();
        return userOne.getFriends().stream().anyMatch(x -> x.getUserId().equals(userTwo.getUserId()));
    }
    public List<User> getUserFriends(Long userId) throws UserNotFoundException {
        User user = userDAO.getUserByUserId(userId);
        if(user == null)
            throw new UserNotFoundException();
        return userDAO.getUserFriends(user);
    }
}

