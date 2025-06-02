package L0.ASTType;

public class ASTTList implements ASTType {
  ASTType elt;

  public ASTTList(ASTType elt) {
    this.elt = elt;
  }

  public String toStr() {
    return "list<" + this.elt.toStr() + ">";
  }
}
