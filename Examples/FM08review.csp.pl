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
'bindval'('MAIN1','val_of'('Sync','src_span'(2,9,2,13,4,4)),'src_span'(2,1,2,13,12,12)).
'channel'('a','type'('dotTupleType'(['setExp'('int'(0),'int'(9))]))).
'agent'('P'(_k),'prefix'('src_span'(6,8,6,9,1,1),['in'(_x)],'a','agent_call'('src_span'(6,15,6,21,6,6),'P',[_k,'int'(1)]),'src_span'(6,11,6,15,4,4)),'src_span'(6,1,6,21,20,20)).
'agent'('Q'(_k2),'prefix'('src_span'(7,8,7,9,1,1),['in'(_y)],'a','agent_call'('src_span'(7,15,7,21,6,6),'Q',[_k2,'int'(2)]),'src_span'(7,11,7,15,4,4)),'src_span'(7,1,7,21,20,20)).
'bindval'('Sync','sharing'('agent_call'('src_span'(9,8,9,12,4,4),'P',['int'(0)]),'a','agent_call'('src_span'(9,25,9,29,4,4),'Q',['int'(0)]),'src_span'(9,12,9,16,4,4)),'src_span'(9,1,9,29,28,28)).
'bindval'('TEST','repChoice'(['comprehensionGenerator'('int'(1),'int'(2))],'prefix'('src_span'(12,21,12,22,1,1),['out'],'a','val_of'('TEST','src_span'(12,28,12,32,4,4)),'src_span'(12,24,12,28,4,4)),'src_span'(12,11,12,18,7,7)),'src_span'(12,1,12,32,31,31)).
'symbol'('P','P','src_span'(6,1,6,2,1,1),'Function or Process').
'symbol'('a','a','src_span'(4,9,4,10,1,1),'Channel').
'symbol'('Q','Q','src_span'(7,1,7,2,1,1),'Function or Process').
'symbol'('MAIN1','MAIN1','src_span'(2,1,2,6,5,5),'Ident (Groundrep.)').
'symbol'('TEST','TEST','src_span'(12,1,12,5,4,4),'Ident (Groundrep.)').
'symbol'('x','x','src_span'(6,10,6,11,1,1),'Ident (Prolog Variable)').
'symbol'('y','y','src_span'(7,10,7,11,1,1),'Ident (Prolog Variable)').
'symbol'('k','k','src_span'(6,3,6,4,1,1),'Ident (Prolog Variable)').
'symbol'('k2','k','src_span'(7,3,7,4,1,1),'Ident (Prolog Variable)').
'symbol'('Sync','Sync','src_span'(9,1,9,5,4,4),'Ident (Groundrep.)').

