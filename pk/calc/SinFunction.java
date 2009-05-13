package pk.calc;

class SinFunction extends Function {
  public SinFunction(String value) {
    super("sin_function", value);
  }
  
  static TokenMatcher getMatcher() {
    return new TokenMatcher("SinFunction", "sin");
  }
  
  public int numOfArgs() {
    return 1;
  }
  
  public Number execute(Number arg[]) {
    return new Number(Math.sin(arg[0].getValue()));
  }
}