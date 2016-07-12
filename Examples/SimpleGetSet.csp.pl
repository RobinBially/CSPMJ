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
'channel'('get','type'('dotTupleType'(['intType']))).
'channel'('sets','type'('dotTupleType'(['intType']))).
'bindval'('MAIN','sharing'('val_of'('GETSET','src_span'(3,8,3,14,6,6)),'get','agent_call'('src_span'(3,29,3,35,6,6),'GEN',['int'(0)]),'src_span'(3,14,3,18,4,4)),'src_span'(3,1,3,35,34,34)).
'bindval'('GETSET','prefix'('src_span'(5,10,5,13,3,3),['in'(_Val)],'get','prefix'('src_span'(5,21,5,25,4,4),['out'(_Val)],'sets','val_of'('GETSET','src_span'(5,33,5,39,6,6)),'src_span'(5,29,5,33,4,4)),'src_span'(5,17,5,21,4,4)),'src_span'(5,1,5,39,38,38)).
'agent'('GEN'(_X),'prefix'('src_span'(7,10,7,13,3,3),['out'(_X)],'get','agent_call'('src_span'(7,19,7,27,8,8),'GEN',[_X,'int'(1)]),'src_span'(7,15,7,19,4,4)),'src_span'(7,1,7,27,26,26)).
'symbol'('Val','Val','src_span'(5,14,5,17,3,3),'Ident (Prolog Variable)').
'symbol'('GEN','GEN','src_span'(7,1,7,4,3,3),'Function or Process').
'symbol'('sets','sets','src_span'(1,13,1,17,4,4),'Channel').
'symbol'('get','get','src_span'(1,9,1,12,3,3),'Channel').
'symbol'('GETSET','GETSET','src_span'(5,1,5,7,6,6),'Ident (Groundrep.)').
'symbol'('X','X','src_span'(7,5,7,6,1,1),'Ident (Prolog Variable)').
'symbol'('MAIN','MAIN','src_span'(3,1,3,5,4,4),'Ident (Groundrep.)').

