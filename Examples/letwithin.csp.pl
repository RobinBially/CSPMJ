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
'channel'('d','type'('dotTupleType'(['setExp'('int'(2),'int'(10))]))).
'bindval'('MAIN','prefix'('src_span'(5,8,5,30,22,22),[],'dotTuple'(['d','tupleExp'(['let'(['bindval'('x','src_span'(5,17,5,18,1,1),[],'int'(6),'src_span'(5,15,5,18,3,3))],'src_span'(5,26,5,29,3,3),[],'val_of'('x','src_span'(5,26,5,27,1,1)),'int'(2))])]),'skip'('src_span'(5,34,5,38,4,4)),'src_span'(5,30,5,34,4,4)),'src_span'(5,1,5,38,37,37)).
'symbol'('d','d','src_span'(2,9,2,10,1,1),'Channel').
'symbol'('x','x','src_span'(5,15,5,16,1,1),'Ident (Groundrep.)').
'symbol'('MAIN','MAIN','src_span'(5,1,5,5,4,4),'Ident (Groundrep.)').

