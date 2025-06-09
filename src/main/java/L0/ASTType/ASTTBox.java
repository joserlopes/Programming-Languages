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

  public boolean isSubtype(ASTType other) {
    if (other instanceof ASTTBox) {
      ASTTBox b1 = (ASTTBox) other;
      return this.type.isSubtype(b1.getType()) && b1.getType().isSubtype(this.type);
    }

    return false;
  }

  public String toStr() {
    return "box<" + this.type.toStr() + ">";
  }
}
