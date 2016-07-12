:- dynamic parserVersionNum/1, parserVersionStr/1, parseResult/5.
:- dynamic module/4.
'parserVersionStr'('CSPMJ V0.5').
'parseResult'('ok','',0,0,0).
:- dynamic channel/2, bindval/3, agent/3.
:- dynamic agent_curry/3, symbol/4.
:- dynamic dataTypeDef/2, subTypeDef/2, nameType/2.
:- dynamic cspTransparent/1.
:- dynamic cspPrint/1.
:- dynamic pragma/1.
:- dynamic comment/2.
:- dynamic assertBool/1, assertRef/5, assertTauPrio/6.
:- dynamic assertModelCheckExt/4, assertModelCheck/3.
:- dynamic assertLtl/4, assertCtl/4.
'parserVersionNum'([0,5]).
'parserVersionStr'('CSPMJ V0.5').
'bindval'('MAX','int'(2),'src_span'(2,1,2,8,7,7)).
'channel'('left','type'('dotTupleType'(['setExp'('int'(0),'val_of'('MAX','src_span'(4,31,4,34,3,3)))]))).
'channel'('right','type'('dotTupleType'(['setExp'('int'(0),'val_of'('MAX','src_span'(4,31,4,34,3,3)))]))).
'channel'('in','type'('dotTupleType'(['setExp'('int'(0),'val_of'('MAX','src_span'(4,31,4,34,3,3)))]))).
'channel'('out','type'('dotTupleType'(['setExp'('int'(0),'val_of'('MAX','src_span'(4,31,4,34,3,3)))]))).
'channel'('one','type'('dotTupleType'(['setExp'('int'(1),'int'(2)),'setExp'('int'(0),'val_of'('MAX','src_span'(6,24,6,27,3,3)))]))).
'channel'('two','type'('dotTupleType'(['setExp'('int'(0),'val_of'('MAX','src_span'(7,17,7,20,3,3)))]))).
'bindval'('Buff','prefix'('src_span'(9,8,9,10,2,2),['in'(_x)],'in','prefix'('src_span'(9,16,9,19,3,3),['out'(_x)],'out','val_of'('Buff','src_span'(9,25,9,29,4,4)),'src_span'(9,21,9,25,4,4)),'src_span'(9,12,9,16,4,4)),'src_span'(9,1,9,29,28,28)).
'bindval'('BuffInc','prefix'('src_span'(10,11,10,13,2,2),['in'(_x2)],'in','tupleExp'(['ifte'(_x2,'val_of'('MAX','src_span'(10,25,10,28,3,3)),'prefix'('src_span'(10,34,10,37,3,3),['out'(_x2,'int'(1))],'out','val_of'('BuffInc','src_span'(10,45,10,52,7,7)),'src_span'(10,41,10,45,4,4)),'prefix'('src_span'(10,58,10,61,3,3),['out'('int'(0))],'out','val_of'('BuffInc','src_span'(10,67,10,74,7,7)),'src_span'(10,63,10,67,4,4)),'src_span'(10,20,10,22,2,2),'src_span'(10,28,10,34,6,6),'src_span'(10,52,10,58,6,6))]),'src_span'(10,15,10,18,3,3)),'src_span'(10,1,10,75,74,74)).
'bindval'('BuffDouble','prefix'('src_span'(11,14,11,18,4,4),['in'(_x3)],'left','prefix'('src_span'(11,25,11,30,5,5),['out'('tupleExp'(['src_span'(11,32,11,35,3,3),[],_x3,'int'(2)]),'tupleExp'(['src_span'(11,39,11,44,5,5),[],'val_of'('MAX','src_span'(11,39,11,42,3,3)),'int'(1)]))],'right','val_of'('BuffDouble','src_span'(11,49,11,59,10,10)),'src_span'(11,45,11,49,4,4)),'src_span'(11,20,11,25,5,5)),'src_span'(11,1,11,59,58,58)).
'bindval'('BuffMul','prefix'('src_span'(12,11,12,14,3,3),['in'(_x4),'in'(_y)],'one','prefix'('src_span'(12,22,12,25,3,3),['out'('tupleExp'(['src_span'(12,27,12,30,3,3),[],_x4,_y]),'tupleExp'(['src_span'(12,33,12,38,5,5),[],'val_of'('MAX','src_span'(12,33,12,36,3,3)),'int'(1)]))],'two','val_of'('BuffMul','src_span'(12,43,12,50,7,7)),'src_span'(12,39,12,43,4,4)),'src_span'(12,18,12,22,4,4)),'src_span'(12,1,12,50,49,49)).
'bindval'('MAIN','lParallel'('linkList'(['link'('right','in')]),'val_of'('BuffDouble','src_span'(14,8,14,18,10,10)),'tupleExp'(['lParallel'('linkList'(['link'('out','dotTuple'(['one','int'(2)]))]),'val_of'('BuffInc','src_span'(14,37,14,44,7,7)),'val_of'('BuffMul','src_span'(14,63,14,70,7,7)),'src_span'(14,44,14,47,3,3))]),'src_span'(14,18,14,21,3,3)),'src_span'(14,1,14,71,70,70)).
'symbol'('BuffDouble','BuffDouble','src_span'(11,1,11,11,10,10),'Ident (Groundrep.)').
'symbol'('BuffInc','BuffInc','src_span'(10,1,10,8,7,7),'Ident (Groundrep.)').
'symbol'('MAX','MAX','src_span'(2,1,2,4,3,3),'Ident (Groundrep.)').
'symbol'('in','in','src_span'(4,20,4,22,2,2),'Channel').
'symbol'('one','one','src_span'(6,9,6,12,3,3),'Channel').
'symbol'('right','right','src_span'(4,14,4,19,5,5),'Channel').
'symbol'('Buff','Buff','src_span'(9,1,9,5,4,4),'Ident (Groundrep.)').
'symbol'('two','two','src_span'(7,9,7,12,3,3),'Channel').
'symbol'('out','out','src_span'(4,23,4,26,3,3),'Channel').
'symbol'('left','left','src_span'(4,9,4,13,4,4),'Channel').
'symbol'('x','x','src_span'(9,11,9,12,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(10,14,10,15,1,1),'Ident (Prolog Variable)').
'symbol'('x3','x','src_span'(11,19,11,20,1,1),'Ident (Prolog Variable)').
'symbol'('x4','x','src_span'(12,15,12,16,1,1),'Ident (Prolog Variable)').
'symbol'('y','y','src_span'(12,17,12,18,1,1),'Ident (Prolog Variable)').
'symbol'('MAIN','MAIN','src_span'(14,1,14,5,4,4),'Ident (Groundrep.)').
'symbol'('BuffMul','BuffMul','src_span'(12,1,12,8,7,7),'Ident (Groundrep.)').

