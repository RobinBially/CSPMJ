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
'dataTypeDef'('FRUIT',['constructor'('apple'),'constructor'('orange'),'constructor'('banana'),'constructor'('pear')]).
'channel'('out','type'('dotTupleType'(['setExp'('int'(0),'int'(3)),'setExp'('int'(2),'int'(4)),'setExp'('int'(2),'int'(7)),'val_of'('FRUIT','src_span'(5,34,5,39,5,5))]))).
'bindval'('MAIN',';'(';'(';'(';'('val_of'('Test1','src_span'(9,8,9,13,5,5)),'val_of'('Test2','src_span'(9,14,9,19,5,5)),'src_span_operator'('no_loc_info_available','src_span'(9,13,9,14,1,1))),'val_of'('Test3','src_span'(9,20,9,25,5,5)),'src_span_operator'('no_loc_info_available','src_span'(9,19,9,20,1,1))),'val_of'('TestHide2','src_span'(9,27,9,36,9,9)),'src_span_operator'('no_loc_info_available','src_span'(9,25,9,27,2,2))),'val_of'('TestMultiSync2','src_span'(9,38,9,52,14,14)),'src_span_operator'('no_loc_info_available','src_span'(9,36,9,38,2,2))),'src_span'(9,1,9,52,51,51)).
'bindval'('Test1','prefix'('src_span'(14,9,14,12,3,3),['out'('int'(1)),'out'('int'(3)),'out'('int'(5)),'out'('orange')],'out','skip'('src_span'(14,29,14,33,4,4)),'src_span'(14,25,14,29,4,4)),'src_span'(14,1,14,33,32,32)).
'channel'('in','type'('dotTupleType'(['setExp'('int'(0),'int'(3)),'setExp'('int'(2),'int'(5))]))).
'channel'('mid','type'('dotTupleType'(['setExp'('int'(0),'int'(3)),'setExp'('int'(2),'int'(5))]))).
'bindval'('Test2','prefix'('src_span'(17,9,17,11,2,2),['inGuard'(_t,'int'(0),'int'(1)),'out'('int'(4))],'in','skip'('src_span'(17,26,17,30,4,4)),'src_span'(17,22,17,26,4,4)),'src_span'(17,1,17,30,29,29)).
'bindval'('Test3','prefix'('src_span'(20,9,20,12,3,3),['in'(_x),'in'(_z),'out'(_x,_z),'in'(_f)],'out','prefix'('src_span'(20,50,20,55,5,5),['in'(_y),'in'(_v),'out'('banana')],'dotTuple'(['out',_z]),'prefix'('src_span'(20,70,20,73,3,3),['in'(_all)],'out','prefix'('src_span'(20,81,20,84,3,3),['inGuard'(_r,'src_span'(20,88,20,89,1,1),[],_x,['comprehensionGenerator'(_x,'src_span'(20,93,20,100,7,7),[],'int'(0),'int'(99)),'comprehensionGuard'('tupleExp'(['src_span'(20,102,20,105,3,3),[],_x,'int'(2)])),'comprehensionGuard'('tupleExp'(['src_span'(20,108,20,111,3,3),[],_x,'int'(0)]))]),'in'(_rest)],'out','skip'('src_span'(20,122,20,126,4,4)),'src_span'(20,118,20,122,4,4)),'src_span'(20,77,20,81,4,4)),'src_span'(20,66,20,70,4,4)),'src_span'(20,22,20,50,28,28)),'src_span'(20,1,20,126,125,125)).
'bindval'('TestHide1','\'('tupleExp'(['prefix'('src_span'(26,14,26,16,2,2),['out'('int'(1)),'out'('int'(2))],'in','skip'('src_span'(26,24,26,28,4,4)),'src_span'(26,20,26,24,4,4))]),'in','src_span_operator'('no_loc_info_available','src_span'(26,29,26,32,3,3))),'src_span'(26,1,26,40,39,39)).
'bindval'('TestHide2','\'('tupleExp'(['|~|'('prefix'('src_span'(27,14,27,16,2,2),['in'(_x2),'in'(_z2)],'in','prefix'('src_span'(27,24,27,26,2,2),['out'(_x2),'out'(_z2)],'in','skip'('src_span'(27,34,27,38,4,4)),'src_span'(27,30,27,34,4,4)),'src_span'(27,20,27,24,4,4)),'prefix'('src_span'(27,43,27,45,2,2),['out'('int'(3)),'out'('int'(5))],'in','skip'('src_span'(27,53,27,57,4,4)),'src_span'(27,49,27,53,4,4)),'src_span_operator'('no_loc_info_available','src_span'(27,38,27,43,5,5)))]),'in','src_span_operator'('no_loc_info_available','src_span'(27,58,27,61,3,3))),'src_span'(27,1,27,69,68,68)).
'bindval'('TestRename1','tupleExp'(['|~|'('prefix'('src_span'(29,16,29,18,2,2),['in'(_x3),'in'(_z3)],'in','prefix'('src_span'(29,26,29,28,2,2),['out'(_x3),'out'(_z3)],'in','skip'('src_span'(29,36,29,40,4,4)),'src_span'(29,32,29,36,4,4)),'src_span'(29,22,29,26,4,4)),'prefix'('src_span'(29,45,29,47,2,2),['out'('int'(3)),'out'('int'(5))],'in','skip'('src_span'(29,55,29,59,4,4)),'src_span'(29,51,29,55,4,4)),'src_span_operator'('no_loc_info_available','src_span'(29,40,29,45,5,5)))]),'in','mid','src_span'(29,1,29,76,75,75)).
'bindval'('R1','prefix'('src_span'(33,6,33,8,2,2),['in'(_x4),'in'(_z4)],'in','prefix'('src_span'(33,16,33,19,3,3),['out'(_x4),'out'(_z4)],'mid','stop'('src_span'(33,27,33,31,4,4)),'src_span'(33,23,33,27,4,4)),'src_span'(33,12,33,16,4,4)),'src_span'(33,1,33,31,30,30)).
'bindval'('R2','prefix'('src_span'(34,6,34,8,2,2),['in'(_x2),'in'(_z2)],'in','prefix'('src_span'(34,18,34,21,3,3),['out'(_x2),'out'(_z2)],'mid','skip'('src_span'(34,31,34,35,4,4)),'src_span'(34,27,34,31,4,4)),'src_span'(34,14,34,18,4,4)),'src_span'(34,1,34,35,34,34)).
'bindval'('TestMultiSync','sharing'('val_of'('R1','src_span'(36,17,36,19,2,2)),'in','val_of'('R2','src_span'(36,35,36,37,2,2)),'src_span'(36,19,36,23,4,4)),'src_span'(36,1,36,37,36,36)).
'bindval'('TestMultiSync2','tupleExp'(['sharing'('val_of'('TestMultiSync','src_span'(38,19,38,32,13,13)),'in','prefix'('src_span'(38,48,38,50,2,2),['out'('int'(2)),'out'('int'(4))],'in','skip'('src_span'(38,58,38,62,4,4)),'src_span'(38,54,38,58,4,4)),'src_span'(38,32,38,36,4,4))]),'src_span'(38,1,38,64,63,63)).
'bindval'('PROB_TEST_TRACE','prefix'('src_span'(43,19,43,35,16,16),[],'dotTuple'(['out','int'(1),'int'(3),'int'(5),'orange']),'prefix'('src_span'(43,39,43,45,6,6),[],'dotTuple'(['in','int'(1),'int'(4)]),'prefix'('src_span'(43,49,43,64,15,15),[],'dotTuple'(['out','int'(1),'int'(2),'int'(3),'apple']),'prefix'('src_span'(43,68,43,84,16,16),[],'dotTuple'(['out','int'(2),'int'(3),'int'(4),'banana']),'prefix'('src_span'(43,88,43,104,16,16),[],'dotTuple'(['out','int'(0),'int'(2),'int'(4),'orange']),'prefix'('src_span'(43,108,43,124,16,16),[],'dotTuple'(['out','int'(1),'int'(2),'int'(4),'orange']),'prefix'('src_span'(43,128,43,134,6,6),[],'dotTuple'(['in','int'(2),'int'(4)]),'prefix'('src_span'(43,138,43,145,7,7),[],'dotTuple'(['mid','int'(2),'int'(4)]),'prefix'('src_span'(43,149,43,156,7,7),[],'dotTuple'(['mid','int'(2),'int'(4)]),'stop'('src_span'(43,160,43,164,4,4)),'src_span'(43,156,43,160,4,4)),'src_span'(43,145,43,149,4,4)),'src_span'(43,134,43,138,4,4)),'src_span'(43,124,43,128,4,4)),'src_span'(43,104,43,108,4,4)),'src_span'(43,84,43,88,4,4)),'src_span'(43,64,43,68,4,4)),'src_span'(43,45,43,49,4,4)),'src_span'(43,35,43,39,4,4)),'src_span'(43,1,43,164,163,163)).
'val_of'('MAIN','src_span'(44,8,44,12,4,4)),'val_of'('PROB_TEST_TRACE','src_span'(44,17,44,32,15,15)).
'symbol'('mid','mid','src_span'(16,12,16,15,3,3),'Channel').
'symbol'('TestHide1','TestHide1','src_span'(26,1,26,10,9,9),'Ident (Groundrep.)').
'symbol'('Test3','Test3','src_span'(20,1,20,6,5,5),'Ident (Groundrep.)').
'symbol'('out','out','src_span'(5,9,5,12,3,3),'Channel').
'symbol'('Test2','Test2','src_span'(17,1,17,6,5,5),'Ident (Groundrep.)').
'symbol'('TestHide2','TestHide2','src_span'(27,1,27,10,9,9),'Ident (Groundrep.)').
'symbol'('apple','apple','src_span'(3,18,3,23,5,5),'Constructor of Datatype').
'symbol'('pear','pear','src_span'(3,44,3,48,4,4),'Constructor of Datatype').
'symbol'('PROB_TEST_TRACE','PROB_TEST_TRACE','src_span'(43,1,43,16,15,15),'Ident (Groundrep.)').
'symbol'('MAIN','MAIN','src_span'(9,1,9,5,4,4),'Ident (Groundrep.)').
'symbol'('TestMultiSync2','TestMultiSync2','src_span'(38,1,38,15,14,14),'Ident (Groundrep.)').
'symbol'('R1','R1','src_span'(33,1,33,3,2,2),'Ident (Groundrep.)').
'symbol'('banana','banana','src_span'(3,35,3,41,6,6),'Constructor of Datatype').
'symbol'('all','all','src_span'(20,74,20,77,3,3),'Ident (Prolog Variable)').
'symbol'('R2','R2','src_span'(34,1,34,3,2,2),'Ident (Groundrep.)').
'symbol'('rest','rest','src_span'(20,114,20,118,4,4),'Ident (Prolog Variable)').
'symbol'('in','in','src_span'(16,9,16,11,2,2),'Channel').
'symbol'('f','f','src_span'(20,21,20,22,1,1),'Ident (Prolog Variable)').
'symbol'('orange','orange','src_span'(3,26,3,32,6,6),'Constructor of Datatype').
'symbol'('Test1','Test1','src_span'(14,1,14,6,5,5),'Ident (Groundrep.)').
'symbol'('r','r','src_span'(20,85,20,86,1,1),'Ident (Prolog Variable)').
'symbol'('FRUIT','FRUIT','src_span'(3,10,3,15,5,5),'Datatype').
'symbol'('t','t','src_span'(17,12,17,13,1,1),'Ident (Prolog Variable)').
'symbol'('v','v','src_span'(20,58,20,59,1,1),'Ident (Prolog Variable)').
'symbol'('x','x','src_span'(20,13,20,14,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(27,17,27,18,1,1),'Ident (Prolog Variable)').
'symbol'('x3','x','src_span'(29,19,29,20,1,1),'Ident (Prolog Variable)').
'symbol'('x4','x','src_span'(33,9,33,10,1,1),'Ident (Prolog Variable)').
'symbol'('z2','z2','src_span'(34,12,34,14,2,2),'Ident (Prolog Variable)').
'symbol'('y','y','src_span'(20,56,20,57,1,1),'Ident (Prolog Variable)').
'symbol'('z','z','src_span'(20,15,20,16,1,1),'Ident (Prolog Variable)').
'symbol'('z2','z','src_span'(27,19,27,20,1,1),'Ident (Prolog Variable)').
'symbol'('z3','z','src_span'(29,21,29,22,1,1),'Ident (Prolog Variable)').
'symbol'('z4','z','src_span'(33,11,33,12,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x2','src_span'(34,9,34,11,2,2),'Ident (Prolog Variable)').
'symbol'('TestMultiSync','TestMultiSync','src_span'(36,1,36,14,13,13),'Ident (Groundrep.)').
'symbol'('TestRename1','TestRename1','src_span'(29,1,29,12,11,11),'Ident (Groundrep.)').

