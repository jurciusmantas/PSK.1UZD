package usecases.mybatis;

import interceptors.Logged;
import lombok.Getter;
import lombok.Setter;
import mybatis.dao.DestinationsMapper;
import mybatis.model.Destinations;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Logged
@Named
@SessionScoped
public class DestinationsList implements Serializable {
    @Inject
    private DestinationsMapper destinationsMapper;
    private CompletableFuture<List<Destinations>> destinationsLoaderTask = null;

    @Getter
    private List<Destinations> allDestinations;

    @Getter @Setter
    private Destinations destToCreate = new Destinations();


    public String startLoading(){
        destinationsLoaderTask = CompletableFuture.supplyAsync(() -> this.loadAllDestinations());
        return "destinationsListLoader.xhtml?faces-redirect=true";
    }

    public void getDestinationLoaderTaskStatus() throws IOException {
        if (destinationsLoaderTask == null || destinationsLoaderTask.isDone()){
            FacesContext.getCurrentInstance().getExternalContext().redirect("destinationsList.xhtml?faces-redirect=true");
        }
    }

    private List<Destinations> loadAllDestinations() {
        try{
            Thread.sleep(5000);
        }
        catch (InterruptedException ignored){
        }
        this.allDestinations = destinationsMapper.selectAll();

        return this.allDestinations;
    }

    @Transactional
    public String createDestination() {
        destinationsMapper.insert(destToCreate);
        return "/destinationDetails?destinationId=" + destToCreate.getId() + "&faces-redirect=true";
    }
}
