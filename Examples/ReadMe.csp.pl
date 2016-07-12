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
'channel'('ReadMe','type'('dotUnitType')).
'bindval'('MAIN','prefix'('src_span'(2,8,2,14,6,6),[],'ReadMe','stop'('src_span'(2,16,2,20,4,4)),'src_span'(2,14,2,16,2,2)),'src_span'(2,1,2,20,19,19)).
'symbol'('ReadMe','ReadMe','src_span'(1,9,1,15,6,6),'Channel').
'symbol'('MAIN','MAIN','src_span'(2,1,2,5,4,4),'Ident (Groundrep.)').

