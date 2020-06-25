package Client;

import Client.Application.Controllers.RootController;
import Client.Application.Main;
import Code8.Command;
import Code8.Organization;
import Code8.Workable;
import javafx.scene.control.Label;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * this class implements work in interactive mode as well as reading from a file
 */
public class Work {
    private static String strInGoCommand;
    private static BufferedReader reader;
    private static ArrayList<String> scriptNameList = new ArrayList<String>(2),
    commandInScriptList = new ArrayList<String>(4);
    private static Deque<BufferedReader> stackReader = new ArrayDeque<BufferedReader>();

    /**
     * this enum has two type work: InteractiveWork, ScriptWork
     */
    public enum Realization{
        InteractiveWork,
        ScriptWork;
    }

    /**
     * this method runs InteractiveWork
     */
    public static Workable goInteractiveWork(String commandName){
//
        RootController rootController = Main.getRootController();
        Workable work = null;
        reader = new BufferedReader(new InputStreamReader(System.in));

                try {
                    work = goCommand(commandName, Realization.InteractiveWork);
                    if(work instanceof Command.Add) {
                        if(((Command.Add) work).getOrg()==null)
                            return null;
                    }
                    if (work instanceof Command.UpdateElement){
                        if(((Command.UpdateElement) work).getOrg()==null)
                            return null;
                    }
                    if (work instanceof Command.AddIfMin) {
                        if(((Command.AddIfMin) work).getOrg()==null)
                            return null;
                    }

                } catch (IOException e) {
                    System.out.println("*happens* IOException. Repeat input, please:");
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("*happens* stringIndexOutOfBoundsException. Repeat input, please:");
                    e.printStackTrace();
                } catch (Exception e) {
                    System.out.println("*happens* Exception. Repeat input, please:");
                }

        return work;
    }

    /**
     * this method runs ScriptWork
     * @param reader this parameter can implement both keyboard input and reading from a file
     */
    public static void goScriptWork(BufferedReader reader){
        Workable work;
        String str;
        while(true){
            try {
                String commandName = reader.readLine();
                work = goCommand(commandName, Realization.ScriptWork);

                if (work == null) {
                } /*else if (work instanceof Command.Exit) {
                    Command.ExecuteScript.getExecuteScript().getListWork().add(work);
                    CloseReader();
                    break;
                } */else if (!(work instanceof Command.ExecuteScript))
                    Command.ExecuteScript.getExecuteScript().getListWork().add(work);

                if (strInGoCommand == null)
                    break;
            } catch (IOException e) {
                System.out.println("*happens* IOException in ScriptWork");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("*happens* StringIndexOutOfBoundsException in ScriptWork");
            } catch (Exception e) {
                System.out.println("*happens* Exception in Script File");
            }
        }
    }

    /**
     * /**
     * this method calls the required method from the class implements Command
     * @param realization this parameter is enum Work.Realization. It parameter specifies how the work is performed, either interactively or using a script from a file
     * @return return if command completed and return false if was a problem
     * @throws IOException this exception is handled higher up in the stack
     * @throws StringIndexOutOfBoundsException this exception is handled higher up in the stack
     */
    private static Workable goCommand(String commandName, Realization realization) throws IOException, StringIndexOutOfBoundsException{
        String str = commandName;
        strInGoCommand = str;
        if(str == null){
            //Work.reader = new BufferedReader(new InputStreamReader(System.in));
            return null;
        }else if(str.equals("")){
            return null;
        }else if(str.equalsIgnoreCase("f")){
                F.getF();
                return null;
        }else if(str.equals("start")){
            Workable work = Command.Start.getStart();
            return work;
        }else if(str.equals("help")){
            Workable work = Command.Help.getHelp();
            return work;
        }else if(str.equals("info")){
            Workable work = Command.Info.getInfo();
            return work;
        }else if(str.equals("show")){
            Workable work = Command.Show.getShow();
            return work;
        }else if(str.equals("clear")){
            Workable work = Command.Clear.getClear();
            return work;
        }else if(str.equals("exit")){
            System.out.println("тут мои полномочия, все...");
            Workable work = Command.Exit.getExit();
            return work;
        }else if(str.equals("print_descending")){
            Workable work= Command.PrintDescending.getPrintDescending();
            return work;
        }else if(str.equals("add")){
            Command.Add work = Command.Add.getAdd();

            if(realization.equals(Realization.InteractiveWork))
                return work;

            work.setOrganization(CreateElement.createElement(reader, realization));
            return work;
        }else if(str.substring(0, 9).equals("update_id")){
            Command.UpdateElement work = Command.UpdateElement.getUpdateElement();

            if(realization.equals(Realization.InteractiveWork))
                return work;

            work.setId(Long.parseLong(str.substring(10)));
            work.setOrg(CreateElement.createElement(reader, realization));
            return work;
        }else if(str.substring(0, 10).equals("add_if_min")){
            Command.AddIfMin command = Command.AddIfMin.getAddIfMin();

            if(realization.equals(Realization.InteractiveWork))
                return command;

            command.setOrganization(CreateElement.createElement(reader, realization));
            return command;
        }else if(str.substring(0,12).equals("remove_by_id")){
            Command.Remove command = Command.Remove.getRemove();

            if(realization.equals(Realization.InteractiveWork))
                return command;

            command.setId(Long.parseLong(str.substring(13)));
            return command;
        }else if(str.substring(0, 12).equals("remove_lower")){
            Command.RemoveLower command = Command.RemoveLower.getRemoveLower();

            if(realization.equals(Realization.InteractiveWork))
                return command;

            command.setElementId(Long.parseLong(str.substring(13)));
            return command;
        }else if(str.substring(0, 14).equals("execute_script")){
            if (realization == Realization.ScriptWork && scriptNameList.contains(str.substring(15))){
                System.out.println("*happens* Recursion in " + str.substring(15));
                return null;
            }
            Command.ExecuteScript command = Command.ExecuteScript.getExecuteScript();

            if(realization.equals(Realization.InteractiveWork)) {
                scriptNameList.add(command.getPath());
                strInGoCommand = str;
                return command;
            }

            command.setFilePath(str.substring(15));
            scriptNameList.add(str.substring(15));
            //stackReader.push(reader);
            System.out.println(command.work() + " " + str.substring(15));
            //reader = stackReader.pollLast();
            strInGoCommand = str;
            return command;
        }else if(str.substring(0, 14).equals("remove_greater")){
            Command.RemoveGreater command = Command.RemoveGreater.getRemoveGreater();

            if(realization.equals(Realization.InteractiveWork))
                return command;

            command.setElementId(Long.parseLong(str.substring(15)));
            return command;
        }else if(str.substring(0, 23).equals("count_greater_than_type")){
            Command.CountGreaterThanType command = Command.CountGreaterThanType.getCountGreaterThanType();

            if(realization.equals(Realization.InteractiveWork))
                return command;

            command.setType(str.substring(24));
            return command;
        }else if(str.substring(0, 38).equals("print_field_ascending_official_address")){
            Command.PrintFieldAscendingOfficialAddress command =
                    Command.PrintFieldAscendingOfficialAddress.getPrintFieldAscendingOfficialAddress();
            return command;
        }else {
            return null;
        }
    }

    public static void CloseReader(){
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("*happens* IOException close");
        }
    }

    public static void removeScriptNameList(){
        scriptNameList.removeAll(scriptNameList);
    }
}
