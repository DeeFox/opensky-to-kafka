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

    public Optional<OpenSkyStates> getFlights() {
        LOGGER.info("get flights");
        String response = openskyClient.getStates("ab1644,ab1640,88044b,7c6b2f");
        Optional<OpenSkyStates> states = Optional.empty();
        ObjectMapper mapper = new ObjectMapper();
        try {
            states = Optional.of(mapper.readValue(response, OpenSkyStates.class));
        } catch (IOException e) {
            LOGGER.fine(e.getMessage());
        }
        return states;
    }


}
