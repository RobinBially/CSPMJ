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
'bindval'('MyInt','int'(0),'int'(99),'src_span'(3,1,3,16,15,15)).
'channel'('gen','type'('dotTupleType'(['val_of'('MyInt','src_span'(5,13,5,18,5,5))]))).
'channel'('stop','type'('dotTupleType'(['val_of'('MyInt','src_span'(6,14,6,19,5,5))]))).
'bindval'('MAIN',';'('val_of'('TestGCD','src_span'(8,8,8,15,7,7)),'tupleExp'(['|||'('agent_call'('src_span'(8,19,8,25,6,6),'GEN',['int'(0)]),'agent_call'('src_span'(8,30,8,36,6,6),'GEN',['int'(0)]),'src_span_operator'('no_loc_info_available','src_span'(8,25,8,30,5,5)))]),'src_span_operator'('no_loc_info_available','src_span'(8,15,8,18,3,3))),'src_span'(8,1,8,37,36,36)).
'agent'('GEN'(_x),'[]'('tupleExp'(['&'(_x,'int'(99),'prefix'('src_span'(10,18,10,21,3,3),['out'(_x)],'gen','agent_call'('src_span'(10,27,10,35,8,8),'GEN',[_x,'int'(1)]),'src_span'(10,23,10,27,4,4)))]),'prefix'('src_span'(10,40,10,44,4,4),['out'(_x)],'stop','stop'('src_span'(10,50,10,54,4,4)),'src_span'(10,46,10,50,4,4)),'src_span_operator'('no_loc_info_available','src_span'(10,36,10,40,4,4))),'src_span'(10,1,10,54,53,53)).
'channel'('out','type'('dotTupleType'(['val_of'('MyInt','src_span'(12,13,12,18,5,5)),'val_of'('MyInt','src_span'(12,19,12,24,5,5))]))).
'agent'('GCD'(_x2,_y),'prefix'('src_span'(13,12,13,15,3,3),['out'(_x2),'out'(_y)],'out','tupleExp'(['ifte'(_y,'int'(0),'agent_call'('src_span'(13,36,13,48,12,12),'GCD',[_y,_x2,_y]),'prefix'('src_span'(13,54,13,57,3,3),['out'(_x2)],'gen','skip'('src_span'(13,63,13,67,4,4)),'src_span'(13,59,13,63,4,4)),'src_span'(13,24,13,26,2,2),'src_span'(13,30,13,36,6,6),'src_span'(13,48,13,54,6,6))]),'src_span'(13,19,13,23,4,4)),'src_span'(13,1,13,68,67,67)).
'bindval'('TestGCD','agent_call'('src_span'(14,11,14,21,10,10),'GCD',['int'(60),'int'(42)]),'src_span'(14,1,14,21,20,20)).
'bindval'('PROB_TEST_TRACE','prefix'('src_span'(20,19,20,28,9,9),[],'dotTuple'(['out','int'(60),'int'(42)]),'prefix'('src_span'(20,32,20,41,9,9),[],'dotTuple'(['out','int'(42),'int'(18)]),'prefix'('src_span'(20,45,20,53,8,8),[],'dotTuple'(['out','int'(18),'int'(6)]),'prefix'('src_span'(20,57,20,64,7,7),[],'dotTuple'(['out','int'(6),'int'(0)]),'prefix'('src_span'(20,68,20,73,5,5),[],'dotTuple'(['gen','int'(6)]),'prefix'('src_span'(20,77,20,82,5,5),[],'dotTuple'(['gen','int'(0)]),'prefix'('src_span'(20,86,20,91,5,5),[],'dotTuple'(['gen','int'(1)]),'prefix'('src_span'(20,95,20,100,5,5),[],'dotTuple'(['gen','int'(2)]),'prefix'('src_span'(20,104,20,109,5,5),[],'dotTuple'(['gen','int'(0)]),'prefix'('src_span'(20,113,20,118,5,5),[],'dotTuple'(['gen','int'(3)]),'prefix'('src_span'(20,122,20,127,5,5),[],'dotTuple'(['gen','int'(4)]),'prefix'('src_span'(20,131,20,136,5,5),[],'dotTuple'(['gen','int'(1)]),'prefix'('src_span'(20,140,20,145,5,5),[],'dotTuple'(['gen','int'(2)]),'prefix'('src_span'(20,149,20,154,5,5),[],'dotTuple'(['gen','int'(3)]),'prefix'('src_span'(20,158,20,163,5,5),[],'dotTuple'(['gen','int'(4)]),'prefix'('src_span'(20,167,20,172,5,5),[],'dotTuple'(['gen','int'(5)]),'prefix'('src_span'(20,176,20,181,5,5),[],'dotTuple'(['gen','int'(6)]),'prefix'('src_span'(20,185,20,190,5,5),[],'dotTuple'(['gen','int'(5)]),'prefix'('src_span'(20,194,20,199,5,5),[],'dotTuple'(['gen','int'(7)]),'prefix'('src_span'(20,203,20,208,5,5),[],'dotTuple'(['gen','int'(8)]),'prefix'('src_span'(20,212,20,218,6,6),[],'dotTuple'(['stop','int'(9)]),'prefix'('src_span'(20,222,20,227,5,5),[],'dotTuple'(['gen','int'(6)]),'prefix'('src_span'(20,231,20,236,5,5),[],'dotTuple'(['gen','int'(7)]),'prefix'('src_span'(20,240,20,245,5,5),[],'dotTuple'(['gen','int'(8)]),'prefix'('src_span'(20,249,20,254,5,5),[],'dotTuple'(['gen','int'(9)]),'prefix'('src_span'(20,258,20,264,6,6),[],'dotTuple'(['gen','int'(10)]),'prefix'('src_span'(20,268,20,274,6,6),[],'dotTuple'(['gen','int'(11)]),'prefix'('src_span'(20,278,20,284,6,6),[],'dotTuple'(['gen','int'(12)]),'prefix'('src_span'(20,288,20,294,6,6),[],'dotTuple'(['gen','int'(13)]),'prefix'('src_span'(20,298,20,304,6,6),[],'dotTuple'(['gen','int'(14)]),'prefix'('src_span'(20,308,20,315,7,7),[],'dotTuple'(['stop','int'(15)]),'stop'('src_span'(20,319,20,323,4,4)),'src_span'(20,315,20,319,4,4)),'src_span'(20,304,20,308,4,4)),'src_span'(20,294,20,298,4,4)),'src_span'(20,284,20,288,4,4)),'src_span'(20,274,20,278,4,4)),'src_span'(20,264,20,268,4,4)),'src_span'(20,254,20,258,4,4)),'src_span'(20,245,20,249,4,4)),'src_span'(20,236,20,240,4,4)),'src_span'(20,227,20,231,4,4)),'src_span'(20,218,20,222,4,4)),'src_span'(20,208,20,212,4,4)),'src_span'(20,199,20,203,4,4)),'src_span'(20,190,20,194,4,4)),'src_span'(20,181,20,185,4,4)),'src_span'(20,172,20,176,4,4)),'src_span'(20,163,20,167,4,4)),'src_span'(20,154,20,158,4,4)),'src_span'(20,145,20,149,4,4)),'src_span'(20,136,20,140,4,4)),'src_span'(20,127,20,131,4,4)),'src_span'(20,118,20,122,4,4)),'src_span'(20,109,20,113,4,4)),'src_span'(20,100,20,104,4,4)),'src_span'(20,91,20,95,4,4)),'src_span'(20,82,20,86,4,4)),'src_span'(20,73,20,77,4,4)),'src_span'(20,64,20,68,4,4)),'src_span'(20,53,20,57,4,4)),'src_span'(20,41,20,45,4,4)),'src_span'(20,28,20,32,4,4)),'src_span'(20,1,20,323,322,322)).
'val_of'('MAIN','src_span'(22,8,22,12,4,4)),'val_of'('PROB_TEST_TRACE','src_span'(22,17,22,32,15,15)).
'symbol'('gen','gen','src_span'(5,9,5,12,3,3),'Channel').
'symbol'('GEN','GEN','src_span'(10,1,10,4,3,3),'Function or Process').
'symbol'('PROB_TEST_TRACE','PROB_TEST_TRACE','src_span'(20,1,20,16,15,15),'Ident (Groundrep.)').
'symbol'('stop','stop','src_span'(6,9,6,13,4,4),'Channel').
'symbol'('MyInt','MyInt','src_span'(3,1,3,6,5,5),'Ident (Groundrep.)').
'symbol'('x','x','src_span'(10,5,10,6,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(13,5,13,6,1,1),'Ident (Prolog Variable)').
'symbol'('GCD','GCD','src_span'(13,1,13,4,3,3),'Function or Process').
'symbol'('y','y','src_span'(13,7,13,8,1,1),'Ident (Prolog Variable)').
'symbol'('TestGCD','TestGCD','src_span'(14,1,14,8,7,7),'Ident (Groundrep.)').
'symbol'('MAIN','MAIN','src_span'(8,1,8,5,4,4),'Ident (Groundrep.)').
'symbol'('out','out','src_span'(12,9,12,12,3,3),'Channel').

