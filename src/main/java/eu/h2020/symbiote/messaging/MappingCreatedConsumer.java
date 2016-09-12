package eu.h2020.symbiote.messaging;

import com.rabbitmq.client.Channel;
import eu.h2020.symbiote.messaging.model.Mapping;
import eu.h2020.symbiote.search.SearchStorage;
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
        log.info("Search received message about created mapping " + deliveredObject.getId());
        SearchStorage.getInstance().registerMapping(deliveredObject);
    }
}
