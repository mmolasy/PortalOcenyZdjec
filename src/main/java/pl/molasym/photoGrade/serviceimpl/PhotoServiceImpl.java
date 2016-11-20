package pl.molasym.photoGrade.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.molasym.photoGrade.entities.Photo;
import pl.molasym.photoGrade.entities.User;
import pl.molasym.photoGrade.enums.Visibility;
import pl.molasym.photoGrade.exceptions.ServerException;
import pl.molasym.photoGrade.exceptions.UserNotFoundException;
import pl.molasym.photoGrade.repository.PhotoRepository;
import pl.molasym.photoGrade.service.PhotoService;
import pl.molasym.photoGrade.service.UserService;

import java.util.List;

/**
 * Created by moliq on 19.11.16.
 */
@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoDAO;

    @Autowired
    private UserService userService;

    public void addPhotoToUser(User user, Photo photo) throws UserNotFoundException, ServerException {
        if(user == null || photo == null)
            throw new ServerException();

        if(userService.getUserByUserId(user.getUserId()) == null)
            throw new UserNotFoundException();
        photoDAO.addPhotoToUser(user,photo);
    }

    public List<Photo> getPhotoFromUserByVisibility(User user, Visibility visibility) throws UserNotFoundException {
        if(user == null)
            throw new UserNotFoundException();
        return photoDAO.getPhotoFromUserByVisibility(user, visibility);
    }

    public Photo getPhotoById(Long id) {
        return photoDAO.getPhotoById(id);

    }

    public List<Photo> getAllPhotoFromUser(User user) throws UserNotFoundException {
        if(user == null)
            throw new UserNotFoundException();
        return photoDAO.getAllPhotoFromUser(user);
    }

}
