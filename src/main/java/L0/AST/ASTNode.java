package L0.AST;

import L0.Environment;
import L0.IValue.*;
import L0.InterpreterError;

public interface ASTNode {
  public IValue eval(Environment<IValue> e) throws InterpreterError;
}
