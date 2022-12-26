package org.example;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data

@Builder
public class Order {
    private int id;
    private String status;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private List<Item> items;
    private Customer cus;
}
