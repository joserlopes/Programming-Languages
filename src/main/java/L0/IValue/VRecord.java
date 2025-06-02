package L0.IValue;

import java.util.List;

public class VRecord implements IValue {
  List<IValue> fields;

  public VRecord(List<IValue> fields) {
    this.fields = fields;
  }

  public String toStr() {
    return "";
  }
}
