;//////////////////////////////////////////////////////////////////////////////
;------------------------- WRESTLING MPIRE 2008: COURT CASES ------------------
;//////////////////////////////////////////////////////////////////////////////

;---------------------------------------------------------------
;///////////////////// 55. COURT CASE //////////////////////////
;---------------------------------------------------------------
Function CourtCase()
;load setting
Loader("Please Wait","Preparing Court Case")
;musicVol#=0.5
;ChannelVolume chTheme,musicVol#
fed=charFed(gamChar) : negSetting=20
negChar=gamCourtChar(gamDate)
negTopic=gamCourtCase(gamDate)
PrepareMeeting()
;reset situation
negTim=-100 : negStage=0 : negVerdict=0
For count=1 To 10
 promoReact(count)=0
Next
;find production
If CountProductions(fed)>0
 Repeat
  negSubTopic=Rnd(1,12)
 Until fedProduction(fed,negSubTopic)>0
Else
 negSubTopic=Rnd(1,12)
EndIf
;calculate fines
negPayOff=fedBank(charFed(gamChar))/10
negPayOff=Rnd(negPayOff/2,negPayOff*2)
If negPayOff<100 Then negPayOff=100
If negPayOff>10000 Then negPayOff=10000
negPayOff=RoundOff(negPayOff,100)
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
foc=1 : oldfoc=foc : charged=0
go=0 : gotim=-20 : keytim=0
While go=0

 Cls
 frames=WaitTimer(timer)
 For framer=1 To frames
	
	;counters
	keytim=keytim-1
	If keytim<1 Then keytim=0
	
	;PORTAL 
    gotim=gotim+1
    ;speed-up's
    If gotim>15 Then negTim=negTim+1 
    If negTim>50 And keytim=0
     If KeyDown(1) Or KeyDown(28) Or ButtonPressed() Or MouseDown(1) Then negTim=negTim+50 : keytim=5
    EndIf
    ;theme fading
    ;If gotim>0
     ;musicVol#=musicVol#-0.001
     ;ChannelVolume chTheme,musicVol#
    ;EndIf
    ManageMusic(-1)
    ;leave
    If negStage=3 And negTim>10200 Then go=1    
	
	;SPEAKING
	For cyc=1 To no_plays
	 If pChar(cyc)>0
	  ;change animation
	  If cyc=<4
	   If pSpeaking(cyc)=0 And pState(cyc)<>1 
	    pAnimSpeed#(cyc)=Rnd#(0.1,0.3)
	    Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,pAnim(cyc)),10
	    pState(cyc)=1
	   EndIf
	   If pSpeaking(cyc) And pState(cyc)<>2 
	    pAnimSpeed#(cyc)=Rnd#(0.35,0.7)
	    randy=Rnd(0,1)
	    If randy=0 Then pAnimSpeed#(cyc)=-pAnimSpeed#(cyc)
	    Animate p(cyc),1,pAnimSpeed#(cyc),pSeq(cyc,14),10
	    pState(cyc)=2
	   EndIf
	  EndIf 
	  ;facial expressions
	  FacialExpressions(cyc)
	  ManageEyes(cyc)
	  ;shadows
	  For limb=1 To 50
       If pShadow(cyc,limb)>0
        RotateEntity pShadow(cyc,limb),90,EntityYaw(pLimb(cyc,limb),1),0
        PositionEntity pShadow(cyc,limb),EntityX(pLimb(cyc,limb),1),pY#(cyc)+0.4,EntityZ(pLimb(cyc,limb),1)
       EndIf
      Next
     EndIf
	Next
	
	;CAMERA
	Camera()
	
 UpdateWorld

 ;OVERRIDE ANIMATION
 If pFoc(5)>0
  PointHead(5,pLimb(pFoc(5),1))
  LookAtPerson(5,pFoc(5)) 
 EndIf
 For cyc=1 To 5
  If pChar(cyc)>0
   If charEyeShape(pChar(cyc))=112 Then LookAtPerson(cyc,cyc)
  EndIf
 Next

 Next  
 RenderWorld 1

 ;DISPLAY
 ;reset expressions
 oldFoc=camFoc
 For cyc=1 To no_plays
  pSpeaking(cyc)=0 
 Next
 ;introduce widescreen
 If negTim>0 And negTim<10000 
  y#=60
  If negTim=<25 Then y#=PercentOf#(60,negTim*4) 
  If negTim=>9975 Then y#=PercentOf#(60,(10000-negTim)*4) 
  Color 0,0,0 : Rect rX#(0),rY#(0),rX#(800),rY#(y#),1
  y#=480
  If negTim=<25 Then y#=600-PercentOf#(120,negTim*4) 
  If negTim=>9975 Then y#=600-PercentOf#(120,(10000-negTim)*4) 
  Color 0,0,0 : Rect rX#(0),rY#(y#),rX#(800),rY#(600),1
 EndIf 
 ;reset subtitles
 lineA$="" : lineB$=""
 redLineA$="" : redLineB$=""
 greenLineA$="" : greenLineB$=""
 ;OPENING LINE
 If negStage=0 And negTim>25 And negTim<325
  Speak(5,0,3) : pFoc(5)=2
  lineA$="We're gathered to hear the case of "+charName$(pChar(2))+" versus"
  lineB$=charName$(pChar(1))+". So, "+charName$(pChar(2))+", what's the story?"
  If negTim>125 And promoReact(1)=0 Then ProduceSound(0,sCrowd(7),0,0.25) : promoReact(1)=1
 EndIf
 ;1. SEXUAL ABUSE
 If negTopic=1
  ;statements
  If negStage=0
   If negTim>325 Then camFoc=2
   If negTim>350 And negTim<650
    Speak(2,0,1) : g=charGender(pChar(1))
    lineA$=charName$(pChar(1))+"'s locker room is like a brothel!"
    lineB$="I'm constantly being subjected to sexual abuse..."
    If negTim>450 And promoReact(2)=0 Then ProduceSound(0,sCrowd(Rnd(4,5)),0,0.25) : promoReact(2)=1
   EndIf
   If negTim>650 Then camFoc=1
   If negTim>675 And negTim<975
    Speak(1,0,1) : g=charGender(pChar(2))
    lineA$=He$(g)+"'s lying to get attention! I've never heard of"
    lineB$="any such incidents occurring in my locker room..."
    If negTim>775 And promoReact(3)=0 Then ProduceSound(0,sCrowd(Rnd(4,5)),0,0.25) : promoReact(3)=1
   EndIf
   If negTim>975 Then camFoc=5
   If negTim>1000 Then negStage=1 : negTim=300
  EndIf
  ;positive verdict
  If negStage=2 And negVerdict=1
   If negTim>325 And negTim<625
    Speak(5,0,1) : pFoc(5)=2
    lineA$=charName$(pChar(2))+", your accusations are laughable!"
    lineB$="There's nothing sexual about "+fedName$(fed)+"..."
   EndIf
   If negTim>650 And negTim<950
    Speak(5,0,2) : pFoc(5)=2
    lineA$="Wrestling is bound to get boisterous from time to time!"
    lineB$="If you can't stand the heat, get out of the kitchen..."
   EndIf
   If negTim>950 Then camFoc=pFoc(5)
   If negTim>975 Then negStage=3 : negTim=9975
  EndIf
  ;negative verdict
  If negStage=2 And negVerdict=2
   If negTim>325 And negTim<625
    Speak(5,0,1) : pFoc(5)=1
    lineA$=charName$(pChar(1))+", you're a vile sexual predator"
    lineB$="and "+fedName$(fed)+" is your playground!"
   EndIf
   If negTim>650 And negTim<950
    Speak(5,0,1) : pFoc(5)=1
    If charged=0 Then fedBank(fed)=fedBank(fed)-negPayOff : PlaySound sCash : charged=1
    lineA$="I order you to pay $"+GetFigure$(negPayOff)+" to compensate for"
    lineB$="what "+charName$(pChar(2))+" has had to go through..."
   EndIf
   If negTim>950 Then camFoc=pFoc(5)
   If negTim>975 Then negStage=3 : negTim=9975
  EndIf
 EndIf
 ;2. UNFAIR DISMISSAL
 If negTopic=2
  ;statements
  If negStage=0
   If negTim>325 Then camFoc=2
   If negTim>350 And negTim<650
    Speak(2,0,1) : g=charGender(pChar(1))
    lineA$=charName$(pChar(1))+" promised that I had a job for life,"
    lineB$="but then threw me out of "+fedName$(fed)+"!"
    If negTim>450 And promoReact(2)=0 Then ProduceSound(0,sCrowd(Rnd(4,5)),0,0.25) : promoReact(2)=1
   EndIf
   If negTim>650 Then camFoc=1
   If negTim>675 And negTim<975
    Speak(1,0,1) : g=charGender(pChar(2))
    lineA$=charName$(pChar(2))+" AGREED to leave "+fedName$(fed)+"!"
    lineB$=His$(g)+" contract couldn't be terminated without consent..."
    If negTim>775 And promoReact(3)=0 Then ProduceSound(0,sCrowd(Rnd(4,5)),0,0.25) : promoReact(3)=1
   EndIf
   If negTim>975 Then camFoc=5
   If negTim>1000 Then negStage=1 : negTim=300
  EndIf
  ;positive verdict
  If negStage=2 And negVerdict=1
   If negTim>325 And negTim<625
    Speak(5,0,1) : pFoc(5)=2
    lineA$=charName$(pChar(2))+", it's clear to me that "+charName$(pChar(1))+" no"
    lineB$="longer owes you a living - and neither does this court!"
   EndIf
   If negTim>625 Then camFoc=pFoc(5)
   If negTim>650 Then negStage=3 : negTim=9975 
  EndIf
  ;negative verdict
  If negStage=2 And negVerdict=2
   If negTim>325 And negTim<625
    Speak(5,0,1) : pFoc(5)=1
    lineA$=charName$(pChar(1))+", you're a ruthless businessman!"
    lineB$="You treat your employees like circus animals..."
   EndIf
   If negTim>650 And negTim<950
    Speak(5,0,1) : pFoc(5)=1
    If charged=0 Then fedBank(fed)=fedBank(fed)-negPayOff : PlaySound sCash : charged=1
    lineA$="You could have ruined "+charName$(pChar(2))+"'s life!"
    lineB$="I order you to pay $"+GetFigure$(negPayOff)+" in lost earnings..."
   EndIf
   If negTim>950 Then camFoc=pFoc(5)
   If negTim>975 Then negStage=3 : negTim=9975 
  EndIf
 EndIf
 ;3. IMPRISONMENT
 If negTopic=3
  ;statements
  If negStage=0
   If negTim>325 Then camFoc=2
   If negTim>350 And negTim<650
    Speak(2,0,1) : g=charGender(pChar(1))
    lineA$=charName$(pChar(1))+" is a vicious slave driver and"
    lineB$="refuses to release me from "+Lower$(His$(g))+" monstrous regime!"
    If negTim>450 And promoReact(2)=0 Then ProduceSound(0,sCrowd(Rnd(4,5)),0,0.25) : promoReact(2)=1
   EndIf
   If negTim>650 Then camFoc=1
   If negTim>675 And negTim<975
    Speak(1,0,1) : g=charGender(pChar(2))
    lineA$="All I ask is that "+charName$(pChar(2))+" does the job"
    lineB$=Lower$(He$(g))+" was contracted to do! "+He$(g)+"'s just being lazy..."
    If negTim>775 And promoReact(3)=0 Then ProduceSound(0,sCrowd(Rnd(4,5)),0,0.25) : promoReact(3)=1
   EndIf
   If negTim>975 Then camFoc=5
   If negTim>1000 Then negStage=1 : negTim=300
  EndIf
  ;positive verdict
  If negStage=2 And negVerdict=1
   If negTim>325 And negTim<625
    Speak(5,0,1) : pFoc(5)=2
    lineA$=charName$(pChar(2))+", you're being paid very well for"
    lineB$="a job that you agreed to do. Now get back to work!"
   EndIf
   If negTim>625 Then camFoc=pFoc(5)
   If negTim>650 Then negStage=3 : negTim=9975
  EndIf
  ;negative verdict
  If negStage=2 And negVerdict=2
   If negTim>325 And negTim<625
    Speak(5,0,1) : pFoc(5)=1
    If charged=0 Then MoveChar(negChar,7) : PlaySound sPaper : charged=1
    lineA$=charName$(pChar(1))+", you're not fit to be an employer!"
    lineB$="You should release "+charName$(pChar(2))+" from your grip..."
   EndIf  
   If negTim>625 Then camFoc=pFoc(5)
   If negTim>650 Then negStage=3 : negTim=9975
  EndIf
 EndIf
 ;4. CAREER SABOTAGE
 If negTopic=4
  ;statements
  If negStage=0
   If negTim>325 Then camFoc=2
   If negTim>350 And negTim<650
    Speak(2,0,1) : g=charGender(pChar(1))
    lineA$="Since working for "+charName$(pChar(1))+", my lucrative"
    lineB$="career has gone downhill! I deserve compensation..."
    If negTim>450 And promoReact(2)=0 Then ProduceSound(0,sCrowd(Rnd(4,5)),0,0.25) : promoReact(2)=1
   EndIf
   If negTim>650 Then camFoc=1
   If negTim>675 And negTim<975
    Speak(1,0,1) : g=charGender(pChar(2))
    lineA$=charName$(pChar(2))+" is responsible for "+Lower$(His$(g))+" own career!"
    lineB$="I've given "+Him$(g)+" every opportunity to prove "+Him$(g)+"self..."
    If negTim>775 And promoReact(3)=0 Then ProduceSound(0,sCrowd(Rnd(4,5)),0,0.25) : promoReact(3)=1
   EndIf
   If negTim>975 Then camFoc=5
   If negTim>1000 Then negStage=1 : negTim=300
  EndIf
  ;positive verdict
  If negStage=2 And negVerdict=1
   If negTim>325 And negTim<625
    Speak(5,0,1) : pFoc(5)=2
    lineA$=charName$(pChar(2))+", you got yourself into this situation!"
    lineB$="If you don't like your career, I suggest you work harder..."
   EndIf
   If negTim>625 Then camFoc=pFoc(5)
   If negTim>650 Then negStage=3 : negTim=9975
  EndIf
  ;negative verdict
  If negStage=2 And negVerdict=2
   If negTim>325 And negTim<625
    Speak(5,0,1) : pFoc(5)=1
    If charged=0 Then fedBank(fed)=fedBank(fed)-negPayOff : PlaySound sCash : charged=1
    lineA$=charName$(pChar(1))+", you ruined "+charName$(pChar(2))+"'s career!"
    lineB$="I order you to pay $"+GetFigure$(negPayOff)+" in compensation..."
   EndIf
   If negTim>625 Then camFoc=pFoc(5)
   If negTim>650 Then negStage=3 : negTim=9975
  EndIf
 EndIf
 ;5. BODILY HARM
 If negTopic=5
  ;statements
  If negStage=0
   If negTim>325 Then camFoc=2
   If negTim>350 And negTim<650
    Speak(2,0,1) : g=charGender(pChar(1))
    lineA$=charName$(pChar(1))+"'s violent brand of wrestling"
    lineB$="left me fighting for my life in hospital!"
    If negTim>450 And promoReact(2)=0 Then ProduceSound(0,sCrowd(Rnd(4,5)),0,0.25) : promoReact(2)=1
   EndIf
   If negTim>650 Then camFoc=1
   If negTim>675 And negTim<975
    Speak(1,0,1) : g=charGender(pChar(2))
    lineA$="Everybody knows the risks involved in wrestling!"
    lineB$=charName$(pChar(2))+" should have been more careful..."
    If negTim>775 And promoReact(3)=0 Then ProduceSound(0,sCrowd(Rnd(4,5)),0,0.25) : promoReact(3)=1
   EndIf
   If negTim>975 Then camFoc=5
   If negTim>1000 Then negStage=1 : negTim=300
  EndIf
  ;positive verdict
  If negStage=2 And negVerdict=1
   If negTim>325 And negTim<625
    Speak(5,0,1) : pFoc(5)=2
    lineA$=charName$(pChar(2))+", you're responsible for your own body!"
    lineB$="If you can't hack it, I suggest you find a safer job..."
   EndIf
   If negTim>625 Then camFoc=pFoc(5)
   If negTim>650 Then negStage=3 : negTim=9975
  EndIf
  ;negative verdict
  If negStage=2 And negVerdict=2
   If negTim>325 And negTim<625
    Speak(5,0,1) : pFoc(5)=1
    lineA$=charName$(pChar(1))+", you treat your wrestlers like crash"
    lineB$="test dummies! "+charName$(pChar(2))+" has clearly suffered..."
   EndIf
   If negTim>650 And negTim<950
    Speak(5,0,1) : pFoc(5)=1
    If charged=0 Then fedBank(fed)=fedBank(fed)-negPayOff : PlaySound sCash : charged=1
    lineA$="I order you to pay $"+GetFigure$(negPayOff)+" in medical bills,"
    lineB$="and I don't want to see this happen again!"
   EndIf
   If negTim>950 Then camFoc=pFoc(5)
   If negTim>975 Then negStage=3 : negTim=9975
  EndIf
 EndIf
 ;6. DRUG ABUSE
 If negTopic=6
  ;statements
  If negStage=0
   If negTim>325 Then camFoc=2
   If negTim>350 And negTim<650
    Speak(2,0,1) : g=charGender(pChar(1))
    lineA$=charName$(pChar(1))+"'s locker room is like a pharmacy!"
    lineB$=He$(g)+" forced me to take performance-enhancing drugs..."
    If negTim>450 And promoReact(2)=0 Then ProduceSound(0,sCrowd(Rnd(4,5)),0,0.25) : promoReact(2)=1
   EndIf
   If negTim>650 Then camFoc=1
   If negTim>675 And negTim<975
    Speak(1,0,1) : g=charGender(pChar(2))
    lineA$=charName$(pChar(2))+"'s life is none of my business!"
    lineB$="I would never force anybody to take anything..."
    If negTim>775 And promoReact(3)=0 Then ProduceSound(0,sCrowd(Rnd(4,5)),0,0.25) : promoReact(3)=1
   EndIf
   If negTim>975 Then camFoc=5
   If negTim>1000 Then negStage=1 : negTim=300
  EndIf
  ;positive verdict
  If negStage=2 And negVerdict=1
   If negTim>325 And negTim<625
    Speak(5,0,1) : pFoc(5)=2
    lineA$=charName$(pChar(2))+", you may be a drug user - but"
    lineB$=charName$(pChar(1))+" certainly isn't a drug dealer!"
   EndIf
   If negTim>625 Then camFoc=pFoc(5)
   If negTim>650 Then negStage=3 : negTim=9975
  EndIf
  ;negative verdict
  If negStage=2 And negVerdict=2
   If negTim>325 And negTim<625
    Speak(5,0,1) : pFoc(5)=1
    If charged=0 Then gamSchedule(gamDate+1)=0 : PlaySound sPaper : charged=1
    lineA$=charName$(pChar(1))+", you're a glorified drug dealer!"
    lineB$="I'm taking your show off the air until it's clean..."
   EndIf
   If negTim>625 Then camFoc=pFoc(5)
   If negTim>650 Then negStage=3 : negTim=9975
  EndIf
 EndIf
 ;7. BUREAUCRACY
 If negTopic=7
  ;statements
  If negStage=0
   If negTim>325 Then camFoc=2
   If negTim>350 And negTim<650
    Speak(2,0,1) : g=charGender(pChar(1))
    lineA$=charName$(pChar(1))+" has been withholding money"
    lineB$="from me due to a loophole in my contract!"
    If negTim>450 And promoReact(2)=0 Then ProduceSound(0,sCrowd(Rnd(4,5)),0,0.25) : promoReact(2)=1
   EndIf
   If negTim>650 Then camFoc=1
   If negTim>675 And negTim<975
    Speak(1,0,1) : g=charGender(pChar(2))
    lineA$=fedName$(fed)+" reserves the right to"
    lineB$="pay wrestlers according to their status!"
    If negTim>775 And promoReact(3)=0 Then ProduceSound(0,sCrowd(Rnd(4,5)),0,0.25) : promoReact(3)=1
   EndIf
   If negTim>975 Then camFoc=5
   If negTim>1000 Then negStage=1 : negTim=300
  EndIf
  ;positive verdict
  If negStage=2 And negVerdict=1
   If negTim>325 And negTim<625
    Speak(5,0,1) : pFoc(5)=2
    lineA$=charName$(pChar(2))+", you're only entitled to a"
    lineB$="FULL salary when you work a FULL schedule!"
   EndIf
   If negTim>625 Then camFoc=pFoc(5)
   If negTim>650 Then negStage=3 : negTim=9975
  EndIf
  ;negative verdict
  If negStage=2 And negVerdict=2
   If negTim>325 And negTim<625
    Speak(5,0,1) : pFoc(5)=1 
    lineA$=charName$(pChar(1))+", you're a devious businessman!"
    lineB$="You've been taking advantage of your employees..."
   EndIf
   If negTim>650 And negTim<950
    Speak(5,0,1) : pFoc(5)=1 
    If charged=0 
     PlaySound sPaper : charged=1
     For count=1 To fedSize(fed)
      char=fedRoster(fed,count)
      charClause(char,2)=2 : charClause(char,3)=2
      If char<>gamChar And char<>negChar Then charHappiness(char)=charHappiness(char)+PursueValue(charHappiness(char),100,0)
     Next
    EndIf
    lineA$="You may fool them, but you don't fool this court!"
    lineB$="From now on, you'll pay ALL wrestlers unconditionally..."
   EndIf
   If negTim>950 Then camFoc=pFoc(5)
   If negTim>975 Then negStage=3 : negTim=9975
  EndIf
 EndIf
 ;8. COPYCAT INJURIES
 If negTopic=8
  ;statements
  If negStage=0
   If negTim>325 Then camFoc=2
   If negTim>350 And negTim<650
    Speak(2,0,1) : g=charGender(pChar(1))
    lineA$="My emergency room is filled with kids that have been"
    lineB$="imitating "+charName$(pChar(1))+"'s violent wrestling show!"
    If negTim>450 And promoReact(2)=0 Then ProduceSound(0,sCrowd(Rnd(4,5)),0,0.25) : promoReact(2)=1
   EndIf
   If negTim>650 Then camFoc=1
   If negTim>675 And negTim<975
    Speak(1,0,1) : g=charGender(pChar(2))
    lineA$="It's not my show's responsibility to raise children!"
    lineB$="No viewer is encouraged to imitate what they see..."
    If negTim>775 And promoReact(3)=0 Then ProduceSound(0,sCrowd(Rnd(4,5)),0,0.25) : promoReact(3)=1
   EndIf
   If negTim>975 Then camFoc=5
   If negTim>1000 Then negStage=1 : negTim=300
  EndIf
  ;positive verdict
  If negStage=2 And negVerdict=1
   If negTim>325 And negTim<625
    Speak(5,0,1) : pFoc(5)=2
    lineA$=charName$(pChar(2))+", I suggest you save your vitriol"
    lineB$="for the PARENTS of these moronic children!"
   EndIf
   If negTim>625 Then camFoc=pFoc(5)
   If negTim>650 Then negStage=3 : negTim=9975
  EndIf
  ;negative verdict
  If negStage=2 And negVerdict=2
   If negTim>325 And negTim<625
    Speak(5,0,1) : pFoc(5)=1 
    If charged=0 Then gamSchedule(gamDate+1)=0 : PlaySound sPaper : charged=1
    lineA$=fedName$(fed)+" is a menace to society!"
    lineB$="I want it taken off the air until further notice..."
   EndIf
   If negTim>625 Then camFoc=pFoc(5)
   If negTim>650 Then negStage=3 : negTim=9975
  EndIf
 EndIf
 ;9. PRODUCTION PLAGUERISM
 If negTopic=9
  ;statements
  If negStage=0
   If negTim>325 Then camFoc=2
   If negTim>350 And negTim<650
    Speak(2,0,1) : g=charGender(pChar(1))
    lineA$="My company pioneered the use of '"+prodName$(negSubTopic)+"' in"
    lineB$="wrestling, and now "+fedName$(fed)+" are copying!"
    If negTim>450 And promoReact(2)=0 Then ProduceSound(0,sCrowd(Rnd(4,5)),0,0.25) : promoReact(2)=1
   EndIf
   If negTim>650 Then camFoc=1
   If negTim>675 And negTim<975
    Speak(1,0,1) : g=charGender(pChar(2))
    lineA$="'"+prodName$(negSubTopic)+"' isn't exclusive to "+fedName$(charFed(pChar(2)))+"!"
    lineB$="Any company should be free to develop that production..."
    If negTim>775 And promoReact(3)=0 Then ProduceSound(0,sCrowd(Rnd(4,5)),0,0.25) : promoReact(3)=1
   EndIf
   If negTim>975 Then camFoc=5
   If negTim>1000 Then negStage=1 : negTim=300
  EndIf
  ;positive verdict
  If negStage=2 And negVerdict=1
   If negTim>325 And negTim<625
    Speak(5,0,1) : pFoc(5)=2
    lineA$=fedName$(charFed(pChar(2)))+" doesn't own '"+prodName$(negSubTopic)+"'!"
    lineB$="It was invented for the whole industry to share..."
   EndIf
   If negTim>625 Then camFoc=pFoc(5)
   If negTim>650 Then negStage=3 : negTim=9975
  EndIf
  ;negative verdict
  If negStage=2 And negVerdict=2
   If negTim>325 And negTim<625
    Speak(5,0,1) : pFoc(5)=1 
    If charged=0 
     PlaySound sProduce : charged=1
     fedProduction(fed,negSubTopic)=0
     If gamBuild=negSubTopic Then gamBuild=0
     gamAgreement(negSubTopic)=4
    EndIf
    lineA$=charName$(pChar(1))+" stole the idea of '"+prodName$(negSubTopic)+"'!"
    lineB$="I demand that you stop using it immediately..."
   EndIf
   If negTim>625 Then camFoc=pFoc(5)
   If negTim>650 Then negStage=3 : negTim=9975
  EndIf
 EndIf

 ;INTERRUPT
 If negStage=1
  If negTim>325 And negTim<625
   Speak(5,0,1) : pFoc(5)=0
   lineA$="OK, you can both stop bickering! I'll settle this."
   lineB$="Just give me a minute to think over the facts..."
  EndIf
  If negTim>650 And negTim<950
   Speak(5,0,2) : pFoc(5)=0
   negVerdict=Rnd(1,2)
   If fedProduction(fed,1)>0 Then negVerdict=Rnd(0,2)
   If negVerdict<1 Then negVerdict=1
   lineA$="After hearing each of your statements and reviewing"
   lineB$="the evidence, this court rules in favour of..."
   If negTim>800 And promoReact(4)=0 Then ProduceSound(0,sCrowd(7),0,0.25) : promoReact(4)=1
  EndIf
  If negTim>1025 Then camFoc=negVerdict
  If negTim>1050 And negTim<1200 
   If negVerdict=1 Then pEyes(1)=3 : pEyes(2)=1 : pEyes(3)=3 : pEyes(4)=1
   If negVerdict=2 Then pEyes(1)=1 : pEyes(2)=3 : pEyes(3)=1 : pEyes(4)=3
   If promoReact(5)=0 Then ProduceSound(0,sCrowd(1+negVerdict),0,0.25) : promoReact(5)=1
   Outline("..."+charName$(pChar(negVerdict))+"!",rX#(400),rY#(535),30,30,30,250,Rnd(150,220),100)
  EndIf
  If negTim>1200 Then negStage=2 : negTim=300 : camFoc=5
 EndIf
 ;---------- DISPLAY SUBTITLES ----------
 DisplaySubtitles()
 ;cursor
 DrawImage gCursor,MouseX(),MouseY()
 ;mask shaky start
 If gotim=<0 Then Loader("Please Wait","Preparing Court Case") 

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;restore sound
;ChannelVolume chTheme,1.0
chThemeVol#=PercentOf#(1.0,optMusicVolume)	
ChannelVolume chTheme,chThemeVol#
StopChannel chCrowd
FreeTimer timer
;free entities
FreeEntity world
FreeEntity cam 
FreeEntity camPivot
FreeEntity dummy
FreeEntity light(1)
FreeEntity lightPivot
;remove characters
For cyc=1 To no_plays
 If pChar(cyc)>0
  FreeEntity p(cyc)
  For limb=1 To 50
   If pShadow(cyc,limb)>0
    FreeEntity pShadow(cyc,limb)
   EndIf
  Next
 EndIf
Next
;effects of win
If negVerdict=1
 fedPopularity(fed)=fedPopularity(fed)+1
 fedReputation(fed)=fedReputation(fed)+1
 charPopularity(gamChar)=charPopularity(gamChar)+1
 charHappiness(gamChar)=charHappiness(gamChar)+1
 charPopularity(negChar)=charPopularity(negChar)-1
 charAttitude(negChar)=charAttitude(negChar)+PursueValue(charAttitude(negChar),30,0)
 charHappiness(negChar)=charHappiness(negChar)+PursueValue(charHappiness(negChar),30,0)
 gamNightScore(gamDate)=1 
EndIf
;effects of loss
If negVerdict=2
 fedPopularity(fed)=fedPopularity(fed)-1
 fedReputation(fed)=fedReputation(fed)+PursueValue(fedReputation(fed),30,0)
 charPopularity(gamChar)=charPopularity(gamChar)-1
 charHappiness(gamChar)=charHappiness(gamChar)+PursueValue(charHappiness(gamChar),30,0) 
 charPopularity(negChar)=charPopularity(negChar)+1 
 charHappiness(negChar)=charHappiness(negChar)+PursueValue(charHappiness(negChar),100,0)
 gamNightScore(gamDate)=-1
EndIf
;become enemies
ChangeRelationship(negChar,gamChar,-1)
ChangeRealRelationship(negChar,gamChar,-1)
;proceed
CheckFedLimits(fed)
screen=24
End Function