package pl.molasym.photoGrade.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.molasym.photoGrade.entities.Photo;
import pl.molasym.photoGrade.entities.User;
import pl.molasym.photoGrade.enums.Visibility;
import pl.molasym.photoGrade.repository.PhotoRepository;
import pl.molasym.photoGrade.service.PhotoService;

import java.util.List;

/**
 * Created by moliq on 19.11.16.
 */
@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoDAO;

    public void addPhotoToUser(User user, Photo photo) {
        photoDAO.addPhotoToUser(user,photo);
    }

    public List<Photo> getPhotoFromUserByVisibility(User user, Visibility visibility) {
        return photoDAO.getPhotoFromUserByVisibility(user, Visibility.PUBLIC);
    }

    public Photo getPhotoById(Long id) {
        return photoDAO.getPhotoById(id);
    }
}
