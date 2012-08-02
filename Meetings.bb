;//////////////////////////////////////////////////////////////////////////////
;------------------------- WRESTLING MPIRE 2008: MEETINGS ---------------------
;//////////////////////////////////////////////////////////////////////////////

;----------------------------------------------------------------
;//////////////////// RISK FORMAL MEETINGS //////////////////////
;----------------------------------------------------------------
Function RiskFormalMeetings()
 fed=charFed(gamChar)
 negTopic=0 : negSetting=1 : negChar=0
 ;media offers
 randy=Rnd(0,50)
 If randy=0 Then negTopic=56 : negChar=fedBooker(8) ;magazine interview
 If randy=1 Then negTopic=57 : negChar=fedBooker(8) ;movie offer
 If randy=2 And fedBank(fed)>0 Then negTopic=58 : negChar=fedBooker(8) ;DVD offer
 If randy=3 And fedBank(fed)>0 Then negTopic=59 : negChar=fedBooker(8) ;game offer
 ;promotion switch
 randy=Rnd(0,40)
 If randy=<1 And charExperience(gamChar,fed)>4
  Repeat
   negVariable=Rnd(1,6)
  Until negVariable<>fed And fedRanked(negVariable)<>1 And fedSize(negVariable)<optRosterLim
  negTopic=28 : negChar=201
 EndIf
 ;inter-promotional challenge
 inter=0 : charity=0
 For date=gamDate To 48
  If gamSchedule(date)=4 Then inter=1
  If gamSchedule(date)=5 Then charity=1
 Next
 randy=Rnd(0,40)
 If randy=<1 And inter=0 And gamDate=<46 And gamSchedule(gamDate+2)=<2
  Repeat 
   promotion=Rnd(1,6)
  Until promotion<>fed 
  negTopic=24 : negChar=fedBooker(promotion)
 EndIf
 ;drug scandal
 randy=Rnd(0,10)
 If randy=<1 And gamAgreement(14)>0 And gamSchedule(gamDate)>0 Then negTopic=52 : negChar=201
 ;event issues
 randy=Rnd(0,50)
 If randy=<1 Then negTopic=27 : negChar=201 ;charity suggestion
 If randy=2 And gamSchedule(gamDate)>0 Then negTopic=53 : negChar=201 ;power failure!
 If randy=3 And gamSchedule(gamDate)>0 Then negTopic=54 : negChar=201 ;bomb threat! 
 If gamAgreement(20)>0 And gamSchedule(gamDate)>0 Then negTopic=55 : negChar=201 ;no audience!
 If gamFatality>0 Then negTopic=26 : negChar=201 ;tribute suggestion
 ;analyze roster
 tempPopularity=0 : tempStrength=0 : tempSkill=0 : tempAgility=0
 tempStamina=0 : tempToughness=0 : tempFemale=0
 For count=1 To fedSize(fed)
  char=fedRoster(fed,count)
  If charPopularity(char)=>90 Then tempPopularity=char
  If charStrength(char)=>90 Then tempStrength=char
  If charSkill(char)=>90 Then tempSkill=char
  If charAgility(char)=>90 Then tempAgility=char
  If charStamina(char)=>90 Then tempStamina=char
  If charToughness(char)=>90 Then tempToughness=char
  If charGender(char)=1 Then tempFemale=char
 Next
 ;mission assignments
 If gamMission=0 And gamDate=<41 And charExperience(gamChar,fed)>4
  randy=Rnd(0,50)
  If (randy=1 And fedPopularity(fed)<85) Or fedPopularity(fed)<60 Then negTopic=101 : negChar=201 ;improve popularity
  If (randy=2 And fedReputation(fed)<80) Or fedReputation(fed)<50 Then negTopic=102 : negChar=201 ;improve reputation
  If randy=3 And fedBank(fed)=>0 And fedBank(fed)<1000000 Then negTopic=103 : negChar=201 ;improve finances
  If randy=4 Then negTopic=106 : negChar=201 ;attain character
  If randy=5 And fedSize(fed)>20 Then negTopic=107 : negChar=201 ;remove character
  If randy=6 And fedSize(fed)>5 Then negTopic=108 : negChar=201 ;crown world champion
  If randy=7 And fedSize(fed)>5 Then negTopic=109 : negChar=201 ;crown inter champion
  If randy=8 And fedSize(fed)>5 Then negTopic=110 : negChar=201 ;crown tag champions 
  If (randy=9 Or fedSize(fed)=<12) And fedSize(fed)=<32 Then negTopic=111 : negChar=201 ;expand roster
  If randy=10 And fedSize(fed)>30 Then negTopic=112 : negChar=201 ;reduce roster
  If randy=11 And CountProductions(fed)<10 Then negTopic=113 : negChar=201 ;acquire production 
  If randy=>12 And randy=<13
   rando=Rnd(1,6)
   If rando=1 And tempPopularity=0 Then negTopic=114 : negChar=201 ;attain popular character
   If rando=2 And tempStrength=0 Then negTopic=115 : negChar=201 ;attain strong character
   If rando=3 And tempSkill=0 Then negTopic=116 : negChar=201 ;attain skillful character
   If rando=4 And tempAgility=0 Then negTopic=117 : negChar=201 ;attain agile character
   If rando=5 And tempStamina=0 Then negTopic=118 : negChar=201 ;attain fit character
   If rando=6 And tempToughness=0 Then negTopic=119 : negChar=201 ;attain tough character
  EndIf
  If randy=14 And tempFemale=0 Then negTopic=120 : negChar=201 ;attain female character
  If showRanked(fed)>1 And showOldRanked(fed)=1 Then negTopic=105 : negChar=201 ;regain top spot
  If fedBank(fed)<0 Then negTopic=104 : negChar=201 ;get out of debt!
 EndIf
 ;mission completions
 If gamMission=1 And fedPopularity(fed)=>gamTarget Then negTopic=120 : negChar=201 ;improved popularity
 If gamMission=2 And fedReputation(fed)=>gamTarget Then negTopic=120 : negChar=201 ;improved reputation
 If gamMission=3 And fedBank(fed)=>gamTarget Then negTopic=121 : negChar=201 ;improved finances 
 If gamMission=4 And fedBank(fed)=>0 Then negTopic=121 : negChar=201 ;got out of debt
 If gamMission=5 And showRanked(fed)=1 Then negTopic=120 : negChar=201 ;regained top spot
 If gamMission=6 
  If charFed(gamTarget)=fed Then negVariable=gamTarget : negTopic=122 : negChar=201 ;attained character
 EndIf
 If gamMission=7
  If charFed(gamTarget)<>fed Then negTopic=120 : negChar=201 ;removed character 
 EndIf
 If gamMission=8 And fedChampWorld(fed)=gamTarget Then negTopic=120 : negChar=201 ;crowned world champion
 If gamMission=9 And fedChampInter(fed)=gamTarget Then negTopic=120 : negChar=201 ;crowned inter champion
 If gamMission=10 
  If TitleHolder(gamTarget,3) Then negTopic=120 : negChar=201 ;crowned tag champions
 EndIf
 If gamMission=11 And fedSize(fed)=>gamTarget Then negTopic=120 : negChar=201 ;expanded roster
 If gamMission=12 And fedSize(fed)=<gamTarget Then negTopic=120 : negChar=201 ;reduced roster 
 If gamMission=13
  If fedProduction(fed,gamTarget)>0 Then negTopic=120 : negChar=201 ;acquired production
 EndIf
 If gamMission=14 And tempPopularity>0 Then negVariable=tempPopularity : negTopic=122 : negChar=201 ;acquired popular character
 If gamMission=15 And tempStrength>0 Then negVariable=tempStrength : negTopic=122 : negChar=201 ;acquired strong character
 If gamMission=16 And tempSkill>0 Then negVariable=tempSkill : negTopic=122 : negChar=201 ;acquired skillful character
 If gamMission=17 And tempAgility>0 Then negVariable=tempAgility : negTopic=122 : negChar=201 ;acquired agile character
 If gamMission=18 And tempStamina>0 Then negVariable=tempStamina : negTopic=122 : negChar=201 ;acquired fit character
 If gamMission=19 And tempToughness>0 Then negVariable=tempToughness : negTopic=122 : negChar=201 ;acquired tough character
 If gamMission=20 And tempFemale>0 Then negVariable=tempFemale : negTopic=122 : negChar=201 ;acquired female character 
 ;mission failure
 If gamMission>0 And gamDate=>gamDeadline And negTopic<120 Then gamComplete=-1
 ;victory!!!
 If showRanked(fed)=1 And gamComplete<2 Then gamComplete=1 : gamMission=0
 ;execute
 If negTopic>0 And negChar>0 And gamComplete<>-1 And gamComplete<>1 And screen<>25 And screen<>50 Then screen=53 : Meeting() 
End Function

;----------------------------------------------------------------
;//////////////////// RISK CASUAL MEETINGS //////////////////////
;----------------------------------------------------------------
Function RiskCasualMeetings(factor)
 ;INTERNAL ISSUES
 fed=charFed(gamChar) 
 For count=1 To fedSize(fed)
  negTopic=0 : negSetting=2 : negChar=fedRoster(fed,count)
  If negChar<>gamChar And gamNegotiated(negChar,3)=0 And charVacant(negChar)=0 And InjuryStatus(negChar)=<4
   ;allegiance issues
   chance=300
   If charHeel(negChar)=1 And AllegianceRatio(fed,0)<AllegianceRatio(fed,1) Then chance=chance/2
   If charHeel(negChar)=0 And AllegianceRatio(fed,1)<AllegianceRatio(fed,0) Then chance=chance/2
   If charHeel(negChar)=1 And AllegianceRatio(fed,0)>AllegianceRatio(fed,1) Then chance=chance*2
   If charHeel(negChar)=0 And AllegianceRatio(fed,1)>AllegianceRatio(fed,0) Then chance=chance*2
   randy=Rnd(0,chance*factor)
   If randy=<1 Then negTopic=2+charHeel(negChar) 
   ;title requests
   If TitleHolder(negChar,0)=0 And charAgreement(negChar,11)=0 And charAgreement(negChar,12)=0 And charAgreement(negChar,13)=0
    randy=Rnd(0,750*factor)
    If randy=0 And fedChampWorld(fed)>0 Then negTopic=4 ;world title run
    If randy=1 And fedChampInter(fed)>0 Then negTopic=5 ;inter title run
    If randy=2 And fedChampTag(fed,1)>0 And fedChampTag(fed,2)>0
     If charPartner(negChar)>0 And TitleHolder(charPartner(negChar),3)=0 Then negTopic=6 ;tag title run
    EndIf
   EndIf
   ;relationship issues
   randy=Rnd(0,1250*factor)
   If randy=<3 And charPartner(negChar)=0
    If randy=0 And FindFriend(negChar,0)>0 And TitleHolder(negChar,3)=0 Then negTopic=7 ;team with story friend
    If randy=1 And FindRealFriend(negChar,0)>0 And TitleHolder(negChar,3)=0 Then negTopic=8 ;team with real friend
    If randy=2 And FindFriend(negChar,0)>0 And charManager(negChar)=0 Then negTopic=9 ;managed by story friend
    If randy=3 And FindRealFriend(negChar,0)>0 And charManager(negChar)=0 Then negTopic=10 ;managed by real friend
   EndIf 
   If randy=4 
    If FindFriend(negChar,0)>0 And TitleHolder(negChar,3)=0 Then negTopic=35 ;split up with friend
   EndIf
   If randy=>5 And randy=<6 And charPartner(negChar)>0 And TitleHolder(negChar,3)=0 Then negTopic=11 ;split from partner
   If randy=>7 And randy=<8 And charManager(negChar)>0 Then negTopic=12 ;split from manager
   If randy=9 Then negTopic=44 ;locker room leadership
   If randy=10 And gamSchedule(gamDate)>0 And gamSegments(gamDate)=0
    If FindRealEnemy(negChar,0)>0 Then negTopic=47 ;send enemy home
   EndIf 
   If randy=11
    If FindRealEnemy(negChar,0)>0 Then negTopic=48 ;fire enemy
   EndIf
   If randy=12 Then negTopic=49 ;hire friend
   ;gimmick issues
   randy=Rnd(0,750*factor)
   If randy=0 And charAgreement(negChar,1)=0 Then negTopic=13 ;change solo name
   If randy=1 Or (randy=>1 And randy=<2 And charTeamName$(negChar)=charName$(negChar)+"'s Team") 
    If charPartner(negChar)>0 And charAgreement(negChar,1)=0 Then negTopic=14 ;change team name
   EndIf
   If randy=3 And charAgreement(negChar,3)=0 Then negTopic=15 ;change costume
   If randy=4 And charPartner(negChar)>0 And charAgreement(negChar,3)=0 
    If charLegs(negChar,1)<>charLegs(charPartner(negChar),1) Then negTopic=16 ;synchronize costumes
   EndIf
   If randy=5 And charAgreement(negChar,2)=0 Then negTopic=17 ;change hairstyle
   If randy=6 And charAgreement(negChar,4)=0 And fedProduction(charFed(gamChar),6)>0 Then negTopic=18 ;change entrance
   If randy=7 And charAgreement(negChar,5)=0 Then negTopic=19 ;change attacks
   If randy=8 And charAgreement(negChar,6)=0 Then negTopic=20 ;change moves
   If randy=9 And charAgreement(negChar,7)=0 Then negTopic=21 ;change gestures
   If randy=10 And CountProductions(fed)<12 Then negTopic=22 ;production idea 
   ;health issues
   randy=Rnd(0,750*factor)
   If randy=0 Or (randy=1 And charTrainCourse(negChar)>0)
    If AverageStats(negChar)<80 And charAgreement(negChar,9)=0 Then negTopic=36 ;steroid request
   EndIf
   If (randy=2 And charHealth(negChar)<25) Or (randy=3 And charHealth(negChar)<50)
    If gamSchedule(gamDate)>0 And charWorked(negChar)=0 And InjuryStatus(negChar)=0 And charAgreement(negChar,10)=0 Then negTopic=37 ;painkiller request
   EndIf
   randy=Rnd(0,150*factor)
   If randy=<1 And InjuryStatus(negChar)>1 Then negTopic=38 ;surgery request
   ;repair limbs
   For limb=1 To 50
    chance=750*factor
    If MajorLimb(limb) Then chance=chance/5
    randy=Rnd(0,chance)
    If randy=<1 And charLimb(negChar,limb)=0 Then negTopic=39 
   Next
   ;work issues
   If gamSchedule(gamDate)>0 And charWorked(negChar)=0 And InjuryStatus(negChar)=0
    chance=charAttitude(negChar)*10
    randy=Rnd(0,chance*factor)
    If randy=0 Or (randy=1 And charHealth(negChar)<50) Then negTopic=40 ;night off
    If randy=2 Or (randy=3 And charHappiness(negChar)<70) Then negTopic=41 ;extended break
    If randy=3 Then negTopic=43 ;held up for money
    If randy=4 And charSalary(negChar)<3000 Then negTopic=45 ;protest threat
   EndIf
   chance=charHappiness(negChar)*10
   randy=Rnd(0,chance*factor)
   If randy=<1 And charContract(negChar)>1 And charHappiness(negChar)<70 Then negTopic=46 ;mutual termination
   randy=Rnd(0,charAttitude(negChar)*factor)
   If randy=<1 And charTrainCourse(negChar)>0 Then negTopic=42 ;excused training
   ;invasion
   randy=Rnd(0,1500)
   If randy=<1 And gamSchedule(gamDate)>0 And gamSegments(gamDate)=0 Then negTopic=51
  EndIf
  ;execute
  If negTopic>0 And negChar>0 And screen<>25 And screen<>50 Then screen=53 : Meeting() 
 Next
End Function

;----------------------------------------------------------------
;////////////////// RISK POST-MATCH MEETINGS ////////////////////
;----------------------------------------------------------------
Function PostMatchMeetings()
 ;nothing by default
 negTopic=0 : negSetting=3 : negChar=0 : negVariable=0 : fed=charFed(gamChar)
 ;match participants
 For cyc=1 To no_plays
  If pChar(cyc)<>gamChar And InjuryStatus(pChar(cyc))=0 And charVacant(pChar(cyc))=0 And charFed(pChar(cyc))=<8
   ;rematch suggestion
   randy=Rnd(0,10)
   If randy=0 And no_wrestlers=2 And matchWinner=cyc And matchLoser>0 And charRelationship(pChar(cyc),pChar(matchLoser))=>0
    negTopic=50 : negChar=pChar(cyc) : negVariable=pChar(matchLoser)
   EndIf
   If randy=0 And no_wrestlers=2 And matchWinner>0 And matchLoser=cyc And charRelationship(pChar(cyc),pChar(matchWinner))=>0
    negTopic=50 : negChar=pChar(cyc) : negVariable=pChar(matchWinner)
   EndIf
   ;team with casual partner
   If pRole(cyc)=0 And matchTeams>0 And no_wrestlers=<6
    partner=0
    For v=1 To no_wrestlers
     If cyc<>v And pTeam(cyc)=pTeam(v) And partner=0 Then partner=pChar(v)
    Next
    randy=Rnd(0,4)
    If randy=0 And partner>0 And partner<>charPartner(pChar(cyc)) And pChar(cyc)<>charManager(partner)
     If charFed(partner)=charFed(pChar(cyc)) Then negTopic=29 : negChar=pChar(cyc) : negVariable=partner
    EndIf 
   EndIf 
   ;turn on incompetent team-mate
   randy=Rnd(0,10)
   If randy=0 And matchTeams>0 And pRole(cyc)=0 And matchLoser<>cyc And pTeam(matchLoser)=pTeam(cyc) And pTeam(matchWinner)<>pTeam(cyc) And TitleHolder(pChar(cyc),3)=0
    If charFed(pChar(matchLoser))=charFed(pChar(cyc)) Then negTopic=32 : negChar=pChar(cyc) : negVariable=pChar(matchLoser) 
   EndIf
   ;turn on abusive team-mate
   randy=Rnd(0,10)
   If randy=0 Or (randy=1 And pTeam(cyc)=pTeam(matchLoser))
    If pAbused(cyc)>0 And charFed(pAbused(cyc))=charFed(pChar(cyc)) And TitleHolder(pChar(cyc),3)=0 Then negTopic=33 : negChar=pChar(cyc) : negVariable=pAbused(cyc)
   EndIf
   ;blame main referee
   ref=pChar(no_wrestlers+1) 
   randy=Rnd(0,4)
   If (randy=0 And charRole(ref)=<2) Or (randy=1 And matchPromo=28)
    If pRole(cyc)=0 And matchLoser=cyc And no_refs>0 And charRelationship(pChar(cyc),ref)=>0
     If charFed(ref)=charFed(pChar(cyc)) Then negTopic=34 : negChar=pChar(cyc) : negVariable=ref
    EndIf
   EndIf
   ;blame interfering referee
   ref=0
   For v=1 To no_plays
    If v>no_wrestlers+no_refs And pRole(v)=1 Then ref=pChar(v)
   Next
   randy=Rnd(0,2)
   If randy=0 And ref>0 And pRole(cyc)=0 And matchLoser=cyc And charRelationship(pChar(cyc),ref)=>0
    If charFed(ref)=charFed(pChar(cyc)) Then negTopic=34 : negChar=pChar(cyc) : negVariable=ref
   EndIf
   ;interferences
   If pRole(cyc)=0
    randy=Rnd(0,5)
    helper=0
    For v=1 To no_plays
     If cyc<>v And pRole(v)=>2 And pRole(v)=<3 And pTeam(v)=pTeam(cyc) Then helper=pChar(v)
    Next
    If randy=0 And helper>0 And helper<>charPartner(pChar(cyc)) And helper<>charManager(pChar(cyc))
     If charFed(helper)=charFed(pChar(cyc)) Then negTopic=30 : negChar=pChar(cyc) : negVariable=helper ;team with helper (that took side)
    EndIf
    If randy=1 And matchWinner=cyc And matchLoser>0 And pTeam(matchLoser)<>pTeam(cyc)
     If pIntruder(matchLoser)>0 And pIntruder(matchLoser)<>charPartner(pChar(cyc)) And pIntruder(matchLoser)<>charManager(pChar(cyc))
      If charFed(pIntruder(matchLoser))=charFed(pChar(cyc)) Then negTopic=30 : negChar=pChar(cyc) : negVariable=pIntruder(matchLoser) ;team with helper (that opposed enemy)
     EndIf
    EndIf
    If (randy=0 Or (randy=1 And matchLoser=cyc)) And pIntruder(cyc)>0 And charRelationship(pChar(cyc),pIntruder(cyc))=>0
     If charFed(pIntruder(cyc))=charFed(pChar(cyc)) Then negTopic=31 : negChar=pChar(cyc) : negVariable=pIntruder(cyc) ;enemies with intruder
    EndIf 
   EndIf
   ;invasion leads to inter-promotional
   If pIntruder(cyc)>0 And charFed(pIntruder(cyc))=<6 And charFed(pIntruder(cyc))<>charFed(gamChar) 
    If gamSchedule(gamDate)=<2 And gamDate=<46 And gamSchedule(gamDate+2)=<2
     conflict=0
     For date=gamDate To 48
      If gamSchedule(date)=4 Then conflict=1
     Next
     If conflict=0 Then negTopic=25 : negChar=pIntruder(cyc)
    EndIf
   EndIf
  EndIf
 Next
 ;inter-promotional aftermath
 randy=Rnd(0,1)
 If randy=0 And gamSchedule(gamDate)=4 And gamSegments(gamDate)=3 Then negTopic=23 : negChar=fedBooker(gamRivalFed(gamDate))
 ;pay for damages
 randy=Rnd(0,10000)
 If matchDamage=>100 And randy=<matchDamage And fedBank(fed)>0 And gamDate=<47 And gamSchedule(gamDate+1)>0
  negTopic=60 : negChar=201 
 EndIf
 ;execute
 If negTopic>0 And negChar>0 And negVariable<>gamChar And screen<>25 And screen<>50 Then screen=53 : Meeting()  
End Function

;---------------------------------------------------------------
;///////////////// LOAD MEETING SETTING ////////////////////////
;---------------------------------------------------------------
Function PrepareMeeting()
 ;calculate cast
 ResetTextures()
 no_plays=4
 For count=1 To optPlayLim
  pChar(count)=0
 Next
 pChar(1)=negChar : pChar(2)=gamChar
 If screen=53
  negStar=1 : negVisitor=2
  If negSetting=1 Then negStar=2 : negVisitor=1
  pChar(negStar)=gamChar : pChar(negVisitor)=negChar
  camFoc=negVisitor
 EndIf
 If screen=55 
  no_plays=5 : pChar(1)=gamChar : pChar(2)=negChar
  pChar(3)=201 : pChar(4)=fedBooker(8)
  pChar(5)=202 : camFoc=5
 EndIf
 If screen=56 Then pChar(1)=gamChar : pChar(2)=fedBooker(fed) : pChar(3)=negChar
 If negSetting=1 And screen<>55 And screen<>56
  For cyc=1 To 2
   If FindCharacter(fedBooker(charFed(pChar(cyc))))=0 And pChar(cyc+2)=0 And pChar(cyc)<>201 Then pChar(cyc+2)=fedBooker(charFed(pChar(cyc)))
  Next
  If screen=53 And (negTopic=56 Or negTopic=57) Then pChar(3)=negVariable
 EndIf
 For cyc=1 To 2
  If pChar(cyc)=0 Then pChar(cyc)=Rnd(1,no_chars)
 Next
 ;LOCATION
 ;office locations
 If negSetting=>1 And negSetting=<5 
  world=LoadAnimMesh("World/Office/Office.3ds")
  If charFed(negChar)=>1 And charFed(negChar)=<6
   tempLogo=LoadTexture("World/Videos/Promotion0"+charFed(negChar)+".JPG")
   tempFlag=LoadTexture("World/Videos/Flag0"+charFed(negChar)+".JPG")
  Else
   If charFed(negChar)=0 Then tempLogo=LoadTexture("World/Videos/Video02.JPG")
   If charFed(negChar)>0 Then tempLogo=LoadTexture("Graphics/Promotions/Promotion0"+charFed(negChar)+".png")
   tempFlag=LoadTexture("World/Videos/Flag02.JPG") 
  EndIf
  EntityTexture FindChild(world,"Poster01"),tempLogo
  FreeTexture tempLogo
  EntityTexture FindChild(world,"Poster02"),tempFlag
  FreeTexture tempFlag
  EntityAlpha FindChild(world,"Window"),0.5 
  If negSetting=1 And camFoc=1 ;seated (aiming for guest)
   randy=Rnd(1,2)
   If randy=1 Then camX#=-80 : camY#=50 : camZ#=90
   If randy=2 Then camX#=100 : camY#=50 : camZ#=-80
  EndIf
  If negSetting=1 And camFoc=2 ;seated (aiming for host)
   randy=Rnd(1,2)
   If randy=1 Then camX#=-90 : camY#=50 : camZ#=-90
   If randy=2 Then camX#=90 : camY#=50 : camZ#=-90
  EndIf
  If negSetting=2 ;north east
   camX#=-80 : camY#=Rnd(25,50) : camZ#=-80
   meetX#=65 : meetZ#=80 : meetA#=135
  EndIf
  If negSetting=3 ;south east
   camX#=-90 : camY#=Rnd(10,50) : camZ#=90
   meetX#=60 : meetZ#=-70 : meetA#=45
  EndIf
  If negSetting=4 ;south west
   camX#=90 : camY#=Rnd(10,50) : camZ#=90
   meetX#=-55 : meetZ#=-60 : meetA#=315
  EndIf
  If negSetting=5 ;north west
   camX#=80 : camY#=Rnd(25,50) : camZ#=-80
   meetX#=-70 : meetZ#=80 : meetA#=225
  EndIf
  lightX#=0 : lightY#=65 : lightZ#=0
 EndIf
 ;locker room locations
 If negSetting=>6 And negSetting=<9 
  GetArenaSettings(gamVenue(gamDate))
  world=LoadAnimMesh("World/Arena/Hall.3ds")
  DecorateArena()
  If negSetting=6 ;north east
   randy=Rnd(1,2)
   If randy=1 Then camX#=-80 : camY#=Rnd(10,50) : camZ#=870
   If randy=2 Then camX#=220 : camY#=Rnd(10,50) : camZ#=650
   lightX#=110 : lightY#=90 : lightZ#=805
   meetX#=175 : meetZ#=820 : meetA#=135
  EndIf
  If negSetting=7 ;south east
   randy=Rnd(1,2)
   If randy=1 Then camX#=220 : camY#=Rnd(10,50) : camZ#=860
   If randy=2 Then camX#=-40 : camY#=Rnd(10,50) : camZ#=630
   lightX#=110 : lightY#=90 : lightZ#=695
   meetX#=145 : meetZ#=680 : meetA#=45
  EndIf
  If negSetting=8 ;south west
   randy=Rnd(1,2)
   If randy=1 Then camX#=-100 : camY#=Rnd(10,50) : camZ#=880
   If randy=2 Then camX#=40 : camY#=Rnd(10,50) : camZ#=620
   lightX#=-100 : lightY#=90 : lightZ#=695
   meetX#=-100 : meetZ#=690 : meetA#=315
  EndIf
  If negSetting=9 ;north west
   randy=Rnd(1,2)
   If randy=1 Then camX#=-90 : camY#=Rnd(10,50) : camZ#=640
   If randy=2 Then camX#=66 : camY#=Rnd(10,50) : camZ#=875
   lightX#=-100 : lightY#=90 : lightZ#=805
   meetX#=-165 : meetZ#=835 : meetA#=225
  EndIf
 EndIf
 ;lounge locations
 If negSetting=>10 And negSetting=<13 
  GetArenaSettings(gamVenue(gamDate))
  world=LoadAnimMesh("World/Arena/Hall.3ds")
  DecorateArena()
  If negSetting=10 ;north east
   randy=Rnd(1,2)
   If randy=1 Then camX#=-65 : camY#=Rnd(10,50) : camZ#=-620
   If randy=2 Then camX#=130 : camY#=Rnd(10,50) : camZ#=-780
   lightX#=55 : lightY#=70 : lightZ#=-705
   meetX#=100 : meetZ#=-650 : meetA#=135
  EndIf
  If negSetting=11 ;south east
   randy=Rnd(1,2) 
   If randy=1 Then camX#=120 : camY#=Rnd(10,50) : camZ#=-625
   If randy=2 Then camX#=-110 : camY#=Rnd(10,50) : camZ#=-755
   lightX#=55 : lightY#=70 : lightZ#=-705
   meetX#=105 : meetZ#=-755 : meetA#=45
  EndIf
  If negSetting=12 ;south west
   randy=Rnd(1,2)
   If randy=1 Then camX#=-120 : camY#=Rnd(10,50) : camZ#=-620
   If randy=2 Then camX#=120 : camY#=Rnd(10,50) : camZ#=-770
   lightX#=-52 : lightY#=70 : lightZ#=-705
   meetX#=-90 : meetZ#=-740 : meetA#=315
  EndIf
  If negSetting=13 ;north west
   randy=Rnd(1,2)
   If randy=1 Then camX#=-40 : camY#=Rnd(10,50) : camZ#=-780
   If randy=2 Then camX#=90 : camY#=Rnd(10,50) : camZ#=-625
   lightX#=-52 : lightY#=70 : lightZ#=-705
   meetX#=-105 : meetZ#=-650 : meetA#=225
  EndIf
 EndIf
 ;courtroom
 If negSetting=20
  world=LoadAnimMesh("World/Courtroom/Courtroom.3ds")
  For count=1 To 10
   EntityTexture FindChild(world,"Crowd"+Dig$(count,10)),tCrowd(Rnd(1,5))
  Next
  LoopSound sCrowd(1)
  chCrowd=PlaySound(sCrowd(1))
  ChannelVolume chCrowd,0.1
  camX#=10 : camY#=35 : camZ#=-150 
  lightX#=0 : lightY#=70 : lightZ#=100
 EndIf
 ;universal scenery
 PrepareScenery()
 ;ATMOSPHERE
 ;camera
 cam=CreateCamera()
 CameraViewport cam,0,0,GraphicsWidth(),GraphicsHeight()
 CameraZoom cam,1.5 
 PositionEntity cam,camX#,camY#,camZ# 
 camType=10 : camOption=1
 ;pivot
 dummy=CreatePivot()
 camPivot=CreatePivot()
 ;lighting
 AmbientLight 220,210,200
 no_lights=1
 light(1)=CreateLight(1) 
 LightColor light(1),250,230,210
 PositionEntity light(1),lightX#,lightY#,lightZ#
 lightPivot=CreatePivot()
 PositionEntity lightPivot,lightX#+Rnd(-100,100),lightY#,lightZ#+Rnd(-110,110) 
 PointEntity light(1),lightPivot
 ;power failure
 If gamAgreement(18)>0 Or (screen=53 And negTopic=53)
  AmbientLight 20,20,30
  LightColor light(1),25,25,25
 EndIf
 ;CHARACTERS
 ;office positions
 If negSetting=1
  pX#(1)=0 : pY#(1)=5 : pZ#(1)=-4 : pA#(1)=0 : pAnim(1)=Rnd(4,11)
  If pAnim(1)>9 Then pAnim(1)=9
  pX#(2)=0 : pY#(2)=5 : pZ#(2)=67 : pA#(2)=180 : pAnim(2)=Rnd(1,8)
  pX#(3)=20 : pY#(3)=5 : pZ#(3)=-23 : pA#(3)=15 : pAnim(3)=Rnd(10,13)
  pX#(4)=-20 : pY#(4)=5 : pZ#(4)=90 : pA#(4)=195 : pAnim(4)=Rnd(10,13)
 EndIf
 ;standing positions
 If negSetting=>2 And negSetting<20
  PositionEntity dummy,meetX#,0,meetZ#
  RotateEntity dummy,0,meetA#,0
  MoveEntity dummy,12,0,0
  pX#(1)=EntityX(dummy) : pY#(1)=wGround# : pZ#(1)=EntityZ(dummy) : pA#(1)=meetA#+60 : pAnim(1)=Rnd(10,13)
  PositionEntity dummy,meetX#,0,meetZ#
  RotateEntity dummy,0,meetA#,0
  MoveEntity dummy,-12,0,0
  pX#(2)=EntityX(dummy) : pY#(2)=wGround# : pZ#(2)=EntityZ(dummy) : pA#(2)=meetA#-60 : pAnim(2)=Rnd(10,13)
  If negSetting=>2 And negSetting=<5 Then pY#(1)=5 : pY#(2)=5
 EndIf
 ;court positions
 If negSetting=20
  pX#(1)=-45 : pY#(1)=2 : pZ#(1)=10 : pA#(1)=-15 : pAnim(1)=Rnd(10,13)
  pX#(2)=55 : pY#(2)=2 : pZ#(2)=10 : pA#(2)=15 : pAnim(2)=Rnd(10,13)
  pX#(3)=-65 : pY#(3)=2 : pZ#(3)=7 : pA#(3)=-40 : pAnim(3)=Rnd(10,13)
  pX#(4)=75 : pY#(4)=2 : pZ#(4)=7 : pA#(4)=40 : pAnim(4)=Rnd(10,13)
  pX#(5)=7 : pY#(5)=18 : pZ#(5)=127 : pA#(5)=180 : pAnim(5)=Rnd(1,8)
 EndIf
 ;load models
 For cyc=1 To no_plays
  If pChar(cyc)>0
   LoadMeeter(cyc,pChar(cyc))
  EndIf
 Next
 ;restore textures
 RestoreTextures()
 ;point camera at camfoc
 camPivX#=pX#(camFoc) : camPivY#=pY#(camFoc)+30 : camPivZ#=pZ#(camFoc)
 PositionEntity camPivot,camPivX#,camPivY#,camPivZ#
End Function

;---------------------------------------------------------------
;//////////////// LOAD MEETING CHARACTER ///////////////////////
;---------------------------------------------------------------
Function LoadMeeter(cyc,char)
 ;generate model
 p(cyc)=LoadAnimMesh("Characters/Models/Model0"+GetModel(char)+".3ds")
 StripModel(cyc)
 ;apply costume
 pCostume(cyc)=2
 If screen=53
  If negTopic=>15 And negTopic=<16 And char=negChar Then pCostume(cyc)=1
 EndIf
 ApplyCostume(cyc)
 pEyes(cyc)=2 : pOldEyes(cyc)=-1
 pFoc(cyc)=0
 ;include props
 If TitleHolder(char,0)>0 Then WearBelt(cyc,TitleHolder(char,0))
 ;If screen=53 And cyc=2
  ;If (negTopic=>40 And negTopic=<41) Or (negTopic=>73 And negTopic=<74) Then ShowEntity FindChild(p(cyc),"Syringe")
 ;EndIf
 ;post-match scars
 If screen=53 And gamSegments(gamDate)>0 And FindCharacter(char)>0
  For limb=0 To 50
   If pLimb(cyc,limb)>0
    If limb=<4 Then EntityTexture pLimb(cyc,limb),tFaceScar(1),0,5
    If limb=5 Then EntityTexture pLimb(cyc,limb),tBodyScar(1),0,5
    If (limb=>6 And limb=<7) Or (limb=>19 And limb=<20) Then EntityTexture pLimb(cyc,limb),tArmScar(1),0,5
    If (limb=>8 And limb=<18) Or (limb=>21 And limb=<31) Then EntityTexture pLimb(cyc,limb),tHandScar(1),0,5
    If limb=>36 And limb=<44 Then EntityTexture pLimb(cyc,limb),tLegScar(1),0,5
    If limb=>45 And limb=<46 Then EntityTexture pLimb(cyc,limb),tEyeScar(1),0,5 
   EndIf
  Next
 EndIf
 ;load sequences
 pSeq(cyc,601)=LoadAnimSeq(p(cyc),"Characters/Sequences/Standard01.3ds")
 pSeq(cyc,604)=LoadAnimSeq(p(cyc),"Characters/Sequences/Standard04.3ds")
 pSeq(cyc,605)=LoadAnimSeq(p(cyc),"Characters/Sequences/Standard05.3ds")
 pSeq(cyc,606)=LoadAnimSeq(p(cyc),"Characters/Sequences/Standard06.3ds") 
 pSeq(cyc,610)=LoadAnimSeq(p(cyc),"Characters/Sequences/Standard10.3ds") 
 ;sitting animations
 pSeq(cyc,0)=ExtractAnimSeq(p(cyc),2090,2100,pSeq(cyc,604)) ;dead!
 pSeq(cyc,1)=ExtractAnimSeq(p(cyc),1480,1520,pSeq(cyc,604)) ;sitting at desk (hands spread)
 pSeq(cyc,2)=ExtractAnimSeq(p(cyc),2280,2320,pSeq(cyc,604)) ;sitting at desk (hands overlapped)
 pSeq(cyc,3)=ExtractAnimSeq(p(cyc),2330,2370,pSeq(cyc,604)) ;sitting at desk (hands clasped)
 pSeq(cyc,4)=ExtractAnimSeq(p(cyc),2180,2220,pSeq(cyc,604)) ;sitting rubbing chin
 pSeq(cyc,5)=ExtractAnimSeq(p(cyc),2230,2270,pSeq(cyc,604)) ;sitting arms folded
 pSeq(cyc,6)=ExtractAnimSeq(p(cyc),2480,2520,pSeq(cyc,604)) ;sitting relaxed
 pSeq(cyc,7)=ExtractAnimSeq(p(cyc),2430,2470,pSeq(cyc,604)) ;sitting hands behind head
 pSeq(cyc,8)=ExtractAnimSeq(p(cyc),2380,2420,pSeq(cyc,604)) ;sitting arms on sides
 pSeq(cyc,9)=ExtractAnimSeq(p(cyc),1530,1570,pSeq(cyc,604)) ;sitting cross-legged
 ;standing animations
 pSeq(cyc,10)=ExtractAnimSeq(p(cyc),1730,1760,pSeq(cyc,601)) ;standing straight
 pSeq(cyc,11)=ExtractAnimSeq(p(cyc),2110,2170,pSeq(cyc,604)) ;standing rubbing chin
 pSeq(cyc,12)=ExtractAnimSeq(p(cyc),945,1005,pSeq(cyc,606)) ;standing hands on hips
 pSeq(cyc,13)=ExtractAnimSeq(p(cyc),2020,2080,pSeq(cyc,604)) ;standing folded arms
 pSeq(cyc,14)=ExtractAnimSeq(p(cyc),495,675,pSeq(cyc,610)) ;court speech
 pSeq(cyc,20)=ExtractAnimSeq(p(cyc),70,110,pSeq(cyc,604)) ;injured stance
 pSeq(cyc,21)=ExtractAnimSeq(p(cyc),1625,1665,pSeq(cyc,605)) ;feminine
 If pAnim(cyc)=>10 And charGender(pChar(cyc))=1 Then pAnim(cyc)=21
 ;weary
 weary=0
 If InjuryStatus(char)>0 Or charHealth(char)<25 Then weary=1
 ;If screen=53 And char=gamChar And (negTopic=40 Or negTopic=73 Or negTopic=76) Then weary=1
 If pAnim(cyc)=>10 And weary=1 Then pAnim(cyc)=20
 ;dead!
 If pAnim(cyc)<10 And charFed(char)=9 And char<>fedBooker(9)
   pAnim(cyc)=0
   For limb=0 To 50
    If pLimb(cyc,limb)>0
     If limb=<4 Then EntityTexture pLimb(cyc,limb),tFaceScar(Rnd(1,4)),0,5
     If limb=5 Then EntityTexture pLimb(cyc,limb),tBodyScar(Rnd(1,4)),0,5
     If limb=>6 And limb=<31 Then EntityTexture pLimb(cyc,limb),tArmScar(Rnd(1,4)),0,5
     If limb=>36 And limb=<44 Then EntityTexture pLimb(cyc,limb),tLegScar(Rnd(1,4)),0,5
    EndIf
   Next
 EndIf
 Animate p(cyc),1,Rnd#(0.1,0.3),pSeq(cyc,pAnim(cyc)),0
 pState(cyc)=1
 ;orientation
 PositionEntity p(cyc),pX#(cyc),pY#(cyc),pZ#(cyc)
 RotateEntity p(cyc),0,pA#(cyc),0
 scaler#=0.055+(GetPercent#(charHeight(char),24)/10000)
 If pAnim(cyc)<10 Then scaler#=0.06
 ScaleEntity p(cyc),scaler#,scaler#,scaler# 
 ;shadows
 LoadShadows(cyc)
End Function

;----------------------------------------------------------------------------
;//////////////////////// 53. MEETING PROCESS ///////////////////////////////
;----------------------------------------------------------------------------
Function Meeting()
;loading message
Loader("Please Wait","Meeting "+charName$(negChar))
;ChannelVolume chTheme,0.5
;costume suggestion
If negTopic=15 Or negTopic=16
 CopyChar(negChar,0)
 For coz=1 To 3
  Repeat 
   idol=Rnd(1,no_chars)
  Until idol<>negChar
  If negTopic=16 Then idol=charPartner(negChar)
  charBaggy(negChar,coz)=charBaggy(idol,coz)
  charHatStyle(negChar,coz)=charHatStyle(idol,coz)
  charHat(negChar,coz)=charHat(idol,coz)
  charSpecs(negChar,coz)=charSpecs(idol,coz)
  If charBody(negChar,coz)>5 Or charBody(idol,coz)>5 Then charBody(negChar,coz)=charBody(idol,coz)
  charLeftArm(negChar,coz)=charLeftArm(idol,coz)
  charRightArm(negChar,coz)=charRightArm(idol,coz) 
  charLeftForearm(negChar,coz)=charLeftForearm(idol,coz)
  charRightForearm(negChar,coz)=charRightForearm(idol,coz) 
  charLeftHand(negChar,coz)=charLeftHand(idol,coz)
  charRightHand(negChar,coz)=charRightHand(idol,coz)  
  charShorts(negChar,coz)=charShorts(idol,coz)
  charLegs(negChar,coz)=charLegs(idol,coz)
  charShins(negChar,coz)=charShins(idol,coz)
  charShoes(negChar,coz)=charShoes(idol,coz)
 Next
EndIf
;hairstyle suggestion
If negTopic=17
 CopyChar(negChar,0)
 its=0
 Repeat 
  idol=Rnd(1,no_chars) : its=its+1
 Until (idol<>negChar And GetRace(idol)=GetRace(negChar)) Or its>1000
 charHair(negChar,1)=charHair(idol,1)
 charHatStyle(negChar,1)=0
 charHairStyle(negChar,1)=Rnd(0,no_hairstyles)
 For coz=2 To 3
  charHatStyle(negChar,coz)=charHatStyle(negChar,1)
  charHairStyle(negChar,coz)=charHairStyle(negChar,1)
  charHair(negChar,coz)=charHair(negChar,1)
 Next
EndIf
;get subject of media interest
If negTopic=56 Or negTopic=57
 its=0
 Repeat
  satisfied=1 : its=its+1
  negVariable=fedRoster(fed,Rnd(1,fedSize(fed)))
  randy=Rnd(0,2)
  If randy>0 And charPopularity(negVariable)<fedPopularity(fed)-5 And TitleHolder(negVariable,0)=0 Then satisfied=0
  If charVacant(negVariable)>0 Or InjuryStatus(negVariable)>0 Then satisfied=0
  If negVariable=gamChar Then satisfied=0
 Until satisfied=1 Or its>1000
EndIf
;load setting
If negSetting>1
 If negSetting=3 Then negSetting=Rnd(6,13) Else negSetting=Rnd(2,13)
 If gamSchedule(gamDate)=<0 Then negSetting=Rnd(2,5)
EndIf
If negSetting<1 Then negSetting=1
PrepareMeeting()
;reset situation
negStage=0 : negTim=0
negVerdict=0
;find champion criticism
If negTopic=4 Or negTopic=5 
 champ=fedChampWorld(fed)
 If negTopic=5 Then champ=fedChampInter(fed) 
 Repeat
  negSubTopic=FindStrength(negChar,champ)
 Until negSubTopic<>8 And negSubTopic<>9 And negSubTopic<>10 And negSubTopic<>13
EndIf
;get character references
If negTopic=7 Or negTopic=9 Or negTopic=35 Then negVariable=FindFriend(negChar,-1)
If negTopic=8 Or negTopic=10 Then negVariable=FindRealFriend(negChar,-1)
If negTopic=47 Or negTopic=48 Then negVariable=FindRealEnemy(negChar,-1)
If negTopic=49
 its=0
 Repeat
  negVariable=Rnd(1,no_chars) : its=its+1
 Until (charRealRelationship(negChar,negVariable)>0 And charFed(negVariable)=<8 And charFed(negVariable)<>charFed(negChar) And ImportantChar(negVariable)=0) Or its>1000
EndIf
;invading promotion
If negTopic=51
 Repeat
  negVariable=Rnd(1,6)
 Until negVariable<>charFed(gamChar)
EndIf
;get name suggestions
If negTopic=13 Or negTopic=14 
 charName$(0)=GenerateName$(negChar)
 charTeamName$(0)=textTeamName$(Rnd(1,no_teamnames))
EndIf
;production suggestion
If negTopic=22 Or negTopic=113
 Repeat
  negSubTopic=Rnd(1,12)
 Until fedProduction(fed,negSubTopic)=0
EndIf
;get target character (to buy)
If negTopic=106
 Repeat
  gamTarget=Rnd(1,no_chars)
 Until charFed(gamTarget)=<8 And charFed(gamTarget)<>fed And gamTarget<>fedBooker(charFed(gamTarget)) And ImportantChar(gamTarget)=0
EndIf
;get target character (to remove)
If negTopic=107
 Repeat
  gamTarget=fedRoster(fed,Rnd(1,fedSize(fed)))
 Until gamTarget<>gamChar
EndIf
;get target character (to crown)
If negTopic=108 Or negTopic=109 Or negTopic=110
 Repeat
  gamTarget=fedRoster(fed,Rnd(1,fedSize(fed)))
 Until gamTarget<>gamChar And TitleHolder(gamTarget,0)=0
EndIf
;bribe amount
negPayOff=fedBank(charFed(gamChar))/20
;negPayOff=Rnd(negPayOff/2,negPayOff*2)
If negTopic=36 Or negTopic=38 Or negTopic=39 Or negTopic=44 Then negPayOff=10000 ;surgery costs
If negTopic=37 Then negPayOff=1000 ;painkiller costs
If negTopic=43 Then negPayOff=charWorth(negChar)*2 ;wrestler bribe
If negTopic=52 Then negPayOff=fedBank(charFed(gamChar))/10 ;fine
If negTopic=56
 negPayOff=((charPopularity(negVariable)-30)*(charPopularity(negVariable)-30))*2 ;minor media fee
EndIf
If negTopic=57 
 negPayOff=((charPopularity(negVariable)-30)*(charPopularity(negVariable)-30))*10 ;major media fee
EndIf
If negTopic=58 Or negTopic=59 ;media costs 
 negPayOff=fedBank(charFed(gamChar))/20 
 If negPayOff<10000 Then negPayOff=10000
EndIf
If negTopic=60 Then negPayOff=fedBank(charFed(gamChar))/20 ;damages
negPayOff=RoundOff(negPayOff,100)
If negPayOff<1000 Then negPayOff=1000
If negPayOff>100000 Then negPayOff=100000
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
foc=1 : oldfoc=foc : charged=0
go=0 : gotim=-20 : keytim=20
While go=0

 Cls
 frames=WaitTimer(timer)
 For framer=1 To frames
	
	;timers
	keytim=keytim-1
	If keytim<1 Then keytim=0
	
	;PORTAL 
    gotim=gotim+1
    If gotim>25 Then negTim=negTim+1 
    ;speed-ups
    If gotim>0 And negStage<>1 And negStage<>3 And keytim=0
	 If KeyDown(1) Or KeyDown(28) Or ButtonPressed() Or MouseDown(1) Then negTim=negTim+50 : keytim=5 ;: PlaySound sMenuBrowse
	EndIf
	
	;CHOICE CONFIRMATION
	If keytim=0 And negStage=1
	 ;highlight option
	 If KeyDown(200) Or JoyYDir()=-1 Then foc=foc-1 : keytim=10
	 If KeyDown(208) Or JoyYDir()=1 Then foc=foc+1 : keytim=10
	 If foc>2 Then foc=2
	 If foc<1 Then foc=1 
	 ;proceed 
     If KeyDown(1) Or KeyDown(28) Or ButtonPressed() Or MouseDown(1)
	  PlaySound sMenuGo : keytim=20
	  negStage=2 : negTim=0
	  If foc=1 Then negVerdict=1
	  If foc=2 Or KeyDown(1) Then negVerdict=-1
	 EndIf
	EndIf
	
	;SPEAKING
	For cyc=1 To no_plays
	 If pChar(cyc)>0
	  ;facial expressions
	  RandomizeAnimation(cyc)
	  FacialExpressions(cyc)
	  ManageEyes(cyc)
	  ;shadows
	  For limb=1 To 50
       If pShadow(cyc,limb)>0
        RotateEntity pShadow(cyc,limb),90,EntityYaw(pLimb(cyc,limb),1),0
        PositionEntity pShadow(cyc,limb),EntityX(pLimb(cyc,limb),1),pY#(cyc)+0.4,EntityZ(pLimb(cyc,limb),1)
       EndIf
      Next
     EndIf 
	Next
	
	;CAMERA
	Camera()
	;music
	ManageMusic(-1)  
	
 UpdateWorld
 Next  
 
 ;ANIMATION OVERRIDE
 pFoc(1)=2 :  pFoc(2)=1
 For cyc=1 To 2
  If pFoc(cyc)>0
   If negSetting=>2 Then PointHead(cyc,pLimb(pFoc(cyc),1))
   LookAtPerson(cyc,pFoc(cyc))
  Else
   RotateEntity pLimb(cyc,45),0,0,0
   RotateEntity pLimb(cyc,46),0,0,0
  EndIf
  If charEyeShape(pChar(cyc))=112 Then LookAtPerson(cyc,cyc)
 Next

 RenderWorld 1

 ;DISPLAY
 DrawImage gLogo(2),rX#(400),rY#(65)
 ;reset expressions
 For cyc=1 To no_plays
  pSpeaking(cyc)=0
 Next
 ;reset subtitles
 lineA$="" : lineB$=""
 redLineA$="" : redLineB$=""
 greenLineA$="" : greenLineB$=""
 ;------------------------ TOPICS ----------------------------
 ;1. RETIREMENT CONFIRMATION
 If negTopic=1
  optionA$="Yes, retire..." : optionB$="No, keep working!"
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,1)
   lineA$="Hey, "+charName$(gamChar)+", what's all this I hear about"
   lineB$="retirement?! Are you sure you want to leave us?"
  EndIf
  If negStage=0 And negTim>325 Then camFoc=negStar
  If negStage=0 And negTim>350 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1 And CountExperience(gamChar,0)<48
   Speak(negVisitor,0,1)
   lineA$="If that's what you want, I guess there's nothing left"
   lineB$="to say. Some people just haven't got what it takes..."
  EndIf
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1 And CountExperience(gamChar,0)=>48
   Speak(negVisitor,0,3)
   lineA$="In that case, all that remains is to wish you luck."
   lineB$="Perhaps we'll see you back in the ring some time!"
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(negVisitor,0,1)
   lineA$="Good, I'll be damned if anybody leaves this place"
   lineB$="without me firing them! Now get back to work..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;2. WRESTLER ASKS TO TURN HEEL
 If negTopic=2
  optionA$="Yes, turn Heel!" : optionB$="No, stay Face..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,2)
   lineA$="Listen, "+charName$(gamChar)+", I'd like to talk to you"
   lineB$="about taking my career in a new direction..."
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,3)
   lineA$="Don't you think it's time I turned against the fans?"
   lineB$="I figure I'd be much more effective in that role!"
  EndIf
  If negStage=0 And negTim>650 Then camFoc=negStar
  If negStage=0 And negTim>675 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 
    ChangeAllegiance(negChar,1) : charAgreement(negChar,8)=4
    AdjustAttitude(negChar,-1) : charged=1
   EndIf
   lineA$="Great, it'll be much more fun to play a villain!"
   lineB$="It should open up some new possibilities too..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)=0
   Speak(negVisitor,0,1)
   If charged=0 Then AdjustAttitude(negChar,1) : charged=1
   lineA$="Alright, it was only a suggestion! We need to do"
   lineB$="something to keep people interested in my career..."
  EndIf
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)>0
   Speak(negVisitor,0,1)
   If charged=0 
    ChangeAllegiance(negChar,1) : charAgreement(negChar,8)=4
    AdjustAttitude(negChar,-2) : charged=1
   EndIf
   lineA$="Well it's a good job I don't need your approval!"
   lineB$="I want to play Heel, so get used to the idea..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;3. WRESTLER ASKS TO TURN FACE
 If negTopic=3
  optionA$="Yes, turn Face!" : optionB$="No, stay Heel..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,2)
   lineA$="Listen, "+charName$(gamChar)+", I'd like to talk to you"
   lineB$="about taking my career in a new direction..."
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,3)
   lineA$="How would you feel about me becoming a fan favourite?"
   lineB$="I figure I'd be much more effective in that role!"
  EndIf
  If negStage=0 And negTim>650 Then camFoc=negStar
  If negStage=0 And negTim>675 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 
    ChangeAllegiance(negChar,0) : charAgreement(negChar,8)=4
    AdjustAttitude(negChar,-1) : charged=1
   EndIf
   lineA$="Great, this could be just the break I need! I'm sure"
   lineB$="the fans will appreciate the chance to get behind me..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)>0
   Speak(negVisitor,0,1)
   If charged=0 Then AdjustAttitude(negChar,1) : charged=1
   lineA$="Alright, it was only a suggestion! We need to do"
   lineB$="something to keep people interested in my career..."
  EndIf
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)=0
   Speak(negVisitor,0,1)
   If charged=0 
    ChangeAllegiance(negChar,0) : charAgreement(negChar,8)=4
    AdjustAttitude(negChar,-2) : charged=1
   EndIf
   lineA$="Well it's a good job I don't need your approval!"
   lineB$="I want to turn Face, so get used to the idea..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;4/5. WRESTLER ASKS FOR WORLD/INTER TITLE RUN
 If negTopic=4 Or negTopic=5
  g=charGender(fedChampWorld(fed))
  If negTopic=5 Then g=charGender(fedChampInter(fed))
  optionA$="Yes, promise title run!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,2)
   lineA$="Hi, "+charName$(gamChar)+". Sorry to bother you,"
   lineB$="but I'd like to make a suggestion..."
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,1)
   If negTopic=5 Then champ=fedChampInter(fed) Else champ=fedChampWorld(fed)
   If negSubTopic=0 Then lineA$="Everybody knows "+charName$(champ)+" isn't good enough"
   If negSubTopic=1 Then lineA$="I'm not sure that "+charName$(champ)+" is strong enough"
   If negSubTopic=2 Then lineA$="I don't think "+charName$(champ)+" is talented enough"
   If negSubTopic=3 Then lineA$="I'm not sure that "+charName$(champ)+" is agile enough"
   If negSubTopic=4 Then lineA$="I'm not sure that "+charName$(champ)+" is fit enough" 
   If negSubTopic=5 Then lineA$="I'm not sure that "+charName$(champ)+" is tough enough"
   If negSubTopic=6 Then lineA$="Everybody knows "+charName$(champ)+" isn't good enough"
   If negSubTopic=7 Then lineA$="I don't think "+charName$(champ)+" is popular enough"
   If negSubTopic=11 Then lineA$="I don't think "+charName$(champ)+" is experienced enough"
   If negSubTopic=12 Then lineA$="Everybody knows that "+charName$(champ)+" is too old"
   If negSubTopic=14 Then lineA$="I'm not sure that "+charName$(champ)+" is reliable enough"
   If negTopic=4 Then lineB$="to be the World Champion! Can't I get a shot?" 
   If negTopic=5 Then lineB$="to be the Inter Champion! Can't I get a shot?"
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,3)
   lineA$="Give me a run with the title and I'll make"
   lineB$="it worth something again! What do you say?"
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 Then charAgreement(negChar,7+negTopic)=4 : AdjustAttitude(negChar,-1) : charged=1
   lineA$="Great, I can't wait to get my hands on the title!"
   lineB$="Let's make sure it happens within the next month..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 ;And charClause(negChar,1)=<1
   Speak(negVisitor,0,1)
   If charged=0 Then AdjustAttitude(negChar,1) : charged=1
   lineA$="Well good luck with the current 'champion'!"
   lineB$=He$(g)+"'s doing more harm to the show than good..."
  EndIf
  ;If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)=2
   ;Speak(negVisitor,0,1)
   ;If charged=0 Then charAgreement(negChar,7+negTopic)=4 : AdjustAttitude(negChar,-2) : charged=1
   ;lineA$="Hey, it's written right here in my contract!"
   ;lineB$="What I say goes - and I want that title!"
  ;EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;6. WRESTLER ASKS FOR TAG TITLE RUN
 If negTopic=6
  optionA$="Yes, promise title run!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,2)
   lineA$="Hi, "+charName$(gamChar)+". Sorry to bother you,"
   lineB$="but I'd like to make a suggestion..."
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,1)
   lineA$=charName$(fedChampTag(fed,1))+" and "+charName$(fedChampTag(fed,2))+" are making a"
   lineB$="mockery of the Tag titles! We need new blood..."
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,3)
   lineA$="Me and "+charName$(charPartner(negChar))+" have been working well"
   lineB$="together. Can't we get a run with the titles?"
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 Then charAgreement(negChar,13)=4 : AdjustAttitude(negChar,-1) : charged=1
   lineA$="Great, we can't wait to get our hands on the titles!"
   lineB$="Let's make sure it happens within the next month..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 ;And charClause(negChar,1)=<1
   Speak(negVisitor,0,1)
   If charged=0 Then AdjustAttitude(negChar,1) : charged=1
   lineA$="Well good luck with the current 'champions'!"
   lineB$="They're doing more harm to the scene than good..."
  EndIf
  ;If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)=2
   ;Speak(negVisitor,0,1)
   ;If charged=0 Then charAgreement(negChar,13)=4 : AdjustAttitude(negChar,-2) : charged=1
   ;lineA$="Hey, it's written right here in my contract!"
   ;lineB$="What I say goes - and I want those titles!"
  ;EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;7. WRESTLER ASKS TO TEAM WITH STORY FRIEND
 If negTopic=7
  optionA$="Yes, form team!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,2)
   lineA$="Hi, "+charName$(gamChar)+". Sorry to bother you,"
   lineB$="but I'd like to make a suggestion..."
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,3)
   lineA$=charName$(negVariable)+" is an established ally of mine,"
   lineB$="so why don't the two of us form a proper team?"
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,3)
   lineA$="Working together could magnify our strengths"
   lineB$="and hide our weaknesses! What do you think?"
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 
    FormTeam(negChar,negVariable) : charAgreement(negChar,14)=4
    AdjustAttitude(negChar,-1) : charged=1
   EndIf 
   lineA$="Great, I'm sure "+charName$(negVariable)+" will be thrilled!"
   lineB$="This could be the start of something big..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)=0
   Speak(negVisitor,0,1)
   If charged=0 Then AdjustAttitude(negChar,1) : charged=1
   lineA$="What's the point in being allies if we hardly"
   lineB$="ever work together?! It's a wasted opportunity..."
  EndIf
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)>0
   Speak(negVisitor,0,1)
   If charged=0 
    FormTeam(negChar,negVariable) : charAgreement(negChar,14)=4
    AdjustAttitude(negChar,-2) : charged=1
   EndIf 
   lineA$="You haven't got the right to tell us what to do!"
   lineB$="If we want to team together then we will..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;8. WRESTLER ASKS TO TEAM WITH REAL FRIEND
 If negTopic=8
  optionA$="Yes, form team!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,2)
   lineA$="Hi, "+charName$(gamChar)+". Sorry to bother you,"
   lineB$="but I'd like to make a suggestion..."
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,3)
   lineA$="Everyone knows I'm friends with "+charName$(negVariable)
   lineB$="in real life, so why don't we team up on screen?"
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,3)
   lineA$="Most teams have to force chemistry, but with us"
   lineB$="it's already there! Why don't we give it a try?"
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 
    FormTeam(negChar,negVariable) : charAgreement(negChar,14)=4
    AdjustAttitude(negChar,-1) : charged=1
   EndIf 
   lineA$="Great, I'm sure "+charName$(negVariable)+" will be as pleased as I am!"
   lineB$="The fans will get a kick out of seeing us together too..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)=0
   Speak(negVisitor,0,1)
   If charged=0 Then AdjustAttitude(negChar,1) : charged=1
   lineA$="Why would you ignore a readymade friendship?!"
   lineB$="The fans would love to see us work together..."
  EndIf
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)>0
   Speak(negVisitor,0,1)
   If charged=0 
    FormTeam(negChar,negVariable) : charAgreement(negChar,14)=4
    AdjustAttitude(negChar,-2) : charged=1
   EndIf 
   lineA$="You haven't got the right to tell us what to do!"
   lineB$="If we want to team together then we will..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;9/10. WRESTLER ASKS TO BE MANAGED BY FRIEND
 If negTopic=9 Or negTopic=10
  g=charGender(negVariable)
  optionA$="Yes, assign a manager!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,2)
   lineA$="Hi, "+charName$(gamChar)+". Sorry to bother you,"
   lineB$="but I'd like to make a suggestion..."
  EndIf
  If negStage=0 And negTim>350 And negTim<650 And negTopic=9
   Speak(negVisitor,0,3)
   lineA$=charName$(negVariable)+" is an established ally of mine,"
   lineB$="so why don't we assign "+Him$(g)+" as my manager?"
  EndIf
  If negStage=0 And negTim>350 And negTim<650 And negTopic=10
   Speak(negVisitor,0,3)
   lineA$="Everyone knows I'm friends with "+charName$(negVariable)+" in"
   lineB$="real life, so why don't we assign "+Him$(g)+" as my manager?"
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,3)
   lineA$="It would give "+Him$(g)+" something to do AND make"
   lineB$="my matches more interesting at the same time!"
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 
    ChangeRelationship(negChar,negVariable,1)
    charManager(negChar)=negVariable : charAgreement(negChar,15)=4
    AdjustAttitude(negChar,-1) : charged=1
   EndIf 
   lineA$="Great, I'm sure "+charName$(negVariable)+" will be thrilled!"
   lineB$="I can't wait to see what an impact "+Lower$(He$(g))+" makes..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)=0
   Speak(negVisitor,0,1)
   If charged=0 Then AdjustAttitude(negChar,1) : charged=1
   lineA$="What's the point in being friends if we hardly"
   lineB$="ever work together?! It's a wasted opportunity..."
  EndIf
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)>0
   Speak(negVisitor,0,1)
   If charged=0 
    ChangeRelationship(negChar,negVariable,1)
    charManager(negChar)=negVariable : charAgreement(negChar,15)=4
    AdjustAttitude(negChar,-2) : charged=1
   EndIf 
   lineA$="You haven't got the right to tell us what to do!"
   lineB$="If we want to work together then we will..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;11. WRESTLER ASKS TO SPLIT FROM PARTNER
 If negTopic=11 
  g=charGender(charPartner(negChar))
  optionA$="Yes, separate team..." : optionB$="No, stay together!"
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,1)
   lineA$="Sorry, "+charName$(gamChar)+", but I'm really not happy"
   lineB$="about teaming with "+charName$(charPartner(negChar))+" anymore!"
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,1)
   lineA$="Having a partner makes me look weak! Do you"
   lineB$="mind if we go our separate ways for a while?"
  EndIf
  If negStage=0 And negTim>650 Then camFoc=negStar
  If negStage=0 And negTim>675 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 
    charPartner(charPartner(negChar))=0
    charPartner(negChar)=0 : charAgreement(negChar,14)=4
    AdjustAttitude(negChar,2) : charged=1
   EndIf 
   lineA$="Good, I'm sure that this is for the best!"
   lineB$="I can't wait to stand on my own two feet..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)=0
   Speak(negVisitor,0,1)
   If charged=0 
    ChangeRealRelationship(negChar,charPartner(negChar),-1)
    AdjustAttitude(negChar,-2) : charAgreement(negChar,14)=4 : charged=1
   EndIf
   lineA$="Alright, but don't blame me when it hits the fan!"
   lineB$="If you force us together than we'll fall out..."
  EndIf
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)>0
   Speak(negVisitor,0,1)
   If charged=0 
    charPartner(charPartner(negChar))=0
    charPartner(negChar)=0 : charAgreement(negChar,14)=4
    AdjustAttitude(negChar,-2) : charged=1
   EndIf 
   lineA$="You haven't got the right to tell me what to do!"
   lineB$="If I don't want a partner then I won't have one..."
  EndIf
  If negStage=2 And negTim>375 Then go=1
 EndIf
 ;12. WRESTLER ASKS TO SPLIT FROM MANAGER
 If negTopic=12 
  g=charGender(charManager(negChar))
  optionA$="Yes, remove manager..." : optionB$="No, stay together!"
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,1)
   lineA$="Sorry, "+charName$(gamChar)+", but I'm really not happy"
   lineB$="about having "+charName$(charManager(negChar))+" as my manager!"
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,1)
   lineA$="I look stupid with a babysitter at ringside! Do"
   lineB$="you mind if I strike out on my own for a while?"
  EndIf
  If negStage=0 And negTim>650 Then camFoc=negStar
  If negStage=0 And negTim>675 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 
    charManager(negChar)=0 : charAgreement(negChar,15)=4
    AdjustAttitude(negChar,2) : charged=1
   EndIf 
   lineA$="Good, I'm sure that this is for the best!"
   lineB$="I can't wait to stand on my own two feet..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)=0
   Speak(negVisitor,0,1)
   If charged=0 
    ChangeRealRelationship(negChar,charManager(negChar),-1)
    AdjustAttitude(negChar,-2) : charAgreement(negChar,15)=4 : charged=1
   EndIf
   lineA$="Alright, but don't blame me when it hits the fan!"
   lineB$="If you force us together than we'll fall out..."
  EndIf
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)>0
   Speak(negVisitor,0,1)
   If charged=0 
    charManager(negChar)=0 : charAgreement(negChar,15)=4
    AdjustAttitude(negChar,-2) : charged=1
   EndIf 
   lineA$="You haven't got the right to tell me what to do!"
   lineB$="If I don't want a manager then I won't have one..."
  EndIf
  If negStage=2 And negTim>375 Then go=1
 EndIf
 ;13. WRESTLER SUGGESTS SOLO NAME CHANGE
 If negTopic=13 
  execute=0
  optionA$="Yes, change name!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,2)
   lineA$="Listen, "+charName$(gamChar)+", I'd like to talk to you"
   lineB$="about taking my career in a new direction..."
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,3)
   lineA$="I've been thinking of wrestling under a different"
   lineB$="name! How does '"+charName$(0)+"' sound to you?"
  EndIf
  If negStage=0 And negTim>650 Then camFoc=negStar
  If negStage=0 And negTim>675 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 Then execute=1 : AdjustAttitude(negChar,-1) : charged=1
   lineA$="Cool, I'm glad you agree! Wrestling under a new"
   lineB$="identity will be like being born all over again..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)=0
   Speak(negVisitor,0,1)
   If charged=0 Then AdjustAttitude(negChar,1) : charged=1
   lineA$="What's wrong with the name '"+charName$(0)+"'?!"
   lineB$="You're just jealous you didn't come up with it..."
  EndIf
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)>0
   Speak(negVisitor,0,1)
   If charged=0 Then execute=1 : AdjustAttitude(negChar,-2) : charged=1
   lineA$="Well it's a good job I don't need your approval!"
   lineB$="If I want to change my name then I will..."
  EndIf
  If negStage=2 And negTim>375 Then go=1
  ;execute changes
  If execute=1
   charName$(negChar)=charName$(0) : charAgreement(negChar,1)=4
   charTeamName$(negChar)=charName$(0)+"'s Team"
  EndIf
 EndIf
 ;14. WRESTLER SUGGESTS TEAM NAME CHANGE
 If negTopic=14 
  execute=0
  optionA$="Yes, change name!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,2)
   lineA$="Listen, I'm not sure '"+charTeamName$(negChar)+"' sums"
   lineB$="up what my team with "+charName$(charPartner(negChar))+" is about..."
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,3)
   lineA$="Why can't we have a proper name like '"+charTeamName$(0)+"'?"
   lineB$="We'd be taken much more seriously as a team that way!"
  EndIf
  If negStage=0 And negTim>650 Then camFoc=negStar
  If negStage=0 And negTim>675 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 Then execute=1 : AdjustAttitude(negChar,-1) : charged=1
   lineA$="I'm glad you agree! Operating under our own"
   lineB$="identity will make us feel like a proper team..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)=0
   Speak(negVisitor,0,1)
   If charged=0 Then AdjustAttitude(negChar,1) : charged=1
   lineA$="What's wrong with the name '"+charTeamName$(0)+"'?!"
   lineB$="You're just jealous you didn't come up with it..."
  EndIf
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)>0
   Speak(negVisitor,0,1)
   If charged=0 Then execute=1 : AdjustAttitude(negChar,-2) : charged=1
   lineA$="Well it's a good job we don't need your approval!"
   lineB$="It's our team and we'll call it whatever we want..."
  EndIf
  If negStage=2 And negTim>375 Then go=1
  ;execute changes
  If execute=1
   charTeamName$(negChar)=charTeamName$(0) : charAgreement(negChar,1)=4
   charTeamName$(charPartner(negChar))=charTeamName$(negChar)
  EndIf
 EndIf
 ;15. WRESTLER SUGGESTS COSTUME CHANGE
 If negTopic=15 
  optionA$="Yes, change costume!" : optionB$="No, take it off..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,3)
   lineA$="Hey, "+charName$(gamChar)+", what do you think of"
   lineB$="this new costume that I put together?"
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,3)
   lineA$="Shall I start dressing like this in my matches?"
   lineB$="It could be just what I need to reinvent myself!"
  EndIf
  If negStage=0 And negTim>650 Then camFoc=negStar
  If negStage=0 And negTim>675 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 Then charAgreement(negChar,3)=4 : AdjustAttitude(negChar,-1) : charged=1
   lineA$="I'm glad you agree! If you like that, just"
   lineB$="wait until you see the other outfits I got..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)=0
   Speak(negVisitor,0,1)
   If charged=0 Then CopyChar(0,negChar) : AdjustAttitude(negChar,1) : charged=1
   lineA$="Hey, it's better than the crap YOU make us wear!"
   lineB$="The fans are dying to see some new fashions..."
  EndIf
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)>0
   Speak(negVisitor,0,1)
   If charged=0 Then charAgreement(negChar,3)=4 : AdjustAttitude(negChar,-2) : charged=1
   lineA$="Well it's a good job I don't need your approval!"
   lineB$="If I want to dress like this then I will..."
  EndIf
  If negStage=2 And negTim>375 Then go=1
 EndIf
 ;16. TEAM WANT TO SYCHRONIZE COSTUMES
 If negTopic=16 
  optionA$="Yes, change costume!" : optionB$="No, take it off..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,3)
   lineA$="Hey, "+charName$(gamChar)+", how do you think I"
   lineB$="look in "+charName$(charPartner(negChar))+"'s costume?"
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,3)
   lineA$="Since we're a team, I figure we should start"
   lineB$="dressing like one! Shall I keep it like this?"
  EndIf
  If negStage=0 And negTim>650 Then camFoc=negStar
  If negStage=0 And negTim>675 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 Then charAgreement(negChar,3)=4 : AdjustAttitude(negChar,-1) : charged=1
   lineA$="I'm glad you agree! We'll be taken much more"
   lineB$="seriously as a team if we look like one..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)=0
   Speak(negVisitor,0,1)
   If charged=0 Then CopyChar(0,negChar) : AdjustAttitude(negChar,1) : charged=1
   lineA$="What's the point in being a team if we don't look"
   lineB$="like one?! We'll never be taken seriously that way..."
  EndIf
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)>0
   Speak(negVisitor,0,1)
   If charged=0 Then charAgreement(negChar,3)=4 : AdjustAttitude(negChar,-2) : charged=1
   lineA$="Well it's a good job we don't need your approval!"
   lineB$="If we want to dress the same then we will..."
  EndIf
  If negStage=2 And negTim>375 Then go=1
 EndIf
 ;17. WRESTLER SUGGESTS HAIRSTYLE CHANGE
 If negTopic=17 
  execute=0
  optionA$="Yes, keep hairstyle!" : optionB$="No, change it back..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,3)
   lineA$="Hey, "+charName$(gamChar)+", what do you think of"
   lineB$="this new hairstyle that I put together?"
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,3)
   lineA$="Shall I start wearing it like this in my matches?"
   lineB$="It could be just what I need to reinvent myself!"
  EndIf
  If negStage=0 And negTim>650 Then camFoc=negStar
  If negStage=0 And negTim>675 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 Then execute=1 : AdjustAttitude(negChar,-1) : charged=1
   lineA$="I'm glad you agree. I've always wanted to try"
   lineB$="it like this! I feel like a different person..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)=0
   Speak(negVisitor,0,1)
   If charged=0 Then CopyChar(0,negChar) : AdjustAttitude(negChar,1) : charged=1
   lineA$="Hey, it's better than what's on YOUR head!"
   lineB$="You wouldn't know what a good hairstyle is..."
  EndIf
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)>0
   Speak(negVisitor,0,1)
   If charged=0 Then execute=1 : AdjustAttitude(negChar,-2) : charged=1
   lineA$="Well it's too late for that now isn't it! You"
   lineB$="should have said something while I was cutting it..."
  EndIf
  If negStage=2 And negTim>375 Then go=1
  ;execute changes
  If execute=1
   For coz=1 To 3
    charHatStyle(negChar,coz)=charHatStyle(0,coz)
   Next 
   charAgreement(negChar,2)=4
  EndIf
 EndIf
 ;18. WRESTLER SUGGESTS ENTRANCE CHANGE
 If negTopic=18 
  execute=0
  optionA$="Yes, change entrance!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,3)
   lineA$="Hey, "+charName$(gamChar)+", I've discovered a great"
   lineB$="new piece of music to make my entrance to!"
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,3)
   lineA$="Do you mind if we introduce it in my next match?"
   lineB$="We could even update the lighting effects too!"
  EndIf
  If negStage=0 And negTim>650 Then camFoc=negStar
  If negStage=0 And negTim>675 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 Then execute=1 : AdjustAttitude(negChar,-1) : charged=1
   lineA$="I'm glad you're willing to give it a try!"
   lineB$="I'll pass the CD on to the sound engineers..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)=0
   Speak(negVisitor,0,1)
   If charged=0 Then AdjustAttitude(negChar,1) : charged=1
   lineA$="You obviously don't know anything about music!"
   lineB$="This tune would have been a hit with the kids..."
  EndIf
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)>0
   Speak(negVisitor,0,1)
   If charged=0 Then execute=1 : AdjustAttitude(negChar,-2) : charged=1
   lineA$="Well it's a good job I don't need your approval!"
   lineB$="If I want to update my entrance then I will..."
  EndIf
  If negStage=2 And negTim>375 Then go=1
  ;execute changes
  If execute=1
   charTheme(negChar)=Rnd(1,no_themes)
   charThemePitch(negChar)=22050
   charLight(negChar)=Rnd(1,no_lightshows)
   charAgreement(negChar,4)=4
  EndIf
 EndIf
 ;19. WRESTLER SUGGESTS ATTACKS CHANGE
 If negTopic=19 
  execute=0
  optionA$="Yes, change attacks!" : optionB$="No, don't bother..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,3)
   lineA$="Hey, "+charName$(gamChar)+", I've been thinking of"
   lineB$="working some new attacks into my act!"
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,3)
   lineA$="Do you mind if I introduce them in my next match?"
   lineB$="It could be just what I need to make an impact!"
  EndIf
  If negStage=0 And negTim>650 Then camFoc=negStar
  If negStage=0 And negTim>675 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 Then execute=1 : AdjustAttitude(negChar,-1) : charged=1
   lineA$="Great, I can't wait to show you what I can do!"
   lineB$="This is gonna change the way people see me..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)=0
   Speak(negVisitor,0,1)
   If charged=0 Then AdjustAttitude(negChar,1) : charged=1
   lineA$="What's wrong with trying out a few new things?!"
   lineB$="The fans will get bored of me sooner or later..."
  EndIf
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)>0
   Speak(negVisitor,0,1)
   If charged=0 Then execute=1 : AdjustAttitude(negChar,-2) : charged=1
   lineA$="Well it's a good job I don't need your approval!"
   lineB$="If I want to change my fighting style then I will..."
  EndIf
  If negStage=2 And negTim>375 Then go=1
  ;execute changes
  If execute=1
   Repeat 
    idol=Rnd(1,no_chars)
   Until idol<>negChar
   For count=1 To 5
    charAttack(negChar,count)=charAttack(idol,count)
    charCrush(negChar,count)=charCrush(idol,count)
   Next
   charAgreement(negChar,5)=4
  EndIf
 EndIf
 ;20. WRESTLER SUGGESTS MOVES CHANGE
 If negTopic=20 
  execute=0
  optionA$="Yes, change moves!" : optionB$="No, don't bother..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,3)
   lineA$="Hey, "+charName$(gamChar)+", I've been thinking of"
   lineB$="working some new moves into my act!"
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,3)
   lineA$="Do you mind if I introduce them in my next match?"
   lineB$="It could be just what I need to make an impact!"
  EndIf
  If negStage=0 And negTim>650 Then camFoc=negStar
  If negStage=0 And negTim>675 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 Then execute=1 : AdjustAttitude(negChar,-1) : charged=1
   lineA$="Great, I can't wait to show you what I can do!"
   lineB$="This is gonna change the way people see me..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)=0
   Speak(negVisitor,0,1)
   If charged=0 Then AdjustAttitude(negChar,1) : charged=1
   lineA$="What's wrong with trying out a few new things?!"
   lineB$="The fans will get bored of me sooner or later..."
  EndIf
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)>0
   Speak(negVisitor,0,1)
   If charged=0 Then execute=1 : AdjustAttitude(negChar,-2) : charged=1
   lineA$="Well it's a good job I don't need your approval!"
   lineB$="If I want to use different moves then I will..."
  EndIf
  If negStage=2 And negTim>375 Then go=1
  ;execute changes
  If execute=1
   Repeat 
    idol=Rnd(1,no_chars)
   Until idol<>negChar
   For count=1 To 15
    charMove(negChar,count)=charMove(idol,count)
   Next
   For count=1 To 6
    charGroundMove(negChar,count)=charGroundMove(idol,count)
   Next
   charAgreement(negChar,6)=4
  EndIf
 EndIf
 ;21. WRESTLER SUGGESTS GESTURES CHANGE
 If negTopic=21 
  execute=0
  optionA$="Yes, change gestures!" : optionB$="No, don't bother..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,3)
   lineA$="Listen, "+charName$(gamChar)+", I've been thinking of"
   lineB$="changing the way I carry myself in the ring!"
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,3)
   lineA$="A few new gestures could change my whole persona!"
   lineB$="Do you mind if I give it a try in my next match?"
  EndIf
  If negStage=0 And negTim>650 Then camFoc=negStar
  If negStage=0 And negTim>675 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 Then execute=1 : AdjustAttitude(negChar,-1) : charged=1
   lineA$="Great, I can't wait to show you what I mean!"
   lineB$="This is gonna change the way people see me..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)=0
   Speak(negVisitor,0,1)
   If charged=0 Then AdjustAttitude(negChar,1) : charged=1
   lineA$="What's wrong with trying out a few new things?!"
   lineB$="The fans will get bored of me sooner or later..."
  EndIf
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)>0
   Speak(negVisitor,0,1)
   If charged=0 Then execute=1 : AdjustAttitude(negChar,-2) : charged=1
   lineA$="Well it's a good job I don't need your approval!"
   lineB$="If I want to change the way I act then I will..."
  EndIf
  If negStage=2 And negTim>375 Then go=1
  ;execute changes
  If execute=1
   Repeat 
    idol=Rnd(1,no_chars)
   Until idol<>negChar
   charEyes(negChar)=Rnd(1,3)
   charStance(negChar)=Rnd(1,no_stances)
   For count=1 To 4
    charTaunt(negChar,count)=charTaunt(idol,count)
   Next
   charAgreement(negChar,7)=4
  EndIf
 EndIf
 ;22. PRODUCTION SUGGESTION
 If negTopic=22
  optionA$="Yes, produce it!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,2)
   lineA$="Hi, "+charName$(gamChar)+". Sorry to bother you,"
   lineB$="but I'd like to make a suggestion..."
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,1)
   lineA$="I can't believe that "+fedName$(fed)
   lineB$="hasn't developed '"+prodName$(negSubTopic)+"' yet!"
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,3)
   lineA$="It would only cost an extra $"+GetFigure$(prodCost(negSubTopic))+" per week,"
   lineB$="so why don't we get the production team onto it?"
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 
    PlaySound sProduce
    gamBuild=negSubTopic : gamAgreement(13)=1
    AdjustAttitude(negChar,2) : charged=1
   EndIf
   lineA$="I'm glad you agree! Nowadays you need good"
   lineB$="production values just to stay in the race..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(negVisitor,0,1)
   If charged=0 Then AdjustAttitude(negChar,-2) : charged=1
   lineA$="You DON'T want to bring this place up to date?!"
   lineB$="At this rate we'll never be taken seriously..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;23. INTER-PROMOTIONAL AFTERMATH
 If negTopic=23
  optionA$="Yes, accept challenge!" : optionB$="No, we're done..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,1)
   lineA$="Hey, "+charName$(gamChar)+", we're not done with"
   lineB$="you yet! The fans are waiting for more..."
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,1)
   lineA$="What we just had was merely a 'battle'."
   lineB$="The WAR is still left to be fought!"
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,1)
   lineA$="Why don't you rally your pathetic troops"
   lineB$="and face us in one final TEAM contest?"
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,1)
   If charged=0 Then ProduceSound(0,sCrowd(6),0,0.5) : charged=1
   lineA$="Every match so far means NOTHING compared"
   lineB$="to this! Get ready for the main event..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(negVisitor,0,1)
   lineA$="Yeah, you better run away while you still can!"
   lineB$="Nobody can defeat "+fedName$(charFed(negChar))+"..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;24. INTER-PROMOTIONAL CHALLENGE
 If negTopic=24
  optionA$="Yes, accept challenge!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,1)
   lineA$="Hey, "+charName$(gamChar)+", we at "+fedName$(charFed(negChar))
   lineB$="challenge "+fedName$(fed)+" to a showdown!"
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,1)
   lineA$="If you've got the guts, you'll place YOUR best"
   lineB$="guys against OURS in a series of dream matches!"
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,1)
   lineA$="One by one, we'll tear your pathetic champions up"
   lineB$="and show the whole world that WE'RE the best!"
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,1)
   If charged=0
    ProduceSound(0,sCrowd(6),0,0.5)
    gamSchedule(gamDate+2)=4
    gamRivalFed(gamDate+2)=charFed(negChar)
    ChangeRelationship(negChar,gamChar,-1)
    ChangeRealRelationship(negChar,gamChar,-1)
    charged=1
   EndIf
   lineA$="Then it's settled! In just a few short weeks,"
   lineB$=fedName$(charFed(negChar))+" will destroy "+fedName$(fed)+"!"
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(negVisitor,0,1)
   If charged=0
    ChangeRelationship(negChar,gamChar,-1)
    ChangeRealRelationship(negChar,gamChar,-1)
    charged=1
   EndIf
   lineA$="Haha, I knew you cowards wouldn't put up a fight!"
   lineB$="You wouldn't have been much of a challenge anyway..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;25. INVASION LEADS TO INTER-PROMOTIONAL CHALLENGE 
 If negTopic=25
  optionA$="Yes, accept challenge!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,2)
   lineA$="Hey, "+charName$(gamChar)+", I'm sure you're wondering how a"
   lineB$=fedName$(charFed(negChar))+" star got onto YOUR show?"
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,1)
   lineA$="Well that was NOTHING! I came here to challenge"
   lineB$=fedName$(fed)+" to an inter-promotional contest!"
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,1)
   lineA$="If you've got any dignity left, you'll step"
   lineB$="into the ring with us for a REAL showdown?"
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,1)
   If charged=0
    ProduceSound(0,sCrowd(6),0,0.5)
    gamSchedule(gamDate+2)=4
    gamRivalFed(gamDate+2)=charFed(negChar)
    ChangeRelationship(negChar,gamChar,-1)
    ChangeRealRelationship(negChar,gamChar,-1)
    charged=1
   EndIf
   lineA$="Then it's settled! In just a few short weeks,"
   lineB$=fedName$(charFed(negChar))+" will destroy "+fedName$(fed)+"!"
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(negVisitor,0,1)
   If charged=0
    ChangeRelationship(negChar,gamChar,-1)
    ChangeRealRelationship(negChar,gamChar,-1)
    charged=1
   EndIf
   lineA$="Ha, we should have known you wouldn't put up a fight!"
   lineB$="You're not in the same league as "+fedName$(charFed(negChar))+"..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;26. NETWORK SUGGESTS TRIBUTE SHOW
 If negTopic=26
  g=charGender(fedRoster(9,fedSize(9)))
  optionA$="Yes, pay tribute!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,2)
   lineA$="Hello, "+charName$(gamChar)+". I'm sure you heard about"
   lineB$="the tragic death of "+charName$(fedRoster(9,fedSize(9)))+"?"
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,3)
   lineA$="Well, we at the TV Network would like you to"
   lineB$="stage a special tribute show in "+Lower$(His$(g))+" honour!"
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,3)
   lineA$="We'd obviously have to donate the profits to the"
   lineB$="memorial fund, but what's money at a time like this?"
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 Then gamSchedule(gamDate+1)=6 : gamFatality=0 : charged=1
   lineA$="Good, that's very gracious of you! Just make"
   lineB$="sure the show does "+charName$(fedRoster(9,fedSize(9)))+" proud..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(negVisitor,0,1)
   If charged=0
    ProduceSound(0,sCrowd(3),0,0.5)
    fedPopularity(fed)=fedPopularity(fed)-1
    fedReputation(fed)=fedReputation(fed)+PursueValue(fedReputation(fed),30,0)
    gamFatality=0 : charged=1
   EndIf
   lineA$="You DON'T want to pay your respects to a fallen"
   lineB$="wrestler?! This is going to be hard to explain..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;27. NETWORK SUGGESTS CHARITY SHOW
 If negTopic=27
  optionA$="Yes, donate to charity!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,3)
   lineA$="Hello, "+charName$(gamChar)+". I'd like to talk to you"
   lineB$="about staging a special charity event soon..."
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,3)
   lineA$="With all the negative publicity surrounding wrestling,"
   lineB$="it's important to give back from time to time!"
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,3)
   lineA$="How would you feel about hosting a show next month"
   lineB$="that would donate all proceeds to a charity fund?"
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 Then gamSchedule(gamDate+4)=5 : charged=1
   lineA$="Excellent! The whole world will be watching,"
   lineB$="so make sure that it's a spectacular show..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(negVisitor,0,1)
   If charged=0
    ProduceSound(0,sCrowd(3),0,0.5)
    fedPopularity(fed)=fedPopularity(fed)-1
    fedReputation(fed)=fedReputation(fed)+PursueValue(fedReputation(fed),30,0)
    charged=1
   EndIf
   lineA$="You can't spare a single week's profits to give"
   lineB$="back to the community?! This is going to look bad..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;28. NETWORK SUGGESTS PROMOTION SWITCH
 If negTopic=28
  optionA$="Yes, switch promotions!" : optionB$="No, stay put..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,2)
   lineA$="Listen, "+charName$(gamChar)+", I'm not sure your"
   lineB$="future belongs at "+fedName$(fed)+"..."
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,3)
   lineA$="How would you feel about taking control of"
   lineB$="a show like "+fedName$(negVariable)+" instead?"
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,3)
   lineA$="Word is that "+charName$(fedBooker(negVariable))+" is screwing up over there,"
   lineB$="so a change of management could be just what's needed!"
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0
    MoveChar(gamChar,negVariable)
    ChangeRelationship(gamChar,fedBooker(negVariable),-1)
    ChangeRealRelationship(gamChar,fedBooker(negVariable),-1)
    fedBooker(negVariable)=gamChar
    For count=1 To fedSize(negVariable)
     char=fedRoster(negVariable,count)
     charMatches(char,negVariable)=0
     charWins(char,negVariable)=0
    Next
    If fedBank(negVariable)<100000 Then fedBank(negVariable)=100000
    For count=1 To 12
     fedProduction(negVariable,count)=Rnd(0,1)
    Next
    gamBuild=0 : gamComplete=0
    ResetSchedule(gamDate)
    gamSchedule(gamDate)=0
   EndIf
   lineA$="I'm glad you're up to the challenge! That ambition"
   lineB$="could be just what "+fedName$(negVariable)+" needs..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(negVisitor,0,2)
   lineA$="Alright, I was just thinking out loud. Let's"
   lineB$="see how things pan out at "+fedName$(fed)+"..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;29. WRESTLER ASKS TO TEAM WITH GUEST PARTNER
 If negTopic=29
  optionA$="Yes, form team!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,3)
   lineA$="Hey, "+charName$(gamChar)+", I worked pretty well"
   lineB$="with "+charName$(negVariable)+", don't you think?"
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,3)
   lineA$="So why don't we make it a permanent thing?"
   lineB$="I figure we should be a team from now on!"
  EndIf
  If negStage=0 And negTim>650 Then camFoc=negStar
  If negStage=0 And negTim>675 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 
    FormTeam(negChar,negVariable) : charAgreement(negChar,14)=4
    AdjustAttitude(negChar,-1) : charged=1
   EndIf 
   lineA$="Great, I'm sure "+charName$(negVariable)+" will be thrilled!"
   lineB$="This could be the start of something big..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)=0
   Speak(negVisitor,0,1)
   If charged=0 Then AdjustAttitude(negChar,1) : charged=1
   lineA$="What was the point in putting us together if it's"
   lineB$="never gonna happen again?! The fans will be confused..."
  EndIf
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)>0
   Speak(negVisitor,0,1)
   If charged=0 
    FormTeam(negChar,negVariable) : charAgreement(negChar,14)=4
    AdjustAttitude(negChar,-2) : charged=1
   EndIf 
   lineA$="You haven't got the right to tell us what to do!"
   lineB$="If we want to team together then we will..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;30. WRESTLER ASKS TO TEAM WITH POSITIVE INTRUDER
 If negTopic=30
  g=charGender(negVariable)
  optionA$="Yes, form team!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,3)
   lineA$="Hey, "+charName$(gamChar)+", that interference from"
   lineB$=charName$(negVariable)+" was pretty cool, huh?"
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,3)
   lineA$="Since "+Lower$(He$(g))+" helped me out, I figure the two of"
   lineB$="us should be a team now! What do you think?"
  EndIf
  If negStage=0 And negTim>650 Then camFoc=negStar
  If negStage=0 And negTim>675 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 
    FormTeam(negChar,negVariable) : charAgreement(negChar,14)=4
    AdjustAttitude(negChar,-1) : charged=1
   EndIf 
   lineA$="Cool, I'll talk to "+charName$(negVariable)+" about the idea."
   lineB$="The fans will go crazy for our little alliance!"
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)=0
   Speak(negVisitor,0,1)
   If charged=0 Then AdjustAttitude(negChar,1) : charged=1
   lineA$="Come on, we're supposed to waste that opportunity?!"
   lineB$="The fans will be disappointed to see it go nowhere..."
  EndIf
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)>0
   Speak(negVisitor,0,1)
   If charged=0 
    FormTeam(negChar,negVariable) : charAgreement(negChar,14)=4
    AdjustAttitude(negChar,-2) : charged=1
   EndIf 
   lineA$="You haven't got the right to tell us what to do!"
   lineB$="If we want to team together then we will..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;31. WRESTLER ASKS TO FEUD WITH INTRUDER
 If negTopic=31
  g=charGender(negVariable)
  optionA$="Yes, start feud!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,3)
   lineA$="Hey, "+charName$(gamChar)+", that interference from"
   lineB$=charName$(negVariable)+" was pretty cool, huh?"
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,2)
   lineA$="Since "+Lower$(He$(g))+" screwed with my career, I figure I should"
   lineB$="hold it against "+Him$(g)+"! Why don't we start a feud?"
  EndIf
  If negStage=0 And negTim>650 Then camFoc=negStar
  If negStage=0 And negTim>675 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 Then ChangeRelationship(negChar,negVariable,-1) : AdjustAttitude(negChar,-1) : charged=1
   lineA$="Cool, I'll talk to "+charName$(negVariable)+" about the idea."
   lineB$="There should be a ton of heat on our next match!"
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)=0
   Speak(negVisitor,0,1)
   If charged=0 Then AdjustAttitude(negChar,1) : charged=1
   lineA$="Come on, we're supposed to waste that opportunity?!"
   lineB$="The fans will be disappointed to see it go nowhere..."
  EndIf
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)>0
   Speak(negVisitor,0,1)
   If charged=0 Then ChangeRelationship(negChar,negVariable,-1) : AdjustAttitude(negChar,-2) : charged=1 
   lineA$="You haven't got the right to tell us what to do!"
   lineB$="If we want to feud with each other then we will..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;32. WRESTLER ASKS TO FEUD WITH INCOMPETENT TEAM-MATE
 If negTopic=32
  g=charGender(negVariable)
  optionA$="Yes, start feud!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,1)
   lineA$="Hey, "+charName$(gamChar)+", it was embarrassing"
   lineB$="to be on the losing team on that match!"
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,2)
   lineA$="But if you think about it, I didn't really lose!"
   lineB$="It was "+charName$(negVariable)+" that took the fall..."
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,3)
   lineA$="I figure I should turn on "+Him$(g)+" for costing us"
   lineB$="the match? Then I could wash my hands of it!"
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 Then ChangeRelationship(negChar,negVariable,-1) : AdjustAttitude(negChar,-1) : charged=1
   lineA$="Excellent! The fans will go wild when they"
   lineB$="see a team torn apart in front of their eyes!"
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)=0
   Speak(negVisitor,0,1)
   If charged=0 Then charPopularity(negChar)=charPopularity(negChar)-1 : AdjustAttitude(negChar,1) : charged=1
   lineA$="Damn, that was the perfect opportunity to leave!"
   lineB$="Thanks to you, I'm stuck on a sinking ship..."
  EndIf
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)>0
   Speak(negVisitor,0,1)
   If charged=0 Then ChangeRelationship(negChar,negVariable,-1) : AdjustAttitude(negChar,-2) : charged=1 
   lineA$="You haven't got the right to tell us what to do!"
   lineB$="If we want to feud with each other then we will..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;33. WRESTLER ASKS TO FEUD WITH ABUSIVE TEAM-MATE
 If negTopic=33
  g=charGender(negVariable)
  optionA$="Yes, start feud!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,1)
   lineA$="Hey, "+charName$(gamChar)+", did you notice that"
   lineB$=charName$(negVariable)+" attacked ME in that match?!"
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,2)
   lineA$="Well, I figure I should hold it against "+Him$(g)+"!"
   lineB$="Why don't we use it as an excuse to start a feud?"
  EndIf
  If negStage=0 And negTim>650 Then camFoc=negStar
  If negStage=0 And negTim>675 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 Then ChangeRelationship(negChar,negVariable,-1) : AdjustAttitude(negChar,-1) : charged=1
   lineA$="Excellent! The fans will go wild when they"
   lineB$="see a team torn apart in front of their eyes!"
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)=0
   Speak(negVisitor,0,1)
   If charged=0 Then charPopularity(negChar)=charPopularity(negChar)-1 : AdjustAttitude(negChar,1) : charged=1
   lineA$="Damn, that was my only opportunity to save face!"
   lineB$="I'm gonna look like a pussy if we let this slide..."
  EndIf
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)>0
   Speak(negVisitor,0,1)
   If charged=0 Then ChangeRelationship(negChar,negVariable,-1) : AdjustAttitude(negChar,-2) : charged=1 
   lineA$="You haven't got the right to tell us what to do!"
   lineB$="If we want to feud with each other then we will..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;34. WRESTLER ASKS TO FEUD WITH REFEREE
 If negTopic=34
  g=charGender(negVariable)
  optionA$="Yes, start feud!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,3)
   lineA$="Hey, "+charName$(gamChar)+", it was quite interesting"
   lineB$="to see "+charName$(negVariable)+" as a referee, right?"
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,2)
   lineA$="Well, since I lost, I figure I should blame "+Him$(g)+"?"
   lineB$="We could start a whole angle based on what happened!"
  EndIf
  If negStage=0 And negTim>650 Then camFoc=negStar
  If negStage=0 And negTim>675 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 Then ChangeRelationship(negChar,negVariable,-1) : AdjustAttitude(negChar,-1) : charged=1
   lineA$="Cool, I'll talk to "+charName$(negVariable)+" about the idea."
   lineB$="The fans will be dying to see what happens next!"
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)=0
   Speak(negVisitor,0,1)
   If charged=0 Then AdjustAttitude(negChar,1) : charged=1
   lineA$="Hey, there's more to wrestling than throwing punches!"
   lineB$="Sometimes the referees can be just as powerful..."
  EndIf
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)>0
   Speak(negVisitor,0,1)
   If charged=0 Then ChangeRelationship(negChar,negVariable,-1) : AdjustAttitude(negChar,-2) : charged=1 
   lineA$="You haven't got the right to tell us what to do!"
   lineB$="If we want to feud with each other then we will..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;35. WRESTLER ASKS TO FEUD WITH FRIEND
 If negTopic=35
  g=charGender(negVariable)
  optionA$="Yes, start feud!" : optionB$="No, stay friends..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,2)
   lineA$="Hi, "+charName$(gamChar)+". Sorry to bother you,"
   lineB$="but I'd like to make a suggestion..."
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,1)
   lineA$="I'm tired of being linked with "+charName$(negVariable)+"!"
   lineB$="Friendships have no place in a business like this..."
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,2)
   lineA$="The breaking up is all that matters, so why don't"
   lineB$="we get it over with by starting a dramatic feud?"
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 Then ChangeRelationship(negChar,negVariable,-1) : AdjustAttitude(negChar,-1) : charged=1
   lineA$="Great! A heated rivalry should liven up both of our"
   lineB$="careers and give the fans plenty to talk about..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)=0
   Speak(negVisitor,0,1)
   If charged=0 Then ChangeRealRelationship(negChar,negVariable,-1) : AdjustAttitude(negChar,1) : charged=1
   lineA$="Fine! If you won't let us break up in the ring,"
   lineB$="we'll just have to settle our differences backstage!"
  EndIf
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)>0
   Speak(negVisitor,0,1)
   If charged=0 Then ChangeRelationship(negChar,negVariable,-1) : AdjustAttitude(negChar,-2) : charged=1 
   lineA$="You haven't got the right to tell us what to do!"
   lineB$="If we want to feud with each other then we will..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;36. WRESTLER ASKS FOR STEROIDS
 If negTopic=36
  optionA$="Yes, prescribe drugs!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,1)
   lineA$="Hey, "+charName$(gamChar)+", I'm tired of busting"
   lineB$="my ass in the gym and getting nowhere!"
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,2)
   lineA$="Nobody does that anymore now that we have drugs!"
   lineB$="Can't you prescribe me some steroids instead?"
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,3)
   lineA$="It would only cost the company $"+GetFigure$(negPayOff)+", and the"
   lineB$="benefits to my performance would be priceless!"
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 
    PlaySound sCash : fedBank(fed)=fedBank(fed)-negPayOff
    charStrength(negChar)=charStrength(negChar)+PursueValue(charStrength(negChar),100,0)
    charSkill(negChar)=charSkill(negChar)+PursueValue(charSkill(negChar),100,0)
    charAgility(negChar)=charAgility(negChar)+PursueValue(charAgility(negChar),100,0)
    charStamina(negChar)=charStamina(negChar)+PursueValue(charStamina(negChar),100,0)
    charToughness(negChar)=charToughness(negChar)+PursueValue(charToughness(negChar),100,0)
    charAttitude(negChar)=charAttitude(negChar)+PursueValue(charAttitude(negChar),30,0)
    charHappiness(negChar)=charHappiness(negChar)+PursueValue(charHappiness(negChar),100,0)
    charTrainCourse(negChar)=0 : charAgreement(negChar,9)=2 
    gamAgreement(14)=2 : charged=1
   EndIf
   lineA$="Alright, I can feel the benefits already! I'll"
   lineB$="finally be a real 'heavyweight' in this business..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(negVisitor,0,1)
   If charged=0 Then AdjustAttitude(negChar,1) : charged=1
   lineA$="Hey, it's not my fault I wasn't born perfect!"
   lineB$="All I was asking for was a little boost..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;37. WRESTLER ASKS FOR PAINKILLERS
 If negTopic=37
  optionA$="Yes, prescribe drugs!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,1)
   lineA$="Hey, "+charName$(gamChar)+", your merciless schedule"
   lineB$="has left my health in a terrible state!"
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,2)
   lineA$="I feel nauseous and I'm aching 24 hours a day!"
   lineB$="Can't you prescribe some painkillers for me?"
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,3)
   lineA$="It'd only cost the company $"+GetFigure$(negPayOff)+", and then"
   lineB$="I'd have enough energy to carry on working!"
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 
    PlaySound sCash : fedBank(fed)=fedBank(fed)-negPayOff
    charHealth(negChar)=100 : charAgreement(negChar,10)=2
    charAttitude(negChar)=charAttitude(negChar)+PursueValue(charAttitude(negChar),30,0)
    charHappiness(negChar)=charHappiness(negChar)+PursueValue(charHappiness(negChar),100,0)
    gamAgreement(14)=2 : charged=1
   EndIf
   lineA$="Alright, I can feel the benefits already!"
   lineB$="Get me out there and I'll wrestle all night..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(negVisitor,0,1)
   If charged=0 Then AdjustAttitude(negChar,1) : charged=1
   lineA$="Come on, I can barely walk - let alone wrestle!"
   lineB$="I needed those drugs to get through the day..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;38. WRESTLER ASKS FOR SURGERY
 If negTopic=38
  optionA$="Yes, pay for surgery!" : optionB$="No, be patient..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,1)
   lineA$="Hey, "+charName$(gamChar)+", why do I have to sit on the"
   lineB$="sidelines and wait for these injuries to heal?!"
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,3)
   lineA$="Can't you come up with $"+GetFigure$(negPayOff)+" for the surgery?"
   lineB$="Then I'd be back to work in HALF the time!"
  EndIf
  If negStage=0 And negTim>650 Then camFoc=negStar
  If negStage=0 And negTim>675 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 
    PlaySound sCash : fedBank(fed)=fedBank(fed)-negPayOff
    For limb=0 To 5
     charInjured(negChar,limb)=charInjured(negChar,limb)/2
    Next
    charHappiness(negChar)=charHappiness(negChar)+PursueValue(charHappiness(negChar),100,0)
    charAttitude(negChar)=charAttitude(negChar)-1 : charged=1
   EndIf
   lineA$="Great, thanks for this! I'll be back in no"
   lineB$="time once the surgeons get in there and fix me..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(negVisitor,0,1)
   If charged=0 Then charAgreement(negChar,19)=2 : AdjustAttitude(negChar,1) : charged=1
   lineA$="Hey, it's YOUR fault that I'm in this mess!"
   lineB$="The least you could do is pay to fix it..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;39. WRESTLER ASKS FOR COSMETIC SURGERY
 If negTopic=39
  optionA$="Yes, pay for surgery!" : optionB$="No, live with it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,1)
   lineA$="Hey, "+charName$(gamChar)+", look at the state of me!"
   lineB$="I've sacrificed my body for this company..."
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,2)
   lineA$="I just wish I could feel whole again! Can't you"
   lineB$="come up with $"+GetFigure$(negPayOff)+" for corrective surgery?"
  EndIf
  If negStage=0 And negTim>650 Then camFoc=negStar
  If negStage=0 And negTim>675 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 
    PlaySound sCash : fedBank(fed)=fedBank(fed)-negPayOff
    For limb=0 To 50
     charLimb(negChar,limb)=1
    Next
    If InjuryStatus(negChar)=0 Then charInjured(negChar,0)=1 : charHealth(negChar)=0
    charHappiness(negChar)=charHappiness(negChar)+PursueValue(charHappiness(negChar),100,0)
    charAttitude(negChar)=charAttitude(negChar)-1 : charged=1
   EndIf
   lineA$="Great, I can't wait to feel like my old self!"
   lineB$="I'll be good as new once I get out of hospital..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(negVisitor,0,1)
   If charged=0 Then charAgreement(negChar,19)=2 : AdjustAttitude(negChar,1) : charged=1
   lineA$="Hey, it's YOUR fault that I'm in this mess!"
   lineB$="The least you could do is pay to fix it..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;40. WRESTLER ASKS FOR DAY OFF
 If negTopic=40
  optionA$="Yes, take a break..." : optionB$="No, get to work!"
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,2)
   lineA$="Listen, "+charName$(gamChar)+", we need to talk about"
   lineB$="the schedule you've got me working to..."
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,3)
   lineA$="I'm struggling to keep up with the pace! Do you"
   lineB$="mind if I take the night off to recuperate?"
  EndIf
  If negStage=0 And negTim>650 Then camFoc=negStar
  If negStage=0 And negTim>675 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 
    charAttitude(negChar)=charAttitude(negChar)+PursueValue(charAttitude(negChar),100,0)
    charHappiness(negChar)=charHappiness(negChar)+PursueValue(charHappiness(negChar),100,0)
    charVacant(negChar)=1 : charTrainCourse(negChar)=0 : charged=1
   EndIf
   lineA$="Thanks for this! Once I've sorted myself out,"
   lineB$="I'll be back in the ring and better than ever..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)=0
   Speak(negVisitor,0,1)
   If charged=0
    charAttitude(negChar)=charAttitude(negChar)+PursueValue(charAttitude(negChar),30,0)
    charHappiness(negChar)=charHappiness(negChar)+PursueValue(charHappiness(negChar),30,0)
    charAgreement(negChar,17)=2 : charged=1
   EndIf
   lineA$="That's the thanks I get for coming to you?!"
   lineB$="Maybe I'll just fail to turn up in future..."
  EndIf
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)>0
   Speak(negVisitor,0,1)
   If charged=0
    charAttitude(negChar)=charAttitude(negChar)+PursueValue(charAttitude(negChar),30,0)
    charHappiness(negChar)=charHappiness(negChar)+PursueValue(charHappiness(negChar),100,0)
    charVacant(negChar)=1 : charTrainCourse(negChar)=0 : charged=1
   EndIf
   lineA$="Hey, I was just being polite by asking you!"
   lineB$="If I want to take the night off then I will..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;41. WRESTLER ASKS FOR EXTENDED BREAK
 If negTopic=41
  optionA$="Yes, take a break..." : optionB$="No, get to work!"
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,2)
   lineA$="Listen, "+charName$(gamChar)+", we need to talk about"
   lineB$="the schedule you've got me working to..."
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,3)
   lineA$="I've got a lot of problems at home right now, and"
   lineB$="I could use an extended break to sort things out?"
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,2)
   lineA$="I know it's a lot to ask, but my work will"
   lineB$="start to suffer if things go on like this!"
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 
    charAttitude(negChar)=charAttitude(negChar)+PursueValue(charAttitude(negChar),100,0)
    charHappiness(negChar)=charHappiness(negChar)+PursueValue(charHappiness(negChar),100,0)
    charVacant(negChar)=2 : charTrainCourse(negChar)=0 : charged=1
   EndIf
   lineA$="Thanks for this! Once I've sorted myself out,"
   lineB$="I'll be back in the ring and better than ever..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)=0
   Speak(negVisitor,0,1)
   If charged=0
    charAttitude(negChar)=charAttitude(negChar)+PursueValue(charAttitude(negChar),30,0)
    charHappiness(negChar)=charHappiness(negChar)+PursueValue(charHappiness(negChar),30,0)
    charAgreement(negChar,17)=2 : charged=1
   EndIf
   lineA$="Hey, the world doesn't revolve around wrestling!"
   lineB$="I've got more important things to worry about..."
  EndIf
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)>0
   Speak(negVisitor,0,1)
   If charged=0
    charAttitude(negChar)=charAttitude(negChar)+PursueValue(charAttitude(negChar),30,0)
    charHappiness(negChar)=charHappiness(negChar)+PursueValue(charHappiness(negChar),100,0)
    charVacant(negChar)=2 : charTrainCourse(negChar)=0 : charged=1
   EndIf
   lineA$="Hey, I was just being polite by asking you!"
   lineB$="If I need to take some time off then I will..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;42. WRESTLER ASKS TO BE EXCUSED TRAINING
 If negTopic=42
  optionA$="Yes, stop training,.." : optionB$="No, keep at it!"
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,1)
   lineA$="Hey, "+charName$(gamChar)+", why do I have to train?!"
   lineB$="It's an insult to my wrestling ability..."
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,3)
   lineA$="Do you mind if I take a break from the gym?"
   lineB$="I'd prefer to do my working out in the ring!"
  EndIf
  If negStage=0 And negTim>650 Then camFoc=negStar
  If negStage=0 And negTim>675 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 
    charAttitude(negChar)=charAttitude(negChar)-1
    charHappiness(negChar)=charHappiness(negChar)+PursueValue(charHappiness(negChar),100,0)
    charTrainCourse(negChar)=0 : charAgreement(negChar,20)=4 : charged=1
   EndIf
   lineA$="Great, thanks for cutting me a little slack!"
   lineB$="There's more to wrestling than being fit..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)=0
   Speak(negVisitor,0,1)
   If charged=0
    charAttitude(negChar)=charAttitude(negChar)+1
    charHappiness(negChar)=charHappiness(negChar)+PursueValue(charHappiness(negChar),30,0)
    charAgreement(negChar,17)=2 : charged=1
   EndIf
   lineA$="Hey, there's more to wrestling than being fit!"
   lineB$="How you handle yourself in the ring is what matters..."
  EndIf
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)>0
   Speak(negVisitor,0,1)
   If charged=0
    charAttitude(negChar)=charAttitude(negChar)-1
    charHappiness(negChar)=charHappiness(negChar)+PursueValue(charHappiness(negChar),100,0)
    charTrainCourse(negChar)=0 : charAgreement(negChar,20)=4: charged=1
   EndIf
   lineA$="Hey, I was just being polite by asking you!"
   lineB$="If I don't feel like training then I won't..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;43. WRESTLER HOLDS YOU UP FOR MONEY
 If negTopic=43
  optionA$="Yes, pay $"+GetFigure$(negPayOff)+"..." : optionB$="No, do your worst!"
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,1)
   lineA$="Hey, "+charName$(gamChar)+", I'm not sure you appreciate"
   lineB$="how important I am to "+fedName$(fed)+"!"
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,1)
   lineA$="This show would crumble if I wasn't around,"
   lineB$="but that's not reflected in my $"+GetFigure$(charSalary(negChar))+" salary!"
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,1)
   lineA$="Well the insanity ends tonight! If you don't"
   lineB$="give me $"+GetFigure$(negPayOff)+" upfront then I'm outta here..."
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 
    PlaySound sCash : fedBank(fed)=fedBank(fed)-negPayOff
    charHappiness(negChar)=charHappiness(negChar)+PursueValue(charHappiness(negChar),100,0)
    charged=1
   EndIf
   lineA$="Good, I'm glad we understand each other!"
   lineB$="Now don't ever take me for granted again..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(negVisitor,0,1)
   If charged=0
    charAttitude(negChar)=charAttitude(negChar)+PursueValue(charAttitude(negChar),30,0)
    charHappiness(negChar)=charHappiness(negChar)+PursueValue(charHappiness(negChar),30,0)
    charVacant(negChar)=2 : charged=1
   EndIf
   lineA$="You don't think that I'm worth $"+GetFigure$(negPayOff)+"?"
   lineB$="Fine, let's see how you get on without me!"
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf 
 ;44. WRESTLER OFFERS TO IMPROVE ATMOSPHERE
 If negTopic=44
  optionA$="Yes, pay $"+GetFigure$(negPayOff)+"!" : optionB$="No thanks..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,1)
   lineA$="Hey, "+charName$(gamChar)+", it can't have escaped your"
   lineB$="attention that morale is low at the moment?"
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,2)
   lineA$="What "+fedName$(fed)+" needs is leadership!"
   lineB$="Somebody needs to take charge of the locker room..."
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,3)
   lineA$="I'd be willing to do it if you want? For $"+GetFigure$(negPayOff)+","
   lineB$="I'll get everybody pulling in the same direction?"
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 
    PlaySound sCash : fedBank(fed)=fedBank(fed)-negPayOff
    For count=1 To fedSize(fed)
     char=fedRoster(fed,count)
     If char<>gamChar
      charAttitude(char)=charAttitude(char)+PursueValue(charAttitude(char),100,0)
      charHappiness(char)=charHappiness(char)+PursueValue(charHappiness(char),100,0)
     EndIf
    Next
    charged=1
   EndIf
   lineA$="Good, I'm glad you're receptive to a solution!"
   lineB$="Don't worry - I'll straighten everything out..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(negVisitor,0,1)
   If charged=0
    For count=1 To fedSize(fed)
     char=fedRoster(fed,count)
     If char<>gamChar
      charAttitude(char)=charAttitude(char)-1
      charHappiness(char)=charHappiness(char)-1
     EndIf
    Next
    charged=1
   EndIf
   lineA$="Fine, I'll sit back and watch things get worse!"
   lineB$="You're losing control of the entire roster..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf 
 ;45. WRESTLER DEMANDS BETTER PAY
 If negTopic=45
  optionA$="Yes, increase pay..." : optionB$="No, do your worst!"
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,1)
   lineA$="Hey, "+charName$(gamChar)+", we're getting fed up with"
   lineB$="the pathetic wages at "+fedName$(fed)+"!"
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,1)
   lineA$="It's US that step into the ring and put our bodies"
   lineB$="on the line, but it's YOU that makes all the money?!"
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,1)
   lineA$="Well the game's up! Give the talent a 10% pay rise"
   lineB$="or we'll protest and bring this show to its knees..."
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 
    PlaySound sCash : fedBank(fed)=fedBank(fed)-negPayOff
    For count=1 To fedSize(fed)
     char=fedRoster(fed,count) 
     If char<>gamChar
      charSalary(char)=PercentOf#(charSalary(char),110)
      charSalary(char)=RoundOff(charSalary(char),100)
      charAttitude(char)=charAttitude(char)+1
      charHappiness(char)=charHappiness(char)+PursueValue(charHappiness(char),100,0)
     EndIf
    Next
    charged=1
   EndIf
   lineA$="Good, I'm glad you're receptive to a solution!"
   lineB$="The locker room will be a much happier place now..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(negVisitor,0,1)
   If charged=0
    For count=1 To fedSize(fed)
     char=fedRoster(fed,count)
     randy=Rnd(0,2)
     If (randy=0 Or char=negChar) And char<>gamChar
      charVacant(char)=Rnd(0,2)
      charAttitude(char)=charAttitude(char)+PursueValue(charAttitude(char),30,0)
      charHappiness(char)=charHappiness(char)+PursueValue(charHappiness(char),30,0)
     EndIf
    Next
    charged=1
   EndIf
   lineA$="Fine, since you've abused YOUR power we'll have to"
   lineB$="use OURS! Good luck staging a show with no wrestlers..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf 
 ;46. MUTUAL TERMINATION
 If negTopic=46
  optionA$="Yes, terminate contract!" : optionB$="No, honour contract..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,2)
   lineA$="Listen, "+charName$(gamChar)+", I think we need to talk"
   lineB$="about my career here at "+fedName$(fed)+"..."
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,1)
   lineA$="I'm sure you've noticed that things aren't working"
   lineB$="out for me here, and that's no good for you either..."
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,3)
   lineA$="So why don't we cut our losses and terminate"
   lineB$="my contract? I'm happy to do it if you are?"
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 
    PlaySound sPaper : MoveChar(negChar,7)
    charAttitude(negChar)=charAttitude(negChar)+PursueValue(charAttitude(negChar),30,0)
    charHappiness(negChar)=charHappiness(negChar)+PursueValue(charHappiness(negChar),30,0)
    gamNegotiated(negChar,1)=1 : charged=1
   EndIf
   lineA$="Great! No offence, but I can't wait to leave!"
   lineB$=fedName$(fed)+" simply wasn't for me..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(negVisitor,0,1)
   If charged=0
    charAttitude(negChar)=charAttitude(negChar)+PursueValue(charAttitude(negChar),30,0)
    charHappiness(negChar)=charHappiness(negChar)+PursueValue(charHappiness(negChar),30,0)
    charAgreement(negChar,17)=2 : charged=1
   EndIf
   lineA$="Fine! If you won't release me then I'll just"
   lineB$="have to raise hell until you BEG me to leave!"
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf 
 ;47. SEND ENEMY HOME
 If negTopic=47
  g=charGender(negVariable)
  optionA$="Yes, send "+Him$(g)+" home!" : optionB$="No, calm down..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,1)
   lineA$="Hey, "+charName$(gamChar)+", I can't stand being in"
   lineB$="the same locker room as "+charName$(negVariable)+"!"
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,1)
   lineA$=fedName$(fed)+" simply isn't big enough"
   lineB$="for the both of us! One of us will have to go..."
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,2)
   lineA$="Can't you send "+charName$(negVariable)+" home for the day?"
   lineB$="I swear to God, if "+Lower$(He$(g))+" doesn't leave I will!"
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,2)
   If charged=0 
    charVacant(negVariable)=1
    charHappiness(negVariable)=charHappiness(negVariable)+PursueValue(charHappiness(negVariable),30,0)
    AdjustAttitude(negChar,-1) : charged=1
   EndIf
   lineA$="Thank God! If "+charName$(negVariable)+" stayed any longer"
   lineB$="we'd probably see a REAL wrestling match!"
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(negVisitor,0,1)
   If charged=0
    charAttitude(negChar)=charAttitude(negChar)+PursueValue(charAttitude(negChar),30,0)
    charHappiness(negChar)=charHappiness(negChar)+PursueValue(charHappiness(negChar),30,0)
    charVacant(negChar)=1 : charged=1
   EndIf
   lineA$="So you're choosing "+charName$(negVariable)+" over me?!"
   lineB$="Fine! I'll leave you together while I go home..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf 
 ;48. REMOVE ENEMY
 If negTopic=48
  g=charGender(negVariable)
  optionA$="Yes, get rid of "+Him$(g)+"!" : optionB$="No, keep "+Him$(g)+"..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,1)
   lineA$="Hey, "+charName$(gamChar)+", I can't stand being in"
   lineB$="the same locker room as "+charName$(negVariable)+"!"
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,1)
   lineA$="That asshole is bad for MY career and YOUR show!"
   lineB$="Would anybody really miss "+Him$(g)+" if "+Lower$(He$(g))+" was gone?"
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,2)
   lineA$="Why don't we conspire to force "+Him$(g)+" out of here?"
   lineB$="Just say the word and "+Lower$(His$(g))+" contract will disappear..."
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,2)
   If charged=0 
    MoveChar(negVariable,7) : ChangeRealRelationship(negVariable,gamChar,-1)
    charHappiness(negVariable)=charHappiness(negVariable)+PursueValue(charHappiness(negVariable),30,0)
    AdjustAttitude(negChar,-1) : charged=1
   EndIf
   lineA$="Haha, we're finally gonna see the back of that loser!"
   lineB$="Leave it to me and "+Lower$(He$(g))+"'ll soon be off the roster..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(negVisitor,0,1)
   If charged=0
    charAttitude(negChar)=charAttitude(negChar)+PursueValue(charAttitude(negChar),30,0)
    charHappiness(negChar)=charHappiness(negChar)+PursueValue(charHappiness(negChar),30,0)
    charVacant(negChar)=1 : charged=1
   EndIf
   lineA$="So you're choosing "+charName$(negVariable)+" over me?!"
   lineB$="Fine! I'll leave you together while I go home..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf 
 ;49. HIRE FRIEND
 If negTopic=49
  g=charGender(negVariable)
  optionA$="Yes, hire "+Him$(g)+"!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,2)
   lineA$="Hey, "+charName$(gamChar)+", have you ever heard of"
   lineB$="'"+charName$(negVariable)+"' from "+fedName$(charFed(negVariable))+"?"
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,3)
   lineA$="Well "+Lower$(He$(g))+"'s a good friend of mine, and I'm sure"
   lineB$=Lower$(He$(g))+"'d like it here at "+fedName$(fed)+"!"
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,3)
   lineA$="Do you think we could give "+Him$(g)+" a trial run?"
   lineB$="I'm sure "+Lower$(He$(g))+"'d be happy with a basic contract..."
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 
    MoveChar(negVariable,charFed(gamChar)) : charArrived(negVariable)=1
    charContract(negVariable)=4
    charSalary(negVariable)=RoundOff(charWorth(negVariable)/2,10)
    For count=1 To 3
     charClause(negVariable,count)=0
    Next
    charHappiness(negVariable)=charHappiness(negVariable)+PursueValue(charHappiness(negVariable),100,0)
    charHappiness(negChar)=charHappiness(negChar)+PursueValue(charHappiness(negChar),100,0)
    charAttitude(negVariable)=charAttitude(negVariable)+1 : charAttitude(negChar)=charAttitude(negChar)+1
    charged=1
   EndIf
   lineA$="Fantastic, I can't wait to tell "+charName$(negVariable)+"!"
   lineB$="I promise you won't regret giving "+Him$(g)+" a chance..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(negVisitor,0,1)
   If charged=0 Then AdjustAttitude(negChar,-2) : charged=1
   lineA$="Hey, you could do worse than "+charName$(negVariable)+"!"
   lineB$=He$(g)+"'d be perfect for "+fedName$(fed)+"..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;50. REMATCH SUGGESTION
 If negTopic=50
  g=charGender(negVariable)
  optionA$="Yes, start a feud!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,2)
   lineA$="Hey, "+charName$(gamChar)+", that match with"
   lineB$=charName$(negVariable)+" was pretty intense, huh?"
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,3)
   lineA$="So much so that we can't possible leave it there!"
   lineB$="The fans will be expecting to see a rematch..."
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,3)
   lineA$="What do you think? If we start a feud based on"
   lineB$="that match we can build up to an even hotter one!"
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 Then ChangeRelationship(negChar,negVariable,-1) : AdjustAttitude(negChar,-1) : charged=1
   lineA$="Cool, I'll talk to "+charName$(negVariable)+" about the idea!"
   lineB$="There should be a ton of heat on our next match..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)=0
   Speak(negVisitor,0,1)
   If charged=0 Then AdjustAttitude(negChar,1) : charged=1
   lineA$="Come on, we're supposed to waste all of that heat?!"
   lineB$="The fans will be disappointed to see it go nowhere..."
  EndIf
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1 And charClause(negChar,1)>0
   Speak(negVisitor,0,1)
   If charged=0 Then ChangeRelationship(negChar,negVariable,-1) : AdjustAttitude(negChar,-2) : charged=1
   lineA$="Hey, you haven't got the right to tell us what to do!"
   lineB$="If we want to feud with each other then we will..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf 
 ;51. FOREIGN INVASION
 If negTopic=51
  optionA$="Yes, fight them off!" : optionB$="No, stay away..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(2,0,2)
   lineA$="Hey, "+charName$(gamChar)+", have you heard that"
   lineB$=fedName$(negVariable)+" invaded the building?!"
   If negTim>50 Then ChannelPitch chTheme,PercentOf#(chThemePitch,90) 
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(2,0,1)
   lineA$="They forced their way into the arena, and now"
   lineB$="they're tearing through the locker rooms!"
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(2,0,2)
   lineA$="Come on, we've got to stop them before they"
   lineB$="ruin tonight's show! Are you gonna help?"
  EndIf
  If negStage=0 And negTim>975 Then camFoc=1
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,3)
   If charged=0 Then ProduceSound(0,sCrowd(6),0,0.5) : charged=1
   lineA$="Thank God! Now let's get out there and show those"
   lineB$="assholes what "+fedName$(fed)+" is made of!"
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(2,0,1)
   If charged=0
    ProduceSound(0,sCrowd(3),0,0.5)
    gamSchedule(gamDate)=0
    For count=1 To fedSize(fed)
     char=fedRoster(fed,count)
     charHappiness(char)=charHappiness(char)-1
    Next
    charged=1
   EndIf 
   lineA$="NO?! Well that's tonight's show screwed then!"
   lineB$="They're going to tear this place to the ground..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;52. DRUG SCANDAL
 If negTopic=52
  optionA$="Yes, pay $"+GetFigure$(negPayOff)+" fine!" : optionB$="No, serve suspension..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,1)
   lineA$="Hey, "+charName$(gamChar)+", I hear you've turned"
   lineB$="the locker room into a God damn pharmacy?!"
   If negTim>50 Then ChannelPitch chTheme,PercentOf#(chThemePitch,90) 
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,1)
   lineA$="What were you thinking? A televised show must"
   lineB$="not endorse the use of drugs of any kind!"
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,1)
   lineA$="Before this gets blown out of proportion, I"
   lineB$="have to be seen to be doing something about it..."
  EndIf
  If negStage=0 And negTim>1000 And negTim<1300
   Speak(negVisitor,0,2)
   lineA$="I should tear your damn show off the air, but I"
   lineB$="may be able to let you off with a $"+GetFigure$(negPayOff)+" fine?"
  EndIf
  If negStage=0 And negTim>1300 Then camFoc=negStar
  If negStage=0 And negTim>1325 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,1)
   If charged=0
    PlaySound sCash : fedBank(fed)=fedBank(fed)-negPayOff
    fedPopularity(fed)=fedPopularity(fed)-1
    fedReputation(fed)=fedReputation(fed)+PursueValue(fedReputation(fed),30,0)
    charged=1
   EndIf
   lineA$="OK, get out your wallet and we'll make this problem"
   lineB$="go away - but it won't buy our reputation back!"
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(negVisitor,0,2)
   If charged=0
    gamSchedule(gamDate)=0 : gamSchedule(gamDate+1)=0
    fedPopularity(fed)=fedPopularity(fed)-1
    fedReputation(fed)=fedReputation(fed)-1
    charged=1
   EndIf
   lineA$="Alright, I'll have to take you off the air for"
   lineB$="a couple of weeks while this thing dies down..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;53. POWER FAILURE
 If negTopic=53
  optionA$="Yes, stage show!" : optionB$="No, cancel show..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,1)
   lineA$="Look, "+charName$(gamChar)+", it seems the venue"
   lineB$="for tonight has suffered a power failure!"
   If negTim>50 Then ChannelPitch chTheme,PercentOf#(chThemePitch,90) 
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,2)
   lineA$="That obviously leaves the arena with hardly"
   lineB$="any lighting, so things could get dangerous!"
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,3)
   lineA$="It's your call though. Do you want to cancel"
   lineB$="the show or struggle through a few matches?"
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0 Then gamAgreement(18)=1 : charged=1
   lineA$="Good, I'm glad you're up to the challenge!"
   lineB$="Who knows? It could make things interesting..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(negVisitor,0,1)
   If charged=0
    gamSchedule(gamDate)=0
    fedPopularity(fed)=fedPopularity(fed)-1
    fedReputation(fed)=fedReputation(fed)-1
    gamAgreement(18)=1 : charged=1
   EndIf
   lineA$="I understand, but I'm not sure the fans will!"
   lineB$="They went to a lot of expense to see a show..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;54. TERRORIST THREAT
 If negTopic=54
  optionA$="Yes, stage show!" : optionB$="No, cancel show..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,2)
   lineA$="Listen, "+charName$(gamChar)+", I'm afraid I have"
   lineB$="some disturbing news about tonight's show..."
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,1)
   lineA$="It seems the arena has been targeted for a"
   lineB$="terrorist attack and may not be safe to use!"
   If negTim>400 Then ChannelPitch chTheme,PercentOf#(chThemePitch,90) 
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,3)
   lineA$="It's your call though. Do you want to cancel"
   lineB$="the show or take a chance on a few matches?"
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,1)
   If charged=0 Then gamAgreement(15)=1 : charged=1
   lineA$="Alright, but this is YOUR responsibility!"
   lineB$="You can't say that I didn't warn you..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(negVisitor,0,2)
   If charged=0
    gamSchedule(gamDate)=0
    fedPopularity(fed)=fedPopularity(fed)-1
    fedReputation(fed)=fedReputation(fed)-1
    gamAgreement(15)=1 : charged=1
   EndIf
   lineA$="That's probably for the best, but I'm not sure the"
   lineB$="fans will see it that way! They came to see a show..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;55. NO AUDIENCE
 If negTopic=55
  optionA$="Yes, stage show!" : optionB$="No, cancel show..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,2)
   lineA$="Listen, "+charName$(gamChar)+", I'm afraid I have"
   lineB$="some bad news about tonight's show..."
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,1)
   lineA$="The wrong date was printed on the tickets, so"
   lineB$="the audience haven't turned up at the arena!"
   If negTim>400 Then ChannelPitch chTheme,PercentOf#(chThemePitch,90) 
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,3)
   lineA$="We can still stage a show for the TV viewers, but"
   lineB$="it might be hard without a live crowd to feed off?"
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   lineA$="I suppose that's for the best! We might not make"
   lineB$="any money, but we can still score big ratings..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(negVisitor,0,1)
   If charged=0
    gamSchedule(gamDate)=0
    fedPopularity(fed)=fedPopularity(fed)-1
    fedReputation(fed)=fedReputation(fed)-1
    charged=1
   EndIf
   lineA$="Well, we can't lose any more money than we already"
   lineB$="have! It's just a shame that the ratings will suffer..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;56. RELEASE WRESTLER FOR INTERVIEW
 If negTopic=56
  g=charGender(negVariable)
  optionA$="Yes, accept $"+GetFigure$(negPayOff)+"!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,3)
   lineA$="Hello, "+charName$(gamChar)+". I represent a magazine"
   lineB$="that's interested in "+charName$(negVariable)+"!"
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,3)
   lineA$="Our readers would love to hear "+Lower$(His$(g))+" story,"
   lineB$="so it would be great to talk to "+Him$(g)+"?"
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,3)
   lineA$="Do you think we could spend the day with "+Him$(g)
   lineB$="if we paid you $"+GetFigure$(negPayOff)+" for your trouble?"
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0
    PlaySound sCash : fedBank(fed)=fedBank(fed)+negPayOff
    fedPopularity(fed)=fedPopularity(fed)+1
    fedReputation(fed)=fedReputation(fed)+1
    charPopularity(negVariable)=charPopularity(negVariable)+1
    charAttitude(negVariable)=charAttitude(negVariable)-1
    charHappiness(negVariable)=charHappiness(negVariable)+1
    charVacant(negVariable)=1 : charged=1
   EndIf
   lineA$="Great! Our readers will be interested to hear about"
   lineB$=charName$(negVariable)+"'s career at "+fedName$(fed)+"!"
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(negVisitor,0,1)
   If charged=0 Then AdjustAttitude(negVariable,1) : charged=1
   lineA$="Well, anybody that's stupid enough to work for"
   lineB$="you probably isn't worth interviewing anyway!"
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;57. RELEASE WRESTLER FOR MOVIE
 If negTopic=57
  g=charGender(negVariable)
  optionA$="Yes, accept $"+GetFigure$(negPayOff)+"!" : optionB$="No, forget it..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,3)
   lineA$="Hi, "+charName$(gamChar)+". I represent a movie studio"
   lineB$="that wants to make "+charName$(negVariable)+" a star!"
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,3)
   lineA$="We'd like to take "+Him$(g)+" out of the ring for a while"
   lineB$="and see how "+Lower$(He$(g))+" performs on the big screen!"
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,3)
   lineA$="Would you be willing to release "+Him$(g)+" for the duration"
   lineB$="of the shoot if we paid you $"+GetFigure$(negPayOff)+" for your trouble?"
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0
    PlaySound sCash : fedBank(fed)=fedBank(fed)+negPayOff
    fedPopularity(fed)=fedPopularity(fed)+1
    fedReputation(fed)=fedReputation(fed)+1
    charPopularity(negVariable)=charPopularity(negVariable)+PursueValue(charPopularity(negVariable),100,0)
    charAttitude(negVariable)=charAttitude(negVariable)+PursueValue(charAttitude(negVariable),30,0)
    charHappiness(negVariable)=charHappiness(negVariable)+PursueValue(charHappiness(negVariable),100,0)
    charVacant(negVariable)=4 : charged=1
   EndIf
   lineA$="Alright, "+charName$(negVariable)+" is gonna be a star!"
   lineB$="We'll be sure to credit you as a producer..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(negVisitor,0,1)
   If charged=0
    ChangeRealRelationship(negVariable,gamChar,-1)
    charAttitude(negVariable)=charAttitude(negVariable)-1
    charHappiness(negVariable)=charHappiness(negVariable)+PursueValue(charHappiness(negVariable),30,0) 
    charged=1
   EndIf
   lineA$="You selfish fool! This opportunity could"
   lineB$="have changed "+charName$(negVariable)+"'s life..."
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;58. DVD OFFER
 If negTopic=58
  optionA$="Yes, pay $"+GetFigure$(negPayOff)+"!" : optionB$="No thanks..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,3)
   lineA$="Hi, "+charName$(gamChar)+". I represent a DVD company"
   lineB$="that's interested in "+fedName$(fed)+"!"
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,3)
   lineA$="Everybody knows that wrestling fans love to"
   lineB$="watch shows all over again - and now they can!"
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,3)
   lineA$="We could produce DVD's for "+fedName$(fed)
   lineB$="if you pick up the $"+GetFigure$(negPayOff)+" production cost?"
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0
    PlaySound sCash : fedBank(fed)=fedBank(fed)-negPayOff
    fedPopularity(fed)=fedPopularity(fed)+1
    fedReputation(fed)=fedReputation(fed)+1
    For count=1 To fedSize(fed)
     char=fedRoster(fed,count)
     charHappiness(char)=charHappiness(char)+1
    Next
    charged=1
   EndIf
   lineA$="It's a pleasure to do business with you! You'll"
   lineB$="have more fans than ever once these DVD's get out..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(negVisitor,0,1)
   lineA$="You'll never boost your audience if the fans"
   lineB$="haven't got anything to show their friends!"
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf
 ;59. GAME OFFER
 If negTopic=59
  optionA$="Yes, pay $"+GetFigure$(negPayOff)+"!" : optionB$="No thanks..."
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,3)
   lineA$="Hi, "+charName$(gamChar)+". I represent a developer that"
   lineB$="wants to make a game about "+fedName$(fed)+"!"
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,3)
   lineA$="Nowadays, a game is the ultimate wrestling souvenir!"
   lineB$="It allows the fans to immerse themselves in your world..."
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,3)
   lineA$="We could produce one for "+fedName$(fed)
   lineB$="if you pick up the $"+GetFigure$(negPayOff)+" development fee?"
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,3)
   If charged=0
    PlaySound sCash : fedBank(fed)=fedBank(fed)-negPayOff
    fedPopularity(fed)=fedPopularity(fed)+1
    fedReputation(fed)=fedReputation(fed)+1
    For count=1 To fedSize(fed)
     char=fedRoster(fed,count)
     charHappiness(char)=charHappiness(char)+1
    Next
    charged=1
   EndIf
   lineA$="Alright, your fans won't be disappointed!"
   lineB$="I know a guy that makes great wrestling games..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(negVisitor,0,1)
   lineA$="How do you intend to stay relevant in the"
   lineB$="digital age if you don't even have a game?!"
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf 
 ;60. PAY FOR DAMAGES
 If negTopic=60
  optionA$="Yes, pay $"+GetFigure$(negPayOff)+"..." : optionB$="No, forget it!"
  If negStage=0 And negTim>25 And negTim<325
   Speak(negVisitor,0,1)
   lineA$="For the love of God, "+charName$(gamChar)+"!"
   lineB$="You made a hell of a mess in that match..."
  EndIf
  If negStage=0 And negTim>350 And negTim<650
   Speak(negVisitor,0,1)
   lineA$="We all like to see an entertaining match, but"
   lineB$=fedName$(charFed(negChar))+" has to pay for that stuff!"
  EndIf
  If negStage=0 And negTim>675 And negTim<975
   Speak(negVisitor,0,1)
   lineA$="Before the venue sues us for damages, I figure"
   lineB$="we can put this mess behind us for $"+GetFigure$(negPayOff)+"?"
  EndIf
  If negStage=0 And negTim>975 Then camFoc=negStar
  If negStage=0 And negTim>1000 Then negStage=1 : keytim=20
  ;positive
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=1
   Speak(negVisitor,0,2)
   If charged=0 Then PlaySound sCash : fedBank(fed)=fedBank(fed)-negPayOff : charged=1
   lineA$="Thanks, I'll pass this onto the arena staff."
   lineB$="Try to be more responsible in future though..."
  EndIf
  ;negative
  If negStage=2 And negTim>25 And negTim<325 And negVerdict=-1
   Speak(negVisitor,0,1)
   If charged=0 
    gamSchedule(gamDate+1)=0
    fedReputation(fed)=fedReputation(fed)+PursueValue(fedReputation(fed),30,0)  
    charged=1
   EndIf
   lineA$="Fine, if you can't be trusted in the arenas"
   lineB$="then I'll just have to keep you out of them!"
  EndIf
  If negStage=2 And negTim>375 Then go=1 
 EndIf

 ;-------------------------- MISSION ASSIGNMENTS ---------------------------------
 ;STANDARD INTRO
 If negTopic>100 And negTopic<120 
  If negTim>25 And negTim<325
   Speak(negVisitor,0,2)
   lineA$="Listen, "+charName$(gamChar)+", I've been keeping a close eye"
   lineB$="on your progress and I think changes need to be made..."
  EndIf
 EndIf
 ;1. IMPROVE POPULARITY
 If negTopic=101
  If negTim>350 And negTim<650 
   Speak(negVisitor,0,1)
   gamTarget=fedPopularity(fed)+5 : gamDeadline=gamDate+Rnd(4,6)
   lineA$="I'm not sure this show has the potential to succeed!"
   lineB$="You need to prove that things are moving forward..."
  EndIf
  If negTim>675 And negTim<975
   Speak(negVisitor,0,1)
   lineA$="Get our rating up to "+gamTarget+"% by the "+DescribeDate$(gamDeadline,0)
   lineB$="or I'll throw the towel in on the whole thing!"
  EndIf
  If negTim>1025 Then gamMission=1 : go=1
 EndIf
 ;2. IMPROVE REPUTATION
 If negTopic=102
  If negTim>350 And negTim<650 
   Speak(negVisitor,0,1)
   gamTarget=fedReputation(fed)+5 : gamDeadline=gamDate+Rnd(4,6)
   lineA$="I don't like the direction your show is heading!"
   lineB$="That dubious reputation brings shame on the network..."
  EndIf
  If negTim>675 And negTim<975
   Speak(negVisitor,0,1)
   lineA$="Improve it to "+gamTarget+"% by the "+DescribeDate$(gamDeadline,0)
   lineB$="or I'll put you in the gutter where you belong!"
  EndIf
  If negTim>1025 Then gamMission=2 : go=1
 EndIf
 ;3. IMPROVE BANK BALANCE
 If negTopic=103
  If negTim>350 And negTim<650 
   Speak(negVisitor,0,1)
   gamTarget=fedBank(fed)+100000 : gamDeadline=gamDate+Rnd(4,6)
   lineA$="I'm not sure this show is capable of making money!"
   lineB$="In case you haven't noticed, that's the whole point..."
  EndIf
  If negTim>675 And negTim<975
   Speak(negVisitor,0,1)
   lineA$="Gather together $"+GetFigure$(gamTarget)+" by the "+DescribeDate$(gamDeadline,0)
   lineB$="or I'll give your spot to a more profitable show!"
  EndIf
  If negTim>1025 Then gamMission=3 : go=1
 EndIf
 ;4. GET OUT OF DEBT
 If negTopic=104
  If negTim>350 And negTim<650 
   Speak(negVisitor,0,1)
   gamTarget=0 : gamDeadline=gamDate+4
   lineA$="Your financial status brings shame on the network!"
   lineB$="How did you manage to run the show into MINUS figures?!"
  EndIf
  If negTim>675 And negTim<975
   Speak(negVisitor,0,1)
   lineA$="You better get out of debt by the "+DescribeDate$(gamDeadline,0)
   lineB$="or I'll put you out on the street where you belong!"
  EndIf
  If negTim>1025 Then gamMission=4 : go=1
 EndIf
 ;5. RECLAIM TOP SPOT
 If negTopic=105
  If negTim>350 And negTim<650 
   Speak(negVisitor,0,1)
   gamTarget=1 : gamDeadline=gamDate+Rnd(4,6)
   lineA$="How come we're not on top of the ratings anymore?"
   lineB$="Once you've been there, anything less is a failure!"
  EndIf
  If negTim>675 And negTim<975
   Speak(negVisitor,0,1)
   lineA$="Reclaim the top spot by the "+DescribeDate$(gamDeadline,0)
   lineB$="or I'll have to assume this show has peaked..."
  EndIf
  If negTim>1025 Then gamMission=5 : go=1
 EndIf
 ;6. SIGN WRESTLER
 If negTopic=106
  If negTim>350 And negTim<650 
   Speak(negVisitor,0,3) : g=charGender(gamTarget)
   gamDeadline=gamDate+4
   lineA$="I've heard good things about "+charName$(gamTarget)+"!"
   lineB$="I think "+Lower$(He$(g))+"'d be an asset to "+fedName$(fed)+"..."
  EndIf
  If negTim>675 And negTim<975
   Speak(negVisitor,0,1)
   lineA$="Sign "+Him$(g)+" from "+fedName$(charFed(gamTarget))+" by the"
   lineB$=DescribeDate$(gamDeadline,0)+" or somebody else will!"
  EndIf
  If negTim>1025 Then gamMission=6 : go=1
 EndIf
 ;7. REMOVE WRESTLER
 If negTopic=107
  If negTim>350 And negTim<650 
   Speak(negVisitor,0,1) : g=charGender(gamTarget)
   gamDeadline=gamDate+4
   lineA$=charName$(gamTarget)+" is ruining "+fedName$(fed)+"!"
   lineB$="I think we'd be better off without "+Him$(g)+"..."
  EndIf
  If negTim>675 And negTim<975
   Speak(negVisitor,0,1)
   lineA$="Get rid of "+Him$(g)+" by the "+DescribeDate$(gamDeadline,0)
   lineB$="or you'll BOTH be working for somebody else!"
  EndIf
  If negTim>1025 Then gamMission=7 : go=1
 EndIf
 ;8. CROWN WORLD CHAMPION
 If negTopic=108
  If negTim>350 And negTim<650 
   Speak(negVisitor,0,1) : g=charGender(gamTarget)
   gamDeadline=gamDate+4
   lineA$=charName$(gamTarget)+" is the pride of "+fedName$(fed)+"!"
   lineB$="I think "+Lower$(He$(g))+" should be the World Champion..."
  EndIf
  If negTim>675 And negTim<975
   Speak(negVisitor,0,1)
   lineA$="Make it happen by the "+DescribeDate$(gamDeadline,0)
   lineB$="or I'll have to find a booker that will!"
  EndIf
  If negTim>1025 Then gamMission=8 : go=1
 EndIf
 ;9. CROWN INTER CHAMPION
 If negTopic=109
  If negTim>350 And negTim<650 
   Speak(negVisitor,0,1) : g=charGender(gamTarget)
   gamDeadline=gamDate+4
   lineA$=charName$(gamTarget)+" is the future of "+fedName$(fed)+"!"
   lineB$="I think "+Lower$(He$(g))+" should be the Inter Champion..."
  EndIf
  If negTim>675 And negTim<975
   Speak(negVisitor,0,1)
   lineA$="Make it happen by the "+DescribeDate$(gamDeadline,0)
   lineB$="or I'll have to find a booker that will!"
  EndIf
  If negTim>1025 Then gamMission=9 : go=1
 EndIf
 ;10. CROWN TAG CHAMPION
 If negTopic=110
  If negTim>350 And negTim<650 
   Speak(negVisitor,0,1) : g=charGender(gamTarget)
   gamDeadline=gamDate+4
   lineA$="The team division needs someone like "+charName$(gamTarget)+"!"
   lineB$="I think "+Lower$(He$(g))+" should get a run with the Tag titles..."
  EndIf
  If negTim>675 And negTim<975
   Speak(negVisitor,0,1)
   lineA$="Make it happen by the "+DescribeDate$(gamDeadline,0)
   lineB$="or I'll have to find a booker that will!"
  EndIf
  If negTim>1025 Then gamMission=10 : go=1
 EndIf
 ;11. EXPAND ROSTER
 If negTopic=111
  If negTim>350 And negTim<650 
   Speak(negVisitor,0,1)
   gamTarget=fedSize(fed)+4 : gamDeadline=gamDate+4
   lineA$=fedName$(fed)+" is too small to function!"
   lineB$="We're supposed to be building an empire here..."
  EndIf
  If negTim>675 And negTim<975
   Speak(negVisitor,0,1)
   lineA$="Increase the roster to "+gamTarget+" wrestlers by the"
   lineB$=DescribeDate$(gamDeadline,0)+" or you won't be on it!"
  EndIf
  If negTim>1025 Then gamMission=11 : go=1
 EndIf
 ;12. REDUCE ROSTER
 If negTopic=112
  If negTim>350 And negTim<650 
   Speak(negVisitor,0,1)
   gamTarget=fedSize(fed)-4 : gamDeadline=gamDate+4
   lineA$=fedName$(fed)+" is too large to control!"
   lineB$="We've got more employees than the show needs..."
  EndIf
  If negTim>675 And negTim<975
   Speak(negVisitor,0,1)
   lineA$="Reduce the roster to "+gamTarget+" wrestlers by the"
   lineB$=DescribeDate$(gamDeadline,0)+" or you'll be the first to go!"
  EndIf
  If negTim>1025 Then gamMission=12 : go=1
 EndIf
 ;13. ACQUIRE PRODUCTION
 If negTopic=113
  If negTim>350 And negTim<650 
   Speak(negVisitor,0,1)
   gamTarget=negSubTopic : gamDeadline=gamDate+4
   lineA$=fedName$(fed)+" is falling behind the times!"
   lineB$="We haven't even got '"+prodName$(gamTarget)+"' yet..."
  EndIf
  If negTim>675 And negTim<975
   Speak(negVisitor,0,1)
   lineA$="Make it part of the show by the "+DescribeDate$(gamDeadline,0)
   lineB$="or I'll bring in somebody with more ambition!"
  EndIf
  If negTim>1025 Then gamMission=13 : go=1
 EndIf
 ;14. ACQUIRE POPULAR WRESTLER
 If negTopic=114
  If negTim>350 And negTim<650 
   Speak(negVisitor,0,1) : gamDeadline=gamDate+4
   lineA$=fedName$(fed)+" lacks star power!"
   lineB$="We need a major name to sell the brand..."
  EndIf
  If negTim>675 And negTim<975
   Speak(negVisitor,0,1)
   lineA$="Let me see one by the "+DescribeDate$(gamDeadline,0)
   lineB$="or I'll lose interest in the whole show!"
  EndIf
  If negTim>1025 Then gamMission=14 : go=1
 EndIf
 ;15. ACQUIRE STRONG WRESTLER
 If negTopic=115
  If negTim>350 And negTim<650 
   Speak(negVisitor,0,1) : gamDeadline=gamDate+4
   lineA$="What good is a wrestling show without muscle?!"
   lineB$="We need to sign a particularly strong wrestler..."
  EndIf
  If negTim>675 And negTim<975
   Speak(negVisitor,0,1)
   lineA$="Let me see one by the "+DescribeDate$(gamDeadline,0)
   lineB$="or I'll lose interest in the whole show!"
  EndIf
  If negTim>1025 Then gamMission=15 : go=1
 EndIf
 ;16. ACQUIRE SKILLFUL WRESTLER
 If negTopic=116
  If negTim>350 And negTim<650 
   Speak(negVisitor,0,1) : gamDeadline=gamDate+4
   lineA$="What good is a wrestling show without talent?!"
   lineB$="We need to sign a particularly skillful wrestler..."
  EndIf
  If negTim>675 And negTim<975
   Speak(negVisitor,0,1)
   lineA$="Let me see one by the "+DescribeDate$(gamDeadline,0)
   lineB$="or I'll lose interest in the whole show!"
  EndIf
  If negTim>1025 Then gamMission=16 : go=1
 EndIf
 ;17. ACQUIRE AGILE WRESTLER
 If negTopic=117
  If negTim>350 And negTim<650 
   Speak(negVisitor,0,1) : gamDeadline=gamDate+4
   lineA$="What good is a wrestling show without energy?!"
   lineB$="We need to sign a particularly agile wrestler..."
  EndIf
  If negTim>675 And negTim<975
   Speak(negVisitor,0,1)
   lineA$="Let me see one by the "+DescribeDate$(gamDeadline,0)
   lineB$="or I'll lose interest in the whole show!"
  EndIf
  If negTim>1025 Then gamMission=17 : go=1
 EndIf
 ;18. ACQUIRE FIT WRESTLER
 If negTopic=118
  If negTim>350 And negTim<650 
   Speak(negVisitor,0,1) : gamDeadline=gamDate+4
   lineA$="What good is a wrestling show without athleticism?!"
   lineB$="We need to sign a particularly fit wrestler..."
  EndIf
  If negTim>675 And negTim<975
   Speak(negVisitor,0,1)
   lineA$="Let me see one by the "+DescribeDate$(gamDeadline,0)
   lineB$="or I'll lose interest in the whole show!"
  EndIf
  If negTim>1025 Then gamMission=18 : go=1
 EndIf
 ;19. ACQUIRE TOUGH WRESTLER
 If negTopic=119
  If negTim>350 And negTim<650 
   Speak(negVisitor,0,1) : gamDeadline=gamDate+4
   lineA$="What good is a wrestling show without balls?!"
   lineB$="We need to sign a particularly tough wrestler..."
  EndIf
  If negTim>675 And negTim<975
   Speak(negVisitor,0,1)
   lineA$="Let me see one by the "+DescribeDate$(gamDeadline,0)
   lineB$="or I'll lose interest in the whole show!"
  EndIf
  If negTim>1025 Then gamMission=19 : go=1
 EndIf
 ;20. ACQUIRE FEMALE WRESTLER
 If negTopic=120
  If negTim>350 And negTim<650 
   Speak(negVisitor,0,1) : gamDeadline=gamDate+4
   lineA$="How come there's not a single woman on the show?!"
   lineB$="We'll lose the female audience with that attitude..."
  EndIf
  If negTim>675 And negTim<975
   Speak(negVisitor,0,1)
   lineA$="Sign a female wrestler by the "+DescribeDate$(gamDeadline,0)
   lineB$="or I'll have to sack you for being chauvinistic!"
  EndIf
  If negTim>1025 Then gamMission=20 : go=1
 EndIf
   
 ;MISSION ACCOMPLISHED! (GENERIC)
 If negTopic=120
  If negTim>25 And negTim<325
   Speak(negVisitor,0,3)
   lineA$="Congratulations, "+charName$(gamChar)+"! You followed"
   lineB$="my advice and made the show better than ever..."
  EndIf
  If negTim>350 And negTim<650 
   Speak(negVisitor,0,3) : negPayOff=50000
   If charged=0 
    PlaySound sCash : charged=1
    fedBank(fed)=fedBank(fed)+negPayOff
   EndIf
   lineA$="As a reward for your struggle, here's a $"+GetFigure$(negPayOff)+" bonus!"
   lineB$="I'll still be watching though, so don't get complacent..."
  EndIf
  If negTim>700 Then gamMission=0 : go=1
 EndIf
 ;MISSION ACCOMPLISHED! (FINANCIAL)
 If negTopic=121
  If negTim>25 And negTim<325
   Speak(negVisitor,0,3)
   lineA$="Congratulations, "+charName$(gamChar)+"! With $"+GetFigure$(fedBank(fed))
   lineB$="in the bank, the show's future is assured..."
  EndIf
  If negTim>350 And negTim<650 
   Speak(negVisitor,0,3)
   lineA$="I wish I could offer you a reward, but you're obviously"
   lineB$="capable of earning your own money! Just keep it up..."
  EndIf
  If negTim>700 Then gamMission=0 : go=1
 EndIf
 ;MISSION ACCOMPLISHED! (RECRUIT)
 If negTopic=122
  g=charGender(negVariable)
  If negTim>25 And negTim<325
   Speak(negVisitor,0,3)
   lineA$="Good work, "+charName$(gamChar)+"! "+charName$(negVariable)+" is"
   lineB$="exactly what "+fedName$(fed)+" needs..."
  EndIf
  If negTim>350 And negTim<650 
   Speak(negVisitor,0,3) : negPayOff=50000
   If charged=0 
    PlaySound sCash : charged=1
    fedBank(fed)=fedBank(fed)+negPayOff
   EndIf
   lineA$="As a reward for signing "+Him$(g)+", here's a $"+GetFigure$(negPayOff)+" bonus!"
   lineB$="I'll still be watching though, so treat "+Him$(g)+" right..."
  EndIf
  If negTim>700 Then gamMission=0 : go=1
 EndIf

 ;MISSED FAILED
 If negTopic=130
  If negTim>25 And negTim<325
   Speak(negVisitor,0,1)
   lineA$="Well, "+charName$(gamChar)+", your time is up and it"
   lineB$="appears that you've failed to follow my advice..."
  EndIf
  If negTim>350 And negTim<650 
   Speak(negVisitor,0,1) : ChannelPitch chTheme,PercentOf#(chThemePitch,90)
   lineA$="Slackers like you don't belong at THIS show!"
   lineB$="Clean out your office and get out of my sight..."
  EndIf
  If negTim>675 Then camFoc=negStar : pEyes(camFoc)=1
  If negTim>825 Then gamMission=0 : go=1
 EndIf
 ;---------- DISPLAY SUBTITLES ----------
 DisplaySubtitles()
 ;---------- OPTION BOX ---------------
 If negStage=1 And negVerdict=0
  ;reflect mood
  mood=2
  If negTopic=1
   If foc=1 Then mood=1
   If foc=2 Then mood=3
  Else
   If foc=1 Then mood=3
   If foc=2 Then mood=1
  EndIf
  Speak(negStar,0,mood)
  ;display options
  DrawOption(1,rX#(400),rY#(520),optionA$,"")
  DrawOption(2,rX#(400),rY#(560),optionB$,"")
  ;stat reminder
  showFed=0
  If negTopic=>23 And negTopic=<25 Then showFed=charFed(negChar)
  If negTopic=28 Then showFed=negVariable
  If showFed>0
   DrawFedProfile(showFed,-1,-1)
  Else
   char=negChar
   If negTopic=7 Or negTopic=8 Or negTopic=9 Or negTopic=10 Then char=negVariable
   If negTopic=11 Then char=charPartner(negChar) 
   If negTopic=12 Then char=charManager(negChar)
   If negTopic=>29 And negTopic=<35 Then char=negVariable
   If negTopic=>47 And negTopic=<50 Then char=negVariable
   If negTopic=56 Or negTopic=57 Then char=negVariable
   If MouseDown(2) And char<>negChar Then char=negChar
   DrawProfile(char,-1,-1,0)
  EndIf
 EndIf
 ;return camera
 If negStage=2 And negTim>10 Then camFoc=negVisitor
 ;cursor
 If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect 
 DrawImage gCursor,MouseX(),MouseY()
 ;mask shaky start
 If gotim=<0 Then Loader("Please Wait","Meeting "+charName$(negChar))

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;restore sound
;ChannelVolume chTheme,1.0	
FreeTimer timer
;free entities
FreeEntity world
FreeEntity cam 
FreeEntity camPivot
FreeEntity dummy
FreeEntity light(1)
FreeEntity lightPivot
;remove characters
For cyc=1 To no_plays
 If pChar(cyc)>0
  FreeEntity p(cyc)
  For limb=1 To 50
   If pShadow(cyc,limb)>0
    FreeEntity pShadow(cyc,limb)
   EndIf
  Next
 EndIf
Next
;proceed
gamNegotiated(negChar,3)=1
screen=20
If negTopic=0 Then cupSlot=3+slot : gamScroll=-((GetMonth(gamDate)-1)*125)
If (negTopic=1 And negVerdict=1) Or negTopic=130 Then screen=25
;detour to 8-man inter-promotional war
If negTopic=23 And negVerdict=1 
 ConstructInterPromotional(4)
EndIf
;detour to invasion brawl
If negTopic=51 And negVerdict=1
 ResetCharacters()
 GetMatchRules(1) : AddGimmick(0)
 If negSetting=>10 And negSetting=<13 Then matchLocation=2 Else matchLocation=1
 If matchLocation=1 Then itemLayout=2 : weapLayout=2
 no_wrestlers=4 : no_plays=4 : matchTeams=1
 pChar(1)=gamChar : pControl(1)=3 : pChar(2)=negChar 
 For count=3 To 4
  Repeat
   newbie=fedRoster(negVariable,Rnd(1,fedSize(negVariable)))
  Until FindCharacter(newbie)=0
  pChar(count)=newbie
 Next
 matchPromo=56
 screen=50 : screenAgenda=12
EndIf
End Function

;/////////////////////////////////////////////////////////////////
;---------------------- RELATED FUNCTIONS ------------------------
;/////////////////////////////////////////////////////////////////

;ADJUST ATTITUDE
Function AdjustAttitude(char,level)
 If level=2 Then charAttitude(char)=charAttitude(char)+1 : charHappiness(char)=charHappiness(char)+1 ;2. thoroughly positive
 If level=1 Then charAttitude(char)=charAttitude(char)+1 : charHappiness(char)=charHappiness(char)-1 ;1. unhappy consent
 If level=-1 Then charAttitude(char)=charAttitude(char)-1 : charHappiness(char)=charHappiness(char)+1 ;-1. happy rebellion
 If level=-2 Then charAttitude(char)=charAttitude(char)-1 : charHappiness(char)=charHappiness(char)-1 ;-2. thoroughly negative
 ;limits
 randy=Rnd(0,1)
 If randy=0 And charHappiness(char)>90 And charHappiness(char)>charOldHappiness(char) Then charHappiness(char)=charOldHappiness(char)
 If randy=1 And charAttitude(char)>90 And charAttitude(char)>charOldAttitude(char) Then charAttitude(char)=charOldAttitude(char)
 If charAttitude(char)>99 Then charAttitude(char)=99
 If charHappiness(char)>99 Then charHappiness(char)=99
End Function

;RANDOMIZE MEETING ANIMATIONS
Function RandomizeAnimation(cyc)
 randy=Rnd(0,5000)
 ;seated animations
 If randy=<1 And pAnim(cyc)=>1 And pAnim(cyc)=<9
  Repeat
   pAnim(cyc)=Rnd(1,9) : satisfied=1
   If pAnim(cyc)=<3 And negSetting=1 And cyc=1 Then satisfied=0
   If pAnim(cyc)=9 And negSetting=1 And cyc=2 Then satisfied=0
  Until satisfied=1
 EndIf
 ;standing animations 
 If pAnim(cyc)=>10 And pAnim(cyc)=<14
  If randy=<1 Then pAnim(cyc)=Rnd(10,14) 
  If randy=<10 And pSpeaking(cyc)>0 Then pAnim(cyc)=14
  If pAnim(cyc)=14 And pSpeaking(cyc)=0 Then pAnim(cyc)=Rnd(10,13) 
 EndIf
 ;activate
 If pAnim(cyc)<>pOldAnim(cyc)
  animSpeed#=Rnd#(0.1,0.3)
  Animate p(cyc),1,animSpeed#,pSeq(cyc,pAnim(cyc)),20
  pOldAnim(cyc)=pAnim(cyc)
 EndIf
End Function