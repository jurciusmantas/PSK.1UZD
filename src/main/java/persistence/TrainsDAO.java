package persistence;

import entities.Train;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class TrainsDAO {

    @Inject
    private EntityManager em;

    public void persist(Train train){
        this.em.persist(train);
    }

    public Train findOne(Integer id){
        return em.find(Train.class, id);
    }

    public Train update(Train train){
        return em.merge(train);
    }
}
