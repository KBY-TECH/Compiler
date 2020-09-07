
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class LexicalAnalyzer {

	public static void main(String[] args) throws FileNotFoundException {

		String inputText = "";

		File file = new File("C:\\input.c");
		Scanner scanner = new Scanner(file);

		Stack<Character> s = new Stack<Character>();

		int star_flag = 0;

		inputText = scanner.nextLine();
		while (scanner.hasNextLine()) {
			inputText = inputText + "\n" + scanner.nextLine();
		}
		ArrayList<String> arr = new ArrayList<String>();
		char[] string = inputText.toCharArray();
		int k = 1;
		for (int i = 0; i < string.length; i++) {

//문자 인식시
			if (star_flag < 1) {
				if ((string[i] >= 'A' && string[i] <= 'Z') || string[i] == '_'
						|| (string[i] >= 'a' && string[i] <= 'z')) {

					String temp = "";
					temp += string[i];

					i++;

					while ((string[i] >= 'A' && string[i] <= 'Z') || string[i] == '_'
							|| (string[i] >= 'a' && string[i] <= 'z') || (string[i] >= '0' && string[i] <= '9')) {
						if (!s.isEmpty())
							s.pop();
						temp += string[i];

						i++;
					}
					if (arr.isEmpty())
						arr.add(temp);
					else {
						for (int j = 0; j < arr.size(); j++) {

							if (arr.contains((String) temp)) {
								break;
							} else {
								if (!temp.equals("if") && !temp.equals("else") && !temp.equals("for")
										&& !temp.equals("while"))
									arr.add(temp);
							}
						}
					}

					if (temp.equals("if")) {
						System.out.println("Keyword : if, Token num : 3");
					} else if (temp.equals("else"))

						System.out.println("Keyword : else, Token num : 4");
					else if (temp.equals("for"))
						System.out.println("Keyword : for, Token num : 5");
					else if (temp.equals("while"))
						System.out.println("Keyword : while, Token num : 6");
					else {
						for (int j = 0; j < arr.size(); j++) {
							k = arr.indexOf(temp);
						}
						System.out.println("Identifier : " + temp + ", Token num : 1, Token value: " + k);
					}
				}
// 숫자 인식기
				if (string[i] >= '0' && string[i] <= '9') {
					System.out.print("Integer : ");

					String temp = "";

					while (string[i] >= '0' && string[i] <= '9') {
						temp += string[i];
						System.out.print(string[i]);
						i++;
					}
					System.out.println(", Token num : 2 , Token value: " + temp);
				}
			}

//연산자 인식시.

			if (string[i] == '+') {
				if (star_flag < 1) {
					System.out.print("Operator : +, ");
					System.out.println("Token num : 7 ");
				}
			}

			if (string[i] == '-') {
				if (star_flag < 1) {
					System.out.print("Operator : -, ");
					System.out.println("Token num : 8 ");
				}
			}

			if (string[i] == '*') {
				if (!s.isEmpty() && (s.peek().equals('/') || s.peek().equals('*'))) {
					if (star_flag >= 2) {

					} else {
						s.add(string[i]);
						star_flag++;
					}
				} else {
					System.out.print("Operator : *, ");
					System.out.println("Token num : 9 ");
				}
			}
			if (string[i] == '/') {

				if (s.isEmpty() && star_flag == 0) {
					s.add(string[i]);
					if (Character.isDigit(string[i + 2])) {
						s.pop();
						System.out.print("Operator : /, ");
						System.out.println("Token num : 10 ");
					}
				} else {
					if (star_flag == 2 && s.peek().equals('*')) {
						while (!s.isEmpty()) {
							s.pop();
						}
						star_flag = 0;
					}
				}
			}

			if (string[i] == '=') {
				if (star_flag < 1) {
					System.out.print("Operator : =, ");
					System.out.println("Token num : 11 ");
				}
			}

			if (string[i] == ';') {
				if (star_flag < 1) {
					System.out.print("Delimiter : ;, ");
					System.out.println("Token num : 12 ");
				}
			}

			if (string[i] == '(') {
				if (star_flag < 1) {
					System.out.print("Delimiter : (, ");
					System.out.println("Token num : 13 ");
				}
			}

			if (string[i] == ')') {
				if (star_flag < 1) {
					System.out.print("Delimiter : ), ");
					System.out.println("Token num : 14 ");
				}
			}

			if (string[i] == '{') {
				if (star_flag < 1) {
					System.out.print("Delimiter : {, ");
					System.out.println("Token num : 15 ");
				}
			}

			if (string[i] == '}') {
				if (star_flag < 1) {
					System.out.print("Delimiter : }, ");
					System.out.println("Token num : 16 ");
				}
			}
		}
		scanner.close();
	}
}
// commit push test
