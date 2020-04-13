package usecases.mybatis;

import lombok.Getter;
import lombok.Setter;
import mybatis.dao.DestinationsMapper;
import mybatis.model.Destinations;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class DestinationsList {
    @Inject
    private DestinationsMapper destinationsMapper;

    @Getter
    private List<Destinations> allDestinations;

    @Getter @Setter
    private Destinations destToCreate = new Destinations();

    @PostConstruct
    public void init() {
        this.loadAllDestinations();
    }

    private void loadAllDestinations() {
        this.allDestinations = destinationsMapper.selectAll();
    }

    @Transactional
    public String createDestination() {
        destinationsMapper.insert(destToCreate);
        return "/destinationDetails?destinationId=" + destToCreate.getId() + "&faces-redirect=true";
    }
}
