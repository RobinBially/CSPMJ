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
'channel'('in','type'('dotTupleType'(['setExp'('int'(0),'int'(1))]))).
'channel'('out','type'('dotTupleType'(['setExp'('int'(0),'int'(1))]))).
'channel'('left','type'('dotTupleType'(['setExp'('int'(0),'int'(1))]))).
'channel'('right','type'('dotTupleType'(['setExp'('int'(0),'int'(1))]))).
'bindval'('Buff','prefix'('src_span'(4,8,4,10,2,2),['in'(_x)],'in','prefix'('src_span'(4,16,4,19,3,3),['out'('tupleExp'(['src_span'(4,21,4,24,3,3),[],_x,'int'(1)]),'int'(2))],'out','val_of'('Buff','src_span'(4,31,4,35,4,4)),'src_span'(4,27,4,31,4,4)),'src_span'(4,12,4,16,4,4)),'src_span'(4,1,4,35,34,34)).
'bindval'('MAIN','lParallel'('linkList'(['link'('out','in')]),'val_of'('Buff','src_span'(7,8,7,12,4,4)),'tupleExp'(['lParallel'('linkList'(['link'('out','in')]),'val_of'('Buff','src_span'(7,29,7,33,4,4)),'val_of'('Buff','src_span'(7,49,7,53,4,4)),'src_span'(7,33,7,36,3,3))]),'src_span'(7,12,7,15,3,3)),'src_span'(7,1,7,54,53,53)).
'val_of'('MAIN','src_span'(13,8,13,12,4,4)),'val_of'('Nroot','src_span'(13,18,13,23,5,5)).
'val_of'('Nroot','src_span'(14,8,14,13,5,5)),'val_of'('MAIN','src_span'(14,19,14,23,4,4)).
'bindval'('Nroot','tupleExp'(['val_of'('N5105','src_span'(18,38,18,43,5,5))]),'src_span'(18,1,18,47,46,46)).
'bindval'('N5105','[]'('prefix'('src_span'(21,9,21,13,4,4),[],'dotTuple'(['in','int'(0)]),'val_of'('N5106','src_span'(21,15,21,20,5,5)),'src_span'(21,13,21,15,2,2)),'prefix'('src_span'(22,5,22,9,4,4),[],'dotTuple'(['in','int'(1)]),'val_of'('N5107','src_span'(22,11,22,16,5,5)),'src_span'(22,9,22,11,2,2)),'src_span_operator'('no_loc_info_available','src_span'(21,20,21,26,6,6))),'src_span'(21,1,22,16,15,15)).
'bindval'('N5106','tupleExp'(['val_of'('N5108','src_span'(25,76,25,81,5,5))]),'src_span'(25,1,25,85,84,84)).
'bindval'('N5107','tupleExp'(['val_of'('N5117','src_span'(28,76,28,81,5,5))]),'src_span'(28,1,28,85,84,84)).
'bindval'('N5108','[]'('[]'('tupleExp'(['val_of'('N5111','src_span'(31,76,31,81,5,5))]),'prefix'('src_span'(32,8,32,12,4,4),[],'dotTuple'(['in','int'(0)]),'val_of'('N5109','src_span'(32,14,32,19,5,5)),'src_span'(32,12,32,14,2,2)),'src_span_operator'('no_loc_info_available','src_span'(32,3,32,8,5,5))),'prefix'('src_span'(33,5,33,9,4,4),[],'dotTuple'(['in','int'(1)]),'val_of'('N5110','src_span'(33,11,33,16,5,5)),'src_span'(33,9,33,11,2,2)),'src_span_operator'('no_loc_info_available','src_span'(32,19,32,25,6,6))),'src_span'(31,1,33,16,15,15)).
'bindval'('N5109','tupleExp'(['val_of'('N5122','src_span'(36,76,36,81,5,5))]),'src_span'(36,1,36,85,84,84)).
'bindval'('N5110','tupleExp'(['val_of'('N5112','src_span'(39,76,39,81,5,5))]),'src_span'(39,1,39,85,84,84)).
'bindval'('N5111','[]'('[]'('prefix'('src_span'(42,9,42,13,4,4),[],'dotTuple'(['in','int'(0)]),'val_of'('N5122','src_span'(42,15,42,20,5,5)),'src_span'(42,13,42,15,2,2)),'prefix'('src_span'(43,5,43,9,4,4),[],'dotTuple'(['in','int'(1)]),'val_of'('N5112','src_span'(43,11,43,16,5,5)),'src_span'(43,9,43,11,2,2)),'src_span_operator'('no_loc_info_available','src_span'(42,20,42,26,6,6))),'prefix'('src_span'(44,5,44,10,5,5),[],'dotTuple'(['out','int'(1)]),'val_of'('N5130','src_span'(44,12,44,17,5,5)),'src_span'(44,10,44,12,2,2)),'src_span_operator'('no_loc_info_available','src_span'(43,16,43,22,6,6))),'src_span'(42,1,44,17,16,16)).
'bindval'('N5112','[]'('tupleExp'(['val_of'('N5113','src_span'(47,76,47,81,5,5))]),'prefix'('src_span'(48,8,48,13,5,5),[],'dotTuple'(['out','int'(1)]),'val_of'('N5114','src_span'(48,15,48,20,5,5)),'src_span'(48,13,48,15,2,2)),'src_span_operator'('no_loc_info_available','src_span'(48,3,48,8,5,5))),'src_span'(47,1,48,20,19,19)).
'bindval'('N5113','[]'('[]'('prefix'('src_span'(51,9,51,13,4,4),[],'dotTuple'(['in','int'(0)]),'val_of'('N5115','src_span'(51,15,51,20,5,5)),'src_span'(51,13,51,15,2,2)),'prefix'('src_span'(52,5,52,9,4,4),[],'dotTuple'(['in','int'(1)]),'val_of'('N5116','src_span'(52,11,52,16,5,5)),'src_span'(52,9,52,11,2,2)),'src_span_operator'('no_loc_info_available','src_span'(51,20,51,26,6,6))),'prefix'('src_span'(53,5,53,10,5,5),[],'dotTuple'(['out','int'(1)]),'val_of'('N5117','src_span'(53,12,53,17,5,5)),'src_span'(53,10,53,12,2,2)),'src_span_operator'('no_loc_info_available','src_span'(52,16,52,22,6,6))),'src_span'(51,1,53,17,16,16)).
'bindval'('N5114','tupleExp'(['val_of'('N5117','src_span'(56,76,56,81,5,5))]),'src_span'(56,1,56,85,84,84)).
'bindval'('N5115','prefix'('src_span'(59,9,59,14,5,5),[],'dotTuple'(['out','int'(1)]),'val_of'('N5118','src_span'(59,16,59,21,5,5)),'src_span'(59,14,59,16,2,2)),'src_span'(59,1,59,21,20,20)).
'bindval'('N5116','prefix'('src_span'(62,9,62,14,5,5),[],'dotTuple'(['out','int'(1)]),'val_of'('N5126','src_span'(62,16,62,21,5,5)),'src_span'(62,14,62,16,2,2)),'src_span'(62,1,62,21,20,20)).
'bindval'('N5117','[]'('[]'('tupleExp'(['val_of'('N5129','src_span'(65,76,65,81,5,5))]),'prefix'('src_span'(66,8,66,12,4,4),[],'dotTuple'(['in','int'(0)]),'val_of'('N5118','src_span'(66,14,66,19,5,5)),'src_span'(66,12,66,14,2,2)),'src_span_operator'('no_loc_info_available','src_span'(66,3,66,8,5,5))),'prefix'('src_span'(67,5,67,9,4,4),[],'dotTuple'(['in','int'(1)]),'val_of'('N5126','src_span'(67,11,67,16,5,5)),'src_span'(67,9,67,11,2,2)),'src_span_operator'('no_loc_info_available','src_span'(66,19,66,25,6,6))),'src_span'(65,1,67,16,15,15)).
'bindval'('N5118','tupleExp'(['val_of'('N5119','src_span'(70,76,70,81,5,5))]),'src_span'(70,1,70,85,84,84)).
'bindval'('N5119','[]'('tupleExp'(['val_of'('N5120','src_span'(73,76,73,81,5,5))]),'prefix'('src_span'(74,8,74,13,5,5),[],'dotTuple'(['out','int'(0)]),'val_of'('N5121','src_span'(74,15,74,20,5,5)),'src_span'(74,13,74,15,2,2)),'src_span_operator'('no_loc_info_available','src_span'(74,3,74,8,5,5))),'src_span'(73,1,74,20,19,19)).
'bindval'('N5120','[]'('[]'('prefix'('src_span'(77,9,77,13,4,4),[],'dotTuple'(['in','int'(0)]),'val_of'('N5131','src_span'(77,15,77,20,5,5)),'src_span'(77,13,77,15,2,2)),'prefix'('src_span'(78,5,78,9,4,4),[],'dotTuple'(['in','int'(1)]),'val_of'('N5132','src_span'(78,11,78,16,5,5)),'src_span'(78,9,78,11,2,2)),'src_span_operator'('no_loc_info_available','src_span'(77,20,77,26,6,6))),'prefix'('src_span'(79,5,79,10,5,5),[],'dotTuple'(['out','int'(0)]),'val_of'('N5108','src_span'(79,12,79,17,5,5)),'src_span'(79,10,79,12,2,2)),'src_span_operator'('no_loc_info_available','src_span'(78,16,78,22,6,6))),'src_span'(77,1,79,17,16,16)).
'bindval'('N5121','tupleExp'(['val_of'('N5108','src_span'(82,76,82,81,5,5))]),'src_span'(82,1,82,85,84,84)).
'bindval'('N5122','[]'('tupleExp'(['val_of'('N5123','src_span'(85,76,85,81,5,5))]),'prefix'('src_span'(86,8,86,13,5,5),[],'dotTuple'(['out','int'(1)]),'val_of'('N5121','src_span'(86,15,86,20,5,5)),'src_span'(86,13,86,15,2,2)),'src_span_operator'('no_loc_info_available','src_span'(86,3,86,8,5,5))),'src_span'(85,1,86,20,19,19)).
'bindval'('N5123','[]'('[]'('prefix'('src_span'(89,9,89,13,4,4),[],'dotTuple'(['in','int'(0)]),'val_of'('N5124','src_span'(89,15,89,20,5,5)),'src_span'(89,13,89,15,2,2)),'prefix'('src_span'(90,5,90,9,4,4),[],'dotTuple'(['in','int'(1)]),'val_of'('N5125','src_span'(90,11,90,16,5,5)),'src_span'(90,9,90,11,2,2)),'src_span_operator'('no_loc_info_available','src_span'(89,20,89,26,6,6))),'prefix'('src_span'(91,5,91,10,5,5),[],'dotTuple'(['out','int'(1)]),'val_of'('N5108','src_span'(91,12,91,17,5,5)),'src_span'(91,10,91,12,2,2)),'src_span_operator'('no_loc_info_available','src_span'(90,16,90,22,6,6))),'src_span'(89,1,91,17,16,16)).
'bindval'('N5124','prefix'('src_span'(94,9,94,14,5,5),[],'dotTuple'(['out','int'(1)]),'val_of'('N5109','src_span'(94,16,94,21,5,5)),'src_span'(94,14,94,16,2,2)),'src_span'(94,1,94,21,20,20)).
'bindval'('N5125','prefix'('src_span'(97,9,97,14,5,5),[],'dotTuple'(['out','int'(1)]),'val_of'('N5110','src_span'(97,16,97,21,5,5)),'src_span'(97,14,97,16,2,2)),'src_span'(97,1,97,21,20,20)).
'bindval'('N5126','tupleExp'(['val_of'('N5127','src_span'(100,76,100,81,5,5))]),'src_span'(100,1,100,85,84,84)).
'bindval'('N5127','[]'('tupleExp'(['val_of'('N5128','src_span'(103,76,103,81,5,5))]),'prefix'('src_span'(104,8,104,13,5,5),[],'dotTuple'(['out','int'(0)]),'val_of'('N5114','src_span'(104,15,104,20,5,5)),'src_span'(104,13,104,15,2,2)),'src_span_operator'('no_loc_info_available','src_span'(104,3,104,8,5,5))),'src_span'(103,1,104,20,19,19)).
'bindval'('N5128','[]'('[]'('prefix'('src_span'(107,9,107,13,4,4),[],'dotTuple'(['in','int'(0)]),'val_of'('N5133','src_span'(107,15,107,20,5,5)),'src_span'(107,13,107,15,2,2)),'prefix'('src_span'(108,5,108,9,4,4),[],'dotTuple'(['in','int'(1)]),'val_of'('N5134','src_span'(108,11,108,16,5,5)),'src_span'(108,9,108,11,2,2)),'src_span_operator'('no_loc_info_available','src_span'(107,20,107,26,6,6))),'prefix'('src_span'(109,5,109,10,5,5),[],'dotTuple'(['out','int'(0)]),'val_of'('N5117','src_span'(109,12,109,17,5,5)),'src_span'(109,10,109,12,2,2)),'src_span_operator'('no_loc_info_available','src_span'(108,16,108,22,6,6))),'src_span'(107,1,109,17,16,16)).
'bindval'('N5129','[]'('[]'('prefix'('src_span'(112,9,112,13,4,4),[],'dotTuple'(['in','int'(0)]),'val_of'('N5119','src_span'(112,15,112,20,5,5)),'src_span'(112,13,112,15,2,2)),'prefix'('src_span'(113,5,113,9,4,4),[],'dotTuple'(['in','int'(1)]),'val_of'('N5127','src_span'(113,11,113,16,5,5)),'src_span'(113,9,113,11,2,2)),'src_span_operator'('no_loc_info_available','src_span'(112,20,112,26,6,6))),'prefix'('src_span'(114,5,114,10,5,5),[],'dotTuple'(['out','int'(0)]),'val_of'('N5130','src_span'(114,12,114,17,5,5)),'src_span'(114,10,114,12,2,2)),'src_span_operator'('no_loc_info_available','src_span'(113,16,113,22,6,6))),'src_span'(112,1,114,17,16,16)).
'bindval'('N5130','[]'('prefix'('src_span'(117,9,117,13,4,4),[],'dotTuple'(['in','int'(0)]),'val_of'('N5121','src_span'(117,15,117,20,5,5)),'src_span'(117,13,117,15,2,2)),'prefix'('src_span'(118,5,118,9,4,4),[],'dotTuple'(['in','int'(1)]),'val_of'('N5114','src_span'(118,11,118,16,5,5)),'src_span'(118,9,118,11,2,2)),'src_span_operator'('no_loc_info_available','src_span'(117,20,117,26,6,6))),'src_span'(117,1,118,16,15,15)).
'bindval'('N5131','prefix'('src_span'(121,9,121,14,5,5),[],'dotTuple'(['out','int'(0)]),'val_of'('N5109','src_span'(121,16,121,21,5,5)),'src_span'(121,14,121,16,2,2)),'src_span'(121,1,121,21,20,20)).
'bindval'('N5132','prefix'('src_span'(124,9,124,14,5,5),[],'dotTuple'(['out','int'(0)]),'val_of'('N5110','src_span'(124,16,124,21,5,5)),'src_span'(124,14,124,16,2,2)),'src_span'(124,1,124,21,20,20)).
'bindval'('N5133','prefix'('src_span'(127,9,127,14,5,5),[],'dotTuple'(['out','int'(0)]),'val_of'('N5118','src_span'(127,16,127,21,5,5)),'src_span'(127,14,127,16,2,2)),'src_span'(127,1,127,21,20,20)).
'bindval'('N5134','prefix'('src_span'(130,9,130,14,5,5),[],'dotTuple'(['out','int'(0)]),'val_of'('N5126','src_span'(130,16,130,21,5,5)),'src_span'(130,14,130,16,2,2)),'src_span'(130,1,130,21,20,20)).
'symbol'('N5123','N5123','src_span'(89,1,89,6,5,5),'Ident (Groundrep.)').
'symbol'('N5122','N5122','src_span'(85,1,85,6,5,5),'Ident (Groundrep.)').
'symbol'('N5121','N5121','src_span'(82,1,82,6,5,5),'Ident (Groundrep.)').
'symbol'('N5120','N5120','src_span'(77,1,77,6,5,5),'Ident (Groundrep.)').
'symbol'('N5105','N5105','src_span'(21,1,21,6,5,5),'Ident (Groundrep.)').
'symbol'('N5127','N5127','src_span'(103,1,103,6,5,5),'Ident (Groundrep.)').
'symbol'('N5126','N5126','src_span'(100,1,100,6,5,5),'Ident (Groundrep.)').
'symbol'('N5125','N5125','src_span'(97,1,97,6,5,5),'Ident (Groundrep.)').
'symbol'('out','out','src_span'(2,12,2,15,3,3),'Channel').
'symbol'('N5124','N5124','src_span'(94,1,94,6,5,5),'Ident (Groundrep.)').
'symbol'('N5109','N5109','src_span'(36,1,36,6,5,5),'Ident (Groundrep.)').
'symbol'('N5108','N5108','src_span'(31,1,31,6,5,5),'Ident (Groundrep.)').
'symbol'('N5107','N5107','src_span'(28,1,28,6,5,5),'Ident (Groundrep.)').
'symbol'('N5129','N5129','src_span'(112,1,112,6,5,5),'Ident (Groundrep.)').
'symbol'('N5106','N5106','src_span'(25,1,25,6,5,5),'Ident (Groundrep.)').
'symbol'('N5128','N5128','src_span'(107,1,107,6,5,5),'Ident (Groundrep.)').
'symbol'('MAIN','MAIN','src_span'(7,1,7,5,4,4),'Ident (Groundrep.)').
'symbol'('N5130','N5130','src_span'(117,1,117,6,5,5),'Ident (Groundrep.)').
'symbol'('in','in','src_span'(2,9,2,11,2,2),'Channel').
'symbol'('N5112','N5112','src_span'(47,1,47,6,5,5),'Ident (Groundrep.)').
'symbol'('N5134','N5134','src_span'(130,1,130,6,5,5),'Ident (Groundrep.)').
'symbol'('N5111','N5111','src_span'(42,1,42,6,5,5),'Ident (Groundrep.)').
'symbol'('N5133','N5133','src_span'(127,1,127,6,5,5),'Ident (Groundrep.)').
'symbol'('N5110','N5110','src_span'(39,1,39,6,5,5),'Ident (Groundrep.)').
'symbol'('N5132','N5132','src_span'(124,1,124,6,5,5),'Ident (Groundrep.)').
'symbol'('right','right','src_span'(2,21,2,26,5,5),'Channel').
'symbol'('N5131','N5131','src_span'(121,1,121,6,5,5),'Ident (Groundrep.)').
'symbol'('Buff','Buff','src_span'(4,1,4,5,4,4),'Ident (Groundrep.)').
'symbol'('N5116','N5116','src_span'(62,1,62,6,5,5),'Ident (Groundrep.)').
'symbol'('N5115','N5115','src_span'(59,1,59,6,5,5),'Ident (Groundrep.)').
'symbol'('N5114','N5114','src_span'(56,1,56,6,5,5),'Ident (Groundrep.)').
'symbol'('N5113','N5113','src_span'(51,1,51,6,5,5),'Ident (Groundrep.)').
'symbol'('Nroot','Nroot','src_span'(18,1,18,6,5,5),'Ident (Groundrep.)').
'symbol'('N5119','N5119','src_span'(73,1,73,6,5,5),'Ident (Groundrep.)').
'symbol'('N5118','N5118','src_span'(70,1,70,6,5,5),'Ident (Groundrep.)').
'symbol'('N5117','N5117','src_span'(65,1,65,6,5,5),'Ident (Groundrep.)').
'symbol'('left','left','src_span'(2,16,2,20,4,4),'Channel').
'symbol'('x','x','src_span'(4,11,4,12,1,1),'Ident (Prolog Variable)').

