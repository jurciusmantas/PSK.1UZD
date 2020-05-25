package persistence;

import entities.Destination;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class DestinationsDAO {
    @Inject
    private EntityManager em;

    public Destination findOne(Integer id){
        return em.find(Destination.class, id);
    }

    public Destination update(Destination destination){
        return em.merge(destination);
    }

    public void flush(){ em.flush();}
}
