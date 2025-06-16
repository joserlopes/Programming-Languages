package L0.AST;

import L0.ASTType.*;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;

public class ASTNeg implements ASTNode {
  ASTNode exp;

  public ASTNeg(ASTNode e) {
    this.exp = e;
  }

  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    ASTType t1 = this.exp.typecheck(e);

    t1 = e.unfoldTypes(t1);

    if (t1 instanceof ASTTInt) {
      return t1;
    }

    throw new TypeCheckError("illegal type to neg operator " + t1.toStr());
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    IValue v0 = this.exp.eval(e);
    if (v0 instanceof VInt) {
      int i0 = ((VInt) v0).getVal();
      return new VInt(-i0);
    }

    throw new InterpreterError("illegal types to neg operator");
  }
}
