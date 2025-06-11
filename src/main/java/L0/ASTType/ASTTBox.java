package L0.ASTType;

import L0.Environment;
import L0.Errors.InterpreterError;

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

  public boolean isSubtype(ASTType other, Environment<ASTType> e) throws InterpreterError {
    if (other instanceof ASTTBox) {
      ASTTBox b1 = (ASTTBox) other;
      return this.type.isSubtype(b1.getType(), e) && b1.getType().isSubtype(this.type, e);
    } else if (other instanceof ASTTId) {
      other = e.unfoldTypes(other);
      return this.isSubtype(other, e);
    }

    return false;
  }

  public String toStr() {
    return "box<" + this.type.toStr() + ">";
  }
}
