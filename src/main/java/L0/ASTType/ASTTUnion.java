package L0.ASTType;

import java.util.Map;

public class ASTTUnion implements ASTType {
  TypeBindList ll;

  public ASTTUnion(TypeBindList ll) {
    this.ll = ll;
  }

  public String toStr() {
    String res = "union [ ";
    for (Map.Entry<String, ASTType> entry : this.ll.getTbl().entrySet()) {
      res += entry.getKey() + ": " + entry.getValue().toStr() + "; ";
    }
    res = res.substring(0, res.length() - 2);
    res += " ]";
    return res;
  }
}
