package vu.lt.entities;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "RacingClass.findAll", query = "select t from RacingClass as t")
})
@Table(name = "RACINGCLASS")
@Getter @Setter
public class RacingClass {

    public RacingClass(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "racingClass", fetch = FetchType.EAGER)
    private List<Racer> racers = new ArrayList<>();

    @OneToMany(mappedBy = "racingClass")
    private Set<CoPilot> coPilots;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RacingClass racingClass = (RacingClass) o;
        return Objects.equals(name, racingClass.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
