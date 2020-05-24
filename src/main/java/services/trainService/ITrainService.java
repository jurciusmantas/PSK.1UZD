package services.trainService;

import rest.contracts.TrainsDto;

public interface ITrainService {
    TrainsDto getTrainById(Integer id);
    int createTrain(TrainsDto train);
    boolean updateTrain(int id, TrainsDto train);
}
