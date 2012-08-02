;//////////////////////////////////////////////////////////////////////////////
;------------------------ WRESTLING MPIRE 2008: EDITOR ------------------------
;//////////////////////////////////////////////////////////////////////////////

;-------------------------------------------------------------------
;///////////////////// 51. EDIT CHARACTER //////////////////////////
;-------------------------------------------------------------------
Function Editor()
;loading
Loader("Please Wait","Loading Editor")
ResetTextures()
;camera
cam=CreateCamera()
CameraViewport cam,0,0,GraphicsWidth(),GraphicsHeight()
CameraZoom cam,1.5
CameraClsMode cam,0,1
camX#=-219 : camY#=35 : camZ#=-83
camXA#=3 : camYA#=119 : camZA#=0
If GraphicsWidth()>1024 Then camX#=-212 : camY#=35 : camZ#=-79
PositionEntity cam,camX#,camY#,camZ#
RotateEntity cam,camXA#,camYA#,camZA#
;atmosphere
AmbientLight 200,190,170
light(1)=CreateLight(3)
PositionEntity light(1),-145,100,-70
RotateEntity light(1),90,0,0
LightRange light(1),500
LightConeAngles light(1),0,135
LightColor light(1),220,200,180
;load model
If editChar=<no_chars Then CopyChar(editChar,0) : charFed(0)=charFed(editChar)
If editChar=no_chars+1 Then GenerateCharacter(0) : charFed(editChar)=fed : charFed(0)=charFed(editChar)
cyc=1 : pChar(cyc)=0 : pCostume(cyc)=1
pX#(cyc)=-257 : pY#(cyc)=11.5 : pZ#(cyc)=-124 
oldState=-1
LoadMuse(cyc)
LoadShadows(cyc)
PointEntity light(1),p(cyc) 
;initial character data
If screenAgenda=5
 GenerateCharacter(0) 
 charPopularity(0)=50
 charStrength(0)=50
 charSkill(0)=50
 charAgility(0)=50
 charStamina(0)=50
 charToughness(0)=50
 charHappiness(0)=75
 charAttitude(0)=75
 charWeapon(0)=0
 charManager(0)=0
 charWorth(0)=CalculateWorth(0,7)
 charSalary(0)=0
 charContract(0)=0
 charClause(0,1)=2
 charClause(0,2)=2
 charClause(0,3)=2
 charRole(0)=2
EndIf
;assess blocks
blockData=0 : blockName=0 : blockAllegiance=0
blockHair=0 : blockCostume=0 : blockEntrance=0 : blockWeapon=0
blockMoves=0 : blockAttacks=0 : blockTaunts=0
blockPartner=0 : blockManager=0
If game=1 And screenAgenda<>9
 If screenAgenda=5
  blockPartner=1 : blockManager=1
 Else
  blockData=1 : blockAllegiance=1
  If charClause(editChar,1)=2
   blockName=1 : blockEntrance=1
   blockHair=1 : blockCostume=1
   blockAttacks=1 : blockMoves=1 : blockTaunts=1
  EndIf
  If fedProduction(charFed(gamChar),3)=0 Then blockAttacks=1 : blockMoves=1
  If fedProduction(charFed(gamChar),4)=0 Then blockHair=1 : blockCostume=1
  If charAgreement(editChar,1)>0 Then blockName=1
  If charAgreement(editChar,2)>0 Then blockHair=1
  If charAgreement(editChar,3)>0 Then blockCostume=1
  If charAgreement(editChar,4)>0 Then blockEntrance=1
  If charAgreement(editChar,5)>0 Then blockAttacks=1
  If charAgreement(editChar,6)>0 Then blockMoves=1
  If charAgreement(editChar,7)>0 Then blockTaunts=1
  If charAgreement(editChar,8)>0 Then blockAllegiance=1 
  If charAgreement(editChar,14)>0 Then blockPartner=1
  If charAgreement(editChar,15)>0 Then blockManager=1 
 EndIf
EndIf
If game=1 And screenAgenda=9
 blockData=1 : blockName=1 : blockAllegiance=1
 blockHair=1 : blockCostume=1 : blockEntrance=1 : blockWeapon=1
 blockMoves=1 : blockAttacks=1 : blockTaunts=1
 blockPartner=1 : blockManager=1
EndIf 
;page properties
pageList(0)=11
pageList(1)=12
pageList(2)=14
pageList(3)=10
pageList(4)=13
pageList(5)=10
For count=6 To 8
 pageList(count)=16
Next
pageList(9)=13
page=0 : no_pages=9
foc=pageList(page) : oldfoc=foc
editThemeTest=0 : editExamine=0
;frame rating
timer=CreateTimer(30)
;MAIN LOOP
go=0 : gotim=0 : keytim=10
While go=0
	
 Cls
 screenCall=0
 frames=WaitTimer(timer)
 For framer=1 To frames
	
	;counters
	keytim=keytim-1
	If keytim<1 Then keytim=0
    flashTim=flashTim+1
	If flashTim>30 Then flashTim=0  
	
	;PORTAL 
	gotim=gotim+1
	If gotim>20 And keytim=0
	 ;cancellations
	 If KeyDown(1)
	  If page=0
	   go=-1
	  Else
	   PlaySound sMenuBack : keytim=10 : gotim=0
	   page=0 : foc=pageList(page)
	  EndIf
	 EndIf
	 ;activations
	 If KeyDown(28) Or ButtonPressed() Or (MouseDown(1) And MouseX()>rX#(400) And editExamine=0)
	  If page=0
	   If foc=pageList(page) Then go=1
	   If foc=>0 And foc=<8
	    PlaySound sMenuGo : keytim=10 : gotim=10
	    If foc>0 Then page=foc Else page=9
	    foc=pageList(page) 
	   EndIf
	   If page=0 And foc=9 Then go=2
	   If page=0 And foc=10 Then go=3
	   If go=>2 And go=<3 And (screenAgenda=5 Or screenAgenda=9) Then BlockAccess()
	  Else
	   If foc=pageList(page)
	    PlaySound sMenuBack : keytim=10 : gotim=10
	    page=0 : foc=pageList(page)
	   EndIf
	  EndIf
	 EndIf
	 ;paste moves from clipboard
	 If KeyDown(199) And editClipboard>0
	  PlaySound sFall : keytim=10 
	  For count=1 To 5
       charAttack(0,count)=charAttack(editClipboard,count)
       charCrush(0,count)=charCrush(editClipboard,count)
      Next
      For count=1 To 15
       charMove(0,count)=charMove(editClipboard,count)
      Next
      For count=1 To 6
       charGroundMove(0,count)=charGroundMove(editClipboard,count)
      Next
	 EndIf
	 ;generate character!
	 If KeyDown(56) And KeyDown(34)
	  PlaySound sSwing : keytim=10 
	  GenerateCharacter(0) 
	 EndIf
	EndIf
	
	;OPTION SELECTION
	If gotim>20 And keytim=0
	 ;grid browsing
	 If page=0  
	  If KeyDown(200) Or JoyYDir()=-1
	   newFoc=foc-1
	   If foc=0 Then newFoc=foc
	   If foc=1 Or foc=6 Then newFoc=0
	   If foc=11 Then newFoc=5  
	   foc=newFoc : keytim=5
	  EndIf
	  If KeyDown(208) Or JoyYDir()=1
	   newFoc=foc+1
	   If foc=0 Then newFoc=1
	   If foc=5 Or foc=10 Then newFoc=11
	   If foc=11 Then newFoc=foc
	   foc=newFoc : keytim=5
	  EndIf
	  If (KeyDown(203) Or JoyXDir()=-1) And foc<>11
	   If foc=>6 And foc=<10 Then newFoc=foc-5 Else newFoc=foc
	   foc=newFoc : keytim=5
	  EndIf
	  If (KeyDown(205) Or JoyXDir()=1) And foc<>11
	   If foc=>1 And foc=<5 Then newFoc=foc+5 Else newFoc=foc
	   foc=newFoc : keytim=5
	  EndIf
	  If foc<0 Then foc=pageList(page)
	  If foc>pageList(page) Then foc=0 
	 Else
	  ;list browsing
	  If KeyDown(200) Or JoyYDir()=-1 Then foc=foc-1 : keytim=5
	  If KeyDown(208) Or JoyYDir()=1 Then foc=foc+1 : keytim=5
      If foc<1 Then foc=pageList(page)
	  If foc>pageList(page) Then foc=1
     EndIf
    EndIf
	
	;CAREER POINTS
	If game=1 And screenAgenda=5 Then editPoints=325 Else editPoints=999
	editPoints=editPoints-charStrength(0)
	editPoints=editPoints-charSkill(0)
	editPoints=editPoints-charAgility(0) 
	editPoints=editPoints-charStamina(0)  
	editPoints=editPoints-charToughness(0)       
	If editPoints<0 Then editPoints=0
	
	;0. MENU SHORTCUTS
	If page=0 And keytim=0
	 ;costume cloning
	 source=0 : target=foc-5
	 If KeyDown(15) And foc=6 And blockCostume=0 Then source=2
	 If KeyDown(15) And foc=7 And blockCostume=0 Then source=1 
	 If KeyDown(15) And foc=8 And blockCostume=0 Then source=2 
	 If source>0
	  PlaySound sSwing : keytim=10
	  charBaggy(0,target)=charBaggy(0,source)
      charHatStyle(0,target)=charHatStyle(0,source)
      charHat(0,target)=charHat(0,source)
      charSpecs(0,target)=charSpecs(0,source)
      charBody(0,target)=charBody(0,source)
      charLeftArm(0,target)=charLeftArm(0,source)
      charRightArm(0,target)=charRightArm(0,source)
      charLeftForearm(0,target)=charLeftForearm(0,source)
      charRightForearm(0,target)=charRightForearm(0,source)
      charLeftHand(0,target)=charLeftHand(0,source)
      charRightHand(0,target)=charRightHand(0,source)
      charShorts(0,target)=charShorts(0,source)
      charLegs(0,target)=charLegs(0,source)
      charShins(0,target)=charShins(0,source) 
      charShoes(0,target)=charShoes(0,source)
	 EndIf
	 ;reset relationships
	 If KeyDown(14) Or KeyDown(211)
	  If foc=9 And blockData=0 And screenAgenda<>5 
	   PlaySound sTrash : keytim=10
	   For v=1 To no_chars
	    charRelationship(editChar,v)=0
	    charRelationship(v,editChar)=0
	   Next
	  EndIf
	  If foc=10 And blockData=0 And screenAgenda<>5
	   PlaySound sTrash : keytim=10
	   For v=1 To no_chars
	    charRealRelationship(editChar,v)=0
	    charRealRelationship(v,editChar)=0
	   Next
	  EndIf
	 EndIf
	 ;generate relationships
	 If KeyDown(57)
	  If foc=9 And blockData=0 And screenAgenda<>5
	   PlaySound sSwing : keytim=10
	   GenerateStoryRelationships(editChar)
	  EndIf
	  If foc=10 And blockData=0 And screenAgenda<>5
	   PlaySound sSwing : keytim=10
	   GenerateRealRelationships(editChar)
	  EndIf
	 EndIf
	EndIf
	
	;1. PROFILE
	If page=1 And blockData=0 And keytim=0
	 ;search left
	 If KeyDown(203) Or JoyXDir()=-1 Or (MouseDown(2) And MouseX()>rX#(400) And editExamine=0)
	  If foc=3 Then charAge(0)=charAge(0)-1 : PlaySound sMenuBrowse : keytim=3   
	  If foc=4 And screenAgenda<>5 Then charPopularity(0)=charPopularity(0)-1 : PlaySound sMenuBrowse : keytim=2
	  If foc=5 Then charStrength(0)=charStrength(0)-1 : PlaySound sMenuBrowse : keytim=2
      If foc=6 Then charSkill(0)=charSkill(0)-1 : PlaySound sMenuBrowse : keytim=2
      If foc=7 Then charAgility(0)=charAgility(0)-1 : PlaySound sMenuBrowse : keytim=2
      If foc=8 Then charStamina(0)=charStamina(0)-1 : PlaySound sMenuBrowse : keytim=2
      If foc=9 Then charToughness(0)=charToughness(0)-1 : PlaySound sMenuBrowse : keytim=2
      If foc=10 And screenAgenda<>5 Then charAttitude(0)=charAttitude(0)-1 : PlaySound sMenuBrowse : keytim=2
      If foc=11 And screenAgenda<>5 Then charHappiness(0)=charHappiness(0)-1 : PlaySound sMenuBrowse : keytim=2
	 EndIf
	 ;search right
	 If KeyDown(205) Or JoyXDir()=1 Or (MouseDown(1) And MouseX()>rX#(400) And editExamine=0)
	  If foc=3 Then charAge(0)=charAge(0)+1 : PlaySound sMenuBrowse : keytim=3  
	  If editPoints>0
	   If foc=4 And screenAgenda<>5 Then charPopularity(0)=charPopularity(0)+1 : PlaySound sMenuBrowse : keytim=2
	   If foc=5 Then charStrength(0)=charStrength(0)+1 : PlaySound sMenuBrowse : keytim=2
       If foc=6 Then charSkill(0)=charSkill(0)+1 : PlaySound sMenuBrowse : keytim=2
       If foc=7 Then charAgility(0)=charAgility(0)+1 : PlaySound sMenuBrowse : keytim=2
       If foc=8 Then charStamina(0)=charStamina(0)+1 : PlaySound sMenuBrowse : keytim=2
       If foc=9 Then charToughness(0)=charToughness(0)+1 : PlaySound sMenuBrowse : keytim=2
       If foc=10 And screenAgenda<>5 Then charAttitude(0)=charAttitude(0)+1 : PlaySound sMenuBrowse : keytim=2
       If foc=11 And screenAgenda<>5 Then charHappiness(0)=charHappiness(0)+1 : PlaySound sMenuBrowse : keytim=2
	  EndIf
	 EndIf   
	 ;randomize
	 If KeyDown(57)
	  PlaySound sMenuBrowse : keytim=5
	  If foc=3 Then charAge(0)=Rnd(16,50)
	  If game=0 Or screenAgenda<>5
	   If foc=4 Then charPopularity(0)=Rnd(50,90)
       If foc=5 Then charStrength(0)=Rnd(50,90)
       If foc=6 Then charSkill(0)=Rnd(50,90)
       If foc=7 Then charAgility(0)=Rnd(50,90)
       If foc=8 Then charStamina(0)=Rnd(50,90)
       If foc=9 Then charToughness(0)=Rnd(50,90)
       If foc=10 Then charAttitude(0)=Rnd(50,90)
       If foc=11 Then charHappiness(0)=Rnd(50,90)
      EndIf
	 EndIf 
	 ;revert
	 If KeyDown(14)
	  PlaySound sMenuBrowse : keytim=10
	  If foc=2 Then charTeamName$(0)=charName$(0)+"'s Team"
	  If foc=3 Then charAge(0)=21
	  If foc=4 Then charPopularity(0)=50
      If foc=5 Then charStrength(0)=50
      If foc=6 Then charSkill(0)=50
      If foc=7 Then charAgility(0)=50
      If foc=8 Then charStamina(0)=50
      If foc=9 Then charToughness(0)=50
      If foc=10 Then charAttitude(0)=50
      If foc=11 Then charHappiness(0)=50
	 EndIf  
	EndIf
	
	;2. GIMMICK
	oldTheme=charTheme(0)
	oldPitch=charThemePitch(0)
	If page=2 And keytim=0
	 ;search left
	 If KeyDown(203) Or JoyXDir()=-1 Or (MouseDown(2) And MouseX()>rX#(400) And editExamine=0)
	  If foc=1 And blockData=0 And screenAgenda<>5 Then charRole(0)=charRole(0)-1 : PlaySound sMenuBrowse : keytim=8
	  If foc=2 And blockAllegiance=0 Then charHeel(0)=charHeel(0)-1 : PlaySound sMenuBrowse : keytim=8
	  If foc=3 And blockTaunts=0 Then charStance(0)=charStance(0)-1 : PlaySound sMenuBrowse : keytim=8
	  If foc=>4 And foc=<7 And blockTaunts=0
	   PlaySound sMenuBrowse : keytim=5
	   Repeat
	    charTaunt(0,foc-3)=charTaunt(0,foc-3)-1
	    If charTaunt(0,foc-3)<1 Then charTaunt(0,foc-3)=no_taunts
       Until tauntLearned(charTaunt(0,foc-3))>0 Or game=0 Or screenAgenda=5
      EndIf
      If foc=8 And blockEntrance=0 
       PlaySound sMenuBrowse : keytim=5
       Repeat
        charTheme(0)=charTheme(0)-1
        If charTheme(0)<0 Then charTheme(0)=no_themes
       Until musicLearned(charTheme(0))>0 Or charTheme(0)=0 Or game=0 Or screenAgenda=5
      EndIf
      If foc=9 And blockEntrance=0 Then charThemePitch(0)=charThemePitch(0)-100 : PlaySound sMenuBrowse : keytim=2
      If foc=10 And blockEntrance=0 Then charLight(0)=charLight(0)-1 : PlaySound sMenuBrowse : keytim=5
      If foc=11 And blockWeapon=0 Then charWeapon(0)=charWeapon(0)-1 : PlaySound sMenuBrowse : keytim=5
      If foc=12 And blockPartner=0
       PlaySound sMenuBrowse : keytim=5 
       Repeat
        charPartner(0)=charPartner(0)-1
        If charPartner(0)<0 Then charPartner(0)=no_chars
	   Until (charPartner(0)<>editChar And charFed(charPartner(0))=charFed(editChar)) Or charPartner(0)=0
	  EndIf
      If foc=13 And blockManager=0
       PlaySound sMenuBrowse : keytim=5 
       Repeat
        charManager(0)=charManager(0)-1
        If charManager(0)<0 Then charManager(0)=no_chars
	   Until (charManager(0)<>editChar And charFed(charManager(0))=charFed(editChar)) Or charManager(0)=0
	  EndIf
	 EndIf
	 ;search right
	 If KeyDown(205) Or JoyXDir()=1 Or (MouseDown(1) And MouseX()>rX#(400) And editExamine=0)
	  If foc=1 And blockData=0 And screenAgenda<>5 Then charRole(0)=charRole(0)+1 : PlaySound sMenuBrowse : keytim=8
	  If foc=2 And blockAllegiance=0 Then charHeel(0)=charHeel(0)+1 : PlaySound sMenuBrowse : keytim=8
	  If foc=3 And blockTaunts=0 Then charStance(0)=charStance(0)+1 : PlaySound sMenuBrowse : keytim=8
	  If foc=>4 And foc=<7 And blockTaunts=0
	   PlaySound sMenuBrowse : keytim=5
	   Repeat
	    charTaunt(0,foc-3)=charTaunt(0,foc-3)+1
	    If charTaunt(0,foc-3)>no_taunts Then charTaunt(0,foc-3)=1
       Until tauntLearned(charTaunt(0,foc-3))>0 Or game=0 Or screenAgenda=5
      EndIf
      If foc=8 And blockEntrance=0 
       PlaySound sMenuBrowse : keytim=5
       Repeat
        charTheme(0)=charTheme(0)+1
        If charTheme(0)>no_themes Then charTheme(0)=0
       Until musicLearned(charTheme(0))>0 Or charTheme(0)=0 Or game=0 Or screenAgenda=5
      EndIf
      If foc=9 And blockEntrance=0 Then charThemePitch(0)=charThemePitch(0)+100 : PlaySound sMenuBrowse : keytim=2
      If foc=10 And blockEntrance=0 Then charLight(0)=charLight(0)+1 : PlaySound sMenuBrowse : keytim=5
      If foc=11 And blockWeapon=0 Then charWeapon(0)=charWeapon(0)+1 : PlaySound sMenuBrowse : keytim=5
      If foc=12 And blockPartner=0
       PlaySound sMenuBrowse : keytim=5 
       Repeat
        charPartner(0)=charPartner(0)+1
        If charPartner(0)>no_chars Then charPartner(0)=0
	   Until (charPartner(0)<>editChar And charFed(charPartner(0))=charFed(editChar)) Or charPartner(0)=0
	  EndIf
      If foc=13 And blockManager=0
       PlaySound sMenuBrowse : keytim=5 
       Repeat
        charManager(0)=charManager(0)+1
        If charManager(0)>no_chars Then charManager(0)=0
	   Until (charManager(0)<>editChar And charFed(charManager(0))=charFed(editChar)) Or charManager(0)=0
	  EndIf
	 EndIf 
	 ;reciprocate relationships
	 If KeyDown(19) Or KeyDown(15) 
	  If foc=12 And charPartner(0)>0 And blockPartner=0 Then charPartner(charPartner(0))=editChar : PlaySound sSwing : keytim=10
	  If foc=13 And charManager(0)>0 And blockManager=0 Then charManager(charManager(0))=editChar : PlaySound sSwing : keytim=10
	 EndIf
	 ;randomize
	 If KeyDown(57)
	  PlaySound sMenuBrowse : keytim=5
	  If foc=1 And blockData=0 And screenAgenda<>5 Then charRole(0)=Rnd(1,3)
	  If foc=2 And blockAllegiance=0 Then charHeel(0)=Rnd(0,1)
	  If foc=3 And blockTaunts=0 Then charStance(0)=Rnd(0,no_stances)
	  If foc=>4 And foc=<7 And blockTaunts=0
	   PlaySound sMenuBrowse : keytim=5
	   Repeat
	    charTaunt(0,foc-3)=Rnd(1,no_taunts)
       Until tauntLearned(charTaunt(0,foc-3))>0 Or game=0 Or screenAgenda=5
      EndIf
      If foc=8 And blockEntrance=0 
       PlaySound sMenuBrowse : keytim=5
       charTheme(0)=Rnd(0,no_themes)
      EndIf
      If foc=9 And blockEntrance=0 Then charThemePitch(0)=22050
      If foc=10 And blockEntrance=0 Then charLight(0)=Rnd(0,no_lightshows)
      If foc=11 And blockWeapon=0 Then charWeapon(0)=Rnd(1,weapList)
      If foc=12 And blockPartner=0
       Repeat
        charPartner(0)=Rnd(0,no_chars)
	   Until (charPartner(0)<>editChar And charFed(charPartner(0))=charFed(editChar)) Or charPartner(0)=0
	  EndIf
      If foc=13 And blockManager=0
       Repeat
        charManager(0)=Rnd(0,no_chars)
	   Until (charManager(0)<>editChar And charFed(charManager(0))=charFed(editChar)) Or charManager(0)=0
	  EndIf
	 EndIf
	 ;revert
	 If KeyDown(14)
	  PlaySound sMenuBrowse : keytim=10
	  If foc=1 And blockData=0 And screenAgenda<>5 Then charRole(0)=1
	  If foc=2 And blockAllegiance=0 Then charHeel(0)=0
	  If foc=3 And blockTaunts=0 Then charStance(0)=0
	  If foc=>4 And foc=<7 And blockTaunts=0
	   If tauntLearned(1)>0 Or game=0 Or screenAgenda=5 Then charTaunt(0,foc-3)=1
	  EndIf
      If foc=8 And blockEntrance=0 Then charTheme(0)=0
      If foc=9 And blockEntrance=0 Then charThemePitch(0)=22050
      If foc=10 And blockEntrance=0 Then charLight(0)=1
      If foc=11 And blockWeapon=0 Then charWeapon(0)=0
      If foc=12 And blockPartner=0 Then charPartner(0)=0
      If foc=13 And blockManager=0 Then charManager(0)=0
	 EndIf 
	EndIf
	
	;3. ATTACKS
	If page=3 And blockAttacks=0 And keytim=0
     ;search left
	 If KeyDown(203) Or JoyXDir()=-1 Or (MouseDown(2) And MouseX()>rX#(400) And editExamine=0) 
	  PlaySound sMenuBrowse : keytim=5
	  For count=1 To 5
	   If foc=count Then charAttack(0,count)=charAttack(0,count)-1
	  Next
	  For count=2 To 5
	   If foc=count+4 Then charCrush(0,count)=charCrush(0,count)-1
	  Next
	 EndIf
	 ;search right
	 If KeyDown(205) Or JoyXDir()=1 Or (MouseDown(1) And MouseX()>rX#(400) And editExamine=0)
	  PlaySound sMenuBrowse : keytim=5
	  For count=1 To 5
	   If foc=count Then charAttack(0,count)=charAttack(0,count)+1
	  Next
	  For count=2 To 5
	   If foc=count+4 Then charCrush(0,count)=charCrush(0,count)+1
	  Next
	 EndIf
	 ;randomize
	 If KeyDown(57)
	  PlaySound sMenuBrowse : keytim=5
	  For count=1 To 5
	   If foc=count Then charAttack(0,count)=Rnd(1,attackList(count))
	  Next
	  For count=2 To 5
	   If foc=count+4 Then charCrush(0,count)=Rnd(1,crushList(count))
	  Next
	 EndIf 
	 ;revert
	 If KeyDown(14)
	  PlaySound sMenuBrowse : keytim=10
	  For count=1 To 5
	   If foc=count Then charAttack(0,count)=1
	  Next
	  For count=2 To 5
	   If foc=count+4 Then charCrush(0,count)=1
	  Next
	 EndIf
	EndIf
	
	;4. MAIN MOVES
	If page=4 And blockMoves=0 And keytim=0
     ;search left
	 If KeyDown(203) Or JoyXDir()=-1 Or (MouseDown(2) And MouseX()>rX#(400) And editExamine=0) 
	  PlaySound sMenuBrowse : keytim=5
	  For count=1 To 12
	   If foc=count Then charMove(0,count)=charMove(0,count)-1
	  Next
	 EndIf
	 ;search right
	 If KeyDown(205) Or JoyXDir()=1 Or (MouseDown(1) And MouseX()>rX#(400) And editExamine=0)
	  PlaySound sMenuBrowse : keytim=5
	  For count=1 To 12
	   If foc=count Then charMove(0,count)=charMove(0,count)+1
	  Next
	 EndIf
	 ;randomize
	 If KeyDown(57)
	  PlaySound sMenuBrowse : keytim=5
	  For count=1 To 12
	   If foc=count Then charMove(0,count)=Rnd(1,moveList(1))
	  Next
	 EndIf
	 ;revert
	 If KeyDown(14)
	  PlaySound sMenuBrowse : keytim=10
	  For count=1 To 12
	   If foc=count Then charMove(0,count)=7
	  Next
	 EndIf
	EndIf 
	
	;5. ADDITIONAL MOVES
	If page=5 And blockMoves=0 And keytim=0
     ;search left
	 If KeyDown(203) Or JoyXDir()=-1 Or (MouseDown(2) And MouseX()>rX#(400) And editExamine=0)  
	  PlaySound sMenuBrowse : keytim=5
	  For count=1 To 3
	   If foc=count Then charGroundMove(0,count)=charGroundMove(0,count)-1 
	  Next
	  For count=4 To 6
	   If foc=count Then charGroundMove(0,count)=charGroundMove(0,count)-1 
	  Next
	  If foc=7 Or foc=9 Then charMove(0,foc+6)=charMove(0,foc+6)-1 
	  If foc=8 Then charMove(0,14)=charMove(0,14)-1 
	 EndIf
	 ;search right
	 If KeyDown(205) Or JoyXDir()=1 Or (MouseDown(1) And MouseX()>rX#(400) And editExamine=0)
	  PlaySound sMenuBrowse : keytim=5
      For count=1 To 3
	   If foc=count Then charGroundMove(0,count)=charGroundMove(0,count)+1 
	  Next
	  For count=4 To 6
	   If foc=count Then charGroundMove(0,count)=charGroundMove(0,count)+1 
	  Next
	  If foc=7 Or foc=9 Then charMove(0,foc+6)=charMove(0,foc+6)+1 
	  If foc=8 Then charMove(0,14)=charMove(0,14)+1 
	 EndIf
	 ;randomize
	 If KeyDown(57)
	  PlaySound sMenuBrowse : keytim=5
	  For count=1 To 3
	   If foc=count Then charGroundMove(0,count)=Rnd(1,moveList(3))
	  Next
	  For count=4 To 6
	   If foc=count Then charGroundMove(0,count)=Rnd(1,moveList(4))
	  Next
	  If foc=7 Or foc=9 Then charMove(0,foc+6)=Rnd(1,moveList(1))
	  If foc=8 Then charMove(0,14)=Rnd(1,moveList(2))
	 EndIf
	 ;revert
	 If KeyDown(14)
	  PlaySound sMenuBrowse : keytim=10
	  For count=1 To 3
	   If foc=count Then charGroundMove(0,count)=1
	   If foc=count+3 Then charGroundMove(0,count+3)=1
	  Next
	  If foc=7 Then charMove(0,13)=moveList(1)
	  If foc=8 Then charMove(0,14)=1
	  If foc=9 Then charMove(0,15)=moveList(1)
	 EndIf
	EndIf 
	
	;6-8. COSTUMES
	If charRole(0)<1 Then charRole(0)=3
	If charRole(0)>3 Then charRole(0)=1
	pCostume(cyc)=charRole(0) 
	If page=0 And foc=>6 And foc=<8 Then pCostume(cyc)=foc-5
	If page=>6 And page=<8 Then pCostume(cyc)=page-5
	coz=pCostume(cyc)
	oldHeadwear=charHatStyle(0,coz)  
	If page=>6 And page=<8 And blockCostume=0 And keytim=0
	 ;search left
	 If KeyDown(203) Or JoyXDir()=-1 Or (MouseDown(2) And MouseX()>rX#(400) And editExamine=0)
	  If foc=1 Then charBaggy(0,coz)=charBaggy(0,coz)-1 : PlaySound sMenuBrowse : keytim=8
	  If foc=2 Then charHatStyle(0,coz)=charHatStyle(0,coz)-1 : PlaySound sMenuBrowse : keytim=5
	  If foc=3 Then charHat(0,coz)=charHat(0,coz)-1 : PlaySound sMenuBrowse : keytim=5
	  If foc=4 Then charSpecs(0,coz)=charSpecs(0,coz)-1 : PlaySound sMenuBrowse : keytim=5
      If foc=5 Then charBody(0,coz)=charBody(0,coz)-1 : PlaySound sMenuBrowse : keytim=5
      If foc=6
       PlaySound sMenuBrowse : keytim=5
       charRightArm(0,coz)=charRightArm(0,coz)-1 : charRightForearm(0,coz)=charRightArm(0,coz)
       charLeftArm(0,coz)=charRightArm(0,coz) : charLeftForearm(0,coz)=charLeftArm(0,coz)
      EndIf
      If foc=7
       PlaySound sMenuBrowse : keytim=5 
       charRightForearm(0,coz)=charRightForearm(0,coz)-1 : charLeftForearm(0,coz)=charRightForearm(0,coz)
      EndIf
      If foc=8
       PlaySound sMenuBrowse : keytim=5
       charRightHand(0,coz)=charRightHand(0,coz)-1 : charLeftHand(0,coz)=charRightHand(0,coz)
      EndIf
      If foc=9
       PlaySound sMenuBrowse : keytim=5
       charLeftArm(0,coz)=charLeftArm(0,coz)-1 : charLeftForearm(0,coz)=charLeftArm(0,coz)
      EndIf
      If foc=10 Then charLeftForearm(0,coz)=charLeftForearm(0,coz)-1 : PlaySound sMenuBrowse : keytim=5
      If foc=11 Then charLeftHand(0,coz)=charLeftHand(0,coz)-1 : PlaySound sMenuBrowse : keytim=5
      If foc=12
       PlaySound sMenuBrowse : keytim=5
       Repeat
        charShorts(0,coz)=charShorts(0,coz)-1
        If charShorts(0,coz)<0 Then charShorts(0,coz)=no_legs
       Until ShortsViable(charShorts(0,coz))
      EndIf
      If foc=13
       PlaySound sMenuBrowse : keytim=5
       charLegs(0,coz)=charLegs(0,coz)-1
	   charShins(0,coz)=charLegs(0,coz) : charShoes(0,coz)=charLegs(0,coz)
      EndIf
	  If foc=14 Then charShins(0,coz)=charShins(0,coz)-1 : charShoes(0,coz)=charShins(0,coz) : PlaySound sMenuBrowse : keytim=5
      If foc=15 Then charShoes(0,coz)=charShoes(0,coz)-1 : PlaySound sMenuBrowse : keytim=5
	 EndIf
	 ;search right
	 If KeyDown(205) Or JoyXDir()=1 Or (MouseDown(1) And MouseX()>rX#(400) And editExamine=0)
	  If foc=1 Then charBaggy(0,coz)=charBaggy(0,coz)+1 : PlaySound sMenuBrowse : keytim=8
	  If foc=2 Then charHatStyle(0,coz)=charHatStyle(0,coz)+1 : PlaySound sMenuBrowse : keytim=5
	  If foc=3 Then charHat(0,coz)=charHat(0,coz)+1 : PlaySound sMenuBrowse : keytim=5
	  If foc=4 Then charSpecs(0,coz)=charSpecs(0,coz)+1 : PlaySound sMenuBrowse : keytim=5
      If foc=5 Then charBody(0,coz)=charBody(0,coz)+1 : PlaySound sMenuBrowse : keytim=5
      If foc=6
       PlaySound sMenuBrowse : keytim=5
       charRightArm(0,coz)=charRightArm(0,coz)+1 : charRightForearm(0,coz)=charRightArm(0,coz)
       charLeftArm(0,coz)=charRightArm(0,coz) : charLeftForearm(0,coz)=charLeftArm(0,coz)
      EndIf
      If foc=7
       PlaySound sMenuBrowse : keytim=5 
       charRightForearm(0,coz)=charRightForearm(0,coz)+1 : charLeftForearm(0,coz)=charRightForearm(0,coz)
      EndIf
      If foc=8
       PlaySound sMenuBrowse : keytim=5
       charRightHand(0,coz)=charRightHand(0,coz)+1 : charLeftHand(0,coz)=charRightHand(0,coz)
      EndIf
      If foc=9
       PlaySound sMenuBrowse : keytim=5
       charLeftArm(0,coz)=charLeftArm(0,coz)+1 : charLeftForearm(0,coz)=charLeftArm(0,coz)
      EndIf
      If foc=10 Then charLeftForearm(0,coz)=charLeftForearm(0,coz)+1 : PlaySound sMenuBrowse : keytim=5
      If foc=11 Then charLeftHand(0,coz)=charLeftHand(0,coz)+1 : PlaySound sMenuBrowse : keytim=5
      If foc=12
       PlaySound sMenuBrowse : keytim=5
       Repeat
        charShorts(0,coz)=charShorts(0,coz)+1
        If charShorts(0,coz)<0 Then charShorts(0,coz)=no_legs
       Until ShortsViable(charShorts(0,coz))
      EndIf
      If foc=13
       PlaySound sMenuBrowse : keytim=5
       charLegs(0,coz)=charLegs(0,coz)+1
	   charShins(0,coz)=charLegs(0,coz) : charShoes(0,coz)=charLegs(0,coz)
      EndIf
	  If foc=14 Then charShins(0,coz)=charShins(0,coz)+1 : charShoes(0,coz)=charShins(0,coz) : PlaySound sMenuBrowse : keytim=5
      If foc=15 Then charShoes(0,coz)=charShoes(0,coz)+1 : PlaySound sMenuBrowse : keytim=5
	 EndIf 
	 ;randomize
	 If KeyDown(57)
	  PlaySound sMenuBrowse : keytim=5
	  If foc=1 Then charBaggy(0,coz)=Rnd(0,3)
	  If foc=2 Then charHatStyle(0,coz)=Rnd(0,no_hatstyles)
	  If foc=3 Then charHat(0,coz)=Rnd(1,no_hats)
	  If foc=4 Then charSpecs(0,coz)=Rnd(1,no_specs)
      If foc=5 Then charBody(0,coz)=Rnd(1,no_bodies)
      If foc=6
       charRightArm(0,coz)=Rnd(1,no_arms) : charRightForearm(0,coz)=charRightArm(0,coz)
       charLeftArm(0,coz)=charRightArm(0,coz) : charLeftForearm(0,coz)=charLeftArm(0,coz)
      EndIf
      If foc=7 Then charRightForearm(0,coz)=Rnd(1,no_arms) : charLeftForearm(0,coz)=charRightForearm(0,coz)
      If foc=8 Then charRightHand(0,coz)=Rnd(1,no_hands) : charLeftHand(0,coz)=charRightHand(0,coz)
      If foc=9 Then charLeftArm(0,coz)=Rnd(1,no_arms) : charLeftForearm(0,coz)=charLeftArm(0,coz)
      If foc=10 Then charLeftForearm(0,coz)=Rnd(1,no_arms)
	  If foc=11 Then charLeftHand(0,coz)=Rnd(1,no_hands)
      If foc=12
       Repeat
        charShorts(0,coz)=Rnd(0,no_legs)
       Until ShortsViable(charShorts(0,coz))
      EndIf
      If foc=13 Then charLegs(0,coz)=Rnd(1,no_legs) : charShins(0,coz)=charLegs(0,coz) : charShoes(0,coz)=charLegs(0,coz)
      If foc=14 Then charShins(0,coz)=Rnd(1,no_legs) : charShoes(0,coz)=charShins(0,coz)
      If foc=15 Then charShoes(0,coz)=Rnd(1,no_legs)
	 EndIf  
	 ;revert
	 If KeyDown(14)
	  PlaySound sMenuBrowse : keytim=10 
	  If foc=1 Then charBaggy(0,coz)=0
	  If foc=2 Then charHatStyle(0,coz)=0
	  If foc=3 Then charHat(0,coz)=1
	  If foc=4 Then charSpecs(0,coz)=0
      If foc=5 Then charBody(0,coz)=1
      If foc=6
       charRightArm(0,coz)=1 : charRightForearm(0,coz)=charRightArm(0,coz)
       charLeftArm(0,coz)=charRightArm(0,coz) : charLeftForearm(0,coz)=charLeftArm(0,coz)
      EndIf
      If foc=7 Then charRightForearm(0,coz)=charRightArm(0,coz)
      If foc=8 Then charRightHand(0,coz)=1+charGender(0) : charLeftHand(0,coz)=charRightHand(0,coz)
      If foc=9 Then charLeftArm(0,coz)=1 : charLeftForearm(0,coz)=charLeftArm(0,coz)
      If foc=10 Then charLeftForearm(0,coz)=charLeftArm(0,coz)
	  If foc=11 Then charLeftHand(0,coz)=charRightHand(0,coz)
      If foc=12 Then charShorts(0,coz)=0
      If foc=13 Then charLegs(0,coz)=1 : charShins(0,coz)=charLegs(0,coz) : charShoes(0,coz)=charLegs(0,coz)
      If foc=14 Then charShins(0,coz)=charLegs(0,coz) : charShoes(0,coz)=charShins(0,coz)
      If foc=15 Then charShoes(0,coz)=charLegs(0,coz)
	 EndIf  
	EndIf  
	
	;9. APPEARANCE
	If page=9 And keytim=0
	 ;search left
	 If KeyDown(203) Or JoyXDir()=-1 Or (MouseDown(2) And MouseX()>rX#(400) And editExamine=0)
	  If foc=1 And blockData=0 Then charGender(0)=charGender(0)-1 : PlaySound sMenuBrowse : keytim=8 
	  If foc=2 And blockData=0 Then charHeight(0)=charHeight(0)-1 : PlaySound sMenuBrowse : keytim=5
	  If foc=3 And blockData=0 Then charWeight(0)=charWeight(0)-1 : PlaySound sMenuBrowse : keytim=2  
	  If foc=4 And blockHair=0 Then charHairStyle(0,coz)=charHairStyle(0,coz)-1 : PlaySound sMenuBrowse : keytim=5
      If foc=5 And blockHair=0 Then charHair(0,coz)=charHair(0,coz)-1 : PlaySound sMenuBrowse : keytim=5
      If foc=6 And blockData=0 Then charFace(0,coz)=charFace(0,coz)-1 : PlaySound sMenuBrowse : keytim=5
      If foc=7 And blockCostume=0 Then charBeard(0,coz)=charBeard(0,coz)-1 : PlaySound sMenuBrowse : keytim=5
      If foc=8 And blockAllegiance=0 Then charEyes(0)=charEyes(0)-1 : PlaySound sMenuBrowse : keytim=8
      If foc=9 And blockData=0 Then charEyeballs(0)=charEyeballs(0)-1 : PlaySound sMenuBrowse : keytim=5
      If charEyeShape(0)=<100 Then range=10 Else range=1
      If foc=10 And blockData=0 Then charEyeShape(0)=charEyeShape(0)-range : PlaySound sMenuBrowse : keytim=5
      If foc=11 And blockData=0 Then charHeadSize(0)=charHeadSize(0)-1 : PlaySound sMenuBrowse : keytim=5
      If foc=12 And blockCostume=0 Then charTattoos(0)=charTattoos(0)-1 : PlaySound sMenuBrowse : keytim=5
     EndIf
	 ;search right
	 If KeyDown(205) Or JoyXDir()=1 Or (MouseDown(1) And MouseX()>rX#(400) And editExamine=0)
	  If foc=1 And blockData=0 Then charGender(0)=charGender(0)+1 : PlaySound sMenuBrowse : keytim=8 
	  If foc=2 And blockData=0 Then charHeight(0)=charHeight(0)+1 : PlaySound sMenuBrowse : keytim=5
	  If foc=3 And blockData=0 Then charWeight(0)=charWeight(0)+1 : PlaySound sMenuBrowse : keytim=2  
	  If foc=4 And blockHair=0 Then charHairStyle(0,coz)=charHairStyle(0,coz)+1 : PlaySound sMenuBrowse : keytim=5
      If foc=5 And blockHair=0 Then charHair(0,coz)=charHair(0,coz)+1 : PlaySound sMenuBrowse : keytim=5
      If foc=6 And blockData=0 Then charFace(0,coz)=charFace(0,coz)+1 : PlaySound sMenuBrowse : keytim=5
      If foc=7 And blockCostume=0 Then charBeard(0,coz)=charBeard(0,coz)+1 : PlaySound sMenuBrowse : keytim=5
      If foc=8 And blockAllegiance=0 Then charEyes(0)=charEyes(0)+1 : PlaySound sMenuBrowse : keytim=8
      If foc=9 And blockData=0 Then charEyeballs(0)=charEyeballs(0)+1 : PlaySound sMenuBrowse : keytim=5
      If charEyeShape(0)<100 Then range=10 Else range=1
      If foc=10 And blockData=0 Then charEyeShape(0)=charEyeShape(0)+range : PlaySound sMenuBrowse : keytim=5
      If foc=11 And blockData=0 Then charHeadSize(0)=charHeadSize(0)+1 : PlaySound sMenuBrowse : keytim=5
      If foc=12 And blockCostume=0 Then charTattoos(0)=charTattoos(0)+1 : PlaySound sMenuBrowse : keytim=5
	 EndIf 
	 ;randomize
	 If KeyDown(57)
	  PlaySound sMenuBrowse : keytim=5
	  If foc=1 And blockData=0 Then charGender(0)=Rnd(0,1)
	  If foc=2 And blockData=0 Then charHeight(0)=Rnd(0,28)
	  If foc=3 And blockData=0 Then charWeight(0)=Rnd(75,250)
	  If foc=4 And blockHair=0 Then charHairStyle(0,coz)=Rnd(0,no_hairstyles)
      If foc=5 And blockHair=0 Then charHair(0,coz)=Rnd(1,no_hairs)
      If foc=6 And blockData=0 Then charFace(0,coz)=Rnd(1,no_faces)
      If foc=7 And blockCostume=0 Then charBeard(0,coz)=Rnd(0,no_beards) 
      If foc=8 And blockAllegiance=0 Then charEyes(0)=Rnd(1,3)
      If foc=9 And blockData=0 Then charEyeballs(0)=Rnd(1,no_eyes)
      If foc=10 And blockData=0 Then randy=Rnd(2,11) : charEyeShape(0)=randy*10
      If foc=11 And blockData=0 Then charHeadSize(0)=Rnd(80,110) 
      If foc=12 And blockCostume=0 Then charTattoos(0)=Rnd(0,no_tattoos)
     EndIf  
	 ;revert
	 If KeyDown(14)
	  PlaySound sMenuBrowse : keytim=10 
	  If foc=1 And blockData=0 Then charGender(0)=0
	  If foc=2 And blockData=0 Then charHeight(0)=12
	  If foc=3 And blockData=0 Then charWeight(0)=125
	  If foc=4 And blockHair=0 Then charHairStyle(0,coz)=0
      If foc=5 And blockHair=0 Then charHair(0,coz)=1
      If foc=6 And blockData=0 Then charFace(0,coz)=1
      If foc=7 And blockCostume=0 Then charBeard(0,coz)=0
      If foc=8 And blockAllegiance=0 Then charEyes(0)=2
      If foc=9 And blockData=0 Then charEyeballs(0)=2
      If foc=10 And blockData=0 Then charEyeShape(0)=0
      If foc=11 And blockData=0 Then charHeadSize(0)=100
      If foc=12 And blockCostume=0 Then charTattoos(0)=0
	 EndIf 
	 ;refresh model
	 If KeyDown(28) Or ButtonPressed()
	  If (foc=1 Or foc=3) And GetModel(0)<>editModel Then screenCall=1 : PlaySound sMenuGo : keytim=10
	 EndIf  
	EndIf 
	
    ;UPDATE MODEL
	;check limits
	CheckLimits(0)
	;starting limitations
	If screenAgenda=5
	 charPopularity(0)=50
	 charAttitude(0)=75
	 charHappiness(0)=75 
	 charPartner(0)=0
	 charManager(0)=0
	EndIf
	;consistency
	For count=1 To 3
	 charHairStyle(0,count)=charHairStyle(0,pCostume(cyc))
	 charHair(0,count)=charHair(0,pCostume(cyc))
	 charFace(0,count)=charFace(0,pCostume(cyc))
	 charBeard(0,count)=charBeard(0,pCostume(cyc)) 
	Next 
	;switch to ideal headwear
	If charHatStyle(0,coz)<>oldHeadwear
	 If charHatStyle(0,coz)=1 Or charHatStyle(0,coz)=10
	  If charHat(0,coz)<13 Then charHat(0,coz)=13
	 Else 
	  If charHat(0,coz)=>13 Then charHat(0,coz)=1
	 EndIf
	EndIf
	;update costume
	ApplyCostume(cyc)
	EntityTexture FindChild(p(cyc),"Head"),tEyes(charEyes(0)),0,3  
	;scale
	scaler#=0.055+(GetPercent#(charHeight(0),24)/10000)
    ScaleEntity p(cyc),scaler#,scaler#,scaler# 
    ;orientation
    If MouseX()>rX#(120) And MouseX()<rX#(300) And MouseY()>rY#(100) And MouseY()<rY#(550)
     If (MouseDown(1) Or MouseDown(2)) And editExamine=0 Then PlaySound sMenuSelect : editExamine=1 : void=MouseXSpeed()
    EndIf
    If MouseDown(1)=0 And MouseDown(2)=0 And editExamine=1 Then PlaySound sMenuSelect : editExamine=0
    If editExamine=1
     TurnEntity p(cyc),0,MouseXSpeed()+360,0
    Else
     RotateEntity p(cyc),0,345,0
    EndIf
	;shadows
	For limb=1 To 50
     If pShadow(cyc,limb)>0
      RotateEntity pShadow(cyc,limb),90,EntityYaw(pLimb(cyc,limb),1),0
      PositionEntity pShadow(cyc,limb),EntityX(pLimb(cyc,limb),1),pY#(cyc)+0.4,EntityZ(pLimb(cyc,limb),1)
      If go>0 Then EntityAlpha pShadow(cyc,limb),0
     EndIf
    Next  
    ;weapon preview
    For v=1 To weapList
     HideEntity FindChild(p(cyc),weapFile$(v))
    Next
    If page=2 And foc=11 And charWeapon(0)>0
     v=charWeapon(0)
     If weapFile$(v)="Bottle" Then EntityAlpha FindChild(p(cyc),weapFile$(v)),0.75
     If weapTex(v)>0 Then EntityTexture FindChild(p(cyc),weapFile$(v)),weapTex(v)
     EntityShininess FindChild(p(cyc),weapFile$(v)),weapShine#(v)
     ShowEntity FindChild(p(cyc),weapFile$(v))
    EndIf
    
    ;ANIMATIONS
    ;get required state
    oldState=pState(cyc)
    pState(cyc)=pCostume(cyc)
    If pState(cyc)=1 Then pState(cyc)=10+charStance(0)
    If page=0 And foc=>7 And foc=<8 Then pState(cyc)=foc-5
    If page=2 And foc=3 Then pState(cyc)=20+charStance(0)
    If page=2 And foc=>4 And foc=<7 Then pState(cyc)=100+charTaunt(0,foc-3)
    If page=2 And foc=11 And charWeapon(0)>0
     If weapHold(charWeapon(0))=3 Then pState(cyc)=4 Else pState(cyc)=2
    EndIf
    If page=3 And foc=>1 And foc=<5 Then pState(cyc)=(180+(foc*30))+charAttack(0,foc)
    If page=3 And foc=>6 And foc=<9 Then pState(cyc)=(380+((foc-4)*20))+charCrush(0,foc-4)
    If pState(cyc)=2 And stanceType(charStance(0))=0 Then pState(cyc)=10+charStance(0)
    If pState(cyc)=>2 And pState(cyc)=<3 And charGender(0)=1 Then pState(cyc)=16
    ;trigger new stance
    If pState(cyc)<>oldState Then pAnimTim(cyc)=0
    If pState(cyc)<>oldState And pState(cyc)<30
     If pState(cyc)=>20 And pState(cyc)<>21 And pState(cyc)<>26
      pSpeed#(cyc)=0.1+(Float(charAgility(0))/125)
      If pSpeed#(cyc)<0.2 Then pSpeed#(cyc)=0.2
      Animate p(cyc),1,pSpeed#(cyc)*2,pSeq(cyc,pState(cyc)),10
     Else
      Animate p(cyc),1,Rnd#(0.1,0.5),pSeq(cyc,pState(cyc)),10
     EndIf
    EndIf
    ;taunt sequences
    If pState(cyc)=>101 And pState(cyc)=<200
     If pState(cyc)<>oldState
      If tauntLoop(pState(cyc)-100)=0 Then Animate p(cyc),3,tauntSpeed#(pState(cyc)-100),pSeq(cyc,pState(cyc)),tauntTransition(pState(cyc)-100)
      If tauntLoop(pState(cyc)-100)=1 Then Animate p(cyc),1,tauntSpeed#(pState(cyc)-100),pSeq(cyc,pState(cyc)),tauntTransition(pState(cyc)-100)
     EndIf
     If pAnimTim(cyc)>tauntLength(pState(cyc)-100)/tauntSpeed#(pState(cyc)-100) And tauntLoop(pState(cyc)-100)=0 Then pState(cyc)=1
    EndIf 
    ;attack sequences
    If pState(cyc)=>201 And pState(cyc)=<400
     For count=1 To 5
      move=180+(count*30)
      If pState(cyc)-move=>1 And pState(cyc)-move=<attackList(count)
       If pState(cyc)<>oldState Then Animate p(cyc),3,PercentOf#(attackSpeed#(count,pState(cyc)-move),75),pSeq(cyc,pState(cyc)),attackTransition(count,pState(cyc)-move)/PercentOf#(attackSpeed#(count,pState(cyc)-move),75)
       If pAnimTim(cyc)>attackLength(count,pState(cyc)-move)/PercentOf#(attackSpeed#(count,pState(cyc)-move),75) Then pState(cyc)=1
      EndIf
     Next
    EndIf
    ;crush sequences
    If pState(cyc)=>401 And pState(cyc)=<500
     For count=2 To 5
      move=380+(count*20)
      If pState(cyc)-move=>1 And pState(cyc)-move=<crushList(count)
       If pState(cyc)<>oldState Then Animate p(cyc),3,PercentOf#(crushSpeed#(count,pState(cyc)-move),75),pSeq(cyc,pState(cyc)),crushTransition(count,pState(cyc)-move)/PercentOf#(crushSpeed#(count,pState(cyc)-move),75)
       If pAnimTim(cyc)>crushLength(count,pState(cyc)-move)/PercentOf#(crushSpeed#(count,pState(cyc)-move),75) Then pState(cyc)=1
      EndIf
     Next
    EndIf  
    pAnimTim(cyc)=pAnimTim(cyc)+1
    ;eyeballs
    ManageEyes(cyc)

    ;MANAGE MUSIC
	;initiate preview
	If page=2 And foc=>8 And foc=<9
	 If charTheme(0)<>oldTheme
	  charThemePitch(0)=22050
      PlayTheme(0) : editThemeTest=1
     EndIf
     If charThemePitch(0)<>oldPitch 
      If editThemeTest=0 Then PlayTheme(0) : editThemeTest=1
      ChannelPitch chTheme,charThemePitch(0)
     EndIf
    Else
     ;restore main theme
     If editThemeTest=1 Then PlayTheme(-1) : editThemeTest=0
     ManageMusic(-1)
    EndIf 

    ;prepare to take photo
    If go>0
     CameraClsColor cam,25,5,5
     CameraClsMode cam,1,1
    EndIf

 UpdateWorld
 Next
 ;animation override
 If charEyeShape(0)=112
  LookAtPerson(cyc,cyc) 
 Else
  RotateEntity pLimb(cyc,45),0,0,0
  RotateEntity pLimb(cyc,46),0,0,0
 EndIf
 ;underlying images
 If go<>1
  DrawImage gBackground,rX#(400),rY#(300)
  DrawImage gLogo(1),rX#(400),rY#(150)
 EndIf
 RenderWorld 1

 ;DISPLAY
 advice$="" : adviceX=0 : adviceY=0
 warning$="" : warnX=0 : warnY=0 
 ;0. MAIN MENU
 If page=0
  x=590 : y=320
  DrawOption(0,rX#(x),rY#(y-40),"APPEARANCE","")
  DrawOption(1,rX#(x)-100,rY#(y),"PROFILE","")
  DrawOption(2,rX#(x)-100,rY#(y+40),"GIMMICK","")
  DrawOption(3,rX#(x)-100,rY#(y+80),"ATTACKS","")
  DrawOption(4,rX#(x)-100,rY#(y+120),"MAIN MOVES","")
  DrawOption(5,rX#(x)-100,rY#(y+160),"ADDITIONAL MOVES","")
  DrawOption(6,rX#(x)+100,rY#(y),"WRESTLING COSTUME","") 
  DrawOption(7,rX#(x)+100,rY#(y+40),"CASUAL COSTUME","") 
  DrawOption(8,rX#(x)+100,rY#(y+80),"REFEREE COSTUME","")
  DrawOption(9,rX#(x)+100,rY#(y+120),"STORY RELATIONSHIPS","") 
  DrawOption(10,rX#(x)+100,rY#(y+160),"REAL RELATIONSHIPS","") 
  If screenAgenda=5 Then namer$=">>> START CAREER >>>" Else namer$="<<< SAVE & EXIT <<<"
  DrawOption(11,rX#(x),rY#(550),namer$,"")
 EndIf
 ;1. PROFILE DISPLAY
 If page=1
  ;main options
  x=590 : y=125 : pointsY=0
  DrawOption(1,rX#(x),rY#(y),"Name",charName$(0)) : y=y+35
  DrawOption(2,rX#(x),rY#(y),"Team Name",charTeamName$(0)) : y=y+40
  DrawOption(3,rX#(x),rY#(y),"Age",charAge(0)+"yrs") : y=y+40
  DrawOption(4,rX#(x),rY#(y),"Popularity",charPopularity(0)+"%") : y=y+35
  DrawOption(5,rX#(x),rY#(y),"Strength",charStrength(0)+"%") : editPointsY(1)=rY#(y) : y=y+35
  DrawOption(6,rX#(x),rY#(y),"Skill",charSkill(0)+"%") : editPointsY(2)=rY#(y)  : y=y+35
  DrawOption(7,rX#(x),rY#(y),"Agility",charAgility(0)+"%") : editPointsY(3)=rY#(y) : y=y+35
  DrawOption(8,rX#(x),rY#(y),"Stamina",charStamina(0)+"%") : editPointsY(4)=rY#(y) : y=y+35
  DrawOption(9,rX#(x),rY#(y),"Toughness",charToughness(0)+"%") : editPointsY(5)=rY#(y) : y=y+35
  DrawOption(10,rX#(x),rY#(y),"Attitude",charAttitude(0)+"%") : y=y+35
  DrawOption(11,rX#(x),rY#(y),"Happiness",charHappiness(0)+"%") : y=y+40
  DrawOption(12,rX#(x),rY#(550),"<<< BACK <<<","")
  ;career points
  If game=1 And screenAgenda=5 And foc=>5 And foc=<9
   SetFont font(2)
   If editPoints>0 Then Outline("("+editPoints+" Points Remaining)",rX#(x),editPointsY(foc-4)+13,0,0,0,100,200,100)
   If editPoints=0 And flashTim=<20 Then Outline("(No Points Remaining!)",rX#(x),editPointsY(foc-4)+13,0,0,0,255,50,50)
  EndIf
  ;enter name
  y=125
  If EnterName() Or ButtonPressed() Or (MouseDown(1) And MouseX()>rX#(400) And editExamine=0)
   If foc=1 And blockName=0 And gotim>40 And keytim=0
    PlaySound sMenuBrowse : keytim=10 : FlushKeys()
    DrawOption(1,rX#(x),rY#(y),"Name","     ") : Flip
    Locate rX#(x)+10,rY#(y)-9 : Color 0,0,0
    SetFont font(4) 
    oldName$=charName$(0)
    charName$(0)=Left$(Input$(""),30) 
    If charName$(0)="" Then charName$(0)=oldName$
    If Right$(charTeamName$(0),7)="'s Team" Then charTeamName$(0)=charName$(0)+"'s Team"
   EndIf
   If foc=2 And blockName=0 And gotim>40 And keytim=0
    PlaySound sMenuBrowse : keytim=10 : FlushKeys()
    DrawOption(2,rX#(x),rY#(y+35),"Team Name","     ") : Flip
    Locate rX#(x)+10,rY#(y+35)-9 : Color 0,0,0
    SetFont font(4) 
    oldName$=charTeamName$(0)
    charTeamName$(0)=Left$(Input$(""),30) 
    If charTeamName$(0)="" Then charTeamName$(0)=charName$(0)+"'s Team"
   EndIf
  EndIf
 EndIf
 ;2. GIMMICK DISPLAY
 If page=2
  x=590 : y=70
  DrawOption(1,rX#(x),rY#(y),"Role",textRole$(charRole(0))) : y=y+35
  DrawOption(2,rX#(x),rY#(y),"Allegiance",textHeel$(charHeel(0)))
  DrawImage gAllegiance(charHeel(0)),rX#(x),rY#(y)-1 : y=y+40
  DrawOption(3,rX#(x),rY#(y),"Stance",stanceName$(charStance(0))) : y=y+35
  DrawOption(4,rX#(x),rY#(y),"Static Taunt",tauntName$(charTaunt(0,1))) : y=y+35
  DrawOption(5,rX#(x),rY#(y),"Direction Taunt",tauntName$(charTaunt(0,2))) : y=y+35
  DrawOption(6,rX#(x),rY#(y),"Special Taunt",tauntName$(charTaunt(0,3))) : y=y+35
  DrawOption(7,rX#(x),rY#(y),"Celebration Taunt",tauntName$(charTaunt(0,4))) : y=y+40
  If charTheme(0)>0 Then namer$=charTheme(0)+" / "+no_themes Else namer$="None"
  DrawOption(8,rX#(x),rY#(y),"Theme Tune",namer$) : y=y+35
  If charTheme(0)>0 Then namer$=GetFigure$(charThemePitch(0))+"kHz" Else namer$="N/A"
  DrawOption(9,rX#(x),rY#(y),"Theme Speed",namer$) : y=y+35
  DrawOption(10,rX#(x),rY#(y),"Light Show",textLight$(charLight(0))) : y=y+35
  If charWeapon(0)>0 Then namer$=weapName$(charWeapon(0)) Else namer$="None"
  DrawOption(11,rX#(x),rY#(y),"Prop",namer$) : y=y+40
  If charPartner(0)>0 Then namer$=charName$(charPartner(0)) Else namer$="None"
  DrawOption(12,rX#(x),rY#(y),"Tag Team Partner",namer$)
  If charPartner(0)>0
   reveal=(64-PortraitHead#(charPartner(0)))+10
   DrawImageRect charPhoto(charPartner(0)),rX#(x),(rY#(y)+10)+(64-reveal),0,0,76,reveal
  EndIf
  y=y+35
  If charManager(0)>0 Then namer$=charName$(charManager(0)) Else namer$="None"
  DrawOption(13,rX#(x),rY#(y),"Manager",namer$)
  If charManager(0)>0
   reveal=(64-PortraitHead#(charManager(0)))+10
   DrawImageRect charPhoto(charManager(0)),rX#(x),(rY#(y)+10)+(64-reveal),0,0,76,reveal
  EndIf
  DrawOption(14,rX#(x),rY#(550),"<<< BACK <<<","")
 EndIf
 ;3. ATTACKS DISPLAY
 If page=3
  x=590 : y=150
  DrawOption(1,rX#(x),rY#(y),"Upper Attack",attackName$(1,charAttack(0,1))) : y=y+40
  DrawOption(2,rX#(x),rY#(y),"Lower Attack",attackName$(2,charAttack(0,2))) : y=y+40
  DrawOption(3,rX#(x),rY#(y),"Big Attack",attackName$(3,charAttack(0,3))) : y=y+40
  DrawOption(4,rX#(x),rY#(y),"Running Attack",attackName$(4,charAttack(0,4))) : y=y+40
  DrawOption(5,rX#(x),rY#(y),"Flying Attack",attackName$(5,charAttack(0,5))) : y=y+45
  DrawOption(6,rX#(x),rY#(y),"Stomp Attack",crushName$(2,charCrush(0,2))) : y=y+40
  DrawOption(7,rX#(x),rY#(y),"Crush Attack",crushName$(3,charCrush(0,3))) : y=y+40
  DrawOption(8,rX#(x),rY#(y),"Running Crush",crushName$(4,charCrush(0,4))) : y=y+40
  DrawOption(9,rX#(x),rY#(y),"Flying Crush",crushName$(5,charCrush(0,5))) : y=y+45
  DrawOption(10,rX#(x),rY#(550),"<<< BACK <<<","")
 EndIf
 ;4. MAIN MOVES DISPLAY
 If page=4
  x=590 : y=100
  DrawOption(1,rX#(x),rY#(y),"Move A Centre",moveName$(1,charMove(0,1))) : y=y+35
  DrawOption(2,rX#(x),rY#(y),"Move A Up",moveName$(1,charMove(0,2))) : y=y+35
  DrawOption(3,rX#(x),rY#(y),"Move A Down",moveName$(1,charMove(0,3))) : y=y+35
  DrawOption(4,rX#(x),rY#(y),"Move A Side",moveName$(1,charMove(0,4))) : y=y+40
  DrawOption(5,rX#(x),rY#(y),"Move B Centre",moveName$(1,charMove(0,5))) : y=y+35
  DrawOption(6,rX#(x),rY#(y),"Move B Up",moveName$(1,charMove(0,6))) : y=y+35
  DrawOption(7,rX#(x),rY#(y),"Move B Down",moveName$(1,charMove(0,7))) : y=y+35
  DrawOption(8,rX#(x),rY#(y),"Move B Side",moveName$(1,charMove(0,8))) : y=y+40
  DrawOption(9,rX#(x),rY#(y),"Move C Centre",moveName$(1,charMove(0,9))) : y=y+35
  DrawOption(10,rX#(x),rY#(y),"Move C Up",moveName$(1,charMove(0,10))) : y=y+35
  DrawOption(11,rX#(x),rY#(y),"Move C Down",moveName$(1,charMove(0,11))) : y=y+35
  DrawOption(12,rX#(x),rY#(y),"Move C Side",moveName$(1,charMove(0,12))) : y=y+40
  DrawOption(13,rX#(x),rY#(550),"<<< BACK <<<","")
 EndIf
 ;5. ADDITIONAL MOVES DISPLAY
 If page=5
  x=590 : y=150
  DrawOption(1,rX#(x),rY#(y),"Head Move A",moveName$(3,charGroundMove(0,1))) : y=y+40
  DrawOption(2,rX#(x),rY#(y),"Head Move B",moveName$(3,charGroundMove(0,2))) : y=y+40
  DrawOption(3,rX#(x),rY#(y),"Head Move C",moveName$(3,charGroundMove(0,3))) : y=y+45
  DrawOption(4,rX#(x),rY#(y),"Leg Move A",moveName$(4,charGroundMove(0,4))) : y=y+40
  DrawOption(5,rX#(x),rY#(y),"Leg Move B",moveName$(4,charGroundMove(0,5))) : y=y+40
  DrawOption(6,rX#(x),rY#(y),"Leg Move C",moveName$(4,charGroundMove(0,6))) : y=y+45
  DrawOption(7,rX#(x),rY#(y),"Momentum Move",moveName$(1,charMove(0,13))) : y=y+40
  DrawOption(8,rX#(x),rY#(y),"Turnbuckle Move",moveName$(2,charMove(0,14))) : y=y+40
  DrawOption(9,rX#(x),rY#(y),"Special Move",moveName$(1,charMove(0,15))) : y=y+45
  DrawOption(10,rX#(x),rY#(550),"<<< BACK <<<","")
 EndIf
 ;6-8. COSTUMES
 If page=>6 And page=<8
  x=590 : y=50 : coz=pCostume(cyc)
  DrawOption(1,rX#(x),rY#(y),"Clothing",textBaggy$(charBaggy(0,coz))) : y=y+32
  DrawOption(2,rX#(x),rY#(y),"Headwear",textHat$(charHatStyle(0,coz))) : y=y+32
  If charHatStyle(0,coz)>0 Then namer$=charHat(0,coz)+" / "+no_hats Else namer$="N/A"
  DrawOption(3,rX#(x),rY#(y),"Hat Design",namer$) : y=y+32
  DrawOption(4,rX#(x),rY#(y),"Eyewear",textSpecs$(charSpecs(0,coz))) : y=y+36
  DrawOption(5,rX#(x),rY#(y),"Body",charBody(0,coz)+" / "+no_bodies) : y=y+32
  DrawOption(6,rX#(x),rY#(y),"Right Arm",charRightArm(0,coz)+" / "+no_arms) : y=y+32
  DrawOption(7,rX#(x),rY#(y),"Right Forearm",charRightForearm(0,coz)+" / "+no_arms) : y=y+32 
  DrawOption(8,rX#(x),rY#(y),"Right Hand",charRightHand(0,coz)+" / "+no_hands) : y=y+32 
  DrawOption(9,rX#(x),rY#(y),"Left Arm",charLeftArm(0,coz)+" / "+no_arms) : y=y+32
  DrawOption(10,rX#(x),rY#(y),"Left Forearm",charLeftForearm(0,coz)+" / "+no_arms) : y=y+32 
  DrawOption(11,rX#(x),rY#(y),"Left Hand",charLeftHand(0,coz)+" / "+no_hands) : y=y+36  
  If charShorts(0,coz)>0 Then namer$=charShorts(0,coz)+" / "+no_legs Else namer$="None"
  DrawOption(12,rX#(x),rY#(y),"Shorts",namer$) : y=y+32
  DrawOption(13,rX#(x),rY#(y),"Legs",charLegs(0,coz)+" / "+no_legs) : y=y+32
  DrawOption(14,rX#(x),rY#(y),"Shins",charShins(0,coz)+" / "+no_legs) : y=y+32
  DrawOption(15,rX#(x),rY#(y),"Shoes",charShoes(0,coz)+" / "+no_legs) : y=y+36
  DrawOption(16,rX#(x),rY#(550),"<<< BACK <<<","")
 EndIf
 ;9. APPEARANCE
 If page=9
  x=590 : y=100 : coz=pCostume(cyc)
  DrawOption(1,rX#(x),rY#(y),"Gender",textGender$(charGender(0))) : y=y+35
  DrawOption(2,rX#(x),rY#(y),"Height",GetHeight$(charHeight(0))) : y=y+35
  DrawOption(3,rX#(x),rY#(y),"Weight",TranslateWeight(0)+"lbs") : y=y+40
  DrawOption(4,rX#(x),rY#(y),"Hair Style",textHair$(charHairStyle(0,coz))) : y=y+35
  DrawOption(5,rX#(x),rY#(y),"Hair Colour",charHair(0,coz)+" / "+no_hairs) : y=y+35
  DrawOption(6,rX#(x),rY#(y),"Face",charFace(0,coz)+" / "+no_faces) : y=y+35
  If charBeard(0,coz)>0 Then namer$=charBeard(0,coz)+" / "+no_beards Else namer$="None"
  DrawOption(7,rX#(x),rY#(y),"Facial Hair",namer$) : y=y+40
  DrawOption(8,rX#(x),rY#(y),"Demeanour",textEyes$(charEyes(0))) : y=y+35
  DrawOption(9,rX#(x),rY#(y),"Eye Colour",charEyeballs(0)+" / "+no_eyes) : y=y+35
  namer$="None"
  If charEyeShape(0)>0 And charEyeShape(0)=<110 Then namer$=charEyeShape(0)+"%"
  If charEyeShape(0)=111 Then namer$="Uneven"
  If charEyeShape(0)=112 Then namer$="Boss-Eyed"
  DrawOption(10,rX#(x),rY#(y),"Eye Shape",namer$) : y=y+35
  DrawOption(11,rX#(x),rY#(y),"Head Shape",charHeadSize(0)+"%") : y=y+40
  DrawOption(12,rX#(x),rY#(y),"Tattoos",textTattoos$(charTattoos(0))) : y=y+40
  DrawOption(13,rX#(x),rY#(550),"<<< BACK <<<","")
 EndIf
 ;advice & warnings
 If warnX>0 And warnY>0
  SetFont font(2)
  If page=0 Then SqueezeFont(warning$,210,15)
  If flashTim=<20 Then Outline(warning$,warnX,warnY,0,0,0,255,50,50)
 Else
  SetFont font(2)
  If page=0 Then SqueezeFont(advice$,210,15)
  If adviceX>0 And adviceY>0 And flashTim=<20 Then Outline(advice$,adviceX,adviceY,0,0,0,100,200,100)
 EndIf
 ;loading call
 If screenCall=1
  ;QuickLoader(rX#(400),rY#(300),"Please Wait","Reloading Character")
  Loader("Please Wait","Reloading Character")
  FreeEntity p(cyc)
  LoadMuse(cyc)
  oldState=-1
 EndIf
 ;cursor
 If foc<>oldfoc Then oldfoc=foc : PlaySound sMenuSelect  
 If go=0
  DrawImage gCursor,MouseX(),MouseY()
  If editExamine=0 And MouseX()>rX#(120) And MouseX()<rX#(300) And MouseY()>rY#(100) And MouseY()<rY#(550)
   SetFont font(2)
   Outline("CLICK TO EXAMINE",MouseX()+5,MouseY()-20,0,0,0,255,240,100)
  EndIf
 EndIf

 Flip
 ;screenshot (F12)
 If KeyHit(88) Then Screenshot()

Wend
;leave
If go>0 Then PlaySound sMenuGo Else PlaySound sMenuBack
;save changes
If go>0
 If optWindow=0 Then Loader("Please Wait","Saving Character")
 ;update mask
 GetColor 1,1
 charPhotoR(editChar)=ColorRed()
 charPhotoG(editChar)=ColorGreen()
 charPhotoB(editChar)=ColorBlue()
 ;take photo
 photoX#=210 : photoY#=300
 If pState(cyc)=2 Or pState(cyc)=10 Then photoX#=210 : photoY#=285 ;standing offset
 If pState(cyc)=3 Then photoX#=205 : photoY#=290 ;referee offset 
 If pState(cyc)=16 Then photoX#=195 : photoY#=285 ;feminine offset
 If pState(cyc)=11 Then photoX#=200 : photoY#=285 ;power offset
 If pState(cyc)=14 Or pState(cyc)=15 Then photoX#=205 : photoY#=295 ;martial arts offset
 If GraphicsWidth()>1024 Then photoX#=photoX#+20
 charPhoto(editChar)=CreateImage(rY#(330),rY#(540))
 GrabImage charPhoto(editChar),rX#(photoX#),rY#(photoY#)
 MaskImage charPhoto(editChar),charPhotoR(editChar),charPhotoG(editChar),charPhotoB(editChar)
 ;save new image
 Loader("Please Wait","Saving Character") 
 ResizeImage charPhoto(editChar),75,128
 SaveImage(charPhoto(editChar),"Data/Slot0"+slot+"/Portraits/Photo"+Dig$(editChar,100)+".bmp")
 ;translate changes 
 CopyChar(0,editChar)
 If charAge(editChar)=<30 Then charPeaked(editChar)=0
 If charAge(editChar)=>40 Then charPeaked(editChar)=charAge(editChar)
 If charPartner(editChar)>0 
  ;If charRelationship(editChar,charPartner(editChar))<1 Then ChangeRelationship(editChar,charPartner(editChar),1)
  If editChar=charPartner(charPartner(editChar)) And Right$(charTeamName$(editChar),7)<>"'s Team" Then charTeamName$(charPartner(editChar))=charTeamName$(editChar)
 EndIf
 ;If charManager(editChar)>0 And charRelationship(editChar,charManager(editChar))<1 Then ChangeRelationship(editChar,charManager(editChar),1)
 If screenAgenda=5 Then ResetOldValues(editChar)
 ;save changes
 If editChar=no_chars+1
  no_chars=no_chars+1
  fedSize(charFed(editChar))=fedSize(charFed(editChar))+1
  fedRoster(charFed(editChar),fedSize(charFed(editChar)))=editChar
  SaveWorld(slot)
 EndIf
 SaveChar(editChar,slot)
EndIf
;free entities
FreeTimer timer
FreeEntity cam
FreeEntity light(1)
FreeEntity p(cyc)
For limb=1 To 50
 If pShadow(cyc,limb)>0
  FreeEntity pShadow(cyc,limb)
 EndIf
Next
;restore textures
If go=<0 Then Loader("Please Wait","Closing Editor")
RestoreTextures()
;proceed
screen=12 : fed=editFed
If go=-1 And screenAgenda=5 Then slotActive(slot)=0 : screen=10
If go=2 Then screen=11 : screenAgenda=7
If go=3 Then screen=11 : screenAgenda=8
If go=1 And game=1 And screenAgenda=5 Then screen=11
End Function 

;-----------------------------------------------------------------
;////////////////////// RELATED FUNCTIONS ////////////////////////
;-----------------------------------------------------------------

;INITIALISE CREATION MODEL
Function LoadMuse(cyc)
 ;load model
 editModel=GetModel(pChar(cyc))
 p(cyc)=LoadAnimMesh("Characters/Models/Model0"+editModel+".3ds")
 StripModel(cyc)
 PositionEntity p(cyc),pX#(cyc),pY#(cyc),pZ#(cyc) 
 RotateEntity p(cyc),0,345,0
 ;load animations
 pSeq(cyc,1000)=LoadAnimSeq(p(cyc),"Characters/Sequences/Weapons.3ds")
 For count=1 To 11
  pSeq(cyc,1000+count)=LoadAnimSeq(p(cyc),"Characters/Sequences/Standard"+Dig$(count,10)+".3ds")
 Next
 ;role identifiers
 pSeq(cyc,1)=ExtractAnimSeq(p(cyc),10,40,pSeq(cyc,1001)) ;wrestler
 pSeq(cyc,2)=ExtractAnimSeq(p(cyc),1730,1760,pSeq(cyc,1001)) ;casual (male)
 pSeq(cyc,3)=ExtractAnimSeq(p(cyc),985,1065,pSeq(cyc,1004)) ;referee
 pSeq(cyc,4)=ExtractAnimSeq(p(cyc),20,60,pSeq(cyc,1000)) ;weapon holder
 ;stance previews
 pSeq(cyc,10)=ExtractAnimSeq(p(cyc),1730,1760,pSeq(cyc,1001)) ;normal
 pSeq(cyc,11)=ExtractAnimSeq(p(cyc),905,965,pSeq(cyc,1007)) ;powerful
 pSeq(cyc,12)=ExtractAnimSeq(p(cyc),1295,1335,pSeq(cyc,1005)) ;wrestling
 pSeq(cyc,13)=ExtractAnimSeq(p(cyc),10,40,pSeq(cyc,1001)) ;boxing
 pSeq(cyc,14)=ExtractAnimSeq(p(cyc),20,60,pSeq(cyc,1005)) ;lowered boxing
 pSeq(cyc,15)=ExtractAnimSeq(p(cyc),845,885,pSeq(cyc,1005)) ;raised boxing
 pSeq(cyc,16)=ExtractAnimSeq(p(cyc),1625,1665,pSeq(cyc,1005)) ;feminine
 ;movement previews
 pSeq(cyc,20)=ExtractAnimSeq(p(cyc),340,400,pSeq(cyc,1003)) ;walking
 pSeq(cyc,21)=ExtractAnimSeq(p(cyc),905,965,pSeq(cyc,1007)) ;powerful
 pSeq(cyc,22)=ExtractAnimSeq(p(cyc),1345,1405,pSeq(cyc,1005)) ;wrestling
 pSeq(cyc,23)=ExtractAnimSeq(p(cyc),145,205,pSeq(cyc,1001)) ;boxing
 pSeq(cyc,24)=ExtractAnimSeq(p(cyc),70,130,pSeq(cyc,1005)) ;lowered boxing
 pSeq(cyc,25)=ExtractAnimSeq(p(cyc),895,955,pSeq(cyc,1005)) ;raised boxing
 pSeq(cyc,26)=ExtractAnimSeq(p(cyc),1625,1665,pSeq(cyc,1005)) ;feminine
 ;100-200: taunts
 For anim=1 To no_taunts
  pSeq(cyc,100+anim)=ExtractAnimSeq(p(cyc),tauntAnimStart(anim),tauntAnimEnd(anim),pSeq(cyc,tauntAnimSource(anim)))
 Next
 ;200-400: attacks
 For count=1 To 5
  For anim=1 To attackList(count)
   pSeq(cyc,(180+(30*count))+anim)=ExtractAnimSeq(p(cyc),attackAnimStart(count,anim),attackAnimEnd(count,anim),pSeq(cyc,attackAnimSource(count,anim)))
  Next 
 Next
 ;400-400: crushes
 For count=2 To 5
  For anim=1 To crushList(count)
   pSeq(cyc,(380+(20*count))+anim)=ExtractAnimSeq(p(cyc),crushAnimStart(count,anim),crushAnimEnd(count,anim),pSeq(cyc,crushAnimSource(count,anim)))
  Next 
 Next
 ;reset state
 Animate p(cyc),1,1,pSeq(cyc,1),0
 pState(cyc)=-1
End Function

;STRIP MODEL BARE
Function StripModel(cyc)
 ;limb loss
 GetLimbs(cyc)
 For limb=1 To 50
  If charLimb(pChar(cyc),limb)=0 And pLimb(cyc,limb)>0
   EntityAlpha pLimb(cyc,limb),0
  EndIf
 Next
 ;hide props
 For v=1 To weapList
  HideEntity FindChild(p(cyc),weapFile$(v))
 Next
 For count=1 To 2
  HideEntity FindChild(p(cyc),"Belt0"+count)
  EntityShininess FindChild(p(cyc),"Belt0"+count),1.0
 Next
 HideEntity FindChild(p(cyc),"Barbell")
End Function

;IDENTIFY LIMBS
Function GetLimbs(cyc)
 ;reset entries
 For count=1 To 50
  pLimb(cyc,count)=0 
 Next
 ;head
 pLimb(cyc,1)=FindChild(p(cyc),"Head")
 pLimb(cyc,2)=FindChild(p(cyc),"L_Ear")
 pLimb(cyc,3)=FindChild(p(cyc),"R_Ear")
 pLimb(cyc,4)=FindChild(p(cyc),"Neck")
 ;body
 If charBaggy(pChar(cyc),pCostume(cyc))=1 Or charBaggy(pChar(cyc),pCostume(cyc))=3
  pLimb(cyc,5)=FindChild(p(cyc),"Body_Bag")
 Else
  pLimb(cyc,5)=FindChild(p(cyc),"Body")
 EndIf 
 ;left arm
 pLimb(cyc,6)=FindChild(p(cyc),"L_Bicep")
 pLimb(cyc,7)=FindChild(p(cyc),"L_Arm")
 pLimb(cyc,8)=FindChild(p(cyc),"L_Hand")
 pLimb(cyc,9)=FindChild(p(cyc),"L_Thumb01")
 pLimb(cyc,10)=FindChild(p(cyc),"L_Thumb02")
 For count=1 To 8
  pLimb(cyc,10+count)=FindChild(p(cyc),"L_Finger0"+count)
 Next
 ;right arm
 pLimb(cyc,19)=FindChild(p(cyc),"R_Bicep")
 pLimb(cyc,20)=FindChild(p(cyc),"R_Arm")
 pLimb(cyc,21)=FindChild(p(cyc),"R_Hand")
 pLimb(cyc,22)=FindChild(p(cyc),"R_Thumb01")
 pLimb(cyc,23)=FindChild(p(cyc),"R_Thumb02")
 For count=1 To 8
  pLimb(cyc,23+count)=FindChild(p(cyc),"R_Finger0"+count)
 Next
 ;gloves
 ;pLimb(cyc,32)=FindChild(p(cyc),"L_Pad")
 ;pLimb(cyc,33)=FindChild(p(cyc),"R_Pad")
 ;pLimb(cyc,34)=FindChild(p(cyc),"L_Glove")
 ;pLimb(cyc,35)=FindChild(p(cyc),"R_Glove")
 ;shorts
 pLimb(cyc,36)=FindChild(p(cyc),"Hips")
 pLimb(cyc,37)=FindChild(p(cyc),"L_Short")
 pLimb(cyc,38)=FindChild(p(cyc),"R_Short")
 ;legs
 pLimb(cyc,39)=FindChild(p(cyc),"L_Thigh")
 If charBaggy(pChar(cyc),pCostume(cyc))=2 Or charBaggy(pChar(cyc),pCostume(cyc))=3
  pLimb(cyc,40)=FindChild(p(cyc),"L_Leg_Bag")
 Else
  pLimb(cyc,40)=FindChild(p(cyc),"L_Leg")
 EndIf
 pLimb(cyc,41)=FindChild(p(cyc),"L_Foot")
 pLimb(cyc,42)=FindChild(p(cyc),"R_Thigh")  
 If charBaggy(pChar(cyc),pCostume(cyc))=2 Or charBaggy(pChar(cyc),pCostume(cyc))=3 
  pLimb(cyc,43)=FindChild(p(cyc),"R_Leg_Bag")
 Else
  pLimb(cyc,43)=FindChild(p(cyc),"R_Leg")
 EndIf
 pLimb(cyc,44)=FindChild(p(cyc),"R_Foot")
 ;eyes
 pLimb(cyc,45)=FindChild(p(cyc),"L_Eye")
 pLimb(cyc,46)=FindChild(p(cyc),"R_Eye")
End Function

;MAJOR LIMB?
Function MajorLimb(limb)
 value=1
 If limb=>2 And limb=<3 Then value=0 ;ears
 If limb=>9 And limb=<18 Then value=0 ;left fingers
 If limb=>22 And limb=<31 Then value=0 ;right fingers
 If limb=>37 And limb=<38 Then value=0 ;shorts
 Return value
End Function

;DETERMINE RACE
Function GetRace(char) ;0/1=white, 2=asian, 3=black
 value=1
 If tFaceType(charFace(char,1))>0 Then value=tFaceType(charFace(char,1))
 Return value
End Function

;TRANSLATE WEIGHT (in relation to height)
Function TranslateWeight(char)
 factor=GetPercent#(charHeight(char)+24,24)
 value=PercentOf#(charWeight(char),factor)
 Return value
End Function

;GET MODEL (from base weight)
Function GetModel(char)
 value=0
 If charGender(char)>0
  If charWeight(char)<125 Then value=7 ;slim
  If charWeight(char)=>125 And charWeight(char)<200 Then value=8 ;thick
  If charWeight(char)=>200 And charWeight(char)<225 Then value=4 ;chubby
  If charWeight(char)=>225 And charWeight(char)<250 Then value=5 ;fat   
  If charWeight(char)=>250 Then value=6 ;obese
 Else
  If charWeight(char)<110 Then value=0 ;gaunt
  If charWeight(char)=>110 And charWeight(char)<145 Then value=1 ;slim
  If charWeight(char)=>145 And charWeight(char)<175 Then value=2 ;normal
  If charWeight(char)=>175 And charWeight(char)<200 Then value=3 ;muscular
  If charWeight(char)=>200 And charWeight(char)<225 Then value=4 ;chubby
  If charWeight(char)=>225 And charWeight(char)<250 Then value=5 ;fat   
  If charWeight(char)=>250 Then value=6 ;obese
 EndIf
 Return value
End Function

;GET MODEL ELEVATION
Function ModelElevation#(char)
 ;default elevation
 value#=4.0
 If GetModel(char)=2 Then value#=4.25
 If GetModel(char)=3 Then value#=4.75
 If GetModel(char)=4 Then value#=5.0 
 If GetModel(char)=5 Then value#=5.25
 If GetModel(char)=6 Then value#=5.5
 ;height offset
 offset#=GetPercent#(charHeight(char)-12,12)
 value#=value#+PercentOf#(value#,PercentOf#(10,offset#))
 Return value#
End Function

;GET VOICE TONE
Function GetVoice(cyc)
 voice=24000-(TranslateWeight(pChar(cyc))*10)
 If charGender(pChar(cyc))=1 Then voice=28000
 Return voice
End Function

;PICK OUT HEAD IN PORTRAIT
Function PortraitHead#(char)
 factor#=GetPercent#(charHeight(char),24)
 If char=0 Then factor#=GetPercent#(12,24)
 value=Int(22.0+PercentOf#(19,factor#))
 Return value
End Function

;APPLY COSTUME
Function ApplyCostume(cyc)
 GetLimbs(cyc)
 ApplyHairstyle(cyc,charHatStyle(pChar(cyc),pCostume(cyc)))
 ApplyHeadwear(cyc)
 ApplyEyewear(cyc)
 ApplyClothing(cyc) 
End Function

;REMOVE ALL HAIR
Function RemoveHair(cyc)
 For limb=1 To 30
  If FindChild(p(cyc),hairFile$(limb))>0
   EntityAlpha FindChild(p(cyc),hairFile$(limb)),0
  EndIf
 Next
End Function

;APPLY HAIRSTYLE
Function ApplyHairstyle(cyc,headwear)
 ;identify variables
 char=pChar(cyc) : coz=pCostume(cyc)
 ;hide all by default
 RemoveHair(cyc)
 ;determine style
 showHairA$="" : showHairB$=""
 If charHairStyle(char,coz)=5 Then showHairA$="H_Bald" : showHairB$=""
 If charHairStyle(char,coz)=6 Then showHairA$="H_Oaf" : showHairB$=""
 If charHairStyle(char,coz)=7 Then showHairA$="H_Recede" : showHairB$=""
 If charHairStyle(char,coz)=8 Then showHairA$="H_Short" : showHairB$=""
 If charHairStyle(char,coz)=9 Then showHairA$="H_Raised" : showHairB$=""
 If charHairStyle(char,coz)=10 Then showHairA$="H_Quiff" : showHairB$=""
 If charHairStyle(char,coz)=11 Then showHairA$="H_Side" : showHairB$=""
 If charHairStyle(char,coz)=12 Then showHairA$="H_Part" : showHairB$=""
 If charHairStyle(char,coz)=13 Then showHairA$="H_Mop" : showHairB$=""
 If charHairStyle(char,coz)=14 Then showHairA$="H_Thick" : showHairB$=""
 If charHairStyle(char,coz)=15 Then showHairA$="H_Full" : showHairB$=""  
 If charHairStyle(char,coz)=16 Then showHairA$="H_Curly" : showHairB$=""
 If charHairStyle(char,coz)=17 Then showHairA$="H_Afro" : showHairB$=""
 If charHairStyle(char,coz)=18 Then showHairA$="H_Buzz" : showHairB$=""
 If charHairStyle(char,coz)=19 Then showHairA$="H_Spikey" : showHairB$=""
 If charHairStyle(char,coz)=20 Then showHairA$="H_Punk" : showHairB$=""
 If charHairStyle(char,coz)=21 Then showHairA$="H_Rows" : showHairB$=""
 If charHairStyle(char,coz)=22 Then showHairA$="H_Bald" : showHairB$="H_Pony"
 If charHairStyle(char,coz)=23 Then showHairA$="H_Recede" : showHairB$="H_Pony"
 If charHairStyle(char,coz)=24 Then showHairA$="H_Short" : showHairB$="H_Pony"
 If charHairStyle(char,coz)=25 Then showHairA$="H_Raised" : showHairB$="H_Pony"
 If charHairStyle(char,coz)=26 Then showHairA$="H_Quiff" : showHairB$="H_Pony"
 If charHairStyle(char,coz)=27 Then showHairA$="H_Side" : showHairB$="H_Pony"
 If charHairStyle(char,coz)=28 Then showHairA$="H_Part" : showHairB$="H_Pony"
 If charHairStyle(char,coz)=29 Then showHairA$="H_Mop" : showHairB$="H_Pony"  
 If charHairStyle(char,coz)=30 Then showHairA$="H_Thick" : showHairB$="H_Pony"
 If charHairStyle(char,coz)=31 Then showHairA$="H_Curly" : showHairB$="H_Pony"
 If charHairStyle(char,coz)=32 Then showHairA$="H_Punk" : showHairB$="H_Pony"
 If charHairStyle(char,coz)=33 Then showHairA$="H_Rows" : showHairB$="H_Pony"
 If charHairStyle(char,coz)=34 Then showHairA$="H_Bald" : showHairB$="H_Long"
 If charHairStyle(char,coz)=35 Then showHairA$="H_Recede" : showHairB$="H_Long"
 If charHairStyle(char,coz)=36 Then showHairA$="H_Short" : showHairB$="H_Long"
 If charHairStyle(char,coz)=37 Then showHairA$="H_Raised" : showHairB$="H_Long"
 If charHairStyle(char,coz)=38 Then showHairA$="H_Quiff" : showHairB$="H_Long"
 If charHairStyle(char,coz)=39 Then showHairA$="H_Side" : showHairB$="H_Long"
 If charHairStyle(char,coz)=40 Then showHairA$="H_Part" : showHairB$="H_Long"
 If charHairStyle(char,coz)=41 Then showHairA$="H_Mop" : showHairB$="H_Long"
 If charHairStyle(char,coz)=42 Then showHairA$="H_Thick" : showHairB$="H_Long"  
 If charHairStyle(char,coz)=43 Then showHairA$="H_Curly" : showHairB$="H_Long"
 If charHairStyle(char,coz)=44 Then showHairA$="H_Punk" : showHairB$="H_Long"
 If charHairStyle(char,coz)=45 Then showHairA$="H_Rows" : showHairB$="H_Long"
 If charHairStyle(char,coz)=46 Then showHairA$="H_Slick" : showHairB$=""
 If KeyDown(25) Then showHairB$="H_Pony" 
 If KeyDown(38) Then showHairB$="H_Long"
 ;hat logic
 If showHairA$<>""
  If headwear=1
   If showHairA$="H_Side" Or showHairA$="H_Mop" Then showHairA$="H_Part"
  EndIf
  If headwear=2 Then showHairA$="" : showHairB$=""
  If headwear=>3 And headwear=<9
   If showHairA$="H_Buzz"
    showHairA$=""
   Else
    showHairA$="H_Bald"
    If charHairStyle(char,coz)=6 Or charHairStyle(char,coz)=15 Then showHairA$="H_Oaf"
   EndIf
  EndIf
 EndIf
 ;compose hair
 If showHairA$<>""
  EntityAlpha FindChild(p(cyc),showHairA$),1
  EntityTexture FindChild(p(cyc),showHairA$),tHair(charHair(char,coz));,0,1
  tHairAccess(charHair(char,coz))=1
 EndIf
 If showHairB$<>""
  EntityAlpha FindChild(p(cyc),showHairB$),1 
  EntityTexture FindChild(p(cyc),showHairB$),tHair(charHair(char,coz));,0,1
  tHairAccess(charHair(char,coz))=1
  If showHairB$="H_Pony" Then EntityTexture FindChild(p(cyc),showHairB$),tPony,0,2
 EndIf
 ;add shaved layer
 EntityTexture FindChild(p(cyc),"Head"),tMouth(0),0,2
 If charHairStyle(char,coz)=>1 And charHairStyle(char,coz)=<4
  EntityTexture FindChild(p(cyc),"Head"),tShaved(charHairStyle(char,coz)),0,2
 EndIf
 If charHairStyle(char,coz)=47 Then EntityTexture FindChild(p(cyc),"Head"),tShaved(5),0,2
 If charHairStyle(char,coz)=48 Then EntityTexture FindChild(p(cyc),"Head"),tShaved(6),0,2  
 If showHairA$="H_Buzz" Or showHairA$="H_Rows" Then EntityTexture FindChild(p(cyc),"Head"),tShaved(3),0,2
End Function

;REMOVE ALL HEADWEAR
Function RemoveHeadwear(cyc)
 For count=1 To 2
  HideEntity FindChild(p(cyc),"Band0"+count)
 Next
 HideEntity FindChild(p(cyc),"Bandana")
 HideEntity FindChild(p(cyc),"Cap")
 HideEntity FindChild(p(cyc),"Hat")
 HideEntity FindChild(p(cyc),"Horns")
End Function

;APPLY HEADWEAR
Function ApplyHeadwear(cyc)
 ;identify variables
 char=pChar(cyc) : coz=pCostume(cyc)
 ;hide by default
 RemoveHeadwear(cyc)
 ;headband
 If charHatStyle(char,coz)=1
  For count=1 To 2
   ShowEntity FindChild(p(cyc),"Band0"+count)
   EntityTexture FindChild(p(cyc),"Band0"+count),tHat(charHat(char,coz)),0,1 
   tHatAccess(charHat(char,coz))=1
  Next
  If charHairStyle(char,coz)=>5
   style=charHairStyle(char,coz)
   If style<>18 And style<>20 And style<>21 And style<>32 And style<>33 And style<>44 And style<>45 And style<>47 And style<>48
    HideEntity FindChild(p(cyc),"Band02")
   EndIf
  EndIf
 EndIf
 ;bandana
 If charHatStyle(char,coz)=2
  HideEntity FindChild(p(cyc),"L_Ear")
  HideEntity FindChild(p(cyc),"R_Ear")
  ShowEntity FindChild(p(cyc),"Bandana")
  EntityTexture FindChild(p(cyc),"Bandana"),tHat(charHat(char,coz)),0,1 
  tHatAccess(charHat(char,coz))=1
 Else
  ShowEntity FindChild(p(cyc),"L_Ear")
  ShowEntity FindChild(p(cyc),"R_Ear")
 EndIf
 ;caps
 shaved=0
 If charHairStyle(char,coz)=<4 Or charHairstyle(char,coz)=47 Or charHairstyle(char,coz)=48 Then shaved=1
 If charHatStyle(char,coz)=>3 And charHatStyle(char,coz)=<6
  ShowEntity FindChild(p(cyc),"Cap")
  EntityTexture FindChild(p(cyc),"Cap"),tHat(charHat(char,coz)),0,1
  tHatAccess(charHat(char,coz))=1
  PositionEntity FindChild(p(cyc),"Cap"),-0.33,34.39,3.45 
  If shaved>0 Then PositionEntity FindChild(p(cyc),"Cap"),-0.33,34.39,6.6
  RotateEntity FindChild(p(cyc),"Cap"),0,0,0
  If charHatStyle(char,coz)=4 ;tipped variation
   PositionEntity FindChild(p(cyc),"Cap"),1.03,41.19,5.06 
   If shaved>0 Then PositionEntity FindChild(p(cyc),"Cap"),2.11,39.19,9.73 
   RotateEntity FindChild(p(cyc),"Cap"),18.60,-21.79,0
  EndIf
  If charHatStyle(char,coz)=5 ;raised variation
   PositionEntity FindChild(p(cyc),"Cap"),-1.15,37.03,1.97 
   If shaved>0 Then PositionEntity FindChild(p(cyc),"Cap"),-0.52,35.03,5.61
   RotateEntity FindChild(p(cyc),"Cap"),-25.1,12.5,0
  EndIf
  If charHatStyle(char,coz)=6 ;reversed variation
   PositionEntity FindChild(p(cyc),"Cap"),0.05,32.4,3.54
   If shaved>0 Then PositionEntity FindChild(p(cyc),"Cap"),0.05,30.4,6.54 
   RotateEntity FindChild(p(cyc),"Cap"),23.77,180.9,0
  EndIf
 EndIf
 ;hats
 If charHatStyle(char,coz)=>7 And charHatStyle(char,coz)=<9
  ShowEntity FindChild(p(cyc),"Hat")
  EntityTexture FindChild(p(cyc),"Hat"),tHat(charHat(char,coz)),0,1
  tHatAccess(charHat(char,coz))=1
  PositionEntity FindChild(p(cyc),"Hat"),-0.33,34.39,3.45 
  If shaved>0 Then PositionEntity FindChild(p(cyc),"Hat"),-0.33,34.39,6.6
  RotateEntity FindChild(p(cyc),"Hat"),0,0,0
  If charHatStyle(char,coz)=8 ;tipped variation
   PositionEntity FindChild(p(cyc),"Hat"),2.43,41.62,5.14 
   If shaved>0 Then PositionEntity FindChild(p(cyc),"Hat"),2.43,39.62,6.14 
   RotateEntity FindChild(p(cyc),"Hat"),20,-18.9,-7.4
   If charSpecs(char,coz)>0
    PositionEntity FindChild(p(cyc),"Hat"),2.43,42.16,5.14  
    RotateEntity FindChild(p(cyc),"Hat"),16.9,-18.9,-7.4
   EndIf
  EndIf
  If charHatStyle(char,coz)=9 ;raised variation
   PositionEntity FindChild(p(cyc),"Hat"),1.69,40.73,1.22 
   If shaved>0 Then PositionEntity FindChild(p(cyc),"Hat"),1.69,36.44,4.45
   RotateEntity FindChild(p(cyc),"Hat"),-21.47,2.4,0
  EndIf
 EndIf
 ;horns
 If charHatStyle(char,coz)=10
  ShowEntity FindChild(p(cyc),"Horns")
  EntityTexture FindChild(p(cyc),"Horns"),tHat(charHat(char,coz)),0,1 
  tHatAccess(charHat(char,coz))=1
 EndIf
End Function

;REMOVE ALL EYEWEAR
Function RemoveEyewear(cyc)
 HideEntity FindChild(p(cyc),"Specs")
 HideEntity FindChild(p(cyc),"Lens01")
 HideEntity FindChild(p(cyc),"Lens02")
 HideEntity FindChild(p(cyc),"Patch")
End Function

;APPLY EYEWEAR
Function ApplyEyewear(cyc)
 ;identify variables
 char=pChar(cyc) : coz=pCostume(cyc)
 ;hide by default
 RemoveEyewear(cyc)
 If charSpecs(char,coz)=>1 And charSpecs(char,coz)=<7
  ;compose specs
  ShowEntity FindChild(p(cyc),"Specs")
  EntityShininess FindChild(p(cyc),"Specs"),0.5
  For count=1 To 2
   ShowEntity FindChild(p(cyc),"Lens0"+count)
   EntityColor FindChild(p(cyc),"Lens0"+count),255,255,255
   EntityAlpha FindChild(p(cyc),"Lens0"+count),0.2
   EntityShininess FindChild(p(cyc),"Lens0"+count),1
  Next
  ;golden frame
  If charSpecs(char,coz)=1 
   EntityTexture FindChild(p(cyc),"Specs"),tSpecs(1)
  EndIf
  ;silver frame
  If charSpecs(char,coz)=2 
   EntityTexture FindChild(p(cyc),"Specs"),tSpecs(2)
  EndIf
  ;black frame
  If charSpecs(char,coz)=3 
   EntityTexture FindChild(p(cyc),"Specs"),tSpecs(3)
  EndIf
  ;amber lenses
  If charSpecs(char,coz)=4 
   EntityTexture FindChild(p(cyc),"Specs"),tSpecs(1)
   For count=1 To 2
    EntityColor FindChild(p(cyc),"Lens0"+count),100,30,0
    EntityAlpha FindChild(p(cyc),"Lens0"+count),0.5
   Next
  EndIf
  ;yellow lenses
  If charSpecs(char,coz)=5 
   EntityTexture FindChild(p(cyc),"Specs"),tSpecs(3)
   For count=1 To 2
    EntityColor FindChild(p(cyc),"Lens0"+count),255,240,0
    EntityAlpha FindChild(p(cyc),"Lens0"+count),0.5
   Next
  EndIf
  ;black shades
  If charSpecs(char,coz)=6
   EntityTexture FindChild(p(cyc),"Specs"),tSpecs(3)
   For count=1 To 2
    EntityColor FindChild(p(cyc),"Lens0"+count),0,0,0
    EntityAlpha FindChild(p(cyc),"Lens0"+count),0.75
   Next
  EndIf
  ;gold-rimmed shades
  If charSpecs(char,coz)=7
   EntityTexture FindChild(p(cyc),"Specs"),tSpecs(1)
   For count=1 To 2
    EntityColor FindChild(p(cyc),"Lens0"+count),0,0,0
    EntityAlpha FindChild(p(cyc),"Lens0"+count),0.75
   Next
  EndIf
 EndIf
 ;eye-patch
 If charSpecs(char,coz)=8
  ShowEntity FindChild(p(cyc),"Patch")
 EndIf
End Function

;APPLY CLOTHING
Function ApplyClothing(cyc)
 ;identify variables
 char=pChar(cyc) : coz=pCostume(cyc)
 ;head
 For limb=1 To 4
  EntityTexture pLimb(cyc,limb),tFace(charFace(char,coz)),0,0
  tFaceAccess(charFace(char,coz))=1
  If limb=>2 And limb=<3 Then EntityTexture pLimb(cyc,limb),tEars,0,3
 Next
 ScaleEntity pLimb(cyc,1),Float#(charHeadSize(char))/100,1,1
 ;facial hair
 limb=1
 If charBeard(char,coz)>0
  EntityTexture pLimb(cyc,limb),tBeard(charBeard(char,coz)),0,1
 Else
  EntityTexture pLimb(cyc,limb),tMouth(0),0,1
 EndIf
 ;eyes
 If screen=51
  pEyeTex(cyc)=tEyeballs(charEyeballs(char))
 Else
  pEyeTex(cyc)=LoadTexture("Characters/Eyes/Eyes"+Dig$(charEyeballs(char),10)+".JPG")
 EndIf
 For limb=45 To 46
  EntityTexture pLimb(cyc,limb),pEyeTex(cyc),0,1
  If charEyeShape(char)=0 Then EntityAlpha pLimb(cyc,limb),0 Else EntityAlpha pLimb(cyc,limb),1
 Next
 If charEyeShape(char)<100
  ScaleTexture pEyeTex(cyc),1.0,1.0+(Float#(100-charEyeShape(char))/50)
  If charEyeShape(char)=>90 Then PositionTexture pEyeTex(cyc),1.0,-0.075
  If charEyeShape(char)=>80 And charEyeShape(char)<90 Then PositionTexture pEyeTex(cyc),1.0,-0.13
  If charEyeShape(char)=>70 And charEyeShape(char)<80 Then PositionTexture pEyeTex(cyc),1.0,-0.17
  If charEyeShape(char)=>60 And charEyeShape(char)<70 Then PositionTexture pEyeTex(cyc),1.0,-0.24
  If charEyeShape(char)=>50 And charEyeShape(char)<60 Then PositionTexture pEyeTex(cyc),1.0,-0.25
  If charEyeShape(char)=>40 And charEyeShape(char)<50 Then PositionTexture pEyeTex(cyc),1.0,-0.27
  If charEyeShape(char)=>30 And charEyeShape(char)<40 Then PositionTexture pEyeTex(cyc),1.0,-0.28
  If charEyeShape(char)=>20 And charEyeShape(char)<30 Then PositionTexture pEyeTex(cyc),1.0,-0.29
  If charEyeShape(char)=>10 And charEyeShape(char)<20 Then PositionTexture pEyeTex(cyc),1.0,-0.30
 Else
  ScaleTexture pEyeTex(cyc),1.0,1.0
  PositionTexture pEyeTex(cyc),1.0,1.0
 EndIf
 ;If screen<>51 Then FreeTexture pEyeTex(cyc)
 ;body
 limb=5
 EntityTexture pLimb(cyc,limb),tMouth(0),0,1
 EntityTexture pLimb(cyc,limb),tMouth(0),0,2
 EntityTexture pLimb(cyc,limb),tBody(charBody(char,coz)),0,0
 tBodyAccess(charBody(char,coz))=1
 If GetRace(char)>1 And tBodyType(charBody(char,coz))>0
  EntityTexture pLimb(cyc,limb),tBodyShade(tBodyType(charBody(char,coz))+(GetRace(char)-2)),0,2  
  tBodyShadeAccess(tBodyType(charBody(char,coz))+(GetRace(char)-2))=1
 EndIf
 If charTattoos(char)=1 Or charTattoos(char)=2
  If tBodyType(charBody(char,coz))>0 And tBodyTattoo(tBodyType(charBody(char,coz)))>0
   EntityTexture pLimb(cyc,limb),tBodyTattoo(tBodyType(charBody(char,coz))),0,1  
   tBodyTattooAccess(tBodyType(charBody(char,coz)))=1
  EndIf
 EndIf
 If charBaggy(char,coz)=1 Or charBaggy(char,coz)=3
  EntityAlpha FindChild(p(cyc),"Body_Bag"),1
  EntityAlpha FindChild(p(cyc),"Body"),0
 Else
  EntityAlpha FindChild(p(cyc),"Body"),1
  EntityAlpha FindChild(p(cyc),"Body_Bag"),0
 EndIf
 ;left arm
 limb=6
  EntityTexture pLimb(cyc,limb),tMouth(0),0,1
  EntityTexture pLimb(cyc,limb),tMouth(0),0,2
  EntityTexture pLimb(cyc,limb),tArm(charLeftArm(char,coz)),0,0
  tArmAccess(charLeftArm(char,coz))=1
  If GetRace(char)>1 And tArmType(charLeftArm(char,coz))>0
   EntityTexture pLimb(cyc,limb),tArmShade(tArmType(charLeftArm(char,coz))+(GetRace(char)-2)),0,2
   tArmShadeAccess(tArmType(charLeftArm(char,coz))+(GetRace(char)-2))=1
  EndIf
  If charTattoos(char)=1 Or charTattoos(char)=3 Or charTattoos(char)=4 Or charTattoos(char)=6
   If tArmType(charLeftArm(char,coz))>0 And tArmTattoo(tArmType(charLeftArm(char,coz)))>0
    EntityTexture pLimb(cyc,limb),tArmTattoo(tArmType(charLeftArm(char,coz))),0,1
    tArmTattooAccess(tArmType(charLeftArm(char,coz)))=1
   EndIf
  EndIf
 ;left forearm
 limb=7
  EntityTexture pLimb(cyc,limb),tMouth(0),0,1
  EntityTexture pLimb(cyc,limb),tMouth(0),0,2
  EntityTexture pLimb(cyc,limb),tArm(charLeftForearm(char,coz)),0,0
  tArmAccess(charLeftForearm(char,coz))=1
  If GetRace(char)>1 And tArmType(charLeftForearm(char,coz))>0
   EntityTexture pLimb(cyc,limb),tArmShade(tArmType(charLeftForearm(char,coz))+(GetRace(char)-2)),0,2
   tArmShadeAccess(tArmType(charLeftForearm(char,coz))+(GetRace(char)-2))=1
  EndIf
  If charTattoos(char)=1 Or charTattoos(char)=3 Or charTattoos(char)=4 Or charTattoos(char)=8
   If tArmType(charLeftForearm(char,coz))>0 And tArmTattoo(tArmType(charLeftForearm(char,coz)))>0
    EntityTexture pLimb(cyc,limb),tArmTattoo(tArmType(charLeftForearm(char,coz))),0,1
    tArmTattooAccess(tArmType(charLeftForearm(char,coz)))=1
   EndIf
  EndIf
 ;left hand
 For limb=8 To 18
   EntityTexture pLimb(cyc,limb),tMouth(0),0,2
   EntityTexture pLimb(cyc,limb),tHands(charLeftHand(char,coz)),0,0
   tHandsAccess(charLeftHand(char,coz))=1
   If GetRace(char)>1 And tHandType(charLeftHand(char,coz))>0
    EntityTexture pLimb(cyc,limb),tHandShade(tHandType(charLeftHand(char,coz))+(GetRace(char)-2)),0,2
    tHandShadeAccess(tHandType(charLeftHand(char,coz))+(GetRace(char)-2))=1
   EndIf
 Next
 ;right arm
 limb=19 
  EntityTexture pLimb(cyc,limb),tMouth(0),0,1
  EntityTexture pLimb(cyc,limb),tMouth(0),0,2
  EntityTexture pLimb(cyc,limb),tArm(charRightArm(char,coz)),0,0
  tArmAccess(charRightArm(char,coz))=1
  If GetRace(char)>1 And tArmType(charRightArm(char,coz))>0
   EntityTexture pLimb(cyc,limb),tArmShade(tArmType(charRightArm(char,coz))+(GetRace(char)-2)),0,2
   tArmShadeAccess(tArmType(charRightArm(char,coz))+(GetRace(char)-2))=1
  EndIf
  If charTattoos(char)=1 Or charTattoos(char)=3 Or charTattoos(char)=5 Or charTattoos(char)=7
   If tArmType(charRightArm(char,coz))>0 And tArmTattoo(tArmType(charRightArm(char,coz)))>0
    EntityTexture pLimb(cyc,limb),tArmTattoo(tArmType(charRightArm(char,coz))),0,1
    tArmTattooAccess(tArmType(charRightArm(char,coz)))=1
   EndIf
  EndIf
 ;right forearm
 limb=20
  EntityTexture pLimb(cyc,limb),tMouth(0),0,1
  EntityTexture pLimb(cyc,limb),tMouth(0),0,2
  EntityTexture pLimb(cyc,limb),tArm(charRightForearm(char,coz)),0,0
  tArmAccess(charRightForearm(char,coz))=1
  If GetRace(char)>1 And tArmType(charRightForearm(char,coz))>0
   EntityTexture pLimb(cyc,limb),tArmShade(tArmType(charRightForearm(char,coz))+(GetRace(char)-2)),0,2
   tArmShadeAccess(tArmType(charRightForearm(char,coz))+(GetRace(char)-2))=1
  EndIf
  If charTattoos(char)=1 Or charTattoos(char)=3 Or charTattoos(char)=5 Or charTattoos(char)=9
   If tArmType(charRightForearm(char,coz))>0 And tArmTattoo(tArmType(charRightForearm(char,coz)))>0
    EntityTexture pLimb(cyc,limb),tArmTattoo(tArmType(charRightForearm(char,coz))),0,1
    tArmTattooAccess(tArmType(charRightForearm(char,coz)))=1
   EndIf
  EndIf
 ;right hand
 For limb=21 To 31
   EntityTexture pLimb(cyc,limb),tMouth(0),0,2
   EntityTexture pLimb(cyc,limb),tHands(charRightHand(char,coz)),0,0
   tHandsAccess(charRightHand(char,coz))=1
   If GetRace(char)>1 And tHandType(charRightHand(char,coz))>0
    EntityTexture pLimb(cyc,limb),tHandShade(tHandType(charRightHand(char,coz))+(GetRace(char)-2)),0,2
    tHandShadeAccess(tHandType(charRightHand(char,coz))+(GetRace(char)-2))=1
   EndIf
 Next
 ;legs
 For limb=36 To 42
  If limb<>40 And limb<>41
   EntityTexture pLimb(cyc,limb),tMouth(0),0,2
   EntityTexture pLimb(cyc,limb),tLegs(charLegs(char,coz)),0,0
   tLegsAccess(charLegs(char,coz))=1 
   If GetRace(char)>1 And tLegType(charLegs(char,coz))>0
    EntityTexture pLimb(cyc,limb),tLegShade(tLegType(charLegs(char,coz))+(GetRace(char)-2)),0,2 
    tLegShadeAccess(tLegType(charLegs(char,coz))+(GetRace(char)-2))=1
   EndIf
  EndIf
 Next
 ;shins
 For count=1 To 2
  If count=1 Then limb=40 Else limb=43
  EntityTexture pLimb(cyc,limb),tMouth(0),0,2
  EntityTexture pLimb(cyc,limb),tLegs(charShins(char,coz)),0,0
  tLegsAccess(charShins(char,coz))=1
  If GetRace(char)>1 And tLegType(charShins(char,coz))>0
   EntityTexture pLimb(cyc,limb),tLegShade(tLegType(charShins(char,coz))+(GetRace(char)-2)),0,2 
   tLegShadeAccess(tLegType(charShins(char,coz))+(GetRace(char)-2))=1
  EndIf
 Next
 ;shoes
 For count=1 To 2
  If count=1 Then limb=41 Else limb=44
  EntityTexture pLimb(cyc,limb),tMouth(0),0,2
  EntityTexture pLimb(cyc,limb),tLegs(charShoes(char,coz)),0,0
  tLegsAccess(charShoes(char,coz))=1
  If GetRace(char)>1 And tLegType(charShoes(char,coz))>0
   EntityTexture pLimb(cyc,limb),tLegShade(tLegType(charShoes(char,coz))+(GetRace(char)-2)),0,2 
   tLegShadeAccess(tLegType(charShoes(char,coz))+(GetRace(char)-2))=1
  EndIf
 Next 
 ;bagginess
 If charBaggy(char,coz)=2 Or charBaggy(char,coz)=3
  EntityAlpha FindChild(p(cyc),"L_Leg_Bag"),1
  EntityAlpha FindChild(p(cyc),"R_Leg_Bag"),1
  EntityAlpha FindChild(p(cyc),"L_Leg"),0
  EntityAlpha FindChild(p(cyc),"R_Leg"),0
 Else
  EntityAlpha FindChild(p(cyc),"L_Leg"),1
  EntityAlpha FindChild(p(cyc),"R_Leg"),1
  EntityAlpha FindChild(p(cyc),"L_Leg_Bag"),0
  EntityAlpha FindChild(p(cyc),"R_Leg_Bag"),0
 EndIf
 ;shorts
 If charShorts(char,coz)>0
  For limb=36 To 38
   EntityTexture pLimb(cyc,limb),tMouth(0),0,2 
   EntityTexture pLimb(cyc,limb),tLegs(charShorts(char,coz)),0,0
   tLegsAccess(charShorts(char,coz))=1
  Next
  EntityAlpha FindChild(p(cyc),"L_Short"),1
  EntityAlpha FindChild(p(cyc),"R_Short"),1
 Else
  EntityAlpha FindChild(p(cyc),"L_Short"),0
  EntityAlpha FindChild(p(cyc),"R_Short"),0
 EndIf
 ;remove limbs
 For limb=1 To 50
  If pLimb(cyc,limb)>0 And charLimb(char,limb)=0
   EntityAlpha pLimb(cyc,limb),0 
   If limb=39 Then EntityAlpha pLimb(cyc,37),0 
   If limb=42 Then EntityAlpha pLimb(cyc,38),0 
  EndIf 
 Next
End Function

;TEXTURE SUITABLE FOR SHORTS?
Function ShortsViable(option)
 viable=0
 If option=0 Or tLegType(option)=0 Then viable=1
 If tLegType(option)=5 Or tLegType(option)=7 Or tLegType(option)=9 Or tLegType(option)=11 Or tLegType(option)=19 Then viable=1
 Return viable
End Function

;HIDE/SHOW BELT
Function WearBelt(cyc,belt)
 ;hide by default
 For count=1 To 2
  HideEntity FindChild(p(cyc),"Belt0"+count)
  EntityShininess FindChild(p(cyc),"Belt0"+count),1.0
 Next
 ;show given belt
 If belt>0
  ShowEntity FindChild(p(cyc),"Belt01")
  EntityTexture FindChild(p(cyc),"Belt01"),tBelt(belt)
 EndIf
End Function

;PLAY THEME
Function PlayTheme(char)
 ;remove existing music
 If ChannelPlaying(chTheme)>0 Then StopChannel chTheme
 ;initiate character's theme
 If char=>0
  LoopSound sTheme(charTheme(char))
  If screen=50 Then chTheme=EmitSound(sTheme(charTheme(char)),cam)
  If screen<>50 Then chTheme=PlaySound(sTheme(charTheme(char)))
  ChannelPitch chTheme,charThemePitch(char)
  If charTheme(char)>0 Then chCurrentTheme=charTheme(char) : chThemeChange=-1
  If charTheme(char)=0 Then chCurrentTheme=-1 : chThemeChange=-1
 Else
  ;restore main theme
  If ChannelPlaying(chTheme)>0 Then StopChannel chTheme
  LoopSound sMainTheme
  chTheme=PlaySound(sMainTheme)
  chCurrentTheme=0 : chThemeChange=-1 
 EndIf 
 ;reset volume
 chThemeVol#=1.0
 ChannelVolume chTheme,chThemeVol#
End Function

;EDITING ADVICE
Function EditingAdvice(x,y)
 If screen=51
  ;advice
  If page=0
   If foc=6 And blockCostume=0 Then advice$="(Press TAB to copy CASUAL)" : adviceX=x : adviceY=y+13
   If foc=7 And blockCostume=0 Then advice$="(Press TAB to copy WRESTLING)" : adviceX=x : adviceY=y+13
   If foc=8 And blockCostume=0 Then advice$="(Press TAB to copy CASUAL)" : adviceX=x : adviceY=y+13
   If foc=9 And screenAgenda<>5 And blockData=0 Then advice$="(Press SPACE to randomize)" : adviceX=x : adviceY=y+13
   If foc=10 And screenAgenda<>5 And blockData=0 Then advice$="(Press SPACE to randomize)" : adviceX=x : adviceY=y+13
  EndIf
  If page=2
   If foc=12 And charPartner(0)>0 And blockData=0 Then advice$="(Press TAB to reciprocate)" : adviceX=x : adviceY=y+13
   If foc=13 And charManager(0)>0 And blockData=0 Then advice$="(Press TAB to reciprocate)" : adviceX=x : adviceY=y+13
  EndIf
  If page=9
   If foc=1 Or foc=3 And editModel<>GetModel(0) Then advice$="(Press ENTER to update model)" : adviceX=x : adviceY=y+13
  EndIf
  ;main menu warnings
  If page=0
   If foc=>9 And foc=<10 And screenAgenda=5 Then warnX=x : warnY=y+13 : warning$="(This is beyond your control)"
  EndIf
  ;profile blocks
  If page=1
   If foc=<2 And blockName=1
    warnX=x : warnY=y+13 : warning$="(This value is beyond your control)"
    If game=1 And charAgreement(editChar,1)>0 Then warnX=x : warnY=y+13 : warning$="(You agreed to change this value)"
   EndIf
   If foc=>3 And foc=<11 And blockData=1 Then warnX=x : warnY=y+13 : warning$="(This value is beyond your control)"
   If screenAgenda=5
    If foc=4 Or foc=10 Or foc=11 Then warnX=x : warnY=y+13 : warning$="(This value is beyond your control)"
   EndIf
  EndIf
  ;gimmick blocks
  If page=2
   If foc=1 And screenAgenda=5 Then warnX=x : warnY=y+13 : warning$="(This value is beyond your control)"
   If foc=2 And blockAllegiance=1
    warnX=x : warnY=y+13 : warning$="(This value is beyond your control)"
    If game=1 And charAgreement(editChar,8)>0 Then warnX=x : warnY=y+13 : warning$="(You agreed to change this value)"
   EndIf
   If foc=>2 And foc=<7 And blockTaunts=1
    warnX=x : warnY=y+13 : warning$="(This value is beyond your control)"
    If game=1 And charAgreement(editChar,7)>0 Then warnX=x : warnY=y+13 : warning$="(You agreed to change this value)"
   EndIf
   If foc=>8 And foc=<10 And blockEntrance=1
    warnX=x : warnY=y+13 : warning$="(This value is beyond your control)"
    If game=1 And charAgreement(editChar,4)>0 Then warnX=x : warnY=y+13 : warning$="(You agreed to change this value)"
   EndIf
   If foc=12 And blockPartner=1
    warnX=x : warnY=y+13 : warning$="(This value is beyond your control)"
    If game=1 And charAgreement(editChar,14)>0 Then warnX=x : warnY=y+13 : warning$="(You agreed to change this value)"
   EndIf
   If foc=13 And blockManager=1
    warnX=x : warnY=y+13 : warning$="(This value is beyond your control)"
    If game=1 And charAgreement(editChar,15)>0 Then warnX=x : warnY=y+13 : warning$="(You agreed to change this value)"
   EndIf
   If game=1 And foc=>8 And foc=<9 And fedProduction(charFed(gamChar),6)=0 Then warnX=x : warnY=y+13 : warning$="(Requires the 'Sound System' production)"
   If game=1 And foc=10 And fedProduction(charFed(gamChar),7)=0 Then warnX=x : warnY=y+13 : warning$="(Requires the 'Lighting' production)"
   If game=1 And foc=11 And fedProduction(charFed(gamChar),12)=0 Then warnX=x : warnY=y+13 : warning$="(Requires the 'Props' production)"
  EndIf
  ;attack blocks
  If page=3
   If foc=>1 And foc=<9 And blockAttacks=1
    warnX=x : warnY=y+13 : warning$="(This value is beyond your control)"
    If game=1 And fedProduction(charFed(gamChar),3)=0 Then warnX=x : warnY=y+13 : warning$="(Requires the 'Personal Trainers' production)"
    If game=1 And charAgreement(editChar,5)>0 Then warnX=x : warnY=y+13 : warning$="(You agreed to change this value)"
   EndIf
  EndIf
  ;move blocks
  If page=4
   If foc=>1 And foc=<12 And blockMoves=1
    warnX=x : warnY=y+13 : warning$="(This value is beyond your control)"
    If game=1 And fedProduction(charFed(gamChar),3)=0 Then warnX=x : warnY=y+13 : warning$="(Requires the 'Personal Trainers' production)"
    If game=1 And charAgreement(editChar,6)>0 Then warnX=x : warnY=y+13 : warning$="(You agreed to change this value)"
   EndIf
  EndIf
  If page=5
   If foc=>1 And foc=<9 And blockMoves=1
    warnX=x : warnY=y+13 : warning$="(This value is beyond your control)"
    If game=1 And fedProduction(charFed(gamChar),3)=0 Then warnX=x : warnY=y+13 : warning$="(Requires the 'Personal Trainers' production)"
    If game=1 And charAgreement(editChar,6)>0 Then warnX=x : warnY=y+13 : warning$="(You agreed to change this value)"
   EndIf
  EndIf
  ;costume blocks
  If page=>6 And page=<8
   If foc=>1 And foc=<15 And blockCostume=1
    warnX=x : warnY=y+13 : warning$="(This value is beyond your control)"
    If game=1 And fedProduction(charFed(gamChar),4)=0 Then warnX=x : warnY=y+13 : warning$="(Requires the 'Fashion Stylists' production)"
    If game=1 And charAgreement(editChar,3)>0 Then warnX=x : warnY=y+13 : warning$="(You agreed to change this value)"
   EndIf
  EndIf
  ;appearance blocks
  If page=9
   If (foc=>1 And foc=<3) Or foc=6 Or (foc=>9 And foc=<11)
    If blockData=1 Then warnX=x : warnY=y+13 : warning$="(This value is beyond your control)"
   EndIf
   If foc=>4 And foc=<5 And blockHair=1
    warnX=x : warnY=y+13 : warning$="(This value is beyond your control)"
    If game=1 And fedProduction(charFed(gamChar),4)=0 Then warnX=x : warnY=y+13 : warning$="(Requires the 'Fashion Stylists' production)"
    If game=1 And charAgreement(editChar,2)>0 Then warnX=x : warnY=y+13 : warning$="(You agreed to change this value)"
   EndIf
   If (foc=7 Or foc=12) And blockCostume=1
    warnX=x : warnY=y+13 : warning$="(This value is beyond your control)"
    If game=1 And fedProduction(charFed(gamChar),4)=0 Then warnX=x : warnY=y+13 : warning$="(Requires the 'Fashion Stylists' production)"
    If game=1 And charAgreement(editChar,3)>0 Then warnX=x : warnY=y+13 : warning$="(You agreed to change this value)"
   EndIf
   If foc=8 And blockAllegiance=1
    warnX=x : warnY=y+13 : warning$="(This value is beyond your control)"
    If game=1 And charAgreement(editChar,8)>0 Then warnX=x : warnY=y+13 : warning$="(You agreed to change this value)"
   EndIf
  EndIf 
 EndIf
 ;production limitations
 If game=1 And screen=17 
  If foc=>1 And foc=<2 And fedProduction(charFed(gamChar),10)=0 Then warnX=x : warnY=y+13 : warning$="(Requires the 'Engineering' production)"
  If foc=3 And fedProduction(charFed(gamChar),9)=0 Then warnX=x : warnY=y+13 : warning$="(Requires the 'Pyrotechnics' production)"
  If foc=>4 And foc=<6 And fedProduction(charFed(gamChar),11)=0 Then warnX=x : warnY=y+13 : warning$="(Requires the 'Furniture' production)"
  If foc=>7 And foc=<9 And fedProduction(charFed(gamChar),12)=0 Then warnX=x : warnY=y+13 : warning$="(Requires the 'Props' production)"
  If foc=>11 And foc=<12 And fedProduction(charFed(gamChar),1)=0 Then warnX=x : warnY=y+13 : warning$="(Requires the 'Script Writers' production)"
 EndIf
 If game=1 And screen=13
  If foc=1 Or (foc=>5 And foc=<9)
   If fedProduction(charFed(gamChar),5)=0 Then warnX=x : warnY=y+13 : warning$="(Requires the 'Ring Crew' production)"
  EndIf
  If foc=3 And fedProduction(charFed(gamChar),7)=0 Then warnX=x : warnY=y+13 : warning$="(Requires the 'Lighting' production)"
  If foc=2 Or foc=4 Then warnX=x : warnY=y+13 : warning$="(This value is beyond your control)"
 EndIf
End Function

;CALCULATE GIMMICK EXPENSES
Function GimmickExpenses()
 value=0
 If screenAgenda<>5
  ;gimmicks
  If charTheme(0)>0 And charTheme(0)<>charTheme(gamChar) Then value=value+250
  If charLight(0)>0 And charLight(0)<>charLight(gamChar) Then value=value+100 
  ;costumes
  If charHair(0,1)<>charHair(gamChar,1) Then value=value+25
  If charHairStyle(0,1)<>charHairStyle(gamChar,1) Then value=value+25
  For count=1 To 3
   If charHatStyle(0,count)>0 And charHatStyle(0,count)<>charHatStyle(gamChar,count) Then value=value+25
   If charHatStyle(0,count)>0 And charHatStyle(0,count)=charHatStyle(gamChar,count) And charHat(0,count)<>charHat(gamChar,count) Then value=value+25
   If charSpecs(0,count)>0 And charSpecs(0,count)<>charSpecs(gamChar,count) Then value=value+25
   If charBody(0,count)<>charBody(gamChar,count) Then value=value+25
   If charLeftArm(0,count)<>charLeftArm(gamChar,count) Then value=value+25
   If charRightArm(0,count)<>charRightArm(gamChar,count) Then value=value+25
   If charShorts(0,count)>0 And charShorts(0,count)<>charShorts(gamChar,count) And charShorts(0,count)<>charLegs(gamChar,count) And charShorts(0,count)<>charLegs(0,count) Then value=value+25 
   If charLegs(0,count)<>charLegs(gamChar,count) Then value=value+25
   If charShoes(0,count)<>charLegs(0,count) And charShoes(0,count)<>charShoes(gamChar,count) Then value=value+25 
  Next
 EndIf
 Return value
End Function