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
'channel'('left','type'('dotTupleType'(['val_of'('FRUIT','src_span'(6,25,6,30,5,5))]))).
'channel'('right','type'('dotTupleType'(['val_of'('FRUIT','src_span'(6,25,6,30,5,5))]))).
'channel'('mid','type'('dotTupleType'(['val_of'('FRUIT','src_span'(6,25,6,30,5,5))]))).
'channel'('ack','type'('dotUnitType')).
'bindval'('SEND','prefix'('src_span'(9,8,9,12,4,4),['in'(_x)],'left','prefix'('src_span'(9,20,9,23,3,3),['out'(_x)],'mid','prefix'('src_span'(9,31,9,34,3,3),[],'ack','val_of'('SEND','src_span'(9,38,9,42,4,4)),'src_span'(9,34,9,38,4,4)),'src_span'(9,27,9,31,4,4)),'src_span'(9,16,9,20,4,4)),'src_span'(9,1,9,42,41,41)).
'bindval'('REC','prefix'('src_span'(11,7,11,10,3,3),['in'(_x2)],'mid','prefix'('src_span'(11,18,11,23,5,5),['out'(_x2)],'right','prefix'('src_span'(11,31,11,34,3,3),[],'ack','val_of'('REC','src_span'(11,38,11,41,3,3)),'src_span'(11,34,11,38,4,4)),'src_span'(11,27,11,31,4,4)),'src_span'(11,14,11,18,4,4)),'src_span'(11,1,11,41,40,40)).
'bindval'('SYSTEM','\'('tupleExp'(['sharing'('val_of'('SEND','src_span'(13,11,13,15,4,4)),'mid','ack','val_of'('REC','src_span'(13,34,13,37,3,3)),'src_span'(13,15,13,19,4,4))]),'mid','ack','src_span_operator'('no_loc_info_available','src_span'(13,38,13,41,3,3))),'src_span'(13,1,13,54,53,53)).
'bindval'('GEN1','prefix'('src_span'(15,8,15,12,4,4),['out'('oranges')],'left','prefix'('src_span'(15,24,15,29,5,5),['out'('oranges')],'right','val_of'('GEN2','src_span'(15,41,15,45,4,4)),'src_span'(15,37,15,41,4,4)),'src_span'(15,20,15,24,4,4)),'src_span'(15,1,15,45,44,44)).
'bindval'('GEN2','prefix'('src_span'(16,8,16,12,4,4),['out'('pears')],'left','prefix'('src_span'(16,22,16,27,5,5),['out'('pears')],'right','val_of'('GEN1','src_span'(16,37,16,41,4,4)),'src_span'(16,33,16,37,4,4)),'src_span'(16,18,16,22,4,4)),'src_span'(16,1,16,41,40,40)).
'bindval'('MAIN','sharing'('val_of'('SYSTEM','src_span'(18,8,18,14,6,6)),'left','right','val_of'('GEN1','src_span'(18,38,18,42,4,4)),'src_span'(18,14,18,18,4,4)),'src_span'(18,1,18,42,41,41)).
'bindval'('PROB_TEST_TRACE','prefix'('src_span'(23,19,23,31,12,12),[],'dotTuple'(['left','oranges']),'prefix'('src_span'(23,35,23,48,13,13),[],'dotTuple'(['right','oranges']),'prefix'('src_span'(23,52,23,62,10,10),[],'dotTuple'(['left','pears']),'prefix'('src_span'(23,66,23,77,11,11),[],'dotTuple'(['right','pears']),'prefix'('src_span'(23,81,23,93,12,12),[],'dotTuple'(['left','oranges']),'prefix'('src_span'(23,97,23,110,13,13),[],'dotTuple'(['right','oranges']),'stop'('src_span'(23,114,23,118,4,4)),'src_span'(23,110,23,114,4,4)),'src_span'(23,93,23,97,4,4)),'src_span'(23,77,23,81,4,4)),'src_span'(23,62,23,66,4,4)),'src_span'(23,48,23,52,4,4)),'src_span'(23,31,23,35,4,4)),'src_span'(23,1,23,118,117,117)).
'val_of'('MAIN','src_span'(25,8,25,12,4,4)),'val_of'('PROB_TEST_TRACE','src_span'(25,17,25,32,15,15)).
'symbol'('SYSTEM','SYSTEM','src_span'(13,1,13,7,6,6),'Ident (Groundrep.)').
'symbol'('GEN1','GEN1','src_span'(15,1,15,5,4,4),'Ident (Groundrep.)').
'symbol'('oranges','oranges','src_span'(4,27,4,34,7,7),'Constructor of Datatype').
'symbol'('GEN2','GEN2','src_span'(16,1,16,5,4,4),'Ident (Groundrep.)').
'symbol'('ack','ack','src_span'(7,9,7,12,3,3),'Channel').
'symbol'('mid','mid','src_span'(6,20,6,23,3,3),'Channel').
'symbol'('right','right','src_span'(6,14,6,19,5,5),'Channel').
'symbol'('SEND','SEND','src_span'(9,1,9,5,4,4),'Ident (Groundrep.)').
'symbol'('REC','REC','src_span'(11,1,11,4,3,3),'Ident (Groundrep.)').
'symbol'('PROB_TEST_TRACE','PROB_TEST_TRACE','src_span'(23,1,23,16,15,15),'Ident (Groundrep.)').
'symbol'('FRUIT','FRUIT','src_span'(4,10,4,15,5,5),'Datatype').
'symbol'('pears','pears','src_span'(4,37,4,42,5,5),'Constructor of Datatype').
'symbol'('left','left','src_span'(6,9,6,13,4,4),'Channel').
'symbol'('x','x','src_span'(9,15,9,16,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(11,13,11,14,1,1),'Ident (Prolog Variable)').
'symbol'('MAIN','MAIN','src_span'(18,1,18,5,4,4),'Ident (Groundrep.)').
'symbol'('apples','apples','src_span'(4,18,4,24,6,6),'Constructor of Datatype').

