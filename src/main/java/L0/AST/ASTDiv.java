package L0.AST;

import L0.Environment;
import L0.Errors.InterpreterError;
import L0.IValue.*;

public class ASTDiv implements ASTNode {
  ASTNode lhs, rhs;

  public ASTDiv(ASTNode lhs, ASTNode rhs) {
    this.lhs = lhs;
    this.rhs = rhs;
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    IValue v1 = this.lhs.eval(e);
    if (v1 instanceof VInt) {
      IValue v2 = this.rhs.eval(e);
      if (v2 instanceof VInt) {
        int i1 = ((VInt) v1).getVal();
        int i2 = ((VInt) v2).getVal();
        if (i2 == 0) {
          throw new InterpreterError("division by zero");
        }
        return new VInt(i1 / i2);
      }
    }

    throw new InterpreterError("illegal types to / operator");
  }
}
