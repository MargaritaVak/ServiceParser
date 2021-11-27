package leroymerlin;

import loader.HtmlLoader;
import model.Feedback;
import model.Product;
import nanegative.NanegativeSettings;
import org.jetbrains.annotations.NotNull;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parser.Parser;
import parser.ParserSettings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class LeroyMerlinParser implements Parser<ArrayList<Product>> {
    @Override
    public @NotNull ArrayList<Product> Parse(@NotNull Document document, @NotNull ParserSettings parserSettings) throws IOException {
        ArrayList<Product> products = new ArrayList<>();

        Elements productsEl = document.getElementsByClass("phytpj4_plp largeCard");

        for (Element productEl : productsEl) {
            String name = productEl.getElementsByClass("t9jup0e_plp p1h8lbu4_plp").text();
            String productPath = productEl.getElementsByTag("a").get(0).attr("href");
            ArrayList<Feedback> feedbacks = parseFeedbacks(productPath);

            products.add(new Product(name, feedbacks));
        }

        return products;
    }

    private @NotNull ArrayList<Feedback> parseFeedbacks(@NotNull String productPath) throws IOException {
        ArrayList<Feedback> feedbacks = new ArrayList<>();

        HtmlLoader.setUrl(NanegativeSettings.BASE_URL.substring(0, 22) + productPath);
        Document feedbacksPage = HtmlLoader.getSource();

        Elements feedbacksEl = feedbacksPage.getElementsByAttributeValue("itemprop", "review");

        for (Element feedbackEl : feedbacksEl) {
            String plus = "", minus = "";
            if (!feedbackEl.getElementsByAttributeValue("slot", "desc").isEmpty()) {
                plus = feedbackEl.getElementsByAttributeValue("slot", "desc").size() > 0 ? feedbackEl.getElementsByAttributeValue("slot", "desc").get(0).text() : "";
                minus = feedbackEl.getElementsByAttributeValue("slot", "desc").size() > 1 ? feedbackEl.getElementsByAttributeValue("slot", "desc").get(1).text() : "";
            }
            String text = Objects.requireNonNull(feedbackEl.getElementsByAttributeValue("slot", "review-text").first()).ownText();
            String author = feedbackEl.getElementsByAttributeValue("slot", "title").text();
            feedbacks.add(new Feedback(plus, minus, text,author));
        }

        return feedbacks;
    }
}
