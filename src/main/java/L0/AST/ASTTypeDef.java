package L0.AST;

import L0.ASTType.ASTType;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;
import java.util.HashMap;

public class ASTTypeDef implements ASTNode {
  HashMap<String, ASTType> ltd;
  ASTNode body;

  public ASTTypeDef(HashMap<String, ASTType> ltdp, ASTNode body) {
    this.ltd = ltdp;
    this.body = body;
  }

  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    // TODO
    throw new UnsupportedOperationException("Unimplemented method 'typecheck'");
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    return body.eval(e);
  }
}
