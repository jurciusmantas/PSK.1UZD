package rest.contracts;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class TrainsDto {
    private String name;
    private List<TrainAdditionalDataDto> additionalData;
}
