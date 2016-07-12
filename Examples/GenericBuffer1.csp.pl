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
'bindval'('MyIntL','int'(0),'int'(999),'src_span'(2,1,2,18,17,17)).
'bindval'('MyInt','int'(0),'int'(9),'src_span'(3,1,3,15,14,14)).
'dataTypeDef'('FRUIT',['constructor'('apples'),'constructor'('oranges'),'constructor'('pears')]).
'channel'('left','type'('dotTupleType'(['val_of'('FRUIT','src_span'(7,25,7,30,5,5))]))).
'channel'('right','type'('dotTupleType'(['val_of'('FRUIT','src_span'(7,25,7,30,5,5))]))).
'channel'('mid','type'('dotTupleType'(['val_of'('FRUIT','src_span'(7,25,7,30,5,5))]))).
'channel'('ack','type'('dotUnitType')).
'agent'('FRUITGEN'(_ch),'prefix'('src_span'(11,16,11,18,2,2),['out'('apples')],_ch,'prefix'('src_span'(11,29,11,31,2,2),['out'('oranges')],_ch,'prefix'('src_span'(11,43,11,45,2,2),['out'('pears')],_ch,'agent_call'('src_span'(11,55,11,67,12,12),'FRUITGEN',[_ch]),'src_span'(11,51,11,55,4,4)),'src_span'(11,39,11,43,4,4)),'src_span'(11,25,11,29,4,4)),'src_span'(11,1,11,67,66,66)).
'agent'('BUFF'(_in,_out),'prefix'('src_span'(13,16,13,18,2,2),['in'(_x)],_in,'prefix'('src_span'(13,24,13,27,3,3),['out'(_x)],_out,'agent_call'('src_span'(13,33,13,45,12,12),'BUFF',[_in,_out]),'src_span'(13,29,13,33,4,4)),'src_span'(13,20,13,24,4,4)),'src_span'(13,1,13,45,44,44)).
'bindval'('SYSTEM','sharing'('agent_call'('src_span'(16,10,16,26,16,16),'BUFF',['left','right']),'left','agent_call'('src_span'(16,44,16,58,14,14),'FRUITGEN',['left']),'src_span'(16,26,16,30,4,4)),'src_span'(16,1,16,58,57,57)).
'bindval'('MAIN',';'(';'('val_of'('Testiii','src_span'(17,8,17,15,7,7)),'val_of'('TestNrGen2','src_span'(17,18,17,28,10,10)),'src_span_operator'('no_loc_info_available','src_span'(17,15,17,18,3,3))),'val_of'('SYSTEM','src_span'(17,31,17,37,6,6)),'src_span_operator'('no_loc_info_available','src_span'(17,28,17,31,3,3))),'src_span'(17,1,17,37,36,36)).
'channel'('ii','type'('dotTupleType'(['val_of'('MyInt','src_span'(19,19,19,24,5,5)),'val_of'('MyInt','src_span'(19,25,19,30,5,5))]))).
'channel'('oo','type'('dotTupleType'(['val_of'('MyInt','src_span'(19,19,19,24,5,5)),'val_of'('MyInt','src_span'(19,25,19,30,5,5))]))).
'channel'('tt','type'('dotTupleType'(['val_of'('MyInt','src_span'(19,19,19,24,5,5)),'val_of'('MyInt','src_span'(19,25,19,30,5,5))]))).
'agent'('NRGEN'(_ch2),'prefix'('src_span'(21,13,21,15,2,2),['out'('int'(1)),'out'('int'(1))],_ch2,'prefix'('src_span'(21,23,21,25,2,2),['out'('int'(2)),'out'('int'(4))],_ch2,'prefix'('src_span'(21,33,21,35,2,2),['out'('int'(3)),'out'('int'(6))],_ch2,'agent_call'('src_span'(21,43,21,52,9,9),'NRGEN',[_ch2]),'src_span'(21,39,21,43,4,4)),'src_span'(21,29,21,33,4,4)),'src_span'(21,19,21,23,4,4)),'src_span'(21,1,21,52,51,51)).
'agent'('NRGENFinite'(_ch3),'prefix'('src_span'(22,19,22,21,2,2),['out'('int'(1)),'out'('int'(1))],_ch3,'prefix'('src_span'(22,29,22,31,2,2),['out'('int'(2)),'out'('int'(4))],_ch3,'prefix'('src_span'(22,39,22,41,2,2),['out'('int'(3)),'out'('int'(6))],_ch3,'skip'('src_span'(22,49,22,53,4,4)),'src_span'(22,45,22,49,4,4)),'src_span'(22,35,22,39,4,4)),'src_span'(22,25,22,29,4,4)),'src_span'(22,1,22,53,52,52)).
'bindval'('TEST','sharing'('agent_call'('src_span'(24,9,24,20,11,11),'BUFF',['ii','oo']),'ii','agent_call'('src_span'(24,36,24,45,9,9),'NRGEN',['ii']),'src_span'(24,20,24,24,4,4)),'src_span'(24,1,24,45,44,44)).
'bindval'('NRGENii','prefix'('src_span'(26,11,26,13,2,2),['out'('int'(1)),'out'('int'(1))],'ii','prefix'('src_span'(26,21,26,23,2,2),['out'('int'(2)),'out'('int'(4))],'ii','prefix'('src_span'(26,31,26,33,2,2),['out'('int'(3)),'out'('int'(6))],'ii','val_of'('NRGENii','src_span'(26,41,26,48,7,7)),'src_span'(26,37,26,41,4,4)),'src_span'(26,27,26,31,4,4)),'src_span'(26,17,26,21,4,4)),'src_span'(26,1,26,48,47,47)).
'bindval'('MYCHANNEL','ii','src_span'(28,1,28,15,14,14)).
'bindval'('TestNrGen','agent_call'('src_span'(29,13,29,29,16,16),'NRGEN',['val_of'('MYCHANNEL','src_span'(29,19,29,28,9,9))]),'src_span'(29,1,29,29,28,28)).
'agent'('MyChannel'(_x2),'ifte'(_x2,'int'(0),'ii','oo','src_span'(30,16,30,18,2,2),'src_span'(30,22,30,28,6,6),'src_span'(30,30,30,36,6,6)),'src_span'(30,1,30,38,37,37)).
'bindval'('TestNrGen2','agent_call'('src_span'(31,14,31,39,25,25),'NRGENFinite',['agent_call'('src_span'(31,26,31,38,12,12),'MyChannel',['int'(1)])]),'src_span'(31,1,31,39,38,38)).
'bindval'('BUFFii','prefix'('src_span'(34,10,34,12,2,2),['in'(_x3)],'ii','prefix'('src_span'(34,18,34,20,2,2),['out'(_x3)],'oo','prefix'('src_span'(34,26,34,28,2,2),['out'(_x3)],'tt','val_of'('BUFFii','src_span'(34,34,34,40,6,6)),'src_span'(34,30,34,34,4,4)),'src_span'(34,22,34,26,4,4)),'src_span'(34,14,34,18,4,4)),'src_span'(34,1,34,40,39,39)).
'bindval'('TESTii','sharing'('val_of'('BUFFii','src_span'(35,11,35,17,6,6)),'ii','val_of'('NRGENii','src_span'(35,33,35,40,7,7)),'src_span'(35,17,35,21,4,4)),'src_span'(35,1,35,40,39,39)).
'channel'('iii','type'('dotTupleType'(['val_of'('MyIntL','src_span'(38,17,38,23,6,6)),'val_of'('MyInt','src_span'(38,24,38,29,5,5)),'val_of'('MyInt','src_span'(38,30,38,35,5,5))]))).
'channel'('ooo','type'('dotTupleType'(['val_of'('MyIntL','src_span'(38,17,38,23,6,6)),'val_of'('MyInt','src_span'(38,24,38,29,5,5)),'val_of'('MyInt','src_span'(38,30,38,35,5,5))]))).
'bindval'('Testiii','sharing'('agent_call'('src_span'(39,11,39,31,20,20),'NRGENFinite',['dotTuple'(['iii','int'(999)])]),'iii','prefix'('src_span'(39,48,39,51,3,3),['in'(_z1)],'iii','prefix'('src_span'(39,58,39,61,3,3),['in'(_z2)],'iii','prefix'('src_span'(39,68,39,71,3,3),['in'(_z3)],'iii','agent_call'('src_span'(39,78,39,94,16,16),'Outooo',[_z1,_z2,_z3]),'src_span'(39,74,39,78,4,4)),'src_span'(39,64,39,68,4,4)),'src_span'(39,54,39,58,4,4)),'src_span'(39,31,39,35,4,4)),'src_span'(39,1,39,94,93,93)).
'agent'('Outooo'(_x4,_y,_z),'prefix'('src_span'(41,17,41,20,3,3),['out'(_x4)],'ooo','prefix'('src_span'(41,26,41,29,3,3),['out'(_y)],'ooo','prefix'('src_span'(41,35,41,38,3,3),['out'(_z)],'ooo','skip'('src_span'(41,44,41,48,4,4)),'src_span'(41,40,41,44,4,4)),'src_span'(41,31,41,35,4,4)),'src_span'(41,22,41,26,4,4)),'src_span'(41,1,41,48,47,47)).
'bindval'('PROB_TEST_TRACE','prefix'('src_span'(45,19,45,30,11,11),[],'dotTuple'(['iii','int'(999),'int'(1),'int'(1)]),'prefix'('src_span'(45,34,45,45,11,11),[],'dotTuple'(['iii','int'(999),'int'(2),'int'(4)]),'prefix'('src_span'(45,49,45,60,11,11),[],'dotTuple'(['iii','int'(999),'int'(3),'int'(6)]),'prefix'('src_span'(45,64,45,75,11,11),[],'dotTuple'(['ooo','int'(999),'int'(1),'int'(1)]),'prefix'('src_span'(45,79,45,90,11,11),[],'dotTuple'(['ooo','int'(999),'int'(2),'int'(4)]),'prefix'('src_span'(45,94,45,105,11,11),[],'dotTuple'(['ooo','int'(999),'int'(3),'int'(6)]),'prefix'('src_span'(45,109,45,115,6,6),[],'dotTuple'(['ii','int'(1),'int'(1)]),'prefix'('src_span'(45,119,45,125,6,6),[],'dotTuple'(['ii','int'(2),'int'(4)]),'prefix'('src_span'(45,129,45,135,6,6),[],'dotTuple'(['ii','int'(3),'int'(6)]),'prefix'('src_span'(45,139,45,150,11,11),[],'dotTuple'(['left','apples']),'prefix'('src_span'(45,154,45,166,12,12),[],'dotTuple'(['right','apples']),'prefix'('src_span'(45,170,45,182,12,12),[],'dotTuple'(['left','oranges']),'prefix'('src_span'(45,186,45,199,13,13),[],'dotTuple'(['right','oranges']),'prefix'('src_span'(45,203,45,213,10,10),[],'dotTuple'(['left','pears']),'prefix'('src_span'(45,217,45,228,11,11),[],'dotTuple'(['right','pears']),'prefix'('src_span'(45,232,45,243,11,11),[],'dotTuple'(['left','apples']),'prefix'('src_span'(45,247,45,259,12,12),[],'dotTuple'(['right','apples']),'prefix'('src_span'(45,263,45,275,12,12),[],'dotTuple'(['left','oranges']),'prefix'('src_span'(45,279,45,292,13,13),[],'dotTuple'(['right','oranges']),'stop'('src_span'(45,296,45,300,4,4)),'src_span'(45,292,45,296,4,4)),'src_span'(45,275,45,279,4,4)),'src_span'(45,259,45,263,4,4)),'src_span'(45,243,45,247,4,4)),'src_span'(45,228,45,232,4,4)),'src_span'(45,213,45,217,4,4)),'src_span'(45,199,45,203,4,4)),'src_span'(45,182,45,186,4,4)),'src_span'(45,166,45,170,4,4)),'src_span'(45,150,45,154,4,4)),'src_span'(45,135,45,139,4,4)),'src_span'(45,125,45,129,4,4)),'src_span'(45,115,45,119,4,4)),'src_span'(45,105,45,109,4,4)),'src_span'(45,90,45,94,4,4)),'src_span'(45,75,45,79,4,4)),'src_span'(45,60,45,64,4,4)),'src_span'(45,45,45,49,4,4)),'src_span'(45,30,45,34,4,4)),'src_span'(45,1,45,300,299,299)).
'val_of'('MAIN','src_span'(47,8,47,12,4,4)),'val_of'('PROB_TEST_TRACE','src_span'(47,17,47,32,15,15)).
'symbol'('SYSTEM','SYSTEM','src_span'(16,1,16,7,6,6),'Ident (Groundrep.)').
'symbol'('tt','tt','src_span'(19,15,19,17,2,2),'Channel').
'symbol'('MYCHANNEL','MYCHANNEL','src_span'(28,1,28,10,9,9),'Ident (Groundrep.)').
'symbol'('ack','ack','src_span'(8,9,8,12,3,3),'Channel').
'symbol'('iii','iii','src_span'(38,9,38,12,3,3),'Channel').
'symbol'('mid','mid','src_span'(7,20,7,23,3,3),'Channel').
'symbol'('BUFF','BUFF','src_span'(13,1,13,5,4,4),'Function or Process').
'symbol'('TestNrGen2','TestNrGen2','src_span'(31,1,31,11,10,10),'Ident (Groundrep.)').
'symbol'('NRGENFinite','NRGENFinite','src_span'(22,1,22,12,11,11),'Function or Process').
'symbol'('ooo','ooo','src_span'(38,13,38,16,3,3),'Channel').
'symbol'('out','out','src_span'(13,9,13,12,3,3),'Ident (Prolog Variable)').
'symbol'('NRGENii','NRGENii','src_span'(26,1,26,8,7,7),'Ident (Groundrep.)').
'symbol'('MyChannel','MyChannel','src_span'(30,1,30,10,9,9),'Function or Process').
'symbol'('PROB_TEST_TRACE','PROB_TEST_TRACE','src_span'(45,1,45,16,15,15),'Ident (Groundrep.)').
'symbol'('pears','pears','src_span'(5,37,5,42,5,5),'Constructor of Datatype').
'symbol'('Outooo','Outooo','src_span'(41,1,41,7,6,6),'Function or Process').
'symbol'('MyIntL','MyIntL','src_span'(2,1,2,7,6,6),'Ident (Groundrep.)').
'symbol'('MAIN','MAIN','src_span'(17,1,17,5,4,4),'Ident (Groundrep.)').
'symbol'('NRGEN','NRGEN','src_span'(21,1,21,6,5,5),'Function or Process').
'symbol'('BUFFii','BUFFii','src_span'(34,1,34,7,6,6),'Ident (Groundrep.)').
'symbol'('ii','ii','src_span'(19,9,19,11,2,2),'Channel').
'symbol'('oo','oo','src_span'(19,12,19,14,2,2),'Channel').
'symbol'('oranges','oranges','src_span'(5,27,5,34,7,7),'Constructor of Datatype').
'symbol'('ch','ch','src_span'(11,10,11,12,2,2),'Ident (Prolog Variable)').
'symbol'('ch2','ch','src_span'(21,7,21,9,2,2),'Ident (Prolog Variable)').
'symbol'('ch3','ch','src_span'(22,13,22,15,2,2),'Ident (Prolog Variable)').
'symbol'('in','in','src_span'(13,6,13,8,2,2),'Ident (Prolog Variable)').
'symbol'('right','right','src_span'(7,14,7,19,5,5),'Channel').
'symbol'('TestNrGen','TestNrGen','src_span'(29,1,29,10,9,9),'Ident (Groundrep.)').
'symbol'('FRUIT','FRUIT','src_span'(5,10,5,15,5,5),'Datatype').
'symbol'('left','left','src_span'(7,9,7,13,4,4),'Channel').
'symbol'('FRUITGEN','FRUITGEN','src_span'(11,1,11,9,8,8),'Function or Process').
'symbol'('TEST','TEST','src_span'(24,1,24,5,4,4),'Ident (Groundrep.)').
'symbol'('MyInt','MyInt','src_span'(3,1,3,6,5,5),'Ident (Groundrep.)').
'symbol'('z1','z1','src_span'(39,52,39,54,2,2),'Ident (Prolog Variable)').
'symbol'('x','x','src_span'(13,19,13,20,1,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(30,11,30,12,1,1),'Ident (Prolog Variable)').
'symbol'('x3','x','src_span'(34,13,34,14,1,1),'Ident (Prolog Variable)').
'symbol'('x4','x','src_span'(41,8,41,9,1,1),'Ident (Prolog Variable)').
'symbol'('TESTii','TESTii','src_span'(35,1,35,7,6,6),'Ident (Groundrep.)').
'symbol'('Testiii','Testiii','src_span'(39,1,39,8,7,7),'Ident (Groundrep.)').
'symbol'('z2','z2','src_span'(39,62,39,64,2,2),'Ident (Prolog Variable)').
'symbol'('z3','z3','src_span'(39,72,39,74,2,2),'Ident (Prolog Variable)').
'symbol'('y','y','src_span'(41,10,41,11,1,1),'Ident (Prolog Variable)').
'symbol'('z','z','src_span'(41,12,41,13,1,1),'Ident (Prolog Variable)').
'symbol'('apples','apples','src_span'(5,18,5,24,6,6),'Constructor of Datatype').

