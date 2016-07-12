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
'bindval'('MyInt','int'(0),'int'(99),'src_span'(5,1,5,16,15,15)).
'channel'('gen','type'('dotTupleType'(['val_of'('MyInt','src_span'(6,13,6,18,5,5))]))).
'channel'('stop','type'('dotTupleType'(['val_of'('MyInt','src_span'(7,14,7,19,5,5))]))).
'bindval'('MAIN','/\'('agent_call'('src_span'(9,8,9,14,6,6),'GEN',['int'(0)]),'tupleExp'(['[>'('agent_call'('src_span'(9,19,9,26,7,7),'GEN',['int'(10)]),'agent_call'('src_span'(9,30,9,37,7,7),'GEN',['int'(20)]),'src_span_operator'('no_loc_info_available','src_span'(9,26,9,30,4,4)))]),'src_span_operator'('no_loc_info_available','src_span'(9,14,9,18,4,4))),'src_span'(9,1,9,38,37,37)).
'agent'('GEN'(_x),'[]'('tupleExp'(['&'(_x,'int'(99),'prefix'('src_span'(11,18,11,21,3,3),['out'(_x)],'gen','agent_call'('src_span'(11,27,11,35,8,8),'GEN',[_x,'int'(1)]),'src_span'(11,23,11,27,4,4)))]),'prefix'('src_span'(11,40,11,44,4,4),['out'(_x)],'stop','stop'('src_span'(11,50,11,54,4,4)),'src_span'(11,46,11,50,4,4)),'src_span_operator'('no_loc_info_available','src_span'(11,36,11,40,4,4))),'src_span'(11,1,11,54,53,53)).
'bindval'('PROB_TEST_TRACE','[>'('prefix'('src_span'(14,19,14,24,5,5),[],'dotTuple'(['gen','int'(0)]),'prefix'('src_span'(14,28,14,33,5,5),[],'dotTuple'(['gen','int'(1)]),'prefix'('src_span'(14,37,14,42,5,5),[],'dotTuple'(['gen','int'(2)]),'stop'('src_span'(14,46,14,50,4,4)),'src_span'(14,42,14,46,4,4)),'src_span'(14,33,14,37,4,4)),'src_span'(14,24,14,28,4,4)),'prefix'('src_span'(14,54,14,60,6,6),[],'dotTuple'(['gen','int'(20)]),'prefix'('src_span'(14,64,14,70,6,6),[],'dotTuple'(['gen','int'(21)]),'prefix'('src_span'(14,74,14,81,7,7),[],'dotTuple'(['stop','int'(22)]),'stop'('src_span'(14,85,14,89,4,4)),'src_span'(14,81,14,85,4,4)),'src_span'(14,70,14,74,4,4)),'src_span'(14,60,14,64,4,4)),'src_span_operator'('no_loc_info_available','src_span'(14,50,14,54,4,4))),'src_span'(14,1,14,89,88,88)).
'val_of'('MAIN','src_span'(16,8,16,12,4,4)),'val_of'('PROB_TEST_TRACE','src_span'(16,17,16,32,15,15)).
'symbol'('gen','gen','src_span'(6,9,6,12,3,3),'Channel').
'symbol'('GEN','GEN','src_span'(11,1,11,4,3,3),'Function or Process').
'symbol'('PROB_TEST_TRACE','PROB_TEST_TRACE','src_span'(14,1,14,16,15,15),'Ident (Groundrep.)').
'symbol'('stop','stop','src_span'(7,9,7,13,4,4),'Channel').
'symbol'('MyInt','MyInt','src_span'(5,1,5,6,5,5),'Ident (Groundrep.)').
'symbol'('x','x','src_span'(11,5,11,6,1,1),'Ident (Prolog Variable)').
'symbol'('MAIN','MAIN','src_span'(9,1,9,5,4,4),'Ident (Groundrep.)').

