package persistence;

import entities.Train;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public class TrainsDAO implements Serializable {

    @Inject
    private EntityManager em;

    public void persist(Train train){
        this.em.persist(train);
    }

    public List<Train> loadAll() {
        return em.createNamedQuery("Trains.findAll", Train.class).getResultList();
    }

    public Train findOne(Integer id){
        return em.find(Train.class, id);
    }

    public Train update(Train train){
        return em.merge(train);
    }
}
