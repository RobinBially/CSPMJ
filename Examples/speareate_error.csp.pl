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
'bindval'('s','int'(1),'int'(2),'int'(3),'src_span'(3,1,3,12,11,11)).
'bindval'('ll','src_span'(4,1,4,8,7,7)).
'symbol'('ll','ll','src_span'(4,1,4,3,2,2),'Ident (Groundrep.)').
'symbol'('s','s','src_span'(3,1,3,2,1,1),'Ident (Groundrep.)').

