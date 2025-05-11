package L0.IValue;

public class VCons implements IValue {
  IValue head, tail;

  public VCons(IValue head, IValue tail) {
    this.head = head;
    this.tail = tail;
  }

  public IValue getHead() {
    return this.getHead();
  }

  public String toStr() {
    return "cons(" + this.head.toStr() + "," + this.tail.toStr() + ")";
  }
}
