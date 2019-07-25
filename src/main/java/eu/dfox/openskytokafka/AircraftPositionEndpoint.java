package eu.dfox.openskytokafka;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/positions")
public class AircraftPositionEndpoint {

    @Inject
    AircraftPositionService aircraftPositionService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {
        return Response.ok(aircraftPositionService.getAircraftPositions()).build();
    }
}