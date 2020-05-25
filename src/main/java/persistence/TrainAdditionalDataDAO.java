package persistence;

import enums.TrainAdditionalDataType;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class TrainAdditionalDataDAO {
    @Inject
    private EntityManager em;

    public int getTrainCountByType(TrainAdditionalDataType type){
        var query = em.createNamedQuery("TrainAdditionalData.getTrainCountByType");
        return ((Number)query.setParameter("type", type).getSingleResult()).intValue();
    }
}
