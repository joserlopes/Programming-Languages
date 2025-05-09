package L0.AST;

import L0.Environment;
import L0.IValue.*;
import L0.InterpreterError;

public class ASTFunCall implements ASTNode {
  ASTNode func;
  ASTNode arg;

  public ASTFunCall(ASTNode func, ASTNode arg) {
    this.func = func;
    this.arg = arg;
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    IValue v1 = this.func.eval(e);

    if (v1 instanceof VClos) {
      VClos c1 = (VClos) v1;
      System.out.println("Viemos para aqui!!!");
    }
    throw new InterpreterError("illegal types to function call");
  }
}
