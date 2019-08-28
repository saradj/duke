import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

public class Deadline extends Task {
    protected String by;
    protected Date date;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.date=getDate(by);
    }

    @Override
    public String toString(){
       // String[] date_time= by.split(" ");
      //  if(date_time[0].matches("(.*)/(.*)/(.*)"))
       //getDate(date_time[0].split("/"));

            return ( getDate(by)==null)? "[D]" + super.toString() + "(by: " + by  + ")": "[D]" + super.toString() + "(by: " +getDateString(date) + ")";
    }
    public Date getDate(String date)  {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hhmm");
try {
        Date d = dateFormat.parse(by);
        return d;}
catch (ParseException e){

}return null;
    }
    private String getDaySuffix(int n){
        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
            case 1:  return "st";
            case 2:  return "nd";
            case 3:  return "rd";
            default: return "th";
        }
    }
    private String getDateString(Date date){
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        SimpleDateFormat formatter = new SimpleDateFormat("d'"+getDaySuffix(localDate.getDayOfMonth())+"' 'of' MMMM yyyy, ha ");
        return formatter.format(date);
    }
    /*
private String getDate(String[] date){
        StringBuilder sb = new StringBuilder();
    if(date.length!=3)
        return null;
    int month= Integer.valueOf(date[1]);
    int day= Integer.valueOf(date[0] );
    int year= Integer.valueOf(date[2]);
    if(day>31||day<1||month>12||month<1||year<2019)
        return null;//case if date is wrong
switch (date[0]){
    case "1":
    case "21":
    case "31": sb.append(date[0]).append("st of ");
    break;
    case "2":
    case "22":sb.append(date[0]).append("nd of ");
    break;
    case "3":
    case "23": sb.append(date[0]).append("rd of ");
    break;
    default:
        sb.append(date[0]).append("th of ");
}

    sb.append(Month.of(month).getDisplayName( TextStyle.FULL , Locale.US )).append(" ").append(year);

    return sb.toString();}
private String getTime(String time){
        int
}*/
    @Override
    public String printInFile() {
        return this.isDone ? "D|1|" + this.getDescription() + "|" + this.by : "D|0|" + this.getDescription() + "|" + this.by;
    }
}
