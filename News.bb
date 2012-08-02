;//////////////////////////////////////////////////////////////////////////////
;------------------------- WRESTLING MPIRE 2008: NEWS REPORTS -----------------
;//////////////////////////////////////////////////////////////////////////////

;------------------------------------------------------------------------
;///////////////////// 22. INTRODUCE NEW WEEK ///////////////////////////
;------------------------------------------------------------------------
Function IntroduceDate()
;advance date
AdvanceDate()
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
x1=-100 : x2=900
go=0 : gotim=0 : keytim=0
While go=0

 Cls
 frames=WaitTimer(timer)
 For framer=1 To frames 
	
    ;PORTAL
    gotim=gotim+1
	If gotim>20
	 If KeyDown(1) Or KeyDown(28) Or ButtonPressed() Or MouseDown(1) Then go=1
	EndIf
	If gotim>500 Then go=1 
	;swing in boxes
	x1=x1+20
	If x1>305 Then x1=305
	x2=x2-20
	If x2<495 Then x2=495
	
 UpdateWorld
 Next
 RenderWorld 1

 ;DISPLAY
 DrawImage gBackground,rX#(400),rY#(300)
 DrawImage gLogo(1),rX#(400),rY#(250)
 DrawImage gMDickie,rX#(400),rY#(530)
 ;date boxes
 If x1<305 And x2>495
  DrawOption(-1,rX#(x1),rY#(425),"New Week","")
  DrawOption(-1,rX#(x2),rY#(425),textWeek$(GetWeek(gamDate))+" of "+textMonth$(GetMonth(gamDate)),"")
 Else
  DrawOption(-1,rX#(400),rY#(425),"New Week",textWeek$(GetWeek(gamDate))+" of "+textMonth$(GetMonth(gamDate)))
 EndIf
 ;cursor
 DrawImage gCursor,MouseX(),MouseY()

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;proceed to news
FreeTimer timer
SaveUniverse()
SaveProgress(slot)
SaveWorld(slot)
SaveChars(slot) 
screen=30
End Function

;------------------------------------------------------------------
;///////////////////// ADVANCE DATE PROCESS ///////////////////////
;------------------------------------------------------------------
Function AdvanceDate()
 ;inspire new destinies
 SeedRnd(MilliSecs())
 ;advance date
 gamDate=gamDate+1
 If gamDate>48 
  gamDate=1 : gamYear=gamYear+1
  For char=1 To no_chars
   If charFed(char)=<8 Then charAge(char)=charAge(char)+1
  Next
  ResetSchedule(1)
 EndIf
 ;reset card
 gamSegments(gamDate)=0
 For count=1 To 10
  gamMatchScore(gamDate,count)=0
  gamMatchHardcore(gamDate,count)=0
  gamMatchFormat(gamDate,count)=0 
  gamMatchWinner(gamDate,count)=0
  gamMatchLoser(gamDate,count)=0
 Next
 ;expire career agreements
 For count=1 To 50
  If count<>21 Then gamAgreement(count)=gamAgreement(count)-1
  If gamAgreement(count)<0 Then gamAgreement(count)=0
 Next
 ;expire productions
 For count=1 To 12
  If fedProduction(charFed(gamChar),count)>0 Then fedProduction(charFed(gamChar),count)=fedProduction(charFed(gamChar),count)+1
 Next
 ;reset character handles
 For char=1 To no_chars
  For count=1 To 3
   gamNegotiated(char,count)=0
  Next
  charOpponent(char)=0
  charFought(char)=0
  charBracket(char)=0
  ResetOldValues(char)
 Next
 ;clock promotion title status
 For cyc=1 To 6
  fedOldChampWorld(cyc)=fedChampWorld(cyc)
  fedOldChampInter(cyc)=fedChampInter(cyc)
  For count=1 To 2
   fedOldChampTag(cyc,count)=fedChampTag(cyc,count)
  Next
 Next
 ;ensure booker
 fedBooker(charFed(gamChar))=gamChar
 ;CHARACTER DEVELOPMENTS
 For char=1 To no_chars
  ;health status
  For count=0 To 5
   charOldInjured(char,count)=charInjured(char,count)
   charInjured(char,count)=charInjured(char,count)-1
   If charInjured(char,count)<0 Then charInjured(char,count)=0
  Next
  charOldHealth(char)=charHealth(char)
  healer=Rnd(charStamina(char)/4,charStamina(char))
  If fedProduction(charFed(gamChar),2)>0 Or charFed(char)<>charFed(gamChar) Then healer=Rnd(charStamina(char)/2,charStamina(char))
  charHealth(char)=charHealth(char)+healer
  If charHealth(char)>100 Then charHealth(char)=100
  If charTrainCourse(char)>0
   If charTrainLevel(char)=1 And charHealth(char)>75 Then charHealth(char)=75
   If charTrainLevel(char)=2 And charHealth(char)>50 Then charHealth(char)=50
   If charTrainLevel(char)=3 And charHealth(char)>25 Then charHealth(char)=25
  EndIf
  If InjuryStatus(char)>0 Then charHealth(char)=0
  ;contract status
  charOldVacant(char)=charVacant(char)
  charVacant(char)=charVacant(char)-1
  If charVacant(char)<0 Then charVacant(char)=0
  If charArrived(char)>0
   charContract(char)=charContract(char)-1
   If charContract(char)<0 Then charContract(char)=0
   charExperience(char,charFed(char))=charExperience(char,charFed(char))+1
  EndIf
  ;assign CPU matches
  randy=Rnd(0,8) : workRate=2
  If TitleHolder(char,0)>0 Then workRate=workRate-1
  If TitleHolder(char,3) Then workRate=workRate-1
  If randy=<workRate And FightAvailable(char) And charFed(char)<>charFed(gamChar)
   charOpponent(char)=AssignOpponent(char,gamDate,0)
  EndIf
  ;execute CPU matches
  If charOpponent(char)>0 And charFought(char)=0
   v=charOpponent(char) : charOpponent(v)=char
   charFought(char)=1 : charMatches(char,charFed(char))=charMatches(char,charFed(char))+1
   charFought(v)=1 : charMatches(v,charFed(v))=charMatches(v,charFed(v))+1
   If char=fedChampTag(charFed(char),1) Then charFought(fedChampTag(charFed(char),2))=1
   If char=fedChampTag(charFed(char),2) Then charFought(fedChampTag(charFed(char),1))=1
   If v=fedChampTag(charFed(v),1) Then charFought(fedChampTag(charFed(v),2))=1
   If v=fedChampTag(charFed(v),2) Then charFought(fedChampTag(charFed(v),1))=1
   winner=Rnd(-2,2)
   If (winner=1 Or winner=-1) And TitleHolder(v,0)>0 Then winner=2
   If (winner=2 Or winner=-2) And TitleHolder(char,0)>0 Then winner=1
   If winner=<0
    If PercentOf#(AverageStats(char),charHealth(char))=>PercentOf#(AverageStats(v),charHealth(v)) Then winner=1 Else winner=2
   EndIf 
   If winner=1 Then WinEffect(char,v)
   If winner=2 Then WinEffect(v,char)
  EndIf
  ;stat fluctuations
  If ((InjuryStatus(char)=0 And charHealth(char)>50) Or charOpponent(char)>0) And charFed(char)<>charFed(gamChar)
   randy=Rnd(0,10) : level=Rnd(1,6)
   If randy=0 Then charStrength(char)=charStrength(char)+PursueValue(charStrength(char),statLevel(level),1)
   randy=Rnd(0,10) : level=Rnd(1,6)
   If randy=0 Then charSkill(char)=charSkill(char)+PursueValue(charSkill(char),statLevel(level),1)
   randy=Rnd(0,10) : level=Rnd(1,6)
   If randy=0 Then charAgility(char)=charAgility(char)+PursueValue(charAgility(char),statLevel(level),1)
   randy=Rnd(0,10) : level=Rnd(1,6)
   If randy=0 Then charStamina(char)=charStamina(char)+PursueValue(charStamina(char),statLevel(level),1)
   randy=Rnd(0,10) : level=Rnd(1,6)
   If randy=0 Then charToughness(char)=charToughness(char)+PursueValue(charToughness(char),statLevel(level),1)
   ;limit changes if old
   If charPeaked(char)>0
    If charStrength(char)>charOldStrength(char) Then charStrength(char)=charOldStrength(char)
    If charSkill(char)>charOldSkill(char) Then charSkill(char)=charOldSkill(char)
    If charAgility(char)>charOldAgility(char) Then charAgility(char)=charOldAgility(char)
    If charStamina(char)>charOldStamina(char) Then charStamina(char)=charOldStamina(char)
    If charToughness(char)>charOldToughness(char) Then charToughness(char)=charOldToughness(char) 
   EndIf
  EndIf
  ;personality fluctuations
  If char<>gamChar And charFed(char)<>charFed(gamChar)
   randy=Rnd(0,10) : level=Rnd(1,6)
   If randy=0 Then charAttitude(char)=charAttitude(char)+PursueValue(charAttitude(char),statLevel(level),1)
   randy=Rnd(0,10) : level=Rnd(1,6)
   If randy=0 Then charHappiness(char)=charHappiness(char)+PursueValue(charHappiness(char),statLevel(level),1)
  EndIf
  ;training effects
  If InjuryStatus(char)>0 Or charVacant(char)>0 Or charFed(char)<>charFed(gamChar) Then charTrainCourse(char)=0
  If charFed(char)=charFed(gamChar) And charTrainCourse(char)>0 And charPeaked(char)=0
   randy=Rnd(0,3-charTrainLevel(char))
   If fedProduction(charFed(gamChar),3)>0 Then randy=Rnd(-1,3-charTrainLevel(char)) 
   If randy=<0 ;dedicated training
    If charTrainCourse(char)=1 Then charStrength(char)=charStrength(char)+PursueValue(charStrength(char),100,Rnd(1,charTrainLevel(char)))
    If charTrainCourse(char)=2 Then charSkill(char)=charSkill(char)+PursueValue(charSkill(char),100,Rnd(1,charTrainLevel(char)))
    If charTrainCourse(char)=3 Then charAgility(char)=charAgility(char)+PursueValue(charAgility(char),100,Rnd(1,charTrainLevel(char)))
    If charTrainCourse(char)=4 Then charStamina(char)=charStamina(char)+PursueValue(charStamina(char),100,Rnd(1,charTrainLevel(char)))
    If charTrainCourse(char)=5 Then charToughness(char)=charToughness(char)+PursueValue(charToughness(char),100,Rnd(1,charTrainLevel(char)))
   EndIf
   If charTrainCourse(char)=6 ;universal training 
    For count=1 To charTrainLevel(char)
     randy=Rnd(1,10)
     If fedProduction(charFed(gamChar),3)>0 Then randy=Rnd(0,5)
     If randy=1 Then charStrength(char)=charStrength(char)+1
     If randy=2 Then charSkill(char)=charSkill(char)+1
     If randy=3 Then charAgility(char)=charAgility(char)+1
     If randy=4 Then charStamina(char)=charStamina(char)+1
     If randy=5 Then charToughness(char)=charToughness(char)+1
    Next
   EndIf
   FindWeightChanges(char)
  EndIf
  ;weight changes
  charWeightChange(char)=charWeightChange(char)+Rnd(-1,1)
  If charAgreement(char,9)>0 And charWeightChange(char)<1 Then charWeightChange(char)=1
  If charAgreement(char,10)>0 And charWeightChange(char)>-1 Then charWeightChange(char)=-1
  charWeight(char)=charWeight(char)+charWeightChange(char)
  charWeightChange(char)=0
  ;link booker popularity to fed
  If char=fedBooker(charFed(char)) 
   charPopularity(char)=charPopularity(char)+PursueValue(charPopularity(char),fedPopularity(charFed(char)),0) 
   charHappiness(char)=charHappiness(char)+PursueValue(charHappiness(char),fedPopularity(charFed(char)),0)
   charAttitude(char)=charAttitude(char)+PursueValue(charAttitude(char),fedReputation(charFed(char)),0)
  EndIf
  ;develop relationships
  charRelationship(char,char)=0
  For v=1 To no_chars
   If charRelationship(char,v)>0 Then charRelationship(char,v)=charRelationship(char,v)+1
   If charRelationship(char,v)<0 Then charRelationship(char,v)=charRelationship(char,v)-1
   randy=Rnd(0,100-MakePositive#(charRelationship(char,v)))
   If randy=0 And (charRelationship(char,v)>4 Or charRelationship(char,v)<-4) Then charRelationship(char,v)=0
   If charRealRelationship(char,v)>0 Then charRealRelationship(char,v)=charRelationship(char,v)+1
   If charRealRelationship(char,v)<0 Then charRealRelationship(char,v)=charRelationship(char,v)-1
   randy=Rnd(0,100-MakePositive#(charRealRelationship(char,v)))
   If randy=0 And (charRealRelationship(char,v)>4 Or charRealRelationship(char,v)<-4) Then charRealRelationship(char,v)=0
   If charRelationship(char,v)>0 And charTeamHistory(char,v)<1 Then charTeamHistory(char,v)=1
  Next 
  If charPartner(char)>0 Then charTeamHistory(char,charPartner(char))=2 : charTeamHistory(charPartner(char),char)=2
  If charManager(char)>0 Then charTeamHistory(char,charManager(char))=3 : charTeamHistory(charManager(char),char)=3 
  ;expire personal agreements
  For count=1 To 50
   charAgreement(char,count)=charAgreement(char,count)-1
   If charAgreement(char,count)<0 Then charAgreement(char,count)=0
  Next
  If charAgreement(char,11)>0 And (TitleHolder(char,1) Or charFed(char)<>charFed(gamChar)) Then charAgreement(char,11)=0
  If charAgreement(char,12)>0 And (TitleHolder(char,2) Or charFed(char)<>charFed(gamChar)) Then charAgreement(char,12)=0
  If charAgreement(char,13)>0 And (TitleHolder(char,3) Or charFed(char)<>charFed(gamChar)) Then charAgreement(char,13)=0
 Next
 ;check changes
 For char=1 To no_chars
  CheckLimits(char)
  ResetNewValues(char)
 Next
 ;PROMOTION DEVELOPMENTS
 For fed=1 To 9
  ;store old values
  fedOldPopularity(fed)=fedPopularity(fed)
  fedOldReputation(fed)=fedReputation(fed)
  ;fluctuate values
  If fed=<6 And fed<>charFed(gamChar)
   randy=Rnd(0,2) : level=Rnd(2,6)
   If randy=0 Then fedPopularity(fed)=fedPopularity(fed)+PursueValue(fedPopularity(fed),statLevel(level),1)
   randy=Rnd(0,2) : level=Rnd(2,6)
   If randy=0 Then fedReputation(fed)=fedReputation(fed)+PursueValue(fedReputation(fed),statLevel(level),1)
   ;finances
   factor=Rnd(10,20)
   casher=fedBank(fed)/factor
   casher=RoundOff(casher,1000)
   If casher<1000 Then casher=1000
   randy=Rnd(-2,2)
   If (randy<0 Or fedBank(fed)>2000000) And fedBank(fed)>100000 Then fedBank(fed)=fedBank(fed)-casher
   If (randy>0 Or fedBank(fed)<100000) And fedBank(fed)<2000000 Then fedBank(fed)=fedBank(fed)+casher
   If fedBank(fed)<0 Then fedBank(fed)=0
  EndIf
  ;champion logic
  If fed=>7 Then fedChampWorld(fed)=0 : fedChampInter(fed)=0 : fedChampTag(fed,1)=0 : fedChampTag(fed,1)=0
  If charFed(fedChampWorld(fed))<>fed Then fedChampWorld(fed)=0
  If charFed(fedChampInter(fed))<>fed Then fedChampInter(fed)=0
  If charFed(fedChampTag(fed,1))<>fed Or charFed(fedChampTag(fed,2))<>fed Then fedChampTag(fed,1)=0 : fedChampTag(fed,2)=0
  ;check limits
  CheckFedLimits(fed)
 Next
 ;rank promotions
 RankPromotions()
End Function

;--------------------------------------------------------------------
;////////////////////// FIND EVENTS FOR NEWS ////////////////////////
;--------------------------------------------------------------------
Function FindEvents()
 ;reset events
 no_events=0
 For count=1 To 200
  ResetEvent(count)
 Next
 gamNewcomer=0
 ;SCAN ROSTER FOR EVENTS
For fed=1 To 7
 For cyc=1 To fedSize(fed)
  char=fedRoster(fed,cyc)
  charEvent(char)=0 : charUpdated(char)=0
  ResetNewValues(char)
  ;1-20: HEALTH ISSUES
  If char<>gamChar And InjuryStatus(char)=0
   ;match injuries
   If charOpponent(char)>0 And charFed(char)<>charFed(gamChar)
    randy=Rnd(0,charToughness(char)*300)
    ;If randy=<10 Then AddEvent(char,1) ;minor injury from match
    If randy=>11 And randy=<15 Then AddEvent(char,2) ;major injury from match
    If randy=16 Then AddEvent(char,3) ;paralysis from match
    If randy=17 And fedSize(fed)>optRosterLim/2 And fedSize(9)<optRosterLim/4 Then AddEvent(char,4) ;death from match 
   EndIf
   ;accidents
   randy=Rnd(0,charToughness(char)*750)
   If randy=<10 And charFed(char)=charFed(gamChar) Then AddEvent(char,5) ;minor injury from accident
   If randy=>11 And randy=<15 Then AddEvent(char,6) ;major injury from accident
   If randy=16 Then AddEvent(char,7) ;paralysis from accident
   If fedSize(fed)>optRosterLim/2 And fedSize(9)<optRosterLim/4
    If randy=17 Then AddEvent(char,8) ;death from accident
    randy=Rnd(0,charAttitude(char)*500)
    If randy=0 Then AddEvent(char,9) ;hedonistic death
    If randy=1 Then AddEvent(char,10) ;violent death
    randy=Rnd(0,(charPopularity(char)*charHappiness(char))*5)
    If randy=0 Then AddEvent(char,11) ;death from suicide
    randy=Rnd(0,500000)
    If randy=<charAge(char) And charAge(char)=>50 Then AddEvent(char,12) ;death from old age
   EndIf
  EndIf
  ;return from injury
  If charFed(char)=charFed(gamChar) And InjuryStatus(char)=0 And OldInjuryStatus(char)>0 Then AddEvent(char,13) 
  ;20-40: CONTRACT ISSUES
  If char<>gamChar And charContract(char)=0
   ;assess chances
   chance=50
   If fed=7 And char<>fedBooker(fed)
    chance=(90-GetValue(char))*10
    If fedSize(7)<16 Then chance=chance*2 
    If fedSize(7)=>32 Then chance=chance/2
    If chance<10 Then chance=10
   EndIf
   randy=Rnd(0,chance)
   ;renew contract
   If (fed<>7 Or char=fedBooker(fed)) And charFed(char)<>charFed(gamChar)
    If (randy=<1 And fedSize(fed)<optRosterLim) Or (randy=<3 And fedSize(fed)=<32 And charHappiness(char)=>60)
     AddEvent(char,22)
    EndIf
   EndIf
   ;move elsewhere
   If (fedSize(fed)=>16 Or charFed(char)=charFed(gamChar)) And (charRole(char)<>3 Or charFed(char)=charFed(gamChar))
    If randy=4 Or (randy=<7 And charFed(char)=charFed(gamChar)) Then AddEvent(char,23)
    ;released to school
    If charFed(char)<>charFed(gamChar) And fed<>7 And fedSize(7)=<32 And char<>fedBooker(fed) And GetValue(char)<70 And charPopularity(char)<fedPopularity(fed)-5 And TitleHolder(char,0)=0
     If randy=5 Or (randy=6 And fedSize(fed)>32) Or (randy=7 And fedSize(7)<16)
      AddEvent(char,24) 
     EndIf 
    EndIf
    ;retirement
    chance=(70-charAge(char))*20
    If fed=7 And char<>fedBooker(fed) Then chance=chance*5
    randy=Rnd(0,chance)
    If randy=<InjuryStatus(char) And charAge(char)=>30 And fedSize(fed)=>16 And fedSize(8)<16 Then AddEvent(char,25)
   EndIf
  EndIf
  ;contract status
  If char<>gamChar And fed=<6
   randy=Rnd(0,5)
   If (randy=0 Or charFed(char)=charFed(gamChar)) And charContract(char)=1 Then AddEvent(char,20) ;expires soon
   If charContract(char)=0 And charFed(char)=charFed(gamChar) Then AddEvent(char,21) ;expired 
  EndIf
  ;become available
  If charFed(char)=charFed(gamChar) And charVacant(char)=0
   If charOldVacant(char)>0 And charArrived(char)>0 Then AddEvent(char,27) ;return from absence
   If charArrived(char)=0 Then AddEvent(char,30) ;arrived to work
  EndIf
  ;reaction to trades
  If charTradeReaction(char)<>0
   randy=Rnd(0,10)
   If randy=0 And charFed(char)=charFed(gamChar)
    If charTradeReaction(char)>0 Then AddEvent(char,28) ;happy about move
    If charTradeReaction(char)<0 Then AddEvent(char,29) ;unhappy about move
   Else
    If charTradeReaction(char)>0 Then charAttitude(char)=charAttitude(char)+1 : charHappiness(char)=charHappiness(char)+1
    If charTradeReaction(char)<0 Then charAttitude(char)=charAttitude(char)-1 : charHappiness(char)=charHappiness(char)-1
   EndIf
   charTradeReaction(char)=0
  EndIf
  ;40-60: STATUS ISSUES
  ;stripped of titles (or should be)
  If (InjuryStatus(char)=>4 And charOpponent(char)=0) Or charVacant(char)=>4
   If charFed(char)=charFed(gamChar)
    If TitleHolder(char,1) Then AddEvent(char,46) 
    If TitleHolder(char,2) Then AddEvent(char,47) 
    If TitleHolder(char,3) Then AddEvent(char,48) 
   Else
    If TitleHolder(char,1) Then AddEvent(char,40) 
    If TitleHolder(char,2) Then AddEvent(char,41) 
    If TitleHolder(char,3) Then AddEvent(char,42) 
   EndIf
  EndIf 
  ;acknowledge new champion
  If char=fedChampWorld(fed) And char<>fedOldChampWorld(fed) Then AddEvent(char,43) 
  If char=fedChampInter(fed) And char<>fedOldChampInter(fed) Then AddEvent(char,44) 
  If char=fedChampTag(fed,1) And char<>fedOldChampTag(fed,1) And char<>fedOldChampTag(fed,2) Then AddEvent(char,45)
  ;49=tournament replacement
  ;title promises
  If charFed(char)=charFed(gamChar)
   If charAgreement(char,11)>1 Then AddEvent(char,51) ;world title promise
   If charAgreement(char,12)>1 Then AddEvent(char,52) ;inter title promise
   If charAgreement(char,13)>1 Then AddEvent(char,53) ;tag title promise
   If charAgreement(char,11)=1 Or charAgreement(char,12)=1 Or charAgreement(char,13)=1 Then AddEvent(char,50) ;failed promise
  EndIf   
  ;60-100: MISC EVENTS
  If charFed(char)=charFed(gamChar)
   ;injury gets worse
   randy=Rnd(0,charToughness(char))
   For count=0 To 5
    If randy=count And charInjured(char,count)>0 Then eVariable(no_events+1)=count : AddEvent(char,60)
   Next  
   ;injury gets better
   randy=Rnd(0,(110-charToughness(char))*2)
   For count=0 To 5
    If randy=count And charInjured(char,count)>1 Then eVariable(no_events+1)=count : AddEvent(char,61) 
   Next
   ;health fluctuation
   randy=Rnd(0,100)
   If randy=0 And InjuryStatus(char)=0 And charHealth(char)>50 Then AddEvent(char,62) ;feeling ill
   If randy=1 And InjuryStatus(char)=0 And charHealth(char)<75 Then AddEvent(char,63) ;feeling good
   ;body changes
   If charGender(char)=0
    randy=Rnd(0,200)
    If (randy=0 Or (randy=1 And char=gamChar And gamAgreement(13)>0)) And charWeight(char)=<275 Then AddEvent(char,64) ;gain weight
    If (randy=2 Or (randy=3 And char=gamChar And gamAgreement(14)>0)) And charWeight(char)=>100 Then AddEvent(char,65) ;lose weight
   EndIf 
   If randy=>2 And randy=<3 And charHeight(char)<24
    If charAge(char)=<21 Or (char=gamChar And gamAgreement(13)>0) Then AddEvent(char,66) ;grow taller 
   EndIf
  EndIf
  ;reach peak
  chance=(50-charAge(char))*50
  If chance<0 Then chance=0
  randy=Rnd(0,chance) 
  If randy=0 And charAge(char)=>30 And charPeaked(char)=0 Then AddEvent(char,67) 
  ;spontaneous stat improvements
  If charFed(char)=charFed(gamChar)
   randy=Rnd(0,(110-charAttitude(char))*20)
   If randy=1 And charPopularity(char)<90 Then AddEvent(char,68) ;popularity
   If charPeaked(char)=0
    If randy=2 And charStrength(char)<90 Then AddEvent(char,69) ;strength
    If randy=3 And charSkill(char)<90 Then AddEvent(char,70) ;skill
    If randy=4 And charAgility(char)<90 Then AddEvent(char,71) ;agility
    If randy=5 And charStamina(char)<90 Then AddEvent(char,72) ;stamina
    If randy=6 And charToughness(char)<90 Then AddEvent(char,73) ;toughness
   EndIf
   If randy=7 And charAttitude(char)<90 And char<>gamChar Then AddEvent(char,74) ;attitude
   If randy=8 And charHappiness(char)<90 And char<>gamChar Then AddEvent(char,75) ;happiness
   ;spontaneous stat deteriorations
   randy=Rnd(0,charAttitude(char)*10)
   If randy=1 And charPopularity(char)>60 Then AddEvent(char,76) ;popularity
   If randy=2 And charStrength(char)>60 Then AddEvent(char,77) ;strength
   If randy=3 And charSkill(char)>60 Then AddEvent(char,78) ;skill
   If randy=4 And charAgility(char)>60 Then AddEvent(char,79) ;agility
   If randy=5 And charStamina(char)>60 Then AddEvent(char,80) ;stamina
   If randy=6 And charToughness(char)>60 Then AddEvent(char,81) ;toughness
   If randy=7 And charAttitude(char)>60 And char<>gamChar Then AddEvent(char,82) ;attitude
   If randy=8 And charHappiness(char)>60 And char<>gamChar Then AddEvent(char,83) ;happiness
  EndIf
  ;drug side effects
  If charAgreement(char,9)>0 Or charAgreement(char,10)>0
   randy=Rnd(0,6)
   If randy=<1 And charAgreement(char,9)>0 And charWeight(char)=<275 Then AddEvent(char,64) ;gain weight
   If randy=<1 And charAgreement(char,10)>0 And charWeight(char)=>125 Then AddEvent(char,65) ;lose weight
   If randy=2 And InjuryStatus(char)=0 Then AddEvent(char,86) ;withdrawl symptons
   If randy=3 And charPopularity(char)=>60 Then AddEvent(char,87) ;exposed
  EndIf
  ;lack of exposure damages popularity
  randy=Rnd(0,10)
  If randy=0 And charFed(char)=charFed(gamChar) And charWorked(char)=0 And charPopularity(char)>50 Then AddEvent(char,88)
  ;training issues
  If charFed(char)=charFed(gamChar) And charTrainCourse(char)>0
   If charPeaked(char)>0 Then AddEvent(char,89) ;too old to benefit
   chance=(charAttitude(char)-25)+(charHappiness(char)-25)
   randy=Rnd(1,chance)
   If randy=<charTrainLevel(char) And char<>gamChar Then AddEvent(char,90) ;storm out of training
   randy=Rnd(1,charToughness(char)*2)
   If randy=<charTrainLevel(char) And char<>gamChar Then AddEvent(char,91) ;training injury 
  EndIf
  ;tardiness
  If charFed(char)=charFed(gamChar) And char<>gamChar And charVacant(char)=0 And InjuryStatus(char)=0 And gamSchedule(gamDate)>0
   chance=(charAttitude(char)-25)+(charHappiness(char)-25)
   randy=Rnd(0,chance)
   If randy=0 Then AddEvent(char,92) ;failed to turn up
   If randy=1 Then AddEvent(char,93) ;turn up drunk!
   chance=(charAttitude(char)-25)*5
   randy=Rnd(0,chance)
   If randy=0 Then AddEvent(char,94) ;arrested!
  EndIf
  ;unhappy about not being used
  If charFed(char)=charFed(gamChar) And char<>gamChar And charWorked(char)=0 And gamDate>1 And gamSchedule(gamDate-1)>0 And gamSchedule(gamDate-1)<>4 And charOldHealth(char)=>50 And OldInjuryStatus(char)=0 And charOldVacant(char)=0 
   chance=120-GetValue(char)
   If charClause(char,2)=<1 Then chance=chance/2
   If charClause(char,2)=0 Then chance=chance/2
   If gamSchedule(gamDate-1)=>2 Then chance=chance/2
   randy=Rnd(0,chance)
   If randy=<1 And charPopularity(char)=>60 Then AddEvent(char,95) 
  EndIf
  ;100-130: RELATIONSHIP ISSUES
  If charFed(char)=charFed(gamChar)
   ;management
   If charManager(char)>0
    randy=Rnd(0,50)
    If randy=0 And charPopularity(char)<charPopularity(charManager(char)) Then AddEvent(char,103) ;manager boosts profile
    If randy=1 And charPopularity(char)>charPopularity(charManager(char)) Then AddEvent(char,104) ;manager taints profile
   EndIf
   ;team-mates
   If charPartner(char)>0
    randy=Rnd(0,50)
    If randy=0 And charPopularity(char)<charPopularity(charPartner(char)) Then AddEvent(char,108) ;partner boosts profile
    If randy=1 And charPopularity(char)>charPopularity(charPartner(char)) Then AddEvent(char,109) ;partner taints profile
   EndIf
   ;relationships
   randy=Rnd(0,400)
   If randy=1 And char<>gamChar Then AddEvent(char,110) ;internal friendship
   If randy=2 And char<>gamChar Then AddEvent(char,111) ;internal rivalry
   If randy=3 And char<>gamChar Then AddEvent(char,112) ;external friendship
   If randy=4 And char<>gamChar Then AddEvent(char,113) ;external rivalry 
   If randy=5 And char<>gamChar Then AddEvent(char,114) ;settle differences
   For v=1 To no_chars
    If char<>v
     ;feud loses momentum
     randy=Rnd(0,10)
     If randy=0 And charFed(v)=charFed(char) And charRelationship(char,v)=-5 ;And charEvent(char)<>115
      charRelationship(v,char)=-6 
      eVariable(no_events+1)=v 
      AddEvent(char,115)
     EndIf
     ;brawl with enemy
     If charFed(v)=charFed(char) And charRealRelationship(char,v)<0 And char<>gamChar And v<>gamChar And InjuryStatus(char)=0 And InjuryStatus(v)=0 And charVacant(char)=0 And charVacant(v)=0
      chance=((charAttitude(char)-25)+(charAttitude(v)-25))*5
      randy=Rnd(0,chance)
      If randy=0 Then eVariable(no_events+1)=v : AddEvent(char,118)
     EndIf
     ;take inspiration from friend
     If charFed(v)=charFed(char) And charRealRelationship(char,v)>0 And char<>gamChar And v<>gamChar And charVacant(char)=0 And charVacant(v)=0
      chance=((110-charAttitude(char))+(110-charAttitude(v)))*5
      randy=Rnd(0,chance)
      If randy=0 Then eVariable(no_events+1)=v : AddEvent(char,119)
     EndIf
    EndIf
   Next
   ;fans determine allegiance
   If fed=<6
    chance=100
    If charHeel(char)=1 And AllegianceRatio(fed,0)<AllegianceRatio(fed,1)-2 Then chance=chance/2
    If charHeel(char)=0 And AllegianceRatio(fed,1)<AllegianceRatio(fed,0)-2 Then chance=chance/2
    If charHeel(char)=1 And AllegianceRatio(fed,0)>AllegianceRatio(fed,1)+2 Then chance=chance*2
    If charHeel(char)=0 And AllegianceRatio(fed,1)>AllegianceRatio(fed,0)+2 Then chance=chance*2
    randy=Rnd(0,chance)
    If randy=0 And charHeel(char)=0 Then AddEvent(char,116) ;refuse to accept face
    If randy=1 And charHeel(char)=1 Then AddEvent(char,117) ;refuse to accept heel
   EndIf
  EndIf
  ;130-150: COURT CASES
  If char<>gamChar And charEvent(char)=0 And gamDate=<47 And CourtDate()=0
   ;sexual abuse
   chance=(charAttitude(char)+charHappiness(char))*2
   randy=Rnd(1,chance)
   If randy=<charGender(char)*4 And fed=charFed(gamChar) Then AddEvent(char,131) 
   ;unfair dismissal
   chance=(charAttitude(char)+charHappiness(char))*2
   randy=Rnd(0,chance)
   If randy=<10 And fed<>charFed(gamChar) And charAgreement(char,16)>0 Then AddEvent(char,132)  
   ;imprisonment
   chance=(charAttitude(char)+charHappiness(char))*2
   randy=Rnd(0,chance)
   If randy=<10 And fed=charFed(gamChar) And charAgreement(char,17)>0 Then AddEvent(char,133)
   ;career sabotage
   chance=(charAttitude(char)+charHappiness(char))*2
   randy=Rnd(0,chance)
   If randy=<10 And fed=charFed(gamChar) And charAgreement(char,18)>0 And TitleHolder(char,0)=0 Then AddEvent(char,134) 
   ;bodily harm
   chance=charAttitude(char)+charHappiness(char)
   randy=Rnd(0,chance)
   If randy=<10 And charAgreement(char,19)>0 Then AddEvent(char,135) 
   ;drug abuse
   chance=charAttitude(char)+charHappiness(char)
   randy=Rnd(0,chance)
   If randy=<10 And (charAgreement(char,9)>0 Or charAgreement(char,10)>0) Then AddEvent(char,136) 
   ;bureaucracy
   chance=(charAttitude(char)+charHappiness(char))*10
   randy=Rnd(0,chance)
   If randy=<1 And fed=charFed(gamChar) And charClause(char,2)=<1 Then AddEvent(char,137) 
  EndIf
  ;*REPLACE IN TOURNAMENT
  If TournamentStatus(char)>0 And (charFed(char)<>charFed(gamChar) Or charVacant(char)>0) Then AddEvent(char,49)
 Next
 ;PROMOTION ISSUES (200-210)
 fedNewPopularity(fed)=fedPopularity(fed)
 fedNewReputation(fed)=fedReputation(fed)  
 ;generate new character
 If gamNewcomer=0
  randy=Rnd(0,100)
  If randy=0 Or (randy=<5 And fed=7) Then AddEvent(0,31)
 EndIf
 ;buy talent
 randy=Rnd(0,fedSize(fed))
 If randy=0 And fed=<6 And fed<>charFed(gamChar) And fedSize(fed)=<32 Then AddEvent(0,26) 
 ;new booker
 If fed<>charFed(gamChar)
  randy=Rnd(0,1000)
  If randy=<1 Or fedBooker(fed)=0 Or charFed(fedBooker(fed))<>fed Then AddEvent(0,200)
 EndIf
 ;vacant titles
 If fed=<6 And fed<>charFed(gamChar) And fedSize(fed)=>5
  If fedChampWorld(fed)=0 Or charFed(fedChampWorld(fed))<>fed Then AddEvent(0,201) ;new world champ
  If fedChampInter(fed)=0 Or charFed(fedChampInter(fed))<>fed Then AddEvent(0,202) ;new inter champ
  If fedChampTag(fed,1)=0 Or fedChampTag(fed,2)=0 Or charFed(fedChampTag(fed,1))<>fed Or charFed(fedChampTag(fed,2))<>fed Then AddEvent(0,203) ;new tag champs
 EndIf
 If fed=charFed(gamChar)
  If fedChampWorld(fed)=0 Or charFed(fedChampWorld(fed))<>fed Then AddEvent(0,204) ;need new world champ
  If fedChampInter(fed)=0 Or charFed(fedChampInter(fed))<>fed Then AddEvent(0,205) ;need new inter champ
  If fedChampTag(fed,1)=0 Or fedChampTag(fed,2)=0 Or charFed(fedChampTag(fed,1))<>fed Or charFed(fedChampTag(fed,2))<>fed Then AddEvent(0,206) ;need new tag champs
 EndIf
 ;business advice (250-270)
 If fed=charFed(gamChar)
  If AllegianceRatio(fed,0)=>AllegianceRatio(fed,1)+5 Then AddEvent(0,250) ;too many faces
  If AllegianceRatio(fed,1)=>AllegianceRatio(fed,0)+5 Then AddEvent(0,251) ;too many heels
  If fedSize(fed)=<12 Then AddEvent(0,252) ;roster too small! 
 EndIf 
 ;production issues (270-290)
 If fed=charFed(gamChar)
  If gamBuild=0 And fedBank(fed)>0 And CountProductions(fed)<12 Then AddEvent(0,270) ;production being wasted
  If gamBuild>0 And fedBank(fed)<prodCost(gamBuild) Then AddEvent(0,271) ;can't afford production
  randy=Rnd(0,2)
  If randy=0 And gamBuild>0 Then AddEvent(0,272) ;delayed production
  If randy>0 And gamBuild>0 Then AddEvent(0,273) ;successful production
  For count=1 To 12
   randy=Rnd(0,50-fedProduction(fed,count))
   If randy=<0 And fedProduction(fed,count)>4 Then AddEvent(0,273+count) ;production expires (274-285)
  Next
  If fedBank(fed)>0
   If fedPopularity(fed)=>PromotionPotential(fed)-5 And fedPopularity(fed)<PromotionPotential(fed) Then AddEvent(0,253) ;approaching potential limit
   If fedPopularity(fed)=>PromotionPotential(fed) And PromotionPotential(fed)<100 Then AddEvent(0,254) ;reached potential limit
  EndIf
 EndIf
 ;status fluctuations (210-230)
 If fed=<6
  If fedRanked(fed)<fedOldRanked(fed) Then AddEvent(0,210) ;acknowledge ranking change
  If showRanked(fed)>1 And showOldRanked(fed)=1 Then AddEvent(0,219) ;lost chart spot
  If showRanked(fed)=1 And showOldRanked(fed)>1 Then AddEvent(0,220) ;gained chart spot
  randy=Rnd(0,20)
  If randy=0 And fedChampWorld(fed)>0 And charPopularity(fedChampWorld(fed))<fedPopularity(fed) Then AddEvent(0,211) ;champ taints popularity
  If randy=1 And fedChampWorld(fed)>0 And charPopularity(fedChampWorld(fed))>fedPopularity(fed) Then AddEvent(0,212) ;champ boosts popularity
  randy=Rnd(0,100)
  If randy=1 And fedPopularity(fed)<90 And fedPopularity(fed)<PromotionPotential(fed) Then AddEvent(0,213) ;major popularity boost
  If randy=2 And fedPopularity(fed)>60 Then AddEvent(0,214) ;major popularity decrease
  If randy=3 And fed<>charFed(gamChar) And fedReputation(fed)<90 Then AddEvent(0,215) ;major reptation boost
  If randy=4 And fed<>charFed(gamChar) And fedReputation(fed)>60 Then AddEvent(0,216) ;major reputation decrease
 EndIf
 If fed=charFed(gamChar)
  randy=Rnd(0,100)
  If randy=0 Then AddEvent(gamChar,217) ;whole industry improves
  If randy=1 Then AddEvent(gamChar,218) ;whole industry suffers
 EndIf
 ;court cases
 If gamDate=<47 And CourtDate()=0
  randy=Rnd(0,fedReputation(fed))
  If randy=<1 And fed=charFed(gamChar) Then AddEvent(fedBooker(9),138) ;copycat incidents
  randy=Rnd(0,250)
  If randy=<1 And fed=<6 And fed<>charFed(gamChar)
   If CountProductions(charFed(gamChar))>0 Then AddEvent(fedBooker(fed),139) ;production plaguerism
  EndIf
 EndIf
 ;schedule issues (230-250)
 If fed=charFed(gamChar)
  randy=Rnd(0,100)
  If randy=0 And gamSchedule(gamDate)>0 And gamSchedule(gamDate)<>3 And gamPromo(gamDate)=0 Then AddEvent(0,230) ;last minute cancellation
  If randy=1 And gamDate=<47 And gamSchedule(gamDate+1)>0 And gamSchedule(gamDate+1)<>3 And gamPromo(gamDate+1)=0 And gamFatality=0 Then AddEvent(0,231) ;next week cancellation
  If randy=2 And gamDate=<44 And gamSchedule(gamDate+4)>0 And gamSchedule(gamDate+4)<>3 Then AddEvent(0,232) ;planned rest
  
  If gamSchedule(gamDate)=>2 Then AddEvent(0,238) ;big night tonight
  If gamSchedule(gamDate+1)=>2Then AddEvent(0,239) ;big night next
  If gamDate=1 Then AddEvent(gamChar,240) ;new year
 EndIf
 ;150-180: MISSION REMINDERS
 If fed=charFed(gamChar) And gamMission>0 Then AddEvent(gamChar,150+gamMission) 
 ;verify changes
 CheckFedLimits(fed)
 If fedNewPopularity(fed)<>fedPopularity(fed) Then fedOldPopularity(fed)=fedPopularity(fed)
 If fedNewReputation(fed)<>fedReputation(fed) Then fedOldReputation(fed)=fedReputation(fed)
 For char=1 To no_chars
  CheckLimits(char)
 Next
Next
 ;nothing to report!
 If no_events=0 Then fed=0 : AddEvent(0,0) 
End Function

;--------------------------------------------------------------------
;//////////////////// ADD AN EVENT TO THE LIST //////////////////////
;--------------------------------------------------------------------
Function AddEvent(char,event)
 If no_events<200 ;And (charFed(char)=<8 Or char=0)
  ;increment list
  no_events=no_events+1
  eChar(no_events)=char
  eFed(no_events)=fed
  eEvent(no_events)=event
  If char>0 Then charEvent(char)=event
  eFoc(no_events)=10
  ;newspaper make-up
  eNewspaper(no_events)=Rnd(1,1)
  If char=0 Or char=fedBooker(charFed(char)) Or charRole(char)>1
   eBackground(no_events)=Rnd(1,3)
  Else
   eBackground(no_events)=Rnd(4,12)
  EndIf
  eAdvert(no_events,1)=Rnd(1,9)
  Repeat
   eAdvert(no_events,2)=Rnd(1,9)
  Until eAdvert(no_events,2)<>eAdvert(no_events,1)
  ;HEALTH ISSUES
  If event=1 Or event=5 Or event=91 Then ApplyInjury(char,Rnd(0,5),1) ;minor injury
  If event=2 Or event=6 Then ApplyInjury(char,Rnd(0,5),2) ;major injury 
  If event=3 Or event=7 Then ApplyInjury(char,99,2) ;paralysis!
  ;death
  If event=4 Or (event=>8 And event=<12)
   If event=10
    its=0
    Repeat
     newbie=Rnd(1,no_chars) : its=its+1
     If its>1000 Then charRelationship(char,newbie)=-1
    Until newbie<>char And charRelationship(char,newbie)<0 And charFed(newbie)=<8
    eVariable(no_events)=newbie
    If charVacant(newbie)<4 Then charVacant(newbie)=4
   EndIf
   gamFatality=char 
   MoveChar(char,9)
   charNewHealth(char)=0
  EndIf
  ;CONTRACT ISSUES
  ;renew contract
  If event=22 
   GenerateContract(char)
   charNewAttitude(char)=charNewAttitude(char)+Rnd(-1,1)
   charNewHappiness(char)=charNewHappiness(char)+1;PursueValue(charHappiness(char),100,0)
   randy=Rnd(0,1)
   If randy=0
    ResetEvent(no_events)
    no_events=no_events-1
   EndIf
  EndIf
  ;move elsewhere
  If event=23 
   Repeat
    newbie=Rnd(1,6) : satisfied=1
    randy=Rnd(0,2)
    If (randy>0 And fedSize(newbie)>32) Or fedSize(newbie)=>optRosterLim Then satisfied=0
    If newbie=fed Or newbie=charFed(gamChar) Then satisfied=0
   Until satisfied=1
   MoveChar(char,newbie)
   GenerateContract(char)
   charNewAttitude(char)=charNewAttitude(char)+Rnd(-1,1)
   If charNewHappiness(char)<50 Then charNewHappiness(char)=50
   charNewHappiness(char)=charNewHappiness(char)+PursueValue(charHappiness(char),100,0)
  EndIf
  ;released
  If event=24
   MoveChar(char,7)
   GenerateContract(char)
   charNewPopularity(char)=charNewPopularity(char)+PursueValue(charPopularity(char),30,0)
   charNewAttitude(char)=charNewAttitude(char)+PursueValue(charAttitude(char),30,0)
   charNewHappiness(char)=charNewHappiness(char)+PursueValue(charHappiness(char),30,0) 
  EndIf
  ;retired
  If event=25
   MoveChar(char,8)
   GenerateContract(char)
   charNewPopularity(char)=charNewPopularity(char)+1 
   charNewAttitude(char)=charNewAttitude(char)+Rnd(-1,1)
   charNewHappiness(char)=charNewHappiness(char)+PursueValue(charHappiness(char),100,0) 
  EndIf
  ;bought
  If event=26 
   its=0
   Repeat
    satisfied=1 : its=its+1
    char=Rnd(1,no_chars)
    If char=gamChar Or charRole(char)=3 Or ImportantChar(char) Then satisfied=0
    If charFed(char)=fed Or charFed(char)=charFed(gamChar) Or charFed(char)=9 Then satisfied=0
    If charFed(char)=<6 And fedSize(charFed(char))=<20 Then satisfied=0
    If charEvent(char)>0 Then satisfied=0
   Until satisfied=1 Or its>1000
   eVariable(no_events)=charFed(char)
   MoveChar(char,fed)
   GenerateContract(char)
   charSalary(char)=charSalary(char)*2
   ResetNewValues(char)
   charNewPopularity(char)=charNewPopularity(char)+1 
   charNewAttitude(char)=charNewAttitude(char)+Rnd(-1,1)
   If charNewHappiness(char)<50 Then charNewHappiness(char)=50
   charNewHappiness(char)=charNewHappiness(char)+PursueValue(charHappiness(char),100,0) 
   CheckNewValues(char)
   eChar(no_events)=char
  EndIf
  ;happy about move
  If event=28
   eVariable(no_events)=charTradeReaction(char)
   charNewAttitude(char)=charNewAttitude(char)+1
   charNewHappiness(char)=charNewHappiness(char)+1
  EndIf
  ;unhappy about move
  If event=29
   eVariable(no_events)=MakePositive#(charTradeReaction(char))
   charNewAttitude(char)=charNewAttitude(char)-1
   charNewHappiness(char)=charNewHappiness(char)-1
  EndIf
  ;arrived to work
  If event=30 
   charNewAttitude(char)=charNewAttitude(char)+1
   charNewHappiness(char)=charNewHappiness(char)+1
   charArrived(char)=1
  EndIf
  ;generate new character
  If event=31
   gamNewcomer=0
   Repeat
    randy=Rnd(1,5)
    If randy=<3 And gamFatality=0 Then gamNewcomer=fedRoster(9,Rnd(1,fedSize(9)))
    If randy=4 Then gamNewcomer=fedRoster(8,Rnd(1,fedSize(8)))
    If randy=5 Then gamNewcomer=fedRoster(7,Rnd(1,fedSize(7)))
   Until gamNewcomer>0 And gamNewcomer<>fedBooker(charFed(gamNewcomer)) And gamNewcomer<>gamChar And gamNewcomer<>charPartner(gamChar)
   eVariable(no_events)=charFed(gamNewcomer)
   eChar(no_events)=gamNewcomer : charEvent(gamNewcomer)=event
   GenerateCharacter(gamNewcomer)
   MoveChar(gamNewcomer,fed)
   GenerateContract(gamNewcomer) 
   charPhoto(gamNewcomer)=CopyImage(charPhoto(0))
   SaveImage(charPhoto(gamNewcomer),filer$+"Portraits/Photo"+Dig$(gamNewcomer,100)+".bmp")
   charPhotoR(gamNewcomer)=25 : charPhotoG(gamNewcomer)=5 : charPhotoB(gamNewcomer)=5
   MaskImage charPhoto(gamNewcomer),charPhotoR(gamNewcomer),charPhotoG(gamNewcomer),charPhotoB(gamNewcomer)
  EndIf
  ;STATUS ISSUES
  ;stripped of title
  If event=>40 And event=<42
   If fedChampWorld(fed)=char Then fedChampWorld(fed)=0
   If fedChampInter(fed)=char Then fedChampInter(fed)=0
   If fedChampTag(fed,1)=char Or fedChampTag(fed,2)=char Then fedChampTag(fed,1)=0 : fedChampTag(fed,2)=0
   charNewPopularity(char)=charNewPopularity(char)-1
   charNewHappiness(char)=charNewHappiness(char)+PursueValue(charHappiness(char),30,0)
  EndIf
  ;title winner
  If event=>43 And event=<45
   charNewPopularity(char)=charNewPopularity(char)+1
   charNewHappiness(char)=charNewHappiness(char)+1
   charTitles(char,fed,event-42)=charTitles(char,fed,event-42)+1
   If event=45 Then charTitles(charPartner(char),fed,3)=charTitles(charPartner(char),fed,3)+1
   WriteHistory(fed,event-42)
  EndIf
  ;replaced in tournament
  If event=49
   its=0
   Repeat
    newbie=fedRoster(charFed(gamChar),Rnd(1,fedSize(charFed(gamChar))))
    its=its+1
   Until TournamentStatus(newbie)=0 Or (its>100 And newbie<>char)
   For b=1 To 32
    If cupResult(cupSlot,b)=0
     If cupBracket(cupSlot,b,1)=char Then cupBracket(cupSlot,b,1)=newbie
     If cupBracket(cupSlot,b,2)=char Then cupBracket(cupSlot,b,2)=newbie
    EndIf
    If cupCharPartner(cupSlot,cupBracket(cupSlot,b,1))=char Then cupCharPartner(cupSlot,cupBracket(cupSlot,b,1))=newbie
    If cupCharPartner(cupSlot,cupBracket(cupSlot,b,2))=char Then cupCharPartner(cupSlot,cupBracket(cupSlot,b,2))=newbie
   Next
   eVariable(no_events)=newbie
  EndIf
  ;failed title promise
  If event=50
   charNewAttitude(char)=charNewAttitude(char)+PursueValue(charAttitude(char),30,0)
   charNewHappiness(char)=charNewHappiness(char)+PursueValue(charHappiness(char),30,0)
   ChangeRealRelationship(char,gamChar,-1)
  EndIf
  ;MISC EVENTS
  ;injury gets worse
  If event=60
   charInjured(char,eVariable(no_events))=charInjured(char,eVariable(no_events))*2
   charNewStrength(char)=charNewStrength(char)-Rnd(0,1)
   charNewSkill(char)=charNewSkill(char)-Rnd(0,1)
   charNewAgility(char)=charNewAgility(char)-Rnd(0,1)
   charNewStamina(char)=charNewStamina(char)-Rnd(0,1)
   charNewToughness(char)=charNewToughness(char)-Rnd(0,1)
   charNewHappiness(char)=charNewHappiness(char)-1
  EndIf
  ;injury gets better
  If event=61
   charInjured(char,eVariable(no_events))=charInjured(char,eVariable(no_events))/2
   charNewHappiness(char)=charNewHappiness(char)+1
  EndIf
  ;feeling ill/good
  If event=62 Then charNewHealth(char)=0 : charNewHappiness(char)=charNewHappiness(char)-1
  If event=63 Then charNewHealth(char)=100 : charNewHappiness(char)=charNewHappiness(char)+1
  ;gain weight
  If event=64
   charWeight(char)=charWeight(char)+25
   charNewStrength(char)=charNewStrength(char)+1
   charNewSkill(char)=charNewSkill(char)-1
   charNewAgility(char)=charNewAgility(char)-1
   charNewStamina(char)=charNewStamina(char)-1
   charNewToughness(char)=charNewToughness(char)+1 
   charNewHappiness(char)=charNewHappiness(char)-1
  EndIf
  ;lose weight
  If event=65
   charWeight(char)=charWeight(char)-25
   charNewStrength(char)=charNewStrength(char)-1
   charNewSkill(char)=charNewSkill(char)+1
   charNewAgility(char)=charNewAgility(char)+1
   charNewStamina(char)=charNewStamina(char)+1
   charNewToughness(char)=charNewToughness(char)-1
   charNewHappiness(char)=charNewHappiness(char)+1 
  EndIf
  ;grow taller
  If event=66
   charHeight(char)=charHeight(char)+1
   charNewStrength(char)=charNewStrength(char)+1
   charNewSkill(char)=charNewSkill(char)-1
   charNewAgility(char)=charNewAgility(char)-1
   charNewStamina(char)=charNewStamina(char)-1
   charNewToughness(char)=charNewToughness(char)+1
   charNewHappiness(char)=charNewHappiness(char)+1 
  EndIf
  ;reached peak
  If event=67
   charPeaked(char)=charAge(char)
   charNewHappiness(char)=charNewHappiness(char)-1 
  EndIf
  ;spontaneous stat improvements
  If event=>68 And event=<74 Then charNewHappiness(char)=charNewHappiness(char)+1
  If event=68 Then charNewPopularity(char)=charNewPopularity(char)+PursueValue(charPopularity(char),100,0)
  If event=69 Then charNewStrength(char)=charNewStrength(char)+PursueValue(charStrength(char),100,0) : charWeightChange(char)=charWeightChange(char)+Rnd(1,3)
  If event=70 Then charNewSkill(char)=charNewSkill(char)+PursueValue(charSkill(char),100,0) : charWeightChange(char)=charWeightChange(char)+1
  If event=71 Then charNewAgility(char)=charNewAgility(char)+PursueValue(charAgility(char),100,0) : charWeightChange(char)=charWeightChange(char)-Rnd(1,3)
  If event=72 Then charNewStamina(char)=charNewStamina(char)+PursueValue(charStamina(char),100,0) : charWeightChange(char)=charWeightChange(char)-1
  If event=73 Then charNewToughness(char)=charNewToughness(char)+PursueValue(charToughness(char),100,0) : charWeightChange(char)=charWeightChange(char)+1
  If event=74 Then charNewAttitude(char)=charNewAttitude(char)+PursueValue(charAttitude(char),100,0)
  If event=75 Then charNewHappiness(char)=charNewHappiness(char)+PursueValue(charHappiness(char),100,0)
  ;spontaneous stat deterioration
  If event=>76 And event=<82 Then charNewHappiness(char)=charNewHappiness(char)-1
  If event=76 Then charNewPopularity(char)=charNewPopularity(char)+PursueValue(charPopularity(char),30,0)
  If event=77 Then charNewStrength(char)=charNewStrength(char)+PursueValue(charStrength(char),30,0) : charWeightChange(char)=charWeightChange(char)-Rnd(1,3)
  If event=78 Then charNewSkill(char)=charNewSkill(char)+PursueValue(charSkill(char),30,0) : charWeightChange(char)=charWeightChange(char)-1
  If event=79 Then charNewAgility(char)=charNewAgility(char)+PursueValue(charAgility(char),30,0) : charWeightChange(char)=charWeightChange(char)+Rnd(1,3)
  If event=80 Then charNewStamina(char)=charNewStamina(char)+PursueValue(charStamina(char),30,0) : charWeightChange(char)=charWeightChange(char)+1
  If event=81 Then charNewToughness(char)=charNewToughness(char)+PursueValue(charToughness(char),30,0) : charWeightChange(char)=charWeightChange(char)-1
  If event=82 Then charNewAttitude(char)=charNewAttitude(char)+PursueValue(charAttitude(char),30,0)
  If event=83 Then charNewHappiness(char)=charNewHappiness(char)+PursueValue(charHappiness(char),30,0)
  ;bad reaction to drugs
  If event=86
   charNewHealth(char)=0 : charInjured(char,0)=1
   charNewAttitude(char)=charNewAttitude(char)+PursueValue(charAttitude(char),30,0)
   charNewHappiness(char)=charNewHappiness(char)+PursueValue(charHappiness(char),30,0)
  EndIf
  ;exposed as drug user
  If event=87
   charNewPopularity(char)=charNewPopularity(char)+PursueValue(charPopularity(char),30,0)
   charNewAttitude(char)=charNewAttitude(char)+PursueValue(charAttitude(char),30,0)
   charNewHappiness(char)=charNewHappiness(char)+PursueValue(charHappiness(char),30,0)
  EndIf
  ;lack of exposure
  If event=88
   charNewPopularity(char)=charNewPopularity(char)-1
   charNewHappiness(char)=charNewHappiness(char)-1
  EndIf
  ;storm out of training
  If event=90
   charNewAttitude(char)=charNewAttitude(char)+PursueValue(charAttitude(char),30,0)
   charNewHappiness(char)=charNewHappiness(char)+PursueValue(charHappiness(char),30,0)
   charVacant(char)=1 : charTrainCourse(char)=0
  EndIf
  ;fail to turn up
  If event=92
   charNewAttitude(char)=charNewAttitude(char)+PursueValue(charAttitude(char),30,0)
   charNewHappiness(char)=charNewHappiness(char)-1
   charVacant(char)=1 : charTrainCourse(char)=0
  EndIf
  ;turn up drunk
  If event=93
   charNewAttitude(char)=charNewAttitude(char)+PursueValue(charAttitude(char),30,0)
   charNewHappiness(char)=charNewHappiness(char)-1 
   charInjured(char,0)=1 : charTrainCourse(char)=0
   charNewHealth(char)=0
  EndIf
  ;arrested
  If event=94
   charNewPopularity(char)=charNewPopularity(char)-1
   charNewAttitude(char)=charNewAttitude(char)+PursueValue(charAttitude(char),30,0)
   charNewHappiness(char)=charNewHappiness(char)+PursueValue(charHappiness(char),30,0)
   charVacant(char)=Rnd(2,6) : charTrainCourse(char)=0
  EndIf
  ;upset at not being used
  If event=95
   charNewAttitude(char)=charNewAttitude(char)-1
   charNewHappiness(char)=charNewHappiness(char)-1
  EndIf
  ;RELATIONSHIP ISSUES
  ;relationship boosts profile
  If event=103 Or event=108
   charNewPopularity(char)=charNewPopularity(char)+1
   charNewAttitude(char)=charNewAttitude(char)+1
   charNewHappiness(char)=charNewHappiness(char)+1
  EndIf
  ;relationship damages profile
  If event=104 Or event=109
   charNewPopularity(char)=charNewPopularity(char)-1
   charNewAttitude(char)=charNewAttitude(char)-1
   charNewHappiness(char)=charNewHappiness(char)-1
  EndIf
  ;internal friendship
  If event=110
   its=0
   Repeat 
    newbie=fedRoster(fed,Rnd(1,fedSize(fed))) : its=its+1
   Until (newbie<>char And newbie<>gamChar And charRealRelationship(char,newbie)=<0) Or its>100
   eVariable(no_events)=newbie
   ChangeRealRelationship(char,newbie,1)
   charNewAttitude(char)=charNewAttitude(char)+1
   charNewHappiness(char)=charNewHappiness(char)+1
  EndIf
  ;internal rivalry
  If event=111
   its=0
   Repeat 
    newbie=fedRoster(fed,Rnd(1,fedSize(fed))) : its=its+1
   Until (newbie<>char And newbie<>gamChar And charRealRelationship(char,newbie)=>0) Or its>100
   eVariable(no_events)=newbie
   ChangeRealRelationship(char,newbie,-1)
   charNewAttitude(char)=charNewAttitude(char)-1
   charNewHappiness(char)=charNewHappiness(char)-1
  EndIf
  ;external friendship
  If event=112
   Repeat 
    newbie=Rnd(1,no_chars)
   Until newbie<>char And newbie<>gamChar And charFed(newbie)<>fed And charFed(newbie)=<8
   eVariable(no_events)=newbie
   ChangeRealRelationship(char,newbie,1)
   charNewAttitude(char)=charNewAttitude(char)+1
   charNewHappiness(char)=charNewHappiness(char)+1
  EndIf
  ;external rivalry
  If event=113
   Repeat 
    newbie=Rnd(1,no_chars)
   Until newbie<>char And newbie<>gamChar And charFed(newbie)<>fed And charFed(newbie)=<8
   eVariable(no_events)=newbie
   ChangeRealRelationship(char,newbie,-1)
   charNewAttitude(char)=charNewAttitude(char)-1
   charNewHappiness(char)=charNewHappiness(char)-1
  EndIf
  ;settle differences
  If event=114
   its=0
   Repeat
    its=its+1 : satisfied=1
    randy=Rnd(0,2)
    If randy=<1 Then newbie=fedRoster(fed,Rnd(1,fedSize(fed)))
    If randy=2 Then newbie=Rnd(1,no_chars)
    If charRealRelationship(char,newbie)=>0 Then satisfied=0
    If newbie=char Or newbie=gamChar Or charFed(char)=9 Then satisfied=0
   Until satisfied=1 Or its>1000
   eVariable(no_events)=newbie
   ChangeRealRelationship(char,newbie,0)
   charNewAttitude(char)=charNewAttitude(char)+1
   charNewHappiness(char)=charNewHappiness(char)+1
  EndIf
  ;not accepted as face
  If event=116
   charHeel(char)=1
   charNewPopularity(char)=charNewPopularity(char)-1
   charNewAttitude(char)=charNewAttitude(char)-1
   charNewHappiness(char)=charNewHappiness(char)-1
  EndIf 
  ;not accepted as heel
  If event=117
   charHeel(char)=0
   charNewPopularity(char)=charNewPopularity(char)+1
   charNewAttitude(char)=charNewAttitude(char)+1
   charNewHappiness(char)=charNewHappiness(char)+1
  EndIf
  ;brawl with enemy
  If event=118
   v=eVariable(no_events)
   charInjured(char,0)=1 : charNewHealth(char)=0
   charNewAttitude(char)=charNewAttitude(char)-1
   charNewHappiness(char)=charNewHappiness(char)+PursueValue(charNewHappiness(char),30,0)
   charInjured(v,0)=1 : charHealth(v)=0 : charNewHealth(v)=0
   charAttitude(v)=charAttitude(v)-1
   charHappiness(v)=charHappiness(v)+PursueValue(charHappiness(v),30,0)
  EndIf
  ;inspired by friend
  If event=119
   v=eVariable(no_events)
   charNewAttitude(char)=charNewAttitude(char)+1
   charNewHappiness(char)=charNewHappiness(char)+1
   charAttitude(v)=charAttitude(v)+1
   charHappiness(v)=charHappiness(v)+1
  EndIf
  ;court cases
  If event=>131 And event=<149
   date=NextDate()
   If date=0 Then date=gamDate+1
   gamSchedule(date)=-2
   gamCourtChar(date)=char
   gamCourtCase(date)=event-130
   ChangeRealRelationship(char,gamChar,-1)
   charNewAttitude(char)=charNewAttitude(char)+PursueValue(charAttitude(char),30,0)
   charNewHappiness(char)=charNewHappiness(char)+PursueValue(charHappiness(char),30,0)
   If charVacant(char)<3 Then charVacant(char)=3 
  EndIf
  ;PROMOTION ISSUES
  ;new booker
  If event=200 
   level=60 : its=0
   Repeat
    its=its+1 : satisfied=1
    newbie=fedRoster(fed,Rnd(1,fedSize(fed)))
    randy=Rnd(0,2)
    If randy>0 And charAge(newbie)<level Then satisfied=0 
    If newbie=fedBooker(fed) Or TitleHolder(newbie,0)>0 Then satisfied=0
    If newbie=gamChar Or newbie=charPartner(gamChar) Or newbie=charManager(gamChar) Then satisfied=0
    If its>100 And level>10 Then level=level-10 : its=0 
   Until satisfied=1 Or its>1000
   char=newbie : eChar(no_events)=newbie
   fedBooker(fed)=char : WriteHistory(fed,0)
   charNewPopularity(char)=charNewPopularity(char)+PursueValue(charNewPopularity(char),100,0)
   charNewAttitude(char)=charNewAttitude(char)+Rnd(-1,1)
   charNewHappiness(char)=charNewHappiness(char)+PursueValue(charNewHappiness(char),100,0)
   If fed=charFed(gamChar) Then gamMission=0
  EndIf
  ;new world champ
  If event=201 
   level=90 : its=0
   Repeat
    its=its+1 : satisfied=1
    newbie=fedRoster(fed,Rnd(1,fedSize(fed)))
    If charPopularity(newbie)<level Then satisfied=0 
    If TitleHolder(newbie,0)>0 Or InjuryStatus(newbie)>0 Or charRole(newbie)<>1 Then satisfied=0
    If newbie=gamChar Or newbie=charPartner(gamChar) Or newbie=charManager(gamChar) Then satisfied=0
    If its>100 And level>50 Then level=level-10 : its=0 
   Until satisfied=1 Or its>1000
   char=newbie : eChar(no_events)=newbie
   fedChampWorld(fed)=char : charTitles(char,fed,1)=charTitles(char,fed,1)+1 : WriteHistory(fed,1)
   charNewPopularity(char)=charNewPopularity(char)+1
   charNewHappiness(char)=charNewHappiness(char)+1 
  EndIf
  ;new inter champ
  If event=202 
   its=0
   Repeat
    its=its+1 : satisfied=1
    newbie=fedRoster(fed,Rnd(1,fedSize(fed)))
    If TitleHolder(newbie,0)>0 Or InjuryStatus(newbie)>0 Or charRole(newbie)<>1 Then satisfied=0
    If newbie=gamChar Or newbie=charPartner(gamChar) Or newbie=charManager(gamChar) Then satisfied=0
   Until satisfied=1 Or its>1000
   char=newbie : eChar(no_events)=newbie
   fedChampInter(fed)=char : charTitles(char,fed,2)=charTitles(char,fed,2)+1 : WriteHistory(fed,2)
   charNewPopularity(char)=charNewPopularity(char)+1
   charNewHappiness(char)=charNewHappiness(char)+1 
  EndIf
  ;new tag champs
  If event=203 
   its=0
   Repeat
    its=its+1 : satisfied=1
    newbie=fedRoster(fed,Rnd(1,fedSize(fed)))
    randy=Rnd(0,1)
    If randy>0 And charPartner(newbie)=0 Then satisfied=0
    If TitleHolder(newbie,0)>0 Or InjuryStatus(newbie)>0 Or charRole(newbie)<>1 Then satisfied=0
    If newbie=gamChar Or newbie=charPartner(gamChar) Or newbie=charManager(gamChar) Then satisfied=0
   Until satisfied=1 Or its>1000
   char=newbie : eChar(no_events)=newbie
   fedChampTag(fed,1)=char : fedChampTag(fed,2)=AssignPartner(char,-1) : WriteHistory(fed,3)
   FormTeam(fedChampTag(fed,1),fedChampTag(fed,2)) 
   charTitles(char,fed,3)=charTitles(char,fed,3)+1
   charTitles(charPartner(char),fed,3)=charTitles(charPartner(char),fed,3)+1
   charNewPopularity(char)=charNewPopularity(char)+1
   charNewHappiness(char)=charNewHappiness(char)+1 
  EndIf
  ;become leading wrestling show
  ;If event=210 And fedRanked(fed)=1
   ;fedNewPopularity(fed)=fedNewPopularity(fed)+1
   ;fedNewReputation(fed)=fedNewReputation(fed)+1 
   ;eFoc(no_events)=1
  ;EndIf
  ;champ affects popularity
  If event=211 Then fedNewPopularity(fed)=fedNewPopularity(fed)-1 : eFoc(no_events)=1 
  If event=212 Then fedNewPopularity(fed)=fedNewPopularity(fed)+1 : eFoc(no_events)=1 
  ;whole fed gets more popular
  If event=213
   For count=1 To fedSize(fed)
    charPopularity(fedRoster(fed,count))=charPopularity(fedRoster(fed,count))+1
    charHappiness(fedRoster(fed,count))=charHappiness(fedRoster(fed,count))+1
   Next
   fedNewPopularity(fed)=fedNewPopularity(fed)+PursueValue(fedPopularity(fed),100,0)
   eFoc(no_events)=1
  EndIf
  ;whole fed gets less popular
  If event=214 
   For count=1 To fedSize(fed)
    charPopularity(fedRoster(fed,count))=charPopularity(fedRoster(fed,count))-1
    charHappiness(fedRoster(fed,count))=charHappiness(fedRoster(fed,count))-1
   Next
   fedNewPopularity(fed)=fedNewPopularity(fed)+PursueValue(fedPopularity(fed),50,0)
   eFoc(no_events)=1
  EndIf
  ;reputation changes
  If event=215 Then fedNewReputation(fed)=fedNewReputation(fed)+PursueValue(fedReputation(fed),100,0) : eFoc(no_events)=1 ;reputation increase
  If event=216 Then fedNewReputation(fed)=fedNewReputation(fed)+PursueValue(fedReputation(fed),30,0) : eFoc(no_events)=1 ;reputation decrease
  ;whole industry gets more popular
  If event=217
   charNewPopularity(char)=charNewPopularity(char)+1
   charNewHappiness(char)=charNewHappiness(char)+1
   For v=1 To no_chars
    If charFed(v)=<7 And v<>char
     charPopularity(v)=charPopularity(v)+1
     charHappiness(v)=charHappiness(v)+1
    EndIf
   Next  
   For count=1 To 6
    fedPopularity(count)=fedPopularity(count)+1
   Next
  EndIf
  ;whole industry gets less popular
  If event=218 
   charNewPopularity(char)=charNewPopularity(char)-1
   charNewHappiness(char)=charNewHappiness(char)-1
   For v=1 To no_chars
    If charFed(v)=<7 And v<>char
     charPopularity(v)=charPopularity(v)-1
     charHappiness(v)=charHappiness(v)-1
    EndIf
   Next  
   For count=1 To 6
    fedPopularity(count)=fedPopularity(count)-1
   Next
  EndIf
  ;schedule issues
  If event=230 Then gamSchedule(gamDate)=0 ;last minute cancellation
  If event=231 Then gamSchedule(gamDate+1)=0 ;new week cancellation
  If event=232 Then gamSchedule(gamDate+4)=0 ;planned break
  ;production issues
  If event=271 Then eVariable(no_events)=gamBuild : gamBuild=0 ;can't afford
  If event=273 
   fedProduction(fed,gamBuild)=1
   eVariable(no_events)=gamBuild
   gamBuild=0
  EndIf
  If event=>274 And event=<285 
   fedProduction(fed,event-273)=0
  EndIf
  ;PREPARE TO INDICATE CHANGES
  If char>0
   CheckNewValues(char)
   If charNewHappiness(char)<>charHappiness(char) Then charOldHappiness(char)=charHappiness(char) : eFoc(no_events)=1 
   If charNewAttitude(char)<>charAttitude(char) Then charOldAttitude(char)=charAttitude(char) : eFoc(no_events)=1
   If charNewToughness(char)<>charToughness(char) Then charOldToughness(char)=charToughness(char) : eFoc(no_events)=1
   If charNewStamina(char)<>charStamina(char) Then charOldStamina(char)=charStamina(char) : eFoc(no_events)=1
   If charNewAgility(char)<>charAgility(char) Then charOldAgility(char)=charAgility(char) : eFoc(no_events)=1
   If charNewSkill(char)<>charSkill(char) Then charOldSkill(char)=charSkill(char) : eFoc(no_events)=1
   If charNewStrength(char)<>charStrength(char) Then charOldStrength(char)=charStrength(char) : eFoc(no_events)=1
   If charNewPopularity(char)<>charPopularity(char) Then charOldPopularity(char)=charPopularity(char) : eFoc(no_events)=1
   If charNewHealth(char)<>charHealth(char) Then eFoc(no_events)=1
  EndIf
 EndIf
End Function

;RESET EVENT
Function ResetEvent(event)
 eEvent(event)=0 : eChar(event)=0 : eFoc(event)=10
 eFed(event)=0 : eVariable(event)=0 : eCharged(event)=0
End Function

;----------------------------------------------------------------------
;/////////////////////// 23. NEWS PROCESS /////////////////////////////
;----------------------------------------------------------------------
Function NewsReports()
;prepare events
FindEvents()
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
foc=eFoc(1) : cyc=1
go=0 : gotim=-10 : keytim=0
While go=0
 
 Cls
 frames=WaitTimer(timer)
 For framer=1 To frames
	
	;counters
	keytim=keytim-1
	If keytim<1 Then keytim=0 
	
	;PROCESS
    gotim=gotim+1
	If gotim>30 
	 ;alter states
	 If eChar(cyc)=0 Then AlterFedStats(eFed(cyc)) 
	 If eChar(cyc)>0 Then AlterStats(eChar(cyc))
	 ;browse events
	 If KeyDown(1) Or KeyDown(28) Or ButtonPressed() Or MouseDown(1)
	  If keytim=0
	   If foc=10 
	    PlaySound sMenuBrowse : gotim=-10
	    cyc=cyc+1 : foc=eFoc(cyc) : keytim=10
	    If eChar(cyc)>0 And charUpdated(eChar(cyc))>0 Then foc=10
	   EndIf
	   If cyc>no_events Then cyc=no_events : foc=10 : go=1
	  Else
	   If foc<10 And keytim>1 Then keytim=1
	  EndIf
	 EndIf 
	EndIf 
	;paper impact
	If gotim=0 Then ProduceSound(0,sPaper,0,0.25)
	;music
	ManageMusic(-1) 
	
 UpdateWorld
 Next 
 RenderWorld 1

 ;DISPLAY
 DrawImage gBackground,rX#(400),rY#(300)
 If eFed(cyc)>0
  DrawImage gFed(eFed(cyc)),rX#(400),rY#(60)  
 Else
  DrawImage gLogo(2),rX#(400),rY#(60)  
 EndIf
 ;show profiles
 char=eChar(cyc)
 If char>0 
  If gotim>20 Then HighlightStats(char)
  If gotim>10 Then DrawProfile(char,-1,-1,0)
 Else
  If eFed(cyc)>0
   If gotim>20 Then HighlightFedStats(eFed(cyc))
   If gotim>10 Then DrawFedProfile(eFed(cyc),-1,-1)
  EndIf
 EndIf
 ;get context
 showChar=0 : refChar=0
 If char>0 Then showChar=char Else showChar=fedBooker(eFed(cyc))
 g=charGender(char)
 ;CONSTRUCT NEWSPAPER
 If gotim>0
  ;graphics
  x=rX#(400) : y=rY#(370)
  DrawImage gNewspaper,x,y
  DrawImage gNewsIdentity(eNewspaper(cyc)),x-158,y-165
  DrawImage gNewsAdvert(eAdvert(cyc,1)),x+42,y-165
  DrawImage gNewsAdvert(eAdvert(cyc,2)),x+201,y-165
  DrawImage gNewsScene(eBackground(cyc)),x+146,y+40 
  ;smallprint
  SetFont fontNews(0) : Color 110,110,110
  Text x-268,y-107,"The Nation's #1 Newspaper",0,1
  Text x+40,y-107,DescribeDate$(gamDate,gamYear),1,1
  Text x+235,y-107,"50 Cents",0,1
  ;default message
  SetFont fontNews(3) : Color 0,0,0
  If eEvent(cyc)=0
   headline$="CALM BEFORE THE STORM"
   Text x+5,y+150,"Nothing has happened in the world of wrestling...",1,1
  EndIf
  ;health issues
  If eEvent(cyc)=1 And IdentifyInjury(char)=0
   headline$="RISKY BUSINESS"
   Text x+5,y+138,charName$(char)+" incurred a minor injury in a recent match",1,1
   Text x+5,y+166,"and will be out of action for the next "+InjuryStatus(char)+" weeks...",1,1
  EndIf
  If eEvent(cyc)=1 And IdentifyInjury(char)>0
   headline$="RISKY BUSINESS"
   Text x+5,y+138,charName$(char)+" incurred a slight "+textInjury$(IdentifyInjury(char))+" injury in a recent",1,1
   Text x+5,y+166,"match and will be suffering with it for the next "+charInjured(char,IdentifyInjury(char))+" weeks...",1,1
  EndIf
  If eEvent(cyc)=2 And IdentifyInjury(char)=0
   headline$="RISKY BUSINESS"
   Text x+5,y+125,charName$(char)+" sustained a serious injury in a recent",1,1
   Text x+5,y+150,"match and will be hospitalized for the next "+InjuryStatus(char)+" weeks.",1,1
   Text x+5,y+175,"Even then, "+Lower$(He$(g))+" may be a shadow of "+Lower$(His$(g))+" former self...",1,1
  EndIf
  If eEvent(cyc)=2 And IdentifyInjury(char)>0
   headline$="RISKY BUSINESS"
   Text x+5,y+125,charName$(char)+" sustained a serious "+textInjury$(IdentifyInjury(char))+" injury in a recent",1,1
   Text x+5,y+150,"match and will be suffering with it for the next "+charInjured(char,IdentifyInjury(char))+" weeks.",1,1
   Text x+5,y+175,"Even then, "+Lower$(He$(g))+" may be a shadow of "+Lower$(His$(g))+" former self...",1,1
  EndIf
  If eEvent(cyc)=3
   headline$="CASUALTY OF WAR"
   Text x+5,y+125,charName$(char)+" sustained a serious injury in a recent match.",1,1
   Text x+5,y+150,He$(g)+" was rushed to hospital, where it's feared "+Lower$(He$(g))+" may be paralyzed!",1,1
   Text x+5,y+175,His$(g)+" wrestling career is almost certainly over...",1,1
   ChannelPitch chTheme,PercentOf#(chThemePitch,90)
  EndIf
  If eEvent(cyc)=4
   headline$="RING OF DEATH" 
   Text x+5,y+125,charName$(char)+" sustained a serious injury in a recent match.",1,1
   Text x+5,y+150,"Despite their best efforts, medics were not able to resuscitate "+Lower$(Him$(g))+".",1,1
   Text x+5,y+175,charName$(char)+" has died as a result of "+Lower$(His$(g))+" injuries. "+He$(g)+" was "+charAge(char)+"...",1,1                
   ChannelPitch chTheme,PercentOf#(chThemePitch,90)
  EndIf
  If eEvent(cyc)=5 And IdentifyInjury(char)=0
   headline$="BROKEN BONES"
   Text x+5,y+138,charName$(char)+" incurred a minor injury in an accident",1,1
   Text x+5,y+166,"and will be out of action for the next "+InjuryStatus(char)+" weeks...",1,1
  EndIf
  If eEvent(cyc)=5 And IdentifyInjury(char)>0
   headline$="RISKY BUSINESS"
   Text x+5,y+138,charName$(char)+" incurred a slight "+textInjury$(IdentifyInjury(char))+" injury in an",1,1
   Text x+5,y+166,"accident and will be suffering with it for the next "+charInjured(char,IdentifyInjury(char))+" weeks...",1,1
  EndIf
  If eEvent(cyc)=6 And IdentifyInjury(char)=0
   headline$="BROKEN BONES"
   Text x+5,y+125,charName$(char)+" sustained a serious injury in an accident",1,1
   Text x+5,y+150,"and will be hospitalized for the next "+InjuryStatus(char)+" weeks.",1,1
   Text x+5,y+175,"Even then, "+Lower$(He$(g))+" may be a shadow of "+Lower$(His$(g))+" former self...",1,1
  EndIf
  If eEvent(cyc)=6 And IdentifyInjury(char)>0
   headline$="BROKEN BONES"
   Text x+5,y+125,charName$(char)+" sustained a serious "+textInjury$(IdentifyInjury(char))+" injury in an",1,1
   Text x+5,y+150,"accident and will be suffering with it for the next "+charInjured(char,IdentifyInjury(char))+" weeks.",1,1
   Text x+5,y+175,"Even then, "+Lower$(He$(g))+" may be a shadow of "+Lower$(His$(g))+" former self...",1,1
  EndIf
  If eEvent(cyc)=7
   headline$="WRESTLER PARALYZED"
   Text x+5,y+125,charName$(char)+" was involved in a horrific accident",1,1
   Text x+5,y+150,"this week and doctors fear "+Lower$(He$(g))+" may never walk again!",1,1
   Text x+5,y+175,His$(g)+" wrestling career is almost certainly over...",1,1
   ChannelPitch chTheme,PercentOf#(chThemePitch,90)
  EndIf
  If eEvent(cyc)=8
   headline$="WRESTLER KILLED"
   Text x+5,y+125,charName$(char)+" was involved in a horrific accident",1,1
   Text x+5,y+150,"this week and later died in hospital. The "+charAge(char)+" year",1,1
   Text x+5,y+175,"old will be greatly missed by the world of wrestling...",1,1                   
   ChannelPitch chTheme,PercentOf#(chThemePitch,90)
  EndIf
  If eEvent(cyc)=9
   headline$="OVERDOSE TRAGEDY"
   Text x+5,y+125,charName$(char)+" was found dead at "+Lower$(His$(g))+" home last night.",1,1
   Text x+5,y+150,"The hedonistic "+charAge(char)+" year old's life is thought to have",1,1
   Text x+5,y+175,"been claimed by a lethal cocktail of drink and drugs...",1,1
   ChannelPitch chTheme,PercentOf#(chThemePitch,90)
  EndIf
  If eEvent(cyc)=10
   headline$="FIGHT TO THE DEATH"
   Text x+5,y+125,charName$(char)+" was found dead at "+Lower$(His$(g))+" home last night.",1,1
   Text x+5,y+150,His$(g)+" death is thought to be linked to a dispute with",1,1
   Text x+5,y+175,charName$(eVariable(cyc))+", who is now under investigation...",1,1
   ChannelPitch chTheme,PercentOf#(chThemePitch,90)
  EndIf
  If eEvent(cyc)=11
   headline$="SUICIDE SHOCK"
   Text x+5,y+125,charName$(char)+" was found dead at "+Lower$(His$(g))+" home last night.",1,1
   Text x+5,y+150,"The troubled "+charAge(char)+" year old is thought to have committed suicide.",1,1
   Text x+5,y+175,His$(g)+" fleeting contribution to wrestling will be cherished...",1,1
   ChannelPitch chTheme,PercentOf#(chThemePitch,90)
  EndIf
  If eEvent(cyc)=12
   headline$="LEGEND PASSES AWAY"
   Text x+5,y+125,charName$(char)+" was found dead at "+Lower$(His$(g))+" home last night.",1,1
   Text x+5,y+150,"The "+charAge(char)+" year old is thought to have died of natural causes.",1,1
   Text x+5,y+175,His$(g)+" contribution to wrestling will never be forgotten...",1,1
   ChannelPitch chTheme,PercentOf#(chThemePitch,90)
  EndIf
  If eEvent(cyc)=13
   headline$="ROAD TO RECOVERY"
   Text x+5,y+138,charName$(char)+" has recovered from "+Lower$(His$(g))+" injury",1,1
   Text x+5,y+166,"and should now return to active competition...",1,1
  EndIf
  ;contract issues
  If eEvent(cyc)=20 And charFed(char)=charFed(gamChar)
   headline$="THE END IS NEAR"
   Text x+5,y+138,charName$(char)+"'s contract expires next week.",1,1
   Text x+5,y+166,"It may be time to re-evaluate "+Lower$(His$(g))+" future...",1,1
  EndIf
  If eEvent(cyc)=20 And charFed(char)<>charFed(gamChar)
   headline$="NOW OR NEVER"
   Text x+5,y+138,charName$(char)+"'s contract expires soon.",1,1
   Text x+5,y+166,"Now would be an ideal time to poach "+Him$(g)+"...",1,1
  EndIf
  If eEvent(cyc)=21
   headline$="JUDGMENT DAY"
   Text x+5,y+138,charName$(char)+" is working without a contract!",1,1
   Text x+5,y+166,He$(g)+" could disappear from the roster at any time...",1,1
  EndIf
  If eEvent(cyc)=22
   headline$="THE SAGA CONTINUES"
   Text x+5,y+138,charName$(char)+" has renewed "+Lower$(His$(g))+" contract with "+fedName$(eFed(cyc)),1,1
   Text x+5,y+166,"and will remain on the roster for the next "+charContract(char)+" weeks...",1,1
  EndIf
  If eEvent(cyc)=23
   headline$="MOVING ON"
   Text x+5,y+138,charName$(char)+" has left "+fedName$(eFed(cyc)),1,1
   Text x+5,y+166,"to pursue a career at "+fedName$(charFed(char))+"...",1,1
  EndIf
  If eEvent(cyc)=24
   headline$="BACK TO BASICS"
   Text x+5,y+138,charName$(char)+" has been released from "+fedName$(eFed(cyc)),1,1
   Text x+5,y+166,"and must make ends meet on the independent circuit...",1,1
  EndIf
  If eEvent(cyc)=25
   headline$="END OF THE ROAD"
   Text x+5,y+138,"At the age of "+charAge(char)+", "+charName$(char)+" has retired from",1,1
   Text x+5,y+166,"the wrestling business to pursue other ambitions...",1,1
  EndIf
  If eEvent(cyc)=26
   headline$="HOT PROPERTY"
   Text x+5,y+138,fedName$(eFed(cyc))+" have broken the bank to",1,1
   Text x+5,y+166,"sign "+charName$(char)+" from "+fedName$(eVariable(cyc))+"!",1,1
  EndIf
  If eEvent(cyc)=27
   headline$="BACK TO BUSINESS"
   Text x+5,y+138,charName$(char)+" has returned from "+Lower$(His$(g))+" absence",1,1
   Text x+5,y+166,"and should now return to active competition...",1,1
  EndIf
  If eEvent(cyc)=28 And charFed(eVariable(cyc))=charFed(char)
   headline$="WE MEET AGAIN"
   Text x+5,y+138,charName$(char)+" is pleased that "+charName$(eVariable(cyc)),1,1
   Text x+5,y+166,"has joined "+fedName$(charFed(char))+"!",1,1
   refChar=eVariable(cyc)
  EndIf
  If eEvent(cyc)=28 And charFed(eVariable(cyc))<>charFed(char)
   headline$="GOOD RIDDANCE"
   Text x+5,y+138,charName$(char)+" is glad that "+charName$(eVariable(cyc)),1,1
   Text x+5,y+166,"has left "+fedName$(charFed(char))+"!",1,1
   refChar=eVariable(cyc)
  EndIf
  If eEvent(cyc)=29 And charFed(eVariable(cyc))=charFed(char)
   headline$="ENEMY AT THE GATE"
   Text x+5,y+138,charName$(char)+" is upset that "+charName$(eVariable(cyc)),1,1
   Text x+5,y+166,"has joined "+fedName$(charFed(char))+"...",1,1
   refChar=eVariable(cyc)
  EndIf
  If eEvent(cyc)=29 And charFed(eVariable(cyc))<>charFed(char)
   headline$="WISH YOU WERE HERE"
   Text x+5,y+138,charName$(char)+" is sad that "+charName$(eVariable(cyc)),1,1
   Text x+5,y+166,"has left "+fedName$(charFed(char))+"...",1,1
   refChar=eVariable(cyc)
  EndIf
  If eEvent(cyc)=30
   headline$="NEW ARRIVAL"
   Text x+5,y+138,charName$(char)+" has arrived to work",1,1
   Text x+5,y+166,"for "+fedName$(eFed(cyc))+"!",1,1
  EndIf
  If eEvent(cyc)=31
   headline$="FRESH MEAT"
   If eFed(cyc)=7
    Text x+5,y+125,"A new wrestler called '"+charName$(char)+"' has",1,1
    Text x+5,y+150,"started training on the independent scene.",1,1
    Text x+5,y+175,He$(g)+" will be unveiled to the press later...",1,1
   Else
    Text x+5,y+125,"A new wrestler called '"+charName$(char)+"' has",1,1
    Text x+5,y+150,"risen through the ranks of "+fedName$(eFed(cyc))+".",1,1
    Text x+5,y+175,He$(g)+" will be unveiled to the press later...",1,1
   EndIf
  EndIf
  ;status issues
  If eEvent(cyc)=40
   headline$="CHAMP STRIPPED"
   Text x+5,y+138,charName$(char)+" has been stripped of the World",1,1
   Text x+5,y+166,"Championship due to being unable to defend it!",1,1
  EndIf
  If eEvent(cyc)=41
   headline$="CHAMP STRIPPED"
   Text x+5,y+138,charName$(char)+" has been stripped of the Inter",1,1
   Text x+5,y+166,"Championship due to being unable to defend it!",1,1
  EndIf
  If eEvent(cyc)=42
   headline$="CHAMPS STRIPPED"
   Text x+5,y+138,charTeamName$(char)+" have been stripped of the Tag",1,1
   Text x+5,y+166,"Championships due to being unable to defend them!",1,1
  EndIf
  If eEvent(cyc)=43
   If eCharged(cyc)=0 Then ProduceSound(0,sCrowd(9),0,0.5) : eCharged(cyc)=1
   headline$="NEW WORLD CHAMP"
   Text x+5,y+138,charName$(char)+" has defeated "+charName$(fedOldChampWorld(eFed(cyc)))+" to become",1,1
   Text x+5,y+166,"the new World Champion of "+fedName$(eFed(cyc))+"!",1,1
  EndIf
  If eEvent(cyc)=44
   If eCharged(cyc)=0 Then ProduceSound(0,sCrowd(9),0,0.25) : eCharged(cyc)=1
   headline$="NEW INTER CHAMP"
   Text x+5,y+138,charName$(char)+" has defeated "+charName$(fedOldChampInter(eFed(cyc)))+" to become",1,1
   Text x+5,y+166,"the new Inter Champion of "+fedName$(eFed(cyc))+"!",1,1
  EndIf
  If eEvent(cyc)=45
   If eCharged(cyc)=0 Then ProduceSound(0,sCrowd(9),0,0.25) : eCharged(cyc)=1
   headline$="NEW TAG CHAMPS"
   Text x+5,y+138,charTeamName$(char)+" have defeated "+charTeamName$(fedOldChampWorld(eFed(cyc)))+" to",1,1
   Text x+5,y+166,"become the new Tag Champions of "+fedName$(eFed(cyc))+"!",1,1
  EndIf
  If eEvent(cyc)=46
   headline$="PAPER CHAMPION"
   Text x+5,y+138,"The World title is wasted on "+charName$(char),1,1
   Text x+5,y+166,"while "+Lower$(He$(g))+"'s unavailable to defend it!",1,1
  EndIf
  If eEvent(cyc)=47
   headline$="PAPER CHAMPION"
   Text x+5,y+138,"The Inter title is wasted on "+charName$(char),1,1
   Text x+5,y+166,"while "+Lower$(He$(g))+"'s unavailable to defend it!",1,1
  EndIf
  If eEvent(cyc)=48
   headline$="PAPER CHAMPIONS"
   Text x+5,y+138,"The Tag titles are wasted on "+charName$(fedChampTag(eFed(cyc),1))+" and",1,1
   Text x+5,y+166,charName$(fedChampTag(eFed(cyc),2))+" while they're unable to compete!",1,1
  EndIf
  If eEvent(cyc)=49
   headline$="FILLING IN THE GAPS"
   If cupTeams(cupSlot)>0
    Text x+5,y+138,charTeamName$(char)+" have been replaced by",1,1
    Text x+5,y+166,charTeamName$(eVariable(cyc))+" in the tournament...",1,1
   Else
    Text x+5,y+138,charName$(char)+" has been replaced by",1,1
    Text x+5,y+166,charName$(eVariable(cyc))+" in the tournament...",1,1
   EndIf
   refChar=eVariable(cyc)
  EndIf
  If eEvent(cyc)=50
   headline$="SHATTERED DREAMS"
   Text x+5,y+138,charName$(char)+" is extremely upset that you",1,1
   Text x+5,y+166,"went back on your word about a title run!",1,1
  EndIf
  If eEvent(cyc)=51
   headline$="ON A PROMISE"
   Text x+5,y+138,"Remember that you've promised to give",1,1
   Text x+5,y+166,charName$(char)+" a run with the World title...",1,1
  EndIf
  If eEvent(cyc)=52
   headline$="ON A PROMISE"
   Text x+5,y+138,"Remember that you've promised to give",1,1
   Text x+5,y+166,charName$(char)+" a run with the Inter title...",1,1
  EndIf
  If eEvent(cyc)=53
   headline$="ON A PROMISE"
   Text x+5,y+138,"Remember that you've promised to give",1,1
   Text x+5,y+166,charName$(char)+"'s team a title run...",1,1
  EndIf
  ;misc events
  If eEvent(cyc)=60 And eVariable(cyc)=0
   headline$="HEALTH COMPLICATIONS"
   Text x+5,y+138,charName$(char)+"'s injury seems to have worsened",1,1
   Text x+5,y+166,"and will now take "+charInjured(char,eVariable(cyc))+" weeks to treat...",1,1
  EndIf
  If eEvent(cyc)=60 And eVariable(cyc)>0
   headline$="HEALTH COMPLICATIONS"
   Text x+5,y+138,charName$(char)+"'s "+textInjury$(eVariable(cyc))+" injury has gotten worse",1,1
   Text x+5,y+166,"and won't be better for another "+charInjured(char,eVariable(cyc))+" weeks yet...",1,1
  EndIf
  If eEvent(cyc)=61 And eVariable(cyc)=0
   headline$="MIRACLE RECOVERY"
   Text x+5,y+138,charName$(char)+"'s injury isn't as bad as first",1,1
   If charInjured(char,eVariable(cyc))=<1 Then Text x+5,y+166,"thought and should be healed by next week!",1,1 
   If charInjured(char,eVariable(cyc))>1 Then Text x+5,y+166,"thought and should heal in just "+charInjured(char,eVariable(cyc))+" weeks!",1,1
  EndIf
  If eEvent(cyc)=61 And eVariable(cyc)>0
   headline$="MIRACLE RECOVERY"
   Text x+5,y+138,charName$(char)+"'s "+textInjury$(eVariable(cyc))+" injury is healing nicely",1,1
   If charInjured(char,eVariable(cyc))=<1 Then Text x+5,y+166,"and should now be better by next week!",1,1
   If charInjured(char,eVariable(cyc))>1 Then Text x+5,y+166,"and should now be better in just "+charInjured(char,eVariable(cyc))+" weeks!",1,1
  EndIf
  If eEvent(cyc)=62
   headline$="HEALTH SCARE"
   Text x+5,y+138,charName$(char)+"'s health has been sapped",1,1
   Text x+5,y+166,"by the symptoms of a mild illness...",1,1
  EndIf
  If eEvent(cyc)=63
   headline$="FIGHTING FIT"
   Text x+5,y+138,charName$(char)+" seems to be feeling great",1,1
   Text x+5,y+166,"today and is in perfect health!",1,1
  EndIf
  If eEvent(cyc)=64
   headline$="LARGER THAN LIFE"
   Text x+5,y+138,charName$(char)+" seems to have gained weight!",1,1
   Text x+5,y+166,"The "+charAge(char)+" year old now weighs in at "+TranslateWeight(char)+"lbs...",1,1 
  EndIf
  If eEvent(cyc)=65
   headline$="SHEDDING THE POUNDS"
   Text x+5,y+138,charName$(char)+" seems to have lost weight!",1,1
   Text x+5,y+166,"The "+charAge(char)+" year old now weighs in at "+TranslateWeight(char)+"lbs...",1,1
  EndIf
  If eEvent(cyc)=66
   headline$="GROWING PAINS"
   Text x+5,y+138,charName$(char)+" seems to have grown taller!",1,1
   Text x+5,y+166,"The "+charAge(char)+" year old now stands at "+GetHeight$(charHeight(char))+"...",1,1
  EndIf
  If eEvent(cyc)=67
   headline$="OVER THE HILL"
   Text x+5,y+138,"At the age of "+charAge(char)+", "+charName$(char)+" has reached",1,1
   Text x+5,y+166,Lower$(His$(g))+" physical peak. "+He$(g)+"'s as good as "+Lower$(He$(g))+"'ll ever be...",1,1
  EndIf 
  ;stat fluctuations
  If eEvent(cyc)=68
   headline$="RISING STAR"
   Text x+5,y+138,charName$(char)+"'s profile has risen",1,1
   Text x+5,y+166,"after being hyped by the press!",1,1
  EndIf
  If eEvent(cyc)=69
   headline$="MOUNTAIN OF MUSCLE"
   Text x+5,y+138,charName$(char)+"'s strength seems to have",1,1
   Text x+5,y+166,"improved considerably in recent weeks!",1,1
  EndIf
  If eEvent(cyc)=70
   headline$="SAFE HANDS"
   Text x+5,y+138,charName$(char)+"'s technical ability seems to have",1,1
   Text x+5,y+166,"improved considerably in recent weeks!",1,1
  EndIf
  If eEvent(cyc)=71
   headline$="FANCY FOOTWORK"
   Text x+5,y+138,charName$(char)+"'s agility seems to have",1,1
   Text x+5,y+166,"improved considerably in recent weeks!",1,1
  EndIf
  If eEvent(cyc)=72
   headline$="SETTING THE PACE"
   Text x+5,y+138,charName$(char)+"'s fitness seems to have",1,1
   Text x+5,y+166,"improved considerably in recent weeks!",1,1
  EndIf
  If eEvent(cyc)=73
   headline$="IRON MAN"
   Text x+5,y+138,charName$(char)+"'s toughness seems to have",1,1
   Text x+5,y+166,"improved considerably in recent weeks!",1,1
  EndIf
  If eEvent(cyc)=74
   headline$="EMPLOYEE OF THE MONTH"
   Text x+5,y+138,charName$(char)+"'s attitude seems to have",1,1
   Text x+5,y+166,"improved considerably in recent weeks!",1,1
  EndIf
  If eEvent(cyc)=75
   headline$="JOB SATISFACTION"
   Text x+5,y+138,charName$(char)+" has been enjoying "+Lower$(His$(g))+" role",1,1
   Text x+5,y+166,"at "+fedName$(eFed(cyc))+" in recent weeks!",1,1
  EndIf
  If eEvent(cyc)=76
   headline$="FADING STAR"
   Text x+5,y+138,charName$(char)+"'s profile has suffered",1,1
   Text x+5,y+166,"after being criticized by the press...",1,1
  EndIf
  If eEvent(cyc)=77
   headline$="PENCIL NECK GEEK"
   Text x+5,y+138,charName$(char)+"'s strength seems to have",1,1
   Text x+5,y+166,"deteriorated considerably in recent weeks...",1,1
  EndIf
  If eEvent(cyc)=78
   headline$="SLIPPERY FINGERS"
   Text x+5,y+138,charName$(char)+"'s technical ability seems to have",1,1
   Text x+5,y+166,"deteriorated considerably in recent weeks...",1,1
  EndIf
  If eEvent(cyc)=79
   headline$="TWO LEFT FEET"
   Text x+5,y+138,charName$(char)+"'s agility seems to have",1,1
   Text x+5,y+166,"deteriorated considerably in recent weeks...",1,1
  EndIf
  If eEvent(cyc)=80
   headline$="OUT OF SHAPE"
   Text x+5,y+138,charName$(char)+"'s fitness seems to have",1,1
   Text x+5,y+166,"deteriorated considerably in recent weeks...",1,1
  EndIf
  If eEvent(cyc)=81
   headline$="HANDLE WITH CARE"
   Text x+5,y+138,charName$(char)+"'s toughness seems to have",1,1
   Text x+5,y+166,"deteriorated considerably in recent weeks...",1,1
  EndIf
  If eEvent(cyc)=82
   headline$="PROBLEM CHILD"
   Text x+5,y+138,charName$(char)+"'s attitude seems to have",1,1
   Text x+5,y+166,"deteriorated considerably in recent weeks...",1,1
  EndIf
  If eEvent(cyc)=83
   headline$="BAD VIBES"
   Text x+5,y+138,charName$(char)+" seems to be growing weary",1,1
   Text x+5,y+166,"of working for "+fedName$(eFed(cyc))+"...",1,1
  EndIf
  If eEvent(cyc)=84
   If eCharged(cyc)=0 Then ProduceSound(0,sCrowd(3),0,0.5) : eCharged(cyc)=1
   headline$="CAREER KILLER" 
   If gamOpponent(gamDate-1)=char Then v=gamChar Else v=gamOpponent(gamDate-1)
   Text x+5,y+138,charName$(char)+" has been branded negligent for",1,1
   Text x+5,y+166,"injuring "+charName$(v)+" in their recent match!",1,1
   refChar=v
  EndIf
  If eEvent(cyc)=85
   If eCharged(cyc)=0 Then ProduceSound(0,sCrowd(3),0,0.5) : eCharged(cyc)=1
   headline$="IN COLD BLOOD" 
   If gamOpponent(gamDate-1)=char Then v=gamChar Else v=gamOpponent(gamDate-1)
   Text x+5,y+138,"The press have blamed "+charName$(char)+" for",1,1
   Text x+5,y+166,"causing "+charName$(v)+"'s recent death!",1,1
  EndIf 
  If eEvent(cyc)=86
   headline$="THE DRUGS DON'T WORK" 
   Text x+5,y+138,charName$(char)+" has developed a dependency on",1,1
   Text x+5,y+166,"drugs and is suffering from withdrawl symptoms!",1,1
   ChannelPitch chTheme,PercentOf#(chThemePitch,90)
  EndIf 
  If eEvent(cyc)=87
   If eCharged(cyc)=0 Then ProduceSound(0,sCrowd(3),0,0.5) : eCharged(cyc)=1
   headline$="BAD HABIT" 
   Text x+5,y+138,charName$(char)+" has lost the respect of the",1,1
   Text x+5,y+166,"fans after being exposed as a drug user!",1,1
   ChannelPitch chTheme,PercentOf#(chThemePitch,90)
  EndIf 
  If eEvent(cyc)=88
   headline$="KEEPING UP APPEARANCES"
   Text x+5,y+138,charName$(char)+"'s profile has suffered due",1,1
   Text x+5,y+166,"to a lack of exposure in recent weeks...",1,1
  EndIf
  If eEvent(cyc)=89
   headline$="OVER THE HILL"
   Text x+5,y+138,"At the age of "+charAge(char)+", "+charName$(char)+"'s",1,1
   Text x+5,y+166,"training regime is futile...",1,1
  EndIf
  If eEvent(cyc)=90
   headline$="HOME BODY"
   Text x+5,y+138,charName$(char)+" resents being asked to train",1,1
   Text x+5,y+166,"and has decided to stay at home instead!",1,1
  EndIf 
  If eEvent(cyc)=91 And IdentifyInjury(char)=0
   headline$="NO PAIN, NO GAIN"
   Text x+5,y+138,charName$(char)+" sustained an injury while training",1,1
   Text x+5,y+166,"and will have to take it easy for the next "+InjuryStatus(char)+" weeks...",1,1
  EndIf
  If eEvent(cyc)=91 And IdentifyInjury(char)>0
   headline$="NO PAIN, NO GAIN"
   Text x+5,y+138,charName$(char)+" sustained a "+textInjury$(IdentifyInjury(char))+" injury while training",1,1
   Text x+5,y+166,"and will be suffering with it for the next "+charInjured(char,IdentifyInjury(char))+" weeks...",1,1
  EndIf
  If eEvent(cyc)=92
   headline$="MISSING IN ACTION"
   Text x+5,y+138,charName$(char)+" has failed to turn up at the arena!",1,1
   Text x+5,y+166,He$(g)+" won't be available for tonight's show...",1,1
  EndIf 
  If eEvent(cyc)=93
   headline$="UNDER THE INFLUENCE"
   Text x+5,y+138,charName$(char)+" seems to have turned up drunk!",1,1
   Text x+5,y+166,He$(g)+"'s in no state to perform tonight...",1,1
  EndIf
  If eEvent(cyc)=94
   headline$="CRIME DOESN'T PAY"
   Text x+5,y+138,charName$(char)+" has been arrested for assault!",1,1
   Text x+5,y+166,He$(g)+"'ll be unavailable until the matter is resolved...",1,1
   ChannelPitch chTheme,PercentOf#(chThemePitch,90)
  EndIf 
  If eEvent(cyc)=95
   headline$="OUT OF THE LOOP"
   Text x+5,y+138,charName$(char)+" is a little upset that",1,1
   Text x+5,y+166,Lower$(He$(g))+" wasn't used on last week's show...",1,1
  EndIf
  ;relationship issues
  If eEvent(cyc)=103
   headline$="FRIENDS IN HIGH PLACES"
   Text x+5,y+138,charName$(char)+"'s profile has benefited",1,1
   Text x+5,y+166,"from being associated with "+charName$(charManager(char))+"!",1,1
  EndIf
  If eEvent(cyc)=104
   headline$="BAD COMPANY"
   Text x+5,y+138,charName$(char)+"'s profile has suffered",1,1
   Text x+5,y+166,"from being associated with "+charName$(charManager(char))+"...",1,1
  EndIf
  If eEvent(cyc)=108
   headline$="FRIENDS IN HIGH PLACES"
   Text x+5,y+138,charName$(char)+"'s profile has benefited",1,1
   Text x+5,y+166,"from being associated with "+charName$(charPartner(char))+"!",1,1
  EndIf
  If eEvent(cyc)=109
   headline$="BAD COMPANY"
   Text x+5,y+138,charName$(char)+"'s profile has suffered",1,1
   Text x+5,y+166,"from being associated with "+charName$(charPartner(char))+"...",1,1
  EndIf
  If eEvent(cyc)=110
   headline$="MUTUAL ADMIRATION"
   Text x+5,y+138,charName$(char)+" has become close friends",1,1
   Text x+5,y+166,"with "+charName$(eVariable(cyc))+"...",1,1
   refChar=eVariable(cyc)
  EndIf
  If eEvent(cyc)=111
   headline$="WAR OF WORDS"
   Text x+5,y+138,charName$(char)+" has developed a bitter",1,1
   Text x+5,y+166,"rivalry with "+charName$(eVariable(cyc))+"!",1,1
   refChar=eVariable(cyc)
  EndIf
  If eEvent(cyc)=112
   headline$="MUTUAL ADMIRATION"
   Text x+5,y+138,charName$(char)+" has become close friends with",1,1
   Text x+5,y+166,charName$(eVariable(cyc))+" of "+fedName$(charFed(eVariable(cyc)))+"...",1,1
   refChar=eVariable(cyc)
  EndIf
  If eEvent(cyc)=113
   headline$="WAR OF WORDS"
   Text x+5,y+138,charName$(char)+" has developed a bitter rivalry",1,1
   Text x+5,y+166,"with "+charName$(eVariable(cyc))+" of "+fedName$(charFed(eVariable(cyc)))+"!",1,1
   refChar=eVariable(cyc)
  EndIf
  If eEvent(cyc)=114
   headline$="BURY THE HATCHET"
   If charFed(eVariable(cyc))=charFed(gamChar)
    Text x+5,y+138,charName$(char)+" has settled "+Lower$(His$(g)),1,1
    Text x+5,y+166,"differences with "+charName$(eVariable(cyc))+"...",1,1
   Else 
    Text x+5,y+138,charName$(char)+" has settled "+Lower$(His$(g))+" differences",1,1
    Text x+5,y+166,"with "+charName$(eVariable(cyc))+" of "+fedName$(charFed(eVariable(cyc)))+"...",1,1
   EndIf
   refChar=eVariable(cyc)
  EndIf
  If eEvent(cyc)=115
   headline$="OUT OF STEAM"
   Text x+5,y+138,charName$(char)+"'s feud with "+charName$(eVariable(cyc)),1,1
   Text x+5,y+166,"seems to have lost momentum...",1,1
  EndIf
  If eEvent(cyc)=116
   headline$="IDENTITY CRISIS"
   Text x+5,y+138,"The fans insist on treating "+charName$(char),1,1
   Text x+5,y+166,"as a Heel despite "+Lower$(His$(g))+" attempts to play Face!",1,1
  EndIf
  If eEvent(cyc)=117
   headline$="IDENTITY CRISIS"
   Text x+5,y+138,"The fans insist on treating "+charName$(char),1,1
   Text x+5,y+166,"as a Face despite "+Lower$(His$(g))+" attempts to play Heel!",1,1
  EndIf
  If eEvent(cyc)=118
   headline$="BOILING POINT"
   Text x+5,y+138,charName$(char)+" and "+charName$(eVariable(cyc))+" are already tired",1,1
   Text x+5,y+166,"after coming to blows in a backstage brawl!",1,1
   refChar=eVariable(cyc) 
  EndIf
  If eEvent(cyc)=119
   headline$="HELPING HAND"
   Text x+5,y+138,charName$(char)+"'s friendship with "+charName$(eVariable(cyc)),1,1
   Text x+5,y+166,"has created a good atmosphere in the locker room!",1,1
   refChar=eVariable(cyc)
  EndIf
  ;court cases
  If eEvent(cyc)=131
   headline$="SEX SCANDAL!" : ChannelPitch chTheme,PercentOf#(chThemePitch,90)
   Text x+5,y+125,charName$(char)+" claims that "+Lower$(He$(g))+"'s being sexually",1,1
   Text x+5,y+150,"abused at "+fedName$(charFed(gamChar))+"! The matter will",1,1
   Text x+5,y+175,"be settled in court over the following weeks...",1,1
  EndIf
  If eEvent(cyc)=132
   headline$="UNFAIR DISMISSAL" : ChannelPitch chTheme,PercentOf#(chThemePitch,90)
   Text x+5,y+125,charName$(char)+" claims that "+Lower$(His$(g))+" departure",1,1
   Text x+5,y+150,"from "+fedName$(charFed(gamChar))+" was unlawful! "+He$(g)+"'ll pursue",1,1
   Text x+5,y+175,"compensation in court as soon as possible...",1,1
  EndIf
  If eEvent(cyc)=133
   headline$="HOSTAGE NEGOTIATIONS" : ChannelPitch chTheme,PercentOf#(chThemePitch,90)
   Text x+5,y+125,charName$(char)+" claims that "+fedName$(charFed(gamChar)),1,1
   Text x+5,y+150,"has violated "+Lower$(His$(g))+" human rights! "+He$(g)+"'ll pursue a",1,1
   Text x+5,y+175,"release in court over the following weeks...",1,1
  EndIf
  If eEvent(cyc)=134
   headline$="CAREER KILLER" : ChannelPitch chTheme,PercentOf#(chThemePitch,90)
   Text x+5,y+125,charName$(char)+" claims that "+fedName$(charFed(gamChar)),1,1
   Text x+5,y+150,"has ruined "+Lower$(His$(g))+" career! "+He$(g)+" intends to pursue",1,1
   Text x+5,y+175,"compensation in court as soon as possible...",1,1
  EndIf
  If eEvent(cyc)=135
   headline$="SCARRED FOR LIFE" : ChannelPitch chTheme,PercentOf#(chThemePitch,90)
   Text x+5,y+125,charName$(char)+" intends to sue "+fedName$(charFed(gamChar)),1,1
   Text x+5,y+150,"over "+Lower$(His$(g))+" wrestling injuries! The matter will",1,1
   Text x+5,y+175,"be settled in court over the following weeks...",1,1
  EndIf
  If eEvent(cyc)=136
   headline$="DRUG DEALER" : ChannelPitch chTheme,PercentOf#(chThemePitch,90)
   Text x+5,y+125,charName$(char)+" claims that "+fedName$(charFed(gamChar)),1,1
   Text x+5,y+150,"is responsible for "+Lower$(His$(g))+" drug use! The matter will",1,1
   Text x+5,y+175,"be settled in court over the following weeks...",1,1
  EndIf
  If eEvent(cyc)=137
   headline$="SMALL PRINT" : ChannelPitch chTheme,PercentOf#(chThemePitch,90)
   Text x+5,y+125,charName$(char)+" claims that "+fedName$(charFed(gamChar)),1,1
   Text x+5,y+150,"has withheld money from "+Him$(g)+"! "+He$(g)+"'ll pursue",1,1
   Text x+5,y+175,"compensation in court as soon as possible...",1,1
  EndIf
  If eEvent(cyc)=138
   headline$="MENACE TO SOCIETY" : ChannelPitch chTheme,PercentOf#(chThemePitch,90)
   Text x+5,y+125,charName$(char)+" claims that "+fedName$(charFed(gamChar)),1,1
   Text x+5,y+150,"is responsible for copycat injuries! "+He$(g)+"'ll make",1,1
   Text x+5,y+175,Lower$(His$(g))+" case in court over the following weeks...",1,1
  EndIf
  If eEvent(cyc)=139
   headline$="ATTACK OF THE CLONES" : ChannelPitch chTheme,PercentOf#(chThemePitch,90)
   Text x+5,y+125,charName$(char)+" claims that "+fedName$(charFed(gamChar)),1,1
   Text x+5,y+150,"has stolen ideas from "+fedName$(charFed(char))+"!",1,1
   Text x+5,y+175,He$(g)+"'ll make "+Lower$(His$(g))+" case in court in a few weeks...",1,1
  EndIf
  ;mission reminders
  If eEvent(cyc)=151
   headline$="MISSION IMPOSSIBLE"
   Text x+5,y+138,"Remember you're on a mission to",1,1
   Text x+5,y+166,"attain a 'Popularity' rating of "+gamTarget+"%...",1,1
  EndIf
  If eEvent(cyc)=152
   headline$="MISSION IMPOSSIBLE"
   Text x+5,y+138,"Remember you're on a mission to",1,1
   Text x+5,y+166,"attain a 'Reputation' rating of "+gamTarget+"%...",1,1
  EndIf
  If eEvent(cyc)=153
   headline$="MISSION IMPOSSIBLE"
   Text x+5,y+138,"Remember you're on a mission to",1,1
   Text x+5,y+166,"amass a fortune of $"+GetFigure$(gamTarget)+"...",1,1
  EndIf
  If eEvent(cyc)=154
   headline$="MISSION IMPOSSIBLE"
   Text x+5,y+138,"Remember you're on a mission to get",1,1
   Text x+5,y+166,"out of debt by the "+DescribeDate$(gamDeadline,0)+"!",1,1
  EndIf
  If eEvent(cyc)=155
   headline$="MISSION IMPOSSIBLE"
   Text x+5,y+138,"Remember you're on a mission to regain",1,1
   Text x+5,y+166,"the number one spot in the ratings!",1,1
  EndIf
  If eEvent(cyc)=156
   headline$="MISSION IMPOSSIBLE"
   Text x+5,y+138,"Remember you're on a mission to sign",1,1
   Text x+5,y+166,charName$(gamTarget)+" from "+fedName$(charFed(gamTarget))+"...",1,1 
   refChar=gamTarget
  EndIf
  If eEvent(cyc)=157
   headline$="MISSION IMPOSSIBLE"
   Text x+5,y+138,"Remember you're on a mission to",1,1
   Text x+5,y+166,"remove "+charName$(gamTarget)+" from the roster...",1,1
   refChar=gamTarget
  EndIf
  If eEvent(cyc)=158
   headline$="MISSION IMPOSSIBLE"
   Text x+5,y+138,"Remember you're on a mission to crown",1,1
   Text x+5,y+166,charName$(gamTarget)+" as the World Champion...",1,1
   refChar=gamTarget
  EndIf
  If eEvent(cyc)=159
   headline$="MISSION IMPOSSIBLE"
   Text x+5,y+138,"Remember you're on a mission to crown",1,1
   Text x+5,y+166,charName$(gamTarget)+" as the Inter Champion...",1,1
   refChar=gamTarget
  EndIf
  If eEvent(cyc)=160
   headline$="MISSION IMPOSSIBLE"
   Text x+5,y+138,"Remember you're on a mission to make",1,1
   Text x+5,y+166,charName$(gamTarget)+" a Tag Champion...",1,1
   refChar=gamTarget
  EndIf
  If eEvent(cyc)=161
   headline$="MISSION IMPOSSIBLE"
   Text x+5,y+138,"Remember you're on a mission to",1,1
   Text x+5,y+166,"expand the roster to "+gamTarget+" wrestlers...",1,1
  EndIf
  If eEvent(cyc)=162
   headline$="MISSION IMPOSSIBLE"
   Text x+5,y+138,"Remember you're on a mission to",1,1
   Text x+5,y+166,"reduce the roster to "+gamTarget+" wrestlers...",1,1
  EndIf
  If eEvent(cyc)=163
   headline$="MISSION IMPOSSIBLE"
   Text x+5,y+138,"Remember you're on a mission to",1,1
   Text x+5,y+166,"acquire the '"+prodName$(gamTarget)+"' production...",1,1
  EndIf
  If eEvent(cyc)=164
   headline$="MISSION IMPOSSIBLE"
   Text x+5,y+138,"Remember you're on a mission to acquire",1,1
   Text x+5,y+166,"a wrestler with a 'Popularity' rating of 90%...",1,1
  EndIf
  If eEvent(cyc)=165
   headline$="MISSION IMPOSSIBLE"
   Text x+5,y+138,"Remember you're on a mission to acquire",1,1
   Text x+5,y+166,"a wrestler with a 'Strength' rating of 90%...",1,1
  EndIf
  If eEvent(cyc)=166
   headline$="MISSION IMPOSSIBLE"
   Text x+5,y+138,"Remember you're on a mission to acquire",1,1
   Text x+5,y+166,"a wrestler with a 'Skill' rating of 90%...",1,1
  EndIf
  If eEvent(cyc)=167
   headline$="MISSION IMPOSSIBLE"
   Text x+5,y+138,"Remember you're on a mission to acquire",1,1
   Text x+5,y+166,"a wrestler with an 'Agility' rating of 90%...",1,1
  EndIf
  If eEvent(cyc)=168
   headline$="MISSION IMPOSSIBLE"
   Text x+5,y+138,"Remember you're on a mission to acquire",1,1
   Text x+5,y+166,"a wrestler with a 'Stamina' rating of 90%...",1,1
  EndIf
  If eEvent(cyc)=169
   headline$="MISSION IMPOSSIBLE"
   Text x+5,y+138,"Remember you're on a mission to acquire",1,1
   Text x+5,y+166,"a wrestler with a 'Toughness' rating of 90%...",1,1
  EndIf
  If eEvent(cyc)=170
   headline$="MISSION IMPOSSIBLE"
   Text x+5,y+138,"Remember you're on a mission to",1,1
   Text x+5,y+166,"sign a female wrestler...",1,1
  EndIf
  ;promotion title issues
  If eEvent(cyc)=200
   If eCharged(cyc)=0 Then ProduceSound(0,sCrowd(9),0,0.5) : eCharged(cyc)=1
   headline$="NEW KING IN TOWN"
   Text x+5,y+138,charName$(char)+" has been appointed as the",1,1
   Text x+5,y+166,"new booker of "+fedName$(eFed(cyc))+"!",1,1
  EndIf
  If eEvent(cyc)=201
   If eCharged(cyc)=0 Then ProduceSound(0,sCrowd(9),0,0.5) : eCharged(cyc)=1
   headline$="NEW WORLD CHAMP"
   Text x+5,y+138,charName$(fedChampWorld(eFed(cyc)))+" has been crowned the new",1,1
   Text x+5,y+166,"World Champion of "+fedName$(eFed(cyc))+"!",1,1
  EndIf
  If eEvent(cyc)=202
   If eCharged(cyc)=0 Then ProduceSound(0,sCrowd(9),0,0.5) : eCharged(cyc)=1
   headline$="NEW INTER CHAMP"
   Text x+5,y+138,charName$(fedChampInter(eFed(cyc)))+" has been crowned the new ",1,1
   Text x+5,y+166,"Inter Champion of "+fedName$(eFed(cyc))+"!",1,1
  EndIf
  If eEvent(cyc)=203
   If eCharged(cyc)=0 Then ProduceSound(0,sCrowd(9),0,0.5) : eCharged(cyc)=1
   headline$="NEW TAG CHAMPS"
   Text x+5,y+138,charName$(fedChampTag(eFed(cyc),1))+" and "+charName$(fedChampTag(eFed(cyc),2))+" have been crowned",1,1
   Text x+5,y+166,"the new Tag Champions of "+fedName$(eFed(cyc))+"!",1,1
  EndIf
  If eEvent(cyc)=204
   headline$="GOING NOWHERE"
   Text x+5,y+138,fedName$(eFed(cyc))+" has no World Champion!",1,1
   Text x+5,y+166,"We should crown a new one as soon as possible...",1,1
  EndIf
  If eEvent(cyc)=205
   headline$="GOING NOWHERE"
   Text x+5,y+138,fedName$(eFed(cyc))+" has no Inter Champion!",1,1
   Text x+5,y+166,"We should crown a new one as soon as possible...",1,1
  EndIf
  If eEvent(cyc)=206
   headline$="GOING NOWHERE"
   Text x+5,y+138,fedName$(eFed(cyc))+" has no Tag Champions!",1,1
   Text x+5,y+166,"We should crown new ones as soon as possible...",1,1
  EndIf  
  ;promotion status issues
  If eEvent(cyc)=210 And fedRanked(eFed(cyc))=1
   headline$="GREATEST SHOW ON EARTH"
   If eCharged(cyc)=0 Then ProduceSound(0,sCrowd(9),0,0.5) : eCharged(cyc)=1
   Text x+5,y+138,fedName$(eFed(cyc))+" have overtaken "+fedName$(fedOldList(fedRanked(eFed(cyc)))),1,1
   Text x+5,y+166,"as the world's leading wrestling promotion!",1,1
  EndIf
  If eEvent(cyc)=210 And fedRanked(eFed(cyc))>1
   headline$="NEW WORLD ORDER"
   Text x+5,y+138,fedName$(eFed(cyc))+" have overtaken "+fedName$(fedOldList(fedRanked(eFed(cyc)))),1,1
   Text x+5,y+166,"as the world's "+textNumber$(fedRanked(eFed(cyc)))+" biggest wrestling promotion...",1,1
  EndIf
  If eEvent(cyc)=211
   headline$="BAD DRAW"
   Text x+5,y+138,fedName$(eFed(cyc))+"'s profile has suffered since",1,1
   Text x+5,y+166,charName$(fedChampWorld(eFed(cyc)))+" became their World Champion...",1,1
   showChar=fedChampWorld(eFed(cyc))
  EndIf
  If eEvent(cyc)=212
   headline$="MOST VALUABLE PLAYER"
   Text x+5,y+138,fedName$(eFed(cyc))+"'s profile has improved since",1,1
   Text x+5,y+166,charName$(fedChampWorld(eFed(cyc)))+" became their World Champion!",1,1
   showChar=fedChampWorld(eFed(cyc))
  EndIf
  If eEvent(cyc)=213
   headline$="THE NEXT BIG THING"
   Text x+5,y+138,fedName$(eFed(cyc))+"'s product has attracted",1,1
   Text x+5,y+166,"a stronger following in recent weeks!",1,1
  EndIf
  If eEvent(cyc)=214
   headline$="OLD NEWS"
   Text x+5,y+138,fedName$(eFed(cyc))+"'s product has suffered",1,1
   Text x+5,y+166,"a dip in popularity in recent weeks...",1,1
  EndIf
  If eEvent(cyc)=215
   headline$="KEEP IT CLEAN"
   Text x+5,y+138,fedName$(eFed(cyc))+" have been striving",1,1
   Text x+5,y+166,"to deliver a more respectable product...",1,1
  EndIf
  If eEvent(cyc)=216
   headline$="DIRTY MOVES"
   Text x+5,y+138,fedName$(eFed(cyc))+" seem to be intent on",1,1
   Text x+5,y+166,"delivering a more controversial product!",1,1
  EndIf
  If eEvent(cyc)=217
   headline$="THE IN THING"
   Text x+5,y+138,"The whole sport of wrestling seems to have",1,1
   Text x+5,y+166,"captured the imagination of the public!",1,1
  EndIf
  If eEvent(cyc)=218
   headline$="OLD NEWS"
   Text x+5,y+138,"The whole sport of wrestling seems be",1,1
   Text x+5,y+166,"losing its appeal to the outside world...",1,1
  EndIf 
  If eEvent(cyc)=219
   headline$="NO SECOND PRIZE"
   If eFed(cyc)=charFed(gamChar) And eCharged(cyc)=0 Then ProduceSound(0,sCrowd(3),0,0.5) : eCharged(cyc)=1
   Text x+5,y+138,fedName$(eFed(cyc))+" has been overtaken by",1,1
   Text x+5,y+166,showName$(showList(1))+" as the world's #1 TV show...",1,1
  EndIf
  If eEvent(cyc)=220
   headline$="GREATEST SHOW ON EARTH"
   If eCharged(cyc)=0 Then ProduceSound(0,sCrowd(9),0,0.5) : eCharged(cyc)=1
   Text x+5,y+138,fedName$(eFed(cyc))+" has overtaken "+showName$(showOldList(1)),1,1
   Text x+5,y+166,"to become the world's highest rated TV show!",1,1
  EndIf
  ;promotion scheduling issues
  If eEvent(cyc)=230
   headline$="NO SHOW"
   Text x+5,y+138,"The TV Network won't be able to broadcast",1,1
   Text x+5,y+166,"tonight's show due to technical problems!",1,1
   If eCharged(cyc)=0 Then ProduceSound(0,sCrowd(3),0,0.5) : eCharged(cyc)=1
   ChannelPitch chTheme,PercentOf#(chThemePitch,90)
  EndIf
  If eEvent(cyc)=231
   headline$="NO SHOW"
   Text x+5,y+138,"The TV Network won't be broadcasting the",1,1
   Text x+5,y+166,"show next week due to a scheduling mix-up!",1,1
   If eCharged(cyc)=0 Then ProduceSound(0,sCrowd(3),0,0.5) : eCharged(cyc)=1
   ChannelPitch chTheme,PercentOf#(chThemePitch,90)
  EndIf
  If eEvent(cyc)=232
   headline$="NO SHOW"
   Text x+5,y+138,"The TV Network has withdrawn the show from next",1,1
   Text x+5,y+166,"month's schedule to make room for another programme...",1,1
   If eCharged(cyc)=0 Then ProduceSound(0,sCrowd(3),0,0.5) : eCharged(cyc)=1
   ChannelPitch chTheme,PercentOf#(chThemePitch,90)
  EndIf
  
  If eEvent(cyc)=238
   headline$="THE MOMENT OF TRUTH"
   Text x+5,y+138,"It's a big night for "+fedName$(eFed(cyc))+"!",1,1
   Text x+5,y+166,"Make sure the roster rises to the occasion...",1,1
  EndIf
  If eEvent(cyc)=239
   headline$="TENSION MOUNTS"
   Text x+5,y+138,"Remember to prepare for the big event that",1,1
   Text x+5,y+166,fedName$(eFed(cyc))+" has planned next week!",1,1
  EndIf
  If eEvent(cyc)=240
   If eCharged(cyc)=0 Then ProduceSound(0,sCrowd(9),0,0.5) : eCharged(cyc)=1
   headline$="HAPPY NEW YEAR!"
   Text x+5,y+138,"A new year is upon us! You are now "+charAge(char)+" years old and",1,1
   Text x+5,y+166,"have been in the wrestling business for "+CountExperience(char,0)+" weeks...",1,1
  EndIf
  ;promotion business advice
  If eEvent(cyc)=250
   headline$="BALANCE OF POWER"
   Text x+5,y+138,"The "+fedName$(eFed(cyc))+" roster has a",1,1
   Text x+5,y+166,"disproportionately high number of Faces!",1,1
  EndIf 
  If eEvent(cyc)=251
   headline$="BALANCE OF POWER"
   Text x+5,y+138,"The "+fedName$(eFed(cyc))+" roster has a",1,1
   Text x+5,y+166,"disproportionately high number of Heels!",1,1
  EndIf
  If eEvent(cyc)=252
   headline$="SMALLTIME"
   Text x+5,y+138,"The "+fedName$(eFed(cyc))+" roster is",1,1
   Text x+5,y+166,"too small to stage a decent show!",1,1
  EndIf
  If eEvent(cyc)=253
   headline$="GLASS CEILING"
   Text x+5,y+138,fedName$(eFed(cyc))+" is approaching the",1,1
   Text x+5,y+166,"limit of its current production values...",1,1
  EndIf
  If eEvent(cyc)=254
   headline$="GLASS CEILING"
   Text x+5,y+138,fedName$(eFed(cyc))+" is being held back",1,1
   Text x+5,y+166,"by its production values!",1,1
  EndIf 
  ;production reports
  If eEvent(cyc)=270
   headline$="WASTE OF TIME"
   Text x+5,y+138,"Our production team are going to waste!",1,1
   Text x+5,y+166,"We should assign them a project to work on...",1,1
  EndIf
  If eEvent(cyc)=271
   headline$="BAD CREDIT"
   Text x+5,y+138,"Our financial status isn't stable enough to",1,1
   Text x+5,y+166,"fund the production of '"+prodName$(eVariable(cyc))+"'...",1,1
  EndIf
  If eEvent(cyc)=272
   headline$="UNDER DEVELOPMENT" 
   Text x+5,y+138,"The '"+prodName$(gamBuild)+"' production is taking longer",1,1
   Text x+5,y+166,"than expected and won't be available until next week...",1,1
  EndIf
  If eEvent(cyc)=273
   headline$="SPECIAL DELIVERY"
   If eCharged(cyc)=0 Then PlaySound sProduce : eCharged(cyc)=1
   Text x+5,y+138,"Work has finished on the '"+prodName$(eVariable(cyc))+"' production!",1,1
   Text x+5,y+166,"("+prodDesc$(eVariable(cyc))+")",1,1
  EndIf
  If eEvent(cyc)=274
   headline$="PAPER TRAIL"
   If eCharged(cyc)=0 Then PlaySound sTrash : eCharged(cyc)=1
   Text x+5,y+138,"Our agreement with the legal team has expired!",1,1
   Text x+5,y+166,"We'll have to go through the system again...",1,1
  EndIf
  If eEvent(cyc)=275
   headline$="BLOOD MONEY"
   If eCharged(cyc)=0 Then PlaySound sTrash : eCharged(cyc)=1
   Text x+5,y+138,"Our agreement with the medical team has expired!",1,1
   Text x+5,y+166,"We'll have to go through the system again...",1,1
  EndIf
  If eEvent(cyc)=276
   headline$="EXHAUSTED"
   If eCharged(cyc)=0 Then PlaySound sTrash : eCharged(cyc)=1
   Text x+5,y+138,"Our agreement with the training team has expired!",1,1
   Text x+5,y+166,"We'll have to go through the system again...",1,1
  EndIf
  If eEvent(cyc)=277
   headline$="SCRIPT HOLES"
   If eCharged(cyc)=0 Then PlaySound sTrash : eCharged(cyc)=1
   Text x+5,y+138,"The scripts for our promos are no longer relevant!",1,1
   Text x+5,y+166,"We'll need to have some new ones written up...",1,1
  EndIf
  If eEvent(cyc)=278
   headline$="FASHION VICTIM"
   If eCharged(cyc)=0 Then PlaySound sTrash : eCharged(cyc)=1
   Text x+5,y+138,"Our costumes are no longer in fashion!",1,1
   Text x+5,y+166,"We'll need to produce a new wardrobe...",1,1
  EndIf
  If eEvent(cyc)=279
   headline$="REFURBISHMENTS"
   If eCharged(cyc)=0 Then PlaySound sTrash : eCharged(cyc)=1
   Text x+5,y+138,"Our arena decorations are falling apart!",1,1
   Text x+5,y+166,"The ring crew will need some new materials...",1,1
  EndIf
  If eEvent(cyc)=280
   headline$="SONIC BOOM"
   If eCharged(cyc)=0 Then PlaySound sTrash : eCharged(cyc)=1
   Text x+5,y+138,"Our music equipment isn't working properly!",1,1
   Text x+5,y+166,"We'll need to produce a brand new system...",1,1
  EndIf
  If eEvent(cyc)=281
   headline$="LIGHTS OUT"
   If eCharged(cyc)=0 Then PlaySound sTrash : eCharged(cyc)=1
   Text x+5,y+138,"Our visual effects equipment has burnt out!",1,1
   Text x+5,y+166,"We'll need to produce a brand new system...",1,1
  EndIf
  If eEvent(cyc)=282
   headline$="UP IN FLAMES"
   If eCharged(cyc)=0 Then PlaySound sTrash : eCharged(cyc)=1
   Text x+5,y+138,"Our pyrotechnics have lost their spark!",1,1
   Text x+5,y+166,"We'll need to produce some new ones...",1,1
  EndIf
  If eEvent(cyc)=283
   headline$="DEATH TRAP"
   If eCharged(cyc)=0 Then PlaySound sTrash : eCharged(cyc)=1
   Text x+5,y+138,"Our engineering work is no longer stable!",1,1
   Text x+5,y+166,"We'll need to produce some new structures...",1,1
  EndIf
  If eEvent(cyc)=284
   headline$="OUT OF STOCK"
   If eCharged(cyc)=0 Then PlaySound sTrash : eCharged(cyc)=1
   Text x+5,y+138,"Our range of furniture has been exhausted!",1,1
   Text x+5,y+166,"We'll need to manufacture some new stock...",1,1
  EndIf
  If eEvent(cyc)=285
   headline$="OUT OF STOCK"
   If eCharged(cyc)=0 Then PlaySound sTrash : eCharged(cyc)=1
   Text x+5,y+138,"Our selection of props has been exhausted!",1,1
   Text x+5,y+166,"We'll need to manufacture some new ones...",1,1
  EndIf
  ;display headline
  SetFont fontNews(10) : Color 0,0,0
  Text x+5,y-63,headline$,1,1
  ;photo display
  If showChar>0
   If refChar>0 Then DrawImage charPhoto(refChar),x+70,y+33
   If charManager(showChar)>0 And charManager(showChar)<>refChar
    If charPartner(showChar)=0 Or charPartner(showChar)=charManager(showChar) Then DrawImage charPhoto(charManager(showChar)),x+125,y+33
   EndIf
   If charPartner(showChar)>0 And charPartner(showChar)<>refChar And charManager(showChar)=0 Then DrawImage charPhoto(charPartner(showChar)),x+125,y+33
   If charManager(showChar)>0 And charManager(showChar)<>refChar And charPartner(showChar)>0 And charPartner(showChar)<>refChar And charPartner(showChar)<>charManager(showChar)
    DrawImage charPhoto(charManager(showChar)),x+115,y+32
    DrawImage charPhoto(charPartner(showChar)),x+130,y+33
   EndIf
   DrawImage charPhoto(showChar),x+145,y+34
  EndIf
  ;prompt
  If foc=10 And gotim>20
   SetFont font(2)
   Outline(">>> PRESS ANY COMMAND TO PROCEED >>>",x,y+210,100,100,100,255,255,255)
  EndIf
 EndIf
 ;cursor
 DrawImage gCursor,MouseX(),MouseY()

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
FreeTimer timer
 PlaySound sMenuGo
 ;introduce new character
 If gamNewcomer>0
  editChar=gamNewcomer
  screen=51 : screenAgenda=9 : Editor()
 EndIf 
 ;generate arena
 Loader("Please Wait","Generating Arena")
 If gamVenue(gamDate)=0 Then GenerateArena(charFed(gamChar),gamSchedule(gamDate),0)
 GenerateArena(charFed(gamChar),gamSchedule(gamDate),1) 
 randy=Rnd(0,100)
 If randy=<1 Then gamAgreement(20)=1
 gamAttendance(gamDate)=GenerateAttendance()
 arenaAttendance=TranslateAttendance(gamAttendance(gamDate)) 
 If gamSchedule(gamDate)>0 
  fed=charFed(gamChar) : screen=13
 Else
  PostNewsProcess()
 EndIf
End Function

;/////////////////////////////////////////////////////////////////////////////////////
;------------------------------ RELATED FUNCTIONS ------------------------------------
;/////////////////////////////////////////////////////////////////////////////////////

;AVAILABLE FOR SIMULATED FIGHT?
Function FightAvailable(char)
 available=0
 If charFed(char)=<7 And charRole(char)=1 And charHealth(char)>50 And InjuryStatus(char)=0 And charVacant(char)=0 And charOpponent(char)=0 And charFought(char)=0
  available=1
 EndIf
 Return available
End Function

;SIMULATED WIN EFFECT
Function WinEffect(char,v)
 ;become champion
 If charFed(char)=charFed(v) And TournamentStatus(char)=0 And TournamentStatus(v)=0
  If TitleHolder(v,1) Then fedChampWorld(charFed(char))=char : WriteHistory(charFed(char),1)
  If TitleHolder(v,2) Then fedChampInter(charFed(char))=char : WriteHistory(charFed(char),2)
  If TitleHolder(v,3) 
   If charPartner(char)=0 Then charPartner(char)=AssignPartner(char,-1)  
   fedChampTag(charFed(char),1)=char
   fedChampTag(charFed(char),2)=charPartner(char)
   WriteHistory(charFed(char),3)
  EndIf
 EndIf
 ;boost status of winner
 charWins(char,charFed(char))=charWins(char,charFed(char))+1
 target=charPopularity(v)
 If target<charPopularity(char)+1 Then target=charPopularity(char)+1
 If charPopularity(char)=>90 Then charPopularity(char)=charPopularity(char)+Rnd(0,1) Else charPopularity(char)=charPopularity(char)+1;+PursueValue(charPopularity(char),target,Rnd(1,3))
 If charHappiness(char)=>90 Then charHappiness(char)=charHappiness(char)+Rnd(0,1) Else charHappiness(char)=charHappiness(char)+1;PursueValue(charHappiness(char),100,Rnd(1,5))
 If charPopularity(char)>charOldPopularity(char)
  fedLimit=fedPopularity(charFed(char))
  If fedLimit<60 Then fedLimit=60
  If charPopularity(char)>fedLimit Then charPopularity(char)=charPopularity(char)+1
  If charPopularity(char)>fedLimit+5 Then charPopularity(char)=fedPopularity(charFed(char))+5
  If charPopularity(char)<charOldPopularity(char) Then charPopularity(char)=charOldPopularity(char)
 EndIf 
 charHealth(char)=Rnd(charHealth(char)/2,charHealth(char))
 ;damage status of loser
 target=charPopularity(char)
 If target>charPopularity(v)-1 Then target=charPopularity(v)-1
 If charPopularity(v)>50 Then charPopularity(v)=charPopularity(v)-1;+PursueValue(charPopularity(v),target,Rnd(1,3))
 charHappiness(v)=charHappiness(v)-1;+PursueValue(charHappiness(v),30,Rnd(1,5))
 charHealth(v)=Rnd(0,charHealth(v)/2)
End Function 

;QUEUE REACTIONS TO TRADE
Function FindTradeReactions(char,promotion)
 For v=1 To no_chars
  If char<>v And charFed(v)=<7
   ;react to departures
   If charFed(char)=charFed(v) And promotion<>charFed(v)
    If charRealRelationship(v,char)>0 Then charTradeReaction(v)=-char
    If charRealRelationship(v,char)<0 Then charTradeReaction(v)=char
   EndIf
   ;react to arrivals
   If charFed(char)<>charFed(v) And promotion=charFed(v)
    If charRealRelationship(v,char)>0 Then charTradeReaction(v)=char
    If charRealRelationship(v,char)<0 Then charTradeReaction(v)=-char
   EndIf
  EndIf
 Next
End Function

;POST NEWS PROCESS
Function PostNewsProcess()
 ;find meetings
 Loader("Please Wait","Finding Meetings")
 RiskFormalMeetings()
 If gamComplete<>-1 And gamComplete<>1 
  RiskOffers()
  RiskCasualMeetings(1)
 EndIf
 ;save progress
 Loader("Please Wait","Saving Progress")
 For char=1 To no_chars
  charWorked(char)=0
 Next
 SaveUniverse()
 SaveProgress(slot)
 SaveWorld(slot)
 SaveChars(slot)
 ;proceed
 If screen<>25 And screen<>50
  gamScroll=-((GetMonth(gamDate)-1)*125)
  screen=20
  If gamComplete=-1 Or gamComplete=1 ;public confrontation
   eFed(cyc)=charFed(gamChar)
   ResetCharacters()
   pChar(1)=gamChar : pControl(1)=3 : pChar(2)=201 
   GetMatchRules(1) : AddGimmick(0)
   matchTimeLim=1
   If gamComplete=-1 Then matchPromo=51
   If gamComplete=1 Then matchPromo=52 : fedLocked(eFed(cyc))=0 : gamComplete=2
   screen=50 : screenAgenda=0
  EndIf
 EndIf
End Function