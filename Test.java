import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	
	public static void main(String[] args) {
		
//		System.out.println("yes");
		
		Test.test_Regex_Photo_Memo();
		
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
	
}//public class Test
