package eu.h2020.symbiote.messaging;

import com.rabbitmq.client.Channel;
import eu.h2020.symbiote.messaging.model.OntologyModel;
import eu.h2020.symbiote.search.SearchStore;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by Mael on 08/09/2016.
 */
public class ModelCreatedConsumer extends SymbioteMessageConsumer<OntologyModel>{

    private static Log log = LogFactory.getLog(ModelCreatedConsumer.class);

    /**
     * Constructs a new instance and records its association to the passed-in channel.
     *
     * @param channel the channel to which this consumer is attached
     */
    public ModelCreatedConsumer(Channel channel) {
        super(channel);
    }

    @Override
    protected void handleEventObject(OntologyModel deliveredObject) {
        log.info("Search received message about created model " + deliveredObject.getId());
        SearchStore.getSingleton().registerModel(deliveredObject);
    }

}
