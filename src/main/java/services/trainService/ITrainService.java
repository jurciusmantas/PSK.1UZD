package services.trainService;

import rest.contracts.TrainsDto;

public interface ITrainService {
    TrainsDto getTrainById(Integer id);
    Integer createTrain(TrainsDto train);
}
