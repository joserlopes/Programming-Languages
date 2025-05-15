package L0.IValue;

import L0.AST.*;
import L0.Environment;
import L0.InterpreterError;

public class VLCons implements IValue {
  // Contrary to what happens in a Regular List, the values of head and tail
  // need to be ASTNode, because they are not yet evaluated and it needs to have
  // an environment for when they are evaluated.
  ASTNode head, tail;
  Environment<IValue> e;

  IValue evaluatedHead = null;
  IValue evaluatedTail = null;
  boolean headWasEvaluated = false;
  boolean tailWasEvaluated = false;

  public VLCons(ASTNode head, ASTNode tail, Environment<IValue> e) {
    this.head = head;
    this.tail = tail;
    this.e = e;
  }

  public IValue getHead() throws InterpreterError {
    if (!this.headWasEvaluated) {
      this.evaluatedHead = this.head.eval(this.e);
      this.headWasEvaluated = true;
    }
    return this.evaluatedHead;
  }

  public IValue getTail() throws InterpreterError {
    if (!this.tailWasEvaluated) {
      this.evaluatedTail = this.tail.eval(this.e);
      this.tailWasEvaluated = true;
    }
    return this.evaluatedTail;
  }

  public String toStr() {
    if (this.headWasEvaluated && this.tailWasEvaluated) {
      return "cons(" + this.evaluatedHead.toStr() + ", " + this.evaluatedTail.toStr() + ")";
    } else {
      return "lcons(unknown, unknown)";
    }
  }
}
