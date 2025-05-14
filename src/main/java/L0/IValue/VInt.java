package L0.IValue;

public class VInt implements IValue {
  int v;

  public VInt(int v0) {
    this.v = v0;
  }

  public int getVal() {
    return this.v;
  }

  public String toStr() {
    return Integer.toString(this.v);
  }
}
