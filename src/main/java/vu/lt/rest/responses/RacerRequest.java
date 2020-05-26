package vu.lt.rest.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RacerRequest {
    private String name;

    private String licensePlate;

    private Integer racingClassId;
}
