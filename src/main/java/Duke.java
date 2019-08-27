import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
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
                    System.out.println("\t No tasks yet!");
                else {
                    System.out.println("\t Here are the tasks in your list:");
                    for (int i = 1; i <= tasks.size(); i++) { // looping to print all the saved tasks
                        System.out.println("\t " + i + "." + tasks.get(i - 1).toString());
                    }
                }
            } else {
                if (input.isBlank())
                    System.out.println("Please enter a valid task");
                else {
                    String[] spllited = input.split(" ", 2);
                    Task newTask = null;

                    switch (spllited[0]) {
                        case "done":
                            if (input.length() > 5) {
                                int taskNb = Integer.parseInt(spllited[1]);
                                if (taskNb <= tasks.size() && taskNb > 0) {
                                    tasks.get(taskNb - 1).markAsDone();
                                    System.out.println("\t Nice! I've marked this task as done:");
                                    System.out.println("\t " + tasks.get(taskNb - 1).toString());
                                } else System.out.println("Enter a valid task number");
                            } else System.out.println("Need to enter a task number after done!");
                            break;
                        case "todo":
                            newTask = new Todo(spllited[1]);
                            break;
                        case "deadline":
                            String getBy[] = spllited[1].split("/by ", 2);
                            newTask = new Deadline(getBy[0], getBy[1]);

                            break;
                        case "event":
                            String getAt[] = spllited[1].split("/at ", 2);
                            newTask = new Event(getAt[0], getAt[1]);
                            break;
                        default:
                            System.out.println("task type invalid!");
                    }
                    if (newTask != null) {
                        tasks.add(newTask);
                        System.out.println("\t Got it. I've added this task: ");
                        System.out.println("\t "+ newTask.toString());
                        System.out.println("\t Now you have " + tasks.size() + " tasks in the list.");
                    }
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
