package com.doubled.kafka.demo;

import java.util.*;

public class Store {
    private static Store ourInstance = new Store();

    public static Store getInstance() {
        return ourInstance;
    }

    private List<Donation> donations = new ArrayList<>();
    private Store() {
    }

    public void addItem(Donation donation){
        donations.add(donation);
    }

    public List<Donation> allDonations(){
        return donations;
    }
}
