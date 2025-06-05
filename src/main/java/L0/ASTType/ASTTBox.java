package L0.ASTType;

public class ASTTBox implements ASTType {
  ASTType type;

  public ASTTBox(ASTType type) {
    this.type = type;
  }

  public ASTType getType() {
    return this.type;
  }

  public void assign(ASTType type) {
    this.type = type;
  }

  public String toStr() {
    return "box<" + this.type.toStr() + ">";
  }
}
