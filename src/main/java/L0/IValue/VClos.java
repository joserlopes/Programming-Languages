package L0.IValue;

import L0.AST.ASTNode;
import L0.Environment;

public class VClos implements IValue {
  String param;
  ASTNode body;
  Environment<IValue> en;

  public VClos(String parameter, ASTNode body, Environment<IValue> en) {
    this.param = parameter;
    this.body = body;
    this.en = en;
  }

  public ASTNode getBody() {
    return this.body;
  }

  public Environment<IValue> getEnvironment() {
    return this.en;
  }

  public String getParameter() {
    return this.param;
  }

  public String toStr() {
    return "app(" + this.param + ")";
  }
}
