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
'channel'('in','type'('dotTupleType'(['setExp'('int'(0),'int'(999))]))).
'channel'('out','type'('dotTupleType'(['setExp'('int'(0),'int'(999))]))).
'agent'('Buff'(_MSeq),'ifte'(_MSeq,'prefix'('src_span'(6,18,6,20,2,2),['in'(_x)],'in','agent_call'('src_span'(6,26,6,35,9,9),'Buff',[_x]),'src_span'(6,22,6,26,4,4)),'prefix'('src_span'(8,18,8,21,3,3),['out'('agent_call'('src_span'(8,22,8,32,10,10),'head',['src_span'(8,27,8,31,4,4),[],_MSeq]))],'out','agent_call'('src_span'(8,36,8,52,16,16),'Buff',['agent_call'('src_span'(8,41,8,51,10,10),'tail',[_MSeq])]),'src_span'(8,32,8,36,4,4)),'src_span'(5,14,5,16,2,2),'src_span'(5,25,5,49,24,24),'src_span'(6,35,6,73,38,38)),'src_span'(5,1,8,52,51,51)).
'bindval'('Test','agent_call'('src_span'(11,8,11,23,15,15),'Buff',['int'(1),'int'(2),'int'(3),'int'(4)]),'src_span'(11,1,11,23,22,22)).
'bindval'('Test2','agent_call'('src_span'(13,9,13,40,31,31),'Buff',['agent_call'('src_span'(13,14,13,39,25,25),'concat',['int'(1),'int'(2),'int'(3),'int'(4),'int'(5),'int'(6)])]),'src_span'(13,1,13,40,39,39)).
'bindval'('SYSTEM','sharing'('agent_call'('src_span'(15,11,15,19,8,8),'Buff',[]),'in','prefix'('src_span'(15,33,15,35,2,2),['out'('int'(2))],'in','prefix'('src_span'(15,41,15,43,2,2),['out'('int'(4))],'in','prefix'('src_span'(15,49,15,51,2,2),['out'('int'(8))],'in','prefix'('src_span'(15,57,15,59,2,2),['out'('int'(16))],'in','stop'('src_span'(15,65,15,69,4,4)),'src_span'(15,62,15,65,3,3)),'src_span'(15,53,15,57,4,4)),'src_span'(15,45,15,49,4,4)),'src_span'(15,37,15,41,4,4)),'src_span'(15,19,15,23,4,4)),'src_span'(15,1,15,69,68,68)).
'bindval'('MAIN',';'('val_of'('TestElem','src_span'(17,8,17,16,8,8)),'val_of'('SYSTEM','src_span'(17,19,17,25,6,6)),'src_span_operator'('no_loc_info_available','src_span'(17,16,17,19,3,3))),'src_span'(17,1,17,25,24,24)).
'agent'('Check'(_x2,_MSeq2),'ifte'('agent_call'('src_span'(19,20,19,32,12,12),'elem',[_x2,_MSeq2]),'prefix'('src_span'(19,38,19,41,3,3),['out'('int'(1))],'out','skip'('src_span'(19,47,19,51,4,4)),'src_span'(19,43,19,47,4,4)),'prefix'('src_span'(19,57,19,60,3,3),['out'('int'(0))],'out','skip'('src_span'(19,66,19,70,4,4)),'src_span'(19,62,19,66,4,4)),'src_span'(19,17,19,19,2,2),'src_span'(19,32,19,38,6,6),'src_span'(19,51,19,57,6,6)),'src_span'(19,1,19,70,69,69)).
'bindval'('TestElem',';'(';'(';'('agent_call'('src_span'(20,12,20,30,18,18),'Check',['int'(3),'int'(1),'int'(2),'int'(3),'int'(4)]),'agent_call'('src_span'(20,33,20,51,18,18),'Check',['int'(1),'int'(1),'int'(2),'int'(3),'int'(4)]),'src_span_operator'('no_loc_info_available','src_span'(20,30,20,33,3,3))),'agent_call'('src_span'(20,54,20,72,18,18),'Check',['int'(5),'int'(1),'int'(2),'int'(3),'int'(4)]),'src_span_operator'('no_loc_info_available','src_span'(20,51,20,54,3,3))),'agent_call'('src_span'(20,75,20,86,11,11),'Check',['int'(5)]),'src_span_operator'('no_loc_info_available','src_span'(20,72,20,75,3,3))),'src_span'(20,1,20,86,85,85)).
'bindval'('GetLen','prefix'('src_span'(21,10,21,13,3,3),['out'('agent_call'('src_span'(21,14,21,31,17,17),'length',['src_span'(21,21,21,30,9,9),[],'src_span'(21,22,21,23,1,1),[],'int'(1),'src_span'(21,24,21,25,1,1),[],'int'(2),'src_span'(21,26,21,27,1,1),[],'int'(3),'src_span'(21,28,21,29,1,1),[],'int'(4)]))],'out','prefix'('src_span'(21,35,21,38,3,3),['out'('src_span'(21,41,21,42,1,1),[],'int'(1),'src_span'(21,43,21,44,1,1),[],'int'(2),'src_span'(21,45,21,46,1,1),[],'int'(3))],'out','prefix'('src_span'(21,51,21,54,3,3),['out'('agent_call'('src_span'(21,55,21,65,10,10),'length',['src_span'(21,62,21,64,2,2),[]]))],'out','skip'('src_span'(21,69,21,73,4,4)),'src_span'(21,65,21,69,4,4)),'src_span'(21,47,21,51,4,4)),'src_span'(21,31,21,35,4,4)),'src_span'(21,1,21,73,72,72)).
'agent'('palin'(_x3,_y),'tupleExp'([_x3,_y]),'src_span'(25,1,25,22,21,21)).
'agent'('palin'(_x4,_z,_y2),'tupleExp'([_x4,_y2]),'src_span'(26,1,26,24,23,23)).
'agent'('palin','true','src_span'(27,1,27,16,15,15)).
'agent'('TestPalin'(_s),'ifte'('agent_call'('src_span'(29,19,29,27,8,8),'palin',[_s]),'prefix'('src_span'(29,33,29,36,3,3),['out'('int'(1))],'out','skip'('src_span'(29,42,29,46,4,4)),'src_span'(29,38,29,42,4,4)),'prefix'('src_span'(29,52,29,55,3,3),['out'('int'(0))],'out','skip'('src_span'(29,61,29,65,4,4)),'src_span'(29,57,29,61,4,4)),'src_span'(29,16,29,18,2,2),'src_span'(29,27,29,33,6,6),'src_span'(29,46,29,52,6,6)),'src_span'(29,1,29,65,64,64)).
'bindval'('TestP',';'(';'('agent_call'('src_span'(30,9,30,27,18,18),'TestPalin',['int'(1),'int'(2),'int'(1)]),'agent_call'('src_span'(30,30,30,50,20,20),'TestPalin',['^'('int'(1),'int'(1),'int'(2))]),'src_span_operator'('no_loc_info_available','src_span'(30,27,30,30,3,3))),'agent_call'('src_span'(30,53,30,69,16,16),'TestPalin',['int'(1),'int'(3)]),'src_span_operator'('no_loc_info_available','src_span'(30,50,30,53,3,3))),'src_span'(30,1,30,69,68,68)).
'symbol'('SYSTEM','SYSTEM','src_span'(15,1,15,7,6,6),'Ident (Groundrep.)').
'symbol'('in','in','src_span'(3,9,3,11,2,2),'Channel').
'symbol'('Check','Check','src_span'(19,1,19,6,5,5),'Function or Process').
'symbol'('Buff','Buff','src_span'(5,1,5,5,4,4),'Function or Process').
'symbol'('out','out','src_span'(3,12,3,15,3,3),'Channel').
'symbol'('Test2','Test2','src_span'(13,1,13,6,5,5),'Ident (Groundrep.)').
'symbol'('TestPalin','TestPalin','src_span'(29,1,29,10,9,9),'Function or Process').
'symbol'('TestP','TestP','src_span'(30,1,30,6,5,5),'Ident (Groundrep.)').
'symbol'('GetLen','GetLen','src_span'(21,1,21,7,6,6),'Ident (Groundrep.)').
'symbol'('s','s','src_span'(29,11,29,12,1,1),'Ident (Prolog Variable)').
'symbol'('Test','Test','src_span'(11,1,11,5,4,4),'Ident (Groundrep.)').
'symbol'('MSeq','MSeq','src_span'(5,6,5,10,4,4),'Ident (Prolog Variable)').
'symbol'('MSeq2','MSeq','src_span'(19,9,19,13,4,4),'Ident (Prolog Variable)').
'symbol'('x','x','src_span'(6,21,6,22,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(19,7,19,8,1,1),'Ident (Prolog Variable)').
'symbol'('x3','x','src_span'(25,8,25,9,1,1),'Ident (Prolog Variable)').
'symbol'('x4','x','src_span'(26,8,26,9,1,1),'Ident (Prolog Variable)').
'symbol'('TestElem','TestElem','src_span'(20,1,20,9,8,8),'Ident (Groundrep.)').
'symbol'('palin','palin','src_span'(25,1,25,6,5,5),'Function or Process').
'symbol'('y','y','src_span'(25,10,25,11,1,1),'Ident (Prolog Variable)').
'symbol'('y2','y','src_span'(26,12,26,13,1,1),'Ident (Prolog Variable)').
'symbol'('MAIN','MAIN','src_span'(17,1,17,5,4,4),'Ident (Groundrep.)').
'symbol'('z','z','src_span'(26,10,26,11,1,1),'Ident (Prolog Variable)').

