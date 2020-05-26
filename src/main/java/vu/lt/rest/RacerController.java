package vu.lt.rest;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Racer;
import vu.lt.persistence.ClassesDAO;
import vu.lt.persistence.RacersDAO;
import vu.lt.rest.responses.RacerRequest;
import vu.lt.rest.responses.RacerResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/racers")
public class RacerController {
    @Inject
    @Getter
    @Setter
    private RacersDAO racersDAO;

    @Inject
    @Getter
    @Setter
    private ClassesDAO classesDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Racer racer = racersDAO.findOne(id);
        if (racer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        RacerResponse racerResponse = new RacerResponse();
        racerResponse.setLicensePlate(racer.getLicensePlate());
        racerResponse.setName(racer.getName());

        return Response.ok(racerResponse).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Integer id, RacerResponse racerResponse) {
        try {
            Racer racer = racersDAO.findOne(id);
            if (racer == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            racer.setName(racerResponse.getName());
            racer.setLicensePlate(racerResponse.getLicensePlate());
            racersDAO.update(racer);
            return Response.ok().build();
        } catch (OptimisticLockException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response add(RacerRequest racerToAdd) {
        Racer racer = new Racer();
        racer.setName(racerToAdd.getName());
        racer.setLicensePlate(racerToAdd.getLicensePlate());
        racer.setRacingClass(classesDAO.findOne(racerToAdd.getRacingClassId()));
        racersDAO.persist(racer);
        return Response.ok().build();
    }
}
