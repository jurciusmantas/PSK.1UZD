package services.statistics;

import enums.TrainAdditionalDataType;
import interceptors.Logged;
import persistence.TrainAdditionalDataDAO;
import persistence.TrainsDAO;
import rest.contracts.StatisticsDTO;
import rest.contracts.TrainAdditionalDataDTO;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.util.ArrayList;

@Alternative
public class AdvancedStatisticsService implements IStatisticsService {
    @Inject
    private TrainsDAO trainsDAO;

    @Inject
    private TrainAdditionalDataDAO trainAdditionalDataDAO;

    @Logged
    @Override
    public StatisticsDTO calculateStatistics() {
        StatisticsDTO statistics = new StatisticsDTO();
        statistics.setTrainCount(trainsDAO.loadAllCount());

        var allTypes = TrainAdditionalDataType.values();
        var typeStatistics = new ArrayList<TrainAdditionalDataDTO>();
        for(var i = 0; i < allTypes.length; i++){
            TrainAdditionalDataDTO dto = new TrainAdditionalDataDTO();
            dto.setType(allTypes[i]);
            dto.setValue(String.valueOf(trainAdditionalDataDAO.getTrainCountByType(allTypes[i])));
            typeStatistics.add(dto);
        }
        statistics.setTrainCountByType(typeStatistics);
        return statistics;
    }
}
