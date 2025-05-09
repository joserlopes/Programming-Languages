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
    String res = "";
    for (String parameter : this.params) {
      res += parameter + " ";
    }
    res += "\n" + this.body;
    return res;
  }
}
