package L0.ASTType;

public class ASTTArrow implements ASTType {
  ASTType dom;
  ASTType codom;

  public ASTTArrow(ASTType dom, ASTType codom) {
    this.dom = dom;
    this.codom = codom;
  }

  public ASTType getDomain() {
    return this.dom;
  }

  public ASTType getCoDomain() {
    return this.codom;
  }

  public boolean isSubtype(ASTType other) {
    if (other instanceof ASTTArrow) {
      ASTTArrow a1 = (ASTTArrow) other;
      return a1.getDomain().isSubtype(this.dom) && this.codom.isSubtype(a1.getCoDomain());
    }
    return false;
  }

  public String toStr() {
    return this.dom.toStr() + "->" + this.codom.toStr();
  }
}
