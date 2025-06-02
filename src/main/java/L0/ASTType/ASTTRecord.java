package L0.ASTType;

public class ASTTRecord implements ASTType {
  TypeBindList ll;

  public ASTTRecord(TypeBindList llp) {
    this.ll = llp;
  }

  public TypeBindList getBinds() {
    return this.ll;
  }

  public String toStr() {
    // TODO: Add the names of bindings here.
    return "struct { ... }";
  }
}
