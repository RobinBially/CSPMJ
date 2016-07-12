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
'bindval'('R','let'(['bindval'('Q','stop'('src_span'(4,14,4,18,4,4)),'src_span'(4,10,4,18,8,8))],'prefix'('src_span'(5,15,5,16,1,1),[],'a','val_of'('Q','src_span'(5,19,5,20,1,1)),'src_span'(5,16,5,19,3,3))),'src_span'(3,1,5,20,19,19)).
'bindval'('Q2','prefix'('src_span'(6,5,6,6,1,1),[],'a','prefix'('src_span'(6,9,6,10,1,1),[],'a','skip'('src_span'(6,14,6,18,4,4)),'src_span'(6,10,6,14,4,4)),'src_span'(6,6,6,9,3,3)),'src_span'(6,1,6,18,17,17)).
'symbol'('a','a','src_span'(2,9,2,10,1,1),'Channel').
'symbol'('Q','Q','src_span'(4,10,4,11,1,1),'Ident (Groundrep.)').
'symbol'('Q2','Q','src_span'(6,1,6,2,1,1),'Ident (Groundrep.)').
'symbol'('R','R','src_span'(3,1,3,2,1,1),'Ident (Groundrep.)').

