import java.util.ListResourceBundle;

public class GuiCreateNewOrganization_ru_RU  extends ListResourceBundle {
    @Override
    public Object[][] getContents(){
        return contents;
    }

    private final static Object[][] contents = {
            //LABEL
            {"labelName", "Имя"},
            {"labelX", "Координата X"},
            {"labelY", "Координата Y"},
            {"labelAnnualTurnover", "Годовой оборот"},
            {"labelEmployeesCount", "Колличество сотрудников"},
            {"labelType", "Тип"},
            {"labelAddress", "Адрес"},
            {"buttonCreate", "Создать"},
            {"buttonCancel", "Отмена"},
            //LABEL ERROR
            {"leName1", "Строка не может быть пустой"},
            {"leX1", "значение поля должно быть больше -904"},
            {"leX2", "значение координаты X должно быть числом \n(значением не выходящее за заданные пределы)"},
            {"leY1", "значение поля должно быть меньше 552"},
            {"leY2", "значение ккординаты Y должно быть числом \n(значением не выходящее за заданные пределы)"},
            {"leAnnualTurnover1", "значение годового оборота должно быть положительным"},
            {"leAnnualTurnover2", "значение годового оборота должно быть числом \n(положительное значение не превышающее " +Float.MAX_VALUE+")"},
            {"leEmployeesCount1", "значение количества сотрудников должно быть положительным"},
            {"leEmployeesCount2", "значение количества сотрудников должно быть числом \n(положительное значение не превышающее " +Integer.MAX_VALUE+")"},
            {"leType1", "поле не должно быть пустым"},
            {"leAddress1", "поле не должно быть пустым"},
            {"leAddress2", "поле не может быть длинной больше 15 символов \nпростите=)"},
    };
}
