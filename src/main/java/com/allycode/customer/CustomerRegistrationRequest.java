package com.allycode.customer;

public record CustomerRegistrationRequest(
    String name,
    String email,
    Integer age
) {
}
