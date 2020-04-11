package usecases;

import entities.Train;
import lombok.Getter;
import lombok.Setter;
import persistence.TrainsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Model
public class Trains {

    @Inject
    private TrainsDAO trainsDAO;

    @Getter @Setter
    private Train trainToCreate = new Train();

    @Getter
    private List<Train> allTrains;

    @PostConstruct
    public void init(){
        loadAllTrains();
    }

    @Transactional
    public String createTrain(){
        this.trainsDAO.persist(trainToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllTrains(){
        this.allTrains = trainsDAO.loadAll();
    }
}
