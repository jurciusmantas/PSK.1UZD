package rest.contracts;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DestinationsDTO {
    private int id;
    private int distanceFromStation;
    private String name;
}
