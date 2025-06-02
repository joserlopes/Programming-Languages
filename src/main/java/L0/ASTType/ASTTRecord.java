package L0.ASTType;

import java.util.Map;

public class ASTTRecord implements ASTType {
  TypeBindList ll;

  public ASTTRecord(TypeBindList llp) {
    this.ll = llp;
  }

  public TypeBindList getBinds() {
    return this.ll;
  }

  public String toStr() {
    String res = "record { ";
    for (Map.Entry<String, ASTType> entry : this.ll.getTbl().entrySet()) {
      res += entry.getKey() + ": " + entry.getValue().toStr() + ", ";
    }
    res = res.substring(0, res.length() - 2);
    res += " }";
    return res;
  }
}
