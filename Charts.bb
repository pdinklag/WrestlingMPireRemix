;//////////////////////////////////////////////////////////////////////////////
;-------------------------- WRESTLING MPIRE 2008: CHARTS ----------------------
;//////////////////////////////////////////////////////////////////////////////

;//////////////////////////////////////////////////////////////////////////////
;---------------------------- 29. NIGHT SUMMARY -------------------------------
;//////////////////////////////////////////////////////////////////////////////
Function NightSummary()
;calculate night averages
divider=gamSegments(gamDate)
If gamSegments(gamDate)=<5 Then divider=gamSegments(gamDate)+1
;If gamSegments(gamDate)=>8 Then divider=gamSegments(gamDate)-1
entScore=0 : entHardcore=0
For count=1 To gamSegments(gamDate)
 entScore=entScore+gamMatchScore(gamDate,count)
 entHardcore=entHardcore+gamMatchHardcore(gamDate,count)
Next
gamNightScore(gamDate)=entScore/divider
gamNightHardcore(gamDate)=entHardcore/divider
;affect popularity
fed=charFed(gamChar)
fedOldPopularity(fed)=fedPopularity(fed)
fedNewPopularity(fed)=fedPopularity(fed)
If GetNightRating(gamNightScore(gamDate))=<1 Then target=50
If GetNightRating(gamNightScore(gamDate))=2 Then target=65
If GetNightRating(gamNightScore(gamDate))=3 Then target=75
If GetNightRating(gamNightScore(gamDate))=4 Then target=85
If GetNightRating(gamNightScore(gamDate))=5 Then target=99
progress=0
If fedPopularity(fed)<target
 progress=progress+1
 If fedPopularity(fed)=<target-10 Then progress=progress+1
 If fedPopularity(fed)=<target-20 Then progress=progress+1
 If fedPopularity(fed)=<target-30 Then progress=progress+1
 If fedPopularity(fed)=<target-40 Then progress=progress+1
EndIf
If fedPopularity(fed)>target
 progress=progress-1
 If fedPopularity(fed)=>target+10 Then progress=progress-Rnd(0,1)
 If fedPopularity(fed)=>target+20 Then progress=progress-Rnd(0,1)
 If fedPopularity(fed)=>target+30 Then progress=progress-Rnd(0,1)
 If fedPopularity(fed)=>target+40 Then progress=progress-Rnd(0,1)
EndIf
If fedPopularity(fed)=>90 And progress>1 Then progress=1
fedNewPopularity(fed)=fedNewPopularity(fed)+progress
;affect reputation
fedOldReputation(fed)=fedReputation(fed)
fedNewReputation(fed)=fedReputation(fed)
If GetNightContent(gamNightHardcore(gamDate))=<1 Then target=99
If GetNightContent(gamNightHardcore(gamDate))=2 Then target=80
If GetNightContent(gamNightHardcore(gamDate))=3 Then target=70
If GetNightContent(gamNightHardcore(gamDate))=4 Then target=60
If GetNightContent(gamNightHardcore(gamDate))=5 Then target=50
If gamSegments(gamDate)=0 Then target=30
progress=0
If fedReputation(fed)<target 
 progress=progress+1
 If fedReputation(fed)=<target-10 Then progress=progress+Rnd(0,1)
 If fedReputation(fed)=<target-20 Then progress=progress+Rnd(0,1)
 If fedReputation(fed)=<target-30 Then progress=progress+Rnd(0,1)
 If fedReputation(fed)=<target-40 Then progress=progress+Rnd(0,1)
EndIf
If fedReputation(fed)>target 
 progress=progress-1
 If fedReputation(fed)=>target-10 Then progress=progress-Rnd(0,1)
 If fedReputation(fed)=>target-20 Then progress=progress-Rnd(0,1)
 If fedReputation(fed)=>target-30 Then progress=progress-Rnd(0,1)
 If fedReputation(fed)=>target-40 Then progress=progress-Rnd(0,1)
EndIf 
If gamSegments(gamDate)<6 Then progress=progress-1
If fedReputation(fed)=>90 And progress>0 Then progress=0
fedNewReputation(fed)=fedNewReputation(fed)+progress
;PPV bonuses
If gamSchedule(gamDate)=>2 
 If fedNewPopularity(fed)>fedOldPopularity(fed) Then fedNewPopularity(fed)=fedNewPopularity(fed)+1
 If fedNewPopularity(fed)<fedOldPopularity(fed) Then fedNewPopularity(fed)=fedNewPopularity(fed)-1
 If fedNewReputation(fed)>fedOldReputation(fed) Then fedNewReputation(fed)=fedNewReputation(fed)+1
 If fedNewReputation(fed)<fedOldReputation(fed) Then fedNewReputation(fed)=fedNewReputation(fed)-1 
EndIf
;charity kudos
If gamSchedule(gamDate)=>5 And gamSchedule(gamDate)=<6
 If fedNewReputation(fed)<fedOldReputation(fed) Then fedNewReputation(fed)=fedOldReputation(fed)
 fedNewReputation(fed)=fedNewReputation(fed)+1 
EndIf
;glass ceiling
If fedNewPopularity(fed)>fedOldPopularity(fed) And fedNewPopularity(fed)>PromotionPotential(fed)
 If fedOldPopularity(fed)<PromotionPotential(fed) Then fedNewPopularity(fed)=PromotionPotential(fed)
 If fedOldPopularity(fed)=>PromotionPotential(fed) Then fedNewPopularity(fed)=fedOldPopularity(fed) 
EndIf
;check limits
CheckFedLimits(fed)
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
foc=0 : cyc=0
go=0 : gotim=-30 : keytim=10
While go=0

 Cls
 frames=WaitTimer(timer)
 For framer=1 To frames
	
	;timers
	keytim=keytim-1
	If keytim<1 Then keytim=0
	
	;PROCESS
    gotim=gotim+1
	If gotim>20 And keytim=0
	 ;trigger stats
	 If gotim=((gamSegments(gamDate)*35)-20)+70 Then PlaySound sMenuBrowse : foc=1
	 ;alter stats
	 AlterFedStats(fed)
	 ;proceed
	 If KeyDown(1) Or KeyDown(28) Or ButtonPressed() Or MouseDown(1) 
	  If foc<10 And keytim>1 Then keytim=1
	  If foc=10 And keytim=0 Then go=1
	 EndIf
	EndIf
	
	;REACTIONS
	If gotim=-10 Then PlaySound sPaper
	;matches
	If gamSegments(gamDate)>0
	 For count=1 To gamSegments(gamDate)
      If gotim=(count*35)-20
       If GetMatchRating(gamMatchScore(gamDate,count))=1 Then ProduceSound(0,sCrowd(3),0,0.5)
       If GetMatchRating(gamMatchScore(gamDate,count))=2 Then ProduceSound(0,sCrowd(5),0,0.5)
       If GetMatchRating(gamMatchScore(gamDate,count))=3 Then ProduceSound(0,sCrowd(4),0,0.5)
       If GetMatchRating(gamMatchScore(gamDate,count))=4 Then ProduceSound(0,sCrowd(2),0,0.5)
       If GetMatchRating(gamMatchScore(gamDate,count))=5 Then ProduceSound(0,sCrowd(6),0,0.5) : ProduceSound(0,sCrowd(9),0,0.5)
      EndIf
     Next
    EndIf
    ;night
    If gotim=((gamSegments(gamDate)*35)-20)+50
     If GetNightRating(gamNightScore(gamDate))=1 Then ProduceSound(0,sCrowd(3),0,0.5)
     If GetNightRating(gamNightScore(gamDate))=2 Then ProduceSound(0,sCrowd(5),0,0.5)
     If GetNightRating(gamNightScore(gamDate))=3 Then ProduceSound(0,sCrowd(4),0,0.5)
     If GetNightRating(gamNightScore(gamDate))=4 Then ProduceSound(0,sCrowd(2),0,0.5)
     If GetNightRating(gamNightScore(gamDate))=5 Then ProduceSound(0,sCrowd(6),0,0.5) : ProduceSound(0,sCrowd(9),0,0.5)
    EndIf
	
 UpdateWorld
 Next 
 RenderWorld 1

 ;DISPLAY
 DrawImage gBackground,rX#(400),rY#(300)
 DrawImage gFed(fed),rX#(400),rY#(60) 
 ;show profile
 HighlightFedStats(fed)
 DrawFedProfile(fed,-1,-1)
 ;magazine mock-up
 If gotim>-10
  PreviewMatchRatings(gamDate,rX#(400),rY#(390),1)
 EndIf
 ;cursor
 DrawImage gCursor,MouseX(),MouseY()

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
If go=-1 Then PlaySound sMenuBack Else PlaySound sMenuGo
FreeTimer timer
screen=24
End Function

;//////////////////////////////////////////////////////////////////////////////
;--------------------------- 24. FINANCIAL REPORT -----------------------------
;//////////////////////////////////////////////////////////////////////////////
Function FinanceReport()
;reset working figures
fed=charFed(gamChar)
For count=1 To 7
 gamFinances(count,0)=0
 gamFinances(count,1)=0
Next
;calculate income
If gamSchedule(gamDate)=>2 Then gamTicket=15 Else gamTicket=20
If gamSchedule(gamDate)=<0 Then gamTicket=0 : gamAttendance(gamDate)=0
gamFinances(1,1)=gamAttendance(gamDate)*gamTicket
If gamAttendance(gamDate)>0
 gamFinances(2,1)=Rnd(gamAttendance(gamDate)/8,gamAttendance(gamDate)/4)
 gamFinances(2,1)=gamFinances(2,1)*10
EndIf
;refund!
If gamSegments(gamDate)=0 Then gamFinances(1,1)=0 : gamFinances(2,1)=0
;calculate expenses
gamFinances(3,1)=CountSalaries(fed,1)
gamFinances(4,1)=ProductionCosts(fed,1)
If gamSchedule(gamDate)>0
 If gamVenue(gamDate)=>11 Then gamFinances(5,1)=50000 Else gamFinances(5,1)=5000
 If gamSchedule(gamDate)=5 Or gamSchedule(gamDate)=6 Then gamFinances(5,1)=0
EndIf
;calculate profit
gamFinances(6,1)=(gamFinances(1,1)+gamFinances(2,1))-(gamFinances(3,1)+gamFinances(4,1)+gamFinances(5,1))
If gamSchedule(gamDate)=5 Or gamSchedule(gamDate)=6 ;negate for charity
 gamFinances(3,1)=0 : gamFinances(4,1)=0
 gamFinances(5,1)=0 : gamFinances(6,1)=0
EndIf
oldBank=fedBank(fed)
gamFinances(7,0)=fedBank(fed)
gamFinances(7,1)=fedBank(fed)+gamFinances(6,1)
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
foc=0
go=0 : gotim=-20 : keytim=10
While go=0

 Cls
 frames=WaitTimer(timer)
 For framer=1 To frames
	
	;timers
	keytim=keytim-1
	If keytim<1 Then keytim=0
	
	;PROCESS
    gotim=gotim+1
	;initial subject
	If gotim>20 And foc=0 Then PlaySound sMenuSelect : foc=1
	If gotim>30 And keytim=0
	 ;develop figures
	 For cyc=1 To 7
	  If foc=cyc And keytim=0
	   If gamFinances(cyc,0)<>gamFinances(cyc,1) 
	    ProduceSound(0,sMenuBrowse,0,0.25) : keytim=1
	    gamFinances(cyc,0)=gamFinances(cyc,0)+PursueFigure(gamFinances(cyc,0),gamFinances(cyc,1))
	   EndIf
	   If gamFinances(cyc,0)=gamFinances(cyc,1) 
	    foc=foc+1 : keytim=10
	    If gamFinances(cyc,1)=0 Then PlaySound sMenuSelect Else PlaySound sCash
	    If foc>7 Then fedBank(fed)=gamFinances(7,1)
	   EndIf
	  EndIf
	 Next
	 ;leave
	 If foc>7 And keytim=0
	  If KeyDown(1) Or KeyDown(28) Or ButtonPressed() Or MouseDown(1) Then go=1
	 EndIf
	EndIf
	;speed up
	If foc=>1 And foc=<7 And keytim>0
	 If KeyDown(1) Or KeyDown(28) Or ButtonPressed() Or MouseDown(1) Then keytim=keytim/2
	EndIf
	;paper thud
	If gotim=0 Then PlaySound sPaper 
	;music
	ManageMusic(-1) 
	
 UpdateWorld
 Next 
 RenderWorld 1

 ;DISPLAY
 DrawImage gBackground,rX#(400),rY#(300)
 DrawImage gFed(fed),rX#(400),rY#(60)
 ;stat reminder
 If gotim>10 Then DrawFedProfile(fed,-1,-1)
 ;REPORT
 If gotim>0
  x=rX#(400) : y=rY#(390)
  DrawImage gReport,x,y
  ;dateline
  SetFont fontNews(1)
  Outline(DescribeDate$(gamDate,gamYear),x+105,y-153,225,220,215,100,100,100)
  ;items
  For count=-2 To 7
   ;positioning
   showX=x+15
   If count=-2 Then showY=y-105
   If count=-1 Then showY=y-85
   If count=0 Then showY=y-65
   If count=1 Then showY=y-40
   If count=2 Then showY=y-20
   If count=3 Then showY=y+20
   If count=4 Then showY=y+40
   If count=5 Then showY=y+60
   If count=6 Then showY=y+100
   If count=7 Then showY=y+120
   ;headers
   If count=-2 
    header$="PROMOTION:"
    If fed>0 Then sum$=fedName$(fed) Else sum$="Various"
   EndIf
   If count=-1 
    header$="EVENT:" : sum$="None"
    If gamSchedule(gamDate)>0 Then sum$=textEvent$(gamSchedule(gamDate))
    If fed=7 And gamSchedule(gamDate)=1 Then sum$="Training Session"
    If fed=7 And gamSchedule(gamDate)=2 Then sum$="Student Showcase"
   EndIf
   If count=0
    header$="ATTENDANCE:" : sum$="N/A"
    If gamSchedule(gamDate)>0 Then sum$=GetFigure$(gamAttendance(gamDate))+" x $"+gamTicket
   EndIf
   If count>0
    sum$="$"+GetFigure$(gamFinances(count,0))
    If count=1 Then header$="REVENUE:"
    If count=2 Then header$="MERCHANDISE:"
    If count=3 Then header$="WAGES:"
    If count=4 Then header$="PRODUCTION:"
    If count=5 Then header$="VENUE HIRE:"
    If count=6 Then header$="PROFIT:"
    If count=7 Then header$="BANK BALANCE:"
   EndIf
   ;highlighter
   SetFont fontNews(2)
   If foc>0 And foc=count
    Color 150,150,150
    Rect (showX-5)-StringWidth(header$),showY-7,(StringWidth(header$)+5)+(StringWidth(sum$)+5),16,0
    Color 0,0,0
    Rect (showX-7)-StringWidth(header$),showY-9,(StringWidth(header$)+5)+(StringWidth(sum$)+9),20,0
   EndIf
   Color 175,175,175
   Line showX-110,y,showX+90,y
   Line showX-110,y+80,showX+90,y+80
   ;display text
   OutlineStraight(header$,(showX-3)-StringWidth(header$),showY,225,220,215,50,50,50)
   r=50 : g=50 : b=50
   If count>0
    If gamFinances(count,0)>0 Then r=0 : g=150 : b=0
    If gamFinances(count,0)<0 Or (count=>3 And count=<5 And gamFinances(count,0)>0) Then r=150 : g=0 : b=0
    If count=7
     r=50 : g=50 : b=50
     If gamFinances(count,0)>oldBank Then r=0 : g=150 : b=0
     If gamFinances(count,0)<oldBank Then r=150 : g=0 : b=0
    EndIf
   EndIf
   OutlineStraight(sum$,showX+3,showY,225,220,215,r,g,b)
   ;explanations
   If foc>count
    explain$=""
    If gamSchedule(gamDate)>0 And gamSegments(gamDate)=0 And count=>1 And count=<2 Then explain$="(Refunded)"
    If gamSchedule(gamDate)=>5 And gamSchedule(gamDate)=<6 
     If count=3 Or count=6 Then explain$="(Donated)"
     If count=4 Or count=5 Then explain$="(Waived)" 
    EndIf
    stringer=StringWidth(sum$)
    SetFont fontNews(0) 
    OutlineStraight(explain$,(showX+3)+(stringer+5),showY,225,220,215,100,100,100)
   EndIf
  Next
  ;prompt
  If foc>7 And gotim>50
   SetFont font(2)
   Outline(">>> PRESS ANY COMMAND TO PROCEED >>>",x+10,y+190,120,120,120,250,250,250)
  EndIf
 EndIf
 ;cursor
 DrawImage gCursor,MouseX(),MouseY()

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
If go=-1 Then PlaySound sMenuBack Else PlaySound sMenuGo
FreeTimer timer
screen=22
End Function

;----------------------------------------------------------------------
;///////////////////////// 30. TV RATINGS /////////////////////////////
;----------------------------------------------------------------------
Function TelevisionRatings()
;store old values
For count=1 To 20
 If count=<6 Then showOldPopularity(count)=fedOldPopularity(count)
 If count=>7 Then showOldPopularity(count)=showPopularity(count)
 showOldRanked(count)=showRanked(count)
 showOldList(count)=showList(count)
Next
;update ratings
For count=1 To 20
 If count=<6
  showName$(count)=fedName$(count)
  showGenre$(count)="Wrestling"
  showPopularity(count)=fedPopularity(count)
 Else
  showPopularity(count)=showPopularity(count)+PursueValue(showPopularity(count),statLevel(Rnd(3,6)),1)
  randy=Rnd(0,10)
  If randy=0 Then showPopularity(count)=showPopularity(count)+PursueValue(showPopularity(count),statLevel(Rnd(3,6)),0) 
  If showPopularity(count)>95 Then showPopularity(count)=95
 EndIf
 If showPopularity(count)<60 Then showPopularity(count)=60
 If showPopularity(count)>99 Then showPopularity(count)=99
 showRanked(count)=0
Next
;re-arrange rankings
For count=1 To 20
 hi=0 : leader=0
 For cyc=1 To 20
  If showPopularity(cyc)>hi And showRanked(cyc)=0 Then hi=showPopularity(cyc) : leader=cyc 
 Next
 showList(count)=leader : showRanked(leader)=count
Next
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
go=0 : gotim=-10 : keytim=10
While go=0

 Cls
 frames=WaitTimer(timer)
 For framer=1 To frames
	
	;timers
	keytim=keytim-1
	If keytim<1 Then keytim=0
	showTim=showTim-1
	If showTim<1 Then showTim=0
	
	;PORTAL
    gotim=gotim+1
	If gotim>20 And keytim=0 
	 If KeyDown(1) Or KeyDown(28) Or ButtonPressed() Or MouseDown(1) 
	  If gotim<showTime(1) Then PlaySound sMenuSelect : gotim=gotim+20 : keytim=10
	  If gotim>showTime(1)+20 And keytim=0 Then go=1
	 EndIf
	EndIf
	;reactions
	If gotim=5 Then ProduceSound(0,sCrowd(7),0,0.25)
	For count=1 To 10
	 If gotim=showTime(count)
	  PlaySound sMenuBrowse
	  If showList(count)=charFed(gamChar) And count=>4 
	   If showRanked(charFed(gamChar))=<showOldRanked(charFed(gamChar)) Then ProduceSound(0,sCrowd(4),0,0.5)
	   If showRanked(charFed(gamChar))>showOldRanked(charFed(gamChar)) Then ProduceSound(0,sCrowd(5),0,0.5)
	  EndIf
	  If showRanked(showList(count))<showOldRanked(showList(count)) And showOldRanked(showList(count))>10 Then ProduceSound(0,sCrowd(4),0,0.5)
	  If count=3 Then ProduceSound(0,sCrowd(4),0,0.5)
	  If count=2 Then ProduceSound(0,sCrowd(2),0,0.5)
	  If count=1 Then ProduceSound(0,sCrowd(6),0,0.5) : ProduceSound(0,sCrowd(9),0,0.5)
	 EndIf
	Next
	;music
	ManageMusic(-1) 
	
 UpdateWorld
 Next
 RenderWorld 1

 ;DISPLAY
 DrawImage gBackground,rX#(400),rY#(300)
 DrawImage gLogo(2),rX#(400),rY#(60)
 ;chart
 x=165 : y=175
 For rank=1 To 10
  show=showList(rank)
  If gotim>showTime(rank)
   ;progress icons
   If showRanked(show)<showOldRanked(show) And showOldRanked(show)>10 Then DrawImage gChart(0),rX#(x),rY#(y)
   If showRanked(show)>showOldRanked(show) And showOldRanked(show)=<10 Then DrawImage gChart(1),rX#(x),rY#(y)
   If showRanked(show)=showOldRanked(show) Then DrawImage gChart(2),rX#(x),rY#(y)
   If showRanked(show)<showOldRanked(show) And showOldRanked(show)=<10 Then DrawImage gChart(3),rX#(x),rY#(y)
   ;position 
   SetFont fontNews(4)
   If rank=1 Then SetFont fontNews(8) 
   If rank=10 Then SetFont fontNews(3) 
   r=255 : g=230 : b=110
   If show=charFed(gamChar) Then r=255 : g=Rnd(150,250) : b=130
   Outline(rank+".",rX#(x+35),rY#(y),0,0,0,r,g,b)
   ;name
   r=255 : g=255 : b=255
   If show=charFed(gamChar) Then r=255 : g=Rnd(150,250) : b=130
   SqueezeFont(showName$(show),200,18)
   OutlineStraight(showName$(show),rX#(x+55),rY#(y)+1,0,0,0,r,g,b)
   ;additional info 
   SetFont font(1)
   r=175 : g=175 : b=175
   If show=charFed(gamChar) Then r=255 : g=Rnd(150,250) : b=130
   Outline(showGenre$(show),rX#(x+335),rY#(y)+1,0,0,0,r,g,b)
   ;score
   SetFont fontNews(4)
   r=100 : g=200 : b=100 
   If show=charFed(gamChar) Then r=255 : g=Rnd(150,250) : b=130
   Outline(showPopularity(show)+"%",rX#(x+440),rY#(y),0,0,0,r,g,b)
  EndIf
  y=y+37
 Next 
 ;header
 SetFont fontNews(4)
 Outline("TV Ratings:",rX#(400),rY#(132),50,35,20,250,230,110)
 ;prompt
 If gotim>showTime(1)
  SetFont font(2)
  Outline(">>> PRESS ANY COMMAND TO PROCEED >>>",rX#(400),rY#(565),0,0,0,85,85,85)
 EndIf
 ;cursor
 DrawImage gCursor,MouseX(),MouseY()

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
PlaySound sMenuGo
FreeTimer timer
fed=1 : screen=23
End Function

;----------------------------------------------------------------------
;///////////////////// 25. RETIREMENT SUMMARY /////////////////////////
;----------------------------------------------------------------------
Function Retirement()
;get working values
fed=charFed(gamChar)
hiWealth(0)=fedBank(fed)
hiPopularity(0)=fedPopularity(fed)
hiReputation(0)=fedReputation(fed)
hiExperience(0)=CountExperience(gamChar,0)
;newpaper identity
eNewspaper(no_events)=Rnd(1,1)
eBackground(no_events)=Rnd(1,12)
eAdvert(no_events,1)=Rnd(1,9)
Repeat
 eAdvert(no_events,2)=Rnd(1,9)
Until eAdvert(no_events,2)<>eAdvert(no_events,1)
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
go=0 : gotim=-10 : keytim=0
While go=0
 
 Cls
 frames=WaitTimer(timer)
 For framer=1 To frames
	
	;PORTAL
    gotim=gotim+1
	If gotim>100 
	 If KeyDown(1) Or KeyDown(28) Or ButtonPressed() Or MouseDown(1) Then go=1
	EndIf 
	;reaction
	If gotim=0 
	 PlaySound sPaper
	 If hiWealth(0)<10000 Then PlaySound sCrowd(8)
	 If hiWealth(0)=>10000 And hiWealth(0)<100000 Then PlaySound sCrowd(7)
	 If hiWealth(0)=>100000 And hiWealth(0)<500000 Then PlaySound sCrowd(4)
	 If hiWealth(0)=>500000 And hiWealth(0)<1000000 Then PlaySound sCrowd(2)
	 If hiWealth(0)=>1000000 Then PlaySound sCrowd(6) : PlaySound sCrowd(9)
	EndIf
	;music
	ManageMusic(-1) 
	
 UpdateWorld
 Next 
 RenderWorld 1

 ;DISPLAY
 DrawImage gBackground,rX#(400),rY#(300)  
 DrawImage gFed(fed),rX#(400),rY#(60)  
 ;show profiles
 If gotim>10 Then DrawFedProfile(fed,-1,-1)
 ;CONSTRUCT NEWSPAPER
 If gotim>0
  ;images
  x=rX#(400) : y=rY#(370)
  DrawImage gNewspaper,x,y
  DrawImage gNewsIdentity(1),x-158,y-165
  DrawImage gNewsAdvert(1),x+42,y-165
  DrawImage gNewsAdvert(2),x+201,y-165
  DrawImage gNewsScene(1),x+146,y+40 
  DrawImage charPhoto(gamChar),x+145,y+34
  ;small print
  SetFont fontNews(0) : Color 110,110,110
  Text x-268,y-107,"The Nation's #1 Newspaper",0,1
  Text x+40,y-107,DescribeDate$(gamDate,gamYear),1,1
  Text x+235,y-107,"50 Cents",0,1
  ;headline
  SetFont fontNews(10) : Color 0,0,0
  Text x+5,y-63,"GAME OVER",1,1
  ;career summary
  g=charGender(gamChar)
  SetFont fontNews(3) : Color 0,0,0
  Text x+5,y+125,"After "+hiExperience(0)+" weeks, "+charName$(gamChar)+" has retired from",1,1
  If fedBank(fed)=>0 Then Text x+5,y+150,fedName$(fed)+" with a fortune of $"+GetFigure$(fedBank(fed))+".",1,1
  If fedBank(fed)<0 Then Text x+5,y+150,fedName$(fed)+" with a debt of $"+GetFigure$(fedBank(fed))+".",1,1
  Text x+5,y+175,He$(g)+" leaves the promotion with a rating of "+fedPopularity(fed)+"%...",1,1 
  ;prompt
  If gotim>100
   SetFont font(2)
   Outline(">>> PRESS ANY COMMAND TO PROCEED >>>",x,y+210,130,130,130,255,255,255)
  EndIf
 EndIf
 ;cursor
 DrawImage gCursor,MouseX(),MouseY()

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
Loader("Please Wait","Saving Career")
PlaySound sMenuGo
FreeTimer timer
;find hall of fame slot
hiInduct=0
For cyc=1 To 10
 If hiChar(cyc)=0 Then hiInduct=cyc
Next
If hiInduct=0
 hi=999999999 : loser=0
 For cyc=1 To 10
  If hiPopularity(cyc)<hi Then hi=hiPopularity(cyc) : loser=cyc
 Next
 hiInduct=loser
EndIf
;transfer details
If hiInduct>0
 hiChar(hiInduct)=gamChar
 hiName$(hiInduct)=charName$(gamChar)
 hiPhoto(hiInduct)=charPhoto(gamChar)
 hiPhotoHeight#(hiInduct)=PortraitHead#(gamChar)
 hiPhotoR(hiInduct)=charPhotoR(gamChar)
 hiPhotoG(hiInduct)=charPhotoG(gamChar)
 hiPhotoB(hiInduct)=charPhotoB(gamChar)
 MaskImage hiPhoto(hiInduct),hiPhotoR(hiInduct),hiPhotoG(hiInduct),hiPhotoB(hiInduct)
 SaveImage(hiPhoto(hiInduct),"Data/Hall Of Fame/Photo"+Dig$(hiInduct,10)+".bmp")
 hiWealth(hiInduct)=hiWealth(0)
 hiPopularity(hiInduct)=hiPopularity(0)
 hiReputation(hiInduct)=hiReputation(0)
 hiExperience(hiInduct)=hiExperience(0)
 hiFed(hiInduct)=charFed(gamChar) 
EndIf
;move to manager ppool
If charFed(gamChar)=<7 Then MoveChar(gamChar,8)
slotActive(slot)=0
hiChar(0)=0
SaveUniverse()
;proceed
screen=26
End Function

;----------------------------------------------------------------------
;/////////////////////// 26. HALL OF FAME /////////////////////////////
;----------------------------------------------------------------------
Function HallOfFame()
;initiate list
If hiInduct>0 Then PlaySound sCrowd(9)
CareerRankings(1)
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
foc=3 : oldfoc=foc : page=1
go=0 : gotim=0 : keytim=10
While go=0

 Cls
 frames=WaitTimer(timer)
 For framer=1 To frames
	
	;counters
	keytim=keytim-1
	If keytim<1 Then keytim=0
	flashTim=flashTim+1
	If flashTim>30 Then flashTim=0 
	
	;PORTAL
    gotim=gotim+1
	If gotim>20 
	 ;quit
	 If KeyDown(1) Then go=-1 
	 ;proceed
	 If KeyDown(28) Or ButtonPressed() Or (MouseDown(1) And foc=3) Then go=1
	EndIf
	;music
	ManageMusic(-1) 
	  
	;CONFIGURE 
	oldPage=page
	If gotim>20 And keytim=0
	 ;change category
	 mousy=0
	 If MouseX()>rX#(100) And MouseX()<rX#(700) And MouseY()>rY#(115) And MouseY()<rY#(525) Then mousy=1
	 If (mousy=1 And MouseDown(2) And foc=0) Or KeyDown(203) Or JoyXDir()=-1 Then page=page-1 : PlaySound sMenuBrowse : keytim=6 
	 If (mousy=1 And MouseDown(1) And foc=0) Or KeyDown(205) Or JoyXDir()=1 Then page=page+1 : PlaySound sMenuBrowse : keytim=6  
	 ;reset entries
	 If KeyDown(29) And KeyDown(19)
	  Loader("Please Wait","Populating List")
	  PlaySound sTrash : keytim=10 
	  For count=1 To 10
       hiChar(count)=fedBooker(Rnd(1,9))
       hiName$(count)=charName$(hiChar(count))
       hiPhoto(count)=charPhoto(hiChar(count))
 	   hiPhotoHeight#(count)=PortraitHead#(hiChar(count))
 	   hiPhotoR(count)=charPhotoR(hiChar(count))
 	   hiPhotoG(count)=charPhotoG(hiChar(count))
 	   hiPhotoB(count)=charPhotoB(hiChar(count))
 	   MaskImage hiPhoto(count),hiPhotoR(count),hiPhotoG(count),hiPhotoB(count)
 	   SaveImage(hiPhoto(count),"Data/Hall Of Fame/Photo"+Dig$(count,10)+".bmp")     
       hiWealth(count)=count*1000    
       hiPopularity(count)=count*5
       hiReputation(count)=count*5
       hiExperience(count)=count
       hiFed(count)=charFed(hiChar(count))
      Next 
      CareerRankings(page)
	 EndIf
	EndIf
	;limits
	If page<1 Then page=4
	If page>4 Then page=1
    ;update list
	If page<>oldPage 
	 CareerRankings(page)
    EndIf
	
 UpdateWorld
 Next
 RenderWorld 1

 ;DISPLAY
 DrawImage gBackground,rX#(400),rY#(300)
 DrawImage gLogo(2),rX#(400),rY#(60)
 ;RANKINGS
 x=165 : y=173
 For count=1 To 10
  cyc=hiRank(count)
  ;photo
  If hiPhoto(cyc)>0
   If count=1
    DrawImage hiPhoto(cyc),rX#(x),rY#(y)-40 
   Else
    reveal=(64-hiPhotoHeight#(cyc))+10
    DrawImageRect hiPhoto(cyc),rX#(x),(rY#(y)+10)+(64-reveal),0,0,76,reveal
   EndIf
  EndIf
  ;position
  SetFont fontNews(4)
  If hiChar(cyc)>0 And count=1 Then SetFont fontNews(8) 
  If hiChar(cyc)>0 And count=10 Then SetFont fontNews(3) 
  r=255 : g=230 : b=110
  If hiInduct>0 And cyc=hiInduct Then r=255 : g=Rnd(150,250) : b=130
  If hiChar(cyc)>0 Then Outline(count+".",rX#(x+35),rY#(y),0,0,0,r,g,b)
  If hiChar(cyc)=0 Then Outline("-",rX#(x+35),rY#(y),0,0,0,r,g,b)
  ;name
  r=255 : g=255 : b=255
  If hiInduct>0 And cyc=hiInduct Then r=255 : g=Rnd(150,250) : b=130
  If hiChar(cyc)>0 Then namer$=hiName$(cyc) Else namer$=""
  SqueezeFont(namer$,150,18)
  OutlineStraight(namer$,rX#(x+55),rY#(y),0,0,0,r,g,b)
  ;promotion
  r=175 : g=175 : b=175
  If hiInduct>0 And cyc=hiInduct Then r=255 : g=Rnd(150,250) : b=130
  If hiChar(cyc)>0 Then namer$=fedName$(hiFed(cyc)) Else namer$=""
  SqueezeFont(namer$,125,18)
  Outline(namer$,rX#(x+300),rY#(y),0,0,0,r,g,b)
  ;value
  SetFont fontNews(3)
  If page=1 Then namer$="$"+GetFigure$(hiWealth(cyc)) : header$="Wealthiest Careers"
  If page=2 Then namer$=hiPopularity(cyc)+"%" : header$="Most Popular Careers"
  If page=3 Then namer$=hiReputation(cyc)+"%" : header$="Most Reputable Careers"
  If page=4 Then namer$=hiExperience(cyc)+" Weeks" : header$="Longest Careers"
  If hiChar(cyc)=0 Then namer$=""
  r=100 : g=200 : b=100 
  If hiInduct>0 And cyc=hiInduct Then r=255 : g=Rnd(150,250) : b=130
  Outline(namer$,rX#(x+440),rY#(y),0,0,0,r,g,b)
  y=y+37
 Next 
 ;header
 SetFont font(4)
 Outline(header$,rX#(400),rY#(130),50,35,20,250,230,110)
 ;options
 foc=0
 DrawOption(3,rX#(400),rY#(560),"<<< EXIT <<<","")
 ;advice
 ;SetFont font(1)
 ;If foc=1 And flashTim>15
  ;Outline("CLICK TO BROWSE",rX#(80),rY#(320)-10,0,0,0,255,255,255)
  ;Outline("CATEGORIES",rX#(80),rY#(320)+10,0,0,0,255,255,255) 
  ;Outline("CLICK TO BROWSE",rX#(720),rY#(320)-10,0,0,0,255,255,255)
  ;Outline("CATEGORIES",rX#(720),rY#(320)+10,0,0,0,255,255,255) 
 ;EndIf
 ;cursor 
 If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect 
 DrawImage gCursor,MouseX(),MouseY()

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
If go=1 Then PlaySound sMenuGo Else PlaySound sMenuBack
FreeTimer timer
screen=10
If hiInduct>0 Then screen=7
hiInduct=0
End Function

;----------------------------------------------------------------------
;/////////////////////// 28. WORLD LEADERS ////////////////////////////
;----------------------------------------------------------------------
Function WorldLeaders()
;initiate list
fed=0 : page=1
GetRankings(page)
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
foc=3 : oldfoc=foc
go=0 : gotim=0 : keytim=10
While go=0

 Cls
 frames=WaitTimer(timer)
 For framer=1 To frames
	
	;counters
	keytim=keytim-1
	If keytim<1 Then keytim=0
	flashTim=flashTim+1
	If flashTim>50 Then flashTim=0 
	
	;PORTAL
    gotim=gotim+1
	If gotim>20 
	 ;quit
	 If KeyDown(1) Then go=-1 
	 ;proceed
	 If KeyDown(28) Or ButtonPressed() Or MouseDown(1)
	  If foc=3 Then go=1
	 EndIf
	EndIf
	;hotspots
	foc=0 
	If MouseX()>rX#(400)-100 And MouseX()<rX#(400)+100 And MouseY()>rY#(60)-45 And MouseY()<rY#(60)+45 Then foc=1
	If MouseX()>rX#(100) And MouseX()<rX#(700) And MouseY()>rY#(115) And MouseY()<rY#(525) Then foc=2 
	
	;CONFIGURE 
	oldPage=page : oldFed=fed
	If gotim>20 And keytim=0
	 ;change category
	 If (MouseDown(2) And foc=1) Or KeyDown(200) Or JoyYDir()=-1 Then fed=fed-1 : PlaySound sMenuBrowse : keytim=8 
	 If (MouseDown(1) And foc=1) Or KeyDown(208) Or JoyYDir()=1 Then fed=fed+1 : PlaySound sMenuBrowse : keytim=8   
	 ;change category
	 If (MouseDown(2) And foc=2) Or KeyDown(203) Or JoyXDir()=-1 Then page=page-1 : PlaySound sMenuBrowse : keytim=5
	 If (MouseDown(1) And foc=2) Or KeyDown(205) Or JoyXDir()=1 Then page=page+1 : PlaySound sMenuBrowse : keytim=5  
	EndIf
	;limits
	If page<1 Then page=28
	If page>28 Then page=1
	If page=>24 And page=<28
	 If fed<1 Then fed=6
	 If fed>6 Then fed=1
	Else
	 If fed<0 Then fed=7
	 If fed>7 Then fed=0 
    EndIf
    ;update list
	If fed<>oldFed Or page<>oldPage 
	 GetRankings(page)
    EndIf
	;music
	ManageMusic(-1) 
	
 UpdateWorld
 Next
 RenderWorld 1

 ;DISPLAY
 DrawImage gBackground,rX#(400),rY#(300)
 If fed>0
  DrawImage gFed(fed),rX#(400),rY#(60)
 Else
  DrawImage gLogo(2),rX#(400),rY#(60)
 EndIf
 ;RANKINGS
 x=165 : y=173
 For count=1 To 10
  char=fedRank(fed,count)
  If page=24 Then char=fedHistChar(fed,0,count)
  If page=25 Then char=fedHistChar(fed,1,count)
  If page=26 Then char=fedHistChar(fed,2,count)
  If page=27 Then char=fedHistChar(fed,3,count)
  If page=28 Then char=fedHistChar(fed,4,count)
  ;photo
  photoChar=char
  If photoChar>0
   If count=1
    DrawImage charPhoto(photoChar),rX#(x),rY#(y)-40 
   Else
    reveal=(64-PortraitHead#(photoChar))+10
    DrawImageRect charPhoto(photoChar),rX#(x),(rY#(y)+10)+(64-reveal),0,0,76,reveal
   EndIf
  EndIf
  ;position
  rank=count
  If page=>24 And page=<28 
   rank=fedHistCount(fed,page-24,count)
  EndIf
  SetFont fontNews(4)
  If char>0 And rank=1 Then SetFont fontNews(8) 
  If char>0 And rank=10 Then SetFont fontNews(3) 
  r=255 : g=230 : b=110
  ;If char=gamChar Then r=255 : g=Rnd(150,250) : b=130
  If rank>0 And char>0 
   Outline(rank+".",rX#(x+35),rY#(y),0,0,0,r,g,b)
  Else
   Outline("-",rX#(x+35),rY#(y),0,0,0,r,g,b)
  EndIf
  ;name
  r=255 : g=255 : b=255
  ;If char=gamChar Then r=255 : g=Rnd(150,250) : b=130
  If char>0 Then namer$=charName$(char) Else namer$=""
  If char>0 And page=27 And fedHistPartner(fed,3,count)>0 Then namer$=charName$(char)+" & "+charName$(fedHistPartner(fed,3,count))
  SqueezeFont(namer$,150,18)
  If page=27 Then SqueezeFont(namer$,300,18)
  OutlineStraight(namer$,rX#(x+55),rY#(y)+1,0,0,0,r,g,b)
  ;promotion
  r=175 : g=175 : b=175
  ;If char=gamChar Then r=255 : g=Rnd(150,250) : b=130
  If char>0 Then namer$=fedName$(charFed(char)) Else namer$=""
  If page=27 Then namer$=""
  SqueezeFont(namer$,125,18)
  If (page=>10 And page=<11) Or (page=>19 And page=<28) Then offset=300 Else offset=305
  Outline(namer$,rX#(x+offset),rY#(y)+1,0,0,0,r,g,b)
  ;value
  SetFont fontNews(4)
  If (page=>10 And page=<11) Or (page=>19 And page=<28) Then SetFont fontNews(3)
  If page=1 Then namer$=charPopularity(char)+"%" : header$="Most Popular Wrestlers"
  If page=2 Then namer$=charStrength(char)+"%" : header$="Strongest Wrestlers"
  If page=3 Then namer$=charSkill(char)+"%" : header$="Most Skilful Wrestlers"
  If page=4 Then namer$=charAgility(char)+"%" : header$="Most Agile Wrestlers"
  If page=5 Then namer$=charStamina(char)+"%" : header$="Fittest Wrestlers"
  If page=6 Then namer$=charToughness(char)+"%" : header$="Toughest Wrestlers"
  If page=7 Then namer$=charAttitude(char)+"%" : header$="Most Reliable Wrestlers"
  If page=8 Then namer$=charHappiness(char)+"%" : header$="Happiest Wrestlers"
  If page=9 Then namer$=AverageStats(char)+"%" : header$="Best Overall Wrestlers"
  If page=10 
   value=CountMatches(char,fed)
   If fed=0 Then value=CountMatches(char,charFed(gamChar))
   namer$=value+" Matches" : header$="Most Used Wrestlers"
  EndIf
  If page=11
   value=GetWinRate(char,fed)
   If fed=0 Then value=GetWinRate(char,charFed(gamChar))
   namer$=value+"% Wins" : header$="Most Favoured Wrestlers"
  EndIf
  If page=12 Then namer$=charHealth(char)+"%" : header$="Healthiest Wrestlers"
  If page=13 Then namer$=GetHeight$(charHeight(char)) : header$="Shortest Wrestlers"
  If page=14 Then namer$=GetHeight$(charHeight(char)) : header$="Tallest Wrestlers"
  If page=15 Then namer$=TranslateWeight(char)+"lbs" : header$="Lightest Wrestlers"
  If page=16 Then namer$=TranslateWeight(char)+"lbs" : header$="Heaviest Wrestlers"
  If page=17 Then namer$=charAge(char)+"yrs" : header$="Youngest Wrestlers"
  If page=18 Then namer$=charAge(char)+"yrs" : header$="Oldest Wrestlers"
  If page=19 Then namer$=charContract(char)+" Weeks" : header$="Most Committed Wrestlers"
  If page=20 Then namer$="$"+GetFigure$(charSalary(char)) : header$="Highest Paid Wrestlers"   
  If page=21 
   namer$=CountTitles(char,fed)+" Titles" : header$="Most Decorated Wrestlers" 
  EndIf
  If page=22 
   namer$=CountRealRelationships(char,1)+" Friends" : header$="Most Sociable Wrestlers"   
  EndIf
  If page=23 
   namer$=CountRealRelationships(char,-1)+" Enemies" : header$="Most Hostile Wrestlers" 
  EndIf
  If page=24 Then namer$=Left$(textMonth$(GetMonth(fedHistDate(fed,0,count))),3)+" "+fedHistYear(fed,0,count) : header$="Management History"
  If page=25 Then namer$=Left$(textMonth$(GetMonth(fedHistDate(fed,1,count))),3)+" "+fedHistYear(fed,1,count) : header$="World Title History"
  If page=26 Then namer$=Left$(textMonth$(GetMonth(fedHistDate(fed,2,count))),3)+" "+fedHistYear(fed,2,count) : header$="Inter Title History"
  If page=27 Then namer$=Left$(textMonth$(GetMonth(fedHistDate(fed,3,count))),3)+" "+fedHistYear(fed,3,count) : header$="Tag Title History"
  If page=28 Then namer$=Left$(textMonth$(GetMonth(fedHistDate(fed,4,count))),3)+" "+fedHistYear(fed,4,count) : header$="Trophy History" 
  If char=0 Then namer$=""
  r=100 : g=200 : b=100 
  ;If char=gamChar Then r=255 : g=Rnd(150,250) : b=130
  Outline(namer$,rX#(x+440),rY#(y),0,0,0,r,g,b)
  y=y+37
 Next 
 ;header
 SetFont fontNews(4)
 Outline(header$+":",rX#(400),rY#(130),50,35,20,250,230,110)
 ;options
 DrawOption(3,rX#(400),rY#(560),"<<< EXIT <<<","")
 ;advice
 ;SetFont font(1)
 ;If foc=1 And flashTim>15
  ;Outline("CLICK TO BROWSE",rX#(400)-160,rY#(60)-10,0,0,0,255,255,255)
  ;Outline("ROSTERS",rX#(400)-160,rY#(60)+10,0,0,0,255,255,255) 
  ;Outline("CLICK TO BROWSE",rX#(400)+160,rY#(60)-10,0,0,0,255,255,255)
  ;Outline("ROSTERS",rX#(400)+160,rY#(60)+10,0,0,0,255,255,255) 
 ;EndIf
 ;If foc=2 And flashTim>15
  ;Outline("CLICK TO BROWSE",rX#(80),rY#(320)-10,0,0,0,255,255,255)
  ;Outline("CATEGORIES",rX#(80),rY#(320)+10,0,0,0,255,255,255) 
  ;Outline("CLICK TO BROWSE",rX#(720),rY#(320)-10,0,0,0,255,255,255)
  ;Outline("CATEGORIES",rX#(720),rY#(320)+10,0,0,0,255,255,255) 
 ;EndIf
 ;cursor 
 If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect 
 DrawImage gCursor,MouseX(),MouseY()

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
If go=1 Then PlaySound sMenuGo Else PlaySound sMenuBack
FreeTimer timer
screen=20
End Function

;/////////////////////////////////////////////////////////////////
;---------------------- RELATED FUNCTIONS ------------------------
;/////////////////////////////////////////////////////////////////

;GET CHARACTER RANKINGS
Function GetRankings(category)
 ;reset records
 For char=1 To no_chars
  charRanked(char)=0
  fedRank(fed,char)=0
 Next
 ;honour roster slots
 If category=0
  If fed=0
   slotter=0
   For promotion=1 To 9
    For count=1 To fedSize(promotion) 
     char=fedRoster(promotion,count)
     If screenAgenda<>1 Or fedLocked(promotion)=0
      slotter=slotter+1
      fedRank(fed,slotter)=char : charRanked(char)=slotter
     EndIf
    Next
   Next
  Else
   For count=1 To fedSize(fed)
    char=fedRoster(fed,count)
    fedRank(fed,count)=char : charRanked(char)=count
   Next
  EndIf
 EndIf
 ;rank according to character stats
 If category=>1 And category=<23
  range=no_chars
  If fed>0 Then range=fedSize(fed)
  If screen=28 Then range=10
  For finder=1 To range
   leader=0
   If category=13 Or category=15 Or category=17 Then hi=9999 Else hi=-1
   For char=1 To no_chars
    excluded=0
    If fed>0 And charFed(char)<>fed Then excluded=1
    If screenAgenda=1 And fedLocked(charFed(char)) Then excluded=1
    If screen=28 And charFed(char)=>8 Then excluded=1
    If excluded=0 And charRanked(char)=0
     If category=1 And charPopularity(char)>hi Then hi=charPopularity(char) : leader=char
     If category=2 And charStrength(char)>hi Then hi=charStrength(char) : leader=char
     If category=3 And charSkill(char)>hi Then hi=charSkill(char) : leader=char
     If category=4 And charAgility(char)>hi Then hi=charAgility(char) : leader=char
     If category=5 And charStamina(char)>hi Then hi=charStamina(char) : leader=char
     If category=6 And charToughness(char)>hi Then hi=charToughness(char) : leader=char
     If category=7 And charAttitude(char)>hi Then hi=charAttitude(char) : leader=char
     If category=8 And charHappiness(char)>hi Then hi=charHappiness(char) : leader=char
     If category=9 And AverageStats(char)>hi Then hi=AverageStats(char) : leader=char 
     If category=10
      value=CountMatches(char,fed)
      If fed=0 Then value=CountMatches(char,charFed(gamChar))
      If value>hi Then hi=value : leader=char
     EndIf
     If category=11
      value=GetWinRate(char,fed)
      If fed=0 Then value=GetWinRate(char,charFed(gamChar))
      If value>hi Then hi=value : leader=char
     EndIf
     If category=12 And charHealth(char)>hi Then hi=charHealth(char) : leader=char
     If category=13 And charHeight(char)<hi Then hi=charHeight(char) : leader=char
     If category=14 And charHeight(char)>hi Then hi=charHeight(char) : leader=char
     If category=15 And TranslateWeight(char)<hi Then hi=TranslateWeight(char) : leader=char
     If category=16 And TranslateWeight(char)>hi Then hi=TranslateWeight(char) : leader=char
     If category=17 And charAge(char)<hi Then hi=charAge(char) : leader=char
     If category=18 And charAge(char)>hi Then hi=charAge(char) : leader=char
     If category=19 And charContract(char)>hi Then hi=charContract(char) : leader=char
     If category=20 And charSalary(char)>hi Then hi=charSalary(char) : leader=char
     If category=21
      value=CountTitles(char,fed)
      If value>hi Then hi=value : leader=char
     EndIf
     If category=22
      value=CountRealRelationships(char,1)
      If value>hi Then hi=value : leader=char
     EndIf
     If category=23
      value=CountRealRelationships(char,-1)
      If value>hi Then hi=value : leader=char
     EndIf
    EndIf
   Next
   fedRank(fed,finder)=leader : charRanked(leader)=finder
  Next
 EndIf
End Function

;GET CAREER RANKINGS
Function CareerRankings(category)
 ;reset checkers
 For count=1 To 10
  hiRank(count)=0
 Next
 For count=1 To 10
  charRanked(count)=0
 Next
 ;find top 10
 For finder=1 To 10
  hi=-999999 : leader=0
  For cyc=1 To 10
   If hiChar(cyc)>0 And charRanked(cyc)=0
    If category=1 And hiWealth(cyc)>hi Then hi=hiWealth(cyc) : leader=cyc
    If category=2 And hiPopularity(cyc)>hi Then hi=hiPopularity(cyc) : leader=cyc
    If category=3 And hiReputation(cyc)>hi Then hi=hiReputation(cyc) : leader=cyc
    If category=4 And hiExperience(cyc)>hi Then hi=hiExperience(cyc) : leader=cyc
   EndIf
  Next
  hiRank(finder)=leader : charRanked(leader)=finder
 Next
End Function

;RANK PROMOTIONS
Function RankPromotions()
 ;reset and clock previous
 For promotion=1 To 6
  fedOldList(promotion)=fedList(promotion)
  fedList(promotion)=0
  fedOldRanked(promotion)=fedRanked(promotion)
  fedRanked(promotion)=0
 Next
 ;update list
 For finder=1 To 6
  hi=0 : leader=0
  For promotion=1 To 6
   If fedPopularity(promotion)>hi And fedRanked(promotion)=0 Then leader=promotion : hi=fedPopularity(promotion)
  Next
  fedList(finder)=leader : fedRanked(leader)=finder
 Next
End Function

;CLOCK NEW CHAMPIONS
Function WriteHistory(promotion,title) ;0=booker, 1=world, 2=inter, 3=tags, 4=trophy
 If game=1 And slot>0
  ;find changes
  change=0
  If title=0 And fedBooker(promotion)<>fedHistChar(promotion,title,1) Then change=1
  If title=1 And fedChampWorld(promotion)<>fedHistChar(promotion,title,1) Then change=1
  If title=2 And fedChampInter(promotion)<>fedHistChar(promotion,title,1) Then change=1
  If title=3
   If fedChampTag(promotion,1)<>fedHistChar(promotion,title,1) And fedChampTag(promotion,2)<>fedHistChar(promotion,title,1) Then change=1
   If fedChampTag(promotion,1)<>fedHistPartner(promotion,title,1) And fedChampTag(promotion,2)<>fedHistPartner(promotion,title,1) Then change=1
  EndIf
  If title=4 And gamDate<>fedHistDate(promotion,title,1) Then change=1 
  If change=1
   ;get working character
   If title=0 Then char=fedBooker(promotion)
   If title=1 Then char=fedChampWorld(promotion)
   If title=2 Then char=fedChampInter(promotion)
   If title=3 Then char=fedChampTag(promotion,1)
   If title=4 Then char=fedCupHolder(promotion)
   If char>0
    ;move down priors
    For count=1 To 9
     source=10-count : target=11-count 
     fedHistCount(promotion,title,target)=fedHistCount(promotion,title,source)
     fedHistChar(promotion,title,target)=fedHistChar(promotion,title,source)
     fedHistPartner(promotion,title,target)=fedHistPartner(promotion,title,source)
     fedHistDate(promotion,title,target)=fedHistDate(promotion,title,source)
     fedHistYear(promotion,title,target)=fedHistYear(promotion,title,source)
    Next
    ;new entry 
    If fedHistChar(promotion,title,2)>0 Then fedHistCount(promotion,title,1)=fedHistCount(promotion,title,2)+1 Else fedHistCount(promotion,title,1)=1
    fedHistChar(promotion,title,1)=char
    fedHistPartner(promotion,title,1)=0
    If title=3 Then fedHistPartner(promotion,title,1)=fedChampTag(promotion,2)
    If game=0 Or slot=0 Then GetRealDate()
    fedHistDate(promotion,title,1)=gamDate
    fedHistYear(promotion,title,1)=gamYear
   EndIf
  EndIf
 EndIf
End Function

;CALCULATE NIGHT ENTERTAINMENT RATING
Function GetNightRating(score)
 value=1
 If score=>3000 Then value=2
 If score=>3500 Then value=3
 If score=>4000 Then value=4
 If score=>4500 Then value=5 
 Return value
End Function

;CALCULATE NIGHT HARDCORE CONTENT
Function GetNightContent(score)
 value=1
 If score=>50 Then value=2
 If score=>100 Then value=3
 If score=>250 Then value=4
 If score=>500 Then value=5
 Return value
End Function

;PREVIEW MATCH RATINGS
Function PreviewMatchRatings(date,x,y,task) ;0=preview, 1=review
 ;image
 DrawImage gMagazine,x,y
 ;smallprint
 SetFont fontNews(0) : Color 100,100,100
 Text x-270,y-205,"Wrestling Review Magazine",0,1
 Text x,y-205,DescribeDate$(date,gamYear),1,1 
 Text x+210,y-205,"news@wrestlingreview.com",1,1
 ;headline
 ;SetFont fontNews(1) : Color 70,70,70
 ;Text x+10,y-85,"All the results from the show that "+fedName$(fed)+" promoted on the "+DescribeDate$(date,0)+":",1,1 
 ;prompt
 If task=1 And foc=10 And gamSegments(date)=<8
  SetFont font(2)
  Outline(">>> PRESS ANY COMMAND TO PROCEED >>>",x,y+195,120,120,120,250,250,250)
 EndIf
 ;segments
 If gamSegments(date)>0
  listY=y-65 : spacing=225/gamSegments(date)
  If spacing<20 Then spacing=20
  If spacing>30 Then spacing=30
  For count=1 To gamSegments(date)
   If task=0 Or (task=1 And gotim>(count*35)-20)
    If gamMatchWinner(date,count)>0
     reveal=(64-PortraitHead#(gamMatchWinner(date,count)))+10
     DrawImageRect charPhoto(gamMatchWinner(date,count)),x-210,(listY+10)+(64-reveal),0,0,76,reveal
     If TitleHolder(gamMatchWinner(date,count),0)>0 Then DrawImage gBelt(TitleHolder(gamMatchWinner(date,count),0)),x-210,listY+10
    EndIf
    DrawImage gRating(GetMatchRating(gamMatchScore(date,count))),x+115,listY-1
    DrawImage gHardcore(GetMatchContent(gamMatchHardcore(date,count))),x+200,listY-1
    If count=10 Then SetFont fontNews(3) Else SetFont fontNews(4)
    Outline(count+".",x-185,listY,0,0,0,255,230,110)
    SetFont fontNews(1)
    namer$="Nobody won this contest"
    If gamMatchWinner(date,count)>0 And gamMatchLoser(date,count)>0
     If gamMatchFormat(date,count)=1 Then namer$=charName$(gamMatchWinner(date,count))+" defeated "+charName$(gamMatchLoser(date,count))
     If gamMatchFormat(date,count)=2 Then namer$=charTeamName$(gamMatchWinner(date,count))+" defeated "+charTeamName$(gamMatchLoser(date,count))
     If gamMatchFormat(date,count)=3 Then namer$=charName$(gamMatchWinner(date,count))+" defeated multiple opponents"
    EndIf
    OutlineStraight(namer$,x-170,listY,210,200,190,70,70,70)
    listY=listY+spacing
   EndIf
  Next
 EndIf
 ;overall rating
 If task=1 And gamSchedule(date)<>4
  listY=listY+10
  If listY<y+150 Then listY=y+140
  If listY>y+175 Then listY=y+175
  If gotim>((gamSegments(date)*35)-20)+50
   If gamSegments(date)=0
    DrawImage gRating(GetNightRating(gamNightScore(date))),x-45,y+35
    DrawImage gHardcore(GetNightContent(gamNightHardcore(date))),x+40,y+35
   Else
    DrawImage gRating(GetNightRating(gamNightScore(date))),x+115,listY-1
    DrawImage gHardcore(GetNightContent(gamNightHardcore(date))),x+200,listY-1
   EndIf
   lineA$="" : lineB$=""
   If gamSegments(date)=>6
    ;1-star reviews
    If GetNightRating(gamNightScore(date))=1
     If fedOldPopularity(fed)<65
      lineA$="One of the world's most pathetic promotions"
      lineB$="continues to be an embarrassment to the sport..."
     EndIf
     If fedOldPopularity(fed)=>65 And fedOldPopularity(fed)<75
      lineA$=fedName$(fed)+" let themselves down"
      lineB$="when they desperately need to make progress..."
     EndIf
     If fedOldPopularity(fed)=>75 And fedOldPopularity(fed)<85
      lineA$="A promotion that's aiming for the top"
      lineB$="shows us wrestling at its worst..."
     EndIf
     If fedOldPopularity(fed)=>85
      lineA$="An astonishing fall from grace for one of"
      lineB$="the world's leading wrestling promotions..."
     EndIf
    EndIf
    ;2-star reviews
    If GetNightRating(gamNightScore(date))=2
     If fedOldPopularity(fed)<65
      lineA$="A struggling promotion just about convinces"
      lineB$="us that they've got something to live for..."
     EndIf
     If fedOldPopularity(fed)=>65 And fedOldPopularity(fed)<75
      lineA$="The kind of unremarkable show we've come to"
      lineB$="expect from this unremarkable promotion..."
     EndIf
     If fedOldPopularity(fed)=>75 And fedOldPopularity(fed)<85
      lineA$="A disappointing setback for a promotion"
      lineB$="that could use a little consistency..."
     EndIf
     If fedOldPopularity(fed)=>85
      lineA$="The cracks are starting to appear in"
      lineB$=fedName$(fed)+"'s invincible facade..."
     EndIf
    EndIf
    ;3-star reviews
    If GetNightRating(gamNightScore(date))=3
     If fedOldPopularity(fed)<65
      lineA$="A surprisingly solid effort from a"
      lineB$="promotion that many had written off!"
     EndIf
     If fedOldPopularity(fed)=>65 And fedOldPopularity(fed)<75
      lineA$="A definite step in the right direction,"
      lineB$="which they'll be hoping to build on..."
     EndIf
     If fedOldPopularity(fed)=>75 And fedOldPopularity(fed)<85
      lineA$="Good enough to stay afloat, but they need to"
      lineB$="set their sights higher to make progress..."
     EndIf
     If fedOldPopularity(fed)=>85
      lineA$="Not bad, but you'd expect so much more from"
      lineB$="one of the world's leading promotions..."
     EndIf
    EndIf
    ;4-star reviews
    If GetNightRating(gamNightScore(date))=4
     If fedOldPopularity(fed)<65
      lineA$="An inspiring effort from a promotion"
      lineB$="that might just have a future!"
     EndIf
     If fedOldPopularity(fed)=>65 And fedOldPopularity(fed)<75
      lineA$="An average promotion proves that they have"
      lineB$="what it takes to deliver something special..."
     EndIf
     If fedOldPopularity(fed)=>75 And fedOldPopularity(fed)<85
      lineA$="A confident step forward for a company"
      lineB$="that has its sights set on the top..."
     EndIf
     If fedOldPopularity(fed)=>85
      lineA$="The solid entertainment that we've come to"
      lineB$="expect, but 'good' may not be good enough..."
     EndIf
    EndIf
    ;5-star reviews
    If GetNightRating(gamNightScore(date))=5
     If fedOldPopularity(fed)<65
      lineA$="Some promotions may be in the gutter,"
      lineB$="but this one is looking at the stars!"
     EndIf
     If fedOldPopularity(fed)=>65 And fedOldPopularity(fed)<75
      lineA$="A rare moment of genius from a struggling"
      lineB$="promotion that many had underestimated!"
     EndIf
     If fedOldPopularity(fed)=>75 And fedOldPopularity(fed)<85
      lineA$=fedName$(fed)+" make a bid for"
      lineB$="the bigtime with an unforgettable show!"
     EndIf
     If fedOldPopularity(fed)=>85
      lineA$="With another classic, "+fedName$(fed)
      lineB$="remind us why they belong at the top!"
     EndIf
    EndIf
   Else
    ;reduced cards
    If GetNightRating(gamNightScore(date))=1
     lineA$="This pathetic excuse for a show sold fans"
     lineB$="short on both quantity AND quality!"
    EndIf
    If GetNightRating(gamNightScore(date))=2
     lineA$="Offering a reduced card is always a risk,"
     lineB$="and this one simply didn't pay off..."
    EndIf
    If GetNightRating(gamNightScore(date))=3
     lineA$="Some fans may feel short-changed by the quantity,"
     lineB$="but there was just enough quality to pull it off..."
    EndIf
    If GetNightRating(gamNightScore(date))=4
     lineA$="Despite the small card, there was plenty of"
     lineB$="entertainment to be had. More please!"
    EndIf  
    If GetNightRating(gamNightScore(date))=5
     lineA$="Short but very, very sweet! Even the reduced"
     lineB$="card can't stop this going down as a classic..."
    EndIf
   EndIf
   If gamSegments(date)=0
    lineA$=fedName$(fed)+" show disdain for their"
    lineB$="fans by failing to offer a single match!"
   EndIf
   SetFont fontNews(1) : Color 70,70,70
   If gamSegments(date)=0
    Outline(lineA$,x,y-16,210,200,190,70,70,70)
    Outline(lineB$,x,y,210,200,190,70,70,70)
   Else
    Outline(lineA$,x-90,listY-8,210,200,190,70,70,70)
    Outline(lineB$,x-90,listY+8,210,200,190,70,70,70)
   EndIf
  EndIf
 EndIf
End Function