package eu.h2020.symbiote.messaging;

import com.rabbitmq.client.Channel;
import org.apache.catalina.servlet4preview.http.Mapping;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by Mael on 08/09/2016.
 */
public class MappingCreatedConsumer extends SymbioteMessageConsumer<Mapping>{

    private static Log log = LogFactory.getLog(MappingCreatedConsumer.class);

    /**
     * Constructs a new instance and records its association to the passed-in channel.
     *
     * @param channel the channel to which this consumer is attached
     */
    public MappingCreatedConsumer(Channel channel) {
        super(channel);
    }

    @Override
    protected void handleEventObject(Mapping deliveredObject) {
        log.warn("Mapping created consumer not implemented yet");
    }
}
