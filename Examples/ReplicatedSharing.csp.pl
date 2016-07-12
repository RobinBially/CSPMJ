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
'channel'('link','type'('dotTupleType'(['setExp'('int'(0),'int'(9))]))).
'channel'('out','type'('dotTupleType'(['setExp'('int'(0),'int'(9))]))).
'bindval'('MAIN','procRepSharing'('link',['comprehensionGenerator'('int'(1),'int'(3))],'agent_call'('src_span'(4,36,4,40,4,4),'P',[]),'src_span'(4,25,4,33,8,8)),'src_span'(4,1,4,40,39,39)).
'agent'('P'(_x),'[]'('prefix'('src_span'(6,8,6,11,3,3),['out'(_x)],'out','agent_call'('src_span'(6,17,6,21,4,4),'P',[_x]),'src_span'(6,13,6,17,4,4)),'prefix'('src_span'(6,25,6,29,4,4),['in'(_z)],'link','prefix'('src_span'(6,35,6,38,3,3),['out'(_z)],'out','agent_call'('src_span'(6,44,6,48,4,4),'P',[_x]),'src_span'(6,40,6,44,4,4)),'src_span'(6,31,6,35,4,4)),'src_span_operator'('no_loc_info_available','src_span'(6,21,6,25,4,4))),'src_span'(6,1,6,48,47,47)).
'symbol'('P','P','src_span'(6,1,6,2,1,1),'Function or Process').
'symbol'('link','link','src_span'(1,9,1,13,4,4),'Channel').
'symbol'('x','x','src_span'(6,3,6,4,1,1),'Ident (Prolog Variable)').
'symbol'('MAIN','MAIN','src_span'(4,1,4,5,4,4),'Ident (Groundrep.)').
'symbol'('z','z','src_span'(6,30,6,31,1,1),'Ident (Prolog Variable)').
'symbol'('out','out','src_span'(1,14,1,17,3,3),'Channel').

