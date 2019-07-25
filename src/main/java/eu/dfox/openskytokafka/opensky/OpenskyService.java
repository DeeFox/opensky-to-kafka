package eu.dfox.openskytokafka.opensky;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

@ApplicationScoped
public class OpenskyService {

    private final static Logger LOGGER = Logger.getLogger(OpenskyService.class.getName());

    @Inject
    @RestClient
    OpenskyClient openskyClient;

    public Optional<OpenSkyStates> getFlights(String icao24List) {
        String response = openskyClient.getStates(icao24List);
        Optional<OpenSkyStates> states = Optional.empty();
        ObjectMapper mapper = new ObjectMapper();
        try {
            LOGGER.info(response);
            states = Optional.ofNullable(mapper.readValue(response, OpenSkyStates.class));
        } catch (IOException e) {
            LOGGER.fine(e.getMessage());
        }
        return states;
    }


}
