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
'channel'('out','type'('dotTupleType'(['setExp'('int'(0),'int'(99))]))).
'agent'('f'(_y,_z),'int'(3),'src_span'(5,1,5,11,10,10)).
'bindval'('MAIN1','prefix'('src_span'(8,9,8,12,3,3),['out'('agent_call'('src_span'(8,13,8,21,8,8),'f',['src_span'(8,15,8,16,1,1),[],'int'(2),'src_span'(8,17,8,20,3,3),[],'dotTuple'(['int'(2),'int'(2)])]))],'out','prefix'('src_span'(8,25,8,28,3,3),['out'('tupleExp'(['src_span'(8,30,8,40,10,10),[],'agent_call'('src_span'(8,30,8,40,10,10),'f',['src_span'(8,32,8,33,1,1),[],'int'(2),'src_span'(8,34,8,39,5,5),[],'dotTuple'(['int'(2),'int'(3),'int'(4)])])]))],'out','prefix'('src_span'(9,8,9,11,3,3),['out'('agent_call'('src_span'(9,12,9,22,10,10),'f',['src_span'(9,14,9,15,1,1),[],'int'(2),'src_span'(9,16,9,21,5,5),[],'dotTuple'(['int'(3),'int'(4),'int'(2)])]))],'out','prefix'('src_span'(9,26,9,29,3,3),['out'('agent_call'('src_span'(9,30,9,38,8,8),'f',['src_span'(9,32,9,33,1,1),[],'int'(2),'src_span'(9,34,9,37,3,3),[],'dotTuple'(['int'(3),'int'(2)])]))],'out','prefix'('src_span'(9,42,9,45,3,3),['out'('agent_call'('src_span'(9,46,9,56,10,10),'f',['src_span'(9,48,9,51,3,3),[],'dotTuple'(['int'(2),'int'(3)]),'src_span'(9,52,9,55,3,3),[],'dotTuple'(['int'(3),'int'(3)])]))],'out','stop'('src_span'(9,60,9,64,4,4)),'src_span'(9,56,9,60,4,4)),'src_span'(9,38,9,42,4,4)),'src_span'(9,22,9,26,4,4)),'src_span'(8,41,8,53,12,12)),'src_span'(8,21,8,25,4,4)),'src_span'(8,1,9,64,63,63)).
'agent'('g'('dotpat'([_x,_y2])),'agent_call'('src_span'(11,10,11,14,4,4),'g',[_y2]),_x,'src_span'(11,1,11,16,15,15)).
'agent'('g'(_z2),_z2,'src_span'(12,1,12,9,8,8)).
'bindval'('MAIN2','prefix'('src_span'(15,9,15,12,3,3),['out'('agent_call'('src_span'(15,13,15,17,4,4),'g',['src_span'(15,15,15,16,1,1),[],'int'(3)]))],'out','prefix'('src_span'(15,21,15,24,3,3),['out'('agent_call'('src_span'(15,25,15,33,8,8),'g',['src_span'(15,27,15,32,5,5),[],'dotTuple'(['int'(1),'int'(2),'int'(3)])]))],'out','prefix'('src_span'(15,37,15,40,3,3),['out'('agent_call'('src_span'(15,41,15,61,20,20),'g',['src_span'(15,43,15,60,17,17),[],'dotTuple'(['agent_call'('src_span'(15,43,15,49,6,6),'g',['src_span'(15,45,15,48,3,3),[],'dotTuple'(['int'(1),'int'(5)])]),'agent_call'('src_span'(15,50,15,57,7,7),'g',['src_span'(15,52,15,56,4,4),[],'dotTuple'(['int'(10),'int'(4)])]),'int'(10)])]))],'out','stop'('src_span'(15,65,15,69,4,4)),'src_span'(15,61,15,65,4,4)),'src_span'(15,33,15,37,4,4)),'src_span'(15,17,15,21,4,4)),'src_span'(15,1,15,69,68,68)).
'bindval'('MAIN2c','prefix'('src_span'(18,10,18,13,3,3),['out'('agent_call'('src_span'(18,14,18,18,4,4),'g',['src_span'(18,16,18,17,1,1),[],'int'(3)]))],'out','stop'('src_span'(18,22,18,26,4,4)),'src_span'(18,18,18,22,4,4)),'src_span'(18,1,18,26,25,25)).
'bindval'('MAIN2d','prefix'('src_span'(20,10,20,13,3,3),['out'('agent_call'('src_span'(20,14,20,20,6,6),'g',['src_span'(20,16,20,19,3,3),[],'dotTuple'(['int'(5),'int'(6)])]))],'out','stop'('src_span'(20,24,20,28,4,4)),'src_span'(20,20,20,24,4,4)),'src_span'(20,1,20,28,27,27)).
'agent'('fst'('dotpat'([_x2,_y3])),_x2,'src_span'(23,1,23,13,12,12)).
'bindval'('MAIN3','prefix'('src_span'(25,9,25,12,3,3),['out'('agent_call'('src_span'(25,13,25,23,10,10),'fst',['src_span'(25,17,25,22,5,5),[],'dotTuple'(['int'(22),'int'(33)])]))],'out','prefix'('src_span'(25,27,25,30,3,3),['out'('agent_call'('src_span'(25,31,25,47,16,16),'fst',['src_span'(25,35,25,46,11,11),[],'dotTuple'(['int'(11),'int'(22),'int'(33),'int'(44)])]))],'out','stop'('src_span'(25,51,25,55,4,4)),'src_span'(25,47,25,51,4,4)),'src_span'(25,23,25,27,4,4)),'src_span'(25,1,25,55,54,54)).
'agent'('g2'('dotpat'(['int'(1),_y4])),'agent_call'('src_span'(28,11,28,16,5,5),'g2',[_y4]),'int'(1),'src_span'(28,1,28,18,17,17)).
'agent'('g2'('dotpat'(['int'(2),_y5])),'agent_call'('src_span'(29,11,29,16,5,5),'g2',[_y5]),'int'(2),'src_span'(29,1,29,18,17,17)).
'agent'('g2'('dotpat'(['int'(3),_y6])),'agent_call'('src_span'(30,11,30,16,5,5),'g2',[_y6]),'int'(3),'src_span'(30,1,30,18,17,17)).
'agent'('g2'(_z3),_z3,'src_span'(31,1,31,10,9,9)).
'bindval'('MAIN4','prefix'('src_span'(34,9,34,12,3,3),['out'('agent_call'('src_span'(34,13,34,18,5,5),'g2',['src_span'(34,16,34,17,1,1),[],'int'(3)]))],'out','prefix'('src_span'(34,22,34,25,3,3),['out'('agent_call'('src_span'(34,26,34,35,9,9),'g2',['src_span'(34,29,34,34,5,5),[],'dotTuple'(['int'(1),'int'(2),'int'(3)])]))],'out','prefix'('src_span'(34,39,34,42,3,3),['out'('agent_call'('src_span'(34,43,34,64,21,21),'g2',['src_span'(34,46,34,63,17,17),[],'dotTuple'(['agent_call'('src_span'(34,46,34,53,7,7),'g2',['src_span'(34,49,34,52,3,3),[],'dotTuple'(['int'(1),'int'(2)])]),'agent_call'('src_span'(34,54,34,61,7,7),'g2',['src_span'(34,57,34,60,3,3),[],'dotTuple'(['int'(1),'int'(1)])]),'int'(3)])]))],'out','stop'('src_span'(34,68,34,72,4,4)),'src_span'(34,64,34,68,4,4)),'src_span'(34,35,34,39,4,4)),'src_span'(34,18,34,22,4,4)),'src_span'(34,1,34,72,71,71)).
'agent'('g2b'('dotpat'(['int'(1),_y7])),'agent_call'('src_span'(37,12,37,18,6,6),'g2b',[_y7]),'int'(1),'src_span'(37,1,37,20,19,19)).
'agent'('g2b'('dotpat'(['int'(2),_y8])),'agent_call'('src_span'(38,12,38,18,6,6),'g2b',[_y8]),'int'(2),'src_span'(38,1,38,20,19,19)).
'agent'('g2b'('dotpat'(['int'(3),_y9])),'agent_call'('src_span'(39,12,39,18,6,6),'g2b',[_y9]),'int'(3),'src_span'(39,1,39,20,19,19)).
'agent'('g2b'(_z4),'int'(0),'src_span'(40,1,40,11,10,10)).
'bindval'('MAIN4b','prefix'('src_span'(43,10,43,13,3,3),['out'('agent_call'('src_span'(43,14,43,20,6,6),'g2b',['src_span'(43,18,43,19,1,1),[],'int'(3)]))],'out','prefix'('src_span'(43,24,43,27,3,3),['out'('agent_call'('src_span'(43,28,43,38,10,10),'g2b',['src_span'(43,32,43,37,5,5),[],'dotTuple'(['int'(1),'int'(2),'int'(1)])]))],'out','stop'('src_span'(43,42,43,46,4,4)),'src_span'(43,38,43,42,4,4)),'src_span'(43,20,43,24,4,4)),'src_span'(43,1,43,46,45,45)).
'agent'('g3'('dotpat'(['int'(1),_y10])),'int'(1),'src_span'(46,1,46,12,11,11)).
'agent'('g3'('dotpat'(['int'(2),_y11])),'int'(2),'src_span'(47,1,47,12,11,11)).
'agent'('g3'('dotpat'(['int'(3),_y12])),'int'(3),'src_span'(48,1,48,12,11,11)).
'agent'('g3'(_z5),'int'(4),'src_span'(49,1,49,10,9,9)).
'bindval'('MAIN5','prefix'('src_span'(51,9,51,12,3,3),['out'('agent_call'('src_span'(51,13,51,21,8,8),'g3',['src_span'(51,16,51,20,4,4),[],'dotTuple'(['int'(1),'int'(11)])]))],'out','prefix'('src_span'(51,25,51,28,3,3),['out'('agent_call'('src_span'(51,29,51,40,11,11),'g3',['src_span'(51,32,51,39,7,7),[],'dotTuple'(['int'(2),'int'(22),'int'(33)])]))],'out','stop'('src_span'(51,44,51,48,4,4)),'src_span'(51,40,51,44,4,4)),'src_span'(51,21,51,25,4,4)),'src_span'(51,1,51,48,47,47)).
'bindval'('MAIN5b','prefix'('src_span'(53,10,53,13,3,3),['out'('agent_call'('src_span'(53,14,53,20,6,6),'g3',['src_span'(53,17,53,19,2,2),[],'int'(22)]))],'out','stop'('src_span'(53,24,53,28,4,4)),'src_span'(53,20,53,24,4,4)),'src_span'(53,1,53,28,27,27)).
'agent'('g4'('dotpat'([_x3,_y13])),'int'(3),_x3,'src_span'(55,1,55,14,13,13)).
'bindval'('MAIN6','prefix'('src_span'(57,9,57,12,3,3),['out'('agent_call'('src_span'(57,13,57,21,8,8),'g4',['src_span'(57,16,57,20,4,4),[],'dotTuple'(['int'(1),'int'(11)])]))],'out','prefix'('src_span'(57,25,57,28,3,3),['out'('agent_call'('src_span'(57,29,57,40,11,11),'g4',['src_span'(57,32,57,39,7,7),[],'dotTuple'(['int'(2),'int'(22),'int'(33)])]))],'out','stop'('src_span'(57,44,57,48,4,4)),'src_span'(57,40,57,44,4,4)),'src_span'(57,21,57,25,4,4)),'src_span'(57,1,57,48,47,47)).
'bindval'('MAIN6b','prefix'('src_span'(59,10,59,13,3,3),['out'('agent_call'('src_span'(59,14,59,19,5,5),'g4',['src_span'(59,17,59,18,1,1),[],'int'(2)]))],'out','stop'('src_span'(59,23,59,27,4,4)),'src_span'(59,19,59,23,4,4)),'src_span'(59,1,59,27,26,26)).
'symbol'('MAIN4','MAIN4','src_span'(34,1,34,6,5,5),'Ident (Groundrep.)').
'symbol'('MAIN3','MAIN3','src_span'(25,1,25,6,5,5),'Ident (Groundrep.)').
'symbol'('MAIN2','MAIN2','src_span'(15,1,15,6,5,5),'Ident (Groundrep.)').
'symbol'('MAIN1','MAIN1','src_span'(8,1,8,6,5,5),'Ident (Groundrep.)').
'symbol'('f','f','src_span'(5,1,5,2,1,1),'Function or Process').
'symbol'('fst','fst','src_span'(23,1,23,4,3,3),'Function or Process').
'symbol'('MAIN6','MAIN6','src_span'(57,1,57,6,5,5),'Ident (Groundrep.)').
'symbol'('g','g','src_span'(11,1,11,2,1,1),'Function or Process').
'symbol'('MAIN5','MAIN5','src_span'(51,1,51,6,5,5),'Ident (Groundrep.)').
'symbol'('g2','g2','src_span'(28,1,28,3,2,2),'Function or Process').
'symbol'('g3','g3','src_span'(46,1,46,3,2,2),'Function or Process').
'symbol'('g4','g4','src_span'(55,1,55,3,2,2),'Function or Process').
'symbol'('out','out','src_span'(2,9,2,12,3,3),'Channel').
'symbol'('MAIN2d','MAIN2d','src_span'(20,1,20,7,6,6),'Ident (Groundrep.)').
'symbol'('MAIN2c','MAIN2c','src_span'(18,1,18,7,6,6),'Ident (Groundrep.)').
'symbol'('g2b','g2b','src_span'(37,1,37,4,3,3),'Function or Process').
'symbol'('x','x','src_span'(11,3,11,4,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(23,5,23,6,1,1),'Ident (Prolog Variable)').
'symbol'('x3','x','src_span'(55,4,55,5,1,1),'Ident (Prolog Variable)').
'symbol'('y','y','src_span'(5,3,5,4,1,1),'Ident (Prolog Variable)').
'symbol'('y2','y','src_span'(11,5,11,6,1,1),'Ident (Prolog Variable)').
'symbol'('y3','y','src_span'(23,7,23,8,1,1),'Ident (Prolog Variable)').
'symbol'('y4','y','src_span'(28,6,28,7,1,1),'Ident (Prolog Variable)').
'symbol'('y5','y','src_span'(29,6,29,7,1,1),'Ident (Prolog Variable)').
'symbol'('y6','y','src_span'(30,6,30,7,1,1),'Ident (Prolog Variable)').
'symbol'('y7','y','src_span'(37,7,37,8,1,1),'Ident (Prolog Variable)').
'symbol'('y8','y','src_span'(38,7,38,8,1,1),'Ident (Prolog Variable)').
'symbol'('y9','y','src_span'(39,7,39,8,1,1),'Ident (Prolog Variable)').
'symbol'('y10','y','src_span'(46,6,46,7,1,1),'Ident (Prolog Variable)').
'symbol'('y11','y','src_span'(47,6,47,7,1,1),'Ident (Prolog Variable)').
'symbol'('y12','y','src_span'(48,6,48,7,1,1),'Ident (Prolog Variable)').
'symbol'('y13','y','src_span'(55,6,55,7,1,1),'Ident (Prolog Variable)').
'symbol'('z','z','src_span'(5,5,5,6,1,1),'Ident (Prolog Variable)').
'symbol'('z2','z','src_span'(12,3,12,4,1,1),'Ident (Prolog Variable)').
'symbol'('z3','z','src_span'(31,4,31,5,1,1),'Ident (Prolog Variable)').
'symbol'('z4','z','src_span'(40,5,40,6,1,1),'Ident (Prolog Variable)').
'symbol'('z5','z','src_span'(49,4,49,5,1,1),'Ident (Prolog Variable)').
'symbol'('MAIN4b','MAIN4b','src_span'(43,1,43,7,6,6),'Ident (Groundrep.)').
'symbol'('MAIN5b','MAIN5b','src_span'(53,1,53,7,6,6),'Ident (Groundrep.)').
'symbol'('MAIN6b','MAIN6b','src_span'(59,1,59,7,6,6),'Ident (Groundrep.)').

