;//////////////////////////////////////////////////////////////////////////////
;------------------------ WRESTLING MPIRE 2008: NEGOTIATIONS ------------------
;//////////////////////////////////////////////////////////////////////////////

;------------------------------------------------------------------------------
;/////////////////////////// RISK BUYING OFFERS ///////////////////////////////
;------------------------------------------------------------------------------
Function RiskOffers()
 ;scan promotions
 For fed=1 To 6
  negTopic=0 : negChar=0
  randy=Rnd(0,fedSize(fed))
  If randy=<1 And fed<>charFed(gamChar) And fedSize(charFed(gamChar))>5 And fedSize(fed)<36 And fedBank(fed)>0 And fedBooker(fed)>0
   its=0
   Repeat
    satisfied=1
    negChar=fedRoster(charFed(gamChar),Rnd(1,fedSize(charFed(gamChar))))
    randy=Rnd(0,2)
    If randy>0 And charPopularity(negChar)<PercentOf#(fedPopularity(fed),75) Then satisfied=0
    randy=Rnd(0,2)
    If randy>0 And charContract(negChar)=>20 Then satisfied=0
    randy=Rnd(0,2)
    If randy>0 And GetValue(negChar)<fedPopularity(fed)/2 Then satisfied=0
    If fedBank(fed)<(charContract(negChar)*charWorth(negChar))*2 Then satisfied=0
    If negChar=gamChar Or charContract(negChar)=<1 Or InjuryStatus(negChar)>0 Then satisfied=0
    its=its+1
    If its>1000 Then satisfied=1 : negChar=0
   Until satisfied=1
   negTopic=3
  EndIf
  ;go into negotiations
  If negTopic=3 And negChar>0 And screen<>25 And screen<>50 Then screen=56 : SellCharacter()
 Next
End Function

;------------------------------------------------------------------------------
;////////////////////// 52. CONTRACT NEGOTIATIONS /////////////////////////////
;------------------------------------------------------------------------------
Function Negotiations()
;get setting
Loader("Please Wait","Meeting "+charName$(negChar))
;ChannelVolume chTheme,0.5
fed=charFed(gamChar)
negTopic=0
If charFed(negChar)<>fed And charContract(negChar)=0 Then negTopic=1
If charFed(negChar)<>fed And charContract(negChar)>0 Then negTopic=2
negSetting=1 : camFoc=2
PrepareMeeting()
;initial contract
negChances=Rnd(1,charAttitude(negChar)/9)
If negTopic=0 Then negChances=negChances*2
negWorth=CalculateWorth(negChar,fed)
If negTopic=2 And negWorth<charWorth(negChar) Then negWorth=charWorth(negChar)
negWorth=PercentOf#(negWorth,Rnd(75,125))
negBuyOut=0
If negTopic=2 And negChar<>fedBooker(charFed(negChar))
 negBuyOut=charSalary(negChar)*charContract(negChar)
 ;If negBuyOut<charWorth(negChar)*charContract(negChar) Then negBuyOut=charWorth(negChar)*charContract(negChar)
EndIf
negPayOff=negBuyOut : negPayOffPrefer=Rnd(-10,4)
If negPayOffPrefer<0 Then negPayOffPrefer=0
If negTopic=1 Then negSalary=0 Else negSalary=charSalary(negChar)
negContract=20
For count=1 To 3
 If negTopic=1 Then negClause(count)=0 Else negClause(count)=charClause(negChar,count)
 negClausePrefer(count)=Rnd(0,2)
 If count=1 Then negClausePrefer(count)=Rnd(-4,2)
 If negClausePrefer(count)<0 Then negClausePrefer(count)=0
Next
;reset progress
negTim=0 : negStage=0
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
foc=1 : oldfoc=foc : charged=0
go=0 : gotim=-20 : keytim=20
While go=0

 Cls
 frames=WaitTimer(timer)
 For framer=1 To frames
	
	;timers
	keytim=keytim-1
	If keytim<1 Then keytim=0  
	
	;PORTAL
    gotim=gotim+1
    If gotim>25 Then negTim=negTim+1 
    ;speed-ups
    If gotim>0 And negStage<>1 And negStage<>3 And keytim=0
	 If KeyDown(1) Or KeyDown(28) Or ButtonPressed() Or MouseDown(1) Then negTim=negTim+50 : keytim=5 ;: PlaySound sMenuBrowse
	EndIf
	
	;CONFIGURATION
	If gotim>20 And keytim=0 And (negStage=1 Or negStage=3)
	 ;highlight option
	 If KeyDown(200) Or JoyYDir()=-1 Then foc=foc-1 : keytim=5
	 If KeyDown(208) Or JoyYDir()=1 Then foc=foc+1 : keytim=5
	 If foc>7 Then foc=0
	 If foc<0 Then foc=7 
	 ;adjust contract
	 If negStage=1 And foc=>1 And foc=<6
	  ;browse left
	  If KeyDown(203) Or JoyXDir()=-1 Or MouseDown(2)
	   range=FigureRange(negPayOff)
	   If range<100 Then range=100
	   If foc=1 Then negPayOff=negPayOff-range : PlaySound sMenuBrowse : keytim=2
	   range=FigureRange(negSalary)
	   If foc=2 Then negSalary=negSalary-range : PlaySound sMenuBrowse : keytim=2
	   If foc=3 Then negContract=negContract-1 : PlaySound sMenuBrowse : keytim=3
	   For count=1 To 3
	    If foc=3+count Then negClause(count)=negClause(count)-1 : PlaySound sMenuBrowse : keytim=5
	   Next
	  EndIf
	  ;browse right
	  If KeyDown(205) Or JoyXDir()=1 Or MouseDown(1)
	   range=FigureRange(negPayOff)
	   If range<100 Then range=100
	   If foc=1 Then negPayOff=negPayOff+range : PlaySound sMenuBrowse : keytim=2
	   range=FigureRange(negSalary)
	   If foc=2 Then negSalary=negSalary+range : PlaySound sMenuBrowse : keytim=2
	   If foc=3 Then negContract=negContract+1 : PlaySound sMenuBrowse : keytim=3
	   For count=1 To 3
	    If foc=3+count Then negClause(count)=negClause(count)+1 : PlaySound sMenuBrowse : keytim=5
	   Next
	  EndIf
	  ;reset
	  If KeyDown(14)
	   PlaySound sMenuBrowse : keytim=10
	   If foc=1 Then negPayOff=0
	   If foc=2 Then negSalary=0
	   If foc=3 Then negContract=0
	   For count=1 To 3
	    If foc=3+count Then negClause(count)=0
	   Next
	  EndIf
	 EndIf
	 ;submit proposal
	 If foc=7 Or KeyDown(28)
	  If KeyDown(28) Or ButtonPressed() Or MouseDown(1) 
	   PlaySound sMenuGo : keytim=15
	   If negStage=1 
	    negStage=2 : negTim=0 : foc=7
	    negVerdict=GetContractVerdict()
	   EndIf
	   If negStage=3
	    negStage=4 : negTim=0
	    If charFed(negChar)<>fed Then charVacant(negChar)=Rnd(-2,4) : charOldVacant(negChar)=1
	    If charVacant(negChar)<0 Then charVacant(negChar)=0
	   EndIf
	  EndIf
	 EndIf
	 ;cancel
	 If foc=0 Or KeyDown(1)
	  If KeyDown(1) Or KeyDown(28) Or ButtonPressed() Or MouseDown(1)
	   PlaySound sMenuBack : keytim=15
	   negStage=5 : negTim=0
	  EndIf
	 EndIf
	EndIf
	;check limits
	If negPayOff<0 Then negPayOff=0
	If fedBank(fed)>0
	 If negPayOff>fedBank(fed) Then negPayOff=fedBank(fed) 
	Else
	 negPayOff=0 
	EndIf
	If negSalary<0 Then negSalary=0
	If negSalary>25000 Then negSalary=25000  
	If negContract<0 Then negContract=0
	If negContract>48 Then negContract=48
	For count=1 To 3
	 If negClause(count)<0 Then negClause(count)=0
	 If negClause(count)>2 Then negClause(count)=2 
	Next  
	
	;PLAYER CYCLE
	For cyc=1 To no_plays
	 If pChar(cyc)>0
	  ;facial expressions
	  RandomizeAnimation(cyc)
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
	;music
	ManageMusic(-1)
	
 UpdateWorld
 Next  

 ;ANIMATION OVERRIDE
 pFoc(1)=2 :  pFoc(2)=1
 If pChar(3)>0 
  If pSpeaking(3)>0 Or (negStage=0 And negTopic=2 And negChar<>fedBooker(charFed(negChar)) And negTim=>350) Then pFoc(1)=3 : pFoc(2)=3
  If negStage=1 Or negStage=3 Then pFoc(3)=1 Else pFoc(3)=2
 EndIf
 For cyc=1 To 3
 If pChar(cyc)>0
  If pFoc(cyc)>0
   If cyc=3 Or pFoc(cyc)=3 Then PointHead(cyc,pLimb(pFoc(cyc),1))
   LookAtPerson(cyc,pFoc(cyc))
  Else
   RotateEntity pLimb(cyc,45),0,0,0
   RotateEntity pLimb(cyc,46),0,0,0
  EndIf
  If charEyeShape(pChar(cyc))=112 Then LookAtPerson(cyc,cyc)
 EndIf
 Next
 
 RenderWorld 1

 ;DISPLAY
 DrawImage gLogo(2),rX#(400),rY#(65)
 ;reset speech
 For cyc=1 To no_plays
  pSpeaking(cyc)=0
 Next
 ;reset subtitles
 lineA$="" : lineB$=""
 redLineA$="" : redLineB$=""
 greenLineA$="" : greenLineB$=""
 ;get context
 g=charGender(negChar)
 ;------------- INTRODUCTIONS -----------------
 ;renew with existing promotion
 If negStage=0 And negTopic=0
  If negTim>25 And negTim<325 And charContract(negChar)=<1
   Speak(2,0,3)
   lineA$="Hi, "+charName$(negChar)+". Your contract with us has"
   lineB$="expired, so we need to talk about your future..."
  EndIf
  If negTim>25 And negTim<325 And charContract(negChar)>1
   Speak(2,0,3)
   lineA$="Hi, "+charName$(negChar)+". I know you've got "+charContract(negChar)+" weeks left on"
   lineB$="your contract, but we'd like to talk about a new one..."
  EndIf
  If negTim>350 And negTim<650 And negWorth<charWorth(negChar)
   Speak(2,0,1)
   lineA$="It seems your value has deteriorated since we last"
   lineB$="spoke, so I'd like to make the following adjustments:"
  EndIf
  If negTim>350 And negTim<650 And negWorth=>charWorth(negChar)
   Speak(2,0,3)
   lineA$="It seems you've made progress since we last spoke,"
   lineB$="and I'd like to reflect that with these adjustments:"
  EndIf
  If negTim>675 Then negStage=1 : negTim=0 : keytim=20
 EndIf
 ;sign free agent
 If negStage=0 And negTopic=1
  If negTim>25 And negTim<325 And charExperience(negChar,fed)=0
   Speak(2,0,3)
   lineA$="Nice to meet you, "+charName$(negChar)+". I invited you here"
   lineB$="today to discuss working for "+fedName$(fed)+"?"
  EndIf
  If negTim>25 And negTim<325 And charExperience(negChar,fed)>0
   Speak(2,0,3)
   lineA$="Nice to see you again, "+charName$(negChar)+". We'd like to"
   lineB$="discuss bringing you back to "+fedName$(fed)+"?"
  EndIf
  If negTim>350 And negTim<650 And charFed(negChar)=<6
   Speak(2,0,3)
   lineA$="I notice your contract with "+fedName$(charFed(negChar))
   lineB$="has expired, so we'd like to step in with this deal:"
  EndIf
  If negTim>350 And negTim<650 And charFed(negChar)=>7
   Speak(2,0,3)
   lineA$="I notice you're a free agent at the moment,"
   lineB$="so we'd like to step in with this deal:"
  EndIf
  If negTim>675 Then negStage=1 : negTim=0 : keytim=20
 EndIf
 ;buy contracted wrestler
 If negStage=0 And negTopic=2 And negChar<>fedBooker(charFed(negChar))
  If negTim>25 And negTim<325 And charExperience(negChar,fed)=0
   Speak(2,0,3)
   lineA$="Nice to meet you, "+charName$(negChar)+". I invited you here"
   lineB$="today to discuss working for "+fedName$(fed)+"?"
  EndIf
  If negTim>25 And negTim<325 And charExperience(negChar,fed)>0
   Speak(2,0,3)
   lineA$="Nice to see you again, "+charName$(negChar)+". We'd like to"
   lineB$="discuss bringing you back to "+fedName$(fed)+"?"
  EndIf
  If negTim>350 And negTim<650
   Speak(3,0,1)
   lineA$=charName$(negChar)+" is tied to "+fedName$(charFed(negChar))
   lineB$="with a "+charContract(negChar)+" week contract worth $"+GetFigure$(charSalary(negChar))+" per week!"
  EndIf
  If negTim>675 And negTim<975
   Speak(3,0,1)
   lineA$="It'll cost at least $"+GetFigure$(negBuyOut)+" to buy "+Him$(g)
   lineB$="out of that contract, "+charName$(gamChar)+"..."
  EndIf
  If negTim>1000 And negTim<1300
   Speak(2,0,3)
   lineA$="OK, "+charName$(pChar(3))+" - I understand the situation!"
   lineB$="With that in mind, we're prepared to offer this deal:"
  EndIf
  If negTim>1325 Then negStage=1 : negTim=0 : keytim=20
 EndIf
 ;buy out booker
 If negStage=0 And negTopic=2 And negChar=fedBooker(charFed(negChar))
  If negTim>25 And negTim<325 And charExperience(negChar,fed)=0
   Speak(2,0,3)
   lineA$="Nice to meet you, "+charName$(negChar)+". I invited you here"
   lineB$="today to discuss working for "+fedName$(fed)+"?"
  EndIf
  If negTim>25 And negTim<325 And charExperience(negChar,fed)>0
   Speak(2,0,3)
   lineA$="Nice to see you again, "+charName$(negChar)+". We'd like to"
   lineB$="discuss bringing you back to "+fedName$(fed)+"?"
  EndIf
  If negTim>350 And negTim<650
   Speak(2,0,3)
   lineA$="I understand you're very busy at the moment,"
   lineB$="but we'd like you to consider this deal:"
  EndIf
  If negTim>675 Then negStage=1 : negTim=0 : keytim=20
 EndIf
 ;don't listen to offer
 If negStage=1 And gamNegotiated(negChar,1)>0
  If charFed(negChar)=<8 Or negChar=fedBooker(charFed(negChar)) Then negVerdict=1 : negStage=2 : negTim=0 : keytim=20
 EndIf
 ;--------------- CONTRACT SETUP --------------------
 If negStage=1 Or negStage=3
  ;facial expression
  mood=2
  If foc=0 Then mood=1
  If foc=7 Then mood=3
  Speak(2,0,mood)
  ;options
  y=475
  Color 0,0,0 : Rect rX#(0),rY#(y)-30,rX#(800),rY#(300),1
  DrawOption(0,rX#(104),rY#(515),"<<< WITHDRAW <<<","")
  namer$="$"+GetFigure$(negPayOff)
  If negBuyOut>0 And negPayOff=>negBuyOut Then namer$="$"+GetFigure$(negBuyOut)+" + $"+GetFigure$(negPayOff-negBuyOut)
  DrawOption(1,rX#(400),rY#(y-1),"Pay-Off",namer$)
  DrawOption(2,rX#(400),rY#(y+31),"Salary","$"+GetFigure$(negSalary)+" per week")
  DrawOption(3,rX#(400),rY#(y+63),"Contract",negContract+" weeks") 
  If negStage=3 Then namer$=">>> CONFIRM >>>" Else namer$=">>> PROPOSE >>>"
  DrawOption(7,rX#(696),rY#(515),namer$,"")
  ;smallprint hotspots
  y=y+85
  If MouseX()=>rX#(400)-90 And MouseX()=<rX#(400)+110
   For count=1 To 3
    If MouseY()=>rY#(y+((count-1)*12))-3 And MouseY()=<rY#(y+((count-1)*12))+6 Then foc=count+3
   Next
  EndIf
  ;smallprint options
  SetFont font(1)
  r=150 : g=80 : b=80
  If foc=4 Then DrawImage gSmallPrint,rX#(400)-111,rY#(y)+5 : r=230 : g=0 : b=0
  OutlineStraight("Creative Control:",rX#(400)-101,rY#(y+2),0,0,0,r,g,b)
  r=150 : g=80 : b=80
  If foc=5 Then DrawImage gSmallPrint,rX#(400)-133,rY#(y+14)+5 : r=230 : g=0 : b=0
  OutlineStraight("Performance Clause:",rX#(400)-123,rY#(y+14),0,0,0,r,g,b)
  r=150 : g=80 : b=80
  If foc=6 Then DrawImage gSmallPrint,rX#(400)-96,rY#(y+26)+5 : r=230 : g=0 : b=0
  OutlineStraight("Health Policy:",rX#(400)-85,rY#(y+26),0,0,0,r,g,b)
  SetFont fontStat(0)
  For count=1 To 3
   namer$=textClause$(count,negClause(count))
   GetStatColour(negClause(count),1)
   r=ColorRed() : g=ColorGreen() : b=ColorBlue()
   If foc<>3+count Then r=r-(r/4) : g=g-(g/4) : b=b-(b/4)
   OutlineStraight(namer$,rX#(400)+4,rY#(y+((count-1)*12)),0,0,0,r,g,b)
  Next
 EndIf 
 ;stat reminder
 DrawProfile(negChar,-1,-1,0)
 ;------------------ VERDICTS -----------------------------
 If negStage=2 And negTim>10 Then camFoc=1
 ;acceptances
 If negStage=2 And negVerdict=0
  If negTim>25 And negTim<325
   Speak(1,0,3)
   greenLineA$="OK, I'm happy to sign up for that contract!"
   greenLineB$="Put it down in writing and it's a done deal..."
  EndIf
  If negTim>350 Then negStage=3 : negTim=0 : keytim=15
 EndIf
 ;urgent objections
 If negStage=2 And negVerdict=1
  If negTim>25 And negTim<325
   Speak(1,0,1) 
   redLineA$="We've already talked about this enough for one day!"
   redLineB$="Come back when you've given it some more thought..."
  EndIf
  If negTim>375 Then negStage=5 : go=1
 EndIf 
 If negStage=2 And negVerdict=2
  If negTim>25 And negTim<325
   Speak(3,0,1) 
   redLineA$="Sorry to ruin your plans, but "+charName$(negChar)+" is DEAD!"
   redLineB$="I can't believe we dug "+Lower$(His$(g))+" body up for this charade..."
  EndIf
  If negTim>375 Then negStage=5 : go=1
 EndIf 
 If negStage=2 And negVerdict=3
  If negTim>25 And negTim<325
   Speak(1,0,1) 
   redLineA$="In case you haven't noticed, we're in competition!"
   redLineB$="I don't want to hear about "+fedName$(fed)+"..."
  EndIf
  If negTim>375 Then negStage=5 : go=1
 EndIf 
 If negStage=2 And negVerdict=4
  If negTim>25 And negTim<325
   Speak(1,0,3) 
   redLineA$="I'm flattered by your interest, but I don't consider"
   redLineB$="myself a wrestler! I've got more important things to do..."
  EndIf
  If negTim>375 Then negStage=5 : go=1
 EndIf 
 If negStage=2 And negVerdict=5
  If negTim>25 And negTim<325
   Speak(1,0,1) 
   redLineA$="This is all irrelevant, because there isn't any"
   redLineB$="room for me at "+fedName$(fed)+" anyway!"
  EndIf
  If negTim>375 Then negStage=5 : go=1
 EndIf 
 If negStage=2 And negVerdict=6
  If negTim>25 And negTim<325
   Speak(1,0,3) 
   redLineA$="Sorry, but I can't leave "+fedName$(charFed(negChar))
   redLineB$="while the roster is in such bad shape..."
  EndIf
  If negTim>375 Then negStage=5 : go=1
 EndIf
 If negStage=2 And negVerdict=7
  If negTim>25 And negTim<325 And charFed(negChar)=fed
   Speak(1,0,1) 
   redLineA$="Forget about it, "+charName$(gamChar)+"! I'm not"
   redLineB$="spending any longer with you than I have to..."
  EndIf
  If negTim>25 And negTim<325 And charFed(negChar)<>fed
   Speak(1,0,1) 
   redLineA$="Forget about it, "+charName$(gamChar)+"! I wouldn't work"
   redLineB$="for an asshole like you if my life depended on it..."
  EndIf
  If negTim>375 Then negStage=5 : go=1
 EndIf 
 ;contract objections
 If negStage=2 And negVerdict=20
  If negTim>25 And negTim<325
   Speak(1,0,1) 
   redLineA$="You expect me to work for NOTHING?! I didn't"
   redLineB$="get into this business to become your slave!"
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=15
 EndIf 
 If negStage=2 And negVerdict=21
  If negTim>25 And negTim<325
   Speak(1,0,1) 
   redLineA$="I'm insulted that you would even make such an offer!"
   redLineB$="You're obviously not taking this very seriously..."
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=15
 EndIf 
 If negStage=2 And negVerdict=22
  If negTim>25 And negTim<325 And negContract>1
   Speak(1,0,1) 
   redLineA$="What's the point in signing up for "+negContract+" weeks?"
   redLineB$="We wouldn't have time to get anything done!"
  EndIf
  If negTim>25 And negTim<325 And negContract=1
   Speak(1,0,1) 
   redLineA$="What's the point in signing up for a week?"
   redLineB$="We wouldn't have time to get anything done!"
  EndIf
  If negTim>25 And negTim<325 And negContract=0
   Speak(1,0,1) 
   redLineA$="You want me to work for you without a contract?"
   redLineB$="I'm afraid that'll require a little compensation!"
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=15
 EndIf 
 If negStage=2 And negVerdict=23
  If negTim>25 And negTim<325
   Speak(1,0,1) 
   redLineA$="No, I'm not happy with that arrangement."
   redLineB$="We both know that I'm worth more than that..."
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=15
 EndIf 
 If negStage=2 And negVerdict=24
  If negTim>25 And negTim<325
   Speak(1,0,1) 
   redLineA$="No, I'm not happy with $"+GetFigure$(negSalary)+" per week."
   redLineB$="I think I should be earning more than that..."
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=15
 EndIf 
 If negStage=2 And negVerdict=25
  If negTim>25 And negTim<325
   Speak(1,0,1) 
   redLineA$="No, I'm not happy with a $"+GetFigure$(negPayOff-negBuyOut)+" pay-off."
   redLineB$="Everybody knows I deserve more than that..."
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=15
 EndIf  
 If negStage=2 And negVerdict=26
  If negTim>25 And negTim<325
   Speak(1,0,1) 
   redLineA$="No, even a "+negContract+"-week contract deserves more than that."
   redLineB$="You're paying for the QUALITY - not the quantity!"
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=15
 EndIf 
 If negStage=2 And negVerdict=27
  If negTim>25 And negTim<325
   Speak(1,0,1) 
   redLineA$="Sorry, but a "+negContract+"-week contract needs to pay better."
   redLineB$="That's a big commitment you're asking from me!"
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=15
 EndIf 
 If negStage=2 And negVerdict=28
  If negTim>25 And negTim<325 And negPayOffPrefer=<3
   Speak(1,0,1) 
   redLineA$="No, I'm not happy with that arrangement. I'd like"
   redLineB$="at least "+(negPayOffPrefer*25)+"% of my payment to be a lump sum?"
  EndIf
  If negTim>25 And negTim<325 And negPayOffPrefer=>4
   Speak(1,0,1) 
   redLineA$="No, I'm not happy with that arrangement. I'd prefer"
   redLineB$="to receive every penny of my payment upfront!"
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=15
 EndIf 
 If negStage=2 And negVerdict=29
  If negTim>25 And negTim<325
   Speak(1,0,1) 
   redLineA$="I'm too busy to spend "+negContract+" weeks as a wrestler!"
   redLineB$="I've got more important things to worry about..."
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=15
 EndIf 
 If negStage=2 And negVerdict=30
  If negTim>25 And negTim<325
   Speak(1,0,1) 
   redLineA$="I've already got a better deal than that right now!"
   redLineB$="You're supposed to give me a reason to tear it up..."
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=15
 EndIf 
 If negStage=2 And negVerdict=31
  If negTim>25 And negTim<325
   Speak(3,0,1) 
   redLineA$="I'm still waiting for my $"+GetFigure$(negBuyOut)+", "+charName$(gamChar)+"!"
   redLineB$="Until I get that, this deal isn't going anywhere..."
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=15
 EndIf 
 ;clause issues
 If negStage=2 And negVerdict=40
  If negTim>25 And negTim<325
   Speak(1,0,1) 
   redLineA$="Sorry, but even those clauses don't excuse such"
   redLineB$="low pay! I'm gonna need to see some cash as well..."
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=15
 EndIf 
 If negStage=2 And negVerdict=41
  If negTim>25 And negTim<325 And ClauseEntitled(negChar,fed,1)=1
   Speak(1,0,3) 
   redLineA$="Sorry, but I think I'm entitled to a little creative"
   redLineB$="control? It's the least you could do to sweeten the deal..."
  EndIf
  If negTim>25 And negTim<325 And ClauseEntitled(negChar,fed,1)=2
   Speak(1,0,1) 
   redLineA$="Sorry, but I think I'm entitled to control my career!"
   redLineB$="It's the least you could do to make this worthwhile..."
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=15
 EndIf
 If negStage=2 And negVerdict=42
  If negTim>25 And negTim<325 And ClauseEntitled(negChar,fed,2)=1
   Speak(1,0,3) 
   redLineA$="Sorry, but I think I'm entitled to at least get"
   redLineB$="paid SOMETHING whether you use me or not?"
  EndIf
  If negTim>25 And negTim<325 And ClauseEntitled(negChar,fed,2)=2
   Speak(1,0,1) 
   redLineA$="Sorry, but I think I'm entitled to get paid no matter"
   redLineB$="what! It's not my fault if you don't decide to use me..."
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=15
 EndIf 
 If negStage=2 And negVerdict=43
  If negTim>25 And negTim<325 And ClauseEntitled(negChar,fed,3)=1
   Speak(1,0,3) 
   redLineA$="Sorry, but I think I'm entitled to a little health"
   redLineB$="cover? It's the least you could do to sweeten the deal..."
  EndIf
  If negTim>25 And negTim<325 And ClauseEntitled(negChar,fed,3)=2
   Speak(1,0,1) 
   redLineA$="Sorry, but I think I'm entitled to FULL health cover!"
   redLineB$="I can't risk my neck for you if you don't pick up the tab..."
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=15
 EndIf
 If negStage=2 And negVerdict=44
  If negTim>25 And negTim<325 And negClausePrefer(1)=1
   Speak(1,0,1) 
   redLineA$="Sorry, but it's important that I have at least SOME"
   redLineB$="creative control? I don't want to be anybody's slave!"
  EndIf
  If negTim>25 And negTim<325 And negClausePrefer(1)=2
   Speak(1,0,1) 
   redLineA$="Sorry, but it's important that I have FULL control"
   redLineB$="over my career! You can't put a price on freedom..."
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=15
 EndIf
 If negStage=2 And negVerdict=45
  If negTim>25 And negTim<325 And negClausePrefer(2)=1
   Speak(1,0,3) 
   redLineA$="Sorry, but it's important that I at least get"
   redLineB$="paid SOMETHING whether I'm used or not?"
  EndIf
  If negTim>25 And negTim<325 And negClausePrefer(2)=2
   Speak(1,0,1) 
   redLineA$="Sorry, but it's important that I get paid no matter"
   redLineB$="what! Either that or I earn more when I am used?"
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=15
 EndIf 
 If negStage=2 And negVerdict=46
  If negTim>25 And negTim<325 And negClausePrefer(3)=1
   Speak(1,0,3) 
   redLineA$="Sorry, but it's important that I at least have"
   redLineB$="SOME health cover? I'd rather not take risks..."
  EndIf
  If negTim>25 And negTim<325 And negClausePrefer(3)=2
   Speak(1,0,1) 
   redLineA$="Sorry, but it's important that I have FULL"
   redLineB$="health cover! An athlete's body is their life..."
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=15
 EndIf
 ;personal objections
 If negStage=2 And negVerdict=60
  If negTim>25 And negTim<325
   Speak(1,0,1) 
   redLineA$="Remember I'm a champion of "+fedName$(charFed(negChar))+"!"
   redLineB$="I deserve to be paid more than the average wrestler..."
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=15
 EndIf 
 If negStage=2 And negVerdict=61
  If negTim>25 And negTim<325
   Speak(1,0,1) 
   redLineA$="Everybody knows I'm too big for this show! If you"
   redLineB$="want me to stay, you'll have to do better than that..."
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=15
 EndIf 
 If negStage=2 And negVerdict=62
  If negTim>25 And negTim<325 And charFed(negChar)=fed
   Speak(1,0,1) 
   redLineA$="Sorry, but I've got more enemies here than friends!"
   redLineB$="I'm not sure I can put up with it any longer..."
  EndIf
  If negTim>25 And negTim<325 And charFed(negChar)<>fed
   Speak(1,0,1) 
   redLineA$="Sorry, but I've got more enemies than friends at"
   redLineB$=fedName$(fed)+"! I'm not sure I belong there..."
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=15
 EndIf
 If negStage=2 And negVerdict=63
  If negTim>25 And negTim<325
   Speak(1,0,3) 
   redLineA$="Sorry, but I've got more friends here than at"
   redLineB$=fedName$(fed)+"! I'd rather not leave them..."
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=15
 EndIf 
 If negStage=2 And negVerdict=64
  If negTim>25 And negTim<325
   Speak(1,0,1) 
   redLineA$="Sorry, but I'm not happy here at "+fedName$(fed)+"!"
   redLineB$="It would take more than that to convince me to stay..."
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=15
 EndIf
 If negStage=2 And negVerdict=65
  If negTim>25 And negTim<325
   Speak(1,0,1) 
   redLineA$="Everybody knows that "+fedName$(charFed(negChar))+" is better"
   redLineB$="than "+fedName$(fed)+"! Why would I jump ship?"
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=15
 EndIf 
 If negStage=2 And negVerdict=66
  If negTim>25 And negTim<325
   Speak(1,0,1) 
   redLineA$="I'm not sure I belong at "+fedName$(fed)+"!"
   redLineB$="It's too tame for me to express my wild side..."
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=15
 EndIf 
 If negStage=2 And negVerdict=67
  If negTim>25 And negTim<325
   Speak(3,0,1) 
   redLineA$="I'm concerned by "+fedName$(fed)+"'s reputation!"
   redLineB$="I'd prefer to be in a more respectable environment..."
  EndIf
  If negTim>350 Then PushLuck(1) : negTim=10 : keytim=15
 EndIf 
 ;----------------- ENDINGS -----------------------
 If negStage=>4 And negTim>10 Then camFoc=1
 ;successful renewl
 If negStage=4 And negTopic=0
  If negTim>25 And negTim<325
   Speak(1,0,3)
   If charged=0 Then fedBank(fed)=fedBank(fed)-negPayOff : PlaySound sCash : charged=1
   lineA$="Good, I'm glad we could come to an agreement!"
   If negContract=<1 Then lineB$="Let's see how this deal works out..."
   If negContract>1 Then lineB$="I look forward to the next "+negContract+" weeks..."
  EndIf
  If negTim>375 Then go=1
 EndIf
 ;successful deal
 If negStage=4 And negTopic=>1
  If negTim>25 And negTim<325
   Speak(1,0,3)
   If charged=0 Then fedBank(fed)=fedBank(fed)-negPayOff : PlaySound sCash : charged=1
   If charExperience(negChar,fed)=0 Then lineA$="Great, here's to my career at "+fedName$(fed)+"!"
   If charExperience(negChar,fed)>0 Then lineA$="Great, I can't wait to return to the promotion!"
   If charVacant(negChar)=0 Then lineB$="I can start working for you right away..."
   If charVacant(negChar)=1 Then lineB$="I'll be ready to work for you by next week..."
   If charVacant(negChar)=>2 Then lineB$="I'll be ready to work for you in "+charVacant(negChar)+" weeks..."
  EndIf
  If negTim>375 Then go=1
 EndIf
 ;renewal breakdown
 If negStage=5 And negTopic=0 And charContract(negChar)=<1
  If negTim>25 And negTim<325
   Speak(1,0,2)
   redLineA$="Well, we're obviously not on the same page anymore."
   redLineB$="I guess I'll have to take my expertise elsewhere..."
  EndIf
  If negTim>375 Then go=1
 EndIf
 ;deal breakdown
 If negStage=5 And (negTopic=>1 Or charContract(negChar)>1)
  If negTim>25 And negTim<325
   Speak(1,0,1)
   redLineA$="Alright, this meeting isn't going anywhere."
   redLineB$="Let's just forget about it for the time being..."
  EndIf
  If negTim>375 Then go=1
 EndIf
 ;---------- DISPLAY SUBTITLES ----------
 DisplaySubtitles()
 ;diagnostics
 ;negVerdict=GetContractVerdict()
 ;SetFont fontStat(1)
 ;Outline("negPaymentTotal: "+negPaymentTotal,100,300,0,0,0,255,255,255)
 ;Outline("negPaymentLimit: "+negPaymentLimit,100,315,0,0,0,255,255,255)
 ;Outline("ClauseEntitled(1): "+ClauseEntitled(gamChar,fed,1),100,335,0,0,0,255,255,255)
 ;Outline("ClauseEntitled(2): "+ClauseEntitled(gamChar,fed,2),100,350,0,0,0,255,255,255)
 ;Outline("ClauseEntitled(3): "+ClauseEntitled(gamChar,fed,3),100,365,0,0,0,255,255,255)
 ;Outline("Verdict: "+negVerdict,100,385,0,0,0,255,255,255)
 ;cursor
 If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect 
 DrawImage gCursor,MouseX(),MouseY()
 ;mask shaky start
 If gotim=<0 Then Loader("Please Wait","Meeting "+charName$(negChar))

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;restore sound
;ChannelVolume chTheme,1.0	
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
;update status
If negStage=4
 If fed<>charFed(negChar)
  fedBank(charFed(negChar))=fedBank(charFed(negChar))+negBuyOut
  MoveChar(negChar,fed)
  If charVacant(negChar)>0 Then charArrived(negChar)=0 Else charArrived(negChar)=1
 EndIf
 charBank(negChar)=charBank(negChar)+negPayOff
 charWorth(negChar)=negWorth
 charSalary(negChar)=negSalary
 charContract(negChar)=negContract
 For count=1 To 3
  charClause(negChar,count)=negClause(count)
 Next
 charWorth(negChar)=((charSalary(negChar)*charContract(negChar))+(negPayOff-negBuyOut))/charContract(negChar)
 magnifier=100
 For count=1 To 3
  If count=2 Then clauseValue=20 Else clauseValue=10
  If count=1 Or charSalary(negChar)>0 Then magnifier=magnifier+(charClause(negChar,count)*clauseValue)
 Next
 charWorth(negChar)=PercentOf#(charWorth(negChar),magnifier)
 charAttitude(negChar)=charAttitude(negChar)+1
 charHappiness(negChar)=charHappiness(negChar)+PursueValue(charHappiness(negChar),100,0)
Else
 If fed=charFed(negChar) And charContract(negChar)=<1
  MoveChar(negChar,7)
  charPopularity(negChar)=charPopularity(negChar)-1
  charAttitude(negChar)=charAttitude(negChar)-1
  charHappiness(negChar)=charHappiness(negChar)+PursueValue(charHappiness(negChar),30,0)
  charAgreement(negChar,16)=2 
 EndIf
EndIf
;proceed
gamNegotiated(negChar,1)=1
screen=20
End Function

;////////////////////////////////////////////////////////////////////////////
;-------------------------- 56. SELL CHARACTER ------------------------------
;////////////////////////////////////////////////////////////////////////////
Function SellCharacter()
;establish context
Loader("Please Wait","Visiting "+fedName$(fed))
;ChannelVolume chTheme,0.5
If negTopic=2
 If charContract(negChar)=0 Then negTopic=0
 If fed=7 And charContract(negChar)>0 And charSalary(negChar)>0 Then negTopic=1
EndIf
negSetting=1 : camFoc=1
If negTopic=3 Then camFoc=2
PrepareMeeting()
;contract value
negBuyOut=charContract(negChar)*charSalary(negChar)
If negTopic=>2 And negBuyOut<charContract(negChar)*charWorth(negChar) Then negBuyOut=charContract(negChar)*charWorth(negChar)
negBuyOut=RoundOff(negBuyOut,100)
negPayOff=Rnd(negBuyOut/4,negBuyOut/2)
If negTopic=1 Or negTopic=3 Then negPayOff=negBuyOut
negPayOff=RoundOff(negPayOff,100)
;interest gauntlet
negVerdict=0
negInterest=Rnd(1,GetValue(negChar)/16)
If negTopic=3 Then negInterest=Rnd(1,GetValue(negChar)/8)
If charPopularity(negChar)>fedPopularity(fed) Then negInterest=negInterest+1
If TitleHolder(negChar,0)>0 Then negInterest=negInterest+1
If InjuryStatus(negChar)>0 Then negInterest=negInterest-1 : negVerdict=4 
If InjuryStatus(negChar)>4 Then negInterest=negInterest-1 : negVerdict=4
If charPeaked(negChar)>0 Then negInterest=negInterest-1 : negVerdict=8
If charContract(negChar)=>20 Then negInterest=negInterest-1 : negVerdict=7
If charContract(negChar)=>40 Then negInterest=negInterest-1 : negVerdict=7
If negPayOff>fedBank(fed)/5 Then negInterest=negInterest-1 : negVerdict=6
If fedSize(fed)>32 Then negInterest=negInterest-1 : negVerdict=5
If negInterest<0 Then negInterest=0
;urgent refusals
If negPayOff=>fedBank(fed)/2 Then negInterest=0 : negVerdict=6
If fedSize(fed)=>optRosterLim Then negInterest=0 : negVerdict=5
If InjuryStatus(negChar)>8 Or (charLimb(negChar,40)=0 And charLimb(negChar,43)=0) Then negInterest=0 : negVerdict=4
If fed=8 And charPopularity(negChar)<80 Then negInterest=0 : negVerdict=3
If fed=9 Then negInterest=0 : negVerdict=1
If negTopic=2 And gamNegotiated(fedBooker(fed),2)>0 Then negInterest=0 : negVerdict=2
;guaranteed reaction
If negTopic=3 Or (fed=7 And fedSize(fed)<optRosterLim) 
 If negInterest<1 Then negInterest=1 
EndIf
negChances=negInterest
;reset negotiation status
negOffer=0 : negTim=0 : negStage=0
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
foc=1 : oldfoc=foc : charged=0
keytim=10 : go=0 : gotim=0
While go=0

 Cls
 frames=WaitTimer(timer)
 For framer=1 To frames
	
	;timers
	keytim=keytim-1
	If keytim<1 Then keytim=0 
	
	;PORTAL
	gotim=gotim+1 : negTim=negTim+1 
	;speed up's
	If gotim>20 And keytim=0 And negStage<>2
	 If KeyDown(1) Or KeyDown(28) Or ButtonPressed() Or MouseDown(1) Then negTim=negTim+50 : keytim=5 ;: PlaySound sMenuBrowse
	EndIf
	;leave
	If negStage=>4 And negTim>375 Then go=1
	
	;CONFIGURATION
	If negStage=2 And keytim=0 
	 ;select option
	 If KeyDown(200) Or JoyYDir()=-1 Then foc=foc-1 : keytim=5
	 If KeyDown(208) Or JoyYDir()=1 Then foc=foc+1 : keytim=5
	 If foc<1 Then foc=1
	 If foc>2 Then foc=2
	 ;confirm
	 If KeyDown(28) Or ButtonPressed() Or MouseDown(1)
	  PlaySound sMenuGo : negTim=0 : keytim=20
	  If foc=1 And negTopic=>2 Then negStage=4
	  If foc=2 And negTopic=>2
	   negStage=1 : negChances=negChances-1
	   randy=Rnd(0,10)
	   If randy=<4 Then negChances=negChances-1
	   If randy=0 Then negChances=0
	   If negChances<0 Then negChances=0
	   adder=Rnd(negPayOff/10,negPayOff/2)
	   negPayOff=negPayOff+adder
	   negPayOff=RoundOff(negPayOff,100)
	   If negPayOff=>fedBank(fed)/2 Then negChances=0
	   negOffer=Rnd(1,3)
	  EndIf
	  If foc=1 And negTopic=<1 Then negStage=4
	  If foc=2 And negTopic=<1 Then negStage=5
	 EndIf
	 ;cancel
	 If KeyDown(1) And negTopic=<1 Then PlaySound sMenuBack : negStage=5 : negTim=0 : keytim=20
	EndIf 
	
	;PLAYER CYCLE
	For cyc=1 To no_plays
	 If pChar(cyc)>0
	  ;facial expressions
	  RandomizeAnimation(cyc)
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
	;music
	ManageMusic(-1)	
	
 UpdateWorld
 Next  

 ;ANIMATION OVERRIDE
 pFoc(1)=2 :  pFoc(2)=1
 If pChar(3)>0 
  If pSpeaking(3)>0 Then pFoc(1)=3 : pFoc(2)=3
  If negStage=2 Or (negStage=4 And negTopic=<1) Then pFoc(3)=1 Else pFoc(3)=2
 EndIf
 For cyc=1 To 3
 If pChar(cyc)>0
  If pFoc(cyc)>0
   If cyc=3 Or pFoc(cyc)=3 Then PointHead(cyc,pLimb(pFoc(cyc),1))
   LookAtPerson(cyc,pFoc(cyc))
  Else
   RotateEntity pLimb(cyc,45),0,0,0
   RotateEntity pLimb(cyc,46),0,0,0
  EndIf
  If charEyeShape(pChar(cyc))=112 Then LookAtPerson(cyc,cyc)
 EndIf
 Next

 RenderWorld 1

 ;DISPLAY
 DrawImage gLogo(2),rX#(400),rY#(65)
 ;reset speech
 For cyc=1 To no_plays
  pSpeaking(cyc)=0
 Next
 ;reset subtitles
 lineA$="" : lineB$=""
 redLineA$="" : redLineB$=""
 greenLineA$="" : greenLineB$=""
 ;get context
 g=charGender(negChar)
 ;INTRODUCTIONS
 ;0. Standard Release
 If negStage=0 And negTopic=0
  If negTim>25 And negTim<325
   Speak(1,0,2)
   lineA$=charName$(negChar)+"'s contract with "+fedName$(charFed(negChar))
   lineB$="has expired, so we're looking to release "+Him$(g)+"..."
  EndIf
  If negTim>350 And negTim<650
   Speak(1,0,3)
   lineA$="How would you like to give "+Him$(g)+" a spot on your"
   lineB$="roster over here at "+fedName$(fed)+"?"
  EndIf
  If negTim>650 Then camFoc=1
  If negTim>675 Then negStage=1 : negTim=0 : keytim=20
 EndIf
 ;1. Payed Release
 If negStage=0 And negTopic=1
  If negTim>25 And negTim<325
   Speak(1,0,3)
   lineA$="Hi, "+charName$(fedBooker(fed))+". We'd like to talk to you about"
   lineB$="releasing "+charName$(negChar)+" to the independent circuit?"
  EndIf
  If negTim>350 And negTim<650
   Speak(3,0,1)
   lineA$="Hold on, "+charName$(gamChar)+"! I've still got "+charContract(negChar)+" weeks"
   lineB$="left on my "+fedName$(charFed(negChar))+" contract..."
  EndIf
  If negTim>675 And negTim<975
   Speak(3,0,1)
   lineA$="At $"+GetFigure(charSalary(negChar))+" per week, you'd have to pay me"
   lineB$="$"+GetFigure(negPayOff)+" to terminate that agreement!"
  EndIf
  If negTim>1000 And negTim<1300 And fedSize(fed)<optRosterLim
   Speak(2,0,3)
   lineA$=He$(g)+"'s right, "+charName$(gamChar)+". We can't take "+Him$(g)
   lineB$="off your hands until that paperwork is settled..."
  EndIf
  If negTim>1000 And negTim<1300 And fedSize(fed)=>optRosterLim
   Speak(2,0,1)
   lineA$="This is all irrelevant because there's no room"
   lineB$="for "+charName$(negChar)+" at "+fedName$(fed)+" anyway!"
  EndIf
  If negTim>1300 And fedSize(fed)<optRosterLim Then camFoc=1
  If negTim>1325 And fedSize(fed)<optRosterLim Then negStage=2 : negTim=0 : keytim=20
  If negTim>1325 And fedSize(fed)=>optRosterLim Then negStage=5 : negTim=0 : keytim=20
 EndIf
 ;2. Regular Sale
 If negStage=0 And negTopic=2
  If negTim>25 And negTim<325
   Speak(1,0,3)
   lineA$="Hi, "+charName$(fedBooker(fed))+". I'd like to talk to you about"
   lineB$="bringing "+charName$(negChar)+" to "+fedName$(fed)+"?"
  EndIf
  If negTim>350 And negTim<650
   Speak(1,0,2)
   lineA$=He$(g)+"'s currently tied to "+fedName$(charFed(negChar))
   lineB$="with a "+charContract(negChar)+"-week contract at $"+GetFigure$(charSalary(negChar))+" per week..."
  EndIf
  If negTim>675 And negTim<975
   Speak(1,0,3)
   lineA$="That contract is worth $"+GetFigure$(negBuyOut)+", but how much would"
   lineB$="you pay to secure "+charName$(negChar)+"'s talents?"
  EndIf
  If negTim>975 Then camFoc=2
  If negTim>1000 Then negStage=1 : negTim=0 : keytim=20
 EndIf
 ;3. Approach
 If negStage=0 And negTopic=3
  If negTim>25 And negTim<325
   Speak(2,0,3)
   lineA$="Hi, "+charName$(gamChar)+". I'd like to talk to you about"
   lineB$="bringing "+charName$(negChar)+" to "+fedName$(fed)+"..."
  EndIf
  If negTim>350 And negTim<650
   Speak(1,0,1)
   lineA$=He$(g)+"'s currently tied to "+fedName$(charFed(negChar))
   lineB$="with a "+charContract(negChar)+"-week contract at $"+GetFigure$(charSalary(negChar))+" per week..."
  EndIf
  If negTim>675 And negTim<975
   Speak(1,0,2)
   lineA$="That contract is worth $"+GetFigure$(negBuyOut)+", so how much"
   lineB$="would you offer us to release "+charName$(negChar)+"?"
  EndIf
  If negTim>975 Then camFoc=2
  If negTim>1000 Then negStage=1 : negTim=0 : keytim=20
 EndIf
 ;POSITIVE SALE RESPONSES
 If negStage=1 And negTim>5 Then camFoc=2
 If negStage=1 And negTopic=>2 And negInterest>0
  If negTim>25 And negTim<325 And negChances=<0 
   Speak(2,0,1)
   redLineA$="Alright, forget it! "+charName$(negChar)+" hasn't got what it"
   redLineB$="takes to cut it at "+fedName$(fed)+" anyway..."
  EndIf 
  If negChances=0 And negTim>350 Then go=1
  If negTim>25 And negTim<325 And negChances=>1 And negChances=<2 And negOffer=0 And negTopic=2 
   Speak(2,0,2)
   greenLineA$="We're not that interested in "+charName$(negChar)+","
   greenLineB$="but we could offer you $"+GetFigure$(negPayOff)+" for "+Him$(g)+"?"
  EndIf
  If negTim>25 And negTim<325 And negChances>2 And negOffer=0 And negTopic=2 
   Speak(2,0,3)
   greenLineA$="Yes, I'm sure "+charName$(negChar)+" would fit in here!"
   greenLineB$="Would you accept $"+GetFigure$(negPayOff)+" for "+Him$(g)+"?"
  EndIf
  If negTim>25 And negTim<325 And negChances=>1 And negChances=<2 And negOffer=0 And negTopic=3 
   Speak(2,0,2)
   greenLineA$=charName$(negChar)+" isn't exactly a priority for us,"
   greenLineB$="but we could offer you $"+GetFigure$(negPayOff)+" for "+Him$(g)+"?"
  EndIf
  If negTim>25 And negTim<325 And negChances>2 And negOffer=0 And negTopic=3 
   Speak(2,0,3)
   greenLineA$="We're very serious about signing "+charName$(negChar)+","
   greenLineB$="so we'd be happy to pay $"+GetFigure$(negPayOff)+" for "+Him$(g)+"?"
  EndIf
  If negTim>50 And negTim<200 And negChances>0 And negOffer>0
   Speak(2,0,2)
   If negOffer=1 Then greenLineA$="Alright... how about $"+GetFigure$(negPayOff)+"?"
   If negOffer=2 Then greenLineA$="Well... what would you say to $"+GetFigure$(negPayOff)+"?"
   If negOffer=3 Then greenLineA$="We could be willing to stretch to $"+GetFigure$(negPayOff)+"?"
  EndIf
  If negChances>0 
   If (negOffer=0 And negTim>350) Or (negOffer>0 And negTim>225) Then negStage=2 : negTim=0 : keytim=20
  EndIf
 EndIf
 ;NEGATIVE SALE RESPONSES
 If negStage=1 And negTopic=>2 And negInterest=0
  If negTim>25 And negTim<325 And negVerdict=0
   Speak(2,0,3)
   redLineA$="Thanks for the offer, "+charName$(gamChar)+", but"
   redLineB$="we're not interested in signing "+charName$(negChar)+"..."
  EndIf
  If negTim>25 And negTim<325 And negVerdict=1
   Speak(2,0,1)
   redLineA$="Sorry, but we only look after dead wrestlers - and"
   redLineB$="I'm not sure "+charName$(negChar)+" is ready to go just yet!"
  EndIf
  If negTim>25 And negTim<325 And negVerdict=2
   Speak(2,0,1)
   redLineA$="You've already taken up enough of our time!"
   redLineB$="Get on with your show and let us do the same..."
  EndIf
  If negTim>25 And negTim<325 And negVerdict=3
   Speak(2,0,3)
   redLineA$="Sorry, but "+charName$(negChar)+" isn't a big enough"
   redLineB$="star to make a name for "+Him$(g)+"self in Hollywood..."
  EndIf
  If negTim>25 And negTim<325 And negVerdict=4
   Speak(2,0,1)
   redLineA$="No, we're not taking your damaged goods!"
   redLineB$="Everybody knows "+charName$(negChar)+" is a cripple..."
  EndIf
  If negTim>25 And negTim<325 And negVerdict=5
   Speak(2,0,3)
   redLineA$="Sorry, but I'm not sure there's enough room"
   redLineB$="on our roster to sign anybody at the moment!"
  EndIf 
  If negTim>25 And negTim<325 And negVerdict=6
   Speak(2,0,3)
   redLineA$="Sorry, but "+fedName$(fed)+" can't afford"
   redLineB$="to spend that kind of money at the moment..."
  EndIf
  If negTim>25 And negTim<325 And negVerdict=7
   Speak(2,0,1)
   redLineA$="Sorry, but we're not buying out a "+charContract(negChar)+"-week contract!"
   redLineB$="Come back when there's a little less red tape..."
  EndIf
  If negTim>25 And negTim<325 And negVerdict=8
   Speak(2,0,1)
   redLineA$="Sorry, but we can't do anything with a "+charAge(negChar)+"-year old!"
   redLineB$="Everybody knows "+charName$(negChar)+" is past "+Lower$(His$(g))+" prime..."
  EndIf
  If negTim>350 Then negStage=5 : negTim=0 : keytim=20
 EndIf
 ;RELEASE RESPONSES
 If negStage=1 And negTopic=0
  If negTim>25 And negTim<325 And negInterest=0 And fed=9
   Speak(2,0,1)
   redLineA$="Sorry, but we only look after dead wrestlers - and"
   redLineB$="I'm not sure "+charName$(negChar)+" is ready to go yet!"
  EndIf
  If negTim>25 And negTim<325 And negInterest=0 And fed<>9
   Speak(2,0,1)
   redLineA$="Sorry, but I don't think there's a place for"
   redLineB$=charName$(negChar)+" on the "+fedName$(fed)+" roster..."
  EndIf
  If negTim>25 And negTim<325 And negInterest>0 
   Speak(2,0,3)
   greenLineA$="Sure, we'll take "+charName$(negChar)+" off your hands!"
   greenLineB$="Leave "+Him$(g)+" here and we'll discuss terms later..."
  EndIf
  If negTim>350 And negChances>0 Then negStage=2 : negTim=0 : keytim=20
  If negTim>350 And negChances=0 Then negStage=5 : negTim=0 : keytim=20
 EndIf
 ;CONFIRMATION
 If negStage=2
  ;initiate
  If foc=1 Then mood=3
  If foc=2 Then mood=1
  Speak(1,0,mood)
  ;options
  If negTopic=0 Then headerA$="CONFIRM RELEASE" : headerB$="<<< WITHDRAW <<<"
  If negTopic=1 Then headerA$="PAY $"+GetFigure$(negPayOff) : headerB$="<<< WITHDRAW <<<"
  If negTopic=>2 Then headerA$="ACCEPT $"+GetFigure$(negPayOff) : headerB$="REJECT OFFER"
  DrawOption(1,rX#(400),rY#(520),headerA$,"")
  DrawOption(2,rX#(400),rY#(560),headerB$,"")
  ;stat reminder
  DrawProfile(negChar,-1,-1,0) 
 EndIf
 ;ENDINGS
 ;confirm success
 If negStage=4 And negTim>10 
  If negTopic=>2 Then camFoc=2 Else camFoc=3
 EndIf
 If negStage=4 And negTim>25 And negTim<325 And negTopic=0
  Speak(3,0,1)
  lineA$="It's your loss, "+charName$(gamChar)+"! One day,"
  lineB$="you'll be begging me to come back..."
 EndIf
 If negStage=4 And negTim>25 And negTim<325 And negTopic=1
  Speak(3,0,1) 
  lineA$="You paid to get rid of me?! Good! Who wants to"
  lineB$="work at "+fedName$(charFed(gamChar))+" anyway..."
 EndIf
 If negStage=4 And negTim>25 And negTim<325 And negTopic=>2
  Speak(2,0,3)
  lineA$="Great! Welcome to the show, "+charName$(negChar)+"!"
  lineB$="I'm sure you'll enjoy your time with us..."
 EndIf
 ;exchange money
 If negStage=4 And negTim>35 And charged=0 
  PlaySound sCash : charged=1
  If negTopic=1 Then fedBank(charFed(negChar))=fedBank(charFed(negChar))-negPayOff
  If negTopic=>2 
   fedBank(fed)=fedBank(fed)-negPayOff
   fedBank(charFed(negChar))=fedBank(charFed(negChar))+negPayOff
  EndIf
 EndIf
 ;confirm breakdown
 If negStage=5 And negTim>10 Then camFoc=1
 If negStage=5 And negTim>25 And negTim<325
  Speak(1,0,1)
  redLineA$="OK, this meeting isn't going anywhere."
  redLineB$="Let's just forget about it for the time being..."
 EndIf
 ;---------- DISPLAY SUBTITLES ----------
 DisplaySubtitles()
 ;cursor
 If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect 
 DrawImage gCursor,MouseX(),MouseY()

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;restore sound
;ChannelVolume chTheme,1.0
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
;make transition
If negStage=4
 MoveChar(negChar,fed)
 GenerateContract(negChar)
 charAttitude(negChar)=charAttitude(negChar)+1
 charHappiness(negChar)=charHappiness(negChar)+PursueValue(charHappiness(negChar),100,0)
 CheckLimits(negChar)
 If negTopic=<1 Then ChangeRealRelationship(negChar,gamChar,-1)
 gamNegotiated(negChar,1)=1 
 charAgreement(negChar,16)=2
EndIf
;clock visit
If negTopic=2 Then gamNegotiated(fedBooker(fed),2)=1
If negTopic=3 Then charAgreement(negChar,17)=2
;proceed
fed=charFed(gamChar)
If negStage=4 Then screen=20 Else screen=12
End Function

;////////////////////////////////////////////////////////////////////////////
;-------------------------- RELATED FUNCTIONS -------------------------------
;////////////////////////////////////////////////////////////////////////////

;GET BEST FIGURE CHANGER
Function FigureRange(amount)
 ;negative values
 If amount<0 Then value=-10
 If amount=<-1000 Then value=-100
 If amount=<-10000 Then value=-1000
 If amount=<-100000 Then value=-10000
 If amount=<-1000000 Then value=-50000
 If amount=<-10000000 Then value=-100000
 ;positive values
 If amount=>0 Then value=10
 If amount=>1000 Then value=100
 If amount=>10000 Then value=1000
 If amount=>100000 Then value=10000
 If amount=>1000000 Then value=50000
 If amount=>10000000 Then value=100000
 Return value
End Function

;CALCULATE MARKET VALUE
Function GetValue(char)
 value=charPopularity(char)*4
 value=value+charStrength(char)+charSkill(char)+charAgility(char)+charStamina(char)+charToughness(char)
 value=value/8
 ;If charPeaked(char)>0 Then value=value-(value/10)
 If value>99 Then value=99 
 Return value
End Function

;CALCULATE WORTH (RAW SALARY)
Function CalculateWorth(char,promotion)
 ;base value
 chunk#=GetValue(char)-40
 If chunk#<10 Then chunk#=10
 magnifier#=chunk#/15
 If magnifier#<1 Then magnifier#=1
 value=Int((chunk#*chunk#)*magnifier#)
 ;filters
 If charFed(char)=7 Then value=value/2 ;unemployed
 If InjuryStatus(char)=>1 And InjuryStatus(char)=<4 Then value=value-(value/4) ;injured
 If InjuryStatus(char)>4 Then value=value/2 ;severely injured
 If TitleHolder(char,1)
  value=value+(value/3) ;bonus for world champions
 Else
  If TitleHolder(char,2) Or TitleHolder(char,3) Or charPopularity(char)>fedPopularity(promotion) Then value=value+(value/5) ;bonus for other champions 
 EndIf
 If charFed(char)=8 Then value=value*2 ;celebrity
 ;learning curve
 If charFed(char)<>8
  curve=25+CountExperience(char,0)
  If curve>100 Then curve=100
  value=Int(PercentOf#(value,curve))
  ;If game=1 And value>charWorth(char)*2 Then value=charWorth(char)*2
 EndIf
 ;limits
 value=RoundOff(value,10) 
 If value>1000 Then value=RoundOff(value,100)
 If value<100 Then value=100
 Return value
End Function

;GENERATE CPU CONTRACT
Function GenerateContract(char)
 If charFed(char)=7 And char<>fedBooker(7)
  ;independent status
  charWorth(char)=CalculateWorth(char,charFed(char))
  charSalary(char)=0
  charContract(char)=0
  charClause(char,1)=2
  charClause(char,2)=0
  charClause(char,3)=0
 Else
  ;calculate wages
  If charFed(char)=>8 Then charContract(char)=0 Else charContract(char)=Rnd(8,48)
  For count=1 To 3
   charClause(char,count)=Rnd(0,ClauseEntitled(char,charFed(char),count))
   If count=1 Then charClause(char,count)=Rnd(-2,ClauseEntitled(char,charFed(char),count))
   randy=Rnd(0,3)
   If randy=0 Then charClause(char,count)=charClause(char,count)+1
   If charClause(char,count)<0 Then charClause(char,count)=0
   If charClause(char,count)>2 Then charClause(char,count)=2
  Next
  charWorth(char)=CalculateWorth(char,charFed(char))
  charSalary(char)=charWorth(char)
  charSalary(char)=ContractFilter(charSalary(char),charContract(char),charFed(char))
  charSalary(char)=ClauseFilter(charSalary(char),charSalary(char),charClause(char,1),charClause(char,2),charClause(char,3),charFed(char))
  charSalary(char)=Rnd(PercentOf#(charSalary(char),70),PercentOf#(charSalary(char),120))
  charSalary(char)=RoundOff(charSalary(char),10)
  If charSalary(char)>1000 Then charSalary(char)=RoundOff(charSalary(char),100)
 EndIf
End Function

;FILTER SALARY THROUGH CONTRACT LENGTH
Function ContractFilter(value,contract,promotion)
 factor#=100
 ;negative swing
 If contract<24 And promotion<>3
  ;swing#=PercentOf#(8.0,GetPercent#(charAttitude(negChar)-45,45))
  ;If swing#<2.0 Then swing#=2.0
  ;factor#=GetPercent#((24-(24/swing#))+(Float#(contract)/swing#),24)
  factor#=GetPercent#(24-(Float#(24-contract)/3.0),24)
 EndIf
 ;positive swing
 If contract>24
  ;swing#=Float#(110-charAttitude(negChar))/5
  ;If swing#<3.0 Then swing#=3.0
  ;factor#=GetPercent#((24-(24/swing#))+(Float#(contract)/swing#),24)
  factor#=GetPercent#(24+(Float#(contract-24)/4.0),24)
 EndIf
 ;translate
 value=Int(PercentOf#(value,factor#))
 Return value
End Function

;FILTER SALARY THROUGH LUXURIES
Function ClauseFilter(value,salary,clause1,clause2,clause3,promotion)
 reduction=0
 For count=1 To 2
  ;creative control reductions
  If clause1=>count
   If clause1>ClauseEntitled(char,promotion,1) Then reduction=reduction+10 Else reduction=reduction+5
  EndIf
  ;performance clause reductions
  If clause2=>count And salary>0
   If clause2>ClauseEntitled(char,promotion,2) Then reduction=reduction+20 Else reduction=reduction+10
  EndIf
  ;health policy reductions
  If clause3=>count And salary>0
   If clause3>ClauseEntitled(char,promotion,3) Then reduction=reduction+10 Else reduction=reduction+5
  EndIf
 Next
 ;apply reductions
 value=Int(PercentOf#(value,100-reduction))
 Return value
End Function

;FIND CLAUSE ENTITLEMENT
Function ClauseEntitled(char,promotion,clause)
 value=0
 ;creative control
 If clause=1
  experience=CountExperience(char,0)
  If experience>100 Then experience=100
  entitle=((charPopularity(char)*3)+experience)/4
  If TitleHolder(char,0)>0 Or charPopularity(char)>fedPopularity(promotion) Then entitle=entitle+(entitle/10)
  If entitle=>PercentOf#(fedPopularity(promotion),80) Then value=1
  If entitle=>PercentOf#(fedPopularity(promotion),100) Then value=2
 EndIf
 ;performance clause
 If clause=2
  entitle=((AverageStats(char)*2)+(charPopularity(char)*2))/4
  If TitleHolder(char,0)>0 Or charPopularity(char)>fedPopularity(promotion) Then entitle=entitle+(entitle/10)
  If entitle=>PercentOf#(fedPopularity(promotion),80) Then value=1
  If entitle=>PercentOf#(fedPopularity(promotion),100) Then value=2
 EndIf
 ;health policy
 If clause=3
  entitle=((charToughness(char)*2)+(charPopularity(char)*2))/4
  If TitleHolder(char,0)>0 Or charPopularity(char)>fedPopularity(promotion) Then entitle=entitle+(entitle/10)
  If entitle=>PercentOf#(fedPopularity(promotion),80) Then value=1
  If entitle=>PercentOf#(fedPopularity(promotion),100) Then value=2
  If InjuryStatus(char)>0 Then value=value-1
 EndIf
 ;limits
 If value<0 Then value=0
 If value>2 Then value=2
 Return value
End Function

;GET CONTRACT VERDICT
Function GetContractVerdict()
 ;translate values
 verdict=0
 negContractWorth=ContractFilter(negWorth,negContract,fed)
 negClauseWorth=ClauseFilter(negContractWorth,negSalary,negClause(1),negClause(2),negClause(3),fed)
 negPaymentTotal=(negSalary*negContract)+PercentOf#(negPayOff-negBuyOut,125)
 negPaymentRequired=negClauseWorth*negContract
 ;personal objections (60-80)
 If negPaymentTotal<PercentOf#(negPaymentRequired,150)
  negFriendsHere=0 : negEnemiesHere=0
  For count=1 To fedSize(fed)
   If charRealRelationship(negChar,fedRoster(fed,count))>0 Then negFriendsHere=negFriendsHere+1
   If charRealRelationship(negChar,fedRoster(fed,count))<0 Then negEnemiesHere=negEnemiesHere+1
  Next
  negFriendsThere=0 : negEnemiesThere=0
  For count=1 To fedSize(charFed(negChar))
   If charRealRelationship(negChar,fedRoster(charFed(negChar),count))>0 Then negFriendsThere=negFriendsThere+1
   If charRealRelationship(negChar,fedRoster(charFed(negChar),count))<0 Then negEnemiesThere=negEnemiesThere+1
  Next
  If charFed(negChar)<>fed And charFed(negChar)=<6 And charHappiness(negChar)=>60 And negFriendsHere<negEnemiesHere 
   style=((125-charToughness(negChar))+charAttitude(negChar))/2 ;(weak=80%, average=60%, thug=40%)
   If fedReputation(fed)<style+10 And fedReputation(fed)<90 And fedReputation(fed)<fedReputation(charFed(negChar)) Then verdict=67 ;fed is too hardcore
   If fedReputation(fed)>style+20 And fedReputation(fed)>70 And fedReputation(fed)>fedReputation(charFed(negChar)) Then verdict=66 ;fed is too boring
   If fedPopularity(fed)<fedPopularity(charFed(negChar)) And charContract(negChar)>0 Then verdict=65 ;working somewhere better
  EndIf
  If charFed(negChar)=fed And charHappiness(negChar)<60 Then verdict=64 ;not happy enough to renew
  If charFed(negChar)<>fed And negFriendsThere=>negFriendsHere*2 Then verdict=63 ;don't want to leave friends
  If charFed(negChar)=fed And negEnemiesHere=>negFriendsHere*2 Then verdict=62 ;don't want to stay with enemies
  If charFed(negChar)<>fed And negEnemiesHere=>negFriendsHere*2 And negEnemiesHere>negEnemiesThere Then verdict=62 ;don't want to work with enemies
  If charFed(negChar)=fed And fedPopularity(fed)<charPopularity(negChar) And fedPopularity(fed)<90 Then verdict=61 ;too big for fed
  If TitleHolder(negChar,0)>0 Then verdict=60 ;champion leverage
 EndIf
 ;clause issues (40-60)
 If negPaymentTotal<PercentOf#(negPaymentRequired,150)
  For count=1 To 3
   randy=Rnd(1,3)
   If negClause(randy)<negClausePrefer(randy) Then verdict=43+randy ;holding out for better clause
  Next
 EndIf
 If negPaymentTotal<negPaymentRequired
  If negClause(1)>0 Or negClause(2)>0 Or negClause(3)>0 Then verdict=40 ;clauses don't excuse pay
  For count=1 To 3
   randy=Rnd(1,3)
   If negClause(randy)<ClauseEntitled(negChar,fed,randy) Then verdict=40+randy ;feels entitled to better clause
  Next
 EndIf
 ;contract issues (20-40)
 ignoreClauses=Rnd(0,1)
 If negPaymentTotal<negPaymentRequired And (ignoreClauses=1 Or verdict=0 Or verdict=>60)
  Repeat
   randy=Rnd(0,6)
   If randy=6 And negContract>20 Then verdict=27 ;not enough for longer contract
   If randy=5 And negContract=<20 Then verdict=26 ;too little even for shorter contract 
   If randy=4 And negPayOff-negBuyOut>0 Then verdict=25 ;pay-off is too low
   If randy=>2 And randy=<3 And negSalary>0 Then verdict=24 ;salary is too low
   If randy=<1 Then verdict=23 ;payment is too low
  Until verdict=>1 And verdict=<40 
  If negContract>20 And negPaymentTotal=>negWorth*negContract Then verdict=27 ;not enough for longer contract
 EndIf
 If negPayOffPrefer>0 And negPayOff-negBuyOut<PercentOf#(negPaymentRequired,negPayOffPrefer*25) And negPaymentTotal<PercentOf#(negPaymentRequired,150)
  If negPayOffPrefer=>4 And negPayOff-negBuyOut>0 Then verdict=25 Else verdict=28 ;insists on pay-off
 EndIf
 If negContract=<4 And negPaymentTotal<PercentOf#(negPaymentRequired,150) 
  If negContract=<1 Or charFed(negChar)<>8 Then verdict=22 ;contract way too short
 EndIf
 If negContract>8 And charFed(negChar)=8 And negPaymentTotal<PercentOf#(negPaymentRequired,150) Then verdict=29 ;too long for celebrity  
 If negPaymentTotal<negPaymentRequired/2 Then verdict=21 ;payment way too low
 If negPayOff-negBuyOut=0 And negSalary=0 Then verdict=20 ;slave labour!
 ;already got a better deal?
 If (charFed(negChar)=fed And charContract(negChar)>1) Or (charFed(negChar)<>fed And charContract(negChar)>0)
  magnifyOffer=100 : magnifyGot=100
  For count=1 To 3
   If count=2 Then clauseValue=20 Else clauseValue=10
   If count=1 Or negSalary>0 Then magnifyOffer=magnifyOffer+(negClause(count)*clauseValue)
   If count=1 Or charSalary(negChar)>0 Then magnifyGot=magnifyGot+(charClause(negChar,count)*clauseValue)
  Next
  If PercentOf#(negPaymentTotal,magnifyOffer)<PercentOf#(charWorth(negChar)*charContract(negChar),magnifyGot) Then verdict=30
  If PercentOf#(negSalary,magnifyOffer)<PercentOf#(charSalary(negChar),magnifyGot) Then verdict=30
 EndIf
 ;haven't met buy-out
 If negBuyOut>0 And negPayOff<negBuyOut Then verdict=31
 ;urgent objections (1-20)
 If charFed(negChar)<>fed
  If (charFed(negChar)=<6 And fedSize(charFed(negChar))=<12) Or fedSize(charFed(negChar))=<1 Then verdict=6 ;can't leave small roster 
  If fedSize(fed)=>optRosterLim Then verdict=5 ;no room anyway!
 EndIf
 If ImportantChar(negChar) Then verdict=4 ;important character
 If charRealRelationship(negChar,gamChar)<0 And negPaymentTotal<negPaymentRequired*2 Then verdict=7 ;hates you!
 If gamSchedule(gamDate)=4 And charFed(negChar)=gamRivalFed(gamDate) Then verdict=3 ;inter-promotional rivals!
 If CourtDate()>0 And negChar=gamCourtChar(CourtDate()) Then verdict=3 ;court rivals!
 If gamNegotiated(negChar,1)>0 Then verdict=1 ;already tried
 If charFed(negChar)=9 And negChar<>fedBooker(9) Then verdict=2 ;dead!
 ;cheat guarantee!
 If KeyDown(46) Then verdict=0
 Return verdict
End Function

;LOSE CHANCES
Function PushLuck(degree)
 negChances=negChances-degree
 If negChances>0 Then negStage=1 Else negStage=5
End Function