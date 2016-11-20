package pl.molasym.photoGrade.repository;

import org.springframework.stereotype.Repository;
import pl.molasym.photoGrade.entities.Photo;
import pl.molasym.photoGrade.entities.User;
import pl.molasym.photoGrade.enums.Visibility;

import java.util.List;

/**
 * Created by moliq on 19.11.16.
 */
public interface PhotoRepository {

    public void addPhotoToUser(User user, Photo photo);
    public List<Photo> getPhotoFromUserByVisibility(User user, Visibility visibility);
    public Photo getPhotoById(Long id);

}
