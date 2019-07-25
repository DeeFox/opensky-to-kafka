package eu.dfox.openskytokafka;

import io.reactivex.Flowable;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@ApplicationScoped
public class AircraftPositionScheduler {

    private final static Logger LOGGER = Logger.getLogger(AircraftPositionScheduler.class.getName());


    @Inject
    AircraftPositionMapper mapper;

    @Inject
    AircraftPositionService aircraftPositionService;

    @Outgoing("positions")
    public Flowable<String> generate() {
        LOGGER.info("generate called");
        return Flowable.interval(10, TimeUnit.SECONDS)
                       .flatMapIterable(tick -> aircraftPositionService.getAircraftPositions())
                       .map(aircraftPosition -> mapper.toJsonString(aircraftPosition))
                       .map(json -> {
                           LOGGER.info(json);
                           return json;
                       });
    }

}
