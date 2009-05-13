package pk.calc;

class TgFunction extends Function {
  public TgFunction(String value) {
    super("tg_function", value);
  }
  
  static TokenMatcher getMatcher() {
    return new TokenMatcher("TgFunction", "tg");
  }
  
  public int numOfArgs() {
    return 1;
  }
  
  public Number execute(Number arg[]) {
    return new Number(Math.tan(arg[0].getValue()));
  }
}