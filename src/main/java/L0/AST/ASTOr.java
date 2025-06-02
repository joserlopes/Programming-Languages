package L0.AST;

import L0.ASTType.*;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;

public class ASTOr implements ASTNode {
  ASTNode lhs, rhs;

  public ASTOr(ASTNode lhs, ASTNode rhs) {
    this.lhs = lhs;
    this.rhs = rhs;
  }

  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    ASTType t1 = this.lhs.typecheck(e);
    if (t1 instanceof ASTTBool) {
      ASTType t2 = this.rhs.typecheck(e);
      if (t2 instanceof ASTTBool) {
        return t1;
      } else {
        throw new TypeCheckError("illegal type to || operator " + t2.toStr());
      }
    } else {
      throw new TypeCheckError("illegal type to || operator " + t1.toStr());
    }
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    IValue v1 = this.lhs.eval(e);
    if (v1 instanceof VBool) {
      IValue v2 = this.rhs.eval(e);
      if (v2 instanceof VBool) {
        boolean b1 = ((VBool) v1).getVal();
        boolean b2 = ((VBool) v2).getVal();
        return new VBool(b1 || b2);
      }
    }

    throw new InterpreterError("illegal types to || operator");
  }
}
