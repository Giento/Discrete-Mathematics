package hr.fer.oop.streams.streams2;

import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Set<Driver> drivers = DBLoader.loadDrivers();

        // a) dio zadatka
        System.out.println("Sortirano po OIB-u ispisati sve vozace iz Zagreba:");
        drivers.stream().filter(driver -> driver.getAddress().contains("Zagreb"))
            .sorted((d1, d2) -> Long.compare(d1.getPid(), d2.getPid()))
            .forEach(driver -> System.out.println(driver));
        System.out.println();

        // b) dio zadatka
        Set<Long> oibs = drivers.stream()
                .filter(driver -> driver.getSurname().startsWith("M"))
                .map(d -> d.getPid()).collect(Collectors.toSet());

        System.out.println("Skup OIB-a vozaca cije prezime pocinje slovom M:");
        oibs.forEach(o -> System.out.println(o));
        System.out.println();

        // c) dio zadatka
        System.out.println("Prosjecna duljina imena vozaca:");
        drivers.stream().mapToInt(d -> d.getFirstName().length())
                .average().ifPresent(avg -> System.out.println(avg));
    }
}
