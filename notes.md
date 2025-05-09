- Does it make sense that there are two different nodes for boolean values? One AstTrue and another AstFalse? Or can I just make an AstBool with a value inside it?
- Do we call beginScope on if and while nodes?
- Shouldn't we use the alt that we defined in the last
  building block of the .jj file?
- To implement functions as anonymous functions and single
  argument ones, just make it so that each function can
  only have one argument, and then deal with the fact that
  that can return another function.
- All functions have at least one argument. Functions
  with no arguments only produce side effects, which is
  something that our language doesn't have.
