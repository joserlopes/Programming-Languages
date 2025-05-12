package L0.IValue;

import L0.AST.ASTNode;
import L0.Environment;
import java.util.List;

public class VClos implements IValue {
  List<String> params;
  ASTNode body;
  Environment<IValue> en;

  public VClos(List<String> parameters, ASTNode body, Environment<IValue> en) {
    this.params = parameters;
    this.body = body;
    this.en = en;
  }

  public List<String> getParameters() {
    return this.params;
  }

  public ASTNode getBody() {
    return this.body;
  }

  public Environment<IValue> getEnvironment() {
    return this.en;
  }

  public String toStr() {
    // This doesn't matter, since this never ends up going to the call at the end of the interpreter
    // read
    return "";
  }
}
