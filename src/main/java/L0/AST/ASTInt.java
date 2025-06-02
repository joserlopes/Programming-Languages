package L0.AST;

import L0.ASTType.*;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;

public class ASTInt implements ASTNode {
  int value;

  public ASTInt(int value) {
    this.value = value;
  }

  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    return new ASTTInt();
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    return new VInt(this.value);
  }
}
