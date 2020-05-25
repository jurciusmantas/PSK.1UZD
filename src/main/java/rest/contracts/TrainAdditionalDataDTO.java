package rest.contracts;

import enums.TrainAdditionalDataType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TrainAdditionalDataDTO {
    private TrainAdditionalDataType type;
    private String value;
}
