package services.trainService;

import entities.Train;
import entities.TrainAdditionalData;
import entities.TrainAdditionalDataKey;
import interceptors.Logged;
import lombok.Getter;
import lombok.Setter;
import persistence.TrainsDAO;
import rest.contracts.TrainAdditionalDataDto;
import rest.contracts.TrainsDto;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TrainsService implements ITrainService {
    @Inject
    @Setter
    @Getter
    private TrainsDAO trainsDAO;

    @Override
    public TrainsDto getTrainById(Integer id) {
        Train train = trainsDAO.findOne(id);
        if (train == null)
            return null;

        TrainsDto trainsDto = new TrainsDto();
        trainsDto.setName(train.getName());

        List<TrainAdditionalData> trainAdditionalData = train.getAdditionalData();
        if (trainAdditionalData != null && trainAdditionalData.size() > 0){
            List<TrainAdditionalDataDto> trainAdditionalDataDto = new ArrayList<>();

            trainAdditionalData.forEach(data -> {
                TrainAdditionalDataDto toAdd = new TrainAdditionalDataDto();
                toAdd.setType(data.getId().getType());
                toAdd.setValue(data.getValue());
                trainAdditionalDataDto.add(toAdd);
            });

            trainsDto.setAdditionalData(trainAdditionalDataDto);
        }

        return trainsDto;
    }

    @Override
    @Logged
    public Integer createTrain(TrainsDto train) {
        try {
            Train newTrain = new Train();
            newTrain.setName(train.getName());

            trainsDAO.persist(newTrain);
            return newTrain.getId();
        }
        catch(Exception exc){
            return -1;
        }
    }
}
