package dateandtime;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeDemo {

	public DateTimeDemo() {
		super();
		
		LocalDateTime today = LocalDateTime.now();
		System.out.println(today);
		System.out.println("hours : "+today.getHour());
		// LocalDateTime time is immutable
		LocalDateTime today1 = today.minusDays(7L);
		System.out.println("today before 7 days : "+today1);
		// convert LocalDateTime to and from TimeStamp
		System.out.println("LocalDateTime to TimeStamp  : "+Timestamp.valueOf(today)); 
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		System.out.println("TimeStamp to LocalDateTime : "+ts.toLocalDateTime()); 
		// LocalDateTime with specific DateTimeFormatter
		String today_with_format = today.format(DateTimeFormatter.ofPattern("hh a",Locale.US));
		System.out.println("today with US specific format : "+today_with_format); 
		String today_with_format1 = today.format(DateTimeFormatter.ofPattern("hh a",Locale.CANADA_FRENCH));
		System.out.println("today with france specific format : "+today_with_format1);
		// convert LocalDateTime to and from LocalDate
		System.out.println("LocalDateTime to LocalDate  : "+today.toLocalDate().toString());
		// convert LocalDateTime to and from LocalTime
		System.out.println("LocalDateTime to LocalDate  : "+today.toLocalTime().toString());
		
	}

}
