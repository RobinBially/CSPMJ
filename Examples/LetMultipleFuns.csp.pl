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
'channel'('out','type'('dotTupleType'(['setExp'('int'(1),'int'(216))]))).
'agent'('TEST_MULTIPLE_FUNS'(_x),'let'(['agent'('f'(_y),_x,_y,'src_span'(5,12,5,22,10,10)),'agent'('g'(_z),'agent_call'('src_span'(6,19,6,23,4,4),'f',[_z]),'agent_call'('src_span'(6,24,6,28,4,4),'f',['x']),'src_span'(6,12,6,28,16,16))],'prefix'('src_span'(8,12,8,15,3,3),['out'('agent_call'('src_span'(8,16,8,20,4,4),'f',['src_span'(8,18,8,19,1,1),[],'int'(1)]))],'out','prefix'('src_span'(8,24,8,27,3,3),['out'('agent_call'('src_span'(8,28,8,32,4,4),'f',['src_span'(8,30,8,31,1,1),[],_x]))],'out','prefix'('src_span'(8,36,8,39,3,3),['out'('agent_call'('src_span'(8,40,8,44,4,4),'g',['src_span'(8,42,8,43,1,1),[],'int'(1)]))],'out','prefix'('src_span'(8,48,8,51,3,3),['out'('agent_call'('src_span'(8,52,8,56,4,4),'g',['src_span'(8,54,8,55,1,1),[],_x]))],'out','skip'('src_span'(8,60,8,64,4,4)),'src_span'(8,56,8,60,4,4)),'src_span'(8,44,8,48,4,4)),'src_span'(8,32,8,36,4,4)),'src_span'(8,20,8,24,4,4))),'src_span'(3,1,8,64,63,63)).
'bindval'('TEST_MUL',';'('agent_call'('src_span'(10,12,10,33,21,21),'TEST_MULTIPLE_FUNS',['int'(3)]),'agent_call'('src_span'(10,36,10,57,21,21),'TEST_MULTIPLE_FUNS',['int'(4)]),'src_span_operator'('no_loc_info_available','src_span'(10,33,10,36,3,3))),'src_span'(10,1,10,57,56,56)).
'agent'('TEST_MULTIPLE_FUNS_INV_ORDER'(_x2),'let'(['agent'('g2'(_z2),'agent_call'('src_span'(14,19,14,23,4,4),'f2',[_z2]),'agent_call'('src_span'(14,24,14,28,4,4),'f2',[_x2]),'src_span'(14,12,14,28,16,16)),'agent'('f2'(_y2),'x2',_y2,'src_span'(15,12,15,22,10,10))],'prefix'('src_span'(17,12,17,15,3,3),['out'('agent_call'('src_span'(17,16,17,20,4,4),'f2',['src_span'(17,18,17,19,1,1),[],'int'(1)]))],'out','prefix'('src_span'(17,24,17,27,3,3),['out'('agent_call'('src_span'(17,28,17,32,4,4),'f2',['src_span'(17,30,17,31,1,1),[],_x2]))],'out','prefix'('src_span'(17,36,17,39,3,3),['out'('agent_call'('src_span'(17,40,17,44,4,4),'g2',['src_span'(17,42,17,43,1,1),[],'int'(1)]))],'out','prefix'('src_span'(17,48,17,51,3,3),['out'('agent_call'('src_span'(17,52,17,56,4,4),'g2',['src_span'(17,54,17,55,1,1),[],_x2]))],'out','skip'('src_span'(17,60,17,64,4,4)),'src_span'(17,56,17,60,4,4)),'src_span'(17,44,17,48,4,4)),'src_span'(17,32,17,36,4,4)),'src_span'(17,20,17,24,4,4))),'src_span'(12,1,17,64,63,63)).
'bindval'('TEST_MUL_INV',';'('agent_call'('src_span'(18,16,18,47,31,31),'TEST_MULTIPLE_FUNS_INV_ORDER',['int'(3)]),'agent_call'('src_span'(18,50,18,81,31,31),'TEST_MULTIPLE_FUNS_INV_ORDER',['int'(4)]),'src_span_operator'('no_loc_info_available','src_span'(18,47,18,50,3,3))),'src_span'(18,1,18,81,80,80)).
'agent'('TEST_MultipleEq'(_x3,_y3,_z3),'let'(['agent'('f3'('int'(0)),'int'(1),'src_span'(22,12,22,20,8,8)),'agent'('f3'('int'(1)),'tupleExp'(['x3','y3','z3']),'src_span'(23,12,23,26,14,14)),'agent'('f3'(_a),'tupleExp'(['x3','y3','z3']),'agent_call'('src_span'(24,27,24,33,6,6),'f3',[_a,'int'(1)]),'src_span'(24,12,24,33,21,21)),'agent'('g3'(_v),'ifte'(_v,'x3','int'(1),'ifte'(_v,'y3','int'(11),'ifte'(_v,'z3','int'(22),'int'(33),'src_span'(25,59,25,61,2,2),'src_span'(25,65,25,71,6,6),'src_span'(25,73,25,79,6,6)),'src_span'(25,39,25,41,2,2),'src_span'(25,45,25,51,6,6),'src_span'(25,53,25,59,6,6)),'src_span'(25,19,25,21,2,2),'src_span'(25,25,25,31,6,6),'src_span'(25,33,25,39,6,6)),'src_span'(25,12,25,81,69,69))],'prefix'('src_span'(27,12,27,15,3,3),['out'('agent_call'('src_span'(27,16,27,20,4,4),'f3',['src_span'(27,18,27,19,1,1),[],'int'(1)]))],'out','prefix'('src_span'(27,24,27,27,3,3),['out'('agent_call'('src_span'(27,28,27,32,4,4),'f3',['src_span'(27,30,27,31,1,1),[],'int'(2)]))],'out','prefix'('src_span'(27,36,27,39,3,3),['out'('agent_call'('src_span'(27,40,27,44,4,4),'f3',['src_span'(27,42,27,43,1,1),[],'int'(3)]))],'out','prefix'('src_span'(27,48,27,51,3,3),['out'('agent_call'('src_span'(27,52,27,56,4,4),'g3',['src_span'(27,54,27,55,1,1),[],'int'(0)]))],'out','prefix'('src_span'(27,60,27,63,3,3),['out'('agent_call'('src_span'(27,64,27,68,4,4),'g3',['src_span'(27,66,27,67,1,1),[],'int'(2)]))],'out','prefix'('src_span'(27,72,27,75,3,3),['out'('agent_call'('src_span'(27,76,27,81,5,5),'g3',['src_span'(27,78,27,80,2,2),[],'int'(10)]))],'out','skip'('src_span'(27,85,27,89,4,4)),'src_span'(27,81,27,85,4,4)),'src_span'(27,68,27,72,4,4)),'src_span'(27,56,27,60,4,4)),'src_span'(27,44,27,48,4,4)),'src_span'(27,32,27,36,4,4)),'src_span'(27,20,27,24,4,4))),'src_span'(20,1,27,89,88,88)).
'bindval'('TEST_MultipleEq123','agent_call'('src_span'(29,22,29,44,22,22),'TEST_MultipleEq',['int'(1),'int'(2),'int'(3)]),'src_span'(29,1,29,44,43,43)).
'bindval'('MAIN',';'(';'(';'(';'('val_of'('TEST_MUL','src_span'(31,8,31,16,8,8)),'val_of'('TEST_MUL_INV','src_span'(31,19,31,31,12,12)),'src_span_operator'('no_loc_info_available','src_span'(31,16,31,19,3,3))),'val_of'('Test_nested1','src_span'(31,33,31,45,12,12)),'src_span_operator'('no_loc_info_available','src_span'(31,31,31,33,2,2))),'val_of'('Test_nested_3','src_span'(31,47,31,60,13,13)),'src_span_operator'('no_loc_info_available','src_span'(31,45,31,47,2,2))),'val_of'('TEST_MultipleEq123','src_span'(31,62,31,80,18,18)),'src_span_operator'('no_loc_info_available','src_span'(31,60,31,62,2,2))),'src_span'(31,1,31,80,79,79)).
'agent'('Test_nested_lets'(_x4,_y4),'let'(['agent'('f4'('int'(0)),'int'(1),'src_span'(35,29,35,37,8,8)),'agent'('f4'('int'(1)),'int'(2),'src_span'(36,29,36,37,8,8)),'agent'('f4'(_n),'int'(1),'agent_call'('src_span'(37,38,37,44,6,6),'f4',[_n,'int'(1)]),'src_span'(37,29,37,44,15,15))],'prefix'('src_span'(39,29,39,32,3,3),['out'('agent_call'('src_span'(39,33,39,39,6,6),'f4',['src_span'(39,35,39,38,3,3),[],_x4,_y4]))],'out','let'(['agent'('f5'(_n2),'int'(2),_n2,'src_span'(40,35,40,45,10,10))],'prefix'('src_span'(41,35,41,38,3,3),['out'('agent_call'('src_span'(41,39,41,45,6,6),'f5',['src_span'(41,41,41,44,3,3),[]]))],'out','skip'('src_span'(41,49,41,53,4,4)),'src_span'(41,45,41,49,4,4))),'src_span'(39,39,39,75,36,36))),'src_span'(34,1,41,53,52,52)).
'bindval'('Test_nested1','agent_call'('src_span'(42,16,42,37,21,21),'Test_nested_lets',['int'(2),'int'(3)]),'src_span'(42,1,42,37,36,36)).
'agent'('Test_nested_2'(_x5,_y5),'let'(['agent'('f6'('int'(0)),'int'(100),'src_span'(46,9,46,19,10,10)),'agent'('f6'('int'(1)),'int'(200),'src_span'(47,9,47,19,10,10)),'agent'('f6'(_n3),'let'(['agent'('g4'(_z4),'tupleExp'(['x5','y5',_z4]),_n3,'src_span'(48,21,48,41,20,20))],'agent_call'('src_span'(48,50,48,54,4,4),'g4',['int'(1)]),'agent_call'('src_span'(48,55,48,59,4,4),'g4',['int'(2)]),'agent_call'('src_span'(48,60,48,64,4,4),'g4',['int'(5)])),'src_span'(48,9,48,64,55,55))],'prefix'('src_span'(50,8,50,11,3,3),['out'('agent_call'('src_span'(50,12,50,16,4,4),'f6',['src_span'(50,14,50,15,1,1),[],'int'(1)]))],'out','prefix'('src_span'(50,20,50,23,3,3),['out'('agent_call'('src_span'(50,24,50,28,4,4),'f6',['src_span'(50,26,50,27,1,1),[],'int'(3)]))],'out','prefix'('src_span'(50,32,50,35,3,3),['out'('agent_call'('src_span'(50,36,50,40,4,4),'f6',['src_span'(50,38,50,39,1,1),[],_x5]))],'out','prefix'('src_span'(50,44,50,47,3,3),['out'('agent_call'('src_span'(50,48,50,54,6,6),'f6',['src_span'(50,50,50,53,3,3),[],'int'(100)]))],'out','skip'('src_span'(50,58,50,62,4,4)),'src_span'(50,54,50,58,4,4)),'src_span'(50,40,50,44,4,4)),'src_span'(50,28,50,32,4,4)),'src_span'(50,16,50,20,4,4))),'src_span'(45,1,50,62,61,61)).
'bindval'('Test_nested_3','agent_call'('src_span'(51,17,51,35,18,18),'Test_nested_2',['int'(2),'int'(5)]),'src_span'(51,1,51,35,34,34)).
'bindval'('PROB_TEST_TRACE',';'('prefix'('src_span'(57,19,57,24,5,5),[],'dotTuple'(['out','int'(3)]),'prefix'('src_span'(57,28,57,33,5,5),[],'dotTuple'(['out','int'(9)]),'prefix'('src_span'(57,37,57,43,6,6),[],'dotTuple'(['out','int'(12)]),'prefix'('src_span'(57,47,57,53,6,6),[],'dotTuple'(['out','int'(18)]),'prefix'('src_span'(57,57,57,62,5,5),[],'dotTuple'(['out','int'(4)]),'prefix'('src_span'(57,66,57,72,6,6),[],'dotTuple'(['out','int'(16)]),'prefix'('src_span'(57,76,57,82,6,6),[],'dotTuple'(['out','int'(20)]),'prefix'('src_span'(57,86,57,92,6,6),[],'dotTuple'(['out','int'(32)]),'prefix'('src_span'(57,96,57,101,5,5),[],'dotTuple'(['out','int'(3)]),'prefix'('src_span'(57,105,57,110,5,5),[],'dotTuple'(['out','int'(9)]),'prefix'('src_span'(57,114,57,120,6,6),[],'dotTuple'(['out','int'(12)]),'prefix'('src_span'(57,124,57,130,6,6),[],'dotTuple'(['out','int'(18)]),'prefix'('src_span'(57,134,57,139,5,5),[],'dotTuple'(['out','int'(4)]),'prefix'('src_span'(57,143,57,149,6,6),[],'dotTuple'(['out','int'(16)]),'prefix'('src_span'(57,153,57,159,6,6),[],'dotTuple'(['out','int'(20)]),'prefix'('src_span'(57,163,57,169,6,6),[],'dotTuple'(['out','int'(32)]),'prefix'('src_span'(57,173,57,178,5,5),[],'dotTuple'(['out','int'(6)]),'prefix'('src_span'(57,182,57,188,6,6),[],'dotTuple'(['out','int'(10)]),'prefix'('src_span'(57,192,57,199,7,7),[],'dotTuple'(['out','int'(200)]),'prefix'('src_span'(57,203,57,208,5,5),[],'dotTuple'(['out','int'(2)]),'prefix'('src_span'(57,212,57,217,5,5),[],'dotTuple'(['out','int'(2)]),'prefix'('src_span'(57,221,57,227,6,6),[],'dotTuple'(['out','int'(38)]),'prefix'('src_span'(57,231,57,236,5,5),[],'dotTuple'(['out','int'(6)]),'prefix'('src_span'(57,240,57,246,6,6),[],'dotTuple'(['out','int'(36)]),'prefix'('src_span'(57,250,57,257,7,7),[],'dotTuple'(['out','int'(216)]),'prefix'('src_span'(57,261,57,267,6,6),[],'dotTuple'(['out','int'(1)]),'prefix'('src_span'(57,271,57,277,6,6),[],'dotTuple'(['out','int'(22)]),'prefix'('src_span'(57,281,57,287,6,6),[],'dotTuple'(['out','int'(33)]),'skip'('src_span'(57,291,57,295,4,4)),'src_span'(57,287,57,291,4,4)),'src_span'(57,277,57,281,4,4)),'src_span'(57,267,57,271,4,4)),'src_span'(57,257,57,261,4,4)),'src_span'(57,246,57,250,4,4)),'src_span'(57,236,57,240,4,4)),'src_span'(57,227,57,231,4,4)),'src_span'(57,217,57,221,4,4)),'src_span'(57,208,57,212,4,4)),'src_span'(57,199,57,203,4,4)),'src_span'(57,188,57,192,4,4)),'src_span'(57,178,57,182,4,4)),'src_span'(57,169,57,173,4,4)),'src_span'(57,159,57,163,4,4)),'src_span'(57,149,57,153,4,4)),'src_span'(57,139,57,143,4,4)),'src_span'(57,130,57,134,4,4)),'src_span'(57,120,57,124,4,4)),'src_span'(57,110,57,114,4,4)),'src_span'(57,101,57,105,4,4)),'src_span'(57,92,57,96,4,4)),'src_span'(57,82,57,86,4,4)),'src_span'(57,72,57,76,4,4)),'src_span'(57,62,57,66,4,4)),'src_span'(57,53,57,57,4,4)),'src_span'(57,43,57,47,4,4)),'src_span'(57,33,57,37,4,4)),'src_span'(57,24,57,28,4,4)),'stop'('src_span'(57,298,57,302,4,4)),'src_span_operator'('no_loc_info_available','src_span'(57,295,57,298,3,3))),'src_span'(57,1,57,302,301,301)).
'val_of'('MAIN','src_span'(59,8,59,12,4,4)),'val_of'('PROB_TEST_TRACE','src_span'(59,17,59,32,15,15)).
'symbol'('TEST_MUL_INV','TEST_MUL_INV','src_span'(18,1,18,13,12,12),'Ident (Groundrep.)').
'symbol'('TEST_MULTIPLE_FUNS','TEST_MULTIPLE_FUNS','src_span'(3,1,3,19,18,18),'Function or Process').
'symbol'('a','a','src_span'(24,14,24,15,1,1),'Ident (Prolog Variable)').
'symbol'('TEST_MultipleEq123','TEST_MultipleEq123','src_span'(29,1,29,19,18,18),'Ident (Groundrep.)').
'symbol'('f','f','src_span'(5,12,5,13,1,1),'Function or Process').
'symbol'('f2','f','src_span'(15,12,15,13,1,1),'Function or Process').
'symbol'('f3','f','src_span'(22,12,22,13,1,1),'Function or Process').
'symbol'('f4','f','src_span'(35,29,35,30,1,1),'Function or Process').
'symbol'('f5','f','src_span'(40,35,40,36,1,1),'Function or Process').
'symbol'('f6','f','src_span'(46,9,46,10,1,1),'Function or Process').
'symbol'('g','g','src_span'(6,12,6,13,1,1),'Function or Process').
'symbol'('g2','g','src_span'(14,12,14,13,1,1),'Function or Process').
'symbol'('g3','g','src_span'(25,12,25,13,1,1),'Function or Process').
'symbol'('g4','g','src_span'(48,21,48,22,1,1),'Function or Process').
'symbol'('TEST_MUL','TEST_MUL','src_span'(10,1,10,9,8,8),'Ident (Groundrep.)').
'symbol'('TEST_MultipleEq','TEST_MultipleEq','src_span'(20,1,20,16,15,15),'Function or Process').
'symbol'('Test_nested1','Test_nested1','src_span'(42,1,42,13,12,12),'Ident (Groundrep.)').
'symbol'('n','n','src_span'(37,31,37,32,1,1),'Ident (Prolog Variable)').
'symbol'('n2','n','src_span'(40,37,40,38,1,1),'Ident (Prolog Variable)').
'symbol'('n3','n','src_span'(48,11,48,12,1,1),'Ident (Prolog Variable)').
'symbol'('out','out','src_span'(1,9,1,12,3,3),'Channel').
'symbol'('PROB_TEST_TRACE','PROB_TEST_TRACE','src_span'(57,1,57,16,15,15),'Ident (Groundrep.)').
'symbol'('Test_nested_2','Test_nested_2','src_span'(45,1,45,14,13,13),'Function or Process').
'symbol'('Test_nested_lets','Test_nested_lets','src_span'(34,1,34,17,16,16),'Function or Process').
'symbol'('v','v','src_span'(25,14,25,15,1,1),'Ident (Prolog Variable)').
'symbol'('TEST_MULTIPLE_FUNS_INV_ORDER','TEST_MULTIPLE_FUNS_INV_ORDER','src_span'(12,1,12,29,28,28),'Function or Process').
'symbol'('x','x','src_span'(3,20,3,21,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(12,30,12,31,1,1),'Ident (Prolog Variable)').
'symbol'('x3','x','src_span'(20,17,20,18,1,1),'Ident (Prolog Variable)').
'symbol'('x4','x','src_span'(34,18,34,19,1,1),'Ident (Prolog Variable)').
'symbol'('x5','x','src_span'(45,15,45,16,1,1),'Ident (Prolog Variable)').
'symbol'('y','y','src_span'(5,14,5,15,1,1),'Ident (Prolog Variable)').
'symbol'('y2','y','src_span'(15,14,15,15,1,1),'Ident (Prolog Variable)').
'symbol'('y3','y','src_span'(20,19,20,20,1,1),'Ident (Prolog Variable)').
'symbol'('y4','y','src_span'(34,20,34,21,1,1),'Ident (Prolog Variable)').
'symbol'('y5','y','src_span'(45,17,45,18,1,1),'Ident (Prolog Variable)').
'symbol'('z','z','src_span'(6,14,6,15,1,1),'Ident (Prolog Variable)').
'symbol'('z2','z','src_span'(14,14,14,15,1,1),'Ident (Prolog Variable)').
'symbol'('z3','z','src_span'(20,21,20,22,1,1),'Ident (Prolog Variable)').
'symbol'('z4','z','src_span'(48,23,48,24,1,1),'Ident (Prolog Variable)').
'symbol'('MAIN','MAIN','src_span'(31,1,31,5,4,4),'Ident (Groundrep.)').
'symbol'('Test_nested_3','Test_nested_3','src_span'(51,1,51,14,13,13),'Ident (Groundrep.)').

