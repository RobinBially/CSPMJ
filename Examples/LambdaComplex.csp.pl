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
'agent'('f1'(_x,_y,_z),_x,_y,_z,'src_span'(3,1,3,18,17,17)).
'bindval'('f2','lambda'([_x2,_y2,_z2],_x2,_y2,_z2),'src_span'(5,1,5,21,20,20)).
'channel'('c','type'('dotTupleType'(['setExp'('int'(0),'int'(3))]))).
'channel'('d','type'('dotTupleType'(['setExp'('int'(0),'int'(99))]))).
'bindval'('MAIN1','prefix'('src_span'(10,9,10,10,1,1),['in'(_v)],'c','prefix'('src_span'(10,16,10,17,1,1),['in'(_w)],'c','prefix'('src_span'(10,23,10,24,1,1),['out'('agent_call'('src_span'(10,25,10,34,9,9),'f1',['src_span'(10,28,10,29,1,1),[],_v,'src_span'(10,30,10,31,1,1),[],_w,'src_span'(10,32,10,33,1,1),[],_w]))],'d','prefix'('src_span'(10,38,10,39,1,1),['out'('agent_call'('src_span'(10,40,10,49,9,9),'f2',['src_span'(10,43,10,44,1,1),[],_v,'src_span'(10,45,10,46,1,1),[],_w,'src_span'(10,47,10,48,1,1),[],_w]))],'d','skip'('src_span'(11,7,11,11,4,4)),'src_span'(10,49,10,58,9,9)),'src_span'(10,34,10,38,4,4)),'src_span'(10,19,10,23,4,4)),'src_span'(10,12,10,16,4,4)),'src_span'(10,1,11,11,10,10)).
'bindval'('MAIN2','prefix'('src_span'(13,9,13,10,1,1),['in'(_v2)],'c','prefix'('src_span'(13,17,13,18,1,1),['out'('tupleExp'(['src_span'(13,20,13,36,16,16),[],'tupleExp'(['lambda'([_x3],'src_span'(13,27,13,30,3,3),[],_x3,'int'(1))]),'src_span'(13,32,13,35,3,3),[],_v2,_v2]))],'d','skip'('src_span'(13,41,13,45,4,4)),'src_span'(13,37,13,41,4,4)),'src_span'(13,12,13,17,5,5)),'src_span'(13,1,13,45,44,44)).
'agent'('map'(_f,_s),'agent_call'('src_span'(15,14,15,18,4,4),_f,[]),['comprehensionGenerator'(_s)],'src_span'(15,1,15,27,26,26)).
'agent'('twice'(_n),_n,'int'(2),'src_span'(17,1,17,15,14,14)).
'agent'('Q','skip'('src_span'(19,9,19,13,4,4)),'src_span'(19,1,19,13,12,12)).
'agent'('Q'('appendPattern'([_h,_t])),'prefix'('src_span'(20,12,20,13,1,1),['out'(_h)],'d','agent_call'('src_span'(20,19,20,23,4,4),'Q',[_t]),'src_span'(20,15,20,19,4,4)),'src_span'(20,1,20,23,22,22)).
'bindval'('MAIN3',';'('agent_call'('src_span'(23,9,23,28,19,19),'Q',['agent_call'('src_span'(23,11,23,27,16,16),'map',['twice','int'(1),'int'(2)])]),'agent_call'('src_span'(24,6,24,30,24,24),'Q',['agent_call'('src_span'(24,8,24,29,21,21),'map',['lambda'([_y3],_y3,_y3),'int'(2),'int'(3),'int'(4)])]),'src_span_operator'('no_loc_info_available','src_span'(23,28,23,37,9,9))),'src_span'(23,1,24,30,29,29)).
'agent'('maps'(_f2),'src_span'(27,1,27,16,15,15)).
'agent'('maps'(_f3,'appendPattern'([_h2,_t2])),'^'('agent_call'('src_span'(28,18,28,22,4,4),_f3,[_h2]),'agent_call'('src_span'(28,24,28,33,9,9),'maps',[_f3,_t2])),'src_span'(28,1,28,33,32,32)).
'bindval'('MAIN4',';'('agent_call'('src_span'(30,9,30,29,20,20),'Q',['agent_call'('src_span'(30,11,30,28,17,17),'maps',['twice','int'(1),'int'(2)])]),'agent_call'('src_span'(31,6,31,31,25,25),'Q',['agent_call'('src_span'(31,8,31,30,22,22),'maps',['lambda'([_y4],_y4,_y4),'int'(2),'int'(3),'int'(4)])]),'src_span_operator'('no_loc_info_available','src_span'(30,29,30,38,9,9))),'src_span'(30,1,31,31,30,30)).
'agent'('MAIN5'(_zz),'agent_call'('src_span'(33,13,33,42,29,29),'Q',['agent_call'('src_span'(33,15,33,41,26,26),'maps',['lambda'([_ww],_ww,_zz),'int'(2),'int'(3),'int'(4)])]),'src_span'(33,1,33,42,41,41)).
'bindval'('MAIN',';'(';'(';'(';'(';'('val_of'('MAIN1','src_span'(35,8,35,13,5,5)),'val_of'('MAIN2','src_span'(35,17,35,22,5,5)),'src_span_operator'('no_loc_info_available','src_span'(35,13,35,17,4,4))),'val_of'('MAIN3','src_span'(35,25,35,30,5,5)),'src_span_operator'('no_loc_info_available','src_span'(35,22,35,25,3,3))),'val_of'('MAIN4','src_span'(35,33,35,38,5,5)),'src_span_operator'('no_loc_info_available','src_span'(35,30,35,33,3,3))),'agent_call'('src_span'(35,40,35,48,8,8),'MAIN5',['int'(3)]),'src_span_operator'('no_loc_info_available','src_span'(35,38,35,40,2,2))),'val_of'('MAIN6','src_span'(35,49,35,54,5,5)),'src_span_operator'('no_loc_info_available','src_span'(35,48,35,49,1,1))),'src_span'(35,1,35,54,53,53)).
'bindval'('MAIN6','prefix'('src_span'(37,9,37,10,1,1),['in'(_x4)],'c','tupleExp'(['tupleExp'(['lambda'([_y5],'prefix'('src_span'(37,23,37,24,1,1),['out'('tupleExp'(['src_span'(37,26,37,29,3,3),[],_y5,_y5]))],'d','skip'('src_span'(37,32,37,36,4,4)),'src_span'(37,30,37,32,2,2)))]),_x4]),'src_span'(37,12,37,16,4,4)),'src_span'(37,1,37,41,40,40)).
'symbol'('MAIN4','MAIN4','src_span'(30,1,30,6,5,5),'Ident (Groundrep.)').
'symbol'('zz','zz','src_span'(33,7,33,9,2,2),'Ident (Prolog Variable)').
'symbol'('MAIN3','MAIN3','src_span'(23,1,23,6,5,5),'Ident (Groundrep.)').
'symbol'('MAIN2','MAIN2','src_span'(13,1,13,6,5,5),'Ident (Groundrep.)').
'symbol'('MAIN1','MAIN1','src_span'(10,1,10,6,5,5),'Ident (Groundrep.)').
'symbol'('MAIN6','MAIN6','src_span'(37,1,37,6,5,5),'Ident (Groundrep.)').
'symbol'('MAIN5','MAIN5','src_span'(33,1,33,6,5,5),'Function or Process').
'symbol'('f1','f1','src_span'(3,1,3,3,2,2),'Function or Process').
'symbol'('f2','f2','src_span'(5,1,5,3,2,2),'Ident (Groundrep.)').
'symbol'('Q','Q','src_span'(19,1,19,2,1,1),'Function or Process').
'symbol'('MAIN','MAIN','src_span'(35,1,35,5,4,4),'Ident (Groundrep.)').
'symbol'('map','map','src_span'(15,1,15,4,3,3),'Function or Process').
'symbol'('ww','ww','src_span'(33,21,33,23,2,2),'Ident (Prolog Variable)').
'symbol'('c','c','src_span'(7,9,7,10,1,1),'Channel').
'symbol'('d','d','src_span'(8,9,8,10,1,1),'Channel').
'symbol'('maps','maps','src_span'(27,1,27,5,4,4),'Function or Process').
'symbol'('f','f','src_span'(15,5,15,6,1,1),'Ident (Prolog Variable)').
'symbol'('f2','f','src_span'(27,6,27,7,1,1),'Ident (Prolog Variable)').
'symbol'('f3','f','src_span'(28,6,28,7,1,1),'Ident (Prolog Variable)').
'symbol'('h','h','src_span'(20,4,20,5,1,1),'Ident (Prolog Variable)').
'symbol'('h2','h','src_span'(28,9,28,10,1,1),'Ident (Prolog Variable)').
'symbol'('n','n','src_span'(17,7,17,8,1,1),'Ident (Prolog Variable)').
'symbol'('twice','twice','src_span'(17,1,17,6,5,5),'Function or Process').
'symbol'('s','s','src_span'(15,7,15,8,1,1),'Ident (Prolog Variable)').
'symbol'('t','t','src_span'(20,7,20,8,1,1),'Ident (Prolog Variable)').
'symbol'('t2','t','src_span'(28,12,28,13,1,1),'Ident (Prolog Variable)').
'symbol'('v','v','src_span'(10,11,10,12,1,1),'Ident (Prolog Variable)').
'symbol'('v2','v','src_span'(13,11,13,12,1,1),'Ident (Prolog Variable)').
'symbol'('w','w','src_span'(10,18,10,19,1,1),'Ident (Prolog Variable)').
'symbol'('x','x','src_span'(3,4,3,5,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(5,8,5,9,1,1),'Ident (Prolog Variable)').
'symbol'('x3','x','src_span'(13,23,13,24,1,1),'Ident (Prolog Variable)').
'symbol'('x4','x','src_span'(37,11,37,12,1,1),'Ident (Prolog Variable)').
'symbol'('y','y','src_span'(3,6,3,7,1,1),'Ident (Prolog Variable)').
'symbol'('y2','y','src_span'(5,10,5,11,1,1),'Ident (Prolog Variable)').
'symbol'('y3','y','src_span'(24,13,24,14,1,1),'Ident (Prolog Variable)').
'symbol'('y4','y','src_span'(31,14,31,15,1,1),'Ident (Prolog Variable)').
'symbol'('y5','y','src_span'(37,19,37,20,1,1),'Ident (Prolog Variable)').
'symbol'('z','z','src_span'(3,8,3,9,1,1),'Ident (Prolog Variable)').
'symbol'('z2','z','src_span'(5,12,5,13,1,1),'Ident (Prolog Variable)').

