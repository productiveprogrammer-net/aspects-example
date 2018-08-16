package net.productiveprogrammer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HelloMessage {

    private final String name;
    private final int age;

    public HelloMessage(@JsonProperty("name") final String name,
                        @JsonProperty("age") final int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
