package rest.contracts;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class TrainsDTO {
    private String name;
    private List<TrainAdditionalDataDTO> additionalData;
}
