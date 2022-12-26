package org.example;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class Main {
    static Repository dataRepo = new Repository();
    static Predicate<Item> isItemMobile = item -> item.getCategory().equals("Mobiles");

    public static void main(String[] args) {
        System.out.println("1: Obtain a list of items belongs to category 'Mobiles':");
        List<Item> mobiles = dataRepo.getItems().stream().filter(isItemMobile).collect(Collectors.toList());
        mobiles.forEach(System.out::println);
        System.out.println();

        System.out.println("2: Obtain a list of items belongs to category 'Mobiles' and price is greater than 20000");
        List<Item> mobileOver20000 = dataRepo.getItems().stream().filter(isItemMobile).filter(x -> x.getPrice() > 20000).collect(Collectors.toList());
        mobileOver20000.forEach(System.out::println);
        System.out.println();

        System.out.println("3: Obtain a list of order with items category is not 'Mobiles'");
        List<Order> notMobilesOrder = dataRepo.getOrders().stream()
                .filter(x -> x.getItems().stream().noneMatch(isItemMobile))
                .collect(Collectors.toList());
        notMobilesOrder.forEach(System.out::println);
        System.out.println();

        System.out.println("4: Obtain a list of items with category “Laptops” and then apply 5% discount");
        UnaryOperator<Item> applyDiscount = x ->  {x.setPrice(x.getPrice()*0.95);return x;};
        List<Item> laptops = dataRepo.getItems().stream().filter(x -> x.getCategory().equals("Laptops")).map(applyDiscount).collect(Collectors.toList());
        laptops.forEach(System.out::println);
        System.out.println();

        //TODO: Doubt should these items be distinct?
        System.out.println("5: Obtain a list of items ordered by customers who are female");
        List<Item> itemsOrderedByFemales = dataRepo.getOrders().stream()
                .filter(x -> x.getCus().getGender().equals("female")).map(Order::getItems)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        itemsOrderedByFemales.forEach(System.out::println);
        System.out.println();

        System.out.println("6: Obtain a list of items ordered by customers between 01-Jan-2022 and 31-Oct-2022");
        LocalDate rangeStart = LocalDate.of(2022,1,1);
        LocalDate rangeEnd = LocalDate.of(2022,10,31);
        Predicate<Order> isAfter = x-> x.getOrderDate().isAfter(rangeStart);
        Predicate<Order> isBefore = x-> x.getOrderDate().isBefore(rangeEnd);
        Predicate<Order> isBetween = isAfter.and(isBefore);

        dataRepo.getOrders().stream()
                .filter(isBetween)
                .map(Order::getItems)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        mobiles.forEach(System.out::println);
        System.out.println();
    }
}