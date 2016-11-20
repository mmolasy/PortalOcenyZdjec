package pl.molasym.photoGrade.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.molasym.photoGrade.entities.User;
import pl.molasym.photoGrade.exceptions.UserAlreadyRegistered;
import pl.molasym.photoGrade.exceptions.UserNotFoundException;
import pl.molasym.photoGrade.exceptions.UserWrongCredentials;
import pl.molasym.photoGrade.repository.UserRepository;
import pl.molasym.photoGrade.service.UserService;

/**
 * Created by moliq on 24.10.16.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userDAO;


    public User getUserByUserId(long id) throws UserNotFoundException{
        return userDAO.getUserByUserId(id);
    }

    public User validateLoginUser(String email, String password) throws UserWrongCredentials {
        User user = userDAO.validateLoginUser(email,password);
        if(user == null)
            throw new UserWrongCredentials("Wrong email or password");

        return user;
    }

    public boolean checkIfUsernameExists(String username){
        return userDAO.checkIfUsernameExists(username);
    }

    public User getUserByEmail(String mail)  {

        User user = userDAO.getUserByEmail(mail);
        return user;
    }
    public void registerNewUser(User user) throws UserAlreadyRegistered {
        User found = userDAO.getUserByEmail(user.getEmail());
        if(found != null) {
            throw new UserAlreadyRegistered("E-mail already exist on DB");
        }
        else
        {
            userDAO.registerNewUser(user);
        }
    }
    public boolean areFriends(User userOne, final User userTwo){
       return userOne.getFriends().stream().anyMatch(x -> x.getUserId().equals(userTwo.getUserId()));
    }



}

