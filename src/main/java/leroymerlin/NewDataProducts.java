package leroymerlin;

import model.Product;
import org.jetbrains.annotations.NotNull;
import parser.OnNewDataHandler;

import java.util.ArrayList;

public class NewDataProducts implements OnNewDataHandler<ArrayList<Product>> {
    @Override
    public void onNewData(@NotNull Object sender, @NotNull ArrayList<Product> e) {
        for (Product product : e) {
            System.out.println(product);
        }
    }
}
