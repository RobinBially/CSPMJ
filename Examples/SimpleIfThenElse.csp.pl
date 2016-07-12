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
'channel'('gen','type'('dotTupleType'(['setExp'('int'(0),'int'(9))]))).
'channel'('stop','type'('dotTupleType'(['setExp'('int'(0),'int'(9))]))).
'channel'('finish','type'('dotTupleType'(['setExp'('int'(0),'int'(9))]))).
'bindval'('MAIN',';'('agent_call'('src_span'(7,8,7,14,6,6),'GEN',['int'(0)]),'agent_call'('src_span'(7,17,7,23,6,6),'GEN',['int'(0)]),'src_span_operator'('no_loc_info_available','src_span'(7,14,7,17,3,3))),'src_span'(7,1,7,23,22,22)).
'agent'('GEN'(_x),'ifte'(_x,'int'(8),'tupleExp'([_x,'int'(0)]),'true','[]'('ifte'(_x,'int'(2),_x,'int'(4),'prefix'('src_span'(11,16,11,19,3,3),['out'(_x)],'gen','agent_call'('src_span'(11,25,11,33,8,8),'GEN',[_x,'int'(2)]),'src_span'(11,21,11,25,4,4)),'prefix'('src_span'(13,16,13,19,3,3),['out'(_x)],'gen','agent_call'('src_span'(13,25,13,33,8,8),'GEN',[_x,'int'(1)]),'src_span'(13,21,13,25,4,4)),'src_span'(10,13,10,15,2,2),'src_span'(10,28,10,50,22,22),'src_span'(11,33,11,68,35,35)),'prefix'('src_span'(13,37,13,41,4,4),['out'(_x)],'stop','skip'('src_span'(13,47,13,51,4,4)),'src_span'(13,43,13,47,4,4)),'src_span_operator'('no_loc_info_available','src_span'(13,33,13,37,4,4))),'prefix'('src_span'(15,13,15,19,6,6),['out'(_x)],'finish','skip'('src_span'(15,25,15,29,4,4)),'src_span'(15,21,15,25,4,4)),'src_span'(9,10,9,12,2,2),'src_span'(9,38,9,57,19,19),'src_span'(13,51,13,80,29,29)),'src_span'(9,1,15,29,28,28)).
'bindval'('Test','agent_call'('src_span'(17,8,17,12,4,4),'T',['int'(1)]),'src_span'(17,1,17,12,11,11)).
'agent'('T'(_y),'&'(_y,'int'(4),'prefix'('src_span'(18,15,18,18,3,3),['out'(_y)],'gen','agent_call'('src_span'(18,24,18,30,6,6),'T',[_y,'int'(1)]),'src_span'(18,20,18,24,4,4))),'src_span'(18,1,18,30,29,29)).
'bindval'('TRACE',';'('prefix'('src_span'(20,9,20,14,5,5),[],'dotTuple'(['gen','int'(0)]),'prefix'('src_span'(20,18,20,23,5,5),[],'dotTuple'(['gen','int'(1)]),'prefix'('src_span'(20,27,20,32,5,5),[],'dotTuple'(['gen','int'(2)]),'prefix'('src_span'(20,36,20,41,5,5),[],'dotTuple'(['gen','int'(4)]),'prefix'('src_span'(20,45,20,50,5,5),[],'dotTuple'(['gen','int'(6)]),'prefix'('src_span'(20,54,20,59,5,5),[],'dotTuple'(['gen','int'(7)]),'prefix'('src_span'(20,63,20,71,8,8),[],'dotTuple'(['finish','int'(8)]),'prefix'('src_span'(20,75,20,80,5,5),[],'dotTuple'(['gen','int'(0)]),'prefix'('src_span'(20,84,20,89,5,5),[],'dotTuple'(['gen','int'(1)]),'prefix'('src_span'(20,93,20,98,5,5),[],'dotTuple'(['gen','int'(2)]),'prefix'('src_span'(20,102,20,107,5,5),[],'dotTuple'(['gen','int'(4)]),'prefix'('src_span'(20,111,20,117,6,6),[],'dotTuple'(['stop','int'(6)]),'skip'('src_span'(20,121,20,125,4,4)),'src_span'(20,117,20,121,4,4)),'src_span'(20,107,20,111,4,4)),'src_span'(20,98,20,102,4,4)),'src_span'(20,89,20,93,4,4)),'src_span'(20,80,20,84,4,4)),'src_span'(20,71,20,75,4,4)),'src_span'(20,59,20,63,4,4)),'src_span'(20,50,20,54,4,4)),'src_span'(20,41,20,45,4,4)),'src_span'(20,32,20,36,4,4)),'src_span'(20,23,20,27,4,4)),'src_span'(20,14,20,18,4,4)),'stop'('src_span'(20,128,20,132,4,4)),'src_span_operator'('no_loc_info_available','src_span'(20,125,20,128,3,3))),'src_span'(20,1,20,132,131,131)).
'val_of'('MAIN','src_span'(22,8,22,12,4,4)),'val_of'('TRACE','src_span'(22,17,22,22,5,5)).
'symbol'('TRACE','TRACE','src_span'(20,1,20,6,5,5),'Ident (Groundrep.)').
'symbol'('gen','gen','src_span'(3,9,3,12,3,3),'Channel').
'symbol'('GEN','GEN','src_span'(9,1,9,4,3,3),'Function or Process').
'symbol'('stop','stop','src_span'(4,9,4,13,4,4),'Channel').
'symbol'('T','T','src_span'(18,1,18,2,1,1),'Function or Process').
'symbol'('Test','Test','src_span'(17,1,17,5,4,4),'Ident (Groundrep.)').
'symbol'('x','x','src_span'(9,5,9,6,1,1),'Ident (Prolog Variable)').
'symbol'('y','y','src_span'(18,3,18,4,1,1),'Ident (Prolog Variable)').
'symbol'('finish','finish','src_span'(5,9,5,15,6,6),'Channel').
'symbol'('MAIN','MAIN','src_span'(7,1,7,5,4,4),'Ident (Groundrep.)').

