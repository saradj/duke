import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a list of {@link Task}s added by {@link Duke}
 */
public class TaskList {
    
    private List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the {@link TaskList}
     * @param task {@link Task} to be added to the list
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Returns the number of {@link Task}s in the {@link TaskList} so far
     * @return an integer indicating the size of the list of {@link Task}s stored
     */
    public int size() {
        return taskList.size();
    }


    /**
     * Marks a task as completed if the user finished it
     * @param taskNb the number of the {@link Task} in the {@link TaskList} that was completed
     */
    public void markTaskDone(int taskNb) {
        taskList.get(taskNb).markAsDone();
    }

    /**
     * Returns the {@link Task} at the position indicated by the taskNb
     * @param taskNb the position of the {@link Task} requested in the {@link TaskList}
     * @return the requested {@link Task}
     */
    public Task getTask(int taskNb) {
        return taskList.get(taskNb);
    }

    /**
     * Returns an immutable list of all the {@link Task}s in the {@link TaskList} so far
     * @return unmodifiable {@link ArrayList} of {@link Task} listed so far
     */
    public List<Task> getAllTasks() {
        return  Collections.unmodifiableList(new ArrayList<>(taskList));
    }

    /**
     * Returns the removed {@link Task} from position taskNb in the {@link TaskList}
     * @param taskNb the position of the {@link Task} to be removed from the {@link TaskList}
     * @return Task the task that was removed
     */
    public Task removeTask(int taskNb) {
        return taskList.remove(taskNb);
    }


}
