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
'channel'('out','type'('dotTupleType'(['setExp'('int'(0),'int'(999999))]))).
'agent'('McCarthy'(_n),'ifte'(_n,'int'(100),_n,'int'(10),'agent_call'('src_span'(8,19,8,43,24,24),'McCarthy',['agent_call'('src_span'(8,28,8,42,14,14),'McCarthy',[_n,'int'(11)])]),'src_span'(5,15,5,17,2,2),'src_span'(5,23,5,48,25,25),'src_span'(6,23,6,64,41,41)),'src_span'(5,1,8,43,42,42)).
'agent'('Test'(_n2,_m),'ifte'(_n2,_m,'prefix'('src_span'(13,25,13,28,3,3),['out'('agent_call'('src_span'(13,29,13,40,11,11),'McCarthy',['src_span'(13,38,13,39,1,1),[],_n2]))],'out','agent_call'('src_span'(13,44,13,55,11,11),'Test',[_n2,'int'(1),_m]),'src_span'(13,40,13,44,4,4)),'stop'('src_span'(13,61,13,65,4,4)),'src_span'(13,13,13,15,2,2),'src_span'(13,19,13,25,6,6),'src_span'(13,55,13,61,6,6)),'src_span'(13,1,13,65,64,64)).
'bindval'('MAIN','agent_call'('src_span'(15,8,15,21,13,13),'Test',['int'(0),'int'(10000)]),'src_span'(15,1,15,21,20,20)).
'symbol'('McCarthy','McCarthy','src_span'(5,1,5,9,8,8),'Function or Process').
'symbol'('Test','Test','src_span'(13,1,13,5,4,4),'Function or Process').
'symbol'('MAIN','MAIN','src_span'(15,1,15,5,4,4),'Ident (Groundrep.)').
'symbol'('m','m','src_span'(13,8,13,9,1,1),'Ident (Prolog Variable)').
'symbol'('n','n','src_span'(5,10,5,11,1,1),'Ident (Prolog Variable)').
'symbol'('n2','n','src_span'(13,6,13,7,1,1),'Ident (Prolog Variable)').
'symbol'('out','out','src_span'(3,9,3,12,3,3),'Channel').

