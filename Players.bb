;//////////////////////////////////////////////////////////////////////////////
;------------------------ WRESTLING MPIRE 2008: PLAYERS -----------------------
;//////////////////////////////////////////////////////////////////////////////

;-----------------------------------------------------------------
;//////////////////////// PREPARE CAST ///////////////////////////
;-----------------------------------------------------------------
Function PrepareCast()
 ;reset all
 matchPlayer=0 : matchMulti=0
 curtainRotor=0 : curtainSlot(1)=0 : curtainSlot(2)=0
 For cyc=1 To optPlayLim
  pManager(cyc)=0 : pClient(cyc)=0
  teamLegal(cyc)=0 : teamFalls(cyc)=0  
 Next
 ;confirm wrestler status
 For cyc=1 To no_wrestlers 
  If pChar(cyc)=0
   Repeat
    newbie=Rnd(1,no_chars)
   Until FindCharacter(newbie)=0
   pChar(cyc)=newbie
  EndIf 
  pRole(cyc)=0
 Next
 ;insert referees
 If no_refs>0
  For count=1 To no_refs
   cyc=no_wrestlers+count
   If pChar(cyc)=0
    Repeat
     newbie=Rnd(1,no_chars)
    Until FindCharacter(newbie)=0
    pChar(cyc)=newbie
   EndIf
   pRole(cyc)=1
  Next
 EndIf
 ;add managers
 If (optManagers=1 And no_wrestlers<4) Or (optManagers=2 And no_wrestlers=<4) Or (optManagers=3 And no_wrestlers=<8)
  For cyc=1 To no_wrestlers
   manager=charManager(pChar(cyc))
   If manager=0 And charPartner(pChar(cyc))>0 Then manager=charPartner(pChar(cyc))
   If game=1 And gamSchedule(gamDate)=4 Then manager=fedBooker(charFed(pChar(cyc)))
   If game=1 And charVacant(manager)>0 Then manager=0
   If cyc=1 And matchPromo=50 Then manager=201
   If manager>0 And FindCharacter(manager)=0 And InjuryStatus(manager)=0 And no_plays<optPlayLim
    no_plays=no_plays+1
    pChar(no_plays)=manager : pRole(no_plays)=2
    pManager(cyc)=no_plays : pClient(no_plays)=cyc
   EndIf
  Next
 EndIf
 ;add intruders
 If matchTeams=>0 And screenAgenda<>10
  If (optIntruders=>1 And no_wrestlers<4) Or (optIntruders=2 And no_wrestlers=<4) Or (optIntruders=3 And no_wrestlers=<8)
   If no_plays<optPlayLim Then AddIntruder() 
  EndIf
  If optIntruders=3 And no_wrestlers=<4 And no_plays<optPlayLim Then AddIntruder()
 EndIf
End Function

;RESET CHARACTER SELECTION
Function ResetCharacters()
 For cyc=1 To optPlayLim
  pChar(cyc)=0
  pControl(cyc)=0
 Next
End Function

;FIND CHARACTER (in game cast)
Function FindCharacter(char)
 value=0
 For v=1 To no_plays
  If pChar(v)=char Then value=v
 Next
 Return value
End Function

;ADD INTRUDER
Function AddIntruder()
 no_plays=no_plays+1
 its=0
 Repeat
  satisfied=1 : its=its+1
  ;source characters
  newbie=Rnd(1,no_chars) 
  randy=Rnd(1,4)
  If randy=<3 And fed>0 And fedSize(fed)>no_plays Then newbie=fedRoster(fed,Rnd(1,fedSize(fed)))
  ;favour meaningful intruders
  purpose=0
  For v=1 To no_wrestlers
   If charRelationship(newbie,pChar(v))<>0 Or charRealRelationship(newbie,pChar(v))<>0 Then purpose=1
  Next
  randy=Rnd(0,2)
  If randy=0 And purpose=0 Then satisfied=0
  randy=Rnd(0,2)
  If randy=0 And fed>0 And charFed(newbie)<>fed Then satisfied=0
  ;urgent exceptions
  If FindCharacter(newbie)>0 Or charVacant(newbie)>0 Or ImportantChar(newbie) Or charFed(newbie)=9 Then satisfied=0
 Until satisfied=1 Or its>1000
 ;add to cast
 pChar(no_plays)=newbie
 pRole(no_plays)=3
End Function

;-----------------------------------------------------------------
;//////////////////////// LOAD PLAYERS ///////////////////////////
;-----------------------------------------------------------------
Function LoadPlayers()
 For cyc=1 To no_plays
  ;assign team
  pHeel(cyc)=charHeel(pChar(cyc))
  If pRole(cyc)=0 Then pTeam(cyc)=cyc Else pTeam(cyc)=0
  If matchTeams>0 And pRole(cyc)=0
   If cyc=<no_wrestlers/2 Then pTeam(cyc)=1 Else pTeam(cyc)=2
  EndIf
  If pRole(cyc)=2 Then pTeam(cyc)=pTeam(pClient(cyc))
  If teamLegal(pTeam(cyc))=0 Then teamLegal(pTeam(cyc))=cyc
  If matchTeams>0 And pTeam(cyc)=>1 And pTeam(cyc)=<2
   pCurtain(cyc)=pTeam(cyc)
  Else
   curtainRotor=curtainRotor+1
   If curtainRotor>2 Then curtainRotor=1
   pCurtain(cyc)=curtainRotor
  EndIf
  ;clock player status
  If game=1 And gamSchedule(gamDate)=4 And pChar(cyc)=gamChar Then pControl(cyc)=3
  If pControl(cyc)>0
   If matchPlayer=0 Then matchPlayer=cyc Else matchMulti=1
   If optOnline>0 Then matchMulti=0
  EndIf
  ;establish costume
  If pRole(cyc)=0 Then pCostume(cyc)=1
  If KeyDown(46) Then pCostume(cyc)=2
  If pRole(cyc)=1 Then pCostume(cyc)=3
  If pRole(cyc)=>2 Or matchPreset=<1 Or matchLocation>0 Then pCostume(cyc)=2
  randy=Rnd(0,4)
  If (randy=0 Or charRole(pChar(cyc))=3) And pRole(cyc)=3 And no_refs>0 Then pCostume(cyc)=3
  ;generate model
  namer$="Wrestler "+cyc+" of "+no_wrestlers ;charName$(pChar(cyc))
  If pRole(cyc)=1 Then namer$="Referee"
  If pRole(cyc)=2 Then namer$="Manager"
  If pRole(cyc)=3 Then namer$="Intruder"
  MatchLoader("Please Wait","Loading "+namer$) 
  p(cyc)=LoadAnimMesh("Characters/Models/Model0"+GetModel(pChar(cyc))+".3ds")
  StripModel(cyc)
  ;apply costume
  ApplyCostume(cyc)
  ;reset scars
  For limb=1 To 50
   pScar(cyc,limb)=0 : pOldScar(cyc,limb)=-1
   If charLimb(pChar(cyc),limb)=0 Then pScar(cyc,limb)=5
  Next
  ;load sequences
  LoadSequences(cyc)
  Animate p(cyc),1,0.25,pSeq(cyc,1),0
  ;default positions
  pOutTim(cyc)=0 : pFoc(cyc)=0
  If pRole(cyc)=0
   curtainSlot(pCurtain(cyc))=curtainSlot(pCurtain(cyc))+1
   If matchTeams=0
    slotter=(no_wrestlers-(no_wrestlers/2))-curtainSlot(pCurtain(cyc))
    If slotter<0 Then slotter=0
    If pCurtain(cyc)=1 Then pZ#(cyc)=410+(20*slotter) : pA#(cyc)=180
    slotter=(no_wrestlers/2)-curtainSlot(pCurtain(cyc))
    If slotter<0 Then slotter=0 
    If pCurtain(cyc)=2 Then pZ#(cyc)=-410-(20*slotter) : pA#(cyc)=0  
   Else
    If pCurtain(cyc)=1 Then pZ#(cyc)=390+(20*curtainSlot(pCurtain(cyc))) : pA#(cyc)=180
    If pCurtain(cyc)=2 Then pZ#(cyc)=-390-(20*curtainSlot(pCurtain(cyc))) : pA#(cyc)=0  
   EndIf
   pX#(cyc)=0 : pY#(cyc)=wGround#
  EndIf
  If pRole(cyc)=1  
   Repeat
    pX#(cyc)=Rnd(-130,130) : pZ#(cyc)=Rnd(-130,130)
   Until InsideRing(pX#(cyc),pZ#(cyc),0)=0
   pY#(cyc)=wGround# : pA#(cyc)=Rnd(0,360)
   pOutTim(cyc)=100
  EndIf
  If pRole(cyc)=2
   If pZ#(pClient(cyc))>0 Then pZ#(cyc)=pZ#(pClient(cyc))+10 : pA#(cyc)=180  
   If pZ#(pClient(cyc))<0 Then pZ#(cyc)=pZ#(pClient(cyc))-10 : pA#(cyc)=0   
   randy=Rnd(0,1) 
   If randy=0 Then pX#(cyc)=pX#(pClient(cyc))-10 Else pX#(cyc)=pX#(pClient(cyc))+10
   pY#(cyc)=pY#(pClient(cyc))
  EndIf
  ;intruder positions
  If pRole(cyc)=3
   randy=Rnd(0,2)
   If randy=0
    spectate=Rnd(1,6)
    If spectate=1 Then pX#(cyc)=Rnd(-190,-35) : pZ#(cyc)=Rnd(150,190)
    If spectate=2 Then pX#(cyc)=Rnd(35,190) : pZ#(cyc)=Rnd(150,190) 
    If spectate=3 Then pX#(cyc)=Rnd(150,190) : pZ#(cyc)=Rnd(-190,190) 
    If spectate=4 Then pX#(cyc)=Rnd(35,190) : pZ#(cyc)=Rnd(-190,-150)
    If spectate=5 Then pX#(cyc)=Rnd(-190,-35) : pZ#(cyc)=Rnd(-190,-150) 
    If spectate=6 Then pX#(cyc)=Rnd(-190,-150) : pZ#(cyc)=Rnd(-190,190) 
   EndIf
   If randy=1 Or matchLocation=1 Then pX#(cyc)=Rnd(-120,120) : pZ#(cyc)=Rnd(660,840) 
   If randy=2 Or matchLocation=2 Then pX#(cyc)=Rnd(-90,100) : pZ#(cyc)=Rnd(-740,-640)   
   pY#(cyc)=wGround# : pA#(cyc)=Rnd(0,360)
  EndIf
  ;backstage positions
  If matchLocation>0 And pRole(cyc)=<2 
   If pRole(cyc)=0
    its=0
    Repeat
     conflict=0 : its=its+1
     If matchLocation=1 Then pX#(cyc)=Rnd(-120,120) : pZ#(cyc)=Rnd(660,840) 
     If matchLocation=2 Then pX#(cyc)=Rnd(-90,100) : pZ#(cyc)=Rnd(-740,-640)  
     If matchTeams>0 
      If matchLocation=1 And pTeam(cyc)=1 Then pX#(cyc)=Rnd(-120,0) : pZ#(cyc)=Rnd(660,840) 
      If matchLocation=1 And pTeam(cyc)=2 Then pX#(cyc)=Rnd(0,120) : pZ#(cyc)=Rnd(660,840) 
      If matchLocation=2 And pTeam(cyc)=1 Then pX#(cyc)=Rnd(-90,5) : pZ#(cyc)=Rnd(-740,-640)
      If matchLocation=2 And pTeam(cyc)=2 Then pX#(cyc)=Rnd(5,100) : pZ#(cyc)=Rnd(-740,-640) 
     EndIf
     For v=1 To no_plays
      If cyc<>v And ReachedCord(pX#(cyc),pZ#(cyc),pX#(v),pZ#(v),20) Then conflict=1
     Next
    Until conflict=0  
   EndIf
   If pRole(cyc)=1 
    If matchLocation=1 Then pX#(cyc)=Rnd(-20,20) : pZ#(cyc)=Rnd(500,550)
    If matchLocation=2 Then pX#(cyc)=Rnd(-20,20) : pZ#(cyc)=Rnd(-550,-500)
   EndIf
   If pRole(cyc)=2
    If pX#(pClient(cyc))<0 Then pX#(cyc)=pX#(pClient(cyc))-20 Else pX#(cyc)=pX#(pClient(cyc))+20 
    If matchLocation=1 
     If pZ#(pClient(cyc))<750 Then pZ#(cyc)=pZ#(pClient(cyc))-20 Else pZ#(cyc)=pZ#(pClient(cyc))+20   
    EndIf
    If matchLocation=2 
     If pZ#(pClient(cyc))<-690 Then pZ#(cyc)=pZ#(pClient(cyc))-20 Else pZ#(cyc)=pZ#(pClient(cyc))+20   
    EndIf
   EndIf
   pY#(cyc)=wGround# : pA#(cyc)=Rnd(0,360)
   pOutTim(cyc)=100
  EndIf
  ;in-ring positions
  If matchEntrances=0 And matchLocation=0 And pRole(cyc)=<2 And pRole(cyc)<>1
   If pRole(cyc)=0
    its=0
    Repeat
     conflict=0 : its=its+1
     pX#(cyc)=Rnd(-55,55) : pZ#(cyc)=Rnd(-55,55) 
     If matchTeams>0 And no_wrestlers=<10
      If pTeam(cyc)=1 Then pX#(cyc)=Rnd(-55,0) : pZ#(cyc)=Rnd(-55,0) 
      If pTeam(cyc)=2 Then pX#(cyc)=Rnd(0,55) : pZ#(cyc)=Rnd(0,55) 
     EndIf
     For v=1 To no_plays
      If cyc<>v And ReachedCord(pX#(cyc),pZ#(cyc),pX#(v),pZ#(v),20) Then conflict=1
     Next
    Until conflict=0
   EndIf
   If pRole(cyc)=2
    If pX#(pClient(cyc))<0 Then pX#(cyc)=pX#(pClient(cyc))+20 Else pX#(cyc)=pX#(pClient(cyc))-20 
    If pZ#(pClient(cyc))<0 Then pZ#(cyc)=pZ#(pClient(cyc))+20 Else pZ#(cyc)=pZ#(pClient(cyc))-20   
   EndIf
   pY#(cyc)=wStage# : pA#(cyc)=Rnd(0,360)
   pOutTim(cyc)=100
  EndIf
  ;honour position
  pOldX#(cyc)=pX#(cyc) : pOldZ#(cyc)=pZ#(cyc) : pTA#(cyc)=pA#(cyc)
  pSubX#(cyc)=9999 : pSubZ#(cyc)=9999
  PositionEntity p(cyc),pX#(cyc),pY#(cyc),pZ#(cyc)
  RotateEntity p(cyc),0,pA#(cyc),0
  scaler#=0.055+(GetPercent#(charHeight(pChar(cyc)),24)/10000)
  ScaleEntity p(cyc),scaler#,scaler#,scaler# 
  ;reset values
  pOldEyes(cyc)=-1 : pEyes(cyc)=2
  pAnim(cyc)=0 : pAnimTim(cyc)=0 : pReaction(cyc)=0
  pDT(cyc)=0 : pHeat(cyc)=50 : pImmunity(cyc)=0
  pDizzyTim(cyc)=0 : pBlindTim(cyc)=0
  pHurtDelay(cyc)=0 : pBlockTim(cyc)=0 
  pOutCount(cyc)=0 : pPinCount(cyc)=0
  pFalls(cyc)=0 : pEliminated(cyc)=0
  pGrappling(cyc)=0 : pGrappler(cyc)=0
  pGrappleAssist(cyc)=0 : pStretched(cyc)=0
  pPinning(cyc)=0 : pPinner(cyc)=0
  pChecked(cyc)=0 : pWarned(cyc)=0
  pRunTim(cyc)=0 : pPlatform(cyc)=0
  pHolding(cyc)=0 : pCarrying(cyc)=0
  pAbused(cyc)=0 : pIntruder(cyc)=0
  ResetExcuses(cyc,0) 
  For v=1 To no_plays
   pAngerTim(cyc,v)=0
  Next
  ;default health
  pHealth(cyc)=5000*optLength 
  If game=0 And screenAgenda=11 Then pHealth(cyc)=PercentOf#(5000*optLength,cupCharHealth(cupSlot,pChar(cyc)))
  If game=1 Then pHealth(cyc)=PercentOf#(5000*optLength,charHealth(pChar(cyc)))
  For count=0 To 5
   pInjured(cyc,count)=0
   If game=0 And screenAgenda=11 And cupCharInjured(cupSlot,pChar(cyc),count)>0 Then pInjured(cyc,count)=cupCharInjured(cupSlot,pChar(cyc),count) : pHealth(cyc)=2500*optLength
   If game=1 And charInjured(pChar(cyc),count)>0 Then pInjured(cyc,count)=100000 : pHealth(cyc)=2500*optLength
  Next
  If pHealth(cyc)<100 Then pHealth(cyc)=100
  pHealthLimit(cyc)=pHealth(cyc)
  pShowHealth(cyc)=pHealth(cyc)
  pHP(cyc)=pHealth(cyc)/10
  ;shadows
  LoadShadows(cyc)
  ;labels
  If optLabels>0
   pLabel(cyc)=CreateSprite()
   SpriteViewMode pLabel(cyc),1
   EntityFX pLabel(cyc),1
   If optLabels=1 Then EntityAlpha pLabel(cyc),0.7
   HideEntity pLabel(cyc)
  EndIf
  ;visibility
  pHidden(cyc)=0
  If matchTeams=-1 And pRole(cyc)<>1
   If optHideElim=1 And (cyc=<no_wrestlers-4 Or pRole(cyc)=>2) Then pHidden(cyc)=1
   If optHideElim=2 And (cyc=<no_wrestlers-2 Or pRole(cyc)=>2) Then pHidden(cyc)=1
  EndIf
  If pRole(cyc)=3
   If optHideElim=1 And no_wrestlers>4 Then pHidden(cyc)=1
   If optHideElim=2 Then pHidden(cyc)=1
  EndIf
  ;video portrait
  If pRole(cyc)=0 And no_wrestlers=<10
   tVideo(20+cyc)=LoadTexture("Data/Slot0"+slot+"/Portraits/Photo"+Dig$(pChar(cyc),100)+".bmp")
   ScaleTexture tVideo(20+cyc),1,2
   factor#=GetPercent#(charHeight(pChar(cyc)),36)
   pos#=0.85+PercentOf#(0.15,factor#)
   PositionTexture tVideo(20+cyc),1,pos# 
  EndIf
 Next
End Function

;LOAD SHADOWS
Function LoadShadows(cyc)
 For limb=1 To 50
  pShadow(cyc,limb)=0
  If (optShadows>0 And limb=36) Or (optShadows=2 And (limb=1 Or (limb=>6 And limb=<8) Or (limb=>19 And limb=<21) Or limb=40 Or limb=41 Or limb=43 Or limb=44))
   ;load sprite
   pShadow(cyc,limb)=LoadSprite("World/Sprites/Shadow.png",2)
   ScaleSprite pShadow(cyc,limb),13,13
   If limb<>36 Then ScaleSprite pShadow(cyc,limb),10,10
   If limb=8 Or limb=21 Or limb=41 Or limb=44 Then ScaleSprite pShadow(cyc,limb),8,8
   RotateEntity pShadow(cyc,limb),90,0,0
   SpriteViewMode pShadow(cyc,limb),2
   ;set transparency
   EntityAlpha pShadow(cyc,limb),0.1
   If optShadows=2 And (limb=36 Or limb=8 Or limb=21) Then EntityAlpha pShadow(cyc,limb),0.2
   If optShadows=1 Then EntityAlpha pShadow(cyc,limb),0.5
   If optShadows=0 Then EntityAlpha pShadow(cyc,limb),0 
   EntityColor pShadow(cyc,limb),10,10,10
  EndIf
 Next 
End Function

;-----------------------------------------------------------------
;///////////////////////// PLAYER CYCLE //////////////////////////
;-----------------------------------------------------------------
Function PlayerCycle()
 For cyc=1 To no_plays
  ;counters
  pChaosTim(cyc)=pChaosTim(cyc)-1
  If pRole(cyc)=3 And pChaosTim(cyc)<10 Then pChaosTim(cyc)=10
  If pChaosTim(cyc)<0 Then pChaosTim(cyc)=0
  pFriction(cyc)=pFriction(cyc)-1
  If pFriction(cyc)<0 Then pFriction(cyc)=0
  If pAnim(cyc)=12 Then pMomentum(cyc)=pMomentum(cyc)-1
  If pOutCount(cyc)=>8 And pMomentum(cyc)>20 Then pMomentum(cyc)=20
  If pMomentum(cyc)<0 Then pMomentum(cyc)=0
  If pAnim(cyc)<>12 And pAnim(cyc)<>44 And pAnim(cyc)<>302 And pAnim(cyc)<>309 And pAnim(cyc)<>700 And pAnim(cyc)<>701 Then pMomentum(cyc)=0
  pHurtTim(cyc)=pHurtTim(cyc)-1
  If pHurtTim(cyc)<0 Then pHurtTim(cyc)=0
  pBuckleTim(cyc)=pBuckleTim(cyc)-1
  If pBuckleTim(cyc)<0 Then pBuckleTim(cyc)=0
  pImmunity(cyc)=pImmunity(cyc)-1
  If pImmunity(cyc)<0 Then pImmunity(cyc)=0
  pFocTim(cyc)=pFocTim(cyc)-1
  If pFocTim(cyc)<0 Then pFocTim(cyc)=0 
  pSpecialFlash(cyc)=pSpecialFlash(cyc)+1
  If pSpecialFlash(cyc)>10 Then pSpecialFlash(cyc)=0
  ;negate count
  pCountTim(cyc)=pCountTim(cyc)-1
  If pCountTim(cyc)<0 Then pCountTim(cyc)=0
  If InsideRing(RealX#(cyc),RealZ#(cyc),-5)=0
   If pOutSideTim(cyc)=0 Then pAutoTim(cyc)=0
   pOutsideTim(cyc)=pOutsideTim(cyc)+1
  Else 
   If pGrappling(cyc)=0 And (pGrappler(cyc)=0 Or pAnim(pGrappler(cyc))=312)
    pOutsideTim(cyc)=0
    If pOutCount(cyc)<10 Then pOutCount(cyc)=0
   EndIf
  EndIf
  If pPinner(cyc)=0 And pPinCount(cyc)<3 Then pPinCount(cyc)=0
  ;checked status
  If RefViable(cyc)=0 Then pChecked(cyc)=0
  If pPinning(cyc)>0 Or (pGrappling(cyc)>0 And pStretched(pGrappling(cyc))>0)
   If pWarned(cyc)>0 And pAngerTim(pWarned(cyc),cyc)<10 Then pAngerTim(pWarned(cyc),cyc)=10
  EndIf
  ;automated refereeing
  If matchState=3 Then AutomatedRefs(cyc)
  ;make entrance
  ManageEntrance(cyc)
  ;monitor status
  MonitorStatus(cyc)
  ;get input
  GetInput(cyc)
  TranslateInput(cyc)
  ;fall off items
  If pPlatform(cyc)=>100
   v=pPlatform(cyc)-100
   If iCarrier(v)>0 Or iState(v)=1 Then pHP(cyc)=0 : pHurtA#(cyc)=Rnd(0,360)
  EndIf
  ;trigger falls
  If pAnim(cyc)<60 Or pAnim(cyc)=>70 Then pHurtDelay(cyc)=0
  If matchCage>0 And pPlatform(cyc)=>1 And pPlatform(cyc)=<4 And pHP(cyc)<100 Then pHP(cyc)=100
  If pHP(cyc)=<0 And CollapseViable(cyc) And pHurtDelay(cyc)=0 Then TriggerFall(cyc)
  ;manage animations
  Animations(cyc)
  ;enforce blocks
  If pHidden(cyc)=0
   EnforceBlocks(cyc)
  EndIf
  ;adjust angle
  AdjustAngle(cyc)
  ;footsteps
  If pStepTim#(cyc)>30 And pHidden(cyc)=0
   If pAnim(cyc)=12 Or pAnim(cyc)=30 Then vol#=1 Else vol#=0
   stepper=Rnd(5,6)
   If InsideRing(pX#(cyc),pZ#(cyc),20) And arenaMatting>0 Then stepper=Rnd(1,2)
   If InsideRing(pX#(cyc),pZ#(cyc),0) Then stepper=Rnd(3,4)
   If pPlatform(cyc)=>5 And pPlatform(cyc)=<8 Then stepper=Rnd(1,2)
   ProduceSound(p(cyc),sStep(stepper),22050,vol#)
   If pPlatform(cyc)=>91 And pPlatform(cyc)=<94 Then ProduceSound(p(cyc),sImpactMetal,20000,Rnd(0.1,0.5)) : ShakeCage(pPlatform(cyc)-90,0.25)
   If pPlatform(cyc)=>95 And pPlatform(cyc)=<98 Then ProduceSound(p(cyc),sImpactMetal,20000,Rnd(0.1,0.5)) : ShakeCage(pPlatform(cyc)-94,0.25)
   pStepTim#(cyc)=0
  EndIf
  ;shake ropes when animating nearby
  If (pAnim(cyc)=>120 And pAnim(cyc)=<149) Or pAnim(cyc)=151 Or pAnim(cyc)=153 Or pAnim(cyc)=155 Or pAnim(cyc)=157
   If InsideRing(pX#(cyc),pZ#(cyc),-15)
    For limb=1 To 50
     If pLimbX#(cyc,limb)<blockX2#(4) Or pLimbX#(cyc,limb)>blockX1#(2) Or pLimbZ#(cyc,limb)<blockZ2#(3) Or pLimbZ#(cyc,limb)>blockZ1#(1)
      ShakeRopes(pX#(cyc),pZ#(cyc),11,0)
     EndIf
    Next
   EndIf
  EndIf
  ;belt wearing
  If pHolding(cyc)>0 And weapFile$(weapType(pHolding(cyc)))="Belt"
   HideEntity FindChild(p(cyc),"Belt")
   HideEntity FindChild(p(cyc),"Belt01")
   HideEntity FindChild(p(cyc),"Belt02")
   If weapWear(pHolding(cyc))=0 Then ShowEntity FindChild(p(cyc),"Belt")
   If weapWear(pHolding(cyc))>0 Then ShowEntity FindChild(p(cyc),"Belt0"+weapWear(pHolding(cyc)))
  EndIf
  ;clock weapon use
  If pOutTim(cyc)>0 And pHidden(cyc)=0
   For v=1 To no_weaps
    pWeaponAccess(cyc,v)=pWeaponAccess(cyc,v)-1
    If pWeaponAccess(cyc,v)<0 Then pWeaponAccess(cyc,v)=0
   Next
  EndIf
  If pHolding(cyc)>0 Then pWeaponAccess(cyc,pHolding(cyc))=20
  ;universal move issues
  If (pAnim(cyc)=>310 And pAnim(cyc)=<499) Or (pAnim(cyc)=>510 And pAnim(cyc)=<599) Or (pAnim(cyc)=>610 And pAnim(cyc)=<699) Or (pAnim(cyc)=>710 And pAnim(cyc)=<799) Or (pAnim(cyc)=>800 And pAnim(cyc)=<899)
   DropWeapon(cyc,1000)
   If pStretched(pGrappling(cyc))=0 Then RiskExertion(cyc,1)
  EndIf
 Next
End Function

;-----------------------------------------------------------------
;////////////////////// RELATED FUNCTIONS ////////////////////////
;-----------------------------------------------------------------

;PRE CYCLE CHECKS
Function PreCycleChecks()
 For cyc=1 To no_plays  
  ;store old positions
  If ProjectedAnim(cyc)=0 Then pOldX#(cyc)=pX#(cyc) : pOldZ#(cyc)=pZ#(cyc)
  If ProjectedAnim(cyc)>0 And pExcusedWorld(cyc)=0
   If BlockConflict(RealX#(cyc),pY#(cyc),RealZ#(cyc),1)=0 And ItemConflict(RealX#(cyc),pY#(cyc),RealZ#(cyc))=0
    pOldX#(cyc)=RealX#(cyc) : pOldZ#(cyc)=RealZ#(cyc)
   EndIf
  EndIf 
  pOldY#(cyc)=pY#(cyc)
  ;reset animation offsets
  pBodyTXA#(cyc)=0 : pBodyTZA#(cyc)=0
  ;store old angle
  pOldA#(cyc)=pA#(cyc)
  ;online input reset
  If optOnline>0 Then ResetInput(cyc)
 Next
End Function

;DISPLAY PLAYERS
Function DisplayPlayers()
 For cyc=1 To no_plays 
  If pHidden(cyc)=0 
   ;facial expressions
   AdjustGaze(cyc)
   ManageEyes(cyc)
   If matchState<>2
    If GrimaceViable(cyc)=1 Then pSpeaking(cyc)=1 : pEyes(cyc)=charEyes(pChar(cyc))-1
    If GrimaceViable(cyc)=-1 Then pSpeaking(cyc)=1 : pEyes(cyc)=charEyes(pChar(cyc))+1
   EndIf
   FacialExpressions(cyc) 
   ManageScars(cyc)
   ;body weaving
   weaver#=4.0
   If pAnim(cyc)=12 Then weaver#=1.0
   If (pAnim(cyc)=301 And pOldAnim(cyc)=300) Or (pAnim(cyc)=302 And pOldAnim(pGrappler(cyc))=300) Then weaver#=1.0
   If pAnim(cyc)=>80 And pAnim(cyc)=<89 Then weaver#=2.0
   If pBodyXA#(cyc)<pBodyTXA#(cyc) Then pBodyXA#(cyc)=pBodyXA#(cyc)+weaver#
   If pBodyXA#(cyc)>pBodyTXA#(cyc) Then pBodyXA#(cyc)=pBodyXA#(cyc)-weaver#
   If pBodyXA#(cyc)=>pBodyTXA#(cyc)-weaver# And pBodyXA#(cyc)=<pBodyTXA#(cyc)+weaver# Then pBodyXA#(cyc)=pBodyTXA#(cyc)
   If pBodyZA#(cyc)<pBodyTZA#(cyc) Then pBodyZA#(cyc)=pBodyZA#(cyc)+weaver#
   If pBodyZA#(cyc)>pBodyTZA#(cyc) Then pBodyZA#(cyc)=pBodyZA#(cyc)-weaver#
   If pBodyZA#(cyc)=>pBodyTZA#(cyc)-weaver# And pBodyZA#(cyc)=<pBodyTZA#(cyc)+weaver# Then pBodyZA#(cyc)=pBodyTZA#(cyc) 
   ;update orientation
   FindElevation(cyc)
   pA#(cyc)=CleanAngle(pA#(cyc))
   RotateEntity p(cyc),0,pA#(cyc),0
   PositionEntity p(cyc),pX#(cyc),pY#(cyc)+pElevation#(cyc),pZ#(cyc)
  EndIf
  If pHidden(cyc)>0 Then HideEntity p(cyc) Else ShowEntity p(cyc)
  ;shadows
  For limb=1 To 50
   If pShadow(cyc,limb)>0
    shadowY#=wGround#+0.1
    If RealX#(cyc)=>-119 And RealX#(cyc)=<119 And RealZ#(cyc)=>-119 And RealZ#(cyc)=<119 Then shadowY#=wGround#+1.5
    If InsideRing(RealX#(cyc),RealZ#(cyc),-5) Then shadowY#=(wStage#+0.1)+ringOffsetY#(1)
    If pAnim(cyc)=40 And pAnimTim(cyc)<Int(40/pAnimSpeed#(cyc)) Then shadowY#=wGround#+1.5
    If pAnim(cyc)=43 And pAnimTim(cyc)>10 Then shadowY#=wGround#+1.5
    PositionEntity pShadow(cyc,limb),EntityX(pLimb(cyc,limb),1),shadowY#,EntityZ(pLimb(cyc,limb),1)
    RotateEntity pShadow(cyc,limb),90,EntityYaw(pLimb(cyc,limb),1),0
    If pHidden(cyc)>0 Then HideEntity pShadow(cyc,limb) Else ShowEntity pShadow(cyc,limb)
    If pScar(cyc,limb)=>5 Then HideEntity pShadow(cyc,limb)
   EndIf
  Next
  ;overhead labels
  If optLabels>0
   DisplayLabels(cyc)
  EndIf
  ;hiding cheat
  ;If KeyDown(87) And pHidden(cyc)=0 Then pHidden(cyc)=1
 Next
End Function

;DISPLAY LABELS
Function DisplayLabels(cyc)
 ;get label
 label=0
 If FindInjury(cyc)>0 Then label=labInjured ;injured
 If pSpecial(cyc)>0 Then label=labSpecial ;special
 If matchState=3
  If matchTeams=2 And LegalMan(cyc) And pChaosTim(cyc)>0 Then label=labLegal ;legal man
  If matchRules=2 And pHolding(cyc)>0 And weapWear(pHolding(cyc))=0 And pFoc(cyc)>0 And LegalMan(cyc) And InsideRing(RealX#(cyc),RealZ#(cyc),0)
   If FindReferee(cyc,pFoc(cyc))>0 And (LegalMan(pFoc(cyc)) Or pRole(pFoc(cyc))=1) Then label=labWarning ;DQ warning
  EndIf
  If pEliminated(cyc) And no_wrestlers>2 Then label=labEliminated ;eliminated
  If LegalMan(cyc)=0 And pRole(cyc)<>1 And pRole(cyc)<>3 And pChaosTim(cyc)=0 And InsideRing(RealX#(cyc),RealZ#(cyc),-15)
   label=labOut ;leave ring
  EndIf 
 EndIf
 If controlTim>0 And pControl(cyc)>0 Then label=labControl
 ;pinning status
 If matchState=3 And pRole(cyc)=0 And no_refs=0 And pPinning(cyc)>0 
  If pPinCount(pPinning(cyc))>0 Then label=labCount(pPinCount(pPinning(cyc))) ;pinner
 EndIf
 If matchState=3 And pRole(cyc)=1 And pAnim(cyc)=>179 And pAnim(cyc)=<182 And pPinner(pRefVictim(cyc))>0 
  If pPinCount(pRefVictim(cyc))>0 Then label=labCount(pPinCount(pRefVictim(cyc))) ;referee
 EndIf
 ;show tags
 If pHidden(cyc)>0 Then label=0
 If label>0
  If label<>pOldLabel(cyc)
   ShowEntity pLabel(cyc)
   EntityTexture pLabel(cyc),label 
   ScaleSprite pLabel(cyc),12,5
   EntityColor pLabel(cyc),255,255,255
   If label=labSpecial Then ScaleSprite pLabel(cyc),12,3 
   If label=labOut Then ScaleSprite pLabel(cyc),10,4 
   If label=labWarning Then ScaleSprite pLabel(cyc),9,4 
   If label=labEliminated Then ScaleSprite pLabel(cyc),10,4 
   If label=labControl Then ScaleSprite pLabel(cyc),10,4 
   If label=labCount(1) Or label=labCount(2) Or label=labCount(3) Then ScaleSprite pLabel(cyc),20,5 
  EndIf
  If label=labSpecial Or label=labWarning
   randy=Rnd(0,1)
   If randy=0 Then pLabelX#(cyc)=Rnd#(-0.25,0.25) : pLabelY#(cyc)=Rnd#(-0.25,0.25) : pLabelZ#(cyc)=Rnd#(-0.25,0.25)
  EndIf
  x#=pLimbX#(cyc,36)+pLabelX#(cyc)
  y#=(pLimbY#(cyc,36)+TranslateHeight#(cyc,23))+pLabelY#(cyc)
  z#=pLimbZ#(cyc,36)+pLabelZ#(cyc)
  PositionEntity pLabel(cyc),x#,y#,z#
 EndIf
 If label=0 And pOldLabel(cyc)>0 Then HideEntity pLabel(cyc)
 pOldLabel(cyc)=label 
End Function

;GET REAL X#
Function RealX#(cyc)
 x#=pX#(cyc)
 If cyc>0 And p(cyc)>0 And pChar(cyc)>0 And ProjectedAnim(cyc)>0
  If FindChild(p(cyc),"Hips")>0
   x#=EntityX(FindChild(p(cyc),"Hips"),1)
  EndIf
 EndIf
 Return x#
End Function

;GET REAL Y#
Function RealY#(cyc)
 y#=pY#(cyc)
 If cyc>0 And p(cyc)>0 And pChar(cyc)>0 And ProjectedAnim(cyc)>0
  If FindChild(p(cyc),"Hips")>0
   y#=EntityY(FindChild(p(cyc),"Hips"),1)
  EndIf
 EndIf
 Return y#
End Function

;GET REAL Z#
Function RealZ#(cyc)
 z#=pZ#(cyc)
 If cyc>0 And p(cyc)>0 And pChar(cyc)>0 And ProjectedAnim(cyc)>0
  If FindChild(p(cyc),"Hips")>0
   z#=EntityZ(FindChild(p(cyc),"Hips"),1)
  EndIf
 EndIf 
 Return z#
End Function

;CALCULATE CENTRE VALUES OF MULTIPLE WRESTLERS
Function IdentifyCentres(task) ;0=for ref, 1=for camera
 lowX#=9999 : highX#=-9999
 lowY#=9999 : highY#=-9999
 lowZ#=9999 : highZ#=-9999
 For v=1 To no_plays
  If (task=0 And LegalMan(v) And pOutTim(v)>0 And pHidden(v)=0) Or (task=1 And CamViable(v))
   If RealX#(v)<lowX# Then lowX#=RealX#(v)
   If RealX#(v)>highX# Then highX#=RealX#(v)
   If EyeLevel#(v)<lowY# Then lowY#=EyeLevel#(v)
   If EyeLevel#(v)>highY# Then highY#=EyeLevel#(v)
   If RealZ#(v)<lowZ# Then lowZ#=RealZ#(v)
   If RealZ#(v)>highZ# Then highZ#=RealZ#(v)
  EndIf
 Next
 centreX#=GetCentre#(lowX#,highX#)
 centreY#=GetCentre#(lowY#,highY#)
 centreZ#=GetCentre#(lowZ#,highZ#)
End Function

;PROJECT DUMMY (FROM PLAYER)
Function ProjectDummy(cyc,advanceX#,advanceY#,advanceZ#)
 PositionEntity dummy,pX#(cyc),pY#(cyc),pZ#(cyc)
 RotateEntity dummy,0,pA#(cyc),0
 MoveEntity dummy,advanceX#,advanceY#,advanceZ#
End Function

;RESET EXCUSED STATES
Function ResetExcuses(cyc,setting)
 pExcusedPlays(cyc)=setting
 pExcusedItems(cyc)=setting
 pExcusedWorld(cyc)=setting
 pExcusedEdges(cyc)=setting
End Function

;ENFORCE BLOCKS
Function EnforceBlocks(cyc)
 ;human blocks
 If pExcusedPlays(cyc)=0 And pOutTim(cyc)>0
  For v=1 To no_plays
   excused=0
   If AttackViable(v)=3 And pAnim(cyc)=>72 And pAnim(cyc)=<73 Then excused=1
   If AttackViable(v)=4 And pStretched(v)=0 Then excused=1
   If cyc<>v And pOutTim(v)>0 And pHidden(v)=0 And excused=0
    width#=10 : height#=35
    If pOldX#(cyc)>RealX#(v)-width# And pOldX#(cyc)<RealX#(v)+width# And pOldZ#(cyc)>RealZ#(v)-width# And pOldZ#(cyc)<RealZ#(v)+width#
     trapped=1
    Else
     If pX#(cyc)>RealX#(v)-width# And pX#(cyc)<RealX#(v)+width# And pZ#(cyc)>RealZ#(v)-width# And pZ#(cyc)<RealZ#(v)+width# And pY#(cyc)>pY#(v)-height# And pY#(cyc)<pY#(v)+height#
      If pOldX#(cyc)>RealX#(v)-width# And pOldX#(cyc)<RealX#(v)+width# Then pZ#(cyc)=pOldZ#(cyc)
      If pOldZ#(cyc)>RealZ#(v)-width# And pOldZ#(cyc)<RealZ#(v)+width# Then pX#(cyc)=pOldX#(cyc)
      If matchState=1 And pOutTim(cyc)=1 Then pX#(cyc)=pOldX#(cyc) : pZ#(cyc)=pOldZ#(cyc)
     EndIf
    EndIf
   EndIf
  Next
 EndIf
 ;standard world blocks
 If pExcusedWorld(cyc)=0 And ProjectedAnim(cyc)=<1
  For v=0 To 100
   excused=0
   If v=>1 And v=<4 And pExcusedEdges(cyc) Then excused=1
   If blockType(v)>0 And excused=0
    If pOldX#(cyc)>blockX1#(v) And pOldX#(cyc)<blockX2#(v) And pOldZ#(cyc)>blockZ1#(v) And pOldZ#(cyc)<blockZ2#(v)
     trapped=1
    Else
     If pX#(cyc)>blockX1#(v) And pX#(cyc)<blockX2#(v) And pZ#(cyc)>blockZ1#(v) And pZ#(cyc)<blockZ2#(v) And pY#(cyc)>blockY1#(v) And pY#(cyc)<blockY2#(v)
      If pOldX#(cyc)>blockX1#(v) And pOldX#(cyc)<blockX2#(v) Then pZ#(cyc)=pOldZ#(cyc)
	  If pOldZ#(cyc)>blockZ1#(v) And pOldZ#(cyc)<blockZ2#(v) Then pX#(cyc)=pOldX#(cyc)
	  If v=>1 And v=<4 Then ShakeRopes(pX#(cyc),pZ#(cyc),11,0)
	  ;randy=Rnd(0,100)
      ;If randy=0 And v=>5 Then pNowhere(cyc)=99 
     EndIf
    EndIf
   EndIf
  Next
 EndIf
 ;projected world blocks
 If pExcusedWorld(cyc)=0 And ProjectedAnim(cyc)=2 
  PositionEntity p(cyc),pX#(cyc),pY#(cyc),pZ#(cyc)
  For v=0 To 100
   excused=0
   If v=>1 And v=<4 And pExcusedEdges(cyc) Then excused=1
   If blockType(v)>0 And excused=0
    its=0
    Repeat
     conflict=0
     If pOldX#(cyc)>blockX1#(v) And pOldX#(cyc)<blockX2#(v) And pOldZ#(cyc)>blockZ1#(v) And pOldZ#(cyc)<blockZ2#(v)
      trapped=1
     Else
      If RealX#(cyc)>blockX1#(v) And RealX#(cyc)<blockX2#(v) And RealZ#(cyc)>blockZ1#(v) And RealZ#(cyc)<blockZ2#(v) And pY#(cyc)>blockY1#(v) And pY#(cyc)<blockY2#(v)    
       ForceMoveOut(cyc,v,RealX#(cyc),RealZ#(cyc))
       If pGrappler(cyc)>0 Then ForceMoveOut(pGrappler(cyc),v,RealX#(cyc),RealZ#(cyc))
       PositionEntity p(cyc),pX#(cyc),pY#(cyc),pZ#(cyc)
       If v=>1 And v=<4 Then ShakeRopes(RealX#(cyc),RealZ#(cyc),11,0) 
       conflict=1
      EndIf
     EndIf
     its=its+1
     If its>5 Then conflict=0
    Until conflict=0 
   EndIf
  Next
 EndIf
 ;standard item blocks
 If pExcusedItems(cyc)=0 And ProjectedAnim(cyc)=<1 And pOutTim(cyc)>1 ;And (pX#(cyc)<>pOldX#(cyc) Or pZ#(cyc)<>pOldZ#(cyc))
  For v=1 To no_items
   If ItemProximity(cyc,v,40) And pY#(cyc)=>iY#(v)-10 And pY#(cyc)=<iY#(v)+iHeight#(iType(v)) And iState(v)=0 And cyc<>iCarrier(v)
    If ItemCollide(cyc,v,pOldX#(cyc),pOldZ#(cyc),0)=0
     If ItemCollide(cyc,v,RealX#(cyc),RealZ#(cyc),0)
      If ItemCollide(cyc,v,pOldX#(cyc),RealZ#(cyc),0) Then pZ#(cyc)=pOldZ#(cyc)
      If ItemCollide(cyc,v,RealX#(cyc),pOldZ#(cyc),0) Then pX#(cyc)=pOldX#(cyc) 
      If pAnim(cyc)=506 Or pAnim(cyc)=606 Then pX#(cyc)=pOldX#(cyc) : pZ#(cyc)=pOldZ#(cyc) : pA#(cyc)=pOldA#(cyc)
      randy=Rnd(0,4)
      If randy=0 Then pNowhere(cyc)=99
      pNowhere(cyc)=pNowhere(cyc)+3
      FindItemClimbing(cyc,v) 
     EndIf
    EndIf
   EndIf
  Next
 EndIf
 ;projected item blocks
 If pExcusedItems(cyc)=0 And ProjectedAnim(cyc)=2 And pOutTim(cyc)>1 ;And (pX#(cyc)<>pOldX#(cyc) Or pZ#(cyc)<>pOldZ#(cyc)) 
  PositionEntity p(cyc),pX#(cyc),pY#(cyc),pZ#(cyc)
  For v=1 To no_items
   If ItemProximity(cyc,v,40) And pY#(cyc)=>iY#(v)-10 And pY#(cyc)=<iY#(v)+iHeight#(iType(v)) And iState(v)=0 And cyc<>iCarrier(v)
    its=0
    Repeat
     conflict=0
     If ItemCollide(cyc,v,pOldX#(cyc),pOldZ#(cyc),0)=0
      If ItemCollide(cyc,v,RealX#(cyc),RealZ#(cyc),0)   
       ForceMoveOut(cyc,v+100,RealX#(cyc),RealZ#(cyc))
       If pGrappler(cyc)>0 Then ForceMoveOut(pGrappler(cyc),v+100,RealX#(cyc),RealZ#(cyc))
       PositionEntity p(cyc),pX#(cyc),pY#(cyc),pZ#(cyc) 
       conflict=1
      EndIf
     EndIf
     its=its+1
     If its>5 Then conflict=0
    Until conflict=0 
   EndIf
  Next
 EndIf 
 ;apron limits
 If pExcusedWorld(cyc)=0 And pAnim(cyc)<>40 And pAnim(cyc)<>41 And pAnim(cyc)<>42 And pAnim(cyc)<>43 And pAnim(cyc)<>204
  If pPlatform(cyc)=1 ;north
   If pX#(cyc)<blockX2#(4)+2 Then pX#(cyc)=blockX2#(4)+2
   If pX#(cyc)>blockX1#(2)-2 Then pX#(cyc)=blockX1#(2)-2
   pZ#(cyc)=80;pOldZ#(cyc)
  EndIf
  If pPlatform(cyc)=2 ;east
   If pZ#(cyc)<blockZ2#(3)+2 Then pZ#(cyc)=blockZ2#(3)+2
   If pZ#(cyc)>blockZ1#(1)-2 Then pZ#(cyc)=blockZ1#(1)-2
   pX#(cyc)=80;pOldX#(cyc)
  EndIf
  If pPlatform(cyc)=3 ;south
   If pX#(cyc)<blockX2#(4)+2 Then pX#(cyc)=blockX2#(4)+2
   If pX#(cyc)>blockX1#(2)-2 Then pX#(cyc)=blockX1#(2)-2
   pZ#(cyc)=-80;pOldZ#(cyc)
  EndIf
  If pPlatform(cyc)=4 ;west
   If pZ#(cyc)<blockZ2#(3)+2 Then pZ#(cyc)=blockZ2#(3)+2
   If pZ#(cyc)>blockZ1#(1)-2 Then pZ#(cyc)=blockZ1#(1)-2
   pX#(cyc)=-80;pOldX#(cyc)
  EndIf
 EndIf
 ;platform confines
 If pExcusedWorld(cyc)=0
  If pPlatform(cyc)=>10 And pPlatform(cyc)=<90
   block=pPlatform(cyc)-10
   If pX#(cyc)<blockPlatX1#(block) Then pX#(cyc)=pOldX#(cyc);blockPlatX1#(block)
   If pX#(cyc)>blockPlatX2#(block) Then pX#(cyc)=pOldX#(cyc);blockPlatX2#(block)
   If pZ#(cyc)<blockPlatZ1#(block) Then pZ#(cyc)=pOldZ#(cyc);blockPlatZ1#(block)
   If pZ#(cyc)>blockPlatZ2#(block) Then pZ#(cyc)=pOldZ#(cyc);blockPlatZ2#(block)
  EndIf
  If pPlatform(cyc)=>100 Then pX#(cyc)=pOldX#(cyc) : pZ#(cyc)=pOldZ#(cyc)
 EndIf
 ;cage climbing confines
 If pAnim(cyc)<>233 And pAnim(cyc)<>234
  If pPlatform(cyc)=91 Then pZ#(cyc)=80
  If pPlatform(cyc)=92 Then pX#(cyc)=80
  If pPlatform(cyc)=93 Then pZ#(cyc)=-80
  If pPlatform(cyc)=94 Then pX#(cyc)=-80
  If pPlatform(cyc)=95 Then pZ#(cyc)=92
  If pPlatform(cyc)=96 Then pX#(cyc)=92
  If pPlatform(cyc)=97 Then pZ#(cyc)=-92
  If pPlatform(cyc)=98 Then pX#(cyc)=-92
  If pPlatform(cyc)=91 Or pPlatform(cyc)=93 Or pPlatform(cyc)=95 Or pPlatform(cyc)=97
   If pX#(cyc)<blockX2#(4)+2 Then pX#(cyc)=pOldX#(cyc)
   If pX#(cyc)>blockX1#(2)-2 Then pX#(cyc)=pOldX#(cyc)
  EndIf
  If pPlatform(cyc)=92 Or pPlatform(cyc)=94 Or pPlatform(cyc)=96 Or pPlatform(cyc)=98
   If pZ#(cyc)<blockZ2#(3)+2 Then pZ#(cyc)=pOldZ#(cyc)
   If pZ#(cyc)>blockZ1#(1)-2 Then pZ#(cyc)=pOldZ#(cyc)
  EndIf
  limit#=53.5-PercentOf#(7.0,GetPercent#(charHeight(pChar(cyc)),24))
  If pPlatform(cyc)=>91 And pPlatform(cyc)=<98 And pY#(cyc)>limit# Then pY#(cyc)=limit#
  If pPlatform(cyc)=>91 And pPlatform(cyc)=<94 And pY#(cyc)<wStage# Then pY#(cyc)=wStage#
  If pPlatform(cyc)=>95 And pPlatform(cyc)=<98 And pY#(cyc)<wGround# Then pY#(cyc)=wGround# 
 EndIf
 ;ceiling limits
 If pAnim(cyc)=54 Or pAnim(cyc)=64 Or pAnim(cyc)=74 Or pAnim(cyc)=143 Or pAnim(cyc)=144
  limit#=FindCeiling#(pX#(cyc),pZ#(cyc))-35
  If pOldY#(cyc)<limit# And pY#(cyc)>limit# Then pY#(cyc)=limit#
 EndIf
 ;clock nowhere
 If pX#(cyc)>pOldX#(cyc)-(pSpeed#(cyc)/4) And pX#(cyc)<pOldX#(cyc)+(pSpeed#(cyc)/4) And pZ#(cyc)>pOldZ#(cyc)-(pSpeed#(cyc)/4) And pZ#(cyc)<pOldZ#(cyc)+(pSpeed#(cyc)/4)
  If pAnim(cyc)=11 Or pAnim(cyc)=12 Or pAnim(cyc)=49 Or pAnim(cyc)=82 Or pAnim(cyc)=85 Or pAnim(cyc)=158 Or pAnim(cyc)=202
   If DirPressed(cyc) Or cRun(cyc)=1 Then pNowhere(cyc)=pNowhere(cyc)+2
  EndIf
 EndIf
End Function

;CREATE TEMPORARY BLOCK
Function CreateBlock(cyc,blockX#,blockZ#,range#)
 If pOldX#(cyc)=>blockX#-range# And pOldX#(cyc)=<blockX#+range# And pOldZ#(cyc)=>blockZ#-range# And pOldZ#(cyc)=<blockZ#+range#
  trapped=1
 Else
  If pX#(cyc)=>blockX#-range# And pX#(cyc)=<blockX#+range# And pZ#(cyc)=>blockZ#-range# And pZ#(cyc)=<blockZ#+range#
   If pOldX#(cyc)=>blockX#-range# And pOldX#(cyc)=<blockX#+range# Then pZ#(cyc)=pOldZ#(cyc)
   If pOldZ#(cyc)=>blockZ#-range# And pOldZ#(cyc)=<blockZ#+range# Then pX#(cyc)=pOldX#(cyc)
  EndIf
 EndIf
End Function

;CONFINE TO RING
Function ConfineToRing(cyc)
 If pZ#(cyc)>blockZ1#(1) Then pZ#(cyc)=blockZ1#(1) ;north limit
 If pX#(cyc)>blockX1#(2) Then pX#(cyc)=blockX1#(2) ;east limit
 If pZ#(cyc)<blockZ2#(3) Then pZ#(cyc)=blockZ2#(3) ;south limit 
 If pX#(cyc)<blockX2#(4) Then pX#(cyc)=blockX2#(4) ;west limit
End Function

;SCAN BODY
Function ScanBody(cyc)
 ;reset by default
 scanner=0
 For count=0 To 6
  pScanX#(cyc,count)=RealX#(cyc)
  pScanZ#(cyc,count)=RealZ#(cyc)
 Next
 ;find dimensions
 forward=0 : backward=0
 If AttackViable(cyc)=3 Then forward=1 : backward=0
 If AttackViable(cyc)=4 Then forward=1 : backward=1
 If pAnim(cyc)=64 And attackFall(5,charAttack(pChar(cyc),5))=1 Then forward=1 : backward=1
 If pAnim(cyc)=74 And crushFall(5,charCrush(pChar(cyc),5))=1 Then forward=1 : backward=1
 If pAnim(cyc)=120 Or pAnim(cyc)=121 Or pAnim(cyc)=122 Or pAnim(cyc)=124 Or pAnim(cyc)=125 Or pAnim(cyc)=129 Or pAnim(cyc)=138 Or pAnim(cyc)=139 Or pAnim(cyc)=143 Then forward=1 : backward=1
 If pAnim(cyc)=127 Or pAnim(cyc)=128 Or pAnim(cyc)=137 Then forward=1 : backward=0
 ;If pAnim(cyc)<>60 And pAnim(cyc)<>61 And pAnim(cyc)<>62 And pAnim(cyc)<>63 And pAnim(cyc)<>70 And pAnim(cyc)<>71 And pAnim(cyc)<>72 And pAnim(cyc)<>73
  If GetDistance#(pLimbX#(cyc,36),pLimbZ#(cyc,36),pLimbX#(cyc,1),pLimbZ#(cyc,1))=>10 Then backward=1
  If GetDistance#(pLimbX#(cyc,36),pLimbZ#(cyc,36),pLimbX#(cyc,41),pLimbZ#(cyc,41))=>10 Then forward=1
  If GetDistance#(pLimbX#(cyc,36),pLimbZ#(cyc,36),pLimbX#(cyc,44),pLimbZ#(cyc,44))=>10 Then forward=1
 ;EndIf
 ;forward scan
 If forward>0
  PositionEntity dummy,RealX#(cyc),pY#(cyc),RealZ#(cyc)
  RotateEntity dummy,0,pA#(cyc),0
  If ProjectedAnim(cyc)>0 Then RotateEntity dummy,0,EntityYaw(pLimb(cyc,36),1),0
  For depth=1 To forward
   MoveEntity dummy,0,0,8
   scanner=scanner+1
   pScanX#(cyc,scanner)=EntityX(dummy)
   pScanZ#(cyc,scanner)=EntityZ(dummy)
  Next
 EndIf
 ;backward scan
 If backward>0
  PositionEntity dummy,RealX#(cyc),pY#(cyc),RealZ#(cyc)
  RotateEntity dummy,0,pA#(cyc),0
  If ProjectedAnim(cyc)>0 Then RotateEntity dummy,0,EntityYaw(pLimb(cyc,36),1),0
  For depth=1 To backward
   MoveEntity dummy,0,0,-8
   scanner=scanner+1
   pScanX#(cyc,scanner)=EntityX(dummy)
   pScanZ#(cyc,scanner)=EntityZ(dummy)
  Next
 EndIf
End Function

;MANAGE ELEVATION
Function FindElevation(cyc)
 ;nothing by default
 target#=0
 ScanBody(cyc)
 ;outside matting
 If arenaMatting>0 And InsideRing(RealX#(cyc),RealZ#(cyc),0)=0 And pPlatform(cyc)=0 And RealX#(cyc)=>-119 And RealX#(cyc)=<119 And RealZ#(cyc)=>-119 And RealZ#(cyc)=<119
  target#=target#+1.1
 EndIf
 ;elevated by items
 adder#=0
 If pAnim(cyc)<>200
  For v=1 To no_items
   If ItemProximity(cyc,v,30) And iState(v)=1 And iCarrier(v)=0
    If (pY#(cyc)=>iY#(v)-5 And pY#(cyc)=<iY#(v)+15) Or pAnim(cyc)=54 Or pAnim(cyc)=64 Or pAnim(cyc)=74 Or (pAnim(cyc)=>143 And pAnim(cyc)=<146)
     ScanItem(v,-2)
     For scan=0 To 4
      If ItemCollide(cyc,v,pScanX#(cyc,scan),pScanZ#(cyc,scan),0) And iElevate#(iType(v))>adder# Then adder#=iElevate#(iType(v))
     Next
     ScanItem(v,0)
    EndIf
   EndIf
  Next
 EndIf
 target#=target#+adder#
 ;elevated by humans
 covering=0
 For v=1 To no_plays
  If cyc<>v And (pGrappling(cyc)<>v Or pAnim(cyc)=501) And pGrappler(cyc)<>v And pGrappling(v)<>cyc And pGrappler(v)=0 And AttackViable(v)=4 And (pLieTim(cyc)<pLieTim(v) Or AttackViable(cyc)<AttackViable(v))
   If (pY#(cyc)=>pY#(v)-10 And pY#(cyc)=<pY#(v)+10) Or pAnim(cyc)=54 Or pAnim(cyc)=64 Or pAnim(cyc)=74 Or (pAnim(cyc)=>143 And pAnim(cyc)=<146)
    ScanBody(v)
    For scan=0 To 6
     For count=0 To 4
      If ReachedCord(pScanX#(cyc,scan),pScanZ#(cyc,scan),pScanX#(v,count),pScanZ#(v,count),6) Or InProximity(cyc,v,8) Or (AttackViable(cyc)=3 And SatisfiedAngle(pA#(cyc),pA#(v)+180,45) And InProximity(cyc,v,12))
       If AttackViable(cyc)=3 And target#<pElevation#(v)+(ModelElevation#(pChar(v))-4.0) Then target#=pElevation#(v)+(ModelElevation#(pChar(v))-4.0)
       If AttackViable(cyc)<>3 And target#<pElevation#(v)+ModelElevation#(pChar(v)) Then target#=pElevation#(v)+ModelElevation#(pChar(v))
       ;acknowledge casual pins
       If InLine(cyc,p(v),90) Or SatisfiedAngle(pA#(cyc),pA#(v)+180,45) Or AttackViable(cyc)<>3
        If AttackViable(cyc)=>3 And pAnim(v)=150 And pPinning(cyc)=0 And pPinning(v)=0 And pPinner(cyc)=0 And pPinner(v)=0 ;And pElevation#(cyc)=>pElevation#(v) 
         pPinning(cyc)=v : pPinner(v)=cyc 
         pAutoTim(cyc)=0 : pWarned(cyc)=0
        EndIf
        covering=1
       EndIf
      EndIf
     Next
    Next
   EndIf
  EndIf
 Next
 ;lose casual pin
 v=pPinning(cyc)
 If v>0 And pAnim(cyc)<>160 And pAnim(cyc)<>161 And pAnim(cyc)<300
  If covering=0 Or pAnim(v)<>150 Or AttackViable(cyc)=<2
   If matchState=3 And pPinCount(v)=0 Then Pop(v,Rnd(2,8),0.5) : entScore=entScore+Rnd(charPopularity(pChar(v))/4,charPopularity(pChar(v))/2)
   If matchState=3 And pPinCount(v)=1 Then Pop(v,Rnd(2,9),0) : entScore=entScore+Rnd(charPopularity(pChar(v)),charPopularity(pChar(v))*2)
   If matchState=3 And pPinCount(v)=>2 Then Pop(v,Rnd(2,9),1) : Pop(cyc,Rnd(2,9),0) : entScore=entScore+Rnd(charPopularity(pChar(v))*2,charPopularity(pChar(v))*4)
   pPinner(v)=0 : pPinning(cyc)=0
   If pAnim(v)<>150 And AttackViable(cyc)=>3 Then GroundReaction(cyc)
  EndIf
 EndIf
 ;force pinners on top
 If pPinning(cyc)>0 And target#<pElevation#(pPinning(cyc)) Then target#=pElevation#(pPinning(cyc))
 ;negate elevation
 If pAnim(cyc)=>45 And pAnim(cyc)=<49 Then target#=0
 ;pursue target
 If target#>10 Then target#=10
 For count=1 To 5
  If pElevation#(cyc)<target# Then pElevation#(cyc)=pElevation#(cyc)+0.1
  If pElevation#(cyc)>target# Then pElevation#(cyc)=pElevation#(cyc)-0.1
  If pElevation#(cyc)=>target#-0.1 And pElevation#(cyc)=<target#+0.1 Then pElevation#(cyc)=target#
 Next
End Function

;TRANSLATE VALUE IN TERMS OF HEIGHT
Function TranslateHeight#(cyc,height#) 
 offset#=Float#(12-charHeight(pChar(cyc)))/5
 height#=height#-offset#
 Return height#
End Function

;AUTOMATICALLY ADJUST ANGLE
Function AdjustAngle(cyc)
 If TurnViable(cyc)>0
  If pDizzyTim(cyc)=0 And pBlindTim(cyc)=0
   ;focus on human
   If pFoc(cyc)>0
    PointEntity p(cyc),FindChild(p(pFoc(cyc)),"Hips")
    If pAnim(cyc)=178 Or pAnim(cyc)=179 Then PointEntity p(cyc),FindChild(p(pFoc(cyc)),"Head")
    pTA#(cyc)=CleanAngle#(EntityYaw(p(cyc)))
    If pAnim(cyc)=69 Then pTA#(cyc)=CleanAngle#(pTA#(cyc)+180)
    RotateEntity p(cyc),0,pA#(cyc),0
   EndIf
   ;manual walking focus
   If pAnim(cyc)=11 And pFoc(cyc)=0 And pControl(cyc)>0 And DirPressed(cyc) Then pTA#(cyc)=RequestAngle#(cyc)
   If (pAnim(cyc)=11 And pFoc(cyc)=0 And pControl(cyc)=0) Or (matchState=1 And matchEnter=cyc And pOutTim(cyc)=<1)
    PositionEntity dummy,pActX#(cyc),pY#(cyc),pActZ#(cyc)
    PointEntity p(cyc),dummy
    pTA#(cyc)=CleanAngle#(EntityYaw(p(cyc)))
    RotateEntity p(cyc),0,pA#(cyc),0
   EndIf
   If pAnim(cyc)<11 And pFoc(cyc)=0 And pControl(cyc)>0 And DirPressed(cyc)=0 Then pTA#(cyc)=pA#(cyc)
   ;running orientation
   If pAnim(cyc)=12 And pControl(cyc)>0 And DirPressed(cyc) Then pTA#(cyc)=RequestAngle#(cyc)
   If pAnim(cyc)=12 And pControl(cyc)=0 
    PositionEntity dummy,pActX#(cyc),pY#(cyc),pActZ#(cyc)
    PointEntity p(cyc),dummy
    pTA#(cyc)=CleanAngle#(EntityYaw(p(cyc)))
    RotateEntity p(cyc),0,pA#(cyc),0
   EndIf
   If pMomentum(cyc)>0 Then pTA#(cyc)=pA#(cyc)
   ;perched movement
   If pAnim(cyc)=>48 And pAnim(cyc)=<49 And DirPressed(cyc) Then pTA#(cyc)=RequestAngle#(cyc)
   ;flying angle
   If pAnim(cyc)=74 And pY#(cyc)>pGround#(cyc)+0.2
    If DirPressed(cyc) Then pTA#(cyc)=RequestAngle#(cyc) Else pTA#(cyc)=pA#(cyc)
   EndIf
   ;crawling orientation
   If pAnim(cyc)=158 And DirPressed(cyc) Then pTA#(cyc)=RequestAngle#(cyc)
   ;dragging orientation
   If pAnim(cyc)=202
    If DirPressed(cyc) Then pTA#(cyc)=RequestAngle#(cyc)+180 Else pTA#(cyc)=pA#(cyc)
   EndIf
  EndIf 
  ;dizzy state
  If pDizzyTim(cyc)>0 Or pBlindTim(cyc)>0
   If pAnim(cyc)=11 Then pTA#(cyc)=CleanAngle#(RequestAngle#(cyc)+180) Else pTA#(cyc)=pA#(cyc)
  EndIf
  ;entrance angle
  If (matchState=1 Or matchEnter=cyc) And pOutTim(cyc)=1
   PositionEntity dummy,0,pY#(cyc),0
   PointEntity p(cyc),dummy
   pTA#(cyc)=CleanAngle#(EntityYaw(p(cyc)))
   RotateEntity p(cyc),0,pA#(cyc),0
  EndIf
  ;pursue target
  If pA#(cyc)<>pTA#(cyc)
   If pAnim(cyc)<10 And SatisfiedAngle(pA#(cyc),pTA#(cyc),15)=0 Then ChangeAnim(cyc,10)
   If pAnim(cyc)=48 And SatisfiedAngle(pA#(cyc),pTA#(cyc),5)=0 Then ChangeAnim(cyc,49)
   turner#=pSpeed#(cyc)*3
   If pFoc(cyc)=0 Then turner#=turner#+(turner#/2)
   If TurnViable(cyc)=2 Then turner#=pSpeed#(cyc)
   If pDizzyTim(cyc)>0 Or pBlindTim(cyc)>0 Then turner#=turner#/2
   If TurnViable(cyc)=1 And pAnim(cyc)<>155 And pAnim(cyc)<>157 And pAnim(pFoc(cyc))<>30
    turner#=BoostTurn#(pA#(cyc),pTA#(cyc),turner#,3)
   EndIf
   If camType=>8 And camType=<9 And cyc=camFoc And pFoc(cyc)=0 Then turner#=turner#/2
   pA#(cyc)=pA#(cyc)+ReachAngle#(pA#(cyc),pTA#(cyc),turner#)
   pA#(cyc)=CleanAngle#(pA#(cyc))
   If SatisfiedAngle(pA#(cyc),pTA#(cyc),turner#*2) Then pA#(cyc)=pTA#(cyc)
  EndIf
 EndIf
End Function

;MAGNIFY TURNING SPEED
Function BoostTurn#(sA#,tA#,speed#,factor)
 turner#=speed#
 For count=1 To 18
  If SatisfiedAngle(sA#,tA#,count*10)=0 Then turner#=turner#+(speed#/3)
 Next
 Return turner#
End Function

;SCAR SPECIFIC LIMB
Function ScarLimb(cyc,limb,chance)
 If optGore=>4 And matchBlood=0 Then chance=chance/2
 randy=Rnd(0,chance)
 If randy=0 And pScar(cyc,limb)=<4
  ;add scarring
  pScar(cyc,limb)=pScar(cyc,limb)+1 
  If pScar(cyc,limb)>4 Then pScar(cyc,limb)=4
  If pScar(cyc,limb)=>2 And limb>0 And pLimb(cyc,limb)>0
   If MajorLimb(limb) Then ProduceSound(p(cyc),sBleed,22050,Float#(pScar(cyc,limb))*0.15)
   If MajorLimb(limb) Then CreateParticle(pLimbX#(cyc,limb),pLimbY#(cyc,limb),pLimbZ#(cyc,limb),-1,15)
   If MajorLimb(limb)=0 Then CreateParticle(pLimbX#(cyc,limb),pLimbY#(cyc,limb),pLimbZ#(cyc,limb),-1,6) 
   If MajorLimb(limb)
    If optGore=>4 Then entHardcore=entHardcore+pScar(cyc,limb) Else entHardcore=entHardcore+(10*pScar(cyc,limb))
   EndIf
   If pScar(cyc,limb)=>3 Then LoseLimb(cyc,limb,chance*15)
  EndIf
  ;head damage
  If limb=1
   If matchState=3 And matchBlood>0 And LegalMan(cyc) Then entScore=entScore+(charPopularity(pChar(cyc))*pScar(cyc,limb))
   If pScar(cyc,limb)=>1
    LoseLimb(cyc,2,chance*15) : LoseLimb(cyc,3,chance*15)
    LoseLimb(cyc,45,chance*15) : LoseLimb(cyc,46,chance*15)
   EndIf
   If pBlindTim(cyc)=0 Then pBlindTim(cyc)=Rnd(0,250)
   randy=Rnd(0,10)
   If randy=0
    RemoveHeadwear(cyc)
    If charHatStyle(pChar(cyc),pCostume(cyc))>0 
     ApplyHairstyle(cyc,0)
    EndIf
   EndIf
   If randy=1 Then HideEntity FindChild(p(cyc),"Specs")
   If randy=>2 And randy=<3 Then HideEntity FindChild(p(cyc),"Lens01")
   If randy=>4 And randy=<5 Then HideEntity FindChild(p(cyc),"Lens01")  
  EndIf 
 EndIf
End Function

;SCAR WHOLE BODY
Function ScarArea(cyc,x#,y#,z#,chance)
 If optGore=>4 And matchBlood=0 Then chance=chance/2
 For limb=0 To 50
  If limb=1 Then risk=chance*2 Else risk=chance
  If limb=1 And risk<1 Then risk=1
  If x#=0 And y#=0 And z#=0 
   ScarLimb(cyc,limb,risk)
  Else
   If pLimb(cyc,limb)>0
    If x#>pLimbX#(cyc,limb)-5 And x#<pLimbX#(cyc,limb)+5 And z#>pLimbZ#(cyc,limb)-5 And z#<pLimbZ#(cyc,limb)+5 And y#>pLimbY#(cyc,limb)-5 And y#<pLimbY#(cyc,limb)+5
     ScarLimb(cyc,limb,chance)
    EndIf
   EndIf
  EndIf
 Next
End Function

;LOSE LIMB
Function LoseLimb(cyc,limb,chance)
 ;risk loss
 If chance<5 Then chance=5
 If optGore=>4 Then chance=chance*10
 If optGore=>4 And game=1 Then chance=chance*2
 If MajorLimb(limb) Then chance=chance*4
 If (limbPrecede(limb)>0 And pScar(cyc,limbPrecede(limb))=<1) Or (limbSource(limb)>0 And pScar(cyc,limbSource(limb))=<1)
  chance=chance*2
 EndIf
 randy=Rnd(0,chance)
 If randy=0 And optGore=>3 And pLimb(cyc,limb)>0 And limbSource(limb)>0 And pScar(cyc,limbSource(limb))=<4
  ;If (pScar(cyc,limbPrecede(limb))=>2 Or limbPrecede(limb)=0) And (pScar(cyc,limbSource(limb))=>2 Or limbSource(limb)=0)
   ;pain and mess
   ProduceSound(p(cyc),sBleed,22050,1)
   If MajorLimb(limb) Or (limb=>45 And limb=<46)
    Pop(0,Rnd(2,8),1) : Pop(0,Rnd(2,8),1)
    ProduceSound(0,sAgony(Rnd(1,3)),GetVoice(cyc),RoomVolume#(RealX#(cyc),RealZ#(cyc),1))
    BloodSpurt(pLimbX#(cyc,limb),pLimbY#(cyc,limb),pLimbZ#(cyc,limb),-1,20,1)
    pHealth(cyc)=pHealth(cyc)/2
    If pDT(cyc)<500 Then pDT(cyc)=500
    If limb=>19 And limb=<31 Then pInjured(cyc,2)=1000000
    If limb=>36 Then pInjured(cyc,4)=1000000
    If limb=>45 And limb=<46 Then pInjured(cyc,5)=10000 : pBlindTim(cyc)=10000
    If FindInjury(cyc)=<1 Then pInjured(cyc,0)=1000000
    entScore=entScore+1000
   Else
    Pop(0,Rnd(2,8),1)
    ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),1)
    BloodSpurt(pLimbX#(cyc,limb),pLimbY#(cyc,limb),pLimbZ#(cyc,limb),-1,20,0)
    If limb=>2 And limb=<3 Then pInjured(cyc,5)=5000
    If limb=>21 And limb=<31 Then pInjured(cyc,1)=1000000
    entScore=entScore+250
   EndIf
   If AttackViable(cyc)=>1 And AttackViable(cyc)=<2 Then pHP(cyc)=0
   DropWeapon(cyc,0)
   ;queue reaction
   If limb=>2 And limb=<3 Then pReaction(cyc)=110
   If limb=>21 And limb=<31 Then pReaction(cyc)=111
   If limb=>19 And limb=<20 Then pReaction(cyc)=112
   If limb=>36 Then pReaction(cyc)=114
   If limb=>45 And limb=<46 Then pReaction(cyc)=110
   ;remove limb
   pScar(cyc,limb)=5
   SeverLimbs(cyc)
   EntityAlpha pLimb(cyc,limb),0 
   If limb=39 Then EntityAlpha pLimb(cyc,37),0 
   If limb=42 Then EntityAlpha pLimb(cyc,38),0  
  ;EndIf
 EndIf
End Function

;SEVER LIMBS
Function SeverLimbs(cyc) 
 ;ears
 If pScar(cyc,2)=5 And pScar(cyc,3)=<4 Then EntityTexture pLimb(cyc,1),tSeverEars(1),0,6
 If pScar(cyc,2)=<4 And pScar(cyc,3)=5 Then EntityTexture pLimb(cyc,1),tSeverEars(2),0,6
 If pScar(cyc,2)=5 And pScar(cyc,3)=5 Then EntityTexture pLimb(cyc,1),tSeverEars(3),0,6
 ;eyes
 If pScar(cyc,45)=5 And pScar(cyc,46)=<4 Then EntityTexture pLimb(cyc,1),tSeverEyes(1),0,7
 If pScar(cyc,45)=<4 And pScar(cyc,46)=5 Then EntityTexture pLimb(cyc,1),tSeverEyes(2),0,7
 If pScar(cyc,45)=5 And pScar(cyc,46)=5 Then EntityTexture pLimb(cyc,1),tSeverEyes(3),0,7
 ;torso
 If pScar(cyc,6)=5 And pScar(cyc,19)=<4 Then EntityTexture pLimb(cyc,5),tSeverBody(1),0,6
 If pScar(cyc,6)=<4 And pScar(cyc,19)=5 Then EntityTexture pLimb(cyc,5),tSeverBody(2),0,6
 If pScar(cyc,6)=5 And pScar(cyc,19)=5 Then EntityTexture pLimb(cyc,5),tSeverBody(3),0,6
 ;arms
 For limb=6 To 31
  If pScar(cyc,limb)=5
   If limbSource(limb)=>9 And limbSource(limb)=<18 Then EntityTexture pLimb(cyc,limbSource(limb)),tSeverHand(1),0,6
   If limbSource(limb)=>22 And limbSource(limb)=<31 Then EntityTexture pLimb(cyc,limbSource(limb)),tSeverHand(1),0,6
   If limbSource(limb)=8 Or limbSource(limb)=21 Then EntityTexture pLimb(cyc,limbSource(limb)),tSeverHand(2),0,6
   If limbSource(limb)=7 Or limbSource(limb)=20 Then EntityTexture pLimb(cyc,limbSource(limb)),tSeverArm(2),0,6
   If limbSource(limb)=6 Or limbSource(limb)=19 Then EntityTexture pLimb(cyc,limbSource(limb)),tSeverArm(3),0,6  
  EndIf
 Next
 ;legs
 For limb=39 To 44
  If pScar(cyc,limb)=5
   If limbSource(limb)=40 Or limbSource(limb)=43 Then EntityTexture pLimb(cyc,limbSource(limb)),tSeverLegs(1),0,6
   If limbSource(limb)=39 Or limbSource(limb)=42 Then EntityTexture pLimb(cyc,limbSource(limb)),tSeverLegs(2),0,6
   If limbSource(limb)=36 Then EntityTexture pLimb(cyc,limbSource(limb)),tSeverLegs(3),0,6
  EndIf
 Next
End Function

;MANAGE SCARS
Function ManageScars(cyc)
 For limb=0 To 50
  ;inherit limb loss
  If limb=<4 Then pScar(cyc,limb)=pScar(cyc,1)
  If pScar(cyc,limb)=5 And limbPrecede(limb)>0 And pScar(cyc,limbPrecede(limb))<5
   EntityAlpha pLimb(cyc,limbPrecede(limb)),0
   pScar(cyc,limbPrecede(limb))=5
  EndIf
  If limbSource(limb)>0 And pScar(cyc,limbSource(limb))=5 And pScar(cyc,limb)<5
   EntityAlpha pLimb(cyc,limb),0
   pScar(cyc,limb)=5
  EndIf 
  If pScar(cyc,limb)=<4 And pLimb(cyc,limb)>0
   ;heal scars
   randy=Rnd(0,10000)
   If randy=0 And (matchBlood=0 Or limb<>1) Then pScar(cyc,limb)=pScar(cyc,limb)-1
   If pScar(cyc,limb)<0 Then pScar(cyc,limb)=0 
   ;remove stains
   If randy=0 And limb=1 Then EntityTexture pLimb(cyc,limb),tFaceScar(0),0,6
   ;prevent gore
   If (optGore=0 Or limb=0) And pScar(cyc,limb)>1 Then pScar(cyc,limb)=1
   ;apply scars (standard)
   If pScar(cyc,limb)<>pOldScar(cyc,limb)
    If limb=<4 Then EntityTexture pLimb(cyc,limb),tFaceScar(pScar(cyc,1)),0,5
    If limb=>45 And limb=<46 Then EntityTexture pLimb(cyc,limb),tEyeScar(pScar(cyc,limb)),0,5
    If limb=5 Then EntityTexture pLimb(cyc,limb),tBodyScar(pScar(cyc,limb)),0,5
    If (limb=>6 And limb=<7) Or (limb=>19 And limb=<20) Then EntityTexture pLimb(cyc,limb),tArmScar(pScar(cyc,limb)),0,5
    If (limb=>8 And limb=<18) Or (limb=>21 And limb=<31) Then EntityTexture pLimb(cyc,limb),tHandScar(pScar(cyc,limb)),0,5
    If limb=>36 And limb=<44 Then EntityTexture pLimb(cyc,limb),tLegScar(pScar(cyc,limb)),0,5
   EndIf
   ;store status
   pOldScar(cyc,limb)=pScar(cyc,limb)
  EndIf
 Next 
End Function

;COUNT SCARS
Function CountScars(cyc)
 value=0
 For limb=1 To 50
  If pLimb(cyc,limb)>0 And pScar(cyc,limb)=>2 And MajorLimb(limb)
   If limb=5 Then value=value+2 Else value=value+1
  EndIf
 Next 
 Return value
End Function

;MEASURE BLOOD AT CO-ORDINATES
Function FindBlood(cyc,x#,y#,z#)
 value=0 : range#=5.0
 For limb=1 To 50
  If pLimb(cyc,limb)>0 And MajorLimb(limb)
   doit=0
   If y#>pLimbY#(cyc,limb)-range# And y#<pLimbY#(cyc,limb)+range#
    If x#>pLimbX#(cyc,limb)-range# And x#<pLimbX#(cyc,limb)+range# And z#>pLimbZ#(cyc,limb)-range# And z#<pLimbZ#(cyc,limb)+range# Then doit=1
   EndIf
   If x#=0 And y#=0 And z#=0 Then doit=1 
   gore=pScar(cyc,limb)
   If limb=39 Or limb=42 Then gore=(pScar(cyc,limb)+pScar(cyc,36))/2
   If doit=1 And gore>1 And gore>value Then value=gore
  EndIf
 Next
 If value<0 Then value=0
 If value>4 Then value=4
 Return value
End Function

;MONITOR STATUS
Function MonitorStatus(cyc) 
 ;dizzy state
 pDizzyTim(cyc)=pDizzyTim(cyc)-1
 If pDizzyTim(cyc)>0 And pInjured(cyc,5)>0 Then pDizzyTim(cyc)=pDizzyTim(cyc)+Rnd(0,1)
 If pSpecial(cyc)>0 Or pOutCount(cyc)=>8 Then pDizzyTim(cyc)=pDizzyTim(cyc)-10
 If pDizzyTim(cyc)=1 And pBlindTim(cyc)=0 And pAnim(cyc)<40 Then ChangeAnim(cyc,110)
 If pDizzyTim(cyc)<0 Then pDizzyTim(cyc)=0
 pBlindTim(cyc)=pBlindTim(cyc)-1
 If pSpecial(cyc)>0 Or pOutCount(cyc)=>8 Then pBlindTim(cyc)=pBlindTim(cyc)-10
 If pBlindTim(cyc)=1 And pDizzyTim(cyc)=0 And pAnim(cyc)<40 Then ChangeAnim(cyc,110)
 If pBlindTim(cyc)<0 Then pBlindTim(cyc)=0 
 If (pDizzyTim(cyc)>0 Or pBlindTim(cyc)>0) And pAnim(cyc)<40
  randy=Rnd(0,200)
  If randy=0 Then ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5) : pHeat(cyc)=pHeat(cyc)-1
  If randy=<50 Then pHealth(cyc)=pHealth(cyc)-1
  chance=pHP(cyc)*optLength
  If chance<100*optLength Then chance=100*optLength
  randy=Rnd(0,chance)
  If randy=0 Then pHP(cyc)=0 : pHurtA#(cyc)=Rnd(0,360)
  DropWeapon(cyc,100)
 EndIf
 ;injured state
 If matchState=3
  For count=0 To 5
   If charInjured(pChar(cyc),count)=0 Or game=0 Then pInjured(cyc,count)=pInjured(cyc,count)-1
   If pInjured(cyc,count)<0 Then pInjured(cyc,count)=0
  Next
 EndIf
 If FindInjury(cyc)>0 And AttackViable(cyc)=1 And pOutTim(cyc)>0
  randy=Rnd(0,pToughness(cyc)*4) 
  If randy=0 Then ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),Rnd(0.1,0.5))
  If randy=<5
   pHealth(cyc)=pHealth(cyc)-10
   If FindInjury(cyc)>1 And AttackViable(cyc)=1 Then pHP(cyc)=pHP(cyc)-10 : pHurtA#(cyc)=RandomFall#(pA#(cyc)+180)
   If randy=<1
    For limb=0 To 5
     If pInjured(cyc,limb)>5000 And charInjured(pChar(cyc),limb)=0 And limb<>1 And limb<>2
      pHP(cyc)=0 : pHurtA#(cyc)=RandomFall#(pA#(cyc)+180)
      If pDT(cyc)<optLieHP Then pDT(cyc)=pDT(cyc)+Rnd(0,100)
     EndIf
    Next
   EndIf
  EndIf
 EndIf
 If pAnim(cyc)<30 And pDizzyTim(cyc)=0 And pBlindTim(cyc)=0 And pMomentum(cyc)=0 And pSpecial(cyc)=0
  randy=Rnd(0,pToughness(cyc)*30)
  If randy=1 And pInjured(cyc,1)>0 Then ChangeAnim(cyc,111)
  If randy=2 And pInjured(cyc,2)>0 Then ChangeAnim(cyc,112)
  If randy=3 And pInjured(cyc,3)>0 Then ChangeAnim(cyc,113)
  If randy=4 And (pInjured(cyc,4)>0 Or pInjured(cyc,0)>0) Then ChangeAnim(cyc,114)
  If randy=5 And pInjured(cyc,5)>0 Then ChangeAnim(cyc,110) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0)
 EndIf
 ;limb logic
 If pScar(cyc,21)=5 Then DropWeapon(cyc,0)
 If pScar(cyc,8)=5 And pScar(cyc,21)=5 Then DropItem(cyc) 
 If pScar(cyc,40)=5 And pScar(cyc,43)=5
  If AttackViable(cyc)=>1 And AttackViable(cyc)=<2 Then pHP(cyc)=0 
 EndIf
 If pScar(cyc,39)=5 And pScar(cyc,42)=5 And pDT(cyc)<250 Then pDT(cyc)=250
 ;down time
 pDT(cyc)=pDT(cyc)-1
 If pPinner(cyc)>0
  pDT(cyc)=pDT(cyc)-Rnd(0,1)
  If pPinCount(cyc)=>2 Then pDT(cyc)=pDT(cyc)-Rnd(0,2)
  If pPinCount(cyc)>0 And pAnim(pPinner(cyc))<>161 Then pDT(cyc)=pDT(cyc)-Rnd(0,1)
 EndIf
 randy=Rnd(0,1)
 If (randy=0 And pHealth(cyc)<100) Or FindInjury(cyc)>1 Then pDT(cyc)=pDT(cyc)+Rnd(0,1)
 healthFactor=GetPercent#(pHealth(cyc),5000*optLength)
 chance=3000-((pPopularity(cyc)*10)+(healthFactor*10))
 randy=Rnd(0,chance)
 If randy=<3 Or pSpecial(cyc)>0 Or pOutCount(cyc)=>8 Then pDT(cyc)=pDT(cyc)-(pDT(cyc)/10)
 If randy=>4 And randy=<5 Then pDT(cyc)=pDT(cyc)-(pDT(cyc)/5)
 If randy=6 Then pDT(cyc)=pDT(cyc)/2
 If randy=7 Then pDT(cyc)=0
 If pDT(cyc)<0 Then pDT(cyc)=0 
 ;lying heirarchy
 If AttackViable(cyc)=>2 Then pLieTim(cyc)=pLieTim(cyc)+1 Else pLieTim(cyc)=0
 ;KO entertainment
 If RefViable(cyc)=3 Then entScore=entScore+1
 ;health management
 If matchState<>4 And FindInjury(cyc)=0 And pDizzyTim(cyc)=0 And pBlindTim(cyc)=0 And pStretched(cyc)=0 And pGrappler(cyc)=0
  chance=(110-pStamina(cyc))*4
  If matchShoot>0 Then chance=chance*2
  If LegalMan(cyc)=0 Then chance=chance*2
  If pSpecial(cyc)>0 Then chance=chance/4
  If pHealth(cyc)=<0 And matchKOs>0 And AttackViable(cyc)=>3 Then chance=chance*10
  If pDT(cyc)<optLieHP And pPinner(cyc)=0 And pGrappler(cyc)=0 And (DirPressed(cyc)=0 Or ActionPressed(cyc)=0)
   If pAnim(cyc)=0 Or pAnim(cyc)=48 Or pAnim(cyc)=150 Or pAnim(cyc)=152 Or pAnim(cyc)=154 Or pAnim(cyc)=156 Then chance=chance/2
  EndIf
  randy=Rnd(0,chance)
  If randy=<10 And pAnim(cyc)<>12 Then pHealth(cyc)=pHealth(cyc)+10
 EndIf
 If pHealth(cyc)<0 Then pHealth(cyc)=0 
 If pHealthLimit(cyc)>pHealth(cyc)+(1250*optLength) And pRole(cyc)=0 Then pHealthLimit(cyc)=pHealthLimit(cyc)-1
 If pHealth(cyc)>pHealthLimit(cyc) Then pHealth(cyc)=pHealthLimit(cyc)  
 ;meter tracking
 If pShowHealth(cyc)<pHealth(cyc) Then pShowHealth(cyc)=pShowHealth(cyc)+100
 If pShowHealth(cyc)>pHealth(cyc) Then pShowHealth(cyc)=pShowHealth(cyc)-100
 If pShowHealth(cyc)=>pHealth(cyc)-100 And pShowHealth(cyc)=<pHealth(cyc)+100 Then pShowHealth(cyc)=pHealth(cyc)
 ;HP management
 If FindInjury(cyc)=<1 And pDizzyTim(cyc)=0 And pBlindTim(cyc)=0 And pStretched(cyc)=0
  chance=(110-pStamina(cyc))*2
  If pSpecial(cyc)>0 Then chance=chance/4
  If DirPressed(cyc)=0 Or ActionPressed(cyc)=0 Then chance=chance/2
  randy=Rnd(0,chance)
  If randy=<10 And pHP(cyc)>0 And pAnim(cyc)<>12 Then pHP(cyc)=pHP(cyc)+1
 EndIf
 If pHP(cyc)<0 Then pHP(cyc)=0 
 healthChunk=pHealth(cyc)/10
 If healthChunk<100 Then healthChunk=100
 If pHP(cyc)>healthChunk Then pHP(cyc)=healthChunk 
 ;heat management
 If matchState=>2 And pHeat(cyc)<100
  If pHeat(cyc)>50 Or AttackViable(cyc)=>3 Or pDizzyTim(cyc)>0 Or pBlindTim(cyc)>0 Or pMomentum(cyc)>0 Or pStretched(cyc)>0 Or (LegalMan(cyc) And InsideRing(pX#(cyc),pZ#(cyc),0)=0 And matchCountOuts>0)
   chance=pPopularity(cyc)*15
   If pDizzyTim(cyc)>0 Or pBlindTim(cyc)>0 Or pMomentum(cyc)>0 Or (LegalMan(cyc) And InsideRing(pX#(cyc),pZ#(cyc),0)=0 And matchCountOuts>0) Then chance=chance/2
   If pSpecial(pFoc(cyc))>0 And InProximity(cyc,pFoc(cyc),50) Then chance=chance/2
   randy=Rnd(0,chance)
   If randy=<1 Then pHeat(cyc)=pHeat(cyc)-1
  Else
   If pHeat(cyc)=<50
    chance=(120-pPopularity(cyc))*2
    randy=Rnd(0,chance)
    If randy=<1 Then pHeat(cyc)=pHeat(cyc)+1 
   EndIf 
  EndIf
 EndIf
 If pHeat(cyc)<0 Then pHeat(cyc)=0
 If pHeat(cyc)>100 Then pHeat(cyc)=100
 pSpecial(cyc)=pSpecial(cyc)-1
 If pSpecial(cyc)=>1 And pSpecial(cyc)=<10 Then pHeat(cyc)=25
 If pSpecial(cyc)<0 Then pSpecial(cyc)=0 
 ;heat meter
 If pShowHeat(cyc)<pHeat(cyc) Then pShowHeat(cyc)=pShowHeat(cyc)+1
 If pShowHeat(cyc)>pHeat(cyc) Then pShowHeat(cyc)=pShowHeat(cyc)-1
 If pShowHeat(cyc)=>pHeat(cyc)-1 And pShowHeat(cyc)=<pHeat(cyc)+1 Then pShowHeat(cyc)=pHeat(cyc)
 If pSpecial(cyc)>0 Then pShowHeat(cyc)=100
 ;exhaustion
 exhaust=75+(pHealth(cyc)/(200*optLength))
 If pSpecial(cyc)>0 Then exhaust=110
 pPopularity(cyc)=charPopularity(pChar(cyc))
 pStrength(cyc)=PercentOf#(charStrength(pChar(cyc)),exhaust)
 If pStrength(cyc)<50 Then pStrength(cyc)=50
 If pStrength(cyc)>100 Then pStrength(cyc)=100
 pSkill(cyc)=PercentOf#(charSkill(pChar(cyc)),exhaust)
 If pSkill(cyc)<50 Then pSkill(cyc)=50
 If pSkill(cyc)>100 Then pSkill(cyc)=100
 pAgility(cyc)=PercentOf#(charAgility(pChar(cyc)),exhaust)
 If pAgility(cyc)<50 Then pAgility(cyc)=50
 If pAgility(cyc)>100 Then pAgility(cyc)=100
 pStamina(cyc)=charStamina(pChar(cyc))
 pToughness(cyc)=PercentOf#(charToughness(pChar(cyc)),exhaust)
 If pToughness(cyc)<50 Then pToughness(cyc)=50
 If pToughness(cyc)>100 Then pToughness(cyc)=100
 ;stat handicap
 handicap=0
 If (optLevel=0 And pControl(cyc)=0) Or (optLevel=6 And pControl(cyc)>0) Then handicap=-5
 If (optLevel=0 And pControl(cyc)>0) Or (optLevel=6 And pControl(cyc)=0) Then handicap=5
 pPopularity(cyc)=pPopularity(cyc)+handicap
 pStrength(cyc)=pStrength(cyc)+handicap 
 pSkill(cyc)=pSkill(cyc)+handicap
 pAgility(cyc)=pAgility(cyc)+handicap 
 pStamina(cyc)=pStamina(cyc)+handicap
 pToughness(cyc)=pToughness(cyc)+handicap 
 ;movement speed
 defaultSpeed#=0.1+(Float(pAgility(cyc))/125)
 If defaultSpeed#<0.4 Then defaultSpeed#=0.4
 pSpeed#(cyc)=defaultSpeed#
 If pAnim(cyc)<40 And pMomentum(cyc)=0
  If FindInjury(cyc)>1 And pInjured(cyc,4)=0 Then pSpeed#(cyc)=defaultSpeed#-(defaultSpeed#/3)
  If pInjured(cyc,4)>0 Then pSpeed#(cyc)=defaultSpeed#/2
  If pDizzyTim(cyc)>0 Or pBlindTim(cyc)>0 Then pSpeed#(cyc)=defaultSpeed#/3
 EndIf
End Function

;RISK TIREDNESS
Function RiskExertion(cyc,level)
 randy=Rnd(0,pStamina(cyc))
 If randy=<10*level And pRole(cyc)=0
  pHealth(cyc)=pHealth(cyc)-10
  If pHealthLimit(cyc)>1250*optLength Then pHealthLimit(cyc)=pHealthLimit(cyc)-10
 EndIf
End Function

;RISK INJURY
Function RiskInjury(cyc,limb,factor)
 factor=factor*optLength
 chance=pToughness(cyc)*factor
 randy=Rnd(0,chance)
 If randy=<0 And pInjured(cyc,limb)=0 And pSpecial(cyc)=0
  ProduceSound(0,sAgony(Rnd(1,3)),GetVoice(cyc),RoomVolume#(RealX#(cyc),RealZ#(cyc),1))
  If limb=1 Then pHealth(cyc)=pHealth(cyc)-(pHealth(cyc)/4) Else pHealth(cyc)=pHealth(cyc)/2
  pHP(cyc)=pHP(cyc)/2
  pDT(cyc)=pDT(cyc)+(pDT(cyc)/2)
  pHeat(cyc)=pHeat(cyc)/2
  entScore=entScore+500
  pInjured(cyc,limb)=Rnd(1,100)
  pInjured(cyc,limb)=pInjured(cyc,limb)*100
  If limb=5 And pScar(cyc,1)<3 Then pScar(cyc,1)=3
  pReaction(cyc)=110+limb
 EndIf
End Function
 
;FIND DEBILITATING INJURY
Function FindInjury(cyc) ;0=none, 1=minor, 2=debilitating
 value=0
 If pSpecial(cyc)=0
  If pInjured(cyc,1)>0 Then value=1
  For count=0 To 5
   If pInjured(cyc,count)>0 And count<>1 Then value=2
  Next
 EndIf
 Return value
End Function

;MANAGE ENTRANCE
Function ManageEntrance(cyc)
 If matchEnter=cyc And pRole(cyc)=0 And matchState=>1 And matchTim>50
  ;activate player (and team-mates)
  If pOutTim(cyc)=0
   pOutTim(cyc)=1 : pHidden(cyc)=0
   pAgenda(cyc)=0 : pOldAgenda(cyc)=-1
   pFoc(cyc)=0
   If matchState=1 
    camFoc=cyc : camType=11
    If pControl(cyc)>0
     If optEntranceCam=1 Then camType=12 : camA#=CleanAngle#(pA#(camFoc))
     If optEntranceCam=2 Then camType=2 : camOption=1 : camA#=CleanAngle#(pA#(camFoc)+180)
     If optEntranceCam=3 Then camType=11
     If optEntranceCam=4 Then camType=8
    EndIf
    If fed<>7 Or arenaPreset=>11
     arenaLight=charLight(pChar(cyc))
     PointEntity light(1),p(cyc)
    EndIf
   EndIf
   If fed<>7 Or arenaPreset=>11
    If game=0 Or fed=0 Or fedProduction(charFed(gamChar),6)>0 Then PlayTheme(pChar(cyc))
   EndIf
   For v=1 To no_plays
    If cyc<>v And pTeam(v)=pTeam(cyc) And pRole(v)<>1 And pRole(v)<>3 And pOutTim(v)=0
     pOutTim(v)=1 : pFoc(v)=0
     pAgenda(v)=0 : pOldAgenda(v)=-1
    EndIf
   Next
  EndIf
  ;fade out theme & lighting
  If pOutTim(cyc)=>125 And ChannelPlaying(chTheme) And matchState<>4
   chThemeVol#=chThemeVol#-0.005
   If chThemeVol#<0 Then chThemeVol#=0
   ChannelVolume chTheme,chThemeVol#
   If chThemeVol#=<0 Or pOutTim(cyc)>250 Then StopChannel chTheme
   If chThemeVol#=<0.3 Then arenaLight=1
  EndIf
  If pOutTim(cyc)=>125 And comTim>25 Then comTim=25 : comSpeed=-1 
  If pOutTim(cyc)>150 Then arenaLight=1
  ;satisfy and proceed
  If pOutTim(cyc)>250 And matchState=1
   Repeat  
    If matchTeams=-1 Then matchEnter=matchEnter+1 Else matchEnter=matchEnter-1
    If matchEnter<0 Then matchEnter=0
    If matchEnter>no_wrestlers Then matchEnter=0
   Until pOutTim(matchEnter)=0 Or matchEnter=0
   If matchTeams>0 And matchEnter>0 And matchEnter<>teamLegal(pTeam(matchEnter)) Then matchEnter=teamLegal(pTeam(matchEnter)) ;force team captains
   If matchTeams=-1 And matchEnter>2 Then matchEnter=0 ;stop at first 2 in rumbles
   If matchEnter>0
    ambTR#=50 : ambTG#=50 : ambTB#=50
    lightTR#=10 : lightTG#=10 : lightTB#=10
    camFoc=matchEnter
   Else
    GetCamera(optDefaultCam)
    camFoc=0
   EndIf
   matchTim=0
  EndIf
 EndIf
 ;appear through curtain
 If (pZ#(cyc)>-385 And pZ#(cyc)<385) Or (matchState=>3 And camZ#>400 And pZ#(cyc)>400) Or (matchState=>3 And camZ#<-400 And pZ#(cyc)<-400)
  If pOutTim(cyc)=1
   If pRole(cyc)=0
    Pop(cyc,2,1)
    If pAnim(cyc)<40 And matchTeams=<0
     anim=Rnd(190,192)
     If pHolding(cyc)>0 And weapName$(weapType(pHolding(cyc)))="Trophy" Then anim=198
     If pHolding(cyc)>0 And weapWear(pHolding(cyc))>0 Then anim=215+weapWear(pHolding(cyc))
     ChangeAnim(cyc,anim) 
    EndIf
   EndIf
   If pRole(cyc)=1 Then Pop(0,7,1) 
   If pRole(cyc)=3 Then Pop(cyc,2,1) 
   pOutTim(cyc)=2 
   If matchState=3 
    camTempTim=100 : camTempFoc=cyc : camOldFoc=camFoc
    entScore=entScore+(charPopularity(pChar(cyc))*2)
    If pRole(cyc)=0 Then entScore=entScore+charPopularity(pChar(cyc))
   EndIf
   If cyc=matchEnter Or matchState=3
    If pRole(cyc)=0 And matchTeams>0
     If no_wrestlers=3 And cyc=1
      PostMessage(charName$(pChar(cyc)))
     Else
      PostMessage(charTeamName$(pChar(cyc)))
     EndIf
     If TitleHolder(pChar(cyc),3) Then comSuffix$="Tag Champions"
    Else
     PostMessage(charName$(pChar(cyc)))
     If pChar(cyc)=fedCupHolder(charFed(pChar(cyc))) Then comSuffix$="Cup Holder"
     If TitleHolder(pChar(cyc),3) Then comSuffix$="Tag Champion"
     If TitleHolder(pChar(cyc),2) Then comSuffix$="Inter Champion"
     If TitleHolder(pChar(cyc),1) Then comSuffix$="World Champion"
    EndIf
    If charFed(pChar(cyc))<>fed Then comSuffix$=fedName$(charFed(pChar(cyc)))
    If pManager(cyc)>0 Then comAffix$="(w/ "+charName$(pChar(pManager(cyc)))+")"
   EndIf
  EndIf
 EndIf
 ;clock out time
 If pOutTim(cyc)=>2 And pOutTim(cyc)<1000
  clocker=0 : range#=0
  If (matchState=3 And matchCountOuts=3) Or matchCage>0 Then range#=-15
  If InsideRing(RealX#(cyc),RealZ#(cyc),range#) Then clocker=1
  If matchTeams>0
   For v=1 To no_wrestlers
    If pTeam(v)=pTeam(cyc) And pControl(v)>0 And InsideRing(RealX#(v),RealZ#(v),-15)=0 Then clocker=0
   Next
  EndIf
  If pOutTim(cyc)>100 Or (pRole(cyc)=3 And pOutTim(cyc)>2) Then clocker=1
  If matchLocation>0 Then clocker=1
  If clocker=1 Then pOutTim(cyc)=pOutTim(cyc)+1
  If matchState=1 And matchCage>0 And pPlatform(cyc)=>91 And pPlatform(cyc)=<94 Then pOutTim(cyc)=pOutTim(cyc)+1 ;cage booster
  If matchState=3 And pRole(cyc)=0 And pEliminated(cyc)=0 And InsideRing(pX#(cyc),pZ#(cyc),0) Then pOutTim(cyc)=pOutTim(cyc)+1 ;rumble booster 
 EndIf
 ;trigger interferences
 chance=5000*optLength
 If optIntruders>2 Then chance=chance*2 
 randy=Rnd(0,chance)
 If randy=<1 And matchState=>3 And matchMins>optLength And pRole(cyc)=3 And pOutTim(cyc)=0
  needed=0
  For v=1 To no_plays
   If LegalMan(v) And (pHealth(v)<pHealthLimit(v)/2 Or FindInjury(v)>0) Then needed=v
  Next
  If needed>0	
   teamer=Rnd(-2,2)
   If teamer<0 Then pTeam(cyc)=pTeam(needed) Else pTeam(cyc)=teamer
   For v=1 To no_wrestlers
	If teamer>0 And charHeel(pChar(v))=charHeel(pChar(cyc)) Then pTeam(cyc)=pTeam(v) ;support like allegiance
	If teamer>0 And charHeel(pChar(v))<>charHeel(pChar(cyc)) Then OpposeTeam(cyc,v) ;desert enemy allegiance
	If fed=0
	 If charFed(pChar(v))=charFed(pChar(cyc)) Then pTeam(cyc)=pTeam(v) ;support same fed
	 If charFed(pChar(v))<>charFed(pChar(cyc)) Then OpposeTeam(cyc,v) ;desert enemy fed 
	EndIf
	If fed>0 And charFed(pChar(cyc))<>fed Then pTeam(cyc)=0 ;neutral if foreign
	If charRelationship(pChar(cyc),pChar(v))>0 Then pTeam(cyc)=pTeam(v) ;support friends
	If charRelationship(pChar(cyc),pChar(v))<0 Then OpposeTeam(cyc,v) ;desert enemies  
	If charRelationship(pChar(cyc),pChar(v))<0 Then pAngerTim(cyc,v)=1000  
   Next 
   If pCostume(cyc)=3 Then pRole(cyc)=1 : pTeam(cyc)=0 ;referee addition
   pOutTim(cyc)=1 : pHeat(cyc)=95
   pAgenda(cyc)=1 : GetNewFoc(cyc)
   ;risk betrayal! 
   randy=Rnd(0,10)
   If randy=<1 And game=1 And matchState=3 And matchPlayer>0 And pRole(matchPlayer)=0 And pRole(cyc)<>1 And charRelationship(pChar(cyc),gamChar)>0
    OpposeTeam(cyc,matchPlayer) 
    pAgenda(cyc)=1 : pFoc(cyc)=matchPlayer
    pAngerTim(cyc,matchPlayer)=1000
   EndIf
  EndIf
 EndIf 
 ;risk managerial betrayal
 randy=Rnd(0,100000*optLength)
 If randy=<1 And game=1 And matchState=3 And pRole(cyc)=2 And matchPlayer=pClient(cyc) And pTeam(cyc)=pTeam(matchPlayer)
  OpposeTeam(cyc,matchPlayer)
  pAgenda(cyc)=1 : pFoc(cyc)=matchPlayer
  pAngerTim(cyc,matchPlayer)=1000 
  pChaosTim(cyc)=500
 EndIf
 ;exhaust interferences
 If pRole(cyc)=3 And pOutTim(cyc)>750 Then pRole(cyc)=2
 ;remove eliminated
 If matchState=3 And pEliminated(cyc) And InsideRing(pX#(cyc),pZ#(cyc),0)=0
  pOutTim(cyc)=pOutTim(cyc)+1
  If pOutTim(cyc)>1500 And pHidden(cyc)=0 And cyc<>camFoc And pControl(cyc)=0 And pGrappling(cyc)=0 And pGrappler(cyc)=0 And pPinning(cyc)=0 And pPinner(cyc)=0 And pPlatform(cyc)=0
   If (optHideElim=1 And matchRemaining>4) Or (optHideElim=2 And matchRemaining>2)
    DropWeapon(cyc,0)
    DropItem(cyc)
    pX#(cyc)=9999 : pY#(cyc)=9999 : pZ#(cyc)=9999
    pOldX#(cyc)=pX#(cyc) : pOldZ#(cyc)=pZ#(cyc)
    pAnim(cyc)=-1 : pHidden(cyc)=1
   EndIf
  EndIf
 EndIf 
End Function

;AUTOMATED REFEREEING
Function AutomatedRefs(cyc)
 ;automated pinning
 pAutoTim(cyc)=pAutoTim(cyc)+1 
 v=pPinning(cyc)
 If no_refs=0 And v>0 And RefViable(v)=1 And pChecked(v)=0
  oldCount=pPinCount(v) : pRefVictim(cyc)=v
  If pAnim(cyc)<>150 And pAnim(cyc)<>152 And pAnim(cyc)<>154 And pAnim(cyc)<>160 And pAnim(cyc)<>161
   If pGrappling(cyc)=0 Then pAutoTim(cyc)=pAutoTim(cyc)-2
  EndIf
  If pAutoTim(cyc)>50 And pPinCount(v)=0 Then pPinCount(v)=1 : pAutoTim(cyc)=0
  If pAutoTim(cyc)>75 And pPinCount(v)>0 Then pPinCount(v)=pPinCount(v)+1 : pAutoTim(cyc)=0 
  If pPinCount(v)<>oldCount
   ProduceSound(cam,sStep(Rnd(3,4)),22050,0.5)
   ProduceSound(cam,sBlock(Rnd(1,4)),22050,0.25)
   ProduceSound(cam,sCount(pPinCount(v)),0,1)
   entScore=entScore+50
  EndIf
  ;rope break
  If pAutoTim(cyc)>25 And matchRules=>1 And LegalMan(cyc) And LegalMan(v) 
   If TouchingRopes(cyc) Or TouchingRopes(v) 
    Pop(v,Rnd(2,9),0) : Pop(0,Rnd(2,9),0)
    ProduceSound(cam,sRopeBreak,0,1)
    entScore=entScore+(charPopularity(pChar(v))*2)
    pChecked(v)=1
   EndIf 
  EndIf
 EndIf
 ;delayed pin declaration
 v=pRefVictim(cyc)
 If no_refs=0 And RefViable(v)=11
  If pAutoTim(cyc)>40
   matchWinStyle=1
   DeclareFall(cyc,pRefVictim(cyc))
   pChecked(pRefVictim(cyc))=1
  EndIf
 EndIf
 ;pinning chaos
 v=pPinning(cyc)
 If v>0 And LegalMan(cyc) And LegalMan(v) And pPinCount(v)>0 Then CauseChaos(100)
 ;submission declaration
 If no_refs=0 And RefViable(cyc)=12 And pChecked(cyc)=0 And pSubmitDelay(cyc)=0
  pRefAward(cyc)=pGrappler(cyc)
  pSubmitDelay(cyc)=1 : pChecked(cyc)=1
 EndIf
 If pSubmitDelay(cyc)=>1 Then pSubmitDelay(cyc)=pSubmitDelay(cyc)+1
 If pSubmitDelay(cyc)>20
  matchWinStyle=2 : pSubmitDelay(cyc)=0
  DeclareFall(pRefAward(cyc),cyc)
 EndIf
 ;KO declaration
 If no_refs=0 And RefViable(cyc)=13 Then matchWinStyle=3 : DeclareFall(0,cyc)
 ;blood declaration
 If no_refs=0 And RefViable(cyc)=14 Then matchWinStyle=4 : DeclareFall(0,cyc)
 ;count-out's
 If no_refs=0 And RefViable(cyc)=5
  interrupted=0
  For v=1 To no_plays
   If pFoc(v)=cyc And pOutCount(v)<pOutCount(cyc)-1 And pTeam(v)<>pTeam(cyc) And InProximity(v,cyc,30) And AttackViable(v)=1 Then interrupted=1
  Next
  If interrupted=0
   If pAutoTim(cyc)>150*matchCountOuts Or (pOutCount(cyc)=0 And pAutoTim(cyc)>100)
    pOutCount(cyc)=pOutCount(cyc)+1
    announce=1
    For v=1 To no_plays
     If cyc<>v And (RefViable(v)=5 Or RefViable(v)=15) And pOutCount(v)=>pOutCount(cyc) Then announce=0
    Next
    If announce=1
     Pop(0,Rnd(2,9),Float(pOutCount(cyc))/10)
     ProduceSound(cam,sCount(pOutCount(cyc)),0,1)
     entScore=entScore+(10*pOutCount(cyc))
    EndIf
    pAutoTim(cyc)=0
   EndIf
  EndIf
 EndIf
 If no_refs=0 And matchCountOuts=<2 And RefViable(cyc)=15 
  matchWinStyle=6
  For v=1 To no_wrestlers
   If pOutCount(v)=>8 Then DeclareFall(0,v)
  Next
 EndIf
 ;over-the-top elimination / cage escape
 If matchCountOuts=3 And RefViable(cyc)=15
  If matchCage=0 Then matchWinStyle=5 : DeclareFall(0,cyc)
  If matchCage>0 Then matchWinStyle=5 : EndMatch(cyc)
 EndIf
 ;delayed referee DQ
 If pDQDelay(cyc)=>1 Then pDQDelay(cyc)=pDQDelay(cyc)+1
 If pRole(cyc)=1 And pDQDelay(cyc)>0 And pRefVictim(cyc)>0 And pAnim(cyc)<40
  matchWinStyle=6
  ProduceSound(cam,sDQ,0,1)
  ChangeAnim(cyc,172)
  pDQDelay(cyc)=0
 EndIf
 ;delayed automated DQ
 If no_refs=0 And pDQDelay(cyc)=5 Then ProduceSound(cam,sDQ,0,1)
 If no_refs=0 And pDQDelay(cyc)>20
  matchWinStyle=6
  DeclareFall(pRefAward(cyc),cyc)
  pDQDelay(cyc)=0 
 EndIf
End Function

;RISK DISQUALIFICATION!
Function RiskDQ(cyc,v)
 If matchState=3 And (matchSecs>1 Or matchMins>0) And matchRules=2 
  If LegalMan(cyc) And (LegalMan(v) Or pRole(v)=1) And InsideRing(pX#(cyc),pZ#(cyc),-5) And InsideRing(pX#(v),pZ#(v),-5)
   ;find a witness
   witness=FindReferee(cyc,v)
   If pRole(v)=1 And witness=0 Then witness=v
   ;queue human declaration
   If witness>0 And no_refs>0 And pDQDelay(witness)=0
    pRefAward(witness)=v
    pRefVictim(witness)=cyc
    pDQDelay(witness)=1
   EndIf
   ;queue automated declaration
   If witness>0 And no_refs=0 And pDQDelay(cyc)=0
    pRefAward(cyc)=v
    pDQDelay(cyc)=1
   EndIf 
   ;thrill of cheating!
   crowdVolTarget#=1.0
   Pop(cyc,Rnd(2,8),0) : Pop(0,3,0)
   entScore=entScore+250
  EndIf
 EndIf
End Function

;CAUSE CHAOS (to all players)
Function CauseChaos(chaos)
 For cyc=1 To no_plays
  If pChaosTim(cyc)<chaos Then pChaosTim(cyc)=chaos
 Next
End Function

;FIND CHAOS (involving all players)
Function FindChaos()
 rioters=0
 For cyc=1 To no_plays
  If pChaosTim(cyc)>0 Then rioters=rioters+1
 Next
 If rioters=no_plays Then chaos=1 Else chaos=0
 Return chaos
End Function

;OPPOSE PLAYER'S TEAM
Function OpposeTeam(cyc,v)
 If pTeam(cyc)=pTeam(v) Then pTeam(cyc)=pTeam(cyc)+1 
 If v=matchPlayer And pTeam(v)=1 Then pTeam(cyc)=2 
 If v=matchPlayer And pTeam(v)=2 Then pTeam(cyc)=1 
End Function