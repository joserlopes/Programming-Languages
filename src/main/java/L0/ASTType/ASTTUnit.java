package L0.ASTType;

import L0.Environment;
import L0.Errors.InterpreterError;

public class ASTTUnit implements ASTType {
  public ASTTUnit() {}

  public boolean isSubtype(ASTType other, Environment<ASTType> e) throws InterpreterError {
    return true;
  }

  public String toStr() {
    return "()";
  }
}
