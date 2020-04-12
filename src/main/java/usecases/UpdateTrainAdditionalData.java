package usecases;

import entities.TrainAdditionalData;
import entities.Train;
import enums.TrainAdditionalDataType;
import lombok.Getter;
import lombok.Setter;
import persistence.TrainsDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ViewScoped
@Named
@Getter @Setter
public class UpdateTrainAdditionalData implements Serializable {
    private Train train;
    private TrainAdditionalData newAddData;
    private List<TrainAdditionalData> addDataToUpdate;
    private TrainAdditionalDataType[] addDataTypes = TrainAdditionalDataType.values();
    private Integer addDataToUpdateIndex;
    private String addDataToUpdateValue;

    @Inject
    private TrainsDAO trainsDAO;

    @PostConstruct
    private void init() {
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer trainId = Integer.parseInt(requestParameters.get("trainId"));
        this.train = trainsDAO.findOne(trainId);
        this.addDataToUpdate = this.train.getAdditionalData();
        this.newAddData = new TrainAdditionalData(this.train);
    }

    @Transactional
    public String addNewAdditionalData(){
        try {
            var allAddData = this.train.getAdditionalData();
            allAddData.add(newAddData);
            trainsDAO.update(this.train);
        } catch (OptimisticLockException e) {
            return "/trainDetails.xhtml?faces-redirect=true&playerId=" + this.train.getId() + "&error=optimistic-lock-exception";
        }
        return "trainDetails.xhtml?trainId=" + this.train.getId() + "&faces-redirect=true";
    }

    @Transactional
    public String update(){
        try {
            this.trainsDAO.update(this.train);
        } catch (OptimisticLockException e) {
            return "/trainDetails.xhtml?faces-redirect=true&playerId=" + this.train.getId() + "&error=optimistic-lock-exception";
        }
        return "trainDetails.xhtml?trainId=" + this.train.getId() + "&faces-redirect=true";
    }
}
