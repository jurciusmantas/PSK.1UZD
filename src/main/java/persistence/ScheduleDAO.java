package persistence;

import entities.Schedule;
import entities.Train;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class ScheduleDAO {

    @Inject
    private EntityManager em;

    public List<Schedule> loadAll() {
        return em.createNamedQuery("Schedule.findAll", Schedule.class).getResultList();
    }
}
