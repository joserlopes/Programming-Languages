package L0.IValue;

public class VBool implements IValue {
  boolean v;

  public VBool(boolean v0) {
    this.v = v0;
  }

  public boolean getVal() {
    return this.v;
  }

  public String toStr() {
    return Boolean.toString(this.v);
  }
}
