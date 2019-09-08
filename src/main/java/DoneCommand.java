/**
 * Represents a specific {@link Command} used to mark a {@link Task} as done
 */
public class DoneCommand extends Command {
    private int taskNb;

    public DoneCommand(int taskNb) {
        this.taskNb = taskNb;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskNb < taskList.size() && taskNb >= 0) {
            taskList.markTaskDone(taskNb);
            ui.showMarkDone(taskList.getTask(taskNb).toString());
            storage.changeContent(taskNb);
        } else
            throw new DukeException("Enter a valid task number after done, between 1 and " + taskList.size());
    }
}
