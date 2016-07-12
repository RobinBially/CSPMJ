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
'channel'('open','type'('dotUnitType')).
'channel'('close','type'('dotUnitType')).
'channel'('stop','type'('dotUnitType')).
'channel'('start','type'('dotUnitType')).
'bindval'('MAIN','val_of'('OPEN','src_span'(6,8,6,12,4,4)),'src_span'(6,1,6,12,11,11)).
'bindval'('OPEN','prefix'('src_span'(8,8,8,13,5,5),[],'close','val_of'('CLOSED','src_span'(8,17,8,23,6,6)),'src_span'(8,13,8,17,4,4)),'src_span'(8,1,8,23,22,22)).
'bindval'('CLOSED','[]'('prefix'('src_span'(10,10,10,14,4,4),[],'open','val_of'('OPEN','src_span'(10,18,10,22,4,4)),'src_span'(10,14,10,18,4,4)),'prefix'('src_span'(10,26,10,31,5,5),[],'start','val_of'('HEAT','src_span'(10,35,10,39,4,4)),'src_span'(10,31,10,35,4,4)),'src_span_operator'('no_loc_info_available','src_span'(10,22,10,26,4,4))),'src_span'(10,1,10,39,38,38)).
'bindval'('HEAT','[]'('prefix'('src_span'(12,8,12,12,4,4),[],'stop','val_of'('CLOSED','src_span'(12,16,12,22,6,6)),'src_span'(12,12,12,16,4,4)),'prefix'('src_span'(12,26,12,30,4,4),[],'open','val_of'('OPEN','src_span'(12,34,12,38,4,4)),'src_span'(12,30,12,34,4,4)),'src_span_operator'('no_loc_info_available','src_span'(12,22,12,26,4,4))),'src_span'(12,1,12,38,37,37)).
'bindval'('PROB_TEST_TRACE','prefix'('src_span'(16,19,16,24,5,5),[],'close','prefix'('src_span'(16,28,16,32,4,4),[],'open','prefix'('src_span'(16,36,16,41,5,5),[],'close','prefix'('src_span'(16,45,16,50,5,5),[],'start','prefix'('src_span'(16,54,16,58,4,4),[],'stop','prefix'('src_span'(16,62,16,67,5,5),[],'start','prefix'('src_span'(16,71,16,75,4,4),[],'stop','prefix'('src_span'(16,79,16,84,5,5),[],'start','prefix'('src_span'(16,88,16,92,4,4),[],'open','prefix'('src_span'(16,96,16,101,5,5),[],'close','stop'('src_span'(16,105,16,109,4,4)),'src_span'(16,101,16,105,4,4)),'src_span'(16,92,16,96,4,4)),'src_span'(16,84,16,88,4,4)),'src_span'(16,75,16,79,4,4)),'src_span'(16,67,16,71,4,4)),'src_span'(16,58,16,62,4,4)),'src_span'(16,50,16,54,4,4)),'src_span'(16,41,16,45,4,4)),'src_span'(16,32,16,36,4,4)),'src_span'(16,24,16,28,4,4)),'src_span'(16,1,16,109,108,108)).
'val_of'('MAIN','src_span'(18,8,18,12,4,4)),'val_of'('PROB_TEST_TRACE','src_span'(18,17,18,32,15,15)).
'symbol'('HEAT','HEAT','src_span'(12,1,12,5,4,4),'Ident (Groundrep.)').
'symbol'('PROB_TEST_TRACE','PROB_TEST_TRACE','src_span'(16,1,16,16,15,15),'Ident (Groundrep.)').
'symbol'('stop','stop','src_span'(4,20,4,24,4,4),'Channel').
'symbol'('CLOSED','CLOSED','src_span'(10,1,10,7,6,6),'Ident (Groundrep.)').
'symbol'('start','start','src_span'(4,25,4,30,5,5),'Channel').
'symbol'('MAIN','MAIN','src_span'(6,1,6,5,4,4),'Ident (Groundrep.)').
'symbol'('close','close','src_span'(4,14,4,19,5,5),'Channel').
'symbol'('open','open','src_span'(4,9,4,13,4,4),'Channel').
'symbol'('OPEN','OPEN','src_span'(8,1,8,5,4,4),'Ident (Groundrep.)').

