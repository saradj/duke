import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Event extends Task {
    private String at;
    private Date date;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.date = getDate(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + getDateString(date) + ")";
    }

    public String printInFile() {
        return this.isDone() ? "E|1|" + getDescription() + "|" + this.getDateString(date) : "E|0|" + this.getDescription() + "|" + getDateString(date);
    }

    private String getDateString(Date date) {
        if (date == null)
            return at;
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String pattern = at.length() > 11 ? "d'" + getDaySuffix(localDate.getDayOfMonth()) + "' 'of' MMMM yyyy, ha " : "d'" + getDaySuffix(localDate.getDayOfMonth()) + "' 'of' MMMM yyyy";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }
}