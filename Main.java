package com.company;

/**
 Armon White
 CSC201 Spring 2021
 Program Assignment 2
 March 16 2021
 **/

public class Main {

    public static void main(String[] args) {
        double p; //probability of arrival during given interval
        int numNurses; //number of nurses giving shots
        int maxService; //max amt o time to get shot
        int runtime; //num of minutes simulation will run

        p = Double.parseDouble(args[0]);
        numNurses = Integer.parseInt(args[1]);
        maxService = Integer.parseInt(args[2]);
        runtime = Integer.parseInt(args[3]);

        int averageQLength = 0;
        int maxQLength = 0;
        int timeOfMaxQLength = 0;
        int maxWaitTime = 0;
        double averageWaitTime = 0.0;
        int numCustomers = 0;
        int srvcTime = 0;

        Nurse ns[] = new Nurse[numNurses];  //initiates array of nurses
        for (int i = 0; i < numNurses; i++){
            Nurse n = new Nurse();
            ns[i] = n;
        }


        CovidQ priorityQ = new CovidQ();
        PQueue pQ = new PQueue();
        for (int i = 1; i <= runtime; i++) { //starts time and repeats until max runtime is reached
            System.out.println("Minute " + i);
            double r = Math.random(); //checks to see if a customer comes in based on the probability; then creates new customer with attributes
            if (r >= p) {
                numCustomers++;
                srvcTime = (int) ((Math.random() * maxService) + 1);
                Customer nc = new Customer(numCustomers, srvcTime);
                System.out.println("Customer " + numCustomers + " with priority " + nc.getPriority() + " is arriving in the queue");
                System.out.println("The customer will require " + srvcTime + " minutes of service");
                priorityQ.enqueue(nc);
                pQ.add(nc);

                outer: for (int j = 0; j < ns.length ; j++) { //dequeues and updates/retrieves customer's wait time, avg wait time, and max wait time
                    if (priorityQ.isEmpty()){
                        break outer;
                    }
                    if (ns[j].isFree()) {
                        Customer custRemoved = priorityQ.dequeue();
                        Customer cR = pQ.poll();

                        int wt = custRemoved.getWaitTime(); //gets max wait time and records average wait time //crust removed is also a possable issue
                        if(wt > maxWaitTime){
                            maxWaitTime = wt;
                        }
                        averageWaitTime += wt;

                        ns[j].newCust(custRemoved);
                        System.out.println("Customer " + custRemoved.getCustomerNumber() + " goes to nurse " + (j + 1));
                        System.out.println("This customer had to wait for " + custRemoved.getWaitTime() + " minutes");
                    }
                }
            }


            averageQLength += priorityQ.size();
            if (priorityQ.size() > maxQLength) {//updates max length of queue and time of max length
                timeOfMaxQLength = i;
                maxQLength = priorityQ.size();
            }
            priorityQ.minPassed(); //should update the wait time of each customer in the queue
            for (int k = 0; k < ns.length ; k++){//decrements the amount of time each customer is with each nurse
                ns[k].decrementTime();
            }
        }
        System.out.println();
        System.out.println("Avg. Queue Length: " + (averageQLength/runtime));
        System.out.println("Max Queue Length: " + maxQLength + " at " + timeOfMaxQLength + " minutes");
        System.out.println("Max Wait Time: " + maxWaitTime);
        System.out.println("Average Wait time:  " + (averageWaitTime / numCustomers));

    }
}
