import java.util.ListResourceBundle;

public class GuiUser_cs_CZ extends ListResourceBundle {

    @Override
    public Object[][] getContents() {
        return contents;
    }

    private static final Object[][] contents = {
            {"labelLogin", "Jméno"},
            {"labelPassword", "Heslo"}
    };
}
