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
'bindval'('MAIN',';'('val_of'('TestGCD','src_span'(6,8,6,15,7,7)),'tupleExp'(['|||'('agent_call'('src_span'(6,19,6,25,6,6),'GEN',['int'(0)]),'agent_call'('src_span'(6,30,6,36,6,6),'GEN',['int'(0)]),'src_span_operator'('no_loc_info_available','src_span'(6,25,6,30,5,5)))]),'src_span_operator'('no_loc_info_available','src_span'(6,15,6,18,3,3))),'src_span'(6,1,6,37,36,36)).
'agent'('GEN'(_x),'[]'('prefix'('src_span'(8,10,8,13,3,3),['out'(_x)],'gen','agent_call'('src_span'(8,19,8,27,8,8),'GEN',[_x,'int'(1)]),'src_span'(8,15,8,19,4,4)),'prefix'('src_span'(8,31,8,35,4,4),['out'(_x)],'stop','stop'('src_span'(8,41,8,45,4,4)),'src_span'(8,37,8,41,4,4)),'src_span_operator'('no_loc_info_available','src_span'(8,27,8,31,4,4))),'src_span'(8,1,8,45,44,44)).
'channel'('out','type'('dotTupleType'(['intType','intType']))).
'agent'('GCD'(_x2,_y),'prefix'('src_span'(11,12,11,15,3,3),['out'(_x2),'out'(_y)],'out','tupleExp'(['ifte'(_y,'int'(0),'agent_call'('src_span'(11,35,11,47,12,12),'GCD',[_y,_x2,_y]),'prefix'('src_span'(11,53,11,56,3,3),['out'(_x2)],'gen','skip'('src_span'(11,62,11,66,4,4)),'src_span'(11,58,11,62,4,4)),'src_span'(11,23,11,25,2,2),'src_span'(11,29,11,35,6,6),'src_span'(11,47,11,53,6,6))]),'src_span'(11,19,11,22,3,3)),'src_span'(11,1,11,67,66,66)).
'bindval'('TestGCD','agent_call'('src_span'(12,11,12,21,10,10),'GCD',['int'(60),'int'(42)]),'src_span'(12,1,12,21,20,20)).
'bindval'('PROB_TEST_TRACE','prefix'('src_span'(18,19,18,28,9,9),[],'dotTuple'(['out','int'(60),'int'(42)]),'prefix'('src_span'(18,32,18,41,9,9),[],'dotTuple'(['out','int'(42),'int'(18)]),'prefix'('src_span'(18,45,18,53,8,8),[],'dotTuple'(['out','int'(18),'int'(6)]),'prefix'('src_span'(18,57,18,64,7,7),[],'dotTuple'(['out','int'(6),'int'(0)]),'prefix'('src_span'(18,68,18,73,5,5),[],'dotTuple'(['gen','int'(6)]),'prefix'('src_span'(18,77,18,82,5,5),[],'dotTuple'(['gen','int'(0)]),'prefix'('src_span'(18,86,18,91,5,5),[],'dotTuple'(['gen','int'(1)]),'prefix'('src_span'(18,95,18,100,5,5),[],'dotTuple'(['gen','int'(2)]),'prefix'('src_span'(18,104,18,109,5,5),[],'dotTuple'(['gen','int'(0)]),'prefix'('src_span'(18,113,18,118,5,5),[],'dotTuple'(['gen','int'(3)]),'prefix'('src_span'(18,122,18,127,5,5),[],'dotTuple'(['gen','int'(4)]),'prefix'('src_span'(18,131,18,136,5,5),[],'dotTuple'(['gen','int'(1)]),'prefix'('src_span'(18,140,18,145,5,5),[],'dotTuple'(['gen','int'(2)]),'prefix'('src_span'(18,149,18,154,5,5),[],'dotTuple'(['gen','int'(3)]),'prefix'('src_span'(18,158,18,163,5,5),[],'dotTuple'(['gen','int'(4)]),'prefix'('src_span'(18,167,18,172,5,5),[],'dotTuple'(['gen','int'(5)]),'prefix'('src_span'(18,176,18,181,5,5),[],'dotTuple'(['gen','int'(6)]),'prefix'('src_span'(18,185,18,190,5,5),[],'dotTuple'(['gen','int'(5)]),'prefix'('src_span'(18,194,18,199,5,5),[],'dotTuple'(['gen','int'(7)]),'prefix'('src_span'(18,203,18,208,5,5),[],'dotTuple'(['gen','int'(8)]),'prefix'('src_span'(18,212,18,218,6,6),[],'dotTuple'(['stop','int'(9)]),'prefix'('src_span'(18,222,18,227,5,5),[],'dotTuple'(['gen','int'(6)]),'prefix'('src_span'(18,231,18,236,5,5),[],'dotTuple'(['gen','int'(7)]),'prefix'('src_span'(18,240,18,245,5,5),[],'dotTuple'(['gen','int'(8)]),'prefix'('src_span'(18,249,18,254,5,5),[],'dotTuple'(['gen','int'(9)]),'prefix'('src_span'(18,258,18,264,6,6),[],'dotTuple'(['gen','int'(10)]),'prefix'('src_span'(18,268,18,274,6,6),[],'dotTuple'(['gen','int'(11)]),'prefix'('src_span'(18,278,18,284,6,6),[],'dotTuple'(['gen','int'(12)]),'prefix'('src_span'(18,288,18,294,6,6),[],'dotTuple'(['gen','int'(13)]),'prefix'('src_span'(18,298,18,304,6,6),[],'dotTuple'(['gen','int'(14)]),'prefix'('src_span'(18,308,18,315,7,7),[],'dotTuple'(['stop','int'(15)]),'stop'('src_span'(18,319,18,323,4,4)),'src_span'(18,315,18,319,4,4)),'src_span'(18,304,18,308,4,4)),'src_span'(18,294,18,298,4,4)),'src_span'(18,284,18,288,4,4)),'src_span'(18,274,18,278,4,4)),'src_span'(18,264,18,268,4,4)),'src_span'(18,254,18,258,4,4)),'src_span'(18,245,18,249,4,4)),'src_span'(18,236,18,240,4,4)),'src_span'(18,227,18,231,4,4)),'src_span'(18,218,18,222,4,4)),'src_span'(18,208,18,212,4,4)),'src_span'(18,199,18,203,4,4)),'src_span'(18,190,18,194,4,4)),'src_span'(18,181,18,185,4,4)),'src_span'(18,172,18,176,4,4)),'src_span'(18,163,18,167,4,4)),'src_span'(18,154,18,158,4,4)),'src_span'(18,145,18,149,4,4)),'src_span'(18,136,18,140,4,4)),'src_span'(18,127,18,131,4,4)),'src_span'(18,118,18,122,4,4)),'src_span'(18,109,18,113,4,4)),'src_span'(18,100,18,104,4,4)),'src_span'(18,91,18,95,4,4)),'src_span'(18,82,18,86,4,4)),'src_span'(18,73,18,77,4,4)),'src_span'(18,64,18,68,4,4)),'src_span'(18,53,18,57,4,4)),'src_span'(18,41,18,45,4,4)),'src_span'(18,28,18,32,4,4)),'src_span'(18,1,18,323,322,322)).
'symbol'('gen','gen','src_span'(3,9,3,12,3,3),'Channel').
'symbol'('GEN','GEN','src_span'(8,1,8,4,3,3),'Function or Process').
'symbol'('PROB_TEST_TRACE','PROB_TEST_TRACE','src_span'(18,1,18,16,15,15),'Ident (Groundrep.)').
'symbol'('stop','stop','src_span'(4,9,4,13,4,4),'Channel').
'symbol'('x','x','src_span'(8,5,8,6,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(11,5,11,6,1,1),'Ident (Prolog Variable)').
'symbol'('GCD','GCD','src_span'(11,1,11,4,3,3),'Function or Process').
'symbol'('y','y','src_span'(11,7,11,8,1,1),'Ident (Prolog Variable)').
'symbol'('TestGCD','TestGCD','src_span'(12,1,12,8,7,7),'Ident (Groundrep.)').
'symbol'('MAIN','MAIN','src_span'(6,1,6,5,4,4),'Ident (Groundrep.)').
'symbol'('out','out','src_span'(10,9,10,12,3,3),'Channel').

