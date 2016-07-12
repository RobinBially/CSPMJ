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
'bindval'('P','prefix'('src_span'(6,5,6,10,5,5),['in'(_pair)],'left'','prefix'('src_span'(6,19,6,25,6,6),['out'(_pair)],'right'','skip'('src_span'(6,34,6,38,4,4)),'src_span'(6,30,6,34,4,4)),'src_span'(6,15,6,19,4,4)),'src_span'(6,1,6,38,37,37)).
'agent'('f'(_d2,_x2),'tupleExp'([_x2,_d2,_x2]),'int'(2),'src_span'(8,1,8,21,20,20)).
'channel'('left','type'('dotTupleType'(['typeTuple'(['setExp'('int'(0),'int'(1)),'setExp'('int'(1),'int'(5)),'setExp'('int'(1),'int'(2))])]))).
'channel'('right','type'('dotTupleType'(['typeTuple'(['setExp'('int'(0),'int'(1)),'setExp'('int'(1),'int'(5)),'setExp'('int'(1),'int'(2))])]))).
'bindval'('Q','prefix'('src_span'(13,5,13,9,4,4),['in'(_triple)],'left','prefix'('src_span'(13,20,13,25,5,5),['out'(_triple)],'right','skip'('src_span'(13,36,13,40,4,4)),'src_span'(13,32,13,36,4,4)),'src_span'(13,16,13,20,4,4)),'src_span'(13,1,13,40,39,39)).
'bindval'('MAIN',';'(';'('val_of'('P','src_span'(15,8,15,9,1,1)),'val_of'('Q','src_span'(15,10,15,11,1,1)),'src_span_operator'('no_loc_info_available','src_span'(15,9,15,10,1,1))),'val_of'('ITER','src_span'(15,12,15,16,4,4)),'src_span_operator'('no_loc_info_available','src_span'(15,11,15,12,1,1))),'src_span'(15,1,15,16,15,15)).
'symbol'('left'','left'','src_span'(2,9,2,14,5,5),'Channel').
'symbol'('d','d','src_span'(4,15,4,16,1,1),'Ident (Prolog Variable)').
'symbol'('d2','d','src_span'(8,3,8,4,1,1),'Ident (Prolog Variable)').
'symbol'('f','f','src_span'(8,1,8,2,1,1),'Function or Process').
'symbol'('right'','right'','src_span'(2,15,2,21,6,6),'Channel').
'symbol'('right','right','src_span'(12,14,12,19,5,5),'Channel').
'symbol'('pair','pair','src_span'(6,11,6,15,4,4),'Ident (Prolog Variable)').
'symbol'('P','P','src_span'(6,1,6,2,1,1),'Ident (Groundrep.)').
'symbol'('Q','Q','src_span'(13,1,13,2,1,1),'Ident (Groundrep.)').
'symbol'('triple','triple','src_span'(13,10,13,16,6,6),'Ident (Prolog Variable)').
'symbol'('left','left','src_span'(12,9,12,13,4,4),'Channel').
'symbol'('x','x','src_span'(4,17,4,18,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(8,5,8,6,1,1),'Ident (Prolog Variable)').
'symbol'('ITER','ITER','src_span'(4,1,4,5,4,4),'Ident (Groundrep.)').
'symbol'('MAIN','MAIN','src_span'(15,1,15,5,4,4),'Ident (Groundrep.)').

