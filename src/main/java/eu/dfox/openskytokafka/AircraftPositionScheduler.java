package eu.dfox.openskytokafka;

import io.quarkus.scheduler.Scheduled;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.logging.Logger;

@ApplicationScoped
public class AircraftPositionScheduler {

    private final static Logger LOGGER = Logger.getLogger(AircraftPositionScheduler.class.getName());

    @Inject
    AircraftPositionService aircraftPositionService;

    @Scheduled(every = "60s")
    void requestPositions() {
        LOGGER.info("request positions");
        LOGGER.info(aircraftPositionService.getAircraftPositions().toString());
    }

}
