package vu.lt.entities;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.keys.CoPilotKey;

import javax.persistence.*;

@Entity
@Getter @Setter
public class CoPilot {

    @EmbeddedId
    private CoPilotKey id;

    @ManyToOne
    @MapsId("RACER_ID")
    @JoinColumn(name = "RACER_ID")
    private Racer racer;

    @ManyToOne
    @MapsId("RACINGCLASS_ID")
    @JoinColumn(name = "RACINGCLASS_ID")
    private RacingClass racingClass;

    private String name;

    public CoPilot(){

    }
}
