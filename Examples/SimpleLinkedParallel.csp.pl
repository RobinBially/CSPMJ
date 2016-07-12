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
'channel'('left','type'('dotTupleType'(['setExp'('int'(0),'int'(9))]))).
'channel'('right','type'('dotTupleType'(['setExp'('int'(0),'int'(9))]))).
'channel'('in','type'('dotTupleType'(['setExp'('int'(0),'int'(9))]))).
'channel'('out','type'('dotTupleType'(['setExp'('int'(0),'int'(9))]))).
'bindval'('Buff','prefix'('src_span'(6,8,6,10,2,2),['in'(_x)],'in','prefix'('src_span'(6,16,6,19,3,3),['out'(_x)],'out','val_of'('Buff','src_span'(6,25,6,29,4,4)),'src_span'(6,21,6,25,4,4)),'src_span'(6,12,6,16,4,4)),'src_span'(6,1,6,29,28,28)).
'bindval'('BuffInc','prefix'('src_span'(7,11,7,13,2,2),['in'(_x2)],'in','tupleExp'(['ifte'(_x2,'int'(9),'prefix'('src_span'(7,32,7,35,3,3),['out'(_x2,'int'(1))],'out','val_of'('BuffInc','src_span'(7,43,7,50,7,7)),'src_span'(7,39,7,43,4,4)),'prefix'('src_span'(7,56,7,59,3,3),['out'('int'(0))],'out','val_of'('BuffInc','src_span'(7,65,7,72,7,7)),'src_span'(7,61,7,65,4,4)),'src_span'(7,20,7,22,2,2),'src_span'(7,26,7,32,6,6),'src_span'(7,50,7,56,6,6))]),'src_span'(7,15,7,19,4,4)),'src_span'(7,1,7,73,72,72)).
'bindval'('MAIN','lParallel'('linkList'(['link'('out','in')]),'val_of'('BuffInc','src_span'(9,8,9,15,7,7)),'tupleExp'(['lParallel'('linkList'(['link'('out','in')]),'val_of'('BuffInc2','src_span'(9,32,9,40,8,8)),'val_of'('BuffInc3','src_span'(9,56,9,64,8,8)),'src_span'(9,40,9,43,3,3))]),'src_span'(9,15,9,18,3,3)),'src_span'(9,1,9,65,64,64)).
'bindval'('TEST1','sharing'('val_of'('BuffInc','src_span'(11,9,11,16,7,7)),'out','val_of'('BuffInc','src_span'(11,33,11,40,7,7)),'src_span'(11,16,11,20,4,4)),'src_span'(11,1,11,40,39,39)).
'bindval'('TEST1b','sharing'('val_of'('Buff','src_span'(12,10,12,14,4,4)),'out','val_of'('BuffInc','src_span'(12,31,12,38,7,7)),'src_span'(12,14,12,18,4,4)),'src_span'(12,1,12,38,37,37)).
'bindval'('TESTR','val_of'('BuffInc','src_span'(14,9,14,16,7,7)),'right','in','src_span'(14,1,14,34,33,33)).
'bindval'('TESTR2','val_of'('BuffInc','src_span'(16,10,16,17,7,7)),'out','in','src_span'(16,1,16,33,32,32)).
'bindval'('TEST4','lParallel'('linkList'(['link'('out','in')]),'val_of'('BuffInc','src_span'(18,9,18,16,7,7)),'val_of'('Buff','src_span'(18,32,18,36,4,4)),'src_span'(18,16,18,19,3,3)),'src_span'(18,1,18,36,35,35)).
'bindval'('PROB_TEST_TRACE','prefix'('src_span'(21,19,21,23,4,4),[],'dotTuple'(['in','int'(4)]),'prefix'('src_span'(21,27,21,31,4,4),[],'dotTuple'(['in','int'(1)]),'prefix'('src_span'(21,35,21,40,5,5),[],'dotTuple'(['out','int'(7)]),'prefix'('src_span'(21,44,21,48,4,4),[],'dotTuple'(['in','int'(7)]),'prefix'('src_span'(21,52,21,57,5,5),[],'dotTuple'(['out','int'(4)]),'prefix'('src_span'(21,61,21,65,4,4),[],'dotTuple'(['in','int'(9)]),'prefix'('src_span'(21,69,21,74,5,5),[],'dotTuple'(['out','int'(0)]),'prefix'('src_span'(21,78,21,83,5,5),[],'dotTuple'(['out','int'(2)]),'stop'('src_span'(21,87,21,91,4,4)),'src_span'(21,83,21,87,4,4)),'src_span'(21,74,21,78,4,4)),'src_span'(21,65,21,69,4,4)),'src_span'(21,57,21,61,4,4)),'src_span'(21,48,21,52,4,4)),'src_span'(21,40,21,44,4,4)),'src_span'(21,31,21,35,4,4)),'src_span'(21,23,21,27,4,4)),'src_span'(21,1,21,91,90,90)).
'bindval'('BuffInc2','prefix'('src_span'(26,12,26,14,2,2),['in'(_x3)],'in','tupleExp'(['ifte'(_x3,'int'(9),'prefix'('src_span'(26,33,26,36,3,3),['out'(_x3,'int'(1))],'out','val_of'('BuffInc2','src_span'(26,44,26,52,8,8)),'src_span'(26,40,26,44,4,4)),'prefix'('src_span'(26,58,26,61,3,3),['out'('int'(0))],'out','val_of'('BuffInc2','src_span'(26,67,26,75,8,8)),'src_span'(26,63,26,67,4,4)),'src_span'(26,21,26,23,2,2),'src_span'(26,27,26,33,6,6),'src_span'(26,52,26,58,6,6))]),'src_span'(26,16,26,20,4,4)),'src_span'(26,1,26,76,75,75)).
'bindval'('BuffInc3','prefix'('src_span'(27,12,27,14,2,2),['in'(_x4)],'in','tupleExp'(['ifte'(_x4,'int'(9),'prefix'('src_span'(27,33,27,36,3,3),['out'(_x4,'int'(1))],'out','val_of'('BuffInc3','src_span'(27,44,27,52,8,8)),'src_span'(27,40,27,44,4,4)),'prefix'('src_span'(27,58,27,61,3,3),['out'('int'(0))],'out','val_of'('BuffInc3','src_span'(27,67,27,75,8,8)),'src_span'(27,63,27,67,4,4)),'src_span'(27,21,27,23,2,2),'src_span'(27,27,27,33,6,6),'src_span'(27,52,27,58,6,6))]),'src_span'(27,16,27,20,4,4)),'src_span'(27,1,27,76,75,75)).
'bindval'('MAIN2','tupleExp'(['lParallel'('linkList'(['link'('out','in')]),'val_of'('BuffInc','src_span'(28,10,28,17,7,7)),'val_of'('BuffInc2','src_span'(28,33,28,41,8,8)),'src_span'(28,17,28,20,3,3))]),'src_span'(28,1,28,42,41,41)).
'symbol'('TESTR','TESTR','src_span'(14,1,14,6,5,5),'Ident (Groundrep.)').
'symbol'('TEST4','TEST4','src_span'(18,1,18,6,5,5),'Ident (Groundrep.)').
'symbol'('MAIN2','MAIN2','src_span'(28,1,28,6,5,5),'Ident (Groundrep.)').
'symbol'('BuffInc','BuffInc','src_span'(7,1,7,8,7,7),'Ident (Groundrep.)').
'symbol'('in','in','src_span'(3,20,3,22,2,2),'Channel').
'symbol'('TEST1b','TEST1b','src_span'(12,1,12,7,6,6),'Ident (Groundrep.)').
'symbol'('right','right','src_span'(3,14,3,19,5,5),'Channel').
'symbol'('Buff','Buff','src_span'(6,1,6,5,4,4),'Ident (Groundrep.)').
'symbol'('out','out','src_span'(3,23,3,26,3,3),'Channel').
'symbol'('PROB_TEST_TRACE','PROB_TEST_TRACE','src_span'(21,1,21,16,15,15),'Ident (Groundrep.)').
'symbol'('BuffInc2','BuffInc2','src_span'(26,1,26,9,8,8),'Ident (Groundrep.)').
'symbol'('left','left','src_span'(3,9,3,13,4,4),'Channel').
'symbol'('x','x','src_span'(6,11,6,12,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(7,14,7,15,1,1),'Ident (Prolog Variable)').
'symbol'('x3','x','src_span'(26,15,26,16,1,1),'Ident (Prolog Variable)').
'symbol'('x4','x','src_span'(27,15,27,16,1,1),'Ident (Prolog Variable)').
'symbol'('TESTR2','TESTR2','src_span'(16,1,16,7,6,6),'Ident (Groundrep.)').
'symbol'('MAIN','MAIN','src_span'(9,1,9,5,4,4),'Ident (Groundrep.)').
'symbol'('BuffInc3','BuffInc3','src_span'(27,1,27,9,8,8),'Ident (Groundrep.)').
'symbol'('TEST1','TEST1','src_span'(11,1,11,6,5,5),'Ident (Groundrep.)').

