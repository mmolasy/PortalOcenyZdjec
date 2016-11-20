package pl.molasym.photoGrade.entities;

import javax.persistence.*;

/**
 * Created by moliq on 20.11.16.
 */

@Entity
@Table(name="INVITATION")
public class Invitation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="FROM")
    private User from;

    @Column(name="TO")
    private User to;

    @Column(name = "STATUS")
    private String status;
}
