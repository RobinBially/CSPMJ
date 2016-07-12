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
'nameType'('Val1','type'('dotTupleType'(['setExp'('int'(1),'int'(3))]))).
'nameType'('Val2','type'('dotTupleType'(['setExp'('int'(8),'int'(9))]))).
'nameType'('Val3','type'('dotTupleType'(['setExp'('int'(0),'int'(1))]))).
'dataTypeDef'('Record',['constructorC'('pair','dotTupleType'(['val_of'('Val1','src_span'(6,24,6,28,4,4)),'val_of'('Val2','src_span'(6,29,6,33,4,4))])),'constructorC'('triple','dotTupleType'(['val_of'('Val1','src_span'(6,43,6,47,4,4)),'val_of'('Val2','src_span'(6,48,6,52,4,4)),'val_of'('Val3','src_span'(6,53,6,57,4,4))]))]).
'channel'('r','type'('dotTupleType'(['val_of'('Record','src_span'(8,13,8,19,6,6))]))).
'bindval'('MAIN','prefix'('src_span'(10,8,10,14,6,6),['in'(_v1),'in'(_v2)],'dotTuple'(['r','pair']),'prefix'('src_span'(10,24,10,32,8,8),['out'(_v1),'out'(_v2),'in'(_v3)],'dotTuple'(['r','triple']),'val_of'('MAIN2','src_span'(10,45,10,50,5,5)),'src_span'(10,41,10,45,4,4)),'src_span'(10,20,10,24,4,4)),'src_span'(10,1,10,50,49,49)).
'nameType'('Pair1','type'('dotTupleType'(['setExp'('int'(1),'int'(2)),'setExp'('int'(1),'int'(3))]))).
'channel'('p','type'('dotTupleType'(['val_of'('Pair1','src_span'(17,12,17,17,5,5))]))).
'bindval'('MAIN2','prefix'('src_span'(19,9,19,10,1,1),['in'(_x)],'p','prefix'('src_span'(20,6,20,7,1,1),['in'(_v12),'in'(_v22)],'p','prefix'('src_span'(21,6,21,7,1,1),['out'(_v12),'out'(_v12)],'p','prefix'('src_span'(22,6,22,7,1,1),['out'('int'(2)),'out'('int'(3))],'p','val_of'('MAIN3','src_span'(23,6,23,11,5,5)),'src_span'(22,11,22,45,34,34)),'src_span'(21,13,21,21,8,8)),'src_span'(20,13,20,80,67,67)),'src_span'(19,12,19,20,8,8)),'src_span'(19,1,23,11,10,10)).
'nameType'('Pair2','type'('dotTupleType'(['val_of'('Val1','src_span'(26,18,26,22,4,4)),'val_of'('Val2','src_span'(26,23,26,27,4,4))]))).
'channel'('p2','type'('dotTupleType'(['val_of'('Pair2','src_span'(28,13,28,18,5,5))]))).
'bindval'('MAIN3','prefix'('src_span'(30,9,30,11,2,2),['in'(_x2)],'p2','prefix'('src_span'(33,8,33,10,2,2),['out'(_x2)],'p2','val_of'('MAIN','src_span'(34,6,34,10,4,4)),'src_span'(33,12,33,19,7,7)),'src_span'(30,13,30,146,133,133)),'src_span'(30,1,34,10,9,9)).
'symbol'('MAIN3','MAIN3','src_span'(30,1,30,6,5,5),'Ident (Groundrep.)').
'symbol'('Pair1','Pair1','src_span'(15,10,15,15,5,5),'Nametype').
'symbol'('MAIN2','MAIN2','src_span'(19,1,19,6,5,5),'Ident (Groundrep.)').
'symbol'('p2','p2','src_span'(28,9,28,11,2,2),'Channel').
'symbol'('Pair2','Pair2','src_span'(26,10,26,15,5,5),'Nametype').
'symbol'('pair','pair','src_span'(6,19,6,23,4,4),'Constructor of Datatype').
'symbol'('p','p','src_span'(17,9,17,10,1,1),'Channel').
'symbol'('r','r','src_span'(8,9,8,10,1,1),'Channel').
'symbol'('triple','triple','src_span'(6,36,6,42,6,6),'Constructor of Datatype').
'symbol'('Val1','Val1','src_span'(1,10,1,14,4,4),'Nametype').
'symbol'('x','x','src_span'(19,11,19,12,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(30,12,30,13,1,1),'Ident (Prolog Variable)').
'symbol'('Val2','Val2','src_span'(2,10,2,14,4,4),'Nametype').
'symbol'('Val3','Val3','src_span'(3,10,3,14,4,4),'Nametype').
'symbol'('Record','Record','src_span'(6,10,6,16,6,6),'Datatype').
'symbol'('MAIN','MAIN','src_span'(10,1,10,5,4,4),'Ident (Groundrep.)').
'symbol'('v1','v1','src_span'(10,15,10,17,2,2),'Ident (Prolog Variable)').
'symbol'('v12','v1','src_span'(20,8,20,10,2,2),'Ident (Prolog Variable)').
'symbol'('v2','v2','src_span'(10,18,10,20,2,2),'Ident (Prolog Variable)').
'symbol'('v22','v2','src_span'(20,11,20,13,2,2),'Ident (Prolog Variable)').
'symbol'('v3','v3','src_span'(10,39,10,41,2,2),'Ident (Prolog Variable)').

