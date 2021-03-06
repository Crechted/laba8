import java.util.ListResourceBundle;

public class GuiRoot_ru_RU extends ListResourceBundle {

    @Override
    public Object[][] getContents() {
        return contents;
    }

    private static final Object[][] contents = {
            //BUTTON
            {"bShow", "показать"},
            {"bHelp", "помощь"},
            {"bInfo", "информация"},
            {"bAdd", "добавить"},
            {"bUpdate", "обновить"},
            {"bAddIfMin", "добавить если мин"},
            {"bRemove", "удалить"},
            {"bClear", "очистить"},
            {"bRemoveGreater", "удалить большие"},
            {"bRemoveLower", "удалить меньшие"},
            {"bCGTT", "КТБЗ"},
            {"bPD", "НП"},
            {"bPFAOA", "ВППА"},
            {"bExecuteScript", "выполнить скрипт"},
            {"bChangeUser", "сменить пользователя"},
            {"cShowAllOrganizations", "показать все Организации"},
            {"lFilter", "Фильтр"},
            //TOOLTIP
            {"tShow", "вывести информацию по всем организациям"},
            {"tHelp", "показать все доступные команды(почти, да и вообще эта кнопка безполезна)"},
            {"tInfo", "вывести общую информацию о коллекции"},
            {"tAdd", "создать новую организацию и добавить её в коллекцию"},
            {"tUpdate", "задать новые значения ВЫБРАННОЙ организации"},
            {"tAddIfMin", "создать новую организацию и добавить её в коллекцию, \nесли её годовой оборот (annual turnover) будет самым наименьшим"},
            {"tRemove", "удалить ВЫБРАННУЮ организацию"},
            {"tClear", "очистить список всех \"ваших\" организаций"},
            {"tRemoveGreater", "удалить все организации, чей годовой оборот (annual turnover) превышает годовой оборот ВЫБРАННОЙ организации"},
            {"tRemoveLower", "удалить все организации, чей годовой оборот (annual turnover) меньше годового оборота ВЫБРАННОЙ организации"},
            {"tCGTT", "'Колличество Типов Больше Заданного' - колличество организаций чьи типы \"по моему мнению\" считаются больше типа ВЫБРАННОЙ организации"},
            {"tPD", "'Нисходящий Порядок' - вывести все организации в обратном порядке"},
            {"tPFAOA", "'Восходящий Порядок Полей Адресов' - вывести все адреса в порядке возрастания организаций"},
            {"tExecuteScript", "выполнить скрипт из указаного файла"},
            {"tChangeUser", "ввести новые имя пользователя и пароль"},
            {"tShowAllOrganizations", "демонстрировать все организации, а не только те, которые вы можете редактировать"},
            {"tChoiceLanguage", "смена языка"},
            //COLUMN
            {"organizationNameColumn","название организации"},
            {"employeesCountColumn", "колличество сотрудиков"},
            {"annualTurnoverColumn", "годовой оборот"},
            {"coordinatesColumn", "координаты"},
            {"typeColumn", "тип"},
            {"dateColumn", "дата создания"},
            {"addressColumn", "адрес"},
            {"idColumn", "id"},
            //DATA
            {"dataFormat", "dd.MM.yy"},
            //WORK
            {"sRoot1", "команда была принята!"},
            {"sRoot2", "команда была отправлена на сервер."},
            {"sRoot3", "пожалуйста, подождите 3 секунды, пока сервер отвечает"},
            {"sRoot4", "сервер не ответил"},
            {"sClient1", "прошло 3 секунды. Новых пакетов от сервера не поступало"},
            {"sClient2", "Пакет"},
            {"sClient3", "был получен"}
    };
}
