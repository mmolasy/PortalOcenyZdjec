package pl.molasym.photoGrade.entities;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by root on 22.10.16.
 */
public class Photo {

    private long id;
    private User owner;
    private String description;
    private Image image;
    private double grade;
    private List<Comment> comments;
    private Date creationDate;
}
