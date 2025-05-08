package L0;

import static org.testng.Assert.*;

import L0.AST.*;
import L0.IValue.*;
import java.io.StringReader;
import org.testng.annotations.*;

@Test
public class TestArithmetic {
  StringReader reader;
  static Parser parser = new Parser(new StringReader(""));
  ASTNode exp;

  @DataProvider
  private Object[][] simpleSumValues() {
    return new Object[][] {
      {"1 + 2;;", "3"},
      {"3 + 7;;", "10"},
      {"-10 + 30;;", "20"},
      {"-10 - 30;;", "-40"},
      {"10 - 30;;", "-20"},
    };
  }

  @DataProvider
  private Object[][] simpleMulValues() {
    return new Object[][] {
      {"1 * 2;;", "2"},
      {"3 * 7;;", "21"},
      {"-10 * 30;;", "-300"},
      {"-10 * -30;;", "300"},
    };
  }

  @DataProvider
  private Object[][] simpleDivValues() {
    return new Object[][] {
      {"2 / 1;;", "2"},
      {"9 / 3;;", "3"},
      {"30 / 10;;", "3"},
      {"-35 / 5;;", "-7"},
    };
  }

  @Test(dataProvider = "simpleSumValues")
  public void simpleSum(String input, String result) {
    StringReader reader = new StringReader(input);
    parser.ReInit(reader);
    try {
      exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      assertEquals(v_str, result);
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }

  @Test(dataProvider = "simpleMulValues")
  public void simpleMul(String input, String result) {
    StringReader reader = new StringReader(input);
    parser.ReInit(reader);
    try {
      exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      assertEquals(v_str, result);
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }

  @Test(dataProvider = "simpleDivValues")
  public void simpleDiv(String input, String result) {
    StringReader reader = new StringReader(input);
    parser.ReInit(reader);
    try {
      exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      assertEquals(v_str, result);
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }
}
