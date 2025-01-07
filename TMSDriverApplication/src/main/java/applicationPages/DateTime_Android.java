package applicationPages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime_Android {
	public String local_time() {
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd_HH:mm:ss");
		   LocalDateTime now = LocalDateTime.now();
		   System.out.println(dtf.format(now).toString());
		   return dtf.format(now).toString();
	}

	public static void main(String[] args) {

		DateTime_Android LTD = new DateTime_Android();
		LTD.local_time();
		// TODO Auto-generated method stub

	}

}
