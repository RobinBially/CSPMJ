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
'dataTypeDef'('COL',['constructor'('red'),'constructor'('green')]).
'channel'('ap','type'('dotTupleType'(['typeTuple'(['setExp'('int'(0),'int'(3)),'val_of'('COL','src_span'(2,21,2,24,3,3))])]))).
'agent'('f'('int'(0),_red2),'int'(1),'src_span'(5,1,5,15,14,14)).
'agent'('f'('int'(0),_green2),'int'(2),'src_span'(6,1,6,17,16,16)).
'agent'('f'(_x,_red3),'agent_call'('src_span'(7,14,7,28,14,14),'f',['tupleExp'([_x,'int'(1),'green'])]),'src_span'(7,1,7,28,27,27)).
'agent'('f'(_x2,_green3),'agent_call'('src_span'(8,16,8,28,12,12),'f',['tupleExp'([_x2,'int'(1),'red'])]),'src_span'(8,1,8,28,27,27)).
'bindval'('MAIN','prefix'('src_span'(10,8,10,10,2,2),['in'(_pair)],'ap','prefix'('src_span'(10,19,10,21,2,2),['out'('tupleExp'(['src_span'(10,23,10,30,7,7),[],'agent_call'('src_span'(10,23,10,30,7,7),'f',['src_span'(10,25,10,29,4,4),[],_pair]),'src_span'(10,31,10,34,3,3),[],'red']))],'ap','val_of'('MAIN','src_span'(10,39,10,43,4,4)),'src_span'(10,35,10,39,4,4)),'src_span'(10,15,10,19,4,4)),'src_span'(10,1,10,43,42,42)).
'symbol'('red','red','src_span'(1,16,1,19,3,3),'Constructor of Datatype').
'symbol'('red2','red','src_span'(5,6,5,9,3,3),'Ident (Prolog Variable)').
'symbol'('red3','red','src_span'(7,6,7,9,3,3),'Ident (Prolog Variable)').
'symbol'('COL','COL','src_span'(1,10,1,13,3,3),'Datatype').
'symbol'('green','green','src_span'(1,22,1,27,5,5),'Constructor of Datatype').
'symbol'('green2','green','src_span'(6,6,6,11,5,5),'Ident (Prolog Variable)').
'symbol'('green3','green','src_span'(8,6,8,11,5,5),'Ident (Prolog Variable)').
'symbol'('f','f','src_span'(5,1,5,2,1,1),'Function or Process').
'symbol'('x','x','src_span'(7,4,7,5,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(8,4,8,5,1,1),'Ident (Prolog Variable)').
'symbol'('MAIN','MAIN','src_span'(10,1,10,5,4,4),'Ident (Groundrep.)').
'symbol'('pair','pair','src_span'(10,11,10,15,4,4),'Ident (Prolog Variable)').
'symbol'('ap','ap','src_span'(2,9,2,11,2,2),'Channel').

