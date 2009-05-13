package pk.calc;

import java.util.*;

class Calc {
  private Lexer lexer;
  private CalcStack stack;
  private Queue<Token> rpnNotation;
  
  Calc(String expression) throws Exception {
    lexer = new Lexer(expression);
    stack = new CalcStack();
    rpnNotation = new LinkedList<Token>();
  }
  
  Double evaluate() throws Exception {
    convertToRPN();
    return process();
  }
  
  private Double process() throws Exception {
    CalcStack tempStack = new CalcStack();
    Token token = null;
    
    while(!rpnNotation.isEmpty()) {
      token = rpnNotation.poll();
    //while((Token token = rpnNotation.poll()) != null) {
      if(token instanceof Number) {
        tempStack.push(token);
      }
      else if(token instanceof Operator) { // FIXIT !!
        Number[] arg = tempStack.pop(((Operator)token).numOfArgs());
        tempStack.push(((Operator)token).execute(arg));
        
        Util.log("Calc::process - executing: " + token.getType() + "(" + Arrays.toString(arg) + ") = " + ((Number)tempStack.peek()).getValue());
      }
      else if(token instanceof Function) { // FIXIT !!
        Number[] arg = tempStack.pop(((Function)token).numOfArgs());
        tempStack.push(((Function)token).execute(arg));

        Util.log("Calc::process - executing: " + token.getType() + "(" + Arrays.toString(arg) + ") = " + ((Number)tempStack.peek()).getValue());
      }
    }
    
    return ((Number)tempStack.pop()).getValue();
  }
  
  private void convertToRPN() throws Exception {
    lexer.tokenize();
    Iterator iterator = lexer.getIterator();
    while(iterator.hasNext()) {
      Token token = (Token)iterator.next();
     
      // Jeœli symbol jest liczb¹
      if(token instanceof Number) {
        // dodaj go do kolejki wyjœcie
        rpnNotation.add(token);
      }
      // Jeœli symbol jest funkcj¹
      else if(token instanceof Function) {
        // w³ó¿ go na stos.
        stack.push(token);
      }
      // Jeœli symbol jest znakiem oddzielaj¹cym argumenty funkcji (np. przecinek):
      else if(token instanceof Coma) {
        // Dopóki najwy¿szy element stosu nie jest lewym nawiasem,
        while(!(stack.peek() instanceof L_Brace)) {
          // zdejmij element ze stosu i dodaj go do kolejki wyjœcie.
          rpnNotation.add((Token)stack.pop());
        }
        // Jeœli lewy nawias nie zosta³ napotkany oznacza to, 
        // ¿e znaki oddzielaj¹ce zosta³y postawione w z³ym miejscu lub nawiasy s¹ Ÿle umieszczone.
        if(!(stack.peek() instanceof L_Brace)) {
          throw new Exception("Missing left bracket in expression");
        }
      }
      // Jeœli symbol jest operatorem, o1
      else if(token instanceof Operator) {
        // 1) dopóki na górze stosu znajduje siê operator, o2 taki, ¿e:
        Operator t = (Operator) token;
        Object stackTop = stack.peek();
        if(stackTop != null && stackTop instanceof Operator) {
          int priority = ((Operator)stackTop).priority();
          //o1 jest ³¹czny lub lewostronnie ³¹czny i jego kolejnoœæ wykonywania jest mniejsza lub równa kolejnoœci wyk. o2, lub
          boolean test1 = (t.associativity() == "both" || t.associativity() == "left") && (t.priority() <= priority);
          //o1 jest prawostronnie ³¹czny i jego kolejnoœæ wykonywania jest mniejsza od o2,
          boolean test2 = (t.associativity() == "right") && (t.priority() < priority);
          if(test1 || test2) {
            // zdejmij o2 ze stosu i do³ó¿ go do kolejki wyjœciowej;
            rpnNotation.add((Token)stack.pop());
          }
        }
        // 2) w³ó¿ o1 na stos operatorów.
        stack.push(token);
      }
      // Je¿eli symbol jest lewym nawiasem
      else if(token instanceof L_Brace) {
        // to w³ó¿ go na stos.
        stack.push(token);
      }
      
      // Je¿eli symbol jest prawym nawiasem
      else if(token instanceof R_Brace) {
        boolean leftBracketExists = false;
        Object operator;
        while(!stack.empty()) {
          operator = stack.pop();
          // dopóki symbol na górze stosu nie jest lewym nawiasem,
          if(operator instanceof L_Brace) {
            leftBracketExists = true;
            break;
          }
          // to zdejmuj operatory ze stosu i dok³adaj je do kolejki wyjœcie
          else {
            rpnNotation.add((Token)operator);
          }
        }

        // Teraz, jeœli najwy¿szy element na stosie jest funkcj¹, tak¿e do³ó¿ go do kolejki wyjœcie.
        if(stack.peek() instanceof Function) {
          rpnNotation.add((Token)stack.pop());
        }

        // Jeœli stos zostanie opró¿niony i nie napotkasz lewego nawiasu, oznacza to, ¿e nawiasy zosta³y Ÿle umieszczone.
        if(stack.empty() && !leftBracketExists) {
          throw new Exception("Missing left bracket in expression");
        }
      }
      
      Util.log("Calc::convertToRPN - processing token: " + token + " Stack: " + stack + " RPN: " + rpnNotation);
    }
    // Jeœli nie ma wiêcej symboli do przeczytania, zdejmuj wszystkie symbole ze stosu (jeœli jakieœ s¹) i dodawaj je do kolejki wyjœcia.
    Object operator;
    while(!stack.empty()) {
      operator = stack.pop();
      // Powinny to byæ wy³¹cznie operatory, 
      // jeœli natrafisz na jakiœ nawias, znaczy to, ¿e nawiasy zosta³y Ÿle umieszczone.
      if(operator instanceof Brace) {
        throw new Exception("Mismatched brackets in expression");
      }
      rpnNotation.add((Token)operator);
    }
    
    Util.log("Calc::convertToRPN - RPN notation: " + rpnNotation);
  }
}