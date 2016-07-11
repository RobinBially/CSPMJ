:- dynamic parserVersionNum/1, parserVersionStr/1, parseResult/5.
:- dynamic module/4.
'parserVersionStr'('CSPMJ V0.49').
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
'parserVersionNum'([0,49]).
'parserVersionStr'('CSPMJ V0.49').
'comment'('lineComment'('-- This is a Line-Comment.','src_span'(1,1,1,27,26,26))).
'bindval'('Eol','int'(42),'src_span'(2,1,2,9,8,8)).
'comment'('blockComment'('{-\r\nAnd this is\r\na Multiline-Comment.\r\n-}','src_span'(3,1,3,42,41,41))).
'dataTypeDef'('dt',['constructor'('cn'),'constructorC'('cn2','dotTupleType'(['boolType']))]).
'subTypeDef'('st',['constructor'('cn')]).
'nameType'('nt','type'('dotTupleType'(['boolType','intType']))).
'channel'('c1','type'('dotTupleType'(['boolType','intType']))).
'channel'('c2','type'('dotTupleType'(['boolType','intType']))).
'channel'('c3','type'('dotTupleType'(['boolType','intType']))).
'bindval'('A','stop'('src_span'(11,5,11,9,4,4)),'src_span'(11,1,11,9,8,8)).
'agent'('B'(_x,_y),'skip'('src_span'(12,10,12,14,4,4)),'src_span'(12,1,12,14,13,13)).
'agent'('B'(_x2,_y2),'int'(5),'src_span'(13,1,13,11,10,10)).
'bindval'('C','emptyMap','src_span'(14,1,14,10,9,9)).
'bindval'('D','mapExp'(['int'(1),'int'(2)]),'src_span'(15,1,15,13,12,12)).
'bindval'('E','char'('a'),'src_span'(16,1,16,8,7,7)).
'agent'('F'(_x3,_y3),'tupleExp'(['int'(1),'int'(2)]),'src_span'(17,1,17,15,14,14)).
'agent'('G'(_a),'agent_call'('src_span'(18,8,18,12,4,4),'F',[_a]),'src_span'(18,1,18,12,11,11)).
'bindval'('H','events'('src_span'(19,5,19,11,6,6)),'src_span'(19,1,19,11,10,10)).
'agent'('I'(_a2),'val_of'('A','src_span'(20,8,20,9,1,1)),'src_span'(20,1,20,9,8,8)).
'agent'('I'(_a3),_a3,'src_span'(21,1,21,9,8,8)).
'agent'('I'(_A2),'val_of'('a','src_span'(22,8,22,9,1,1)),'src_span'(22,1,22,9,8,8)).
'agent'('I'(_A3),_A3,'src_span'(23,1,23,9,8,8)).
'bindval'('J','\'('int'(1),'int'(2),'src_span_operator'('no_loc_info_available','src_span'(24,6,24,9,3,3))),'src_span'(24,1,24,10,9,9)).
'bindval'('K','|||'('int'(1),'int'(2),'src_span_operator'('no_loc_info_available','src_span'(25,6,25,11,5,5))),'src_span'(25,1,25,12,11,11)).
'bindval'('L','exception'('int'(2),'int'(3),'int'(4),'src_span'(26,6,26,9,3,3)),'src_span'(26,1,26,14,13,13)).
'bindval'('M','sharing'('int'(2),'int'(3),'int'(4),'src_span'(27,6,27,9,3,3)),'src_span'(27,1,27,14,13,13)).
'bindval'('N','aParallel'('int'(2),'int'(3),'int'(4),'int'(5),'src_span'(28,6,28,8,2,2)),'src_span'(28,1,28,15,14,14)).
'bindval'('O','lParallel'('linkList'(['link'('int'(3),'int'(4))]),'int'(2),'int'(5),'src_span'(29,6,29,8,2,2)),'src_span'(29,1,29,16,15,15)).
'bindval'('P','lParallel'('linkList'(['link'('int'(3),'int'(4)),'link'('int'(6),'int'(7))]),'int'(2),'int'(5),'src_span'(30,6,30,8,2,2)),'src_span'(30,1,30,22,21,21)).
'bindval'('Q','|~|'('int'(2),'int'(3),'src_span_operator'('no_loc_info_available','src_span'(31,6,31,11,5,5))),'src_span'(31,1,31,12,11,11)).
'bindval'('R','[]'('int'(2),'int'(3),'src_span_operator'('no_loc_info_available','src_span'(32,6,32,10,4,4))),'src_span'(32,1,32,11,10,10)).
'bindval'('S','syncExtChoice'('int'(2),'int'(3),'int'(4),'src_span'(33,6,33,10,4,4)),'src_span'(33,1,33,16,15,15)).
'bindval'('T','/\'('int'(2),'int'(3),'src_span_operator'('no_loc_info_available','src_span'(34,6,34,10,4,4))),'src_span'(34,1,34,11,10,10)).
'bindval'('U','syncInterrupt'('int'(2),'int'(1),'int'(3),'src_span'(35,6,35,10,4,4)),'src_span'(35,1,35,16,15,15)).
'bindval'('V','[>'('int'(2),'int'(3),'src_span_operator'('no_loc_info_available','src_span'(36,6,36,10,4,4))),'src_span'(36,1,36,11,10,10)).
'bindval'('W',';'('int'(2),'int'(3),'src_span_operator'('no_loc_info_available','src_span'(37,6,37,9,3,3))),'src_span'(37,1,37,10,9,9)).
'bindval'('X','&'('int'(2),'int'(3)),'src_span'(38,1,38,10,9,9)).
'bindval'('Y','prefix'('src_span'(39,5,39,6,1,1),[],'int'(1),'int'(5),'src_span'(39,6,39,10,4,4)),'src_span'(39,1,39,11,10,10)).
'bindval'('Z','prefix'('src_span'(40,5,40,6,1,1),['in'('dotpat'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'src_span'(40,10,40,14,4,4)),'src_span'(40,1,40,15,14,14)).
'agent'('a4'(_d),'prefix'('src_span'(41,8,41,9,1,1),['inGuard'('dotpat'(['int'(1),'int'(2)]),'dotTuple'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'src_span'(41,17,41,21,4,4)),'src_span'(41,1,41,22,21,21)).
'agent'('a4'(_d2),'int'(1),'src_span'(42,1,42,9,8,8)).
'bindval'('b','prefix'('src_span'(43,5,43,6,1,1),['nondetIn'('dotpat'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'src_span'(43,10,43,14,4,4)),'src_span'(43,1,43,15,14,14)).
'bindval'('c','prefix'('src_span'(44,5,44,6,1,1),['nondetInGuard'('dotpat'(['int'(1),'int'(2)]),'dotTuple'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'src_span'(44,14,44,18,4,4)),'src_span'(44,1,44,19,18,18)).
'bindval'('d3','prefix'('src_span'(45,5,45,6,1,1),['out'('dotTuple'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'src_span'(45,10,45,14,4,4)),'src_span'(45,1,45,15,14,14)).
'bindval'('e','prefix'('src_span'(46,5,46,6,1,1),['in'('alsoPattern'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'src_span'(46,11,46,15,4,4)),'src_span'(46,1,46,16,15,15)).
'bindval'('f','prefix'('src_span'(47,5,47,6,1,1),['in'('appendPattern'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'src_span'(47,10,47,14,4,4)),'src_span'(47,1,47,15,14,14)).
'bindval'('g','^'('int'(1),'int'(2)),'src_span'(48,1,48,8,7,7)).
'bindval'('h','lambda'([_a5,_b2],'int'(3)),'src_span'(49,1,49,11,10,10)).
'agent'('i'(_a6),'lambda'([_b3],_a6,_b3),'src_span'(50,1,50,14,13,13)).
'comment'('lineComment'('-- j(y,y) = y -- This should throw a redef. error!','src_span'(51,1,51,51,50,50))).
'comment'('lineComment'('-- j(y) = 1?y?y -> 5 -- This should throw a redef. error!','src_span'(52,1,52,58,57,57))).
'comment'('lineComment'('-- j(y) = \b,b@a+b -- This should throw a redef. error!','src_span'(53,1,53,56,55,55))).
'agent'('j'(_y4),'prefix'('src_span'(54,8,54,9,1,1),['in'(_y5)],'int'(1),_y5,'src_span'(54,11,54,15,4,4)),'src_span'(54,1,54,16,15,15)).
'bindval'('k','let'(['agent'('I2'(_a7),'int'(1),'src_span'(56,3,56,11,8,8)),'agent'('I2'(_a8),'let'(['agent'('I3'(_a9),_a9,'src_span'(58,5,58,13,8,8))],'int'(8)),'src_span'(57,3,59,12,9,9)),'agent'('I2'(_a10),'int'(3),'src_span'(60,3,60,11,8,8))],'int'(9)),'src_span'(55,1,61,10,9,9)).
'agent'('l'(_x4),'let'(['bindval'('u','int'(1),'src_span'(63,4,63,9,5,5)),'agent'('b4'(_u2),'let'(['bindval'('c2',_u2,'src_span'(65,6,65,11,5,5))],'int'(1)),'src_span'(64,4,66,13,9,9))],_x4),'src_span'(62,1,67,11,10,10)).
'bindval'('m','ifte'('true','int'(1),'int'(1),'src_span'(71,5,71,7,2,2),'src_span'(71,12,71,18,6,6),'src_span'(71,19,71,25,6,6)),'src_span'(71,1,71,26,25,25)).
'bindval'('n','repChoice'(['comprehensionGuard'('true')],'int'(1),'src_span'(72,8,72,12,4,4)),'src_span'(72,1,72,16,15,15)).
'bindval'('o','repInternalChoice'(['comprehensionGenerator'('int'(1),'int'(2))],'int'(1),'src_span'(73,9,73,12,3,3)),'src_span'(73,1,73,16,15,15)).
'bindval'('p','repInterleave'(['comprehensionGenerator'('int'(1),'int'(2)),'comprehensionGuard'('true')],'int'(2),'src_span'(74,9,74,17,8,8)),'src_span'(74,1,74,21,20,20)).
'bindval'('q','procRepAParallel'(['comprehensionGuard'('int'(1))],'pair'('true','int'(1)),'src_span'(75,8,75,9,1,1)),'src_span'(75,1,75,20,19,19)).
'bindval'('r','repSequence'(['comprehensionGuard'('int'(1))],'int'(2),'src_span'(76,7,76,8,1,1)),'src_span'(76,1,76,12,11,11)).
'bindval'('s','procRepSharing'('true',['comprehensionGuard'('int'(1))],'int'(2),'src_span'(77,14,77,15,1,1)),'src_span'(77,1,77,19,18,18)).
'comment'('lineComment'('-- This is an End-Of-File-Comment.','src_span'(79,1,79,35,34,34))).
'symbol'('dt','dt','src_span'(7,10,7,12,2,2),'Datatype').
'symbol'('cn2','cn2','src_span'(7,18,7,21,3,3),'Constructor of Datatype').
'symbol'('c1','c1','src_span'(10,9,10,11,2,2),'Channel').
'symbol'('c2','c2','src_span'(10,12,10,14,2,2),'Channel').
'symbol'('c3','c3','src_span'(10,15,10,17,2,2),'Channel').
'symbol'('A','A','src_span'(11,1,11,2,1,1),'Ident (Groundrep.)').
'symbol'('A2','A','src_span'(22,3,22,4,1,1),'Ident (Prolog Variable)').
'symbol'('A3','A','src_span'(23,3,23,4,1,1),'Ident (Prolog Variable)').
'symbol'('B','B','src_span'(12,1,12,2,1,1),'Function or Process').
'symbol'('C','C','src_span'(14,1,14,2,1,1),'Ident (Groundrep.)').
'symbol'('D','D','src_span'(15,1,15,2,1,1),'Ident (Groundrep.)').
'symbol'('E','E','src_span'(16,1,16,2,1,1),'Ident (Groundrep.)').
'symbol'('nt','nt','src_span'(9,10,9,12,2,2),'Nametype').
'symbol'('F','F','src_span'(17,1,17,2,1,1),'Function or Process').
'symbol'('G','G','src_span'(18,1,18,2,1,1),'Function or Process').
'symbol'('H','H','src_span'(19,1,19,2,1,1),'Ident (Groundrep.)').
'symbol'('I','I','src_span'(20,1,20,2,1,1),'Function or Process').
'symbol'('I2','I','src_span'(56,3,56,4,1,1),'Function or Process').
'symbol'('I3','I','src_span'(58,5,58,6,1,1),'Function or Process').
'symbol'('J','J','src_span'(24,1,24,2,1,1),'Ident (Groundrep.)').
'symbol'('K','K','src_span'(25,1,25,2,1,1),'Ident (Groundrep.)').
'symbol'('L','L','src_span'(26,1,26,2,1,1),'Ident (Groundrep.)').
'symbol'('M','M','src_span'(27,1,27,2,1,1),'Ident (Groundrep.)').
'symbol'('N','N','src_span'(28,1,28,2,1,1),'Ident (Groundrep.)').
'symbol'('O','O','src_span'(29,1,29,2,1,1),'Ident (Groundrep.)').
'symbol'('P','P','src_span'(30,1,30,2,1,1),'Ident (Groundrep.)').
'symbol'('Q','Q','src_span'(31,1,31,2,1,1),'Ident (Groundrep.)').
'symbol'('R','R','src_span'(32,1,32,2,1,1),'Ident (Groundrep.)').
'symbol'('S','S','src_span'(33,1,33,2,1,1),'Ident (Groundrep.)').
'symbol'('T','T','src_span'(34,1,34,2,1,1),'Ident (Groundrep.)').
'symbol'('U','U','src_span'(35,1,35,2,1,1),'Ident (Groundrep.)').
'symbol'('V','V','src_span'(36,1,36,2,1,1),'Ident (Groundrep.)').
'symbol'('W','W','src_span'(37,1,37,2,1,1),'Ident (Groundrep.)').
'symbol'('X','X','src_span'(38,1,38,2,1,1),'Ident (Groundrep.)').
'symbol'('Y','Y','src_span'(39,1,39,2,1,1),'Ident (Groundrep.)').
'symbol'('Z','Z','src_span'(40,1,40,2,1,1),'Ident (Groundrep.)').
'symbol'('st','st','src_span'(8,9,8,11,2,2),'Datatype').
'symbol'('a','a','src_span'(18,3,18,4,1,1),'Ident (Prolog Variable)').
'symbol'('a2','a','src_span'(20,3,20,4,1,1),'Ident (Prolog Variable)').
'symbol'('a3','a','src_span'(21,3,21,4,1,1),'Ident (Prolog Variable)').
'symbol'('a4','a','src_span'(41,1,41,2,1,1),'Function or Process').
'symbol'('a5','a','src_span'(49,6,49,7,1,1),'Ident (Prolog Variable)').
'symbol'('a6','a','src_span'(50,3,50,4,1,1),'Ident (Prolog Variable)').
'symbol'('a7','a','src_span'(56,5,56,6,1,1),'Ident (Prolog Variable)').
'symbol'('a8','a','src_span'(57,5,57,6,1,1),'Ident (Prolog Variable)').
'symbol'('a9','a','src_span'(58,7,58,8,1,1),'Ident (Prolog Variable)').
'symbol'('a10','a','src_span'(60,5,60,6,1,1),'Ident (Prolog Variable)').
'symbol'('b','b','src_span'(43,1,43,2,1,1),'Ident (Groundrep.)').
'symbol'('b2','b','src_span'(49,8,49,9,1,1),'Ident (Prolog Variable)').
'symbol'('b3','b','src_span'(50,9,50,10,1,1),'Ident (Prolog Variable)').
'symbol'('b4','b','src_span'(64,4,64,5,1,1),'Function or Process').
'symbol'('Eol','Eol','src_span'(2,1,2,4,3,3),'Ident (Groundrep.)').
'symbol'('c','c','src_span'(44,1,44,2,1,1),'Ident (Groundrep.)').
'symbol'('c2','c','src_span'(65,6,65,7,1,1),'Ident (Groundrep.)').
'symbol'('d','d','src_span'(41,3,41,4,1,1),'Ident (Prolog Variable)').
'symbol'('d2','d','src_span'(42,3,42,4,1,1),'Ident (Prolog Variable)').
'symbol'('d3','d','src_span'(45,1,45,2,1,1),'Ident (Groundrep.)').
'symbol'('e','e','src_span'(46,1,46,2,1,1),'Ident (Groundrep.)').
'symbol'('f','f','src_span'(47,1,47,2,1,1),'Ident (Groundrep.)').
'symbol'('g','g','src_span'(48,1,48,2,1,1),'Ident (Groundrep.)').
'symbol'('h','h','src_span'(49,1,49,2,1,1),'Ident (Groundrep.)').
'symbol'('i','i','src_span'(50,1,50,2,1,1),'Function or Process').
'symbol'('j','j','src_span'(54,1,54,2,1,1),'Function or Process').
'symbol'('cn','cn','src_span'(8,14,8,16,2,2),'Constructor of Datatype').
'symbol'('k','k','src_span'(55,1,55,2,1,1),'Ident (Groundrep.)').
'symbol'('l','l','src_span'(62,1,62,2,1,1),'Function or Process').
'symbol'('m','m','src_span'(71,1,71,2,1,1),'Ident (Groundrep.)').
'symbol'('n','n','src_span'(72,1,72,2,1,1),'Ident (Groundrep.)').
'symbol'('o','o','src_span'(73,1,73,2,1,1),'Ident (Groundrep.)').
'symbol'('p','p','src_span'(74,1,74,2,1,1),'Ident (Groundrep.)').
'symbol'('q','q','src_span'(75,1,75,2,1,1),'Ident (Groundrep.)').
'symbol'('r','r','src_span'(76,1,76,2,1,1),'Ident (Groundrep.)').
'symbol'('s','s','src_span'(77,1,77,2,1,1),'Ident (Groundrep.)').
'symbol'('u','u','src_span'(63,4,63,5,1,1),'Ident (Groundrep.)').
'symbol'('u2','u','src_span'(64,6,64,7,1,1),'Ident (Prolog Variable)').
'symbol'('x','x','src_span'(12,3,12,4,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(13,3,13,4,1,1),'Ident (Prolog Variable)').
'symbol'('x3','x','src_span'(17,3,17,4,1,1),'Ident (Prolog Variable)').
'symbol'('x4','x','src_span'(62,3,62,4,1,1),'Ident (Prolog Variable)').
'symbol'('y','y','src_span'(12,5,12,6,1,1),'Ident (Prolog Variable)').
'symbol'('y2','y','src_span'(13,5,13,6,1,1),'Ident (Prolog Variable)').
'symbol'('y3','y','src_span'(17,5,17,6,1,1),'Ident (Prolog Variable)').
'symbol'('y4','y','src_span'(54,3,54,4,1,1),'Ident (Prolog Variable)').
'symbol'('y5','y','src_span'(54,10,54,11,1,1),'Ident (Prolog Variable)').

