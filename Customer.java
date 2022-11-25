package com.company;

public class Customer implements Comparable<Customer>{
    private int customerNumber;
    private int priority;
    private int waitTime;
    private int shotTime;

    Customer(int customerNum, int st){//initializes customer and assigns with shot time, number of customer, priority, and default wait time
        customerNumber = customerNum;
        shotTime = st;
        priority = (int)(Math.random() * 3) + 1;
        waitTime = 0;
    }

    @Override
    public int compareTo(Customer c) {
        if (this.priority > c.priority){
            return 1;
        }
        if (this.priority < c.priority){
            return -1;
        }
        return 0;
    }

    public int getCustomerNumber(){
        return customerNumber;
    }

    public int getPriority(){
        return priority;
    }

    public int getWaitTime(){
        return waitTime;
    }

    public int getShotTime(){
        return shotTime;
    }

    public void incrementTime(){
        waitTime++;
    }
}
