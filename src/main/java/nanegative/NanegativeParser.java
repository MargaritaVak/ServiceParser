package nanegative;

import loader.HtmlLoader;
import model.Feedback;
import model.Review;
import org.jetbrains.annotations.NotNull;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parser.Parser;
import parser.ParserSettings;

import java.io.IOException;
import java.util.ArrayList;

public class NanegativeParser implements Parser<ArrayList<Review>> {

    @Override
    public  @NotNull ArrayList<Review> Parse(@NotNull Document document, @NotNull ParserSettings parserSettings) throws IOException {
        ArrayList<Review> reviews = new ArrayList<>();

        Elements reviewsEl = document.getElementsByClass("find-list-box");

        for (Element reviewEl: reviewsEl) {
            String name = reviewEl.getElementsByTag("a").text().substring(9);

            String reviewPath = reviewEl.getElementsByTag("a").get(0).attr("href");
            ArrayList<Feedback> feedbacks = parseFeedbacks(reviewPath, parserSettings);

            reviews.add(new Review(name, feedbacks));
        }

        return reviews;
    }

    private @NotNull ArrayList<Feedback> parseFeedbacks(@NotNull String onlineStorePath, @NotNull ParserSettings parserSettings) throws IOException {
        ArrayList<Feedback> feedbacks = new ArrayList<>();

        for (int i = parserSettings.getInternalStartPoint(); i <= parserSettings.getInternalEndPoint(); i++) {

            HtmlLoader.setUrl(NanegativeSettings.BASE_URL.substring(0, 21) + onlineStorePath + NanegativeSettings.SEPARATOR + NanegativeSettings.PREFIX);
            Document feedbacksPage = HtmlLoader.getSourceByPageId(i);

            Elements feedbacksEl = feedbacksPage.getElementsByClass("reviewers-box");

            for (Element feedbackEl : feedbacksEl) {
                String plus = feedbackEl.getElementsByAttributeValue("itemprop", "pro").text();
                String minus = feedbackEl.getElementsByAttributeValue("itemprop", "contra").text();
                String text = feedbackEl.getElementsByAttributeValue("itemprop", "reviewBody").text();
                String author = feedbackEl.getElementsByAttributeValue("itemprop","author").text();

                feedbacks.add(new Feedback(plus, minus, text,author));
            }
        }

        return feedbacks;
    }
}
