package eu.dfox.openskytokafka.opensky;

import eu.dfox.openskytokafka.opensky.OpenSkyStates;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@RegisterRestClient(baseUri = "https://opensky-network.org/api/")
public interface OpenskyClient {

    @GET
    @Path("states/all")
    @Produces("application/json")
    String getStates(@QueryParam(value = "icao24") String icao24);

}