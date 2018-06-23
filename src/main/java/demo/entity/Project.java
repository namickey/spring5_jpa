package demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PROJECT")
@Data
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int id;

    @Column()
    private String name;

    @Override
    public String toString() {
        return this.id + ", " + this.name;
    }
}
