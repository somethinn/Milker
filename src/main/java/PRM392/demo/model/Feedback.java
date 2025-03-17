package PRM392.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @Column(name = "ReviewID", nullable = false)
    private String reviewID;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ProductID")
    private Product productID;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "MemberID")
    private User memberID;

    @Column(name = "Grade")
    private Integer grade;

    @Lob
    @Column(name = "Comment")
    private String comment;

    @Column(name = "CreateDate")
    private Instant createDate;

    @Column(name = "UpdateDate")
    private Instant updateDate;

}