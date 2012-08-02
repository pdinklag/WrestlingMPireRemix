;//////////////////////////////////////////////////////////////////////////////
;--------------------- WRESTLING MPIRE 2008: BUCKLE MOVES ---------------------
;//////////////////////////////////////////////////////////////////////////////

;--------------------------------------------------------------------
;//////////////////////////// ROPES /////////////////////////////////
;--------------------------------------------------------------------
;LOAD ROPES
Function LoadRopes()
 ;get rope positions
 y#=49 : s=0
 For count=1 To 3
  ropeX#(s+1)=0 : ropeY#(s+1)=y# : ropeZ#(s+1)=73 : ropeA#(s+1)=0 ;north
  ropeX#(s+2)=73 : ropeY#(s+2)=y# : ropeZ#(s+2)=0 : ropeA#(s+2)=270 ;east
  ropeX#(s+3)=0 : ropeY#(s+3)=y# : ropeZ#(s+3)=-73 : ropeA#(s+3)=180 ;south
  ropeX#(s+4)=-73 : ropeY#(s+4)=y# : ropeZ#(s+4)=0 : ropeA#(s+4)=90 ;west
  y#=y#-8 : s=s+4
 Next
 ;construct ropes
 For cyc=1 To 12
  If matchRopes=0 Or (matchRopes=1 And arenaRopes>0)
   rope(cyc)=LoadAnimMesh("World/Ropes/Rope.3ds")
  Else
   rope(cyc)=LoadAnimMesh("World/Ropes/Novelty.3ds")
  EndIf
  For count=1 To 8 
   EntityShininess FindChild(rope(cyc),"Barbs0"+count),1.0
   If matchRopes=0 Then HideEntity FindChild(rope(cyc),"Barbs0"+count)
   If optDetail=<1 And count>4 Then HideEntity FindChild(rope(cyc),"Barbs0"+count)
  Next
  ropeSeq(cyc,1)=ExtractAnimSeq(rope(cyc),0,30,0) ;static
  ropeSeq(cyc,11)=ExtractAnimSeq(rope(cyc),635,720,0) ;whole shake (mild)
  ropeSeq(cyc,12)=ExtractAnimSeq(rope(cyc),40,130,0) ;whole shake (major)
  ropeSeq(cyc,21)=ExtractAnimSeq(rope(cyc),960,1050,0) ;shake segment A
  ropeSeq(cyc,22)=ExtractAnimSeq(rope(cyc),740,830,0) ;shake segment B
  ropeSeq(cyc,23)=ExtractAnimSeq(rope(cyc),850,940,0) ;shake segment C
  ropeSeq(cyc,31)=ExtractAnimSeq(rope(cyc),310,450,0) ;whip segment A
  ropeSeq(cyc,32)=ExtractAnimSeq(rope(cyc),150,290,0) ;whip segment B
  ropeSeq(cyc,33)=ExtractAnimSeq(rope(cyc),470,610,0) ;whip segment C
  PositionEntity rope(cyc),ropeX#(cyc),ropeY#(cyc),ropeZ#(cyc)
  RotateEntity rope(cyc),0,ropeA#(cyc),0
  ropeAnim(cyc)=0 : ropeAnimTim(cyc)=0
  ropeBurning(cyc)=0
  ;get rope textures
  If arenaRopes=<9 Then colA=tRope(arenaRopes) : colB=tRope(arenaRopes) : colC=tRope(arenaRopes)
  If arenaRopes=10 Then colA=tRope(2) : colB=tRope(1) : colC=tRope(2) ;black & white 
  If arenaRopes=11 Then colA=tRope(7) : colB=tRope(3) : colC=tRope(2) ;MDickie (Dark)
  If arenaRopes=12 Then colA=tRope(6) : colB=tRope(7) : colC=tRope(3) ;MDickie (Light)
  If arenaRopes=13 Then colA=tRope(3) : colB=tRope(1) : colC=tRope(7) ;FedOn
  If arenaRopes=14 Then colA=tRope(3) : colB=tRope(1) : colC=tRope(4) ;american
  If arenaRopes=15 Then colA=tRope(4) : colB=tRope(1) : colC=tRope(3) ;british
  If arenaRopes=16 Then colA=tRope(4) : colB=tRope(1) : colC=tRope(2) ;scottish 
  If arenaRopes=17 Then colA=tRope(5) : colB=tRope(1) : colC=tRope(7) ;irish
  If arenaRopes=18 Then colA=tRope(2) : colB=tRope(1) : colC=tRope(6) ;UKW
  If arenaRopes=19 Then colA=tRope(3) : colB=tRope(9) : colC=tRope(7) ;RSP
  If arenaRopes=20 Then colA=tRope(1) : colB=tRope(3) : colC=tRope(1) ;Japanese
  If arenaRopes=21 Then colA=tRope(3) : colB=tRope(1) : colC=tRope(3) ;canadian
  If arenaRopes=22 Then colA=tRope(3) : colB=tRope(1) : colC=tRope(5) ;mexican
  ;apply rope colours
  For count=1 To 4
   limb=FindChild(rope(cyc),"Rope0"+count)
   If matchRopes=0 Or (matchRopes=1 And arenaRopes>0)
    If cyc=>1 And cyc=<4 Then EntityTexture limb,colA
    If cyc=>5 And cyc=<8 Then EntityTexture limb,colB
    If cyc=>9 And cyc=<12 Then EntityTexture limb,colC
   EndIf
   If matchRopes=1 And arenaRopes=0 Then EntityTexture limb,tBarbedRope : EntityShininess limb,0.5
   If matchRopes=2 Then EntityTexture limb,tElectricRope : EntityShininess limb,0.5 : EntityFX limb,1
   If matchRopes=3 Then EntityTexture limb,tHotRope : EntityFX limb,1
  Next
 Next 
 ;rope sounds
 If matchRopes=2 
  LoopSound sPower
  chRopes=EmitSound(sPower,FindChild(world,"Canvas"))
  ChannelVolume chRopes,0.15
 EndIf
 If matchRopes=3 
  LoopSound sFire
  chRopes=EmitSound(sFire,FindChild(world,"Canvas"))
  ChannelVolume chRopes,0.15
 EndIf
End Function

;ROPE CYCLE
Function RopeCycle()
 For cyc=1 To 12
  ;stop
  If ropeAnim(cyc)=0
   If ropeAnimTim(cyc)=0 Then Animate rope(cyc),3,0,ropeSeq(cyc,1),20
  EndIf
  ;shakes
  If (ropeAnim(cyc)=>11 And ropeAnim(cyc)=<12) Or (ropeAnim(cyc)=>21 And ropeAnim(cyc)=<23)
   If ropeAnimTim(cyc)=0 Then Animate rope(cyc),3,Rnd(1.5,1.8),ropeSeq(cyc,ropeAnim(cyc)),5
   randy=Rnd(0,200)
   If (randy=0 And ropeAnimTim(cyc)>10) Or ropeAnimTim(cyc)>100
    ropeAnim(cyc)=0 : ropeAnimTim(cyc)=-1
   EndIf
  EndIf
  ;whips
  If ropeAnim(cyc)=>31 And ropeAnim(cyc)=<33
   If ropeAnimTim(cyc)=0 Then Animate rope(cyc),3,ropeAnimSpeed#(cyc),ropeSeq(cyc,ropeAnim(cyc)),15/ropeAnimSpeed#(cyc)
   randy=Rnd(0,200)
   If (randy=0 And ropeAnimTim(cyc)>60/ropeAnimSpeed#(cyc)) Or ropeAnimTim(cyc)>150/ropeAnimSpeed#(cyc)
    ropeAnim(cyc)=0 : ropeAnimTim(cyc)=-1
   EndIf
  EndIf
  ;increment
  ropeAnimTim(cyc)=ropeAnimTim(cyc)+1
  ;burning effect
  ;If (ropeBurning(cyc)>0 Or matchRopes=>2) And optFX>0
   ;randy=Rnd(0,100)
   ;If optFX=<1 Then randy=Rnd(0,200)
   ;If ropeA#(cyc)=0 Or ropeA#(cyc)=180 
    ;If ropeBurning(cyc)>0 Or matchRopes=3 Then CreateParticle(ropeX#(cyc)+Rnd(-70,70),ropeY#(cyc)-1,ropeZ#(cyc),-1,9)
    ;If randy=<1 And matchRopes=2 Then CreateParticle(ropeX#(cyc)+Rnd(-70,70),ropeY#(cyc)-1,ropeZ#(cyc),-1,10) 
    ;If randy=0 Then CreateParticle(ropeX#(cyc)+Rnd(-70,70),ropeY#(cyc)-1,ropeZ#(cyc),-1,2)
    ;If randy=1 Then CreateParticle(ropeX#(cyc)+Rnd(-70,70),ropeY#(cyc)-1,ropeZ#(cyc),-1,13)
   ;EndIf
   ;If ropeA#(cyc)=90 Or ropeA#(cyc)=270 
    ;If ropeBurning(cyc)>0 Or matchRopes=3 Then CreateParticle(ropeX#(cyc),ropeY#(cyc)-1,ropeZ#(cyc)+Rnd(-70,70),-1,9)
    ;If randy=<1 And matchRopes=2 Then CreateParticle(ropeX#(cyc),ropeY#(cyc)-1,ropeZ#(cyc)+Rnd(-70,70),-1,10)
    ;If randy=0 Then CreateParticle(ropeX#(cyc),ropeY#(cyc)-1,ropeZ#(cyc)+Rnd(-70,70),-1,2)
    ;If randy=1 Then CreateParticle(ropeX#(cyc),ropeY#(cyc)-1,ropeZ#(cyc)+Rnd(-70,70),-1,13)
   ;EndIf
   ;If ropeBurning(cyc)>0
    ;ropeBurning(cyc)=ropeBurning(cyc)-1
    ;If ropeBurning(cyc)=<0 Then ExtinguishRope(cyc)
   ;EndIf
  ;EndIf
  ;update display
  PositionEntity rope(cyc),ropeX#(cyc)+ringOffsetX#(cyc+50),ropeY#(cyc)+ringOffsetY#(cyc+50),ropeZ#(cyc)+ringOffsetZ#(cyc+50)
 Next
 ;electric flow
 ropeFlow#=ropeFlow#+0.25
 If matchRopes=2 Then PositionTexture tElectricRope,1,ropeFlow#
 If matchRopes=3 Then PositionTexture tHotRope,1,ropeFlow#
End Function

;--------------------------------------------------------------------
;///////////////////////////// CAGE /////////////////////////////////
;--------------------------------------------------------------------
;LOAD CAGE
Function LoadCage()
 ;load model
 cage=LoadAnimMesh("World/Cage/Cage.3ds")
 For cyc=1 To 4
  ;clock original positions
  wall=FindChild(cage,"Frame0"+cyc)
  cageOrigX#(cyc)=EntityX#(wall,1) : cageOrigY#(cyc)=EntityY#(wall,1) : cageOrigZ#(cyc)=EntityZ#(wall,1)
  cageX#(cyc)=cageOrigX#(cyc) : cageY#(cyc)=cageOrigY#(cyc) : cageZ#(cyc)=cageOrigZ#(cyc)
  cageTX#(cyc)=cageX#(cyc) : cageTY#(cyc)=cageY#(cyc)  : cageTZ#(cyc)=cageZ#(cyc) 
  cageOrigXA#(cyc)=EntityPitch#(wall,1) : cageOrigYA#(cyc)=EntityYaw#(wall,1) : cageOrigZA#(cyc)=EntityRoll#(wall,1)
  cageXA#(cyc)=cageOrigXA#(cyc) : cageYA#(cyc)=cageOrigYA#(cyc) : cageZA#(cyc)=cageOrigZA#(cyc)
  cageTXA#(cyc)=cageXA#(cyc) : cageTYA#(cyc)=cageYA#(cyc)  : cageTZA#(cyc)=cageZA#(cyc)  
  cageShakeTim(cyc)=0
  ;apply texture
  If matchCage=1 
   EntityTexture FindChild(cage,"Frame0"+cyc),tGirder(1)
   EntityTexture FindChild(cage,"Wall0"+cyc),tCage(2)
  EndIf
  If matchCage=>2 And matchCage=<4 
   EntityTexture FindChild(cage,"Frame0"+cyc),tGirder(2)
   EntityTexture FindChild(cage,"Wall0"+cyc),tCage(1)
   If matchCage=3 
    EntityColor FindChild(cage,"Wall0"+cyc),80,120,180
    EntityColor FindChild(cage,"Frame0"+cyc),80,120,180
   EndIf
   If matchCage=4 
    EntityColor FindChild(cage,"Wall0"+cyc),75,75,75
    EntityColor FindChild(cage,"Frame0"+cyc),75,75,75
   EndIf
  EndIf
  ;add shine
  EntityShininess FindChild(cage,"Wall0"+cyc),0.5
  EntityShininess FindChild(cage,"Frame0"+cyc),0.5
 Next
End Function

;CAGE CYCLE
Function CageCycle()
 If matchCage>0
  For cyc=1 To 4
   ;timer
   cageShakeTim(cyc)=cageShakeTim(cyc)-1
   If cageShakeTim(cyc)<0 Then cageShakeTim(cyc)=0
   ;adjust orientation
   If cageXA#(cyc)<cageTXA#(cyc) Then cageXA#(cyc)=cageXA#(cyc)+cageSpeedXA#(cyc)
   If cageXA#(cyc)>cageTXA#(cyc) Then cageXA#(cyc)=cageXA#(cyc)-cageSpeedXA#(cyc)
   If cageShakeTim(cyc)>0 And cageXA#(cyc)=>cageTXA#(cyc)-cageSpeedXA#(cyc) And cageXA#(cyc)=<cageTXA#(cyc)+cageSpeedXA#(cyc)
    If cageTXA#(cyc)<0 Then cageTXA#(cyc)=MakePositive#(cageTXA#(cyc))/2 Else cageTXA#(cyc)=-(cageTXA#(cyc)/2)
    cageSpeedXA#(cyc)=GetDiff#(cageXA#(cyc),cageTXA#(cyc))/10
   EndIf
   If cageShakeTim(cyc)=0 Then cageTXA#(cyc)=cageOrigXA#(cyc) : cageSpeedXA#(cyc)=0.01
   If cageYA#(cyc)<cageTYA#(cyc) Then cageYA#(cyc)=cageYA#(cyc)+cageSpeedYA#(cyc)
   If cageYA#(cyc)>cageTYA#(cyc) Then cageYA#(cyc)=cageYA#(cyc)-cageSpeedYA#(cyc)
   If cageShakeTim(cyc)>0 And cageYA#(cyc)=>cageTYA#(cyc)-cageSpeedYA#(cyc) And cageYA#(cyc)=<cageTYA#(cyc)+cageSpeedYA#(cyc)
    If cageTYA#(cyc)<0 Then cageTYA#(cyc)=MakePositive#(cageTYA#(cyc))/2 Else cageTYA#(cyc)=-(cageTYA#(cyc)/2)
    cageSpeedYA#(cyc)=GetDiff#(cageYA#(cyc),cageTYA#(cyc))/10
   EndIf
   If cageShakeTim(cyc)=0 Then cageTYA#(cyc)=cageOrigYA#(cyc) : cageSpeedYA#(cyc)=0.01
   If cageZA#(cyc)<cageTZA#(cyc) Then cageZA#(cyc)=cageZA#(cyc)+cageSpeedZA#(cyc)
   If cageZA#(cyc)>cageTZA#(cyc) Then cageZA#(cyc)=cageZA#(cyc)-cageSpeedZA#(cyc)
   If cageShakeTim(cyc)>0 And cageZA#(cyc)=>cageTZA#(cyc)-cageSpeedZA#(cyc) And cageZA#(cyc)=<cageTZA#(cyc)+cageSpeedZA#(cyc)
    If cageTZA#(cyc)<0 Then cageTZA#(cyc)=MakePositive#(cageTZA#(cyc))/2 Else cageTZA#(cyc)=-(cageTZA#(cyc)/2)
    cageSpeedZA#(cyc)=GetDiff#(cageZA#(cyc),cageTZA#(cyc))/10
   EndIf
   If cageShakeTim(cyc)=0 Then cageTZA#(cyc)=cageOrigZA#(cyc) : cageSpeedZA#(cyc)=0.01
   ;update display
   PositionEntity FindChild(cage,"Frame0"+cyc),cageX#(cyc)+ringOffsetX#(cyc+70),cageY#(cyc)+ringOffsetY#(cyc+70),cageZ#(cyc)+ringOffsetZ#(cyc+70)
   RotateEntity FindChild(cage,"Frame0"+cyc),cageXA#(cyc),cageYA#(cyc),cageZA#(cyc)
   ;transparency
   ghost#=1.0
   If InsideRing(camPivX#,camPivZ#,-5)
    If cyc=1 And camZ#>85 Then ghost#=0.5
    If cyc=2 And camX#>85 Then ghost#=0.5
    If cyc=3 And camZ#<-85 Then ghost#=0.5
    If cyc=4 And camX#<-85 Then ghost#=0.5
   EndIf
   EntityAlpha FindChild(cage,"Frame0"+cyc),ghost#
   EntityAlpha FindChild(cage,"Wall0"+cyc),ghost#
  Next
 EndIf
End Function

;SHAKE CAGE
Function ShakeCage(cyc,shake#)
 ;north side
 If cyc=1
  cageTXA#(cyc)=cageOrigXA#(cyc)+shake# : cageSpeedXA#(cyc)=GetDiff#(cageXA#(cyc),cageTXA#(cyc))/5
  cageTYA#(cyc)=cageOrigYA#(cyc)+Rnd#(-(shake#/2),shake#/2) : cageSpeedYA#(cyc)=GetDiff#(cageYA#(cyc),cageTYA#(cyc))/5
  cageTZA#(cyc)=cageOrigZA#(cyc)+Rnd#(-(shake#/2),shake#/2) : cageSpeedZA#(cyc)=GetDiff#(cageZA#(cyc),cageTZA#(cyc))/5
  cageShakeTim(cyc)=50
 EndIf
 ;east side
 If cyc=2
  cageTXA#(cyc)=cageOrigXA#(cyc)+Rnd#(-(shake#/2),shake#/2) : cageSpeedXA#(cyc)=GetDiff#(cageXA#(cyc),cageTXA#(cyc))/5
  cageTYA#(cyc)=cageOrigYA#(cyc)+Rnd#(-(shake#/2),shake#/2) : cageSpeedYA#(cyc)=GetDiff#(cageYA#(cyc),cageTYA#(cyc))/5
  cageTZA#(cyc)=cageOrigZA#(cyc)-shake# : cageSpeedZA#(cyc)=GetDiff#(cageZA#(cyc),cageTZA#(cyc))/5
  cageShakeTim(cyc)=50
 EndIf
 ;south side
 If cyc=3
  cageTXA#(cyc)=cageOrigXA#(cyc)-shake# : cageSpeedXA#(cyc)=GetDiff#(cageXA#(cyc),cageTXA#(cyc))/5
  cageTYA#(cyc)=cageOrigYA#(cyc)+Rnd#(-(shake#/2),shake#/2) : cageSpeedYA#(cyc)=GetDiff#(cageYA#(cyc),cageTYA#(cyc))/5
  cageTZA#(cyc)=cageOrigZA#(cyc)+Rnd#(-(shake#/2),shake#/2) : cageSpeedZA#(cyc)=GetDiff#(cageZA#(cyc),cageTZA#(cyc))/5
  cageShakeTim(cyc)=50
 EndIf
 ;west side
 If cyc=4
  cageTXA#(cyc)=cageOrigXA#(cyc)+Rnd#(-(shake#/2),shake#/2) : cageSpeedXA#(cyc)=GetDiff#(cageXA#(cyc),cageTXA#(cyc))/5
  cageTYA#(cyc)=cageOrigYA#(cyc)+Rnd#(-(shake#/2),shake#/2) : cageSpeedYA#(cyc)=GetDiff#(cageYA#(cyc),cageTYA#(cyc))/5
  cageTZA#(cyc)=cageOrigZA#(cyc)+shake# : cageSpeedZA#(cyc)=GetDiff#(cageZA#(cyc),cageTZA#(cyc))/5
  cageShakeTim(cyc)=50
 EndIf
 ;shake off climbers
 If shake#=>1.0
  For v=1 To no_plays
   If pPlatform(v)=90+cyc Or pPlatform(v)=94+cyc
    randy=Rnd(0,10)
    If (randy=0 Or shake#>1.0) And pAnim(v)=>231 And pAnim(v)=<232 Then pHP(v)=0
   EndIf
  Next
 EndIf
End Function

;---------------------------------------------------------------------
;///////////////////////// RING COMPONENTS ///////////////////////////
;---------------------------------------------------------------------
;GET RING COMPONENTS
Function GetRingComponents()
 ;identify entities
 ringComponent(1)=FindChild(world,"Canvas")
 ringComponent(2)=FindChild(world,"Apron")
 ;3-6: posts
 For count=1 To 4
  ringComponent(count+2)=FindChild(world,"Post"+Dig$(count,10))
 Next 
 ;7-10: buckle rings
 For count=1 To 4
  ringComponent(count+6)=FindChild(world,"Ring"+Dig$(count,10))
 Next 
 ;11-22: buckles
 For count=1 To 12
  ringComponent(count+10)=FindChild(world,"Buckle"+Dig$(count,10))
 Next 
 ;31-42: pads
 For count=1 To 12
  ringComponent(count+30)=FindChild(world,"Pad"+Dig$(count,10))
 Next 
 ;51-62: ropes
 ;71-74: cage walls
 ;reset properties
 For cyc=1 To 75
  If ringComponent(cyc)>0
   ringX#(cyc)=EntityX#(ringComponent(cyc),1)
   ringY#(cyc)=EntityY#(ringComponent(cyc),1)
   ringZ#(cyc)=EntityZ#(ringComponent(cyc),1)
  EndIf
  ringOffsetX#(cyc)=0 : ringOffsetY#(cyc)=0 : ringOffsetZ#(cyc)=0
  ringTX#(cyc)=0 : ringTY#(cyc)=0 : ringTZ#(cyc)=0
 Next
End Function

;MANAGE RING COMPONENTS
Function ManageRingComponents()
 For cyc=1 To 75
  ;shake X axis
  ringShakeTim(cyc)=ringShakeTim(cyc)-1
  If ringShakeTim(cyc)<0 Then ringShakeTim(cyc)=0 
  If ringShakeTim(cyc)=0 Then ringTX#(cyc)=0 : ringSpeedX#(cyc)=0.01
  If ringOffsetX#(cyc)>ringTX#(cyc) Then ringOffsetX#(cyc)=ringOffsetX#(cyc)-ringSpeedX#(cyc)
  If ringOffsetX#(cyc)<ringTX#(cyc) Then ringOffsetX#(cyc)=ringOffsetX#(cyc)+ringSpeedX#(cyc)
  If ringShakeTim(cyc)>0 And ringTX#(cyc)<0 And ringOffsetX#(cyc)=<ringTX#(cyc)
   ringTX#(cyc)=MakePositive#(ringTX#(cyc)/2)
   ringSpeedX#(cyc)=GetDiff#(ringOffsetX#(cyc),ringTX#(cyc))/10
  EndIf
  If ringShakeTim(cyc)>0 And ringTX#(cyc)>0 And ringOffsetX#(cyc)=>ringTX#(cyc)
   ringTX#(cyc)=-(ringTX#(cyc)/2)
   ringSpeedX#(cyc)=GetDiff#(ringOffsetX#(cyc),ringTX#(cyc))/10
  EndIf
  ;shake Y axis
  ringShakeTim(cyc)=ringShakeTim(cyc)-1
  If ringShakeTim(cyc)<0 Then ringShakeTim(cyc)=0 
  If ringShakeTim(cyc)=0 Then ringTY#(cyc)=0 : ringSpeedY#(cyc)=0.01
  If ringOffsetY#(cyc)>ringTY#(cyc) Then ringOffsetY#(cyc)=ringOffsetY#(cyc)-ringSpeedY#(cyc)
  If ringOffsetY#(cyc)<ringTY#(cyc) Then ringOffsetY#(cyc)=ringOffsetY#(cyc)+ringSpeedY#(cyc)
  If ringShakeTim(cyc)>0 And ringTY#(cyc)<0 And ringOffsetY#(cyc)=<ringTY#(cyc)
   ringTY#(cyc)=MakePositive#(ringTY#(cyc)/2)
   ringSpeedY#(cyc)=GetDiff#(ringOffsetY#(cyc),ringTY#(cyc))/10
  EndIf
  If ringShakeTim(cyc)>0 And ringTY#(cyc)>0 And ringOffsetY#(cyc)=>ringTY#(cyc)
   ringTY#(cyc)=-(ringTY#(cyc)/2)
   ringSpeedY#(cyc)=GetDiff#(ringOffsetY#(cyc),ringTY#(cyc))/10
  EndIf
  ;shake Z axis
  ringShakeTim(cyc)=ringShakeTim(cyc)-1
  If ringShakeTim(cyc)<0 Then ringShakeTim(cyc)=0 
  If ringShakeTim(cyc)=0 Then ringTZ#(cyc)=0 : ringSpeedZ#(cyc)=0.01
  If ringOffsetZ#(cyc)>ringTZ#(cyc) Then ringOffsetZ#(cyc)=ringOffsetZ#(cyc)-ringSpeedZ#(cyc)
  If ringOffsetZ#(cyc)<ringTZ#(cyc) Then ringOffsetZ#(cyc)=ringOffsetZ#(cyc)+ringSpeedZ#(cyc)
  If ringShakeTim(cyc)>0 And ringTZ#(cyc)<0 And ringOffsetZ#(cyc)=<ringTZ#(cyc)
   ringTZ#(cyc)=MakePositive#(ringTZ#(cyc)/2)
   ringSpeedZ#(cyc)=GetDiff#(ringOffsetZ#(cyc),ringTZ#(cyc))/10
  EndIf
  If ringShakeTim(cyc)>0 And ringTZ#(cyc)>0 And ringOffsetZ#(cyc)=>ringTZ#(cyc)
   ringTZ#(cyc)=-(ringTZ#(cyc)/2)
   ringSpeedZ#(cyc)=GetDiff#(ringOffsetZ#(cyc),ringTZ#(cyc))/10
  EndIf 
  ;bind components together
  ringOffsetX#(2)=ringOffsetX#(1) : ringOffsetY#(2)=ringOffsetY#(1) : ringOffsetZ#(2)=ringOffsetZ#(1)
  For count=1 To 4
   ringOffsetX#(count+6)=ringOffsetX#(count+30) : ringOffsetY#(count+6)=ringOffsetY#(count+30) : ringOffsetZ#(count+6)=ringOffsetZ#(count+30)
  Next
  ;update display
  If ringComponent(cyc)>0
   PositionEntity ringComponent(cyc),ringX#(cyc)+ringOffsetX#(cyc),ringY#(cyc)+ringOffsetY#(cyc),ringZ#(cyc)+ringOffsetZ#(cyc)
  EndIf
 Next
End Function

;SHAKE RING 
Function ShakeRing(area,shaker#) ;0=whole ring, 1-4=specific corner
 shaker#=shaker#/2
 For cyc=1 To 75
  shake=0
  If area=1
   If cyc=3 Or cyc=7 Or cyc=11 Or cyc=15 Or cyc=19 Or cyc=31 Or cyc=35 Or cyc=39 Then shake=1
  EndIf
  If area=2
   If cyc=4 Or cyc=8 Or cyc=12 Or cyc=16 Or cyc=20 Or cyc=32 Or cyc=36 Or cyc=40 Then shake=1
  EndIf
  If area=3
   If cyc=5 Or cyc=9 Or cyc=13 Or cyc=17 Or cyc=21 Or cyc=33 Or cyc=37 Or cyc=41 Then shake=1
  EndIf
  If area=4
   If cyc=6 Or cyc=10 Or cyc=14 Or cyc=18 Or cyc=22 Or cyc=34 Or cyc=38 Or cyc=42 Then shake=1
  EndIf
  If area=0 Or shake=1
   ringTX#(cyc)=Rnd#(-(shaker#/2),shaker#/2) : ringSpeedX#(cyc)=GetDiff#(ringOffsetX#(cyc),ringTX#(cyc))/6
   ringTY#(cyc)=Rnd#(-shaker#,shaker#) : ringSpeedY#(cyc)=GetDiff#(ringOffsetY#(cyc),ringTY#(cyc))/6
   ringTZ#(cyc)=Rnd#(-(shaker#/2),shaker#/2) : ringSpeedZ#(cyc)=GetDiff#(ringOffsetZ#(cyc),ringTZ#(cyc))/6
   ringShakeTim(cyc)=100
  EndIf
 Next
End Function

;---------------------------------------------------------------------
;//////////////////// BUCKLE MOVE SEQUENCES //////////////////////////
;---------------------------------------------------------------------
Function LoadBuckleSequences(cyc)
 ;buckle issues
 pSeq(cyc,700)=ExtractAnimSeq(p(cyc),1535,1585,pSeq(cyc,1008)) ;turn into buckle
 pSeq(cyc,701)=ExtractAnimSeq(p(cyc),1470,1510,pSeq(cyc,1008)) ;buckle slump
 pSeq(cyc,702)=ExtractAnimSeq(p(cyc),1595,1675,pSeq(cyc,1008)) ;unfasten buckle
 pSeq(cyc,703)=ExtractAnimSeq(p(cyc),2225,2275,pSeq(cyc,1009)) ;tag slap
 ;drag down
 pSeq(cyc,710)=ExtractAnimSeq(p(cyc),680,765,pSeq(cyc,1060)) ;[execute]
 pSeq(cyc,711)=ExtractAnimSeq(p(cyc),680,765,pSeq(cyc,1061)) ;[receive]
 ;superplex
 If BuckleMoveRequired(cyc,711,1) Then pSeq(cyc,712)=ExtractAnimSeq(p(cyc),70,370,pSeq(cyc,1060)) ;[execute]
 If BuckleMoveRequired(cyc,711,-1) Then pSeq(cyc,713)=ExtractAnimSeq(p(cyc),70,370,pSeq(cyc,1061)) ;[receive]
 ;mounted punches
 If BuckleMoveRequired(cyc,712,1) Then pSeq(cyc,714)=ExtractAnimSeq(p(cyc),380,670,pSeq(cyc,1060)) ;[execute]
 If BuckleMoveRequired(cyc,712,-1) Then pSeq(cyc,715)=ExtractAnimSeq(p(cyc),380,670,pSeq(cyc,1061)) ;[receive]
 ;head smashes
 If BuckleMoveRequired(cyc,713,1) Then pSeq(cyc,716)=ExtractAnimSeq(p(cyc),775,985,pSeq(cyc,1060)) ;[execute]
 If BuckleMoveRequired(cyc,713,-1) Then pSeq(cyc,717)=ExtractAnimSeq(p(cyc),775,985,pSeq(cyc,1061)) ;[receive]
 ;shoulder barges
 If BuckleMoveRequired(cyc,714,1) Then pSeq(cyc,718)=ExtractAnimSeq(p(cyc),995,1160,pSeq(cyc,1060)) ;[execute]
 If BuckleMoveRequired(cyc,714,-1) Then pSeq(cyc,719)=ExtractAnimSeq(p(cyc),995,1160,pSeq(cyc,1061)) ;[receive]
 ;hurricanranna
 If BuckleMoveRequired(cyc,715,1) Then pSeq(cyc,720)=ExtractAnimSeq(p(cyc),1170,1425,pSeq(cyc,1060)) ;[execute]
 If BuckleMoveRequired(cyc,715,-1) Then pSeq(cyc,721)=ExtractAnimSeq(p(cyc),1170,1425,pSeq(cyc,1061)) ;[receive]
End Function

;FIND BUCKLE MOVE REQUIREMENT
Function BuckleMoveRequired(cyc,move,task) ;-1=receive, 1=execute
 needed=0
 For v=1 To no_plays
  If moveAnim(2,charMove(pChar(v),14))=move
   If task=1 And cyc=v Then needed=1 ;executor 
   If task=-1 And cyc<>v Then needed=1 ;receiver
  EndIf 
 Next
 Return needed
End Function

;---------------------------------------------------------------------
;/////////////////// BUCKLE MOVE ANIMATIONS //////////////////////////
;---------------------------------------------------------------------
Function BuckleMoves(cyc)
 ;turn into buckle
 If pAnim(cyc)=700
  pExcusedPlays(cyc)=1
  If pAnimTim(cyc)=0 Then pAnimSpeed#(cyc)=2.0 : Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,700),15/pAnimSpeed#(cyc)
  If pAnimTim(cyc)=5 Then ProduceSound(p(cyc),sSwing,21000,Rnd(0.25,0.5))
  BucklePositioning(cyc,0)
  pStepTim#(cyc)=pStepTim#(cyc)+pAnimSpeed#(cyc)
  If pAnimTim(cyc)=<Int(40/pAnimSpeed#(cyc)) Then FindSmashes(cyc,pLimbY#(cyc,36),2)
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc))  
   If NearestCorner(pX#(cyc),pZ#(cyc))=1 Then ShakeRopes(-100,50,12,0) : ShakeRopes(-50,100,12,0)
   If NearestCorner(pX#(cyc),pZ#(cyc))=2 Then ShakeRopes(100,50,12,0) : ShakeRopes(50,100,12,0)
   If NearestCorner(pX#(cyc),pZ#(cyc))=3 Then ShakeRopes(100,50,12,0) : ShakeRopes(50,-100,12,0)
   If NearestCorner(pX#(cyc),pZ#(cyc))=4 Then ShakeRopes(-100,-50,12,0) : ShakeRopes(-50,-100,12,0)
   Pop(0,Rnd(2,8),0)
   ShakeRing(0,1.0)
   ProduceSound(p(cyc),sThud,22050,0)
   ProduceSound(p(cyc),sBlock(7),22050,0)
   ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0)
   ImpactSpurt(pLimbX#(cyc,36),45,pLimbZ#(cyc,36),1,1)
   ScarArea(cyc,pLimbX#(cyc,36),45,pLimbZ#(cyc,36),10)
   RiskInjury(cyc,3,10)
   pHealth(cyc)=pHealth(cyc)-100 
   If EntertainViable(cyc,0) Then entScore=entScore+100
   If padExposed(NearestCorner(pX#(cyc),pZ#(cyc)))=1
    Pop(0,Rnd(2,8),1)
    ProduceSound(p(cyc),sImpact(7),22050,0)
    ProduceSound(p(cyc),sImpactBlade,22050,0)
    ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),1)
    limb=FindChild(world,"Ring0"+NearestCorner(pX#(cyc),pZ#(cyc)))
    ImpactSpurt(EntityX(limb,1),45,EntityZ(limb,1),1,1)
    BloodSpurt(EntityX(limb,1),45,EntityZ(limb,1),pA#(cyc),pScar(cyc,5)-1,1)
    ScarArea(cyc,EntityX(limb,1),45,EntityZ(limb,1),5)
    If pScar(cyc,5)>1 Then EntityTexture limb,tBodyScar(Rnd(1,4)),0,2
    RiskInjury(cyc,3,1)
    pHealth(cyc)=pHealth(cyc)-100
    If EntertainViable(cyc,0) Then entScore=entScore+100 : entHardcore=entHardcore+50
   EndIf
   randy=Rnd(0,10)
   If randy=<1 And pMomentum(cyc)>0 Then pHP(cyc)=0
   For v=1 To no_plays
    If InProximity(cyc,v,30) And pPlatform(v)=>5 And pPlatform(v)=<8 And AttackViable(v)>0 Then pHP(v)=0 : pHurtA#(v)=pA#(cyc)
   Next
  EndIf
  If pAnimTim(cyc)>Int(65/pAnimSpeed#(cyc)) Or (pAnimTim(cyc)>Int(40/pAnimSpeed#(cyc)) And pHP(cyc)=0) 
   ChangeAnim(cyc,701) : SharpTransition(cyc,701,0,180)
   pExcusedPlays(cyc)=0 : pMomentum(cyc)=0
   pDT(cyc)=GetDT(cyc,250) : pHurtA#(cyc)=pA#(cyc)
   randy=Rnd(0,1)
   If randy=0 And pHP(cyc)=0 Then pA#(cyc)=pA#(cyc)+180
  EndIf
  DropWeapon(cyc,100)
  pBuckleTim(cyc)=20
  RiskExertion(cyc,1)
 EndIf
 ;buckle slump
 If pAnim(cyc)=701
  If pAnimTim(cyc)=0 Then Animate p(cyc),1,Rnd#(0.2,0.4),pSeq(cyc,701),0
  If pAnimTim(cyc)>10 And pDT(cyc)=<0 
   If DirPressed(cyc) Or ActionPressed(cyc) Then ChangeAnim(cyc,702)
  EndIf
  pBuckleTim(cyc)=20
 EndIf
 ;leave buckle
 If pAnim(cyc)=702
  If pAnimTim(cyc)=0 Then Animate p(cyc),1,1.0,pSeq(cyc,2),20
  If pAnimTim(cyc)=>10 Then ChangeAnim(cyc,0)
 EndIf
 ;unfasten pad
 If pAnim(cyc)=703
  If pAnimTim(cyc)=0 Then pAnimSpeed#(cyc)=2.0 : Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,702),5
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
  If pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc))
   v=NearestCorner(pX#(cyc),pZ#(cyc))
   If padExposed(v)=0
    Pop(cyc,Rnd(2,8),1)
    ProduceSound(p(cyc),sBlock(6),20000,0.25)
    ProduceSound(p(cyc),sImpactBlade,20000,0.25)
    limb=FindChild(world,"Ring0"+v)
    CreateSpurt(EntityX(limb,1),45,EntityZ(limb,1),-1,1,10,4)
    CreateSpurt(EntityX(limb,1),45,EntityZ(limb,1),-1,1,10,5)
    HideEntity FindChild(world,"Pad0"+v)
    If cyc=matchPlayer Then matchDamage=matchDamage+50
    entScore=entScore+100
    padExposed(v)=1
   EndIf
  EndIf
  If pAnimTim(cyc)=>Int(80/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,0)
 EndIf
 ;tag execute / receive
 If pAnim(cyc)=704 Or pAnim(cyc)=705
  If pAnimTim(cyc)=0 
   pAnimSpeed#(cyc)=2.5
   Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,703),15/pAnimSpeed#(cyc)
   pSting(cyc)=1
  EndIf
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,22050,0.5)
  If pAnim(cyc)=704 And pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc))
   For v=1 To no_plays
    If cyc<>v And InProximity(cyc,v,20) And pAnim(v)=705 And pSting(cyc)=1
     Pop(v,Rnd(2,7),0)
     ProduceSound(p(cyc),sImpact(Rnd(1,4)),22050,0)
     If GetPercent#(pHealth(v),optLength*5000)=>GetPercent#(pHealth(cyc),optLength*5000)+10
      Pop(v,Rnd(2,7),1)
      entScore=entScore+(charPopularity(pChar(v))*4)
     Else
      entScore=entScore+(charPopularity(pChar(v))*2)
     EndIf 
     If (pTeam(v)=1 And charRelationship(pChar(v),pChar(teamLegal(2)))<0) Or (pTeam(v)=2 And charRelationship(pChar(v),pChar(teamLegal(1)))<0)
      Pop(v,Rnd(2,7),1)
      entScore=entScore+(charPopularity(pChar(v))*2)
     EndIf
     teamLegal(pTeam(cyc))=v
     If pTeam(v)=1 Then pFoc(v)=teamLegal(2)
     If pTeam(v)=2 Then pFoc(v)=teamLegal(1)
     pChaosTim(cyc)=200 : pChaosTim(v)=200
     pAgenda(cyc)=0 : pAgenda(v)=1
     If optTagControl=1 Then SwapControls(cyc,v,0)
     If pControl(cyc)=0 Then GetNewFoc(cyc)
     pSting(cyc)=0
    EndIf
   Next
  EndIf
  If pAnimTim(cyc)>Int(60/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,0)
 EndIf
 ;//////////////////////////////// BUCKLE MOVES /////////////////////////////////
 ;DRAG DOWN
 If pAnim(cyc)=710
  v=pGrappling(cyc)
  pExcusedWorld(cyc)=1 : pExcusedWorld(v)=1
  pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=710 : animB=711 : pAnimSpeed#(cyc)=1.8
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.5)
  If pAnimTim(cyc)=>Int(40/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(70/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(55/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,1,3,0)
  If pAnimTim(cyc)>Int(85/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,0)
   ChangeAnim(v,152) : SharpTransition(v,152,1,0)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;SUPERPLEX
 If pAnim(cyc)=711
  v=pGrappling(cyc)
  pExcusedWorld(cyc)=1 : pExcusedWorld(v)=1
  pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=712 : animB=713 : pAnimSpeed#(cyc)=1.9
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(75/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(170/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(210/pAnimSpeed#(cyc))
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
  EndIf
  If pAnimTim(cyc)=Int(35/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(50/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(95/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(115/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(135/pAnimSpeed#(cyc))
   pStepTim#(cyc)=99
  EndIf
  If pAnimTim(cyc)=Int(75/pAnimSpeed#(cyc)) Then Pop(cyc,Rnd(2,9),0)
  If pAnimTim(cyc)=Int(170/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),0.5)
  If pAnimTim(cyc)=Int(210/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0)
  If pAnimTim(cyc)=>Int(220/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(260/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(240/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,5,10,2) : pDT(cyc)=GetDT(cyc,100)
  If pAnimTim(cyc)>Int(300/pAnimSpeed#(cyc))
   ChangeAnim(cyc,150) : SharpTransition(cyc,150,1,180)
   ChangeAnim(v,150) : SharpTransition(v,150,1,0)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;MOUNTED PUNCHES
 If pAnim(cyc)=712
  v=pGrappling(cyc)
  pExcusedWorld(cyc)=1 : pExcusedWorld(v)=1
  pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=714 : animB=715 : pAnimSpeed#(cyc)=1.8
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(7/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(22/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(37/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(52/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(245/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(267/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(282/pAnimSpeed#(cyc))
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
  EndIf
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(260/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(275/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(285/pAnimSpeed#(cyc))
   pStepTim#(cyc)=99
  EndIf
  If pAnimTim(cyc)=Int(60/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(105/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(150/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(205/pAnimSpeed#(cyc))
   ProduceSound(p(cyc),sSwing,21000,0.5)
  EndIf
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(115/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(160/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(215/pAnimSpeed#(cyc))
   Pop(cyc,Rnd(2,8),0)
   If pHolding(cyc)=0 Or weapWear(pHolding(cyc))>0 Then ProduceSound(p(cyc),sImpact(Rnd(1,4)),22050,0)
   If pHolding(cyc)>0 And weapWear(pHolding(cyc))=0 Then ProduceSound(p(cyc),sImpact(Rnd(1,4)),22050,0.5)
   ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),0)
   ImpactSpurt(pLimbX#(v,1),pLimbY#(v,1),pLimbZ#(v,1),1,1)
   BloodSpurt(pLimbX#(v,1),pLimbY#(v,1),pLimbZ#(v,1),pA#(v)+180,pScar(v,1)-1,1)
   ScarLimb(v,1,50)
   If pHolding(cyc)>0 And weapWear(pHolding(cyc))=0 
    ScarLimb(v,1,25) : BloodSpurt(pLimbX#(v,1),pLimbY#(v,1),pLimbZ#(v,1),pA#(v)+180,pScar(v,1)-1,1)
    If weapBurning(pHolding(cyc))>0 Then CreateParticle(pLimbX#(v,1),pLimbY#(v,1),pLimbZ#(v,1),-1,2) : CreateParticle(pLimbX#(v,1),pLimbY#(v,1),pLimbZ#(v,1),-1,13)
   EndIf
   pHealth(v)=pHealth(v)-PercentOf#(pStrength(cyc),125)
   If EntertainViable(cyc,v) Then entScore=entScore+PercentOf#(pStrength(cyc),125)
   pHeat(cyc)=pHeat(cyc)+1 : pHeat(v)=pHeat(v)-1
   WeaponImpact(cyc,v,1)
   ShakeRing(NearestCorner(pX#(cyc),pZ#(cyc)),0.1) 
  EndIf
  If pAnimTim(cyc)=>Int(255/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(275/pAnimSpeed#(cyc)) Then FindSmashes(cyc,pLimbY#(cyc,36),2)
  If pAnimTim(cyc)>Int(285/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,710)
 EndIf
 ;HEAD SMASHES
 If pAnim(cyc)=713
  v=pGrappling(cyc)
  pExcusedWorld(cyc)=1 : pExcusedWorld(v)=1
  pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=716 : animB=717 : pAnimSpeed#(cyc)=2.0
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
  If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(25/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(68/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(113/pAnimSpeed#(cyc)) 
   ProduceSound(p(cyc),sSwing,21000,0.5)
  EndIf
  If pAnimTim(cyc)=Int(43/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(88/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(133/pAnimSpeed#(cyc))
   limb=FindChild(world,"Ring0"+NearestCorner(pX#(cyc),pZ#(cyc)))
   Pop(cyc,Rnd(2,8),0)
   If padExposed(NearestCorner(pX#(cyc),pZ#(cyc)))=0 Then ProduceSound(p(cyc),sBlock(Rnd(5,7)),22050,0)
   ProduceSound(p(cyc),sThud,22050,0)
   ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),0)
   If padExposed(NearestCorner(pX#(cyc),pZ#(cyc)))=1 Then impactY#=45 Else impactY#=50
   ImpactSpurt(EntityX(limb,1),impactY#,EntityZ(limb,1),1,1)
   BloodSpurt(EntityX(limb,1),impactY#,EntityZ(limb,1),pA#(v)+180,pScar(v,1)-1,1)
   ScarLimb(v,1,50)
   If pHolding(cyc)>0 And weapWear(pHolding(cyc))=0 Then ScarLimb(v,1,25) : BloodSpurt(EntityX(limb,1),impactY#,EntityZ(limb,1),pA#(v)+180,pScar(v,1)-1,1)
   pHealth(v)=pHealth(v)-PercentOf#(pStrength(cyc),150)
   If EntertainViable(cyc,v) Then entScore=entScore+PercentOf#(pStrength(cyc),150)
   pHeat(cyc)=pHeat(cyc)+1 : pHeat(v)=pHeat(v)-1
   WeaponImpact(cyc,v,1)
   If padExposed(NearestCorner(pX#(cyc),pZ#(cyc)))=1
    Pop(0,Rnd(2,8),1)
    ProduceSound(p(v),sImpact(7),22050,0)
    ProduceSound(p(v),sImpactBlade,22050,0)
    ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),1)
    BloodSpurt(EntityX(limb,1),45,EntityZ(limb,1),pA#(v)+180,pScar(v,1)-1,1)
    ScarLimb(v,1,25) : pHeat(v)=pHeat(v)-1
    If pScar(cyc,5)>1 Then EntityTexture limb,tBodyScar(Rnd(1,4)),0,2
    pHealth(cyc)=pHealth(cyc)-(pStrength(cyc)/2)
    If EntertainViable(cyc,v) Then entScore=entScore+(pStrength(cyc)/2) : entHardcore=entHardcore+50
   EndIf
   ShakeRing(NearestCorner(pX#(cyc),pZ#(cyc)),0.5)
  EndIf
  If pAnimTim(cyc)=>Int(160/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(195/pAnimSpeed#(cyc)) Then FindSmashes(v,pLimbY#(v,36),1) 
  If pAnimTim(cyc)=Int(185/pAnimSpeed#(cyc))
   ProduceSound(pLimb(v,36),sPain(Rnd(1,8)),GetVoice(v),0.5)
   ProduceSound(pLimb(v,36),sFall,22050,0)
   ProduceSound(pLimb(v,36),sThud,22050,0.5)
   ScarArea(v,0,0,0,50)
   ShakeRing(0,0.5)
  EndIf
  If pAnimTim(cyc)>Int(210/pAnimSpeed#(cyc))
   ChangeAnim(cyc,0) : SharpTransition(cyc,0,1,45)
   ChangeAnim(v,150) : SharpTransition(v,150,1,180)
   EndMove(cyc,v)
  EndIf
 EndIf
 ;SHOULDER BARGES
 If pAnim(cyc)=714
  v=pGrappling(cyc)
  pExcusedWorld(cyc)=1 : pExcusedWorld(v)=1
  pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=718 : animB=719 : pAnimSpeed#(cyc)=1.6
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(10/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(145/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
  If pAnimTim(cyc)=Int(20/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(165/pAnimSpeed#(cyc)) Then pStepTim#(cyc)=99
  If pAnimTim(cyc)=Int(40/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(75/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(110/pAnimSpeed#(cyc)) 
   ProduceSound(p(cyc),sSwing,21000,0)
  EndIf
  If pAnimTim(cyc)=Int(52/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(87/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(122/pAnimSpeed#(cyc))  
   Pop(cyc,Rnd(2,8),0)
   ProduceSound(p(cyc),sThud,22050,0)
   ProduceSound(p(cyc),sFall,22050,0.5)
   ProduceSound(p(cyc),sBlock(Rnd(5,7)),22050,1)
   ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),1)
   ImpactSpurt(pLimbX#(v,36),pLimbY#(v,36),pLimbZ#(v,36),1,1)
   BloodSpurt(pLimbX#(v,36),pLimbY#(v,36),pLimbZ#(v,36),pA#(v)+180,pScar(v,1)-1,1)
   ScarArea(v,pLimbX#(v,36),pLimbY#(v,36),pLimbZ#(v,36),50)
   If pHolding(cyc)>0 And weapWear(pHolding(cyc))=0
    ScarArea(v,pLimbX#(v,36),pLimbY#(v,36),pLimbZ#(v,36),25)
    BloodSpurt(pLimbX#(v,36),pLimbY#(v,36),pLimbZ#(v,36),pA#(v)+180,pScar(v,1)-1,1)
   EndIf
   pHealth(v)=pHealth(v)-PercentOf#(pStrength(cyc),150)
   If EntertainViable(cyc,v) Then entScore=entScore+PercentOf#(pStrength(cyc),150)
   pHeat(cyc)=pHeat(cyc)+1 : pHeat(v)=pHeat(v)-1
   WeaponImpact(cyc,v,1)
   If padExposed(NearestCorner(pX#(cyc),pZ#(cyc)))=1
    Pop(0,Rnd(2,8),1)
    ProduceSound(p(v),sImpact(7),22050,0)
    ProduceSound(p(v),sImpactBlade,22050,0)
    ProduceSound(p(v),sPain(Rnd(1,8)),GetVoice(v),1)
    limb=FindChild(world,"Ring0"+NearestCorner(pX#(cyc),pZ#(cyc)))
    ImpactSpurt(EntityX(limb,1),45,EntityZ(limb,1),1,1)
    BloodSpurt(EntityX(limb,1),45,EntityZ(limb,1),pA#(v)+180,pScar(v,1)-1,1)
    ScarArea(v,EntityX(limb,1),45,EntityZ(limb,1),25)
    If pScar(cyc,5)>1 Then EntityTexture limb,tBodyScar(Rnd(1,4)),0,2
    pHealth(cyc)=pHealth(cyc)-(pStrength(cyc)/2)
    If EntertainViable(cyc,v) Then entScore=entScore+(pStrength(cyc)/2) : entHardcore=entHardcore+50
    pHeat(v)=pHeat(v)-1
   EndIf
   ShakeRing(NearestCorner(pX#(cyc),pZ#(cyc)),1.0)
  EndIf
  If pAnimTim(cyc)>Int(165/pAnimSpeed#(cyc)) Then ChangeAnim(cyc,710)
 EndIf 
 ;HURRICANRANNA
 If pAnim(cyc)=715
  v=pGrappling(cyc)
  pExcusedWorld(cyc)=1 : pExcusedWorld(v)=1
  pExcusedItems(cyc)=1 : pExcusedItems(v)=1
  animA=720 : animB=721 : pAnimSpeed#(cyc)=1.75
  If pAnimTim(cyc)=0 Then Animate p(cyc),3,pAnimSpeed#(cyc),pSeq(cyc,animA),0 : Animate p(v),3,pAnimSpeed#(cyc),pSeq(v,animB),0
  If pAnimTim(cyc)=Int(15/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(65/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(115/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(170/pAnimSpeed#(cyc))
   ProduceSound(p(cyc),sShuffle(Rnd(1,3)),0,0)
  EndIf
  If pAnimTim(cyc)=Int(30/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(45/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(85/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(100/pAnimSpeed#(cyc)) Or pAnimTim(cyc)=Int(120/pAnimSpeed#(cyc))
   pStepTim#(cyc)=99
  EndIf
  If pAnimTim(cyc)=Int(70/pAnimSpeed#(cyc)) Then Pop(cyc,Rnd(2,9),0)
  If pAnimTim(cyc)=Int(130/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0.5)
  If pAnimTim(cyc)=Int(170/pAnimSpeed#(cyc)) Then ProduceSound(p(cyc),sSwing,21000,0)
  If pAnimTim(cyc)=>Int(200/pAnimSpeed#(cyc)) And pAnimTim(cyc)=<Int(235/pAnimSpeed#(cyc)) Then FindSmashes(v,LowestValue#(pLimbY#(v,1),pLimbY#(v,36)),0)
  If pAnimTim(cyc)=Int(215/pAnimSpeed#(cyc)) Then MoveImpact(cyc,v,5,10,0) : pDT(cyc)=GetDT(cyc,100)
  If pAnimTim(cyc)>Int(255/pAnimSpeed#(cyc))
   ChangeAnim(cyc,154) : SharpTransition(cyc,154,1,180)
   ChangeAnim(v,150) : SharpTransition(v,150,1,0)
   EndMove(cyc,v)
  EndIf
 EndIf
End Function

;--------------------------------------------------------------
;////////////////// RELATED FUNCTIONS /////////////////////////
;--------------------------------------------------------------

;TOUCHING ROPES?
Function TouchingRopes(cyc)
 value=0
 If InsideRing(pX#(cyc),pZ#(cyc),-15)
  For limb=1 To 50
   If pLimb(cyc,limb)>0 And MajorLimb(limb)
    If pLimbZ#(cyc,limb)>blockZ1#(1)+3 Or pLimbX#(cyc,limb)>blockX1#(2)+3 Or pLimbZ#(cyc,limb)<blockZ2#(3)-3 Or pLimbX#(cyc,limb)<blockX2#(4)-3 Then value=1
   EndIf
  Next
  If RealZ#(cyc)>blockZ1#(1)-1 Or RealX#(cyc)>blockX1#(2)-1 Or RealZ#(cyc)<blockZ2#(3)+1 Or RealX#(cyc)<blockX2#(4)+1 Then value=1
 EndIf
 Return value
End Function

;FIND NEAREST ROPE SIDE
Function FindRopes(x#,z#)
 nearest=0 : hi#=9999
 For count=1 To 4
  distance#=GetDistance#(x#,z#,ropeX#(count),ropeZ#(count))
  If distance#<hi# Then hi#=distance# : nearest=count
 Next
 value=nearest
 Return value
End Function

;FIND ROPE SEGMENT
Function RopeSegment(side,x#,z#)
 value=2
 ;north matches
 If side=1
  If x#=<-25 Then value=1
  If x#=>25 Then value=3
 EndIf
 ;east matches
 If side=2
  If z#=<-25 Then value=3
  If z#=>25 Then value=1
 EndIf
 ;south matches
 If side=3
  If x#=<-25 Then value=3
  If x#=>25 Then value=1
 EndIf
 ;west matches
 If side=4
  If z#=<-25 Then value=1
  If z#=>25 Then value=3
 EndIf
 Return value
End Function

;SHAKE ROPES
Function ShakeRopes(x#,z#,anim,override)
 side=FindRopes(x#,z#)
 ;whole shakes  
 If anim<20
  For count=1 To 12
   If ropeAnim(count)=0 Or override=1
    If ropeSide(count)=side Then ropeAnim(count)=anim : ropeAnimTim(count)=0
   EndIf
  Next
 EndIf 
 ;shake segments
 If anim=>20 And anim=<23
  segment=RopeSegment(side,x#,z#)
  For count=1 To 12
   If ropeAnim(count)<20 Or override=1
    If ropeSide(count)=side Then ropeAnim(count)=20+segment : ropeAnimTim(count)=0
   EndIf
  Next
 EndIf
 ;whip segments
 If anim=>30 And anim=<33
  segment=RopeSegment(side,x#,z#)
  For count=1 To 12
   If ropeAnim(count)<30 Or override=1
    If ropeSide(count)=side Then ropeAnim(count)=30+segment : ropeAnimTim(count)=0
   EndIf
  Next
 EndIf
End Function

;FIND ROPE BOUNCES
Function RopeBounce(cyc)
 If InsideRing(pX#(cyc),pZ#(cyc),-15) And pPlatform(cyc)=0 And ItemClear(cyc,pA#(cyc),5)
  ;position checker
  ProjectDummy(cyc,0,0,5)
  checkX#=EntityX(dummy) : checkZ#=EntityZ(dummy)
  ;find rope contact
  If checkX#>blockX1#(0)+35 And checkX#<blockX2#(0)-35
   If checkZ#>blockZ1#(1) Then pA#(cyc)=0 : ChangeAnim(cyc,44) ;north side
   If checkZ#<blockZ2#(3) Then pA#(cyc)=180 : ChangeAnim(cyc,44) ;south side
  EndIf
  If checkZ#>blockZ1#(0)+35 And checkZ#<blockZ2#(0)-35
   If checkX#>blockX1#(2) Then pA#(cyc)=270 : ChangeAnim(cyc,44) ;east side
   If checkX#<blockX2#(4) Then pA#(cyc)=90 : ChangeAnim(cyc,44) ;west side
  EndIf
  ;find turnbuckle contact
  If pAnim(cyc)<>44 And pMomentum(cyc)>0
   If FindBuckleSlumps(cyc,checkX#,checkZ#,8)>0 Then ChangeAnim(cyc,700)
  EndIf
 EndIf
End Function

;NOVELTY ROPE DAMAGE
Function RopeBurn(cyc)
 nearest=FindRopes(RealX#(cyc),RealZ#(cyc))
 For v=1 To 12
  If ropeA#(v)=ropeA#(nearest)
   ;If ropeA#(v)=0 Or ropeA#(v)=180 Then impactX#=RealX#(cyc) : impactZ#=ropeZ#(v)
   ;If ropeA#(v)=90 Or ropeA#(v)=270 Then impactX#=ropeX#(v) : impactZ#=RealZ#(cyc)
   impactX#=RealX#(cyc) : impactZ#=RealZ#(cyc) 
   If ropeBurning(v)>0 Or matchRopes>0 
    If v=<4 
     Pop(0,Rnd(2,8),1)
     ProduceSound(p(cyc),sPain(Rnd(1,8)),GetVoice(cyc),1)
     If matchRopes>0 Then ProduceSound(p(cyc),sImpactBlade,22050,0.25)
     If matchRopes=2 Then ProduceSound(p(cyc),sElectric,22050,0)
     If ropeBurning(v)>0 Or matchRopes=>2 Then ProduceSound(p(cyc),sIgnite,22050,0)
     randy=Rnd(0,16)
     If randy=<1 And matchRopes=2 Then CreateExplosion(p(cyc),impactX#,35,impactZ#,1)
     pHealth(cyc)=pHealth(cyc)-100 : pHP(cyc)=pHP(cyc)-50
     If AttackViable(cyc)<3 Then pDT(cyc)=pDT(cyc)+50
     If EntertainViable(cyc,0) Then entScore=entScore+50 : entHardcore=entHardcore+50
    EndIf
    includeRope=1
    If (pAnim(cyc)=140 Or pAnim(cyc)=141 Or pAnim(cyc)=145 Or pAnim(cyc)=146) And v>4 Then includeRope=0
    If includeRope=1
     ScarArea(cyc,impactX#,ropeY#(v),impactZ#,5)
     CreateSpurt(impactX#,ropeY#(v)-1,impactZ#,pA#(cyc),0,3,5)
     CreateSpurt(impactX#,ropeY#(v)-1,impactZ#,pA#(cyc),0,3,6)
     randy=Rnd(0,1)
     If randy=0 Then CreateParticle(impactX#,ropeY#(v)-1,impactZ#,pA#(cyc),3)
     randy=Rnd(0,1)
     If randy=0 Then CreateParticle(impactX#,ropeY#(v)-1,impactZ#,pA#(cyc),4)
     randy=Rnd(0,1)
     If randy=0 Then CreateParticle(impactX#,ropeY#(v)-1,impactZ#,pA#(cyc),14) 
     randy=Rnd(0,1)
     If randy=0 And matchRopes=2 Then CreateParticle(impactX#,ropeY#(v)-1,impactZ#,pA#(cyc),10)
     If ropeBurning(v)>0 Or matchRopes=>2
      randy=Rnd(0,1)
      If randy=0 Then CreateParticle(impactX#,ropeY#(v)-1,impactZ#,-1,2)
      randy=Rnd(0,1)
      If randy=0 Then CreateParticle(impactX#,ropeY#(v)-1,impactZ#,-1,13)
     EndIf
    EndIf 
   EndIf
  EndIf
 Next
End Function

;EMIT ROPE SOUND
Function RopeSound(entity,vol#)
 ;standard sound
 ProduceSound(entity,sRope,44100,vol#)
 ;add novelties
 If vol#=1 Then vol#=0 Else vol#=0.3
 If matchRopes>0 Then ProduceSound(entity,sSmashWire,22050,vol#)
 If matchRopes=2 Then ProduceSound(entity,sElectric,22050,vol#)
 If matchRopes=3 Then ProduceSound(entity,sExpire,22050,vol#)
End Function

;FIND BUCKLE SLUMPS
Function FindBuckleSlumps(cyc,x#,z#,range#)
 value=0
 If z#>blockZ1#(1)-range# And x#<blockX2#(4)+range# And BuckleClear(1) Then value=1 ;north west
 If z#>blockZ1#(1)-range# And x#>blockX1#(2)-range# And BuckleClear(2) Then value=2 ;north east
 If z#<blockZ2#(3)+range# And x#>blockX1#(2)-range# And BuckleClear(3) Then value=3 ;south east
 If z#<blockZ2#(3)+range# And x#<blockX2#(4)+range# And BuckleClear(4) Then value=4 ;south west 
 Return value
End Function

;TURNBUCKLE CLEAR?
Function BuckleClear(buckle)
 clear=1
 For v=1 To no_plays
  If pAnim(v)=>700 And pAnim(v)=<703
   If NearestCorner(pX#(v),pZ#(v))=buckle Then clear=0
  EndIf
 Next
 Return clear
End Function

;NEAREST RING SIDE
Function NearestSide(x#,z#)
 nearest=0 : distance#=9999
 If GetDistance#(x#,z#,0,140)<distance# Then nearest=1 : distance#=GetDistance#(x#,z#,0,140) ;north side
 If GetDistance#(x#,z#,140,0)<distance# Then nearest=2 : distance#=GetDistance#(x#,z#,140,0) ;east side
 If GetDistance#(x#,z#,0,-140)<distance# Then nearest=3 : distance#=GetDistance#(x#,z#,0,-140) ;south side
 If GetDistance#(x#,z#,-140,0)<distance# Then nearest=4 : distance#=GetDistance#(x#,z#,-140,0) ;west side
 Return nearest
End Function 

;FIND NEAREST RING CORNER
Function NearestCorner(x#,z#)
 If z#=>0
  If x#=<0 Then value=1 Else value=2
 Else
  If x#=<0 Then value=4 Else value=3
 EndIf
 Return value
End Function

;BUCKLE POSITIONING
Function BucklePositioning(cyc,angle#)
 ;get targets
 If NearestCorner(pX#(cyc),pZ#(cyc))=1 Then tX#=blockX2#(4) : tZ#=blockZ1#(1) : pTA#(cyc)=45+angle# ;north west
 If NearestCorner(pX#(cyc),pZ#(cyc))=2 Then tX#=blockX1#(2) : tZ#=blockZ1#(1) : pTA#(cyc)=315+angle# ;north east
 If NearestCorner(pX#(cyc),pZ#(cyc))=3 Then tX#=blockX1#(2) : tZ#=blockZ2#(3) : pTA#(cyc)=225+angle# ;south east
 If NearestCorner(pX#(cyc),pZ#(cyc))=4 Then tX#=blockX2#(4) : tZ#=blockZ2#(3) : pTA#(cyc)=135+angle# ;south west
 ;pursue targets
 If pX#(cyc)<tX# Then pX#(cyc)=pX#(cyc)+1.0
 If pX#(cyc)>tX# Then pX#(cyc)=pX#(cyc)-1.0
 If pZ#(cyc)<tZ# Then pZ#(cyc)=pZ#(cyc)+1.0
 If pZ#(cyc)>tZ# Then pZ#(cyc)=pZ#(cyc)-1.0 
 pA#(cyc)=pA#(cyc)+ReachAngle#(pA#(cyc),pTA#(cyc),1.0)
End Function