package L0.ASTType;

public class ASTTList implements ASTType {
  ASTType type;

  public ASTTList(ASTType type) {
    this.type = type;
  }

  public ASTType getType() {
    return this.type;
  }

  public String toStr() {
    return "list<" + this.type.toStr() + ">";
  }
}
