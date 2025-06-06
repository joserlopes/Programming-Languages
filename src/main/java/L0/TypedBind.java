package L0;

import L0.AST.*;
import L0.ASTType.ASTType;

public class TypedBind {
  private final String id;
  private final ASTNode exp;
  private final ASTType type;

  public TypedBind(String id, ASTNode exp, ASTType type) {
    this.id = id;
    this.exp = exp;
    this.type = type;
  }

  public String getId() {
    return this.id;
  }

  public ASTNode getExp() {
    return this.exp;
  }

  public ASTType getType() {
    return this.type;
  }

  // This is here just for debug purposes
  public String toString() {
    return "id: " + this.id + " exp: " + this.exp + " type: " + this.type;
  }
}
