package com.company;

public class Link<E> {//makes a link for each link in the list
    private E node;
    private Link<E> pointer;

    Link(E el, Link pt){//assigns a node and pointer to another link
        node = el;
        pointer = pt;
    }

    E element(){
        return node;
    }

    E setElement(E elmt){
        node = elmt;
        return node;
    }

    Link<E> nextLink(){
        return pointer;
    }

    Link<E> setNextLink(Link<E> newlink){
        pointer = newlink;
        return pointer;
    }


}
