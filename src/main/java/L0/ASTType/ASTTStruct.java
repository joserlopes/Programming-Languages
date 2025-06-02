package L0.ASTType;

public class ASTTStruct implements ASTType {
  TypeBindList ll;

  public ASTTStruct(TypeBindList llp) {
    this.ll = llp;
  }

  public String toStr() {
    // TODO: Had the names of bindings here.
    return "union { ... }";
  }
}
