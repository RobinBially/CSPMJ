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
'bindval'('QUEUENUM','int'(1),'int'(3),'src_span'(6,1,6,18,17,17)).
'dataTypeDef'('CUSTOMER',['constructor'('c1'),'constructor'('c2'),'constructor'('c3')]).
'dataTypeDef'('STATUS',['constructor'('success'),'constructor'('fail')]).
'dataTypeDef'('QSTATUS',['constructor'('yes'),'constructor'('no')]).
'bindval'('maxLimit','int'(3),'src_span'(11,1,11,13,12,12)).
'bindval'('defaultCounter','int'(1),'src_span'(12,1,12,20,19,19)).
'bindval'('defaultCustomer','c1','src_span'(13,1,13,21,20,20)).
'bindval'('maxQueueingCustomers','val_of'('maxLimit','src_span'(14,24,14,32,8,8)),'int'(1),'src_span'(14,1,14,35,34,34)).
'bindval'('numQueues','int'(2),'src_span'(15,1,15,14,13,13)).
'channel'('enterBank','type'('dotTupleType'(['val_of'('CUSTOMER','src_span'(19,21,19,29,8,8))]))).
'channel'('leaveBank','type'('dotTupleType'(['val_of'('CUSTOMER','src_span'(20,21,20,29,8,8))]))).
'channel'('report','type'('dotTupleType'(['val_of'('STATUS','src_span'(21,18,21,24,6,6))]))).
'channel'('com1','type'('dotTupleType'(['val_of'('CUSTOMER','src_span'(24,16,24,24,8,8))]))).
'channel'('com3','type'('dotTupleType'(['val_of'('CUSTOMER','src_span'(25,16,25,24,8,8))]))).
'channel'('canJoinResponse','type'('dotTupleType'(['val_of'('STATUS','src_span'(26,27,26,33,6,6))]))).
'channel'('retrieveCustomer','type'('dotUnitType')).
'channel'('joinQueue','type'('dotTupleType'(['val_of'('CUSTOMER','src_span'(30,21,30,29,8,8))]))).
'channel'('leaveQueue','type'('dotTupleType'(['val_of'('QUEUENUM','src_span'(31,22,31,30,8,8)),'val_of'('CUSTOMER','src_span'(31,31,31,39,8,8))]))).
'channel'('queryQueueEmpty','type'('dotTupleType'(['val_of'('QUEUENUM','src_span'(32,27,32,35,8,8)),'val_of'('QSTATUS','src_span'(32,36,32,43,7,7))]))).
'agent'('inc'(_queueNo),'tupleExp'([_queueNo,'val_of'('numQueues','src_span'(35,27,35,36,9,9))]),'int'(1),'src_span'(35,1,35,41,40,40)).
'bindval'('CounterCtrl','agent_call'('src_span'(49,15,49,45,30,30),'CurrentCtrl',['int'(0),'val_of'('defaultCustomer','src_span'(49,29,49,44,15,15))]),'src_span'(49,1,49,45,44,44)).
'agent'('CurrentCtrl'(_num,_currentCust),'[]'('agent_call'('src_span'(52,5,52,31,26,26),'JoinCtrl',[_num,_currentCust]),'agent_call'('src_span'(52,35,52,61,26,26),'LeaveCtrl',[_num,_currentCust]),'src_span_operator'('no_loc_info_available','src_span'(52,31,52,35,4,4))),'src_span'(51,1,52,61,60,60)).
'agent'('JoinCtrl'(_num2,_currentCust2),'&'(_num2,'val_of'('maxLimit','src_span'(60,9,60,17,8,8)),'prefix'('src_span'(60,20,60,29,9,9),['in'(_cc)],'enterBank','tupleExp'(['[]'('[]'('tupleExp'(['&'(_num2,'int'(0),'prefix'('src_span'(61,21,61,35,14,14),[],'dotTuple'(['report','success']),'agent_call'('src_span'(61,39,61,60,21,21),'CurrentCtrl',[_num2,'int'(1),_cc]),'src_span'(61,35,61,39,4,4)))]),'tupleExp'(['&'('tupleExp'([_num2,'int'(0),'tupleExp'([_cc,_currentCust2])]),'prefix'('src_span'(63,47,63,51,4,4),['out'(_cc)],'com1','prefix'('src_span'(64,23,64,38,15,15),['in'(_bb)],'canJoinResponse','prefix'('src_span'(64,48,64,57,9,9),[],'dotTuple'(['report',_bb]),'tupleExp'(['ifte'('tupleExp'([_bb,'success']),'agent_call'('src_span'(66,27,66,57,30,30),'CurrentCtrl',[_num2,'int'(1),_currentCust2]),'agent_call'('src_span'(68,27,68,55,28,28),'CurrentCtrl',[_num2,_currentCust2]),'src_span'(65,26,65,28,2,2),'src_span'(65,44,65,77,33,33),'src_span'(66,57,66,117,60,60))]),'src_span'(64,57,64,86,29,29)),'src_span'(64,44,64,48,4,4)),'src_span'(63,55,63,83,28,28)))]),'src_span_operator'('no_loc_info_available','src_span'(61,61,61,83,22,22))),'tupleExp'(['&'('tupleExp'([_num2,'int'(0),'tupleExp'([_cc,_currentCust2])]),'prefix'('src_span'(70,47,70,58,11,11),[],'dotTuple'(['report','fail']),'agent_call'('src_span'(70,62,70,90,28,28),'CurrentCtrl',[_num2,_currentCust2]),'src_span'(70,58,70,62,4,4)))]),'src_span_operator'('no_loc_info_available','src_span'(68,57,68,80,23,23)))]),'src_span'(60,33,60,41,8,8))),'src_span'(59,1,70,93,92,92)).
'agent'('LeaveCtrl'(_num3,_currentCust3),'[]'('tupleExp'(['&'(_num3,'int'(1),'prefix'('src_span'(83,21,83,30,9,9),['out'(_currentCust3)],'leaveBank','agent_call'('src_span'(83,46,83,76,30,30),'CurrentCtrl',['int'(0),'val_of'('defaultCustomer','src_span'(83,60,83,75,15,15))]),'src_span'(83,42,83,46,4,4)))]),'tupleExp'(['&'(_num3,'int'(1),'prefix'('src_span'(85,20,85,29,9,9),['out'(_currentCust3)],'leaveBank','prefix'('src_span'(85,45,85,61,16,16),[],'retrieveCustomer','prefix'('src_span'(85,65,85,69,4,4),['in'(_cc2)],'com3','agent_call'('src_span'(85,76,85,97,21,21),'CurrentCtrl',[_num3,'int'(1),_cc2]),'src_span'(85,72,85,76,4,4)),'src_span'(85,61,85,65,4,4)),'src_span'(85,41,85,45,4,4)))]),'src_span_operator'('no_loc_info_available','src_span'(83,77,83,100,23,23))),'src_span'(82,1,85,98,97,97)).
'bindval'('QueuesCtrl','agent_call'('src_span'(91,14,91,27,13,13),'QCtrl',['int'(0),'int'(1)]),'src_span'(91,1,91,27,26,26)).
'agent'('QCtrl'(_s,_queueNo2,_custSet),'[]'('tupleExp'(['&'(_s,'val_of'('maxQueueingCustomers','src_span'(93,11,93,31,20,20)),'prefix'('src_span'(93,34,93,38,4,4),['in'(_cc3)],'com1','tupleExp'(['ifte'('tupleExp'(['agent_call'('src_span'(94,21,94,39,18,18),'member',[_cc3,_custSet])]),'prefix'('src_span'(95,19,95,34,15,15),['out'('fail')],'canJoinResponse','agent_call'('src_span'(95,43,95,67,24,24),'QCtrl',[_s,_queueNo2,_custSet]),'src_span'(95,39,95,43,4,4)),'prefix'('src_span'(97,19,97,34,15,15),['out'('success')],'canJoinResponse','prefix'('src_span'(97,46,97,58,12,12),[],'dotTuple'(['joinQueue',_cc3]),'agent_call'('src_span'(98,5,98,45,40,40),'QCtrl',[_s,'int'(1),_queueNo2,'agent_call'('src_span'(98,23,98,42,19,19),'union',[_custSet,_cc3])]),'src_span'(97,58,97,67,9,9)),'src_span'(97,42,97,46,4,4)),'src_span'(94,17,94,19,2,2),'src_span'(94,40,94,68,28,28),'src_span'(95,67,95,108,41,41))]),'src_span'(93,43,93,64,21,21)))]),'tupleExp'(['&'(_s,'int'(0),'prefix'('src_span'(101,15,101,31,16,16),[],'retrieveCustomer','agent_call'('src_span'(101,35,101,63,28,28),'NextQCtrl',[_s,_queueNo2,_custSet]),'src_span'(101,31,101,35,4,4)))]),'src_span_operator'('no_loc_info_available','src_span'(98,48,98,72,24,24))),'src_span'(92,1,101,64,63,63)).
'agent'('NextQCtrl'(_s2,_queueNo3,_custSet2),'prefix'('src_span'(110,12,110,27,15,15),['out'(_queueNo3),'in'(_bb2)],'queryQueueEmpty','tupleExp'(['ifte'('tupleExp'([_bb2,'no']),'prefix'('src_span'(112,14,112,24,10,10),['out'(_queueNo3),'in'(_cc4)],'leaveQueue','tupleExp'(['ifte'('tupleExp'(['agent_call'('src_span'(113,18,113,36,18,18),'member',[_cc4,_custSet2])]),'prefix'('src_span'(114,20,114,24,4,4),['out'(_cc4)],'com3','agent_call'('src_span'(115,24,115,66,42,42),'QCtrl',[_s2,'int'(1),'agent_call'('src_span'(115,34,115,46,12,12),'inc',[_queueNo3]),'agent_call'('src_span'(115,47,115,65,18,18),'diff',[_custSet2,_cc4])]),'src_span'(114,27,114,55,28,28)),'div'('src_span'(116,20,116,23,3,3)),'src_span'(113,14,113,16,2,2),'src_span'(113,37,113,59,22,22),'src_span'(115,66,115,87,21,21))]),'src_span'(112,35,112,53,18,18)),'agent_call'('src_span'(119,14,119,47,33,33),'NextQCtrl',[_s2,'agent_call'('src_span'(119,26,119,38,12,12),'inc',[_queueNo3]),_custSet2]),'src_span'(111,13,111,15,2,2),'src_span'(111,26,111,46,20,20),'src_span'(116,24,116,161,137,137))]),'src_span'(110,38,110,54,16,16)),'src_span'(109,1,119,48,47,47)).
'bindval'('A','com1','com3','canJoinResponse','retrieveCustomer','src_span'(121,1,121,51,50,50)).
'bindval'('B','joinQueue','leaveQueue','queryQueueEmpty','src_span'(122,1,122,52,51,51)).
'bindval'('BankSystem','\'('sharing'('val_of'('CounterCtrl','src_span'(123,14,123,25,11,11)),'val_of'('A','src_span'(123,28,123,29,1,1)),'val_of'('QueuesCtrl','src_span'(123,32,123,42,10,10)),'src_span'(123,25,123,28,3,3)),'agent_call'('src_span'(123,45,123,55,10,10),'union',['val_of'('A','src_span'(123,51,123,52,1,1)),'val_of'('B','src_span'(123,53,123,54,1,1))]),'src_span_operator'('no_loc_info_available','src_span'(123,42,123,45,3,3))),'src_span'(123,1,123,55,54,54)).
'bindval'('SPEC','repInterleave'(['comprehensionGenerator'('int'(1),'val_of'('maxLimit','src_span'(127,18,127,26,8,8)))],'val_of'('CUST','src_span'(127,30,127,34,4,4)),'src_span'(127,12,127,27,15,15)),'src_span'(127,1,127,34,33,33)).
'bindval'('CUST','prefix'('src_span'(128,8,128,17,9,9),['in'(_i)],'enterBank','tupleExp'(['[]'('prefix'('src_span'(128,24,128,35,11,11),[],'dotTuple'(['report','fail']),'val_of'('CUST','src_span'(128,39,128,43,4,4)),'src_span'(128,35,128,39,4,4)),'prefix'('src_span'(128,47,128,61,14,14),[],'dotTuple'(['report','success']),'prefix'('src_span'(129,29,129,40,11,11),[],'dotTuple'(['leaveBank',_i]),'val_of'('CUST','src_span'(129,44,129,48,4,4)),'src_span'(129,40,129,44,4,4)),'src_span'(128,61,128,94,33,33)),'src_span_operator'('no_loc_info_available','src_span'(128,43,128,47,4,4)))]),'src_span'(128,19,128,23,4,4)),'src_span'(128,1,129,49,48,48)).
'val_of'('SPEC','src_span'(136,9,136,13,4,4)),'val_of'('BankSystem','src_span'(136,18,136,28,10,10)).
'bindval'('SPEC2','agent_call'('src_span'(140,9,140,19,10,10),'NEWSPEC',['int'(0)]),'src_span'(140,1,140,19,18,18)).
'agent'('NEWSPEC'(_num4),'[]'('&'(_num4,'val_of'('maxLimit','src_span'(142,10,142,18,8,8)),'prefix'('src_span'(142,21,142,30,9,9),['in'(_cc5)],'enterBank','tupleExp'(['|~|'('prefix'('src_span'(143,12,143,26,14,14),[],'dotTuple'(['report','success']),'agent_call'('src_span'(143,30,143,44,14,14),'NEWSPEC',[_num4,'int'(1)]),'src_span'(143,26,143,30,4,4)),'prefix'('src_span'(143,49,143,60,11,11),[],'dotTuple'(['report','fail']),'agent_call'('src_span'(143,64,143,76,12,12),'NEWSPEC',[_num4]),'src_span'(143,60,143,64,4,4)),'src_span_operator'('no_loc_info_available','src_span'(143,44,143,49,5,5)))]),'src_span'(142,34,142,50,16,16))),'&'(_num4,'int'(0),'prefix'('src_span'(145,14,145,48,34,34),[],'tupleExp'(['repInternalChoice'(['comprehensionGenerator'(_cc5,'src_span'(145,23,145,31,8,8),[],'CUSTOMER')],'src_span'(145,34,145,47,13,13),[],'dotTuple'(['leaveBank',_cc5]),'src_span'(145,19,145,31,12,12))]),'agent_call'('src_span'(145,52,145,66,14,14),'NEWSPEC',[_num4,'int'(1)]),'src_span'(145,48,145,52,4,4))),'src_span_operator'('no_loc_info_available','src_span'(143,77,143,89,12,12))),'src_span'(141,1,145,66,65,65)).
'val_of'('SPEC2','src_span'(147,8,147,13,5,5)),'val_of'('BankSystem','src_span'(147,18,147,28,10,10)).
'val_of'('SPEC2','src_span'(148,8,148,13,5,5)),'val_of'('BankSystem','src_span'(148,18,148,28,10,10)).
'channel'('a','type'('dotUnitType')).
'bindval'('div'('src_span'(153,1,153,4,3,3)),'DIV','\'('val_of'('LOOP','src_span'(153,7,153,11,4,4)),'a','src_span_operator'('no_loc_info_available','src_span'(153,11,153,14,3,3))),'src_span'(153,1,153,17,16,16)).
'bindval'('LOOP','prefix'('src_span'(154,8,154,9,1,1),[],'a','val_of'('LOOP','src_span'(154,13,154,17,4,4)),'src_span'(154,9,154,13,4,4)),'src_span'(154,1,154,17,16,16)).
'symbol'('defaultCustomer','defaultCustomer','src_span'(13,1,13,16,15,15),'Ident (Groundrep.)').
'symbol'('num','num','src_span'(51,13,51,16,3,3),'Ident (Prolog Variable)').
'symbol'('num2','num','src_span'(59,10,59,13,3,3),'Ident (Prolog Variable)').
'symbol'('num3','num','src_span'(82,11,82,14,3,3),'Ident (Prolog Variable)').
'symbol'('num4','num','src_span'(141,9,141,12,3,3),'Ident (Prolog Variable)').
'symbol'('LOOP','LOOP','src_span'(154,1,154,5,4,4),'Ident (Groundrep.)').
'symbol'('NextQCtrl','NextQCtrl','src_span'(109,1,109,10,9,9),'Function or Process').
'symbol'('JoinCtrl','JoinCtrl','src_span'(59,1,59,9,8,8),'Function or Process').
'symbol'('STATUS','STATUS','src_span'(8,10,8,16,6,6),'Datatype').
'symbol'('CUSTOMER','CUSTOMER','src_span'(7,10,7,18,8,8),'Datatype').
'symbol'('numQueues','numQueues','src_span'(15,1,15,10,9,9),'Ident (Groundrep.)').
'symbol'('SPEC','SPEC','src_span'(127,1,127,5,4,4),'Ident (Groundrep.)').
'symbol'('currentCust','currentCust','src_span'(51,18,51,29,11,11),'Ident (Prolog Variable)').
'symbol'('currentCust2','currentCust','src_span'(59,14,59,25,11,11),'Ident (Prolog Variable)').
'symbol'('currentCust3','currentCust','src_span'(82,15,82,26,11,11),'Ident (Prolog Variable)').
'symbol'('CounterCtrl','CounterCtrl','src_span'(49,1,49,12,11,11),'Ident (Groundrep.)').
'symbol'('queryQueueEmpty','queryQueueEmpty','src_span'(32,9,32,24,15,15),'Channel').
'symbol'('c1','c1','src_span'(7,21,7,23,2,2),'Constructor of Datatype').
'symbol'('c2','c2','src_span'(7,26,7,28,2,2),'Constructor of Datatype').
'symbol'('c3','c3','src_span'(7,31,7,33,2,2),'Constructor of Datatype').
'symbol'('fail','fail','src_span'(8,29,8,33,4,4),'Constructor of Datatype').
'symbol'('maxQueueingCustomers','maxQueueingCustomers','src_span'(14,1,14,21,20,20),'Ident (Groundrep.)').
'symbol'('custSet','custSet','src_span'(92,17,92,24,7,7),'Ident (Prolog Variable)').
'symbol'('custSet2','custSet','src_span'(109,21,109,28,7,7),'Ident (Prolog Variable)').
'symbol'('success','success','src_span'(8,19,8,26,7,7),'Constructor of Datatype').
'symbol'('canJoinResponse','canJoinResponse','src_span'(26,9,26,24,15,15),'Channel').
'symbol'('QSTATUS','QSTATUS','src_span'(9,10,9,17,7,7),'Datatype').
'symbol'('queueNo','queueNo','src_span'(35,5,35,12,7,7),'Ident (Prolog Variable)').
'symbol'('queueNo2','queueNo','src_span'(92,9,92,16,7,7),'Ident (Prolog Variable)').
'symbol'('queueNo3','queueNo','src_span'(109,13,109,20,7,7),'Ident (Prolog Variable)').
'symbol'('joinQueue','joinQueue','src_span'(30,9,30,18,9,9),'Channel').
'symbol'('bb','bb','src_span'(64,42,64,44,2,2),'Ident (Prolog Variable)').
'symbol'('bb2','bb','src_span'(110,36,110,38,2,2),'Ident (Prolog Variable)').
'symbol'('no','no','src_span'(9,25,9,27,2,2),'Constructor of Datatype').
'symbol'('A','A','src_span'(121,1,121,2,1,1),'Ident (Groundrep.)').
'symbol'('B','B','src_span'(122,1,122,2,1,1),'Ident (Groundrep.)').
'symbol'('QCtrl','QCtrl','src_span'(92,1,92,6,5,5),'Function or Process').
'symbol'('QueuesCtrl','QueuesCtrl','src_span'(91,1,91,11,10,10),'Ident (Groundrep.)').
'symbol'('CUST','CUST','src_span'(128,1,128,5,4,4),'Ident (Groundrep.)').
'symbol'('LeaveCtrl','LeaveCtrl','src_span'(82,1,82,10,9,9),'Function or Process').
'symbol'('CurrentCtrl','CurrentCtrl','src_span'(51,1,51,12,11,11),'Function or Process').
'symbol'('inc','inc','src_span'(35,1,35,4,3,3),'Function or Process').
'symbol'('cc','cc','src_span'(60,31,60,33,2,2),'Ident (Prolog Variable)').
'symbol'('cc2','cc','src_span'(85,70,85,72,2,2),'Ident (Prolog Variable)').
'symbol'('cc3','cc','src_span'(93,41,93,43,2,2),'Ident (Prolog Variable)').
'symbol'('cc4','cc','src_span'(112,33,112,35,2,2),'Ident (Prolog Variable)').
'symbol'('cc5','cc','src_span'(142,32,142,34,2,2),'Ident (Prolog Variable)').
'symbol'('SPEC2','SPEC2','src_span'(140,1,140,6,5,5),'Ident (Groundrep.)').
'symbol'('a','a','src_span'(152,9,152,10,1,1),'Channel').
'symbol'('yes','yes','src_span'(9,19,9,22,3,3),'Constructor of Datatype').
'symbol'('enterBank','enterBank','src_span'(19,9,19,18,9,9),'Channel').
'symbol'('QUEUENUM','QUEUENUM','src_span'(6,1,6,9,8,8),'Ident (Groundrep.)').
'symbol'('leaveQueue','leaveQueue','src_span'(31,9,31,19,10,10),'Channel').
'symbol'('i','i','src_span'(128,18,128,19,1,1),'Ident (Prolog Variable)').
'symbol'('NEWSPEC','NEWSPEC','src_span'(141,1,141,8,7,7),'Function or Process').
'symbol'('leaveBank','leaveBank','src_span'(20,9,20,18,9,9),'Channel').
'symbol'('defaultCounter','defaultCounter','src_span'(12,1,12,15,14,14),'Ident (Groundrep.)').
'symbol'('DIV','DIV','src_span'(153,1,153,4,3,3),'Ident (Groundrep.)').
'symbol'('retrieveCustomer','retrieveCustomer','src_span'(27,9,27,25,16,16),'Channel').
'symbol'('BankSystem','BankSystem','src_span'(123,1,123,11,10,10),'Ident (Groundrep.)').
'symbol'('s','s','src_span'(92,7,92,8,1,1),'Ident (Prolog Variable)').
'symbol'('s2','s','src_span'(109,11,109,12,1,1),'Ident (Prolog Variable)').
'symbol'('maxLimit','maxLimit','src_span'(11,1,11,9,8,8),'Ident (Groundrep.)').
'symbol'('report','report','src_span'(21,9,21,15,6,6),'Channel').
'symbol'('com3','com3','src_span'(25,9,25,13,4,4),'Channel').
'symbol'('com1','com1','src_span'(24,9,24,13,4,4),'Channel').

