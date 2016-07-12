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
'channel'('prize','type'('dotUnitType')).
'bindval'('S','year1','year2','year3','pass','fail','graduate','src_span'(10,1,10,52,51,51)).
'bindval'('P','pass','present','src_span'(12,1,12,24,23,23)).
'bindval'('C','pass','fail','prize','src_span'(14,1,14,28,27,27)).
'bindval'('STUDENT','prefix'('src_span'(16,11,16,16,5,5),[],'year1','tupleExp'(['[]'('prefix'('src_span'(16,21,16,25,4,4),[],'pass','val_of'('YEAR2','src_span'(16,29,16,34,5,5)),'src_span'(16,25,16,29,4,4)),'prefix'('src_span'(16,38,16,42,4,4),[],'fail','val_of'('STUDENT','src_span'(16,46,16,53,7,7)),'src_span'(16,42,16,46,4,4)),'src_span_operator'('no_loc_info_available','src_span'(16,34,16,38,4,4)))]),'src_span'(16,16,16,20,4,4)),'src_span'(16,1,16,54,53,53)).
'bindval'('YEAR2','prefix'('src_span'(18,9,18,14,5,5),[],'year2','tupleExp'(['[]'('prefix'('src_span'(18,19,18,23,4,4),[],'pass','val_of'('YEAR3','src_span'(18,27,18,32,5,5)),'src_span'(18,23,18,27,4,4)),'prefix'('src_span'(18,36,18,40,4,4),[],'fail','val_of'('YEAR2','src_span'(18,44,18,49,5,5)),'src_span'(18,40,18,44,4,4)),'src_span_operator'('no_loc_info_available','src_span'(18,32,18,36,4,4)))]),'src_span'(18,14,18,18,4,4)),'src_span'(18,1,18,50,49,49)).
'bindval'('YEAR3','prefix'('src_span'(20,9,20,14,5,5),[],'year3','tupleExp'(['[]'('prefix'('src_span'(20,19,20,23,4,4),[],'pass','prefix'('src_span'(20,27,20,35,8,8),[],'graduate','stop'('src_span'(20,39,20,43,4,4)),'src_span'(20,35,20,39,4,4)),'src_span'(20,23,20,27,4,4)),'tupleExp'(['prefix'('src_span'(20,48,20,52,4,4),[],'fail','val_of'('YEAR3','src_span'(20,56,20,61,5,5)),'src_span'(20,52,20,56,4,4))]),'src_span_operator'('no_loc_info_available','src_span'(20,43,20,47,4,4)))]),'src_span'(20,14,20,18,4,4)),'src_span'(20,1,20,63,62,62)).
'bindval'('PARENT','prefix'('src_span'(22,10,22,14,4,4),[],'pass','prefix'('src_span'(22,18,22,25,7,7),[],'present','val_of'('PARENT','src_span'(22,29,22,35,6,6)),'src_span'(22,25,22,29,4,4)),'src_span'(22,14,22,18,4,4)),'src_span'(22,1,22,35,34,34)).
'bindval'('COLLEGE','[]'('prefix'('src_span'(24,11,24,15,4,4),[],'fail','stop'('src_span'(24,19,24,23,4,4)),'src_span'(24,15,24,19,4,4)),'prefix'('src_span'(24,27,24,31,4,4),[],'pass','val_of'('C1','src_span'(24,35,24,37,2,2)),'src_span'(24,31,24,35,4,4)),'src_span_operator'('no_loc_info_available','src_span'(24,23,24,27,4,4))),'src_span'(24,1,24,37,36,36)).
'bindval'('C1','[]'('prefix'('src_span'(26,6,26,10,4,4),[],'fail','stop'('src_span'(26,14,26,18,4,4)),'src_span'(26,10,26,14,4,4)),'prefix'('src_span'(26,22,26,26,4,4),[],'pass','val_of'('C2','src_span'(26,30,26,32,2,2)),'src_span'(26,26,26,30,4,4)),'src_span_operator'('no_loc_info_available','src_span'(26,18,26,22,4,4))),'src_span'(26,1,26,32,31,31)).
'bindval'('C2','[]'('prefix'('src_span'(28,6,28,10,4,4),[],'fail','stop'('src_span'(28,14,28,18,4,4)),'src_span'(28,10,28,14,4,4)),'prefix'('src_span'(28,22,28,26,4,4),[],'pass','prefix'('src_span'(28,30,28,35,5,5),[],'prize','stop'('src_span'(28,39,28,43,4,4)),'src_span'(28,35,28,39,4,4)),'src_span'(28,26,28,30,4,4)),'src_span_operator'('no_loc_info_available','src_span'(28,18,28,22,4,4))),'src_span'(28,1,28,43,42,42)).
'bindval'('SYSTEM','aParallel'('tupleExp'(['aParallel'('val_of'('STUDENT','src_span'(30,11,30,18,7,7)),'val_of'('S','src_span'(30,21,30,22,1,1)),'val_of'('P','src_span'(30,26,30,27,1,1)),'val_of'('PARENT','src_span'(30,30,30,36,6,6)),'src_span'(30,18,30,21,3,3))]),'agent_call'('src_span'(30,40,30,50,10,10),'union',['val_of'('S','src_span'(30,46,30,47,1,1)),'val_of'('P','src_span'(30,48,30,49,1,1))]),'val_of'('C','src_span'(30,54,30,55,1,1)),'val_of'('COLLEGE','src_span'(30,58,30,65,7,7)),'src_span'(30,37,30,40,3,3)),'src_span'(30,1,30,65,64,64)).
'symbol'('SYSTEM','SYSTEM','src_span'(30,1,30,7,6,6),'Ident (Groundrep.)').
'symbol'('C','C','src_span'(14,1,14,2,1,1),'Ident (Groundrep.)').
'symbol'('pass','pass','src_span'(8,30,8,34,4,4),'Channel').
'symbol'('year1','year1','src_span'(8,9,8,14,5,5),'Channel').
'symbol'('year2','year2','src_span'(8,16,8,21,5,5),'Channel').
'symbol'('prize','prize','src_span'(8,61,8,66,5,5),'Channel').
'symbol'('year3','year3','src_span'(8,23,8,28,5,5),'Channel').
'symbol'('C1','C1','src_span'(26,1,26,3,2,2),'Ident (Groundrep.)').
'symbol'('C2','C2','src_span'(28,1,28,3,2,2),'Ident (Groundrep.)').
'symbol'('P','P','src_span'(12,1,12,2,1,1),'Ident (Groundrep.)').
'symbol'('YEAR3','YEAR3','src_span'(20,1,20,6,5,5),'Ident (Groundrep.)').
'symbol'('fail','fail','src_span'(8,36,8,40,4,4),'Channel').
'symbol'('STUDENT','STUDENT','src_span'(16,1,16,8,7,7),'Ident (Groundrep.)').
'symbol'('S','S','src_span'(10,1,10,2,1,1),'Ident (Groundrep.)').
'symbol'('YEAR2','YEAR2','src_span'(18,1,18,6,5,5),'Ident (Groundrep.)').
'symbol'('COLLEGE','COLLEGE','src_span'(24,1,24,8,7,7),'Ident (Groundrep.)').
'symbol'('graduate','graduate','src_span'(8,42,8,50,8,8),'Channel').
'symbol'('PARENT','PARENT','src_span'(22,1,22,7,6,6),'Ident (Groundrep.)').
'symbol'('present','present','src_span'(8,52,8,59,7,7),'Channel').

