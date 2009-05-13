package pk.calc;

class CosFunction extends Function {
  public CosFunction(String value) {
    super("cos_function", value);
  }
  
  static TokenMatcher getMatcher() {
    return new TokenMatcher("CosFunction", "cos");
  }
  
  public int numOfArgs() {
    return 1;
  }
  
  public Number execute(Number arg[]) {
    return new Number(Math.cos(arg[0].getValue()));
  }
}