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
'channel'('gen','type'('dotTupleType'(['intType']))).
'channel'('stop','type'('dotTupleType'(['intType']))).
'bindval'('MAIN',';'('agent_call'('src_span'(6,8,6,14,6,6),'GEN',['int'(0)]),'agent_call'('src_span'(6,17,6,23,6,6),'GEN',['int'(1)]),'src_span_operator'('no_loc_info_available','src_span'(6,14,6,17,3,3))),'src_span'(6,1,6,23,22,22)).
'agent'('GEN'(_x),'[]'('prefix'('src_span'(8,10,8,13,3,3),['out'(_x)],'gen','agent_call'('src_span'(8,19,8,27,8,8),'GEN',[_x,'int'(1)]),'src_span'(8,15,8,19,4,4)),'prefix'('src_span'(8,31,8,35,4,4),['out'(_x)],'stop','skip'('src_span'(8,41,8,45,4,4)),'src_span'(8,37,8,41,4,4)),'src_span_operator'('no_loc_info_available','src_span'(8,27,8,31,4,4))),'src_span'(8,1,8,45,44,44)).
'bindval'('PROB_TEST_TRACE',';'('prefix'('src_span'(11,19,11,24,5,5),[],'dotTuple'(['gen','int'(0)]),'prefix'('src_span'(11,28,11,33,5,5),[],'dotTuple'(['gen','int'(1)]),'prefix'('src_span'(11,37,11,42,5,5),[],'dotTuple'(['gen','int'(2)]),'prefix'('src_span'(11,46,11,51,5,5),[],'dotTuple'(['gen','int'(3)]),'prefix'('src_span'(11,55,11,60,5,5),[],'dotTuple'(['gen','int'(4)]),'prefix'('src_span'(11,64,11,70,6,6),[],'dotTuple'(['stop','int'(5)]),'prefix'('src_span'(11,74,11,79,5,5),[],'dotTuple'(['gen','int'(1)]),'prefix'('src_span'(11,83,11,88,5,5),[],'dotTuple'(['gen','int'(2)]),'prefix'('src_span'(11,92,11,97,5,5),[],'dotTuple'(['gen','int'(3)]),'prefix'('src_span'(11,101,11,106,5,5),[],'dotTuple'(['gen','int'(4)]),'prefix'('src_span'(11,110,11,116,6,6),[],'dotTuple'(['stop','int'(5)]),'skip'('src_span'(11,120,11,124,4,4)),'src_span'(11,116,11,120,4,4)),'src_span'(11,106,11,110,4,4)),'src_span'(11,97,11,101,4,4)),'src_span'(11,88,11,92,4,4)),'src_span'(11,79,11,83,4,4)),'src_span'(11,70,11,74,4,4)),'src_span'(11,60,11,64,4,4)),'src_span'(11,51,11,55,4,4)),'src_span'(11,42,11,46,4,4)),'src_span'(11,33,11,37,4,4)),'src_span'(11,24,11,28,4,4)),'stop'('src_span'(11,127,11,131,4,4)),'src_span_operator'('no_loc_info_available','src_span'(11,124,11,127,3,3))),'src_span'(11,1,11,131,130,130)).
'val_of'('MAIN','src_span'(13,8,13,12,4,4)),'val_of'('PROB_TEST_TRACE','src_span'(13,17,13,32,15,15)).
'symbol'('gen','gen','src_span'(3,9,3,12,3,3),'Channel').
'symbol'('GEN','GEN','src_span'(8,1,8,4,3,3),'Function or Process').
'symbol'('PROB_TEST_TRACE','PROB_TEST_TRACE','src_span'(11,1,11,16,15,15),'Ident (Groundrep.)').
'symbol'('stop','stop','src_span'(4,9,4,13,4,4),'Channel').
'symbol'('x','x','src_span'(8,5,8,6,1,1),'Ident (Prolog Variable)').
'symbol'('MAIN','MAIN','src_span'(6,1,6,5,4,4),'Ident (Groundrep.)').

