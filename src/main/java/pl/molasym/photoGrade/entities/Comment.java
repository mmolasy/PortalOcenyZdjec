package pl.molasym.photoGrade.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by root on 22.10.16.
 */

@Entity
@Table(name="COMMENT")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COMMENT_ID")
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne
    @JoinColumn(name="PHOTO_ID")
    private Photo photo;

    @Column(name = "CONTENT")
    private String content;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATED_DATE")
    private Date createdDate;
}
