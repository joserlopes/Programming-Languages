package L0.AST;

import L0.Environment;
import L0.Errors.InterpreterError;
import L0.IValue.*;

public class ASTCons implements ASTNode {
  ASTNode head, tail;

  public ASTCons(ASTNode head, ASTNode tail) {
    this.head = head;
    this.tail = tail;
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    IValue v1 = this.head.eval(e);
    IValue v2 = this.tail.eval(e);

    return new VCons(v1, v2);
  }
}
