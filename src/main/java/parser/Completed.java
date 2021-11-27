package parser;

import org.jetbrains.annotations.NotNull;

public class Completed implements OnCompleted {
    @Override
    public void onCompleted(@NotNull Object sender) {
        System.out.println("Загрузка закончена\n");
    }
}
