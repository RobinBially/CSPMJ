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
'channel'('left'','type'('dotTupleType'(['typeTuple'(['setExp'('int'(1),'int'(20)),'setExp'('int'(1),'int'(10))])]))).
'channel'('right'','type'('dotTupleType'(['typeTuple'(['setExp'('int'(1),'int'(20)),'setExp'('int'(1),'int'(10))])]))).
'bindval'('ITER','prefix'('src_span'(4,8,4,13,5,5),['in'(_d,_x)],'left'','prefix'('src_span'(4,23,4,29,6,6),['out'('tupleExp'(['src_span'(4,31,4,32,1,1),[],_d,'src_span'(4,33,4,39,6,6),[],'agent_call'('src_span'(4,33,4,39,6,6),'f',['src_span'(4,35,4,36,1,1),[],_d,'src_span'(4,37,4,38,1,1),[],_x])]))],'right'','val_of'('ITER','src_span'(4,44,4,48,4,4)),'src_span'(4,40,4,44,4,4)),'src_span'(4,19,4,23,4,4)),'src_span'(4,1,4,48,47,47)).
'bindval'('ITER2','prefix'('src_span'(5,9,5,14,5,5),['in'(_d2,'int'(5))],'left'','prefix'('src_span'(5,24,5,30,6,6),['out'('tupleExp'(['src_span'(5,32,5,33,1,1),[],_d2,'src_span'(5,34,5,40,6,6),[],'agent_call'('src_span'(5,34,5,40,6,6),'f',['src_span'(5,36,5,37,1,1),[],_d2,'src_span'(5,38,5,39,1,1),[],'int'(5)])]))],'right'','skip'('src_span'(5,45,5,49,4,4)),'src_span'(5,41,5,45,4,4)),'src_span'(5,20,5,24,4,4)),'src_span'(5,1,5,49,48,48)).
'bindval'('P','prefix'('src_span'(7,5,7,10,5,5),['in'(_pair)],'left'','prefix'('src_span'(7,19,7,25,6,6),['out'(_pair)],'right'','skip'('src_span'(7,34,7,38,4,4)),'src_span'(7,30,7,34,4,4)),'src_span'(7,15,7,19,4,4)),'src_span'(7,1,7,38,37,37)).
'agent'('f'(_d3,_x2),'tupleExp'([_x2,_d3,_x2]),'int'(2),'src_span'(9,1,9,21,20,20)).
'dataTypeDef'('COL',['constructor'('red'),'constructor'('green')]).
'channel'('left','type'('dotTupleType'(['typeTuple'(['setExp'('int'(0),'int'(1)),'setExp'('int'(1),'int'(5)),'setExp'('int'(1),'int'(2)),'val_of'('COL','src_span'(13,42,13,45,3,3))])]))).
'channel'('right','type'('dotTupleType'(['typeTuple'(['setExp'('int'(0),'int'(1)),'setExp'('int'(1),'int'(5)),'setExp'('int'(1),'int'(2)),'val_of'('COL','src_span'(13,42,13,45,3,3))])]))).
'bindval'('Q','prefix'('src_span'(14,5,14,9,4,4),['in'(_x3,'int'(2),_y,_red2)],'left','prefix'('src_span'(14,25,14,30,5,5),['out'('tupleExp'(['src_span'(14,32,14,33,1,1),[],_x3,'src_span'(14,34,14,37,3,3),[],_x3,_y,'src_span'(14,38,14,39,1,1),[],_y,'src_span'(14,40,14,45,5,5),[],'green']))],'right','val_of'('Q','src_span'(14,50,14,51,1,1)),'src_span'(14,46,14,50,4,4)),'src_span'(14,21,14,25,4,4)),'src_span'(14,1,14,51,50,50)).
'bindval'('MAIN',';'('val_of'('ITER2','src_span'(19,8,19,13,5,5)),'val_of'('Q','src_span'(19,14,19,15,1,1)),'src_span_operator'('no_loc_info_available','src_span'(19,13,19,14,1,1))),'src_span'(19,1,19,15,14,14)).
'symbol'('left'','left'','src_span'(2,9,2,14,5,5),'Channel').
'symbol'('COL','COL','src_span'(11,10,11,13,3,3),'Datatype').
'symbol'('green','green','src_span'(11,22,11,27,5,5),'Constructor of Datatype').
'symbol'('d','d','src_span'(4,15,4,16,1,1),'Ident (Prolog Variable)').
'symbol'('d2','d','src_span'(5,16,5,17,1,1),'Ident (Prolog Variable)').
'symbol'('d3','d','src_span'(9,3,9,4,1,1),'Ident (Prolog Variable)').
'symbol'('f','f','src_span'(9,1,9,2,1,1),'Function or Process').
'symbol'('right'','right'','src_span'(2,15,2,21,6,6),'Channel').
'symbol'('right','right','src_span'(13,14,13,19,5,5),'Channel').
'symbol'('pair','pair','src_span'(7,11,7,15,4,4),'Ident (Prolog Variable)').
'symbol'('P','P','src_span'(7,1,7,2,1,1),'Ident (Groundrep.)').
'symbol'('red','red','src_span'(11,16,11,19,3,3),'Constructor of Datatype').
'symbol'('red2','red','src_span'(14,17,14,20,3,3),'Ident (Prolog Variable)').
'symbol'('ITER2','ITER2','src_span'(5,1,5,6,5,5),'Ident (Groundrep.)').
'symbol'('Q','Q','src_span'(14,1,14,2,1,1),'Ident (Groundrep.)').
'symbol'('left','left','src_span'(13,9,13,13,4,4),'Channel').
'symbol'('x','x','src_span'(4,17,4,18,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(9,5,9,6,1,1),'Ident (Prolog Variable)').
'symbol'('x3','x','src_span'(14,11,14,12,1,1),'Ident (Prolog Variable)').
'symbol'('y','y','src_span'(14,15,14,16,1,1),'Ident (Prolog Variable)').
'symbol'('ITER','ITER','src_span'(4,1,4,5,4,4),'Ident (Groundrep.)').
'symbol'('MAIN','MAIN','src_span'(19,1,19,5,4,4),'Ident (Groundrep.)').

