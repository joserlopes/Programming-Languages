package L0.AST;

import L0.ASTType.*;
import L0.Environment;
import L0.Errors.InterpreterError;
import L0.Errors.TypeCheckError;
import L0.IValue.*;

public class ASTLCons implements ASTNode {
  ASTNode head, tail;

  public ASTLCons(ASTNode head, ASTNode tail) {
    this.head = head;
    this.tail = tail;
  }

  @Override
  public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
    ASTType t1 = this.head.typecheck(e);
    ASTType t2 = this.tail.typecheck(e);

    if (t2 instanceof ASTTList) {
      ASTTList l1 = (ASTTList) t2;
      if (t1.getClass() == l1.getType().getClass()) {
        return l1;
      } else {
        throw new TypeCheckError("illegal list " + t1.toStr() + " " + l1.toStr());
      }
    } else {
      throw new TypeCheckError("illegal list " + t1.toStr() + " " + t2.toStr());
    }
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    return new VLCons(this.head, this.tail, e);
  }
}
