package parser;

public class ParserSettings {

    // Адрес сайта
    public static String BASE_URL;

    // Разделитель
    public static String SEPARATOR;

    // Префикс страницы
    public static String PREFIX;

    // Начало пагинации внешней
    protected int externalStartPoint;

    // Конец пагинации внешней
    protected int externalEndPoint;

    // Начало пагинации внутренней
    protected int internalStartPoint;

    // Конец пагинации внутренней
    protected int internalEndPoint;

    public int getExternalStartPoint() {
        return externalStartPoint;
    }

    public int getExternalEndPoint() {
        return externalEndPoint;
    }

    public int getInternalStartPoint() {
        return internalStartPoint;
    }

    public int getInternalEndPoint() {
        return internalEndPoint;
    }
}
