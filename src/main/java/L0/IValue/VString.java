package L0.IValue;


public class VString implements IValue {
  String v;

  public VString(String v0) {
    this.v = v0;
  }

  public String getVal() {
    return this.v;
  }

  public String toStr() {
    return this.v;
  }
}
