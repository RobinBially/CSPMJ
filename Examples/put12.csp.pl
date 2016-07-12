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
'channel'('put','type'('dotTupleType'(['intType']))).
'bindval'('MAIN','[]'('[]'('[]'('val_of'('T','src_span'(3,8,3,9,1,1)),'prefix'('src_span'(3,14,3,17,3,3),['out'('int'(1))],'put','prefix'('src_span'(3,23,3,26,3,3),['out'('int'(2))],'put','val_of'('P','src_span'(3,32,3,33,1,1)),'src_span'(3,28,3,32,4,4)),'src_span'(3,19,3,23,4,4)),'src_span_operator'('no_loc_info_available','src_span'(3,9,3,14,5,5))),'prefix'('src_span'(3,37,3,40,3,3),['out'('int'(0))],'put','val_of'('R','src_span'(3,46,3,47,1,1)),'src_span'(3,42,3,46,4,4)),'src_span_operator'('no_loc_info_available','src_span'(3,33,3,37,4,4))),'tupleExp'(['|||'('prefix'('src_span'(3,54,3,57,3,3),['out'('int'(10))],'put','stop'('src_span'(3,64,3,68,4,4)),'src_span'(3,60,3,64,4,4)),'prefix'('src_span'(3,73,3,76,3,3),['out'('int'(11))],'put','stop'('src_span'(3,83,3,87,4,4)),'src_span'(3,79,3,83,4,4)),'src_span_operator'('no_loc_info_available','src_span'(3,68,3,73,5,5)))]),'src_span_operator'('no_loc_info_available','src_span'(3,47,3,53,6,6))),'src_span'(3,1,3,88,87,87)).
'bindval'('P','[]'('prefix'('src_span'(5,5,5,8,3,3),['out'('int'(3))],'put','agent_call'('src_span'(5,14,5,18,4,4),'Q',['int'(6)]),'src_span'(5,10,5,14,4,4)),'prefix'('src_span'(5,22,5,25,3,3),['out'('int'(4))],'put','val_of'('R2','src_span'(5,31,5,33,2,2)),'src_span'(5,27,5,31,4,4)),'src_span_operator'('no_loc_info_available','src_span'(5,18,5,22,4,4))),'src_span'(5,1,5,33,32,32)).
'bindval'('R','sharing'('prefix'('src_span'(7,5,7,8,3,3),['out'('int'(4))],'put','prefix'('src_span'(7,14,7,17,3,3),['in'(_z)],'put','stop'('src_span'(7,23,7,27,4,4)),'src_span'(7,19,7,23,4,4)),'src_span'(7,10,7,14,4,4)),'dotTuple'(['put','int'(4)]),'prefix'('src_span'(7,42,7,45,3,3),['in'(_y)],'put','prefix'('src_span'(7,51,7,54,3,3),['out'(_y)],'put','stop'('src_span'(7,60,7,64,4,4)),'src_span'(7,56,7,60,4,4)),'src_span'(7,47,7,51,4,4)),'src_span'(7,27,7,31,4,4)),'src_span'(7,1,7,64,63,63)).
'agent'('Q'(_x),'|~|'('prefix'('src_span'(9,8,9,11,3,3),['out'('int'(5))],'put','stop'('src_span'(9,17,9,21,4,4)),'src_span'(9,13,9,17,4,4)),'prefix'('src_span'(9,26,9,29,3,3),['out'(_x)],'put','agent_call'('src_span'(9,35,9,41,6,6),'Q',[_x,'int'(1)]),'src_span'(9,31,9,35,4,4)),'src_span_operator'('no_loc_info_available','src_span'(9,21,9,26,5,5))),'src_span'(9,1,9,41,40,40)).
'channel'('put2','type'('dotTupleType'(['intType','intType']))).
'bindval'('R2','sharing'('prefix'('src_span'(13,6,13,10,4,4),['out'('int'(4))],'put2','prefix'('src_span'(13,16,13,20,4,4),['in'(_z2)],'put2','stop'('src_span'(13,26,13,30,4,4)),'src_span'(13,22,13,26,4,4)),'src_span'(13,12,13,16,4,4)),'dotTuple'(['put2','int'(4),'int'(2)]),'prefix'('src_span'(13,48,13,52,4,4),['in'(_y2)],'put2','prefix'('src_span'(13,58,13,62,4,4),['out'(_y2)],'put2','stop'('src_span'(13,68,13,72,4,4)),'src_span'(13,64,13,68,4,4)),'src_span'(13,54,13,58,4,4)),'src_span'(13,30,13,34,4,4)),'src_span'(13,1,13,72,71,71)).
'dataTypeDef'('TRAINS',['constructor'('Thomas'),'constructor'('Gordon')]).
'channel'('go','type'('dotTupleType'(['val_of'('TRAINS','src_span'(17,14,17,20,6,6))]))).
'bindval'('T','sharing'('prefix'('src_span'(19,5,19,7,2,2),['in'(_t)],'go','prefix'('src_span'(19,13,19,15,2,2),['out'(_t)],'go','stop'('src_span'(19,19,19,23,4,4)),'src_span'(19,17,19,19,2,2)),'src_span'(19,9,19,13,4,4)),'go','prefix'('src_span'(19,38,19,40,2,2),['out'('Thomas')],'go','prefix'('src_span'(19,51,19,53,2,2),['in'(_t2)],'go','stop'('src_span'(19,60,19,64,4,4)),'src_span'(19,56,19,60,4,4)),'src_span'(19,47,19,51,4,4)),'src_span'(19,23,19,28,5,5)),'src_span'(19,1,19,64,63,63)).
'bindval'('TG2','sharing'('prefix'('src_span'(20,7,20,9,2,2),['in'(_t2)],'go','prefix'('src_span'(20,15,20,17,2,2),['out'(_t2)],'go','stop'('src_span'(20,21,20,25,4,4)),'src_span'(20,19,20,21,2,2)),'src_span'(20,11,20,15,4,4)),'dotTuple'(['go','Thomas']),'prefix'('src_span'(20,45,20,47,2,2),['out'('Thomas')],'go','prefix'('src_span'(20,58,20,60,2,2),['in'(_t22)],'go','stop'('src_span'(20,67,20,71,4,4)),'src_span'(20,63,20,67,4,4)),'src_span'(20,54,20,58,4,4)),'src_span'(20,25,20,30,5,5)),'src_span'(20,1,20,71,70,70)).
'channel'('meet','type'('dotTupleType'(['val_of'('TRAINS','src_span'(22,15,22,21,6,6)),'val_of'('TRAINS','src_span'(22,22,22,28,6,6))]))).
'bindval'('T2','sharing'('prefix'('src_span'(24,6,24,10,4,4),['in'('dotpat'([_t1,_t23]))],'meet','prefix'('src_span'(24,20,24,22,2,2),['out'(_t23)],'go','stop'('src_span'(24,29,24,33,4,4)),'src_span'(24,25,24,29,4,4)),'src_span'(24,16,24,20,4,4)),'meet','prefix'('src_span'(24,51,24,55,4,4),['in'('dotpat'([_t3,_Gordon2]))],'meet','stop'('src_span'(24,69,24,73,4,4)),'src_span'(24,65,24,69,4,4)),'src_span'(24,33,24,39,6,6)),'src_span'(24,1,24,73,72,72)).
'bindval'('T3','sharing'('val_of'('T2','src_span'(26,6,26,8,2,2)),'meet','prefix'('src_span'(26,27,26,31,4,4),['out'('Thomas'),'in'(_x2)],'meet','stop'('src_span'(26,44,26,48,4,4)),'src_span'(26,40,26,44,4,4)),'src_span'(26,8,26,14,6,6)),'src_span'(26,1,26,48,47,47)).
'bindval'('T4','sharing'('prefix'('src_span'(28,6,28,10,4,4),['out'('Thomas'),'in'(_x3)],'meet','stop'('src_span'(28,22,28,26,4,4)),'src_span'(28,19,28,22,3,3)),'meet','prefix'('src_span'(28,44,28,48,4,4),['out'('dotTuple'(['Thomas','Gordon']))],'meet','stop'('src_span'(28,66,28,70,4,4)),'src_span'(28,62,28,66,4,4)),'src_span'(28,26,28,32,6,6)),'src_span'(28,1,28,70,69,69)).
'agent'('T5'(_x4),'tupleExp'(['prefix'('src_span'(30,10,30,14,4,4),['out'('Thomas'),'out'('Gordon')],'meet','stop'('src_span'(30,32,30,36,4,4)),'src_span'(30,28,30,32,4,4))]),'dotTuple'(['meet',_x4]),'go','src_span'(30,1,30,56,55,55)).
'channel'('a','type'('dotUnitType')).
'channel'('b','type'('dotUnitType')).
'channel'('c','type'('dotUnitType')).
'channel'('d','type'('dotUnitType')).
'bindval'('T6','aParallel'('tupleExp'(['prefix'('src_span'(34,7,34,8,1,1),[],'a','prefix'('src_span'(34,10,34,11,1,1),[],'d','prefix'('src_span'(34,13,34,14,1,1),[],'b','prefix'('src_span'(34,16,34,17,1,1),[],'b','stop'('src_span'(34,19,34,23,4,4)),'src_span'(34,17,34,19,2,2)),'src_span'(34,14,34,16,2,2)),'src_span'(34,11,34,13,2,2)),'src_span'(34,8,34,10,2,2))]),'a','b','b','c','tupleExp'(['prefix'('src_span'(34,47,34,48,1,1),[],'b','prefix'('src_span'(34,50,34,51,1,1),[],'c','prefix'('src_span'(34,53,34,54,1,1),[],'b','stop'('src_span'(34,56,34,60,4,4)),'src_span'(34,54,34,56,2,2)),'src_span'(34,51,34,53,2,2)),'src_span'(34,48,34,50,2,2))]),'src_span'(34,24,34,28,4,4)),'src_span'(34,1,34,61,60,60)).
'channel'('gen','type'('dotTupleType'(['setExp'('int'(0),'int'(99))]))).
'channel'('stop','type'('dotTupleType'(['setExp'('int'(0),'int'(99))]))).
'bindval'('Test3','aParallel'('agent_call'('src_span'(40,9,40,15,6,6),'Gen',['int'(0)]),'gen','dotTuple'(['gen','int'(2)]),'dotTuple'(['gen','int'(3)]),'dotTuple'(['gen','int'(4)]),'stop','agent_call'('src_span'(40,59,40,65,6,6),'Gen',['int'(2)]),'src_span'(40,15,40,18,3,3)),'src_span'(40,1,40,65,64,64)).
'agent'('Gen'(_x5),'[]'('prefix'('src_span'(41,10,41,13,3,3),['out'(_x5)],'gen','agent_call'('src_span'(41,19,41,27,8,8),'Gen',[_x5,'int'(1)]),'src_span'(41,15,41,19,4,4)),'prefix'('src_span'(41,31,41,35,4,4),['out'(_x5)],'stop','skip'('src_span'(41,41,41,45,4,4)),'src_span'(41,37,41,41,4,4)),'src_span_operator'('no_loc_info_available','src_span'(41,27,41,31,4,4))),'src_span'(41,1,41,45,44,44)).
'symbol'('put','put','src_span'(1,9,1,12,3,3),'Channel').
'symbol'('Test3','Test3','src_span'(40,1,40,6,5,5),'Ident (Groundrep.)').
'symbol'('P','P','src_span'(5,1,5,2,1,1),'Ident (Groundrep.)').
'symbol'('Q','Q','src_span'(9,1,9,2,1,1),'Function or Process').
'symbol'('gen','gen','src_span'(39,9,39,12,3,3),'Channel').
'symbol'('R','R','src_span'(7,1,7,2,1,1),'Ident (Groundrep.)').
'symbol'('TRAINS','TRAINS','src_span'(15,10,15,16,6,6),'Datatype').
'symbol'('T','T','src_span'(19,1,19,2,1,1),'Ident (Groundrep.)').
'symbol'('MAIN','MAIN','src_span'(3,1,3,5,4,4),'Ident (Groundrep.)').
'symbol'('TG2','TG2','src_span'(20,1,20,4,3,3),'Ident (Groundrep.)').
'symbol'('T2','T2','src_span'(24,1,24,3,2,2),'Ident (Groundrep.)').
'symbol'('T3','T3','src_span'(26,1,26,3,2,2),'Ident (Groundrep.)').
'symbol'('R2','R2','src_span'(13,1,13,3,2,2),'Ident (Groundrep.)').
'symbol'('Thomas','Thomas','src_span'(15,19,15,25,6,6),'Constructor of Datatype').
'symbol'('Gordon','Gordon','src_span'(15,28,15,34,6,6),'Constructor of Datatype').
'symbol'('Gordon2','Gordon','src_span'(24,59,24,65,6,6),'Ident (Prolog Variable)').
'symbol'('T4','T4','src_span'(28,1,28,3,2,2),'Ident (Groundrep.)').
'symbol'('T5','T5','src_span'(30,1,30,3,2,2),'Function or Process').
'symbol'('a','a','src_span'(32,9,32,10,1,1),'Channel').
'symbol'('b','b','src_span'(32,11,32,12,1,1),'Channel').
'symbol'('T6','T6','src_span'(34,1,34,3,2,2),'Ident (Groundrep.)').
'symbol'('c','c','src_span'(32,13,32,14,1,1),'Channel').
'symbol'('d','d','src_span'(32,15,32,16,1,1),'Channel').
'symbol'('go','go','src_span'(17,9,17,11,2,2),'Channel').
'symbol'('Gen','Gen','src_span'(41,1,41,4,3,3),'Function or Process').
'symbol'('t','t','src_span'(19,8,19,9,1,1),'Ident (Prolog Variable)').
'symbol'('t2','t','src_span'(20,10,20,11,1,1),'Ident (Prolog Variable)').
'symbol'('meet','meet','src_span'(22,9,22,13,4,4),'Channel').
'symbol'('stop','stop','src_span'(39,13,39,17,4,4),'Channel').
'symbol'('put2','put2','src_span'(11,9,11,13,4,4),'Channel').
'symbol'('x','x','src_span'(9,3,9,4,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(26,39,26,40,1,1),'Ident (Prolog Variable)').
'symbol'('x3','x','src_span'(28,18,28,19,1,1),'Ident (Prolog Variable)').
'symbol'('x4','x','src_span'(30,4,30,5,1,1),'Ident (Prolog Variable)').
'symbol'('x5','x','src_span'(41,5,41,6,1,1),'Ident (Prolog Variable)').
'symbol'('y','y','src_span'(7,46,7,47,1,1),'Ident (Prolog Variable)').
'symbol'('y2','y','src_span'(13,53,13,54,1,1),'Ident (Prolog Variable)').
'symbol'('z','z','src_span'(7,18,7,19,1,1),'Ident (Prolog Variable)').
'symbol'('z2','z','src_span'(13,21,13,22,1,1),'Ident (Prolog Variable)').
'symbol'('t1','t1','src_span'(24,11,24,13,2,2),'Ident (Prolog Variable)').
'symbol'('t2','t2','src_span'(19,54,19,56,2,2),'Ident (Prolog Variable)').
'symbol'('t22','t2','src_span'(20,61,20,63,2,2),'Ident (Prolog Variable)').
'symbol'('t23','t2','src_span'(24,14,24,16,2,2),'Ident (Prolog Variable)').
'symbol'('t3','t3','src_span'(24,56,24,58,2,2),'Ident (Prolog Variable)').

