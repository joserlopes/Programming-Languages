package L0.ASTType;

import java.util.HashMap;

public class TypeBindList {
  HashMap<String, ASTType> lbl;

  public TypeBindList(HashMap<String, ASTType> ll) {
    this.lbl = ll;
  }

  public HashMap<String, ASTType> getTbl() {
    return this.lbl;
  }

  public ASTType getType(String label) {
    return this.lbl.get(label);
  }
}
