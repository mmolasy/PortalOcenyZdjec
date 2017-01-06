package pl.molasym.photoGrade.entities;

import org.codehaus.jackson.map.util.JSONPObject;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.*;
import java.util.List;

/**
 * Created by root on 22.10.16.
 */

@Entity
@Table(name = "PHOTO")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PHOTO_ID")
    private Long photoId;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="IMAGEFILE")
    private byte[] image;

    @Column(name="IMAGENAME")
    private String imageName;

    @Column(name = "GRADE")
    private double grade;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,mappedBy = "photo")
    List<Grade> grades;

    public JSONObject getTransformedGrades() {
        return transformedGrades;
    }

    public void setTransformedGrades(JSONObject transformedGrades) {
        this.transformedGrades = transformedGrades;
    }

    @Transient
    private JSONObject transformedGrades;

    public String getCurrentUserGrade() {
        return currentUserGrade;
    }

    public void setCurrentUserGrade(String currentUserGrade) {
        this.currentUserGrade = currentUserGrade;
    }

    @Transient
    private String currentUserGrade;


    @Temporal(TemporalType.DATE)
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name="VISIBILIY")
    private String visibility;

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public List<Grade> getGrades() {
        return grades;
    }
    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        if (Double.compare(photo.grade, grade) != 0) return false;
        if (photoId != null ? !photoId.equals(photo.photoId) : photo.photoId != null) return false;
        if (user != null ? !user.equals(photo.user) : photo.user != null) return false;
        if (description != null ? !description.equals(photo.description) : photo.description != null) return false;
        if (!Arrays.equals(image, photo.image)) return false;
        if (imageName != null ? !imageName.equals(photo.imageName) : photo.imageName != null) return false;
        if (grades != null ? !grades.equals(photo.grades) : photo.grades != null) return false;
        if (createdDate != null ? !createdDate.equals(photo.createdDate) : photo.createdDate != null) return false;
        return visibility != null ? visibility.equals(photo.visibility) : photo.visibility == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = photoId != null ? photoId.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(image);
        result = 31 * result + (imageName != null ? imageName.hashCode() : 0);
        temp = Double.doubleToLongBits(grade);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (grades != null ? grades.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (visibility != null ? visibility.hashCode() : 0);
        return result;
    }
}
