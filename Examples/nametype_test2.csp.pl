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
'nameType'('Val1','type'('dotTupleType'(['setExp'('int'(1),'int'(3))]))).
'nameType'('Val2','type'('dotTupleType'(['setExp'('int'(8),'int'(9))]))).
'nameType'('Val3','type'('dotTupleType'(['setExp'('int'(0),'int'(1))]))).
'bindval'('VVal1','int'(4),'int'(4),'src_span'(8,1,8,15,14,14)).
'nameType'('TESTT1','type'('dotTupleType'(['typeTuple'(['val_of'('Val1','src_span'(9,20,9,24,4,4)),'val_of'('VVal1','src_span'(9,25,9,30,5,5))])]))).
'nameType'('TESTT2','type'('dotTupleType'(['val_of'('Val3','src_span'(10,19,10,23,4,4)),'val_of'('VVal1','src_span'(10,24,10,29,5,5))]))).
'channel'('testt1','type'('dotTupleType'(['typeTuple'(['val_of'('TESTT1','src_span'(11,19,11,25,6,6)),'val_of'('TESTT2','src_span'(11,26,11,32,6,6))])]))).
'channel'('out','type'('dotTupleType'(['setExp'('int'(0),'int'(9))]))).
'bindval'('MAIN','prefix'('src_span'(13,8,13,14,6,6),['in'(_x)],'testt1','agent_call'('src_span'(13,20,13,29,9,9),'treat1',[_x]),'src_span'(13,16,13,20,4,4)),'src_span'(13,1,13,29,28,28)).
'agent'('treat1'(_v1,_vv1,'dotpat'([_v3,_vv1b])),'prefix'('src_span'(14,38,14,41,3,3),['out'(_v1)],'out','prefix'('src_span'(14,48,14,51,3,3),['out'(_vv1)],'out','prefix'('src_span'(14,59,14,62,3,3),['out'(_v3)],'out','prefix'('src_span'(14,69,14,72,3,3),['out'(_vv1b)],'out','skip'('src_span'(14,81,14,85,4,4)),'src_span'(14,77,14,81,4,4)),'src_span'(14,65,14,69,4,4)),'src_span'(14,55,14,59,4,4)),'src_span'(14,44,14,48,4,4)),'src_span'(14,5,14,85,80,80)).
'symbol'('VVal1','VVal1','src_span'(8,1,8,6,5,5),'Ident (Groundrep.)').
'symbol'('TESTT1','TESTT1','src_span'(9,10,9,16,6,6),'Nametype').
'symbol'('vv1b','vv1b','src_span'(14,27,14,31,4,4),'Ident (Prolog Variable)').
'symbol'('out','out','src_span'(12,9,12,12,3,3),'Channel').
'symbol'('vv1','vv1','src_span'(14,18,14,21,3,3),'Ident (Prolog Variable)').
'symbol'('Val1','Val1','src_span'(4,10,4,14,4,4),'Nametype').
'symbol'('testt1','testt1','src_span'(11,9,11,15,6,6),'Channel').
'symbol'('x','x','src_span'(13,15,13,16,1,1),'Ident (Prolog Variable)').
'symbol'('Val2','Val2','src_span'(5,10,5,14,4,4),'Nametype').
'symbol'('Val3','Val3','src_span'(6,10,6,14,4,4),'Nametype').
'symbol'('TESTT2','TESTT2','src_span'(10,10,10,16,6,6),'Nametype').
'symbol'('MAIN','MAIN','src_span'(13,1,13,5,4,4),'Ident (Groundrep.)').
'symbol'('v1','v1','src_span'(14,15,14,17,2,2),'Ident (Prolog Variable)').
'symbol'('v3','v3','src_span'(14,24,14,26,2,2),'Ident (Prolog Variable)').
'symbol'('treat1','treat1','src_span'(14,5,14,11,6,6),'Function or Process').

