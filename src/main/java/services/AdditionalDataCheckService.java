package services;

import entities.Train;
import entities.TrainAdditionalData;
import enums.TrainAdditionalDataType;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AdditionalDataCheckService implements Serializable {
    private static final TrainAdditionalDataType[] EMPTY = {};

    public TrainAdditionalDataType[] availableAddDataTypes(Train t){
        if (t == null)
            return EMPTY;

        var allTypes = TrainAdditionalDataType.values();
        var allAddData = t.getAdditionalData();
        var typesList = new ArrayList<TrainAdditionalDataType>();

        for(var i = 0; i < allTypes.length; i++){
            if (!containsType(allAddData, allTypes[i]))
                typesList.add(allTypes[i]);
        }

        TrainAdditionalDataType[] res = new TrainAdditionalDataType[typesList.size()];
        res = typesList.toArray(res);

        return res;
    }

    private boolean containsType(final List<TrainAdditionalData> list, final TrainAdditionalDataType type){
        return list.stream().filter(o -> o.getId().getType().equals(type)).findFirst().isPresent();
    }
}
