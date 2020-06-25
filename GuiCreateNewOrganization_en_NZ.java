import java.util.ListResourceBundle;

public class GuiCreateNewOrganization_en_NZ extends ListResourceBundle {
    @Override
    public Object[][] getContents(){
        return contents;
    }

    private final static Object[][] contents = {
            //LABEL
            {"labelName", "Name"},
            {"labelX", "Coordinate X"},
            {"labelY", "Coordinate Y"},
            {"labelAnnualTurnover", "Annual turnover"},
            {"labelEmployeesCount", "Employees count"},
            {"labelType", "Type"},
            {"labelAddress", "Address"},
            {"buttonCreate", "Create"},
            {"buttonCancel", "Cancel"},
            //LABEL ERROR
            {"leName1", "The string cannot be empty"},
            {"leX1", "the field value must be greater than -904"},
            {"leX2", "the value of the x coordinate must be a number \n(the value does not exceed the specified limits)"},
            {"leY1", "the field value must be less than 552"},
            {"leY2", "the value of the y coordinate must be a number \n(the value does not exceed the specified limits)"},
            {"leAnnualTurnover1", "the value of the annual turnover must be positive"},
            {"leAnnualTurnover2", "the value of the annual turnover must be a number \n(positive value not exceeding " +Float.MAX_VALUE+")"},
            {"leEmployeesCount1", "the number of employees must be positive"},
            {"leEmployeesCount2", "the number of employees must be a number \n(positive value not exceeding " +Integer.MAX_VALUE+")"},
            {"leType1", "the field must not be empty"},
            {"leAddress1", "the field must not be empty"},
            {"leAddress2", "the field cannot be longer than 15 characters \nsorry=)"},
    };
}

