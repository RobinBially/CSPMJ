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
'channel'('p1setflag1','type'('dotUnitType')).
'channel'('p1resetflag1','type'('dotUnitType')).
'channel'('p2setflag1','type'('dotUnitType')).
'channel'('p2resetflag1','type'('dotUnitType')).
'channel'('p1gettrueflag1','type'('dotUnitType')).
'channel'('p1getfalseflag1','type'('dotUnitType')).
'channel'('p2gettrueflag1','type'('dotUnitType')).
'channel'('p2getfalseflag1','type'('dotUnitType')).
'channel'('p1setflag2','type'('dotUnitType')).
'channel'('p1resetflag2','type'('dotUnitType')).
'channel'('p2setflag2','type'('dotUnitType')).
'channel'('p2resetflag2','type'('dotUnitType')).
'channel'('p1gettrueflag2','type'('dotUnitType')).
'channel'('p1getfalseflag2','type'('dotUnitType')).
'channel'('p2gettrueflag2','type'('dotUnitType')).
'channel'('p2getfalseflag2','type'('dotUnitType')).
'channel'('p1set1turn','type'('dotUnitType')).
'channel'('p1set2turn','type'('dotUnitType')).
'channel'('p2set1turn','type'('dotUnitType')).
'channel'('p2set2turn','type'('dotUnitType')).
'channel'('p1get1turn','type'('dotUnitType')).
'channel'('p1get2turn','type'('dotUnitType')).
'channel'('p2get1turn','type'('dotUnitType')).
'channel'('p2get2turn','type'('dotUnitType')).
'channel'('p1enter','type'('dotUnitType')).
'channel'('p1critical','type'('dotUnitType')).
'channel'('p1leave','type'('dotUnitType')).
'channel'('p2enter','type'('dotUnitType')).
'channel'('p2critical','type'('dotUnitType')).
'channel'('p2leave','type'('dotUnitType')).
'bindval'('FLAG1','val_of'('FLAG1FALSE','src_span'(18,9,18,19,10,10)),'src_span'(18,1,18,19,18,18)).
'bindval'('FLAG1FALSE','[]'('[]'('[]'('[]'('[]'('prefix'('src_span'(19,15,19,30,15,15),[],'p1getfalseflag1','val_of'('FLAG1FALSE','src_span'(19,34,19,44,10,10)),'src_span'(19,30,19,34,4,4)),'prefix'('src_span'(20,15,20,30,15,15),[],'p2getfalseflag1','val_of'('FLAG1FALSE','src_span'(20,34,20,44,10,10)),'src_span'(20,30,20,34,4,4)),'src_span_operator'('no_loc_info_available','src_span'(19,44,19,60,16,16))),'prefix'('src_span'(21,15,21,25,10,10),[],'p1setflag1','val_of'('FLAG1TRUE','src_span'(21,29,21,38,9,9)),'src_span'(21,25,21,29,4,4)),'src_span_operator'('no_loc_info_available','src_span'(20,44,20,60,16,16))),'prefix'('src_span'(22,15,22,25,10,10),[],'p2setflag1','val_of'('FLAG1TRUE','src_span'(22,29,22,38,9,9)),'src_span'(22,25,22,29,4,4)),'src_span_operator'('no_loc_info_available','src_span'(21,38,21,54,16,16))),'prefix'('src_span'(23,15,23,27,12,12),[],'p1resetflag1','val_of'('FLAG1FALSE','src_span'(23,31,23,41,10,10)),'src_span'(23,27,23,31,4,4)),'src_span_operator'('no_loc_info_available','src_span'(22,38,22,54,16,16))),'prefix'('src_span'(24,15,24,27,12,12),[],'p2resetflag1','val_of'('FLAG1FALSE','src_span'(24,31,24,41,10,10)),'src_span'(24,27,24,31,4,4)),'src_span_operator'('no_loc_info_available','src_span'(23,41,23,57,16,16))),'src_span'(19,1,24,41,40,40)).
'bindval'('FLAG1TRUE','[]'('[]'('[]'('[]'('[]'('prefix'('src_span'(25,15,25,29,14,14),[],'p1gettrueflag1','val_of'('FLAG1TRUE','src_span'(25,33,25,42,9,9)),'src_span'(25,29,25,33,4,4)),'prefix'('src_span'(26,15,26,29,14,14),[],'p2gettrueflag1','val_of'('FLAG1TRUE','src_span'(26,33,26,42,9,9)),'src_span'(26,29,26,33,4,4)),'src_span_operator'('no_loc_info_available','src_span'(25,42,25,58,16,16))),'prefix'('src_span'(27,15,27,27,12,12),[],'p1resetflag1','val_of'('FLAG1FALSE','src_span'(27,31,27,41,10,10)),'src_span'(27,27,27,31,4,4)),'src_span_operator'('no_loc_info_available','src_span'(26,42,26,58,16,16))),'prefix'('src_span'(28,15,28,27,12,12),[],'p2resetflag1','val_of'('FLAG1FALSE','src_span'(28,31,28,41,10,10)),'src_span'(28,27,28,31,4,4)),'src_span_operator'('no_loc_info_available','src_span'(27,41,27,57,16,16))),'prefix'('src_span'(29,15,29,25,10,10),[],'p1setflag1','val_of'('FLAG1TRUE','src_span'(29,29,29,38,9,9)),'src_span'(29,25,29,29,4,4)),'src_span_operator'('no_loc_info_available','src_span'(28,41,28,57,16,16))),'prefix'('src_span'(30,15,30,25,10,10),[],'p2setflag1','val_of'('FLAG1TRUE','src_span'(30,29,30,38,9,9)),'src_span'(30,25,30,29,4,4)),'src_span_operator'('no_loc_info_available','src_span'(29,38,29,54,16,16))),'src_span'(25,1,30,38,37,37)).
'bindval'('FLAG2','val_of'('FLAG2FALSE','src_span'(32,9,32,19,10,10)),'src_span'(32,1,32,19,18,18)).
'bindval'('FLAG2FALSE','[]'('[]'('[]'('[]'('[]'('prefix'('src_span'(33,15,33,30,15,15),[],'p1getfalseflag2','val_of'('FLAG2FALSE','src_span'(33,34,33,44,10,10)),'src_span'(33,30,33,34,4,4)),'prefix'('src_span'(34,15,34,30,15,15),[],'p2getfalseflag2','val_of'('FLAG2FALSE','src_span'(34,34,34,44,10,10)),'src_span'(34,30,34,34,4,4)),'src_span_operator'('no_loc_info_available','src_span'(33,44,33,60,16,16))),'prefix'('src_span'(35,15,35,25,10,10),[],'p1setflag2','val_of'('FLAG2TRUE','src_span'(35,29,35,38,9,9)),'src_span'(35,25,35,29,4,4)),'src_span_operator'('no_loc_info_available','src_span'(34,44,34,60,16,16))),'prefix'('src_span'(36,15,36,25,10,10),[],'p2setflag2','val_of'('FLAG2TRUE','src_span'(36,29,36,38,9,9)),'src_span'(36,25,36,29,4,4)),'src_span_operator'('no_loc_info_available','src_span'(35,38,35,54,16,16))),'prefix'('src_span'(37,15,37,27,12,12),[],'p1resetflag2','val_of'('FLAG2FALSE','src_span'(37,31,37,41,10,10)),'src_span'(37,27,37,31,4,4)),'src_span_operator'('no_loc_info_available','src_span'(36,38,36,54,16,16))),'prefix'('src_span'(38,15,38,27,12,12),[],'p2resetflag2','val_of'('FLAG2FALSE','src_span'(38,31,38,41,10,10)),'src_span'(38,27,38,31,4,4)),'src_span_operator'('no_loc_info_available','src_span'(37,41,37,57,16,16))),'src_span'(33,1,38,41,40,40)).
'bindval'('FLAG2TRUE','[]'('[]'('[]'('[]'('[]'('prefix'('src_span'(39,15,39,29,14,14),[],'p1gettrueflag2','val_of'('FLAG2TRUE','src_span'(39,33,39,42,9,9)),'src_span'(39,29,39,33,4,4)),'prefix'('src_span'(40,15,40,29,14,14),[],'p2gettrueflag2','val_of'('FLAG2TRUE','src_span'(40,33,40,42,9,9)),'src_span'(40,29,40,33,4,4)),'src_span_operator'('no_loc_info_available','src_span'(39,42,39,58,16,16))),'prefix'('src_span'(41,15,41,27,12,12),[],'p1resetflag2','val_of'('FLAG2FALSE','src_span'(41,31,41,41,10,10)),'src_span'(41,27,41,31,4,4)),'src_span_operator'('no_loc_info_available','src_span'(40,42,40,58,16,16))),'prefix'('src_span'(42,15,42,27,12,12),[],'p2resetflag2','val_of'('FLAG2FALSE','src_span'(42,31,42,41,10,10)),'src_span'(42,27,42,31,4,4)),'src_span_operator'('no_loc_info_available','src_span'(41,41,41,57,16,16))),'prefix'('src_span'(43,15,43,25,10,10),[],'p1setflag2','val_of'('FLAG2TRUE','src_span'(43,29,43,38,9,9)),'src_span'(43,25,43,29,4,4)),'src_span_operator'('no_loc_info_available','src_span'(42,41,42,57,16,16))),'prefix'('src_span'(44,15,44,25,10,10),[],'p2setflag2','val_of'('FLAG2TRUE','src_span'(44,29,44,38,9,9)),'src_span'(44,25,44,29,4,4)),'src_span_operator'('no_loc_info_available','src_span'(43,38,43,54,16,16))),'src_span'(39,1,44,38,37,37)).
'bindval'('TURN','val_of'('TURNONE','src_span'(46,8,46,15,7,7)),'src_span'(46,1,46,15,14,14)).
'bindval'('TURNONE','[]'('[]'('[]'('[]'('[]'('prefix'('src_span'(47,12,47,22,10,10),[],'p1get1turn','val_of'('TURNONE','src_span'(47,26,47,33,7,7)),'src_span'(47,22,47,26,4,4)),'prefix'('src_span'(48,12,48,22,10,10),[],'p2get1turn','val_of'('TURNONE','src_span'(48,26,48,33,7,7)),'src_span'(48,22,48,26,4,4)),'src_span_operator'('no_loc_info_available','src_span'(47,33,47,46,13,13))),'prefix'('src_span'(49,12,49,22,10,10),[],'p1set1turn','val_of'('TURNONE','src_span'(49,26,49,33,7,7)),'src_span'(49,22,49,26,4,4)),'src_span_operator'('no_loc_info_available','src_span'(48,33,48,46,13,13))),'prefix'('src_span'(50,12,50,22,10,10),[],'p2set1turn','val_of'('TURNONE','src_span'(50,26,50,33,7,7)),'src_span'(50,22,50,26,4,4)),'src_span_operator'('no_loc_info_available','src_span'(49,33,49,46,13,13))),'prefix'('src_span'(51,12,51,22,10,10),[],'p1set2turn','val_of'('TURNTWO','src_span'(51,26,51,33,7,7)),'src_span'(51,22,51,26,4,4)),'src_span_operator'('no_loc_info_available','src_span'(50,33,50,46,13,13))),'prefix'('src_span'(52,12,52,22,10,10),[],'p2set2turn','val_of'('TURNTWO','src_span'(52,26,52,33,7,7)),'src_span'(52,22,52,26,4,4)),'src_span_operator'('no_loc_info_available','src_span'(51,33,51,46,13,13))),'src_span'(47,1,52,33,32,32)).
'bindval'('TURNTWO','[]'('[]'('[]'('[]'('[]'('prefix'('src_span'(53,12,53,22,10,10),[],'p1get2turn','val_of'('TURNTWO','src_span'(53,26,53,33,7,7)),'src_span'(53,22,53,26,4,4)),'prefix'('src_span'(54,12,54,22,10,10),[],'p2get2turn','val_of'('TURNTWO','src_span'(54,26,54,33,7,7)),'src_span'(54,22,54,26,4,4)),'src_span_operator'('no_loc_info_available','src_span'(53,33,53,46,13,13))),'prefix'('src_span'(55,12,55,22,10,10),[],'p1set1turn','val_of'('TURNONE','src_span'(55,26,55,33,7,7)),'src_span'(55,22,55,26,4,4)),'src_span_operator'('no_loc_info_available','src_span'(54,33,54,46,13,13))),'prefix'('src_span'(56,12,56,22,10,10),[],'p2set1turn','val_of'('TURNONE','src_span'(56,26,56,33,7,7)),'src_span'(56,22,56,26,4,4)),'src_span_operator'('no_loc_info_available','src_span'(55,33,55,46,13,13))),'prefix'('src_span'(57,12,57,22,10,10),[],'p1set2turn','val_of'('TURNTWO','src_span'(57,26,57,33,7,7)),'src_span'(57,22,57,26,4,4)),'src_span_operator'('no_loc_info_available','src_span'(56,33,56,46,13,13))),'prefix'('src_span'(58,12,58,22,10,10),[],'p2set2turn','val_of'('TURNTWO','src_span'(58,26,58,33,7,7)),'src_span'(58,22,58,26,4,4)),'src_span_operator'('no_loc_info_available','src_span'(57,33,57,46,13,13))),'src_span'(53,1,58,33,32,32)).
'bindval'('P1','prefix'('src_span'(60,6,60,16,10,10),[],'p1setflag1','prefix'('src_span'(60,20,60,30,10,10),[],'p1set2turn','val_of'('P1WAIT','src_span'(60,34,60,40,6,6)),'src_span'(60,30,60,34,4,4)),'src_span'(60,16,60,20,4,4)),'src_span'(60,1,60,40,39,39)).
'bindval'('P1WAIT','[]'('prefix'('src_span'(62,11,62,25,14,14),[],'p1gettrueflag2','tupleExp'(['[]'('prefix'('src_span'(62,30,62,40,10,10),[],'p1get2turn','val_of'('P1WAIT','src_span'(62,44,62,50,6,6)),'src_span'(62,40,62,44,4,4)),'prefix'('src_span'(63,32,63,42,10,10),[],'p1get1turn','val_of'('P1ENTER','src_span'(63,46,63,53,7,7)),'src_span'(63,42,63,46,4,4)),'src_span_operator'('no_loc_info_available','src_span'(62,50,62,83,33,33)))]),'src_span'(62,25,62,29,4,4)),'prefix'('src_span'(64,11,64,26,15,15),[],'p1getfalseflag2','val_of'('P1ENTER','src_span'(64,30,64,37,7,7)),'src_span'(64,26,64,30,4,4)),'src_span_operator'('no_loc_info_available','src_span'(63,54,63,66,12,12))),'src_span'(62,1,64,37,36,36)).
'bindval'('P1ENTER','prefix'('src_span'(66,11,66,18,7,7),[],'p1enter','prefix'('src_span'(66,22,66,32,10,10),[],'p1critical','prefix'('src_span'(66,36,66,43,7,7),[],'p1leave','prefix'('src_span'(66,47,66,59,12,12),[],'p1resetflag1','val_of'('P1','src_span'(66,63,66,65,2,2)),'src_span'(66,59,66,63,4,4)),'src_span'(66,43,66,47,4,4)),'src_span'(66,32,66,36,4,4)),'src_span'(66,18,66,22,4,4)),'src_span'(66,1,66,65,64,64)).
'bindval'('P2','prefix'('src_span'(68,6,68,16,10,10),[],'p2setflag2','prefix'('src_span'(68,20,68,30,10,10),[],'p2set1turn','val_of'('P2WAIT','src_span'(68,34,68,40,6,6)),'src_span'(68,30,68,34,4,4)),'src_span'(68,16,68,20,4,4)),'src_span'(68,1,68,40,39,39)).
'bindval'('P2WAIT','[]'('prefix'('src_span'(70,11,70,25,14,14),[],'p2gettrueflag1','tupleExp'(['[]'('prefix'('src_span'(70,30,70,40,10,10),[],'p2get1turn','val_of'('P2WAIT','src_span'(70,44,70,50,6,6)),'src_span'(70,40,70,44,4,4)),'prefix'('src_span'(71,32,71,42,10,10),[],'p2get2turn','val_of'('P2ENTER','src_span'(71,46,71,53,7,7)),'src_span'(71,42,71,46,4,4)),'src_span_operator'('no_loc_info_available','src_span'(70,50,70,83,33,33)))]),'src_span'(70,25,70,29,4,4)),'prefix'('src_span'(72,11,72,26,15,15),[],'p2getfalseflag1','val_of'('P2ENTER','src_span'(72,30,72,37,7,7)),'src_span'(72,26,72,30,4,4)),'src_span_operator'('no_loc_info_available','src_span'(71,54,71,66,12,12))),'src_span'(70,1,72,37,36,36)).
'bindval'('P2ENTER','prefix'('src_span'(74,11,74,18,7,7),[],'p2enter','prefix'('src_span'(74,22,74,32,10,10),[],'p2critical','prefix'('src_span'(74,36,74,43,7,7),[],'p2leave','prefix'('src_span'(74,47,74,59,12,12),[],'p2resetflag2','val_of'('P2','src_span'(74,63,74,65,2,2)),'src_span'(74,59,74,63,4,4)),'src_span'(74,43,74,47,4,4)),'src_span'(74,32,74,36,4,4)),'src_span'(74,18,74,22,4,4)),'src_span'(74,1,74,65,64,64)).
'bindval'('aP1','p1setflag1','p1resetflag1','p1gettrueflag1','p1getfalseflag1','p1setflag2','p1resetflag2','p1gettrueflag2','p1getfalseflag2','p1set1turn','p1set2turn','p1get1turn','p1get2turn','p1enter','p1critical','p1leave','src_span'(76,1,79,39,38,38)).
'bindval'('aP2','p2setflag1','p2resetflag1','p2gettrueflag1','p2getfalseflag1','p2setflag2','p2resetflag2','p2gettrueflag2','p2getfalseflag2','p2set1turn','p2set2turn','p2get1turn','p2get2turn','p2enter','p2critical','p2leave','src_span'(81,1,84,39,38,38)).
'bindval'('aF1','p1setflag1','p1resetflag1','p1gettrueflag1','p1getfalseflag1','p2setflag1','p2resetflag1','p2gettrueflag1','p2getfalseflag1','src_span'(86,1,87,67,66,66)).
'bindval'('aF2','p1setflag2','p1resetflag2','p1gettrueflag2','p1getfalseflag2','p2setflag2','p2resetflag2','p2gettrueflag2','p2getfalseflag2','src_span'(89,1,90,67,66,66)).
'bindval'('aT','p1set1turn','p1set2turn','p1get1turn','p1get2turn','p2set1turn','p2set2turn','p2get1turn','p2get2turn','src_span'(92,1,93,56,55,55)).
'bindval'('PROCS','aParallel'('val_of'('P1','src_span'(95,9,95,11,2,2)),'val_of'('aP1','src_span'(95,14,95,17,3,3)),'val_of'('aP2','src_span'(95,21,95,24,3,3)),'val_of'('P2','src_span'(95,27,95,29,2,2)),'src_span'(95,11,95,14,3,3)),'src_span'(95,1,95,29,28,28)).
'bindval'('FLAGS','aParallel'('val_of'('FLAG1','src_span'(97,9,97,14,5,5)),'val_of'('aF1','src_span'(97,17,97,20,3,3)),'val_of'('aF2','src_span'(97,24,97,27,3,3)),'val_of'('FLAG2','src_span'(97,30,97,35,5,5)),'src_span'(97,14,97,17,3,3)),'src_span'(97,1,97,35,34,34)).
'bindval'('VARS','aParallel'('val_of'('FLAGS','src_span'(99,8,99,13,5,5)),'agent_call'('src_span'(99,16,99,30,14,14),'union',['val_of'('aF1','src_span'(99,22,99,25,3,3)),'val_of'('aF2','src_span'(99,26,99,29,3,3))]),'val_of'('aT','src_span'(99,34,99,36,2,2)),'val_of'('TURN','src_span'(99,39,99,43,4,4)),'src_span'(99,13,99,16,3,3)),'src_span'(99,1,99,43,42,42)).
'bindval'('SYSTEM','aParallel'('val_of'('PROCS','src_span'(101,10,101,15,5,5)),'agent_call'('src_span'(101,18,101,32,14,14),'union',['val_of'('aP1','src_span'(101,24,101,27,3,3)),'val_of'('aP2','src_span'(101,28,101,31,3,3))]),'agent_call'('src_span'(101,36,101,60,24,24),'union',['agent_call'('src_span'(101,42,101,56,14,14),'union',['val_of'('aF1','src_span'(101,48,101,51,3,3)),'val_of'('aF2','src_span'(101,52,101,55,3,3))]),'val_of'('aT','src_span'(101,57,101,59,2,2))]),'val_of'('VARS','src_span'(101,63,101,67,4,4)),'src_span'(101,15,101,18,3,3)),'src_span'(101,1,101,67,66,66)).
'symbol'('SYSTEM','SYSTEM','src_span'(101,1,101,7,6,6),'Ident (Groundrep.)').
'symbol'('aP2','aP2','src_span'(81,1,81,4,3,3),'Ident (Groundrep.)').
'symbol'('aP1','aP1','src_span'(76,1,76,4,3,3),'Ident (Groundrep.)').
'symbol'('VARS','VARS','src_span'(99,1,99,5,4,4),'Ident (Groundrep.)').
'symbol'('P1WAIT','P1WAIT','src_span'(62,1,62,7,6,6),'Ident (Groundrep.)').
'symbol'('p2get2turn','p2get2turn','src_span'(14,45,14,55,10,10),'Channel').
'symbol'('p1getfalseflag1','p1getfalseflag1','src_span'(10,25,10,40,15,15),'Channel').
'symbol'('p2get1turn','p2get1turn','src_span'(14,33,14,43,10,10),'Channel').
'symbol'('aT','aT','src_span'(92,1,92,3,2,2),'Ident (Groundrep.)').
'symbol'('TURNONE','TURNONE','src_span'(47,1,47,8,7,7),'Ident (Groundrep.)').
'symbol'('p2set1turn','p2set1turn','src_span'(13,33,13,43,10,10),'Channel').
'symbol'('p2leave','p2leave','src_span'(16,30,16,37,7,7),'Channel').
'symbol'('p2enter','p2enter','src_span'(16,9,16,16,7,7),'Channel').
'symbol'('FLAGS','FLAGS','src_span'(97,1,97,6,5,5),'Ident (Groundrep.)').
'symbol'('p1setflag2','p1setflag2','src_span'(11,9,11,19,10,10),'Channel').
'symbol'('p1setflag1','p1setflag1','src_span'(9,9,9,19,10,10),'Channel').
'symbol'('p1getfalseflag2','p1getfalseflag2','src_span'(12,25,12,40,15,15),'Channel').
'symbol'('P2ENTER','P2ENTER','src_span'(74,1,74,8,7,7),'Ident (Groundrep.)').
'symbol'('p1resetflag1','p1resetflag1','src_span'(9,21,9,33,12,12),'Channel').
'symbol'('p1resetflag2','p1resetflag2','src_span'(11,21,11,33,12,12),'Channel').
'symbol'('p2set2turn','p2set2turn','src_span'(13,45,13,55,10,10),'Channel').
'symbol'('p1enter','p1enter','src_span'(15,9,15,16,7,7),'Channel').
'symbol'('TURNTWO','TURNTWO','src_span'(53,1,53,8,7,7),'Ident (Groundrep.)').
'symbol'('PROCS','PROCS','src_span'(95,1,95,6,5,5),'Ident (Groundrep.)').
'symbol'('FLAG1','FLAG1','src_span'(18,1,18,6,5,5),'Ident (Groundrep.)').
'symbol'('p2critical','p2critical','src_span'(16,18,16,28,10,10),'Channel').
'symbol'('FLAG2','FLAG2','src_span'(32,1,32,6,5,5),'Ident (Groundrep.)').
'symbol'('P2WAIT','P2WAIT','src_span'(70,1,70,7,6,6),'Ident (Groundrep.)').
'symbol'('FLAG2TRUE','FLAG2TRUE','src_span'(39,1,39,10,9,9),'Ident (Groundrep.)').
'symbol'('FLAG2FALSE','FLAG2FALSE','src_span'(33,1,33,11,10,10),'Ident (Groundrep.)').
'symbol'('p1get2turn','p1get2turn','src_span'(14,21,14,31,10,10),'Channel').
'symbol'('aF2','aF2','src_span'(89,1,89,4,3,3),'Ident (Groundrep.)').
'symbol'('aF1','aF1','src_span'(86,1,86,4,3,3),'Ident (Groundrep.)').
'symbol'('p2resetflag1','p2resetflag1','src_span'(9,47,9,59,12,12),'Channel').
'symbol'('p2resetflag2','p2resetflag2','src_span'(11,47,11,59,12,12),'Channel').
'symbol'('p2getfalseflag1','p2getfalseflag1','src_span'(10,58,10,73,15,15),'Channel').
'symbol'('p2getfalseflag2','p2getfalseflag2','src_span'(12,58,12,73,15,15),'Channel').
'symbol'('FLAG1TRUE','FLAG1TRUE','src_span'(25,1,25,10,9,9),'Ident (Groundrep.)').
'symbol'('p2setflag1','p2setflag1','src_span'(9,35,9,45,10,10),'Channel').
'symbol'('TURN','TURN','src_span'(46,1,46,5,4,4),'Ident (Groundrep.)').
'symbol'('p2setflag2','p2setflag2','src_span'(11,35,11,45,10,10),'Channel').
'symbol'('p1get1turn','p1get1turn','src_span'(14,9,14,19,10,10),'Channel').
'symbol'('FLAG1FALSE','FLAG1FALSE','src_span'(19,1,19,11,10,10),'Ident (Groundrep.)').
'symbol'('p1set1turn','p1set1turn','src_span'(13,9,13,19,10,10),'Channel').
'symbol'('p1gettrueflag1','p1gettrueflag1','src_span'(10,9,10,23,14,14),'Channel').
'symbol'('p1gettrueflag2','p1gettrueflag2','src_span'(12,9,12,23,14,14),'Channel').
'symbol'('p2gettrueflag2','p2gettrueflag2','src_span'(12,42,12,56,14,14),'Channel').
'symbol'('P1','P1','src_span'(60,1,60,3,2,2),'Ident (Groundrep.)').
'symbol'('P2','P2','src_span'(68,1,68,3,2,2),'Ident (Groundrep.)').
'symbol'('p2gettrueflag1','p2gettrueflag1','src_span'(10,42,10,56,14,14),'Channel').
'symbol'('p1set2turn','p1set2turn','src_span'(13,21,13,31,10,10),'Channel').
'symbol'('p1leave','p1leave','src_span'(15,30,15,37,7,7),'Channel').
'symbol'('p1critical','p1critical','src_span'(15,18,15,28,10,10),'Channel').
'symbol'('P1ENTER','P1ENTER','src_span'(66,1,66,8,7,7),'Ident (Groundrep.)').

