import java.util.ListResourceBundle;

public class GuiUser_en_NZ extends ListResourceBundle {

    @Override
    public Object[][] getContents() {
        return contents;
    }

    private static final Object[][] contents = {
            {"labelLogin", "Username"},
            {"labelPassword", "Password"}
    };
}
