package L0.AST;

import L0.ASTType.*;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;

public class ASTIf implements ASTNode {
  ASTNode cond, trueBody, falseBody;

  public ASTIf(ASTNode cond, ASTNode trueBody, ASTNode falseBody) {
    this.cond = cond;
    this.trueBody = trueBody;
    this.falseBody = falseBody;
  }

  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    ASTType t1 = this.cond.typecheck(e);
    if (t1 instanceof ASTTBool) {
      ASTType t2 = this.trueBody.typecheck(e);
      ASTType t3 = this.falseBody.typecheck(e);
      if (t2.isSubtype(t3, e)) {
        return t3;
      } else if (t3.isSubtype(t2, e)) {
        return t2;
      } else {
        throw new TypeCheckError("illegal type to if body " + t2.toStr() + " " + t3.toStr());
      }
    } else {
      throw new TypeCheckError("illegal type for the if condition " + t1.toStr());
    }
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    IValue v1 = this.cond.eval(e);
    if (v1 instanceof VBool) {
      boolean b1 = ((VBool) v1).getVal();
      if (b1) {
        return this.trueBody.eval(e);
      } else {
        return this.falseBody.eval(e);
      }
    }
    throw new InterpreterError("illegal types to the condition of if");
  }
}
