package L0.IValue;

public class VBox implements IValue {
  IValue value;

  public VBox(IValue value) {
    this.value = value;
  }

  public IValue deref() {
    return this.value;
  }

  public void assign(IValue value) {
    this.value = value;
  }

  public String toStr() {
    return "Box(" + value.toStr() + ")";
  }
}
