package PRM392.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @Size(max = 255)
    @Column(name = "reviewid", nullable = false)
    private String reviewid;

    @Lob
    @Column(name = "comment")
    private String comment;

    @Column(name = "create_date")
    private Instant createDate;

    @Column(name = "grade")
    private Integer grade;

    @Column(name = "update_date")
    private Instant updateDate;

    @Size(max = 255)
    @Column(name = "memberid")
    private String memberid;

    @Size(max = 255)
    @Column(name = "productid")
    private String productid;

}