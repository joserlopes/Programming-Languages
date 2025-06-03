package L0.AST;

import L0.ASTType.*;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;

public class ASTUnit implements ASTNode {
  public ASTUnit() {}

  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    return new ASTTUnit();
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    return new VNil();
  }
}
