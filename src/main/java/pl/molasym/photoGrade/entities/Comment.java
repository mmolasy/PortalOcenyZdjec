package pl.molasym.photoGrade.entities;

import java.util.Date;

/**
 * Created by root on 22.10.16.
 */
public class Comment {

    private Date creationDate;
    private long id;
    private User owner;
    private Photo photo;
    private String comment;

}
