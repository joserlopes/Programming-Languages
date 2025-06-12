package L0.ASTType;

import L0.Environment;
import L0.Errors.InterpreterError;

public interface ASTType {
  public String toStr();

  default boolean isSubtype(ASTType other, Environment<ASTType> e) throws InterpreterError {
    if (other instanceof ASTTId) {
      other = e.unfoldTypes(other);
      return this.isSubtype(other, e);
    }

    return this.toStr().equals(other.toStr());
  }
}
