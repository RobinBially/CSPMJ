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
'channel'('on','type'('dotUnitType')).
'channel'('off','type'('dotUnitType')).
'channel'('staines','type'('dotUnitType')).
'channel'('ashford','type'('dotUnitType')).
'channel'('pound','type'('dotUnitType')).
'channel'('ticket','type'('dotUnitType')).
'bindval'('MACHINE','prefix'('src_span'(9,11,9,13,2,2),[],'on','val_of'('TICKETS','src_span'(9,17,9,24,7,7)),'src_span'(9,13,9,17,4,4)),'src_span'(9,1,9,24,23,23)).
'bindval'('TICKETS','[]'('[]'('prefix'('src_span'(11,13,11,20,7,7),[],'staines','prefix'('src_span'(11,24,11,29,5,5),[],'pound','prefix'('src_span'(11,33,11,39,6,6),[],'ticket','val_of'('TICKETS','src_span'(11,43,11,50,7,7)),'src_span'(11,39,11,43,4,4)),'src_span'(11,29,11,33,4,4)),'src_span'(11,20,11,24,4,4)),'prefix'('src_span'(11,54,11,61,7,7),[],'ashford','prefix'('src_span'(11,65,11,70,5,5),[],'pound','prefix'('src_span'(11,74,11,79,5,5),[],'pound','prefix'('src_span'(11,83,11,89,6,6),[],'ticket','val_of'('TICKETS','src_span'(11,93,11,100,7,7)),'src_span'(11,89,11,93,4,4)),'src_span'(11,79,11,83,4,4)),'src_span'(11,70,11,74,4,4)),'src_span'(11,61,11,65,4,4)),'src_span_operator'('no_loc_info_available','src_span'(11,50,11,54,4,4))),'prefix'('src_span'(11,104,11,107,3,3),[],'off','val_of'('MACHINE','src_span'(11,111,11,118,7,7)),'src_span'(11,107,11,111,4,4)),'src_span_operator'('no_loc_info_available','src_span'(11,100,11,104,4,4))),'src_span'(11,1,11,118,117,117)).
'channel'('insert_coin','type'('dotUnitType')).
'channel'('coffee','type'('dotUnitType')).
'channel'('tee','type'('dotUnitType')).
'channel'('ausgeben','type'('dotUnitType')).
'bindval'('Aut','prefix'('src_span'(22,7,22,18,11,11),[],'insert_coin','val_of'('Getraenk','src_span'(22,22,22,30,8,8)),'src_span'(22,18,22,22,4,4)),'src_span'(22,1,22,30,29,29)).
'bindval'('Getraenk','[]'('prefix'('src_span'(24,12,24,18,6,6),[],'coffee','val_of'('GibAus','src_span'(24,22,24,28,6,6)),'src_span'(24,18,24,22,4,4)),'prefix'('src_span'(24,32,24,35,3,3),[],'tee','val_of'('GibAus','src_span'(24,40,24,46,6,6)),'src_span'(24,35,24,40,5,5)),'src_span_operator'('no_loc_info_available','src_span'(24,28,24,32,4,4))),'src_span'(24,1,24,46,45,45)).
'bindval'('GibAus','prefix'('src_span'(26,10,26,18,8,8),[],'ausgeben','val_of'('Aut','src_span'(26,22,26,25,3,3)),'src_span'(26,18,26,22,4,4)),'src_span'(26,1,26,25,24,24)).
'bindval'('A','int'(3),'src_span'(28,1,28,6,5,5)).
'symbol'('Aut','Aut','src_span'(22,1,22,4,3,3),'Ident (Groundrep.)').
'symbol'('A','A','src_span'(28,1,28,2,1,1),'Ident (Groundrep.)').
'symbol'('GibAus','GibAus','src_span'(26,1,26,7,6,6),'Ident (Groundrep.)').
'symbol'('ticket','ticket','src_span'(7,43,7,49,6,6),'Channel').
'symbol'('insert_coin','insert_coin','src_span'(20,9,20,20,11,11),'Channel').
'symbol'('ausgeben','ausgeben','src_span'(20,35,20,43,8,8),'Channel').
'symbol'('off','off','src_span'(7,13,7,16,3,3),'Channel').
'symbol'('ashford','ashford','src_span'(7,27,7,34,7,7),'Channel').
'symbol'('pound','pound','src_span'(7,36,7,41,5,5),'Channel').
'symbol'('Getraenk','Getraenk','src_span'(24,1,24,9,8,8),'Ident (Groundrep.)').
'symbol'('staines','staines','src_span'(7,18,7,25,7,7),'Channel').
'symbol'('tee','tee','src_span'(20,30,20,33,3,3),'Channel').
'symbol'('TICKETS','TICKETS','src_span'(11,1,11,8,7,7),'Ident (Groundrep.)').
'symbol'('coffee','coffee','src_span'(20,22,20,28,6,6),'Channel').
'symbol'('MACHINE','MACHINE','src_span'(9,1,9,8,7,7),'Ident (Groundrep.)').
'symbol'('on','on','src_span'(7,9,7,11,2,2),'Channel').

