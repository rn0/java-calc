package pk.calc;

class CtgFunction extends Function {
  public CtgFunction(String value) {
    super("ctg_function", value);
  }
  
  static TokenMatcher getMatcher() {
    return new TokenMatcher("CtgFunction", "ctg");
  }
  
  public int numOfArgs() {
    return 1;
  }
  
  public Number execute(Number arg[]) {
    return new Number(Math.tan(Math.PI / 2 - arg[0].getValue()));
  }
}