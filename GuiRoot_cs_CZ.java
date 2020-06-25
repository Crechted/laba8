import java.util.ListResourceBundle;

public class GuiRoot_cs_CZ extends ListResourceBundle {
    @Override
    public Object[][] getContents() {
        return contents;
    }

    private static final Object[][] contents = {
            //BUTTON
            {"bShow", "Ukázat"},
            {"bHelp", "pomoct"},
            {"bInfo", "informace"},
            {"bAdd", "přidat"},
            {"bUpdate", "aktualizace"},
            {"bAddIfMin", "add if mine"},
            {"bRemove", "odstranit"},
            {"bClear", "jasný"},
            {"bRemoveGreater", "odstranit větší"},
            {"bRemoveLower", "odstranit dolů"},
            {"bCGTT", "PVNT"}, //Počet větší než Typ
            {"bPD", "SP"}, //vytisknout sestupný
            {"bPFAOA", "TPVOA"}, //tiskové pole vzestupná oficiální adresa
            {"bExecuteScript", "spustit skript"},
            {"bChangeUser", "změnit uživatele"},
            {"cShowAllOrganizations", "Zobrazit všechny organizace"},
            {"lFilter", "filtrovat"},
            //TOOLTIP
            {"tShow", "zobrazit informace o všech organizacích"},
            {"tHelp", "Zobrazit všechny dostupné příkazy (téměř a vůbec toto tlačítko je k ničemu)"},
            {"tInfo", "zobrazit obecné informace o sbírce"},
            {"tAdd", "vytvořte novou organizaci a přidejte ji do sbírky"},
            {"tUpdate", "nastavit nové hodnoty VYBRANÉ organizace"},
            {"tAddIfMin", "vytvořte novou organizaci a přidejte ji do sbírky, \npokud bude její roční obrat (annual turnover) nejmenší"},
            {"tRemove", "odstranit VYBRANOU organizaci"},
            {"tClear", "vymazat seznam všech \"vašich\" organizací"},
            {"tRemoveGreater", "odstranit všechny organizace, jejichž roční obrat (annual turnover) přesahuje roční obrat VYBRANÉ organizaci"},
            {"tRemoveLower", "odstraňte všechny organizace, jejichž roční obrat (annual turnover) je menší než roční obrat vybrané organizace"},
            {"tCGTT", "'Počet Větší Než Typ' - počet organizací, jejichž typy \"podle mého názoru\" jsou považovány za větší typ vybrané organizace"},
            {"tPD", "'Sestupné pořadí' - zrušit všechny organizace v opačném pořadí"},
            {"tPFAOA", "'Tiskové Pole Vzestupná Oficiální Adresa' - Zobrazit všechny adresy ve vzestupném pořadí organizací"},
            {"tExecuteScript", "spustit skript z zadaného souboru"},
            {"tChangeUser", "zadejte nové uživatelské jméno a heslo"},
            {"tShowAllOrganizations", "Zobrazit všechny organizace, nejen ty, které můžete upravit"},
            {"tChoiceLanguage", "změna jazyka"},
            //COLUMN
            {"organizationNameColumn","název organizace"},
            {"employeesCountColumn", "počet zaměstnanců"},
            {"annualTurnoverColumn", "roční obrat"},
            {"coordinatesColumn", "souřadnice"},
            {"typeColumn", "typ"},
            {"dateColumn", "datum vytvoření"},
            {"addressColumn", "adresa"},
            {"idColumn", "id"},
            //DATA
            {"dataFormat", "d. M. yyyy"},
            //WORK
            {"sRoot1", "tým byl přijat!"},
            {"sRoot2", "tým byl odeslán na server."},
            {"sRoot3", "prosím, počkejte 3 sekundy, dokud server reaguje"},
            {"sRoot4", "server neodpověděl"},
            {"sClient1", "uběhly 3 vteřiny. Nové balíčky ze serveru nepřišly"},
            {"sClient2", "Balíček"},
            {"sClient3", "byl přijat"}
    };
}
