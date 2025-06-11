package L0.ASTType;

import L0.Environment;
import L0.Errors.InterpreterError;
import java.util.HashMap;
import java.util.Map;

public class ASTTUnion implements ASTType {
  TypeBindList ll;

  public ASTTUnion(TypeBindList ll) {
    this.ll = ll;
  }

  public TypeBindList getBinds() {
    return this.ll;
  }

  public boolean isSubtype(ASTType other, Environment<ASTType> e) throws InterpreterError {
    if (other instanceof ASTTUnion) {
      ASTTUnion u1 = (ASTTUnion) other;
      HashMap<String, ASTType> matchableLabels = this.ll.getTbl();
      HashMap<String, ASTType> otherMatchableLabels = u1.getBinds().getTbl();
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
      return matched == matchableLabels.size();
    } else if (other instanceof ASTTId) {
      other = e.unfoldTypes(other);
      return this.isSubtype(other, e);
    }
    return false;
  }

  public String toStr() {
    String res = "union { ";
    for (Map.Entry<String, ASTType> entry : this.ll.getTbl().entrySet()) {
      res += entry.getKey() + ": " + entry.getValue().toStr() + ", ";
    }
    res = res.substring(0, res.length() - 2);
    res += " }";
    return res;
  }
}
