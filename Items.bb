;//////////////////////////////////////////////////////////////////////////////
;------------------- WRESTLING MPIRE 2008: ITEMS & WEAPONS --------------------
;//////////////////////////////////////////////////////////////////////////////

;-----------------------------------------------------------------
;//////////////////// LOAD ITEM INTERACTION //////////////////////
;-----------------------------------------------------------------
Function ItemAnims(cyc)
 ;200-210: ITEMS
 ;pick up item
 If pAnim(cyc)=200
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=2.5
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,500),10/pAnimSpeed#(cyc)
  EndIf
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,22050,0.2)
  If pAnimTim(cyc)=>Int(15/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(35/pAnimSpeed#(cyc))
   For v=1 To no_items
    viable=0
    If iState(v)=0 And iHeight#(iType(v))>15 Then viable=1
    If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Then viable=1
    If pAnimTim(cyc)<Int(20/pAnimSpeed#(cyc)) And iCarryAnim(iType(v),iState(v))=1 Then viable=0
    If viable=1 And ItemProximity(cyc,v,30) And pY#(cyc)>iY#(v)-5 And pY#(cyc)<iY#(v)+5 And pCarrying(cyc)=0 And iCarrier(v)=0 And iCarryAnim(iType(v),iState(v))=>0
     If ItemRange(cyc,v,20) And ItemCollide(cyc,v,pX#(cyc),pZ#(cyc),2)
	  ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
	  ProduceSound(p(cyc),sItem,22050,0)
	  pCarrying(cyc)=v : iCarrier(v)=cyc 
	  ChangeAnim(cyc,201)
	  For count=1 To no_weaps
	   If ItemCollide(0,v,weapX#(count),weapZ#(count),0) And weapY#(count)=>(iY#(v)+iHeight#(iType(v)))-5 And weapY#(count)=<(iY#(v)+iHeight#(iType(v)))+5
	    weapGravity#(count)=weapWeight#(weapType(count))*6
	    weapFlight#(count)=Rnd#(0.0,0.5) : weapFlightA#(count)=Rnd(0,360)
	   EndIf
	  Next
	 EndIf
	EndIf
   Next
  EndIf
  If pCarrying(cyc)>0 And iBurning(pCarrying(cyc))>0 Then BurnHands(cyc,1,0)
  If pAnimTim(cyc)=>Int(70/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,0)
 EndIf
 ;holding item
 If pAnim(cyc)=201
  newAnim=-1
  v=pCarrying(cyc)
  If pAnimTim(cyc)=0
   If iCarryAnim(iType(v),iState(v))=1 Then anim=201 Else anim=200
   transition=10
   If pOldAnim(cyc)=200
    If iCarryAnim(iType(v),iState(v))=1 Then transition=10
    If iState(v)=1 Or iHeight#(iType(v))<15 Then transition=15
   EndIf
   Animate p(cyc),1,Rnd#(0.25,0.5),pSeq(cyc,anim),transition
  EndIf
  BindItem(cyc)
  If pAnimTim(cyc)>10 Or (pAnimTim(cyc)>5 And pOldAnim(cyc)<>200)  
   If DirPressed(cyc) Then newAnim=202
   If ActionPressed(cyc) And cRun(cyc)=0 Then newAnim=203
   If iState(v)=1
    randy=Rnd(0,100)
    If (pControl(cyc)>0 And cRun(cyc)=1 And cPickUp(cyc)=1) Or (randy=0 And pControl(cyc)=0) Then newAnim=205
   EndIf
  EndIf
  If newAnim=>0 Then ChangeAnim(cyc,newAnim)
 EndIf
 ;dragging item
 If pAnim(cyc)=202
  newAnim=-1
  v=pCarrying(cyc)
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=1.5
   If iCarryAnim(iType(v),iState(v))=1 Then anim=203 Else anim=202
   Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,anim),5
   pStepTim#(cyc)=-15
  EndIf
  moveAngle#=RequestAngle#(cyc)
  If pControl(cyc)=0 And cUpTim(cyc)<5 And cDownTim(cyc)<5 And cLeftTim(cyc)<5 And cRightTim(cyc)<5
   PositionEntity dummy,pActX#(cyc),pY#(cyc),pActZ#(cyc)
   PointEntity p(cyc),dummy
   moveAngle#=CleanAngle#(EntityYaw(p(cyc)))
   RotateEntity p(cyc),0,pA#(cyc),0
  EndIf
  If DirPressed(cyc) Then Advance(cyc,moveAngle#,0.25)
  pStepTim#(cyc)=pStepTim#(cyc)+pAnimSpeed#(cyc) 
  BindItem(cyc)
  If pAnimTim(cyc)>5 
   If DirPressed(cyc)=0 Then newAnim=201
   If ActionPressed(cyc) And cRun(cyc)=0 Then newAnim=203
   If iState(v)=1
    randy=Rnd(0,100)
    If (pControl(cyc)>0 And cRun(cyc)=1 And cPickUp(cyc)=1) Or (randy=0 And pControl(cyc)=0) Then newAnim=205
   EndIf
  EndIf
  If newAnim=>0 Then ChangeAnim(cyc,newAnim)
 EndIf
 ;drop item
 If pAnim(cyc)=203
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,1.0,pSeq(cyc,500),10
  If pAnimTim(cyc)>5 Then ChangeAnim(cyc,0)
  If pRole(cyc)=1 And pCarrying(cyc)>0 Then pAgenda(cyc)=0 : pOldAgenda(cyc)=-1 
  DropItem(cyc) 
 EndIf
 ;steer item into climb
 If pAnim(cyc)=204
  v=pCarrying(cyc)
  If pAnimTim(cyc)=0 
   anim=202 : pAnimSpeed#(cyc)=1.5
   If iCarryAnim(iType(v),iState(v))=1 Then anim=203 Else anim=202
   Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,anim),5
  EndIf
  If (pY#(cyc)=wGround# And pPlatform(cyc)=1) Or (pY#(cyc)=wStage# And pPlatform(cyc)=3) Then tA#=180
  If (pY#(cyc)=wGround# And pPlatform(cyc)=2) Or (pY#(cyc)=wStage# And pPlatform(cyc)=4) Then tA#=90
  If (pY#(cyc)=wGround# And pPlatform(cyc)=3) Or (pY#(cyc)=wStage# And pPlatform(cyc)=1) Then tA#=0
  If (pY#(cyc)=wGround# And pPlatform(cyc)=4) Or (pY#(cyc)=wStage# And pPlatform(cyc)=2) Then tA#=270
  pA#(cyc)=pA#(cyc)+ReachAngle#(pA#(cyc),tA#,10)
  pStepTim#(cyc)=pStepTim#(cyc)+pAnimSpeed#(cyc)
  If SatisfiedAngle(pA#(cyc),tA#,15) 
   pA#(cyc)=tA#
   If pY#(cyc)=wGround# Then ChangeAnim(cyc,40)
   If pY#(cyc)=wStage# Then ChangeAnim(cyc,42)
  EndIf
  BindItem(cyc)
 EndIf
 ;fix item
 If pAnim(cyc)=205
  v=pCarrying(cyc)
  If pAnimTim(cyc)=0 
   pAnimSpeed#(cyc)=2.5
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,500),15/pAnimSpeed#(cyc)
   Animate i(v),3,-3.0,1,0
  EndIf
  If v>0 
   PositionEntity dummy,iX#(v),iY#(v),iZ#(v)
   RotateEntity dummy,0,pA#(cyc),0
   MoveEntity dummy,0,0,0.25
   If iType(v)=>4 And iType(v)=<6 Then MoveEntity dummy,0,0,0.1 : iA#(v)=iA#(v)+5
   iX#(v)=EntityX(dummy) : iZ#(v)=EntityZ(dummy)
   If iY#(v)>pY#(cyc) Then iY#(v)=iY#(v)-1.0
   If iY#(v)<pY#(cyc) Then iY#(v)=pY#(cyc)
  EndIf
  If pAnimTim(cyc)=5 Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0.5)
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc))
   ProduceSound(i(v),iSound(iType(v)),22050,0)
   iState(v)=0 : DropItem(cyc)
  EndIf
  If pAnimTim(cyc)>Int(70/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,0)
 EndIf
 ;210-220: WEAPONS
 ;pick up weapon
 If pAnim(cyc)=210
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=1.5
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,210),10/pAnimSpeed#(cyc)
   pTravel#(cyc)=0.25
  EndIf
  pTravel#(cyc)=pTravel#(cyc)-(pTravel#(cyc)/Int(45/pAnimSpeed#(cyc)))
  If pTravel#(cyc)<0 Then pTravel#(cyc)=0
  Advance(cyc,pA#(cyc),pTravel#(cyc)) 
  If pTravel#(cyc)>0.1 Then pStepTim#(cyc)=pStepTim#(cyc)+1
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,22050,0.2)
  If pAnimTim(cyc)=>Int(15/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(25/pAnimSpeed#(cyc)) And pHolding(cyc)=0 And pScar(cyc,21)=<4
   For v=1 To no_weaps
    range#=5+weapSize#(weapType(v))
    If pLimbY#(cyc,21)=>weapY#(v)-5 And pLimbY#(cyc,21)=<weapY#(v)+15 And pHolding(cyc)=0 And (pWeaponAccess(cyc,v)=0 Or weapCarrier(v)>0)
     If ReachedCord(pLimbX#(cyc,21),pLimbZ#(cyc,21),weapX#(v),weapZ#(v),range#)
      If (weapCarrier(v)=0 And weapY#(v)=weapGround#(v)) Or (weapCarrier(v)>0 And AttackViable(weapCarrier(v))>0)
	   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
	   ProduceSound(weap(v),weapSound(weapType(v)),22050,0.5)
	   CreateSpurt(weapX#(v),weapY#(v)+1,weapZ#(v),-1,1,5,7) 
	   If weapCarrier(v)>0
	    ProduceSound(p(weapCarrier(v)),sPain(Rnd(1,8)),GetVoice(weapCarrier(v)),0)
	    If AttackViable(weapCarrier(v))=>2 
	     GroundReaction(weapCarrier(v)) 
	    Else
	     ChangeAnim(weapCarrier(v),Rnd(102,105)) : pHurtTim(weapCarrier(v))=10
	     pHurtA#(weapCarrier(v))=pA#(cyc) : pStagger#(weapCarrier(v))=0.5
	    EndIf
	    pFoc(weapCarrier(v))=cyc : pAngerTim(weapCarrier(v),cyc)=50
	    If EntertainViable(cyc,v) Then entScore=entScore+100
	   EndIf
	   AttainWeapon(cyc,v)
	  EndIf
	 EndIf 
	EndIf
   Next
  EndIf
  If pHolding(cyc)>0 And weapBurning(pHolding(cyc))>0 Then BurnHands(cyc,0,10)
  If pAnimTim(cyc)>Int(30/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,0)
 EndIf
 ;snatch weapon / pick up high
 If pAnim(cyc)=211
  If pAnimTim(cyc)=0 
   pAnimSpeed#(cyc)=2.5
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,211),15/pAnimSpeed#(cyc)
   pTravel#(cyc)=0.5
  EndIf
  pTravel#(cyc)=pTravel#(cyc)-(pTravel#(cyc)/Int(45/pAnimSpeed#(cyc)))
  If pTravel#(cyc)<0 Then pTravel#(cyc)=0
  Advance(cyc,pA#(cyc),pTravel#(cyc))
  If pTravel#(cyc)>0.1 Then pStepTim#(cyc)=pStepTim#(cyc)+1                                                                
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,22050,0.5)
  If pAnimTim(cyc)=>Int(30/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(45/pAnimSpeed#(cyc)) And pHolding(cyc)=0 And pScar(cyc,21)=<4
   For v=1 To no_weaps
    range#=5+weapSize#(weapType(v)) : height#=5
    If weapCarrier(v)>0 Then range#=5+(weapSize#(weapType(v))/2) : height#=10
    If pLimbY#(cyc,21)=>weapY#(v)-height# And pLimbY#(cyc,21)=<weapY#(v)+height# And pHolding(cyc)=0 And (pWeaponAccess(cyc,v)=0 Or weapCarrier(v)>0)
     If ReachedCord(pLimbX#(cyc,21),pLimbZ#(cyc,21),weapX#(v),weapZ#(v),range#)
      If weapCarrier(v)=0 Or AttackViable(weapCarrier(v))=1
	   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
	   ProduceSound(weap(v),weapSound(weapType(v)),22050,0.5)
	   CreateSpurt(weapX#(v),weapY#(v)+1,weapZ#(v),-1,2,10,7) 
	   If weapCarrier(v)>0
	    ProduceSound(p(weapCarrier(v)),sPain(Rnd(1,8)),GetVoice(weapCarrier(v)),0)
	    ChangeAnim(weapCarrier(v),Rnd(102,105)) : pHurtTim(weapCarrier(v))=10
	    pHurtA#(weapCarrier(v))=pA#(cyc) : pStagger#(weapCarrier(v))=0.5
	    pFoc(weapCarrier(v))=cyc : pAngerTim(weapCarrier(v),cyc)=50
	    If EntertainViable(cyc,v) Then entScore=entScore+100
	   EndIf
	   AttainWeapon(cyc,v)
	  EndIf
	 EndIf 
	EndIf
   Next
  EndIf
  If pHolding(cyc)>0 And weapBurning(pHolding(cyc))>0 Then BurnHands(cyc,0,10)
  If pAnimTim(cyc)>Int(65/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,0)
 EndIf
 ;drop weapon
 If pAnim(cyc)=212
  If pAnimTim(cyc)=0 
   pAnimSpeed#(cyc)=2.0
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,212),10/pAnimSpeed#(cyc)
  EndIf
  If pAnimTim(cyc)=Int(5/pAnimSpeed#(cyc)) Then DropWeapon(cyc,0) 
  If pAnimTim(cyc)>Int(15/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,0)
 EndIf
 ;throw weapon
 If pAnim(cyc)=213
  If pAnimTim(cyc)=0 
   pAnimSpeed#(cyc)=2.0
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,213),20/pAnimSpeed#(cyc)
   weapGravity#(pHolding(cyc))=1.0
   pTravel#(cyc)=0.5
   If matchState=3 And matchRules=>1 And pRole(cyc)=1 
    randy=Rnd(0,1)
    If randy=0 Then pA#(cyc)=pA#(cyc)-45
    If randy=1 Then pA#(cyc)=pA#(cyc)+45
   EndIf
  EndIf
  pTravel#(cyc)=pTravel#(cyc)-(pTravel#(cyc)/Int(45/pAnimSpeed#(cyc)))
  If pTravel#(cyc)<0 Then pTravel#(cyc)=0
  Advance(cyc,pA#(cyc),pTravel#(cyc))
  If pTravel#(cyc)>0.1 Then pStepTim#(cyc)=pStepTim#(cyc)+1 
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,22050,0)
  If pAnimTim(cyc)=<Int(35/pAnimSpeed#(cyc))
   If cGrapple(cyc)=1 Or pControl(cyc)=0 Then weapGravity#(pHolding(cyc))=weapGravity#(pHolding(cyc))+0.1
  EndIf
  If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Then ThrowWeapon(cyc)
  If pAnimTim(cyc)>Int(70/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,0)
  pHealth(cyc)=pHealth(cyc)-Rnd(0,1)
 EndIf
 ;set fire to weapon/item
 If pAnim(cyc)=214
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=1.5
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,210),10/pAnimSpeed#(cyc)
   pTravel#(cyc)=0.25
   pSting(cyc)=1
  EndIf
  pTravel#(cyc)=pTravel#(cyc)-(pTravel#(cyc)/Int(45/pAnimSpeed#(cyc)))
  If pTravel#(cyc)<0 Then pTravel#(cyc)=0
  Advance(cyc,pA#(cyc),pTravel#(cyc)) 
  If pTravel#(cyc)>0.1 Then pStepTim#(cyc)=pStepTim#(cyc)+1
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,22050,0.2)
  If pAnimTim(cyc)=>Int(10/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(20/pAnimSpeed#(cyc))
   limb=pLimb(cyc,19)
   CreateSpurt(pLimbX#(cyc,21),pLimbY#(cyc,21),pLimbZ#(cyc,21),-1,0,5,9)
   randy=Rnd(0,2)
   If randy=0 Then CreateParticle(pLimbX#(cyc,21),pLimbY#(cyc,21),pLimbZ#(cyc,21),-1,2)
   If randy=1 Then CreateParticle(pLimbX#(cyc,21),pLimbY#(cyc,21),pLimbZ#(cyc,21),-1,13) 
  EndIf
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) And pScar(cyc,21)=<4 And optFX>0
   If pHolding(cyc)>0 And weapBurning(pHolding(cyc))>0
    For v=1 To no_items
     range#=HighestValue#(iSizeX#(iType(v)),iSizeZ#(iType(v)))+8
     If ItemProximity(cyc,v,30) And pY#(cyc)>iY#(v)-5 And pY#(cyc)<iY#(v)+5 And iState(v)=1 And iBurning(v)=0 And iFlammable(iType(v)) And iCarrier(v)=0 And pSting(cyc)=1
      If ItemRange(cyc,v,20) And ItemCollide(cyc,v,pX#(cyc),pZ#(cyc),2) Then IgniteItem(v) : pSting(cyc)=0 
	 EndIf
    Next
   EndIf
   For v=1 To no_weaps
    range#=5+weapSize#(weapType(v))
    If pLimbY#(cyc,21)=>weapY#(v)-5 And pLimbY#(cyc,21)=<weapY#(v)+10 And weapBurning(v)=0 And weapFlammable(weapType(v)) And weapCarrier(v)=0 And pSting(cyc)=1
     If ReachedCord(pLimbX#(cyc,21),pLimbZ#(cyc,21),weapX#(v),weapZ#(v),range#) Then IgniteWeapon(v) : pSting(cyc)=0 
	EndIf
   Next
  EndIf
  If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) And pSting(cyc)=1 Then ProduceSound(p(cyc),sExpire,22050,0)
  If pAnimTim(cyc)>Int(40/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,0)
 EndIf
 
 ;belt fondling/placing
 If pAnim(cyc)=216 Or pAnim(cyc)=217 
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=1.0 : transition=5
   If weapWear(pHolding(cyc))=0 Then pAnimSpeed#(cyc)=1.5
   If pOldAnim(cyc)=48 Or pOldAnim(cyc)=49 Then transition=transition+5
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,pAnim(cyc)),transition
  EndIf
  If pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) And pHolding(cyc)>0 And weapFile$(weapType(pHolding(cyc)))="Belt" And weapWear(pHolding(cyc))=0
   weapWear(pHolding(cyc))=pAnim(cyc)-215
  EndIf
  If pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) And LegalMan(cyc) Then Pop(cyc,Rnd(1,9),0) 
  TauntEffects(cyc,pFoc(cyc),Int(45/pAnimSpeed#(cyc)))
  If pAnimTim(cyc)=>Int(160/pAnimSpeed#(cyc)) Or (pAnimTim(cyc)>Int(45/pAnimSpeed#(cyc)) And TauntAbort(cyc))
   If pOldAnim(cyc)=48 Or pOldAnim(cyc)=49 Then anim=48 Else anim=0
   ChangeAnim(cyc,anim)
  EndIf
 EndIf
 ;belt removal
 If pAnim(cyc)=218 Or pAnim(cyc)=219 
  If pAnimTim(cyc)=0
   pAnimSpeed#(cyc)=2.0
   Animate p(cyc),3,-pAnimSpeed#(cyc),pSeq(cyc,pAnim(cyc)),10/pAnimSpeed#(cyc)
  EndIf
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) And pHolding(cyc)>0 Then weapWear(pHolding(cyc))=0
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then Animate p(cyc),3,1.0,pSeq(cyc,0),20/pAnimSpeed#(cyc)
  If pAnimTim(cyc)>Int(50/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,0)
 EndIf
 ;WEAPON ATTACKS
 ;loaded punch
 If pAnim(cyc)=220
  newAnim=-1
  If pAnimTim(cyc)=0
   factor#=50+PercentOf#(50,pSkill(cyc))
   pAnimSpeed#(cyc)=PercentOf#(3.5,factor#)
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,220),15/pAnimSpeed#(cyc)
   factor#=50+PercentOf#(50,pAgility(cyc))
   pTravel#(cyc)=PercentOf#(0.5,factor#)
   pHurtDelay(cyc)=0 : pDelayAnim(cyc)=0
   pStagger#(cyc)=0 : pSting(cyc)=1
  EndIf
  AttackMovement(cyc,60)
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0)
  If pAnimTim(cyc)=>Int(24/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(45/pAnimSpeed#(cyc)) And pSting(cyc)=1
   FindAttackImpacts(cyc,24,29,45,15+weapSize#(weapType(pHolding(cyc))),28,100,1,1)
  EndIf
  If pSting(cyc)=0 And pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) Then RiskInjury(cyc,Rnd(1,2),100)
  If pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) And pSting(cyc)=1 Then pHeat(cyc)=pHeat(cyc)-Rnd(0,1)
  If pAnimTim(cyc)>Int(45/pAnimSpeed#(cyc)) And pSting(cyc)=0 Then DropWeapon(cyc,500)
  If pAnimTim(cyc)>Int(45/pAnimSpeed#(cyc)) And pSting(cyc)=0 And pSpecial(cyc)=0
   randy=Rnd(0,25)
   If randy=0 And pInjured(cyc,1)>0 Then newAnim=111
   If randy=1 And pInjured(cyc,2)>0 Then newAnim=112
   If pReaction(cyc)=>111 And pReaction(cyc)=<115 Then newAnim=pReaction(cyc) : pImmunity(cyc)=20 : pReaction(cyc)=0
  EndIf
  If pHurtDelay(cyc)>0
   If pAnimTim(cyc)>Int(45/pAnimSpeed#(cyc)) Then newAnim=pDelayAnim(cyc) : pHurtDelay(cyc)=0
  Else
   If pAnimTim(cyc)=>Int(75/pAnimSpeed#(cyc)) Then newAnim=0 
  EndIf 
  If newAnim=>0 Then ChangeAnim(cyc,newAnim)
  pHealth(cyc)=pHealth(cyc)-1
 EndIf
 ;loaded double axe-handle
 If pAnim(cyc)=221
  newAnim=-1
  If pAnimTim(cyc)=0
   factor#=50+PercentOf#(50,pSkill(cyc))
   pAnimSpeed#(cyc)=PercentOf#(3.0,factor#)
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,221),30/pAnimSpeed#(cyc)
   factor#=50+PercentOf#(50,pAgility(cyc))
   pTravel#(cyc)=PercentOf#(0.3,factor#)
   pHurtDelay(cyc)=0 : pDelayAnim(cyc)=0
   pStagger#(cyc)=0 : pSting(cyc)=1
  EndIf
  AttackMovement(cyc,95)
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,1)
  If pAnimTim(cyc)=>Int(42/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(65/pAnimSpeed#(cyc)) And pSting(cyc)=1
   FindAttackImpacts(cyc,42,48,65,12+weapSize#(weapType(pHolding(cyc))),26,200,1,3)
  EndIf
  If pSting(cyc)=0 And pAnimTim(cyc)=Int(65/pAnimSpeed#(cyc)) Then RiskInjury(cyc,Rnd(1,2),100)
  If pAnimTim(cyc)=Int(65/pAnimSpeed#(cyc)) And pSting(cyc)=1 Then pHeat(cyc)=pHeat(cyc)-1
  If pAnimTim(cyc)>Int(65/pAnimSpeed#(cyc)) And pSting(cyc)=0 Then DropWeapon(cyc,500)
  If pAnimTim(cyc)>Int(65/pAnimSpeed#(cyc)) And pSting(cyc)=0 And pSpecial(cyc)=0
   randy=Rnd(0,25)
   If randy=0 And pInjured(cyc,1)>0 Then newAnim=111
   If randy=1 And pInjured(cyc,2)>0 Then newAnim=112
   If pReaction(cyc)=>111 And pReaction(cyc)=<115 Then newAnim=pReaction(cyc) : pImmunity(cyc)=20 : pReaction(cyc)=0
  EndIf
  If pHurtDelay(cyc)>0
   If pAnimTim(cyc)>Int(65/pAnimSpeed#(cyc)) Then newAnim=pDelayAnim(cyc) : pHurtDelay(cyc)=0
  Else
   If pAnimTim(cyc)=>Int(120/pAnimSpeed#(cyc)) Then newAnim=0 
  EndIf 
  If newAnim=>0 Then ChangeAnim(cyc,newAnim)
  pHealth(cyc)=pHealth(cyc)-1
 EndIf 
 ;weak swing
 If pAnim(cyc)=222
  newAnim=-1
  If pAnimTim(cyc)=0
   factor#=50+PercentOf#(50,pSkill(cyc))
   pAnimSpeed#(cyc)=PercentOf#(3.0,factor#)
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,222),15/pAnimSpeed#(cyc)
   factor#=50+PercentOf#(50,pAgility(cyc))
   pTravel#(cyc)=PercentOf#(0.3,factor#)
   pHurtDelay(cyc)=0 : pDelayAnim(cyc)=0
   pStagger#(cyc)=0 : pSting(cyc)=1
  EndIf
  AttackMovement(cyc,35)
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0)
  If pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(40/pAnimSpeed#(cyc)) And pSting(cyc)=1
   FindAttackImpacts(cyc,20,32,40,10+PercentOf#(weapSize#(weapType(pHolding(cyc))),150),28,100,1,1)
  EndIf
  If pSting(cyc)=0 And pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then RiskInjury(cyc,Rnd(1,2),100)
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) And pSting(cyc)=1 Then pHeat(cyc)=pHeat(cyc)-Rnd(0,1)
  If pAnimTim(cyc)>Int(40/pAnimSpeed#(cyc)) And pSting(cyc)=0 Then DropWeapon(cyc,500)
  If pAnimTim(cyc)>Int(40/pAnimSpeed#(cyc)) And pSting(cyc)=0 And pSpecial(cyc)=0
   randy=Rnd(0,25)
   If randy=0 And pInjured(cyc,1)>0 Then newAnim=111
   If randy=1 And pInjured(cyc,2)>0 Then newAnim=112
   If pReaction(cyc)=>111 And pReaction(cyc)=<115 Then newAnim=pReaction(cyc) : pImmunity(cyc)=20 : pReaction(cyc)=0
  EndIf
  If pHurtDelay(cyc)>0
   If pAnimTim(cyc)>Int(40/pAnimSpeed#(cyc)) Then newAnim=pDelayAnim(cyc) : pHurtDelay(cyc)=0
  Else
   If pAnimTim(cyc)=>Int(55/pAnimSpeed#(cyc)) Then newAnim=0 
  EndIf 
  If newAnim=>0 Then ChangeAnim(cyc,newAnim)
  pHealth(cyc)=pHealth(cyc)-1
 EndIf
 ;strong swing
 If pAnim(cyc)=223
  newAnim=-1
  If pAnimTim(cyc)=0
   factor#=50+PercentOf#(50,pSkill(cyc))
   pAnimSpeed#(cyc)=PercentOf#(3.5,factor#)
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,223),30/pAnimSpeed#(cyc)
   factor#=50+PercentOf#(50,pAgility(cyc))
   pTravel#(cyc)=PercentOf#(0.3,factor#)
   pHurtDelay(cyc)=0 : pDelayAnim(cyc)=0
   pStagger#(cyc)=0 : pSting(cyc)=1
  EndIf
  AttackMovement(cyc,95)
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,1)
  If pAnimTim(cyc)=>Int(40/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(70/pAnimSpeed#(cyc)) And pSting(cyc)=1
   FindAttackImpacts(cyc,40,52,70,10+PercentOf#(weapSize#(weapType(pHolding(cyc))),150),28,200,1,3)
  EndIf
  If pSting(cyc)=0 And pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then RiskInjury(cyc,Rnd(1,2),100)
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) And pSting(cyc)=1 Then pHeat(cyc)=pHeat(cyc)-1
  If pAnimTim(cyc)>Int(70/pAnimSpeed#(cyc)) And pSting(cyc)=0 Then DropWeapon(cyc,500)
  If pAnimTim(cyc)>Int(70/pAnimSpeed#(cyc)) And pSting(cyc)=0 And pSpecial(cyc)=0
   randy=Rnd(0,25)
   If randy=0 And pInjured(cyc,1)>0 Then newAnim=111
   If randy=1 And pInjured(cyc,2)>0 Then newAnim=112 
   If pReaction(cyc)=>111 And pReaction(cyc)=<115 Then newAnim=pReaction(cyc) : pImmunity(cyc)=20 : pReaction(cyc)=0
  EndIf
  If pHurtDelay(cyc)>0
   If pAnimTim(cyc)>Int(70/pAnimSpeed#(cyc)) Then newAnim=pDelayAnim(cyc) : pHurtDelay(cyc)=0
  Else
   If pAnimTim(cyc)=>Int(120/pAnimSpeed#(cyc)) Then newAnim=0 
  EndIf 
  If newAnim=>0 Then ChangeAnim(cyc,newAnim) 
  pHealth(cyc)=pHealth(cyc)-1
 EndIf 
 ;weak stab
 If pAnim(cyc)=224
  newAnim=-1
  If pAnimTim(cyc)=0
   factor#=50+PercentOf#(50,pSkill(cyc))
   pAnimSpeed#(cyc)=PercentOf#(3.0,factor#)
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,224),15/pAnimSpeed#(cyc)
   factor#=50+PercentOf#(50,pAgility(cyc))
   pTravel#(cyc)=PercentOf#(0.3,factor#)
   pHurtDelay(cyc)=0 : pDelayAnim(cyc)=0
   pStagger#(cyc)=0 : pSting(cyc)=1
  EndIf
  AttackMovement(cyc,35)
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0)
  If pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(40/pAnimSpeed#(cyc)) And pSting(cyc)=1
   FindAttackImpacts(cyc,20,30,40,14+weapSize#(weapType(pHolding(cyc))),28,100,1,1)
  EndIf
  If pSting(cyc)=0 And pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then RiskInjury(cyc,Rnd(1,2),100)
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) And pSting(cyc)=1 Then pHeat(cyc)=pHeat(cyc)-Rnd(0,1)
  If pAnimTim(cyc)>Int(40/pAnimSpeed#(cyc)) And pSting(cyc)=0 Then DropWeapon(cyc,500)
  If pAnimTim(cyc)>Int(40/pAnimSpeed#(cyc)) And pSting(cyc)=0 And pSpecial(cyc)=0
   randy=Rnd(0,25)
   If randy=0 And pInjured(cyc,1)>0 Then newAnim=111
   If randy=1 And pInjured(cyc,2)>0 Then newAnim=112
   If pReaction(cyc)=>111 And pReaction(cyc)=<115 Then newAnim=pReaction(cyc) : pImmunity(cyc)=20 : pReaction(cyc)=0
  EndIf
  If pHurtDelay(cyc)>0
   If pAnimTim(cyc)>Int(40/pAnimSpeed#(cyc)) Then newAnim=pDelayAnim(cyc) : pHurtDelay(cyc)=0
  Else
   If pAnimTim(cyc)=>Int(55/pAnimSpeed#(cyc)) Then newAnim=0 
  EndIf 
  If newAnim=>0 Then ChangeAnim(cyc,newAnim)
  pHealth(cyc)=pHealth(cyc)-1
 EndIf
 ;strong stab
 If pAnim(cyc)=225
  newAnim=-1
  If pAnimTim(cyc)=0
   factor#=50+PercentOf#(50,pSkill(cyc))
   pAnimSpeed#(cyc)=PercentOf#(3.5,factor#)
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,225),30/pAnimSpeed#(cyc)
   factor#=50+PercentOf#(50,pAgility(cyc))
   pTravel#(cyc)=PercentOf#(0.3,factor#)
   pHurtDelay(cyc)=0 : pDelayAnim(cyc)=0
   pStagger#(cyc)=0 : pSting(cyc)=1
  EndIf
  AttackMovement(cyc,95)
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,1)
  If pAnimTim(cyc)=>Int(40/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(65/pAnimSpeed#(cyc)) And pSting(cyc)=1
   FindAttackImpacts(cyc,40,50,65,14+weapSize#(weapType(pHolding(cyc))),28,200,1,3)
  EndIf
  If pSting(cyc)=0 And pAnimTim(cyc)=Int(65/pAnimSpeed#(cyc)) Then RiskInjury(cyc,Rnd(1,2),100)
  If pAnimTim(cyc)=Int(65/pAnimSpeed#(cyc)) And pSting(cyc)=1 Then pHeat(cyc)=pHeat(cyc)-1
  If pAnimTim(cyc)>Int(65/pAnimSpeed#(cyc)) And pSting(cyc)=0 Then DropWeapon(cyc,500)
  If pAnimTim(cyc)>Int(65/pAnimSpeed#(cyc)) And pSting(cyc)=0 And pSpecial(cyc)=0
   randy=Rnd(0,25)
   If randy=0 And pInjured(cyc,1)>0 Then newAnim=111
   If randy=1 And pInjured(cyc,2)>0 Then newAnim=112
   If pReaction(cyc)=>111 And pReaction(cyc)=<115 Then newAnim=pReaction(cyc) : pImmunity(cyc)=20 : pReaction(cyc)=0
  EndIf
  If pHurtDelay(cyc)>0
   If pAnimTim(cyc)>Int(65/pAnimSpeed#(cyc)) Then newAnim=pDelayAnim(cyc) : pHurtDelay(cyc)=0
  Else
   If pAnimTim(cyc)=>Int(120/pAnimSpeed#(cyc)) Then newAnim=0 
  EndIf 
  If newAnim=>0 Then ChangeAnim(cyc,newAnim)
  pHealth(cyc)=pHealth(cyc)-1
 EndIf 
 ;high chair swing
 If pAnim(cyc)=226
  newAnim=-1
  If pAnimTim(cyc)=0
   factor#=50+PercentOf#(50,pSkill(cyc))
   pAnimSpeed#(cyc)=PercentOf#(3.5,factor#)
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,226),30/pAnimSpeed#(cyc)
   factor#=50+PercentOf#(50,pAgility(cyc))
   pTravel#(cyc)=PercentOf#(0.3,factor#)
   If pOldAnim(cyc)=63 Then pTravel#(cyc)=pTravel#(cyc)+(pTravel#(cyc)/2)
   pHurtDelay(cyc)=0 : pDelayAnim(cyc)=0
   pStagger#(cyc)=0 : pSting(cyc)=1
  EndIf
  AttackMovement(cyc,75)
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,1)
  If pAnimTim(cyc)=>Int(40/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(70/pAnimSpeed#(cyc)) And pSting(cyc)=1
   FindAttackImpacts(cyc,40,50,70,25,28,200,1,3)
  EndIf
  If pSting(cyc)=0 And pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then RiskInjury(cyc,Rnd(1,2),100)
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) And pSting(cyc)=1 Then pHeat(cyc)=pHeat(cyc)-1
  If pAnimTim(cyc)>Int(70/pAnimSpeed#(cyc)) And pSting(cyc)=0 Then DropWeapon(cyc,500)
  If pAnimTim(cyc)>Int(70/pAnimSpeed#(cyc)) And pSting(cyc)=0 And pSpecial(cyc)=0
   randy=Rnd(0,25)
   If randy=0 And pInjured(cyc,1)>0 Then newAnim=111
   If randy=1 And pInjured(cyc,2)>0 Then newAnim=112
   If pReaction(cyc)=>111 And pReaction(cyc)=<115 Then newAnim=pReaction(cyc) : pImmunity(cyc)=20 : pReaction(cyc)=0
  EndIf
  If pHurtDelay(cyc)>0
   If pAnimTim(cyc)>Int(70/pAnimSpeed#(cyc)) Then newAnim=pDelayAnim(cyc) : pHurtDelay(cyc)=0
  Else
   If pAnimTim(cyc)=>Int(105/pAnimSpeed#(cyc)) Then newAnim=0 
  EndIf 
  If newAnim=>0 Then ChangeAnim(cyc,newAnim)
  pHealth(cyc)=pHealth(cyc)-1
 EndIf 
 ;ground weapon swing
 If pAnim(cyc)=>227 And pAnim(cyc)=<229
  newAnim=-1
  If pAnimTim(cyc)=0
   factor#=50+PercentOf#(50,pSkill(cyc))
   If pAnim(cyc)=229 Then pAnimSpeed#(cyc)=PercentOf#(3.0,factor#) Else pAnimSpeed#(cyc)=PercentOf#(3.5,factor#)
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,pAnim(cyc)),30/pAnimSpeed#(cyc)
   factor#=50+PercentOf#(50,pAgility(cyc))
   pTravel#(cyc)=PercentOf#(0.3,factor#)
   If weapHold(weapType(pHolding(cyc)))=0 Or weapHold(weapType(pHolding(cyc)))=2 Then pTravel#(cyc)=pTravel#(cyc)+(pTravel#(cyc)/2)
   pHurtDelay(cyc)=0 : pDelayAnim(cyc)=0
   pStagger#(cyc)=0 : pSting(cyc)=1
  EndIf
  AttackMovement(cyc,75)
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,1)
  If pAnimTim(cyc)=>Int(40/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(50/pAnimSpeed#(cyc))
   range#=5+weapSize#(weapType(pHolding(cyc)))
   range#=range#+PercentOf#(PercentOf#(range#,25),GetPercent#(charHeight(pChar(cyc)),24))
   ProjectDummy(cyc,0,0,range#)
   impactX#=EntityX(dummy) : impactZ#=EntityZ(dummy)
   For v=1 To no_plays
    impact=0
    If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) And AttackViable(v)=2 Then impact=3
    If pAnimTim(cyc)=<Int(45/pAnimSpeed#(cyc)) And AttackViable(v)=3 Then impact=2
    If pAnimTim(cyc)>Int(45/pAnimSpeed#(cyc)) And AttackViable(v)=4 And weapHold(weapType(pHolding(cyc)))=<2 Then impact=1
    If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) And AttackViable(v)=4 Then impact=1
    If impact>0 And cyc<>v And InLine(cyc,p(v),45) And pY#(cyc)>pY#(v)-5 And pY#(cyc)<pY#(v)+5 And GettingUp(v)=0 And pSting(cyc)=1
     ScanBody(v)
     For scan=0 To 6
      If (ReachedCord(impactX#,impactZ#,pScanX#(v,scan),pScanZ#(v,scan),8) Or ReachedCord(pX#(cyc),pZ#(cyc),pScanX#(v,scan),pScanZ#(v,scan),range#)) And pSting(cyc)=1
       GroundImpactChecks(v)
       Pop(cyc,Rnd(2,9),1)
       ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),1)
       ProduceSound(p(cyc),sBlock(Rnd(5,7)),22050,0.5)
       If ReachedCord(impactX#,impactZ#,pScanX#(v,scan),pScanZ#(v,scan),8)=0 
        ProjectDummy(cyc,0,0,range#/2)
        impactX#=EntityX(dummy) : impactZ#=EntityZ(dummy)
       EndIf
       impactY#=pY#(cyc)+2
       If impact=2 Then impactY#=pY#(cyc)+5
       If impact=3 Then impactY#=pY#(cyc)+10
       ImpactSpurt(impactX#,impactY#,impactZ#,1,1)
       BloodSpurt(impactX#,impactY#,impactZ#,-1,(FindBlood(v,impactX#,impactY#,impactZ#)-1)*2,1)
       ScarArea(v,impactX#,impactY#,impactZ#,25)
       GroundReaction(v) : pHurtTim(v)=5
       If weapHold(weapType(pHolding(cyc)))=3 Then ResetItemStings(v) : FindSmashes(v,pY#(v),1)
       pain=PercentOf#(100,pStrength(cyc))
       pHealth(v)=pHealth(v)-pain : pDT(v)=pDT(v)-(pDT(v)/5)
       pHeat(cyc)=pHeat(cyc)+1 : pHeat(v)=pHeat(v)-1
       WeaponImpact(cyc,v,1)
       pFoc(v)=cyc : pSting(cyc)=0
      EndIf
     Next
    EndIf
   Next
  EndIf
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc))
   ProduceSound(p(cyc),sThud,22050,0.5)
   pStepTim#(cyc)=99
   RiskInjury(cyc,Rnd(1,2),100)
   If pSting(cyc)=1 
    ProduceSound(p(cyc),weapSound(weapType(pHolding(cyc))),22050,0)
    ;ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
    ;pHP(cyc)=0 : pHurtA#(cyc)=Rnd(0,360)
    pHeat(cyc)=pHeat(cyc)-1
   EndIf  
  EndIf
  If pAnimTim(cyc)>Int(50/pAnimSpeed#(cyc)) Then DropWeapon(cyc,100)
  If pAnimTim(cyc)>Int(50/pAnimSpeed#(cyc)) And pSpecial(cyc)=0
   randy=Rnd(0,25)
   If randy=0 And pInjured(cyc,1)>0 Then newAnim=111
   If randy=1 And pInjured(cyc,2)>0 Then newAnim=112
   If pReaction(cyc)=>111 And pReaction(cyc)=<115 Then newAnim=pReaction(cyc) : pImmunity(cyc)=20 : pReaction(cyc)=0
  EndIf
  If pAnimTim(cyc)=>Int(110/pAnimSpeed#(cyc)) Then newAnim=0  
  If newAnim=>0 Then ChangeAnim(cyc,newAnim)
  pHealth(cyc)=pHealth(cyc)-1
 EndIf 
End Function

;-----------------------------------------------------------------
;//////////////////////// LOAD ITEMS /////////////////////////////
;-----------------------------------------------------------------
Function LoadItems()
 For cyc=1 To no_items
  ;selection
  Repeat
   iType(cyc)=Rnd(1,itemList) ;random by default
  Until iType(cyc)<>3
  If itemSelection>1 Then iType(cyc)=itemSelection-1 ;specific bias
  If (cyc=<no_items/2 Or (itemSelection=1 And itemLayout=1)) And cyc=<19 ;standard override
   Repeat
    iType(cyc)=Rnd(1,itemList)
    randy=Rnd(0,2)
    If randy=0 And cyc=>9 And cyc=<10 Then iType(cyc)=8
    If randy=0 And cyc=>16 And cyc=<17 Then iType(cyc)=10
   Until iType(cyc)<>3 And iType(cyc)<>7 And iType(cyc)<>14
   If cyc=1 Or cyc=2 
    iType(cyc)=Rnd(0,3) ;announcer's desks
    If iType(cyc)<1 Then iType(cyc)=3
   EndIf
   If cyc=14 Then iType(cyc)=1 ;study table
   If (cyc=>3 And cyc=<4) Or cyc=15 Then iType(cyc)=9 ;chairs
   If cyc=>5 And cyc=<6 Then iType(cyc)=7 ;monitors
   If cyc=>7 And cyc=<8 Then iType(cyc)=15 ;steps
  EndIf
  ;generate
  i(cyc)=LoadAnimMesh(iFile$(iType(cyc)))
  ExtractAnimSeq(i(cyc),0,60,0) ;1. break animation
  ExtractAnimSeq(i(cyc),70,100,0) ;2. broken animation
  ScaleEntity i(cyc),0.4,0.4,0.4 
  ;texture variations
  If iType(cyc)=>1 And iType(cyc)=<2
   For count=1 To 3
    If iTexture(iType(cyc))>0 Then EntityTexture FindChild(i(cyc),"Table0"+count),iTexture(iType(cyc))
    If iShine#(iType(cyc))>0 Then EntityShininess FindChild(i(cyc),"Table0"+count),iShine#(iType(cyc))
   Next
  Else
   For count=1 To CountChildren(i(cyc))
    If iTexture(iType(cyc))>0 Then EntityTexture GetChild(i(cyc),count),iTexture(iType(cyc))
    EntityShininess GetChild(i(cyc),count),iShine#(iType(cyc))
   Next
  EndIf
  If iType(cyc)=14 Then EntityAlpha FindChild(i(cyc),"Glass"),0.6
  ;location
  its=0
  Repeat 
   conflict=0 : its=its+1
   iA#(cyc)=Rnd(0,360)
   area=Rnd(1,4) : favour=Rnd(0,2)
   If area=1 And itemLayout<>0 And itemLayout<>4 Then favour=0
   If favour=0 
    If itemLayout=1 Or itemLayout=5 Then area=Rnd(2,4)
    If itemLayout=2 Then area=Rnd(3,4)
    If itemLayout=3 Then area=2
    If itemLayout=4 Then area=1
   EndIf
   If itemLayout=5 And iType(cyc)=itemSelection-1 Then area=1
   If area=1 Then iX#(cyc)=Rnd(-60,60) : iZ#(cyc)=Rnd(-60,60) ;inside ring
   If area=2
    Repeat
     iX#(cyc)=Rnd(-130,130) : iZ#(cyc)=Rnd(-130,130) ;surrounding ring
    Until InsideRing(iX#(cyc),iZ#(cyc),10)=0 
   EndIf
   If area=3 Then iX#(cyc)=Rnd(-165,210) : iZ#(cyc)=Rnd(625,860) ;locker room
   If area=4 Then iX#(cyc)=Rnd(-115,125) : iZ#(cyc)=Rnd(-765,-625) ;lounge
   If iSizeX#(iType(cyc))>iSizeZ#(iType(cyc))+(iSizeZ#(iType(cyc))/2)
    If iZ#(cyc)=>-100 And iZ#(cyc)=<100
     If (iX#(cyc)=>-130 And iX#(cyc)=<-100) Or (iX#(cyc)=>100 And iX#(cyc)=<130)
      If SatisfiedAngle(iA#(cyc),0,45) Or SatisfiedAngle(iA#(cyc),180,45) Then iA#(cyc)=iA#(cyc)+90
     EndIf
    EndIf 
    If iX#(cyc)=>-100 And iX#(cyc)=<100
     If (iZ#(cyc)=>-130 And iZ#(cyc)=<-100) Or (iZ#(cyc)=>100 And iZ#(cyc)=<130)
      If SatisfiedAngle(iA#(cyc),90,45) Or SatisfiedAngle(iA#(cyc),270,45) Then iA#(cyc)=iA#(cyc)+90
     EndIf
    EndIf
   EndIf
   For v=1 To no_items
    If v<cyc And ReachedCord(iX#(cyc),iZ#(cyc),iX#(v),iZ#(v),20) Then conflict=1 ;avoid conflicts
   Next
  Until conflict=0 Or its>100
  ;bind steps to ring
  If iType(cyc)=15
   stepsNE=0 : stepsSE=0 : stepsSW=0 : stepsNW=0
   For v=1 To no_items
    If v<>cyc
     If ReachedCord(iX#(cyc),iZ#(cyc),87,87,5)=0 Then stepsNE=1
     If ReachedCord(iX#(cyc),iZ#(cyc),87,-87,5)=0 Then stepsSE=1
     If ReachedCord(iX#(cyc),iZ#(cyc),-87,-87,5)=0 Then stepsSW=1
     If ReachedCord(iX#(cyc),iZ#(cyc),-87,87,5)=0 Then stepsNW=1
    EndIf
   Next
   If stepsNE=0 Then iX#(cyc)=87 : iZ#(cyc)=87 : iA#(cyc)=135
   If stepsSE=0 Then iX#(cyc)=87 : iZ#(cyc)=-87 : iA#(cyc)=45
   If stepsSW=0 Then iX#(cyc)=-87 : iZ#(cyc)=-87 : iA#(cyc)=315
   If stepsNW=0 Then iX#(cyc)=-87 : iZ#(cyc)=87 : iA#(cyc)=225
  EndIf
  ;standard override
  If cyc=<no_items/2 Or itemLayout=1
   If cyc=1 Then iX#(cyc)=(-140)+(iSizeX#(iType(cyc))*2) : iZ#(cyc)=135-(iSizeZ#(iType(cyc))*2) : iA#(cyc)=180 ;upper left announcer's desk
   If cyc=2 Then iX#(cyc)=140-(iSizeX#(iType(cyc))*2) : iZ#(cyc)=(-135)+(iSizeZ#(iType(cyc))*2) : iA#(cyc)=0 ;lower right announcer's desk
   If cyc=3 And iType(cyc)=9 And iType(1)=<3 Then iX#(cyc)=-120 : iZ#(cyc)=133 : iA#(cyc)=Rnd(180,200) ;upper left announcer's desk chair
   If cyc=4 And iType(cyc)=9 And iType(2)=<3 Then iX#(cyc)=120 : iZ#(cyc)=-133 : iA#(cyc)=Rnd(0,20) ;lower right announcer's desk chair
   If cyc=5 And iType(cyc)=7 Then iX#(cyc)=-134 : iZ#(cyc)=112 : iA#(cyc)=215 ;upper left announcer's monitor
   If cyc=6 And iType(cyc)=7 Then iX#(cyc)=134 : iZ#(cyc)=-112 : iA#(cyc)=45 ;lower right announcer's monitor
   If cyc=7 And iType(cyc)=15 Then iX#(cyc)=87 : iZ#(cyc)=87 : iA#(cyc)=135 ;upper right ring steps
   If cyc=8 And iType(cyc)=15 Then iX#(cyc)=-87 : iZ#(cyc)=-87 : iA#(cyc)=315 ;lower left ring steps
   If cyc=9 Then iX#(cyc)=(-240)+(iSizeZ#(iType(cyc))*2) : iZ#(cyc)=885-(iSizeX#(iType(cyc))*2) : iA#(cyc)=270 ;north west of locker room
   If cyc=10 Then iX#(cyc)=145-(iSizeX#(iType(cyc))*2) : iZ#(cyc)=(-610)-(iSizeZ#(iType(cyc))*2) : iA#(cyc)=180 ;north east of lounge 
   If cyc=11 Then iX#(cyc)=240-(iSizeZ#(iType(cyc))*2) : iZ#(cyc)=790 : iA#(cyc)=90 ;east of locker room
   If cyc=12 Then iX#(cyc)=(-145)+(iSizeX#(iType(cyc))*2) : iZ#(cyc)=(-610)-(iSizeZ#(iType(cyc))*2) : iA#(cyc)=180 ;north west of lounge  
   If cyc=13 Then iX#(cyc)=0 : iZ#(cyc)=885-(iSizeZ#(iType(cyc))*2) : iA#(cyc)=180 ;north of locker room 
   If cyc=14 Then iX#(cyc)=105-(iSizeZ#(iType(cyc))*2) : iZ#(cyc)=(-765)+(iSizeX#(iType(cyc))*2) : iA#(cyc)=90 ;bookshelf enclave
   If cyc=15 And iType(cyc)=9 And iType(12)=<3 Then iX#(cyc)=iX#(12)-20 : iZ#(cyc)=iZ#(12) : iA#(cyc)=Rnd(240,300) ;bookshelf chair 
   If cyc=16 Then iX#(cyc)=140-(iSizeZ#(iType(cyc))*2) : iZ#(cyc)=140-(iSizeX#(iType(cyc))*2) : iA#(cyc)=90 ;upper right railing
   If cyc=17 Then iX#(cyc)=(-140)+(iSizeZ#(iType(cyc))*2) : iZ#(cyc)=(-140)+(iSizeX#(iType(cyc))*2) : iA#(cyc)=270 ;lower left railing
   If cyc=18 Then iX#(cyc)=Rnd(80,150) : iZ#(cyc)=615+(iSizeZ#(iType(cyc))*2) : iA#(cyc)=0 ;south east of locker room 
   If cyc=19 Then iX#(cyc)=Rnd(-145,-110) : iZ#(cyc)=615+(iSizeZ#(iType(cyc))*2) : iA#(cyc)=0 ;south west of locker room
   ;If cyc=20 Then iX#(cyc)=(-110)+(iSizeZ#(iType(cyc))*2) : iZ#(cyc)=(-760)+(iSizeX#(iType(cyc))*2) : iA#(cyc)=270 ;sofa enclave
   ;If iType(cyc)<>7 And iType(cyc)<>9
    ;If iSizeX#(iType(cyc))=<iSizeZ#(iType(cyc))+(iSizeZ#(iType(cyc))/2) Then iA#(cyc)=Rnd(0,360)
   ;EndIf
  EndIf
  iY#(cyc)=wGround#
  If InsideRing(iX#(cyc),iZ#(cyc),-20) Then iY#(cyc)=wStage#
  ;reset data
  iOldX#(cyc)=999 : iOldZ#(cyc)=999
  iState(cyc)=0 : iOldState(cyc)=-1
  iScar(cyc)=0 : iBurning(cyc)=0
  iScreen(cyc)=0 : iOldScreen(cyc)=-1
  iCarrier(cyc)=0 
 Next
End Function

;-----------------------------------------------------------------
;/////////////////////// ITEM CYCLE //////////////////////////////
;-----------------------------------------------------------------
Function ItemCycle()
 For cyc=1 To no_items
  ;determine ground
  iOldY#(cyc)=iY#(cyc)
  iGround#(cyc)=wGround#
  If arenaMatting>0 And InsideRing(iX#(cyc),iZ#(cyc),20)
   iGround#(cyc)=wGround#+1.1
   If iY#(cyc)<iGround#(cyc) And iCarrier(cyc)=0
    iY#(cyc)=iY#(cyc)+0.2
    If iY#(cyc)>iGround#(cyc) Then iY#(cyc)=iGround#(cyc)
   EndIf
  EndIf
  If InsideRing(iX#(cyc),iZ#(cyc),-5) And iOldY#(cyc)=>wStage# Then iGround#(cyc)=wStage#
  ;trigger fall to floor
  If InsideRing(iX#(cyc),iZ#(cyc),-5)=0 And iY#(cyc)>iGround#(cyc) And iCarrier(cyc)=0 And iAnim(cyc)=0
   iAnim(cyc)=10 : iAnimTim(cyc)=0
   iGravity#(cyc)=0
  EndIf
  ;falling/dropping process
  If iAnim(cyc)=10
   iGravity#(cyc)=iGravity#(cyc)-0.25
   iY#(cyc)=iY#(cyc)+iGravity#(cyc) 
   iA#(cyc)=iA#(cyc)+0.5
   PositionEntity dummy,iX#(cyc),iY#(cyc),iZ#(cyc)
   RotateEntity dummy,0,iFallA#(cyc),0
   MoveEntity dummy,0,0,0.2
   iX#(cyc)=EntityX(dummy) : iZ#(cyc)=EntityZ(dummy)
   If iY#(cyc)<iGround#(cyc)
    ProduceSound(i(cyc),sItem,22050,0)
    ProduceSound(i(cyc),sThud,22050,0.5)
    If iType(cyc)=15 Then ProduceSound(i(cyc),sImpactMetal,22050,0)
    iAnim(cyc)=11 : iAnimTim(cyc)=0
    If iState(cyc)=0 Then iAnimTim(cyc)=10
    iY#(cyc)=iGround#(cyc)
   EndIf
  EndIf
  ;bounce process
  If iAnim(cyc)=11
   If iAnimTim(cyc)=<4 Then tY#=iGround#(cyc)+2
   If iAnimTim(cyc)=>5 And iAnimTim(cyc)=<9 Then tY#=iGround#(cyc)
   If iAnimTim(cyc)=>10 And iAnimTim(cyc)=<13 Then tY#=iGround#(cyc)+1
   If iAnimTim(cyc)=>14 Then tY#=iGround#(cyc)
   If iY#(cyc)<tY# Then iY#(cyc)=iY#(cyc)+0.25
   If iY#(cyc)>tY# Then iY#(cyc)=iY#(cyc)-0.25
   If iAnimTim(cyc)=8 Then ProduceSound(i(cyc),sItem,22050,0.4)
   If iAnimTim(cyc)>18 Then iY#(cyc)=iGround#(cyc) : iAnim(cyc)=0 
  EndIf
  ;burning
  If iBurning(cyc)>0
   size#=((iSizeX#(iType(cyc))+iSizeZ#(iType(cyc)))/2)+1
   height#=iY#(cyc)
   If iType(cyc)=>4 And iType(cyc)=<6 Then height#=iY#(cyc)+(iElevate#(iType(cyc))/2)
   randy=Rnd(1,3)
   If randy=1 Or optFX=>2 Then CreateParticle(iX#(cyc)+Rnd#(-(size#/2),size#/2),height#,iZ#(cyc)+Rnd#(-(size#/2),size#/2),-1,1)
   If randy=2 Or optFX=>2 Then CreateParticle(iX#(cyc)+Rnd#(-size#,size#),height#,iZ#(cyc)+Rnd#(-size#,size#),-1,1)
   If randy=3 Or optFX=>2 Then CreateParticle(iX#(cyc)+Rnd#(-size#,size#),height#,iZ#(cyc)+Rnd#(-size#,size#),-1,1)
   randy=Rnd(0,30)
   If optFX=<1 Then randy=Rnd(0,60)
   If randy=0 Then CreateParticle(iX#(cyc)+Rnd#(-(size#/2),size#/2),height#+1.0,iZ#(cyc)+Rnd#(-(size#/2),size#/2),-1,24)
   If randy=1 Then CreateParticle(iX#(cyc)+Rnd#(-(size#/2),size#/2),height#+1.0,iZ#(cyc)+Rnd#(-(size#/2),size#/2),-1,25)
   iBurning(cyc)=iBurning(cyc)-1
   If iBurning(cyc)=<0 Or iState(cyc)=0 Then ExtinguishItem(cyc)
  EndIf
  ;screen
  If iType(cyc)=7
   If iState(cyc)=0
    randy=Rnd(-1,1250)
    If randy=<6 Then iScreen(cyc)=randy
    If randy=>7 And randy=<12 Then iScreen(cyc)=Rnd(10,12)
    If randy=13 And no_wrestlers=<10 And iOldScreen(cyc)<20
     iScreen(cyc)=20+Rnd(1,no_wrestlers) 
	 If matchEnter>0 Then iScreen(cyc)=20+matchEnter
	 If matchWinner>0 Then iScreen(cyc)=20+matchWinner 
	EndIf
	If randy=>14 And randy=<18 And arenaApron=>16 And arenaApron=<18 Then iScreen(cyc)=20
	If iScreen(cyc)<0 Then iScreen(cyc)=0
    If iScreen(cyc)<>iOldScreen(cyc)
     EntityTexture FindChild(i(cyc),"Screen"),tVideo(iScreen(cyc)),0,1
     EntityTexture FindChild(i(cyc),"Screen"),tVideoOverlay,0,2
     iOldScreen(cyc)=iScreen(cyc)
    EndIf
    randy=Rnd(0,4)
    If randy=0 And iScreen(cyc)=0 Then PositionTexture tVideo(0),1,Rnd(0.0,2.0)
   Else
    EntityTexture FindChild(i(cyc),"Screen"),tScreen,0,1
   EndIf
  EndIf
  ;glass breakage
  If iType(cyc)=14 And iState(cyc)<>iOldState(cyc)
   EntityTexture FindChild(i(cyc),"Glass"),tGlass(iState(cyc)+1)
   iOldState(cyc)=iState(cyc)
  EndIf
  ;display
  ManageItemScars(cyc)
  PositionEntity i(cyc),iX#(cyc),iY#(cyc),iZ#(cyc)
  RotateEntity i(cyc),0,iA#(cyc),0
  ScanItem(cyc,0)
  ;If iX#(cyc)<>iOldX#(cyc) Or iZ#(cyc)<>iOldZ#(cyc) Then ScanItem(cyc,0)
  ;iOldX#(cyc)=iX#(cyc) : iOldZ#(cyc)=iZ#(cyc)
  ;increment animations
  iAnimTim(cyc)=iAnimTim(cyc)+1
 Next
End Function

;-----------------------------------------------------------------
;////////////////////// LOAD WEAPONS /////////////////////////////
;-----------------------------------------------------------------
Function LoadWeapons()
 For cyc=1 To no_weaps
  ;selection
  If weapType(cyc)=0
   Repeat 
    weapType(cyc)=Rnd(1,weapList)
   Until weapStandard(weapType(cyc))=>0
   If weapSelection=1
    Repeat 
     weapType(cyc)=Rnd(1,weapList) : satisfied=1
     randy=Rnd(0,10)
     If randy=<1 Then weapType(cyc)=26
     randy=Rnd(0,1) 
     If randy=0 And weapStandard(weapType(cyc))=0 Then satisfied=0
     If weapStandard(weapType(cyc))=-1 Then satisfied=0
    Until satisfied=1
   EndIf
   If weapSelection=>2
    randy=Rnd(0,1) 
    If randy=0 Then weapType(cyc)=weapSelection-1 
   EndIf
   If weapType(cyc)=19 And game=1 And fedProduction(charFed(gamChar),9)=0 Then weapType(cyc)=Rnd(1,18) ;block pyro
  EndIf
  ;generate
  weap(cyc)=LoadAnimMesh("Items/Weapons/"+weapFile$(weapType(cyc))+".3ds")
  ScaleEntity weap(cyc),0.45,0.45,0.45
  If weapTex(weapType(cyc))>0
   For count=1 To CountChildren(weap(cyc))
    EntityTexture GetChild(weap(cyc),count),weapTex(weapType(cyc))
    If weapFile$(weapType(cyc))="Bottle" Then EntityAlpha GetChild(weap(cyc),count),0.75
   Next
  EndIf
  If weapShine#(weapType(cyc))>0
   For count=1 To CountChildren(weap(cyc))
    EntityShininess GetChild(weap(cyc),count),weapShine#(weapType(cyc))
   Next
  EndIf
  If weapName$(weapType(cyc))="Thumbtacks" Then EntityTexture FindChild(weap(cyc),"Tacks"),tTacks
  ;location
  its=0
  Repeat 
   conflict=0 : its=its+1
   area=Rnd(0,6) : favour=Rnd(0,2)
   If area=0 Then area=2
   If area=1 And weapLayout>0 And weapLayout<>4 Then favour=0
   If area=3 And weapLayout>0 And weapLayout<>3 Then favour=0 
   If area=4 And weapLayout>0 And weapLayout<>2 Then favour=0
   If favour=0 
    If weapLayout=1 Or weapLayout=5
     area=Rnd(2,6)
     If area=3 Or area=4 Then area=2
    EndIf
    If weapLayout=2 Then area=Rnd(4,6)
    If weapLayout=3 Then area=Rnd(2,3)
    If weapLayout=4 Then area=1 
   EndIf
   If weapLayout=5 And weapType(cyc)=weapSelection-1 Then area=1
   If area=1 Then weapX#(cyc)=Rnd(-80,80) : weapZ#(cyc)=Rnd(-80,80) ;inside ring
   If area=2
    Repeat
     weapX#(cyc)=Rnd(-130,130) : weapZ#(cyc)=Rnd(-130,130) ;surrounding ring
    Until InsideRing(weapX#(cyc),weapZ#(cyc),0)=0 
   EndIf
   If area=3
    Repeat
     weapX#(cyc)=Rnd(-20,20) : weapZ#(cyc)=Rnd(-385,385) ;aisle ways
    Until weapZ#(cyc)>140 Or weapZ#(cyc)<-140
   EndIf
   If area=4
    Repeat
     weapX#(cyc)=Rnd(-40,40) : weapZ#(cyc)=Rnd(-610,610) ;gorilla positions
    Until weapZ#(cyc)>400 Or weapZ#(cyc)<-400
   EndIf
   If area=5 Then weapX#(cyc)=Rnd(-240,240) : weapZ#(cyc)=Rnd(615,885) ;locker room
   If area=6 Then weapX#(cyc)=Rnd(-145,145) : weapZ#(cyc)=Rnd(-795,-615) ;lounge
   If weapLayout<>5 Or weapType(cyc)<>weapSelection-1
    If cyc=<2 Then randy=Rnd(0,1) Else randy=Rnd(0,4)
    If randy=0 And no_items>0
     v=Rnd(1,no_items)
     If cyc=<2 Then v=cyc
     If iType(v)=<9 Then weapX#(cyc)=iX#(v) : weapZ#(cyc)=iZ#(v) ;ride items
    EndIf
   EndIf
   For v=1 To no_weaps
    If v<cyc And ReachedCord(weapX#(cyc),weapZ#(cyc),weapX#(v),weapZ#(v),10) Then conflict=1 ;avoid conflicts
   Next
  Until conflict=0 Or its>100
  weapA#(cyc)=Rnd(0,360)
  weapY#(cyc)=50
  ;reward positions
  If cyc>weapCount
   If (matchChamps=2 Or (screenAgenda=11 And cupReward(cupSlot)=1)) And weapType(cyc)=21 Then weapX#(cyc)=-118 : weapZ#(cyc)=121 : camRewardFoc=cyc
   If (matchChamps=3 Or (screenAgenda=11 And cupReward(cupSlot)=2)) And weapType(cyc)=22 Then weapX#(cyc)=-118 : weapZ#(cyc)=121 : camRewardFoc=cyc
   If (matchChamps=4 Or (screenAgenda=11 And cupReward(cupSlot)=3)) And weapType(cyc)=23 Then weapX#(cyc)=(-118)+Rnd(-8,8) : weapZ#(cyc)=121+Rnd(-4,4) : camRewardFoc=cyc
   If (matchChamps=5 Or (screenAgenda=11 And cupReward(cupSlot)=0)) And weapType(cyc)=11 
    weapX#(cyc)=-118 : weapZ#(cyc)=121 : camRewardFoc=cyc
    If matchTeams>0 Then weapX#(cyc)=weapX#(cyc)+Rnd(-8,8) : weapZ#(cyc)=weapZ#(cyc)+Rnd(-4,4)
   EndIf
  EndIf
  If camRewardFoc>0
   If iType(1)=3 Then weapZ#(camRewardFoc)=weapZ#(camRewardFoc)+4
   For v=1 To weapCount
    If ReachedCord(weapX#(v),weapZ#(v),weapX#(camRewardFoc),weapZ#(camRewardFoc),10) Then weapY#(v)=wGround#
   Next
  EndIf
  ;reset data
  weapCarrier(cyc)=0 : weapWear(cyc)=0
  weapBurning(cyc)=0 : weapScar(cyc)=0
  weapPrimed(cyc)=0 : weapRelocate(cyc)=0
 Next
End Function

;PREPARE WEAPONS FOR PLAYERS
Function PrepareWeapons()
 ;reset selection
 weapCount=no_weaps
 For cyc=1 To weapLimit
  weapType(cyc)=0
 Next
 ;negate rewards
 If fed=0 Or screenAgenda=10 Or screenAgenda=12
  If matchChamps=>1 And matchChamps=<4 Then matchChamps=0
 EndIf
 ;prepare rewards
 matchReward=matchChamps
 If screenAgenda=11 And cupFoc(cupSlot)=1
  If cupReward(cupSlot)>0 Then matchReward=cupReward(cupSlot)+1 Else matchReward=5
 EndIf
 If matchReward=2 Or (screenAgenda=11 And cupReward(cupSlot)=1) Then AddWeapon(21)
 If matchReward=3 Or (screenAgenda=11 And cupReward(cupSlot)=2) Then AddWeapon(22)
 If matchReward=4 Or (screenAgenda=11 And cupReward(cupSlot)=3) Then AddWeapon(23) : AddWeapon(23) 
 If matchReward=5 Or (screenAgenda=11 And cupReward(cupSlot)=0)
  If matchTeams=<0 Then AddWeapon(11)
  If matchTeams>0
   For count=1 To no_wrestlers/2
    AddWeapon(11)
   Next
  EndIf
 EndIf
 ;find personal items
 For cyc=1 To no_plays
  ;default weapon
  pWeapon(cyc)=charWeapon(pChar(cyc))
  ;promo mic
  If matchPromo>0 And matchLocation=0 And fed<>7
   If cyc=promoActor(1) Or cyc=promoActor(2) Or cyc=promoActor(3) Then pWeapon(cyc)=5
  EndIf
  ;prop as talking point
  If (matchPromo=29 Or matchPromo=74) And charWeapon(pChar(cyc))>0 Then pWeapon(cyc)=charWeapon(pChar(cyc))
  ;production override
  If game=1 And fed=charFed(gamChar) And fedProduction(charFed(gamChar),12)=0 Then pWeapon(cyc)=0
  ;cup holder
  If pChar(cyc)=fedCupHolder(charFed(pChar(cyc))) And matchChamps<>5 And screenAgenda<>11 And pRole(cyc)<>1 Then pWeapon(cyc)=11
  ;tag championship
  If TitleHolder(pChar(cyc),3) And matchChamps<>4 And pRole(cyc)<>1 And (screenAgenda<>11 Or cupReward(cupSlot)<>3)
   pWeapon(cyc)=23
   If matchChamps=1 And matchTeams>0 And pRole(cyc)=0 And no_wrestlers=>3 And no_wrestlers=<4 Then matchReward=4
  EndIf
  ;inter championship
  If TitleHolder(pChar(cyc),2) And matchChamps<>3 And pRole(cyc)<>1 And (screenAgenda<>11 Or cupReward(cupSlot)<>2)
   pWeapon(cyc)=22
   If matchChamps=1 And matchTeams=<0 And pRole(cyc)=0 Then matchReward=3
  EndIf
  ;world championship
  If TitleHolder(pChar(cyc),1) And matchChamps<>2 And pRole(cyc)<>1 And (screenAgenda<>11 Or cupReward(cupSlot)<>1)
   pWeapon(cyc)=21
   If matchChamps=1 And matchTeams=<0 And pRole(cyc)=0 Then matchReward=2
  EndIf
  ;add to list
  If pWeapon(cyc)>0 Then AddWeapon(pWeapon(cyc))
 Next
End Function

;ADD WEAPON TO LIST
Function AddWeapon(weapon)
 no_weaps=no_weaps+1
 If no_weaps>weapLimit Then no_weaps=weapLimit
 weapType(no_weaps)=weapon
End Function

;ASSIGN WEAPONS TO PLAYERS
Function AssignWeapons()
 For cyc=1 To no_plays
  For v=1 To no_weaps
   If weapType(v)=pWeapon(cyc) And pHolding(cyc)=0 And weapCarrier(v)=0 And v<>camRewardFoc
    AttainWeapon(cyc,v)
    If weapFile$(weapType(v))="Belt"
     weapWear(v)=Rnd(1,2)
     If charBaggy(pChar(cyc),pCostume(cyc))=1 Or charBaggy(pChar(cyc),pCostume(cyc))=3 Or pRole(cyc)>0 Then weapWear(v)=1
    EndIf
   EndIf
  Next
 Next
End Function

;FIND REWARD TYPE
Function FindReward(cyc)
 reward=0
 If pRole(cyc)<>1
  If TitleHolder(pChar(cyc),1) Then reward=21
  If TitleHolder(pChar(cyc),2) Then reward=22
  If TitleHolder(pChar(cyc),3) Then reward=23
  If fedCupHolder(charFed(pChar(cyc)))=pChar(cyc) And screenAgenda<>11 Then reward=11
  If matchReward=5 And pRole(cyc)=0 And pTeam(cyc)=pTeam(matchWinner) Then reward=11
 EndIf
 Return reward
End Function

;-----------------------------------------------------------------
;////////////////////// WEAPON CYCLE /////////////////////////////
;-----------------------------------------------------------------
Function WeaponCycle()
 For cyc=1 To no_weaps
  ;flight
  If weapCarrier(cyc)=0 And weapRelocate(cyc)=0
   weapFlight#(cyc)=weapFlight#(cyc)-0.03
   If weapY#(cyc)=<weapGround#(cyc) And weapFlight#(cyc)>1.0 Then weapFlight#(cyc)=1.0
   If weapFlight#(cyc)<0 Then weapFlight#(cyc)=0
   If weapFlight#(cyc)>0
    If weapHold(weapType(cyc))<>2 Then weapA#(cyc)=weapA#(cyc)+weapFlight#(cyc)
    PositionEntity weap(cyc),weapX#(cyc),weapY#(cyc),weapZ#(cyc)
    RotateEntity weap(cyc),0,weapFlightA#(cyc),0
    MoveEntity weap(cyc),0,0,weapFlight#(cyc)
    weapX#(cyc)=EntityX(weap(cyc)) : weapZ#(cyc)=EntityZ(weap(cyc))
   EndIf
  EndIf
  ;find ground
  itemRide=0
  weapGround#(cyc)=wGround#
  If arenaMatting>0 And InsideRing(weapX#(cyc),weapZ#(cyc),20) Then weapGround#(cyc)=wGround#+1.1
  If InsideRing(weapX#(cyc),weapZ#(cyc),-5) Then weapGround#(cyc)=wStage#
  If weapCarrier(cyc)=0 And weapRelocate(cyc)=0
   For v=5 To 100
    If blockType(v)>0
     If weapX#(cyc)=>blockX1#(v)+5 And weapX#(cyc)=<blockX2#(v)-5 And weapZ#(cyc)=>blockZ1#(v)+5 And weapZ#(cyc)=<blockZ2#(v)-5
      If weapY#(cyc)=>blockY2#(v) Then weapGround#(cyc)=blockY2#(v)
     EndIf
    EndIf
   Next
   For v=1 To no_items
    viable=1 
    If iType(v)=15 And (InsideRing(weapX#(cyc),weapZ#(cyc),5)=0 Or InsideRing(weapX#(cyc),weapZ#(cyc),-5)) Then viable=0
    If viable>0 And ReachedCord(weapX#(cyc),weapZ#(cyc),iX#(v),iZ#(v),30) And iCarrier(v)=0 And LowestValue#(iSizeX#(iType(v)),iSizeZ#(iType(v)))>2
     ScanItem(v,-1)
     If ItemCollide(0,v,weapX#(cyc),weapZ#(cyc),-1) 
      If iState(v)=0 
       If weapY#(cyc)=>iY#(v)+iHeight#(iType(v)) Then weapGround#(cyc)=iY#(v)+iHeight#(iType(v)) : itemRide=v
       If iAnim(v)=11 And iAnimTim(v)<5 And weapY#(cyc)=>(iY#(v)+iHeight#(iType(v)))-5 And weapY#(cyc)=<(iY#(v)+iHeight#(iType(v)))+5
        If weapGravity#(cyc)=<0 Then weapGravity#(cyc)=weapWeight#(weapType(cyc))*8
       EndIf
      EndIf
      If iState(v)=1 And weapY#(cyc)=>iY#(v)+iElevate#(iType(v)) Then weapGround#(cyc)=iY#(v)+iElevate#(iType(v))
     EndIf 
     ScanItem(v,0)
    EndIf
   Next
  EndIf
  ;gravity
  If weapCarrier(cyc)=0 And weapRelocate(cyc)=0 
   If weapY#(cyc)>weapGround#(cyc) Then weapGravity#(cyc)=weapGravity#(cyc)-weapWeight#(weapType(cyc))
   weapY#(cyc)=weapY#(cyc)+weapGravity#(cyc)
  EndIf
  ;bounce on ground
  If weapY#(cyc)=<weapGround#(cyc) And weapCarrier(cyc)=0 And weapRelocate(cyc)=0
   weapY#(cyc)=weapGround#(cyc)
   If weapGravity#(cyc)<-(weapWeight#(weapType(cyc))*5)
    If gotim>0 Then ProduceSound(weap(cyc),weapSound(weapType(cyc)),22050,MakePositive#(weapGravity#(cyc))/8)
    CreateSpurt(weapX#(cyc),weapY#(cyc),weapZ#(cyc),-1,2,5,7)
    weapFlight#(cyc)=weapFlight#(cyc)-(weapFlight#(cyc)/6)
    weapGravity#(cyc)=MakePositive#(weapGravity#(cyc))
    weapGravity#(cyc)=weapGravity#(cyc)-(weapGravity#(cyc)/3)
    If weapGravity#(cyc)<0.1 Or gotim<0 Then weapGravity#(cyc)=0
    If weapFlight#(cyc)<weapGravity#(cyc)/5 Then weapFlight#(cyc)=weapGravity#(cyc)/5
    If weapName$(weapType(cyc))="Thumbtacks" And itemRide=0
     If weapGravity#(cyc)>0.5 Then weapGravity#(cyc)=0.5
     If weapFlight#(cyc)>0.5 Then weapFlight#(cyc)=0.5
    EndIf
    weapA#(cyc)=weapA#(cyc)+Rnd(-20,20)
    If itemRide>0
     ;PointEntity weap(cyc),i(itemRide)
     ;weapFlightA#(cyc)=EntityYaw(weap(cyc)) 
     weapFlightA#(cyc)=weapFlightA#(cyc)+Rnd(-90,90)
     ;weapFlight#(cyc)=weapFlight#(cyc)/2
    EndIf
    If weapGravity#(cyc)<1.0 Or itemRide>0
     For v=1 To no_plays
      pWeapSting(v,cyc)=0
     Next
    EndIf
    For v=1 To no_items
     If iState(v)=1 And ReachedCord(weapX#(cyc),weapZ#(cyc),iX#(v),iZ#(v),10) And weapY#(cyc)=>iY#(v) And weapY#(cyc)<iY#(v)+iElevate#(iType(v))
      ;If ItemCollide(0,v,weapX#(cyc),weapZ#(cyc),0)
       If weapBurning(cyc)>0 And iBurning(v)=0 Then IgniteItem(v)
       If weapBurning(cyc)=0 And iBurning(v)>0 Then IgniteWeapon(cyc)
      ;EndIf
     EndIf
    Next
    If weapPrimed(cyc)=1 Then RiskExplosion(cyc,5)
   EndIf
  EndIf
  ;bounce against blocks/items
  If weapCarrier(cyc)=0 And weapRelocate(cyc)=0 And weapFlight#(cyc)>0 And weapY#(cyc)>weapGround#(cyc)
   bounce=0
   For v=1 To 100
    excused=0
    randy=Rnd(0,1)
    If randy=0 And v=>1 And v=<4 Then excused=1
    If excused=0 And blockType(v)>0
     If weapOldX#(cyc)=>blockX1#(v) And weapOldX#(cyc)=<blockX2#(v) And weapOldZ#(cyc)=>blockZ1#(v) And weapOldZ#(cyc)=<blockZ2#(v) And weapOldY#(cyc)=>blockY1#(v) And weapOldY#(cyc)<blockY2#(v)
      trapped=1
     Else 
      If weapX#(cyc)=>blockX1#(v) And weapX#(cyc)=<blockX2#(v) And weapZ#(cyc)=>blockZ1#(v) And weapZ#(cyc)=<blockZ2#(v) And weapY#(cyc)=>blockY1#(v) And weapY#(cyc)<blockY2#(v)
       bounce=1
       If v=>1 And v=<4 Then ShakeRopes(weapX#(cyc),weapZ#(cyc),11,0)
      EndIf
     EndIf
    EndIf
   Next
   If matchCage>0 And weapY#(cyc)=>wStage# And weapY#(cyc)<96
    cageBounce=0
    If weapX#(cyc)=>-90 And weapX#(cyc)=<90 And weapZ#(cyc)=>83 And weapZ#(cyc)=<89 Then cageBounce=1 
    If weapX#(cyc)=>83 And weapX#(cyc)=<89 And weapZ#(cyc)=>-90 And weapZ#(cyc)=<90 Then cageBounce=2
    If weapX#(cyc)=>-90 And weapX#(cyc)=<90 And weapZ#(cyc)=>-89 And weapZ#(cyc)=<-83 Then cageBounce=3 
    If weapX#(cyc)=>-89 And weapX#(cyc)=<-83 And weapZ#(cyc)=>-90 And weapZ#(cyc)=<90 Then cageBounce=4
    If cageBounce>0
     ProduceSound(weap(cyc),sSmashWire,22050,0.5)
     ShakeCage(cageBounce,0.5)
     weapX#(cyc)=weapOldX#(cyc) : weapZ#(cyc)=weapOldZ#(cyc)
     bounce=1
    EndIf
   EndIf
   For v=1 To no_items
    If iState(v)=0 And ReachedCord(weapX#(cyc),weapZ#(cyc),iX#(v),iZ#(v),30) And weapY#(cyc)=>iY#(v) And weapY#(cyc)<iY#(v)+iHeight#(iType(v)) And weapItemSting(cyc,v)=1
     ;ScanItem(v,-2)
     If ItemCollide(0,v,weapX#(cyc),weapZ#(cyc),0) And ItemCollide(0,v,weapOldX#(cyc),weapOldZ#(cyc),0)=0
      ;iAnim(v)=11 : iAnimTim(v)=0 
      weapItemSting(cyc,v)=0
      bounce=1
     EndIf
     ;ScanItem(v,0)
    EndIf
   Next
   If bounce>0
    If gotim>0 Then ProduceSound(weap(cyc),weapSound(weapType(cyc)),22050,Rnd(0.1,0.3))
    CreateSpurt(weapX#(cyc),weapY#(cyc),weapZ#(cyc),-1,1,5,7)
    ;weapX#(cyc)=weapOldX#(cyc) : weapZ#(cyc)=weapOldZ#(cyc)
    weapGravity#(cyc)=1.0 : weapFlight#(cyc)=weapFlight#(cyc)/4
    weapFlightA#(cyc)=weapFlightA#(cyc)+180
    weapA#(cyc)=weapA#(cyc)+Rnd(-20,20)
    If weapPrimed(cyc)=1 Then RiskExplosion(cyc,2)
   EndIf
  EndIf
  ;bounce off humans
  If weapCarrier(cyc)=0 And weapRelocate(cyc)=0 And weapFlight#(cyc)>0 And weapY#(cyc)>weapGround#(cyc)
   For v=1 To no_plays
    range#=weapSize#(weapType(cyc))/2
    If range#<4 Then range#=4
    If AttackViable(v)=>3 Then range#=range#*2
    If WeaponProximity(v,cyc,range#) And weapY#(cyc)=>pY#(v)+5 And weapY#(cyc)=<pLimbY#(v,1)+5 And AttackViable(v)>0 And pWeapSting(v,cyc)=1
     ProduceSound(weap(cyc),weapSound(weapType(cyc)),22050,0) 
     ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),0)
     ImpactSpurt(weapX#(cyc),weapY#(cyc),weapZ#(cyc),1,1)
     BloodSpurt(weapX#(cyc),weapY#(cyc),weapZ#(cyc),pA#(cyc),FindBlood(v,weapX#(cyc),weapY#(cyc),weapZ#(cyc))-1,1)
     ScarArea(v,weapX#(cyc),weapY#(cyc),weapZ#(cyc),5)
     If FindBlood(v,weapX#(cyc),weapY#(cyc),weapZ#(cyc))>0 Then ScarWeapon(cyc,3)
     pain=weapDamage(weapType(cyc))
     If weapBurning(cyc)>0 
      ProduceSound(p(v),sIgnite,22050,0) 
      FireSpurt(weapX#(cyc),weapY#(cyc),weapZ#(cyc),-1)
      ScarArea(v,weapX#(cyc),weapY#(cyc),weapZ#(cyc),1)
      pain=pain+(pain/2)
     EndIf
     pHealth(v)=pHealth(v)-pain : pHP(v)=pHP(v)-pain : pDT(v)=GetDT(v,pain)
     pHurtA#(v)=weapFlightA#(cyc) : pStagger#(v)=0.5 : pHurtTim(v)=5
     If EntertainViable(0,v) Then entScore=entScore+pain : entHardcore=entHardcore+pain
     If AttackViable(v)=>1 And AttackViable(v)=<2
      ImpactChecks(v)
      If weapY#(cyc)=>pY#(v)+TranslateHeight#(cyc,20) Then ChangeAnim(v,Rnd(90,95)) : RiskDizziness(v,pain)
      If weapY#(cyc)<pY#(v)+TranslateHeight#(cyc,20) Then ChangeAnim(v,Rnd(100,105))
     EndIf
     If AttackViable(v)=>3
      GroundImpactChecks(v)
      GroundReaction(v)
      pDT(v)=pDT(v)-(pDT(v)/10)
     EndIf
     ;weapX#(cyc)=weapOldX#(cyc) : weapZ#(cyc)=weapOldZ#(cyc)
     weapFlightA#(cyc)=weapFlightA#(cyc)+180
     weapA#(cyc)=weapA#(cyc)+Rnd(-50,50)
     weapGravity#(cyc)=1.0 : weapFlight#(cyc)=weapFlight#(cyc)/4
     If weapFlight#(cyc)>1.0 Then weapFlight#(cyc)=1.0
     If weapHold(weapType(cyc))=2
      ProduceSound(weap(cyc),sStab,22050,1)
      weapGravity#(cyc)=0 : weapFlight#(cyc)=0
     EndIf
     If weapPrimed(cyc)=1 Then RiskExplosion(cyc,2)
     If weapThrower(cyc)>0 And weapThrower(cyc)<>v Then pFoc(v)=weapThrower(cyc) : pAngerTim(v,weapThrower(cyc))=100
     pWeapSting(v,cyc)=0
    EndIf
   Next
  EndIf
  ;follow carrier
  v=weapCarrier(cyc)
  If v>0 
   limb=FindChild(p(v),"R_Hand")
   If weapWear(cyc)>0 Then limb=FindChild(p(v),"Belt0"+weapWear(cyc))
   weapX#(cyc)=EntityX(limb,1) : weapY#(cyc)=EntityY(limb,1) : weapZ#(cyc)=EntityZ(limb,1)
  EndIf 
  ;bag state
  If weapName$(weapType(cyc))="Thumbtacks" And weapCarrier(cyc)=0
   If weapY#(cyc)=>weapGround#(cyc)-3 And weapY#(cyc)=<weapGround#(cyc)+3 And (weapGround#(cyc)=<wGround#+1.1 Or weapGround#(cyc)=wStage#)
    HideEntity FindChild(weap(cyc),"Bag")
    ShowEntity FindChild(weap(cyc),"Empty")
    ShowEntity FindChild(weap(cyc),"Tacks")
   Else
    ShowEntity FindChild(weap(cyc),"Bag")
    HideEntity FindChild(weap(cyc),"Empty")
    HideEntity FindChild(weap(cyc),"Tacks")
   EndIf
  EndIf
  ;burning
  If weapBurning(cyc)>0 
   If weapCarrier(cyc)>0
    limb=FindChild(p(weapCarrier(cyc)),weapFile$(weapType(cyc)))
    If weapWear(cyc)>0 Then limb=FindChild(p(weapCarrier(cyc)),"Belt0"+weapWear(cyc))
    limbX#=EntityX(limb,1) : limbY#=EntityY(limb,1) : limbZ#=EntityZ(limb,1)
   Else
    limbX#=weapX#(cyc)+Rnd#(-1.0,1.0) : limbY#=weapY#(cyc)+Rnd#(0.0,1.0) : limbZ#=weapZ#(cyc)+Rnd#(-1.0,1.0)
   EndIf
   CreateParticle(limbX#,limbY#-1,limbZ#,-1,9)
   CreateParticle(limbX#,limbY#-1,limbZ#,-1,7)
   randy=Rnd(0,50)
   If optFX=<1 Then randy=Rnd(0,100)
   If randy=0 Then CreateParticle(limbX#,limbY#,limbZ#,-1,2)
   If randy=1 Then CreateParticle(limbX#,limbY#,limbZ#,-1,13)
   weapBurning(cyc)=weapBurning(cyc)-1
   If weapBurning(cyc)=<0 Then ExtinguishWeapon(cyc)
   If weapCarrier(cyc)=0 And weapY#(cyc)=weapGround#(cyc) Then RiskExplosion(cyc,1000)
  EndIf
  ;explode relocation
  If weapRelocate(cyc)=>1 Then weapRelocate(cyc)=weapRelocate(cyc)+1
  If weapRelocate(cyc)>200 Then weapY#(cyc)=50 : weapRelocate(cyc)=0
  ;scarring
  ManageWeaponScars(cyc)
  ;update position
  PositionEntity weap(cyc),weapX#(cyc),weapY#(cyc),weapZ#(cyc)
  RotateEntity weap(cyc),0,weapA#(cyc),0
  ;preserve old positions
  If weapY#(cyc)=weapOldY#(cyc) Then weapGravity#(cyc)=0
  weapOldX#(cyc)=weapX#(cyc)
  weapOldY#(cyc)=weapY#(cyc)
  weapOldZ#(cyc)=weapZ#(cyc)
 Next
End Function

;--------------------------------------------------------------
;////////////////// RELATED FUNCTIONS /////////////////////////
;--------------------------------------------------------------

;ITEM IN PROXIMITY?
Function ItemProximity(cyc,v,range#)
 value=0
 If RealX#(cyc)>iX#(v)-range# And RealX#(cyc)<iX#(v)+range# And RealZ#(cyc)>iZ#(v)-range# And RealZ#(cyc)<iZ#(v)+range# Then value=1
 Return value
End Function

;ITEM IN RANGE?
Function ItemRange(cyc,v,range#)
 ;position probe
 ProjectDummy(cyc,0,0,range#)
 checkX#=EntityX(dummy) : checkZ#=EntityZ(dummy)
 ;find matches
 value=0
 If checkX#>iX#(v)-range# And checkX#<iX#(v)+range# And checkZ#>iZ#(v)-range# And checkZ#<iZ#(v)+range# Then value=1
 Return value
End Function

;CO-ORDINATES TRAPPED IN ITEM?
Function ItemConflict(x#,y#,z#)
 conflict=0
 For v=1 To no_items
  If iState(v)=0 And ReachedCord(x#,z#,iX#(v),iZ#(v),30) And y#=>iY#(v) And y#<iY#(v)+iHeight#(iType(v))
   If ItemCollide(0,v,x#,z#,0) Then conflict=1
  EndIf
 Next
 Return conflict
End Function

;SCAN ITEM DIMENSIONS
Function ScanItem(cyc,offset#)
 ;reset dummy
 PositionEntity dummy,iX#(cyc),iY#(cyc),iZ#(cyc)
 RotateEntity dummy,0,iA#(cyc),0
 rangeX=(iSizeX#(iType(cyc))+offset#)*2
 rangeZ=(iSizeZ#(iType(cyc))+offset#)*2
 MoveEntity dummy,-rangeX,0,-rangeZ
 ;explore dimensions
 iDensity(cyc)=0
 For countX=1 To rangeX+1
  For countZ=1 To rangeZ+1
   iDensity(cyc)=iDensity(cyc)+1
   iDimX#(cyc,iDensity(cyc))=EntityX(dummy)
   iDimZ#(cyc,iDensity(cyc))=EntityZ(dummy)
   MoveEntity dummy,0,0,2
  Next
  MoveEntity dummy,0,0,-((rangeZ+1)*2)
  MoveEntity dummy,2,0,0
 Next
End Function

;ITEM COLLISION?
Function ItemCollide(cyc,v,x#,z#,offset#)
 value=0
 For count=1 To iDensity(v)
  range#=3+offset#
  If range#<1 Then range#=1
  If x#=>iDimX#(v,count)-range# And x#=<iDimX#(v,count)+range# And z#=>iDimZ#(v,count)-range# And z#=<iDimZ#(v,count)+range# Then value=1 
 Next
 Return value
End Function

;FIND ITEM CLIMBING
Function FindItemClimbing(cyc,v)
 If pPlatform(cyc)=0 And pAnim(cyc)=>11 And pAnim(cyc)=<12 And pDizzyTim(cyc)=0 And pBlindTim(cyc)=0 And pMomentum(cyc)=0
  If (iY#(v)=>wStage# And InsideRing(iX#(v),iZ#(v),-20)) Or (iY#(v)<wStage# And InsideRing(iX#(v),iZ#(v),-10)=0)
   If RequestItem(cyc,v) 
    If cRun(cyc)=1 Then pFriction(cyc)=pFriction(cyc)+4 Else pFriction(cyc)=pFriction(cyc)+2
    If pFriction(cyc)>25 And iCarrier(v)=0 And PlatformClear(v+100,iX#(v),iZ#(v)) And LowestValue#(iSizeX#(iType(v)),iSizeZ#(iType(v)))>2
     InstantTurn(cyc,i(v))
     pPlatform(cyc)=v+100 : pPlatformY#(cyc)=iY#(v)+iHeight#(iType(v))
     pPlatformX#(cyc)=iX#(v) : pPlatformZ#(cyc)=iZ#(v)
     If pY#(cyc)<wStage# And InsideRing(pPlatformX#(cyc),pPlatformZ#(cyc),0)
      If pPlatformX#(cyc)>blockX1#(0) And pX#(cyc)=<blockX1#(0) Then pPlatformX#(cyc)=blockX1#(0)-1
      If pPlatformX#(cyc)<blockX2#(0) And pX#(cyc)=>blockX2#(0) Then pPlatformX#(cyc)=blockX2#(0)+1
      If pPlatformZ#(cyc)>blockZ1#(0) And pZ#(cyc)=<blockZ1#(0) Then pPlatformZ#(cyc)=blockZ1#(0)-1
      If pPlatformZ#(cyc)<blockZ2#(0) And pZ#(cyc)=>blockZ2#(0) Then pPlatformZ#(cyc)=blockZ2#(0)+1
     EndIf
     ChangeAnim(cyc,53) : pFriction(cyc)=0
    EndIf
   EndIf
  EndIf
 EndIf
End Function

;BIND ITEM TO CARRIER
Function BindItem(cyc)
 v=pCarrying(cyc)
 If v>0
  ;projected targets
  PositionEntity dummy,RealX#(cyc),pY#(cyc),RealZ#(cyc)
  RotateEntity dummy,0,pA#(cyc),0
  mover#=iCarryDepth#(iType(v),iState(v))
  If pAnim(cyc)=>40 And pAnim(cyc)=<43 Then mover#=mover#+5
  MoveEntity dummy,0,0,mover#
  tX#=EntityX(dummy) : tZ#=EntityZ(dummy)
  tY#=EntityY(FindChild(p(cyc),"R_Hand"),1)+iCarryY#(iType(v),iState(v))
  ;pursue targets
  GetSmoothSpeeds(iX#(v),tX#,iY#(v),tY#,iZ#(v),tZ#,5)
  If pAnim(cyc)=201 And pOldAnim(cyc)=200 And pAnimTim(cyc)<10
   If iState(v)=1 Then speedY#=speedY#*5
   If iCarryAnim(iType(v),iState(v))=1 Then speedY#=speedY#*10
   If iX#(v)<tX# Then iX#(v)=iX#(v)+speedX#
   If iX#(v)>tX# Then iX#(v)=iX#(v)-speedX#
   If iZ#(v)<tZ# Then iZ#(v)=iZ#(v)+speedZ#
   If iZ#(v)>tZ# Then iZ#(v)=iZ#(v)-speedZ#
  Else
   iX#(v)=tX# : iZ#(v)=tZ#
  EndIf
  If iY#(v)<tY# Then iY#(v)=iY#(v)+speedY#
  If iY#(v)>tY# Then iY#(v)=iY#(v)-speedY#
  If iY#(v)=>tY#-speedY# And iY#(v)=<tY#+speedY# Then iY#(v)=tY#
  If iY#(v)<pY#(cyc) Then iY#(v)=pY#(cyc)
  ;carrying into ring
  If (pAnim(cyc)=40 Or pAnim(cyc)=204) And InsideRing(iX#(v),iZ#(v),5)
   If iY#(v)<wStage# Then iY#(v)=wStage#
  EndIf
  ;offset angle
  iA#(v)=pA#(cyc)+iCarryA#(iType(v),iState(v))
 EndIf
End Function

;DROP ITEM
Function DropItem(cyc)
 v=pCarrying(cyc)
 If v>0
  ProduceSound(p(cyc),sShuffle(Rnd(1,3)),44100,0)
  pCarrying(cyc)=0 : iCarrier(v)=0
  iAnim(v)=10 : iAnimTim(v)=0 : iGravity#(v)=0
  iFallA#(v)=pA#(cyc) : iGround#(v)=pY#(cyc)
  If iState(v)=0
   pusher#=9.0-(iCarryDepth#(iType(v),0)-iSizeZ#(iType(v)))
   If pusher#<2.0 Then pusher#=2.0
   PositionEntity dummy,iX#(v),iY#(v),iZ#(v)
   RotateEntity dummy,0,iFallA#(v),0
   MoveEntity dummy,0,0,pusher#
   iX#(v)=EntityX(dummy) : iZ#(v)=EntityZ(dummy)
  EndIf
 EndIf
End Function

;RESET ITEM CONTACTS
Function ResetItemStings(cyc)
 ;reset item contacts
 For v=1 To no_items
  pItemSting(cyc,v)=1
 Next
 ;reset weapon contacts
 For v=1 To no_weaps
  pWeapSting(cyc,v)=1
 Next
End Function

;CRASH INTO THINGS WHEN FALLING
Function FindSmashes(cyc,level#,task) ;0=humans & items (ie. all), weapons & items only (i.e. crushes), 2=standing items only (i.e. essential breaks)
 ;get co-ordinates
 checkX#=RealX#(cyc) : checkZ#=RealZ#(cyc)
 If pGrappler(cyc)>0 And pAnim(pGrappler(cyc))=515 Then checkX#=pLimbX#(cyc,1)  : checkZ#=pLimbZ#(cyc,1) 
 ;item contacts
 For v=1 To no_items
  viable=1
  If iCarrier(v)>0 And pAnim(iCarrier(v))=>204 And pAnim(iCarrier(v))=<205 Then viable=0
  If task=2 And iState(v)=1 Then viable=0
  If pAnim(cyc)=43 And iType(v)=15
   If iX#(v)=87 And iZ#(v)=87 Then viable=0
   If iX#(v)=-87 And iZ#(v)=-87 Then viable=0
  EndIf
  If pAnim(cyc)=54 And pOldPlatform(cyc)=100+v Then viable=0
  If iState(v)=1 Then height#=10 Else height#=iHeight#(iType(v))+5
  If viable=1 And ItemProximity(cyc,v,30) And level#=>iY#(v)-5 And level#<iY#(v)+height# And pItemSting(cyc,v)=1
   margin#=0
   If pOldPlatform(cyc)=100+v
    If pAnim(cyc)=54 Or pAnim(cyc)=64 Or pAnim(cyc)=74 Then margin#=-2
   EndIf
   If ItemCollide(cyc,v,checkX#,checkZ#,margin#)
    Pop(0,Rnd(2,9),0)
    ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),1)
    ProduceSound(i(v),iSound(iType(v)),22050,1) 
    impactY#=level# 
    If iState(v)=0 And impactY#>iY#(v)+iHeight#(iType(v)) Then impactY#=iY#(v)+iHeight#(iType(v)) 
    If iState(v)=1 And impactY#>iY#(v)+5 Then impactY#=iY#(v)+5
    ImpactSpurt(checkX#,impactY#,checkZ#,2,1)
    ScarArea(cyc,0,0,0,5) : BloodSpurt(checkX#,impactY#,checkZ#,-1,FindBlood(cyc,checkX#,impactY#,checkZ#)-1,1)
    If iType(v)=14 Then ScarArea(cyc,0,0,0,5) : BloodSpurt(checkX#,impactY#,checkZ#,-1,FindBlood(cyc,checkX#,impactY#,checkZ#)-1,1)
    If FindBlood(cyc,0,0,0)>0 Then ScarItem(v,3)
    If iState(v)=1 Then broken=1 Else broken=0
    If iState(v)=0 Then Animate i(v),3,3.0,1,0 Else Animate i(v),3,2.0,2,5
    iState(v)=1 : pItemSting(cyc,v)=0
    randy=Rnd(0,3)
    If randy=0 And iState(v)=0 And iExplodable(iType(v))>0 Then CreateExplosion(i(v),iX#(v),iY#(v)+5,iZ#(v),iExplodable(iType(v)))
    iX#(v)=iX#(v)+Rnd#(-2.0,2.0) : iZ#(v)=iZ#(v)+Rnd#(-2.0,2.0)
    iA#(v)=iA#(v)+Rnd(-20,20)
    If cyc=matchPlayer Or cyc=pFoc(matchPlayer)
     If iState(v)=0 Then matchDamage=matchDamage+100 Else matchDamage=matchDamage+25
    EndIf
    pain=iDamage(iType(v))/2
    If broken=1 Then pain=pain/2
    If (pAnim(cyc)=>143 And pAnim(cyc)=<146) Or pAnim(cyc)=309 Or pAnim(cyc)=509 Then pain=pain*2 
    If iBurning(v)>0 
     ProduceSound(p(cyc),sIgnite,22050,0) 
     Pop(0,Rnd(2,9),1) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),1) 
     FireSpurt(checkX#,impactY#,checkZ#,-1) 
     BloodSpurt(checkX#,impactY#,checkZ#,-1,FindBlood(cyc,0,0,0)-1,1)
     ScarArea(cyc,0,0,0,5) : RiskInjury(cyc,Rnd(0,5),1)
     pain=pain*2
    EndIf
    pHealth(cyc)=pHealth(cyc)-pain
    If AttackViable(cyc)<3 Then pDT(cyc)=pDT(cyc)+Rnd(pain/4,pain/2)
    If EntertainViable(cyc,0) Then entScore=entScore+(pain/2) : entHardcore=entHardcore+pain
    If pAnim(cyc)=43 Or pAnim(cyc)=50 Or pAnim(cyc)=51 Or pAnim(cyc)=52 Or pAnim(cyc)=54 Or pAnim(cyc)=56 Or (pAnim(cyc)=>60 And pAnim(cyc)=<73) Or pAnim(cyc)=234
     pHP(cyc)=0 : pHurtA#(cyc)=pA#(cyc)+180 
    EndIf
    RiskInjury(cyc,Rnd(0,5),1)
    pHeat(cyc)=pHeat(cyc)-1
    If iCarrier(v)>0 
     pHurtA#(iCarrier(v))=pA#(iCarrier(v))+180 : pStagger#(iCarrier(v))=0.5
     ChangeAnim(iCarrier(v),Rnd(102,105))
     DropItem(iCarrier(v))
    EndIf
   EndIf
  EndIf
 Next
 ;weapon contacts
 If task=<1 And pAnim(cyc)<>43 And pAnim(cyc)<>54 And pAnim(cyc)<>56
  For v=1 To no_weaps
   range#=5+(weapSize#(weapType(v))/2)
   If checkX#>weapX#(v)-range# And checkX#<weapX#(v)+range# And checkZ#>weapZ#(v)-range# And checkZ#<weapZ#(v)+range# And level#=>weapY#(v)-5 And level#=<weapY#(v)+5 And weapCarrier(v)=0 And pWeapSting(cyc,v)=1
    Pop(0,Rnd(2,9),0)
    ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),1)
    ProduceSound(weap(v),weapSound(weapType(v)),22050,0)
    ImpactSpurt(weapX#(v),weapY#(v)+2,weapZ#(v),1,1)
    BloodSpurt(weapX#(v),weapY#(v)+2,weapZ#(v),-1,FindBlood(cyc,weapX#(v),weapY#(v),weapZ#(v))-1,1)
    ScarArea(cyc,weapX#(v),pY#(cyc),weapZ#(v),10)
    If FindBlood(cyc,weapX#(v),weapY#(v),weapZ#(v))>0 Then ScarWeapon(v,3)
    Animate weap(v),3,2.0,0,2
    weapA#(v)=weapA#(v)+Rnd(-30,30)
    weapX#(v)=weapX#(v)+Rnd(-2,2) : weapZ#(v)=weapZ#(v)+Rnd(-2,2)
    If cyc=matchPlayer Or cyc=pFoc(matchPlayer) Then matchDamage=matchDamage+10
    pain=weapDamage(weapType(v))/2
    If (pAnim(cyc)=>143 And pAnim(cyc)=<146) Or pAnim(cyc)=309 Or pAnim(cyc)=509 Then pain=pain*2
    If weapName$(weapType(v))="Thumbtacks" 
     Pop(0,Rnd(2,9),1) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),1)
     BloodSpurt(weapX#(v),weapY#(v)+2,weapZ#(v),-1,FindBlood(cyc,weapX#(v),weapY#(v),weapZ#(v))-1,1)
     ScarArea(cyc,weapX#(v),weapY#(v),weapZ#(v),5)
     RiskInjury(cyc,Rnd(0,5),1)
     pain=pain*2
    EndIf
    If weapBurning(v)>0 
     ProduceSound(p(cyc),sIgnite,22050,0) 
     Pop(0,Rnd(2,9),1) : ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),1) 
     FireSpurt(weapX#(v),weapY#(v)+2,weapZ#(v),-1) 
     BloodSpurt(weapX#(v),weapY#(v)+2,weapZ#(v),-1,FindBlood(cyc,weapX#(v),weapY#(v),weapZ#(v))-1,1)
     ScarArea(cyc,weapX#(v),weapY#(v),weapZ#(v),5)
     RiskInjury(cyc,Rnd(0,5),1)
     pain=pain*2
    EndIf
    pHealth(cyc)=pHealth(cyc)-pain
    If AttackViable(cyc)<3 Then pDT(cyc)=pDT(cyc)+Rnd(pain/2,pain)
    If EntertainViable(cyc,0) Then entScore=entScore+(pain/2) : entHardcore=entHardcore+pain
    If pAnim(cyc)=54 Or (pAnim(cyc)=>60 And pAnim(cyc)=<73) Then pHP(cyc)=0 : pHurtA#(cyc)=pA#(cyc)+180
    RiskInjury(cyc,Rnd(0,5),10) : RiskExplosion(v,2)
    pHeat(cyc)=pHeat(cyc)-1
    pWeapSting(cyc,v)=0
   EndIf
  Next
 EndIf 
 ;human contacts
 If task<>1 Or (pAnim(cyc)=>60 And pAnim(cyc)=<63)
  For v=1 To no_plays
   ;standing victims
   If cyc<>v And ReachedCord(checkX#,checkZ#,RealX#(v),RealZ#(v),8) And pAnim(v)<>64 And pAnim(v)<>74 And pMultiSting(cyc,v)=1
    If level#=>pY#(v)-5 And ((level#<pY#(v)+40 And AttackViable(v)=1) Or (level#<pY#(v)+30 And AttackViable(v)=2))
     ImpactChecks(v)
     Pop(0,Rnd(2,9),0)
     ProduceSound(p(v),sImpact(Rnd(5,7)),22050,0) 
     ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),0)
     ChangeAnim(v,Rnd(92,95))
     pHealth(v)=pHealth(v)-TranslateWeight(pChar(cyc))
     If EntertainViable(cyc,v) Then entScore=entScore+100
     If (pAnim(cyc)=>143 And pAnim(cyc)=<146) Or pAnim(cyc)=309 Or pAnim(cyc)=509 Then pHealth(cyc)=pHealth(cyc)-TranslateWeight(pChar(cyc))
     pHP(v)=0 : pHurtA#(v)=pA#(v)+180
     ;If pAnim(cyc)=43 Or pAnim(cyc)=50 Or pAnim(cyc)=51 Or pAnim(cyc)=52 Or pAnim(cyc)=54 Or pAnim(cyc)=56 Or pAnim(cyc)=64 Or pAnim(cyc)=234
      ;pHP(cyc)=0 : pHurtA#(cyc)=pA#(cyc)+180
     ;EndIf
     pHeat(v)=pHeat(v)-1
     pMultiSting(cyc,v)=0
    EndIf 
   EndIf
   ;grounded victims 
   If cyc<>v And task=<1 And pMultiSting(cyc,v)<>AttackViable(v) And level#>pY#(v)-10 And pMultiSting(cyc,v)=1
    If (level#<pY#(v)+20 And AttackViable(v)=3) Or (level#<pY#(v)+10 And AttackViable(v)=4)
     ScanBody(v)
     For scan=0 To 6
      If ReachedCord(checkX#,checkZ#,pScanX#(v,scan),pScanZ#(v,scan),10) And pMultiSting(cyc,v)=1
       GroundImpactChecks(v)
       Pop(0,Rnd(2,9),0)
       ProduceSound(p(v),sBlock(7),22050,0) 
       ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),0)
       GroundReaction(v) : pHurtTim(v)=10
       ResetItemStings(v) : FindSmashes(v,pY#(v),1)
       pHealth(v)=pHealth(v)-TranslateWeight(pChar(cyc))
       If EntertainViable(cyc,v) Then entScore=entScore+50
       If (pAnim(cyc)=>143 And pAnim(cyc)=<146) Or pAnim(cyc)=309 Or pAnim(cyc)=509 Then pHealth(cyc)=pHealth(cyc)-TranslateWeight(pChar(cyc)) 
       ;If pAnim(cyc)=43 Or pAnim(cyc)=50 Or pAnim(cyc)=51 Or pAnim(cyc)=52 Or pAnim(cyc)=54 Or pAnim(cyc)=56 Or pAnim(cyc)=64 Then pHP(cyc)=0 : pHurtA#(cyc)=pA#(cyc)+180
       pHeat(v)=pHeat(v)-1
       pMultiSting(cyc,v)=0    
      EndIf
     Next
    EndIf  
   EndIf
  Next
 EndIf
 ;ropes
 nearest=FindRopes(RealX#(cyc),RealZ#(cyc))
 If TouchingRopes(cyc) And pRopeSting(cyc,nearest)=1 And task=0 And pAnim(cyc)<>44 And pAnim(cyc)<>142
  ShakeRopes(RealX#(cyc),RealZ#(cyc),20,1)
  RopeSound(p(cyc),0)
  RopeBurn(cyc)
  pRopeSting(cyc,nearest)=0
 EndIf
 ;cage contacts
 If matchCage>0 And InsideRing(checkX#,checkZ#,0) And pCageSting(cyc)=1 And task=0
  If checkZ#>blockZ1#(1)-1 Or checkX#>blockX1#(2)-1 Or checkZ#<blockZ2#(3)+1 Or checkX#<blockX2#(4)+1
   Pop(0,Rnd(2,9),0)
   ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),1)
   ProduceSound(p(cyc),sSmashWire,22050,1)
   ShakeCage(NearestSide(checkX#,checkZ#),2.0)
   ScarArea(cyc,0,0,0,5) : RiskInjury(cyc,Rnd(0,5),1)
   pain=100
   pHealth(cyc)=pHealth(cyc)-pain
   If AttackViable(cyc)<3 Then pDT(cyc)=pDT(cyc)+Rnd(pain/4,pain/2)
   If EntertainViable(cyc,0) Then entScore=entScore+(pain/2) : entHardcore=entHardcore+pain
   pCageSting(cyc)=0
  EndIf
 EndIf
End Function

;SCAR ITEM
Function ScarItem(cyc,chance)
 randy=Rnd(0,chance)
 If randy=0 Then iScar(cyc)=iScar(cyc)+1
 If iScar(cyc)>4 Then iScar(cyc)=4 
End Function

;MANAGE ITEM SCARS
Function ManageItemScars(cyc)
 ;heal scars
 randy=Rnd(0,6000)
 If randy=0 Then iScar(cyc)=iScar(cyc)-1
 If iScar(cyc)<0 Then iScar(cyc)=0
 ;prevent gore
 If optGore=0 And iScar(cyc)>1 Then iScar(cyc)=1
 ;apply scars
 If iScar(cyc)<>iOldScar(cyc) And tBodyScar(iScar(cyc))>0
  For limb=1 To CountChildren(i(cyc))
   EntityTexture GetChild(i(cyc),limb),tBodyScar(iScar(cyc)),0,2
  Next
 EndIf
 ;store status
 iOldScar(cyc)=iScar(cyc)
End Function

;WEAPON IN PROXIMITY?
Function WeaponProximity(cyc,v,range#)
 value=0
 If RealX#(cyc)>weapX#(v)-range# And RealX#(cyc)<weapX#(v)+range# And RealZ#(cyc)>weapZ#(v)-range# And RealZ#(cyc)<weapZ#(v)+range#
  value=1
 EndIf
 Return value
End Function

;FIND NEAREST WEAPON
Function NearestWeapon(cyc) 
 value=0 : hi#=9999
 For v=1 To no_weaps 
  distance#=GetDistance#(pX#(cyc),pZ#(cyc),weapX#(v),weapZ#(v))  
  If weapCarrier(v)>0 Then distance#=distance#+(distance#/2)
  If weapY#(v)<pY#(cyc)-30 Or weapY#(v)>pY#(cyc)+30 Or (weapY#(v)>weapGround#(v) And weapCarrier(v)=0) Then distance#=distance#*2
  If distance#<hi# And pWeaponAccess(cyc,v)=0 Then value=v : hi#=distance#
 Next
 Return value
End Function

;ATTAIN WEAPON
Function AttainWeapon(cyc,v)
 ;bind to player
 If weapCarrier(v)>0 
  HideEntity FindChild(p(weapCarrier(v)),weapFile$(weapType(v)))
  HideEntity FindChild(p(weapCarrier(v)),"Belt01")
  HideEntity FindChild(p(weapCarrier(v)),"Belt02")
  pHolding(weapCarrier(v))=0
 EndIf
 pHolding(cyc)=v : weapCarrier(v)=cyc : weapWear(v)=0
 ;texturing issues
 If weapTex(weapType(v))>0
  EntityTexture FindChild(p(cyc),weapFile$(weapType(v))),weapTex(weapType(v))
  For count=1 To 2
   EntityTexture FindChild(p(cyc),"Belt0"+count),weapTex(weapType(v))
  Next
 EndIf
 If weapFile$(weapType(v))="Bottle" Then EntityAlpha FindChild(p(cyc),weapFile$(weapType(v))),0.75
 EntityShininess FindChild(p(cyc),weapFile$(weapType(v))),weapShine#(weapType(v))
 weapOldScar(v)=-1  
 ;switch display
 ShowEntity FindChild(p(cyc),weapFile$(weapType(v))) 
 HideEntity weap(v)
End Function

;DROP WEAPON
Function DropWeapon(cyc,chance)
 v=pHolding(cyc)
 If weapHold(weapType(v))=3
  If pAnim(cyc)<>226 And pAnim(cyc)<>229 And pAnim(cyc)<>80 And pAnim(cyc)<>81 And pAnim(cyc)<>83 And pAnim(cyc)<>84
   If pAnim(cyc)<60 Or pAnim(cyc)=>80 Then chance=0
  EndIf
 EndIf
 randy=Rnd(0,chance)
 If randy=0 And v>0
  ;change physics
  ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
  limb=FindChild(p(cyc),"R_Hand")
  If weapWear(v)>0 Then limb=FindChild(p(cyc),"Belt0"+weapWear(v))
  weapX#(v)=EntityX(limb,1) : weapY#(v)=EntityY(limb,1) : weapZ#(v)=EntityZ(limb,1)
  If weapY#(v)<pY#(cyc)+5 Then weapY#(v)=pY#(cyc)+5
  weapA#(v)=EntityYaw(FindChild(p(cyc),weapFile$(weapType(v))),1)
  weapGravity#(v)=weapWeight#(weapType(v))*5 : weapFlight#(v)=0.5
  If pAnim(cyc)=212 Then weapFlightA#(v)=pA#(cyc)+Rnd(135,315) Else weapFlightA#(v)=Rnd(0,360) 
  pHolding(cyc)=0 : weapCarrier(v)=0 : weapWear(v)=0 
  HideEntity FindChild(p(cyc),weapFile$(weapType(v)))
  For count=1 To 2
   HideEntity FindChild(p(cyc),"Belt0"+count)
  Next
  ShowEntity weap(v) : weapOldScar(v)=-1
  ;disarm for humans
  weapPrimed(v)=0
  For count=1 To no_plays
   pWeapSting(count,v)=0
  Next
  pWeapSting(cyc,v)=0 
  For count=1 To no_items
   weapItemSting(v,count)=1
  Next 
 EndIf
End Function

;THROW WEAPON
Function ThrowWeapon(cyc)
 v=pHolding(cyc)
 If v>0
  ;position for release
  limb=FindChild(p(cyc),"R_Hand")
  PositionEntity dummy,EntityX(limb,1),EntityY(limb,1),EntityZ(limb,1)
  weapA#(v)=EntityYaw(FindChild(p(cyc),weapFile$(weapType(v))),1)
  RotateEntity dummy,0,weapA#(v),0
  MoveEntity dummy,-2,0,0
  weapX#(v)=EntityX(dummy) : weapY#(v)=EntityY(dummy) : weapZ#(v)=EntityZ(dummy)
  ;reset state
  weapFlightA#(v)=pA#(cyc) : weapFlight#(v)=1.5+weapGravity#(v)
  If weapHold(weapType(v))=2
   weapFlight#(v)=weapFlight#(v)+(weapFlight#(v)/4) 
   weapGravity#(v)=weapGravity#(v)-(weapGravity#(v)/3)
   weapA#(v)=pA#(cyc)+180
  EndIf
  weapThrower(v)=cyc : weapCarrier(v)=0
  pHolding(cyc)=0
  HideEntity FindChild(p(cyc),weapFile$(weapType(v)))
  ShowEntity weap(v) : weapOldScar(v)=-1
  ;make potent
  weapPrimed(v)=1
  For count=1 To no_plays
   pWeapSting(count,v)=1
  Next
  pWeapSting(cyc,v)=0 
  For count=1 To no_items
   weapItemSting(v,count)=1
  Next
 EndIf
End Function

;FIND SPONTANEOUS WEAPON IMPACT
Function WeaponImpact(cyc,v,force) ;0=blocked, 1=weak, 2=strong
 w=pHolding(cyc)
 If w>0 And weapWear(w)=0
  ;blocked effect
  If force=0
   ;Pop(cyc,Rnd(2,8),0.5)
   ProduceSound(p(v),weapSound(weapType(w)),22050,0)
   If weapHold(weapType(w))=2 Then ProduceSound(p(v),sStab,22050,0)
   ScarArea(v,pX#(v),EntityY(pLimb(cyc,19),1),pZ#(v),10)
   pain=weapDamage(weapType(w))/2
  EndIf
  ;weak effect
  If force=1
   ;Pop(cyc,Rnd(2,8),0)
   ProduceSound(p(v),weapSound(weapType(w)),22050,0)
   If weapHold(weapType(w))=2 Then ProduceSound(p(v),sStab,22050,0)
   ScarArea(v,pX#(v),EntityY(pLimb(cyc,19),1),pZ#(v),5)
   RiskInjury(v,Rnd(0,5),10)
   pain=weapDamage(weapType(w))
  EndIf
  ;strong effect
  If force=2
   ;Pop(cyc,Rnd(2,8),1)
   ProduceSound(p(v),weapSound(weapType(w)),22050,1)
   If weapHold(weapType(w))=2 Then ProduceSound(p(v),sStab,22050,1)
   ScarArea(v,pX#(v),EntityY(pLimb(cyc,19),1),pZ#(v),1)
   RiskInjury(v,Rnd(0,5),1)
   pain=weapDamage(weapType(w))+(weapDamage(weapType(w))/2)
  EndIf
  ;fire bonus
  If weapBurning(w)>0 
   ProduceSound(p(v),sIgnite,22050,0) 
   limb=FindChild(p(cyc),weapFile$(weapType(w)))
   ScarArea(v,EntityX(limb,1),EntityY(limb,1),EntityZ(limb,1),1)
   pain=pain+(pain/2)
  EndIf
  ;damage
  pHealth(v)=pHealth(v)-pain
  If AttackViable(v)=>1 And AttackViable(v)=<2 Then pHP(v)=pHP(v)-Rnd(pain/4,pain)
  If AttackViable(v)=<2 Then pDT(v)=pDT(v)+Rnd(pain/2,pain)
  If EntertainViable(cyc,v) Then entScore=entScore+(pain/2) : entHardcore=entHardcore+pain
  ;clock usage
  ClockWeapon(cyc,v,weapType(w))
  ;risk drop
  If pAnim(cyc)=>300 Then DropWeapon(cyc,5)
  ;risk DQ
  RiskDQ(cyc,v)
 EndIf
End Function

;SCAR WEAPON
Function ScarWeapon(cyc,chance)
 randy=Rnd(0,chance)
 If randy=0 Then weapScar(cyc)=weapScar(cyc)+1
 If weapScar(cyc)>4 Then weapScar(cyc)=4 
End Function

;MANAGE WEAPON SCARS
Function ManageWeaponScars(cyc)
 ;heal scars
 randy=Rnd(0,6000)
 If randy=0 Then weapScar(cyc)=weapScar(cyc)-1
 If weapScar(cyc)<0 Then weapScar(cyc)=0
 ;prevent gore
 If optGore=0 And weapScar(cyc)>1 Then weapScar(cyc)=1
 ;apply scars
 If weapScar(cyc)<>weapOldScar(cyc) And tBodyScar(weapScar(cyc))>0
  For limb=1 To CountChildren(weap(cyc))
   EntityTexture GetChild(weap(cyc),limb),tBodyScar(weapScar(cyc)),0,2
  Next
  If weapCarrier(cyc)>0
   limb=FindChild(p(weapCarrier(cyc)),weapFile$(weapType(cyc)))
   If weapWear(cyc)>0 Then limb=FindChild(p(weapCarrier(cyc)),"Belt0"+weapWear(cyc))
   EntityTexture limb,tBodyScar(weapScar(cyc)),0,2 
  EndIf
 EndIf
 ;store status
 weapOldScar(cyc)=weapScar(cyc)
End Function

;RISK EXPLOSION
Function RiskExplosion(cyc,chance)
If game=0 Or fedProduction(charFed(gamChar),9)>0
 If weapType(cyc)=12 Or weapType(cyc)=20 Then chance=chance*5
 randy=Rnd(0,chance)
 If (randy=0 Or (weapPrimed(cyc) And weapBurning(cyc)>0)) And weapExplodable(weapType(cyc))>0 And optFX>0 And gotim>0
  CreateExplosion(weap(cyc),weapX#(cyc),weapY#(cyc),weapZ#(cyc),weapExplodable(weapType(cyc)))
  If weapBurning(cyc)>0 Then ExtinguishWeapon(cyc)
  area=Rnd(1,2)
  If weapZ#(cyc)<-385 Or weapZ#(cyc)>385 Then area=1
  If weapZ#(cyc)>385 Then area=2
  If area=0 Then weapX#(cyc)=Rnd(-140,140) : weapZ#(cyc)=Rnd(-140,140)
  If area=1 Then weapX#(cyc)=Rnd(-100,100) : weapZ#(cyc)=755+Rnd(-75,75)
  If area=2 Then weapX#(cyc)=Rnd(-100,100) : weapZ#(cyc)=(-700)+Rnd(-50,50)
  weapY#(cyc)=wGround#-50
  weapRelocate(cyc)=1 : weapPrimed(cyc)=0
 EndIf
EndIf
End Function

;BURN HANDS
Function BurnHands(cyc,style,chance) ;0=weapon (one hand), 1=item (two hands)
 randy=Rnd(0,chance)
 If randy=0
  ;effect
  ProduceSound(p(cyc),sIgnite,22050,0)
  ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),1)
  ;scar hand(s)
  If style=1
   For limb=8 To 18
    ScarLimb(cyc,limb,5)
   Next
  EndIf
  For limb=21 To 31
   ScarLimb(cyc,limb,5)
  Next
  ;adjust state
  DropItem(cyc)
  DropWeapon(cyc,0)
  ChangeAnim(cyc,Rnd(102,105))
  ;scare CPU
  pNowhere(cyc)=99
 EndIf
End Function