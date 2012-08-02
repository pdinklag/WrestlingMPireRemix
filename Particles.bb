;//////////////////////////////////////////////////////////////////////////////
;--------------------- WRESTLING MPIRE 2008: PARTICLE EFFECTS -----------------
;//////////////////////////////////////////////////////////////////////////////

;-----------------------------------------------------------------
;/////////////////////// PARTICLE EFFECTS ////////////////////////
;-----------------------------------------------------------------

;LOAD PARTICLE EFFECTS
Function LoadParticles()
 For cyc=1 To no_particles
  If cyc=<no_particles/2 Then part(cyc)=LoadSprite("World/Sprites/Particle.bmp") : partTexture(cyc)=1
  If cyc>no_particles/2 Then part(cyc)=LoadSprite("World/Sprites/Particle.bmp",2) : partTexture(cyc)=2 
  EntityFX part(cyc),9
  partState(cyc)=0
  HideEntity part(cyc)
 Next
 ;reset explosions
 For count=1 To no_explodes
  exTim(count)=0
 Next
End Function

;CREATE PARTICLE EFFECT
Function CreateParticle(x#,y#,z#,angle#,style)
If optFX>0 And (optGore>0 Or style<>6 Or style<>14 Or style<>15)
 ;get texture style
 require=1
 If style=6 Or style=12 Or style=13 Or style=14 Or style=15 Or style=23 Or style=25 Then require=2
 ;find empty spot
 cyc=0
 For count=1 To no_particles
  If partState(count)=0 And partTexture(count)=require Then cyc=count
 Next
 ;force spot!
 If cyc=0 
  Repeat
   cyc=Rnd(1,no_particles)
  Until partTexture(cyc)=require
 EndIf
 ;activate new particle
 If cyc>0
  partX#(cyc)=x# : partY#(cyc)=y# : partZ#(cyc)=z#
  partA#(cyc)=angle#
  ;unique traits
  partType(cyc)=style
  If partType(cyc)=1 ;fire
   randy=Rnd(0,4)
   If randy=<3 Then EntityColor part(cyc),220,Rnd(0,100),0
   If randy=4 Then EntityColor part(cyc),220,150,100
   partSize#(cyc)=Rnd#(4.0,8.0) : partFlight#(cyc)=0.3
   partGravity#(cyc)=Rnd#(1.0,2.0) : partWeight#(cyc)=0.15
   partAlpha#(cyc)=Rnd#(0.7,1.0) : partFade#(cyc)=0.05
  EndIf
  If partType(cyc)=2 ;light smoke
   randy=Rnd(0,100)
   EntityColor part(cyc),randy,randy,randy 
   partSize#(cyc)=Rnd#(1.0,4.0) : partFlight#(cyc)=0.3
   partGravity#(cyc)=0.5 : partWeight#(cyc)=0
   partAlpha#(cyc)=Rnd#(0.4,0.8) : partFade#(cyc)=0.01
  EndIf
  If partType(cyc)=3 ;red impact
   randy=Rnd(0,4)
   If randy=<3 Then EntityColor part(cyc),Rnd(50,200),0,0
   If randy=4 Then EntityColor part(cyc),Rnd(50,200),0,Rnd(50,200)
   partSize#(cyc)=Rnd#(1.0,5.0) : partFlight#(cyc)=0.2
   partGravity#(cyc)=Rnd#(0.5,1.0) : partWeight#(cyc)=0.1
   partAlpha#(cyc)=Rnd#(0.7,0.9) : partFade#(cyc)=0.04
  EndIf
  If partType(cyc)=4 ;yellow impact
   EntityColor part(cyc),Rnd(90,110),Rnd(70,90),Rnd(40,60)
   partSize#(cyc)=Rnd#(1.0,5.0) : partFlight#(cyc)=0.1
   partGravity#(cyc)=Rnd#(0.5,1.0) : partWeight#(cyc)=0.1
   partAlpha#(cyc)=Rnd#(0.6,0.8) : partFade#(cyc)=0.04
  EndIf
  If partType(cyc)=5 ;sweat spittle
   randy=Rnd(1,3)
   If randy=1 Then EntityColor part(cyc),200,200,200
   If randy=2 Then EntityColor part(cyc),230,200,100
   If randy=3 Then EntityColor part(cyc),150,200,250
   partSize#(cyc)=Rnd#(0.05,0.5) : partFlight#(cyc)=Rnd#(0.2,0.5)
   partGravity#(cyc)=Rnd#(1.0,2.0) : partWeight#(cyc)=0.15
   partAlpha#(cyc)=Rnd#(0.3,0.6) : partFade#(cyc)=0.01
  EndIf
  If partType(cyc)=6 ;blood spittle
   EntityColor part(cyc),255,0,0
   partSize#(cyc)=Rnd#(0.1,1.0) : partFlight#(cyc)=Rnd#(0.2,0.5)
   partGravity#(cyc)=Rnd#(1.0,2.0) : partWeight#(cyc)=0.2
   partAlpha#(cyc)=Rnd#(0.6,0.8) : partFade#(cyc)=0.015
  EndIf
  If partType(cyc)=7 ;dust
   EntityColor part(cyc),100,80,50 
   partSize#(cyc)=Rnd#(1.0,4.0) : partFlight#(cyc)=0.3
   partGravity#(cyc)=0.5 : partWeight#(cyc)=0.15
   partAlpha#(cyc)=Rnd#(0.2,0.5) : partFade#(cyc)=0.02 
  EndIf
  If partType(cyc)=8 ;water spit
   EntityColor part(cyc),40,80,120
   partSize#(cyc)=Rnd#(1.0,5.0) : partFlight#(cyc)=0.3
   partGravity#(cyc)=0.5 : partWeight#(cyc)=0.1
   partAlpha#(cyc)=Rnd#(0.6,0.9) : partFade#(cyc)=0.025
  EndIf
  If partType(cyc)=9 ;small fire
   randy=Rnd(0,4)
   If randy=<3 Then EntityColor part(cyc),220,Rnd(0,100),0
   If randy=4 Then EntityColor part(cyc),220,150,100
   partSize#(cyc)=Rnd#(1.0,4.0) : partFlight#(cyc)=0.3
   partGravity#(cyc)=Rnd#(0.75,1.25) : partWeight#(cyc)=0.15
   partAlpha#(cyc)=Rnd#(0.7,1.0) : partFade#(cyc)=0.075
  EndIf
  If partType(cyc)=10 ;multi-coloured spark
   randy=Rnd(0,4)
   If randy=0 Then EntityColor part(cyc),Rnd(100,250),Rnd(100,250),Rnd(100,250)
   If randy=1 Then EntityColor part(cyc),Rnd(150,250),50,50
   If randy=2 Then EntityColor part(cyc),50,Rnd(150,250),50
   If randy=3 Then EntityColor part(cyc),50,50,Rnd(150,250)
   If randy=4 Then EntityColor part(cyc),250,Rnd(100,250),Rnd(0,150)
   partSize#(cyc)=Rnd#(1.0,4.0) : partFlight#(cyc)=0.1
   partGravity#(cyc)=0.5 : partWeight#(cyc)=0.05
   partAlpha#(cyc)=Rnd#(0.4,0.8) : partFade#(cyc)=0.05
  EndIf
  If partType(cyc)=11 ;green mist
   EntityColor part(cyc),0,Rnd(100,225),0
   partSize#(cyc)=Rnd#(1.0,5.0) : partFlight#(cyc)=0.3
   partGravity#(cyc)=0.5 : partWeight#(cyc)=0.1
   partAlpha#(cyc)=Rnd#(0.7,1.0) : partFade#(cyc)=0.035
  EndIf
  If partType(cyc)=12 ;beer spit
   EntityColor part(cyc),Rnd(150,250),Rnd(50,200),0
   partSize#(cyc)=Rnd#(2.5,7.0) : partFlight#(cyc)=0.3
   partGravity#(cyc)=0.5 : partWeight#(cyc)=0.1
   partAlpha#(cyc)=Rnd#(0.3,0.5) : partFade#(cyc)=0.02
  EndIf
  If partType(cyc)=13 ;dark smoke
   randy=Rnd(0,100)
   EntityColor part(cyc),randy,randy,randy 
   partSize#(cyc)=Rnd#(1.0,4.0) : partFlight#(cyc)=0.3
   partGravity#(cyc)=0.5 : partWeight#(cyc)=0
   partAlpha#(cyc)=Rnd#(0.25,0.5) : partFade#(cyc)=0.01
  EndIf
  If partType(cyc)=14 ;blood spurt
   EntityColor part(cyc),Rnd(200,250),0,0
   partSize#(cyc)=Rnd#(3.5,7.5) : partFlight#(cyc)=Rnd#(0.3,0.6)
   partGravity#(cyc)=Rnd#(1.5,2.5) : partWeight#(cyc)=0.3
   partAlpha#(cyc)=Rnd#(0.8,1.0) : partFade#(cyc)=0.005
  EndIf
  If partType(cyc)=15 ;blood drop
   EntityColor part(cyc),Rnd(200,250),0,0
   partSize#(cyc)=Rnd#(1.0,3.0) : partFlight#(cyc)=Rnd#(0.0,0.1)
   partGravity#(cyc)=1.0 : partWeight#(cyc)=0.3
   partAlpha#(cyc)=Rnd#(0.8,1.0) : partFade#(cyc)=0.005
  EndIf

  If partType(cyc)=20 ;explosion (fire)
   randy=Rnd(0,4)
   If randy=<3 Then EntityColor part(cyc),220,Rnd(0,100),0
   If randy=4 Then EntityColor part(cyc),220,150,100
   partSize#(cyc)=Rnd#(7.5,15.0) : partFlight#(cyc)=0.3
   partGravity#(cyc)=0.5 : partWeight#(cyc)=0.025
   partAlpha#(cyc)=Rnd#(0.9,1.1) : partFade#(cyc)=0.03
  EndIf
  If partType(cyc)=21 ;explosion (foam)
   randy=Rnd(50,200)
   EntityColor part(cyc),randy,randy,randy
   partSize#(cyc)=Rnd#(7.5,15.0) : partFlight#(cyc)=0.5
   partGravity#(cyc)=1.0 : partWeight#(cyc)=0.025
   partAlpha#(cyc)=Rnd#(0.4,0.6) : partFade#(cyc)=0.02
  EndIf
  If partType(cyc)=22 ;explosion (water)
   randy=Rnd(0,2)
   If randy=<1 Then EntityColor part(cyc),40,80,120
   If randy=2 Then EntityColor part(cyc),50,50,Rnd(50,200)
   partSize#(cyc)=Rnd#(7.5,15.0) : partFlight#(cyc)=0.3
   partGravity#(cyc)=1.0 : partWeight#(cyc)=0.025
   partAlpha#(cyc)=Rnd#(0.7,0.9) : partFade#(cyc)=0.02
  EndIf
  If partType(cyc)=23 ;explosion (beer)
   EntityColor part(cyc),Rnd(150,250),Rnd(50,150),0
   partSize#(cyc)=Rnd#(10.0,20.0) : partFlight#(cyc)=0.5
   partGravity#(cyc)=1.0 : partWeight#(cyc)=0.025
   partAlpha#(cyc)=Rnd#(0.3,0.5) : partFade#(cyc)=0.01
  EndIf
  If partType(cyc)=24 ;explosion (white smoke)
   randy=Rnd(0,100)
   EntityColor part(cyc),randy,randy,randy 
   partSize#(cyc)=Rnd#(7.5,15.0) : partFlight#(cyc)=0.3 
   partGravity#(cyc)=0.25 : partWeight#(cyc)=0
   partAlpha#(cyc)=Rnd#(0.7,0.9) : partFade#(cyc)=0.01
  EndIf
  If partType(cyc)=25 ;explosion (black smoke)
   randy=Rnd(0,100)
   EntityColor part(cyc),randy,randy,randy 
   partSize#(cyc)=Rnd#(7.5,15.0) : partFlight#(cyc)=0.3
   partGravity#(cyc)=0.25 : partWeight#(cyc)=0
   partAlpha#(cyc)=Rnd#(0.5,0.8) : partFade#(cyc)=0.01
  EndIf
  ;reset & show
  partTim(cyc)=0
  partState(cyc)=1
  ShowEntity part(cyc)
  PositionEntity part(cyc),partX#(cyc),partY#(cyc),partZ#(cyc)
  RotateEntity part(cyc),0,partA#(cyc),0
  ScaleSprite part(cyc),partSize#(cyc),partSize#(cyc)
  EntityAlpha part(cyc),partAlpha#(cyc)
 EndIf
EndIf
End Function

;CREATE SPURT OF ONE PARTICLE
Function CreateSpurt(x#,y#,z#,angle#,spread#,density,style)
 If optFX>0
  If optFX=<1 Then density=density/2 
  If density<1 Then density=1
  For count=1 To density 
   If angle#=-1 Then CreateParticle(x#+Rnd(-spread#,spread#),y#+Rnd(-spread#,spread#),z#+Rnd(-spread#,spread#),Rnd(0,360),style)
   If angle#=>0 Then CreateParticle(x#+Rnd(-spread#,spread#),y#+Rnd(-spread#,spread#),z#+Rnd(-spread#,spread#),angle#+Rnd(-45,45),style)
  Next
 EndIf
End Function

;CREATE IMPACT SPURT
Function ImpactSpurt(x#,y#,z#,spread#,density)
 CreateSpurt(x#,y#,z#,-1,0.5*spread#,10,3) ;red
 CreateSpurt(x#,y#,z#,-1,0.5*spread#,10,4) ;yellow
 CreateSpurt(x#,y#,z#,-1,0,5,5) ;sweat
End Function

;BLOOD SPURT
Function BloodSpurt(x#,y#,z#,angle#,density,style) ;0=spittle, 1=full spurt
 If optGore>0 And density>0
  If style=<1 Then CreateSpurt(x#,y#,z#,angle#,0,density,6) ;spittle
  If style=1 Then CreateSpurt(x#,y#,z#,angle#,0,density,14) ;thick
  If optGore=>4
   If style=<1 Then CreateSpurt(x#,y#,z#,angle#,0,density/2,6) ;spittle
   If style=1 Then CreateSpurt(x#,y#,z#,angle#,0,density/2,14) ;thick
  EndIf
 EndIf
End Function

;ADD FIRE IMPACT SPURT
Function FireSpurt(x#,y#,z#,angle#)
 CreateParticle(x#,y#,z#,angle#,1)
 CreateParticle(x#,y#,z#,angle#,9) 
 CreateParticle(x#,y#,z#,angle#,2)
 CreateParticle(x#,y#,z#,angle#,13)
 CreateParticle(x#,y#,z#,angle#,5)
End Function

;CREATE SPIT MIST
Function CreateSpit(cyc,startTim,peakTim,style)
 limb=pLimb(cyc,1)
 If optFX=<1 Then density=2 Else density=4
 For count=1 To density
  PositionEntity dummy,pLimbX#(cyc,1),pLimbY#(cyc,1),pLimbZ#(cyc,1)
  RotateEntity dummy,EntityPitch(limb,1),EntityYaw(limb,1),EntityRoll(limb,1)
  factor#=GetPercent#(pAnimTim(cyc)-startTim,peakTim-startTim)
  If factor#>100 Then factor#=100
  If startTim=peakTim Then factor#=100
  pusher#=PercentOf#(Rnd(5,15),factor#)
  If pusher#<5 Or count=1 Then pusher#=5
  spray#=pusher#/5
  MoveEntity dummy,0,0,pusher#
  CreateParticle(EntityX(dummy)+Rnd#(-spray#,spray#),EntityY(dummy)+Rnd#(-spray#,spray#),EntityZ(dummy)+Rnd#(-spray#,spray#),EntityYaw(limb,1),style)
  If style=1 Or style=9
   If count=1
    CreateParticle(EntityX(dummy)+Rnd#(-spray#,spray#),EntityY(dummy)+Rnd#(-spray#,spray#),EntityZ(dummy)+Rnd#(-spray#,spray#),EntityYaw(limb,1),2)
    CreateParticle(EntityX(dummy)+Rnd#(-spray#,spray#),EntityY(dummy)+Rnd#(-spray#,spray#),EntityZ(dummy)+Rnd#(-spray#,spray#),EntityYaw(limb,1),13)
   EndIf
  Else
   If count=2 Then CreateParticle(EntityX(dummy)+Rnd#(-spray#,spray#),EntityY(dummy)+Rnd#(-spray#,spray#),EntityZ(dummy)+Rnd#(-spray#,spray#),EntityYaw(limb,1),5)
  EndIf
 Next
End Function

;PARTICLE CYCLE
Function ParticleCycle()
 For cyc=1 To no_particles
  If partState(cyc)>0
   ;flight
   ;partFlight#(cyc)=partFlight#(cyc)-0.01
   ;If partFlight#(cyc)<0 Then partFlight#(cyc)=0
   MoveEntity part(cyc),0,0,partFlight#(cyc)
   partX#(cyc)=EntityX(part(cyc))
   partZ#(cyc)=EntityZ(part(cyc))
   ;gravity
   partGravity#(cyc)=partGravity#(cyc)-partWeight#(cyc)
   If partType(cyc)=>20 And partGravity#(cyc)<0.1 Then partGravity#(cyc)=0.1
   partY#(cyc)=partY#(cyc)+partGravity#(cyc)
   ;land on ground
   landed=0
   If InsideRing(partX#(cyc),partZ#(cyc),-10)=0 And partY#(cyc)=<wGround# And partGravity#(cyc)<0 Then landed=1
   If InsideRing(partX#(cyc),partZ#(cyc),-10) And partY#(cyc)=>wStage#-2.0 And partY#(cyc)=<wStage# And partGravity#(cyc)<0 Then landed=1
   If landed=1
    If partType(cyc)=6 Or partType(cyc)=14 Or partType(cyc)=15
     randy=Rnd(0,6)
     If optFX=<1 Then randy=Rnd(0,12) 
     If randy=<1 And partType(cyc)=6 Then CreatePool(partX#(cyc),partZ#(cyc),Rnd(1.0,3.0),1,1)
     If randy=<6 And partType(cyc)=>14 And partType(cyc)=<15 Then CreatePool(partX#(cyc),partZ#(cyc),Rnd(2.0,7.0),1,1)
    EndIf
    partState(cyc)=0
   EndIf
   ;update properties
   PositionEntity part(cyc),partX#(cyc),partY#(cyc),partZ#(cyc)
   RotateEntity part(cyc),0,partA#(cyc),0
   If partType(cyc)=14 Or partType(cyc)=15 Then partSize#(cyc)=partSize#(cyc)-0.25
   ScaleSprite part(cyc),partSize#(cyc),partSize#(cyc)
   ;transparency
   partAlpha#(cyc)=partAlpha#(cyc)-partFade#(cyc)
   EntityAlpha part(cyc),partAlpha#(cyc)
   ;clock
   partTim(cyc)=partTim(cyc)+1
   If partAlpha#(cyc)=<0 Or partTim(cyc)>1000 Then partState(cyc)=0
  EndIf
  ;remove
  If partState(cyc)=0 Then HideEntity part(cyc)
 Next
End Function

;-----------------------------------------------------------------
;///////////////////////// EXPLOSIONS ////////////////////////////
;-----------------------------------------------------------------

;TRIGGER EXPLOSION
Function CreateExplosion(entity,x#,y#,z#,style)
 If optFX>0
  ;find empty slot
  cyc=0
  For count=1 To no_explodes
   If exTim(count)=0 Then cyc=count
  Next
  If cyc=0 Then cyc=Rnd(1,no_explodes)
  ;initiate explosion
  If style<1 Then style=1
  exType(cyc)=style : exTim(cyc)=20
  exX#(cyc)=x# : exY#(cyc)=y# : exZ#(cyc)=z#
  For v=1 To no_plays
   exSting(cyc,v)=1
  Next
  ;sound effect
  If exType(cyc)=1 Then ProduceSound(0,sExplosion,0,RoomVolume#(x#,z#,1))
  If exType(cyc)=>2 Then ProduceSound(0,sExplosion,0,RoomVolume#(x#,z#,0.6)) : ProduceSound(0,sSplash,22050,RoomVolume#(x#,z#,1))
  If InsideRing(x#,z#,10) Then ShakeRing(0,1.0)
  If matchBlastTim=0 And gamAgreement(15)=0 Then entScore=entScore+250 : entHardcore=entHardcore+100
 EndIf
End Function

;EXPLOSION CYCLE
Function ExplosionCycle()
 For cyc=1 To no_explodes
  If exTim(cyc)>0
   ;blaze sequence
   If exTim(cyc)=19 Then Pop(0,Rnd(2,8),1)
   If exTim(cyc)=20 Or exTim(cyc)=15 Or exTim(cyc)=10 Or exTim(cyc)=5
    billow=GetPercent#(20-exTim(cyc),5)
    If billow>100 Then billow=100
    range#=PercentOf#(5,billow)
    If exType(cyc)=1
     If optFX=<1 Then randy=Rnd(0,1) Else randy=0
     If randy=0 Then CreateParticle(exX#(cyc),exY#(cyc),exZ#(cyc),-1,19+exType(cyc))
     If optFX=<1 Then density=1 Else density=2
     For count=1 To density
      CreateParticle(exX#(cyc)+Rnd#(-range#,range#),exY#(cyc)+Rnd#(-range#,range#),exZ#(cyc)+Rnd#(-range#,range#),-1,19+exType(cyc))
     Next
     If optFX=<1 Then density=4 Else density=8
     For count=1 To density
      range#=PercentOf#(10,billow)
      CreateParticle(exX#(cyc)+Rnd#(-range#,range#),exY#(cyc)+Rnd#(-range#,range#),exZ#(cyc)+Rnd#(-range#,range#),-1,19+exType(cyc))
     Next
    EndIf
    If optFX=<1 Then density=7 Else density=14
    If exType(cyc)>1 Then density=density+(density/2)
    For count=1 To density
     range#=PercentOf#(15,billow)
     CreateParticle(exX#(cyc)+Rnd#(-range#,range#),exY#(cyc)+Rnd#(-range#,range#),exZ#(cyc)+Rnd#(-range#,range#),-1,19+exType(cyc))
    Next
    If exType(cyc)=<2
     If optFX=<1 Then randy=Rnd(0,1) Else randy=0
     If randy=0 Then CreateParticle(exX#(cyc)+Rnd#(-range#,range#),exY#(cyc)+Rnd#(-range#,range#),exZ#(cyc)+Rnd#(-range#,range#),-1,24)
     If optFX=<1 Then randy=Rnd(0,1) Else randy=0
     If randy=0 Then CreateParticle(exX#(cyc)+Rnd#(-range#,range#),exY#(cyc)+Rnd#(-range#,range#),exZ#(cyc)+Rnd#(-range#,range#),-1,25)
    EndIf
   EndIf
   ;mess
   If exTim(cyc)=10 And exType(cyc)>1 
    ;ProduceSound(0,sBleed,22050,0.5)
    CreatePool(exX#(cyc),exZ#(cyc),Rnd(10.0,15.0),1,exType(cyc))
   EndIf
   ;human damage
   If exTim(cyc)=>5 And exTim(cyc)=<15
    For v=1 To no_plays
     If BlastProximity(cyc,RealX#(v),pY#(v),RealZ#(v),30) Then RiskBlindness(v,9999)
     If BlastProximity(cyc,RealX#(v),pY#(v),RealZ#(v),20) And exSting(cyc,v)=1 ;And AttackViable(v)>0
      Pop(0,Rnd(2,8),0)
      If exType(cyc)=1 Then ProduceSound(p(v),sIgnite,22050,0)
      ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),0)
      entScore=entScore+250 : entHardcore=entHardcore+50
      If exType(cyc)=1
       If matchBlastTim>0 Or matchRopes=2 Then damage=2 Else damage=1
       ScarArea(v,0,0,0,damage)
       If BlastProximity(cyc,RealX#(v),pY#(v),RealZ#(v),10) Then pHealth(v)=pHealth(v)-Rnd(250,1000) Else pHealth(v)=pHealth(v)-Rnd(100,500)
       entScore=entScore+250 : entHardcore=entHardcore+250
       If matchBlastTim>0 Or matchRopes=2 Then risk=2 Else risk=1
       RiskInjury(v,Rnd(0,5),risk) 
      EndIf
      If AttackViable(v)=>3
       GroundImpactChecks(v)
       If BlastProximity(cyc,RealX#(v),pY#(v),RealZ#(v),10)=0 Then GroundReaction(v)
       pA#(v)=pA#(v)+Rnd(-25,25)
      Else 
       ImpactChecks(v)
       pHP(v)=0 : pHurtA#(v)=Rnd(0,360)
       If AttackViable(v)>0 And BlastProximity(cyc,RealX#(v),pY#(v),RealZ#(v),10)=0 And pPlatform(cyc)=0 Then ChangeAnim(v,Rnd(102,105))
       pA#(v)=pA#(v)+Rnd(-45,45)
      EndIf
      If (AttackViable(v)=0 Or BlastProximity(cyc,RealX#(v),pY#(v),RealZ#(v),10)) And pPlatform(cyc)=0 Then ChangeAnim(v,Rnd(143,144))
      exSting(cyc,v)=0
     EndIf
    Next
   EndIf
   ;damage items
   If exTim(cyc)=15 And exType(cyc)=1
    For v=1 To no_items
     If BlastProximity(cyc,iX#(v),iY#(v),iZ#(v),30)
      ProduceSound(i(v),iSound(iType(v)),22050,1)
      If iState(v)=0 Then Animate i(v),3,3.0,1,0 Else Animate i(v),3,2.0,2,5
      iState(v)=1 : iA#(v)=iA#(v)+Rnd(-20,20)
      iX#(v)=iX#(v)+Rnd#(-2,2) : iZ#(v)=iZ#(v)+Rnd#(-2,2)
      IgniteItem(v)
      entScore=entScore+100 : entHardcore=entHardcore+50
     EndIf
    Next
    For v=1 To no_weaps
     If BlastProximity(cyc,weapX#(v),weapY#(v),weapZ#(v),15) Then IgniteWeapon(v)
    Next
   EndIf
   ;extinguish items
   If exTim(cyc)=15 And exType(cyc)>1
    For v=1 To no_items
     If BlastProximity(cyc,iX#(v),iY#(v),iZ#(v),30) And iBurning(v)>0 Then ExtinguishItem(v)
    Next
    For v=1 To no_weaps
     If BlastProximity(cyc,weapX#(v),weapY#(v),weapZ#(v),30) And weapBurning(v)>0 Then ExtinguishWeapon(v)
    Next
   EndIf
   ;expire
   exTim(cyc)=exTim(cyc)-1
  EndIf
 Next
 ;BLAST GIMMICK
 ;initial blast
 If matchBlastTim>0 And matchMins=matchBlastTim And matchBlasted=0
  PlaySound sBuzzer : matchBlasted=1
  CreateExplosion(0,-50,wStage#,50,1) : CreateExplosion(0,50,wStage#,50,1)
  CreateExplosion(0,-50,wStage#,-50,1) : CreateExplosion(0,50,wStage#,-50,1) 
  CreateExplosion(0,0,wStage#,0,1)
 EndIf
 ;follow-up blasts
 randy=Rnd(0,200)
 If randy=<1 And matchState=3 And matchBlastTim>0 And matchMins=>matchBlastTim
  x#=Rnd(-130,130) : z#=Rnd(-130,130)
  If InsideRing(x#,z#,-5) Then y#=wStage# Else y#=wGround#
  CreateExplosion(0,x#,y#,z#,1)
 EndIf
 ;terrorism
 If game=1 And gamAgreement(15)>0 And matchState=>3 And screenAgenda<>10
  randy=Rnd(0,500)
  If randy=<1 And matchState=3 And matchBlastTim>0 And matchMins=>matchBlastTim
   x#=Rnd(-200,200) : z#=Rnd(-400,400)
   If randy=0 Then x#=Rnd(-130,130) : z#=Rnd(-130,130)
   If InsideRing(x#,z#,-5) Then y#=wStage# Else y#=wGround#
   CreateExplosion(0,x#,y#,z#,1)
  EndIf
 EndIf
End Function

;BLAST PROXIMITY
Function BlastProximity(cyc,x#,y#,z#,range#)
 value=0
 If y#>exY#(cyc)-30 And y#<exY#(cyc)+30
  If x#>exX#(cyc)-range# And x#<exX#(cyc)+range# And z#>exZ#(cyc)-range# And z#<exZ#(cyc)+range# Then value=1
 EndIf
 Return value
End Function

;IGNITE ITEM
Function IgniteItem(cyc)
 If optFX>0 And iFlammable(iType(cyc))>0 And iBurning(cyc)=0
  ;effect
  Pop(0,Rnd(4,7),0)
  ProduceSound(i(cyc),sIgnite,22050,1)
  LoopSound sFire
  iChannel(cyc)=EmitSound(sFire,i(cyc))
  CreateSpurt(iX#(cyc),iY#(cyc),iZ#(cyc),-1,5,10,1) 
  iBurning(cyc)=Rnd(100,1000)
  entScore=entScore+100 : entHardcore=entHardcore+50
  ;char material
  If iScar(cyc)<1 Then iScar(cyc)=1
  For limb=1 To CountChildren(i(cyc))
   EntityTexture GetChild(i(cyc),limb),tCharred,0,3
  Next
 EndIf
End Function

;EXTINGUISH ITEM
Function ExtinguishItem(cyc)
 ProduceSound(i(cyc),sExpire,22050,1)
 If ChannelPlaying(iChannel(cyc)) Then StopChannel iChannel(cyc)
 CreateSpurt(iX#(cyc),iY#(cyc),iZ#(cyc),-1,5,10,2)
 iBurning(cyc)=0
End Function

;IGNITE WEAPON
Function IgniteWeapon(cyc)
 If optFX>0 And weapFlammable(weapType(cyc))>0 And weapBurning(cyc)=0
  Pop(0,Rnd(4,7),0)
  ProduceSound(weap(cyc),sIgnite,22050,1)
  LoopSound sFire
  weapChannel(cyc)=EmitSound(sFire,weap(cyc))
  CreateSpurt(weapX#(cyc),weapY#(cyc),weapZ#(cyc),-1,1,10,1)
  If weapScar(cyc)<1 Then weapScar(cyc)=1 
  weapBurning(cyc)=Rnd(100,1000)
  entScore=entScore+50 : entHardcore=entHardcore+25
 EndIf
End Function

;EXTINGUISH WEAPON
Function ExtinguishWeapon(cyc)
 ProduceSound(weap(cyc),sExpire,22050,1)
 If ChannelPlaying(weapChannel(cyc)) Then StopChannel weapChannel(cyc)
 CreateSpurt(weapX#(cyc),weapY#(cyc),weapZ#(cyc),-1,1,10,2)
 weapBurning(cyc)=0
End Function

;IGNITE ROPE
Function IgniteRope(cyc)
 If optFX>0 And matchRopes=0
  ;effect
  Pop(0,Rnd(2,8),0)
  ProduceSound(rope(cyc),sIgnite,22050,1)
  LoopSound sFire
  ropeChannel(cyc)=EmitSound(sFire,rope(cyc)) 
  ropeBurning(cyc)=Rnd(100,1000)
  ;char material
  For count=1 To 4
   EntityTexture FindChild(rope(cyc),"Rope0"+count),tCharred,0,3
  Next
 EndIf
End Function

;EXTINGUISH ROPE
Function ExtinguishRope(cyc)
 ProduceSound(rope(cyc),sExpire,22050,1)
 If ChannelPlaying(ropeChannel(cyc)) Then StopChannel ropeChannel(cyc)
 ropeBurning(cyc)=0
End Function

;-----------------------------------------------------------------
;//////////////////////////// POOLS //////////////////////////////
;-----------------------------------------------------------------

;LOAD POOLS
Function LoadPools()
 For cyc=1 To no_pools
  pool(cyc)=LoadSprite("World/Sprites/Pool.png",4)
  SpriteViewMode pool(cyc),2
  HideEntity pool(cyc)
  poolState(cyc)=0
 Next
End Function

;PRODUCE POOL
Function CreatePool(x#,z#,size#,layers,style)
 If optFX>0 And (optGore=>2 Or style<>1)
  ;check for overlap
  overlap=0
  For v=1 To no_pools
   If poolState(v)>0 And poolAlpha#(v)>0.35
    If ReachedCord(x#,z#,poolX#(v),poolZ#(v),size#) Or ReachedCord(poolX#(v),poolZ#(v),x#,z#,poolSize#(v)) Then overlap=1
   EndIf
  Next
  ;proceed to create
  If overlap=0
   For count=1 To layers
    ;find empty spot
    cyc=0
    For count=1 To no_pools
     If poolState(count)=0 Then cyc=count
    Next
    ;force spot!
    If cyc=0 Then cyc=Rnd(1,no_pools)
    ;generate pool
    poolX#(cyc)=x# : poolZ#(cyc)=z#
    If count>1 Then poolX#(cyc)=x#+Rnd(-5,5) : poolZ#(cyc)=z#+Rnd(-5,5)
    poolA#(cyc)=Rnd(0,360)
    poolY#(cyc)=wGround#+0.1
    If arenaMatting>0 And InsideRing(x#,z#,20) Then poolY#(cyc)=wGround#+1.5
    If InsideRing(x#,z#,-10) Then poolY#(cyc)=wStage#+0.55
    poolSize#(cyc)=size# : poolAlpha#(cyc)=0.7 
    If optGore=>4 And style=1 Then poolSize#(cyc)=Rnd(poolSize#(cyc),poolSize#(cyc)+(poolSize#(cyc)/2))
    poolState(cyc)=1 : ShowEntity pool(cyc)
    ;colour variations
    poolType(cyc)=style
    If style=1 Then EntityColor pool(cyc),Rnd(150,220),0,0 ;blood
    If style=2 Then EntityColor pool(cyc),255,255,255 ;foam
    If style=3 Then EntityColor pool(cyc),100,200,255 ;water
    If style=4 Then EntityColor pool(cyc),150,50,0 ;beer
   Next
  EndIf
 EndIf
End Function

;POOL CYCLE
Function PoolCycle()
 For cyc=1 To no_pools
  If poolState(cyc)=1
   ;location
   PositionEntity pool(cyc),poolX#(cyc),poolY#(cyc),poolZ#(cyc)
   RotateEntity pool(cyc),90,poolA#(cyc),0
   ;fade away
   If poolType(cyc)=1
    ;If optGore=>4 Then poolAlpha#(cyc)=poolAlpha#(cyc)-0.000005 Else poolAlpha#(cyc)=poolAlpha#(cyc)-0.00005
    poolAlpha#(cyc)=poolAlpha#(cyc)-0.000005
   Else 
    poolAlpha#(cyc)=poolAlpha#(cyc)-0.0005
   EndIf
   EntityAlpha pool(cyc),poolAlpha#(cyc)
   ;shrink away
   ;If poolType(cyc)=1 And optGore=>4 Then poolSize#(cyc)=poolSize#(cyc)-0.001 Else poolSize#(cyc)=poolSize#(cyc)-0.01
   poolSize#(cyc)=poolSize#(cyc)-0.001
   ScaleSprite pool(cyc),poolSize#(cyc),poolSize#(cyc)
   ;remove
   If poolSize#(cyc)<0.5 Or poolAlpha#(cyc)<0.01 Then poolState(cyc)=0 : HideEntity pool(cyc)
  EndIf
 Next
End Function