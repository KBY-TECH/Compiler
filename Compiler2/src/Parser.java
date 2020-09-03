
public class Parser {
	public static void main(String args[]) {
		System.out.println("LL");
		LLParser.parse("(((id)*(id)))");
		
		
		System.out.println("LR");
		LRParser.parse("bda"); 

	}

}
