package com.telran;

public class StringBuilderVsStringBuffer {

    public static void main(String[] args) {

        //Классы, предназначенные для работы в многопоточной среде называются ThreadSafe классами
        //StringBuffer - thread safe class
        //StringBuilder - is not thread safe

        //      Product
        //Customer 1, Customer 2

        //Order (customer 1)
        //Order (customer 2)

        //Account 1 (customer 1) -> Account 2 (organization)
        //Account 1 (customer 2) -> Account 2 (organization)
        //Account 1 (customer 3) -> Account 2 (organization)


    }
}


