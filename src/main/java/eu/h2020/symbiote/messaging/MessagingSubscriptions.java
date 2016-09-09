package eu.h2020.symbiote.messaging;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Mael on 07/09/2016.
 */
public class MessagingSubscriptions {

    private static String PLATFORM_CREATED_QUEUE = "PlatformCreated";
    private static String MODEL_CREATED_QUEUE = "ModelCreated";
    private static String MAPPING_CREATED_QUEUE = "MappingCreated";

    private static Log log = LogFactory.getLog(MessagingSubscriptions.class);

    public static void subscribeForSearchService() throws IOException, TimeoutException {
        subscribePlatformCreated(PLATFORM_CREATED_QUEUE);
        subscribeModelCreated(MODEL_CREATED_QUEUE);
        subscribeMappingCreated(MAPPING_CREATED_QUEUE);
    }

    public static void subscribePlatformCreated( String queueName ) throws IOException, TimeoutException {
        Channel channel = getChannel(queueName);
        PlatformCreatedConsumer consumer = new PlatformCreatedConsumer(channel);
        channel.basicConsume(queueName, true, consumer);
    }

    public static void subscribeModelCreated( String queueName ) throws IOException, TimeoutException {
        Channel channel = getChannel(queueName);
        ModelCreatedConsumer consumer = new ModelCreatedConsumer(channel);
        channel.basicConsume(queueName, true, consumer);
    }

    public static void subscribeMappingCreated( String queueName ) throws IOException, TimeoutException {
        Channel channel = getChannel(queueName);
        MappingCreatedConsumer consumer = new MappingCreatedConsumer(channel);
        channel.basicConsume(queueName, true, consumer);
    }


    private static Channel getChannel( String queueName ) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(queueName, false, false, false, null);
        log.info("Receiver waiting for messages....");
        return channel;
    }
}
