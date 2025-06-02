package L0.ASTType;

public class ASTTArrow implements ASTType {
  ASTType dom;
  ASTType codom;

  public ASTTArrow(ASTType dom, ASTType codom) {
    this.dom = dom;
    this.codom = codom;
  }

  public String toStr() {
    return this.dom.toStr() + "->" + this.codom.toStr();
  }
}
