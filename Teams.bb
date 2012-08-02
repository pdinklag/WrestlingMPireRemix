;//////////////////////////////////////////////////////////////////////////////
;----------------------- WRESTLING MPIRE 2008: TEAM MOVES ---------------------
;//////////////////////////////////////////////////////////////////////////////

;---------------------------------------------------------------------
;///////////////////// TEAM MOVE SEQUENCES ///////////////////////////
;---------------------------------------------------------------------
Function LoadTeamMoveSequences(cyc)
 ;double suplex
 pSeq(cyc,801)=ExtractAnimSeq(p(cyc),70,195,pSeq(cyc,1070)) ;[execute]
 pSeq(cyc,802)=ExtractAnimSeq(p(cyc),70,195,pSeq(cyc,1071)) ;[receive]
 pSeq(cyc,803)=ExtractAnimSeq(p(cyc),70,195,pSeq(cyc,1072)) ;[assist]
 ;double hip toss
 pSeq(cyc,804)=ExtractAnimSeq(p(cyc),205,295,pSeq(cyc,1070)) ;[execute]
 pSeq(cyc,805)=ExtractAnimSeq(p(cyc),205,295,pSeq(cyc,1071)) ;[receive]
 pSeq(cyc,806)=ExtractAnimSeq(p(cyc),205,295,pSeq(cyc,1072)) ;[assist]
 ;death drop
 pSeq(cyc,807)=ExtractAnimSeq(p(cyc),305,420,pSeq(cyc,1070)) ;[execute]
 pSeq(cyc,808)=ExtractAnimSeq(p(cyc),305,420,pSeq(cyc,1071)) ;[receive]
 pSeq(cyc,809)=ExtractAnimSeq(p(cyc),305,420,pSeq(cyc,1072)) ;[assist]
 ;decapitation
 pSeq(cyc,810)=ExtractAnimSeq(p(cyc),430,560,pSeq(cyc,1070)) ;[execute]
 pSeq(cyc,811)=ExtractAnimSeq(p(cyc),430,560,pSeq(cyc,1071)) ;[receive]
 pSeq(cyc,812)=ExtractAnimSeq(p(cyc),430,560,pSeq(cyc,1072)) ;[assist]
 ;double lift
 pSeq(cyc,813)=ExtractAnimSeq(p(cyc),570,690,pSeq(cyc,1070)) ;[execute]
 pSeq(cyc,814)=ExtractAnimSeq(p(cyc),570,690,pSeq(cyc,1071)) ;[receive]
 pSeq(cyc,815)=ExtractAnimSeq(p(cyc),570,690,pSeq(cyc,1072)) ;[assist]
End Function

;-----------------------------------------------------------------
;/////////////////// TEAM MOVE ANIMATIONS ////////////////////////
;-----------------------------------------------------------------
Function TeamMoves(cyc)
 ;DOUBLE SUPLEX
 If pAnim(cyc)=801
  v=pGrappling(cyc) : a=pGrappleAssist(cyc)
  pExcusedItems(cyc)=1 : pExcusedItems(v)=1 : pExcusedItems(a)=1
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=1.6
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,801),0
   Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,802),0
   Animate p(a),3,pAnimSpeed#(cyc),pSeq(a,803),0
  EndIf
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then Pop(cyc,Rnd(2,9),1) : Pop(a,Rnd(2,9),1)
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(27/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,0,3)
  If pAnimTim(cyc)=>Int(70/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(100/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If v>0 And pAnimTim(cyc)=>Int(70/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(100/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=>Int(70/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(100/pAnimSpeed#(cyc)) Then FindSmashes(a,LowestValue#(pLimbY#(a,1),pLimbY#(a,36)),0)
  If v>0 And pAnimTim(cyc)=Int(80/pAnimSpeed#(cyc))
   MoveImpact(cyc,v,3,8,2)
   If InsideRing(RealX#(cyc),RealZ#(cyc),-5) Then ShakeRing(0,3.0)
   ProduceSound(pLimb(a,36),sFall,22050,0)
   ProduceSound(pLimb(a,36),sThud,22050,1)
   pHeat(a)=pHeat(a)+5
  EndIf
  If pAnimTim(cyc)>Int(125/pAnimSpeed#(cyc))
   ChangeAnim(cyc,150) : SharpTransition(cyc,150,1,0)
   If v>0 Then ChangeAnim(v,150) : SharpTransition(v,150,1,180)
   ChangeAnim(a,150) : SharpTransition(a,150,1,0)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveThrowOut(cyc,v,Int(70/pAnimSpeed#(cyc)),Int(75/pAnimSpeed#(cyc)),3)
  entScore=entScore+1
 EndIf
 ;DOUBLE HIP TOSS
 If pAnim(cyc)=802
  v=pGrappling(cyc) : a=pGrappleAssist(cyc)
  pExcusedItems(v)=1
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=1.7
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,804),0
   Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,805),0
   Animate p(a),3,pAnimSpeed#(cyc),pSeq(a,806),0
  EndIf
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then Pop(cyc,Rnd(2,9),1) : Pop(a,Rnd(2,9),1)
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,0,3)
  If v>0 And pAnimTim(cyc)=>Int(45/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(80/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If v>0 And pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,2,8,1) : pHeat(a)=pHeat(a)+5
  If pAnimTim(cyc)>Int(90/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,180)
   If v>0 Then ChangeAnim(v,150) : SharpTransition(v,150,1,180)
   ChangeAnim(a,0) : SharpTransition(a,0,1,180)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveThrowOut(cyc,v,Int(45/pAnimSpeed#(cyc)),Int(55/pAnimSpeed#(cyc)),3)
  entScore=entScore+1 
 EndIf
 ;DEATH DROP
 If pAnim(cyc)=803
  v=pGrappling(cyc) : a=pGrappleAssist(cyc)
  pExcusedItems(cyc)=1 : pExcusedItems(v)=1 : pExcusedItems(a)=1
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=1.5
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,807),0
   Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,808),0
   Animate p(a),3,pAnimSpeed#(cyc),pSeq(a,809),0
  EndIf
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then Pop(cyc,Rnd(2,9),1) : Pop(a,Rnd(2,9),1)
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.25)
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,0,4)
  If pAnimTim(cyc)=>Int(50/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(85/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pAnimTim(cyc)=>Int(50/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(85/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=>Int(50/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(85/pAnimSpeed#(cyc)) Then FindSmashes(a,LowestValue#(pLimbY#(a,1),pLimbY#(a,36)),0)
  If pAnimTim(cyc)=Int(65/pAnimSpeed#(cyc)) 
   MoveImpact(cyc,v,4,10,2)
   If InsideRing(RealX#(cyc),RealZ#(cyc),-5) Then ShakeRing(0,3.0)
   ProduceSound(pLimb(a,36),sFall,22050,0)
   ProduceSound(pLimb(a,36),sThud,22050,1)
   pHeat(a)=pHeat(a)+5
  EndIf 
  If pAnimTim(cyc)=Int(85/pAnimSpeed#(cyc)) Then MoveFallEffect(v)
  If pAnimTim(cyc)>Int(115/pAnimSpeed#(cyc))
   ChangeAnim(cyc,154) : SharpTransition(cyc,154,1,270)
   ChangeAnim(v,150) : SharpTransition(v,150,1,90)
   ChangeAnim(a,154) : SharpTransition(a,154,1,90)
   EndMove(cyc,v)
  EndIf
  entScore=entScore+1
 EndIf
 ;DECAPITATION
 If pAnim(cyc)=804
  v=pGrappling(cyc) : a=pGrappleAssist(cyc)
  pExcusedItems(cyc)=1 : pExcusedItems(v)=1 : pExcusedItems(a)=1
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=1.6
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,810),0
   Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,811),0
   Animate p(a),3,pAnimSpeed#(cyc),pSeq(a,812),0
  EndIf
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then Pop(cyc,Rnd(2,9),1) : Pop(a,Rnd(2,9),1)
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(105/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(130/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.25)
  If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,0,4)
  If pAnimTim(cyc)=>Int(65/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(100/pAnimSpeed#(cyc)) Then FindSmashes(cyc,LowestValue#(pLimbY#(cyc,1),pLimbY#(cyc,36)),0)
  If pAnimTim(cyc)=>Int(65/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(100/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=>Int(65/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(100/pAnimSpeed#(cyc)) Then FindSmashes(a,LowestValue#(pLimbY#(a,1),pLimbY#(a,36)),0)
  If pAnimTim(cyc)=Int(80/pAnimSpeed#(cyc)) 
   MoveImpact(cyc,v,4,10,2)
   If InsideRing(RealX#(cyc),RealZ#(cyc),-5) Then ShakeRing(0,3.0)
   ProduceSound(pLimb(a,36),sFall,22050,0)
   ProduceSound(pLimb(a,36),sThud,22050,1)
   pHeat(a)=pHeat(a)+5
  EndIf 
  If pAnimTim(cyc)>Int(130/pAnimSpeed#(cyc))
   ChangeAnim(cyc,154) : SharpTransition(cyc,154,1,90)
   ChangeAnim(v,150) : SharpTransition(v,150,1,270)
   ChangeAnim(a,154) : SharpTransition(a,154,1,270)
   EndMove(cyc,v)
  EndIf
  entScore=entScore+1
 EndIf
 ;DOUBLE LIFT
 If pAnim(cyc)=805
  v=pGrappling(cyc) : a=pGrappleAssist(cyc)
  pExcusedItems(v)=1
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=1.75
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,813),0
   Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,814),0
   Animate p(a),3,pAnimSpeed#(cyc),pSeq(a,815),0
  EndIf
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then Pop(cyc,Rnd(2,9),1) : Pop(a,Rnd(2,9),1)
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,Rnd(0.3,0.6))
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0)
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then BreakMove(cyc,v,0,3)
  If v>0 And pAnimTim(cyc)=>Int(65/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(95/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If v>0 And pAnimTim(cyc)=Int(80/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,2,8,0) : pHeat(a)=pHeat(a)+5
  If pAnimTim(cyc)>Int(120/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,0)
   If v>0 Then ChangeAnim(v,152) : SharpTransition(v,152,1,90)
   ChangeAnim(a,0) : SharpTransition(a,0,1,180)
   EndMove(cyc,v)
  EndIf
  If v>0 Then MoveThrowOut(cyc,v,Int(60/pAnimSpeed#(cyc)),Int(65/pAnimSpeed#(cyc)),10)
  entScore=entScore+1
 EndIf
End Function

;--------------------------------------------------------------
;////////////////// RELATED FUNCTIONS /////////////////////////
;--------------------------------------------------------------

;FIND DOUBLE TEAM ASSISTANCE
Function FindAssistance(cyc,v)
 a=0
 If pRole(cyc)<>1 And pPlatform(cyc)=0 And pPlatform(v)=0
  For count=1 To no_plays
   If count<>cyc And count<>v And pRole(count)<>1 And pAnim(count)=300 And InProximity(count,v,30) And pY#(count)=pY#(v) And pPlatform(count)=0 And pTeam(count)<>pTeam(v)
    If InLine(count,p(v),45) Then a=count
   EndIf
  Next
 EndIf
 Return a
End Function

;FIND POTENTIAL DOUBLE TEAM ASSISTANCE
Function PotentialAssistance(cyc)
 a=0 : v=pFoc(cyc)
 If v>0 And pRole(cyc)<>1 And pTeam(cyc)<>pTeam(v) And AttackViable(v)=>1 And AttackViable(v)=<2 And pPlatform(cyc)=0 And pPlatform(v)=0
  For count=1 To no_plays
   If count<>cyc And count<>v And pRole(count)<>1 And pFoc(count)=v And pAnim(count)=300 And InProximity(count,v,30) And pY#(count)=pY#(v) And pPlatform(count)=0 And pTeam(count)<>pTeam(v)
    If InLine(count,p(v),45) Then a=count
   EndIf
  Next
 EndIf
 Return a
End Function

;FIND APPROPRIATE TEAM MOVE
Function FindTeamMove(cyc,a)
 move=801 : hi=9999 
 ;assistant near right (suplex)
 ProjectDummy(cyc,5,0,0)
 If GetDistance#(pX#(a),pZ#(a),EntityX(dummy),EntityZ(dummy))<hi Then move=801 : hi=GetDistance#(pX#(a),pZ#(a),EntityX(dummy),EntityZ(dummy))
 ;assistant near left (hip toss)
 ProjectDummy(cyc,-5,0,0)
 If GetDistance#(pX#(a),pZ#(a),EntityX(dummy),EntityZ(dummy))<hi Then move=802 : hi=GetDistance#(pX#(a),pZ#(a),EntityX(dummy),EntityZ(dummy)) 
 ;assistant far right (death drop)
 ProjectDummy(cyc,30,0,15)
 If GetDistance#(pX#(a),pZ#(a),EntityX(dummy),EntityZ(dummy))<hi Then move=803 : hi=GetDistance#(pX#(a),pZ#(a),EntityX(dummy),EntityZ(dummy))
 ;assistant far left (decapitation)
 ProjectDummy(cyc,-30,0,15)
 If GetDistance#(pX#(a),pZ#(a),EntityX(dummy),EntityZ(dummy))<hi Then move=804 : hi=GetDistance#(pX#(a),pZ#(a),EntityX(dummy),EntityZ(dummy)) 
 ;assistant on other side (lift)
 ProjectDummy(cyc,0,0,30)
 If GetDistance#(pX#(a),pZ#(a),EntityX(dummy),EntityZ(dummy))<hi Then move=805 : hi=GetDistance#(pX#(a),pZ#(a),EntityX(dummy),EntityZ(dummy)) 
 Return move
End Function