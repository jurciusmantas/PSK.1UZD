package rest.contracts;

import enums.TrainAdditionalDataType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TrainAdditionalDataDto {
    private TrainAdditionalDataType type;
    private String value;
}
