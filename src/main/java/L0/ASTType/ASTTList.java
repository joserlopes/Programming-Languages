package L0.ASTType;

import L0.Environment;
import L0.Errors.InterpreterError;

public class ASTTList implements ASTType {
  ASTType type;

  public ASTTList(ASTType type) {
    this.type = type;
  }

  public ASTType getType() {
    return this.type;
  }

  public boolean isSubtype(ASTType other, Environment<ASTType> e) throws InterpreterError {
    if (other instanceof ASTTList) {
      return this.type.isSubtype(((ASTTList) other).getType(), e);
    } else if (other instanceof ASTTId) {
      other = e.unfoldTypes(other);
      return this.isSubtype(other, e);
    }
    return false;
  }

  public String toStr() {
    return "list<" + this.type.toStr() + ">";
  }
}
