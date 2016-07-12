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
'bindval'('MAIN','/\'('prefix'('src_span'(3,8,3,9,1,1),[],'a','skip'('src_span'(3,11,3,15,4,4)),'src_span'(3,9,3,11,2,2)),'val_of'('Q','src_span'(3,19,3,20,1,1)),'src_span_operator'('no_loc_info_available','src_span'(3,15,3,19,4,4))),'src_span'(3,1,3,20,19,19)).
'bindval'('P','[]'('prefix'('src_span'(5,5,5,6,1,1),[],'a','prefix'('src_span'(5,8,5,9,1,1),[],'b','stop'('src_span'(5,11,5,15,4,4)),'src_span'(5,9,5,11,2,2)),'src_span'(5,6,5,8,2,2)),'prefix'('src_span'(5,19,5,20,1,1),[],'b','prefix'('src_span'(5,22,5,23,1,1),[],'b','stop'('src_span'(5,25,5,29,4,4)),'src_span'(5,23,5,25,2,2)),'src_span'(5,20,5,22,2,2)),'src_span_operator'('no_loc_info_available','src_span'(5,15,5,19,4,4))),'src_span'(5,1,5,29,28,28)).
'bindval'('Q','[]'('prefix'('src_span'(6,5,6,6,1,1),[],'c','stop'('src_span'(6,8,6,12,4,4)),'src_span'(6,6,6,8,2,2)),'prefix'('src_span'(6,16,6,17,1,1),[],'d','prefix'('src_span'(6,19,6,20,1,1),[],'c','stop'('src_span'(6,22,6,26,4,4)),'src_span'(6,20,6,22,2,2)),'src_span'(6,17,6,19,2,2)),'src_span_operator'('no_loc_info_available','src_span'(6,12,6,16,4,4))),'src_span'(6,1,6,26,25,25)).
'symbol'('P','P','src_span'(5,1,5,2,1,1),'Ident (Groundrep.)').
'symbol'('a','a','src_span'(1,9,1,10,1,1),'Channel').
'symbol'('Q','Q','src_span'(6,1,6,2,1,1),'Ident (Groundrep.)').
'symbol'('b','b','src_span'(1,11,1,12,1,1),'Channel').
'symbol'('c','c','src_span'(1,13,1,14,1,1),'Channel').
'symbol'('d','d','src_span'(1,15,1,16,1,1),'Channel').
'symbol'('MAIN','MAIN','src_span'(3,1,3,5,4,4),'Ident (Groundrep.)').

