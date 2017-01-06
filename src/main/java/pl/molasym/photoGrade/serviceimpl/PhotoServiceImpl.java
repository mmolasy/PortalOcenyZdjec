package pl.molasym.photoGrade.serviceimpl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.molasym.photoGrade.dto.GradeValue;
import pl.molasym.photoGrade.entities.Grade;
import pl.molasym.photoGrade.entities.Photo;
import pl.molasym.photoGrade.entities.User;
import pl.molasym.photoGrade.enums.Visibility;
import pl.molasym.photoGrade.exceptions.PhotoNotFound;
import pl.molasym.photoGrade.exceptions.ServerException;
import pl.molasym.photoGrade.exceptions.UserNotFoundException;
import pl.molasym.photoGrade.repository.PhotoRepository;
import pl.molasym.photoGrade.service.PhotoService;
import pl.molasym.photoGrade.service.UserService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * Created by moliq on 19.11.16.
 */
@Service
@Transactional
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
        List<Photo> photoList = photoDAO.getPhotoFromUserByVisibility(user, visibility);
        for(Photo photo: photoList) {
            try {
                photo.setTransformedGrades(new JSONObject(getPhotoGrades(photo.getPhotoId())));
            } catch (PhotoNotFound photoNotFound) {
                photoNotFound.printStackTrace();
            }
        }
        return photoList;
    }

    public Photo getPhotoById(Long id) {
        return photoDAO.getPhotoById(id);

    }

    public List<Photo> getAllPhotoFromUser(User user) throws UserNotFoundException {
        if(user == null)
            throw new UserNotFoundException();
        List<Photo> photoList = photoDAO.getAllPhotoFromUser(user);
        for(Photo photo: photoList) {
            try {
                photo.setTransformedGrades(new JSONObject(getPhotoGrades(photo.getPhotoId())));
            } catch (PhotoNotFound photoNotFound) {
                photoNotFound.printStackTrace();
            }
        }
        return photoList;
    }

    public void addPhotoGrade(User user, Long photoId, String val) throws Exception {
        Photo founded = photoDAO.getPhotoById(photoId);
        if (founded == null)
            throw new PhotoNotFound();
        User photoOwner = founded.getUser();
        if(!(founded.getVisibility().equals(Visibility.PUBLIC.name()) || photoOwner.getUserId().equals(user.getUserId()) || userService.areFriends(user, photoOwner)))
            throw new Exception("No access");
        GradeValue value = GradeValue.valueOf(val);
        if(value == null)
            throw new Exception();

        Grade grade = new Grade();

        boolean gradeFounded  = founded.getGrades().stream().anyMatch(x -> x.getUser().getUserId().equals(user.getUserId()));
        if(value.compareTo(GradeValue.NoRate) == 0){
            if (gradeFounded){
                grade.setUser(user);
                grade.setPhoto(founded);
                photoDAO.removePhotoGrade(founded, grade);
            }
            return;
        }
        switch(value) {
            case One:
                grade.setValue(GradeValue.One.getGrade());
                break;
            case Two:
                grade.setValue(GradeValue.Two.getGrade());
                break;
            case Three:
                grade.setValue(GradeValue.Three.getGrade());
                break;
            case Four:
                grade.setValue(GradeValue.Four.getGrade());
                break;
            case Five:
                grade.setValue(GradeValue.Five.getGrade());
                break;
        }
        grade.setUser(user);
        grade.setPhoto(founded);
        if(gradeFounded){
            photoDAO.updatePhotoGrade(founded, grade);
        }
        else {
            photoDAO.addPhotoGrade(founded, grade);
        }
    }
    public Map<Integer, Long> getPhotoGrades(Long photoId) throws PhotoNotFound {
        Photo founded = photoDAO.getPhotoById(photoId);
        if (founded == null)
            throw new PhotoNotFound();
        return photoDAO.getGradesByPhoto(founded);
    }
    public String getGradeByPhotoAndUser(Photo photo, User user){
        Integer value = photoDAO.getGradeByPhotoAndUser(photo, user);
        if(value == null)
            return "0";
        else
            return String.valueOf(value);
        }
    public void removePhoto(User user, Long photoId) throws Exception {
        Photo founded = photoDAO.getPhotoById(Long.valueOf(photoId));
        if (founded == null)
            throw new PhotoNotFound();
        if(!founded.getUser().getUserId().equals(user.getUserId()))
            throw new Exception("No access");
        photoDAO.removePhoto(founded);
    }
}

