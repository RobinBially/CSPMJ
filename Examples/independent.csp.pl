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
'channel'('up','type'('dotUnitType')).
'channel'('down','type'('dotUnitType')).
'channel'('left','type'('dotUnitType')).
'channel'('right','type'('dotUnitType')).
'bindval'('UD','prefix'('src_span'(9,6,9,8,2,2),[],'up','prefix'('src_span'(9,12,9,16,4,4),[],'down','val_of'('UD','src_span'(9,20,9,22,2,2)),'src_span'(9,16,9,20,4,4)),'src_span'(9,8,9,12,4,4)),'src_span'(9,1,9,22,21,21)).
'bindval'('LR','prefix'('src_span'(11,6,11,10,4,4),[],'left','prefix'('src_span'(11,14,11,19,5,5),[],'right','val_of'('LR','src_span'(11,23,11,25,2,2)),'src_span'(11,19,11,23,4,4)),'src_span'(11,10,11,14,4,4)),'src_span'(11,1,11,25,24,24)).
'bindval'('UDLR','aParallel'('val_of'('UD','src_span'(13,8,13,10,2,2)),'up','down','left','right','val_of'('LR','src_span'(13,49,13,51,2,2)),'src_span'(13,10,13,13,3,3)),'src_span'(13,1,13,51,50,50)).
'symbol'('UDLR','UDLR','src_span'(13,1,13,5,4,4),'Ident (Groundrep.)').
'symbol'('left','left','src_span'(7,19,7,23,4,4),'Channel').
'symbol'('LR','LR','src_span'(11,1,11,3,2,2),'Ident (Groundrep.)').
'symbol'('up','up','src_span'(7,9,7,11,2,2),'Channel').
'symbol'('right','right','src_span'(7,25,7,30,5,5),'Channel').
'symbol'('down','down','src_span'(7,13,7,17,4,4),'Channel').
'symbol'('UD','UD','src_span'(9,1,9,3,2,2),'Ident (Groundrep.)').

