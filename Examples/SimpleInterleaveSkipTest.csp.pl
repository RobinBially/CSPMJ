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
'bindval'('MAIN','val_of'('P','src_span'(8,8,8,9,1,1)),'src_span'(8,1,8,9,8,8)).
'bindval'('PROB_TEST_TRACE','prefix'('src_span'(15,19,15,20,1,1),[],'a','prefix'('src_span'(15,24,15,25,1,1),[],'c','prefix'('src_span'(15,29,15,30,1,1),[],'c','prefix'('src_span'(15,34,15,35,1,1),[],'c','prefix'('src_span'(15,39,15,40,1,1),[],'a','prefix'('src_span'(15,44,15,45,1,1),[],'b','prefix'('src_span'(15,49,15,50,1,1),[],'a','prefix'('src_span'(15,54,15,55,1,1),[],'b','prefix'('src_span'(15,59,15,60,1,1),[],'a','prefix'('src_span'(15,64,15,65,1,1),[],'c','prefix'('src_span'(15,69,15,70,1,1),[],'b','prefix'('src_span'(15,74,15,75,1,1),[],'b','prefix'('src_span'(15,79,15,80,1,1),[],'b','prefix'('src_span'(15,84,15,85,1,1),[],'a','prefix'('src_span'(15,89,15,90,1,1),[],'c','prefix'('src_span'(15,94,15,95,1,1),[],'a','prefix'('src_span'(15,99,15,100,1,1),[],'b','prefix'('src_span'(15,104,15,105,1,1),[],'c','prefix'('src_span'(15,109,15,110,1,1),[],'b','prefix'('src_span'(15,114,15,115,1,1),[],'a','prefix'('src_span'(15,119,15,120,1,1),[],'c','stop'('src_span'(15,124,15,128,4,4)),'src_span'(15,120,15,124,4,4)),'src_span'(15,115,15,119,4,4)),'src_span'(15,110,15,114,4,4)),'src_span'(15,105,15,109,4,4)),'src_span'(15,100,15,104,4,4)),'src_span'(15,95,15,99,4,4)),'src_span'(15,90,15,94,4,4)),'src_span'(15,85,15,89,4,4)),'src_span'(15,80,15,84,4,4)),'src_span'(15,75,15,79,4,4)),'src_span'(15,70,15,74,4,4)),'src_span'(15,65,15,69,4,4)),'src_span'(15,60,15,64,4,4)),'src_span'(15,55,15,59,4,4)),'src_span'(15,50,15,54,4,4)),'src_span'(15,45,15,49,4,4)),'src_span'(15,40,15,44,4,4)),'src_span'(15,35,15,39,4,4)),'src_span'(15,30,15,34,4,4)),'src_span'(15,25,15,29,4,4)),'src_span'(15,20,15,24,4,4)),'src_span'(15,1,15,128,127,127)).
'val_of'('MAIN','src_span'(17,8,17,12,4,4)),'val_of'('PROB_TEST_TRACE','src_span'(17,17,17,32,15,15)).
'symbol'('P','P','src_span'(5,1,5,2,1,1),'Ident (Groundrep.)').
'symbol'('a','a','src_span'(3,9,3,10,1,1),'Channel').
'symbol'('b','b','src_span'(3,12,3,13,1,1),'Channel').
'symbol'('P2','P2','src_span'(6,1,6,3,2,2),'Ident (Groundrep.)').
'symbol'('PROB_TEST_TRACE','PROB_TEST_TRACE','src_span'(15,1,15,16,15,15),'Ident (Groundrep.)').
'symbol'('c','c','src_span'(3,15,3,16,1,1),'Channel').
'symbol'('MAIN','MAIN','src_span'(8,1,8,5,4,4),'Ident (Groundrep.)').

