package parser;
import org.jetbrains.annotations.NotNull;
import org.jsoup.nodes.Document;



import java.io.IOException;

public interface Parser<T> {
    T Parse(@NotNull Document document,@NotNull ParserSettings parserSettings) throws IOException;
}
