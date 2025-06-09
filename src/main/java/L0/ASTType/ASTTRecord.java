package L0.ASTType;

import java.util.HashMap;
import java.util.Map;

public class ASTTRecord implements ASTType {
  TypeBindList ll;

  public ASTTRecord(TypeBindList llp) {
    this.ll = llp;
  }

  public TypeBindList getBinds() {
    return this.ll;
  }

  public boolean isSubtype(ASTType other) {
    if (other instanceof ASTTRecord) {
      ASTTRecord r1 = (ASTTRecord) other;
      HashMap<String, ASTType> matchableLabels = this.ll.getTbl();
      HashMap<String, ASTType> otherMatchableLabels = r1.getBinds().getTbl();
      int matched = 0;
      for (Map.Entry<String, ASTType> entry : matchableLabels.entrySet()) {
        String name = entry.getKey();
        ASTType type = entry.getValue();
        if (otherMatchableLabels.containsKey(name)) {
          ASTType otherType = otherMatchableLabels.get(name);
          if (type.isSubtype(otherType)) {
            matched++;
          }
        }
      }
      System.out.println("Matched labels: " + matched);
      return matched == otherMatchableLabels.size();
    }
    return false;
  }

  public String toStr() {
    if (this.ll.getTbl().isEmpty()) {
      return "record { }";
    }
    String res = "record { ";
    for (Map.Entry<String, ASTType> entry : this.ll.getTbl().entrySet()) {
      res += "#" + entry.getKey() + ": " + entry.getValue().toStr() + "; ";
    }
    res = res.substring(0, res.length() - 2);
    res += " }";
    return res;
  }
}
