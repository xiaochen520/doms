package test;

public class mod {
	public static void main(String[] args) {
		String str = "111A|CCC11|";
		String[] s=str.split( "\\|");
		System.out.println(s.length);
		for(int i=0;i<s.length;i++){
			System.out.println(s[i]);
		}
				
	}
}
