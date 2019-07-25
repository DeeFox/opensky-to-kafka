package eu.dfox.openskytokafka;

import eu.dfox.openskytokafka.opensky.OpenSkyStates;
import eu.dfox.openskytokafka.opensky.OpenskyService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@ApplicationScoped
public class AircraftPositionService {

    private static final String ICAO24_LIST = "ab1644,ab1640,88044b,7c6b2f,7c35e8,aa8c39,7c6b2d,88044f,7c6b41";

    @Inject
    OpenskyService openskyService;

    @Inject
    AircraftPositionMapper aircraftPositionMapper;

    public Collection<AircraftPosition> getAircraftPositions() {
        Optional<OpenSkyStates> states = openskyService.getFlights(ICAO24_LIST);
        if (!states.isPresent() || states.get().getStates() == null) {
            return Collections.EMPTY_LIST;
        }
        return aircraftPositionMapper.toAircraftPositions(states.get().getStates());
    }

}
