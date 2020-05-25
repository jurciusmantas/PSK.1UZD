package services.trainService;

import entities.Train;
import entities.TrainAdditionalData;
import entities.TrainAdditionalDataKey;
import interceptors.Logged;
import persistence.TrainsDAO;
import rest.contracts.TrainAdditionalDataDTO;
import rest.contracts.TrainsDTO;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Logged
@Decorator
public abstract class TrainServiceDecorator implements ITrainService {
    @Inject
    private TrainsDAO trainsDAO;

    @Inject
    @Delegate
    @Any
    private ITrainService trainService;

    @Logged
    public int createTrain(TrainsDTO train){
        Integer newTrainId = trainService.createTrain(train);

        List<TrainAdditionalDataDTO> trainAddData = train.getAdditionalData();
        if (newTrainId != -1 && trainAddData.size() > 0){
            List<TrainAdditionalData> newTrainAdditionalData = new ArrayList<>();
            Train newTrain = trainsDAO.findOne(newTrainId);

            trainAddData.forEach(data -> {
                TrainAdditionalDataKey key = new TrainAdditionalDataKey();
                key.setTrainId(newTrainId);
                key.setType(data.getType());

                TrainAdditionalData newAddData = new TrainAdditionalData();
                newAddData.setId(key);
                newAddData.setValue(data.getValue());
                newAddData.setTrain(newTrain);

                newTrainAdditionalData.add(newAddData);
            });

            newTrain.setAdditionalData(newTrainAdditionalData);
            trainsDAO.update(newTrain);
        }

        return newTrainId;
    }
}
