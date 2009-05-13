package pk.calc;

class R_Brace extends Brace {
  public R_Brace(String value) {
    super("r_brace", value);
  }
  
  static TokenMatcher getMatcher() {
    return new TokenMatcher("R_Brace", "\\)");
  }
}