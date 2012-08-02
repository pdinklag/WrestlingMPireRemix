;//////////////////////////////////////////////////////////////////////////////
;------------------------ WRESTLING MPIRE 2008: CREDITS -----------------------
;//////////////////////////////////////////////////////////////////////////////

;//////////////////////////////////////////////////////////////////////////////
;--------------------------------- INTRO --------------------------------------
;//////////////////////////////////////////////////////////////////////////////
Function Intro()
;prepare images
size=8
For count=0 To 16
 font(count)=LoadFont("Comic Book Normal.ttf",size,0,0,0)
 size=size+2
Next 
gBackground=LoadImage("Graphics/Background.png")
MaskImage gBackground,255,0,255
ResizeImage gBackground,GraphicsWidth(),GraphicsHeight()
gLogo(1)=LoadImage("Graphics/Logo01.png")
MaskImage gLogo(1),255,0,255
gMDickie=LoadImage("Graphics/MDickie.png")
MaskImage gMDickie,255,0,255
For count=1 To 8
 gMenu(count)=LoadImage("Graphics/Menus/Menu0"+count+".png")
 MaskImage gMenu(count),255,0,255
Next
;prepare textures
For count=1 To 5
 tCrowd(count)=LoadTexture("World/Sprites/Crowd0"+count+".png",4)
Next
For count=1 To 2
 tPlant(count)=LoadTexture("World/Sprites/Plant0"+count+".png",4)
Next
For count=1 To 9
 tRope(count)=LoadTexture("World/Ropes/Rope"+Dig$(count,10)+".JPG")
Next 
tScreen=LoadTexture("World/Videos/Flag01.JPG")
tVideo(1)=LoadTexture("World/Videos/Flag03.JPG")
tVideo(2)=LoadTexture("World/Videos/Flag02.JPG")
tGirder(1)=LoadTexture("World/Cage/Girder01.png",4)
;load scene
fed=0 : arenaPreset=23
arenaLight=1 : arenaAtmos=0 : arenaAttendance=50
arenaApron=7 : arenaCanvas=5
arenaPads=1 : arenaRopes=14 : arenaMatting=2
LoadAtmos()
camX#=0 : camY#=25 : camZ#=760
camA#=135 : camAccel=0 : angleAccel=100
ambTR#=0 : ambTG#=0 : ambTB#=0
lightTR#=0 : lightTG#=0 : lightTB#=0
LoadArena()
LoadRopes()
;apply posters
tPoster(1)=LoadTexture("World/Banners/Poster01.JPG")
tPoster(2)=LoadTexture("World/Banners/Poster02.JPG")
tPoster(3)=LoadTexture("World/Banners/Poster04.JPG")
tPoster(4)=LoadTexture("World/Banners/Poster05.JPG")
tPoster(5)=LoadTexture("World/Banners/Poster07.JPG")
tPoster(6)=LoadTexture("World/Banners/Poster13.JPG")
EntityTexture FindChild(world,"Poster03"),tPoster(1)
EntityTexture FindChild(world,"Poster04"),tPoster(2)
EntityTexture FindChild(world,"Poster12"),tPoster(3)
EntityTexture FindChild(world,"Poster10"),tPoster(4)
EntityTexture FindChild(world,"Poster11"),tPoster(5)
EntityTexture FindChild(world,"Poster06"),tPoster(6)
EntityTexture FindChild(world,"Screen01"),tVideo(1),0,1
EntityTexture FindChild(world,"Screen04"),tVideo(2),0,1
;load actor
cyc=1 : pChar(cyc)=0 : pCostume(cyc)=1
charBaggy(pChar(cyc),pCostume(cyc))=3
p(cyc)=LoadAnimMesh("Characters/Models/Model02.3ds")
pSeq(cyc,604)=LoadAnimSeq(p(cyc),"Characters/Sequences/Standard04.3ds")
pSeq(cyc,1)=ExtractAnimSeq(p(cyc),2530,2590,pSeq(cyc,604)) ;standing still
pSeq(cyc,2)=ExtractAnimSeq(p(cyc),2600,2660,pSeq(cyc,604)) ;gesture
Animate p(cyc),1,0.1,pSeq(cyc,1),0
PositionEntity p(cyc),40,1.6,615
RotateEntity p(cyc),0,45,0
scaler#=0.06
ScaleEntity p(cyc),scaler#,scaler#,scaler#
;dress actor
GetLimbs(cyc)
LoadWeaponData()
For v=1 To weapList
 HideEntity FindChild(p(cyc),weapFile$(v))
Next
For count=1 To 2
 HideEntity FindChild(p(cyc),"Belt0"+count)
Next
HideEntity FindChild(p(cyc),"Barbell")
RemoveHair(cyc)
RemoveHeadwear(cyc)
RemoveEyewear(cyc)
tHat(0)=LoadTexture("Characters/Headwear/Hat09.JPG")
ShowEntity FindChild(p(cyc),"Hat")
EntityTexture FindChild(p(cyc),"Hat"),tHat(0),0,1
PositionEntity FindChild(p(cyc),"Hat"),2.43,41.62,5.14 
RotateEntity FindChild(p(cyc),"Hat"),20,-18.9,-7.4
tHair(0)=LoadTexture("Characters/Hair/Hair03.JPG")
EntityAlpha FindChild(p(cyc),"H_Bald"),1
EntityTexture FindChild(p(cyc),"H_Bald"),tHair(0);,0,1
tFace(0)=LoadTexture("Characters/Faces/Face06.JPG")
tEars=LoadTexture("Characters/Expressions/Ears.JPG")
tEyes(0)=LoadTexture("Characters/Expressions/Eyes02.JPG")
EntityTexture pLimb(cyc,1),tEyes(0),0,2
For limb=1 To 4
 EntityTexture pLimb(cyc,limb),tFace(0),0,1
 If limb=>2 And limb=<3 Then EntityTexture pLimb(cyc,limb),tEars,0,3
Next
tEyeballs(0)=LoadTexture("Characters/Eyes/Eyes02.JPG")
For limb=45 To 46
 EntityTexture pLimb(cyc,limb),tEyeballs(0),0,1
Next
tBody(0)=LoadTexture("Characters/Bodies/Body52.JPG")
EntityTexture pLimb(cyc,5),tBody(0),0,1
EntityAlpha FindChild(p(cyc),"Body"),0
tArm(0)=LoadTexture("Characters/Arms/Arm16 (5).JPG")
tHands(0)=LoadTexture("Characters/Hands/Hands01 (1).JPG")
For limb=6 To 31
 If (limb=>8 And limb=<18) Or (limb=>21 And limb=<31)
  EntityTexture pLimb(cyc,limb),tHands(0),0,1
 Else 
  EntityTexture pLimb(cyc,limb),tArm(0),0,1
 EndIf
Next
tLegs(0)=LoadTexture("Characters/Legs/Legs107.JPG")
For limb=36 To 44
 EntityTexture pLimb(cyc,limb),tLegs(0),0,1
Next
EntityAlpha FindChild(p(cyc),"L_Leg"),0
EntityAlpha FindChild(p(cyc),"R_Leg"),0
EntityAlpha FindChild(p(cyc),"L_Short"),0
EntityAlpha FindChild(p(cyc),"R_Short"),0
;crowd effect
LoopSound sCrowd(1)
chCrowd=PlaySound(sCrowd(1))
ChannelVolume chCrowd,0.125
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
musicTrigger=0 : animTrigger=0
camTrigger=0 : crowdTrigger=0
leaveTim=0 : quitTim=0
go=0 : gotim=-25
While go=0

 Cls
 frames=WaitTimer(timer)
 For framer=1 To frames
	
	;timers
	keytim=keytim-1
	If keytim<1 Then keytim=0
	
	;PORTAL
    gotim=gotim+1
	If gotim>50 And keytim=0
	 If KeyDown(1) Or KeyDown(28) Or ButtonPressed() Or MouseDown(1) Then quitTim=1 : PlaySound sMenuGo : keytim=10
	EndIf  
	If quitTim>0 Then quitTim=quitTim+1
	If quitTim>2 Then screen=1 : go=1
	;trigger theme
	If gotim=48 And ChannelPlaying(chTheme)=0 Then PlayTheme(-1) : musicTrigger=gotim
    ;trigger gesture
    If gotim=270 Or KeyDown(57) Then Animate p(1),3,1.25,pSeq(1,2),5 : animTrigger=gotim
	;trigger travel
	If gotim>275 And camAccel=0 Then camAccel=1 : camTrigger=gotim 
	If camZ#<395 
	 If crowdTrigger=0 Then ProduceSound(0,sCrowd(7),0,0.5) : crowdTrigger=1
	 ChannelVolume chCrowd,0.4
	EndIf
	If camZ#=<300 And leaveTim=0 Then camZ#=300 : ProduceSound(0,sCrowd(2),0,0.5) : leaveTim=1
	If leaveTim>0 Then leaveTim=leaveTim+1
	If leaveTim>2 Then go=1
	
	;CAMERA
	If go=0 And leaveTim=0
	 ;position
	 If camAccel>0
	  camAccel=camAccel+1
      If camAccel>100 Then camAccel=100
      camZ#=camZ#-PercentOf#(10,camAccel)
     EndIf
     If camZ#<300 Then camZ#=300
	 PositionEntity cam,camX#,camY#,camZ# 
	 ;orientation
	 If camA#>135 And camA#<240 Then angleAccel=angleAccel-1
	 If angleAccel<50 Then angleAccel=50 
	 If gotim>0 Then camA#=camA#-PercentOf#(1.0,angleAccel)
	 If camA#<0 Then camA#=359
	 If camA#>135 And camA#<180 Then camA#=180 
	 RotateEntity cam,0,camA#,0
     ;atmosphere
     ManageAtmos()
    EndIf
	
 If go=0 And leaveTim=0 Then UpdateWorld
 Next
 RenderWorld 1

 ;DISPLAY
 ;SetFont font(4)
 ;Outline("Gotim: "+gotim,100,25,0,0,0,255,255,255)
 ;Outline("animTrigger: "+animTrigger,100,50,0,0,0,255,255,255)
 If gotim<0 Then Color 0,0,0 : Rect 0,0,GraphicsWidth(),GraphicsHeight()
 If quitTim>0 Then DrawImage gBackground,rX#(400),rY#(300)

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;proceed
Loader("Please Wait","Loading Game")
If ChannelPlaying(chTheme)=0 Then PlayTheme(-1)
chCurrentTheme=0 : chThemeChange=-1
ChannelVolume chCrowd,0.125
If quitTim>0 Then StopChannel chCrowd
FreeTimer timer
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
;remove actor
FreeEntity p(1)
FreeTexture tHat(0)
FreeTexture tHair(0)
FreeTexture tFace(0)
FreeTexture tEars
FreeTexture tEyes(0)
FreeTexture tEyeballs(0)
FreeTexture tBody(0)
FreeTexture tArm(0)
FreeTexture tHands(0)
FreeTexture tLegs(0)
;remove arena textures
For count=1 To 5
 FreeTexture tCrowd(count)
Next
For count=1 To 2
 FreeTexture tPlant(count)
Next
For count=1 To 6
 FreeTexture tPoster(count)
Next
For count=1 To 9
 FreeTexture tRope(count)
Next 
FreeTexture tScreen
FreeTexture tVideo(1)
FreeTexture tVideo(2)
FreeTexture tGirder(1)
End Function

;//////////////////////////////////////////////////////////////////////////////
;------------------------------ 1. MAIN MENU ----------------------------------
;//////////////////////////////////////////////////////////////////////////////
Function MainMenu()
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
foc=1 : oldfoc=foc
go=0 : gotim=0 : keytim=20
While go=0

 Cls
 frames=WaitTimer(timer)
 For framer=1 To frames
	
	;timers
	keytim=keytim-1
	If keytim<1 Then keytim=0
	
	;PORTAL
    gotim=gotim+1
	If gotim>20 And keytim=0
	 ;leave
	 If KeyDown(1) Then go=-1
	 ;proceed
	 If KeyDown(28) Or ButtonPressed() Or MouseDown(1)
	  If foc=8 Then go=-1 Else go=1
	 EndIf
	 ;online mode
	 If KeyDown(24) And optWindow>0 Then go=2
	EndIf
	;music
	ManageMusic(-1)
	
	;CONFIGURATION 
	If gotim>20 And keytim=0
	 ;highlight option
	 If KeyDown(200) Or JoyYDir()=-1 Then foc=foc-1 : keytim=6
	 If KeyDown(208) Or JoyYDir()=1 Then foc=foc+1 : keytim=6  
	EndIf  
	;limits
	If foc<1 Then foc=8
	If foc>8 Then foc=1     
	
 UpdateWorld
 Next
 RenderWorld 1

 ;DISPLAY
 DrawImage gBackground,rX#(400),rY#(300)
 DrawImage gLogo(1),rX#(400),rY#(135)
 ;DrawImage gMDickie,rX#(400),rY#(530) 
 ;options
 y=290
 DrawOption(1,rX#(400),rY#(y),"CAREER","") : y=y+35
 DrawOption(2,rX#(400),rY#(y),"TOURNAMENT","") : y=y+35
 DrawOption(3,rX#(400),rY#(y),"EXHIBITION","") : y=y+40
 DrawOption(4,rX#(400),rY#(y),"EDITOR","") : y=y+35
 DrawOption(5,rX#(400),rY#(y),"PREFERENCES","") : y=y+35
 DrawOption(6,rX#(400),rY#(y),"DISPLAY","") : y=y+40
 DrawOption(7,rX#(400),rY#(y),"CREDITS","") : y=y+40
 DrawOption(8,rX#(400),rY#(560),"<<< EXIT <<<","")  
 ;version ID
 SetFont font(2) 
 Color 100,100,100
 Text rX#(400)+175,rY#(135)+60,"v2.5",0,1
 ;cursor
 If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect  
 DrawImage gCursor,MouseX(),MouseY()

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;proceed
FreeTimer timer
If go>0 Then PlaySound sMenuGo Else PlaySound sMenuBack 
If go=1
 If foc=1 Then game=1 : screen=10 : screenAgenda=0 ;career
 If foc=2 Then game=0 : screen=10 : screenAgenda=11 ;tournament
 If foc=3 Then game=0 : screen=10 : screenAgenda=0 ;exhibition
 If foc=4 Then game=0 : screen=10 : screenAgenda=1 ;editor
 If foc=5 Then screen=2 ;preferences
 If foc=6 Then screen=3 ;display
 If foc=7 Then screen=7 ;credits
EndIf
;exit
If go=-1 Then screen=6
;online test
If go=2 Then StartOnlineGame()
End Function

;//////////////////////////////////////////////////////////////////////////////
;------------------------------- 7. CREDITS -----------------------------------
;//////////////////////////////////////////////////////////////////////////////
Function Credits()
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
PlaySound sCrowd(2)
scroll=0
go=0 : gotim=0 : keytim=10
While go=0
 
 Cls
 frames=WaitTimer(timer)
 For framer=1 To frames
	
	;timers
	keytim=keytim-1
	If keytim<1 Then keytim=0
	
	;PORTAL
    gotim=gotim+1
	If gotim>20 
	 If KeyDown(1) Or KeyDown(28) Or JoyDown(1) Then go=1
	 If MouseDown(1) And (scroll<-1560 Or scroll>0) Then go=1 
	EndIf	
	;scroller
	If gotim>50
	 scroll=scroll-1
	 If KeyDown(200) Or JoyY()=-1 Or MouseDown(1) Then scroll=scroll-5
	 If KeyDown(208) Or JoyY()=1 Or MouseDown(2) Then scroll=scroll+5
	 If scroll<-1580 Then scroll=600
	 If scroll>600 Then scroll=-1580
	EndIf
	;music
	ManageMusic(-1)
	
 UpdateWorld
 Next
 RenderWorld 1

 ;DISPLAY
 DrawImage gBackground,rX#(400),rY#(300)
 ;list
 y=135
 SetFont font(9)
 DrawImage gLogo(1),rX#(400),rY#(y+scroll) : y=y+180
 Outline("Concept",rX#(400),rY#(y+scroll),0,0,0,255,250,50) : y=y+38
 SetFont fontNews(10)
 Outline("©",rX#(400)-184,rY#(y+scroll)-1,0,0,0,250,250,250)
 SetFont font(9)
 Outline("MDickie 2003-2011",rX#(400),rY#(y+scroll),0,0,0,250,250,250) : y=y+65
 Outline("Game Design",rX#(400),rY#(y+scroll),0,0,0,250,235,45) : y=y+38
 Outline("Mat Dickie",rX#(400),rY#(y+scroll),0,0,0,250,250,250) : y=y+65
 Outline("Programming",rX#(400),rY#(y+scroll),0,0,0,245,220,40) : y=y+38
 Outline("Mat Dickie",rX#(400),rY#(y+scroll),0,0,0,250,250,250) : y=y+65
 Outline("2D Graphics",rX#(400),rY#(y+scroll),0,0,0,240,200,35) : y=y+38
 Outline("Mat Dickie",rX#(400),rY#(y+scroll),0,0,0,250,250,250) : y=y+65
 Outline("3D Modelling",rX#(400),rY#(y+scroll),0,0,0,235,180,30) : y=y+38
 Outline("Mat Dickie",rX#(400),rY#(y+scroll),0,0,0,250,250,250) : y=y+65
 Outline("Animation",rX#(400),rY#(y+scroll),0,0,0,230,155,25) : y=y+38
 Outline("Mat Dickie",rX#(400),rY#(y+scroll),0,0,0,250,250,250) : y=y+65
 Outline("Texturing",rX#(400),rY#(y+scroll),0,0,0,225,130,20) : y=y+38
 Outline("Mat Dickie",rX#(400),rY#(y+scroll),0,0,0,250,250,250) : y=y+25
 SetFont font(1)
 Outline("(Steel chair courtesy of Simon Sayers)",rX#(400),rY#(y+scroll),0,0,0,200,200,200) : y=y+55
 SetFont font(9)
 Outline("Sound FX",rX#(400),rY#(y+scroll),0,0,0,220,105,15) : y=y+38
 Outline("Mat Dickie",rX#(400),rY#(y+scroll),0,0,0,250,250,250) : y=y+65
 Outline("Music",rX#(400),rY#(y+scroll),0,0,0,215,80,10) : y=y+38
 Outline("Mat Dickie",rX#(400),rY#(y+scroll),0,0,0,250,250,250) : y=y+25
 SetFont font(1)
 Outline("(Themes 14-17 courtesy of Big Wilk)",rX#(400),rY#(y+scroll),0,0,0,200,200,200) : y=y+20
 Outline("(Theme 41 courtesy of Edmund Buggs)",rX#(400),rY#(y+scroll),0,0,0,200,200,200) : y=y+55
 SetFont font(9)
 Outline("Scripts",rX#(400),rY#(y+scroll),0,0,0,210,50,5) : y=y+38
 Outline("Mat Dickie",rX#(400),rY#(y+scroll),0,0,0,250,250,250) : y=y+65
 Outline("Publishing",rX#(400),rY#(y+scroll),0,0,0,205,10,0) : y=y+38
 Outline("MDickie.com",rX#(400),rY#(y+scroll),0,0,0,250,250,250) : y=y+110
 DrawImage gMDickie,rX#(400),rY#(y+scroll)
 ;diagnostic
 ;SetFont font(1)
 ;Outline(scroll,rX#(50),rY#(50),0,0,0,250,250,250)
 ;cursor
 DrawImage gCursor,MouseX()+10,MouseY()+9

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
If go=-1 Then PlaySound sMenuBack Else PlaySound sMenuGo
FreeTimer timer
screen=1
End Function

;//////////////////////////////////////////////////////////////////////////////
;-------------------------------- 6. OUTRO ------------------------------------
;//////////////////////////////////////////////////////////////////////////////
Function Outro()
;reset theme
;ChannelVolume chTheme,0.5
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
go=0 : gotim=0 : keytim=20
While go=0

 Cls
 frames=WaitTimer(timer)
 For framer=1 To frames
	
	;PORTAL
    gotim=gotim+1
	If gotim>50
	 If KeyDown(1) Or KeyDown(28) Or ButtonPressed() Or MouseDown(1) Or gotim>500 Then go=1
	EndIf  
	;music
	ManageMusic(-1)  
	
 UpdateWorld
 Next
 RenderWorld 1

 ;DISPLAY
 DrawImage gBackground,rX#(400),rY#(300)
 DrawImage gOutro,rX#(400),rY#(360)
 DrawImage gLogo(1),rX#(400),rY#(135)
 DrawImage gMDickie,rX#(400),rY#(520) 
 ;message
 y=337
 SetFont font(12)
 Outline("Follow all the latest developments",rX#(400),rY#(360)-45,0,0,0,255,255,255)
 Outline("and connect with other fans at",rX#(400),rY#(360),0,0,0,255,255,255)
 Outline("www.facebook.com/MDickieFans",rX#(400),rY#(360)+45,0,0,0,255,255,255)
 ;Outline("Keep your career up to date",rX#(400),rY#(360)-45,0,0,0,255,255,255)
 ;Outline("with all the latest downloads",rX#(400),rY#(360),0,0,0,255,255,255)
 ;Outline("from www.MDickie.com!",rX#(400),rY#(360)+45,0,0,0,255,255,255)

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
FreeTimer timer
SaveOptions()
SaveUniverse()
SaveProgress(slot)
SaveWorld(slot)
SaveChars(slot)
StopChannel chTheme
If KeyDown(56) And KeyDown(45)
 LoopHole()
EndIf
screen=0
End Function