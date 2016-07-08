:- dynamic parserVersionNum/1, parserVersionStr/1, parseResult/5.
:- dynamic module/4.
'parserVersionStr'('0.6.1.1').
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
'parserVersionNum'([0,11,0,1]).
'parserVersionStr'('CSPM-Frontent-0.11.0.1').
'channel'('c','type'('dotTupleType'(['boolType','intType']))).
'channel'('d','type'('dotTupleType'(['boolType','intType']))).
'channel'('e','type'('dotTupleType'(['boolType','intType']))).
'bindval'('A','stop'('src_span'(7,5,7,9,4,4)),'src_span'(7,1,7,9,8,8)).
'agent'('B'(_x2,_y2),'skip'('src_span'(8,10,8,14,4,4)),'src_span'(8,1,8,14,13,13)).
'bindval'('C','emptyMap','src_span'(9,1,9,10,9,9)).
'bindval'('D','mapExp'(['int'(1),'int'(2)]),'src_span'(10,1,10,13,12,12)).
'bindval'('E','char'('a'),'src_span'(11,1,11,8,7,7)).
'agent'('F'(_x2,_y2),'tupleExp'(['int'(1),'int'(2)]),'src_span'(12,1,12,15,14,14)).
'bindval'('G','agent_call'('src_span'(13,5,13,11,6,6),'F',['int'(1),'int'(2)]),'src_span'(13,1,13,11,10,10)).
'bindval'('H','events'('src_span'(14,5,14,11,6,6)),'src_span'(14,1,14,11,10,10)).
'bindval'('I','\'('int'(1),'int'(2),'src_span_operator'('no_loc_info_available','src_span'(15,6,15,9,3,3)),'src_span'(15,5,15,10,5,5)),'src_span'(15,1,15,10,9,9)).
'bindval'('J','|||'('int'(1),'int'(2),'src_span_operator'('no_loc_info_available','src_span'(16,6,16,11,5,5)),'src_span'(16,5,16,12,7,7)),'src_span'(16,1,16,12,11,11)).
'bindval'('K','val_of'('A','src_span'(17,5,17,6,1,1)),'src_span'(17,1,17,6,5,5)).
'symbol'('A','A','src_span'(7,1,7,2,1,1),'Ident (Groundrep.)').
'symbol'('B','B','src_span'(8,1,8,2,1,1),'Function or Process').
'symbol'('c','c','src_span'(6,9,6,10,1,1),'Channel').
'symbol'('C','C','src_span'(9,1,9,2,1,1),'Ident (Groundrep.)').
'symbol'('d','d','src_span'(6,11,6,12,1,1),'Channel').
'symbol'('D','D','src_span'(10,1,10,2,1,1),'Ident (Groundrep.)').
'symbol'('e','e','src_span'(6,13,6,14,1,1),'Channel').
'symbol'('E','E','src_span'(11,1,11,2,1,1),'Ident (Groundrep.)').
'symbol'('F','F','src_span'(12,1,12,2,1,1),'Function or Process').
'symbol'('G','G','src_span'(13,1,13,2,1,1),'Ident (Groundrep.)').
'symbol'('H','H','src_span'(14,1,14,2,1,1),'Ident (Groundrep.)').
'symbol'('I','I','src_span'(15,1,15,2,1,1),'Ident (Groundrep.)').
'symbol'('J','J','src_span'(16,1,16,2,1,1),'Ident (Groundrep.)').
'symbol'('K','K','src_span'(17,1,17,2,1,1),'Ident (Groundrep.)').
'symbol'('x','x','src_span'(8,3,8,4,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(12,3,12,4,1,1),'Ident (Prolog Variable)').
'symbol'('y','y','src_span'(8,5,8,6,1,1),'Ident (Prolog Variable)').
'symbol'('y2','y','src_span'(12,5,12,6,1,1),'Ident (Prolog Variable)').

