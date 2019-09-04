import java.io.IOException;
import java.util.List;

public class FindCommand extends Command {
    String toFind;
    public FindCommand(String toFind){
        this.toFind=toFind;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage)  {
        StringBuilder sb= new StringBuilder();
        int i=1;
        for( Task task : taskList.getAllTasks()){
            if(task.getDescription().contains(toFind)){
                sb.append("\t ").append(i++).append(".").append(task.toString());
                sb.append(System.lineSeparator());
            }
        }if(sb.length()==0){
            System.out.println("No matching tasks found! ");
        }else
            System.out.println("\t Here are the matching tasks in your list:");
        sb.setLength(sb.length()-1);// to remove the last new line
        System.out.println(sb.toString());
    }
}
