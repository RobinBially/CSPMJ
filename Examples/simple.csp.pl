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
'dataTypeDef'('FRUIT',['constructor'('apples'),'constructor'('oranges'),'constructor'('pears')]).
'channel'('left','type'('dotTupleType'(['val_of'('FRUIT','src_span'(10,26,10,31,5,5))]))).
'channel'('right','type'('dotTupleType'(['val_of'('FRUIT','src_span'(10,26,10,31,5,5))]))).
'channel'('mid','type'('dotTupleType'(['val_of'('FRUIT','src_span'(10,26,10,31,5,5))]))).
'channel'('ack','type'('dotUnitType')).
'bindval'('COPY','prefix'('src_span'(14,8,14,12,4,4),['in'(_x)],'left','prefix'('src_span'(14,20,14,25,5,5),['out'(_x)],'right','val_of'('COPY','src_span'(14,33,14,37,4,4)),'src_span'(14,29,14,33,4,4)),'src_span'(14,16,14,20,4,4)),'src_span'(14,1,14,37,36,36)).
'bindval'('SEND','prefix'('src_span'(18,8,18,12,4,4),['in'(_x2)],'left','prefix'('src_span'(18,20,18,23,3,3),['out'(_x2)],'mid','prefix'('src_span'(18,31,18,34,3,3),[],'ack','val_of'('SEND','src_span'(18,38,18,42,4,4)),'src_span'(18,34,18,38,4,4)),'src_span'(18,27,18,31,4,4)),'src_span'(18,16,18,20,4,4)),'src_span'(18,1,18,42,41,41)).
'bindval'('REC','prefix'('src_span'(19,7,19,10,3,3),['in'(_x3)],'mid','prefix'('src_span'(19,18,19,23,5,5),['out'(_x3)],'right','prefix'('src_span'(19,31,19,34,3,3),[],'ack','val_of'('REC','src_span'(19,38,19,41,3,3)),'src_span'(19,34,19,38,4,4)),'src_span'(19,27,19,31,4,4)),'src_span'(19,14,19,18,4,4)),'src_span'(19,1,19,41,40,40)).
'bindval'('SYSTEM','\'('tupleExp'(['sharing'('val_of'('SEND','src_span'(22,11,22,15,4,4)),'mid','ack','val_of'('REC','src_span'(22,37,22,40,3,3)),'src_span'(22,15,22,19,4,4))]),'mid','ack','src_span_operator'('no_loc_info_available','src_span'(22,41,22,44,3,3))),'src_span'(22,1,22,58,57,57)).
'val_of'('COPY','src_span'(27,8,27,12,4,4)),'val_of'('SYSTEM','src_span'(27,18,27,24,6,6)).
'val_of'('SYSTEM','src_span'(31,8,31,14,6,6)),'val_of'('COPY','src_span'(31,20,31,24,4,4)).
'symbol'('SYSTEM','SYSTEM','src_span'(22,1,22,7,6,6),'Ident (Groundrep.)').
'symbol'('oranges','oranges','src_span'(7,27,7,34,7,7),'Constructor of Datatype').
'symbol'('ack','ack','src_span'(11,9,11,12,3,3),'Channel').
'symbol'('mid','mid','src_span'(10,20,10,23,3,3),'Channel').
'symbol'('COPY','COPY','src_span'(14,1,14,5,4,4),'Ident (Groundrep.)').
'symbol'('right','right','src_span'(10,14,10,19,5,5),'Channel').
'symbol'('SEND','SEND','src_span'(18,1,18,5,4,4),'Ident (Groundrep.)').
'symbol'('REC','REC','src_span'(19,1,19,4,3,3),'Ident (Groundrep.)').
'symbol'('FRUIT','FRUIT','src_span'(7,10,7,15,5,5),'Datatype').
'symbol'('pears','pears','src_span'(7,37,7,42,5,5),'Constructor of Datatype').
'symbol'('left','left','src_span'(10,9,10,13,4,4),'Channel').
'symbol'('x','x','src_span'(14,15,14,16,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(18,15,18,16,1,1),'Ident (Prolog Variable)').
'symbol'('x3','x','src_span'(19,13,19,14,1,1),'Ident (Prolog Variable)').
'symbol'('apples','apples','src_span'(7,18,7,24,6,6),'Constructor of Datatype').

