;//////////////////////////////////////////////////////////////////////////////
;---------------------- WRESTLING MPIRE 2008: GROUND MOVES --------------------
;//////////////////////////////////////////////////////////////////////////////

;---------------------------------------------------------------------
;///////////////////// HEAD MOVE SEQUENCES ///////////////////////////
;---------------------------------------------------------------------
Function LoadHeadSequences(cyc)
 ;head grappling
 pSeq(cyc,500)=ExtractAnimSeq(p(cyc),2075,2135,pSeq(cyc,1007)) ;ground lunge
 pSeq(cyc,501)=ExtractAnimSeq(p(cyc),80,120,pSeq(cyc,1040)) ;grasp head (on back)
 pSeq(cyc,502)=ExtractAnimSeq(p(cyc),80,120,pSeq(cyc,1041)) ;head grasped (on back)
 pSeq(cyc,503)=ExtractAnimSeq(p(cyc),305,350,pSeq(cyc,1040)) ;grasp head (from front)
 pSeq(cyc,504)=ExtractAnimSeq(p(cyc),305,350,pSeq(cyc,1041)) ;head grasped (from front) 
 pSeq(cyc,505)=ExtractAnimSeq(p(cyc),130,170,pSeq(cyc,1040)) ;holding head
 pSeq(cyc,506)=ExtractAnimSeq(p(cyc),130,170,pSeq(cyc,1041)) ;head being held
 pSeq(cyc,507)=ExtractAnimSeq(p(cyc),180,240,pSeq(cyc,1040)) ;dragging by head
 pSeq(cyc,508)=ExtractAnimSeq(p(cyc),180,240,pSeq(cyc,1041)) ;dragged by head
 pSeq(cyc,509)=ExtractAnimSeq(p(cyc),250,295,pSeq(cyc,1040)) ;release head
 pSeq(cyc,510)=ExtractAnimSeq(p(cyc),250,295,pSeq(cyc,1041)) ;head released
 ;drag up by head
 pSeq(cyc,511)=ExtractAnimSeq(p(cyc),360,445,pSeq(cyc,1040)) ;[execute]
 pSeq(cyc,512)=ExtractAnimSeq(p(cyc),360,445,pSeq(cyc,1041)) ;[receive]
 ;camel clutch
 If GroundMoveRequired(cyc,511,1) Then pSeq(cyc,513)=ExtractAnimSeq(p(cyc),455,520,pSeq(cyc,1040)) ;apply execute
 If GroundMoveRequired(cyc,511,-1) Then pSeq(cyc,514)=ExtractAnimSeq(p(cyc),455,520,pSeq(cyc,1041)) ;apply receive
 If GroundMoveRequired(cyc,511,1) Then pSeq(cyc,515)=ExtractAnimSeq(p(cyc),520,565,pSeq(cyc,1040)) ;wrench execute
 If GroundMoveRequired(cyc,511,-1) Then pSeq(cyc,516)=ExtractAnimSeq(p(cyc),520,565,pSeq(cyc,1041)) ;wrench receive
 ;mounted punches
 If GroundMoveRequired(cyc,512,1) Then pSeq(cyc,517)=ExtractAnimSeq(p(cyc),575,950,pSeq(cyc,1040)) ;[execute]
 If GroundMoveRequired(cyc,512,-1) Then pSeq(cyc,518)=ExtractAnimSeq(p(cyc),575,950,pSeq(cyc,1041)) ;[receive]
 ;sleeper hold
 If GroundMoveRequired(cyc,513,1) Then pSeq(cyc,519)=ExtractAnimSeq(p(cyc),960,990,pSeq(cyc,1040)) ;apply execute
 If GroundMoveRequired(cyc,513,-1) Then pSeq(cyc,520)=ExtractAnimSeq(p(cyc),960,990,pSeq(cyc,1041)) ;apply receive
 If GroundMoveRequired(cyc,513,1) Then pSeq(cyc,521)=ExtractAnimSeq(p(cyc),990,1030,pSeq(cyc,1040)) ;wrench execute
 If GroundMoveRequired(cyc,513,-1) Then pSeq(cyc,522)=ExtractAnimSeq(p(cyc),990,1030,pSeq(cyc,1041)) ;wrench receive
 ;dragon sleeper
 If GroundMoveRequired(cyc,514,1) Then pSeq(cyc,523)=ExtractAnimSeq(p(cyc),1040,1070,pSeq(cyc,1040)) ;apply execute
 If GroundMoveRequired(cyc,514,-1) Then pSeq(cyc,524)=ExtractAnimSeq(p(cyc),1040,1070,pSeq(cyc,1041)) ;apply receive
 If GroundMoveRequired(cyc,514,1) Then pSeq(cyc,525)=ExtractAnimSeq(p(cyc),1070,1110,pSeq(cyc,1040)) ;wrench execute
 If GroundMoveRequired(cyc,514,-1) Then pSeq(cyc,526)=ExtractAnimSeq(p(cyc),1070,1110,pSeq(cyc,1041)) ;wrench receive
 ;head smashes
 If GroundMoveRequired(cyc,515,1) Then pSeq(cyc,527)=ExtractAnimSeq(p(cyc),1120,1335,pSeq(cyc,1040)) ;[execute]
 If GroundMoveRequired(cyc,515,-1) Then pSeq(cyc,528)=ExtractAnimSeq(p(cyc),1120,1335,pSeq(cyc,1041)) ;[receive]
 ;choke hold
 If GroundMoveRequired(cyc,516,1) Then pSeq(cyc,529)=ExtractAnimSeq(p(cyc),1345,1380,pSeq(cyc,1040)) ;apply execute
 If GroundMoveRequired(cyc,516,-1) Then pSeq(cyc,530)=ExtractAnimSeq(p(cyc),1345,1380,pSeq(cyc,1041)) ;apply receive
 If GroundMoveRequired(cyc,516,1) Then pSeq(cyc,531)=ExtractAnimSeq(p(cyc),1380,1419,pSeq(cyc,1040)) ;wrench execute
 If GroundMoveRequired(cyc,516,-1) Then pSeq(cyc,532)=ExtractAnimSeq(p(cyc),1380,1419,pSeq(cyc,1041)) ;wrench receive
 ;crossface clutch
 If GroundMoveRequired(cyc,517,1) Then pSeq(cyc,533)=ExtractAnimSeq(p(cyc),1430,1495,pSeq(cyc,1040)) ;apply execute
 If GroundMoveRequired(cyc,517,-1) Then pSeq(cyc,534)=ExtractAnimSeq(p(cyc),1430,1495,pSeq(cyc,1041)) ;apply receive
 If GroundMoveRequired(cyc,517,1) Then pSeq(cyc,535)=ExtractAnimSeq(p(cyc),1495,1535,pSeq(cyc,1040)) ;wrench execute
 If GroundMoveRequired(cyc,517,-1) Then pSeq(cyc,536)=ExtractAnimSeq(p(cyc),1495,1535,pSeq(cyc,1041)) ;wrench receive
 ;bow & arrow
 If GroundMoveRequired(cyc,518,1) Then pSeq(cyc,537)=ExtractAnimSeq(p(cyc),1545,1575,pSeq(cyc,1040)) ;apply execute
 If GroundMoveRequired(cyc,518,-1) Then pSeq(cyc,538)=ExtractAnimSeq(p(cyc),1545,1575,pSeq(cyc,1041)) ;apply receive
 If GroundMoveRequired(cyc,518,1) Then pSeq(cyc,539)=ExtractAnimSeq(p(cyc),1575,1615,pSeq(cyc,1040)) ;wrench execute
 If GroundMoveRequired(cyc,518,-1) Then pSeq(cyc,540)=ExtractAnimSeq(p(cyc),1575,1615,pSeq(cyc,1041)) ;wrench receive
 ;arm bar
 If GroundMoveRequired(cyc,519,1) Then pSeq(cyc,541)=ExtractAnimSeq(p(cyc),1625,1700,pSeq(cyc,1040)) ;apply execute
 If GroundMoveRequired(cyc,519,-1) Then pSeq(cyc,542)=ExtractAnimSeq(p(cyc),1625,1700,pSeq(cyc,1041)) ;apply receive
 If GroundMoveRequired(cyc,519,1) Then pSeq(cyc,543)=ExtractAnimSeq(p(cyc),1700,1740,pSeq(cyc,1040)) ;wrench execute
 If GroundMoveRequired(cyc,519,-1) Then pSeq(cyc,544)=ExtractAnimSeq(p(cyc),1700,1740,pSeq(cyc,1041)) ;wrench receive
End Function

;FIND GROUND MOVE REQUIREMENT
Function GroundMoveRequired(cyc,move,task) ;-1=receive, 1=execute
 needed=0
 For v=1 To no_plays
  For count=1 To 3
   If moveAnim(3,charGroundMove(pChar(v),count))=move Or moveAnim(4,charGroundMove(pChar(v),count+3))=move
    If task=1 And cyc=v Then needed=1 ;executor
    If task=-1 And cyc<>v Then needed=1 ;receiver
   EndIf
  Next
 Next
 Return needed
End Function

;---------------------------------------------------------------------
;///////////////////// HEAD MOVE ANIMATIONS //////////////////////////
;---------------------------------------------------------------------
Function HeadMoves(cyc)
 ;GROUND LUNGE
 If pAnim(cyc)=500
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=1.25+(Float(pSkill(cyc))/90)
   If pAnimSpeed#(cyc)<1.8 Then pAnimSpeed#(cyc)=1.8
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,500),15/pAnimSpeed#(cyc)
   factor#=GetPercent#(pAnimSpeed#(cyc),2.0)
   pTravel#(cyc)=Float(pAgility(cyc))/180
   pTravel#(cyc)=PercentOf#(pTravel#(cyc),factor#)
   pSting(cyc)=1
  EndIf
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,Rnd(0.25,0.5))
  pTravel#(cyc)=pTravel#(cyc)-(pTravel#(cyc)/Int(50/pAnimSpeed#(cyc)))
  If InProximity(cyc,pFoc(cyc),15)=0 Or pFoc(cyc)=0 Then Advance(cyc,pA#(cyc),pTravel#(cyc))
  If pAnimTim(cyc)<Int(50/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=pStepTim#(cyc)+1
  intact=1
  If pScar(cyc,7)=5 And pScar(cyc,20)=5 Then intact=0 
  If intact=1 And pAnimTim(cyc)=>Int(33/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(40/pAnimSpeed#(cyc)) And pPlatform(cyc)=0
   For v=1 To no_plays
    range#=17+(charHeight(pChar(cyc))/3)
    contact=0
    If AttackViable(v)=3 And pAnimTim(cyc)=<Int(35/pAnimSpeed#(cyc)) Then contact=2
    If AttackViable(v)=4 And pAnimTim(cyc)=>Int(35/pAnimSpeed#(cyc)) Then contact=1
    sensible=1
    If pFoc(cyc)>0 And v<>pFoc(cyc) And InProximity(cyc,pFoc(cyc),range#)
     If InLine(cyc,pLimb(pFoc(cyc),36),45) Then sensible=0
    EndIf
    If contact>0 And sensible=1 And cyc<>v And pY#(cyc)=>pY#(v)-5 And pY#(cyc)=<pY#(v)+5 And pPlatform(v)=0 And GettingUp(v)=0 And pGrappling(cyc)=0 And pGrappler(v)=0 And pGrappling(v)=0 And pSting(cyc)=1
     If InLine(cyc,pLimb(v,36),45)
      ScanBody(v)
      For scan=0 To 6
       If ReachedCord(pX#(cyc),pZ#(cyc),pScanX#(v,scan),pScanZ#(v,scan),range#) And pSting(cyc)=1
        GroundImpactChecks(v)
        ProduceSound(p(v),sBlock(Rnd(1,4)),22050,0.25)
        ProduceSound(p(v),sShuffle(Rnd(1,3)),0,0)
        ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),0.5)
        pGrappling(cyc)=v : pGrappler(v)=cyc : pGrappleAssist(cyc)=0
        pFoc(cyc)=v : pFoc(v)=cyc
        ChangeAnim(cyc,501) : GroundReaction(v) : pHurtTim(v)=5   
        ClockAbuse(cyc,v,1)
        pSting(cyc)=0
       EndIf
      Next
     EndIf
    EndIf
   Next
  EndIf
  If pAnimTim(cyc)=>Int(70/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,0)
  pHealth(cyc)=pHealth(cyc)-1
 EndIf
 ;SHUFFLE TO NEAREST LIMB
 If pAnim(cyc)=501
  v=pGrappling(cyc) : pExcusedPlays(cyc)=1
  pAnimSpeed#(cyc)=pSpeed#(cyc)*2
  If pAnimTim(cyc)=0 Then Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,49),10 : pStepTim#(cyc)=-15
  GroundPositioning(cyc,v)
  pStepTim#(cyc)=pStepTim#(cyc)+pAnimSpeed#(cyc)
  If ReachedCord(pX#(cyc),pZ#(cyc),pTX#(cyc),pTZ#(cyc),2) Or pAnimTim(cyc)>100
   If pAnim(v)=150 Or pAnim(v)=152 Or pAnim(v)=99 Or pAnim(v)=109
    If pAnim(v)=152 Or pAnim(v)=109
     If ClosestLimb(cyc,v)=1 Then ChangeAnim(cyc,504)
     If ClosestLimb(cyc,v)=2 Then ChangeAnim(cyc,604)
    Else
     If ClosestLimb(cyc,v)=1 Then ChangeAnim(cyc,503)
     If ClosestLimb(cyc,v)=2 Then ChangeAnim(cyc,603)
    EndIf 
    ChangeAnim(v,502)
    pX#(cyc)=pX#(v) : pZ#(cyc)=pZ#(v) : pA#(cyc)=pA#(v)
    pOldX#(cyc)=pX#(cyc) : pOldZ#(cyc)=pZ#(cyc)
    pExcusedPlays(cyc)=1 : pExcusedPlays(v)=1
    If EntertainViable(cyc,v) Then entScore=entScore+100
   EndIf
  EndIf 
 EndIf
 ;GROUND GRAPPLE VICTIM
 If pAnim(cyc)=502
  If pAnim(pGrappler(cyc))<300 Then pGrappling(pGrappler(cyc))=0 : pGrappler(cyc)=0 
  If pGrappler(cyc)=0 Then ChangeAnim(cyc,150)
  DropWeapon(cyc,100)
 EndIf
 ;PICK-UP HEAD (off back)
 If pAnim(cyc)=503
  v=pGrappling(cyc)
  If pAnimTim(cyc)=<1 Then pExcusedWorld(cyc)=1 Else pExcusedWorld(cyc)=0
  If pAnimTim(cyc)=0 
   pAnimSpeed#(cyc)=2.0
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,501),0
   Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,502),0
  EndIf
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) Then GrappleOffset(cyc,v,1)
  If pAnimTim(cyc)=>Int(35/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,505)
  FixGroundGrapple(cyc)
  DropWeapon(cyc,1000)
  DropWeapon(v,1000)
 EndIf
 ;PICK-UP HEAD (off front)
 If pAnim(cyc)=504
  v=pGrappling(cyc)
  If pAnimTim(cyc)=<1 Then pExcusedWorld(cyc)=1 Else pExcusedWorld(cyc)=0
  If pAnimTim(cyc)=0 
   pAnimSpeed#(cyc)=2.0
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,503),0
   Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,504),0
  EndIf
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=>Int(25/pAnimSpeed#(cyc)) Then GrappleOffset(cyc,v,1)
  If pAnimTim(cyc)=>Int(45/pAnimSpeed#(cyc)) 
   ChangeAnim(cyc,505) : SharpTransition(cyc,505,1,-1)
   SharpTransition(v,506,1,-1)
  EndIf
  FixGroundGrapple(cyc)
  DropWeapon(cyc,1000)
  DropWeapon(v,1000)
 EndIf
 ;HOLD HEAD
 If pAnim(cyc)=505
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   pAnimSpeed#(cyc)=0.5
   If pOldAnim(cyc)=504 Then transition=0 Else transition=10
   Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,505),transition
   Animate p(v),1,pAnimSpeed#(cyc),pSeq(v,506),transition
  EndIf
  FixGroundGrapple(cyc)
  GrappleOffset(cyc,v,1)
  If DirPressed(cyc) Then ChangeAnim(cyc,506)
  FindGroundMoves(cyc,1)
 EndIf
 ;DRAG BY HEAD
 If pAnim(cyc)=506
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   pAnimSpeed#(cyc)=1.5
   Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,507),0;5
   Animate p(v),1,pAnimSpeed#(cyc),pSeq(v,508),0;5
   pStepTim#(cyc)=-15 : pStepTim#(v)=-15
  EndIf
  ;movement
  If DirPressed(cyc)
   pTA#(cyc)=CleanAngle#(RequestAngle#(cyc)+180)
   Advance(v,RequestAngle#(cyc),pAnimSpeed#(cyc)/6) 
   turner#=0.5
   pA#(cyc)=pA#(cyc)+ReachAngle#(pA#(cyc),pTA#(cyc),turner#)
   If SatisfiedAngle(pA#(cyc),pTA#(cyc),turner#*2) Then pA#(cyc)=pTA#(cyc)
   pStepTim#(cyc)=pStepTim#(cyc)+pAnimSpeed#(cyc)
   pStepTim#(v)=pStepTim#(v)+(pAnimSpeed#(cyc)/2)
  EndIf
  ;bind to victim
  EnforceBlocks(v)
  FixGroundGrapple(cyc)
  GrappleOffset(cyc,v,1)
  If pAnimTim(cyc)>5 And DirPressed(cyc)=0 Then ChangeAnim(cyc,505)
  FindGroundMoves(cyc,1) 
 EndIf
 ;RELEASE HEAD
 If pAnim(cyc)=507
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=1.5
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,509),0
   Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,510),0
  EndIf
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0) : ProduceSound(p(v),sSwing,22050,0.25)
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(v),sThud,22050,0.25) : ProduceSound(p(v),sFall,22050,0)
  If pAnimTim(cyc)=>Int(45/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,-1)
   ChangeAnim(v,150) : EndMove(cyc,v)
  EndIf
  DropWeapon(cyc,1000)
  DropWeapon(v,1000)
 EndIf
 ;GROUND MOVE VICTIM
 If pAnim(cyc)=509
  DropWeapon(cyc,100)
 EndIf
 ;********************** HEAD MOVES *****************************
 ;DRAG UP TO FEET
 If pAnim(cyc)=510
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=2.0
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,511),0
   Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,512),0
  EndIf
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(80/pAnimSpeed#(cyc)) Then pStepTim#(v)=99
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,22050,0.25)
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,1,2)
  If pAnimTim(cyc)=>Int(45/pAnimSpeed#(cyc)) Then GrappleOffset(cyc,v,0)
  If pAnimTim(cyc)>Int(85/pAnimSpeed#(cyc))
   ChangeAnim(cyc,301) : SharpTransition(cyc,305,1,0)
   ChangeAnim(v,302) : SharpTransition(v,306,1,180)
   pExcusedPlays(cyc)=0 : pExcusedPlays(v)=0
  EndIf
 EndIf
 ;CAMEL CLUTCH
 If pAnim(cyc)=511
  ;apply
  v=pGrappling(cyc)
  animA=513 : animB=514
  If pAnimStage(cyc)=1
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.0
    Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0
    Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
   EndIf 
   If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(55/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(65/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.2)
   If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sFall,22050,0.5)
   If pAnimTim(cyc)>Int(60/pAnimSpeed#(cyc)) Then pAnimStage(cyc)=2 : pAnimTim(cyc)=0
  EndIf
  ;wrench
  If pAnimStage(cyc)=2
   If pAnimTim(cyc)=0
    Pop(cyc,Rnd(2,9),0)
    ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),0)
    ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
    If pSpecial(cyc)>0 Then pAnimSpeed#(cyc)=1.0 Else pAnimSpeed#(cyc)=Rnd(0.5,0.75)
    Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,animA+2),5
    Animate p(v),1,pAnimSpeed#(cyc),pSeq(v,animB+2),5
   EndIf
   HoldEffects(cyc,v,2,0.05,1)
  EndIf
  ;leave
  If pAnimStage(cyc)=3
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.5
    Animate p(cyc),3,-pAnimSpeed#(cyc),pSeq(cyc,animA),5
    Animate p(v),3,-pAnimSpeed#(cyc),pSeq(v,animB),5
   EndIf
   If pAnimTim(cyc)=Int(5/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.2)
   If pAnimTim(cyc)=Int(5/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(55/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)>Int(65/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,507)
  EndIf
 EndIf
 ;MOUNTED PUNCHES
 If pAnim(cyc)=512
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=2.25
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,517),0
   Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,518),0
  EndIf
  If pAnimTim(cyc)=Int(14/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(41/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(355/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
  If pAnimTim(cyc)=Int(27/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(55/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(375/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sFall,22050,0.5)
  If pAnimTim(cyc)=Int(75/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(140/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(205/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(280/pAnimSpeed#(cyc))
   ProduceSound(p(cyc),sSwing,21000,0.5)
  EndIf
  If pAnimTim(cyc)=Int(88/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(153/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(218/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(295/pAnimSpeed#(cyc))
   Pop(cyc,Rnd(2,8),0)
   If pHolding(cyc)=0 Or weapWear(pHolding(cyc))>0 Then ProduceSound(p(cyc),sImpact(Rnd(1,4)),22050,0)
   If pHolding(cyc)>0 And weapWear(pHolding(cyc))=0 Then ProduceSound(p(cyc),sImpact(Rnd(1,4)),22050,0.5)
   ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),0)
   ImpactSpurt(pLimbX#(v,1),pLimbY#(v,1)-1,pLimbZ#(v,1),1,1)
   BloodSpurt(pLimbX#(v,1),pLimbY#(v,1)-1,pLimbZ#(v,1),pA#(v)+180,pScar(v,1)-1,1)
   ScarLimb(v,1,50)
   If pHolding(cyc)>0 And weapWear(pHolding(cyc))=0 
    ScarLimb(v,1,25) : BloodSpurt(pLimbX#(v,1),pLimbY#(v,1)-1,pLimbZ#(v,1),pA#(v)+180,pScar(v,1)-1,1)
    If weapBurning(pHolding(cyc))>0 Then CreateParticle(pLimbX#(v,1),pLimbY#(v,1)-1,pLimbZ#(v,1),-1,2) : CreateParticle(pLimbX#(v,1),pLimbY#(v,1)-1,pLimbZ#(v,1),-1,13)
   EndIf
   pHealth(v)=pHealth(v)-PercentOf#(pStrength(cyc),125)
   If EntertainViable(cyc,v) Then entScore=entScore+PercentOf#(pStrength(cyc),125)
   pHeat(cyc)=pHeat(cyc)+1 : pHeat(v)=pHeat(v)-1
   WeaponImpact(cyc,v,1)
  EndIf
  If pAnimTim(cyc)>Int(375/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,90)
   ChangeAnim(v,150)
   EndMove(cyc,v)
   If matchState=3 And pEliminated(v)=0 Then pDT(v)=0
  EndIf
 EndIf 
 ;SLEEPER HOLD
 If pAnim(cyc)=513
  ;apply
  v=pGrappling(cyc)
  animA=519 : animB=520
  If pAnimStage(cyc)=1
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=1.5
    Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0
    Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
   EndIf 
   If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)>Int(25/pAnimSpeed#(cyc)) Then pAnimStage(cyc)=2 : pAnimTim(cyc)=0
  EndIf
  ;wrench
  If pAnimStage(cyc)=2
   If pAnimTim(cyc)=0
    Pop(cyc,Rnd(2,9),0)
    ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),0)
    ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
    If pSpecial(cyc)>0 Then pAnimSpeed#(cyc)=1.0 Else pAnimSpeed#(cyc)=Rnd(0.5,0.75)
    Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,animA+2),5
    Animate p(v),1,pAnimSpeed#(cyc),pSeq(v,animB+2),5
   EndIf
   HoldEffects(cyc,v,1,0.025,0)
  EndIf
  ;leave
  If pAnimStage(cyc)=3
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.0
    Animate p(cyc),3,-pAnimSpeed#(cyc),pSeq(cyc,animA),5
    Animate p(v),3,-pAnimSpeed#(cyc),pSeq(v,animB),5
   EndIf
   If pAnimTim(cyc)=Int(5/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)>Int(30/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,507)
  EndIf
 EndIf
 ;DRAGON SLEEPER
 If pAnim(cyc)=514
  ;apply
  v=pGrappling(cyc)
  animA=523 : animB=524
  If pAnimStage(cyc)=1
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=1.8
    Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0
    Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
   EndIf 
   If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)>Int(25/pAnimSpeed#(cyc)) Then pAnimStage(cyc)=2 : pAnimTim(cyc)=0
  EndIf
  ;wrench
  If pAnimStage(cyc)=2
   If pAnimTim(cyc)=0
    Pop(cyc,Rnd(2,9),0)
    ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),0)
    ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
    If pSpecial(cyc)>0 Then pAnimSpeed#(cyc)=1.0 Else pAnimSpeed#(cyc)=Rnd(0.5,0.75)
    Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,animA+2),5
    Animate p(v),1,pAnimSpeed#(cyc),pSeq(v,animB+2),5
   EndIf
   HoldEffects(cyc,v,2,0.025,0)
  EndIf
  ;leave
  If pAnimStage(cyc)=3
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.0
    Animate p(cyc),3,-pAnimSpeed#(cyc),pSeq(cyc,animA),5
    Animate p(v),3,-pAnimSpeed#(cyc),pSeq(v,animB),5
   EndIf
   If pAnimTim(cyc)=Int(5/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)>Int(30/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,507)
  EndIf
 EndIf
 ;HEAD SMASHES
 If pAnim(cyc)=515
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=2.25
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,527),0
   Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,528),0
  EndIf
  If pAnimTim(cyc)=Int(8/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(175/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
  If pAnimTim(cyc)=Int(17/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(195/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sFall,22050,0.5)
  If pAnimTim(cyc)=Int(68/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(111/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(154/pAnimSpeed#(cyc)) 
   ProduceSound(p(cyc),sSwing,21000,0.5)
  EndIf
  If pAnimTim(cyc)=Int(78/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(121/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(164/pAnimSpeed#(cyc))
   Pop(cyc,Rnd(2,8),0)
   If pHolding(cyc)=0 Or weapWear(pHolding(cyc))>0 Then ProduceSound(p(cyc),sImpact(Rnd(5,7)),22050,0)
   If pHolding(cyc)>0 And weapWear(pHolding(cyc))=0 Then ProduceSound(p(cyc),sImpact(Rnd(5,7)),22050,0.5)
   ProduceSound(p(cyc),sThud,22050,0)
   ProduceSound(p(cyc),sFall,22050,0.5)
   ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),1)
   ImpactSpurt(pLimbX#(v,1),pLimbY#(v,1),pLimbZ#(v,1),1,1)
   BloodSpurt(pLimbX#(v,1),pLimbY#(v,1),pLimbZ#(v,1),pA#(v)+180,pScar(v,1)-1,1)
   ScarLimb(v,1,50)
   If pHolding(cyc)>0 And weapWear(pHolding(cyc))=0 Then ScarLimb(v,1,25) : BloodSpurt(pLimbX#(v,1),pLimbY#(v,1),pLimbZ#(v,1),pA#(v)+180,pScar(v,1)-1,1)
   pHealth(v)=pHealth(v)-PercentOf#(pStrength(cyc),150)
   If EntertainViable(cyc,v) Then entScore=entScore+PercentOf#(pStrength(cyc),150)
   pHeat(cyc)=pHeat(cyc)+1 : pHeat(v)=pHeat(v)-1
   WeaponImpact(cyc,v,1)
   FindSmashes(v,pY#(v),1)
   If InsideRing(RealX#(cyc),RealZ#(cyc),-5) Then ShakeRing(0,0.5)
  EndIf
  If pAnimTim(cyc)>Int(215/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,90)
   ChangeAnim(v,150)
   EndMove(cyc,v)
   If matchState=3 And pEliminated(v)=0 Then pDT(v)=0
  EndIf
 EndIf 
 ;CHOKE HOLD
 If pAnim(cyc)=516
  ;apply
  v=pGrappling(cyc)
  animA=529 : animB=530
  If pAnimStage(cyc)=1
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=1.5
    Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0
    Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
   EndIf 
   If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sFall,22050,0.5)
   If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)>Int(30/pAnimSpeed#(cyc)) Then pAnimStage(cyc)=2 : pAnimTim(cyc)=0
  EndIf
  ;wrench
  If pAnimStage(cyc)=2
   If pAnimTim(cyc)=0
    Pop(cyc,Rnd(2,9),0)
    ProduceSound(p(v),sChoke,GetVoice(v),0)
    ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
    If pSpecial(cyc)>0 Then pAnimSpeed#(cyc)=1.25 Else pAnimSpeed#(cyc)=Rnd(0.5,1.0)
    Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,animA+2),5
    Animate p(v),1,pAnimSpeed#(cyc),pSeq(v,animB+2),5
   EndIf
   HoldEffects(cyc,v,2,0.05,0)
  EndIf
  ;leave
  If pAnimStage(cyc)=3
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=1.75
    Animate p(cyc),3,-pAnimSpeed#(cyc),pSeq(cyc,animA),5
    Animate p(v),3,-pAnimSpeed#(cyc),pSeq(v,animB),5
   EndIf
   If pAnimTim(cyc)=Int(5/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)>Int(35/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,507)
  EndIf
 EndIf
 ;CROSSFACE CLUTCH
 If pAnim(cyc)=517
  ;apply
  v=pGrappling(cyc)
  animA=533 : animB=534
  If pAnimStage(cyc)=1
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.5
    Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0
    Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
   EndIf 
   If pAnimTim(cyc)=Int(12/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.2)
   If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sFall,22050,0.5)
   If pAnimTim(cyc)>Int(60/pAnimSpeed#(cyc)) Then pAnimStage(cyc)=2 : pAnimTim(cyc)=0
  EndIf
  ;wrench
  If pAnimStage(cyc)=2
   If pAnimTim(cyc)=0
    Pop(cyc,Rnd(2,9),0)
    ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),0)
    ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
    If pSpecial(cyc)>0 Then pAnimSpeed#(cyc)=1.0 Else pAnimSpeed#(cyc)=Rnd(0.5,0.75)
    Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,animA+2),5
    Animate p(v),1,pAnimSpeed#(cyc),pSeq(v,animB+2),5
   EndIf
   HoldEffects(cyc,v,3,0.05,1)
  EndIf
  ;leave
  If pAnimStage(cyc)=3
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.5
    Animate p(cyc),3,-pAnimSpeed#(cyc),pSeq(cyc,animA),5
    Animate p(v),3,-pAnimSpeed#(cyc),pSeq(v,animB),5
   EndIf
   If pAnimTim(cyc)=Int(5/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.2)
   If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)>Int(65/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,507)
  EndIf
 EndIf
 ;BOW & ARROW
 If pAnim(cyc)=518
  ;apply
  v=pGrappling(cyc)
  animA=537 : animB=538
  If pAnimStage(cyc)=1
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.0
    Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0
    Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
   EndIf 
   If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)>Int(25/pAnimSpeed#(cyc)) Then pAnimStage(cyc)=2 : pAnimTim(cyc)=0
  EndIf
  ;wrench
  If pAnimStage(cyc)=2
   If pAnimTim(cyc)=0
    Pop(cyc,Rnd(2,9),0)
    ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),0)
    ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
    If pSpecial(cyc)>0 Then pAnimSpeed#(cyc)=1.0 Else pAnimSpeed#(cyc)=Rnd(0.5,0.75)
    Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,animA+2),5
    Animate p(v),1,pAnimSpeed#(cyc),pSeq(v,animB+2),5
   EndIf
   HoldEffects(cyc,v,2,0.025,0)
  EndIf
  ;leave
  If pAnimStage(cyc)=3
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.5
    Animate p(cyc),3,-pAnimSpeed#(cyc),pSeq(cyc,animA),5
    Animate p(v),3,-pAnimSpeed#(cyc),pSeq(v,animB),5
   EndIf
   If pAnimTim(cyc)=Int(5/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)>Int(30/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,507)
  EndIf
 EndIf
 ;ARM BAR
 If pAnim(cyc)=519
  ;apply
  v=pGrappling(cyc)
  animA=541 : animB=542
  If pAnimStage(cyc)=1
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.0
    Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0
    Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
   EndIf 
   If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc))
    ProduceSound(p(cyc),sFall,22050,0.5)
    If InsideRing(RealX#(cyc),RealZ#(cyc),-5) Then ShakeRing(0,0.25)
   EndIf
   If pAnimTim(cyc)>Int(70/pAnimSpeed#(cyc)) Then pAnimStage(cyc)=2 : pAnimTim(cyc)=0
  EndIf
  ;wrench
  If pAnimStage(cyc)=2
   If pAnimTim(cyc)=0
    Pop(cyc,Rnd(2,9),0)
    ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),0)
    ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
    If pSpecial(cyc)>0 Then pAnimSpeed#(cyc)=1.0 Else pAnimSpeed#(cyc)=Rnd(0.5,0.75)
    Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,animA+2),5
    Animate p(v),1,pAnimSpeed#(cyc),pSeq(v,animB+2),5
   EndIf
   HoldEffects(cyc,v,2,0.05,0)
  EndIf
  ;leave
  If pAnimStage(cyc)=3
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.5
    Animate p(cyc),3,-pAnimSpeed#(cyc),pSeq(cyc,animA),5
    Animate p(v),3,-pAnimSpeed#(cyc),pSeq(v,animB),5
   EndIf
   If pAnimTim(cyc)=Int(5/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.2)
   If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)>Int(75/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,507)
  EndIf
 EndIf
End Function

;---------------------------------------------------------------------
;////////////////////// LEG MOVE SEQUENCES ///////////////////////////
;---------------------------------------------------------------------
Function LoadLegSequences(cyc)
 ;head grappling
 pSeq(cyc,601)=ExtractAnimSeq(p(cyc),80,125,pSeq(cyc,1050)) ;grasp legs (on back)
 pSeq(cyc,602)=ExtractAnimSeq(p(cyc),80,125,pSeq(cyc,1051)) ;legs grasped (on back)
 pSeq(cyc,603)=ExtractAnimSeq(p(cyc),315,375,pSeq(cyc,1050)) ;grasp legs (from front)
 pSeq(cyc,604)=ExtractAnimSeq(p(cyc),315,375,pSeq(cyc,1051)) ;legs grasped (from front) 
 pSeq(cyc,605)=ExtractAnimSeq(p(cyc),135,175,pSeq(cyc,1050)) ;holding legs
 pSeq(cyc,606)=ExtractAnimSeq(p(cyc),135,175,pSeq(cyc,1051)) ;legs being held
 pSeq(cyc,607)=ExtractAnimSeq(p(cyc),185,245,pSeq(cyc,1050)) ;dragging by legs
 pSeq(cyc,608)=ExtractAnimSeq(p(cyc),185,245,pSeq(cyc,1051)) ;dragged by legs
 pSeq(cyc,609)=ExtractAnimSeq(p(cyc),255,305,pSeq(cyc,1050)) ;release legs
 pSeq(cyc,610)=ExtractAnimSeq(p(cyc),255,305,pSeq(cyc,1051)) ;legs released
 ;drag up to feet
 pSeq(cyc,611)=ExtractAnimSeq(p(cyc),385,450,pSeq(cyc,1050)) ;[execute]
 pSeq(cyc,612)=ExtractAnimSeq(p(cyc),385,450,pSeq(cyc,1051)) ;[receive]
 ;sharpshooter
 If GroundMoveRequired(cyc,611,1) Then pSeq(cyc,613)=ExtractAnimSeq(p(cyc),460,575,pSeq(cyc,1050)) ;apply execute
 If GroundMoveRequired(cyc,611,-1) Then pSeq(cyc,614)=ExtractAnimSeq(p(cyc),460,575,pSeq(cyc,1051)) ;apply receive
 If GroundMoveRequired(cyc,611,1) Then pSeq(cyc,615)=ExtractAnimSeq(p(cyc),575,615,pSeq(cyc,1050)) ;wrench execute
 If GroundMoveRequired(cyc,611,-1) Then pSeq(cyc,616)=ExtractAnimSeq(p(cyc),575,615,pSeq(cyc,1051)) ;wrench receive
 ;groin kick
 If GroundMoveRequired(cyc,612,1) Then pSeq(cyc,617)=ExtractAnimSeq(p(cyc),625,715,pSeq(cyc,1050)) ;[execute]
 If GroundMoveRequired(cyc,612,-1) Then pSeq(cyc,618)=ExtractAnimSeq(p(cyc),625,715,pSeq(cyc,1051)) ;[receive]
 ;boston crab
 If GroundMoveRequired(cyc,613,1) Then pSeq(cyc,619)=ExtractAnimSeq(p(cyc),725,780,pSeq(cyc,1050)) ;apply execute
 If GroundMoveRequired(cyc,613,-1) Then pSeq(cyc,620)=ExtractAnimSeq(p(cyc),725,780,pSeq(cyc,1051)) ;apply receive
 If GroundMoveRequired(cyc,613,1) Then pSeq(cyc,621)=ExtractAnimSeq(p(cyc),780,840,pSeq(cyc,1050)) ;wrench execute
 If GroundMoveRequired(cyc,613,-1) Then pSeq(cyc,622)=ExtractAnimSeq(p(cyc),780,840,pSeq(cyc,1051)) ;wrench receive
 ;half crab
 If GroundMoveRequired(cyc,614,1) Then pSeq(cyc,623)=ExtractAnimSeq(p(cyc),850,905,pSeq(cyc,1050)) ;apply execute
 If GroundMoveRequired(cyc,614,-1) Then pSeq(cyc,624)=ExtractAnimSeq(p(cyc),850,905,pSeq(cyc,1051)) ;apply receive
 If GroundMoveRequired(cyc,614,1) Then pSeq(cyc,625)=ExtractAnimSeq(p(cyc),905,965,pSeq(cyc,1050)) ;wrench execute
 If GroundMoveRequired(cyc,614,-1) Then pSeq(cyc,626)=ExtractAnimSeq(p(cyc),905,965,pSeq(cyc,1051)) ;wrench receive
 ;elevated crab
 If GroundMoveRequired(cyc,615,1) Then pSeq(cyc,627)=ExtractAnimSeq(p(cyc),975,1035,pSeq(cyc,1050)) ;apply execute
 If GroundMoveRequired(cyc,615,-1) Then pSeq(cyc,628)=ExtractAnimSeq(p(cyc),975,1035,pSeq(cyc,1051)) ;apply receive
 If GroundMoveRequired(cyc,615,1) Then pSeq(cyc,629)=ExtractAnimSeq(p(cyc),1035,1075,pSeq(cyc,1050)) ;wrench execute
 If GroundMoveRequired(cyc,615,-1) Then pSeq(cyc,630)=ExtractAnimSeq(p(cyc),1035,1075,pSeq(cyc,1051)) ;wrench receive
 ;figure four leglock
 If GroundMoveRequired(cyc,616,1) Then pSeq(cyc,631)=ExtractAnimSeq(p(cyc),1085,1170,pSeq(cyc,1050)) ;apply execute
 If GroundMoveRequired(cyc,616,-1) Then pSeq(cyc,632)=ExtractAnimSeq(p(cyc),1085,1170,pSeq(cyc,1051)) ;apply receive
 If GroundMoveRequired(cyc,616,1) Then pSeq(cyc,633)=ExtractAnimSeq(p(cyc),1170,1210,pSeq(cyc,1050)) ;wrench execute
 If GroundMoveRequired(cyc,616,-1) Then pSeq(cyc,634)=ExtractAnimSeq(p(cyc),1170,1210,pSeq(cyc,1051)) ;wrench receive
 ;spinning toe hold
 If GroundMoveRequired(cyc,617,1) Then pSeq(cyc,635)=ExtractAnimSeq(p(cyc),1220,1255,pSeq(cyc,1050)) ;apply execute
 If GroundMoveRequired(cyc,617,-1) Then pSeq(cyc,636)=ExtractAnimSeq(p(cyc),1220,1255,pSeq(cyc,1051)) ;apply receive
 If GroundMoveRequired(cyc,617,1) Then pSeq(cyc,637)=ExtractAnimSeq(p(cyc),1255,1300,pSeq(cyc,1050)) ;wrench execute
 If GroundMoveRequired(cyc,617,-1) Then pSeq(cyc,638)=ExtractAnimSeq(p(cyc),1255,1300,pSeq(cyc,1051)) ;wrench receive
 ;leg wrench
 If GroundMoveRequired(cyc,618,1) Then pSeq(cyc,639)=ExtractAnimSeq(p(cyc),1310,1385,pSeq(cyc,1050)) ;apply execute
 If GroundMoveRequired(cyc,618,-1) Then pSeq(cyc,640)=ExtractAnimSeq(p(cyc),1310,1385,pSeq(cyc,1051)) ;apply receive
 If GroundMoveRequired(cyc,618,1) Then pSeq(cyc,641)=ExtractAnimSeq(p(cyc),1385,1425,pSeq(cyc,1050)) ;wrench execute
 If GroundMoveRequired(cyc,618,-1) Then pSeq(cyc,642)=ExtractAnimSeq(p(cyc),1385,1425,pSeq(cyc,1051)) ;wrench receive
 ;slingshot
 If GroundMoveRequired(cyc,619,1) Then pSeq(cyc,643)=ExtractAnimSeq(p(cyc),1435,1610,pSeq(cyc,1050)) ;[execute]
 If GroundMoveRequired(cyc,619,-1) Then pSeq(cyc,644)=ExtractAnimSeq(p(cyc),1435,1610,pSeq(cyc,1051)) ;[receive]
 ;ankle lock
 If GroundMoveRequired(cyc,620,1) Then pSeq(cyc,645)=ExtractAnimSeq(p(cyc),1620,1690,pSeq(cyc,1050)) ;apply execute
 If GroundMoveRequired(cyc,620,-1) Then pSeq(cyc,646)=ExtractAnimSeq(p(cyc),1620,1690,pSeq(cyc,1051)) ;apply receive
 If GroundMoveRequired(cyc,620,1) Then pSeq(cyc,647)=ExtractAnimSeq(p(cyc),1690,1730,pSeq(cyc,1050)) ;wrench execute
 If GroundMoveRequired(cyc,620,-1) Then pSeq(cyc,648)=ExtractAnimSeq(p(cyc),1690,1730,pSeq(cyc,1051)) ;wrench receive
End Function

;---------------------------------------------------------------------
;////////////////////// LEG MOVE ANIMATIONS //////////////////////////
;---------------------------------------------------------------------
Function LegMoves(cyc)
 ;PICK-UP LEGS (off back)
 If pAnim(cyc)=603
  v=pGrappling(cyc)
  If pAnimTim(cyc)=<1 Then pExcusedWorld(cyc)=1 Else pExcusedWorld(cyc)=0
  If pAnimTim(cyc)=0 
   pAnimSpeed#(cyc)=2.0
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,601),0
   Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,602),0
  EndIf
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) Then GrappleOffset(cyc,v,2)
  If pAnimTim(cyc)=>Int(40/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,605)
  FixGroundGrapple(cyc)
  DropWeapon(cyc,1000)
  DropWeapon(v,1000)
 EndIf
 ;PICK-UP LEGS (off front)
 If pAnim(cyc)=604
  v=pGrappling(cyc)
  If pAnimTim(cyc)=<1 Then pExcusedWorld(cyc)=1 Else pExcusedWorld(cyc)=0
  If pAnimTim(cyc)=0 
   pAnimSpeed#(cyc)=1.5
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,603),0
   Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,604),0
  EndIf
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=>Int(30/pAnimSpeed#(cyc)) Then GrappleOffset(cyc,v,2) 
  If pAnimTim(cyc)=>Int(60/pAnimSpeed#(cyc)) 
   ChangeAnim(cyc,605) : SharpTransition(cyc,605,0,180)
   SharpTransition(v,606,0,-1)
  EndIf
  FixGroundGrapple(cyc) 
  DropWeapon(cyc,1000)
  DropWeapon(v,1000)
 EndIf
 ;HOLD LEGS
 If pAnim(cyc)=605
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   pAnimSpeed#(cyc)=0.5
   If pOldAnim(cyc)=604 Then transition=0 Else transition=10
   Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,605),transition
   Animate p(v),1,pAnimSpeed#(cyc),pSeq(v,606),transition
  EndIf
  FixGroundGrapple(cyc)
  GrappleOffset(cyc,v,2)
  If DirPressed(cyc) Then ChangeAnim(cyc,606)
  FindGroundMoves(cyc,2)
 EndIf
 ;DRAG BY LEGS
 If pAnim(cyc)=606
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   pAnimSpeed#(cyc)=1.5
   Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,607),0;5
   Animate p(v),1,pAnimSpeed#(cyc),pSeq(v,608),0;5
   pStepTim#(cyc)=-15 : pStepTim#(v)=-15
  EndIf
  ;movement
  If DirPressed(cyc)
   pTA#(cyc)=CleanAngle#(RequestAngle#(cyc))
   Advance(v,RequestAngle#(cyc),pAnimSpeed#(cyc)/6) 
   turner#=0.5
   pA#(cyc)=pA#(cyc)+ReachAngle#(pA#(cyc),pTA#(cyc),turner#)
   If SatisfiedAngle(pA#(cyc),pTA#(cyc),turner#*2) Then pA#(cyc)=pTA#(cyc)
   pStepTim#(cyc)=pStepTim#(cyc)+pAnimSpeed#(cyc)
   pStepTim#(v)=pStepTim#(v)+(pAnimSpeed#(cyc)/2)
  EndIf
  ;bind to victim
  EnforceBlocks(v)
  FixGroundGrapple(cyc)
  GrappleOffset(cyc,v,2)
  If pAnimTim(cyc)>5 And DirPressed(cyc)=0 Then ChangeAnim(cyc,605)
  FindGroundMoves(cyc,2) 
 EndIf
 ;RELEASE LEGS
 If pAnim(cyc)=607
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=2.0
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,609),0
   Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,610),0
  EndIf
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0) : ProduceSound(p(v),sSwing,22050,0.25)
  If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Then ProduceSound(p(v),sThud,22050,0.5) : ProduceSound(p(v),sFall,22050,0)
  If pAnimTim(cyc)=>Int(50/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,-1)
   ChangeAnim(v,150) : EndMove(cyc,v)
  EndIf
  DropWeapon(cyc,1000)
  DropWeapon(v,1000)
 EndIf
 ;********************** LEG MOVES *****************************
 ;DRAG UP TO FEET
 If pAnim(cyc)=610
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=1.5
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,611),0
   Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,612),0
  EndIf
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then pStepTim#(v)=99
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,22050,0.25)
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,1,2)
  If pAnimTim(cyc)=>Int(35/pAnimSpeed#(cyc)) Then GrappleOffset(cyc,v,0)
  If pAnimTim(cyc)>Int(65/pAnimSpeed#(cyc))
   ChangeAnim(cyc,301) : SharpTransition(cyc,305,1,180)
   ChangeAnim(v,302) : SharpTransition(v,306,1,0)
   pExcusedPlays(cyc)=0 : pExcusedPlays(v)=0
  EndIf
 EndIf
 ;SCORPION LOCK
 If pAnim(cyc)=611
  ;apply
  v=pGrappling(cyc)
  animA=613 : animB=614
  If pAnimStage(cyc)=1
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.0
    Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0
    Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
   EndIf 
   If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(37/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(75/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(55/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(105/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)=Int(55/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(105/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sThud,22050,0.5)
   If pAnimTim(cyc)=Int(65/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.2)
   If pAnimTim(cyc)=Int(100/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sFall,22050,0.5)
   If pAnimTim(cyc)>Int(110/pAnimSpeed#(cyc)) Then pAnimStage(cyc)=2 : pAnimTim(cyc)=0
  EndIf
  ;wrench
  If pAnimStage(cyc)=2
   If pAnimTim(cyc)=0
    Pop(cyc,Rnd(2,9),0)
    ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),0)
    ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
    If pSpecial(cyc)>0 Then pAnimSpeed#(cyc)=1.0 Else pAnimSpeed#(cyc)=Rnd(0.5,0.75)
    Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,animA+2),5
    Animate p(v),1,pAnimSpeed#(cyc),pSeq(v,animB+2),5
   EndIf
   HoldEffects(cyc,v,3,0.05,1)
  EndIf
  ;leave
  If pAnimStage(cyc)=3
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.5
    Animate p(cyc),3,-pAnimSpeed#(cyc),pSeq(cyc,animA),5
    Animate p(v),3,-pAnimSpeed#(cyc),pSeq(v,animB),5
   EndIf
   If pAnimTim(cyc)=Int(5/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.2)
   If pAnimTim(cyc)=Int(5/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(80/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(95/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)>Int(115/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,607)
  EndIf
 EndIf
 ;GROIN KICK
 If pAnim(cyc)=612
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   pAnimSpeed#(cyc)=1.5
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,617),0
   Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,618),0
  EndIf 
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(55/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(55/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.5)
  If pAnimTim(cyc)=Int(41/pAnimSpeed#(cyc))
   Pop(cyc,Rnd(2,7),0) : Pop(0,8,0)
   ProduceSound(p(v),sImpact(7),22050,0) : ProduceSound(p(v),sBlock(7),22050,0)
   ProduceSound(p(v),sBleed,22050,1) : ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),1)
   ImpactSpurt(pLimbX#(v,36),pLimbY#(v,36),pLimbZ#(v,36),1,1)
   BloodSpurt(pLimbX#(v,36),pLimbY#(v,36),pLimbZ#(v,36),-1,pScar(v,36)-1,1)
   ScarLimb(v,36,10)
   pHealth(v)=pHealth(v)-(pStrength(cyc)*3)
   If EntertainViable(cyc,v) Then entScore=entScore+(pStrength(cyc)*5)
   pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-5
  EndIf
  If pAnimTim(cyc)>Int(90/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,180)
   ChangeAnim(v,150) : SharpTransition(v,150,1,0)
   EndMove(cyc,v)
   If matchState=3 And pEliminated(v)=0 Then pDT(v)=0
  EndIf
 EndIf
 ;BOSTON CRAB VARIATIONS
 If pAnim(cyc)=>613 And pAnim(cyc)=<614
  ;apply
  v=pGrappling(cyc)
  If pAnim(cyc)=613 Then animA=619 : animB=620
  If pAnim(cyc)=614 Then animA=623 : animB=624
  If pAnimStage(cyc)=1
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.0
    Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0
    Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
   EndIf 
   If pAnimTim(cyc)=Int(5/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.25)
   If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(55/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sFall,22050,0.5)
   If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sThud,22050,0.5)
   If pAnimTim(cyc)>Int(50/pAnimSpeed#(cyc)) Then pAnimStage(cyc)=2 : pAnimTim(cyc)=0
  EndIf
  ;wrench
  If pAnimStage(cyc)=2
   If pAnimTim(cyc)=0
    Pop(cyc,Rnd(2,9),0)
    ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),0)
    ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
    If pSpecial(cyc)>0 Then pAnimSpeed#(cyc)=1.0 Else pAnimSpeed#(cyc)=Rnd(0.5,0.75)
    Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,animA+2),5
    Animate p(v),1,pAnimSpeed#(cyc),pSeq(v,animB+2),5
   EndIf
   HoldEffects(cyc,v,2,0.05,1)
  EndIf
  ;leave
  If pAnimStage(cyc)=3
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.5
    Animate p(cyc),3,-pAnimSpeed#(cyc),pSeq(cyc,animA),5
    Animate p(v),3,-pAnimSpeed#(cyc),pSeq(v,animB),5
   EndIf
   If pAnimTim(cyc)=Int(5/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.2)
   If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)>Int(55/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,607)
  EndIf
 EndIf
 ;ELEVATED CRAB (LION TAMER)
 If pAnim(cyc)=615
  ;apply
  v=pGrappling(cyc)
  animA=627 : animB=628
  If pAnimStage(cyc)=1
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.0
    Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0
    Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
   EndIf 
   If pAnimTim(cyc)=Int(5/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.25)
   If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sFall,22050,0)
   If pAnimTim(cyc)>Int(55/pAnimSpeed#(cyc)) Then pAnimStage(cyc)=2 : pAnimTim(cyc)=0
  EndIf
  ;wrench
  If pAnimStage(cyc)=2
   If pAnimTim(cyc)=0
    Pop(cyc,Rnd(2,9),0)
    ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),0)
    ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
    If pSpecial(cyc)>0 Then pAnimSpeed#(cyc)=0.75 Else pAnimSpeed#(cyc)=Rnd(0.25,0.5)
    Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,animA+2),5
    Animate p(v),1,pAnimSpeed#(cyc),pSeq(v,animB+2),5
   EndIf
   HoldEffects(cyc,v,3,0.025,1)
  EndIf
  ;leave
  If pAnimStage(cyc)=3
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.5
    Animate p(cyc),3,-pAnimSpeed#(cyc),pSeq(cyc,animA),5
    Animate p(v),3,-pAnimSpeed#(cyc),pSeq(v,animB),5
   EndIf
   If pAnimTim(cyc)=Int(5/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.2)
   If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)>Int(60/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,607)
  EndIf
 EndIf
 ;FIGURE FOUR LEGLOCK
 If pAnim(cyc)=616
  ;apply
  v=pGrappling(cyc)
  animA=631 : animB=632
  If pAnimStage(cyc)=1
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=1.8
    Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0
    Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
   EndIf 
   If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.25)
   If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(55/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)=Int(65/pAnimSpeed#(cyc)) 
    ProduceSound(p(cyc),sFall,22050,0)
    ProduceSound(p(cyc),sThud,22050,0.5)
    If InsideRing(RealX#(cyc),RealZ#(cyc),-5) Then ShakeRing(0,0.25)
   EndIf
   If pAnimTim(cyc)>Int(80/pAnimSpeed#(cyc)) Then pAnimStage(cyc)=2 : pAnimTim(cyc)=0
  EndIf
  ;wrench
  If pAnimStage(cyc)=2
   If pAnimTim(cyc)=0
    Pop(cyc,Rnd(2,9),0)
    ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),0)
    ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
    If pSpecial(cyc)>0 Then pAnimSpeed#(cyc)=1.0 Else pAnimSpeed#(cyc)=Rnd(0.5,0.75)
    Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,animA+2),5
    Animate p(v),1,pAnimSpeed#(cyc),pSeq(v,animB+2),5
   EndIf
   HoldEffects(cyc,v,3,0.025,0)
  EndIf
  ;leave
  If pAnimStage(cyc)=3
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.25
    Animate p(cyc),3,-pAnimSpeed#(cyc),pSeq(cyc,animA),5
    Animate p(v),3,-pAnimSpeed#(cyc),pSeq(v,animB),5
   EndIf
   If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.2)
   If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)>Int(85/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,607)
  EndIf
 EndIf
 ;SPINNING TOE HOLD
 If pAnim(cyc)=617
  ;apply
  v=pGrappling(cyc)
  animA=635 : animB=636
  If pAnimStage(cyc)=1
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=1.5
    Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0
    Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
   EndIf 
   If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.25)
   If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)>Int(35/pAnimSpeed#(cyc)) Then pAnimStage(cyc)=2 : pAnimTim(cyc)=0
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
   HoldEffects(cyc,v,2,0.025,0)
  EndIf
  ;leave
  If pAnimStage(cyc)=3
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=1.5
    Animate p(cyc),3,-pAnimSpeed#(cyc),pSeq(cyc,animA),5
    Animate p(v),3,-pAnimSpeed#(cyc),pSeq(v,animB),5
   EndIf
   If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.2)
   If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)>Int(35/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,607)
  EndIf
 EndIf
 ;LEG WRENCH
 If pAnim(cyc)=618
  ;apply
  v=pGrappling(cyc)
  animA=639 : animB=640
  If pAnimStage(cyc)=1
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.25
    Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0
    Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
   EndIf 
   If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.25)
   If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc))
    ProduceSound(p(cyc),sFall,22050,0)
    ProduceSound(p(cyc),sThud,22050,0.5)
    If InsideRing(RealX#(cyc),RealZ#(cyc),-5) Then ShakeRing(0,0.25)
   EndIf
   If pAnimTim(cyc)>Int(70/pAnimSpeed#(cyc)) Then pAnimStage(cyc)=2 : pAnimTim(cyc)=0
  EndIf
  ;wrench
  If pAnimStage(cyc)=2
   If pAnimTim(cyc)=0
    Pop(cyc,Rnd(2,9),0)
    ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),0)
    ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
    If pSpecial(cyc)>0 Then pAnimSpeed#(cyc)=1.0 Else pAnimSpeed#(cyc)=Rnd(0.5,0.75)
    Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,animA+2),5
    Animate p(v),1,pAnimSpeed#(cyc),pSeq(v,animB+2),5
   EndIf
   HoldEffects(cyc,v,2,0.05,0)
  EndIf
  ;leave
  If pAnimStage(cyc)=3
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.5
    Animate p(cyc),3,-pAnimSpeed#(cyc),pSeq(cyc,animA),5
    Animate p(v),3,-pAnimSpeed#(cyc),pSeq(v,animB),5
   EndIf
   If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.2)
   If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)>Int(75/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,607)
  EndIf
 EndIf
 ;SLINGSHOT
 If pAnim(cyc)=619
  v=pGrappling(cyc)
  If pAnimTim(cyc)=0 
   pAnimSpeed#(cyc)=2.0
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,643),0
   Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,644),0
  EndIf 
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(120/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(150/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0)
  If pAnimTim(cyc)=Int(75/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.25) 
  If pAnimTim(cyc)=>Int(40/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(95/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If v>0 And pAnimTim(cyc)=>Int(40/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(145/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then MoveFallEffect(cyc)
  If v>0 And pAnimTim(cyc)=Int(115/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,1,5,0)
  If pAnimTim(cyc)>Int(175/pAnimSpeed#(cyc))
   ChangeAnim(cyc,156) : SharpTransition(cyc,156,1,0)
   If v>0 Then ChangeAnim(v,152) : SharpTransition(v,152,1,0)
   EndMove(cyc,v)
   If matchState=3 And pEliminated(v)=0 Then pDT(v)=0
  EndIf
  ;If v>0 Then MoveThrowOut(cyc,v,Int(95/pAnimSpeed#(cyc)),Int(100/pAnimSpeed#(cyc)),5)
 EndIf
 ;ANKLE LOCK
 If pAnim(cyc)=620
  ;apply
  v=pGrappling(cyc)
  animA=645 : animB=646
  If pAnimStage(cyc)=1
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.0
    Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0
    Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
   EndIf 
   If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.2)
   If pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sFall,22050,0.5)
   If pAnimTim(cyc)>Int(65/pAnimSpeed#(cyc)) Then pAnimStage(cyc)=2 : pAnimTim(cyc)=0
  EndIf
  ;wrench
  If pAnimStage(cyc)=2
   If pAnimTim(cyc)=0
    Pop(cyc,Rnd(2,9),0)
    ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),0)
    ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
    If pSpecial(cyc)>0 Then pAnimSpeed#(cyc)=1.0 Else pAnimSpeed#(cyc)=Rnd(0.5,0.75)
    Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,animA+2),5
    Animate p(v),1,pAnimSpeed#(cyc),pSeq(v,animB+2),5
   EndIf
   HoldEffects(cyc,v,2,0.05,1)
  EndIf
  ;leave
  If pAnimStage(cyc)=3
   If pAnimTim(cyc)=0 
    pAnimSpeed#(cyc)=2.5
    Animate p(cyc),3,-pAnimSpeed#(cyc),pSeq(cyc,animA),5
    Animate p(v),3,-pAnimSpeed#(cyc),pSeq(v,animB),5
   EndIf
   If pAnimTim(cyc)=Int(5/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.2)
   If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(55/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
   If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
   If pAnimTim(cyc)>Int(70/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,607)
  EndIf
 EndIf
End Function

;--------------------------------------------------------------
;////////////////// RELATED FUNCTIONS /////////////////////////
;--------------------------------------------------------------

;MOVE INTO APPROPRIATE GROUND POSITION
Function GroundPositioning(cyc,v)
 ;find distance behind (head)
 ProjectDummy(v,0,0,-20)
 headX#=EntityX(dummy) : headZ#=EntityZ(dummy)
 ;find distance ahead (legs)
 ProjectDummy(v,0,0,20)
 legX#=EntityX(dummy) : legZ#=EntityZ(dummy)
 ;act on closest
 If GetDistance#(pX#(cyc),pZ#(cyc),headX#,headZ#)<GetDistance#(pX#(cyc),pZ#(cyc),legX#,legZ#) Then range#=-20 Else range#=20
 ProjectDummy(v,0,0,range#)
 checkX#=EntityX(dummy) : checkZ#=EntityZ(dummy)
 ;check for block conflicts
 conflict=0
 If BlockConflict(checkX#,pY#(v),checkZ#,1) Then conflict=1
 If InsideRing(pX#(v),pZ#(v),-15) And InsideRing(checkX#,checkZ#,-15)=0 Then conflict=1
 ;check for item conflicts
 If conflict=0
  For b=1 To no_items
   If checkX#>iX#(b)-30 And checkX#<iX#(b)+30 And checkZ#>iZ#(b)-30 And checkZ#<iZ#(b)+30 And pY#(v)>iY#(b)-5 And pY#(v)<iY#(b)+10 And iState(b)=0
    If ItemCollide(v,b,checkX#,checkZ#,0)=1 Then conflict=1
   EndIf
  Next
 EndIf
 ;act on conflict
 If conflict=1
  If range#>0 Then ProjectDummy(v,0,0,-1)
  If range#<0 Then ProjectDummy(v,0,0,1)
  pX#(v)=EntityX(dummy) : pZ#(v)=EntityZ(dummy)
 EndIf
 ;cruise to location
 ProjectDummy(v,0,0,range#)
 pTX#(cyc)=EntityX(dummy) : pTZ#(cyc)=EntityZ(dummy)
 GetSmoothSpeeds(pX#(cyc),pTX#(cyc),pY#(cyc),pY#(cyc),pZ#(cyc),pTZ#(cyc),10)
 If speedX#<0.5 Then speedX#=0.5
 If speedZ#<0.5 Then speedZ#=0.5
 If pX#(cyc)>pTX#(cyc) Then pX#(cyc)=pX#(cyc)-speedX#
 If pX#(cyc)<pTX#(cyc) Then pX#(cyc)=pX#(cyc)+speedX#
 If pZ#(cyc)>pTZ#(cyc) Then pZ#(cyc)=pZ#(cyc)-speedZ#
 If pZ#(cyc)<pTZ#(cyc) Then pZ#(cyc)=pZ#(cyc)+speedZ#
End Function

;IDENTIFY CLOSEST LIMB
Function ClosestLimb(cyc,v) ;1=head, 2=legs
 ProjectDummy(cyc,0,0,10)
 checkX#=EntityX(dummy) : checkZ#=EntityZ(dummy)
 If GetDistance#(checkX#,checkZ#,pLimbX#(v,1),pLimbZ#(v,1))<GetDistance#(checkX#,checkZ#,pLimbX#(v,43),pLimbZ#(v,43))
  value=1 
 Else 
  value=2
 EndIf
 Return value
End Function

;IDENTIFY NEAREST SIDE (OF GROUNDED VICTIM)
Function NearestHumanSide(cyc,v) ;1=left, 2=right 
 ;consider orientation (0=back, 1=front)
 If PinReaction(v)>0 And PinReaction(v)<>150 And pAnim(v)<>151 Then orientation=1 Else orientation=0
 ;find distance to left
 If orientation=0 Then ProjectDummy(v,-20,0,0)
 If orientation=1 Then ProjectDummy(v,20,0,0) 
 leftX#=EntityX(dummy) : leftZ#=EntityZ(dummy)
 ;find distance to right
 If orientation=0 Then ProjectDummy(v,20,0,0)
 If orientation=1 Then ProjectDummy(v,-20,0,0)
 rightX#=EntityX(dummy) : rightZ#=EntityZ(dummy)
 ;return closest
 If GetDistance#(pX#(cyc),pZ#(cyc),leftX#,leftZ#)<GetDistance#(pX#(cyc),pZ#(cyc),rightX#,rightZ#) Then value=1 Else value=2
 Return value
End Function

;FIX GROUND GRAPPLE
Function FixGroundGrapple(cyc)
 v=pGrappling(cyc)
 pOldX#(cyc)=pX#(cyc) : pOldZ#(cyc)=pZ#(cyc)
 pX#(cyc)=pX#(v) : pZ#(cyc)=pZ#(v) : pA#(v)=pA#(cyc)
 EnforceBlocks(cyc)
End Function

;FIND GROUND MOVES
Function FindGroundMoves(cyc,limb) ;1=head, 2=legs
 v=pGrappling(cyc) 
 ;trigger moves
 move=0 : randy=Rnd(0,50)
 If matchCountOuts=3 And matchCage=0 Then randy=Rnd(0,100)
 If (pControl(cyc)>0 And cAttack(cyc)=1) Or (pControl(cyc)=0 And randy=1)
  If limb=1 Then move=moveAnim(3,charGroundMove(pChar(cyc),1)) ;head move A
  If limb=2 Then move=moveAnim(4,charGroundMove(pChar(cyc),4)) ;leg move A
 EndIf
 If (pControl(cyc)>0 And cRun(cyc)=1) Or (pControl(cyc)=0 And randy=2) 
  If limb=1 Then move=moveAnim(3,charGroundMove(pChar(cyc),2)) ;head move B
  If limb=2 Then move=moveAnim(4,charGroundMove(pChar(cyc),5)) ;leg move B
 EndIf
 If (pControl(cyc)>0 And cPickUp(cyc)=1) Or (pControl(cyc)=0 And randy=3)
  If limb=1 Then move=moveAnim(3,charGroundMove(pChar(cyc),3)) ;head move C
  If limb=2 Then move=moveAnim(4,charGroundMove(pChar(cyc),6)) ;leg move C
 EndIf
 ;drag up
 randy=Rnd(0,50)
 If matchSubs>0 And matchPins=0 And (TouchingRopes(v)=0 Or matchRules=0) Then randy=Rnd(0,1000)
 If matchCountOuts=3 And matchCage=0 Then randy=Rnd(0,25)
 If randy=<1 And pControl(cyc)=0 Then move=510
 If pControl(cyc)>0 And cGrapple(cyc)=1 And DirPressed(cyc) Then move=510
 If pRole(cyc)=1 Or pRole(v)=1 Or pTeam(cyc)=pTeam(v)
  If pControl(cyc)=0 And move>510 Then move=510
 EndIf
 If matchRules=>1 And (TouchingRopes(cyc) Or TouchingRopes(v))
  If pControl(cyc)=0 And move>510 Then move=510
 EndIf
 ;release
 If pControl(cyc)>0 And cGrapple(cyc)=1 And DirPressed(cyc)=0 Then move=507
 ;lose grip
 If ReachedCord(pX#(cyc),pZ#(cyc),pX#(v),pZ#(v),2)=0 Then move=507
 ;drop weapon
 If pControl(cyc)=0 And matchState=3 And matchRules=>2 And LegalMan(cyc) And InsideRing(pX#(cyc),pZ#(cyc),0) And pHolding(cyc)>0 And weapWear(pHolding(cyc))=0 
  If FindReferee(cyc,pFoc(cyc))>0 And move>0 Then DropWeapon(cyc,0)
 EndIf
 ;execute
 If limb=2 And move=>500 And move=<510 Then move=move+100
 If move>0 Then ChangeAnim(cyc,move) : ChangeAnim(v,509) : pWarned(cyc)=0
 ;break out
 If pSpecial(cyc)=0 And pDT(v)=<0
  chance=200
  If (pAnim(cyc)=505 Or pAnim(cyc)=605) And move=0 Then chance=chance/2
  If RushViable(v) And pOutCount(cyc)<pOutCount(v) Then chance=chance/2
  If optLevel=0 And pControl(cyc)=0 And pControl(v)>0 Then chance=chance/2
  If optLevel=6 And pControl(cyc)>0 And pControl(v)=0 Then chance=chance/2
  randy=Rnd(0,chance) 
  If randy=0 Or (randy=<20 And move>0) Or pSpecial(v)>0
   If pControl(v)=0 Or DirPressed(v) Or ActionPressed(v) Then BreakHold(cyc,v,-1)
  EndIf
 EndIf
End Function

;SUBMISSION HOLD EFFECTS
Function HoldEffects(cyc,v,power,crawl#,orientation) ;1=mild, 2=average, 3=powerful
 ;initial entertainment
 If pAnimTim(cyc)=0 And EntertainViable(cyc,v) Then entScore=entScore+(20+(power*15))
 ;movement
 If pAnimTim(cyc)>5
  If DirPressed(v) And crawl#>0
   Advance(v,RequestAngle#(v),crawl#)
   pTA#(v)=RequestAngle#(v)
   If orientation=1 Then pTA#(v)=CleanAngle#(RequestAngle#(v)+180)  ;turned onto front
   If orientation=10 Then pTA#(v)=RequestAngle#(v) ;headlock
   If orientation=11 Then pTA#(v)=CleanAngle#(RequestAngle#(v)+90) ;sleeper hold
   pA#(v)=pA#(v)+ReachAngle(pA#(v),pTA#(v),crawl#*2)
   If SatisfiedAngle(pA#(v),pTA#(v),crawl#*2) Then pA#(v)=pTA#(v)
   pStepTim#(v)=pStepTim#(v)+1.0
  EndIf
  EnforceBlocks(v)
  pX#(cyc)=pX#(v) : pY#(cyc)=pY#(v) : pZ#(cyc)=pZ#(v) : pA#(cyc)=pA#(v)
  EnforceBlocks(cyc)
 EndIf
 ;pain
 If EntertainViable(cyc,v)
  If power=1 Then entScore=entScore+1
  If power=2 Then entScore=entScore+Rnd(1,2)
  If power=3 Then entScore=entScore+Rnd(1,3) 
  If pSpecial(cyc)>0 Then entScore=entScore+1
  If matchSubs>0 And matchPins=0 Then entScore=entScore+1
 EndIf
 pHealth(v)=pHealth(v)-Rnd(0,1)
 If power=<1 Then pHealth(v)=pHealth(v)-Rnd(0,1)
 If power=2 Then pHealth(v)=pHealth(v)-1
 If power=3 Then pHealth(v)=pHealth(v)-Rnd(1,2)  
 If pHolding(cyc)>0 And weapWear(pHolding(cyc))=0 Then pHealth(v)=pHealth(v)-Rnd(0,1) : DropWeapon(cyc,100)
 If pSpecial(cyc)>0 Then pHealth(v)=pHealth(v)-1
 If pSpecial(cyc)>10 Then pSpecial(cyc)=pSpecial(cyc)-1
 randy=Rnd(0,100)
 If randy=0 
  Pop(cyc,Rnd(2,8),0)
  ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),0)
  If pSpecial(cyc)>10 Then pSpecial(cyc)=pSpecial(cyc)-1
  If EntertainViable(cyc,v) Then entScore=entScore+(15+(2*power))
 EndIf
 If randy=1 Then ProduceSound(p(v),sChoke,GetVoice(v),Rnd(0.1,1.0))
 If randy=<1 And pHolding(cyc)>0 And weapWear(pHolding(cyc))=0 
  ProduceSound(p(cyc),weapSound(weapType(pHolding(cyc))),22050,Rnd#(0.1,0.5))
  If EntertainViable(cyc,v) Then entScore=entScore+1 : entHardcore=entHardcore+2
 EndIf
 If randy=<1 Then pHeat(cyc)=pHeat(cyc)+Rnd(1,2) : pHeat(v)=pHeat(v)-Rnd(1,2) 
 ;tapping
 If pStretched(v)<1 Then pStretched(v)=1
 chance=pHealth(v)*20
 If chance<2000 Then chance=2000
 If matchSubs=1 And matchPins=0 And matchKOs=0 And matchBlood=0 Then chance=chance-(chance/4)
 If pHealth(v)<100 Then chance=chance-(chance/4)
 If FindInjury(v)>1 Then chance=chance/2
 If pSpecial(cyc)>0 Then chance=chance/2
 If game=1 And gamAgreement(11)>0 And cyc=matchPlayer And pChar(v)=gamOpponent(gamDate) Then chance=chance/2
 If game=1 And gamAgreement(12)>0 And pChar(cyc)=gamOpponent(gamDate) And v=matchPlayer Then chance=chance/2
 randy=Rnd(1,chance)
 If randy=<power And pAnimTim(cyc)>25
  Pop(cyc,Rnd(2,9),1)
  ProduceSound(cam,sAgony(Rnd(1,3)),GetVoice(v),1) 
  If EntertainViable(cyc,v) Then entScore=entScore+250
  If pStretched(v)<2 
   pStretched(v)=2 : pAnimTim(cyc)=0
   If pDT(v)<300 Then pDT(v)=300
  EndIf
  RiskInjury(v,Rnd(0,5),1)
 EndIf
 ;rope break?
 cancel=0
 If no_refs=0 And matchRules=>1 And LegalMan(cyc) And LegalMan(v) And pAnimTim(cyc)>20
  If TouchingRopes(cyc) Or TouchingRopes(v)
   Pop(v,Rnd(2,9),0) : Pop(0,Rnd(2,9),0)
   ProduceSound(cam,sRopeBreak,0,1)
   entScore=entScore+(charPopularity(pChar(v))*2)
   cancel=1
  EndIf
 EndIf
 ;release
 If pAnimTim(cyc)>10
  If pControl(cyc)>0 And cGrapple(cyc)=1 Then cancel=1
  If pControl(cyc)=0 And (RushViable(cyc) Or pWarned(cyc)>0) Then cancel=1
  If pControl(cyc)=0 And pStretched(v)=2 And pRole(cyc)=0 And pRole(v)=0 And matchState<>3 Then cancel=1
  If cancel=1 Then pAnimStage(cyc)=3 : pAnimTim(cyc)=0 : pStretched(v)=0
 EndIf
 ;break out
 If pAnimTim(cyc)>20 And cancel=0
  break=0
  chance=(pSkill(cyc)+(150-(pHealth(v)/100)))*15
  If RushViable(v) Then chance=chance/2
  If pSpecial(cyc)>0 Then chance=chance*2
  If pSpecial(v)>0 Then chance=chance/2
  randy=Rnd(-3,chance)
  If randy=<power And (DirPressed(v) Or ActionPressed(v) Or pControl(v)=0) Then break=1
  If ReachedCord(pX#(cyc),pZ#(cyc),pX#(v),pZ#(v),2)=0 Then break=1
  If pAnimTim(cyc)>1000 Or (pAnimTim(cyc)>100 And RushViable(v)) Then break=2
  If break=1 And pAnim(cyc)=615 Then break=2
  If break=1 Then BreakHold(cyc,v,orientation)
  If break=2 Then pAnimStage(cyc)=3 : pAnimTim(cyc)=0 : pStretched(v)=0
 EndIf
 ;cause chaos
 If LegalMan(cyc) And LegalMan(v) And matchSubs>0 Then CauseChaos(100)
End Function

;BREAK GROUND MOVE
Function BreakHold(cyc,v,style) ;-1=grapple turn, 0=move on back, 1=move on front, 10+=standing move
 ;effects
 Pop(v,Rnd(2,9),0) 
 ProduceSound(p(cyc),sBlock(Rnd(1,4)),22050,0)
 ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0) 
 ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
 ProduceSound(p(cyc),sSwing,20000,0.25)
 If EntertainViable(cyc,v) Then entScore=entScore+charPopularity(pChar(v)) 
 ;ground transitions
 If style<10
  BreakPlayer(cyc)
  pHurtA#(cyc)=EntityYaw(pLimb(cyc,36),1)+180
  If style=-1 Then ChangeAnim(v,151)
  If style=0 Then ChangeAnim(v,99)
  If style=1 Then ChangeAnim(v,109) : SharpTransition(v,152,1,180)
  pHurtTim(v)=10
  EnforceBlocks(v)
 EndIf
 ;standing transition
 If style=>10
  ChangeAnim(cyc,Rnd(102,105)) : SharpTransition(cyc,pAnim(cyc),1,-1)
  pHurtA#(cyc)=pA#(cyc)+180 : pStagger#(cyc)=0.2
  pOldX#(cyc)=pX#(cyc) : pOldZ#(cyc)=pZ#(cyc) 
  BreakPlayer(v)
 EndIf
 EndMove(cyc,v)
End Function