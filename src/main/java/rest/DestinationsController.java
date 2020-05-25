package rest;

import persistence.DestinationsDAO;
import rest.contracts.DestinationsDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/destinations")
public class DestinationsController {
    //@Inject
    //private IDestinationsService destinationsService;

    @Inject
    private DestinationsDAO destinationsDAO;

    @Path("/update/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") int id, DestinationsDTO destination) throws InterruptedException {
        var destinationInDb = destinationsDAO.findOne(id);
        if (destinationInDb == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        destinationInDb.setDistanceFromStation(destination.getDistanceFromStation());
        destinationInDb.setName(destination.getName());
        destination.setId(destinationInDb.getId());

        //For optimistic lock exception
        if (destination.getDistanceFromStation() == 0){
            Thread.sleep(6000);
        }

        try{
            destinationsDAO.update(destinationInDb);
            destinationsDAO.flush();
            return Response.ok(destination).build();
        }
        catch (OptimisticLockException ole){
            System.out.println("MYPREFIX - Optimistic lock exception caught!");
            return Response.status(Response.Status.CONFLICT).entity(ole.getEntity()).build();
        }
    }
}
