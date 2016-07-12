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
'agent'('R'(_b),'let'(['bindval'('b2','stop'('src_span'(3,14,3,18,4,4)),'src_span'(3,10,3,18,8,8))],'prefix'('src_span'(4,15,4,16,1,1),[],'a','val_of'('b2','src_span'(4,19,4,20,1,1)),'src_span'(4,16,4,19,3,3))),'src_span'(2,1,4,20,19,19)).
'bindval'('Q','prefix'('src_span'(5,5,5,6,1,1),[],'a','prefix'('src_span'(5,9,5,10,1,1),[],'a','skip'('src_span'(5,14,5,18,4,4)),'src_span'(5,10,5,14,4,4)),'src_span'(5,6,5,9,3,3)),'src_span'(5,1,5,18,17,17)).
'symbol'('a','a','src_span'(1,9,1,10,1,1),'Channel').
'symbol'('Q','Q','src_span'(5,1,5,2,1,1),'Ident (Groundrep.)').
'symbol'('R','R','src_span'(2,1,2,2,1,1),'Function or Process').
'symbol'('b','b','src_span'(2,3,2,4,1,1),'Ident (Prolog Variable)').
'symbol'('b2','b','src_span'(3,10,3,11,1,1),'Ident (Groundrep.)').

