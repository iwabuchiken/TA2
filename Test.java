/*
 * javac C:\WORKS\WS\Eclipse_Luna\TA2\Test.java
 * 
 * ref http://stackoverflow.com/questions/9395207/how-to-include-jar-files-with-java-file-and-compile-in-command-prompt answered Feb 22 '12 at 14:08
 * javac -cp "commons-lang-2.1.jar" C:\WORKS\WS\Eclipse_Luna\TA2\Test.java
 * java -cp C:\WORKS\WS\Eclipse_Luna\TA2 Test
 * 
 * #use -cp in java command
 * #ref http://stackoverflow.com/questions/219585/setting-multiple-jars-in-java-classpath answered Oct 20 '08 at 20:32
 * java -cp "C:\WORKS\WS\Eclipse_Luna\TA2;./*" Test
 * 
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

//import ta2.items.ListItem;
//import ta2.main.R;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Test {
	
	public static void main(String[] args) {
		
//		System.out.println("yes");
	
		Test.test_Conv_FileName_2_TimeLabel();
		
//		Test.test_Regex__Audio_File();
//		Test.test_Regex_Photo_Memo();
		
//		test_Regex();
		
//		return 0;
		
	}
	
	public static void 
	test_Conv_FileName_2_TimeLabel() {
		
		String text  = "def Record_2015-12-06_08-18-04.mp3 abcde";
//		String text  = "def Record_2015-12-05.mp3 abcde";
		
		Pattern p = Pattern.compile("^.+_(\\d{4}.+)\\.mp3");
//		Pattern p = Pattern.compile("^.+_(\\d{4}.+mp3)");
//		Pattern p = Pattern.compile("^(レコード_\\d{4}.+mp3)");
//		p = Pattern.compile("^@recorded_(\\d{4}.+wav)");
		Matcher m = p.matcher(text);
//		"^@(\\d{4}.+wav)"
		
		if (m.find()) {
//			if (m.matches()) {

//			String msg;
//			msg = String.format(Locale.JAPAN, "[%s : %d] m.groupCount() => %d", Thread
//					.currentThread().getStackTrace()[1].getFileName(), Thread
//					.currentThread().getStackTrace()[1].getLineNumber(), 
//					m.groupCount()
//					);
//
//			System.out.println(msg);
//			
////			String msg;
//			msg = String.format(Locale.JAPAN, "[%s : %d] group(0) => %s", Thread
//					.currentThread().getStackTrace()[1].getFileName(), Thread
//					.currentThread().getStackTrace()[1].getLineNumber(), m.group(0));
//
//			System.out.println(msg);
//			
//			msg = String.format(Locale.JAPAN, "[%s : %d] group(1) => %s", Thread
//					.currentThread().getStackTrace()[1].getFileName(), Thread
//					.currentThread().getStackTrace()[1].getLineNumber(), m.group(1));
//			
//			System.out.println(msg);
			
			//			[Test.java : 51] m.groupCount() => 1
			//			[Test.java : 60] group(0) => def Record_2015-12-06_08-18-04.mp3
			//			[Test.java : 66] group(1) => 2015-12-06_08-18-04
			
//			file.lastModified()
//			file = レコード_2015-12-06_08-18-04.mp3 (2015/12/06 08:18:36.000) comp = -2

		} else {

			String msg;
			msg = String.format(Locale.JAPAN, "[%s : %d] no matches", Thread
					.currentThread().getStackTrace()[1].getFileName(), Thread
					.currentThread().getStackTrace()[1].getLineNumber());

			System.out.println(msg);
			
			return;
			
		}		
		
		///////////////////////////////////
		//
		// convert
		//
		///////////////////////////////////
		String orig = m.group(1);
		
		String msg;
		msg = String.format(Locale.JAPAN, "[%s : %d] match 1 => %s", Thread
				.currentThread().getStackTrace()[1].getFileName(), Thread
				.currentThread().getStackTrace()[1].getLineNumber(), orig);

		System.out.println(msg);

		// tokens
		String[] tokens = orig.split("_");
		
		String[] tokens_Date = tokens[0].split("-");
		
		String label_Date = StringUtils.join(tokens_Date, "/");

		///////////////////////////////////
		//
		// label: time
		//
		///////////////////////////////////
		String[] tokens_Time = tokens[1].split("-");
		
		String label_Time = StringUtils.join(tokens_Time, ":");
		
		label_Time = label_Time + ".000";
		
//		String msg;
		msg = String.format(Locale.JAPAN, "[%s : %d] %s %s", Thread
//				msg = String.format(Locale.JAPAN, "[%s : %d] label_Date => %s | time => %s", Thread
				.currentThread().getStackTrace()[1].getFileName(), Thread
				.currentThread().getStackTrace()[1].getLineNumber(), 
				label_Date, label_Time);

		System.out.println(msg);

		
		
		
	}//test_Conv_FileName_2_TimeLabel
	
	
	public static void 
	test_Regex() {
		
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
