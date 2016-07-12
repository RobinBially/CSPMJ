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
'channel'('a','type'('dotUnitType')).
'channel'('b','type'('dotUnitType')).
'channel'('c','type'('dotUnitType')).
'channel'('d','type'('dotUnitType')).
'channel'('e','type'('dotUnitType')).
'channel'('f','type'('dotUnitType')).
'bindval'('MAIN','[]'('prefix'('src_span'(5,8,5,9,1,1),[],'e','val_of'('Test1','src_span'(5,13,5,18,5,5)),'src_span'(5,9,5,13,4,4)),';'('prefix'('src_span'(5,22,5,23,1,1),[],'f','val_of'('Test3','src_span'(5,27,5,32,5,5)),'src_span'(5,23,5,27,4,4)),'prefix'('src_span'(5,35,5,36,1,1),[],'e','val_of'('Test1','src_span'(5,40,5,45,5,5)),'src_span'(5,36,5,40,4,4)),'src_span_operator'('no_loc_info_available','src_span'(5,32,5,35,3,3))),'src_span_operator'('no_loc_info_available','src_span'(5,18,5,22,4,4))),'src_span'(5,1,5,45,44,44)).
'bindval'('Test1','aParallel'('tupleExp'(['prefix'('src_span'(7,10,7,11,1,1),[],'a','prefix'('src_span'(7,15,7,16,1,1),[],'b','prefix'('src_span'(7,20,7,21,1,1),[],'b','stop'('src_span'(7,25,7,29,4,4)),'src_span'(7,21,7,25,4,4)),'src_span'(7,16,7,20,4,4)),'src_span'(7,11,7,15,4,4))]),'a','b','b','c','tupleExp'(['prefix'('src_span'(7,51,7,52,1,1),[],'b','prefix'('src_span'(7,56,7,57,1,1),[],'c','prefix'('src_span'(7,61,7,62,1,1),[],'b','stop'('src_span'(7,66,7,70,4,4)),'src_span'(7,62,7,66,4,4)),'src_span'(7,57,7,61,4,4)),'src_span'(7,52,7,56,4,4))]),'src_span'(7,30,7,33,3,3)),'src_span'(7,1,7,71,70,70)).
'bindval'('Test2','aParallel'('tupleExp'(['prefix'('src_span'(10,10,10,11,1,1),[],'a','prefix'('src_span'(10,15,10,16,1,1),[],'d','prefix'('src_span'(10,20,10,21,1,1),[],'b','prefix'('src_span'(10,25,10,26,1,1),[],'b','stop'('src_span'(10,30,10,34,4,4)),'src_span'(10,26,10,30,4,4)),'src_span'(10,21,10,25,4,4)),'src_span'(10,16,10,20,4,4)),'src_span'(10,11,10,15,4,4))]),'a','b','b','c','tupleExp'(['prefix'('src_span'(10,56,10,57,1,1),[],'b','prefix'('src_span'(10,61,10,62,1,1),[],'c','prefix'('src_span'(10,66,10,67,1,1),[],'b','stop'('src_span'(10,71,10,75,4,4)),'src_span'(10,67,10,71,4,4)),'src_span'(10,62,10,66,4,4)),'src_span'(10,57,10,61,4,4))]),'src_span'(10,35,10,38,3,3)),'src_span'(10,1,10,76,75,75)).
'channel'('gen','type'('dotTupleType'(['setExp'('int'(0),'int'(99))]))).
'channel'('stop','type'('dotTupleType'(['setExp'('int'(0),'int'(99))]))).
'bindval'('Test3','aParallel'('agent_call'('src_span'(16,9,16,15,6,6),'GEN',['int'(0)]),'gen','dotTuple'(['stop','int'(4)]),'dotTuple'(['gen','int'(2)]),'dotTuple'(['gen','int'(3)]),'dotTuple'(['gen','int'(4)]),'dotTuple'(['stop','int'(2)]),'dotTuple'(['stop','int'(4)]),'agent_call'('src_span'(16,75,16,81,6,6),'GEN',['int'(2)]),'src_span'(16,15,16,18,3,3)),'src_span'(16,1,16,81,80,80)).
'agent'('GEN'(_x),'[]'('tupleExp'(['&'(_x,'int'(99),'prefix'('src_span'(19,18,19,21,3,3),['out'(_x)],'gen','agent_call'('src_span'(19,27,19,35,8,8),'GEN',[_x,'int'(1)]),'src_span'(19,23,19,27,4,4)))]),'prefix'('src_span'(19,40,19,44,4,4),['out'(_x)],'stop','skip'('src_span'(19,50,19,54,4,4)),'src_span'(19,46,19,50,4,4)),'src_span_operator'('no_loc_info_available','src_span'(19,36,19,40,4,4))),'src_span'(19,1,19,54,53,53)).
'bindval'('G2','agent_call'('src_span'(21,6,21,12,6,6),'GEN',['int'(2)]),'src_span'(21,1,21,12,11,11)).
'bindval'('Test4',';'('tupleExp'(['|||'('val_of'('G2','src_span'(23,10,23,12,2,2)),'val_of'('G2','src_span'(23,17,23,19,2,2)),'src_span_operator'('no_loc_info_available','src_span'(23,12,23,17,5,5)))]),'val_of'('G2','src_span'(23,23,23,25,2,2)),'src_span_operator'('no_loc_info_available','src_span'(23,20,23,23,3,3))),'src_span'(23,1,23,25,24,24)).
'bindval'('PROB_TEST_TRACE','prefix'('src_span'(26,19,26,20,1,1),[],'f','prefix'('src_span'(26,24,26,29,5,5),[],'dotTuple'(['gen','int'(0)]),'prefix'('src_span'(26,33,26,38,5,5),[],'dotTuple'(['gen','int'(1)]),'prefix'('src_span'(26,42,26,47,5,5),[],'dotTuple'(['gen','int'(2)]),'prefix'('src_span'(26,51,26,56,5,5),[],'dotTuple'(['gen','int'(3)]),'prefix'('src_span'(26,60,26,66,6,6),[],'dotTuple'(['stop','int'(4)]),'prefix'('src_span'(26,70,26,71,1,1),[],'e','prefix'('src_span'(26,75,26,76,1,1),[],'a','prefix'('src_span'(26,80,26,81,1,1),[],'b','prefix'('src_span'(26,85,26,86,1,1),[],'c','prefix'('src_span'(26,90,26,91,1,1),[],'b','stop'('src_span'(26,95,26,99,4,4)),'src_span'(26,91,26,95,4,4)),'src_span'(26,86,26,90,4,4)),'src_span'(26,81,26,85,4,4)),'src_span'(26,76,26,80,4,4)),'src_span'(26,71,26,75,4,4)),'src_span'(26,66,26,70,4,4)),'src_span'(26,56,26,60,4,4)),'src_span'(26,47,26,51,4,4)),'src_span'(26,38,26,42,4,4)),'src_span'(26,29,26,33,4,4)),'src_span'(26,20,26,24,4,4)),'src_span'(26,1,26,99,98,98)).
'val_of'('MAIN','src_span'(28,8,28,12,4,4)),'val_of'('PROB_TEST_TRACE','src_span'(28,17,28,32,15,15)).
'symbol'('a','a','src_span'(3,9,3,10,1,1),'Channel').
'symbol'('b','b','src_span'(3,11,3,12,1,1),'Channel').
'symbol'('c','c','src_span'(3,13,3,14,1,1),'Channel').
'symbol'('d','d','src_span'(3,15,3,16,1,1),'Channel').
'symbol'('e','e','src_span'(3,17,3,18,1,1),'Channel').
'symbol'('f','f','src_span'(3,19,3,20,1,1),'Channel').
'symbol'('G2','G2','src_span'(21,1,21,3,2,2),'Ident (Groundrep.)').
'symbol'('Test4','Test4','src_span'(23,1,23,6,5,5),'Ident (Groundrep.)').
'symbol'('Test3','Test3','src_span'(16,1,16,6,5,5),'Ident (Groundrep.)').
'symbol'('Test2','Test2','src_span'(10,1,10,6,5,5),'Ident (Groundrep.)').
'symbol'('Test1','Test1','src_span'(7,1,7,6,5,5),'Ident (Groundrep.)').
'symbol'('gen','gen','src_span'(14,9,14,12,3,3),'Channel').
'symbol'('GEN','GEN','src_span'(19,1,19,4,3,3),'Function or Process').
'symbol'('PROB_TEST_TRACE','PROB_TEST_TRACE','src_span'(26,1,26,16,15,15),'Ident (Groundrep.)').
'symbol'('stop','stop','src_span'(14,13,14,17,4,4),'Channel').
'symbol'('x','x','src_span'(19,5,19,6,1,1),'Ident (Prolog Variable)').
'symbol'('MAIN','MAIN','src_span'(5,1,5,5,4,4),'Ident (Groundrep.)').

