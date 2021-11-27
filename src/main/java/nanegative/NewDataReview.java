package nanegative;

import model.Review;
import org.jetbrains.annotations.NotNull;
import parser.OnNewDataHandler;

import java.util.ArrayList;

public class NewDataReview implements OnNewDataHandler<ArrayList<Review>> {

    @Override
    public void onNewData(@NotNull Object sender, @NotNull ArrayList<Review> e) {
        for(Review review: e)
        {
            System.out.println(review);
        }
    }
}
