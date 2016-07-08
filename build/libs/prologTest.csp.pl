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
'dataTypeDef'('dt',['constructor'('cn'),'constructorC'('cn2','dotTupleType'(['boolType']))]).
'subTypeDef'('st',['constructor'('cn')]).
'nameType'('nt','type'('dotTupleType'(['boolType','intType']))).
'channel'('c1','type'('dotTupleType'(['boolType','intType']))).
'channel'('c2','type'('dotTupleType'(['boolType','intType']))).
'channel'('c3','type'('dotTupleType'(['boolType','intType']))).
'bindval'('A','stop'('src_span'(11,5,11,9,4,4)),'src_span'(11,1,11,9,8,8)).
'agent'('B'(_x2,_y2),'skip'('src_span'(12,10,12,14,4,4)),'src_span'(12,1,12,14,13,13)).
'bindval'('C','emptyMap','src_span'(13,1,13,10,9,9)).
'bindval'('D','mapExp'(['int'(1),'int'(2)]),'src_span'(14,1,14,13,12,12)).
'bindval'('E','char'('a'),'src_span'(15,1,15,8,7,7)).
'agent'('F'(_x2,_y2),'tupleExp'(['int'(1),'int'(2)]),'src_span'(16,1,16,15,14,14)).
'bindval'('G','agent_call'('src_span'(17,5,17,11,6,6),'F',['int'(1),'int'(2)]),'src_span'(17,1,17,11,10,10)).
'bindval'('H','events'('src_span'(18,5,18,11,6,6)),'src_span'(18,1,18,11,10,10)).
'bindval'('I','\'('int'(1),'int'(2),'src_span_operator'('no_loc_info_available','src_span'(19,6,19,9,3,3)),'src_span'(19,5,19,10,5,5)),'src_span'(19,1,19,10,9,9)).
'bindval'('J','|||'('int'(1),'int'(2),'src_span_operator'('no_loc_info_available','src_span'(20,6,20,11,5,5)),'src_span'(20,5,20,12,7,7)),'src_span'(20,1,20,12,11,11)).
'bindval'('K','val_of'('A','src_span'(21,5,21,6,1,1)),'src_span'(21,1,21,6,5,5)).
'comment'('lineComment'('-- This is an End-Of-File-Comment.','src_span'(22,1,22,35,34,34))).
'symbol'('st','st','src_span'(8,9,8,11,2,2),'Datatype').
'symbol'('A','A','src_span'(11,1,11,2,1,1),'Ident (Groundrep.)').
'symbol'('B','B','src_span'(12,1,12,2,1,1),'Function or Process').
'symbol'('Eol','Eol','src_span'(2,1,2,4,3,3),'Ident (Groundrep.)').
'symbol'('C','C','src_span'(13,1,13,2,1,1),'Ident (Groundrep.)').
'symbol'('D','D','src_span'(14,1,14,2,1,1),'Ident (Groundrep.)').
'symbol'('E','E','src_span'(15,1,15,2,1,1),'Ident (Groundrep.)').
'symbol'('cn2','cn2','src_span'(7,18,7,21,3,3),'Constructor of Datatype').
'symbol'('nt','nt','src_span'(9,10,9,12,2,2),'Nametype').
'symbol'('F','F','src_span'(16,1,16,2,1,1),'Function or Process').
'symbol'('G','G','src_span'(17,1,17,2,1,1),'Ident (Groundrep.)').
'symbol'('H','H','src_span'(18,1,18,2,1,1),'Ident (Groundrep.)').
'symbol'('I','I','src_span'(19,1,19,2,1,1),'Ident (Groundrep.)').
'symbol'('J','J','src_span'(20,1,20,2,1,1),'Ident (Groundrep.)').
'symbol'('cn','cn','src_span'(8,14,8,16,2,2),'Constructor of Datatype').
'symbol'('K','K','src_span'(21,1,21,2,1,1),'Ident (Groundrep.)').
'symbol'('c1','c1','src_span'(10,9,10,11,2,2),'Channel').
'symbol'('c2','c2','src_span'(10,12,10,14,2,2),'Channel').
'symbol'('dt','dt','src_span'(7,10,7,12,2,2),'Datatype').
'symbol'('c3','c3','src_span'(10,15,10,17,2,2),'Channel').
'symbol'('x','x','src_span'(12,3,12,4,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(16,3,16,4,1,1),'Ident (Prolog Variable)').
'symbol'('y','y','src_span'(12,5,12,6,1,1),'Ident (Prolog Variable)').
'symbol'('y2','y','src_span'(16,5,16,6,1,1),'Ident (Prolog Variable)').

