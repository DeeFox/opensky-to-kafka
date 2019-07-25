package eu.dfox.openskytokafka.opensky;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Collection;

@JsonDeserialize(using = OpenSkyStatesDeserializer.class)
public class OpenSkyStates {
    private int time;
    private Collection<StateVector> flightStates;

    /**
     * @return The point in time for which states are stored
     */
    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * @return Actual states for this point in time
     */
    public Collection<StateVector> getStates() {
        if (flightStates == null || flightStates.isEmpty()) return null;
        return this.flightStates;
    }

    public void setStates(Collection<StateVector> states) {
        this.flightStates = states;
    }
}