;//////////////////////////////////////////////////////////////////////////////
;----------------------- WRESTLING MPIRE 2008: TOURNAMENTS --------------------
;//////////////////////////////////////////////////////////////////////////////

;//////////////////////////////////////////////////////////////////////////////
;--------------------------- 16. TOURNAMENT SETUP -----------------------------
;//////////////////////////////////////////////////////////////////////////////
Function TournamentSetup()
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
foc=9 : oldfoc=foc
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
	  If foc=>7 And foc=<8 Then go=1
	  If go=1 And foc=8 And cupSize(cupSlot)=0 Then BlockAccess()
	  If foc=9 Then go=-1
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
	  PlaySound sMenuBrowse : keytim=5
	  If foc=1 
	   Repeat
	    optCupFed=optCupFed-1
	    If optCupFed<0 Then optCupFed=7
	   Until optCupFed=0 Or (optCupTeams=0 And fedSize(optCupFed)=>optCupSize) Or (optCupTeams=1 And fedSize(optCupFed)=>optCupSize*2)
	  EndIf
	  If foc=2 Then optCupTeams=optCupTeams-1
	  If foc=3 
	   Repeat
	    optCupReward=optCupReward-1 : satisfied=1
	    If optCupReward<0 Then optCupReward=3
	    If optCupReward>0 And optCupFed=0 Then satisfied=0
	    If optCupReward>0 And game=0 And slot>0 Then satisfied=0
	    If optCupReward=>1 And optCupReward=<2 And optCupTeams=1 Then satisfied=0
	    If optCupReward=3 And optCupTeams=0 Then satisfied=0
	   Until satisfied=1
	  EndIf
	  If foc=4 Then optCupSize=optCupSize/2
	  If foc=5 Then optCupSelect=optCupSelect-1
	  If foc=6 Then optCupControl=optCupControl-1
	 EndIf
	 ;search right
	 If KeyDown(205) Or JoyXDir()=1 Or MouseDown(1)
	  PlaySound sMenuBrowse : keytim=5 
	  If foc=1 
	   Repeat
	    optCupFed=optCupFed+1
	    If optCupFed>7 Then optCupFed=0
	   Until optCupFed=0 Or (optCupTeams=0 And fedSize(optCupFed)=>optCupSize) Or (optCupTeams=1 And fedSize(optCupFed)=>optCupSize*2)
	  EndIf
	  If foc=2 Then optCupTeams=optCupTeams+1
	  If foc=3 
	   Repeat
	    optCupReward=optCupReward+1 : satisfied=1
	    If optCupReward>3 Then optCupReward=0
	    If optCupReward>0 And optCupFed=0 Then satisfied=0
	    If optCupReward>0 And game=0 And slot>0 Then satisfied=0
	    If optCupReward=>1 And optCupReward=<2 And optCupTeams=1 Then satisfied=0
	    If optCupReward=3 And optCupTeams=0 Then satisfied=0
	   Until satisfied=1
	  EndIf
	  expand=1
	  If optCupFed>0 And optCupTeams=0 And fedSize(optCupFed)<optCupSize*2 Then expand=0
	  If optCupFed>0 And optCupTeams=1 And fedSize(optCupFed)<(optCupSize*2)*2 Then expand=0
	  If optCupFed=0 And optCupTeams=0 And no_chars<optCupSize*2 Then expand=0
	  If optCupFed=0 And optCupTeams=1 And no_chars<(optCupSize*2)*2 Then expand=0
	  If foc=4 And expand=1 Then optCupSize=optCupSize*2
	  If foc=5 Then optCupSelect=optCupSelect+1
	  If foc=6 Then optCupControl=optCupControl+1 
	 EndIf 
	 ;reset status
	 If KeyDown(29) And KeyDown(19)
	  PlaySound sTrash : keytim=10
	  cupSize(cupSlot)=0
	 EndIf
	EndIf  
	;limits
	If foc<1 Then foc=9
	If foc>9 Then foc=1 
	If optCupFed<0 Then optCupFed=7
	If optCupFed>7 Then optCupFed=0
	If game=1 Then optCupFed=charFed(gamChar)
	If optCupTeams<0 Then optCupTeams=1
	If optCupTeams>1 Then optCupTeams=0  
	If optCupReward<0 Then optCupReward=3
	If optCupReward>3 Then optCupReward=0   
	If optCupFed>0 And optCupTeams=1 And fedSize(optCupFed)<8 Then optCupTeams=0
	If optCupFed=0 And optCupTeams=1 And no_chars<8 Then optCupTeams=0  
	If optCupSize<4 Then optCupSize=4
	If optCupSize>32 Then optCupSize=32 
	If optCupFed>0 And optCupTeams=0 And optCupSize>fedSize(optCupFed) Then optCupSize=4
	If optCupFed>0 And optCupTeams=1 And optCupSize*2>fedSize(optCupFed) Then optCupSize=4 
	If optCupFed=0 And optCupTeams=0 And optCupSize>no_chars Then optCupSize=4
	If optCupFed=0 And optCupTeams=1 And optCupSize*2>no_chars Then optCupSize=4    
    If optCupSelect<0 Then optCupSelect=1
	If optCupSelect>1 Then optCupSelect=0	
	If optCupTeams=1
	 If optCupControl<0 Then optCupControl=5
	 If optCupControl>5 Then optCupControl=0
	Else
	 If optCupControl<0 Then optCupControl=4
	 If optCupControl>4 Then optCupControl=0    
    EndIf
    If game=1 Then optCupControl=3
	
 UpdateWorld
 Next
 RenderWorld 1

 ;DISPLAY
 DrawImage gBackground,rX#(400),rY#(300)
 If optCupFed=0 Then DrawImage gLogo(2),rX#(400),rY#(60)
 If optCupFed>0 Then DrawImage gFed(optCupFed),rX#(400),rY#(60)
 ;options
 y=150
 If optCupFed>0 Then namer$=fedName$(optCupFed) Else namer$="All Characters"
 DrawOption(1,rX#(400),rY#(y),"Talent Pool",namer$)
 DrawOption(2,rX#(400),rY#(y+35),"Format",textTeams$(optCupTeams))
 DrawOption(3,rX#(400),rY#(y+70),"Reward",textCupReward$(optCupReward)) 
 If optCupTeams>0 Then affix$="Teams" Else affix$="Characters"
 DrawOption(4,rX#(400),rY#(y+110),"Competition Size",optCupSize+" "+affix$)
 DrawOption(5,rX#(400),rY#(y+145),"Character Selection",textCupSelect$(optCupSelect))
 DrawOption(6,rX#(400),rY#(y+180),"Control Method",textCupControl$(optCupControl))
 DrawOption(7,rX#(400),rY#(y+220),"CREATE NEW","")
 DrawOption(8,rX#(400),rY#(y+255),"CONTINUE OLD","")
 If cupSize(cupSlot)=0 Then DrawImage gBlackout(1),rX#(400),rY#(y+255)
 DrawOption(9,rX#(400),rY#(560),"<<< BACK <<<","")  
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
If go=1
 If foc=7 And optCupSelect>0
  GenerateTournament(optCupFed,optCupTeams,optCupReward,optCupSize,optCupSelect,0,1)
  screen=15
 EndIf
 If foc=7 And optCupSelect=0 Then fed=optCupFed : screen=12
 If foc=8 Then screen=15
EndIf
If go=-1
 If game=1 Then screen=20 Else screen=1
EndIf
End Function

;//////////////////////////////////////////////////////////////////////////////
;-------------------------- 15. TOURNAMENT DISPLAY ----------------------------
;//////////////////////////////////////////////////////////////////////////////
Function Tournament()
;find next match
For b=1 To 31
 If cupBracket(cupSlot,b,1)>0 And cupBracket(cupSlot,b,2)>0 And cupResult(cupSlot,b)=0 Then cupFoc(cupSlot)=b
Next
foc=cupFoc(cupSlot)
If game=1 Then foc=0
oldfoc=foc
scroll=0 : scrollAccel=0 : scrollReveal=0
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
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
	If gotim>20 And scrollReveal=1 And keytim=0
	 ;leave
	 If KeyDown(1) Then go=-1
	 ;ACTIVATATIONS
	 If KeyDown(28) Or ButtonPressed() Or MouseDown(1)
	  ;play/simulate match
	  If foc=>1 And foc=<31
	   If cupBracket(cupSlot,foc,1)>0 And cupBracket(cupSlot,foc,2)>0 And cupResult(cupSlot,foc)=0
	    If cupCharControl(cupSlot,cupBracket(cupSlot,foc,1))>0 Or cupCharControl(cupSlot,cupBracket(cupSlot,foc,2))>0
	     go=1
	    Else
	     PlaySound sCrowd(Rnd(2,6)) : keytim=10
	     PlaySound sImpact(Rnd(1,7)) : PlaySound sPain(Rnd(1,8))
	     SimulateBracket(foc)
	     scrollAccel=-10 : scrollReveal=0
	    EndIf
	   EndIf
	  EndIf
	  ;refresh characters
	  If foc=32 And game=0
	   PlaySound sSwing : keytim=20
	   For char=1 To no_chars
	    healer=charStamina(char)
	    cupCharHealth(cupSlot,char)=cupCharHealth(cupSlot,char)+Rnd(healer/4,healer/2)
        If cupCharHealth(cupSlot,char)>100 Then cupCharHealth(cupSlot,char)=100
        For count=1 To 5
         If cupCharInjured(cupSlot,char,count)>0
          cupCharInjured(cupSlot,char,count)=cupCharInjured(cupSlot,char,count)/2
          If cupCharInjured(cupSlot,char,count)<100 Then cupCharInjured(cupSlot,char,count)=0  
          cupCharHealth(cupSlot,char)=0
         EndIf
        Next
	   Next
	  EndIf
	  ;leave
	  If foc=0 Then go=-1
	 EndIf
	EndIf
	;force players
	For b=1 To 32
	 If cupBracket(cupSlot,b,1)>0 And cupBracket(cupSlot,b,2)>0 And cupResult(cupSlot,b)=0
	  If cupControl(cupSlot)=3
	   cupCharControl(cupSlot,cupBracket(cupSlot,b,1))=3
	   cupCharControl(cupSlot,cupBracket(cupSlot,b,2))=0
	  EndIf
	  If cupControl(cupSlot)=4
	   cupCharControl(cupSlot,cupBracket(cupSlot,b,1))=1
	   cupCharControl(cupSlot,cupBracket(cupSlot,b,2))=2
	  EndIf
	 EndIf
	Next
	;music
	ManageMusic(-1) 
	
	;SCROLLING
	scrollAccel=scrollAccel+2
	If scrollAccel>100 Then scrollAccel=100
	If scrollAccel>0
	 ;introductory scrolling	 
	 If scrollReveal=0
	  scrollTarget=0
	  stage=CupStage(cupFoc(cupSlot))  
	  If BracketSide(cupFoc(cupSlot))=1 Then scrollTarget=cupScrollLimit(stage+1)
	  If BracketSide(cupFoc(cupSlot))=2 Then scrollTarget=-cupScrollLimit(stage+1) 
	  stage=CupStage(cupSize(cupSlot)/2) 
	  If scrollTarget<-cupScrollLimit(stage) Then scrollTarget=-cupScrollLimit(stage)  
	  If scrollTarget>cupScrollLimit(stage) Then scrollTarget=cupScrollLimit(stage)
	  If scroll<scrollTarget Then scroll=scroll+PercentOf#((scrollTarget-scroll)/10,scrollAccel)
	  If scroll>scrollTarget Then scroll=scroll-PercentOf#((scroll-scrollTarget)/10,scrollAccel)
	  If scroll=>scrollTarget-10 And scroll=<scrollTarget+10 Then scrollReveal=1
	 Else
	  ;manual scrolling
	  scrollSpeed=0  
	  If MouseX()>rX#(400)+100 Then scrollSpeed=(MouseX()-(rX#(400)+100))/10
	  If MouseX()<rX#(400)-100 Then scrollSpeed=(MouseX()-(rX#(400)-100))/10 
	  scroll=scroll-scrollSpeed
     EndIf
    EndIf
    ;display limits
    stage=CupStage(cupSize(cupSlot)/2) 
	If scroll<-cupScrollLimit(stage) Then scroll=-cupScrollLimit(stage)  
	If scroll>cupScrollLimit(stage) Then scroll=cupScrollLimit(stage)
  	
 UpdateWorld
 Next
 RenderWorld 1

 ;DISPLAY
 TileImage gBackground,rX#(400),rY#(300)
 DrawImage gLogo(1),rX#(400)+scroll,rY#(135)
 DrawImage gVersus,rX#(400)+scroll,rY#(300)
 ;rewards
 If cupReward(cupSlot)=1 Then DrawImage gBelt(1),rX#(400)+scroll,rY#(300)+50
 If cupReward(cupSlot)=2 Then DrawImage gBelt(2),rX#(400)+scroll,rY#(300)+50
 If cupReward(cupSlot)=3
  DrawImage gBelt(3),(rX#(400)+scroll)-3,rY#(300)+49
  DrawImage gBelt(3),(rX#(400)+scroll)+3,rY#(300)+51
 EndIf
 ;routes
 For b=1 To 32
  If b<cupSize(cupSlot) And cupTargetBracket(b)>0
   sourceX=(rX#(400)+cupX(b))+scroll
   sourceY=rY#(300)+cupY(b)
   targetX=(rX#(400)+cupX(cupTargetBracket(b)))+scroll
   If cupTargetBracket(b)=1 And cupTargetSlot(b)=1 Then targetX=targetX-150
   If cupTargetBracket(b)=1 And cupTargetSlot(b)=2 Then targetX=targetX+150
   targetY=rY#(300)+cupY(cupTargetBracket(b))
   Color 0,0,0
   DrawLine(sourceX,sourceY,targetX,sourceY,130,120,90)
   DrawLine(targetX,sourceY,targetX,targetY,130,120,90)
  EndIf
 Next
 ;brackets
 For b=1 To 32
  If b<cupSize(cupSlot)
   x=(rX#(400)+cupX(b))+scroll : y=rY#(300)+cupY(b)
   If cupBracket(cupSlot,b,1)>0 And cupBracket(cupSlot,b,2)>0 And cupResult(cupSlot,b)=0 And scrollReveal=1
    If b=1 And MouseX()=>x-240 And MouseX()=<x+240 And MouseY()=>y-16 And MouseY()=<y+13 Then foc=b
    If b=>2 And b=<15 And MouseX()=>x-91 And MouseX()=<x+91 And MouseY()=>y-35 And MouseY()=<y+35 Then foc=b
    If b=>16 And MouseX()=>x-91 And MouseX()=<x+91 And MouseY()=>y-32 And MouseY()=<y+32 Then foc=b
   EndIf
   If b=1 Then DrawBracket(b,1,x-150,y) : DrawBracket(b,2,x+150,y)
   If b=>2 And b=<15 Then DrawBracket(b,1,x,y-20) : DrawBracket(b,2,x,y+20)
   If b=>16 Then DrawBracket(b,1,x,y-17) : DrawBracket(b,2,x,y+17)
  EndIf
 Next
 ;options
 If scrollReveal=1
  If game=0 Then DrawOption(32,rX#(400)+scroll,rY#(515),"REFRESH CHARACTERS","")
  DrawOption(0,rX#(400)+scroll,rY#(560),"<<< SAVE & EXIT <<<","")  
 EndIf
 ;cursor
 If scrollReveal=1
  If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect  
  DrawImage gCursor,MouseX(),MouseY()
 EndIf

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
FreeTimer timer
If go=1 Or cupResult(cupSlot,1)>0 Then PlaySound sMenuGo Else PlaySound sMenuBack 
If go=-1
 If cupResult(cupSlot,1)>0 Then cupSize(cupSlot)=0
 screen=1
 If game=1 Then screen=20
EndIf
;construct match
If go=1
 fed=cupFed(cupSlot)
 If foc=>1 And foc=<31 Then cupFoc(cupSlot)=foc
 For cyc=1 To optPlayLim
  pChar(cyc)=0
  pControl(cyc)=0
 Next
 GetMatchRules(2)
 If cupTeams(cupSlot)=1 Then GetMatchRules(12)
 AddGimmick(0)
 If CupStage(cupFoc(cupSlot))=<2 Then matchTimeLim=0
 If CupStage(cupFoc(cupSlot))=3 Then matchTimeLim=10
 If CupStage(cupFoc(cupSlot))=4 Then matchTimeLim=10 : matchCountOuts=1
 If CupStage(cupFoc(cupSlot))=5 Or (CupStage(cupFoc(cupSlot))=4 And cupSize(cupSlot)=<16) Then matchTimeLim=5 : matchCountOuts=1
 If matchTimeLim>0 And optLength=2 Then matchTimeLim=matchTimeLim+5
 If matchTimeLim>0 And optLength=3 Then matchTimeLim=matchTimeLim+10
 If cupTeams(cupSlot)=1
  pChar(1)=cupBracket(cupSlot,cupFoc(cupSlot),1)
  pChar(2)=cupCharPartner(cupSlot,cupBracket(cupSlot,cupFoc(cupSlot),1))
  pChar(3)=cupBracket(cupSlot,cupFoc(cupSlot),2)
  pChar(4)=cupCharPartner(cupSlot,cupBracket(cupSlot,cupFoc(cupSlot),2))
  If cupControl(cupSlot)=5
   pControl(1)=1
   pControl(2)=2
  Else
   pControl(1)=cupCharControl(cupSlot,pChar(1))
   pControl(3)=cupCharControl(cupSlot,pChar(3))
  EndIf
 Else
  For cyc=1 To 2
   pChar(cyc)=cupBracket(cupSlot,cupFoc(cupSlot),cyc)
   pControl(cyc)=cupCharControl(cupSlot,pChar(cyc))
  Next
 EndIf
 If no_refs>0 Then pChar(no_plays)=AssignReferee() 
 randy=Rnd(0,2)
 If randy=0 And no_refs>0 And cupFoc(cupSlot)>1 Then matchPromo=92
 If cupFoc(cupSlot)=1 Then matchPromo=93
 If game=0 Then GetRealDate() : GenerateArena(fed,3,1)
 screen=50 : screenAgenda=11
EndIf
End Function

;////////////////////////////////////////////////////////////////
;---------------------- RELATED FUNCTIONS -----------------------
;////////////////////////////////////////////////////////////////

;GENERATE TOURNAMENT
Function GenerateTournament(promotion,teams,reward,size,selection,date,span)
 ;get cast
 cupSpan(cupSlot)=span
 cupFed(cupSlot)=promotion
 cupTeams(cupSlot)=teams
 cupReward(cupSlot)=reward
 cupSize(cupSlot)=size
 If selection>0
  For cyc=1 To 32
   cupChar(cupSlot,cyc)=0
  Next
  For cyc=1 To cupSize(cupSlot)
   its=0
   Repeat
    conflict=0 : its=its+1
    If promotion=0 Then newbie=Rnd(1,no_chars)
    If promotion>0 Then newbie=fedRoster(promotion,Rnd(1,fedSize(promotion)))
    For v=1 To 32
     If newbie=cupChar(cupSlot,v) Then conflict=1
    Next
    If promotion>0 And charFed(newbie)<>promotion Then conflict=1
    If charFed(newbie)=>8 Then conflict=1
    If charVacant(newbie)>0 And its<500 Then conflict=1
    If charRole(newbie)<>1 And its<500 Then conflict=1
   Until conflict=0 Or its>1000
   cupChar(cupSlot,cyc)=newbie
  Next
 EndIf
 ;reset character status'
 For char=1 To no_chars
  If selection>0 Then cupCharPartner(cupSlot,char)=0
  cupCharHealth(cupSlot,char)=100
  For count=0 To 5
   cupCharInjured(cupSlot,char,count)=0
  Next
  If selection>0 Then cupCharControl(cupSlot,char)=0
 Next
 If selection>0 And game=0
  If optCupControl=1 Or optCupControl=5 Then cupCharControl(cupSlot,cupChar(cupSlot,Rnd(1,cupSize(cupSlot))))=3
  If optCupControl=2
   cupCharControl(cupSlot,cupChar(cupSlot,Rnd(1,cupSize(cupSlot))))=1
   Repeat 
    newbie=cupChar(cupSlot,Rnd(1,cupSize(cupSlot)))
   Until cupCharControl(cupSlot,newbie)=0
   cupCharControl(cupSlot,newbie)=2
  EndIf
 EndIf
 ;assign partners
 If teams>0 And selection>0
  For cyc=1 To cupSize(cupSlot)
   char=cupChar(cupSlot,cyc)
   cupCharPartner(cupSlot,char)=AssignPartner(char,1)
  Next
 EndIf
 ;reset brackets
 cupFoc(cupSlot)=0
 For b=1 To 32
  cupBracket(cupSlot,b,1)=0
  cupBracket(cupSlot,b,2)=0
  cupResult(cupSlot,b)=0
 Next
 For b=1 To 32
  If b=>cupSize(cupSlot)/2 And b<cupSize(cupSlot)
   For cyc=1 To 2
    Repeat
     conflict=0
     newbie=cupChar(cupSlot,Rnd(1,cupSize(cupSlot)))
     For v=1 To 32
      If newbie=cupBracket(cupSlot,v,1) Or newbie=cupBracket(cupSlot,v,2) Then conflict=1
     Next
    Until conflict=0
    cupBracket(cupSlot,b,cyc)=newbie
   Next
  EndIf
 Next
 ;change schedule
 If game=1
  range=CupStage(cupSize(cupSlot)/2)-1
  If span=1 Then range=0
  If date+range=<48
   For count=date To date+range
    gamSchedule(count)=3
    gamOpponent(count)=0
   Next
   If gamSchedule((date+range)+1)=<2 Then gamSchedule((date+range)+1)=0
  Else
   cupSize(cupSlot)=0
  EndIf
 EndIf
 ;confirm control method
 cupControl(cupSlot)=optCupControl
End Function

;DRAW TOURNAMENT BOX
Function DrawBracket(bracket,cyc,x,y)
 char=cupBracket(cupSlot,bracket,cyc)
 If char>0
  If foc=bracket Then DrawImage gMenu(5),x,y+1 Else DrawImage gMenu(6),x,y+1
  DrawImage gPortrait,x,y+1
  DrawImageRect charPhoto(char),x-48,(y+64)-11,28,(64-PortraitHead(char))-10,21,21
  If cupResult(cupSlot,bracket)=0
   Color 0,0,0 : Rect x-65,y+6,102,5,1
   Color 200,0,0 : Rect x-64,y+7,100,3,1
   Color 130,0,0 : Rect x-64,y+7,100,3,0
   If game=0 Then meter=cupCharHealth(cupSlot,char) Else meter=charHealth(char)
   Color 0,200,0 : Rect x-64,y+7,meter,3,1
   Color 0,130,0 : Rect x-64,y+7,meter,3,0
  EndIf
  If cupResult(cupSlot,bracket)=0
   If cupCharControl(cupSlot,char)=>1 And cupCharControl(cupSlot,char)=<2 Then DrawImage gControl(cupCharControl(cupSlot,char)),x-67,y+1
   If cupCharControl(cupSlot,char)=3 Then DrawImage gControl(2),x-67,y+1
  EndIf
  If bracket=1 And cupResult(cupSlot,bracket)=cyc
   DrawImage gCrown,x,y-15
   SetFont font(3)
   Outline("WINNER!",x,y+14,0,0,0,255,Rnd(175,225),100)
  EndIf
  If cupTeams(cupSlot)>0 Then namer$=charTeamName$(char) Else namer$=charName$(char)
  SqueezeFont(namer$,140,18)
  r=200 : g=200 : b=200
  If foc=bracket Then r=255 : g=255 : b=255
  Outline(namer$,x+11,y,0,0,0,r,g,b)
  If cupResult(cupSlot,bracket)>0 And cupResult(cupSlot,bracket)<>cyc Then DrawImage gBlackout(2),x,y+1
 Else
  If foc=bracket Then shower=5 Else shower=6
  DrawImage gMenu(shower),x,y+1
 EndIf
End Function

;FIND CUP STAGE
Function CupStage(bracket)
 value=0
 If bracket=1 Then value=1 ;final 
 If bracket=>2 And bracket=<3 Then value=2 ;semi-finals
 If bracket=>4 And bracket=<7 Then value=3 ;quarter-finals
 If bracket=>8 And bracket=<15 Then value=4 ;2nd round
 If bracket=>16 Then value=5 ;1st round
 Return value
End Function

;FIND BRACKET SIDE
Function BracketSide(b) ;0=centre, 1=left, 2=right
 value=0
 If b>1
  If b=2 Or b=4 Or b=6 Or b=8 Or b=10 Or b=12 Or b=14 Or b=16 Or b=18 Or b=20 Or b=22 Or b=24 Or b=26 Or b=28 Or b=30 Or b=32
   value=1
  Else
   value=2
  EndIf
 EndIf
 Return value
End Function

;HAS CHARACTER BEEN SELECTED FOR A TOURNAMENT?
Function TournamentSelected(char)
 value=0
 If cupSize(cupSlot)>0
  For b=1 To 32
   If cupChar(cupSlot,b)=char Or cupCharPartner(cupSlot,cupChar(cupSlot,b))=char Then value=b
  Next
 EndIf
 Return value
End Function

;IS CHARACTER IN AN ACTIVE TOURNAMENT?
Function TournamentStatus(char)
 value=0
 If cupSize(cupSlot)>0
  For b=1 To 32
   If cupBracket(cupSlot,b,1)=char Or cupBracket(cupSlot,b,2)=char Or cupCharPartner(cupSlot,cupBracket(cupSlot,b,1))=char Or cupCharPartner(cupSlot,cupBracket(cupSlot,b,2))=char
    If cupResult(cupSlot,b)=0 Then value=b
   EndIf
  Next
 EndIf
 Return value
End Function

;SIMULATE TOURNAMENT MATCH
Function SimulateBracket(b) 
 ;find winner
 cupResult(cupSlot,b)=Rnd(-2,2)
 If cupResult(cupSlot,b)=<0
  fitness1#=PercentOf#(AverageStats(cupBracket(cupSlot,b,1)),charHealth(cupBracket(cupSlot,b,1)))
  fitness2#=PercentOf#(AverageStats(cupBracket(cupSlot,b,2)),charHealth(cupBracket(cupSlot,b,2)))
  If fitness1#=>fitness2# Then cupResult(cupSlot,b)=1 Else cupResult(cupSlot,b)=2
 EndIf 
 ;affect health
 For count=1 To 2
  char=cupBracket(cupSlot,b,count)
  partner=cupCharPartner(cupSlot,char)
  If cupResult(cupSlot,b)=count 
   cupCharHealth(cupSlot,char)=Rnd(cupCharHealth(cupSlot,char)/2,cupCharHealth(cupSlot,char))
   cupCharHealth(cupSlot,partner)=Rnd(cupCharHealth(cupSlot,partner)/2,cupCharHealth(cupSlot,partner))
   If game=1 
    charPopularity(char)=charPopularity(char)+1 : charHappiness(char)=charHappiness(char)+1
    If partner>0 Then charPopularity(partner)=charPopularity(partner)+1 : charHappiness(partner)=charHappiness(partner)+1
   EndIf
  EndIf 
  If cupResult(cupSlot,b)<>count
   cupCharHealth(cupSlot,char)=0
   cupCharHealth(cupSlot,partner)=0 
   If game=1 
    charPopularity(char)=charPopularity(char)-1 : charHappiness(char)=charHappiness(char)-1
    If partner>0 Then charPopularity(partner)=charPopularity(partner)-1 : charHappiness(partner)=charHappiness(partner)-1
   EndIf
  EndIf
 Next
 ;update brackets
 If b=1 
  char=cupBracket(cupSlot,b,cupResult(cupSlot,b))
  If cupReward(cupSlot)=0 Then fedCupHolder(charFed(char))=char : charTitles(char,charFed(char),4)=charTitles(char,charFed(char),4)+1 : WriteHistory(charFed(char),4)
  If cupReward(cupSlot)=1 Then fedChampWorld(charFed(char))=char : charTitles(char,charFed(char),1)=charTitles(char,charFed(char),1)+1 : WriteHistory(charFed(char),1)
  If cupReward(cupSlot)=2 Then fedChampInter(charFed(char))=char : charTitles(char,charFed(char),2)=charTitles(char,charFed(char),2)+1 : WriteHistory(charFed(char),2)
  If cupReward(cupSlot)=3
   fedChampTag(charFed(char),1)=char : fedChampTag(charFed(char),2)=cupCharPartner(cupSlot,char)
   charTitles(char,charFed(char),3)=charTitles(char,charFed(char),3)+1 : charTitles(fedChampTag(charFed(char),2),charFed(char),4)=charTitles(fedChampTag(charFed(char),2),charFed(char),4)+1
   WriteHistory(charFed(char),3)
  EndIf
 Else
  cupBracket(cupSlot,cupTargetBracket(b),cupTargetSlot(b))=cupBracket(cupSlot,b,cupResult(cupSlot,b)) 
  If cupBracket(cupSlot,cupTargetBracket(b),1)>0 And cupBracket(cupSlot,cupTargetBracket(b),2)>0 And cupResult(cupSlot,cupTargetBracket(b))=0
   foc=cupTargetBracket(b) : cupFoc(cupSlot)=foc
  Else 
   foc=0
   For v=1 To 31
    If cupBracket(cupSlot,v,1)>0 And cupBracket(cupSlot,v,2)>0 And cupResult(cupSlot,v)=0 Then foc=v : cupFoc(cupSlot)=foc
   Next
  EndIf
 EndIf
End Function