import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "____________________________________________________________";
        //System.out.println("Hello from\n" + logo);
        List<Task> tasks = new ArrayList<>();
        System.out.println("\t" + line);
        System.out.println("\t Hello! I'm Duke");
        System.out.println("\t What can I do for you?");
        System.out.println("\t" + line);
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String input = scanner.nextLine();  // Read user input
        while (!(input.equals("bye"))) { //Continue reading until user inputs bye
            System.out.println("\t" + line);
            if (input.equals("list")) {
                if (tasks.isEmpty())
                    throw new DukeException("No tasks yet!");
                else {
                    System.out.println("\t Here are the tasks in your list:");
                    for (int i = 1; i <= tasks.size(); i++) { // looping to print all the saved tasks
                        System.out.println("\t " + i + "." + tasks.get(i - 1).toString());
                    }
                }
            } else {
                String[] splitted = input.split(" ", 2);
                Task newTask = null;
                try {
                    switch (splitted[0]) {
                        case "done":
                            if (splitted.length == 2) {
                                int taskNb = Integer.parseInt(splitted[1]);
                                if (taskNb <= tasks.size() && taskNb > 0) {
                                    tasks.get(taskNb - 1).markAsDone();
                                    System.out.println("\t Nice! I've marked this task as done:");
                                    System.out.println("\t " + tasks.get(taskNb - 1).toString());
                                } else
                                    throw new DukeException("Enter a valid task number after done, between 1 and " + tasks.size());
                            } else throw new DukeException("Need a task number after done!");
                            break;
                        case "todo":
                            if ((splitted.length == 1) || splitted[1].isBlank())
                                throw new DukeException("The description of a todo cannot be empty.");
                            newTask = new Todo(splitted[1]);
                            break;
                        case "deadline":
                            if ((splitted.length == 1) || splitted[1].isBlank())
                                throw new DukeException("The description of a deadline cannot be empty.");
                            String getBy[] = splitted[1].split("/by ", 2);
                            if (getBy.length < 2)
                                throw new DukeException("The description of a deadline must contain /by date!");
                            newTask = new Deadline(getBy[0], getBy[1]);

                            break;
                        case "event":
                            if ((splitted.length == 1) || splitted[1].isBlank())
                                throw new DukeException("The description of an event cannot be empty, and it must contain /at");
                            String getAt[] = splitted[1].split("/at ", 2);
                            if (getAt.length < 2)
                                throw new DukeException("The description of a deadline must contain /at data and time from-to!");
                            newTask = new Event(getAt[0], getAt[1]);
                            break;
                        default:
                            throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                if (newTask != null) {
                    tasks.add(newTask);
                    System.out.println("\t Got it. I've added this task: ");
                    System.out.println("\t " + newTask.toString());
                    System.out.println(tasks.size() == 1 ? "\t Now you have " + tasks.size() + " task in the list." : "\t Now you have " + tasks.size() + " tasks in the list.");
                }
            }
            System.out.println("\t" + line);
            input = scanner.nextLine();  // Read next user input
        }
        System.out.println("\t" + line);
        System.out.println("\t Bye. Hope to see you again soon!");
        System.out.println("\t" + line);
    }
}
