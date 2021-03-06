package usecases.jpa;

import lombok.Getter;
import lombok.Setter;
import persistence.ScheduleDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Schedule {

    @Inject
    private ScheduleDAO scheduleDAO;

    @Getter @Setter
    private entities.Schedule scheduleToCreate = new entities.Schedule();

    @Getter
    private List<entities.Schedule> fullSchedule;

    @PostConstruct
    public void init(){
        loadSchedule();
    }

    private void loadSchedule(){
        this.fullSchedule = scheduleDAO.loadAll();
    }
}
