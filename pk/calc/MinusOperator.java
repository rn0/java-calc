package pk.calc;

class MinusOperator extends Operator {
  public MinusOperator(String value) {
    super("minus_operator", value);
  }
  
  static TokenMatcher getMatcher() {
    return new TokenMatcher("MinusOperator", "\\-");
  }

  public int priority() {
    return 2;
  }
  
  public String associativity() {
    return "left";
  }
  
  public Number execute(Number arg[]) {
    return new Number(arg[0].getValue() - arg[1].getValue());
  }
}