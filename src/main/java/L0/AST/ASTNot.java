package L0.AST;

import L0.ASTType.*;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;

public class ASTNot implements ASTNode {
  ASTNode exp;

  public ASTNot(ASTNode rhs) {
    this.exp = rhs;
  }

  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    ASTType t1 = this.exp.typecheck(e);
    if (t1 instanceof ASTTBool) {
      return new ASTTBool();
    } else {
      throw new TypeCheckError("illegal type to ~ operator " + t1.toStr());
    }
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    IValue v0 = this.exp.eval(e);
    if (v0 instanceof VBool) {
      boolean b0 = ((VBool) v0).getVal();
      return new VBool(!b0);
    }

    throw new InterpreterError("illegal types to ~ operator");
  }
}
