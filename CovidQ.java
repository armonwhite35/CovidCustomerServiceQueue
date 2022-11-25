package com.company;


public class CovidQ {

    private Link<Customer> head;
    private Link<Customer> tail;
    private Link<Customer> current;
    private int size;

    CovidQ(){
        clear();
    }

    public void clear() {
        tail = new Link(null, null);
        head = tail;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if (size == 0){
            return true;
        }
        return false;
    }

    public boolean enqueue(Customer eq) {//inserts each customer into the queue based on priority
        current = head.nextLink();


        if (eq.getPriority() == 3)
        {
            if (isEmpty()) {//if the queue is empty, adds the node to the tail of the list
                tail.setNextLink(new Link<Customer>(eq, null));
                tail = tail.nextLink();
                size++;
                return true;
            }
            outer: while (current.nextLink() != null){//if queue is not empty, adds node to end of the list
                if (current.nextLink() == null){
                    current.setNextLink(new Link<Customer>(current.element(), current.nextLink()));
                    current.setElement(eq);
                    size++;

                    tail = current;
                    break outer;
                }
            }
        }

        if (eq.getPriority() == 2)
        {
            if (isEmpty()){//if the queue is empty, adds the node to the tail of the list
                tail.setNextLink(new Link<Customer>(eq, null));
                tail = tail.nextLink();
                size++;
                return true;
            }
            //while queue is not empty, if the current customer's priority in the queue is less than 2 and the next pointer is null
            // (or the current customer's priority in the queue is less than 2 and the next customer's priority is 3), the customer
            //is placed accoridingly
            outer: while(current.nextLink() != null){
                if((current.element().getPriority() < 2 && current.nextLink() == null) ||
                        (current.element().getPriority() < 2 && current.nextLink().element().getPriority() == 3 )){
                    current.setNextLink(new Link<Customer>(current.element(), current.nextLink()));
                    current.setElement(eq);
                    size++;
                    if(current.nextLink() == null){
                        tail = current;
                        break outer;
                }
                }
                current = current.nextLink();
            }
            if (tail == current){//sets tail if it is equal to the last customer in the queue
                tail = current.nextLink();
            }
        }

        if (eq.getPriority() == 1)
        {
            if (isEmpty()){//if the queue is empty, adds the node to the tail of the list
                tail.setNextLink(new Link<Customer>(eq, null));
                tail = tail.nextLink();
                size++;
                return true;
            }
            outer: while(current.nextLink() != null){//if the current customer's priority equals 2, then places the enqueued customer right before it
                if (current.element().getPriority() == 2){
                    current.setNextLink(new Link<Customer>(current.element(), current.nextLink()));
                    current.setElement(eq);
                    size++;
                    break outer;
                }
                current = current.nextLink();//updates current if none of the if statements above are true
            }
        }
        return true;
    }

    public Customer dequeue() {//removes customer from the head of the list
        if (size ==0) {
            return null;
        }
        Customer removing = head.nextLink().element();
        head.setNextLink(head.nextLink().nextLink());
        if (head.nextLink() == null){
            tail = head;
        }
        size--;
        return removing;
    }

    public boolean minPassed(){//reduces the amount of time for each nurse they are with a customer
        if (head.nextLink() == null){
            return true;
        }
        current = head.nextLink();
        while (current.nextLink() != null){
            current.element().incrementTime();
            current = current.nextLink();
        }
        return true;
    }

}