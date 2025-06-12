package L0.ASTType;

import L0.Environment;
import L0.Errors.InterpreterError;

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

  public boolean isSubtype(ASTType other, Environment<ASTType> e) throws InterpreterError {
    if (other instanceof ASTTId) {
      other = e.unfoldTypes(other);
      return this.isSubtype(other, e);
    } else if (other instanceof ASTTArrow) {
      ASTTArrow a1 = (ASTTArrow) other;
      return a1.getDomain().isSubtype(this.dom, e) && this.codom.isSubtype(a1.getCoDomain(), e);
    }

    return false;
  }

  public String toStr() {
    return this.dom.toStr() + "->" + this.codom.toStr();
  }
}
