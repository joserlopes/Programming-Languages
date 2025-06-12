# Programming Languages Project

## First Phase

### Structure

- All the source code is at `src/main/java/L0/`
- All the test code is at `src/test/java/L0/`. The majority of the test cases belong to files ending in `.L0`.
- The code for the parser is at `src/main/javacc/ParserL0.jj`

### Commands

- `makeit` - Builds the interpreter
- `x++` - Starts the REPL. If a file ending in `.xpp` is passed as argument, that file is evaluated instead.
- `test` - Runs automatic tests

### Notes

- The syntax for dereferencing boxed values is `*`
- The syntax for building regular lists is `cons(N, M)`
- The syntax for building lazy lists is `lcons(N, M)`
- The syntax for the match construct is `match M { | nil -> N | y :: z -> K }`.

## Second Phase

Everything that is applied in the first phase also applies here.
