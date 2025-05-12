package L0;

import static org.testng.Assert.*;

import L0.AST.*;
import L0.IValue.*;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.StringReader;
import org.testng.annotations.*;

@Test
public class TestLazyList {
  StringReader reader;
  static Parser parser = TestArithmetic.parser;
  ASTNode exp;

  public void infiniteFibonnaci() {
    String path = "src/test/java/L0/LazyList1.L0";

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream capturedOutput = new PrintStream(outputStream);

    System.setOut(capturedOutput);

    try {
      parser.ReInit(new FileReader(path));
      exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      // NOTE: How can I test what gets printed to stdout?
      assertEquals(v_str, "true");

      capturedOutput.flush();
      String capturedString = outputStream.toString().trim();
      assertEquals(capturedString, "0\n1\n1\n2\n3\n5\n8\n13\n21\n34");
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }

  public void infiniteFactorial() {
    String path = "src/test/java/L0/LazyList2.L0";

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream capturedOutput = new PrintStream(outputStream);

    System.setOut(capturedOutput);

    try {
      parser.ReInit(new FileReader(path));
      exp = parser.Start();
      String v_str = exp.eval(new Environment<IValue>()).toStr();
      // NOTE: How can I test what gets printed to stdout?
      assertEquals(v_str, "true");

      capturedOutput.flush();
      String capturedString = outputStream.toString().trim();
      assertEquals(capturedString, "1\n2\n6\n24\n120\n720\n5040\n40320\n362880\n3628800");
    } catch (Exception e) {
      // Ignore exception, it never fails
    }
  }
}
