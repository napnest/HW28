import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static final long KB_SIZE = 1024;
    public static final long MB_SIZE = KB_SIZE * 1024;
    public static final long GB_SIZE = MB_SIZE * 1024;

    public static void main(String[] args) {
        System.out.println("============= ДЗ 2 =============");
        System.out.println("Введите путь к файлу или папке: ");
        String path = new Scanner(System.in).nextLine();
        printSize(path);

        System.out.println("============= ДЗ 3 =============");
        //Файл содержит: марка авто, год производства, цена.
        // Создать на основе файла список машин.
        // Сделать скидку на автомобили, которым больше 5 лет 5% от цены.
        // Сделать скидку 2% от цены на все остальные авто.
        // Сохранить полученный результат в файл car_price2.txt в идентичном виде.
        setSale();
    }

    //метод, подсчитывающий размер, полученного файла или папки
    private static void printSize(String path) {
        File file = new File(path);
        long size;
        try {
            if (file.isFile()) {
                size = file.length();
            } else{
                size = Files.walk(Path.of(path))
                        .filter(f -> f.toFile().isFile())
                        .mapToLong(f -> f.toFile().length())
                        .sum();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        coverterSize(size);
    }
    private static void coverterSize(double size){
        if(size<=KB_SIZE){
            System.out.printf("Папка или файл весит: %.2f байт.\n",size);
        }else if(size<=MB_SIZE){
            System.out.printf("Папка или файл весит: %.2f Кб.\n",size/KB_SIZE);
        }else if(size<=GB_SIZE){
            System.out.printf("Папка или файл весит: %.2f Мб.\n",size/MB_SIZE);
        }else{
            System.out.printf("Папка или файл весит: %.2f Гб.\n",size/GB_SIZE);
        }

    }
    //метод, меняющий цену автомобиля в зависимости от скидки
    private static void setSale() {
        try {
            //читаем файл
            List <String> strings = Files.readAllLines(Path.of("HW28/data/car_price.txt"));
            //создаем список машин из файла, разделив элементы через пробел
            List <Car> cars = strings.stream()
                    .map(s->{
                        String [] items = s.split("\\s");
                        if(items.length != 3){
                            throw new RuntimeException("Wrong line!");
                        }
                        return new Car(items[0],
                                Integer.parseInt(items[1]),
                                Double.parseDouble(items[2]));
                                }).collect(Collectors.toList());
            int currentYear = LocalDate.now().getYear();
            //создаем список цен с учетом скидок
            List<String> carDiscount = cars.stream()
                    .map(c->{
                        int age = currentYear-c.getYear();
                        double discount = age > 5 ? 0.05 : 0.02;
                        double discountedPrice = c.getPrice() * (1-discount);
                        return c.getMark() + " " + c.getYear() + " " + discountedPrice;
                    }).collect(Collectors.toList());
            //создаем новый файл с содержимым обновленных цен
            Files.write(Path.of("HW28/data/car_price2.txt"),carDiscount);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
