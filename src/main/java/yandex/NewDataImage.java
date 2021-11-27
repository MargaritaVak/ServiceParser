package yandex;

import model.Image;
import org.jetbrains.annotations.NotNull;
import parser.OnNewDataHandler;

import java.util.ArrayList;

public class NewDataImage implements OnNewDataHandler<ArrayList<Image>> {
    @Override
    public void onNewData(@NotNull Object sender, @NotNull ArrayList<Image> e) {
        for (Image image : e) {
            System.out.println(image);
        }
    }
}
