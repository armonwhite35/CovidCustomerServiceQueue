package com.company;


import java.util.PriorityQueue;

public class PQueue extends PriorityQueue<Customer> {

    public boolean add(Customer c){
        return super.add(c);
    }

    @Override
    public Customer poll() {
        return super.poll();
    }
}
