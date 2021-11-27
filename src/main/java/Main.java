import leroymerlin.LeroyMerlinParser;
import leroymerlin.LeroyMerlinSettings;
import leroymerlin.NewDataProducts;
import loader.ImageLoader;
import model.Image;
import model.Product;
import model.Review;
import nanegative.NanegativeParser;
import nanegative.NanegativeSettings;
import nanegative.NewDataReview;
import parser.Completed;
import parser.ParserWorker;
import yandex.NewDataImage;
import yandex.YandexParser;
import yandex.YandexSettings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Scanner IN = new Scanner(System.in);

    public static void main(String[] args) {
        int menu = -1;
        while (menu != 0) {
            printMenu();
            System.out.print("Выберите пункт меню: ");
            try {
                menu = IN.nextInt();
            } catch (Exception e) {
                System.out.println("Ошибка при считывании с клавиатуры, повторите попытку");
            }
            switch (menu) {
                case 0 -> System.out.println("...");
                case 1 -> parseNanegative();
                case 2 -> parseLeroyMerlin();
                case 3 -> parseYandex();
            }
        }
    }

    private static void printMenu() {
        System.out.println("Сбор данных:");
        System.out.println("1. https://nanegative.ru/stroitelstvo");
        System.out.println("2. https://leroymerlin.ru/");
        System.out.println("3. Картинки из интернета");
        System.out.println("0. Выход");
    }

    private static void parseNanegative() {
        try {
            int startPageReview = readPagination("Введите начало страницы сайта: ");
            int endPageReview = readPagination("Введите конец страницы сайта: ");

            int startPageFeedback = readPagination("Введите начало страницы отзывов: ");
            int endPageFeedback = readPagination("Введите конец страницы отзывов: ");

            ParserWorker<ArrayList<Review>> parser = new ParserWorker<>(new NanegativeParser(),
                    new NanegativeSettings(startPageReview, endPageReview, startPageFeedback, endPageFeedback));

            parser.onCompletedList.add(new Completed());
            parser.onNewDataList.add(new NewDataReview());

            System.out.println("\nЗагрузка началась\n\n");
            parser.start();
            parser.abort();
        } catch (Exception e) {
            System.out.println("Ошибка...\n" + e.getMessage() + "\n");
        }
    }

    private static int readPagination(String message) {
        System.out.print(message);
        int value = 0;
        if (IN.hasNextInt()) {
            value = IN.nextInt();
            if (value > 0) {
                return value;
            }
            System.out.println("Введите целочисленное значение больше 0");
        }
        return value;
    }

    private static void parseLeroyMerlin() {
        try {
            System.out.print("Введите категорию товаров транслитом: ");
            String category = IN.next();
            int startPage = readPagination("Введите начало страницы товаров: ");
            int endPage = readPagination("Введите конец страницы товаров: ");

            ParserWorker<ArrayList<Product>> parser = new ParserWorker<>(new LeroyMerlinParser(),
                    new LeroyMerlinSettings(startPage, endPage, category));

            parser.onCompletedList.add(new Completed());
            parser.onNewDataList.add(new NewDataProducts());

            System.out.println("\nЗагрузка началась\n\n");
            parser.start();
            parser.abort();
        } catch (Exception e) {
            System.out.println("Ошибка...\n" + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()) + "\n");
        }
    }

    private static void parseYandex() {
        try {
            System.out.print("Введите тему: ");
            String query = IN.next();

            int startPage = readPagination("Введите начало страницы: ");
            int endPage = readPagination("Введите конец страницы: ");

            ImageLoader.setSavePath("uploads/yandex/" + query + "/");

            ParserWorker<ArrayList<Image>> parser = new ParserWorker<>(new YandexParser(),
                    new YandexSettings(query, startPage - 1, endPage - 1));

            parser.onCompletedList.add(new Completed());
            parser.onNewDataList.add(new NewDataImage());

            System.out.println("\nЗагрузка началась\n\n");
            parser.start();
            parser.abort();
        } catch (Exception e) {
            System.out.println("Ошибка...\n" + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()) + "\n");
        }
    }
}
