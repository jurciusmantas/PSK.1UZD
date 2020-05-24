package services.trainService;

import interceptors.Logged;
import rest.contracts.TrainsDto;
import javax.enterprise.inject.Specializes;

@Logged
@Specializes
public class UpdateTrainServiceValidation extends TrainsService {

    @Override
    public boolean updateTrain(int id, TrainsDto train) {
        System.out.println("UpdateTrainServiceValidation - updateTrain");

        if (id < 1 || train == null)
            return false;

        String trainName = train.getName();
        if (trainName == null || trainName.length() <= 3)
            return false;

        return super.updateTrain(id, train);
    }

    @Override
    public int createTrain(TrainsDto train) {
        System.out.println("UpdateTrainServiceValidation - createTrain");
        if (train == null)
            return -1;

        String trainName = train.getName();
        if (trainName == null || trainName.length() <= 3)
            return -1;

        return super.createTrain(train);
    }
}
