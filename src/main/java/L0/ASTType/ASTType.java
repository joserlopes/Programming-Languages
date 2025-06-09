package L0.ASTType;

public interface ASTType {
  /* Represents types */
  public String toStr();

  // TODO: Maybe change this!!
  default boolean isSubtype(ASTType other) {
    return true;
  }
}
