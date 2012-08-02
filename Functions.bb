;//////////////////////////////////////////////////////////////////////////////
;--------------------------- UNIVERSAL FUNCTIONS ------------------------------
;//////////////////////////////////////////////////////////////////////////////

;PRODUCE VARIANT SOUND
Function ProduceSound(entity,sound,pitch,vol#)
 ;fluctuate pitch
 range1=pitch-(pitch/8)
 range2=pitch+(pitch/16)
 pitcher=Rnd(range1,range2)
 ;fluctuate volume
 If vol#=0 Then vol#=Rnd(0.4,1.2)
 If vol#>1.0 Then vol#=1.0
 ;deliver sound
 If sound>0 And (gotim>0 Or screen<>50)
  If pitch>0 Then SoundPitch sound,pitcher
  SoundVolume sound,vol#
  If screen=50
   If entity=<0 Then EmitSound sound,cam
   If entity>0 Then EmitSound sound,entity 
  Else
   PlaySound sound
  EndIf
  ;reset
  If pitch>0 Then SoundPitch sound,pitch
  SoundVolume sound,1
 EndIf
End Function

;---------------------------------------------------------------------------
;///////////////////////////// GRAPHICAL ///////////////////////////////////
;---------------------------------------------------------------------------

;OUTLINE
Function Outline(script$,x,y,r1,g1,b1,r2,g2,b2)
 ;outline
 If r1<>r2 Or g1<>g2 Or b1<>b2
  Color r1,g1,b1
  Text x+2,y+2,script$,1,1
  Text x+1,y,script$,1,1
  Text x-1,y,script$,1,1
  Text x,y+1,script$,1,1
  Text x,y-1,script$,1,1
 EndIf
 ;core
 Color r2,g2,b2
 Text x,y,script$,1,1
End Function

;OUTLINE STRAIGHT
Function OutlineStraight(script$,x,y,r1,g1,b1,r2,g2,b2)
 ;outline
 If r1<>r2 Or g1<>g2 Or b1<>b2
  Color r1,g1,b1
  Text x+2,y+2,script$,0,1
  Text x+1,y,script$,0,1
  Text x-1,y,script$,0,1
  Text x,y+1,script$,0,1
  Text x,y-1,script$,0,1
 EndIf
 ;core
 Color r2,g2,b2
 Text x,y,script$,0,1
End Function

;PLOT BOLD LINE
Function DrawLine(startX,startY,endX,endY,r,g,b)
 ;outline
 Color 0,0,0
 Line startX-1,startY,endX-1,endY
 Line startX+1,startY,endX+1,endY
 Line startX,startY-1,endX,endY-1 
 Line startX,startY+1,endX,endY+1
 ;coloured line
 Color r,g,b
 Line startX,startY,endX,endY
End Function

;RESOLUTION X FIX
Function rX#(x#)
 factor#=800.0/Float(GraphicsWidth())
 newX#=x#/factor#
 Return newX#
End Function

;RESOLUTION Y FIX
Function rY#(y#)
 factor#=600.0/Float(GraphicsHeight())
 newY#=y#/factor#
 Return newY#
End Function

;---------------------------------------------------------------------------
;//////////////////////////// MATHEMATICAL /////////////////////////////////
;---------------------------------------------------------------------------

;CALCULATE HEIGHT IN FEET & INCHES
Function GetHeight$(value)
 feet=value/12
 inches=value-(feet*12)
 ft$=(feet+5)+"'"
 in$=inches+"''"
 figure$=ft$+in$
 Return figure$
End Function

;CALCULATE 1'000'000 FIGURE
Function GetFigure$(value)
  minus=0
  If value<0 Then minus=1 : value=value-(value*2)
  ;get segments
  hundreds=0 : thousands=0 : millions=0
  millions=value/1000000
  If millions<0 Then millions=0
  thousands=(value-(millions*1000000))/1000
  If thousands<0 Then thousands=0
  hundreds=value-((millions*1000000)+(thousands*1000))
  If hundreds<0 Then hundreds=0
  ;piece together
  hun$=hundreds
  If thousands>0 Then tho$=thousands Else tho$=""
  If millions>0 Then mil$=millions Else mil$=""
  If (thousands>0 Or millions>0) Then hun$="'"+hundreds
  If (thousands>0 Or millions>0) And hundreds<100 Then hun$="'0"+hundreds
  If (thousands>0 Or millions>0) And hundreds<10 Then hun$="'00"+hundreds
  If millions>0 Then tho$="'"+thousands
  If millions>0 And thousands<100 Then tho$="'0"+thousands
  If millions>0 And thousands<10 Then tho$="'00"+thousands
  ;return
  If minus=0 Then figure$=mil$+tho$+hun$ 
  If minus=1 Then figure$="-"+mil$+tho$+hun$
  Return figure$
End Function

;TRANSLATE NUMBER INTO DIGITS
Function Dig$(value,degree)
 digits$=value
 If value<degree Then digits$="0"+digits$
 If value<degree/10 Then digits$="0"+digits$
 If value=0 And degree=10 Then digits$="00"
 If value=0 And degree=100 Then digits$="000"
 Return digits$
End Function

;GET FIGURE TO NEAREST ???
Function RoundOff(value,degree)
 floater#=value/degree
 inty=Int(floater#)
 returner=inty*degree
 Return returner
End Function 

;PURSUE FIGURE
Function PursueFigure(current,target)
 ;working values
 differ=target-current
 value=0
 ;negative values
 If differ<0 Then value=-1
 If differ=<-10 Then value=-10
 If differ<-200 Then value=-100
 If differ<-2000 Then value=-1000
 If differ<-20000 Then value=-10000
 If differ<-200000 Then value=-100000
 If differ<-2000000 Then value=-1000000
 ;positive values
 If differ>0 Then value=1
 If differ=>10 Then value=10
 If differ>200 Then value=100
 If differ>2000 Then value=1000
 If differ>20000 Then value=10000
 If differ>200000 Then value=100000
 If differ>2000000 Then value=1000000
 Return value
End Function

;REACHED VALUE?
Function Reached(curr#,dest#,range)
 value=0
 If curr#>dest#-range And curr#<dest#+range Then value=1
 Return value
End Function

;REACHED CO-ORDINATE?
Function ReachedCord(currX#,currZ#,destX#,destZ#,range)
 value=0
 If currX#=>destX#-range And currX#=<destX#+range And currZ#=>destZ#-range And currZ#=<destZ#+range Then value=1
 Return value
End Function

;CLEAN GIVEN ANGLE
Function CleanAngle#(angle#)
 its=0
 Repeat
  If angle#<0 Then angle#=angle#+360
  If angle#>360 Then angle#=angle#-360
  its=its+1
 Until (angle#=>0 And angle#=<360) Or its>100
 Return angle#
End Function

;FIND BEST ANGLE ROUTE
Function ReachAngle#(sA#,tA#,speed#)
 ;clean angles
 sA#=CleanAngle#(sA#)
 tA#=CleanAngle#(tA#)
 ;get negative route
 neg=0 : checkA#=sA#
 Repeat
  neg=neg+1
  checkA#=checkA#-1
  If checkA#<0 Then checkA#=359
 Until checkA#=>tA#-1 And checkA#=<tA#+1
 ;get positive route
 pos=0 : checkA#=sA#
 Repeat
  pos=pos+1
  checkA#=checkA#+1
  If checkA#>359 Then checkA#=0
 Until checkA#=>tA#-1 And checkA#=<tA#+1
 ;return shortest route
 If neg<pos Then value#=-speed# Else value#=speed#
 Return value#
End Function

;SATISFIED TARGET ANGLE?
Function SatisfiedAngle(sA#,tA#,range)
 value=0
 ;clean working angles
 sA#=CleanAngle#(sA#)
 tA#=CleanAngle#(tA#)
 ;scan clockwise
 angler#=sA#
 For count=1 To range
  If angler#=>tA#-1 And angler#=<tA#+1 Then value=1 
  angler#=angler#+1
  If angler#>360 Then angler#=0
 Next
 ;scan counter-clockwise
 angler#=sA#
 For count=1 To range
  If angler#=>tA#-1 And angler#=<tA#+1 Then value=1 
  angler#=angler#-1
  If angler#<0 Then angler#=360
 Next
 Return value
End Function

;CALCULATE DIFFERENCE (between any 2 numbers)
Function GetDiff#(source#,dest#)
 diff#=dest#-source#
 If diff#<0 Then diff#=MakePositive#(diff#)
 Return diff#
End Function

;CALCULATE CENTRE (of any 2 numbers)
Function GetCentre#(source#,dest#)
 If source#<dest#
  centre#=source#+(GetDiff#(source#,dest#)/2)
 Else
  centre#=dest#+(GetDiff#(source#,dest#)/2)
 EndIf
 Return centre#
End Function

;CALCULATE DISTANCE (between any 2 sets of co-ordinates)
Function GetDistance#(sourceX#,sourceZ#,destX#,destZ#)
 diffX#=GetDiff#(sourceX#,destX#)
 diffZ#=GetDiff#(sourceZ#,destZ#)
 If diffX#>diffZ# Then distance#=diffX# Else distance#=diffZ#
 Return distance#
End Function

;CALCULATE HIGHEST VALUE
Function HighestValue#(valueA#,valueB#)
 highest#=valueA#
 If valueB#>valueA# Then highest#=valueB#
 Return highest#
End Function

;CALCULATE LOWEST VALUE
Function LowestValue#(valueA#,valueB#)
 lowest#=valueA#
 If valueB#<valueA# Then lowest#=valueB#
 Return lowest#
End Function

;SMOOTH TRAVELLING SPEEDS
Function GetSmoothSpeeds(x#,tX#,y#,tY#,z#,tZ#,factor)
 ;calculate differences & identify leader
 diffX#=GetDiff#(x#,tX#)
 lead#=diffX# : leader=1
 diffY#=GetDiff#(y#,tY#) 
 If diffY#>lead# Then lead#=diffY# : leader=2
 diffZ#=GetDiff#(z#,tZ#)
 If diffZ#>lead# Then lead#=diffZ# : leader=3
 ;make anchor speed from leading difference
 anchor#=lead#/factor
 ;calculate respective speeds
 If leader=1 Then speedX#=anchor# Else speedX#=anchor#*(diffX#/lead#)
 If leader=2 Then speedY#=anchor# Else speedY#=anchor#*(diffY#/lead#)
 If leader=3 Then speedZ#=anchor# Else speedZ#=anchor#*(diffZ#/lead#)
End Function

;FORCE MINUS INTO POSITIVE
Function MakePositive#(value#)
 If value#<0
  positive#=value#-(value#*2)
 Else
  positive#=value#
 EndIf
 Return positive#
End Function

;CALCULATE PERCENTAGE OF VALUE
Function PercentOf#(valueA#,percent#)
 valueB#=(valueA#/100)*percent#
 Return valueB#
End Function

;CALCULATE VALUE AS PERCENT
Function GetPercent#(valueA#,valueB#)
 percent#=0
 If valueB#>0 Then percent#=(valueA#/valueB#)*100
 Return percent#
End Function