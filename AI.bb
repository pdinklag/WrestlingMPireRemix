;//////////////////////////////////////////////////////////////////////////////
;------------------ WRESTLING MPIRE 2008: ARTIFICIAL INTELLIGENCE -------------
;//////////////////////////////////////////////////////////////////////////////

;----------------------------------------------------------------
;///////////////////// GET INPUT ////////////////////////////////
;----------------------------------------------------------------
Function GetInput(cyc)
 ;reset commands
 If optOnline=0 Then ResetInput(cyc)
 If gotim>0 And pOutTim(cyc)>0 And pHidden(cyc)=0
  If pController(cyc)=netID Then netControl=cyc
  If optOnline=0 ;Or (NetPlayerLocal(netID) And pController(cyc)=netID)
   ;get keyboard input
   If pControl(cyc)=1 Or pControl(cyc)=3
    If KeyDown(200) Then cUp(cyc)=1
    If KeyDown(208) Then cDown(cyc)=1
    If KeyDown(203) Then cLeft(cyc)=1
    If KeyDown(205) Then cRight(cyc)=1
    If KeyDown(keyAttack) Then cAttack(cyc)=1
    If KeyDown(keyRun) Then cRun(cyc)=1
    If KeyDown(keyGrapple) Then cGrapple(cyc)=1
    If KeyDown(keyPickUp) Then cPickUp(cyc)=1
    If KeyDown(keySwitch) Then cSwitch(cyc)=1
    If KeyDown(keyTaunt) Then cTaunt(cyc)=1
    If cGrapple(cyc)=1 And (cAttack(cyc)=1 Or cPickUp(cyc)=1) Then cBlock(cyc)=1
   EndIf
   ;get gamepad input
   If pControl(cyc)=2 Or pControl(cyc)=3
    If JoyYDir(pJoystick(cyc))=-1 Then cUp(cyc)=1
    If JoyYDir(pJoystick(cyc))=1 Then cDown(cyc)=1
    If JoyXDir(pJoystick(cyc))=-1 Then cLeft(cyc)=1
    If JoyXDir(pJoystick(cyc))=1 Then cRight(cyc)=1
    If JoyDown(buttAttack,pJoystick(cyc)) Then cAttack(cyc)=1
    If JoyDown(buttRun,pJoystick(cyc)) Then cRun(cyc)=1
    If JoyDown(buttGrapple,pJoystick(cyc)) Then cGrapple(cyc)=1
    If JoyDown(buttPickUp,pJoystick(cyc)) Then cPickUp(cyc)=1
    If JoyDown(buttSwitch,pJoystick(cyc)) Then cSwitch(cyc)=1
    If JoyDown(buttTaunt,pJoystick(cyc)) Then cTaunt(cyc)=1
    If cGrapple(cyc)=1 And (cAttack(cyc)=1 Or cPickUp(cyc)=1) Then cBlock(cyc)=1
   EndIf
  EndIf
  ;chat input
  ;If optOnline>0 And NetPlayerLocal(netID) And pController(cyc)=netID
  ;  k=GetKey()
  ;  If k>0
  ;      If k=13
  ;          If netChat$<>"" SendNetMsg 10,netChat$,netID,0,0
  ;          netChat$=""
  ;      Else If k=8
  ;          If Len(netChat$)>0 Then netChat$=Left$(netChat$,Len(netChat$)-1)
  ;      Else If k>=32 And k<127
  ;          netChat$=netChat$+Chr$(k)
  ;      EndIf
  ;  EndIf
  ;  If k<>13 Then SendNetMsg 1,PackOnlineData$(cyc),netID,0,0
  ;EndIf
  ;get CPU input
  If pControl(cyc)=0
   AI(cyc)
  EndIf
 EndIf
End Function

;----------------------------------------------------------------
;/////////////// ARTIFICIAL INTELLIGENCE ////////////////////////
;----------------------------------------------------------------
Function AI(cyc)
 ;CHANGE FOCUS
 If SwitchViable(cyc) And matchState<>2
  If (pOutTim(cyc)>2 And matchLocation=0) Or matchState=>3
   ;random change
   randy=Rnd(0,500)
   If matchState=3 Or (matchState=2 And promoTim=0)
    If pRole(cyc)=<1 And LegalMan(pFoc(cyc))=0 And pChaosTim(pFoc(cyc))=0 Then randy=Rnd(0,100)
    If pFoc(cyc)=0 Then randy=Rnd(0,100)
   EndIf
   If (LegalMan(cyc) Or matchState=4) And matchState=>3 And pFoc(cyc)>0 And InProximity(cyc,pFoc(cyc),100)=0 Then randy=Rnd(0,100)
   If pTeam(cyc)=pTeam(pFoc(cyc)) And (pRole(cyc)<>2 Or pChaosTim(cyc)>0) Then randy=Rnd(0,50)
   If TryingToTag(cyc) Then randy=Rnd(0,10000)
   If randy=<1 Then GetNewFoc(cyc)
   ;enemy attraction
   For v=1 To no_plays
    If cyc<>v And pFoc(cyc)<>v And pTeam(cyc)<>pTeam(v) And pRole(v)<>1 And InProximity(cyc,v,40) And pY#(cyc)=>pY#(v)-10 And pY#(cyc)=<pY#(v)+10 And AttackViable(v)>0 And pOutTim(v)>0 And pHidden(v)=0
     randy=Rnd(0,200)
     If pFoc(v)=cyc And LegalMan(cyc) And LegalMan(v) Then randy=Rnd(0,50)
     If pRole(cyc)=1 And InsideRing(pX#(v),pZ#(v),0) And RingViable(v)=0 Then randy=Rnd(0,100)
     If charRelationship(pChar(cyc),pChar(v))>0 Then randy=Rnd(0,1000)
     If LegalMan(cyc) And LegalMan(v)=0 Then randy=Rnd(0,1000)
     If (matchRules=>1 Or matchCountOuts>0) And InsideRing(pX#(v),pZ#(v),0)=0 Then randy=Rnd(0,1000)
     If TryingToTag(cyc) Then randy=Rnd(0,1000)
     If matchState<>3 Then randy=Rnd(0,1000)
     If randy=0 Or (randy=<5 And pFoc(cyc)=0) Then pFoc(cyc)=v : pAgenda(cyc)=1
    EndIf
    If pAngerTim(cyc,v)>0 Then pFoc(cyc)=v : pAgenda(cyc)=1
   Next
  EndIf
  ;forced to focus on entrant
  If matchState=1 And matchEnter>0 And pTeam(cyc)<>pTeam(matchEnter) Then pFoc(cyc)=matchEnter
  ;If matchState=1 And pRole(cyc)=2 Then pFoc(cyc)=pClient(cyc)
 EndIf
 ;manage anger
 For v=1 To no_plays
  pAngerTim(cyc,v)=pAngerTim(cyc,v)-1
  If pRole(cyc)=1 Or pRole(v)=1 Then pAngerTim(cyc,v)=pAngerTim(cyc,v)-1
  If pAngerTim(cyc,v)<0 Then pAngerTim(cyc,v)=0
 Next
 ;GET AGENDA
 ;new agenda
 pRelocate(cyc)=pRelocate(cyc)-1
 If pRelocate(cyc)<0 Then pRelocate(cyc)=0
 If pRelocate(cyc)=0
  randy=Rnd(0,500)
  If matchState<>3 Or TryingToTag(cyc) Then randy=Rnd(0,1000)
  If LegalMan(cyc)=0 And pRole(cyc)<>1 Then randy=Rnd(0,2500)
  If matchState=2 Or TagStatic(cyc) Then randy=Rnd(0,10000)
  If randy=0 Then pAgenda(cyc)=0 : pSubX#(cyc)=9999 : pSubZ#(cyc)=9999
  If randy=1 And pFoc(cyc)>0 Then pAgenda(cyc)=1
  If randy=<1 And pAgenda(cyc)=1 Then pSubX#(cyc)=9999 : pSubZ#(cyc)=9999
  If randy=2
   If no_weaps>0 And pHolding(cyc)=0 And pCarrying(cyc)=0 And pRole(cyc)<>1 And matchRules=<1 And matchCountOuts=<2 And matchCage=0 And pScar(cyc,21)=<4
    pAgenda(cyc)=21 : pWeapFoc(cyc)=0
   EndIf
  EndIf
  ;agenda filter
  If matchState=1 And pRole(cyc)=2 Then pAgenda(cyc)=1
  If matchState=<2 Or pOutTim(cyc)<2
   If matchLocation>0 Then pAgenda(cyc)=1 Else pAgenda(cyc)=0
  EndIf
  If matchState=3
   If pFoc(cyc)>0 And LegalMan(cyc) And LegalMan(pFoc(cyc)) And pAgenda(cyc)=0
    If pSpecial(cyc)>0 Or pDizzyTim(pFoc(cyc))>0 Or pBlindTim(pFoc(cyc))>0 Or pAnim(pFoc(cyc))=701 Then pAgenda(cyc)=1
   EndIf
   If RingViable(cyc)=0 And (pAgenda(cyc)<11 Or pAgenda(cyc)>14)
    If InsideRing(pX#(cyc),pZ#(cyc),-15) Or InsideRing(pTX#(cyc),pTZ#(cyc),-15) Then pAgenda(cyc)=0
   EndIf
   If RushViable(cyc) Then pAgenda(cyc)=0
   randy=Rnd(0,500)
   If randy=0 And (matchRules=>1 Or matchCountOuts>0) And LegalMan(cyc)
    If InsideRing(pTX#(cyc),pTZ#(cyc),-15)=0 Then pAgenda(cyc)=0
   EndIf
   If pRole(cyc)=3 And pFoc(cyc)>0 Then pAgenda(cyc)=1
  EndIf
  If matchState=4
   If matchTim>500 And pRole(cyc)=0 And pAgenda(cyc)=1 Then pAgenda(cyc)=0
   If pRole(cyc)=2 And pFoc(cyc)=pClient(cyc) Then pAgenda(cyc)=1
  EndIf
  If pDizzyTim(cyc)>0 Or pBlindTim(cyc)>0 Then pAgenda(cyc)=0
 EndIf
 ;refereeing
 If pRole(cyc)=1 And pRelocate(cyc)=0
  oldDuty=0 : pOldFoc(cyc)=pFoc(cyc)
  For v=1 To no_plays
   newDuty=RefViable(v)
   If newDuty=21
    If pAgenda(cyc)=1 And LegalMan(pFoc(cyc)) Then newDuty=0 Else pAngerTim(cyc,v)=25
   EndIf
   If newDuty=22 Then pAngerTim(cyc,v)=25
   If newDuty>0 And DutyPriority(newDuty)>DutyPriority(oldDuty) And pChecked(v)=0
    If matchCounter=0 Or matchCounter=cyc Or (newDuty<>5 And newDuty<>15)
     If SwitchViable(cyc) Then pFoc(cyc)=v
     If newDuty<>5 And newDuty<>15 Then pAgenda(cyc)=1
    EndIf
    oldDuty=newDuty
   Else
    If LegalMan(v) And (InProximity(cyc,v,30) Or ReachedCord(pTX#(cyc),pTZ#(cyc),pX#(v),pZ#(v),30))
     duties=0
     For count=1 To no_plays
      If RefViable(count)>0 Then duties=duties+1
     Next
     If duties=0 Then pNowhere(cyc)=99
    EndIf
   EndIf
  Next
  If pFoc(cyc)<>pOldFoc(cyc)
   If (RefViable(pFoc(cyc))=>1 And RefViable(pFoc(cyc))=<4) Or (RefViable(pFoc(cyc))=>11 And RefViable(pFoc(cyc))=<14) Then pCountTim(cyc)=0
  EndIf
 EndIf
 ;keep managers away from brawlers
 randy=Rnd(0,500)
 If randy=0 And pRole(cyc)=2 And matchRules=0 And InsideRing(pX#(cyc),pZ#(cyc),-5)=0 And pRelocate(cyc)=0
  For v=1 To no_wrestlers
   If LegalMan(v) And (InProximity(cyc,v,30) Or ReachedCord(pTX#(cyc),pTZ#(cyc),pX#(v),pZ#(v),30)) Then pNowhere(cyc)=99
  Next
 EndIf
 ;pin attraction
 If matchState=3 And matchPins>0 And LegalMan(cyc) And SwitchViable(cyc)
  For v=1 To no_wrestlers
   If cyc<>v And pTeam(cyc)<>pTeam(v) And InProximity(cyc,v,50) And LegalMan(v) And FallsCount(v) And AttackViable(v)=>3 And pDT(v)>100 And pPinning(v)=0 And pPinner(v)=0
    chance=500-pDT(v)
    If FindInjury(v)>1 Or pHealth(v)<100 Then chance=chance/2
    If matchTimeLim>0 And matchMins=>matchTimeLim-1 And matchTeams=>0 Then chance=chance/2
    If chance<5 Then chance=5
    randy=Rnd(0,chance)
    If matchRules=>1 And TouchingRopes(v) Then randy=99
    If randy=<5
     pAgenda(cyc)=1 : pFoc(cyc)=v
     If InProximity(cyc,v,20) Or GetDistance#(pX#(cyc),pZ#(cyc),pLimbX#(v,1),pLimbZ#(v,1))<15 Then cTaunt(cyc)=1
    EndIf
   EndIf
  Next
 EndIf
 ;tag team issues
 If matchState=3 And matchTeams=2 And pRole(cyc)=0 And LegalMan(cyc)=0 And pEliminated(cyc)=0
  If pChaosTim(cyc)=0 And pPlatform(cyc)=<4 And (pAgenda(cyc)<11 Or pAgenda(cyc)>14) ;get out
   Repeat
    randy=Rnd(0,1)
    If randy=0 Then pAgenda(cyc)=Rnd(12,13) Else pAgenda(cyc)=Rnd(11,14)
   Until FindTagConflict(cyc,pAgenda(cyc))=0
  EndIf
  randy=Rnd(0,100)
  If randy=0 And pChaosTim(cyc)>0 And pAgenda(cyc)=>11 And pAgenda(cyc)=<14 Then pAgenda(cyc)=1 ;get in
 EndIf
 ;consider tagging
 If matchState=3 And matchTeams=2 And LegalMan(cyc) And teamRemaining(pTeam(cyc))>1
  For v=1 To no_wrestlers
   If cyc<>v And pTeam(v)=pTeam(cyc) And pEliminated(v)=0 And TagProximity(v,80) And InsideRing(pX#(cyc),pZ#(cyc),-15) And Isolated(cyc,15)
    If optLength=1 Then chance=(pHealth(cyc)*2)+2000
    If optLength=2 Then chance=pHealth(cyc)+2000
    If optLength=3 Then chance=(pHealth(cyc)/2)+2000
    If pHealth(v)<pHealth(cyc) Or FindInjury(v)>0 Or pSpecial(cyc)>0 Then chance=chance*2
    If pAnim(v)=>190 And pAnim(v)=<193 And pControl(v)>0 Then chance=chance/10
    If pSpecial(v)>0 Then chance=chance/10
    chance=chance*(teamRemaining(pTeam(cyc))-1)
    randy=Rnd(0,chance)
    If randy=<20 And pAnim(cyc)<>177 Then pFoc(cyc)=v : pAgenda(cyc)=1
   EndIf
  Next
 EndIf
 ;pursue outside if caged
 If matchState=3 And matchCage>0 And matchCountOuts=3 And LegalMan(cyc)
  If InsideRing(pX#(cyc),pZ#(cyc),0) And pPlatform(cyc)=<4
   If pFoc(cyc)>0 And AttackViable(pFoc(cyc))=>2
    randy=Rnd(0,500-pDT(pFoc(cyc)))
    If randy=<5 Then pAgenda(cyc)=15
   EndIf
   If pFoc(cyc)=0
    randy=Rnd(0,250)
    If randy=<5 Then pAgenda(cyc)=15
   EndIf
  EndIf
  If pPlatform(cyc)=>91 And pPlatform(cyc)=<98 Then pAgenda(cyc)=15
 EndIf
 ;item attraction
 If pAnim(cyc)<40 And pPlatform(cyc)=0 And pCarrying(cyc)=0 And pScar(cyc,21)=<4 And pAgenda(cyc)<>20
  For v=1 To no_items
   If ItemProximity(cyc,v,50) And pY#(cyc)=>iY#(v)-5 And pY#(cyc)=<iY#(v)+5 And iCarrier(v)=0 And iCarryAnim(iType(v),iState(v))=>0 And iAnim(v)=0
    chance=600
    If iState(v)=1 Then chance=chance*2
    If pAgenda(cyc)=21 Then chance=chance*2
    If Isolated(cyc,30) Then chance=chance/2
    If pRole(cyc)=1 And InsideRing(iX#(v),iZ#(v),0)=0 Then chance=chance*5
    randy=Rnd(0,chance)
    If pRole(cyc)=1 And itemLayout=5 And iType(v)=itemSelection-1 Then randy=99
    If iState(v)=0 And pNowhere(cyc)>0 And pNowhere(cyc)<50 Then randy=Rnd(0,20)
    If randy=0 Then pItemFoc(cyc)=v : pAgenda(cyc)=20 : pNowhere(cyc)=0
   EndIf
  Next
 EndIf
 ;weapon attraction
 If pAnim(cyc)<40 And pHolding(cyc)=0 And pCarrying(cyc)=0 And pScar(cyc,21)=<4 And pAgenda(cyc)<>21
  For v=1 To no_weaps
   If WeaponProximity(cyc,v,30) And weapY#(v)=>pY#(cyc)-5 And weapY#(v)=<pY#(cyc)+25
    If (weapCarrier(v)=0 And pWeaponAccess(cyc,v)=0) Or (weapCarrier(v)>0 And (AttackSensible(cyc,weapCarrier(v)) Or pRole(cyc)=1) And matchState=>3)
     randy=Rnd(0,250)
     If Isolated(cyc,30) Or (weapCarrier(v)>0 And weapCarrier(v)=pFoc(cyc)) Then randy=Rnd(0,50)
     If matchState=3 And matchRules=>2 And LegalMan(cyc) And InsideRing(weapX#(v),weapZ#(v),0) Then randy=Rnd(0,1000)
     If pRole(cyc)=1
      If InsideRing(weapX#(v),weapZ#(v),0)=0 Or matchRules=0 Then randy=Rnd(0,1000)
      If weapLayout=5 And weapType(v)=weapSelection-1 Then randy=99
     EndIf
     If randy=0 Then pWeapFoc(cyc)=v : pAgenda(cyc)=21
    EndIf
   EndIf
   If matchState=4 And weapType(v)=FindReward(cyc)
    If weapCarrier(v)=0 Or pTeam(weapCarrier(v))<>pTeam(cyc) Then pWeapFoc(cyc)=v : pAgenda(cyc)=21
   EndIf
  Next
 EndIf
 ;find win threats
 If matchState=3 And pRole(cyc)<>1 And SwitchViable(cyc)
  For v=1 To no_plays
   threat=0
   If cyc<>v And pTeam(cyc)<>pTeam(v)
    If LegalMan(v)
     If matchPins>0 And pPinning(v)>0 And pPinCount(pPinning(v))>0 And LegalMan(pPinning(v))
      If pTeam(pPinning(v))=pTeam(cyc) Or matchType<>5 Then threat=1 ;pin threat
     EndIf
     If matchSubs>0 And pGrappling(v)>0 And pStretched(pGrappling(v))>0 And LegalMan(pGrappling(v))
      If pTeam(pGrappling(v))=pTeam(cyc) Or matchType<>5 Then threat=1 ;submission threat
     EndIf
     If matchCage>0 And matchCountOuts=3 And pPlatform(v)=>91 And pPlatform(v)=<98
      If pPlatform(cyc)<91 Or pPlatform(cyc)>98 Then threat=1 ;cage escape threat
     EndIf
    EndIf
    If LegalMan(cyc)=0 And LegalMan(v)=0 And pFoc(v)=teamLegal(pTeam(cyc)) And InProximity(v,teamLegal(pTeam(cyc)),100) And InProximity(cyc,v,100)
     legal=teamLegal(pTeam(cyc))
     If matchPins>0 And pPinning(legal)>0 And pPinCount(pPinning(legal))>0 And LegalMan(pPinning(legal)) Then threat=1 ;protect pinner
     If matchSubs>0 And pGrappling(legal)>0 And pStretched(pGrappling(legal))>0 And LegalMan(pGrappling(legal)) Then threat=1 ;protect grappler
    EndIf
   EndIf
   randy=0
   If matchTeams=2 And LegalMan(cyc)=0 And pRole(cyc)=0 And pEliminated(cyc)=0 Then randy=Rnd(0,teamRemaining(pTeam(cyc))*10)
   If pRole(cyc)=2 And InsideRing(pX#(cyc),pZ#(cyc),-5)=0 And InsideRing(pX#(v),pZ#(v),-15) Then randy=Rnd(0,100)
   If pEliminated(cyc) And (pZ#(cyc)>385 Or pZ#(cyc)<-385) Then randy=Rnd(0,100)
   If threat>0 And randy=0
    pFoc(cyc)=v : pAgenda(cyc)=1
    If InProximity(cyc,v,30)=0 Then pRunTim(cyc)=GetDistance#(pX#(cyc),pZ#(cyc),pX#(v),pZ#(v))
    If pNowhere(cyc)=20 Then cAttack(cyc)=1 : cRun(cyc)=0 : cGrapple(cyc)=0 : cPickUp(cyc)=0
   EndIf
  Next
 EndIf
 ;rethink if getting nowhere
 pNowhere(cyc)=pNowhere(cyc)-1
 If pNowhere(cyc)<0 Then pNowhere(cyc)=0
 If pNowhere(cyc)>50 And pRelocate(cyc)=0
  If pPlatform(cyc)=>5
   its=0
   Repeat
    tA#=Rnd(0,360) : satisfied=1
    If ItemClear(cyc,tA#,20)=0 And its<180 Then satisfied=0
    If FlightClear(cyc,tA#,20)=0 And its<720 Then satisfied=0
   Until satisfied=1
   PositionEntity dummy,pX#(cyc),pY#(cyc),pZ#(cyc)
   RotateEntity dummy,0,tA#,0
   MoveEntity dummy,0,0,50
   pSubX#(cyc)=EntityX(dummy) : pSubZ#(cyc)=EntityZ(dummy)
  Else
   its=0
   Repeat
    satisfied=1 : its=its+1
    pSubX#(cyc)=pX#(cyc)+Rnd(-100,100)
    pSubZ#(cyc)=pZ#(cyc)+Rnd(-100,100)
    If pSubX#(cyc)>pX#(cyc)-30 And pSubX#(cyc)<pX#(cyc)+30 And pSubZ#(cyc)>pZ#(cyc)-30 And pSubZ#(cyc)<pZ#(cyc)+30 Then satisfied=0
    If matchState=1 And (pSubZ#(cyc)>385 Or pSubZ#(cyc)<-385) Then satisfied=0
    If InsideRing(pX#(cyc),pZ#(cyc),-15) And InsideRing(pSubX#(cyc),pSubZ#(cyc),-30)=0 Then satisfied=0
    If InsideRing(pX#(cyc),pZ#(cyc),-5)=0 And InsideRing(pSubX#(cyc),pSubZ#(cyc),0) Then satisfied=0
    If BehindRailings(pX#(cyc),pZ#(cyc))=0 And BehindRailings(pSubX#(cyc),pSubZ#(cyc)) Then satisfied=0
    If BlockConflict(pSubX#(cyc),FindGround#(pSubX#(cyc),pSubZ#(cyc)),pSubZ#(cyc),0) Then satisfied=0
    If satisfied=1
     PositionEntity dummy,pSubX#(cyc),pY#(cyc),pSubZ#(cyc)
     PointEntity p(cyc),dummy
     tA#=EntityYaw(p(cyc))
     RotateEntity p(cyc),0,pA#(cyc),0
     If ItemClear(cyc,tA#,10)=0 Then satisfied=0
    EndIf
    If RushViable(cyc) Then pSubX#(cyc)=Rnd(-50,50) : pSubZ#(cyc)=Rnd(-50,50) : satisfied=1
   Until satisfied=1 Or its>1000
  EndIf
  If pAgenda(cyc)=20 Or pAgenda(cyc)=21 Then cPickUp(cyc)=1
  pAgenda(cyc)=0
  pNowhere(cyc)=0
  pRelocate(cyc)=50
 EndIf
 ;HONOUR AGENDA
 ;general exploration
 If pAgenda(cyc)=0
  randy=Rnd(0,250)
  If matchState<>3 Or (matchState=3 And pRole(cyc)>0) Then randy=Rnd(0,1000)
  ;If matchState=3 And matchRules=0 And pRole(cyc)=2 Then randy=Rnd(0,5000)
  If matchState=2 Or TagStatic(cyc) Then randy=Rnd(0,10000)
  If randy=0 Or pAgenda(cyc)<>pOldAgenda(cyc)
   If matchState=3 And matchRules=0 And matchCountOuts=0
    sourceX#=pX#(cyc) : sourceZ#=pZ#(cyc)
    If pRole(cyc)=1
     IdentifyCentres(0)
     sourceX#=centreX# : sourceZ#=centreZ#
    EndIf
    If pRole(cyc)=2 Then sourceX#=pX#(pClient(cyc)) : sourceZ#=pZ#(pClient(cyc))
    its=0
    Repeat
     satisfied=1 : its=its+1
     pTX#(cyc)=sourceX#+Rnd(-100,100)
     pTZ#(cyc)=sourceZ#+Rnd(-100,100)
     If InsideRing(pX#(cyc),pZ#(cyc),-15) And InsideRing(pTX#(cyc),pTZ#(cyc),0) And InsideRing(pTX#(cyc),pTZ#(cyc),-30)=0 Then satisfied=0
     If InsideRing(pX#(cyc),pZ#(cyc),-5)=0 And InsideRing(pTX#(cyc),pTZ#(cyc),0) And InsideRing(pTX#(cyc),pTZ#(cyc),-30)=0 Then satisfied=0
     If BehindRailings(pX#(cyc),pZ#(cyc))=0 And BehindRailings(pTX#(cyc),pTZ#(cyc)) Then satisfied=0
     If BlockConflict(pTX#(cyc),FindGround#(pTX#(cyc),pTZ#(cyc)),pTZ#(cyc),0)>0 Then satisfied=0
    Until satisfied=1 Or its>1000
    explore=Rnd(0,50)
    If explore=<5 And matchState=3 And pRole(cyc)<>1 And pRole(cyc)<>3 Then ExploreArea(explore)
   Else
    pTX#(cyc)=Rnd(-60,60) : pTZ#(cyc)=Rnd(-60,60)
    If matchState=<2 And matchTeams>0
     If pTeam(cyc)=1 Then pTX#(cyc)=Rnd(-60,0) : pTZ#(cyc)=Rnd(-60,0)
     If pTeam(cyc)=2 Then pTX#(cyc)=Rnd(0,60) : pTZ#(cyc)=Rnd(0,60)
    EndIf
    If RingViable(cyc)=0
     Repeat
      pTX#(cyc)=Rnd(-130,130) : pTZ#(cyc)=Rnd(-130,130)
      If matchTeams>0 And pTeam(cyc)=1 Then pTX#(cyc)=Rnd(-130,60) : pTZ#(cyc)=Rnd(-60,130)
      If matchTeams>0 And pTeam(cyc)=2 Then pTX#(cyc)=Rnd(-60,130) : pTZ#(cyc)=Rnd(-130,60)
     Until InsideRing(pTX#(cyc),pTZ#(cyc),0)=0
    EndIf
    explore=Rnd(0,100)
    If explore=<5 And matchState=3 And pRole(cyc)<>1 And pRole(cyc)<>3 Then ExploreArea(explore)
   EndIf
   If matchState=4 Or pEliminated(cyc)
    If matchTeams>0
     If pTeam(cyc)=1 Then pTX#(cyc)=Rnd(-250,250) : pTZ#(cyc)=Rnd(610,890)
     If pTeam(cyc)=2 Then pTX#(cyc)=Rnd(-150,150) : pTZ#(cyc)=Rnd(-800,-610)
    Else
     If pZ#(cyc)=>0 Then pTX#(cyc)=Rnd(-250,250) : pTZ#(cyc)=Rnd(610,890)
     If pZ#(cyc)<0 Then pTX#(cyc)=Rnd(-150,150) : pTZ#(cyc)=Rnd(-800,-610)
    EndIf
   EndIf
  EndIf
  If matchState=1 And pOutTim(cyc)=2 And (pZ#(cyc)>140 Or pZ#(cyc)<-140)
   If pTX#(cyc)<-17 Then pTX#(cyc)=-17
   If pTX#(cyc)>17 Then pTX#(cyc)=17
   If (pZ#(cyc)>300 Or pZ#(cyc)<-300) Then pTX#(cyc)=0
  EndIf
  If randy=1 And (pDizzyTim(cyc)>0 Or pBlindTim(cyc)>0) Then pTX#(cyc)=pX#(cyc) : pTZ#(cyc)=pZ#(cyc)
  If RushViable(cyc) Or (matchState=3 And matchCountOuts=3 And matchCage=0 And LegalMan(cyc)) Or pOutTim(cyc)<2 Or (matchState=<2 And pRole(cyc)=1)
   While InsideRing(pTX#(cyc),pTZ#(cyc),-25)=0 And RingViable(cyc)
    pTX#(cyc)=Rnd(-60,60) : pTZ#(cyc)=Rnd(-60,60)
   Wend
  EndIf
  If RingViable(cyc)=0
   While InsideRing(pTX#(cyc),pTZ#(cyc),0)
    pTX#(cyc)=Rnd(-130,130) : pTZ#(cyc)=Rnd(-130,130)
   Wend
  EndIf
  pFollow(cyc)=5
 EndIf
 ;following
 v=pFoc(cyc)
 If pAgenda(cyc)=1 And v>0
  pTX#(cyc)=pX#(v) : pTZ#(cyc)=pZ#(v)
  pFollow(cyc)=15
  If pRole(cyc)=1
   pFollow(cyc)=50
   If RefViable(v)>0 Then pFollow(cyc)=20
  EndIf
  If pRole(cyc)=2 And v=pClient(cyc) Then pFollow(cyc)=30
  If pAngerTim(cyc,v)>0 Then pFollow(cyc)=15
  If InsideRing(pX#(v),pZ#(v),-15) And InsideRing(pX#(cyc),pZ#(cyc),0)=0 Then pFollow(cyc)=1
  If pPlatform(v)=>91 And pPlatform(v)=<98 And pPlatform(cyc)=0 Then pFollow(cyc)=1
  If TryingToTag(cyc) Then pFollow(cyc)=1
  If matchState=3 And matchCountOuts=3 And matchCage=0 And LegalMan(cyc) And InsideRing(pX#(v),pZ#(v),0)=0 Then pAgenda(cyc)=0
 EndIf
 ;pursue apron position A (far left)
 If pAgenda(cyc)=11
  If pTeam(cyc)=1 Then pTX#(cyc)=blockX1#(0)+10 : pTZ#(cyc)=blockZ1#(0)+55
  If pTeam(cyc)=2 Then pTX#(cyc)=blockX2#(0)-55 : pTZ#(cyc)=blockZ2#(0)-10
  pFollow(cyc)=5
  If FindTagConflict(cyc,pAgenda(cyc)) Then pNowhere(cyc)=99
 EndIf
 ;pursue apron position B (close left)
 If pAgenda(cyc)=12
  If pTeam(cyc)=1 Then pTX#(cyc)=blockX1#(0)+10 : pTZ#(cyc)=blockZ1#(0)+35
  If pTeam(cyc)=2 Then pTX#(cyc)=blockX2#(0)-35 : pTZ#(cyc)=blockZ2#(0)-10
  pFollow(cyc)=5
  If FindTagConflict(cyc,pAgenda(cyc)) Then pNowhere(cyc)=99
 EndIf
 ;pursue apron position C (close right)
 If pAgenda(cyc)=13
  If pTeam(cyc)=1 Then pTX#(cyc)=blockX1#(0)+35 : pTZ#(cyc)=blockZ1#(0)+10
  If pTeam(cyc)=2 Then pTX#(cyc)=blockX2#(0)-10 : pTZ#(cyc)=blockZ2#(0)-35
  pFollow(cyc)=5
  If FindTagConflict(cyc,pAgenda(cyc)) Then pNowhere(cyc)=99
 EndIf
 ;pursue apron position D (far right)
 If pAgenda(cyc)=14
  If pTeam(cyc)=1 Then pTX#(cyc)=blockX1#(0)+55 : pTZ#(cyc)=blockZ1#(0)+10
  If pTeam(cyc)=2 Then pTX#(cyc)=blockX2#(0)-10 : pTZ#(cyc)=blockZ2#(0)-55
  pFollow(cyc)=5
  If FindTagConflict(cyc,pAgenda(cyc)) Then pNowhere(cyc)=99
 EndIf
 ;escape from cage
 If pAgenda(cyc)=15
  While InsideRing(pTX#(cyc),pTZ#(cyc),0)
   pTX#(cyc)=Rnd(-130,130) : pTZ#(cyc)=Rnd(-130,130)
  Wend
  pFollow(cyc)=5
  If InsideRing(pTX#(cyc),pTZ#(cyc),0)=0 And pY#(cyc)=wGround# Then pAgenda(cyc)=0
 EndIf
 ;follow item
 If pAgenda(cyc)=20 And no_items>0
  If pItemFoc(cyc)=0
   Repeat
    pItemFoc(cyc)=Rnd(0,no_items)
   Until ItemProximity(cyc,pItemFoc(cyc),200) Or pItemFoc(cyc)=0
  EndIf
  If pCarrying(cyc)>0 Or iCarrier(pItemFoc(cyc))>0 Or ItemProximity(cyc,pItemFoc(cyc),200)=0 Then pItemFoc(cyc)=0
  If matchState=3 And matchCountOuts=3 And matchCage=0 And LegalMan(cyc) And InsideRing(iX#(pItemFoc(cyc)),iZ#(pItemFoc(cyc)),0)=0 Then pItemFoc(cyc)=0
  If pItemFoc(cyc)>0
   pTX#(cyc)=iX#(pItemFoc(cyc)) : pTZ#(cyc)=iZ#(pItemFoc(cyc))
   pFollow(cyc)=5
   If pFoc(cyc)>0 And ItemProximity(cyc,pItemFoc(cyc),100)=0 And Isolated(cyc,50) Then pFoc(cyc)=0
  Else
   pAgenda(cyc)=0
  EndIf
 EndIf
 ;follow weapon
 If pAgenda(cyc)=21 And no_weaps>0
  If pWeapFoc(cyc)=0
   Repeat
    pWeapFoc(cyc)=Rnd(0,no_weaps)
   Until WeaponProximity(cyc,pWeapFoc(cyc),200) Or pWeapFoc(cyc)=0
  EndIf
  If pHolding(cyc)>0 Then pWeapFoc(cyc)=0
  If weapCarrier(pWeapFoc(cyc))>0 And AttackSensible(cyc,weapCarrier(pWeapFoc(cyc)))=0 Then pWeapFoc(cyc)=0
  If matchState=3 And matchCountOuts=3 And matchCage=0 And LegalMan(cyc) And InsideRing(weapX#(pWeapFoc(cyc)),weapZ#(pWeapFoc(cyc)),0)=0 Then pWeapFoc(cyc)=0
  If pWeapFoc(cyc)>0
   pTX#(cyc)=weapX#(pWeapFoc(cyc)) : pTZ#(cyc)=weapZ#(pWeapFoc(cyc))
   pFollow(cyc)=5
   If pFoc(cyc)>0 And WeaponProximity(cyc,pWeapFoc(cyc),100)=0 And Isolated(cyc,50) Then pFoc(cyc)=0
  Else
   If matchState=3 And pFoc(cyc)>0 Then pAgenda(cyc)=1 Else pAgenda(cyc)=0
  EndIf
 EndIf
 ;head for ropes in holds
 If pStretched(cyc)>0 And InsideRing(RealX#(cyc),RealZ#(cyc),-15) And TouchingRopes(cyc)=0
  If NearestCorner(RealX#(cyc),RealZ#(cyc))=1 Then pTX#(cyc)=blockX2#(4) : pTZ#(cyc)=blockZ1#(1)
  If NearestCorner(RealX#(cyc),RealZ#(cyc))=2 Then pTX#(cyc)=blockX1#(2) : pTZ#(cyc)=blockZ1#(1)
  If NearestCorner(RealX#(cyc),RealZ#(cyc))=3 Then pTX#(cyc)=blockX1#(2) : pTZ#(cyc)=blockZ2#(3)
  If NearestCorner(RealX#(cyc),RealZ#(cyc))=4 Then pTX#(cyc)=blockX2#(4) : pTZ#(cyc)=blockZ2#(3)
  pFollow(cyc)=1
 EndIf
 ;don't let refs climb cage
 If matchState=3 And matchCage>0 And matchCountOuts>0 And pRole(cyc)=1
  If pTX#(cyc)>80 Then pTX#(cyc)=80
  If pTX#(cyc)<-80 Then pTX#(cyc)=-80
  If pTZ#(cyc)>80 Then pTZ#(cyc)=80
  If pTZ#(cyc)<-80 Then pTZ#(cyc)=-80
 EndIf
 ;make refs carry items out
 If matchState=3 And matchRules=>1 And pRole(cyc)=1 And pCarrying(cyc)>0 And InsideRing(pX#(cyc),pZ#(cyc),-15)
  If NearestSide(RealX#(cyc),RealZ#(cyc))=1 Then pTZ#(cyc)=80
  If NearestSide(RealX#(cyc),RealZ#(cyc))=2 Then pTX#(cyc)=80
  If NearestSide(RealX#(cyc),RealZ#(cyc))=3 Then pTZ#(cyc)=-80
  If NearestSide(RealX#(cyc),RealZ#(cyc))=4 Then pTX#(cyc)=-80
  pFollow(cyc)=5
 EndIf
 ;stand still prior to entrances
 If matchState=0 Then pTX#(cyc)=pX#(cyc) : pTZ#(cyc)=pZ#(cyc)
 ;clock last agenda
 pOldAgenda(cyc)=pAgenda(cyc)
 ;avoid inaccessible co-ord's
 If InsideRing(pTX#(cyc),pTZ#(cyc),0)
  If pTX#(cyc)>blockX1#(2) And pTX#(cyc)<>80 Then testTX#=pTX#(cyc) : pTX#(cyc)=80
  If pTX#(cyc)<blockX2#(4) And pTX#(cyc)<>-80 Then testTX#=pTX#(cyc) : pTX#(cyc)=-80
  If pTZ#(cyc)>blockZ1#(1) And pTZ#(cyc)<>80 Then testTZ#=pTZ#(cyc) : pTZ#(cyc)=80
  If pTZ#(cyc)<blockZ2#(3) And pTZ#(cyc)<>-80 Then testTZ#=pTZ#(cyc) : pTZ#(cyc)=-80
 EndIf
 If InsideRing(pSubX#(cyc),pSubZ#(cyc),0)
  If pSubX#(cyc)>blockX1#(2) And pSubX#(cyc)<>9999 And pSubX#(cyc)<>80 Then testSubX#=pSubX#(cyc) : pSubX#(cyc)=80
  If pSubX#(cyc)<blockX2#(4) And pSubX#(cyc)<>9999 And pSubX#(cyc)<>-80 Then testSubX#=pSubX#(cyc) : pSubX#(cyc)=-80
  If pSubZ#(cyc)>blockZ1#(1) And pSubZ#(cyc)<>9999 And pSubZ#(cyc)<>80 Then testSubZ#=pSubZ#(cyc) : pSubZ#(cyc)=80
  If pSubZ#(cyc)<blockZ2#(3) And pSubZ#(cyc)<>9999 And pSubZ#(cyc)<>-80 Then testSubZ#=pSubZ#(cyc) : pSubZ#(cyc)=-80
 EndIf
 ;CONSIDER SUB-ROUTES
 ;reset once satisified
 satisfiedSubX=0 : satisfiedSubZ=0
 If Reached(pX#(cyc),pSubX#(cyc),5) Then pSubX#(cyc)=9999
 If Reached(pZ#(cyc),pSubZ#(cyc),5) Then pSubZ#(cyc)=9999
 ;get through curtains
 If pX#(cyc)<-5 Or pX#(cyc)>5
  If (pTZ#(cyc)>395 And pZ#(cyc)<395) Or (pTZ#(cyc)<395 And pZ#(cyc)>395) Or (pTZ#(cyc)<-395 And pZ#(cyc)>-395) Or (pTZ#(cyc)>-395 And pZ#(cyc)<-395)
   If pTX#(cyc)<-5 And pSubX#(cyc)=9999 Then pSubX#(cyc)=-5
   If pTX#(cyc)>5 And pSubX#(cyc)=9999 Then pSubX#(cyc)=5
  EndIf
 EndIf
 ;leave aisles
 If pX#(cyc)>-25 And pX#(cyc)<25
  If (pTZ#(cyc)<140 And pZ#(cyc)>140) Or (pTZ#(cyc)>-140 And pZ#(cyc)<-140)
   If pTX#(cyc)<-15 And pSubX#(cyc)=9999 Then pSubX#(cyc)=-15
   If pTX#(cyc)>15 And pSubX#(cyc)=9999 Then pSubX#(cyc)=15
  EndIf
 EndIf
 ;enter north aisle
 If pTZ#(cyc)>140 And pZ#(cyc)<140 And (pX#(cyc)<-15 Or pX#(cyc)>15)
  If (pTX#(cyc)>-25 And pTX#(cyc)<-25) Or pTZ#(cyc)>610
   If pX#(cyc)<-15 And pSubX#(cyc)=9999 Then pSubX#(cyc)=-15
   If pX#(cyc)>15 And pSubX#(cyc)=9999 Then pSubX#(cyc)=15
   If pSubZ#(cyc)=9999 Then pSubZ#(cyc)=140
  EndIf
 EndIf
 ;enter south aisle
 If pTZ#(cyc)<-140 And pZ#(cyc)>-140 And (pX#(cyc)<-15 Or pX#(cyc)>15)
  If (pTX#(cyc)>-25 And pTX#(cyc)<-25) Or pTZ#(cyc)<-610
   If pX#(cyc)<-15 And pSubX#(cyc)=9999 Then pSubX#(cyc)=-15
   If pX#(cyc)>15 And pSubX#(cyc)=9999 Then pSubX#(cyc)=15
   If pSubZ#(cyc)=9999 Then pSubZ#(cyc)=-140
  EndIf
 EndIf
 ;enter north backstage area
 If pTZ#(cyc)>610 And pZ#(cyc)<610
  If pTX#(cyc)<-25 And pSubX#(cyc)=9999 Then pSubX#(cyc)=-25
  If pTX#(cyc)>25 And pSubX#(cyc)=9999 Then pSubX#(cyc)=25
  If pSubZ#(cyc)=9999 Then pSubZ#(cyc)=625
 EndIf
 ;enter south backstage area
 If pTZ#(cyc)<-610 And pZ#(cyc)>-610
  If pX#(cyc)<-25 And pSubX#(cyc)=9999 Then pSubX#(cyc)=-25
  If pX#(cyc)>25 And pSubX#(cyc)=9999 Then pSubX#(cyc)=25
  If pSubZ#(cyc)=9999 Then pSubZ#(cyc)=-625
 EndIf
 ;leave north backstage area
 If pTZ#(cyc)<610 And pZ#(cyc)>610 And (pX#(cyc)<-25 Or pX#(cyc)>25)
  If (pTX#(cyc)>-40 And pTX#(cyc)<-40) Or pTZ#(cyc)<395
   If pX#(cyc)<-25 And pSubX#(cyc)=9999 Then pSubX#(cyc)=-25
   If pX#(cyc)>25 And pSubX#(cyc)=9999 Then pSubX#(cyc)=25
   If pSubZ#(cyc)=9999 Then pSubZ#(cyc)=610
  EndIf
 EndIf
 ;leave south backstage area
 If pTZ#(cyc)>-610 And pZ#(cyc)<-610 And (pX#(cyc)<-25 Or pX#(cyc)>25)
  If (pTX#(cyc)>-40 And pTX#(cyc)<-40) Or pTZ#(cyc)>-395
   If pX#(cyc)<-25 And pSubX#(cyc)=9999 Then pSubX#(cyc)=-25
   If pX#(cyc)>25 And pSubX#(cyc)=9999 Then pSubX#(cyc)=25
   If pSubZ#(cyc)=9999 Then pSubZ#(cyc)=-610
  EndIf
 EndIf
 ;pursue tag inside ring
 If TryingToTag(cyc)
  If pZ#(cyc)>blockZ1#(1) And pTZ#(cyc)>blockZ1#(1) And pSubZ#(cyc)=9999 Then pSubZ#(cyc)=blockZ1#(1)-5
  If pX#(cyc)>blockX1#(2) And pTX#(cyc)>blockX1#(2) And pSubX#(cyc)=9999 Then pSubX#(cyc)=blockX1#(2)-5
  If pZ#(cyc)<blockZ2#(3) And pTZ#(cyc)<blockZ2#(3) And pSubZ#(cyc)=9999 Then pSubZ#(cyc)=blockZ2#(3)+5
  If pX#(cyc)<blockX2#(4) And pTX#(cyc)<blockX2#(4) And pSubX#(cyc)=9999 Then pSubX#(cyc)=blockX2#(4)+5
 EndIf
 ;find path to apron positions
 If RingViable(cyc)=0 And InsideRing(pTX#(cyc),pTZ#(cyc),0) And InsideRing(pX#(cyc),pZ#(cyc),-15)=0
  relocateX=0 : relocateZ=0
  If pTZ#(cyc)=>blockZ1#(0) And pTZ#(cyc)=<blockZ2#(0)
   If pX#(cyc)<blockX2#(4) And pTX#(cyc)>blockX1#(2) Then relocateZ=1
   If pX#(cyc)>blockX1#(2) And pTX#(cyc)<blockX2#(4) Then relocateZ=1
   If pZ#(cyc)=<blockZ1#(0) Or pZ#(cyc)=>blockZ2#(0)
    If pX#(cyc)=<blockX2#(0) And pTX#(cyc)=<blockX2#(0) And pTX#(cyc)>blockX1#(2) Then relocateX=1
    If pX#(cyc)=>blockX1#(0) And pTX#(cyc)=>blockX1#(0) And pTX#(cyc)<blockX2#(4) Then relocateX=1
   EndIf
  EndIf
  If pTX#(cyc)=>blockX1#(0) And pTX#(cyc)=<blockX2#(0)
   If pZ#(cyc)<blockZ2#(3) And pTZ#(cyc)>blockZ1#(1) Then relocateX=1
   If pZ#(cyc)>blockZ1#(1) And pTZ#(cyc)<blockZ2#(3) Then relocateX=1
   If pX#(cyc)=<blockX1#(0) Or pX#(cyc)=>blockX2#(0)
    If pZ#(cyc)=<blockZ2#(0) And pTZ#(cyc)=<blockZ2#(0) And pTZ#(cyc)>blockZ1#(1) Then relocateZ=1
    If pZ#(cyc)=>blockZ1#(0) And pTZ#(cyc)=>blockZ1#(0) And pTZ#(cyc)<blockZ2#(3) Then relocateZ=1
   EndIf
  EndIf
  If relocateX=1 And pSubX#(cyc)=9999
   If pX#(cyc)=>0 Then pSubX#(cyc)=105 Else pSubX#(cyc)=-105
  EndIf
  If relocateZ=1 And pSubZ#(cyc)=9999
   If pZ#(cyc)=>0 Then pSubZ#(cyc)=105 Else pSubZ#(cyc)=-105
  EndIf
 EndIf
 ;get off unhelpful aprons
 If RingViable(cyc)=0 And pPlatform(cyc)=>1 And pPlatform(cyc)=<4
  If pZ#(cyc)>blockZ1#(1) And pTZ#(cyc)<blockZ1#(1) And pSubZ#(cyc)=9999 Then pSubZ#(cyc)=105
  If pX#(cyc)>blockX1#(2) And pTX#(cyc)<blockX1#(2) And pSubX#(cyc)=9999 Then pSubX#(cyc)=105
  If pZ#(cyc)<blockZ2#(3) And pTZ#(cyc)>blockZ2#(3) And pSubZ#(cyc)=9999 Then pSubZ#(cyc)=-105
  If pX#(cyc)<blockX2#(4) And pTX#(cyc)>blockX2#(4) And pSubX#(cyc)=9999 Then pSubX#(cyc)=-105
 EndIf
 ;get around ring
 If RingViable(cyc)=0 And InsideRing(pX#(cyc),pZ#(cyc),-15)=0
  If pTZ#(cyc)=>blockZ1#(0) And pTZ#(cyc)=<blockZ2#(0) And pSubZ#(cyc)=9999
   If (pX#(cyc)<blockX2#(4) And pTX#(cyc)>blockX1#(2)) Or (pX#(cyc)>blockX1#(2) And pTX#(cyc)<blockX2#(4))
    If pZ#(cyc)=>0 Then pSubZ#(cyc)=105 Else pSubZ#(cyc)=-105
   EndIf
  EndIf
  If pTX#(cyc)=>blockX1#(0) And pTX#(cyc)=<blockX2#(0) And pSubX#(cyc)=9999
   If (pZ#(cyc)<blockZ2#(3) And pTZ#(cyc)>blockZ1#(1)) Or (pZ#(cyc)>blockZ1#(1) And pTZ#(cyc)<blockZ2#(3))
    If pX#(cyc)=>0 Then pSubX#(cyc)=105 Else pSubX#(cyc)=-105
   EndIf
  EndIf
 EndIf
 ;MOVEMENT INPUT
 ;get working values
 pActX#(cyc)=pTX#(cyc) : pActZ#(cyc)=pTZ#(cyc)
 If pSubX#(cyc)<>9999 Then pActX#(cyc)=pSubX#(cyc) : pFollow(cyc)=1
 If pSubZ#(cyc)<>9999 Then pActZ#(cyc)=pSubZ#(cyc) : pFollow(cyc)=1
 ;check satisfied
 pSatisfied(cyc)=pSatisfied(cyc)-1
 If pSatisfied(cyc)<0 Or pOutTim(cyc)=0 Then pSatisfied(cyc)=0
 If ReachedCord(pX#(cyc),pZ#(cyc),pTX#(cyc),pTZ#(cyc),pFollow(cyc)) Then pSatisfied(cyc)=50
 ;pursue target
 If ReachedCord(pX#(cyc),pZ#(cyc),pActX#(cyc),pActZ#(cyc),pFollow(cyc))=0
  If pSatisfied(cyc)=0 Or (pAgenda(cyc)<>1 And pOutTim(cyc)>2)
   PositionEntity dummy,pActX#(cyc),pY#(cyc),pActZ#(cyc)
   PointEntity p(cyc),dummy
   tA#=CleanAngle#(EntityYaw(p(cyc)))
   RotateEntity p(cyc),0,pA#(cyc),0
   RequestDirection(cyc,tA#,2)
  EndIf
 EndIf
 ;stall on platforms
 If pPlatform(cyc)=>5 And pPlatform(cyc)=<8 And LegalMan(cyc) And pFoc(cyc)>0 And AttackViable(pFoc(cyc))>0
  If GetDistance#(pX#(cyc),pZ#(cyc),pX#(pFoc(cyc)),pZ#(pFoc(cyc)))<RequiredRange#(cyc,64) Or GetDistance#(pX#(cyc),pZ#(cyc),pX#(pFoc(cyc)),pZ#(pFoc(cyc)))<RequiredRange#(cyc,74)
   cUpTim(cyc)=0 : cDownTim(cyc)=0
   cLeftTim(cyc)=0 : cRightTim(cyc)=0
  EndIf
 EndIf
 ;add running
 pRunTim(cyc)=pRunTim(cyc)-1
 If pRunTim(cyc)<0 Then pRunTim(cyc)=0
 If pRunTim(cyc)>250 Then pRunTim(cyc)=250
 If pRunTim(cyc)=0 And ReachedCord(pX#(cyc),pZ#(cyc),pActX#(cyc),pActZ#(cyc),20)=0
  journey=Int(GetDistance#(pX#(cyc),pZ#(cyc),pActX#(cyc),pActZ#(cyc)))
  If journey<25 Then journey=25
  chance=600
  If matchState=3 Then chance=chance/2
  If matchState=3 And pRole(cyc)=0 And pFoc(cyc)>0 And AttackViable(pFoc(cyc))>0 Then chance=chance/2 : journey=journey*2
  If TryingToTag(cyc) Then chance=chance/2
  randy=Rnd(0,chance)
  If randy=<RushViable(cyc)*2 Then pRunTim(cyc)=Rnd(25,journey*2)
 EndIf
 If pRole(cyc)=1 And RefViable(pFoc(cyc))>0 And RefViable(pFoc(cyc))<>5 And InProximity(cyc,pFoc(cyc),30)=0
  If pRunTim(cyc)<10 Then pRunTim(cyc)=10
 EndIf
 If ReachedCord(pX#(cyc),pZ#(cyc),pActX#(cyc),pActZ#(cyc),15) Then pRunTim(cyc)=0
 If InsideRing(pX#(cyc),pZ#(cyc),-15) And InsideRing(pX#(cyc),pZ#(cyc),-35)=0 And InsideRing(pActX#(cyc),pActZ#(cyc),-15)=0 Then pRunTim(cyc)=0
 If pRunTim(cyc)>0 Then cRun(cyc)=1
 ;If matchState=1 And pOutTim(cyc)=<2 And HumanClear(cyc,pA#(cyc),15)=0 Then cRun(cyc)=0
 ;ATTACKING
 v=pFoc(cyc)
 If v>0 And cyc<>v And AttackViable(v)>0 And AttackSensible(cyc,v)
  ;assess priority
  priority=2
  randy=Rnd(0,5-optLevel)
  If randy=<0
   If (FindThreat(cyc)>10 Or pSpecial(v)>0) And pFoc(v)=cyc Then priority=1
   If (pAnim(v)=>80 And pAnim(v)=<119) Or AttackViable(v)=>2 Then priority=3
   If pDizzyTim(v)>0 Or pBlindTim(v)>0 Then priority=3
   If pSpecial(cyc)>0 Then priority=3
   If pFoc(v)<>cyc Then priority=3
  EndIf
  If pAnim(v)=701 Or pPlatform(v)=>5 Or pMomentum(v)>0 Then priority=3
  If pRole(cyc)=1 Then priority=1
  ;assess intensity
  intensity=GetIntensity(cyc)
  If AttackViable(v)=>3 And pPinning(v)=0 And pGrappling(v)=0 Then intensity=intensity*2
  If pAnim(cyc)=11 And DirPressed(cyc) And SatisfiedAngle(RequestAngle#(cyc),pA#(v),45) And BlockClear(cyc,pA#(v),10) Then intensity=1000
  If pAnim(cyc)=30 And pAnimTim(cyc)<Int(20/pAnimSpeed#(cyc)) Then intensity=1000
  If pPlatform(cyc)=>5 Or (pPlatform(cyc)=>1 And pPlatform(cyc)=<4 And pY#(v)<pY#(cyc)) Then intensity=1000
  If FindThreat(cyc)<10 And pPinning(v)=0 And pGrappling(v)=0
   If pRole(cyc)=1 And pWarned(v)=0 Then intensity=10000
   If LegalMan(cyc)=0 And pAngerTim(cyc,v)=0 And pChaosTim(cyc)=0 Then intensity=10000
   If LegalMan(v)=0 And pAngerTim(cyc,v)=0 And pChaosTim(v)=0 Then intensity=10000
   If pTeam(cyc)=pTeam(v) And pAngerTim(cyc,v)=0 Then intensity=10000
   If matchState<>3 And pAngerTim(cyc,v)=0 Then intensity=10000
   If RushViable(cyc) Then intensity=10000
  EndIf
  If matchTeams=2 And LegalMan(cyc)=0 And TagProximity(cyc,80) And pPlatform(cyc)=>1 And pPlatform(cyc)=<4 And pY#(v)<pY#(cyc) Then intensity=10000
  If matchCage>0 And pPlatform(cyc)=>91 And pPlatform(cyc)=<98 And matchCountOuts=3 Then intensity=1000000
  If pRole(cyc)=1
   If pWarned(v)=0 And intensity<1000 Then intensity=1000
   If RefViable(v)>20 And intensity>2500 Then intensity=2500
  EndIf
  testIntensity=intensity
  randy=Rnd(0,intensity)
  ;get working range
  blockOffset#=0
  If pAnim(v)=>80 And pAnim(v)=<89 Then blockOffset#=2.0
  distance#=GetDistance#(pX#(cyc),pZ#(cyc),RealX#(v),RealZ#(v))
  If AttackViable(v)=<2 And GetDistance#(pX#(cyc),pZ#(cyc),pLimbX#(v,1),pLimbZ#(v,1))<distance#
   distance#=GetDistance#(pX#(cyc),pZ#(cyc),pLimbX#(v,1),pLimbZ#(v,1))
  EndIf
  ;weak attacks
  If pAnim(v)<>701
   anim=60 : cAttackAgenda(cyc)=Rnd(0,2)
   If cAttackAgenda(cyc)>0 Or AttackViable(v)=2 Then anim=61
   If pHolding(cyc)>0 And cAttackAgenda(cyc)>0 Then anim=220+(weapHold(weapType(pHolding(cyc)))*2)
   If FindRunUp(cyc) Then anim=63
   If pAnim(cyc)=156 Then anim=65
   If AttackViable(v)=>3
    anim=71
    If pHolding(cyc)>0 Then anim=227
    If FindRunUp(cyc) Then anim=73
   EndIf
   If pPlatform(cyc)=>5 Or (pPlatform(cyc)=>1 And pPlatform(cyc)=<4 And pY#(v)<pY#(cyc)) Then anim=64
   If (priority=1 And randy=<40) Or (priority=2 And randy=<30) Or (priority=3 And randy=<20)
    If distance#<RequiredRange#(cyc,anim)+blockOffset# Then cAttack(cyc)=1 : cRun(cyc)=0 : cGrapple(cyc)=0
   EndIf
  EndIf
  ;big attacks
  anim=62
  If pHolding(cyc)>0 And weapHold(weapType(pHolding(cyc)))=<2 Then anim=219+(weapHold(weapType(pHolding(cyc)))*2)
  If pHolding(cyc)>0 And weapHold(weapType(pHolding(cyc)))=3 Then anim=226
  If FindRunUp(cyc) Then anim=63
  If AttackViable(v)=>3
   anim=72
   If pHolding(cyc)>0 Then anim=227
   If FindRunUp(cyc) Then anim=73
  EndIf
  If (priority=1 And randy=>41 And randy=<45) Or (priority=2 And randy=>31 And randy=<40) Or (priority=3 And randy=>21 And randy=<35)
   If distance#<RequiredRange#(cyc,anim)+blockOffset# Then cAttack(cyc)=1 : cRun(cyc)=1 : cGrapple(cyc)=0
  EndIf
  ;grapples
  If pGrappling(v)=0 And pGrappler(v)=0
   If (priority=1 And randy=>46 And randy=<50) Or (priority=2 And randy=>41 And randy=<50) Or (priority=3 And randy=>36 And randy=<50) Or (randy=<10 And pMomentum(v)>0)
    If distance#<RequiredRange#(cyc,300)+blockOffset#
     If matchShoot=0 Then cAttack(cyc)=0 : cRun(cyc)=0 : cGrapple(cyc)=1
     If matchShoot=1 Then cAttack(cyc)=1 : cRun(cyc)=0 : cGrapple(cyc)=0
    EndIf
   EndIf
  EndIf
  ;turn ref big attacks into grapples
  If pRole(cyc)=1 And cAttack(cyc)=1 And cRun(cyc)=1 Then cAttack(cyc)=0 : cRun(cyc)=0 : cGrapple(cyc)=1
  ;assist team moves
  If cAttack(cyc)=1
   If PotentialAssistance(cyc)>0 Then cAttack(cyc)=0 : cRun(cyc)=0 : cGrapple(cyc)=1
  EndIf
  ;avoid repetitive spitting
  If cAttack(cyc)=1 And (cRun(cyc)=0 Or cGrapple(cyc)=1)
   If attackWeapon(3,charAttack(pChar(cyc),3))=>10 Or (pHolding(cyc)>0 And weapType(pHolding(cyc))=>16 And weapType(pHolding(cyc))=<17)
    If pBlindTim(v)>0 Then cAttack(cyc)=1 : cRun(cyc)=0 : cGrapple(cyc)=0
   EndIf
  EndIf
  ;negate action
  If pAnim(cyc)=65 Or pAnim(cyc)=156
   If AttackViable(v)=>3 Or cRun(cyc)=1 Or cGrapple(cyc)=1 Then cAttack(cyc)=0 ;prevent crouching punches
  EndIf
  If matchState=3 And matchCountOuts=3 And matchCage=0 And LegalMan(cyc) And InsideRing(pX#(v),pZ#(v),0)=0 Then cAttack(cyc)=0 ;don't jump off cage walls
  If cAttack(cyc)=1 Or cGrapple(cyc)=1
   If pCarrying(cyc)>0 Then cPickUp(cyc)=1 : cAttack(cyc)=0 : cRun(cyc)=0 : cGrapple(cyc)=0 ;drop if carrying
   If InLine(cyc,pLimb(v,36),45)=0 Then cAttack(cyc)=0 : cRun(cyc)=0 : cGrapple(cyc)=0 ;not in line
  EndIf
 EndIf
 ;DEFENDING
 v=pFoc(cyc)
 If FindThreat(cyc)>10
  ;blocking
  If pAnim(cyc)<>30 And pAnim(v)<>64 And pAnim(v)<>300
   intensity=GetIntensity(cyc)
   If pAnim(cyc)=11 And DirPressed(cyc) And SatisfiedAngle(RequestAngle(cyc),pA#(v),45) And BlockClear(cyc,pA#(cyc)+180,10) And InProximity(cyc,v,15)=0 Then intensity=intensity*1000
   If pSpecial(cyc)>0 Or RushViable(cyc) Then intensity=intensity*10
   If DirPressed(cyc)=0 Or ActionPressed(cyc)=0 Then intensity=intensity/2
   If matchShoot>0 Then intensity=intensity/2
   randy=Rnd(0,intensity)
   If randy=<1 And pBlockTim(cyc)=0 Then pBlockTim(cyc)=Rnd(10,50)
   If pBlockTim(cyc)>0 Then cBlock(cyc)=1
  EndIf
  ;evading
  If BlockClear(cyc,pA#(cyc)+180,10)
   intensity=GetIntensity(cyc)
   If ActionPressed(cyc)=0 Then intensity=intensity/2
   If cBlock(cyc)=1 Then intensity=intensity*2
   If RushViable(cyc) Then intensity=intensity*10
   randy=Rnd(0,intensity)
   If randy=<10 And InProximity(cyc,v,15)=0 Then RequestDirection(cyc,pA#(cyc)+Rnd(90,270),20) : cBlock(cyc)=0
   If randy=>11 And randy=<12 And pAnim(cyc)<>30 And pDashState(cyc)<>3
    pDashA#(cyc)=CleanAngle#(pA#(v)+Rnd(-135,135))
    pDashState(cyc)=3 : pDashTim(cyc)=0
    cBlock(cyc)=0
   EndIf
  EndIf
 EndIf
 ;random blocking
 pBlockTim(cyc)=pBlockTim(cyc)-1
 If pBlockTim(cyc)<0 Then pBlockTim(cyc)=0
 If matchState=3 And v>0 And pFoc(v)=cyc And pTeam(cyc)<>pTeam(v) And pRole(v)=0 And pRole(cyc)=0 And pAgenda(cyc)=1 And InProximity(cyc,v,50) And pY#(cyc)=pY#(v) And AttackViable(v)=1 And pDizzyTim(v)=0 And pBlindTim(v)=0 And pMomentum(v)=0 And RushViable(cyc)=0
  chance=2000
  If pSpecial(cyc)>0 Then chance=chance*2
  If pHP(cyc)<pHP(v)/2 Then chance=chance/2
  If matchShoot>0 Then chance=chance/2
  randy=Rnd(0,chance)
  If randy=0 And pBlockTim(cyc)=0 Then pBlockTim(cyc)=Rnd(20,200)
  If pBlockTim(cyc)>0 Then cBlock(cyc)=1
 EndIf
 ;taunts
 If RushViable(cyc)=0
  chance=1000
  If matchState=3 And LegalMan(cyc) And Isolated(cyc,30) Then chance=chance/2
  If matchState=1 Or matchState=4 Then chance=chance/2
  If matchState=4 And matchWinner>0 And pTeam(cyc)=pTeam(matchWinner) Then chance=chance/2
  If FindInteractions(cyc)>0 And FindInteractions(cyc)<>2 Then chance=chance/4
  randy=Rnd(0,chance)
  If randy=<1 Then cTaunt(cyc)=1
 EndIf
 ;ref triggers
 If RefActViable(cyc,pFoc(cyc))
  If pChecked(pFoc(cyc))=0 And pCountTim(cyc)=0 Then cTaunt(cyc)=1 Else cTaunt(cyc)=0
 EndIf
 ;ITEM INTERACTION
 ;pick up item
 If pAgenda(cyc)=20 And pItemFoc(cyc)>0 And pCarrying(cyc)=0 And pPlatform(cyc)=0 And pScar(cyc,21)=<4
  v=pItemFoc(cyc)
  If ItemProximity(cyc,v,30) And pY#(cyc)=>iY#(v)-5 And pY#(cyc)=<iY#(v)+5 And iCarrier(v)=0 And iCarryAnim(iType(v),iState(v))=>0 And iAnim(v)=0
   If ItemCollide(cyc,v,pX#(cyc),pZ#(cyc),2) Then cPickUp(cyc)=1 : cGrapple(cyc)=0 : cRun(cyc)=0 : cAttack(cyc)=0
  EndIf
 EndIf
 ;pick up weapon
 If pAgenda(cyc)=21 And pWeapFoc(cyc)>0 And pHolding(cyc)=0 And pScar(cyc,21)=<4
  v=pWeapFoc(cyc)
  range#=10+weapSize#(weapType(v))
  If weapY#(v)>pY#(cyc)+10 Then range#=range#+10
  If weapCarrier(v)>0 Then range#=20+(weapSize#(weapType(v))/2)
  If WeaponProximity(cyc,v,range#) And weapY#(v)=>pY#(cyc)-5 And weapY#(v)=<pY#(cyc)+25
   If weapCarrier(v)=0 Or (weapCarrier(v)>0 And (AttackSensible(cyc,weapCarrier(v)) Or pRole(cyc)=1) And matchState=>3)
    cPickUp(cyc)=1 : cGrapple(cyc)=0 : cRun(cyc)=0 : cAttack(cyc)=0
   EndIf
  EndIf
 EndIf
 ;item dropping
 chance=200
 If pAgenda(cyc)=0 And ReachedCord(pX#(cyc),pZ#(cyc),pActX#(cyc),pActZ#(cyc),pFollow(cyc)) Then chance=chance/2
 If LegalMan(cyc) And matchState=3 And Isolated(cyc,30)=0 Then chance=chance/4
 randy=Rnd(0,chance)
 If randy=<1 And pCarrying(cyc)>0 Then cPickUp(cyc)=1 : cGrapple(cyc)=0 : cRun(cyc)=0 : cAttack(cyc)=0
 ;weapon dropping/throwing
 dropBlock=0
 If matchState=<2 And matchPromo>0 And weapType(pHolding(cyc))=5
  If cyc=promoActor(1) Or cyc=promoActor(2) Or cyc=promoActor(3) Then dropBlock=1
 EndIf
 If matchState=4 And weapType(pHolding(cyc))=FindReward(cyc) Then dropBlock=1
 If pHolding(cyc)>0 And dropBlock=0
  randy=Rnd(0,1000)
  If pRole(cyc)=1 Then randy=Rnd(0,500)
  If matchState=2 Then randy=Rnd(0,10000)
  If matchState=3 And matchRules=>2 And LegalMan(cyc) And InsideRing(pX#(cyc),pZ#(cyc),0)
   If FindReferee(cyc,pFoc(cyc))>0 Or (randy=>50 And randy=<100) Then randy=0
  EndIf
  If matchState=4 And FindReward(cyc)>0 And weapType(pHolding(cyc))<>FindReward(cyc) Then randy=0
  If randy=0 Then cPickUp(cyc)=1 : cGrapple(cyc)=0 : cRun(cyc)=0 : cAttack(cyc)=0
  If randy=1 Or (randy=>1 And randy=<5 And weapExplodable(weapType(pHolding(cyc)))) Or (randy=>1 And randy=<5 And pRole(cyc)=1)
   If weapWear(pHolding(cyc))=0 And pTeam(pFoc(cyc))<>pTeam(cyc)
    If Isolated(cyc,30) Or pRole(cyc)=1 Then cGrapple(cyc)=1 : cPickUp(cyc)=0 : cRun(cyc)=0 : cAttack(cyc)=0
   EndIf
  EndIf
 EndIf
 ;TRANSLATE MOVEMENT INPUT
 cUpTim(cyc)=cUpTim(cyc)-1
 If cUpTim(cyc)<0 Then cUpTim(cyc)=0
 cDownTim(cyc)=cDownTim(cyc)-1
 If cDownTim(cyc)<0 Then cDownTim(cyc)=0
 cLeftTim(cyc)=cLeftTim(cyc)-1
 If cLeftTim(cyc)<0 Then cLeftTim(cyc)=0
 cRightTim(cyc)=cRightTim(cyc)-1
 If cRightTim(cyc)<0 Then cRightTim(cyc)=0
 If cUpTim(cyc)>0 And cUpTim(cyc)>cDownTim(cyc) Then cUp(cyc)=1
 If cDownTim(cyc)>0 And cDownTim(cyc)>cUpTim(cyc) Then cDown(cyc)=1
 If cLeftTim(cyc)>0 And cLeftTim(cyc)>cRightTim(cyc) Then cLeft(cyc)=1
 If cRightTim(cyc)>0 And cRightTim(cyc)>cLeftTim(cyc) Then cRight(cyc)=1
 ;protect pin
 If ProtectPin(cyc)>0 Then ResetInput(cyc)
End Function

;----------------------------------------------------------------
;////////////////// TRANSLATE INPUT /////////////////////////////
;----------------------------------------------------------------
Function TranslateInput(cyc)
 ;declaw
 If matchState=<2 Then cAttack(cyc)=0
 If matchState=3 And LegalMan(cyc)=0 And pChaosTim(cyc)=0 And pRole(cyc)<>1 And pRole(cyc)<>3
  If InsideRing(pX#(cyc),pZ#(cyc),-15) Or (pPlatform(cyc)=>5 And pPlatform(cyc)=<8) Then cAttack(cyc)=0 : cGrapple(cyc)=0
 EndIf
 If matchState=<1 And pOutTim(cyc)<2 Then cTaunt(cyc)=0 : cSwitch(cyc)=0
 If matchState=2 And pControl(cyc)=0
  If cyc=speaker Or InProximity(cyc,speaker,50) Then cUp(cyc)=0 : cDown(cyc)=0 : cLeft(cyc)=0 : cRight(cyc)=0
 EndIf
 ;forced entrance
 If (matchState=1 Or matchEnter=cyc) And pOutTim(cyc)=1
  cUp(cyc)=0 : cDown(cyc)=0 : cLeft(cyc)=0 : cRight(cyc)=0
  PositionEntity dummy,0,pY#(cyc),0
  PointEntity p(cyc),dummy
  tA#=CleanAngle#(EntityYaw(p(cyc)))
  RotateEntity p(cyc),0,pA#(cyc),0
  RequestDirection(cyc,tA#,2)
  If cUpTim(cyc)>0 Then cUp(cyc)=1
  If cDownTim(cyc)>0 Then cDown(cyc)=1
  If cLeftTim(cyc)>0 Then cLeft(cyc)=1
  If cRightTim(cyc)>0 Then cRight(cyc)=1
 EndIf
 If matchState=1 And pOutTim(cyc)=<1 ;(pOutTim(cyc)=<1 Or (pControl(cyc)=0 And pOutTim(cyc)=2))
  If HumanClear(cyc,RequestAngle#(cyc),15)=0 Then cUp(cyc)=0 : cDown(cyc)=0 : cLeft(cyc)=0 : cRight(cyc)=0 : pSatisfied(cyc)=50
 EndIf
 ;movement
 If DirPressed(cyc) And pAnim(cyc)<11 And pMomentum(cyc)=0 Then ChangeAnim(cyc,11)
 ;lucid actions
 If pDizzyTim(cyc)=0 And pBlindTim(cyc)=0 And pMomentum(cyc)=0
  ;switch focus
  If cSwitch(cyc)=1 And pFocTim(cyc)<40 And SwitchViable(cyc) Then PlaySound sMenuBrowse : SwitchFocus(cyc)
  ;trigger reactions
  If pAnim(cyc)=pReaction(cyc) Then pReaction(cyc)=0
  If pReaction(cyc)>0 And pAnim(cyc)<40
   If pReaction(cyc)=>111 And pReaction(cyc)=<115 Then pImmunity(cyc)=20
   ChangeAnim(cyc,pReaction(cyc)) : pReaction(cyc)=0
  EndIf
  ;special ignition
  If pHeat(cyc)=>100 And pSpecial(cyc)=0 And matchState=>3 And pAnim(cyc)<40 Then ChangeAnim(cyc,192)
  ;running
  If cRun(cyc)=1 And pAnim(cyc)=<11 Then ChangeAnim(cyc,12)
  ;dashing
 If optOnline=0
  If pDashState(cyc)=0 And DirPressed(cyc) And pAnim(cyc)<>30 Then pDashState(cyc)=1 : pDashA#(cyc)=RequestAngle#(cyc)
  If pDashState(cyc)=1 And DirPressed(cyc)=0 Then pDashState(cyc)=2
  If pDashState(cyc)=>2 Then pDashTim(cyc)=pDashTim(cyc)+1 Else pDashTim(cyc)=0
  If pDashState(cyc)=2 And pDashTim(cyc)>15 Then pDashState(cyc)=0
  If pControl(cyc)>0 And pDashState(cyc)=2 And DirPressed(cyc)
   If SatisfiedAngle(RequestAngle#(cyc),pDashA#(cyc),45) Then pDashState(cyc)=3
  EndIf
  v=pFoc(cyc)
  If matchState=3 And pControl(cyc)=0 And pDashState(cyc)<>3 And pAnim(cyc)<>30 And v>0 And pAnim(v)<>701 And FightViable(cyc,v) And pSpecial(cyc)=0 And LegalMan(cyc) And LegalMan(v) And RushViable(cyc)=0 And AttackViable(v)=1 And pPlatform(v)=0
   randy=Rnd(0,pAgility(cyc)*2)
   If randy=0
    Repeat
     pDashA#(cyc)=Rnd(0,360)
    Until SatisfiedAngle(pDashA#(cyc),pA#(cyc),45)=0 Or InProximity(cyc,v,30)=0
    pDashState(cyc)=3 : pDashTim(cyc)=0
   EndIf
  EndIf
  If pDashState(cyc)=3 And pDashTim(cyc)>30 Then pDashState(cyc)=0
  If pDashState(cyc)=3 And pAnim(cyc)<12
   If FindInjury(cyc)=<1 Or pInjured(cyc,0)>0 Or pInjured(cyc,4)>0 Then ChangeAnim(cyc,30)
   pDashState(cyc)=0
  EndIf
 EndIf
  ;blocking
  If cBlock(cyc)=1 And pAnim(cyc)<40 Then ChangeAnim(cyc,80)
  ;blocking sway
  If pAnim(cyc)=>80 And pAnim(cyc)=<85 And DirPressed(cyc)
   If SatisfiedAngle(RequestAngle#(cyc),CleanAngle#(pA#(cyc)+77),35) Then pBodyTXA#(cyc)=0 : pBodyTZA#(cyc)=20 ;west
   If SatisfiedAngle(RequestAngle#(cyc),CleanAngle#(pA#(cyc)+135),23) Then pBodyTXA#(cyc)=-25 : pBodyTZA#(cyc)=10 ;south west
   If SatisfiedAngle(RequestAngle#(cyc),CleanAngle#(pA#(cyc)+180),23) Then pBodyTXA#(cyc)=-40 : pBodyTZA#(cyc)=0 ;south
   If SatisfiedAngle(RequestAngle#(cyc),CleanAngle#(pA#(cyc)+225),23) Then pBodyTXA#(cyc)=-25 : pBodyTZA#(cyc)=-15 ;south east
   If SatisfiedAngle(RequestAngle#(cyc),CleanAngle#(pA#(cyc)+283),35) Then pBodyTXA#(cyc)=0 : pBodyTZA#(cyc)=-30 ;east
   If pHolding(cyc)>0 And weapHold(weapType(pHolding(cyc)))=1 Then pBodyTXA#(cyc)=pBodyTXA#(cyc)/2
  EndIf
  ;flying triggers
  If pAnim(cyc)<30 Or (pAnim(cyc)=>48 And pAnim(cyc)=<49) Or (pAnim(cyc)=>231 And pAnim(cyc)=<232)
   viable=1
   If pFoc(cyc)>0 And (pY#(cyc)=pY#(pFoc(cyc)) Or (pAnim(pFoc(cyc))=40 And pY#(cyc)=wStage#))
    If InProximity(cyc,pFoc(cyc),30) Or pPlatform(cyc)=pPlatform(pFoc(cyc)) Or (pPlatform(cyc)=<4 And pPlatform(pFoc(cyc))=<4) Then viable=0
   EndIf
   If viable=1 And cAttack(cyc)=1 And pPlatform(cyc)>0
    pFlightA#(cyc)=pA#(cyc)
    If pPlatform(cyc)=>91 And pPlatform(cyc)=<98 Then pFlightA#(cyc)=pA#(cyc)+180
    If pFoc(cyc)>0
     PointEntity p(cyc),p(pFoc(cyc))
     pFlightA#(cyc)=EntityYaw(p(cyc))
     RotateEntity p(cyc),0,pA#(cyc),0
    EndIf
    If FlightClear(cyc,pFlightA#(cyc),35) Or (FlightClear(cyc,pFlightA#(cyc),75) And pPlatform(cyc)<>49)
     RiskFall(cyc,1)
     If pHP(cyc)>0
      If (pFoc(cyc)>0 And AttackViable(pFoc(cyc))=>3) Or cRun(cyc)=1 Then anim=58 Else anim=57
      If anim=58 And crushName$(5,charCrush(pChar(cyc),5))="Moonsault" Then pA#(cyc)=pFlightA#(cyc)
      ChangeAnim(cyc,anim)
     EndIf
    EndIf
   EndIf
  EndIf
  ;running jump to outside
  If pAnim(cyc)=12 And pAnimTim(cyc)>10 And FindInjury(cyc)=<1 And pPlatform(cyc)=0
   If pFoc(cyc)>0 And InsideRing(pX#(pFoc(cyc)),pZ#(pFoc(cyc)),0)=0 And InsideRing(pX#(cyc),pZ#(cyc),-15) ;And InsideRing(pX#(cyc),pZ#(cyc),-55)=0
    If cAttack(cyc)=1 Or (pControl(cyc)=0 And TopeViable(cyc))
     tope=0
     PositionEntity dummy,pX#(cyc),pY#(cyc),pZ#(cyc)
     RotateEntity dummy,0,pA#(cyc),0
     MoveEntity dummy,0,0,60
     If InsideRing(EntityX(dummy),EntityZ(dummy),0)=0 And matchCage=0
      If charAgility(pChar(cyc))=>70 And InsideRing(pX#(pFoc(cyc)),pZ#(pFoc(cyc)),5)=0
       If crushTope(5,charCrush(pChar(cyc),5))>0 Then tope=74
       If AttackViable(pFoc(cyc))=1 And attackTope(5,charAttack(pChar(cyc),5))>0 Then tope=64
      EndIf
     EndIf
     If tope=0 Then ChangeAnim(cyc,68)
     If tope>0 Then ChangeAnim(cyc,tope)
    EndIf
   EndIf
  EndIf
  ;attacks
  If cAttack(cyc)=1 And pAnim(cyc)<40 And (pPlatform(cyc)=<4 Or pPlatform(cyc)>8)
   runup=FindRunUp(cyc)
   If NearGrounded(cyc,30)
    anim=71 ;stomp
    If cRun(cyc)=1 Then anim=72 ;crush
    If runup=1 Then anim=73 ;running crush
   Else
    anim=61 ;lower attack
    If (pControl(cyc)>0 And DirPressed(cyc)) Or (pControl(cyc)=0 And cAttackAgenda(cyc)>0)
     anim=60 ;upper attack
     If pFoc(cyc)>0 And AttackViable(pFoc(cyc))=2 And (pHolding(cyc)=0 Or weapHold(weapType(pHolding(cyc)))=0) Then anim=61 ;low translation
    EndIf
    If cRun(cyc)=1 Then anim=62 ;big attack
    If runup=1 Then anim=63 ;running attack
   EndIf
   If anim>0 Then ChangeAnim(cyc,anim)
  EndIf
  ;grapple / throw weapon
  If cGrapple(cyc)=1 And pAnim(cyc)<40
   If pHolding(cyc)>0 And weapWear(pHolding(cyc))=0
    If pFoc(cyc)>0 And pRole(cyc)<>1
     If InProximity(cyc,pFoc(cyc),30)=0 Or matchShoot>0 Then ChangeAnim(cyc,213)
    Else
     ChangeAnim(cyc,213)
    EndIf
   EndIf
   If pAnim(cyc)<>213 And matchState=>3 And matchShoot=0
    If NearGrounded(cyc,40) Then anim=500 Else anim=300
    ChangeAnim(cyc,anim)
   EndIf
  EndIf
  ;pin attempts
  If cTaunt(cyc)=1 And pAnim(cyc)<40 And matchState=3 And pRole(cyc)=0 And matchPins>0 And FallsCount(cyc) And pPlatform(cyc)=0
   v=pFoc(cyc)
   If v>0 And cyc<>v And pRole(v)=0 And pTeam(v)<>pTeam(cyc) And AttackViable(v)=>3 And GettingUp(v)=0 And pY#(cyc)=pY#(v) And pPlatform(v)=0 And pPinning(v)=0 And pPinner(v)=0 And pGrappler(v)=0
    If InProximity(cyc,v,20) Or GetDistance#(pX#(cyc),pZ#(cyc),pLimbX#(v,1),pLimbZ#(v,1))<15
     GroundImpactChecks(v)
     pFoc(cyc)=v : pFoc(v)=cyc
     pPinning(cyc)=v : pPinner(v)=cyc
     pAutoTim(cyc)=0 : pWarned(cyc)=0
     GetPin(cyc,v)
     ChangeAnim(cyc,160)
     anim=PinReaction(v)
     If anim>0 And pAnim(v)<>anim
      ChangeAnim(v,anim) : pHurtTim(v)=5
      If (anim=139 Or anim=159) And pOldAnim(v)<>151 Then SharpTransition(v,anim,0,180)
     EndIf
     If pDT(v)<50 Then pDT(v)=50
    EndIf
   EndIf
  EndIf
  ;refereeing
  If cTaunt(cyc)=1 And RefActViable(cyc,pFoc(cyc))=0 And pAnim(cyc)<40
   For v=1 To no_plays
    If RefActViable(cyc,v)>0 Then pFoc(cyc)=v
   Next
  EndIf
  If cTaunt(cyc)=1 And RefActViable(cyc,pFoc(cyc)) And pAnim(cyc)<40
   duty=RefViable(pFoc(cyc))
   If FindInteractions(cyc)<>3 Or (duty<>5 And duty<>15)
    InstantTurn(cyc,p(pFoc(cyc)))
    anim=178
    If (duty=2 Or duty=12) And pAnim(pFoc(cyc))=309 Then anim=183
    If duty=5 Or duty=15
     anim=170
     For v=1 To no_plays
      If v<>pFoc(cyc) And (RefViable(v)=5 Or RefViable(v)=15)
       anim=171
       PositionEntity dummy,GetCentre#(RealX#(pFoc(cyc)),RealX#(v)),pY#(cyc),GetCentre#(RealZ#(pFoc(cyc)),RealZ#(v))
       InstantTurn(cyc,dummy)
      EndIf
     Next
     matchCounter=cyc
    EndIf
    ChangeAnim(cyc,anim)
    pRefVictim(cyc)=pFoc(cyc)
    If duty=1 Or duty=11 Then pRefAward(cyc)=pPinner(pFoc(cyc)) : pPinCount(pFoc(cyc))=0
    If duty=2 Or duty=12 Then pRefAward(cyc)=pGrappler(pFoc(cyc))
    If duty<>5 And duty<>15 Then pChecked(pRefVictim(cyc))=1
   EndIf
  EndIf
  ;taunts
  If cTaunt(cyc)=1 And RefActViable(cyc,pFoc(cyc))=0
   If pAnim(cyc)<40 Or (pAnim(cyc)=>48 And pAnim(cyc)=<49)
    issue=FindInteractions(cyc)
    If issue>0
     If issue=2
      InstantTurn(cyc,FindChild(world,"Ring0"+NearestCorner(pX#(cyc),pZ#(cyc))))
      ChangeAnim(cyc,703)
     Else
      v=pInteract(cyc)
      pOldFoc(cyc)=pFoc(cyc) : pFoc(cyc)=v
      InstantTurn(cyc,p(v))
      If issue=1 Or issue=3 Then ChangeAnim(cyc,177)
     EndIf
    Else
     anim=190
     If pControl(cyc)>0 And DirPressed(cyc) Then anim=191
     If pControl(cyc)=0 Then anim=Rnd(190,191)
     If pRole(cyc)=1 Then anim=Rnd(190,193)
     If pRole(cyc)=2 Then anim=Rnd(190,196)
     If matchState=4 And pTeam(cyc)=pTeam(matchWinner)
      randy=Rnd(0,1)
      If randy=0 Or matchTim<500 Then anim=193
     EndIf
     randy=Rnd(0,1)
     If randy=0 And matchState=4 And matchWinner>0 And pTeam(cyc)<>pTeam(matchWinner) And pRole(cyc)<>1 Then anim=Rnd(194,196)
     If pSpecial(cyc)>0 Then anim=192
     If pHolding(cyc)>0
      If weapName$(weapType(pHolding(cyc)))="Microphone" And matchState=>3 Then anim=197
      If weapName$(weapType(pHolding(cyc)))="Trophy" Then anim=198
      If weapFile$(weapType(pHolding(cyc)))="Belt"
       If weapWear(pHolding(cyc))=0
        anim=Rnd(216,217)
        If charBaggy(pChar(cyc),pCostume(cyc))=1 Or charBaggy(pChar(cyc),pCostume(cyc))=3 Then anim=216
        If pRole(cyc)=1 Or pRole(cyc)=2 Then anim=216
       EndIf
       randy=Rnd(0,1)
       If randy=0 And weapWear(pHolding(cyc))>0 And pSpecial(cyc)=0 Then anim=215+weapWear(pHolding(cyc))
      EndIf
     EndIf
     ChangeAnim(cyc,anim)
    EndIf
   EndIf
  EndIf
  ;purify pick-up command
  If cPickUp(cyc)=1
   If pAnim(cyc)=>300 Then cPickUpFree(cyc)=0
  Else
   cPickUpFree(cyc)=1
  EndIf
  ;item interaction
  If cPickUp(cyc)=1 And cPickUpFree(cyc)=1 And pCarrying(cyc)=0 And pAnim(cyc)<40
   ;forecast weapon pick-up
   picker=0
   If pHolding(cyc)=0
    v=NearestWeapon(cyc)
    range#=10+weapSize#(weapType(v))
    If weapY#(v)>pY#(cyc)+10 Then range#=range#+10
    If weapCarrier(v)>0 Then range#=20+(weapSize#(weapType(v))/2)
    If WeaponProximity(cyc,v,range#) Then picker=v
    If pControl(cyc)=0 And pAgenda(cyc)=21 And pWeapFoc(cyc)>0 Then picker=pWeapFoc(cyc)
   EndIf
   ;pick up item
   If pPlatform(cyc)=0 And picker=0
    For v=1 To no_items
     If ItemProximity(cyc,v,30) And pY#(cyc)>iY#(v)-10 And pY#(cyc)<iY#(v)+10 And iCarrier(v)=0 And iCarryAnim(iType(v),iState(v))=>0 And iAnim(v)=0 And pAnim(cyc)<40
      If ItemCollide(cyc,v,pX#(cyc),pZ#(cyc),2)
       InstantTurn(cyc,i(v))
       anim=200
       If pHolding(cyc)>0 And weapBurning(pHolding(cyc))>0 And (game=0 Or fedProduction(charFed(gamChar),9)>0)
        randy=Rnd(0,1)
        If randy=0 And pControl(cyc)=0 And pRole(cyc)<>1 And iFlammable(iType(v)) And iBurning(v)=0 Then anim=214
        If pControl(cyc)>0 And cRun(cyc)=1 Then anim=214
       EndIf
       ChangeAnim(cyc,anim)
      EndIf
     EndIf
    Next
   EndIf
   ;drop weapon
   If pHolding(cyc)>0 And pAnim(cyc)<40 And picker=0
    anim=212
    If weapWear(pHolding(cyc))>0 Then anim=217+weapWear(pHolding(cyc))
    ChangeAnim(cyc,anim)
   EndIf
   ;execute weapon pick-up
   v=picker
   If v=0 And pAnim(cyc)<40 Then v=NearestWeapon(cyc)
   If v>0 And weapCarrier(v)=0
    InstantTurn(cyc,weap(v))
    If weapY#(v)>pY#(cyc)+15 Then anim=211 Else anim=210
    If game=0 Or fedProduction(charFed(gamChar),9)>0
     randy=Rnd(0,15)
     If randy=0 And pControl(cyc)=0 And pRole(cyc)<>1 And weapFlammable(weapType(v)) And weapBurning(v)=0 Then anim=214
     If pControl(cyc)>0 And cRun(cyc)=1 Then anim=214
    EndIf
    ChangeAnim(cyc,anim)
   EndIf
   If v>0 And weapCarrier(v)>0 And matchState=>3
    InstantTurn(cyc,pLimb(weapCarrier(v),21))
    If weapY#(v)>pY#(cyc)+5 Then anim=211 Else anim=210
    ChangeAnim(cyc,anim)
   EndIf
  EndIf
  ;ring interaction
  FindClimbing(cyc)
 EndIf
End Function

;--------------------------------------------------------------
;////////////////// RELATED FUNCTIONS /////////////////////////
;--------------------------------------------------------------

;SWAP CONTROLS
Function SwapControls(cyc,v,style) ;0=naturally, 1=fiddled
 If pControl(cyc)=0 Or pControl(v)=0
  ;buy in if no humand
  If style=1 And matchPlayer=0 Then pControl(cyc)=3 : matchPlayer=cyc
  ;make transition
  temp=pControl(v)
  pControl(v)=pControl(cyc) : pControl(cyc)=temp
  If matchPlayer=cyc And pControl(v)>0 Then matchPlayer=v
  If matchPlayer=v And pControl(cyc)>0 Then matchPlayer=cyc
 EndIf
End Function

;RESET INPUT
Function ResetInput(cyc)
 ;directions
 cUp(cyc)=0 : cDown(cyc)=0
 cLeft(cyc)=0 : cRight(cyc)=0
 ;commands
 cAttack(cyc)=0 : cGrapple(cyc)=0
 cRun(cyc)=0 : cPickUp(cyc)=0
 cSwitch(cyc)=0 : cTaunt(cyc)=0
 cBlock(cyc)=0
End Function

;DIRECTION PRESSED?
Function DirPressed(cyc)
 value=0
 If cUp(cyc)=1 Or cDown(cyc)=1 Or cLeft(cyc)=1 Or cRight(cyc)=1 Then value=1
 Return value
End Function

;ACTION PRESSED?
Function ActionPressed(cyc)
 value=0
 If cAttack(cyc)=1 Or cRun(cyc)=1 Or cGrapple(cyc)=1 Or cPickUp(cyc)=1 Or cTaunt(cyc)=1 Then value=1
 Return value
End Function

;FIND RUN UP
Function FindRunUp(cyc)
 value=0
 If (pAnim(cyc)=12 And pAnimTim(cyc)=>5) Or pOldAnim(cyc)=44 Then value=1
 If pAnim(cyc)=30 And pDashA#(cyc)=pA#(cyc) Then value=1
 Return value
End Function

;EXPLORE KEY AREA
Function ExploreArea(area)
 If area=0 Then pTX#(cyc)=Rnd(-60,60) : pTZ#(cyc)=Rnd(-60,60) ;ring
 If area=1 Then pTX#(cyc)=Rnd(-135,135) : pTZ#(cyc)=Rnd(-135,135) ;outside of ring
 If area=2 Then pTX#(cyc)=Rnd(-18,18) : pTZ#(cyc)=Rnd(140,610) ;north aisle
 If area=3 Then pTX#(cyc)=Rnd(-18,18) : pTZ#(cyc)=Rnd(-610,-140) ;south aisle
 If area=4 Then pTX#(cyc)=Rnd(-250,250) : pTZ#(cyc)=Rnd(610,890) ;locker room
 If area=5 Then pTX#(cyc)=Rnd(-150,150) : pTZ#(cyc)=Rnd(-800,-610) ;lounge
End Function

;FIND DESIRED ANGLE
Function RequestAngle#(cyc)
 ;get orientation
 orient#=EntityYaw#(cam)
 If camType=>8 And camType=<9 And cyc=camFoc Then orient#=pA#(cyc)
 angle#=pA#(cyc)
 ;standard directions
 If cLeft(cyc)=1 Then angle#=orient#+90
 If cRight(cyc)=1 Then angle#=orient#+270
 If cUp(cyc)=1 Then angle#=orient#
 If cDown(cyc)=1 Then angle#=orient#+180
 ;diagonal angles
 If cLeft(cyc)=1 And cUp(cyc)=1 Then angle#=orient#+45
 If cLeft(cyc)=1 And cDown(cyc)=1 Then angle#=orient#+135
 If cRight(cyc)=1 And cUp(cyc)=1 Then angle#=orient#+315
 If cRight(cyc)=1 And cDown(cyc)=1 Then angle#=orient#+225
 angle#=CleanAngle#(angle#)
 Return angle#
End Function

;FIND REQUIRED DIRECTIONS
Function RequestDirection(cyc,angle#,hold)
 ;reset status
 value=0
 orient#=EntityYaw#(cam)
 If camType=>8 And camType=<9 And cyc=camFoc Then orient#=pA#(cyc)
 ;standard directions
 If SatisfiedAngle(angle#,CleanAngle#(orient#),45) Then cUpTim(cyc)=hold ;north
 If SatisfiedAngle(angle#,CleanAngle#(orient#+270),45) Then cRightTim(cyc)=hold ;east
 If SatisfiedAngle(angle#,CleanAngle#(orient#+180),45) Then cDownTim(cyc)=hold ;south
 If SatisfiedAngle(angle#,CleanAngle#(orient#+90),45) Then cLeftTim(cyc)=hold ;west
 ;diagonal directions
 If SatisfiedAngle(angle#,CleanAngle#(orient#+315),45) Then cRightTim(cyc)=hold : cUpTim(cyc)=hold ;north east
 If SatisfiedAngle(angle#,CleanAngle#(orient#+225),45) Then cRightTim(cyc)=hold : cDownTim(cyc)=hold ;south east
 If SatisfiedAngle(angle#,CleanAngle#(orient#+135),45) Then cLeftTim(cyc)=hold : cDownTim(cyc)=hold ;south west
 If SatisfiedAngle(angle#,CleanAngle#(orient#+45),45) Then cLeftTim(cyc)=hold : cUpTim(cyc)=hold ;north west
 Return value
End Function

;MOVING INTO BLOCK?
Function RequestBlock(cyc,block)
 value=0
 If DirPressed(cyc) Or cRun(cyc)=1
  ;prepare probe
  PositionEntity dummy,pX#(cyc),pY#(cyc),pZ#(cyc)
  RotateEntity dummy,0,RequestAngle#(cyc),0
  MoveEntity dummy,0,0,5
  checkX#=EntityX(dummy) : checkZ#=EntityZ(dummy)
  ;find match
  If checkX#=>blockX1#(block) And checkX#=<blockX2#(block) And pY#(cyc)=>blockY1#(block) And pY#(cyc)=<blockY2#(block) And checkZ#=>blockZ1#(block) And checkZ#=<blockZ2#(block)
   If (pX#(cyc)>blockX1#(block)+10 And pX#(cyc)<blockX2#(block)-10) Or (pZ#(cyc)>blockZ1#(block)+10 And pZ#(cyc)<blockZ2#(block)-10) Or (block=>23 And block=<26)
    value=1
    If blockClimb(block)=1 And pOldZ#(cyc)>blockZ1#(block) Then value=0
    If blockClimb(block)=2 And pOldX#(cyc)>blockX1#(block) Then value=0
    If blockClimb(block)=3 And pOldZ#(cyc)<blockZ2#(block) Then value=0
    If blockClimb(block)=4 And pOldX#(cyc)<blockX2#(block) Then value=0
   EndIf
  EndIf
 EndIf
 Return value
End Function

;MOVING INTO ITEM?
Function RequestItem(cyc,v)
 value=0
 If DirPressed(cyc) Or cRun(cyc)=1
  ;prepare probe
  PositionEntity dummy,pX#(cyc),pY#(cyc),pZ#(cyc)
  RotateEntity dummy,0,RequestAngle#(cyc),0
  MoveEntity dummy,0,0,5
  checkX#=EntityX(dummy) : checkZ#=EntityZ(dummy)
  ;find match
  If checkX#>iX#(v)-30 And checkX#<iX#(v)+30 And checkZ#>iZ#(v)-30 And checkZ#<iZ#(v)+30 And pY#(cyc)>iY#(v)-5 And pY#(cyc)<iY#(v)+10
   If ItemCollide(cyc,v,checkX#,checkZ#,0) Then value=1
  EndIf
 EndIf
 Return value
End Function

;CHANGE FOCUS (MANUALLY)
Function SwitchFocus(cyc)
 ;nearest enemy
 pOldFoc(cyc)=pFoc(cyc)
 If pFocTim(cyc)=0
  pFoc(cyc)=0 : hi#=100
  For v=1 To no_plays
   If cyc<>v And v<>pOldFoc(cyc) And pHidden(v)=0 And (pTeam(cyc)<>pTeam(v) Or pRole(cyc)=2 Or matchState=4) And (LegalMan(v) Or LegalMan(cyc)=0 Or pRole(v)=0 Or pChaosTim(v)>0 Or matchState=4)
    If pOutTim(v)>0 Or (pRole(v)=3 And matchState=>3)
     If GetDistance#(pX#(cyc),pZ#(cyc),RealX#(v),RealZ#(v))<hi# Then pFoc(cyc)=v : hi#=GetDistance#(pX#(cyc),pZ#(cyc),RealX#(v),RealZ#(v))
    EndIf
   EndIf
  Next
 EndIf
 ;browse all
 If pFocTim(cyc)>0 Or pFoc(cyc)=pOldFoc(cyc)
  Repeat
   satisfied=1
   pFoc(cyc)=pFoc(cyc)+1
   If pFoc(cyc)>no_plays Then pFoc(cyc)=0
   If pFoc(cyc)>0
    If pFoc(cyc)=cyc Or (pTeam(pFoc(cyc))=pTeam(cyc) And matchState<>4) Then satisfied=0
    If pOutTim(pFoc(cyc))=0 And (pRole(pFoc(cyc))<>3 Or matchState<3) Then satisfied=0
    If pHidden(pFoc(cyc))>0 Then satisfied=0
   EndIf
  Until satisfied=1
 EndIf
 pFocTim(cyc)=50
End Function

;GET NEW ENEMY
Function GetNewFoc(cyc)
 pOldFoc(cyc)=pFoc(cyc)
 its=0
 Repeat
  ;get ideal first choice
  satisfied=1
  pFoc(cyc)=Rnd(1,no_plays)
  If matchState<>0 And matchState<>3 Then pFoc(cyc)=Rnd(0,no_plays)
  randy=Rnd(0,1)
  If randy=0 And matchState=3 And pRole(cyc)=0 And matchLeader>0 Then pFoc(cyc)=matchLeader
  If pRole(cyc)=0
   For v=1 To no_wrestlers
    randy=Rnd(0,1)
    If randy=0 And charRelationship(pChar(cyc),pChar(v))<0 And pTeam(cyc)<>pTeam(v) And LegalMan(v) Then pFoc(cyc)=v
   Next
  EndIf
  randy=Rnd(0,1)
  If randy=0 And pRole(cyc)=2 And pClient(cyc)>0 And pChaosTim(cyc)=0 Then pFoc(cyc)=pClient(cyc)
  ;avoid friends
  randy=Rnd(0,1)
  If randy=0 And charRelationship(pChar(cyc),pChar(pFoc(cyc)))>0 And pRole(cyc)<>2 Then satisfied=0
  ;avoid irrelevances
  If matchState=0 Or matchState=3 Or matchEnter=0
   If pRole(cyc)=<1 And pRole(pFoc(cyc))<>0 Then satisfied=0 ;non-wrestlers
   If (LegalMan(cyc) Or pRole(cyc)=1) And LegalMan(pFoc(cyc))=0 Then satisfied=0 ;illegals
  EndIf
  ;avoid suicide!
  If pTeam(cyc)=pTeam(pFoc(cyc)) And charRelationship(pChar(cyc),pChar(pFoc(cyc)))=>0 And pRole(cyc)<>2 And matchState<>4 Then satisfied=0
  If pFoc(cyc)=cyc Then satisfied=0
  ;avoid unavailable
  If matchState>0
   randy=Rnd(0,2)
   If randy=0 And pFoc(cyc)>0 And InProximity(cyc,pFoc(cyc),100)=0 Then satisfied=0
   If randy=<1 And pFoc(cyc)>0 And InProximity(cyc,pFoc(cyc),200)=0 Then satisfied=0
   If pOutTim(pFoc(cyc))=0 Or pHidden(pFoc(cyc))>0 Then satisfied=0
  EndIf
  ;last resort
  its=its+1
  If its>100 Then pFoc(cyc)=0 : satisfied=1
 Until satisfied=1
End Function

;GET INTENSITY
Function GetIntensity(cyc)
 ;standard settings
 If optLevel=<1 Then value=1000
 If optLevel=2 Then value=600
 If optLevel=3 Then value=300
 If optLevel=4 Then value=150
 If optLevel=>5 Then value=50
 ;minimum performance for inter-promotionals
 If game=1 And gamSchedule(gamDate)=4 And value>100 Then value=100
 ;consider player status
 If matchPlayer=0 Or pRole(cyc)=1 Then value=50
 If matchPlayer>0 And pTeam(cyc)=pTeam(matchPlayer) Then value=50
 ;toned down for sparring
 If game=1 And screenAgenda=10 Then value=value*2
 Return value
End Function

;ASSESS THREAT OF ATTACK
Function FindThreat(cyc) ;1=potential high, 2=potential low, 11=active high, 12=active low
 threat=0
 v=pFoc(cyc)
 If v>0 And pFoc(v)=cyc
  If (pAnim(v)=>60 And pAnim(v)=<69) Or (pAnim(v)=>220 And pAnim(v)=<226) Or pAnim(v)=300
   If InLine(v,p(cyc),45)
    ;potential threat
    If pAnim(v)=61 Or pAnim(v)=65 Or pAnim(v)=69 Then threat=2 Else threat=1
    ;genuine threat
    active=1
    If pSting(v)=0 Then active=0
    If pAnim(v)=>60 And pAnim(v)=<63
     style=pAnim(v)-59
     If pAnimTim(v)>Int(attackExpire(style,charAttack(pChar(v),style))/pAnimSpeed#(v)) Then active=0
    EndIf
    If pAnim(v)=64 And pY#(v)=<pGround#(v) Then active=0
    If pAnim(v)=65 And pAnimTim(v)>Int(45/pAnimSpeed#(v)) Then active=0
    If pAnim(v)=69 And pAnimTim(v)>Int(40/pAnimSpeed#(v)) Then active=0
    If pAnim(v)=>220 And pAnim(v)=<226 And pAnimTim(v)>Int(70/pAnimSpeed#(v)) Then active=0
    If pAnim(v)=300 And pAnimTim(v)>Int(36/pAnimSpeed#(v)) Then active=0
    range#=RequiredRange#(v,pAnim(v))
    contact#=GetDistance#(pX#(v),pZ#(v),pX#(cyc),pZ#(cyc))
    If GetDistance#(pX#(v),pZ#(v),pLimbX#(cyc,1),pLimbZ#(cyc,1))<contact# Then contact#=GetDistance#(pX#(v),pZ#(v),pLimbX#(cyc,1),pLimbZ#(cyc,1))
    If (pY#(cyc)=>pY#(v)-10 And pY#(cyc)=<pY#(v)+10) Or pAnim(v)=64
     If active=1 And contact#<range# Then threat=threat+10
    EndIf
   EndIf
  EndIf
 EndIf
 Return threat
End Function

;IN PROXIMITY OF OTHER PLAYER?
Function InProximity(cyc,v,range#)
 value=0
 If RealX#(cyc)>RealX#(v)-range# And RealX#(cyc)<RealX#(v)+range# And RealZ#(cyc)>RealZ#(v)-range# And RealZ#(cyc)<RealZ#(v)+range# Then value=1
 Return value
End Function

;FRONT IN LINE WITH ENTITY?
Function InLine(cyc,entity,range)
 value=0
 If entity>0
  ;get target angle
  PointEntity p(cyc),entity
  tA#=CleanAngle#(EntityYaw(p(cyc)))
  RotateEntity p(cyc),0,pA#(cyc),0
  ;find match
  If SatisfiedAngle(pA#(cyc),tA#,range) Then value=1
 EndIf
 Return value
End Function

;REAR IN LINE WITH ENTITY?
Function InRearView(cyc,entity,range)
 value=0
 If entity>0
  ;get target angle
  PointEntity p(cyc),entity
  tA#=CleanAngle#(EntityYaw(p(cyc))+180)
  RotateEntity p(cyc),0,pA#(cyc),0
  ;find match
  If SatisfiedAngle(pA#(cyc),tA#,range) Then value=1
 EndIf
 Return value
End Function

;ISOLATED FROM AGGRESSORS?
Function Isolated(cyc,range#)
 value=1
 For v=1 To no_plays
  If cyc<>v And pTeam(v)<>pTeam(cyc) And pRole(v)<>1 And pFoc(v)=cyc And AttackViable(v)=1
   If InProximity(cyc,v,range#) Then value=0
  EndIf
 Next
 Return value
End Function

;INSIDE RING?
Function InsideRing(x#,z#,offset#)
 value=0
 If x#>blockX1#(0)-offset# And x#<blockX2#(0)+offset# And z#>blockZ1#(0)-offset# And z#<blockZ2#(0)+offset#
  value=1
 EndIf
 Return value
End Function

;ALLOWED INSIDE RING?
Function RingViable(cyc)
 viable=1
 If matchState=3
  If pRole(cyc)=0 And LegalMan(cyc)=0 And pChaosTim(cyc)=0 Then viable=0
  If pRole(cyc)=2 Or pEliminated(cyc)
   If pChaosTim(cyc)=0 Or matchCountOuts=3 Then viable=0
  EndIf
 EndIf
 Return viable
End Function

;BEHIND RAILINGS?
Function BehindRailings(x#,z#)
 value=0
 If z#>142 And z#<395
  If x#>28 Then value=1 ;north east segment
  If x#<-28 Then value=1 ;north west segment
 EndIf
 If z#>-142 And z#<142
  If x#>141 Then value=1 ;east segment
  If x#<-141 Then value=1 ;west segment
 EndIf
 If z#>-395 And z#<-142
  If x#>28 Then value=1 ;south east segment
  If x#<-28 Then value=1 ;south west segment
 EndIf
 Return value
End Function

;FIND CLIMBING
Function FindClimbing(cyc)
 ;CLIMBABLE BLOCKS
 If pPlatform(cyc)=0 And pAnim(cyc)=>11 And pAnim(cyc)=<12
  For v=0 To 100
   If blockClimb(v)>0 And pPlatform(cyc)=0
    If RequestBlock(cyc,v)
     If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2
     If pFriction(cyc)>20
      pPlatformX#(cyc)=GetCentre#(blockPlatX1#(v),blockPlatX2#(v))
      If blockPlatZ1#(v)=blockPlatZ2#(v) And blockPlatX1#(v)<>blockPlatX2#(v) And pX#(cyc)>blockX1#(v)+10 And pX#(cyc)<blockX2#(v)-10 Then pPlatformX#(cyc)=pX#(cyc)
      If blockClimb(v)=1 Or blockClimb(v)=3 Then pPlatformX#(cyc)=pX#(cyc)
      pPlatformZ#(cyc)=GetCentre#(blockPlatZ1#(v),blockPlatZ2#(v))
      If blockPlatX1#(v)=blockPlatX2#(v) And blockPlatZ1#(v)<>blockPlatZ2#(v) And pZ#(cyc)>blockZ1#(v)+10 And pZ#(cyc)<blockZ2#(v)-10 Then pPlatformZ#(cyc)=pZ#(cyc)
      If blockClimb(v)=2 Or blockClimb(v)=4 Then pPlatformZ#(cyc)=pZ#(cyc)
      pPlatformY#(cyc)=blockY2#(v)
      If PlatformClear(0,pPlatformX#(cyc),pPlatformZ#(cyc))
       PositionEntity dummy,pPlatformX#(cyc),pY#(cyc),pPlatformZ#(cyc)
       InstantTurn(cyc,dummy)
       If pPlatformY#(cyc)-pY#(cyc)>40 And blockClimb(v)=>1 And blockClimb(v)=<4
        If blockClimb(v)=1 Then pA#(cyc)=0
        If blockClimb(v)=2 Then pA#(cyc)=270
        If blockClimb(v)=3 Then pA#(cyc)=180
        If blockClimb(v)=4 Then pA#(cyc)=90
        anim=55
       Else
        anim=53
       EndIf
       ChangeAnim(cyc,anim)
       pPlatform(cyc)=v+10
      EndIf
     EndIf
    EndIf
   EndIf
  Next
 EndIf
 ;CLIMB OFF BLOCKS
 If pPlatform(cyc)=>10 And pPlatform(cyc)=<90 And pAnim(cyc)=>11 And pAnim(cyc)=<12
  If FlightClear(cyc,RequestAngle#(cyc),20)
   If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15
    block=pPlatform(cyc)-10
    If pPlatformY#(cyc)-wGround#>40 And blockClimb(block)=>1 And blockClimb(block)=<4
     If blockClimb(block)=1 Then pA#(cyc)=180
     If blockClimb(block)=2 Then pA#(cyc)=90
     If blockClimb(block)=3 Then pA#(cyc)=0
     If blockClimb(block)=4 Then pA#(cyc)=270
     ChangeAnim(cyc,56)
    Else
     pA#(cyc)=RequestAngle#(cyc)
     ChangeAnim(cyc,54)
    EndIf
   EndIf
  EndIf
 EndIf
 ;CLIMB OFF ITEMS
 If pPlatform(cyc)=>100 And pAnim(cyc)=>11 And pAnim(cyc)=<12
  If FlightClear(cyc,RequestAngle#(cyc),20)
   If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 Then pA#(cyc)=RequestAngle#(cyc) : ChangeAnim(cyc,54)
  EndIf
 EndIf
 ;CLIMB UP TURNBUCKLES FROM APRON
 accessRange=45
 If pPlatform(cyc)=>1 And pPlatform(cyc)=<4 And pAnim(cyc)=>11 And pAnim(cyc)=<12
  ;north side to north west buckle
  If pPlatform(cyc)=1 And pX#(cyc)<blockX1#(0)+35 And SatisfiedAngle(RequestAngle#(cyc),90,accessRange)
   If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 And PlatformClear(5,0,0) Then pPlatform(cyc)=5 : pX#(cyc)=-64 : pA#(cyc)=180 : ChangeAnim(cyc,45)
  EndIf
  ;east side to north east buckle
  If pPlatform(cyc)=2 And pZ#(cyc)>blockZ2#(0)-35 And SatisfiedAngle(RequestAngle#(cyc),0,accessRange)
   If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 And PlatformClear(6,0,0) Then pPlatform(cyc)=6 : pZ#(cyc)=64 : pA#(cyc)=90 : ChangeAnim(cyc,45)
  EndIf
  ;south side to south east buckle
  If pPlatform(cyc)=3 And pX#(cyc)>blockX2#(0)-35 And SatisfiedAngle(RequestAngle#(cyc),270,accessRange)
   If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 And PlatformClear(7,0,0) Then pPlatform(cyc)=7 : pX#(cyc)=64 : pA#(cyc)=0 : ChangeAnim(cyc,45)
  EndIf
  ;west side to south west buckle
  If pPlatform(cyc)=4 And pZ#(cyc)<blockZ1#(0)+35 And SatisfiedAngle(RequestAngle#(cyc),180,accessRange)
   If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 And PlatformClear(8,0,0) Then pPlatform(cyc)=8 : pZ#(cyc)=-64 : pA#(cyc)=270 : ChangeAnim(cyc,45)
  EndIf
  ;north side to north east buckle
  If pPlatform(cyc)=1 And pX#(cyc)>blockX2#(0)-35 And SatisfiedAngle(RequestAngle#(cyc),270,accessRange)
   If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 And PlatformClear(6,0,0) Then pPlatform(cyc)=6 : pX#(cyc)=64 : pA#(cyc)=180 : ChangeAnim(cyc,46)
  EndIf
  ;east side to south east buckle
  If pPlatform(cyc)=2 And pZ#(cyc)<blockZ1#(0)+35 And SatisfiedAngle(RequestAngle#(cyc),180,accessRange)
   If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 And PlatformClear(7,0,0) Then pPlatform(cyc)=7 : pZ#(cyc)=-64 : pA#(cyc)=90 : ChangeAnim(cyc,46)
  EndIf
  ;south side to south west buckle
  If pPlatform(cyc)=3 And pX#(cyc)<blockX1#(0)+35 And SatisfiedAngle(RequestAngle#(cyc),90,accessRange)
   If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 And PlatformClear(8,0,0) Then pPlatform(cyc)=8 : pX#(cyc)=-64 : pA#(cyc)=0 : ChangeAnim(cyc,46)
  EndIf
  ;west side to north west buckle
  If pPlatform(cyc)=4 And pZ#(cyc)>blockZ2#(0)-35 And SatisfiedAngle(RequestAngle#(cyc),0,accessRange)
   If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 And PlatformClear(5,0,0) Then pPlatform(cyc)=5 : pZ#(cyc)=64 : pA#(cyc)=270 : ChangeAnim(cyc,46)
  EndIf
 EndIf
 ;CLIMB UP TURNBUCKLES FROM INSIDE
 accessRange=45
 If matchState=3 And matchTeams=2 And LegalMan(cyc) Then accessRange=30
 If TryingToTag(cyc)=0 And InsideRing(pX#(cyc),pZ#(cyc),-15) And pY#(cyc)=wStage# And pPlatform(cyc)=0 And pAnim(cyc)=>11 And pAnim(cyc)=<12
  ;north west
  If pX#(cyc)<blockX1#(0)+35 And pZ#(cyc)>blockZ2#(0)-35 And SatisfiedAngle(RequestAngle#(cyc),45,accessRange)
   If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>20 And PlatformClear(5,0,0) Then pPlatform(cyc)=5 : pX#(cyc)=-64 : pZ#(cyc)=64 : pA#(cyc)=45 : ChangeAnim(cyc,47)
  EndIf
  ;north east
  If pX#(cyc)>blockX2#(0)-35 And pZ#(cyc)>blockZ2#(0)-35 And SatisfiedAngle(RequestAngle#(cyc),315,accessRange)
   If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>20 And PlatformClear(6,0,0) Then pPlatform(cyc)=6 : pX#(cyc)=64 : pZ#(cyc)=64 : pA#(cyc)=315 : ChangeAnim(cyc,47)
  EndIf
  ;south east
  If pX#(cyc)>blockX2#(0)-35 And pZ#(cyc)<blockZ1#(0)+35 And SatisfiedAngle(RequestAngle#(cyc),225,accessRange)
   If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>20 And PlatformClear(7,0,0) Then pPlatform(cyc)=7 : pX#(cyc)=64 : pZ#(cyc)=-64 : pA#(cyc)=225 : ChangeAnim(cyc,47)
  EndIf
  ;south west
  If pX#(cyc)<blockX1#(0)+35 And pZ#(cyc)<blockZ1#(0)+35 And SatisfiedAngle(RequestAngle#(cyc),135,accessRange)
   If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>20 And PlatformClear(8,0,0) Then pPlatform(cyc)=8 : pX#(cyc)=-64 : pZ#(cyc)=-64 : pA#(cyc)=135 : ChangeAnim(cyc,47)
  EndIf
 EndIf
 ;CLIMB DOWN FROM TURNBUCKLES
 accessRange=45
 If pPlatform(cyc)=>5 And pPlatform(cyc)=<8 And pAnim(cyc)=>48 And pAnim(cyc)=<49 And DirPressed(cyc)
  ;north west buckle to west apron
  If pPlatform(cyc)=5 And SatisfiedAngle(RequestAngle#(cyc),112,67)
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 Then pPlatform(cyc)=4 : pX#(cyc)=-80 : pY#(cyc)=wStage# : pZ#(cyc)=64 : pA#(cyc)=270 : ChangeAnim(cyc,51)
  EndIf
  ;north west buckle to north apron
  If pPlatform(cyc)=5 And SatisfiedAngle(RequestAngle#(cyc),337,67)
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 Then pPlatform(cyc)=1 : pX#(cyc)=-64 : pY#(cyc)=wStage# : pZ#(cyc)=80 : pA#(cyc)=180 : ChangeAnim(cyc,50)
  EndIf
  ;north west buckle to inside
  If pPlatform(cyc)=5 And SatisfiedAngle(RequestAngle#(cyc),225,45) And RingViable(cyc)
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 Then pPlatform(cyc)=0 : pX#(cyc)=-63 : pY#(cyc)=wStage# : pZ#(cyc)=63 : pA#(cyc)=45 : ChangeAnim(cyc,52)
  EndIf
  ;north east buckle to north apron
  If pPlatform(cyc)=6 And SatisfiedAngle(RequestAngle#(cyc),23,67)
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 Then pPlatform(cyc)=1 : pX#(cyc)=64 : pY#(cyc)=wStage# : pZ#(cyc)=80 : pA#(cyc)=180 : ChangeAnim(cyc,51)
  EndIf
  ;north east buckle to east apron
  If pPlatform(cyc)=6 And SatisfiedAngle(RequestAngle#(cyc),247,67)
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 Then pPlatform(cyc)=2 : pX#(cyc)=80 : pY#(cyc)=wStage# : pZ#(cyc)=64 : pA#(cyc)=90 : ChangeAnim(cyc,50)
  EndIf
  ;north east buckle to inside
  If pPlatform(cyc)=6 And SatisfiedAngle(RequestAngle#(cyc),135,45) And RingViable(cyc)
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 Then pPlatform(cyc)=0 : pX#(cyc)=63 : pY#(cyc)=wStage# : pZ#(cyc)=63 : pA#(cyc)=315 : ChangeAnim(cyc,52)
  EndIf
  ;south east buckle to east apron
  If pPlatform(cyc)=7 And SatisfiedAngle(RequestAngle#(cyc),293,67)
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 Then pPlatform(cyc)=2 : pX#(cyc)=80 : pY#(cyc)=wStage# : pZ#(cyc)=-64 : pA#(cyc)=90 : ChangeAnim(cyc,51)
  EndIf
  ;south east buckle to south apron
  If pPlatform(cyc)=7 And SatisfiedAngle(RequestAngle#(cyc),157,67)
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 Then pPlatform(cyc)=3 : pX#(cyc)=64 : pY#(cyc)=wStage# : pZ#(cyc)=-80 : pA#(cyc)=0 : ChangeAnim(cyc,50)
  EndIf
  ;south east buckle to inside
  If pPlatform(cyc)=7 And SatisfiedAngle(RequestAngle#(cyc),45,45) And RingViable(cyc)
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 Then pPlatform(cyc)=0 : pX#(cyc)=63 : pY#(cyc)=wStage# : pZ#(cyc)=-63 : pA#(cyc)=225 : ChangeAnim(cyc,52)
  EndIf
  ;south west buckle to south apron
  If pPlatform(cyc)=8 And SatisfiedAngle(RequestAngle#(cyc),203,67)
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 Then pPlatform(cyc)=3 : pX#(cyc)=-64 : pY#(cyc)=wStage# : pZ#(cyc)=-80 : pA#(cyc)=0 : ChangeAnim(cyc,51)
  EndIf
  ;south west buckle to west apron
  If pPlatform(cyc)=8 And SatisfiedAngle(RequestAngle#(cyc),67,67)
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 Then pPlatform(cyc)=4 : pX#(cyc)=-80 : pY#(cyc)=wStage# : pZ#(cyc)=-64 : pA#(cyc)=270 : ChangeAnim(cyc,50)
  EndIf
  ;south west buckle to inside
  If pPlatform(cyc)=8 And SatisfiedAngle(RequestAngle#(cyc),315,45) And RingViable(cyc)
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 Then pPlatform(cyc)=0 : pX#(cyc)=-63 : pY#(cyc)=wStage# : pZ#(cyc)=-63 : pA#(cyc)=135 : ChangeAnim(cyc,52)
  EndIf
 EndIf
 ;CLIMBING UP TO APRON
 accessRange=60
 blocked=0
 If matchState=3 And matchCountOuts=3
  If pRole(cyc)=2 Or pEliminated(cyc) Then blocked=1
 EndIf
 If blocked=0 And matchCage=0 And InsideRing(pX#(cyc),pZ#(cyc),2) And pY#(cyc)=wGround# And pPlatform(cyc)=0 And ((pAnim(cyc)=>11 And pAnim(cyc)=<12) Or pAnim(cyc)=202)
  ;north side
  If SatisfiedAngle(RequestAngle#(cyc),180,accessRange) And pZ#(cyc)=>blockZ2#(0) And pX#(cyc)>blockX1#(0)+30 And pX#(cyc)<blockX2#(0)-30
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 And HumanClear(cyc,180,15) And ItemClear(cyc,180,15)
    If pCarrying(cyc)=0 Then pPlatform(cyc)=1 : pA#(cyc)=180 : ChangeAnim(cyc,40)
    If pCarrying(cyc)>0 Then pPlatform(cyc)=1 : ChangeAnim(cyc,204)
   EndIf
  EndIf
  ;east side
  If SatisfiedAngle(RequestAngle#(cyc),90,accessRange) And pX#(cyc)=>blockX2#(0) And pZ#(cyc)>blockZ1#(0)+30 And pZ#(cyc)<blockZ2#(0)-30
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 And HumanClear(cyc,90,15) And ItemClear(cyc,90,15)
    If pCarrying(cyc)=0 Then pPlatform(cyc)=2 : pA#(cyc)=90 : ChangeAnim(cyc,40)
    If pCarrying(cyc)>0 Then pPlatform(cyc)=2 : ChangeAnim(cyc,204)
   EndIf
  EndIf
  ;south side
  If SatisfiedAngle(RequestAngle#(cyc),0,accessRange) And pZ#(cyc)=<blockZ1#(0) And pX#(cyc)>blockX1#(0)+30 And pX#(cyc)<blockX2#(0)-30
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 And HumanClear(cyc,0,15) And ItemClear(cyc,0,15)
    If pCarrying(cyc)=0 Then pPlatform(cyc)=3 : pA#(cyc)=0 : ChangeAnim(cyc,40)
    If pCarrying(cyc)>0 Then pPlatform(cyc)=3 : ChangeAnim(cyc,204)
   EndIf
  EndIf
  ;west side
  If SatisfiedAngle(RequestAngle#(cyc),270,accessRange) And pX#(cyc)=<blockX1#(0) And pZ#(cyc)>blockZ1#(0)+30 And pZ#(cyc)<blockZ2#(0)-30
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 And HumanClear(cyc,270,15) And ItemClear(cyc,270,15)
    If pCarrying(cyc)=0 Then pPlatform(cyc)=4 : pA#(cyc)=270 : ChangeAnim(cyc,40)
    If pCarrying(cyc)>0 Then pPlatform(cyc)=4 : ChangeAnim(cyc,204)
   EndIf
  EndIf
 EndIf
 ;CLIMB IN FROM APRON
 blocked=0
 If RingViable(cyc)=0 Then blocked=1
 If blocked=0 And pPlatform(cyc)=>1 And pPlatform(cyc)=<4 And ((pAnim(cyc)=>11 And pAnim(cyc)=<12) Or pAnim(cyc)=202)
  ;north ropes
  If pPlatform(cyc)=1 And SatisfiedAngle(RequestAngle#(cyc),180,accessRange) And pX#(cyc)>blockX1#(0)+30 And pX#(cyc)<blockX2#(0)-30
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 And HumanClear(cyc,180,15) And ItemClear(cyc,180,15) Then pA#(cyc)=180 : ChangeAnim(cyc,41)
  EndIf
  ;east ropes
  If pPlatform(cyc)=2 And SatisfiedAngle(RequestAngle#(cyc),90,accessRange) And pZ#(cyc)>blockZ1#(0)+30 And pZ#(cyc)<blockZ2#(0)-30
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 And HumanClear(cyc,90,15) And ItemClear(cyc,90,15) Then pA#(cyc)=90 : ChangeAnim(cyc,41)
  EndIf
  ;south ropes
  If pPlatform(cyc)=3 And SatisfiedAngle(RequestAngle#(cyc),0,accessRange) And pX#(cyc)>blockX1#(0)+30 And pX#(cyc)<blockX2#(0)-30
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 And HumanClear(cyc,0,15) And ItemClear(cyc,0,15) Then pA#(cyc)=0 : ChangeAnim(cyc,41)
  EndIf
  ;west ropes
  If pPlatform(cyc)=4 And SatisfiedAngle(RequestAngle#(cyc),270,accessRange) And pZ#(cyc)>blockZ1#(0)+30 And pZ#(cyc)<blockZ2#(0)-30
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 And HumanClear(cyc,270,15) And ItemClear(cyc,270,15) Then pA#(cyc)=270 : ChangeAnim(cyc,41)
  EndIf
 EndIf
 ;CLIMB OUT TO APRON
 If TryingToTag(cyc)=0 And InsideRing(pX#(cyc),pZ#(cyc),-15) And pY#(cyc)=wStage# And pPlatform(cyc)=0 And ((pAnim(cyc)=>11 And pAnim(cyc)=<12) Or pAnim(cyc)=202)
  ;north ropes
  If SatisfiedAngle(RequestAngle#(cyc),0,accessRange) And pX#(cyc)>blockX1#(1)+30 And pX#(cyc)<blockX2#(1)-30 And pZ#(cyc)>blockZ1#(1)-2 And pZ#(cyc)<blockZ1#(1)+2
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>20 And HumanClear(cyc,0,15) And ItemClear(cyc,0,15)
    If pCarrying(cyc)=0 Then pPlatform(cyc)=1 : pA#(cyc)=0 : ChangeAnim(cyc,42)
    If pCarrying(cyc)>0 Then pPlatform(cyc)=1 : ChangeAnim(cyc,204)
   EndIf
  EndIf
  ;east ropes
  If SatisfiedAngle(RequestAngle#(cyc),270,accessRange) And pX#(cyc)>blockX1#(2)-2 And pX#(cyc)<blockX1#(2)+2 And pZ#(cyc)>blockZ1#(2)+30 And pZ#(cyc)<blockZ2#(2)-30
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>20 And HumanClear(cyc,270,15) And ItemClear(cyc,270,15)
    If pCarrying(cyc)=0 Then pPlatform(cyc)=2 : pA#(cyc)=270 : ChangeAnim(cyc,42)
    If pCarrying(cyc)>0 Then pPlatform(cyc)=2 : ChangeAnim(cyc,204)
   EndIf
  EndIf
  ;south ropes
  If SatisfiedAngle(RequestAngle#(cyc),180,accessRange) And pX#(cyc)>blockX1#(3)+30 And pX#(cyc)<blockX2#(3)-30 And pZ#(cyc)>blockZ2#(3)-2 And pZ#(cyc)<blockZ2#(3)+2
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>20 And HumanClear(cyc,180,15) And ItemClear(cyc,180,15)
    If pCarrying(cyc)=0 Then pPlatform(cyc)=3 : pA#(cyc)=180 : ChangeAnim(cyc,42)
    If pCarrying(cyc)>0 Then pPlatform(cyc)=3 : ChangeAnim(cyc,204)
   EndIf
  EndIf
  ;west ropes
  If SatisfiedAngle(RequestAngle#(cyc),90,accessRange) And pX#(cyc)>blockX2#(4)-2 And pX#(cyc)<blockX2#(4)+2 And pZ#(cyc)>blockZ1#(4)+30 And pZ#(cyc)<blockZ2#(4)-30
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>20 And HumanClear(cyc,90,15) And ItemClear(cyc,90,15)
    If pCarrying(cyc)=0 Then pPlatform(cyc)=4 : pA#(cyc)=90 : ChangeAnim(cyc,42)
    If pCarrying(cyc)>0 Then pPlatform(cyc)=4 : ChangeAnim(cyc,204)
   EndIf
  EndIf
 EndIf
 ;CLIMB DOWN FROM APRON
 blocked=0
 If matchState=3 And matchCountOuts=3 And LegalMan(cyc) Then blocked=1
 If blocked=0 And matchCage=0 And pPlatform(cyc)=>1 And pPlatform(cyc)=<4 And ((pAnim(cyc)=>11 And pAnim(cyc)=<12) Or pAnim(cyc)=202)
  ;north side
  If pPlatform(cyc)=1 And SatisfiedAngle(RequestAngle#(cyc),0,accessRange)
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 Then pA#(cyc)=0 : ChangeAnim(cyc,43)
  EndIf
  ;east side
  If pPlatform(cyc)=2 And SatisfiedAngle(RequestAngle#(cyc),270,accessRange)
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 Then pA#(cyc)=270 : ChangeAnim(cyc,43)
  EndIf
  ;south side
  If pPlatform(cyc)=3 And SatisfiedAngle(RequestAngle#(cyc),180,accessRange)
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 Then pA#(cyc)=180 : ChangeAnim(cyc,43)
  EndIf
  ;west side
  If pPlatform(cyc)=4 And SatisfiedAngle(RequestAngle#(cyc),90,accessRange)
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 Then pA#(cyc)=90 : ChangeAnim(cyc,43)
  EndIf
 EndIf
 ;CLIMB ONTO INSIDE WALLS OF CAGE
 accessRange=60
 blocked=0
 If matchState=<2 And pRole(cyc)=0 Then blocked=1
 If blocked=0 And matchCage>0 And pPlatform(cyc)=>1 And pPlatform(cyc)=<4 And pAnim(cyc)=>11 And pAnim(cyc)=<12
  ;north side
  If pPlatform(cyc)=1 And SatisfiedAngle(RequestAngle#(cyc),0,accessRange)
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 Then pPlatform(cyc)=91 : pA#(cyc)=0 : ChangeAnim(cyc,230)
  EndIf
  ;east side
  If pPlatform(cyc)=2 And SatisfiedAngle(RequestAngle#(cyc),270,accessRange)
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 Then pPlatform(cyc)=92 : pA#(cyc)=270 : ChangeAnim(cyc,230)
  EndIf
  ;south side
  If pPlatform(cyc)=3 And SatisfiedAngle(RequestAngle#(cyc),180,accessRange)
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 Then pPlatform(cyc)=93 : pA#(cyc)=180 : ChangeAnim(cyc,230)
  EndIf
  ;west side
  If pPlatform(cyc)=4 And SatisfiedAngle(RequestAngle#(cyc),90,accessRange)
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>15 Then pPlatform(cyc)=94 : pA#(cyc)=90 : ChangeAnim(cyc,230)
  EndIf
 EndIf
 ;CLIMB ONTO OUTSIDE WALLS OF CAGE
 blocked=0
 If matchState=3 And (pEliminated(cyc) Or pRole(cyc)=2) Then blocked=1
 If matchCage>0 And blocked=0 And InsideRing(pX#(cyc),pZ#(cyc),2) And pY#(cyc)=wGround# And pPlatform(cyc)=0 And pAnim(cyc)=>11 And pAnim(cyc)=<12
  ;north side
  If SatisfiedAngle(RequestAngle#(cyc),180,accessRange) And pZ#(cyc)=>blockZ2#(0) And pX#(cyc)>blockX1#(0)+30 And pX#(cyc)<blockX2#(0)-30
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>20 Then pPlatform(cyc)=95 : pA#(cyc)=180 : ChangeAnim(cyc,230)
  EndIf
  ;east side
  If SatisfiedAngle(RequestAngle#(cyc),90,accessRange) And pX#(cyc)=>blockX2#(0) And pZ#(cyc)>blockZ1#(0)+30 And pZ#(cyc)<blockZ2#(0)-30
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>20 Then pPlatform(cyc)=96 : pA#(cyc)=90 : ChangeAnim(cyc,230)
  EndIf
  ;south side
  If SatisfiedAngle(RequestAngle#(cyc),0,accessRange) And pZ#(cyc)=<blockZ1#(0) And pX#(cyc)>blockX1#(0)+30 And pX#(cyc)<blockX2#(0)-30
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>20 Then pPlatform(cyc)=97 : pA#(cyc)=0 : ChangeAnim(cyc,230)
  EndIf
  ;west side
  If SatisfiedAngle(RequestAngle#(cyc),270,accessRange) And pX#(cyc)=<blockX1#(0) And pZ#(cyc)>blockZ1#(0)+30 And pZ#(cyc)<blockZ2#(0)-30
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>20 Then pPlatform(cyc)=98 : pA#(cyc)=270 : ChangeAnim(cyc,230)
  EndIf
 EndIf
 ;FIND TAGS
 accessRange=60
 If matchTeams=2 And LegalMan(cyc) And InsideRing(pX#(cyc),pZ#(cyc),-15) And pPlatform(cyc)=0 And pY#(cyc)=wStage# And pAnim(cyc)=>11 And pAnim(cyc)=<12
  tagger=0
  ;north ropes
  If SatisfiedAngle(RequestAngle#(cyc),0,accessRange) And pZ#(cyc)>blockZ1#(1)-2 And pZ#(cyc)<blockZ1#(1)+2
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>10 Or (pControl(cyc)=0 And TryingToTag(cyc)) Then tagger=1
  EndIf
  ;east ropes
  If SatisfiedAngle(RequestAngle#(cyc),270,accessRange) And pX#(cyc)>blockX1#(2)-2 And pX#(cyc)<blockX1#(2)+2
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>10 Or (pControl(cyc)=0 And TryingToTag(cyc)) Then tagger=2
  EndIf
  ;south ropes
  If SatisfiedAngle(RequestAngle#(cyc),180,accessRange) And pZ#(cyc)>blockZ2#(3)-2 And pZ#(cyc)<blockZ2#(3)+2
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>10 Or (pControl(cyc)=0 And TryingToTag(cyc)) Then tagger=3
  EndIf
  ;west ropes
  If SatisfiedAngle(RequestAngle#(cyc),90,accessRange) And pX#(cyc)>blockX2#(4)-2 And pX#(cyc)<blockX2#(4)+2
   pFriction(cyc)=pFriction(cyc)+2
   If pFriction(cyc)>10 Or (pControl(cyc)=0 And TryingToTag(cyc)) Then tagger=4
  EndIf
  ;translate into tag?
  If tagger>0
   PositionEntity dummy,pX#(cyc),pY#(cyc),pZ#(cyc)
   If tagger=1 Then RotateEntity dummy,0,0,0
   If tagger=2 Then RotateEntity dummy,0,270,0
   If tagger=3 Then RotateEntity dummy,0,180,0
   If tagger=4 Then RotateEntity dummy,0,90,0
   MoveEntity dummy,0,0,10
   checkX#=EntityX(dummy) : checkZ#=EntityZ(dummy)
   For count=1 To 2
    For v=1 To no_wrestlers
     cpuBlock=0
     If pControl(cyc)=0 And pFoc(cyc)>0 And pTeam(pFoc(cyc))=pTeam(cyc) And v<>pFoc(cyc) Then cpuBlock=1
     If cpuBlock=0 And cyc<>v And pTeam(cyc)=pTeam(v) And pRole(v)=0 And pPlatform(v)=tagger
      If count=2 Then range#=16 Else range#=8
      If TagProximity(v,80) And checkX#>pX#(v)-range# And checkX#<pX#(v)+range# And checkZ#>pZ#(v)-range# And checkZ#<pZ#(v)+range#
       If pAnim(v)<30 Or (pAnim(v)=>190 And pAnim(v)=<199)
        InstantTurn(cyc,p(v)) : ChangeAnim(cyc,704)
        InstantTurn(v,p(cyc)) : ChangeAnim(v,705)
       EndIf
      EndIf
     EndIf
    Next
    If pAnim(cyc)=704 Then Exit
   Next
  EndIf
 EndIf
End Function

;PLATFORM CLEAR?
Function PlatformClear(platform,targetX#,targetZ#)
 clear=1
 For v=1 To no_plays
  If cyc<>v
   If platform>0 And pPlatform(v)=platform Then clear=0
   If platform=0 And pPlatform(v)>0 And ReachedCord(pX#(v),pZ#(v),targetX#,targetZ#,10) Then clear=0
  EndIf
 Next
 Return clear
End Function

;PASSAGE CLEAR OF HUMANS?
Function HumanClear(cyc,angle#,range#)
 ;prepare probe
 PositionEntity dummy,pX#(cyc),pY#(cyc),pZ#(cyc)
 RotateEntity dummy,0,angle#,0
 MoveEntity dummy,0,0,range#
 checkX#=EntityX(dummy) : checkZ#=EntityZ(dummy)
 ;find conflicts
 clear=1
 For v=1 To no_plays
  If cyc<>v And AttackViable(v)<>4
   If checkX#>pX#(v)-10 And checkX#<pX#(v)+10 And checkZ#>pZ#(v)-10 And checkZ#<pZ#(v)+10 Then clear=0
  EndIf
 Next
 Return clear
End Function

;PASSAGE CLEAR OF ITEMS?
Function ItemClear(cyc,angle#,range#)
 ;prepare probe
 PositionEntity dummy,pX#(cyc),pY#(cyc),pZ#(cyc)
 RotateEntity dummy,0,angle#,0
 MoveEntity dummy,0,0,range#
 checkX#=EntityX(dummy) : checkZ#=EntityZ(dummy)
 ;find conflicts
 clear=1
 For v=1 To no_items
  If checkX#>iX#(v)-30 And checkX#<iX#(v)+30 And checkZ#>iZ#(v)-30 And checkZ#<iZ#(v)+30 And pY#(cyc)>iY#(v)-5 And pY#(cyc)<iY#(v)+10 And iState(v)=0
   If ItemCollide(cyc,v,checkX#,checkZ#,0) And v<>pPlatform(cyc)-100 Then clear=0
  EndIf
 Next
 Return clear
End Function

;PASSAGE CLEAR OF BLOCKS?
Function BlockClear(cyc,angle#,range#)
 ;prepare probe
 PositionEntity dummy,pX#(cyc),pY#(cyc),pZ#(cyc)
 RotateEntity dummy,0,angle#,0
 ;find conflicts
 clear=1
 For depth=1 To 3
  MoveEntity dummy,0,0,range#/3
  checkX#=EntityX(dummy) : checkZ#=EntityZ(dummy)
  For v=0 To 100
   If pY#(cyc)=>blockY1#(v) And pY#(cyc)=<blockY2#(v)
    If checkX#=>blockX1#(v) And checkX#=<blockX2#(v) And checkZ#=>blockZ1#(v) And checkZ#=<blockZ2#(v) Then clear=0
   EndIf
  Next
 Next
 ;scenery logic
 If InsideRing(pX#(cyc),pZ#(cyc),-15) And InsideRing(checkX#,checkZ#,-15)=0 Then clear=0 ;ring
 If BehindRailings(checkX#,checkZ#) And BehindRailings(pX#(cyc),pZ#(cyc))=0 Then clear=0 ;railings
 If BehindRailings(pX#(cyc),pZ#(cyc)) And BehindRailings(checkX#,checkZ#)=0 Then clear=0 ;railings
 If pZ#(cyc)<385 And checkZ#>385 Then clear=0 ;curtains
 If pZ#(cyc)>-385 And checkZ#<-385 Then clear=0 ;curtains
 Return clear
End Function

;CALCULATE FREE SPACE IN GIVEN ANGLE
Function MeasureSpace(cyc,angle#,limit)
 ;prepare probe
 space=0
 PositionEntity dummy,pX#(cyc),pY#(cyc),pZ#(cyc)
 RotateEntity dummy,0,angle#,0
 ;find conflicts
 For depth=1 To limit
  clear=1
  MoveEntity dummy,0,0,1
  checkX#=EntityX(dummy) : checkZ#=EntityZ(dummy)
  ;human blocks
  For v=1 To no_plays
   If cyc<>v And AttackViable(v)<>4 And pY#(cyc)=>pY#(v)-30 And pY#(cyc)=<pY#(v)+30
    If checkX#>pX#(v)-10 And checkX#<pX#(v)+10 And checkZ#>pZ#(v)-10 And checkZ#<pZ#(v)+10 Then clear=0
   EndIf
  Next
  ;item blocks
  For v=1 To no_items
   If checkX#>iX#(v)-30 And checkX#<iX#(v)+30 And checkZ#>iZ#(v)-30 And checkZ#<iZ#(v)+30 And pY#(cyc)>iY#(v)-5 And pY#(cyc)<iY#(v)+10 And iState(v)=0
    If ItemCollide(cyc,v,checkX#,checkZ#,0) Then clear=0
   EndIf
  Next
  ;world blocks
  For v=0 To 100
   If pY#(cyc)=>blockY1#(v) And pY#(cyc)=<blockY2#(v)
    If checkX#=>blockX1#(v) And checkX#=<blockX2#(v) And checkZ#=>blockZ1#(v) And checkZ#=<blockZ2#(v) Then clear=0
   EndIf
  Next
  ;scenery logic
  If InsideRing(pX#(cyc),pZ#(cyc),-15) And InsideRing(checkX#,checkZ#,-15)=0 Then clear=0 ;ring
  If BehindRailings(checkX#,checkZ#) And BehindRailings(pX#(cyc),pZ#(cyc))=0 Then clear=0 ;railings
  If BehindRailings(pX#(cyc),pZ#(cyc)) And BehindRailings(checkX#,checkZ#)=0 Then clear=0 ;railings
  If pZ#(cyc)<385 And checkZ#>385 Then clear=0 ;curtains
  If pZ#(cyc)>-385 And checkZ#<-385 Then clear=0 ;curtains
  ;increment count
  If clear=1 Then space=space+1 Else Exit
 Next
 Return space
End Function

;PASSAGE CLEAR FOR FLIGHT?
Function FlightClear(cyc,angle#,range#)
 clear=1
 ;check destination isn't blocked
 PositionEntity dummy,pX#(cyc),pY#(cyc),pZ#(cyc)
 RotateEntity dummy,0,angle#,0
 MoveEntity dummy,0,0,range#
 checkX#=EntityX(dummy) : checkZ#=EntityZ(dummy)
 ring=1
 If pPlatform(cyc)=>5 And pPlatform(cyc)=<8 Then ring=0
 If BlockConflict(checkX#,FindGround#(pX#(cyc),pZ#(cyc)),checkZ#,ring) Then clear=0
 ;don't allow ring for throw outs
 If pAnim(cyc)=145 Or pAnim(cyc)=146
  If InsideRing(checkX#,checkZ#,0) Then clear=0
 EndIf
 ;apron target remains within ring
 If pPlatform(cyc)=>1 And pPlatform(cyc)=<4
  If InsideRing(checkX#,checkZ#,0) Then clear=0
  If pPlatform(cyc)=1 And checkZ#<blockZ2#(0) Then clear=0
  If pPlatform(cyc)=2 And checkX#<blockX2#(0) Then clear=0
  If pPlatform(cyc)=3 And checkZ#>blockZ1#(0) Then clear=0
  If pPlatform(cyc)=4 And checkX#>blockX1#(0) Then clear=0
 EndIf
 ;curtain logic
 If pZ#(cyc)<385 And checkZ#>385 Then clear=0
 If pZ#(cyc)>-385 And checkZ#<-385 Then clear=0
 ;ledge logic
 If pPlatform(cyc)=>10 And pPlatform(cyc)=<90
  block=pPlatform(cyc)-10
  If blockClimb(block)=1 And block<>39 And SatisfiedAngle(angle#,0,90) Then clear=0
  If blockClimb(block)=2 And block<>39 And SatisfiedAngle(angle#,270,90) Then clear=0
  If blockClimb(block)=3 And block<>39 And SatisfiedAngle(angle#,180,90) Then clear=0
  If blockClimb(block)=4 And block<>39 And SatisfiedAngle(angle#,90,90) Then clear=0
  If block=>23 And block=<26
   If checkX#<-17 Or checkX#>17 Or checkZ#<-385 Or checkZ#>385 Then clear=0
  EndIf
  If block=39 And checkX#<blockX2#(block)+1 And checkZ#<blockZ2#(block)+1 Then clear=0
  If block=>55 And block=<60
   If checkX#<blockX2#(60)+1 Or checkX#>blockX1#(57)-1 Or checkZ#<blockZ2#(58)+1 Or checkZ#>blockZ1#(55)-1 Then clear=0
  EndIf
 EndIf
 ;cage logic
 If matchCage>0
  If pPlatform(cyc)<90 Or pPlatform(cyc)=>100
   If InsideRing(pX#(cyc),pZ#(cyc),0) And InsideRing(checkX#,checkZ#,-25)=0 Then clear=0
   If InsideRing(pX#(cyc),pZ#(cyc),0)=0 And InsideRing(checkX#,checkZ#,0) Then clear=0
  EndIf
  If pPlatform(cyc)=>91 And pPlatform(cyc)=<94 And InsideRing(checkX#,checkZ#,-15)=0 Then clear=0
  If pPlatform(cyc)=>95 And pPlatform(cyc)=<98 And InsideRing(checkX#,checkZ#,0) Then clear=0
 EndIf
 Return clear
End Function

;CO-ORDINATES BLOCKED?
Function BlockConflict(x#,y#,z#,ring)
 conflict=0
 If ring=1 Then start=0 Else start=5
 For v=start To 100
  If blockType(v)>0
   If x#=>blockX1#(v) And x#=<blockX2#(v) And y#=>blockY1#(v) And y#=<blockY2#(v) And z#=>blockZ1#(v) And z#=<blockZ2#(v) Then conflict=1
  EndIf
 Next
 Return conflict
End Function

;LIMB CONTACT?
Function LimbContact(cyc,v,limb,range#)
 value=0
 For count=1 To 50
  If pLimbY#(cyc,limb)=>pLimbY#(v,count)-range# And pLimbY#(cyc,limb)=<pLimbY#(v,count)+range#
   If pLimbX#(cyc,limb)=>pLimbX#(v,count)-range# And pLimbX#(cyc,limb)=<pLimbX#(v,count)+range# And pLimbZ#(cyc,limb)=>pLimbZ#(v,count)-range# And pLimbZ#(cyc,limb)=<pLimbZ#(v,count)+range#
    value=count
   EndIf
  EndIf
 Next
 Return value
End Function

;SENSIBLE TO ATTACK?
Function AttackSensible(cyc,v)
 sensible=1
 ;personal objections
 If pAngerTim(cyc,v)=0
  If pTeam(v)=pTeam(cyc) Then sensible=0 ;don't attack team-mates
  If charRelationship(pChar(cyc),pChar(v))=>0
   If pRole(cyc)=1
    If pRole(v)=0 And pEliminated(v)=0 Then sensible=0 ;don't let ref attack legals
    If pRole(v)=<2 And InsideRing(pX#(v),pZ#(v),0)=0 Then sensible=0 ;don't let ref attack well behaved secondaries
    If AttackViable(v)=>3 Then sensible=0 ;don't let ref attack neutralized
   EndIf
   If pRole(v)=1
    If matchState=<3 Or pTeam(matchWinner)=pTeam(cyc) Or matchWinner=0 Or pRole(cyc)=1 Then sensible=0 ;don't attack refs
   EndIf
  EndIf
 EndIf
 ;match logic
 If pRole(cyc)<>1
  ;don't disturb team-mate activity
  If pTeam(pGrappler(v))=pTeam(cyc) Or pTeam(pPinner(v))=pTeam(cyc) Then sensible=0
  ;don't disturb enemy elimination
  If matchType=5
   If pGrappling(v)>0 And pTeam(pGrappling(v))<>pTeam(cyc) Then sensible=0
   If pGrappler(v)>0 And pTeam(v)<>pTeam(cyc) Then sensible=0
   If pPinning(v)>0 And pTeam(pPinning(v))<>pTeam(cyc) Then sensible=0
   If pPinner(v)>0 And pTeam(v)<>pTeam(cyc) Then sensible=0
  EndIf
  ;don't disturb referee examinations
  If matchType=5 And pTeam(v)<>pTeam(cyc)
   If RefViable(v)=3 Or RefViable(v)=4 Or RefViable(v)=13 Or RefViable(v)=14
    For count=1 To no_plays
     If pRole(count)=1 And pRefVictim(count)=v Then sensible=0
    Next
   EndIf
  EndIf
  ;don't disturb beneficial refs
  If pRole(v)=1
   If pRefAward(v)>0 And pTeam(pRefAward(v))=pTeam(cyc) Then sensible=0
   If matchType=5 And pRefVictim(v)>0 And pTeam(pRefVictim(v))<>pTeam(cyc) Then sensible=0
  EndIf
 EndIf
 ;inaccessible height
 If pPlatform(cyc)=0 Or (pPlatform(cyc)=>1 And pPlatform(cyc)=<4 And pY#(cyc)=pY#(v))
  If pY#(v)<pY#(cyc)-20 Or pY#(v)>pY#(cyc)+30 Then sensible=0
 EndIf
 ;inaccessible when getting up
 If AttackViable(v)=>3 And GettingUp(v) Then sensible=0
 Return sensible
End Function

;NEAR GROUNDED OPPONENT?
Function NearGrounded(cyc,range#)
 ;extend range
 If pAnim(cyc)=12 Then range#=range#+(range#/2)
 If pHolding(cyc)>0 And (weapHold(weapType(pHolding(cyc)))=1 Or weapHold(weapType(pHolding(cyc)))=3)
  range#=range#+weapSize#(weapType(pHolding(cyc)))
 EndIf
 ;check target
 value=0
 If pFoc(cyc)>0
  v=pFoc(cyc)
  If InProximity(cyc,v,range#) And pY#(v)>pY#(cyc)-10 And pY#(v)<pY#(cyc)+10
   If AttackViable(v)=>3 And AttackViable(v)=<4 Then value=1
   If pAnim(v)=>120 And pAnim(v)=<149 And pAnim(v)<>123 And pAnim(v)<>126 And pAnim(v)<>140
    If pAnimTim(v)>20 Then value=1
   EndIf
  EndIf
 Else
  ;check all targets
  For v=1 To no_plays
   If cyc<>v And pTeam(cyc)<>pTeam(v) And InProximity(cyc,v,range#) And pY#(v)>pY#(cyc)-10 And pY#(v)<pY#(cyc)+10 And AttackViable(v)=>3 And AttackViable(v)=<4
    If InLine(cyc,pLimb(v,36),45) Then value=1
   EndIf
  Next
 EndIf
 Return value
End Function

;TOO BUSY TO ENGAGE IN ACTIVITY?
Function RushViable(cyc)
 viable=0
 If LegalMan(cyc) And matchState=3 And matchCountOuts=>1 And matchCountOuts=<2 And matchRules=>1 And InsideRing(pX#(cyc),pZ#(cyc),0)=0
  If pOutCount(cyc)=>2 And InsideRing(pX#(cyc),pZ#(cyc),450)=0 Then viable=1
  If pOutCount(cyc)=>3 And InsideRing(pX#(cyc),pZ#(cyc),350)=0 Then viable=1
  If pOutCount(cyc)=>4 And InsideRing(pX#(cyc),pZ#(cyc),250)=0 Then viable=1
  If pOutCount(cyc)=>5 And InsideRing(pX#(cyc),pZ#(cyc),150)=0 Then viable=1
  If pOutCount(cyc)=>6 And InsideRing(pX#(cyc),pZ#(cyc),50)=0 Then viable=1
  If pOutCount(cyc)=>7 Then viable=1
 EndIf
 Return viable
End Function

;FIND INTERACTIONS
Function FindInteractions(cyc)
 value=0
 ;human interactions
 For v=1 To no_plays
  If cyc<>v
   If InProximity(cyc,v,50)
    ;1. request space
    If matchState=3 And pTeam(v)<>pTeam(cyc) And InsideRing(pX#(cyc),pZ#(cyc),-15) And AttackViable(v)=<2
     If (LegalMan(cyc) Or pRole(cyc)=1) And LegalMan(v)=0 And pRole(v)<>1
      If pRole(v)>0 Or pEliminated(v) Then range#=0 Else range#=-15
      If InsideRing(pX#(v),pZ#(v),range#) Then pInteract(cyc)=v : value=1
     EndIf
    EndIf
    ;3. interrupting count
    If matchState=3 And matchCountOuts=>1 And matchCountOuts=<2 And pRole(cyc)=1 And pRole(v)<>1 And pFoc(v)>0
     If RefViable(pFoc(v))=5 And pOutCount(v)<pOutCount(pFoc(v))-1 And pTeam(v)<>pTeam(pFoc(v))
      If InProximity(v,pFoc(v),30) And AttackViable(v)=1 Then pInteract(cyc)=v : value=3
     EndIf
    EndIf
   EndIf
  EndIf
 Next
 ;scenery interactions
 If InsideRing(pX#(cyc),pZ#(cyc),-15) And pY#(cyc)=wStage#
  For v=1 To 4
   ;2. unfasten buckle
   limb=FindChild(world,"Ring0"+v)
   If NearestCorner(pX#(cyc),pZ#(cyc))=v And ReachedCord(pX#(cyc),pZ#(cyc),EntityX(limb,1),EntityZ(limb,1),12)
    If padExposed(v)=0 Then value=2
   EndIf
  Next
 EndIf
 Return value
End Function

;REFEREE'S ATTENTION REQUIRED?
Function RefViable(cyc) ;1-10=checkable, 11-20=declarable, 21+=trivia
 viable=0
 If cyc>0 And pRole(cyc)<>1
  ;21. intruding
  If matchState=3 And LegalMan(cyc)=0 And pChaosTim(cyc)=0 And AttackViable(cyc)=>1 And AttackViable(cyc)=<2
   range#=0
   If pRole(cyc)=0 And pEliminated(cyc)=0 Then range#=-15
   If InsideRing(pX#(cyc),pZ#(cyc),range#) Then viable=21
  EndIf
  ;5. counted out
  If matchState=3 And matchCountOuts>0 And LegalMan(cyc) And pOutTim(cyc)>100
   If InsideRing(RealX#(cyc),RealZ#(cyc),-5)=0 Or (pPlatform(cyc)=>95 And pPlatform(cyc)=<98)
    If matchCountOuts<3 And matchRules=>1 Then viable=5
    If matchCountOuts=3 And pY#(cyc)=wGround# And pPlatform(cyc)=0 And AttackViable(cyc)>0 Then viable=15
   EndIf
   If pOutCount(cyc)=>10 Then viable=15
  EndIf
  ;22. interrupting count
  If matchState=3 And matchCountOuts=>1 And matchCountOuts=<2 And pFoc(cyc)>0 And LegalMan(pFoc(cyc)) And pTeam(cyc)<>pTeam(pFoc(cyc)) And InsideRing(RealX#(cyc),RealZ#(cyc),-5)=0
   If InsideRing(RealX#(pFoc(cyc)),RealZ#(pFoc(cyc)),-5)=0 Or (pPlatform(pFoc(cyc))=>95 And pPlatform(pFoc(cyc))=<98)
    If pOutCount(cyc)<pOutCount(pFoc(cyc))-1 And InProximity(cyc,pFoc(cyc),30) And AttackViable(cyc)=1 Then viable=22
   EndIf
  EndIf
  ;match stipulations
  If matchState=3 And LegalMan(cyc) And pOutTim(cyc)>100
   If FallsCount(cyc)
    ;4. bloodiness
    If matchBlood>0 And AttackViable(cyc)=>3 And pDT(cyc)>50 And pScar(cyc,1)>0
     viable=4
     If pScar(cyc,1)=>3 Or (optGore=0 And pScar(cyc,1)>0) Then viable=14
    EndIf
    ;3. knocked out
    If matchKOs>0 And AttackViable(cyc)=>3 And pDT(cyc)>100
     If pHealth(cyc)<1250*optLength Then viable=3
     If pHealth(cyc)<100 Then viable=13
    EndIf
    ;2. hold victim
    If matchSubs>0 And pGrappler(cyc)>0 And LegalMan(pGrappler(cyc)) And pTeam(pGrappler(cyc))<>pTeam(cyc)
     If pStretched(cyc)>0 Then viable=2
     If pStretched(cyc)=2 Then viable=12
    EndIf
    ;1. pin victim
    If matchPins>0 And pPinner(cyc)>0 And LegalMan(pPinner(cyc)) And pTeam(pPinner(cyc))<>pTeam(cyc) ;And pAnim(pPinner(cyc))<>160
     viable=1
    EndIf
    If pPinCount(cyc)=>3 Then viable=11
   EndIf
  EndIf
 EndIf
 Return viable
End Function

;REFEREE IN A POSITION TO ACT?
Function RefActViable(cyc,v)
 viable=0
 If pRole(cyc)=1 And v>0
  mission=RefViable(v)
  ;examinations
  If ((mission=>1 And mission=<4) Or (mission=>11 And mission=<14)) And pChecked(v)=0
   range#=30
   If mission=1 Or mission=11 Then range#=35
   If (mission=2 Or mission=12) And pAnim(v)=309 Then range#=25
   If InProximity(cyc,v,range#) Or (pGrappler(v)>0 And InProximity(cyc,pGrappler(v),range#)) Or (pPinner(v)>0 And InProximity(cyc,pPinner(v),range#))
    If pY#(cyc)>pY#(v)-5 And pY#(cyc)<pY#(v)+5 And pPlatform(cyc)=0 Then viable=1
   EndIf
  EndIf
  ;count-out's
  If matchCounter=0 Or matchCounter=cyc
   If FindInteractions(cyc)<>3
    If mission=5 And pOutsideTim(v)>50
     If InProximity(cyc,v,100) Or InsideRing(pX#(cyc),pZ#(cyc),0) Then viable=1
     For count=1 To no_plays
      If count<>v And RefViable(count)=5 And pOutsideTim(count)>25 Then viable=1
     Next
    EndIf
    If mission=15 Then viable=1
   EndIf
  EndIf
 EndIf
 Return viable
End Function

;DUTY PRIORITY FOR REFEREES
Function DutyPriority(duty)
 priority=0
 If duty=21 Then priority=1 ;interrupting
 If duty=5 Or duty=15 Then priority=5 ;count-out's
 If duty=22 Then priority=6 ;count-out bullying
 If duty=4 Or duty=14 Then priority=7 ;blood possibility
 If duty=3 Or duty=13 Then priority=8 ;KO possibility
 If duty=2 Or duty=12 Then priority=9 ;submission attempt
 If duty=1 Or duty=11 Then priority=10 ;pin attempt
 Return priority
End Function

;FIND AN ACTIVE REFEREE
Function FindReferee(cyc,v) ;0,0=for chaos, cyc,v=for DQ's
 ;find real referees
 If no_refs>0
  value=0
  For count=1 To no_plays
   If pRole(count)=1 And InsideRing(pX#(count),pZ#(count),0)
    If cyc=0 And v=0
     If AttackViable(count)<3 Then value=count
    Else
     blind=0
     If pDizzyTim(count)>0 Or pBlindTim(count)>0 Or pAnim(count)=110 Then blind=1
     If charRelationship(pChar(count),pChar(cyc))>0 Then blind=1
     If charRelationship(pChar(count),pChar(v))<0 Then blind=1
     If game=1 And gamAgreement(15)>0 And cyc=matchPlayer Then blind=1
     If game=1 And gamAgreement(16)>0 And v=matchPlayer Then blind=1
     If blind=0 And AttackViable(count)=1 And pGrappling(count)=0 And pGrappler(count)=0
      If InLine(count,p(cyc),45) Or InLine(count,p(v),45) Then value=count
     EndIf
    EndIf
   EndIf
  Next
 EndIf
 ;guaranteed if automated
 If no_refs=0 Then value=1
 Return value
End Function

;ESTABLISH WHETHER FALLS COUNT
Function FallsCount(cyc)
 value=0
 If matchRules=>1 And InsideRing(pX#(cyc),pZ#(cyc),-15) Then value=1
 If matchRules=0 Then value=1
 Return value
End Function

;LEGAL MAN?
Function LegalMan(cyc)
 value=0
 If teamLegal(pTeam(cyc))=cyc Or matchTeams=<1 Then value=1
 If pRole(cyc)>0 Or pEliminated(cyc) Then value=0
 Return value
End Function

;ASSIGN NEW LEGAL MAN
Function GetNewLegal(cyc)
 If matchTeams=2 And matchState=3
  ;find replacement
  v=cyc : its=0
  Repeat
   v=v+1 : satisfied=1
   If v>no_wrestlers Then v=1
   If cyc=v Or pTeam(v)<>pTeam(cyc) Then satisfied=0
   If pRole(v)>0 Or pEliminated(v) Then satisfied=0
   its=its+1
   If its>100 Then v=0 : satisfied=1
  Until satisfied=1
  ;make transition
  If v>0 And cyc<>v
   teamLegal(pTeam(cyc))=v
   If pTeam(v)=1 Then pFoc(v)=teamLegal(2)
   If pTeam(v)=2 Then pFoc(v)=teamLegal(1)
   pAgenda(v)=1
   If optTagControl=1
    SwapControls(cyc,v,0)
    camTempTim=50 : camTempFoc=cyc
   EndIf
  EndIf
 EndIf
End Function

;FIND TAG POSITION CONFLICTS
Function FindTagConflict(cyc,agenda)
 conflict=0
 For v=1 To no_wrestlers
  If cyc<>v And pTeam(cyc)=pTeam(v) And LegalMan(v)=0 And pAgenda(v)=agenda Then conflict=1
 Next
 Return conflict
End Function

;CHARACTER IN TAG CORNER?
Function TagProximity(cyc,range#)
 value=0
 If pY#(cyc)=wStage#
  If pX#(cyc)>teamX#(pTeam(cyc))-range# And pX#(cyc)<teamX#(pTeam(cyc))+range# And pZ#(cyc)>teamZ#(pTeam(cyc))-range# And pZ#(cyc)<teamZ#(pTeam(cyc))+range#
   value=1
  EndIf
 EndIf
 Return value
End Function

;PARTNER REQUIRED TO STAND STILL?
Function TagStatic(cyc)
 value=0
 If matchState=3 And matchTeams=2 And pRole(cyc)=0 And LegalMan(cyc)=0 And pEliminated(cyc)=0 And pChaosTim(cyc)=0
  If InsideRing(pX#(cyc),pZ#(cyc),-15)=0 And TagProximity(cyc,80) Then value=1
 EndIf
 Return value
End Function

;ATTEMPTING TO TAG?
Function TryingToTag(cyc)
 value=0
 If matchState=3 And matchTeams=2 And LegalMan(cyc) And pControl(cyc)=0
  If pAgenda(cyc)=1 And pTeam(pFoc(cyc))=pTeam(cyc) And pPlatform(pFoc(cyc))=>1 And pPlatform(pFoc(cyc))=<4 Then value=1
 EndIf
 Return value
End Function

;CPU READY TO TOPE?
Function TopeViable(cyc)
 value=0
 If pFoc(cyc)>0 And InProximity(cyc,pFoc(cyc),50) And pRole(cyc)<>1 Then value=1
 Return value
End Function