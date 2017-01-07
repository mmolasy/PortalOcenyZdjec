package pl.molasym.photoGrade.repository;

import pl.molasym.photoGrade.entities.Grade;
import pl.molasym.photoGrade.entities.Photo;
import pl.molasym.photoGrade.entities.User;
import pl.molasym.photoGrade.enums.Visibility;

import java.util.List;
import java.util.Map;

/**
 * Created by moliq on 19.11.16.
 */
public interface PhotoRepository {

    public void addPhotoToUser(User user, Photo photo);
    public List<Photo> getPhotoFromUserByVisibility(User user, Visibility visibility);
    public Photo getPhotoById(Long id);
    public List<Photo> getAllPhotoFromUser(User user);
    public void addPhotoGrade(Photo photo, Grade grade);
    public void updatePhotoGrade(Photo photo, Grade newGrade);
    public void removePhotoGrade(Photo photo, Grade newGrade);
    public Map<Integer,Long> getGradesByPhoto(Photo photo);
    public Integer getGradeByPhotoAndUser(Photo photo, User user);
    public void removePhoto(Photo photo);
    public void updateUser(User user);
}
