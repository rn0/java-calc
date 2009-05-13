package pk.calc;

class MultiplyOperator extends Operator {
  public MultiplyOperator(String value) {
    super("multiply_operator", value);
  }
  
  static TokenMatcher getMatcher() {
    return new TokenMatcher("MultiplyOperator", "\\*");
  }

  public int priority() {
    return 3;
  }
  
  public String associativity() {
    return "both";
  }
  
  public Number execute(Number arg[]) {
    return new Number(arg[0].getValue() * arg[1].getValue());
  }
}