package L0.ASTType;

import java.util.HashMap;

public class TypeBindList {
  HashMap<String, ASTType> lbl;

  public TypeBindList(HashMap<String, ASTType> ll) {
    this.lbl = ll;
  }
}
