package pk.calc;

class Coma extends Token {
  public Coma(String value) {
    super("coma", value);
  }
  
  static TokenMatcher getMatcher() {
    return new TokenMatcher("Coma", "\\,");
  }
}