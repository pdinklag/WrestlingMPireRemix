;//////////////////////////////////////////////////////////////////////////////
;----------------------- WRESTLING MPIRE 2008: MENU SCREENS -------------------
;//////////////////////////////////////////////////////////////////////////////

;//////////////////////////////////////////////////////////////////////////////
;------------------------------- 2. OPTIONS -----------------------------------
;//////////////////////////////////////////////////////////////////////////////
Function Options()
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
foc=12 : oldfoc=foc
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
	  If foc=>11 And foc=<12 Then go=1
	  If foc=13 Then go=1
	 EndIf
	EndIf
	;music
	ManageMusic(-1)
	
	;CONFIGURATION 
	If gotim>20 And keytim=0
	 ;highlight option
	 If KeyDown(200) Or JoyYDir()=-1 Then foc=foc-1 : keytim=5
	 If KeyDown(208) Or JoyYDir()=1 Then foc=foc+1 : keytim=5 
	 ;search left
	 If KeyDown(203) Or JoyXDir()=-1 Or MouseDown(2)
	  If foc=1 Then optLevel=optLevel-1 : PlaySound sMenuBrowse : keytim=5
	  If foc=2 Then optRatingsLevel=optRatingsLevel-1 : PlaySound sMenuBrowse : keytim=5 
	  If foc=3 Then optLength=optLength-1 : PlaySound sMenuBrowse : keytim=5
	  If foc=4 Then optSpeed=optSpeed-1 : PlaySound sMenuBrowse : keytim=5
	  If foc=5 Then optEntrances=optEntrances-1 : PlaySound sMenuBrowse : keytim=5
	  If foc=6 Then optReferees=optReferees-1 : PlaySound sMenuBrowse : keytim=5
	  If foc=7 Then optManagers=optManagers-1 : PlaySound sMenuBrowse : keytim=5
	  If foc=8 Then optIntruders=optIntruders-1 : PlaySound sMenuBrowse : keytim=5
	  If foc=9 Then optHideElim=optHideElim-1 : PlaySound sMenuBrowse : keytim=5 
	  If foc=10 Then optTagControl=optTagControl-1 : PlaySound sMenuBrowse : keytim=5  
	 EndIf
	 ;search right
	 If KeyDown(205) Or JoyXDir()=1 Or MouseDown(1)
	  If foc=1 Then optLevel=optLevel+1 : PlaySound sMenuBrowse : keytim=5
	  If foc=2 Then optRatingsLevel=optRatingsLevel+1 : PlaySound sMenuBrowse : keytim=5 
	  If foc=3 Then optLength=optLength+1 : PlaySound sMenuBrowse : keytim=5
	  If foc=4 Then optSpeed=optSpeed+1 : PlaySound sMenuBrowse : keytim=5
	  If foc=5 Then optEntrances=optEntrances+1 : PlaySound sMenuBrowse : keytim=5
	  If foc=6 Then optReferees=optReferees+1 : PlaySound sMenuBrowse : keytim=5
	  If foc=7 Then optManagers=optManagers+1 : PlaySound sMenuBrowse : keytim=5
	  If foc=8 Then optIntruders=optIntruders+1 : PlaySound sMenuBrowse : keytim=5
	  If foc=9 Then optHideElim=optHideElim+1 : PlaySound sMenuBrowse : keytim=5 
	  If foc=10 Then optTagControl=optTagControl+1 : PlaySound sMenuBrowse : keytim=5  
	 EndIf 
	EndIf  
	;limits
	If foc<1 Then foc=13
	If foc>13 Then foc=1 
	If optLevel<0 Then optLevel=0
	If optLevel>6 Then optLevel=6
	If optRatingsLevel<1 Then optRatingsLevel=1
	If optRatingsLevel>4 Then optRatingsLevel=4 
	If optLength<1 Then optLength=1
	If optLength>3 Then optLength=3
	If optSpeed<1 Then optSpeed=1
	If optSpeed>4 Then optSpeed=4 
	If optEntrances<0 Then optEntrances=0
	If optEntrances>2 Then optEntrances=2
	If optReferees<0 Then optReferees=0
	If optReferees>3 Then optReferees=3 
	If optManagers<0 Then optManagers=0
	If optManagers>3 Then optManagers=3 
	If optIntruders<0 Then optIntruders=0
	If optIntruders>3 Then optIntruders=3 
	If optHideElim<0 Then optHideElim=0
	If optHideElim>2 Then optHideElim=2  
	If optTagControl<0 Then optTagControl=1
	If optTagControl>1 Then optTagControl=0    
	
 UpdateWorld
 Next
 RenderWorld 1

 ;DISPLAY
 DrawImage gBackground,rX#(400),rY#(300)
 DrawImage gLogo(2),rX#(400),rY#(60) 
 ;options
 y=140
 DrawOption(1,rX#(400),rY#(y),"CPU Level",textLevel$(optLevel)) : y=y+32
 DrawOption(2,rX#(400),rY#(y),"Ratings Level",textRatingsLevel$(optRatingsLevel)) : y=y+32 
 DrawOption(3,rX#(400),rY#(y),"Match Length",textLength$(optLength)) : y=y+32
 DrawOption(4,rX#(400),rY#(y),"Game Speed",textSpeed$(optSpeed)) : y=y+37
 DrawOption(5,rX#(400),rY#(y),"Entrances",textEntrances$(optEntrances)) : y=y+32
 DrawOption(6,rX#(400),rY#(y),"Referees",textReferees$(optReferees)) : y=y+32
 DrawOption(7,rX#(400),rY#(y),"Managers",textManagers$(optManagers)) : y=y+32 
 DrawOption(8,rX#(400),rY#(y),"Interferences",textIntruders$(optIntruders)) : y=y+32 
 DrawOption(9,rX#(400),rY#(y),"Eliminations",textEliminations$(optHideElim)) : y=y+32 
 DrawOption(10,rX#(400),rY#(y),"Tag Control",textTagControl$(optTagControl)) : y=y+39
 DrawOption(11,rX#(400),rY#(y),"REDEFINE KEYS","") : y=y+33
 DrawOption(12,rX#(400),rY#(y),"REDEFINE GAMEPAD","") : y=y+37   
 DrawOption(13,rX#(400),rY#(560),"<<< SAVE & EXIT <<<","")  
 ;cursor
 If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect  
 DrawImage gCursor,MouseX(),MouseY()

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
FreeTimer timer
If go=1 Then PlaySound sMenuGo Else PlaySound sMenuBack 
SaveOptions()
If go=1
 If foc=11 Then screen=4
 If foc=12 Then screen=5
 If foc=13 Then screen=1
EndIf
If go=-1 Then screen=1
End Function

;//////////////////////////////////////////////////////////////////////////////
;--------------------------- 3. DISPLAY OPTIONS -------------------------------
;//////////////////////////////////////////////////////////////////////////////
Function DisplayOptions()
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
oldMenuRes=optMenuRes
foc=13 : oldfoc=foc
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
	  If foc=13 Then go=1
	 EndIf
	EndIf
	;music
	ManageMusic(-1)
	
	;CONFIGURATION 
	oldRes=optGameRes
	If gotim>20 And keytim=0
	 ;highlight option
	 If KeyDown(200) Or JoyYDir()=-1 Then foc=foc-1 : keytim=5
	 If KeyDown(208) Or JoyYDir()=1 Then foc=foc+1 : keytim=5 
	 ;search left
	 If KeyDown(203) Or JoyXDir()=-1 Or MouseDown(2)
	  If foc=1 Then optGameRes=optGameRes-1 : PlaySound sMenuBrowse : keytim=5
	  If foc=2 Then optDefaultCam=optDefaultCam-1 : PlaySound sMenuBrowse : keytim=3
	  If foc=3 Then optFog=optFog-1 : PlaySound sMenuBrowse : keytim=5
	  If foc=4 Then optDetail=optDetail-1 : PlaySound sMenuBrowse : keytim=5
	  If foc=5 Then optCrowdAnim=optCrowdAnim-1 : PlaySound sMenuBrowse : keytim=5 
	  If foc=6 Then optFX=optFX-1 : PlaySound sMenuBrowse : keytim=5
	  If foc=7 Then optShadows=optShadows-1 : PlaySound sMenuBrowse : keytim=5
	  If foc=8 Then optGore=optGore-1 : PlaySound sMenuBrowse : keytim=5 
	  If foc=9 Then optMeters=optMeters-1 : PlaySound sMenuBrowse : keytim=5 
	  If foc=10 Then entDisplay=entDisplay-1 : PlaySound sMenuBrowse : keytim=5 
	  If foc=11 Then optGrid=optGrid-1 : PlaySound sMenuBrowse : keytim=5  
	  If foc=12 Then optMusicVolume=optMusicVolume-5 : PlaySound sMenuBrowse : keytim=5
	 EndIf
	 ;search right
	 If KeyDown(205) Or JoyXDir()=1 Or MouseDown(1)
	  If foc=1 Then optGameRes=optGameRes+1 : PlaySound sMenuBrowse : keytim=5
	  If foc=2 Then optDefaultCam=optDefaultCam+1 : PlaySound sMenuBrowse : keytim=3
	  If foc=3 Then optFog=optFog+1 : PlaySound sMenuBrowse : keytim=5
	  If foc=4 Then optDetail=optDetail+1 : PlaySound sMenuBrowse : keytim=5
	  If foc=5 Then optCrowdAnim=optCrowdAnim+1 : PlaySound sMenuBrowse : keytim=5 
	  If foc=6 Then optFX=optFX+1 : PlaySound sMenuBrowse : keytim=5
	  If foc=7 Then optShadows=optShadows+1 : PlaySound sMenuBrowse : keytim=5
	  If foc=8 Then optGore=optGore+1 : PlaySound sMenuBrowse : keytim=5 
	  If foc=9 Then optMeters=optMeters+1 : PlaySound sMenuBrowse : keytim=5 
	  If foc=10 Then entDisplay=entDisplay+1 : PlaySound sMenuBrowse : keytim=5 
	  If foc=11 Then optGrid=optGrid+1 : PlaySound sMenuBrowse : keytim=5  
	  If foc=12 Then optMusicVolume=optMusicVolume+5 : PlaySound sMenuBrowse : keytim=5
	 EndIf 
	EndIf  
	;limits
	If foc<1 Then foc=13
	If foc>13 Then foc=1 
	If optGameRes<1 Then optGameRes=1
	If optGameRes>no_resolutions Then optGameRes=no_resolutions
	If oldRes<>optGameRes Then optMenuRes=optGameRes
	If optMenuRes<1 Then optMenuRes=1
	If optMenuRes>no_resolutions Then optMenuRes=no_resolutions 
	If optDefaultCam<1 Then optDefaultCam=34
	If optDefaultCam>34 Then optDefaultCam=1 
	If optFog<0 Then optFog=1
	If optFog>1 Then optFog=0
	If optDetail<0 Then optDetail=0
	If optDetail>2 Then optDetail=2 
	If optCrowdAnim<0 Then optCrowdAnim=0
	If optCrowdAnim>2 Then optCrowdAnim=2 
	If optShadows<0 Then optShadows=0
	If optShadows>2 Then optShadows=2 
	If optFX<0 Then optFX=0
	If optFX>2 Then optFX=2
	If optGore<0 Then optGore=0
	If optGore>4 Then optGore=4
	If optMeters<0 Then optMeters=0
	If optMeters>2 Then optMeters=2 
	If entDisplay<0 Then entDisplay=0
	If entDisplay>3 Then entDisplay=3  
	If optGrid<0 Then optGrid=1
	If optGrid>1 Then optGrid=0  
	If optMusicVolume<0 Then optMusicVolume=0
	If optMusicVolume>100 Then optMusicVolume=100     
	
 UpdateWorld
 Next
 RenderWorld 1

 ;DISPLAY
 DrawImage gBackground,rX#(400),rY#(300)
 DrawImage gLogo(2),rX#(400),rY#(60) 
 ;options
 y=140
 DrawOption(1,rX#(400),rY#(y),"Game Resolution",textResX$(optGameRes)+" x "+textResY$(optGameRes)) : y=y+32
 GetCamera(optDefaultCam)
 namer$=camName$(camType,0)
 If camName$(camType,camOption)<>"" Then namer$=namer$+" "+camName$(camType,camOption)
 DrawOption(2,rX#(400),rY#(y),"Default Camera",namer$) : y=y+36
 DrawOption(3,rX#(400),rY#(y),"Fog Effect",textOnOff$(optFog)) : y=y+32
 DrawOption(4,rX#(400),rY#(y),"Arena Detail",textDetail$(optDetail)) : y=y+32 
 DrawOption(5,rX#(400),rY#(y),"Crowd",textCrowdAnim$(optCrowdAnim)) : y=y+36 
 DrawOption(6,rX#(400),rY#(y),"Particle FX",textFX$(optFX)) : y=y+32
 DrawOption(7,rX#(400),rY#(y),"Shadows",textShadows$(optShadows)) : y=y+32  
 DrawOption(8,rX#(400),rY#(y),"Gore",textGore$(optGore)) : y=y+36
 DrawOption(9,rX#(400),rY#(y),"Health Meters",textMeters$(optMeters)) : y=y+32
 DrawOption(10,rX#(400),rY#(y),"Entertainment Display",textEntertainment$(entDisplay)) : y=y+32
 DrawOption(11,rX#(400),rY#(y),"Character Selection",textGrid$(optGrid)) : y=y+36   
 DrawOption(12,rX#(400),rY#(y),"Music Volume",optMusicVolume+"%") : y=y+36
 DrawOption(13,rX#(400),rY#(560),"<<< SAVE & EXIT <<<","")  
 ;cursor
 If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect  
 DrawImage gCursor,MouseX(),MouseY()

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
FreeTimer timer
If go=1 Then PlaySound sMenuGo Else PlaySound sMenuBack 
;change resolution
If optMenuRes<>oldMenuRes
 ChangeResolution(optMenuRes,1)
EndIf
SaveOptions()
;proceed
SaveOptions()
If go=1 And foc=13 Then screen=1
If go=-1 Then screen=1
End Function

;//////////////////////////////////////////////////////////////////////////////
;---------------------------- 4. REDEFINE KEYS --------------------------------
;//////////////////////////////////////////////////////////////////////////////
Function RedefineKeys()
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
foc=8 : oldfoc=foc : screenCall=0
go=0 : gotim=0 : keytim=20
While go=0

 If screenCall=0 Then Cls

 frames=WaitTimer(timer)
 For framer=1 To frames
	
	;timers
	keytim=keytim-1
	If keytim<1 Then keytim=0
	
	;PORTAL
    gotim=gotim+1
	If gotim>20 And keytim=0 And screenCall=0
	 ;leave
	 If KeyDown(1) Then go=-1
	 ;proceed
	 If KeyDown(28) Or ButtonPressed() Or MouseDown(1)
	  If foc=8 Then go=1
	 EndIf
	EndIf
	;music
	ManageMusic(-1)
	
	;CONFIGURATION 
	If gotim>20 And keytim=0 And screenCall=0
	 ;highlight options
	 If KeyDown(200) Or JoyYDir()=-1 Then foc=foc-1 : keytim=5
	 If KeyDown(208) Or JoyYDir()=1 Then foc=foc+1 : keytim=5
	 If foc<1 Then foc=8
	 If foc>8 Then foc=1
	 ;activate
	 If KeyDown(28) Or ButtonPressed() Or MouseDown(1)
	  ;enter new command
	  If foc=<6
	   PlaySound sMenuBrowse : keytim=20
	   callX=MouseX() : callY=MouseY()
	   screenCall=foc
	  EndIf
	  ;restore defaults
	  If foc=7
	   PlaySound sTrash : keytim=20
	   keyAttack=30 : keyGrapple=31
	   keyRun=44 : keyPickUp=45
	   keySwitch=29 : keyTaunt=57
	  EndIf
	 EndIf 
	EndIf   
	
	;INPUT DELAY
    If screenCall>0 And keytim=0
     If screenCall=1 Then keyAttack=AssignKey(keyAttack)
     If screenCall=2 Then keyGrapple=AssignKey(keyGrapple)
     If screenCall=3 Then keyRun=AssignKey(keyRun)
     If screenCall=4 Then keyPickUp=AssignKey(keyPickUp)
     If screenCall=5 Then keySwitch=AssignKey(keySwitch)
     If screenCall=6 Then keyTaunt=AssignKey(keyTaunt)
    EndIf
	
 UpdateWorld
 Next
 RenderWorld 1

 ;DISPLAY
 DrawImage gBackground,rX#(400),rY#(300)
 DrawImage gLogo(2),rX#(400),rY#(60) 
 ;lock mouse
 If screenCall>0 Then MoveMouse callX,callY
 ;options
 SetFont font(1)
 y=150
 If screenCall=1 Then namer$="Press New Key" Else namer$="'"+Key$(keyAttack)+"' Key"
 DrawOption(1,rX#(400),rY#(y),"Attack",namer$) : y=y+35
 If screenCall=2 Then namer$="Press New Key" Else namer$="'"+Key$(keyGrapple)+"' Key"
 DrawOption(2,rX#(400),rY#(y),"Grapple / Throw",namer$) : y=y+40
 If screenCall=3 Then namer$="Press New Key" Else namer$="'"+Key$(keyRun)+"' Key"
 DrawOption(3,rX#(400),rY#(y),"Run",namer$) : y=y+35
 If screenCall=4 Then namer$="Press New Key" Else namer$="'"+Key$(keyPickUp)+"' Key"
 DrawOption(4,rX#(400),rY#(y),"Pick-Up / Drop",namer$) : y=y+40
 If screenCall=5 Then namer$="Press New Key" Else namer$="'"+Key$(keySwitch)+"' Key"
 DrawOption(5,rX#(400),rY#(y),"Switch Focus",namer$) : y=y+35
 If screenCall=6 Then namer$="Press New Key" Else namer$="'"+Key$(keyTaunt)+"' Key"
 DrawOption(6,rX#(400),rY#(y),"Taunt / Pin",namer$) : y=y+50
 DrawOption(7,rX#(400),rY#(y),"RESTORE DEFAULTS","")
 DrawOption(8,rX#(400),rY#(560),"<<< BACK <<<","")
 ;cursor
 If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect  
 DrawImage gCursor,MouseX(),MouseY()

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
FreeTimer timer
If go=1 Then PlaySound sMenuGo Else PlaySound sMenuBack 
screen=2
End Function

;//////////////////////////////////////////////////////////////////////////////
;--------------------------- 5. REDEFINE GAMEPAD ------------------------------
;//////////////////////////////////////////////////////////////////////////////
Function RedefineGamepad()
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
foc=8 : oldfoc=foc : screenCall=0
go=0 : gotim=0 : keytim=20
While go=0

 If screenCall=0 Then Cls

 frames=WaitTimer(timer)
 For framer=1 To frames
	
	;timers
	keytim=keytim-1
	If keytim<1 Then keytim=0
	
	;PORTAL
    gotim=gotim+1
	If gotim>20 And keytim=0 And screenCall=0
	 ;leave
	 If KeyDown(1) Then go=-1
	 ;proceed
	 If KeyDown(28) Or ButtonPressed() Or MouseDown(1)
	  If foc=8 Then go=1
	 EndIf
	EndIf
	;music
	ManageMusic(-1)
	
	;CONFIGURATION 
	If gotim>20 And keytim=0 And screenCall=0
	 ;highlight options
	 If KeyDown(200) Or JoyYDir()=-1 Then foc=foc-1 : keytim=5
	 If KeyDown(208) Or JoyYDir()=1 Then foc=foc+1 : keytim=5
	 If foc<1 Then foc=8
	 If foc>8 Then foc=1
	 ;activate
	 If KeyDown(28) Or ButtonPressed() Or MouseDown(1)
	  ;enter new command
	  If foc=<6
	   PlaySound sMenuBrowse : keytim=20
	   callX=MouseX() : callY=MouseY()
	   screenCall=foc
	  EndIf
	  ;restore defaults
	  If foc=7
	   PlaySound sTrash : keytim=20
	   buttAttack=1 : buttGrapple=2
	   buttRun=3 : buttPickUp=4
	   buttSwitch=5 : buttTaunt=7
	  EndIf
	 EndIf 
	EndIf   
	
	;INPUT DELAY
    If screenCall>0 And keytim=0
     If screenCall=1 Then buttAttack=AssignButton(buttAttack)
     If screenCall=2 Then buttGrapple=AssignButton(buttGrapple)
     If screenCall=3 Then buttRun=AssignButton(buttRun)
     If screenCall=4 Then buttPickUp=AssignButton(buttPickUp)
     If screenCall=5 Then buttSwitch=AssignButton(buttSwitch)
     If screenCall=6 Then buttTaunt=AssignButton(buttTaunt)
    EndIf
	
 UpdateWorld
 Next
 RenderWorld 1

 ;DISPLAY
 DrawImage gBackground,rX#(400),rY#(300)
 DrawImage gLogo(2),rX#(400),rY#(60) 
 ;lock mouse
 If screenCall>0 Then MoveMouse callX,callY
 ;options
 y=150
 If screenCall=1 Then namer$="Press New Button" Else namer$="Button "+buttAttack
 DrawOption(1,rX#(400),rY#(y),"Attack",namer$) : y=y+35
 If screenCall=2 Then namer$="Press New Button" Else namer$="Button "+buttGrapple
 DrawOption(2,rX#(400),rY#(y),"Grapple / Throw",namer$) : y=y+40
 If screenCall=3 Then namer$="Press New Button" Else namer$="Button "+buttRun
 DrawOption(3,rX#(400),rY#(y),"Run",namer$) : y=y+35
 If screenCall=4 Then namer$="Press New Button" Else namer$="Button "+buttPickUp
 DrawOption(4,rX#(400),rY#(y),"Pick-Up / Drop",namer$) : y=y+40
 If screenCall=5 Then namer$="Press New Button" Else namer$="Button "+buttSwitch
 DrawOption(5,rX#(400),rY#(y),"Switch Focus",namer$) : y=y+35
 If screenCall=6 Then namer$="Press New Button" Else namer$="Button "+buttTaunt
 DrawOption(6,rX#(400),rY#(y),"Taunt / Pin",namer$) : y=y+50
 DrawOption(7,rX#(400),rY#(y),"RESTORE DEFAULTS","")
 DrawOption(8,rX#(400),rY#(560),"<<< BACK <<<","")
 ;cursor
 If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect  
 DrawImage gCursor,MouseX(),MouseY()

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
FreeTimer timer
If go=1 Then PlaySound sMenuGo Else PlaySound sMenuBack 
screen=2
End Function

;//////////////////////////////////////////////////////////////////////////////
;--------------------------- 10. SLOT SELECTION -------------------------------
;//////////////////////////////////////////////////////////////////////////////
Function SelectSlot()
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
foc=5 : oldfoc=foc
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
	  If foc=>0 And foc=<3 Then go=1
	  If foc=4 And game=1 Then go=1
	  If foc=5 Then go=-1
	  ;block access
	  If go=1 And game=0 And foc=>1 And foc=<3
       If slotActive(foc)=0 Then BlockAccess()
      EndIf 
      ;resetting process
      If foc=4 And game=0
       If confirmer=0 Then confirmer=1 : PlaySound sMenuGo : keytim=10
	   If confirmer=1 And keytim=0
	    Loader("Please Wait","Restoring Backup Universe")
	    PlaySound sMenuBrowse : keytim=10
	    LoadWorld(-1)
	    LoadChars(-1)
	    LoadPhotos(-1)
	    Loader("Please Wait","Replacing Current Universe")
	    SaveWorld(0)
	    SaveChars(0)
	    SavePhotos(0)
	    PlaySound sTrash : confirmer=0
       EndIf
      EndIf
	 EndIf
	EndIf
	;music
	ManageMusic(-1)
	
	;CONFIGURATION 
	If gotim>20 And keytim=0
	 ;highlight option
	 If KeyDown(200) Or JoyYDir()=-1 Then foc=foc-1 : keytim=5
	 If KeyDown(208) Or JoyYDir()=1 Then foc=foc+1 : keytim=5 
	 ;delete records
	 If (KeyDown(14) Or KeyDown(211)) And foc=>1 And foc=<3
	  If slotActive(foc)>0
	   PlaySound sTrash : keytim=10
	   slotActive(foc)=0
	  EndIf
	 EndIf   
	EndIf  
	;limits
	If game=0 And screenAgenda<>1
	 If foc=4 And oldfoc>4 Then foc=3
	 If foc=4 And oldfoc<4 Then foc=5 
	EndIf
	If game=1
	 If foc<1 Then foc=5
	 If foc>5 Then foc=1 
	Else
	 If foc<0 Then foc=5
	 If foc>5 Then foc=0     
    EndIf
    ;reset confirmer
	If foc<>4 Then confirmer=0
	
 UpdateWorld
 Next
 RenderWorld 1

 ;DISPLAY
 DrawImage gBackground,rX#(400),rY#(300)
 DrawImage gLogo(1),rX#(400),rY#(135) 
 ;previews
 If game=1 Then y=300 Else y=330
 If foc=>1 And foc=<3
  If slotActive(foc)>0 And slotPreview(foc)>0
   photoX=rX#(400)-210 : photoY=rY#(y+(35*(foc-1)))
   DrawImage slotPreview(foc),photoX,photoY
   Color 0,0,0 : Rect photoX-116,photoY-58,232,117,0
   Color 225,0,0 : Rect photoX-115,photoY-57,230,115,0
   Color 0,0,0 : Rect photoX-114,photoY-56,228,113,0
  EndIf
 EndIf
 ;options
 If game=1 Then y=300 Else y=330
 If game=0 Then DrawOption(0,rX#(400),rY#(y)-40,"DEFAULT UNIVERSE","")
 For count=1 To 3
  If game=0
   If slotActive(count)>0 Then namer$="CAREER "+count+"'S UNIVERSE" Else namer$="EMPTY"
  EndIf
  If game=1
   If slotActive(count)>0 Then namer$="RESUME CAREER "+count Else namer$="NEW CAREER"
  EndIf
  DrawOption(count,rX#(400),rY#(y),namer$,"")
  If slotActive(count)=0 And game=0 Then DrawImage gBlackout(1),rX#(400),rY#(y)
  y=y+35
 Next
 If game=0 And screenAgenda=1
  If confirmer=1 Then namer$="ARE YOU SURE?" Else namer$="RESET UNIVERSE"
  DrawOption(4,rX#(400),rY#(y+10),namer$,"")
 EndIf 
 If game=1 
  DrawOption(4,rX#(400),rY#(y+15),"HALL OF FAME","")
  DrawImage gCrown,rX#(400),rY#(y+15)-17
 EndIf
 DrawOption(5,rX#(400),rY#(560),"<<< BACK <<<","")  
 ;cursor
 If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect  
 DrawImage gCursor,MouseX(),MouseY()

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
FreeTimer timer
If go=1 Then PlaySound sMenuGo Else PlaySound sMenuBack 
If go=-1 Then screen=1
If go=1 And foc=4 Then screen=26
;access slot
If go=1 And foc=>0 And foc=<3
 If game=1 And slotActive(foc)=0
  If SlotUsable(foc)
   targetSlot=foc
   screen=27
  Else
   Loader("Please Wait","Generating Career")
   SwitchSlot(0)
   slot=foc : cupSlot=3+slot
   gamInherit=0
   slotActive(slot)=1
   SaveUniverse()
   SaveProgress(slot)
   SaveWorld(slot)
   SaveChars(slot)
   SavePhotos(slot) 
   Repeat
    gamChar=fedRoster(7,Rnd(1,fedSize(7)))
   Until gamChar<>fedBooker(7)
   editChar=gamChar
   screen=51 : screenAgenda=5
  EndIf
 Else
  If slot<>foc Then Loader("Please Wait","Restoring Data")
  SwitchSlot(foc)
  screen=11
  If screenAgenda=11 Then cupSlot=foc : screen=16 : optCupFed=0 : optCupTeams=0 : optCupSize=4
  If game=1 Then cupSlot=3+slot : gamScroll=-((GetMonth(gamDate)-1)*125) : screen=20
 EndIf
EndIf
End Function

;//////////////////////////////////////////////////////////////////////////////
;-------------------------- 11. ROSTER SELECTION ------------------------------
;//////////////////////////////////////////////////////////////////////////////
Function SelectRoster()
;editor locks
If fedLocked(1)=0 And fedLocked(2)=0 And fedLocked(3)=0 And fedLocked(4)=0 And fedLocked(5)=0 And fedLocked(6)=0
 fedLocked(7)=0
 fedLocked(8)=0
 fedLocked(9)=0
EndIf
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
foc=11 : oldfoc=foc
go=0 : gotim=0 : keytim=20
While go=0

 Cls
 frames=WaitTimer(timer)
 For framer=1 To frames
	
	;timers
	keytim=keytim-1
	If keytim<1 Then keytim=0
	flashTim=flashTim+1
	If flashTim>30 Then flashTim=0  
	
	;PORTAL
    gotim=gotim+1
	If gotim>20 And keytim=0
	 ;leave
	 If KeyDown(1) Then go=-1
	 ;proceed
	 If KeyDown(28) Or ButtonPressed() Or MouseDown(1)
	  If foc=11 Then go=-1 Else go=1
	  ;block access
	  If go=1
       If screenAgenda=1 And foc=>1 And foc=<9
        If fedLocked(foc) Then BlockAccess()
       EndIf
       If screenAgenda=4 And (foc=10 Or foc=charFed(gamChar)) Then BlockAccess()
       If screenAgenda=5 And foc=>7 Then BlockAccess()
      EndIf  
	 EndIf
	EndIf
	;music
	ManageMusic(-1)
	
	;CONFIGURATION 
	If gotim>20 And keytim=0
	 ;highlight option
	 If KeyDown(200) Or JoyYDir()=-1
	  newFoc=foc-3
	  If foc=>1 And foc=<3 Then newFoc=foc
	  If foc=10 Then newFoc=8
	  If foc=11 Then newFoc=10 
	  foc=newFoc : keytim=5
	 EndIf
	 If KeyDown(208) Or JoyYDir()=1
	  newFoc=foc+3
	  If foc=>7 And foc=<9 Then newFoc=10
	  If foc=10 Then newFoc=11
	  If foc=11 Then newFoc=foc
	  foc=newFoc : keytim=5
	 EndIf
	 If foc=>1 And foc=<9
	  If KeyDown(203) Or JoyXDir()=-1
	   newFoc=foc-1
	   If foc=1 Or foc=4 Or foc=7 Then newFoc=foc
	   foc=newFoc : keytim=5
	  EndIf
	  If KeyDown(205) Or JoyXDir()=1
	   newFoc=foc+1
	   If foc=3 Or foc=6 Or foc=9 Then newFoc=foc
	   foc=newFoc : keytim=5
	  EndIf
	 EndIf
	 ;edit properties
     If screenAgenda=1 And foc=>1 And foc=<9 
      If fedLocked(foc)=0
       If KeyDown(16) Then fedBank(foc)=fedBank(foc)-10000 : PlaySound sMenuBrowse : keytim=3
	   If KeyDown(17) Then fedBank(foc)=fedBank(foc)+10000 : PlaySound sMenuBrowse : keytim=3
	   If KeyDown(30) Then fedPopularity(foc)=fedPopularity(foc)-1 : PlaySound sMenuBrowse : keytim=3
	   If KeyDown(31) Then fedPopularity(foc)=fedPopularity(foc)+1 : PlaySound sMenuBrowse : keytim=3
	   If KeyDown(44) Then fedReputation(foc)=fedReputation(foc)-1 : PlaySound sMenuBrowse : keytim=3
	   If KeyDown(45) Then fedReputation(foc)=fedReputation(foc)+1 : PlaySound sMenuBrowse : keytim=3
	   CheckFedLimits(foc)
	   fedOldPopularity(foc)=fedPopularity(foc)
       fedOldReputation(foc)=fedReputation(foc)
	  EndIf
	 EndIf
	 ;reset contracts/status cheat
	 If screenAgenda=1 And KeyDown(56) And KeyDown(46)
	  PlaySound sPaper : keytim=10
      For char=1 To no_chars
       ResetHealthStatus(char)
       ResetCareerStatus(char) 
      Next
	 EndIf
	 ;jumble rosters cheat
	 If screenAgenda=1 And KeyDown(56) And KeyDown(36)
	  PlaySound sCrowd(8) : keytim=10
	  For fed=1 To 9
       fedSize(fed)=0
       For count=1 To 40
        fedRoster(fed,count)=0
       Next
      Next
      For char=1 To no_chars
       If charRole(char)<>3
        its=0
        Repeat
         satisfied=1 : its=its+1
         charFed(char)=Rnd(1,9)
         If charFed(char)=>8 And fedSize(charFed(char))>0 Then satisfied=0
         If fedSize(charFed(char))>30 Then satisfied=0
         If charFed(char)=<6 And GetValue(char)<60 And charRole(char)=1 Then satisfied=0
         If charFed(char)=>7 And GetValue(char)>70 Then satisfied=0
        Until satisfied=1 Or its>100
       EndIf
       fedSize(charFed(char))=fedSize(charFed(char))+1
       fedRoster(charFed(char),fedSize(charFed(char)))=char
       If fedSize(charFed(char))=1 Then fedBooker(charFed(char))=char
       If fedSize(charFed(char))=2 And charFed(char)=<6 Then fedChampWorld(charFed(char))=char
       If fedSize(charFed(char))=3 And charFed(char)=<6 Then fedChampInter(charFed(char))=char
       If fedSize(charFed(char))=4 And charFed(char)=<6 Then fedChampTag(charFed(char),1)=char
       If fedSize(charFed(char))=5 And charFed(char)=<6 Then fedChampTag(charFed(char),2)=char
       If fedSize(charFed(char))=6 And charFed(char)=<6 Then fedCupHolder(charFed(char))=char
       GenerateContract(char)
      Next
	 EndIf 
	 ;unlock rosters cheat
	 If screenAgenda=1 And KeyDown(18) And KeyDown(50)
	  If MouseX()>rX#(400)-100 And MouseX()<rX#(400)+100 And MouseY()>rY#(60)-50 And MouseY()<rY#(60)+50
	   PlaySound sCrowd(6) : keytim=10
	   For count=1 To 9
	    fedLocked(count)=0
	   Next
	  EndIf
	 EndIf 
	 ;reset roster locks
	 If screenAgenda=1 And KeyDown(29) And KeyDown(19)
	  PlaySound sTrash : keytim=10
	  For count=1 To 9
	   fedLocked(count)=1
	  Next
	 EndIf     
	EndIf   
	
 UpdateWorld
 Next
 RenderWorld 1

 ;DISPLAY
 DrawImage gBackground,rX#(400),rY#(300)
 DrawImage gLogo(2),rX#(400),rY#(60)
 ;display profile
 If foc=>1 And foc=<9 Then DrawFedProfile(foc,-1,-1)
 ;promotions
 nameX=0 : nameY=0
 x=rX#(400)-235 : y=rY#(215)
 For count=1 To 9 
  If MouseX()=>x-80 And MouseX()=<x+80 And MouseY()=>y-90 And MouseY()=<y Then foc=count
  If foc<>count Or (screenAgenda=1 And fedLocked(count)) Or (screenAgenda=5 And count=>7)
   DrawImage gFedDark(count),x,y-60
  Else
   DrawImage gFed(count),x,y-60 
  EndIf
  DrawOption(count,x,y,fedName$(count),"") 
  If screenAgenda=1 And fedLocked(count) 
   DrawImage gBlackout(1),x,y
   If foc=count And flashTim=<20
    If count=>7 Then namer$="Unlock All Other Promotions!" Else namer$="Top The TV Ratings!"
    SqueezeFont(namer$,190,18)
    Outline(namer$,x,y-1,50,25,50,220,150,250)
   EndIf
  EndIf
  If screenAgenda=5 And count=>7 Then DrawImage gBlackout(1),x,y
  If foc=count Then nameX=x : nameY=y
  x=x+235
  If x>rX#(400)+400 Then x=rX#(400)-235 : y=y+rY#(128)
 Next
 DrawOption(10,rX#(400),rY#(515),"ALL CHARACTERS","")
 If screenAgenda=5 Then DrawImage gBlackout(1),rX#(400),rY#(515)
 ;roster preview
 If KeyDown(57) Or MouseDown(2)
  If foc=>1 And foc=<9
   x=rX#(400)-300
   If fedSize(foc)>1 Then spacing=600/(fedSize(foc)-1)
   If spacing>30 Then spacing=30
   For count=1 To fedSize(foc)
    DrawImage charPhoto(fedRoster(foc,count)),x,rY#(500)
    x=x+spacing 
   Next
  EndIf
 EndIf
 ;options
 If screenAgenda=>7 And screenAgenda=<8 Then namer$="<<< SAVE & EXIT <<<" Else namer$="<<< BACK <<<"
 DrawOption(11,rX#(400),rY#(560),namer$,"")  
 ;name changes
 If KeyDown(14) And screenAgenda=1 And foc=>1 And foc=<9 And gotim>40 And keytim=0
  If fedLocked(foc)=0
   PlaySound sMenuBrowse : keytim=10 : FlushKeys()
   DrawOption(-1,nameX,nameY,"","") : Flip
   Locate nameX-84,nameY-6 : Color 0,0,0
   SetFont font(2) 
   oldName$=fedName$(foc)
   fedName$(foc)=Left$(Input$(""),30) 
   If fedName$(foc)="" Then fedName$(foc)=oldName$
  EndIf
 EndIf
 ;cursor
 If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect  
 DrawImage gCursor,MouseX(),MouseY()
 If screenAgenda=4 Then DrawImage charPhoto(negChar),MouseX()+40,MouseY()+25
 If screenAgenda=5 Then DrawImage charPhoto(gamChar),MouseX()+40,MouseY()+25
 If screenAgenda=>7 And screenAgenda=<8 Then DrawImage charPhoto(editChar),MouseX()+40,MouseY()+25

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
FreeTimer timer
If go=1 Or screenAgenda=7 Or screenAgenda=8 Then PlaySound sMenuGo Else PlaySound sMenuBack 
editFoc=0 : editScroll=0 : editFilter=0
If go=1
 If foc=>1 And foc=<9 Then fed=foc Else fed=0
EndIf
If screenAgenda=0 ;playing
 If go=-1 Then screen=1
 If go=1 Then GenerateArena(fed,Rnd(0,2),1) : screen=14 
EndIf
If screenAgenda=1 ;editing
 If go=-1 Then screen=1
 If go=1 Then screen=12 
EndIf
If screenAgenda=2 ;studying in career
 If go=-1 Then screen=20
 If go=1 Then screen=12
EndIf
If screenAgenda=3 ;buying
 If go=-1 Then screen=20
 If go=1 Then screen=12
EndIf
If screenAgenda=4 ;selling
 If go=-1 Then fed=charFed(gamChar) : screen=12
 If go=1 Then negTopic=2 : screen=56
EndIf
If screenAgenda=7 Or screenAgenda=8 ;setting relationships
 If go=-1 Then screen=51 : screenAgenda=1
 If go=1 Then screen=12
EndIf
;career initiation
If screenAgenda=5 And go=-1 Then slotActive(slot)=0 : screen=10
If screenAgenda=5 And go=1
 MoveChar(gamChar,fed)
 fed=charFed(gamChar)
 GenerateCareer()
 ResetCharacters()
 pChar(1)=gamChar : pControl(1)=3
 pChar(2)=fedBooker(charFed(gamChar))
 If pChar(2)=0 Then pChar(2)=201
 GetMatchRules(1) : AddGimmick(0)
 matchTimeLim=1 : matchPromo=50
 screen=50 : screenAgenda=0
EndIf
End Function

;//////////////////////////////////////////////////////////////////////////////
;------------------------- 12. CHARACTER SELECTION ----------------------------
;//////////////////////////////////////////////////////////////////////////////
Function SelectCharacter()
;assess player count
no_plays=no_wrestlers+no_refs
If screenAgenda>0 Then no_plays=1
If screenAgenda=11 
 no_plays=optCupSize
 If optCupTeams>0 Then no_plays=no_plays*2
EndIf
;reset selection status
If optGrid=0 And editFilter=0 Then editFilter=1
If optGrid=1 Then editFilter=0
GetRankings(editFilter)
ResetCharacters()
For char=1 To no_chars
 charSelected(char)=-1
Next
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
cloner=0 : scroll=editScroll
If no_plays>1 And editFoc=0 Then editFoc=1
foc=editFoc : oldfoc=foc
go=0 : gotim=0 : keytim=10
While go=0

 Cls
 frames=WaitTimer(timer)
 For framer=1 To frames
	
	;timers
	keytim=keytim-1
	If keytim<1 Then keytim=0
	flashTim=flashTim+1
	If flashTim>20 Then flashTim=0   
	
	;PORTAL
	chosen=CountSelected()
    gotim=gotim+1
	If gotim>20 And keytim=0
	 ;leave / cancel switching
	 If KeyDown(1) And cloner=0 Then go=-1 
	 If (KeyDown(1) Or MouseDown(1)) And cloner>0 Then cloner=0 : PlaySound sMenuBack : keytim=10
	 ;proceed
	 If KeyDown(28) Or ButtonPressed() Or (MouseDown(1) And MouseOverBox())
	  If foc=0 
	   If no_plays>1 And chosen=no_plays Then go=1 
	   If no_plays=1 Or (no_plays>1 And chosen<no_plays) Then go=-1
	  EndIf
	  If foc=>1 And foc=<no_boxes And no_plays=1 And subFoc=-1 And screenAgenda<>2 And screenAgenda<>7 And screenAgenda<>8
	   If MouseY()=>rY#(150)-15 And MouseY()=<rY#(510)+15 Then go=1
	  EndIf
	  If game=1
	   If screenAgenda=1 And charVacant(fedRank(fed,foc))>0 Then BlockAccess() 
	   If (screenAgenda=3 Or screenAgenda=4) And fedRank(fed,foc)=gamChar Then BlockAccess()
	   If screenAgenda=6
	    If InjuryStatus(fedRank(fed,foc))>0 Or charVacant(fedRank(fed,foc))>0 Or charAgreement(fedRank(fed,foc),20)>0 Then BlockAccess()
	   EndIf
	  EndIf
	 EndIf
	 ;switch display
	 If KeyDown(15)
	  PlaySound sMenuGo : keytim=10
	  If optGrid=1 Then optGrid=0 Else optGrid=1
	  If optGrid=0 And editFilter=0
	   editFilter=1
	   GetRankings(editFilter)
	  EndIf
	  If optGrid=1 And editFilter<>0
	   editFilter=0
	   GetRankings(editFilter)
	  EndIf
	  scroll=0
	 EndIf
	EndIf
	;music
	ManageMusic(-1)
	
	;ASSESS ROSTER
	;count character slots
	no_boxes=0 
    For count=1 To no_chars
     If fedRank(fed,count)>0 Then no_boxes=no_boxes+1
    Next
    ;add one for editing
    If screenAgenda=1 And game=0 And fed>0 And no_chars<optCharLim And fedSize(fed)<optRosterLim
     no_boxes=fedSize(fed)+1
     charName$(no_chars+1)="~ NEW CHARACTER ~"
     fedRoster(fed,no_boxes)=no_chars+1
     fedRank(fed,no_boxes)=no_chars+1
     charPhoto(no_chars+1)=charPhoto(0)
    EndIf

	;CONFIGURATION
	If gotim>20 And keytim=0
	 ;FIDDLES
	 char=fedRank(fed,foc) 
	 If screenAgenda=1 And game=0 And foc>0 And char=<no_chars
	  ;switch rosters
	  If fedSize(charFed(char))>3 Or (fedSize(charFed(char))>1 And charFed(char)=>8)
	   For count=1 To 9
	    If KeyDown(count+1) And fedSize(count)<optRosterLim And charFed(char)<>count And fedLocked(count)=0
	     PlaySound sSwing : keytim=10
	     MoveChar(char,count)
	     GetRankings(editFilter)
	     If foc=no_boxes Then foc=foc-1
	    EndIf
	   Next 
	  EndIf
	  ;change titles
	  If KeyDown(48) And fedBooker(charFed(char))<>char Then fedBooker(charFed(char))=char : PlaySound sPaper : keytim=10
	  If charFed(char)=<6
	   If KeyDown(17) And fedChampWorld(charFed(char))<>char Then fedChampWorld(charFed(char))=char : PlaySound sPaper : keytim=10
	   If KeyDown(23) And fedChampInter(charFed(char))<>char Then fedChampInter(charFed(char))=char : PlaySound sPaper : keytim=10
	   If KeyDown(20) And fedChampTag(charFed(char),1)<>char Then fedChampTag(charFed(char),1)=char : PlaySound sPaper : keytim=10
	   If KeyDown(21) And fedChampTag(charFed(char),2)<>char Then fedChampTag(charFed(char),2)=char : PlaySound sPaper : keytim=10
	  EndIf
	  If KeyDown(46) And KeyDown(56)=0 And charFed(char)=<7 And fedCupHolder(charFed(char))<>char
	   PlaySound sImpactMetal : keytim=10
	   fedCupHolder(charFed(char))=char
	  EndIf
	  ;inflict injury
	  If KeyDown(36) And InjuryStatus(char)=0
	   PlaySound sAgony(Rnd(1,3)) : keytim=10
	   charInjured(char,Rnd(0,5))=Rnd(1,10)
	  EndIf
	  ;reset health status
	  If KeyDown(14) 
	   PlaySound sTrash : keytim=10
	   ResetHealthStatus(char)
	  EndIf
	  ;reset career status
	  If KeyDown(56) And KeyDown(46) ;And (char<>gamChar Or slot=0)
	   PlaySound sPaper : keytim=10
	   ResetCareerStatus(char)
	  EndIf
	  ;copy moves to clipboard
	  If KeyDown(199) Then editClipboard=char : PlaySound sCamera : keytim=10
	 EndIf
	 ;SELECTION
	 ;browse slots
	 If optGrid=1
	  If KeyDown(200) Or JoyYDir()=-1
	   newFoc=foc-4
	   If foc=>1 And foc=<4 Then newFoc=foc
	   If foc=0 Then newFoc=no_boxes
	   foc=newFoc : keytim=5
	  EndIf
	  If KeyDown(208) Or JoyYDir()=1
	   newFoc=foc+4
	   If newFoc>no_boxes Then newFoc=no_boxes
	   If FindRow(newFoc,4)=FindRow(foc,4) Then newFoc=0
	   If foc=no_boxes Then newFoc=0
	   If newFoc=0 And no_plays>1
	    If CountSelected()<no_plays Then newFoc=foc
	   EndIf
	   If foc=0 Then newFoc=0
	   foc=newFoc : keytim=5
	  EndIf
	  If (KeyDown(203) Or JoyXDir()=-1) And foc>0
	   newFoc=foc-1
	   For count=1 To 100
	    If foc=((count-1)*4)+1 Then newFoc=foc
	   Next
	   foc=newFoc : keytim=5
	  EndIf
	  If (KeyDown(205) Or JoyXDir()=1) And foc>0
	   newFoc=foc+1
	   For count=1 To 100
	    If foc=count*4 Or foc=no_boxes Then newFoc=foc
	   Next
	   foc=newFoc : keytim=5
	  EndIf
	 Else
	  If KeyDown(200) Or JoyYDir()=-1 Then foc=foc-1 : keytim=4
	  If KeyDown(208) Or JoyYDir()=1 Then foc=foc+1 : keytim=4
      If foc<0 Then foc=no_boxes	
      If foc>no_boxes Then foc=0	
	 EndIf
	 ;select or toggle control
	 If KeyDown(28) Or ButtonPressed() Or (MouseDown(1) And MouseOverBox() And MouseY()=>rY#(150)-15 And MouseY()=<rY#(510)+15)
	  char=fedRank(fed,foc)
	  If foc>0 And char<>no_chars+1 And subFoc=-1
	   If no_plays>1
	    If charVacant(char)>0
	     If keytim=0 Then PlaySound sVoid : keytim=10
	    Else
	     For cyc=1 To no_plays
	      If pChar(cyc)=0 And charSelected(char)=-1 Then pChar(cyc)=char : Exit
	     Next
	     If no_plays>1 And (CountSelected()<no_plays Or charSelected(char)=>0)
	      PlaySound sMenuBrowse : keytim=8
	      charSelected(char)=charSelected(char)+1
	      If charSelected(char)=1 And ControlUsed(char,1) Then charSelected(char)=charSelected(char)+1
	      If charSelected(char)=2 And ControlUsed(char,2) Then charSelected(char)=charSelected(char)+1
	      If charSelected(char)>2 Then charSelected(char)=-1
	     EndIf
	    EndIf
	   EndIf
	   If game=0 And screenAgenda=7 ;set relationship
	    If char=editChar
	     PlaySound sVoid : keytim=15
	    Else
	     PlaySound sMenuBrowse : keytim=8
	     charRelationship(editChar,char)=charRelationship(editChar,char)+1
	     If charRelationship(editChar,char)>1 Then charRelationship(editChar,char)=-1
	     charRelationship(char,editChar)=charRelationship(editChar,char)
	    EndIf
	   EndIf
	   If game=0 And screenAgenda=8 ;set real relationship
	    If char=editChar
	     PlaySound sVoid : keytim=15
	    Else
	     PlaySound sMenuBrowse : keytim=8
	     charRealRelationship(editChar,char)=charRealRelationship(editChar,char)+1
	     If charRealRelationship(editChar,char)>1 Then charRealRelationship(editChar,char)=-1
	     charRealRelationship(char,editChar)=charRealRelationship(editChar,char)
	    EndIf
	   EndIf
	  EndIf
	 EndIf
	 ;change rank criteria
	 If MouseDown(1) And subFoc=>0 And subFoc=<12
	  PlaySound sMenuGo : keytim=10
	  editFilter=subFoc : scroll=0
	  If subFoc=10 Then editFilter=19
	  If subFoc=11 Then editFilter=20   
	  GetRankings(editFilter)
	 EndIf 
	 ;CLONING/SWITCHING
	 char=fedRank(fed,foc) 
	 If KeyDown(57) Or (MouseDown(2) And MouseOverBox())
	  ;initiate
	  If foc>0 And char<>no_chars+1 And cloner=0 Then cloner=foc : PlaySound sMenuGo : keytim=10
	  ;cancel
	  If cloner>0 And cloner=foc And keytim=0 Then cloner=0 : PlaySound sMenuBack : keytim=10
	  ;execute
	  If cloner>0 And cloner<>foc And keytim=0
	   PlaySound sMenuGo : keytim=10
       If fed>0 And foc=fedSize(fed)+1 ;clone to new slot
 	    fedSize(fed)=fedSize(fed)+1 : no_chars=no_chars+1
        If no_chars>optCharLim Then no_chars=optCharLim
        fedRoster(fed,foc)=no_chars : fedRank(fed,foc)=no_chars
        CopyChar(fedRank(fed,cloner),no_chars)
        charPhoto(no_chars)=CopyImage(charPhoto(fedRank(fed,cloner))) 
        SaveImage(charPhoto(no_chars),"Data/Slot0"+slot+"/Portraits/Photo"+Dig$(no_chars,100)+".bmp")
        charFed(no_chars)=charFed(fedRank(fed,cloner))    
       Else
        oldSlot=fedRank(fed,foc) ;swap existing
        fedRank(fed,foc)=fedRank(fed,cloner)
        fedRank(fed,cloner)=oldSlot
        If optGrid=1 And charFed(fedRank(fed,foc))=charFed(fedRank(fed,cloner))
         f=charFed(fedRank(fed,cloner))
         oldSlot=fedRoster(f,foc)
         fedRoster(f,foc)=fedRoster(f,cloner)
         fedRoster(f,cloner)=oldSlot
        EndIf 
       EndIf
       cloner=0
	  EndIf
	 EndIf
	EndIf
	;bind controls to player (or remove)
	For cyc=1 To no_plays
	 pControl(cyc)=0
	 If screenAgenda=11 And charSelected(pChar(cyc))=>0
	  If optCupControl=>3 And optCupControl=<4 Then charSelected(pChar(cyc))=2
	  If optCupControl=5
	   For v=1 To optCupSize
	    If cyc=v*2 And charSelected(pChar((v*2)-1))>0 Then charSelected(pChar(cyc))=charSelected(pChar((v*2)-1))
	   Next
	  EndIf
	 EndIf
	 If charSelected(pChar(cyc))=-1 Then pChar(cyc)=0 
	 If charSelected(pChar(cyc))>0 Then pControl(cyc)=charSelected(pChar(cyc))
	Next
	
	;SCROLLING
	If optGrid=1
	 ;grid display
	 no_displays=(Int((rY#(510)-rY#(150))/35)+1)*4
	 If no_boxes>no_displays
	  If MouseDown(1) And keytim=0 
	   If subFoc=20 Then scroll=scroll+35 : PlaySound sMenuBrowse : keytim=5
	   If subFoc=21 Then scroll=scroll-35 : PlaySound sMenuBrowse : keytim=5
	  EndIf
	  If scroll>0 Then scroll=0
	  excess=Int(((no_boxes-no_displays)-1)/4)+1
	  If scroll<-(excess*35) Then scroll=-(excess*35)
	  If foc=>1 And foc=<no_boxes And foc<>oldfoc
	   excess=Int(((foc-no_displays)-1)/4)+1
	   If foc>no_displays And scroll>-(excess*35) Then scroll=-(excess*35)
	   recess=Int((foc-1)/4);Int(foc/no_displays)
	   If scroll<-(recess*35) Then scroll=-(recess*35) 
	  EndIf
     EndIf 
	Else
	 ;list display
	 no_displays=Int((rY#(510)-rY#(150))/32)+1
	 If no_boxes>no_displays
	  If MouseDown(1) And keytim=0 
	   If fed=0 Then scrollSpeed=2 Else scrollSpeed=4
	   If subFoc=20 Then scroll=scroll+32 : PlaySound sMenuBrowse : keytim=scrollSpeed
	   If subFoc=21 Then scroll=scroll-32 : PlaySound sMenuBrowse : keytim=scrollSpeed
	  EndIf
	  If scroll>0 Then scroll=0
	  If scroll<-((no_boxes-no_displays)*32) Then scroll=-((no_boxes-no_displays)*32)
	  If foc=>1 And foc=<no_boxes And foc<>oldfoc
	   If foc>no_displays And scroll>-((foc-no_displays)*32) Then scroll=-((foc-no_displays)*32)
	   If scroll<-((foc-1)*32) Then scroll=-((foc-1)*32) 
	  EndIf
     EndIf 
	EndIf
	
 UpdateWorld
 Next
 RenderWorld 1

 ;DISPLAY
 DrawImage gBackground,rX#(400),rY#(300)
 If foc=>1 And foc=<no_boxes And fedRank(fed,foc)=<no_chars
  DrawImage gFed(charFed(fedRank(fed,foc))),rX#(400),rY#(60)
 Else
  If fed=0 Then DrawImage gLogo(2),rX#(400),rY#(60)
  If fed>0 Then DrawImage gFed(fed),rX#(400),rY#(60)
 EndIf
 ;profile
 If foc=>1 And foc=<no_boxes And fedRank(fed,foc)=<no_chars
  DrawProfile(fedRank(fed,foc),-1,-1,0)
 EndIf
 ;scrollers
 subFoc=-1
 If no_boxes>no_displays 
  If scroll<0
   If optGrid=1 Then y=105 Else y=150
   DrawImage gScroll(1),rX#(400)-360,rY#(y)
   DrawImage gScroll(1),rX#(400)+360,rY#(y)
   If MouseY()>rY#(y)-20 And MouseY()<rY#(y)+20
    If MouseX()>(rX#(400)-360)-20 And MouseX()<(rX#(400)-360)+15 Then subFoc=20
    If MouseX()>(rX#(400)+360)-20 And MouseX()<(rX#(400)+360)+15 Then subFoc=20
   EndIf
   If subFoc=20
    DrawImage gScroll(3),rX#(400)-360,rY#(y)
    DrawImage gScroll(3),rX#(400)+360,rY#(y)
   EndIf
  EndIf
  excess=Int(((no_boxes-no_displays)-1)/4)+1
  If (optGrid=0 And scroll>-((no_boxes-no_displays)*32)) Or (optGrid=1 And scroll>-(excess*35))
   If optGrid=1 Then y=550 Else y=505
   DrawImage gScroll(2),rX#(400)-360,rY#(y)
   DrawImage gScroll(2),rX#(400)+360,rY#(y)
   If MouseY()>rY#(y)-20 And MouseY()<rY#(y)+20
    If MouseX()>(rX#(400)-360)-15 And MouseX()<(rX#(400)-360)+20 Then subFoc=21
    If MouseX()>(rX#(400)+360)-15 And MouseX()<(rX#(400)+360)+20 Then subFoc=21
   EndIf
   If subFoc=21
    DrawImage gScroll(4),rX#(400)-360,rY#(y)
    DrawImage gScroll(4),rX#(400)+360,rY#(y)
   EndIf
  EndIf
 EndIf
 ;CHARACTER BOXES
 If optGrid=0 Then x=rX#(400)-200 : listY=rY#(150)
 If optGrid=1 Then x=rX#(400)-285 : listY=rY#(150)
 no_relates=0 
 sourceX=0 : sourceY=0
 targetX=0 : targetY=0
 For rank=1 To no_boxes
  char=fedRank(fed,rank)
  y=listY+scroll
  If char>0 And y=>rY#(150) And y=<rY#(510)
   ;mouse hotspots
   If optGrid=1
    If MouseX()=>x-91 And MouseX()=<x+91 And MouseY()=>y-15 And MouseY()=<y+14 Then foc=rank
   Else
    If MouseX()=>x-155 And MouseX()=<x+545 And MouseY()=>y-15 And MouseY()=<y+14
     foc=rank
     ;If MouseX()=<x-105 Then subFoc=0
     statX=x+110
     For count=1 To 11
      If MouseX()=>statX-12 And MouseX()=<statX+14 Then subFoc=count
      If count=11 And MouseX()=>statX-28 And MouseX()=<statX+30 Then subFoc=count
      statX=statX+28
      If count=10 Then statX=statX+16
     Next
     If MouseX()=>x+440 Then subFoc=12
    EndIf
   EndIf
   ;rank
   If optGrid=0 And char=<no_chars
    r=255 : g=255 : b=255
    If foc<>rank Or subFoc=>20 Then r=r-(r/4) : g=g-(g/4) : b=b-(b/4)
    SetFont fontNews(8)
    If rank=>10 Then SetFont fontNews(6)
    If rank=>100 Then SetFont fontNews(4)
    namer$="#"+rank
    OutlineStraight(namer$,(x-101)-StringWidth(namer$),y,0,0,0,r,g,b)
   EndIf
   ;name box
   If foc=rank And subFoc<20 Then highlight=1 Else highlight=0
   DrawImage gMenu(6-highlight),x,y+1
   If charTrainCourse(char)>0 Then DrawImage gMenu(14-highlight),x,y+1
   If charVacant(char)>0 Then DrawImage gMenu(12-highlight),x,y+1
   If InjuryStatus(char)>0 Then DrawImage gMenu(10-highlight),x,y+1
   If charSelected(char)=0 And no_plays>1 Then Color 200,0,0 : Rect x-90,y-16,179,28,1
   If charSelected(char)=1 And no_plays>1 Then Color 0,0,200 : Rect x-90,y-16,179,28,1
   If charSelected(char)=2 And no_plays>1 Then Color 175,0,175 : Rect x-90,y-16,179,28,1 
   If screenAgenda=7 And charRelationship(editChar,char)<0 Then Color 225,0,0 : Rect x-90,y-16,179,28,1
   If screenAgenda=7 And charRelationship(editChar,char)>0 Then Color 0,225,0 : Rect x-90,y-16,179,28,1
   If screenAgenda=8 And charRealRelationship(editChar,char)<0 Then Color 225,0,0 : Rect x-90,y-16,179,28,1
   If screenAgenda=8 And charRealRelationship(editChar,char)>0 Then Color 0,225,0 : Rect x-90,y-16,179,28,1
   If cloner>0 And cloner=rank Then Color 255,245,0 : Rect x-90,y-16,179,28,1
   If KeyDown(19) And foc=>1 And foc=<no_boxes And foc<>rank And fed=charFed(fedRank(fed,foc))
    If charRelationship(char,fedRank(fed,foc))>0 Then Color 250,100,125 : Rect x-90,y-16,179,28,1
    If charRelationship(char,fedRank(fed,foc))<0 Then Color 250,Rnd(75,150),0 : Rect x-90,y-16,179,28,1
    If charRelationship(char,fedRank(fed,foc))<-4 Then Color 225,75,0 : Rect x-90,y-16,179,28,1
   EndIf
   DrawImage gMenu(8-highlight),x,y+1
   DrawImage gPortrait,x,y+1
   DrawImageRect charPhoto(char),x-48,(y+64)-11,28,(64-PortraitHead(char))-10,21,21
   If optGrid=1 And char=<no_chars
    Color 0,0,0 : Rect x-65,y+6,102,5,1
    Color 225,0,0 : Rect x-64,y+7,100,3,1
    Color 125,0,0 : Rect x-64,y+7,100,3,0
    If game=0 And screenAgenda=0 Then meter=100 Else meter=charHealth(char)
    ;If charTrainCourse(char)>0 Then Color 50,120,250 Else Color 0,225,0
    Color 0,225,0
    Rect x-64,y+7,meter,3,1
    If charTrainCourse(char)>0 Then Color 5,70,190 Else Color 0,125,0
    Rect x-64,y+7,meter,3,0
   EndIf
   If charTrainCourse(char)>0 Then DrawImage gTraining,x-76,y
   If char=fedCupHolder(charFed(char)) Then DrawImage gCup,x-83,y+5
   DisplayBelts(char,x-77,y+8)  
   If char=fedBooker(charFed(char)) Then DrawImage gCrown,x,y-15
   If charVacant(char)>0 And InjuryStatus(char)=0 
    DrawImage gVacant,x-76,y
    SetFont fontStat(1)
    Outline(charVacant(char),x-75,y,0,0,0,185,250,170)
   EndIf
   If InjuryStatus(char)>0 
    DrawImage gInjured,x-75,y-1
    SetFont fontStat(1)
    Outline(InjuryStatus(char),x-75,y-1,0,0,0,250,165,165)
   EndIf
   If charSelected(char)=>0 And no_plays>1
    DrawImage gControl(charSelected(char)),x-67,y+1
    If screenAgenda=11
     For v=1 To optCupSize
      If optCupTeams=0 And char=pChar(v) Then SetFont font(4) : Outline("P"+v,x-75,y+1,0,0,0,250,200,100)
      If optCupTeams=1 And (char=pChar((v*2)-1) Or char=pChar(v*2)) Then SetFont font(4) : Outline("T"+v,x-75,y+1,0,0,0,250,200,100)
     Next
    Else
     If matchTeams>0
      For v=1 To no_wrestlers
       If char=pChar(v) And v=<no_wrestlers/2 Then SetFont font(4) : Outline("T1",x-75,y+1,0,0,0,250,200,100)
       If char=pChar(v) And v>no_wrestlers/2 Then SetFont font(4) : Outline("T2",x-75,y+1,0,0,0,250,200,100)
      Next
     Else
      For v=1 To no_wrestlers
       If char=pChar(v) Then SetFont font(4) : Outline("P"+v,x-75,y+1,0,0,0,250,200,100)
      Next
     EndIf
     If no_refs>0
      For v=1 To no_refs
       If char=pChar(no_wrestlers+v) Then SetFont font(4) : Outline("R"+v,x-75,y+1,0,0,0,250,200,100)
      Next
     EndIf
    EndIf
   EndIf
   SqueezeFont(charName$(char),140,18)
   r=200 : g=200 : b=200
   If foc=rank And subFoc<20 Then r=255 : g=255 : b=255
   Outline(charName$(char),x+11,y,0,0,0,r,g,b)
   ;statistics
   If optGrid=0 And char=<no_chars
    statX=x+110
    For count=1 To 11
     DrawStatBox(rank,char,count,statX,y)
     statX=statX+28
     If count=10 Then statX=statX+16
    Next
   EndIf
   ;health meter
   healthX=x+440
   If optGrid=0 And char=<no_chars
    Color 0,0,0 : Rect healthX+1,y-4,100,10,1
    Color 225,0,0 : Rect healthX,y-5,100,10,1
    Color 125,0,0 : Rect healthX,y-5,100,10,0
    If game=0 And screenAgenda=0 Then meter=100 Else meter=charHealth(char) 
    ;If charTrainCourse(char)>0 Then Color 50,120,250 Else Color 0,225,0
    Color 0,225,0
    Rect healthX,y-5,meter,10,1
    If charTrainCourse(char)>0 Then Color 5,70,190 Else Color 0,125,0
    Rect healthX,y-5,meter,10,0 
    If foc=rank And subFoc=12
     Color 0,0,0 : Rect healthX-1,y-6,102,12,0
     Color 255,240,100 : Rect healthX-2,y-7,104,14,0
     Color 0,0,0 : Rect healthX-3,y-8,106,16,0
    EndIf
    If (game=1 Or screenAgenda<>0) And InjuryStatus(char)>0
     DrawImage gInjured,healthX+8,y
     SetFont fontStat(1)
     OutlineStraight(InjuryStatus(char),healthX+20,y-1,0,0,0,250,150,150)
     offset=1+StringWidth(Str(InjuryStatus(char)))
     SetFont fontStat(0)
     OutlineStraight(" weeks",(healthX+20)+offset,y-2,0,0,0,250,150,150)
    EndIf
   EndIf
   ;cloning data
   If cloner=rank Then sourceX=x : sourceY=y 
   If foc=rank And subFoc<20 
    targetX=x : targetY=y 
    SetFont font(1)
    ;If cloner>0 And cloner<>rank And screenAgenda=1 Then Outline("RIGHT CLICK TO CLONE",x,y+12,0,0,0,255,240,100)
    If cloner>0 And cloner<>rank Then Outline("RIGHT CLICK TO SWITCH",x,y+13,0,0,0,255,240,100)
    If cloner>0 And cloner=rank Then Outline("RIGHT CLICK TO CANCEL",x,y+13,0,0,0,255,240,100)
   EndIf
   ;relationship data
   If optGrid=1 And foc=>1 And foc=<no_boxes And fed=charFed(fedRank(fed,foc))
    If foc=rank   
     relateX(0)=x : relateY(0)=y
    Else 
     If charRelationship(char,fedRank(fed,foc))<>0
      no_relates=no_relates+1
      relateX(no_relates)=x : relateY(no_relates)=y 
      If charRelationship(char,fedRank(fed,foc))>0 Then relateType(no_relates)=1
      If charRelationship(char,fedRank(fed,foc))<0 Then relateType(no_relates)=-1
      If charRelationship(char,fedRank(fed,foc))<-4 Then relateType(no_relates)=-2
     EndIf
    EndIf
   EndIf
  EndIf
  ;increment
  If optGrid=1
   x=x+192
   If x>rX#(400)+400 Then x=rX#(400)-285 : listY=listY+35
  Else 
   listY=listY+32
  EndIf
 Next
 ;plot clone line
 If cloner>0 And foc<>cloner And targetY>0 
  If optGrid=1
   DrawLine(sourceX,sourceY,targetX,targetY,255,240,100)
  Else
   DrawLine(sourceX,sourceY,sourceX-87,GetCentre#(sourceY,targetY),255,240,100)
   DrawLine(sourceX-87,GetCentre#(sourceY,targetY),targetX,targetY,255,240,100)
  EndIf
 EndIf
 ;plot relationship lines
 If KeyDown(19) And no_relates>0
  For count=1 To no_relates 
   If relateType(count)=1 Then r=250 : g=100 : b=125
   If relateType(count)=-2 Then r=250 : g=90 : b=0
   If relateType(count)=-1 Then r=250 : g=Rnd(50,200) : b=0
   DrawLine(relateX(0),relateY(0),relateX(count),relateY(count),r,g,b)
  Next
 EndIf
 ;stat headers
 If optGrid=0 And no_boxes>0
  x=(rX#(400)-200)+110 : y=rY#(150)
  For stat=1 To 11
   DrawImage gStat(stat),(x-5)+(ImageWidth(gStat(stat))/2),(y-15)-(ImageHeight(gStat(stat))/2) 
   x=x+28
   If stat=10 Then x=x+16
  Next
 EndIf
 ;empty message
 If no_boxes=0
  SetFont font(5)
  Outline("This pool doesn't contain any characters!",rX#(400),rY#(285),0,0,0,255,255,255)
  Outline("Please exit and choose another...",rX#(400),rY#(315),0,0,0,255,255,255)
 EndIf
 ;selection reminder
 If no_boxes>0 And no_plays>1
  SetFont font(4)
  chosen=CountSelected()
  If screenAgenda=0
   If chosen<no_wrestlers
    Outline(chosen+"/"+no_wrestlers+" Wrestlers",rX#(400),rY#(555),0,0,0,200,200,200)
   EndIf
   If chosen=>no_wrestlers And chosen<no_plays And no_refs>0
    Outline((chosen-no_wrestlers)+"/"+no_refs+" Referees",rX#(400),rY#(555),0,0,0,200,200,200)
   EndIf
  Else
   If chosen<no_plays
    Outline(chosen+"/"+no_plays+" Wrestlers",rX#(400),rY#(555),0,0,0,200,200,200)
   EndIf
  EndIf
  If chosen=no_plays Then DrawOption(0,rX#(400),rY#(560),">>> PROCEED >>>","")
 Else
  DrawOption(0,rX#(400),rY#(560),"<<< BACK <<<","") 
 EndIf
 ;diagnostic
 ;SetFont font(2)
 ;Outline("editFilter: "+editFilter,rX#(100),rY#(585),0,0,0,255,255,255)
 ;y=200
 ;For count=1 To fedSize(fed)
  ;Outline(count+". "+fedRoster(fed,count)+" ("+charFed(fedRoster(fed,count))+")",rX#(400),rY#(y),0,0,0,0,255,0)
  ;y=y+15
 ;Next
 ;cursor
 If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect
 If subFoc<>oldSubFoc Then oldSubFoc=subFoc : PlaySound sMenuSelect
 DrawImage gCursor,MouseX(),MouseY()
 If screenAgenda=>7 And screenAgenda=<8 Then DrawImage charPhoto(editChar),MouseX()+40,MouseY()+25

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
FreeTimer timer
If go=-1 Then PlaySound sMenuBack Else PlaySound sMenuGo
editFoc=foc : editScroll=scroll
If screenAgenda=0 ;playing
 If game=1
  If go=-1 Then screen=17
  If go=1 Then screen=50
 Else
  If go=-1 Then screen=13
  If go=1 Then screen=50
 EndIf
EndIf
If screenAgenda=1 And game=0 ;editing (universe)
 If go=-1 Then screen=11
 If go=1 Then editChar=fedRank(fed,foc) : editFed=fed : screen=51
EndIf
If screenAgenda=1 And game=1 ;editing (career)
 If go=-1 Then screen=20
 If go=1 Then editChar=fedRank(fed,foc) : editFed=fed : screen=51
EndIf
If screenAgenda=2 ;studying
 If go=-1 Then screen=11
 If go=1 Then screen=11
EndIf
If screenAgenda=3 ;buying
 If go=-1 Then screen=11
 If go=1 Then negChar=fedRank(fed,foc) : screen=52
EndIf
If screenAgenda=4 ;selling
 If go=-1 Then screen=20
 If go=1 Then negChar=fedRank(fed,foc) : screen=11
EndIf
If screenAgenda=6 ;training
 If go=-1 Then screen=20
 If go=1 Then editChar=fedRank(fed,foc) : screen=54
EndIf
If screenAgenda=7 Or screenAgenda=8 ;setting relationships
 If go=-1 Then screen=11
 If go=1 Then screen=11
EndIf
;tournament setup
If screenAgenda=11
 If go=-1 Then screen=16
 If go=1
  For cyc=1 To optCupSize
   If optCupTeams=1
    cupChar(cupSlot,cyc)=pChar((cyc*2)-1)
    cupCharControl(cupSlot,cupChar(cupSlot,cyc))=charSelected(pChar((cyc*2)-1))
    cupCharPartner(cupSlot,cupChar(cupSlot,cyc))=pChar(cyc*2)
   Else
    cupChar(cupSlot,cyc)=pChar(cyc)
    cupCharControl(cupSlot,cupChar(cupSlot,cyc))=charSelected(pChar(cyc))
   EndIf
  Next 
  GenerateTournament(optCupFed,optCupTeams,optCupReward,optCupSize,optCupSelect,0,1)
  screen=15
 EndIf
EndIf
End Function

;//////////////////////////////////////////////////////////////////////////////
;----------------------------- 14. MATCH SETUP --------------------------------
;//////////////////////////////////////////////////////////////////////////////
Function MatchSetup()
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
foc=13 : oldfoc=foc
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
	If gotim>20 
	 ;leave
	 If KeyDown(1) Then go=-1
	 ;proceed
	 If KeyDown(28) Or ButtonPressed() Or MouseDown(1)
	  If foc<13 And MouseDown(1)=0 Then foc=13 : keytim=10 
	  If foc=13 And keytim=0 Then go=1
	  If foc=14 Then go=-1 
	 EndIf
	EndIf
	;music
	ManageMusic(-1)
	
	;CONFIGURATION
	oldMatch=matchPreset 
	If keytim=0
	 ;highlight options
	 If KeyDown(200) Or JoyYDir()=-1 Then foc=foc-1 : keytim=5
	 If KeyDown(208) Or JoyYDir()=1 Then foc=foc+1 : keytim=5
	 If foc>13 Then foc=1
	 If foc<1 Then foc=13
	 ;browse left
	 If KeyDown(203) Or JoyXDir()=-1 Or MouseDown(2)
	  If foc=>1 And foc=<12 Then PlaySound sMenuBrowse : keytim=5
	  If foc=1 Then matchPreset=matchPreset-1
	  If foc=2 Then no_wrestlers=no_wrestlers-1
	  If foc=3 Then no_refs=no_refs-1 : keytim=4
	  If foc=4 Then matchType=matchType-1
	  If foc=5 Then matchRules=matchRules-1
	  If foc=6 Then matchTeams=matchTeams-1
      If foc=7 Then matchPins=matchPins-1
      If foc=8 Then matchSubs=matchSubs-1
      If foc=9 Then matchKOs=matchKOs-1
      If foc=10 Then matchBlood=matchBlood-1
      If foc=11 Then matchCountOuts=matchCountOuts-1
      If foc=12 Then matchTimeLim=matchTimeLim-1
	 EndIf
	 ;browse right
	 If KeyDown(205) Or JoyXDir()=1 Or MouseDown(1)
	  If foc=>1 And foc=<12 Then PlaySound sMenuBrowse : keytim=5
	  If foc=1 Then matchPreset=matchPreset+1
	  If (no_wrestlers+no_refs)<optPlayLim And ((no_wrestlers+no_refs)<fedSize(fed) Or fed=0)
	   If foc=2 Then no_wrestlers=no_wrestlers+1
	   If foc=3 Then no_refs=no_refs+1
	  EndIf
	  If foc=4 Then matchType=matchType+1
	  If foc=5 Then matchRules=matchRules+1
	  If foc=6 Then matchTeams=matchTeams+1
      If foc=7 Then matchPins=matchPins+1
      If foc=8 Then matchSubs=matchSubs+1
      If foc=9 Then matchKOs=matchKOs+1
      If foc=10 Then matchBlood=matchBlood+1
      If foc=11 Then matchCountOuts=matchCountOuts+1
      If foc=12 Then matchTimeLim=matchTimeLim+1
	 EndIf
	 ;revert to match presets
	 If KeyDown(14)
      PlaySound sMenuBrowse : keytim=10
      GetMatchRules(matchPreset)
     EndIf
	EndIf
	;check limits
	If matchPreset>no_matches Then matchPreset=0
	If matchPreset<0 Then matchPreset=no_matches
	If matchPreset<>oldMatch Then GetMatchRules(matchPreset) 
	If fed=0 And no_wrestlers>no_chars Then no_wrestlers=no_chars    
	If fed>0 And no_wrestlers>fedSize(fed) Then no_wrestlers=fedSize(fed)
	If no_wrestlers>optPlayLim Then no_wrestlers=optPlayLim  
	If no_wrestlers<2 Then no_wrestlers=2
	If matchTeams=2 And no_wrestlers>10 Then no_wrestlers=10
	If no_refs>5 Then no_refs=5
	If fed=0 And no_refs>no_chars-2 Then no_refs=no_chars-2
	If fed>0 And no_refs>fedSize(fed)-2 Then no_refs=fedSize(fed)-2 
	If no_refs<0 Then no_refs=0
	If matchType<0 Then matchType=5
	If matchType>5 Then matchType=0
	If matchRules<0 Then matchRules=0
	If matchRules>2 Then matchRules=2
	If matchTeams<-1 Then matchTeams=-1
	If matchTeams>2 Then matchTeams=2
	If matchPins<0 Then matchPins=1
	If matchPins>1 Then matchPins=0
	If matchSubs<0 Then matchSubs=1
    If matchSubs>1 Then matchSubs=0	
	If matchKOs<0 Then matchKOs=1
	If matchKOs>1 Then matchKOs=0	
	If matchBlood<0 Then matchBlood=1
	If matchBlood>1 Then matchBlood=0
	If matchCountOuts<0 Then matchCountOuts=3
	If matchCountOuts>3 Then matchCountOuts=0
	If matchTeams=-1
     If matchTimeLim<1 Then matchTimeLim=1 
	 If matchTimeLim>10 Then matchTimeLim=10	
    Else
     If matchTimeLim<0 Then matchTimeLim=0 
	 If matchTimeLim>60 Then matchTimeLim=60	
    EndIf 
	
 UpdateWorld
 Next
 RenderWorld 1

 ;DISPLAY
 DrawImage gBackground,rX#(400),rY#(300)
 If fed=0 Then DrawImage gLogo(2),rX#(400),rY#(60)
 If fed>0 Then DrawImage gFed(fed),rX#(400),rY#(60)
 ;options
 y=140
 DrawOption(1,rX#(400),rY#(y),"Preset",textMatch$(matchPreset)) : y=y+37
 DrawOption(2,rX#(400),rY#(y),"No. Wrestlers",no_wrestlers) : y=y+32
 DrawOption(3,rX#(400),rY#(y),"No. Referees",no_refs) : y=y+32
 DrawOption(4,rX#(400),rY#(y),"Aim",textAim$(matchType)) : y=y+32
 DrawOption(5,rX#(400),rY#(y),"Rules",textRules$(matchRules)) : y=y+32
 If matchTeams=-1 Then namer$="Intervals" Else namer$=textTeams$(matchTeams)
 DrawOption(6,rX#(400),rY#(y),"Format",namer$) : y=y+37
 DrawOption(7,rX#(400),rY#(y),"Pins",textCount$(matchPins)) : y=y+32
 DrawOption(8,rX#(400),rY#(y),"Submissions",textCount$(matchSubs)) : y=y+32
 DrawOption(9,rX#(400),rY#(y),"Knock-Out's",textCount$(matchKOs)) : y=y+32
 DrawOption(10,rX#(400),rY#(y),"Bleeding",textCount$(matchBlood)) : y=y+32
 DrawOption(11,rX#(400),rY#(y),"Count-Out's",textCountOuts$(matchCountOuts)) : y=y+36
 If matchTeams=-1 Then header$="Entrance Interval" Else header$="Time Limit"
 If matchTimeLim=0 Then namer$="None"
 If matchTimeLim=1 Then namer$=matchTimeLim+"min"
 If matchTimeLim>1 Then namer$=matchTimeLim+"mins"
 DrawOption(12,rX#(400),rY#(y),header$,namer$)
 DrawOption(13,rX#(400),rY#(560),">>> PROCEED >>>","") 
 ;cursor
 If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect  
 DrawImage gCursor,MouseX(),MouseY()

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
FreeTimer timer
If go=1 Then PlaySound sMenuGo Else PlaySound sMenuBack 
If game=1
 If go=-1 Then screen=20
 If go=1 Then screen=17
Else
 If go=-1 Then screen=11
 If go=1 Then screen=17
EndIf
End Function

;//////////////////////////////////////////////////////////////////////////////
;----------------------------- 17. GIMMICK SETUP --------------------------------
;//////////////////////////////////////////////////////////////////////////////
Function GimmickSetup()
;forecast cage
If matchPreset=18 And matchCage=0 Then matchCage=1
;forecast promos
If game=1 And matchPromo=0 And fedProduction(charFed(gamChar),1)>0
 If (matchPreset=>3 And matchPreset=<9) Or (matchPreset=>13 And matchPreset=<14) Or (matchPreset=>17 And matchPreset=<18)
  randy=Rnd(0,5) 
  If randy=<1 And no_refs>0 Then promoFoc=1 ;match summary
  If randy=2 And no_wrestlers=2 Then promoFoc=91 ;rule debate
 EndIf 
 randy=Rnd(0,1)
 If randy=0 And gamSchedule(gamDate)=5 Then promoFoc=14 ;charity
 If randy=1 And gamSchedule(gamDate)=6 Then promoFoc=13 ;tribute
EndIf
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
foc=13 : oldfoc=foc
go=0 : gotim=0 : keytim=20
While go=0

 Cls
 frames=WaitTimer(timer)
 For framer=1 To frames
	
	;timers
	keytim=keytim-1
	If keytim<1 Then keytim=0
	flashTim=flashTim+1
	If flashTim>30 Then flashTim=0 
	
	;PORTAL
    gotim=gotim+1
	If gotim>20 
	 ;leave
	 If KeyDown(1) Then go=-1
	 ;proceed
	 If KeyDown(28) Or ButtonPressed() Or MouseDown(1) 
	  If foc<13 And MouseDown(1)=0 Then foc=13 : keytim=10
	  If foc=13 And keytim=0 Then go=1
	  If foc=14 Then go=-1 
	 EndIf
	EndIf
	;music
	ManageMusic(-1)
	
	;CONFIGURATION
	If keytim=0
	 ;highlight option
	 If KeyDown(200) Or JoyYDir()=-1 Then foc=foc-1 : keytim=5
	 If KeyDown(208) Or JoyYDir()=1 Then foc=foc+1 : keytim=5
	 If foc>13 Then foc=1
	 If foc<1 Then foc=13
	 ;browse left
	 If KeyDown(203) Or JoyXDir()=-1 Or MouseDown(2)
	  If foc=>1 And foc=<12 Then PlaySound sMenuBrowse : keytim=5
	  If foc=1 And (fedProduction(charFed(gamChar),10)>0 Or game=0) Then matchCage=matchCage-1
	  If foc=2 And (fedProduction(charFed(gamChar),10)>0 Or game=0) Then matchRopes=matchRopes-1
	  If foc=3 And (fedProduction(charFed(gamChar),9)>0 Or game=0) Then matchBlastTim=matchBlastTim-1 : keytim=3
	  If foc=4 And (fedProduction(charFed(gamChar),11)>0 Or game=0) Then no_items=no_items-1 : keytim=3
	  If foc=5 And (fedProduction(charFed(gamChar),11)>0 Or game=0) Then itemSelection=itemSelection-1
	  If foc=6 And (fedProduction(charFed(gamChar),11)>0 Or game=0) Then itemLayout=itemLayout-1
	  If foc=7 And (fedProduction(charFed(gamChar),12)>0 Or game=0) Then no_weaps=no_weaps-1 : keytim=3 
	  If foc=8 And (fedProduction(charFed(gamChar),12)>0 Or game=0) Then weapSelection=weapSelection-1
	  If foc=9 And (fedProduction(charFed(gamChar),12)>0 Or game=0) Then weapLayout=weapLayout-1
	  If foc=10
	   Repeat  
	    matchChamps=matchChamps-1
	    If matchChamps<0 Then matchChamps=7
	   Until RewardApplicable(matchChamps)=1
      EndIf
	  If foc=11 And (fedProduction(charFed(gamChar),1)>0 Or game=0)
	   temp=promoFoc : keytim=8
	   If temp=0 Then promoFoc=91
	   If temp=>1 And temp=<30 Then promoFoc=0
	   If temp=>31 And temp=<60 Then promoFoc=1
	   If temp=>61 And temp=<90 Then promoFoc=31
	   If temp=>91 Then promoFoc=61
	  EndIf
	  If foc=12 And (fedProduction(charFed(gamChar),1)>0 Or game=0)
	   Repeat  
	    promoFoc=promoFoc-1
	    If promoFoc<0 Then promoFoc=200
	   Until promoLib(promoFoc)=>0 And promoLocked(Abs(promoLib(promoFoc)))=0
	  EndIf
	 EndIf
	 ;browse right
	 If KeyDown(205) Or JoyXDir()=1 Or MouseDown(1)
	  If foc=>1 And foc=<12 Then PlaySound sMenuBrowse : keytim=5
	  If foc=1 And (fedProduction(charFed(gamChar),10)>0 Or game=0) Then matchCage=matchCage+1
	  If foc=2 And (fedProduction(charFed(gamChar),10)>0 Or game=0) Then matchRopes=matchRopes+1
	  If foc=3 And (fedProduction(charFed(gamChar),9)>0 Or game=0) Then matchBlastTim=matchBlastTim+1 : keytim=3
	  If foc=4 And (fedProduction(charFed(gamChar),11)>0 Or game=0) Then no_items=no_items+1 : keytim=3
	  If foc=5 And (fedProduction(charFed(gamChar),11)>0 Or game=0) Then itemSelection=itemSelection+1
	  If foc=6 And (fedProduction(charFed(gamChar),11)>0 Or game=0) Then itemLayout=itemLayout+1
	  If foc=7 And (fedProduction(charFed(gamChar),12)>0 Or game=0) Then no_weaps=no_weaps+1 : keytim=3 
	  If foc=8 And (fedProduction(charFed(gamChar),12)>0 Or game=0) Then weapSelection=weapSelection+1
	  If foc=9 And (fedProduction(charFed(gamChar),12)>0 Or game=0) Then weapLayout=weapLayout+1
	  If foc=10
	   Repeat  
	    matchChamps=matchChamps+1
	    If matchChamps>7 Then matchChamps=0
	   Until RewardApplicable(matchChamps)=1
      EndIf
	  If foc=11 And (fedProduction(charFed(gamChar),1)>0 Or game=0) 
	   temp=promoFoc : keytim=8
	   If temp=0 Then promoFoc=1
	   If temp=>1 And temp=<30 Then promoFoc=31
	   If temp=>31 And temp=<60 Then promoFoc=61
	   If temp=>61 And temp=<90 Then promoFoc=91
	   If temp=>91 Then promoFoc=0
	  EndIf
	  If foc=12 And (fedProduction(charFed(gamChar),1)>0 Or game=0)
	   Repeat  
	    promoFoc=promoFoc+1
	    If promoFoc>200 Then promoFoc=0
	   Until promoLib(promoFoc)=>0
	  EndIf
	 EndIf 
	 ;randomization
	 If KeyDown(57)
	  If (foc=11 Or foc=12) And (fedProduction(charFed(gamChar),1)>0 Or game=0)
	   PlaySound sMenuBrowse : keytim=5
	   Repeat  
	    promoFoc=Rnd(1,200)
	   Until promoLib(promoFoc)=>0
	  EndIf
	 EndIf
	 ;reverts
	 If KeyDown(14)
	  PlaySound sMenuBrowse : keytim=10
	  If foc=1 Then matchCage=0
	  If foc=2 Then matchRopes=0
	  If foc=3 Then matchBlastTim=0
	  If foc=4 Then no_items=0
	  If foc=5 Then itemSelection=1
	  If foc=6 Then itemLayout=1
	  If foc=7 Then no_weaps=0
	  If foc=8 Then weapSelection=1
	  If foc=9 Then weapLayout=1
	  If foc=10 Then matchChamps=0
	  If foc=11 Or foc=12 Then promoFoc=0
	 EndIf 
	EndIf
	;check limits
	If matchCage<0 Then matchCage=4
	If matchCage>4 Then matchCage=0
	If game=1 And fedProduction(charFed(gamChar),10)=0 Then matchCage=0
	If matchRopes<0 Then matchRopes=3
	If matchRopes>3 Then matchRopes=0
	If game=1
	 If fedProduction(charFed(gamChar),10)=0 Then matchRopes=0 
	 If matchRopes=>2 Then arenaRopes=0 Else arenaRopes=gamRopes(gamDate)
	EndIf
	If matchBlastTim<0 Then matchBlastTim=0
	If matchTimeLim>0 And matchBlastTim>matchTimeLim Then matchBlastTim=matchTimeLim
	If matchBlastTim>60 Then matchBlastTim=60
	If game=1 And fedProduction(charFed(gamChar),9)=0 Then matchBlastTim=0
	If no_items<0 Then no_items=0
	If no_items>itemLimit Then no_items=itemLimit
	If itemSelection<0 Then itemSelection=itemList+1 
	If itemSelection>itemList+1 Then itemSelection=0
	If itemLayout<0 Then itemLayout=5
	If itemLayout>5 Then itemLayout=0 
	If game=1 And fedProduction(charFed(gamChar),11)=0 Then no_items=0 : itemSelection=1 : itemLayout=1
	If no_weaps<0 Then no_weaps=0
	If no_weaps>weapLimit Then no_weaps=weapLimit
	If weapSelection<0 Then weapSelection=weapList+1
	If weapSelection>weapList+1  Then weapSelection=0 
	If weapLayout<0 Then weapLayout=5
	If weapLayout>5 Then weapLayout=0 
	If game=1 And fedProduction(charFed(gamChar),12)=0 Then no_weaps=0 : weapSelection=1 : weapLayout=1 
	If matchChamps<0 Then matchChamps=7
	If matchChamps>7 Then matchChamps=0
	matchPromo=promoLib(promoFoc)
	
 UpdateWorld
 Next
 RenderWorld 1

 ;DISPLAY
 DrawImage gBackground,rX#(400),rY#(300)
 If fed=0 Then DrawImage gLogo(2),rX#(400),rY#(60)
 If fed>0 Then DrawImage gFed(fed),rX#(400),rY#(60)
 ;options
 warning$="" : warnX=0 : warnY=0 
 y=140
 DrawOption(1,rX#(400),rY#(y),"Cage",textCage$(matchCage)) : y=y+32
 DrawOption(2,rX#(400),rY#(y),"Rope Type",textRopeType$(matchRopes)) : y=y+32
 If matchBlastTim=0 Then namer$="None" Else namer$=matchBlastTim+"mins"
 DrawOption(3,rX#(400),rY#(y),"Blast Time",namer$) : y=y+37
 DrawOption(4,rX#(400),rY#(y),"No. Items",no_items) : y=y+32
 If itemSelection>1 Then namer$="Mostly "+iName$(itemSelection-1)+"s" Else namer$=textSelection$(itemSelection)
 DrawOption(5,rX#(400),rY#(y),"Item Selection",namer$) : y=y+32
 If itemLayout=5 And itemSelection>1 Then namer$=iName$(itemSelection-1)+"s Inside" Else namer$=textLayout$(itemLayout)
 DrawOption(6,rX#(400),rY#(y),"Item Layout",namer$) : y=y+36
 DrawOption(7,rX#(400),rY#(y),"No. Weapons",no_weaps) : y=y+32
 If weapSelection>1 Then namer$="Mostly "+weapName$(weapSelection-1)+"s" Else namer$=textSelection$(weapSelection)
 DrawOption(8,rX#(400),rY#(y),"Weapon Selection",namer$) : y=y+32
 If weapLayout=5 And weapSelection>1 Then namer$=weapName$(weapSelection-1)+"s Inside" Else namer$=textLayout$(weapLayout)
 DrawOption(9,rX#(400),rY#(y),"Weapon Layout",namer$) : y=y+37
 DrawOption(10,rX#(400),rY#(y),"Reward",textReward$(matchChamps)) : y=y+32
 namer$="None"
 If promoFoc=>1 And promoFoc=<30 Then namer$="Announcements"
 If promoFoc=>31 And promoFoc=<60 Then namer$="Championships"
 If promoFoc=>61 And promoFoc=<90 Then namer$="Relationships"
 If promoFoc=>91 Then namer$="Arguments"
 DrawOption(11,rX#(400),rY#(y),"Promo Category",namer$) : y=y+32
 DrawOption(12,rX#(400),rY#(y),"Promo Script",promoTitle$(matchPromo))
 SetFont fontStat(1)
 If foc=12 And matchPromo>0 Then Outline(promoExplain$(matchPromo),rX#(400),rY#(y)+14,0,0,0,255,200,150)
 DrawOption(13,rX#(400),rY#(560),">>> PROCEED >>>","")  
 ;warning text
 If warnX>0 And warnY>0
  SetFont font(2)
  If flashTim=<20 Then Outline(warning$,warnX,warnY,0,0,0,255,50,50)
 EndIf
 ;cursor
 If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect  
 DrawImage gCursor,MouseX(),MouseY()

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
FreeTimer timer
If go=1 Then PlaySound sMenuGo Else PlaySound sMenuBack 
If game=1
 If go=-1 Then screen=14
 If go=1 Then screen=12
Else
 If go=-1 Then screen=14
 If go=1 Then screen=13
EndIf
End Function

;//////////////////////////////////////////////////////////////////////////////
;----------------------------- 13. ARENA SETUP --------------------------------
;//////////////////////////////////////////////////////////////////////////////
Function ArenaSetup()
;initial settings
If optFog=0 Then arenaAtmos=0
If game=0 And matchRopes>0 Then arenaRopes=0
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
foc=10 : oldfoc=foc
go=0 : gotim=0 : keytim=20
While go=0

 Cls
 frames=WaitTimer(timer)
 For framer=1 To frames
	
	;timers
	keytim=keytim-1
	If keytim<1 Then keytim=0
	flashTim=flashTim+1
	If flashTim>30 Then flashTim=0 
	
	;PORTAL
    gotim=gotim+1
	If gotim>20 And keytim=0
	 ;leave
	 If KeyDown(1) Then go=-1
	 ;proceed
	 If KeyDown(28) Or ButtonPressed() Or MouseDown(1)
	  If foc<10 And MouseDown(1)=0 Then foc=10 : keytim=10
	  If foc=10 And keytim=0 Then go=1
	  If foc=11 Then go=-1
	 EndIf
	EndIf
	;music
	ManageMusic(-1)
	
	;CONFIGURATION 
	If gotim>20 And keytim=0
	 ;highlight option
	 If KeyDown(200) Or JoyYDir()=-1 Then foc=foc-1 : keytim=5
	 If KeyDown(208) Or JoyYDir()=1 Then foc=foc+1 : keytim=5 
	 ;search left
	 If KeyDown(203) Or JoyXDir()=-1 Or MouseDown(2)
	  If foc=1 And (fedProduction(charFed(gamChar),5)>0 Or game=0) Then arenaPreset=arenaPreset-1 : PlaySound sMenuBrowse : keytim=5
	  If foc=2 Then matchLocation=matchLocation-1 : PlaySound sMenuBrowse : keytim=5
	  If foc=3 And (fedProduction(charFed(gamChar),7)>0 Or game=0) Then arenaAtmos=arenaAtmos-1 : PlaySound sMenuBrowse : keytim=5
	  If foc=4 Then arenaAttendance=arenaAttendance-10 : PlaySound sMenuBrowse : keytim=5
	  If foc=5 And (fedProduction(charFed(gamChar),5)>0 Or game=0) Then arenaRopes=arenaRopes-1 : PlaySound sMenuBrowse : keytim=5
	  If foc=6 And (fedProduction(charFed(gamChar),5)>0 Or game=0) Then arenaPads=arenaPads-1 : PlaySound sMenuBrowse : keytim=5
	  If foc=7 And (fedProduction(charFed(gamChar),5)>0 Or game=0) Then arenaCanvas=arenaCanvas-1 : PlaySound sMenuBrowse : keytim=5
	  If foc=8 And (fedProduction(charFed(gamChar),5)>0 Or game=0) Then arenaApron=arenaApron-1 : PlaySound sMenuBrowse : keytim=5
	  If foc=9 And (fedProduction(charFed(gamChar),5)>0 Or game=0) Then arenaMatting=arenaMatting-1 : PlaySound sMenuBrowse : keytim=5
	 EndIf
	 ;search right
	 If KeyDown(205) Or JoyXDir()=1 Or MouseDown(1)
	  If foc=1 And (fedProduction(charFed(gamChar),5)>0 Or game=0) Then arenaPreset=arenaPreset+1 : PlaySound sMenuBrowse : keytim=5
	  If foc=2 Then matchLocation=matchLocation+1 : PlaySound sMenuBrowse : keytim=5
	  If foc=3 And (fedProduction(charFed(gamChar),7)>0 Or game=0) Then arenaAtmos=arenaAtmos+1 : PlaySound sMenuBrowse : keytim=5
	  If foc=4 Then arenaAttendance=arenaAttendance+10 : PlaySound sMenuBrowse : keytim=5
	  If foc=5 And (fedProduction(charFed(gamChar),5)>0 Or game=0) Then arenaRopes=arenaRopes+1 : PlaySound sMenuBrowse : keytim=5
	  If foc=6 And (fedProduction(charFed(gamChar),5)>0 Or game=0) Then arenaPads=arenaPads+1 : PlaySound sMenuBrowse : keytim=5
	  If foc=7 And (fedProduction(charFed(gamChar),5)>0 Or game=0) Then arenaCanvas=arenaCanvas+1 : PlaySound sMenuBrowse : keytim=5
	  If foc=8 And (fedProduction(charFed(gamChar),5)>0 Or game=0) Then arenaApron=arenaApron+1 : PlaySound sMenuBrowse : keytim=5
	  If foc=9 And (fedProduction(charFed(gamChar),5)>0 Or game=0) Then arenaMatting=arenaMatting+1 : PlaySound sMenuBrowse : keytim=5
	 EndIf 
	 ;randomize
	 If KeyDown(57)
	  PlaySound sMenuBrowse : keytim=5
	  If foc=1 And (fedProduction(charFed(gamChar),5)>0 Or game=0) Then arenaPreset=Rnd(1,no_arenas)
	  If foc=2 Then matchLocation=0
	  If foc=3 And (fedProduction(charFed(gamChar),7)>0 Or game=0) Then arenaAtmos=Rnd(0,no_atmos)
	  If foc=4 Then arenaAttendance=Rnd(0,10) : arenaAttendance=arenaAttendance*10
	  If foc=5 And (fedProduction(charFed(gamChar),5)>0 Or game=0) Then arenaRopes=Rnd(1,no_ropes)
	  If foc=6 And (fedProduction(charFed(gamChar),5)>0 Or game=0) Then arenaPads=Rnd(1,3)
	  If foc=7 And (fedProduction(charFed(gamChar),5)>0 Or game=0) Then arenaCanvas=Rnd(1,no_canvases)
	  If foc=8 And (fedProduction(charFed(gamChar),5)>0 Or game=0) Then arenaApron=Rnd(1,no_aprons)
	  If foc=9 And (fedProduction(charFed(gamChar),5)>0 Or game=0) Then arenaMatting=Rnd(0,3)
	 EndIf  
	 ;reverts
	 If KeyDown(14)
	  PlaySound sMenuBrowse : keytim=10
	  If foc=1 And (fedProduction(charFed(gamChar),5)>0 Or game=0) Then arenaPreset=1
	  If foc=2 Then matchLocation=0
	  If foc=3 And (fedProduction(charFed(gamChar),7)>0 Or game=0) Then arenaAtmos=0
	  If foc=4 Then arenaAttendance=0
	  If foc=5 And (fedProduction(charFed(gamChar),5)>0 Or game=0) Then arenaRopes=1
	  If foc=6 And (fedProduction(charFed(gamChar),5)>0 Or game=0) Then arenaPads=1
	  If foc=7 And (fedProduction(charFed(gamChar),5)>0 Or game=0) Then arenaCanvas=1
	  If foc=8 And (fedProduction(charFed(gamChar),5)>0 Or game=0) Then arenaApron=1
	  If foc=9 And (fedProduction(charFed(gamChar),5)>0 Or game=0) Then arenaMatting=0
	 EndIf 
	EndIf  
	;limits
	If foc<1 Then foc=10
	If foc>10 Then foc=1 
	If game=1
	 If gamSchedule(gamDate)=>2
	  If arenaPreset<11 Then arenaPreset=no_arenas
	  If arenaPreset>no_arenas Then arenaPreset=11
	 Else
	  If arenaPreset<1 Then arenaPreset=10
	  If arenaPreset>10 Then arenaPreset=1 
	 EndIf
	Else
	 If arenaPreset<1 Then arenaPreset=no_arenas
	 If arenaPreset>no_arenas Then arenaPreset=1 
	EndIf
	If matchLocation<0 Then matchLocation=2
	If matchLocation>2 Then matchLocation=0 
	If matchTeams=-1 Or matchTeams=2 Then matchLocation=0 
	If game=1 Then matchLocation=0 
	If arenaAtmos<0 Then arenaAtmos=no_atmos
	If arenaAtmos>no_atmos Then arenaAtmos=0 
	If arenaAttendance<0 Then arenaAttendance=0
	If arenaAttendance>100 Then arenaAttendance=100 
	If game=0 And matchRopes=1
	 If arenaRopes<0 Then arenaRopes=no_ropes
	 If arenaRopes>no_ropes Then arenaRopes=0 
	Else 
	 If arenaRopes<1 Then arenaRopes=no_ropes
	 If arenaRopes>no_ropes Then arenaRopes=1	
	EndIf
	If game=0 And matchRopes=>2 Then arenaRopes=0
	If arenaPads<1 Then arenaPads=3
	If arenaPads>3 Then arenaPads=1
	If arenaCanvas<1 Then arenaCanvas=no_canvases
	If arenaCanvas>no_canvases Then arenaCanvas=1
	If arenaApron<1 Then arenaApron=no_aprons
	If arenaApron>no_aprons Then arenaApron=1
	If game=1 And gamSchedule(gamDate)=1
	 If arenaApron<4 Then arenaApron=6
	 If arenaApron>6 Then arenaApron=4
	EndIf
	If game=1 And gamSchedule(gamDate)=>2 And gamSchedule(gamDate)=<4
	 If arenaApron<7 Then arenaApron=9
	 If arenaApron>9 Then arenaApron=7
	EndIf
	If game=1 And gamSchedule(gamDate)=5
	 If arenaApron<4 Then arenaApron=6
	 If arenaApron>6 Then arenaApron=4
	EndIf
	If game=1 And gamSchedule(gamDate)=6
	 If arenaApron<16 Then arenaApron=18
	 If arenaApron>18 Then arenaApron=16
	EndIf      
	If arenaMatting<0 Then arenaMatting=3
	If arenaMatting>3 Then arenaMatting=0        
	
 UpdateWorld
 Next
 RenderWorld 1

 ;DISPLAY
 DrawImage gBackground,rX#(400),rY#(300)
 If fed=0 Then DrawImage gLogo(2),rX#(400),rY#(60)
 If fed>0 Then DrawImage gFed(fed),rX#(400),rY#(60)
 ;arena previw
 If gArena(arenaPreset)>0
  DrawImage gArena(arenaPreset),rX#(400),rY#(306)
  Color 0,0,0 : Rect rX#(400)-100,rY#(306)-50,200,100,0
 EndIf
 ;options
 warning$="" : warnX=0 : warnY=0 
 y=140
 DrawOption(1,rX#(400),rY#(y),"Arena",textArena$(arenaPreset)) : y=y+32
 DrawOption(2,rX#(400),rY#(y),"Location",textLocation$(matchLocation)) : y=y+32
 DrawOption(3,rX#(400),rY#(y),"Atmosphere",textAtmos$(arenaAtmos)) : y=y+32 
 If game=1 Then namer$="???" Else namer$=arenaAttendance+"%"
 DrawOption(4,rX#(400),rY#(y),"Attendance",namer$) : y=378
 DrawOption(5,rX#(400),rY#(y),"Ropes",textRopes$(arenaRopes)) : y=y+32
 DrawOption(6,rX#(400),rY#(y),"Turnbuckles",textBuckles$(arenaPads)) : y=y+32 
 DrawOption(7,rX#(400),rY#(y),"Canvas",textCanvas$(arenaCanvas)) : y=y+32
 DrawOption(8,rX#(400),rY#(y),"Apron",textApron$(arenaApron)) : y=y+32
 DrawOption(9,rX#(400),rY#(y),"Matting",textMatting$(arenaMatting)) : y=y+32
 DrawOption(10,rX#(400),rY#(560),">>> PROCEED >>>","")   
 ;warning text
 If warnX>0 And warnY>0
  SetFont font(2)
  If flashTim=<20 Then Outline(warning$,warnX,warnY,0,0,0,255,50,50)
 EndIf
 ;cursor
 If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect  
 DrawImage gCursor,MouseX(),MouseY()

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
FreeTimer timer
If go=1 Or game=1 Then PlaySound sMenuGo Else PlaySound sMenuBack 
If go=1 Then screen=12
If go=-1 Then screen=17
;career process
If game=1
 gamVenue(gamDate)=arenaPreset 
 gamAtmos(gamDate)=arenaAtmos
 gamAttendance(gamDate)=GenerateAttendance()
 arenaAttendance=TranslateAttendance(gamAttendance(gamDate)) 
 matchCage=0 : matchRopes=0
 gamRopes(gamDate)=arenaRopes
 gamPads(gamDate)=arenaPads
 gamCanvas(gamDate)=arenaCanvas
 gamApron(gamDate)=arenaApron
 gamMatting(gamDate)=arenaMatting
 PreviewArena()
 PostNewsProcess()
EndIf
End Function

;////////////////////////////////////////////////////////////////
;---------------------- RELATED FUNCTIONS -----------------------
;////////////////////////////////////////////////////////////////

;DRAW MENU ITEM
Function DrawOption(box,x,y,scriptA$,scriptB$)
 ;scriptA$=Chr$(3110+box)
 ;favour smaller box
 size=0
 If screen=31 And box=>1 And box=<12 Then size=-1
 If screen=52 And (box=0 Or box=7) Then size=-1
 If screen=56 And (box=0 Or box=5) Then size=-1
 ;mouse hotspots
 If scriptB$="" Then width=96 Else width=190
 If size=-1 Then width=91
 If box=>0 And (editExamine=0 Or screen<>51)
  If MouseX()=>(x-width)+1 And MouseX()=<(x+width)+1 And MouseY()=>y-15 And MouseY()=<y+14 Then foc=box
 EndIf
 ;assess highlight
 highlight=0
 If foc=box Or box=-1 Then highlight=1
 ;draw solo box
 If scriptB$=""
  If size=-1
   If highlight=1 Then DrawImage gMenu(5),x,y 
   If highlight=0 Then DrawImage gMenu(6),x,y
  Else
   If highlight=1 Then DrawImage gMenu(1),x,y 
   If highlight=0 Then DrawImage gMenu(2),x,y
  EndIf
  r=200 : g=200 : b=200
  If highlight=1 Then r=255 : g=255 : b=255
  SqueezeFont(scriptA$,175,18)
  If size=-1 Then SqueezeFont(scriptA$,170,18)
  Outline(scriptA$,x,y-1,0,0,0,r,g,b) 
 EndIf
 ;draw twin box
 If scriptB$<>""
  If highlight=1 Then DrawImage gMenu(3),x,y
  If highlight=0 Then DrawImage gMenu(4),x,y
  r=200 : g=200 : b=200
  If highlight=1 Then r=255 : g=255 : b=255
  SqueezeFont(scriptA$,175,18)
  Outline(scriptA$,x-93,y-1,0,0,0,r,g,b)
  SqueezeFont(scriptB$,175,18)
  Outline(scriptB$,x+93,y-1,0,0,0,r,g,b)
 EndIf
 ;editor warnings
 If foc=box Then EditingAdvice(x,y)
End Function

;SQUEEZE MAIN FONT INTO BOX
Function SqueezeFont(script$,width,height)
 f=16
 SetFont font(f)
 While f>0 And (StringWidth(script$)>width Or StringHeight(script$)>height)
  f=f-1 : fontNumber=f
  SetFont font(f)
 Wend
End Function

;SQUEEZE NEWS FONT INTO BOX
Function SqueezeNewsFont(script$,width,height)
 f=11
 SetFont fontNews(f)
 While f>0 And (StringWidth(script$)>width Or StringHeight(script$)>height)
  f=f-1 : fontNumber=f
  SetFont fontNews(f)
 Wend
End Function

;BUTTON PRESSED?
Function ButtonPressed()
 value=0
 For count=1 To 12
  If JoyDown(count) Then value=1
 Next
 Return value
End Function

;ATTEMPTING TO ENTER NAME?
Function EnterName()
 value=0
 For count=2 To 52
  If KeyDown(count) And count<>28 Then value=1
 Next
 Return value
End Function

;ASSIGN KEY PROCESS
Function AssignKey(current)
 value=0
 While value=0
  For v=0 To 255
   If KeyDown(v)=1 And keytim=0 
    If v<>0 And v<>1 And v<>28 And v<>25 Then value=v : screenCall=0 : PlaySound sMenuSelect : gotim=0 : keytim=20
   EndIf
  Next
  If KeyDown(1) And keytim=0 Then value=current : screenCall=0 : PlaySound sMenuBack : go=0 : gotim=0 : keytim=30
 Wend
 Return value
End Function

;ASSIGN BUTTON PROCESS
Function AssignButton(current)
 value=28
 While value=28
  For v=1 To 12
   If JoyDown(v)=1 And keytim=0 Then value=v : screenCall=0 : PlaySound sMenuSelect : gotim=0 : keytim=20
  Next
  If KeyDown(1) And keytim=0 Then value=current : screenCall=0 : PlaySound sMenuBack : go=0 : gotim=0 : keytim=30
 Wend
 Return value
End Function

;MOUSE OVER CHARACTER BOX?
Function MouseOverBox()
 value=0
 If MouseX()=>(rX#(400)-195)-91 And MouseX()=<(rX#(400)-195)+91 And MouseY()=>rY#(150)-20 And MouseY()=<rY#(510)+20 Then value=1
 If optGrid=1 Or MouseY()>rY#(560)-25 Then value=1
 Return value
End Function

;FIND ROW WITHIN GRID
Function FindRow(box,columns)
 row=Int((box-1)/4)+1
 Return row
End Function

;COUNT SELECTED CHARACTERS
Function CountSelected()
 value=0
 For char=1 To no_chars
  If charSelected(char)=>0 Then value=value+1
 Next
 Return value
End Function

;CONTROL USED?
Function ControlUsed(char,control)
 ;assigned to other characters
 value=0
 For v=1 To no_chars
  If v<>char
   If charSelected(v)=control Then value=1
   If screenAgenda=11 And optCupControl=5 And charSelected(v)>0 Then value=1
  EndIf
 Next
 ;void in all human tournament
 If screenAgenda=11 And optCupControl=>3 And optCupControl=<4 Then value=1
 Return value
End Function

;BLOCK ACCESS
Function BlockAccess()
 go=0
 If keytim=0 Then PlaySound sVoid : keytim=10
End Function

;DRAW STAT BOX
Function DrawStatBox(rank,char,category,x,y)
 ;box base
 If foc=rank And subFoc<20
  If category=<9 Then Color 150,150,185 Else Color 100,100,100
 Else
  If category=<9 Then Color 115,115,135 Else Color 50,50,50
 EndIf
 If category=11
  Rect x-29,y-13,58,26,1
 Else
  Rect x-13,y-13,26,26,1
 EndIf
 ;box border
 If foc=rank And subFoc<20
  If category=<9 Then Color 230,90,85 Else Color 230,230,230
 Else
  If category=<9 Then Color 170,70,55 Else Color 180,180,180
 EndIf
 If foc=rank And subFoc=category Then Color 255,240,100
 If category=11
  Rect x-28,y-12,56,24,0
 Else
  Rect x-12,y-12,24,24,0
 EndIf
 ;black outlines
 Color 0,0,0
 If category=11
  Rect x-29,y-13,58,26,0
  Rect x-27,y-11,54,22,0
 Else
  Rect x-13,y-13,26,26,0
  Rect x-11,y-11,22,22,0
 EndIf
 ;get figure
 stat$=0
 If category=1 Then stat$=charPopularity(char) : GetStatColour(charPopularity(char),charOldPopularity(char))
 If category=2 
  stat$=charStrength(char) : GetStatColour(charStrength(char),charOldStrength(char))
  If ColorBlue()>200 And (charTrainCourse(char)=1 Or charTrainCourse(char)=6) Then Color 150,150,250 
 EndIf
 If category=3
  stat$=charSkill(char) : GetStatColour(charSkill(char),charOldSkill(char))
  If ColorBlue()>200 And (charTrainCourse(char)=2 Or charTrainCourse(char)=6) Then Color 150,150,250 
 EndIf
 If category=4
  stat$=charAgility(char) : GetStatColour(charAgility(char),charOldAgility(char))
  If ColorBlue()>200 And (charTrainCourse(char)=3 Or charTrainCourse(char)=6) Then Color 150,150,250 
 EndIf
 If category=5
  stat$=charStamina(char) : GetStatColour(charStamina(char),charOldStamina(char))
  If ColorBlue()>200 And (charTrainCourse(char)=4 Or charTrainCourse(char)=6) Then Color 150,150,250 
 EndIf
 If category=6
  stat$=charToughness(char) : GetStatColour(charToughness(char),charOldToughness(char))
  If ColorBlue()>200 And (charTrainCourse(char)=5 Or charTrainCourse(char)=6) Then Color 150,150,250 
 EndIf
 If category=7 Then stat$=charAttitude(char) : GetStatColour(charAttitude(char),charOldAttitude(char))
 If category=8 Then stat$=charHappiness(char) : GetStatColour(charHappiness(char),charOldHappiness(char))
 If category=9 Then stat$=AverageStats(char) : Color 255,255,255
 If category=10 Then stat$=charContract(char) : Color 255,255,255
 If category=11 Then stat$="$"+GetFigure$(charSalary(char)) : Color 255,255,255
 ;display text
 r=ColorRed() : g=ColorGreen() : b=ColorBlue()
 If category=<9 And r>200 And g>200 And b>200 Then r=255 : g=255 : b=130
 If foc<>rank Or subFoc=>20 Then r=r-(r/4) : g=g-(g/4) : b=b-(b/4)
 SetFont fontStat(1)
 Outline(stat$,x,y-1,0,0,0,r,g,b)
End Function

;DRAW CHARACTER PROFILE
Function DrawProfile(char,x,y,display) ;-1=stats only, 0=normal, 1=left side only
 ;default location
 If x<0 Then x=rX#(400)-225
 If y<0 Then y=rY#(25)
 ;colour scheme
 r=255 : g=240 : b=100
 ;<<<<<<<<< LEFT HAND SIDE <<<<<<<<<
 ;photo(s)
 If display=>0
  If charManager(char)>0 And (charPartner(char)=0 Or charPartner(char)=charManager(char)) Then DrawImage charPhoto(charManager(char)),x-122,y+35
  If charPartner(char)>0 And charManager(char)=0 Then DrawImage charPhoto(charPartner(char)),x-122,y+35
  If charManager(char)>0 And charPartner(char)>0 And charPartner(char)<>charManager(char)
   DrawImage charPhoto(charManager(char)),x-132,y+33
   DrawImage charPhoto(charPartner(char)),x-117,y+35
  EndIf
  DrawImage charPhoto(char),x-102,y+37
  ;titles
  DisplayBelts(char,x-102,(y+46)-(charHeight(char)/2))
 EndIf
 ;health meter
 Color 0,0,0 : Rect x-49,y+6,100,6,1
 Color 225,0,0 : Rect x-50,y+5,100,6,1
 Color 125,0,0 : Rect x-50,y+5,100,6,0
 meter=charHealth(char)
 If game=0 And screenAgenda=0 Then meter=100
 If game=0 And screenAgenda=11 Then meter=cupCharHealth(cupSlot,char)
 ;If charTrainCourse(char)>0 Then Color 50,120,250 Else Color 0,225,0
 Color 0,225,0 
 Rect x-50,y+5,meter,6,1
 If charTrainCourse(char)>0 Then Color 5,70,190 Else Color 0,125,0
 Rect x-50,y+5,meter,6,0
 ;name line
 SqueezeFont(charName$(char),150,18) 
 If display=>0 
  placer=(StringWidth(charName$(char))/2)+5
  If placer<45 Then placer=45
  If placer>75 Then placer=75
  DrawImage gAllegiance(charHeel(char)),x-placer,y
 EndIf
 If (game=1 Or screenAgenda<>0) And InjuryStatus(char)>0 Then DrawImage gInjured,x-50,y+5
 Outline(charName$(char),x,y,0,0,0,r,g,b)
 ;headers
 SetFont font(1)
 Outline("Popularity:",x-55,y+22,0,0,0,r,g,b)
 Outline("Strength:",x-50,y+37,0,0,0,r,g,b)
 Outline("Skill:",x-39,y+52,0,0,0,r,g,b)
 Outline("Agility:",x-43,y+67,0,0,0,r,g,b)
 Outline("Stamina:",x+45,y+22,0,0,0,r,g,b)
 Outline("Toughness:",x+36,y+37,0,0,0,r,g,b)
 Outline("Attitude:",x+44,y+52,0,0,0,r,g,b)
 Outline("Happiness:",x+37,y+67,0,0,0,r,g,b)
 Outline("Build:",x-61,y+84,0,0,0,r,g,b)
 ;statistics
 SetFont fontStat(1)
 x1=x-11 : x2=x+80
 GetStatColour(charPopularity(char),charOldPopularity(char))
 Outline(charPopularity(char),x1,y+21,0,0,0,ColorRed(),ColorGreen(),ColorBlue()) 
 GetStatColour(charStrength(char),charOldStrength(char))
 If game=1 And ColorBlue()>100
  If charTrainCourse(char)=1 Or charTrainCourse(char)=6 Then Color 150,150,250 
 EndIf
 Outline(charStrength(char),x1,y+36,0,0,0,ColorRed(),ColorGreen(),ColorBlue())
 GetStatColour(charSkill(char),charOldSkill(char))
 If game=1 And ColorBlue()>100
  If charTrainCourse(char)=2 Or charTrainCourse(char)=6 Then Color 150,150,250 
 EndIf
 Outline(charSkill(char),x1,y+51,0,0,0,ColorRed(),ColorGreen(),ColorBlue())
 GetStatColour(charAgility(char),charOldAgility(char))
 If game=1 And ColorBlue()>100
  If charTrainCourse(char)=3 Or charTrainCourse(char)=6 Then Color 150,150,250 
 EndIf
 Outline(charAgility(char),x1,y+66,0,0,0,ColorRed(),ColorGreen(),ColorBlue())
 GetStatColour(charStamina(char),charOldStamina(char))
 If game=1 And ColorBlue()>100
  If charTrainCourse(char)=4 Or charTrainCourse(char)=6 Then Color 150,150,250 
 EndIf
 Outline(charStamina(char),x2,y+21,0,0,0,ColorRed(),ColorGreen(),ColorBlue())
 GetStatColour(charToughness(char),charOldToughness(char))
 If game=1 And ColorBlue()>100
  If charTrainCourse(char)=5 Or charTrainCourse(char)=6 Then Color 150,150,250 
 EndIf
 Outline(charToughness(char),x2,y+36,0,0,0,ColorRed(),ColorGreen(),ColorBlue())
 GetStatColour(charAttitude(char),charOldAttitude(char))
 Outline(charAttitude(char),x2,y+51,0,0,0,ColorRed(),ColorGreen(),ColorBlue())
 GetStatColour(charHappiness(char),charOldHappiness(char))
 Outline(charHappiness(char),x2,y+66,0,0,0,ColorRed(),ColorGreen(),ColorBlue()) 
 OutlineStraight(charAge(char)+"yrs, "+GetHeight$(charHeight(char))+", "+TranslateWeight(char)+"lbs",x-40,y+83,0,0,0,255,255,255)
 ;>>>>>>>>>> RIGHT HAND SIDE >>>>>>>>>
 If display=0
  x=rX#(400)+235
  ;status line
  Color 200,150,50 : Rect x-50,y+5,100,5,1
  Color 0,0,0 : Rect x-50,y+5,100,5,0
  status$="Available" : context$="" : r2=r : g2=g : b2=b
  If game=1
   If charWorked(char)>0 Then status$="Worked" : r2=200 : g2=190 : b2=150
   If charTrainCourse(char)>0 Then status$="Training" : context$="("+textTrainCourse$(charTrainCourse(char))+")" : r2=150 : g2=150 : b2=250
   If screen<>21 Or foc=>10
    If charVacant(char)>0 Then status$="Absent" : context$="("+charVacant(char)+" weeks)" : r2=125 : g2=225 : b2=125
    If InjuryStatus(char)>0 Then status$="Injured" : context$="("+InjuryStatus(char)+" weeks)" : r2=225 : g2=125 : b2=125
   EndIf
  EndIf
  SetFont font(4)
  If context$="" Then offset=0 Else offset=5
  Outline(status$,x-offset,y-1,0,0,0,r2,g2,b2)
  offset=StringWidth(status$)/2
  SetFont fontStat(0)
  OutlineStraight(context$,x+offset,y-1,0,0,0,r2,g2,b2)
  ;headers
  SetFont font(1)
  Outline("Record:",x-48,y+21,0,0,0,r,g,b)
  Outline("Contract:",x-52,y+36,0,0,0,r,g,b)
  Outline("Salary:",x-46,y+51,0,0,0,r,g,b)
  ;record
  SetFont fontStat(1)
  OutlineStraight(CountMatches(char,charFed(char))+" matches",x-20,y+20,0,0,0,255,255,255)
  offset=3+StringWidth(CountMatches(char,charFed(char))+" matches")
  SetFont fontStat(0)
  OutlineStraight("("+GetWinRate(char,charFed(char))+"% wins)",(x-20)+offset,y+20,0,0,0,255,255,255)
  ;contract
  SetFont fontStat(1)
  If charContract(char)>0 Then namer$=charContract(char)+" weeks" Else namer$="None"
  OutlineStraight(namer$,x-20,y+35,0,0,0,255,255,255) 
  ;salary
  r2=255 : g2=255 : b2=255
  If char=fedBooker(charFed(char)) 
   namer$="Undisclosed"
  Else
   payment=charSalary(char)
   If game=1 And charFed(char)=charFed(gamChar)
    If charVacant(char)>0 And charClause(char,2)=0 Then payment=0 : r2=125 : g2=225 : b2=125
    If charVacant(char)>0 And charClause(char,2)=1 Then payment=charSalary(char)/2 : r2=125 : g2=225 : b2=125
    If InjuryStatus(char)>0 And charClause(char,3)=0 Then payment=0 : r2=225 : g2=125 : b2=125
    If InjuryStatus(char)>0 And charClause(char,3)=1 Then payment=charSalary(char)/2 : r2=225 : g2=125 : b2=125
   EndIf
   If payment>0 Then namer$="$"+GetFigure$(payment)+" per week" Else namer$="None" 
  EndIf
  OutlineStraight(namer$,x-20,y+50,0,0,0,r2,g2,b2)
  ;smallprint headers
  SetFont font(0)
  Outline("Creative Control:",x-66,y+66,0,0,0,r,g,b)
  Outline("Perform Clause:",x-65,y+77,0,0,0,r,g,b)
  Outline("Health Policy:",x-59,y+88,0,0,0,r,g,b)
  ;small print stats
  SetFont fontStat(0)
  For count=1 To 3
   namer$="Undisclosed" : r=255 : g=255 : b=255
   If char<>fedBooker(charFed(char))
    namer$=textClause$(count,charClause(char,count))
    If charClause(char,count)=0 Then r=220 : g=100 : b=100
    If charClause(char,count)=2 Then r=100 : g=220 : b=100
   EndIf
   OutlineStraight(namer$,x-20,y+(54+(count*11)),0,0,0,r,g,b)
  Next
 EndIf
End Function

;DISPLAY BELTS
Function DisplayBelts(char,x,y)
 If TitleHolder(char,1) 
  If TitleHolder(char,3) And TitleHolder(char,2)=0 Then DrawImage gBelt(1),x,y-3
  If TitleHolder(char,3) And TitleHolder(char,2) Then DrawImage gBelt(1),x,y-6
  If TitleHolder(char,2) Then DrawImage gBelt(2),x,y-3
  DrawImage gBelt(1),x,y
 Else
  If TitleHolder(char,3) And TitleHolder(char,2)=0 Then DrawImage gBelt(3),x,y
  If TitleHolder(char,3) And TitleHolder(char,2) Then DrawImage gBelt(3),x,y-3
  If TitleHolder(char,2) Then DrawImage gBelt(2),x,y  
 EndIf
End Function

;DRAW FED STATS
Function DrawFedProfile(cyc,x,y)
 ;default location
 If x<0 Then x=rX#(400)-220
 If y<0 Then y=rY#(27)
 ;colour scheme
 r=255 : g=240 : b=100
 ;<<<<<<<<< LEFT HAND SIDE <<<<<<<<<
 ;name
 Color 200,150,50 : Rect x-50,y+6,100,5,1
 Color 0,0,0 : Rect x-50,y+6,100,5,0
 SqueezeFont(fedName$(cyc),175,18) 
 Outline(fedName$(cyc),x,y,0,0,0,r,g,b)
 ;portraits
 If fedChampWorld(cyc)>0 And charFed(fedChampWorld(cyc))=cyc 
  DrawImage charPhoto(fedChampWorld(cyc)),x-127,y+23
  DisplayBelts(fedChampWorld(cyc),x-127,(y+31)-(charHeight(fedChampWorld(cyc))/2))
 EndIf
 If fedBooker(cyc)>0 And charFed(fedBooker(cyc))=cyc Then DrawImage charPhoto(fedBooker(cyc)),x-102,y+24 
 ;headers
 SetFont font(1)
 OutlineStraight("Popularity:",x-64,y+22,0,0,0,r,g,b)
 OutlineStraight("Reputation:",x-64,y+37,0,0,0,r,g,b)
 OutlineStraight("Booker:",x-54,y+53,0,0,0,r,g,b)
 OutlineStraight("Champion:",x-66,y+68,0,0,0,r,g,b)
 ;popularity data
 SetFont fontStat(1)
 GetStatColour(fedPopularity(cyc),fedOldPopularity(cyc))
 Outline(fedPopularity(cyc),x+14,y+21,0,0,0,ColorRed(),ColorGreen(),ColorBlue())
 r2=ColorRed()-(ColorRed()/4) : g2=ColorGreen()-(ColorGreen()/4) : b2=ColorBlue()-(ColorBlue()/4)
 Outline("/",x+29,y+21,0,0,0,r2,g2,b2)
 Outline(PromotionPotential(cyc),x+46,y+21,0,0,0,r2,g2,b2) 
 ;reputation data
 GetStatColour(fedReputation(cyc),fedOldReputation(cyc))
 Outline(fedReputation(cyc),x+14,y+36,0,0,0,ColorRed(),ColorGreen(),ColorBlue())
 r2=ColorRed()-(ColorRed()/4) : g2=ColorGreen()-(ColorGreen()/4) : b2=ColorBlue()-(ColorBlue()/4)
 Outline("/",x+29,y+36,0,0,0,r2,g2,b2)
 Outline("100",x+46,y+36,0,0,0,r2,g2,b2) 
 ;key players
 If fedBooker(cyc)>0 And charFed(fedBooker(cyc))=cyc Then namer$=charName$(fedBooker(cyc)) Else namer$="Vacant"
 OutlineStraight(namer$,x-4,y+52,0,0,0,255,255,255)
 If fedChampWorld(cyc)>0 And charFed(fedChampWorld(cyc))=cyc Then namer$=charName$(fedChampWorld(cyc)) Else namer$="Vacant"
 OutlineStraight(namer$,x-4,y+67,0,0,0,255,255,255)
 ;>>>>>>>>>> RIGHT HAND SIDE >>>>>>>>>
 x=rX#(400)+235
 ;bank details
 Color 200,150,50 : Rect x-50,y+6,100,5,1
 Color 0,0,0 : Rect x-50,y+6,100,5,0
 SetFont fontNews(4)
 If fedBank(cyc)=<0 Then Outline("$"+GetFigure$(fedBank(cyc)),x,y-1,0,0,0,200,0,0)
 If fedBank(cyc)>0 Then Outline("$"+GetFigure$(fedBank(cyc)),x,y-1,0,0,0,250,250,130) 
 ;headers
 SetFont font(1)
 OutlineStraight("Roster:",x-66,y+22,0,0,0,r,g,b)
 OutlineStraight("Ratio:",x-56,y+37,0,0,0,r,g,b)
 OutlineStraight("Salaries:",x-74,y+52,0,0,0,r,g,b)
 OutlineStraight("Production:",x-91,y+67,0,0,0,r,g,b) 
 ;data
 SetFont fontStat(1)
 OutlineStraight(fedSize(cyc)+" wrestlers",x-18,y+21,0,0,0,255,255,255)
 OutlineStraight(AllegianceRatio(cyc,0)+" Faces vs "+AllegianceRatio(cyc,1)+" Heels",x-18,y+36,0,0,0,255,255,255) 
 OutlineStraight("$"+GetFigure$(CountSalaries(cyc,0))+" per week",x-18,y+51,0,0,0,255,255,255)
 namer$="Undisclosed"
 If game=1 And cyc=charFed(gamChar) Then namer$="$"+GetFigure$(ProductionCosts(cyc,0))+" per week"
 OutlineStraight(namer$,x-18,y+66,0,0,0,255,255,255)
End Function

;GET STAT COLOUR
Function GetStatColour(current,old)
 Color 255,255,255
 If game=1
  If current>old Then Color 100,220,100
  If current<old Then Color 220,100,100
 EndIf
End Function

;REWARD SETTINGS APPLICABLE?
Function RewardApplicable(reward)
 applicable=1
 If reward=>2 And reward=<3 And matchTeams>0 Then applicable=0 ;no solo titles for teams
 If reward=4 And matchTeams=<0 Then applicable=0 ;no team titles for individuals
 If reward=4 And (no_wrestlers<3 Or no_wrestlers>4) Then applicable=0 ;team sizes too big
 If reward=>1 And reward=<4 And (fed=0 Or fed=>7) Then applicable=0 ;no titles for novelty feds
 If game=0 And slot>0 And reward>0 Then applicable=0 ;no title changes in career universes
 Return applicable
End Function

;STANDARD LOADING DISPLAY
Function Loader(scriptA$,scriptB$)
 If screen>0 Then Cls
  If screen>0 Then DrawImage gBackground,rX#(400),rY#(300)
  DrawImage gLogo(1),rX#(400),rY#(250)
  DrawImage gMDickie,rX#(400),rY#(530) 
  DrawOption(-1,rX#(400),rY#(425),scriptA$,scriptB$)
 Flip
End Function

;MATCH LOADING DISPLAY
Function MatchLoader(scriptA$,scriptB$)
 Cls
  ;background
  DrawImage gBackground,rX#(400),rY#(300)
  DrawImage gLogo(1),rX#(400),rY#(155)
  DrawImage gMDickie,rX#(400),rY#(530) 
  ;versus display
  y=rY#(340)
  If no_wrestlers=2 Or matchTeams>0 Then DrawImage gVersus,rX#(400)-1,y
  showX1=rX#(400)-70 : showX2=rX#(400)+72
  If no_wrestlers=2
   DrawProfile(pChar(1),showX1-130,y-45,-1)
   DrawImage charPhoto(pChar(1)),showX1,y-10
   DrawProfile(pChar(2),showX2+125,y-45,-1)
   DrawImage charPhoto(pChar(2)),showX2,y-10
  EndIf  
  If no_wrestlers=3 And matchTeams=<0
   DrawImage gSmallVersus,rX#(400)-50,y
   DrawImage gSmallVersus,rX#(400)+50,y
   DrawImage charPhoto(pChar(1)),rX#(400)-100,y-10
   DrawImage charPhoto(pChar(2)),rX#(400),y-10
   DrawImage charPhoto(pChar(3)),rX#(400)+100,y-10
  EndIf
  If no_wrestlers=4 And matchTeams=<0
   DrawImage gSmallVersus,rX#(400)-100,y
   DrawImage gSmallVersus,rX#(400)-1,y
   DrawImage gSmallVersus,rX#(400)+100,y
   DrawImage charPhoto(pChar(1)),rX#(400)-150,y-10
   DrawImage charPhoto(pChar(2)),rX#(400)-50,y-10
   DrawImage charPhoto(pChar(3)),rX#(400)+50,y-10 
   DrawImage charPhoto(pChar(4)),rX#(400)+150,y-10
  EndIf
  ;team display
  If matchTeams>0
   divider=(no_wrestlers/2)-1
   If divider<1 Then divider=1
   spacing=125/divider
   If spacing<15 Then spacing=15
   If spacing>50 Then spacing=50
   For cyc=1 To no_wrestlers
    If cyc=<no_wrestlers/2 Then DrawImage charPhoto(pChar(cyc)),showX1,y-10 : showX1=showX1-spacing
    If cyc>no_wrestlers/2 Then DrawImage charPhoto(pChar(cyc)),showX2,y-10 : showX2=showX2+spacing
   Next
  EndIf
  ;big match display
  If no_wrestlers>4 And matchTeams=<0
   showX1=rX#(400)-200 : spacing=400/(no_wrestlers-1)
   For cyc=1 To no_wrestlers
    DrawImage charPhoto(pChar(cyc)),showX1,y-10
    showX1=showX1+spacing
   Next
  EndIf
  ;message
  DrawOption(-1,rX#(400),rY#(435),scriptA$,scriptB$)
 Flip
End Function

;QUICK LOADING DISPLAY
Function QuickLoader(x#,y#,scriptA$,scriptB$)
 DrawOption(-1,x#,y#,scriptA$,scriptB$)
 Flip
End Function

;CHANGE RESOLUTION
Function ChangeResolution(resolution,task) ;0=pre-game, 1=during game
 ;assess preferences
 width=Int(textResX$(resolution))
 height=Int(textResY$(resolution))
 If GfxMode3DExists(width,height,16)=0 Then width=800 : height=600 : optGameRes=2 : optMenuRes=2
 ;make transition?
 If width<>GraphicsWidth() Or height<>GraphicsHeight()
  If task>0 Then Loader("Please Wait","Adjusting Resolution")
  Graphics3D width,height,16,1+optWindow
  If task>0 ;restore media
   LoadImages()
   Loader("Please Wait","Restoring Media")
   LoadTextures()
   LoadPhotos(slot)
   For cyc=1 To 3
    slotPreview(cyc)=LoadImage("Data/Previews/Preview0"+cyc+".bmp")
    MaskImage slotPreview(cyc),255,0,255
   Next
   For cyc=1 To 10
    hiPhoto(cyc)=LoadImage("Data/Hall Of Fame/Photo"+Dig$(cyc,10)+".bmp")
    MaskImage hiPhoto(cyc),hiPhotoR(cyc),hiPhotoG(cyc),hiPhotoB(cyc) 
   Next
   LoadItemData()
   LoadWeaponData()
  EndIf
 EndIf
End Function

;GET SCREENSHOT
Function Screenshot()   
 ;obtain image
 PlaySound sCamera
 screenshot=CreateImage(GraphicsWidth(),GraphicsHeight())
 GrabImage screenshot,GraphicsWidth()/2,GraphicsHeight()/2
 ;title & save
 temp=MilliSecs()/10
 namer$="Screenshot - "+temp+".bmp"
 If game=1 And slot>0
  namer$=charName$(gamChar)+" ("+Left$(textMonth$(GetMonth(gamDate)),3)+" "+gamYear+") - "+temp+".bmp"
 EndIf
 SaveImage(screenshot,"Photo Album/"+namer$)
 FreeImage screenshot
End Function

;MANAGE MUSIC
Function ManageMusic(track)
 ;request switch
 randy=Rnd(0,10000)
 If (randy=<2 Or (randy=<5 And chCurrentTheme=>15) Or chCurrentTheme=33) And optJukeBox>0 And chThemeChange=-1
  Repeat
   newbie=Rnd(1,no_themes)
  Until newbie<>chCurrentTheme
  chThemeChange=newbie
 EndIf
 If track=>0 Then chThemeChange=track
 ;pursue target volume
 chThemeTarget#=PercentOf#(1.0,optMusicVolume) : speeder#=0.01
 If (screen=>50 And screen<>51) Or screen=6 Or track=-2 Then chThemeTarget#=chThemeTarget#/2 : speeder#=0.01
 If chThemeChange=>0 Then chThemeTarget#=0
 If chThemeVol#<chThemeTarget# Then chThemeVol#=chThemeVol#+speeder#
 If chThemeVol#>chThemeTarget# Then chThemeVol#=chThemeVol#-speeder#
 If chThemeVol#<0 Then chThemeVol#=0
 If chThemeVol#>1.0 Then chThemeVol#=1.0
 ;make switch
 If chThemeVol#=<0.01 And chThemeChange=>0
  StopChannel(chTheme)
  If chThemeChange>0
   LoopSound sTheme(chThemeChange)
   chTheme=PlaySound(sTheme(chThemeChange))
  Else
   LoopSound sMainTheme
   chTheme=PlaySound(sMainTheme)
  EndIf
  chCurrentTheme=chThemeChange : chThemeChange=-1
  If chCurrentTheme>0 Then chThemePitch=22050 Else chThemePitch=44010 
  ChannelPitch chTheme,chThemePitch
 EndIf 
 If chCurrentTheme>0 Then chThemePitch=22050 Else chThemePitch=44010 
 ChannelVolume chTheme,chThemeVol# 
End Function