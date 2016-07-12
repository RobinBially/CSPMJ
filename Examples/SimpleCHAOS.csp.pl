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
'channel'('in','type'('dotTupleType'(['setExp'('int'(1),'int'(3)),'setExp'('int'(1),'int'(3))]))).
'channel'('out','type'('dotTupleType'(['setExp'('int'(1),'int'(3)),'setExp'('int'(1),'int'(3))]))).
'bindval'('Test1','prefix'('src_span'(5,9,5,11,2,2),['in'(_x),'out'('int'(1))],'in','agent_call'('src_span'(5,19,5,39,20,20),['dotTuple'(['out',_x,'int'(2)])]),'src_span'(5,15,5,19,4,4)),'src_span'(5,1,5,39,38,38)).
'bindval'('Test2','prefix'('src_span'(6,10,6,12,2,2),['in'(_x2),'out'('int'(1))],'in','agent_call'('src_span'(6,20,6,38,18,18),['dotTuple'(['out',_x2])]),'src_span'(6,16,6,20,4,4)),'src_span'(6,1,6,38,37,37)).
'bindval'('Test2b','prefix'('src_span'(7,11,7,13,2,2),['in'(_x3),'out'('int'(1))],'in','agent_call'('src_span'(7,21,7,41,20,20),['dotTuple'(['out',_x3,'int'(1)])]),'src_span'(7,17,7,21,4,4)),'src_span'(7,1,7,41,40,40)).
'bindval'('Test3','prefix'('src_span'(8,10,8,12,2,2),['in'(_x4)],'in','agent_call'('src_span'(8,18,8,36,18,18),['dotTuple'(['out',_x4])]),'src_span'(8,14,8,18,4,4)),'src_span'(8,1,8,36,35,35)).
'bindval'('Test4','prefix'('src_span'(10,9,10,11,2,2),['in'(_x5)],'in','agent_call'('src_span'(10,17,10,30,13,13),['events'('src_span'(10,23,10,29,6,6))]),'src_span'(10,13,10,17,4,4)),'src_span'(10,1,10,30,29,29)).
'bindval'('Test5','prefix'('src_span'(11,9,11,11,2,2),['in'(_x6),'out'('int'(1))],'in','agent_call'('src_span'(11,19,11,48,29,29),['agent_call'('src_span'(11,25,11,47,22,22),'diff',['events'('src_span'(11,30,11,36,6,6)),'dotTuple'(['out',_x6])])]),'src_span'(11,15,11,19,4,4)),'src_span'(11,1,11,48,47,47)).
'bindval'('Test5b','prefix'('src_span'(12,10,12,12,2,2),['out'('int'(1)),'out'('int'(2))],'in','agent_call'('src_span'(12,20,12,47,27,27),['agent_call'('src_span'(12,26,12,46,20,20),'diff',['events'('src_span'(12,31,12,37,6,6)),'out'])]),'src_span'(12,16,12,20,4,4)),'src_span'(12,1,12,47,46,46)).
'bindval'('Test5c','sharing'('val_of'('Test5b','src_span'(13,10,13,16,6,6)),'in','out','prefix'('src_span'(14,9,14,11,2,2),['in'(_x7),'in'(_y)],'in','agent_call'('src_span'(14,19,14,27,8,8),'Gen',[_x7,_y]),'src_span'(14,15,14,19,4,4)),'src_span'(13,16,13,20,4,4)),'src_span'(13,1,14,27,26,26)).
'agent'('Gen'(_x8,_y2),'[]'('[]'('[]'('[]'('prefix'('src_span'(15,12,15,14,2,2),['out'(_x8),'out'(_y2)],'in','agent_call'('src_span'(15,22,15,30,8,8),'Gen',[_x8,_y2]),'src_span'(15,18,15,22,4,4)),'prefix'('src_span'(15,34,15,36,2,2),['out'(_y2),'out'(_x8)],'in','agent_call'('src_span'(15,44,15,52,8,8),'Gen',[_x8,_y2]),'src_span'(15,40,15,44,4,4)),'src_span_operator'('no_loc_info_available','src_span'(15,30,15,34,4,4))),'prefix'('src_span'(15,56,15,58,2,2),['out'(_x8),'out'(_x8)],'in','agent_call'('src_span'(15,66,15,74,8,8),'Gen',[_x8,_y2]),'src_span'(15,62,15,66,4,4)),'src_span_operator'('no_loc_info_available','src_span'(15,52,15,56,4,4))),'prefix'('src_span'(15,78,15,80,2,2),['out'(_y2),'out'(_y2)],'in','agent_call'('src_span'(15,88,15,96,8,8),'Gen',[_x8,_y2]),'src_span'(15,84,15,88,4,4)),'src_span_operator'('no_loc_info_available','src_span'(15,74,15,78,4,4))),'prefix'('src_span'(15,100,15,103,3,3),['out'(_x8),'out'(_y2)],'out','agent_call'('src_span'(15,111,15,119,8,8),'Gen',[_x8,_y2]),'src_span'(15,107,15,111,4,4)),'src_span_operator'('no_loc_info_available','src_span'(15,96,15,100,4,4))),'src_span'(15,1,15,119,118,118)).
'bindval'('Test6','prefix'('src_span'(16,9,16,11,2,2),['in'(_x9)],'in','agent_call'('src_span'(16,17,16,46,29,29),['agent_call'('src_span'(16,23,16,45,22,22),'diff',['events'('src_span'(16,28,16,34,6,6)),'dotTuple'(['out',_x9])])]),'src_span'(16,13,16,17,4,4)),'src_span'(16,1,16,46,45,45)).
'bindval'('Test7','agent_call'('src_span'(17,9,17,26,17,17),['in','out']),'src_span'(17,1,17,26,25,25)).
'bindval'('MAIN','val_of'('Test5c','src_span'(19,8,19,14,6,6)),'src_span'(19,1,19,14,13,13)).
'bindval'('MAIN2','val_of'('Test3','src_span'(21,9,21,14,5,5)),'src_span'(21,1,21,14,13,13)).
'symbol'('MAIN2','MAIN2','src_span'(21,1,21,6,5,5),'Ident (Groundrep.)').
'symbol'('Test5b','Test5b','src_span'(12,1,12,7,6,6),'Ident (Groundrep.)').
'symbol'('in','in','src_span'(3,9,3,11,2,2),'Channel').
'symbol'('Test7','Test7','src_span'(17,1,17,6,5,5),'Ident (Groundrep.)').
'symbol'('Test6','Test6','src_span'(16,1,16,6,5,5),'Ident (Groundrep.)').
'symbol'('Test5','Test5','src_span'(11,1,11,6,5,5),'Ident (Groundrep.)').
'symbol'('Test4','Test4','src_span'(10,1,10,6,5,5),'Ident (Groundrep.)').
'symbol'('Test3','Test3','src_span'(8,1,8,6,5,5),'Ident (Groundrep.)').
'symbol'('out','out','src_span'(3,12,3,15,3,3),'Channel').
'symbol'('Test2','Test2','src_span'(6,1,6,6,5,5),'Ident (Groundrep.)').
'symbol'('Test1','Test1','src_span'(5,1,5,6,5,5),'Ident (Groundrep.)').
'symbol'('Gen','Gen','src_span'(15,1,15,4,3,3),'Function or Process').
'symbol'('x','x','src_span'(5,12,5,13,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(6,13,6,14,1,1),'Ident (Prolog Variable)').
'symbol'('x3','x','src_span'(7,14,7,15,1,1),'Ident (Prolog Variable)').
'symbol'('x4','x','src_span'(8,13,8,14,1,1),'Ident (Prolog Variable)').
'symbol'('x5','x','src_span'(10,12,10,13,1,1),'Ident (Prolog Variable)').
'symbol'('x6','x','src_span'(11,12,11,13,1,1),'Ident (Prolog Variable)').
'symbol'('x7','x','src_span'(14,12,14,13,1,1),'Ident (Prolog Variable)').
'symbol'('x8','x','src_span'(15,5,15,6,1,1),'Ident (Prolog Variable)').
'symbol'('x9','x','src_span'(16,12,16,13,1,1),'Ident (Prolog Variable)').
'symbol'('y','y','src_span'(14,14,14,15,1,1),'Ident (Prolog Variable)').
'symbol'('y2','y','src_span'(15,7,15,8,1,1),'Ident (Prolog Variable)').
'symbol'('MAIN','MAIN','src_span'(19,1,19,5,4,4),'Ident (Groundrep.)').
'symbol'('Test5c','Test5c','src_span'(13,1,13,7,6,6),'Ident (Groundrep.)').
'symbol'('Test2b','Test2b','src_span'(7,1,7,7,6,6),'Ident (Groundrep.)').

