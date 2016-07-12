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
'symbol'('ashford','ashford','src_span'(7,27,7,34,7,7),'Channel').
'symbol'('pound','pound','src_span'(7,36,7,41,5,5),'Channel').
'symbol'('staines','staines','src_span'(7,18,7,25,7,7),'Channel').
'symbol'('ticket','ticket','src_span'(7,43,7,49,6,6),'Channel').
'symbol'('TICKETS','TICKETS','src_span'(11,1,11,8,7,7),'Ident (Groundrep.)').
'symbol'('MACHINE','MACHINE','src_span'(9,1,9,8,7,7),'Ident (Groundrep.)').
'symbol'('off','off','src_span'(7,13,7,16,3,3),'Channel').
'symbol'('on','on','src_span'(7,9,7,11,2,2),'Channel').

