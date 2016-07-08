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
'comment'('lineComment'('-- This is a Line-Comment.','src_span'(1,1,1,27,26,26))).
'bindval'('Eol','int'(42),'src_span'(2,1,2,9,8,8)).
'comment'('blockComment'('{-\r\nAnd this is\r\na Multiline-Comment.\r\n-}','src_span'(3,1,3,42,41,41))).
'channel'('c','type'('dotTupleType'(['boolType','intType']))).
'channel'('d','type'('dotTupleType'(['boolType','intType']))).
'channel'('e','type'('dotTupleType'(['boolType','intType']))).
'bindval'('A','stop'('src_span'(8,5,8,9,4,4)),'src_span'(8,1,8,9,8,8)).
'agent'('B'(_x2,_y2),'skip'('src_span'(9,10,9,14,4,4)),'src_span'(9,1,9,14,13,13)).
'bindval'('C','emptyMap','src_span'(10,1,10,10,9,9)).
'bindval'('D','mapExp'(['int'(1),'int'(2)]),'src_span'(11,1,11,13,12,12)).
'bindval'('E','char'('a'),'src_span'(12,1,12,8,7,7)).
'agent'('F'(_x2,_y2),'tupleExp'(['int'(1),'int'(2)]),'src_span'(13,1,13,15,14,14)).
'bindval'('G','agent_call'('src_span'(14,5,14,11,6,6),'F',['int'(1),'int'(2)]),'src_span'(14,1,14,11,10,10)).
'bindval'('H','events'('src_span'(15,5,15,11,6,6)),'src_span'(15,1,15,11,10,10)).
'bindval'('I','\'('int'(1),'int'(2),'src_span_operator'('no_loc_info_available','src_span'(16,6,16,9,3,3)),'src_span'(16,5,16,10,5,5)),'src_span'(16,1,16,10,9,9)).
'bindval'('J','|||'('int'(1),'int'(2),'src_span_operator'('no_loc_info_available','src_span'(17,6,17,11,5,5)),'src_span'(17,5,17,12,7,7)),'src_span'(17,1,17,12,11,11)).
'bindval'('K','val_of'('A','src_span'(18,5,18,6,1,1)),'src_span'(18,1,18,6,5,5)).
'comment'('lineComment'('-- This is an End-Of-File-Comment.','src_span'(19,1,19,35,34,34))).
'symbol'('A','A','src_span'(8,1,8,2,1,1),'Ident (Groundrep.)').
'symbol'('B','B','src_span'(9,1,9,2,1,1),'Function or Process').
'symbol'('Eol','Eol','src_span'(2,1,2,4,3,3),'Ident (Groundrep.)').
'symbol'('c','c','src_span'(7,9,7,10,1,1),'Channel').
'symbol'('C','C','src_span'(10,1,10,2,1,1),'Ident (Groundrep.)').
'symbol'('d','d','src_span'(7,11,7,12,1,1),'Channel').
'symbol'('D','D','src_span'(11,1,11,2,1,1),'Ident (Groundrep.)').
'symbol'('e','e','src_span'(7,13,7,14,1,1),'Channel').
'symbol'('E','E','src_span'(12,1,12,2,1,1),'Ident (Groundrep.)').
'symbol'('F','F','src_span'(13,1,13,2,1,1),'Function or Process').
'symbol'('G','G','src_span'(14,1,14,2,1,1),'Ident (Groundrep.)').
'symbol'('H','H','src_span'(15,1,15,2,1,1),'Ident (Groundrep.)').
'symbol'('I','I','src_span'(16,1,16,2,1,1),'Ident (Groundrep.)').
'symbol'('J','J','src_span'(17,1,17,2,1,1),'Ident (Groundrep.)').
'symbol'('K','K','src_span'(18,1,18,2,1,1),'Ident (Groundrep.)').
'symbol'('x','x','src_span'(9,3,9,4,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(13,3,13,4,1,1),'Ident (Prolog Variable)').
'symbol'('y','y','src_span'(9,5,9,6,1,1),'Ident (Prolog Variable)').
'symbol'('y2','y','src_span'(13,5,13,6,1,1),'Ident (Prolog Variable)').

