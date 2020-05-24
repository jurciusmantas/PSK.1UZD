package rest;

import lombok.Getter;
import lombok.Setter;
import rest.contracts.TrainsDto;
import services.trainService.ITrainService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/trains")
public class TrainsController {

    @Inject
    @Setter
    @Getter
    private ITrainService trainsService;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final int id) {
        TrainsDto train = trainsService.getTrainById(id);
        if (train == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(train).build();
    }

    @Path("/update/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final int id, TrainsDto train) {
        boolean success = trainsService.updateTrain(id, train);
        if (success)
            return Response.status(Response.Status.OK).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(TrainsDto train) {
        Integer newTrainId = trainsService.createTrain(train);
        if (newTrainId != -1)
            return Response.ok(Response.Status.OK).build();
        else
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
