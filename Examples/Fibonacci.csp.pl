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
'agent'('fib'(_n),'ifte'(_n,'int'(2),'int'(1),'agent_call'('src_span'(5,11,5,19,8,8),'fib',[_n,'int'(1)]),'agent_call'('src_span'(5,20,5,28,8,8),'fib',[_n,'int'(2)]),'src_span'(2,10,2,12,2,2),'src_span'(2,16,2,35,19,19),'src_span'(3,14,3,41,27,27)),'src_span'(2,1,5,28,27,27)).
'channel'('out','type'('dotTupleType'(['setExp'('int'(0),'int'(999999))]))).
'channel'('out2','type'('dotTupleType'(['setExp'('int'(0))]))).
'bindval'('MAIN','prefix'('src_span'(10,9,10,12,3,3),['out'('agent_call'('src_span'(10,13,10,19,6,6),'fib',['src_span'(10,17,10,18,1,1),[],'int'(2)]))],'out','prefix'('src_span'(10,23,10,26,3,3),['out'('agent_call'('src_span'(10,27,10,34,7,7),'fib',['src_span'(10,31,10,33,2,2),[],'int'(10)]))],'out','prefix'('src_span'(10,38,10,41,3,3),['out'('agent_call'('src_span'(10,42,10,49,7,7),'fib',['src_span'(10,46,10,48,2,2),[],'int'(20)]))],'out','prefix'('src_span'(10,53,10,56,3,3),['out'('agent_call'('src_span'(10,57,10,64,7,7),'fib',['src_span'(10,61,10,63,2,2),[],'int'(25)]))],'out','stop'('src_span'(10,68,10,72,4,4)),'src_span'(10,64,10,68,4,4)),'src_span'(10,49,10,53,4,4)),'src_span'(10,34,10,38,4,4)),'src_span'(10,19,10,23,4,4)),'src_span'(10,1,10,72,71,71)).
'symbol'('out2','out2','src_span'(8,9,8,13,4,4),'Channel').
'symbol'('MAIN','MAIN','src_span'(10,1,10,5,4,4),'Ident (Groundrep.)').
'symbol'('fib','fib','src_span'(2,1,2,4,3,3),'Function or Process').
'symbol'('n','n','src_span'(2,5,2,6,1,1),'Ident (Prolog Variable)').
'symbol'('out','out','src_span'(7,9,7,12,3,3),'Channel').

