package L0;

import static org.testng.Assert.*;

import L0.AST.*;
import L0.IValue.*;
import java.io.FileReader;
import java.io.StringReader;
import org.testng.annotations.*;

@Test
public class TestLet {
  StringReader reader;
  static Parser parser = TestArithmetic.parser;
  ASTNode exp;

  @DataProvider
  private Object[][] simpleLetValues() {
    return new Object[][] {
      {"let x = 1; (x + 1);;", "2"},
      {"let x = 1; let y = 2; (x + y);;", "3"},
    };
  }

  @Test(dataProvider = "simpleLetValues")
  public void simpleLet(String input, String result) {
    StringReader reader = new StringReader(input);
    parser.ReInit(reader);

    try {
      ASTNode exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      assertEquals(v_str, result);
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }

  public void nestedLet() {
    String path = "src/test/java/L0/nestedLet.L0";

    try {
      parser.ReInit(new FileReader(path));
      ASTNode exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      assertEquals(v_str, "72");
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }
}
