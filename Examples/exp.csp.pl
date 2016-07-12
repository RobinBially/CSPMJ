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
'agent'('test'(_x),'tupleExp'([_x,'tupleExp'([_x,'tupleExp'([_x,'tupleExp'([_x,'tupleExp'([_x,'tupleExp'([_x,'tupleExp'([_x,'tupleExp'([_x,'tupleExp'([_x,'tupleExp'([_x,'tupleExp'([_x,'tupleExp'([_x,'tupleExp'([_x,'tupleExp'([_x,'tupleExp'([_x,'tupleExp'([_x,'tupleExp'([_x])])])])])])])])])])])])])])])])]),'src_span'(2,1,19,18,17,17)).
'symbol'('test','test','src_span'(2,1,2,5,4,4),'Function or Process').
'symbol'('x','x','src_span'(2,6,2,7,1,1),'Ident (Prolog Variable)').

