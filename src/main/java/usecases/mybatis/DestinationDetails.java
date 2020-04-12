package usecases.mybatis;

import lombok.Getter;
import mybatis.dao.DestinationsMapper;
import mybatis.model.Destinations;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class DestinationDetails implements Serializable {
    @Inject
    private DestinationsMapper destinationsMapper;

    @Getter
    private Destinations destination;

    @PostConstruct
    public void init() {
        this.loadDestination();
    }

    private void loadDestination(){
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer destinationId = Integer.parseInt(requestParameters.get("destinationId"));

        this.destination = destinationsMapper.selectByPrimaryKey(destinationId);
    }

    /*@Transactional
    public String update(){
        destinationsMapper.updateByPrimaryKey(this.destination);
        return "destinationsList";
    }*/
}
