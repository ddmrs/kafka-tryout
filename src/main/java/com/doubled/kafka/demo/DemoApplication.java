package com.doubled.kafka.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.config.ContainerProperties;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@SpringBootApplication
public class DemoApplication {
    @Autowired
    private Consumer consumer;

    @PostConstruct
    public void blaat() {
        System.out.println("I got called " + consumer);

//        ContainerProperties containerProps = new ContainerProperties("donations-single");
//        containerProps.setMessageListener(new MyList("cons1"));
//        KafkaMessageListenerContainer<String, String> container = consumer.createContainer(containerProps, "group1");
//        container.setBeanName("testAuto");
//        container.start();

        ContainerProperties props2 = new ContainerProperties("donations-single");
        props2.setMessageListener(new MyList("cons2"));
        KafkaMessageListenerContainer<String, String> cont2 = consumer.createContainer(props2, "group3");
        cont2.setBeanName("testAuto2");
        cont2.start();
        System.out.println("Started listening");
    }

    private class MyList implements MessageListener<String, String> {
        private final String name;

        public MyList(String name) {
            this.name = name;
        }

        @Override
        public void onMessage(ConsumerRecord<String, String> message) {
            System.out.println(name + " received: " + message);
            Donation donation = new Donation(message.key(), new BigDecimal(message.value()));
            donation.setConsumer(name);
            Store.getInstance().addItem(donation);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}


