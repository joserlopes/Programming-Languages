package L0.AST;

import L0.ASTType.*;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;

public class ASTAssign implements ASTNode {
  ASTNode lhs, rhs;

  public ASTAssign(ASTNode lhs, ASTNode rhs) {
    this.lhs = lhs;
    this.rhs = rhs;
  }

  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    ASTType t1 = this.lhs.typecheck(e);
    if (t1 instanceof ASTTBox) {
      ASTTBox b1 = (ASTTBox) t1;
      ASTType t2 = this.rhs.typecheck(e);
      if (t2.getClass() == b1.getType().getClass()) {
        b1.assign(t2);
        return t2;
      } else {
        throw new TypeCheckError(
            "illegal type to right := operator. Needed: "
                + b1.getType().toStr()
                + ", got: "
                + t2.toStr());
      }
    } else {
      throw new TypeCheckError("illegal type to left := operator " + t1.toStr());
    }
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    IValue v1 = this.lhs.eval(e);
    if (v1 instanceof VBox) {
      // v2 is the new value that is to be assigned to the left-hand side.
      IValue v2 = this.rhs.eval(e);
      VBox b1 = (VBox) v1;
      b1.assign(v2);

      // Do we return the boxed value, or the inner value?
      return v2;
    }

    throw new InterpreterError("illegal types to := operator");
  }
}
