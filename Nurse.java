package com.company;

public class Nurse {
    private int timeSrvce;
    private boolean isFree;

    Nurse(){//initialize each nurse to be free/unoccupied
        isFree = true;
        timeSrvce = 0;
    }

    public boolean isFree(){
        return isFree;
    }

    public void decrementTime(){//decrements time any nurse is with a customer and makes nurse free if the customer is done
        if (timeSrvce > 0){
            timeSrvce--;
        }
        if (timeSrvce == 0){
            isFree = true;
        }
    }

    public void newCust(Customer cmr){//gives nurse a customer
        timeSrvce = cmr.getShotTime();
        isFree = false;
    }

}
