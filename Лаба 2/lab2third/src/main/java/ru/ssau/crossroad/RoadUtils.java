package ru.ssau.crossroad;

import java.awt.Image;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

import javax.swing.ImageIcon;

class RoadUtils 
{
    private static Random random = new Random();
    private static List<Image> carImages;
    private static Image backgroundImage = new ImageIcon("lab2third\\src\\main\\resources\\background.png").getImage();
    public static Map<TrafficLight.State, Image> trafficLightImages = Map.of
    (
        TrafficLight.State.Green, (new ImageIcon("lab2third\\src\\main\\resources\\traffic light\\green.png")).getImage(),
        TrafficLight.State.Yellow, (new ImageIcon("lab2third\\src\\main\\resources\\traffic light\\yellow.png")).getImage(),
        TrafficLight.State.Red, (new ImageIcon("lab2third\\src\\main\\resources\\traffic light\\red.png")).getImage()
    );

    static {
        carImages = new ArrayList<>();
        Path imagesFolder = Paths.get("lab2third\\src\\main\\resources\\car");
        try (Stream<Path> paths = Files.list(imagesFolder)) 
        {
            paths.filter(
                path -> {
                    String lowerFileName = path.getFileName().toString().toLowerCase();
                    return lowerFileName.endsWith(".png") || lowerFileName.endsWith(".jpg") || lowerFileName.endsWith(".jpeg");
                }
            )
            .forEach(
                path -> {
                    Image icon = new ImageIcon(path.toAbsolutePath().toString()).getImage();
                    carImages.add(icon);
                }
            );
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TrafficLight createDefaultTrafficLight()
    {
        return new TrafficLight(4000, trafficLightImages);
    }

    public static Car createRandomCar()
    {
        var index = carImages.size() == 1 ? 0 : random.nextInt(0, carImages.size()-1);
        var inHarry = random.nextBoolean();
        var name = inHarry ? "Спешит" : "Нормальный";
        var speed = random.nextInt(5, 15);
        return new Car(name, speed, inHarry, carImages.get(index));
    }

    public static Image getBackgroundImage()
    {
        return backgroundImage;
    }
}
