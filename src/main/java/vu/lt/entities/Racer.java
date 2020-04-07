package vu.lt.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Racer.findAll", query = "select a from Racer as a")
})
@Table(name = "RACER")
@Getter @Setter
public class Racer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "RACINGCLASS_ID")
    private RacingClass racingClass;

    @OneToMany(mappedBy = "racer")
    private Set<CoPilot> coPilots;

    public Racer(){

    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Racer racer = (Racer) o;
        return Objects.equals(id, racer.id) &&
                Objects.equals(name, racer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
