import java.util.ListResourceBundle;

public class GuiCreateNewOrganization_lt_LT extends ListResourceBundle {
    @Override
    public Object[][] getContents(){
        return contents;
    }

    private final static Object[][] contents = {
            //LABEL
            {"labelName", "Мardas"},
            {"labelX", "Koordinatė X"},
            {"labelY", "Koordinatė Н"},
            {"labelAnnualTurnover", "Metinė apyvarta"},
            {"labelEmployeesCount", "Darbuotojų skaičius"},
            {"labelType", "Еipas"},
            {"labelAddress", "Adresas"},
            {"buttonCreate", "Įkurti"},
            {"buttonCancel", "Panaikinimas"},
            //LABEL ERROR
            {"leName1", "Eilutė negali būti tuščias"},
            {"leX1", "lauko reikšmė turi būti didesnė nei -904"},
            {"leX2", "vertė koordinatės X turi būti skaičius \n(vertė yra ne didesnį už nustatytą ribą)"},
            {"leY1", "lauko reikšmė turi būti mažesnė 552"},
            {"leY2", "vertė koordinatės Y turi būti skaičius \n(vertė yra ne didesnį už nustatytą ribą)"},
            {"leAnnualTurnover1", "vertė metinė apyvarta turi būti teigiamas"},
            {"leAnnualTurnover2", "vertė metinė apyvarta turi būti skaičius \n(teigiamą reikšmę ne daugiau nei " +Float.MAX_VALUE+")"},
            {"leEmployeesCount1", "vertė, kiek darbuotojų turi būti teigiamas"},
            {"leEmployeesCount2", "vertė, kiek darbuotojų turi būti skaičius \n(teigiamą reikšmę ne daugiau nei " +Integer.MAX_VALUE+")"},
            {"leType1", "laukas negali būti tuščias"},
            {"leAddress1", "laukas negali būti tuščias"},
            {"leAddress2", "laukas gali būti ilga daugiau kaip 15 simbolių \nAtleisk man=)"},
    };
}

