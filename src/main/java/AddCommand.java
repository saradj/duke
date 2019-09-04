import java.io.IOException;
public class AddCommand extends Command {

    private Task task;
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.addTask(task);
        ui.showAddCommand(task.toString(), taskList.size());
        try {
            storage.addCommandInFile(task.printInFile());
        } catch (IOException e) {
            throw new DukeException("Error while adding the command to the duke.txt file");
        }
    }
}
