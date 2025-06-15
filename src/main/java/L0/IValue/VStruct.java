package L0.IValue;

import L0.Environment;
import java.util.HashMap;
import java.util.Map;

public class VStruct implements IValue {
  HashMap<String, IValue> fields;
  Environment<IValue> e;

  public VStruct(HashMap<String, IValue> fields, Environment<IValue> e) {
    this.fields = fields;
    this.e = e;
  }

  public IValue getValue(String key) {
    return this.fields.get(key);
  }

  public String toStr() {
    if (this.fields.isEmpty()) {
      return "struct { }";
    }
    String res = "struct { ";
    for (Map.Entry<String, IValue> entry : this.fields.entrySet()) {
      res += entry.getKey() + " = " + entry.getValue().toStr() + ", ";
    }
    res = res.substring(0, res.length() - 2);
    res += " }";
    return res;
  }
}
