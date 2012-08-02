;//////////////////////////////////////////////////////////////////////////////
;----------------------- WRESTLING MPIRE 2008: ATTACKS ------------------------
;//////////////////////////////////////////////////////////////////////////////

;-----------------------------------------------------------------
;////////////////// STANDING ATTACK ANIMATIONS ///////////////////
;-----------------------------------------------------------------
Function AttackAnims(cyc)
 ;ATTACKING PROCESS
 If pAnim(cyc)=>60 And pAnim(cyc)=<63
  newAnim=-1
  style=pAnim(cyc)-59
  move=charAttack(pChar(cyc),style)
  ;initiation
  If pAnimTim(cyc)=0
   factor#=50+PercentOf#(50,pSkill(cyc))
   If attackFall(style,move)>0 Or attackLimb(style,move)=>36 Then factor#=50+PercentOf#(50,pAgility(cyc))
   If factor#<75 Then factor#=75
   pAnimSpeed#(cyc)=PercentOf#(attackSpeed#(style,move),factor#)
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,pAnim(cyc)),attackTransition(style,move)/pAnimSpeed#(cyc)
   factor#=50+PercentOf#(50,pAgility(cyc))
   pTravel#(cyc)=PercentOf#(attackTravel#(style,move),factor#)
   If pAnim(cyc)=63 Then pTravel#(cyc)=pSpeed#(cyc)*2
   pHurtDelay(cyc)=0 : pDelayAnim(cyc)=0
   pStagger#(cyc)=0 : pSting(cyc)=1
  EndIf
  ;travelling
  AttackMovement(cyc,attackMomentum(style,move))
  ;swing
  If pAnimTim(cyc)=Int(attackActive(style,move)/pAnimSpeed#(cyc))/2
   If style=<2 Then ProduceSound(p(cyc),sSwing,22000,PercentOf#(0.5,pStrength(cyc)))
   If style=>3 Then ProduceSound(p(cyc),sSwing,20000,PercentOf#(0.75,pStrength(cyc)))
  EndIf
  ;find impacts
  intact=1
  If attackLimb(style,move)=1 And pScar(cyc,20)=5 Then intact=0
  If attackLimb(style,move)>1 And pScar(cyc,attackLimb(style,move))=5 Then intact=0 
  If intact=1 And pAnimTim(cyc)=>Int(attackActive(style,move)/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(attackExpire(style,move)/pAnimSpeed#(cyc)) And pSting(cyc)=1
   FindAttackImpacts(cyc,attackActive(style,move),attackImpact(style,move),attackExpire(style,move),attackRange#(style,move),attackHeight#(style,move),attackPower(style,move),attackWeapon(style,move),style)
  EndIf
  If pAnimTim(cyc)=Int(attackExpire(style,move)/pAnimSpeed#(cyc)) And pSting(cyc)=0
   If attackLimb(style,move)<36 Then RiskInjury(cyc,Rnd(1,2),100)
   If attackLimb(style,move)=>36 Then RiskInjury(cyc,4,100)
  EndIf
  ;failed attempt
  If pAnimTim(cyc)=Int(attackExpire(style,move)/pAnimSpeed#(cyc)) And pSting(cyc)=1
   If style=>1 And style=<2 Then pHeat(cyc)=pHeat(cyc)-Rnd(0,1)
   If style=>3 And style=<4 Then pHeat(cyc)=pHeat(cyc)-1
  EndIf
  If pAnimTim(cyc)>Int(attackExpire(style,move)/pAnimSpeed#(cyc)) And pSting(cyc)=0 Then DropWeapon(cyc,500)
  ;landing
  If attackFall(style,move)>0
   If pAnimTim(cyc)=>Int(attackActive(style,move)/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(attackFall(style,move)/pAnimSpeed#(cyc)) Then FindSmashes(cyc,pLimbY#(cyc,36),1)
   If pAnimTim(cyc)=Int(attackFall(style,move)/pAnimSpeed#(cyc))
    ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
    ProduceSound(p(cyc),sFall,22050,1)
    ProduceSound(p(cyc),sThud,22050,0) 
    If InsideRing(RealX#(cyc),RealZ#(cyc),-5) Then ShakeRing(0,1.0)
    FindSmashes(cyc,pY#(cyc),1)
    If pSting(cyc)=1
     pHealth(cyc)=pHealth(cyc)-100 : pHP(cyc)=pHP(cyc)-100 : pHurtA#(cyc)=Rnd(0,360)
     If EntertainViable(cyc,0) Then entScore=entScore+100
    EndIf
    pTravel#(cyc)=pTravel#(cyc)/2
   EndIf
   If pAnimTim(cyc)>Int(attackFall(style,move)/pAnimSpeed#(cyc)) Then DropWeapon(cyc,500)
  EndIf
  ;aggravate injuries
  If pAnimTim(cyc)>Int(attackExpire(style,move)/pAnimSpeed#(cyc)) And attackFall(style,move)=0 And pSting(cyc)=0 And pSpecial(cyc)=0
   randy=Rnd(0,25)
   If randy=0 And attackLimb(style,move)<36 And pInjured(cyc,1)>0 Then newAnim=111
   If randy=1 And attackLimb(style,move)<36 And pInjured(cyc,2)>0 Then newAnim=112
   If randy=2 And attackLimb(style,move)=>36 And pInjured(cyc,4)>0 Then newAnim=114 
   If pReaction(cyc)=>111 And pReaction(cyc)=<115 Then newAnim=pReaction(cyc) : pImmunity(cyc)=20 : pReaction(cyc)=0
  EndIf
  ;end
  If pHurtDelay(cyc)>0
   If pAnimTim(cyc)>Int(attackExpire(style,move)/pAnimSpeed#(cyc)) Then newAnim=pDelayAnim(cyc) : pHurtDelay(cyc)=0
  Else
   If pAnimTim(cyc)=>Int(attackLength(style,move)/pAnimSpeed#(cyc)) Then newAnim=0 
  EndIf 
  If game=1 And attackLearned(style,move)<1 Then attackLearned(style,move)=1
  ;divert to other animations
  If pAnim(cyc)=62
   If attackWeapon(3,charAttack(pChar(cyc),3))=10 Then newAnim=67 ;dust throw 
   If attackWeapon(3,charAttack(pChar(cyc),3))=11 Then newAnim=66 : pSpurt(cyc)=11 ;green mist
   If attackWeapon(3,charAttack(pChar(cyc),3))=12 Then newAnim=66 : pSpurt(cyc)=1 ;fire breath
  EndIf
  If pHolding(cyc)>0 And weapWear(pHolding(cyc))=0
   If pAnim(cyc)=60 And attackWeapon(style,move)<>1 Then newAnim=220
   If pAnim(cyc)=60 And weapHold(weapType(pHolding(cyc)))=1 Then newAnim=222
   If pAnim(cyc)=60 And weapHold(weapType(pHolding(cyc)))=2 Then newAnim=224
   If pAnim(cyc)=62 And attackWeapon(style,move)<>1 Then newAnim=221
   If pAnim(cyc)=62 And weapHold(weapType(pHolding(cyc)))=1 Then newAnim=223
   If pAnim(cyc)=62 And weapHold(weapType(pHolding(cyc)))=2 Then newAnim=225
   If weapHold(weapType(pHolding(cyc)))=3 Then newAnim=226
   If pAnim(cyc)=62 And weapType(pHolding(cyc))=16 Then newAnim=66 : pSpurt(cyc)=8 ;water spit
   If pAnim(cyc)=62 And weapType(pHolding(cyc))=17 Then newAnim=66 : pSpurt(cyc)=12 ;beer spit
  EndIf
  If newAnim=>0 Then ChangeAnim(cyc,newAnim)
  pHealth(cyc)=pHealth(cyc)-1
 EndIf
 ;FLYING ATTACK PROCESS
 If pAnim(cyc)=64
  style=pAnim(cyc)-59
  move=charAttack(pChar(cyc),style)
  If InsideRing(pX#(cyc),pZ#(cyc),-15) Then pGround#(cyc)=wStage# Else pGround#(cyc)=wGround#
  ;initiation
  If pAnimTim(cyc)=0
   If pPlatform(cyc)=0 Then pAnimSpeed#(cyc)=2.0 Else pAnimSpeed#(cyc)=2.25+((23.0-LeapHeight#(cyc,50))/60)
   If pPlatform(cyc)=0 Then attackTransition(style,move)=10 Else attackTransition(style,move)=20
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,pAnim(cyc)),attackTransition(style,move)/pAnimSpeed#(cyc)
   GetFlightPhysics(cyc,pFoc(cyc))
   forecast#=50
   If pPlatform(cyc)=>100
    forecast#=iHeight#(iType(pPlatform(cyc)-100))*3
    If forecast#>50 Then forecast#=50 
   EndIf
   pFlightA#(cyc)=pA#(cyc) : pFlyAgenda(cyc)=GetFlyAgenda(cyc,forecast#)
   If pPlatform(cyc)=0 Then pFlyAgenda(cyc)=2 : pExcusedWorld(cyc)=1 
   If pY#(cyc)=<pGround#(cyc) Then pY#(cyc)=pGround#(cyc)+0.1
   pExcusedPlays(cyc)=1 : pExcusedEdges(cyc)=1
   ResetMultiSting(cyc) : pSting(cyc)=1
   pPlatform(cyc)=0
  EndIf
  ;flight physics
  If pAnimTim(cyc)=Int(attackTransition(style,move)/pAnimSpeed#(cyc)) 
   ProduceSound(p(cyc),sSwing,20000,1) : Pop(0,Rnd(2,9),1)
   If EntertainViable(cyc,0) Then entScore=entScore+pAgility(cyc)
  EndIf 
  If pAnimTim(cyc)>Int(attackTransition(style,move)/pAnimSpeed#(cyc))
   pTravel#(cyc)=pTravel#(cyc)-(pTravel#(cyc)/Int(attackMomentum(style,move)/pAnimSpeed#(cyc)))
   If pFoc(cyc)>0 And GetDistance#(pX#(cyc),pZ#(cyc),pX#(pFoc(cyc)),pZ#(pFoc(cyc)))<attackRange#(style,move)+(attackRange#(style,move)/2) 
    pTravel#(cyc)=pTravel#(cyc)-(pTravel#(cyc)/attackTravel#(style,move))
   EndIf
   If pTravel#(cyc)<0 Then pTravel#(cyc)=0
   Advance(cyc,pFlightA#(cyc),pTravel#(cyc))
  EndIf
  ;gravity
  If pAnimTim(cyc)>Int(attackTransition(style,move)/pAnimSpeed#(cyc))
   If pY#(cyc)>pGround#(cyc)
    ;velocity#=0.15+PercentOf#(0.16,pAgility(cyc))
    pGravity#(cyc)=pGravity#(cyc)-pVelocity#(cyc)
    If BlockConflict(pX#(cyc),pY#(cyc)-1,pZ#(cyc),0)
     If pTravel#(cyc)<0.5 Then pTravel#(cyc)=0.5 
     ;If pGravity#(cyc)<0 Then pGravity#(cyc)=0
    EndIf
    pY#(cyc)=pY#(cyc)+pGravity#(cyc)
   EndIf
   FlightCorrection(cyc,0)
  EndIf
  ;impacts
  If pAnimTim(cyc)=>Int(attackActive(style,move)/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(attackExpire(style,move)/pAnimSpeed#(cyc))
   peakRange#=attackRange#(style,move)+PercentOf#(PercentOf#(attackRange#(style,move),25),GetPercent#(charHeight(pChar(cyc)),24))
   For v=1 To no_plays
    ;versus standing
    If cyc<>v And pY#(cyc)=>pY#(v)-TranslateHeight#(cyc,30) And pMultiSting(cyc,v)=1
     If (pY#(cyc)=<pY#(v)+TranslateHeight#(v,attackHeight#(style,move)) And AttackViable(v)=1) Or (pY#(cyc)=<pY#(v)+(TranslateHeight#(v,attackHeight#(style,move))/2) And AttackViable(v)=2)
      If (InProximity(cyc,v,peakRange#) And InLine(cyc,pLimb(v,36),30)) Or InProximity(cyc,v,10)
       ImpactChecks(v)
       Pop(cyc,Rnd(2,8),1) : Pop(cyc,9,Rnd#(0.1,1.0))
       ProduceSound(p(v),sImpact(Rnd(5,7)),22050,1) 
       ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),1)
       impactY#=pY#(v)+TranslateHeight#(v,28)
       If impactY#>pLimbY#(v,1) Then impactY#=pLimbY#(v,1)
       ImpactSpurt(pX#(v),impactY#,pZ#(v),1,1)
       BloodSpurt(pX#(v),impactY#,pZ#(v),pA#(cyc),pScar(v,1)-1,1)
       ScarArea(v,pX#(v),impactY#,pZ#(v),25)
       ChangeAnim(v,Rnd(92,95))
       pain=PercentOf#(attackPower(style,move),pStrength(cyc))
       If pSpecial(cyc)>0 Then pain=pain+(pain/4) : pSpecial(cyc)=pSpecial(cyc)-(pSpecial(cyc)/10)
       pHealth(v)=pHealth(v)-pain : pDT(v)=GetDT(v,PercentOf#(pain,125))
       pHP(v)=0 : pHurtA#(v)=pFlightA#(cyc)
       pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-5
       If EntertainViable(cyc,v) Then entScore=entScore+pain
       If attackWeapon(style,move)>0 Then WeaponImpact(cyc,v,2)
       pMultiSting(cyc,v)=0 : pSting(cyc)=0 : pFoc(v)=cyc
       ClockIntruder(cyc,v)
       ClockAbuse(cyc,v,1)
       pTravel#(cyc)=pTravel#(cyc)/2
      EndIf
     EndIf
    EndIf
    ;versus grounded
    If cyc<>v And pY#(cyc)=>pY#(v) And GettingUp(v)=0 And pMultiSting(cyc,v)=1
     If (pY#(cyc)<pY#(v)+8 And AttackViable(v)=3) Or (pY#(cyc)<pY#(v)+4 And AttackViable(v)=4)
      ScanBody(v)
      For scan=0 To 6
       If ReachedCord(pX#(cyc),pZ#(cyc),pScanX#(v,scan),pScanZ#(v,scan),8) And pMultiSting(cyc,v)=1
        GroundImpactChecks(v)
        Pop(cyc,Rnd(2,9),1)
        ProduceSound(p(v),sBlock(7),22050,1) 
        ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),1)
        ImpactSpurt(pX#(cyc),pY#(cyc),pZ#(cyc),1,1)
        BloodSpurt(pX#(cyc),pY#(cyc),pZ#(cyc),-1,FindBlood(v,pX#(cyc),pY#(cyc),pZ#(cyc))-1,0)
        ScarArea(v,pX#(cyc),pY#(cyc),pZ#(cyc),50) 
        GroundReaction(v) : pHurtTim(v)=10
        ResetItemStings(v) : FindSmashes(v,pY#(v),1)
        pHealth(v)=pHealth(v)-TranslateWeight(pChar(cyc))
        pHP(cyc)=0 : pHeat(v)=pHeat(v)-1
        If EntertainViable(cyc,v) Then entScore=entScore+TranslateWeight(pChar(cyc))
        pMultiSting(cyc,v)=0
        pTravel#(cyc)=pTravel#(cyc)/2 : pGravity#(cyc)=0
       EndIf
      Next
     EndIf
    EndIf
   Next
   ;item smashes
   FindSmashes(cyc,pY#(cyc),1)
  EndIf
  ;landing
  If pY#(cyc)<pGround#(cyc)
   ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
   ProduceSound(p(cyc),sFall,22050,1) : ProduceSound(p(cyc),sThud,22050,1)
   If InsideRing(RealX#(cyc),RealZ#(cyc),-5) Then ShakeRing(0,3.0)
   FindSmashes(cyc,pY#(cyc),1)
   RiskInjury(cyc,Rnd(0,5),10)
   pHealth(cyc)=pHealth(cyc)-100
   If EntertainViable(cyc,0) Then entScore=entScore+125
   pTravel#(cyc)=pTravel#(cyc)/2
   pY#(cyc)=pGround#(cyc)
   If pSting(cyc)=1
    ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),1)
    ProduceSound(p(cyc),sBlock(7),20000,0)
    RiskInjury(cyc,Rnd(0,5),1)
    pHealth(cyc)=pHealth(cyc)-250 : pHP(cyc)=0 : pHurtA#(cyc)=Rnd(0,360)
    pHeat(cyc)=pHeat(cyc)-5
   EndIf
   If game=1 And attackLearned(style,move)<1 Then attackLearned(style,move)=1
  EndIf 
  If pY#(cyc)=<pGround#(cyc) Then DropWeapon(cyc,10)
  ;ending
  crash=0
  If pHP(cyc)=0 And pY#(cyc)=pGround#(cyc)
   If attackFall(style,move)=0 Then crash=1
   If attackFall(style,move)>0 And pAnimTim(cyc)>Int((attackLength(style,move)-15)/pAnimSpeed#(cyc)) Then crash=1
  EndIf
  If pAnimTim(cyc)=>Int(attackLength(style,move)/pAnimSpeed#(cyc)) Or crash=1
   If pY#(cyc)=pGround#(cyc) Or pAnimTim(cyc)=>Int(attackLength(style,move)/pAnimSpeed#(cyc))*2
    anim=0
    If pSting(cyc)=0 And pSpecial(cyc)=0
     If attackLimb(style,move)=1 And pInjured(cyc,1)>0 Then anim=111
     If attackLimb(style,move)=1 And pInjured(cyc,2)>0 Then anim=112
     If pInjured(cyc,4)>0 Then anim=114 
    EndIf
    ChangeAnim(cyc,anim)
    pY#(cyc)=pGround#(cyc)
    FlightCorrection(cyc,1)
    ResetExcuses(cyc,0)
   EndIf
  EndIf
  pHealth(cyc)=pHealth(cyc)-1
 EndIf
 ;CROUCHING ATTACK
 If pAnim(cyc)=65
  If pAnimTim(cyc)=0
   factor#=50+PercentOf#(50,pSkill(cyc))
   If factor#<75 Then factor#=75
   pAnimSpeed#(cyc)=PercentOf#(3.25,factor#)
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,65),20/pAnimSpeed#(cyc)
   pTravel#(cyc)=0.1 : pStagger#(cyc)=0
   pSting(cyc)=1
  EndIf
  AttackMovement(cyc,55)
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,22000,PercentOf#(0.5,pStrength(cyc)))
  If pAnimTim(cyc)=>Int(30/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(45/pAnimSpeed#(cyc)) And pSting(cyc)=1 And pScar(cyc,20)=<4
   FindAttackImpacts(cyc,25,35,45,18,15,150,1,1)
  EndIf
  If pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) And pSting(cyc)=1 Then pHeat(cyc)=pHeat(cyc)-1
  If pAnimTim(cyc)>Int(45/pAnimSpeed#(cyc)) And pSting(cyc)=0 Then DropWeapon(cyc,500)
  If pAnimTim(cyc)>Int(70/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,156)
  pHealth(cyc)=pHealth(cyc)-1
 EndIf
 ;SPIT ATTACK
 If pAnim(cyc)=66
  pAnimSpeed#(cyc)=2.75 : transition=30
  If pAnimTim(cyc)=0
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,66),transition/pAnimSpeed#(cyc)
   pTravel#(cyc)=0.1
   ResetMultiSting(cyc)
  EndIf
  AttackMovement(cyc,transition+45)
  If pAnimTim(cyc)=Int(transition/pAnimSpeed#(cyc)) Then Pop(cyc,Rnd(2,8),0.5)
  If pAnimTim(cyc)=Int((transition+10)/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.25)
  If pAnimTim(cyc)=Int((transition+15)/pAnimSpeed#(cyc))
   If pSpurt(cyc)=1 Or pSpurt(cyc)=9 Then ProduceSound(p(cyc),sIgnite,22050,1)
   If pSpurt(cyc)<>1 And pSpurt(cyc)<>9 Then ProduceSound(p(cyc),sIgnite,22050,0.5)
   If pSpurt(cyc)=8 Or pSpurt(cyc)=12 Then ProduceSound(p(cyc),sSplash,22050,0.5)
  EndIf
  If pAnimTim(cyc)=>Int((transition+15)/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int((transition+50)/pAnimSpeed#(cyc))
   CreateSpit(cyc,Int((transition+15)/pAnimSpeed#(cyc)),Int((transition+25)/pAnimSpeed#(cyc)),pSpurt(cyc))
  EndIf
  If pAnimTim(cyc)=>Int((transition+15)/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int((transition+45)/pAnimSpeed#(cyc))
   FindSpitImpacts(cyc,Int((transition+15)/pAnimSpeed#(cyc)),Int((transition+25)/pAnimSpeed#(cyc)),26,pSpurt(cyc))
  EndIf
  If pAnimTim(cyc)>Int((transition+60)/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,0)
  If game=1 And pSpurt(cyc)=11 And moveLearned(1,5)<1 Then moveLearned(1,5)=1
  If game=1 And pSpurt(cyc)=1 And moveLearned(1,6)<1 Then moveLearned(1,6)=1
  pHealth(cyc)=pHealth(cyc)-1
  If EntertainViable(cyc,0) Then entScore=entScore+1
 EndIf
 ;DUST THROW
 If pAnim(cyc)=67
  pAnimSpeed#(cyc)=2.75 : transition=30
  If pAnimTim(cyc)=0
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,67),transition/pAnimSpeed#(cyc)
   pTravel#(cyc)=0.1
   ResetMultiSting(cyc)
  EndIf
  AttackMovement(cyc,transition+40)
  If pAnimTim(cyc)=Int(transition/pAnimSpeed#(cyc)) Then Pop(cyc,Rnd(2,8),0.5)
  If pAnimTim(cyc)=Int((transition+5)/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,20000,0.5)
  If pAnimTim(cyc)=Int((transition+10)/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sIgnite,22050,0.25)
  If pAnimTim(cyc)=>Int((transition)/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int((transition+25)/pAnimSpeed#(cyc))
   density=10 : spread#=3.0
   If pAnimTim(cyc)<Int((transition+15)/pAnimSpeed#(cyc)) Then density=2 : spread#=1.0
   CreateSpurt(pLimbX#(cyc,8),pLimbY#(cyc,8),pLimbZ#(cyc,8),pA#(cyc),spread#,1,4)
   CreateSpurt(pLimbX#(cyc,8),pLimbY#(cyc,8),pLimbZ#(cyc,8),pA#(cyc),spread#,density,5)
   CreateSpurt(pLimbX#(cyc,8),pLimbY#(cyc,8),pLimbZ#(cyc,8),pA#(cyc),spread#,density,7)
  EndIf
  If pAnimTim(cyc)=>Int((transition+15)/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int((transition+35)/pAnimSpeed#(cyc))
   FindSpitImpacts(cyc,Int((transition+15)/pAnimSpeed#(cyc)),Int((transition+25)/pAnimSpeed#(cyc)),24,7)
  EndIf
  If pAnimTim(cyc)>Int((transition+55)/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,0)
  If game=1 And moveLearned(1,4)<1 Then moveLearned(1,4)=1
  pHealth(cyc)=pHealth(cyc)-1
  If EntertainViable(cyc,0) Then entScore=entScore+1
 EndIf
 ;BASEBALL SLIDE
 If pAnim(cyc)=68
  pAnimSpeed#(cyc)=2.5 : transition=15
  If pAnimTim(cyc)=0
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,68),transition/pAnimSpeed#(cyc)
   pSting(cyc)=1
  EndIf
  If pAnimTim(cyc)<Int((transition+90)/pAnimSpeed#(cyc)) Then Advance(cyc,pA#(cyc),pSpeed#(cyc)*2)
  If pAnimTim(cyc)=Int((transition+10)/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,22000,1.0)
  If pAnimTim(cyc)=Int((transition+35)/pAnimSpeed#(cyc))
   Pop(0,Rnd(2,9),0)
   MoveFallEffect(cyc)
   FindSmashes(cyc,pY#(cyc),0)
   If TouchingRopes(cyc) Then ShakeRopes(pX#(cyc),pZ#(cyc),20,1)
  EndIf
  If pScar(cyc,40)=<4 Or pScar(cyc,44)=<4
   If pAnimTim(cyc)=>Int((transition+30)/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int((transition+60)/pAnimSpeed#(cyc)) And pSting(cyc)=1
    FindAttackImpacts(cyc,transition+30,transition+40,transition+60,25,1,500,0,4)
   EndIf
  EndIf
  If pAnimTim(cyc)=Int((transition+60)/pAnimSpeed#(cyc)) And pSting(cyc)=1 Then pHeat(cyc)=pHeat(cyc)-1
  If pAnimTim(cyc)=Int((transition+50)/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int((transition+100)/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0.5)
  If pAnimTim(cyc)=Int((transition+75)/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99 
  If pAnimTim(cyc)>Int((transition+95)/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,154) : SharpTransition(cyc,154,0,180)
  DropWeapon(cyc,1000)
  pHealth(cyc)=pHealth(cyc)-1
  If EntertainViable(cyc,0) Then entScore=entScore+1
 EndIf
 ;HIND KICK
 If pAnim(cyc)=69
  transition=15
  If pAnimTim(cyc)=0
   factor#=50+PercentOf#(50,pAgility(cyc))
   pAnimSpeed#(cyc)=PercentOf#(2.5,factor#)
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,69),transition/pAnimSpeed#(cyc)
   pTravel#(cyc)=0 : pStagger#(cyc)=0.5
   pSting(cyc)=1
  EndIf
  AttackMovement(cyc,transition+35)
  If pAnimTim(cyc)=Int(transition/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,22000,PercentOf#(0.5,pStrength(cyc)))
  If pAnimTim(cyc)=>Int((transition+5)/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int((transition+25)/pAnimSpeed#(cyc)) And pSting(cyc)=1 And pScar(cyc,43)=<4
   FindAttackImpacts(cyc,transition+5,transition+15,transition+25,20,15,300,0,2)
  EndIf
  If pAnimTim(cyc)=Int((transition+25)/pAnimSpeed#(cyc)) And pSting(cyc)=1 Then pHeat(cyc)=pHeat(cyc)-1
  If pAnimTim(cyc)=Int((transition+35)/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)>Int((transition+50)/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,154)
  DropWeapon(cyc,1000) 
  pHealth(cyc)=pHealth(cyc)-1
 EndIf
End Function

;-----------------------------------------------------------------
;/////////////////// GROUND ATTACK ANIMATIONS ////////////////////
;-----------------------------------------------------------------
Function CrushAnims(cyc)
 ;CRUSHING PROCESS
 If pAnim(cyc)=>70 And pAnim(cyc)=<73
  style=pAnim(cyc)-69
  move=charCrush(pChar(cyc),style)
  ;initiation
  If pAnimTim(cyc)=0
   factor#=50+PercentOf#(50,pSkill(cyc))
   If crushFall(style,move)>0 Or crushLimb(style,move)=>36 Then factor#=50+PercentOf#(50,pAgility(cyc))
   If factor#<75 Then factor#=75
   pAnimSpeed#(cyc)=PercentOf#(crushSpeed#(style,move),factor#)
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,pAnim(cyc)),crushTransition(style,move)/pAnimSpeed#(cyc)
   factor#=50+PercentOf#(50,pAgility(cyc))
   pTravel#(cyc)=PercentOf#(crushTravel#(style,move),factor#)
   If pAnim(cyc)=73 Then pTravel#(cyc)=pSpeed#(cyc)*2
   pHurtDelay(cyc)=0 : pDelayAnim(cyc)=0
   pStagger#(cyc)=0 : pSting(cyc)=1
  EndIf
  ;travelling
  If crushName$(style,move)="Senton Bomb" Or crushName$(style,move)="Moonsault" Then flyover=1 Else flyover=0
  pTravel#(cyc)=pTravel#(cyc)-(pTravel#(cyc)/Int(crushMomentum(style,move)/pAnimSpeed#(cyc)))
  If pSting(cyc)=0 And flyover=0 Then pTravel#(cyc)=pTravel#(cyc)/2
  If pTravel#(cyc)<0 Then pTravel#(cyc)=0
  If InProximity(cyc,pFoc(cyc),10)=0 Or pFoc(cyc)=0 Or flyover=1 Then Advance(cyc,pA#(cyc),pTravel#(cyc))
  If pTravel#(cyc)>0.1 Then pStepTim#(cyc)=pStepTim#(cyc)+1
  ;swing
  If pAnimTim(cyc)=Int(crushImpactLow(style,move)/pAnimSpeed#(cyc))/2
   If style=<2 Then ProduceSound(p(cyc),sSwing,22000,PercentOf#(0.5,pStrength(cyc)))
   If style=>3 Then ProduceSound(p(cyc),sSwing,20000,PercentOf#(0.75,pStrength(cyc)))
  EndIf
  ;find impacts
  intact=1
  If pAnim(cyc)=<71 And pScar(cyc,43)=5 Then intact=0 
  If intact=1 And (pAnimTim(cyc)=Int(crushImpactHigh(style,move)/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(crushImpactLow(style,move)/pAnimSpeed#(cyc)))
   range#=crushRange#(style,move)+PercentOf#(PercentOf#(crushRange#(style,move),25),GetPercent#(charHeight(pChar(cyc)),24))
   ProjectDummy(cyc,0,0,range#)
   impactX#=EntityX(dummy) : impactZ#=EntityZ(dummy)
   For v=1 To no_plays
    impact=0
    If pAnimTim(cyc)=Int(crushImpactLow(style,move)/pAnimSpeed#(cyc)) And AttackViable(v)=>2 And AttackViable(v)=<3 Then impact=2
    If pAnimTim(cyc)=Int(crushImpactLow(style,move)/pAnimSpeed#(cyc)) And AttackViable(v)=>3 And AttackViable(v)=<4 Then impact=1
    If impact>0
     If cyc<>v And pY#(cyc)>pY#(v)-5 And pY#(cyc)<pY#(v)+5 And GettingUp(v)=0 And pSting(cyc)=1
      ScanBody(v)
      For scan=0 To 6
       If (ReachedCord(impactX#,impactZ#,pScanX#(v,scan),pScanZ#(v,scan),8) Or ReachedCord(pX#(cyc),pZ#(cyc),pScanX#(v,scan),pScanZ#(v,scan),range#)) And pSting(cyc)=1
        GroundImpactChecks(v)
        ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),Rnd(0.25,1.0))
        If style=>1 And style=<2 Then ProduceSound(p(cyc),sBlock(Rnd(1,4)),22050,0) : Pop(cyc,Rnd(2,7),Rnd(0.1,0.5))
        If style=>3 And style=<4 Then ProduceSound(p(cyc),sBlock(Rnd(5,7)),22050,1) : Pop(cyc,Rnd(2,9),Rnd(0.5,1.0))
        If impact=2 Then impactY#=pY#(cyc)+5 Else impactY#=pY#(cyc)+2
        If style=<2
         impactX#=pLimbX#(cyc,crushLimb(style,move)) : impactZ#=pLimbZ#(cyc,crushLimb(style,move)) 
         If crushName$(style,move)="Kick" 
          ProjectDummy(cyc,0,0,8)
          impactX#=EntityX(dummy) : impactZ#=EntityZ(dummy)
         EndIf
         ImpactSpurt(impactX#,impactY#,impactZ#,1,1)
         BloodSpurt(impactX#,impactY#,impactZ#,-1,FindBlood(v,impactX#,impactY#,impactZ#)-1,0)
         ScarArea(v,impactX#,impactY#,impactZ#,50)
        Else
         ImpactSpurt(pX#(v),impactY#,pZ#(v),1,1)
         BloodSpurt(pX#(v),impactY#,pZ#(v),-1,FindBlood(v,pX#(v),impactY#,pZ#(v))-1,0)
         ScarArea(v,pX#(v),impactY#,pZ#(v),25)
        EndIf
        If AttackViable(v)=<2 Then ChangeAnim(v,Rnd(102,105))
        If AttackViable(v)=>3 Then GroundReaction(v)
        If style=>3 Then pHurtTim(v)=10 Else pHurtTim(v)=5
        If crushFall(style,move)>0 Then ResetItemStings(v) : FindSmashes(v,pY#(v),1)
        pain=PercentOf#(crushPower(style,move),pStrength(cyc))
        pHealth(v)=pHealth(v)-pain : pDT(v)=pDT(v)-(pDT(v)/10)
        If style=>1 And style=<2 Then pHeat(cyc)=pHeat(cyc)+Rnd(0,1) : pHeat(v)=pHeat(v)-1
        If style=>3 And style=<4 Then pHeat(cyc)=pHeat(cyc)+1 : pHeat(v)=pHeat(v)-2
        If EntertainViable(cyc,v) Then entScore=entScore+(pain/2)
        If crushWeapon(style,move)>0 Then WeaponImpact(cyc,v,1)
        ClockIntruder(cyc,v)
        ClockAbuse(cyc,v,1)
        If pWarned(v)<>cyc Then pFoc(v)=cyc
        pSting(cyc)=0
       EndIf
      Next
     EndIf
    EndIf
   Next
  EndIf
  ;failed attempt
  If pAnimTim(cyc)=Int(crushImpactLow(style,move)/pAnimSpeed#(cyc))
   ProduceSound(p(cyc),sThud,22050,0.5)
   pStepTim#(cyc)=99
   If pSting(cyc)=1
    If style=>1 And style=<2 Then pHeat(cyc)=pHeat(cyc)-Rnd(0,1)
    If style=>3 And style=<4 Then pHeat(cyc)=pHeat(cyc)-1
   EndIf
  EndIf
  ;landing
  If crushFall(style,move)>0
   If pAnimTim(cyc)=>Int(crushImpactHigh(style,move)/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(crushFall(style,move)/pAnimSpeed#(cyc)) Then FindSmashes(cyc,pLimbY#(cyc,36),1)
   If pAnimTim(cyc)=Int(crushFall(style,move)/pAnimSpeed#(cyc))
    ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
    ProduceSound(p(cyc),sFall,22050,1)
    ProduceSound(p(cyc),sThud,22050,0)
    If InsideRing(RealX#(cyc),RealZ#(cyc),-5) Then ShakeRing(0,1.0)
    FindSmashes(cyc,pY#(cyc),1)
    If pSting(cyc)=1 
     pHealth(cyc)=pHealth(cyc)-100 : pHP(cyc)=pHP(cyc)-100 : pHurtA#(cyc)=Rnd(0,360)
     If EntertainViable(cyc,0) Then entScore=entScore+100
    EndIf
    pTravel#(cyc)=pTravel#(cyc)/2
   EndIf
   If pAnimTim(cyc)>Int(crushFall(style,move)/pAnimSpeed#(cyc)) Then DropWeapon(cyc,500)
  EndIf 
  ;end
  If pAnimTim(cyc)=>Int(crushLength(style,move)/pAnimSpeed#(cyc))
   anim=0
   If pSting(cyc)=0 And pSpecial(cyc)=0
    randy=Rnd(0,5)
    If randy=0 And crushLimb(style,move)=1 And pInjured(cyc,1)>0 Then anim=111
    If randy=1 And crushLimb(style,move)=1 And pInjured(cyc,2)>0 Then anim=112
    If randy=2 And crushLimb(style,move)>1 And pInjured(cyc,4)>0 Then anim=114 
   EndIf
   ChangeAnim(cyc,anim)
   If crushName$(style,move)="Moonsault" Then SharpTransition(cyc,0,1,180)
   If game=1 And crushLearned(style,move)<1 Then crushLearned(style,move)=1
   If game=1 And pAnim(cyc)=72 And crushLearned(style+1,move)<1 Then crushLearned(style+1,move)=1
   If game=1 And pAnim(cyc)=73 And crushLearned(style-1,move)<1 Then crushLearned(style-1,move)=1
  EndIf 
  ;switch to weapon version
  If pAnim(cyc)=>70 And pAnim(cyc)=<71 And pHolding(cyc)>0 And weapWear(pHolding(cyc))=0
   If weapHold(weapType(pHolding(cyc)))=<1 Then ChangeAnim(cyc,227)
   If weapHold(weapType(pHolding(cyc)))=2 Then ChangeAnim(cyc,228)
   If weapHold(weapType(pHolding(cyc)))=3 Then ChangeAnim(cyc,229)
  EndIf
  pHealth(cyc)=pHealth(cyc)-1
 EndIf
 ;FLYING CRUSH PROCESS
 If pAnim(cyc)=74
  style=pAnim(cyc)-69
  move=charCrush(pChar(cyc),style)
  If InsideRing(pX#(cyc),pZ#(cyc),-15) Then pGround#(cyc)=wStage# Else pGround#(cyc)=wGround#
  ;initiation
  If pAnimTim(cyc)=0
   If pPlatform(cyc)=0 Then pAnimSpeed#(cyc)=2.0 Else pAnimSpeed#(cyc)=2.25+((23.0-LeapHeight#(cyc,50))/60)
   crushTransition(style,move)=20
   If pPlatform(cyc)=0 Then crushTransition(style,move)=10
   If pPlatform(cyc)=>5 And pPlatform(cyc)=<8 Then crushTransition(style,move)=15
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,pAnim(cyc)),crushTransition(style,move)/pAnimSpeed#(cyc)
   GetFlightPhysics(cyc,pFoc(cyc))
   forecast#=50
   If pPlatform(cyc)=>100
    forecast#=iHeight#(iType(pPlatform(cyc)-100))*3
    If forecast#>50 Then forecast#=50
   EndIf
   pFlightA#(cyc)=pA#(cyc) : pFlyAgenda(cyc)=GetFlyAgenda(cyc,forecast#)
   If pPlatform(cyc)=0 Then pFlyAgenda(cyc)=6 : pExcusedWorld(cyc)=1 
   If pY#(cyc)=<pGround#(cyc) Then pY#(cyc)=pGround#(cyc)+0.1
   pExcusedPlays(cyc)=1 : pExcusedEdges(cyc)=1
   ResetMultiSting(cyc) : pSting(cyc)=1
   pPlatform(cyc)=0
  EndIf
  ;travelling
  If pAnimTim(cyc)=Int(crushTransition(style,move)/pAnimSpeed#(cyc))
   ProduceSound(p(cyc),sSwing,20000,1) : Pop(0,Rnd(2,9),1)
   If EntertainViable(cyc,0) Then entScore=entScore+pAgility(cyc)
  EndIf
  If pAnimTim(cyc)=<Int(crushTransition(style,move)/pAnimSpeed#(cyc)) And crushTransition(style,move)<20
   If InsideRing(pX#(cyc),pZ#(cyc),-20) Then Advance(cyc,pA#(cyc),pSpeed#(cyc))
  EndIf
  If pAnimTim(cyc)>Int(crushTransition(style,move)/pAnimSpeed#(cyc))
   pTravel#(cyc)=pTravel#(cyc)-(pTravel#(cyc)/Int((crushTransition(style,move)+80)/pAnimSpeed#(cyc)))
   If pFoc(cyc)>0 And GetDistance#(pX#(cyc),pZ#(cyc),pX#(pFoc(cyc)),pZ#(pFoc(cyc)))<15 Then pTravel#(cyc)=pTravel#(cyc)-(pTravel#(cyc)/crushTravel#(style,move))
   If pTravel#(cyc)<0 Then pTravel#(cyc)=0
   Advance(cyc,pFlightA#(cyc),pTravel#(cyc))
  EndIf
  ;gravity
  If pAnimTim(cyc)>Int(crushTransition(style,move)/pAnimSpeed#(cyc))
   If pY#(cyc)>pGround#(cyc)
    ;velocity#=0.15+PercentOf#(0.16,pAgility(cyc))
    pGravity#(cyc)=pGravity#(cyc)-pVelocity#(cyc)
    If BlockConflict(pX#(cyc),pY#(cyc)-1,pZ#(cyc),0)
     If pTravel#(cyc)<0.5 Then pTravel#(cyc)=0.5
     ;If pGravity#(cyc)<0 Then pGravity#(cyc)=0
    EndIf
    pY#(cyc)=pY#(cyc)+pGravity#(cyc)
   EndIf
   FlightCorrection(cyc,0)
  EndIf
  ;impacts
  If pAnimTim(cyc)=>Int((crushTransition(style,move)+30)/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int((80+crushTransition(style,move))/pAnimSpeed#(cyc))
   For v=1 To no_plays
    ;versus standing 
    If cyc<>v And pLimbY#(cyc,36)>pY#(v) And pMultiSting(cyc,v)=1
     If (pLimbY#(cyc,36)<pY#(v)+40 And AttackViable(v)=1) Or (pLimbY#(cyc,36)<pY#(v)+30 And AttackViable(v)=2)
      If InProximity(cyc,v,10) Or (InLine(cyc,pLimb(v,36),45) And InProximity(cyc,v,15))
       ImpactChecks(v)
       Pop(cyc,Rnd(2,8),1)
       ProduceSound(p(v),sImpact(Rnd(5,7)),22050,0) 
       ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),0)
       ;ImpactSpurt(pX#(v),pLimbY#(cyc,36),pZ#(v),1,1)
       ;BloodSpurt(pX#(v),pLimbY#(cyc,36),pZ#(v),-1,pScar(v,1)-1,0)
       ScarArea(v,pX#(v),pLimbY#(cyc,36),pZ#(v),50)
       ChangeAnim(v,Rnd(92,95))
       pain=PercentOf#(crushPower(style,move),pStrength(cyc))/2
       pHealth(v)=pHealth(v)-pain : pHP(v)=0 : pDT(v)=GetDT(v,pain)
       pHurtA#(v)=pFlightA#(cyc) : pHeat(v)=pHeat(v)-1
       If EntertainViable(cyc,v) Then entScore=entScore+pain
       WeaponImpact(cyc,v,1)
       pMultiSting(cyc,v)=0 : pSting(cyc)=0
       pTravel#(cyc)=pTravel#(cyc)/2 : pGravity#(cyc)=0
       randy=Rnd(1,2)
       If randy=1 Then pFlightA#(cyc)=pFlightA#(cyc)-25 : pA#(cyc)=pA#(cyc)-25
       If randy=2 Then pFlightA#(cyc)=pFlightA#(cyc)+25 : pA#(cyc)=pA#(cyc)+25
      EndIf
     EndIf
    EndIf 
    ;versus grounded
    If cyc<>v And pY#(cyc)=>pY#(v) And GettingUp(v)=0 And pMultiSting(cyc,v)=1
     If (pY#(cyc)<pY#(v)+12 And AttackViable(v)=3) Or (pY#(cyc)<pY#(v)+5 And AttackViable(v)=4)
      ProjectDummy(cyc,0,0,range#)
      impactX#=EntityX(dummy) : impactZ#=EntityZ(dummy)
      ScanBody(v)
      For scan=0 To 6
       If (ReachedCord(impactX#,impactZ#,pScanX#(v,scan),pScanZ#(v,scan),10) Or ReachedCord(pX#(cyc),pZ#(cyc),pScanX#(v,scan),pScanZ#(v,scan),10)) And pMultiSting(cyc,v)=1 
        GroundImpactChecks(v)
        Pop(cyc,Rnd(2,8),1) : Pop(cyc,9,Rnd#(0.1,1.0))
        ProduceSound(p(v),sBlock(7),22050,1) 
        ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),1)
        ;ImpactSpurt(pX#(cyc),pY#(v)+3,pZ#(cyc),1,1)
        ;BloodSpurt(pX#(cyc),pY#(v)+3,pZ#(cyc),-1,FindBlood(v,pX#(cyc),pY#(v)+3,pZ#(cyc))-1,0)
        ScarArea(v,pX#(cyc),pY#(v),pZ#(cyc),25) 
        RiskInjury(v,Rnd(0,5),10)
        GroundReaction(v) : pHurtTim(v)=20
        ResetItemStings(v) : FindSmashes(v,pY#(v),1)
        pain=PercentOf#(crushPower(style,move),pStrength(cyc))
        If pSpecial(cyc)>0 Then pain=pain+(pain/4) : pSpecial(cyc)=pSpecial(cyc)-(pSpecial(cyc)/10)
        pHealth(v)=pHealth(v)-pain : pDT(v)=GetDT(v,PercentOf#(pain,125))
        If pDT(v)<250 Then pDT(v)=250
        pHeat(cyc)=pHeat(cyc)+5 : pHeat(v)=pHeat(v)-5
        If EntertainViable(cyc,v) Then entScore=entScore+pain 
        If crushWeapon(style,move)>0 Then WeaponImpact(cyc,v,2)
        pMultiSting(cyc,v)=0 : pSting(cyc)=0 : pFoc(v)=cyc
        ClockIntruder(cyc,v)
        ClockAbuse(cyc,v,1) 
        If crushFall(style,move)=2 Or crushFall(style,move)=5 Then pTravel#(cyc)=pTravel#(cyc)/2 
        pGravity#(cyc)=0
       EndIf
      Next
     EndIf
    EndIf
   Next
   ;item smashes
   FindSmashes(cyc,pY#(cyc),1)
  EndIf
  ;landing
  If pY#(cyc)<pGround#(cyc)
   ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
   ProduceSound(p(cyc),sFall,22050,1) : ProduceSound(p(cyc),sThud,22050,1)
   If InsideRing(RealX#(cyc),RealZ#(cyc),-5) Then ShakeRing(0,3.0)
   ScarArea(cyc,0,0,0,20) : RiskInjury(cyc,Rnd(0,5),10) 
   pHealth(cyc)=pHealth(cyc)-100 
   If EntertainViable(cyc,0) Then entScore=entScore+125
   pTravel#(cyc)=pTravel#(cyc)/2
   pY#(cyc)=pGround#(cyc)
   If pSting(cyc)=1
    ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),1)
    ProduceSound(p(cyc),sBlock(7),20000,0)
    ScarArea(cyc,0,0,0,10) : RiskInjury(cyc,Rnd(0,5),1)
    pHealth(cyc)=pHealth(cyc)-500 
    pDT(cyc)=GetDT(cyc,250) : pHeat(cyc)=pHeat(cyc)-5
    pTravel#(cyc)=pTravel#(cyc)/2
   EndIf
   If game=1 And crushLearned(style,move)<1 Then crushLearned(style,move)=1
  EndIf 
  DropWeapon(cyc,100)
  If pY#(cyc)=<pGround#(cyc) Then DropWeapon(cyc,0)
  ;ending
  If pAnimTim(cyc)=>Int((crushTransition(style,move)+crushLength(style,move))/pAnimSpeed#(cyc))
   If pY#(cyc)=pGround#(cyc) Or pAnimTim(cyc)=>Int(crushLength(style,move)/pAnimSpeed#(cyc))*2
    If crushFall(style,move)=0 Then ChangeAnim(cyc,0)
    If crushFall(style,move)=1 Then ChangeAnim(cyc,150)
    If crushFall(style,move)=2 Then ChangeAnim(cyc,152)
    If crushFall(style,move)=3 Then ChangeAnim(cyc,154) : SharpTransition(cyc,154,1,270)
    If crushFall(style,move)=4 Then ChangeAnim(cyc,156)
    If crushFall(style,move)=5 Then ChangeAnim(cyc,150) : SharpTransition(cyc,150,1,180)
    If crushFall(style,move)=6 Then ChangeAnim(cyc,152) : SharpTransition(cyc,152,1,180)
    pY#(cyc)=pGround#(cyc)
    FlightCorrection(cyc,1)
    ResetExcuses(cyc,0)
   EndIf
  EndIf
  pHealth(cyc)=pHealth(cyc)-1 
  ;alert victim
  randy=Rnd(0,100)
  If randy=<1 And pFoc(cyc)>0 And pAnimTim(cyc)<Int((crushTransition(style,move)+30)/pAnimSpeed#(cyc)) Then pDT(pFoc(cyc))=0
 EndIf
End Function

;--------------------------------------------------------------
;////////////////// RELATED FUNCTIONS /////////////////////////
;--------------------------------------------------------------

;BLOCK ATTACK?
Function RiskBlocking(cyc,v,weapon)
 value=0
 If InLine(v,p(cyc),45) And pAnim(v)=>80 And pAnim(v)=<89
  blockChance=(pStrength(v)+pToughness(v))*4
  breakChance=(pStrength(cyc)-25)
  If pAnim(cyc)=62 Or pAnim(cyc)=63 Then breakChance=breakChance*2
  If weapon>0 Then breakChance=breakChance*2
  If pHolding(v)>0 Then blockChance=blockChance*2
  If pSpecial(v)>0 Then blockChance=blockChance*4
  If pSpecial(cyc)>0 Then breakChance=breakChance*4
  randy=Rnd(-blockChance,breakChance)
  If randy=<0 Then value=1
 EndIf
 Return value
End Function

;RESET MULTI STING
Function ResetMultiSting(cyc)
 ;reset human contacts
 For v=1 To no_plays
  pMultiSting(cyc,v)=1
  If pAnim(cyc)=>120 And pAnim(cyc)=<139 And (v=pFoc(cyc) Or cyc=pFoc(v))
   If InProximity(cyc,v,8) And pY#(cyc)=pY#(v) Then pMultiSting(cyc,v)=0 
  EndIf
 Next
 ;reset item contacts
 ResetItemStings(cyc)
 ;reset rope contacts
 For count=1 To 12
  pRopeSting(cyc,count)=1
 Next 
 ;reset cage contacts
 pCageSting(cyc)=1
End Function

;ATTACK TRAVELLING
Function AttackMovement(cyc,momentum)
 If pStagger#(cyc)>0
  ;recoil
  pStagger#(cyc)=pStagger#(cyc)-(pStagger#(cyc)/10)
  If pStagger#(cyc)<0 Then pStagger#(cyc)=0
  Advance(cyc,pA#(cyc),-pStagger#(cyc))
 Else
  ;travel
  pTravel#(cyc)=pTravel#(cyc)-(pTravel#(cyc)/Int(momentum/pAnimSpeed#(cyc)))
  If pSting(cyc)=0 Then pTravel#(cyc)=pTravel#(cyc)/2
  If pTravel#(cyc)<0 Then pTravel#(cyc)=0
  If InProximity(cyc,pFoc(cyc),10)=0 Or pFoc(cyc)=0 Then Advance(cyc,pA#(cyc),pTravel#(cyc))
  If pTravel#(cyc)>0.1 Then pStepTim#(cyc)=pStepTim#(cyc)+1
 EndIf
End Function

;FORECAST WORKABLE ATTACK RANGE
Function RequiredRange#(cyc,anim)
 range#=0
 ;standard attacks
 If anim=>60 And anim=<63 
  style=anim-59
  range#=attackRange#(style,charAttack(pChar(cyc),style))
  range#=range#+PercentOf#(PercentOf#(range#,25),GetPercent#(charHeight(pChar(cyc)),24))
 EndIf
 If anim=65 
  range#=15
  range#=range#+PercentOf#(PercentOf#(range#,25),GetPercent#(charHeight(pChar(cyc)),24))
 EndIf 
 If anim=>66 And anim=<69 Then range#=20
 If anim=>70 And anim=<73 Then range#=20
 ;flying attacks
 If anim=64 Or anim=74 
  If pAnim(cyc)=64 Then range#=30
  If pAnim(cyc)<>64 Then range#=PercentOf#(75,pAgility(cyc))
 EndIf
 ;weapon attacks
 If anim=>220 And anim=<221 Then range#=12+weapSize#(weapType(pHolding(cyc)))
 If anim=>222 And anim=<223 Then range#=10+PercentOf#(weapSize#(weapType(pHolding(cyc))),150)
 If anim=>224 And anim=<225 Then range#=14+weapSize#(weapType(pHolding(cyc)))
 If anim=226 Then range#=25
 If anim=>220 And anim=<226 Then range#=range#+PercentOf#(PercentOf#(range#,25),GetPercent#(charHeight(pChar(cyc)),24))
 If anim=>227 And anim=<229 Then range#=20
 ;grapple lunge
 If anim=300 Then range#=16+(charHeight(pChar(cyc))/3)
 ;add travel
 range#=range#+Float#(pAgility(cyc)/50)
 If anim=63 Then range#=range#+Float#(pAgility(cyc)/10)
 If anim=73 Then range#=range#+Float#(pAgility(cyc)/25)
 Return range#
End Function

;FIND ATTACK IMPACTS
Function FindAttackImpacts(cyc,activeTim,impactTim,expireTim,baseRange#,height#,basePower,weapon,style)
 ;assess range
 peakTim=Int(impactTim/pAnimSpeed#(cyc)) 
 peakRange#=baseRange#+PercentOf#(PercentOf#(baseRange#,25),GetPercent#(charHeight(pChar(cyc)),24))
 stretch#=PercentOf#(peakRange#,25)
 If pAnimTim(cyc)<peakTim Then factor#=GetPercent#(peakTim-pAnimTim(cyc),peakTim-Int(activeTim/pAnimSpeed#(cyc)))
 If pAnimTim(cyc)=>peakTim Then factor#=GetPercent#(pAnimTim(cyc)-peakTim,Int(expireTim/pAnimSpeed#(cyc))-peakTim)
 range#=peakRange#-PercentOf#(stretch#,factor#)
 ;find contacts
 For v=1 To no_plays
  blockOffset#=0
  If pAnim(v)=>80 And pAnim(v)=<89 Then blockOffset#=2.0
  contact#=GetDistance#(pX#(cyc),pZ#(cyc),RealX#(v),RealZ#(v))
  If pY#(cyc)+TranslateHeight#(cyc,height#)>pY#(v)+TranslateHeight#(v,20) Or AttackViable(v)=2
   If GetDistance#(pX#(cyc),pZ#(cyc),pLimbX#(v,1),pLimbZ#(v,1))<contact# Then contact#=GetDistance#(pX#(cyc),pZ#(cyc),pLimbX#(v,1),pLimbZ#(v,1))
  EndIf
  angle#=45-((contact#-10)*3)
  If angle#<5 Then angle#=5 
  If cyc<>v And contact#<range#+blockOffset# And pY#(cyc)+TranslateHeight#(cyc,height#)>pY#(v) And pY#(cyc)+TranslateHeight#(cyc,height#)<pY#(v)+35 And pSting(cyc)=1
   impact=0
   If AttackViable(v)=1 Then impact=1
   If AttackViable(v)=2 
    If TranslateHeight#(cyc,height#)<TranslateHeight#(v,20) Then impact=2
    If style=>3 And pAnimTim(cyc)>Int(impactTim/pAnimSpeed#(cyc)) Then impact=2
    If pHolding(cyc)>0 And weapHold(weapType(pHolding(cyc)))>0 And pAnimTim(cyc)>Int(impactTim/pAnimSpeed#(cyc)) Then impact=2
   EndIf
   If impact>0 And InLine(cyc,pLimb(v,36),angle#)
    ;universal effects
    If pAnim(cyc)=69 Then pHurtA#(v)=pA#(cyc)+180 Else pHurtA#(v)=pA#(cyc)
    checkRange#=peakRange#
    If pAnim(cyc)=>220 And pAnim(cyc)=<229 And checkRange#>25 Then checkRange#=25
    pStagger#(v)=(checkRange#-GetDistance#(pX#(cyc),pZ#(cyc),pX#(v),pZ#(v)))/4
    If pStagger#(v)<checkRange#/50 Then pStagger#(v)=checkRange#/50
    If pStagger#(v)>checkRange#/10 Then pStagger#(v)=checkRange#/10
    If InProximity(cyc,v,15) And pStagger#(v)<1.0 Then pStagger#(v)=1.0
    If pAnim(cyc)=>60 And pAnim(cyc)=<63
     style=pAnim(cyc)-59 : move=charAttack(pChar(cyc),style)
     If attackFall(style,move)>0 Then pStagger#(v)=pStagger#(v)+(pStagger#(v)/4)
    EndIf
    checkRange#=peakRange#
    If pAnim(cyc)=>220 And pAnim(cyc)=<229 And checkRange#>20 Then checkRange#=20
    space=MeasureSpace(v,pHurtA#(v),20)
    pStagger#(cyc)=(checkRange#-(GetDistance#(pX#(cyc),pZ#(cyc),pX#(v),pZ#(v))+space))/6
    If pPlatform(v)=>1 And pPlatform(v)=<4 Then pStagger#(cyc)=(checkRange#-GetDistance#(pX#(cyc),pZ#(cyc),pX#(v),pZ#(v)))/6
    If pStagger#(cyc)>checkRange#/15 Then pStagger#(cyc)=checkRange#/15
    If pStagger#(cyc)<0 Then pStagger#(cyc)=0
    If pAnim(cyc)=69 Then pTravel#(cyc)=pStagger#(cyc) : pStagger#(cyc)=0
    pHurtTim(v)=2
    If style=2 Then pHurtTim(v)=4
    If style=>3 Or pAnim(cyc)=65 Then pHurtTim(v)=8
    ClockIntruder(cyc,v)
    ClockAbuse(cyc,v,1) 
    If pWarned(v)<>cyc And (RefViable(v)=0 Or pRole(cyc)<>1) Then pFoc(v)=cyc : pAngerTim(v,cyc)=50
    pSting(cyc)=0
    ;blocked contact
    blocked=RiskBlocking(cyc,v,weapon)
    If blocked>0
     If pHolding(v)>0 And weapWear(pHolding(v))=0 Then ProduceSound(p(v),weapSound(weapType(pHolding(v))),22050,0)
     If style=>3 And style=<4
      Pop(cyc,Rnd(2,9),Rnd(0.25,0.5))
      If pHolding(cyc)=0 Or weapWear(pHolding(cyc))>0 Then ProduceSound(p(cyc),sBlock(Rnd(5,7)),22050,1)
      If pHolding(cyc)>0 And weapWear(pHolding(cyc))=0 Then ProduceSound(p(cyc),sBlock(Rnd(5,7)),22050,0.5)
      ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),Rnd(0.5,1.0))
     Else
      Pop(cyc,Rnd(2,7),Rnd(0.0,0.25))
      If pHolding(cyc)=0 Or weapWear(pHolding(cyc))>0 Then ProduceSound(p(cyc),sBlock(Rnd(1,4)),22050,0)
      If pHolding(cyc)>0 And weapWear(pHolding(cyc))=0 Then ProduceSound(p(cyc),sBlock(Rnd(1,4)),22050,0.5)
      ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),Rnd(0.1,0.5))
     EndIf
     impactY#=pY#(cyc)+TranslateHeight#(cyc,height#)
     If impactY#>pY#(v)+TranslateHeight#(v,20)
      CreateSpurt(pLimbX#(v,1),impactY#,pLimbZ#(v,1),-1,0.75,10,4)
      CreateSpurt(pLimbX#(v,1),impactY#,pLimbZ#(v,1),-1,0,5,5)
      anim=81
     Else
      CreateSpurt(pX#(v),impactY#,pZ#(v),-1,0.75,10,4)
      CreateSpurt(pX#(v),impactY#,pZ#(v),-1,0,5,5)
      anim=84
     EndIf
     ScarArea(v,pX#(v),impactY#,pZ#(v),50)
     If weapon>0 And pHolding(cyc)>0 And weapWear(pHolding(cyc))=0 
      ScarArea(v,pX#(v),impactY#,pZ#(v),25)
      If weapBurning(pHolding(cyc))>0 Then FireSpurt(pX#(v),impactY#,pZ#(v),-1)
     EndIf
     If pInjured(v,1)>0 Then anim=111
     If pInjured(v,2)>0 Then anim=112
     ChangeAnim(v,anim) 
     pain=PercentOf#(basePower,pStrength(cyc))/2
     If matchShoot>0 Then pain=pain+(pain/10) : pHeat(cyc)=pHeat(cyc)+Rnd(0,1) : pHeat(v)=pHeat(v)-Rnd(0,1)
     If pSpecial(cyc)>0 And style=>3 Then pain=pain+(pain/4) : pSpecial(cyc)=pSpecial(cyc)-(pSpecial(cyc)/10)
     pHealth(v)=pHealth(v)-pain : pHP(v)=pHP(v)-Rnd(pain/4,pain)
     If pAnim(cyc)=>60 And pAnim(cyc)=<63
      style=pAnim(cyc)-59 : move=charAttack(pChar(cyc),style)
      If attackFall(style,move)>0 Or style=4 Then pHP(v)=pHP(v)-pain
     EndIf
     If pAnim(cyc)=65 Then pHP(v)=pHP(v)-pain
     If EntertainViable(cyc,v)
      If style=<2 Then entScore=entScore+PercentOf#(pain,60)
      If style=>3 Then entScore=entScore+PercentOf#(pain,40)
      If matchShoot>0 Then entScore=entScore+PercentOf#(pain,25)
     EndIf
     pDT(v)=GetDT(v,pain)
     If style=>1 And style=<2 Then pHeat(cyc)=pHeat(cyc)+Rnd(0,1) : pHeat(v)=pHeat(v)-Rnd(0,1)
     If style=>3 And style=<4 Then pHeat(cyc)=pHeat(cyc)+1 : pHeat(v)=pHeat(v)-1
     If weapon>0 Then WeaponImpact(cyc,v,0)
     RiskInjury(v,Rnd(1,2),100)
    EndIf
    ;successful contact
    If blocked=0 
     ImpactChecks(v)
     If style=>3 And style=<4
      Pop(cyc,Rnd(2,9),Rnd(0.5,1.0))
      If pHolding(cyc)=0 Or weapWear(pHolding(cyc))>0 Then ProduceSound(p(cyc),sImpact(Rnd(5,7)),22050,1)
      If pHolding(cyc)>0 And weapWear(pHolding(cyc))=0 Then ProduceSound(p(cyc),sImpact(Rnd(5,7)),22050,0.5)
      ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),1)
     Else
      Pop(cyc,Rnd(2,7),Rnd(0.1,0.5))
      If pHolding(cyc)=0 Or weapWear(pHolding(cyc))>0 Then ProduceSound(p(cyc),sImpact(Rnd(1,4)),22050,0)
      If pHolding(cyc)>0 And weapWear(pHolding(cyc))=0 Then ProduceSound(p(cyc),sImpact(Rnd(1,4)),22050,0.5)
      ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),0)
     EndIf
     impactY#=pY#(cyc)+TranslateHeight#(cyc,height#)
     If impactY#>pLimbY#(v,1) Then impactY#=pLimbY#(v,1)
     If impactY#>pY#(v)+TranslateHeight#(v,20)
      ImpactSpurt(pLimbX#(v,1),impactY#,pLimbZ#(v,1),1,1)
      BloodSpurt(pLimbX#(v,1),impactY#,pLimbZ#(v,1),pA#(cyc),pScar(v,1)-1,1)
      If weapon>0 And pHolding(cyc)>0 And weapWear(pHolding(cyc))=0 
       ScarLimb(v,1,25) : BloodSpurt(pLimbX#(v,1),impactY#,pLimbZ#(v,1),pA#(cyc),pScar(v,1)-1,1)
       If weapBurning(pHolding(cyc))>0 Then FireSpurt(pLimbX#(v,1),impactY#,pLimbZ#(v,1),-1)
      EndIf
      If style=<2 Then ScarLimb(v,1,50)
      If style=>3 Then ScarLimb(v,1,25)
      If pDizzyTim(v)>0 And pBlindTim(v)=0 Then pDelayAnim(v)=91 Else pDelayAnim(v)=Rnd(92,95)
     Else 
      ImpactSpurt(pX#(v),impactY#,pZ#(v),1,1)
      BloodSpurt(pX#(v),impactY#,pZ#(v),pA#(cyc),FindBlood(v,pX#(v),impactY#,pZ#(v))-1,1)
      If weapon>0 And pHolding(cyc)>0 And weapWear(pHolding(cyc))=0
       ScarArea(v,pX#(v),impactY#,pZ#(v),25) : BloodSpurt(pX#(v),impactY#,pZ#(v),pA#(cyc),FindBlood(v,pX#(v),impactY#,pZ#(v))-1,1)
       If weapBurning(pHolding(cyc))>0 Then FireSpurt(pX#(v),impactY#,pZ#(v),-1)
      EndIf
      If style=<2 Then ScarArea(v,pX#(v),impactY#,pZ#(v),50)
      If style=>3 Then ScarArea(v,pX#(v),impactY#,pZ#(v),25)
      If pDizzyTim(v)>0 And pBlindTim(v)=0 Then pDelayAnim(v)=101 Else pDelayAnim(v)=Rnd(102,105)
     EndIf
     If pBlinkTim(v)=0 Then pBlinkTim(v)=Rnd(10,50)
     pain=PercentOf#(basePower,pStrength(cyc))
     If matchShoot>0 Then pain=pain+(pain/10) : pHeat(cyc)=pHeat(cyc)+Rnd(0,1) : pHeat(v)=pHeat(v)-Rnd(0,1)
     If pMomentum(v)>0 Then pain=pain+(pain/2) : pHeat(cyc)=pHeat(cyc)+1 : pHeat(v)=pHeat(v)-1
     If pSpecial(cyc)>0 And style=>3 Then pain=pain+(pain/4) : pSpecial(cyc)=pSpecial(cyc)-(pSpecial(cyc)/10)
     If EntertainViable(cyc,v) 
      If style=<2 Then entScore=entScore+PercentOf#(pain,60)
      If style=>3 Then entScore=entScore+PercentOf#(pain,40)
      If matchShoot>0 Then entScore=entScore+PercentOf#(pain,25)
     EndIf
     chance=((110-pStrength(cyc))+(pToughness(v)-25))*2
     If style=>3 Then chance=chance/2
     If pSpecial(cyc)>0 Then chance=chance/2
     randy=Rnd(0,chance)
     If randy=<2 Then pain=pain*2 : pHeat(cyc)=pHeat(cyc)+1 : pHeat(v)=pHeat(v)-1
     If randy=3 Then pain=pain*3 : pHeat(cyc)=pHeat(cyc)+2 : pHeat(v)=pHeat(v)-(pHeat(v)/10)
     If randy=4 Then pain=pain*4 : pHeat(cyc)=pHeat(cyc)+3 : pHeat(v)=pHeat(v)-(pHeat(v)/8)
     If randy=5 Then pain=pain*5 : pHeat(cyc)=pHeat(cyc)+4 : pHeat(v)=pHeat(v)-(pHeat(v)/6)
     If randy=<5
      Pop(cyc,Rnd(2,9),1)
      ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),1)
      ProduceSound(p(cyc),sImpact(7),22050,1) 
      If pDelayAnim(v)=>90 And pDelayAnim(v)=<99 Then pDelayAnim(v)=90
      If pDelayAnim(v)=>100 And pDelayAnim(v)=<109 Then pDelayAnim(v)=100
      If EntertainViable(cyc,v) Then entScore=entScore+PercentOf#(basePower,pStrength(cyc))
     EndIf
     pHealth(v)=pHealth(v)-pain : pHP(v)=pHP(v)-Rnd(pain/4,pain)
     If pAnim(cyc)=>60 And pAnim(cyc)=<63
      style=pAnim(cyc)-59 : move=charAttack(pChar(cyc),style)
      If attackFall(style,move)>0 Or style=4 Then pHP(v)=pHP(v)-pain
     EndIf
     If pAnim(cyc)=65 Or pAnim(cyc)=69 Then pHP(v)=pHP(v)-pain
     If (pSpecial(cyc)>0 And style=>3) Or pAnim(cyc)=68 Then pHP(v)=0
     pDT(v)=GetDT(v,pain)*2
     If pDT(v)>500 Then pDT(v)=500
     If style=>1 And style=<2 Then pHeat(cyc)=pHeat(cyc)+1 : pHeat(v)=pHeat(v)-1
     If style=>3 And style=<4 Then pHeat(cyc)=pHeat(cyc)+2 : pHeat(v)=pHeat(v)-2
     If weapon>0 And style=<2 Then WeaponImpact(cyc,v,1)
     If weapon>0 And style=>3 Then WeaponImpact(cyc,v,2)
     If pDelayAnim(v)=>90 And pDelayAnim(v)=<95 Then RiskDizziness(v,pain) : RiskBlindness(v,pain) : RiskInjury(v,5,100)
     If pDelayAnim(v)=>100 And pDelayAnim(v)=<105 Then RiskInjury(v,3,100) : RiskInjury(v,4,200)
     If pAnim(v)=>60 And pAnim(v)=<63
      randy=Rnd(0,110-pToughness(v))
      If randy=<2 And pDelayAnim(v)<>90 And pDelayAnim(v)<>100 Then pHurtDelay(v)=1 
      chance=(pHP(cyc)+pHP(v))*optLength
      If chance<100*optLength Then chance=100*optLength
      randy=Rnd(0,chance) 
      If randy=<1
       ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),1)
       ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),1)
       Pop(cyc,Rnd(2,9),1) : Pop(v,Rnd(2,9),1)
       pHP(cyc)=0 : pHP(v)=0
       If EntertainViable(cyc,v) Then entScore=entScore+250
      EndIf
     EndIf
     If pReaction(v)=>111 And pReaction(v)=<115 
      ChangeAnim(v,pReaction(v)) : pImmunity(v)=20 : pReaction(v)=0
     Else
      If pHurtDelay(v)=0 Then ChangeAnim(v,pDelayAnim(v))
     EndIf
    EndIf
   EndIf
  EndIf
 Next
End Function

;FIND SPIT IMPACTS
Function FindSpitImpacts(cyc,startTim,peakTim,range#,spurt)
 For v=1 To no_plays
  factor#=GetPercent#(pAnimTim(cyc)-startTim,peakTim-startTim)
  If factor#>100 Then factor#=100
  range#=(range#/2)+PercentOf#(range#/2,factor#)
  contact#=GetDistance#(pX#(cyc),pZ#(cyc),pLimbX#(v,1),pLimbZ#(v,1))
  If cyc<>v And contact#<range# And pY#(cyc)>pY#(v)-5 And pY#(cyc)<pY#(v)+5 And AttackViable(v)=1 And pMultiSting(cyc,v)=1
   If InLine(cyc,pLimb(v,36),45)
    ImpactChecks(v)
    Pop(cyc,Rnd(2,8),1)
    ProduceSound(p(v),sBleed,22050,0)
    ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),1)
    ProduceSound(p(v),sChoke,GetVoice(v),0)
    If spurt=1 Or spurt=9
     ProduceSound(p(v),sIgnite,22050,0)
     BloodSpurt(pLimbX#(v,1),pLimbY#(v,1),pLimbZ#(v,1),pA#(cyc),pScar(v,1)-1,1)
     pHealth(v)=pHealth(v)-100 : pHP(v)=pHP(v)-100
     ScarLimb(v,1,10)
    EndIf
    If pScar(v,1)<1 Then pScar(v,1)=1
    If spurt=11 Then EntityTexture pLimb(v,1),tFaceScar(5),0,6
    ChangeAnim(v,Rnd(92,95))
    pHurtA#(v)=pA#(cyc) : pStagger#(v)=0.5
    pHealth(v)=pHealth(v)-50 : pHP(v)=pHP(v)-50
    pHeat(cyc)=pHeat(cyc)-1 : pHeat(v)=pHeat(v)-5
    If EntertainViable(cyc,v) Then entScore=entScore+100 : entHardcore=entHardcore+50
    pBlindTim(v)=Rnd(0,250)
    pMultiSting(cyc,v)=0
   EndIf
  EndIf
 Next
End Function

;FIND RUNNING OBSTRUCTIONS
Function RunningImpacts(cyc)
 contact=0
 ;human obstructions
 For v=1 To no_plays
  If cyc<>v And AttackViable(v)<>4 And pAnim(v)<>300 And pAnim(v)<>310
   width#=12 : height#=35
   If pX#(cyc)>RealX#(v)-width# And pX#(cyc)<RealX#(v)+width# And pZ#(cyc)>RealZ#(v)-width# And pZ#(cyc)<RealZ#(v)+width# And pY#(cyc)>pY#(v)-height# And pY#(cyc)<pY#(v)+height#
    If InLine(cyc,pLimb(v,36),45)
     Pop(cyc,Rnd(2,8),0)
     ProduceSound(p(v),sImpact(Rnd(1,6)),22050,0)
     ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),0)
     impactY#=pLimbY#(cyc,1)-5
     If impactY#>pLimbY#(v,1)-5 Then impactY#=pLimbY#(v,1)-5
     If impactY#<pY#(v)+5 Then impactY#=pY#(v)+5
     ImpactSpurt(RealX#(v),impactY#,RealZ#(v),1,1)
     BloodSpurt(RealX#(v),impactY#,RealZ#(v),pA#(cyc),FindBlood(v,RealX#(v),impactY#,RealZ#(v))-1,1)
     ScarArea(v,RealX#(v),impactY#,RealZ#(v),25)
     BloodSpurt(RealX#(cyc),impactY#,RealZ#(cyc),pA#(cyc)+180,FindBlood(cyc,RealX#(cyc),impactY#,RealZ#(cyc))-1,1)
     ScarArea(cyc,RealX#(cyc),impactY#,RealZ#(cyc),25)
     If AttackViable(v)=>1 And AttackViable(v)=<2 
      ImpactChecks(v)
      ChangeAnim(v,Rnd(92,95)) : pHurtTim(v)=5
      pHurtA#(v)=pA#(cyc) : pStagger#(v)=0.5
     EndIf
     If AttackViable(v)=>3 Then GroundReaction(v)
     pain=TranslateWeight(pChar(cyc))
     pHealth(v)=pHealth(v)-pain : pHP(v)=pHP(v)-pain
     pDT(v)=GetDT(v,pain) : contact=1
     If EntertainViable(cyc,v) Then entScore=entScore+100
     If pTeam(v)<>pTeam(cyc) Then pFoc(v)=cyc : pAngerTim(v,cyc)=50
    EndIf
   EndIf
  EndIf
 Next
 ;item obstructions
 If contact=0
  For v=1 To no_items
   If iState(v)=0 And ItemProximity(cyc,v,30) And pY#(cyc)=>iY#(v)-20 And pY#(cyc)=<iY#(v)+iHeight#(iType(v))
    ScanItem(v,0)
    If ItemCollide(cyc,v,pX#(cyc),pZ#(cyc),0) 
     ProduceSound(i(v),sItem,22050,0)
     impactY#=pLimbY#(cyc,1)-5
     If impactY#>iHeight#(iType(v))-5 Then impactY#=iHeight#(iType(v))-5
     ImpactSpurt(pX#(cyc),impactY#,pZ#(cyc),1,1)
     BloodSpurt(pX#(cyc),impactY#,pZ#(cyc),pA#(cyc),FindBlood(cyc,pX#(cyc),impactY#,pZ#(cyc))-1,1)
     ScarArea(cyc,pX#(cyc),impactY#,pZ#(cyc),25)
     iAnim(v)=11 : iAnimTim(v)=0
     entHardcore=entHardcore+50
     contact=2
    EndIf
   EndIf
  Next
 EndIf
 ;scenic obstructions
 If contact=0 And pAnim(cyc)<>44 And pAnim(cyc)<>700 And pAnim(cyc)<>701
  For v=0 To 100
   If blockType(v)>0
    If pX#(cyc)>blockX1#(v) And pX#(cyc)<blockX2#(v) And pZ#(cyc)>blockZ1#(v) And pZ#(cyc)<blockZ2#(v) And pY#(cyc)>blockY1#(v) And pY#(cyc)<blockY2#(v)
     Pop(cyc,Rnd(2,8),0)
     ProduceSound(p(cyc),sBlock(Rnd(1,6)),22050,0)
     impactY#=pLimbY#(cyc,1)-5
     If impactY#>blockY2#(v)-5 Then impactY#=blockY2#(v)-5
     If v=0 And matchCage>0 Then impactY#=pLimbY#(cyc,1)-5
     ImpactSpurt(pX#(cyc),impactY#,pZ#(cyc),1,1)
     BloodSpurt(pX#(cyc),impactY#,pZ#(cyc),pA#(cyc),FindBlood(cyc,pX#(cyc),impactY#,pZ#(cyc))-1,1)
     ScarArea(cyc,pX#(cyc),impactY#,pZ#(cyc),25)
     If v=0 
      ShakeRing(0,1.0)
      If matchCage>0
       ProduceSound(p(cyc),sSmashWire,22050,0)
       ShakeCage(NearestSide(pX#(cyc),pZ#(cyc)),-1.0)
       ScarArea(cyc,pX#(cyc),impactY#,pZ#(cyc),25) 
       pHealth(cyc)=pHealth(cyc)-100
       entHardcore=entHardcore+50
      EndIf
     EndIf
     contact=1
    EndIf
   EndIf
  Next
 EndIf
 ;act on contact
 If contact>0
  Pop(cyc,Rnd(2,8),0)
  ProduceSound(p(cyc),sImpact(Rnd(1,6)),22050,0)
  ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),1)
  pHealth(cyc)=pHealth(cyc)-100 : pHP(cyc)=0
  pDT(cyc)=GetDT(cyc,100) : pHurtA#(cyc)=pA#(cyc)+180
  randy=Rnd(0,2)
  If randy>0 And contact=2 Then pHurtA#(cyc)=pA#(cyc) : pA#(cyc)=pA#(cyc)+180
  If EntertainViable(cyc,0) Then entScore=entScore+100
 EndIf
End Function

;UNIVERSAL STANDING IMPACT VICTIM EFFECTS
Function ImpactChecks(cyc)
 ;rope turn
 If pAnim(cyc)=44 
  If pAnimTim(cyc)=>Int(20/pAnimSpeed#(cyc)) Then SharpTransition(cyc,34,0,180)
  pHP(cyc)=0
 EndIf
 ;momentum bonus
 If pMomentum(cyc)>0 Then pHP(cyc)=0
 If pOldAnim(cyc)=44 And pAnim(cyc)=12 And pAnimTim(cyc)<10 Then pHP(cyc)=0
 ;platform topple
 randy=Rnd(0,5)
 If randy=0 And pPlatform(cyc)=>1 And pPlatform(cyc)=<4 Then pHP(cyc)=0
 If pPlatform(cyc)=>5 Then pHP(cyc)=0
 ;aerial counter
 If pAnim(cyc)=64 Or pAnim(cyc)=74
  ResetExcuses(cyc,0)
  pY#(cyc)=pGround#(cyc)
  FlightCorrection(cyc,1)
  pHP(cyc)=0
 EndIf
 If pAnim(cyc)=54 Or pAnim(cyc)=56
  ResetExcuses(cyc,0)
  pY#(cyc)=pGround#(cyc)
  pPlatform(cyc)=0
  pHP(cyc)=0
 EndIf
 ;break pins
 If pPinning(cyc)>0 Then pPinner(pPinning(cyc))=0 : pPinning(cyc)=0 : pExcusedPlays(cyc)=0
 If pPinner(cyc)>0 Then pPinner(pPinner(cyc))=0 : pPinning(cyc)=0
 ;break standing grappler
 If pGrappling(cyc)>0 And pAnim(cyc)<500
  v=pGrappling(cyc)
  ChangeAnim(v,Rnd(102,105)) : pHurtA#(v)=pA#(v)+180 : pStagger#(v)=0.25
  If pStretched(v)>0 Then SharpTransition(cyc,1,1,-1) : SharpTransition(v,1,1,-1) : pHP(v)=0
  EndMove(cyc,v)
 EndIf
 ;break standing grapple victim
 If pGrappler(cyc)>0 And pAnim(cyc)<500
  v=pGrappler(cyc)
  ChangeAnim(v,Rnd(102,105)) : pHurtA#(v)=pA#(v)+180 : pStagger#(v)=0.25
  If pStretched(cyc)>0 Then SharpTransition(cyc,1,1,-1) : SharpTransition(v,1,1,-1) : pHP(cyc)=0
  EndMove(v,cyc)
 EndIf 
 ;break ground grappler
 If pGrappling(cyc)>0 And pAnim(cyc)=>500
  v=pGrappling(cyc)
  GroundReaction(v)
  SharpTransition(cyc,156,1,-1)
  pHurtA#(cyc)=pA#(cyc)+180 : pStagger#(cyc)=0.25
  EndMove(cyc,v)
 EndIf
 ;reset referee checking
 If pRole(cyc)=1
  pChecked(pRefVictim(cyc))=0
  If matchCounter=cyc Then matchCounter=0
 EndIf
 ;drop item
 DropItem(cyc)
 ;activate intruders
 If pRole(cyc)=3 And pOutTim(cyc)=0
  pOutTim(cyc)=1
  If pCostume(cyc)=3 Then pRole(cyc)=1 : pTeam(cyc)=0
 EndIf
End Function

;UNIVERSAL GROUND IMPACT VICTIM EFFECTS
Function GroundImpactChecks(cyc)
 ;break pinner
 If pPinning(cyc)>0 And pAnim(cyc)<300 And pAnim(pPinning(cyc))<300
  v=pPinning(cyc)
  If matchState=3 And pPinCount(v)=1 Then Pop(v,Rnd(2,9),0) : entScore=entScore+Rnd(charPopularity(pChar(v)),charPopularity(pChar(v))*2)
  If matchState=3 And pPinCount(v)=>2 Then Pop(v,Rnd(2,9),1) : Pop(cyc,Rnd(2,9),0) : entScore=entScore+Rnd(charPopularity(pChar(v))*2,charPopularity(pChar(v))*4)
  GroundReaction(v)
  If pAnim(cyc)=160 Or pAnim(cyc)=161 Then pHurtA#(cyc)=pA#(cyc)+180 : pHP(cyc)=0
  pPinner(v)=0 : pPinning(cyc)=0
  pExcusedPlays(cyc)=0
 EndIf
 ;break pin victim
 If pPinner(cyc)>0 And pAnim(cyc)<300 And pAnim(pPinner(cyc))<300
  v=pPinner(cyc)
  If matchState=3 And pPinCount(cyc)=1 Then Pop(v,Rnd(2,9),0) : entScore=entScore+Rnd(charPopularity(pChar(cyc)),charPopularity(pChar(cyc))*2)
  If matchState=3 And pPinCount(cyc)=>2 Then Pop(v,Rnd(2,9),1) : Pop(cyc,Rnd(2,9),0) : entScore=entScore+Rnd(charPopularity(pChar(cyc))*2,charPopularity(pChar(cyc))*4)
  If pAnim(v)=160 Or pAnim(v)=161 Then ChangeAnim(v,Rnd(162,163))
  pPinner(v)=0 : pPinning(cyc)=0
  pExcusedPlays(v)=0
 EndIf
 ;break grapple victim
 If pGrappler(cyc)>0
  v=pGrappler(cyc)
  EnforceBlocks(cyc) : pDT(cyc)=pDT(cyc)+50
  ChangeAnim(v,Rnd(92,95)) : SharpTransition(v,1,1,-1)
  pHurtA#(v)=pA#(v)+180 : pStagger#(v)=0.25
  EndMove(v,cyc)
 EndIf
 ;reset referee checking
 If pRole(cyc)=1
  pChecked(pRefVictim(cyc))=0
  If matchCounter=cyc Then matchCounter=0
 EndIf
End Function

;GET DOWN TIME
Function GetDT(cyc,pain)
 factor#=50+(GetPercent#((5000*optLength)-pHealth(cyc),5000*optLength)/2)
 value=PercentOf#(pain,factor#)
 value=Rnd(PercentOf#(value,75),PercentOf#(value,125))
 If value>750 Then value=750
 Return value
End Function

;RISK DIZZINESS
Function RiskDizziness(cyc,pain)
 If pain>500 Then pain=500
 randy=Rnd(0,pToughness(cyc)*75)
 If randy=<pain And pDizzyTim(cyc)=0
  Pop(0,Rnd(2,9),0)
  If charGender(pChar(cyc))=0 Then ProduceSound(p(cyc),sPain(Rnd(1,8)),18000,0)
  If charGender(pChar(cyc))=1 Then ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),1)
  pDizzyTim(cyc)=Rnd(0,250)
 EndIf
End Function

;RISK BLINDNESS
Function RiskBlindness(cyc,pain)
 If pain>500 Then pain=500
 randy=Rnd(0,pToughness(cyc)*75)
 If randy=<pain And pBlindTim(cyc)=0
  Pop(0,Rnd(2,9),0)
  ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),1)
  pBlindTim(cyc)=Rnd(0,250)
 EndIf
End Function

;GROUND REACTION
Function GroundReaction(cyc)
;move pinning
If pAnim(cyc)=>309 And pAnim(cyc)=<499 And (pPinning(cyc)>0 Or pPinner(cyc)>0)
 If pPinning(cyc)>0 Then pAnimStage(cyc)=3 : pAnimTim(cyc)=0 : pPinner(pPinning(cyc))=0 : pPinning(cyc)=0
 If pPinner(cyc)>0 Then pAnimStage(pPinner(cyc))=3 : pAnimTim(pPinner(cyc))=0 : pPinning(pPinner(cyc))=0 : pPinner(cyc)=0
Else
 ;on back by default
 anim=99
 ;lying on front
 If pAnim(cyc)=138 Then anim=109 ;breaking down onto front
 If pAnim(cyc)=151 And pAnimTim(cyc)>Int(30/pAnimSpeed#(cyc)) Then anim=109 ;turning onto front
 If pAnim(cyc)=152 Or pAnim(cyc)=109 Then anim=109 ;already on front
 If pAnim(cyc)=153 Then anim=109 ;rising off front
 If pAnim(cyc)=159 And pAnimTim(cyc)=<Int(25/pAnimSpeed#(cyc)) Then anim=109 ;turning onto back
 If pAnim(cyc)=502 And pGrappler(cyc)>0
  If pAnimTim(pGrappler(cyc))<Int(15/pAnimSpeed#(pGrappler(cyc)))
   If pAnim(pGrappler(cyc))=504 Or pAnim(pGrappler(cyc))=604 Then anim=109 ;being ground grappled off front
  EndIf
  If pAnim(pGrappler(cyc))=511 Or pAnim(pGrappler(cyc))=611 Then anim=109 ;ground move on front
 EndIf
 ;kneeling
 If AttackViable(cyc)=2 Then anim=137
 ;hands & knees breakdown
 breaker=0
 If pAnim(cyc)=68 Or pAnim(cyc)=69 Then breaker=1
 If pAnim(cyc)=137 Or (pAnim(cyc)=138 And pAnimTim(cyc)=<Int(20/pAnimSpeed#(cyc))) Or (pAnim(cyc)=139 And pAnimTim(cyc)=<Int(20/pAnimSpeed#(cyc)))  Then breaker=1
 If (pAnim(cyc)=151 And pAnimTim(cyc)>Int(40/pAnimSpeed#(cyc))) Or (pAnim(cyc)=153 And pAnimTim(cyc)>Int(20/pAnimSpeed#(cyc))) Or pAnim(cyc)=154 Or pAnim(cyc)=158 Then breaker=1
 If pAnim(cyc)=>160 And pAnim(cyc)=<161 And pGrappler(cyc)>0 Then breaker=1
 If breaker=1
  anim=Rnd(138,139)
  If anim=138 And (pAnim(cyc)=68 Or pAnim(cyc)=139 Or pAnim(cyc)=151) Then SharpTransition(cyc,138,0,180)
  If anim=139 And pAnim(cyc)<>68 And pAnim(cyc)<>139 And pAnim(cyc)<>151 Then SharpTransition(cyc,139,0,180)
 EndIf
 If pAnim(cyc)=179 Or pAnim(cyc)=181 Then anim=Rnd(138,139)
 ;pin release
 If pAnim(cyc)=>160 And pAnim(cyc)=<161 And pGrappler(cyc)=0 Then anim=162
 ;instant transition to front
 If anim=109
  If pAnim(cyc)=151 Or pAnim(cyc)=159 Then SharpTransition(cyc,152,0,180)
  If pAnim(cyc)=502 And pAnimTim(pGrappler(cyc))<Int(15/pAnimSpeed#(pGrappler(cyc)))
   If pAnim(pGrappler(cyc))=504 Or pAnim(pGrappler(cyc))=604 Then SharpTransition(cyc,152,0,180)
  EndIf
 EndIf
 ;instant transition on back
 If anim=99
  If pAnim(cyc)=502 And pAnimTim(pGrappler(cyc))=>Int(15/pAnimSpeed#(pGrappler(cyc)))
   If pAnim(pGrappler(cyc))=504 Or pAnim(pGrappler(cyc))=604 Then SharpTransition(cyc,150,0,0)
  EndIf
 EndIf
 ;activate animation
 ChangeAnim(cyc,anim)
EndIf
End Function

;TRYING TO GET UP?
Function GettingUp(cyc)
 value=0
 If pDT(cyc)=0 And pAnim(cyc)=>150 And pAnim(cyc)=<159
  If ActionPressed(cyc) Or pControl(cyc)=0 Then value=1
  If DirPressed(cyc) And pAnim(cyc)<>154 And pAnim(cyc)<>158 Then value=1
  ;If pAnim(cyc)=65 Or pAnim(cyc)=68 Or pAnim(cyc)=69 Or pAnim(cyc)=179 Then value=0
 EndIf
 Return value
End Function

;FORECAST JUMP DISTANCE
Function LeapHeight#(cyc,forecast#) 
 ;position probe
 If forecast#<>0
  PositionEntity dummy,pX#(cyc),pY#(cyc),pZ#(cyc)
  RotateEntity dummy,0,pA#(cyc),0
  MoveEntity dummy,0,0,forecast#
  checkX#=EntityX(dummy) : checkZ#=EntityZ(dummy)
 EndIf
 If forecast#=0 Then checkX#=RealX#(cyc) : checkZ#=RealZ#(cyc)
 ;forecast height difference
 height#=pY#(cyc)-wGround# 
 If pPlatform(cyc)=>5 And pPlatform(cyc)=<8
  If InsideRing(checkX#,checkZ#,-15) Then height#=pY#(cyc)-wStage#
 Else
  If InsideRing(checkX#,checkZ#,0) Then height#=pY#(cyc)-wStage#
 EndIf
 Return height#
End Function

;CALCULATE FLIGHT PHYSICS
Function GetFlightPhysics(cyc,v)
 ;distance
 If v>0 Then distance#=GetDistance#(pX#(cyc),pZ#(cyc),pX#(v),pZ#(v)) Else distance#=40
 pTravel#(cyc)=PercentOf#(3.75,GetPercent#(distance#,65))
 max#=0.75+PercentOf#(4.0,pAgility(cyc)-25)
 If pTravel#(cyc)>max# Then pTravel#(cyc)=max#
 If pTravel#(cyc)<1.0 Then pTravel#(cyc)=1.0
 ;height
 pGravity#(cyc)=1.0+PercentOf#(2.0,pAgility(cyc))
 ;If pAnim(cyc)=64 Then PercentOf#(pGravity#(cyc),attackLaunch(5,charAttack(pChar(cyc),5)))
 ;If pAnim(cyc)=74 Then PercentOf#(pGravity#(cyc),crushLaunch(5,charCrush(pChar(cyc),5)))
 pVelocity#(cyc)=PercentOf#(pGravity#(cyc),11.5)
 ;tope offset
 If pPlatform(cyc)=0 
  pTravel#(cyc)=2.5
  pGravity#(cyc)=4.0
  pVelocity#(cyc)=0.4
 EndIf
End Function

;GET FLYING AGENDA
Function GetFlyAgenda(cyc,forecast#) ;1=inside ring (from buckle), 2=inside to outside (from buckle), 3=outside, 4=into aisleway, 5=inside ring (from item), 6=inside to outside (from item)
 value=0
 ;regular flights
 If InsideRing(pX#(cyc),pZ#(cyc),0)
  ProjectDummy(cyc,0,0,forecast#)
  checkX#=EntityX(dummy) : checkZ#=EntityZ(dummy) 
  If pPlatform(cyc)=>1 And pPlatform(cyc)=<8
   If InsideRing(checkX#,checkZ#,-15) Then value=1 Else value=2
   If (pAnim(cyc)=64 Or pAnim(cyc)=74) And pFoc(cyc)>0 And InsideRing(pX#(pFoc(cyc)),pZ#(pFoc(cyc)),0) Then value=1
  Else
   If InsideRing(checkX#,checkZ#,0) Then value=5 Else value=6
  EndIf
 Else
  value=3
 EndIf
 ;balcony flights
 If pPlatform(cyc)-10=>23 And pPlatform(cyc)-10=<26 Then value=4
 ;cage flights
 If pPlatform(cyc)=>91 And pPlatform(cyc)=<94 Then value=1
 If pPlatform(cyc)=>95 And pPlatform(cyc)=<98 Then value=3
 Return value
End Function

;FLIGHT CORRECTION
Function FlightCorrection(cyc,style) ;0=gentle, 1=instant
 ;force into ring
 If pFlyAgenda(cyc)=1 Or pFlyAgenda(cyc)=5
  If pZ#(cyc)>blockZ1#(1)-1
   If style=1 Or pFlyAgenda(cyc)=5 Then pZ#(cyc)=blockZ1#(1)-1 Else pZ#(cyc)=pZ#(cyc)-0.5
  EndIf
  If pX#(cyc)>blockX1#(2)-1
   If style=1 Or pFlyAgenda(cyc)=5 Then pX#(cyc)=blockX1#(2)-1 Else pX#(cyc)=pX#(cyc)-0.5
  EndIf
  If pZ#(cyc)<blockZ2#(3)+1
   If style=1 Or pFlyAgenda(cyc)=5 Then pZ#(cyc)=blockZ2#(3)+1 Else pZ#(cyc)=pZ#(cyc)+0.5 
  EndIf 
  If pX#(cyc)<blockX2#(4)+1
   If style=1 Or pFlyAgenda(cyc)=5 Then pX#(cyc)=blockX2#(4)+1 Else pX#(cyc)=pX#(cyc)+0.5
  EndIf
 EndIf
 ;force out of ring
 If (pFlyAgenda(cyc)=>2 And pFlyAgenda(cyc)=<3) Or pFlyAgenda(cyc)=6
  If InsideRing(pX#(cyc),pZ#(cyc),0) And InsideRing(pX#(cyc),pZ#(cyc),-15)=0
   side=NearestSide(pX#(cyc),pZ#(cyc))
   If side=1
    If style=1 Then pZ#(cyc)=blockZ2#(0)+1 Else pZ#(cyc)=pZ#(cyc)+0.5
   EndIf
   If side=2
    If style=1 Then pX#(cyc)=blockX2#(0)+1 Else pX#(cyc)=pX#(cyc)+0.5
   EndIf
   If side=3
    If style=1 Then pZ#(cyc)=blockZ1#(0)-1 Else pZ#(cyc)=pZ#(cyc)-0.5
   EndIf
   If side=4
    If style=1 Then pX#(cyc)=blockX1#(0)-1 Else pX#(cyc)=pX#(cyc)-0.5
   EndIf
  EndIf
 EndIf
 ;keep within aisleway
 If pFlyAgenda(cyc)=4
  If pZ#(cyc)>385
   If style=1 Then pZ#(cyc)=385 Else pZ#(cyc)=pZ#(cyc)-0.5
  EndIf
  If pX#(cyc)>17
   If style=1 Then pX#(cyc)=17 Else pX#(cyc)=pX#(cyc)-0.5
  EndIf
  If pZ#(cyc)<-385
   If style=1 Then pZ#(cyc)=-385 Else pZ#(cyc)=pZ#(cyc)+0.5 
  EndIf 
  If pX#(cyc)<-17
   If style=1 Then pX#(cyc)=-17 Else pX#(cyc)=pX#(cyc)+0.5
  EndIf
 EndIf
End Function

;CLOCK INTRUDER
Function ClockIntruder(cyc,v)
 If game=1 And matchState=3 And pRole(cyc)=3 And pRole(v)=0 And pEliminated(v)=0 And pTeam(cyc)<>pTeam(v) Then pIntruder(v)=pChar(cyc)
End Function

;CLOCK WEAPON USE
Function ClockWeapon(cyc,v,weapon)
 If game=1 And matchState=3 And matchRules>0 And pChar(cyc)=gamChar And pTeam(v)<>pTeam(matchPlayer) And pRole(v)=0 And pHealth(v)<pHealthLimit(v)/2
  matchWeapon=weapon
 EndIf
End Function

;CLOCK TEAM ABUSE
Function ClockAbuse(cyc,v,level)
 randy=Rnd(0,10)
 If randy=<1 And game=1 And matchTeams>0 And pTeam(v)=pTeam(cyc) And cyc<>v Then pAbused(v)=pChar(cyc)
End Function