# iLEX
# a DFA generator based on funtion-calls not RE-parse
  While working on my course design in principle of compiler class,I found a paradox that is when I am trying 
to get a DFA-generator based on RE-parse,I have to make a syntax-translator,but a syntax-translator cannot be
achieved because of no DFA to recognize its elements.But now,I get a good thought to avoid this kind of confusing
things,that is,build the DFA by funtion-parse not RE-parse,so we don't need a syntax-translator.So there is a 
prototype for showing my idea that contains some issues not fixed.
