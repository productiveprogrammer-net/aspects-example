package net.productiveprogrammer.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RequestLoggerImpl implements RequestLogger {

    private static final Logger LOG = LoggerFactory.getLogger(RequestLoggerImpl.class);

    @Override
    public void log(final String message) {
        LOG.info(message);
    }
}
