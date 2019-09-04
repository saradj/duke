import java.util.ArrayList;
import java.util.List;

public class TaskList {
    
    private List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public int size() {
        return taskList.size();
    }

    public void markTaskDone(int taskNb) {
        taskList.get(taskNb).markAsDone();
    }

    public Task getTask(int taskNb) {
        return taskList.get(taskNb);
    }

    public List<Task> getAllTasks() {
        return taskList;
    }

    public Task removeTask(int taskNb) {
        return taskList.remove(taskNb);
    }


}
