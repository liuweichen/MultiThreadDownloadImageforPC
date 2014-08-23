package picdownload.util;
import java.util.Date;

public class DateString {
	public static final String strBeforeAday;
	public static final String strDate;
	public static final String strAdd6Hours;
	static {
		long timeBeforeAday = System.currentTimeMillis() - 24 * 60 * 60 * 1000;
		Date dateBeforeAday = new Date(timeBeforeAday);

		String dt1 = dateBeforeAday.toString();
		dt1 = dt1.replace("Jan", "01");
		dt1 = dt1.replace("Feb", "02");
		dt1 = dt1.replace("Mar", "03");
		dt1 = dt1.replace("Apr", "04");
		dt1 = dt1.replace("May", "05");
		dt1 = dt1.replace("Jun", "06");
		dt1 = dt1.replace("Jul", "07");
		dt1 = dt1.replace("Aug", "08");
		dt1 = dt1.replace("Sep", "09");
		dt1 = dt1.replace("Oct", "10");
		dt1 = dt1.replace("Nov", "11");
		dt1 = dt1.replace("Dec", "12");

		String[] dt2 = dt1.split(" ");
		// Wed May 07 11:35:39 CST 2014
		strBeforeAday = dt2[5] + dt2[1] + dt2[2];
		
		long timeAdd6Hours = System.currentTimeMillis()  - (24-6) * 60 * 60 * 1000;
		Date dateAdd6Hours = new Date(timeAdd6Hours);

		String dt7 = dateAdd6Hours.toString();
		dt7 = dt7.replace("Jan", "01");
		dt7 = dt7.replace("Feb", "02");
		dt7 = dt7.replace("Mar", "03");
		dt7 = dt7.replace("Apr", "04");
		dt7 = dt7.replace("May", "05");
		dt7 = dt7.replace("Jun", "06");
		dt7 = dt7.replace("Jul", "07");
		dt7 = dt7.replace("Aug", "08");
		dt7 = dt7.replace("Sep", "09");
		dt7 = dt7.replace("Oct", "10");
		dt7 = dt7.replace("Nov", "11");
		dt7 = dt7.replace("Dec", "12");

		String[] dt8 = dt7.split(" ");
		// Wed May 07 11:35:39 CST 2014

		strAdd6Hours = dt8[5] + dt8[1] + dt8[2];

		String dtNow = new Date().toString();
		dtNow = dtNow.replace("Jan", "01");
		dtNow = dtNow.replace("Feb", "02");
		dtNow = dtNow.replace("Mar", "03");
		dtNow = dtNow.replace("Apr", "04");
		dtNow = dtNow.replace("May", "05");
		dtNow = dtNow.replace("Jun", "06");
		dtNow = dtNow.replace("Jul", "07");
		dtNow = dtNow.replace("Aug", "08");
		dtNow = dtNow.replace("Sep", "09");
		dtNow = dtNow.replace("Oct", "10");
		dtNow = dtNow.replace("Nov", "11");
		dtNow = dtNow.replace("Dec", "12");

		String[] dtNow2 = dtNow.split(" ");
		// Wed May 07 11:35:39 CST 2014

		strDate = dtNow2[5] + dtNow2[1] + dtNow2[2];
	}
}
