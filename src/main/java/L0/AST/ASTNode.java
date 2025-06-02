package L0.AST;

import L0.Environment;
import L0.Errors.InterpreterError;
import L0.IValue.IValue;

public interface ASTNode {
  public IValue eval(Environment<IValue> e) throws InterpreterError;

  // public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError;
}
