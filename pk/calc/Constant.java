package pk.calc;

abstract class Constant extends Number {
  public Constant(String value) {
    super(Double.parseDouble(value));
  }
  
  public Constant(Double value) {
    super(value);
  }
  
  public Number execute() {
    return new Number(getValue());
  }
}