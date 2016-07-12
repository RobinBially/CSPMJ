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
'channel'('out','type'('dotTupleType'(['setExp'('int'(0),'int'(9))]))).
'agent'('Gen','skip'('src_span'(5,11,5,15,4,4)),'src_span'(5,1,5,15,14,14)).
'agent'('Gen'('appendPattern'([_x,_s])),'prefix'('src_span'(7,14,7,17,3,3),['out'(_x)],'out','agent_call'('src_span'(7,23,7,29,6,6),'Gen',[_s]),'src_span'(7,19,7,23,4,4)),'src_span'(7,1,7,29,28,28)).
'bindval'('P','int'(9),['comprehensionGenerator'('int'(1),'int'(3),'int'(5))],'src_span'(9,1,9,23,22,22)).
'bindval'('MAIN',';'(';'(';'('agent_call'('src_span'(10,8,10,31,23,23),'Gen',['int'(2),['comprehensionGenerator'('int'(1),'int'(3),'int'(5))]]),'agent_call'('src_span'(10,34,10,40,6,6),'Gen',['val_of'('P','src_span'(10,38,10,39,1,1))]),'src_span_operator'('no_loc_info_available','src_span'(10,31,10,34,3,3))),'agent_call'('src_span'(10,42,10,78,36,36),'Gen',['tupleExp'(['lambda'([_a2,_b2,_a,_b],_a,_b)]),'int'(1),'int'(2),'int'(3),'int'(4)]),'src_span_operator'('no_loc_info_available','src_span'(10,40,10,42,2,2))),'agent_call'('src_span'(11,8,11,42,34,34),'Gen',['agent_call'('src_span'(11,12,11,41,29,29),'head',['^'('int'(1),'int'(2)),['comprehensionGenerator'('int'(3),'int'(5))]])]),'src_span_operator'('no_loc_info_available','src_span'(10,78,10,89,11,11))),'src_span'(10,1,11,42,41,41)).
'symbol'('P','P','src_span'(9,1,9,2,1,1),'Ident (Groundrep.)').
'symbol'('b2','b2','src_span'(10,52,10,54,2,2),'Ident (Prolog Variable)').
'symbol'('Gen','Gen','src_span'(5,1,5,4,3,3),'Function or Process').
'symbol'('a2','a2','src_span'(10,49,10,51,2,2),'Ident (Prolog Variable)').
'symbol'('a','a','src_span'(10,55,10,56,1,1),'Ident (Prolog Variable)').
'symbol'('b','b','src_span'(10,57,10,58,1,1),'Ident (Prolog Variable)').
'symbol'('s','s','src_span'(7,9,7,10,1,1),'Ident (Prolog Variable)').
'symbol'('x','x','src_span'(7,6,7,7,1,1),'Ident (Prolog Variable)').
'symbol'('MAIN','MAIN','src_span'(10,1,10,5,4,4),'Ident (Groundrep.)').
'symbol'('out','out','src_span'(3,9,3,12,3,3),'Channel').

