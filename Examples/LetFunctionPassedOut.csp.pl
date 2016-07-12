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
'channel'('req','type'('dotTupleType'(['setExp'('int'(0),'int'(9))]))).
'channel'('ack','type'('dotTupleType'(['setExp'('int'(0),'int'(9))]))).
'channel'('out','type'('dotTupleType'(['setExp'('int'(0),'int'(9))]))).
'agent'('other1'(_req2),'ack','src_span'(6,1,6,18,17,17)).
'agent'('other1'(_ack2),'out','src_span'(7,1,7,18,17,17)).
'agent'('other1'(_out2),'req','src_span'(8,1,8,18,17,17)).
'agent'('Proc1'(_ch,_V,_other),'prefix'('src_span'(11,21,11,30,9,9),['out'(_V)],'agent_call'('src_span'(11,21,11,30,9,9),_other,['src_span'(11,27,11,29,2,2),[],_ch]),'agent_call'('src_span'(11,36,11,60,24,24),'Proc1',['agent_call'('src_span'(11,42,11,51,9,9),_other,[_ch]),_V,_other]),'src_span'(11,32,11,36,4,4)),'src_span'(11,1,11,60,59,59)).
'agent'('Proc2'(_x,_Y),'let'(['agent'('other2'(_req3),'out','src_span'(14,3,14,20,17,17)),'agent'('other2'(_ack3),'req','src_span'(15,3,15,20,17,17)),'agent'('other2'(_out3),'ack','src_span'(16,3,16,20,17,17))],'agent_call'('src_span'(17,11,17,28,17,17),'Proc1',[_x,_Y,'other2'])),'src_span'(13,1,17,28,27,27)).
'bindval'('MAIN','[]'('agent_call'('src_span'(19,8,19,27,19,19),'Proc1',['req','int'(1),'other1']),'agent_call'('src_span'(19,31,19,43,12,12),'Proc2',['req','int'(2)]),'src_span_operator'('no_loc_info_available','src_span'(19,27,19,31,4,4))),'src_span'(19,1,19,43,42,42)).
'symbol'('Proc2','Proc2','src_span'(13,1,13,6,5,5),'Function or Process').
'symbol'('other','other','src_span'(11,12,11,17,5,5),'Ident (Prolog Variable)').
'symbol'('ch','ch','src_span'(11,7,11,9,2,2),'Ident (Prolog Variable)').
'symbol'('Proc1','Proc1','src_span'(11,1,11,6,5,5),'Function or Process').
'symbol'('ack','ack','src_span'(3,13,3,16,3,3),'Channel').
'symbol'('ack2','ack','src_span'(7,8,7,11,3,3),'Ident (Prolog Variable)').
'symbol'('ack3','ack','src_span'(15,10,15,13,3,3),'Ident (Prolog Variable)').
'symbol'('out','out','src_span'(3,17,3,20,3,3),'Channel').
'symbol'('out2','out','src_span'(8,8,8,11,3,3),'Ident (Prolog Variable)').
'symbol'('out3','out','src_span'(16,10,16,13,3,3),'Ident (Prolog Variable)').
'symbol'('V','V','src_span'(11,10,11,11,1,1),'Ident (Prolog Variable)').
'symbol'('x','x','src_span'(13,7,13,8,1,1),'Ident (Prolog Variable)').
'symbol'('Y','Y','src_span'(13,9,13,10,1,1),'Ident (Prolog Variable)').
'symbol'('MAIN','MAIN','src_span'(19,1,19,5,4,4),'Ident (Groundrep.)').
'symbol'('other1','other1','src_span'(6,1,6,7,6,6),'Function or Process').
'symbol'('req','req','src_span'(3,9,3,12,3,3),'Channel').
'symbol'('req2','req','src_span'(6,8,6,11,3,3),'Ident (Prolog Variable)').
'symbol'('req3','req','src_span'(14,10,14,13,3,3),'Ident (Prolog Variable)').
'symbol'('other2','other2','src_span'(14,3,14,9,6,6),'Function or Process').

