package L0.AST;

import L0.ASTType.*;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;

public class ASTFunCall implements ASTNode {
  ASTNode func, arg;

  public ASTFunCall(ASTNode func, ASTNode arg) {
    this.func = func;
    this.arg = arg;
  }

  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    ASTType t1 = this.func.typecheck(e);
    // t1 = e.unrollTypes(t1);

    if (t1 instanceof ASTTArrow) {
      ASTTArrow a1 = (ASTTArrow) t1;
      if (this.arg == null) {
        if (a1.getDomain().toStr().equals("()")) {
          return a1.getCoDomain();
        } else {
          throw new TypeCheckError(
              "a function that receives no arguments must have it's argument type as ()");
        }
      }

      ASTType t2 = this.arg.typecheck(e);
      // t2 = e.unrollTypes(t2);

      if (t2.isSubtype(a1.getDomain())) {
        return a1.getCoDomain();
      } else {
        throw new TypeCheckError(
            "illegal type to argument. Got " + t2.toStr() + ", expected " + a1.getDomain().toStr());
      }
    } else {
      throw new TypeCheckError("illegal type for function call " + t1.toStr());
    }
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    IValue v1 = this.func.eval(e);

    if (v1 instanceof VClos) {
      VClos c1 = (VClos) v1;
      if (this.arg == null) {
        return c1.getBody().eval(new Environment<IValue>(c1.getEnvironment()));
      }

      IValue a1 = this.arg.eval(e);

      Environment<IValue> en = new Environment<IValue>(c1.getEnvironment());
      en.assoc(c1.getParameter(), a1);

      return c1.getBody().eval(en);
    }
    throw new InterpreterError("illegal types to function call");
  }
}
