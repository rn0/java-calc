package pk.calc;

class PlusOperator extends Operator {
  public PlusOperator(String value) {
    super("plus_operator", value);
  }
  
  static TokenMatcher getMatcher() {
    return new TokenMatcher("PlusOperator", "\\+");
  }

  public int priority() {
    return 2;
  }
  
  public String associativity() {
    return "both";
  }
  
  public Number execute(Number arg[]) {
    return new Number(arg[0].getValue() + arg[1].getValue());
  }
}