package L0.AST;

import L0.ASTType.*;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;

public class ASTDeref implements ASTNode {
  ASTNode exp;

  public ASTDeref(ASTNode exp) {
    this.exp = exp;
  }

  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    ASTType t1 = this.exp.typecheck(e);
    if (t1 instanceof ASTTBox) {
      ASTTBox b1 = (ASTTBox) t1;
      return b1.getType();
    } else {
      throw new TypeCheckError("illegal type to * operator " + t1.toStr());
    }
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    IValue v1 = this.exp.eval(e);
    if (v1 instanceof VBox) {
      return ((VBox) v1).deref();
    }

    throw new InterpreterError("illegal type to be dereferenced");
  }
}
