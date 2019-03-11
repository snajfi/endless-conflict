package cz.endless.conflict.producers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * Created by snajfi1 on 18.12.2017.
 */
public class LoggerProduces {

    @Produces
    public Logger getLogger(InjectionPoint p) {
        return LogManager.getLogger(p.getClass().getCanonicalName());
    }

}
