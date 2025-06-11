package L0.ASTType;

import L0.Environment;
import L0.Errors.InterpreterError;
import java.util.HashMap;
import java.util.Map;

public class ASTTStruct implements ASTType {
  TypeBindList ll;

  public ASTTStruct(TypeBindList llp) {
    this.ll = llp;
  }

  public TypeBindList getBinds() {
    return this.ll;
  }

  public boolean isSubtype(ASTType other, Environment<ASTType> e) throws InterpreterError {
    if (other instanceof ASTTStruct) {
      ASTTStruct s1 = (ASTTStruct) other;
      HashMap<String, ASTType> matchableLabels = this.ll.getTbl();
      HashMap<String, ASTType> otherMatchableLabels = s1.getBinds().getTbl();
      int matched = 0;
      for (Map.Entry<String, ASTType> entry : matchableLabels.entrySet()) {
        String name = entry.getKey();
        ASTType type = entry.getValue();
        if (otherMatchableLabels.containsKey(name)) {
          ASTType otherType = otherMatchableLabels.get(name);
          if (type.isSubtype(otherType, e)) {
            matched++;
          }
        }
      }
      return matched == otherMatchableLabels.size();
    } else if (other instanceof ASTTId) {
      other = e.unfoldTypes(other);
      return this.isSubtype(other, e);
    }
    return false;
  }

  public String toStr() {
    if (this.ll.getTbl().isEmpty()) {
      return "struct { }";
    }
    String res = "struct { ";
    for (Map.Entry<String, ASTType> entry : this.ll.getTbl().entrySet()) {
      res += entry.getKey() + ": " + entry.getValue().toStr() + ", ";
    }
    res = res.substring(0, res.length() - 2);
    res += " }";
    return res;
  }
}
