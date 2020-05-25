package services.trainService;

import rest.contracts.TrainsDTO;

public interface ITrainService {
    TrainsDTO getTrainById(Integer id);
    int createTrain(TrainsDTO train);
    boolean updateTrain(int id, TrainsDTO train);
}
