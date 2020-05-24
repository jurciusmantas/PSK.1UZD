package services.trainService;

import entities.Train;
import entities.TrainAdditionalData;
import entities.TrainAdditionalDataKey;
import persistence.TrainsDAO;
import rest.contracts.TrainAdditionalDataDto;
import rest.contracts.TrainsDto;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Decorator
public abstract class TrainServiceDecorator implements ITrainService {
    @Inject
    private TrainsDAO trainsDAO;

    @Inject
    @Delegate
    @Any
    private ITrainService trainService;

    public Integer createTrain(TrainsDto train){
        Integer newTrainId = trainService.createTrain(train);

        List<TrainAdditionalDataDto> trainAddData = train.getAdditionalData();
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
