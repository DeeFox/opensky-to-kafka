package eu.dfox.openskytokafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.dfox.openskytokafka.opensky.StateVector;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.stream.Collectors;

@ApplicationScoped
public class AircraftPositionMapper {

    private ObjectMapper objectMapper = new ObjectMapper();

    public AircraftPosition toAircraftPosition(StateVector stateVector) {
        AircraftPosition aircraftPosition = new AircraftPosition();
        aircraftPosition.setCallsign(stateVector.getCallsign().trim());
        aircraftPosition.setHeading(stateVector.getHeading());
        aircraftPosition.setIcao24(stateVector.getIcao24());
        aircraftPosition.setLatitude(stateVector.getLatitude());
        aircraftPosition.setLongitude(stateVector.getLongitude());
        aircraftPosition.setVelocity(stateVector.getVelocity());
        aircraftPosition.setOnGround(stateVector.isOnGround());
        return aircraftPosition;
    }

    public Collection<AircraftPosition> toAircraftPositions(Collection<StateVector> stateVectors) {
        return stateVectors.stream()
                           .map(stateVector -> toAircraftPosition(stateVector))
                           .collect(Collectors.toList());
    }

    public String toJsonString(AircraftPosition position) {
        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(position);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }


}
