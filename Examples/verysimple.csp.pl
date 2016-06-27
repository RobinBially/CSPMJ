:- dynamic parserVersionNum/1, parserVersionStr/1, parseResult/5.
:- dynamic module/4.
'parserVersionStr'('0.6.1.0').
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
'parserVersionNum'([0,10,1,0]).
'parserVersionStr'('CSPM-Frontent-0.10.1.0').
'bindval'('P','skip'('src_span'(1,5,1,9,4,4)),'src_span'(1,1,1,9,0,8)).
'bindval'('Q','stop'('src_span'(3,5,3,9,14,4)),'src_span'(3,1,3,9,10,8)).
'symbol'('P','P','src_span'(1,1,1,2,0,1),'Ident (Groundrep.)').
'symbol'('Q','Q','src_span'(3,1,3,2,10,1),'Ident (Groundrep.)').