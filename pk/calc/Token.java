package pk.calc;

abstract class Token {
  protected Object value;
  private String type;
  
  public Token() {
  }

  public Token(String type, Object value) {
    setType(type);
    setValue(value);
  }
  
  public void setType(String type) {
    this.type = type;
  }
  
  public String getType() {
    return type;
  }
  
  public void setValue(Object value) {
    this.value = value;
  }
  
  public Object getValue() {
    return value;
  }

  public String toString() {
    return value.toString();
  }
  
  /*static TokenMatcher getMatcher() throws Exception {
    throw new Exception("Unimplemented");
  }*/
}