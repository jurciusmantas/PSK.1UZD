package services.statistics;

import interceptors.Logged;
import persistence.TrainsDAO;
import rest.contracts.StatisticsDTO;

import javax.inject.Inject;

public class SimpleStatisticsService implements IStatisticsService {
    @Inject
    private TrainsDAO trainsDAO;

    @Logged
    @Override
    public StatisticsDTO calculateStatistics() {
        StatisticsDTO statistics = new StatisticsDTO();
        statistics.setTrainCount(trainsDAO.loadAllCount());
        return statistics;
    }
}
