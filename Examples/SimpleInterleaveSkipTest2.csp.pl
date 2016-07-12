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
'bindval'('P','|||'('|||'('prefix'('src_span'(5,5,5,6,1,1),[],'a','skip'('src_span'(5,10,5,14,4,4)),'src_span'(5,6,5,10,4,4)),'prefix'('src_span'(5,19,5,20,1,1),[],'b','skip'('src_span'(5,24,5,28,4,4)),'src_span'(5,20,5,24,4,4)),'src_span_operator'('no_loc_info_available','src_span'(5,14,5,19,5,5))),';'('prefix'('src_span'(5,33,5,34,1,1),[],'c','skip'('src_span'(5,38,5,42,4,4)),'src_span'(5,34,5,38,4,4)),'val_of'('P','src_span'(5,43,5,44,1,1)),'src_span_operator'('no_loc_info_available','src_span'(5,42,5,43,1,1))),'src_span_operator'('no_loc_info_available','src_span'(5,28,5,33,5,5))),'src_span'(5,1,5,44,43,43)).
'bindval'('P2',';'('tupleExp'(['|||'('|||'('prefix'('src_span'(6,7,6,8,1,1),[],'a','skip'('src_span'(6,12,6,16,4,4)),'src_span'(6,8,6,12,4,4)),'prefix'('src_span'(6,21,6,22,1,1),[],'b','skip'('src_span'(6,26,6,30,4,4)),'src_span'(6,22,6,26,4,4)),'src_span_operator'('no_loc_info_available','src_span'(6,16,6,21,5,5))),'prefix'('src_span'(6,35,6,36,1,1),[],'c','skip'('src_span'(6,40,6,44,4,4)),'src_span'(6,36,6,40,4,4)),'src_span_operator'('no_loc_info_available','src_span'(6,30,6,35,5,5)))]),'val_of'('P2','src_span'(6,46,6,48,2,2)),'src_span_operator'('no_loc_info_available','src_span'(6,45,6,46,1,1))),'src_span'(6,1,6,48,47,47)).
'bindval'('MAIN','val_of'('P2','src_span'(8,8,8,10,2,2)),'src_span'(8,1,8,10,9,9)).
'bindval'('PROB_TEST_TRACE','prefix'('src_span'(11,19,11,20,1,1),[],'a','prefix'('src_span'(11,24,11,25,1,1),[],'c','prefix'('src_span'(11,29,11,30,1,1),[],'b','prefix'('src_span'(11,34,11,35,1,1),[],'a','prefix'('src_span'(11,39,11,40,1,1),[],'b','prefix'('src_span'(11,44,11,45,1,1),[],'c','prefix'('src_span'(11,49,11,50,1,1),[],'c','prefix'('src_span'(11,54,11,55,1,1),[],'b','prefix'('src_span'(11,59,11,60,1,1),[],'a','prefix'('src_span'(11,64,11,65,1,1),[],'a','prefix'('src_span'(11,69,11,70,1,1),[],'b','prefix'('src_span'(11,74,11,75,1,1),[],'c','stop'('src_span'(11,79,11,83,4,4)),'src_span'(11,75,11,79,4,4)),'src_span'(11,70,11,74,4,4)),'src_span'(11,65,11,69,4,4)),'src_span'(11,60,11,64,4,4)),'src_span'(11,55,11,59,4,4)),'src_span'(11,50,11,54,4,4)),'src_span'(11,45,11,49,4,4)),'src_span'(11,40,11,44,4,4)),'src_span'(11,35,11,39,4,4)),'src_span'(11,30,11,34,4,4)),'src_span'(11,25,11,29,4,4)),'src_span'(11,20,11,24,4,4)),'src_span'(11,1,11,83,82,82)).
'val_of'('MAIN','src_span'(13,8,13,12,4,4)),'val_of'('PROB_TEST_TRACE','src_span'(13,17,13,32,15,15)).
'symbol'('P','P','src_span'(5,1,5,2,1,1),'Ident (Groundrep.)').
'symbol'('a','a','src_span'(3,9,3,10,1,1),'Channel').
'symbol'('b','b','src_span'(3,12,3,13,1,1),'Channel').
'symbol'('P2','P2','src_span'(6,1,6,3,2,2),'Ident (Groundrep.)').
'symbol'('PROB_TEST_TRACE','PROB_TEST_TRACE','src_span'(11,1,11,16,15,15),'Ident (Groundrep.)').
'symbol'('c','c','src_span'(3,15,3,16,1,1),'Channel').
'symbol'('MAIN','MAIN','src_span'(8,1,8,5,4,4),'Ident (Groundrep.)').

