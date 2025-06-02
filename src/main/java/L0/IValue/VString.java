package L0.IValue;

public class VString implements IValue {
  String value;

  public VString(String value) {
    this.value = value;
  }

  public String toStr() {
    return this.value;
  }
}
