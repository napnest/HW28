import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("============= ДЗ 2 =============");
        System.out.println("Введите путь к файлу или папке: ");
        String path = new Scanner(System.in).nextLine();
        printSize(path);

        System.out.println("============= ДЗ 3 =============");
        setSale();
    }

    private static void printSize(String path) {
        //todo Дописать код расчета размера и корректного отображения
    }

    private static void setSale() {
        //todo Тут написать код для ДЗ #3
    }
}
