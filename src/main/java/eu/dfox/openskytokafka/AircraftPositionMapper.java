package eu.dfox.openskytokafka;

import eu.dfox.openskytokafka.opensky.StateVector;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.stream.Collectors;

@ApplicationScoped
public class AircraftPositionMapper {

    public AircraftPosition toAircraftPosition(StateVector stateVector) {
        AircraftPosition aircraftPosition = new AircraftPosition();
        aircraftPosition.setCallsign(stateVector.getCallsign());
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


}
