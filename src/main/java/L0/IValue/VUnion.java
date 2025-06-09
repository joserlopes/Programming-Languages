package L0.IValue;

public class VUnion implements IValue {
  String lbl;
  IValue value;

  public VUnion(String lbl, IValue value) {
    this.lbl = lbl;
    this.value = value;
  }

  public String getLabel() {
    return this.lbl;
  }

  public IValue getValue() {
    return this.value;
  }

  public String toStr() {
    return "union { " + this.lbl + " = " + this.value.toStr() + " }";
  }
}
