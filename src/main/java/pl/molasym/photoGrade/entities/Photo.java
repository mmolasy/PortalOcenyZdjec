package pl.molasym.photoGrade.entities;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
import pl.molasym.photoGrade.enums.Visibility;

import javax.persistence.*;
import java.io.File;
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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "photo")
    private List<Comment> comments;

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

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
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
}
