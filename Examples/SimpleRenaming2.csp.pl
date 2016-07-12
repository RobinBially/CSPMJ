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
'channel'('gen','type'('dotTupleType'(['setExp'('int'(0),'int'(9))]))).
'channel'('gen1','type'('dotTupleType'(['setExp'('int'(0),'int'(9))]))).
'channel'('gen2','type'('dotTupleType'(['setExp'('int'(0),'int'(9)),'setExp'('int'(0),'int'(9))]))).
'channel'('stop','type'('dotTupleType'(['setExp'('int'(0),'int'(9))]))).
'bindval'('RTEST1','agent_call'('src_span'(8,10,8,16,6,6),'GEN',['int'(0)]),'dotTuple'(['gen','tupleExp'(['int'(0),'int'(0)])]),'dotTuple'(['gen2','int'(9),'int'(9)]),'stop','dotTuple'(['gen2','tupleExp'(['int'(2),'int'(3)])]),'src_span'(8,1,8,63,62,62)).
'agent'('GEN'(_x),'[]'('tupleExp'(['&'(_x,'int'(9),'prefix'('src_span'(11,17,11,20,3,3),['out'(_x)],'gen','agent_call'('src_span'(11,26,11,34,8,8),'GEN',[_x,'int'(1)]),'src_span'(11,22,11,26,4,4)))]),'prefix'('src_span'(11,39,11,43,4,4),['out'(_x)],'stop','skip'('src_span'(11,49,11,53,4,4)),'src_span'(11,45,11,49,4,4)),'src_span_operator'('no_loc_info_available','src_span'(11,35,11,39,4,4))),'src_span'(11,1,11,53,52,52)).
'agent'('DGEN'(_x2),'[]'('tupleExp'(['&'(_x2,'int'(9),'prefix'('src_span'(14,18,14,22,4,4),['out'(_x2),'out'(_x2)],'gen2','agent_call'('src_span'(14,30,14,39,9,9),'DGEN',[_x2,'int'(1)]),'src_span'(14,26,14,30,4,4)))]),'prefix'('src_span'(14,44,14,48,4,4),['out'(_x2)],'stop','skip'('src_span'(14,54,14,58,4,4)),'src_span'(14,50,14,54,4,4)),'src_span_operator'('no_loc_info_available','src_span'(14,40,14,44,4,4))),'src_span'(14,1,14,58,57,57)).
'bindval'('RTEST2','agent_call'('src_span'(16,10,16,17,7,7),'DGEN',['int'(0)]),'dotTuple'(['gen2','int'(0)]),'gen','dotTuple'(['gen2','int'(2)]),'gen','dotTuple'(['gen2','int'(4)]),'gen','dotTuple'(['gen2','int'(6)]),'gen','src_span'(16,1,16,82,81,81)).
'bindval'('MAIN',';'('val_of'('RTEST1','src_span'(18,8,18,14,6,6)),'val_of'('RTEST2','src_span'(18,17,18,23,6,6)),'src_span_operator'('no_loc_info_available','src_span'(18,14,18,17,3,3))),'src_span'(18,1,18,23,22,22)).
'bindval'('PROB_TEST_TRACE',';'('prefix'('src_span'(21,19,21,27,8,8),[],'dotTuple'(['gen2','int'(9),'int'(9)]),'prefix'('src_span'(21,31,21,36,5,5),[],'dotTuple'(['gen','int'(1)]),'prefix'('src_span'(21,40,21,45,5,5),[],'dotTuple'(['gen','int'(2)]),'prefix'('src_span'(21,49,21,54,5,5),[],'dotTuple'(['gen','int'(3)]),'prefix'('src_span'(21,58,21,63,5,5),[],'dotTuple'(['gen','int'(4)]),'prefix'('src_span'(21,67,21,72,5,5),[],'dotTuple'(['gen','int'(5)]),'prefix'('src_span'(21,76,21,84,8,8),[],'dotTuple'(['gen2','int'(5),'int'(6)]),'prefix'('src_span'(21,88,21,93,5,5),[],'dotTuple'(['gen','int'(0)]),'prefix'('src_span'(21,97,21,105,8,8),[],'dotTuple'(['gen2','int'(1),'int'(1)]),'prefix'('src_span'(21,109,21,114,5,5),[],'dotTuple'(['gen','int'(2)]),'prefix'('src_span'(21,118,21,126,8,8),[],'dotTuple'(['gen2','int'(3),'int'(3)]),'prefix'('src_span'(21,130,21,135,5,5),[],'dotTuple'(['gen','int'(4)]),'prefix'('src_span'(21,139,21,147,8,8),[],'dotTuple'(['gen2','int'(5),'int'(5)]),'prefix'('src_span'(21,151,21,156,5,5),[],'dotTuple'(['gen','int'(6)]),'prefix'('src_span'(21,160,21,168,8,8),[],'dotTuple'(['gen2','int'(7),'int'(7)]),'prefix'('src_span'(21,172,21,180,8,8),[],'dotTuple'(['gen2','int'(8),'int'(8)]),'prefix'('src_span'(21,184,21,190,6,6),[],'dotTuple'(['stop','int'(9)]),'skip'('src_span'(21,194,21,198,4,4)),'src_span'(21,190,21,194,4,4)),'src_span'(21,180,21,184,4,4)),'src_span'(21,168,21,172,4,4)),'src_span'(21,156,21,160,4,4)),'src_span'(21,147,21,151,4,4)),'src_span'(21,135,21,139,4,4)),'src_span'(21,126,21,130,4,4)),'src_span'(21,114,21,118,4,4)),'src_span'(21,105,21,109,4,4)),'src_span'(21,93,21,97,4,4)),'src_span'(21,84,21,88,4,4)),'src_span'(21,72,21,76,4,4)),'src_span'(21,63,21,67,4,4)),'src_span'(21,54,21,58,4,4)),'src_span'(21,45,21,49,4,4)),'src_span'(21,36,21,40,4,4)),'src_span'(21,27,21,31,4,4)),'stop'('src_span'(21,201,21,205,4,4)),'src_span_operator'('no_loc_info_available','src_span'(21,198,21,201,3,3))),'src_span'(21,1,21,205,204,204)).
'val_of'('MAIN','src_span'(23,8,23,12,4,4)),'val_of'('PROB_TEST_TRACE','src_span'(23,17,23,32,15,15)).
'symbol'('RTEST1','RTEST1','src_span'(8,1,8,7,6,6),'Ident (Groundrep.)').
'symbol'('gen','gen','src_span'(3,9,3,12,3,3),'Channel').
'symbol'('gen1','gen1','src_span'(4,9,4,13,4,4),'Channel').
'symbol'('GEN','GEN','src_span'(11,1,11,4,3,3),'Function or Process').
'symbol'('gen2','gen2','src_span'(5,9,5,13,4,4),'Channel').
'symbol'('PROB_TEST_TRACE','PROB_TEST_TRACE','src_span'(21,1,21,16,15,15),'Ident (Groundrep.)').
'symbol'('DGEN','DGEN','src_span'(14,1,14,5,4,4),'Function or Process').
'symbol'('RTEST2','RTEST2','src_span'(16,1,16,7,6,6),'Ident (Groundrep.)').
'symbol'('stop','stop','src_span'(6,9,6,13,4,4),'Channel').
'symbol'('x','x','src_span'(11,5,11,6,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(14,6,14,7,1,1),'Ident (Prolog Variable)').
'symbol'('MAIN','MAIN','src_span'(18,1,18,5,4,4),'Ident (Groundrep.)').

