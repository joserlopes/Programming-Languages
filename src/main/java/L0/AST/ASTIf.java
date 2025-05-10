package L0.AST;

import L0.Environment;
import L0.IValue.*;
import L0.InterpreterError;

public class ASTIf implements ASTNode {

  ASTNode cond, trueBody, falseBody;

  public ASTIf(ASTNode cond, ASTNode trueBody, ASTNode falseBody) {
    this.cond = cond;
    this.trueBody = trueBody;
    this.falseBody = falseBody;
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    // Evaluate the condition
    IValue v1 = this.cond.eval(e);
    if (v1 instanceof VBool) {
      boolean b1 = ((VBool) v1).getVal();
      // If it is true evaluate the trueBody
      if (b1) {
        return this.trueBody.eval(e);
        // otherwise, evaluate the falseBody
      } else {
        return this.falseBody.eval(e);
      }
    }
    throw new InterpreterError("illegal types to the condition of if");
  }
}
