import java.util.ListResourceBundle;

public class GuiUser_ru_RU extends ListResourceBundle {

    @Override
    public Object[][] getContents() {
        return contents;
    }

    private static final Object[][] contents = {
            {"labelLogin", "Имя пользователя"},
            {"labelPassword", "Пароль"}
    };
}
