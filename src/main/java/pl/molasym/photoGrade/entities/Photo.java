package pl.molasym.photoGrade.entities;

import javax.persistence.*;
import java.awt.*;
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
    private long id;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @Column(name="DESCRIPTION")
    private String description;

    @Transient
    private Image image;

    @Column(name = "GRADE")
    private double grade;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "photo")
    private List<Comment> comments;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATED_DATE")
    private Date createdDate;
}
