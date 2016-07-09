:- dynamic parserVersionNum/1, parserVersionStr/1, parseResult/5.
:- dynamic module/4.
'parserVersionStr'('0.6.1.1').
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
'parserVersionNum'([0,11,0,1]).
'parserVersionStr'('CSPM-Frontent-0.11.0.1').
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
'bindval'('G','agent_call'('src_span'(18,5,18,11,6,6),'F',['int'(1),'int'(2)]),'src_span'(18,1,18,11,10,10)).
'bindval'('H','events'('src_span'(19,5,19,11,6,6)),'src_span'(19,1,19,11,10,10)).
'bindval'('I','val_of'('A','src_span'(20,5,20,6,1,1)),'src_span'(20,1,20,6,5,5)).
'bindval'('J','\'('int'(1),'int'(2),'src_span_operator'('no_loc_info_available','src_span'(21,6,21,9,3,3))),'src_span'(21,1,21,10,9,9)).
'bindval'('K','|||'('int'(1),'int'(2),'src_span_operator'('no_loc_info_available','src_span'(22,6,22,11,5,5))),'src_span'(22,1,22,12,11,11)).
'bindval'('L','exception'('int'(2),'int'(3),'int'(4),'src_span'(23,6,23,9,3,3)),'src_span'(23,1,23,14,13,13)).
'bindval'('M','sharing'('int'(2),'int'(3),'int'(4),'src_span'(24,6,24,9,3,3)),'src_span'(24,1,24,14,13,13)).
'bindval'('N','aParallel'('int'(2),'int'(3),'int'(4),'int'(5),'src_span'(25,6,25,8,2,2)),'src_span'(25,1,25,15,14,14)).
'bindval'('O','lParallel'('linkList'(['link'('int'(3),'int'(4))]),'int'(2),'int'(5),'src_span'(26,6,26,8,2,2)),'src_span'(26,1,26,16,15,15)).
'bindval'('P','lParallel'('linkList'(['link'('int'(3),'int'(4)),'link'('int'(6),'int'(7))]),'int'(2),'int'(5),'src_span'(27,6,27,8,2,2)),'src_span'(27,1,27,22,21,21)).
'bindval'('Q','|~|'('int'(2),'int'(3),'src_span_operator'('no_loc_info_available','src_span'(28,6,28,11,5,5))),'src_span'(28,1,28,12,11,11)).
'bindval'('R','[]'('int'(2),'int'(3),'src_span_operator'('no_loc_info_available','src_span'(29,6,29,10,4,4))),'src_span'(29,1,29,11,10,10)).
'bindval'('S','syncExtChoice'('int'(2),'int'(3),'int'(4),'src_span'(30,6,30,10,4,4)),'src_span'(30,1,30,16,15,15)).
'bindval'('T','/\'('int'(2),'int'(3),'src_span_operator'('no_loc_info_available','src_span'(31,6,31,10,4,4))),'src_span'(31,1,31,11,10,10)).
'bindval'('U','syncInterrupt'('int'(2),'int'(1),'int'(3),'src_span'(32,6,32,10,4,4)),'src_span'(32,1,32,16,15,15)).
'bindval'('V','[>'('int'(2),'int'(3),'src_span_operator'('no_loc_info_available','src_span'(33,6,33,10,4,4))),'src_span'(33,1,33,11,10,10)).
'bindval'('W',';'('int'(2),'int'(3),'src_span_operator'('no_loc_info_available','src_span'(34,6,34,9,3,3))),'src_span'(34,1,34,10,9,9)).
'bindval'('X','&'('int'(2),'int'(3)),'src_span'(35,1,35,10,9,9)).
'bindval'('Y','prefix'('src_span'(36,5,36,6,1,1),[],'int'(1),'int'(5),'src_span'(36,6,36,10,4,4)),'src_span'(36,1,36,11,10,10)).
'bindval'('Z','prefix'('src_span'(37,5,37,6,1,1),['in'('dotpat'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'src_span'(37,10,37,14,4,4)),'src_span'(37,1,37,15,14,14)).
'bindval'('a','prefix'('src_span'(38,5,38,6,1,1),['inGuard'('dotpat'(['int'(1),'int'(2)]),'dotTuple'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'src_span'(38,14,38,18,4,4)),'src_span'(38,1,38,19,18,18)).
'bindval'('b','prefix'('src_span'(39,5,39,6,1,1),['nondetIn'('dotpat'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'src_span'(39,10,39,14,4,4)),'src_span'(39,1,39,15,14,14)).
'bindval'('c','prefix'('src_span'(40,5,40,6,1,1),['nondetInGuard'('dotpat'(['int'(1),'int'(2)]),'dotTuple'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'src_span'(40,14,40,18,4,4)),'src_span'(40,1,40,19,18,18)).
'bindval'('d','prefix'('src_span'(41,5,41,6,1,1),['out'('dotTuple'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'src_span'(41,10,41,14,4,4)),'src_span'(41,1,41,15,14,14)).
'bindval'('e','prefix'('src_span'(42,5,42,6,1,1),['in'('alsoPattern'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'src_span'(42,11,42,15,4,4)),'src_span'(42,1,42,16,15,15)).
'bindval'('f','prefix'('src_span'(43,5,43,6,1,1),['in'('appendPattern'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'src_span'(43,10,43,14,4,4)),'src_span'(43,1,43,15,14,14)).
'bindval'('g','^'('int'(1),'int'(2)),'src_span'(44,1,44,8,7,7)).
'comment'('lineComment'('-- This is an End-Of-File-Comment.','src_span'(45,1,45,35,34,34))).
'symbol'('A','A','src_span'(11,1,11,2,1,1),'Ident (Groundrep.)').
'symbol'('B','B','src_span'(13,1,13,2,1,1),'Function or Process').
'symbol'('C','C','src_span'(14,1,14,2,1,1),'Ident (Groundrep.)').
'symbol'('D','D','src_span'(15,1,15,2,1,1),'Ident (Groundrep.)').
'symbol'('E','E','src_span'(16,1,16,2,1,1),'Ident (Groundrep.)').
'symbol'('nt','nt','src_span'(9,10,9,12,2,2),'Nametype').
'symbol'('F','F','src_span'(17,1,17,2,1,1),'Function or Process').
'symbol'('G','G','src_span'(18,1,18,2,1,1),'Ident (Groundrep.)').
'symbol'('H','H','src_span'(19,1,19,2,1,1),'Ident (Groundrep.)').
'symbol'('I','I','src_span'(20,1,20,2,1,1),'Ident (Groundrep.)').
'symbol'('J','J','src_span'(21,1,21,2,1,1),'Ident (Groundrep.)').
'symbol'('K','K','src_span'(22,1,22,2,1,1),'Ident (Groundrep.)').
'symbol'('L','L','src_span'(23,1,23,2,1,1),'Ident (Groundrep.)').
'symbol'('M','M','src_span'(24,1,24,2,1,1),'Ident (Groundrep.)').
'symbol'('N','N','src_span'(25,1,25,2,1,1),'Ident (Groundrep.)').
'symbol'('O','O','src_span'(26,1,26,2,1,1),'Ident (Groundrep.)').
'symbol'('dt','dt','src_span'(7,10,7,12,2,2),'Datatype').
'symbol'('P','P','src_span'(27,1,27,2,1,1),'Ident (Groundrep.)').
'symbol'('Q','Q','src_span'(28,1,28,2,1,1),'Ident (Groundrep.)').
'symbol'('R','R','src_span'(29,1,29,2,1,1),'Ident (Groundrep.)').
'symbol'('S','S','src_span'(30,1,30,2,1,1),'Ident (Groundrep.)').
'symbol'('T','T','src_span'(31,1,31,2,1,1),'Ident (Groundrep.)').
'symbol'('U','U','src_span'(32,1,32,2,1,1),'Ident (Groundrep.)').
'symbol'('V','V','src_span'(33,1,33,2,1,1),'Ident (Groundrep.)').
'symbol'('W','W','src_span'(34,1,34,2,1,1),'Ident (Groundrep.)').
'symbol'('X','X','src_span'(35,1,35,2,1,1),'Ident (Groundrep.)').
'symbol'('Y','Y','src_span'(36,1,36,2,1,1),'Ident (Groundrep.)').
'symbol'('Z','Z','src_span'(37,1,37,2,1,1),'Ident (Groundrep.)').
'symbol'('st','st','src_span'(8,9,8,11,2,2),'Datatype').
'symbol'('a','a','src_span'(38,1,38,2,1,1),'Ident (Groundrep.)').
'symbol'('b','b','src_span'(39,1,39,2,1,1),'Ident (Groundrep.)').
'symbol'('Eol','Eol','src_span'(2,1,2,4,3,3),'Ident (Groundrep.)').
'symbol'('c','c','src_span'(40,1,40,2,1,1),'Ident (Groundrep.)').
'symbol'('d','d','src_span'(41,1,41,2,1,1),'Ident (Groundrep.)').
'symbol'('e','e','src_span'(42,1,42,2,1,1),'Ident (Groundrep.)').
'symbol'('cn2','cn2','src_span'(7,18,7,21,3,3),'Constructor of Datatype').
'symbol'('f','f','src_span'(43,1,43,2,1,1),'Ident (Groundrep.)').
'symbol'('g','g','src_span'(44,1,44,2,1,1),'Ident (Groundrep.)').
'symbol'('cn','cn','src_span'(8,14,8,16,2,2),'Constructor of Datatype').
'symbol'('c1','c1','src_span'(10,9,10,11,2,2),'Channel').
'symbol'('c2','c2','src_span'(10,12,10,14,2,2),'Channel').
'symbol'('c3','c3','src_span'(10,15,10,17,2,2),'Channel').
'symbol'('x','x','src_span'(12,3,12,4,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(13,3,13,4,1,1),'Ident (Prolog Variable)').
'symbol'('x3','x','src_span'(17,3,17,4,1,1),'Ident (Prolog Variable)').
'symbol'('y','y','src_span'(12,5,12,6,1,1),'Ident (Prolog Variable)').
'symbol'('y2','y','src_span'(13,5,13,6,1,1),'Ident (Prolog Variable)').
'symbol'('y3','y','src_span'(17,5,17,6,1,1),'Ident (Prolog Variable)').

