;//////////////////////////////////////////////////////////////////////////////
;------------------------- WRESTLING MPIRE 2008: WORLD ------------------------
;//////////////////////////////////////////////////////////////////////////////

;--------------------------------------------------------------------------
;/////////////////////////// PREVIEW ARENA ////////////////////////////////
;--------------------------------------------------------------------------
Function PreviewArena()
;load scene
Loader("Please Wait","Generating Arena")
;ChannelVolume chTheme,0.5
fed=charFed(gamChar)
LoadAtmos()
ambR#=0 : ambG#=0 : ambB#=0
ambTR#=0 : ambTG#=0 : ambTB#=0
lightR#=0 : lightG#=0 : lightB#=0
lightTR#=0 : lightTG#=0 : lightTB#=0
LoadArena()
LoadRopes()
;video screens
If fed=>1 And fed=<6
 tVideo(4)=LoadTexture("World/Videos/Promotion0"+fed+".JPG")
Else
 tVideo(4)=LoadTexture("World/Videos/Video02.JPG")
EndIf
For count=1 To 10
 If FindChild(world,"Screen"+Dig$(count,10))>0
  EntityTexture FindChild(world,"Screen"+Dig$(count,10)),tVideo(4),0,1
 EndIf
Next
FreeTexture tVideo(4)
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
go=0 : gotim=-25 : keytim=10
While go=0

 Cls
 frames=WaitTimer(timer)
 For framer=1 To frames
	
	;timers
	keytim=keytim-1
	If keytim<1 Then keytim=0
	
	;PORTAL
    gotim=gotim+1
	If gotim>225 And keytim=0
	 If KeyDown(1) Or KeyDown(28) Or ButtonPressed() Or MouseDown(1) Or gotim=>360 Then go=1
    EndIf 
    ;reaction
    If gotim=180
     attender=gamAttendance(gamDate)
     If gamSchedule(gamDate)=>2 Then attender=attender/2
     If attender<2000 Then ProduceSound(0,sCrowd(8),0,0.5)
     If attender=>2000 And attender<4000 Then ProduceSound(0,sCrowd(5),0,0.5)
     If attender=>4000 And attender<6000 Then ProduceSound(0,sCrowd(4),0,0.5)
     If attender=>6000 And attender<8000 Then ProduceSound(0,sCrowd(2),0,0.5)
     If attender=>8000 Then ProduceSound(0,sCrowd(6),0,0.5) : ProduceSound(0,sCrowd(9),0,0.5)
    EndIf

    ;CAMERA
    If gotim=>0
     PositionEntity cam,0,50,0
     RotateEntity cam,0,gotim,0
    EndIf
    ;animate crowd
    If go=0 And arenaCrowd>0 And optCrowdAnim=1
     AnimateCrowd()
    EndIf
    ;manage atmos
    ManageAtmos()
    ;music
	ManageMusic(-2)
 	
 UpdateWorld
 Next
 RenderWorld 1
 
 ;DISPLAY
 ;decribe attendance
 If gotim=>100 And gotim=<340
  SetFont font(8)
  Outline("Tonight's show from "+textCity$(fed,arenaPreset),rX#(400),rY#(300)-110,0,0,0,255,255,255)
  Outline("on the "+DescribeDate$(gamDate,gamYear),rX#(400),rY#(300)-80,0,0,0,255,255,255)
  Outline("has drawn an audience of...",rX#(400),rY#(300)-50,0,0,0,255,255,255)
  If gotim=>180
   SetFont fontNews(12)
   Outline(GetFigure$(gamAttendance(gamDate)),rX#(400),rY#(300)+5,0,0,0,255,Rnd(150,225),100)
  EndIf
 EndIf
 ;mask shaky start!
 If gotim=<0 Then Loader("Please Wait","Generating Arena")

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;restore sound
;PlaySound sMenuGo
FreeTimer timer
FreeEntity camListener
StopChannel chCrowd
StopChannel chAtmos
;remove entities
FreeEntity cam
FreeEntity camPivot
FreeEntity dummy
For cyc=1 To no_lights
 FreeEntity light(cyc)
Next
FreeEntity lightPivot
FreeEntity world
For cyc=1 To 12
 FreeEntity rope(cyc)
Next
;remove signs
If no_signs>0
 For cyc=1 To no_signs
  FreeEntity sign(cyc)
 Next
EndIf
ClearWorld 1,1,0
End Function

;--------------------------------------------------------------------
;////////////////////////// LOAD ARENA //////////////////////////////
;--------------------------------------------------------------------
Function LoadArena()
 ;load model
 GetArenaSettings(arenaPreset)
 If arenaType=1 Then world=LoadAnimMesh("World/Arena/Hall.3ds")
 If arenaType=>2 Then world=LoadAnimMesh("World/Arena/Stadium.3ds")
 DecorateArena()
 DecorateRing()
 PrepareScenery()
 ;videos
 If screen>0 And screen<>12
  If fed=>1 And fed=<6
   tVideo(4)=LoadTexture("World/Videos/Promotion0"+fed+".JPG")
   tVideo(5)=LoadTexture("World/Videos/Flag0"+fed+".JPG")
  Else
   If fed=0 Then tVideo(4)=LoadTexture("World/Videos/Video02.JPG")
   If fed>0 Then tVideo(4)=LoadTexture("Graphics/Promotions/Promotion0"+fed+".png")
   tVideo(5)=LoadTexture("World/Videos/Flag02.JPG")
  EndIf
  tVideo(6)=LoadTexture("World/Banners/Poster"+Dig$(Rnd(1,18),10)+".JPG")
  If arenaApron=>16 And arenaApron=<18
   tVideo(20)=LoadTexture("Data/Slot0"+slot+"/Portraits/Photo"+Dig$(fedRoster(9,fedSize(9)),100)+".bmp")
   ScaleTexture tVideo(20),1,2
   factor#=GetPercent#(charHeight(fedRoster(9,fedSize(9))),36)
   pos#=0.85+PercentOf#(0.15,factor#)
   PositionTexture tVideo(20),1,pos# 
  EndIf
 EndIf
 ;girders
 EntityTexture FindChild(world,"Girders"),tGirder(1)
 ;banner advertizing
 For count=1 To 16
  If FindChild(world,"Banner"+Dig$(count,10))>0
   If screen=0 Or optDetail=<1 Or screenAgenda=10 Or (fed=7 And arenaAttendance=0)
    HideEntity FindChild(world,"Banner"+Dig$(count,10))
   Else
    tBanner=LoadTexture("World/Banners/Banner"+Dig$(Rnd(1,10),10)+".JPG")
    EntityTexture FindChild(world,"Banner"+Dig$(count,10)),tBanner
    FreeTexture tBanner
   EndIf
  EndIf
 Next 
 ;remove posters/books
 If screen>0 And optDetail=<1
  For count=1 To 10
   If FindChild(world,"PFrame"+Dig$(count,10))>0
    HideEntity FindChild(world,"PFrame"+Dig$(count,10))
   EndIf
  Next
  For count=1 To 12
   If FindChild(world,"Poster"+Dig$(count,10))>0
    HideEntity FindChild(world,"Poster"+Dig$(count,10))
   EndIf
  Next
  For count=1 To 25
   If FindChild(world,"Books"+Dig$(count,10))>0
    HideEntity FindChild(world,"Books"+Dig$(count,10))
   EndIf
  Next
 EndIf
 ;reduced detail
 If screen>0 And optDetail=0
  For count=1 To 3
   HideEntity FindChild(world,"Toilet"+Dig$(count,10))
   HideEntity FindChild(world,"Lid"+Dig$(count,10))
  Next
  HideEntity FindChild(world,"Plant01")
  HideEntity FindChild(world,"Plant02")
  HideEntity FindChild(world,"Plant04")
  HideEntity FindChild(world,"Plant06")
  HideEntity FindChild(world,"Monitor")
  HideEntity FindChild(world,"Monitor01") 
  For count=1 To 16
   HideEntity FindChild(world,"Locker"+Dig$(count,10))
  Next
  HideEntity FindChild(world,"Book Shelf") 
  For count=1 To 2
   HideEntity FindChild(world,"Sofa"+Dig$(count,10))
  Next
 EndIf
 ;crowd
 crowdLimit=0
 If arenaType=1
  arenaCrowd=0
  ;If arenaAttendance>0 Then arenaCrowd=1
  ;If arenaAttendance>35 Then arenaCrowd=2
  ;If arenaAttendance>70 Then arenaCrowd=3
  If arenaAttendance>0 Then arenaCrowd=3
  If arenaCrowd=1 Then crowdLimit=10 
  If arenaCrowd=2 Then crowdLimit=20 
  If arenaCrowd=3 Then crowdLimit=30
 EndIf
 If arenaType=>2
  arenaCrowd=PercentOf#(14,arenaAttendance)
  If arenaAttendance>0 And arenaCrowd<1 Then arenaCrowd=1
  ;If arenaCrowd=1 Then crowdLimit=10 
  ;If arenaCrowd=2 Then crowdLimit=16 
  If arenaCrowd=>1 Then crowdLimit=22 
  For count=1 To 12
   If count>1 And arenaCrowd=>count Then crowdLimit=crowdLimit+14
  Next
 EndIf
 no_signs=0
 For count=1 To 200
  If FindChild(world,"Crowd"+Dig$(count,100))>0
   EntityTexture FindChild(world,"Crowd"+Dig$(count,100)),tCrowd(Rnd(1,5))
   crowdSource#(count)=EntityY(FindChild(world,"Crowd"+Dig$(count,100)))
   crowdY#(count)=crowdSource#(count)
   crowdTY#(count)=crowdY#(count)
   If count>crowdLimit Then HideEntity FindChild(world,"Crowd"+Dig$(count,100))
   If screen>0 And optCrowdAnim=>2 And count=<crowdLimit And CrowdShape(count)=>0
    randy=Rnd(0,6)
    If randy=0 Or (randy=<2 And count=<22) Or arenaType=1 Or CrowdShape(count)=1 Then LoadSign(count)
   EndIf
  EndIf
 Next
 ;load scenery blocks
 If screen>0 Then LoadBlocks()
End Function

;CROWD SHAPE
Function CrowdShape(crowder) ;-1=broken, 0=normal, 1=elongated
 value=0
 If arenaType=1
  If crowder=3 Or crowder=13 Or crowder=23 Then value=1
  If crowder=8 Or crowder=18 Or crowder=28 Then value=1
 Else
  If crowder=23 Or crowder=37 Or crowder=51 Or crowder=65 Or crowder=79 Or crowder=93 Then value=-1
  If crowder=30 Or crowder=44 Or crowder=58 Or crowder=72 Or crowder=86 Or crowder=100 Then value=-1
  ;If crowder=1 Or crowder=5 Or crowder=6 Or crowder=10 Then value=1
  If crowder=3 Or crowder=12 Or crowder=18 Then value=1
  If crowder=8 Or crowder=15 Or crowder=21 Then value=1
 EndIf
 Return value
End Function

;LOAD SIGN
Function LoadSign(crowder)
 ;appearance
 no_signs=no_signs+1
 sign(no_signs)=LoadAnimMesh("World/Signs/Model.3ds")
 Repeat
  signer=Rnd(1,no_signtex) : satisfied=1
  randy=Rnd(0,20)
  If randy=1 And fed=>1 And fed=<6 Then signer=fed
  If randy=2 And fed=>1 And fed=<6 Then signer=fed+6
  If randy=3 And fed=>1 And fed=<6 Then signer=fed+12
  randy=Rnd(0,1)
  If randy=0 And fed=>1 And fed=<6 And signer=<18 
   If signer<>fed And signer<>fed+6 And signer<>fed+12 Then satisfied=0
  EndIf
 Until satisfied=1
 EntityTexture FindChild(sign(no_signs),"Sign"),tSign(signer)
 scaler#=Rnd#(0.6,0.9)
 ScaleEntity sign(no_signs),Rnd#(0.6,0.9),Rnd#(0.7,1.0),1
 Animate sign(no_signs),1,Rnd#(0.25,1.0)
 ;position
 signX#(no_signs)=EntityX(FindChild(world,"Crowd"+Dig$(crowder,100)))
 signY#(no_signs)=EntityY(FindChild(world,"Crowd"+Dig$(crowder,100)))+Rnd(30,38)
 signZ#(no_signs)=EntityZ(FindChild(world,"Crowd"+Dig$(crowder,100)))
 signA#(no_signs)=EntityYaw(FindChild(world,"Crowd"+Dig$(crowder,100)))
 PositionEntity sign(no_signs),signX#(no_signs),signY#(no_signs),signZ#(no_signs)
 RotateEntity sign(no_signs),0,signA#(no_signs),0
 If CrowdShape(crowder)=0 Then MoveEntity sign(no_signs),Rnd(-50,50),0,0
 If CrowdShape(crowder)=1 Then MoveEntity sign(no_signs),Rnd(-150,150),0,0
 signX#(no_signs)=EntityX(sign(no_signs))
 signZ#(no_signs)=EntityZ(sign(no_signs))
 If signX#(no_signs)<0 Then signX#(no_signs)=signX#(no_signs)-2
 If signX#(no_signs)>0 Then signX#(no_signs)=signX#(no_signs)+2
 If signZ#(no_signs)<0 Then signZ#(no_signs)=signZ#(no_signs)-2
 If signZ#(no_signs)>0 Then signZ#(no_signs)=signZ#(no_signs)+2
 PositionEntity sign(no_signs),signX#(no_signs),signY#(no_signs),signZ#(no_signs)
End Function

;GET ARENA SETTINGS
Function GetArenaSettings(option)
 ;white hall
 If option=1 Or option=11
  If option=>11 Then arenaType=2 Else arenaType=1
  arenaWall(1)=1 : arenaCeiling(1)=2 : arenaGround(1)=8
  arenaWall(2)=2 : arenaCeiling(2)=4 : arenaGround(2)=7
  arenaWall(3)=5 : arenaCeiling(3)=8 : arenaGround(3)=11
 EndIf 
 ;grey hall
 If option=2 Or option=12
  If option=>11 Then arenaType=2 Else arenaType=1
  arenaWall(1)=2 : arenaCeiling(1)=4 : arenaGround(1)=7
  arenaWall(2)=9 : arenaCeiling(2)=6 : arenaGround(2)=5
  arenaWall(3)=8 : arenaCeiling(3)=5 : arenaGround(3)=12
 EndIf 
 ;dingy hall
 If option=3 Or option=13
  If option=>11 Then arenaType=2 Else arenaType=1
  arenaWall(1)=3 : arenaCeiling(1)=5 : arenaGround(1)=4
  arenaWall(2)=9 : arenaCeiling(2)=6 : arenaGround(2)=5
  arenaWall(3)=2 : arenaCeiling(3)=4 : arenaGround(3)=1
 EndIf 
 ;classy hall
 If option=4 Or option=14
  If option=>11 Then arenaType=2 Else arenaType=1
  arenaWall(1)=4 : arenaCeiling(1)=3 : arenaGround(1)=12
  arenaWall(2)=6 : arenaCeiling(2)=7 : arenaGround(2)=5
  arenaWall(3)=5 : arenaCeiling(3)=8 : arenaGround(3)=8
 EndIf 
 ;wooden hall
 If option=5 Or option=15
  If option=>11 Then arenaType=2 Else arenaType=1
  arenaWall(1)=5 : arenaCeiling(1)=8 : arenaGround(1)=8
  arenaWall(2)=1 : arenaCeiling(2)=2 : arenaGround(2)=7
  arenaWall(3)=7 : arenaCeiling(3)=3 : arenaGround(3)=10
 EndIf 
 ;red brick hall
 If option=6 Or option=16
  If option=>11 Then arenaType=2 Else arenaType=1
  arenaWall(1)=6 : arenaCeiling(1)=7 : arenaGround(1)=3
  arenaWall(2)=8 : arenaCeiling(2)=5 : arenaGround(2)=4
  arenaWall(3)=3 : arenaCeiling(3)=5 : arenaGround(3)=1
 EndIf 
 ;yellow brick hall
 If option=7 Or option=17
  If option=>11 Then arenaType=2 Else arenaType=1
  arenaWall(1)=7 : arenaCeiling(1)=3 : arenaGround(1)=9
  arenaWall(2)=1 : arenaCeiling(2)=2 : arenaGround(2)=8
  arenaWall(3)=5 : arenaCeiling(3)=8 : arenaGround(3)=11
 EndIf 
 ;dark hall
 If option=8 Or option=18
  If option=>11 Then arenaType=2 Else arenaType=1
  arenaWall(1)=8 : arenaCeiling(1)=5 : arenaGround(1)=2
  arenaWall(2)=11 : arenaCeiling(2)=5 : arenaGround(2)=6
  arenaWall(3)=9 : arenaCeiling(3)=6 : arenaGround(3)=12
 EndIf 
 ;concrete hall
 If option=9 Or option=19
  If option=>11 Then arenaType=2 Else arenaType=1
  arenaWall(1)=9 : arenaCeiling(1)=6 : arenaGround(1)=1
  arenaWall(2)=2 : arenaCeiling(2)=4 : arenaGround(2)=4
  arenaWall(3)=8 : arenaCeiling(3)=5 : arenaGround(3)=11
 EndIf 
 ;metal hall
 If option=10 Or option=20
  If option=>11 Then arenaType=2 Else arenaType=1
  arenaWall(1)=11 : arenaCeiling(1)=5 : arenaGround(1)=6
  arenaWall(2)=9 : arenaCeiling(2)=6 : arenaGround(2)=4
  arenaWall(3)=2 : arenaCeiling(3)=4 : arenaGround(3)=1
 EndIf 
 ;starry stadium
 If option=21
  arenaType=2
  arenaWall(1)=12 : arenaCeiling(1)=1 : arenaGround(1)=4
  arenaWall(2)=9 : arenaCeiling(2)=6 : arenaGround(2)=1
  arenaWall(3)=8 : arenaCeiling(3)=5 : arenaGround(3)=11
 EndIf
 ;classic stadium
 If option=22
  arenaType=2
  arenaWall(1)=14 : arenaCeiling(1)=1 : arenaGround(1)=2
  arenaWall(2)=2 : arenaCeiling(2)=4 : arenaGround(2)=7
  arenaWall(3)=6 : arenaCeiling(3)=7 : arenaGround(3)=10
 EndIf
 ;regular stadium
 If option=23
  arenaType=2
  arenaWall(1)=13 : arenaCeiling(1)=1 : arenaGround(1)=1
  arenaWall(2)=3 : arenaCeiling(2)=5 : arenaGround(2)=4
  arenaWall(3)=4 : arenaCeiling(3)=3 : arenaGround(3)=10
 EndIf
 ;vegas stadium
 If option=24
  arenaType=2
  arenaWall(1)=15 : arenaCeiling(1)=1 : arenaGround(1)=7
  arenaWall(2)=11 : arenaCeiling(2)=5 : arenaGround(2)=6
  arenaWall(3)=7 : arenaCeiling(3)=3 : arenaGround(3)=8
 EndIf
 ;outdoor concrete
 If option=25
  arenaType=3
  arenaWall(1)=16 : arenaCeiling(1)=9 : arenaGround(1)=2
  arenaWall(2)=6 : arenaCeiling(2)=7 : arenaGround(2)=7
  arenaWall(3)=3 : arenaCeiling(3)=5 : arenaGround(3)=1
 EndIf
 ;outdoor beach
 If option=26
  arenaType=3
  arenaWall(1)=16 : arenaCeiling(1)=9 : arenaGround(1)=9
  arenaWall(2)=1 : arenaCeiling(2)=2 : arenaGround(2)=9
  arenaWall(3)=7 : arenaCeiling(3)=3 : arenaGround(3)=9
 EndIf
End Function

;GENERATE ARENA
Function GenerateArena(promotion,event,ring)
 ;location
 arenaPreset=Rnd(1,no_arenas)
 If event=1 Then arenaPreset=Rnd(1,10)
 If event=>2 
  randy=Rnd(0,1)
  If randy=0 Then arenaPreset=Rnd(21,no_arenas) Else arenaPreset=Rnd(11,no_arenas)
 EndIf
 If event=3
  If CupStage(cupFoc(cupSlot))=1 Then arenaPreset=Rnd(11,no_arenas)
  If CupStage(cupFoc(cupSlot))=2 Then arenaPreset=Rnd(11,no_arenas)
  If CupStage(cupFoc(cupSlot))=3 Then arenaPreset=Rnd(11,no_arenas)
  If CupStage(cupFoc(cupSlot))=4 Then arenaPreset=Rnd(1,10)
  If CupStage(cupFoc(cupSlot))=5 Then arenaPreset=Rnd(1,10)
 EndIf
 If promotion=7
  If arenaPreset=>1 And arenaPreset=<10 Then arenaPreset=1
  If arenaPreset=>11 And arenaPreset=<no_arenas Then arenaPreset=11
 EndIf
 ;atmosphere
 arenaAtmos=0
 If optFog>0
  arenaAtmos=Rnd(1,no_atmos)
  If arenaPreset=25 Or arenaPreset=26 Then arenaAtmos=Rnd(3,4)
 EndIf
 ;exhibition attendance
 If game=0
  arenaAttendance=Rnd(1,10)
  arenaAttendance=arenaAttendance*10
 EndIf
 ;ropes
 arenaRopes=Rnd(1,no_ropes)
 If promotion=1
  randy=Rnd(0,6)
  If randy=<1 Then arenaRopes=13
  If randy=2 Then arenaRopes=9
  If randy=3 Then arenaRopes=2
  If randy=4 Then arenaRopes=3
  If randy=5 Then arenaRopes=Rnd(11,12)
  If randy=6 Then arenaRopes=14
 EndIf
 If promotion=2
  randy=Rnd(0,3)
  If randy=<1 Then arenaRopes=14
  If randy=2 Then arenaRopes=2
  If randy=3 Then arenaRopes=3
  If randy=4 Then arenaRopes=8
 EndIf 
 If promotion=3
  randy=Rnd(0,5)
  If randy=<1 Then arenaRopes=15
  If randy=2 Then arenaRopes=Rnd(1,2)
  If randy=3 Then arenaRopes=Rnd(9,10)
  If randy=4 Then arenaRopes=Rnd(16,17)
  If randy=5 Then arenaRopes=18
 EndIf 
 If promotion=4
  randy=Rnd(0,6)
  If randy=<1 Then arenaRopes=19
  If randy=2 Then arenaRopes=Rnd(1,2)
  If randy=3 Then arenaRopes=7
  If randy=4 Then arenaRopes=Rnd(9,10)
  If randy=5 Then arenaRopes=Rnd(11,12)
  If randy=6 Then arenaRopes=20
 EndIf
 If promotion=5
  randy=Rnd(0,4)
  If randy=<1 Then arenaRopes=21
  If randy=2 Then arenaRopes=Rnd(1,2)
  If randy=3 Then arenaRopes=3
  If randy=4 Then arenaRopes=9
 EndIf 
 If promotion=6
  randy=Rnd(0,6)
  If randy=<1 Then arenaRopes=22
  If randy=2 Then arenaRopes=1
  If randy=3 Then arenaRopes=3
  If randy=4 Then arenaRopes=5
  If randy=5 Then arenaRopes=6
  If randy=6 Then arenaRopes=9
 EndIf 
 If promotion=7
  randy=Rnd(0,3)
  If randy=<1 Then arenaRopes=9
  If randy=1 Then arenaRopes=1
  If randy=2 Then arenaRopes=2
 EndIf
 If promotion=0 Or promotion=>8
  randy=Rnd(0,3)
  If randy=<1 Then arenaRopes=14
  If randy=2 Then arenaRopes=Rnd(1,2)
  If randy=3 Then arenaRopes=Rnd(9,10)
 EndIf
 ;canvas
 If event=<1 Or event=3
  randy=Rnd(0,3)
  If randy=<1 Then arenaCanvas=Rnd(1,4)
  If randy=2 Then arenaCanvas=Rnd(5,8)
  If randy=3 Then arenaCanvas=9 
  If fed=7 Then arenaCanvas=Rnd(1,4)
 EndIf
 If event=2
  randy=Rnd(0,3)
  If randy=<1 Then arenaCanvas=9
  If randy=2 Then arenaCanvas=Rnd(5,8)
  If randy=3 Then arenaCanvas=Rnd(1,4)
 EndIf
 If event=>4
  randy=Rnd(0,3)
  If randy=<1 Then arenaCanvas=10
  If randy=2 Then arenaCanvas=Rnd(5,8)
  If randy=3 Then arenaCanvas=Rnd(1,4)
 EndIf
 ;apron
 colour=Rnd(-1,3)
 If promotion=>4 And promotion=<6 Then colour=Rnd(1,3)
 If colour<1 Then colour=1
 Repeat
  arenaApron=Rnd(4,6) 
  If event=2 Then arenaApron=Rnd(7,9) 
  If event=3 Then arenaApron=Rnd(10,12)
  If event=4 Then arenaApron=Rnd(4,9)
  If event=5 Then arenaApron=Rnd(13,15)
  If event=6 Then arenaApron=Rnd(16,18)
 Until ApronColour(arenaApron)=colour
 ;turnbuckles
 arenaPads=Rnd(1,ApronColour(arenaApron))
 randy=Rnd(0,1)
 If randy=0 Then arenaPads=ApronColour(arenaApron)
 ;matting
 arenaMatting=Rnd(0,3)
 randy=Rnd(0,1)
 If randy=0 Then arenaMatting=Rnd(1,2)
 ;store for career
 If game=1
  If gamVenue(gamDate)=0 Then gamVenue(gamDate)=arenaPreset 
  If gamAtmos(gamDate)=0 Then gamAtmos(gamDate)=arenaAtmos
  If ring=1
   If gamRopes(gamDate)=0 Then gamRopes(gamDate)=arenaRopes
   If gamPads(gamDate)=0 Then gamPads(gamDate)=arenaPads
   If gamCanvas(gamDate)=0 Then gamCanvas(gamDate)=arenaCanvas
   If gamApron(gamDate)=0 Then gamApron(gamDate)=arenaApron
   If gamMatting(gamDate)=0 Then gamMatting(gamDate)=arenaMatting
  EndIf
 EndIf
End Function

;CALCULATE ATTENDANCE (based on selected characters)
Function GenerateAttendance()
 value=0
 ;average talent popularity
 popper=0
 For count=1 To fedSize(charFed(gamChar))
  char=fedRoster(charFed(gamChar),count)
  popper=popper+charPopularity(char)
 Next
 popper=popper/fedSize(charFed(gamChar))
 If fedChampWorld(charFed(gamChar))>0 Then popper=(popper/2)+(charPopularity(fedChampWorld(charFed(gamChar)))/2)
 ;promotion popularity
 fedPopper=fedPopularity(charFed(gamChar))
 If gamSchedule(gamDate)=>4 Then fedPopper=100 
 ;calculate core attendance
 value=(popper-40)*(fedPopper-40)
 value=Rnd(value,value*2)
 value=value*2
 ;magnify for big venues
 If arenaPreset=>11
  magnifier=60+(((popper-30)+(fedPopper-30))*2)
  value=PercentOf#(value,magnifier)
  If gamSchedule(gamDate)<2 Then value=value/2
  If value>30000 Then value=30000
 Else
  If value>10000 Then value=10000
 EndIf
 ;negate audience
 ;If charFed(gamChar)=7 And gamSchedule(gamDate)<2 Then value=0
 If game=1 And gamAgreement(20)>0 Then value=0
 Return value
End Function

;TRANSLATE ATTENDANCE (as percentage)
Function TranslateAttendance(attendance)
 value=Int(GetPercent#(attendance,10000))
 If arenaPreset=>11 Then value=Int(GetPercent#(attendance,30000))
 If value>100 Then value=100
 Return value
End Function

;GET APRON COLOUR
Function ApronColour(apron) ;1=black, 2=blue, 3=white
 If apron=1 Or apron=4 Or apron=7 Or apron=10 Or apron=13 Or apron=16 Then value=1
 If apron=2 Or apron=5 Or apron=8 Or apron=11 Or apron=14 Or apron=17 Then value=2
 If apron=3 Or apron=6 Or apron=9 Or apron=12 Or apron=15 Or apron=18 Then value=3
 Return value
End Function

;DECORATE ARENA
Function DecorateArena()
 ;building
 For count=1 To 3
  ;walls
  tWall(count)=LoadTexture("World/Walls/Wall"+Dig$(arenaWall(count),10)+".JPG")
  If arenaType=2 And arenaWall(count)=<10 Then ScaleTexture tWall(count),0.5,1.0
  EntityTexture FindChild(world,"Walls"+Dig$(count,10)),tWall(count)
  FreeTexture tWall(count)
  ;ceilings
  tCeiling(count)=LoadTexture("World/Ceilings/Ceiling"+Dig$(arenaCeiling(count),10)+".JPG")
  If arenaType=2 And arenaWall(count)=<10 Then ScaleTexture tCeiling(count),0.5,0.5
  EntityTexture FindChild(world,"Ceiling"+Dig$(count,10)),tCeiling(count)
  FreeTexture tCeiling(count)
  ;grounds
  tGround(count)=LoadTexture("World/Floors/Ground"+Dig$(arenaGround(count),10)+".JPG")
  If count=1 And (arenaGround(count)=11 Or arenaGround(count)=12)
   RotateTexture tGround(count),90
   ScaleTexture tGround(count),0.5,0.5
  EndIf
  EntityTexture FindChild(world,"Ground"+Dig$(count,10)),tGround(count)
  FreeTexture tGround(count)
 Next
 ;sky effect
 If arenaType=3
  EntityFX FindChild(world,"Walls01"),1
  EntityFX FindChild(world,"Ceiling01"),1
  HideEntity FindChild(world,"Girders")
 EndIf
 ;curtains
 If fed=>1 And fed=<6
  tCurtain=LoadTexture("World/Curtains/Curtain"+Dig$(fed,10)+".JPG")
 Else
  tCurtain=LoadTexture("World/Curtains/Curtain00.JPG")
 EndIf
 For count=1 To 2
  EntityTexture FindChild(world,"Curtain0"+count),tCurtain
 Next 
 FreeTexture tCurtain
 ;posters
 If screen>0 And (optDetail=>2 Or screen<>50)
  For count=3 To 12
   If FindChild(world,"Poster"+Dig$(count,10))>0 And count<>8
    randy=Rnd(1,9)
    If randy=<5 Then tPoster(0)=LoadTexture("World/Banners/Poster"+Dig$(Rnd(1,18),10)+".JPG")
    If fed=>1 And fed=<6
     If randy=6 Then tPoster(0)=LoadTexture("World/Videos/Flag0"+fed+".JPG")
     If randy=7 Then tPoster(0)=LoadTexture("World/Videos/Promotion0"+fed+".JPG")
    Else
     If randy=6 Then tPoster(0)=LoadTexture("World/Videos/Flag01.JPG")
     If randy=7 Then tPoster(0)=LoadTexture("World/Videos/Video02.JPG")
    EndIf
    If randy=>8 Then tPoster(0)=LoadTexture("World/Videos/Video"+Dig$(Rnd(1,3),10)+".JPG")
    EntityTexture FindChild(world,"Poster"+Dig$(count,10)),tPoster(0)
    FreeTexture tPoster(0)
   EndIf
  Next
 EndIf
End Function

;DECORATE RING
Function DecorateRing()
 ;force tournament apron 
 If screenAgenda=11 
  colour=ApronColour(arenaApron)
  If colour=1 Then arenaApron=10
  If colour=2 Then arenaApron=11
  If colour=3 Then arenaApron=12 
 EndIf
 ;apron
 If arenaApron=<3 Then tApron=LoadTexture("World/Aprons/Plain0"+arenaApron+".JPG")
 If arenaApron=>4 And arenaApron=<9
  If fed=0
   If arenaApron=4 Or arenaApron=7 Then tApron=LoadTexture("World/Aprons/Inter01.JPG")
   If arenaApron=5 Or arenaApron=8 Then tApron=LoadTexture("World/Aprons/Inter02.JPG")
   If arenaApron=6 Or arenaApron=9 Then tApron=LoadTexture("World/Aprons/Inter03.JPG")
  EndIf
  If fed=>1 And fed=<6
   If arenaApron=>4 And arenaApron=<6 Then tApron=LoadTexture("World/Aprons/Promotion0"+fed+"/TV0"+(arenaApron-3)+".JPG")
   If arenaApron=>7 And arenaApron=<9 Then tApron=LoadTexture("World/Aprons/Promotion0"+fed+"/PPV0"+(arenaApron-6)+".JPG")
  EndIf
  If fed=7
   If arenaApron=4 Then tApron=LoadTexture("World/Aprons/Plain01.JPG")
   If arenaApron=5 Then tApron=LoadTexture("World/Aprons/Plain02.JPG")
   If arenaApron=6 Then tApron=LoadTexture("World/Aprons/Plain03.JPG")
   If arenaApron=7 Then tApron=LoadTexture("World/Aprons/School01.JPG")
   If arenaApron=8 Then tApron=LoadTexture("World/Aprons/School02.JPG")
   If arenaApron=9 Then tApron=LoadTexture("World/Aprons/School03.JPG")
  EndIf
  If fed=>8
   If arenaApron=4 Or arenaApron=7 Then tApron=LoadTexture("World/Aprons/Plain01.JPG")
   If arenaApron=5 Or arenaApron=8 Then tApron=LoadTexture("World/Aprons/Plain02.JPG")
   If arenaApron=6 Or arenaApron=9 Then tApron=LoadTexture("World/Aprons/Plain03.JPG")
  EndIf
 EndIf
 If arenaApron=>10 And arenaApron=<12 Then tApron=LoadTexture("World/Aprons/Tournament0"+(arenaApron-9)+".JPG")
 If arenaApron=>13 And arenaApron=<15 Then tApron=LoadTexture("World/Aprons/Charity0"+(arenaApron-12)+".JPG")
 If arenaApron=>16 And arenaApron=<18 Then tApron=LoadTexture("World/Aprons/Tribute0"+(arenaApron-15)+".JPG")
 EntityTexture FindChild(world,"Apron"),tApron
 FreeTexture tApron
 ;canvas
 If arenaCanvas=<4 Then tCanvas=LoadTexture("World/Canvases/Plain"+Dig$(arenaCanvas,10)+".JPG")
 If arenaCanvas=>5 And arenaCanvas=<8 Then tCanvas=LoadTexture("World/Canvases/MDickie"+Dig$(arenaCanvas-4,10)+".JPG")
 If arenaCanvas=9
  If fed=>1 And fed=<6
   tCanvas=LoadTexture("World/Canvases/Promotions/Promotion"+Dig$(fed,10)+".JPG")
  Else
   tCanvas=LoadTexture("World/Canvases/Promotions/Promotion00.JPG")
  EndIf
 EndIf
 If arenaCanvas=10 Then tCanvas=LoadTexture("World/Canvases/Promotions/Promotion00.JPG")
 EntityTexture FindChild(world,"Canvas"),tCanvas,0,1
 FreeTexture tCanvas
 tCanvasEdge=LoadTexture("World/Canvases/Edge0"+ApronColour(arenaApron)+".JPG")
 EntityTexture FindChild(world,"Canvas"),tCanvasEdge,0,2
 FreeTexture tCanvasEdge
 ;turnbuckles
 tPad=LoadTexture("World/Turnbuckles/Pad0"+arenaPads+".JPG")
 For count=1 To 12
  EntityTexture FindChild(world,"Pad"+Dig$(count,10)),tPad
 Next 
 FreeTexture tPad
 ;shine buckles
 For count=1 To 4
  EntityShininess FindChild(world,"Ring"+Dig$(count,10)),1.0
  padExposed(count)=0
 Next
 ;posts
 tPost=LoadTexture("World/Turnbuckles/Post0"+arenaPads+".JPG")
 For count=1 To 4
  EntityTexture FindChild(world,"Post0"+count),tPost
  EntityShininess FindChild(world,"Post0"+count),0.25
 Next
 FreeTexture tPost
 ;matting
 If arenaMatting>0
  tMatting=LoadTexture("World/Mats/Mat0"+arenaMatting+".JPG")
  EntityTexture FindChild(world,"Matting"),tMatting
  FreeTexture tMatting
 Else 
  HideEntity FindChild(world,"Matting")
 EndIf
End Function

;APPLY UNIVERSAL SCENERY
Function PrepareScenery()
 ;assess blackout
 blackout=0
 If game=1 And gamAgreement(18)>0 Then blackout=1
 If screen=53 And negTopic=53 Then blackout=1
 ;plants
 For count=1 To 20
  If FindChild(world,"Bush"+Dig$(count,10))>0
   EntityTexture FindChild(world,"Bush"+Dig$(count,10)),tPlant(1)
   If screen=50 And optDetail=0 Then HideEntity FindChild(world,"Bush"+Dig$(count,10))
  EndIf
  If FindChild(world,"Leaves"+Dig$(count,10))>0
   EntityTexture FindChild(world,"Leaves"+Dig$(count,10)),tPlant(1)
   If screen=50 And optDetail=0 Then HideEntity FindChild(world,"Leaves"+Dig$(count,10))
  EndIf
  If FindChild(world,"Tree"+Dig$(count,10))>0
   EntityTexture FindChild(world,"Tree"+Dig$(count,10)),tPlant(1)
   If screen=50 And optDetail=0 Then HideEntity FindChild(world,"Tree"+Dig$(count,10))
  EndIf
  If FindChild(world,"Fern"+Dig$(count,10))>0
   EntityTexture FindChild(world,"Fern"+Dig$(count,10)),tPlant(2)
   If screen=50 And optDetail=0 Then HideEntity FindChild(world,"Fern"+Dig$(count,10))
  EndIf
 Next
 ;luminous signs
 For count=1 To 20
  If FindChild(world,"Sign"+Dig$(count,10))>0 And blackout=0
   EntityFX FindChild(world,"Sign"+Dig$(count,10)),1
  EndIf
 Next
 ;light bulbs
 For count=1 To 100
  If FindChild(world,"Bulb"+Dig$(count,10))>0 And blackout=0
   EntityFX FindChild(world,"Bulb"+Dig$(count,10)),9
   EntityShininess FindChild(world,"Bulb"+Dig$(count,10)),1.0
  EndIf
 Next
 ;video screens
 For count=1 To 10
  If FindChild(world,"Screen"+Dig$(count,10))>0
   EntityTexture FindChild(world,"Screen"+Dig$(count,10)),tScreen,0,1
   active=1
   If fed=7 And arenaPreset=<10 Then active=0
   If screenAgenda=10 Then active=0
   If game=1 And fed=charFed(gamChar) And fedProduction(charFed(gamChar),8)=0 Then active=0
   If active=1 And blackout=0 Then EntityFX FindChild(world,"Screen"+Dig$(count,10)),1
  EndIf
  videoScreen(count)=0 : videoOldScreen(count)=-1
 Next
End Function

;/////////////////////////////////////////////////////////
;------------------- SCENERY BLOCKS ----------------------
;/////////////////////////////////////////////////////////
Function LoadBlocks()
 ;reset by default
 For count=1 To 100
  blockType(count)=0
  blockX1#(count)=0 : blockX2#(count)=0
  blockZ1#(count)=0 : blockZ2#(count)=0
  blockY1#(count)=0 : blockY2#(count)=0
  blockClimb(count)=0
  blockPlatX1#(count)=0 : blockPlatX2#(count)=0
  blockPlatZ1#(count)=0 : blockPlatZ2#(count)=0
 Next
 ;ring base
 n=0 : blockType(n)=2 : blockClimb(n)=0
 blockX1#(n)=-93 : blockX2#(n)=93
 blockZ1#(n)=-93 : blockZ2#(n)=93
 blockY1#(n)=0 : blockY2#(n)=25
 ;ropes (north)
 n=1 : blockType(n)=1 : blockClimb(n)=0
 blockX1#(n)=blockX1#(0) : blockX2#(n)=blockX2#(0)
 blockZ1#(n)=66 : blockZ2#(n)=blockZ2#(0)
 blockY1#(n)=25 : blockY2#(n)=52
 ;ropes (east)
 n=2 : blockType(n)=1 : blockClimb(n)=0
 blockX1#(n)=66 : blockX2#(n)=blockX2#(0)
 blockZ1#(n)=blockZ1#(0) : blockZ2#(n)=blockZ2#(0)
 blockY1#(n)=25 : blockY2#(n)=52
 ;ropes (south)
 n=3 : blockType(n)=1 : blockClimb(n)=0
 blockX1#(n)=blockX1#(0) : blockX2#(n)=blockX2#(0)
 blockZ1#(n)=blockZ1#(0) : blockZ2#(n)=-66
 blockY1#(n)=25 : blockY2#(n)=52
 ;ropes (west)
 n=4 : blockType(n)=1 : blockClimb(n)=0
 blockX1#(n)=blockX1#(0) : blockX2#(n)=-66
 blockZ1#(n)=blockZ1#(0) : blockZ2#(n)=blockZ2#(0)
 blockY1#(n)=25 : blockY2#(n)=52
 ;north east aisle railing
 n=5 : blockType(n)=2 : blockClimb(n)=5
 blockX1#(n)=18 : blockX2#(n)=35
 blockZ1#(n)=133 : blockZ2#(n)=405
 blockY1#(n)=0 : blockY2#(n)=20.25
 blockPlatX1#(n)=GetCentre#(blockX1#(n),blockX2#(n)) : blockPlatX2#(n)=blockPlatX1#(n)
 blockPlatZ1#(n)=143 : blockPlatZ2#(n)=385
 ;north east railing
 n=6 : blockType(n)=2 : blockClimb(n)=6
 blockX1#(n)=18 : blockX2#(n)=148
 blockZ1#(n)=133 : blockZ2#(n)=150
 blockY1#(n)=0 : blockY2#(n)=20.25
 blockPlatX1#(n)=28 : blockPlatX2#(n)=138
 blockPlatZ1#(n)=GetCentre#(blockZ1#(n),blockZ2#(n)) : blockPlatZ2#(n)=blockPlatZ1#(n)
 ;east railing
 n=7 : blockType(n)=2 : blockClimb(n)=5
 blockX1#(n)=133 : blockX2#(n)=150
 blockZ1#(n)=-150 : blockZ2#(n)=150
 blockY1#(n)=0 : blockY2#(n)=20.25
 blockPlatX1#(n)=GetCentre#(blockX1#(n),blockX2#(n)) : blockPlatX2#(n)=blockPlatX1#(n)
 blockPlatZ1#(n)=-140 : blockPlatZ2#(n)=140
 ;south east railing
 n=8 : blockType(n)=2 : blockClimb(n)=6
 blockX1#(n)=18 : blockX2#(n)=150
 blockZ1#(n)=-150 : blockZ2#(n)=-133
 blockY1#(n)=0 : blockY2#(n)=20.25
 blockPlatX1#(n)=28 : blockPlatX2#(n)=140
 blockPlatZ1#(n)=GetCentre#(blockZ1#(n),blockZ2#(n)) : blockPlatZ2#(n)=blockPlatZ1#(n)
 ;south east aisle railing
 n=9 : blockType(n)=2 : blockClimb(n)=5
 blockX1#(n)=18 : blockX2#(n)=35 
 blockZ1#(n)=-405 : blockZ2#(n)=-133
 blockY1#(n)=0 : blockY2#(n)=20.25
 blockPlatX1#(n)=GetCentre#(blockX1#(n),blockX2#(n)) : blockPlatX2#(n)=blockPlatX1#(n)
 blockPlatZ1#(n)=-395 : blockPlatZ2#(n)=-143
 ;north west aisle railing
 n=10 : blockType(n)=2 : blockClimb(n)=5
 blockX1#(n)=-35 : blockX2#(n)=-18
 blockZ1#(n)=133 : blockZ2#(n)=405
 blockY1#(n)=0 : blockY2#(n)=20.25
 blockPlatX1#(n)=GetCentre#(blockX1#(n),blockX2#(n)) : blockPlatX2#(n)=blockPlatX1#(n)
 blockPlatZ1#(n)=143 : blockPlatZ2#(n)=395
 ;north west railing
 n=11 : blockType(n)=2 : blockClimb(n)=6
 blockX1#(n)=-150 : blockX2#(n)=-18
 blockZ1#(n)=133 : blockZ2#(n)=150
 blockY1#(n)=0 : blockY2#(n)=20.25 
 blockPlatX1#(n)=-140 : blockPlatX2#(n)=-28
 blockPlatZ1#(n)=GetCentre#(blockZ1#(n),blockZ2#(n)) : blockPlatZ2#(n)=blockPlatZ1#(n)
 ;west railing
 n=12 : blockType(n)=2 : blockClimb(n)=5
 blockX1#(n)=-150 : blockX2#(n)=-133
 blockZ1#(n)=-150 : blockZ2#(n)=150
 blockY1#(n)=0 : blockY2#(n)=20.25
 blockPlatX1#(n)=GetCentre#(blockX1#(n),blockX2#(n)) : blockPlatX2#(n)=blockPlatX1#(n)
 blockPlatZ1#(n)=-140 : blockPlatZ2#(n)=140
 ;south west railing
 n=13 : blockType(n)=2 : blockClimb(n)=6
 blockX1#(n)=-150 : blockX2#(n)=-18
 blockZ1#(n)=-150 : blockZ2#(n)=-133
 blockY1#(n)=0 : blockY2#(n)=20.25
 blockPlatX1#(n)=-140 : blockPlatX2#(n)=-28
 blockPlatZ1#(n)=GetCentre#(blockZ1#(n),blockZ2#(n)) : blockPlatZ2#(n)=blockPlatZ1#(n)
 ;south west aisle railing
 n=14 : blockType(n)=2 : blockClimb(n)=5
 blockX1#(n)=-35 : blockX2#(n)=-18
 blockZ1#(n)=-405 : blockZ2#(n)=-133
 blockY1#(n)=0 : blockY2#(n)=20.25
 blockPlatX1#(n)=GetCentre#(blockX1#(n),blockX2#(n)) : blockPlatX2#(n)=blockPlatX1#(n)
 blockPlatZ1#(n)=-395 : blockPlatZ2#(n)=-143
 ;north west seating block / hall wall
 n=15 : blockType(n)=2 : blockClimb(n)=0
 blockX1#(n)=-615 : blockX2#(n)=-32
 blockZ1#(n)=193 : blockZ2#(n)=620
 blockY1#(n)=0 : blockY2#(n)=300
 If arenaType=1 Then blockX2#(n)=-77
 ;north tunnel ceiling / hall wall
 n=16 : blockType(n)=2 : blockClimb(n)=0
 blockX1#(n)=-615 : blockX2#(n)=615
 blockZ1#(n)=382 : blockZ2#(n)=620
 blockY1#(n)=50 : blockY2#(n)=300
 ;north east seating block / hall wall
 n=17 : blockType(n)=2 : blockClimb(n)=0
 blockX1#(n)=32 : blockX2#(n)=615
 blockZ1#(n)=193 : blockZ2#(n)=620
 blockY1#(n)=0 : blockY2#(n)=300
 If arenaType=1 Then blockX1#(n)=77
 ;east seating block / hall wall
 n=18 : blockType(n)=2 : blockClimb(n)=0
 blockX1#(n)=190 : blockX2#(n)=615
 blockZ1#(n)=-620 : blockZ2#(n)=620
 blockY1#(n)=0 : blockY2#(n)=300
 ;south east seating block / hall wall
 n=19 : blockType(n)=2 : blockClimb(n)=0
 blockX1#(n)=32 : blockX2#(n)=615
 blockZ1#(n)=-620 : blockZ2#(n)=-193
 blockY1#(n)=0 : blockY2#(n)=300
 If arenaType=1 Then blockX1#(n)=77
 ;south tunnel ceiling / hall wall
 n=20 : blockType(n)=2 : blockClimb(n)=0
 blockX1#(n)=-45 : blockX2#(n)=45
 blockZ1#(n)=-620 : blockZ2#(n)=-382
 blockY1#(n)=50 : blockY2#(n)=300
 ;south west seating block / hall wall
 n=21 : blockType(n)=2 : blockClimb(n)=0
 blockX1#(n)=-615 : blockX2#(n)=-32
 blockZ1#(n)=-620 : blockZ2#(n)=-193
 blockY1#(n)=0 : blockY2#(n)=300
 If arenaType=1 Then blockX2#(n)=-77
 ;west seating block / hall wall
 n=22 : blockType(n)=2 : blockClimb(n)=0
 blockX1#(n)=-615 : blockX2#(n)=-190
 blockZ1#(n)=-620 : blockZ2#(n)=620
 blockY1#(n)=0 : blockY2#(n)=300
 ;north west curtain edge
 n=23 : blockType(n)=2 : blockClimb(n)=0
 blockX1#(n)=-45 : blockX2#(n)=-10
 blockZ1#(n)=383 : blockZ2#(n)=405
 blockY1#(n)=0 : blockY2#(n)=70
 If arenaType=>2
  blockClimb(n)=1
  blockPlatX1#(n)=-17 : blockPlatX2#(n)=17
  blockPlatZ1#(n)=GetCentre#(blockZ1#(n),blockZ2#(n)) : blockPlatZ2#(n)=blockPlatZ1#(n)
 EndIf
 ;north east curtain edge
 n=24 : blockType(n)=2 : blockClimb(n)=0
 blockX1#(n)=10 : blockX2#(n)=45
 blockZ1#(n)=383 : blockZ2#(n)=405
 blockY1#(n)=0 : blockY2#(n)=70
 If arenaType=>2
  blockClimb(n)=1
  blockPlatX1#(n)=-17 : blockPlatX2#(n)=17
  blockPlatZ1#(n)=GetCentre#(blockZ1#(n),blockZ2#(n)) : blockPlatZ2#(n)=blockPlatZ1#(n)
 EndIf
 ;south west curtain edge
 n=25 : blockType(n)=2 : blockClimb(n)=0
 blockX1#(n)=-45 : blockX2#(n)=-10
 blockZ1#(n)=-405 : blockZ2#(n)=-383
 blockY1#(n)=0 : blockY2#(n)=70
 If arenaType=>2
  blockClimb(n)=3
  blockPlatX1#(n)=-17 : blockPlatX2#(n)=17
  blockPlatZ1#(n)=GetCentre#(blockZ1#(n),blockZ2#(n)) : blockPlatZ2#(n)=blockPlatZ1#(n)
 EndIf
 ;south east curtain edge
 n=26 : blockType(n)=2 : blockClimb(n)=0
 blockX1#(n)=10 : blockX2#(n)=45
 blockZ1#(n)=-405 : blockZ2#(n)=-383
 blockY1#(n)=0 : blockY2#(n)=70
 If arenaType=>2
  blockClimb(n)=3
  blockPlatX1#(n)=-17 : blockPlatX2#(n)=17
  blockPlatZ1#(n)=GetCentre#(blockZ1#(n),blockZ2#(n)) : blockPlatZ2#(n)=blockPlatZ1#(n)
 EndIf
 ;north west tunnel wall
 n=27 : blockType(n)=2 : blockClimb(n)=0
 blockX1#(n)=-615 : blockX2#(n)=-32
 blockZ1#(n)=388 : blockZ2#(n)=620
 blockY1#(n)=0 : blockY2#(n)=300
 ;north east tunnel wall
 n=28 : blockType(n)=2 : blockClimb(n)=0
 blockX1#(n)=32 : blockX2#(n)=315
 blockZ1#(n)=388 : blockZ2#(n)=620
 blockY1#(n)=0 : blockY2#(n)=300
 ;south west tunnel wall
 n=29 : blockType(n)=2 : blockClimb(n)=0
 blockX1#(n)=-615 : blockX2#(n)=-32
 blockZ1#(n)=-620 : blockZ2#(n)=-388
 blockY1#(n)=0 : blockY2#(n)=300
 ;south east tunnel wall
 n=30 : blockType(n)=2 : blockClimb(n)=0
 blockX1#(n)=32 : blockX2#(n)=315
 blockZ1#(n)=-620 : blockZ2#(n)=-388
 blockY1#(n)=0 : blockY2#(n)=300
 ;north east backstage wall
 n=31 : blockType(n)=2 : blockClimb(n)=0
 blockX1#(n)=-265 : blockX2#(n)=-235
 blockZ1#(n)=590 : blockZ2#(n)=910
 blockY1#(n)=0 : blockY2#(n)=105
 ;north backstage wall
 n=32 : blockType(n)=2 : blockClimb(n)=0
 blockX1#(n)=-265 : blockX2#(n)=265
 blockZ1#(n)=880 : blockZ2#(n)=910
 blockY1#(n)=0 : blockY2#(n)=105
 ;north west backstage wall
 n=33 : blockType(n)=2 : blockClimb(n)=0
 blockX1#(n)=235 : blockX2#(n)=265
 blockZ1#(n)=590 : blockZ2#(n)=910
 blockY1#(n)=0 : blockY2#(n)=105
 ;south east backstage wall
 n=34 : blockType(n)=2 : blockClimb(n)=0
 blockX1#(n)=-265 : blockX2#(n)=-142
 blockZ1#(n)=-910 : blockZ2#(n)=-590
 blockY1#(n)=0 : blockY2#(n)=105
 ;south backstage wall
 n=35 : blockType(n)=2 : blockClimb(n)=0
 blockX1#(n)=-265 : blockX2#(n)=265
 blockZ1#(n)=-910 : blockZ2#(n)=-792 
 blockY1#(n)=0 : blockY2#(n)=105
 ;south west backstage wall
 n=36 : blockType(n)=2 : blockClimb(n)=0
 blockX1#(n)=142 : blockX2#(n)=265
 blockZ1#(n)=-910 : blockZ2#(n)=-590
 blockY1#(n)=0 : blockY2#(n)=105
 ;37-38 = FREE
 ;toilet cubicles
 n=39 : blockType(n)=2 : blockClimb(n)=3
 blockX1#(n)=-250 : blockX2#(n)=-172
 blockZ1#(n)=610 : blockZ2#(n)=726
 blockY1#(n)=0 : blockY2#(n)=51
 blockPlatX1#(n)=-240 : blockPlatX2#(n)=-182
 blockPlatZ2#(n)=blockZ2#(n)-11 : blockPlatZ1#(n)=blockPlatZ2#(n)
If optDetail>0
 ;toilet plant
 n=40 : blockType(n)=2 : blockClimb(n)=0
 blockX1#(n)=-250 : blockX2#(n)=-216
 blockZ1#(n)=726 : blockZ2#(n)=745
 blockY1#(n)=0 : blockY2#(n)=12
 ;north locker plant
 n=41 : blockType(n)=2 : blockClimb(n)=0
 blockX1#(n)=86 : blockX2#(n)=115
 blockZ1#(n)=865 : blockZ2#(n)=890
 blockY1#(n)=0 : blockY2#(n)=18
 ;north lockers
 n=42 : blockType(n)=2 : blockClimb(n)=7
 blockX1#(n)=105 : blockX2#(n)=250
 blockZ1#(n)=865 : blockZ2#(n)=890
 blockY1#(n)=0 : blockY2#(n)=38
 blockPlatX1#(n)=125 : blockPlatX2#(n)=240
 blockPlatZ1#(n)=GetCentre#(blockZ1#(n),blockZ2#(n)) : blockPlatZ2#(n)=blockPlatZ1#(n)
 ;north-east locker
 n=43 : blockType(n)=2 : blockClimb(n)=7
 blockX1#(n)=225 : blockX2#(n)=250
 blockZ1#(n)=830 : blockZ2#(n)=890
 blockY1#(n)=0 : blockY2#(n)=17
 blockPlatX1#(n)=GetCentre#(blockX1#(n),blockX2#(n)) : blockPlatX2#(n)=blockPlatX1#(n)
 blockPlatZ1#(n)=840 : blockPlatZ2#(n)=880
 ;42=free
 ;south east lockers
 n=45 : blockType(n)=2 : blockClimb(n)=7
 blockX1#(n)=222 : blockX2#(n)=250
 blockZ1#(n)=610 : blockZ2#(n)=750
 blockY1#(n)=0 : blockY2#(n)=38
 blockPlatX1#(n)=GetCentre#(blockX1#(n),blockX2#(n)) : blockPlatX2#(n)=blockPlatX1#(n)
 blockPlatZ1#(n)=620 : blockPlatZ2#(n)=740
 ;south locker
 n=46 : blockType(n)=2 : blockClimb(n)=7
 blockX1#(n)=183 : blockX2#(n)=250
 blockZ1#(n)=610 : blockZ2#(n)=636
 blockY1#(n)=0 : blockY2#(n)=17
 blockPlatX1#(n)=193 : blockPlatX2#(n)=240
 blockPlatZ1#(n)=GetCentre#(blockZ1#(n),blockZ2#(n)) : blockPlatZ2#(n)=blockPlatZ1#(n)
 ;north west lounge plant
 n=47 : blockType(n)=2 : blockClimb(n)=0
 blockX1#(n)=-150 : blockX2#(n)=-124
 blockZ1#(n)=-634 : blockZ2#(n)=-610
 blockY1#(n)=0 : blockY2#(n)=12
 ;north east lounge plant
 n=48 : blockType(n)=2 : blockClimb(n)=0
 blockX1#(n)=126 : blockX2#(n)=150
 blockZ1#(n)=-634 : blockZ2#(n)=-610
 blockY1#(n)=0 : blockY2#(n)=12
 ;south west sofa
 n=49 : blockType(n)=2 : blockClimb(n)=7
 blockX1#(n)=-150 : blockX2#(n)=-120
 blockZ1#(n)=-776 : blockZ2#(n)=-698
 blockY1#(n)=0 : blockY2#(n)=13
 blockPlatX1#(n)=GetCentre#(blockX1#(n),blockX2#(n)) : blockPlatX2#(n)=blockPlatX1#(n)
 blockPlatZ1#(n)=-766 : blockPlatZ2#(n)=-708
 ;south sofa
 n=50 : blockType(n)=2 : blockClimb(n)=7
 blockX1#(n)=-131 : blockX2#(n)=-51
 blockZ1#(n)=-800 : blockZ2#(n)=-768
 blockY1#(n)=0 : blockY2#(n)=13
 blockPlatX1#(n)=-121 : blockPlatX2#(n)=-61
 blockPlatZ1#(n)=GetCentre#(blockZ1#(n),blockZ2#(n)) : blockPlatZ2#(n)=blockPlatZ1#(n)
 ;bookshelf plant
 n=51 : blockType(n)=2 : blockClimb(n)=0
 blockX1#(n)=8 : blockX2#(n)=35
 blockZ1#(n)=-800 : blockZ2#(n)=-773
 blockY1#(n)=0 : blockY2#(n)=12
 ;south bookshelf
 n=52 : blockType(n)=2 : blockClimb(n)=3
 blockX1#(n)=27 : blockX2#(n)=150
 blockZ1#(n)=-800 : blockZ2#(n)=-781
 blockY1#(n)=0 : blockY2#(n)=43
 blockPlatX1#(n)=37 : blockPlatX2#(n)=140
 blockPlatZ1#(n)=GetCentre#(blockZ1#(n),blockZ2#(n)) : blockPlatZ2#(n)=blockPlatZ1#(n)
 ;south east bookshelf
 n=53 : blockType(n)=2 : blockClimb(n)=2
 blockX1#(n)=133 : blockX2#(n)=150
 blockZ1#(n)=-800 : blockZ2#(n)=-675
 blockY1#(n)=0 : blockY2#(n)=43
 blockPlatX1#(n)=GetCentre#(blockX1#(n),blockX2#(n)) : blockPlatX2#(n)=blockPlatX1#(n)
 blockPlatZ1#(n)=-790 : blockPlatZ2#(n)=-685
 ;south west lounge plant
 n=54 : blockType(n)=2 : blockClimb(n)=0
 blockX1#(n)=-145 : blockX2#(n)=-125
 blockZ1#(n)=-795 : blockZ2#(n)=-775
 blockY1#(n)=0 : blockY2#(n)=12
EndIf
If arenaType=>2
 ;north west seating area climbing ledge
 n=55 : blockType(n)=0 : blockClimb(n)=1
 blockX1#(n)=-615 : blockX2#(n)=-32
 blockZ1#(n)=193 : blockZ2#(n)=215
 blockY1#(n)=0 : blockY2#(n)=25
 blockPlatX1#(n)=-605 : blockPlatX2#(n)=-42
 blockPlatZ1#(n)=blockZ1#(n)+15 : blockPlatZ2#(n)=blockPlatZ1#(n)
 ;north east seating area climbing ledge
 n=56 : blockType(n)=0 : blockClimb(n)=1
 blockX1#(n)=32 : blockX2#(n)=615
 blockZ1#(n)=193 : blockZ2#(n)=215
 blockY1#(n)=0 : blockY2#(n)=25
 blockPlatX1#(n)=42 : blockPlatX2#(n)=605
 blockPlatZ1#(n)=blockZ1#(n)+15 : blockPlatZ2#(n)=blockPlatZ1#(n)
 ;east seating area climbing ledge
 n=57 : blockType(n)=0 : blockClimb(n)=2
 blockX1#(n)=190 : blockX2#(n)=212
 blockZ1#(n)=-620 : blockZ2#(n)=620
 blockY1#(n)=0 : blockY2#(n)=25
 blockPlatX1#(n)=blockX1#(n)+15 : blockPlatX2#(n)=blockPlatX1#(n)
 blockPlatZ1#(n)=-610 : blockPlatZ2#(n)=610
 ;south east seating area climbing ledge
 n=58 : blockType(n)=0 : blockClimb(n)=3
 blockX1#(n)=32 : blockX2#(n)=615
 blockZ1#(n)=-215 : blockZ2#(n)=-193
 blockY1#(n)=0 : blockY2#(n)=25
 blockPlatX1#(n)=42 : blockPlatX2#(n)=605
 blockPlatZ2#(n)=blockZ2#(n)-15 : blockPlatZ1#(n)=blockPlatZ2#(n)
 ;south west seating area climbing ledge
 n=59 : blockType(n)=0 : blockClimb(n)=3
 blockX1#(n)=-615 : blockX2#(n)=-32
 blockZ1#(n)=-215 : blockZ2#(n)=-193
 blockY1#(n)=0 : blockY2#(n)=25
 blockPlatX1#(n)=-605 : blockPlatX2#(n)=-42
 blockPlatZ2#(n)=blockZ2#(n)-15 : blockPlatZ1#(n)=blockPlatZ2#(n)
 ;west seating area climbing ledge
 n=60 : blockType(n)=0 : blockClimb(n)=4
 blockX1#(n)=-212 : blockX2#(n)=-190
 blockZ1#(n)=-620 : blockZ2#(n)=620
 blockY1#(n)=0 : blockY2#(n)=25
 blockPlatX2#(n)=blockX2#(n)-15 : blockPlatX1#(n)=blockPlatX2#(n)
 blockPlatZ1#(n)=-610 : blockPlatZ2#(n)=610
EndIf
End Function

;--------------------------------------------------------------------
;/////////////////////////// ATMOSPHERE /////////////////////////////
;--------------------------------------------------------------------
;LOAD ATMOSPHERE
Function LoadAtmos()
 ;CAMERA(S)
 cam=CreateCamera()
 CameraViewport cam,0,0,GraphicsWidth(),GraphicsHeight()
 CameraZoom cam,1.5
 camX#=0 : camY#=50 : camZ#=0
 camPivX#=0 : camPivY#=30 : camPivZ#=0 
 If matchEntrances=0 Then camZ#=-250
 If matchLocation=1 Then camX#=0 : camY#=60 : camZ#=880 : camPivZ#=760
 If matchLocation=2 Then camX#=-20 : camY#=50 : camZ#=-790 : camPivZ#=-695 
 camTX#=camX# : camTY#=camY# : camTZ#=camZ#
 PositionEntity cam,camX#,camY#,camZ# 
 GetCamera(optDefaultCam)
 camFoc=0 : camRewardFoc=0
 camTempTim=0 : camTempFoc=0
 camAccel=100 : camPivAccel=100
 ;pivot
 dummy=CreatePivot()
 camPivot=CreatePivot() 
 PositionEntity camPivot,camPivX#,camPivY#,camPivZ#
 ;fog effect
 CameraRange cam,1,2000 
 If arenaAtmos>0
  CameraFogMode cam,1
  If arenaType=1 Then CameraFogRange cam,250,1000
  If arenaType=2 Then CameraFogRange cam,375,1250
  If arenaType=3 Then CameraFogRange cam,500,1500
  If arenaAtmos=1 Then CameraFogColor cam,0,0,0 ;black
  If arenaAtmos=2 Then CameraFogColor cam,200,200,200 ;white
  If arenaAtmos=3 Then CameraFogColor cam,160,130,100 ;cream
  If arenaAtmos=4 Then CameraFogColor cam,200,200,240 ;daylight 
  If arenaAtmos=5 Then CameraFogColor cam,100,90,120 ;purple
  If arenaAtmos=6 Then CameraFogColor cam,100,50,50 ;red
  If arenaAtmos=7 Then CameraFogColor cam,50,100,50 ;green
  If arenaAtmos=8 Then CameraFogColor cam,50,50,100 ;blue
  If arenaAtmos=9 Then CameraFogColor cam,Rnd(0,255),Rnd(0,255),Rnd(0,255) ;random
 EndIf
 ;3D listener
 If screen>0
  range#=0.02
  camListener=CreateListener(cam,range#,range#,range#)
 EndIf
 ;lighting
 LoadLighting()
 arenaLight=1
 ambR#=0 : ambG#=0 : ambB#=0
 If arenaType=3 ;Or matchLocation>0
  ambR#=220 : ambG#=210 : ambB#=200
  lightR#=250 : lightG#=230 : lightB#=150
 EndIf
 ;initiate crowd
 If screen>0
  LoopSound sCrowd(1)
  chCrowd=EmitSound(sCrowd(1),cam)
  crowdVol#=0.5 : crowdVolTarget#=crowdVol#
  crowdPitch#=44100 : crowdPitchTarget#=crowdPitch# 
  If arenaType=1 Then attendance#=20+PercentOf#(40,arenaAttendance)
  If arenaType=>2 Then attendance#=25+PercentOf#(75,arenaAttendance)
  If arenaCrowd=0 Then attendance#=0
  ChannelVolume chCrowd,PercentOf#(crowdVol#,attendance#)
  ChannelPitch chCrowd,crowdPitch#
 EndIf
 ;add background noise
 If screen>0
  If arenaPreset=<24 Then sAtmos=LoadSound("Sound/Crowd/Atmosphere.wav")
  If arenaPreset=25 Then sAtmos=LoadSound("Sound/Crowd/City.wav") 
  If arenaPreset=26 Then sAtmos=LoadSound("Sound/Crowd/Beach.wav") 
  LoopSound sAtmos
  chAtmos=EmitSound(sAtmos,cam)
  ChannelVolume chAtmos,0.5
 EndIf
End Function

;LOAD LIGHTING
Function LoadLighting()
 ;load light entities
 lightPivot=CreatePivot()
 no_lights=3
 ;main ring light
 light(1)=CreateLight(3)
 PositionEntity light(1),Rnd(-100,100),100,Rnd(-100,100)
 PositionEntity lightPivot,Rnd(-100,100),20,Rnd(-100,100)
 PointEntity light(1),lightPivot
 ;north backstage light
 light(2)=CreateLight(3)
 PositionEntity light(2),Rnd(-230,230),100,Rnd(645,860)
 PositionEntity lightPivot,Rnd(-230,230),5,Rnd(645,860)
 PointEntity light(2),lightPivot
 ;south backstage light
 light(3)=CreateLight(3)
 PositionEntity light(3),Rnd(-150,150),75,Rnd(645,800)
 PositionEntity lightPivot,Rnd(-150,150),5,Rnd(645,800)
 PointEntity light(3),lightPivot
End Function

;MANAGE ATMOSPHERE
Function ManageAtmos()
 ;outdoor override
 If arenaType=3 Then arenaLight=1
 ;production override
 If game=1 And fed=charFed(gamChar) And fedProduction(charFed(gamChar),7)=0 Then arenaLight=1
 ;power failure override
 If game=1 And gamAgreement(18)>0 Then arenaLight=0
 ;darkness
 If arenaLight=0
  ambTR#=20 : ambTG#=20 : ambTB#=30
  lightTR#=10 : lightTG#=10 : lightTB#=10
 EndIf
 ;main light
 If arenaLight=1
  ambTR#=220 : ambTG#=210 : ambTB#=200
  lightTR#=250 : lightTG#=230 : lightTB#=150
 EndIf
 ;multi-coloured (dark)
 If arenaLight=2 Or arenaLight=3
  If arenaLight=3 Then randy=Rnd(0,10) Else randy=Rnd(0,50)
  If randy=0 Then ambTR#=Rnd(0,100) : ambTG#=Rnd(0,100) : ambTB#=Rnd(0,100)
  If randy=1 Then lightTR#=Rnd(0,100) : lightTG#=Rnd(0,100) : lightTB#=Rnd(0,100) 
  If arenaLight=3
   ambR#=ambTR# : ambG#=ambTG# : ambB#=ambTB#
   lightR#=lightTR# : lightG#=lightTG# : lightB#=lightTB#
  EndIf  
 EndIf
 ;multi-coloured (normal)
 If arenaLight=4 Or arenaLight=5
  If arenaLight=5 Then randy=Rnd(0,10) Else randy=Rnd(0,50)
  If randy=0 Then ambTR#=Rnd(0,255) : ambTG#=Rnd(0,255) : ambTB#=Rnd(0,255)
  If randy=1 Then lightTR#=Rnd(0,255) : lightTG#=Rnd(0,255) : lightTB#=Rnd(0,255) 
  If arenaLight=5
   ambR#=ambTR# : ambG#=ambTG# : ambB#=ambTB#
   lightR#=lightTR# : lightG#=lightTG# : lightB#=lightTB#
  EndIf  
 EndIf
 ;red
 If arenaLight=6
  randy=Rnd(0,50)
  If randy=0 Then ambTR#=Rnd(100,255) : ambTG#=Rnd(0,100) : ambTB#=Rnd(0,100)
  If randy=1 Then lightTR#=Rnd(100,255) : lightTG#=Rnd(0,100) : lightTB#=Rnd(0,100)
 EndIf
 ;green
 If arenaLight=7
  randy=Rnd(0,50)
  If randy=0 Then ambTR#=Rnd(0,100) : ambTG#=Rnd(100,255) : ambTB#=Rnd(0,100)
  If randy=1 Then lightTR#=Rnd(0,100) : lightTG#=Rnd(100,255) : lightTB#=Rnd(0,100)
 EndIf
 ;blue
 If arenaLight=8
  randy=Rnd(0,50)
  If randy=0 Then ambTR#=Rnd(0,100) : ambTG#=Rnd(0,100) : ambTB#=Rnd(100,255)
  If randy=1 Then lightTR#=Rnd(0,100) : lightTG#=Rnd(0,100) : lightTB#=Rnd(100,255)
 EndIf
 ;pink
 If arenaLight=9
  randy=Rnd(0,50)
  If randy=0 Then ambTR#=255 : ambTG#=Rnd(0,255) : ambTB#=255
  If randy=1 Then lightTR#=255 : lightTG#=Rnd(0,255) : lightTB#=255
 EndIf
 ;gold
 If arenaLight=10
  randy=Rnd(0,50)
  If randy=0 Then ambTR#=255 : ambTG#=Rnd(150,255) : ambTB#=Rnd(0,150)
  If randy=1 Then lightTR#=255 : lightTG#=Rnd(150,255) : lightTB#=Rnd(0,100)
 EndIf
 ;dark light
 If arenaLight=11
  ambTR#=20 : ambTG#=20 : ambTB#=30
  randy=Rnd(0,50)
  If randy=1 Then lightTR#=Rnd(0,255) : lightTG#=Rnd(0,255) : lightTB#=Rnd(0,255) 
 EndIf
 ;flashing lights
 If arenaLight=>2
  randy=Rnd(0,50)
  If randy=0
   PositionEntity light(1),Rnd(-100,100),100,Rnd(-100,100)
   PositionEntity lightPivot,Rnd(-100,100),20,Rnd(-100,100)
   PointEntity light(1),lightPivot
  EndIf
 EndIf
 ;apply ambience
 changer#=2.0
 If matchState=1 Then changer#=5
 If screen=0 Or screen=12 Then changer#=PercentOf#(10.0,gotim/2) 
 If gotim<0 Then changer#=0
 If ambR#<ambTR# Then ambR#=ambR#+changer#
 If ambR#>ambTR# Then ambR#=ambR#-changer#
 If ambG#<ambTG# Then ambG#=ambG#+changer#
 If ambG#>ambTG# Then ambG#=ambG#-changer#
 If ambB#<ambTB# Then ambB#=ambB#+changer#
 If ambB#>ambTB# Then ambB#=ambB#-changer# 
 AmbientLight ambR#,ambG#,ambB#
 ;apply lighting
 If lightR#<lightTR# Then lightR#=lightR#+changer#
 If lightR#>lightTR# Then lightR#=lightR#-changer#
 If lightG#<lightTG# Then lightG#=lightG#+changer#
 If lightG#>lightTG# Then lightG#=lightG#-changer#
 If lightB#<lightTB# Then lightB#=lightB#+changer#
 If lightB#>lightTB# Then lightB#=lightB#-changer# 
 For count=1 To no_lights
  LightColor light(count),lightR#,lightG#,lightB#
  ;EntityColor FindChild(world,"Bulb"+Dig$(count,10)),lightR#,lightG#,lightB#
 Next
End Function

;--------------------------------------------------------------------
;//////////////////// CAMERA OPERATIONS /////////////////////////////
;--------------------------------------------------------------------
Function Camera()
 ;store old position
 camOldX#=camX# : camOldY#=camY# : camOldZ#=camZ#
 ;identify mass centres
 If screen=50
  IdentifyCentres(1)
  camSpread#=0
 EndIf
 ;SELECTION PROCESS
 ;clock camera change
 If camType<>camOldType Then camAccel=0
 camOldType=camType
 ;display timer
 camTim=camTim-1
 If game=1 Then camTim=camTim-1
 If camTim<0 Then camTim=0 
 ;browse presets
 For count=1 To 10
  If KeyDown(count+1) And camShortCut(count)>0 And keytim=0
   PlaySound sCamera : keytim=15 : camTim=200 : camOldType=-1
   If camType=camShortCut(count)
    camOption=camOption+1
   Else
    camType=camShortCut(count)
   EndIf
  EndIf
 Next
 ;trigger manual
 ;If KeyDown(21) And CamConflict(21)=0 Then camType=0 : camTim=100
 ;If KeyDown(23) And CamConflict(23)=0 Then camType=0 : camTim=100
 ;For count=35 To 38
  ;If KeyDown(count) And CamConflict(count)=0 Then camType=0 : camTim=100
 ;Next
 If screen=50 And optWindow=0
  camMouseX#=MouseXSpeed()/2
  camMouseY#=-(MouseYSpeed()/2)
  If (camMouseX#<>0 Or camMouseY#<>0) And matchState=>1 Then camType=0 : camTim=100
  If optWindow=0 Then MoveMouse rX#(400),rY#(300)
 EndIf
 ;ESTABLISH FOCUS
 ;get appropriate target
 If screen=50 And (matchState<>2 Or promoTim=0)
  camFoc=0
  If matchLocation>0 Or screenAgenda=10
   If matchState=0 And matchPlayer>0 And matchMulti=0 Then camFoc=matchPlayer : camType=12
  EndIf
  If matchState=1 And matchEnter>0
   camFoc=matchEnter
   If matchPlayer>0 And pTeam(matchPlayer)=pTeam(matchEnter) Then camFoc=matchPlayer
  EndIf
  If matchState=3 And matchPlayer>0 And matchMulti=0
   ;If GetDistance#(RealX#(matchPlayer),RealZ#(matchPlayer),centreX#,centreZ#)>100 Then camFoc=matchPlayer
  EndIf
  If matchState=4 And matchWinner>0 Then camFoc=matchWinner
  If matchState=4 And matchTim>500
   If matchPlayer>0 And matchMulti=0 Then camFoc=matchPlayer
   If camType=12 Then camType=2 : camOption=2 : camA#=CleanAngle#(pA#(camFoc)+180)
  EndIf
 EndIf
 ;temporary override
 If matchPause=0 Then camTempTim=camTempTim-1 
 If camTempTim>5 And camTempFoc>0 Then camFoc=camTempFoc
 If camTempTim=>1 And camTempTim=<5
  camFoc=camOldFoc
  If camType=11 And camOldType=11 Then camOldType=-1
 EndIf
 If camTempTim=<0 Then camTempTim=0 : camTempFoc=0
 ;focus on character
 If camFoc>0
  camPivTX#=RealX#(camFoc) : camPivTZ#=RealZ#(camFoc) 
  camPivTY#=EyeLevel#(camFoc)
 EndIf
 ;contain all
 If camFoc=0
  camPivTX#=centreX# : camPivTZ#=centreZ#
  camPivTY#=centreY#
  If screen=54
   camPivTX#=GetCentre#(RealX#(1),RealX#(2))
   camPivTZ#=GetCentre#(RealZ#(1),RealZ#(2))
   camPivTY#=pY#(1)+24
  EndIf
 EndIf
 ;camera orientation
 If screen=50
  If camFoc>0 And (matchState=4 Or camType=12)
   camTA#=CleanAngle#(pA#(camFoc))
  Else 
   camTA#=WatchAngle#()
  EndIf
  turner#=0.1
  If (camType=>1 And camType=<3) Or camType=12
   If camFoc>0 And camTA#=CleanAngle#(pA#(camFoc)) Then turner#=turner#*4
  EndIf
  If camType=4 Then turner#=turner#/2
  If SatisfiedAngle(camA#,camTA#,turner#)=0 Then camA#=camA#+ReachAngle#(camA#,camTA#,turner#) Else camA#=camTA#
  camA#=CleanAngle#(camA#)
 EndIf
 ;GET TARGET POSITION
 ;manual control
 If camType=0
  camOption=1
  ;If KeyDown(36) And CamConflict(36)=0 Then MoveEntity cam,-1,0,0
  ;If KeyDown(38) And CamConflict(38)=0 Then MoveEntity cam,1,0,0
  ;If KeyDown(35) And CamConflict(35)=0 Then MoveEntity cam,0,-1,0
  ;If KeyDown(21) And CamConflict(21)=0 Then MoveEntity cam,0,1,0
  ;If KeyDown(23) And CamConflict(23)=0 Then MoveEntity cam,0,0,1
  ;If KeyDown(37) And CamConflict(37)=0 Then MoveEntity cam,0,0,-1
  If MouseDown(1) Or MouseDown(2)
   If camMouseY#>0 And ReachedCord(camX#,camZ#,camPivTX#,camPivTZ#,10) And camY#=>camPivTY#-20 And camY#=<camPivTY#+5
    MoveEntity cam,camMouseX#,0,0
   Else
    MoveEntity cam,camMouseX#,0,camMouseY#
   EndIf
  Else
   MoveEntity cam,camMouseX#,camMouseY#/2,0
  EndIf
  camX#=EntityX(cam) : camTX#=camX#
  camY#=EntityY(cam) : camTY#=camY#
  camZ#=EntityZ(cam) : camTZ#=camZ#
 EndIf
 ;low follow
 If camType=1
  If camOption>3 Then camOption=1
  PositionEntity dummy,camPivTX#,camPivTY#,camPivTZ#
  RotateEntity dummy,0,camA#,0
  If camFoc>0
   If camOption=1 Then zoom#=60
   If camOption=2 Then zoom#=80
   If camOption=3 Then zoom#=100
   If pAnim(camFoc)=11 And SatisfiedAngle(RequestAngle#(camFoc),EntityYaw#(cam)+180,90) Then zoom#=zoom#+(zoom#/4)
   If pAnim(camFoc)=12 And SatisfiedAngle(RequestAngle#(camFoc),EntityYaw#(cam)+180,90) Then zoom#=zoom#+(zoom#/2)
  Else
   distance#=camSpread#
   If distance#<40 Then distance#=40
   If camOption=1 Then zoom#=distance#+(distance#/2)
   If camOption=2 Then zoom#=distance#*2 
   If camOption=3 Then zoom#=distance#*3 
  EndIf
  If zoom#>200 Then zoom#=200
  MoveEntity dummy,0,-(zoom#/10),-zoom#
  camTX#=EntityX(dummy) : camTY#=EntityY(dummy) : camTZ#=EntityZ(dummy) 
 EndIf 
 ;level follow
 If camType=2
  If camOption>3 Then camOption=1
  PositionEntity dummy,camPivTX#,camPivTY#,camPivTZ#
  RotateEntity dummy,0,camA#,0
  If camFoc>0
   If camOption=1 Then zoom#=60
   If camOption=2 Then zoom#=80
   If camOption=3 Then zoom#=100
   If pAnim(camFoc)=11 And SatisfiedAngle(RequestAngle#(camFoc),EntityYaw#(cam)+180,90) Then zoom#=zoom#+(zoom#/4)
   If pAnim(camFoc)=12 And SatisfiedAngle(RequestAngle#(camFoc),EntityYaw#(cam)+180,90) Then zoom#=zoom#+(zoom#/2)
  Else
   distance#=camSpread#
   If distance#<40 Then distance#=40
   If camOption=1 Then zoom#=distance#+(distance#/2)
   If camOption=2 Then zoom#=distance#*2 
   If camOption=3 Then zoom#=distance#*3 
  EndIf
  If zoom#>200 Then zoom#=200
  MoveEntity dummy,0,zoom#/4,-zoom#
  camTX#=EntityX(dummy) : camTY#=EntityY(dummy) : camTZ#=EntityZ(dummy) 
 EndIf 
 ;high follow
 If camType=3
  If camOption>3 Then camOption=1
  PositionEntity dummy,camPivTX#,camPivTY#,camPivTZ#
  RotateEntity dummy,0,camA#,0
  If camFoc>0
   If camOption=1 Then zoom#=60
   If camOption=2 Then zoom#=80
   If camOption=3 Then zoom#=100
   If pAnim(camFoc)=11 And SatisfiedAngle(RequestAngle#(camFoc),EntityYaw#(cam)+180,90) Then zoom#=zoom#+(zoom#/4)
   If pAnim(camFoc)=12 And SatisfiedAngle(RequestAngle#(camFoc),EntityYaw#(cam)+180,90) Then zoom#=zoom#+(zoom#/2)
  Else
   distance#=camSpread#
   If distance#<40 Then distance#=40
   If camOption=1 Then zoom#=distance#+(distance#/2)
   If camOption=2 Then zoom#=distance#*2 
   If camOption=3 Then zoom#=distance#*3 
  EndIf
  If zoom#>200 Then zoom#=200 
  MoveEntity dummy,0,zoom#/2,-zoom#
  camTX#=EntityX(dummy) : camTY#=EntityY(dummy) : camTZ#=EntityZ(dummy) 
 EndIf 
 ;bird's eye
 If camType=4
  If camOption>3 Then camOption=1
  PositionEntity dummy,camPivTX#,camPivTY#,camPivTZ#
  RotateEntity dummy,0,camA#,0
  distance#=camSpread#
  If distance#<30 Or camFoc>0 Then distance#=30
  If camOption=1 Then zoom#=distance#*2
  If camOption=2 Then zoom#=distance#*3 
  If camOption=3 Then zoom#=distance#*4 
  If camOption=2 And zoom#<120 Then zoom#=120
  If camOption=3 And zoom#<150 Then zoom#=150
  MoveEntity dummy,0,zoom#,-(zoom#/10)
  camTX#=EntityX(dummy) : camTY#=EntityY(dummy) : camTZ#=EntityZ(dummy) 
 EndIf 
 ;static distant 
 If camType=5
  If camOption>9 Then camOption=1
  If camOption=1 Then camTX#=0 : camTY#=110 : camTZ#=200 
  If camOption=2 Then camTX#=175 : camTY#=110 : camTZ#=175
  If camOption=3 Then camTX#=200 : camTY#=110 : camTZ#=0 
  If camOption=4 Then camTX#=175 : camTY#=110 : camTZ#=-175 
  If camOption=5 Then camTX#=0 : camTY#=110 : camTZ#=-200 
  If camOption=6 Then camTX#=-175 : camTY#=110 : camTZ#=-175 
  If camOption=7 Then camTX#=-200 : camTY#=110 : camTZ#=0 
  If camOption=8 Then camTX#=-175 : camTY#=110 : camTZ#=175
  If camOption=9 Then camTX#=0 : camTY#=140 : camTZ#=0 
 EndIf
 ;fan perspective
 If camType=6
  If camOption>6 Then camOption=1
  If camOption=1 Then camTX#=80 : camTY#=45 : camTZ#=200 
  If camOption=2 Then camTX#=225 : camTY#=45 : camTZ#=0 
  If camOption=3 Then camTX#=80 : camTY#=45 : camTZ#=-200 
  If camOption=4 Then camTX#=-80 : camTY#=45 : camTZ#=-200 
  If camOption=5 Then camTX#=-225 : camTY#=45 : camTZ#=0 
  If camOption=6 Then camTX#=-80 : camTY#=45 : camTZ#=200 
 EndIf 
 ;corner perspective
 If camType=7
  If camOption>4 Then camOption=1
  If camOption=1 Then camTX#=75 : camTY#=56 : camTZ#=75 
  If camOption=2 Then camTX#=75 : camTY#=56 : camTZ#=-75 
  If camOption=3 Then camTX#=-75 : camTY#=56 : camTZ#=-75
  If camOption=4 Then camTX#=-75 : camTY#=56 : camTZ#=75 
 EndIf 
 ;cut-scene head shot
 If camType=10 And camFoc>0
  camOption=1
  nearestSide=NearestSideTo(camFoc,camX#,camZ#)
  PositionEntity dummy,camPivTX#,camPivTY#,camPivTZ#
  RotateEntity dummy,0,pA#(camFoc),0
  swing#=0 : elevation#=5 : zoom#=35
  If screen=50
   elevation#=5 : zoom#=40
   If nearestSide=1 Then swing#=-15 Else swing#=15
  EndIf
  If screen=52 Or screen=53 Or screen=56 Or screen=57
   If camFoc=2 Or camFoc=4 Then swing#=-15 Else swing#=15
  EndIf
  If screen=54
   If camFoc=2 Then swing#=15 Else swing#=-15
  EndIf
  If screen=55
   If camFoc=2 Then swing#=-15 Else swing#=15
  EndIf
  MoveEntity dummy,swing#,elevation#,zoom#
  camTX#=EntityX(dummy) : camTY#=EntityY(dummy) : camTZ#=EntityZ(dummy)  
  If screen=55 And camFoc=<4 Then camTY#=camTY#-5
  camA#=CleanAngle#(EntityYaw(cam,1))
 EndIf
 ;spontaneous
 If camType=11
  camOption=1
  If matchState=1 Then randy=Rnd(0,500) Else randy=Rnd(0,750)
  If randy=<2 Or gotim=0 Or camType<>camOldType Or camFoc<>camOldFoc Or matchState<>matchOldState
   camTX#=camPivTX# : camTY#=camPivTY# : camTZ#=camPivTZ# 
   camAccel=0
  EndIf
  range#=camSpread#
  If range#<50 Then range#=50
  If camFoc>0 Then range#=30
  If range#>150 Then range#=150
  range#=PercentOf#(range#,75)
  While camTX#>camPivTX#-range# And camTX#<camPivTX#+range# And camTZ#>camPivTZ#-range# And camTZ#<camPivTZ#+range# And camTY#<camPivTY#+60
   camTX#=camPivTX#+Rnd(-(range#-50),range#+50)
   randy=Rnd(0,1) 
   If randy=0 Then camTY#=camPivTY#+Rnd(-20,50) Else camTY#=camPivTY#+Rnd(-20,100) 
   camTZ#=camPivTZ#+Rnd(-(range#-50),range#+50)
   randy=Rnd(0,2)
   If randy=<1 Or (matchState=1 And matchEnter=camFoc And pOutTim(matchEnter)<2)
    camTX#=camPivTX#+Rnd(-(range#-5),range#+5)
    camTZ#=camPivTZ#+Rnd(-(range#-5),range#+5)
    camTY#=camPivTY#+Rnd(-10,30)
   EndIf
  Wend
  If matchState=1 And (camTZ#>200 Or camTZ#<-200)
   If camTX#<-30 Then camTX#=-30 
   If camTX#>30 Then camTX#=30 
  EndIf
 EndIf
 ;in-game head shot
 If camType=12 And camFoc>0
  camOption=1
  PositionEntity dummy,camPivTX#,camPivTY#,camPivTZ#
  RotateEntity dummy,0,camA#,0
  zoom#=50
  If pAnim(camFoc)=11 And SatisfiedAngle(RequestAngle#(camFoc),EntityYaw#(cam)+180,90) Then zoom#=zoom#+(zoom#/4)
  If pAnim(camFoc)=12 And SatisfiedAngle(RequestAngle#(camFoc),EntityYaw#(cam)+180,90) Then zoom#=zoom#+(zoom#/2)
  MoveEntity dummy,0,5,zoom#
  camTX#=EntityX(dummy) : camTY#=EntityY(dummy) : camTZ#=EntityZ(dummy) 
 EndIf
 ;intro override
 If screen=50 And matchState=0 And matchLocation=0 And screenAgenda<>10
  camTX#=0 : camTY#=50 : camTZ#=0
  If camRewardFoc>0 
   If gotim<0 Then camX#=-115 : camY#=25 : camZ#=100
   camTX#=0 : camTY#=100 : camTZ#=100
  EndIf
 EndIf
 ;entrance protection
 If screen=50 And matchState=1 And matchLocation=0 And matchEnter>0 And pOutTim(matchEnter)=0
  If camTZ#>380 Then camTZ#=380
  If camTZ#<-380 Then camTZ#=-380
 EndIf
 ;training override
 If screen=54
  If trainStage=1 Then camTX#=pX#(1)-20 : camTY#=30 : camTZ#=pZ#(1)-65
  If trainStage=2
   camTX#=pX#(1)-55 : camTY#=30 : camTZ#=pZ#(1)-55
   If trainCourse=5 Then camTX#=pX#(1)-20 : camTZ#=pZ#(1)-65
  EndIf
 EndIf
 ;ADJUST TARGETS
 heightAssist=0
 If screen=50 And camType>0 And (pOutTim(camFoc)>1 Or matchState=>2)
  ;dip under tunnel
  If InsideTunnel(camX#,camZ#) Or InsideTunnel(camTX#,camTZ#) Or InsideTunnel(camPivTX#,camPivTZ#) Or (camZ#<385 And camPivTZ#>610) Or (camZ#>615 And camPivTZ#<392) Or (camZ#>-385 And camPivTZ#<-610) Or (camZ#<-615 And camPivTZ#>-392)
   ;If camPivTY#>30 Then camPivTY#=30
   If camTY#=>40 Then camTY#=40 : heightAssist=1
  EndIf
  ;follow behind curtain
  If camPivTZ#>400 And camTZ#<400 Then camTZ#=400
  If camPivTZ#<390 And camTZ#>390 Then camTZ#=390
  If camPivTZ#<-400 And camTZ#>-400 Then camTZ#=-400
  If camPivTZ#>-390 And camTZ#<-390 Then camTZ#=-390
  ;follow into tunnel/backstage
  If camPivTZ#>615 And camTZ#<615 Then camTZ#=615
  If camPivTZ#<605 And camTZ#>605 Then camTZ#=605
  If camPivTZ#<-615 And camTZ#>-615 Then camTZ#=-615
  If camPivTZ#>-605 And camTZ#<-605 Then camTZ#=-605
  ;get through tunnel
  If (camZ#>610 And camTZ#<610) Or (camZ#<610 And camTZ#>610) Or (camZ#>-610 And camTZ#<-610) Or (camZ#<-610 And camTZ#>-610)
   If camTX#<-20 Then camTX#=-20
   If camTX#>20 Then camTX#=20
  EndIf 
  ;get through curtain
  If (camZ#>394 And camTZ#<394) Or (camZ#<394 And camTZ#>394) Or (camZ#>-394 And camTZ#<-394) Or (camZ#<-394 And camTZ#>-394)
   If camTX#<-5 Then camTX#=-5
   If camTX#>5 Then camTX#=5
  EndIf
  ;get into aisle ways
  If (camPivTZ#>230 Or camPivTZ#<-230) And camZ#=>blockZ2#(21) And camZ#=<blockZ1#(15)
   If camTX#<blockX2#(15)+10 Then camTX#=blockX2#(15)+10
   If camTX#>blockX1#(17)-10 Then camTX#=blockX1#(17)-10
  EndIf
  ;account for balcony divers
  For v=1 To no_plays
   If CamViable(v)
    If (pPlatform(v)=33 Or pPlatform(v)=34) And camPivZ#<400 And camTZ#>300 Then camTZ#=300
    If (pPlatform(v)=35 Or pPlatform(v)=36) And camPivZ#>400 And camTZ#<-300 Then camTZ#=-300
   EndIf
  Next
 EndIf
 ;intro arena scan
 If screen=50 And matchState=0 And matchEntrances>0
  PositionEntity dummy,camX#,camY#,camZ#
  If pCurtain(matchEnter)=1 Then RotateEntity dummy,0,matchTim*2,0
  If pCurtain(matchEnter)=2 Then RotateEntity dummy,0,180+(matchTim*2),0
  MoveEntity dummy,0,0,200
  camPivTX#=EntityX(dummy) : camPivTY#=EntityY(dummy) : camPivTZ#=EntityZ(dummy)
 EndIf
 ;PURSUE TARGETS
 ;camera tracking 
 If gotim>0 
  speeder=60
  If screen=50 
   speeder=120-gotim
   If speeder<60 Then speeder=60
  EndIf
  If screen=50 And camFoc>0 And camType<>11
   If pAnim(camFoc)=11 Then speeder=speeder-(speeder/4)
   If pAnim(camFoc)=12 Then speeder=speeder/2
  EndIf
  If screen=50 And camType=11 Then speeder=speeder*2
  GetSmoothSpeeds(camX#,camTX#,camY#,camTY#,camZ#,camTZ#,speeder)
  If camType<>camOldType Then camAccel=0
  camAccel=camAccel+2
  If camAccel>100 Then camAccel=100
  If (camType=11 And pOutTim(cyc)=<2) Or matchState=1 Or screen<>50 Then camAccel=100
  speedX#=PercentOf#(speedX#,camAccel)
  speedY#=PercentOf#(speedY#,camAccel)
  speedZ#=PercentOf#(speedZ#,camAccel)
  If heightAssist=1 Then speedY#=speedY#*4
  If camX#<camTX# Then camX#=camX#+speedX#
  If camX#>camTX# Then camX#=camX#-speedX#
  If camY#<camTY# Then camY#=camY#+(speedY#/2)
  If camY#>camTY# Then camY#=camY#-(speedY#/2)
  If camZ#<camTZ# Then camZ#=camZ#+speedZ#
  If camZ#>camTZ# Then camZ#=camZ#-speedZ#
 EndIf
 ;enforce blocks
 camBlocked=0
 If screen=50 And gotim>0
  For v=0 To 100
   If blockType(v)=2
    If camOldX#=>blockX1#(v) And camOldX#=<blockX2#(v) And camOldZ#=>blockZ1#(v) And camOldZ#=<blockZ2#(v)
     trapped=1
    Else
     If camX#=<blockX2#(v) And camX#=>blockX1#(v) And camZ#=<blockZ2#(v) And camZ#=>blockZ1#(v) And camY#=>blockY1#(v) And camY#=<blockY2#(v)
      If camOldX#=>blockX1#(v) And camOldX#=<blockX2#(v) Then camZ#=camOldZ#
      If camOldZ#=>blockZ1#(v) And camOldZ#=<blockZ2#(v) Then camX#=camOldX#
      camBlocked=1
     EndIf
    EndIf
   EndIf
  Next
 EndIf
 ;height limits
 If camY#<wGround#+2 Then camY#=wGround#+2
 If InsideRing(camX#,camZ#,-10) And camY#<wStage#+1 Then camY#=wStage#+1
 If camY#>FindCeiling#(camX#,camZ#) Then camY#=FindCeiling#(camX#,camZ#)
 ;pivot tracking 
 speeder=30
 If screen=50 And camFoc>0
  If pAnim(camFoc)=11 Then speeder=speeder-(speeder/3)
  If pAnim(camFoc)=12 Then speeder=speeder/3
 EndIf
 If screen>50 Then speeder=30
 If screen=55 Then speeder=45
 GetSmoothSpeeds(camPivX#,camPivTX#,camPivY#,camPivTY#,camPivZ#,camPivTZ#,speeder)
 camPivAccel=camPivAccel+1
 If camPivAccel>100 Then camPivAccel=100
 If matchState=0 Or (matchState=1 And matchEnter>0) Or camTempTim>0 Or screen<>50 Then camPivAccel=100 
 speedX#=PercentOf#(speedX#,camPivAccel)
 speedY#=PercentOf#(speedY#,camPivAccel)
 speedZ#=PercentOf#(speedZ#,camPivAccel)
 If camPivX#<camPivTX# Then camPivX#=camPivX#+speedX#
 If camPivX#>camPivTX# Then camPivX#=camPivX#-speedX#
 If camPivY#<camPivTY# Then camPivY#=camPivY#+speedY#
 If camPivY#>camPivTY# Then camPivY#=camPivY#-speedY#
 If camPivZ#<camPivTZ# Then camPivZ#=camPivZ#+speedZ#
 If camPivZ#>camPivTZ# Then camPivZ#=camPivZ#-speedZ#
 ;first person override
 If camType=8 And matchState>0
  If matchPlayer>0 Then camFoc=matchPlayer Else camFoc=1
  limb=FindChild(p(camFoc),"Head")
  PositionEntity dummy,EntityX(limb,1),EntityY(limb,1)+1,EntityZ(limb,1)
  RotateEntity dummy,EntityPitch(limb,1),EntityYaw(limb,1),EntityRoll(limb,1)
  MoveEntity dummy,0,0,1
  camX#=EntityX(dummy) : camY#=EntityY(dummy) : camZ#=EntityZ(dummy)
  If pFoc(camFoc)>0
   limb=FindChild(p(pFoc(camFoc)),"Head")
   camPivX#=EntityX(limb,1) : camPivY#=EntityY(limb,1) : camPivZ#=EntityZ(limb,1)
  Else 
   MoveEntity dummy,0,0,30 
   camPivX#=EntityX(dummy) : camPivY#=EntityY(dummy) : camPivZ#=EntityZ(dummy)
  EndIf
 EndIf
 ;opponent's view override
 If camType=9 And matchState>0 
  If matchPlayer>0 And pFoc(matchPlayer)>0 Then camFoc=pFoc(matchPlayer) Else camFoc=2
  limb=FindChild(p(camFoc),"Head")
  PositionEntity dummy,EntityX(limb,1),EntityY(limb,1)+1,EntityZ(limb,1)
  RotateEntity dummy,EntityPitch(limb,1),EntityYaw(limb,1),EntityRoll(limb,1)
  MoveEntity dummy,0,0,1
  camX#=EntityX(dummy) : camY#=EntityY(dummy) : camZ#=EntityZ(dummy)
  If pFoc(camFoc)>0
   limb=FindChild(p(pFoc(camFoc)),"Head")
   camPivX#=EntityX(limb,1) : camPivY#=EntityY(limb,1) : camPivZ#=EntityZ(limb,1)
  Else
   MoveEntity dummy,0,0,30 
   camPivX#=EntityX(dummy) : camPivY#=EntityY(dummy) : camPivZ#=EntityZ(dummy)
  EndIf
 EndIf 
 ;reward override
 If matchState=0 And camRewardFoc>0
  camPivX#=weapX#(camRewardFoc) : camPivY#=weapY#(camRewardFoc) : camPivZ#=weapZ#(camRewardFoc)
  PointEntity light(1),weap(camRewardFoc)
 EndIf
 ;position & point 
 PositionEntity camPivot,camPivX#,camPivY#,camPivZ#
 PointEntity cam,camPivot
 PositionEntity cam,camX#,camY#,camZ# 
 ;SCENERY ISSUES
 If screen=50
  ;lighting conflict
  If arenaType=>2
   girder=FindChild(world,"Girders")
   If EntityY(girder)>140 Then PositionEntity girder,EntityX(girder),EntityY(girder)-1,EntityZ(girder)
   If EntityY(girder)<camY#+15 ;And InsideRing(camX#,camZ#,0)
    PositionEntity girder,EntityX(girder),camY#+15,EntityZ(girder)
   EndIf
  EndIf
  ;ghost scenery
  For count=1 To 2
   EntityAlpha FindChild(world,"Curtain0"+count),0.9
  Next
  If matchState=>2
   If (camZ#<395 And camPivTZ#>400) Or (camZ#>395 And camPivTZ#<385) Then EntityAlpha FindChild(world,"Curtain01"),0.6
   If (camZ#<-395 And camPivTZ#>-385) Or (camZ#>-395 And camPivTZ#<-400) Then EntityAlpha FindChild(world,"Curtain02"),0.6
  EndIf
 EndIf
End Function

;GET CAMERA SETTINGS (from option)
Function GetCamera(option)
 ;low follows
 If option=1 Then camType=1 : camOption=1
 If option=2 Then camType=1 : camOption=2
 If option=3 Then camType=1 : camOption=3
 ;level follows
 If option=4 Then camType=2 : camOption=1
 If option=5 Then camType=2 : camOption=2
 If option=6 Then camType=2 : camOption=3
 ;raised follows
 If option=7 Then camType=3 : camOption=1
 If option=8 Then camType=3 : camOption=2
 If option=9 Then camType=3 : camOption=3
 ;bird's eye follows
 If option=10 Then camType=4 : camOption=1
 If option=11 Then camType=4 : camOption=2
 If option=12 Then camType=4 : camOption=3
 ;static perspectives
 If option=13 Then camType=5 : camOption=1
 If option=14 Then camType=5 : camOption=2
 If option=15 Then camType=5 : camOption=3
 If option=16 Then camType=5 : camOption=4
 If option=17 Then camType=5 : camOption=5
 If option=18 Then camType=5 : camOption=6
 If option=19 Then camType=5 : camOption=7
 If option=20 Then camType=5 : camOption=8
 If option=21 Then camType=5 : camOption=9 
 ;fan perspectives
 If option=22 Then camType=6 : camOption=1
 If option=23 Then camType=6 : camOption=2
 If option=24 Then camType=6 : camOption=3
 If option=25 Then camType=6 : camOption=4
 If option=26 Then camType=6 : camOption=5
 If option=27 Then camType=6 : camOption=6 
 ;corner perspectives
 If option=28 Then camType=7 : camOption=1
 If option=29 Then camType=7 : camOption=2
 If option=30 Then camType=7 : camOption=3
 If option=31 Then camType=7 : camOption=4
 ;misc
 If option=32 Then camType=8 : camOption=1
 If option=33 Then camType=9 : camOption=1
 If option=34 Then camType=11 : camOption=1
 If option=35 Then camType=12 : camOption=1
End Function

;CAMERA vs CONTROL CONFLICTS?
Function CamConflict(command)
 conflict=0
 If command=keyUpperLeft Or command=keyUpperRight Or command=keyLowerLeft Or command=keyLowerRight Then conflict=1
 If command=keySwitch Or command=keyTaunt Then conflict=1
 Return conflict
End Function

;INCLUDE PLAYER IN CAMERA ACTIVITY?
Function CamViable(cyc)
 viable=0
 If pOutTim(cyc)>0 And pHidden(cyc)=0
  If LegalMan(cyc) Or (pRole(cyc)=0 And pChaosTim(cyc)>0 And pEliminated(cyc)=0) Or pControl(cyc)>0 
   humanBlock=0
   If matchPlayer>0 And LegalMan(matchPlayer)
    range#=50
    If pFoc(matchPlayer)=cyc Or pFoc(cyc)=matchPlayer Then range#=range#*2
    If camBlocked>0 And range#>GetDistance#(camX#,camZ#,RealX#(matchPlayer),RealZ#(matchPlayer)) Then range#=GetDistance#(camX#,camZ#,RealX#(matchPlayer),RealZ#(matchPlayer)) 
    If pControl(cyc)=0 And InProximity(cyc,matchPlayer,range#)=0 Then humanBlock=1
   EndIf
   If humanBlock=0 Then viable=1
  EndIf
 EndIf
 Return viable
End Function

;GET SUITABLE HEIGHT FOR FOCUSING
Function EyeLevel#(cyc)
 ;default standing height
 height#=pY#(cyc)+(21+(charHeight(pChar(cyc))/3))
 ;head shot offset
 If camType=10 Or camType=12 Or camFoc>0
  height#=pY#(cyc)+(25+(charHeight(pChar(cyc))/3))
  If (screen=52 Or screen=53 Or screen=55 Or screen=56 Or screen=57) And pAnim(cyc)<10 Then height#=pY#(cyc)+23
  If screen=54 And camFoc=1 Then height#=pY#(cyc)+23
 EndIf
 ;projected animation
 If ProjectedAnim(cyc)=1 Then height#=EntityY(FindChild(p(cyc),"Hips"),1)+9
 Return height#
End Function

;CALCULATE PERFECT WATCHING ANGLE
Function WatchAngle#()
 ;identify farthest most players
 camCriteria=0
 cyc=camFoc : v=camFoc 
 If camFoc=0
  hi#=-9999
  For count=1 To no_plays
   If CamViable(count)
    camCriteria=camCriteria+1
    distance#=GetDistance#(RealX#(count),RealZ#(count),centreX#,centreZ#)
    If distance#>hi# Then cyc=count : v=count : hi#=distance#
   EndIf
  Next
  hi#=-9999
  For count=1 To no_plays
   If CamViable(count)
    distance#=GetDistance#(RealX#(count),RealZ#(count),RealX#(cyc),RealZ#(cyc))
    If distance#>hi# Then v=count : hi#=distance# : camSpread#=distance#
   EndIf
  Next
 EndIf
 ;clock criteria
 If cyc>0 And cyc=v Then camFoc=cyc : camCriteria=1
 If camCriteria<>camOldCriteria Then camAccel=0 : camPivAccel=0
 camOldCriteria=camCriteria
 ;assess perspectives
 If cyc>0 And v>0
  If camFoc>0
   view1#=CleanAngle#(pA#(cyc))
  Else
   PositionEntity dummy,RealX#(cyc),pY#(cyc),RealZ#(cyc)
   PointEntity dummy,pLimb(v,36)
   view1#=CleanAngle#(EntityYaw(dummy))
  EndIf
  angle1#=view1#-90
  PositionEntity dummy,GetCentre#(RealX#(cyc),RealX#(v)),0,GetCentre#(RealZ#(cyc),RealZ#(v))
  RotateEntity dummy,0,angle1#,0
  MoveEntity dummy,0,0,-100
  distance1#=GetDistance#(EntityX(dummy),EntityZ(dummy),camX#,camZ#) 
  angle2#=angle1#+180
  PositionEntity dummy,GetCentre#(RealX#(cyc),RealX#(v)),0,GetCentre#(RealZ#(cyc),RealZ#(v))
  RotateEntity dummy,0,angle2#,0
  MoveEntity dummy,0,0,-100
  distance2#=GetDistance#(EntityX(dummy),EntityZ(dummy),camX#,camZ#)
 EndIf
 ;return most suitable angle
 If distance1#<distance2# Then angle#=angle1# Else angle#=angle2#
 angle#=CleanAngle#(angle#)
 Return angle#
End Function

;NEAREST SIDE TO (STANDING PLAYER)
Function NearestSideTo(cyc,x#,z#) ;1=left, 2=right 
 ;find distance to left
 ProjectDummy(cyc,-20,0,0)
 leftX#=EntityX(dummy) : leftZ#=EntityZ(dummy)
 ;find distance to right
 ProjectDummy(cyc,20,0,0)
 rightX#=EntityX(dummy) : rightZ#=EntityZ(dummy)
 ;return closest
 If GetDistance#(x#,z#,leftX#,leftZ#)<GetDistance#(x#,z#,rightX#,rightZ#) Then value=1 Else value=2
 Return value
End Function

;FIND GROUND
Function FindGround#(x#,z#)
 ground#=wGround#
 If InsideRing(x#,z#,0) Then ground#=wStage#
 Return ground#
End Function

;FIND CEILING
Function FindCeiling#(x#,z#)
 ceiling#=300
 If arenaType=1 Then ceiling#=140
 If z#>610 Then ceiling#=95
 If z#<-610 Then ceiling#=80
 If InsideTunnel(x#,z#)
  If camOldY#=<50 Or z#>590 Or z#<-590 Or arenaType=1 Then ceiling#=45
 EndIf
 Return ceiling#
End Function

;CO-ORDINATES INSIDE TUNNEL?
Function InsideTunnel(x#,z#)
 value=0
 If x#>-615 And x#<615
  If z#>392 And z#<610 Then value=1
  If z#>-610 And z#<-392 Then value=1
 EndIf
 Return value
End Function