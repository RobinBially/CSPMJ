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
'dataTypeDef'('tree',['constructorC'('Leaf','dotTupleType'(['setExp'('int'(0),'int'(99))])),'constructorC'('Node','dotTupleType'(['typeTuple'(['val_of'('tree','src_span'(3,38,3,42,4,4)),'val_of'('tree','src_span'(3,43,3,47,4,4))])]))]).
'channel'('out','type'('dotTupleType'(['val_of'('tree','src_span'(5,13,5,17,4,4))]))).
'bindval'('TEST','prefix'('src_span'(7,8,7,19,11,11),[],'dotTuple'(['out','Leaf','int'(22)]),'prefix'('src_span'(7,23,7,26,3,3),['out'('dotTuple'(['Node','tupleExp'(['src_span'(7,33,7,39,6,6),[],'dotTuple'(['Leaf','int'(2)]),'src_span'(7,40,7,46,6,6),[],'dotTuple'(['Leaf','int'(3)])])]))],'out','prefix'('src_span'(7,51,7,54,3,3),['in'(_x)],'out','prefix'('src_span'(7,60,7,63,3,3),['out'(_x)],'out','val_of'('TEST','src_span'(7,69,7,73,4,4)),'src_span'(7,65,7,69,4,4)),'src_span'(7,56,7,60,4,4)),'src_span'(7,47,7,51,4,4)),'src_span'(7,19,7,23,4,4)),'src_span'(7,1,7,73,72,72)).
'bindval'('TEST2','prefix'('src_span'(9,9,9,12,3,3),['in'(_x2)],'out','stop'('src_span'(9,18,9,22,4,4)),'src_span'(9,14,9,18,4,4)),'src_span'(9,1,9,22,21,21)).
'symbol'('TEST2','TEST2','src_span'(9,1,9,6,5,5),'Ident (Groundrep.)').
'symbol'('TEST','TEST','src_span'(7,1,7,5,4,4),'Ident (Groundrep.)').
'symbol'('Node','Node','src_span'(3,32,3,36,4,4),'Constructor of Datatype').
'symbol'('tree','tree','src_span'(3,10,3,14,4,4),'Datatype').
'symbol'('x','x','src_span'(7,55,7,56,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(9,13,9,14,1,1),'Ident (Prolog Variable)').
'symbol'('Leaf','Leaf','src_span'(3,17,3,21,4,4),'Constructor of Datatype').
'symbol'('out','out','src_span'(5,9,5,12,3,3),'Channel').

