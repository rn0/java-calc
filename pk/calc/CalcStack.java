package pk.calc;

import java.util.*;

class CalcStack {
  private ArrayList<Token> buffer;
  
  public CalcStack() {
    buffer = new ArrayList<Token>();
  }
  
  public void push(Token t) {
    buffer.add(t);
  }
  
  public Token pop() {
      int last = buffer.size() - 1;
      Token t =  buffer.get(last);
      buffer.remove(last);
      return t;
  }
  
  // Fixit !!!!!!
  public Number[] pop(int toPop) throws Exception {
    if(toPop > buffer.size()) {
      throw new Exception("Cannot pop (toPop: " + toPop + ") Stack: " + buffer);
    }
    
    Number[] ret = new Number[toPop];
    while((toPop--) > 0) {
      ret[toPop] = (Number)pop();
    }
    return ret;
  }
  
  public Token peek() {
    int size = buffer.size();
    if(size == 0) {
      return null;
    }
    else {
      return buffer.get(size - 1);
    }
  }
  
  public boolean empty() {
    return buffer.isEmpty();
  }
  
  public String toString() {
    return buffer.toString();
  }
}