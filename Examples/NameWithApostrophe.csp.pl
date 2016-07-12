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
'channel'('left','type'('dotTupleType'(['setExp'('int'(1),'int'(10))]))).
'channel'('right','type'('dotTupleType'(['setExp'('int'(1),'int'(10))]))).
'channel'('left'','type'('dotTupleType'(['setExp'('int'(1),'int'(10)),'setExp'('int'(0),'int'(5))]))).
'channel'('right'','type'('dotTupleType'(['setExp'('int'(1),'int'(10)),'setExp'('int'(0),'int'(5))]))).
'bindval'('MAIN','prefix'('src_span'(6,8,6,12,4,4),['in'(_x)],'left','prefix'('src_span'(6,18,6,23,5,5),['out'(_x),'out'('tupleExp'(['src_span'(6,27,6,30,3,3),[],_x,'int'(2)]))],'left'','val_of'('MAIN','src_span'(6,35,6,39,4,4)),'src_span'(6,31,6,35,4,4)),'src_span'(6,14,6,18,4,4)),'src_span'(6,1,6,39,38,38)).
'symbol'('left'','left'','src_span'(3,9,3,14,5,5),'Channel').
'symbol'('left','left','src_span'(2,9,2,13,4,4),'Channel').
'symbol'('x','x','src_span'(6,13,6,14,1,1),'Ident (Prolog Variable)').
'symbol'('MAIN','MAIN','src_span'(6,1,6,5,4,4),'Ident (Groundrep.)').
'symbol'('right','right','src_span'(2,14,2,19,5,5),'Channel').
'symbol'('right'','right'','src_span'(3,15,3,21,6,6),'Channel').

