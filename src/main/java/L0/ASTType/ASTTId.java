package L0.ASTType;

import L0.Environment;
import L0.Errors.InterpreterError;

public class ASTTId implements ASTType {
  String id;

  public ASTTId(String id) {
    this.id = id;
  }

  public boolean isSubtype(ASTType other, Environment<ASTType> e) throws InterpreterError {
    if (other instanceof ASTTId) {
      return this.toStr().equals(other.toStr());
    }

    return e.unfoldTypes(this).isSubtype(other, e);
  }

  public String toStr() {
    return this.id;
  }
}
