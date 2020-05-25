package services.trainService;

import entities.Train;
import entities.TrainAdditionalData;
import interceptors.Logged;
import persistence.TrainsDAO;
import rest.contracts.TrainAdditionalDataDTO;
import rest.contracts.TrainsDTO;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TrainsService implements ITrainService {
    @Inject
    private TrainsDAO trainsDAO;

    @Logged
    @Override
    public TrainsDTO getTrainById(Integer id) {
        Train train = trainsDAO.findOne(id);
        if (train == null)
            return null;

        TrainsDTO trainsDto = new TrainsDTO();
        trainsDto.setName(train.getName());

        List<TrainAdditionalData> trainAdditionalData = train.getAdditionalData();
        if (trainAdditionalData != null && trainAdditionalData.size() > 0){
            List<TrainAdditionalDataDTO> trainAdditionalDataDto = new ArrayList<>();

            trainAdditionalData.forEach(data -> {
                TrainAdditionalDataDTO toAdd = new TrainAdditionalDataDTO();
                toAdd.setType(data.getId().getType());
                toAdd.setValue(data.getValue());
                trainAdditionalDataDto.add(toAdd);
            });

            trainsDto.setAdditionalData(trainAdditionalDataDto);
        }

        return trainsDto;
    }

    @Logged
    @Override
    public int createTrain(TrainsDTO train) {
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

    @Logged
    @Override
    public boolean updateTrain(int id, TrainsDTO train) {
        Train trainToUpdate = trainsDAO.findOne(id);
        if (trainToUpdate != null){
            trainToUpdate.setName(train.getName());
            trainsDAO.update(trainToUpdate);
            return true;
        }

        return false;
    }
}
