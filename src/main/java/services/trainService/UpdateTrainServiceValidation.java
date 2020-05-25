package services.trainService;

import interceptors.Logged;
import rest.contracts.TrainsDTO;
import javax.enterprise.inject.Specializes;

@Specializes
public class UpdateTrainServiceValidation extends TrainsService {

    @Logged
    @Override
    public boolean updateTrain(int id, TrainsDTO train) {

        if (id < 1 || train == null)
            return false;

        String trainName = train.getName();
        if (trainName == null || trainName.length() <= 3)
            return false;

        return super.updateTrain(id, train);
    }

    @Logged
    @Override
    public int createTrain(TrainsDTO train) {
        if (train == null)
            return -1;

        String trainName = train.getName();
        if (trainName == null || trainName.length() <= 3)
            return -1;

        return super.createTrain(train);
    }
}
