package org.example;

import lombok.Getter;
import lombok.SneakyThrows;

import java.time.LocalDate;
import java.util.List;

@Getter
public class Repository {
    /**
     *  id, item, price, category
     * 	134, Moto24, 15000, Mobiles
     * 	157, Spiderman, 50, Books
     * 	204, Acer, 70000, Laptops
     * 	210, iPhone, 45000, Mobiles
     * 	215, Shirt, 1500, Shirts
     * */
    private final List<Item> items = List.of(
            Item.builder().id(134).name("Moto24").price(15000).category("Mobiles").build(),
            Item.builder().id(157).name("Spiderman").price(50).category("Books").build(),
            Item.builder().id(204).name("Acer").price(70000).category("Laptops").build(),
            Item.builder().id(210).name("iPhone").price(45000).category("Mobiles").build(),
            Item.builder().id(215).name("Shirt").price(1500).category("Shirts").build()
    );

    /**
     * id, name, gender, city
     * 	1, Tom, male, Bangalore
     * 	2, Mike, male, Delhi
     * 	3, Miley, female, Pune
     * 	4, Kunal, male, Delhi
     * 	5, Sakshi, female, Delhi
     * */
    private final List<Customer> customers = List.of(
            Customer.builder().id(1).name("Tom").gender("male").city("Bangalore").build(),
            Customer.builder().id(2).name("Mike").gender("male").city("Delhi").build(),
            Customer.builder().id(3).name("Miley").gender("female").city("Pune").build(),
            Customer.builder().id(4).name("Kunal").gender("male").city("Delhi").build(),
            Customer.builder().id(5).name("Sakshi").gender("female").city("Delhi").build()
    );

    /**
    * Orders
     * 	id, status, order-date, delivery date, items, customer
     * 	1, delivered, 12-Nov-2021, 14-Nov-2021, [134,204], 1
     * 	2, delivered, 19-May-2022, 24-May-2022, [204], 4
     * 	3, delivered, 31-Jul-2021, 04-Aug-2021, [210, 215], 2
     * 	4, pending, 29-Oct-2022, -, (134, 157, [204, 215], 3
     * 	5, pending, 30-Oct-2021, -, (134), 5
    * */
    private final List<Order> orders = List.of(
            Order.builder().id(1).status("delivered").orderDate(LocalDate.of(2021,11,12)).deliveryDate(LocalDate.of(2021,11,14)).items(List.of(getItemsWhereId(134),getItemsWhereId(204))).cus(getCustomersWhereId(1)).build(),
            Order.builder().id(2).status("delivered").orderDate(LocalDate.of(2022,5,19)).deliveryDate(LocalDate.of(2022,5,24)).items(List.of(getItemsWhereId(204))).cus(getCustomersWhereId(4)).build(),
            Order.builder().id(3).status("delivered").orderDate(LocalDate.of(2021,7,31)).deliveryDate(LocalDate.of(2021,8,4)).items(List.of(getItemsWhereId(210),getItemsWhereId(215))).cus(getCustomersWhereId(2)).build(),
            Order.builder().id(4).status("pending").orderDate(LocalDate.of(2022,10,29)).deliveryDate(null).items(List.of(getItemsWhereId(134),getItemsWhereId(157),getItemsWhereId(204),getItemsWhereId(215))).cus(getCustomersWhereId(3)).build(),
            Order.builder().id(5).status("pending").orderDate(LocalDate.of(2021,10,30)).deliveryDate(null).items(List.of(getItemsWhereId(134))).cus(getCustomersWhereId(5)).build()
    );

    //IDs will always be present if the data is correct.
    @SneakyThrows
    private Item getItemsWhereId(int id){
        return items.stream().filter(x -> x.getId() == id).findFirst().orElseThrow(() -> new Exception("Item Data is Invalid"));
    }

    @SneakyThrows
    private Customer getCustomersWhereId(int id){
        return customers.stream().filter(x -> x.getId() == id).findFirst().orElseThrow(() -> new Exception("Customer Data is Invalid"));
    }
}
