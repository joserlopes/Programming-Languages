package L0.IValue;

public class VInt implements IValue {
  int value;

  public VInt(int value) {
    this.value = value;
  }

  public int getVal() {
    return this.value;
  }

  public String toStr() {
    return Integer.toString(this.value);
  }
}
