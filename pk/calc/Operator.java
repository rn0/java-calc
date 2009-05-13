package pk.calc;

abstract class Operator extends Token {
  public Operator(String type, String value) {
    super(type, value);
  }
  
  public int numOfArgs() {
    return 2;
  }
  
  abstract public int priority();
  abstract public String associativity();
  abstract public Number execute(Number arg[]);
}