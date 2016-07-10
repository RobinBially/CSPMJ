:- dynamic parserVersionNum/1, parserVersionStr/1, parseResult/5.
:- dynamic module/4.
'parserVersionStr'('0.6.1.1').
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
'parserVersionNum'([0,11,0,1]).
'parserVersionStr'('CSPM-Frontent-0.11.0.1').
'agent'('a'(_x),'let'(['agent'('a2'(_x2),_x2,'src_span'(2,4,2,13,9,9))],'int'(1)),'src_span'(1,1,3,11,10,10)).
'symbol'('a','a','src_span'(1,1,1,2,1,1),'Function or Process').
'symbol'('a2','a','src_span'(2,4,2,5,1,1),'Function or Process').
'symbol'('x','x','src_span'(1,3,1,4,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(2,6,2,7,1,1),'Ident (Prolog Variable)').

