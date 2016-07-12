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
'dataTypeDef'('BSet',['constructor'('code'),'constructor'('names')]).
'dataTypeDef'('Bcode',['constructor'('c1'),'constructor'('c2'),'constructor'('c3')]).
'dataTypeDef'('Bnames',['constructor'('n1'),'constructor'('n2')]).
'agent'('B'(_code2),'Bcode','src_span'(14,1,14,16,15,15)).
'agent'('B'(_names2),'Bnames','src_span'(15,1,15,18,17,17)).
'channel'('get','type'('dotTupleType'(['code','names']))).
'bindval'('Test','prefix'('src_span'(19,8,19,11,3,3),['in'(_x),'in'(_z)],'get','stop'('src_span'(19,19,19,23,4,4)),'src_span'(19,15,19,19,4,4)),'src_span'(19,1,19,23,22,22)).
'dataTypeDef'('Code',['constructor'('B1')]).
'dataTypeDef'('Names',['constructor'('B2')]).
'channel'('get2','type'('dotTupleType'(['val_of'('Code','src_span'(28,15,28,19,4,4)),'val_of'('Names','src_span'(28,20,28,25,5,5))]))).
'bindval'('Test2','prefix'('src_span'(30,9,30,13,4,4),['in'(_x2),'in'(_z2)],'get2','stop'('src_span'(30,21,30,25,4,4)),'src_span'(30,17,30,21,4,4)),'src_span'(30,1,30,25,24,24)).
'dataTypeDef'('Code3',['constructor'('c31'),'constructor'('c32')]).
'dataTypeDef'('Names3',['constructor'('n31'),'constructor'('n32'),'constructor'('n33')]).
'channel'('get3','type'('dotTupleType'(['val_of'('Code3','src_span'(43,15,43,20,5,5)),'val_of'('Names3','src_span'(43,21,43,27,6,6))]))).
'bindval'('Test3','prefix'('src_span'(45,9,45,13,4,4),['in'(_x3),'in'(_z3)],'get3','stop'('src_span'(45,21,45,25,4,4)),'src_span'(45,17,45,21,4,4)),'src_span'(45,1,45,25,24,24)).
'symbol'('c31','c31','src_span'(38,18,38,21,3,3),'Constructor of Datatype').
'symbol'('B','B','src_span'(14,1,14,2,1,1),'Function or Process').
'symbol'('code','code','src_span'(11,17,11,21,4,4),'Constructor of Datatype').
'symbol'('code2','code','src_span'(14,3,14,7,4,4),'Ident (Prolog Variable)').
'symbol'('n1','n1','src_span'(13,19,13,21,2,2),'Constructor of Datatype').
'symbol'('c32','c32','src_span'(38,24,38,27,3,3),'Constructor of Datatype').
'symbol'('n2','n2','src_span'(13,22,13,24,2,2),'Constructor of Datatype').
'symbol'('n32','n32','src_span'(40,25,40,28,3,3),'Constructor of Datatype').
'symbol'('n31','n31','src_span'(40,19,40,22,3,3),'Constructor of Datatype').
'symbol'('Test3','Test3','src_span'(45,1,45,6,5,5),'Ident (Groundrep.)').
'symbol'('Test2','Test2','src_span'(30,1,30,6,5,5),'Ident (Groundrep.)').
'symbol'('n33','n33','src_span'(40,31,40,34,3,3),'Constructor of Datatype').
'symbol'('Names','Names','src_span'(26,10,26,15,5,5),'Datatype').
'symbol'('Test','Test','src_span'(19,1,19,5,4,4),'Ident (Groundrep.)').
'symbol'('get','get','src_span'(17,10,17,13,3,3),'Channel').
'symbol'('Bnames','Bnames','src_span'(13,10,13,16,6,6),'Datatype').
'symbol'('BSet','BSet','src_span'(11,10,11,14,4,4),'Datatype').
'symbol'('Code3','Code3','src_span'(38,10,38,15,5,5),'Datatype').
'symbol'('Names3','Names3','src_span'(40,10,40,16,6,6),'Datatype').
'symbol'('get2','get2','src_span'(28,9,28,13,4,4),'Channel').
'symbol'('Code','Code','src_span'(25,10,25,14,4,4),'Datatype').
'symbol'('get3','get3','src_span'(43,9,43,13,4,4),'Channel').
'symbol'('c1','c1','src_span'(12,18,12,20,2,2),'Constructor of Datatype').
'symbol'('c2','c2','src_span'(12,21,12,23,2,2),'Constructor of Datatype').
'symbol'('B1','B1','src_span'(25,17,25,19,2,2),'Constructor of Datatype').
'symbol'('c3','c3','src_span'(12,24,12,26,2,2),'Constructor of Datatype').
'symbol'('B2','B2','src_span'(26,18,26,20,2,2),'Constructor of Datatype').
'symbol'('names','names','src_span'(11,24,11,29,5,5),'Constructor of Datatype').
'symbol'('names2','names','src_span'(15,3,15,8,5,5),'Ident (Prolog Variable)').
'symbol'('x','x','src_span'(19,12,19,13,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(30,14,30,15,1,1),'Ident (Prolog Variable)').
'symbol'('x3','x','src_span'(45,14,45,15,1,1),'Ident (Prolog Variable)').
'symbol'('z','z','src_span'(19,14,19,15,1,1),'Ident (Prolog Variable)').
'symbol'('z2','z','src_span'(30,16,30,17,1,1),'Ident (Prolog Variable)').
'symbol'('z3','z','src_span'(45,16,45,17,1,1),'Ident (Prolog Variable)').
'symbol'('Bcode','Bcode','src_span'(12,10,12,15,5,5),'Datatype').

