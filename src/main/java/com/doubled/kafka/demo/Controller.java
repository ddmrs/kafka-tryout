package com.doubled.kafka.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class Controller {
    private static List<String> CONTRIBUTORS = Stream.of("Dries", "Jens", "Sven", "Marijn","Anthony").collect(Collectors.toList());
    private static final int MAX_AMOUNT = 1000;
    private final Random contRandom = new Random();
    private final Random amtRandom = new Random();
    private final KafkaTemplate<String, String> template;

    @Autowired
    public Controller(Producer producer) {
        this.template = producer.createTemplate();
    }

    @RequestMapping(path = "/donate", method = RequestMethod.POST)
    public Donation donateMoney(){
        String contributor = CONTRIBUTORS.get(contRandom.nextInt(CONTRIBUTORS.size()));
        BigDecimal amount = BigDecimal.valueOf(amtRandom.nextInt(MAX_AMOUNT));
        System.out.println("Sending €" + amount + " from '" + contributor + "'");
        template.send("donations-single", contributor, amount.toString());
        return new Donation(contributor, amount);
    }

    @RequestMapping(path = "/listAll", method = RequestMethod.GET)
    public List<Donation> getAll(){
        return Store.getInstance().allDonations();
    }
}
