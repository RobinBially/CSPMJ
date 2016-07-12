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
'channel'('out','type'('dotTupleType'(['setExp'('int'(0),'int'(99))]))).
'agent'('Gen'(_ls),'ifte'(_ls,'skip'('src_span'(9,17,9,21,4,4)),'prefix'('src_span'(11,17,11,20,3,3),['out'('agent_call'('src_span'(11,21,11,29,8,8),'head',['src_span'(11,26,11,28,2,2),[],_ls]))],'out','agent_call'('src_span'(11,33,11,46,13,13),'Gen',['agent_call'('src_span'(11,37,11,45,8,8),'tail',[_ls])]),'src_span'(11,29,11,33,4,4)),'src_span'(8,11,8,13,2,2),'src_span'(8,20,8,43,23,23),'src_span'(9,21,9,57,36,36)),'src_span'(8,1,11,46,45,45)).
'channel'('status','type'('dotTupleType'(['setExp'('int'(0),'int'(2))]))).
'agent'('Test'(_ls2),'[]'('prefix'('src_span'(16,12,16,18,6,6),['out'('int'(2))],'status','skip'('src_span'(16,24,16,28,4,4)),'src_span'(16,20,16,24,4,4)),'prefix'('src_span'(17,12,17,15,3,3),['in'(_x)],'out','tupleExp'(['ifte'('agent_call'('src_span'(17,25,17,35,10,10),'elem',[_x,_ls2]),'prefix'('src_span'(17,41,17,47,6,6),['out'('int'(1))],'status','agent_call'('src_span'(17,51,17,59,8,8),'Test',[_ls2]),'src_span'(17,49,17,51,2,2)),'prefix'('src_span'(17,65,17,71,6,6),['out'('int'(0))],'status','agent_call'('src_span'(17,77,17,85,8,8),'Test',[_ls2]),'src_span'(17,73,17,77,4,4)),'src_span'(17,22,17,24,2,2),'src_span'(17,35,17,41,6,6),'src_span'(17,59,17,65,6,6))]),'src_span'(17,17,17,20,3,3)),'src_span_operator'('no_loc_info_available','src_span'(16,28,16,44,16,16))),'src_span'(16,1,17,86,85,85)).
'bindval'('TestElemInfSeq','sharing'('agent_call'('src_span'(19,18,19,29,11,11),'Test',['int'(3)]),'out','dotTuple'(['status','int'(2)]),'tupleExp'([';'('agent_call'('src_span'(19,54,19,65,11,11),'Gen',['int'(2),'int'(4)]),'prefix'('src_span'(19,66,19,72,6,6),['out'('int'(2))],'status','skip'('src_span'(19,76,19,80,4,4)),'src_span'(19,74,19,76,2,2)),'src_span_operator'('no_loc_info_available','src_span'(19,65,19,66,1,1)))]),'src_span'(19,29,19,33,4,4)),'src_span'(19,1,19,81,80,80)).
'bindval'('TestE','agent_call'('src_span'(21,9,21,20,11,11),'Test',['int'(3)]),'src_span'(21,1,21,20,19,19)).
'bindval'('MAIN',';'(';'(';'(';'('agent_call'('src_span'(23,8,23,18,10,10),'Gen',['int'(1),'int'(2)]),'agent_call'('src_span'(23,21,23,32,11,11),'Gen',['int'(8),'int'(9)]),'src_span_operator'('no_loc_info_available','src_span'(23,18,23,21,3,3))),'val_of'('TestElemInfSeq','src_span'(23,35,23,49,14,14)),'src_span_operator'('no_loc_info_available','src_span'(23,32,23,35,3,3))),'val_of'('TestE','src_span'(23,52,23,57,5,5)),'src_span_operator'('no_loc_info_available','src_span'(23,49,23,52,3,3))),'agent_call'('src_span'(23,60,23,71,11,11),'Gen',['int'(55)]),'src_span_operator'('no_loc_info_available','src_span'(23,57,23,60,3,3))),'src_span'(23,1,23,71,70,70)).
'symbol'('Gen','Gen','src_span'(8,1,8,4,3,3),'Function or Process').
'symbol'('TestElemInfSeq','TestElemInfSeq','src_span'(19,1,19,15,14,14),'Ident (Groundrep.)').
'symbol'('Test','Test','src_span'(16,1,16,5,4,4),'Function or Process').
'symbol'('ls','ls','src_span'(8,5,8,7,2,2),'Ident (Prolog Variable)').
'symbol'('ls2','ls','src_span'(16,6,16,8,2,2),'Ident (Prolog Variable)').
'symbol'('x','x','src_span'(17,16,17,17,1,1),'Ident (Prolog Variable)').
'symbol'('MAIN','MAIN','src_span'(23,1,23,5,4,4),'Ident (Groundrep.)').
'symbol'('TestE','TestE','src_span'(21,1,21,6,5,5),'Ident (Groundrep.)').
'symbol'('out','out','src_span'(6,9,6,12,3,3),'Channel').
'symbol'('status','status','src_span'(14,9,14,15,6,6),'Channel').

