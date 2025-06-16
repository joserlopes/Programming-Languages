package L0.AST;

import L0.ASTType.*;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;

public class ASTLt implements ASTNode {
  ASTNode lhs, rhs;

  public ASTLt(ASTNode lhs, ASTNode rhs) {
    this.lhs = lhs;
    this.rhs = rhs;
  }

  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    ASTType t1 = this.lhs.typecheck(e);
    t1 = e.unfoldTypes(t1);

    if (t1 instanceof ASTTInt) {
      ASTType t2 = this.rhs.typecheck(e);
      if (t2 instanceof ASTTInt) {
        return new ASTTBool();
      } else {
        throw new TypeCheckError("illegal type to < operator " + t2.toStr());
      }
    } else {
      throw new TypeCheckError("illegal type to < operator " + t1.toStr());
    }
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    IValue v1 = this.lhs.eval(e);
    if (v1 instanceof VInt) {
      IValue v2 = this.rhs.eval(e);
      if (v2 instanceof VInt) {
        int i1 = ((VInt) v1).getVal();
        int i2 = ((VInt) v2).getVal();
        return new VBool(i1 < i2);
      }
    }
    throw new InterpreterError("illegal types to < operator");
  }
}
