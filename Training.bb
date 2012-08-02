;//////////////////////////////////////////////////////////////////////////////
;------------------------- WRESTLING MPIRE 2008: TRAINING ---------------------
;//////////////////////////////////////////////////////////////////////////////

;----------------------------------------------------------------------------
;//////////////////////// 54. TRAINING PROCESS //////////////////////////////
;----------------------------------------------------------------------------
Function Training()
;load setting
Loader("Please Wait","Preparing To Train")
;ChannelVolume chTheme,0.5
ResetTextures()
world=LoadAnimMesh("World/Gym/Gym.3ds")
ExtractAnimSeq world,15,100,0 ;1
PrepareScenery()
For count=1 To 2
 EntityAlpha FindChild(world,"Net0"+count),0.5
Next
HideEntity FindChild(world,"Ball")
;camera
cam=CreateCamera()
CameraViewport cam,0,0,GraphicsWidth(),GraphicsHeight()
CameraZoom cam,1.5 
camX#=450 : camY#=30 : camZ#=195 
PositionEntity cam,450,30,195
RotateEntity cam,0,315,0 
;atmosphere
AmbientLight 220,210,200
no_lights=1
light(1)=CreateLight(1) 
LightColor light(1),250,230,210
PositionEntity light(1),0,100,0
lightPivot=CreatePivot()
PositionEntity lightPivot,Rnd(-100,100),100,Rnd(-110,110) 
PointEntity light(1),lightPivot
;CHARACTERS
no_plays=1 : pChar(1)=editChar
;If editChar<>gamChar Then no_plays=2 : pChar(2)=gamChar
pX#(1)=485 : pY#(1)=6 : pZ#(1)=265 : pA#(1)=180 : pAnim(1)=0
pX#(2)=pX#(1)-40 : pY#(2)=6 : pZ#(2)=250 : pA#(2)=245 : pAnim(2)=0
For cyc=1 To no_plays
 ;load model
 pCostume(cyc)=2
 p(cyc)=LoadAnimMesh("Characters/Models/Model0"+GetModel(pChar(cyc))+".3ds")
 StripModel(cyc)
 ApplyCostume(cyc)
 If cyc=2 And TitleHolder(pChar(cyc),0)>0 Then WearBelt(cyc,TitleHolder(pChar(cyc),0)) 
 pEyes(cyc)=2 : pOldEyes(cyc)=-1
 ;load sequences
 pSeq(cyc,601)=LoadAnimSeq(p(cyc),"Characters/Sequences/Standard01.3ds")
 pSeq(cyc,604)=LoadAnimSeq(p(cyc),"Characters/Sequences/Standard04.3ds")
 pSeq(cyc,605)=LoadAnimSeq(p(cyc),"Characters/Sequences/Standard05.3ds")
 pSeq(cyc,606)=LoadAnimSeq(p(cyc),"Characters/Sequences/Standard06.3ds") 
 If InjuryStatus(pChar(cyc))>0
  pSeq(cyc,0)=ExtractAnimSeq(p(cyc),70,110,pSeq(cyc,604)) ;injured
 Else
  pSeq(cyc,0)=ExtractAnimSeq(p(cyc),450,510,pSeq(cyc,605)) ;resting
 EndIf
 pSeq(cyc,1)=ExtractAnimSeq(p(cyc),590,650,pSeq(cyc,605)) ;power exercise
 pSeq(cyc,2)=ExtractAnimSeq(p(cyc),2240,2385,pSeq(cyc,601)) ;skill exercise
 pSeq(cyc,3)=ExtractAnimSeq(p(cyc),2110,2230,pSeq(cyc,601)) ;agility exercise
 pSeq(cyc,4)=ExtractAnimSeq(p(cyc),1175,1235,pSeq(cyc,605)) ;stamina exercise
 pSeq(cyc,5)=ExtractAnimSeq(p(cyc),70,210,pSeq(cyc,606)) ;toughness exercise
 pSeq(cyc,10)=ExtractAnimSeq(p(cyc),2020,2080,pSeq(cyc,604)) ;onlooker
 If cyc=1 Then Animate p(cyc),1,Rnd#(0.25,0.5),pSeq(cyc,0),0
 If cyc=2 Then Animate p(cyc),1,Rnd#(0.25,0.5),pSeq(cyc,10),0
 ;orientation
 PositionEntity p(cyc),pX#(cyc),pY#(cyc),pZ#(cyc)
 RotateEntity p(cyc),0,pA#(cyc),0  
 scaler#=0.055+(GetPercent#(charHeight(pChar(cyc)),24)/10000)
 ScaleEntity p(cyc),scaler#,scaler#,scaler# 
 ;shadows
 LoadShadows(cyc)
Next
;restore textures
RestoreTextures()
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
oldCourse=-1 : oldLevel=-1
foc=9 : oldfoc=foc
go=0 : gotim=0 : keytim=20
While go=0

 Cls
 frames=WaitTimer(timer)
 For framer=1 To frames
	
	;timers
	keytim=keytim-1
	If keytim<0 Then keytim=0 
	
    ;CONFIGURATION
    gotim=gotim+1
	If gotim>20 And keytim=0
	 ;highlight option
	 If KeyDown(200) Or JoyYDir()=-1 Then foc=foc-1 : keytim=5
	 If KeyDown(208) Or JoyYDir()=1 Then foc=foc+1 : keytim=5
	 If foc>9 Then foc=0
	 If foc<0 Then foc=9
	 ;adjust intensity
	 If foc=7 
	  If KeyDown(203) Or JoyXDir()=-1 Or MouseDown(2) 
	   PlaySound sMenuBrowse : keytim=5
	   charTrainLevel(editChar)=charTrainLevel(editChar)-1
	  EndIf
	  If KeyDown(205) Or JoyXDir()=1 Or MouseDown(1)
	   PlaySound sMenuBrowse : keytim=5
	   charTrainLevel(editChar)=charTrainLevel(editChar)+1
	  EndIf
	 EndIf
	 ;activations
	 If KeyDown(28) Or ButtonPressed() Or MouseDown(1)
	  ;trigger course
	  If foc=>0 And foc=<6 
	   If InjuryStatus(editChar)>0 
	    If keytim=0 Then PlaySound sVoid : keytim=10
	   Else
	    PlaySound sStep(1) : keytim=10
	    If foc>0 Then ProduceSound(0,sPain(Rnd(1,8)),GetVoice(1),0)
	    charTrainCourse(editChar)=foc
	   EndIf
	  EndIf
	  ;proceed
	  If foc=8 And InjuryStatus(editChar)>0 And keytim=0 Then PlaySound sVoid : keytim=10
	  If foc=8 And InjuryStatus(editChar)=0 And keytim=0 Then go=2
	  If foc=9 And keytim=0 Then go=1
	 EndIf
	 ;leave
	 If KeyDown(1) Then go=-1  
	EndIf 
	;check limits
	If charTrainLevel(editChar)<1 Then charTrainLevel(editChar)=1
	If charTrainLevel(editChar)>3 Then charTrainLevel(editChar)=3 
	If InjuryStatus(editChar)>0 Then charTrainCourse(editChar)=0
	If charTrainCourse(editChar)=0 Then charTrainLevel(editChar)=0  
	
	;ANIMATIONS
	cyc=1
	;trigger new  	
	If oldCourse<>charTrainCourse(editChar) Or oldLevel<>charTrainLevel(editChar)
	 If charTrainCourse(editChar)=0 Then pAnimSpeed#(cyc)=0.5
	 If charTrainCourse(editChar)=1 Then pAnimSpeed#(cyc)=1.0
	 If charTrainCourse(editChar)=2 Then pAnimSpeed#(cyc)=2.25  
	 If charTrainCourse(editChar)=3 Then pAnimSpeed#(cyc)=1.75
	 If charTrainCourse(editChar)=4 Then pAnimSpeed#(cyc)=2.0 
	 If charTrainCourse(editChar)=5 Then pAnimSpeed#(cyc)=2.5
	 If charTrainCourse(editChar)=6 Then pAnimSpeed#(cyc)=2.0  
	 If charTrainLevel(editChar)=1 Then pAnimSpeed#(cyc)=pAnimSpeed#(cyc)-(pAnimSpeed#(cyc)/3)
	 If charTrainLevel(editChar)=3 Then pAnimSpeed#(cyc)=pAnimSpeed#(cyc)+(pAnimSpeed#(cyc)/3)  
	 If charTrainCourse(editChar)=<5 Then Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,charTrainCourse(editChar)),5
	 If charTrainCourse(editChar)=6 Then pAnim(cyc)=1 : pAnimTim(cyc)=0
    EndIf 
    ;everything sequence
    If charTrainCourse(editChar)=6
     If pAnim(cyc)=1 
      If pAnimTim(cyc)=0 Then Animate p(cyc),1,pAnimSpeed#(cyc)/2,pSeq(cyc,pAnim(cyc)),5
      If pAnimTim(cyc)>120/(pAnimSpeed#(cyc)/2) Then pAnim(cyc)=pAnim(cyc)+1 : pAnimTim(cyc)=0
     EndIf
     If pAnim(cyc)=2 
      If pAnimTim(cyc)=0 Then Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,pAnim(cyc)),5
      If pAnimTim(cyc)>145/pAnimSpeed#(cyc) Then pAnim(cyc)=pAnim(cyc)+1 : pAnimTim(cyc)=0
     EndIf
     If pAnim(cyc)=3 
      If pAnimTim(cyc)=0 Then Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,pAnim(cyc)),5
      If pAnimTim(cyc)>120/pAnimSpeed#(cyc) Then pAnim(cyc)=pAnim(cyc)+1 : pAnimTim(cyc)=0
     EndIf
     If pAnim(cyc)=4 
      If pAnimTim(cyc)=0 Then Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,pAnim(cyc)),5
      If pAnimTim(cyc)>120/pAnimSpeed#(cyc) Then pAnim(cyc)=pAnim(cyc)+1 : pAnimTim(cyc)=0
     EndIf
     If pAnim(cyc)=5 
      If pAnimTim(cyc)=0 Then Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,pAnim(cyc)),5
      If pAnimTim(cyc)>140/pAnimSpeed#(cyc) Then pAnim(cyc)=1 : pAnimTim(cyc)=-1
     EndIf
    EndIf
    pAnimTim(cyc)=pAnimTim(cyc)+1
    ;hide/show props
    If charTrainCourse(editChar)=1 Or (charTrainCourse(editChar)=6 And pAnim(cyc)=1)
	 ShowEntity FindChild(p(cyc),"Barbell")
	Else
	 HideEntity FindChild(p(cyc),"Barbell")
	EndIf  
    ;sound effects
    trainSoundTim=trainSoundTim-1 
	If trainSoundTim<0 Then trainSoundTim=0
	If trainSoundTim=0
	 If charTrainCourse(editChar)=2
	  If (Int(AnimTime(p(cyc)))=>29 And Int(AnimTime(p(cyc)))=<31) Or (Int(AnimTime(p(cyc)))=>94 And Int(AnimTime(p(cyc)))=<96)
	   ProduceSound(0,sSwing,22050,0.25)
	   trainSoundTim=5
	  EndIf
	 EndIf
	 If charTrainCourse(editChar)=3
	  If (Int(AnimTime(p(cyc)))=>29 And Int(AnimTime(p(cyc)))=<31) Or (Int(AnimTime(p(cyc)))=>89 And Int(AnimTime(p(cyc)))=<91)
	   ProduceSound(0,sStep(Rnd(3,4)),22050,0.5)
	   trainSoundTim=5
	  EndIf
	 EndIf
     If charTrainCourse(editChar)=4
	  If (Int(AnimTime(p(cyc)))=>14 And Int(AnimTime(p(cyc)))=<16) Or (Int(AnimTime(p(cyc)))=>44 And Int(AnimTime(p(cyc)))=<46)
	   ProduceSound(0,sStep(Rnd(3,4)),22050,Rnd(0.25,0.75))
	   trainSoundTim=5
	  EndIf
	 EndIf
	 If charTrainCourse(editChar)=5
	  If (Int(AnimTime(p(cyc)))=>19 And Int(AnimTime(p(cyc)))=<21) Or (Int(AnimTime(p(cyc)))=>89 And Int(AnimTime(p(cyc)))=<91)
	   ProduceSound(0,sImpact(Rnd(1,4)),22050,0.3)
	   trainSoundTim=5 : trainGruntTim#=999
	  EndIf
	 EndIf
	EndIf
	trainGruntTim#=trainGruntTim#+pAnimSpeed#(1) 
	If trainGruntTim#>150 And charTrainCourse(editChar)>0
	 ProduceSound(0,sPain(Rnd(1,8)),GetVoice(cyc),Rnd(0.0,0.5))
	 trainGruntTim#=0
    EndIf
    ;record changes
    oldCourse=charTrainCourse(editChar) 
	oldLevel=charTrainLevel(editChar)
 	
	;CHARACTER MANAGEMENT
	For cyc=1 To no_plays
	 ;facial expressions
	 If cyc=1 And charTrainCourse(editChar)>0 Then pSpeaking(cyc)=1 Else pSpeaking(cyc)=0
	 FacialExpressions(cyc)
	 ManageEyes(cyc) 
	 ;shadows
	 For limb=1 To 50
      If pShadow(cyc,limb)>0
       RotateEntity pShadow(cyc,limb),90,EntityYaw(pLimb(cyc,limb),1),0
       PositionEntity pShadow(cyc,limb),EntityX(pLimb(cyc,limb),1),pY#(cyc)+0.4,EntityZ(pLimb(cyc,limb),1)
      EndIf
     Next
	Next
	;music
	ManageMusic(-1)
	
 UpdateWorld
 Next 
 ;look at trainee
 If no_plays=2
  PointHead(2,FindChild(p(1),"Head")) : LookAtPerson(2,1)
  If charEyeShape(pChar(2))=112 Then LookAtPerson(2,2)
 EndIf 

 RenderWorld 1

 ;DISPLAY
 DrawImage gLogo(2),rX#(400),rY#(65)
 DrawProfile(editChar,-1,-1,0)
 ;options
 x=590 : y=175
 DrawOption(0,rX#(x),rY#(y),"REST","")
 DrawOption(1,rX#(x),rY#(y+40),"STRENGTH","")
 DrawOption(2,rX#(x),rY#(y+75),"SKILL","")
 DrawOption(3,rX#(x),rY#(y+110),"AGILITY","")
 DrawOption(4,rX#(x),rY#(y+145),"STAMINA","") 
 DrawOption(5,rX#(x),rY#(y+180),"TOUGHNESS","")
 DrawOption(6,rX#(x),rY#(y+215),"EVERYTHING","") 
 DrawOption(7,rX#(x),rY#(y+260),"Intensity",textTrainLevel$(charTrainLevel(editChar))) 
 DrawOption(8,rX#(x),rY#(y+305),"SPARRING","")
 DrawOption(9,rX#(x),rY#(560),"<<< SAVE & EXIT <<<","")  
 ;portrait icon
 showX=rX#(x)-90 : showY=rY#(y)
 If charTrainCourse(editChar)>0 Then showY=rY#((y+5)+(charTrainCourse(editChar)*35))
 reveal=(64-PortraitHead#(editChar))+10
 DrawImageRect charPhoto(editChar),showX,(showY+10)+(64-reveal),0,0,76,reveal
 ;intensity preview
 If charTrainLevel(editChar)>0
  Color 0,0,0 : Rect x-49,rY#(y+260)+8,100,6,1
  Color 225,0,0 : Rect x-50,rY#(y+260)+7,100,6,1
  Color 125,0,0 : Rect x-50,rY#(y+260)+7,100,6,0
  meter=100
  If charTrainLevel(editChar)=1 Then meter=75
  If charTrainLevel(editChar)=2 Then meter=50
  If charTrainLevel(editChar)=3 Then meter=25
  Color 0,225,0 : Rect x-50,rY#(y+260)+7,meter,6,1
  Color 0,125,0 : Rect x-50,rY#(y+260)+7,meter,6,0
 EndIf
 ;cursor
 If foc<>oldfoc Then PlaySound sMenuSelect : oldfoc=foc 
 DrawImage gCursor,MouseX(),MouseY()
 ;mask shaky start
 If gotim<0 Then Loader("Please Wait","Preparing To Train")

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;restore sound 
If go=>1 Then PlaySound sMenuGo Else PlaySound sMenuBack 
ChannelVolume chTheme,1.0
FreeTimer timer
;free entities
FreeEntity world
FreeEntity cam 
FreeEntity light(1)
FreeEntity lightPivot
;remove characters
For cyc=1 To no_plays
 FreeEntity p(cyc)
 For limb=1 To 50
  If pShadow(cyc,limb)>0
   FreeEntity pShadow(cyc,limb)
  EndIf
 Next
Next
;proceed
If go=<1 Then screen=12 : screenAgenda=6
;sparring session
If go=2
 ResetCharacters()
 GetMatchRules(2) : AddGimmick(0)
 matchTimeLim=optLength
 pChar(1)=editChar : pControl(1)=3
 pChar(2)=AssignOpponent(editChar,0,0)
 If no_refs>0 
  If gamChar<>pChar(1) And gamChar<>pChar(2) Then pChar(3)=gamChar
  If pChar(3)=0 Then pChar(3)=AssignReferee()
 EndIf
 arenaPreset=1 : arenaAttendance=0
 If optFog>0 Then arenaAtmos=3 Else arenaAtmos=0
 arenaRopes=9 : arenaPads=Rnd(1,3)
 arenaApron=arenaPads : arenaCanvas=Rnd(1,4)
 arenaMatting=Rnd(0,3)
 screen=50 : screenAgenda=10
EndIf
End Function

;--------------------------------------------------------------
;////////////////// RELATED FUNCTIONS /////////////////////////
;--------------------------------------------------------------

;ADJUST WEIGHT ACCORDING TO STAT CHANGES
Function FindWeightChanges(char)
 If charStrength(char)>charOldStrength(char) Then charWeightChange(char)=charWeightChange(char)+Rnd(1,3)
 If charSkill(char)>charOldSkill(char) Then charWeightChange(char)=charWeightChange(char)-1
 If charAgility(char)>charOldAgility(char) Then charWeightChange(char)=charWeightChange(char)-Rnd(1,3)
 If charStamina(char)>charOldStamina(char) Then charWeightChange(char)=charWeightChange(char)-1
 If charToughness(char)>charOldToughness(char) Then charWeightChange(char)=charWeightChange(char)+1
End Function