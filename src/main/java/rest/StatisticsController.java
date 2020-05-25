package rest;

import rest.contracts.StatisticsDTO;
import rest.contracts.TrainsDTO;
import services.statistics.IStatisticsService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/statistics")
public class StatisticsController {
    @Inject
    private IStatisticsService statisticsService;

    @Path("/calculate")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response calculate() {
        StatisticsDTO statistics = statisticsService.calculateStatistics();
        if (statistics == null)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

        return Response.ok(statistics).build();
    }
}
