import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	
	public static void main(String[] args) {
		
//		System.out.println("yes");
		
		test_Regex();
		
//		return 0;
		
	}
	
	public static void test_Regex() {
		
//		System.out.println("regex");
		
//		String fmt_FileName_PlayMemo = "2014";	// 
//		String fmt_FileName_PlayMemo = "\\d{4}";	// not
//		String fmt_FileName_PlayMemo = "^\\@";	// not
//		String fmt_FileName_PlayMemo = "^@";	// not
//		String fmt_FileName_PlayMemo = "^@\\d{4}";	// not
		String fmt_FileName_PlayMemo = "^@\\d{4}.+wav";	// not
		
//		String text = "@2014-10-03_10-01-45-933.wav";
		String text = "@2014-10-03_10-01-45-933.wav :N :DONE";
		
		Pattern p = Pattern.compile(fmt_FileName_PlayMemo);
		
		Matcher m = p.matcher(text);

		if (m.find()) {
//			if (m.matches()) {
			
			System.out.println("matches");
			
			System.out.println("m.group(0) => " + m.group(0));
			
		} else {
			
			System.out.println("not");
			
		}
		
	}
	
}