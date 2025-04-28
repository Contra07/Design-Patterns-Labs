package ru.ssau;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import ru.ssau.strategy.Counter;
import ru.ssau.strategy.FirstCountStrategy;
import ru.ssau.strategy.SecondFirstCountStrategy;

public class Main 
{
    public static void main(String[] args) throws IOException, ClassNotFoundException 
    {
        var filepath = "lab3_seventh\\src\\main\\resources\\array";
        try (
            var in = new ObjectInputStream(Files.newInputStream(Paths.get(filepath)))
        ) {
            var a = (int[])in.readObject();
            for (int i : a) {
                System.out.println(i);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        var strategy1 = new FirstCountStrategy();
        var strategy2 = new SecondFirstCountStrategy();
        var counter = new Counter(strategy1);
        System.out.println("Используем первую стратегию: ");
        System.out.println(counter.countFromFile(filepath));
        System.out.println("Используем вторую стратегию: ");
        counter.setCountStrategy(strategy2);
        System.out.println(counter.countFromFile(filepath));
    }
}