;//////////////////////////////////////////////////////////////////////////////
;-------------------------- WRESTLING MPIRE 2008: DATA ------------------------
;//////////////////////////////////////////////////////////////////////////////

;------------------------------------------------------------------------
;///////////////////////////// OPTIONS //////////////////////////////////
;------------------------------------------------------------------------

;SAVE OPTIONS
Function SaveOptions()
 file=WriteFile("Data/Options.dat")
  ;preferences
  WriteInt file,optLevel
  WriteInt file,optRatingsLevel
  WriteInt file,optLength
  WriteInt file,optSpeed
  WriteInt file,optEntrances
  WriteInt file,optReferees
  WriteInt file,optManagers
  WriteInt file,optIntruders
  WriteInt file,optHideElim
  WriteInt file,optTagControl
  ;display
  WriteInt file,optGameRes
  WriteInt file,optMenuRes
  WriteInt file,optDefaultCam
  WriteInt file,optFog
  WriteInt file,optDetail
  WriteInt file,optFX
  WriteInt file,optShadows
  WriteInt file,optGore
  WriteInt file,optMeters
  WriteInt file,optGrid
  WriteInt file,optCrowdAnim
  WriteInt file,entDisplay
  WriteInt file,optMusicVolume
  WriteInt file,optJukeBox
  ;key layout
  WriteInt file,keyAttack
  WriteInt file,keyGrapple
  WriteInt file,keyRun
  WriteInt file,keyPickUp
  WriteInt file,keySwitch
  WriteInt file,keyTaunt
  ;button layout
  WriteInt file,buttAttack
  WriteInt file,buttGrapple
  WriteInt file,buttRun
  WriteInt file,buttPickUp
  WriteInt file,buttSwitch
  WriteInt file,buttTaunt
  WriteInt file,buttSelect
  ;future proofing - thanks MDickie!
  For count=1 To 1000
   WriteInt file,0
  Next
 CloseFile(file)
End Function

;LOAD OPTIONS
Function LoadOptions()
 file=ReadFile("Data/Options.dat")
  ;preferences
  optLevel=ReadInt(file)
  optRatingsLevel=ReadInt(file)
  optLength=ReadInt(file)
  optSpeed=ReadInt(file)
  optEntrances=ReadInt(file)
  optReferees=ReadInt(file)
  optManagers=ReadInt(file)
  optIntruders=ReadInt(file)
  optHideElim=ReadInt(file)
  optTagControl=ReadInt(file)
  ;display
  optGameRes=ReadInt(file)
  optMenuRes=ReadInt(file)
  optDefaultCam=ReadInt(file)
  optFog=ReadInt(file)
  optDetail=ReadInt(file)
  optFX=ReadInt(file)
  optShadows=ReadInt(file)
  optGore=ReadInt(file)
  optMeters=ReadInt(file)
  optGrid=ReadInt(file)
  optCrowdAnim=ReadInt(file)
  entDisplay=ReadInt(file)
  optMusicVolume=ReadInt(file)
  void=ReadInt(file)
  ;key layout
  keyAttack=ReadInt(file)
  keyGrapple=ReadInt(file)
  keyRun=ReadInt(file)
  keyPickUp=ReadInt(file)
  keySwitch=ReadInt(file)
  keyTaunt=ReadInt(file)
  ;button layout
  buttAttack=ReadInt(file)
  buttGrapple=ReadInt(file)
  buttRun=ReadInt(file)
  buttPickUp=ReadInt(file)
  buttSwitch=ReadInt(file)
  buttTaunt=ReadInt(file)
  buttSelect=ReadInt(file)
 CloseFile(file)
End Function

;------------------------------------------------------------------------
;////////////////////////////// UNIVERSE ////////////////////////////////
;------------------------------------------------------------------------

;SAVE UNIVERSE
Function SaveUniverse()
 file=WriteFile("Data/Universe.dat")
  ;slot status
  For cyc=1 To 3
   WriteInt file,slotActive(cyc)
  Next
  ;editor locks
  For cyc=1 To 9
   WriteInt file,fedLocked(cyc)
  Next
  ;tournaments
  For cyc=0 To 10
   WriteInt file,cupSpan(cyc)
   WriteInt file,cupFed(cyc)
   WriteInt file,cupTeams(cyc)
   WriteInt file,cupReward(cyc)
   WriteInt file,cupSize(cyc)
   WriteInt file,cupControl(cyc)
   WriteInt file,cupFoc(cyc)
   For b=1 To 32 
    WriteInt file,cupChar(cyc,b)
    For count=1 To 2
     WriteInt file,cupBracket(cyc,b,count)
    Next 
    WriteInt file,cupResult(cyc,b)
   Next 
   For char=1 To optCharLim
    WriteInt file,cupCharControl(cyc,char)
    WriteInt file,cupCharPartner(cyc,char)
    WriteInt file,cupCharHealth(cyc,char)
    For count=1 To 5
     WriteInt file,cupCharInjured(cyc,char,count)
    Next 
   Next
  Next
  ;hi scores
  For cyc=1 To 10
   WriteInt file,hiChar(cyc)
   WriteString file,hiName$(cyc)
   WriteFloat file,hiPhotoHeight#(cyc)
   WriteInt file,hiPhotoR(cyc)
   WriteInt file,hiPhotoG(cyc)
   WriteInt file,hiPhotoB(cyc)
   WriteInt file,hiWealth(cyc)
   WriteInt file,hiPopularity(cyc)
   WriteInt file,hiReputation(cyc)
   WriteInt file,hiExperience(cyc)
   WriteInt file,hiFed(cyc)
   WriteInt file,void
  Next
  ;future proofing
  For count=1 To 1000
   WriteInt file,0
  Next
 CloseFile(file)
End Function

;LOAD UNIVERSE
Function LoadUniverse()
 file=ReadFile("Data/Universe.dat")
  ;slot status
  For cyc=1 To 3
   slotActive(cyc)=ReadInt(file)
   slotPreview(cyc)=LoadImage("Data/Previews/Preview0"+cyc+".bmp")
   MaskImage slotPreview(cyc),255,0,255
  Next
  ;editor locks
  For cyc=1 To 9
   fedLocked(cyc)=ReadInt(file)
  Next 
  ;tournaments
  For cyc=0 To 10
   cupSpan(cyc)=ReadInt(file) 
   cupFed(cyc)=ReadInt(file)
   cupTeams(cyc)=ReadInt(file)
   cupReward(cyc)=ReadInt(file)
   cupSize(cyc)=ReadInt(file)
   cupControl(cyc)=ReadInt(file)
   cupFoc(cyc)=ReadInt(file)
   For b=1 To 32 
    cupChar(cyc,b)=ReadInt(file)
    For count=1 To 2
     cupBracket(cyc,b,count)=ReadInt(file)
    Next 
    cupResult(cyc,b)=ReadInt(file)
   Next 
   For char=1 To optCharLim
    cupCharControl(cyc,char)=ReadInt(file)
    cupCharPartner(cyc,char)=ReadInt(file)
    cupCharHealth(cyc,char)=ReadInt(file)
    For count=1 To 5
     cupCharInjured(cyc,char,count)=ReadInt(file)
    Next 
   Next
  Next
  ;hi scores
  For cyc=1 To 10
   hiChar(cyc)=ReadInt(file)
   hiName$(cyc)=ReadString(file)
   hiPhotoHeight#(cyc)=ReadFloat(file)
   hiPhotoR(cyc)=ReadInt(file)
   hiPhotoG(cyc)=ReadInt(file)
   hiPhotoB(cyc)=ReadInt(file)
   hiPhoto(cyc)=LoadImage("Data/Hall Of Fame/Photo"+Dig$(cyc,10)+".bmp")
   MaskImage hiPhoto(cyc),hiPhotoR(cyc),hiPhotoG(cyc),hiPhotoB(cyc) 
   hiWealth(cyc)=ReadInt(file)
   hiPopularity(cyc)=ReadInt(file)
   hiReputation(cyc)=ReadInt(file)
   hiExperience(cyc)=ReadInt(file)
   hiFed(cyc)=ReadInt(file)
   void=ReadInt(file)
  Next
 CloseFile(file)
End Function

;------------------------------------------------------------------------
;///////////////////////////// PROGRESS /////////////////////////////////
;------------------------------------------------------------------------

;SAVE PROGRESS
Function SaveProgress(directory)
 If directory<0 Then file=WriteFile("Data/Backup/Progress.dat")
 If directory=>0 Then file=WriteFile("Data/Slot0"+directory+"/Progress.dat")
  ;status
  WriteInt file,gamChar  
  WriteInt file,gamFatality
  WriteInt file,gamBuild
  WriteInt file,gamComplete
  ;schedule
  WriteInt file,gamDate
  WriteInt file,gamYear
  WriteInt file,gamMission
  WriteInt file,gamTarget
  WriteInt file,gamDeadline
  For date=1 To 48
   WriteInt file,gamSchedule(date)
   WriteInt file,gamRivalFed(date)
   WriteInt file,gamCourtCase(date)
   WriteInt file,gamCourtChar(date)
   ;arena properties
   WriteInt file,gamVenue(date) 
   WriteInt file,gamAtmos(date)
   WriteInt file,gamAttendance(date)
   WriteInt file,gamRopes(date)
   WriteInt file,gamPads(date)
   WriteInt file,gamCanvas(date)
   WriteInt file,gamApron(date)
   WriteInt file,gamMatting(date)
   ;ratings
   WriteInt file,gamSegments(date)
   For count=1 To 10
    WriteInt file,gamMatchScore(date,count)
    WriteInt file,gamMatchHardcore(date,count)
    WriteInt file,gamMatchWinner(date,count)
    WriteInt file,gamMatchLoser(date,count)
    WriteInt file,gamMatchFormat(date,count)
   Next
   WriteInt file,gamNightScore(date)
   WriteInt file,gamNightHardcore(date)
  Next
  ;TV ratings
  For count=1 To 20
   WriteString file,showName$(count)
   WriteString file,showGenre$(count)
   WriteInt file,showPopularity(count)
   WriteInt file,showOldPopularity(count)
   WriteInt file,showRanked(count)
   WriteInt file,showOldRanked(count)
   WriteInt file,showList(count)
   WriteInt file,showOldList(count)
  Next
  ;agreements
  For count=1 To 50
   WriteInt file,gamAgreement(count)
  Next
  ;negotiation history
  For v=1 To optCharLim
   For count=1 To 3
    WriteInt file,gamNegotiated(v,count)
   Next
  Next 
  ;future proofing
  For count=1 To 1000
   WriteInt file,0
  Next
 CloseFile(file)
End Function

;LOAD PROGRESS
Function LoadProgress(directory)
 If directory<0 Then file=ReadFile("Data/Backup/Progress.dat")
 If directory=>0 Then file=ReadFile("Data/Slot0"+directory+"/Progress.dat")
  ;status
  gamChar=ReadInt(file)
  gamFatality=ReadInt(file)
  gamBuild=ReadInt(file)
  gamComplete=ReadInt(file)
  ;schedule
  gamDate=ReadInt(file)
  gamYear=ReadInt(file)
  gamMission=ReadInt(file)
  gamTarget=ReadInt(file)
  gamDeadline=ReadInt(file)
  For date=1 To 48
   gamSchedule(date)=ReadInt(file)
   gamRivalFed(date)=ReadInt(file)
   gamCourtCase(date)=ReadInt(file)
   gamCourtChar(date)=ReadInt(file)
   ;arena properties
   gamVenue(date)=ReadInt(file) 
   gamAtmos(date)=ReadInt(file)
   gamAttendance(date)=ReadInt(file)
   gamRopes(date)=ReadInt(file)
   gamPads(date)=ReadInt(file)
   gamCanvas(date)=ReadInt(file)
   gamApron(date)=ReadInt(file)
   gamMatting(date)=ReadInt(file)
   ;ratings
   gamSegments(date)=ReadInt(file)
   For count=1 To 10
    gamMatchScore(date,count)=ReadInt(file)
    gamMatchHardcore(date,count)=ReadInt(file)
    gamMatchWinner(date,count)=ReadInt(file)
    gamMatchLoser(date,count)=ReadInt(file)
    gamMatchFormat(date,count)=ReadInt(file)
   Next
   gamNightScore(date)=ReadInt(file)
   gamNightHardcore(date)=ReadInt(file)
  Next
  ;TV ratings
  For count=1 To 20
   showName$(count)=ReadString(file)
   showGenre$(count)=ReadString(file)
   showPopularity(count)=ReadInt(file)
   showOldPopularity(count)=ReadInt(file)
   showRanked(count)=ReadInt(file)
   showOldRanked(count)=ReadInt(file)
   showList(count)=ReadInt(file)
   showOldList(count)=ReadInt(file)
  Next
  ;agreements
  For count=1 To 50
   gamAgreement(count)=ReadInt(file)
  Next
  ;negotiation history
  For v=1 To optCharLim
   For count=1 To 3
    gamNegotiated(v,count)=ReadInt(file)
   Next
  Next 
 CloseFile(file)
End Function

;------------------------------------------------------------------------
;////////////////////////////// WORLD ///////////////////////////////////
;------------------------------------------------------------------------

;SAVE WORLD
Function SaveWorld(directory)
 If directory<0 Then file=WriteFile("Data/Backup/World.dat")
 If directory=>0 Then file=WriteFile("Data/Slot0"+directory+"/World.dat") 
  ;universal
  WriteInt file,no_chars
  For cyc=1 To 9
   ;status
   WriteString file,fedName$(cyc)
   WriteInt file,fedBank(cyc)
   WriteInt file,fedPopularity(cyc)
   WriteInt file,fedReputation(cyc)
   WriteInt file,fedOldPopularity(cyc)
   WriteInt file,fedOldReputation(cyc) 
   ;roster
   WriteInt file,fedSize(cyc)
   For count=1 To 50
    WriteInt file,fedRoster(cyc,count)
   Next
   WriteInt file,fedFatality(cyc)
   ;title history
   WriteInt file,fedBooker(cyc)
   WriteInt file,fedChampWorld(cyc)
   WriteInt file,fedChampInter(cyc)
   For count=1 To 2
    WriteInt file,fedChampTag(cyc,count)
   Next
   WriteInt file,fedCupHolder(cyc)
   For title=0 To 4
    For count=1 To 10
     WriteInt file,fedHistCount(cyc,title,count)
     WriteInt file,fedHistChar(cyc,title,count)
     WriteInt file,fedHistPartner(cyc,title,count)
     WriteInt file,fedHistDate(cyc,title,count)
     WriteInt file,fedHistYear(cyc,title,count)
    Next
   Next
   ;production
   For count=1 To 20
    WriteInt file,fedProduction(cyc,count)
   Next
   ;rankings
   WriteInt file,fedList(cyc)
   WriteInt file,fedOldList(cyc)
   WriteInt file,fedRanked(cyc)
   WriteInt file,fedOldRanked(cyc)
  Next
  ;future proofing
  For count=1 To 1000
   WriteInt file,0
  Next
 CloseFile(file)
End Function

;LOAD WORLD
Function LoadWorld(directory)
 If directory<0 Then file=ReadFile("Data/Backup/World.dat")
 If directory=>0 Then file=ReadFile("Data/Slot0"+directory+"/World.dat")
  ;universal
  no_chars=ReadInt(file)
  For cyc=1 To 9
   ;status
   fedName$(cyc)=ReadString(file)
   fedBank(cyc)=ReadInt(file)
   fedPopularity(cyc)=ReadInt(file)
   fedReputation(cyc)=ReadInt(file)
   fedOldPopularity(cyc)=ReadInt(file)
   fedOldReputation(cyc)=ReadInt(file) 
   ;roster
   fedSize(cyc)=ReadInt(file)
   For count=1 To 50
    fedRoster(cyc,count)=ReadInt(file)
   Next
   fedFatality(cyc)=ReadInt(file)
   ;title history
   fedBooker(cyc)=ReadInt(file)
   fedChampWorld(cyc)=ReadInt(file)
   fedChampInter(cyc)=ReadInt(file)
   For count=1 To 2
    fedChampTag(cyc,count)=ReadInt(file)
   Next
   fedCupHolder(cyc)=ReadInt(file)
   For title=0 To 4
    For count=1 To 10
     fedHistCount(cyc,title,count)=ReadInt(file)
     fedHistChar(cyc,title,count)=ReadInt(file)
     fedHistPartner(cyc,title,count)=ReadInt(file)
     fedHistDate(cyc,title,count)=ReadInt(file)
     fedHistYear(cyc,title,count)=ReadInt(file)
    Next
   Next
   ;production
   For count=1 To 20
    fedProduction(cyc,count)=ReadInt(file)
   Next
   ;rankings
   fedList(cyc)=ReadInt(file)
   fedOldList(cyc)=ReadInt(file)
   fedRanked(cyc)=ReadInt(file)
   fedOldRanked(cyc)=ReadInt(file)
  Next
 CloseFile(file)
End Function

;------------------------------------------------------------------------
;//////////////////////////// CHARACTERS ////////////////////////////////
;------------------------------------------------------------------------

;SAVE ALL CHARACTERS
Function SaveChars(directory)
 For char=1 To no_chars
  SaveChar(char,directory)
 Next
End Function

;SAVE INDIVIDUAL CHARACTER
Function SaveChar(char,directory)
  CheckLimits(char)
  If directory<0 Then file=WriteFile("Data/Backup/Character"+Dig$(char,100)+".dat") 
  If directory=>0 Then file=WriteFile("Data/Slot0"+directory+"/Character"+Dig$(char,100)+".dat")
   ;profile
   WriteString file,charName$(char)
   WriteString file,charTeamName$(char)
   WriteInt file,charAge(char)
   WriteInt file,charHeight(char)
   WriteInt file,charWeight(char)
   WriteInt file,charGender(char)
   WriteInt file,charPopularity(char)
   WriteInt file,charStrength(char)
   WriteInt file,charSkill(char)
   WriteInt file,charAgility(char)
   WriteInt file,charStamina(char)
   WriteInt file,charToughness(char)
   WriteInt file,charAttitude(char)
   WriteInt file,charHappiness(char)
   WriteInt file,charOldPopularity(char)
   WriteInt file,charOldStrength(char)
   WriteInt file,charOldSkill(char)
   WriteInt file,charOldAgility(char)
   WriteInt file,charOldStamina(char)
   WriteInt file,charOldToughness(char)
   WriteInt file,charOldAttitude(char)
   WriteInt file,charOldHappiness(char)
   ;gimmick
   WriteInt file,charRole(char)
   WriteInt file,charHeel(char)
   WriteInt file,charEyes(char)
   WriteInt file,charStance(char)
   For count=1 To 4
    WriteInt file,charTaunt(char,count)
   Next
   WriteInt file,charTheme(char)
   WriteInt file,charThemePitch(char)
   WriteInt file,charLight(char)
   WriteInt file,charWeapon(char)
   WriteInt file,charPartner(char)
   WriteInt file,charManager(char)
   ;attacks  
   For count=1 To 5
    WriteInt file,charAttack(char,count)
    WriteInt file,charCrush(char,count)
   Next
   ;moves
   For count=1 To 15
    WriteInt file,charMove(char,count)
   Next
   For count=1 To 6
    WriteInt file,charGroundMove(char,count)
   Next
   ;costumes
   For coz=1 To 3
    WriteInt file,charBaggy(char,coz)
    WriteInt file,charHatStyle(char,coz)
    WriteInt file,charHat(char,coz)
    WriteInt file,charSpecs(char,coz)
    WriteInt file,charHairStyle(char,coz)
    WriteInt file,charHair(char,coz)
    WriteInt file,charFace(char,coz)
    WriteInt file,charBody(char,coz)
    WriteInt file,charLeftArm(char,coz)
    WriteInt file,charRightArm(char,coz)
    WriteInt file,charShorts(char,coz)
    WriteInt file,charLegs(char,coz)
    WriteInt file,charShoes(char,coz)
   Next
   WriteInt file,charPhotoR(char)
   WriteInt file,charPhotoG(char)
   WriteInt file,charPhotoB(char)
   ;career status
   WriteInt file,charFed(char)
   WriteInt file,charWorth(char)
   WriteInt file,charBank(char)
   WriteInt file,charContract(char)
   WriteInt file,charSalary(char)
   For count=1 To 3
    WriteInt file,charClause(char,count)
   Next
   WriteInt file,charVacant(char)
   WriteInt file,charArrived(char)
   WriteInt file,charWorked(char)
   For count=1 To 9
    WriteInt file,charExperience(char,count) 
    WriteInt file,charMatches(char,count)
    WriteInt file,charWins(char,count)
    For title=1 To 4
     WriteInt file,charTitles(char,count,title)
    Next
   Next
   WriteInt file,charTrainCourse(char)
   WriteInt file,charTrainLevel(char)
   WriteInt file,charWeightChange(char)
   WriteInt file,charPeaked(char)
   WriteInt file,charVariable(char)
   ;health status
   WriteInt file,charHealth(char)
   For count=0 To 5
    WriteInt file,charInjured(char,count)
    WriteInt file,charOldInjured(char,count)
   Next 
   For limb=1 To 50
    WriteInt file,charLimb(char,limb)
   Next
   ;agreements
   For count=1 To 50
    WriteInt file,charAgreement(char,count) 
   Next
   ;new data
   WriteInt file,charEyeballs(char)
   WriteInt file,charEyeShape(char)
   WriteInt file,charHeadSize(char)
   WriteInt file,charTattoos(char) 
   For coz=1 To 3
    WriteInt file,charBeard(char,coz)
    WriteInt file,charLeftForearm(char,coz)
    WriteInt file,charRightForearm(char,coz)
    WriteInt file,charLeftHand(char,coz)
    WriteInt file,charRightHand(char,coz)
    WriteInt file,charShins(char,coz)
   Next
   ;relationships
   WriteInt file,charTradeReaction(char)
   For count=1 To optCharLim
    WriteInt file,charRelationship(char,count) 
    WriteInt file,charRealRelationship(char,count) 
    WriteInt file,charTeamHistory(char,count)
    WriteInt file,charHistory(char,count)
   Next
   ;future proofing
   For count=1 To 1000
    WriteInt file,0
   Next
  CloseFile(file)
End Function

;LOAD ALL CHARACTERS
Function LoadChars(directory)
 ;restore individual data
 For char=1 To no_chars
  LoadChar(char,directory)
  If FindOnRoster(char,charFed(char))=0 And fedSize(charFed(char))<optRosterLim
   fedSize(charFed(char))=fedSize(charFed(char))+1
   fedRoster(charFed(char),fedSize(charFed(char)))=char
  EndIf
 Next 
 ;confirm feds
 For f=1 To 9
  For count=1 To fedSize(f)
   charFed(fedRoster(f,count))=f
  Next
 Next
 ;check limits
 For char=1 To no_chars
  CheckLimits(char)
 Next
End Function

;LOAD INDIVIDUAL CHARACTER
Function LoadChar(char,directory)
  If directory<0 Then file=ReadFile("Data/Backup/Character"+Dig$(char,100)+".dat") 
  If directory=>0 Then file=ReadFile("Data/Slot0"+directory+"/Character"+Dig$(char,100)+".dat")
  If file>0
   ;profile
   charName$(char)=ReadString(file)
   charTeamName$(char)=ReadString(file)
   charAge(char)=ReadInt(file)
   charHeight(char)=ReadInt(file)
   charWeight(char)=ReadInt(file)
   charGender(char)=ReadInt(file)
   charPopularity(char)=ReadInt(file)
   charStrength(char)=ReadInt(file)
   charSkill(char)=ReadInt(file)
   charAgility(char)=ReadInt(file)
   charStamina(char)=ReadInt(file)
   charToughness(char)=ReadInt(file)
   charAttitude(char)=ReadInt(file)
   charHappiness(char)=ReadInt(file)
   charOldPopularity(char)=ReadInt(file)
   charOldStrength(char)=ReadInt(file)
   charOldSkill(char)=ReadInt(file)
   charOldAgility(char)=ReadInt(file)
   charOldStamina(char)=ReadInt(file)
   charOldToughness(char)=ReadInt(file)
   charOldAttitude(char)=ReadInt(file)
   charOldHappiness(char)=ReadInt(file)
   ;gimmick
   charRole(char)=ReadInt(file)
   charHeel(char)=ReadInt(file)
   charEyes(char)=ReadInt(file)
   charStance(char)=ReadInt(file)
   For count=1 To 4
    charTaunt(char,count)=ReadInt(file)
   Next
   charTheme(char)=ReadInt(file)
   charThemePitch(char)=ReadInt(file)
   charLight(char)=ReadInt(file)
   charWeapon(char)=ReadInt(file)
   charPartner(char)=ReadInt(file)
   charManager(char)=ReadInt(file)
   ;attacks  
   For count=1 To 5
    charAttack(char,count)=ReadInt(file)
    charCrush(char,count)=ReadInt(file)
   Next
   ;moves
   For count=1 To 15
    charMove(char,count)=ReadInt(file)
   Next
   For count=1 To 6
    charGroundMove(char,count)=ReadInt(file)
   Next
   ;costumes
   For coz=1 To 3
    charBaggy(char,coz)=ReadInt(file)
    charHatStyle(char,coz)=ReadInt(file)
    charHat(char,coz)=ReadInt(file)
    charSpecs(char,coz)=ReadInt(file)
    charHairStyle(char,coz)=ReadInt(file)
    charHair(char,coz)=ReadInt(file)
    charFace(char,coz)=ReadInt(file)
    charBody(char,coz)=ReadInt(file)
    charLeftArm(char,coz)=ReadInt(file)
    charRightArm(char,coz)=ReadInt(file)
    charShorts(char,coz)=ReadInt(file)
    charLegs(char,coz)=ReadInt(file)
    charShoes(char,coz)=ReadInt(file)
   Next
   charPhotoR(char)=ReadInt(file)
   charPhotoG(char)=ReadInt(file)
   charPhotoB(char)=ReadInt(file)
   ;career status
   charFed(char)=ReadInt(file)
   charWorth(char)=ReadInt(file)
   charBank(char)=ReadInt(file)
   charContract(char)=ReadInt(file)
   charSalary(char)=ReadInt(file)
   For count=1 To 3
    charClause(char,count)=ReadInt(file)
   Next
   charVacant(char)=ReadInt(file)
   charArrived(char)=ReadInt(file)
   charWorked(char)=ReadInt(file)
   For count=1 To 9
    charExperience(char,count)=ReadInt(file)
    charMatches(char,count)=ReadInt(file)
    charWins(char,count)=ReadInt(file)
    For title=1 To 4
     charTitles(char,count,title)=ReadInt(file)
    Next
   Next
   charTrainCourse(char)=ReadInt(file)
   charTrainLevel(char)=ReadInt(file)
   charWeightChange(char)=ReadInt(file)
   charPeaked(char)=ReadInt(file)
   charVariable(char)=ReadInt(file)
   ;health status
   charHealth(char)=ReadInt(file)
   For count=0 To 5
    charInjured(char,count)=ReadInt(file)
    charOldInjured(char,count)=ReadInt(file)
   Next 
   For limb=1 To 50
    charLimb(char,limb)=ReadInt(file)
   Next
   ;agreements
   For count=1 To 50
    charAgreement(char,count)=ReadInt(file) 
   Next
   ;new data
   charEyeballs(char)=ReadInt(file)
   If charEyeballs(char)=0 Then charEyeballs(char)=2
   charEyeShape(char)=ReadInt(file)
   charHeadSize(char)=ReadInt(file)
   If charHeadSize(char)=0 Then charHeadSize(char)=95
   charTattoos(char)=ReadInt(file)
   For coz=1 To 3
    charBeard(char,coz)=ReadInt(file) 
    charLeftForearm(char,coz)=ReadInt(file) 
    charRightForearm(char,coz)=ReadInt(file) 
    If charLeftForearm(char,coz)=0 Then charLeftForearm(char,coz)=charLeftArm(char,coz)
    If charRightForearm(char,coz)=0 Then charRightForearm(char,coz)=charRightArm(char,coz)
    charLeftHand(char,coz)=ReadInt(file) 
    charRightHand(char,coz)=ReadInt(file) 
    If charLeftHand(char,coz)=0 Then charLeftHand(char,coz)=1
    If charRightHand(char,coz)=0 Then charRightHand(char,coz)=1
    charShins(char,coz)=ReadInt(file) 
    If charShins(char,coz)=0 Then charShins(char,coz)=charLegs(char,coz)
   Next
   ;relationships
   charTradeReaction(char)=ReadInt(file)
   For count=1 To optCharLim
    charRelationship(char,count)=ReadInt(file) 
    charRealRelationship(char,count)=ReadInt(file) 
    charTeamHistory(char,count)=ReadInt(file)
    charHistory(char,count)=ReadInt(file)
   Next
  CloseFile(file)
  EndIf
End Function

;LOAD PHOTOS
Function LoadPhotos(directory)
 For char=0 To no_chars
  ;Loader("Please Wait","Loading Portrait "+char+" of "+no_chars)
  filer$="Data/Backup/Portraits/"
  If directory=>0 And char>0 Then filer$="Data/Slot0"+slot+"/Portraits/"
  charPhoto(char)=LoadImage(filer$+"Photo"+Dig$(char,100)+".bmp")
  If charPhoto(char)=0
   charPhoto(char)=CopyImage(charPhoto(0))
   SaveImage(charPhoto(char),filer$+"Portraits/Photo"+Dig$(char,100)+".bmp")
   charPhotoR(char)=25 : charPhotoG(char)=5 : charPhotoB(char)=5
  EndIf
  If char=0 Then charPhotoR(char)=25 : charPhotoG(char)=5 : charPhotoB(char)=5
  MaskImage charPhoto(char),charPhotoR(char),charPhotoG(char),charPhotoB(char) 
 Next 
End Function

;SAVE PHOTOS
Function SavePhotos(directory)
 For char=1 To no_chars
  If directory<0 Then SaveImage(charPhoto(char),"Data/Backup/Portraits/Photo"+Dig$(char,100)+".bmp")
  If directory=>0 Then SaveImage(charPhoto(char),"Data/Slot0"+slot+"/Portraits/Photo"+Dig$(char,100)+".bmp")
 Next 
End Function

;-----------------------------------------------------------------
;////////////////////// RELATED FUNCTIONS ////////////////////////
;-----------------------------------------------------------------

;CHECK CHARACTER LIMITS
Function CheckLimits(char)
 ;profile
 If charAge(char)<16 Then charAge(char)=16
 If charAge(char)>70 Then charAge(char)=70
 If charHeight(char)<0 Then charHeight(char)=0
 If charHeight(char)>35 Then charHeight(char)=35
 If charWeight(char)<75 Then charWeight(char)=75
 If charWeight(char)>300 Then charWeight(char)=300
 If charGender(char)<0 Then charGender(char)=1
 If charGender(char)>1 Then charGender(char)=0 
 If charPopularity(char)<30 Then charPopularity(char)=30
 If charPopularity(char)>99 Then charPopularity(char)=99
 If charStrength(char)<30 Then charStrength(char)=30
 If charStrength(char)>99 Then charStrength(char)=99
 If charSkill(char)<30 Then charSkill(char)=30
 If charSkill(char)>99 Then charSkill(char)=99
 If charAgility(char)<30 Then charAgility(char)=30
 If charAgility(char)>99 Then charAgility(char)=99 
 If charStamina(char)<30 Then charStamina(char)=30
 If charStamina(char)>99 Then charStamina(char)=99 
 If charToughness(char)<30 Then charToughness(char)=30
 If charToughness(char)>99 Then charToughness(char)=99
 If charAttitude(char)<30 Then charAttitude(char)=30
 If charAttitude(char)>99 Then charAttitude(char)=99
 If charHappiness(char)<30 Then charHappiness(char)=30
 If charHappiness(char)>99 Then charHappiness(char)=99
 ;gimmick
 If charRole(char)<1 Then charRole(char)=3
 If charRole(char)>3 Then charRole(char)=1
 If charHeel(char)<0 Then charHeel(char)=1
 If charHeel(char)>1 Then charHeel(char)=0 
 If charEyes(char)<1 Then charEyes(char)=3
 If charEyes(char)>3 Then charEyes(char)=1
 If charEyeballs(char)<1 Then charEyeballs(char)=no_eyes
 If charEyeballs(char)>no_eyes Then charEyeballs(char)=1
 If charEyeShape(char)<0 Then charEyeShape(char)=0
 If charEyeShape(char)>112 Then charEyeShape(char)=112 
 If charHeadSize(char)<80 Then charHeadSize(char)=80
 If charHeadSize(char)>110 Then charHeadSize(char)=110  
 If charTattoos(char)<0 Then charTattoos(char)=no_tattoos
 If charTattoos(char)>no_tattoos Then charTattoos(char)=0
 If charStance(char)<0 Then charStance(char)=no_stances
 If charStance(char)>no_stances Then charStance(char)=0
 For count=1 To 4
  If charTaunt(char,count)<1 Then charTaunt(char,count)=no_taunts
  If charTaunt(char,count)>no_taunts Then charTaunt(char,count)=1
 Next
 If charTheme(char)<0 Then charTheme(char)=no_themes
 If charTheme(char)>no_themes Then charTheme(char)=0
 If charThemePitch(char)<15000 Then charThemePitch(char)=15000
 If charThemePitch(char)>30000 Then charThemePitch(char)=30000
 If charLight(char)<0 Then charLight(char)=no_lightshows
 If charLight(char)>no_lightshows Then charLight(char)=0
 If charWeapon(char)<0 Then charWeapon(char)=weapList
 If charWeapon(char)>weapList Then charWeapon(char)=0
 If charPartner(char)<0 Then charPartner(char)=no_chars
 If charPartner(char)>no_chars Then charPartner(char)=0
 ;If char=fedChampTag(charFed(char),1) Then charPartner(char)=fedChampTag(charFed(char),2)
 ;If char=fedChampTag(charFed(char),2) Then charPartner(char)=fedChampTag(charFed(char),1) 
 If charFed(charPartner(char))<>charFed(char) Or charPartner(char)=char Then charPartner(char)=0
 If charManager(char)<0 Then charManager(char)=no_chars
 If charManager(char)>no_chars Then charManager(char)=0
 If charFed(charManager(char))<>charFed(char) Or charManager(char)=char Then charManager(char)=0
 ;attacks
 For count=1 To 5
  If charAttack(char,count)<1 Then charAttack(char,count)=attackList(count)
  If charAttack(char,count)>attackList(count) Then charAttack(char,count)=1
  If charCrush(char,count)<1 Then charCrush(char,count)=crushList(count)
  If charCrush(char,count)>crushList(count) Then charCrush(char,count)=1
 Next
 ;move set
 For count=1 To 12
  If charMove(char,count)<1 Then charMove(char,count)=moveList(1)
  If charMove(char,count)>moveList(1) Then charMove(char,count)=1
 Next
 If charMove(char,13)<1 Then charMove(char,13)=moveList(1)
 If charMove(char,13)>moveList(1) Then charMove(char,13)=1
 If charMove(char,14)<1 Then charMove(char,14)=moveList(2)
 If charMove(char,14)>moveList(2) Then charMove(char,14)=1
 If charMove(char,15)<1 Then charMove(char,15)=moveList(1)
 If charMove(char,15)>moveList(1) Then charMove(char,15)=1
 For count=1 To 3
  If charGroundMove(char,count)<1 Then charGroundMove(char,count)=moveList(3)
  If charGroundMove(char,count)>moveList(3) Then charGroundMove(char,count)=1
  If charGroundMove(char,count+3)<1 Then charGroundMove(char,count+3)=moveList(4)
  If charGroundMove(char,count+3)>moveList(4) Then charGroundMove(char,count+3)=1
 Next
 ;costumes
 For coz=1 To 3
  If charBaggy(char,coz)<0 Then charBaggy(char,coz)=3
  If charBaggy(char,coz)>3 Then charBaggy(char,coz)=0    	
  If charHatStyle(char,coz)<0 Then charHatStyle(char,coz)=no_hatstyles
  If charHatStyle(char,coz)>no_hatstyles Then charHatStyle(char,coz)=0
  If charHat(char,coz)<1 Then charHat(char,coz)=no_hats
  If charHat(char,coz)>no_hats Then charHat(char,coz)=1 
  If charSpecs(char,coz)<0 Then charSpecs(char,coz)=no_specs
  If charSpecs(char,coz)>no_specs Then charSpecs(char,coz)=0  
  If charHairStyle(char,coz)<0 Then charHairStyle(char,coz)=no_hairstyles
  If charHairStyle(char,coz)>no_hairstyles Then charHairStyle(char,coz)=0 
  If charHair(char,coz)<1 Then charHair(char,coz)=no_hairs
  If charHair(char,coz)>no_hairs Then charHair(char,coz)=1	
  If charFace(char,coz)<1 Then charFace(char,coz)=no_faces
  If charFace(char,coz)>no_faces Then charFace(char,coz)=1
  If charBeard(char,coz)<0 Then charBeard(char,coz)=no_beards
  If charBeard(char,coz)>no_beards Then charBeard(char,coz)=0 
  If charBody(char,coz)<1 Then charBody(char,coz)=no_bodies
  If charBody(char,coz)>no_bodies Then charBody(char,coz)=1
  If charLeftArm(char,coz)<1 Then charLeftArm(char,coz)=no_arms
  If charLeftArm(char,coz)>no_arms Then charLeftArm(char,coz)=1
  If charRightArm(char,coz)<1 Then charRightArm(char,coz)=no_arms
  If charRightArm(char,coz)>no_arms Then charRightArm(char,coz)=1
  If charLeftForearm(char,coz)<1 Then charLeftForearm(char,coz)=no_arms
  If charLeftForearm(char,coz)>no_arms Then charLeftForearm(char,coz)=1
  If charRightForearm(char,coz)<1 Then charRightForearm(char,coz)=no_arms
  If charRightForearm(char,coz)>no_arms Then charRightForearm(char,coz)=1
  If charLeftHand(char,coz)<1 Then charLeftHand(char,coz)=no_hands
  If charLeftHand(char,coz)>no_hands Then charLeftHand(char,coz)=1
  If charRightHand(char,coz)<1 Then charRightHand(char,coz)=no_hands
  If charRightHand(char,coz)>no_hands Then charRightHand(char,coz)=1
  If charShorts(char,coz)<0 Then charShorts(char,coz)=no_legs
  If charShorts(char,coz)>no_legs Then charShorts(char,coz)=0
  If charLegs(char,coz)<1 Then charLegs(char,coz)=no_legs
  If charLegs(char,coz)>no_legs Then charLegs(char,coz)=1
  If charShins(char,coz)<1 Then charShins(char,coz)=no_legs
  If charShins(char,coz)>no_legs Then charShins(char,coz)=1
  If charShoes(char,coz)<1 Then charShoes(char,coz)=no_legs
  If charShoes(char,coz)>no_legs Then charShoes(char,coz)=1 
 Next
End Function

;COPY CHARACTER DATA
Function CopyChar(source,target)
 ;profile
 charName$(target)=charName$(source)
 charTeamName$(target)=charTeamName$(source)
 charAge(target)=charAge(source)
 charHeight(target)=charHeight(source)
 charWeight(target)=charWeight(source)
 charGender(target)=charGender(source)
 charPopularity(target)=charPopularity(source)
 charStrength(target)=charStrength(source)
 charSkill(target)=charSkill(source)
 charAgility(target)=charAgility(source)
 charStamina(target)=charStamina(source) 
 charToughness(target)=charToughness(source)
 charAttitude(target)=charAttitude(source) 
 charHappiness(target)=charHappiness(source)
 ;gimmick
 charRole(target)=charRole(source)
 charHeel(target)=charHeel(source)
 charEyes(target)=charEyes(source)
 charEyeballs(target)=charEyeballs(source)
 charEyeShape(target)=charEyeShape(source)
 charHeadSize(target)=charHeadSize(source)
 charTattoos(target)=charTattoos(source) 
 charStance(target)=charStance(source)
 For count=1 To 4
  charTaunt(target,count)=charTaunt(source,count)
 Next
 charTheme(target)=charTheme(source)
 charThemePitch(target)=charThemePitch(source)
 charLight(target)=charLight(source)
 charWeapon(target)=charWeapon(source)
 charPartner(target)=charPartner(source)
 charManager(target)=charManager(source)
 ;costumes
 For coz=1 To 3
  charBaggy(target,coz)=charBaggy(source,coz)
  charHatStyle(target,coz)=charHatStyle(source,coz)
  charHat(target,coz)=charHat(source,coz)
  charSpecs(target,coz)=charSpecs(source,coz)
  charHairStyle(target,coz)=charHairStyle(source,coz)
  charHair(target,coz)=charHair(source,coz)
  charFace(target,coz)=charFace(source,coz)
  charBeard(target,coz)=charBeard(source,coz) 
  charBody(target,coz)=charBody(source,coz)
  charLeftArm(target,coz)=charLeftArm(source,coz)
  charRightArm(target,coz)=charRightArm(source,coz)
  charLeftForearm(target,coz)=charLeftForearm(source,coz)
  charRightForearm(target,coz)=charRightForearm(source,coz)
  charLeftHand(target,coz)=charLeftHand(source,coz)
  charRightHand(target,coz)=charRightHand(source,coz)
  charShorts(target,coz)=charShorts(source,coz)
  charLegs(target,coz)=charLegs(source,coz)
  charShins(target,coz)=charShins(source,coz)
  charShoes(target,coz)=charShoes(source,coz)
 Next
 ;attacks
 For count=1 To 5
  charAttack(target,count)=charAttack(source,count)
  charCrush(target,count)=charCrush(source,count)
 Next
 ;moves
 For count=1 To 15
  charMove(target,count)=charMove(source,count)
 Next
 For count=1 To 6
  charGroundMove(target,count)=charGroundMove(source,count)
 Next
 ;health status
 charHealth(target)=charHealth(source)
 For limb=0 To 5
  charInjured(target,limb)=charInjured(source,limb)
 Next
 For limb=1 To 50
  charLimb(target,limb)=charLimb(source,limb)
 Next
End Function

;MOVE CHARACTER
Function MoveChar(char,target)
 If charFed(char)<>target And fedSize(target)<optRosterLim
  ;queue reactions
  If game=1 Then FindTradeReactions(char,target) 
  ;find spot in current roster
  source=charFed(char)
  spot=0
  For count=1 To fedSize(source)
   If spot=0 And fedRoster(source,count)=char Then spot=count
  Next
  ;delete from source roster
  For count=spot To fedSize(source)-1
   fedRoster(source,count)=fedRoster(source,count+1)
  Next
  fedSize(source)=fedSize(source)-1
  ;add to target roster
  fedSize(target)=fedSize(target)+1
  fedRoster(target,fedSize(target))=char
  charFed(char)=target
  ;reset ranking criteria
  editFilter=0
  ;affect relationships
  charPartner(char)=0 : charManager(char)=0
  For v=1 To no_chars
   If charPartner(v)=char Then charPartner(v)=0
   If charManager(v)=char Then charManager(v)=0
  Next
  ;affect gimmicks
  If target=<6
   If charTheme(char)=0 Then charTheme(char)=Rnd(1,no_themes)
   If charLight(char)=0 Then charLight(char)=5
   For coz=1 To 3
    If charBody(char,coz)=>55 And charBody(char,coz)=<60
     charBody(char,coz)=54+target 
     If charBody(char,coz)=59
      If charLeftArm(char,coz)=21 Then charLeftArm(char,coz)=16 : charRightArm(char,coz)=charLeftArm(char,coz)
      If charLeftArm(char,coz)=22 Then charLeftArm(char,coz)=18 : charRightArm(char,coz)=charLeftArm(char,coz)
      If charLeftArm(char,coz)=23 Then charLeftArm(char,coz)=19 : charRightArm(char,coz)=charLeftArm(char,coz)
      If charLeftArm(char,coz)=24 Then charLeftArm(char,coz)=20 : charRightArm(char,coz)=charLeftArm(char,coz)
     Else
      If charLeftArm(char,coz)=16 Or charLeftArm(char,coz)=17 Then charLeftArm(char,coz)=21 : charRightArm(char,coz)=charLeftArm(char,coz)
      If charLeftArm(char,coz)=18 Then charLeftArm(char,coz)=22 : charRightArm(char,coz)=charLeftArm(char,coz)
      If charLeftArm(char,coz)=19 Then charLeftArm(char,coz)=23 : charRightArm(char,coz)=charLeftArm(char,coz)
      If charLeftArm(char,coz)=20 Then charLeftArm(char,coz)=24 : charRightArm(char,coz)=charLeftArm(char,coz)
     EndIf
    EndIf
   Next
  EndIf
  ;reset career status
  GenerateContract(char)
  If game=0 Or target<>charFed(gamChar) Then charArrived(char)=1
 EndIf
End Function

;FIND ON ROSTER
Function FindOnRoster(char,f)
 value=0
 For count=1 To fedSize(f)
  If fedRoster(f,count)=char Then value=count
 Next
 Return value
End Function

;CALCULATE AVERAGE STATS
Function AverageStats(char)
 value=charPopularity(char)
 value=value+charStrength(char)+charSkill(char)+charAgility(char)+charStamina(char)+charToughness(char)
 value=value/6
 If value>99 Then value=99 
 Return value
End Function

;CHECK WHETHER SLOT IS RE-USABLE
Function SlotUsable(checkSlot)
 value=1
 ;negate if files are missing
 If FileType(CurrentDir$()+"Data/Slot0"+checkSlot+"/Progress.dat")=0 Then value=0
 If FileType(CurrentDir$()+"Data/Slot0"+checkSlot+"/World.dat")=0 Then value=0
 If value=1
  For char=1 To no_chars
   If FileType(CurrentDir$()+"Data/Slot0"+checkSlot+"/Character"+Dig$(char,100)+".dat")=0 Then value=0
   If FileType(CurrentDir$()+"Data/Slot0"+checkSlot+"/Portraits/Photo"+Dig$(char,100)+".bmp")=0 Then value=0 
  Next
 EndIf
 Return value
End Function

;SWITCH SLOT
Function SwitchSlot(newSlot)
 If slot<>newSlot 
  ;save current
  SaveProgress(slot)
  SaveWorld(slot)
  SaveChars(slot)
  ;load new
  slot=newSlot
  LoadProgress(slot)
  LoadWorld(slot)
  LoadChars(slot)
  LoadPhotos(slot)
 EndIf
End Function

;GENERATE CHARACTER
Function GenerateCharacter(char)
   ;profile
   randy=Rnd(0,6)
   If randy=0 Then charGender(char)=1 Else charGender(char)=0 
   charFace(char,1)=Rnd(1,no_faces)
   randy=Rnd(0,2)
   If randy=<1 And charGender(char)=1 And GetRace(char)=<1 Then charFace(char,1)=Rnd(97,103)
   If randy=<1 And charGender(char)=1 And GetRace(char)=2 Then charFace(char,1)=Rnd(32,34)
   If randy=<1 And charGender(char)=1 And GetRace(char)=3 Then charFace(char,1)=Rnd(55,58)
   charName$(char)=GenerateName$(char)
   charTeamName$(char)=textTeamName$(Rnd(1,50))
   randy=Rnd(0,2)
   If randy=<1 Then charAge(char)=Rnd(20,40) Else charAge(char)=Rnd(16,60)
   If fed=7
    charPopularity(char)=Rnd(40,70)
    charStrength(char)=Rnd(50,80)
    charSkill(char)=Rnd(50,80)
    charAgility(char)=Rnd(50,90)
    charStamina(char)=Rnd(50,80)
    charToughness(char)=Rnd(50,80)
   Else
    charPopularity(char)=Rnd(50,80)
    charStrength(char)=Rnd(50,90)
    charSkill(char)=Rnd(50,90)
    charAgility(char)=Rnd(50,90)
    charStamina(char)=Rnd(50,90)
    charToughness(char)=Rnd(50,90)
   EndIf
   charAttitude(char)=Rnd(50,90)
   charHappiness(char)=Rnd(50,90)
   ResetOldValues(char)
   ResetNewValues(char)
   ;gimmick
   randy=Rnd(1,10)
   If randy=<7 Then charRole(char)=1
   If randy=>8 And randy=<9 Then charRole(char)=2
   If randy=10 Then charRole(char)=3
   charHeel(char)=Rnd(0,1)
   charEyes(char)=Rnd(2,3)
   charEyes(char)=charEyes(char)-charHeel(char)
   charStance(char)=Rnd(0,no_stances)
   For count=1 To 4
    charTaunt(char,count)=Rnd(1,no_taunts)
   Next
   charTheme(char)=Rnd(0,no_themes)
   charThemePitch(char)=22050
   charLight(char)=Rnd(0,no_lightshows)
   charWeapon(char)=0
   charPartner(char)=0
   charManager(char)=0
   ;attacks  
   For count=1 To 5
    charAttack(char,count)=Rnd(1,attackList(count))
    charCrush(char,count)=Rnd(1,crushList(count))
   Next
   ;moves
   For count=1 To 15
    charMove(char,count)=charMove(Rnd(1,no_chars),count) ;Rnd(1,moveList(1))
   Next
   charMove(char,14)=Rnd(1,moveList(2))
   For count=1 To 3
    charGroundMove(char,count)=Rnd(1,moveList(3))
	charGroundMove(char,count+3)=Rnd(1,moveList(4))
   Next
   ;appearance
   charHeight(char)=Rnd(0,35)
   randy=Rnd(0,2)
   If randy=<1 And charGender(char)=0 Then charHeight(char)=Rnd(10,16)
   If randy=<1 And charGender(char)=1 Then charHeight(char)=Rnd(4,10)
   charWeight(char)=Rnd(75,300)
   randy=Rnd(0,2)
   If randy=<1 And charGender(char)=0 Then charWeight(char)=Rnd(100,250)
   If randy=<1 And charGender(char)=1 Then charWeight(char)=Rnd(75,200)
   charHairstyle(char,1)=Rnd(0,no_hairstyles)
   randy=Rnd(0,2)
   If randy=<1 And charGender(char)=0
    Repeat
     charHairstyle(char,1)=Rnd(0,no_hairstyles)
    Until charHairstyle(char,1)=<21 Or charHairstyle(char,1)=>46
   EndIf
   randy=Rnd(0,5)
   If randy=<4 And charGender(char)=1 Then charHairstyle(char,1)=Rnd(22,45)
   randy=Rnd(0,2)
   If randy=<1 Then charHair(char,1)=Rnd(1,11) Else charHair(char,1)=Rnd(1,no_hairs)
   If GetRace(char)=3 Then charHair(char,1)=Rnd(1,2)
   charBeard(char,1)=0
   randy=Rnd(0,8)
   If randy=<2 And charGender(char)=0 Then charBeard(char,1)=Rnd(1,25) 
   If randy=8 Then charBeard(char,1)=Rnd(26,37) 
   charEyeballs(char)=Rnd(1,no_eyes)
   randy=Rnd(0,5)
   If randy=<4 Then charEyeballs(char)=Rnd(1,4)
   If GetRace(char)=3 Then charEyeballs(char)=Rnd(1,2)
   rando=Rnd(3,10) : charEyeShape(char)=rando*10
   randy=Rnd(0,10)
   If randy=<4 Then rando=Rnd(7,9) : charEyeShape(char)=rando*10
   If randy=9 Then charEyeShape(char)=111
   If randy=10 Then charEyeShape(char)=112 
   charHeadSize(char)=Rnd(90,100)
   randy=Rnd(0,4)
   If randy=0 Then charTattoos(char)=Rnd(0,no_tattoos) Else charTattoos(char)=0
   For coz=2 To 3
    charHairStyle(char,coz)=charHairStyle(char,1)
    charHair(char,coz)=charHair(char,1)
    charFace(char,coz)=charFace(char,1)
    charBeard(char,coz)=charBeard(char,1)
   Next
   ;costumes
   For coz=1 To 3
    Repeat 
     idol=Rnd(1,no_chars)
    Until idol<>char
    charBaggy(char,coz)=charBaggy(idol,coz)
    charHatStyle(char,coz)=charHatStyle(idol,coz)
    charHat(char,coz)=charHat(idol,coz)
    charSpecs(char,coz)=charSpecs(idol,coz)
    charBody(char,coz)=charBody(idol,coz)
    If charGender(char)=1 And charBody(char,coz)=<7 Then charBody(char,coz)=Rnd(10,13)
    charLeftArm(char,coz)=charLeftArm(idol,coz)
    charRightArm(char,coz)=charRightArm(idol,coz) 
    charLeftForearm(char,coz)=charLeftForearm(idol,coz)
    charRightForearm(char,coz)=charRightForearm(idol,coz) 
    charLeftHand(char,coz)=charLeftHand(idol,coz)
    charRightHand(char,coz)=charRightHand(idol,coz)  
    charShorts(char,coz)=charShorts(idol,coz)
    charLegs(char,coz)=charLegs(idol,coz)
    charShins(char,coz)=charShins(idol,coz)
    charShoes(char,coz)=charShoes(idol,coz)
   Next
   ;career status
   charWorth(char)=CalculateWorth(char,7)
   charVacant(char)=0
   charArrived(char)=1
   charWorked(char)=0
   For count=1 To 9
    charExperience(char,count)=0
    charMatches(char,count)=0
    charWins(char,count)=0
    For title=1 To 4
     charTitles(char,count,title)=0
    Next
   Next
   charTrainCourse(char)=0
   charTrainLevel(char)=0
   charWeightChange(char)=0
   charPeaked(char)=0
   charVariable(char)=0
   ;health status
   ResetHealthStatus(char) 
   ResetCareerStatus(char) 
   ;agreements
   For count=1 To 50
    charAgreement(char,count)=0
   Next
   ;relationships
   GenerateStoryRelationships(char)
   GenerateRealRelationships(char)
   charTradeReaction(char)=0
   For count=1 To optCharLim
    charTeamHistory(char,count)=0
    charHistory(char,count)=0
   Next
   ;check changes
   CheckLimits(char)
End Function

;GENERATE NAME
Function GenerateName$(char)
 Repeat
  ;assign new name
  randy=Rnd(0,1)
  If randy=0 Or charGender(char)=1
   name$=textNickname$(Rnd(0,no_nicknames))
  Else
   name$=textFirstName$(Rnd(0,no_firstnames))+" "+textSurName$(Rnd(0,no_surnames))
   randy=Rnd(0,10)
   If randy=1 Then name$=textSurName$(Rnd(0,no_surnames))+" "+textFirstName$(Rnd(0,no_firstnames))
   If randy=2 Then name$=textFirstName$(Rnd(0,no_firstnames))+" "+textFirstName$(Rnd(0,no_firstnames))
   If randy=3 Then name$=textSurName$(Rnd(0,no_surnames))+" "+textSurName$(Rnd(0,no_surnames))
  EndIf
  ;find conflicts
  conflict=0
  For v=1 To no_chars
   If charName$(v)=name$ Then conflict=1
  Next
 Until conflict=0
 Return name$
End Function

;GENERATE STORY RELATIONSHIPS
Function GenerateStoryRelationships(char)
 	   For v=1 To no_chars
	    charRelationship(char,v)=0 : charRelationship(v,char)=0
	   Next
	   For v=1 To no_chars
	    If v<>char
	     If charFed(v)=charFed(char) Then randy=Rnd(0,5) Else randy=Rnd(0,10)
	     If randy=<1 Then charRelationship(char,v)=Rnd(-1,1) Else charRelationship(char,v)=0
	     randy=Rnd(0,1)
	     If randy=0 And charRelationship(char,v)>0 And charHeel(v)<>charHeel(char) Then charRelationship(char,v)=0
	     If randy=0 And charRelationship(char,v)<0 And charHeel(v)=charHeel(char) Then charRelationship(char,v)=0
	     If v=charPartner(0) Or v=charManager(0) Then charRelationship(char,v)=1
	     charRelationship(v,char)=charRelationship(char,v)
	    EndIf
	   Next
End Function

;GENERATE REAL RELATIONSHIPS
Function GenerateRealRelationships(char)
 	   For v=1 To no_chars
	    charRealRelationship(char,v)=0 : charRealRelationship(v,char)=0
	   Next
	   For v=1 To no_chars
	    If v<>char
	     If charFed(v)=charFed(char) Then randy=Rnd(0,5) Else randy=Rnd(0,10)
	     If randy=0 Then charRealRelationship(char,v)=Rnd(-1,1) Else charRealRelationship(char,v)=0
	     ;If v=charPartner(0) Or v=charManager(0) Then charRealRelationship(char,v)=1
	     charRealRelationship(v,char)=charRealRelationship(char,v)
	    EndIf
	   Next
End Function