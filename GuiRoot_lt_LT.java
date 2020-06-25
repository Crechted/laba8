import java.util.ListResourceBundle;

public class GuiRoot_lt_LT extends ListResourceBundle {
    @Override
    public Object[][] getContents() {
        return contents;
    }

    private static final Object[][] contents = {
            //BUTTON
            {"bShow", "rodyti"},
            {"bHelp", "pagalba"},
            {"bInfo", "informacija"},
            {"bAdd", "pridėt"},
            {"bUpdate", "atnaujinti"},
            {"bAddIfMin", "pridėti jei min"},
            {"bRemove", "pašalinti"},
            {"bClear", "nuvalyti"},
            {"bRemoveGreater", "ištrinti didelis"},
            {"bRemoveLower", "ištrinti mažiau"},
            {"bCGTT", "SDUT"}, //Skaičius didesnis už tipą
            {"bPD", "SMT"}, //spausdinti mažėjančia tvarka
            {"bPFAOA", "SLDTOA"}, //spausdinimo laukas didėjančia tvarka oficialus adresas
            {"bExecuteScript", "vykdyti scenarijų"},
            {"bChangeUser", "keisti vartotoją"},
            {"cShowAllOrganizations", "rodyti visas organizacijas"},
            {"lFilter", "filtras"},
            //TOOLTIP
            {"tShow", "matyti informaciją apie visus organizacijoms"},
            {"tHelp", "rodyti visas komandas(beveik, taip ir apskritai šis mygtukas nenaudingas)"},
            {"tInfo", "matyti bendrą informaciją apie kolekcijos"},
            {"tAdd", "kurti naują organizaciją ir įtraukti į savo kolekciją"},
            {"tUpdate", "nustatyti naujų vertės PASIRINKTOS organizacijos"},
            {"tAddIfMin", "kurti naują organizaciją ir įtraukti į savo kolekciją, \njei jos metinė apyvarta (annual turnover) bus viena iš mažiausiai"},
            {"tRemove", "ištrinti PASIRINKTĄ organizaciją"},
            {"tClear", "išvalyti sąrašą visų \"savo\" organizacijų"},
            {"tRemoveGreater", "ištrinti visas organizacijos, kurių metinė apyvarta (annual turnover) \nviršija metinę apyvartą PASIRINKTOS organizacijos"},
            {"tRemoveLower", "ištrinti visas organizacijos, kurių metinė apyvarta (annual turnover) \nmažiau metinio PASIRINKTOS organizacijos"},
            {"tCGTT", "'Skaičius Didesnis Už Tipą' - daug organizacijų, kurių tipai \"mano nuomone\", yra laikomi daugiau tipo PASIRINKTOS organizacijos"},
            {"tPD", "'Spausdinti Mažėjančia Tvarka' - rodyti viską organizacijos atvirkštine tvarka"},
            {"tPFAOA", "'Spausdinimo Laukas Didėjančia Tvarka Oficialus Adresas' - \nrodyti visus adresus didėjančia tvarka organizacijų"},
            {"tExecuteScript", "vykdyti scenarijus iš nurodyto failo"},
            {"tChangeUser", "įveskite naują vartotojo vardą ir slaptažodį"},
            {"tShowAllOrganizations", "rodyti visus organizacijos, o ne tik tie, \nkuriuos jūs galite redaguoti"},
            {"tChoiceLanguage", "kalbos pakeitimas"},
            //COLUMN
            {"organizationNameColumn","organizacijos pavadinimas"},
            {"employeesCountColumn", "darbuotojų skaičius"},
            {"annualTurnoverColumn", "metinė apyvarta"},
            {"coordinatesColumn", "koordinatės"},
            {"typeColumn", "tipas"},
            {"dateColumn", "sukūrimo data"},
            {"addressColumn", "adresas"},
            {"idColumn", "id"},
            //DATA
            {"dataFormat", "yyyy-MM-dd"},
            //WORK
            {"sRoot1", "komanda buvo priimtas!"},
            {"sRoot2", "komanda buvo siunčiami į serverį."},
            {"sRoot3", "palaukite 3 sekundes, kol serveris yra atsakingas"},
            {"sRoot4", "serveris neatsakė"},
            {"sClient1", "praėjo 3 sekundes. Naujų paketų, iš serverio buvo pranešta"},
            {"sClient2", "Paketas"},
            {"sClient3", "buvo gautas"}
    };
}