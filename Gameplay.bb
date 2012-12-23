;-------------------------------------------------------------------------
;/////////////// WRESTLING MPIRE REMIX: MANAGEMENT EDITION ///////////////
;-------------------------------------------------------------------------
;~~~~~~~~~~~~~~~~~~~~~ Copyright © Mat Dickie 2008 ~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~ This program may not be re-released under any other ~~~~~~~~~~
;~~~~~~~ identity or sold commercially without express permission. ~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

;FILE STRUCTURE
SeedRnd(MilliSecs())
AppTitle "Wrestling MPire Remix: Management Edition"
Include "Values.bb"
Include "Texts.bb"
Include "Functions.bb"
Include "Data.bb"
Include "Credits.bb"
Include "Menus.bb"
Include "Tournaments.bb"
Include "Career.bb"
Include "Charts.bb"
Include "News.bb"
Include "Aftermath.bb"
Include "Meetings.bb"
Include "Negotiations.bb"
Include "Court.bb"
Include "Editor.bb"
Include "Training.bb"
Include "World.bb"
Include "Items.bb"
Include "Particles.bb"
Include "Players.bb"
Include "AI.bb"
Include "Anims.bb"
Include "Attacks.bb"
Include "Moves.bb"
Include "Ground.bb"
Include "Buckles.bb"
Include "Teams.bb"
Include "Promos.bb"

;INITIATE ENGINE 
LoadOptions()
optOnline=0;StartNetGame()
ChangeResolution(optMenuRes,0)
SetBuffer BackBuffer()
AutoMidHandle True
EnableDirectInput True
HidePointer
MoveMouse rX#(650),rY#(500)
SeedRnd(MilliSecs())   

;LOADING PROCESS
;start intro
Intro()
;load media
LoadMusic()
LoadImages()
LoadTextures()
LoadItemData()
LoadWeaponData()
;default universe
Loader("Please Wait","Restoring Data")
LoadUniverse()
slot=0
LoadProgress(slot)
LoadWorld(slot)
LoadChars(slot)
Loader("Please Wait","Loading Portraits")
LoadPhotos(slot)
;stop crowd
If ChannelPlaying(chCrowd) Then StopChannel chCrowd
;default match settings
fed=1
GenerateArena(0,2,0)
GetMatchRules(2)
AddGimmick(0)
;fiddles
;For char=1 To no_chars
;Next
;online test
screen=1
If optOnline>0
 StartOnlineGame()
EndIf

;SCREEN MANAGEMENT
SeedRnd(MilliSecs())
;screen=1
Repeat
 ;load screen
 If screen>0
  LoadScreen(screen)
 EndIf
 ;get-out clause
 If KeyDown(56) And KeyDown(45) Then End
Until screen=0
End 

;SCREEN ACCESS
Function LoadScreen(request)
 ;main menus
 If request=1 Then MainMenu()
 If request=2 Then Options()
 If request=3 Then DisplayOptions()
 If request=4 Then RedefineKeys()
 If request=5 Then RedefineGamepad()
 If request=6 Then Outro()
 If request=7 Then Credits()
 ;setup menus
 If request=10 Then SelectSlot()
 If request=11 Then SelectRoster()
 If request=12 Then SelectCharacter()
 If request=13 Then ArenaSetup()
 If request=14 Then MatchSetup()
 If request=15 Then Tournament()
 If request=16 Then TournamentSetup()
 If request=17 Then GimmickSetup()
 ;career screens
 If request=20 Then Calendar()
 If request=21 Then Aftermath()
 If request=22 Then IntroduceDate()
 If request=23 Then NewsReports()
 If request=24 Then FinanceReport()
 If request=25 Then Retirement()
 If request=26 Then HallOfFame()
 If request=27 Then ConfirmUniverse()
 If request=28 Then WorldLeaders()
 If request=29 Then NightSummary()
 If request=30 Then TelevisionRatings()
 If request=31 Then Production()
 ;3D scenes
 If request=50 Then Gameplay()
 If request=51 Then Editor()
 If request=52 Then Negotiations()
 If request=53 Then Meeting()
 If request=54 Then Training()
 If request=55 Then CourtCase()
 If request=56 Then SellCharacter()
End Function

;DELIBERATE CRASH LOOPHOLE
Function LoopHole()
 LoopHole()
End Function

;--------------------------------------------------------------------------
;/////////////////////////// 50. GAMEPLAY /////////////////////////////////
;--------------------------------------------------------------------------
Function Gameplay()
;adjust resolution
ChangeResolution(optGameRes,1)
ResetTextures()
;adjust length option
optOldLength=optLength
If game=1
 If screenAgenda=12 Then optLength=1
 If matchShoot>0 Then optLength=1
 If gamSchedule(gamDate)=4 Then optLength=1
 If matchPromo=>50 And matchPromo=<52 Then optLength=1
EndIf
;determine entrance system
matchEntrances=1
If optEntrances=0 Or matchLocation<>0 Or screenAgenda=10 Then matchEntrances=0
If optEntrances=1 And no_wrestlers>4 And matchTeams=0 Then matchEntrances=0
If matchTeams=-1 Then matchEntrances=1
;load atmosphere
MatchLoader("Please Wait","Loading Atmosphere")
LoadAtmos()
;load location
MatchLoader("Please Wait","Loading Arena")
LoadArena()
;load ropes
MatchLoader("Please Wait","Loading Ropes")
LoadRopes()
;add cage
If matchCage>0 Then LoadCage()
;get ring components
GetRingComponents()
;load characters
PrepareCast()
LoadPlayers()
;initial focus
If matchEntrances=0
 matchState=0
 For cyc=1 To no_plays
  GetNewFoc(cyc) 
 Next
EndIf
;activate dual control
If matchPlayer>0 And matchMulti=0 Then pControl(matchPlayer)=3
;clean textures
MatchLoader("Please Wait","Cleaning Textures")
RestoreTextures()
;prepare promo
PreparePromo()
;load items
If no_items>0
 MatchLoader("Please Wait","Loading Items")
 LoadItems()
EndIf
;load weapons
PrepareWeapons()
If no_weaps>0
 MatchLoader("Please Wait","Loading Weapons")
 LoadWeapons()
 AssignWeapons()
EndIf
;load particles
If optFX>0
 MatchLoader("Please Wait","Loading Effects")
 no_particles=1000 
 If optFX=1 Then no_particles=no_particles/2
 LoadParticles()
EndIf
;load pools
If optFX>0
 no_pools=50 
 If optFX=<1 Then no_pools=no_pools/2
 LoadPools()
EndIf
;reset status
matchEnter=no_wrestlers
If matchTeams=-1 Then matchEnter=1
If matchTeams>0 Then matchEnter=(no_wrestlers/2)+1
If matchEntrances=0 Then matchEnter=0
matchState=0 : matchTim=0 : matchCountTim=0
matchMins=0 : matchLastMin=0 : matchSecs=0 : matchClock=0
matchWinner=0 : matchLeader=0 : matchLoser=0 : matchWinStyle=0
matchBlasted=0 : matchDamage=0 : matchWeapon=0
matchIntruder=0 : matchBetrayal=0
matchPause=0 : comTim=0 : comSpeed=0
;rule logic
If matchLocation>0 
 If matchTeams>1 Then matchTeams=1
 If matchCountOuts>0 Then matchCountOuts=0
EndIf
If matchRules=0 And matchCountOuts=>1 And matchCountOuts=<2 Then matchCountOuts=0
If matchTeams=-1
 If matchCountOuts=>1 And matchCountOuts=<2 Then matchCountOuts=0
 If matchType=3 Then matchType=1
 If matchType=4 Then matchType=5
EndIf
If matchType=>3 And matchType=<4 And matchTimeLim<5 Then matchTimeLim=5
;store title status
For promotion=1 To 9
 fedOldChampWorld(promotion)=fedChampWorld(promotion)
 fedOldChampInter(promotion)=fedChampInter(promotion)
 For count=1 To 2
  fedOldChampTag(promotion,count)=fedChampTag(promotion,count)
 Next
Next
;reset entertainment
entX=725 : entY=560
entHype=1000 : entHardcore=0
For cyc=1 To no_wrestlers
 entHype=entHype+(((charPopularity(pChar(cyc))/2)+35)*10)
Next
If matchTeams=-1 And no_wrestlers>2 Then entHype=entHype/2
If matchTeams=2 And no_wrestlers>3
 entHype=PercentOf#(entHype,GetPercent#(3,no_wrestlers))
 entHype=entHype+(entHype/4)
EndIf
FindFeud()
entHype=entHype+(entFeud*20)
If matchReward=2 Or matchReward=7 Or fed=0 Or (screenAgenda=11 And cupFoc(cupSlot)=1) Then entHype=entHype+(entHype/5)
If matchReward=3 Or matchReward=4 Or matchReward=6 Or (screenAgenda=11 And cupFoc(cupSlot)>1) Then entHype=entHype+(entHype/10)
If matchPromo=>50 And matchPromo=<52 Then entHype=entHype*2
If optLength=1 Then entHype=entHype+(entHype/4);PercentOf#(entHype,75)
If optLength=3 Then entHype=entHype-(entHype/4);entHype/2
entScore=entHype
entEndScore=-1 : entEndHardcore=-1
;prepare press photo
If game=1
 x#=rX#(512)
 If x#>700 Then x#=700
 gPressPhoto=CreateImage(x#,rY#(256))
EndIf
;stop theme
MatchLoader("Please Wait","Preparing To Play")
If ChannelPlaying(chTheme)>0 Then StopChannel chTheme
;frame rating
timer=CreateTimer(frameRate(optSpeed))
SeedRnd(MilliSecs())
;MAIN LOOP
delayTim=(2*no_plays)*(2*no_plays)
If delayTim>100 Then delayTim=100
go=0 : gotim=-delayTim : keytim=20
While go=0

 ;ClsColor 255,0,255 ;REMOVE!!!
 ;CameraClsColor cam,255,0,255 ;REMOVE!!!
 Cls
 frames=WaitTimer(timer)
 For framer=1 To frames
	
  ;COUNTERS
  keytim=keytim-1
  If keytim<1 Then keytim=0
  controlTim=controlTim-1
  If controlTim<0 Then controlTim=0 
  For j=1 To 4
    If joyControlTim(j)>0 Then joyControlTim(j) = joyControlTim(j) - 1
  Next
  speedTim=speedTim-1
  If speedTim<0 Then speedTim=0 
  ;store old match/cam states
  matchOldState=matchState
  If camTempFoc=0 Then camOldFoc=camFoc
	
  ;PORTAL
  gotim=gotim+1
  If gotim>40 And keytim=0 
   If KeyDown(1) Or KeyDown(28) 
    ;skip intro
    If matchState=0
     ProduceSound(cam,sSwing,20000,0.5) : keytim=20
     matchState=1 : matchTim=100
     If matchEnter=0 Then GetCamera(optDefaultCam)
    EndIf
    ;skip entrance
    If matchState=1 And matchEnter>0 And pOutTim(matchEnter)>0
     For cyc=1 To no_plays
      If pTeam(cyc)=pTeam(matchEnter) And InsideRing(pX#(cyc),pZ#(cyc),0)=0 And ProjectedAnim(cyc)=0 And pPlatform(cyc)=0
       If cyc=matchEnter Then ProduceSound(p(cyc),sSwing,20000,0.5) : keytim=20
       pX#(cyc)=Rnd(-60,60) : pZ#(cyc)=Rnd(-60,60)
       pOldX#(cyc)=pX#(cyc) : pOldZ#(cyc)=pZ#(cyc)
       pY#(cyc)=wStage# : pPlatform(cyc)=0
       PositionEntity p(cyc),pX#(cyc),pY#(cyc),pZ#(cyc)
       If pOutTim(cyc)<225 Then pOutTim(cyc)=225
       If camFoc=cyc Then camPivX#=pX#(cyc) : camPivZ#=pZ#(cyc)
       If camZ#>390 Then camX#=0 : camY#=30 : camZ#=390 : camOldX#=camX# : camOldY#=camY# : camOldZ#=camZ#
       If camZ#<-390 Then camX#=0 : camY#=30 : camZ#=-390 : camOldX#=camX# : camOldY#=camY# : camOldZ#=camZ#
       PositionEntity light(1),Rnd(-100,100),100,Rnd(-100,100)
       PositionEntity lightPivot,Rnd(-100,100),20,Rnd(-100,100)
       PointEntity light(1),lightPivot 
       If comTim>25 Then comTim=25 : comSpeed=-1
       matchTim=0
      EndIf
     Next
    EndIf
    ;skip promos
	If matchState=2 And matchPromo>0
	 If promoTim>25 And promoTim<promoLength(matchPromo)-25 
	  promoTim=promoTim-100 : keytim=10 
	  If promoTim<25 Then promoTim=25
	 EndIf
	EndIf
	;abort match 
	abortBlock=0
	If game=1 And screenAgenda=12 Then abortBlock=1
    If KeyDown(1) And abortBlock=0 And matchState=3 And matchTim>50
     Pop(0,3,0) : Pop(0,7,0)
     entScore=entScore/2
     matchWinner=0 : matchWinStyle=0
     If game=1 And gamSchedule(gamDate)=4 Then matchWinner=teamLegal(2)
     EndMatch(matchWinner)
    EndIf 
	;exit
    If matchState=4 And matchTim>50 Then go=-1  
   EndIf     
  EndIf
  
  ;ppppppppppppppppppppppp PAUSE LOOP pppppppppppppppppppppppppppppppp
  ;pause toggle
  If (KeyDown(25) And gotim>20 And keytim=0) Or (KeyDown(1) And matchPause=1)
   PlaySound sMenuSelect : keytim=10
   If matchPause=1 Then matchPause=0 Else matchPause=1
  EndIf
  ;pause loop
  If matchPause=0  

   ;FIDDLES
   If gotim>40 And keytim=0
    ;widescreen offset
    If KeyDown(56) And KeyDown(17)
     PlaySound sMenuGo : keytim=10
     adjuster=GraphicsWidth()/10
     CameraViewport cam,adjuster,0,GraphicsWidth()-(adjuster*2),GraphicsHeight()
     PostMessage("Widescreen display has been offset!")
    EndIf
    ;alter game speed
    For count=1 To 4
     If KeyDown(58+count) 
      PlaySound sMenuSelect : keytim=10
      optSpeed=count : speedTim=100
      FreeTimer timer
      timer=CreateTimer(frameRate(optSpeed)) 
      If optSpeed=1 Then PostMessage("Normal game speed restored...")
      If optSpeed=2 Then PostMessage("Swift game speed activated!")
      If optSpeed=3 Then PostMessage("Fast game speed activated!")
      If optSpeed=4 Then PostMessage("Turbo game speed activated!")
     EndIf 
    Next
    ;toggle health meters
    If KeyDown(63)
     PlaySound sMenuSelect : keytim=10
     optMeters=optMeters+1 
     If optMeters>2 Then optMeters=0
     If optMeters=0 Then PostMessage("Health displays have been turned off...")
     If optMeters=1 Then PostMessage("Minimal health displays have been selected...")
     If optMeters=2 Then PostMessage("Full health displays have been selected...")
    EndIf
    ;toggle entertainment display
    If KeyDown(64)
     PlaySound sMenuSelect : keytim=10
     entDisplay=entDisplay+1 
     If entDisplay>3 Then entDisplay=0
     If entDisplay=0 Then PostMessage("Entertainment display has been turned off...")
     If entDisplay>0 Then PostMessage("Entertainment display has been adjusted...")
    EndIf
    ;stop crowd animation
    If KeyDown(65) 
     PlaySound sMenuSelect : keytim=10
     If optCrowdAnim=1 Then optCrowdAnim=0 Else optCrowdAnim=1
     If optCrowdAnim=0 Then PostMessage("Crowd animation has been stopped...")
     If optCrowdAnim=1 Then PostMessage("Crowd animation has been restored...")
    EndIf 
    ;random explosion
    If KeyDown(18) And KeyDown(45) 
     x=Rnd(-130,130) : z=Rnd(-130,130)
     If InsideRing(x,z,-10) Then y=wStage#+5 Else y=wGround#+5 
     CreateExplosion(0,x,y,z,1)
     keytim=10
    EndIf
    ;reduce health
    If KeyDown(56) And KeyDown(207)
     PlaySound sAgony(Rnd(1,3)) : keytim=10
     For v=1 To no_wrestlers
      pHealth(v)=pHealth(v)/2
     Next
     PostMessage("Health points have been halved!")
    EndIf
    ;cause limb loss
    If KeyDown(56) And KeyDown(38) Then LoseLimb(Rnd(1,no_wrestlers),Rnd(1,50),0)
    ;restart match!
    If KeyDown(211) And matchState=4 And matchCountOuts<3 And (gamSchedule(gamDate)<>4 Or game=0)
     If matchBellTim=0 Then PlaySound sBell : matchBellTim=10
     Pop(0,6,1)
     matchState=3 : matchWinStyle=0 : matchWinner=0 : matchLoser=0
     entScore=entScore/2 : entEndScore=-1 : entEndHardcore=-1
     For v=1 To no_wrestlers
      If pHidden(v)=0 Then pFalls(v)=0 : pEliminated(v)=0
     Next
     teamFalls(1)=0 : teamFalls(2)=0
     If matchTimeLim>0 And matchMins=>matchTimeLim Then matchTimeLim=matchTimeLim+5
     If ChannelPlaying(chTheme) Then StopChannel chTheme
     PostMessage("Match has been restarted!")
    EndIf
    ;switch character (keyboard)
    If KeyDown(15) And screenAgenda<>12 And optOnline=0
     newbie=matchPlayer
     Repeat 
      newbie=newbie+1 : satisfied=1
      If newbie>no_plays
        If matchPlayer=0 Then newbie=0 Else newbie=1
      EndIf
      If(newbie > 0)
        If pOutTim(newbie)=0 Then satisfied=0
        If pControl(newbie)>0 Then satisfied=0
        If game=1 And charFed(pChar(newbie))<>charFed(gamChar) Then satisfied=0
        If pHidden(newbie)>0 Or (pEliminated(newbie) And optHideElim>0) Then satisfied=0 
      EndIf
     Until satisfied=1 Or newbie=matchPlayer
     If newbie<>matchPlayer
      PlaySound sSwing : keytim=10
      SwapControls(matchPlayer,newbie,1)
      controlTim=100
      PostMessage("You are now controlling "+charName$(pChar(newbie))+"!")
     EndIf
    EndIf
    ;switch character (joysticks)
    If screenAgenda<>12 And optOnline=0
      For j=1 To 4
        If JoyDown(buttSelect, j-1)
          newbie=joyPlayer(j)
          Repeat
            newbie=newbie+1 : satisfied=1
            If newbie>no_plays
              If joyPlayer(j)=0 Then newbie=0 Else newbie=1
            EndIf
            If(newbie > 0)
              If pOutTim(newbie)=0 Then satisfied=0
              If pControl(newbie)>0 Then satisfied=0
              If game=1 And charFed(pChar(newbie))<>charFed(gamChar) Then satisfied=0
              If pHidden(newbie)>0 Or (pEliminated(newbie) And optHideElim>0) Then satisfied=0 
            EndIf
          Until satisfied=1 Or newbie=joyPlayer(j)

          If newbie<>joyPlayer(j)
            ;Transfer control
            old=joyPlayer(j)
            joyPlayer(j)=newbie
            pControl(newbie)=2
            pJoystick(newbie)=j-1
            pControl(old)=0
            
            keytim=10
            joyControlTim(j)=100
            PlaySound sSwing
            PostMessage("Player " + j + " is now controlling " + charName$(pChar(newbie)) + "!");
          EndIf
        EndIf
      Next
    EndIf
   EndIf

   ;MATCH ANALYSIS
   ;counters
   matchBellTim=matchBellTim-1
   If matchBellTim<0 Then matchBellTim=0
   If gotim=>0 And (matchState<>2 Or promoTim=0)
    matchTim=matchTim+1
    If matchState=0 And matchEntrances=0 Then matchTim=matchTim+1
   EndIf
   ;intro line
   If matchState=0 And matchTim=25
    namer$=""
    If matchReward=2 Then namer$="World Championship on the line!"
    If matchReward=3 Then namer$="Inter Championship on the line!"
    If matchReward=4 Then namer$="Tag Championships on the line!"
    If matchTeams=<0 
     If matchReward=5 Then namer$="Trophy to be awarded!" 
     If matchReward=6 Then namer$="Loser gets their head shaved!" 
     If matchReward=7 Then namer$="Loser must leave the company!" 
    Else
     If matchReward=5 Then namer$="Trophies to be awarded!" 
     If matchReward=6 Then namer$="Losers get their heads shaved!" 
     If matchReward=7 Then namer$="Losers must leave the company!" 
    EndIf
    If screenAgenda=11 
     If CupStage(cupFoc(cupSlot))=5 Then namer$="Tournament 1st Round!"
     If CupStage(cupFoc(cupSlot))=4
      If cupSize(cupSlot)=<16 Then namer$="Tournament 1st Round!" Else namer$="Tournament 2nd Round!"  
     EndIf
     If CupStage(cupFoc(cupSlot))=3 Then namer$="Tournament Quarter-Finals!" 
     If CupStage(cupFoc(cupSlot))=2 Then namer$="Tournament Semi-Finals!" 
     If CupStage(cupFoc(cupSlot))=1 Then namer$="Tournament Final!" 
    EndIf
    PostMessage(namer$)
   EndIf 
   If matchState=0 And matchTim>125 And comTim>25 Then comTim=25 : comSpeed=-1
   ;trigger entrances
   startTim=185
   If matchLocation>0 Then startTim=startTim/2
   If matchState=0 And matchTim>startTim
    matchState=1 : matchTim=0
    If matchEnter=0 
     If camType=12 And matchEntrances=0 Then camA#=CleanAngle#(camA#+180) : camPivAccel=0
     GetCamera(optDefaultCam)
    EndIf
   EndIf
   ;trigger promo
   If matchState=1 And matchEnter=0 And matchTim>50 Then matchState=2 : matchTim=0
   If matchState=2 Then promoTim=promoTim-1
   If promoTim<0 Then promoTim=0 
   If matchState=2 And promoTim=0 Then GetCamera(optDefaultCam) : arenaLight=1
   ;risk promo screw-up
   If game=1 And matchPromo>0 And speaker>0 And pSpeaking(speaker)>0 And promoTim>150 And ImportantPromo(matchPromo)=0
    randy=Rnd(0,charPopularity(pChar(speaker))*300)
    If randy=<1
     ProduceSound(p(speaker),sChoke,GetVoice(speaker),0) : entScore=entScore/2
     matchPromo=-1 : promoTim=275 : promoMesser=speaker 
    EndIf
   EndIf
   ;start match
   If matchState=2 And promoTim=0 And matchTim>50
    startReady=1
    For v=1 To no_plays
     If LegalMan(v) Or pRole(v)=1
      If InsideRing(pX#(v),pZ#(v),0)=0 And pOutTim(cyc)>0 And pHidden(cyc)=0 Then startReady=0
     EndIf
    Next
    If matchLocation>0 Then startReady=1
    If matchTim>1000 And matchCountOuts<3 Then startReady=1   
    If startReady=1
     PlaySound sBell : matchBellTim=10
     crowdVol#=1.0
     Pop(0,Rnd(2,9),1) : Pop(0,Rnd(2,9),1)
     matchState=3 : matchTim=0
     If optTagControl=2 Or (optTagControl=1 And game=0)
      If matchPlayer>0 And pRole(matchPlayer)=0 And matchPlayer<>teamLegal(pTeam(matchPlayer)) And pControl(teamLegal(pTeam(matchPlayer)))=0
       SwapControls(matchPlayer,teamLegal(pTeam(matchPlayer)),0)
      EndIf
     EndIf
     For cyc=1 To no_plays
      If pControl(cyc)=0 Or pFoc(cyc)=0 Or (LegalMan(cyc) And LegalMan(pFoc(cyc))=0) Then GetNewFoc(cyc)
      If pRole(cyc)=0 Then pAgenda(cyc)=1 Else pAgenda(cyc)=0
      If pRole(cyc)=1 Then pReaction(cyc)=174
     Next
     CauseChaos(100)
     GetCamera(optDefaultCam)
     arenaLight=1
    EndIf
   EndIf
   ;clock time
   If matchState=3
    matchClock=matchClock+1
    If matchClock=>30 Then matchSecs=matchSecs+1 : matchClock=0
    If matchSecs=>60 
     matchMins=matchMins+1 : matchSecs=0
     If matchBlastTim>5 And matchMins=matchBlastTim-5 Then PostMessage("5 minutes until the blast!")
     If matchBlastTim>0 And matchMins=matchBlastTim-1 Then PostMessage("One minute until the blast!")
	 If matchTimeLim>5 And matchMins=matchTimeLim-5 Then PostMessage("5 minutes remaining...")
	 If matchTimeLim>1 And matchMins=matchTimeLim-1 Then PostMessage("One minute remaining!")
	EndIf
    If matchMins=>60 Then matchMins=0 : matchSecs=0
    If matchTimeLim>0 And matchMins=>matchTimeLim Then matchMins=matchTimeLim : matchSecs=0 
   EndIf
   ;rumble participants
   If matchState=3 And matchTeams=-1
    remaining=0
    For v=1 To no_plays
     If pOutTim(v)=0 Then remaining=remaining+1
    Next
    If remaining=0 Then matchMins=matchTimeLim : matchSecs=0 : matchClock=0
    If remaining>0 And matchMins=>matchTimeLim 
	 matchMins=0 : matchSecs=0 : matchClock=0
	 matchEnter=0
	 Repeat  
      matchEnter=matchEnter+1
      If matchEnter>no_wrestlers Then matchEnter=0
     Until pOutTim(matchEnter)=0 Or matchEnter=0
     If matchEnter>0
      PlaySound sBuzzer
      Pop(0,6,1) : crowdVolTarget#=1.0
	  If ChannelPlaying(chTheme) And matchState<>4 Then StopChannel chTheme : arenaLight=1
	  If game=0
	   For v=1 To no_wrestlers
	    If pControl(matchEnter)=0 And pControl(v)>0 And pEliminated(v) 
	     pControl(matchEnter)=pControl(v) : pControl(v)=0
	     If v=matchPlayer Then matchPlayer=matchEnter
	     If v=camFoc Then camFoc=matchEnter
	    EndIf
	   Next
	  EndIf
     EndIf
    EndIf
   EndIf 
   ;keep score
   If matchState=3 Then KeepScore()
   MonitorEntertainment()
   ;cause chaos if legals are outside
   If matchState=3
    legals=0 : outside=0
    For v=1 To no_plays
     If LegalMan(v)
      legals=legals+1
      If InsideRing(pX#(v),pZ#(v),0)=0 Then outside=outside+1
     EndIf
    Next
    If (matchLocation=0 And legals=outside) Or FindReferee(0,0)=0 Then CauseChaos(25)
   EndIf
   ;commentary text
   comTim=comTim+comSpeed
   If matchState=4 Then expireTim=400 Else expireTim=100
   If matchState<>1 And comTim>expireTim And comSpeed>0 Then comSpeed=-comSpeed
   If comTim<0 Then comTim=0
 
   ;PLAYER CYCLE
   ;store old positions
   PreCycleChecks()
   ;manage network
   If optOnline>0
    ManageNetwork()
   EndIf
   ;get new input
   PlayerCycle()
   ;update display
   DisplayPlayers()

   ;PROP MANAGEMENT
   ;ring components
   ManageRingComponents()
   ;ropes
   RopeCycle()
   ;cage
   CageCycle()
   ;items
   ItemCycle()
   ;weapons
   WeaponCycle()

   ;PARTICLE EFFECTS  
   If optFX>0
    ;particles
    ParticleCycle()
    ;explosions
    ExplosionCycle()
    ;pools
    PoolCycle()
   EndIf

   ;VIDEO DISPLAY
   active=1
   If fed=7 And arenaPreset=<10 Then active=0
   If screenAgenda=10 Then active=0
   If game=1 And fed=charFed(gamChar) And fedProduction(charFed(gamChar),8)=0 Then active=0
   If active=1
    For count=1 To 10
     If FindChild(world,"Screen"+Dig$(count,10))>0
      ;change image
	  randy=Rnd(-1,1250)
	  If randy=<6 Then videoScreen(count)=randy
	  If randy=>7 And randy=<12 Then videoScreen(count)=Rnd(10,12)
	  If randy=13 And no_wrestlers=<10 And videoOldScreen(count)<20
	   videoScreen(count)=20+Rnd(1,no_wrestlers) 
	   If matchEnter>0 Then videoScreen(count)=20+matchEnter
	   If matchWinner>0 Then videoScreen(count)=20+matchWinner 
	  EndIf
	  If randy=>13 And randy=<18 And arenaApron=>16 And arenaApron=<18 Then videoScreen(count)=20
	  If videoScreen(count)<0 Then videoScreen(count)=0
	  ;apply to screen
      If videoScreen(count)<>videoOldScreen(count)
       EntityTexture FindChild(world,"Screen"+Dig$(count,10)),tVideo(videoScreen(count)),0,1
       EntityTexture FindChild(world,"Screen"+Dig$(count,10)),tVideoOverlay,0,2
       videoOldScreen(count)=videoScreen(count)
      EndIf
      ;shake noise
      randy=Rnd(0,4)
      If randy=0 And videoScreen(count)=0 Then PositionTexture tVideo(0),1,Rnd(0.0,2.0)
     EndIf
    Next
   EndIf

   ;ATMOSPHERE
   ManageAtmos()
   ;crowd management
   ManageCrowd()
   ;hide scenery
   ;If KeyDown(87) Then HideEntity world ;REMOVE!!!

  ;ppppppppppppppppppppp END OF PAUSE LOOP pppppppppppppppppppppppp
  EndIf

  ;CAMERA
  Camera() 

 ;UPDATE WORLD
 If matchPause=0 Then UpdateWorld
 Next

 ;ANIMATION OVERRIDE
 If matchPause=0
  For cyc=1 To no_plays
   ;body swaying
   limb=FindChild(p(cyc),"Body")
   RotateEntity limb,EntityPitch(limb)+pBodyXA#(cyc),EntityYaw(limb),EntityRoll(limb)+pBodyZA#(cyc) 
   ;pointed head/eyes
   If HeadViable(cyc) And pFoc(cyc)>0
    PositionEntity dummy,pLookX#(cyc),pLookY#(cyc),pLookZ#(cyc)
    PointHead(cyc,dummy)
   EndIf
   If pFoc(cyc)>0
    LookAtPerson(cyc,pFoc(cyc))
   Else
    RotateEntity pLimb(cyc,45),0,0,0
    RotateEntity pLimb(cyc,46),0,0,0
   EndIf
   If pDizzyTim(cyc)>0 Or charEyeShape(pChar(cyc))=112 Then LookAtPerson(cyc,cyc) 
  Next
 EndIf

 ;RENDER SCENE
 RenderWorld 1
 ;save shots for video
 If gotim>0
  CopyRect rX#(400)-256,rY#(275)-128,512,256,0,0,0,TextureBuffer(tVideo(10)) ;live feed
  randy=Rnd(0,500)
  If matchState=4 And matchTim>100 Then randy=99
  If randy=0 Or matchState=0
   CopyRect rX#(400)-256,rY#(275)-128,512,256,0,0,0,TextureBuffer(tVideo(11)) ;sporadic feed 1
   If game=1 Then GrabImage gPressPhoto,rX#(400),rY#(325) ;press photo
  EndIf
  If randy=1 Then CopyRect rX#(400)-256,rY#(275)-128,512,256,0,0,0,TextureBuffer(tVideo(12)) ;sporadic feed 2
 EndIf
  
 ;POST-RENDER DETAILS
 For cyc=1 To no_plays
  ;clock true positions
  For limb=1 To 50
   If pLimb(cyc,limb)>0
    pLimbX#(cyc,limb)=EntityX#(pLimb(cyc,limb),1)
    pLimbY#(cyc,limb)=EntityY#(pLimb(cyc,limb),1)
    pLimbZ#(cyc,limb)=EntityZ#(pLimb(cyc,limb),1)
   EndIf
  Next
  ;reset expressions
  ResetExpressions(cyc)
 Next

 ;DISPLAY
 ;commentary
 DisplayCommentary()
 ;clock
 If matchState=3 And comTim=<10 And matchPause=0
  SetFont fontNews(8)
  showMins=matchMins : showSecs=matchSecs
  If matchTimeLim>0
   showMins=matchTimeLim-matchMins
   If matchSecs>0 Then showMins=showMins-1 
   showSecs=60-matchSecs
   If matchSecs=0 Then showSecs=0
  EndIf
  If showMins<0 Then showMins=0
  If showSecs<0 Then showSecs=0
  Outline(showMins+":"+Dig$(showSecs,10),rX#(400),rY#(570),0,0,0,255,255,255)
 EndIf
 ;entertainment display
 If screenAgenda<>10 And screenAgenda<>12 And (gamSchedule(gamDate)<>4 Or game=0)
  DisplayRatings()
 EndIf
 ;meters
 If matchState=3 And optMeters>0
  DisplayMeters()
 EndIf 
 ;camera
 If camTim>0
  x=rX#(762) : y=rY#(568)
  DrawImage gCamera,x,y
  If camName$(camType,camOption)=""
   SetFont font(6)
   OutlineStraight(camName$(camType,0),(x-38)-StringWidth(camName$(camType,0)),y,0,0,0,195,195,230)
  Else
   SetFont font(6)
   OutlineStraight(camName$(camType,0),(x-38)-StringWidth(camName$(camType,0)),y-9,0,0,0,195,195,230)
   SetFont font(3)
   OutlineStraight(camName$(camType,camOption),(x-38)-StringWidth(camName$(camType,camOption)),y+13,0,0,0,150,180,120)
  EndIf
 EndIf
 ;promo text
 If matchState=2
  DisplayPromo()
 EndIf
 ;diagnostics
 ;diagnose=0
 ;If diagnose=1 Or KeyDown(42)
  ;SetFont fontStat(1)
  ;Outline("optOnline: "+optOnline,100,135,0,0,0,255,255,255)
  ;Outline("netID: "+netID,100,150,0,0,0,255,255,255)
  ;Outline("netControl: "+netControl,100,165,0,0,0,255,255,255)
  ;y=180
  ;For cyc=1 To no_plays
   ;Outline("P"+cyc+": "+pController(cyc),100,y,0,0,0,200,200,200)
   ;y=y+15 
  ;Next 
  ;Outline("Chat>: "+netChat$,100,y,0,0,0,255,255,255) 
 ;EndIf
 ;mask shaky start
 If gotim=<0 Then MatchLoader("Please Wait","Preparing To Play")

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;restore sound
Loader("Please Wait","Restoring Sound")
FreeTimer timer
FreeEntity camListener
StopChannel chCrowd
StopChannel chAtmos
;PlayTheme(-1)
;If matchWinner>0 And optJukeBox>0 Then theme=charTheme(pChar(matchWinner)) Else theme=0
If ChannelPlaying(chTheme)=0 Or chCurrentTheme=-1 Or optJukeBox=0 Then ManageMusic(0)
;remove camera
Loader("Please Wait","Removing Camera")
FreeEntity cam
FreeEntity camPivot
FreeEntity dummy
;remove lights
For cyc=1 To no_lights
 Loader("Please Wait","Removing Lights") 
 FreeEntity light(cyc)
Next
FreeEntity lightPivot
;remove world
Loader("Please Wait","Removing Arena")
FreeEntity world
If matchCage>0 Then FreeEntity cage
;remove variable videos
For count=4 To 6
 FreeTexture tVideo(count)
Next
If arenaApron=>16 And arenaApron=<18 Then FreeTexture tVideo(20)
;removes ropes
Loader("Please Wait","Removing Ropes")
For cyc=1 To 12
 FreeEntity rope(cyc)
 If ChannelPlaying(ropeChannel(cyc))>0 Then StopChannel ropeChannel(cyc)
Next
If ChannelPlaying(chRopes)>0 Then StopChannel chRopes
;remove signs
If no_signs>0
 For cyc=1 To no_signs
  FreeEntity sign(cyc)
 Next
EndIf
;remove players
For cyc=1 To no_plays
 Loader("Please Wait","Removing "+charName$(pChar(cyc))) 
 FreeEntity p(cyc)
 If optLabels>0 Then FreeEntity pLabel(cyc)
 FreeTexture pEyeTex(cyc) 
 For limb=1 To 50
  If pShadow(cyc,limb)>0
   FreeEntity pShadow(cyc,limb)
  EndIf 
 Next
 If no_wrestlers=<10 And pRole(cyc)=0 Then FreeTexture tVideo(20+cyc)
Next
;remove items
Loader("Please Wait","Removing Items")
For cyc=1 To no_items
 FreeEntity i(cyc)
 If ChannelPlaying(iChannel(cyc))>0 Then StopChannel iChannel(cyc)
Next
;remove weapons
Loader("Please Wait","Removing Weapons")
For cyc=1 To no_weaps
 FreeEntity weap(cyc)
 If ChannelPlaying(weapChannel(cyc))>0 Then StopChannel weapChannel(cyc)
Next
no_weaps=weapCount
;remove particles
If optFX>0
 Loader("Please Wait","Removing Effects")
 For cyc=1 To no_particles
  FreeEntity part(cyc)
 Next
EndIf
;remove pools
If optGore=>2
 Loader("Please Wait","Removing Effects")  
 For cyc=1 To no_pools
  FreeEntity pool(cyc)  
 Next
EndIf
;process photo
If game=1
 Loader("Please Wait","Processing Photo")
 ResizeImage gPressPhoto,256,128
 SaveImage(gPressPhoto,"Graphics/Articles/Photo.bmp")
 FreeImage gPressPhoto
EndIf
;universal flush
ClearWorld 1,1,0
;restore resolution
ChangeResolution(optMenuRes,1)
;progress exhibition tournament
If screenAgenda=11 And game=0
 cupResult(cupSlot,cupFoc(cupSlot))=pTeam(matchWinner)
 If cupFoc(cupSlot)>1 And cupResult(cupSlot,cupFoc(cupSlot))>0
  cupBracket(cupSlot,cupTargetBracket(cupFoc(cupSlot)),cupTargetSlot(cupFoc(cupSlot)))=cupBracket(cupSlot,cupFoc(cupSlot),cupResult(cupSlot,cupFoc(cupSlot))) 
  cupFoc(cupSlot)=0
 EndIf
 For cyc=1 To no_plays
  char=pChar(cyc)
  cupCharHealth(cupSlot,char)=Int(GetPercent#(pHealth(cyc),optLength*5000))
  For count=0 To 5
   If pInjured(cyc,count)>0
    cupCharInjured(cupSlot,char,count)=pInjured(cyc,count)*2
    cupCharHealth(cupSlot,char)=0
   EndIf 
  Next
 Next
EndIf
;log career segment
If game=1 And screenAgenda<>10 And screenAgenda<>12
 gamSegments(gamDate)=gamSegments(gamDate)+1
 gamMatchScore(gamDate,gamSegments(gamDate))=entTotal
 gamMatchHardcore(gamDate,gamSegments(gamDate))=entHardTotal 
 gamMatchFormat(gamDate,gamSegments(gamDate))=1 
 If matchTeams>0 And no_wrestlers=>4 Then gamMatchFormat(gamDate,gamSegments(gamDate))=2
 If matchTeams=<0 And no_wrestlers=>4 Then gamMatchFormat(gamDate,gamSegments(gamDate))=3
 If matchWinner>0 Then gamMatchWinner(gamDate,gamSegments(gamDate))=pChar(matchWinner)
 If matchLoser>0 Then gamMatchLoser(gamDate,gamSegments(gamDate))=pChar(matchLoser)
EndIf
;proceed
promoFoc=0
screen=1
If screenAgenda=11 And game=0 Then screen=15
If game=1 Then screen=21
If optOnline>0 Then screen=1 : optOnline=0
End Function

;-----------------------------------------------------------------
;////////////////////// RELATED FUNCTIONS ////////////////////////
;-----------------------------------------------------------------

;CROWD MANAGEMENT
Function ManageCrowd()
 ;get entertainment factor
 entExpect=1000+(1000*matchMins)
 If optLength=1 Then entExpect=PercentOf#(entExpect,100+entShortOffset)
 If optLength=3 Then entExpect=PercentOf#(entExpect,100-entLongOffset)
 If entExpect<1000 Then entExpect=1000
 If entExpect>5000 Then entExpect=5000
 entSatisfy#=Float(entTotal)/Float(entExpect)
 If entSatisfy#<0.1 Then entSatisfy#=0.1
 If entSatisfy#>1.0 Then entSatisfy#=1.0
 If screen<>50 Or matchState=<1 Then entSatisfy#=0.5
 ;fluctuate volume
 ;randy=Rnd(0,1000)
 ;If randy=<1 Then crowdVolTarget#=Rnd(0.5,1.0)
 crowdVolTarget#=0.5+(entSatisfy#/2)
 If crowdVol#>crowdVolTarget# Then crowdVol#=crowdVol#-0.00025
 If crowdVol#<crowdVolTarget# Then crowdVol#=crowdVol#+0.00025
 If arenaType=1 Then attendance#=20+PercentOf#(40,arenaAttendance)
 If arenaType=>2 Then attendance#=25+PercentOf#(75,arenaAttendance)
 If arenaCrowd=0 Then attendance#=0
 ChannelVolume chCrowd,PercentOf#(crowdVol#,attendance#)
 If camZ#>400 Or camZ#<-400 Then ChannelVolume chCrowd,PercentOf#(crowdVol#,attendance#)/2 
 ;fluctuate pitch
 ;randy=Rnd(0,1000)
 ;If randy=0 Then crowdPitchTarget#=Rnd(35000,48000)
 ;If randy=1 Then crowdPitchTarget#=44100
 crowdPitchTarget#=37000+(10000*entSatisfy#) 
 If crowdPitch#>crowdPitchTarget# Then crowdPitch#=crowdPitch#-5
 If crowdPitch#<crowdPitchTarget# Then crowdPitch#=crowdPitch#+5
 ChannelPitch chCrowd,crowdPitch#
 ;random boring chant
 randy=Rnd(0,10000)
 If matchState<>2 And matchMins>1
  If randy=<1 And GetMatchRating(entTotal)=1 Then Pop(0,11,0)
  If randy=0 And GetMatchRating(entTotal)=2 Then Pop(0,11,0)
 EndIf
 If randy=<10 And matchState=2 And promoLogic=0 Then Pop(0,11,0)
 ;animate cut-out's
 If arenaCrowd>0 And optCrowdAnim=>1
  AnimateCrowd()
 EndIf
 ;signs
 If no_signs>0
  For cyc=1 To no_signs
   randy=Rnd(0,500)
   If randy=<1 Then Animate sign(cyc),1,Rnd#(0.25,1.0),0,10
  Next
 EndIf
End Function

;ANIMATE CROWD
Function AnimateCrowd()
 For cyc=1 To 200
  If FindChild(world,"Crowd"+Dig$(cyc,100))>0
   ;shake
   randy=Rnd(0,8)
   If randy=0 Then crowdTY#(cyc)=crowdSource#(cyc)+Rnd(-1.0,1.0)
   If crowdY#(cyc)<crowdTY#(cyc) Then crowdY#(cyc)=crowdY#(cyc)+0.125
   If crowdY#(cyc)>crowdTY#(cyc) Then crowdY#(cyc)=crowdY#(cyc)-0.125
   If crowdY#(cyc)<crowdTY#(cyc)-0.075 Or crowdY#(cyc)>crowdTY#(cyc)+0.075
    limb=FindChild(world,"Crowd"+Dig$(cyc,100))
    PositionEntity limb,EntityX(limb),crowdY#(cyc),EntityZ(limb)
   EndIf
   ;hide
   If KeyDown(66) Then HideEntity FindChild(world,"Crowd"+Dig$(cyc,100)) : optCrowdAnim=0
  EndIf
 Next
End Function

;CROWD REACTION
Function Pop(cyc,sound,vol#)
 ;bind response to allegiance
 If cyc>0
  If sound=2 Or sound=3 Then sound=2+pHeel(cyc)
  If sound=4 Or sound=5 Then sound=4+pHeel(cyc) 
  If sound=6 Or sound=7 Then sound=6+pHeel(cyc)
 EndIf
 ;determine volume
 If vol#=0 Then vol#=Rnd(0.5,1.1) 
 If cyc>0 Then vol#=PercentOf#(vol#,pPopularity(cyc))
 If vol#>crowdVol# Then vol#=crowdVol#
 If arenaType=1 Then attendance#=20+PercentOf#(40,arenaAttendance)
 If arenaType=>2 Then attendance#=25+PercentOf#(75,arenaAttendance)
 If arenaCrowd=0 Then attendance#=0 
 vol#=PercentOf#(vol#,attendance#)
 If screen=50 And (camZ#>400 Or camZ#<-400) Then vol#=vol#/2
 If game=1 And matchState=2 And promoLogic=0 Then vol#=vol#/4 
 ;deliver sound
 If sound>1 And arenaCrowd>0
  SoundVolume sCrowd(sound),vol#
  EmitSound sCrowd(sound),cam
 EndIf
End Function

;REDUCE VOLUME BETWEEN ROOMS
Function RoomVolume#(x#,z#,vol#)
 newVol#=vol#
 If (camZ#>395 And z#<395) Or (camZ#<395 And z#>395) Or (camZ#>-395 And z#<-395) Or (camZ#<-395 And z#>-395) Then newVol#=vol#/2
 Return newVol#
End Function

;ENTERTAINMENT ANALYSIS
Function MonitorEntertainment()
 ;key checks
 FindClose()
 FindChemistry()
 FindFeud()
 ;assess match size
 entWorkers=no_wrestlers
 If matchTeams=2 Then entWorkers=3
 If matchCountOuts=3 And matchCage=0 Then entWorkers=PercentOf#(no_wrestlers,90)
 If matchTeams=-1 Then entWorkers=no_wrestlers/2 
 If entWorkers<2 Then entWorkers=2
 ;potential
 entPotential=0 : divider=0 
 For cyc=1 To no_wrestlers 
  If LegalMan(cyc) And pOutTim(cyc)>0 And pEliminated(cyc)=0 
   entPotential=entPotential+(PercentOf#(charPopularity(pChar(cyc)),50)+60)
   divider=divider+1
  EndIf
 Next
 entPotential=entPotential+entFeud  
 If matchReward=2 Or matchReward=7 Or fed=0 Or (screenAgenda=11 And cupFoc(cupSlot)=1) Then entPotential=entPotential+(entPotential/10)
 If matchReward=3 Or matchReward=4 Or matchReward=6 Or (screenAgenda=11 And cupFoc(cupSlot)>1) Then entPotential=entPotential+(entPotential/15)
 If divider<1 Then divider=1
 entPotential=entPotential/divider
 If entPotential<75 Then entPotential=75
 If entPotential>125 Then entPotential=125
 ;boredom
 entBored=(1+optRatingsLevel)+optLength
 If entChemistry=0 Then entBored=entBored+1
 If entClose=0 Then entBored=entBored+1
 If GetMatchRating(entTotal)=>5 Then entBored=entBored+1
 If GetMatchContent(entHardTotal)=>3 Then entBored=entBored+1 
 If matchTeams=2 And matchState=3 And teamRemaining(1)+teamRemaining(2)=>4
  If FindChaos() Then entBored=entBored+(((teamRemaining(1)+teamRemaining(2))/2)-1)
  If InsideRing(pX#(teamLegal(1)),pZ#(teamLegal(1)),0)=0 Or InsideRing(pX#(teamLegal(2)),pZ#(teamLegal(2)),0)=0
   entBored=entBored+(((teamRemaining(1)+teamRemaining(2))/2)-1)
  EndIf
 EndIf
 If matchMins<>matchTimeLim-1 
  boreTim=optLength+1
  If matchTeams=-1 Or matchTeams=2 Then boreTim=optLength+(no_wrestlers/2)
  If matchMins=>boreTim Then entBored=entBored+1
  If matchMins=>boreTim*2 Then entBored=entBored+1
  If matchMins=>boreTim*3 Then entBored=entBored+1 
  If matchMins=>boreTim*4 Then entBored=entBored+1
 EndIf
 If entBored>10 Then entBored=10
 If matchState=<2 Then entBored=2
 ;final breakdown
 randy=Rnd(0,entPotential*2)
 If randy=<entBored Then entScore=entScore-(35+(entTotal/300))
 If matchState=<1 Or (matchState=2 And matchPromo=16)
  If entScore<entHype Then entScore=entHype
 EndIf
 If entScore<0 Then entScore=0
 If matchState=4
  If entEndScore<0 Then entEndScore=entScore Else entScore=entEndScore
 EndIf
 If optLength=1 And entScore/entWorkers>PercentOf#(6000,100-entShortOffset) Then entScore=PercentOf#(6000*entWorkers,100-entShortOffset)
 If optLength=2 And entScore/entWorkers>6000 Then entScore=6000*entWorkers
 If optLength=3 And entScore/entWorkers>PercentOf#(6000,100+entLongOffset) Then entScore=PercentOf#(6000*entWorkers,100+entLongOffset)
 entTotal=entScore/entWorkers
 If optLength=1 Then entTotal=PercentOf#(entTotal,100+entShortOffset)
 If optLength=3 Then entTotal=PercentOf#(entTotal,100-entLongOffset)
 If entTotal>6000 Then entTotal=6000
 ;hardcore content
 randy=Rnd(0,50)
 If randy=<1 Then entHardcore=entHardcore-(1+(entHardcore/100))
 If entHardcore<0 Then entHardcore=0
 If matchState=4
  If entEndHardcore<0 Then entEndHardcore=entHardcore Else entHardcore=entEndHardcore
 EndIf
 If optLength=1 And entHardcore/entWorkers>PercentOf#(5000,100-entShortOffset) Then entHardcore=PercentOf#(5000*entWorkers,100-entShortOffset)
 If optLength=2 And entHardcore/entWorkers>5000 Then entHardcore=5000*entWorkers
 If optLength=3 And entHardcore/entWorkers>PercentOf#(5000,100+entLongOffset) Then entHardcore=PercentOf#(5000*entWorkers,100+entLongOffset)
 entHardTotal=entHardcore/entWorkers
 If optLength=1 Then entHardTotal=PercentOf#(entHardTotal,100+entShortOffset)
 If optLength=3 Then entHardTotal=PercentOf#(entHardTotal,100-entLongOffset)
 If entHardTotal>5000 Then entHardTotal=5000
End Function

;FIND CLOSENESS
Function FindClose()
 ;health average
 healthTotal=0 : healthWrestlers=0 : healthAverage=0
 For cyc=1 To no_wrestlers
  If pOutTim(cyc)>0 And LegalMan(cyc) And pEliminated(cyc)=0 Then healthTotal=healthTotal+pHealth(cyc) : healthWrestlers=healthWrestlers+1
 Next
 If healthWrestlers>0 Then healthAverage=healthTotal/healthWrestlers
 ;health gaps
 entClose=1
 If matchTeams=1
  teamHealth(1)=0 : teamHealth(2)=0 
  For cyc=1 To no_wrestlers
   If pOutTim(cyc)>0 And LegalMan(cyc) And pEliminated(cyc)=0 Then teamHealth(pTeam(cyc))=teamHealth(pTeam(cyc))+pHealth(cyc)
  Next
  swinger=PercentOf#(teamHealth(2),25)
  If swinger<500*optLength Then swinger=500*optLength
  If teamHealth(1)>teamHealth(2)+swinger Then entClose=0
  swinger=PercentOf#(teamHealth(1),25)
  If swinger<500*optLength Then swinger=500*optLength
  If teamHealth(2)>teamHealth(1)+swinger Then entClose=0
 Else
  For cyc=1 To no_wrestlers
   If pOutTim(cyc)>0 And LegalMan(cyc) And pEliminated(cyc)=0
    If GetPercent#(pHealth(cyc),5000*optLength)<GetPercent#(healthAverage,5000*optLength)-15 Then entClose=0
    If GetPercent#(pHealth(cyc),5000*optLength)>GetPercent#(healthAverage,5000*optLength)+15 Then entClose=0 
   EndIf
  Next
 EndIf
 ;accumilative scores
 If matchType=2 Or matchType=3 
  If matchTeams=<0 And matchLeader>0 Then entClose=0
  If matchTeams>0 And teamFalls(1)<>teamFalls(2) Then entClose=0 
 EndIf
 ;team numbers
 If matchTeams=1 And teamRemaining(1)<>teamRemaining(2) Then entClose=0
 ;rumbles are always close!
 If matchCountOuts=3 And matchCage=0 Then entClose=1 
End Function

;FIND FACE-HEEL CHEMISTRY
Function FindChemistry()
 ;find opposing chemistry
 entChemistry=0
 For cyc=1 To no_plays
  If LegalMan(cyc) Or (pRole(cyc)=0 And pChaosTim(cyc)>0) Or (pRole(cyc)=2 And pChaosTim(cyc)>0) Or pRole(cyc)=3
   For v=1 To no_plays
    If LegalMan(v) Or (pRole(v)=0 And pChaosTim(v)>0) Or (pRole(v)=2 And pChaosTim(v)>0) Or pRole(v)=3
     If (pFoc(cyc)=v And pFoc(v)=cyc) Or pGrappling(cyc)=v Or pGrappler(cyc)=v Or pPinning(cyc)=v Or pPinner(cyc)=v
      If cyc<>v And pTeam(cyc)<>pTeam(v) And pOutTim(cyc)>0 And pOutTim(v)>0 And pHeel(cyc)<>pHeel(v) Then entChemistry=1
     EndIf
    EndIf
   Next
  EndIf
 Next
End Function

;FIND A HEATED SITUATION
Function FindFeud()
 ;find heated contact
 feuderA=0 : feuderB=0
 For cyc=1 To no_plays 
  If pRole(cyc)<>2 Or pChaosTim(cyc)>0
   For v=1 To no_plays
    If pRole(v)<>2 Or pChaosTim(v)>0
     If cyc<>v And pTeam(cyc)<>pTeam(v) And charRelationship(pChar(cyc),pChar(v))<0 And charRelationship(pChar(cyc),pChar(v))=>-4
      If matchState=0 And pRole(cyc)=<1 And pRole(v)=<1 Then feuderA=cyc : feuderB=v
      If (pFoc(cyc)=v And pFoc(v)=cyc) Or pGrappling(cyc)=v Or pGrappler(cyc)=v Or pPinning(cyc)=v Or pPinner(cyc)=v
       If pOutTim(cyc)>0 And pOutTim(v)>0 Then feuderA=cyc : feuderB=v  
      EndIf
     EndIf
    EndIf
   Next
  EndIf
 Next
 ;calculate impact
 entFeud=0
 If feuderA>0 And feuderB>0
  entFeud=charPopularity(pChar(feuderA))+charPopularity(pChar(feuderB))
  entFeud=entFeud/10
 EndIf
End Function

;VIABLE TO SCORE ENTERTAINMENT POINTS?
Function EntertainViable(cyc,v)
 viable=0
 If cyc>0 
  If LegalMan(cyc) Or pRole(cyc)=1 Or pRole(cyc)=3 Or InsideRing(RealX#(cyc),RealZ#(cyc),-5) Then viable=1
 EndIf
 If v>0
  If LegalMan(v) Or pRole(v)=1 Or pRole(v)=3 Or InsideRing(RealX#(v),RealZ#(v),-5) Then viable=1
 EndIf
 Return viable
End Function

;CALCULATE MATCH ENTERTAINMENT RATING
Function GetMatchRating(score)
 value=1
 If score=>2000 Then value=2
 If score=>3000 Then value=3
 If score=>4000 Then value=4
 If score=>5000 Then value=5
 Return value
End Function

;CALCULATE MATCH HARDCORE CONTENT
Function GetMatchContent(score)
 value=1
 If score=>100 Then value=2
 If score=>500 Then value=3
 If score=>1000 Then value=4
 If score=>2000 Then value=5
 Return value
End Function

;DISPLAY ENTERTAINMENT
Function DisplayRatings()
 ;find ideal location
 entTX=725 : entTY=560
 ;If matchState=4 Then entTX=655 : entTY=565
 ;If entX<entTX Then entX=entX+10
 ;If entX>entTX Then entX=entX-10
 ;If entY<entTY Then entY=entY+1
 ;If entY>entTY Then entY=entY-1
 x=entX : y=entY
 ;display rating graphics
 If camTim=0
  If (entDisplay=>1 And matchState=3) Or matchState=4 
   DrawImage gRating(GetMatchRating(entTotal)),rX#(x),rY#(y)
  EndIf
  If (entDisplay=>2 And matchState=3) Or matchState=4
   SetFont font(8)
   ;Outline(Int(GetPercent#(entTotal,6000))+"%",rX#(x),rY#(y)-12,60,25,0,250,200,100)
   Outline(GetFigure$(entTotal),rX#(x),rY#(y)-12,60,25,0,250,200,100)
   DrawImage gHardcore(GetMatchContent(entHardTotal)),rX#(x)-3,rY#(y)+18
   ;SetFont fontStat(0)
   ;Outline(entHardTotal,rX#(x)+50,rY#(y)+28,0,0,0,255,255,255)
  EndIf
 EndIf
 ;display additional info
 If entDisplay=>3 And matchState=3
  SetFont font(2)
  r=40 : g=80 : b=40
  If entClose=1 Then r=100 : g=220 : b=100
  Outline("CLOSE",rX#(x),rY#(y)-91,0,0,0,r,g,b)
  r=80 : g=10 : b=80
  If entChemistry=1 Then r=240 : g=100 : b=240
  Outline("CHEMISTRY",rX#(x),rY#(y)-75,0,0,0,r,g,b)
  r=100 : g=50 : b=0
  If entFeud>0 Then r=250 : g=Rnd(100,150) : b=0
  Outline("HEAT",rX#(x),rY#(y)-59,0,0,0,r,g,b)
  Outline("POTENTIAL:",rX#(x)-20,rY#(y)-38,0,0,0,220,230,240)
  SetFont fontStat(1)
  percenter=Int(GetPercent#(entPotential-50,75))
  If percenter>99 Then percenter=99
  OutlineStraight(percenter+"%",rX#(x)+29,rY#(y)-39,0,0,0,250,240,200) 
 EndIf
 ;diagnostics
 ;SetFont fontStat(1)
 ;Outline("entPotential: "+entPotential,rX#(100),rY#(100),0,0,0,255,255,255)
 ;Outline("entBored: "+entBored,rX#(100),rY#(115),0,0,0,255,255,255)
 ;Outline("entScore: "+entScore,rX#(100),rY#(135),0,0,0,255,255,255)
 ;Outline("entHardcore: "+entHardcore,rX#(100),rY#(155),0,0,0,255,255,255)
 ;Outline("entWorkers: "+entWorkers,rX#(100),rY#(175),0,0,0,255,255,255)
 ;Outline("crowdVolTarget: "+crowdVolTarget#,rX#(100),rY#(200),0,0,0,255,255,255)
 ;Outline("crowdPitchTarget: "+crowdPitchTarget#,rX#(100),rY#(215),0,0,0,255,255,255)
End Function

;DECLARE FALL
Function DeclareFall(cyc,v)
 ;sound effects
 If matchBellTim=0 Then EmitSound sBell,cam : matchBellTim=10
 crowdVol#=1.0 : Pop(0,9,0)
 CauseChaos(100)
 ;assess consquences
 action=0
 If cyc>0 And matchType=>1 And matchType=<4 Then action=1 
 If cyc=0 And matchType=>2 And matchType=<4
  action=2
  If matchTeams=<0 And matchRemaining>2 Then action=0
  If matchTeams>0 And teamRemaining(pTeam(v))>1 Then action=0
 EndIf
 ;eliminate victim
 If action=0
  Pop(0,3-pHeel(v),1)
  entScore=entScore+(charPopularity(pChar(v))*2)
  If matchWinStyle=>3 And matchWinStyle=<5 Then entScore=entScore+(charPopularity(pChar(v))*2)
  If (RefViable(v)=13 Or RefViable(v)=14) And AttackViable(v)=>2 Then pDT(v)=pDT(v)+500 : pDizzyTim(v)=Rnd(pDT(v),pDT(v)*2) 
  pEliminated(v)=1 : pOutTim(v)=1000
  GetNewLegal(v)
  If matchType=4 And matchLeader=v Then matchLeader=0
  If matchType=4 And cyc>0 Then matchLeader=cyc
  If matchLoser=0 Then matchLoser=v
  camTempTim=50 : camTempFoc=v : camOldFoc=camFoc
  If matchWinStyle=6 Then PostMessage(charName$(pChar(v))+" has been disqualified!")
  If matchWinStyle<>6 Then PostMessage(charName$(pChar(v))+" has been eliminated!")
 EndIf
 ;award fall to aggressor
 If action=1
  Pop(cyc,Rnd(2,7),1)
  entScore=entScore+(charPopularity(pChar(cyc))*2)
  pFalls(cyc)=pFalls(cyc)+1
  teamFalls(pTeam(cyc))=teamFalls(pTeam(cyc))+1
  If matchType=4 Then matchLeader=cyc
  matchLoser=v
  camTempTim=50 : camTempFoc=cyc : camOldFoc=camFoc
  If matchType=4
   If matchTeams=<0 Then PostMessage(charName$(pChar(cyc))+" has taken the lead!")
   If matchTeams>0 Then PostMessage(charTeamName$(pChar(cyc))+" have taken the lead!")
  Else
   If matchTeams=<0 Then PostMessage(charName$(pChar(cyc))+" has scored "+Lower$(His$(charGender(pChar(cyc))))+" "+textNumber$(pFalls(cyc))+" fall!")
   If matchTeams>0 Then PostMessage(charTeamName$(pChar(cyc))+" have scored their "+textNumber$(teamFalls(pTeam(cyc)))+" fall!")
  EndIf
 EndIf
 ;punish victim
 If action=2
  Pop(0,3-pHeel(v),1) 
  entScore=entScore+(charPopularity(pChar(v))*2)
  If pFalls(v)>0
   pFalls(v)=pFalls(v)-1
   teamFalls(pTeam(v))=teamFalls(pTeam(v))-1
  Else
   If matchTeams>0
    If pTeam(v)=1 Then teamFalls(2)=teamFalls(2)+1 : matchLeader=teamLegal(2) : cyc=matchLeader
    If pTeam(v)=2 Then teamFalls(1)=teamFalls(1)+1 : matchLeader=teamLegal(1) : cyc=matchLeader
   Else
    For count=1 To no_plays
     If count<>v And LegalMan(count) Then pFalls(count)=pFalls(count)+1 : matchLeader=count : cyc=matchLeader
    Next
   EndIf
  EndIf
  If matchType=4 And matchLeader=v Then matchLeader=0
  If matchType<>5 Or matchLoser=0 Then matchLoser=v
  camTempTim=50 : camTempFoc=v : camOldFoc=camFoc
  If matchTeams=<0 Then PostMessage(charName$(pChar(v))+" has been penalized!")
  If matchTeams>0 Then PostMessage(charTeamName$(pChar(v))+" have been penalized!")
 EndIf
 ;set reactions
 For count=1 To no_plays
  If pRole(count)<>1
   If cyc>0 And pTeam(count)=pTeam(cyc) And pAnim(count)<>193 Then pReaction(count)=193
   If v>0 And pTeam(count)=pTeam(v) And pAnim(count)<>194 And pAnim(count)<>195 And pAnim(count)<>196 Then pReaction(count)=Rnd(194,196)
  EndIf
 Next
 ;reset count
 pOutCount(v)=0 : pPinCount(v)=0
End Function

;KEEP SCORE
Function KeepScore()
 ;reset count-out status
 If no_refs>0
  outs=0
  For cyc=1 To no_wrestlers
   If RefViable(cyc)=5 Or RefViable(cyc)=15 Then outs=outs+1
  Next
  If outs=0 And matchCounter>0 Then pReaction(matchCounter)=174 : matchCounter=0
 EndIf
 ;find wrestler with best health
 If matchType<>4
  matchLeader=0 : hi=0
  For cyc=1 To no_wrestlers
   If LegalMan(cyc) And pHealth(cyc)>hi Then matchLeader=cyc : hi=pHealth(cyc)
  Next
 EndIf
 ;first fall
 If matchType=1
  For cyc=1 To no_wrestlers
   If pRole(cyc)=0 And pFalls(cyc)=>1 Then EndMatch(cyc)
  Next
 EndIf
 ;best of three / ironman
 If matchType=2 Or matchType=3
  matchLeader=0 : hi=0
  For cyc=1 To no_wrestlers
   If pRole(cyc)=0 And pEliminated(cyc)=0
    If matchTeams>0
     If teamFalls(pTeam(cyc))>hi Then matchLeader=cyc : hi=teamFalls(pTeam(cyc))
     If pTeam(matchLeader)<>pTeam(cyc) And teamFalls(pTeam(cyc))=teamFalls(pTeam(matchLeader)) Then matchLeader=0
     If matchType=2 And teamFalls(pTeam(cyc))=>2 Then EndMatch(cyc)
    Else
     If pFalls(cyc)>hi Then matchLeader=cyc : hi=pFalls(cyc)
     If matchLeader<>cyc And pFalls(cyc)=pFalls(matchLeader) Then matchLeader=0
     If matchType=2 And pFalls(cyc)=>2 Then EndMatch(cyc)
    EndIf
   EndIf
  Next
  If matchMins=matchTimeLim And matchLeader>0 Then EndMatch(matchLeader)
 EndIf
 ;last fall wins (24/7)
 If matchType=4
  If matchMins=matchTimeLim And matchLeader>0 Then EndMatch(matchLeader)
 EndIf
 ;elimination
 matchRemaining=0 : matchSurvivor=0
 teamRemaining(1)=0 : teamRemaining(2)=0
 For cyc=1 To no_wrestlers
  If pRole(cyc)=0 And pEliminated(cyc)=0
   matchRemaining=matchRemaining+1 : matchSurvivor=cyc
   teamRemaining(pTeam(cyc))=teamRemaining(pTeam(cyc))+1
  EndIf
 Next
 If matchRemaining>0
  If matchTeams=<0 And matchRemaining=1 Then EndMatch(matchSurvivor)
  If matchTeams>0 And teamRemaining(2)=0 Then EndMatch(teamLegal(1))
  If matchTeams>0 And teamRemaining(1)=0 Then EndMatch(teamLegal(2))
 Else
  EndMatch(0)
 EndIf
 ;time out
 If matchState=3 And matchTeams=>0 And matchTimeLim>0 And matchMins=>matchTimeLim
  matchWinStyle=0
  EndMatch(0)
 EndIf
End Function

;END MATCH
Function EndMatch(cyc)
 If matchState<>4
  ;change match status
  If matchBellTim=0 Then PlaySound sBell : matchBellTim=10
  matchWinner=cyc : matchState=4 : matchTim=0
  ;force a cup winner
  If matchWinner=0 And screenAgenda=11 
   If matchTeams>0
    If pHealth(1)+pHealth(2)=>pHealth(3)+pHealth(4) Then matchWinner=1 Else matchWinner=3
   Else
    If pHealth(1)=>pHealth(2) Then matchWinner=1 Else matchWinner=2
   EndIf
   matchWinStyle=7
  EndIf
  ;create a loser
  If matchWinner>0 And (matchLoser=0 Or matchLoser=matchWinner)
   Repeat
    matchLoser=Rnd(1,no_wrestlers)
   Until pTeam(matchLoser)<>pTeam(matchWinner)
  EndIf
  ;switch focus to winner
  If matchWinner>0 
   camFoc=matchWinner : camType=12 : camA#=CleanAngle#(pA#(camFoc))
   camTempTim=0 : camTempFoc=0
  EndIf
  ;crowd reaction
  crowdVol#=1.0
  Pop(matchWinner,Rnd(2,9),1)
  Pop(matchWinner,Rnd(2,9),1)
  If matchWinner>0 And screenAgenda<>12 And (fed<>7 Or arenaPreset=>11)
   If game=0 Or fed=0 Or fedProduction(charFed(gamChar),6)>0 Then PlayTheme(pChar(matchWinner))
  EndIf
  If matchWinner>0 
   entScore=entScore+(charPopularity(pChar(matchWinner))/2)
   If matchReward=>2 Or screenAgenda=11 Then entScore=entScore+(charPopularity(pChar(matchWinner))/2)
   If matchReward=2 Or matchReward=7 Or (screenAgenda=11 And cupFoc(cupSlot)=1) Then entScore=entScore+(charPopularity(pChar(matchWinner))/2)
  EndIf
  ;affect other characters
  For v=1 To no_plays
   ;queue reactions
   If matchWinner>0
    If pRole(v)<>1
     If pTeam(v)=pTeam(matchWinner) And pAnim(v)<>193 Then pReaction(v)=193
     If pTeam(v)<>pTeam(matchWinner) And pAnim(v)<>194 And pAnim(v)<>195 And pAnim(v)<>196 Then pReaction(v)=Rnd(194,196)
    EndIf
    If matchWinner>0 And pTeam(v)<>pTeam(matchWinner) Then pFoc(v)=matchWinner
    pAgenda(v)=0
   EndIf
   If pRole(v)=1 And matchWinner=0 Then pReaction(v)=172
   ;lose hair
   If matchReward=6 And matchWinner>0 And pTeam(v)<>pTeam(matchWinner) And pRole(v)=0 
    Pop(0,7,1) : Pop(0,8,1)
    For coz=1 To 3
     If charHairStyle(pChar(v),coz)=5 Or charHairStyle(pChar(v),coz)=6 Or charHairStyle(pChar(v),coz)=22 Or charHairStyle(pChar(v),coz)=34 Then charHairStyle(pChar(v),coz)=1
     If charHairStyle(pChar(v),coz)=7 Or charHairStyle(pChar(v),coz)=23 Or charHairStyle(pChar(v),coz)=35 Then charHairStyle(pChar(v),coz)=2
     If charHairStyle(pChar(v),coz)>3 Then charHairStyle(pChar(v),coz)=3
    Next
    ApplyHairstyle(v,0)
    pBlindTim(v)=500
   EndIf
   ;leave promotion
   If matchReward=7 And matchWinner>0 And pTeam(v)<>pTeam(matchWinner) And pRole(v)=0
    If game=0 Then MoveChar(pChar(v),7)
   EndIf
  Next
  ;change title holders?
  If fed>0 And matchWinner>0 And matchWinStyle=>1 And matchWinStyle=<5
   If matchReward=2 Then fedChampWorld(fed)=pChar(matchWinner) : WriteHistory(fed,1)
   If matchReward=3 Then fedChampInter(fed)=pChar(matchWinner) : WriteHistory(fed,2)
   If matchReward=4 
    fedChampTag(fed,1)=pChar(matchWinner)
    fedChampTag(fed,2)=pChar(matchWinner)
    For v=1 To no_wrestlers  
     If cyc<>v And pTeam(v)=pTeam(matchWinner) Then fedChampTag(fed,2)=pChar(v)
    Next
    If fedChampTag(fed,1)<>fedChampTag(fed,2) 
     charPartner(fedChampTag(fed,1))=fedChampTag(fed,2)
     charPartner(fedChampTag(fed,2))=fedChampTag(fed,1) 
    EndIf
   EndIf
   WriteHistory(fed,3)
  EndIf
  If matchWinner>0 And matchReward=5
   fedCupHolder(charFed(pChar(matchWinner)))=pChar(matchWinner)
   WriteHistory(charFed(pChar(matchWinner)),4)
  EndIf
  ;describe ending
  If matchWinner=0 Then PostMessage("Nobody won this contest...")
  If matchWinner>0
   If matchTeams>0 
    If no_wrestlers=3 And matchWinner=1
     PostMessage(charName$(pChar(matchWinner))+" Wins!") 
    Else
     PostMessage(charTeamName$(pChar(matchWinner))+" Win!")
    EndIf
    If no_wrestlers=<4 And TitleHolder(pChar(matchWinner),3) Then comSuffix$="Tag Champions"
   Else
    PostMessage(charName$(pChar(matchWinner))+" Wins!") 
    If TitleHolder(pChar(matchWinner),3) Then comSuffix$="Tag Champion"
    If TitleHolder(pChar(matchWinner),2) Then comSuffix$="Inter Champion"
    If TitleHolder(pChar(matchWinner),1) Then comSuffix$="World Champion"
   EndIf
   If fed=charFed(pChar(matchWinner))
    If TitleHolder(pChar(matchWinner),3) And pChar(matchWinner)<>fedOldChampTag(fed,1) And pChar(matchWinner)<>fedOldChampTag(fed,2) Then comSuffix$="New Tag Champions!"
    If TitleHolder(pChar(matchWinner),2) And pChar(matchWinner)<>fedOldChampInter(fed) Then comSuffix$="New Inter Champion!"
    If TitleHolder(pChar(matchWinner),1) And pChar(matchWinner)<>fedOldChampWorld(fed) Then comSuffix$="New World Champion!"
   EndIf
   If matchTeams>0 
    If matchReward=5 Then comSuffix$="Trophy Winners!"
    If matchReward=6 Then comSuffix$=charTeamName$(pChar(matchLoser))+" Lose Hair!"
    If matchReward=7 Then comSuffix$=charTeamName$(pChar(matchLoser))+" Leave Town!"
   Else
    If matchReward=5 Then comSuffix$="Trophy Winner!"
    If matchReward=6 Then comSuffix$=charName$(pChar(matchLoser))+" Loses Hair!"
    If matchReward=7 Then comSuffix$=charName$(pChar(matchLoser))+" Leaves Town!"
   EndIf
  EndIf
 EndIf
End Function

;CREATE COMMENTARY MESSAGE
Function PostMessage(message$)
 comScript$=message$
 comAffix$="" : comSuffix$=""
 If message$<>"" Then comTim=0 : comSpeed=1
End Function

;DISPLAY COMMENTARY (62,570)
Function DisplayCommentary()
 ;base logo
 x=62 : y=rY#(600)-30
 DrawImage gLogo(3),x,y
 ;rope lines
 If comTim>0 Or matchPause>0
  reveal=comTim*4
  If reveal>100 Or matchPause>0 Then reveal=100
  Color 255,25,25 : Rect x+60,y-22,PercentOf#(470,reveal),3,1
  Color 80,5,5 : Rect x+60,y-22,PercentOf#(470,reveal),3,0
  Color 190,190,190 : Rect x+53,y-6,PercentOf#(460,reveal),3,1
  Color 100,100,100 : Rect x+53,y-6,PercentOf#(460,reveal),3,0
  Color 35,90,220 : Rect x+47,y+8,PercentOf#(450,reveal),3,1
  Color 5,5,80 : Rect x+47,y+8,PercentOf#(450,reveal),3,0
 EndIf
 ;message(s)
 If matchPause>0
  script$="Paused"
  SqueezeFont(script$,450,25)
  If fontNumber=>8 Then offset=-6 Else offset=-7
  OutlineStraight(script$,x+57,y+offset,0,0,0,255,255,255)
  offset=10+StringWidth(script$)
  SqueezeFont("(Press 'P' to resume play)",500-offset,16)
  OutlineStraight("(Press 'P' to resume play)",(x+57)+offset,y-6,0,0,0,175,175,175)
 Else
  If comTim>5
   SqueezeFont(comScript$,450,25)
   If fontNumber=>8 Then offset=-6 Else offset=-7
   OutlineStraight(Left$(comScript$,(comTim-5)*2),x+57,y+offset,0,0,0,255,255,255)
   offset=10+StringWidth(comScript$)
   If reveal=100
    SqueezeFont(comAffix$,500-offset,16)
    OutlineStraight(comAffix$,(x+57)+offset,y-6,0,0,0,175,175,175)
    SqueezeFont(comSuffix$,250,14)
    OutlineStraight(comSuffix$,x+52,y+12,0,0,0,255,Rnd(175,225),100)
   EndIf
  EndIf
 EndIf
End Function

;DISPLAY METERS
Function DisplayMeters()
 ;reset list
 For count=0 To 2
  meterSlot(count)=0
  For cyc=1 To optPlayLim
   meterList(count,cyc)=0
   pListed(cyc)=0
  Next
 Next
 ;populate list
 For cyc=1 To no_wrestlers
  excluded=0
  If optMeters=1
   If matchPlayer>0 And pListed(matchPlayer)=0 And pControl(cyc)=0
    If meterSlot(0)=>3 Or (meterSlot(0)=>2 And matchMulti=1) Then excluded=1
    If matchTeams>0 And meterSlot(pTeam(matchPlayer))=>1 Then excluded=1
   EndIf
   If matchTeams>0 And LegalMan(cyc)=0 And meterSlot(pTeam(cyc))=>1 Then excluded=1
  EndIf
  If excluded=0 And pOutTim(cyc)=>2 And pHidden(cyc)=0 And pEliminated(cyc)=0
   If matchTeams=<0 Then meterSlot(0)=meterSlot(0)+1 : meterList(0,meterSlot(0))=cyc : pListed(cyc)=meterSlot(0)
   If matchTeams>0 Then meterSlot(pTeam(cyc))=meterSlot(pTeam(cyc))+1 : meterList(pTeam(cyc),meterSlot(pTeam(cyc)))=cyc : pListed(cyc)=meterSlot(pTeam(cyc))
  EndIf
 Next
 ;display individuals
 baseY=30 : spacing=35
 If matchTeams=<0 And meterSlot(0)>0
  no_slots=meterSlot(0)
  If optMeters=1 And no_slots>4 Then no_slots=4
  If no_slots>8 Then no_slots=8
  For cyc=1 To no_slots
   If no_slots=1 Then x=400 : y=baseY
   If no_slots=2
    If cyc=1 Then x=250 : y=baseY
    If cyc=2 Then x=550 : y=baseY
   EndIf 
   If no_slots=3 Or no_slots=5
    If cyc=1 Then x=200 : y=baseY
    If cyc=2 Then x=400 : y=baseY
    If cyc=3 Then x=600 : y=baseY
   EndIf
   If no_slots=4 Or no_slots=>6
    If cyc=1 Then x=115 : y=baseY
    If cyc=2 Then x=305 : y=baseY
    If cyc=3 Then x=495 : y=baseY
    If cyc=4 Then x=685 : y=baseY
   EndIf
   If no_slots=5
    If cyc=4 Then x=300 : y=baseY+spacing
    If cyc=5 Then x=500 : y=baseY+spacing
   EndIf
   If no_slots=6
    If cyc=5 Then x=205 : y=baseY+spacing
    If cyc=6 Then x=595 : y=baseY+spacing
   EndIf
   If no_slots=7
    If cyc=5 Then x=205 : y=baseY+spacing
    If cyc=6 Then x=400 : y=baseY+spacing
    If cyc=7 Then x=595 : y=baseY+spacing
   EndIf
   If no_slots=8
    If cyc=5 Then x=115 : y=baseY+spacing
    If cyc=6 Then x=305 : y=baseY+spacing
    If cyc=7 Then x=495 : y=baseY+spacing
    If cyc=8 Then x=685 : y=baseY+spacing
   EndIf
   DrawMeter(meterList(0,cyc),rX#(x),rY#(y))
  Next
 EndIf
 ;display left-hand team
 If matchTeams>0 And meterSlot(1)>0
  no_slots=meterSlot(1)
  If optMeters=1 And no_slots>2 Then no_slots=2
  If no_slots>5 Then no_slots=5
  For cyc=1 To no_slots
   If cyc=1 Then x=280 : y=baseY
   If cyc=2 Then x=110 : y=baseY
   If cyc=3 Then x=110 : y=baseY+spacing
   If cyc=4 Then x=280 : y=baseY+spacing
   If cyc=5 Then x=110 : y=(baseY+spacing)+spacing
   DrawMeter(meterList(1,cyc),rX#(x),rY#(y))
  Next
 EndIf
 ;display right-hand team
 If matchTeams>0 And meterSlot(2)>0
  no_slots=meterSlot(2)
  If optMeters=1 And no_slots>2 Then no_slots=2
  If no_slots>5 Then no_slots=5
  For cyc=1 To no_slots
   If cyc=1 Then x=520 : y=baseY
   If cyc=2 Then x=690 : y=baseY
   If cyc=3 Then x=690 : y=baseY+spacing
   If cyc=4 Then x=520 : y=baseY+spacing
   If cyc=5 Then x=690 : y=(baseY+spacing)+spacing
   DrawMeter(meterList(2,cyc),rX#(x),rY#(y))
  Next
 EndIf
End Function

;DRAW HEALTH METER
Function DrawMeter(cyc,x,y)
 ;elongated offset
 x=x+8
 ;health meter
 fader=4
 Color 0,0,0 : Rect x-49,y+1,100,11,1
 r=225 : g=0 : b=0
 If LegalMan(cyc)=0 Then r=r-(r/fader) : g=g-(g/fader) : b=b-(b/fader)
 Color r,g,b : Rect x-50,y,100,8,1
 r=125 : g=0 : b=0
 If LegalMan(cyc)=0 Then r=r-(r/fader) : g=g-(g/fader) : b=b-(b/fader)
 Color r,g,b : Rect x-50,y,100,8,0
 meter=GetPercent#(pShowHealth(cyc),5000*optLength)
 r=0 : g=225 : b=0
 If FindInjury(cyc)>0 Then r=Rnd(130,220) : g=0 : b=0
 If LegalMan(cyc)=0 Then r=r-(r/fader) : g=g-(g/fader) : b=b-(b/fader) 
 Color r,g,b : Rect x-50,y,meter,8,1
 r=0 : g=125 : b=0
 If FindInjury(cyc)>0 Then r=80 : g=0 : b=0
 If LegalMan(cyc)=0 Then r=r-(r/fader) : g=g-(g/fader) : b=b-(b/fader)
 Color r,g,b : Rect x-50,y,meter,8,0
 ;heat meter
 r=140 : g=140 : b=140
 If LegalMan(cyc)=0 Then r=r-(r/fader) : g=g-(g/fader) : b=b-(b/fader)
 Color r,g,b : Rect x-50,y+8,100,3,1
 r=70 : g=70 : b=70
 If LegalMan(cyc)=0 Then r=r-(r/fader) : g=g-(g/fader) : b=b-(b/fader)
 Color r,g,b : Rect x-50,y+8,100,3,0
 meter=pShowHeat(cyc)
 r=255 : g=255 : b=0
 If pSpecial(cyc)>0 Then r=255 : g=Rnd(150,250) : b=0
 If LegalMan(cyc)=0 Then r=r-(r/fader) : g=g-(g/fader) : b=b-(b/fader)
 Color r,g,b : Rect x-50,y+8,meter,3,1
 r=125 : g=85 : b=5
 If LegalMan(cyc)=0 Then r=r-(r/fader) : g=g-(g/fader) : b=b-(b/fader)
 Color r,g,b : Rect x-50,y+8,meter,3,0
 ;indicate leader
 If matchType=>2 And matchType=<3
  If cyc=matchLeader Or pTeam(cyc)=pTeam(matchLeader) Then DrawImage gCrown,x,y-10
 EndIf
 If matchType=4 And cyc=matchLeader Then DrawImage gCrown,x,y-10
 ;name 
 r=255 : g=255 : b=255
 If controlTim>0 And cyc=matchPlayer Then r=200 : g=200 : b=255
 For j = 1 To 4
   If joyControlTim(j) > 0 And pControl(cyc)=2 And pJoystick(cyc)=j-1 Then r=200 : g=200 : b=255
 Next
 If LegalMan(cyc)=0 Then r=r-(r/fader) : g=g-(g/fader) : b=b-(b/fader)
 namer$=charName$(pChar(cyc))
 If optOnline>0 And pController(cyc)>0 Then namer$=NetPlayerName$(pController(cyc))
 SqueezeFont(namer$,120,16) 
 offset=2-fontNumber
 Outline(namer$,x,(y-3)+offset,0,0,0,r,g,b)
 ;stats
 SetFont fontStat(2)
 ;If matchType=>2 And matchType=<4
  ;If cyc=matchLeader Or pTeam(cyc)=pTeam(matchLeader) Then SetFont fontStat(2)
 ;EndIf
 namer$=""
 If matchType=>2 And matchType=<3 Then namer$=pFalls(cyc) : r=200 : g=200 : b=255
 If LegalMan(cyc) And pOutCount(cyc)>0 Then namer$=pOutCount(cyc) : r=255 : g=200 : b=200
 If LegalMan(cyc)=0 Then r=r-(r/fader) : g=g-(g/fader) : b=b-(b/fader)
 OutlineStraight(namer$,x+54,y+5,0,0,0,r,g,b)
 ;photo
 reveal=(64-PortraitHead#(pChar(cyc)))+10
 DrawImageRect charPhoto(pChar(cyc)),x-62,(y+12)+(64-reveal),0,0,76,reveal
End Function

;START ONLINE GAME
Function StartOnlineGame()
 If optOnline=0
  optOnline=StartNetGame()
 EndIf
 ;enter name
 Cls
	DrawImage gBackground,rX#(400),rY#(300)
    DrawImage gLogo(1),rX#(400),rY#(250)
    DrawImage gMDickie,rX#(400),rY#(530) 
 Flip 
 Repeat
	SetFont font(7)
	Color 255,255,255
	Locate rX#(100),rY#(425)
	netName$=Input$("Please enter a username: ")
 Until netName$<>""
 netID=CreateNetPlayer(netName$)
 netControl=0
 ;default match
 optEntrances=0
 optReferees=0 : optManagers=0 : optIntruders=0
 GetMatchRules(14)
 For cyc=1 To no_plays
  pChar(cyc)=cyc
  pControl(cyc)=3 : pController(cyc)=0
 Next
 If optOnline=2 Then netControl=1 : pController(netControl)=netID
 screen=50
End Function

;MANAGE NETWORK
Function ManageNetwork()
 While RecvNetMsg()
		Select NetMsgType()
		
		Case 1:
		  If NetMsgFrom()<>netID
		   For v=1 To no_plays
			If pController(v)=NetMsgFrom() Then UnpackOnlineData(NetMsgData$(),v) : Exit
		   Next
		  EndIf
		Case 10:
			PostMessage(NetPlayerName$(NetMsgFrom())+": "+NetMsgData$())
		Case 100:
			For v=1 To no_plays
			 If pController(v)=0 Then pController(v)=NetMsgFrom() : Exit
		    Next
			PostMessage(NetPlayerName$(NetMsgFrom())+" has joined the game.")
		Case 101:
			PostMessage(NetPlayerName$(NetMsgFrom())+" has left the game.")
		Case 102:
			PostMessage("New host!")
		Case 200:
			PostMessage("Connection lost!")
			optOnline=0
			
		End Select
	Wend
End Function

;pack player details into a string.
Function PackOnlineData$(cyc)
 stringer$=""
 For v=1 To no_plays
  If optOnline=2 Then stringer$=stringer$+LSet$(pController(v),8) Else stringer$=stringer$+LSet$(0,8) 
 Next
 stringer$=stringer$+LSet$(cUp(cyc),8)+LSet$(cDown(cyc),8)+LSet$(cLeft(cyc),8)+LSet$(cRight(cyc),8)
 stringer$=stringer$+LSet$(cAttack(cyc),8)+LSet$(cRun(cyc),8)+LSet$(cBlock(cyc),8)
 stringer$=stringer$+LSet$(cGrapple(cyc),8)+LSet$(cPickUp(cyc),8)+LSet$(cSwitch(cyc),8)+LSet$(cTaunt(cyc),8)  
 Return stringer$
End Function

;unpack player details from a string
Function UnpackOnlineData(msg$,cyc)
 n=1
 For v=1 To no_plays
  If optOnline=1 Then pController(v)=Mid$(msg$,n,8) 
  n=n+8
 Next
 cUp(cyc)=Mid$(msg$,n,8) : n=n+8
 cDown(cyc)=Mid$(msg$,n,8) : n=n+8
 cLeft(cyc)=Mid$(msg$,n,8) : n=n+8
 cRight(cyc)=Mid$(msg$,n,8) : n=n+8
 cAttack(cyc)=Mid$(msg$,n,8) : n=n+8
 cRun(cyc)=Mid$(msg$,n,8) : n=n+8
 cBlock(cyc)=Mid$(msg$,n,8) : n=n+8
 cGrapple(cyc)=Mid$(msg$,n,8) : n=n+8
 cPickUp(cyc)=Mid$(msg$,n,8) : n=n+8
 cSwitch(cyc)=Mid$(msg$,n,8) : n=n+8
 cTaunt(cyc)=Mid$(msg$,n,8) : n=n+8
End Function