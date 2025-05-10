package L0.IValue;

public class VBox implements IValue {
  IValue value;

  public VBox(IValue value) {
    this.value = value;
  }

  public IValue deref() {
    return this.value;
  }

  public String toStr() {
    // NOTE: Is this it?
    return "Box(" + value.toStr() + ")";
  }
}
