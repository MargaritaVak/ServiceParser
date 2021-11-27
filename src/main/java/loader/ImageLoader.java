package loader;

import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class ImageLoader {
    private static String savePath = "uploads/";

    public static void setSavePath(@NotNull String savePath) {
        if (savePath.isEmpty()) {
            throw new NullPointerException("Устанавливаемый путь пустой");
        }

        ImageLoader.savePath = savePath;

        if (new File(ImageLoader.savePath).mkdirs()) {
            System.out.println("Директория для сохранения изображений успешно создана\n");
        }
    }

    public static void loader(@NotNull String imageUrl) throws Exception {
        if (imageUrl.isEmpty()) {
            throw new IllegalArgumentException("Пустая входная ссылка");
        }

        BufferedImage input = ImageIO.read(new URL(imageUrl));
        String imageName = getImageName(imageUrl);
        String imageExtension = getImageExtension(imageUrl);

        File output = new File(savePath + imageName + "." + imageExtension);
        try {
            ImageIO.write(input, imageExtension, output);
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении изображения " + imageName + ": " + e.getMessage() + "\n");
        }
    }

    public static void loader(@NotNull String imageUrl, @NotNull String title) throws Exception {
        if (imageUrl.isEmpty()) {
            throw new IllegalArgumentException("Пустая входная ссылка");
        }

        BufferedImage input = ImageIO.read(new URL(imageUrl));
        String imageExtension = getImageExtension(imageUrl);

        File output = new File(savePath + title + "." + imageExtension);
        try {
            ImageIO.write(input, imageExtension, output);
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении изображения " + title + ": " + e.getMessage() + "\n");
        }
    }

    private @NotNull
    static String getImageName(@NotNull String imageUrl) {
        return imageUrl.substring(imageUrl.lastIndexOf("/"), imageUrl.lastIndexOf("."));
    }

    private @NotNull
    static String getImageExtension(@NotNull String imageUrl) {
        String afterPoint = imageUrl.substring(imageUrl.lastIndexOf(".") + 1);
        return afterPoint.contains("&") ? afterPoint.substring(0, afterPoint.indexOf("&")) : afterPoint;
    }
}
