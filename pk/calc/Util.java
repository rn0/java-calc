package pk.calc;

class Util {
  static boolean showLogs = false;
  
  static void log(String str) {
    if(Util.showLogs) {
      System.out.println(str);
    }
  }
}