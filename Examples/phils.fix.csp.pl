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
'dataTypeDef'('Move',['constructor'('Up'),'constructor'('Down')]).
'channel'('left','type'('dotTupleType'(['val_of'('Move','src_span'(7,23,7,27,4,4))]))).
'channel'('right','type'('dotTupleType'(['val_of'('Move','src_span'(7,23,7,27,4,4))]))).
'bindval'('FORK','[]'('prefix'('src_span'(9,8,9,15,7,7),[],'dotTuple'(['left','Up']),'prefix'('src_span'(9,17,9,26,9,9),[],'dotTuple'(['left','Down']),'val_of'('FORK','src_span'(9,28,9,32,4,4)),'src_span'(9,26,9,28,2,2)),'src_span'(9,15,9,17,2,2)),'prefix'('src_span'(9,36,9,44,8,8),[],'dotTuple'(['right','Up']),'prefix'('src_span'(9,46,9,56,10,10),[],'dotTuple'(['right','Down']),'val_of'('FORK','src_span'(9,58,9,62,4,4)),'src_span'(9,56,9,58,2,2)),'src_span'(9,44,9,46,2,2)),'src_span_operator'('no_loc_info_available','src_span'(9,32,9,36,4,4))),'src_span'(9,1,9,62,61,61)).
'bindval'('PHIL','prefix'('src_span'(11,8,11,15,7,7),[],'dotTuple'(['left','Up']),'prefix'('src_span'(11,17,11,25,8,8),[],'dotTuple'(['right','Up']),'prefix'('src_span'(11,27,11,36,9,9),[],'dotTuple'(['left','Down']),'prefix'('src_span'(11,38,11,48,10,10),[],'dotTuple'(['right','Down']),'val_of'('PHIL','src_span'(11,50,11,54,4,4)),'src_span'(11,48,11,50,2,2)),'src_span'(11,36,11,38,2,2)),'src_span'(11,25,11,27,2,2)),'src_span'(11,15,11,17,2,2)),'src_span'(11,1,11,54,53,53)).
'agent'('LPHILS'(_n),'let'(['agent'('L'('int'(0)),'lParallel'('linkList'(['link'('right','left')]),'val_of'('FORK','src_span'(16,7,16,11,4,4)),'val_of'('PHIL','src_span'(16,27,16,31,4,4)),'src_span'(16,11,16,13,2,2)),'src_span'(15,5,16,31,26,26)),'agent'('L'(_n2),'let'(['bindval'('HALF','agent_call'('src_span'(19,16,19,27,11,11),'LPHILS',[_n2,'int'(1)]),'src_span'(19,9,19,27,18,18))],'tupleExp'(['lParallel'('linkList'(['link'('right','left')]),'val_of'('HALF','src_span'(20,15,20,19,4,4)),'val_of'('HALF','src_span'(20,35,20,39,4,4)),'src_span'(20,19,20,21,2,2))])),'src_span'(17,5,20,40,35,35))],'agent_call'('src_span'(22,10,22,28,18,18),'normal',['agent_call'('src_span'(22,17,22,21,4,4),'L',[_n])])),'src_span'(13,1,22,28,27,27)).
.
'agent'('RPHILS'(_n3),'agent_call'('src_span'(27,3,27,12,9,9),'LPHILS',[_n3]),'left','right','right','left','src_span'(26,1,27,47,46,46)).
'agent'('PHILS'(_n4),'sharing'('agent_call'('src_span'(30,3,30,14,11,11),'LPHILS',[_n4,'int'(1)]),'left','right','agent_call'('src_span'(30,39,30,50,11,11),'RPHILS',[_n4,'int'(1)]),'src_span'(30,14,30,18,4,4)),'src_span'(29,1,30,50,49,49)).
'agent_call'('src_span'(34,8,34,16,8,8),'PHILS',['int'(1)]).
'agent_call'('src_span'(35,8,35,17,9,9),'PHILS',['int'(10)]).
'agent_call'('src_span'(36,8,36,18,10,10),'PHILS',['int'(100)]).
'agent_call'('src_span'(37,8,37,19,11,11),'PHILS',['int'(1000)]).
'symbol'('Down','Down','src_span'(5,22,5,26,4,4),'Constructor of Datatype').
'symbol'('FORK','FORK','src_span'(9,1,9,5,4,4),'Ident (Groundrep.)').
'symbol'('right','right','src_span'(7,15,7,20,5,5),'Channel').
'symbol'('L','L','src_span'(15,5,15,6,1,1),'Function or Process').
'symbol'('n','n','src_span'(13,8,13,9,1,1),'Ident (Prolog Variable)').
'symbol'('n2','n','src_span'(17,7,17,8,1,1),'Ident (Prolog Variable)').
'symbol'('n3','n','src_span'(26,8,26,9,1,1),'Ident (Prolog Variable)').
'symbol'('n4','n','src_span'(29,7,29,8,1,1),'Ident (Prolog Variable)').
'symbol'('RPHILS','RPHILS','src_span'(26,1,26,7,6,6),'Function or Process').
'symbol'('PHILS','PHILS','src_span'(29,1,29,6,5,5),'Function or Process').
'symbol'('HALF','HALF','src_span'(19,9,19,13,4,4),'Ident (Groundrep.)').
'symbol'('Move','Move','src_span'(5,10,5,14,4,4),'Datatype').
'symbol'('left','left','src_span'(7,9,7,13,4,4),'Channel').
'symbol'('LPHILS','LPHILS','src_span'(13,1,13,7,6,6),'Function or Process').
'symbol'('Up','Up','src_span'(5,17,5,19,2,2),'Constructor of Datatype').
'symbol'('PHIL','PHIL','src_span'(11,1,11,5,4,4),'Ident (Groundrep.)').

