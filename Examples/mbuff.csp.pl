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
'dataTypeDef'('Tag',['constructor'('t1'),'constructor'('t2'),'constructor'('t3')]).
'dataTypeDef'('Data',['constructor'('d1'),'constructor'('d2')]).
'channel'('left','type'('dotTupleType'(['val_of'('Tag','src_span'(14,23,14,26,3,3)),'val_of'('Data','src_span'(14,27,14,31,4,4))]))).
'channel'('right','type'('dotTupleType'(['val_of'('Tag','src_span'(14,23,14,26,3,3)),'val_of'('Data','src_span'(14,27,14,31,4,4))]))).
'channel'('snd_mess','type'('dotTupleType'(['val_of'('Tag','src_span'(15,30,15,33,3,3)),'val_of'('Data','src_span'(15,34,15,38,4,4))]))).
'channel'('rcv_mess','type'('dotTupleType'(['val_of'('Tag','src_span'(15,30,15,33,3,3)),'val_of'('Data','src_span'(15,34,15,38,4,4))]))).
'channel'('snd_ack','type'('dotTupleType'(['val_of'('Tag','src_span'(16,30,16,33,3,3))]))).
'channel'('rcv_ack','type'('dotTupleType'(['val_of'('Tag','src_span'(16,30,16,33,3,3))]))).
'channel'('mess','type'('dotTupleType'(['val_of'('Tag','src_span'(17,16,17,19,3,3)),'val_of'('Data','src_span'(17,20,17,24,4,4))]))).
'channel'('ack','type'('dotTupleType'(['val_of'('Tag','src_span'(18,16,18,19,3,3))]))).
'bindval'('SndMess','repChoice'(['comprehensionGenerator'('Tag')],'tupleExp'(['prefix'('src_span'(30,23,30,33,10,10),['in'(_x)],'dotTuple'(['snd_mess']),'prefix'('src_span'(30,41,30,45,4,4),['out'('dotTuple'([_x]))],'mess','val_of'('SndMess','src_span'(30,55,30,62,7,7)),'src_span'(30,51,30,55,4,4)),'src_span'(30,37,30,41,4,4))]),'src_span'(30,14,30,19,5,5)),'src_span'(30,1,30,63,62,62)).
'bindval'('RcvMess','prefix'('src_span'(32,11,32,15,4,4),['in'('dotpat'([_i,_x2]))],'mess','prefix'('src_span'(32,25,32,35,10,10),['out'(_x2)],'dotTuple'(['rcv_mess',_i]),'val_of'('RcvMess','src_span'(32,43,32,50,7,7)),'src_span'(32,39,32,43,4,4)),'src_span'(32,21,32,25,4,4)),'src_span'(32,1,32,50,49,49)).
'bindval'('SndAck','repChoice'(['comprehensionGenerator'('Tag')],'tupleExp'(['prefix'('src_span'(34,22,34,31,9,9),[],'dotTuple'(['snd_ack']),'prefix'('src_span'(34,36,34,39,3,3),['out'],'ack','val_of'('SndAck','src_span'(34,47,34,53,6,6)),'src_span'(34,43,34,47,4,4)),'src_span'(34,31,34,36,5,5))]),'src_span'(34,13,34,18,5,5)),'src_span'(34,1,34,54,53,53)).
'bindval'('RcvAck','prefix'('src_span'(36,10,36,13,3,3),['in'(_i2)],'ack','prefix'('src_span'(36,21,36,30,9,9),[],'dotTuple'(['rcv_ack',_i2]),'val_of'('RcvAck','src_span'(36,34,36,40,6,6)),'src_span'(36,30,36,34,4,4)),'src_span'(36,17,36,21,4,4)),'src_span'(36,1,36,40,39,39)).
'agent'('Tx'(_i3),'prefix'('src_span'(42,9,42,15,6,6),['in'(_x3)],'dotTuple'(['left',_i3]),'prefix'('src_span'(42,23,42,33,10,10),['out'(_x3)],'dotTuple'(['snd_mess',_i3]),'prefix'('src_span'(42,41,42,50,9,9),[],'dotTuple'(['rcv_ack',_i3]),'agent_call'('src_span'(42,54,42,59,5,5),'Tx',[_i3]),'src_span'(42,50,42,54,4,4)),'src_span'(42,37,42,41,4,4)),'src_span'(42,19,42,23,4,4)),'src_span'(42,1,42,59,58,58)).
'agent'('Rx'(_i4),'prefix'('src_span'(44,9,44,19,10,10),['in'(_x4)],'dotTuple'(['rcv_mess',_i4]),'prefix'('src_span'(44,27,44,34,7,7),['out'(_x4)],'dotTuple'(['right',_i4]),'prefix'('src_span'(44,42,44,51,9,9),[],'dotTuple'(['snd_ack',_i4]),'agent_call'('src_span'(44,55,44,60,5,5),'Rx',[_i4]),'src_span'(44,51,44,55,4,4)),'src_span'(44,38,44,42,4,4)),'src_span'(44,23,44,27,4,4)),'src_span'(44,1,44,60,59,59)).
'agent'('FaultyRx'(_i5),'prefix'('src_span'(46,15,46,25,10,10),['in'(_x5)],'dotTuple'(['rcv_mess',_i5]),'prefix'('src_span'(46,33,46,40,7,7),['out'(_x5)],'dotTuple'(['right',_i5]),'tupleExp'(['|~|'('agent_call'('src_span'(46,48,46,59,11,11),'FaultyRx',[_i5]),'prefix'('src_span'(47,51,47,60,9,9),[],'dotTuple'(['snd_ack',_i5]),'agent_call'('src_span'(47,64,47,75,11,11),'FaultyRx',[_i5]),'src_span'(47,60,47,64,4,4)),'src_span_operator'('no_loc_info_available','src_span'(46,59,46,111,52,52)))]),'src_span'(46,44,46,47,3,3)),'src_span'(46,29,46,33,4,4)),'src_span'(46,1,47,76,75,75)).
'bindval'('Txs','repInterleave'(['comprehensionGenerator'('Tag')],'agent_call'('src_span'(51,19,51,24,5,5),'Tx',[]),'src_span'(51,11,51,16,5,5)),'src_span'(51,1,51,24,23,23)).
'bindval'('LHS','\'('tupleExp'(['sharing'('val_of'('Txs','src_span'(56,8,56,11,3,3)),'snd_mess','rcv_ack','tupleExp'(['|||'('val_of'('SndMess','src_span'(56,38,56,45,7,7)),'val_of'('RcvAck','src_span'(56,50,56,56,6,6)),'src_span_operator'('no_loc_info_available','src_span'(56,45,56,50,5,5)))]),'src_span'(56,11,56,14,3,3))]),'snd_mess','rcv_ack','src_span_operator'('no_loc_info_available','src_span'(56,58,56,59,1,1))),'src_span'(56,1,56,80,79,79)).
'bindval'('Rxs','repInterleave'(['comprehensionGenerator'('Tag')],'agent_call'('src_span'(60,19,60,24,5,5),'Rx',[]),'src_span'(60,11,60,16,5,5)),'src_span'(60,1,60,24,23,23)).
'bindval'('FaultyRxs','|||'('|||'('agent_call'('src_span'(62,13,62,19,6,6),'Rx',['t1']),'agent_call'('src_span'(62,24,62,30,6,6),'Rx',['t2']),'src_span_operator'('no_loc_info_available','src_span'(62,19,62,24,5,5))),'agent_call'('src_span'(62,35,62,47,12,12),'FaultyRx',['t3']),'src_span_operator'('no_loc_info_available','src_span'(62,30,62,35,5,5))),'src_span'(62,1,62,47,46,46)).
'bindval'('RHS','\'('tupleExp'(['sharing'('val_of'('Rxs','src_span'(64,8,64,11,3,3)),'rcv_mess','snd_ack','tupleExp'(['|||'('val_of'('RcvMess','src_span'(65,20,65,27,7,7)),'val_of'('SndAck','src_span'(65,32,65,38,6,6)),'src_span_operator'('no_loc_info_available','src_span'(65,27,65,32,5,5)))]),'src_span'(64,11,64,14,3,3))]),'rcv_mess','snd_ack','src_span_operator'('no_loc_info_available','src_span'(65,40,65,41,1,1))),'src_span'(64,1,65,62,61,61)).
'bindval'('FaultyRHS','\'('tupleExp'(['sharing'('val_of'('FaultyRxs','src_span'(67,14,67,23,9,9)),'rcv_mess','snd_ack','tupleExp'(['|||'('val_of'('RcvMess','src_span'(68,26,68,33,7,7)),'val_of'('SndAck','src_span'(68,38,68,44,6,6)),'src_span_operator'('no_loc_info_available','src_span'(68,33,68,38,5,5)))]),'src_span'(67,23,67,26,3,3))]),'rcv_mess','snd_ack','src_span_operator'('no_loc_info_available','src_span'(68,46,68,47,1,1))),'src_span'(67,1,68,68,67,67)).
'bindval'('System','\'('tupleExp'(['sharing'('val_of'('LHS','src_span'(72,11,72,14,3,3)),'mess','ack','val_of'('RHS','src_span'(72,33,72,36,3,3)),'src_span'(72,14,72,17,3,3))]),'mess','ack','src_span_operator'('no_loc_info_available','src_span'(72,37,72,38,1,1))),'src_span'(72,1,72,50,49,49)).
'bindval'('FaultySystem','\'('tupleExp'(['sharing'('val_of'('LHS','src_span'(74,17,74,20,3,3)),'mess','ack','val_of'('FaultyRHS','src_span'(74,39,74,48,9,9)),'src_span'(74,20,74,23,3,3))]),'mess','ack','src_span_operator'('no_loc_info_available','src_span'(74,49,74,50,1,1))),'src_span'(74,1,74,63,62,62)).
'agent'('Copy'(_i6),'prefix'('src_span'(79,11,79,17,6,6),['in'(_x6)],'dotTuple'(['left',_i6]),'prefix'('src_span'(79,25,79,32,7,7),['out'(_x6)],'dotTuple'(['right',_i6]),'agent_call'('src_span'(79,40,79,47,7,7),'Copy',[_i6]),'src_span'(79,36,79,40,4,4)),'src_span'(79,21,79,25,4,4)),'src_span'(79,1,79,47,46,46)).
'bindval'('Spec','repInterleave'(['comprehensionGenerator'('Tag')],'agent_call'('src_span'(81,20,81,27,7,7),'Copy',[]),'src_span'(81,12,81,17,5,5)),'src_span'(81,1,81,27,26,26)).
'val_of'('Spec','src_span'(85,8,85,12,4,4)),'val_of'('System','src_span'(85,18,85,24,6,6)).
'symbol'('FaultySystem','FaultySystem','src_span'(74,1,74,13,12,12),'Ident (Groundrep.)').
'symbol'('FaultyRx','FaultyRx','src_span'(46,1,46,9,8,8),'Function or Process').
'symbol'('ack','ack','src_span'(18,9,18,12,3,3),'Channel').
'symbol'('rcv_mess','rcv_mess','src_span'(15,19,15,27,8,8),'Channel').
'symbol'('mess','mess','src_span'(17,9,17,13,4,4),'Channel').
'symbol'('Rxs','Rxs','src_span'(60,1,60,4,3,3),'Ident (Groundrep.)').
'symbol'('d1','d1','src_span'(12,17,12,19,2,2),'Constructor of Datatype').
'symbol'('d2','d2','src_span'(12,22,12,24,2,2),'Constructor of Datatype').
'symbol'('rcv_ack','rcv_ack','src_span'(16,18,16,25,7,7),'Channel').
'symbol'('Txs','Txs','src_span'(51,1,51,4,3,3),'Ident (Groundrep.)').
'symbol'('System','System','src_span'(72,1,72,7,6,6),'Ident (Groundrep.)').
'symbol'('RcvMess','RcvMess','src_span'(32,1,32,8,7,7),'Ident (Groundrep.)').
'symbol'('FaultyRxs','FaultyRxs','src_span'(62,1,62,10,9,9),'Ident (Groundrep.)').
'symbol'('RcvAck','RcvAck','src_span'(36,1,36,7,6,6),'Ident (Groundrep.)').
'symbol'('LHS','LHS','src_span'(56,1,56,4,3,3),'Ident (Groundrep.)').
'symbol'('RHS','RHS','src_span'(64,1,64,4,3,3),'Ident (Groundrep.)').
'symbol'('SndAck','SndAck','src_span'(34,1,34,7,6,6),'Ident (Groundrep.)').
'symbol'('SndMess','SndMess','src_span'(30,1,30,8,7,7),'Ident (Groundrep.)').
'symbol'('FaultyRHS','FaultyRHS','src_span'(67,1,67,10,9,9),'Ident (Groundrep.)').
'symbol'('Tx','Tx','src_span'(42,1,42,3,2,2),'Function or Process').
'symbol'('Rx','Rx','src_span'(44,1,44,3,2,2),'Function or Process').
'symbol'('snd_mess','snd_mess','src_span'(15,9,15,17,8,8),'Channel').
'symbol'('i','i','src_span'(32,18,32,19,1,1),'Ident (Prolog Variable)').
'symbol'('i2','i','src_span'(36,16,36,17,1,1),'Ident (Prolog Variable)').
'symbol'('i3','i','src_span'(42,4,42,5,1,1),'Ident (Prolog Variable)').
'symbol'('i4','i','src_span'(44,4,44,5,1,1),'Ident (Prolog Variable)').
'symbol'('i5','i','src_span'(46,10,46,11,1,1),'Ident (Prolog Variable)').
'symbol'('i6','i','src_span'(79,6,79,7,1,1),'Ident (Prolog Variable)').
'symbol'('Data','Data','src_span'(12,10,12,14,4,4),'Datatype').
'symbol'('right','right','src_span'(14,15,14,20,5,5),'Channel').
'symbol'('left','left','src_span'(14,9,14,13,4,4),'Channel').
'symbol'('Copy','Copy','src_span'(79,1,79,5,4,4),'Function or Process').
'symbol'('x','x','src_span'(30,36,30,37,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(32,20,32,21,1,1),'Ident (Prolog Variable)').
'symbol'('x3','x','src_span'(42,18,42,19,1,1),'Ident (Prolog Variable)').
'symbol'('x4','x','src_span'(44,22,44,23,1,1),'Ident (Prolog Variable)').
'symbol'('x5','x','src_span'(46,28,46,29,1,1),'Ident (Prolog Variable)').
'symbol'('x6','x','src_span'(79,20,79,21,1,1),'Ident (Prolog Variable)').
'symbol'('Tag','Tag','src_span'(11,10,11,13,3,3),'Datatype').
'symbol'('snd_ack','snd_ack','src_span'(16,9,16,16,7,7),'Channel').
'symbol'('Spec','Spec','src_span'(81,1,81,5,4,4),'Ident (Groundrep.)').
'symbol'('t1','t1','src_span'(11,17,11,19,2,2),'Constructor of Datatype').
'symbol'('t2','t2','src_span'(11,22,11,24,2,2),'Constructor of Datatype').
'symbol'('t3','t3','src_span'(11,27,11,29,2,2),'Constructor of Datatype').

