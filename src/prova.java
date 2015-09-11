
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class prova {
	
	public static void main()
	{
		Calendar cal = Calendar.getInstance();
		Date d=new Date();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		System.out.println(dateFormat.format(d));
	}

}
