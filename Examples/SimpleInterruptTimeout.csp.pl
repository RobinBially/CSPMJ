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
'channel'('gen','type'('dotTupleType'(['setExp'('int'(0),'int'(99))]))).
'channel'('stop','type'('dotTupleType'(['setExp'('int'(0),'int'(99))]))).
'bindval'('MAIN','/\'('agent_call'('src_span'(8,8,8,14,6,6),'GEN',['int'(0)]),'tupleExp'(['[>'('agent_call'('src_span'(8,19,8,26,7,7),'GEN',['int'(10)]),'agent_call'('src_span'(8,30,8,37,7,7),'GEN',['int'(20)]),'src_span_operator'('no_loc_info_available','src_span'(8,26,8,30,4,4)))]),'src_span_operator'('no_loc_info_available','src_span'(8,14,8,18,4,4))),'src_span'(8,1,8,38,37,37)).
'agent'('GEN'(_x),'[]'('tupleExp'(['&'(_x,'int'(99),'prefix'('src_span'(10,18,10,21,3,3),['out'(_x)],'gen','agent_call'('src_span'(10,27,10,35,8,8),'GEN',[_x,'int'(1)]),'src_span'(10,23,10,27,4,4)))]),'prefix'('src_span'(10,40,10,44,4,4),['out'(_x)],'stop','skip'('src_span'(10,50,10,54,4,4)),'src_span'(10,46,10,50,4,4)),'src_span_operator'('no_loc_info_available','src_span'(10,36,10,40,4,4))),'src_span'(10,1,10,54,53,53)).
'bindval'('PROB_TEST_TRACE','[>'('prefix'('src_span'(13,19,13,24,5,5),[],'dotTuple'(['gen','int'(0)]),'prefix'('src_span'(13,28,13,33,5,5),[],'dotTuple'(['gen','int'(1)]),'prefix'('src_span'(13,37,13,42,5,5),[],'dotTuple'(['gen','int'(2)]),'stop'('src_span'(13,46,13,50,4,4)),'src_span'(13,42,13,46,4,4)),'src_span'(13,33,13,37,4,4)),'src_span'(13,24,13,28,4,4)),'prefix'('src_span'(13,54,13,60,6,6),[],'dotTuple'(['gen','int'(20)]),'prefix'('src_span'(13,64,13,70,6,6),[],'dotTuple'(['gen','int'(21)]),'prefix'('src_span'(13,74,13,81,7,7),[],'dotTuple'(['stop','int'(22)]),'stop'('src_span'(13,85,13,89,4,4)),'src_span'(13,81,13,85,4,4)),'src_span'(13,70,13,74,4,4)),'src_span'(13,60,13,64,4,4)),'src_span_operator'('no_loc_info_available','src_span'(13,50,13,54,4,4))),'src_span'(13,1,13,89,88,88)).
'val_of'('MAIN','src_span'(15,8,15,12,4,4)),'val_of'('PROB_TEST_TRACE','src_span'(15,17,15,32,15,15)).
'bindval'('Tst','tupleExp'(['/\'('prefix'('src_span'(17,8,17,12,4,4),['out'('int'(0))],'stop','skip'('src_span'(17,18,17,22,4,4)),'src_span'(17,14,17,18,4,4)),'prefix'('src_span'(17,26,17,29,3,3),['out'('int'(0))],'gen','skip'('src_span'(17,35,17,39,4,4)),'src_span'(17,31,17,35,4,4)),'src_span_operator'('no_loc_info_available','src_span'(17,22,17,26,4,4)))]),'src_span'(17,1,17,40,39,39)).
'bindval'('Tst2','[]'('tupleExp'(['prefix'('src_span'(20,9,20,13,4,4),['out'('int'(0))],'stop','tupleExp'(['[]'('skip'('src_span'(20,20,20,24,4,4)),'prefix'('src_span'(20,28,20,31,3,3),['out'('int'(0))],'gen','skip'('src_span'(20,35,20,39,4,4)),'src_span'(20,33,20,35,2,2)),'src_span_operator'('no_loc_info_available','src_span'(20,24,20,28,4,4)))]),'src_span'(20,15,20,19,4,4))]),'prefix'('src_span'(20,45,20,48,3,3),['out'('int'(0))],'gen','skip'('src_span'(20,54,20,58,4,4)),'src_span'(20,50,20,54,4,4)),'src_span_operator'('no_loc_info_available','src_span'(20,41,20,45,4,4))),'src_span'(20,1,20,58,57,57)).
'agent'('S'(_x2),'tupleExp'(['&'(_x2,'int'(10),'prefix'('src_span'(24,18,24,21,3,3),['out'(_x2)],'gen','agent_call'('src_span'(24,27,24,33,6,6),'S',[_x2,'int'(1)]),'src_span'(24,23,24,27,4,4)))]),'src_span'(24,1,24,34,33,33)).
'bindval'('TestInterrupt1','/\'('agent_call'('src_span'(25,18,25,22,4,4),'S',['int'(0)]),'agent_call'('src_span'(25,26,25,30,4,4),'S',['int'(5)]),'src_span_operator'('no_loc_info_available','src_span'(25,22,25,26,4,4))),'src_span'(25,1,25,30,29,29)).
'bindval'('TestTimeout1','[>'('agent_call'('src_span'(27,16,27,20,4,4),'S',['int'(0)]),'agent_call'('src_span'(27,24,27,28,4,4),'S',['int'(5)]),'src_span_operator'('no_loc_info_available','src_span'(27,20,27,24,4,4))),'src_span'(27,1,27,28,27,27)).
'bindval'('TestTimeout2','[>'('agent_call'('src_span'(28,16,28,22,6,6),'GEN',['int'(0)]),'agent_call'('src_span'(28,26,28,33,7,7),'GEN',['int'(20)]),'src_span_operator'('no_loc_info_available','src_span'(28,22,28,26,4,4))),'src_span'(28,1,28,33,32,32)).
'symbol'('gen','gen','src_span'(5,9,5,12,3,3),'Channel').
'symbol'('GEN','GEN','src_span'(10,1,10,4,3,3),'Function or Process').
'symbol'('PROB_TEST_TRACE','PROB_TEST_TRACE','src_span'(13,1,13,16,15,15),'Ident (Groundrep.)').
'symbol'('TestInterrupt1','TestInterrupt1','src_span'(25,1,25,15,14,14),'Ident (Groundrep.)').
'symbol'('S','S','src_span'(24,1,24,2,1,1),'Function or Process').
'symbol'('stop','stop','src_span'(6,9,6,13,4,4),'Channel').
'symbol'('Tst','Tst','src_span'(17,1,17,4,3,3),'Ident (Groundrep.)').
'symbol'('x','x','src_span'(10,5,10,6,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(24,3,24,4,1,1),'Ident (Prolog Variable)').
'symbol'('MAIN','MAIN','src_span'(8,1,8,5,4,4),'Ident (Groundrep.)').
'symbol'('Tst2','Tst2','src_span'(20,1,20,5,4,4),'Ident (Groundrep.)').
'symbol'('TestTimeout2','TestTimeout2','src_span'(28,1,28,13,12,12),'Ident (Groundrep.)').
'symbol'('TestTimeout1','TestTimeout1','src_span'(27,1,27,13,12,12),'Ident (Groundrep.)').

