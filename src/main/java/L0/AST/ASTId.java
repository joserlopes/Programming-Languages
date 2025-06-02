package L0.AST;

import L0.ASTType.*;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;

public class ASTId implements ASTNode {
  String id;

  public ASTId(String id) {
    this.id = id;
  }

  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    return e.find(this.id);
  }

  public IValue eval(Environment<IValue> env) throws InterpreterError {
    return env.find(this.id);
  }
}
