package L0;

import L0.AST.*;
import L0.IValue.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class L0int {
  public static void main(String args[]) throws IOException {
    ASTNode exp;

    if (args.length == 1) {
      if (args[0].endsWith(".xpp")) {
        try {
          FileReader file = new FileReader(args[0]);
          Parser parser = new Parser(file);
          // TODO: Type checking
          exp = parser.Start();
          if (exp == null) {
            file.close();
            System.exit(0);
          }
          IValue v = exp.eval(new Environment<IValue>());
          System.out.println(v.toStr());
        } catch (FileNotFoundException e) {
          System.out.println("File " + args[0] + "not found");
        } catch (ParseException e) {
          System.out.println("Syntax Error.");
        } catch (Exception e) {
          e.printStackTrace();
        }
      } else {
        System.out.println("File must end in .xpp");
      }
    } else {
      Parser parser = new Parser(System.in);
      System.out.println("L0 interpreter PL MEIC 2024/25\n");
      while (true) {
        try {
          System.out.print("# ");
          exp = parser.Start();
          if (exp == null) System.exit(0);
          // TODO: Type checking
          IValue v = exp.eval(new Environment<IValue>());
          System.out.println(v.toStr());
        } catch (ParseException e) {
          System.out.println("Syntax Error.");
          parser.ReInit(System.in);
        } catch (Exception e) {
          e.printStackTrace();
          parser.ReInit(System.in);
        }
      }
    }
  }
}
