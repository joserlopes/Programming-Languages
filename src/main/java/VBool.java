class VBool implements IValue {
  boolean v;

  VBool(boolean v0) {
    this.v = v0;
  }

  boolean getVal() {
    return this.v;
  }

  public String toStr() {
    return Boolean.toString(this.v);
  }
}
