package L0.IValue;

import L0.Environment;
import java.util.HashMap;

public class VRecord implements IValue {
  HashMap<String, IValue> fields;
  Environment<IValue> e;

  public VRecord(HashMap<String, IValue> fields, Environment<IValue> e) {
    this.fields = fields;
    this.e = e;
  }

  public String toStr() {
    String res = "record(";
    System.out.println(this.fields);
    this.fields.forEach(
        (name, value) -> {
          res.concat(name + "=" + value);
        });
    return res;
  }
}
