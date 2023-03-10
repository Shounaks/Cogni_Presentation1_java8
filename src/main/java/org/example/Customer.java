package org.example;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
    private int id;
    private String name;
    private String gender;
    private String city;
}
