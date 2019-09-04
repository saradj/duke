public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("\t Bye. Hope to see you again soon!");
    }
}
