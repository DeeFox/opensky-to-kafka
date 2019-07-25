package eu.dfox.openskytokafka;

import eu.dfox.openskytokafka.opensky.OpenSkyStates;
import eu.dfox.openskytokafka.opensky.OpenskyService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/positions")
public class AircraftPositionEndpoint {

    @Inject
    OpenskyService openskyService;

    @Inject
    AircraftPositionMapper aircraftPositionMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {

        Optional<OpenSkyStates> states = openskyService.getFlights();

        if (!states.isPresent() || states.get().getStates() == null) {
            return Response.noContent().build();
        }

        return Response.ok(
            aircraftPositionMapper.toAircraftPositions(states.get().getStates())
        ).build();
    }
}