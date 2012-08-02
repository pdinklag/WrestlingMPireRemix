;//////////////////////////////////////////////////////////////////////////////
;----------------------- WRESTLING MPIRE 2008: CAREER ISSUES ------------------
;//////////////////////////////////////////////////////////////////////////////

;//////////////////////////////////////////////////////////////////////////////
;-------------------------- 27. CONFIRM UNIVERSE ------------------------------
;//////////////////////////////////////////////////////////////////////////////
Function ConfirmUniverse()
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
	 ;cancel
	 If KeyDown(1) Then go=-1
	 ;proceed
	 If KeyDown(28) Or ButtonPressed() Or MouseDown(1)
	  If foc=3 Then go=-1 Else go=1
	 EndIf
	EndIf
	;music
	ManageMusic(-1) 
	
	;CONFIGURATION 
	If gotim>20 And keytim=0
	 ;highlight option
	 If KeyDown(200) Or JoyYDir()=-1 Then foc=foc-1 : PlaySound sMenuSelect : keytim=6
	 If KeyDown(208) Or JoyYDir()=1 Then foc=foc+1 : PlaySound sMenuSelect : keytim=6   
	EndIf  
	;limits
	If foc<1 Then foc=3
	If foc>3 Then foc=1     
	
 UpdateWorld
 Next
 RenderWorld 1

 ;DISPLAY
 DrawImage gBackground,rX#(400),rY#(300)
 DrawImage gLogo(1),rX#(400),rY#(135)
 ;DrawImage gMDickie,rX#(400),rY#(530) 
 ;message
 SetFont font(5)
 y=rY#(325)
 Outline("This folder already has a universe on file.",rX#(400),y-28,0,0,0,255,255,255)
 Outline("Would you like to insert a new character",rX#(400),y,0,0,0,255,255,255)
 Outline("into that one or start from scratch?",rX#(400),y+28,0,0,0,255,255,255) 
 ;options
 DrawOption(1,rX#(400),rY#(425),"INHERIT UNIVERSE","")  
 DrawOption(2,rX#(400),rY#(465),"REPLACE UNIVERSE","") 
 DrawOption(3,rX#(400),rY#(560),"<<< BACK <<<","")   
 ;cursor
 If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect  
 DrawImage gCursor,MouseX(),MouseY()

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;proceed
If go>0 Then PlaySound sMenuGo Else PlaySound sMenuBack
FreeTimer timer
;re-use slot
If go=1 And foc=1
 Loader("Please Wait","Generating Career")
 SwitchSlot(targetSlot)
 cupSlot=3+slot
 gamInherit=1
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
;reset slot
If go=1 And foc=2
 Loader("Please Wait","Generating Career")
 SwitchSlot(0)
 slot=targetSlot
 cupSlot=3+slot 
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
;cancel
If go=-1 Then slotActive(slot)=0 : screen=10
End Function

;//////////////////////////////////////////////////////////////////////////////
;------------------------------ 20. CALENDAR ----------------------------------
;//////////////////////////////////////////////////////////////////////////////
Function Calendar()
;restore arena
fed=charFed(gamChar)
If arenaApron=0 Then GenerateArena(charFed(gamChar),gamSchedule(gamDate),1)
arenaPreset=gamVenue(gamDate) 
If optFog>0 Then arenaAtmos=gamAtmos(gamDate) Else arenaAtmos=0
arenaAttendance=TranslateAttendance(gamAttendance(gamDate)) 
arenaRopes=gamRopes(gamDate)
arenaPads=gamPads(gamDate)
arenaCanvas=gamCanvas(gamDate)
arenaApron=gamApron(gamDate)
arenaMatting=gamMatting(gamDate) 
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
scroll=gamScroll : scrollTim=0 : scrollSpeed=5
foc=10 : oldfoc=foc : subfoc=0
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
	 If KeyDown(28) Or ButtonPressed() Or (MouseDown(1) And MouseY()>rY#(300))
	  If foc=11 Then go=-1 Else go=1
	  If foc=>1 And foc=<2 And go=1 
	   If gamSegments(gamDate)=>optSegmentLim Or gamSchedule(gamDate)=<0 Or gamSchedule(gamDate)=4 Then BlockAccess()
	  EndIf
	 EndIf
	EndIf
	;music
	ManageMusic(-1) 	
	
	;CONFIGURATION 
	If gotim>20 And keytim=0
	 ;highlight option
	 If KeyDown(200) Or JoyYDir()=-1
	  newFoc=foc-1
	  If foc=1 Or foc=5 Then newFoc=foc
	  If foc=9 Then newFoc=4  
	  foc=newFoc : keytim=5
	 EndIf
	 If KeyDown(208) Or JoyYDir()=1
	  newFoc=foc+1
	  If foc=4 Or foc=8 Then newFoc=9
	  If foc=11 Then newFoc=foc
	  foc=newFoc : keytim=5
	 EndIf
	 If (KeyDown(203) Or JoyXDir()=-1) And foc=>1 And foc=<8
	  If foc=>5 And foc=<8 Then newFoc=foc-4 Else newFoc=foc
	  foc=newFoc : keytim=5
	 EndIf
	 If (KeyDown(205) Or JoyXDir()=1) And foc=>1 And foc=<8
	  If foc=>1 And foc=<4 Then newFoc=foc+4 Else newFoc=foc
	  foc=newFoc : keytim=5
	 EndIf
	EndIf  
	;limits
	If foc<1 Then foc=11
	If foc>11 Then foc=1 
	
	;SCROLLING
	;aim for current
	scrollDelay=50
	If MouseY()<rY#(185)-45 Or MouseY()>rY#(185)+45 Or scrollTim>scrollDelay/2 Then scrollTim=scrollTim-1
	If scrollTim<0 Then scrollTim=0
	If scrollTim=0 Then scrollTarget=-((GetMonth(gamDate)-1)*255)
	If MouseDown(1)=0 Then scrollSpeed=scrollSpeed+1  
	If scrollSpeed>100 Then scrollSpeed=100
	;override with browsing
	;If KeyDown(203)
	 ;If scrollSpeed<5 Then scrollSpeed=5
	 ;scrollTarget=scrollTarget+scrollSpeed : scrollTim=scrollDelay 
	;EndIf
	;If KeyDown(205)
	 ;If scrollSpeed<5 Then scrollSpeed=5
	 ;scrollTarget=scrollTarget-scrollSpeed : scrollTim=scrollDelay
	;EndIf
	If MouseY()>rY#(185)-45 And MouseY()<rY#(185)+45 And MouseDown(1)
	 If scrollTim<scrollDelay-1 Then void=MouseXSpeed()
	 scroll=scroll+MouseXSpeed()
	 scrollSpeed=0 : scrollTim=scrollDelay 
	EndIf
	If scrollTim>0 And scrollTim<scrollDelay Then scrollSpeed=0
	;pursue target  
	If scrollTarget>0 Then scrollTarget=0
	If scrollTarget<-(11*255) Then scrollTarget=-(11*255)  
	If scroll<scrollTarget Then scroll=scroll+scrollSpeed
	If scroll>scrollTarget Then scroll=scroll-scrollSpeed 
	If scroll=>scrollTarget-scrollSpeed And scroll=<scrollTarget+scrollSpeed Then scroll=scrollTarget  
	;limits
	If scroll>0 Then scroll=0
	If scroll<-(11*255) Then scroll=-(11*255)  
	If scroll=scrollTarget And scrollTim<scrollDelay Then scrollSpeed=0
	
 UpdateWorld
 Next
 RenderWorld 1

 ;DISPLAY
 DrawImage gBackground,rX#(400),rY#(300)
 DrawImage gFed(charFed(gamChar)),rX#(400),rY#(60)
 ;profile
 DrawFedProfile(charFed(gamChar),-1,-1)
 ;SCHEDULE
 subFoc=0
 plotOldX=0 : plotOldY=0
 x=rX#(400)+scroll : y=rY#(185)
 For month=1 To 12
  ;month boxes
  SetFont font(3)
  If month=GetMonth(gamDate)
   DrawImage gMonth(2),x,y
   Outline(textMonth$(month)+" "+gamYear,x,y+25,0,0,0,255,255,255)
  Else
   DrawImage gMonth(1),x,y
   Outline(textMonth$(month)+" "+gamYear,x,y+25,0,0,0,155,155,155)
  EndIf
  ;week boxes
  dateX=x-75
  For week=1 To 4
   date=GetDate(month,week)
   If scrollTim<scrollDelay And MouseX()=>dateX-23 And MouseX()=<dateX+26 And MouseY()=>y-37 And MouseY()=<y+20
    subFoc=date
   EndIf
   If (GetMonth(gamDate)=month And GetWeek(gamDate)=week) Or date=subFoc
    DrawImage gDateHighlight,dateX,y-13
    Color 255,240,100 : Rect dateX-25,y-38,51,51,0
   EndIf
   If InjuryDate(date)
    DrawImage gInjuryDate,dateX,y-13
   Else
    If gamSchedule(date)=-2 Then DrawImage gCourtDate,dateX,y-13
    If gamSchedule(date)=>0 Then DrawImage gEvent(gamSchedule(date)),dateX,y-13
   EndIf
   If date<gamDate Then DrawImage gHistory(0),dateX,y-13
   ;week number
   Color 0,0,0 : Rect dateX-24,y-37,8,11,1
   Color 155,155,155 : Rect dateX-26,y-39,9,12,1
   If (GetMonth(gamDate)=month And GetWeek(gamDate)=week) Or date=subFoc
    Color 255,255,255 : Rect dateX-26,y-39,9,12,1
   EndIf
   Color 0,0,0 : Rect dateX-26,y-39,9,12,0
   SetFont fontStat(0)
   Outline(week,dateX-21,y-34,0,0,0,0,0,0)
   ;success graph
   If date<gamDate And gamSchedule(date)>0 And gamSchedule(date)<>4
    rating=GetNightRating(gamNightScore(date))
    If rating=<1 Then plotY=y+16 : Color 200,15,250
    If rating=2 Then plotY=y+10 : Color 250,15,55
    If rating=3 Then plotY=y+4 : Color 250,100,10
    If rating=4 Then plotY=y-6 : Color 250,150,10
    If rating=5 Then plotY=y-15 : Color 250,190,10
    If plotOldX<>0 And plotOldY<>0 Then Line plotOldX,plotOldY-13,dateX,plotY-13
    plotOldX=dateX : plotOldY=plotY
    DrawImage gResult(rating),dateX,y-13
   EndIf
   If gamMission>0 And date=gamDeadline Then DrawImage charPhoto(201),dateX-25,y-28
   dateX=dateX+50
  Next
  x=x+255
 Next
 ;DESCRIPTION
 ;establish which details to show
 If subFoc>0 Then showDate=subFoc Else showDate=gamDate
 showEvent=gamSchedule(showDate)
 If InjuryDate(showDate) Then showEvent=-1 
 ;event type
 y=rY#(269)
 SetFont font(4)
 If showEvent=-2 Then Outline("COURT CASE",rX#(400),y-21,0,0,0,150,100,50)
 If showEvent=-1 Then Outline("INJURED",rX#(400),y-11,0,0,0,200,50,50)
 If showEvent=0 Then Outline("EMPTY",rX#(400),y-11,0,0,0,150,150,150)
 If fed=7
  If showEvent=1 Then Outline("TRAINING SESSION",rX#(400),y-21,0,0,0,90,150,210)
  If showEvent=2 Then Outline("STUDENT SHOWCASE",rX#(400),y-21,0,0,0,200,100,230)
 Else
  If showEvent=1 Then Outline("TV TAPING",rX#(400),y-21,0,0,0,90,150,210)
  If showEvent=2 Then Outline("PAY-PER-VIEW",rX#(400),y-21,0,0,0,200,100,230)
 EndIf
 If showEvent=3 Then Outline("TOURNAMENT",rX#(400),y-21,0,0,0,250,200,100)
 If showEvent=4 Then Outline("INTER-PROMOTIONAL CONTEST",rX#(400),y-21,0,0,0,100,220,80)
 If showEvent=5 Then Outline("CHARITY EVENT",rX#(400),y-21,0,0,0,225,75,105)
 If showEvent=6 Then Outline(Upper$(charName$(fedRoster(9,fedSize(9))))+" MEMORIAL",rX#(400),y-21,0,0,0,150,100,100)
 ;opponent line
 SetFont font(4)
 If showEvent=0 
  Outline("No Segments Required",rX#(400),y+10,0,0,0,255,255,255)
 Else
  namer$=""
  If showDate=<gamDate Then namer$=gamSegments(showDate)+"/"+optSegmentLim+" Segments Delivered"
  If showDate>gamDate Then namer$="Card To Be Announced"
  If showEvent=-2 Then namer$="Vs "+charName$(gamCourtChar(showDate))
  If showEvent=4 Then namer$="Vs "+fedName$(gamRivalFed(showDate))
  Outline(namer$,rX#(400),y,0,0,0,255,255,255)
 EndIf
 ;context info
 SetFont font(2)
 namer$="" 
 If showEvent=-2
  If showDate=>gamDate Then namer$="Accused Of '"+textCourtCase$(gamCourtCase(showDate))+"'"
  If showDate<gamDate And gamNightScore(showDate)<0 Then namer$="Convicted Of '"+textCourtCase$(gamCourtCase(showDate))+"'"
  If showDate<gamDate And gamNightScore(showDate)>0 Then namer$="Cleared Of '"+textCourtCase$(gamCourtCase(showDate))+"'" 
 EndIf
 If showEvent>0
  namer$="Venue To Be Announced"
  If showDate=<gamDate Then namer$=GetFigure$(gamAttendance(showDate))+" Fans At "+textCity$(fed,gamVenue(showDate))
 EndIf
 Outline(namer$,rX#(400),y+20,0,0,0,200,200,200)
 ;OPTIONS
 y=328
 DrawOption(1,rX#(400)-103,rY#(y),"BOOK MATCH","")
 DrawOption(2,rX#(400)-103,rY#(y+36),"TOURNAMENT","")
 DrawOption(3,rX#(400)-103,rY#(y+72),"TRAINING","")
 DrawOption(4,rX#(400)-103,rY#(y+108),"PRODUCTION","")
 DrawOption(5,rX#(400)+103,rY#(y),"EDIT ROSTER","")
 DrawOption(6,rX#(400)+103,rY#(y+36),"STUDY DATABASE","")
 DrawOption(7,rX#(400)+103,rY#(y+72),"BUY TALENT","")
 DrawOption(8,rX#(400)+103,rY#(y+108),"SELL TALENT","") 
 DrawOption(9,rX#(400),rY#(y+150),"RETIRE!","")
 namer$=">>> PROCEED >>>"  
 If gamSchedule(gamDate)>0 Then namer$=">>> END NIGHT >>>"
 If gamSchedule(gamDate)=4 And gamSegments(gamDate)=0 Then namer$=">>> ROUND ONE >>>"
 If gamSchedule(gamDate)=4 And gamSegments(gamDate)=1 Then namer$=">>> ROUND TWO >>>"
 If gamSchedule(gamDate)=4 And gamSegments(gamDate)=2 Then namer$=">>> ROUND THREE >>>"
 DrawOption(10,rX#(400),rY#(524),namer$,"") 
 DrawOption(11,rX#(400),rY#(560),"<<< SAVE & EXIT <<<","")  
 ;preview ratings
 If KeyDown(57) Or MouseDown(2)
  If subFoc>0 And subFoc<gamDate Then date=subFoc Else date=gamDate
  If date=<gamDate And gamSchedule(date)>0 And gamSegments(date)>0
   PreviewMatchRatings(date,rX#(400),rY#(390),0)
  EndIf
 EndIf
 ;cursor
 If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect 
 If subFoc<>oldSubFoc Then oldSubFoc=subFoc : PlaySound sMenuSelect 
 DrawImage gCursor,MouseX(),MouseY()

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
FreeTimer timer
If go=1 Then PlaySound sMenuGo Else PlaySound sMenuBack 
editFoc=0 : editScroll=0 ;: editFilter=0
gamScroll=scroll
;access option
If go=1 
 If foc=1 Then GetMatchRules(2) : AddGimmick(0) : promoFoc=0 : screen=14 : screenAgenda=0 ;book match
 If foc=2 Then screen=16 : screenAgenda=11 ;tournament
 If foc=3 Then screen=12 : screenAgenda=6 ;training
 If foc=4 Then screen=31 ;production
 If foc=5 Then editFed=fed : screen=12 : screenAgenda=1 ;edit roster
 If foc=6 Then screen=28 : screenAgenda=2 ;database
 If foc=7 Then screen=11 : screenAgenda=3 ;buy
 If foc=8 Then screen=12 : screenAgenda=4 ;sell
 If foc=9 Then negChar=201 : negTopic=1 : negSetting=1 : screen=53 ;retire
EndIf
;end night
If go=1 And foc=10 
 screen=29
 If gamSchedule(gamDate)=<0 Or gamSchedule(gamDate)=4 Then screen=24
 If gamSchedule(gamDate)=-2 Then screen=55
 If gamSchedule(gamDate)=4 And gamSegments(gamDate)=<2 Then ConstructInterPromotional(gamSegments(gamDate)+1)
EndIf
;save & exit
If go=-1
 GrabImage slotPreview(slot),(rX#(400)-225)-18,rY#(25)+34
 Loader("Please Wait","Saving Career")
 SaveImage(slotPreview(slot),"Data/Previews/Preview0"+slot+".bmp")
 SaveUniverse()
 SaveProgress(slot)
 SaveWorld(slot)
 SaveChars(slot)
 screen=10
EndIf
End Function

;/////////////////////////////////////////////////////////////////////////////////////
;-------------------------------- 31. PRODUCTION -------------------------------------
;/////////////////////////////////////////////////////////////////////////////////////
Function Production()
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
fed=charFed(gamChar)
foc=gamBuild : oldfoc=foc : confirmer=0
keytim=10 : go=0 : gotim=0
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
	 If KeyDown(1) Then go=-1
	 If KeyDown(28) Or ButtonPressed() Or MouseDown(1) 
	  If foc=0 Then go=-1
	 EndIf
	EndIf
	;music
	ManageMusic(-1) 
	
	;CONFIGURE
	If gotim>20 And keytim=0
	 ;browse options
	 If KeyDown(200) Or JoyYDir()=-1
	  newFoc=foc-1
	  If foc=>1 And foc=<4 Then newFoc=foc
	  If foc=>5 And foc=<12 Then newFoc=foc-4
	  If foc=0 Then newFoc=10 
	  foc=newFoc : keytim=5
	 EndIf
	 If KeyDown(208) Or JoyYDir()=1
	  newFoc=foc+1
	  If foc=>1 And foc=<8 Then newFoc=foc+4
	  If foc=>9 And foc=<12 Then newFoc=0
	  If foc=0 Then newFoc=foc
	  foc=newFoc : keytim=5
	 EndIf
	 If KeyDown(203) Or JoyXDir()=-1
	  If foc=0 Or foc=1 Or foc=5 Or foc=9 Then newFoc=foc Else newFoc=foc-1
	  foc=newFoc : keytim=5
	 EndIf
	 If KeyDown(205) Or JoyXDir()=1
	  If foc=0 Or foc=4 Or foc=8 Or foc=12 Then newFoc=foc Else newFoc=foc+1
	  foc=newFoc : keytim=5
	 EndIf
     If foc<0 Then foc=12	
     If foc>12 Then foc=0
     ;change status
     If foc=>1 And foc=<12
      If KeyDown(28) Or ButtonPressed() Or MouseDown(1) 
	   ;build new
	   If foc=gamBuild And gamAgreement(13)=0
	    gamBuild=0 : PlaySound sMenuBack : keytim=10
	   Else
	    If gamAgreement(foc)>0 Or gamAgreement(13)>0
	     PlaySound sVoid : keytim=10 
	    Else
	     If fedProduction(fed,foc)=0 Then gamBuild=foc : PlaySound sProduce : keytim=10
        EndIf
       EndIf
       ;destroy existing
       If fedProduction(fed,foc)>0
        If confirmer=foc
         fedProduction(fed,foc)=0 : confirmer=0 : PlaySound sTrash : keytim=10
        Else 
         confirmer=foc : PlaySound sMenuGo : keytim=10
        EndIf
       EndIf
      EndIf
	  ;instant delete
	  If (KeyDown(14) Or KeyDown(211)) And fedProduction(fed,foc)>0
	   PlaySound sTrash : keytim=10
	   fedProduction(fed,foc)=0 : confirmer=0
	   If gamBuild=foc Then gamBuild=0
	  EndIf
	  ;activate cheat!
      If KeyDown(56) And KeyDown(25) And fedProduction(fed,foc)=0
       PlaySound sProduce : keytim=10
       fedProduction(fed,foc)=1
       If gamBuild=foc Then gamBuild=0
	  EndIf
	 EndIf
	EndIf 
	;reset confirmer
	If foc<>confirmer Then confirmer=0
	
 UpdateWorld
 Next
 RenderWorld 1

 ;DISPLAY
 DrawImage gBackground,rX#(400),rY#(300)
 DrawImage gFed(fed),rX#(400),rY#(60)
 DrawFedProfile(fed,-1,-1)
 ;boxes
 x=rX#(400)-285 : y=216
 For cyc=1 To 12
  If MouseX()=>x-80 And MouseX()=<x+80 And MouseY()=>(rY#(y)-50)-37 And MouseY()<(rY#(y)-50)+37 Then foc=cyc
  DrawImage gProduction(cyc),x,rY#(y)-50 
  If fedProduction(fed,cyc)=0 Then DrawImage gProduction(0),x,rY#(y)-50
  Color 0,0,0
  Rect x-78,(rY#(y)-50)-36,157,72,0
  Rect x-80,(rY#(y)-50)-38,161,76,0 
  If fedProduction(fed,cyc)>0 Then Color 0,150,0 Else Color 150,0,0
  Rect rX#(x)-79,(rY#(y)-50)-37,159,75,0
  DrawOption(cyc,x,rY#(y),prodName$(cyc),"")
  SetFont fontStat(0)
  r=200 : g=150 : b=100
  If foc=cyc Then r=250 : g=200 : b=100
  Outline("$"+GetFigure$(prodCost(cyc))+" per week",x,rY#(y)+13,0,0,0,r,g,b)
  If cyc=gamBuild
   SetFont font(4)
   Outline("WORKING",x,rY#(y)-50,0,0,0,100,200,100)
   DrawImage gCrew,x,rY#(y)-44
  EndIf
  If confirmer=cyc
   SetFont font(2)
   Outline("ARE YOU SURE YOU",x,(rY#(y)-50)-9,0,0,0,200,100,200)
   Outline("WANT TO REMOVE?",x,(rY#(y)-50)+9,0,0,0,200,100,200)
  EndIf
  x=x+192
  If x>rX#(400)+400 Then x=rX#(400)-285 : y=y+120
 Next
 ;explanation
 SetFont font(3) 
 If foc>0 Then Outline(prodDesc$(foc),rX#(400),rY#(508),0,0,0,255,255,255)
 ;proceed
 DrawOption(0,rX#(400),rY#(560),"<<< BACK <<<","")
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

;GENERATE CAREER
Function GenerateCareer()
 ;reset agreements
 For count=1 To 50
  gamAgreement(count)=0
 Next
 ;reset schedule
 If gamInherit=0 Then GetRealDate()
 ResetSchedule(1)
 gamSchedule(gamDate)=2 : gamVenue(gamDate)=0
 GenerateArena(charFed(gamChar),gamSchedule(gamDate),1)
 gamAttendance(gamDate)=GenerateAttendance()
 arenaAttendance=TranslateAttendance(gamAttendance(gamDate))  
 cupSize(cupSlot)=0
 gamMission=0 : gamTarget=0 : gamDeadline=0
 gamBuild=0 : gamFatality=0 : gamComplete=0
 ;reset promotions
 For promotion=1 To 9
  fedOldPopularity(promotion)=fedPopularity(promotion)
  fedOldReputation(promotion)=fedReputation(promotion)
  For title=0 To 4
   charTitles(gamChar,promotion,title)=0
   If gamInherit=0
    For count=1 To 10
     fedHistCount(promotion,title,count)=0
     fedHistChar(promotion,title,count)=0
     fedHistPartner(promotion,title,count)=0
     fedHistDate(promotion,title,count)=0
     fedHistYear(promotion,title,count)=0
    Next
    WriteHistory(promotion,title)
   EndIf
  Next
  If gamInherit=0
   champ=fedChampWorld(promotion)
   If charTitles(fedChampWorld(promotion),promotion,1)<1 Then charTitles(fedChampWorld(promotion),promotion,1)=1
   If charTitles(fedChampInter(promotion),promotion,2)<1 Then charTitles(fedChampInter(promotion),promotion,2)=1
   If charTitles(fedChampTag(promotion,1),promotion,3)<1 Then charTitles(fedChampTag(promotion,1),promotion,3)=1
   If charTitles(fedChampTag(promotion,2),promotion,3)<1 Then charTitles(fedChampTag(promotion,2),promotion,3)=1
   If charTitles(fedCupHolder(promotion),promotion,4)<1 Then charTitles(fedCupHolder(promotion),promotion,4)=1
  EndIf
  For count=1 To 12
   fedProduction(promotion,count)=1
  Next
 Next
 ;reset ratings
 If gamInherit=0
  RankPromotions()
  ResetTVRatings() 
  For count=1 To 20
   showPopularity(count)=showPopularity(count)+Rnd(-5,5)
   If count=<6 Then showPopularity(count)=fedPopularity(count)
   showRanked(count)=0
  Next
  For count=1 To 20
   hi=0 : leader=0
   For cyc=1 To 20
    If showPopularity(cyc)>hi And showRanked(cyc)=0 Then hi=showPopularity(cyc) : leader=cyc 
   Next
   showList(count)=leader : showRanked(leader)=count
   showOldList(count)=showList(count) : showOldRanked(leader)=showRanked(leader)
  Next
 EndIf
 ;reset characters
 ResetHealthStatus(gamChar)
 For char=1 To no_chars
  ResetOldValues(char)
  If char<>gamChar And charFed(char)=<7 And gamInherit=0 
   ResetCareerStatus(char)
   If charContract(char)>0 Then charContract(char)=Rnd(0,48) 
   charHealth(char)=Rnd(0,200)
   If charHealth(char)>100 Then charHealth(char)=100
   randy=Rnd(0,charToughness(char)*2)
   If randy=<5 Then charInjured(char,Rnd(0,5))=Rnd(1,8) : charHealth(char)=0
  EndIf
  For promotion=1 To 9
   If promotion<>charFed(char) Or promotion=charFed(gamChar) 
    charExperience(char,promotion)=0
    charMatches(char,promotion)=0
    charWins(char,promotion)=0
   EndIf
   If promotion=charFed(gamChar) And charFed(char)=charFed(gamChar) Then charExperience(char,promotion)=1
  Next
  For count=1 To 3
   gamNegotiated(char,count)=0
  Next
  For count=1 To 50
   charAgreement(char,count)=0
  Next
  charRelationship(gamChar,char)=0
  charRelationship(char,gamChar)=0
  For v=1 To no_chars
   ;If charRelationship(char,v)<0 Then charRelationship(char,v)=-5 
   charHistory(char,v)=0 : charTeamHistory(char,v)=0
  Next
  charTeamHistory(char,charPartner(char))=2
  charTeamHistory(char,charManager(char))=3
  If charPartner(char)=gamChar Then charPartner(char)=0
  If charManager(char)=gamChar Then charManager(char)=0
  If gamInherit=0 Then charTradeReaction(char)=0
  If gamInherit=0 Then charWeightChange(char)=0
  charArrived(char)=1
 Next
End Function

;RESET HEALTH STATUS
Function ResetHealthStatus(char) 
 charHealth(char)=100
 charVacant(char)=0
 charTrainCourse(char)=0
 For limb=0 To 5
  charInjured(char,limb)=0
  charOldInjured(char,limb)=0 
 Next
 For limb=1 To 50
  charLimb(char,limb)=1
 Next
End Function

;RESET CAREER STATUS
Function ResetCareerStatus(char) 
 If charAge(char)=>40 Then charPeaked(char)=charAge(char) Else charPeaked(char)=0
 matchLoops=charAge(char)-16
 matchLoops=Rnd(matchLoops,matchLoops*4)
 If charFed(char)=8 Then matchLoops=matchLoops/5
 If matchLoops<1 Then matchLoops=1
 For promotion=1 To 9
  charMatches(char,promotion)=0 
  charWins(char,promotion)=0
 Next
 For count=1 To matchLoops
  randy=Rnd(1,50)
  If randy=<7
   charMatches(char,randy)=charMatches(char,randy)+1 
  Else
   charMatches(char,charFed(char))=charMatches(char,charFed(char))+1
  EndIf
 Next
 For promotion=1 To 9
  charExperience(char,promotion)=Int(PercentOf#(charMatches(char,promotion),150))
  For count=1 To charMatches(char,promotion)
   randy=Rnd(40,100)
   If randy=<GetValue(char) Then charWins(char,promotion)=charWins(char,promotion)+1
  Next
 Next
 GenerateContract(char)
End Function

;RESET SCHEDULE
Function ResetSchedule(start)
 rotor=Rnd(0,1) : showWeek=Rnd(1,4)
 For date=start To 48
  ;events
  gamSchedule(date)=0
  If date=>gamDate
   gamSchedule(date)=1
   If GetWeek(date)=showWeek Then gamSchedule(date)=2
  EndIf
  randy=Rnd(0,20)
  If randy=<1 Then gamSchedule(date)=0
  ;matches
  gamRivalFed(date)=Rnd(1,6)
  gamOpponent(date)=0
  gamMatch(date)=0
  gamGimmick(date)=0
  gamPromo(date)=0
  gamResult(date)=0
  ;venues
  gamVenue(date)=0
  gamAttendance(date)=0
  gamAtmos(date)=0
  gamRopes(date)=0
  gamPads(date)=0
  gamCanvas(date)=0
  gamApron(date)=0
  gamMatting(date)=0
  ;card
  gamSegments(date)=0
  For count=1 To 10
   gamMatchScore(date,count)=0
   gamMatchHardcore(date,count)=0
   gamMatchFormat(date,count)=0 
   gamMatchWinner(date,count)=0
   gamMatchLoser(date,count)=0
  Next
  gamNightScore(date)=0
  gamNightHardcore(date)=0
 Next
 ;negate missions
 gamMission=0
 gamTarget=0
 gamDeadline=0
 cupSize(cupSlot)=0
 ;generate new location
 GenerateArena(charFed(gamChar),gamSchedule(gamDate),0)
 gamAttendance(gamDate)=GenerateAttendance()
 arenaAttendance=TranslateAttendance(gamAttendance(gamDate)) 
End Function

;CALCULATE REAL DATE!
Function GetRealDate()
 ;identify week
 weeker=Int(Left$(CurrentDate$(),2))
 If weeker=<7 Then week=1
 If weeker=>8 And weeker=<15 Then week=2
 If weeker=>16 And weeker=<23 Then week=3
 If weeker>23 Then week=4
 ;identify month
 For count=1 To 12
  monther$=Mid$(CurrentDate$(),4,3)
  If monther$=Left$(textMonth$(count),3) Then month=count
 Next
 ;translate to game
 gamDate=GetDate(month,week)
 gamYear=Int(Right$(CurrentDate$(),4))
End Function

;CALCULATE DATE FROM WEEK & MONTH
Function GetDate(month,week)
 value=((month-1)*4)+week
 Return value
End Function

;EXTRACT MONTH FROM DATE
Function GetMonth(date)
 value=1
 While date>0
  date=date-4
  If date>0 Then value=value+1
 Wend
 Return value
End Function

;EXTRACT WEEK FROM DATE
Function GetWeek(date)
 value=date-((GetMonth(date)-1)*4)
 Return value
End Function

;DESCRIBE DATE
Function DescribeDate$(date,year)
 dateline$=textWeek$(GetWeek(date))+" of "+textMonth$(GetMonth(date))
 If year>0 Then dateline$=dateline$+" "+year
 Return dateline$
End Function

;FIND NEXT FREE DATE
Function NextDate()
 value=0
 For date=gamDate To 48
  If date>gamDate And gamSchedule(date)=>1 And gamSchedule(date)=<2 Then value=date : Exit
 Next
 Return value
End Function

;FIND NEXT COURT DATE
Function CourtDate()
 value=0
 For date=gamDate To 48
  If gamSchedule(date)=-2 Then value=date : Exit
 Next
 Return value
End Function

;FIND LAST RESULT
Function LastResult()
 value=0
 For count=1 To 48
  date=gamDate-count
  If date>0
   If gamSchedule(date)>0 And gamResult(date)>0 Then value=gamResult(date)
  EndIf
  If value>0 Then Exit
 Next
 Return value
End Function

;FIND LAST EVENT
Function LastEvent()
 value=0
 For count=1 To 48
  date=gamDate-count
  If date>0
   If gamSchedule(date)<>0 Then value=gamSchedule(date) : Exit
  EndIf
 Next
 Return value
End Function

;ALREADY BOOKED TO FACE OPPONENT?
Function OpponentBooked(char)
 value=0
 For date=gamDate To 48
  If gamSchedule(date)>0 And gamOpponent(date)=char Then value=1
 Next
 Return value
End Function

;COUNT CLIENTS
Function CountClients(char)
 value=0
 For count=1 To fedSize(charFed(char))
  v=fedRoster(charFed(char),count)
  If charManager(v)=char Then value=value+1
 Next
 Return value
End Function

;GET MATCH PROPERTIES
Function GetMatchRules(match)
 ;defaults
 matchPreset=match
 matchLocation=0
 matchShoot=0
 matchTables=0
 ;confrontation
 If match=0 Then matchLocation=Rnd(1,2)
 If match=<1
  no_wrestlers=2 : no_refs=0 : matchType=5 : matchRules=0 : matchTeams=0
  matchPins=0 : matchSubs=0 : matchKOs=1 : matchBlood=0 : matchCountOuts=0
  matchTimeLim=0
 EndIf
 ;normal
 If match=2
  no_wrestlers=2 : no_refs=1 : matchType=1 : matchRules=2 : matchTeams=0
  matchPins=1 : matchSubs=1 : matchKOs=0 : matchBlood=0 : matchCountOuts=2
  matchTimeLim=10
 EndIf
 ;best of three
 If match=3
  no_wrestlers=2 : no_refs=1 : matchType=2 : matchRules=2 : matchTeams=0
  matchPins=1 : matchSubs=1 : matchKOs=0 : matchBlood=0 : matchCountOuts=2
  matchTimeLim=15
 EndIf
 ;iron man
 If match=4
  no_wrestlers=2 : no_refs=1 : matchType=3 : matchRules=2 : matchTeams=0
  matchPins=1 : matchSubs=1 : matchKOs=0 : matchBlood=0 : matchCountOuts=2
  matchTimeLim=10
 EndIf
 ;24/7 challenge
 If match=5
  no_wrestlers=2 : no_refs=1 : matchType=4 : matchRules=2 : matchTeams=0
  matchPins=1 : matchSubs=1 : matchKOs=0 : matchBlood=0 : matchCountOuts=2
  matchTimeLim=10
 EndIf
 ;submission
 If match=6
  no_wrestlers=2 : no_refs=1 : matchType=1 : matchRules=2 : matchTeams=0
  matchPins=0 : matchSubs=1 : matchKOs=0 : matchBlood=0 : matchCountOuts=1
  matchTimeLim=10
 EndIf
 ;shoot fight
 If match=7
  no_wrestlers=2 : no_refs=1 : matchType=5 : matchRules=2 : matchTeams=0
  matchPins=0 : matchSubs=0 : matchKOs=1 : matchBlood=0 : matchCountOuts=1
  matchTimeLim=10 : matchShoot=1
 EndIf 
 ;last man standing
 If match=8
  no_wrestlers=2 : no_refs=1 : matchType=5 : matchRules=0 : matchTeams=0
  matchPins=0 : matchSubs=0 : matchKOs=1 : matchBlood=0 : matchCountOuts=0
  matchTimeLim=0
 EndIf
 ;first blood
 If match=9
  no_wrestlers=2 : no_refs=1 : matchType=5 : matchRules=0 : matchTeams=0
  matchPins=0 : matchSubs=0 : matchKOs=0 : matchBlood=1 : matchCountOuts=0
  matchTimeLim=0
 EndIf
 ;triple threat
 If match=10
  no_wrestlers=3 : no_refs=1 : matchType=1 : matchRules=2 : matchTeams=0
  matchPins=1 : matchSubs=1 : matchKOs=0 : matchBlood=0 : matchCountOuts=2
  matchTimeLim=10
 EndIf
 ;handicap
 If match=11
  no_wrestlers=3 : no_refs=1 : matchType=1 : matchRules=2 : matchTeams=1
  matchPins=1 : matchSubs=1 : matchKOs=0 : matchBlood=0 : matchCountOuts=2
  matchTimeLim=10
 EndIf
 ;tag team
 If match=12
  no_wrestlers=4 : no_refs=1 : matchType=1 : matchRules=2 : matchTeams=2
  matchPins=1 : matchSubs=1 : matchKOs=0 : matchBlood=0 : matchCountOuts=2
  matchTimeLim=15 
 EndIf
 ;team battle
 If match=13
  no_wrestlers=4 : no_refs=1 : matchType=5 : matchRules=2 : matchTeams=1
  matchPins=1 : matchSubs=1 : matchKOs=0 : matchBlood=0 : matchCountOuts=2
  matchTimeLim=15
 EndIf
 ;royal brawl
 If match=14
  no_wrestlers=4 : no_refs=1 : matchType=5 : matchRules=2 : matchTeams=0
  matchPins=1 : matchSubs=1 : matchKOs=0 : matchBlood=0 : matchCountOuts=2
  matchTimeLim=0
 EndIf
 ;battle royal
 If match=15
  no_wrestlers=10 : no_refs=0 : matchType=5 : matchRules=0 : matchTeams=0
  matchPins=0 : matchSubs=0 : matchKOs=0 : matchBlood=0 : matchCountOuts=3
  matchTimeLim=0
 EndIf
 ;timed battle royal
 If match=16
  no_wrestlers=10 : no_refs=0 : matchType=5 : matchRules=0 : matchTeams=-1
  matchPins=0 : matchSubs=0 : matchKOs=0 : matchBlood=0 : matchCountOuts=3
  matchTimeLim=1
 EndIf
 ;sumo match
 If match=17
  no_wrestlers=2 : no_refs=1 : matchType=5 : matchRules=2 : matchTeams=0
  matchPins=0 : matchSubs=1 : matchKOs=1 : matchBlood=0 : matchCountOuts=3
  matchTimeLim=10
 EndIf
 ;cage escape
 If match=18
  no_wrestlers=2 : no_refs=0 : matchType=5 : matchRules=0 : matchTeams=0
  matchPins=0 : matchSubs=0 : matchKOs=0 : matchBlood=0 : matchCountOuts=3
  matchTimeLim=0
 EndIf
 ;six man tag
 If match=19
  no_wrestlers=6 : no_refs=1 : matchType=1 : matchRules=2 : matchTeams=2
  matchPins=1 : matchSubs=1 : matchKOs=0 : matchBlood=0 : matchCountOuts=2
  matchTimeLim=15 
 EndIf
 ;eight man tag
 If match=20
  no_wrestlers=8 : no_refs=1 : matchType=5 : matchRules=2 : matchTeams=2
  matchPins=1 : matchSubs=1 : matchKOs=0 : matchBlood=0 : matchCountOuts=2
  matchTimeLim=0 
 EndIf
 ;tables match
 If match=21
  no_wrestlers=2 : no_refs=1 : matchType=5 : matchRules=0 : matchTeams=0
  matchPins=0 : matchSubs=0 : matchKOs=0 : matchBlood=0 : matchCountOuts=0 : matchTables=1
  matchTimeLim=0

  ;mostly wooden tables, mostly ringside
  no_items=20
  itemSelection=2
  itemLayout=3
 EndIf
 ;time limit filter
 If matchTimeLim>0 And matchTeams=>0
  If optLength=3 Then matchTimeLim=matchTimeLim+5
 EndIf
 ;size limit
 If fed>0 And no_wrestlers>fedSize(fed) Then no_wrestlers=fedSize(fed) : no_refs=0
 ;referee filter
 If optReferees=0 Then no_refs=0
 If optReferees=1 And no_wrestlers>3 Then no_refs=0
 If optReferees=2 And no_wrestlers>4 Then no_refs=0
 If optReferees=3 And no_wrestlers>8 Then no_refs=0
 no_plays=no_wrestlers+no_refs
End Function

;ADD GIMMICK TO CAREER MATCH
Function AddGimmick(gimmick)
 ;reset by default
 matchCage=0 : matchRopes=0 : matchBlastTim=0
 no_items=20 : no_weaps=20
 If game=1 And fed=charFed(gamChar) And fedProduction(fed,11)=0 Then no_items=0
 If game=1 And fed=charFed(gamChar) And fedProduction(fed,12)=0 Then no_weaps=0
 itemSelection=1 : itemLayout=1 
 weapSelection=1 : weapLayout=1 
 matchChamps=1 : matchPromo=0
 ;hardcore rules
 If gimmick=1 
  no_items=no_items+(no_items/4)
  itemSelection=0 : itemLayout=0
  no_weaps=no_weaps+(no_weaps/4)
  weapSelection=0 : weapLayout=0 
  matchRules=0 
 EndIf
 If gimmick=>1 And gimmick=<8 And matchRules>1 Then matchRules=1
 ;cages
 If gimmick=2
  matchCage=Rnd(-1,4)
  If matchCage<1 Then matchCage=1
  If matchPreset=6 Or matchPreset=7 Then matchRules=2 
 EndIf
 ;novelty ropes
 If gimmick=3 Then matchRopes=1 ;barbed wire
 If gimmick=4 Then matchRopes=2 ;electrified
 If gimmick=5 Then matchRopes=3 ;inferno 
 ;blast
 If gimmick=6
  If optLength=1 Then matchBlastTim=3
  If optLength=2 Then matchBlastTim=5
  If optLength=3 Then matchBlastTim=7
 EndIf
 ;minefield
 If gimmick=7
  no_weaps=no_weaps+(no_weaps/4)
  weapSelection=20 : weapLayout=5
 EndIf
 ;hall of mirrors
 If gimmick=8
  no_items=no_items+(no_items/4)
  itemSelection=15 : itemLayout=5 
 EndIf
 ;stipulations
 If gimmick=9 Then matchChamps=6 ;hair vs hair
 If gimmick=10 Then matchChamps=7 ;loser leaves
 If gimmick=11 Then gamAttendance(date)=0 : arenaAttendance=0 : matchRules=0 ;empty arena
 ;race against time
 If gimmick=12 And matchTimeLim>0 Then matchTimeLim=optLength
 ;multiple referees
 If gimmick=13 Then no_refs=2 : no_plays=no_wrestlers+no_refs
 ;item/weapon block
 If game=1 And gamAgreement(19)>0 Then no_items=0 : no_weaps=0
 ;negate titles
 If matchTeams=-1 Or fed=0 Or fed=>7 Then matchChamps=0
 If no_wrestlers>4 And matchTeams=<0 And matchChamps=<1 Then matchChamps=5
 If screenAgenda=10 Or screenAgenda=11 Or screenAgenda=12 Then matchChamps=0
 If game=0 Then matchChamps=0
End Function

;ASSIGN OPPONENT
Function AssignOpponent(char,date,task) ;0=main, 1=additional
 v=0 : its=0
 Repeat
  satisfied=1
  ;random by default
  v=fedRoster(charFed(char),Rnd(1,fedSize(charFed(char))))
  If gamSchedule(date)=>4 Then v=Rnd(1,no_chars)
  If gamSchedule(date)=4 And charFed(char)=charFed(gamChar)
   v=fedRoster(gamRivalFed(date),Rnd(1,fedSize(gamRivalFed(date))))
   If its=0 And TitleHolder(char,3) Then v=fedChampTag(gamRivalFed(date),1)
   If its=0 And TitleHolder(char,2) Then v=fedChampInter(gamRivalFed(date))
   If its=0 And TitleHolder(char,1) Then v=fedChampWorld(gamRivalFed(date))
  EndIf
  If gamSchedule(date)=4 And charFed(char)=gamRivalFed(date)
   v=fedRoster(charFed(gamChar),Rnd(1,fedSize(charFed(gamChar))))
   If its=0 And TitleHolder(char,3) Then v=fedChampTag(charFed(gamChar),1)
   If its=0 And TitleHolder(char,2) Then v=fedChampInter(charFed(gamChar))
   If its=0 And TitleHolder(char,1) Then v=fedChampWorld(charFed(gamChar))
  EndIf
  If screen=54 Then v=fedRoster(7,Rnd(1,fedSize(7)))
  ;favour enemies
  If task=0 And FindEnemy(char,0)>0 And screen<>54
   randy=Rnd(0,2)
   If (randy=0 And gamSchedule(date)=1) Or (randy=<1 And gamSchedule(date)=2)
    Repeat
     v=fedRoster(charFed(char),Rnd(1,fedSize(charFed(char))))
     early=Rnd(0,2)
    Until (charRelationship(char,v)=>-4 And charRelationship(char,v)=<-1) Or (early=0 And charRelationship(char,v)<0)
   EndIf
  EndIf
  ;career logic
  If charFed(char)=charFed(v) And screen<>54
   If (TitleHolder(char,1) And charPopularity(v)=<charPopularity(char)-10) Or (TitleHolder(v,1) And charPopularity(char)=<charPopularity(v)-10)
    satisfied=0 ;CPU's not worthy of world title shot
   EndIf 
   If (TitleHolder(char,2) And charPopularity(v)=>charPopularity(char)+10) Or (TitleHolder(v,2) And charPopularity(char)=>charPopularity(v)+10)
    satisfied=0 ;CPU's not ideal for inter title shot
   EndIf
   randy=Rnd(0,1)
   If (randy=0 And TitleHolder(char,3) And charPartner(v)=0) Or (randy=1 And TitleHolder(v,3) And charPartner(char)=0)
    satisfied=0 ;involve tag champions less
   EndIf
   If TitleHolder(char,0)>0 And TitleHolder(v,0)>0 Then satisfied=0 ;prevent champion vs champion
  EndIf
  ;universal logic
  randy=Rnd(0,1)
  If randy>0 And task=0 And charPartner(char)>0 And charPartner(v)=0 Then satisfied=0 ;favour teams vs teams
  randy=Rnd(0,1)
  If randy>0 And task=0 And charHeel(v)=charHeel(char) And TitleHolder(v,0)=0 Then satisfied=0 ;rarely same allegiance in opposition
  If screen=54 And charFed(v)<>7 Then satisfied=0 ;favour students for sparring
  randy=Rnd(0,4)
  If (randy>0 And v=fedBooker(charFed(char))) Or (randy>0 And charRole(v)=2) Or charRole(v)=3 
   If charRelationship(char,v)=>0 And TitleHolder(v,0)=0 And charFed(char)<>7 Then satisfied=0 ;avoid non-wrestlers
  EndIf
  If charVacant(v)>0 Or charFed(v)=>8 Or (charFed(v)=7 And charFed(char)<>7) Then satisfied=0 ;avoid inactive wrestlers
  If TournamentStatus(v)>0 Then satisfied=0 ;avoid pre-occupied wrestlers
  If charFed(char)<>charFed(v) And TitleHolder(v,0)>0 And TitleHolder(char,0)<>TitleHolder(v,0) Then satisfied=0 ;avoid champion counterparts
  randy=Rnd(0,4)
  If (randy>0 And charHealth(v)<50) Or (randy>2 And charHealth(v)<75) Or InjuryStatus(v)>0 Then satisfied=0 ;favour healthy
  If charLimb(v,40)=0 And charLimb(v,43)=0 Then satisfied=0 ;avoid cripples!
  If gamSchedule(date)=>4 And charFed(v)=charFed(char) Then satisfied=0 ;avoid colleagues at special events
  If v=0 Or v=char Or v=charPartner(char) Or v=charManager(char) Then satisfied=0 ;avoid suicide
  If v=gamChar Then satisfied=0 ;avoid player for CPU's 
  If screen=22 And charFought(v)>0 Then satisfied=0 ;avoid already fought in simulations
  If task=1
   For count=1 To no_plays
    If v=pChar(count) Or v=charPartner(pChar(count)) Or v=charManager(pChar(count)) Then satisfied=0 ;avoid already involved
   Next
  EndIf
  ;get-out clause
  its=its+1
  If its>1000 Then satisfied=1
 Until satisfied=1
 Return v
End Function

;FIND IDEAL TEAM-MATE
Function AssignPartner(char,task) ;-1=permanent, 0=for match, 1=for tournament
 partner=0 : its=0
 Repeat
  ;random by default
  satisfied=1 : its=its+1
  partner=fedRoster(charFed(char),Rnd(1,fedSize(charFed(char))))
  If its>500 Then partner=Rnd(1,no_chars)
  ;force partners
  If its<100
   If charPartner(char)>0 Then partner=charPartner(char)
   If char=fedChampTag(charFed(char),1) Then partner=fedChampTag(charFed(char),2)
   If char=fedChampTag(charFed(char),2) Then partner=fedChampTag(charFed(char),1) 
  EndIf
  ;ideal criteria
  If its<100
   If charPartner(partner)>0 And charPartner(partner)<>char And partner<>charPartner(char) Then satisfied=0 ;avoid those with other partners
   If TitleHolder(partner,3)<>TitleHolder(char,3) Then satisfied=0 ;avoid tag champions
  EndIf
  If its=>100 And its<200 And charRelationship(char,partner)=<0 Then satisfied=0 ;prefer friends
  If its=>200 And its<300 And charHeel(char)<>charHeel(partner) Then satisfied=0 ;prefer same allegiance
  If its=>300 And its<400 And charFed(char)<>charFed(partner) Then satisfied=0 ;prefer same fed 
  ;important exceptions
  If game=1
   randy=Rnd(0,2)
   If (randy=0 And charHealth(partner)<50) Or (randy=<1 And charHealth(partner)<25) Then satisfied=0 ;favour healthy
   If charVacant(partner)>0 Or TournamentStatus(partner)>0 Then satisfied=0 ;avoid unavailable characters
  EndIf 
  randy=Rnd(0,5)
  If randy>0 And charLimb(partner,40)=0 And charLimb(partner,43)=0 Then satisfied=0 ;avoid cripples!
  randy=Rnd(0,4)
  If (randy>0 And charRole(partner)<>1) Or charRole(partner)=3 Or charFed(partner)=>8 Then satisfied=0 ;avoid non-wrestlers 
  If task=0 
   For v=1 To no_plays
    If partner=pChar(v) Or (partner=charManager(pChar(v)) And partner<>charPartner(pChar(v)) And its<100)
     satisfied=0 ;already involved in match
    EndIf
   Next   
  EndIf
  If task=1 And TournamentSelected(partner)>0 Then satisfied=0 ;already involved in tournament
  If partner=0 Or char=partner Then satisfied=0 ;avoid suicide
  If its>1000 Then satsified=1
 Until satisfied=1
 Return partner
End Function

;ASSIGN REFEREE
Function AssignReferee()
 char=0 : its=0
 Repeat
  ;random by default
  satisfied=1 : its=its+1
  char=Rnd(1,no_chars)
  If fed>0 And gamSchedule(gamDate)=<3 Then char=fedRoster(fed,Rnd(1,fedSize(fed)))
  ;favour designated 
  randy=Rnd(0,6)
  If randy>0 And fed>0 And gamSchedule(gamDate)=<3
   subIts=0
   Repeat
    char=fedRoster(fed,Rnd(1,fedSize(fed))) : subIts=subIts+1   
   Until charRole(char)=3 Or char=fedBooker(7) Or subIts>1000
  EndIf
  ;check suitability
  If charVacant(char)>0 Or TournamentStatus(char)>0 Then satisfied=0 ;avoid unavailable characters
  randy=Rnd(0,2)
  If charRole(char)=3 Then randy=Rnd(0,5)
  If (randy=0 And charHealth(char)<50) Or (randy=<1 And charHealth(char)<25) Or InjuryStatus(char)>0 Or charFed(char)=9 
   satisfied=0 ;favour healthy
  EndIf
  If charLimb(char,40)=0 And charLimb(char,43)=0 Then satisfied=0 ;avoid cripples!
  If gamSchedule(date)=4 And (charFed(char)=charFed(gamChar) Or charFed(char)=gamRivalFed(gamDate)) Then satisfied=0 ;avoid bias 
  For v=1 To no_plays
   If char=pChar(v) Or char=charPartner(pChar(v)) Or char=charManager(pChar(v)) Then satisfied=0 ;avoid already involved
  Next
  If char=0 Then satisfied=0 ;avoid none
 Until satisfied=1 Or its>1000
 Return char
End Function

;ASSESS TOTAL INJURY TIME (of character)
Function InjuryStatus(char)
 value=0
 For count=0 To 5
  If charInjured(char,count)>value Then value=charInjured(char,count)
 Next
 Return value
End Function

;ASSESS OLD INJURY STATUS
Function OldInjuryStatus(char)
 value=0
 For count=0 To 5
  If charOldInjured(char,count)>value Then value=charOldInjured(char,count)
 Next
 Return value
End Function

;IDENTIFY INJURY TYPE
Function IdentifyInjury(char)
 value=0
 For count=0 To 5
  If charInjured(char,count)>0 Then value=count
 Next
 Return value
End Function

;INJURED ON DATE?
Function InjuryDate(date)
 value=0
 ;If gamSchedule(date)=>0 And InjuryStatus(gamChar)>0 And gamAgreement(10)=0
  ;If date=>gamDate And date=<gamDate+(InjuryStatus(gamChar)-1) Then value=1
 ;EndIf
 If gamSchedule(date)=-1 Then value=1
 Return value
End Function

;COUNT CAREER EXPERIENCE
Function CountExperience(char,promotion)
 value=0
 If promotion>0
  value=charExperience(char,promotion)
 Else
  For count=1 To 9
   value=value+charExperience(char,count)
  Next
 EndIf
 Return value
End Function

;COUNT MATCHES
Function CountMatches(char,promotion)
 value=0
 If promotion>0
  value=charMatches(char,promotion)
 Else
  For count=1 To 9
   value=value+charMatches(char,count)
  Next
 EndIf
 Return value
End Function

;COUNT WINS
Function CountWins(char,promotion)
 value=0
 If promotion>0
  value=charWins(char,promotion)
 Else
  For count=1 To 9
   value=value+charWins(char,count)
  Next
 EndIf
 Return value
End Function

;CALCULATE WIN RATE
Function GetWinRate(char,promotion)
 If promotion>0
  value=Int(GetPercent#(charWins(char,promotion),charMatches(char,promotion)))
 Else
  value=Int(GetPercent#(CountWins(char,0),CountMatches(char,0)))
 EndIf
 Return value
End Function

;COUNT TITLES
Function CountTitles(char,promotion)
 value=0
 For title=1 To 4
  If promotion>0
   value=value+charTitles(char,promotion,title)
  Else
   For count=1 To 9
    value=value+charTitles(char,count,title)
   Next
  EndIf
 Next 
 Return value
End Function

;COUNT RELATIONSHIPS
Function CountRelationships(char,task) ;-1=enemies, 1=friends
 value=0
 For v=1 To no_chars
  If char<>v And charFed(v)=<8
   If task>0 And charRelationship(char,v)>0 Then value=value+1
   If task<0 And charRelationship(char,v)<0 Then value=value+1
  EndIf
 Next 
 Return value
End Function

;COUNT REAL RELATIONSHIPS
Function CountRealRelationships(char,task) ;-1=enemies, 1=friends
 value=0
 For v=1 To no_chars
  If char<>v And charFed(v)=<8
   If task>0 And charRealRelationship(char,v)>0 Then value=value+1
   If task<0 And charRealRelationship(char,v)<0 Then value=value+1
  EndIf
 Next 
 Return value
End Function

;COUNT FACES/HEELS IN FED
Function AllegianceRatio(cyc,style)
 value=0
 For count=1 To fedSize(cyc)
  char=fedRoster(cyc,count)
  If charHeel(char)=style Then value=value+1
 Next 
 Return value
End Function

;CALCULATE TOTAL WAGES
Function CountSalaries(promotion,task) ;0=in theory, 1=after clauses 
 value=0
 For count=1 To fedSize(promotion)
  char=fedRoster(promotion,count)
  If charArrived(char)>0 And char<>fedBooker(promotion)
   payment=charSalary(char)
   If game=1 And promotion=charFed(gamChar)
    If charVacant(char)>0 Or (charWorked(char)=0 And task=1) 
     If charClause(char,2)=0 Then payment=0
     If charClause(char,2)=1 Then payment=charSalary(char)/2
    EndIf
    If InjuryStatus(char)>0 
     If charClause(char,3)=0 Then payment=0
     If charClause(char,3)=1 Then payment=charSalary(char)/2
    EndIf
   EndIf
   value=value+payment
  EndIf
 Next
 Return value
End Function

;COUNT PRODUCTIONS
Function CountProductions(promotion)
 value=0
 For count=1 To 12
  If fedProduction(promotion,count)>0 Then value=value+1
 Next
 Return value
End Function

;CALCULATE PRODUCTION COSTS
Function ProductionCosts(promotion,task) ;0=in theory, 1=based on show
 value=0
 For count=1 To 12
  included=1
  If task=1 And gamSchedule(gamDate)=<0 And (count=1 Or count=>5) Then included=0
  If included=1 And fedProduction(promotion,count)>0 Then value=value+prodCost(count)
 Next
 Return value
End Function

;CALCULATE PROMOTION POTENTIAL
Function PromotionPotential(promotion)
 If game=1 And promotion=charFed(gamChar)
  value=75
  If fedProduction(promotion,4)>0 Then value=value+5
  If fedProduction(promotion,5)>0 Then value=value+5
  If fedProduction(promotion,6)>0 Then value=value+5
  If fedProduction(promotion,7)>0 Then value=value+5
  If fedProduction(promotion,8)>0 Then value=value+5
  ;If value=>95 Then value=100
 Else
  value=100
 EndIf
 Return value
End Function

;HOLDING ANY TITLES?
Function TitleHolder(char,title) ;0=most prestigious, 1=find world, 2=find inter, 3=find tags
 ;return most prestigious
 value=0 
 If title=0
  If fedChampTag(charFed(char),1)=char Or fedChampTag(charFed(char),2)=char Then value=3
  If fedChampInter(charFed(char))=char Then value=2
  If fedChampWorld(charFed(char))=char Then value=1
 EndIf
 ;find specific title
 If title=1 And fedChampWorld(charFed(char))=char Then value=1
 If title=2 And fedChampInter(charFed(char))=char Then value=1
 If title=3 
  If fedChampTag(charFed(char),1)=char Or fedChampTag(charFed(char),2)=char Then value=1
 EndIf
 Return value
End Function

;IMPORTANT CHARACTER?
Function ImportantChar(char)
 value=0
 If char=fedBooker(8) Or char=fedBooker(9) Then value=1
 If char=201 Or char=202 Then value=1
 Return value
End Function

;CONSTRUCT INTER-PROMOTIONAL MATCH
Function ConstructInterPromotional(match) ;1=inter champions, 2=tag champions, 3=world champions, 4=8-man war
 fed=0
 ResetCharacters()
 ;1. inter champions
 If match=1
  GetMatchRules(2) : AddGimmick(0)
  If no_refs>0 Then matchPromo=12
  pChar(1)=fedChampInter(charFed(gamChar))
  pChar(2)=fedChampInter(gamRivalFed(gamDate))
 EndIf
 ;2. tag champions
 If match=2
  GetMatchRules(12) : AddGimmick(0)
  If no_refs>0 Then matchPromo=53
  pChar(1)=fedChampTag(charFed(gamChar),1)
  pChar(2)=fedChampTag(charFed(gamChar),2)
  pChar(3)=fedChampTag(gamRivalFed(gamDate),1)
  pChar(4)=fedChampTag(gamRivalFed(gamDate),2)
 EndIf
 ;3. world champions
 If match=3
  GetMatchRules(2) : AddGimmick(0)
  If no_refs>0 Then matchPromo=12
  pChar(1)=fedChampWorld(charFed(gamChar))
  pChar(2)=fedChampWorld(gamRivalFed(gamDate))
 EndIf
 ;4. 8-man war
 If match=4
  GetMatchRules(20) : AddGimmick(0)
  If no_refs>0 Then matchPromo=53
  pChar(1)=fedChampWorld(charFed(gamChar))
  pChar(2)=fedChampInter(charFed(gamChar))
  pChar(3)=fedChampTag(charFed(gamChar),1)
  pChar(4)=fedChampTag(charFed(gamChar),2)
  pChar(5)=fedChampWorld(gamRivalFed(gamDate))
  pChar(6)=fedChampInter(gamRivalFed(gamDate))
  pChar(7)=fedChampTag(gamRivalFed(gamDate),1)
  pChar(8)=fedChampTag(gamRivalFed(gamDate),2)
 EndIf
 ;fill in gaps
 For cyc=1 To no_wrestlers
  If TitleHolder(pChar(cyc),0)=0 Then pChar(cyc)=0
  If charVacant(pChar(cyc))>0 Then pChar(cyc)=0
  If pChar(cyc)=0
   its=0
   Repeat
    If cyc=<no_wrestlers/2 Then newbie=fedRoster(charFed(gamChar),Rnd(1,fedSize(charFed(gamChar))))
    If cyc>no_wrestlers/2 Then newbie=fedRoster(gamRivalFed(gamDate),Rnd(1,fedSize(gamRivalFed(gamDate))))
    its=its+1
   Until (FindCharacter(newbie)=0 And charVacant(newbie)=0) Or its>1000
   pChar(cyc)=newbie
  EndIf
 Next
 ;add referee
 If no_refs>0 Then pChar(no_wrestlers+1)=AssignReferee()
 screen=50 : screenAgenda=0
End Function