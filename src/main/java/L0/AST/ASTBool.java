package L0.AST;

import L0.ASTType.*;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;

public class ASTBool implements ASTNode {
  boolean value;

  public ASTBool(boolean value) {
    this.value = value;
  }

  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    return new ASTTBool();
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    return new VBool(this.value);
  }
}
