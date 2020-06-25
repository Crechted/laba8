import java.util.ListResourceBundle;

public class GuiRoot_en_NZ extends ListResourceBundle {

        @Override
        public Object[][] getContents() {
                return contents;
        }

        private static final Object[][] contents = {
                //BUTTON
                {"bShow", "show"},
                {"bHelp", "help"},
                {"bInfo", "info"},
                {"bAdd", "add"},
                {"bUpdate", "update"},
                {"bAddIfMin", "add if min"},
                {"bRemove", "remove"},
                {"bClear", "clear"},
                {"bRemoveGreater", "remove greater"},
                {"bRemoveLower", "remove lower"},
                {"bCGTT", "CGTT"},
                {"bPD", "PD"},
                {"bPFAOA", "PFAOA"},
                {"bExecuteScript", "execute script"},
                {"bChangeUser", "change user"},
                {"cShowAllOrganizations", "show all organizations"},
                {"lFilter", "Filter"},
                //TOOLTIP
                {"tShow", "show information for all organization"},
                {"tHelp", "show all available commands(almost, and in General, this button is useless)"},
                {"tInfo", "show general information about the collection"},
                {"tAdd", "create new organization and add it to the collection"},
                {"tUpdate", "set new values for the SELECTED organization"},
                {"tAddIfMin", "creat a new organization and add it to the collection \nif its annual turnover is the smallest"},
                {"tRemove", "remove the SELECTED organization"},
                {"tClear", "clear the list of all \"your\" organizations"},
                {"tRemoveGreater", "remove all organizations if their annual turnover \nis greater than the annual turnover of the selected organization"},
                {"tRemoveLower", "remove all organizations if their annual turnover \nis lower than the annual turnover of the selected organization"},
                {"tCGTT", "\"Count Greater Than Type' - the number of organizations whose types \n\"in my opinion\" are considered to be greater than the type of the SELECTED organization"},
                {"tPD", "'Print descending' - show all organizations in reverse order"},
                {"tPFAOA", "'Print Field Ascending Official Address' - show all address in ascending order of organizations"},
                {"tExecuteScript", "execute a script from the specified file"},
                {"tChangeUser", "enter a new username and password"},
                {"tShowAllOrganizations", "show all organizations, not just those that you can edit"},
                {"tChoiceLanguage", "changing the language"},
                //COLUMN
                {"organizationNameColumn", "organization name"},
                {"employeesCountColumn", "employees count"},
                {"annualTurnoverColumn", "annual turnover"},
                {"coordinatesColumn", "coordinates"},
                {"typeColumn", "type"},
                {"dateColumn", "creation date"},
                {"addressColumn", "address"},
                {"idColumn", "id"},
                //DATA
                {"dataFormat", "dd/MM/yy"},
                //WORK
                {"sRoot1", "Command was received!"},
                {"sRoot2", "Command was sent to the server."},
                {"sRoot3", "Please wait 3 seconds for the server to respond"},
                {"sRoot4", "server is not responding"},
                {"sClient1", "Passed 3 seconds. New packets from the server wasn't received"},
                {"sClient2", "Packet"},
                {"sClient3", "was received from the server"}
        };
}
