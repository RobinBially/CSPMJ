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
'channel'('one','type'('dotTupleType'(['setExp'('int'(0),'int'(3)),'setExp'('int'(0),'int'(9))]))).
'channel'('two','type'('dotTupleType'(['setExp'('int'(0),'int'(9))]))).
'bindval'('Buff','prefix'('src_span'(8,8,8,10,2,2),['in'(_x)],'in','prefix'('src_span'(8,16,8,19,3,3),['out'(_x)],'out','val_of'('Buff','src_span'(8,25,8,29,4,4)),'src_span'(8,21,8,25,4,4)),'src_span'(8,12,8,16,4,4)),'src_span'(8,1,8,29,28,28)).
'bindval'('BuffInc','prefix'('src_span'(9,11,9,13,2,2),['in'(_x2)],'in','tupleExp'(['ifte'(_x2,'int'(9),'prefix'('src_span'(9,32,9,35,3,3),['out'(_x2,'int'(1))],'out','val_of'('BuffInc','src_span'(9,43,9,50,7,7)),'src_span'(9,39,9,43,4,4)),'prefix'('src_span'(9,56,9,59,3,3),['out'('int'(0))],'out','val_of'('BuffInc','src_span'(9,65,9,72,7,7)),'src_span'(9,61,9,65,4,4)),'src_span'(9,20,9,22,2,2),'src_span'(9,26,9,32,6,6),'src_span'(9,50,9,56,6,6))]),'src_span'(9,15,9,19,4,4)),'src_span'(9,1,9,73,72,72)).
'bindval'('BuffDouble','prefix'('src_span'(10,14,10,18,4,4),['in'(_x3)],'left','prefix'('src_span'(10,25,10,30,5,5),['out'('tupleExp'(['src_span'(10,32,10,35,3,3),[],_x3,'int'(2)]),'int'(10))],'right','val_of'('BuffDouble','src_span'(10,44,10,54,10,10)),'src_span'(10,40,10,44,4,4)),'src_span'(10,20,10,25,5,5)),'src_span'(10,1,10,54,53,53)).
'bindval'('BuffMul','prefix'('src_span'(11,11,11,14,3,3),['in'(_x4),'in'(_y)],'one','prefix'('src_span'(11,22,11,25,3,3),['out'('tupleExp'(['src_span'(11,27,11,30,3,3),[],_x4,_y]),'int'(10))],'two','val_of'('BuffMul','src_span'(11,38,11,45,7,7)),'src_span'(11,34,11,38,4,4)),'src_span'(11,18,11,22,4,4)),'src_span'(11,1,11,45,44,44)).
'bindval'('TEST1','lParallel'('linkList'(['link'('right','in')]),'val_of'('BuffDouble','src_span'(13,9,13,19,10,10)),'tupleExp'(['lParallel'('linkList'(['link'('out','left')]),'val_of'('BuffInc','src_span'(13,38,13,45,7,7)),'val_of'('BuffDouble','src_span'(13,63,13,73,10,10)),'src_span'(13,45,13,48,3,3))]),'src_span'(13,19,13,22,3,3)),'src_span'(13,1,13,74,73,73)).
'bindval'('TEST2','lParallel'('linkList'(['link'('out','dotTuple'(['one','int'(2)]))]),'val_of'('Buff','src_span'(14,9,14,13,4,4)),'val_of'('BuffMul','src_span'(14,30,14,37,7,7)),'src_span'(14,13,14,15,2,2)),'src_span'(14,1,14,37,36,36)).
'bindval'('TEST3','lParallel'('linkList'(['link'('right','in')]),'val_of'('BuffDouble','src_span'(15,9,15,19,10,10)),'tupleExp'(['lParallel'('linkList'(['link'('out','dotTuple'(['one','int'(2)]))]),'val_of'('BuffInc','src_span'(15,38,15,45,7,7)),'val_of'('BuffMul','src_span'(15,64,15,71,7,7)),'src_span'(15,45,15,48,3,3))]),'src_span'(15,19,15,22,3,3)),'src_span'(15,1,15,72,71,71)).
'bindval'('MAIN','val_of'('TEST3','src_span'(17,8,17,13,5,5)),'src_span'(17,1,17,13,12,12)).
'bindval'('PROB_TEST_TRACE','prefix'('src_span'(20,19,20,25,6,6),[],'dotTuple'(['left','int'(2)]),'prefix'('src_span'(20,29,20,34,5,5),[],'dotTuple'(['two','int'(0)]),'prefix'('src_span'(20,38,20,44,6,6),[],'dotTuple'(['left','int'(1)]),'prefix'('src_span'(20,48,20,53,5,5),[],'dotTuple'(['two','int'(6)]),'prefix'('src_span'(20,57,20,64,7,7),[],'dotTuple'(['one','int'(3),'int'(3)]),'prefix'('src_span'(20,68,20,73,5,5),[],'dotTuple'(['two','int'(9)]),'prefix'('src_span'(20,77,20,84,7,7),[],'dotTuple'(['one','int'(0),'int'(2)]),'prefix'('src_span'(20,88,20,94,6,6),[],'dotTuple'(['left','int'(2)]),'prefix'('src_span'(20,98,20,103,5,5),[],'dotTuple'(['two','int'(0)]),'prefix'('src_span'(20,107,20,114,7,7),[],'dotTuple'(['one','int'(0),'int'(2)]),'prefix'('src_span'(20,118,20,123,5,5),[],'dotTuple'(['two','int'(0)]),'prefix'('src_span'(20,127,20,132,5,5),[],'dotTuple'(['two','int'(0)]),'prefix'('src_span'(20,136,20,142,6,6),[],'dotTuple'(['left','int'(9)]),'prefix'('src_span'(20,146,20,152,6,6),[],'dotTuple'(['left','int'(1)]),'prefix'('src_span'(20,156,20,161,5,5),[],'dotTuple'(['two','int'(8)]),'prefix'('src_span'(20,165,20,170,5,5),[],'dotTuple'(['two','int'(6)]),'prefix'('src_span'(20,174,20,180,6,6),[],'dotTuple'(['left','int'(5)]),'prefix'('src_span'(20,184,20,191,7,7),[],'dotTuple'(['one','int'(3),'int'(4)]),'prefix'('src_span'(20,195,20,200,5,5),[],'dotTuple'(['two','int'(2)]),'prefix'('src_span'(20,204,20,211,7,7),[],'dotTuple'(['one','int'(0),'int'(3)]),'prefix'('src_span'(20,215,20,220,5,5),[],'dotTuple'(['two','int'(0)]),'prefix'('src_span'(20,224,20,229,5,5),[],'dotTuple'(['two','int'(2)]),'stop'('src_span'(20,233,20,237,4,4)),'src_span'(20,229,20,233,4,4)),'src_span'(20,220,20,224,4,4)),'src_span'(20,211,20,215,4,4)),'src_span'(20,200,20,204,4,4)),'src_span'(20,191,20,195,4,4)),'src_span'(20,180,20,184,4,4)),'src_span'(20,170,20,174,4,4)),'src_span'(20,161,20,165,4,4)),'src_span'(20,152,20,156,4,4)),'src_span'(20,142,20,146,4,4)),'src_span'(20,132,20,136,4,4)),'src_span'(20,123,20,127,4,4)),'src_span'(20,114,20,118,4,4)),'src_span'(20,103,20,107,4,4)),'src_span'(20,94,20,98,4,4)),'src_span'(20,84,20,88,4,4)),'src_span'(20,73,20,77,4,4)),'src_span'(20,64,20,68,4,4)),'src_span'(20,53,20,57,4,4)),'src_span'(20,44,20,48,4,4)),'src_span'(20,34,20,38,4,4)),'src_span'(20,25,20,29,4,4)),'src_span'(20,1,20,237,236,236)).
'val_of'('MAIN','src_span'(22,8,22,12,4,4)),'val_of'('PROB_TEST_TRACE','src_span'(22,17,22,32,15,15)).
'symbol'('TEST2','TEST2','src_span'(14,1,14,6,5,5),'Ident (Groundrep.)').
'symbol'('BuffDouble','BuffDouble','src_span'(10,1,10,11,10,10),'Ident (Groundrep.)').
'symbol'('TEST3','TEST3','src_span'(15,1,15,6,5,5),'Ident (Groundrep.)').
'symbol'('BuffInc','BuffInc','src_span'(9,1,9,8,7,7),'Ident (Groundrep.)').
'symbol'('in','in','src_span'(3,20,3,22,2,2),'Channel').
'symbol'('one','one','src_span'(5,9,5,12,3,3),'Channel').
'symbol'('right','right','src_span'(3,14,3,19,5,5),'Channel').
'symbol'('Buff','Buff','src_span'(8,1,8,5,4,4),'Ident (Groundrep.)').
'symbol'('two','two','src_span'(6,9,6,12,3,3),'Channel').
'symbol'('out','out','src_span'(3,23,3,26,3,3),'Channel').
'symbol'('PROB_TEST_TRACE','PROB_TEST_TRACE','src_span'(20,1,20,16,15,15),'Ident (Groundrep.)').
'symbol'('left','left','src_span'(3,9,3,13,4,4),'Channel').
'symbol'('x','x','src_span'(8,11,8,12,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(9,14,9,15,1,1),'Ident (Prolog Variable)').
'symbol'('x3','x','src_span'(10,19,10,20,1,1),'Ident (Prolog Variable)').
'symbol'('x4','x','src_span'(11,15,11,16,1,1),'Ident (Prolog Variable)').
'symbol'('y','y','src_span'(11,17,11,18,1,1),'Ident (Prolog Variable)').
'symbol'('MAIN','MAIN','src_span'(17,1,17,5,4,4),'Ident (Groundrep.)').
'symbol'('BuffMul','BuffMul','src_span'(11,1,11,8,7,7),'Ident (Groundrep.)').
'symbol'('TEST1','TEST1','src_span'(13,1,13,6,5,5),'Ident (Groundrep.)').

