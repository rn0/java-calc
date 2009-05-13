package pk.calc;

class PowerOperator extends Operator {
  public PowerOperator(String value) {
    super("power_operator", value);
  }
  
  static TokenMatcher getMatcher() {
    return new TokenMatcher("PowerOperator", "\\^");
  }

  public int priority() {
    return 4;
  }
  
  public String associativity() {
    return "right";
  }
  
  public Number execute(Number arg[]) {
    return new Number(Math.pow(arg[0].getValue(), arg[1].getValue()));
  }
}