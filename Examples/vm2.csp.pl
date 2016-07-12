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
'channel'('insert_coin','type'('dotUnitType')).
'channel'('coffee','type'('dotUnitType')).
'channel'('tee','type'('dotUnitType')).
'channel'('ausgeben','type'('dotUnitType')).
'bindval'('Aut','prefix'('src_span'(10,7,10,18,11,11),[],'insert_coin','val_of'('Getraenk','src_span'(10,22,10,30,8,8)),'src_span'(10,18,10,22,4,4)),'src_span'(10,1,10,30,29,29)).
'bindval'('Getraenk','[]'('prefix'('src_span'(12,12,12,18,6,6),[],'coffee','val_of'('GibAus','src_span'(12,22,12,28,6,6)),'src_span'(12,18,12,22,4,4)),'prefix'('src_span'(12,32,12,35,3,3),[],'tee','val_of'('GibAus','src_span'(12,40,12,46,6,6)),'src_span'(12,35,12,40,5,5)),'src_span_operator'('no_loc_info_available','src_span'(12,28,12,32,4,4))),'src_span'(12,1,12,46,45,45)).
'bindval'('GibAus','prefix'('src_span'(14,10,14,18,8,8),[],'ausgeben','val_of'('Aut','src_span'(14,22,14,25,3,3)),'src_span'(14,18,14,22,4,4)),'src_span'(14,1,14,25,24,24)).
'symbol'('Aut','Aut','src_span'(10,1,10,4,3,3),'Ident (Groundrep.)').
'symbol'('Getraenk','Getraenk','src_span'(12,1,12,9,8,8),'Ident (Groundrep.)').
'symbol'('GibAus','GibAus','src_span'(14,1,14,7,6,6),'Ident (Groundrep.)').
'symbol'('insert_coin','insert_coin','src_span'(8,9,8,20,11,11),'Channel').
'symbol'('tee','tee','src_span'(8,30,8,33,3,3),'Channel').
'symbol'('coffee','coffee','src_span'(8,22,8,28,6,6),'Channel').
'symbol'('ausgeben','ausgeben','src_span'(8,35,8,43,8,8),'Channel').

