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
'channel'('out','type'('dotTupleType'(['setExp'('int'(0),'int'(99)),'setExp'('int'(0),'int'(99))]))).
'channel'('middle','type'('dotTupleType'(['setExp'('int'(0),'int'(99)),'setExp'('int'(0),'int'(99))]))).
'agent'('Q'(_l),'prefix'('src_span'(6,11,6,14,3,3),['out'(_l),'out'('int'(0))],'out','skip'('src_span'(6,22,6,26,4,4)),'src_span'(6,18,6,22,4,4)),'src_span'(6,1,6,26,25,25)).
'agent'('Q'('appendPattern'([_x,_t]),_l2),'prefix'('src_span'(7,14,7,17,3,3),['out'(_l2),'out'(_x)],'out','agent_call'('src_span'(7,25,7,33,8,8),'Q',[_t,_l2,'int'(1)]),'src_span'(7,21,7,25,4,4)),'src_span'(7,1,7,33,32,32)).
'agent'('Q1'(_l3),'prefix'('src_span'(12,12,12,15,3,3),['out'(_l3),'out'('int'(0))],'out','skip'('src_span'(12,23,12,27,4,4)),'src_span'(12,19,12,23,4,4)),'src_span'(12,1,12,27,26,26)).
'agent'('Q1'('appendPattern'([_t2,_x2]),_l4),'prefix'('src_span'(13,15,13,18,3,3),['out'(_l4),'out'(_x2)],'out','agent_call'('src_span'(13,26,13,35,9,9),'Q1',[_t2,_l4,'int'(1)]),'src_span'(13,22,13,26,4,4)),'src_span'(13,1,13,35,34,34)).
'agent'('Q2'(_l5),'prefix'('src_span'(16,12,16,15,3,3),['out'(_l5),'out'('int'(0))],'out','skip'('src_span'(16,23,16,27,4,4)),'src_span'(16,19,16,23,4,4)),'src_span'(16,1,16,27,26,26)).
'agent'('Q2'('alsoPattern'(['appendPattern'([_h1,_h2,_t3]),'appendPattern'([_s,_x3])]),_l6),'prefix'('src_span'(17,26,17,32,6,6),['out'(_l6),'out'(_x3)],'middle','agent_call'('src_span'(17,40,17,49,9,9),'Q2',[_t3,_l6,'int'(1)]),'src_span'(17,36,17,40,4,4)),'src_span'(17,1,17,49,48,48)).
'agent'('Q2'('appendPattern'([_t4,_x4]),_l7),'prefix'('src_span'(18,17,18,20,3,3),['out'(_l7),'out'(_x4)],'out','agent_call'('src_span'(18,28,18,37,9,9),'Q2',[_t4,_l7,'int'(1)]),'src_span'(18,24,18,28,4,4)),'src_span'(18,1,18,37,36,36)).
'agent'('reverse','tupleExp'([]),'src_span'(20,1,20,19,18,18)).
'agent'('reverse'('appendPattern'([_x5,_s2])),'^'('agent_call'('src_span'(21,18,21,28,10,10),'reverse',[_s2]),'tupleExp'([_x5])),'src_span'(21,1,21,34,33,33)).
'bindval'('TEST','agent_call'('src_span'(23,8,23,21,13,13),'Q',['int'(1),'int'(2),'int'(3),'int'(20)]),'src_span'(23,1,23,21,20,20)).
'bindval'('MAIN',';'(';'(';'(';'(';'('agent_call'('src_span'(25,9,25,26,17,17),'Q1',['int'(1),'int'(4),'int'(9),'int'(25),'int'(40)]),'agent_call'('src_span'(25,29,25,45,16,16),'Q2',['int'(5),'int'(10),'int'(15),'int'(30)]),'src_span_operator'('no_loc_info_available','src_span'(25,26,25,29,3,3))),'agent_call'('src_span'(25,48,25,63,15,15),'Q',['int'(1),'int'(2),'int'(3),'int'(4),'int'(10)]),'src_span_operator'('no_loc_info_available','src_span'(25,45,25,48,3,3))),'agent_call'('src_span'(25,66,25,90,24,24),'Q',['agent_call'('src_span'(25,68,25,86,18,18),'reverse',['int'(1),'int'(2),'int'(3),'int'(4)]),'int'(20)]),'src_span_operator'('no_loc_info_available','src_span'(25,63,25,66,3,3))),'val_of'('TEST3','src_span'(25,93,25,98,5,5)),'src_span_operator'('no_loc_info_available','src_span'(25,90,25,93,3,3))),'val_of'('TEST4','src_span'(25,101,25,106,5,5)),'src_span_operator'('no_loc_info_available','src_span'(25,98,25,101,3,3))),'src_span'(25,1,25,106,105,105)).
'bindval'('TEST3','agent_call'('src_span'(29,9,29,29,20,20),'Q3',['int'(3),'int'(7),'int'(9),'int'(11),'int'(13),'int'(20)]),'src_span'(29,1,29,29,28,28)).
'channel'('both','type'('dotTupleType'(['setExp'('int'(0),'int'(99)),'setExp'('int'(0),'int'(99))]))).
'agent'('Q3'(_l8),'prefix'('src_span'(32,12,32,15,3,3),['out'(_l8),'out'('int'(0))],'out','skip'('src_span'(32,23,32,27,4,4)),'src_span'(32,19,32,23,4,4)),'src_span'(32,1,32,27,26,26)).
'agent'('Q3'('appendPattern'([_h,_t5,_x6]),_l9),'prefix'('src_span'(33,19,33,23,4,4),['out'(_l9),'out'(_h,_x6)],'both','agent_call'('src_span'(33,33,33,42,9,9),'Q3',[_t5,_l9,'int'(1)]),'src_span'(33,29,33,33,4,4)),'src_span'(33,1,33,42,41,41)).
'agent'('Q3'('appendPattern'([_t6,_x7]),_l10),'prefix'('src_span'(34,17,34,20,3,3),['out'(_l10),'out'(_x7)],'out','agent_call'('src_span'(34,28,34,37,9,9),'Q3',[_t6,_l10,'int'(1)]),'src_span'(34,24,34,28,4,4)),'src_span'(34,1,34,37,36,36)).
'bindval'('TEST4','agent_call'('src_span'(37,9,37,32,23,23),'Q4',['int'(3),'int'(7),'int'(9),'int'(11),'int'(13),'int'(17),'int'(10)]),'src_span'(37,1,37,32,31,31)).
'agent'('Q4'(_l11),'prefix'('src_span'(39,12,39,15,3,3),['out'(_l11),'out'('int'(0))],'out','skip'('src_span'(39,23,39,27,4,4)),'src_span'(39,19,39,23,4,4)),'src_span'(39,1,39,27,26,26)).
'agent'('Q4'('appendPattern'([_h12,_h22,_t7,_e1,_e2]),_l12),'prefix'('src_span'(40,31,40,35,4,4),['out'(_l12),'out'(_h12,_h22,_e1,_e2)],'both','agent_call'('src_span'(40,53,40,62,9,9),'Q4',[_t7,_l12,'int'(1)]),'src_span'(40,49,40,53,4,4)),'src_span'(40,1,40,62,61,61)).
'agent'('Q4'('appendPattern'([_t8,_x8]),_l13),'prefix'('src_span'(41,17,41,20,3,3),['out'(_l13),'out'(_x8)],'out','agent_call'('src_span'(41,28,41,37,9,9),'Q4',[_t8,_l13,'int'(1)]),'src_span'(41,24,41,28,4,4)),'src_span'(41,1,41,37,36,36)).
'bindval'('PROB_TEST_TRACE',';'('prefix'('src_span'(45,19,45,28,9,9),[],'dotTuple'(['out','int'(40),'int'(25)]),'prefix'('src_span'(45,32,45,40,8,8),[],'dotTuple'(['out','int'(41),'int'(9)]),'prefix'('src_span'(45,44,45,52,8,8),[],'dotTuple'(['out','int'(42),'int'(4)]),'prefix'('src_span'(45,56,45,64,8,8),[],'dotTuple'(['out','int'(43),'int'(1)]),'prefix'('src_span'(45,68,45,76,8,8),[],'dotTuple'(['out','int'(44),'int'(0)]),'prefix'('src_span'(45,80,45,92,12,12),[],'dotTuple'(['middle','int'(30),'int'(15)]),'prefix'('src_span'(45,96,45,105,9,9),[],'dotTuple'(['out','int'(31),'int'(15)]),'prefix'('src_span'(45,109,45,117,8,8),[],'dotTuple'(['out','int'(32),'int'(0)]),'prefix'('src_span'(45,121,45,129,8,8),[],'dotTuple'(['out','int'(10),'int'(1)]),'prefix'('src_span'(45,133,45,141,8,8),[],'dotTuple'(['out','int'(11),'int'(2)]),'prefix'('src_span'(45,145,45,153,8,8),[],'dotTuple'(['out','int'(12),'int'(3)]),'prefix'('src_span'(45,157,45,165,8,8),[],'dotTuple'(['out','int'(13),'int'(4)]),'prefix'('src_span'(45,169,45,177,8,8),[],'dotTuple'(['out','int'(14),'int'(0)]),'prefix'('src_span'(45,181,45,189,8,8),[],'dotTuple'(['out','int'(20),'int'(4)]),'prefix'('src_span'(45,193,45,201,8,8),[],'dotTuple'(['out','int'(21),'int'(3)]),'prefix'('src_span'(45,205,45,213,8,8),[],'dotTuple'(['out','int'(22),'int'(2)]),'prefix'('src_span'(45,217,45,225,8,8),[],'dotTuple'(['out','int'(23),'int'(1)]),'prefix'('src_span'(45,229,45,237,8,8),[],'dotTuple'(['out','int'(24),'int'(0)]),'prefix'('src_span'(45,241,45,251,10,10),[],'dotTuple'(['both','int'(20),'int'(16)]),'prefix'('src_span'(45,255,45,265,10,10),[],'dotTuple'(['both','int'(21),'int'(18)]),'prefix'('src_span'(45,269,45,277,8,8),[],'dotTuple'(['out','int'(22),'int'(9)]),'prefix'('src_span'(45,281,45,289,8,8),[],'dotTuple'(['out','int'(23),'int'(0)]),'prefix'('src_span'(45,293,45,303,10,10),[],'dotTuple'(['both','int'(10),'int'(40)]),'prefix'('src_span'(45,307,45,316,9,9),[],'dotTuple'(['out','int'(11),'int'(11)]),'prefix'('src_span'(45,320,45,328,8,8),[],'dotTuple'(['out','int'(12),'int'(9)]),'prefix'('src_span'(45,332,45,340,8,8),[],'dotTuple'(['out','int'(13),'int'(0)]),'skip'('src_span'(45,344,45,348,4,4)),'src_span'(45,340,45,344,4,4)),'src_span'(45,328,45,332,4,4)),'src_span'(45,316,45,320,4,4)),'src_span'(45,303,45,307,4,4)),'src_span'(45,289,45,293,4,4)),'src_span'(45,277,45,281,4,4)),'src_span'(45,265,45,269,4,4)),'src_span'(45,251,45,255,4,4)),'src_span'(45,237,45,241,4,4)),'src_span'(45,225,45,229,4,4)),'src_span'(45,213,45,217,4,4)),'src_span'(45,201,45,205,4,4)),'src_span'(45,189,45,193,4,4)),'src_span'(45,177,45,181,4,4)),'src_span'(45,165,45,169,4,4)),'src_span'(45,153,45,157,4,4)),'src_span'(45,141,45,145,4,4)),'src_span'(45,129,45,133,4,4)),'src_span'(45,117,45,121,4,4)),'src_span'(45,105,45,109,4,4)),'src_span'(45,92,45,96,4,4)),'src_span'(45,76,45,80,4,4)),'src_span'(45,64,45,68,4,4)),'src_span'(45,52,45,56,4,4)),'src_span'(45,40,45,44,4,4)),'src_span'(45,28,45,32,4,4)),'stop'('src_span'(45,351,45,355,4,4)),'src_span_operator'('no_loc_info_available','src_span'(45,348,45,351,3,3))),'src_span'(45,1,45,355,354,354)).
'val_of'('MAIN','src_span'(47,8,47,12,4,4)),'val_of'('PROB_TEST_TRACE','src_span'(47,17,47,32,15,15)).
'symbol'('Q1','Q1','src_span'(12,1,12,3,2,2),'Function or Process').
'symbol'('Q2','Q2','src_span'(16,1,16,3,2,2),'Function or Process').
'symbol'('TEST3','TEST3','src_span'(29,1,29,6,5,5),'Ident (Groundrep.)').
'symbol'('Q3','Q3','src_span'(32,1,32,3,2,2),'Function or Process').
'symbol'('TEST4','TEST4','src_span'(37,1,37,6,5,5),'Ident (Groundrep.)').
'symbol'('middle','middle','src_span'(4,9,4,15,6,6),'Channel').
'symbol'('Q4','Q4','src_span'(39,1,39,3,2,2),'Function or Process').
'symbol'('h','h','src_span'(33,5,33,6,1,1),'Ident (Prolog Variable)').
'symbol'('h1','h1','src_span'(17,5,17,7,2,2),'Ident (Prolog Variable)').
'symbol'('h12','h1','src_span'(40,5,40,7,2,2),'Ident (Prolog Variable)').
'symbol'('h2','h2','src_span'(17,8,17,10,2,2),'Ident (Prolog Variable)').
'symbol'('h22','h2','src_span'(40,10,40,12,2,2),'Ident (Prolog Variable)').
'symbol'('l','l','src_span'(6,6,6,7,1,1),'Ident (Prolog Variable)').
'symbol'('l2','l','src_span'(7,9,7,10,1,1),'Ident (Prolog Variable)').
'symbol'('l3','l','src_span'(12,7,12,8,1,1),'Ident (Prolog Variable)').
'symbol'('l4','l','src_span'(13,10,13,11,1,1),'Ident (Prolog Variable)').
'symbol'('l5','l','src_span'(16,7,16,8,1,1),'Ident (Prolog Variable)').
'symbol'('l6','l','src_span'(17,21,17,22,1,1),'Ident (Prolog Variable)').
'symbol'('l7','l','src_span'(18,12,18,13,1,1),'Ident (Prolog Variable)').
'symbol'('l8','l','src_span'(32,7,32,8,1,1),'Ident (Prolog Variable)').
'symbol'('l9','l','src_span'(33,14,33,15,1,1),'Ident (Prolog Variable)').
'symbol'('l10','l','src_span'(34,12,34,13,1,1),'Ident (Prolog Variable)').
'symbol'('l11','l','src_span'(39,7,39,8,1,1),'Ident (Prolog Variable)').
'symbol'('l12','l','src_span'(40,26,40,27,1,1),'Ident (Prolog Variable)').
'symbol'('l13','l','src_span'(41,12,41,13,1,1),'Ident (Prolog Variable)').
'symbol'('reverse','reverse','src_span'(20,1,20,8,7,7),'Function or Process').
'symbol'('e1','e1','src_span'(40,17,40,19,2,2),'Ident (Prolog Variable)').
'symbol'('e2','e2','src_span'(40,22,40,24,2,2),'Ident (Prolog Variable)').
'symbol'('out','out','src_span'(3,9,3,12,3,3),'Channel').
'symbol'('both','both','src_span'(31,9,31,13,4,4),'Channel').
'symbol'('Q','Q','src_span'(6,1,6,2,1,1),'Function or Process').
'symbol'('PROB_TEST_TRACE','PROB_TEST_TRACE','src_span'(45,1,45,16,15,15),'Ident (Groundrep.)').
'symbol'('s','s','src_span'(17,15,17,16,1,1),'Ident (Prolog Variable)').
'symbol'('s2','s','src_span'(21,13,21,14,1,1),'Ident (Prolog Variable)').
'symbol'('t','t','src_span'(7,7,7,8,1,1),'Ident (Prolog Variable)').
'symbol'('t2','t','src_span'(13,4,13,5,1,1),'Ident (Prolog Variable)').
'symbol'('t3','t','src_span'(17,12,17,13,1,1),'Ident (Prolog Variable)').
'symbol'('t4','t','src_span'(18,5,18,6,1,1),'Ident (Prolog Variable)').
'symbol'('t5','t','src_span'(33,8,33,9,1,1),'Ident (Prolog Variable)').
'symbol'('t6','t','src_span'(34,5,34,6,1,1),'Ident (Prolog Variable)').
'symbol'('t7','t','src_span'(40,14,40,15,1,1),'Ident (Prolog Variable)').
'symbol'('t8','t','src_span'(41,5,41,6,1,1),'Ident (Prolog Variable)').
'symbol'('TEST','TEST','src_span'(23,1,23,5,4,4),'Ident (Groundrep.)').
'symbol'('x','x','src_span'(7,4,7,5,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(13,7,13,8,1,1),'Ident (Prolog Variable)').
'symbol'('x3','x','src_span'(17,18,17,19,1,1),'Ident (Prolog Variable)').
'symbol'('x4','x','src_span'(18,8,18,9,1,1),'Ident (Prolog Variable)').
'symbol'('x5','x','src_span'(21,10,21,11,1,1),'Ident (Prolog Variable)').
'symbol'('x6','x','src_span'(33,11,33,12,1,1),'Ident (Prolog Variable)').
'symbol'('x7','x','src_span'(34,8,34,9,1,1),'Ident (Prolog Variable)').
'symbol'('x8','x','src_span'(41,8,41,9,1,1),'Ident (Prolog Variable)').
'symbol'('MAIN','MAIN','src_span'(25,1,25,5,4,4),'Ident (Groundrep.)').

