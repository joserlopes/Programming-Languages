package L0.AST;

import L0.Environment;
import L0.IValue.*;
import L0.InterpreterError;
import java.util.List;

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

      IValue a1 = this.arg.eval(e);

      int nArgs = c1.getParameters().size();

      if (nArgs > 1) {
        String firstParam = c1.getParameters().get(0);
        List<String> remainingParams = c1.getParameters().subList(1, nArgs);

        Environment<IValue> en = new Environment<>(c1.getEnvironment());
        en.assoc(firstParam, a1);

        return new VClos(remainingParams, c1.getBody(), en);
      } else {
        String param = c1.getParameters().get(0);

        Environment<IValue> en = new Environment<>(c1.getEnvironment());
        en.assoc(param, a1);

        return c1.getBody().eval(en);
      }
    }
    throw new InterpreterError("illegal types to function call");
  }
}
