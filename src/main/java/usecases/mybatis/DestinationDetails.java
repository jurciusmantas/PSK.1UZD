package usecases.mybatis;

import lombok.Getter;
import mybatis.dao.DestinationsMapper;
import mybatis.model.Destinations;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class DestinationDetails {
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

    @Transactional
    public String update(){
        String res = "index.xhtml";
        res += "&faces-redirect=true";
        return res;
        /*String urlToReturn = "";
        if (this.destination == null)
            return "index.xhtml";

        try{
            destinationsMapper.updateByPrimaryKey(this.destination);
            urlToReturn = "/destinationsList.xhtml?faces-redirect=true";
        }
        catch (OptimisticLockException e) {
            urlToReturn = "/destinationDetails.xhtml?faces-redirect=true&destinationId=" + this.destination.getId() + "&error=optimistic-lock-exception";
        }

        if (urlToReturn == ""){
            if (this.destination == null)
                return "/destinationDetails.xhtml?destinationId=" + this.destination.getId() + "&faces-redirect=true";
            else
                return "/index.xhtml&faces-redirect=true";
        }

        return urlToReturn;*/
    }
}
