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
'channel'('board37A','type'('dotUnitType')).
'channel'('alight37A','type'('dotUnitType')).
'channel'('alight37B','type'('dotUnitType')).
'channel'('board111A','type'('dotUnitType')).
'channel'('alight111A','type'('dotUnitType')).
'channel'('alight111B','type'('dotUnitType')).
'channel'('pay70','type'('dotUnitType')).
'channel'('pay90','type'('dotUnitType')).
'bindval'('BUS37','prefix'('src_span'(11,9,11,17,8,8),[],'board37A','tupleExp'(['[]'('prefix'('src_span'(11,22,11,27,5,5),[],'pay90','prefix'('src_span'(11,31,11,40,9,9),[],'alight37B','stop'('src_span'(11,44,11,48,4,4)),'src_span'(11,40,11,44,4,4)),'src_span'(11,27,11,31,4,4)),'prefix'('src_span'(12,25,12,34,9,9),[],'alight37A','stop'('src_span'(12,38,12,42,4,4)),'src_span'(12,34,12,38,4,4)),'src_span_operator'('no_loc_info_available','src_span'(11,48,11,74,26,26)))]),'src_span'(11,17,11,21,4,4)),'src_span'(11,1,12,43,42,42)).
'bindval'('BUS111','prefix'('src_span'(14,10,14,19,9,9),[],'board111A','tupleExp'(['[]'('prefix'('src_span'(14,24,14,29,5,5),[],'pay70','prefix'('src_span'(14,33,14,43,10,10),[],'alight111B','stop'('src_span'(14,47,14,51,4,4)),'src_span'(14,43,14,47,4,4)),'src_span'(14,29,14,33,4,4)),'prefix'('src_span'(15,26,15,36,10,10),[],'alight111A','stop'('src_span'(15,40,15,44,4,4)),'src_span'(15,36,15,40,4,4)),'src_span_operator'('no_loc_info_available','src_span'(14,51,14,78,27,27)))]),'src_span'(14,19,14,23,4,4)),'src_span'(14,1,15,45,44,44)).
'bindval'('SERVICE','|~|'('val_of'('BUS37','src_span'(17,11,17,16,5,5)),'val_of'('BUS111','src_span'(17,21,17,27,6,6)),'src_span_operator'('no_loc_info_available','src_span'(17,16,17,21,5,5))),'src_span'(17,1,17,27,26,26)).
'bindval'('PASS','prefix'('src_span'(19,8,19,16,8,8),[],'board37A','prefix'('src_span'(19,20,19,25,5,5),[],'pay90','prefix'('src_span'(19,29,19,38,9,9),[],'alight37B','stop'('src_span'(19,42,19,46,4,4)),'src_span'(19,38,19,42,4,4)),'src_span'(19,25,19,29,4,4)),'src_span'(19,16,19,20,4,4)),'src_span'(19,1,19,46,45,45)).
'bindval'('SYSTEM','aParallel'('val_of'('SERVICE','src_span'(21,10,21,17,7,7)),'board37A','alight37A','alight37B','board111A','alight111A','alight111B','pay70','pay90','board37A','alight37A','alight37B','board111A','alight111A','alight111B','pay70','pay90','val_of'('PASS','src_span'(26,2,26,6,4,4)),'src_span'(21,17,21,22,5,5)),'src_span'(21,1,26,6,5,5)).
'symbol'('SYSTEM','SYSTEM','src_span'(21,1,21,7,6,6),'Ident (Groundrep.)').
'symbol'('alight37B','alight37B','src_span'(7,30,7,39,9,9),'Channel').
'symbol'('board111A','board111A','src_span'(8,9,8,18,9,9),'Channel').
'symbol'('alight111A','alight111A','src_span'(8,20,8,30,10,10),'Channel').
'symbol'('alight37A','alight37A','src_span'(7,19,7,28,9,9),'Channel').
'symbol'('BUS111','BUS111','src_span'(14,1,14,7,6,6),'Ident (Groundrep.)').
'symbol'('alight111B','alight111B','src_span'(8,32,8,42,10,10),'Channel').
'symbol'('pay90','pay90','src_span'(9,16,9,21,5,5),'Channel').
'symbol'('BUS37','BUS37','src_span'(11,1,11,6,5,5),'Ident (Groundrep.)').
'symbol'('PASS','PASS','src_span'(19,1,19,5,4,4),'Ident (Groundrep.)').
'symbol'('board37A','board37A','src_span'(7,9,7,17,8,8),'Channel').
'symbol'('pay70','pay70','src_span'(9,9,9,14,5,5),'Channel').
'symbol'('SERVICE','SERVICE','src_span'(17,1,17,8,7,7),'Ident (Groundrep.)').

