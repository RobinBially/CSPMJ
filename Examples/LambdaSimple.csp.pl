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
'agent'('f1'(_x,_y,_z),_x,_y,_z,'src_span'(2,1,2,18,17,17)).
'bindval'('f2','lambda'([_x2,_y2,_z2],_x2,_y2,_z2),'src_span'(4,1,4,21,20,20)).
'channel'('c','type'('dotTupleType'(['setExp'('int'(0),'int'(3))]))).
'channel'('d','type'('dotTupleType'(['setExp'('int'(0),'int'(99))]))).
'bindval'('MAIN','prefix'('src_span'(9,8,9,9,1,1),['in'(_v)],'c','prefix'('src_span'(9,15,9,16,1,1),['in'(_w)],'c','prefix'('src_span'(9,22,9,23,1,1),['out'('agent_call'('src_span'(9,24,9,33,9,9),'f1',['src_span'(9,27,9,28,1,1),[],_v,'src_span'(9,29,9,30,1,1),[],_w,'src_span'(9,31,9,32,1,1),[],_w]))],'d','prefix'('src_span'(9,37,9,38,1,1),['out'('agent_call'('src_span'(9,39,9,48,9,9),'f2',['src_span'(9,42,9,43,1,1),[],_v,'src_span'(9,44,9,45,1,1),[],_w,'src_span'(9,46,9,47,1,1),[],_w]))],'d','val_of'('MAIN','src_span'(10,7,10,11,4,4)),'src_span'(9,48,9,57,9,9)),'src_span'(9,33,9,37,4,4)),'src_span'(9,18,9,22,4,4)),'src_span'(9,11,9,15,4,4)),'src_span'(9,1,10,11,10,10)).
'symbol'('c','c','src_span'(6,9,6,10,1,1),'Channel').
'symbol'('d','d','src_span'(7,9,7,10,1,1),'Channel').
'symbol'('v','v','src_span'(9,10,9,11,1,1),'Ident (Prolog Variable)').
'symbol'('w','w','src_span'(9,17,9,18,1,1),'Ident (Prolog Variable)').
'symbol'('x','x','src_span'(2,4,2,5,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(4,8,4,9,1,1),'Ident (Prolog Variable)').
'symbol'('y','y','src_span'(2,6,2,7,1,1),'Ident (Prolog Variable)').
'symbol'('y2','y','src_span'(4,10,4,11,1,1),'Ident (Prolog Variable)').
'symbol'('z','z','src_span'(2,8,2,9,1,1),'Ident (Prolog Variable)').
'symbol'('z2','z','src_span'(4,12,4,13,1,1),'Ident (Prolog Variable)').
'symbol'('MAIN','MAIN','src_span'(9,1,9,5,4,4),'Ident (Groundrep.)').
'symbol'('f1','f1','src_span'(2,1,2,3,2,2),'Function or Process').
'symbol'('f2','f2','src_span'(4,1,4,3,2,2),'Ident (Groundrep.)').

