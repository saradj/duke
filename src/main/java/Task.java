import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    public String printInFile() {
        return "";
    }

    public boolean isDone() {
        return isDone;
    }

    static String getDaySuffix(int n) {
        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }

    static Date getDate(String date) {
        DateFormat dateFormat = (date.length() > 11) ? new SimpleDateFormat("dd/MM/yyyy hhmm") : new SimpleDateFormat("dd/MM/yyyy");
        try {
             dateFormat.parse(date);
            return dateFormat.parse(date);
        } catch (ParseException e) {
            //case the date was not valid!
        }
        return null;
    }
}