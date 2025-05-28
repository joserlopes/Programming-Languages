package L0.AST;

import L0.Environment;
import L0.Errors.InterpreterError;
import L0.IValue.*;

public class ASTMatch implements ASTNode {
  ASTNode matchedValue, nilCase, consCase;
  String headName, tailName;

  public ASTMatch(
      ASTNode matchedValue, ASTNode nilCase, String headName, String tailName, ASTNode consCase) {
    this.matchedValue = matchedValue;
    this.nilCase = nilCase;
    this.consCase = consCase;
    this.headName = headName;
    this.tailName = tailName;
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    IValue v1 = this.matchedValue.eval(e);

    if (v1 instanceof VCons) {
      VCons c1 = (VCons) v1;

      IValue v2 = c1.getHead();
      IValue v3 = c1.getTail();

      Environment<IValue> en = new Environment<IValue>(e);
      en.assoc(headName, v2);
      en.assoc(tailName, v3);

      return consCase.eval(en);
    } else if (v1 instanceof VLCons) {
      VLCons lc1 = (VLCons) v1;

      IValue v2 = lc1.getHead();
      IValue v3 = lc1.getTail();
      Environment<IValue> en = new Environment<IValue>(e);
      en.assoc(headName, v2);
      en.assoc(tailName, v3);

      return this.consCase.eval(en);
    } else if (v1 instanceof VNil) {
      return this.nilCase.eval(e);
    }

    throw new InterpreterError("invalid type to match");
  }
}
