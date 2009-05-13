package pk.calc;

abstract class Function extends Token {
  public Function(String type, String value) {
	super(type, value);
  }
  
  abstract public Number execute(Number arg[]);
  abstract int numOfArgs();
}