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
.
'bindval'('Tags','int'(1),'int'(2),'int'(3),'src_span'(5,1,5,15,14,14)).
'bindval'('Data','int'(1),'int'(2),'src_span'(6,1,6,13,12,12)).
'channel'('left','type'('dotTupleType'(['val_of'('Tags','src_span'(8,29,8,33,4,4)),'val_of'('Data','src_span'(8,36,8,40,4,4))]))).
'channel'('a','type'('dotTupleType'(['val_of'('Tags','src_span'(8,29,8,33,4,4)),'val_of'('Data','src_span'(8,36,8,40,4,4))]))).
'channel'('b','type'('dotTupleType'(['val_of'('Tags','src_span'(8,29,8,33,4,4)),'val_of'('Data','src_span'(8,36,8,40,4,4))]))).
'channel'('right','type'('dotTupleType'(['val_of'('Tags','src_span'(8,29,8,33,4,4)),'val_of'('Data','src_span'(8,36,8,40,4,4))]))).
'channel'('c','type'('dotTupleType'(['val_of'('Tags','src_span'(9,16,9,20,4,4))]))).
'channel'('d','type'('dotTupleType'(['val_of'('Tags','src_span'(9,16,9,20,4,4))]))).
'channel'('mess','type'('dotTupleType'(['val_of'('Tags','src_span'(10,16,10,20,4,4)),'val_of'('Data','src_span'(10,23,10,27,4,4))]))).
'channel'('ack','type'('dotTupleType'(['val_of'('Tags','src_span'(11,16,11,20,4,4))]))).
'bindval'('SM','repChoice'(['comprehensionGenerator'('val_of'('Tags','src_span'(15,13,15,17,4,4)))],'tupleExp'(['prefix'('src_span'(15,21,15,26,5,5),['in'(_x)],'dotTuple'(['a']),'prefix'('src_span'(15,34,15,38,4,4),['out'('dotTuple'([_x]))],'mess','val_of'('SM','src_span'(15,50,15,52,2,2)),'src_span'(15,46,15,50,4,4)),'src_span'(15,30,15,34,4,4))]),'src_span'(15,9,15,17,8,8)),'src_span'(15,1,15,53,52,52)).
'bindval'('RM','prefix'('src_span'(17,6,17,10,4,4),['in'(_t),'in'(_x2)],'mess','prefix'('src_span'(17,22,17,27,5,5),['out'(_x2)],'dotTuple'(['b',_t]),'val_of'('RM','src_span'(17,35,17,37,2,2)),'src_span'(17,31,17,35,4,4)),'src_span'(17,18,17,22,4,4)),'src_span'(17,1,17,37,36,36)).
'bindval'('SA','repChoice'(['comprehensionGenerator'('val_of'('Tags','src_span'(19,13,19,17,4,4)))],'tupleExp'(['prefix'('src_span'(19,21,19,26,5,5),[],'dotTuple'(['c']),'prefix'('src_span'(19,31,19,34,3,3),['out'],'ack','val_of'('SA','src_span'(19,42,19,44,2,2)),'src_span'(19,38,19,42,4,4)),'src_span'(19,26,19,31,5,5))]),'src_span'(19,9,19,17,8,8)),'src_span'(19,1,19,45,44,44)).
'bindval'('RA','prefix'('src_span'(21,6,21,9,3,3),['in'(_x3)],'ack','prefix'('src_span'(21,17,21,22,5,5),[],'dotTuple'(['d',_x3]),'val_of'('RA','src_span'(21,26,21,28,2,2)),'src_span'(21,22,21,26,4,4)),'src_span'(21,13,21,17,4,4)),'src_span'(21,1,21,28,27,27)).
'agent'('T'(_i),'prefix'('src_span'(26,8,26,14,6,6),['in'(_x4)],'dotTuple'(['left',_i]),'prefix'('src_span'(26,22,26,25,3,3),['out'(_x4)],'dotTuple'(['a',_i]),'prefix'('src_span'(26,33,26,36,3,3),[],'dotTuple'(['d',_i]),'agent_call'('src_span'(26,40,26,44,4,4),'T',[_i]),'src_span'(26,36,26,40,4,4)),'src_span'(26,29,26,33,4,4)),'src_span'(26,18,26,22,4,4)),'src_span'(26,1,26,44,43,43)).
'agent'('R'(_i2),'prefix'('src_span'(28,8,28,11,3,3),['in'(_x5)],'dotTuple'(['b',_i2]),'prefix'('src_span'(28,19,28,26,7,7),['out'(_x5)],'dotTuple'(['right',_i2]),'prefix'('src_span'(28,34,28,37,3,3),[],'dotTuple'(['c',_i2]),'agent_call'('src_span'(28,41,28,45,4,4),'R',[_i2]),'src_span'(28,37,28,41,4,4)),'src_span'(28,30,28,34,4,4)),'src_span'(28,15,28,19,4,4)),'src_span'(28,1,28,45,44,44)).
'agent'('FAULTYR'(_i3),'prefix'('src_span'(30,14,30,17,3,3),['in'(_x6)],'dotTuple'(['b',_i3]),'prefix'('src_span'(30,25,30,32,7,7),['out'(_x6)],'dotTuple'(['right',_i3]),'tupleExp'(['|~|'('agent_call'('src_span'(30,41,30,51,10,10),'FAULTYR',[_i3]),'prefix'('src_span'(30,56,30,59,3,3),[],'dotTuple'(['c',_i3]),'agent_call'('src_span'(30,63,30,73,10,10),'FAULTYR',[_i3]),'src_span'(30,59,30,63,4,4)),'src_span_operator'('no_loc_info_available','src_span'(30,51,30,56,5,5)))]),'src_span'(30,36,30,40,4,4)),'src_span'(30,21,30,25,4,4)),'src_span'(30,1,30,74,73,73)).
'bindval'('INS','repInterleave'(['comprehensionGenerator'('val_of'('Tags','src_span'(35,15,35,19,4,4)))],'agent_call'('src_span'(35,23,35,27,4,4),'T',[]),'src_span'(35,11,35,19,8,8)),'src_span'(35,1,35,27,26,26)).
'bindval'('ASM','a','mess','src_span'(40,1,40,20,19,19)).
'bindval'('ARA','d','ack','src_span'(41,1,41,19,18,18)).
'bindval'('LHS','agent_call'('src_span'(44,7,44,45,38,38),'normal',['lParallel'('linkList'(['link'('a','a'),'link'('d','d')]),'val_of'('INS','src_span'(44,14,44,17,3,3)),'tupleExp'(['|||'('val_of'('SM','src_span'(44,34,44,36,2,2)),'val_of'('RA','src_span'(44,41,44,43,2,2)),'src_span_operator'('no_loc_info_available','src_span'(44,36,44,41,5,5)))]),'src_span'(44,17,44,19,2,2))]),'src_span'(44,1,44,45,44,44)).
'bindval'('AR1','dotTuple'(['right','int'(1)]),'dotTuple'(['b','int'(1)]),'dotTuple'(['c','int'(1)]),'src_span'(51,1,51,28,27,27)).
'bindval'('AR2','dotTuple'(['right','int'(2)]),'dotTuple'(['b','int'(2)]),'dotTuple'(['c','int'(2)]),'src_span'(52,1,52,28,27,27)).
'bindval'('AR3','dotTuple'(['right','int'(3)]),'dotTuple'(['b','int'(3)]),'dotTuple'(['c','int'(3)]),'src_span'(53,1,53,28,27,27)).
'bindval'('OUTS','repInterleave'(['comprehensionGenerator'('val_of'('Tags','src_span'(55,16,55,20,4,4)))],'agent_call'('src_span'(55,23,55,27,4,4),'R',[]),'src_span'(55,12,55,20,8,8)),'src_span'(55,1,55,27,26,26)).
'bindval'('FAULTYOUTS','repInterleave'(['comprehensionGenerator'('val_of'('Tags','src_span'(57,22,57,26,4,4)))],'tupleExp'(['ifte'('int'(3),'R','FAULTYR','src_span'(57,30,57,32,2,2),'src_span'(57,37,57,43,6,6),'src_span'(57,44,57,50,6,6))]),'src_span'(57,18,57,26,8,8)),'src_span'(57,1,57,61,60,60)).
'bindval'('RHS','agent_call'('src_span'(59,7,59,46,39,39),'normal',['lParallel'('linkList'(['link'('b','b'),'link'('c','c')]),'val_of'('OUTS','src_span'(59,14,59,18,4,4)),'tupleExp'(['|||'('val_of'('RM','src_span'(59,35,59,37,2,2)),'val_of'('SA','src_span'(59,42,59,44,2,2)),'src_span_operator'('no_loc_info_available','src_span'(59,37,59,42,5,5)))]),'src_span'(59,18,59,20,2,2))]),'src_span'(59,1,59,46,45,45)).
'bindval'('FAULTYRHS','agent_call'('src_span'(61,13,61,58,45,45),'normal',['lParallel'('linkList'(['link'('b','b'),'link'('c','c')]),'val_of'('FAULTYOUTS','src_span'(61,20,61,30,10,10)),'tupleExp'(['|||'('val_of'('RM','src_span'(61,47,61,49,2,2)),'val_of'('SA','src_span'(61,54,61,56,2,2)),'src_span_operator'('no_loc_info_available','src_span'(61,49,61,54,5,5)))]),'src_span'(61,30,61,32,2,2))]),'src_span'(61,1,61,58,57,57)).
'bindval'('SYSTEM','lParallel'('linkList'(['link'('mess','mess'),'link'('ack','ack')]),'val_of'('LHS','src_span'(65,10,65,13,3,3)),'val_of'('RHS','src_span'(65,39,65,42,3,3)),'src_span'(65,13,65,15,2,2)),'src_span'(65,1,65,42,41,41)).
'bindval'('FAULTYSYSTEM','lParallel'('linkList'(['link'('mess','mess'),'link'('ack','ack')]),'val_of'('LHS','src_span'(67,16,67,19,3,3)),'val_of'('FAULTYRHS','src_span'(67,45,67,54,9,9)),'src_span'(67,19,67,21,2,2)),'src_span'(67,1,67,54,53,53)).
'agent'('COPY'(_in,_out),'prefix'('src_span'(72,16,72,18,2,2),['in'(_x7)],_in,'prefix'('src_span'(72,24,72,27,3,3),['out'(_x7)],_out,'agent_call'('src_span'(72,33,72,45,12,12),'COPY',[_in,_out]),'src_span'(72,29,72,33,4,4)),'src_span'(72,20,72,24,4,4)),'src_span'(72,1,72,45,44,44)).
'bindval'('SPEC','repInterleave'(['comprehensionGenerator'('val_of'('Tags','src_span'(74,16,74,20,4,4)))],'agent_call'('src_span'(74,23,74,44,21,21),'COPY',['dotTuple'(['left']),'dotTuple'(['right'])]),'src_span'(74,12,74,20,8,8)),'src_span'(74,1,74,44,43,43)).
'val_of'('SPEC','src_span'(78,8,78,12,4,4)),'val_of'('SYSTEM','src_span'(78,18,78,24,6,6)).
'symbol'('OUTS','OUTS','src_span'(55,1,55,5,4,4),'Ident (Groundrep.)').
'symbol'('SYSTEM','SYSTEM','src_span'(65,1,65,7,6,6),'Ident (Groundrep.)').
'symbol'('ack','ack','src_span'(11,9,11,12,3,3),'Channel').
'symbol'('FAULTYOUTS','FAULTYOUTS','src_span'(57,1,57,11,10,10),'Ident (Groundrep.)').
'symbol'('mess','mess','src_span'(10,9,10,13,4,4),'Channel').
'symbol'('SA','SA','src_span'(19,1,19,3,2,2),'Ident (Groundrep.)').
'symbol'('INS','INS','src_span'(35,1,35,4,3,3),'Ident (Groundrep.)').
'symbol'('out','out','src_span'(72,9,72,12,3,3),'Ident (Prolog Variable)').
'symbol'('R','R','src_span'(28,1,28,2,1,1),'Function or Process').
'symbol'('T','T','src_span'(26,1,26,2,1,1),'Function or Process').
'symbol'('LHS','LHS','src_span'(44,1,44,4,3,3),'Ident (Groundrep.)').
'symbol'('SM','SM','src_span'(15,1,15,3,2,2),'Ident (Groundrep.)').
'symbol'('ASM','ASM','src_span'(40,1,40,4,3,3),'Ident (Groundrep.)').
'symbol'('RHS','RHS','src_span'(59,1,59,4,3,3),'Ident (Groundrep.)').
'symbol'('SPEC','SPEC','src_span'(74,1,74,5,4,4),'Ident (Groundrep.)').
'symbol'('Tags','Tags','src_span'(5,1,5,5,4,4),'Ident (Groundrep.)').
'symbol'('AR1','AR1','src_span'(51,1,51,4,3,3),'Ident (Groundrep.)').
'symbol'('a','a','src_span'(8,15,8,16,1,1),'Channel').
'symbol'('AR2','AR2','src_span'(52,1,52,4,3,3),'Ident (Groundrep.)').
'symbol'('b','b','src_span'(8,18,8,19,1,1),'Channel').
'symbol'('AR3','AR3','src_span'(53,1,53,4,3,3),'Ident (Groundrep.)').
'symbol'('c','c','src_span'(9,9,9,10,1,1),'Channel').
'symbol'('d','d','src_span'(9,12,9,13,1,1),'Channel').
'symbol'('in','in','src_span'(72,6,72,8,2,2),'Ident (Prolog Variable)').
'symbol'('FAULTYRHS','FAULTYRHS','src_span'(61,1,61,10,9,9),'Ident (Groundrep.)').
'symbol'('i','i','src_span'(26,3,26,4,1,1),'Ident (Prolog Variable)').
'symbol'('i2','i','src_span'(28,3,28,4,1,1),'Ident (Prolog Variable)').
'symbol'('i3','i','src_span'(30,9,30,10,1,1),'Ident (Prolog Variable)').
'symbol'('Data','Data','src_span'(6,1,6,5,4,4),'Ident (Groundrep.)').
'symbol'('COPY','COPY','src_span'(72,1,72,5,4,4),'Function or Process').
'symbol'('right','right','src_span'(8,21,8,26,5,5),'Channel').
'symbol'('FAULTYR','FAULTYR','src_span'(30,1,30,8,7,7),'Function or Process').
'symbol'('RA','RA','src_span'(21,1,21,3,2,2),'Ident (Groundrep.)').
'symbol'('ARA','ARA','src_span'(41,1,41,4,3,3),'Ident (Groundrep.)').
'symbol'('FAULTYSYSTEM','FAULTYSYSTEM','src_span'(67,1,67,13,12,12),'Ident (Groundrep.)').
'symbol'('t','t','src_span'(17,13,17,14,1,1),'Ident (Prolog Variable)').
'symbol'('left','left','src_span'(8,9,8,13,4,4),'Channel').
'symbol'('x','x','src_span'(15,29,15,30,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(17,17,17,18,1,1),'Ident (Prolog Variable)').
'symbol'('x3','x','src_span'(21,12,21,13,1,1),'Ident (Prolog Variable)').
'symbol'('x4','x','src_span'(26,17,26,18,1,1),'Ident (Prolog Variable)').
'symbol'('x5','x','src_span'(28,14,28,15,1,1),'Ident (Prolog Variable)').
'symbol'('x6','x','src_span'(30,20,30,21,1,1),'Ident (Prolog Variable)').
'symbol'('x7','x','src_span'(72,19,72,20,1,1),'Ident (Prolog Variable)').
'symbol'('RM','RM','src_span'(17,1,17,3,2,2),'Ident (Groundrep.)').

