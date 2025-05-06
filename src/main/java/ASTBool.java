class ASTBool implements ASTNode {
  boolean v;

  ASTBool(boolean v) {
    this.v = v;
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    return new VBool(this.v);
  }
}
