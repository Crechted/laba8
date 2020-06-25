import java.util.ListResourceBundle;

public class GuiCreateNewOrganization_cs_CZ extends ListResourceBundle {
    @Override
    public Object[][] getContents(){
        return contents;
    }

    private final static Object[][] contents = {
            //LABEL
            {"labelName", "Název"},
            {"labelX", "Souřadnice X"},
            {"labelY", "Souřadnice Н"},
            {"labelAnnualTurnover", "Roční obrat"},
            {"labelEmployeesCount", "Počet zaměstnanců"},
            {"labelType", "Typ"},
            {"labelAddress", "Adresa"},
            {"buttonCreate", "Založit"},
            {"buttonCancel", "Zrušení"},
            //LABEL ERROR
            {"leName1", "Řetězec nemůže být prázdný"},
            {"leX1", "hodnota pole by měla být větší než -904"},
            {"leX2", "hodnota souřadnice X musí být číslo \n(hodnota nepřekračující stanovené limity)"},
            {"leY1", "hodnota pole by měla být menší než 552"},
            {"leY2", "hodnota souřadnice Y musí být číslo \n(hodnota nepřekračující stanovené limity)"},
            {"leAnnualTurnover1", "hodnota ročního obratu by měla být pozitivní"},
            {"leAnnualTurnover2", "hodnota ročního obratu musí být číslo \n(kladná hodnota nepřesahuje " +Float.MAX_VALUE+")"},
            {"leEmployeesCount1", "hodnota počtu zaměstnanců by měla být pozitivní"},
            {"leEmployeesCount2", "hodnota počtu zaměstnanců musí být číslo \n(kladná hodnota nepřesahuje " +Integer.MAX_VALUE+")"},
            {"leType1", "pole by nemělo být prázdné"},
            {"leAddress1", "pole by nemělo být prázdné"},
            {"leAddress2", "pole nemůže být delší než 15 znaků \npardon=)"},
    };
}
