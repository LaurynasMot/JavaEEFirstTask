package vu.lt.entities.keys;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Racer;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter @Setter
public class CoPilotKey implements Serializable {

    @Column(name = "RACER_ID")
    private Integer racerId;

    @Column(name = "RACINGCLASS_ID")
    private Integer racingClassId;

    @Override
    public int hashCode() {
        return Objects.hash(racerId, racingClassId);
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoPilotKey coPilotKey = (CoPilotKey) o;
        return Objects.equals(racerId, coPilotKey.racerId) &&
                Objects.equals(racingClassId, coPilotKey.racingClassId);
    }

    public CoPilotKey(){

    }
}
