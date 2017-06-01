package com.connexta.impl;

import com.connexta.api.Greeting;

public class GreetingImpl implements Greeting {
    @Override
    public String hello(String name) {
        return "Hello, " + name + "!";
    }
}
