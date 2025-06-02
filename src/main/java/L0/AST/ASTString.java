package L0.AST;

import L0.ASTType.*;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;

public class ASTString implements ASTNode {
  String value;

  public ASTString(String value) {
    this.value = value;
  }

  @Override
  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    return new ASTTString();
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    return new VString(this.value);
  }
}
