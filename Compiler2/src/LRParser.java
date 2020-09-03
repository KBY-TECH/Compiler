import java.util.Stack;

public class LRParser {

    private static char nextSymbol;
    private static int index = 0;
    private static String str;
    private static boolean isFinished = false;

    private static Stack<Integer> state_stack = new Stack<Integer>();
    private static Stack<Character> symbol_stack = new Stack<Character>();


    static void parse(String input) {

        str = input + "$";
        nextSymbol = str.charAt(index);

        state_stack.push(0);

        while(!isFinished){

            switch(state_stack.peek())
            {
                case 0:
                    state0();
                    break;
                case 1:
                    state1();
                    break;
                case 2:
                    state2();
                    break;
                case 3:
                    state3();
                    break;
                case 4:
                    state4();
                    break;
                case 5:
                    state5();
                    break;
                case 6:
                    state6();
                    break;
                case 7:
                    state7();
                    break;
                case 8:
                    state8();
                    break;
                case 9:
                    state9();
                    break;
                case 10:
                    state10();
                    break;
            }
        }
    }
    
    // 해당 터미널 기호이면 이동
    
    static void state0(){
        if (nextSymbol == 'b') {
            shift(3);
        } else if (nextSymbol == 'd') {
            shift(4);
        } else
            error("c or d", 1);
    }

    static void state1(){
        if (nextSymbol == '$') {
            System.out.println("accept");
            isFinished = true;
        } else {
            error("$", 1);
        }
    }

    static void state2(){
        if (nextSymbol == 'a') {
            shift(5);
        } else
            error(" a ",1);
    }
    // bda bdc 냐....
    static void state3(){
        if (nextSymbol == 'd') {
            shift(7);
		} 
        else
            error("d", 1);
    }

    static void state4(){
        if (nextSymbol == 'c') {
            shift(8);
        }
        else if(nextSymbol=='a'){
        	reduce5();
        }
        else
            error("c or a", 1);
    }

    static void state5(){
        if (nextSymbol == '$')
            reduce1();
        else
            error("$", 1);
    }
    
    static void state6(){
        if (nextSymbol == 'c') {
            shift(9);
        } else
            error("c", 1);
    }

    static void state7(){
        if (nextSymbol == 'a') {
        	shift(10);
        }
        else if(nextSymbol=='c')
        {
        	reduce5();
        }

        else
            error("$", 1);
    }

    static void state8(){
    	if (nextSymbol == '$') {
            reduce3();
        } else
            error("c or d", 1);
    }

    static void state9(){
        if (nextSymbol == '$') {
            reduce2();
        } else
            error("$", 1);
    }
    static void state10(){
        if (nextSymbol == '$') {
            reduce4();
        } else
            error("$", 1);
    }

    static void shift(int state){
        state_stack.push(state);
        symbol_stack.push(nextSymbol);

        nextSymbol = str.charAt(++index);
    }
    
    static void reduce1() {
    	  String stackTop = "";
          stackTop += symbol_stack.pop();
          stackTop += symbol_stack.pop();

          state_stack.pop();
          state_stack.pop();

          if(stackTop.equals("aA")) {
              symbol_stack.push('S');

              int curState = state_stack.peek();

              if(curState == 0){
                  state_stack.push(1);
              } else {
                  error("", 2);
              }
          } else{
              error("", 2);
          }
      
    }
    static void reduce2()
    {
    	  String stackTop = "";
          stackTop += symbol_stack.pop();
          stackTop += symbol_stack.pop();
          stackTop += symbol_stack.pop();
          
          state_stack.pop();
          state_stack.pop();
          state_stack.pop();

          if(stackTop.equals("cAb")) {
              symbol_stack.push('S');

              int curState = state_stack.peek();

              if(curState == 0){
                  state_stack.push(1);
              } else {
                  error("", 2);
              }
          } else{
              error("", 2);
          }
      
    }
    static void reduce3()
    {
    	  String stackTop = "";
          stackTop += symbol_stack.pop();
          stackTop += symbol_stack.pop();

          state_stack.pop();
          state_stack.pop();

          if(stackTop.equals("cd")) {
              symbol_stack.push('S');

              int curState = state_stack.peek();

              if(curState == 0){
                  state_stack.push(1);
              } else {
                  error("", 2);
              }
          } else{
              error("", 2);
          }
      
    }
    static void reduce4()
    {
    	 String stackTop = "";
         stackTop += symbol_stack.pop();
         stackTop += symbol_stack.pop();
         stackTop += symbol_stack.pop();
         
         state_stack.pop();
         state_stack.pop();
         state_stack.pop();

         if(stackTop.equals("adb")) {
             symbol_stack.push('S');

             int curState = state_stack.peek();

             if(curState == 0){
                 state_stack.push(1);
             } else {
                 error("", 2);
             }
         } else{
             error("", 2);
         }
     
    }
    static void reduce5()
    {
    	 String stackTop = "";
         stackTop += symbol_stack.pop();

         state_stack.pop();

         if(stackTop.equals("d")) {
             symbol_stack.push('A');

             int curState = state_stack.peek();

             if(curState == 3){
                 state_stack.push(6);
             } else if (curState == 0) {
                 state_stack.push(2);
             } 
              else {
                 error("", 2);
             }
         } else{
             error("", 2);
         }
    }
	/*
	 * static void reduce1(){ String stackTop = ""; stackTop += symbol_stack.pop();
	 * stackTop += symbol_stack.pop();
	 * 
	 * state_stack.pop(); state_stack.pop();
	 * 
	 * if(stackTop.equals("CC")) { symbol_stack.push('S');
	 * 
	 * int curState = state_stack.peek();
	 * 
	 * if(curState == 0){ state_stack.push(1); } else { error("", 2); } } else{
	 * error("", 2); } }
	 * 
	 * static void reduce2(){ String stackTop = ""; stackTop += symbol_stack.pop();
	 * stackTop += symbol_stack.pop();
	 * 
	 * state_stack.pop(); state_stack.pop();
	 * 
	 * if(stackTop.equals("Cc")) { symbol_stack.push('C');
	 * 
	 * int curState = state_stack.peek();
	 * 
	 * if(curState == 0){ state_stack.push(2); } else if (curState == 2) {
	 * state_stack.push(5); } else if (curState == 3) { state_stack.push(8); } else
	 * if (curState == 6) { state_stack.push(9); } else { error("", 2); } } else{
	 * error("", 2); } }
	 * 
	 * static void reduce3(){ String stackTop = ""; stackTop += symbol_stack.pop();
	 * 
	 * state_stack.pop();
	 * 
	 * if(stackTop.equals("d")) { symbol_stack.push('C');
	 * 
	 * int curState = state_stack.peek();
	 * 
	 * if(curState == 0){ state_stack.push(2); } else if (curState == 2) {
	 * state_stack.push(5); } else if (curState == 3) { state_stack.push(8); } else
	 * if (curState == 6) { state_stack.push(9); } else { error("", 2); } } else{
	 * error("", 2); } }
	 */


    static void error(String expected, int type) {
        if(type == 1) {
            System.out.println("Invalid input. Expected " + expected + ", but received " + nextSymbol + " at index " + index + ".");
        } else if (type == 2) {
            System.out.println("Reduce error.");
        }
        System.exit(-1);
    }
}