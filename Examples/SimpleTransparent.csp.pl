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
.
'agent'('GEN'(_i),'[]'('prefix'('src_span'(6,10,6,13,3,3),['out'(_i)],'out','agent_call'('src_span'(6,19,6,32,13,13),'GEN',['tupleExp'([_i,'int'(1)]),'int'(10)]),'src_span'(6,15,6,19,4,4)),'prefix'('src_span'(6,36,6,40,4,4),[],'stop','stop'('src_span'(6,44,6,48,4,4)),'src_span'(6,40,6,44,4,4)),'src_span_operator'('no_loc_info_available','src_span'(6,32,6,36,4,4))),'src_span'(6,1,6,48,47,47)).
'channel'('out','type'('dotTupleType'(['setExp'('int'(0),'int'(9))]))).
'channel'('stop','type'('dotUnitType')).
'bindval'('MAIN','|||'('agent_call'('src_span'(11,8,11,14,6,6),'GEN',['int'(1)]),'agent_call'('src_span'(11,19,11,34,15,15),'diamond',['agent_call'('src_span'(11,27,11,33,6,6),'GEN',['int'(5)])]),'src_span_operator'('no_loc_info_available','src_span'(11,14,11,19,5,5))),'src_span'(11,1,11,34,33,33)).
'symbol'('GEN','GEN','src_span'(6,1,6,4,3,3),'Function or Process').
'symbol'('stop','stop','src_span'(9,9,9,13,4,4),'Channel').
'symbol'('i','i','src_span'(6,5,6,6,1,1),'Ident (Prolog Variable)').
'symbol'('MAIN','MAIN','src_span'(11,1,11,5,4,4),'Ident (Groundrep.)').
'symbol'('out','out','src_span'(8,9,8,12,3,3),'Channel').

