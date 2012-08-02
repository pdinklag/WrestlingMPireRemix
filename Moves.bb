;//////////////////////////////////////////////////////////////////////////////
;--------------------- WRESTLING MPIRE 2008: STANDING MOVES -------------------
;//////////////////////////////////////////////////////////////////////////////

;---------------------------------------------------------------------
;/////////////////// STANDING MOVE SEQUENCES /////////////////////////
;---------------------------------------------------------------------
Function LoadMoveSequences(cyc)
 ;grappling
 pSeq(cyc,300)=ExtractAnimSeq(p(cyc),1995,2050,pSeq(cyc,1007)) ;lunge
 pSeq(cyc,301)=ExtractAnimSeq(p(cyc),2145,2205,pSeq(cyc,1007)) ;tie-up (aggressor) (forward start)
 pSeq(cyc,302)=ExtractAnimSeq(p(cyc),2425,2485,pSeq(cyc,1008)) ;tie-up (victim) (forward start)
 pSeq(cyc,303)=ExtractAnimSeq(p(cyc),1910,1970,pSeq(cyc,1007)) ;tie-up movement (aggressor)
 pSeq(cyc,304)=ExtractAnimSeq(p(cyc),1320,1380,pSeq(cyc,1008)) ;tie-up movement (victim)
 pSeq(cyc,305)=ExtractAnimSeq(p(cyc),1840,1900,pSeq(cyc,1007)) ;tie-up (aggressor) (original start)
 pSeq(cyc,306)=ExtractAnimSeq(p(cyc),1390,1450,pSeq(cyc,1008)) ;tie-up (victim) (original start)
 ;irish whip
 pSeq(cyc,310)=ExtractAnimSeq(p(cyc),70,180,pSeq(cyc,1020)) ;[execute]
 pSeq(cyc,311)=ExtractAnimSeq(p(cyc),70,135,pSeq(cyc,1021)) ;[receive]
 ;force out of ring
 pSeq(cyc,312)=ExtractAnimSeq(p(cyc),190,320,pSeq(cyc,1020)) ;[execute]
 pSeq(cyc,313)=ExtractAnimSeq(p(cyc),190,320,pSeq(cyc,1021)) ;[receive]
 ;force into ring
 pSeq(cyc,314)=ExtractAnimSeq(p(cyc),330,430,pSeq(cyc,1020)) ;[execute]
 pSeq(cyc,315)=ExtractAnimSeq(p(cyc),330,430,pSeq(cyc,1021)) ;[receive]
 ;drag out from apron
 pSeq(cyc,316)=ExtractAnimSeq(p(cyc),440,565,pSeq(cyc,1020)) ;[execute]
 pSeq(cyc,317)=ExtractAnimSeq(p(cyc),440,565,pSeq(cyc,1021)) ;[receive]
 ;drag in from apron
 pSeq(cyc,318)=ExtractAnimSeq(p(cyc),575,690,pSeq(cyc,1020)) ;[execute]
 pSeq(cyc,319)=ExtractAnimSeq(p(cyc),575,690,pSeq(cyc,1021)) ;[receive]
 ;drag down from platform
 pSeq(cyc,320)=ExtractAnimSeq(p(cyc),700,805,pSeq(cyc,1020)) ;[execute]
 pSeq(cyc,321)=ExtractAnimSeq(p(cyc),700,805,pSeq(cyc,1021)) ;[receive]
 ;bodyslam
 pSeq(cyc,322)=ExtractAnimSeq(p(cyc),815,960,pSeq(cyc,1020)) ;[execute]
 pSeq(cyc,323)=ExtractAnimSeq(p(cyc),815,960,pSeq(cyc,1021)) ;[receive]
 ;suplex
 If MoveRequired(cyc,317,1) Then pSeq(cyc,324)=ExtractAnimSeq(p(cyc),70,240,pSeq(cyc,1022)) ;[execute]
 If MoveRequired(cyc,317,-1) Then pSeq(cyc,325)=ExtractAnimSeq(p(cyc),70,240,pSeq(cyc,1023)) ;[receive]
 ;snap suplex
 If MoveRequired(cyc,318,1) Then pSeq(cyc,326)=ExtractAnimSeq(p(cyc),250,375,pSeq(cyc,1022)) ;[execute]
 If MoveRequired(cyc,318,-1) Then pSeq(cyc,327)=ExtractAnimSeq(p(cyc),250,375,pSeq(cyc,1023)) ;[receive]
 ;stalling suplex
 If MoveRequired(cyc,319,1) Then pSeq(cyc,328)=ExtractAnimSeq(p(cyc),385,635,pSeq(cyc,1022)) ;[execute]
 If MoveRequired(cyc,319,-1) Then pSeq(cyc,329)=ExtractAnimSeq(p(cyc),385,635,pSeq(cyc,1023)) ;[receive]
 ;brainbuster
 If MoveRequired(cyc,320,1) Then pSeq(cyc,330)=ExtractAnimSeq(p(cyc),645,810,pSeq(cyc,1022)) ;[execute]
 If MoveRequired(cyc,320,-1) Then pSeq(cyc,331)=ExtractAnimSeq(p(cyc),645,810,pSeq(cyc,1023)) ;[receive]
 ;stalling brainbuster
 If MoveRequired(cyc,321,1) Then pSeq(cyc,332)=ExtractAnimSeq(p(cyc),820,1070,pSeq(cyc,1022)) ;[execute]
 If MoveRequired(cyc,321,-1) Then pSeq(cyc,333)=ExtractAnimSeq(p(cyc),820,1070,pSeq(cyc,1023)) ;[receive]
 ;jackhammer
 If MoveRequired(cyc,322,1) Then pSeq(cyc,334)=ExtractAnimSeq(p(cyc),1080,1335,pSeq(cyc,1022)) ;[execute]
 If MoveRequired(cyc,322,-1) Then pSeq(cyc,335)=ExtractAnimSeq(p(cyc),1080,1335,pSeq(cyc,1023)) ;[receive]
 ;suplex drop
 If MoveRequired(cyc,323,1) Then pSeq(cyc,336)=ExtractAnimSeq(p(cyc),1345,1520,pSeq(cyc,1022)) ;[execute]
 If MoveRequired(cyc,323,-1) Then pSeq(cyc,337)=ExtractAnimSeq(p(cyc),1345,1520,pSeq(cyc,1023)) ;[receive]
 ;sleeper hold
 If MoveRequired(cyc,324,1) Then pSeq(cyc,338)=ExtractAnimSeq(p(cyc),1530,1580,pSeq(cyc,1022)) ;[apply execute]
 If MoveRequired(cyc,324,-1) Then pSeq(cyc,339)=ExtractAnimSeq(p(cyc),1530,1580,pSeq(cyc,1023)) ;[apply receive]
 If MoveRequired(cyc,324,1) Then pSeq(cyc,340)=ExtractAnimSeq(p(cyc),1580,1620,pSeq(cyc,1022)) ;[wrench execute]
 If MoveRequired(cyc,324,-1) Then pSeq(cyc,341)=ExtractAnimSeq(p(cyc),1580,1620,pSeq(cyc,1023)) ;[wrench receive]
 ;headlock
 If MoveRequired(cyc,325,1) Then pSeq(cyc,342)=ExtractAnimSeq(p(cyc),70,120,pSeq(cyc,1024)) ;[apply execute]
 If MoveRequired(cyc,325,-1) Then pSeq(cyc,343)=ExtractAnimSeq(p(cyc),70,120,pSeq(cyc,1025)) ;[apply receive]
 If MoveRequired(cyc,325,1) Then pSeq(cyc,344)=ExtractAnimSeq(p(cyc),120,160,pSeq(cyc,1024)) ;[wrench execute]
 If MoveRequired(cyc,325,-1) Then pSeq(cyc,345)=ExtractAnimSeq(p(cyc),120,160,pSeq(cyc,1025)) ;[wrench receive]
 ;headlock punch
 If MoveRequired(cyc,326,1) Then pSeq(cyc,346)=ExtractAnimSeq(p(cyc),170,345,pSeq(cyc,1024)) ;[execute]
 If MoveRequired(cyc,326,-1) Then pSeq(cyc,347)=ExtractAnimSeq(p(cyc),170,345,pSeq(cyc,1025)) ;[receive]
 ;bulldog
 If MoveRequired(cyc,327,1) Then pSeq(cyc,348)=ExtractAnimSeq(p(cyc),355,525,pSeq(cyc,1024)) ;[execute]
 If MoveRequired(cyc,327,-1) Then pSeq(cyc,349)=ExtractAnimSeq(p(cyc),355,525,pSeq(cyc,1025)) ;[receive]
 ;headlock takedown
 If MoveRequired(cyc,328,1) Then pSeq(cyc,350)=ExtractAnimSeq(p(cyc),535,660,pSeq(cyc,1024)) ;[execute]
 If MoveRequired(cyc,328,-1) Then pSeq(cyc,351)=ExtractAnimSeq(p(cyc),535,660,pSeq(cyc,1025)) ;[receive]
 ;pumping press slam
 If MoveRequired(cyc,329,1) Then pSeq(cyc,352)=ExtractAnimSeq(p(cyc),970,1270,pSeq(cyc,1020)) ;[execute]
 If MoveRequired(cyc,329,-1) Then pSeq(cyc,353)=ExtractAnimSeq(p(cyc),970,1270,pSeq(cyc,1021)) ;[receive]
 ;press slam
 If MoveRequired(cyc,330,1) Then pSeq(cyc,354)=ExtractAnimSeq(p(cyc),1280,1470,pSeq(cyc,1020)) ;[execute]
 If MoveRequired(cyc,330,-1) Then pSeq(cyc,355)=ExtractAnimSeq(p(cyc),1280,1470,pSeq(cyc,1021)) ;[receive]
 ;gorilla press slam
 If MoveRequired(cyc,331,1) Then pSeq(cyc,356)=ExtractAnimSeq(p(cyc),1480,1705,pSeq(cyc,1020)) ;[execute]
 If MoveRequired(cyc,331,-1) Then pSeq(cyc,357)=ExtractAnimSeq(p(cyc),1480,1705,pSeq(cyc,1021)) ;[receive]
 ;powerslam
 If MoveRequired(cyc,332,1) Then pSeq(cyc,358)=ExtractAnimSeq(p(cyc),1715,1835,pSeq(cyc,1020)) ;[execute]
 If MoveRequired(cyc,332,-1) Then pSeq(cyc,359)=ExtractAnimSeq(p(cyc),1715,1835,pSeq(cyc,1021)) ;[receive]
 ;shoulder powerslam
 If MoveRequired(cyc,333,1) Then pSeq(cyc,360)=ExtractAnimSeq(p(cyc),1845,2010,pSeq(cyc,1020)) ;[execute]
 If MoveRequired(cyc,333,-1) Then pSeq(cyc,361)=ExtractAnimSeq(p(cyc),1845,2010,pSeq(cyc,1021)) ;[receive]
 ;shoulder breaker
 If MoveRequired(cyc,334,1) Then pSeq(cyc,362)=ExtractAnimSeq(p(cyc),2020,2210,pSeq(cyc,1020)) ;[execute]
 If MoveRequired(cyc,334,-1) Then pSeq(cyc,363)=ExtractAnimSeq(p(cyc),2020,2210,pSeq(cyc,1021)) ;[receive]
 ;tombstone piledriver
 If MoveRequired(cyc,335,1) Then pSeq(cyc,364)=ExtractAnimSeq(p(cyc),2220,2415,pSeq(cyc,1020)) ;[execute]
 If MoveRequired(cyc,335,-1) Then pSeq(cyc,365)=ExtractAnimSeq(p(cyc),2220,2415,pSeq(cyc,1021)) ;[receive]
 ;inverted piledriver
 If MoveRequired(cyc,336,1) Then pSeq(cyc,366)=ExtractAnimSeq(p(cyc),2425,2645,pSeq(cyc,1020)) ;[execute]
 If MoveRequired(cyc,336,-1) Then pSeq(cyc,367)=ExtractAnimSeq(p(cyc),2425,2645,pSeq(cyc,1021)) ;[receive]
 ;jumping bodyslam
 If MoveRequired(cyc,337,1) Then pSeq(cyc,368)=ExtractAnimSeq(p(cyc),2655,2865,pSeq(cyc,1020)) ;[execute]
 If MoveRequired(cyc,337,-1) Then pSeq(cyc,369)=ExtractAnimSeq(p(cyc),2655,2865,pSeq(cyc,1021)) ;[receive]
 ;piledriver
 If MoveRequired(cyc,338,1) Then pSeq(cyc,370)=ExtractAnimSeq(p(cyc),70,270,pSeq(cyc,1026)) ;[execute]
 If MoveRequired(cyc,338,-1) Then pSeq(cyc,371)=ExtractAnimSeq(p(cyc),70,270,pSeq(cyc,1027)) ;[receive]
 ;powerbomb
 If MoveRequired(cyc,339,1) Then pSeq(cyc,372)=ExtractAnimSeq(p(cyc),280,455,pSeq(cyc,1026)) ;[execute]
 If MoveRequired(cyc,339,-1) Then pSeq(cyc,373)=ExtractAnimSeq(p(cyc),280,455,pSeq(cyc,1027)) ;[receive]
 ;sitting powerbomb
 If MoveRequired(cyc,340,1) Then pSeq(cyc,374)=ExtractAnimSeq(p(cyc),465,690,pSeq(cyc,1026)) ;[execute]
 If MoveRequired(cyc,340,-1) Then pSeq(cyc,375)=ExtractAnimSeq(p(cyc),465,690,pSeq(cyc,1027)) ;[receive]
 ;underhook facebuster (pedigree)
 If MoveRequired(cyc,341,1) Then pSeq(cyc,376)=ExtractAnimSeq(p(cyc),700,895,pSeq(cyc,1026)) ;[execute]
 If MoveRequired(cyc,341,-1) Then pSeq(cyc,377)=ExtractAnimSeq(p(cyc),700,895,pSeq(cyc,1027)) ;[receive]
 ;hurricanranna
 If MoveRequired(cyc,342,1) Then pSeq(cyc,378)=ExtractAnimSeq(p(cyc),70,240,pSeq(cyc,1028)) ;[execute]
 If MoveRequired(cyc,342,-1) Then pSeq(cyc,379)=ExtractAnimSeq(p(cyc),70,240,pSeq(cyc,1029)) ;[receive]
 ;leaping plancha
 If MoveRequired(cyc,343,1) Then pSeq(cyc,380)=ExtractAnimSeq(p(cyc),250,400,pSeq(cyc,1028)) ;[execute]
 If MoveRequired(cyc,343,-1) Then pSeq(cyc,381)=ExtractAnimSeq(p(cyc),250,400,pSeq(cyc,1029)) ;[receive]
 ;flying head scissors
 If MoveRequired(cyc,344,1) Then pSeq(cyc,382)=ExtractAnimSeq(p(cyc),410,585,pSeq(cyc,1028)) ;[execute]
 If MoveRequired(cyc,344,-1) Then pSeq(cyc,383)=ExtractAnimSeq(p(cyc),410,585,pSeq(cyc,1029)) ;[receive]
 ;small package
 pSeq(cyc,384)=ExtractAnimSeq(p(cyc),670,735,pSeq(cyc,1024)) ;apply [execute]
 pSeq(cyc,385)=ExtractAnimSeq(p(cyc),670,735,pSeq(cyc,1025)) ;apply [receive]
 pSeq(cyc,386)=ExtractAnimSeq(p(cyc),735,775,pSeq(cyc,1024)) ;hold [execute]
 pSeq(cyc,387)=ExtractAnimSeq(p(cyc),735,775,pSeq(cyc,1025)) ;hold [receive]
 pSeq(cyc,388)=ExtractAnimSeq(p(cyc),775,835,pSeq(cyc,1024)) ;hold [execute]
 pSeq(cyc,389)=ExtractAnimSeq(p(cyc),775,835,pSeq(cyc,1025)) ;hold [receive]
 ;belly-to-belly suplex
 pSeq(cyc,390)=ExtractAnimSeq(p(cyc),70,215,pSeq(cyc,1030)) ;[execute]
 pSeq(cyc,391)=ExtractAnimSeq(p(cyc),70,215,pSeq(cyc,1031)) ;[receive]
 ;belly-to-belly slam
 If MoveRequired(cyc,347,1) Then pSeq(cyc,392)=ExtractAnimSeq(p(cyc),225,350,pSeq(cyc,1030)) ;[execute]
 If MoveRequired(cyc,347,-1) Then pSeq(cyc,393)=ExtractAnimSeq(p(cyc),225,350,pSeq(cyc,1031)) ;[receive]
 ;northern lights suplex
 If MoveRequired(cyc,348,1) Then pSeq(cyc,394)=ExtractAnimSeq(p(cyc),360,475,pSeq(cyc,1030)) ;[execute]
 If MoveRequired(cyc,348,-1) Then pSeq(cyc,395)=ExtractAnimSeq(p(cyc),360,475,pSeq(cyc,1031)) ;[receive]
 ;back body drop
 If MoveRequired(cyc,349,1) Then pSeq(cyc,396)=ExtractAnimSeq(p(cyc),485,645,pSeq(cyc,1030)) ;[execute]
 If MoveRequired(cyc,349,-1) Then pSeq(cyc,397)=ExtractAnimSeq(p(cyc),485,645,pSeq(cyc,1031)) ;[receive]
 ;spinebuster
 If MoveRequired(cyc,350,1) Then pSeq(cyc,398)=ExtractAnimSeq(p(cyc),655,770,pSeq(cyc,1030)) ;[execute]
 If MoveRequired(cyc,350,-1) Then pSeq(cyc,399)=ExtractAnimSeq(p(cyc),655,770,pSeq(cyc,1031)) ;[receive]
 ;side slam
 If MoveRequired(cyc,351,1) Then pSeq(cyc,400)=ExtractAnimSeq(p(cyc),700,840,pSeq(cyc,1070)) ;[execute]
 If MoveRequired(cyc,351,-1) Then pSeq(cyc,401)=ExtractAnimSeq(p(cyc),700,840,pSeq(cyc,1071)) ;[receive]
 ;side backbreaker
 If MoveRequired(cyc,352,1) Then pSeq(cyc,402)=ExtractAnimSeq(p(cyc),850,990,pSeq(cyc,1070)) ;[execute]
 If MoveRequired(cyc,352,-1) Then pSeq(cyc,403)=ExtractAnimSeq(p(cyc),850,990,pSeq(cyc,1071)) ;[receive]
 ;inverted atomic drop
 If MoveRequired(cyc,353,1) Then pSeq(cyc,404)=ExtractAnimSeq(p(cyc),780,940,pSeq(cyc,1030)) ;[execute]
 If MoveRequired(cyc,353,-1) Then pSeq(cyc,405)=ExtractAnimSeq(p(cyc),780,940,pSeq(cyc,1031)) ;[receive]
 ;backbreaker
 If MoveRequired(cyc,354,1) Then pSeq(cyc,406)=ExtractAnimSeq(p(cyc),2875,3025,pSeq(cyc,1020)) ;[execute]
 If MoveRequired(cyc,354,-1) Then pSeq(cyc,407)=ExtractAnimSeq(p(cyc),2875,3025,pSeq(cyc,1021)) ;[receive]
 ;stunner
 If MoveRequired(cyc,355,1) Then pSeq(cyc,408)=ExtractAnimSeq(p(cyc),845,975,pSeq(cyc,1024)) ;[execute]
 If MoveRequired(cyc,355,-1) Then pSeq(cyc,409)=ExtractAnimSeq(p(cyc),845,975,pSeq(cyc,1025)) ;[receive]
 ;rock bottom
 If MoveRequired(cyc,356,1) Then pSeq(cyc,410)=ExtractAnimSeq(p(cyc),950,1085,pSeq(cyc,1030)) ;[execute]
 If MoveRequired(cyc,356,-1) Then pSeq(cyc,411)=ExtractAnimSeq(p(cyc),950,1085,pSeq(cyc,1031)) ;[receive]
 ;german suplex
 If MoveRequired(cyc,357,1) Then pSeq(cyc,412)=ExtractAnimSeq(p(cyc),595,785,pSeq(cyc,1028)) ;[execute]
 If MoveRequired(cyc,357,-1) Then pSeq(cyc,413)=ExtractAnimSeq(p(cyc),595,785,pSeq(cyc,1029)) ;[receive]
 ;back suplex
 If MoveRequired(cyc,358,1) Then pSeq(cyc,414)=ExtractAnimSeq(p(cyc),795,985,pSeq(cyc,1028)) ;[execute]
 If MoveRequired(cyc,358,-1) Then pSeq(cyc,415)=ExtractAnimSeq(p(cyc),795,985,pSeq(cyc,1029)) ;[receive]
 ;atomic drop
 If MoveRequired(cyc,359,1) Then pSeq(cyc,416)=ExtractAnimSeq(p(cyc),995,1230,pSeq(cyc,1028)) ;[execute]
 If MoveRequired(cyc,359,-1) Then pSeq(cyc,417)=ExtractAnimSeq(p(cyc),995,1230,pSeq(cyc,1029)) ;[receive]
 ;drop toe hold
 If MoveRequired(cyc,360,1) Then pSeq(cyc,418)=ExtractAnimSeq(p(cyc),1240,1370,pSeq(cyc,1028)) ;[execute]
 If MoveRequired(cyc,360,-1) Then pSeq(cyc,419)=ExtractAnimSeq(p(cyc),1240,1370,pSeq(cyc,1029)) ;[receive]
 ;samoan drop
 If MoveRequired(cyc,361,1) Then pSeq(cyc,420)=ExtractAnimSeq(p(cyc),1095,1245,pSeq(cyc,1030)) ;[execute]
 If MoveRequired(cyc,361,-1) Then pSeq(cyc,421)=ExtractAnimSeq(p(cyc),1095,1245,pSeq(cyc,1031)) ;[receive]
 ;death valley driver
 If MoveRequired(cyc,362,1) Then pSeq(cyc,422)=ExtractAnimSeq(p(cyc),1255,1450,pSeq(cyc,1030)) ;[execute]
 If MoveRequired(cyc,362,-1) Then pSeq(cyc,423)=ExtractAnimSeq(p(cyc),1255,1450,pSeq(cyc,1031)) ;[receive]
 ;snapmare
 If MoveRequired(cyc,363,1) Then pSeq(cyc,424)=ExtractAnimSeq(p(cyc),985,1085,pSeq(cyc,1024)) ;[execute]
 If MoveRequired(cyc,363,-1) Then pSeq(cyc,425)=ExtractAnimSeq(p(cyc),985,1085,pSeq(cyc,1025)) ;[receive]
 ;neckbreaker
 If MoveRequired(cyc,364,1) Then pSeq(cyc,426)=ExtractAnimSeq(p(cyc),1095,1230,pSeq(cyc,1024)) ;[execute]
 If MoveRequired(cyc,364,-1) Then pSeq(cyc,427)=ExtractAnimSeq(p(cyc),1095,1230,pSeq(cyc,1025)) ;[receive]
 ;DDT
 If MoveRequired(cyc,365,1) Then pSeq(cyc,428)=ExtractAnimSeq(p(cyc),905,1055,pSeq(cyc,1026)) ;[execute]
 If MoveRequired(cyc,365,-1) Then pSeq(cyc,429)=ExtractAnimSeq(p(cyc),905,1055,pSeq(cyc,1027)) ;[receive]
 ;tornado DDT
 If MoveRequired(cyc,366,1) Then pSeq(cyc,430)=ExtractAnimSeq(p(cyc),1065,1265,pSeq(cyc,1026)) ;[execute]
 If MoveRequired(cyc,366,-1) Then pSeq(cyc,431)=ExtractAnimSeq(p(cyc),1065,1265,pSeq(cyc,1027)) ;[receive]
 ;hip toss
 If MoveRequired(cyc,367,1) Then pSeq(cyc,432)=ExtractAnimSeq(p(cyc),1460,1600,pSeq(cyc,1030)) ;[execute]
 If MoveRequired(cyc,367,-1) Then pSeq(cyc,433)=ExtractAnimSeq(p(cyc),1460,1600,pSeq(cyc,1031)) ;[receive]
 ;gutwrench suplex
 If MoveRequired(cyc,368,1) Then pSeq(cyc,434)=ExtractAnimSeq(p(cyc),1610,1740,pSeq(cyc,1030)) ;[execute]
 If MoveRequired(cyc,368,-1) Then pSeq(cyc,435)=ExtractAnimSeq(p(cyc),1610,1740,pSeq(cyc,1031)) ;[receive]
 ;russian legsweep
 If MoveRequired(cyc,369,1) Then pSeq(cyc,436)=ExtractAnimSeq(p(cyc),1380,1550,pSeq(cyc,1028)) ;[execute]
 If MoveRequired(cyc,369,-1) Then pSeq(cyc,437)=ExtractAnimSeq(p(cyc),1380,1550,pSeq(cyc,1029)) ;[receive]
 ;spear
 If MoveRequired(cyc,370,1) Then pSeq(cyc,438)=ExtractAnimSeq(p(cyc),1560,1685,pSeq(cyc,1028)) ;[execute]
 If MoveRequired(cyc,370,-1) Then pSeq(cyc,439)=ExtractAnimSeq(p(cyc),1560,1685,pSeq(cyc,1029)) ;[receive]
 ;standing clothesline
 If MoveRequired(cyc,371,1) Then pSeq(cyc,440)=ExtractAnimSeq(p(cyc),1695,1820,pSeq(cyc,1028)) ;[execute]
 If MoveRequired(cyc,371,-1) Then pSeq(cyc,441)=ExtractAnimSeq(p(cyc),1695,1820,pSeq(cyc,1029)) ;[receive]
 ;choke slam
 If MoveRequired(cyc,372,1) Then pSeq(cyc,442)=ExtractAnimSeq(p(cyc),1240,1405,pSeq(cyc,1024)) ;[execute]
 If MoveRequired(cyc,372,-1) Then pSeq(cyc,443)=ExtractAnimSeq(p(cyc),1240,1405,pSeq(cyc,1025)) ;[receive]
 ;x-factor
 If MoveRequired(cyc,373,1) Then pSeq(cyc,444)=ExtractAnimSeq(p(cyc),1415,1575,pSeq(cyc,1024)) ;[execute]
 If MoveRequired(cyc,373,-1) Then pSeq(cyc,445)=ExtractAnimSeq(p(cyc),1415,1575,pSeq(cyc,1025)) ;[receive]
 ;underhook suplex
 If MoveRequired(cyc,374,1) Then pSeq(cyc,446)=ExtractAnimSeq(p(cyc),1630,1785,pSeq(cyc,1022)) ;[execute]
 If MoveRequired(cyc,374,-1) Then pSeq(cyc,447)=ExtractAnimSeq(p(cyc),1630,1785,pSeq(cyc,1023)) ;[receive]
 ;reverse DDT
 If MoveRequired(cyc,375,1) Then pSeq(cyc,448)=ExtractAnimSeq(p(cyc),1275,1400,pSeq(cyc,1026)) ;[execute]
 If MoveRequired(cyc,375,-1) Then pSeq(cyc,449)=ExtractAnimSeq(p(cyc),1275,1400,pSeq(cyc,1027)) ;[receive]
 ;reverse suplex
 If MoveRequired(cyc,376,1) Then pSeq(cyc,450)=ExtractAnimSeq(p(cyc),1410,1580,pSeq(cyc,1026)) ;[execute]
 If MoveRequired(cyc,376,-1) Then pSeq(cyc,451)=ExtractAnimSeq(p(cyc),1410,1580,pSeq(cyc,1027)) ;[receive]
 ;MDKO
 If MoveRequired(cyc,377,1) Then pSeq(cyc,452)=ExtractAnimSeq(p(cyc),1750,1910,pSeq(cyc,1030)) ;[execute]
 If MoveRequired(cyc,377,-1) Then pSeq(cyc,453)=ExtractAnimSeq(p(cyc),1750,1910,pSeq(cyc,1031)) ;[receive]
 ;victory roll
 pSeq(cyc,454)=ExtractAnimSeq(p(cyc),1830,1960,pSeq(cyc,1028)) ;apply [execute]
 pSeq(cyc,455)=ExtractAnimSeq(p(cyc),1830,1960,pSeq(cyc,1029)) ;apply [receive]
 pSeq(cyc,456)=ExtractAnimSeq(p(cyc),1960,2000,pSeq(cyc,1028)) ;pin [execute]
 pSeq(cyc,457)=ExtractAnimSeq(p(cyc),1960,2000,pSeq(cyc,1029)) ;pin [receive]
 pSeq(cyc,458)=ExtractAnimSeq(p(cyc),2010,2075,pSeq(cyc,1028)) ;release [execute]
 pSeq(cyc,459)=ExtractAnimSeq(p(cyc),2010,2075,pSeq(cyc,1029)) ;release [receive]
 ;test of strength
 If MoveRequired(cyc,379,1) Then pSeq(cyc,460)=ExtractAnimSeq(p(cyc),2085,2220,pSeq(cyc,1028)) ;[apply execute]
 If MoveRequired(cyc,379,-1) Then pSeq(cyc,461)=ExtractAnimSeq(p(cyc),2085,2220,pSeq(cyc,1029)) ;[apply receive]
 If MoveRequired(cyc,379,1) Then pSeq(cyc,462)=ExtractAnimSeq(p(cyc),2230,2270,pSeq(cyc,1028)) ;[wrench execute]
 If MoveRequired(cyc,379,-1) Then pSeq(cyc,463)=ExtractAnimSeq(p(cyc),2230,2270,pSeq(cyc,1029)) ;[wrench receive]
 ;razor's edge
 If MoveRequired(cyc,380,1) Then pSeq(cyc,464)=ExtractAnimSeq(p(cyc),1590,1850,pSeq(cyc,1026)) ;[execute]
 If MoveRequired(cyc,380,-1) Then pSeq(cyc,465)=ExtractAnimSeq(p(cyc),1590,1850,pSeq(cyc,1027)) ;[receive]
 ;go to sleep
 If MoveRequired(cyc,381,1) Then pSeq(cyc,466)=ExtractAnimSeq(p(cyc),1920,2110,pSeq(cyc,1030)) ;[execute]
 If MoveRequired(cyc,381,-1) Then pSeq(cyc,467)=ExtractAnimSeq(p(cyc),1920,2110,pSeq(cyc,1031)) ;[receive]
 ;roll-up pin
 pSeq(cyc,468)=ExtractAnimSeq(p(cyc),310,420,pSeq(cyc,1032)) ;apply [execute]
 pSeq(cyc,469)=ExtractAnimSeq(p(cyc),310,420,pSeq(cyc,1033)) ;apply [receive]
 pSeq(cyc,470)=ExtractAnimSeq(p(cyc),430,470,pSeq(cyc,1032)) ;pin [execute]
 pSeq(cyc,471)=ExtractAnimSeq(p(cyc),430,470,pSeq(cyc,1033)) ;pin [receive]
 pSeq(cyc,472)=ExtractAnimSeq(p(cyc),480,530,pSeq(cyc,1032)) ;release [execute]
 pSeq(cyc,473)=ExtractAnimSeq(p(cyc),480,530,pSeq(cyc,1033)) ;release [receive]
 ;crucifix pin
 pSeq(cyc,474)=ExtractAnimSeq(p(cyc),70,195,pSeq(cyc,1032)) ;apply [execute]
 pSeq(cyc,475)=ExtractAnimSeq(p(cyc),70,195,pSeq(cyc,1033)) ;apply [receive]
 pSeq(cyc,476)=ExtractAnimSeq(p(cyc),200,240,pSeq(cyc,1032)) ;pin [execute]
 pSeq(cyc,477)=ExtractAnimSeq(p(cyc),200,240,pSeq(cyc,1033)) ;pin [receive]
 pSeq(cyc,478)=ExtractAnimSeq(p(cyc),250,300,pSeq(cyc,1032)) ;release [execute]
 pSeq(cyc,479)=ExtractAnimSeq(p(cyc),250,300,pSeq(cyc,1033)) ;release [receive]
 ;full nelson
 If MoveRequired(cyc,384,1) Then pSeq(cyc,480)=ExtractAnimSeq(p(cyc),540,580,pSeq(cyc,1032)) ;[apply execute]
 If MoveRequired(cyc,384,-1) Then pSeq(cyc,481)=ExtractAnimSeq(p(cyc),540,580,pSeq(cyc,1033)) ;[apply receive]
 If MoveRequired(cyc,384,1) Then pSeq(cyc,482)=ExtractAnimSeq(p(cyc),590,630,pSeq(cyc,1032)) ;[wrench execute]
 If MoveRequired(cyc,384,-1) Then pSeq(cyc,483)=ExtractAnimSeq(p(cyc),590,630,pSeq(cyc,1033)) ;[wrench receive]
 ;full nelson suplex
 If MoveRequired(cyc,385,1) Then pSeq(cyc,484)=ExtractAnimSeq(p(cyc),640,815,pSeq(cyc,1032)) ;[execute]
 If MoveRequired(cyc,385,-1) Then pSeq(cyc,485)=ExtractAnimSeq(p(cyc),640,815,pSeq(cyc,1033)) ;[receive] 
 ;skull crushing finale
 If MoveRequired(cyc,386,1) Then pSeq(cyc,486)=ExtractAnimSeq(p(cyc),825,965,pSeq(cyc,1032)) ;[execute]
 If MoveRequired(cyc,386,-1) Then pSeq(cyc,487)=ExtractAnimSeq(p(cyc),825,965,pSeq(cyc,1033)) ;[receive] 
 ;F5
 If MoveRequired(cyc,387,1) Then pSeq(cyc,488)=ExtractAnimSeq(p(cyc),2120,2285,pSeq(cyc,1030)) ;[execute]
 If MoveRequired(cyc,387,-1) Then pSeq(cyc,489)=ExtractAnimSeq(p(cyc),2120,2285,pSeq(cyc,1031)) ;[receive]
 ;throat toss
 If MoveRequired(cyc,388,1) Then pSeq(cyc,490)=ExtractAnimSeq(p(cyc),975,1100,pSeq(cyc,1032)) ;[execute]
 If MoveRequired(cyc,388,-1) Then pSeq(cyc,491)=ExtractAnimSeq(p(cyc),975,1100,pSeq(cyc,1033)) ;[receive]
 ;Attitude Adjustment
 If MoveRequired(cyc,389,1) Then pSeq(cyc,492)=ExtractAnimSeq(p(cyc),2295,2450,pSeq(cyc,1030)) ;[execute]
 If MoveRequired(cyc,389,-1) Then pSeq(cyc,493)=ExtractAnimSeq(p(cyc),2295,2450,pSeq(cyc,1031)) ;[receive]
End Function

;FIND MOVE REQUIREMENT
Function MoveRequired(cyc,move,task) ;-1=receive, 1=execute
 needed=0
 For v=1 To no_plays
  For count=1 To 13
   If moveAnim(1,charMove(pChar(v),count))=move 
    If task=1 And cyc=v Then needed=1 ;executor
    If task=-1 And cyc<>v Then needed=1 ;receiver
   EndIf
  Next
  If moveAnim(1,charMove(pChar(v),15))=move Then needed=1
 Next
 Return needed
End Function

;-----------------------------------------------------------------
;///////////////////// MOVE ANIMATIONS ///////////////////////////
;-----------------------------------------------------------------
Function MoveAnims(cyc)
 ;standing grapple lunge
 If pAnim(cyc)=300
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=1.0+(Float(pSkill(cyc))/90)
   If pAnimSpeed#(cyc)<1.55 Then pAnimSpeed#(cyc)=1.55
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,300),15/pAnimSpeed#(cyc)
   factor#=GetPercent#(pAnimSpeed#(cyc),2.0)
   pTravel#(cyc)=Float(pAgility(cyc))/90
   pTravel#(cyc)=PercentOf#(pTravel#(cyc),factor#)
   pSting(cyc)=1
  EndIf
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,Rnd(0.25,0.5))
  pTravel#(cyc)=pTravel#(cyc)-(pTravel#(cyc)/Int(45/pAnimSpeed#(cyc)))
  If InProximity(cyc,pFoc(cyc),10)=0 Or pFoc(cyc)=0 Then Advance(cyc,pA#(cyc),pTravel#(cyc))
  If pAnimTim(cyc)<Int(45/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=pStepTim#(cyc)+1
  If pFoc(cyc)>0
   If charHeight(pChar(cyc))>charHeight(pChar(pFoc(cyc))) Then pBodyTXA#(cyc)=PercentOf#(charHeight(pChar(cyc))-charHeight(pChar(pFoc(cyc))),85)
   If AttackViable(pFoc(cyc))=>2 Then pBodyTXA#(cyc)=pBodyTXA#(cyc)+15
  EndIf
  intact=1
  If pScar(cyc,7)=5 And pScar(cyc,20)=5 Then intact=0 
  If intact=1 And pAnimTim(cyc)=>Int(25/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(45/pAnimSpeed#(cyc))
   For v=1 To no_plays
    range#=16+(charHeight(pChar(cyc))/3)
    If pAnimTim(cyc)<Int(30/pAnimSpeed#(cyc)) Then range#=range#-(Int(30/pAnimSpeed#(cyc))-pAnimTim(cyc))
    If pPlatform(cyc)=>1 And pPlatform(cyc)=<4 Then range#=range#+3
    If pAnim(v)=701 Then range#=range#-(range#/4)
    level=0
    If pY#(cyc)=pY#(v) Then level=1
    If pPlatform(cyc)=0 And pPlatform(v)>0 And pY#(v)>pY#(cyc)+5 And pY#(v)<pY#(cyc)+35 Then level=2
    If pPlatform(cyc)=0 And pAnim(v)=701 Then level=3
    If level=2 And range#>16 Then range#=16
    If AttackViable(v)=2 And pAnimTim(cyc)<Int(30/pAnimSpeed#(cyc)) Then level=0
    If pAnimTim(cyc)>Int(36/pAnimSpeed#(cyc)) And pMomentum(v)=0 Then level=0
    sensible=1
    If pFoc(cyc)>0 And v<>pFoc(cyc) And InProximity(cyc,pFoc(cyc),range#)
     If InLine(cyc,pLimb(pFoc(cyc),36),45) Then sensible=0
    EndIf
    If level>0 And sensible=1 And cyc<>v And AttackViable(v)=>1 And AttackViable(v)=<2 And pAnim(v)<>155 And InProximity(cyc,v,range#) And pGrappling(cyc)=0 And pGrappler(v)=0 And pGrappling(v)=0 And pSting(cyc)=1
     distance#=GetDistance#(pX#(cyc),pZ#(cyc),RealX#(v),RealZ#(v))
     angle#=55-((distance#-10)*3)
     If angle#<10 Then angle#=10 
     If InLine(cyc,pLimb(v,36),angle#) ;Or (InProximity(cyc,v,10) And InLine(cyc,pLimb(v,36),45))
      ImpactChecks(v)
      ProduceSound(p(v),sBlock(Rnd(1,4)),22050,0.5)
      ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),0.5)
      If EntertainViable(cyc,v) Then entScore=entScore+100
      pGrappling(cyc)=v : pGrappler(v)=cyc : pGrappleAssist(cyc)=0
      pFoc(cyc)=v : pFoc(v)=cyc
      If pAnim(v)=12 And (pAnimTim(v)>5 Or pOldAnim(v)=44)
       If pMomentum(v)<10 Then pMomentum(v)=10
      EndIf
      If level=3
       TriggerMove(cyc,moveAnim(2,charMove(pChar(cyc),14)))
      Else
       pGrappleAssist(cyc)=FindAssistance(cyc,v)
       If pGrappleAssist(cyc)>0
        TriggerMove(cyc,FindTeamMove(cyc,pGrappleAssist(cyc)))
       Else
        If level=1 Then ChangeAnim(cyc,301) : ChangeAnim(v,302)
        If level=2 Then TriggerMove(cyc,315)
       EndIf
      EndIf 
      ClockAbuse(cyc,v,1)
      pAngerTim(v,cyc)=100
      pSting(cyc)=0
     EndIf
    EndIf
   Next
  EndIf
  randy=Rnd(0,(110-pSkill(cyc))*2)
  If (randy=0 And pAnimTim(cyc)>Int(45/pAnimSpeed#(cyc))) Or pAnimTim(cyc)>Int(65/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,0)
  pHealth(cyc)=pHealth(cyc)-1
 EndIf
 ;grapple aggressor
 If pAnim(cyc)=301
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=0.4
   anim=301 : : transition=5
   If pOldAnim(cyc)=300 Then transition=8
   If pOldAnim(cyc)=510 Or pOldAnim(cyc)=610 Then anim=305 : transition=0
   Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,anim),transition
   Animate p(v),1,pAnimSpeed#(cyc),pSeq(v,anim+1),transition
  EndIf
  If pAnimTim(cyc)>5 And InLine(cyc,p(v),25)=0
   BreakGrapple(cyc,0)
  Else
   FixGrapple(cyc)
   GrappleOffset(cyc,v,0)
   If pAnimTim(cyc)>10 Or pOldAnim(cyc)<>300 
    If DirPressed(cyc) Then ChangeAnim(cyc,303)
   EndIf
   ManageGrapple(cyc)
  EndIf
 EndIf
 ;grapple victim
 If pAnim(cyc)=302
  If pGrappler(cyc)=0
   pGrappling(pGrappler(cyc))=0 : pGrappler(cyc)=0
   pHurtA#(cyc)=pA#(cyc)+180 : pStagger#(cyc)=0.5
   ChangeAnim(cyc,Rnd(102,105)) 
  EndIf
  DropWeapon(cyc,100)
 EndIf
 ;grapple movement
 If pAnim(cyc)=303
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=1.5
   Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,303),0;5
   Animate p(v),1,pAnimSpeed#(cyc),pSeq(v,304),0;5
   pStepTim#(cyc)=-15 : pStepTim#(v)=-15
  EndIf
  If InLine(cyc,p(v),5)=0 
   BreakGrapple(cyc,0)
  Else
   FixGrapple(cyc)
   GrappleOffset(cyc,v,0)
   moveAngle#=RequestAngle#(cyc)
   If pControl(cyc)=0 And cUpTim(cyc)<5 And cDownTim(cyc)<5 And cLeftTim(cyc)<5 And cRightTim(cyc)<5
    PositionEntity dummy,pActX#(cyc),pY#(cyc),pActZ#(cyc)
    PointEntity p(cyc),dummy
    moveAngle#=CleanAngle#(EntityYaw(p(cyc)))
    RotateEntity p(cyc),0,pA#(cyc),0
   EndIf
   If DirPressed(cyc) Then Advance(cyc,moveAngle#,pAnimSpeed#(cyc)/4) 
   pStepTim#(cyc)=pStepTim#(cyc)+pAnimSpeed#(cyc)
   pStepTim#(v)=pStepTim#(v)+pAnimSpeed#(cyc)
   If pAnimTim(cyc)>5 And DirPressed(cyc)=0 Then ChangeAnim(cyc,301) 
   ManageGrapple(cyc) 
  EndIf 
 EndIf
 ;move victim
 If pAnim(cyc)=309
  If pAnim(pGrappler(cyc))<300 Then pGrappling(pGrappler(cyc))=0 : pGrappler(cyc)=0 
  If pGrappler(cyc)=0 Then ChangeAnim(cyc,0)
  DropWeapon(cyc,100)
 EndIf
 ;******************************** STANDING MOVES *****************************************
 ;IRISH WHIP
 If pAnim(cyc)=310
  v=pGrappling(cyc)
  animA=310 : animB=311 : pAnimSpeed#(cyc)=2.0
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(80/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Then pStepTim#(v)=99
  If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then Pop(cyc,Rnd(2,9),0) : ProduceSound(p(cyc),sSwing,21000,0.5)
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) 
   ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),2)
   BreakMove(cyc,v,0,1)
  EndIf
  If v>0 And pAnimTim(cyc)=<Int(65/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-0.3)
  If pAnimTim(cyc)=>Int(65/pAnimSpeed#(cyc)) And v>0
   ChangeAnim(v,12) : SharpTransition(v,34,1,180)
   pGrappling(cyc)=0 : pGrappler(v)=0
   pExcusedPlays(v)=0 : pExcusedItems(v)=0
   pMomentum(v)=Rnd(50,200) : pDizzyTim(v)=0
   chance=pHealth(v)/25
   If chance<50 Then chance=50
   randy=Rnd(0,chance)
   If randy=<1 Then pHP(v)=0 : pHurtA#(v)=pA#(v) : pA#(v)=pA#(v)+(180*randy)
   pHeat(v)=pHeat(v)-1
   If EntertainViable(cyc,v) Then entScore=entScore+100 
   If cyc>v
    Advance(v,pA#(v),pSpeed#(v)*2)
    EnforceBlocks(v)
   EndIf
  EndIf
  If pAnimTim(cyc)=>Int(110/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,180)
   pExcusedPlays(cyc)=0 : pExcusedItems(cyc)=0
   pHeat(cyc)=pHeat(cyc)+1
  EndIf
 EndIf
 ;FORCE OUT OF RING
 If pAnim(cyc)=311
  v=pGrappling(cyc)
  pExcusedWorld(cyc)=1 : pExcusedWorld(v)=1 : pExcusedItems(v)=1
  animA=312 : animB=313 : pAnimSpeed#(cyc)=2.0
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then Pop(cyc,Rnd(2,9),1) : RopeSound(p(cyc),0) : ShakeRopes(pX#(cyc),pZ#(cyc),20,0)
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(105/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Then ProduceSound(p(v),sAgony(1),GetVoice(v),1)
  If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0)
  If pAnimTim(cyc)=>Int(45/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(95/pAnimSpeed#(cyc)) Then FindSmashes(v,pLimbY#(v,36),0)
  If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then FallEffects(v,1)
  If pAnimTim(cyc)=Int(95/pAnimSpeed#(cyc)) Then pDT(v)=GetDT(v,500) : FallEffects(v,2)
  If pAnimTim(cyc)>Int(130/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,180)
   ChangeAnim(v,152) : SharpTransition(v,152,1,270) : pY#(v)=wGround#
   EndMove(cyc,v)
  Else
   If pAnimTim(cyc)>Int(90/pAnimSpeed#(cyc))
    vertOffset#=Float(12-charHeight(pChar(v)))*0.175
    pY#(v)=pY#(v)-(vertOffset#/20)
   EndIf
  EndIf
 EndIf
 ;FORCE INTO RING
 If pAnim(cyc)=312
  v=pGrappling(cyc)
  pExcusedWorld(cyc)=1 : pExcusedWorld(v)=1
  animA=314 : animB=315 : pAnimSpeed#(cyc)=1.5
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(95/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then Pop(cyc,Rnd(2,9),0) : ProduceSound(p(cyc),sSwing,21000,0.5)
  If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then ProduceSound(pLimb(v,36),sFall,22050,0)
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then RopeSound(p(cyc),0) : ShakeRopes(RealX#(v),RealZ#(v),20,0)
  If pAnimTim(cyc)>Int(100/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,180)
   ChangeAnim(v,152) : SharpTransition(v,152,1,270)
   ConfineToRing(v) : pY#(v)=wStage#
   EndMove(cyc,v)
  Else
   If pAnimTim(cyc)=>Int(15/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(50/pAnimSpeed#(cyc))
    vertOffset#=Float(12-charHeight(pChar(v)))*0.1
    pY#(v)=pY#(v)+(vertOffset#/Int(15/pAnimSpeed#(cyc)))
   EndIf
  EndIf
  If EntertainViable(cyc,v) Then entScore=entScore+1 
 EndIf 
 ;DRAG OUT FROM APRON
 If pAnim(cyc)=313
  v=pGrappling(cyc)
  pExcusedWorld(cyc)=1 : pExcusedWorld(v)=1 : pExcusedItems(v)=1
  animA=316 : animB=317 : pAnimSpeed#(cyc)=2.0
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then Pop(cyc,Rnd(2,9),1) : RopeSound(p(cyc),0) : ShakeRopes(pX#(cyc),pZ#(cyc),20,0)
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Then ProduceSound(p(v),sAgony(1),GetVoice(v),1)
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0)
  If pAnimTim(cyc)=>Int(55/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(95/pAnimSpeed#(cyc)) Then FindSmashes(v,pLimbY#(v,36),0)
  If pAnimTim(cyc)=Int(85/pAnimSpeed#(cyc)) Then pDT(v)=GetDT(v,500) : FallEffects(v,2)
  If pAnimTim(cyc)>Int(125/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,180)
   ChangeAnim(v,152) : SharpTransition(v,152,1,270) : pY#(v)=wGround#
   EndMove(cyc,v)
  Else
   If pAnimTim(cyc)=>Int(65/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(95/pAnimSpeed#(cyc))
    vertOffset#=Float(12-charHeight(pChar(v)))*0.175
    pY#(v)=pY#(v)-(vertOffset#/Int(30/pAnimSpeed#(cyc)))
   EndIf
  EndIf
 EndIf
 ;DRAG IN FROM APRON
 If pAnim(cyc)=314
  v=pGrappling(cyc)
  pExcusedWorld(cyc)=1 : pExcusedWorld(v)=1 : pExcusedItems(v)=1
  animA=318 : animB=319 : pAnimSpeed#(cyc)=2.0
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then Pop(cyc,Rnd(2,9),0) : RopeSound(p(cyc),0) : ShakeRopes(pX#(cyc),pZ#(cyc),20,0)
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0)
  If pAnimTim(cyc)=>Int(50/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(75/pAnimSpeed#(cyc)) Then FindSmashes(v,pLimbY#(v,36),0)
  If pAnimTim(cyc)=Int(65/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,1,3,1)
  tester=v
  If pAnimTim(cyc)>Int(115/pAnimSpeed#(cyc))
   v=pGrappling(cyc)
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,180)
   ChangeAnim(v,150) : SharpTransition(v,150,1,90)
   ConfineToRing(cyc) : ConfineToRing(v) : pPlatform(v)=0
   EndMove(cyc,v)
  EndIf
 EndIf
 ;TOSS DOWN FROM PLATFORM
 If pAnim(cyc)=315
  v=pGrappling(cyc) : pPlatform(v)=0 : pExcusedItems(v)=1
  animA=320 : animB=321 : pAnimSpeed#(cyc)=2.0
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Then Pop(cyc,Rnd(2,9),1) : ProduceSound(p(v),sAgony(1),GetVoice(v),1)
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=>Int(40/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(70/pAnimSpeed#(cyc)) Then FindSmashes(v,pLimbY#(v,36),0)
  If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,2,7,1)
  If pAnimTim(cyc)>Int(105/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,180)
   ChangeAnim(v,150) : SharpTransition(v,150,1,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;BODYSLAM
 If pAnim(cyc)=316
  v=pGrappling(cyc) : pExcusedItems(v)=1
  animA=322 : animB=323 : pAnimSpeed#(cyc)=2.0
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.25)
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0)
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) 
   ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),1)
   BreakMove(cyc,v,1,3)
  EndIf
  If v>0 And pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(85/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,2.0)
  If v>0 And pAnimTim(cyc)=>Int(60/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(105/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If v>0 And pAnimTim(cyc)=Int(85/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,1,5,1)
  If pAnimTim(cyc)>Int(145/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,0)
   If v>0 Then ChangeAnim(v,150) : SharpTransition(v,150,1,0)
   EndMove(cyc,v)
  EndIf
  If pAnimTim(cyc)<Int(65/pAnimSpeed#(cyc)) Then faller=5 Else faller=1
  If v>0 Then MoveThrowOut(cyc,v,Int(55/pAnimSpeed#(cyc)),Int(75/pAnimSpeed#(cyc)),faller)
 EndIf
 ;SUPLEX
 If pAnim(cyc)=317
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=324 : animB=325 : pAnimSpeed#(cyc)=2.0
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.25) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(90/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0)
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) 
   ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),2)
   BreakMove(cyc,v,0,3)
  EndIf
  If v>0 And pAnimTim(cyc)=>Int(30/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(120/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-0.5)
  If pAnimTim(cyc)=>Int(105/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(130/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If v>0 And pAnimTim(cyc)=>Int(110/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(140/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If v>0 And pAnimTim(cyc)=Int(120/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,3,5,2)
  If pAnimTim(cyc)>Int(170/pAnimSpeed#(cyc))
   ChangeAnim(cyc,150) : SharpTransition(cyc,150,1,0)
   If v>0 Then ChangeAnim(v,150) : SharpTransition(v,150,1,180)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveThrowOut(cyc,v,Int(105/pAnimSpeed#(cyc)),Int(105/pAnimSpeed#(cyc)),3)
 EndIf
 ;SNAP SUPLEX
 If pAnim(cyc)=318
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=326 : animB=327 : pAnimSpeed#(cyc)=2.0
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0)
  If pAnimTim(cyc)=Int(55/pAnimSpeed#(cyc)) 
   ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),2)
   BreakMove(cyc,v,0,3)
  EndIf
  If v>0 And pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(75/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-0.5)
  If pAnimTim(cyc)=>Int(65/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(85/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If v>0 And pAnimTim(cyc)=>Int(70/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(95/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If v>0 And pAnimTim(cyc)=Int(75/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,3,6,2)
  If pAnimTim(cyc)>Int(125/pAnimSpeed#(cyc))
   ChangeAnim(cyc,150) : SharpTransition(cyc,150,1,0)
   If v>0 Then ChangeAnim(v,150) : SharpTransition(v,150,1,180)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveThrowOut(cyc,v,Int(65/pAnimSpeed#(cyc)),Int(70/pAnimSpeed#(cyc)),3)
 EndIf
 ;STALLING SUPLEX
 If pAnim(cyc)=319
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=328 : animB=329 : pAnimSpeed#(cyc)=1.75
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(130/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(130/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.5) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(95/pAnimSpeed#(cyc)) Then Pop(cyc,Rnd(2,9),0)
  If pAnimTim(cyc)=Int(170/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) 
   ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),3)
   BreakMove(cyc,v,1,4)
  EndIf
  If v>0 And pAnimTim(cyc)=>Int(30/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(200/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-0.25)
  If pAnimTim(cyc)=>Int(185/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(210/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If v>0 And pAnimTim(cyc)=>Int(190/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(220/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If v>0 And pAnimTim(cyc)=Int(200/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,3,7,2)
  If pAnimTim(cyc)>Int(250/pAnimSpeed#(cyc))
   ChangeAnim(cyc,150) : SharpTransition(cyc,150,1,0)
   If v>0 Then ChangeAnim(v,150) : SharpTransition(v,150,1,180)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveThrowOut(cyc,v,Int(190/pAnimSpeed#(cyc)),Int(190/pAnimSpeed#(cyc)),3)
 EndIf
 ;BRAINBUSTER
 If pAnim(cyc)=320
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=330 : animB=331 : pAnimSpeed#(cyc)=1.9
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.25) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(90/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0)
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) 
   ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),2)
   BreakMove(cyc,v,0,4)
  EndIf
  If v>0 And pAnimTim(cyc)=>Int(30/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(115/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-0.5)
  If pAnimTim(cyc)=>Int(105/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(135/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If v>0 And pAnimTim(cyc)=>Int(105/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(145/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If v>0 And pAnimTim(cyc)=Int(115/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,8,2)
  If v>0 And pAnimTim(cyc)=Int(135/pAnimSpeed#(cyc)) Then ProduceSound(p(v),sFall,22050,0.5)
  If pAnimTim(cyc)>Int(165/pAnimSpeed#(cyc))
   ChangeAnim(cyc,150) : SharpTransition(cyc,150,1,0)
   If v>0 Then ChangeAnim(v,150) : SharpTransition(v,150,1,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;STALLING BRAINBUSTER
 If pAnim(cyc)=321
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=332 : animB=333 : pAnimSpeed#(cyc)=1.75
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(130/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(130/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.5) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(95/pAnimSpeed#(cyc)) Then Pop(cyc,Rnd(2,9),0)
  If pAnimTim(cyc)=Int(170/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) 
   ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),3)
   BreakMove(cyc,v,1,4)
  EndIf
  If v>0 And pAnimTim(cyc)=>Int(30/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(200/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-0.25)
  If pAnimTim(cyc)=>Int(185/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(215/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If v>0 And pAnimTim(cyc)=>Int(190/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(230/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If v>0 And pAnimTim(cyc)=Int(200/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,9,2)
  If v>0 And pAnimTim(cyc)=Int(230/pAnimSpeed#(cyc)) Then ProduceSound(p(v),sFall,22050,0.5)
  If pAnimTim(cyc)>Int(250/pAnimSpeed#(cyc))
   ChangeAnim(cyc,150) : SharpTransition(cyc,150,1,0)
   If v>0 Then ChangeAnim(v,150) : SharpTransition(v,150,1,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;JACKHAMMER
 If pAnim(cyc)=322
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=334 : animB=335 : pAnimSpeed#(cyc)=2.0
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(130/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(130/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.5) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(95/pAnimSpeed#(cyc)) Then Pop(cyc,Rnd(2,9),0)
  If pAnimTim(cyc)=Int(170/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) 
   ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),5)
   BreakMove(cyc,v,1,5)
  EndIf
  If v>0 And pAnimTim(cyc)=>Int(30/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(210/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-0.5)
  If pAnimTim(cyc)=>Int(185/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(230/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If v>0 And pAnimTim(cyc)=>Int(190/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(230/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If v>0 And pAnimTim(cyc)=Int(210/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,3,8,2)
  If pAnimTim(cyc)>Int(255/pAnimSpeed#(cyc))
   ChangeAnim(cyc,154) : SharpTransition(cyc,154,1,180)
   If v>0 Then ChangeAnim(v,150) : SharpTransition(v,150,1,270)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;SUPLEX DROP
 If pAnim(cyc)=323
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=336 : animB=337 : pAnimSpeed#(cyc)=1.9
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.5) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(95/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) 
   ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),2)
   BreakMove(cyc,v,1,4)
  EndIf
  If v>0 And pAnimTim(cyc)=>Int(30/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(125/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,0.5)
  If pAnimTim(cyc)=>Int(115/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(145/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If v>0 And pAnimTim(cyc)=>Int(115/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(145/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If v>0 And pAnimTim(cyc)=Int(125/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,7,2)
  If pAnimTim(cyc)>Int(175/pAnimSpeed#(cyc))
   ChangeAnim(cyc,154) : SharpTransition(cyc,154,1,0)
   If v>0 Then ChangeAnim(v,152) : SharpTransition(v,152,1,180)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveThrowOut(cyc,v,Int(110/pAnimSpeed#(cyc)),Int(110/pAnimSpeed#(cyc)),6)
 EndIf
 ;SLEEPER HOLD
 If pAnim(cyc)=324
  ;apply
  v=pGrappling(cyc)
  animA=338 : animB=339
  If pAnimStage(cyc)=1
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.0 
    Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0
    Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
   EndIf 
   If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.25)
   If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),1)
   If pAnimTim(cyc)>Int(50/pAnimSpeed#(cyc)) Then pAnimStage(cyc)=2 : pAnimTim(cyc)=0
  EndIf
  ;wrench
  If pAnimStage(cyc)=2
   If pAnimTim(cyc)=0
    Pop(cyc,Rnd(2,9),0)
    ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),0)
    ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
    If pSpecial(cyc)>0 Then pAnimSpeed#(cyc)=0.75 Else pAnimSpeed#(cyc)=Rnd(0.25,0.5)
    Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,animA+2),0
    Animate p(v),1,pAnimSpeed#(cyc),pSeq(v,animB+2),0
   EndIf
   HoldEffects(cyc,v,1,0.01,11)
  EndIf
  ;leave
  If pAnimStage(cyc)=3
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.5
    Animate p(cyc),3,-pAnimSpeed#(cyc),pSeq(cyc,animA),5
    Animate p(v),3,-pAnimSpeed#(cyc),pSeq(v,animB),5
   EndIf
   If pAnimTim(cyc)=Int(5/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.25)
   If pAnimTim(cyc)=Int(5/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)>Int(50/pAnimSpeed#(cyc))
    ChangeAnim(cyc,301) : SharpTransition(cyc,305,1,0)
    ChangeAnim(v,302) : SharpTransition(v,306,1,180)
    BreakGrapple(cyc,0) : pHP(v)=0
   EndIf
  EndIf
 EndIf
 ;HEADLOCK
 If pAnim(cyc)=325
  ;apply
  v=pGrappling(cyc)
  animA=342 : animB=343
  If pAnimStage(cyc)=1
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.0 
    Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0
    Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
   EndIf 
   If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.25)
   If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),1)
   If pAnimTim(cyc)>Int(50/pAnimSpeed#(cyc)) Then pAnimStage(cyc)=2 : pAnimTim(cyc)=0
  EndIf
  ;wrench
  If pAnimStage(cyc)=2
   If pAnimTim(cyc)=0
    Pop(cyc,Rnd(2,9),0)
    ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),0)
    ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
    If pSpecial(cyc)>0 Then pAnimSpeed#(cyc)=1.0 Else pAnimSpeed#(cyc)=Rnd(0.5,0.75)
    Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,animA+2),0
    Animate p(v),1,pAnimSpeed#(cyc),pSeq(v,animB+2),0
   EndIf
   HoldEffects(cyc,v,1,0.01,10)
  EndIf
  ;leave
  If pAnimStage(cyc)=3
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.5
    Animate p(cyc),3,-pAnimSpeed#(cyc),pSeq(cyc,animA),5
    Animate p(v),3,-pAnimSpeed#(cyc),pSeq(v,animB),5
   EndIf
   If pAnimTim(cyc)=Int(5/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.25)
   If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)>Int(50/pAnimSpeed#(cyc))
    ChangeAnim(cyc,301) : SharpTransition(cyc,305,1,0)
    ChangeAnim(v,302) : SharpTransition(v,306,1,180)
    BreakGrapple(cyc,0) : pHP(v)=0
   EndIf
  EndIf
 EndIf
 ;HEADLOCK PUNCH
 If pAnim(cyc)=326
  v=pGrappling(cyc) : pExcusedItems(v)=1
  animA=346 : animB=347 : pAnimSpeed#(cyc)=2.0
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.25)
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
  If pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),1)
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.5)
  If pAnimTim(cyc)=Int(85/pAnimSpeed#(cyc))
   MoveImpact(cyc,v,0,4,2)
   ImpactSpurt(pLimbX#(v,1),pLimbY#(v,1),pLimbZ#(v,1),1,1)
   BloodSpurt(pLimbX#(v,1),pLimbY#(v,1),pLimbZ#(v,1),-1,pScar(v,1)-1,1)
  EndIf
  If pAnimTim(cyc)=>Int(120/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(165/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(135/pAnimSpeed#(cyc))
   MoveFallEffect(v)
   If EntertainViable(cyc,v) Then entScore=entScore+100 
  EndIf
  If pAnimTim(cyc)>Int(175/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0)
   ChangeAnim(v,150) : SharpTransition(v,150,1,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;BULLDOG
 If pAnim(cyc)=327
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=348 : animB=349 : pAnimSpeed#(cyc)=2.0
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(130/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(150/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.5) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),1)
  If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,0,3)
  If pAnimTim(cyc)=>Int(10/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(90/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-1.0)
  If pAnimTim(cyc)=>Int(70/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(110/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pAnimTim(cyc)=>Int(70/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(110/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(90/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,7,2)
  If pAnimTim(cyc)>Int(170/pAnimSpeed#(cyc))
   ChangeAnim(cyc,156) : SharpTransition(cyc,156,1,0)
   ChangeAnim(v,152) : SharpTransition(v,152,1,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;HEADLOCK TAKEDOWN
 If pAnim(cyc)=328
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=350 : animB=351 : pAnimSpeed#(cyc)=2.0
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(95/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(110/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.5) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),1)
  If pAnimTim(cyc)=Int(48/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,0,2)
  If pAnimTim(cyc)=>Int(10/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(73/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-1.0)
  If pAnimTim(cyc)=>Int(60/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(93/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pAnimTim(cyc)=>Int(60/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(93/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(73/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,3,5,2)
  If pAnimTim(cyc)>Int(125/pAnimSpeed#(cyc))
   ChangeAnim(cyc,156) : SharpTransition(cyc,156,1,180)
   ChangeAnim(v,150) : SharpTransition(v,150,1,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;PUMPING PRESS SLAM
 If pAnim(cyc)=329
  v=pGrappling(cyc) : pExcusedItems(v)=1
  animA=352 : animB=353 : pAnimSpeed#(cyc)=2.0
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(90/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(145/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(200/pAnimSpeed#(cyc)) Then Pop(cyc,Rnd(2,9),0)
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(115/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(170/pAnimSpeed#(cyc))
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  EndIf
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.5)
  If pAnimTim(cyc)=Int(220/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0)
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) 
   ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),3)
   BreakMove(cyc,v,1,5)
  EndIf
  If v>0 And pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(240/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,0.5)
  If v>0 And pAnimTim(cyc)=>Int(225/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(260/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If v>0 And pAnimTim(cyc)=Int(240/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,2,9,1)
  If pAnimTim(cyc)>Int(300/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,0)
   If v>0 Then ChangeAnim(v,150) : SharpTransition(v,150,1,0)
   EndMove(cyc,v)
  EndIf
  If pAnimTim(cyc)<Int(227/pAnimSpeed#(cyc)) Then faller=5 Else faller=1
  If v>0 Then MoveThrowOut(cyc,v,Int(220/pAnimSpeed#(cyc)),Int(235/pAnimSpeed#(cyc)),faller)
 EndIf
 ;PRESS SLAM
 If pAnim(cyc)=330
  v=pGrappling(cyc) : pExcusedItems(v)=1
  animA=354 : animB=355 : pAnimSpeed#(cyc)=2.0
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.5)
  If pAnimTim(cyc)=Int(90/pAnimSpeed#(cyc)) Then Pop(cyc,Rnd(2,9),0)
  If pAnimTim(cyc)=Int(110/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0)
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) 
   ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),3)
   BreakMove(cyc,v,1,4)
  EndIf
  If v>0 And pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(130/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,1.0)
  If v>0 And pAnimTim(cyc)=>Int(115/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(150/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If v>0 And pAnimTim(cyc)=Int(130/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,2,8,1)
  If pAnimTim(cyc)>Int(190/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,0)
   If v>0 Then ChangeAnim(v,150) : SharpTransition(v,150,1,0)
   EndMove(cyc,v)
  EndIf
  If pAnimTim(cyc)<Int(117/pAnimSpeed#(cyc)) Then faller=5 Else faller=1
  If v>0 Then MoveThrowOut(cyc,v,Int(110/pAnimSpeed#(cyc)),Int(125/pAnimSpeed#(cyc)),faller)
 EndIf
 ;GORILLA PRESS SLAM
 If pAnim(cyc)=331
  v=pGrappling(cyc) : pExcusedItems(v)=1
  animA=356 : animB=357 : pAnimSpeed#(cyc)=2.1
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(185/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.5)
  If pAnimTim(cyc)=Int(90/pAnimSpeed#(cyc)) Then Pop(cyc,Rnd(2,9),0)
  If pAnimTim(cyc)=Int(130/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0)
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) 
   ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),3)
   BreakMove(cyc,v,1,5)
  EndIf
  If v>0 And pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(140/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-1.0)
  If v>0 And pAnimTim(cyc)=>Int(145/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(190/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If v>0 And pAnimTim(cyc)=Int(170/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,2,8,0)
  If pAnimTim(cyc)>Int(225/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,0,180)
   If v>0 Then ChangeAnim(v,152) : SharpTransition(v,152,1,90)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveThrowOut(cyc,v,Int(145/pAnimSpeed#(cyc)),Int(150/pAnimSpeed#(cyc)),9)
 EndIf
 ;POWERSLAM
 If pAnim(cyc)=332
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=358 : animB=359 : pAnimSpeed#(cyc)=2.1
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(5/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(105/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(120/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),1)
  If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,1,3)
  If pAnimTim(cyc)=>Int(55/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(85/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pAnimTim(cyc)=>Int(55/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(85/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,3,7,2)
  If pAnimTim(cyc)>Int(120/pAnimSpeed#(cyc))
   ChangeAnim(cyc,156) : SharpTransition(cyc,156,1,270)
   ChangeAnim(v,150) : SharpTransition(v,150,1,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;SHOULDER POWERSLAM
 If pAnim(cyc)=333
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=360 : animB=361 : pAnimSpeed#(cyc)=2.0
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(80/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),3)
  If pAnimTim(cyc)=Int(90/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,1,5)
  If pAnimTim(cyc)=>Int(105/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(140/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pAnimTim(cyc)=>Int(105/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(140/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(120/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,3,8,2)
  If pAnimTim(cyc)>Int(165/pAnimSpeed#(cyc))
   ChangeAnim(cyc,154) : SharpTransition(cyc,154,1,270)
   ChangeAnim(v,150) : SharpTransition(v,150,1,270)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;SHOULDER BREAKER
 If pAnim(cyc)=334
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=362 : animB=363 : pAnimSpeed#(cyc)=2.0
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(80/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),2)
  If pAnimTim(cyc)=Int(95/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,1,3)
  If pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(115/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,1.0)
  If pAnimTim(cyc)=>Int(105/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(175/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pAnimTim(cyc)=>Int(105/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(175/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(115/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,7,1)
  If pAnimTim(cyc)=Int(160/pAnimSpeed#(cyc)) Then MoveFallEffect(v)
  If pAnimTim(cyc)>Int(190/pAnimSpeed#(cyc))
   ChangeAnim(cyc,154) : SharpTransition(cyc,154,1,0)
   ChangeAnim(v,150) : SharpTransition(v,150,1,0)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;TOMBSTONE PILEDRIVER
 If pAnim(cyc)=335
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=364 : animB=365 : pAnimSpeed#(cyc)=2.0
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(80/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),5)
  If pAnimTim(cyc)=Int(95/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,0,5)
  If pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(120/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,1.0)
  If pAnimTim(cyc)=>Int(105/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(175/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pAnimTim(cyc)=>Int(105/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(175/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(120/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,8,1)
  If pAnimTim(cyc)=Int(165/pAnimSpeed#(cyc)) Then MoveFallEffect(v)
  If pAnimTim(cyc)>Int(195/pAnimSpeed#(cyc))
   ChangeAnim(cyc,154) : SharpTransition(cyc,154,1,0)
   ChangeAnim(v,150) : SharpTransition(v,150,1,0)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;INVERTED PILEDRIVER
 If pAnim(cyc)=336
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=366 : animB=367 : pAnimSpeed#(cyc)=2.0
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(80/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),5)
  If pAnimTim(cyc)=Int(95/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,0,5)
  If pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(120/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,1.0)
  If pAnimTim(cyc)=>Int(100/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(175/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pAnimTim(cyc)=>Int(105/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(175/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(120/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,9,1)
  If pAnimTim(cyc)=Int(165/pAnimSpeed#(cyc)) Then MoveFallEffect(v)
  If pAnimTim(cyc)>Int(220/pAnimSpeed#(cyc))
   ChangeAnim(cyc,156) : SharpTransition(cyc,156,1,90)
   ChangeAnim(v,150) : SharpTransition(v,150,1,0)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;JUMPING BODYSLAM
 If pAnim(cyc)=337
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=368 : animB=369 : pAnimSpeed#(cyc)=2.0
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(80/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),3)
  If pAnimTim(cyc)=Int(95/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,0,5)
  If pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(120/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,1.0)
  If pAnimTim(cyc)=>Int(100/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(140/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pAnimTim(cyc)=>Int(105/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(140/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(120/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,3,7,2)
  If pAnimTim(cyc)>Int(210/pAnimSpeed#(cyc))
   ChangeAnim(cyc,156) : SharpTransition(cyc,156,1,90)
   ChangeAnim(v,150) : SharpTransition(v,150,1,0)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;PILEDRIVER
 If pAnim(cyc)=338
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=370 : animB=371 : pAnimSpeed#(cyc)=2.0
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.5) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(90/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=Int(90/pAnimSpeed#(cyc))
   ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),2)
   BreakMove(cyc,v,0,4)
  EndIf
  If pAnimTim(cyc)=>Int(60/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(118/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,0.5)
  If pAnimTim(cyc)=>Int(105/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(175/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pAnimTim(cyc)=>Int(105/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(175/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(118/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,8,1)
  If pAnimTim(cyc)=Int(155/pAnimSpeed#(cyc)) Then MoveFallEffect(v)
  If pAnimTim(cyc)>Int(200/pAnimSpeed#(cyc))
   ChangeAnim(cyc,154) : SharpTransition(cyc,154,1,180)
   ChangeAnim(v,150) : SharpTransition(v,150,1,270)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;POWERBOMB
 If pAnim(cyc)=339
  v=pGrappling(cyc) : pExcusedItems(v)=1
  animA=372 : animB=373 : pAnimSpeed#(cyc)=1.9
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.5) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(110/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0)
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) 
   ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),3)
   BreakMove(cyc,v,1,5)
  EndIf
  If v>0 And pAnimTim(cyc)=>Int(60/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(125/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,1.0)
  If v>0 And pAnimTim(cyc)=>Int(110/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(145/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If v>0 And pAnimTim(cyc)=Int(125/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,2,8,1)
  If pAnimTim(cyc)>Int(175/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,0)
   If v>0 Then ChangeAnim(v,150) : SharpTransition(v,150,1,180)
   EndMove(cyc,v)
  EndIf
  MoveThrowOut(cyc,v,Int(105/pAnimSpeed#(cyc)),Int(115/pAnimSpeed#(cyc)),2)
 EndIf
 ;SITTING POWERBOMB
 If pAnim(cyc)=340
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=374 : animB=375 : pAnimSpeed#(cyc)=1.9
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(190/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(210/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.5) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(110/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0)
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) 
   ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),3)
   BreakMove(cyc,v,1,5)
  EndIf
  If pAnimTim(cyc)=>Int(60/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(135/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,1.0)
  If pAnimTim(cyc)=>Int(120/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(155/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pAnimTim(cyc)=>Int(120/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(155/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(135/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,3,8,2)
  If pAnimTim(cyc)=Int(170/pAnimSpeed#(cyc)) Then MoveFallEffect(v)
  If pAnimTim(cyc)>Int(225/pAnimSpeed#(cyc))
   ChangeAnim(cyc,154) : SharpTransition(cyc,154,1,225)
   ChangeAnim(v,152) : SharpTransition(v,152,1,90)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;UNDERHOOK FACEBUSTER
 If pAnim(cyc)=341
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=376 : animB=377 : pAnimSpeed#(cyc)=2.0
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(90/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(55/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(67/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(80/pAnimSpeed#(cyc))
   pStepTim#(cyc)=99
  EndIf
  If pAnimTim(cyc)=Int(95/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=Int(90/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),5)
  If pAnimTim(cyc)=Int(105/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,0,5)
  If pAnimTim(cyc)=>Int(95/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(135/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-1.0)
  If pAnimTim(cyc)=>Int(115/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(155/pAnimSpeed#(cyc))
   FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
   FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  EndIf
  If pAnimTim(cyc)=Int(135/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,9,2)
  If pAnimTim(cyc)>Int(195/pAnimSpeed#(cyc))
   ChangeAnim(cyc,154) : SharpTransition(cyc,154,1,0)
   ChangeAnim(v,152) : SharpTransition(v,152,1,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;HURRICANRANNA
 If pAnim(cyc)=342
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=378 : animB=379 : pAnimSpeed#(cyc)=2.1
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),3)
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,2,5)
  If pAnimTim(cyc)=>Int(15/pAnimSpeed#(cyc)) And pAnimTim(cyc)<Int(80/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-1.0)
  If pAnimTim(cyc)=>Int(80/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(120/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-0.5)
  If pAnimTim(cyc)=>Int(100/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(140/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If v>0 And pAnimTim(cyc)=>Int(100/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(140/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If v>0 And pAnimTim(cyc)=Int(120/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,8,0)
  If pAnimTim(cyc)>Int(170/pAnimSpeed#(cyc))
   ChangeAnim(cyc,154) : SharpTransition(cyc,154,1,0)
   If v>0 Then ChangeAnim(v,150) : SharpTransition(v,150,1,180)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveThrowOut(cyc,v,Int(100/pAnimSpeed#(cyc)),Int(110/pAnimSpeed#(cyc)),3)
 EndIf
 ;LEAPING PLANCHA
 If pAnim(cyc)=343
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=380 : animB=381 : pAnimSpeed#(cyc)=2.5
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(65/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),1)
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,2,3)
  If pAnimTim(cyc)=>Int(15/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(100/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,0.5)
  If pAnimTim(cyc)=>Int(70/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(120/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pAnimTim(cyc)=>Int(70/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(120/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(100/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,6,2)
  If pAnimTim(cyc)>Int(150/pAnimSpeed#(cyc))
   ChangeAnim(cyc,156) : SharpTransition(cyc,156,1,0)
   ChangeAnim(v,150) : SharpTransition(v,150,1,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;FLYING HEAD SCISSORS
 If pAnim(cyc)=344
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=382 : animB=383 : pAnimSpeed#(cyc)=2.5
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=Int(75/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),1)
  If pAnimTim(cyc)=Int(65/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,2,5)
  If pAnimTim(cyc)=>Int(15/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(120/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-0.5)
  If pAnimTim(cyc)=>Int(85/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(140/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If v>0 And pAnimTim(cyc)=>Int(85/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(140/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If v>0 And pAnimTim(cyc)=Int(120/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,7,1)
  If pAnimTim(cyc)>Int(175/pAnimSpeed#(cyc))
   ChangeAnim(cyc,156) : SharpTransition(cyc,156,1,315)
   If v>0 Then ChangeAnim(v,150) : SharpTransition(v,150,1,180)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveThrowOut(cyc,v,Int(100/pAnimSpeed#(cyc)),Int(100/pAnimSpeed#(cyc)),3)
 EndIf
 ;SMALL PACKAGE
 If pAnim(cyc)=345
  ;apply
  v=pGrappling(cyc)
  animA=384 : animB=385
  If pAnimStage(cyc)=1
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.0 
    Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0
    Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
   EndIf 
   If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.5)
   If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99 
   If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then MoveFallEffect(v);MoveImpact(cyc,v,1,2,1)
   If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),1)
   If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,0,3)
   If pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),1)
   If pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),1)
   If pAnimTim(cyc)>Int(60/pAnimSpeed#(cyc)) Then pAnimStage(cyc)=2 : pAnimTim(cyc)=0
  EndIf
  ;hold
  If pAnimStage(cyc)=2
   If pAnimTim(cyc)=0
    Pop(cyc,Rnd(2,9),0)
    ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
    pAnimSpeed#(cyc)=Rnd(0.5,1.0)
    Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,animA+2),5
    Animate p(v),1,pAnimSpeed#(cyc),pSeq(v,animB+2),5
    pPinning(cyc)=v : pPinner(v)=cyc
    pAutoTim(cyc)=0 : pWarned(cyc)=0
    pDT(v)=GetDT(v,Rnd(250,750))
    entScore=entScore+(charPopularity(pChar(cyc))*2)
   EndIf
   MovePin(cyc,v)
  EndIf
  ;release
  If pAnimStage(cyc)=3
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.0
    Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA+4),5
    Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB+4),5
   EndIf
   If pAnimTim(cyc)=Int(5/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.25)
   If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Then MoveFallEffect(v)
   If pAnimTim(cyc)=>Int(10/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(40/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),1)
   If pAnimTim(cyc)=>Int(10/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(40/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),1)
   If pAnimTim(cyc)>Int(60/pAnimSpeed#(cyc))
    ChangeAnim(cyc,150) : SharpTransition(cyc,150,1,270)
    ChangeAnim(v,150) : SharpTransition(v,150,1,180)
    EndMove(cyc,v)
   EndIf
  EndIf
 EndIf
 ;BELLY-TO-BELLY SUPLEX
 If pAnim(cyc)=346
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=390 : animB=391 : pAnimSpeed#(cyc)=2.1
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(105/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0)
  If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) 
   ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),2)
   BreakMove(cyc,v,0,3)
  EndIf
  If v>0 And pAnimTim(cyc)=>Int(10/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(40/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-0.5)
  If pAnimTim(cyc)=>Int(45/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(95/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If v>0 And pAnimTim(cyc)=>Int(55/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(110/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(75/pAnimSpeed#(cyc)) Then MoveFallEffect(cyc)
  If v>0 And pAnimTim(cyc)=Int(80/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,2,6,0)
  If pAnimTim(cyc)>Int(145/pAnimSpeed#(cyc))
   ChangeAnim(cyc,154) : SharpTransition(cyc,154,1,180)
   If v>0 Then ChangeAnim(v,150) : SharpTransition(v,150,1,180)
   EndMove(cyc,v)
  EndIf
  If pAnimTim(cyc)<Int(50/pAnimSpeed#(cyc)) Then faller=7 Else faller=3
  If v>0 Then MoveThrowOut(cyc,v,Int(45/pAnimSpeed#(cyc)),Int(65/pAnimSpeed#(cyc)),faller)
 EndIf
 ;BELLY-TO-BELLY SLAM
 If pAnim(cyc)=347
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=392 : animB=393 : pAnimSpeed#(cyc)=2.2
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc))
   ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),2)
   BreakMove(cyc,v,1,3)
  EndIf
  If pAnimTim(cyc)=>Int(15/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(70/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-0.25)
  If pAnimTim(cyc)=>Int(55/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(95/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pAnimTim(cyc)=>Int(55/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(95/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,3,7,2)
  If pAnimTim(cyc)>Int(125/pAnimSpeed#(cyc))
   ChangeAnim(cyc,154) : SharpTransition(cyc,154,1,180)
   ChangeAnim(v,150) : SharpTransition(v,150,1,90)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;NORTHERN LIGHTS SUPLEX
 If pAnim(cyc)=348
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=394 : animB=395 : pAnimSpeed#(cyc)=1.9
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(18/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(85/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(9/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(115/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(18/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc))
   ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),2)
   BreakMove(cyc,v,0,4)
  EndIf
  If pAnimTim(cyc)=>Int(15/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(55/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-1.0)
  If pAnimTim(cyc)=>Int(40/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(75/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pAnimTim(cyc)=>Int(40/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(75/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(55/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,3,7,2)
  If pAnimTim(cyc)>Int(115/pAnimSpeed#(cyc))
   ChangeAnim(cyc,154) : SharpTransition(cyc,154,1,180)
   ChangeAnim(v,150) : SharpTransition(v,150,1,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;BACK BODY DROP
 If pAnim(cyc)=349
  v=pGrappling(cyc) : pExcusedItems(v)=1
  animA=396 : animB=397 : pAnimSpeed#(cyc)=2.25
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) Then Pop(cyc,Rnd(2,9),0)
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0)
  If pAnimTim(cyc)=Int(32/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),1)
  If pAnimTim(cyc)=Int(37/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,0,3)
  If v>0 And pAnimTim(cyc)=>Int(10/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(50/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-1.0)
  If v>0 And pAnimTim(cyc)=>Int(65/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(110/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If v>0 And pAnimTim(cyc)=Int(90/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,2,6,0)
  If pAnimTim(cyc)>Int(160/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,180)
   If v>0 Then ChangeAnim(v,150) : SharpTransition(v,150,1,180)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveThrowOut(cyc,v,Int(67/pAnimSpeed#(cyc)),Int(75/pAnimSpeed#(cyc)),3)
 EndIf
 ;SPINEBUSTER
 If pAnim(cyc)=350
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=398 : animB=399 : pAnimSpeed#(cyc)=2.0
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(90/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc))
   ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),1)
   BreakMove(cyc,v,1,3)
  EndIf
  If pAnimTim(cyc)=>Int(15/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(65/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-0.25)
  If pAnimTim(cyc)=>Int(50/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(85/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pAnimTim(cyc)=>Int(50/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(85/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(65/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,3,7,2)
  If pAnimTim(cyc)>Int(115/pAnimSpeed#(cyc))
   ChangeAnim(cyc,156) : SharpTransition(cyc,156,1,180)
   ChangeAnim(v,150) : SharpTransition(v,150,1,0)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;SIDE SLAM
 If pAnim(cyc)=351
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=400 : animB=401 : pAnimSpeed#(cyc)=2.0
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(110/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(130/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.25) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),2)
  If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,1,3)
  If pAnimTim(cyc)=>Int(30/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(83/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,1.0)
  If pAnimTim(cyc)=>Int(65/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(103/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pAnimTim(cyc)=>Int(65/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(103/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(83/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,7,2)
  If pAnimTim(cyc)>Int(140/pAnimSpeed#(cyc))
   ChangeAnim(cyc,154) : SharpTransition(cyc,154,1,90)
   ChangeAnim(v,150) : SharpTransition(v,150,1,270)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;SIDE BACKBREAKER
 If pAnim(cyc)=352
  v=pGrappling(cyc) : pExcusedItems(v)=1
  animA=402 : animB=403 : pAnimSpeed#(cyc)=1.9
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(105/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(125/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.25) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),2)
  If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,0,3)
  If pAnimTim(cyc)=>Int(30/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(100/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,1.0)
  If pAnimTim(cyc)=>Int(65/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(125/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(78/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,7,2)
  If pAnimTim(cyc)=Int(103/pAnimSpeed#(cyc)) Then MoveFallEffect(v)
  If pAnimTim(cyc)>Int(140/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,0)
   ChangeAnim(v,152) : SharpTransition(v,152,1,90)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;INVERTED ATOMIC DROP
 If pAnim(cyc)=353
  v=pGrappling(cyc) : pExcusedItems(v)=1
  animA=404 : animB=405 : pAnimSpeed#(cyc)=2.1
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.25) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),1)
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,0,3)
  If pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(110/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,1.0)
  If pAnimTim(cyc)=>Int(65/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(130/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(65/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,6,2)
  If pAnimTim(cyc)=Int(110/pAnimSpeed#(cyc)) Then MoveFallEffect(v)
  If pAnimTim(cyc)>Int(160/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,0)
   ChangeAnim(v,150) : SharpTransition(v,150,1,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;BACKBREAKER
 If pAnim(cyc)=354
  v=pGrappling(cyc) : pExcusedItems(v)=1
  animA=406 : animB=407 : pAnimSpeed#(cyc)=2.0
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(115/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(135/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.25) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),1)
  If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,0,3)
  If pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(115/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,1.0)
  If pAnimTim(cyc)=>Int(70/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(135/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(85/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,7,2)
  If pAnimTim(cyc)=Int(115/pAnimSpeed#(cyc)) Then MoveFallEffect(v)
  If pAnimTim(cyc)>Int(150/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,0)
   ChangeAnim(v,152) : SharpTransition(v,152,1,90)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;STUNNER
 If pAnim(cyc)=355
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=408 : animB=409 : pAnimSpeed#(cyc)=2.0
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(12/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(105/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),5)
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,0,5)
  If pAnimTim(cyc)=>Int(10/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(43/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-1.0)
  If pAnimTim(cyc)=>Int(30/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(85/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pAnimTim(cyc)=>Int(30/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(100/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(43/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,9,2)
  If pAnimTim(cyc)=Int(85/pAnimSpeed#(cyc)) Then MoveFallEffect(v)
  If pAnimTim(cyc)>Int(130/pAnimSpeed#(cyc))
   ChangeAnim(cyc,156) : SharpTransition(cyc,156,1,0)
   ChangeAnim(v,150) : SharpTransition(v,150,1,270)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;ROCK BOTTOM
 If pAnim(cyc)=356
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=410 : animB=411 : pAnimSpeed#(cyc)=1.9
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(110/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(130/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.25) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(55/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc))
   ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),5)
   BreakMove(cyc,v,1,5)
  EndIf
  If pAnimTim(cyc)=>Int(15/pAnimSpeed#(cyc)) And pAnimTim(cyc)<Int(50/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,1.0)
  If pAnimTim(cyc)=>Int(50/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(80/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,0.5)
  If pAnimTim(cyc)=>Int(60/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(100/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pAnimTim(cyc)=>Int(60/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(100/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(80/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,3,9,2)
  If pAnimTim(cyc)>Int(135/pAnimSpeed#(cyc))
   ChangeAnim(cyc,156) : SharpTransition(cyc,156,1,0)
   ChangeAnim(v,150) : SharpTransition(v,150,1,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;GERMAN SUPLEX
 If pAnim(cyc)=357
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=412 : animB=413 : pAnimSpeed#(cyc)=2.25
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(55/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(135/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(165/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.25)
  If pAnimTim(cyc)=Int(55/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If v>0 And pAnimTim(cyc)=Int(80/pAnimSpeed#(cyc))
   ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),3)
   BreakMove(cyc,v,0,4)
  EndIf
  If pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(104/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-1.0)
  If pAnimTim(cyc)=>Int(85/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(150/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If v>0 And pAnimTim(cyc)=>Int(85/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(150/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If v>0 And pAnimTim(cyc)=Int(104/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,3,8,1)
  If v>0 And pAnimTim(cyc)=Int(130/pAnimSpeed#(cyc)) Then MoveFallEffect(v)
  If pAnimTim(cyc)>Int(190/pAnimSpeed#(cyc))
   ChangeAnim(cyc,156) : SharpTransition(cyc,156,1,180)
   If v>0 Then ChangeAnim(v,152) : SharpTransition(v,152,1,0)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveThrowOut(cyc,v,Int(85/pAnimSpeed#(cyc)),Int(95/pAnimSpeed#(cyc)),4)
 EndIf
 ;BACK SUPLEX
 If pAnim(cyc)=358
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=414 : animB=415 : pAnimSpeed#(cyc)=2.1
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(160/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(180/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.25)
  If pAnimTim(cyc)=Int(75/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.5) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If v>0 And pAnimTim(cyc)=Int(105/pAnimSpeed#(cyc))
   ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),1)
   BreakMove(cyc,v,1,3)
  EndIf
  If pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(129/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-0.5)
  If pAnimTim(cyc)=>Int(115/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(150/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If v>0 And pAnimTim(cyc)=>Int(115/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(150/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If v>0 And pAnimTim(cyc)=Int(129/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,3,6,2)
  If pAnimTim(cyc)>Int(190/pAnimSpeed#(cyc))
   ChangeAnim(cyc,154) : SharpTransition(cyc,154,1,180)
   If v>0 Then ChangeAnim(v,150) : SharpTransition(v,150,1,0)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveThrowOut(cyc,v,Int(105/pAnimSpeed#(cyc)),Int(115/pAnimSpeed#(cyc)),4)
 EndIf
 ;ATOMIC DROP
 If pAnim(cyc)=359
  v=pGrappling(cyc) : pExcusedItems(v)=1
  animA=416 : animB=417 : pAnimSpeed#(cyc)=2.25
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(55/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.25) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(105/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=Int(105/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),1)
  If pAnimTim(cyc)=Int(105/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,0,3)
  If pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(175/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,0.5)
  If pAnimTim(cyc)=>Int(120/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(200/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(130/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,6,1)
  If pAnimTim(cyc)=Int(175/pAnimSpeed#(cyc)) Then MoveFallEffect(v)
  If pAnimTim(cyc)>Int(235/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,0)
   ChangeAnim(v,150) : SharpTransition(v,150,1,90)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;DROP TOE HOLD
 If pAnim(cyc)=360
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=418 : animB=419 : pAnimSpeed#(cyc)=2.5
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(100/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.5) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),1)
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,2,2)
  If pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(70/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-1.0)
  If pAnimTim(cyc)=>Int(35/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(90/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pAnimTim(cyc)=>Int(45/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(90/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,1,5,0)
  If pAnimTim(cyc)>Int(130/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,180)
   ChangeAnim(v,152) : SharpTransition(v,152,1,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;SAMOAN DROP
 If pAnim(cyc)=361
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=420 : animB=421 : pAnimSpeed#(cyc)=2.1
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(120/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(12/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(150/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.25) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc))
   ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),3)
   BreakMove(cyc,v,1,4)
  EndIf
  If pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)<Int(50/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-1.0)
  If pAnimTim(cyc)=>Int(50/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(88/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-0.5)
  If pAnimTim(cyc)=>Int(65/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(110/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pAnimTim(cyc)=>Int(65/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(110/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(88/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,3,7,2)
  If pAnimTim(cyc)>Int(150/pAnimSpeed#(cyc))
   ChangeAnim(cyc,154) : SharpTransition(cyc,154,1,180)
   ChangeAnim(v,150) : SharpTransition(v,150,1,270)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;DEATH VALLEY DRIVER
 If pAnim(cyc)=362
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=422 : animB=423 : pAnimSpeed#(cyc)=1.9
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(140/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(12/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(170/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.25) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(55/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=Int(55/pAnimSpeed#(cyc))
   ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),5)
   BreakMove(cyc,v,1,5)
  EndIf
  If pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)<Int(50/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,1.0)
  If pAnimTim(cyc)=>Int(50/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(88/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,0.5)
  If pAnimTim(cyc)=>Int(70/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(150/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pAnimTim(cyc)=>Int(70/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(150/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(88/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,9,2)
  If pAnimTim(cyc)=Int(120/pAnimSpeed#(cyc)) Then MoveFallEffect(v)
  If pAnimTim(cyc)>Int(195/pAnimSpeed#(cyc))
   ChangeAnim(cyc,156) : SharpTransition(cyc,156,1,0)
   ChangeAnim(v,150) : SharpTransition(v,150,1,0)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;SNAPMARE
 If pAnim(cyc)=363
  v=pGrappling(cyc) : pExcusedItems(v)=1
  animA=424 : animB=425 : pAnimSpeed#(cyc)=2.0
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(80/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(90/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.5) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),1)
  If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,0,2)
  If pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(58/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-1.0)
  If pAnimTim(cyc)=>Int(50/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(80/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(58/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,1,5,2)
  If pAnimTim(cyc)>Int(100/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,180)
   ChangeAnim(v,150) : SharpTransition(v,150,1,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;NECKBREAKER
 If pAnim(cyc)=364
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=426 : animB=427 : pAnimSpeed#(cyc)=1.8
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(75/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(110/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.25)
  If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),1)
  If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,0,4)
  If pAnimTim(cyc)=>Int(10/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(48/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-0.5)
  If pAnimTim(cyc)=>Int(30/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(75/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pAnimTim(cyc)=>Int(30/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(105/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(48/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,7,2)
  If pAnimTim(cyc)=Int(100/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sFall,22050,0.5)
  If pAnimTim(cyc)>Int(130/pAnimSpeed#(cyc))
   ChangeAnim(cyc,156) : SharpTransition(cyc,156,1,0)
   ChangeAnim(v,150) : SharpTransition(v,150,1,320)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;DDT
 If pAnim(cyc)=365
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=428 : animB=429 : pAnimSpeed#(cyc)=2.0
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(100/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(125/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc))
   ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),1)
   BreakMove(cyc,v,0,3)
  EndIf
  If pAnimTim(cyc)=>Int(15/pAnimSpeed#(cyc)) And pAnimTim(cyc)<Int(40/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-1.0)
  If pAnimTim(cyc)=>Int(40/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(68/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-0.5)
  If pAnimTim(cyc)=>Int(50/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(95/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pAnimTim(cyc)=>Int(50/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(125/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(68/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,7,2)
  If pAnimTim(cyc)=Int(90/pAnimSpeed#(cyc)) Then MoveFallEffect(v)
  If pAnimTim(cyc)>Int(150/pAnimSpeed#(cyc))
   ChangeAnim(cyc,156) : SharpTransition(cyc,156,1,180)
   ChangeAnim(v,150) : SharpTransition(v,150,1,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;TORNADO DDT
 If pAnim(cyc)=366
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=430 : animB=431 : pAnimSpeed#(cyc)=2.6
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(65/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(145/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(175/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.25)
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),3)
  If pAnimTim(cyc)=Int(85/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,2,5)
  If pAnimTim(cyc)=>Int(15/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(113/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,0.25)
  If pAnimTim(cyc)=>Int(90/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(145/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pAnimTim(cyc)=>Int(90/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(180/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(113/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,8,2)
  If pAnimTim(cyc)=Int(145/pAnimSpeed#(cyc)) Then MoveFallEffect(v)
  If pAnimTim(cyc)>Int(200/pAnimSpeed#(cyc))
   ChangeAnim(cyc,156) : SharpTransition(cyc,156,1,0)
   ChangeAnim(v,150) : SharpTransition(v,150,1,0)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;HIP TOSS
 If pAnim(cyc)=367
  v=pGrappling(cyc) : pExcusedItems(v)=1
  animA=432 : animB=433 : pAnimSpeed#(cyc)=2.6
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),1)
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,0,3)
  If v>0 And pAnimTim(cyc)=>Int(10/pAnimSpeed#(cyc)) And pAnimTim(cyc)<Int(45/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-1.0)
  If v>0 And pAnimTim(cyc)=>Int(45/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(78/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-0.5) 
  If v>0 And pAnimTim(cyc)=>Int(60/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(100/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If v>0 And pAnimTim(cyc)=Int(78/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,1,5,1)
  If pAnimTim(cyc)>Int(140/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,180)
   If v>0 Then ChangeAnim(v,150) : SharpTransition(v,150,1,180)
   EndMove(cyc,v)
  EndIf
  If pAnimTim(cyc)=<Int(62/pAnimSpeed#(cyc)) Then faller=7 Else faller=3
  If v>0 Then MoveThrowOut(cyc,v,Int(60/pAnimSpeed#(cyc)),Int(65/pAnimSpeed#(cyc)),faller)
 EndIf
 ;GUTWRENCH SUPLEX
 If pAnim(cyc)=368
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=434 : animB=435 : pAnimSpeed#(cyc)=2.0
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(105/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(120/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(55/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),1)
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,0,3)
  If v>0 And pAnimTim(cyc)=>Int(10/pAnimSpeed#(cyc)) And pAnimTim(cyc)<Int(55/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-1.0)
  If v>0 And pAnimTim(cyc)=>Int(55/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(78/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-0.5)
  If pAnimTim(cyc)=>Int(65/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(98/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If v>0 And pAnimTim(cyc)=>Int(65/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(98/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If v>0 And pAnimTim(cyc)=Int(78/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,3,6,2)
  If pAnimTim(cyc)>Int(130/pAnimSpeed#(cyc))
   ChangeAnim(cyc,156) : SharpTransition(cyc,156,1,135)
   If v>0 Then ChangeAnim(v,150) : SharpTransition(v,150,1,180)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveThrowOut(cyc,v,Int(62/pAnimSpeed#(cyc)),Int(67/pAnimSpeed#(cyc)),3)
 EndIf
 ;RUSSIAN LEGSWEEP
 If pAnim(cyc)=369
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=436 : animB=437 : pAnimSpeed#(cyc)=2.3
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(65/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(140/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(65/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),2)
  If pAnimTim(cyc)=Int(85/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,0,4)
  If pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(108/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,0.5)
  If pAnimTim(cyc)=>Int(90/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(128/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pAnimTim(cyc)=>Int(90/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(128/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(108/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,7,2)
  If pAnimTim(cyc)>Int(170/pAnimSpeed#(cyc))
   ChangeAnim(cyc,154) : SharpTransition(cyc,154,1,0)
   ChangeAnim(v,150) : SharpTransition(v,150,1,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;SPEAR
 If pAnim(cyc)=370
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=438 : animB=439 : pAnimSpeed#(cyc)=2.25
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(115/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(95/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),1)
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,1,2)
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) And v>0
   ProduceSound(pLimb(v,36),sPain(Rnd(1,8)),GetVoice(v),0)
   ProduceSound(pLimb(cyc,36),sImpact(7),22050,1)
   ProduceSound(pLimb(cyc,36),sThud,22050,0)
   pHealth(v)=pHealth(v)-pStrength(cyc)
   If EntertainViable(cyc,v) Then entScore=entScore+pStrength(cyc)
   WeaponImpact(cyc,v,1)
  EndIf
  If pAnimTim(cyc)=>Int(15/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(73/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,0.25)
  If pAnimTim(cyc)=>Int(50/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(93/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pAnimTim(cyc)=>Int(50/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(93/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(73/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,3,4,1) : pDT(v)=pDT(v)+Rnd(0,50)
  If pAnimTim(cyc)>Int(125/pAnimSpeed#(cyc))
   ChangeAnim(cyc,156) : SharpTransition(cyc,156,1,0)
   ChangeAnim(v,150) : SharpTransition(v,150,1,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;STANDING CLOTHESLINE
 If pAnim(cyc)=371
  v=pGrappling(cyc) : pExcusedItems(v)=1
  animA=440 : animB=441 : pAnimSpeed#(cyc)=2.1
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(100/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(115/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),1)
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,1,1)
  If pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) And v>0
   ProduceSound(pLimb(v,36),sPain(Rnd(1,8)),GetVoice(v),0)
   ProduceSound(pLimb(cyc,36),sImpact(7),22050,1)
   ProduceSound(pLimb(cyc,36),sThud,22050,0)
   pHealth(v)=pHealth(v)-pStrength(cyc)
   If EntertainViable(cyc,v) Then entScore=entScore+pStrength(cyc)
   WeaponImpact(cyc,v,1)
  EndIf
  If pAnimTim(cyc)=>Int(15/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(68/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,0.25)
  If pAnimTim(cyc)=>Int(50/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(100/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(68/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,3,4,1) : pDT(v)=pDT(v)+Rnd(0,50)
  If pAnimTim(cyc)>Int(125/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,0)
   ChangeAnim(v,150) : SharpTransition(v,150,1,270)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;CHOKE SLAM
 If pAnim(cyc)=372
  v=pGrappling(cyc) : pExcusedItems(v)=1
  animA=442 : animB=443 : pAnimSpeed#(cyc)=2.25
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(17/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Then Pop(cyc,Rnd(2,9),0)
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.25) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(80/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0)
  If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) 
   ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),3)
   BreakMove(cyc,v,1,4)
  EndIf
  If v>0 And pAnimTim(cyc)=>Int(10/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(103/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,1.0)
  If v>0 And pAnimTim(cyc)=>Int(80/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(123/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If v>0 And pAnimTim(cyc)=Int(103/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,8,2)
  If pAnimTim(cyc)>Int(165/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,315)
   If v>0 Then ChangeAnim(v,150) : SharpTransition(v,150,1,180)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveThrowOut(cyc,v,Int(75/pAnimSpeed#(cyc)),Int(85/pAnimSpeed#(cyc)),2)
 EndIf
 ;X-FACTOR
 If pAnim(cyc)=373
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=444 : animB=445 : pAnimSpeed#(cyc)=2.5
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(135/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(150/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),5)
  If pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,2,5)
  If pAnimTim(cyc)=>Int(15/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(88/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-0.5)
  If pAnimTim(cyc)=>Int(60/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(108/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pAnimTim(cyc)=>Int(60/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(108/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(88/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,9,2)
  If pAnimTim(cyc)>Int(160/pAnimSpeed#(cyc))
   ChangeAnim(cyc,154) : SharpTransition(cyc,154,1,225)
   ChangeAnim(v,152) : SharpTransition(v,152,1,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;UNDERHOOK SUPLEX
 If pAnim(cyc)=374
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=446 : animB=447 : pAnimSpeed#(cyc)=2.3
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(55/pAnimSpeed#(cyc)) 
   ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),2)
   BreakMove(cyc,v,0,4)
  EndIf
  If v>0 And pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(103/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-0.5)
  If pAnimTim(cyc)=>Int(85/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(123/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If v>0 And pAnimTim(cyc)=>Int(85/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(123/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If v>0 And pAnimTim(cyc)=Int(103/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,3,7,1)
  If pAnimTim(cyc)>Int(155/pAnimSpeed#(cyc))
   ChangeAnim(cyc,156) : SharpTransition(cyc,156,1,180)
   If v>0 Then ChangeAnim(v,150) : SharpTransition(v,150,1,180)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveThrowOut(cyc,v,Int(83/pAnimSpeed#(cyc)),Int(90/pAnimSpeed#(cyc)),3)
 EndIf
 ;REVERSE DDT
 If pAnim(cyc)=375
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=448 : animB=449 : pAnimSpeed#(cyc)=1.9
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(100/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(120/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.25)
  If pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),3)
  If pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,0,4)
  If pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(63/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-1.0)
  If pAnimTim(cyc)=>Int(45/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(83/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pAnimTim(cyc)=>Int(45/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(83/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(63/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,8,2)
  If pAnimTim(cyc)>Int(125/pAnimSpeed#(cyc))
   ChangeAnim(cyc,154) : SharpTransition(cyc,154,1,270)
   ChangeAnim(v,150) : SharpTransition(v,150,1,0)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;REVERSE SUPLEX
 If pAnim(cyc)=376
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=450 : animB=451 : pAnimSpeed#(cyc)=2.0
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(140/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(160/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(58/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),2)
  If pAnimTim(cyc)=Int(75/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,0,4)
  If v>0 And pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(108/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-1.0)
  If pAnimTim(cyc)=>Int(90/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(128/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If v>0 And pAnimTim(cyc)=>Int(90/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(128/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If v>0 And pAnimTim(cyc)=Int(108/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,8,2)
  If pAnimTim(cyc)>Int(170/pAnimSpeed#(cyc))
   ChangeAnim(cyc,154) : SharpTransition(cyc,154,1,180)
   If v>0 Then ChangeAnim(v,152) : SharpTransition(v,152,1,0)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveThrowOut(cyc,v,Int(90/pAnimSpeed#(cyc)),Int(96/pAnimSpeed#(cyc)),8)
 EndIf
 ;MDKO
 If pAnim(cyc)=377
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=452 : animB=453 : pAnimSpeed#(cyc)=2.25
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(135/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(150/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),5)
  If pAnimTim(cyc)=Int(55/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,0,5)
  If pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(87/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-1.0)
  If pAnimTim(cyc)=>Int(70/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(108/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pAnimTim(cyc)=>Int(70/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(130/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(87/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,9,2)
  If pAnimTim(cyc)=Int(120/pAnimSpeed#(cyc)) Then ProduceSound(p(v),sFall,22050,0)
  If pAnimTim(cyc)>Int(160/pAnimSpeed#(cyc))
   ChangeAnim(cyc,154) : SharpTransition(cyc,154,1,180)
   ChangeAnim(v,150) : SharpTransition(v,150,1,0)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;VICTORY ROLL
 If pAnim(cyc)=378
  ;apply
  v=pGrappling(cyc)
  animA=454 : animB=455
  If pAnimStage(cyc)=1
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.25 
    Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0
    Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
   EndIf 
   If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(80/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.5)
   If pAnimTim(cyc)=Int(65/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(65/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99 
   If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then Pop(cyc,Rnd(2,9),0)
   If pAnimTim(cyc)=Int(100/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,1,3,1)
   If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),1)
   If pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,2,4)
   If pAnimTim(cyc)=>Int(80/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),1)
   If pAnimTim(cyc)=>Int(80/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),1)
   If pAnimTim(cyc)>Int(125/pAnimSpeed#(cyc)) Then pAnimStage(cyc)=2 : pAnimTim(cyc)=0
  EndIf
  ;hold
  If pAnimStage(cyc)=2
   If pAnimTim(cyc)=0
    Pop(cyc,Rnd(2,9),0)
    ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
    pAnimSpeed#(cyc)=Rnd(0.5,1.0)
    Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,animA+2),5
    Animate p(v),1,pAnimSpeed#(cyc),pSeq(v,animB+2),5
    pPinning(cyc)=v : pPinner(v)=cyc
    pAutoTim(cyc)=0 : pWarned(cyc)=0
    pDT(v)=GetDT(v,Rnd(250,750))
    entScore=entScore+(charPopularity(pChar(cyc))*3)
   EndIf
   MovePin(cyc,v)
  EndIf
  ;release
  If pAnimStage(cyc)=3
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.0
    Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA+4),5
    Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB+4),5
   EndIf
   If pAnimTim(cyc)=Int(5/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.25)
   If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Then MoveFallEffect(v)
   If pAnimTim(cyc)=>Int(10/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(50/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),1)
   If pAnimTim(cyc)=>Int(10/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(50/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),1)
   If pAnimTim(cyc)>Int(70/pAnimSpeed#(cyc))
    ChangeAnim(cyc,154) : SharpTransition(cyc,154,1,0)
    ChangeAnim(v,154) : SharpTransition(v,154,1,0)
    EndMove(cyc,v)
   EndIf
  EndIf
 EndIf
 ;TEST OF STRENGTH
 If pAnim(cyc)=379
  ;apply
  v=pGrappling(cyc)
  animA=460 : animB=461
  If pAnimStage(cyc)=1
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.0 
    Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0
    Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
   EndIf 
   If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,22050,0.25)
   If pAnimTim(cyc)=Int(37/pAnimSpeed#(cyc)) 
    ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
    ProduceSound(p(cyc),sImpact(1),22050,0.5)
    ProduceSound(p(v),sImpact(3),22050,0.5)
    Pop(cyc,Rnd(2,9),0) : Pop(v,Rnd(2,9),0)
    entScore=entScore+charPopularity(pChar(cyc))
    entScore=entScore+charPopularity(pChar(v))
   EndIf
   If pAnimTim(cyc)=Int(85/pAnimSpeed#(cyc)) 
    ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
    ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0)
    ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),0)
   EndIf
   If pAnimTim(cyc)=Int(120/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sFall,22050,0.25) : pStepTim#(cyc)=99
   If pAnimTim(cyc)=Int(130/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),2)
   If pAnimTim(cyc)>Int(130/pAnimSpeed#(cyc)) Then pAnimStage(cyc)=2 : pAnimTim(cyc)=0
  EndIf
  ;wrench
  If pAnimStage(cyc)=2
   If pAnimTim(cyc)=0
    Pop(cyc,Rnd(2,9),0)
    ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0)
    ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),0)
    ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
    If pSpecial(cyc)>0 Then pAnimSpeed#(cyc)=0.75 Else pAnimSpeed#(cyc)=Rnd(0.25,0.5)
    Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,animA+2),5
    Animate p(v),1,pAnimSpeed#(cyc),pSeq(v,animB+2),5
   EndIf
   HoldEffects(cyc,v,2,0.01,10)
  EndIf
  ;leave
  If pAnimStage(cyc)=3
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.5
    Animate p(cyc),3,-pAnimSpeed#(cyc),pSeq(cyc,animA),5
    Animate p(v),3,-pAnimSpeed#(cyc),pSeq(v,animB),5
   EndIf
   If pAnimTim(cyc)=Int(5/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.25)
   If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(75/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(100/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)>Int(130/pAnimSpeed#(cyc))
    ChangeAnim(cyc,301) : SharpTransition(cyc,305,1,0)
    ChangeAnim(v,302) : SharpTransition(v,306,1,180)
    BreakGrapple(cyc,0)
   EndIf
  EndIf
 EndIf
 ;CRUCIFIX POWERBOMB (RAZOR'S EDGE)
 If pAnim(cyc)=380
  v=pGrappling(cyc) : pExcusedItems(v)=1
  animA=464 : animB=465 : pAnimSpeed#(cyc)=2.0
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(130/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(100/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(230/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(130/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.5) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(160/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.5)
  If pAnimTim(cyc)=Int(100/pAnimSpeed#(cyc))
   Pop(cyc,Rnd(2,9),0) 
   ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),5)
   BreakMove(cyc,v,1,5)
  EndIf
  If v>0 And pAnimTim(cyc)=>Int(60/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(190/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,0.5)
  If pAnimTim(cyc)=>Int(170/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(200/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If v>0 And pAnimTim(cyc)=>Int(170/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(230/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If v>0 And pAnimTim(cyc)=Int(190/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,2,10,1)
  If pAnimTim(cyc)>Int(260/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,0)
   If v>0 Then ChangeAnim(v,150) : SharpTransition(v,150,1,180)
   EndMove(cyc,v)
  EndIf
  MoveThrowOut(cyc,v,Int(170/pAnimSpeed#(cyc)),Int(180/pAnimSpeed#(cyc)),2)
 EndIf
 ;GO TO SLEEP
 If pAnim(cyc)=381
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=466 : animB=467 : pAnimSpeed#(cyc)=1.6
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(12/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(150/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.25) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(80/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),5)
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,1,5)
  If pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)<Int(105/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,1.0)
  If pAnimTim(cyc)=>Int(90/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(155/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(102/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,9,2)
  If pAnimTim(cyc)=Int(140/pAnimSpeed#(cyc)) Then MoveFallEffect(v)
  If pAnimTim(cyc)>Int(190/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,270)
   ChangeAnim(v,150) : SharpTransition(v,150,1,90)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;ROLL-UP PIN
 If pAnim(cyc)=382
  ;apply
  v=pGrappling(cyc)
  animA=468 : animB=469
  If pAnimStage(cyc)=1
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.0
    Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0
    Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
   EndIf 
   If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.5)
   If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99 
   If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then Pop(cyc,Rnd(2,9),0)
   If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then ProduceSound(pLimb(cyc,36),sFall,22050,0)
   If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then MoveFallEffect(v);MoveImpact(cyc,v,1,2,1)
   If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),1)
   If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,0,3)
   If pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(75/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,0.5)
   If pAnimTim(cyc)=>Int(35/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(60/pAnimSpeed#(cyc)) Then FindSmashes(cyc,pLimbY#(cyc,36),1)
   If pAnimTim(cyc)=>Int(50/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(85/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),1)
   If pAnimTim(cyc)>Int(95/pAnimSpeed#(cyc)) Then pAnimStage(cyc)=2 : pAnimTim(cyc)=0
  EndIf
  ;hold
  If pAnimStage(cyc)=2
   If pAnimTim(cyc)=0
    Pop(cyc,Rnd(2,9),0)
    ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
    pAnimSpeed#(cyc)=Rnd(0.5,1.0)
    Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,animA+2),5
    Animate p(v),1,pAnimSpeed#(cyc),pSeq(v,animB+2),5
    pPinning(cyc)=v : pPinner(v)=cyc
    pAutoTim(cyc)=0 : pWarned(cyc)=0
    pDT(v)=GetDT(v,Rnd(250,750))
    entScore=entScore+(charPopularity(pChar(cyc))*2)
   EndIf
   MovePin(cyc,v)
  EndIf
  ;release
  If pAnimStage(cyc)=3
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.25
    Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA+4),5
    Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB+4),5
   EndIf
   If pAnimTim(cyc)=Int(5/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.25)
   If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then MoveFallEffect(v)
   If pAnimTim(cyc)=>Int(15/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),1)
   If pAnimTim(cyc)>Int(55/pAnimSpeed#(cyc))
    ChangeAnim(cyc,156) : SharpTransition(cyc,156,1,90)
    ChangeAnim(v,154) : SharpTransition(v,154,1,180)
    EndMove(cyc,v)
   EndIf
  EndIf
 EndIf
 ;CRUCIFIX PIN
 If pAnim(cyc)=383
  ;apply
  v=pGrappling(cyc)
  animA=474 : animB=475
  If pAnimStage(cyc)=1
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=1.75
    Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0
    Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
   EndIf 
   If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(55/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.5)
   If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99 
   If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then Pop(cyc,Rnd(2,9),0)
   If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),2)
   If pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,2,4)
   If pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(90/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,0.25)
   If pAnimTim(cyc)=>Int(60/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(75/pAnimSpeed#(cyc)) Then FindSmashes(cyc,pLimbY#(cyc,36),1)
   If pAnimTim(cyc)=>Int(75/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(90/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),1)
   If pAnimTim(cyc)=Int(85/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,1,4,1)
   If pAnimTim(cyc)>Int(115/pAnimSpeed#(cyc)) Then pAnimStage(cyc)=2 : pAnimTim(cyc)=0
  EndIf
  ;hold
  If pAnimStage(cyc)=2
   If pAnimTim(cyc)=0
    Pop(cyc,Rnd(2,9),0)
    ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
    pAnimSpeed#(cyc)=Rnd(0.5,1.0)
    Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,animA+2),5
    Animate p(v),1,pAnimSpeed#(cyc),pSeq(v,animB+2),5
    pPinning(cyc)=v : pPinner(v)=cyc
    pAutoTim(cyc)=0 : pWarned(cyc)=0
    pDT(v)=GetDT(v,Rnd(250,750))
    entScore=entScore+(charPopularity(pChar(cyc))*3)
   EndIf
   MovePin(cyc,v)
  EndIf
  ;release
  If pAnimStage(cyc)=3
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.0
    Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA+4),5
    Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB+4),5
   EndIf
   If pAnimTim(cyc)=Int(5/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.25)
   If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then MoveFallEffect(v)
   If pAnimTim(cyc)=>Int(15/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),1)
   If pAnimTim(cyc)>Int(55/pAnimSpeed#(cyc))
    ChangeAnim(cyc,154) : SharpTransition(cyc,154,1,270)
    ChangeAnim(v,154) : SharpTransition(v,154,1,180)
    EndMove(cyc,v)
   EndIf
  EndIf
 EndIf
 ;FULL NELSON
 If pAnim(cyc)=384
  ;apply
  v=pGrappling(cyc)
  animA=480 : animB=481
  If pAnimStage(cyc)=1
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=1.5
    Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0
    Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
   EndIf 
   If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.25)
   If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),1)
   If pAnimTim(cyc)>Int(40/pAnimSpeed#(cyc)) Then pAnimStage(cyc)=2 : pAnimTim(cyc)=0
  EndIf
  ;wrench
  If pAnimStage(cyc)=2
   If pAnimTim(cyc)=0
    Pop(cyc,Rnd(2,9),0)
    ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),0)
    ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
    If pSpecial(cyc)>0 Then pAnimSpeed#(cyc)=0.75 Else pAnimSpeed#(cyc)=Rnd(0.25,0.5)
    Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,animA+2),0
    Animate p(v),1,pAnimSpeed#(cyc),pSeq(v,animB+2),0
   EndIf
   HoldEffects(cyc,v,2,0.015,10)
  EndIf
  ;leave
  If pAnimStage(cyc)=3
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.0
    Animate p(cyc),3,-pAnimSpeed#(cyc),pSeq(cyc,animA),5
    Animate p(v),3,-pAnimSpeed#(cyc),pSeq(v,animB),5
   EndIf
   If pAnimTim(cyc)=Int(5/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.25)
   If pAnimTim(cyc)=Int(5/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)>Int(40/pAnimSpeed#(cyc))
    ChangeAnim(cyc,301) : SharpTransition(cyc,305,1,0)
    ChangeAnim(v,302) : SharpTransition(v,306,1,180)
    BreakGrapple(cyc,0) : pHP(v)=0
   EndIf
  EndIf
 EndIf
 ;FULL NELSON SUPLEX
 If pAnim(cyc)=385
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=484 : animB=485 : pAnimSpeed#(cyc)=1.75
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(155/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.25)
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),3)
  If pAnimTim(cyc)=Int(75/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,0,5)
  If pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(50/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-0.5)
  If pAnimTim(cyc)=>Int(50/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(105/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,-1.0)
  If pAnimTim(cyc)=>Int(90/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(120/pAnimSpeed#(cyc)) Then FindSmashes(cyc,pLimbY#(cyc,36),0)
  If pAnimTim(cyc)=>Int(90/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(135/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(105/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,9,2)
  If pAnimTim(cyc)>Int(175/pAnimSpeed#(cyc))
   ChangeAnim(cyc,154) : SharpTransition(cyc,154,1,180)
   ChangeAnim(v,152) : SharpTransition(v,152,1,0)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;SKULL CRUSHING FINALE
 If pAnim(cyc)=386
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=486 : animB=487 : pAnimSpeed#(cyc)=1.75
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(130/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.25)
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),5)
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,0,5)
  If pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(105/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,0.5)
  If pAnimTim(cyc)=>Int(80/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(115/pAnimSpeed#(cyc)) Then FindSmashes(cyc,pLimbY#(cyc,36),0)
  If pAnimTim(cyc)=>Int(80/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(115/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(100/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,9,2)
  If pAnimTim(cyc)>Int(140/pAnimSpeed#(cyc))
   ChangeAnim(cyc,156) : SharpTransition(cyc,156,1,0)
   ChangeAnim(v,152) : SharpTransition(v,152,1,0)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;F5
 If pAnim(cyc)=387
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=488 : animB=489 : pAnimSpeed#(cyc)=1.8
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(12/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(150/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.25) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(75/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=Int(55/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),5)
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,1,5)
  If pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)<Int(120/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,1.0)
  If pAnimTim(cyc)=>Int(105/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(140/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pAnimTim(cyc)=>Int(95/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(150/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(118/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,9,0)
  If pAnimTim(cyc)>Int(165/pAnimSpeed#(cyc))
   ChangeAnim(cyc,154) : SharpTransition(cyc,154,1,180)
   ChangeAnim(v,150) : SharpTransition(v,150,1,0)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;THROAT TOSS
 If pAnim(cyc)=388
  v=pGrappling(cyc) : pExcusedItems(v)=1
  animA=490 : animB=491 : pAnimSpeed#(cyc)=1.5
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Then Pop(cyc,Rnd(2,9),0.5)
  If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.25) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then ProduceSound(p(v),sChoke,GetVoice(v),0.5)
  If pAnimTim(cyc)=Int(65/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0)
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),2)
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,1,4)
  If v>0 And pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(80/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,1.0)
  If v>0 And pAnimTim(cyc)=>Int(85/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(110/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If v>0 And pAnimTim(cyc)=Int(95/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,2,8,0)
  If pAnimTim(cyc)>Int(125/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,0)
   If v>0 Then ChangeAnim(v,150) : SharpTransition(v,150,1,180)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveThrowOut(cyc,v,Int(85/pAnimSpeed#(cyc)),Int(90/pAnimSpeed#(cyc)),2)
 EndIf
 ;ATTITUDE ADJUSTMENT
 If pAnim(cyc)=389
  v=pGrappling(cyc) : pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=492 : animB=493 : pAnimSpeed#(cyc)=1.8
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(12/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(145/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.25) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(75/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=Int(55/pAnimSpeed#(cyc)) Then ReverseMove(cyc,v,animA,animB,pAnimSpeed#(cyc),pAnimTim(cyc),5)
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,1,5)
  If pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)<Int(125/pAnimSpeed#(cyc)) Then MoveTurn(cyc,v,1.0)
  If pAnimTim(cyc)=>Int(110/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(125/pAnimSpeed#(cyc)) Then FindSmashes(cyc,pLimbY#(cyc,36),0)
  If pAnimTim(cyc)=>Int(100/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(135/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(120/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,4,9,2)
  If pAnimTim(cyc)>Int(155/pAnimSpeed#(cyc))
   ChangeAnim(cyc,156) : SharpTransition(cyc,156,1,90)
   ChangeAnim(v,150) : SharpTransition(v,150,1,90)
   EndMove(cyc,v)
  EndIf
 EndIf
 
 ;REVERSAL PROCESS
 If pAnim(cyc)=499
  ;rewind animation
  v=pGrappling(cyc)
  pRevTim#(cyc)=pRevTim#(cyc)-1.5
  If pRevTim#(cyc)=>0 Then SetAnimTime p(cyc),pRevTim#(cyc)*pRevSpeed#(cyc),pSeq(cyc,pRevAnim(cyc))
  If pRevTim#(cyc)=>0 Then SetAnimTime p(v),pRevTim#(cyc)*pRevSpeed#(cyc),pSeq(v,pRevAnim(v))
  ;switch control
  If pRevTim#(cyc)=<0
   ChangeAnim(cyc,302)
   ChangeAnim(v,301) : SharpTransition(v,301,1,180)
   pGrappling(cyc)=0 : pGrappler(v)=0
   pGrappling(v)=cyc : pGrappler(cyc)=v
   move=moveAnim(1,charMove(pChar(v),Rnd(1,12)))
   randy=Rnd(-1,10)
   If randy=<1 Then move=310
   If matchState=3 And matchPins>0 And FallsCount(cyc) And LegalMan(cyc) And LegalMan(v)
    rando=Rnd(0,1)
    If randy=2 And charSkill(pChar(v))=>70
     If rando=0 Then move=382 Else move=345 
    EndIf
    If randy=3 And charAgility(pChar(v))=>80
     If rando=0 Then move=383 Else move=378 
    EndIf
   EndIf 
   randy=Rnd(0,25)
   If randy=<1 Or pSpecial(v)>0 Then move=moveAnim(1,charMove(pChar(v),15))
   If randy=2 Then move=moveAnim(1,charMove(pChar(cyc),15))
   If move<310 Or pRole(cyc)=1 Or pRole(v)=1 Or pTeam(cyc)=pTeam(v) Then move=310
   TriggerMove(v,move)
   Animate p(v),3,1,pSeq(cyc,310),0
   Animate p(cyc),3,1,pSeq(cyc,311),0 
   PositionEntity p(cyc),pX#(cyc),pY#(cyc),pZ#(cyc)
   PositionEntity p(v),pX#(v),pY#(v),pZ#(v)
   pOldX#(cyc)=EntityX(pLimb(cyc,36),1) : pOldZ#(cyc)=EntityZ(pLimb(cyc,36),1)
   pOldX#(v)=EntityX(pLimb(v,36),1) : pOldZ#(v)=EntityZ(pLimb(v,36),1)
  EndIf
 EndIf
End Function

;--------------------------------------------------------------
;////////////////// RELATED FUNCTIONS /////////////////////////
;--------------------------------------------------------------

;FIX GRAPPLE POSITIONS
Function FixGrapple(cyc)
 v=pGrappling(cyc)
 ;cling to apron victim
 If pPlatform(v)=1 And pZ#(cyc)<blockZ1#(1)-1 Then pZ#(cyc)=blockZ1#(1)-1
 If pPlatform(v)=2 And pX#(cyc)<blockX1#(2)-1 Then pX#(cyc)=blockX1#(2)-1
 If pPlatform(v)=3 And pZ#(cyc)>blockZ2#(3)+1 Then pZ#(cyc)=blockZ2#(3)+1
 If pPlatform(v)=4 And pX#(cyc)>blockX2#(4)+1 Then pX#(cyc)=blockX2#(4)+1
 ;project victim from grappler
 PositionEntity p(v),pX#(cyc),pY#(cyc),pZ#(cyc)
 RotateEntity p(v),0,pA#(cyc),0
 MoveEntity p(v),0,0,15
 ;update co-ordinates
 pX#(v)=EntityX(p(v)) : pY#(v)=pY#(cyc) : pZ#(v)=EntityZ(p(v))
 pA#(v)=CleanAngle#(pA#(cyc)+180)
 PositionEntity p(v),pX#(v),pY#(v),pZ#(v)
 RotateEntity p(v),0,pA#(v),0
 ;pre-empt collisions
 EnforceBlocks(v)
 ;push grappler back?
 If pPlatform(cyc)=0 And pPlatform(v)=0
  PositionEntity dummy,pX#(v),pY#(v),pZ#(v)
  RotateEntity dummy,0,pA#(v),0
  MoveEntity dummy,0,0,15
  If EntityX(dummy)>pX#(cyc)-1 And EntityX(dummy)<pX#(cyc)+1 And EntityZ(dummy)>pZ#(cyc)-1 And EntityZ(dummy)<pZ#(cyc)+1
   satisfied=1
  Else
   MoveEntity p(cyc),0,0,-1
   pX#(cyc)=EntityX(p(cyc)) : pZ#(cyc)=EntityZ(p(cyc))
  EndIf
 EndIf
End Function

;APPLY GRAPPLE OFFSET
Function GrappleOffset(cyc,v,style) ;0=standing, 1=head, 2=legs
 ;standing grapple
 If style=0
  pBodyTXA#(cyc)=PercentOf#(charHeight(pChar(cyc))-charHeight(pChar(v)),85)
  pBodyTXA#(v)=PercentOf#(charHeight(pChar(v))-charHeight(pChar(cyc)),85)
  pBodyTXA#(cyc)=pBodyTXA#(cyc)+PercentOf#((pY#(cyc)+pElevation#(cyc))-(pY#(v)+pElevation#(v)),300)
  pBodyTXA#(v)=pBodyTXA#(v)+PercentOf#((pY#(v)+pElevation#(v))-(pY#(cyc)+pElevation#(cyc)),300)
 EndIf
 ;head grapple
 If style=1
  offset#=charHeight(pChar(cyc))-charHeight(pChar(v))
  If offset#<0 Then pBodyTXA#(cyc)=PercentOf#(offset#,104) : pBodyTXA#(v)=offset#/(-4)
  If offset#>0 Then pBodyTXA#(cyc)=PercentOf#(offset#,62) : pBodyTXA#(v)=offset#/(-2.5)
  If (pY#(cyc)+pElevation#(cyc))<(pY#(v)+pElevation#(v))
   pBodyTXA#(cyc)=pBodyTXA#(cyc)+PercentOf#((pY#(cyc)+pElevation#(cyc))-(pY#(v)+pElevation#(v)),500)
  Else
   pBodyTXA#(cyc)=pBodyTXA#(cyc)+PercentOf#((pY#(cyc)+pElevation#(cyc))-(pY#(v)+pElevation#(v)),300)
  EndIf
 EndIf
 ;leg grapple
 If style=2
  offset#=charHeight(pChar(cyc))-charHeight(pChar(v))
  If offset#<0 Then pBodyTXA#(cyc)=PercentOf#(offset#,45)
  If offset#>0 Then pBodyTXA#(cyc)=offset#/8
  If (pY#(cyc)+pElevation#(cyc))<(pY#(v)+pElevation#(v))
   pBodyTXA#(cyc)=pBodyTXA#(cyc)+PercentOf#((pY#(cyc)+pElevation#(cyc))-(pY#(v)+pElevation#(v)),300)
  Else
   pBodyTXA#(cyc)=pBodyTXA#(cyc)+PercentOf#((pY#(cyc)+pElevation#(cyc))-(pY#(v)+pElevation#(v)),150)
  EndIf
 EndIf
End Function

;MANAGE GRAPPLE
Function ManageGrapple(cyc)
 v=pGrappling(cyc)
 ;break if interrupted
 If pAnim(cyc)<300 Or pAnim(v)<300 Then BreakGrapple(cyc,0) 
 ;break on command
 If pAnimTim(cyc)>10 Or pOldAnim(cyc)<>300
  If pControl(cyc)>0 And cGrapple(cyc)=1 And DirPressed(cyc)=0 Then BreakGrapple(cyc,0)
 EndIf
 ;break if out of touch
 If pAnimTim(cyc)>5
  If InProximity(cyc,v,16)=0 Or InLine(cyc,p(v),25)=0 Or InLine(v,p(cyc),25)=0 Then BreakGrapple(cyc,0)
 EndIf
 ;break over time
 chance=200
 If pAnim(cyc)=301 Then chance=chance/2
 If RushViable(v) And pOutCount(cyc)<pOutCount(v) Then chance=chance/2
 If optLevel=0 And pControl(cyc)=0 And pControl(v)>0 Then chance=chance/2
 If optLevel=6 And pControl(cyc)>0 And pControl(v)=0 Then chance=chance/2
 randy=Rnd(0,chance)
 If randy=0 Or (randy=<1 And pOldAnim(cyc)=300)
  If pControl(v)=0 Or DirPressed(v) Or ActionPressed(v) Then BreakGrapple(cyc,-1)
 EndIf
 ;find moves
 If pGrappling(cyc)>0
  If pAnimTim(cyc)>5 Or pOldAnim(cyc)<>300 Or pMomentum(v)>0 Then FindMoves(cyc)
 EndIf
End Function

;BREAK GRAPPLE
Function BreakGrapple(cyc,style) ;-1=violent victim, 0=mutual, 1-6=violent grappler
 v=pGrappling(cyc)
 If v>0
  ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
  ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),0.5)
  pGrappler(v)=0 : pStretched(v)=0 : pGrappling(cyc)=0
  pHurtA#(cyc)=pA#(cyc)+180 : pStagger#(cyc)=0.5
  pHurtTim(cyc)=5 : pHurtTim(v)=5
  If style=<0 Then ChangeAnim(cyc,Rnd(102,105))
  If style=>1 And style=<3 Then ChangeAnim(cyc,59+style)
  If style=4 Then ChangeAnim(cyc,67)
  If style=5 Then ChangeAnim(cyc,66) : pSpurt(cyc)=11
  If style=6 Then ChangeAnim(cyc,66) : pSpurt(cyc)=1
  If style=-1 Then ChangeAnim(v,Rnd(60,61))
 EndIf
End Function

;FIND MOVES
Function FindMoves(cyc)
 ;standard options
 v=pGrappling(cyc) : move=0
 If pPlatform(cyc)=0 And pPlatform(v)=0 
  ;CPU commands
  If pControl(cyc)=0
   randy=Rnd(-1,50)
   If randy=<0 
    rando=Rnd(0,2)
    If rando=<1 Then move=moveAnim(1,charMove(pChar(cyc),15)) Else move=moveAnim(1,charMove(pChar(v),15)) ;premature specials
   EndIf
   If randy=>1 And randy=<12 
    move=moveAnim(1,charMove(pChar(cyc),randy)) ;random move
   EndIf
   If randy=>13 And randy=<15 And pSpecial(cyc)=0 Then move=310 ;whip
   If matchState=3 And matchCountOuts=3 And matchCage=0 ;rumble assistance
    If randy=>16 And randy=<18 Then move=310
    If randy=>19 And randy=<20 Then move=316
    If randy=21 Then move=346
   EndIf
   If pRole(cyc)=1 Or pRole(v)=1 Or pTeam(cyc)=pTeam(v)
    If move>310 Then move=310 ;safe move for team-mates
   EndIf
  EndIf
  ;HUMAN commands
  If pControl(cyc)>0 
   If cGrapple(cyc)=1 And DirPressed(cyc) Then move=310 ;whip
   If cAttack(cyc)=1 And DirPressed(cyc)=0 Then move=moveAnim(1,charMove(pChar(cyc),1)) ;move A centre
   If cAttack(cyc)=1 And cUp(cyc) Then move=moveAnim(1,charMove(pChar(cyc),2)) ;move A up
   If cAttack(cyc)=1 And cDown(cyc) Then move=moveAnim(1,charMove(pChar(cyc),3)) ;move A down
   If cAttack(cyc)=1 And (cLeft(cyc) Or cRight(cyc)) Then move=moveAnim(1,charMove(pChar(cyc),4)) ;move A side
   If cRun(cyc)=1 And DirPressed(cyc)=0 Then move=moveAnim(1,charMove(pChar(cyc),5)) ;move B centre
   If cRun(cyc)=1 And cUp(cyc) Then move=moveAnim(1,charMove(pChar(cyc),6)) ;move B up
   If cRun(cyc)=1 And cDown(cyc) Then move=moveAnim(1,charMove(pChar(cyc),7)) ;move B down
   If cRun(cyc)=1 And (cLeft(cyc) Or cRight(cyc)) Then move=moveAnim(1,charMove(pChar(cyc),8)) ;move B side
   If cPickUp(cyc)=1 And DirPressed(cyc)=0 Then move=moveAnim(1,charMove(pChar(cyc),9)) ;move C centre
   If cPickUp(cyc)=1 And cUp(cyc) Then move=moveAnim(1,charMove(pChar(cyc),10)) ;move C up
   If cPickUp(cyc)=1 And cDown(cyc) Then move=moveAnim(1,charMove(pChar(cyc),11)) ;move C down
   If cPickUp(cyc)=1 And (cLeft(cyc) Or cRight(cyc)) Then move=moveAnim(1,charMove(pChar(cyc),12)) ;move C side
   If cAttack(cyc)=1 And cRun(cyc)=1 Then move=moveAnim(1,charMove(pChar(cyc),15)) ;premature special
   If cGrapple(cyc)=1 And cPickUp(cyc)=1 Then move=moveAnim(1,charMove(pChar(v),15)) ;premature copycat
  EndIf
  ;novelties
  If pMomentum(v)>0 Then move=moveAnim(1,charMove(pChar(cyc),13)) ;momentum override
  If pSpecial(cyc)>0 And move<>310 ;special override
   move=moveAnim(1,charMove(pChar(cyc),15))
   randy=Rnd(0,10)
   If randy=<1 And pControl(cyc)=0 Then move=moveAnim(1,charMove(pChar(v),15))
   If pControl(cyc)>0 And cAttack(cyc)=1 And cRun(cyc)=1 Then move=moveAnim(1,charMove(pChar(v),15)) ;copycat
  EndIf
 EndIf
 ;apron variations
 apronMove=0
 If pPlatform(cyc)>0 And pPlatform(v)=0
  If matchCage>0 Then apronMove=Rnd(1,3) Else apronMove=313
 EndIf
 If pPlatform(cyc)=0 And pPlatform(v)>0
  If pControl(cyc)=0 And pRole(cyc)=1 And RingViable(v)=0 Then apronMove=Rnd(1,3) Else apronMove=314
 EndIf
 If pPlatform(cyc)>0 And pPlatform(v)>0 Then apronMove=Rnd(1,3)
 If apronMove>0
  randy=Rnd(0,10)
  If pControl(cyc)=0 And randy=0 Then move=apronMove
  If pControl(cyc)>0 
   If cGrapple(cyc)=1 And DirPressed(cyc) Then move=apronMove
   If cAttack(cyc)=1 Or cRun(cyc)=1 Or cPickUp(cyc)=1 Then move=apronMove
  EndIf
 EndIf
 ;convert whip into throw out?
 cpuFavour=0
 If pControl(cyc)=0 And LegalMan(v)=0 And pRole(v)<>1 Then cpuFavour=1
 If (move=310 Or cpuFavour=1) And pY#(cyc)=wStage# And pPlatform(cyc)=0 And pPlatform(v)=0 And matchCage=0 And matchCountOuts=<2
  If pZ#(cyc)>blockZ1#(1)-5 And pX#(cyc)>blockX1#(0)+35 And pX#(cyc)<blockX2#(0)-35 ;north side
   If SatisfiedAngle(pA#(cyc),180,45) Then pA#(cyc)=180 : pZ#(cyc)=blockZ1#(1)-1 : move=311
  EndIf
  If pX#(cyc)>blockX1#(2)-5 And pZ#(cyc)>blockZ1#(0)+35 And pZ#(cyc)<blockZ2#(0)-35 ;east side
   If SatisfiedAngle(pA#(cyc),90,45) Then pA#(cyc)=90 : pX#(cyc)=blockX1#(2)-1 : move=311
  EndIf
  If pZ#(cyc)<blockZ2#(3)+5 And pX#(cyc)>blockX1#(0)+35 And pX#(cyc)<blockX2#(0)-35 ;south side
   If SatisfiedAngle(pA#(cyc),0,45) Then pA#(cyc)=0 : pZ#(cyc)=blockZ2#(3)+1 : move=311
  EndIf
  If pX#(cyc)<blockX2#(4)+5 And pZ#(cyc)>blockZ1#(0)+35 And pZ#(cyc)<blockZ2#(0)-35 ;west side
   If SatisfiedAngle(pA#(cyc),270,45) Then pA#(cyc)=270 : pX#(cyc)=blockX2#(4)+1 : move=311
  EndIf
 EndIf
 ;convert whip into throw in?
 cpuFavour=0
 If pControl(cyc)=0 And matchState=3 And LegalMan(v) Then cpuFavour=1
 If (move=310 Or cpuFavour=1) And pY#(cyc)=wGround# And pPlatform(cyc)=0 And pPlatform(v)=0 And matchCage=0
  If pZ#(cyc)<blockZ2#(0)+5 And pZ#(cyc)>blockZ1#(0) And pX#(cyc)>blockX1#(0)+35 And pX#(cyc)<blockX2#(0)-35 ;north side
   If SatisfiedAngle(pA#(cyc),0,45) Then pA#(cyc)=0 : pZ#(cyc)=blockZ2#(0)+1 : move=312
  EndIf
  If pX#(cyc)<blockX2#(0)+5 And pX#(cyc)>blockX1#(0) And pZ#(cyc)>blockZ1#(0)+35 And pZ#(cyc)<blockZ2#(0)-35 ;east side
   If SatisfiedAngle(pA#(cyc),270,45) Then pA#(cyc)=270 : pX#(cyc)=blockX2#(0)+1 : move=312
  EndIf
  If pZ#(cyc)>blockZ1#(0)-5 And pZ#(cyc)<blockZ2#(0) And pX#(cyc)>blockX1#(0)+35 And pX#(cyc)<blockX2#(0)-35 ;south side
   If SatisfiedAngle(pA#(cyc),180,45) Then pA#(cyc)=180 : pZ#(cyc)=blockZ1#(0)-1 : move=312
  EndIf
  If pX#(cyc)>blockX1#(0)-5 And pX#(cyc)<blockX2#(0) And pZ#(cyc)>blockZ1#(0)+35 And pZ#(cyc)<blockZ2#(0)-35 ;west side
   If SatisfiedAngle(pA#(cyc),90,45) Then pA#(cyc)=90 : pX#(cyc)=blockX1#(0)-1 : move=312
  EndIf
 EndIf
 ;last minute break
 chance=8
 If RushViable(v) And pOutCount(cyc)<pOutCount(v) Then chance=chance/2
 If optLevel=0 And pControl(cyc)=0 And pControl(v)>0 Then chance=chance/2
 If optLevel=6 And pControl(cyc)>0 And pControl(v)=0 Then chance=chance/2
 randy=Rnd(0,chance)
 If randy=0 And move>0 And pMomentum(v)=0
  If pControl(v)=0 Or DirPressed(v) Or ActionPressed(v) Then BreakGrapple(cyc,-1) : move=0
 EndIf
 ;deliver chosen move 
 If move>0
  If move=>310 
   TriggerMove(cyc,move) 
  Else
   If pHP(v)<10 Then pHP(v)=10
   BreakGrapple(cyc,move)
  EndIf
 EndIf
End Function

;TRIGGER MOVE
Function TriggerMove(cyc,move)
 ;make victim copy grappler
 v=pGrappling(cyc)
 If move<700 Then pX#(v)=pX#(cyc) : pZ#(v)=pZ#(cyc) : pA#(v)=pA#(cyc)
 If move>700 And move<800 Then pX#(cyc)=pX#(v) : pZ#(cyc)=pZ#(v) : pA#(cyc)=pA#(v)
 If move>800 Then pX#(cyc)=pX#(v) : pZ#(cyc)=pZ#(v) : pA#(v)=pA#(cyc) 
 pY#(v)=pY#(cyc)
 pExcusedPlays(cyc)=1 : pExcusedPlays(v)=1
 ResetMultiSting(cyc) : ResetMultiSting(v)
 pWarned(cyc)=0
 ;trigger animations
 ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
 ChangeAnim(cyc,move) : ChangeAnim(v,309)
 If pGrappleAssist(cyc)>0
  a=pGrappleAssist(cyc)
  pX#(a)=pX#(cyc) : pZ#(a)=pZ#(cyc) 
  pY#(a)=pY#(cyc) : pA#(a)=pA#(cyc)
  pExcusedPlays(a)=1 : ResetMultiSting(a)
  ChangeAnim(a,800)
 EndIf
 ;protect CPU's from DQ
 If pControl(cyc)=0 And matchState=3 And matchRules=>2 And LegalMan(cyc) And InsideRing(pX#(cyc),pZ#(cyc),0) And pHolding(cyc)>0 And weapWear(pHolding(cyc))=0 
  If FindReferee(cyc,v)>0 Then DropWeapon(cyc,0)
 EndIf
End Function

;MOVE TURNING
Function MoveTurn(cyc,v,speed#)
 ;elimination assistance
 aimer#=-1
 If pControl(cyc)=0 And matchCage=0 And InsideRing(pX#(cyc),pZ#(cyc),-15)
  If (InsideRing(pX#(cyc),pZ#(cyc),-25)=0 And matchRules=0) Or matchCountOuts=3
   If NearestSide(pX#(cyc),pZ#(cyc))=1 Then aimer#=0
   If NearestSide(pX#(cyc),pZ#(cyc))=2 Then aimer#=270
   If NearestSide(pX#(cyc),pZ#(cyc))=3 Then aimer#=180
   If NearestSide(pX#(cyc),pZ#(cyc))=4 Then aimer#=90
  EndIf
 EndIf
 ;keep it inside
 If pControl(cyc)=0 And InsideRing(pX#(cyc),pZ#(cyc),-15) And InsideRing(pX#(cyc),pZ#(cyc),-45)=0
  If (matchRules=>1 And matchCountOuts<>3) Or (matchCage>0 And matchCountOuts=3)
   PositionEntity dummy,0,0,0
   PointEntity p(cyc),dummy
   aimer#=CleanAngle#(EntityYaw(p(cyc)))
   RotateEntity p(cyc),0,pA#(cyc),0
  EndIf
 EndIf
 ;execute turn
 If (DirPressed(cyc) And (pControl(cyc)>0 Or pAgenda(cyc)<>1)) Or aimer#=>0
  tA#=RequestAngle#(cyc)
  If aimer=>0 And speed#=>0 Then tA#=aimer#
  If aimer=>0 And speed#<0 Then tA#=CleanAngle#(aimer#+180) 
  turner#=BoostTurn#(pA#(cyc),tA#,speed#,3)
  pA#(cyc)=pA#(cyc)+ReachAngle#(pA#(cyc),tA#,turner#)
  pA#(v)=pA#(cyc)
 EndIf
End Function

;THROW MOVE OUT OF RING
Function MoveThrowOut(cyc,v,range1,range2,style)
 pExcusedEdges(v)=0
 If matchCage=0 And pAnimTim(cyc)=>range1-10 And pAnimTim(cyc)=<range2 Then pExcusedEdges(v)=1
 If matchCage=0 And pAnimTim(cyc)=>range1 And pAnimTim(cyc)=<range2 And InsideRing(pX#(cyc),pZ#(cyc),-15) And pY#(cyc)=wStage#
  If InsideRing(RealX#(v),RealZ#(v),-30)=0
   ;reset status
   pGrappling(cyc)=0 : pGrappler(v)=0
   pY#(v)=EntityY(FindChild(p(v),"Hips"),1)-5
   If pY#(v)<45 Then pY#(v)=45
   If pY#(v)>55 Then pY#(v)=55
   ;trigger new animation
   If style=1 Then pA#(v)=pA#(cyc) : pFlightA#(v)=pA#(cyc) : ChangeAnim(v,145) ;thrown forward, feet first onto back (slam)
   If style=2 Then pA#(v)=pA#(cyc)+180 : pFlightA#(v)=pA#(cyc) : ChangeAnim(v,145) ;thrown forward, head first onto back (chokeslam)
   If style=3 Then pA#(v)=pA#(cyc)+180 : pFlightA#(v)=pA#(cyc)+180 : ChangeAnim(v,145) ;thrown behind, feet first onto back (suplex)
   If style=4 Then pA#(v)=pA#(cyc) : pFlightA#(v)=pA#(cyc)+180 : ChangeAnim(v,145) ;thrown behind, head first onto back (back suplex)
   If style=5 Then pA#(v)=pA#(cyc) : pFlightA#(v)=pA#(cyc) : ChangeAnim(v,146) ;thrown forward, head first onto front (slam)
   If style=6 Then pA#(v)=pA#(cyc)+180 : pFlightA#(v)=pA#(cyc) : ChangeAnim(v,146) ;thrown forward, feet first onto front (suplex drop)
   If style=7 Then pA#(v)=pA#(cyc)+180 : pFlightA#(v)=pA#(cyc)+180 : ChangeAnim(v,146) ;thrown behind, head first onto front (flapjack)
   If style=8 Then pA#(v)=pA#(cyc) : pFlightA#(v)=pA#(cyc)+180 : ChangeAnim(v,146) ;thrown behind, feet first onto front (reverse suplex)
   If style=9 Then pA#(v)=pA#(cyc)+90 : pFlightA#(v)=pA#(cyc)+180 : ChangeAnim(v,146) ;thrown behind, sideways onto front (gorilla press)
   If style=10 Then pA#(v)=pA#(cyc)+90 : pFlightA#(v)=pA#(cyc)+90 : ChangeAnim(v,146) ;thrown sideways onto front (double lift)
   SharpTransition(v,pAnim(v)+1,1,0)  
   If EntertainViable(cyc,v) Then entScore=entScore+250
   ;check flight path
   its=0
   While FlightClear(v,pFlightA#(v),40)=0 And its<1000
    nearest=NearestSide(pX#(v),pZ#(v))
    If nearest=1 Then pFlightA#(v)=pFlightA#(v)+ReachAngle#(pFlightA#(v),0,5)
    If nearest=2 Then pFlightA#(v)=pFlightA#(v)+ReachAngle#(pFlightA#(v),270,5)
    If nearest=3 Then pFlightA#(v)=pFlightA#(v)+ReachAngle#(pFlightA#(v),180,5)
    If nearest=4 Then pFlightA#(v)=pFlightA#(v)+ReachAngle#(pFlightA#(v),90,5)
    its=its+1
   Wend
  EndIf
 EndIf
End Function

;MESS UP MOVE
Function BreakMove(cyc,v,style,factor) ;0=pure skill, 1=strength involved, 2=agility involved, 1-5=very safe to very risky
 ;assess risk
 a=pGrappleAssist(cyc)
 chance=(pSkill(cyc)-25)*2
 If style=1 Then chance=(pSkill(cyc)-25)+(pStrength(cyc)-25)
 If style=2 Then chance=(pSkill(cyc)-25)+(pAgility(cyc)-25)
 chance=chance/factor
 If factor=>5 Then chance=chance/2
 If optLevel=0 And pControl(cyc)=0 And pControl(v)>0 Then PercentOf#(chance,75);chance=chance/2
 If optLevel=6 And pControl(cyc)>0 And pControl(v)=0 Then PercentOf#(chance,75);chance=chance/2 
 If TranslateWeight(pChar(cyc))>PercentOf#(TranslateWeight(pChar(v)),150) Or charHeight(pChar(cyc))=>charHeight(pChar(v))+10
  If style=0 Then chance=chance+(chance/2)
  If style=1 Then chance=chance*2
  If style=2 Then chance=chance-(chance/2)
 EndIf
 If TranslateWeight(pChar(v))>PercentOf#(TranslateWeight(pChar(cyc)),150) Or charHeight(pChar(v))=>charHeight(pChar(cyc))+10 Then chance=chance/2
 If matchCountOuts=3 And matchCage=0
  If (InsideRing(RealX#(cyc),RealZ#(cyc),-15) And InsideRing(RealX#(cyc),RealZ#(cyc),-35)=0) Or (InsideRing(RealX#(v),RealZ#(v),-15) And InsideRing(RealX#(v),RealZ#(v),-35)=0)
   chance=chance/2
  EndIf
 EndIf
 chance=chance+(chance/2)
 If chance<5 Then chance=5
 If pAnim(cyc)=moveAnim(1,charMove(pChar(cyc),15)) Or pAnim(cyc)=moveAnim(1,charMove(pChar(v),15)) Then chance=3
 ;execute
 randy=Rnd(0,chance)
 If randy=<1 And v>0 And pAnim(cyc)<>499 And pSpecial(cyc)=0
  Pop(cyc,Rnd(2,9),0) : Pop(v,Rnd(2,9),0)
  If pAnim(cyc)=moveAnim(1,charMove(pChar(cyc),15)) Or pAnim(cyc)=moveAnim(1,charMove(pChar(v),15)) Then Pop(v,2,1)
  ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0)
  ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
  ProduceSound(p(cyc),sSwing,20000,0.3)
  BreakPlayer(cyc) : BreakPlayer(v)
  If a>0 Then BreakPlayer(a)
  pHealth(cyc)=pHealth(cyc)-50 : pHealth(v)=pHealth(v)-50
  pHeat(cyc)=pHeat(cyc)-5 : pHeat(v)=pHeat(v)+5
  If EntertainViable(cyc,v) Then entScore=entScore+(charPopularity(pChar(v))*2)
  Repeat
   pA#(v)=Rnd(0,360)
  Until SatisfiedAngle(pA#(v),pA#(cyc),45)=0
  EndMove(cyc,v)
 EndIf
End Function

;BREAK PLAYER OUT OF GRAPPLE
Function BreakPlayer(cyc)
 ;trigger falling animation
 PositionEntity p(cyc),pX#(cyc),pY#(cyc),pZ#(cyc) 
 RotateEntity p(cyc),0,pA#(cyc),0
 ChangeAnim(cyc,Rnd(120,129)) : pAnimTim(cyc)=-1
 SharpTransition(cyc,pAnim(cyc),1,-1) 
 If InsideRing(pX#(cyc),pZ#(cyc),-15) And pY#(cyc)=wStage# Then ConfineToRing(cyc)
 pOldX#(cyc)=pX#(cyc) : pOldZ#(cyc)=pZ#(cyc)
 pHurtA#(cyc)=RandomFall#(pA#(cyc)+180)
 ;fallen status
 pHP(cyc)=PercentOf#(pHealth(cyc)/10,50)
 If pHP(cyc)<100 Then pHP(cyc)=100
 pDT(cyc)=GetDT(cyc,100)
 If pDizzyTim(cyc)=0 Then pDizzyTim(cyc)=Rnd(-pDT(cyc),pDT(cyc))
 If pBlindTim(cyc)=0 Then pBlindTim(cyc)=Rnd(-pDT(cyc),pDT(cyc))
 RiskInjury(cyc,Rnd(0,5),10)
End Function

;REVERSE MOVE
Function ReverseMove(cyc,v,animA,animB,speed#,length#,level) ;1=standard - 5=finisher
 ;assess chances
 chance=((pSkill(cyc)-25)+(110-pSkill(v)))/4
 If optLevel=0 And pControl(cyc)=0 And pControl(v)>0 Then PercentOf#(chance,75);chance=chance/2
 If optLevel=6 And pControl(cyc)>0 And pControl(v)=0 Then PercentOf#(chance,75);chance=chance/2
 If TranslateWeight(pChar(cyc))>PercentOf#(TranslateWeight(pChar(v)),135) Or charHeight(pChar(cyc))=>charHeight(pChar(v))+10 Then chance=chance*2
 If TranslateWeight(pChar(v))>PercentOf#(TranslateWeight(pChar(cyc)),135) Or charHeight(pChar(v))=>charHeight(pChar(cyc))+10 Then chance=chance/2
 If matchCountOuts=3 And matchCage=0
  If (InsideRing(RealX#(cyc),RealZ#(cyc),-15) And InsideRing(RealX#(cyc),RealZ#(cyc),-35)=0) Or (InsideRing(RealX#(v),RealZ#(v),-15) And InsideRing(RealX#(v),RealZ#(v),-35)=0)
   chance=chance/2
  EndIf
 EndIf
 If chance<level*3 Or pAnim(cyc)=moveAnim(1,charMove(pChar(cyc),15)) Or pAnim(cyc)=moveAnim(1,charMove(pChar(v),15)) Then chance=level*3
 If chance<5 Then chance=5
 ;execute risk 
 randy=Rnd(1,chance)
 If DirPressed(v) Or ActionPressed(v) Or pControl(v)=0 Then struggle=1 Else struggle=0
 If (randy=<level*2 Or pSpecial(v)>0) And v>0 And pSpecial(cyc)=0 And struggle=1
  ;effects
  Pop(cyc,Rnd(2,9),0) : Pop(v,Rnd(2,9),0)
  If pAnim(cyc)=moveAnim(1,charMove(pChar(cyc),15)) Or pAnim(cyc)=moveAnim(1,charMove(pChar(v),15)) Then Pop(cyc,2,1)
  ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(v),0)
  ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
  ProduceSound(p(cyc),sSwing,20000,0.3)
  pHeat(cyc)=pHeat(cyc)-5 : pHeat(v)=pHeat(v)+5
  If EntertainViable(cyc,v) Then entScore=entScore+(charPopularity(pChar(v))*2)
  ;reverse animation
  pRevAnim(cyc)=animA : pRevAnim(v)=animB
  pRevTim#(cyc)=length# : pRevSpeed#(cyc)=speed#
  SetAnimTime p(cyc),pRevTim#(cyc)*pRevSpeed#(cyc),pSeq(cyc,pRevAnim(cyc))
  SetAnimTime p(v),pRevTim#(cyc)*pRevSpeed#(cyc),pSeq(cyc,pRevAnim(v))
  ChangeAnim(cyc,499)
 EndIf
End Function

;END MOVE (COMMON CODE)
Function EndMove(cyc,v)
 pGrappling(cyc)=0 : pGrappler(v)=0
 pStretched(v)=0 : pLieTim(v)=10
 ResetExcuses(cyc,0)
 ResetExcuses(v,0)
 If pGrappleAssist(cyc)>0 Then ResetExcuses(pGrappleAssist(cyc),0) : pGrappleAssist(cyc)=0
End Function

;MOVE IMPACT PROCESS
Function MoveImpact(cyc,v,style,level,weapon) ;0=attack impact, 1=gentle slam/drop, 2=hard slam/drop, 3=body crush, 4=head crush, 5=extreme impact
 ;crowd reaction
 Pop(cyc,Rnd(2,7),Rnd#(Float#(level)/10,1.0)) 
 If pSpecial(cyc)>0 Or pAnim(cyc)=moveAnim(1,charMove(pChar(cyc),15)) Or pAnim(cyc)=moveAnim(1,charMove(pChar(v),15)) Then Pop(cyc,2,1)
 randy=Rnd(0,10-level)  
 If randy=<0 Or pSpecial(cyc)>0 Then Pop(cyc,9,Float#(level)/10)
 ;sound effects
 If style=0
  ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(v),0)
  ProduceSound(p(cyc),sImpact(7),22050,0)
 EndIf
 If style=1
  ProduceSound(pLimb(v,36),sPain(Rnd(1,8)),GetVoice(v),0)
  ProduceSound(pLimb(v,36),sBlock(7),22050,0)
  ProduceSound(pLimb(v,36),sFall,22050,0)
  ProduceSound(pLimb(v,36),sThud,22050,0)
  If InsideRing(RealX#(cyc),RealZ#(cyc),-5) Then ShakeRing(0,1.0)
 EndIf
 If style=2
  ProduceSound(pLimb(v,36),sPain(Rnd(1,8)),GetVoice(v),1)
  ProduceSound(pLimb(v,36),sBlock(7),22050,1)
  ProduceSound(pLimb(v,36),sFall,22050,1)
  ProduceSound(pLimb(v,36),sThud,22050,1)
  If InsideRing(RealX#(cyc),RealZ#(cyc),-5) Then ShakeRing(0,2.0)
 EndIf
 If style=3
  ProduceSound(pLimb(v,36),sPain(Rnd(1,8)),GetVoice(v),1)
  ProduceSound(pLimb(v,36),sBlock(7),22050,1)
  ProduceSound(pLimb(cyc,36),sFall,22050,0) : ProduceSound(pLimb(v,36),sFall,22050,1)
  ProduceSound(pLimb(cyc,36),sThud,22050,0) : ProduceSound(pLimb(v,36),sThud,22050,1)
  If InsideRing(RealX#(cyc),RealZ#(cyc),-5) Then ShakeRing(0,2.0)
 EndIf
 If style=4
  ProduceSound(pLimb(v,36),sPain(Rnd(1,8)),GetVoice(v),1)
  ProduceSound(pLimb(v,36),sImpact(7),22050,1)
  ProduceSound(pLimb(cyc,36),sFall,22050,0) : ProduceSound(pLimb(v,36),sFall,22050,1)
  ProduceSound(pLimb(cyc,36),sThud,22050,0) : ProduceSound(pLimb(v,36),sThud,22050,1)
  If InsideRing(RealX#(cyc),RealZ#(cyc),-5) Then ShakeRing(0,2.0)
 EndIf
 If style=5
  ProduceSound(pLimb(cyc,36),sPain(Rnd(1,8)),GetVoice(cyc),0) : ProduceSound(pLimb(v,36),sPain(Rnd(1,8)),GetVoice(v),1)
  ProduceSound(pLimb(cyc,36),sImpact(7),20000,1) : ProduceSound(pLimb(v,36),sImpact(7),20000,1)
  ProduceSound(pLimb(cyc,36),sFall,22050,1) : ProduceSound(pLimb(v,36),sFall,22050,1)
  ProduceSound(pLimb(cyc,36),sThud,22050,1) : ProduceSound(pLimb(v,36),sThud,22050,1)
  If InsideRing(RealX#(cyc),RealZ#(cyc),-5) Then ShakeRing(0,3.0)
 EndIf
 ;damage
 pain=(50+(pStrength(cyc)/2))*level
 knocker=(30+(pStrength(cyc)/6))*(level+5)
 If InsideRing(pX#(cyc),pZ#(cyc),0)=0 Then pain=pain+(pain/10) : knocker=knocker+(knocker/10) : pHeat(v)=pHeat(v)-1
 If pMomentum(v)>0 Then pain=pain+(pain/8) : knocker=knocker+(knocker/8) : pHeat(cyc)=pHeat(cyc)+1 : pHeat(v)=pHeat(v)-1
 If pSpecial(cyc)>0 Then pain=pain+(pain/4) : knocker=knocker+(knocker/4) : pHeat(v)=pHeat(v)-5 : pSpecial(cyc)=pSpecial(cyc)/2
 pHealth(v)=pHealth(v)-pain : pDT(v)=GetDT(v,knocker)
 If EntertainViable(cyc,v)
  entScore=entScore+PercentOf#(pain,40)
  If pSpecial(cyc)>0 Or pAnim(cyc)=moveAnim(1,charMove(pChar(cyc),15)) Or pAnim(cyc)=moveAnim(1,charMove(pChar(v),15)) Then entScore=entScore+charPopularity(pChar(cyc))
 EndIf
 If (pAnim(cyc)=moveAnim(1,charMove(pChar(cyc),15)) Or pAnim(cyc)=moveAnim(1,charMove(pChar(v),15))) And pSpecial(cyc)=0
  pHP(cyc)=0 : pHurtA#(cyc)=Rnd(0,360)
  If pDT(cyc)<optLieHP Then pDT(cyc)=optLieHP 
 EndIf
 If style=0 Or style=2
  ScarLimb(v,1,21-(level*2))
  RiskInjury(v,5,30-(level*2))
 Else
  ScarArea(v,0,0,0,30-(level*2))
  RiskInjury(v,Rnd(0,5),30-(level*2))
 EndIf
 randy=Rnd(0,pSkill(cyc)*2)
 If randy=<1 Then pHP(cyc)=0
 pHeat(cyc)=pHeat(cyc)+level : pHeat(v)=pHeat(v)-level
 If weapon>0 Then WeaponImpact(cyc,v,weapon)
 If style>0 Then DropWeapon(cyc,0)
 DropWeapon(v,0)
 ClockIntruder(cyc,v)
 ClockAbuse(cyc,v,5)
End Function

;LITTLE ADDITIONAL MOVE FALL
Function MoveFallEffect(cyc)
 ProduceSound(pLimb(cyc,36),sPain(Rnd(1,8)),GetVoice(cyc),0)
 ProduceSound(pLimb(cyc,36),sFall,22050,0)
 ProduceSound(pLimb(cyc,36),sThud,22050,0.5)
 ScarArea(cyc,0,0,0,50)
 If InsideRing(RealX#(cyc),RealZ#(cyc),-5) Then ShakeRing(0,0.5)
End Function

;FORCE MOVE OUT OF BLOCKS
Function ForceMoveOut(cyc,block,checkX#,checkZ#)
 ;scenery blocks
 If block<100
  If pOldZ#(cyc)>blockZ1#(block) And pOldZ#(cyc)<blockZ2#(block)
   If checkX#>pOldX#(cyc) Then pX#(cyc)=pX#(cyc)-1
   If checkX#<pOldX#(cyc) Then pX#(cyc)=pX#(cyc)+1
  EndIf
  If pOldX#(cyc)>blockX1#(block) And pOldX#(cyc)<blockX2#(block)
   If checkZ#>pOldZ#(cyc) Then pZ#(cyc)=pZ#(cyc)-1
   If checkZ#<pOldZ#(cyc) Then pZ#(cyc)=pZ#(cyc)+1
  EndIf
 EndIf
 ;item blocks
 If block>100
  v=block-100
  If ItemCollide(cyc,v,checkX#,pOldZ#(cyc),0)
   If checkX#>pOldX#(cyc) Then pX#(cyc)=pX#(cyc)-1
   If checkX#<pOldX#(cyc) Then pX#(cyc)=pX#(cyc)+1  
  EndIf
  If ItemCollide(cyc,v,pOldX#(cyc),checkZ#,0)
   If checkZ#>pOldZ#(cyc) Then pZ#(cyc)=pZ#(cyc)-1
   If checkZ#<pOldZ#(cyc) Then pZ#(cyc)=pZ#(cyc)+1
  EndIf
 EndIf
End Function

;MOVE PIN MANAGEMENT
Function MovePin(cyc,v)
 ;find cancellation
 cancel=0
 If pControl(cyc)>0 And cGrapple(cyc)=1 Then cancel=1
 If pControl(cyc)=0 
  If matchRules=>1 And pAnimTim(cyc)>50 And pPinCount(v)=0
   If pChecked(v)>0 Or no_refs=0
    If TouchingRopes(cyc) Or TouchingRopes(v) Or LegalMan(v)=0 Then cancel=1
   EndIf
  EndIf 
  If pWarned(cyc)>0 Then cancel=1
  If matchState<>3 Or LegalMan(v)=0 Or FallsCount(cyc)=0 Then cancel=1
 EndIf
 ;find breaks
 break=0
 If pDT(v)=<0
  If DirPressed(v) Or ActionPressed(v) Or pControl(v)=0 Then break=1
 EndIf
 If game=1 And gamAgreement(11)>0 And cyc=matchPlayer And pChar(v)=gamOpponent(gamDate) Then break=0
 If game=1 And gamAgreement(12)>0 And v=matchPlayer And pChar(cyc)=gamOpponent(gamDate) Then break=0
 ;execute breaks
 If cancel=1 Or break=1 Or v=0
  If break=1
   If pPinCount(v)=0 Then entScore=entScore+Rnd(charPopularity(pChar(v))/4,charPopularity(pChar(v))/2)
   If pPinCount(v)=1 Then Pop(v,Rnd(2,9),0) : entScore=entScore+Rnd(charPopularity(pChar(v)),charPopularity(pChar(v))*2)
   If pPinCount(v)=>2 Then Pop(v,Rnd(2,9),1) : Pop(v,Rnd(2,9),0) : entScore=entScore+Rnd(charPopularity(pChar(v))*2,charPopularity(pChar(v))*4)
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0)
   If pPinCount(v)>0 Then pHeat(cyc)=pHeat(cyc)-5 : pHeat(v)=pHeat(v)+5
  EndIf
  pAnimStage(cyc)=3 : pAnimTim(cyc)=0
  pPinner(v)=0 : pPinning(cyc)=0
 EndIf
End Function