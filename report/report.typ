#set text(lang: "en", size: 10.5pt)
#show heading: it => {
  if it.level == 1 {
    set text(17pt, navy)
    align(center)[#it]
  } else if it.level == 2 {
    set text(15pt, navy)
    align(center)[#it]
  } else if it.level == 3 {
    set text(14pt, navy)
    it
  }
}

#show link: it => {
  set text(blue)
  underline(it)
}

#show raw.where(block: false): box.with(
  fill: luma(240),
  inset: (x: 3pt, y: 0pt),
  outset: (y: 3pt),
  radius: 2pt,
)

#show raw.where(block: true): block.with(
  fill: luma(240),
  inset: 10pt,
  radius: 4pt,
)


#set par(justify: true, first-line-indent: (
  amount: 1em,
  all: true,
))

#set list(indent: 1em)

#let rule(premises, conclusion, length) = {
  box(grid(
    columns: 1fr,
    row-gutter: (6pt, 6pt),
    gutter: 0pt,
    align(center, premises),
    align(center, line(length: length)),
    align(center, conclusion)
  ))
}

= Programming Languages

== First Phase Report - Lazy Lists

\
#align(center)[
  José António Ribeiro da Silva Lopes\
  ist1103938
]
\

=== Big Step Evaluation rules

#rule($$, $ℰ; "lcons("N, M) arrow.b.double "lcons("N, M, ℰ)$, 33%)

#v(1em)

#grid(
  columns: (1fr, 1fr),
  column-gutter: 24pt,
  rule(
    $ℰ; M arrow.b.double "nil" #h(1em) ℰ; N arrow.b.double U$,
    $ℰ; "match" M { " | nil" -> N | y :: z -> K } arrow.b.double U$,
    87%,
  ),
  rule(
    $ℰ; M arrow.b.double "lcons("I, J, ℰ) #h(1em) ℰ[y -> I; z -> J]; K arrow.b.double U$,
    $ℰ; "match" M { " | nil" -> N | y :: z -> K } arrow.b.double U$,
    100%,
  ),
)

#v(1em)


=== Implementation
\

When evaluating an AST node corresponding to a lazy list, the values of both `M` and `N` are only saved as other AST nodes, meaning they have yet to be evaluated.
Once they are, via the match construct, they become proper IValues and a flags flips to true in order to indicate that they indeed have been evaluated. All of this
information is saved in a VLCons IValue.

The structure of an ASTLCons node is, then, the following:
```java
public class ASTLCons implements ASTNode {
  ASTNode head, tail;

  public ASTLCons(ASTNode head, ASTNode tail) {
    this.head = head;
    this.tail = tail;
  }

  public IValue eval(Environment<IValue> e) throws InterpreterError {
    return new VLCons(this.head, this.tail, e);
  }
}
```

And the structure of VLCons is as follows:
```java
public class VLCons implements IValue {
  ASTNode head, tail;
  Environment<IValue> e;

  IValue evaluatedHead = null;
  IValue evaluatedTail = null;
  boolean headWasEvaluated = false;
  boolean tailWasEvaluated = false;

  public VLCons(ASTNode head, ASTNode tail, Environment<IValue> e) {
    this.head = head;
    this.tail = tail;
    this.e = e;
  }
  ...
}
```

The magic happens inside the match node, in the case where it matches a non nil list, because then, `M` and `N` are evaluated. Like so:

```java
// VLCons.java
...
public IValue getHead() throws InterpreterError {
  if (!this.headWasEvaluated) {
    this.evaluatedHead = this.head.eval(this.e);
    this.headWasEvaluated = true;
  }
  return this.evaluatedHead;
}

public IValue getTail() throws InterpreterError {
  if (!this.tailWasEvaluated) {
    this.evaluatedTail = this.tail.eval(this.e);
    this.tailWasEvaluated = true;
  }
  return this.evaluatedTail;
}
...
// ASTMatch.java
...
VLCons lc1 = (VLCons) v1;

IValue v2 = lc1.getHead();
IValue v3 = lc1.getTail();
Environment<IValue> en = new Environment<IValue>(e);
en.assoc(headName, v2);
en.assoc(tailName, v3);

return consCase.eval(en);
...
```
