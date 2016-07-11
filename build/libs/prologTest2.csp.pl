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
'agent'('a'(_x),'let'(['bindval'('u','int'(1),'src_span'(2,4,2,9,5,5)),'agent'('b'(_u2),'let'(['bindval'('c',_u2,'src_span'(4,6,4,11,5,5))],'int'(1)),'src_span'(3,4,5,13,9,9))],_x),'src_span'(1,1,6,11,10,10)).
'symbol'('a','a','src_span'(1,1,1,2,1,1),'Function or Process').
'symbol'('b','b','src_span'(3,4,3,5,1,1),'Function or Process').
'symbol'('c','c','src_span'(4,6,4,7,1,1),'Ident (Groundrep.)').
'symbol'('u','u','src_span'(2,4,2,5,1,1),'Ident (Groundrep.)').
'symbol'('u2','u','src_span'(3,6,3,7,1,1),'Ident (Prolog Variable)').
'symbol'('x','x','src_span'(1,3,1,4,1,1),'Ident (Prolog Variable)').

