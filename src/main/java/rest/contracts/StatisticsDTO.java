package rest.contracts;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class StatisticsDTO {
    private int trainCount;
    private List<TrainAdditionalDataDTO> trainCountByType;
}
