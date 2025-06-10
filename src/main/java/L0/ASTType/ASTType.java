package L0.ASTType;

public interface ASTType {
  /* Represents types */
  public String toStr();

  // TODO: Transitive subtyping
  default boolean isSubtype(ASTType other) {
    return this.toStr().equals(other.toStr());
  }
}
