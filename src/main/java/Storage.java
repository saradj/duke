import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    Path path ;
    List<String> contentSoFar;
    List<Task> tasks;

    public Storage(String filePath){
        path = Paths.get(filePath);
        tasks=new ArrayList<>();
    }
    public List<Task> load() throws DukeException {

        try {
            contentSoFar = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));// getting the data from the hard disk until now
        } catch (IOException e) {
            e.printStackTrace();
            throw new DukeException(" ");
        }
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
        return tasks;
    }
    public Path getPath(){
        return path;
    }
    public void changeContent(int taskNb) throws DukeException {
        try {
            contentSoFar = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
            contentSoFar.set(taskNb - 1, tasks.get(taskNb - 1).printInFile()); // changing the file content
            Files.write(path, contentSoFar, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        throw new DukeException("Error while reading/writing in the duke.txt file");
    }
    }
    public  void addCommandInFile(String task) throws IOException {
        contentSoFar = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
        contentSoFar.add(task);
        Files.write(path, contentSoFar, StandardCharsets.UTF_8);
    }

}
