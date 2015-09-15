import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Locale;

public class Test {
	
	public static void main(String[] args) {
		
//		System.out.println("yes");
		
		Test.test_Regex__Audio_File();
//		Test.test_Regex_Photo_Memo();
		
//		test_Regex();
		
//		return 0;
		
	}
	
	public static void test_Regex() {
		
//		System.out.println("regex");
		
//		String fmt_FileName_PlayMemo = "2014";	// 
//		String fmt_FileName_PlayMemo = "\\d{4}";	// not
//		String fmt_FileName_PlayMemo = "^\\@";	// not
//		String fmt_FileName_PlayMemo = "^@";	// not
//		String fmt_FileName_PlayMemo = "^@\\d{4}";	// not
//		String fmt_FileName_PlayMemo = "^@\\d{4}.+wav";	// not
		String fmt_FileName_PlayMemo = "^@(\\d{4}.+wav)";	// not
		
//		String text = "@2014-10-03_10-01-45-933.wav";
		String text = "@2014-10-03_10-01-45-933.wav :N :DONE";
		
		Pattern p = Pattern.compile(fmt_FileName_PlayMemo);
		
		Matcher m = p.matcher(text);

		if (m.find()) {
//			if (m.matches()) {
			
			System.out.println("matches");
			
			System.out.println("m.group(0) => " + m.group(0));
			System.out.println("m.group(1) => " + m.group(1));
			
		} else {
			
			System.out.println("not");
			
		}
		
	}
	
	public static void 
	test_Regex_Photo_Memo() {
		
//		System.out.println("regex");
		
//		String fmt_FileName_PlayMemo = "2014";	// 
//		String fmt_FileName_PlayMemo = "\\d{4}";	// not
//		String fmt_FileName_PlayMemo = "^\\@";	// not
//		String fmt_FileName_PlayMemo = "^@";	// not
//		String fmt_FileName_PlayMemo = "^@\\d{4}";	// not
//		String fmt_FileName_PlayMemo = "^@\\d{4}.+wav";	// not
//		String fmt_FileName_PlayMemo = "^@(\\d{4}.+wav)";	// not
		
		String fmt_FileName_PhotoMemo = "^&(\\d+)?";	// => 39
//		String fmt_FileName_PhotoMemo = "^&(\\d+?)";	// => 3
		
//		String text = "@2014-10-03_10-01-45-933.wav";
		String text = "&39 :prog :m :A IFM11 9/2.0 Delete";
		
		Pattern p = Pattern.compile(fmt_FileName_PhotoMemo);
		
		Matcher m = p.matcher(text);
		
		if (m.find()) {
//			if (m.matches()) {
			
			System.out.println("matches");
			
			System.out.println("m.group(0) => " + m.group(0));
			System.out.println("m.group(1) => " + m.group(1));
			
		} else {
			
			System.out.println("not");
			
		}
		
	}//test_Regex_Photo_Memo

	public static void
	test_Regex__Audio_File() {
		
		///////////////////////////////////
		//
		// setup
		//
		///////////////////////////////////
		String regex = "^@[\\d-_]+\\.wav$";
		
		String fname = "@2015-09-14_16-52-30-517.wav";
		String fname2 = "@2015-09-14_16-52-30-517.wav :DONE";	// hit with '@[\d-_]+\.wav'

		//ref http://www.ocpsoft.org/opensource/guide-to-regular-expressions-in-java-part-1/
		Pattern p = Pattern.compile(regex);
		
		Matcher m = p.matcher(fname);
		Matcher m2 = p.matcher(fname2);
		
		///////////////////////////////////
		//
		// report
		//
		///////////////////////////////////
		String msg;
		msg = String.format(Locale.JAPAN, "[%s : %d] regex => %s", Thread
				.currentThread().getStackTrace()[1].getFileName(), Thread
				.currentThread().getStackTrace()[1].getLineNumber(), regex);

		System.out.println(msg);

//		String msg;
		msg = String.format(Locale.JAPAN, "[%s : %d] fname = %s \n fname2 = %s", Thread
				.currentThread().getStackTrace()[1].getFileName(), Thread
				.currentThread().getStackTrace()[1].getLineNumber(), fname, fname2);

		System.out.println(msg);
		
		///////////////////////////////////
		//
		// regex
		//
		///////////////////////////////////
//		String msg;
		msg = String.format(Locale.JAPAN, "[%s : %d] fname => %d / fname2 => %d", Thread
				.currentThread().getStackTrace()[1].getFileName(), Thread
				.currentThread().getStackTrace()[1].getLineNumber(), 
				m.groupCount(), m2.groupCount());

		System.out.println(msg);
		
		int count = 0;
		
		while (m.find()) {
			
//			String msg;
			msg = String.format(Locale.JAPAN, "[%s : %d] m.find => %s", Thread
					.currentThread().getStackTrace()[1].getFileName(), Thread
					.currentThread().getStackTrace()[1].getLineNumber(), m.group());

			System.out.println(msg);
			
			
//			System.out.println("Found a " + m.group() + ".");
//			animals.add(m.group());
			
			count ++;
			
		}
		
//		String msg;
		msg = String.format(Locale.JAPAN, "[%s : %d] count(m) => %d", Thread
				.currentThread().getStackTrace()[1].getFileName(), Thread
				.currentThread().getStackTrace()[1].getLineNumber(), count);

		System.out.println(msg);
		
		///////////////////////////////////
		//
		// m2
		//
		///////////////////////////////////
		count = 0;
		
		while (m.find()) {
			
//			String msg;
			msg = String.format(Locale.JAPAN, "[%s : %d] m.find => %s", Thread
					.currentThread().getStackTrace()[1].getFileName(), Thread
					.currentThread().getStackTrace()[1].getLineNumber(), m.group());
			
			System.out.println(msg);
			
			
//			System.out.println("Found a " + m.group() + ".");
//			animals.add(m.group());
			
			count ++;
			
		}
		
//		String msg;
		msg = String.format(Locale.JAPAN, "[%s : %d] count(m) => %d", Thread
				.currentThread().getStackTrace()[1].getFileName(), Thread
				.currentThread().getStackTrace()[1].getLineNumber(), count);
		
		System.out.println(msg);
		
		
	}//test_Regex__Audio_File
	
}//public class Test
