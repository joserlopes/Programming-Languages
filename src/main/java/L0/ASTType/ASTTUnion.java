package L0.ASTType;

public class ASTTUnion implements ASTType {
  String lbl;
  ASTType type;

  public ASTTUnion(String lbl, ASTType type) {
    this.lbl = lbl;
    this.type = type;
  }

  public String toStr() {
    return "union { " + this.lbl + ": " + this.type.toStr() + " }";
  }
}
