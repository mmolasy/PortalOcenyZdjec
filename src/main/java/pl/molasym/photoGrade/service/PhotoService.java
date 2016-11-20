package pl.molasym.photoGrade.service;

import pl.molasym.photoGrade.entities.Photo;
import pl.molasym.photoGrade.entities.User;
import pl.molasym.photoGrade.enums.Visibility;
import pl.molasym.photoGrade.exceptions.ServerException;
import pl.molasym.photoGrade.exceptions.UserNotFoundException;

import java.util.List;

/**
 * Created by moliq on 19.11.16.
 */
public interface PhotoService {
    public void addPhotoToUser(User user, Photo photo) throws UserNotFoundException, ServerException;
    public List<Photo> getPhotoFromUserByVisibility(User user, Visibility visibility) throws UserNotFoundException;
    public Photo getPhotoById(Long id);
    public List<Photo> getAllPhotoFromUser(User user) throws UserNotFoundException;

}
