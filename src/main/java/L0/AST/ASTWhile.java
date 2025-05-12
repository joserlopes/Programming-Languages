package L0.AST;

import L0.Environment;
import L0.IValue.*;
import L0.InterpreterError;

public class ASTWhile implements ASTNode {
  ASTNode cond, body;

  public ASTWhile(ASTNode cond, ASTNode body) {
    this.cond = cond;
    this.body = body;
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
