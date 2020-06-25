package Code8;

import Client.Work;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicReference;

public class Command implements Serializable{
    private static final long serialVersionUID = 15L;

    public static class Start implements Workable, Serializable{
        private static final long serialVersionUID = 1L;
        private static Start start = new Start();
        CollectionOrganization collection;
        private Start(){
            this.collection = CollectionOrganization.getCollectionOrganization();
        }
        public static Start getStart(){
            return start;
        }
        @Override
        public synchronized String work() {return "";}
    }

    public static class Show implements Workable, Serializable{
        private static final long serialVersionUID = 1L;
        private static Show show = new Show();
        CollectionOrganization collection;
        private Show(){
            this.collection = CollectionOrganization.getCollectionOrganization();
        }
        public static Show getShow(){
            return show;
        }
        @Override
        public synchronized String work() {
            String str = "";
            for (Organization org : collection.getCollection()) {
                str += org + "\n";
            }
            return str;
        }
    }



    public static class Add implements Workable, Serializable{
        private static final long serialVersionUID = 2L;
        private static Add add = new Add();
        private Add addServer;
        private CollectionOrganization collection;
        private Organization org;
        private Add(){
            this.collection = CollectionOrganization.getCollectionOrganization();
        }
        public static Add getAdd(){
            return add;
        }

        public void setOrganization(Organization org){
            this.org = org;
            addServer = add;
        }

        public Organization getOrg() {
            return org;
        }

        @Override
        public synchronized String work() {
            if (addServer.org != null) {
                collection.getCollection().add(addServer.org);
                return "Organization was added";
            }
            else
                return "Organization wasn't added";
        }
    }


    public static class UpdateElement implements Workable, Serializable {
        private static final long serialVersionUID = 3L;
        private static UpdateElement updateElement = new UpdateElement();
        private UpdateElement updateElementServer;
        private CollectionOrganization collection;
        private Organization org;
        private long id;
        private UpdateElement(){
            this.collection = CollectionOrganization.getCollectionOrganization();
        }
        public static UpdateElement getUpdateElement(){
            return updateElement;
        }
        public void setId(long id){
            this.id = id;
        }

        public void setOrg(Organization org) {
            this.org = org;
            updateElementServer = updateElement;
        }

        public Organization getOrg() {
            return org;
        }

        public long getId() {
            return id;
        }


        @Override
        public synchronized String work() {
            Remove remove = Remove.getRemove();
            remove.setId(updateElementServer.id);
            remove.work();
            updateElementServer.org.setId(id);
            collection.getCollection().add(updateElementServer.org);
            return "Organization was updated";
        }
    }



    public static class Remove implements Workable, Serializable {
        private static final long serialVersionUID = 4L;
        private static Remove remove = new Remove();
        private Remove removeServer;
        private CollectionOrganization collection;
        private long id;

        private Remove() {
            this.collection = CollectionOrganization.getCollectionOrganization();
        }

        public static Remove getRemove() {
            return remove;
        }

        public void setId(long id) {
            this.id = id;
            removeServer = remove;
        }

        public Remove getRemoveServer() {
            return removeServer;
        }


        public long getId() {
            return this.id;
        }

        public long getIdRemoverServer() {
            return removeServer.getId();
        }

        @Override
        public synchronized String work() {
            AtomicReference<Organization> org = new AtomicReference<>();
            removeServer.collection.getCollection().stream().
                    filter(o -> o.getId() == removeServer.id).limit(1).
                    forEach(o -> org.set(o));
            removeServer.collection.getCollection().remove(org.get());
            return "Organization with id " + removeServer.id + " was removed";
        }
    }



    public static class Clear implements Workable, Serializable {
        private static final long serialVersionUID = 5L;
        private static Clear clear = new Clear();
        private CollectionOrganization collection;

        private Clear() {
            this.collection = CollectionOrganization.getCollectionOrganization();
        }

        public static Clear getClear() {
            return clear;
        }

        @Override
        public synchronized String work()
        {
            collection.getCollection().clear();
            return "Collection was cleared";
        }
    }



    public static class ExecuteScript implements Workable, Serializable {
        private static final long serialVersionUID = 6L;
        private static ExecuteScript executeScript = new ExecuteScript();
        private CollectionOrganization collection;
        private String path;
        private ArrayList<Workable> listWork = new ArrayList<>();
        private ExecuteScript() {
            this.collection = CollectionOrganization.getCollectionOrganization();
        }

        public static ExecuteScript getExecuteScript() {
            return executeScript;
        }

        public void setFilePath(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }

        public ArrayList<Workable> getListWork() {
            return listWork;
        }

        @Override
        public synchronized String work() { //execute_script C:/Users/Daniil/Desktop/Универ/Прога/lab5/File/start.json

            try (BufferedReader reader = new BufferedReader(new FileReader(path))){
                Work.goScriptWork(reader);
            } catch (IOException e) {
                System.out.println("Введен некорректный адрес");
            } catch (NullPointerException e) {
                System.out.println("Передано пустое значение");
            } catch (StackOverflowError error) {
                System.out.println("ну... тут мои полномочия все, память закончилась" +
                        "\n" + "введите нову команду");

            }
            return "Script finished work";
        }
    }


    public static class AddIfMin implements Workable, Serializable {
        private static final long serialVersionUID = 7L;
        private static AddIfMin addIfMin = new AddIfMin();
        private AddIfMin addIfMinServer;
        private CollectionOrganization collection;
        private Organization org;

        private AddIfMin() {
            this.collection = CollectionOrganization.getCollectionOrganization();
        }

        public static AddIfMin getAddIfMin() {
            return addIfMin;
        }

        public void setOrganization(Organization org) {
            this.org = org;
            addIfMinServer = addIfMin;
        }

        public Organization getOrg() {
            return org;
        }


        @Override
        public synchronized String work() {
            if (addIfMinServer.org.hashCode() < collection.getCollection().first().hashCode() && addIfMinServer.org != null) {
                collection.getCollection().add(addIfMinServer.org);
                return "Organization was added";
            }
            else return  "Organization wasn't added";
        }
    }

    public static class RemoveGreater implements Workable, Serializable{
        private static final long serialVersionUID = 8L;
        private static RemoveGreater removeGreater = new RemoveGreater();
        private CollectionOrganization collection;
        private long elementId;
        private float annualTurnover;

        private RemoveGreater() {
            this.collection = CollectionOrganization.getCollectionOrganization();
        }

        public static RemoveGreater getRemoveGreater() {
            return removeGreater;
        }

        public void setElementId(long elementId) {
            this.elementId = elementId;
        }

        public long getElementId() {
            return elementId;
        }

        public float getAnnualTurnover() {
            return annualTurnover;
        }

        public void setAnnualTurnover(float annualTurnover) {
            this.annualTurnover = annualTurnover;
        }

        @Override
        public synchronized String work() {
            Organization organization = null;
            for (Organization o : collection.getCollection()) {
                if (o.getId() == elementId) {
                    organization = o;
                    break;
                }
            }
            if (organization == null) {
                return "Organizations \"greater\" specified weren't deleted\nincorrect name element";
            } else {
                for (Organization o : collection.getCollection()) {
                    if (o.getAnnualTurnover() > organization.getAnnualTurnover()) {
                        collection.getCollection().remove(o);
                        break;
                    }
                }
            }
            return "Organizations \"greater\" specified were deleted";
        }
    }

    public static class RemoveLower implements Workable, Serializable {
        private static final long serialVersionUID = 9L;
        private static RemoveLower removeLower = new RemoveLower();
        private CollectionOrganization collection;
        private long elementId;
        private float annualTurnover;

        private RemoveLower() {
            this.collection = CollectionOrganization.getCollectionOrganization();
        }

        public static RemoveLower getRemoveLower() {
            return removeLower;
        }

        public void setElementId(long elementId) {
            this.elementId = elementId;
        }

        public long getElementId() {
            return elementId;
        }

        public float getAnnualTurnover() {
            return annualTurnover;
        }

        public void setAnnualTurnover(float annualTurnover) {
            this.annualTurnover = annualTurnover;
        }

        @Override
        public synchronized String work() {
            Organization organization = null;
            for (Organization o : collection.getCollection()) {
                if (o.getId() == elementId) {
                    organization = o;
                    break;
                }
            }
            if (organization == null) {
                return "Organizations \"lower\" specified weren't deleted\nincorrect name element";
            } else {
                for (Organization o : collection.getCollection()) {
                    if (o.getAnnualTurnover() < organization.getAnnualTurnover()) {
                        collection.getCollection().remove(o);
                        break;
                    }
                }
            }
            return "Organizations \"lower\" specified were removed";
        }
    }


    public static class CountGreaterThanType implements Workable, Serializable {
        private static final long serialVersionUID = 10L;
        private static CountGreaterThanType countGreaterThanType = new CountGreaterThanType();
        private CountGreaterThanType countGreaterThanTypeServer;
        private CollectionOrganization collection;
        private String typeS;

        private CountGreaterThanType() {
            this.collection = CollectionOrganization.getCollectionOrganization();
        }

        public static CountGreaterThanType getCountGreaterThanType() {
            return countGreaterThanType;
        }

        public void setType(String typeS) {
            this.typeS = typeS;
            countGreaterThanTypeServer = countGreaterThanType;
        }

        @Override
        public synchronized String work() {
            long count = 0;
            OrganizationType type = null;
            if (countGreaterThanTypeServer.typeS.equalsIgnoreCase(OrganizationType.COMMERCIAL.toString())) {
                type = OrganizationType.COMMERCIAL;
            } else if (countGreaterThanTypeServer.typeS.equalsIgnoreCase(OrganizationType.PUBLIC.toString())) {
                type = OrganizationType.PUBLIC;
            } else if (countGreaterThanTypeServer.typeS.equalsIgnoreCase(OrganizationType.TRUST.toString())) {
                type = OrganizationType.TRUST;
            } else {
                return "You entered an incorrect value";
            }

            OrganizationType finalType = type;
            count = collection.getCollection().stream().filter(o -> o.getType().VALUE > finalType.VALUE).count();

            if (count >= 0)
               return "Number organization with id greater " + countGreaterThanTypeServer.typeS + ": " + count;
            else
                return "Not found organization with id greater " + countGreaterThanTypeServer.typeS;
        }
    }

    public static class PrintDescending implements Workable, Serializable {
        private static final long serialVersionUID = 11L;
        private static PrintDescending printDescending = new PrintDescending();
        private CollectionOrganization collection;

        private PrintDescending() {
            this.collection = CollectionOrganization.getCollectionOrganization();
        }

        public static PrintDescending getPrintDescending() {
            return printDescending;
        }

        @Override
        public synchronized String work() {
            String str = "";
            TreeSet<Organization> deOr = (TreeSet<Organization>) collection.getCollection().descendingSet();
            for (Organization o : deOr) {
                str += o.getName()+"\n";
            }
            return str;
        }
    }


    public static class PrintFieldAscendingOfficialAddress implements Workable, Serializable {
        private static final long serialVersionUID = 12L;
        private static PrintFieldAscendingOfficialAddress printFieldAscendingOfficialAddress =
                new PrintFieldAscendingOfficialAddress();
        private CollectionOrganization collection;

        private PrintFieldAscendingOfficialAddress() {
            this.collection = CollectionOrganization.getCollectionOrganization();
        }

        public static PrintFieldAscendingOfficialAddress getPrintFieldAscendingOfficialAddress() {
            return printFieldAscendingOfficialAddress;
        }

        @Override
        public synchronized String work() {
            TreeSet<Address> treeAddress = new TreeSet<Address>(new Comparator<Address>() {
                @Override
                public int compare(Address o1, Address o2) {
                    return o1.getZipCode().compareTo(o2.toString());
                }
            });
            String str = "";
            collection.getCollection().stream().forEach(o -> treeAddress.add(o.getOfficialAddress()));
            for (Address s : treeAddress)
                str +=s+"\n";
            return str;
        }
    }

    public static class Help implements Workable, Serializable {
        private static final long serialVersionUID = 13L;
        private static Help help = new Help();
        private Help(){
        }

        public static Help getHelp(){
            return help;
        }

        @Override
        public synchronized String work() {
            return "help : вывести справку по доступным командам\n" +
                    "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                    "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                    "add: добавить новый элемент в коллекцию\n" +
                    "update_id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                    "remove_by_id id : удалить элемент из коллекции по его id\n" +
                    "clear : очистить коллекцию\n" +
                    "execute_script {file_name} : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                    "exit : завершить программу (без сохранения в файл)\n" +
                    "add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции\n" +
                    "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный\n" +
                    "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                    "count_greater_than_type type : вывести количество элементов, значение поля type которых больше заданного\n" +
                    "print_descending : вывести элементы коллекции в порядке убывания\n" +
                    "print_field_ascending_official_address: вывести значения поля officialAddress в порядке возрастания";
        }
    }

    public static class Exit implements Workable, Serializable {
        private static final long serialVersionUID = 14L;
        private static Exit exit = new Exit();

        private Exit() {
        }

        public static Exit getExit() {
            return exit;
        }

        @Override
        public synchronized String work() {
            System.exit(4221);
            return "этого мы не увидим никогда))))";
        }
    }

    public static class Info implements Workable, Serializable {
        private static Info info = new Info();
        private CollectionOrganization collection;

        private Info(){
            this.collection = CollectionOrganization.getCollectionOrganization();
        }

        public  static Info getInfo(){
            return info;
        }

        @Override
        public synchronized String work() {
            String text = "";
            text += "Collection: " + collection.getCollection().getClass().getSimpleName() + "\n";
            text += "Date of initialization: " + collection.getDate().toString() + "\n";
            text += "Number elements: " + collection.getCollection().size() + "\n";
            if(collection.getCollection().size() != 0){
                text += "The last collection element: " + collection.getCollection().last().toString() + "\n";
                text += "The first collection element: " + collection.getCollection().first().toString() + "\n";
            }
            return text;
        }
    }
}
