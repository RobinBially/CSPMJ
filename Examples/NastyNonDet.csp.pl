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
'channel'('out','type'('dotTupleType'(['setExp'('int'(0),'int'(999))]))).
'agent'('Gen'(_x),'[]'('[]'('[]'('[]'('[]'('[]'('[]'('[]'('prefix'('src_span'(5,10,5,13,3,3),['out'('int'(1),_x)],'out','val_of'('GenTop','src_span'(5,21,5,27,6,6)),'src_span'(5,17,5,21,4,4)),'prefix'('src_span'(5,31,5,34,3,3),['out'('int'(2),_x)],'out','val_of'('GenTop','src_span'(5,42,5,48,6,6)),'src_span'(5,38,5,42,4,4)),'src_span_operator'('no_loc_info_available','src_span'(5,27,5,31,4,4))),'prefix'('src_span'(5,52,5,55,3,3),['out'('int'(3),_x)],'out','val_of'('GenTop','src_span'(5,63,5,69,6,6)),'src_span'(5,59,5,63,4,4)),'src_span_operator'('no_loc_info_available','src_span'(5,48,5,52,4,4))),'prefix'('src_span'(6,10,6,13,3,3),['out'('int'(4),_x)],'out','val_of'('GenTop','src_span'(6,21,6,27,6,6)),'src_span'(6,17,6,21,4,4)),'src_span_operator'('no_loc_info_available','src_span'(5,69,5,83,14,14))),'prefix'('src_span'(6,31,6,34,3,3),['out'('int'(5),_x)],'out','val_of'('GenTop','src_span'(6,42,6,48,6,6)),'src_span'(6,38,6,42,4,4)),'src_span_operator'('no_loc_info_available','src_span'(6,27,6,31,4,4))),'prefix'('src_span'(6,52,6,55,3,3),['out'('int'(6),_x)],'out','val_of'('GenTop','src_span'(6,63,6,69,6,6)),'src_span'(6,59,6,63,4,4)),'src_span_operator'('no_loc_info_available','src_span'(6,48,6,52,4,4))),'prefix'('src_span'(7,10,7,13,3,3),['out'('int'(7),_x)],'out','val_of'('GenTop','src_span'(7,21,7,27,6,6)),'src_span'(7,17,7,21,4,4)),'src_span_operator'('no_loc_info_available','src_span'(6,69,6,83,14,14))),'prefix'('src_span'(7,31,7,34,3,3),['out'('int'(8),_x)],'out','val_of'('GenTop','src_span'(7,42,7,48,6,6)),'src_span'(7,38,7,42,4,4)),'src_span_operator'('no_loc_info_available','src_span'(7,27,7,31,4,4))),'prefix'('src_span'(7,52,7,55,3,3),['out'('int'(9),_x)],'out','val_of'('GenTop','src_span'(7,63,7,69,6,6)),'src_span'(7,59,7,63,4,4)),'src_span_operator'('no_loc_info_available','src_span'(7,48,7,52,4,4))),'src_span'(5,1,7,69,68,68)).
'agent'('Gen2'(_x2),'[]'('[]'('[]'('[]'('[]'('[]'('[]'('[]'('[]'('agent_call'('src_span'(9,13,9,19,6,6),'Gen',['int'(0)]),'agent_call'('src_span'(9,23,9,29,6,6),'Gen',[_x2]),'src_span_operator'('no_loc_info_available','src_span'(9,19,9,23,4,4))),'agent_call'('src_span'(9,33,9,41,8,8),'Gen',['int'(2),_x2]),'src_span_operator'('no_loc_info_available','src_span'(9,29,9,33,4,4))),'agent_call'('src_span'(10,13,10,21,8,8),'Gen',['int'(3),_x2]),'src_span_operator'('no_loc_info_available','src_span'(9,41,9,58,17,17))),'agent_call'('src_span'(10,25,10,33,8,8),'Gen',['int'(4),_x2]),'src_span_operator'('no_loc_info_available','src_span'(10,21,10,25,4,4))),'agent_call'('src_span'(10,37,10,45,8,8),'Gen',['int'(5),_x2]),'src_span_operator'('no_loc_info_available','src_span'(10,33,10,37,4,4))),'agent_call'('src_span'(10,49,10,57,8,8),'Gen',['int'(6),_x2]),'src_span_operator'('no_loc_info_available','src_span'(10,45,10,49,4,4))),'agent_call'('src_span'(11,13,11,21,8,8),'Gen',['int'(7),_x2]),'src_span_operator'('no_loc_info_available','src_span'(10,57,10,74,17,17))),'agent_call'('src_span'(11,25,11,33,8,8),'Gen',['int'(8),_x2]),'src_span_operator'('no_loc_info_available','src_span'(11,21,11,25,4,4))),'agent_call'('src_span'(11,37,11,45,8,8),'Gen',['int'(9),_x2]),'src_span_operator'('no_loc_info_available','src_span'(11,33,11,37,4,4))),'src_span'(9,1,11,45,44,44)).
'bindval'('GenTop','[]'('[]'('agent_call'('src_span'(13,10,13,17,7,7),'Gen2',['int'(1)]),'agent_call'('src_span'(13,21,13,29,8,8),'Gen2',['int'(10)]),'src_span_operator'('no_loc_info_available','src_span'(13,17,13,21,4,4))),'agent_call'('src_span'(13,33,13,42,9,9),'Gen2',['int'(100)]),'src_span_operator'('no_loc_info_available','src_span'(13,29,13,33,4,4))),'src_span'(13,1,13,42,41,41)).
'bindval'('MAIN','sharing'('prefix'('src_span'(17,9,17,12,3,3),['out'('int'(88))],'out','prefix'('src_span'(17,19,17,22,3,3),['out'('int'(99))],'out','prefix'('src_span'(17,29,17,32,3,3),['out'('int'(909))],'out','prefix'('src_span'(17,40,17,43,3,3),['out'('int'(1))],'out','skip'('src_span'(17,49,17,53,4,4)),'src_span'(17,45,17,49,4,4)),'src_span'(17,36,17,40,4,4)),'src_span'(17,25,17,29,4,4)),'src_span'(17,15,17,19,4,4)),'out','val_of'('GenTop','src_span'(17,70,17,76,6,6)),'src_span'(17,53,17,57,4,4)),'src_span'(17,1,17,76,75,75)).
'bindval'('PROB_TEST_TRACE','prefix'('src_span'(20,19,20,25,6,6),[],'dotTuple'(['out','int'(88)]),'prefix'('src_span'(20,29,20,35,6,6),[],'dotTuple'(['out','int'(99)]),'prefix'('src_span'(20,39,20,46,7,7),[],'dotTuple'(['out','int'(909)]),'prefix'('src_span'(20,50,20,55,5,5),[],'dotTuple'(['out','int'(1)]),'stop'('src_span'(20,59,20,63,4,4)),'src_span'(20,55,20,59,4,4)),'src_span'(20,46,20,50,4,4)),'src_span'(20,35,20,39,4,4)),'src_span'(20,25,20,29,4,4)),'src_span'(20,1,20,63,62,62)).
'val_of'('MAIN','src_span'(22,8,22,12,4,4)),'val_of'('PROB_TEST_TRACE','src_span'(22,17,22,32,15,15)).
'symbol'('Gen','Gen','src_span'(5,1,5,4,3,3),'Function or Process').
'symbol'('PROB_TEST_TRACE','PROB_TEST_TRACE','src_span'(20,1,20,16,15,15),'Ident (Groundrep.)').
'symbol'('Gen2','Gen2','src_span'(9,1,9,5,4,4),'Function or Process').
'symbol'('x','x','src_span'(5,5,5,6,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(9,6,9,7,1,1),'Ident (Prolog Variable)').
'symbol'('MAIN','MAIN','src_span'(17,1,17,5,4,4),'Ident (Groundrep.)').
'symbol'('out','out','src_span'(3,9,3,12,3,3),'Channel').
'symbol'('GenTop','GenTop','src_span'(13,1,13,7,6,6),'Ident (Groundrep.)').

