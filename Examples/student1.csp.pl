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
'channel'('year1','type'('dotUnitType')).
'channel'('year2','type'('dotUnitType')).
'channel'('year3','type'('dotUnitType')).
'channel'('pass','type'('dotUnitType')).
'channel'('fail','type'('dotUnitType')).
'channel'('graduate','type'('dotUnitType')).
'channel'('present','type'('dotUnitType')).
'bindval'('S','year1','year2','year3','pass','fail','graduate','src_span'(9,1,9,52,51,51)).
'bindval'('P','pass','present','src_span'(11,1,11,24,23,23)).
'bindval'('STUDENT','prefix'('src_span'(13,11,13,16,5,5),[],'year1','tupleExp'(['[]'('prefix'('src_span'(13,21,13,25,4,4),[],'pass','val_of'('YEAR2','src_span'(13,29,13,34,5,5)),'src_span'(13,25,13,29,4,4)),'prefix'('src_span'(13,38,13,42,4,4),[],'fail','val_of'('STUDENT','src_span'(13,46,13,53,7,7)),'src_span'(13,42,13,46,4,4)),'src_span_operator'('no_loc_info_available','src_span'(13,34,13,38,4,4)))]),'src_span'(13,16,13,20,4,4)),'src_span'(13,1,13,54,53,53)).
'bindval'('YEAR2','prefix'('src_span'(15,9,15,14,5,5),[],'year2','tupleExp'(['[]'('prefix'('src_span'(15,19,15,23,4,4),[],'pass','val_of'('YEAR3','src_span'(15,27,15,32,5,5)),'src_span'(15,23,15,27,4,4)),'prefix'('src_span'(15,36,15,40,4,4),[],'fail','val_of'('YEAR2','src_span'(15,44,15,49,5,5)),'src_span'(15,40,15,44,4,4)),'src_span_operator'('no_loc_info_available','src_span'(15,32,15,36,4,4)))]),'src_span'(15,14,15,18,4,4)),'src_span'(15,1,15,50,49,49)).
'bindval'('YEAR3','prefix'('src_span'(17,9,17,14,5,5),[],'year3','tupleExp'(['[]'('prefix'('src_span'(17,19,17,23,4,4),[],'pass','prefix'('src_span'(17,27,17,35,8,8),[],'graduate','stop'('src_span'(17,39,17,43,4,4)),'src_span'(17,35,17,39,4,4)),'src_span'(17,23,17,27,4,4)),'prefix'('src_span'(17,47,17,51,4,4),[],'fail','val_of'('YEAR3','src_span'(17,55,17,60,5,5)),'src_span'(17,51,17,55,4,4)),'src_span_operator'('no_loc_info_available','src_span'(17,43,17,47,4,4)))]),'src_span'(17,14,17,18,4,4)),'src_span'(17,1,17,61,60,60)).
'bindval'('PARENT','prefix'('src_span'(19,10,19,14,4,4),[],'pass','prefix'('src_span'(19,18,19,25,7,7),[],'present','val_of'('PARENT','src_span'(19,29,19,35,6,6)),'src_span'(19,25,19,29,4,4)),'src_span'(19,14,19,18,4,4)),'src_span'(19,1,19,35,34,34)).
'bindval'('SYSTEM','aParallel'('val_of'('STUDENT','src_span'(21,10,21,17,7,7)),'val_of'('S','src_span'(21,20,21,21,1,1)),'val_of'('P','src_span'(21,25,21,26,1,1)),'val_of'('PARENT','src_span'(21,29,21,35,6,6)),'src_span'(21,17,21,20,3,3)),'src_span'(21,1,21,35,34,34)).
'symbol'('SYSTEM','SYSTEM','src_span'(21,1,21,7,6,6),'Ident (Groundrep.)').
'symbol'('pass','pass','src_span'(7,30,7,34,4,4),'Channel').
'symbol'('year1','year1','src_span'(7,9,7,14,5,5),'Channel').
'symbol'('year2','year2','src_span'(7,16,7,21,5,5),'Channel').
'symbol'('year3','year3','src_span'(7,23,7,28,5,5),'Channel').
'symbol'('P','P','src_span'(11,1,11,2,1,1),'Ident (Groundrep.)').
'symbol'('YEAR3','YEAR3','src_span'(17,1,17,6,5,5),'Ident (Groundrep.)').
'symbol'('fail','fail','src_span'(7,36,7,40,4,4),'Channel').
'symbol'('STUDENT','STUDENT','src_span'(13,1,13,8,7,7),'Ident (Groundrep.)').
'symbol'('S','S','src_span'(9,1,9,2,1,1),'Ident (Groundrep.)').
'symbol'('YEAR2','YEAR2','src_span'(15,1,15,6,5,5),'Ident (Groundrep.)').
'symbol'('graduate','graduate','src_span'(7,42,7,50,8,8),'Channel').
'symbol'('PARENT','PARENT','src_span'(19,1,19,7,6,6),'Ident (Groundrep.)').
'symbol'('present','present','src_span'(7,52,7,59,7,7),'Channel').

