package demo.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MEMBER")
@Data
@ToString(exclude = "project")
public class Member implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int id;

    @Column()
    private String name;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
