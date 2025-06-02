package L0.AST;

import L0.Environment;
import L0.Errors.InterpreterError;
import L0.IValue.*;

public class ASTFunCall implements ASTNode {
  ASTNode func, arg;

  public ASTFunCall(ASTNode func, ASTNode arg) {
    this.func = func;
    this.arg = arg;
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    IValue v1 = this.func.eval(e);

    if (v1 instanceof VClos) {
      VClos c1 = (VClos) v1;

      IValue a1 = this.arg.eval(e);

      Environment<IValue> en = new Environment<IValue>(c1.getEnvironment());
      en.assoc(c1.getParameter(), a1);

      return c1.getBody().eval(en);
    }
    throw new InterpreterError("illegal types to function call");
  }
}
