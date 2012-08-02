
;//////////////////////////////////////////////////////////////////////////////
;---------------------- WRESTLING MPIRE 2008: ANIMATIONS ----------------------
;//////////////////////////////////////////////////////////////////////////////

;-----------------------------------------------------------------
;////////////////// LOAD ANIMATION SEQUENCES /////////////////////
;-----------------------------------------------------------------
Function LoadSequences(cyc)
 ;source files
 pSeq(cyc,1000)=LoadAnimSeq(p(cyc),"Characters/Sequences/Weapons.3ds")
 For count=1 To 11
  pSeq(cyc,1000+count)=LoadAnimSeq(p(cyc),"Characters/Sequences/Standard"+Dig$(count,10)+".3ds")
 Next
 For count=1 To 7
  pSeq(cyc,1018+(count*2))=LoadAnimSeq(p(cyc),"Characters/Sequences/Moves/Move_Execute"+Dig$(count,10)+".3ds")
  pSeq(cyc,1019+(count*2))=LoadAnimSeq(p(cyc),"Characters/Sequences/Moves/Move_Receive"+Dig$(count,10)+".3ds")
 Next
 pSeq(cyc,1040)=LoadAnimSeq(p(cyc),"Characters/Sequences/Moves/Head_Execute.3ds")
 pSeq(cyc,1041)=LoadAnimSeq(p(cyc),"Characters/Sequences/Moves/Head_Receive.3ds")
 pSeq(cyc,1050)=LoadAnimSeq(p(cyc),"Characters/Sequences/Moves/Legs_Execute.3ds")
 pSeq(cyc,1051)=LoadAnimSeq(p(cyc),"Characters/Sequences/Moves/Legs_Receive.3ds")
 pSeq(cyc,1060)=LoadAnimSeq(p(cyc),"Characters/Sequences/Moves/Buckle_Execute.3ds")
 pSeq(cyc,1061)=LoadAnimSeq(p(cyc),"Characters/Sequences/Moves/Buckle_Receive.3ds")
 pSeq(cyc,1070)=LoadAnimSeq(p(cyc),"Characters/Sequences/Moves/Team_Execute.3ds")
 pSeq(cyc,1071)=LoadAnimSeq(p(cyc),"Characters/Sequences/Moves/Team_Receive.3ds")
 pSeq(cyc,1072)=LoadAnimSeq(p(cyc),"Characters/Sequences/Moves/Team_Assist.3ds")
 ;0-10: stances
 pSeq(cyc,0)=ExtractAnimSeq(p(cyc),1730,1760,pSeq(cyc,1001)) ;normalizer
 If charStance(pChar(cyc))=0 Then pSeq(cyc,1)=ExtractAnimSeq(p(cyc),1730,1760,pSeq(cyc,1001)) ;normal stance
 If charStance(pChar(cyc))=1 Then pSeq(cyc,1)=ExtractAnimSeq(p(cyc),905,965,pSeq(cyc,1007)) ;power stance
 If charStance(pChar(cyc))=2 Then pSeq(cyc,1)=ExtractAnimSeq(p(cyc),1295,1335,pSeq(cyc,1005)) ;wrestling stance
 If charStance(pChar(cyc))=3 Then pSeq(cyc,1)=ExtractAnimSeq(p(cyc),10,40,pSeq(cyc,1001)) ;boxing stance
 If charStance(pChar(cyc))=4 Then pSeq(cyc,1)=ExtractAnimSeq(p(cyc),20,60,pSeq(cyc,1005)) ;lowered boxing stance
 If charStance(pChar(cyc))=5 Then pSeq(cyc,1)=ExtractAnimSeq(p(cyc),845,885,pSeq(cyc,1005)) ;raised boxing stance
 If charStance(pChar(cyc))=6 Then pSeq(cyc,1)=ExtractAnimSeq(p(cyc),1625,1665,pSeq(cyc,1005)) ;feminine stance
 If charGender(pChar(cyc))=0 Then pSeq(cyc,2)=ExtractAnimSeq(p(cyc),1730,1760,pSeq(cyc,1001)) ;distant stance (male)
 If charGender(pChar(cyc))=1 Then pSeq(cyc,2)=ExtractAnimSeq(p(cyc),1625,1665,pSeq(cyc,1005)) ;distant stance (female)
 pSeq(cyc,3)=ExtractAnimSeq(p(cyc),1655,1695,pSeq(cyc,1003)) ;dizzy stance
 pSeq(cyc,4)=ExtractAnimSeq(p(cyc),70,110,pSeq(cyc,1004)) ;injured stance
 pSeq(cyc,5)=ExtractAnimSeq(p(cyc),20,60,pSeq(cyc,1000)) ;weapon stance
 pSeq(cyc,6)=ExtractAnimSeq(p(cyc),1845,1885,pSeq(cyc,1009)) ;blind stance
 pSeq(cyc,7)=ExtractAnimSeq(p(cyc),495,675,pSeq(cyc,1010)) ;talking stance
 ;10-30: standard movement
 If charStance(pChar(cyc))=<1 ;walking stances 
  pSeq(cyc,10)=ExtractAnimSeq(p(cyc),270,330,pSeq(cyc,1003)) ;move forwards
  pSeq(cyc,11)=ExtractAnimSeq(p(cyc),480,540,pSeq(cyc,1003)) ;move backwards
  pSeq(cyc,12)=ExtractAnimSeq(p(cyc),340,400,pSeq(cyc,1003)) ;move left
  pSeq(cyc,13)=ExtractAnimSeq(p(cyc),410,470,pSeq(cyc,1003)) ;move right
 EndIf 
 If charStance(pChar(cyc))=2 ;wrestling stance
  pSeq(cyc,10)=ExtractAnimSeq(p(cyc),1555,1615,pSeq(cyc,1005)) ;move forwards
  pSeq(cyc,11)=ExtractAnimSeq(p(cyc),1485,1545,pSeq(cyc,1005)) ;move backwards
  pSeq(cyc,12)=ExtractAnimSeq(p(cyc),1345,1405,pSeq(cyc,1005)) ;move left
  pSeq(cyc,13)=ExtractAnimSeq(p(cyc),1415,1475,pSeq(cyc,1005)) ;move right
 EndIf 
 If charStance(pChar(cyc))=3 ;boxing stance
  pSeq(cyc,10)=ExtractAnimSeq(p(cyc),540,616,pSeq(cyc,1001)) ;move forwards
  pSeq(cyc,11)=ExtractAnimSeq(p(cyc),315,375,pSeq(cyc,1001)) ;move backwards
  pSeq(cyc,12)=ExtractAnimSeq(p(cyc),145,205,pSeq(cyc,1001)) ;move left
  pSeq(cyc,13)=ExtractAnimSeq(p(cyc),230,290,pSeq(cyc,1001)) ;move right
 EndIf
 If charStance(pChar(cyc))=4 ;lowered boxing stance
  pSeq(cyc,10)=ExtractAnimSeq(p(cyc),280,340,pSeq(cyc,1005)) ;move forwards
  pSeq(cyc,11)=ExtractAnimSeq(p(cyc),210,270,pSeq(cyc,1005)) ;move backwards
  pSeq(cyc,12)=ExtractAnimSeq(p(cyc),70,130,pSeq(cyc,1005)) ;move left
  pSeq(cyc,13)=ExtractAnimSeq(p(cyc),140,200,pSeq(cyc,1005)) ;move right
 EndIf
 If charStance(pChar(cyc))=5 ;raised boxing stance
  pSeq(cyc,10)=ExtractAnimSeq(p(cyc),1105,1165,pSeq(cyc,1005)) ;move forwards
  pSeq(cyc,11)=ExtractAnimSeq(p(cyc),1035,1095,pSeq(cyc,1005)) ;move backwards
  pSeq(cyc,12)=ExtractAnimSeq(p(cyc),895,955,pSeq(cyc,1005)) ;move left
  pSeq(cyc,13)=ExtractAnimSeq(p(cyc),965,1025,pSeq(cyc,1005)) ;move right
 EndIf
 If charStance(pChar(cyc))=6 ;feminine stance
  pSeq(cyc,10)=ExtractAnimSeq(p(cyc),2305,2365,pSeq(cyc,1003)) ;move forwards
  pSeq(cyc,11)=ExtractAnimSeq(p(cyc),480,540,pSeq(cyc,1003)) ;move backwards
  pSeq(cyc,12)=ExtractAnimSeq(p(cyc),340,400,pSeq(cyc,1003)) ;move left
  pSeq(cyc,13)=ExtractAnimSeq(p(cyc),410,470,pSeq(cyc,1003)) ;move right
 EndIf
 If charGender(pChar(cyc))=1
  pSeq(cyc,14)=ExtractAnimSeq(p(cyc),2305,2365,pSeq(cyc,1003)) ;walk forwards (female)
  pSeq(cyc,15)=ExtractAnimSeq(p(cyc),2515,2575,pSeq(cyc,1003)) ;walk backwards (female)
  pSeq(cyc,16)=ExtractAnimSeq(p(cyc),2375,2435,pSeq(cyc,1003)) ;walk left (female)
  pSeq(cyc,17)=ExtractAnimSeq(p(cyc),2445,2505,pSeq(cyc,1003)) ;walk right (female)
 Else
  pSeq(cyc,14)=ExtractAnimSeq(p(cyc),270,330,pSeq(cyc,1003)) ;walk forwards (male)
  pSeq(cyc,15)=ExtractAnimSeq(p(cyc),480,540,pSeq(cyc,1003)) ;walk backwards (male)
  pSeq(cyc,16)=ExtractAnimSeq(p(cyc),340,400,pSeq(cyc,1003)) ;walk left (male)
  pSeq(cyc,17)=ExtractAnimSeq(p(cyc),410,470,pSeq(cyc,1003)) ;walk right (male)
 EndIf
 pSeq(cyc,18)=ExtractAnimSeq(p(cyc),1585,1645,pSeq(cyc,1003)) ;dizzy stagger
 pSeq(cyc,19)=ExtractAnimSeq(p(cyc),120,180,pSeq(cyc,1004)) ;injured stagger
 pSeq(cyc,20)=ExtractAnimSeq(p(cyc),280,340,pSeq(cyc,1000)) ;walk forwards (with weapon)
 pSeq(cyc,21)=ExtractAnimSeq(p(cyc),210,270,pSeq(cyc,1000)) ;walk backwards (with weapon)
 pSeq(cyc,22)=ExtractAnimSeq(p(cyc),70,130,pSeq(cyc,1000)) ;walk left (with weapon)
 pSeq(cyc,23)=ExtractAnimSeq(p(cyc),140,200,pSeq(cyc,1000)) ;walk right (with weapon)
 pSeq(cyc,24)=ExtractAnimSeq(p(cyc),1895,1955,pSeq(cyc,1009)) ;blind stagger
 ;30-40: novelty movements
 pSeq(cyc,30)=ExtractAnimSeq(p(cyc),60,120,pSeq(cyc,1001)) ;dart forwards
 pSeq(cyc,31)=ExtractAnimSeq(p(cyc),2055,2100,pSeq(cyc,1001)) ;dart backwards
 pSeq(cyc,32)=ExtractAnimSeq(p(cyc),455,515,pSeq(cyc,1001)) ;dart left
 pSeq(cyc,33)=ExtractAnimSeq(p(cyc),400,445,pSeq(cyc,1001)) ;dart right
 pSeq(cyc,34)=ExtractAnimSeq(p(cyc),1175,1235,pSeq(cyc,1005)) ;running
 pSeq(cyc,35)=ExtractAnimSeq(p(cyc),350,410,pSeq(cyc,1000)) ;running (with weapon)
 ;40-60: scenery interaction
 pSeq(cyc,40)=ExtractAnimSeq(p(cyc),250,350,pSeq(cyc,1002)) ;climb up to apron
 pSeq(cyc,41)=ExtractAnimSeq(p(cyc),60,150,pSeq(cyc,1002)) ;climb between ropes
 pSeq(cyc,43)=ExtractAnimSeq(p(cyc),160,240,pSeq(cyc,1002)) ;climb down from apron
 pSeq(cyc,44)=ExtractAnimSeq(p(cyc),1265,1285,pSeq(cyc,1005)) ;rope turn
 pSeq(cyc,45)=ExtractAnimSeq(p(cyc),1770,1855,pSeq(cyc,1006)) ;climb up turnbuckle (right)
 pSeq(cyc,46)=ExtractAnimSeq(p(cyc),1915,2000,pSeq(cyc,1006)) ;climb up turnbuckle (left)
 pSeq(cyc,47)=ExtractAnimSeq(p(cyc),2010,2115,pSeq(cyc,1006)) ;climb up turnbuckle (inside)
 pSeq(cyc,48)=ExtractAnimSeq(p(cyc),1865,1905,pSeq(cyc,1006)) ;turnbuckle perch
 pSeq(cyc,49)=ExtractAnimSeq(p(cyc),2140,2200,pSeq(cyc,1006)) ;perched movement
 pSeq(cyc,53)=ExtractAnimSeq(p(cyc),1650,1725,pSeq(cyc,1007)) ;hop onto platform
 pSeq(cyc,54)=ExtractAnimSeq(p(cyc),1750,1830,pSeq(cyc,1007)) ;hop down from platform
 pSeq(cyc,55)=ExtractAnimSeq(p(cyc),870,1040,pSeq(cyc,1009)) ;climb up to high platform
 ;60-70: standing attacks
 For count=1 To 5
  If attackList(count)>0
   move=charAttack(pChar(cyc),count)
   pSeq(cyc,59+count)=ExtractAnimSeq(p(cyc),attackAnimStart(count,move),attackAnimEnd(count,move),pSeq(cyc,attackAnimSource(count,move)))
  EndIf
 Next
 pSeq(cyc,65)=ExtractAnimSeq(p(cyc),95,155,pSeq(cyc,1010)) ;crouching punch
 pSeq(cyc,66)=ExtractAnimSeq(p(cyc),420,490,pSeq(cyc,1011)) ;spit attack
 pSeq(cyc,67)=ExtractAnimSeq(p(cyc),520,590,pSeq(cyc,1011)) ;dust throw
 pSeq(cyc,68)=ExtractAnimSeq(p(cyc),2475,2570,pSeq(cyc,1010)) ;baseball slide
 pSeq(cyc,69)=ExtractAnimSeq(p(cyc),2590,2645,pSeq(cyc,1010)) ;hind kick
 ;70-80: ground attacks  
 For count=2 To 5
  If crushList(count)>0
   move=charCrush(pChar(cyc),count)
   pSeq(cyc,69+count)=ExtractAnimSeq(p(cyc),crushAnimStart(count,move),crushAnimEnd(count,move),pSeq(cyc,crushAnimSource(count,move)))
  EndIf
 Next
 ;80-90: blocking
 pSeq(cyc,80)=ExtractAnimSeq(p(cyc),1770,1810,pSeq(cyc,1001)) ;upper block
 pSeq(cyc,81)=ExtractAnimSeq(p(cyc),1870,1946,pSeq(cyc,1001)) ;upper block movement
 pSeq(cyc,82)=ExtractAnimSeq(p(cyc),1820,1860,pSeq(cyc,1001)) ;lower block
 pSeq(cyc,83)=ExtractAnimSeq(p(cyc),1955,2031,pSeq(cyc,1001)) ;lower block movement
 pSeq(cyc,84)=ExtractAnimSeq(p(cyc),2480,2520,pSeq(cyc,1001)) ;weapon block
 pSeq(cyc,85)=ExtractAnimSeq(p(cyc),2395,2471,pSeq(cyc,1001)) ;weapon block movement
 pSeq(cyc,86)=ExtractAnimSeq(p(cyc),645,685,pSeq(cyc,1000)) ;chair block
 ;90-100: upper pain
 pSeq(cyc,90)=ExtractAnimSeq(p(cyc),1715,1820,pSeq(cyc,1003)) ;major stumble
 pSeq(cyc,91)=ExtractAnimSeq(p(cyc),1945,2005,pSeq(cyc,1003)) ;dazed reaction
 pSeq(cyc,92)=ExtractAnimSeq(p(cyc),730,790,pSeq(cyc,1001)) ;grab face (veer left)
 pSeq(cyc,93)=ExtractAnimSeq(p(cyc),2105,2165,pSeq(cyc,1003)) ;grab face (veer right)
 pSeq(cyc,94)=ExtractAnimSeq(p(cyc),1140,1200,pSeq(cyc,1007)) ;snap back
 pSeq(cyc,95)=ExtractAnimSeq(p(cyc),1220,1280,pSeq(cyc,1007)) ;lifted
 pSeq(cyc,99)=ExtractAnimSeq(p(cyc),1525,1585,pSeq(cyc,1006)) ;lying on back
 ;100-110: lower pain
 pSeq(cyc,100)=ExtractAnimSeq(p(cyc),1840,1925,pSeq(cyc,1003)) ;major stumble
 pSeq(cyc,101)=ExtractAnimSeq(p(cyc),2025,2085,pSeq(cyc,1003)) ;dazed reaction
 pSeq(cyc,102)=ExtractAnimSeq(p(cyc),1170,1230,pSeq(cyc,1001)) ;grab stomach (veer left)
 pSeq(cyc,103)=ExtractAnimSeq(p(cyc),2185,2245,pSeq(cyc,1003)) ;grab stomach (veer right)
 pSeq(cyc,104)=ExtractAnimSeq(p(cyc),80,140,pSeq(cyc,1008)) ;lifted
 pSeq(cyc,105)=ExtractAnimSeq(p(cyc),160,220,pSeq(cyc,1008)) ;bend sideways
 pSeq(cyc,109)=ExtractAnimSeq(p(cyc),1605,1655,pSeq(cyc,1006)) ;lying on front
 ;110-120: ailments
 pSeq(cyc,110)=ExtractAnimSeq(p(cyc),2255,2295,pSeq(cyc,1003)) ;wake up
 pSeq(cyc,111)=ExtractAnimSeq(p(cyc),210,350,pSeq(cyc,1004)) ;nurse hand
 pSeq(cyc,112)=ExtractAnimSeq(p(cyc),270,350,pSeq(cyc,1004)) ;nurse arm
 pSeq(cyc,113)=ExtractAnimSeq(p(cyc),695,830,pSeq(cyc,1004)) ;nurse ribs
 pSeq(cyc,114)=ExtractAnimSeq(p(cyc),860,975,pSeq(cyc,1004)) ;nurse leg
 pSeq(cyc,115)=ExtractAnimSeq(p(cyc),535,670,pSeq(cyc,1004)) ;nurse head
 ;120-140: standard falls
 pSeq(cyc,120)=ExtractAnimSeq(p(cyc),1395,1490,pSeq(cyc,1002)) ;fall backwards onto back
 pSeq(cyc,121)=ExtractAnimSeq(p(cyc),375,455,pSeq(cyc,1002)) ;fall right onto front
 pSeq(cyc,122)=ExtractAnimSeq(p(cyc),530,605,pSeq(cyc,1002)) ;fall right onto hands & knees
 pSeq(cyc,123)=ExtractAnimSeq(p(cyc),680,740,pSeq(cyc,1002)) ;fall right onto one knee
 pSeq(cyc,124)=ExtractAnimSeq(p(cyc),1105,1185,pSeq(cyc,1002)) ;fall left onto front
 pSeq(cyc,125)=ExtractAnimSeq(p(cyc),1210,1285,pSeq(cyc,1002)) ;fall left onto hands & knees
 pSeq(cyc,126)=ExtractAnimSeq(p(cyc),1310,1370,pSeq(cyc,1002)) ;fall left onto one knee
 pSeq(cyc,127)=ExtractAnimSeq(p(cyc),1755,1880,pSeq(cyc,1002)) ;crumple onto front
 pSeq(cyc,128)=ExtractAnimSeq(p(cyc),1905,2000,pSeq(cyc,1002)) ;crumple onto hands & knees
 pSeq(cyc,129)=ExtractAnimSeq(p(cyc),490,590,pSeq(cyc,1009)) ;fall directly onto front

 pSeq(cyc,137)=ExtractAnimSeq(p(cyc),950,1010,pSeq(cyc,1002)) ;downgrade to hands & knees
 pSeq(cyc,138)=ExtractAnimSeq(p(cyc),1020,1080,pSeq(cyc,1002)) ;downgrade to flat on front
 pSeq(cyc,139)=ExtractAnimSeq(p(cyc),1630,1730,pSeq(cyc,1002)) ;downgrade to flat on back
 ;140-150: novelty falls
 pSeq(cyc,140)=ExtractAnimSeq(p(cyc),2120,2250,pSeq(cyc,1002)) ;fall out to apron
 pSeq(cyc,141)=ExtractAnimSeq(p(cyc),2270,2450,pSeq(cyc,1002)) ;fall all the way to the floor
 pSeq(cyc,142)=ExtractAnimSeq(p(cyc),1355,1505,pSeq(cyc,1003)) ;fall off apron 
 pSeq(cyc,143)=ExtractAnimSeq(p(cyc),1515,1520,pSeq(cyc,1003)) ;fall out normalizer
 pSeq(cyc,144)=ExtractAnimSeq(p(cyc),220,330,pSeq(cyc,1009)) ;fall backwards off platform
 pSeq(cyc,145)=ExtractAnimSeq(p(cyc),355,465,pSeq(cyc,1009)) ;fall forwards off platform
 pSeq(cyc,146)=ExtractAnimSeq(p(cyc),600,725,pSeq(cyc,1009)) ;thrown out on back
 pSeq(cyc,147)=ExtractAnimSeq(p(cyc),735,860,pSeq(cyc,1009)) ;thrown out on front
 ;150=160: grounded states
 pSeq(cyc,150)=ExtractAnimSeq(p(cyc),1500,1540,pSeq(cyc,1002)) ;lie on back
 pSeq(cyc,151)=ExtractAnimSeq(p(cyc),1550,1620,pSeq(cyc,1002)) ;rise up off back onto hands & knees
 pSeq(cyc,152)=ExtractAnimSeq(p(cyc),2460,2500,pSeq(cyc,1002)) ;lie on front
 pSeq(cyc,153)=ExtractAnimSeq(p(cyc),800,840,pSeq(cyc,1002)) ;rise up off front onto hands & knees
 pSeq(cyc,154)=ExtractAnimSeq(p(cyc),615,655,pSeq(cyc,1002)) ;rest on hands & knees
 pSeq(cyc,155)=ExtractAnimSeq(p(cyc),850,890,pSeq(cyc,1002)) ;rise up onto one knee
 pSeq(cyc,156)=ExtractAnimSeq(p(cyc),750,790,pSeq(cyc,1002)) ;rest on one knee
 pSeq(cyc,157)=ExtractAnimSeq(p(cyc),900,940,pSeq(cyc,1002)) ;rise up onto feet
 pSeq(cyc,158)=ExtractAnimSeq(p(cyc),2020,2100,pSeq(cyc,1002)) ;crawl
 pSeq(cyc,159)=ExtractAnimSeq(p(cyc),2510,2610,pSeq(cyc,1002)) ;roll onto back
 ;160-170: pinning
 pSeq(cyc,160)=ExtractAnimSeq(p(cyc),1685,1715,pSeq(cyc,1008)) ;intiate pin
 pSeq(cyc,161)=ExtractAnimSeq(p(cyc),1725,1765,pSeq(cyc,1008)) ;pin loop (normal)
 pSeq(cyc,162)=ExtractAnimSeq(p(cyc),1925,1965,pSeq(cyc,1008)) ;pin loop (overhead)
 pSeq(cyc,163)=ExtractAnimSeq(p(cyc),1825,1865,pSeq(cyc,1008)) ;pin loop (left hook)
 pSeq(cyc,164)=ExtractAnimSeq(p(cyc),1875,1915,pSeq(cyc,1008)) ;pin loop (right hook)
 pSeq(cyc,165)=ExtractAnimSeq(p(cyc),1775,1815,pSeq(cyc,1008)) ;hooked pin victim
 pSeq(cyc,166)=ExtractAnimSeq(p(cyc),1975,2150,pSeq(cyc,1008)) ;exasperated pin release
 ;170-190: refereeing
 pSeq(cyc,170)=ExtractAnimSeq(p(cyc),850,935,pSeq(cyc,1003)) ;single standing count
 pSeq(cyc,171)=ExtractAnimSeq(p(cyc),1045,1130,pSeq(cyc,1003)) ;double standing count
 pSeq(cyc,172)=ExtractAnimSeq(p(cyc),945,1035,pSeq(cyc,1003)) ;single rule out (DQ)
 pSeq(cyc,173)=ExtractAnimSeq(p(cyc),1140,1230,pSeq(cyc,1003)) ;end round
 pSeq(cyc,174)=ExtractAnimSeq(p(cyc),1240,1330,pSeq(cyc,1003)) ;start round
 pSeq(cyc,175)=ExtractAnimSeq(p(cyc),985,1065,pSeq(cyc,1004)) ;weigh up scores
 pSeq(cyc,176)=ExtractAnimSeq(p(cyc),1090,1180,pSeq(cyc,1004)) ;reveal winner
 pSeq(cyc,177)=ExtractAnimSeq(p(cyc),1920,2010,pSeq(cyc,1004)) ;go away
 pSeq(cyc,178)=ExtractAnimSeq(p(cyc),2160,2195,pSeq(cyc,1008)) ;kneel down
 pSeq(cyc,179)=ExtractAnimSeq(p(cyc),2205,2365,pSeq(cyc,1008)) ;examination
 pSeq(cyc,180)=ExtractAnimSeq(p(cyc),1135,1195,pSeq(cyc,1009)) ;point at ground
 pSeq(cyc,181)=ExtractAnimSeq(p(cyc),2375,2415,pSeq(cyc,1008)) ;canvas count
 pSeq(cyc,182)=ExtractAnimSeq(p(cyc),1050,1110,pSeq(cyc,1009)) ;repeated rule out
 pSeq(cyc,183)=ExtractAnimSeq(p(cyc),355,415,pSeq(cyc,1010)) ;high examination
 ;190-200: taunts
 If pRole(cyc)=1
  pRefTaunt(cyc,1)=GetRefGesture()
  Repeat
   pRefTaunt(cyc,2)=GetRefGesture()
  Until pRefTaunt(cyc,2)<>pRefTaunt(cyc,1)
  Repeat
   pRefTaunt(cyc,3)=GetRefGesture()
  Until pRefTaunt(cyc,3)<>pRefTaunt(cyc,1) And pRefTaunt(cyc,3)<>pRefTaunt(cyc,2)
  Repeat
   pRefTaunt(cyc,4)=GetRefGesture()
  Until pRefTaunt(cyc,4)<>pRefTaunt(cyc,1) And pRefTaunt(cyc,4)<>pRefTaunt(cyc,2) And pRefTaunt(cyc,4)<>pRefTaunt(cyc,3)
 EndIf
 For count=1 To 4
  move=charTaunt(pChar(cyc),count)
  If pRole(cyc)=1 Then move=pRefTaunt(cyc,count)
  pSeq(cyc,189+count)=ExtractAnimSeq(p(cyc),tauntAnimStart(move),tauntAnimEnd(move),pSeq(cyc,tauntAnimSource(move))) 
 Next
 pSeq(cyc,194)=ExtractAnimSeq(p(cyc),755,840,pSeq(cyc,1003)) ;disappointment (get outta here)
 pSeq(cyc,195)=ExtractAnimSeq(p(cyc),1390,1470,pSeq(cyc,1004)) ;disappointment (shrug shoulders)
 pSeq(cyc,196)=ExtractAnimSeq(p(cyc),835,935,pSeq(cyc,1006)) ;disappointment (hands on hips)
 pSeq(cyc,197)=ExtractAnimSeq(p(cyc),425,485,pSeq(cyc,1010)) ;microphone speech
 pSeq(cyc,198)=ExtractAnimSeq(p(cyc),2830,2950,pSeq(cyc,1006)) ;trophy celebration
 ;200-210: item interaction
 pSeq(cyc,200)=ExtractAnimSeq(p(cyc),1205,1245,pSeq(cyc,1009)) ;holding wide item
 pSeq(cyc,201)=ExtractAnimSeq(p(cyc),1325,1365,pSeq(cyc,1009)) ;holding narrow item
 pSeq(cyc,202)=ExtractAnimSeq(p(cyc),1255,1315,pSeq(cyc,1009)) ;carrying wide item
 pSeq(cyc,203)=ExtractAnimSeq(p(cyc),1375,1435,pSeq(cyc,1009)) ;carrying narrow item
 ;210-220: weapon interaction
 pSeq(cyc,210)=ExtractAnimSeq(p(cyc),1455,1485,pSeq(cyc,1009)) ;pick-up lunge
 pSeq(cyc,211)=ExtractAnimSeq(p(cyc),1700,1755,pSeq(cyc,1009)) ;snatch lunge
 pSeq(cyc,212)=ExtractAnimSeq(p(cyc),1495,1515,pSeq(cyc,1009)) ;drop weapon
 pSeq(cyc,213)=ExtractAnimSeq(p(cyc),1780,1835,pSeq(cyc,1009)) ;throw weapon

 pSeq(cyc,216)=ExtractAnimSeq(p(cyc),1750,1910,pSeq(cyc,1004)) ;fondle/place belt on shoulder
 pSeq(cyc,217)=ExtractAnimSeq(p(cyc),1580,1740,pSeq(cyc,1004)) ;fondle/place belt around waist
 pSeq(cyc,218)=ExtractAnimSeq(p(cyc),1760,1800,pSeq(cyc,1004)) ;remove belt from shoulder
 pSeq(cyc,219)=ExtractAnimSeq(p(cyc),1600,1640,pSeq(cyc,1004)) ;remove belt from waist
 ;220-230: weapon attacks
 pSeq(cyc,220)=ExtractAnimSeq(p(cyc),820,885,pSeq(cyc,1001)) ;loaded punch
 pSeq(cyc,221)=ExtractAnimSeq(p(cyc),1165,1260,pSeq(cyc,1006)) ;loaded double-axe handle 
 pSeq(cyc,222)=ExtractAnimSeq(p(cyc),1540,1595,pSeq(cyc,1009)) ;weak swing
 pSeq(cyc,223)=ExtractAnimSeq(p(cyc),2370,2465,pSeq(cyc,1006)) ;strong swing
 pSeq(cyc,224)=ExtractAnimSeq(p(cyc),1620,1675,pSeq(cyc,1009)) ;weak stab
 pSeq(cyc,225)=ExtractAnimSeq(p(cyc),2495,2590,pSeq(cyc,1006)) ;strong stab
 pSeq(cyc,226)=ExtractAnimSeq(p(cyc),440,520,pSeq(cyc,1000)) ;high chair swing
 pSeq(cyc,227)=ExtractAnimSeq(p(cyc),2735,2820,pSeq(cyc,1006)) ;ground swing
 pSeq(cyc,228)=ExtractAnimSeq(p(cyc),2620,2705,pSeq(cyc,1006)) ;ground stab
 pSeq(cyc,229)=ExtractAnimSeq(p(cyc),550,635,pSeq(cyc,1000)) ;ground chair swing
 ;230-240: cage interaction
 pSeq(cyc,230)=ExtractAnimSeq(p(cyc),870,900,pSeq(cyc,1009)) ;initiate climb
 pSeq(cyc,231)=ExtractAnimSeq(p(cyc),1965,2005,pSeq(cyc,1009)) ;hanging
 pSeq(cyc,232)=ExtractAnimSeq(p(cyc),2140,2200,pSeq(cyc,1009)) ;climbing
 pSeq(cyc,233)=ExtractAnimSeq(p(cyc),2015,2115,pSeq(cyc,1009)) ;climb over 
 ;300-500: standing grapples
 LoadMoveSequences(cyc)
 ;500: head grappling
 LoadHeadSequences(cyc)
 ;600: leg grappling
 LoadLegSequences(cyc)
 ;700: buckle grappling
 LoadBuckleSequences(cyc)
 ;800: team grappling
 LoadTeamMoveSequences(cyc)
End Function

;GET REF GESTURE
Function GetRefGesture()
 randy=Rnd(1,13)
 If randy=1 Then anim=2
 If randy=2 Then anim=3
 If randy=2 Then anim=8
 If randy=3 Then anim=16
 If randy=4 Then anim=17
 If randy=5 Then anim=21
 If randy=6 Then anim=25
 If randy=7 Then anim=27
 If randy=8 Then anim=28
 If randy=9 Then anim=29
 If randy=10 Then anim=35
 If randy=11 Then anim=41
 If randy=12 Then anim=44
 If randy=13 Then anim=50
 Return anim
End Function

;----------------------------------------------------------------
;////////////////// MANAGE ANIMATIONS ///////////////////////////
;----------------------------------------------------------------
Function Animations(cyc)
 ;----------- 0-10: STANCES ----------
 ;standing
 If pAnim(cyc)=0
  anim=1 : pAnimSpeed#(cyc)=Rnd#(0.1,0.5)
  If FightViable(cyc,pFoc(cyc))=0 And stanceType(charStance(pChar(cyc)))>0 Then anim=2
  If pHolding(cyc)>0 And (weapHold(weapType(pHolding(cyc)))=1 Or weapName$(weapType(pHolding(cyc)))="Briefcase") Then anim=2
  If FindInjury(cyc)>1 Then anim=4
  If pHolding(cyc)>0 And weapHold(weapType(pHolding(cyc)))=3 Then anim=5
  If matchState=2 And speaker=cyc
   anim=7 : pAnimSpeed#(cyc)=Rnd#(0.35,0.7)
   If pHolding(cyc)>0 And weapName$(weapType(pHolding(cyc)))="Microphone" Then anim=197 : pAnimSpeed#(cyc)=Rnd#(0.25,0.5)
   randy=Rnd(0,1)
   If randy=0 Then pAnimSpeed#(cyc)=-pAnimSpeed#(cyc)
  EndIf
  If pDizzyTim(cyc)>0 Then anim=3 : pAnimSpeed#(cyc)=Rnd#(0.1,0.5)
  If pBlindTim(cyc)>0 Then anim=6 : pAnimSpeed#(cyc)=Rnd#(0.1,0.5)
  If pAnimTim(cyc)=0 Or anim<>pState(cyc)
   Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,anim),10
   pState(cyc)=anim
  EndIf
  ;costume cheat
  If cyc=matchPlayer And KeyDown(46) And KeyDown(24) Then ChangeAnim(cyc,199)
  If pControl(cyc)=0 And pRole(cyc)=0 And pCostume(cyc)=2 And matchPreset>1 And matchLocation=0 And matchState=3 Then ChangeAnim(cyc,199)
 EndIf
 ;----------- 10-20: STANDARD MOVEMENT ----------
 ;turn
 If pAnim(cyc)=10
  If pAnimTim(cyc)=0 ;Or anim<>pState(cyc)
   anim=Rnd(10,13) : speedFactor#=3
   If FightViable(cyc,pFoc(cyc))=0 Or stanceType(charStance(pChar(cyc)))=0 Then anim=Rnd(14,17) : speedFactor#=2
   If pHolding(cyc)>0 And (weapHold(weapType(pHolding(cyc)))=1 Or weapName$(weapType(pHolding(cyc)))="Briefcase") Then anim=Rnd(14,17) : speedFactor#=2
   If FindInjury(cyc)>1 Then anim=19 : speedFactor#=6
   If pHolding(cyc)>0 And weapHold(weapType(pHolding(cyc)))=3 Then anim=Rnd(20,23) : speedFactor#=2
   If pDizzyTim(cyc)>0 Then anim=18 : speedFactor#=6
   If pBlindTim(cyc)>0 Then anim=24 : speedFactor#=6
   pAnimSpeed#(cyc)=pSpeed#(cyc)*speedFactor#
   If pAnimSpeed#(cyc)<0.5*speedFactor# And speedFactor#=<4 Then pAnimSpeed#(cyc)=0.5*speedFactor#
   If pAnimSpeed#(cyc)>0.7*speedFactor# And speedFactor#=<4 Then pAnimSpeed#(cyc)=0.7*speedFactor#
   Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,anim),5
   pState(cyc)=anim : pStepTim#(cyc)=-15
  EndIf
  If pAnimTim(cyc)>5 And (pA#(cyc)=pTA#(cyc) Or DirPressed(cyc)) Then ChangeAnim(cyc,0)
  pStepTim#(cyc)=pStepTim#(cyc)+pAnimSpeed#(cyc) 
 EndIf
 ;movement
 If pAnim(cyc)=11
  moveAngle#=RequestAngle#(cyc)
  If pControl(cyc)=0 And cUpTim(cyc)<5 And cDownTim(cyc)<5 And cLeftTim(cyc)<5 And cRightTim(cyc)<5
   PositionEntity dummy,pActX#(cyc),pY#(cyc),pActZ#(cyc)
   PointEntity p(cyc),dummy
   moveAngle#=CleanAngle#(EntityYaw(p(cyc)))
   RotateEntity p(cyc),0,pA#(cyc),0
  EndIf
  If (matchState=1 Or matchEnter=cyc) And pOutTim(cyc)=1
   PositionEntity dummy,0,pY#(cyc),0
   PointEntity p(cyc),dummy
   moveAngle#=CleanAngle#(EntityYaw(p(cyc)))
   RotateEntity p(cyc),0,pA#(cyc),0
  EndIf
  anim=9+GetMovementAnim(cyc,moveAngle#) : speedFactor#=4
  If FightViable(cyc,pFoc(cyc))=0 Or stanceType(charStance(pChar(cyc)))=0 Then anim=13+GetMovementAnim(cyc,moveAngle#) : speedFactor#=3
  If pHolding(cyc)>0 And (weapHold(weapType(pHolding(cyc)))=1 Or weapName$(weapType(pHolding(cyc)))="Briefcase") Then anim=13+GetMovementAnim(cyc,moveAngle#) : speedFactor#=3
  If FindInjury(cyc)>1 Then anim=19 : speedFactor#=6
  If pHolding(cyc)>0 And weapHold(weapType(pHolding(cyc)))=3 Then anim=19+GetMovementAnim(cyc,moveAngle#) : speedFactor#=3
  If pDizzyTim(cyc)>0 Then anim=18 : speedFactor#=6 : moveAngle#=CleanAngle#(moveAngle#+180)
  If pBlindTim(cyc)>0 Then anim=24 : speedFactor#=6 : moveAngle#=CleanAngle#(moveAngle#+180) 
  If pAnimTim(cyc)=0 Or (pAnimTim(cyc)>Int(30/pAnimSpeed#(cyc)) And anim<>pState(cyc))
   pAnimSpeed#(cyc)=pSpeed#(cyc)*speedFactor#
   If pAnimSpeed#(cyc)<0.5*speedFactor# And speedFactor#=<4 Then pAnimSpeed#(cyc)=0.5*speedFactor#
   If pAnimSpeed#(cyc)>0.7*speedFactor# And speedFactor#=<4 Then pAnimSpeed#(cyc)=0.7*speedFactor#
   If pAnimTim(cyc)<Int(30/pAnimSpeed#(cyc)) Then transition=5 Else transition=10
   Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,anim),transition
   pState(cyc)=anim : pStepTim#(cyc)=-15
   If pAnimTim(cyc)>0 Then pAnimTim(cyc)=10
  EndIf
  If DirPressed(cyc) Then Advance(cyc,moveAngle#,pSpeed#(cyc))
  If pAnimTim(cyc)>5 And DirPressed(cyc)=0 Then ChangeAnim(cyc,0)
  pStepTim#(cyc)=pStepTim#(cyc)+pAnimSpeed#(cyc)
  If (anim=18 Or anim=24) And EntertainViable(cyc,0) Then entScore=entScore+Rnd(0,1)
 EndIf
 ;running
 If pAnim(cyc)=12
  moveAngle#=RequestAngle#(cyc)
  If pControl(cyc)=0 And cUpTim(cyc)<5 And cDownTim(cyc)<5 And cLeftTim(cyc)<5 And cRightTim(cyc)<5
   PositionEntity dummy,pActX#(cyc),pY#(cyc),pActZ#(cyc)
   PointEntity p(cyc),dummy
   moveAngle#=CleanAngle#(EntityYaw(p(cyc)))
   RotateEntity p(cyc),0,pA#(cyc),0
  EndIf
  If pMomentum(cyc)>0 Then moveAngle#=pA#(cyc)
  If pAnimTim(cyc)=0
   If pHolding(cyc)>0 And weapHold(weapType(pHolding(cyc)))=3 Then anim=35 Else anim=34
   pAnimSpeed#(cyc)=pSpeed#(cyc)*4
   If pAnimSpeed#(cyc)<0.5*4 Then pAnimSpeed#(cyc)=0.5*4
   If pAnimSpeed#(cyc)>0.7*4 Then pAnimSpeed#(cyc)=0.7*4
   If pOldAnim(cyc)=44 Or pOldAnim(cyc)=309 Then transition=0 Else transition=7
   Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,anim),transition
   pStepTim#(cyc)=-15
  EndIf
  Advance(cyc,moveAngle#,pSpeed#(cyc)*2.0)
  If SatisfiedAngle(moveAngle#,CleanAngle#(pA#(cyc)+110),70) Then pBodyTZA#(cyc)=20
  If SatisfiedAngle(moveAngle#,CleanAngle#(pA#(cyc)+250),70) Then pBodyTZA#(cyc)=-20 
  If pAnimTim(cyc)>5 
   RopeBounce(cyc)
   If pMomentum(cyc)>0 Then RunningImpacts(cyc)
   If pAnim(cyc)=12 And pMomentum(cyc)=0 
    If cRun(cyc)=0 Or pDizzyTim(cyc)>0 Or pBlindTim(cyc)>0 Then ChangeAnim(cyc,0) 
   EndIf
  EndIf
  pStepTim#(cyc)=pStepTim#(cyc)+pAnimSpeed#(cyc)
  RiskExertion(cyc,1)
 EndIf
 ;----------- 30-40: NOVELTY MOVEMENTS ----------
 ;dodge
 If pAnim(cyc)=30
  If pAnimTim(cyc)=0
   anim=29+GetMovementAnim(cyc,pDashA#(cyc))
   pAnimSpeed#(cyc)=pSpeed#(cyc)*4
   If pAnimSpeed#(cyc)<0.5*4 Then pAnimSpeed#(cyc)=0.5*4
   If pAnimSpeed#(cyc)>0.7*4 Then pAnimSpeed#(cyc)=0.7*4
   If anim=31 Or anim=33 Then transition=4 Else transition=2
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,anim),transition
   pStagger#(cyc)=pSpeed#(cyc)*2.5
  EndIf
  pStagger#(cyc)=pStagger#(cyc)-(pStagger#(cyc)/30)
  Advance(cyc,pDashA#(cyc),pStagger#(cyc))
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)>Int(20/pAnimSpeed#(cyc))
   RiskInjury(cyc,4,100)
   If pInjured(cyc,0)>0 Or pInjured(cyc,4)>0 Then ChangeAnim(cyc,114)
  EndIf
  If pAnimTim(cyc)=>Int(45/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,0) 
  RiskExertion(cyc,1)
  If InProximity(cyc,pFoc(cyc),50) And EntertainViable(cyc,0) Then entScore=entScore+1
 EndIf
 ;----------- 40-60: SCENERY INTERACTION ----------
 ;climb up to apron
 If pAnim(cyc)=40
  pExcusedPlays(cyc)=1 : pExcusedWorld(cyc)=1
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=1.0+PercentOf#(2.0,pAgility(cyc))
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,40),5
  EndIf
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc))
   If pRole(cyc)=0 And matchState=3 And pOutCount(cyc)>0 Then Pop(cyc,Rnd(2,9),0.5+PercentOf#(0.5,pOutCount(cyc)*10))
  EndIf 
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(80/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sStep(Rnd(1,4)),22050,0)
  If pAnimTim(cyc)=Int(80/pAnimSpeed#(cyc)) Then ShakeRopes(RealX#(cyc),RealZ#(cyc),11,0)
  If pAnimTim(cyc)=>Int(110/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,-1)
   pY#(cyc)=wStage# : ResetExcuses(cyc,0)
   DropItem(cyc) : RiskFall(cyc,1) 
  Else
   factor#=GetPercent#(pAnimSpeed#(cyc),3.0)
   vertOffset#=Float(12-charHeight(pChar(cyc)))*0.0075
   pY#(cyc)=pY#(cyc)+PercentOf#(vertOffset#,factor#)
   horizOffset#=0.01+(Float(12-charHeight(pChar(cyc)))/250)
   If horizOffset#>0 Then Advance(cyc,pA#(cyc),PercentOf#(horizOffset#,factor#))
  EndIf
  BindItem(cyc)
  RiskExertion(cyc,1)
 EndIf
 ;climb in from apron
 If pAnim(cyc)=41
  ResetExcuses(cyc,1)
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=0.75+PercentOf#(2.0,pAgility(cyc))
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,41),5
  EndIf
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Then RopeSound(p(cyc),0) : ShakeRopes(RealX#(cyc),RealZ#(cyc),20,1)
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(55/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(75/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sStep(Rnd(1,4)),22050,0)
  If pAnimTim(cyc)=>Int(95/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,-1)
   pPlatform(cyc)=0 : ConfineToRing(cyc)
   ResetExcuses(cyc,0)
  Else
   factor#=GetPercent#(pAnimSpeed#(cyc),2.5)
   horizOffset#=Float(24-charHeight(pChar(cyc)))/600
   If horizOffset#>0 Then Advance(cyc,pA#(cyc),PercentOf#(horizOffset#,factor#))
  EndIf 
  BindItem(cyc)
 EndIf
 ;climb out to apron
 If pAnim(cyc)=42
  ResetExcuses(cyc,1)
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=0.75+PercentOf#(2.0,pAgility(cyc)) 
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,41),5
  EndIf
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Then RopeSound(p(cyc),0) : ShakeRopes(RealX#(cyc),RealZ#(cyc),20,1)
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(55/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(75/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sStep(Rnd(1,4)),22050,0)
  If pAnimTim(cyc)>Int(55/pAnimSpeed#(cyc)) 
   If pRole(cyc)=1 And pCarrying(cyc)>0 Then pAgenda(cyc)=0 : pOldAgenda(cyc)=-1
   DropItem(cyc)
  EndIf
  If pAnimTim(cyc)=>Int(100/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,-1)
   ResetExcuses(cyc,0)
   RiskFall(cyc,0)
  Else
   factor#=GetPercent#(pAnimSpeed#(cyc),2.5)
   horizOffset#=Float(24-charHeight(pChar(cyc)))/800
   If horizOffset#>0 Then Advance(cyc,pA#(cyc),PercentOf#(horizOffset#,factor#))
  EndIf
  If RealX#(cyc)<-80 Or RealX#(cyc)>80 Or RealZ#(cyc)<-80 Or RealZ#(cyc)>80 Then Advance(cyc,pA#(cyc),-0.1)
  BindItem(cyc)
 EndIf
 ;climb down from apron
 If pAnim(cyc)=43
  ResetExcuses(cyc,1)
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=1.0+PercentOf#(2.0,pAgility(cyc))
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,43),0
   ShakeRopes(RealX#(cyc),RealZ#(cyc),11,0)
   ResetMultiSting(cyc)
  EndIf
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6)) : Pop(0,Rnd(2,7),0.5)
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sStep(Rnd(1,4)),22050,0)
  If pAnimTim(cyc)=>Int(30/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(70/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,41),pLimbY#(cyc,44)),2)
  If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sThud,22050,0.5)
  If pAnimTim(cyc)=>Int(80/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,-1)
   If pPlatform(cyc)=1 And pZ#(cyc)<blockZ2#(0)+1 Then pZ#(cyc)=blockZ2#(0)+1
   If pPlatform(cyc)=3 And pZ#(cyc)>blockZ1#(0)-1 Then pZ#(cyc)=blockZ1#(0)-1
   If pPlatform(cyc)=2 And pX#(cyc)<blockX2#(0)+1 Then pX#(cyc)=blockX2#(0)+1
   If pPlatform(cyc)=4 And pX#(cyc)>blockX1#(0)-1 Then pX#(cyc)=blockX1#(0)-1
   pPlatform(cyc)=0 : pY#(cyc)=wGround#
   ResetExcuses(cyc,0)
  Else
   factor#=GetPercent#(pAnimSpeed#(cyc),3.0)
   vertOffset#=Float(12-charHeight(pChar(cyc)))*0.0075
   pY#(cyc)=pY#(cyc)-PercentOf#(vertOffset#,factor#)
   horizOffset#=0.1+(Float(12-charHeight(pChar(cyc)))/175)
   If horizOffset#>0 Then Advance(cyc,pA#(cyc),PercentOf#(horizOffset#,factor#))
  EndIf
  BindItem(cyc)
 EndIf
 ;rope turn
 If pAnim(cyc)=44
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=0.5+PercentOf#(1.5,pAgility(cyc))
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,44),20/pAnimSpeed#(cyc)
   RopeSound(p(cyc),1)
   ShakeRopes(RealX#(cyc),RealZ#(cyc),30,1)
   For count=1 To 12
    If ropeAnim(count)=>30 And ropeAnim(count)=<33 And ropeAnimTim(count)=0 Then ropeAnimSpeed#(count)=pAnimSpeed#(cyc)
   Next
   ResetMultiSting(cyc)
  EndIf
  If pAnimTim(cyc)=>Int(5/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(20/pAnimSpeed#(cyc)) 
   For v=1 To no_plays
    If cyc<>v And pPlatform(v)=>1 And pPlatform(v)=<4 And ReachedCord(RealX#(cyc),RealZ#(cyc),pX#(v),pZ#(v),15) And AttackViable(v)=1 And pMultiSting(cyc,v)=1
     ImpactChecks(v)
     Pop(cyc,Rnd(2,8),0)
     ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),0)
     ProduceSound(p(cyc),sImpact(Rnd(5,7)),22050,0)
     ChangeAnim(v,Rnd(92,95)) : pHurtTim(v)=10
     If pPlatform(v)=1 Or pPlatform(v)=3
      If pX#(cyc)=>pX#(v) Then pHurtA#(v)=90 Else pHurtA#(v)=270
     EndIf
     If pPlatform(v)=2 Or pPlatform(v)=4
      If pZ#(cyc)=>pZ#(v) Then pHurtA#(v)=180 Else pHurtA#(v)=0
     EndIf
     pStagger#(v)=3.0
     pHealth(v)=pHealth(v)-pStrength(cyc)
     If EntertainViable(cyc,v) Then entScore=entScore+pStrength(cyc)
     If matchCage=0 Then pHP(v)=0
     pFoc(v)=cyc : pMultiSting(cyc,v)=0
    EndIf
   Next
   FindSmashes(cyc,pY#(cyc)+15,1)
  EndIf
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Then RopeBurn(cyc)
  If matchCage>0 And pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) 
   Pop(0,Rnd(2,8),1)
   ProduceSound(p(cyc),sSmashWire,22050,0)
   ProduceSound(p(cyc),sImpact(Rnd(5,7)),22050,0)
   ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),1)
   ScarArea(cyc,0,0,0,5)
   pHealth(cyc)=pHealth(cyc)-100 : pHP(cyc)=pHP(cyc)-100
   ShakeCage(NearestSide(RealX#(cyc),RealZ#(cyc)),2.0)
  EndIf
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc))
   randy=Rnd(0,pAgility(cyc))
   If pMomentum(cyc)>0 Then risk=5 Else risk=1
   If matchRopes>0 Then risk=risk*2
   If randy=<risk And matchCage=0 Then ChangeAnim(cyc,Rnd(140,141)) : SharpTransition(cyc,pAnim(cyc),0,180)
   If randy=<risk And matchCage>0 Then ChangeAnim(cyc,140) : SharpTransition(cyc,140,0,180)
  EndIf 
  If pAnimTim(cyc)=>Int(37/pAnimSpeed#(cyc))
   ChangeAnim(cyc,12) : SharpTransition(cyc,34,0,180)
   pTA#(cyc)=pA#(cyc)
   If pMomentum(cyc)>0 Then risk=5 Else risk=1
   RiskFall(cyc,risk)
   If pHP(cyc)=0 Then pHurtA#(cyc)=pA#(cyc) : pA#(cyc)=pA#(cyc)+180
  Else
   Advance(cyc,pA#(cyc),0.2)
  EndIf
  pStepTim#(cyc)=pStepTim#(cyc)+pAnimSpeed#(cyc)
  RiskExertion(cyc,1)
  If EntertainViable(cyc,0) Then entScore=entScore+1
 EndIf
 ;climb up turnbuckle (from sides)
 If pAnim(cyc)=45 Or pAnim(cyc)=46
  pExcusedPlays(cyc)=1 : pExcusedWorld(cyc)=1
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=1.0+PercentOf#(2.0,pAgility(cyc))
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,pAnim(cyc)),0
   ShakeRopes(RealX#(cyc),RealZ#(cyc),11,0)
  EndIf
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc))
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  EndIf
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(85/pAnimSpeed#(cyc))
   ProduceSound(p(cyc),sStep(Rnd(1,4)),22050,0)
  EndIf
  If pAnimTim(cyc)=>Int(85/pAnimSpeed#(cyc))
   ChangeAnim(cyc,48) : SharpTransition(cyc,48,1,-1)
   pY#(cyc)=49 : ResetExcuses(cyc,0)
   RiskFall(cyc,5)
  EndIf
  If pAnimTim(cyc)<Int(70/pAnimSpeed#(cyc))
   factor#=GetPercent#(pAnimSpeed#(cyc),3.0)
   vertOffset#=Float(12-charHeight(pChar(cyc)))*0.01
   pY#(cyc)=pY#(cyc)+PercentOf#(vertOffset#,factor#)
  EndIf
  RiskExertion(cyc,1)
 EndIf
 ;climb up turnbuckle (from inside)
 If pAnim(cyc)=47
  ResetExcuses(cyc,1)
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=1.0+PercentOf#(2.5,pAgility(cyc))
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,47),0
   ShakeRopes(RealX#(cyc),RealZ#(cyc),11,0)
  EndIf
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc))
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  EndIf
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(65/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(95/pAnimSpeed#(cyc))
   ProduceSound(p(cyc),sStep(Rnd(1,4)),22050,0)
  EndIf
  If pAnimTim(cyc)=>Int(105/pAnimSpeed#(cyc))
   ChangeAnim(cyc,48) : SharpTransition(cyc,48,1,-1)
   pY#(cyc)=49 : ResetExcuses(cyc,0)
   RiskFall(cyc,5)
  EndIf
  If pAnimTim(cyc)<Int(85/pAnimSpeed#(cyc))
   factor#=GetPercent#(pAnimSpeed#(cyc),3.5)
   vertOffset#=Float(12-charHeight(pChar(cyc)))*0.01
   pY#(cyc)=pY#(cyc)+PercentOf#(vertOffset#,factor#)
  EndIf
  RiskExertion(cyc,1)
 EndIf
 ;turnbuckle perch
 If pAnim(cyc)=48
  If pOldAnim(cyc)=>45 And pOldAnim(cyc)=<47 Then transition=0 Else transition=10
  If pAnimTim(cyc)=0 Then Animate p(cyc),1,Rnd#(0.1,0.3),pSeq(cyc,48),transition 
  If pAnimTim(cyc)>5 And DirPressed(cyc) Then ChangeAnim(cyc,49)
 EndIf
 ;perched movement
 If pAnim(cyc)=49
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=pSpeed#(cyc)*3
   If pAnimSpeed#(cyc)<0.5*3 Then pAnimSpeed#(cyc)=0.5*3
   If pAnimSpeed#(cyc)>0.7*3 Then pAnimSpeed#(cyc)=0.7*3
   Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,49),5
   pStepTim#(cyc)=-15
  EndIf
  If pAnimTim(cyc)>5 And DirPressed(cyc)=0 Then ChangeAnim(cyc,48)
  pStepTim#(cyc)=pStepTim#(cyc)+pAnimSpeed#(cyc) 
 EndIf 
 ;climb down from turnbuckle (to apron)
 If pAnim(cyc)=50 Or pAnim(cyc)=51
  ResetExcuses(cyc,1)
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=1.0+PercentOf#(2.0,pAgility(cyc))
   Animate p(cyc),3,-pAnimSpeed#(cyc),pSeq(cyc,pAnim(cyc)-5),0
   ShakeRopes(RealX#(cyc),RealZ#(cyc),11,0)
  EndIf
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc))
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  EndIf
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(85/pAnimSpeed#(cyc))
   ProduceSound(p(cyc),sStep(Rnd(1,4)),22050,0)
  EndIf
  If pAnimTim(cyc)=>Int(35/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(70/pAnimSpeed#(cyc)) Then FindSmashes(cyc,pLimbY#(cyc,36),2)
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then FindSmashes(cyc,pY#(cyc),2)
  If pAnimTim(cyc)=>Int(80/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,0) : ResetExcuses(cyc,0) 
 EndIf
 ;climb down from turnbuckle (to inside)
 If pAnim(cyc)=52
  ResetExcuses(cyc,1)
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=1.0+PercentOf#(2.5,pAgility(cyc)) 
   Animate p(cyc),3,-pAnimSpeed#(cyc),pSeq(cyc,47),0
   ShakeRopes(RealX#(cyc),RealZ#(cyc),11,0)
  EndIf
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc))
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  EndIf
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(65/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(95/pAnimSpeed#(cyc))
   ProduceSound(p(cyc),sStep(Rnd(1,4)),22050,0)
  EndIf
  If pAnimTim(cyc)=>Int(35/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(70/pAnimSpeed#(cyc)) Then FindSmashes(cyc,pLimbY#(cyc,36),2)
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then FindSmashes(cyc,pY#(cyc),2) 
  If pAnimTim(cyc)=>Int(95/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,0) : ResetExcuses(cyc,0)
 EndIf 
 ;hop up onto platform/item
 If pAnim(cyc)=53
  ResetExcuses(cyc,1)
  If pAnimTim(cyc)=0 Then pAnimSpeed#(cyc)=2.5 : Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,53),5
  If pAnimTim(cyc)>Int(15/pAnimSpeed#(cyc))
   ascent#=(pPlatformY#(cyc)-FindGround#(pPlatformX#(cyc),pPlatformZ#(cyc)))/Int(50/pAnimSpeed#(cyc))
   If pY#(cyc)<pPlatformY#(cyc) Then pY#(cyc)=pY#(cyc)+ascent#
  EndIf
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(65/pAnimSpeed#(cyc)) Then pStepTim(cyc)=99
  If pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  factor#=GetPercent#(pAnimTim(cyc),Int(75/pAnimSpeed#(cyc)))
  GetSmoothSpeeds(pX#(cyc),pPlatformX#(cyc),pY#(cyc),pY#(cyc),pZ#(cyc),pPlatformZ#(cyc),10)
  If speedX#<0.5 Then speedX#=0.5
  If speedX#>1.5 Then speedX#=1.5
  If speedZ#<0.5 Then speedZ#=0.5
  If speedZ#>1.5 Then speedZ#=1.5
  If pX#(cyc)<pPlatformX#(cyc) Then pX#(cyc)=pX#(cyc)+PercentOf#(speedX#,factor#)
  If pX#(cyc)>pPlatformX#(cyc) Then pX#(cyc)=pX#(cyc)-PercentOf#(speedX#,factor#)
  If pZ#(cyc)<pPlatformZ#(cyc) Then pZ#(cyc)=pZ#(cyc)+PercentOf#(speedZ#,factor#)
  If pZ#(cyc)>pPlatformZ#(cyc) Then pZ#(cyc)=pZ#(cyc)-PercentOf#(speedZ#,factor#)
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) And pPlatform(cyc)=>100
   iAnim(pPlatform(cyc)-100)=11 : iAnimTim(pPlatform(cyc)-100)=0
  EndIf
  If pAnimTim(cyc)>Int(45/pAnimSpeed#(cyc)) And pPlatform(cyc)=>100
   If iState(pPlatform(cyc)-100)=1 Or iCarrier(pPlatform(cyc)-100)>0
    ChangeAnim(cyc,0)
    ResetExcuses(cyc,0)
   EndIf
  EndIf
  If pAnimTim(cyc)>Int(75/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0)
   pY#(cyc)=pPlatformY#(cyc)
   pOldPlatform(cyc)=pPlatform(cyc)
   ResetExcuses(cyc,0)
   RiskFall(cyc,5)
  EndIf
  DropWeapon(cyc,100)
  RiskExertion(cyc,1)
 EndIf
 ;hop down from platform/item
 If pAnim(cyc)=54
  ResetExcuses(cyc,1)
  If pAnimTim(cyc)=0 
   pGround#(cyc)=FindGround#(pX#(cyc),pZ#(cyc))
   drop#=pY#(cyc)-pGround#(cyc)
   pAnimSpeed#(cyc)=2.0+((20-drop#)/60)
   pAnimSpeed#(cyc)=pAnimSpeed#(cyc)+(pAnimSpeed#(cyc)/4)
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,54),15/pAnimSpeed#(cyc)
   pTravel#(cyc)=1.75 : pGravity#(cyc)=1.6
   If pPlatform(cyc)=>10 And pPlatform(cyc)=<90 And FlightClear(cyc,pA#(cyc),15) Then pTravel#(cyc)=pTravel#(cyc)-(pTravel#(cyc)/3)
   ;If pPlatform(cyc)>100
    ;ProjectDummy(cyc,0,0,15)
    ;If ItemCollide(cyc,pPlatform(cyc)-100,EntityX(dummy),EntityZ(dummy),0) Then pTravel#(cyc)=pTravel#(cyc)+(pTravel#(cyc)/4)
   ;EndIf
   If InsideRing(pX#(cyc),pZ#(cyc),-10) Then pFlyAgenda(cyc)=1 Else pFlyAgenda(cyc)=3
   pOldPlatform(cyc)=pPlatform(cyc) : pPlatform(cyc)=0
   ResetMultiSting(cyc)
  EndIf
  If pFlyAgenda(cyc)=1 Then pGround#(cyc)=wStage#
  If pFlyAgenda(cyc)=3 Then pGround#(cyc)=wGround#
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.5)
  If pAnimTim(cyc)>Int(10/pAnimSpeed#(cyc))
   pGravity#(cyc)=pGravity#(cyc)-0.275
   If pY#(cyc)>pGround#(cyc) Then pY#(cyc)=pY#(cyc)+pGravity#(cyc)
   pTravel#(cyc)=pTravel#(cyc)-(pTravel#(cyc)/Int(55/pAnimSpeed#(cyc)))
   If pTravel#(cyc)<0 Then pTravel#(cyc)=0
   Advance(cyc,pA#(cyc),pTravel#(cyc))
   FlightCorrection(cyc,0)
  EndIf
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) Then pStepTim(cyc)=99
  If pY#(cyc)>pGround#(cyc) And pGravity#(cyc)<0 Then FindSmashes(cyc,pLimbY#(cyc,36),0)
  If pY#(cyc)<pGround#(cyc) 
   If InsideRing(RealX#(cyc),RealZ#(cyc),-5) Then ShakeRing(0,1.0)
   ProduceSound(p(cyc),sThud,22050,1)
   FindSmashes(cyc,pY#(cyc),0)
   pTravel#(cyc)=pTravel#(cyc)/2
   pY#(cyc)=pGround#(cyc)
  EndIf
  If pAnimTim(cyc)>Int(95/pAnimSpeed#(cyc)) Or (pY#(cyc)=pGround(cyc) And pHP(cyc)=0)
   ChangeAnim(cyc,0)
   pY#(cyc)=pGround#(cyc)
   FlightCorrection(cyc,1) 
   ResetExcuses(cyc,0)
  EndIf
  DropWeapon(cyc,100)
  RiskExertion(cyc,1)
 EndIf
 ;climb up to high platform
 If pAnim(cyc)=55
  ResetExcuses(cyc,1)
  pGround#(cyc)=FindGround#(pX#(cyc),pZ#(cyc))
  If pAnimTim(cyc)=0 
   pAnimSpeed#(cyc)=1.0+PercentOf#(1.0,pAgility(cyc))
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,55),5
  EndIf
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(100/pAnimSpeed#(cyc)) Then Pop(0,Rnd(2,9),0)
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.5) : ProduceSound(p(cyc),sPain(Rnd(1,8)),22050,0.5)
  If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(85/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(130/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0) 
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(110/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(155/pAnimSpeed#(cyc)) Then pStepTim(cyc)=99
  If pAnimTim(cyc)=>Int(35/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(85/pAnimSpeed#(cyc))
   distance#=(pPlatformY#(cyc)-35)-pGround#(cyc)
   If distance#<0 Then distance#=0
   ascent#=distance#/(55/pAnimSpeed#(cyc)) 
   If pY#(cyc)<pPlatformY#(cyc)-35 Then pY#(cyc)=pY#(cyc)+ascent#
  EndIf
  If pAnimTim(cyc)>Int(85/pAnimSpeed#(cyc))
   factor#=pAnimTim(cyc)-Int(85/pAnimSpeed#(cyc))
   If pX#(cyc)<pPlatformX#(cyc) Then pX#(cyc)=pX#(cyc)+PercentOf#(0.9,factor#)
   If pX#(cyc)>pPlatformX#(cyc) Then pX#(cyc)=pX#(cyc)-PercentOf#(0.9,factor#)
   If pZ#(cyc)<pPlatformZ#(cyc) Then pZ#(cyc)=pZ#(cyc)+PercentOf#(0.9,factor#)
   If pZ#(cyc)>pPlatformZ#(cyc) Then pZ#(cyc)=pZ#(cyc)-PercentOf#(0.9,factor#)
   ascent#=35/(50/pAnimSpeed#(cyc)) 
   If pY#(cyc)<pPlatformY#(cyc) Then pY#(cyc)=pY#(cyc)+ascent#
  EndIf
  If pAnimTim(cyc)=>Int(175/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,0,180)
   pY#(cyc)=pPlatformY#(cyc)
   ResetExcuses(cyc,0)
   RiskFall(cyc,5)
  EndIf 
  DropWeapon(cyc,100)
  RiskExertion(cyc,1)
 EndIf
 ;climb down from high platform
 If pAnim(cyc)=56
  ResetExcuses(cyc,1)
  pGround#(cyc)=FindGround#(pX#(cyc),pZ#(cyc))
  If pAnimTim(cyc)=0 
   pAnimSpeed#(cyc)=1.0+PercentOf#(1.0,pAgility(cyc))
   Animate p(cyc),3,-pAnimSpeed#(cyc),pSeq(cyc,55),0
   pA#(cyc)=pA#(cyc)+180
   pPlatform(cyc)=0
   ResetMultiSting(cyc)
  EndIf
  If pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) Then Pop(0,Rnd(2,9),0)
  If pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(90/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(140/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0) 
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(65/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(105/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(125/pAnimSpeed#(cyc)) Then pStepTim(cyc)=99 
  If pAnimTim(cyc)=Int(140/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.5)
  If pAnimTim(cyc)=Int(150/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sThud,22050,0.5)
  If pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(90/pAnimSpeed#(cyc))
   Advance(cyc,pA#(cyc),-(11/(70/pAnimSpeed#(cyc))))
  EndIf
  If pAnimTim(cyc)=>Int(45/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(90/pAnimSpeed#(cyc))
   ascent#=35/(45/pAnimSpeed#(cyc)) 
   If pY#(cyc)>pPlatformY#(cyc)-35 Then pY#(cyc)=pY#(cyc)-ascent#
  EndIf
  If pAnimTim(cyc)>Int(90/pAnimSpeed#(cyc))
   distance#=(pPlatformY#(cyc)-35)-pGround#(cyc)
   If distance#<0 Then distance#=0
   ascent#=distance#/(50/pAnimSpeed#(cyc)) 
   If pY#(cyc)>pGround#(cyc) Then pY#(cyc)=pY#(cyc)-ascent#
  EndIf
  If pY#(cyc)>pGround#(cyc) Then FindSmashes(cyc,pY#(cyc),2)
  If pAnimTim(cyc)>Int(150/pAnimSpeed#(cyc)) Or (pY#(cyc)=pGround(cyc) And pHP(cyc)=0)
   ChangeAnim(cyc,0)
   pY#(cyc)=pGround#(cyc)
   ResetExcuses(cyc,0) 
  EndIf
  DropWeapon(cyc,100)
  RiskExertion(cyc,1)
 EndIf
 ;jump positioning
 If pAnim(cyc)=57 Or pAnim(cyc)=58
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=2.0
   If pOldAnim(cyc)=>48 And pOldAnim(cyc)=<49 Then anim=49 Else anim=Rnd(10,13)
   Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,anim),5
   pStepTim#(cyc)=-15
  EndIf
  turner#=BoostTurn#(pA#(cyc),pFlightA#(cyc),5,3)
  If SatisfiedAngle(pA#(cyc),pFlightA#(cyc),turner#*2) Or SatisfiedAngle(pA#(cyc),pFlightA#(cyc),10)
   pA#(cyc)=pFlightA#(cyc)
   If pAnim(cyc)=57 Then ChangeAnim(cyc,64)
   If pAnim(cyc)=58 Then ChangeAnim(cyc,74)
  Else 
   pA#(cyc)=pA#(cyc)+ReachAngle#(pA#(cyc),pFlightA#(cyc),turner#)
  EndIf
  pStepTim#(cyc)=pStepTim#(cyc)+pAnimSpeed#(cyc) 
 EndIf 
 ;----------- 60-70: STANDING ATTACKS ----------
 AttackAnims(cyc)
 ;----------- 70-80: GROUND ATTACKS ----------
 CrushAnims(cyc)
 ;----------- 80-90: BLOCKING ----------
 ;upper block
 If pAnim(cyc)=80
  threat=FindThreat(cyc)
  newAnim=-1
  anim=80 : transition=5
  If pHolding(cyc)>0 And weapHold(weapType(pHolding(cyc)))=1 Then anim=84 : transition=10
  If pHolding(cyc)>0 And weapHold(weapType(pHolding(cyc)))=3 Then anim=86 : transition=10
  If pAnimTim(cyc)=0 Or anim<>pState(cyc)
   Animate p(cyc),1,Rnd#(0.25,0.5),pSeq(cyc,anim),transition
   pState(cyc)=anim
  EndIf
  If DirPressed(cyc) And weapHold(weapType(pHolding(cyc)))<>3 Then newAnim=82 
  If threat=2 Or threat=12 Then newAnim=83
  If pAnimTim(cyc)=>5 And cBlock(cyc)=0 Then newAnim=0
  If newAnim=>0 Then ChangeAnim(cyc,newAnim)
 EndIf
 ;upper block reaction
 If pAnim(cyc)=81
  anim=80
  If pHolding(cyc)>0 And weapHold(weapType(pHolding(cyc)))=1 Then anim=84
  If pHolding(cyc)>0 And weapHold(weapType(pHolding(cyc)))=3 Then anim=86
  If pAnimTim(cyc)=0 Then Animate p(cyc),1,Rnd(1.0,3.0),pSeq(cyc,anim),1
  pStagger#(cyc)=pStagger#(cyc)-(pStagger#(cyc)/10)
  If pStagger#(cyc)<0 Then pStagger#(cyc)=0
  Advance(cyc,pHurtA#(cyc),pStagger#(cyc))
  If pStagger#(cyc)<0.5 Or (cAttack(cyc)=1 And cGrapple(cyc)=0) Or (cAttack(cyc)=0 And cGrapple(cyc)=1)
   chance=110-pToughness(cyc)
   If pSpecial(cyc)>0 Then chance=chance/2 
   If (cAttack(cyc)=1 And cGrapple(cyc)=0) Or (cAttack(cyc)=0 And cGrapple(cyc)=1) Then chance=chance/2 
   randy=Rnd(0,chance)
   If randy=<1 Or pAnimTim(cyc)=>15 Then ChangeAnim(cyc,80)
  EndIf
  pBlockTim(cyc)=0
  DropWeapon(cyc,100)
 EndIf
 ;upper block movement
 If pAnim(cyc)=82
  threat=FindThreat(cyc)
  newAnim=-1
  anim=81
  If pHolding(cyc)>0 Then anim=85
  If pAnimTim(cyc)=0 Or anim<>pState(cyc)
   pAnimSpeed#(cyc)=pSpeed#(cyc)*3
   If pAnimSpeed#(cyc)<0.5*3 Then pAnimSpeed#(cyc)=0.5*3
   If pAnimSpeed#(cyc)>0.7*3 Then pAnimSpeed#(cyc)=0.7*3
   Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,anim),5
   pState(cyc)=anim
   pStepTim#(cyc)=-15
  EndIf
  If DirPressed(cyc) Then Advance(cyc,RequestAngle#(cyc),pSpeed#(cyc)/3)
  pStepTim#(cyc)=pStepTim#(cyc)+pAnimSpeed#(cyc)
  If threat=2 Or threat=12 Then newAnim=85
  If pAnimTim(cyc)=>5
   If DirPressed(cyc)=0 Then newAnim=80
   If cBlock(cyc)=0 Then newAnim=0
  EndIf
  If newAnim=>0 Then ChangeAnim(cyc,newAnim)
 EndIf
 ;lower block
 If pAnim(cyc)=83
  threat=FindThreat(cyc)
  newAnim=-1
  anim=82 : transition=5
  If pHolding(cyc)>0 And weapHold(weapType(pHolding(cyc)))=1 Then anim=84 : transition=10
  If pHolding(cyc)>0 And weapHold(weapType(pHolding(cyc)))=3 Then anim=86 : transition=10
  If pAnimTim(cyc)=0 Or anim<>pState(cyc)
   Animate p(cyc),1,Rnd#(0.25,0.5),pSeq(cyc,anim),transition
   pState(cyc)=anim
  EndIf
  If DirPressed(cyc) And weapHold(weapType(pHolding(cyc)))<>3 Then newAnim=82
  If threat=1 Or threat=11 Then newAnim=80
  If pAnimTim(cyc)=>5 And cBlock(cyc)=0 Then newAnim=0
  If newAnim=>0 Then ChangeAnim(cyc,newAnim)
 EndIf
 ;lower block reaction
 If pAnim(cyc)=84
  anim=82
  If pHolding(cyc)>0 And weapHold(weapType(pHolding(cyc)))=1 Then anim=84
  If pHolding(cyc)>0 And weapHold(weapType(pHolding(cyc)))=3 Then anim=86
  If pAnimTim(cyc)=0 Then Animate p(cyc),1,Rnd(1.0,3.0),pSeq(cyc,anim),1
  pStagger#(cyc)=pStagger#(cyc)-(pStagger#(cyc)/10)
  If pStagger#(cyc)<0 Then pStagger#(cyc)=0
  Advance(cyc,pHurtA#(cyc),pStagger#(cyc))
  If pStagger#(cyc)<0.5 Or (cAttack(cyc)=1 And cGrapple(cyc)=0) Or (cAttack(cyc)=0 And cGrapple(cyc)=1)
   chance=110-pToughness(cyc)
   If pSpecial(cyc)>0 Then chance=chance/2 
   If (cAttack(cyc)=1 And cGrapple(cyc)=0) Or (cAttack(cyc)=0 And cGrapple(cyc)=1) Then chance=chance/2 
   randy=Rnd(0,chance)
   If randy=<1 Or pAnimTim(cyc)=>15 Then ChangeAnim(cyc,83)
  EndIf
  pBlockTim(cyc)=0
  DropWeapon(cyc,100)
 EndIf
 ;lower block movement
 If pAnim(cyc)=85
  threat=FindThreat(cyc)
  newAnim=-1
  anim=83
  If pHolding(cyc)>0 And weapHold(weapType(pHolding(cyc)))=1 Then anim=85
  If pAnimTim(cyc)=0 Or anim<>pState(cyc)
   pAnimSpeed#(cyc)=pSpeed#(cyc)*3
   If pAnimSpeed#(cyc)<0.5*3 Then pAnimSpeed#(cyc)=0.5*3
   If pAnimSpeed#(cyc)>0.7*3 Then pAnimSpeed#(cyc)=0.7*3
   Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,anim),5
   pState(cyc)=anim
   pStepTim#(cyc)=-15
  EndIf
  If DirPressed(cyc) Then Advance(cyc,RequestAngle#(cyc),pSpeed#(cyc)/3)
  pStepTim#(cyc)=pStepTim#(cyc)+pAnimSpeed#(cyc)
  If threat=1 Or threat=11 Then newAnim=82
  If pAnimTim(cyc)=>5
   If DirPressed(cyc)=0 Then newAnim=83
   If cBlock(cyc)=0 Then newAnim=0
  EndIf
  If newAnim=>0 Then ChangeAnim(cyc,newAnim)
 EndIf
 ;----------- 90-100: UPPER HURT ----------
 ;major stumble
 If pAnim(cyc)=90
  If pAnimTim(cyc)=1
   pAnimSpeed#(cyc)=Rnd#(2.0,4.0)
   Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,90),5
   pStagger#(cyc)=Rnd#(0.5,2.0)
  EndIf
  If pAnimTim(cyc)>0
   pStagger#(cyc)=pStagger#(cyc)-(pStagger#(cyc)/(60/pAnimSpeed#(cyc)))
   If pStagger#(cyc)<0 Then pStagger#(cyc)=0
   Advance(cyc,pHurtA#(cyc),pStagger#(cyc))
   If pStagger#(cyc)>0 Then pStepTim#(cyc)=pStepTim#(cyc)+pAnimSpeed#(cyc)
   If pAnimTim(cyc)>Int(105/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,0)
   chance=(pHP(cyc)*2)*optLength
   If chance<200*optLength Then chance=200*optLength
   randy=Rnd(0,chance)
   If randy=0 Then pHP(cyc)=0
  EndIf
  DropWeapon(cyc,10)
 EndIf
 ;minor standing pain
 If pAnim(cyc)=>91 And pAnim(cyc)=<95
  If pAnimTim(cyc)=1
   pAnimSpeed#(cyc)=Rnd#(1.0,3.0)
   If pAnim(cyc)=91 Then pAnimSpeed#(cyc)=Rnd#(2.0,3.0)
   transition=2
   If (pOldAnim(cyc)=>150 And pOldAnim(cyc)=<159) Or (pOldAnim(cyc)=>301 And pOldAnim(cyc)=<303) Then transition=5
   If pBlindTim(cyc)>0 And pOldAnim(cyc)=<11 Then transition=5
   Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,pAnim(cyc)),transition
  EndIf
  If pAnimTim(cyc)>0
   pStagger#(cyc)=pStagger#(cyc)-(pStagger#(cyc)/5)
   If pStagger#(cyc)<0 Then pStagger#(cyc)=0
   Advance(cyc,pHurtA#(cyc),pStagger#(cyc))
   If pStagger#(cyc)>0 Then pStepTim#(cyc)=pStepTim#(cyc)+1
   If pStagger#(cyc)<1.0
    chance=110-pToughness(cyc)
    If ActionPressed(cyc) Or DirPressed(cyc) Then chance=chance/2
    If pSpecial(cyc)>0 Then chance=chance/2 
    randy=Rnd(0,chance)
    If (randy=<1 And pHurtTim(cyc)=0) Or pAnimTim(cyc)=>Int(60/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,0) 
   EndIf
  EndIf
  DropWeapon(cyc,10)
 EndIf
 ;hurt lying on back
 If pAnim(cyc)=99
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=Rnd#(1.0,3.0)
   If pOldAnim(cyc)=99 Or pOldAnim(cyc)=150 Then transition=2 Else transition=5
   Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,pAnim(cyc)),transition
  EndIf
  chance=110-pToughness(cyc)
  If pSpecial(cyc)>0 Then chance=chance/2 
  randy=Rnd(0,chance)
  If (randy=0 And pHurtTim(cyc)=0) Or pAnimTim(cyc)=>Int(60/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,150) 
  DropWeapon(cyc,10)
 EndIf
 ;----------- 100-110: LOWER HURT ----------
 ;major stumble
 If pAnim(cyc)=100
  If pAnimTim(cyc)=1
   pAnimSpeed#(cyc)=Rnd#(2.0,4.0)
   Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,100),5
   pStagger#(cyc)=Rnd#(0.5,2.0)
  EndIf
  If pAnimTim(cyc)=>1
   pStagger#(cyc)=pStagger#(cyc)-(pStagger#(cyc)/(60/pAnimSpeed#(cyc)))
   If pStagger#(cyc)<0 Then pStagger#(cyc)=0
   Advance(cyc,pHurtA#(cyc),pStagger#(cyc))
   If pStagger#(cyc)>0 Then pStepTim#(cyc)=pStepTim#(cyc)+pAnimSpeed#(cyc)
   If pAnimTim(cyc)>Int(85/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,0)
   chance=(pHP(cyc)*2)*optLength
   If chance<200*optLength Then chance=200*optLength
   randy=Rnd(0,chance)
   If randy=0 Then pHP(cyc)=0
  EndIf
  DropWeapon(cyc,10)
 EndIf
 ;minor standing pain
 If pAnim(cyc)=>101 And pAnim(cyc)=<105
  If pAnimTim(cyc)=1
   pAnimSpeed#(cyc)=Rnd#(1.0,3.0)
   If pAnim(cyc)=101 Then pAnimSpeed#(cyc)=Rnd#(2.0,3.0)
   transition=2
   If (pOldAnim(cyc)=>150 And pOldAnim(cyc)=<159) Or (pOldAnim(cyc)=>301 And pOldAnim(cyc)=<303) Then transition=5
   If pBlindTim(cyc)>0 And pOldAnim(cyc)=<11 Then transition=5
   Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,pAnim(cyc)),transition
  EndIf
  If pAnimTim(cyc)>0
   pStagger#(cyc)=pStagger#(cyc)-(pStagger#(cyc)/5)
   If pStagger#(cyc)<0 Then pStagger#(cyc)=0
   Advance(cyc,pHurtA#(cyc),pStagger#(cyc))
   If pStagger#(cyc)>0 Then pStepTim#(cyc)=pStepTim#(cyc)+1
   If pStagger#(cyc)<1.0
    chance=110-pToughness(cyc)
    If ActionPressed(cyc) Or DirPressed(cyc) Then chance=chance/2
    If pSpecial(cyc)>0 Then chance=chance/2 
    randy=Rnd(0,chance)
    If (randy=<1 And pHurtTim(cyc)=0) Or pAnimTim(cyc)=>Int(60/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,0) 
   EndIf
  EndIf
  DropWeapon(cyc,10)
 EndIf
 ;hurt lying on front
 If pAnim(cyc)=109
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=Rnd#(1.0,2.0)
   If pOldAnim(cyc)=109 Or pOldAnim(cyc)=152 Then transition=2 Else transition=5
   Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,pAnim(cyc)),transition
  EndIf
  chance=110-pToughness(cyc)
  If pSpecial(cyc)>0 Then chance=chance/2 
  randy=Rnd(0,chance)
  If (randy=0 And pHurtTim(cyc)=0) Or pAnimTim(cyc)=>Int(50/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,152) 
  DropWeapon(cyc,10)
 EndIf
 ;----------- 110-120: AILMENTS ----------
 ;wake up from dizziness
 If pAnim(cyc)=110
  If pAnimTim(cyc)=0 
   If pBlindTim(cyc)=1 Then transition=10 Else transition=5
   Animate p(cyc),3,2.0,pSeq(cyc,110),transition
  EndIf
  If pAnimTim(cyc)>22 
   pDizzyTim(cyc)=0 : pBlindTim(cyc)=0
   ChangeAnim(cyc,0) 
  EndIf
  DropWeapon(cyc,10)
  If game=1 And tauntLearned(9)<1 Then tauntLearned(9)=1
  If EntertainViable(cyc,0) Then entScore=entScore+1
 EndIf
 ;nurse broken hand
 If pAnim(cyc)=111
  If pAnimTim(cyc)=0
   ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),1)
   pAnimSpeed#(cyc)=2.0
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,pAnim(cyc)),15/pAnimSpeed#(cyc)
  EndIf
  pStagger#(cyc)=pStagger#(cyc)-(pStagger#(cyc)/5)
  If pStagger#(cyc)<0 Then pStagger#(cyc)=0
  Advance(cyc,pHurtA#(cyc),pStagger#(cyc))
  If pStagger#(cyc)>0 Then pStepTim#(cyc)=pStepTim#(cyc)+1
  randy=Rnd(0,290)
  If (randy=0 And pAnimTim(cyc)>10) Or pAnimTim(cyc)>Int(140/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,0)
  DropWeapon(cyc,10)
  If game=1 And tauntLearned(10)<1 Then tauntLearned(10)=1
  If EntertainViable(cyc,0) Then entScore=entScore+1
 EndIf
 ;nurse broken arm
 If pAnim(cyc)=112
  If pAnimTim(cyc)=0
   ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),1)
   pAnimSpeed#(cyc)=2.0
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,pAnim(cyc)),20/pAnimSpeed#(cyc)
  EndIf
  pStagger#(cyc)=pStagger#(cyc)-(pStagger#(cyc)/5)
  If pStagger#(cyc)<0 Then pStagger#(cyc)=0
  Advance(cyc,pHurtA#(cyc),pStagger#(cyc))
  If pStagger#(cyc)>0 Then pStepTim#(cyc)=pStepTim#(cyc)+1 
  randy=Rnd(0,180)
  If (randy=0 And pAnimTim(cyc)>10) Or pAnimTim(cyc)>Int(90/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,0)
  DropWeapon(cyc,10)
  If game=1 And tauntLearned(11)<1 Then tauntLearned(11)=1
  If EntertainViable(cyc,0) Then entScore=entScore+1
 EndIf
 ;nurse ribs
 If pAnim(cyc)=113
  If pAnimTim(cyc)=0
   ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),1)
   pAnimSpeed#(cyc)=2.5
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,113),15/pAnimSpeed#(cyc)
   If pStagger#(cyc)>0.2 Then pStagger#(cyc)=0.2
  EndIf
  pStagger#(cyc)=pStagger#(cyc)-(pStagger#(cyc)/5)
  If pStagger#(cyc)<0 Then pStagger#(cyc)=0
  Advance(cyc,pHurtA#(cyc),pStagger#(cyc))
  If pStagger#(cyc)>0 Then pStepTim#(cyc)=pStepTim#(cyc)+1
  randy=Rnd(0,270)
  If (randy=0 And pAnimTim(cyc)>10) Or pAnimTim(cyc)>Int(135/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,0)
  DropWeapon(cyc,10)
  If game=1 And tauntLearned(13)<1 Then tauntLearned(13)=1
  If EntertainViable(cyc,0) Then entScore=entScore+1
 EndIf
 ;nurse broken leg
 If pAnim(cyc)=114
  If pAnimTim(cyc)=0
   ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),1)
   pAnimSpeed#(cyc)=2.0
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,114),15/pAnimSpeed#(cyc)
  EndIf
  pStagger#(cyc)=pStagger#(cyc)-(pStagger#(cyc)/5)
  If pStagger#(cyc)<0 Then pStagger#(cyc)=0
  Advance(cyc,pHurtA#(cyc),pStagger#(cyc))
  If pStagger#(cyc)>0 Then pStepTim#(cyc)=pStepTim#(cyc)+1
  randy=Rnd(0,230)
  If (randy=0 And pAnimTim(cyc)>10) Or pAnimTim(cyc)>Int(115/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,0)
  DropWeapon(cyc,10)
  If game=1 And tauntLearned(14)<1 Then tauntLearned(14)=1
  If EntertainViable(cyc,0) Then entScore=entScore+1
 EndIf
 ;nurse head
 If pAnim(cyc)=115
  If pAnimTim(cyc)=0
   ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),1)
   pAnimSpeed#(cyc)=2.0
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,115),15/pAnimSpeed#(cyc)
   If pStagger#(cyc)>0.2 Then pStagger#(cyc)=0.2
  EndIf
  pStagger#(cyc)=pStagger#(cyc)-(pStagger#(cyc)/5)
  If pStagger#(cyc)<0 Then pStagger#(cyc)=0
  Advance(cyc,pHurtA#(cyc),pStagger#(cyc))
  If pStagger#(cyc)>0 Then pStepTim#(cyc)=pStepTim#(cyc)+1
  randy=Rnd(0,270)
  If (randy=0 And pAnimTim(cyc)>10) Or pAnimTim(cyc)>Int(135/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,0) 
  DropWeapon(cyc,10)
  If game=1 And tauntLearned(12)<1 Then tauntLearned(12)=1
  If EntertainViable(cyc,0) Then entScore=entScore+1
 EndIf
 ;----------- 120-140: FALLS ----------
 ;fall backwards onto back
 If pAnim(cyc)=120
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=Rnd#(2.0,3.0)
   If ((pOldAnim(cyc)=>309 And pOldAnim(cyc)=<699) Or (pOldAnim(cyc)=>800 And pOldAnim(cyc)=<899)) Or (pOldAnim(cyc)=>800 And pOldAnim(cyc)=<899) Then transition=0 Else transition=7
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,120),transition
   pStagger#(cyc)=Rnd(0.5,1.0)
   ResetMultiSting(cyc)
  EndIf
  pStagger#(cyc)=pStagger#(cyc)-(pStagger#(cyc)/Int(85/pAnimSpeed#(cyc)))
  If pStagger#(cyc)<0 Then pStagger#(cyc)=0
  Advance(cyc,pA#(cyc),-pStagger#(cyc))
  If pAnimTim(cyc)<Int(30/pAnimSpeed#(cyc)) Then RiskSpills(cyc,pA#(cyc)+180)
  If pAnimTim(cyc)=>Int(10/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(70/pAnimSpeed#(cyc)) Then FindSmashes(cyc,pLimbY#(cyc,36),0) 
  If pAnimTim(cyc)=<Int(60/pAnimSpeed#(cyc)) Then pExcusedItems(cyc)=1 Else pExcusedItems(cyc)=0
  If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then FallEffects(cyc,1) : FindSmashes(cyc,pY#(cyc),0)
  If pAnimTim(cyc)=>Int(110/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,150)
  DropWeapon(cyc,20)
 EndIf
 ;fall right onto front
 If pAnim(cyc)=121
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=Rnd#(1.85,2.75)
   If (pOldAnim(cyc)=>309 And pOldAnim(cyc)=<699) Or (pOldAnim(cyc)=>800 And pOldAnim(cyc)=<899) Then transition=0 Else transition=7
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,121),transition
   pStagger#(cyc)=Rnd(0.5,1.0)
   ResetMultiSting(cyc)
  EndIf
  pStagger#(cyc)=pStagger#(cyc)-(pStagger#(cyc)/Int(70/pAnimSpeed#(cyc)))
  If pStagger#(cyc)<0 Then pStagger#(cyc)=0
  Advance(cyc,pA#(cyc),-pStagger#(cyc))
  If pAnimTim(cyc)<Int(20/pAnimSpeed#(cyc)) Then RiskSpills(cyc,pA#(cyc)+180)
  If pAnimTim(cyc)=>Int(10/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(55/pAnimSpeed#(cyc)) Then FindSmashes(cyc,pLimbY#(cyc,36),0)
  If pAnimTim(cyc)=<Int(45/pAnimSpeed#(cyc)) Then pExcusedItems(cyc)=1 Else pExcusedItems(cyc)=0
  If pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) Then FallEffects(cyc,1) : FindSmashes(cyc,pY#(cyc),0)
  If pAnimTim(cyc)=>Int(95/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,152) : SharpTransition(cyc,152,0,180)
  DropWeapon(cyc,20)
 EndIf
 ;fall right onto hands & knees
 If pAnim(cyc)=122
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=Rnd#(1.75,2.5)
   If (pOldAnim(cyc)=>309 And pOldAnim(cyc)=<699) Or (pOldAnim(cyc)=>800 And pOldAnim(cyc)=<899) Then transition=0 Else transition=7
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,122),transition
   pStagger#(cyc)=Rnd(0.2,0.6)
   ResetMultiSting(cyc)
  EndIf
  pStagger#(cyc)=pStagger#(cyc)-(pStagger#(cyc)/Int(50/pAnimSpeed#(cyc)))
  If pStagger#(cyc)<0 Then pStagger#(cyc)=0
  Advance(cyc,pA#(cyc),-pStagger#(cyc))
  If pAnimTim(cyc)<Int(20/pAnimSpeed#(cyc)) Then RiskSpills(cyc,pA#(cyc)+180)
  If pAnimTim(cyc)=>Int(10/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(35/pAnimSpeed#(cyc)) Then FindSmashes(cyc,pLimbY#(cyc,36),0)
  If pAnimTim(cyc)=<Int(30/pAnimSpeed#(cyc)) Then pExcusedItems(cyc)=1 Else pExcusedItems(cyc)=0
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then FallEffects(cyc,1) : FindSmashes(cyc,pY#(cyc),0)
  If pAnimTim(cyc)=>Int(90/pAnimSpeed#(cyc)) 
   ChangeAnim(cyc,154) : SharpTransition(cyc,154,0,180)
   randy=Rnd(0,(5000*optLength)-pHealth(cyc))
   If randy=<25*optLength Or pSpecial(cyc)>0 Then pDT(cyc)=0
  EndIf
  DropWeapon(cyc,20)
 EndIf
 ;fall right onto one knee
 If pAnim(cyc)=123
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=Rnd#(1.75,2.5)
   If (pOldAnim(cyc)=>309 And pOldAnim(cyc)=<699) Or (pOldAnim(cyc)=>800 And pOldAnim(cyc)=<899) Then transition=0 Else transition=7
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,123),transition
   pStagger#(cyc)=Rnd(0.2,0.4)
   ResetMultiSting(cyc)
  EndIf
  pStagger#(cyc)=pStagger#(cyc)-(pStagger#(cyc)/Int(50/pAnimSpeed#(cyc)))
  If pStagger#(cyc)<0 Then pStagger#(cyc)=0
  Advance(cyc,pA#(cyc),-pStagger#(cyc))
  If pAnimTim(cyc)<Int(20/pAnimSpeed#(cyc)) Then RiskSpills(cyc,pA#(cyc)+180)
  If pAnimTim(cyc)=>Int(10/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(30/pAnimSpeed#(cyc)) Then FindSmashes(cyc,pLimbY#(cyc,36),0)
  If pAnimTim(cyc)=<Int(30/pAnimSpeed#(cyc)) Then pExcusedItems(cyc)=1 Else pExcusedItems(cyc)=0
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then FallEffects(cyc,1) : FindSmashes(cyc,pY#(cyc),0)
  If pAnimTim(cyc)=>Int(75/pAnimSpeed#(cyc))
   ChangeAnim(cyc,156) : SharpTransition(cyc,156,0,270)
   randy=Rnd(0,(5000*optLength)-pHealth(cyc))
   If randy=<50*optLength Or pSpecial(cyc)>0 Then pDT(cyc)=0
  EndIf
  DropWeapon(cyc,20)
 EndIf
 ;fall left onto front
 If pAnim(cyc)=124
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=Rnd#(1.85,2.75)
   If (pOldAnim(cyc)=>309 And pOldAnim(cyc)=<699) Or (pOldAnim(cyc)=>800 And pOldAnim(cyc)=<899) Then transition=0 Else transition=7
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,124),transition
   pStagger#(cyc)=Rnd(0.5,1.0)
   ResetMultiSting(cyc)
  EndIf
  pStagger#(cyc)=pStagger#(cyc)-(pStagger#(cyc)/Int(70/pAnimSpeed#(cyc)))
  If pStagger#(cyc)<0 Then pStagger#(cyc)=0
  Advance(cyc,pA#(cyc),-pStagger#(cyc))
  If pAnimTim(cyc)<Int(20/pAnimSpeed#(cyc)) Then RiskSpills(cyc,pA#(cyc)+180)
  If pAnimTim(cyc)=>Int(10/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(55/pAnimSpeed#(cyc)) Then FindSmashes(cyc,pLimbY#(cyc,36),0)
  If pAnimTim(cyc)=<Int(45/pAnimSpeed#(cyc)) Then pExcusedItems(cyc)=1 Else pExcusedItems(cyc)=0
  If pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) Then FallEffects(cyc,1) : FindSmashes(cyc,pY#(cyc),0)
  If pAnimTim(cyc)=>Int(95/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,152) : SharpTransition(cyc,152,0,180)
  DropWeapon(cyc,20)
 EndIf
 ;fall left onto hands & knees
 If pAnim(cyc)=125
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=Rnd#(1.75,2.5)
   If (pOldAnim(cyc)=>309 And pOldAnim(cyc)=<699) Or (pOldAnim(cyc)=>800 And pOldAnim(cyc)=<899) Then transition=0 Else transition=7
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,125),transition
   pStagger#(cyc)=Rnd(0.2,0.6)
   ResetMultiSting(cyc)
  EndIf
  pStagger#(cyc)=pStagger#(cyc)-(pStagger#(cyc)/Int(50/pAnimSpeed#(cyc)))
  If pStagger#(cyc)<0 Then pStagger#(cyc)=0
  Advance(cyc,pA#(cyc),-pStagger#(cyc))
  If pAnimTim(cyc)<Int(20/pAnimSpeed#(cyc)) Then RiskSpills(cyc,pA#(cyc)+180)
  If pAnimTim(cyc)=>Int(10/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(35/pAnimSpeed#(cyc)) Then FindSmashes(cyc,pLimbY#(cyc,36),0)
  If pAnimTim(cyc)=<Int(30/pAnimSpeed#(cyc)) Then pExcusedItems(cyc)=1 Else pExcusedItems(cyc)=0
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then FallEffects(cyc,1) : FindSmashes(cyc,pY#(cyc),0)
  If pAnimTim(cyc)=>Int(90/pAnimSpeed#(cyc))
   ChangeAnim(cyc,154) : SharpTransition(cyc,154,0,180)
   randy=Rnd(0,(5000*optLength)-pHealth(cyc))
   If randy=<25*optLength Or pSpecial(cyc)>0 Then pDT(cyc)=0
  EndIf
  DropWeapon(cyc,20)
 EndIf
 ;fall left onto one knee
 If pAnim(cyc)=126
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=Rnd(1.75,2.5)
   If (pOldAnim(cyc)=>309 And pOldAnim(cyc)=<699) Or (pOldAnim(cyc)=>800 And pOldAnim(cyc)=<899) Then transition=0 Else transition=7
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,126),transition
   pStagger#(cyc)=Rnd(0.2,0.4)
   ResetMultiSting(cyc)
  EndIf
  pStagger#(cyc)=pStagger#(cyc)-(pStagger#(cyc)/Int(50/pAnimSpeed#(cyc)))
  If pStagger#(cyc)<0 Then pStagger#(cyc)=0
  Advance(cyc,pA#(cyc),-pStagger#(cyc))
  If pAnimTim(cyc)<Int(20/pAnimSpeed#(cyc)) Then RiskSpills(cyc,pA#(cyc)+180) 
  If pAnimTim(cyc)=>Int(10/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(30/pAnimSpeed#(cyc)) Then FindSmashes(cyc,pLimbY#(cyc,36),0)
  If pAnimTim(cyc)=<Int(30/pAnimSpeed#(cyc)) Then pExcusedItems(cyc)=1 Else pExcusedItems(cyc)=0
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then FallEffects(cyc,1) : FindSmashes(cyc,pY#(cyc),0)
  If pAnimTim(cyc)=>Int(75/pAnimSpeed#(cyc)) 
   ChangeAnim(cyc,156) : SharpTransition(cyc,156,0,90)
   randy=Rnd(0,(5000*optLength)-pHealth(cyc))
   If randy=<50*optLength Or pSpecial(cyc)>0 Then pDT(cyc)=0
  EndIf
  DropWeapon(cyc,20)
 EndIf
 ;crumple forward onto front
 If pAnim(cyc)=127
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=Rnd#(1.5,2.5)
   If (pOldAnim(cyc)=>309 And pOldAnim(cyc)=<699) Or (pOldAnim(cyc)=>800 And pOldAnim(cyc)=<899) Then transition=0 Else transition=7
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,127),transition
   ResetMultiSting(cyc)
  EndIf
  If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(85/pAnimSpeed#(cyc)) Then FallEffects(cyc,1) : FindSmashes(cyc,pY#(cyc),1)
  If pAnimTim(cyc)=>Int(140/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,152)
  DropWeapon(cyc,20)
 EndIf
 ;crumple forward onto hands and knees
 If pAnim(cyc)=128
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=Rnd#(1.5,2.5)
   If (pOldAnim(cyc)=>309 And pOldAnim(cyc)=<699) Or (pOldAnim(cyc)=>800 And pOldAnim(cyc)=<899) Then transition=0 Else transition=7
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,128),transition
   ResetMultiSting(cyc)
  EndIf
  If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(85/pAnimSpeed#(cyc)) Then FallEffects(cyc,1) : FindSmashes(cyc,pY#(cyc),1)
  If pAnimTim(cyc)=>Int(110/pAnimSpeed#(cyc))
   ChangeAnim(cyc,154)
   randy=Rnd(0,(5000*optLength)-pHealth(cyc))
   If randy=<25*optLength Or pSpecial(cyc)>0 Then pDT(cyc)=0
  EndIf
  DropWeapon(cyc,20)
 EndIf
 ;fall forward onto front
 If pAnim(cyc)=129
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=Rnd#(2.5,3.5)  
   If (pOldAnim(cyc)=>309 And pOldAnim(cyc)=<699) Or (pOldAnim(cyc)=>800 And pOldAnim(cyc)=<899) Then transition=0 Else transition=20/pAnimSpeed#(cyc)
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,129),transition
   pStagger#(cyc)=Rnd(0.75,1.5)
   ResetMultiSting(cyc)
  EndIf
  pStagger#(cyc)=pStagger#(cyc)-(pStagger#(cyc)/Int(60/pAnimSpeed#(cyc)))
  If pStagger#(cyc)<0 Then pStagger#(cyc)=0
  Advance(cyc,pA#(cyc),pStagger#(cyc)) 
  If pAnimTim(cyc)<Int(40/pAnimSpeed#(cyc)) Then RiskSpills(cyc,pA#(cyc)) 
  If pAnimTim(cyc)=>Int(40/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(80/pAnimSpeed#(cyc)) Then FindSmashes(cyc,pLimbY#(cyc,36),0)
  If pAnimTim(cyc)=<Int(70/pAnimSpeed#(cyc)) Then pExcusedItems(cyc)=1 Else pExcusedItems(cyc)=0
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then FallEffects(cyc,1) : FindSmashes(cyc,pY#(cyc),0)
  If pAnimTim(cyc)=>Int(120/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,152)
  DropWeapon(cyc,20)
 EndIf

 ;downgrade to hands & knees
 If pAnim(cyc)=137
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=Rnd#(1.0,2.5) : transition=2
   If pHurtTim(cyc)>0 Then pAnimSpeed#(cyc)=4.0 : transition=0
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,137),transition
  EndIf
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc))
   ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
   ProduceSound(p(cyc),sFall,22050,0.5)
   ProduceSound(p(cyc),sThud,22050,0.5)
  EndIf
  If pAnimTim(cyc)=>Int(60/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,154)
  DropWeapon(cyc,20)
  If EntertainViable(cyc,0) Then entScore=entScore+1
 EndIf
 ;downgrade to flat on front
 If pAnim(cyc)=138
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=Rnd#(1.0,2.5)
   If pOldAnim(cyc)=158 Then transition=5 Else transition=2
   If pHurtTim(cyc)>0 Then pAnimSpeed#(cyc)=6.0 : transition=0
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,138),transition
  EndIf
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc))
   ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
   ProduceSound(p(cyc),sFall,22050,0.5)
   ProduceSound(p(cyc),sThud,22050,0.5)
  EndIf
  If pAnimTim(cyc)=>Int(50/pAnimSpeed#(cyc)) And pAnimSpeed#(cyc)=>4.0 Then ChangeAnim(cyc,109)
  If pAnimTim(cyc)=>Int(60/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,152)
  DropWeapon(cyc,20)
  If EntertainViable(cyc,0) Then entScore=entScore+1
 EndIf
 ;downgrade to flat on back
 If pAnim(cyc)=139
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=Rnd#(1.5,3.0)
   If pHurtTim(cyc)>0 Then pAnimSpeed#(cyc)=5.0
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,139),0
  EndIf
  If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc))
   ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
   ProduceSound(p(cyc),sFall,22050,0.5)
   ProduceSound(p(cyc),sThud,22050,0.5)
  EndIf
  If pAnimTim(cyc)=>Int(80/pAnimSpeed#(cyc)) And pAnimSpeed#(cyc)=>4.0 Then ChangeAnim(cyc,99)
  If pAnimTim(cyc)=>Int(100/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,150)
  DropWeapon(cyc,20)
  If EntertainViable(cyc,0) Then entScore=entScore+1
 EndIf
 ;----------- 140-150: NOVELTY FALLS ------------
 ;fall over ropes to apron
 If pAnim(cyc)=140
  ResetExcuses(cyc,1)
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=2.5
   If pOldAnim(cyc)=44 Then transition=0 Else transition=5
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,140),transition
   ShakeRopes(RealX#(cyc),RealZ#(cyc),20,0)
   ResetMultiSting(cyc)
  EndIf
  If pAnimTim(cyc)=10 Then ProduceSound(p(cyc),sAgony(1),GetVoice(cyc),0)
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc))
   ProduceSound(p(cyc),sSwing,20000,0.5)
   RopeSound(p(cyc),0)
   Pop(0,Rnd(2,9),0)
  EndIf
  If pAnimTim(cyc)=>Int(35/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(75/pAnimSpeed#(cyc)) Then FindSmashes(cyc,pLimbY#(cyc,36),0)
  If pAnimTim(cyc)=Int(75/pAnimSpeed#(cyc)) Then FallEffects(cyc,1) : ShakeRing(0,1.0) : FindSmashes(cyc,pY#(cyc),0)
  If pAnimTim(cyc)=Int(80/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(120/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(100/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=>Int(140/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,0)
   ResetExcuses(cyc,0)
   pPlatform(cyc)=NearestSide(pX#(cyc),pZ#(cyc))
   randy=Rnd(0,pAgility(cyc))
   If randy=<1 And matchCage=0 Then ChangeAnim(cyc,142)
   If pAnim(cyc)=0 Then pHP(cyc)=100
  Else
   If RealX#(cyc)<-81 Or RealX#(cyc)>81 Or RealZ#(cyc)<-81 Or RealZ#(cyc)>81 Then Advance(cyc,pA#(cyc),0.1)
   If InsideRing(RealX#(cyc),RealZ#(cyc),-28) Then Advance(cyc,pA#(cyc),-0.1)
  EndIf
  DropWeapon(cyc,20)
  entScore=entScore+1
  If matchState=3 And matchCountOuts=3 And matchCage=0 And LegalMan(cyc) Then entScore=entScore+1
 EndIf
 ;fall over ropes to floor
 If pAnim(cyc)=141
  ResetExcuses(cyc,1)
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=2.5
   If pOldAnim(cyc)=44 Then transition=0 Else transition=5
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,141),transition
   ShakeRopes(RealX#(cyc),RealZ#(cyc),20,0)
   ResetMultiSting(cyc)
  EndIf
  If pAnimTim(cyc)=10 Then ProduceSound(p(cyc),sAgony(1),GetVoice(cyc),1)
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc))
   ProduceSound(p(cyc),sSwing,20000,0.5)
   RopeSound(p(cyc),0)
   Pop(0,Rnd(2,9),0)
  EndIf
  If pAnimTim(cyc)=>Int(35/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(150/pAnimSpeed#(cyc)) Then FindSmashes(cyc,pLimbY#(cyc,36),0)
  If pAnimTim(cyc)=Int(75/pAnimSpeed#(cyc)) Then FallEffects(cyc,1)
  If pAnimTim(cyc)=Int(140/pAnimSpeed#(cyc)) Then FallEffects(cyc,2) : FindSmashes(cyc,pY#(cyc),0)
  If pAnimTim(cyc)=>Int(190/pAnimSpeed#(cyc))
   ChangeAnim(cyc,152) : SharpTransition(cyc,152,1,90)
   ResetExcuses(cyc,0) : pY#(cyc)=wGround#
  EndIf
  If pAnimTim(cyc)>Int(80/pAnimSpeed#(cyc)) And pAnimTim(cyc)<Int(170/pAnimSpeed#(cyc))
   offset#=(Float(charHeight(pChar(cyc)))/225)-0.053
   pY#(cyc)=pY#(cyc)+offset#
  EndIf
  If InsideRing(RealX#(cyc),RealZ#(cyc),-28) Then Advance(cyc,pA#(cyc),-0.1)
  DropWeapon(cyc,20)
  entScore=entScore+1
  If matchState=3 And matchCountOuts=3 And matchCage=0 And LegalMan(cyc) Then entScore=entScore+1
 EndIf
 ;fall from apron
 If pAnim(cyc)=142
  ResetExcuses(cyc,1)
  If pAnimTim(cyc)=0
   ProduceSound(p(cyc),sAgony(1),GetVoice(cyc),1)
   pAnimSpeed#(cyc)=3.0
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,142),5
   ShakeRopes(RealX#(cyc),RealZ#(cyc),20,0)
   ResetMultiSting(cyc)
  EndIf
  If pAnimTim(cyc)=5 Then Pop(0,Rnd(2,9),0) : ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=>Int(50/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(110/pAnimSpeed#(cyc)) Then FindSmashes(cyc,pLimbY#(cyc,36),0)
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then FallEffects(cyc,1)
  If pAnimTim(cyc)=Int(100/pAnimSpeed#(cyc)) Then FallEffects(cyc,2) : FindSmashes(cyc,pY#(cyc),0)
  If pAnimTim(cyc)=>Int(160/pAnimSpeed#(cyc))
   ChangeAnim(cyc,150) : SharpTransition(cyc,143,1,270)
   pPlatform(cyc)=0 : pY#(cyc)=wGround#
   ResetExcuses(cyc,0)
  Else
   offset#=(Float(charHeight(pChar(cyc)))/400)-0.03
   pY#(cyc)=pY#(cyc)+offset#
  EndIf
  DropWeapon(cyc,20)
  entScore=entScore+1
  If matchState=3 And matchCountOuts=3 And matchCage=0 And LegalMan(cyc) Then entScore=entScore+1
 EndIf
 ;fall off platform
 If pAnim(cyc)=143 Or pAnim(cyc)=144
  If InsideRing(pX#(cyc),pZ#(cyc),-15) Then pGround#(cyc)=wStage# Else pGround#(cyc)=wGround#
  If pAnimTim(cyc)=0
   ProduceSound(p(cyc),sAgony(1),GetVoice(cyc),1)
   If pAnim(cyc)=143 Then height#=LeapHeight#(cyc,-(pY#(cyc)-pGround#(cyc)))
   If pAnim(cyc)=144 Then height#=LeapHeight#(cyc,pY#(cyc)-pGround#(cyc))
   pAnimSpeed#(cyc)=2.5+((23.0-height#)/50)
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,pAnim(cyc)+1),20/pAnimSpeed#(cyc)
   pTravel#(cyc)=2.0 : pGravity#(cyc)=2.0
   If pAnim(cyc)=143 Then pFlightA#(cyc)=pA#(cyc)+180 : pFlyAgenda(cyc)=GetFlyAgenda(cyc,-(pY#(cyc)-pGround#(cyc)))
   If pAnim(cyc)=144 Then pFlightA#(cyc)=pA#(cyc) : pFlyAgenda(cyc)=GetFlyAgenda(cyc,pY#(cyc)-pGround#(cyc))
   If pPlatform(cyc)=>10 And pPlatform(cyc)=<90 And FlightClear(cyc,pFlightA#(cyc),15) Then pTravel#(cyc)=pTravel#(cyc)/2
   If pY#(cyc)<pGround#(cyc)+10 Then pGravity#(cyc)=pGravity#(cyc)+(pGravity#(cyc)/2)
   If pY#(cyc)=<pGround#(cyc) Then pY#(cyc)=pGround#(cyc)+0.1
   pPlatform(cyc)=0
   pExcusedPlays(cyc)=1 : pExcusedItems(cyc)=1
   ResetMultiSting(cyc)
  EndIf
  If pAnimTim(cyc)=5 Then ProduceSound(p(cyc),sSwing,20000,0.5) : Pop(0,Rnd(2,9),1)
  pTravel#(cyc)=pTravel#(cyc)-(pTravel#(cyc)/Int(80/pAnimSpeed#(cyc)))
  If pTravel#(cyc)<0 Then pTravel#(cyc)=0
  Advance(cyc,pFlightA#(cyc),pTravel#(cyc))
  If pY#(cyc)>pGround#(cyc)
   pGravity#(cyc)=pGravity#(cyc)-0.25
   If BlockConflict(pX#(cyc),pY#(cyc)-1,pZ#(cyc),0)
    If pTravel#(cyc)<0.5 Then pTravel#(cyc)=0.5 
    ;If pGravity#(cyc)<0 Then pGravity#(cyc)=0
   EndIf
   pY#(cyc)=pY#(cyc)+pGravity#(cyc)
   FlightCorrection(cyc,0)
  EndIf
  If pAnimTim(cyc)=>Int(50/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(100/pAnimSpeed#(cyc)) And pGravity#(cyc)<-1.0 Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pY#(cyc)>pGround#(cyc) And pGravity#(cyc)<-1.0 Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pY#(cyc)<pGround#(cyc)
   FallEffects(cyc,2)
   pY#(cyc)=pGround#(cyc)
   pTravel#(cyc)=pTravel#(cyc)/2
   FindSmashes(cyc,pY#(cyc),0)
  EndIf 
  If pAnimTim(cyc)=>Int(130/pAnimSpeed#(cyc))
   If pY#(cyc)=pGround#(cyc) Or pAnimTim(cyc)=>Int(130/pAnimSpeed#(cyc))*2
    If pAnim(cyc)=143 Then ChangeAnim(cyc,150)
    If pAnim(cyc)=144 Then ChangeAnim(cyc,152) : SharpTransition(cyc,152,0,90)
    ResetExcuses(cyc,0)
    pY#(cyc)=pGround#(cyc)
    FlightCorrection(cyc,1)
   EndIf
  EndIf
  DropWeapon(cyc,20)
  If EntertainViable(cyc,0) Then entScore=entScore+1
 EndIf
 ;thrown out into fall
 If pAnim(cyc)=145 Or pAnim(cyc)=146
  ResetExcuses(cyc,1)
  pGround#(cyc)=wGround#
  If pAnimTim(cyc)=0 
   Pop(0,Rnd(2,8),1)
   ProduceSound(p(cyc),sSwing,21000,0.5)
   ProduceSound(p(cyc),sAgony(1),GetVoice(cyc),1)
   ShakeRopes(RealX#(cyc),RealZ#(cyc),20,1)
   pAnimSpeed#(cyc)=2.0
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,pAnim(cyc)+1),0
   If pAnim(cyc)=146 Then pTravel#(cyc)=2.5 Else pTravel#(cyc)=2.25
   If pAnim(cyc)=146 Then pGravity#(cyc)=2.5 Else pGravity#(cyc)=2.0
   pVelocity#(cyc)=pGravity#(cyc)/5.5
   ResetMultiSting(cyc)
  EndIf
  pTravel#(cyc)=pTravel#(cyc)-(pTravel#(cyc)/Int(85/pAnimSpeed#(cyc)))
  If pTravel#(cyc)<0 Then pTravel#(cyc)=0
  Advance(cyc,pFlightA#(cyc),pTravel#(cyc))
  If pX#(cyc)>130 Then pX#(cyc)=130
  If pX#(cyc)<-130 Then pX#(cyc)=-130
  If pZ#(cyc)>130 Then pZ#(cyc)=130
  If pZ#(cyc)<-130 Then pZ#(cyc)=-130
  If pY#(cyc)>pGround#(cyc)
   pGravity#(cyc)=pGravity#(cyc)-pVelocity#(cyc)
   pY#(cyc)=pY#(cyc)+pGravity#(cyc)
  EndIf
  If pAnimTim(cyc)=>Int(30/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(85/pAnimSpeed#(cyc)) And pGravity#(cyc)<-1.0 Then FindSmashes(cyc,pY#(cyc),0)
  If pY#(cyc)>pGround#(cyc) And pGravity#(cyc)<-1.0 Then FindSmashes(cyc,pY#(cyc),0)
  If pY#(cyc)<pGround#(cyc)
   Pop(0,Rnd(2,9),1)
   ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),1)
   ProduceSound(p(cyc),sBlock(7),22050,1)
   ProduceSound(p(cyc),sFall,22050,1)
   ProduceSound(p(cyc),sThud,22050,1)
   ScarArea(cyc,0,0,0,10)
   RiskInjury(cyc,Rnd(0,5),1)
   pHealth(cyc)=pHealth(cyc)-1000
   entScore=entScore+250
   pDT(cyc)=GetDT(cyc,1000)
   pHeat(cyc)=pHeat(cyc)-5
   pY#(cyc)=pGround#(cyc)
   pTravel#(cyc)=pTravel#(cyc)/2
   FindSmashes(cyc,pY#(cyc),0)
  EndIf 
  If pAnimTim(cyc)>Int(120/pAnimSpeed#(cyc))
   If pAnim(cyc)=145 Then ChangeAnim(cyc,150)
   If pAnim(cyc)=146 Then ChangeAnim(cyc,152)
   ResetExcuses(cyc,0)
  EndIf
  DropWeapon(cyc,20)
  entScore=entScore+1
  If matchState=3 And matchCountOuts=3 And matchCage=0 And LegalMan(cyc) Then entScore=entScore+1
 EndIf
 ;----------- 150-160: GROUNDED STATES ----------
 ;lying on back
 If pAnim(cyc)=150
  newAnim=-1
  anim=150
  If pPinner(cyc)>0 And pPinStyle(pPinner(cyc))=>4 And pAnim(pPinner(cyc))=161 Then anim=165
  If pAnimTim(cyc)=0 Or anim<>pState(cyc) 
   transition=2
   If pOldAnim(cyc)=99 Or pOldAnim(cyc)=142 Or pPinner(cyc)>0 Then transition=5 
   If anim=165 Or pState(cyc)=165 Then transition=10
   Animate p(cyc),1,Rnd#(0.1,0.5),pSeq(cyc,anim),transition
   pState(cyc)=anim
  EndIf
  If pGrappler(cyc)=0 And (pPinner(cyc)=0 Or pDT(cyc)=<0)
   If pDT(cyc)<optLieHP
    proceed=0
    randy=Rnd(0,pDT(cyc))
    If (randy=0 Or Isolated(cyc,30)=0) And pControl(cyc)=0 Then proceed=1
    If pControl(cyc)>0 And (ActionPressed(cyc) Or DirPressed(cyc)) Then proceed=1
    If proceed=1 Then newAnim=151
   EndIf
   If (BlockClear(cyc,pA#(cyc),-20)=0 Or ItemClear(cyc,pA#(cyc),-20)=0) And pPinner(cyc)=0 Then newAnim=151 
  EndIf
  If newAnim=>0 Then ChangeAnim(cyc,newAnim)
 EndIf
 ;rise up off back onto hands & knees
 If pAnim(cyc)=151
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=PercentOf#(Rnd#(2.0,4.0),pAgility(cyc))
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,151),2
  EndIf
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=>Int(75/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,154) : SharpTransition(cyc,154,0,180) 
 EndIf
 ;lying on front
 If pAnim(cyc)=152
  newAnim=-1
  If pOldAnim(cyc)=109 Then transition=5 Else transition=0
  If pAnimTim(cyc)=0 Then Animate p(cyc),1,Rnd#(0.1,0.5),pSeq(cyc,152),transition
  If pGrappler(cyc)=0
   randy=Rnd(0,1000)
   If randy=0 And pDT(cyc)>0 Then newAnim=159
   If pDT(cyc)<optLieHP
    proceed=0
    randy=Rnd(0,pDT(cyc))
    If (randy=0 Or Isolated(cyc,30)=0) And pControl(cyc)=0 Then proceed=1
    If pControl(cyc)>0 And (ActionPressed(cyc) Or DirPressed(cyc)) Then proceed=1
    If proceed=1 Then newAnim=153
   EndIf
   If (BlockClear(cyc,pA#(cyc),20)=0 Or ItemClear(cyc,pA#(cyc),20)=0) And pPinner(cyc)=0 Then newAnim=153 
  EndIf
  If newAnim=>0
   ChangeAnim(cyc,newAnim)
   If newAnim=159 Then SharpTransition(cyc,159,0,180) 
  EndIf
 EndIf
 ;rise up off front onto hands & knees
 If pAnim(cyc)=153
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=PercentOf#(Rnd#(2.0,4.0),pAgility(cyc))
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,153),2
  EndIf
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=>Int(40/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,154)
 EndIf
 ;resting on hands & knees
 If pAnim(cyc)=154
  newAnim=-1
  If pOldAnim(cyc)=158 Then transition=15 Else transition=2
  If pAnimTim(cyc)=0 Then Animate p(cyc),1,Rnd#(0.1,0.5),pSeq(cyc,154),transition
  chance=pHealth(cyc)/5
  If chance<300 Then chance=300
  randy=Rnd(0,chance)
  If randy=0 And pDT(cyc)>0 And pDT(cyc)<optLieHP Then pDT(cyc)=pDT(cyc)+Rnd(0,150-(pHealth(cyc)/100))
  If pAnimTim(cyc)>10 And DirPressed(cyc) Then newAnim=158 
  If pDT(cyc)>optLieHP And BlockClear(cyc,pA#(cyc),20) Then newAnim=Rnd(138,139) : Pop(0,Rnd(2,8),0)
  If pDT(cyc)<optCrawlHP
   proceed=0 
   If pControl(cyc)>0
    If ActionPressed(cyc) Then proceed=1
    If cAttack(cyc)=1 And InRearView(cyc,pLimb(pFoc(cyc),36),90) Then proceed=2
   Else
    v=pFoc(cyc)
    randy=Rnd(0,pDT(cyc))
    If (randy=0 Or Isolated(cyc,30)=0) And ProtectPin(cyc)=0 Then proceed=1
    randy=Rnd(0,1)
    If randy=0 And Isolated(cyc,25)=0 And InRearView(cyc,pLimb(v,36),75) And pY#(cyc)=>pY#(v)-5 And pY#(cyc)=<pY#(v)+5 And ProtectPin(cyc)=0 Then proceed=2
   EndIf
   If proceed=1 Then newAnim=155
   If proceed=2 Then newAnim=69 
  EndIf
  If pPinning(cyc)=0 And pPinner(cyc)=0
   If BlockClear(cyc,pA#(cyc),15)=0 Or ItemClear(cyc,pA#(cyc),15)=0 Or CrawlOverlap(cyc) Then newAnim=155
  EndIf
  If newAnim=>0
   ChangeAnim(cyc,newAnim)
   If pAnim(cyc)=139 Then SharpTransition(cyc,139,0,180)
  EndIf
 EndIf
 ;rise up onto one knee
 If pAnim(cyc)=155
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=PercentOf#(Rnd#(2.0,4.0),pAgility(cyc))
   If CrawlOverlap(cyc) Then pAnimSpeed#(cyc)=2.5
   If pOldAnim(cyc)=158 Then transition=5 Else transition=2
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,155),transition
  EndIf
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=>Int(40/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,156)
  chance=pHealth(cyc)/8
  If chance<150 Then chance=150
  randy=Rnd(0,chance)
  If randy=0 And pAnimTim(cyc)>Int(20/pAnimSpeed#(cyc)) Then pHP(cyc)=0
 EndIf
 ;resting on one knee
 If pAnim(cyc)=156
  newAnim=-1
  If pAnimTim(cyc)=0 
   If pOldAnim(cyc)=65 Then transition=5 Else transition=2
   Animate p(cyc),1,Rnd#(0.1,0.5),pSeq(cyc,156),transition
  EndIf
  chance=pHealth(cyc)/10
  If chance<150 Then chance=150
  randy=Rnd(0,chance)
  If randy=0 And pDT(cyc)>25 And pDT(cyc)<optCrawlHP Then pDT(cyc)=pDT(cyc)+Rnd(0,150-(pHealth(cyc)/100))
  If pDT(cyc)>optCrawlHP
   If BlockClear(cyc,pA#(cyc),15) And CrawlOverlap(cyc)=0 Then newAnim=137 : Pop(0,Rnd(2,8),0)
  EndIf
  If pDT(cyc)=<25
   proceed=0
   randy=Rnd(0,pDT(cyc))
   If (randy=0 Or Isolated(cyc,30)=0) And pControl(cyc)=0 Then proceed=1
   If pControl(cyc)>0 And (DirPressed(cyc) Or ActionPressed(cyc)) Then proceed=1 
   If proceed=1 Then newAnim=157
  EndIf
  If cAttack(cyc)=1 And cGrapple(cyc)=0 And cRun(cyc)=0 And pDT(cyc)=<optCrawlHP And pDizzyTim(cyc)=0 And pBlindTim(cyc)=0
   If pFoc(cyc)>0 And InProximity(cyc,pFoc(cyc),50) And pY#(cyc)=<pY#(pFoc(cyc)) And AttackViable(pFoc(cyc))=>1 And AttackViable(pFoc(cyc))=<2 Then newAnim=65
  EndIf
  If newAnim=>0 Then ChangeAnim(cyc,newAnim)
 EndIf
 ;rise up onto feet
 If pAnim(cyc)=157
  newAnim=-1 
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=PercentOf#(Rnd#(2.0,4.0),pAgility(cyc))
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,157),2
  EndIf
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=>Int(40/pAnimSpeed#(cyc))
   newAnim=0
   If pHP(cyc)<PercentOf#(pHealth(cyc)/10,75) Then pHP(cyc)=PercentOf#(pHealth(cyc)/10,75)
   If pHP(cyc)<100 Then pHP(cyc)=100
  EndIf
  chance=pHealth(cyc)/8
  If chance<150 Then chance=150
  randy=Rnd(0,chance)
  If randy=0 And pAnimTim(cyc)=<Int(30/pAnimSpeed#(cyc)) Then pHP(cyc)=0
  If newAnim=>0 Then ChangeAnim(cyc,newAnim) 
 EndIf
 ;crawling
 If pAnim(cyc)=158
  newAnim=-1
  If pAnimTim(cyc)=0 
   pAnimSpeed#(cyc)=pSpeed#(cyc)*1.5
   If pAnimSpeed#(cyc)<0.5*1.5 Then pAnimSpeed#(cyc)=0.5*1.5
   If pAnimSpeed#(cyc)>0.7*1.5 Then pAnimSpeed#(cyc)=0.7*1.5
   Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,158),15
  EndIf
  If DirPressed(cyc) Then Advance(cyc,RequestAngle#(cyc),pSpeed#(cyc)/4)
  pStepTim#(cyc)=pStepTim#(cyc)+1
  chance=pHealth(cyc)/5
  If chance<300 Then chance=300
  randy=Rnd(0,chance)
  If randy=0 And pDT(cyc)>0 And pDT(cyc)<optLieHP Then pDT(cyc)=pDT(cyc)+Rnd(0,150-(pHealth(cyc)/100))
  If pAnimTim(cyc)>5 And DirPressed(cyc)=0 Then newAnim=154
  If pDT(cyc)>optLieHP And BlockClear(cyc,pA#(cyc),20) Then newAnim=Rnd(138,139)
  If pDT(cyc)<optCrawlHP
   proceed=0
   randy=Rnd(0,pDT(cyc))
   If (randy=0 Or Isolated(cyc,30)=0) And pControl(cyc)=0 And ProtectPin(cyc)=0 Then proceed=1
   If pControl(cyc)>0 And ActionPressed(cyc) Then proceed=1
   If proceed=1 Then newAnim=155
  EndIf
  If pPinning(cyc)=0 And pPinner(cyc)=0
   If BlockClear(cyc,pA#(cyc),15)=0 Or ItemClear(cyc,pA#(cyc),15)=0 Or CrawlOverlap(cyc) Then newAnim=155
  EndIf
  If newAnim=>0
   ChangeAnim(cyc,newAnim)
   If pAnim(cyc)=139 Then SharpTransition(cyc,139,0,180)
  EndIf
 EndIf
 ;roll onto back
 If pAnim(cyc)=159
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=3.0
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,159),0
  EndIf
  If pAnimTim(cyc)=Int(5/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(75/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(55/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=>Int(100/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,150)
  DropWeapon(cyc,20)
  If EntertainViable(cyc,0) Then entScore=entScore+1
 EndIf
 ;----------- 160-170: PINNING ----------
 ;initiate pin
 If pAnim(cyc)=160
  pExcusedPlays(cyc)=1
  v=pPinning(cyc)
  If pAnimTim(cyc)=0
   If pAnim(v)=139 Or pAnim(v)=159 Then pAnimSpeed#(cyc)=1.5 Else pAnimSpeed#(cyc)=2.0
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,160),5  
  EndIf
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sFall,22050,0.5)
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc))
   If pPinStyle(cyc)=4 Then Animate p(v),1,Rnd#(0.25,0.5),pSeq(cyc,165),10
   If pPinStyle(cyc)=5 Then Animate p(v),1,Rnd#(0.25,0.5),pSeq(cyc,165),10
  EndIf
  If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc))
   If pPinStyle(cyc)=1 Then Animate p(cyc),1,Rnd#(0.25,0.5),pSeq(cyc,162),10
   If pPinStyle(cyc)=>2 And pPinStyle(cyc)=<3 Then Animate p(cyc),1,Rnd#(0.25,0.5),pSeq(cyc,161),10
   If pPinStyle(cyc)=4 Then Animate p(cyc),1,Rnd#(0.25,0.5),pSeq(cyc,163),10
   If pPinStyle(cyc)=5 Then Animate p(cyc),1,Rnd#(0.25,0.5),pSeq(cyc,164),10
  EndIf
  If pAnimTim(cyc)>Int(30/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,161) : ChangeAnim(v,150) 
  ApplyPin(cyc,v)
  DropWeapon(cyc,0)
 EndIf
 ;pinning loop
 If pAnim(cyc)=161
  pExcusedPlays(cyc)=1
  v=pPinning(cyc)
  If pAnimTim(cyc)=0 
   transition=5
   If pOldAnim(cyc)=156 Or pOldAnim(cyc)=158 Then transition=10
   If pOldAnim(cyc)=154 Then transition=20
   If pPinStyle(cyc)=1 Then Animate p(cyc),1,Rnd#(0.25,0.5),pSeq(cyc,162),transition
   If pPinStyle(cyc)=>2 And pPinStyle(cyc)=<3 Then Animate p(cyc),1,Rnd#(0.25,0.5),pSeq(cyc,161),transition
   If pPinStyle(cyc)=4 Then Animate p(cyc),1,Rnd#(0.25,0.5),pSeq(cyc,163),transition
   If pPinStyle(cyc)=5 Then Animate p(cyc),1,Rnd#(0.25,0.5),pSeq(cyc,164),transition
   entScore=entScore+50
  EndIf
  cancel=0
  If pControl(cyc)>0 And cGrapple(cyc)=1 Then cancel=1
  If pControl(cyc)=0
   If pControl(cyc)=0 And matchRules=>1 And pAnimTim(cyc)>50 And pPinCount(v)=0
    If pChecked(v)>0 Or no_refs=0
     If TouchingRopes(cyc) Or TouchingRopes(v) Or LegalMan(v)=0 Then cancel=1
    EndIf
   EndIf
   If pWarned(cyc)>0 Then cancel=1
   If matchState<>3 Or LegalMan(v)=0 Or FallsCount(cyc)=0 Then cancel=1
  EndIf
  If cancel=1 Or pAnim(v)<>150 Or pPinning(cyc)=0
   If cancel=0
    If matchState=3 And pPinCount(v)=0 Then entScore=entScore+Rnd(charPopularity(pChar(v))/4,charPopularity(pChar(v))/2)
    If matchState=3 And pPinCount(v)=1 Then Pop(v,Rnd(2,9),0) : entScore=entScore+Rnd(charPopularity(pChar(v)),charPopularity(pChar(v))*2)
    If matchState=3 And pPinCount(v)=>2 Then Pop(v,Rnd(2,9),1) : Pop(v,Rnd(2,9),0) : entScore=entScore+Rnd(charPopularity(pChar(v))*2,charPopularity(pChar(v))*4)
    ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
    ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0)
    ProduceSound(p(cyc),sSwing,22050,0.25)
    If pPinCount(v)>0 Then pHeat(cyc)=pHeat(cyc)-5 : pHeat(v)=pHeat(v)+5
   EndIf
   If cancel=1 Or pPinCount(v)=0 Then anim=162 Else anim=Rnd(162,163)
   ChangeAnim(cyc,anim) 
   randy=Rnd(0,1)
   If randy=0 And cancel=0 Then pHurtA#(cyc)=pA#(cyc)+180 : pHP(cyc)=0
   pPinner(v)=0 : pPinning(cyc)=0
   pExcusedPlays(cyc)=0
  EndIf
  ApplyPin(cyc,v)
 EndIf
 ;release pin
 If pAnim(cyc)=162
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=0.75+PercentOf#(0.75,pAgility(cyc))
   Animate p(cyc),3,-pAnimSpeed#(cyc),pSeq(cyc,160),0
  EndIf
  If pAnimTim(cyc)<Int(25/pAnimSpeed#(cyc)) Then Advance(cyc,pA#(cyc),-0.25)
  If pAnimTim(cyc)=Int(12/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)>Int(25/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,0) 
 EndIf
 ;exasperated pin release
 If pAnim(cyc)=163
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=1.5
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,166),0
  EndIf
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then Pop(0,Rnd(2,9),0)
  If pAnimTim(cyc)<Int(45/pAnimSpeed#(cyc)) Or pAnimTim(cyc)>Int(125/pAnimSpeed#(cyc)) Then Advance(cyc,pA#(cyc),-0.1)
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(125/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(150/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)>Int(175/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,0) 
  entScore=entScore+1
 EndIf
 ;----------- 170-190: REFEREEING ----------
 ;standing single/double count
 If pAnim(cyc)=170 Or pAnim(cyc)=171 
  newAnim=-1
  If pAnimTim(cyc)=0 Then pAnimSpeed#(cyc)=2.5 : Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,pAnim(cyc)),5
  If pAnimTim(cyc)>Int(10/pAnimSpeed#(cyc)) And pAnimTim(cyc)<Int(50/pAnimSpeed#(cyc))
   If matchCounter<>cyc Then newAnim=0 
  EndIf
  If pAnimTim(cyc)>Int(10/pAnimSpeed#(cyc))
   cancel=1
   For v=1 To no_plays
    If RefViable(v)=5 Or RefViable(v)=15 Then cancel=0
   Next
   If cancel=1 Then newAnim=0 
  EndIf
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc))
   sound=0
   For v=1 To no_plays
    If RefViable(v)=5 Or RefViable(v)=15
     pOutCount(v)=pOutCount(v)+1
     If pOutCount(v)>10 Then pOutCount(v)=10
     Pop(0,Rnd(2,8),Float(pOutCount(v))/10)
     If pOutCount(v)>sound Then sound=pOutCount(v)
    EndIf
   Next
   If sound>0 Then ProduceSound(cam,sCount(sound),0,1) : entScore=entScore+(sound*10)
   pCountTim(cyc)=0
  EndIf
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc))
   declare=0
   For v=1 To no_plays
    If RefViable(v)=15 Then declare=1 : pRefVictim(cyc)=v
   Next
   If declare=1 Then newAnim=172 
  EndIf
  If pAnimTim(cyc)=>Int(90/pAnimSpeed#(cyc)) Then newAnim=0
  If newAnim=>0 
   If matchCountOuts=2 Then pCountTim(cyc)=300 Else pCountTim(cyc)=150
   corruption=0
   For v=1 To no_plays
    If RefViable(v)=5 Or RefViable(v)=15
     If charRelationship(pChar(cyc),pChar(v))>0 Then corruption=1
     If charRelationship(pChar(cyc),pChar(v))<0 And corruption=0 Then corruption=-1
    EndIf
   Next 
   If corruption>0 Then pCountTim(cyc)=pCountTim(cyc)*2
   If corruption<0 Then pCountTim(cyc)=pCountTim(cyc)/2
   ChangeAnim(cyc,newAnim)
  EndIf
  If game=1 And pAnim(cyc)=170 And tauntLearned(4)<1 Then tauntLearned(4)=1
  If game=1 And pAnim(cyc)=171 And tauntLearned(49)<1 Then tauntLearned(49)=1
 EndIf
 ;disqualify
 If pAnim(cyc)=172
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=2.5
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,172),5
   If pRefVictim(cyc)>0 Then InstantTurn(cyc,p(pRefVictim(cyc)))
  EndIf
  If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) And matchState=3 And pRefVictim(cyc)>0
   matchWinStyle=6
   For v=1 To no_wrestlers
    If v=pRefVictim(cyc) Or (RefViable(pRefVictim(cyc))=15 And pOutCount(v)=>8) Then DeclareFall(0,v) : pFoc(v)=cyc
   Next
  EndIf
  If pAnimTim(cyc)=>Int(100/pAnimSpeed#(cyc)) 
   If matchState=4 And matchWinner>0
    pFoc(cyc)=matchWinner
    ChangeAnim(cyc,176)
   Else
    ChangeAnim(cyc,0) 
   EndIf
  EndIf
  If game=1 And tauntLearned(5)<1 Then tauntLearned(5)=1
  entScore=entScore+1
 EndIf
 ;end round / break
 If pAnim(cyc)=173
  v=pFoc(cyc)
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=2.5
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,173),5
  EndIf
  If pAnimTim(cyc)=>Int(100/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,0)
  If game=1 And tauntLearned(6)<1 Then tauntLearned(6)=1
  entScore=entScore+1
 EndIf
 ;start / resume
 If pAnim(cyc)=174
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=2.5
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,174),5
   FaceCentre(cyc)
  EndIf
  If pAnimTim(cyc)=>Int(100/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,0) 
  If game=1 And tauntLearned(7)<1 Then tauntLearned(7)=1
  entScore=entScore+1
 EndIf
 ;weigh up scores
 If pAnim(cyc)=175
  If pAnimTim(cyc)=0 Then pAnimSpeed#(cyc)=0.5 : Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,175),10
  abort=0
  If pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And TauntAbort(cyc) Then abort=1
  If pAnimTim(cyc)=>Int(90/pAnimSpeed#(cyc)) Or abort=1 Then ChangeAnim(cyc,0) 
  If game=1 And tauntLearned(8)<1 Then tauntLearned(8)=1
 EndIf
 ;acknowledge winner
 If pAnim(cyc)=176
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=2.5
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,176),7
   InstantTurn(cyc,p(pFoc(cyc)))
  EndIf
  If pAnimTim(cyc)=>Int(100/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,0) 
  If game=1 And tauntLearned(3)<1 Then tauntLearned(3)=1
  entScore=entScore+1
 EndIf
 ;request space
 If pAnim(cyc)=177
  If pAnimTim(cyc)=0 Then pAnimSpeed#(cyc)=1.0 : Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,177),5 
  abort=0 
  If pAnimTim(cyc)>Int(30/pAnimSpeed#(cyc)) And TauntAbort(cyc) Then abort=1
  If pAnimTim(cyc)=>Int(90/pAnimSpeed#(cyc)) Or abort=1
   If pOldAnim(cyc)=48 Or pOldAnim(cyc)=49 Then anim=48 Else anim=0
   ChangeAnim(cyc,anim)
   pFoc(cyc)=pOldFoc(cyc)
  EndIf
  If game=1 And tauntLearned(20)<1 Then tauntLearned(20)=1
  entScore=entScore+1
 EndIf
 ;kneel down
 If pAnim(cyc)=178
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=Rnd#(1.0,1.5)+PercentOf#(1.0,pAgility(cyc))
   Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,178),5 
  EndIf 
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sFall,22050,0.5)
  If pAnimTim(cyc)>Int(35/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,179) 
 EndIf
 ;examination
 If pAnim(cyc)=179 Or pAnim(cyc)=183
  newAnim=-1
  v=pFoc(cyc)
  If pAnimTim(cyc)=0
   If pAnim(cyc)=179 Then pAnimSpeed#(cyc)=Rnd#(0.5,1.0) : Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,pAnim(cyc)),5
   If pAnim(cyc)=183 Then pAnimSpeed#(cyc)=Rnd#(0.25,0.5) : Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,pAnim(cyc)),10
  EndIf 
  declare=0
  duty=RefViable(v)
  If duty=1 And pAnimTim(cyc)>5
   If pAnim(v)<300 And pAnim(v)<>150 And pAnim(v)<>152 And pAnim(v)<>154
    pCountTim(cyc)=pCountTim(cyc)+2
   Else
    If pAnimTim(cyc)=55
     Pop(v,Rnd(2,8),1)
     entScore=entScore+charPopularity(pChar(cyc))
     entScore=entScore+charPopularity(pChar(v))
    EndIf
   EndIf
   If pControl(cyc)=0 And pCountTim(cyc)=0 Then newAnim=181
   If pControl(cyc)>0 And cTaunt(cyc)=1 Then newAnim=181
  EndIf
  If duty=>11 And duty=<14 Then matchWinStyle=duty-10 : declare=1
  ignore=0
  If charRelationship(pChar(cyc),pChar(v))<0 Then ignore=1
  If pPinner(v)>0 And charRelationship(pChar(cyc),pChar(pPinner(v)))>0 Then ignore=1
  If pGrappler(v)>0 And charRelationship(pChar(cyc),pChar(pGrappler(v)))>0 Then ignore=1
  If pControl(cyc)=0 And matchRules=>1 And duty>0 And ignore=0 And LegalMan(pRefAward(cyc)) And LegalMan(v) And pAnimTim(cyc)>5
   If TouchingRopes(pRefAward(cyc)) Or TouchingRopes(v)
    Pop(v,Rnd(2,9),0) : Pop(0,Rnd(2,9),0)
    ProduceSound(cam,sRopeBreak,0,1)
    pFoc(cyc)=pRefAward(cyc) : pWarned(pRefAward(cyc))=cyc
    pReaction(cyc)=173 : declare=-1
    entScore=entScore+charPopularity(pChar(cyc))
    entScore=entScore+charPopularity(pChar(v))
   EndIf
  EndIf
  If pAnimTim(cyc)>5
   If duty<>1 And duty<>2 And duty<>3 And duty<>4 And duty<>11 And duty<>12 And duty<>13 And duty<>14 Then declare=-1
   If (duty=1 Or duty=11) And pPinner(v)>0 And pRefAward(cyc)<>pPinner(v) Then declare=-1
   If (duty=2 Or duty=12) And pGrappler(v)>0 And pRefAward(cyc)<>pGrappler(v) Then declare=-1
   If pControl(cyc)>0 And (DirPressed(cyc) Or ActionPressed(cyc)) And cTaunt(cyc)=0 Then declare=-1   
   close=0
   If InProximity(cyc,v,40) Or (pGrappler(v)>0 And InProximity(cyc,pGrappler(v),40)) Or (pPinner(v)>0 And InProximity(cyc,pPinner(v),40)) Then close=1
   If close=0 Then declare=-1 : pChecked(v)=0
  EndIf
  If declare<>0
   If declare=-1 Then pRefAward(cyc)=0 : pRefVictim(cyc)=0
   newAnim=180
   If pAnim(cyc)=183
    If declare>0 Then newAnim=182 Else newAnim=0
   EndIf
  EndIf
  If newAnim=>0 Then ChangeAnim(cyc,newAnim)
 EndIf
 ;get up off knees
 If pAnim(cyc)=180
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=1.0+PercentOf#(1.0,pAgility(cyc))
   If pRefAward(cyc)>0 Or pRefVictim(cyc)>0 Then pAnimSpeed#(cyc)=pAnimSpeed#(cyc)+(pAnimSpeed#(cyc)/2)
   Animate p(cyc),1,-pAnimSpeed#(cyc),pSeq(cyc,178),5
  EndIf 
  If pAnimTim(cyc)>Int(35/pAnimSpeed#(cyc))
   If pRefAward(cyc)>0 Or pRefVictim(cyc)>0 Then anim=182 Else anim=0
   ChangeAnim(cyc,anim) 
  EndIf 
 EndIf
 ;administer count
 If pAnim(cyc)=181
  v=pRefVictim(cyc)
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=1.5
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,181),5
  EndIf
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,22050,0.25)
  If pAnimTim(cyc)=Int(27/pAnimSpeed#(cyc))
   ProduceSound(p(cyc),sBlock(Rnd(1,4)),22050,0.5)
   If pHolding(cyc)>0 Then ProduceSound(p(cyc),weapSound(weapType(pHolding(cyc))),22050,0.25)
   pStepTim#(cyc)=99
   If RefViable(v)=1
    pPinCount(v)=pPinCount(v)+1
    If pPinCount(v)>3 Then pPinCount(v)=3
    ProduceSound(cam,sCount(pPinCount(v)),0,1) 
    entScore=entScore+charPopularity(pChar(cyc))
    If pPinCount(v)=1 Then Pop(pRefAward(cyc),Rnd(2,7),0.5)
    If pPinCount(v)=2 Then Pop(pRefAward(cyc),Rnd(2,7),0)
    If pPinCount(v)=3 Then Pop(pRefAward(cyc),Rnd(2,7),1)
   EndIf
  EndIf
  abort=0
  If pAnimTim(cyc)<Int(27/pAnimSpeed#(cyc)) And RefViable(v)<>1 And RefViable(v)<>11 Then abort=1 
  If pAnimTim(cyc)>Int(30/pAnimSpeed#(cyc)) And RefViable(v)=11 Then abort=1
  If pAnimTim(cyc)>Int(40/pAnimSpeed#(cyc)) Then abort=1
  If abort=1 
   ChangeAnim(cyc,179)
   pCountTim(cyc)=Rnd(30,50)
   If charRelationship(pChar(cyc),pChar(v))>0 Or charRelationship(pChar(cyc),pChar(pPinner(v)))<0 Then pCountTim(cyc)=100
   If charRelationship(pChar(cyc),pChar(v))<0 Or charRelationship(pChar(cyc),pChar(pPinner(v)))>0 Then pCountTim(cyc)=25
  EndIf 
 EndIf
 ;declare fall
 If pAnim(cyc)=182
  If pAnimTim(cyc)=<0 Then Animate p(cyc),1,1.25,pSeq(cyc,182),5
  If pAnimTim(cyc)=70 Then Animate p(cyc),1,1.25,pSeq(cyc,180),5
  If pAnimTim(cyc)=15 Then DeclareFall(pRefAward(cyc),pRefVictim(cyc))
  If pAnimTim(cyc)>140 
   If matchState=4 And matchWinner>0
    pFoc(cyc)=matchWinner
    ChangeAnim(cyc,176)
   Else
    ChangeAnim(cyc,0) 
   EndIf
  EndIf
  If game=1 And tauntLearned(47)<1 Then tauntLearned(47)=1
  If game=1 And tauntLearned(48)<1 Then tauntLearned(48)=1
  entScore=entScore+1
 EndIf
 ;----------- 190-200: TAUNTS ----------
 ;taunt/celebration
 If pAnim(cyc)=>190 And pAnim(cyc)=<193
  newAnim=-1
  move=charTaunt(pChar(cyc),pAnim(cyc)-189)
  If pRole(cyc)=1 Then move=pRefTaunt(cyc,pAnim(cyc)-189)
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=tauntSpeed#(move) : transition=tauntTransition(move)
   If pOldAnim(cyc)=48 Or pOldAnim(cyc)=49 Then transition=transition+5
   Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,pAnim(cyc)),transition 
  EndIf
  If pAnimTim(cyc)=>5+tauntTransition(move)
   If pAnimTim(cyc)=5+tauntTransition(move) 
    If pAnim(cyc)=192 And pHeat(cyc)=>100 And pSpecial(cyc)=0
     If arenaCrowd=0 Then PlaySound sCrowd(0)
     If arenaCrowd>0 Then Pop(cyc,10,1) : Pop(cyc,Rnd(2,9),1)
     pSpecial(cyc)=pPopularity(cyc)*10
     If pSpecial(cyc)>0 Then entScore=entScore+(charPopularity(pChar(cyc))*2)
    Else 
     If LegalMan(cyc) Then Pop(cyc,Rnd(1,9),Rnd(0.25,0.75))
    EndIf
   EndIf
   TauntEffects(cyc,pFoc(cyc),5+tauntTransition(move))
  EndIf
  abort=0
  If pAnimTim(cyc)>10+tauntTransition(move) Or pAnimTim(cyc)>Int(tauntLength(move)/pAnimSpeed#(cyc))/2
   If TauntAbort(cyc) Then abort=1 
  EndIf
  If abort=1 
   If pOldAnim(cyc)=48 Or pOldAnim(cyc)=49 Then newAnim=48 Else newAnim=0
  EndIf
  If pAnimTim(cyc)>Int(tauntLength(move)/pAnimSpeed#(cyc))
   If cTaunt(cyc)=0 Or tauntLoop(move)=0
    If pOldAnim(cyc)=48 Or pOldAnim(cyc)=49 Then newAnim=48 Else newAnim=0
   EndIf
  EndIf
  If newAnim=>0 Then ChangeAnim(cyc,newAnim)
  If game=1 And tauntLearned(move)<1 Then tauntLearned(move)=1
 EndIf
 ;disappointment (get outta here)
 If pAnim(cyc)=194
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=2.0
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,pAnim(cyc)),10/pAnimSpeed#(cyc)
  EndIf
  If pAnimTim(cyc)=>Int(85/pAnimSpeed#(cyc)) Or (pAnimTim(cyc)>Int(20/pAnimSpeed#(cyc)) And TauntAbort(cyc))
   If pOldAnim(cyc)=48 Or pOldAnim(cyc)=49 Then anim=48 Else anim=0
   ChangeAnim(cyc,anim) 
  EndIf
  If game=1 And tauntLearned(2)<1 Then tauntLearned(2)=1
  If EntertainViable(cyc,0) Then entScore=entScore+1
 EndIf
 ;disappointment (shrug shoulders)
 If pAnim(cyc)=195
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=1.5
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,pAnim(cyc)),10/pAnimSpeed#(cyc)
  EndIf
  If pAnimTim(cyc)=>Int(80/pAnimSpeed#(cyc)) Or (pAnimTim(cyc)>Int(20/pAnimSpeed#(cyc)) And TauntAbort(cyc))
   If pOldAnim(cyc)=48 Or pOldAnim(cyc)=49 Then anim=48 Else anim=0
   ChangeAnim(cyc,anim) 
  EndIf 
  If game=1 And tauntLearned(17)<1 Then tauntLearned(17)=1
  If EntertainViable(cyc,0) Then entScore=entScore+1
 EndIf
 ;disappointment (hands on hips)
 If pAnim(cyc)=196
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=0.8
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,pAnim(cyc)),20/pAnimSpeed#(cyc)
  EndIf
  If pAnimTim(cyc)=>Int(110/pAnimSpeed#(cyc)) Or (pAnimTim(cyc)>Int(20/pAnimSpeed#(cyc)) And TauntAbort(cyc)) 
   If pOldAnim(cyc)=48 Or pOldAnim(cyc)=49 Then anim=48 Else anim=0
   ChangeAnim(cyc,anim) 
  EndIf
  If game=1 And tauntLearned(24)<1 Then tauntLearned(24)=1
  If EntertainViable(cyc,0) Then entScore=entScore+1
 EndIf
 ;microphone speech
 If pAnim(cyc)=197
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=1.0 : transition=10
   If pOldAnim(cyc)=48 Or pOldAnim(cyc)=49 Then transition=transition+5
   Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,197),transition
  EndIf
  If pAnimTim(cyc)=15 And LegalMan(cyc) Then Pop(cyc,Rnd(1,9),0)
  TauntEffects(cyc,pFoc(cyc),15)
  If (pAnimTim(cyc)=>Int(60/pAnimSpeed#(cyc)) And cTaunt(cyc)=0) Or (pAnimTim(cyc)>15 And TauntAbort(cyc))
   If pOldAnim(cyc)=48 Or pOldAnim(cyc)=49 Then anim=48 Else anim=0
   ChangeAnim(cyc,anim)
  EndIf
 EndIf
 ;cup celebration
 If pAnim(cyc)=198
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=1.0 : transition=5
   If pOldAnim(cyc)=48 Or pOldAnim(cyc)=49 Then transition=transition+5
   Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,198),transition
  EndIf
  If pAnimTim(cyc)=25 And LegalMan(cyc) Then Pop(cyc,Rnd(1,9),0)
  TauntEffects(cyc,pFoc(cyc),25)
  If (pAnimTim(cyc)=>Int(120/pAnimSpeed#(cyc)) And cTaunt(cyc)=0) Or (pAnimTim(cyc)>25 And TauntAbort(cyc))
   If pOldAnim(cyc)=48 Or pOldAnim(cyc)=49 Then anim=48 Else anim=0
   ChangeAnim(cyc,anim)
  EndIf
 EndIf
 ;change costume
 If pAnim(cyc)=199
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=1.5
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,217),10/pAnimSpeed#(cyc)
  EndIf
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc))
   ProduceSound(p(cyc),sSwing,22050,0.5)
   Pop(cyc,Rnd(1,9),0)
   If pCostume(cyc)=2
    pCostume(cyc)=1 : ApplyCostume(cyc)
   Else
    pCostume(cyc)=2 : ApplyCostume(cyc)
   EndIf
  EndIf
  If pAnimTim(cyc)>Int(120/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,0)
 EndIf
 ;---------- 200-230. ITEM INTERACTION ----------
 ItemAnims(cyc)
 ;---------- 230-240. CAGE INTERACTION ----------
 ;climb onto wall
 If pAnim(cyc)=230
  ResetExcuses(cyc,1)
  If pAnimTim(cyc)=0 Then pAnimSpeed#(cyc)=2.0 : Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,230),5
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,22050,0.5)
  If pAnimTim(cyc)>Int(20/pAnimSpeed#(cyc)) Then pY#(cyc)=pY#(cyc)+0.25 
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) 
   ProduceSound(p(cyc),sImpactMetal,20000,0.5)
   If pPlatform(cyc)=>91 And pPlatform(cyc)=<94 Then ShakeCage(pPlatform(cyc)-90,1.0)
   If pPlatform(cyc)=>95 And pPlatform(cyc)=<98 Then ShakeCage(pPlatform(cyc)-94,1.0)
  EndIf
  If pAnimTim(cyc)>Int(30/pAnimSpeed#(cyc))
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   ResetExcuses(cyc,0) : pExcusedWorld(cyc)=1
   ChangeAnim(cyc,231)
   If pScar(cyc,8)=5 And pScar(cyc,21)=5 Then pHP(cyc)=0
  EndIf
  DropWeapon(cyc,100)
 EndIf
 ;hanging on wall
 If pAnim(cyc)=231
  pExcusedWorld(cyc)=1
  If pAnimTim(cyc)=0 Then Animate p(cyc),1,Rnd#(0.25,0.5),pSeq(cyc,231),10
  If pOldAnim(cyc)=230 And pAnimTim(cyc)<10 Then pY#(cyc)=pY#(cyc)+0.25 
  If pAnimTim(cyc)>5 Or pOldAnim(cyc)<>230
   If DirPressed(cyc) Then ChangeAnim(cyc,232)
  EndIf 
  DropWeapon(cyc,1000)
  randy=Rnd(0,1000)
  If randy=<FindInjury(cyc) And matchState=3 Then pHP(cyc)=0
 EndIf
 ;climbing wall
 If pAnim(cyc)=232
  pExcusedWorld(cyc)=1
  If pPlatform(cyc)=>91 And pPlatform(cyc)=<94 Then pGround#(cyc)=wStage# Else pGround#(cyc)=wGround#
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=0.5+PercentOf#(1.5,pAgility(cyc))
   Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,232),15/pAnimSpeed#(cyc)
   pStepTim#(cyc)=0
  EndIf
  moveAngle#=RequestAngle#(cyc)
  If pControl(cyc)=0 And cUpTim(cyc)<5 And cDownTim(cyc)<5 And cLeftTim(cyc)<5 And cRightTim(cyc)<5
   PositionEntity dummy,pActX#(cyc),pY#(cyc),pActZ#(cyc)
   PointEntity p(cyc),dummy
   moveAngle#=CleanAngle#(EntityYaw(p(cyc)))
   RotateEntity p(cyc),0,pA#(cyc),0
  EndIf
  ascend#=0
  If DirPressed(cyc) 
   accessRange=90
   moveSpeed#=pAnimSpeed#(cyc)/10
   If pPlatform(cyc)=91 Or pPlatform(cyc)=97
    If SatisfiedAngle(RequestAngle#(cyc),0,accessRange) Then ascend#=pAnimSpeed#(cyc)/15
    If SatisfiedAngle(RequestAngle#(cyc),180,accessRange) Then ascend#=-(pAnimSpeed#(cyc)/10)
   EndIf
   If pPlatform(cyc)=92 Or pPlatform(cyc)=98
    If SatisfiedAngle(RequestAngle#(cyc),270,accessRange) Then ascend#=pAnimSpeed#(cyc)/15
    If SatisfiedAngle(RequestAngle#(cyc),90,accessRange) Then ascend#=-(pAnimSpeed#(cyc)/10)
   EndIf
   If pPlatform(cyc)=93 Or pPlatform(cyc)=95
    If SatisfiedAngle(RequestAngle#(cyc),180,accessRange) Then ascend#=pAnimSpeed#(cyc)/15
    If SatisfiedAngle(RequestAngle#(cyc),0,accessRange) Then ascend#=-(pAnimSpeed#(cyc)/10)
   EndIf 
   If pPlatform(cyc)=94 Or pPlatform(cyc)=96
    If SatisfiedAngle(RequestAngle#(cyc),90,accessRange) Then ascend#=pAnimSpeed#(cyc)/15
    If SatisfiedAngle(RequestAngle#(cyc),270,accessRange) Then ascend#=-(pAnimSpeed#(cyc)/10)
   EndIf
   If pAnimTim(cyc)=<Int(15/pAnimSpeed#(cyc)) Or (pAnimTim(cyc)=>Int(30/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(45/pAnimSpeed#(cyc)))
    moveSpeed#=moveSpeed#*2
    ascend#=ascend#*2
   EndIf
   If pPlatform(cyc)=>91 And pPlatform(cyc)=<94 And matchState=3 And matchCountOuts=3 And LegalMan(cyc) And ascend#>0 Then ascend#=ascend#/2 : entScore=entScore+1
   If LegalMan(cyc) Then entScore=entScore+1
   Advance(cyc,moveAngle#,moveSpeed#)
   pY#(cyc)=pY#(cyc)+ascend#
  EndIf
  If ascend#>0 And pY#(cyc)>53.5-PercentOf#(7.0,GetPercent#(charHeight(pChar(cyc)),24)) 
   pFriction(cyc)=pFriction(cyc)+2
   If pPlatform(cyc)=>91 And pPlatform(cyc)=<94 And matchState=3 And matchCountOuts=3 And LegalMan(cyc) Then pFriction(cyc)=pFriction(cyc)-Rnd(0,1)
   If pFriction(cyc)>20 Then ChangeAnim(cyc,233)
  EndIf
  If ascend#<0 And pY#(cyc)<pGround#(cyc)+2.0 Then ChangeAnim(cyc,234)
  If pAnimTim(cyc)>5 And DirPressed(cyc)=0 Then ChangeAnim(cyc,231)
  If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then pAnimTim(cyc)=0
  pStepTim#(cyc)=pStepTim#(cyc)+pAnimSpeed#(cyc)
  DropWeapon(cyc,250)
  randy=Rnd(0,1000)
  If randy=<FindInjury(cyc) And matchState=3 Then pHP(cyc)=0
 EndIf
 ;climb over wall
 If pAnim(cyc)=233
  ResetExcuses(cyc,1)
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=1.0+PercentOf#(1.0,pAgility(cyc))
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,233),5
  EndIf
  If matchState=3 And matchCountOuts=3 And LegalMan(cyc)
   If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then Pop(cyc,2,1) : Pop(cyc,Rnd(2,9),0) : entScore=entScore+(charPopularity(pChar(cyc))*4)
  EndIf
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(55/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(75/pAnimSpeed#(cyc))
   ProduceSound(p(cyc),sImpactMetal,20000,Rnd(0.1,0.5))
   If pPlatform(cyc)=>91 And pPlatform(cyc)=<94 Then ShakeCage(pPlatform(cyc)-90,0.5)
   If pPlatform(cyc)=>95 And pPlatform(cyc)=<98 Then ShakeCage(pPlatform(cyc)-94,0.5)
  EndIf
  If pAnimTim(cyc)>Int(105/pAnimSpeed#(cyc))
   ChangeAnim(cyc,231) : SharpTransition(cyc,231,1,180)
   If pPlatform(cyc)=>91 And pPlatform(cyc)=<94 Then pPlatform(cyc)=pPlatform(cyc)+4 Else pPlatform(cyc)=pPlatform(cyc)-4 
   ResetExcuses(cyc,0) : pExcusedWorld(cyc)=1
  Else
   factor#=GetPercent#(charHeight(pChar(cyc)),24)
   horizOffset#=PercentOf#(2/(105/pAnimSpeed#(cyc)),factor#)
   If pPlatform(cyc)=91 Or pPlatform(cyc)=97 Then pZ#(cyc)=pZ#(cyc)-horizOffset#
   If pPlatform(cyc)=92 Or pPlatform(cyc)=98 Then pX#(cyc)=pX#(cyc)-horizOffset#
   If pPlatform(cyc)=93 Or pPlatform(cyc)=95 Then pZ#(cyc)=pZ#(cyc)+horizOffset#
   If pPlatform(cyc)=94 Or pPlatform(cyc)=96 Then pX#(cyc)=pX#(cyc)+horizOffset#
  EndIf
  DropWeapon(cyc,100)
 EndIf
 ;climb off wall
 If pAnim(cyc)=234
  ResetExcuses(cyc,1)
  If pPlatform(cyc)=>91 And pPlatform(cyc)=<94 Then pGround#(cyc)=wStage# Else pGround#(cyc)=wGround#
  If pAnimTim(cyc)=0
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   pAnimSpeed#(cyc)=2.0
   Animate p(cyc),3,-pAnimSpeed#(cyc),pSeq(cyc,230),5
   If pPlatform(cyc)=>91 And pPlatform(cyc)=<94 Then ShakeCage(pPlatform(cyc)-90,1.0)
   If pPlatform(cyc)=>95 And pPlatform(cyc)=<98 Then ShakeCage(pPlatform(cyc)-94,1.0)
  EndIf
  If pY#(cyc)>pGround#(cyc) Then pY#(cyc)=pY#(cyc)-0.4
  If pAnimTim(cyc)<Int(20/pAnimSpeed#(cyc)) Then FindSmashes(cyc,pLimbY#(cyc,36),2)
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sThud,22050,0) : FindSmashes(cyc,pY#(cyc),2)
  If pAnimTim(cyc)>Int(30/pAnimSpeed#(cyc)) 
   ChangeAnim(cyc,0)
   If pPlatform(cyc)=>91 And pPlatform(cyc)=<94 Then pPlatform(cyc)=pPlatform(cyc)-90 Else pPlatform(cyc)=0
   pY#(cyc)=pGround#(cyc)
   ResetExcuses(cyc,0)
  Else
   horizOffset#=1.5/(30/pAnimSpeed#(cyc))
   If pPlatform(cyc)=>95 And pPlatform(cyc)=<98 Then Advance(cyc,pA#(cyc)+180,horizOffset#)
  EndIf
  DropWeapon(cyc,100)
 EndIf
 ;------ 300-500: STANDING MOVES ---------------
 MoveAnims(cyc)
 ;------ 500-600: HEAD MOVES ---------------
 HeadMoves(cyc)
 ;------ 600-700: LEG MOVES ---------------
 LegMoves(cyc)
 ;------ 700-800: BUCKLE MOVES ---------------
 BuckleMoves(cyc)
 ;------ 800-900: TEAM MOVES ---------------
 TeamMoves(cyc)
 ;INCREMENTATION
 pAnimTim(cyc)=pAnimTim(cyc)+1
End Function

;--------------------------------------------------------------
;////////////////// RELATED FUNCTIONS /////////////////////////
;--------------------------------------------------------------

;CHANGE ANIMATION
Function ChangeAnim(cyc,anim)
 pOldAnim(cyc)=pAnim(cyc)
 pAnim(cyc)=anim : pAnimStage(cyc)=1
 If pOldAnim(cyc)>anim Then pAnimTim(cyc)=-1 Else pAnimTim(cyc)=0
End Function

;IMMEDIATE TRANSITION
Function SharpTransition(cyc,anim,location,angle#)
 ;honour current co-ords
 If location=1
  pX#(cyc)=EntityX(FindChild(p(cyc),"Hips"),1)
  pZ#(cyc)=EntityZ(FindChild(p(cyc),"Hips"),1)
  If pExcusedWorld(cyc) Then pOldX#(cyc)=pX#(cyc) : pOldZ#(cyc)=pZ#(cyc)
 EndIf
 ;orientation
 If angle#<0 Then pA#(cyc)=EntityYaw(FindChild(p(cyc),"Hips"),1)
 If angle#=>0 Then pA#(cyc)=pA#(cyc)+angle# 
 pA#(cyc)=CleanAngle#(pA#(cyc)) 
 pTA#(cyc)=pA#(cyc)
 ;immediate transition 
 PositionEntity p(cyc),pX#(cyc),pY#(cyc),pZ#(cyc)
 RotateEntity p(cyc),0,pA#(cyc),0
 Animate p(cyc),1,1,pSeq(cyc,anim),0
End Function

;REVERSE TRANSITION
Function ReverseTransition(cyc,anim,speed#,offX#,endY#,offZ#)
 ;trigger reversed sequence
 Animate p(cyc),3,-speed#,pSeq(cyc,anim),0
 ;forecast destination
 PositionEntity dummy,pX#(cyc),0,pZ#(cyc)
 RotateEntity dummy,0,pA#(cyc),0
 MoveEntity dummy,offX#,0,offZ#
 pX#(cyc)=EntityX(dummy) : pZ#(cyc)=EntityZ(dummy)
 pOldX#(cyc)=pX#(cyc) : pOldZ#(cyc)=pZ#(cyc)
 pY#(cyc)=endY#
End Function

;INSTANT TURN (TO FACE ENTITY)
Function InstantTurn(cyc,entity)
 If entity>0
  PointEntity p(cyc),entity
  pA#(cyc)=CleanAngle#(EntityYaw(p(cyc)))
  RotateEntity p(cyc),0,pA#(cyc),0
 EndIf
End Function

;FACE CENTRE OF MULTIPLES
Function FaceCentre(cyc)
 IdentifyCentres(0)
 PositionEntity dummy,centreX#,pY#(cyc),centreZ#
 InstantTurn(cyc,dummy)
End Function

;PROJECTED ANIMATION?
Function ProjectedAnim(cyc) ;1=projected focus only, 2=projected collisions
 viable=0
 If pAnim(cyc)=>40 And pAnim(cyc)=<44 Then viable=1 ;ring climbing
 If (pAnim(cyc)=>45 And pAnim(cyc)=<47) Or (pAnim(cyc)=>50 And pAnim(cyc)=<52) Then viable=1 ;turnbuckle climbing
 If pAnim(cyc)=>140 And pAnim(cyc)=<142 Then viable=1 ;falling out of ring
 If pAnim(cyc)=233 Then viable=1 ;wall climbing
 If pAnim(cyc)=>309 And pAnim(cyc)=<499 Then viable=2 ;standing moves
 If pAnim(cyc)=>502 And pAnim(cyc)=<599 Then viable=2 ;head moves
 If pAnim(cyc)=>603 And pAnim(cyc)=<699 Then viable=2 ;leg moves
 If pAnim(cyc)=>710 And pAnim(cyc)=<799 Then viable=2 ;corner moves
 If pAnim(cyc)=>800 And pAnim(cyc)=<899 Then viable=2 ;team moves
 Return viable
End Function

;FIND IDEAL MOVEMENT ANIMATION
Function GetMovementAnim(cyc,request#)
 anim=1
 ;focused movements
 If pFoc(cyc)>0
  If SatisfiedAngle(request#,CleanAngle#(pA#(cyc)),45) Then anim=1 ;forwards
  If SatisfiedAngle(request#,CleanAngle#(pA#(cyc)+180),45) Then anim=2 ;backwards
  If SatisfiedAngle(request#,CleanAngle#(pA#(cyc)+90),45) Then anim=3 ;left
  If SatisfiedAngle(request#,CleanAngle#(pA#(cyc)+270),45) Then anim=4 ;right
 EndIf
 ;manual movements
 If pFoc(cyc)=0
  If SatisfiedAngle(request#,CleanAngle#(pA#(cyc)),60) Then anim=1 ;forwards
  If SatisfiedAngle(request#,CleanAngle#(pA#(cyc)+120),60) Then anim=3 ;left
  If SatisfiedAngle(request#,CleanAngle#(pA#(cyc)+240),60) Then anim=4 ;right
 EndIf
 Return anim
End Function

;ADVANCE
Function Advance(cyc,angle#,distance#)
 RotateEntity p(cyc),0,angle#,0
 MoveEntity p(cyc),0,0,distance#
 pX#(cyc)=EntityX#(p(cyc)) : pZ#(cyc)=EntityZ#(p(cyc))
 RotateEntity p(cyc),0,pA#(cyc),0
End Function

;SMOOTH HEAD TURNING
Function AdjustGaze(cyc)
  v=pFoc(cyc) : focus=0
  If HeadViable(cyc) And v>0 Then focus=1
  If focus>0
   pLookTX#(cyc)=pLimbX#(v,1) : pLookTY#(cyc)=pLimbY#(v,1) : pLookTZ#(cyc)=pLimbZ#(v,1) 
   If pFocTim(cyc)>0 Then speeder=10 Else speeder=30
   GetSmoothSpeeds(pLookX#(cyc),pLookTX#(cyc),pLookY#(cyc),pLookTY#(cyc),pLookZ#(cyc),pLookTZ#(cyc),speeder)
   If speedX#<1.0 And speedY#<1.0 And speedZ#<1.0
    speedX#=speedX#*2
    speedY#=speedY#*2
    speedZ#=speedZ#*2 
   EndIf
   If pLookX#(cyc)<pLookTX#(cyc) Then pLookX#(cyc)=pLookX#(cyc)+speedX#
   If pLookX#(cyc)>pLookTX#(cyc) Then pLookX#(cyc)=pLookX#(cyc)-speedX#
   If pLookY#(cyc)<pLookTY#(cyc) Then pLookY#(cyc)=pLookY#(cyc)+speedY#
   If pLookY#(cyc)>pLookTY#(cyc) Then pLookY#(cyc)=pLookY#(cyc)-speedY#
   If pLookZ#(cyc)<pLookTZ#(cyc) Then pLookZ#(cyc)=pLookZ#(cyc)+speedZ#
   If pLookZ#(cyc)>pLookTZ#(cyc) Then pLookZ#(cyc)=pLookZ#(cyc)-speedZ#
  Else 
   ;ProjectDummy(cyc,0,0,50)
   PositionEntity dummy,pLimbX#(cyc,1),pLimbY#(cyc,1),pLimbZ#(cyc,1)
   RotateEntity dummy,EntityPitch(pLimb(cyc,1),1),EntityYaw(pLimb(cyc,1),1),EntityRoll(pLimb(cyc,1),1)
   MoveEntity dummy,0,0,50
   pLookX#(cyc)=EntityX(dummy)
   pLookY#(cyc)=pLimbY#(cyc,1)
   pLookZ#(cyc)=EntityZ(dummy) 
  EndIf
End Function

;POINT HEAD
Function PointHead(cyc,entity)
 ;identify limbs involved
 limb=FindChild(p(cyc),"Head")
 source=FindChild(p(cyc),"Body")
 ;stabilize and point
 RotateEntity limb,EntityPitch(source),EntityYaw(source),EntityRoll(source) 
 PointEntity limb,entity
 ;X limitations
 If EntityPitch(limb)<EntityPitch(source)-60 Then RotateEntity limb,EntityPitch(source)-60,EntityYaw(limb),EntityRoll(limb)
 If EntityPitch(limb)>EntityPitch(source)+10 Then RotateEntity limb,EntityPitch(source)+10,EntityYaw(limb),EntityRoll(limb)
 ;Y limitations
 If EntityYaw(limb)<EntityYaw(source)-40 Then RotateEntity limb,EntityPitch(limb),EntityYaw(source)-40,EntityRoll(limb)
 If EntityYaw(limb)>EntityYaw(source)+40 Then RotateEntity limb,EntityPitch(limb),EntityYaw(source)+40,EntityRoll(limb) 
 ;Z limitations
 RotateEntity limb,EntityPitch(limb),EntityYaw(limb),0
 ;preserve long hair
 hair=FindChild(p(cyc),"H_Long")
 pitch=-EntityPitch(limb)
 If pitch<10 Then pitch=pitch+(GetDiff#(pitch,10)/4)
 RotateEntity hair,pitch,-(EntityYaw(limb)/2),0
End Function

;POINT EYES (at object)
Function LookAtObject(cyc,entity)
 For count=1 To 2
  ;identify limbs involved
  If count=1 Then limb=FindChild(p(cyc),"L_Eye")
  If count=2 Then limb=FindChild(p(cyc),"R_Eye") 
  ;stabilize and point
  PointEntity limb,entity
  ;X limitations
  If EntityPitch(limb)<-20 Then RotateEntity limb,-20,EntityYaw(limb),EntityRoll(limb)
  If EntityPitch(limb)>20 Then RotateEntity limb,20,EntityYaw(limb),EntityRoll(limb)
  ;Y limitations
  If EntityYaw(limb)<-20 Then RotateEntity limb,EntityPitch(limb),-20,EntityRoll(limb)
  If EntityYaw(limb)>20 Then RotateEntity limb,EntityPitch(limb),20,EntityRoll(limb) 
  ;Z limitations
  RotateEntity limb,EntityPitch(limb),EntityYaw(limb),0
 Next
End Function

;POINT EYES (at human)
Function LookAtPerson(cyc,v)
 For count=1 To 2
  ;identify eyes involved
  If count=1
   limb=FindChild(p(cyc),"L_Eye")
   PointEntity limb,FindChild(p(v),"R_Eye")
  EndIf
  If count=2
   limb=FindChild(p(cyc),"R_Eye") 
   PointEntity limb,FindChild(p(v),"L_Eye")
  EndIf
  ;X limitations
  If EntityPitch(limb)<-20 Then RotateEntity limb,-20,EntityYaw(limb),EntityRoll(limb)
  If EntityPitch(limb)>20 Then RotateEntity limb,20,EntityYaw(limb),EntityRoll(limb)
  ;Y limitations
  If EntityYaw(limb)<-20 Then RotateEntity limb,EntityPitch(limb),-20,EntityRoll(limb)
  If EntityYaw(limb)>20 Then RotateEntity limb,EntityPitch(limb),20,EntityRoll(limb) 
  ;Z limitations
  RotateEntity limb,EntityPitch(limb),EntityYaw(limb),0
 Next
End Function

;MANAGE EYES
Function ManageEyes(cyc)
 chance=200
 If screen=50 And GrimaceViable(cyc) Then chance=chance/4
 If screen=54 And charTrainCourse(editChar)>0 Then chance=chance/4
 randy=Rnd(0,chance)
 If randy=<1 And screen<>51 
  pSquintRevert(cyc)=Rnd(-30,PercentOf#(30,charEyeShape(char)))
  If pSquintRevert(cyc)<0 Then pSquintRevert(cyc)=0
 EndIf 
 If screen=51 Then pSquintRevert(cyc)=0
 If randy=2 Then pBlinkTim(cyc)=Rnd(10,20)
 If screen=50
  If pBlindTim(cyc)>0 And pBlinkTim(cyc)=0 Then pBlinkTim(cyc)=10
  If pDizzyTim(cyc)>0 And pBlinkTim(cyc)=0 Then pBlinkTim(cyc)=10
  If (pDT(cyc)>optLieHP And TurnViable(cyc)=0) Or pPinner(cyc)>0
   If pBlinkTim(cyc)<10 Then pBlinkTim(cyc)=10
  EndIf
 EndIf
 pBlinkTim(cyc)=pBlinkTim(cyc)-1
 If pBlinkTim(cyc)<0 Then pBlinkTim(cyc)=0
 If pBlinkTim(cyc)>5 Then pSquintTarget(cyc)=100 Else pSquintTarget(cyc)=pSquintRevert(cyc)
 If pBlinkTim(cyc)>0 Then pSquintSpeed(cyc)=15 Else pSquintSpeed(cyc)=3
 If pSquint(cyc)<pSquintTarget(cyc) Then pSquint(cyc)=pSquint(cyc)+pSquintSpeed(cyc)
 If pSquint(cyc)>pSquintTarget(cyc) Then pSquint(cyc)=pSquint(cyc)-pSquintSpeed(cyc) 
 char=pChar(cyc)
 For limb=45 To 46
  If limb=45 Then scaler#=0.909-PercentOf#(0.18,pSquint(cyc))
  If limb=45 And charEyeShape(char)=111 And pSquint(cyc)<50 Then scaler#=PercentOf#(0.909,96)
  If limb=46 Then scaler#=0.909-PercentOf#(0.26,pSquint(cyc))
  ScaleEntity pLimb(cyc,limb),scaler#,scaler#,scaler# 
  If charEyeShape(char)=<100
   ScaleEntity pLimb(cyc,limb),scaler#,PercentOf#(scaler#,charEyeShape(char)),scaler# 
  EndIf
  If charEyeShape(char)>100 And charEyeShape(char)=<110
   ScaleEntity pLimb(cyc,limb),PercentOf#(scaler#,charEyeShape(char)),PercentOf#(scaler#,charEyeShape(char)),PercentOf#(scaler#,charEyeShape(char))
  EndIf
 Next
End Function

;HEAD VIABLE?
Function HeadViable(cyc)
 viable=0
 If pFoc(cyc)>0 And pDizzyTim(cyc)=0 And pBlindTim(cyc)=0 And pMomentum(cyc)=0
  ;standard animations
  If pAnim(cyc)<40 Then viable=1 ;movement
  If pAnim(cyc)=48 Or pAnim(cyc)=49 Or pAnim(cyc)=57 Or pAnim(cyc)=58 Then viable=1 ;turnbuckle perch
  If pAnim(cyc)=>154 And pAnim(cyc)=<158 And pDT(cyc)=<0 Then viable=1 ;lucid while down
  If pAnim(cyc)=170 Or pAnim(cyc)=172 Or pAnim(cyc)=179 Or pAnim(cyc)=183 Then viable=1 ;refereeing
  If pAnim(cyc)=201 Or pAnim(cyc)=202 Then viable=1 ;carrying item
  If pAnim(cyc)=231 Or pAnim(cyc)=232 Then viable=1 ;cage climbing
  If pAnim(cyc)=>701 And pAnim(cyc)=<702 And pDT(cyc)=<0 Then viable=1 ;lucid while slumped
  ;override for speech
  If (pAnim(cyc)=0 And (pState(cyc)=7 Or pState(cyc)=197)) Or pAnim(cyc)=197
   If pY#(pFoc(cyc))<pY#(cyc)-10 Or pY#(pFoc(cyc))>pY#(cyc)+10 Or InLine(cyc,p(pFoc(cyc)),45)=0 Then viable=1 Else viable=0 
  EndIf
 EndIf
 Return viable
End Function

;USE PENSIVE STANCE?
Function FightViable(cyc,v)
 viable=0 
 If v>0 And LegalMan(cyc) And LegalMan(v) And pTeam(cyc)<>pTeam(v) And matchState=3 
  viable=1
  If InProximity(cyc,v,50)=0 Then viable=0 ;not close
  If InsideRing(pX#(cyc),pZ#(cyc),-15) And InsideRing(pX#(v),pZ#(v),0)=0 Then viable=0 ;victim not in ring
  If InsideRing(pX#(v),pZ#(v),-15) And InsideRing(pX#(cyc),pZ#(cyc),0)=0 Then viable=0 ;aggressor not in ring
 EndIf
 Return viable
End Function

;VIABLE TO CHANGE FOC?
Function SwitchViable(cyc)
 viable=0
 If pAnim(cyc)<30 And pDizzyTim(cyc)=0 And pBlindTim(cyc)=0 Then viable=1 ;moving
 If pAnim(cyc)=48 Or pAnim(cyc)=49 Then viable=1 ;turnbuckle perch
 If pAnim(cyc)=80 Or pAnim(cyc)=82 Or pAnim(cyc)=83 Or pAnim(cyc)=85 Then viable=1 ;blocking
 If pAnim(cyc)=>154 And pAnim(cyc)=<158 And pDT(cyc)=0 Then viable=1 ;lucid while grounded
 If pAnim(cyc)=201 Or pAnim(cyc)=202 Then viable=1 ;carrying
 If pAnim(cyc)=231 Or pAnim(cyc)=232 Then viable=1 ;climbing
 If pAnim(cyc)=701 And pDT(cyc)=0 Then viable=1 ;lucid while slumped
 Return viable
End Function

;GRIMACE VIABLE?
Function GrimaceViable(cyc)
 viable=1
 ;positive variations
 If pAnim(cyc)=>161 And pAnim(cyc)=<163
  If pPinCount(pPinning(cyc))=3 Or (matchState=4 And matchWinner=cyc) Then viable=-1 ;successful pin
 EndIf
 If pAnim(cyc)=>170 And pAnim(cyc)=<189 Then viable=-1 ;refereeing
 If pAnim(cyc)=193 Or pAnim(cyc)=198 Or (pAnim(cyc)=>216 And pAnim(cyc)=<217) Then viable=-1 ;celebrations
 ;exceptions
 If pAnim(cyc)<30 Then viable=0 ;movement
 If pAnim(cyc)=>40 And pAnim(cyc)=<59 Then viable=0 ;climbing
 If pAnim(cyc)=80 Or pAnim(cyc)=82 Or pAnim(cyc)=83 Or pAnim(cyc)=85 Then viable=0 ;blocking
 If pAnim(cyc)=150 Or pAnim(cyc)=152 Or pAnim(cyc)=154 Or pAnim(cyc)=156 Then viable=0 ;lying 
 If pAnim(cyc)=201 Or pAnim(cyc)=202 Then viable=0 ;carrying item
 If pAnim(cyc)=231 Then viable=0 ;hanging
 If pAnim(cyc)=701 Then viable=0 ;buckle slump
 If pPinner(cyc)>0 Then viable=0 ;pin victim
 Return viable
End Function

;TURN VIABLE
Function TurnViable(cyc) ;1=swift, 2=slow
 viable=1
 ;slower variations
 ;If pAnim(cyc)=48 Or pAnim(cyc)=49 Then viable=2 ;turnbuckle perch
 ;If pAnim(cyc)=64 Or pAnim(cyc)=74 Then viable=2 ;flying attack
 ;If pAnim(cyc)=65 Then viable=2 ;crouching punch
 If pAnim(cyc)=69 Then viable=2 ;hind kick
 If pAnim(cyc)=81 Or pAnim(cyc)=84 Then viable=2 ;block reactions
 If pAnim(cyc)=>90 And pAnim(cyc)=<95 Then viable=2 ;upper hurt
 If pAnim(cyc)=>100 And pAnim(cyc)=<105 Then viable=2 ;lower hurt 
 If pAnim(cyc)=151 Or pAnim(cyc)=153 Or pAnim(cyc)=155 Then viable=2 ;rising off ground 
 If pAnim(cyc)=158 Then viable=2 ;crawling
 If pAnim(cyc)=183 Then viable=2 ;standing ref examination
 If pAnim(cyc)=202 Then viable=2 ;dragging item
 ;exceptions
 If pAnim(cyc)=>40 And pAnim(cyc)=<44 Then viable=0 ;ring climbing
 If (pAnim(cyc)=>45 And pAnim(cyc)=<47) Or (pAnim(cyc)=>50 And pAnim(cyc)=<52) Then viable=0 ;turnbuckle climbing
 If pAnim(cyc)=>53 And pAnim(cyc)=<56 Then viable=0 ;platform climbing
 If pAnim(cyc)=57 Or pAnim(cyc)=58 Then viable=0 ;jump positioning
 If pAnim(cyc)=68 And pAnimTim(cyc)>85/pAnimSpeed#(cyc) Then viable=0 ;baseball slide
 If pAnim(cyc)=72 And crushName$(3,charCrush(pChar(cyc),3))="Moonsault"
  If InProximity(cyc,pFoc(cyc),10) Or pAnimTim(cyc)=>Int(crushFall(3,charCrush(pChar(cyc),3))/pAnimSpeed#(cyc)) Then viable=0 ;novelty standing crush
 EndIf
 If pAnim(cyc)=73 And crushName$(4,charCrush(pChar(cyc),4))="Moonsault"
  If InProximity(cyc,pFoc(cyc),10) Or pAnimTim(cyc)=>Int(crushFall(4,charCrush(pChar(cyc),4))/pAnimSpeed#(cyc)) Then viable=0 ;novelty running crush
 EndIf
 If pAnim(cyc)=74 And pY#(cyc)=<pGround#(cyc) Then viable=0 ;flying crush
 If pAnim(cyc)=99 Or pAnim(cyc)=109 Then viable=0 ;lying hurt
 If pAnim(cyc)=>110 And pAnim(cyc)=<119 And pAnim(cyc)<>177 Then viable=0 ;ailments
 If pAnim(cyc)=>120 And pAnim(cyc)=<149 Then viable=0 ;falling 
 If pAnim(cyc)=150 Or pAnim(cyc)=152 Or pAnim(cyc)=154 Or pAnim(cyc)=156 Or pAnim(cyc)=159 Then viable=0 ;lying 
 If pAnim(cyc)=>160 And pAnim(cyc)=<163 Then viable=0 ;pinning
 If pAnim(cyc)=>170 And pAnim(cyc)=<199
  If pAnim(cyc)<>177 And pAnim(cyc)<>178 And pAnim(cyc)<>179 And pAnim(cyc)<>183 Then viable=0 ;gestures
 EndIf
 If pAnim(cyc)=>200 And pAnim(cyc)=<219 And pAnim(cyc)<>202
  If pAnim(cyc)<>213 Or pRole(cyc)=1 Or pFoc(cyc)=0 Then viable=0 ;item interaction
 EndIf
 If pAnim(cyc)=202 And cRun(cyc)=1 Then viable=0 ;static item drag 
 If pAnim(cyc)=>230 And pAnim(cyc)=<239 Then viable=0 ;cage interaction
 If pAnim(cyc)=>301
  If pAnim(cyc)<>303 And pAnim(cyc)<>500 And pAnim(cyc)<>501 Then viable=0 ;grappling
 EndIf
 Return viable
End Function

;ATTACK VIABLE?
Function AttackViable(cyc) ;1=standing, 2=kneeling, 3=crawling, 4=lying
 ;standing by default
 viable=1
 ;kneeling
 If pAnim(cyc)=65 Then viable=2 ;crouching punch
 If pAnim(cyc)=155 And pAnimTim(cyc)>Int(10/pAnimSpeed#(cyc)) Then viable=2 ;rising onto one knee
 If pAnim(cyc)=156 Then viable=2 ;kneeling
 If pAnim(cyc)=157 And pAnimTim(cyc)<Int(20/pAnimSpeed#(cyc)) Then viable=2 ;rising onto feet
 If (pAnim(cyc)=160 And pAnimTim(cyc)=<Int(15/pAnimSpeed#(cyc))) Or pAnim(cyc)=162 Or pAnim(cyc)=163 Then viable=2 ;initiating pin
 If pAnim(cyc)=178 Or pAnim(cyc)=180 Then viable=2 ;initiating examination
 If pAnim(cyc)=501 Then viable=2 ;pursuing ground grapple
 If (pAnim(cyc)=>509 And pAnim(cyc)=<599) Or (pAnim(cyc)=>610 And pAnim(cyc)=<699)
  If pStretched(pGrappling(cyc))>0 Then viable=2 ;ground submission hold
 EndIf
 ;crawling
 If pAnim(cyc)=68 Or pAnim(cyc)=69 Then viable=3 
 If pAnim(cyc)=137 And pAnimTim(cyc)>Int(30/pAnimSpeed#(cyc)) Then viable=3 ;breaking down to hands & knees
 If pAnim(cyc)=138 And pAnimTim(cyc)=<Int(20/pAnimSpeed#(cyc)) Then viable=3 ;breaking down onto front
 If pAnim(cyc)=139 And pAnimTim(cyc)=<Int(20/pAnimSpeed#(cyc)) Then viable=3 ;breaking down onto back
 If pAnim(cyc)=151 And pAnimTim(cyc)=>Int(40/pAnimSpeed#(cyc)) Then viable=3 ;rising onto hands & knees 
 If pAnim(cyc)=>153 And pAnim(cyc)=<154 Then viable=3 ;hands & knees
 If pAnim(cyc)=158 Then viable=3 ;crawling
 If (pAnim(cyc)=160 And pAnimTim(cyc)>Int(15/pAnimSpeed#(cyc))) Or pAnim(cyc)=161 Then viable=3 ;pinning
 If pAnim(cyc)=179 Or pAnim(cyc)=181 Then viable=3 ;examining
 If (pAnim(cyc)=>309 And pAnim(cyc)=<499) Or (pAnim(cyc)=>509 And pAnim(cyc)=<599) Or (pAnim(cyc)=>610 And pAnim(cyc)=<699)
  If pPinning(cyc)>0 Or pPinner(cyc)>0 Then viable=3 ;move pins
 EndIf
 ;lying
 If pAnim(cyc)=99 Or pAnim(cyc)=109 Then viable=4 ;lying hurt
 If pAnim(cyc)=138 And pAnimTim(cyc)>Int(20/pAnimSpeed#(cyc)) Then viable=4 ;breaking down onto front
 If pAnim(cyc)=139 And pAnimTim(cyc)>Int(20/pAnimSpeed#(cyc)) Then viable=4 ;breaking down onto back
 If pAnim(cyc)=150 Or pAnim(cyc)=152 Or pAnim(cyc)=159 Then viable=4 ;lying
 If pAnim(cyc)=151 And pAnimTim(cyc)<Int(40/pAnimSpeed#(cyc)) Then viable=4 ;rising onto hands & knees 
 If pAnim(cyc)=502 Or (pAnim(cyc)=509 And pStretched(cyc)>0) Then viable=4 ;ground grapple victim
 ;exceptions
 If pAnim(cyc)=>40 And pAnim(cyc)=<43 Then viable=0 ;ring climbing
 If (pAnim(cyc)=>45 And pAnim(cyc)=<47) Or (pAnim(cyc)=>50 And pAnim(cyc)=<52) Then viable=0 ;turnbuckle climbing 
 If pAnim(cyc)=>53 And pAnim(cyc)=<56 Then viable=0 ;platform climbing 
 If pAnim(cyc)=64 Or pAnim(cyc)=74 Or pAnim(cyc)=54 Or pAnim(cyc)=56
  If pY#(cyc)>pGround#(cyc)+10 Or pAnim(cyc)=74 Then viable=0 ;flying/climbing down
 EndIf
 If pAnim(cyc)=64 And attackFall(5,charAttack(pChar(cyc),5))>0 Then viable=0 ;lying flying attack
 If pAnim(cyc)=>120 And pAnim(cyc)=<129 Then viable=0 ;basic falling
 If pAnim(cyc)=137 And pAnimTim(cyc)=<Int(30/pAnimSpeed#(cyc)) Then viable=0 ;breaking down to hands & knees 
 If pAnim(cyc)=>140 And pAnim(cyc)=<149 Then viable=0 ;novelty falling
 If pAnim(cyc)=>230 And pAnim(cyc)=<239 And pAnim(cyc)<>231 And pAnim(cyc)<>232 Then viable=0 ;cage interaction
 If (pAnim(cyc)=>309 And pAnim(cyc)=<499) Or (pAnim(cyc)=>509 And pAnim(cyc)=<599) Or (pAnim(cyc)=>610 And pAnim(cyc)=<699) Or (pAnim(cyc)=>710 And pAnim(cyc)=<799) Or (pAnim(cyc)=>800 And pAnim(cyc)=<899)
  If pStretched(cyc)=0 And pStretched(pGrappling(cyc))=0 And pPinning(cyc)=0 And pPinner(cyc)=0 Then viable=0 ;moves 
 EndIf
 If pAnim(cyc)=700 Then viable=0 ;buckle slump
 ;mid-attack immunity
 If pAnim(cyc)=>60 And pAnim(cyc)=<63
  style=pAnim(cyc)-59 : move=charAttack(pChar(cyc),style)
  If attackFall(style,move)>0
   If pAnimTim(cyc)>Int(attackTransition(style,move)/pAnimSpeed#(cyc)) And pAnimTim(cyc)<GetCentre#(Int(attackFall(style,move)/pAnimSpeed#(cyc)),Int(attackLength(style,move)/pAnimSpeed#(cyc))) Then viable=0
   If pAnimTim(cyc)>GetCentre#(Int(attackFall(style,move)/pAnimSpeed#(cyc)),Int(attackLength(style,move)/pAnimSpeed#(cyc))) Then viable=2
  EndIf
 EndIf
 If pAnim(cyc)=>70 And pAnim(cyc)=<73
  style=pAnim(cyc)-69 : move=charCrush(pChar(cyc),style)
  If crushFall(style,move)>0
   If pAnimTim(cyc)>Int(crushTransition(style,move)/pAnimSpeed#(cyc)) And pAnimTim(cyc)<GetCentre#(Int(crushFall(style,move)/pAnimSpeed#(cyc)),Int(crushLength(style,move)/pAnimSpeed#(cyc))) Then viable=0
   If pAnimTim(cyc)>GetCentre#(Int(crushFall(style,move)/pAnimSpeed#(cyc)),Int(crushLength(style,move)/pAnimSpeed#(cyc))) Then viable=2
  EndIf
 EndIf
 ;important refereeing
 If pAnim(cyc)=180 And (pRefAward(cyc)>0 Or pRefVictim(cyc)>0) Then viable=0
 If pAnim(cyc)=172 Or pAnim(cyc)=182 Then viable=0
 ;unavailable
 If pImmunity(cyc)>0 Then viable=0 
 If (pOutTim(cyc)=0 And pRole(cyc)<>3) Or pHidden(cyc)>0 Then viable=0
 Return viable
End Function

;COLLAPSE VIABLE?
Function CollapseViable(cyc)
 viable=1
 If pAnim(cyc)=>40 And pAnim(cyc)=<44 Then viable=0 ;ring climbing
 If (pAnim(cyc)=>45 And pAnim(cyc)=<47) Or (pAnim(cyc)=>50 And pAnim(cyc)=<52) Then viable=0 ;turnbuckle climbing 
 If pAnim(cyc)=>53 And pAnim(cyc)=<56 Then viable=0 ;platform climbing  
 If pAnim(cyc)=>60 And pAnim(cyc)=<63 ;diving attacks
  style=pAnim(cyc)-59 : move=charAttack(pChar(cyc),style)
  If attackFall(style,move)>0 And pAnimTim(cyc)<GetCentre#(attackFall(style,move)/pAnimSpeed#(cyc),attackLength(style,move)/pAnimSpeed#(cyc)) Then viable=0
 EndIf
 If pAnim(cyc)=>70 And pAnim(cyc)=<73 ;diving crushes
  style=pAnim(cyc)-69 : move=charCrush(pChar(cyc),style)
  If crushFall(style,move)>0 And pAnimTim(cyc)<GetCentre#(crushFall(style,move)/pAnimSpeed#(cyc),crushLength(style,move)/pAnimSpeed#(cyc)) Then viable=0
 EndIf
 If pAnim(cyc)=64 Or pAnim(cyc)=74 Then viable=0 ;flying attacks
 If pAnim(cyc)=99 Or pAnim(cyc)=109 Then viable=0 ;lying hurt
 If pAnim(cyc)=>120 And pAnim(cyc)=<149 Then viable=0 ;falling 
 If (pAnim(cyc)=>150 And pAnim(cyc)=<154) Or pAnim(cyc)=158 Or pAnim(cyc)=159 Then viable=0 ;lying 
 If pAnim(cyc)=155 And pAnimTim(cyc)<Int(20/pAnimSpeed#(cyc)) Then viable=0 ;still on hands & knees
 If pAnim(cyc)=>160 And pAnim(cyc)=<161 Then viable=0 ;pinning
 If pAnim(cyc)=>162 And pAnim(cyc)=<163 And pAnimTim(cyc)<Int(10/pAnimSpeed#(cyc)) Then viable=0 ;pin get-up's
 If pAnim(cyc)=179 Or pAnim(cyc)=181 Then viable=0 ;examining
 If pAnim(cyc)=>230 And pAnim(cyc)=<239 And pAnim(cyc)<>231 And pAnim(cyc)<>232 Then viable=0 ;cage interaction 
 If pAnim(cyc)=>301 And pAnim(cyc)=<499 Then viable=0 ;standing grapples
 If pAnim(cyc)=>501 And pAnim(cyc)=<599 Then viable=0 ;head grappling
 If pAnim(cyc)=>603 And pAnim(cyc)=<699 Then viable=0 ;leg grappling 
 If pAnim(cyc)=700 Then viable=0 ;buckle slump
 If pAnim(cyc)=>710 And pAnim(cyc)=<799 Then viable=0 ;buckle grapples
 If pAnim(cyc)=>800 And pAnim(cyc)=<899 Then viable=0 ;team moves
 Return viable
End Function

;FIND POTENTIAL FALL OUT'S
Function SpillViable(cyc,angle#,range#)
 viable=0
 ;1-4: falls out of ring
 If pPlatform(cyc)=0 And InsideRing(pX#(cyc),pZ#(cyc),-15)
  If pZ#(cyc)=>blockZ1#(1)-range# And SatisfiedAngle(pA#(cyc),180,30) Then viable=1 ;north
  If pX#(cyc)=>blockX1#(2)-range# And SatisfiedAngle(pA#(cyc),90,30) Then viable=2 ;east
  If pZ#(cyc)=<blockZ2#(3)+range# And SatisfiedAngle(pA#(cyc),0,30) Then viable=3 ;south
  If pX#(cyc)=<blockX2#(4)+range# And SatisfiedAngle(pA#(cyc),270,30) Then viable=4 ;west
 EndIf
 ;5-8: slump into buckles
 If viable=0 And InsideRing(pX#(cyc),pZ#(cyc),-15) And pPlatform(cyc)=0 And pBuckleTim(cyc)=0
  PositionEntity dummy,pX#(cyc),pY#(cyc),pZ#(cyc)
  RotateEntity dummy,0,angle#,0
  MoveEntity dummy,0,0,5
  checkX#=EntityX(dummy) : checkZ#=EntityZ(dummy)
  If FindBuckleSlumps(cyc,checkX#,checkZ#,5)>0 Then viable=4+FindBuckleSlumps(cyc,checkX#,checkZ#,5)
 EndIf
 Return viable
End Function

;TURN FALLS INTO SLUMPS/FALL-OUT'S
Function RiskSpills(cyc,angle#)
 ;spill out of ring
 spill=SpillViable(cyc,angle#,2)
 If spill=>1 And spill=<4
  If spill=1 Then pA#(cyc)=180
  If spill=2 Then pA#(cyc)=90
  If spill=3 Then pA#(cyc)=0
  If spill=4 Then pA#(cyc)=270
  randy=Rnd(0,2)
  If matchCountOuts=3 Then randy=Rnd(-2,2)
  If randy=<1 Or matchCage>0 Then ChangeAnim(cyc,140)
  If randy=2 And matchCage=0 Then ChangeAnim(cyc,141)
 EndIf
 ;slump into buckles
 If spill=>5 And spill=<8
  If spill=5 Then pA#(cyc)=45
  If spill=6 Then pA#(cyc)=315
  If spill=7 Then pA#(cyc)=225
  If spill=8 Then pA#(cyc)=135
  ChangeAnim(cyc,700)
 EndIf
End Function

;RISK CLIMBING FALL
Function RiskFall(cyc,chance)
 randy=Rnd(0,pAgility(cyc)+pSkill(cyc))
 If randy=<chance And pSpecial(cyc)=0 Then pHP(cyc)=0 : pHurtA#(cyc)=Rnd(0,360)
End Function

;TRIGGER FALL
Function TriggerFall(cyc)
 ;shoot entertainment
 If matchShoot>0 Or matchKOs>0
  If AttackViable(cyc)=1 And EntertainViable(cyc,0) Then entScore=entScore+charPopularity(pChar(cyc))
 EndIf
 ;find suitable fall
 Repeat
  satisfied=1
  If SatisfiedAngle(pA#(cyc),pHurtA#(cyc),90) 
   anim=Rnd(125,130)
   If anim<126 Then anim=123
   If anim>129 Then anim=129
  Else
   anim=Rnd(119,128)
   If anim<120 Then anim=120
  EndIf
  If anim=120 Or anim=121 Or anim=122 Or anim=124 Or anim=125
   If BlockClear(cyc,pA#(cyc),-30)=0 And SpillViable(cyc,pA#(cyc)+180,3)=0 Then satisfied=0   
  EndIf
  If anim=127 Or anim=128 Or anim=129
   If BlockClear(cyc,pA#(cyc),30)=0 Then satisfied=0   
  EndIf
  randy=Rnd(0,1)
  If randy=0 And (anim=127 Or anim=128) Then satisfied=0
  randy=Rnd(0,1)
  If randy=0 And (TranslateWeight(pChar(cyc))=>300 Or charHeight(pChar(cyc))=>22)
   If anim=120 Or anim=121 Or anim=124 Or anim=129 Then satisfied=0
  EndIf
 Until satisfied=1
 ;apron falls
 If pPlatform(cyc)=>1 And pPlatform(cyc)=<4 And matchCage=0
  If pPlatform(cyc)=1 Then pA#(cyc)=180
  If pPlatform(cyc)=2 Then pA#(cyc)=90
  If pPlatform(cyc)=3 Then pA#(cyc)=0
  If pPlatform(cyc)=4 Then pA#(cyc)=270   
  anim=142
 EndIf
 ;platform falls
 If pPlatform(cyc)=>5
  If pPlatform(cyc)=>91 And pPlatform(cyc)=<98 And matchState=3 And matchCountOuts=3 Then entScore=entScore+charPopularity(pChar(cyc))
  If SatisfiedAngle(pA#(cyc),pHurtA#(cyc),90) Then anim=144 Else anim=143
  If pPlatform(cyc)=>91 And pPlatform(cyc)=<98 Then anim=Rnd(143,144)
  range#=LeapHeight#(cyc,pY#(cyc)-FindGround#(pX#(cyc),pZ#(cyc)))
  If range#<20 Then range#=20
  While (anim=143 And FlightClear(cyc,pA#(cyc)+180,range#)=0) Or (anim=144 And FlightClear(cyc,pA#(cyc),range#)=0)
   pA#(cyc)=pA#(cyc)+5
   If pA#(cyc)>360 Then pA#(cyc)=0
   If KeyDown(207) Then Exit
  Wend
 EndIf
 ;execute animation
 ChangeAnim(cyc,anim)
 ;status effects
 pHP(cyc)=PercentOf#(pHealth(cyc)/10,50)
 If pHP(cyc)<100 Then pHP(cyc)=100
 If pDT(cyc)=0 Then pDT(cyc)=GetDT(cyc,100)
 If pDizzyTim(cyc)=0 Then pDizzyTim(cyc)=Rnd(-pDT(cyc),pDT(cyc))
 If pBlindTim(cyc)=0 Then pBlindTim(cyc)=Rnd(-pDT(cyc),pDT(cyc))
End Function

;GET RANDOM FALL ANGLE
Function RandomFall#(favour#)
 randy=Rnd(0,1)
 If randy=0 Then angle#=Rnd(0,360) Else angle#=CleanAngle#(favour#+Rnd(-90,90))
 Return angle#
End Function

;GENERIC FALL EFFECTS
Function FallEffects(cyc,style) ;1=minor, 2=major
 ;minor fall
 If style=1
  Pop(0,Rnd(2,9),0.5)
  ProduceSound(pLimb(cyc,36),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  ProduceSound(pLimb(cyc,36),sFall,22050,0)
  ProduceSound(pLimb(cyc,36),sThud,22050,0.5)
  ScarArea(cyc,0,0,0,50)
  RiskInjury(cyc,Rnd(0,5),10)
  pHealth(cyc)=pHealth(cyc)-50
  pHeat(cyc)=pHeat(cyc)-1
  If EntertainViable(cyc,0) Then entScore=entScore+50
  If InsideRing(RealX#(cyc),RealZ#(cyc),-5) Then ShakeRing(0,0.5)
 EndIf
 ;major fall
 If style=2
  Pop(0,Rnd(2,9),1)
  ProduceSound(pLimb(cyc,36),sPain(Rnd(1,8)),GetVoice(cyc),1)
  ProduceSound(pLimb(cyc,36),sBlock(7),20000,0)
  ProduceSound(pLimb(cyc,36),sFall,22050,1)
  ProduceSound(pLimb(cyc,36),sThud,22050,1)
  ScarArea(cyc,0,0,0,25)
  RiskInjury(cyc,Rnd(0,5),1)
  pHealth(cyc)=pHealth(cyc)-500
  pDT(cyc)=pDT(cyc)+(pDT(cyc)/4)
  pHeat(cyc)=pHeat(cyc)-5
  If EntertainViable(cyc,0) Then entScore=entScore+250
  If InsideRing(RealX#(cyc),RealZ#(cyc),-5) Then ShakeRing(0,2.0)
 EndIf
End Function

;FIND CRAWL OVERLAPS
Function CrawlOverlap(cyc)
 value=0
 ;position probe 
 PositionEntity dummy,pX#(cyc),pY#(cyc),pZ#(cyc)
 RotateEntity dummy,0,pA#(cyc),0
 MoveEntity dummy,0,0,10
 checkX#=EntityX(dummy) : checkZ#=EntityZ(dummy)
 ;find matches
 For v=1 To no_plays
  viable=0
  If AttackViable(v)=>1 And AttackViable(v)=<2
   If pAnim(v)<>64 And pAnim(v)<>70 And pAnim(v)<>71 And pAnim(v)<>72 And pAnim(v)<>73 And pAnim(v)<>74 And pAnim(v)<>500 And pAnim(v)<>501 Then viable=1
  EndIf
  If AttackViable(v)=3 And pDT(cyc)=<pDT(v) Then viable=1
  If viable=1 And cyc<>v And InProximity(cyc,v,30) And AttackViable(v)=<3
   vX#=RealX#(v) : vZ#=RealZ#(v)
   If AttackViable(v)=3
    PositionEntity dummy,pX#(v),pY#(v),pZ#(v)
    RotateEntity dummy,0,pA#(v),0
    MoveEntity dummy,0,0,10
    vX#=EntityX(dummy) : vZ#=EntityZ(dummy)  
   EndIf
   If ReachedCord(checkX#,checkZ#,vX#,vZ#,10) Then value=1
  EndIf
 Next
 Return value
End Function

;GET PINNING DATA
Function GetPin(cyc,v) ;1=overhead, 2=from left (flat), 3=from right (flat), 4=from left (hooked), 5=from right (hooked)
 ;consider orientation (0=back, 1=front)
 If PinReaction(v)>0 And PinReaction(v)<>150 And pAnim(v)<>151 Then orientation=1 Else orientation=0
 ;ideal position
 pPinStyle(cyc)=NearestHumanSide(cyc,v)+1
 If (orientation=0 And PinClear(cyc,v,0,-17,6)) Or (orientation=1 And PinClear(cyc,v,0,17,6)) Or InProximity(cyc,v,20)=0
  pPinStyle(cyc)=1
 EndIf
 If (orientation=0 And PinClear(cyc,v,-8,0,10)) Or (orientation=1 And PinClear(cyc,v,8,0,10))
  randy=Rnd(0,1)
  If randy=0 Then pPinStyle(cyc)=4 Else pPinStyle(cyc)=2
 EndIf
 If (orientation=0 And PinClear(cyc,v,8,0,10)) Or (orientation=1 And PinClear(cyc,v,-8,0,10))
  randy=Rnd(0,1)
  If randy=0 Then pPinStyle(cyc)=5 Else pPinStyle(cyc)=3
 EndIf
 ;target data (on back)
 If orientation=0
  If pPinStyle(cyc)=1
   ProjectDummy(v,0,0,-17)
   pPinX#(cyc)=EntityX(dummy) : pPinZ#(cyc)=EntityZ(dummy) 
   pPinA#(cyc)=pA#(v)
  EndIf
  If pPinStyle(cyc)=2
   ProjectDummy(v,-5,0,-6)
   pPinX#(cyc)=EntityX(dummy) : pPinZ#(cyc)=EntityZ(dummy) 
   pPinA#(cyc)=CleanAngle#(pA#(v)-90)
  EndIf
  If pPinStyle(cyc)=3
   ProjectDummy(v,5,0,-6)
   pPinX#(cyc)=EntityX(dummy) : pPinZ#(cyc)=EntityZ(dummy) 
   pPinA#(cyc)=CleanAngle#(pA#(v)+90)
  EndIf
  If pPinStyle(cyc)=4
   ProjectDummy(v,-7,0,-9)
   pPinX#(cyc)=EntityX(dummy) : pPinZ#(cyc)=EntityZ(dummy) 
   pPinA#(cyc)=CleanAngle#(pA#(v)-90)
  EndIf
  If pPinStyle(cyc)=5
   ProjectDummy(v,10,0,-7)
   pPinX#(cyc)=EntityX(dummy) : pPinZ#(cyc)=EntityZ(dummy) 
   pPinA#(cyc)=CleanAngle#(pA#(v)+90)
  EndIf
 EndIf
 ;target data (on front)
 If orientation=1
  If pPinStyle(cyc)=1
   ProjectDummy(v,0,0,17)
   pPinX#(cyc)=EntityX(dummy) : pPinZ#(cyc)=EntityZ(dummy) 
   pPinA#(cyc)=CleanAngle#(pA#(v)+180)
  EndIf
  If pPinStyle(cyc)=2
   ProjectDummy(v,5,0,6)
   pPinX#(cyc)=EntityX(dummy) : pPinZ#(cyc)=EntityZ(dummy) 
   pPinA#(cyc)=CleanAngle#(pA#(v)+90)
  EndIf
  If pPinStyle(cyc)=3
   ProjectDummy(v,-5,0,6)
   pPinX#(cyc)=EntityX(dummy) : pPinZ#(cyc)=EntityZ(dummy) 
   pPinA#(cyc)=CleanAngle#(pA#(v)-90)
  EndIf
  If pPinStyle(cyc)=4
   ProjectDummy(v,7,0,9)
   pPinX#(cyc)=EntityX(dummy) : pPinZ#(cyc)=EntityZ(dummy) 
   pPinA#(cyc)=CleanAngle#(pA#(v)+90)
  EndIf
  If pPinStyle(cyc)=5
   ProjectDummy(v,-10,0,7)
   pPinX#(cyc)=EntityX(dummy) : pPinZ#(cyc)=EntityZ(dummy) 
   pPinA#(cyc)=CleanAngle#(pA#(v)-90)
  EndIf
 EndIf
 Return value
End Function

;CHECK PIN OPTION IS CLEAR
Function PinClear(cyc,v,posX#,posZ#,range)
 clear=0
 ;validate specified area
 ProjectDummy(v,posX#,0,posZ#)
 checkX#=EntityX(dummy) : checkZ#=EntityZ(dummy)
 If pX#(cyc)>checkX#-range And pX#(cyc)<checkX#+range And pZ#(cyc)>checkZ#-range And pZ#(cyc)<checkZ#+range Then clear=1
 ;human conflicts
 If clear=1 
  For count=1 To no_plays
   If count<>cyc And count<>v And AttackViable(count)<>4 And pY#(cyc)=>pY#(count)-30 And pY#(cyc)=<pY#(count)+30 
    If checkX#>pX#(count)-10 And checkX#<pX#(count)+10 And checkZ#>pZ#(count)-10 And checkZ#<pZ#(count)+10 Then clear=0
   EndIf
  Next
 EndIf
 ;item conflicts
 If clear>0
  For b=1 To no_items
   If iState(b)=0 And checkX#>iX#(b)-30 And checkX#<iX#(b)+30 And checkZ#>iZ#(b)-30 And checkZ#<iZ#(b)+30 And pY#(cyc)>iY#(b)-5 And pY#(cyc)<iY#(b)+10
    ScanItem(b,0)
    If ItemCollide(cyc,b,checkX#,checkZ#,0.5)=1 Then clear=0
   EndIf
  Next
 EndIf
 ;block conflicts
 If clear=1
  For b=1 To 100
   If blockType(b)>0
    If checkX#>blockX1#(b) And checkX#<blockX2#(b) And checkZ#>blockZ1#(b) And checkZ#<blockZ2#(b) And pY#(cyc)>blockY1#(b) And pY#(cyc)<blockY2#(b)
     clear=0
    EndIf
   EndIf
  Next
 EndIf
 ;scenery logic
 If InsideRing(pX#(v),pZ#(v),-15) And InsideRing(checkX#,checkZ#,-15)=0 Then clear=0 ;ring
 If BehindRailings(checkX#,checkZ#) And BehindRailings(pX#(v),pZ#(v))=0 Then clear=0 ;railings
 If BehindRailings(pX#(v),pZ#(v)) And BehindRailings(checkX#,checkZ#)=0 Then clear=0 ;railings
 If pZ#(v)<385 And checkZ#>385 Then clear=0 ;curtains
 If pZ#(v)>-385 And checkZ#<-385 Then clear=0 ;curtains
 Return clear
End Function

;PURSUE PIN POSITION
Function ApplyPin(cyc,v)
 If pPinning(cyc)>0
  ;move to location
  speed#=0.75
  If pX#(cyc)>pPinX#(cyc) Then pX#(cyc)=pX#(cyc)-speed#
  If pX#(cyc)<pPinX#(cyc) Then pX#(cyc)=pX#(cyc)+speed#
  If pX#(cyc)=>pPinX#(cyc)-speed# And pX#(cyc)=<pPinX#(cyc)+speed# Then pX#(cyc)=pPinX#(cyc)
  If pZ#(cyc)>pPinZ#(cyc) Then pZ#(cyc)=pZ#(cyc)-speed#
  If pZ#(cyc)<pPinZ#(cyc) Then pZ#(cyc)=pZ#(cyc)+speed#
  If pZ#(cyc)=>pPinZ#(cyc)-speed# And pZ#(cyc)=<pPinZ#(cyc)+speed# Then pZ#(cyc)=pPinZ#(cyc)
  ;reach correct angle 
  If ReachedCord(pX#(cyc),pZ#(cyc),pPinX#(cyc),pPinZ#(cyc),5)
   turner#=2.5
   turner#=BoostTurn#(pA#(cyc),pPinA#(cyc),turner#,3)
   pA#(cyc)=pA#(cyc)+ReachAngle#(pA#(cyc),pPinA#(cyc),turner#)
   If SatisfiedAngle(pA#(cyc),pPinA#(cyc),turner#*2) Then pA#(cyc)=pPinA#(cyc)
  Else
   InstantTurn(cyc,pLimb(pPinning(cyc),1))
  EndIf
 EndIf
End Function

;PIN ATTEMPT REACTION
Function PinReaction(cyc)
 ;nothing by default
 anim=0
 ;still lying on back
 If pAnim(cyc)=151 Then anim=150 ;turning onto hands & knees
 If pAnim(cyc)=68 Then anim=150 ;baseball slide
 ;lying on front
 If pAnim(cyc)=138 Then anim=159 ;breaking down onto front
 If pAnim(cyc)=151 And pAnimTim(cyc)>Int(30/pAnimSpeed#(cyc)) Then anim=159 ;turning onto front
 If pAnim(cyc)=152 Or pAnim(cyc)=109 Then anim=159 ;already on front
 If pAnim(cyc)=153 Then anim=159 ;rising off front
 If pAnim(cyc)=502 And pAnimTim(pGrappler(cyc))<Int(15/pAnimSpeed#(pGrappler(cyc)))
  If pAnim(pGrappler(cyc))=504 Or pAnim(pGrappler(cyc))=604 Then anim=159 ;being ground grappled off front
 EndIf
 ;hands & knees breakdown
 breaker=0
 If pAnim(cyc)=137 Or (pAnim(cyc)=138 And pAnimTim(cyc)=<Int(20/pAnimSpeed#(cyc))) Or (pAnim(cyc)=139 And pAnimTim(cyc)=<Int(20/pAnimSpeed#(cyc))) Then anim=139
 If (pAnim(cyc)=151 And pAnimTim(cyc)>Int(40/pAnimSpeed#(cyc))) Or (pAnim(cyc)=153 And pAnimTim(cyc)>Int(20/pAnimSpeed#(cyc))) Or pAnim(cyc)=154 Or pAnim(cyc)=158 Then anim=139
 If pAnim(cyc)=>160 And pAnim(cyc)=<161 Then anim=139
 If pAnim(cyc)=69 Then anim=139 ;hind kick
 ;return animation
 Return anim
End Function

;PROTECT PIN?
Function ProtectPin(cyc)
 value=0
 If matchState=3 And matchPins>0 And FallsCount(cyc) And LegalMan(cyc)
  v=pPinning(cyc)
  If v>0 And LegalMan(v) And pTeam(cyc)<>pTeam(v) And InProximity(cyc,v,15)
   If pDT(v)>100 Or pChecked(v)=1 Then value=1
  EndIf
  If matchRules=>1 And (TouchingRopes(cyc) Or TouchingRopes(v)) Then value=0
 EndIf
 Return value
End Function

;TAUNT EFFECTS
Function TauntEffects(cyc,v,tauntTim)
 If matchState=3 And LegalMan(cyc) And v>0 
  If LegalMan(v) And InProximity(cyc,v,50)
   If pAnimTim(cyc)=tauntTim Then Pop(cyc,Rnd(1,9),0) 
   randy=Rnd(0,100)
   If randy=<1 Or pAnimTim(cyc)=tauntTim Then pHeat(cyc)=pHeat(cyc)+1 : pHeat(v)=pHeat(v)-1
  EndIf
  If pAnimTim(cyc)=<100 
   randy=Rnd(0,2)
   If randy=0 And InProximity(cyc,v,50) Then entScore=entScore+Rnd(0,charPopularity(pChar(cyc))/20) Else entScore=entScore+Rnd(0,2)
   If pSpecial(cyc)>0 Then entScore=entScore+Rnd(0,1)
  EndIf
 EndIf 
End Function

;TRYING TO ABORT TAUNT?
Function TauntAbort(cyc)
 ;human cancellation 
 cancel=0
 If pControl(cyc)>0
  If (DirPressed(cyc) Or ActionPressed(cyc)) And cTaunt(cyc)=0 Then cancel=1
 EndIf
 ;CPU urgency
 If pControl(cyc)=0
  randy=Rnd(0,250)
  If randy=<1 Or FindThreat(cyc)>0 Or RushViable(cyc) Then cancel=1 
  If pRole(cyc)=1 
   For v=1 To no_wrestlers 
    duty=RefViable(v)
    If (duty=>1 And duty=<4) Or (duty=>11 And duty=<14) Then cancel=1 
   Next
  EndIf
 EndIf
 ;force cancel if reaction due
 If pReaction(cyc)>0 Then cancel=1
 Return cancel
End Function