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
'channel'('c','type'('dotTupleType'(['boolType']))).
'channel'('i','type'('dotTupleType'(['intType']))).
'bindval'('x1','false','true','true','src_span'(7,1,7,23,22,22)).
'agent'('P'(_ch),'skip'('src_span'(10,12,10,16,4,4)),'src_span'(10,1,10,16,15,15)).
'agent'('P'(_ch2,_x),'prefix'('src_span'(11,11,11,21,10,10),[],'dotTuple'([_ch2,'agent_call'('src_span'(11,14,11,21,7,7),'head',['src_span'(11,19,11,20,1,1),[],_x])]),'agent_call'('src_span'(11,25,11,38,13,13),'P',[_ch2,'agent_call'('src_span'(11,30,11,37,7,7),'tail',[_x])]),'src_span'(11,21,11,25,4,4)),'src_span'(11,1,11,38,37,37)).
'bindval'('P1','agent_call'('src_span'(13,6,13,13,7,7),'P',['c','val_of'('x1','src_span'(13,10,13,12,2,2))]),'src_span'(13,1,13,13,12,12)).
'bindval'('procs','agent_call'('src_span'(15,10,15,22,12,12),'P',['i','int'(1),'int'(2),'int'(3)]),'agent_call'('src_span'(15,23,15,30,7,7),'P',['c']),'tupleExp'(['prefix'('src_span'(15,33,15,39,6,6),[],'dotTuple'(['c','true']),'skip'('src_span'(15,43,15,47,4,4)),'src_span'(15,39,15,43,4,4))]),'src_span'(15,1,15,49,48,48)).
'agent'('Procs','stop'('src_span'(17,13,17,17,4,4)),'src_span'(17,1,17,17,16,16)).
'agent'('Procs'(_ps),';'('agent_call'('src_span'(18,13,18,21,8,8),'head',[_ps]),'agent_call'('src_span'(18,22,18,37,15,15),'Procs',['agent_call'('src_span'(18,28,18,36,8,8),'tail',[_ps])]),'src_span_operator'('no_loc_info_available','src_span'(18,21,18,22,1,1))),'src_span'(18,1,18,37,36,36)).
'bindval'('Procs1','agent_call'('src_span'(20,10,20,22,12,12),'Procs',['val_of'('procs','src_span'(20,16,20,21,5,5))]),'src_span'(20,1,20,22,21,21)).
'val_of'('Procs1','src_span'(22,8,22,14,6,6)),'prefix'('src_span'(22,19,22,22,3,3),[],'dotTuple'(['i','int'(1)]),'prefix'('src_span'(22,24,22,27,3,3),[],'dotTuple'(['i','int'(2)]),'prefix'('src_span'(22,29,22,32,3,3),[],'dotTuple'(['i','int'(3)]),'prefix'('src_span'(22,34,22,41,7,7),[],'dotTuple'(['c','false']),'stop'('src_span'(22,45,22,49,4,4)),'src_span'(22,41,22,45,4,4)),'src_span'(22,32,22,34,2,2)),'src_span'(22,27,22,29,2,2)),'src_span'(22,22,22,24,2,2)).
'symbol'('P','P','src_span'(10,1,10,2,1,1),'Function or Process').
'symbol'('P1','P1','src_span'(13,1,13,3,2,2),'Ident (Groundrep.)').
'symbol'('Procs','Procs','src_span'(17,1,17,6,5,5),'Function or Process').
'symbol'('procs','procs','src_span'(15,1,15,6,5,5),'Ident (Groundrep.)').
'symbol'('c','c','src_span'(3,9,3,10,1,1),'Channel').
'symbol'('ps','ps','src_span'(18,7,18,9,2,2),'Ident (Prolog Variable)').
'symbol'('ch','ch','src_span'(10,3,10,5,2,2),'Ident (Prolog Variable)').
'symbol'('ch2','ch','src_span'(11,3,11,5,2,2),'Ident (Prolog Variable)').
'symbol'('x','x','src_span'(11,6,11,7,1,1),'Ident (Prolog Variable)').
'symbol'('i','i','src_span'(4,9,4,10,1,1),'Channel').
'symbol'('x1','x1','src_span'(7,1,7,3,2,2),'Ident (Groundrep.)').
'symbol'('Procs1','Procs1','src_span'(20,1,20,7,6,6),'Ident (Groundrep.)').

