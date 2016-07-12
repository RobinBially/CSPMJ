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
'channel'('outseq','type'('dotTupleType'(['int'(0),'int'(1)]))).
'bindval'('MAIN1','prefix'('src_span'(5,9,5,15,6,6),['out'('src_span'(5,17,5,18,1,1),[],'int'(0))],'outseq','prefix'('src_span'(5,23,5,29,6,6),['out'('src_span'(5,31,5,32,1,1),[],'int'(0),'src_span'(5,33,5,34,1,1),[],'int'(1),'src_span'(5,35,5,36,1,1),[],'int'(0))],'outseq','prefix'('src_span'(5,41,5,47,6,6),['out'('src_span'(5,49,5,50,1,1),[],'int'(1),'src_span'(5,51,5,52,1,1),[],'int'(2))],'outseq','prefix'('src_span'(5,57,5,63,6,6),['in'(_x)],'outseq','prefix'('src_span'(5,69,5,75,6,6),['out'(_x)],'outseq','stop'('src_span'(5,81,5,85,4,4)),'src_span'(5,77,5,81,4,4)),'src_span'(5,65,5,69,4,4)),'src_span'(5,53,5,57,4,4)),'src_span'(5,37,5,41,4,4)),'src_span'(5,19,5,23,4,4)),'src_span'(5,1,5,85,84,84)).
'bindval'('MAIN','prefix'('src_span'(6,8,6,14,6,6),['out'('src_span'(6,16,6,17,1,1),[],'int'(0))],'outseq','prefix'('src_span'(6,22,6,28,6,6),['out'('src_span'(6,30,6,31,1,1),[],'int'(0),'src_span'(6,32,6,33,1,1),[],'int'(1),'src_span'(6,34,6,35,1,1),[],'int'(0))],'outseq','prefix'('src_span'(6,40,6,46,6,6),['out'('src_span'(6,48,6,49,1,1),[],'int'(1),'src_span'(6,50,6,51,1,1),[],'int'(1))],'outseq','stop'('src_span'(6,56,6,60,4,4)),'src_span'(6,52,6,56,4,4)),'src_span'(6,36,6,40,4,4)),'src_span'(6,18,6,22,4,4)),'src_span'(6,1,6,60,59,59)).
'bindval'('PROB_TEST_TRACE','prefix'('src_span'(9,19,9,29,10,10),[],'dotTuple'(['outseq','src_span'(9,27,9,28,1,1),[],'int'(0)]),'prefix'('src_span'(9,33,9,47,14,14),[],'dotTuple'(['outseq','src_span'(9,41,9,42,1,1),[],'int'(0),'src_span'(9,43,9,44,1,1),[],'int'(1),'src_span'(9,45,9,46,1,1),[],'int'(0)]),'prefix'('src_span'(9,51,9,63,12,12),[],'dotTuple'(['outseq','src_span'(9,59,9,60,1,1),[],'int'(1),'src_span'(9,61,9,62,1,1),[],'int'(1)]),'stop'('src_span'(9,67,9,71,4,4)),'src_span'(9,63,9,67,4,4)),'src_span'(9,47,9,51,4,4)),'src_span'(9,29,9,33,4,4)),'src_span'(9,1,9,71,70,70)).
'val_of'('MAIN','src_span'(12,8,12,12,4,4)),'val_of'('PROB_TEST_TRACE','src_span'(12,17,12,32,15,15)).
'symbol'('outseq','outseq','src_span'(2,9,2,15,6,6),'Channel').
'symbol'('PROB_TEST_TRACE','PROB_TEST_TRACE','src_span'(9,1,9,16,15,15),'Ident (Groundrep.)').
'symbol'('MAIN1','MAIN1','src_span'(5,1,5,6,5,5),'Ident (Groundrep.)').
'symbol'('x','x','src_span'(5,64,5,65,1,1),'Ident (Prolog Variable)').
'symbol'('MAIN','MAIN','src_span'(6,1,6,5,4,4),'Ident (Groundrep.)').

