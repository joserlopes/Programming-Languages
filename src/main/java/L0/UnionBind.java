package L0;

import L0.AST.*;

public class UnionBind {
  private final String label;
  private final String name;
  private final ASTNode exp;

  public UnionBind(String label, String name, ASTNode exp) {
    this.label = label;
    this.name = name;
    this.exp = exp;
  }

  public String getLabel() {
    return this.label;
  }

  public String getName() {
    return this.name;
  }

  public ASTNode getExp() {
    return this.exp;
  }
}
