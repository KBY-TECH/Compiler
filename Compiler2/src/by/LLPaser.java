package by;

public class LLPaser {
	

	private static char nextSymbol;
	private static int index = 0;
	private static String str;

	
	// 닫는 괄호는 모지이이이이이??? 다 넣어줌....
	// 엡실런 처리...
	
	static void parse(String input) {

		str = input + "$";
		nextSymbol = str.charAt(index);

		PE();

		if (nextSymbol == '$') {
			System.out.println("accept");
		} else {
			error("$");
		}
	}

	static void PE() {
		switch (nextSymbol) {
		case '(':case 'i':
			PT();
			PE2();
			break;
		case ' ':
			PE2();
		case')':
			close(); break;
		default:
			error("( or i");
		}
	}
	static void PE2()
	{
		switch (nextSymbol) {
		case '+':
			plus();
			PT();
			PE2();
			break;
		case ' ':
			empty();
			break;
		case')':
			close(); break;
		
		default:
			break;
		}
	}
	static void PT() {
		switch (nextSymbol) {
		case '(':case 'i':
			PF();
			PT2();
			break;
		case ' ':
			PT2();
			break;
		case')':
			close(); break;

		default:
			error("( or i");
		}
	}
	static void PT2()
	{
		switch (nextSymbol) {
		case '*':
			star();
			PF();
			PT2();
			break;
		case ' ' :
			empty();
			break;
		case')':
			close(); break;

		default:
			error("* or null");
		}
	}
	static void PF()
	{
		switch (nextSymbol) {
		case '(':
		
			open();
			PE();
			close();
			break;
		case 'i':
			i();
			d();
			break;

		default:
			error("( or i");
		}
	}
	static void empty()
	{
		if(nextSymbol==' ')
			{if (index < str.length() - 1)
				nextSymbol = str.charAt(++index);
			}
		else
		{
			error(" ");
		}
			
	}
	// +
	static void plus() {
		if (nextSymbol == '+')
			if (index < str.length() - 1)
				nextSymbol = str.charAt(++index);
			else
				error("+");
	}

//    *
	static void star() {
		if (nextSymbol == '*')
			if (index < str.length() - 1)
				nextSymbol = str.charAt(++index);
			else
				error("*");
	}
//   (
	static void open() {
		if (nextSymbol == '(')
			if (index < str.length() - 1)
				nextSymbol = str.charAt(++index);
			else
				error("(");
	}
//   )
	static void close() {
		if (nextSymbol == ')')
			if (index < str.length() - 1)
				nextSymbol = str.charAt(++index);
			else
				error(")");
	}
//	 i
	static void i()
	{
		if (nextSymbol == 'i')
			if (index < str.length() - 1)
				nextSymbol = str.charAt(++index);
			else
				error("i");
	}
//	d
	static void d()
	{
		if (nextSymbol == 'd')
			if (index < str.length() - 1)
				nextSymbol = str.charAt(++index);
			else
				error("d");
	}


	static void error(String expected) {
		System.out.println(
				"Invalid input. Expected " + expected + ", but received " + nextSymbol + " at index " + index + ".");
		System.exit(-1);
	}
}