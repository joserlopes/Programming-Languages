package L0.AST;

import L0.ASTType.*;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;

public class ASTWhile implements ASTNode {
  ASTNode cond, body;

  public ASTWhile(ASTNode cond, ASTNode body) {
    this.cond = cond;
    this.body = body;
  }

  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    ASTType t1 = this.cond.typecheck(e);
    if (t1 instanceof ASTTBool) {
      return this.body.typecheck(e);
    } else {
      throw new TypeCheckError("illegal type for the while condition " + t1.toStr());
    }
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    // Evaluate the condition
    IValue v1 = this.cond.eval(e);
    if (v1 instanceof VBool) {
      boolean b1 = ((VBool) v1).getVal();
      // If it is true evaluate the body and loop over
      while (b1) {
        // Evaluate the body
        this.body.eval(e);
        // Evaluate the condition once again
        v1 = this.cond.eval(e);
        b1 = ((VBool) v1).getVal();
      }

      return new VBool(true);
    }
    throw new InterpreterError("illegal types for the codition of the while loop");
  }
}
