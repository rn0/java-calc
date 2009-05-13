package pk.calc;

import java.util.*;
import java.util.regex.*;
import java.net.URLClassLoader;
import java.lang.reflect.*;

public class Lexer {
  private String expression;
  private ArrayList<TokenMatcher> registeredTokens;
  private ArrayList<Token> tokenObjects;
  private Class[] proto;
  
  Lexer(String exp) throws Exception {
    expression = exp.replaceAll("(?i)\\s+", "$1");
    
    if(expression.isEmpty()) {
      throw new Exception("Expression to tokenize is empty");
    }
    
    tokenObjects = new ArrayList<Token>();
    
    registeredTokens = new ArrayList<TokenMatcher>();
    registeredTokens.add(Number.getMatcher());
    registeredTokens.add(L_Brace.getMatcher());
    registeredTokens.add(R_Brace.getMatcher());
    
    registeredTokens.add(PlusOperator.getMatcher());
    registeredTokens.add(MinusOperator.getMatcher());
    registeredTokens.add(MultiplyOperator.getMatcher());
    registeredTokens.add(DivideOperator.getMatcher());
    registeredTokens.add(PowerOperator.getMatcher());
    
    registeredTokens.add(SinFunction.getMatcher());
    registeredTokens.add(CosFunction.getMatcher());
    registeredTokens.add(TgFunction.getMatcher());
    registeredTokens.add(CtgFunction.getMatcher());
    
    registeredTokens.add(PiConstant.getMatcher());
    
    proto = new Class[1];
    proto[0] = String.class;
  }
  
  private Token tokenFactory(TokenMatcher tr, String value) throws Exception {
    Token klass = null;
    try {
      String path = "pk.calc." + tr.getKlass();
      /*System.out.println("Lexer::tokenFactory - using: " + path);
      Constructor[] cs = Class.forName(path).getConstructors();
      System.out.println("Lexer::tokenFactory - Constructor array: " + cs.length); 
      for(Constructor c : cs) {
        System.out.println(c.toGenericString());
      }*/
      
      klass = (Token)Class.forName(path).getConstructor(proto).newInstance(value);
    }
    catch(Exception e) {
      // FIXIT: 
      e.printStackTrace();
      throw new Exception("Lexer: " + e.getMessage());
    }
    
    return klass;
  }
  
  void tokenize() throws Exception {
    boolean isMatch = false;

    while(expression.length() > 0) {
      isMatch = false;
      for(TokenMatcher tr : registeredTokens) {
        Matcher m = tr.getRegexp().matcher(expression);
        if(!isMatch && m.find()) {
          isMatch = true;
          Util.log("Lexer::tokenize - found token: " + m.group() + " class: " + tr.getKlass());
          tokenObjects.add(tokenFactory(tr, m.group()));
          expression = expression.substring(m.group().length());
          break;
        }
      }
      if(!isMatch) {
        throw new Exception("Unrecognized token: '" + expression + "'");
      }
    }
  }
  
  Iterator getIterator() {
    return tokenObjects.iterator();
  }
}
