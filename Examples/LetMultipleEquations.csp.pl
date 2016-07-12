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
'channel'('req','type'('dotTupleType'(['setExp'('int'(0),'int'(9))]))).
'channel'('ack','type'('dotTupleType'(['setExp'('int'(0),'int'(9))]))).
'channel'('out','type'('dotTupleType'(['setExp'('int'(0),'int'(9))]))).
'agent'('other'(_req2),'ack','src_span'(6,1,6,17,16,16)).
'agent'('other'(_ack2),'out','src_span'(7,1,7,17,16,16)).
'agent'('other'(_out2),'req','src_span'(8,1,8,17,16,16)).
'agent'('Proc1'(_ch,_V),'prefix'('src_span'(11,15,11,24,9,9),['out'(_V)],'agent_call'('src_span'(11,15,11,24,9,9),'other',['src_span'(11,21,11,23,2,2),[],_ch]),'agent_call'('src_span'(11,30,11,48,18,18),'Proc1',['agent_call'('src_span'(11,36,11,45,9,9),'other',[_ch]),_V]),'src_span'(11,26,11,30,4,4)),'src_span'(11,1,11,48,47,47)).
'agent'('Proc2'(_ch2,_V2),'prefix'('src_span'(12,15,12,17,2,2),['out'(_V2)],_ch2,'agent_call'('src_span'(12,23,12,41,18,18),'Proc2',['agent_call'('src_span'(12,29,12,38,9,9),'other',[_ch2]),_V2]),'src_span'(12,19,12,23,4,4)),'src_span'(12,1,12,41,40,40)).
'agent'('Proc3'(_x,_Y),'let'(['agent'('other2'(_req3),'out','src_span'(15,3,15,20,17,17)),'agent'('other2'(_ack3),'req','src_span'(16,3,16,20,17,17)),'agent'('other2'(_out3),'ack','src_span'(17,3,17,20,17,17)),'agent'('Proc4'(_ch3,_V3),'prefix'('src_span'(18,17,18,19,2,2),['out'(_V3)],_ch3,'agent_call'('src_span'(18,25,18,44,19,19),'Proc4',['agent_call'('src_span'(18,31,18,41,10,10),'other2',[_ch3]),_V3]),'src_span'(18,21,18,25,4,4)),'src_span'(18,3,18,44,41,41))],'agent_call'('src_span'(19,11,19,21,10,10),'Proc4',[_x,_Y])),'src_span'(14,1,19,21,20,20)).
'agent'('Proc5'(_x2,_Y2),'let'(['agent'('other22'(_req4),'ack','src_span'(23,3,23,20,17,17)),'agent'('other22'(_ack4),'out','src_span'(24,3,24,20,17,17)),'agent'('other22'(_out4),'ack','src_span'(25,3,25,20,17,17)),'agent'('Proc6'(_ch4,_V4),'prefix'('src_span'(26,17,26,27,10,10),['out'(_V4)],'agent_call'('src_span'(26,17,26,27,10,10),'other22',['src_span'(26,24,26,26,2,2),[],_ch4]),'agent_call'('src_span'(26,33,26,52,19,19),'Proc6',['agent_call'('src_span'(26,39,26,49,10,10),'other22',[_ch4]),_V4]),'src_span'(26,29,26,33,4,4)),'src_span'(26,3,26,52,49,49))],'agent_call'('src_span'(27,11,27,21,10,10),'Proc6',[_x2,_Y2])),'src_span'(22,1,27,21,20,20)).
'bindval'('MAIN','[]'('[]'('[]'('agent_call'('src_span'(30,8,30,20,12,12),'Proc2',['req','int'(1)]),'agent_call'('src_span'(30,24,30,36,12,12),'Proc1',['req','int'(2)]),'src_span_operator'('no_loc_info_available','src_span'(30,20,30,24,4,4))),'agent_call'('src_span'(30,40,30,52,12,12),'Proc3',['req','int'(3)]),'src_span_operator'('no_loc_info_available','src_span'(30,36,30,40,4,4))),'agent_call'('src_span'(30,56,30,68,12,12),'Proc5',['req','int'(5)]),'src_span_operator'('no_loc_info_available','src_span'(30,52,30,56,4,4))),'src_span'(30,1,30,68,67,67)).
'symbol'('Proc2','Proc2','src_span'(12,1,12,6,5,5),'Function or Process').
'symbol'('Proc3','Proc3','src_span'(14,1,14,6,5,5),'Function or Process').
'symbol'('other','other','src_span'(6,1,6,6,5,5),'Function or Process').
'symbol'('Proc4','Proc4','src_span'(18,3,18,8,5,5),'Function or Process').
'symbol'('Proc5','Proc5','src_span'(22,1,22,6,5,5),'Function or Process').
'symbol'('ch','ch','src_span'(11,7,11,9,2,2),'Ident (Prolog Variable)').
'symbol'('ch2','ch','src_span'(12,7,12,9,2,2),'Ident (Prolog Variable)').
'symbol'('ch3','ch','src_span'(18,9,18,11,2,2),'Ident (Prolog Variable)').
'symbol'('ch4','ch','src_span'(26,9,26,11,2,2),'Ident (Prolog Variable)').
'symbol'('Proc1','Proc1','src_span'(11,1,11,6,5,5),'Function or Process').
'symbol'('ack','ack','src_span'(3,13,3,16,3,3),'Channel').
'symbol'('ack2','ack','src_span'(7,7,7,10,3,3),'Ident (Prolog Variable)').
'symbol'('ack3','ack','src_span'(16,10,16,13,3,3),'Ident (Prolog Variable)').
'symbol'('ack4','ack','src_span'(24,10,24,13,3,3),'Ident (Prolog Variable)').
'symbol'('out','out','src_span'(3,17,3,20,3,3),'Channel').
'symbol'('out2','out','src_span'(8,7,8,10,3,3),'Ident (Prolog Variable)').
'symbol'('out3','out','src_span'(17,10,17,13,3,3),'Ident (Prolog Variable)').
'symbol'('out4','out','src_span'(25,10,25,13,3,3),'Ident (Prolog Variable)').
'symbol'('V','V','src_span'(11,10,11,11,1,1),'Ident (Prolog Variable)').
'symbol'('V2','V','src_span'(12,10,12,11,1,1),'Ident (Prolog Variable)').
'symbol'('V3','V','src_span'(18,12,18,13,1,1),'Ident (Prolog Variable)').
'symbol'('V4','V','src_span'(26,12,26,13,1,1),'Ident (Prolog Variable)').
'symbol'('x','x','src_span'(14,7,14,8,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(22,7,22,8,1,1),'Ident (Prolog Variable)').
'symbol'('Y','Y','src_span'(14,9,14,10,1,1),'Ident (Prolog Variable)').
'symbol'('Y2','Y','src_span'(22,9,22,10,1,1),'Ident (Prolog Variable)').
'symbol'('MAIN','MAIN','src_span'(30,1,30,5,4,4),'Ident (Groundrep.)').
'symbol'('Proc6','Proc6','src_span'(26,3,26,8,5,5),'Function or Process').
'symbol'('req','req','src_span'(3,9,3,12,3,3),'Channel').
'symbol'('req2','req','src_span'(6,7,6,10,3,3),'Ident (Prolog Variable)').
'symbol'('req3','req','src_span'(15,10,15,13,3,3),'Ident (Prolog Variable)').
'symbol'('req4','req','src_span'(23,10,23,13,3,3),'Ident (Prolog Variable)').
'symbol'('other2','other2','src_span'(15,3,15,9,6,6),'Function or Process').
'symbol'('other22','other2','src_span'(23,3,23,9,6,6),'Function or Process').

