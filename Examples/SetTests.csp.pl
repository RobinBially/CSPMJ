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
'channel'('out','type'('dotTupleType'(['intType']))).
'channel'('rem','type'('dotTupleType'(['intType']))).
'agent'('OutCard'(_S,_i),'[]'('ifte'('agent_call'('src_span'(5,19,5,27,8,8),'empty',[_S]),'prefix'('src_span'(5,33,5,36,3,3),['out'('int'(1))],'out','agent_call'('src_span'(5,43,5,59,16,16),'OutCard',[_i,_i,'int'(1)]),'src_span'(5,39,5,43,4,4)),'prefix'('src_span'(6,22,6,25,3,3),['out'('agent_call'('src_span'(6,26,6,33,7,7),'card',['src_span'(6,31,6,32,1,1),[],_S]))],'out','agent_call'('src_span'(6,37,6,62,25,25),'OutCard',['agent_call'('src_span'(6,45,6,57,12,12),'union',[_S,_i]),_i,'int'(1)]),'src_span'(6,33,6,37,4,4)),'src_span'(5,16,5,18,2,2),'src_span'(5,27,5,33,6,6),'src_span'(5,59,5,82,23,23)),'prefix'('src_span'(7,22,7,25,3,3),['out'(_i,'int'(1))],'rem','agent_call'('src_span'(7,33,7,63,30,30),'OutCard',['agent_call'('src_span'(7,41,7,58,17,17),'inter',[_S,'int'(1),_i,'int'(1)]),_i,'int'(1)]),'src_span'(7,29,7,33,4,4)),'src_span_operator'('no_loc_info_available','src_span'(6,62,6,85,23,23))),'src_span'(5,1,7,63,62,62)).
'bindval'('TestOutCard','agent_call'('src_span'(11,15,11,28,13,13),'OutCard',['int'(1)]),'src_span'(11,1,11,28,27,27)).
'agent'('OutCard2'(_S2,_i2),'ifte'('agent_call'('src_span'(13,20,13,28,8,8),'empty',[_S2]),'prefix'('src_span'(13,34,13,37,3,3),['out'('int'(1))],'out','agent_call'('src_span'(13,44,13,60,16,16),'OutCard',[_i2,_i2,'int'(1)]),'src_span'(13,40,13,44,4,4)),'prefix'('src_span'(14,22,14,25,3,3),['out'(_i2)],'out','stop'('src_span'(14,31,14,35,4,4)),'src_span'(14,27,14,31,4,4)),'src_span'(13,17,13,19,2,2),'src_span'(13,28,13,34,6,6),'src_span'(13,60,13,83,23,23)),'src_span'(13,1,14,35,34,34)).
'bindval'('Test2','agent_call'('src_span'(17,9,17,24,15,15),'OutCard2',['int'(3),'int'(4)]),'src_span'(17,1,17,24,23,23)).
'bindval'('Test3','agent_call'('src_span'(18,9,18,23,14,14),'OutCard2',['int'(1)]),'src_span'(18,1,18,23,22,22)).
'agent'('csubset'(_x,_y),'ifte'('agent_call'('src_span'(21,19,21,29,10,10),'union',[_x,_y]),_y,'int'(1),'int'(0),'src_span'(21,16,21,18,2,2),'src_span'(21,32,21,38,6,6),'src_span'(21,39,21,45,6,6)),'src_span'(21,1,21,46,45,45)).
'bindval'('Test4','prefix'('src_span'(23,9,23,12,3,3),['out'('agent_call'('src_span'(23,13,23,33,20,20),'csubset',['src_span'(23,21,23,24,3,3),[],'src_span'(23,22,23,23,1,1),[],'int'(1),'src_span'(23,25,23,32,7,7),[],'src_span'(23,26,23,27,1,1),[],'int'(1),'src_span'(23,28,23,29,1,1),[],'int'(2),'src_span'(23,30,23,31,1,1),[],'int'(3)]))],'out','prefix'('src_span'(23,37,23,40,3,3),['out'('agent_call'('src_span'(23,41,23,57,16,16),'csubset',['src_span'(23,49,23,52,3,3),[],'src_span'(23,50,23,51,1,1),[],'int'(2),'src_span'(23,53,23,56,3,3),[],'src_span'(23,54,23,55,1,1),[],'int'(3)]))],'out','prefix'('src_span'(23,61,23,64,3,3),['out'('agent_call'('src_span'(23,65,23,85,20,20),'csubset',['src_span'(23,73,23,78,5,5),[],'src_span'(23,74,23,75,1,1),[],'int'(3),'src_span'(23,76,23,77,1,1),[],'int'(2),'src_span'(23,79,23,84,5,5),[],'src_span'(23,80,23,81,1,1),[],'int'(2),'src_span'(23,82,23,83,1,1),[],'int'(3)]))],'out','prefix'('src_span'(24,9,24,12,3,3),['out'('agent_call'('src_span'(24,13,24,38,25,25),'csubset',['src_span'(24,21,24,27,6,6),[],'int'(1),'int'(3),'src_span'(24,28,24,37,9,9),[],'src_span'(24,29,24,30,1,1),[],'int'(1),'src_span'(24,31,24,32,1,1),[],'int'(3),'src_span'(24,33,24,34,1,1),[],'int'(4),'src_span'(24,35,24,36,1,1),[],'int'(2)]))],'out','prefix'('src_span'(24,42,24,45,3,3),['out'('agent_call'('src_span'(24,46,24,71,25,25),'csubset',['src_span'(24,54,24,60,6,6),[],'int'(1),'int'(3),'src_span'(24,61,24,70,9,9),[],'src_span'(24,62,24,63,1,1),[],'int'(1),'src_span'(24,64,24,65,1,1),[],'int'(2),'src_span'(24,66,24,67,1,1),[],'int'(4),'src_span'(24,68,24,69,1,1),[],'int'(2)]))],'out','prefix'('src_span'(25,9,25,12,3,3),['out'('agent_call'('src_span'(25,13,25,35,22,22),'csubset',['src_span'(25,21,25,28,7,7),[],'src_span'(25,22,25,23,1,1),[],'int'(3),'src_span'(25,24,25,25,1,1),[],'int'(2),'src_span'(25,26,25,27,1,1),[],'int'(2),'src_span'(25,29,25,34,5,5),[],'src_span'(25,30,25,31,1,1),[],'int'(2),'src_span'(25,32,25,33,1,1),[],'int'(3)]))],'out','skip'('src_span'(25,39,25,43,4,4)),'src_span'(25,35,25,39,4,4)),'src_span'(24,71,24,84,13,13)),'src_span'(24,38,24,42,4,4)),'src_span'(23,85,23,98,13,13)),'src_span'(23,57,23,61,4,4)),'src_span'(23,33,23,37,4,4)),'src_span'(23,1,25,43,42,42)).
'bindval'('ReplicationTest','repChoice'(['comprehensionGenerator'('int'(1),'int'(2),'int'(3))],'prefix'('src_span'(28,34,28,37,3,3),['out'],'out','prefix'('src_span'(28,43,28,46,3,3),['out'],'rem','skip'('src_span'(28,52,28,56,4,4)),'src_span'(28,48,28,52,4,4)),'src_span'(28,39,28,43,4,4)),'src_span'(28,22,28,31,9,9)),'src_span'(28,1,28,56,55,55)).
'bindval'('ReplicationTest2','repInterleave'(['comprehensionGenerator'('int'(1),'int'(2),'int'(3))],'prefix'('src_span'(29,36,29,39,3,3),['out'],'out','prefix'('src_span'(29,45,29,48,3,3),['out'],'rem','skip'('src_span'(29,54,29,58,4,4)),'src_span'(29,50,29,54,4,4)),'src_span'(29,41,29,45,4,4)),'src_span'(29,24,29,33,9,9)),'src_span'(29,1,29,58,57,57)).
'bindval'('ReplicationTest3','repSequence'(['comprehensionGenerator'('int'(1),'int'(2),'int'(3))],'prefix'('src_span'(30,34,30,37,3,3),['out'],'out','prefix'('src_span'(30,43,30,46,3,3),['out'],'rem','skip'('src_span'(30,52,30,56,4,4)),'src_span'(30,48,30,52,4,4)),'src_span'(30,39,30,43,4,4)),'src_span'(30,22,30,31,9,9)),'src_span'(30,1,30,56,55,55)).
'bindval'('ReplicationTest4','repInternalChoice'(['comprehensionGenerator'('int'(1),'int'(2),'int'(3))],'prefix'('src_span'(31,36,31,39,3,3),['out'],'out','prefix'('src_span'(31,45,31,48,3,3),['out'],'rem','skip'('src_span'(31,54,31,58,4,4)),'src_span'(31,50,31,54,4,4)),'src_span'(31,41,31,45,4,4)),'src_span'(31,24,31,33,9,9)),'src_span'(31,1,31,58,57,57)).
'agent'('Do'(_x2),_x2,'src_span'(33,1,33,10,9,9)).
'bindval'('ReplicationTest5','agent_call'('src_span'(34,20,34,61,41,41),'Do',['repChoice'(['comprehensionGenerator'('int'(1),'int'(2),'int'(3))],'prefix'('src_span'(34,38,34,41,3,3),['out'],'out','prefix'('src_span'(34,47,34,50,3,3),['out'],'rem','skip'('src_span'(34,56,34,60,4,4)),'src_span'(34,52,34,56,4,4)),'src_span'(34,43,34,47,4,4)),'src_span'(34,26,34,35,9,9))]),'src_span'(34,1,34,61,60,60)).
'agent'('ReplicationTest6C'(_z),'repInterleave'(['comprehensionGenerator'('int'(1),'int'(2),'int'(3))],'tupleExp'(['let'(['agent'('f'(_y2),_z,_y2,'src_span'(37,45,37,57,12,12))],'prefix'('src_span'(38,43,38,46,3,3),['inGuard'(_v,'int'(1),'int'(3))],'rem','prefix'('src_span'(38,59,38,62,3,3),['out'('agent_call'('src_span'(38,63,38,67,4,4),'f',['src_span'(38,65,38,66,1,1),[],_v]))],'out','skip'('src_span'(38,71,38,75,4,4)),'src_span'(38,67,38,71,4,4)),'src_span'(38,55,38,59,4,4)))]),'src_span'(37,28,37,37,9,9)),'src_span'(37,1,38,76,75,75)).
'bindval'('ReplicationTest6','agent_call'('src_span'(39,20,39,40,20,20),'ReplicationTest6C',['int'(2)]),'src_span'(39,1,39,40,39,39)).
'bindval'('Segments','int'(5),'src_span'(42,1,42,13,12,12)).
'bindval'('LastSeg','val_of'('Segments','src_span'(43,11,43,19,8,8)),'int'(1),'src_span'(43,1,43,23,22,22)).
'bindval'('TRACKS','int'(0),'val_of'('LastSeg','src_span'(44,14,44,21,7,7)),'src_span'(44,1,44,22,21,21)).
'bindval'('REALTRACKS','int'(1),'val_of'('LastSeg','src_span'(45,18,45,25,7,7)),'src_span'(45,1,45,26,25,25)).
'dataTypeDef'('TRAINS',['constructor'('Thomas'),'constructor'('Gordon')]).
'channel'('enter','type'('dotTupleType'(['val_of'('TRACKS','src_span'(51,24,51,30,6,6)),'val_of'('TRAINS','src_span'(51,31,51,37,6,6))]))).
'channel'('leave','type'('dotTupleType'(['val_of'('TRACKS','src_span'(51,24,51,30,6,6)),'val_of'('TRAINS','src_span'(51,31,51,37,6,6))]))).
'channel'('sensor_in','type'('dotUnitType')).
'channel'('sensor_out','type'('dotUnitType')).
'bindval'('GateSeg','int'(2),'src_span'(55,1,55,12,11,11)).
'bindval'('Tracks','repInterleave'(['comprehensionGenerator'('val_of'('REALTRACKS','src_span'(57,18,57,28,10,10)))],'agent_call'('src_span'(57,31,57,39,8,8),'Track',[]),'src_span'(57,14,57,28,14,14)),'src_span'(57,1,57,39,38,38)).
'agent'('Track'(_j),'let'(['bindval'('Empty','prefix'('src_span'(61,15,61,22,7,7),['in'(_A)],'dotTuple'(['enter',_j]),'tupleExp'(['ifte'(_j,'int'(1),'prefix'('src_span'(61,42,61,51,9,9),[],'sensor_in','agent_call'('src_span'(61,55,61,62,7,7),'Full',[_A]),'src_span'(61,51,61,55,4,4)),'agent_call'('src_span'(61,68,61,75,7,7),'Full',[_A]),'src_span'(61,29,61,31,2,2),'src_span'(61,36,61,42,6,6),'src_span'(61,62,61,68,6,6))]),'src_span'(61,24,61,28,4,4)),'src_span'(61,5,61,76,71,71)),'agent'('Full'(_A2),'prefix'('src_span'(62,15,62,24,9,9),[],'dotTuple'(['leave','j',_A2]),'tupleExp'(['ifte'('j','val_of'('GateSeg','src_span'(62,35,62,42,7,7)),'prefix'('src_span'(62,48,62,58,10,10),[],'sensor_out','val_of'('Empty','src_span'(62,62,62,67,5,5)),'src_span'(62,58,62,62,4,4)),'val_of'('Empty','src_span'(62,73,62,78,5,5)),'src_span'(62,29,62,31,2,2),'src_span'(62,42,62,48,6,6),'src_span'(62,67,62,73,6,6))]),'src_span'(62,24,62,28,4,4)),'src_span'(62,5,62,79,74,74))],'val_of'('Empty','src_span'(63,10,63,15,5,5))),'src_span'(59,1,63,15,14,14)).
'channel'('prime_enter','type'('dotTupleType'(['val_of'('TRACKS','src_span'(65,28,65,34,6,6)),'int'(2),'int'(3),'int'(5),'int'(7),'val_of'('TRAINS','src_span'(65,46,65,52,6,6))]))).
'bindval'('TestEnumEnter','prefix'('src_span'(66,17,66,28,11,11),['in'(_track),'in'(_train)],'prime_enter','prefix'('src_span'(66,44,66,49,5,5),['out'(_track),'out'(_train)],'leave','val_of'('TestEnumEnter','src_span'(66,65,66,78,13,13)),'src_span'(66,61,66,65,4,4)),'src_span'(66,40,66,44,4,4)),'src_span'(66,1,66,78,77,77)).
'bindval'('MAIN',';'('val_of'('ReplicationTest6','src_span'(74,8,74,24,16,16)),'val_of'('Tracks','src_span'(74,27,74,33,6,6)),'src_span_operator'('no_loc_info_available','src_span'(74,24,74,27,3,3))),'src_span'(74,1,74,33,32,32)).
'bindval'('TestDiff','repChoice'(['comprehensionGenerator'('agent_call'('src_span'(76,17,76,36,19,19),'diff',['int'(1),'int'(5),'int'(3),'int'(2),'int'(1)]))],'prefix'('src_span'(76,39,76,42,3,3),['out'],'out','skip'('src_span'(76,48,76,52,4,4)),'src_span'(76,44,76,48,4,4)),'src_span'(76,15,76,36,21,21)),'src_span'(76,1,76,52,51,51)).
'bindval'('SetComp',['comprehensionGenerator'('int'(1),'int'(3),'int'(5)),'comprehensionGenerator'('int'(1),'int'(2),'int'(4))],'src_span'(79,1,79,43,42,42)).
'bindval'('SetComp2',['comprehensionGenerator'('int'(1),'int'(3),'int'(5)),'comprehensionGenerator'('int'(1),'int'(2),'int'(4))],'src_span'(80,1,80,55,54,54)).
'bindval'('SetComp3',['comprehensionGenerator'('int'(1),'int'(3),'int'(5)),'comprehensionGenerator'('int'(1),'int'(2),'int'(4)),'comprehensionGuard'],'src_span'(81,1,81,49,48,48)).
'bindval'('TestHideUnion1','\'('repChoice'(['comprehensionGenerator'('int'(1),'int'(2),'int'(3),'int'(4),'int'(5))],'prefix'('src_span'(84,36,84,39,3,3),['out'],'out','skip'('src_span'(84,45,84,49,4,4)),'src_span'(84,41,84,45,4,4)),'src_span'(84,20,84,33,13,13)),'agent_call'('src_span'(84,52,84,81,29,29),'union',['dotTuple'(['out','int'(1)]),'dotTuple'(['out','int'(2)]),'dotTuple'(['out','int'(4)])]),'src_span_operator'('no_loc_info_available','src_span'(84,49,84,52,3,3))),'src_span'(84,1,84,81,80,80)).
'bindval'('S1','dotTuple'(['out','int'(1)]),'dotTuple'(['out','int'(2)]),'src_span'(85,1,85,20,19,19)).
'bindval'('S2','dotTuple'(['out','int'(4)]),'src_span'(86,1,86,13,12,12)).
'bindval'('TestHideUnion2','\'('repChoice'(['comprehensionGenerator'('int'(1),'int'(2),'int'(3),'int'(4),'int'(5))],'prefix'('src_span'(87,36,87,39,3,3),['out'],'out','skip'('src_span'(87,45,87,49,4,4)),'src_span'(87,41,87,45,4,4)),'src_span'(87,20,87,33,13,13)),'agent_call'('src_span'(87,52,87,64,12,12),'union',['val_of'('S1','src_span'(87,58,87,60,2,2)),'val_of'('S2','src_span'(87,61,87,63,2,2))]),'src_span_operator'('no_loc_info_available','src_span'(87,49,87,52,3,3))),'src_span'(87,1,87,64,63,63)).
'bindval'('PROB_TEST_TRACE','prefix'('src_span'(90,19,90,24,5,5),[],'dotTuple'(['rem','int'(2)]),'prefix'('src_span'(90,28,90,33,5,5),[],'dotTuple'(['out','int'(4)]),'prefix'('src_span'(90,37,90,42,5,5),[],'dotTuple'(['rem','int'(3)]),'prefix'('src_span'(90,46,90,52,6,6),[],'dotTuple'(['out','int'(12)]),'prefix'('src_span'(90,56,90,61,5,5),[],'dotTuple'(['rem','int'(3)]),'prefix'('src_span'(90,65,90,71,6,6),[],'dotTuple'(['out','int'(18)]),'prefix'('src_span'(90,75,90,89,14,14),[],'dotTuple'(['enter','int'(1),'Gordon']),'prefix'('src_span'(90,93,90,102,9,9),[],'sensor_in','prefix'('src_span'(90,106,90,120,14,14),[],'dotTuple'(['leave','int'(1),'Gordon']),'prefix'('src_span'(90,124,90,138,14,14),[],'dotTuple'(['enter','int'(2),'Thomas']),'prefix'('src_span'(90,142,90,156,14,14),[],'dotTuple'(['enter','int'(3),'Gordon']),'prefix'('src_span'(90,160,90,174,14,14),[],'dotTuple'(['enter','int'(4),'Gordon']),'prefix'('src_span'(90,178,90,192,14,14),[],'dotTuple'(['enter','int'(1),'Thomas']),'prefix'('src_span'(90,196,90,205,9,9),[],'sensor_in','prefix'('src_span'(90,209,90,223,14,14),[],'dotTuple'(['leave','int'(2),'Thomas']),'prefix'('src_span'(90,227,90,237,10,10),[],'sensor_out','prefix'('src_span'(90,241,90,255,14,14),[],'dotTuple'(['leave','int'(3),'Gordon']),'prefix'('src_span'(90,259,90,273,14,14),[],'dotTuple'(['leave','int'(4),'Gordon']),'stop'('src_span'(90,277,90,281,4,4)),'src_span'(90,273,90,277,4,4)),'src_span'(90,255,90,259,4,4)),'src_span'(90,237,90,241,4,4)),'src_span'(90,223,90,227,4,4)),'src_span'(90,205,90,209,4,4)),'src_span'(90,192,90,196,4,4)),'src_span'(90,174,90,178,4,4)),'src_span'(90,156,90,160,4,4)),'src_span'(90,138,90,142,4,4)),'src_span'(90,120,90,124,4,4)),'src_span'(90,102,90,106,4,4)),'src_span'(90,89,90,93,4,4)),'src_span'(90,71,90,75,4,4)),'src_span'(90,61,90,65,4,4)),'src_span'(90,52,90,56,4,4)),'src_span'(90,42,90,46,4,4)),'src_span'(90,33,90,37,4,4)),'src_span'(90,24,90,28,4,4)),'src_span'(90,1,90,281,280,280)).
'val_of'('MAIN','src_span'(92,8,92,12,4,4)),'val_of'('PROB_TEST_TRACE','src_span'(92,17,92,32,15,15)).
'symbol'('ReplicationTest6C','ReplicationTest6C','src_span'(37,1,37,18,17,17),'Function or Process').
'symbol'('Segments','Segments','src_span'(42,1,42,9,8,8),'Ident (Groundrep.)').
'symbol'('Test4','Test4','src_span'(23,1,23,6,5,5),'Ident (Groundrep.)').
'symbol'('Test3','Test3','src_span'(18,1,18,6,5,5),'Ident (Groundrep.)').
'symbol'('Full','Full','src_span'(62,5,62,9,4,4),'Function or Process').
'symbol'('Test2','Test2','src_span'(17,1,17,6,5,5),'Ident (Groundrep.)').
'symbol'('OutCard2','OutCard2','src_span'(13,1,13,9,8,8),'Function or Process').
'symbol'('TRAINS','TRAINS','src_span'(47,10,47,16,6,6),'Datatype').
'symbol'('leave','leave','src_span'(51,16,51,21,5,5),'Channel').
'symbol'('GateSeg','GateSeg','src_span'(55,1,55,8,7,7),'Ident (Groundrep.)').
'symbol'('MAIN','MAIN','src_span'(74,1,74,5,4,4),'Ident (Groundrep.)').
'symbol'('rem','rem','src_span'(3,13,3,16,3,3),'Channel').
'symbol'('ReplicationTest','ReplicationTest','src_span'(28,1,28,16,15,15),'Ident (Groundrep.)').
'symbol'('enter','enter','src_span'(51,9,51,14,5,5),'Channel').
'symbol'('train','train','src_span'(66,35,66,40,5,5),'Ident (Prolog Variable)').
'symbol'('csubset','csubset','src_span'(21,1,21,8,7,7),'Function or Process').
'symbol'('Do','Do','src_span'(33,1,33,3,2,2),'Function or Process').
'symbol'('TestDiff','TestDiff','src_span'(76,1,76,9,8,8),'Ident (Groundrep.)').
'symbol'('S1','S1','src_span'(85,1,85,3,2,2),'Ident (Groundrep.)').
'symbol'('S2','S2','src_span'(86,1,86,3,2,2),'Ident (Groundrep.)').
'symbol'('sensor_out','sensor_out','src_span'(53,20,53,30,10,10),'Channel').
'symbol'('A','A','src_span'(61,23,61,24,1,1),'Ident (Prolog Variable)').
'symbol'('A2','A','src_span'(62,10,62,11,1,1),'Ident (Prolog Variable)').
'symbol'('TestEnumEnter','TestEnumEnter','src_span'(66,1,66,14,13,13),'Ident (Groundrep.)').
'symbol'('TestHideUnion2','TestHideUnion2','src_span'(87,1,87,15,14,14),'Ident (Groundrep.)').
'symbol'('TestHideUnion1','TestHideUnion1','src_span'(84,1,84,15,14,14),'Ident (Groundrep.)').
'symbol'('SetComp2','SetComp2','src_span'(80,1,80,9,8,8),'Ident (Groundrep.)').
'symbol'('out','out','src_span'(3,9,3,12,3,3),'Channel').
'symbol'('SetComp3','SetComp3','src_span'(81,1,81,9,8,8),'Ident (Groundrep.)').
'symbol'('REALTRACKS','REALTRACKS','src_span'(45,1,45,11,10,10),'Ident (Groundrep.)').
'symbol'('Empty','Empty','src_span'(61,5,61,10,5,5),'Ident (Groundrep.)').
'symbol'('PROB_TEST_TRACE','PROB_TEST_TRACE','src_span'(90,1,90,16,15,15),'Ident (Groundrep.)').
'symbol'('S','S','src_span'(5,9,5,10,1,1),'Ident (Prolog Variable)').
'symbol'('S2','S','src_span'(13,10,13,11,1,1),'Ident (Prolog Variable)').
'symbol'('LastSeg','LastSeg','src_span'(43,1,43,8,7,7),'Ident (Groundrep.)').
'symbol'('sensor_in','sensor_in','src_span'(53,9,53,18,9,9),'Channel').
'symbol'('track','track','src_span'(66,29,66,34,5,5),'Ident (Prolog Variable)').
'symbol'('Thomas','Thomas','src_span'(47,19,47,25,6,6),'Constructor of Datatype').
'symbol'('Gordon','Gordon','src_span'(47,28,47,34,6,6),'Constructor of Datatype').
'symbol'('Tracks','Tracks','src_span'(57,1,57,7,6,6),'Ident (Groundrep.)').
'symbol'('ReplicationTest2','ReplicationTest2','src_span'(29,1,29,17,16,16),'Ident (Groundrep.)').
'symbol'('ReplicationTest3','ReplicationTest3','src_span'(30,1,30,17,16,16),'Ident (Groundrep.)').
'symbol'('ReplicationTest4','ReplicationTest4','src_span'(31,1,31,17,16,16),'Ident (Groundrep.)').
'symbol'('f','f','src_span'(37,45,37,46,1,1),'Function or Process').
'symbol'('ReplicationTest5','ReplicationTest5','src_span'(34,1,34,17,16,16),'Ident (Groundrep.)').
'symbol'('ReplicationTest6','ReplicationTest6','src_span'(39,1,39,17,16,16),'Ident (Groundrep.)').
'symbol'('i','i','src_span'(5,11,5,12,1,1),'Ident (Prolog Variable)').
'symbol'('i2','i','src_span'(13,12,13,13,1,1),'Ident (Prolog Variable)').
'symbol'('j','j','src_span'(59,7,59,8,1,1),'Ident (Prolog Variable)').
'symbol'('OutCard','OutCard','src_span'(5,1,5,8,7,7),'Function or Process').
'symbol'('TestOutCard','TestOutCard','src_span'(11,1,11,12,11,11),'Ident (Groundrep.)').
'symbol'('SetComp','SetComp','src_span'(79,1,79,8,7,7),'Ident (Groundrep.)').
'symbol'('TRACKS','TRACKS','src_span'(44,1,44,7,6,6),'Ident (Groundrep.)').
'symbol'('prime_enter','prime_enter','src_span'(65,9,65,20,11,11),'Channel').
'symbol'('v','v','src_span'(38,47,38,48,1,1),'Ident (Prolog Variable)').
'symbol'('x','x','src_span'(21,9,21,10,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(33,4,33,5,1,1),'Ident (Prolog Variable)').
'symbol'('y','y','src_span'(21,11,21,12,1,1),'Ident (Prolog Variable)').
'symbol'('y2','y','src_span'(37,47,37,48,1,1),'Ident (Prolog Variable)').
'symbol'('z','z','src_span'(37,19,37,20,1,1),'Ident (Prolog Variable)').
'symbol'('Track','Track','src_span'(59,1,59,6,5,5),'Function or Process').

