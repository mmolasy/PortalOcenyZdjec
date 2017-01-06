package pl.molasym.photoGrade.entities;

import pl.molasym.photoGrade.entities.User;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by moliq on 24.12.16.
 */

@Entity
@Table(name="GRADE")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PHOTO_ID")
    private Photo photo;
    @Column(name="VALUE")
    @Min(value = 1)
    @Max(value = 5)
    private Integer value;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", user=" + user +
                ", photo=" + photo +
                ", value=" + value +
                '}';
    }
}
