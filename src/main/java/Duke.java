
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        List<Task> tasks = new ArrayList<>();
        Path path = Paths.get("C:\\Users\\Sara\\duke\\duke.txt");
        try {
            List<String> contentSoFar = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));// getting the data from the hard disk until now
            for (String next : contentSoFar) {
                String[] words = next.split("\\|");// splitting each line to extract the task type - words[0], done or not words-[1], description- words[2] and possibly due date words[3]
                switch (words[0]) {
                    case "T":
                        tasks.add(new Todo(words[2]));
                        if (words[1].equals("1"))
                            tasks.get(tasks.size() - 1).markAsDone();
                        break;
                    case "D":
                        tasks.add(new Deadline(words[2], words[3]));
                        if (words[1].equals("1"))
                            tasks.get(tasks.size() - 1).markAsDone();
                        break;
                    default:
                        tasks.add(new Event(words[2], words[3]));
                        if (words[1].equals("1"))
                            tasks.get(tasks.size() - 1).markAsDone();
                        break;
                }
            }
        } catch (IOException E) {
            // No old tasks, ready to start now!
        }
        System.out.println("\t" + line);
        System.out.println("\t Hello! I'm Duke");
        System.out.println("\t What can I do for you?");
        System.out.println("\t" + line);
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String input = scanner.nextLine();  // Read user input
        while (!(input.equals("bye"))) { //Continue reading until user inputs until bye
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
                String[] splitted = input.split(" ", 2);// splitted contains the keyword and the rest (description or task number)
                Task newTask = null;
                try {
                    try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                            new FileOutputStream("duke.txt", true), StandardCharsets.UTF_8))) {
                        switch (splitted[0]) { // switching on the keyword
                            case "done":
                                if (splitted.length == 2) {
                                    int taskNb = Integer.parseInt(splitted[1]);
                                    if (taskNb <= tasks.size() && taskNb > 0) {
                                        tasks.get(taskNb - 1).markAsDone();
                                        List<String> fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
                                        fileContent.set(taskNb - 1, tasks.get(taskNb - 1).printInFile()); // changing the file content
                                        Files.write(path, fileContent, StandardCharsets.UTF_8);
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
                            case "find":
                                if(splitted.length==2){
                                    StringBuilder sb= new StringBuilder();
                                    int i=1;
                                for( Task task : tasks){
                                    if(task.getDescription().contains(splitted[1])){
                                        sb.append(i++).append(".").append(task.toString());
                                        sb.append(System.lineSeparator());
                                    }
                                }if(sb.length()==0){
                                        System.out.println("No matching tasks found! ");
                                    }else
                                        System.out.println("\t Here are the matching tasks in your list:");
                                sb.setLength(sb.length()-1);// to remove the last new line
                                    System.out.println(sb.toString());
                                } else throw new DukeException("Need a word to find! ");
                                break;
                            case "delete":
                                if(splitted.length==2) {
                                    int taskNb = Integer.parseInt(splitted[1]);
                                    if (taskNb <= tasks.size() && taskNb > 0) {
                                Task removed=tasks.remove(taskNb-1);
                                        List<String> fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
                                        fileContent.remove(taskNb-1); // changing the file content
                                        Files.write(path, fileContent, StandardCharsets.UTF_8);
                                        System.out.println("\t Noted. I've removed this task:");
                                        System.out.println("\t " + removed.toString());
                                        System.out.println(tasks.size() == 1 ? "\t Now you have " + tasks.size() + " task in the list." : "\t Now you have " + tasks.size() + " tasks in the list.");
                                    } else
                                        throw new DukeException("Enter a valid task number after done, between 1 and " + tasks.size());
                                }else throw new DukeException("Need a task number after done!");
                                break;
                            default:
                                throw new DukeException("I'm sorry, but I don't know what that means :-(");
                        }
                        if (newTask != null) {
                            tasks.add(newTask);
                            System.out.println("\t Got it. I've added this task: ");
                            System.out.println("\t " + newTask.toString());
                            System.out.println(tasks.size() == 1 ? "\t Now you have " + tasks.size() + " task in the list." : "\t Now you have " + tasks.size() + " tasks in the list.");
                            writer.write(newTask.printInFile());
                            writer.newLine();
                        }
                        writer.close();
                    } catch (IOException e) {
                        System.out.println("IO problems, can not open file:" + e.getMessage());
                    }
                } catch (Exception e) {
                    System.out.println("exception caught: "+ e.getMessage());
                    e.printStackTrace();
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
