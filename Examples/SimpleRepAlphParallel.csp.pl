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
'dataTypeDef'('phil',['constructor'('plato'),'constructor'('sokrates'),'constructor'('aristotle')]).
'bindval'('PHILNAMES','phil','src_span'(4,1,4,17,16,16)).
'bindval'('MAXN','int'(4),'src_span'(5,1,5,9,8,8)).
'bindval'('NRS','int'(0),'val_of'('MAXN','src_span'(6,11,6,15,4,4)),'src_span'(6,1,6,16,15,15)).
'channel'('sync','type'('dotTupleType'(['val_of'('phil','src_span'(8,15,8,19,4,4)),'val_of'('NRS','src_span'(8,20,8,23,3,3))]))).
'channel'('out','type'('dotTupleType'(['val_of'('phil','src_span'(9,14,9,18,4,4)),'val_of'('phil','src_span'(9,19,9,23,4,4)),'val_of'('NRS','src_span'(9,24,9,27,3,3))]))).
'bindval'('SYSTEM','procRepAParallel'(['comprehensionGenerator'('val_of'('PHILNAMES','src_span'(11,15,11,24,9,9)))],'pair'('sync','out','agent_call'('src_span'(11,43,11,52,9,9),'PHIL',['int'(0)])),'src_span'(11,13,11,24,11,11)),'src_span'(11,1,11,52,51,51)).
'bindval'('SYSTEMs','procRepAParallel'(['comprehensionGenerator'('plato','sokrates','aristotle')],'pair'('sync','out','agent_call'('src_span'(14,61,14,70,9,9),'PHIL',['int'(0)])),'src_span'(14,14,14,42,28,28)),'src_span'(14,1,14,70,69,69)).
'agent'('PHIL'(_i,_x),'[]'('prefix'('src_span'(17,13,17,17,4,4),['out'(_i),'out'(_x)],'sync','prefix'('src_span'(17,25,17,28,3,3),['out'(_i),'out'(_i),'out'(_x)],'out','agent_call'('src_span'(17,38,17,62,24,24),'PHIL',[_i,'tupleExp'([_x,'int'(1)]),'tupleExp'(['val_of'('MAXN','src_span'(17,54,17,58,4,4)),'int'(1)])]),'src_span'(17,34,17,38,4,4)),'src_span'(17,21,17,25,4,4)),'prefix'('src_span'(17,66,17,70,4,4),['in'(_other),'in'(_y)],'sync','prefix'('src_span'(17,82,17,85,3,3),['out'(_other),'out'(_other),'out'(_y)],'out','agent_call'('src_span'(17,103,17,112,9,9),'PHIL',[_i,_x]),'src_span'(17,99,17,103,4,4)),'src_span'(17,78,17,82,4,4)),'src_span_operator'('no_loc_info_available','src_span'(17,62,17,66,4,4))),'src_span'(17,1,17,112,111,111)).
'bindval'('P0','agent_call'('src_span'(19,6,19,19,13,13),'PHIL',['plato','int'(0)]),'src_span'(19,1,19,19,18,18)).
'bindval'('SYSTEM2','aParallel'('val_of'('P0','src_span'(20,11,20,13,2,2)),'dotTuple'(['sync','plato','int'(0)]),'dotTuple'(['sync','sokrates','int'(0)]),'dotTuple'(['sync','aristotle','int'(0)]),'sync','out','agent_call'('src_span'(20,85,20,101,16,16),'PHIL',['sokrates','int'(0)]),'src_span'(20,13,20,16,3,3)),'src_span'(20,1,20,101,100,100)).
'bindval'('SYSTEM3','aParallel'('val_of'('SYSTEM2','src_span'(21,11,21,18,7,7)),'sync','out','sync','out','agent_call'('src_span'(21,53,21,70,17,17),'PHIL',['aristotle','int'(0)]),'src_span'(21,18,21,21,3,3)),'src_span'(21,1,21,70,69,69)).
'bindval'('TEST','tupleExp'(['[]'('prefix'('src_span'(23,9,23,13,4,4),['out'('plato'),'out'('int'(1))],'sync','stop'('src_span'(23,25,23,29,4,4)),'src_span'(23,21,23,25,4,4)),'prefix'('src_span'(23,33,23,36,3,3),['out'('plato'),'out'('plato'),'out'('int'(2))],'out','stop'('src_span'(23,54,23,58,4,4)),'src_span'(23,50,23,54,4,4)),'src_span_operator'('no_loc_info_available','src_span'(23,29,23,33,4,4)))]),'src_span'(23,1,23,59,58,58)).
'bindval'('TESTPar','aParallel'('val_of'('TEST','src_span'(24,11,24,15,4,4)),'sync','sync','val_of'('TEST','src_span'(24,47,24,51,4,4)),'src_span'(24,15,24,19,4,4)),'src_span'(24,1,24,51,50,50)).
'bindval'('MAIN','val_of'('SYSTEM','src_span'(26,8,26,14,6,6)),'src_span'(26,1,26,14,13,13)).
'symbol'('SYSTEM','SYSTEM','src_span'(11,1,11,7,6,6),'Ident (Groundrep.)').
'symbol'('P0','P0','src_span'(19,1,19,3,2,2),'Ident (Groundrep.)').
'symbol'('sokrates','sokrates','src_span'(2,25,2,33,8,8),'Constructor of Datatype').
'symbol'('aristotle','aristotle','src_span'(2,36,2,45,9,9),'Constructor of Datatype').
'symbol'('other','other','src_span'(17,71,17,76,5,5),'Ident (Prolog Variable)').
'symbol'('MAXN','MAXN','src_span'(5,1,5,5,4,4),'Ident (Groundrep.)').
'symbol'('i','i','src_span'(17,6,17,7,1,1),'Ident (Prolog Variable)').
'symbol'('sync','sync','src_span'(8,9,8,13,4,4),'Channel').
'symbol'('TESTPar','TESTPar','src_span'(24,1,24,8,7,7),'Ident (Groundrep.)').
'symbol'('NRS','NRS','src_span'(6,1,6,4,3,3),'Ident (Groundrep.)').
'symbol'('phil','phil','src_span'(2,10,2,14,4,4),'Datatype').
'symbol'('out','out','src_span'(9,9,9,12,3,3),'Channel').
'symbol'('PHILNAMES','PHILNAMES','src_span'(4,1,4,10,9,9),'Ident (Groundrep.)').
'symbol'('TEST','TEST','src_span'(23,1,23,5,4,4),'Ident (Groundrep.)').
'symbol'('x','x','src_span'(17,8,17,9,1,1),'Ident (Prolog Variable)').
'symbol'('SYSTEMs','SYSTEMs','src_span'(14,1,14,8,7,7),'Ident (Groundrep.)').
'symbol'('y','y','src_span'(17,77,17,78,1,1),'Ident (Prolog Variable)').
'symbol'('SYSTEM3','SYSTEM3','src_span'(21,1,21,8,7,7),'Ident (Groundrep.)').
'symbol'('MAIN','MAIN','src_span'(26,1,26,5,4,4),'Ident (Groundrep.)').
'symbol'('plato','plato','src_span'(2,17,2,22,5,5),'Constructor of Datatype').
'symbol'('PHIL','PHIL','src_span'(17,1,17,5,4,4),'Function or Process').
'symbol'('SYSTEM2','SYSTEM2','src_span'(20,1,20,8,7,7),'Ident (Groundrep.)').

