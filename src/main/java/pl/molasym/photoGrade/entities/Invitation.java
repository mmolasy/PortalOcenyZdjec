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

    @ManyToOne()
    private User from;

    @ManyToOne()
    private User to;

    @Column(name = "STATUS")
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Invitation{" +
                "id=" + id +
                ", from=" + from.getUserId() +
                ", to=" + to.getUserId() +
                ", status='" + status + '\'' +
                '}';
    }
}

