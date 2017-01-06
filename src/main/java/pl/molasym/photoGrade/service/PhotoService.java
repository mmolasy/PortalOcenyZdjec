package pl.molasym.photoGrade.service;

import pl.molasym.photoGrade.dto.GradeValue;
import pl.molasym.photoGrade.entities.Grade;
import pl.molasym.photoGrade.entities.Photo;
import pl.molasym.photoGrade.entities.User;
import pl.molasym.photoGrade.enums.Visibility;
import pl.molasym.photoGrade.exceptions.PhotoNotFound;
import pl.molasym.photoGrade.exceptions.ServerException;
import pl.molasym.photoGrade.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Map;

/**
 * Created by moliq on 19.11.16.
 */
public interface PhotoService {
    public void addPhotoToUser(User user, Photo photo) throws UserNotFoundException, ServerException;
    public List<Photo> getPhotoFromUserByVisibility(User user, Visibility visibility) throws UserNotFoundException;
    public Photo getPhotoById(Long id);
    public List<Photo> getAllPhotoFromUser(User user) throws UserNotFoundException;
    public void addPhotoGrade(User user, Long photoId, String val) throws Exception;
    public Map<Integer, Long> getPhotoGrades(Long photoId) throws PhotoNotFound;
    public String getGradeByPhotoAndUser(Photo photo, User user);
    public void removePhoto(User user, Long photoId) throws Exception;
    }
