package L0.AST;

import L0.ASTType.*;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;

public class ASTPlus implements ASTNode {
  ASTNode lhs, rhs;

  public ASTPlus(ASTNode lhs, ASTNode rhs) {
    this.lhs = lhs;
    this.rhs = rhs;
  }

  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    ASTType t1 = this.lhs.typecheck(e);
    ASTType t2 = this.rhs.typecheck(e);
    if (t1 instanceof ASTTInt) {
      if (t2 instanceof ASTTInt || t2 instanceof ASTTString) {
        return t2;
      } else {
        throw new TypeCheckError("illegal type to + operator " + t2.toStr());
      }
    } else if (t1 instanceof ASTTString) {
      if (t2 instanceof ASTTInt || t2 instanceof ASTTString) {
        return t1;
      } else {
        throw new TypeCheckError("illegal type to + operator " + t2.toStr());
      }
    } else {
      throw new TypeCheckError("illegal type to + operator " + t1.toStr());
    }
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    IValue v1 = this.lhs.eval(e);
    IValue v2 = this.rhs.eval(e);
    if (v1 instanceof VInt && v2 instanceof VInt) {
      int i1 = ((VInt) v1).getVal();
      int i2 = ((VInt) v2).getVal();
      return new VInt(i1 + i2);
    }

    if (v1 instanceof VString || v2 instanceof VString) {
      String res = v1.toStr() + v2.toStr();
      res = res.replace("\"", "");
      res = "\"" + res + "\"";
      return new VString(res);
    }

    throw new InterpreterError("illegal types to + operator");
  }
}
