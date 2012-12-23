;//////////////////////////////////////////////////////////////////////////////
;------------------------- WRESTLING MPIRE 2008: VARIABLES --------------------
;//////////////////////////////////////////////////////////////////////////////

;//////////////////////////////////////////////////////
;------------------ STRUCTURE -------------------------
;//////////////////////////////////////////////////////
Global version=1
Global screen,oldScreen
;0=character selection, 1=editing, 2=studying rosters, 3=buying, 4=selling, 5=starting career
;6=training, 7=setting relationships, 8=setting real relationships, 9=introduce newcomer
;10=sparring, 11=tournament, 12=brawl
Global screenSource,screenAgenda 
Global screenCall,callX,callY
Global go,gotim
Global foc,subFoc
Global timer,keytim
Global file
;diagnostics
Global tester

;//////////////////////////////////////////////////////
;------------------- OPTIONS --------------------------
;//////////////////////////////////////////////////////
.Options
;constants
Global optPlayLim=50,optCharLim=235,optRosterLim=44
Global optLieHP=150,optCrawlHP=75,optSegmentLim=10
;frame rates
Global speedTim,optSpeed=1 ;1-4=normal to very fast
Dim frameRate(4)
frameRate(1)=30
frameRate(2)=35
frameRate(3)=50
frameRate(4)=70
;game preferences
Global optLevel=3 ;0=extremely easy - 6=extremely hard
Global optRatingsLevel=2 ;1=easy, 2=normal, 3=difficult
Global optLength=2,optOldLength
Global optEntrances=1 ;0=never, 1=only in small matches, 2=always
Global optReferees=2,optManagers=2,optIntruders=2 ;0=never, 1=only small, 2=only standard, 3=always
Global optHideElim=1 ;0=never, 1=only in large matches, 2=always
Global optTagControl=2 ;0=specific, 1=specific in career, 2=whole team
;display preferences
Global optGameRes=2,optMenuRes=2,optWindow=0
Global optDefaultCam=5,optEntranceCam=1 ;1=in front, 2=behind, 3=spontaneous, 4=first person
Global optFX=1,optShadows=1,optGore=3 ;0=none, 1=scars, 2=pools, 3=limbs, 4=bloodbath
Global optFog=1,optDetail=1 ;0=bare backstage areas, 1=banners removed, 2=maximum
Global optLabels=1 ;0=off, 1=transparent, 2=solid
Global optGrid=1 ;0=list boxes, 1=grid of boxes
Global optMeters=2 ;0=none, 1=minimal, 2=show all
Global optCrowdAnim=2 ;0=static, 1=animated, 2=+signs
;keys & buttons
Global keyAttack=30,keyGrapple=31,keyRun=44,keyPickUp=45,keySwitch=29,keyTaunt=57
Global buttAttack=1,buttGrapple=2,buttRun=3,buttPickUp=4,buttSwitch=5,buttTaunt=7
Global buttSelect=6 ;Joystick button to select another wrestler (for multiple joysticks!)
Global controlTim
;online
Global optOnline=0 ;0=none, 1=join, 2=host
Global netName$,netID
Global netControl
Global netChat$

;//////////////////////////////////////////////////////
;------------------- PROGRESS -------------------------
;//////////////////////////////////////////////////////
.Progress
;slot status
Global slot
Dim slotActive(3)
Dim slotPreview(3)
slotActive(0)=1
;career status
Global targetSlot,gamInherit,gamComplete ;-1=failed, 1=success bridge, 2=success
Global game,gamChar
Global gamFatality,gamNewcomer
Global gamBuild
Dim gamNegotiated(optCharLim,3) ;1=buy, 2=sell, 3=meeting
Dim gamFinances(10,2)
Global gamTicket,gamBonus
;1-12=production block, 13=production commitment, 14=drug consent, 15=terrorism, 18=power failure, 20=no attendance
Dim gamAgreement(50)
;schedule
Global gamDate,gamYear,gamScroll
Global gamMission,gamTarget,gamDeadline
Dim gamSchedule(100) ;-2=court, -1=injured, 0=nothing, 1=TV, 2=PPV, 3=tournament, 4=interpromotional, 5=charity, 6=tribute
Dim gamRivalFed(100)
Dim gamCourtCase(100)
Dim gamCourtChar(100)
Dim gamOpponent(100) ;REMOVE!!!
Dim gamMatch(100) ;REMOVE!!!
Dim gamGimmick(100) ;REMOVE!!!
Dim gamPromo(100) ;REMOVE!!!
Dim gamPromoVariable(100) ;REMOVE!!!
;cards
Dim gamSegments(100)
Dim gamMatchScore(100,10)
Dim gamMatchHardcore(100,10)
Dim gamMatchWinner(100,10)
Dim gamMatchLoser(100,10)
Dim gamMatchFormat(100,10) ;1=one-on-one, 2=team, 3=multiple
Dim gamNightScore(100)
Dim gamNightHardcore(100)
Dim gamResult(100) ;REMOVE!!!
;arena status
Dim gamVenue(100)
Dim gamAtmos(100)
Dim gamAttendance(100)
Dim gamRopes(100)
Dim gamPads(100)
Dim gamCanvas(100)
Dim gamApron(100)
Dim gamMatting(100)
;training
Global trainCourse,trainStage ;0=intro, 1=menu, 2=test, 3=stat changes, 4=explanation
Global trainTim,trainInput,trainAnim#
Global trainCPU,trainChanger,trainDrag
Global trainScore,trainResult,trainProgress
Global trainSoundTim,trainGruntTim#
;negotiations
Global negChar,negStar,negVisitor
Global negSetting,negVariable
Global negTopic,negSubTopic,negDate
Global negTim,negStage,negSubStage
Global negWorth,negContractWorth,negClauseWorth
Global negPaymentTotal,negInitialPaymentTotal,negPaymentRequired
Global negChances,negVerdict,negInterest,negOffer
Global negPayOff,negPayOffPrefer,negBuyOut
Global negSalary,negContract
Global negFriendsHere,negFriendsThere
Global negEnemiesHere,negEnemiesThere
Dim negClause(3),negClausePrefer(3)
;news events
Global no_events
Dim eEvent(200)
Dim eChar(200)
Dim eFed(200)
Dim eFoc(200)
Dim eVariable(200)
Dim eCharged(200)
Dim eNewspaper(200)
Dim eBackground(200)
Dim eAdvert(200,2)
;hall of fame
Global hiInduct
Dim hiRank(10)
Dim hiChar(10),hiName$(10)
Dim hiPhoto(10),hiPhotoHeight#(10)
Dim hiPhotoR(10),hiPhotoG(10),hiPhotoB(10)
Dim hiFed(10),hiWealth(10)
Dim hiPopularity(10),hiReputation(10)
Dim hiExperience(10)

;//////////////////////////////////////////////////////
;------------------- MATCHES --------------------------
;//////////////////////////////////////////////////////
.Matches
;rules
Global no_wrestlers=2,no_refs=1,no_plays
Global matchPreset,no_matches=21,no_gimmicks=13
Global matchOldState,matchState=2 ;0=intro, 1=entrances, 2=pre-match promos, 3=active, 4=over
Global matchEntrances,matchEnter
Global matchMins,matchLastMin,matchSecs=0,matchClock
Global matchPause,matchTim,matchCountTim
Global matchTimeLim=10
Global matchRules=1 ;0=hardcore, 1=in ring, 2=in ring strict
Global matchShoot,matchType=1 ;0=none, 1=first fall wins, 2=best of 3, 3=ironman, 4=last fall, 5=elimination
Global matchPins=1,matchSubs=1,matchKOs=0,matchBlood=0,matchTables=0
Global matchCounter,matchCountOuts=1 ;0=none, 1=fast count, 2=slow count, 3=elimination/victory
Global matchTeams=0 ;-1=sequential, 0=individuals, 1=teams, 2=tag teams
Global matchBellTim,matchWinStyle ;0=forfeit, 1=pin, 2=submit, 3=KO, 4=blood, 5=OTT, 6=DQ, 7=awarded decision
Global matchRemaining,matchSurvivor
Global matchWinner,matchLoser,matchLeader
Global matchPlayer,matchMulti,matchReferee

;Joystick player map
Dim joyPlayer(4)
Dim joyControlTim(4)

Global matchIntruder,matchBetrayal
Global matchDamage,matchWeapon
;gimmicks
Global matchLocation=0 ;0=default, 1=locker room, 2=backstage lounge
Global matchRopes=0 ;0=normal, 1=barbed wire, 2=electrified, 3=hotwire
Global matchCage=0 ;1=wire mesh, 2=steel bars, 3=blue bars, 4=black bars
Global matchBlastTim,matchBlasted
Global matchChamps=1,matchReward ;0=ignore, 1=acknowledge, 2-4=crown new, 5=trophy, 6=hair, 7=leave town
;promos
Global no_promos=100,matchPromo=Rnd(1,no_promos)
Global promoFoc,promoTim,promoLogic
Global speaker,promoMesser,promoVariable
Global promoSubTopicA,promoSubTopicB
Dim promoActor(3)
Dim promoReact(20)
Dim promoTitle$(200)
Dim promoExplain$(200)
Dim promoLength(200)
Dim promoRole(200,3) ;-1=ref,0=none,1=next from team one,2=next from team two
Dim promoLocked(200)
Dim promoLib(200)
Global lineA$,lineB$
Global greenLineA$,greenLineB$
Global redLineA$,redLineB$
;entertainment
Global entDisplay=3
Global entShortOffset=5,entLongOffset=5
Global entX,entY,entTX,entTY
Global entHype,entPotential
Global entExpect,entSatisfy#,entBored
Global entClose,entChemistry,entFeud
Global entWorkers,entScore,entTotal,entEndScore
Global entHardcore,entHardTotal,entEndHardcore
Global entReview
;commentary
Global comTim,comSpeed ;+=reveal, -=recoil
Global comScript$,comAffix$,comSuffix$
;meter layout (0=individials, 1-2=teams
Dim meterSlot(3)
Dim meterList(3,optPlayLim)
;tournaments
Global optCupFed,optCupTeams,optCupReward
Global optCupSize=4,optCupSelect=1 ;0=hand-picked, 1=random
Global optCupControl=1 ;0=100% CPU, 1=one human, 2=2 scattered humans, 3=100% 1P, 4=100% 2P, 5=2 on same team 
Global cupSlot ;0-3=exhibitions, 4-6=careers
Dim cupSpan(10) ;0=several days, 1=one night
Dim cupFed(10)
Dim cupTeams(10)
Dim cupReward(10) ;0=trophy, 1-3=crown new
Dim cupSize(10) ;4,8,16,32 chars, 0=inactive
Dim cupControl(10) ;record of option ^^^
Dim cupChar(10,32) ;characters on list
Dim cupCharHealth(10,optCharLim) ;simulated health status
Dim cupCharInjured(10,optCharLim,5) ;simulated injury status
Dim cupCharControl(10,optCharLim) ;control assigned to character
Dim cupCharPartner(10,optCharLim) ;team-mate assigned to character
Dim cupBracket(10,32,2) ;1st and 2nd boxers in each bracket
Dim cupFoc(10) ;bracket selected to play
Dim cupResult(10,32) ;0=not played, 1-2=player advanced
;bracket co-ordinates (offset)
Dim cupX(32),cupY(32) 
cupX(1)=0 : cupY(1)=0
cupX(2)=-400 : cupY(2)=0
cupX(3)=400 : cupY(3)=0
cupX(4)=-625 : cupY(4)=-140
cupX(5)=625 : cupY(5)=-140
cupX(6)=-625 : cupY(6)=140
cupX(7)=625 : cupY(7)=140
cupX(8)=-850 : cupY(8)=-210
cupX(9)=850 : cupY(9)=-210
cupX(10)=-850 : cupY(10)=-70
cupX(11)=850 : cupY(11)=-70
cupX(12)=-850 : cupY(12)=70
cupX(13)=850 : cupY(13)=70
cupX(14)=-850 : cupY(14)=210
cupX(15)=850 : cupY(15)=210
cupX(16)=-1075 : cupY(16)=-245
cupX(17)=1075 : cupY(17)=-245
cupX(18)=-1075 : cupY(18)=-175
cupX(19)=1075 : cupY(19)=-175
cupX(20)=-1075 : cupY(20)=-105
cupX(21)=1075 : cupY(21)=-105
cupX(22)=-1075 : cupY(22)=-35
cupX(23)=1075 : cupY(23)=-35
cupX(24)=-1075 : cupY(24)=35
cupX(25)=1075 : cupY(25)=35
cupX(26)=-1075 : cupY(26)=105
cupX(27)=1075 : cupY(27)=105
cupX(28)=-1075 : cupY(28)=175
cupX(29)=1075 : cupY(29)=175
cupX(30)=-1075 : cupY(30)=245
cupX(31)=1075 : cupY(31)=245
;bracket routes
Dim cupTargetBracket(32),cupTargetSlot(32) 
cupTargetBracket(1)=0 : cupTargetSlot(1)=0
cupTargetBracket(2)=1 : cupTargetSlot(2)=1
cupTargetBracket(3)=1 : cupTargetSlot(3)=2
cupTargetBracket(4)=2 : cupTargetSlot(4)=1
cupTargetBracket(5)=3 : cupTargetSlot(5)=1
cupTargetBracket(6)=2 : cupTargetSlot(6)=2
cupTargetBracket(7)=3 : cupTargetSlot(7)=2
cupTargetBracket(8)=4 : cupTargetSlot(8)=1
cupTargetBracket(9)=5 : cupTargetSlot(9)=1
cupTargetBracket(10)=4 : cupTargetSlot(10)=2
cupTargetBracket(11)=5 : cupTargetSlot(11)=2
cupTargetBracket(12)=6 : cupTargetSlot(12)=1
cupTargetBracket(13)=7 : cupTargetSlot(13)=1
cupTargetBracket(14)=6 : cupTargetSlot(14)=2
cupTargetBracket(15)=7 : cupTargetSlot(15)=2
cupTargetBracket(16)=8 : cupTargetSlot(16)=1
cupTargetBracket(17)=9 : cupTargetSlot(17)=1
cupTargetBracket(18)=8 : cupTargetSlot(18)=2
cupTargetBracket(19)=9 : cupTargetSlot(19)=2
cupTargetBracket(20)=10 : cupTargetSlot(20)=1
cupTargetBracket(21)=11 : cupTargetSlot(21)=1
cupTargetBracket(22)=10 : cupTargetSlot(22)=2
cupTargetBracket(23)=11 : cupTargetSlot(23)=2
cupTargetBracket(24)=12 : cupTargetSlot(24)=1
cupTargetBracket(25)=13 : cupTargetSlot(25)=1
cupTargetBracket(26)=12 : cupTargetSlot(26)=2
cupTargetBracket(27)=13 : cupTargetSlot(27)=2
cupTargetBracket(28)=14 : cupTargetSlot(28)=1
cupTargetBracket(29)=15 : cupTargetSlot(29)=1
cupTargetBracket(30)=14 : cupTargetSlot(30)=2
cupTargetBracket(31)=15 : cupTargetSlot(31)=2
;scroll limits
Dim cupScrollLimit(6)
cupScrollLimit(1)=0
cupScrollLimit(2)=110;100
cupScrollLimit(3)=335;325
cupScrollLimit(4)=570;560
cupScrollLimit(5)=810;800
cupScrollLimit(6)=1010;1000

;///////////////////////////////////////////////////////
;----------------------- SOUND -------------------------
;///////////////////////////////////////////////////////
.Sound
;effects
Global sMenuBrowse=LoadSound("Sound/Browse.wav")
Global sMenuSelect=LoadSound("Sound/Select.wav")
Global sMenuGo=LoadSound("Sound/Confirm.wav")
Global sMenuBack=LoadSound("Sound/Cancel.wav")
Global sVoid=LoadSound("Sound/Void.wav")
Global sCamera=LoadSound("Sound/Camera.wav")
Global sPaper=LoadSound("Sound/Paper.wav")
Global sCash=LoadSound("Sound/Cash.wav")
Global sTrash=LoadSound("Sound/Trash.wav")
Global sWhistle=LoadSound("Sound/Whistle.wav")
Global sProduce=LoadSound("Sound/Produce.wav")
Global sTyping=LoadSound("Sound/Typing.wav")
Global chTyping
;world
Global sBell=Load3DSound("Sound/Bell.wav")
Global sBuzzer=Load3DSound("Sound/Buzzer.wav")
;movements
Dim sShuffle(3)
For count=1 To 3
 sShuffle(count)=Load3DSound("Sound/Movement/Shuffle0"+count+".wav")
Next
Dim sStep(6)
For count=1 To 6
 sStep(count)=Load3DSound("Sound/Movement/Step0"+count+".wav")
Next
Global sFall=Load3DSound("Sound/Movement/Fall.wav")
Global sThud=Load3DSound("Sound/Movement/Thud.wav")
Global sRope=Load3DSound("Sound/Movement/Rope.wav")
Global sSwing=Load3DSound("Sound/Movement/Swing.wav")
;impacts
Global sBleed=Load3DSound("Sound/Impacts/Bleed.wav")
Dim sImpact(7) ;1-4=weak, 5-6=strong, 7=major
Dim sBlock(7)
For count=1 To 7
 sImpact(count)=Load3DSound("Sound/Impacts/Impact"+Dig$(count,10)+".wav")
 sBlock(count)=Load3DSound("Sound/Impacts/Block"+Dig$(count,10)+".wav")
Next
;weapon impacts
Global sStab=Load3DSound("Sound/Props/Stab.wav")
Global sImpactBell=Load3DSound("Sound/Props/Impact_Bell.wav")
Global sImpactBlade=Load3DSound("Sound/Props/Impact_Blade.wav")
Global sImpactBottle=Load3DSound("Sound/Props/Impact_Bottle.wav")
Global sImpactCane=Load3DSound("Sound/Props/Impact_Cane.wav")
Global sImpactGun=Load3DSound("Sound/Props/Impact_Gun.wav")
Global sImpactHammer=Load3DSound("Sound/Props/Impact_Hammer.wav")
Global sImpactMetal=Load3DSound("Sound/Props/Impact_Metal.wav")
Global sImpactMic=Load3DSound("Sound/Props/Impact_Mic.wav")
Global sImpactWood=Load3DSound("Sound/Props/Impact_Wood.wav")
Global sImpactTacks=Load3DSound("Sound/Props/Impact_Tacks.wav")
;item smashes
Global sItem=Load3DSound("Sound/Props/Item.wav")
Global sSmashCard=Load3DSound("Sound/Props/Smash_Card.wav")
Global sSmashElectric=Load3DSound("Sound/Props/Smash_Electric.wav")
Global sSmashGlass=Load3DSound("Sound/Props/Smash_Glass.wav")
Global sSmashMetal=Load3DSound("Sound/Props/Smash_Metal.wav")
Global sSmashPlastic=Load3DSound("Sound/Props/Smash_Plastic.wav")
Global sSmashWire=Load3DSound("Sound/Props/Smash_Wire.wav")
Global sSmashWood=Load3DSound("Sound/Props/Smash_Wood.wav")
;elements
Global sFire=Load3DSound("Sound/Props/Fire.wav")
Global sIgnite=Load3DSound("Sound/Props/Ignite.wav")
Global sExpire=Load3DSound("Sound/Props/Expire.wav") 
Global sExplosion=Load3DSound("Sound/Props/Explosion.wav")
Global sElectric=Load3DSound("Sound/Props/Electricity.wav")
Global sPower=Load3DSound("Sound/Props/Power.wav")
Global sSplash=Load3DSound("Sound/Props/Splash.wav")
;pain
Dim sPain(10)
For count=1 To 8
 sPain(count)=Load3DSound("Sound/Pain/Pain0"+count+".wav")
Next
Dim sAgony(5)
For count=1 To 3
 sAgony(count)=Load3DSound("Sound/Pain/Agony0"+count+".wav")
Next
Global sChoke=Load3DSound("Sound/Pain/Choke.wav")
;referee
Global sDQ=Load3DSound("Sound/Speech/DQ.wav")
Global sRopeBreak=Load3DSound("Sound/Speech/Break.wav")
Dim sCount(10)
For count=1 To 10
 sCount(count)=Load3DSound("Sound/Speech/Count"+Dig$(count,10)+".wav")
Next
;crowd
Global chCrowd,chAtmos,chRopes
Global crowdVol#,crowdVolTarget#
Global crowdPitch#,crowdPitchTarget#
Dim sCrowd(12)
sCrowd(0)=LoadSound("Sound/Crowd/Empty.wav")
sCrowd(1)=LoadSound("Sound/Crowd/Loop.wav")
sCrowd(2)=LoadSound("Sound/Crowd/Cheer.wav")
sCrowd(3)=LoadSound("Sound/Crowd/Boo.wav")
sCrowd(4)=LoadSound("Sound/Crowd/Yay.wav")
sCrowd(5)=LoadSound("Sound/Crowd/Groan.wav")
sCrowd(6)=LoadSound("Sound/Crowd/Excitement.wav")
sCrowd(7)=LoadSound("Sound/Crowd/Murmur.wav")
sCrowd(8)=LoadSound("Sound/Crowd/Laughter.wav")
sCrowd(9)=LoadSound("Sound/Crowd/Applause.wav")
sCrowd(10)=LoadSound("Sound/Crowd/Chant.wav")
sCrowd(11)=LoadSound("Sound/Crowd/Bored.wav")
;music
Global optMusicVolume=80,optJukeBox=1
Global chTheme,chCurrentTheme,chThemeChange
Global chThemeVol#,chThemeTarget#,chThemePitch
Global sMainTheme=LoadSound("Sound/Theme.wav")
Global no_themes
Dim sTheme(100)
Dim musicLearned(100)
;loading function
Function LoadMusic()
 Loader("Please Wait","Loading Music")
 For count=0 To 99
  sTheme(count)=LoadSound("Music/Theme"+Dig$(count,10)+".wav")
  If sTheme(count)>0 Then no_themes=count
 Next
End Function

;///////////////////////////////////////////////////////
;--------------------- GRAPHICS ------------------------
;///////////////////////////////////////////////////////
.Images
;variables
Global fontNumber
Dim font(20)
Dim fontNews(20)
Dim fontStat(10)
Dim gIntro(2)
Dim gLogo(3)
Dim gMenu(20)
Dim gBlackout(2)
Global gBackground,gMDickie,gOutro
Global gDateHighlight,gPortrait
Dim gStat(20)
Dim gScroll(4)
Dim gLoser(3)
Dim gControl(3)
Dim gMonth(2)
Dim gEvent(10)
Dim gHistory(3)
Dim gBelt(5)
Dim gAllegiance(2)
Dim gRating(5)
Dim gHardcore(5)
Dim gResult(5)
Dim gChart(5)
Dim gProduction(20)
Global gCursor,gSmallPrint
Global gCrown,gCup,gCrew
Global gInjured,gVacant,gTraining
Global gInjuryDate,gCourtDate
Global gMagazine,gPressPhoto
Global gNewspaper,gReport
Global gVersus,gSmallVersus
Global gCamera,gClock
Dim gNewsIdentity(5)
Dim gNewsScene(13)
Dim gNewsAdvert(10)
Dim gFed(10)
Dim gFedDark(10)
Dim gArena(30)
;LOADING PROCESS
Function LoadImages()
 ;main font
 size=8
 For count=0 To 16
  font(count)=LoadFont("Comic Book Normal.ttf",size,0,0,0) ;LoadImageFont("HanWangCC02",48)
  size=size+2
 Next 
 ;news font (3=news, 8=promo, 10=headline)
 fontNews(0)=LoadFont("Times New Roman",12,0,0,0)
 size=15
 For count=1 To 11
  fontNews(count)=LoadFont("Times New Roman",size,1,0,0)
  size=size+3
 Next 
 fontNews(12)=LoadFont("Times New Roman",90,1,0,0)
 ;stat font
 fontStat(0)=LoadFont("Tahoma.ttf",13,0,0,0)
 fontStat(1)=LoadFont("Tahoma.ttf",16,1,0,0)
 fontStat(2)=LoadFont("Tahoma.ttf",20,1,0,0)
 fontStat(3)=LoadFont("Tahoma.ttf",24,1,0,0)
 fontStat(4)=LoadFont("Tahoma.ttf",28,1,0,0)
 fontStat(5)=LoadFont("Tahoma.ttf",32,1,0,0)
 fontStat(6)=LoadFont("Tahoma.ttf",36,1,0,0)  
 ;background
 gBackground=LoadImage("Graphics/Background.png")
 MaskImage gBackground,255,0,255
 ResizeImage gBackground,GraphicsWidth(),GraphicsHeight()
 ;logos
 For count=1 To 3
  gLogo(count)=LoadImage("Graphics/Logo0"+count+".png")
  MaskImage gLogo(count),255,0,255
 Next
 gMDickie=LoadImage("Graphics/MDickie.png")
 MaskImage gMDickie,255,0,255
 ;menu boxes
 For count=1 To 14
  gMenu(count)=LoadImage("Graphics/Menus/Menu"+Dig$(count,10)+".png")
  MaskImage gMenu(count),255,0,255
 Next
 For count=1 To 2
  gBlackout(count)=LoadImage("Graphics/Menus/Blackout0"+count+".png")
  MaskImage gBlackout(count),255,0,255
 Next
 gPortrait=LoadImage("Graphics/Menus/Portrait.png")
 MaskImage gPortrait,255,0,255
 ;stat headers
 For count=1 To 11
  gStat(count)=LoadImage("Graphics/Menus/Stat"+Dig$(count,10)+".png")
  MaskImage gStat(count),255,0,255
 Next 
 ;scrollers
 For count=1 To 4
  gScroll(count)=LoadImage("Graphics/Menus/Scroll"+Dig$(count,10)+".png")
  MaskImage gScroll(count),255,0,255
 Next 
 ;misc icons
 gCursor=LoadImage("Graphics/Cursor02.png")
 MaskImage gCursor,255,0,255
 gCamera=LoadImage("Graphics/Camera.png")
 MaskImage gCamera,255,0,255
 gClock=LoadImage("Graphics/Clock.png")
 MaskImage gClock,255,0,255
 gVersus=LoadImage("Graphics/Versus.png")
 MaskImage gVersus,255,0,255
 gSmallVersus=LoadImage("Graphics/Versus (Small).png")
 MaskImage gSmallVersus,255,0,255
 gSmallPrint=LoadImage("Graphics/Smallprint.png")
 MaskImage gSmallPrint,255,0,255
 gInjured=LoadImage("Graphics/Injured.png")
 MaskImage gInjured,255,0,255
 gVacant=LoadImage("Graphics/Vacant.png")
 MaskImage gVacant,255,0,255
 gTraining=LoadImage("Graphics/Training.png")
 MaskImage gTraining,255,0,255
 gAllegiance(0)=LoadImage("Graphics/Face.png")
 MaskImage gAllegiance(0),255,0,255
 gAllegiance(1)=LoadImage("Graphics/Heel.png")
 MaskImage gAllegiance(1),255,0,255
 gCrown=LoadImage("Graphics/Crown.png")
 MaskImage gCrown,255,0,255
 For count=1 To 3
  gBelt(count)=LoadImage("Graphics/Belt0"+count+".png")
  MaskImage gBelt(count),255,0,255
 Next
 gCup=LoadImage("Graphics/Cup.png")
 MaskImage gCup,255,0,255
 ;control icons
 For count=0 To 2
  gControl(count)=LoadImage("Graphics/Control0"+count+".png")
  MaskImage gControl(count),255,0,255
 Next 
 ;calendar icons
 gDateHighlight=LoadImage("Graphics/Calendar/Highlight.png")
 MaskImage gDateHighlight,255,0,255
 For count=1 To 2
  gMonth(count)=LoadImage("Graphics/Calendar/Month0"+count+".png")
  MaskImage gMonth(count),255,0,255
 Next
 gInjuryDate=LoadImage("Graphics/Calendar/Injured.png")
 MaskImage gInjuryDate,255,0,255 
 gCourtDate=LoadImage("Graphics/Calendar/Court.png")
 MaskImage gCourtDate,255,0,255 
 For count=0 To 6
  gEvent(count)=LoadImage("Graphics/Calendar/Event0"+count+".png")
  MaskImage gEvent(count),255,0,255
 Next
 For count=0 To 2
  gHistory(count)=LoadImage("Graphics/Calendar/History0"+count+".png")
  MaskImage gHistory(count),255,0,255
 Next 
 ;entertainment icons
 For count=1 To 5
  gRating(count)=LoadImage("Graphics/Ratings/Stars0"+count+".bmp")
  MaskImage gRating(count),255,0,255
 Next
 For count=1 To 5
  gHardcore(count)=LoadImage("Graphics/Ratings/Skulls0"+count+".bmp")
  MaskImage gHardcore(count),255,0,255
 Next
 For count=1 To 5
  gResult(count)=LoadImage("Graphics/Ratings/Result0"+count+".bmp")
  MaskImage gResult(count),255,0,255
 Next
 ;chart icons
 For count=0 To 3
  gChart(count)=LoadImage("Graphics/Ratings/Chart0"+count+".bmp")
  MaskImage gChart(count),255,0,255
 Next
 ;promotion logos
 For count=1 To 9
  gFed(count)=LoadImage("Graphics/Promotions/Promotion0"+count+".png")
  MaskImage gFed(count),0,0,0
  gFedDark(count)=LoadImage("Graphics/Promotions/Faded0"+count+".png")
  MaskImage gFedDark(count),255,0,255
 Next
 ;arena previews
 For count=1 To 26
  gArena(count)=LoadImage("Graphics/Arenas/Arena"+Dig$(count,10)+".JPG")
  MaskImage gArena(count),255,0,255
 Next
 ;production icons
 For count=0 To 12
  gProduction(count)=LoadImage("Graphics/Productions/Production"+Dig$(count,10)+".png")
  MaskImage gProduction(count),255,0,255
 Next
 gCrew=LoadImage("Graphics/Productions/Crew.png")
 MaskImage gCrew,255,0,255 
 ;articles
 gMagazine=LoadImage("Graphics/Articles/Magazine.png")
 MaskImage gMagazine,255,0,255
 gNewspaper=LoadImage("Graphics/Articles/Newspaper.png")
 MaskImage gNewspaper,255,0,255
 gReport=LoadImage("Graphics/Articles/Report.png")
 MaskImage gReport,255,0,255
 ;newspaper detail
 For count=1 To 1
  gNewsIdentity(count)=LoadImage("Graphics/Articles/Identity"+Dig$(count,10)+".JPG")
  MaskImage gNewsIdentity(count),255,0,255
 Next
 For count=1 To 12
  gNewsScene(count)=LoadImage("Graphics/Articles/Scene"+Dig$(count,10)+".JPG")
  MaskImage gNewsScene(count),255,0,255
 Next
 For count=1 To 9
  gNewsAdvert(count)=LoadImage("Graphics/Articles/Advert"+Dig$(count,10)+".JPG")
  MaskImage gNewsAdvert(count),255,0,255
 Next
 ;outro
 gOutro=LoadImage("Graphics/Outro.JPG")
 MaskImage gOutro,255,0,255
End Function

;///////////////////////////////////////////////////////
;--------------------- TEXTURES ------------------------
;///////////////////////////////////////////////////////
.Textures
;count hat textures
Global no_hats=0
folder=ReadDir(CurrentDir$()+"Characters/Headwear/")
 Repeat
  filename$=NextFile$(folder)
  namer$=Upper$(Left$(filename$,3))
  If FileType(CurrentDir$()+"Characters/Headwear/"+filename$)=1 And namer$="HAT" And no_hats<200 Then no_hats=no_hats+1
 Until filename$=""
CloseDir(folder)
;count hair textures
Global no_hairs=0
folder=ReadDir(CurrentDir$()+"Characters/Hair/")
 Repeat
  filename$=NextFile$(folder)
  namer$=Upper$(Left$(filename$,4))
  If FileType(CurrentDir$()+"Characters/Hair/"+filename$)=1 And namer$="HAIR" And no_hairs<200 Then no_hairs=no_hairs+1
 Until filename$=""
CloseDir(folder)
;count beard textures
Global no_beards=0
folder=ReadDir(CurrentDir$()+"Characters/Beards/")
 Repeat
  filename$=NextFile$(folder)
  namer$=Upper$(Left$(filename$,5))
  If FileType(CurrentDir$()+"Characters/Beards/"+filename$)=1 And namer$="BEARD" Then no_beards=no_beards+1
 Until filename$=""
CloseDir(folder)
;count face textures
Global no_faces=0
folder=ReadDir(CurrentDir$()+"Characters/Faces/")
 Repeat
  filename$=NextFile$(folder)
  namer$=Upper$(Left$(filename$,4))
  If FileType(CurrentDir$()+"Characters/Faces/"+filename$)=1 And namer$="FACE" And no_faces<200 Then no_faces=no_faces+1
 Until filename$=""
CloseDir(folder)
;count eye textures
Global no_eyes=0
folder=ReadDir(CurrentDir$()+"Characters/Eyes/")
 Repeat
  filename$=NextFile$(folder)
  namer$=Upper$(Left$(filename$,4))
  If FileType(CurrentDir$()+"Characters/Eyes/"+filename$)=1 And namer$="EYES" And no_eyes<100 Then no_eyes=no_eyes+1
 Until filename$=""
CloseDir(folder)
;count body textures
Global no_bodies=0
folder=ReadDir(CurrentDir$()+"Characters/Bodies/")
 Repeat
  filename$=NextFile$(folder)
  namer$=Upper$(Left$(filename$,4))
  If FileType(CurrentDir$()+"Characters/Bodies/"+filename$)=1 And namer$="BODY" And no_bodies<200 Then no_bodies=no_bodies+1
 Until filename$=""
CloseDir(folder)
;count arm textures
Global no_arms=0
folder=ReadDir(CurrentDir$()+"Characters/Arms/")
 Repeat
  filename$=NextFile$(folder)
  namer$=Upper$(Left$(filename$,3))
  If FileType(CurrentDir$()+"Characters/Arms/"+filename$)=1 And namer$="ARM" And no_arms<200 Then no_arms=no_arms+1
 Until filename$=""
CloseDir(folder)
;count hand textures
Global no_hands=0
folder=ReadDir(CurrentDir$()+"Characters/Hands/")
 Repeat
  filename$=NextFile$(folder)
  namer$=Upper$(Left$(filename$,5))
  If FileType(CurrentDir$()+"Characters/Hands/"+filename$)=1 And namer$="HANDS" Then no_hands=no_hands+1
 Until filename$=""
CloseDir(folder)
;count leg textures
Global no_legs=0
folder=ReadDir(CurrentDir$()+"Characters/Legs/")
 Repeat
  filename$=NextFile$(folder)
  namer$=Upper$(Left$(filename$,4))
  If FileType(CurrentDir$()+"Characters/Legs/"+filename$)=1 And namer$="LEGS" And no_legs<200 Then no_legs=no_legs+1
 Until filename$=""
CloseDir(folder)
;count sign textures
Global no_signtex=0
folder=ReadDir(CurrentDir$()+"World/Signs/")
 Repeat
  filename$=NextFile$(folder)
  namer$=Upper$(Left$(filename$,4))
  If FileType(CurrentDir$()+"World/Signs/"+filename$)=1 And namer$="SIGN" And no_signtex<100 Then no_signtex=no_signtex+1
 Until filename$=""
CloseDir(folder)
;world variables
Dim tCrowd(5)
Dim tSign(no_signtex)
Dim tVideo(30)
Dim tPoster(6)
Dim tPlant(4)
Dim tRope(20)
Dim tWall(3)
Dim tCeiling(3)
Dim tGround(3)
Global tApron,tCanvas,tCanvasEdge
Global tPad,tPost,tMatting,tCurtain
Global tVideoOverlay,tCharred,tBanner
Global tBarbedRope,tElectricRope,tHotRope
;item variables
Dim tCage(3)
Dim tGirder(3)
Dim tMetal(3)
Dim tWood(3)
Dim tGlass(2)
Dim tBelt(3)
Dim tBottle(2)
Global tChair,tScreen
Global tTape,tGame,tTacks
;costume variables
Global tEars,tPony
Dim tEyes(3),tEyeballs(no_eyes)
Dim tMouth(5)
Dim tSpecs(3)
Dim tHat(200),tHatAccess(200)
Dim tShaved(10),tBeard(no_beards)
Dim tHair(200),tHairAccess(200)
Dim tFace(200),tFaceType(200),tFaceAccess(200)
Dim tBody(200),tBodyType(200),tBodyAccess(200)
Dim tArm(200),tArmType(200),tArmAccess(200)
Dim tHands(200),tHandType(200),tHandsAccess(200)
Dim tLegs(200),tLegType(200),tLegsAccess(200)
Dim tBodyShade(100),tBodyShadeAccess(100)
Dim tArmShade(100),tArmShadeAccess(100)
Dim tHandShade(100),tHandShadeAccess(100)
Dim tLegShade(100),tLegShadeAccess(100)
Dim tBodyTattoo(100),tBodyTattooAccess(100)
Dim tArmTattoo(100),tArmTattooAccess(100)
Dim tFaceScar(5)
Dim tEyeScar(5)
Dim tBodyScar(5)
Dim tArmScar(5)
Dim tHandScar(5)
Dim tLegScar(5)
Dim tSeverEars(3)
Dim tSeverEyes(3)
Dim tSeverBody(3)
Dim tSeverArm(3)
Dim tSeverHand(3)
Dim tSeverLegs(3)
;labels
Dim labCount(3)
Global labSpecial,labEliminated,labWarning
Global labOut,labInjured,labLegal,labControl
;LOADING PROCESS
Function LoadTextures()
 Loader("Please Wait","Loading Textures")
 ;foliage
 For count=1 To 2
  tPlant(count)=LoadTexture("World/Sprites/Plant0"+count+".png",4)
 Next
 ;rope variations
 For count=1 To 9
  tRope(count)=LoadTexture("World/Ropes/Rope"+Dig$(count,10)+".JPG")
 Next
 tBarbedRope=LoadTexture("World/Ropes/Barbed.png",4)
 tElectricRope=LoadTexture("World/Ropes/Electric.png",4)
 tHotRope=LoadTexture("World/Ropes/Hot.png",4)
 ;videos
 For count=0 To 3
  tVideo(count)=LoadTexture("World/Videos/Video0"+count+".JPG")
 Next
 ;4=promotion logo
 ;5=national flag 
 ;6=classic game
 tVideo(10)=CreateTexture(512,256) ;live footage 
 tVideo(11)=CreateTexture(512,256) ;static shot 1
 tVideo(12)=CreateTexture(512,256) ;static shot 2
 ;20-30=wrestler portraits
 tVideoOverlay=LoadTexture("World/Videos/Overlay.png")
 tScreen=LoadTexture("World/Videos/Screen.JPG")
 ;cages
 For count=1 To 3
  tCage(count)=LoadTexture("World/Cage/Cage0"+count+".png",4)
 Next 
 tGirder(1)=LoadTexture("World/Cage/Girder01.png",4)
 tGirder(2)=LoadTexture("World/Cage/Girder02.JPG")
 ;crowd
 Loader("Please Wait","Loading Signs") 
 For count=1 To 5
  tCrowd(count)=LoadTexture("World/Sprites/Crowd0"+count+".png",4)
 Next
 For count=1 To no_signtex
  tSign(count)=LoadTexture("World/Signs/Sign"+Dig$(count,10)+".JPG")
 Next
 ;item materials
 Loader("Please Wait","Loading Items") 
 For count=1 To 2
  tMetal(count)=LoadTexture("Items/Textures/Metal0"+count+".JPG")
 Next
 For count=1 To 2
  tWood(count)=LoadTexture("Items/Textures/Wood0"+count+".JPG")
 Next
 tGlass(1)=LoadTexture("Items/Textures/Glass01.png")
 tGlass(2)=LoadTexture("Items/Textures/Glass02.png",4)
 tCharred=LoadTexture("Items/Textures/Charred.JPG")
 ;weapon materials
 For count=1 To 3
  tBelt(count)=LoadTexture("Items/Textures/Belt0"+count+".JPG")
 Next  
 For count=1 To 2
  tBottle(count)=LoadTexture("Items/Textures/Bottle0"+count+".JPG")
 Next
 tChair=LoadTexture("Items/Textures/Chair.png",4) 
 tTape=LoadTexture("Items/Textures/Tape.JPG")
 tGame=LoadTexture("Items/Textures/Game.JPG")
 tTacks=LoadTexture("Items/Textures/Tacks.png",4) 
 ;facial expressions
 Loader("Please Wait","Loading Accessories") 
 tEars=LoadTexture("Characters/Expressions/Ears.JPG")
 For count=1 To 3
  tEyes(count)=LoadTexture("Characters/Expressions/Eyes0"+count+".JPG")
 Next
 For count=0 To 5
  tMouth(count)=LoadTexture("Characters/Expressions/Mouth0"+count+".JPG")
 Next
 For count=1 To no_eyes
  tEyeballs(count)=LoadTexture("Characters/Eyes/Eyes"+Dig$(count,10)+".JPG")
 Next
 ;costume variations
 For count=1 To no_hats
  tHat(count)=LoadTexture("Characters/Headwear/Hat"+Dig$(count,10)+".JPG")
 Next
 For count=1 To 3
  tSpecs(count)=LoadTexture("Characters/Accessories/Specs"+Dig$(count,10)+".JPG")
 Next
 Loader("Please Wait","Loading Hair")
 tPony=LoadTexture("Characters/Hair/Pony.JPG") 
 For count=1 To 6
  tShaved(count)=LoadTexture("Characters/Hair/Shaved"+Dig$(count,10)+".JPG")
 Next
 For count=1 To no_hairs
  tHair(count)=LoadTexture("Characters/Hair/Hair"+Dig$(count,10)+".JPG")
 Next
 For count=1 To no_beards
  tBeard(count)=LoadTexture("Characters/Beards/Beard"+Dig$(count,10)+".JPG")
 Next
 Loader("Please Wait","Loading Faces")
 For count=1 To no_faces
  ;Loader("Please Wait","Loading Face "+Dig$(count,10)+" of "+no_faces)
  tFaceType(count)=0
  For scan=1 To 3
   If FileType("Characters/Faces/Face"+Dig$(count,10)+" ("+scan+").JPG")=1 Then tFaceType(count)=scan
  Next
  tFace(count)=LoadTexture("Characters/Faces/Face"+Dig$(count,10)+""+TextureAffix$(tFaceType(count))+".JPG")
 Next
 Loader("Please Wait","Loading Bodies")
 For count=1 To no_bodies
  ;Loader("Please Wait","Loading Body "+Dig$(count,10)+" of "+no_bodies)
  tBodyType(count)=0
  For scan=1 To 100
   If FileType("Characters/Bodies/Body"+Dig$(count,10)+" ("+scan+").JPG")=1 Then tBodyType(count)=scan
  Next
  tBody(count)=LoadTexture("Characters/Bodies/Body"+Dig$(count,10)+""+TextureAffix$(tBodyType(count))+".JPG")
 Next
 Loader("Please Wait","Loading Arms")
 For count=1 To no_arms
  ;Loader("Please Wait","Loading Arm "+Dig$(count,10)+" of "+no_arms)
  tArmType(count)=0
  For scan=1 To 100
   If FileType("Characters/Arms/Arm"+Dig$(count,10)+" ("+scan+").JPG")=1 Then tArmType(count)=scan
  Next
  tArm(count)=LoadTexture("Characters/Arms/Arm"+Dig$(count,10)+""+TextureAffix$(tArmType(count))+".JPG")
 Next
 Loader("Please Wait","Loading Hands")
 For count=1 To no_hands
  tHandType(count)=0
  For scan=1 To 100
   If FileType("Characters/Hands/Hands"+Dig$(count,10)+" ("+scan+").JPG")=1 Then tHandType(count)=scan
  Next
  tHands(count)=LoadTexture("Characters/Hands/Hands"+Dig$(count,10)+""+TextureAffix$(tHandType(count))+".JPG")
 Next 
 Loader("Please Wait","Loading Legs")
 For count=1 To no_legs
  ;Loader("Please Wait","Loading Legs "+Dig$(count,10)+" of "+no_legs)
  tLegType(count)=0
  For scan=1 To 100
   If FileType("Characters/Legs/Legs"+Dig$(count,10)+" ("+scan+").JPG")=1 Then tLegType(count)=scan
  Next
  tLegs(count)=LoadTexture("Characters/Legs/Legs"+Dig$(count,10)+""+TextureAffix$(tLegType(count))+".JPG")
 Next
 ;racial shades
 Loader("Please Wait","Loading Shades")
 For count=1 To 100
  tBodyShade(count)=LoadTexture("Characters/Bodies/Shading/Body"+Dig$(count,10)+".png") 
  tArmShade(count)=LoadTexture("Characters/Arms/Shading/Arm"+Dig$(count,10)+".png") 
  tHandShade(count)=LoadTexture("Characters/Hands/Shading/Hands"+Dig$(count,10)+".JPG")
  tLegShade(count)=LoadTexture("Characters/Legs/Shading/Legs"+Dig$(count,10)+".png") 
 Next
 ;tattos
 Loader("Please Wait","Loading Tattoos")
 For count=1 To 100
  tBodyTattoo(count)=LoadTexture("Characters/Bodies/Tattoos/Body"+Dig$(count,10)+".JPG") 
  tArmTattoo(count)=LoadTexture("Characters/Arms/Tattoos/Arm"+Dig$(count,10)+".JPG") 
 Next
 ;scarring
 Loader("Please Wait","Loading Scars")
 For count=0 To 5
  tFaceScar(count)=LoadTexture("Characters/Scarring/Face"+Dig$(count,10)+".JPG") 
 Next
 For count=0 To 4
  tEyeScar(count)=LoadTexture("Characters/Scarring/Eye"+Dig$(count,10)+".JPG") 
 Next 
 For count=0 To 4
  tBodyScar(count)=LoadTexture("Characters/Scarring/Body"+Dig$(count,10)+".JPG") 
 Next
 For count=0 To 4
  tArmScar(count)=LoadTexture("Characters/Scarring/Arm"+Dig$(count,10)+".JPG") 
 Next
 For count=0 To 4
  tHandScar(count)=LoadTexture("Characters/Scarring/Hand"+Dig$(count,10)+".JPG") 
 Next
 For count=0 To 4
  tLegScar(count)=LoadTexture("Characters/Scarring/Legs"+Dig$(count,10)+".JPG") 
 Next
 ;wounds
 For count=1 To 3
  tSeverEars(count)=LoadTexture("Characters/Scarring/Wounds/Ears"+Dig$(count,10)+".JPG") 
 Next
 For count=1 To 3
  tSeverEyes(count)=LoadTexture("Characters/Scarring/Wounds/Eyes"+Dig$(count,10)+".JPG") 
 Next
 For count=1 To 3
  tSeverBody(count)=LoadTexture("Characters/Scarring/Wounds/Body"+Dig$(count,10)+".JPG") 
 Next 
 For count=1 To 3
  tSeverArm(count)=LoadTexture("Characters/Scarring/Wounds/Arm"+Dig$(count,10)+".JPG") 
 Next 
 For count=1 To 2
  tSeverHand(count)=LoadTexture("Characters/Scarring/Wounds/Hand"+Dig$(count,10)+".JPG") 
 Next   
 For count=1 To 3
  tSeverLegs(count)=LoadTexture("Characters/Scarring/Wounds/Legs"+Dig$(count,10)+".JPG") 
 Next 
 ;labels
 Loader("Please Wait","Loading Labels")
 For count=1 To 3
  labCount(count)=LoadTexture("World/Sprites/Labels/Count0"+count+".bmp",12)
 Next
 labSpecial=LoadTexture("World/Sprites/Labels/Special.bmp",12)
 labWarning=LoadTexture("World/Sprites/Labels/Warning.bmp",12)
 labInjured=LoadTexture("World/Sprites/Labels/Injured.bmp",12)
 labEliminated=LoadTexture("World/Sprites/Labels/Eliminated.bmp",12)
 labLegal=LoadTexture("World/Sprites/Labels/Legal.bmp",12)
 labOut=LoadTexture("World/Sprites/Labels/Out.bmp",12)
 labControl=LoadTexture("World/Sprites/Labels/Control.bmp",12)
End Function

;RESTORE TEXTURES
Function RestoreTextures()
 For count=1 To no_hats
  If tHat(count)>0 And tHatAccess(count)>0
   FreeTexture tHat(count)
   tHat(count)=LoadTexture("Characters/Headwear/Hat"+Dig$(count,10)+".JPG")
  EndIf
 Next
 For count=1 To no_hairs
  If tHair(count)>0 And tHairAccess(count)>0
   FreeTexture tHair(count)
   tHair(count)=LoadTexture("Characters/Hair/Hair"+Dig$(count,10)+".JPG")
  EndIf
 Next
 For count=1 To no_faces
  If tFace(count)>0 And tFaceAccess(count)>0
   FreeTexture tFace(count)
   tFace(count)=LoadTexture("Characters/Faces/Face"+Dig$(count,10)+""+TextureAffix$(tFaceType(count))+".JPG")
  EndIf
 Next
 For count=1 To no_bodies
  If tBody(count)>0 And tBodyAccess(count)>0
   FreeTexture tBody(count)
   tBody(count)=LoadTexture("Characters/Bodies/Body"+Dig$(count,10)+""+TextureAffix$(tBodyType(count))+".JPG")
  EndIf
 Next
 For count=1 To no_arms
  If tArm(count)>0 And tArmAccess(count)>0
   FreeTexture tArm(count)
   tArm(count)=LoadTexture("Characters/Arms/Arm"+Dig$(count,10)+""+TextureAffix$(tArmType(count))+".JPG")
  EndIf
 Next
 For count=1 To no_hands
  If tHands(count)>0 And tHandsAccess(count)>0
   FreeTexture tHands(count)
   tHands(count)=LoadTexture("Characters/Hands/Hands"+Dig$(count,10)+""+TextureAffix$(tHandType(count))+".JPG")
  EndIf
 Next
 For count=1 To no_legs
  If tLegs(count)>0 And tLegsAccess(count)>0
   FreeTexture tLegs(count)
   tLegs(count)=LoadTexture("Characters/Legs/Legs"+Dig$(count,10)+""+TextureAffix$(tLegType(count))+".JPG")
  EndIf
 Next
 For count=1 To 100
  If tBodyShade(count)>0 And tBodyShadeAccess(count)>0
   FreeTexture tBodyShade(count)
   tBodyShade(count)=LoadTexture("Characters/Bodies/Shading/Body"+Dig$(count,10)+".png") 
  EndIf
  If tArmShade(count)>0 And tArmShadeAccess(count)>0
   FreeTexture tArmShade(count)
   tArmShade(count)=LoadTexture("Characters/Arms/Shading/Arm"+Dig$(count,10)+".png") 
  EndIf
  If tHandShade(count)>0 And tHandShadeAccess(count)>0
   FreeTexture tHandShade(count)
   tHandShade(count)=LoadTexture("Characters/Hands/Shading/Hands"+Dig$(count,10)+".JPG") 
  EndIf
  If tLegShade(count)>0 And tLegShadeAccess(count)>0
   FreeTexture tLegShade(count)
   tLegShade(count)=LoadTexture("Characters/Legs/Shading/Legs"+Dig$(count,10)+".png") 
  EndIf
  If tBodyTattoo(count)>0 And tBodyTattooAccess(count)>0
   FreeTexture tBodyTattoo(count)
   tBodyTattoo(count)=LoadTexture("Characters/Bodies/Tattoos/Body"+Dig$(count,10)+".JPG") 
  EndIf
  If tArmTattoo(count)>0 And tArmTattooAccess(count)>0
   FreeTexture tArmTattoo(count)
   tArmTattoo(count)=LoadTexture("Characters/Arms/Tattoos/Arm"+Dig$(count,10)+".JPG") 
  EndIf
 Next
End Function

;GET TEXTURE AFFIX
Function TextureAffix$(mask)
 affix$=""
 If mask>0 Then affix$=" ("+mask+")"
 Return affix$ 
End Function

;RESET TEXTURE RECORD
Function ResetTextures()
 ;costumes
 For count=1 To 200
  tHatAccess(count)=0
  tHairAccess(count)=0
  tFaceAccess(count)=0
  tBodyAccess(count)=0
  tArmAccess(count)=0
  tHandsAccess(count)=0
  tLegsAccess(count)=0
 Next
 ;racial shades
 For count=1 To 100
  tBodyShadeAccess(count)=0
  tArmShadeAccess(count)=0
  tHandShadeAccess(count)=0
  tLegShadeAccess(count)=0
  tBodyTattooAccess(count)=0
  tArmTattooAccess(count)=0
 Next
End Function

;//////////////////////////////////////////////////////
;-------------------- ROSTERS -------------------------
;//////////////////////////////////////////////////////
.Rosters
;status
Global fed
Dim fedName$(10)
Dim fedOldBank(10)
Dim fedBank(10)
Dim fedPopularity(10)
Dim fedReputation(10)
Dim fedOldPopularity(10)
Dim fedOldReputation(10)
Dim fedNewPopularity(10)
Dim fedNewReputation(10)
Dim fedSize(10)
Dim fedRoster(10,50)
Dim fedRank(10,optCharLim)
Dim fedFatality(10)
Dim fedProduction(10,20)
Dim fedLocked(10)
;title history
Dim fedBooker(10)
Dim fedCupHolder(10)
Dim fedChampWorld(10)
Dim fedChampInter(10)
Dim fedChampTag(10,2)
Dim fedOldChampWorld(10)
Dim fedOldChampInter(10)
Dim fedOldChampTag(10,2)
Dim fedHistCount(10,4,10)
Dim fedHistChar(10,4,10)
Dim fedHistPartner(10,4,10)
Dim fedHistDate(10,4,10)
Dim fedHistYear(10,4,10)
;rankings
Dim fedList(10)
Dim fedOldList(10)
Dim fedRanked(10)
Dim fedOldRanked(10)
;TV ratings
Dim showName$(20)
Dim showGenre$(20)
Dim showPopularity(20)
Dim showOldPopularity(20)
Dim showList(20)
Dim showOldList(20)
Dim showRanked(20)
Dim showOldRanked(20)
Dim showTime(10)
showTime(10)=25
showTime(9)=50
showTime(8)=75
showTime(7)=100
showTime(6)=125
showTime(5)=150
showTime(4)=175
showTime(3)=210
showTime(2)=250
showTime(1)=300
;initial TV data
Function ResetTVRatings()
 n=6 : showName$(n)="THAT Love Triangle" : showGenre$(n)="Soap Opera" : showPopularity(n)=75
 n=7 : showName$(n)="Popscene" : showGenre$(n)="Live Music" : showPopularity(n)=85
 n=8 : showName$(n)="Popcorn" : showGenre$(n)="Movie Reviews" : showPopularity(n)=80
 n=9 : showName$(n)="Sure Shot" : showGenre$(n)="Cartoon" : showPopularity(n)=70
 n=10 : showName$(n)="Wrecked" : showGenre$(n)="Documentary" : showPopularity(n)=70
 n=11 : showName$(n)="World War Alpha" : showGenre$(n)="History" : showPopularity(n)=70
 n=12 : showName$(n)="Grass Roots" : showGenre$(n)="Soccer" : showPopularity(n)=75
 n=13 : showName$(n)="Big BumpZ" : showGenre$(n)="Home Videos" : showPopularity(n)=85
 n=14 : showName$(n)="The MDickie Show" : showGenre$(n)="Chat Show" : showPopularity(n)=80
 n=15 : showName$(n)="Under Development" : showGenre$(n)="Videogames" : showPopularity(n)=70
 n=16 : showName$(n)="All American Football" : showGenre$(n)="Sport" : showPopularity(n)=90
 n=17 : showName$(n)="All American Baseball" : showGenre$(n)="Sport" : showPopularity(n)=85
 n=18 : showName$(n)="All American Basketball" : showGenre$(n)="Sport" : showPopularity(n)=80
 n=19 : showName$(n)="Hard Time" : showGenre$(n)="Documentary" : showPopularity(n)=85
 n=20 : showName$(n)="Reach" : showGenre$(n)="Boxing" : showPopularity(n)=80
End Function
;productions
Dim prodName$(20)
Dim prodDesc$(20)
Dim prodCost(20)
;~~~~~~~~~~~~
n=1 : prodName$(n)="Script Writers" : prodCost(n)=2000
prodDesc$(n)="Allows wrestlers to take part in spoken storylines"
n=2 : prodName$(n)="Emergency Medics" : prodCost(n)=1000
prodDesc$(n)="Increases health recovery and reduces injury times"
n=3 : prodName$(n)="Personal Trainers" : prodCost(n)=1000
prodDesc$(n)="Increases effectiveness of training and allows moves to be altered"
n=4 : prodName$(n)="Fashion Stylists" : prodCost(n)=1000
prodDesc$(n)="Allows appearances to be altered (increases potential by 5%)"
;~~~~~~~~~~~~
n=5 : prodName$(n)="Ring Crew" : prodCost(n)=1000
prodDesc$(n)="Allows the arena to be customized (increases potential by 5%)"
n=6 : prodName$(n)="Sound System" : prodCost(n)=1000
prodDesc$(n)="Allows entrances to be accompanied by music (increases potential by 5%)"
n=7 : prodName$(n)="Lighting" : prodCost(n)=1000
prodDesc$(n)="Allows lighting effects in the arena (increases potential by 5%)"
n=8 : prodName$(n)="Broadcasting" : prodCost(n)=1000
prodDesc$(n)="Allows video displays in the arena (increases potential by 5%)"
;~~~~~~~~~~~~
n=9 : prodName$(n)="Pyrotechnics" : prodCost(n)=2000
prodDesc$(n)="Allows explosives and fire to be used in matches"
n=10 : prodName$(n)="Engineering" : prodCost(n)=2000
prodDesc$(n)="Allows cages and novelty ropes to be used in matches"
n=11 : prodName$(n)="Furniture" : prodCost(n)=2000
prodDesc$(n)="Allows large items to be placed in the arena"
n=12 : prodName$(n)="Props" : prodCost(n)=2000
prodDesc$(n)="Allows handheld weapons to be placed in the arena"

;//////////////////////////////////////////////////////
;-------------------- PLAYERS -------------------------
;//////////////////////////////////////////////////////
.Players
Dim p(optPlayLim)
Dim pFoc(optPlayLim)
Dim pOldFoc(optPlayLim)
Dim pWeapFoc(optPlayLim)
Dim pItemFoc(optPlayLim)
Dim pFocTim(optPlayLim)
Dim pOutTim(optPlayLim)
Dim pX#(optPlayLim)
Dim pY#(optPlayLim)
Dim pZ#(optPlayLim)
Dim pOldX#(optPlayLim)
Dim pOldY#(optPlayLim)
Dim pOldZ#(optPlayLim)
Dim pA#(optPlayLim)
Dim pTA#(optPlayLim)
Dim pOldA#(optPlayLim)
Dim pBodyXA#(optPlayLim)
Dim pBodyZA#(optPlayLim)
Dim pBodyTXA#(optPlayLim)
Dim pBodyTZA#(optPlayLim)
Dim pOldBodyXA#(optPlayLim)
Dim pOldBodyZA#(optPlayLim)
Dim pSourceBodyXA#(optPlayLim,10)
Dim pEndBodyXA#(optPlayLim,10)
Dim pScanX#(optPlayLim,6)
Dim pScanZ#(optPlayLim,6)
;physics
Dim pDashState(optPlayLim) ;0=none, 1=initiated, 2=released, 3=returned
Dim pDashTim(optPlayLim)
Dim pDashA#(optPlayLim)
Dim pMomentum(optPlayLim)
Dim pTravel#(optPlayLim)
Dim pGravity#(optPlayLim)
Dim pVelocity#(optPlayLim)
Dim pFlightA#(optPlayLim)
Dim pFlyAgenda(optPlayLim) ;1=inside ring, 2=inside to outside, 3=outside
Dim pGround#(optPlayLim)
Dim pHurtA#(optPlayLim)
Dim pStagger#(optPlayLim)
Dim pHurtTim(optPlayLim)
Dim pVictim(optPlayLim)
Dim pSpeed#(optPlayLim)
Dim pFriction(optPlayLim)
Dim pElevation#(optPlayLim)
Dim pPlatform(optPlayLim) ;1-4=aprons, 5-8=turnbuckles, 10-90=scenery, 90-98=cage walls, 100+=items
Dim pOldPlatform(optPlayLim)
Dim pPlatformX#(optPlayLim)
Dim pPlatformY#(optPlayLim)
Dim pPlatformZ#(optPlayLim)
Dim pExcusedPlays(optPlayLim)
Dim pExcusedWorld(optPlayLim)
Dim pExcusedEdges(optPlayLim)
Dim pExcusedItems(optPlayLim)
;status
Dim pRole(optPlayLim)
Dim pHeel(optPlayLim)
Dim pSting(optPlayLim)
Dim pMultiSting(optPlayLim,optPlayLim)
Dim pItemSting(optPlayLim,100)
Dim pWeapSting(optPlayLim,100)
Dim pRopeSting(optPlayLim,12)
Dim pCageSting(optPlayLim)
Dim pHealth(optPlayLim)
Dim pHealthLimit(optPlayLim)
Dim pShowHealth(optPlayLim)
Dim pHP(optPlayLim)
Dim pInjured(optPlayLim,10) ;0=universal, 1=hand, 2=arm, 3=ribs, 4=legs, 5=head
Dim pImmunity(optPlayLim)
Dim pDazed(optPlayLim)
Dim pDizzyTim(optPlayLim)
Dim pBlindTim(optPlayLim)
Dim pBuckleTim(optPlayLim)
Dim pDT(optPlayLim)
Dim pLieTim(optPlayLim)
Dim pHeat(optPlayLim)
Dim pShowHeat(optPlayLim)
Dim pSpecial(optPlayLim)
Dim pSpecialFlash(optPlayLim)
Dim pPopularity(optPlayLim)
Dim pStrength(optPlayLim)
Dim pSkill(optPlayLim)
Dim pAgility(optPlayLim)
Dim pStamina(optPlayLim)
Dim pToughness(optPlayLim)
Dim pCarrying(optPlayLim)
Dim pHolding(optPlayLim)
Dim pWeaponAccess(optPlayLim,100)
Dim pSpurt(optPlayLim)
;officiating
Dim pGrappling(optPlayLim)
Dim pGrappler(optPlayLim)
Dim pGrappleAssist(optPlayLim)
Dim pStretched(optPlayLim)
Dim pPinning(optPlayLim)
Dim pPinner(optPlayLim)
Dim pPinStyle(optPlayLim) ;1=overhead, 2=from left (flat), 3=from right (flat), 4=from left (hooked), 5=from right (hooked)
Dim pPinX#(optPlayLim)
Dim pPinZ#(optPlayLim)
Dim pPinA#(optPlayLim)
Dim pFalls(optPlayLim)
Dim pPinCount(optPlayLim)
Dim pOutCount(optPlayLim)
Dim pCountTim(optPlayLim)
Dim pOutsideTim(optPlayLim)
Dim pRefAward(optPlayLim)
Dim pRefVictim(optPlayLim)
Dim pChecked(optPlayLim)
Dim pWarned(optPlayLim)
Dim pAutoTim(optPlayLim)
Dim pSubmitDelay(optPlayLim)
Dim pDQDelay(optPlayLim)
;input
Dim pControl(optPlayLim)
Dim pJoystick(optPlayLim) ;when pControl is 2 (joystick), this determines the joystick INDEX (0 to 3)
Dim pController(optPlayLim)
Dim cUp(optPlayLim)
Dim cDown(optPlayLim)
Dim cLeft(optPlayLim)
Dim cRight(optPlayLim)
Dim cUpTim(optPlayLim)
Dim cDownTim(optPlayLim)
Dim cLeftTim(optPlayLim)
Dim cRightTim(optPlayLim)
Dim cAttack(optPlayLim)
Dim cAttackAgenda(optPlayLim) ;0=default to low, 1=force high
Dim cRun(optPlayLim)
Dim cBlock(optPlayLim)
Dim cGrapple(optPlayLim)
Dim cPickUp(optPlayLim)
Dim cPickUpFree(optPlayLim)
Dim cSwitch(optPlayLim)
Dim cTaunt(optPlayLim)
;AI
Dim pAgenda(optPlayLim)
Dim pOldAgenda(optPlayLim)
Dim pTX#(optPlayLim)
Dim pTZ#(optPlayLim)
Dim pSubX#(optPlayLim)
Dim pSubZ#(optPlayLim)
Dim pActX#(optPlayLim)
Dim pActZ#(optPlayLim)
Dim pFollow(optPlayLim)
Dim pSatisfied(optPlayLim)
Dim pNowhere(optPlayLim)
Dim pRelocate(optPlayLim)
Dim pRunTim(optPlayLim)
Dim pBlockTim(optPlayLim)
Dim pManager(optPlayLim)
Dim pClient(optPlayLim)
Dim pAngerTim(optPlayLim,optPlayLim)
;animation
Dim pSeq(optPlayLim,1100)
Dim pState(optPlayLim)
Dim pAnim(optPlayLim)
Dim pOldAnim(optPlayLim)
Dim pAnimStage(optPlayLim)
Dim pAnimTim(optPlayLim)
Dim pAnimSpeed#(optPlayLim)
Dim pRevAnim(optPlayLim)
Dim pRevTim#(optPlayLim)
Dim pRevSpeed#(optPlayLim)
Dim pStepTim#(optPlayLim)
Dim pHurtDelay(optPlayLim)
Dim pDelayAnim(optPlayLim)
Dim pReaction(optPlayLim)
Dim pInteract(optPlayLim)
Dim pRefTaunt(optPlayLim,4)
;eyes
Dim pEyes(optPlayLim)
Dim pOldEyes(optPlayLim)
Dim pEyeTex(optPlayLim)
Dim pSquint(optPlayLim) ;% of blink
Dim pSquintTarget(optPlayLim) ;% of blink
Dim pSquintRevert(optPlayLim) ;% of blink
Dim pSquintSpeed(optPlayLim)
Dim pBlinkTim(optPlayLim)
Dim pLookX#(optPlayLim)
Dim pLookY#(optPlayLim)
Dim pLookZ#(optPlayLim)
Dim pLookTX#(optPlayLim)
Dim pLookTY#(optPlayLim)
Dim pLookTZ#(optPlayLim)
;appearance
Dim pChar(optPlayLim)
Dim pCostume(optPlayLim)
Dim pWeapon(optPlayLim)
Dim pChampion(optPlayLim)
Dim pMouth(optPlayLim)
Dim pSpeaking(optPlayLim)
Dim pShadow(optPlayLim,50)
Dim pLabel(optPlayLim)
Dim pLabelX#(optPlayLim)
Dim pLabelY#(optPlayLim)
Dim pLabelZ#(optPlayLim)
Dim pOldLabel(optPlayLim)
Dim pHidden(optPlayLim)
;teams
Global curtainRotor
Dim curtainSlot(2)
Dim pCurtain(optPlayLim)
Dim pTeam(optPlayLim)
Dim pChaosTim(optPlayLim)
Dim pEliminated(optPlayLim)
Dim pIntruder(optPlayLim)
Dim pAbused(optPlayLim)
Dim pListed(optPlayLim)
Dim teamLegal(optPlayLim) 
Dim teamFalls(optPlayLim) 
Dim teamRemaining(optPlayLim) 
Dim teamHealth(optPlayLim) 
Dim teamX#(optPlayLim),teamZ#(optPlayLim) 
teamX#(1)=-100 : teamZ#(1)=-100
teamX#(2)=100 : teamZ#(2)=100

;///////////////////////////////////////////////////////
;----------------------- LIMBS -------------------------
;///////////////////////////////////////////////////////
.Limbs
;status
Dim pLimb(optPlayLim,50)
Dim pLimbX#(optPlayLim,50)
Dim pLimbY#(optPlayLim,50)
Dim pLimbZ#(optPlayLim,50)
Dim pScar(optPlayLim,50)
Dim pOldScar(optPlayLim,50)
;heirarchy
Dim limbPrecede(50)
Dim limbSource(50)
;head
;left arm
limbPrecede(2)=0 : limbSource(2)=1 ;left ear
limbPrecede(3)=0 : limbSource(3)=1 ;right ear
limbPrecede(45)=0 : limbSource(45)=1 ;left eye
limbPrecede(46)=0 : limbSource(46)=1 ;right eye
;left arm
limbPrecede(6)=7 : limbSource(6)=5 ;left bicep
limbPrecede(7)=8 : limbSource(7)=6 ;left arm
limbPrecede(8)=0 : limbSource(8)=7 ;left hand
limbPrecede(9)=10 : limbSource(9)=8 ;left thumb01
limbPrecede(10)=0 : limbSource(10)=9 ;left thumb02
limbPrecede(11)=12 : limbSource(11)=8 ;left finger01
limbPrecede(12)=0 : limbSource(12)=11 ;left finger02
limbPrecede(13)=14 : limbSource(13)=8 ;left finger03
limbPrecede(14)=0 : limbSource(14)=13 ;left finger04
limbPrecede(15)=16 : limbSource(15)=8 ;left finger05
limbPrecede(16)=0 : limbSource(16)=15 ;left finger06
limbPrecede(17)=18 : limbSource(17)=8 ;left finger07
limbPrecede(18)=0 : limbSource(18)=17 ;left finger08
;right arm
limbPrecede(19)=20 : limbSource(19)=5 ;right bicep
limbPrecede(20)=21 : limbSource(20)=19 ;right arm
limbPrecede(21)=0 : limbSource(21)=20 ;right hand
limbPrecede(22)=23 : limbSource(22)=21 ;right thumb01
limbPrecede(23)=0 : limbSource(23)=22 ;right thumb02
limbPrecede(24)=25 : limbSource(24)=21 ;right finger01
limbPrecede(25)=0 : limbSource(25)=24 ;right finger02
limbPrecede(26)=27 : limbSource(26)=21 ;right finger03
limbPrecede(27)=0 : limbSource(27)=26 ;right finger04
limbPrecede(28)=29 : limbSource(28)=21 ;right finger05
limbPrecede(29)=0 : limbSource(29)=28 ;right finger06
limbPrecede(30)=31 : limbSource(30)=21 ;right finger07
limbPrecede(31)=0 : limbSource(31)=30 ;right finger08
;legs
limbPrecede(39)=40 : limbSource(39)=36 ;left thigh
limbPrecede(40)=41 : limbSource(40)=39 ;left leg
limbPrecede(41)=0 : limbSource(41)=40 ;left foot
limbPrecede(42)=43 : limbSource(42)=36 ;right thigh
limbPrecede(43)=44 : limbSource(43)=42 ;right leg
limbPrecede(44)=0 : limbSource(44)=43 ;right foot

;///////////////////////////////////////////////////////
;--------------------- CHARACTERS ----------------------
;///////////////////////////////////////////////////////
.Characters
Global no_chars
;profile
Dim charName$(optCharLim)
Dim charTeamName$(optCharLim)
Dim charAge(optCharLim)
Dim charHeight(optCharLim)
Dim charWeight(optCharLim)
Dim charGender(optCharLim)
Dim charPopularity(optCharLim)
Dim charStrength(optCharLim)
Dim charSkill(optCharLim)
Dim charAgility(optCharLim)
Dim charStamina(optCharLim)
Dim charToughness(optCharLim)
Dim charAttitude(optCharLim)
Dim charHappiness(optCharLim)
Dim charOldPopularity(optCharLim)
Dim charOldStrength(optCharLim)
Dim charOldSkill(optCharLim)
Dim charOldAgility(optCharLim)
Dim charOldStamina(optCharLim)
Dim charOldToughness(optCharLim)
Dim charOldAttitude(optCharLim)
Dim charOldHappiness(optCharLim)
Dim charNewPopularity(optCharLim)
Dim charNewStrength(optCharLim)
Dim charNewSkill(optCharLim)
Dim charNewAgility(optCharLim)
Dim charNewStamina(optCharLim)
Dim charNewToughness(optCharLim)
Dim charNewAttitude(optCharLim)
Dim charNewHappiness(optCharLim)
;gimmick
Dim charRole(optCharLim) ;1=wrestler, 2=manager, 3=referee
Dim charHeel(optCharLim) 
Dim charStance(optCharLim)
Dim charTaunt(optCharLim,5) ;1-2=general, 3=special, 4=celebration
Dim charTheme(optCharLim)
Dim charThemePitch(optCharLim)
Dim charLight(optCharLim)
Dim charWeapon(optCharLim)
Dim charManager(optCharLim)
Dim charPartner(optCharLim)
;appearance
Dim charPhoto(optCharLim)
Dim charPhotoR(optCharLim)
Dim charPhotoG(optCharLim)
Dim charPhotoB(optCharLim)
Dim charEyes(optCharLim)
Dim charEyeballs(optCharLim)
Dim charEyeShape(optCharLim)
Dim charHeadSize(optCharLim)
Dim charTattoos(optCharLim)
Dim charBaggy(optCharLim,3) ;0=tight, 1=baggy top, 2=baggy pants, 3=all baggy
Dim charHatStyle(optCharLim,3)
Dim charHat(optCharLim,3)
Dim charSpecs(optCharLim,3)
Dim charHairStyle(optCharLim,3)
Dim charHair(optCharLim,3)
Dim charFace(optCharLim,3)
Dim charBeard(optCharLim,3)
Dim charBody(optCharLim,3)
Dim charLeftArm(optCharLim,3)
Dim charRightArm(optCharLim,3)
Dim charLeftForearm(optCharLim,3)
Dim charRightForearm(optCharLim,3)
Dim charLeftHand(optCharLim,3)
Dim charRightHand(optCharLim,3)
Dim charShorts(optCharLim,3)
Dim charLegs(optCharLim,3)
Dim charShins(optCharLim,3)
Dim charShoes(optCharLim,3)
;move set
Dim charAttack(optCharLim,5) ;1=punch, 2=kick, 3=big, 4=running, 5=flying
Dim charCrush(optCharLim,5) ;1/2=stomp, 3=big, 4=running, 5=flying
Dim charMove(optCharLim,15) ;1-12=standard, 13=momentum, 14=turnbuckle, 15=special
Dim charGroundMove(optCharLim,6) ;1-3=head, 4-6=legs
;career status
Dim charFed(optCharLim)
Dim charWorth(optCharLim)
Dim charBank(optCharLim)
Dim charContract(optCharLim)
Dim charSalary(optCharLim)
Dim charClause(optCharLim,3)
Dim charVacant(optCharLim)
Dim charOldVacant(optCharLim)
Dim charArrived(optCharLim)
Dim charWorked(optCharLim)
Dim charExperience(optCharLim,9)
Dim charMatches(optCharLim,9)
Dim charWins(optCharLim,9)
Dim charTitles(optCharLim,9,4) ;1=world, 2=inter, 3=tag, 4=cups
Dim charTrainCourse(optCharLim)
Dim charTrainLevel(optCharLim)
Dim charWeightChange(optCharLim)
Dim charPeaked(optCharLim)
Dim charVariable(optCharLim)
;health status
Dim charHealth(optCharLim)
Dim charOldHealth(optCharLim)
Dim charNewHealth(optCharLim)
Dim charInjured(optCharLim,5)
Dim charOldInjured(optCharLim,5)
Dim charLimb(optCharLim,50)
;relationships
Dim charRelationship(optCharLim,optCharLim) ;-=enemy, +=friend
Dim charRealRelationship(optCharLim,optCharLim) ;-=enemy, +=friend
Dim charTeamHistory(optCharLim,optCharLim) ;1=friends, 2=team mates, 3=management
Dim charHistory(optCharLim,optCharLim) ;0=never met, -1=lost, 1=won
Dim charTradeReaction(optCharLim) ;-=negative, +=positive
;1=changed name, 2=hairstyle, 3=costume, 4=entrance, 5=attacks, 6=moves, 7=gestures, 8=allegiance
;9=used steroids, 10=used painkillers, 11-13=promised title shot, 14=assigned partner, 15=assigned manager
;16=dismissed, 17=imprisoned, 18=ruined career, 19=injured, 20=excused training
Dim charAgreement(optCharLim,50)
;handles
Dim charEvent(optCharLim)
Dim charOpponent(optCharLim) 
Dim charFought(optCharLim)
Dim charBracket(optCharLim)
Dim charSelected(optCharLim)
Dim charRanked(optCharLim)
Dim charUpdated(optCharLim)
;editor
Global page,no_pages,no_boxes
Dim pageList(10)
Global editChar,editFed,editFoc,editModel
Global editScroll,editFilter=1,editPoints
Global editThemeTest,editExamine,editClipboard
Global editGimmickChanges
Dim editPointsY(5)
Global advice$,adviceX,adviceY
Global warning$,warnX,warnY
Global blockData,blockName,blockAllegiance
Global blockHair,blockCostume,blockEntrance,blockWeapon
Global blockMoves,blockAttacks,blockTaunts
Global blockPartner,blockManager
Global no_hatstyles=10,no_specs=8
Global no_hairstyles=48,no_tattoos=9
Global no_lightshows=11
Global hatX#,hatY#,hatZ#
Global hatXA#,hatYA#,hatZA#
Dim costumeLearned(3,200) ;1=body, 2=arms, 3=legs
Global no_relates
Dim relateX(50),relateY(50),relateType(50) ;1=friendship, -1=intense rivalry, -2=old rivalry
;stat pursuits
Dim statLevel(6)
statLevel(1)=30
statLevel(2)=50
statLevel(3)=70
statLevel(4)=85
statLevel(5)=100
statLevel(6)=100

;///////////////////////////////////////////////////////
;---------------------- ATTACKS ------------------------
;///////////////////////////////////////////////////////
.Attacks
;properties
Dim attackList(5) ;1=punch, 2=kick, 3=big, 4=running, 5=flying
Dim attackName$(5,50)
Dim attackAnimSource(5,50)
Dim attackAnimStart(5,50)
Dim attackAnimEnd(5,50)
Dim attackTransition(5,50)
Dim attackSpeed#(5,50)
Dim attackLength(5,50)
Dim attackTravel#(5,50)
Dim attackMomentum(5,50)
Dim attackFall(5,50) ;0=standing flight, 1=lying flight
Dim attackTope(5,50)
Dim attackActive(5,50)
Dim attackImpact(5,50)
Dim attackExpire(5,50)
Dim attackHeight#(5,50)
Dim attackRange#(5,50)
Dim attackPower(5,50)
Dim attackLimb(5,50) ;1=arm, 2=leg
Dim attackWeapon(5,50) ;0=no contact, 1=contact, 10=dust, 11=green mist, 12=fire spit
Dim attackLearned(5,50)
;upper attacks
attackList(1)=11
n=1 : attackName$(1,n)="Short Jab"
attackAnimSource(1,n)=1001 : attackAnimStart(1,n)=640 : attackAnimEnd(1,n)=705
attackTransition(1,n)=12 : attackSpeed#(1,n)=4.0 : attackLength(1,n)=attackTransition(1,n)+62
attackTravel#(1,n)=0.5 : attackMomentum(1,n)=attackTransition(1,n)+45 : attackFall(1,n)=0
attackActive(1,n)=attackTransition(1,n)+6 : attackImpact(1,n)=attackTransition(1,n)+18 : attackExpire(1,n)=attackTransition(1,n)+35
attackRange#(1,n)=16 : attackHeight#(1,n)=28 : attackPower(1,n)=100 : attackLimb(1,n)=1 : attackWeapon(1,n)=1
n=2 : attackName$(1,n)="Straight Jab"
attackAnimSource(1,n)=1006 : attackAnimStart(1,n)=235 : attackAnimEnd(1,n)=300
attackTransition(1,n)=12 : attackSpeed#(1,n)=4.25 : attackLength(1,n)=attackTransition(1,n)+62
attackTravel#(1,n)=0.5 : attackMomentum(1,n)=attackTransition(1,n)+45 : attackFall(1,n)=0
attackActive(1,n)=attackTransition(1,n)+5 : attackImpact(1,n)=attackTransition(1,n)+20 : attackExpire(1,n)=attackTransition(1,n)+30
attackRange#(1,n)=18 : attackHeight#(1,n)=28 : attackPower(1,n)=100 : attackLimb(1,n)=1 : attackWeapon(1,n)=1
n=3 : attackName$(1,n)="Straight Punch"
attackAnimSource(1,n)=1007 : attackAnimStart(1,n)=990 : attackAnimEnd(1,n)=1050
attackTransition(1,n)=15 : attackSpeed#(1,n)=3.0 : attackLength(1,n)=attackTransition(1,n)+60
attackTravel#(1,n)=0.5 : attackMomentum(1,n)=attackTransition(1,n)+45 : attackFall(1,n)=0
attackActive(1,n)=attackTransition(1,n)+8 : attackImpact(1,n)=attackTransition(1,n)+13 : attackExpire(1,n)=attackTransition(1,n)+26
attackRange#(1,n)=19 : attackHeight#(1,n)=28 : attackPower(1,n)=100 : attackLimb(1,n)=1 : attackWeapon(1,n)=1
n=4 : attackName$(1,n)="Short Hook"
attackAnimSource(1,n)=1006 : attackAnimStart(1,n)=400 : attackAnimEnd(1,n)=480
attackTransition(1,n)=15 : attackSpeed#(1,n)=4.0 : attackLength(1,n)=attackTransition(1,n)+70
attackTravel#(1,n)=0.5 : attackMomentum(1,n)=attackTransition(1,n)+50 : attackFall(1,n)=0
attackActive(1,n)=attackTransition(1,n)+15 : attackImpact(1,n)=attackTransition(1,n)+22 : attackExpire(1,n)=attackTransition(1,n)+35
attackRange#(1,n)=15 : attackHeight#(1,n)=28 : attackPower(1,n)=100 : attackLimb(1,n)=1 : attackWeapon(1,n)=1
n=5 : attackName$(1,n)="Long Hook"
attackAnimSource(1,n)=1001 : attackAnimStart(1,n)=820 : attackAnimEnd(1,n)=885
attackTransition(1,n)=15 : attackSpeed#(1,n)=4.0 : attackLength(1,n)=attackTransition(1,n)+65
attackTravel#(1,n)=0.5 : attackMomentum(1,n)=attackTransition(1,n)+45 : attackFall(1,n)=0
attackActive(1,n)=attackTransition(1,n)+9 : attackImpact(1,n)=attackTransition(1,n)+14 : attackExpire(1,n)=attackTransition(1,n)+30
attackRange#(1,n)=17 : attackHeight#(1,n)=28 : attackPower(1,n)=100 : attackLimb(1,n)=1 : attackWeapon(1,n)=1
n=6 : attackName$(1,n)="Quick Uppercut"
attackAnimSource(1,n)=1007 : attackAnimStart(1,n)=1390 : attackAnimEnd(1,n)=1465
attackTransition(1,n)=15 : attackSpeed#(1,n)=3.1 : attackLength(1,n)=attackTransition(1,n)+55
attackTravel#(1,n)=0.5 : attackMomentum(1,n)=attackTransition(1,n)+45 : attackFall(1,n)=0
attackActive(1,n)=attackTransition(1,n)+10 : attackImpact(1,n)=attackTransition(1,n)+15 : attackExpire(1,n)=attackTransition(1,n)+30
attackRange#(1,n)=15 : attackHeight#(1,n)=28 : attackPower(1,n)=100 : attackLimb(1,n)=1 : attackWeapon(1,n)=1
n=7 : attackName$(1,n)="Big Uppercut"
attackAnimSource(1,n)=1001 : attackAnimStart(1,n)=1540 : attackAnimEnd(1,n)=1615
attackTransition(1,n)=15 : attackSpeed#(1,n)=3.6 : attackLength(1,n)=attackTransition(1,n)+65
attackTravel#(1,n)=0.5 : attackMomentum(1,n)=attackTransition(1,n)+55 : attackFall(1,n)=0
attackActive(1,n)=attackTransition(1,n)+10 : attackImpact(1,n)=attackTransition(1,n)+20 : attackExpire(1,n)=attackTransition(1,n)+30
attackRange#(1,n)=16 : attackHeight#(1,n)=28 : attackPower(1,n)=100 : attackLimb(1,n)=1 : attackWeapon(1,n)=1
n=8 : attackName$(1,n)="Hammer Blow"
attackAnimSource(1,n)=1008 : attackAnimStart(1,n)=245 : attackAnimEnd(1,n)=300
attackTransition(1,n)=15 : attackSpeed#(1,n)=3.5 : attackLength(1,n)=attackTransition(1,n)+52
attackTravel#(1,n)=0.5 : attackMomentum(1,n)=attackTransition(1,n)+40 : attackFall(1,n)=0
attackActive(1,n)=attackTransition(1,n)+10 : attackImpact(1,n)=attackTransition(1,n)+15 : attackExpire(1,n)=attackTransition(1,n)+30
attackRange#(1,n)=15 : attackHeight#(1,n)=28 : attackPower(1,n)=100 : attackLimb(1,n)=1 : attackWeapon(1,n)=1
n=9 : attackName$(1,n)="Slap"
attackAnimSource(1,n)=1011 : attackAnimStart(1,n)=845 : attackAnimEnd(1,n)=900
attackTransition(1,n)=15 : attackSpeed#(1,n)=3.25 : attackLength(1,n)=attackTransition(1,n)+50
attackTravel#(1,n)=0.3 : attackMomentum(1,n)=attackTransition(1,n)+40 : attackFall(1,n)=0
attackActive(1,n)=attackTransition(1,n)+10 : attackImpact(1,n)=attackTransition(1,n)+15 : attackExpire(1,n)=attackTransition(1,n)+25
attackRange#(1,n)=16 : attackHeight#(1,n)=29 : attackPower(1,n)=100 : attackLimb(1,n)=1 : attackWeapon(1,n)=1
n=10 : attackName$(1,n)="Chop"
attackAnimSource(1,n)=1011 : attackAnimStart(1,n)=925 : attackAnimEnd(1,n)=980
attackTransition(1,n)=20 : attackSpeed#(1,n)=3.25 : attackLength(1,n)=attackTransition(1,n)+50
attackTravel#(1,n)=0.6 : attackMomentum(1,n)=attackTransition(1,n)+40 : attackFall(1,n)=0
attackActive(1,n)=attackTransition(1,n)+5 : attackImpact(1,n)=attackTransition(1,n)+14 : attackExpire(1,n)=attackTransition(1,n)+21
attackRange#(1,n)=15 : attackHeight#(1,n)=27 : attackPower(1,n)=100 : attackLimb(1,n)=7 : attackWeapon(1,n)=1
n=11 : attackName$(1,n)="High Kick"
attackAnimSource(1,n)=1011 : attackAnimStart(1,n)=1085 : attackAnimEnd(1,n)=1140
attackTransition(1,n)=15 : attackSpeed#(1,n)=3.25 : attackLength(1,n)=attackTransition(1,n)+50
attackTravel#(1,n)=0.4 : attackMomentum(1,n)=attackTransition(1,n)+40 : attackFall(1,n)=0
attackActive(1,n)=attackTransition(1,n) : attackImpact(1,n)=attackTransition(1,n)+10 : attackExpire(1,n)=attackTransition(1,n)+25
attackRange#(1,n)=19 : attackHeight#(1,n)=28 : attackPower(1,n)=125 : attackLimb(1,n)=43 : attackWeapon(1,n)=0
;lower attacks
attackList(2)=6
n=1 : attackName$(2,n)="Jab"
attackAnimSource(2,n)=1001 : attackAnimStart(2,n)=1085 : attackAnimEnd(2,n)=1150
attackTransition(2,n)=10 : attackSpeed#(2,n)=3.5 : attackLength(2,n)=attackTransition(2,n)+55
attackTravel#(2,n)=0.4 : attackMomentum(2,n)=attackTransition(2,n)+40 : attackFall(2,n)=0
attackActive(2,n)=attackTransition(2,n)+8 : attackImpact(2,n)=attackTransition(2,n)+20 : attackExpire(2,n)=attackTransition(2,n)+35
attackRange#(2,n)=15 : attackHeight#(2,n)=17 : attackPower(2,n)=100 : attackLimb(2,n)=1 : attackWeapon(2,n)=1
n=2 : attackName$(2,n)="Uppercut"
attackAnimSource(2,n)=1007 : attackAnimStart(2,n)=1305 : attackAnimEnd(2,n)=1365
attackTransition(2,n)=15 : attackSpeed#(2,n)=2.5 : attackLength(2,n)=attackTransition(2,n)+45
attackTravel#(2,n)=0.4 : attackMomentum(2,n)=attackTransition(2,n)+40 : attackFall(2,n)=0
attackActive(2,n)=attackTransition(2,n)+5 : attackImpact(2,n)=attackTransition(2,n)+14 : attackExpire(2,n)=attackTransition(2,n)+30
attackRange#(2,n)=15 : attackHeight#(2,n)=16 : attackPower(2,n)=125 : attackLimb(2,n)=1 : attackWeapon(2,n)=1
n=3 : attackName$(2,n)="Hook"
attackAnimSource(2,n)=1001 : attackAnimStart(2,n)=1350 : attackAnimEnd(2,n)=1415
attackTransition(2,n)=15 : attackSpeed#(2,n)=3.5 : attackLength(2,n)=attackTransition(2,n)+60
attackTravel#(2,n)=0.4 : attackMomentum(2,n)=attackTransition(2,n)+45 : attackFall(2,n)=0
attackActive(2,n)=attackTransition(2,n)+8 : attackImpact(2,n)=attackTransition(2,n)+20 : attackExpire(2,n)=attackTransition(2,n)+30
attackRange#(2,n)=16 : attackHeight#(2,n)=16 : attackPower(2,n)=125 : attackLimb(2,n)=1 : attackWeapon(2,n)=1
n=4 : attackName$(2,n)="Straight Punch"
attackAnimSource(2,n)=1006 : attackAnimStart(2,n)=595 : attackAnimEnd(2,n)=675
attackTransition(2,n)=15 : attackSpeed#(2,n)=3.8 : attackLength(2,n)=attackTransition(2,n)+75
attackTravel#(2,n)=0.25 : attackMomentum(2,n)=attackTransition(2,n)+55 : attackFall(2,n)=0
attackActive(2,n)=attackTransition(2,n)+10 : attackImpact(2,n)=attackTransition(2,n)+25 : attackExpire(2,n)=attackTransition(2,n)+45
attackRange#(2,n)=19 : attackHeight#(2,n)=16 : attackPower(2,n)=100 : attackLimb(2,n)=1 : attackWeapon(2,n)=1
n=5 : attackName$(2,n)="Kick"
attackAnimSource(2,n)=1006 : attackAnimStart(2,n)=1285 : attackAnimEnd(2,n)=1345
attackTransition(2,n)=15 : attackSpeed#(2,n)=3.0 : attackLength(2,n)=attackTransition(2,n)+60
attackTravel#(2,n)=0.4 : attackMomentum(2,n)=attackTransition(2,n)+45 : attackFall(2,n)=0
attackActive(2,n)=attackTransition(2,n)+8 : attackImpact(2,n)=attackTransition(2,n)+15 : attackExpire(2,n)=attackTransition(2,n)+30
attackRange#(2,n)=19 : attackHeight#(2,n)=15 : attackPower(2,n)=150 : attackLimb(2,n)=43 : attackWeapon(2,n)=0
n=6 : attackName$(2,n)="Side Kick"
attackAnimSource(2,n)=1011 : attackAnimStart(2,n)=1005 : attackAnimEnd(2,n)=1060
attackTransition(2,n)=20 : attackSpeed#(2,n)=3.5 : attackLength(2,n)=attackTransition(2,n)+50
attackTravel#(2,n)=0.4 : attackMomentum(2,n)=attackTransition(2,n)+35 : attackFall(2,n)=0
attackActive(2,n)=attackTransition(2,n)-5 : attackImpact(2,n)=attackTransition(2,n)+10 : attackExpire(2,n)=attackTransition(2,n)+23
attackRange#(2,n)=22 : attackHeight#(2,n)=18 : attackPower(2,n)=125 : attackLimb(2,n)=40 : attackWeapon(2,n)=0
;big attacks
n=1 : attackName$(3,n)="Double Axe-Handle"
attackAnimSource(3,n)=1006 : attackAnimStart(3,n)=1165 : attackAnimEnd(3,n)=1260
attackTransition(3,n)=30 : attackSpeed#(3,n)=3.5 : attackLength(3,n)=attackTransition(3,n)+95
attackTravel#(3,n)=0.3 : attackMomentum(3,n)=attackTransition(3,n)+65 : attackFall(3,n)=0
attackActive(3,n)=attackTransition(3,n)+12 : attackImpact(3,n)=attackTransition(3,n)+18 : attackExpire(3,n)=attackTransition(3,n)+35
attackRange#(3,n)=15 : attackHeight#(3,n)=26 : attackPower(3,n)=325 : attackLimb(3,n)=1 : attackWeapon(3,n)=1
n=2 : attackName$(3,n)="Hammer Blow"
attackAnimSource(3,n)=1007 : attackAnimStart(3,n)=505 : attackAnimEnd(3,n)=565
attackTransition(3,n)=25 : attackSpeed#(3,n)=2.75 : attackLength(3,n)=attackTransition(3,n)+60
attackTravel#(3,n)=0.5 : attackMomentum(3,n)=attackTransition(3,n)+50 : attackFall(3,n)=0
attackActive(3,n)=attackTransition(3,n)+13 : attackImpact(3,n)=attackTransition(3,n)+18 : attackExpire(3,n)=attackTransition(3,n)+30
attackRange#(3,n)=18 : attackHeight#(3,n)=28 : attackPower(3,n)=300 : attackLimb(3,n)=1 : attackWeapon(3,n)=1
n=3 : attackName$(3,n)="Backhand"
attackAnimSource(3,n)=1007 : attackAnimStart(3,n)=830 : attackAnimEnd(3,n)=895
attackTransition(3,n)=20 : attackSpeed#(3,n)=2.5 : attackLength(3,n)=attackTransition(3,n)+65
attackTravel#(3,n)=0.5 : attackMomentum(3,n)=attackTransition(3,n)+50 : attackFall(3,n)=0
attackActive(3,n)=attackTransition(3,n)+5 : attackImpact(3,n)=attackTransition(3,n)+10 : attackExpire(3,n)=attackTransition(3,n)+15
attackRange#(3,n)=15 : attackHeight#(3,n)=28 : attackPower(3,n)=300 : attackLimb(3,n)=1 : attackWeapon(3,n)=1
n=4 : attackName$(3,n)="Forearm Strike"
attackAnimSource(3,n)=1006 : attackAnimStart(3,n)=2975 : attackAnimEnd(3,n)=3040
attackTransition(3,n)=30 : attackSpeed#(3,n)=3.25 : attackLength(3,n)=attackTransition(3,n)+65
attackTravel#(3,n)=0.75 : attackMomentum(3,n)=attackLength(3,n) : attackFall(3,n)=0
attackActive(3,n)=attackTransition(3,n)+10 : attackImpact(3,n)=attackTransition(3,n)+20 : attackExpire(3,n)=attackTransition(3,n)+40
attackRange#(3,n)=13 : attackHeight#(3,n)=26 : attackPower(3,n)=300 : attackLimb(3,n)=1 : attackWeapon(3,n)=1
n=5 : attackName$(3,n)="Clothesline"
attackAnimSource(3,n)=1006 : attackAnimStart(3,n)=1370 : attackAnimEnd(3,n)=1435
attackTransition(3,n)=30 : attackSpeed#(3,n)=3.5 : attackLength(3,n)=attackTransition(3,n)+65
attackTravel#(3,n)=0.75 : attackMomentum(3,n)=attackLength(3,n) : attackFall(3,n)=0
attackActive(3,n)=attackTransition(3,n)+10 : attackImpact(3,n)=attackTransition(3,n)+20 : attackExpire(3,n)=attackTransition(3,n)+40
attackRange#(3,n)=13 : attackHeight#(3,n)=26 : attackPower(3,n)=300 : attackLimb(3,n)=1 : attackWeapon(3,n)=1
n=6 : attackName$(3,n)="Big Hook"
attackAnimSource(3,n)=1008 : attackAnimStart(3,n)=880 : attackAnimEnd(3,n)=950
attackTransition(3,n)=30 : attackSpeed#(3,n)=2.75 : attackLength(3,n)=attackTransition(3,n)+65
attackTravel#(3,n)=0.5 : attackMomentum(3,n)=attackTransition(3,n)+50 : attackFall(3,n)=0
attackActive(3,n)=attackTransition(3,n)+12 : attackImpact(3,n)=attackTransition(3,n)+18 : attackExpire(3,n)=attackTransition(3,n)+30
attackRange#(3,n)=16 : attackHeight#(3,n)=28 : attackPower(3,n)=300 : attackLimb(3,n)=1 : attackWeapon(3,n)=1
n=7 : attackName$(3,n)="Big Jab"
attackAnimSource(3,n)=1008 : attackAnimStart(3,n)=1230 : attackAnimEnd(3,n)=1310
attackTransition(3,n)=30 : attackSpeed#(3,n)=3.0 : attackLength(3,n)=attackTransition(3,n)+70
attackTravel#(3,n)=0.3 : attackMomentum(3,n)=attackTransition(3,n)+60 : attackFall(3,n)=0
attackActive(3,n)=attackTransition(3,n)+10 : attackImpact(3,n)=attackTransition(3,n)+25 : attackExpire(3,n)=attackTransition(3,n)+40
attackRange#(3,n)=19 : attackHeight#(3,n)=28 : attackPower(3,n)=300 : attackLimb(3,n)=1 : attackWeapon(3,n)=1
n=8 : attackName$(3,n)="Spinning Punch"
attackAnimSource(3,n)=1007 : attackAnimStart(3,n)=290 : attackAnimEnd(3,n)=410
attackTransition(3,n)=10 : attackSpeed#(3,n)=3.75 : attackLength(3,n)=attackTransition(3,n)+120
attackTravel#(3,n)=0.4 : attackMomentum(3,n)=attackTransition(3,n)+80 : attackFall(3,n)=0
attackActive(3,n)=attackTransition(3,n)+45 : attackImpact(3,n)=attackTransition(3,n)+55 : attackExpire(3,n)=attackTransition(3,n)+67
attackRange#(3,n)=17 : attackHeight#(3,n)=28 : attackPower(3,n)=350 : attackLimb(3,n)=1 : attackWeapon(3,n)=1
n=9 : attackName$(3,n)="Spinning Forearm"
attackAnimSource(3,n)=1007 : attackAnimStart(3,n)=2225 : attackAnimEnd(3,n)=2345
attackTransition(3,n)=10 : attackSpeed#(3,n)=3.75 : attackLength(3,n)=attackTransition(3,n)+120
attackTravel#(3,n)=0.4 : attackMomentum(3,n)=attackTransition(3,n)+80 : attackFall(3,n)=0
attackActive(3,n)=attackTransition(3,n)+45 : attackImpact(3,n)=attackTransition(3,n)+57 : attackExpire(3,n)=attackTransition(3,n)+70
attackRange#(3,n)=13 : attackHeight#(3,n)=26 : attackPower(3,n)=350 : attackLimb(3,n)=1 : attackWeapon(3,n)=1
n=10 : attackName$(3,n)="Diving Clothesline"
attackAnimSource(3,n)=1010 : attackAnimStart(3,n)=1575 : attackAnimEnd(3,n)=1685
attackTransition(3,n)=20 : attackSpeed#(3,n)=2.9 : attackLength(3,n)=attackTransition(3,n)+105
attackTravel#(3,n)=0.75 : attackMomentum(3,n)=attackTransition(3,n)+65 : attackFall(3,n)=attackTransition(3,n)+40
attackActive(3,n)=attackTransition(3,n)+10 : attackImpact(3,n)=attackTransition(3,n)+35 : attackExpire(3,n)=attackTransition(3,n)+35
attackRange#(3,n)=13 : attackHeight#(3,n)=26 : attackPower(3,n)=300 : attackLimb(3,n)=1 : attackWeapon(3,n)=1
n=11 : attackName$(3,n)="Shoulder Tackle"
attackAnimSource(3,n)=1010 : attackAnimStart(3,n)=2345 : attackAnimEnd(3,n)=2455
attackTransition(3,n)=20 : attackSpeed#(3,n)=2.9 : attackLength(3,n)=attackTransition(3,n)+105
attackTravel#(3,n)=0.75 : attackMomentum(3,n)=attackTransition(3,n)+65 : attackFall(3,n)=attackTransition(3,n)+40
attackActive(3,n)=attackTransition(3,n)+15 : attackImpact(3,n)=attackTransition(3,n)+35 : attackExpire(3,n)=attackTransition(3,n)+35
attackRange#(3,n)=12 : attackHeight#(3,n)=26 : attackPower(3,n)=300 : attackLimb(3,n)=1 : attackWeapon(3,n)=1
n=12 : attackName$(3,n)="Dropkick"
attackAnimSource(3,n)=1010 : attackAnimStart(3,n)=185 : attackAnimEnd(3,n)=345
attackTransition(3,n)=20 : attackSpeed#(3,n)=4.0 : attackLength(3,n)=attackTransition(3,n)+160
attackTravel#(3,n)=0.4 : attackMomentum(3,n)=attackTransition(3,n)+100 : attackFall(3,n)=attackTransition(3,n)+80
attackActive(3,n)=attackTransition(3,n)+30 : attackImpact(3,n)=attackTransition(3,n)+50 : attackExpire(3,n)=attackTransition(3,n)+60
attackRange#(3,n)=17 : attackHeight#(3,n)=26 : attackPower(3,n)=325 : attackLimb(3,n)=43 : attackWeapon(3,n)=0
n=13 : attackName$(3,n)="Big Boot"
attackAnimSource(3,n)=1011 : attackAnimStart(3,n)=1285 : attackAnimEnd(3,n)=1370
attackTransition(3,n)=20 : attackSpeed#(3,n)=3.0 : attackLength(3,n)=attackTransition(3,n)+80
attackTravel#(3,n)=0.5 : attackMomentum(3,n)=attackLength(3,n)+70 : attackFall(3,n)=0
attackActive(3,n)=attackTransition(3,n)+10 : attackImpact(3,n)=attackTransition(3,n)+35 : attackExpire(3,n)=attackTransition(3,n)+45
attackRange#(3,n)=17 : attackHeight#(3,n)=28 : attackPower(3,n)=325 : attackLimb(3,n)=43 : attackWeapon(3,n)=0
n=14 : attackName$(3,n)="Shuffle Kick"
attackAnimSource(3,n)=1011 : attackAnimStart(3,n)=1165 : attackAnimEnd(3,n)=1260
attackTransition(3,n)=20 : attackSpeed#(3,n)=3.75 : attackLength(3,n)=attackTransition(3,n)+90
attackTravel#(3,n)=0.75 : attackMomentum(3,n)=attackLength(3,n)+60 : attackFall(3,n)=0
attackActive(3,n)=attackTransition(3,n)+10 : attackImpact(3,n)=attackTransition(3,n)+30 : attackExpire(3,n)=attackTransition(3,n)+50
attackRange#(3,n)=19 : attackHeight#(3,n)=28 : attackPower(3,n)=325 : attackLimb(3,n)=40 : attackWeapon(3,n)=0
n=15 : attackName$(3,n)="Flying Kick"
attackAnimSource(3,n)=1011 : attackAnimStart(3,n)=1520 : attackAnimEnd(3,n)=1625
attackTransition(3,n)=20 : attackSpeed#(3,n)=3.5 : attackLength(3,n)=attackTransition(3,n)+100
attackTravel#(3,n)=0.75 : attackMomentum(3,n)=attackTransition(3,n)+75 : attackFall(3,n)=attackTransition(3,n)+50
attackActive(3,n)=attackTransition(3,n)+13 : attackImpact(3,n)=attackTransition(3,n)+28 : attackExpire(3,n)=attackTransition(3,n)+50
attackRange#(3,n)=18 : attackHeight#(3,n)=26 : attackPower(3,n)=300 : attackLimb(3,n)=43 : attackWeapon(3,n)=0
n=16 : attackName$(3,n)="Spinning Kick"
attackAnimSource(3,n)=1011 : attackAnimStart(3,n)=1395 : attackAnimEnd(3,n)=1495
attackTransition(3,n)=20 : attackSpeed#(3,n)=3.25 : attackLength(3,n)=attackTransition(3,n)+95
attackTravel#(3,n)=0.5 : attackMomentum(3,n)=attackTransition(3,n)+70 : attackFall(3,n)=attackTransition(3,n)+50
attackActive(3,n)=attackTransition(3,n)+20 : attackImpact(3,n)=attackTransition(3,n)+35 : attackExpire(3,n)=attackTransition(3,n)+45
attackRange#(3,n)=18 : attackHeight#(3,n)=28 : attackPower(3,n)=325 : attackLimb(3,n)=43 : attackWeapon(3,n)=0
n=17 : attackName$(3,n)="Cartwheel Kick"
attackAnimSource(3,n)=1011 : attackAnimStart(3,n)=95 : attackAnimEnd(3,n)=225
attackTransition(3,n)=20 : attackSpeed#(3,n)=3.0 : attackLength(3,n)=attackTransition(3,n)+130
attackTravel#(3,n)=0.5 : attackMomentum(3,n)=attackTransition(3,n)+105 : attackFall(3,n)=attackTransition(3,n)+55
attackActive(3,n)=attackTransition(3,n)+25 : attackImpact(3,n)=attackTransition(3,n)+40 : attackExpire(3,n)=attackTransition(3,n)+55
attackRange#(3,n)=17 : attackHeight#(3,n)=30 : attackPower(3,n)=325 : attackLimb(3,n)=43 : attackWeapon(3,n)=0
n=18 : attackName$(3,n)="Dust Throw"
attackAnimSource(3,n)=1011 : attackAnimStart(3,n)=520 : attackAnimEnd(3,n)=590
attackTransition(3,n)=30 : attackSpeed#(3,n)=2.75 : attackLength(3,n)=attackTransition(3,n)+65
attackTravel#(3,n)=0.1 : attackMomentum(3,n)=attackTransition(3,n)+40 : attackFall(3,n)=0
attackActive(3,n)=0 : attackImpact(3,n)=0 : attackExpire(3,n)=0
attackRange#(3,n)=0 : attackHeight#(3,n)=0 : attackPower(3,n)=0 : attackLimb(3,n)=0 : attackWeapon(3,n)=10
n=19 : attackName$(3,n)="Green Mist"
attackAnimSource(3,n)=1011 : attackAnimStart(3,n)=420 : attackAnimEnd(3,n)=490
attackTransition(3,n)=30 : attackSpeed#(3,n)=2.75 : attackLength(3,n)=attackTransition(3,n)+65
attackTravel#(3,n)=0.1 : attackMomentum(3,n)=attackTransition(3,n)+40 : attackFall(3,n)=0
attackActive(3,n)=0 : attackImpact(3,n)=0 : attackExpire(3,n)=0
attackRange#(3,n)=0 : attackHeight#(3,n)=0 : attackPower(3,n)=0 : attackLimb(3,n)=0 : attackWeapon(3,n)=11
n=20 : attackName$(3,n)="Fire Breath"
attackAnimSource(3,n)=1011 : attackAnimStart(3,n)=420 : attackAnimEnd(3,n)=490
attackTransition(3,n)=30 : attackSpeed#(3,n)=2.75 : attackLength(3,n)=attackTransition(3,n)+65
attackTravel#(3,n)=0.1 : attackMomentum(3,n)=attackTransition(3,n)+40 : attackFall(3,n)=0
attackActive(3,n)=0 : attackImpact(3,n)=0 : attackExpire(3,n)=0
attackRange#(3,n)=0 : attackHeight#(3,n)=0 : attackPower(3,n)=0 : attackLimb(3,n)=0 : attackWeapon(3,n)=12
n=21 : attackName$(3,n)="High Knee"
attackAnimSource(3,n)=1011 : attackAnimStart(3,n)=2630 : attackAnimEnd(3,n)=2695
attackTransition(3,n)=20 : attackSpeed#(3,n)=2.5 : attackLength(3,n)=attackTransition(3,n)+65
attackTravel#(3,n)=0.75 : attackMomentum(3,n)=attackTransition(3,n)+50 : attackFall(3,n)=attackTransition(3,n)+40
attackActive(3,n)=attackTransition(3,n)+20 : attackImpact(3,n)=attackTransition(3,n)+25 : attackExpire(3,n)=attackTransition(3,n)+35
attackRange#(3,n)=12 : attackHeight#(3,n)=28 : attackPower(3,n)=350 : attackLimb(3,n)=42 : attackWeapon(3,n)=0
n=22 : attackName$(3,n)="Brogue Kick"
attackAnimSource(3,n)=1011 : attackAnimStart(3,n)=2720 : attackAnimEnd(3,n)=2790
attackTransition(3,n)=20 : attackSpeed#(3,n)=2.5 : attackLength(3,n)=attackTransition(3,n)+70
attackTravel#(3,n)=0.5 : attackMomentum(3,n)=attackTransition(3,n)+50 : attackFall(3,n)=attackTransition(3,n)+40
attackActive(3,n)=attackTransition(3,n)+25 : attackImpact(3,n)=attackTransition(3,n)+30 : attackExpire(3,n)=attackTransition(3,n)+35
attackRange#(3,n)=16 : attackHeight#(3,n)=28 : attackPower(3,n)=325 : attackLimb(3,n)=43 : attackWeapon(3,n)=0

attackList(3)=n
;running attacks
n=1 : CopyAttack(3,4,4,n) ;forearm strike copy
n=2 : CopyAttack(3,5,4,n) ;standing clothesline copy
n=3 : CopyAttack(3,10,4,n) ;diving clothesline copy
n=4 : CopyAttack(3,11,4,n) ;shoulder tackle copy
n=5 : CopyAttack(3,12,4,n) ;dropkick copy
n=6 : CopyAttack(3,14,4,n) ;shuffle kick copy
n=7 : CopyAttack(3,16,4,n) ;spinning kick copy
n=8 : CopyAttack(3,15,4,n) ;flying kick copy
n=9 : CopyAttack(3,17,4,n) ;cartwheel kick copy
n=10 : CopyAttack(3,21,4,n) ;high knee copy
n=11 : CopyAttack(3,22,4,n) ;brogue kick copy
attackList(4)=n
;flying attacks
attackList(5)=4
n=1 : attackName$(5,n)="Double Axe-Handle"
attackAnimSource(5,n)=1006 : attackAnimStart(5,n)=2225 : attackAnimEnd(5,n)=2340
attackTransition(5,n)=20 : attackSpeed#(5,n)=3.0 : attackLength(5,n)=attackTransition(5,n)+115
attackTravel#(5,n)=10 : attackMomentum(5,n)=attackLength(4,n) : attackFall(5,n)=0 : attackTope(5,n)=0
attackActive(5,n)=attackTransition(5,n)+45 : attackImpact(5,n)=attackTransition(5,n)+60 : attackExpire(5,n)=attackTransition(5,n)+75
attackRange#(5,n)=15 : attackHeight#(5,n)=12 : attackPower(5,n)=600 : attackLimb(5,n)=1 : attackWeapon(5,n)=1
n=2 : attackName$(5,n)="Flying Clothesline"
attackAnimSource(5,n)=1010 : attackAnimStart(5,n)=1710 : attackAnimEnd(5,n)=1855
attackTransition(5,n)=20 : attackSpeed#(5,n)=3.0 : attackLength(5,n)=attackTransition(5,n)+140
attackTravel#(5,n)=15 : attackMomentum(5,n)=attackTransition(5,n)+130 : attackFall(5,n)=1 : attackTope(5,n)=1
attackActive(5,n)=attackTransition(5,n)+25 : attackImpact(5,n)=attackTransition(5,n)+40 : attackExpire(5,n)=attackTransition(5,n)+55
attackRange#(5,n)=15 : attackHeight#(5,n)=26 : attackPower(5,n)=600 : attackLimb(5,n)=1 : attackWeapon(5,n)=1
n=3 : attackName$(5,n)="Missile Dropkick"
attackAnimSource(5,n)=1010 : attackAnimStart(5,n)=700 : attackAnimEnd(5,n)=840
attackTransition(5,n)=20 : attackSpeed#(5,n)=3.0 : attackLength(5,n)=attackTransition(5,n)+135
attackTravel#(5,n)=15 : attackMomentum(5,n)=attackTransition(5,n)+110 : attackFall(5,n)=1 : attackTope(5,n)=1
attackActive(5,n)=attackTransition(5,n)+28 : attackImpact(5,n)=attackTransition(5,n)+40 : attackExpire(5,n)=attackTransition(5,n)+60
attackRange#(5,n)=18 : attackHeight#(5,n)=26 : attackPower(5,n)=600 : attackLimb(5,n)=43 : attackWeapon(5,n)=0
n=4 : attackName$(5,n)="Cartwheel Kick"
attackAnimSource(5,n)=1011 : attackAnimStart(5,n)=250 : attackAnimEnd(5,n)=385
attackTransition(5,n)=20 : attackSpeed#(5,n)=3.0 : attackLength(5,n)=attackTransition(5,n)+135
attackTravel#(5,n)=15 : attackMomentum(5,n)=attackTransition(5,n)+110 : attackFall(5,n)=1 : attackTope(5,n)=1
attackActive(5,n)=attackTransition(5,n)+25 : attackImpact(5,n)=attackTransition(5,n)+45 : attackExpire(5,n)=attackTransition(5,n)+60
attackRange#(5,n)=17 : attackHeight#(5,n)=20 : attackPower(5,n)=600 : attackLimb(5,n)=43 : attackWeapon(5,n)=0

;COPY ATTACK
Function CopyAttack(sourceType,sourceID,targetType,targetID)
 attackName$(targetType,targetID)=attackName$(sourceType,sourceID)
 attackAnimSource(targetType,targetID)=attackAnimSource(sourceType,sourceID)
 attackAnimStart(targetType,targetID)=attackAnimStart(sourceType,sourceID)
 attackAnimEnd(targetType,targetID)=attackAnimEnd(sourceType,sourceID)
 attackTransition(targetType,targetID)=attackTransition(sourceType,sourceID)
 attackSpeed#(targetType,targetID)=attackSpeed#(sourceType,sourceID)
 attackLength(targetType,targetID)=attackLength(sourceType,sourceID)
 attackTravel#(targetType,targetID)=attackTravel#(sourceType,sourceID)
 attackMomentum(targetType,targetID)=attackMomentum(sourceType,sourceID)
 attackFall(targetType,targetID)=attackFall(sourceType,sourceID)
 attackActive(targetType,targetID)=attackActive(sourceType,sourceID)
 attackImpact(targetType,targetID)=attackImpact(sourceType,sourceID)
 attackExpire(targetType,targetID)=attackExpire(sourceType,sourceID)
 attackRange#(targetType,targetID)=attackRange#(sourceType,sourceID)
 attackHeight#(targetType,targetID)=attackHeight#(sourceType,sourceID)
 attackPower(targetType,targetID)=attackPower(sourceType,sourceID)
 If sourceType<targetType Then attackPower(targetType,targetID)=PercentOf#(attackPower(targetType,targetID),125)
 attackLimb(targetType,targetID)=attackLimb(sourceType,sourceID)
 attackWeapon(targetType,targetID)=attackWeapon(sourceType,sourceID)
End Function

;///////////////////////////////////////////////////////
;---------------------- CRUSHES ------------------------
;///////////////////////////////////////////////////////
.Crushes
;properties
Dim crushList(5) ;1/2=stomp, 3=big, 4=running, 5=flying
Dim crushName$(5,50)
Dim crushAnimSource(5,50)
Dim crushAnimStart(5,50)
Dim crushAnimEnd(5,50)
Dim crushTransition(5,50)
Dim crushSpeed#(5,50)
Dim crushLength(5,50)
Dim crushTravel#(5,50)
Dim crushMomentum(5,50)
;0=standing, 1=lying on back forwards (swanton), 2=lying on front forwards (splash), 3=hands & knees 270 turn (leg drop)
;4=kneeling (knee drop), 5=lying on back backwards (elbow drop), 6=lying on front backwards (moonsault)
Dim crushFall(5,50) 
Dim crushTope(5,50) 
Dim crushImpactHigh(5,50)
Dim crushImpactLow(5,50)
Dim crushRange#(5,50)
Dim crushPower(5,50)
Dim crushLimb(5,50) ;1=arm, =>36=legs
Dim crushWeapon(5,50)
Dim crushLearned(5,50)
;stomps
crushList(2)=3
n=1 : crushName$(2,n)="Stomp"
crushAnimSource(2,n)=1006 : crushAnimStart(2,n)=1460 : crushAnimEnd(2,n)=1505
crushTransition(2,n)=20 : crushSpeed#(2,n)=3.0 : crushLength(2,n)=crushTransition(2,n)+40
crushTravel#(2,n)=0.5 : crushMomentum(2,n)=crushTransition(2,n)+25 : crushFall(2,n)=0
crushImpactHigh(2,n)=crushTransition(2,n) : crushImpactLow(2,n)=crushTransition(2,n)+5
crushRange#(2,n)=8 : crushPower(2,n)=100 : crushLimb(2,n)=44 : crushWeapon(2,n)=0
n=2 : crushName$(2,n)="Kick"
crushAnimSource(2,n)=1011 : crushAnimStart(2,n)=780 : crushAnimEnd(2,n)=820
crushTransition(2,n)=15 : crushSpeed#(2,n)=2.5 : crushLength(2,n)=crushTransition(2,n)+35
crushTravel#(2,n)=0.5 : crushMomentum(2,n)=crushTransition(2,n)+25 : crushFall(2,n)=0
crushImpactHigh(2,n)=crushTransition(2,n)+6 : crushImpactLow(2,n)=crushTransition(2,n)+10
crushRange#(2,n)=8 : crushPower(2,n)=100 : crushLimb(2,n)=44 : crushWeapon(2,n)=0
n=3 : crushName$(2,n)="Double Axe-Handle"
crushAnimSource(2,n)=1006 : crushAnimStart(2,n)=3070 : crushAnimEnd(2,n)=3155
crushTransition(2,n)=20 : crushSpeed#(2,n)=3.75 : crushLength(2,n)=crushTransition(2,n)+80
crushTravel#(2,n)=0.3 : crushMomentum(2,n)=crushTransition(2,n)+45 : crushFall(2,n)=0
crushImpactHigh(2,n)=crushTransition(2,n)+10 : crushImpactLow(2,n)=crushTransition(2,n)+15
crushRange#(2,n)=8 : crushPower(2,n)=125 : crushLimb(2,n)=21 : crushWeapon(2,n)=1
;crushes
crushList(3)=7
n=1 : crushName$(3,n)="Elbow Drop"
crushAnimSource(3,n)=1010 : crushAnimStart(3,n)=2035 : crushAnimEnd(3,n)=2185
crushTransition(3,n)=20 : crushSpeed#(3,n)=3.3 : crushLength(3,n)=crushTransition(3,n)+145
crushTravel#(3,n)=0.5 : crushMomentum(3,n)=crushTransition(3,n)+110 : crushFall(3,n)=crushTransition(3,n)+45
crushImpactHigh(3,n)=crushTransition(3,n)+18 : crushImpactLow(3,n)=crushTransition(3,n)+40
crushRange#(3,n)=10 : crushPower(3,n)=250 : crushLimb(3,n)=20 : crushWeapon(3,n)=1
n=2 : crushName$(3,n)="Knee Drop"
crushAnimSource(3,n)=1006 : crushAnimStart(3,n)=1680 : crushAnimEnd(3,n)=1760
crushTransition(3,n)=15 : crushSpeed#(3,n)=2.8 : crushLength(3,n)=crushTransition(3,n)+75
crushTravel#(3,n)=0.5 : crushMomentum(3,n)=crushTransition(3,n)+50 : crushFall(3,n)=crushTransition(3,n)+25
crushImpactHigh(3,n)=crushTransition(3,n)+5 : crushImpactLow(3,n)=crushTransition(3,n)+15
crushRange#(3,n)=8 : crushPower(3,n)=200 : crushLimb(3,n)=43 : crushWeapon(3,n)=1
n=3 : crushName$(3,n)="Leg Drop"
crushAnimSource(3,n)=1010 : crushAnimStart(3,n)=1255 : crushAnimEnd(3,n)=1400
crushTransition(3,n)=20 : crushSpeed#(3,n)=3.25 : crushLength(3,n)=crushTransition(3,n)+140
crushTravel#(3,n)=0.5 : crushMomentum(3,n)=crushTransition(3,n)+105 : crushFall(3,n)=crushTransition(3,n)+45
crushImpactHigh(3,n)=crushTransition(3,n)+25 : crushImpactLow(3,n)=crushTransition(3,n)+40
crushRange#(3,n)=10 : crushPower(3,n)=250 : crushLimb(3,n)=43 : crushWeapon(3,n)=1
n=4 : crushName$(3,n)="Senton Bomb"
crushAnimSource(3,n)=1010 : crushAnimStart(3,n)=1050 : crushAnimEnd(3,n)=1230
crushTransition(3,n)=20 : crushSpeed#(3,n)=3.75 : crushLength(3,n)=crushTransition(3,n)+175
crushTravel#(3,n)=1.0 : crushMomentum(3,n)=crushTransition(3,n)+105 : crushFall(3,n)=crushTransition(3,n)+60
crushImpactHigh(3,n)=crushTransition(3,n)+40 : crushImpactLow(3,n)=crushTransition(3,n)+55
crushRange#(3,n)=10 : crushPower(3,n)=300 : crushLimb(3,n)=36 : crushWeapon(3,n)=1
n=5 : crushName$(3,n)="Splash"
crushAnimSource(3,n)=1011 : crushAnimStart(3,n)=620 : crushAnimEnd(3,n)=755
crushTransition(3,n)=20 : crushSpeed#(3,n)=3.5 : crushLength(3,n)=crushTransition(3,n)+130
crushTravel#(3,n)=0.5 : crushMomentum(3,n)=crushTransition(3,n)+110 : crushFall(3,n)=crushTransition(3,n)+45
crushImpactHigh(3,n)=crushTransition(3,n)+15 : crushImpactLow(3,n)=crushTransition(3,n)+40
crushRange#(3,n)=12 : crushPower(3,n)=300 : crushLimb(3,n)=36 : crushWeapon(3,n)=1
n=6 : crushName$(3,n)="Moonsault"
crushAnimSource(3,n)=1009 : crushAnimStart(3,n)=2590 : crushAnimEnd(3,n)=2730
crushTransition(3,n)=20 : crushSpeed#(3,n)=3.5 : crushLength(3,n)=crushTransition(3,n)+140
crushTravel#(3,n)=1.0 : crushMomentum(3,n)=crushTransition(3,n)+120 : crushFall(3,n)=crushTransition(3,n)+55
crushImpactHigh(3,n)=crushTransition(3,n)+15 : crushImpactLow(3,n)=crushTransition(3,n)+50
crushRange#(3,n)=12 : crushPower(3,n)=300 : crushLimb(3,n)=36 : crushWeapon(3,n)=1
n=7 : crushName$(3,n)="Somersault Splash"
crushAnimSource(3,n)=1009 : crushAnimStart(3,n)=3190 : crushAnimEnd(3,n)=3330
crushTransition(3,n)=20 : crushSpeed#(3,n)=3.25 : crushLength(3,n)=crushTransition(3,n)+135
crushTravel#(3,n)=1.0 : crushMomentum(3,n)=crushTransition(3,n)+120 : crushFall(3,n)=crushTransition(3,n)+55
crushImpactHigh(3,n)=crushTransition(3,n)+25 : crushImpactLow(3,n)=crushTransition(3,n)+50
crushRange#(3,n)=12 : crushPower(3,n)=300 : crushLimb(3,n)=36 : crushWeapon(3,n)=1
;running
crushList(4)=crushList(3)
For n=1 To crushList(4)
 crushName$(4,n)=crushName$(3,n)
 crushAnimSource(4,n)=crushAnimSource(3,n) : crushAnimStart(4,n)=crushAnimStart(3,n) : crushAnimEnd(4,n)=crushAnimEnd(3,n)
 crushTransition(4,n)=crushTransition(3,n) : crushSpeed#(4,n)=crushSpeed#(3,n) : crushLength(4,n)=crushLength(3,n)
 crushTravel#(4,n)=crushTravel#(3,n) : crushMomentum(4,n)=crushLength(4,n) : crushFall(4,n)=crushFall(3,n)
 crushImpactHigh(4,n)=crushImpactHigh(3,n) : crushImpactLow(4,n)=crushImpactLow(3,n)
 crushRange#(4,n)=crushRange#(3,n) : crushPower(4,n)=PercentOf#(crushPower(3,n),125) : crushLimb(4,n)=crushLimb(3,n) : crushWeapon(4,n)=crushWeapon(3,n)
Next
;flying
crushList(5)=9
n=1 : crushName$(5,n)="Splash"
crushAnimSource(5,n)=1009 : crushAnimStart(5,n)=2300 : crushAnimEnd(5,n)=2420
crushTransition(5,n)=0 : crushSpeed#(5,n)=3.0 : crushLength(5,n)=120
crushTravel#(5,n)=20 : crushMomentum(5,n)=100 : crushFall(5,n)=2 : crushTope(5,n)=1
crushRange#(5,n)=25 : crushPower(5,n)=600 : crushLimb(5,n)=0 : crushWeapon(5,n)=1
n=2 : crushName$(5,n)="Frog Splash"
crushAnimSource(5,n)=1009 : crushAnimStart(5,n)=2755 : crushAnimEnd(5,n)=2875
crushTransition(5,n)=0 : crushSpeed#(5,n)=3.0 : crushLength(5,n)=120
crushTravel#(5,n)=20 : crushMomentum(5,n)=100 : crushFall(5,n)=2 : crushTope(5,n)=1
crushRange#(5,n)=25 : crushPower(5,n)=600 : crushLimb(5,n)=0 : crushWeapon(5,n)=1
n=3 : crushName$(5,n)="Moonsault"
crushAnimSource(5,n)=1009 : crushAnimStart(5,n)=2445 : crushAnimEnd(5,n)=2565
crushTransition(5,n)=0 : crushSpeed#(5,n)=3.0 : crushLength(5,n)=120
crushTravel#(5,n)=100 : crushMomentum(5,n)=100 : crushFall(5,n)=6 : crushTope(5,n)=1
crushRange#(5,n)=25 : crushPower(5,n)=600 : crushLimb(5,n)=0 : crushWeapon(5,n)=1
n=4 : crushName$(5,n)="Shooting Star Splash"
crushAnimSource(5,n)=1009 : crushAnimStart(5,n)=2900 : crushAnimEnd(5,n)=3020
crushTransition(5,n)=0 : crushSpeed#(5,n)=3.0 : crushLength(5,n)=120
crushTravel#(5,n)=20 : crushMomentum(5,n)=100 : crushFall(5,n)=2 : crushTope(5,n)=1
crushRange#(5,n)=25 : crushPower(5,n)=600 : crushLimb(5,n)=0 : crushWeapon(5,n)=1
n=5 : crushName$(5,n)="Somersault Splash"
crushAnimSource(5,n)=1009 : crushAnimStart(5,n)=3045 : crushAnimEnd(5,n)=3165
crushTransition(5,n)=0 : crushSpeed#(5,n)=3.0 : crushLength(5,n)=120
crushTravel#(5,n)=20 : crushMomentum(5,n)=100 : crushFall(5,n)=2 : crushTope(5,n)=1
crushRange#(5,n)=25 : crushPower(5,n)=600 : crushLimb(5,n)=0 : crushWeapon(5,n)=1
n=6 : crushName$(5,n)="Senton Bomb"
crushAnimSource(5,n)=1010 : crushAnimStart(5,n)=865 : crushAnimEnd(5,n)=1025
crushTransition(5,n)=0 : crushSpeed#(5,n)=3.0 : crushLength(5,n)=160
crushTravel#(5,n)=100 : crushMomentum(5,n)=105 : crushFall(5,n)=1 : crushTope(5,n)=1
crushRange#(5,n)=20 : crushPower(5,n)=600 : crushLimb(5,n)=0 : crushWeapon(5,n)=1
n=7 : crushName$(5,n)="Leg Drop"
crushAnimSource(5,n)=1010 : crushAnimStart(5,n)=1425 : crushAnimEnd(5,n)=1550
crushTransition(5,n)=0 : crushSpeed#(5,n)=3.0 : crushLength(5,n)=125
crushTravel#(5,n)=40 : crushMomentum(5,n)=100 : crushFall(5,n)=3 : crushTope(5,n)=0
crushRange#(5,n)=22 : crushPower(5,n)=600 : crushLimb(5,n)=43 : crushWeapon(5,n)=1
n=8 : crushName$(5,n)="Knee Drop"
crushAnimSource(5,n)=1010 : crushAnimStart(5,n)=1880 : crushAnimEnd(5,n)=2010
crushTransition(5,n)=0 : crushSpeed#(5,n)=3.0 : crushLength(5,n)=130
crushTravel#(5,n)=60 : crushMomentum(5,n)=130 : crushFall(5,n)=4 : crushTope(5,n)=0
crushRange#(5,n)=16 : crushPower(5,n)=600 : crushLimb(5,n)=43 : crushWeapon(5,n)=1
n=9 : crushName$(5,n)="Elbow Drop"
crushAnimSource(5,n)=1010 : crushAnimStart(5,n)=2210 : crushAnimEnd(5,n)=2320
crushTransition(5,n)=0 : crushSpeed#(5,n)=3.0 : crushLength(5,n)=110
crushTravel#(5,n)=20 : crushMomentum(5,n)=110 : crushFall(5,n)=5 : crushTope(5,n)=0
crushRange#(5,n)=20 : crushPower(5,n)=600 : crushLimb(5,n)=20 : crushWeapon(5,n)=1

;///////////////////////////////////////////////////////
;---------------------- MOVES ------------------------
;///////////////////////////////////////////////////////
.Moves
;move library
Dim moveList(4) ;1=standing, 2=corner, 3=head, 4=legs
Dim moveName$(4,200)
Dim moveAnim(4,200)
Dim moveLearned(4,200)
;standing moves
n=1 : moveName$(1,n)="Upper Attack" : moveAnim(1,n)=1
n=2 : moveName$(1,n)="Lower Attack" : moveAnim(1,n)=2
n=3 : moveName$(1,n)="Big Attack" : moveAnim(1,n)=3
n=4 : moveName$(1,n)="Dust Throw" : moveAnim(1,n)=4
n=5 : moveName$(1,n)="Green Mist" : moveAnim(1,n)=5
n=6 : moveName$(1,n)="Fire Breath" : moveAnim(1,n)=6
n=7 : moveName$(1,n)="Irish Whip" : moveAnim(1,n)=310
n=8 : moveName$(1,n)="Small Package" : moveAnim(1,n)=345
n=9 : moveName$(1,n)="Drop Toe Hold" : moveAnim(1,n)=360
n=10 : moveName$(1,n)="Spear" : moveAnim(1,n)=370
n=11 : moveName$(1,n)="Standing Clothesline" : moveAnim(1,n)=371
n=12 : moveName$(1,n)="Headlock Punch" : moveAnim(1,n)=326
n=13 : moveName$(1,n)="Headlock" : moveAnim(1,n)=325
n=14 : moveName$(1,n)="Sleeper Hold" : moveAnim(1,n)=324
n=15 : moveName$(1,n)="Russian Legsweep" : moveAnim(1,n)=369
n=16 : moveName$(1,n)="Hip Toss" : moveAnim(1,n)=367
n=17 : moveName$(1,n)="Headlock Takedown" : moveAnim(1,n)=328
n=18 : moveName$(1,n)="Bulldog" : moveAnim(1,n)=327
n=19 : moveName$(1,n)="Snapmare" : moveAnim(1,n)=363
n=20 : moveName$(1,n)="Neckbreaker" : moveAnim(1,n)=364
n=21 : moveName$(1,n)="Stunner" : moveAnim(1,n)=355
n=22 : moveName$(1,n)="DDT" : moveAnim(1,n)=365
n=23 : moveName$(1,n)="Tornado DDT" : moveAnim(1,n)=366
n=24 : moveName$(1,n)="Reverse DDT" : moveAnim(1,n)=375
n=25 : moveName$(1,n)="Reverse Suplex" : moveAnim(1,n)=376
n=26 : moveName$(1,n)="Snap Suplex" : moveAnim(1,n)=318
n=27 : moveName$(1,n)="Suplex" : moveAnim(1,n)=317
n=28 : moveName$(1,n)="Stalling Suplex" : moveAnim(1,n)=319
n=29 : moveName$(1,n)="Brainbuster" : moveAnim(1,n)=320
n=30 : moveName$(1,n)="Stalling Brainbuster" : moveAnim(1,n)=321
n=31 : moveName$(1,n)="Suplex Drop" : moveAnim(1,n)=323
n=32 : moveName$(1,n)="Suplex Slam" : moveAnim(1,n)=322
n=33 : moveName$(1,n)="Underhook Suplex" : moveAnim(1,n)=374
n=34 : moveName$(1,n)="Northern Lights Suplex" : moveAnim(1,n)=348
n=35 : moveName$(1,n)="German Suplex" : moveAnim(1,n)=357
n=36 : moveName$(1,n)="Back Suplex" : moveAnim(1,n)=358
n=37 : moveName$(1,n)="Gutwrench Suplex" : moveAnim(1,n)=368
n=38 : moveName$(1,n)="Belly-To-Belly Suplex" : moveAnim(1,n)=346
n=39 : moveName$(1,n)="Belly-To-Belly Slam" : moveAnim(1,n)=347
n=40 : moveName$(1,n)="Spinebuster" : moveAnim(1,n)=350
n=41 : moveName$(1,n)="Powerslam" : moveAnim(1,n)=332
n=42 : moveName$(1,n)="Shoulder Powerslam" : moveAnim(1,n)=333
n=43 : moveName$(1,n)="Bodyslam" : moveAnim(1,n)=316
n=44 : moveName$(1,n)="Press Slam" : moveAnim(1,n)=330
n=45 : moveName$(1,n)="Pumping Press Slam" : moveAnim(1,n)=329
n=46 : moveName$(1,n)="Gorilla Press Slam" : moveAnim(1,n)=331
n=47 : moveName$(1,n)="Back Body Drop" : moveAnim(1,n)=349
n=48 : moveName$(1,n)="Samoan Drop" : moveAnim(1,n)=361
n=49 : moveName$(1,n)="Death Valley Driver" : moveAnim(1,n)=362
n=50 : moveName$(1,n)="Rock Bottom" : moveAnim(1,n)=356
n=51 : moveName$(1,n)="Choke Slam" : moveAnim(1,n)=372
n=52 : moveName$(1,n)="Side Slam" : moveAnim(1,n)=351
n=53 : moveName$(1,n)="Side Backbreaker" : moveAnim(1,n)=352
n=54 : moveName$(1,n)="Backbreaker" : moveAnim(1,n)=354
n=55 : moveName$(1,n)="Atomic Drop" : moveAnim(1,n)=359
n=56 : moveName$(1,n)="Inverted Atomic Drop" : moveAnim(1,n)=353
n=57 : moveName$(1,n)="Shoulder Breaker" : moveAnim(1,n)=334
n=58 : moveName$(1,n)="Tombstone Piledriver" : moveAnim(1,n)=335
n=59 : moveName$(1,n)="Inverted Piledriver" : moveAnim(1,n)=336
n=60 : moveName$(1,n)="Sitting Bodyslam" : moveAnim(1,n)=337
n=61 : moveName$(1,n)="Pedigree" : moveAnim(1,n)=341
n=62 : moveName$(1,n)="Piledriver" : moveAnim(1,n)=338
n=63 : moveName$(1,n)="Powerbomb" : moveAnim(1,n)=339
n=64 : moveName$(1,n)="Sitting Powerbomb" : moveAnim(1,n)=340
n=65 : moveName$(1,n)="Jumping Facebuster" : moveAnim(1,n)=373
n=66 : moveName$(1,n)="Flying Head Scissors" : moveAnim(1,n)=344
n=67 : moveName$(1,n)="Leaping Plancha" : moveAnim(1,n)=343
n=68 : moveName$(1,n)="Hurricanranna" : moveAnim(1,n)=342
n=69 : moveName$(1,n)="MDKO" : moveAnim(1,n)=377
n=70 : moveName$(1,n)="Victory Roll" : moveAnim(1,n)=378
n=71 : moveName$(1,n)="Test Of Strength" : moveAnim(1,n)=379
n=72 : moveName$(1,n)="Razor's Edge" : moveAnim(1,n)=380
n=73 : moveName$(1,n)="Go To Sleep" : moveAnim(1,n)=381
n=74 : moveName$(1,n)="Roll-Up Pin" : moveAnim(1,n)=382
n=75 : moveName$(1,n)="Crucifix Pin" : moveAnim(1,n)=383
n=76 : moveName$(1,n)="Full Nelson" : moveAnim(1,n)=384
n=77 : moveName$(1,n)="Full Nelson Suplex" : moveAnim(1,n)=385
n=78 : moveName$(1,n)="Skull Crusher" : moveAnim(1,n)=386
n=79 : moveName$(1,n)="F5" : moveAnim(1,n)=387
n=80 : moveName$(1,n)="Throat Toss" : moveAnim(1,n)=388
n=81 : moveName$(1,n)="Attitude Adjustment" : moveAnim(1,n)=389
moveList(1)=n
;corner moves
moveList(2)=6
n=1 : moveName$(2,n)="Drag Down" : moveAnim(2,n)=710
n=2 : moveName$(2,n)="Shoulder Barges" : moveAnim(2,n)=714
n=3 : moveName$(2,n)="Head Smashes" : moveAnim(2,n)=713
n=4 : moveName$(2,n)="Mounted Punches" : moveAnim(2,n)=712
n=5 : moveName$(2,n)="Superplex" : moveAnim(2,n)=711
n=6 : moveName$(2,n)="Hurricanranna" : moveAnim(2,n)=715
;head moves
moveList(3)=10
n=1 : moveName$(3,n)="Drag Up" : moveAnim(3,n)=510
n=2 : moveName$(3,n)="Mounted Punches" : moveAnim(3,n)=512
n=3 : moveName$(3,n)="Head Smashes" : moveAnim(3,n)=515
n=4 : moveName$(3,n)="Choke Hold" : moveAnim(3,n)=516
n=5 : moveName$(3,n)="Sleeper Hold" : moveAnim(3,n)=513
n=6 : moveName$(3,n)="Dragon Sleeper" : moveAnim(3,n)=514
n=7 : moveName$(3,n)="Bow & Arrow Hold" : moveAnim(3,n)=518
n=8 : moveName$(3,n)="Arm Bar" : moveAnim(3,n)=519
n=9 : moveName$(3,n)="Camel Clutch" : moveAnim(3,n)=511
n=10 : moveName$(3,n)="Crossface Clutch" : moveAnim(3,n)=517
;leg moves
moveList(4)=11
n=1 : moveName$(4,n)="Drag Up" : moveAnim(4,n)=610
n=2 : moveName$(4,n)="Slingshot" : moveAnim(4,n)=619
n=3 : moveName$(4,n)="Groin Kick" : moveAnim(4,n)=612
n=4 : moveName$(4,n)="Spinning Toe Hold" : moveAnim(4,n)=617
n=5 : moveName$(4,n)="Side Leglock" : moveAnim(4,n)=618
n=6 : moveName$(4,n)="Half Crab" : moveAnim(4,n)=614
n=7 : moveName$(4,n)="Boston Crab" : moveAnim(4,n)=613
n=8 : moveName$(4,n)="Elevated Crab" : moveAnim(4,n)=615
n=9 : moveName$(4,n)="Scorpion Lock" : moveAnim(4,n)=611
n=10 : moveName$(4,n)="Figure 4 Leglock" : moveAnim(4,n)=616
n=11 : moveName$(4,n)="Ankle Lock" : moveAnim(4,n)=620

;///////////////////////////////////////////////////////
;---------------------- GESTURES -----------------------
;///////////////////////////////////////////////////////
.Gestures
;stances
Global no_stances=6
Dim stanceName$(no_stances)
Dim stanceType(no_stances) ;0=inactive, 1=active
n=0 : stanceName$(n)="Normal" : stanceType(n)=0
n=1 : stanceName$(n)="Powerful" : stanceType(n)=0
n=2 : stanceName$(n)="Technical" : stanceType(n)=1
n=3 : stanceName$(n)="Fighter" : stanceType(n)=1
n=4 : stanceName$(n)="Martial Arts" : stanceType(n)=1
n=5 : stanceName$(n)="Unorthodox" : stanceType(n)=1
n=6 : stanceName$(n)="Feminine" : stanceType(n)=0
;taunts
Global no_taunts
Dim tauntName$(200)
Dim tauntAnimStart(200)
Dim tauntAnimEnd(200)
Dim tauntAnimSource(200)
Dim tauntSpeed#(200)
Dim tauntTransition(200)
Dim tauntLength(200)
Dim tauntLoop(200)
Dim tauntLearned(200)
n=1 : tauntName$(n)="Punch The Air"
tauntAnimStart(n)=665 : tauntAnimEnd(n)=745
tauntAnimSource(n)=1003 : tauntSpeed#(n)=2.0 
tauntTransition(n)=5 : tauntLength(n)=80 : tauntLoop(n)=0
n=2 : tauntName$(n)="Get Outta Here"
tauntAnimStart(n)=755 : tauntAnimEnd(n)=840
tauntAnimSource(n)=1003 : tauntSpeed#(n)=2.0
tauntTransition(n)=5 : tauntLength(n)=85 : tauntLoop(n)=0
n=3 : tauntName$(n)="Go Ahead"
tauntAnimStart(n)=1090 : tauntAnimEnd(n)=1180
tauntAnimSource(n)=1004 : tauntSpeed#(n)=2.5
tauntTransition(n)=7 : tauntLength(n)=90 : tauntLoop(n)=0
n=4 : tauntName$(n)="Single Count"
tauntAnimStart(n)=850 : tauntAnimEnd(n)=935
tauntAnimSource(n)=1003 : tauntSpeed#(n)=2.5
tauntTransition(n)=5 : tauntLength(n)=85 : tauntLoop(n)=0
n=5 : tauntName$(n)="Disqualify"
tauntAnimStart(n)=945 : tauntAnimEnd(n)=1035
tauntAnimSource(n)=1003 : tauntSpeed#(n)=2.5
tauntTransition(n)=5 : tauntLength(n)=90 : tauntLoop(n)=0
n=6 : tauntName$(n)="Break"
tauntAnimStart(n)=1140 : tauntAnimEnd(n)=1230
tauntAnimSource(n)=1003 : tauntSpeed#(n)=2.5
tauntTransition(n)=5 : tauntLength(n)=90 : tauntLoop(n)=0
n=7 : tauntName$(n)="Resume"
tauntAnimStart(n)=1240 : tauntAnimEnd(n)=1330
tauntAnimSource(n)=1003 : tauntSpeed#(n)=2.5
tauntTransition(n)=5 : tauntLength(n)=90 : tauntLoop(n)=0
n=8 : tauntName$(n)="Deep In Thought"
tauntAnimStart(n)=985 : tauntAnimEnd(n)=1065
tauntAnimSource(n)=1004 : tauntSpeed#(n)=0.5
tauntTransition(n)=10 : tauntLength(n)=80 : tauntLoop(n)=1
n=9 : tauntName$(n)="Wake Up"
tauntAnimStart(n)=2255 : tauntAnimEnd(n)=2295
tauntAnimSource(n)=1003 : tauntSpeed#(n)=2.0
tauntTransition(n)=5 : tauntLength(n)=40 : tauntLoop(n)=0
n=10 : tauntName$(n)="Sore Hand"
tauntAnimStart(n)=210 : tauntAnimEnd(n)=350
tauntAnimSource(n)=1004 : tauntSpeed#(n)=2.0
tauntTransition(n)=7 : tauntLength(n)=140 : tauntLoop(n)=0
n=11 : tauntName$(n)="Sore Arm"
tauntAnimStart(n)=270 : tauntAnimEnd(n)=350
tauntAnimSource(n)=1004 : tauntSpeed#(n)=2.0
tauntTransition(n)=10 : tauntLength(n)=80 : tauntLoop(n)=0
n=12 : tauntName$(n)="Sore Head"
tauntAnimStart(n)=535 : tauntAnimEnd(n)=670
tauntAnimSource(n)=1004 : tauntSpeed#(n)=2.0
tauntTransition(n)=5 : tauntLength(n)=135 : tauntLoop(n)=0
n=13 : tauntName$(n)="Sore Ribs"
tauntAnimStart(n)=695 : tauntAnimEnd(n)=830
tauntAnimSource(n)=1004 : tauntSpeed#(n)=2.5
tauntTransition(n)=5 : tauntLength(n)=135 : tauntLoop(n)=0
n=14 : tauntName$(n)="Sore Leg"
tauntAnimStart(n)=860 : tauntAnimEnd(n)=975
tauntAnimSource(n)=1004 : tauntSpeed#(n)=2.0
tauntTransition(n)=5 : tauntLength(n)=115 : tauntLoop(n)=0
n=15 : tauntName$(n)="Shake Raised Arms"
tauntAnimStart(n)=1190 : tauntAnimEnd(n)=1250
tauntAnimSource(n)=1004 : tauntSpeed#(n)=1.2
tauntTransition(n)=10 : tauntLength(n)=100 : tauntLoop(n)=1
n=16 : tauntName$(n)="Pat Chest"
tauntAnimStart(n)=1275 : tauntAnimEnd(n)=1380
tauntAnimSource(n)=1004 : tauntSpeed#(n)=2.0
tauntTransition(n)=5 : tauntLength(n)=105 : tauntLoop(n)=0
n=17 : tauntName$(n)="Shrug Shoulders"
tauntAnimStart(n)=1390 : tauntAnimEnd(n)=1470
tauntAnimSource(n)=1004 : tauntSpeed#(n)=1.5
tauntTransition(n)=5 : tauntLength(n)=80 : tauntLoop(n)=0
n=18 : tauntName$(n)="Belt Around Waist"
tauntAnimStart(n)=1620 : tauntAnimEnd(n)=1740
tauntAnimSource(n)=1004 : tauntSpeed#(n)=1.0
tauntTransition(n)=10 : tauntLength(n)=120 : tauntLoop(n)=0
n=19 : tauntName$(n)="Belt On Shoulder"
tauntAnimStart(n)=1790 : tauntAnimEnd(n)=1910
tauntAnimSource(n)=1004 : tauntSpeed#(n)=1.0
tauntTransition(n)=10 : tauntLength(n)=120 : tauntLoop(n)=0
n=20 : tauntName$(n)="Go Away"
tauntAnimStart(n)=1920 : tauntAnimEnd(n)=2010
tauntAnimSource(n)=1004 : tauntSpeed#(n)=1.0
tauntTransition(n)=5 : tauntLength(n)=90 : tauntLoop(n)=1
n=21 : tauntName$(n)="Folded Arms"
tauntAnimStart(n)=2020 : tauntAnimEnd(n)=2080
tauntAnimSource(n)=1004 : tauntSpeed#(n)=0.5
tauntTransition(n)=10 : tauntLength(n)=60 : tauntLoop(n)=1
n=22 : tauntName$(n)="Self Abuse"
tauntAnimStart(n)=70 : tauntAnimEnd(n)=210
tauntAnimSource(n)=1006 : tauntSpeed#(n)=3.0
tauntTransition(n)=5 : tauntLength(n)=140 : tauntLoop(n)=1
n=23 : tauntName$(n)="Wind Up"
tauntAnimStart(n)=310 : tauntAnimEnd(n)=370
tauntAnimSource(n)=1006 : tauntSpeed#(n)=3.0
tauntTransition(n)=5 : tauntLength(n)=120 : tauntLoop(n)=1
n=24 : tauntName$(n)="Disappointment"
tauntAnimStart(n)=835 : tauntAnimEnd(n)=935
tauntAnimSource(n)=1006 : tauntSpeed#(n)=0.8
tauntTransition(n)=15 : tauntLength(n)=100 : tauntLoop(n)=0
n=25 : tauntName$(n)="Hands On Hips"
tauntAnimStart(n)=945 : tauntAnimEnd(n)=1005
tauntAnimSource(n)=1006 : tauntSpeed#(n)=0.5
tauntTransition(n)=10 : tauntLength(n)=60 : tauntLoop(n)=1
n=26 : tauntName$(n)="Shake Fist"
tauntAnimStart(n)=1045 : tauntAnimEnd(n)=1135
tauntAnimSource(n)=1006 : tauntSpeed#(n)=2.0
tauntTransition(n)=10 : tauntLength(n)=90 : tauntLoop(n)=0
n=27 : tauntName$(n)="Point Forwards"
tauntAnimStart(n)=140 : tauntAnimEnd(n)=200
tauntAnimSource(n)=1007 : tauntSpeed#(n)=1.0
tauntTransition(n)=7 : tauntLength(n)=60 : tauntLoop(n)=1
n=28 : tauntName$(n)="Point To Side"
tauntAnimStart(n)=210 : tauntAnimEnd(n)=270
tauntAnimSource(n)=1007 : tauntSpeed#(n)=1.0
tauntTransition(n)=7 : tauntLength(n)=60 : tauntLoop(n)=1
n=29 : tauntName$(n)="Shake Finger"
tauntAnimStart(n)=70 : tauntAnimEnd(n)=130
tauntAnimSource(n)=1007 : tauntSpeed#(n)=1.5
tauntTransition(n)=7 : tauntLength(n)=60 : tauntLoop(n)=1
n=30 : tauntName$(n)="Applause"
tauntAnimStart(n)=420 : tauntAnimEnd(n)=480
tauntAnimSource(n)=1007 : tauntSpeed#(n)=1.2
tauntTransition(n)=10 : tauntLength(n)=60 : tauntLoop(n)=1
n=31 : tauntName$(n)="Raised Arm"
tauntAnimStart(n)=575 : tauntAnimEnd(n)=635
tauntAnimSource(n)=1007 : tauntSpeed#(n)=0.5
tauntTransition(n)=10 : tauntLength(n)=60 : tauntLoop(n)=1
n=32 : tauntName$(n)="Raised Arms"
tauntAnimStart(n)=645 : tauntAnimEnd(n)=705
tauntAnimSource(n)=1007 : tauntSpeed#(n)=0.5
tauntTransition(n)=10 : tauntLength(n)=60 : tauntLoop(n)=1
n=33 : tauntName$(n)="Outstretched Arms"
tauntAnimStart(n)=715 : tauntAnimEnd(n)=775
tauntAnimSource(n)=1007 : tauntSpeed#(n)=0.5
tauntTransition(n)=15 : tauntLength(n)=60 : tauntLoop(n)=1
n=34 : tauntName$(n)="Hang Head Forward"
tauntAnimStart(n)=1060 : tauntAnimEnd(n)=1120
tauntAnimSource(n)=1007 : tauntSpeed#(n)=1.0
tauntTransition(n)=15 : tauntLength(n)=60 : tauntLoop(n)=1
n=35 : tauntName$(n)="Rubbing Chin"
tauntAnimStart(n)=2110 : tauntAnimEnd(n)=2170
tauntAnimSource(n)=1004 : tauntSpeed#(n)=0.5
tauntTransition(n)=10 : tauntLength(n)=60 : tauntLoop(n)=1
n=36 : tauntName$(n)="Come Here"
tauntAnimStart(n)=310 : tauntAnimEnd(n)=370
tauntAnimSource(n)=1008 : tauntSpeed#(n)=1.0
tauntTransition(n)=7 : tauntLength(n)=60 : tauntLoop(n)=1
n=37 : tauntName$(n)="Come On"
tauntAnimStart(n)=480 : tauntAnimEnd(n)=540
tauntAnimSource(n)=1008 : tauntSpeed#(n)=2.0
tauntTransition(n)=7 : tauntLength(n)=90 : tauntLoop(n)=1
n=38 : tauntName$(n)="Fancy Footwork"
tauntAnimStart(n)=388 : tauntAnimEnd(n)=470
tauntAnimSource(n)=1008 : tauntSpeed#(n)=1.5
tauntTransition(n)=5 : tauntLength(n)=80 : tauntLoop(n)=0
n=39 : tauntName$(n)="Power Stance"
tauntAnimStart(n)=905 : tauntAnimEnd(n)=965
tauntAnimSource(n)=1007 : tauntSpeed#(n)=0.5
tauntTransition(n)=5 : tauntLength(n)=60 : tauntLoop(n)=1
n=40 : tauntName$(n)="Bicep Flex"
tauntAnimStart(n)=550 : tauntAnimEnd(n)=610
tauntAnimSource(n)=1008 : tauntSpeed#(n)=1.0
tauntTransition(n)=7 : tauntLength(n)=60 : tauntLoop(n)=1
n=41 : tauntName$(n)="Wrist Fiddle"
tauntAnimStart(n)=620 : tauntAnimEnd(n)=710
tauntAnimSource(n)=1008 : tauntSpeed#(n)=1.0
tauntTransition(n)=7 : tauntLength(n)=90 : tauntLoop(n)=1
n=42 : tauntName$(n)="Prayer"
tauntAnimStart(n)=720 : tauntAnimEnd(n)=780
tauntAnimSource(n)=1008 : tauntSpeed#(n)=0.5
tauntTransition(n)=7 : tauntLength(n)=60 : tauntLoop(n)=1
n=43 : tauntName$(n)="Wave"
tauntAnimStart(n)=790 : tauntAnimEnd(n)=850
tauntAnimSource(n)=1008 : tauntSpeed#(n)=1.0
tauntTransition(n)=10 : tauntLength(n)=60 : tauntLoop(n)=1
n=44 : tauntName$(n)="Calm Down"
tauntAnimStart(n)=960 : tauntAnimEnd(n)=1020
tauntAnimSource(n)=1008 : tauntSpeed#(n)=1.0
tauntTransition(n)=7 : tauntLength(n)=60 : tauntLoop(n)=1
n=45 : tauntName$(n)="Punch Hand"
tauntAnimStart(n)=1050 : tauntAnimEnd(n)=1110
tauntAnimSource(n)=1008 : tauntSpeed#(n)=2.0
tauntTransition(n)=10 : tauntLength(n)=60 : tauntLoop(n)=0
n=46 : tauntName$(n)="Hammer Hand"
tauntAnimStart(n)=1140 : tauntAnimEnd(n)=1200
tauntAnimSource(n)=1008 : tauntSpeed#(n)=2.0
tauntTransition(n)=10 : tauntLength(n)=60 : tauntLoop(n)=0
n=47 : tauntName$(n)="Rule Out"
tauntAnimStart(n)=1050 : tauntAnimEnd(n)=1110
tauntAnimSource(n)=1009 : tauntSpeed#(n)=1.25
tauntTransition(n)=5 : tauntLength(n)=90 : tauntLoop(n)=1
n=48 : tauntName$(n)="Point At Ground"
tauntAnimStart(n)=1135 : tauntAnimEnd(n)=1195
tauntAnimSource(n)=1009 : tauntSpeed#(n)=1.25
tauntTransition(n)=10 : tauntLength(n)=90 : tauntLoop(n)=1
n=49 : tauntName$(n)="Double Count"
tauntAnimStart(n)=1045 : tauntAnimEnd(n)=1130
tauntAnimSource(n)=1003 : tauntSpeed#(n)=2.5
tauntTransition(n)=5 : tauntLength(n)=90 : tauntLoop(n)=0
n=50 : tauntName$(n)="Explanation"
tauntAnimStart(n)=495 : tauntAnimEnd(n)=675
tauntAnimSource(n)=1010 : tauntSpeed#(n)=1.25
tauntTransition(n)=5 : tauntLength(n)=100 : tauntLoop(n)=1
n=51 : tauntName$(n)="Two Birds!"
tauntAnimStart(n)=1635 : tauntAnimEnd(n)=1675
tauntAnimSource(n)=1011 : tauntSpeed#(n)=1.0
tauntTransition(n)=10 : tauntLength(n)=40 : tauntLoop(n)=1
n=52 : tauntName$(n)="Finger Guns"
tauntAnimStart(n)=1685 : tauntAnimEnd(n)=1725
tauntAnimSource(n)=1011 : tauntSpeed#(n)=1.0
tauntTransition(n)=10 : tauntLength(n)=80 : tauntLoop(n)=1
n=53 : tauntName$(n)="Cupped Ear"
tauntAnimStart(n)=1745 : tauntAnimEnd(n)=1815
tauntAnimSource(n)=1011 : tauntSpeed#(n)=1.25
tauntTransition(n)=8 : tauntLength(n)=70 : tauntLoop(n)=0
n=54 : tauntName$(n)="Crotch Chop"
tauntAnimStart(n)=1835 : tauntAnimEnd(n)=1885
tauntAnimSource(n)=1011 : tauntSpeed#(n)=1.25
tauntTransition(n)=8 : tauntLength(n)=50 : tauntLoop(n)=0
n=55 : tauntName$(n)="X Sign"
tauntAnimStart(n)=1895 : tauntAnimEnd(n)=1935
tauntAnimSource(n)=1011 : tauntSpeed#(n)=0.5
tauntTransition(n)=10 : tauntLength(n)=80 : tauntLoop(n)=1
n=56 : tauntName$(n)="Diamond Sign"
tauntAnimStart(n)=1945 : tauntAnimEnd(n)=1985
tauntAnimSource(n)=1011 : tauntSpeed#(n)=0.5
tauntTransition(n)=10 : tauntLength(n)=80 : tauntLoop(n)=1
n=57 : tauntName$(n)="Strength From Above"
tauntAnimStart(n)=1995 : tauntAnimEnd(n)=2035
tauntAnimSource(n)=1011 : tauntSpeed#(n)=1.0
tauntTransition(n)=8 : tauntLength(n)=80 : tauntLoop(n)=1
n=58 : tauntName$(n)="Raise The Roof"
tauntAnimStart(n)=2045 : tauntAnimEnd(n)=2085
tauntAnimSource(n)=1011 : tauntSpeed#(n)=1.0
tauntTransition(n)=10 : tauntLength(n)=80 : tauntLoop(n)=1
n=59 : tauntName$(n)="Arm Pump"
tauntAnimStart(n)=2095 : tauntAnimEnd(n)=2135
tauntAnimSource(n)=1011 : tauntSpeed#(n)=1.25
tauntTransition(n)=8 : tauntLength(n)=80 : tauntLoop(n)=1
n=60 : tauntName$(n)="Overhead Arm Pump"
tauntAnimStart(n)=2145 : tauntAnimEnd(n)=2185
tauntAnimSource(n)=1011 : tauntSpeed#(n)=1.0
tauntTransition(n)=10 : tauntLength(n)=80 : tauntLoop(n)=1
n=61 : tauntName$(n)="Throat Slice"
tauntAnimStart(n)=2205 : tauntAnimEnd(n)=2255
tauntAnimSource(n)=1011 : tauntSpeed#(n)=1.5
tauntTransition(n)=10 : tauntLength(n)=50 : tauntLoop(n)=0
n=62 : tauntName$(n)="Beat Chest"
tauntAnimStart(n)=2275 : tauntAnimEnd(n)=2345
tauntAnimSource(n)=1011 : tauntSpeed#(n)=2.0
tauntTransition(n)=5 : tauntLength(n)=70 : tauntLoop(n)=0
n=63 : tauntName$(n)="Emotional"
tauntAnimStart(n)=2355 : tauntAnimEnd(n)=2415
tauntAnimSource(n)=1011 : tauntSpeed#(n)=0.5
tauntTransition(n)=10 : tauntLength(n)=60 : tauntLoop(n)=1
n=64 : tauntName$(n)="Salute"
tauntAnimStart(n)=2425 : tauntAnimEnd(n)=2465
tauntAnimSource(n)=1011 : tauntSpeed#(n)=0.5
tauntTransition(n)=10 : tauntLength(n)=80 : tauntLoop(n)=1
n=65 : tauntName$(n)="Surrender"
tauntAnimStart(n)=2475 : tauntAnimEnd(n)=2515
tauntAnimSource(n)=1011 : tauntSpeed#(n)=0.5
tauntTransition(n)=10 : tauntLength(n)=80 : tauntLoop(n)=1
n=66 : tauntName$(n)="Feminine Stance"
tauntAnimStart(n)=1625 : tauntAnimEnd(n)=1665
tauntAnimSource(n)=1005 : tauntSpeed#(n)=0.25
tauntTransition(n)=10 : tauntLength(n)=80 : tauntLoop(n)=1
n=67 : tauntName$(n)="Go To Sleep"
tauntAnimStart(n)=2535 : tauntAnimEnd(n)=2605
tauntAnimSource(n)=1011 : tauntSpeed#(n)=0.75
tauntTransition(n)=8 : tauntLength(n)=65 : tauntLoop(n)=0
n=68 : tauntName$(n)="Can't See Me"
tauntAnimStart(n)=2670 : tauntAnimEnd(n)=2735
tauntAnimSource(n)=1010 : tauntSpeed#(n)=1.1
tauntTransition(n)=15 : tauntLength(n)=65 : tauntLoop(n)=0
n=69 : tauntName$(n)="Invite Applause"
tauntAnimStart(n)=2760 : tauntAnimEnd(n)=2820
tauntAnimSource(n)=1010 : tauntSpeed#(n)=0.5
tauntTransition(n)=15 : tauntLength(n)=60 : tauntLoop(n)=1

no_taunts=n

;///////////////////////////////////////////////////////
;----------------------- ITEMS -------------------------
;///////////////////////////////////////////////////////
.Items
Global no_items=20,itemLimit=50,itemList=15
Global itemSelection=1 ;0=random, 1=standard, 2+=specific bias
Global itemLayout=1 ;0=anywhere, 1=standard, 2=mostly backstage, 3=mostly ringside, 4=mostly inside, 5=specific inside
;state
Dim i(itemLimit)
Dim iType(itemLimit)
Dim iX#(itemLimit)
Dim iY#(itemLimit)
Dim iZ#(itemLimit)
Dim iOldX#(itemLimit)
Dim iOldY#(itemLimit)
Dim iOldZ#(itemLimit)
Dim iA#(itemLimit)
Dim iState(itemLimit) ;0=standing, 1=broken
Dim iOldState(itemLimit)
Dim iCarrier(itemLimit)
Dim iFallA#(itemLimit)
Dim iGround#(itemLimit)
Dim iGravity#(itemLimit)
Dim iAnim(itemLimit)
Dim iAnimTim(itemLimit)
Dim iScar(itemLimit)
Dim iOldScar(itemLimit)
Dim iBurning(itemLimit)
Dim iChannel(itemLimit)
Dim iScreen(itemLimit)
Dim iOldScreen(itemLimit)
;dimensions
Dim iDensity#(itemLimit)
Dim iDimX#(itemLimit,1000)
Dim iDimZ#(itemLimit,1000)
;type
Dim iName$(itemList)
Dim iFile$(itemList)
Dim iSound(itemList)
Dim iTexture(itemList)
Dim iShine#(itemList)
Dim iSizeX#(itemList)
Dim iSizeZ#(itemList)
Dim iElevate#(itemList)
Dim iCarryY#(itemList,2)
Dim iCarryA#(itemList,2)
Dim iCarryDepth#(itemList,2)
Dim iCarryAnim(itemList,2) ;0=wide, 1=narrow
Dim iHeight#(itemList)
Dim iDamage(itemList)
Dim iFlammable(itemList)
Dim iExplodable(itemList)
Dim iStandard(itemList)
;PROPERTIES
Function LoadItemData()
 ;wooden table
 n=1 : iName$(n)="Wooden Table" : iFile$(n)="Items/Table.3DS"
 iSound(n)=sSmashWood : iTexture(n)=0 : iShine#(n)=0 
 iSizeX#(n)=11 : iSizeZ#(n)=7
 iCarryY#(n,0)=-16.5 : iCarryY#(n,1)=-3
 iCarryA#(n,0)=0 : iCarryA#(n,1)=0 
 iCarryDepth#(n,0)=15.5 : iCarryDepth#(n,1)=15
 iCarryAnim(n,0)=0 : iCarryAnim(n,1)=0 
 iElevate#(n)=5.0 : iHeight#(n)=17
 iDamage(n)=100 : iFlammable(n)=1 : iExplodable(n)=0
 ;metal table
 n=2 : iName$(n)="Metal Table" : iFile$(n)="Items/Table.3DS"
 iSound(n)=sSmashMetal : iTexture(n)=tMetal(1) : iShine#(n)=0.5
 iSizeX#(n)=11 : iSizeZ#(n)=7
 iCarryY#(n,0)=-16.5 : iCarryY#(n,1)=-3
 iCarryA#(n,0)=0 : iCarryA#(n,1)=0 
 iCarryDepth#(n,0)=15.5 : iCarryDepth#(n,1)=15
 iCarryAnim(n,0)=0 : iCarryAnim(n,1)=0  
 iElevate#(n)=5.0 : iHeight#(n)=17
 iDamage(n)=250: iFlammable(n)=0 : iExplodable(n)=0
 ;announcer's desk
 n=3 : iName$(n)="Announce Desk" : iFile$(n)="Items/Desk.3DS"
 iSound(n)=sSmashWood : iTexture(n)=0 : iShine#(n)=0
 iSizeX#(n)=11 : iSizeZ#(n)=6
 iCarryY#(n,0)=-17.5 : iCarryY#(n,1)=-5
 iCarryA#(n,0)=0 : iCarryA#(n,1)=0 
 iCarryDepth#(n,0)=14.5 : iCarryDepth#(n,1)=17
 iCarryAnim(n,0)=0 : iCarryAnim(n,1)=0  
 iElevate#(n)=6.5 : iHeight#(n)=18
 iDamage(n)=100 : iFlammable(n)=1 : iExplodable(n)=0
 ;cardboard box
 n=4 : iName$(n)="Cardboard Box" : iFile$(n)="Items/Crate.3DS"
 iSound(n)=sSmashCard : iTexture(n)=0 : iShine#(n)=0
 iSizeX#(n)=7 : iSizeZ#(n)=7
 iCarryY#(n,0)=-17 : iCarryY#(n,1)=-4
 iCarryA#(n,0)=0 : iCarryA#(n,1)=45 
 iCarryDepth#(n,0)=15 : iCarryDepth#(n,1)=15.0
 iCarryAnim(n,0)=0 : iCarryAnim(n,1)=0 
 iElevate#(n)=5.0 : iHeight#(n)=19.5
 iDamage(n)=50 : iFlammable(n)=1 : iExplodable(n)=0
 ;wooden crate
 n=5 : iName$(n)="Wooden Crate" : iFile$(n)="Items/Crate.3DS"
 iSound(n)=sSmashWood : iTexture(n)=tWood(2) : iShine#(n)=0
 iSizeX#(n)=7 : iSizeZ#(n)=7
 iCarryY#(n,0)=-17 : iCarryY#(n,1)=-4
 iCarryA#(n,0)=0 : iCarryA#(n,1)=45 
 iCarryDepth#(n,0)=15 : iCarryDepth#(n,1)=15.0  
 iCarryAnim(n,0)=0 : iCarryAnim(n,1)=0 
 iElevate#(n)=5.0 : iHeight#(n)=19.5
 iDamage(n)=100 : iFlammable(n)=1 : iExplodable(n)=0
 ;metal crate
 n=6 : iName$(n)="Metal Crate" : iFile$(n)="Items/Crate.3DS"
 iSound(n)=sSmashWire : iTexture(n)=tCage(3) : iShine#(n)=0.5
 iSizeX#(n)=7 : iSizeZ#(n)=7
 iCarryY#(n,0)=-17 : iCarryY#(n,1)=-4
 iCarryA#(n,0)=0 : iCarryA#(n,1)=45
 iCarryDepth#(n,0)=15 : iCarryDepth#(n,1)=15.0
 iCarryAnim(n,0)=0 : iCarryAnim(n,1)=0 
 iElevate#(n)=5.0 : iHeight#(n)=19.5
 iDamage(n)=250 : iFlammable(n)=0 : iExplodable(n)=0
 ;monitor
 n=7 : iName$(n)="Monitor" : iFile$(n)="Items/Monitor.3DS"
 iSound(n)=sSmashElectric : iTexture(n)=0 : iShine#(n)=0.25
 iSizeX#(n)=4 : iSizeZ#(n)=4
 iCarryY#(n,0)=-5 : iCarryY#(n,1)=-3
 iCarryA#(n,0)=0 : iCarryA#(n,1)=0
 iCarryDepth#(n,0)=11 : iCarryDepth#(n,1)=11
 iCarryAnim(n,0)=1 : iCarryAnim(n,1)=1
 iElevate#(n)=5.0 : iHeight#(n)=11
 iDamage(n)=500 : iFlammable(n)=1 : iExplodable(n)=1
 ;trashcan
 n=8 : iName$(n)="Trashcan" : iFile$(n)="Items/Trashcan.3DS"
 iSound(n)=sSmashWire : iTexture(n)=0 : iShine#(n)=0.25
 iSizeX#(n)=4 : iSizeZ#(n)=4
 iCarryY#(n,0)=-15 : iCarryY#(n,1)=-3
 iCarryA#(n,0)=0 : iCarryA#(n,1)=0
 iCarryDepth#(n,0)=12 : iCarryDepth#(n,1)=10
 iCarryAnim(n,0)=1 : iCarryAnim(n,1)=1
 iElevate#(n)=4.0 : iHeight#(n)=19
 iDamage(n)=250 : iFlammable(n)=0 : iExplodable(n)=0
 ;plastic chair
 n=9 : iName$(n)="Plastic Chair" : iFile$(n)="Items/Chair.3DS"
 iSound(n)=sSmashPlastic : iTexture(n)=0 : iShine#(n)=0.25
 iSizeX#(n)=4 : iSizeZ#(n)=5
 iCarryY#(n,0)=-11 : iCarryY#(n,1)=-1
 iCarryA#(n,0)=90 : iCarryA#(n,1)=90
 iCarryDepth#(n,0)=12.0 : iCarryDepth#(n,1)=12.0
 iCarryAnim(n,0)=1 : iCarryAnim(n,1)=1 
 iElevate#(n)=3.5 : iHeight#(n)=11
 iDamage(n)=100 : iFlammable(n)=1 : iExplodable(n)=0
 ;metal ladder
 n=10 : iName$(n)="Metal Ladder" : iFile$(n)="Items/Ladder.3DS"
 iSound(n)=sSmashMetal : iTexture(n)=0 : iShine#(n)=0.5
 iSizeX#(n)=6 : iSizeZ#(n)=4
 iCarryY#(n,0)=-20 : iCarryY#(n,1)=-2
 iCarryA#(n,0)=0 : iCarryA#(n,1)=0
 iCarryDepth#(n,0)=13 : iCarryDepth#(n,1)=11
 iCarryAnim(n,0)=1 : iCarryAnim(n,1)=0
 iElevate#(n)=4.0 : iHeight#(n)=36
 iDamage(n)=250 : iFlammable(n)=0 : iExplodable(n)=0
 ;wooden ladder
 n=11 : iName$(n)="Wooden Ladder" : iFile$(n)="Items/Ladder.3DS"
 iSound(n)=sSmashWood : iTexture(n)=tWood(1) : iShine#(n)=0
 iSizeX#(n)=6 : iSizeZ#(n)=4
 iCarryY#(n,0)=-20 : iCarryY#(n,1)=-2
 iCarryA#(n,0)=0 : iCarryA#(n,1)=0
 iCarryDepth#(n,0)=13 : iCarryDepth#(n,1)=11
 iCarryAnim(n,0)=1 : iCarryAnim(n,1)=0
 iElevate#(n)=4.0 : iHeight#(n)=36
 iDamage(n)=100 : iFlammable(n)=1 : iExplodable(n)=0
 ;barrier
 n=12 : iName$(n)="Barrier" : iFile$(n)="Items/Railing.3DS"
 iSound(n)=sSmashMetal : iTexture(n)=0 : iShine#(n)=0.25
 iSizeX#(n)=8 : iSizeZ#(n)=4
 iCarryY#(n,0)=-15 : iCarryY#(n,1)=-4
 iCarryA#(n,0)=0 : iCarryA#(n,1)=0
 iCarryDepth#(n,0)=10.0 : iCarryDepth#(n,1)=11.0
 iCarryAnim(n,0)=0 : iCarryAnim(n,1)=0 
 iElevate#(n)=5.0 : iHeight#(n)=19
 iDamage(n)=250 : iFlammable(n)=1 : iExplodable(n)=0
 ;mdickie logo
 n=13 : iName$(n)="MDickie Logo" : iFile$(n)="Items/Logo.3DS"
 iSound(n)=sSmashMetal : iTexture(n)=0 : iShine#(n)=0.5
 iSizeX#(n)=10 : iSizeZ#(n)=3
 iCarryY#(n,0)=-18 : iCarryY#(n,1)=-4
 iCarryA#(n,0)=0 : iCarryA#(n,1)=0
 iCarryDepth#(n,0)=7.0 : iCarryDepth#(n,1)=11.0
 iCarryAnim(n,0)=0 : iCarryAnim(n,1)=0 
 iElevate#(n)=5.0 : iHeight#(n)=21
 iDamage(n)=250 : iFlammable(n)=1 : iExplodable(n)=0
 ;glass pane
 n=14 : iName$(n)="Glass Pane" : iFile$(n)="Items/Glass.3DS"
 iSound(n)=sSmashGlass : iTexture(n)=0 : iShine#(n)=0.5
 iSizeX#(n)=6 : iSizeZ#(n)=2
 iCarryY#(n,0)=-16 : iCarryY#(n,1)=0
 iCarryA#(n,0)=0 : iCarryA#(n,1)=0
 iCarryDepth#(n,0)=7.0 : iCarryDepth#(n,1)=0
 iCarryAnim(n,0)=0 : iCarryAnim(n,1)=-1
 iElevate#(n)=2.0 : iHeight#(n)=25
 iDamage(n)=500 : iFlammable(n)=0 : iExplodable(n)=0
 ;steps
 n=15 : iName$(n)="Step" : iFile$(n)="Items/Steps.3DS"
 iSound(n)=sSmashMetal : iTexture(n)=0 : iShine#(n)=0.25
 iSizeX#(n)=8 : iSizeZ#(n)=10
 iCarryY#(n,0)=-10 : iCarryY#(n,1)=-2
 iCarryA#(n,0)=90 : iCarryA#(n,1)=90
 iCarryDepth#(n,0)=18 : iCarryDepth#(n,1)=16.0
 iCarryAnim(n,0)=0 : iCarryAnim(n,1)=0 
 iElevate#(n)=5.0 : iHeight#(n)=16
 iDamage(n)=250 : iFlammable(n)=0 : iExplodable(n)=0
End Function

;///////////////////////////////////////////////////////
;--------------------- WEAPONS -------------------------
;///////////////////////////////////////////////////////
.Weapons
Global no_weaps=30,weapCount,weapLimit=100,weapList=38
Global weapSelection=1 ;0=random, 1=standard, 2+=specific bias
Global weapLayout=1 ;0=anywhere, 1=standard, 2=mostly backstage, 3=mostly ringside, 4=mostly inside, 5=specific inside
;state
Dim weap(weapLimit)
Dim weapType(weapLimit)
Dim weapX#(weapLimit)
Dim weapY#(weapLimit)
Dim weapZ#(weapLimit)
Dim weapOldX#(weapLimit)
Dim weapOldY#(weapLimit)
Dim weapOldZ#(weapLimit)
Dim weapA#(weapLimit)
Dim weapCarrier(weapLimit)
Dim weapWear(weapLimit) ;0=hand, 1=shoulder, 2=waist
Dim weapThrower(weapLimit)
Dim weapGround#(weapLimit)
Dim weapBounce#(weapLimit)
Dim weapGravity#(weapLimit)
Dim weapFlight#(weapLimit)
Dim weapFlightA#(weapLimit)
Dim weapItemSting(weapLimit,itemLimit)
Dim weapScar(weapLimit)
Dim weapOldScar(weapLimit)
Dim weapBurning(weapLimit)
Dim weapChannel(weapLimit)
Dim weapRelocate(weapLimit)
Dim weapPrimed(weapLimit)
;type
Dim weapName$(weapList)
Dim weapFile$(weapList)
Dim weapSound(weapList)
Dim weapTex(weapList)
Dim weapSize#(weapList)
Dim weapWeight#(weapList)
Dim weapDamage(weapList)
Dim weapFlammable(weapList)
Dim weapExplodable(weapList)
Dim weapShine#(weapList)
Dim weapHold(weapList) ;0=fist, 1=swing, 2=stab, 3=broad
Dim weapStandard(weapList) ;-1=never use, 0=rarely use, 1=often use
Dim weapValue(weapList)
;DATA
Function LoadWeaponData()
 weapName$(0)="Thing"
 ;wooden plank
 n=1
 weapName$(n)="Wooden Plank" : weapFile$(n)="Plank"
 weapSound(n)=sImpactWood : weapTex(n)=tWood(1) : weapShine#(n)=0
 weapSize#(n)=11 : weapWeight#(n)=0.25 : weapDamage(n)=75
 weapHold(n)=1 : weapFlammable(n)=1 : weapExplodable(n)=0
 weapStandard(n)=1 : weapValue(n)=25
 ;baseball bat
 n=2
 weapName$(n)="Baseball Bat" : weapFile$(n)="Bat"
 weapSound(n)=sImpactWood : weapTex(n)=0 : weapShine#(n)=0
 weapSize#(n)=9 : weapWeight#(n)=0.25 : weapDamage(n)=75
 weapHold(n)=1 : weapFlammable(n)=1 : weapExplodable(n)=0
 weapStandard(n)=0 : weapValue(n)=50
 ;wooden cane
 n=3
 weapName$(n)="Wooden Cane" : weapFile$(n)="Cane"
 weapSound(n)=sImpactCane : weapTex(n)=0 : weapShine#(n)=0
 weapSize#(n)=15 : weapWeight#(n)=0.225 : weapDamage(n)=50
 weapHold(n)=1 : weapFlammable(n)=1 : weapExplodable(n)=0
 weapStandard(n)=0 : weapValue(n)=50
 ;metal pipe
 n=4
 weapName$(n)="Metal Pipe" : weapFile$(n)="Pipe"
 weapSound(n)=sImpactMetal : weapTex(n)=tMetal(1) : weapShine#(n)=1
 weapSize#(n)=9 : weapWeight#(n)=0.25 : weapDamage(n)=100
 weapHold(n)=1 : weapFlammable(n)=0 : weapExplodable(n)=0
 weapStandard(n)=1 : weapValue(n)=25
 ;microphone
 n=5
 weapName$(n)="Microphone" : weapFile$(n)="Mic"
 weapSound(n)=sImpactMic : weapTex(n)=0 : weapShine#(n)=0
 weapSize#(n)=4 : weapWeight#(n)=0.2 : weapDamage(n)=50
 weapHold(n)=0 : weapFlammable(n)=1 : weapExplodable(n)=0
 weapStandard(n)=1 : weapValue(n)=100
 ;sword
 n=6
 weapName$(n)="Samurai Sword" : weapFile$(n)="Sword"
 weapSound(n)=sImpactBlade : weapTex(n)=0 : weapShine#(n)=1
 weapSize#(n)=13 : weapWeight#(n)=0.3 : weapDamage(n)=150
 weapHold(n)=1 : weapFlammable(n)=1 : weapExplodable(n)=0
 weapStandard(n)=0 : weapValue(n)=250
 ;dagger
 n=7
 weapName$(n)="Dagger" : weapFile$(n)="Dagger"
 weapSound(n)=sImpactBlade : weapTex(n)=0 : weapShine#(n)=1
 weapSize#(n)=6 : weapWeight#(n)=0.25 : weapDamage(n)=125
 weapHold(n)=2 : weapFlammable(n)=1 : weapExplodable(n)=0
 weapStandard(n)=0 : weapValue(n)=100
 ;hammer
 n=8
 weapName$(n)="Hammer" : weapFile$(n)="Hammer"
 weapSound(n)=sImpactHammer : weapTex(n)=0 : weapShine#(n)=0.25
 weapSize#(n)=5 : weapWeight#(n)=0.35 : weapDamage(n)=125
 weapHold(n)=1 : weapFlammable(n)=1 : weapExplodable(n)=0
 weapStandard(n)=1 : weapValue(n)=50
 ;bell
 n=9
 weapName$(n)="Ring Bell" : weapFile$(n)="Bell"
 weapSound(n)=sImpactBell : weapTex(n)=0 : weapShine#(n)=1
 weapSize#(n)=4 : weapWeight#(n)=0.35 : weapDamage(n)=100
 weapHold(n)=0 : weapFlammable(n)=0 : weapExplodable(n)=0
 weapStandard(n)=1 : weapValue(n)=100
 ;brass knucks
 n=10
 weapName$(n)="Brass Knucks" : weapFile$(n)="Knucks"
 weapSound(n)=sImpactBlade : weapTex(n)=0 : weapShine#(n)=1
 weapSize#(n)=4 : weapWeight#(n)=0.3 : weapDamage(n)=50
 weapHold(n)=0 : weapFlammable(n)=0 : weapExplodable(n)=0 
 weapStandard(n)=1 : weapValue(n)=50
 ;trophy
 n=11
 weapName$(n)="Trophy" : weapFile$(n)="Trophy"
 weapSound(n)=sImpactMetal : weapTex(n)=0 : weapShine#(n)=1
 weapSize#(n)=5 : weapWeight#(n)=0.3 : weapDamage(n)=100
 weapHold(n)=0 : weapFlammable(n)=0 : weapExplodable(n)=0
 weapStandard(n)=-1 : weapValue(n)=1000
 ;camera
 n=12
 weapName$(n)="Video Camera" : weapFile$(n)="Camera"
 weapSound(n)=sImpactGun : weapTex(n)=0 : weapShine#(n)=0
 weapSize#(n)=5 : weapWeight#(n)=0.35 : weapDamage(n)=100
 weapHold(n)=0 : weapFlammable(n)=1 : weapExplodable(n)=1
 weapStandard(n)=1 : weapValue(n)=250
 ;tape
 n=13
 weapName$(n)="Video Tape" : weapFile$(n)="Box"
 weapSound(n)=sImpactCane : weapTex(n)=tTape : weapShine#(n)=0
 weapSize#(n)=4 : weapWeight#(n)=0.2 : weapDamage(n)=50
 weapHold(n)=0 : weapFlammable(n)=1 : weapExplodable(n)=0
 weapStandard(n)=1 : weapValue(n)=25
 ;game
 n=14
 weapName$(n)="Game Box" : weapFile$(n)="Box"
 weapSound(n)=sBlock(6) : weapTex(n)=tGame : weapShine#(n)=0
 weapSize#(n)=4 : weapWeight#(n)=0.2 : weapDamage(n)=50
 weapHold(n)=0 : weapFlammable(n)=1 : weapExplodable(n)=0 
 weapStandard(n)=1 : weapValue(n)=25
 ;briefcase
 n=15
 weapName$(n)="Briefcase" : weapFile$(n)="Case"
 weapSound(n)=sBlock(7) : weapTex(n)=0 : weapShine#(n)=0
 weapSize#(n)=6 : weapWeight#(n)=0.3 : weapDamage(n)=75
 weapHold(n)=0 : weapFlammable(n)=1 : weapExplodable(n)=0
 weapStandard(n)=1 : weapValue(n)=100 
 ;water bottle
 n=16
 weapName$(n)="Water Bottle" : weapFile$(n)="Bottle"
 weapSound(n)=sImpactBottle : weapTex(n)=tBottle(1) : weapShine#(n)=0
 weapSize#(n)=4 : weapWeight#(n)=0.2 : weapDamage(n)=50
 weapHold(n)=0 : weapFlammable(n)=0 : weapExplodable(n)=3
 weapStandard(n)=1 : weapValue(n)=25 
 ;beer bottle
 n=17
 weapName$(n)="Beer Bottle" : weapFile$(n)="Bottle"
 weapSound(n)=sImpactBottle : weapTex(n)=tBottle(2) : weapShine#(n)=0
 weapSize#(n)=4 : weapWeight#(n)=0.2 : weapDamage(n)=50
 weapHold(n)=0 : weapFlammable(n)=1 : weapExplodable(n)=4
 weapStandard(n)=1 : weapValue(n)=25
 ;fire extinguisher
 n=18
 weapName$(n)="Fire Extinguisher" : weapFile$(n)="Exting"
 weapSound(n)=sImpactMetal : weapTex(n)=0 : weapShine#(n)=0.25
 weapSize#(n)=6 : weapWeight#(n)=0.3 : weapDamage(n)=100
 weapHold(n)=0 : weapFlammable(n)=0 : weapExplodable(n)=2
 weapStandard(n)=1 : weapValue(n)=100
 ;TNT
 n=19
 weapName$(n)="Explosive" : weapFile$(n)="TNT"
 weapSound(n)=sItem : weapTex(n)=0 : weapShine#(n)=0
 weapSize#(n)=6 : weapWeight#(n)=0.25 : weapDamage(n)=50
 weapHold(n)=0 : weapFlammable(n)=1 : weapExplodable(n)=1
 weapStandard(n)=0 : weapValue(n)=500
 ;handgun
 n=20
 weapName$(n)="Handgun" : weapFile$(n)="Gun"
 weapSound(n)=sImpactGun : weapTex(n)=0 : weapShine#(n)=1
 weapSize#(n)=4 : weapWeight#(n)=0.4 : weapDamage(n)=150
 weapHold(n)=0 : weapFlammable(n)=0 : weapExplodable(n)=1
 weapStandard(n)=0 : weapValue(n)=250
 ;world title
 n=21
 weapName$(n)="World Title" : weapFile$(n)="Belt"
 weapSound(n)=sBlock(7) : weapTex(n)=tBelt(1) : weapShine#(n)=1
 weapSize#(n)=7 : weapWeight#(n)=0.3 : weapDamage(n)=100
 weapHold(n)=0 : weapFlammable(n)=1 : weapExplodable(n)=0 
 weapStandard(n)=-1 : weapValue(n)=1000
 ;inter title
 n=22
 weapName$(n)="Inter Title" : weapFile$(n)="Belt"
 weapSound(n)=sBlock(7) : weapTex(n)=tBelt(2) : weapShine#(n)=1
 weapSize#(n)=7 : weapWeight#(n)=0.3 : weapDamage(n)=100
 weapHold(n)=0 : weapFlammable(n)=1 : weapExplodable(n)=0 
 weapStandard(n)=-1 : weapValue(n)=1000
 ;tag title
 n=23
 weapName$(n)="Tag Title" : weapFile$(n)="Belt"
 weapSound(n)=sBlock(7) : weapTex(n)=tBelt(3) : weapShine#(n)=1
 weapSize#(n)=7 : weapWeight#(n)=0.3 : weapDamage(n)=100
 weapHold(n)=0 : weapFlammable(n)=1 : weapExplodable(n)=0  
 weapStandard(n)=-1 : weapValue(n)=1000
 ;wooden board
 n=24
 weapName$(n)="Wooden Board" : weapFile$(n)="Board"
 weapSound(n)=sSmashWood : weapTex(n)=tWood(2) : weapShine#(n)=0
 weapSize#(n)=10 : weapWeight#(n)=0.275 : weapDamage(n)=100
 weapHold(n)=3 : weapFlammable(n)=0 : weapExplodable(n)=0
 weapStandard(n)=1 : weapValue(n)=75 
 ;metal sheet
 n=25
 weapName$(n)="Metal Sheet" : weapFile$(n)="Board"
 weapSound(n)=sSmashMetal : weapTex(n)=tMetal(1) : weapShine#(n)=1
 weapSize#(n)=10 : weapWeight#(n)=0.3 : weapDamage(n)=150
 weapHold(n)=3 : weapFlammable(n)=0 : weapExplodable(n)=0
 weapStandard(n)=1 : weapValue(n)=100
 ;steel chair
 n=26
 weapName$(n)="Steel Chair" : weapFile$(n)="Board"
 weapSound(n)=sSmashMetal : weapTex(n)=tChair : weapShine#(n)=1
 weapSize#(n)=10 : weapWeight#(n)=0.3 : weapDamage(n)=150
 weapHold(n)=3 : weapFlammable(n)=0 : weapExplodable(n)=0
 weapStandard(n)=2 : weapValue(n)=150
 ;cage piece
 n=27
 weapName$(n)="Cage Piece" : weapFile$(n)="Board"
 weapSound(n)=sSmashWire : weapTex(n)=tCage(3) : weapShine#(n)=1
 weapSize#(n)=10 : weapWeight#(n)=0.275 : weapDamage(n)=125
 weapHold(n)=3 : weapFlammable(n)=0 : weapExplodable(n)=0
 weapStandard(n)=0 : weapValue(n)=125
 ;thumbtacks
 n=28
 weapName$(n)="Thumbtacks" : weapFile$(n)="Bag"
 weapSound(n)=sImpactTacks : weapTex(n)=0 : weapShine#(n)=0
 weapSize#(n)=8 : weapWeight#(n)=0.25 : weapDamage(n)=50
 weapHold(n)=0 : weapFlammable(n)=0 : weapExplodable(n)=0
 weapStandard(n)=0 : weapValue(n)=25
 ;scissors
 n=29
 weapName$(n)="Scissors" : weapFile$(n)="Scissors"
 weapSound(n)=sImpactBlade : weapTex(n)=0 : weapShine#(n)=1.0
 weapSize#(n)=4 : weapWeight#(n)=0.2 : weapDamage(n)=100
 weapHold(n)=2 : weapFlammable(n)=0 : weapExplodable(n)=0
 weapStandard(n)=0 : weapValue(n)=50
 ;meat cleaver
 n=30
 weapName$(n)="Meat Cleaver" : weapFile$(n)="Cleaver"
 weapSound(n)=sImpactBlade : weapTex(n)=0 : weapShine#(n)=1.0
 weapSize#(n)=7 : weapWeight#(n)=0.3 : weapDamage(n)=150
 weapHold(n)=1 : weapFlammable(n)=1 : weapExplodable(n)=0
 weapStandard(n)=0 : weapValue(n)=100
 ;nightstick
 n=31
 weapName$(n)="Nightstick" : weapFile$(n)="Baton"
 weapSound(n)=sImpactWood : weapTex(n)=0 : weapShine#(n)=0.25
 weapSize#(n)=6 : weapWeight#(n)=0.25 : weapDamage(n)=75
 weapHold(n)=1 : weapFlammable(n)=1 : weapExplodable(n)=0
 weapStandard(n)=0 : weapValue(n)=50
 ;dumbbell
 n=32
 weapName$(n)="Dumbbell" : weapFile$(n)="Dumbell"
 weapSound(n)=sImpactHammer : weapTex(n)=0 : weapShine#(n)=0.25
 weapSize#(n)=6 : weapWeight#(n)=0.5 : weapDamage(n)=150
 weapHold(n)=0 : weapFlammable(n)=0 : weapExplodable(n)=0
 weapStandard(n)=0 : weapValue(n)=50
 ;pool cue
 n=33
 weapName$(n)="Pool Cue" : weapFile$(n)="Cue"
 weapSound(n)=sImpactCane : weapTex(n)=0 : weapShine#(n)=0.25
 weapSize#(n)=15 : weapWeight#(n)=0.225 : weapDamage(n)=50
 weapHold(n)=1 : weapFlammable(n)=1 : weapExplodable(n)=0
 weapStandard(n)=0 : weapValue(n)=50
 ;broom
 n=34
 weapName$(n)="Broom" : weapFile$(n)="Broom"
 weapSound(n)=sImpactCane : weapTex(n)=0 : weapShine#(n)=0
 weapSize#(n)=15 : weapWeight#(n)=0.25 : weapDamage(n)=50
 weapHold(n)=1 : weapFlammable(n)=1 : weapExplodable(n)=0
 weapStandard(n)=1 : weapValue(n)=25
 ;screwdriver
 n=35
 weapName$(n)="Screwdriver" : weapFile$(n)="Screw"
 weapSound(n)=sImpactBlade : weapTex(n)=0 : weapShine#(n)=1.0
 weapSize#(n)=4 : weapWeight#(n)=0.2 : weapDamage(n)=75
 weapHold(n)=2 : weapFlammable(n)=0 : weapExplodable(n)=0
 weapStandard(n)=1 : weapValue(n)=25
 ;syringe
 n=36
 weapName$(n)="Syringe" : weapFile$(n)="Syringe"
 weapSound(n)=sImpactBlade : weapTex(n)=0 : weapShine#(n)=0.5
 weapSize#(n)=4 : weapWeight#(n)=0.175 : weapDamage(n)=75
 weapHold(n)=2 : weapFlammable(n)=0 : weapExplodable(n)=0
 weapStandard(n)=0 : weapValue(n)=100
 ;rock
 n=37
 weapName$(n)="Rock" : weapFile$(n)="Rock"
 weapSound(n)=sImpactHammer : weapTex(n)=0 : weapShine#(n)=0
 weapSize#(n)=4 : weapWeight#(n)=0.3 : weapDamage(n)=100
 weapHold(n)=0 : weapFlammable(n)=0 : weapExplodable(n)=0 
 weapStandard(n)=1 : weapValue(n)=25
 ;brick
 n=38
 weapName$(n)="Brick" : weapFile$(n)="Brick"
 weapSound(n)=sImpactHammer : weapTex(n)=0 : weapShine#(n)=0
 weapSize#(n)=5 : weapWeight#(n)=0.4 : weapDamage(n)=100
 weapHold(n)=0 : weapFlammable(n)=0 : weapExplodable(n)=0 
 weapStandard(n)=1 : weapValue(n)=25
 ;offset weights
 For v=1 To weapList
  weapWeight#(v)=weapWeight#(v)+(weapWeight#(v)/4)
 Next
End Function

;///////////////////////////////////////////////////////
;----------------------- WORLD -------------------------
;///////////////////////////////////////////////////////
.World
Global world
Global wGround#=1.6,wStage#=26.1
Global meetX#,meetZ#,meetA#
;arena variations
Global no_arenas=26,no_atmos=9,no_ropes=22
Global no_aprons=18,no_canvases=10
Global arenaPreset=23,arenaType=2
Global arenaLight=1,arenaAtmos=0
Global arenaAttendance=50,arenaCrowd=14
Dim arenaWall(3),arenaCeiling(3),arenaGround(3)
Global arenaApron=4,arenaCanvas=1
Global arenaPads=1,arenaRopes=11,arenaMatting=2
;ring components
Dim ringComponent(75)
Dim ringX#(75)
Dim ringY#(75)
Dim ringZ#(75)
Dim ringOffsetX#(75)
Dim ringOffsetY#(75)
Dim ringOffsetZ#(75)
Dim ringTX#(75)
Dim ringTY#(75)
Dim ringTZ#(75)
Dim ringSpeedX#(75)
Dim ringSpeedY#(75)
Dim ringSpeedZ#(75)
Dim ringShakeTim(75)
;blocks
Dim blockX1#(100)
Dim blockX2#(100)
Dim blockZ1#(100)
Dim blockZ2#(100)
Dim blockY1#(100)
Dim blockY2#(100)
Dim blockType(100) ;0=inactive, 1=humans only, 2=include camera
Dim blockClimb(100) ;0=never, 1-4=one-sided ledge, 5=double-sided horizontal, 6=doubled-sided vertical, 7=rectangle
Dim blockPlatX1#(100)
Dim blockPlatX2#(100)
Dim blockPlatZ1#(100)
Dim blockPlatZ2#(100)
;videos
Dim videoScreen(10)
Dim videoOldScreen(10)
;ropes
Global ropeFlow#
Dim rope(12)
Dim ropeSeq(12,100)
Dim ropeAnim(12)
Dim ropeAnimTim(12)
Dim ropeAnimSpeed#(12)
Dim ropeX#(12)
Dim ropeY#(12)
Dim ropeZ#(12)
Dim ropeA#(12)
Dim ropeBurning(12)
Dim ropeChannel(12)
Dim ropeSide(12)
ropeSide(1)=1 : ropeSide(5)=1 : ropeSide(9)=1
ropeSide(2)=2 : ropeSide(6)=2 : ropeSide(10)=2
ropeSide(3)=3 : ropeSide(7)=3 : ropeSide(11)=3
ropeSide(4)=4 : ropeSide(8)=4 : ropeSide(12)=4
;pad status
Dim padExposed(4)
;cage
Global cage
Dim cageShakeTim(4)
Dim cageOrigX#(4),cageOrigY#(4),cageOrigZ#(4)
Dim cageX#(4),cageY#(4),cageZ#(4)
Dim cageTX#(4),cageTY#(4),cageTZ#(4)
Dim cageSpeedX#(4),cageSpeedY#(4),cageSpeedZ#(4)
Dim cageOrigXA#(4),cageOrigYA#(4),cageOrigZA#(4)
Dim cageXA#(4),cageYA#(4),cageZA#(4)
Dim cageTXA#(4),cageTYA#(4),cageTZA#(4)
Dim cageSpeedXA#(4),cageSpeedYA#(4),cageSpeedZA#(4)
;crowd
Dim crowdSource#(200)
Dim crowdY#(200)
Dim crowdTY#(200)
;signs
Global no_signs
Dim sign(200)
Dim signX#(200)
Dim signY#(200)
Dim signZ#(200)
Dim signA#(200)
;camera 
Global camListener,dummy
Global cam,camPivot
Global camType,camOption
Global camOldType,camTim
Global camFoc,camOldFoc,camRewardFoc
Global camTempTim,camTempFoc
Global camX#,camY#=75,camZ#
Global camTX#,camTY#,camTZ#
Global camOldX#,camOldY#,camOldZ#
Global camPivX#,camPivY#=100,camPivZ#
Global camPivTX#,camPivTY#,camPivTZ#
Global camA#,camTA#,camSpread#
Global camAccel,camPivAccel
Global camCriteria,camOldCriteria
Global camBlocked
Global camMouseX#,camMouseY#
;smooth co-ordination
Global speedX#,speedY#,speedZ#
Global centreX#,centreY#,centreZ#
;camera names
Dim camName$(20,10) ;0=type, 1-10=variation
n=0 : camName$(n,0)="Manual" ;: camName$(n,1)="(I, J, K, L, Y, H)"
n=1 : camName$(n,0)="Low Follow"
camName$(n,1)="(Close)" : camName$(n,2)="(Average)" : camName$(n,3)="(Far)"
n=2 : camName$(n,0)="Level Follow"
camName$(n,1)="(Close)" : camName$(n,2)="(Average)" : camName$(n,3)="(Far)"
n=3 : camName$(n,0)="Raised Follow"
camName$(n,1)="(Close)" : camName$(n,2)="(Average)" : camName$(n,3)="(Far)"
n=4 : camName$(n,0)="Bird's Eye View"
camName$(n,1)="(Close)" : camName$(n,2)="(Average)" : camName$(n,3)="(Far)"
n=5 : camName$(n,0)="Static"
camName$(n,1)="(North)" : camName$(n,2)="(North East)" : camName$(n,3)="(East)" : camName$(n,4)="(South East)"
camName$(n,5)="(South)" : camName$(n,6)="(South East)" : camName$(n,7)="(West)" : camName$(n,8)="(North West)"
camName$(n,9)="(Rafters)"
n=6 : camName$(n,0)="Fan's View"
camName$(n,1)="(North East)" : camName$(n,2)="(East)" : camName$(n,3)="(South East)"
camName$(n,4)="(South West)" : camName$(n,5)="(West)" : camName$(n,6)="(North West)"
n=7 : camName$(n,0)="Corner View"
camName$(n,1)="(North East)" : camName$(n,2)="(South East)" : camName$(n,3)="(South West)" : camName$(n,4)="(North West)"
n=8 : camName$(n,0)="First Person"
n=9 : camName$(n,0)="Opponent's View"
n=10 : camName$(n,0)="Head Shot"
n=11 : camName$(n,0)="Spontaneous"
n=12 : camName$(n,0)="Head Follow"
;camera presets
Dim camShortcut(10)
camShortcut(1)=1
camShortcut(2)=2
camShortcut(3)=3
camShortcut(4)=4
camShortcut(5)=5
camShortcut(6)=6
camShortcut(7)=7
camShortcut(8)=8
camShortcut(9)=9
camShortcut(10)=11
;lighting
Dim light(10)
Global no_lights,lightPivot
Global lightR#,lightG#,lightB#
Global lightTR#,lightTG#,lightTB#
Global ambR#=100,ambG#=100,ambB#=100
Global ambTR#,ambTG#,ambTB#
Global atmosR#=100,atmosG#=100,atmosB#=100
Global atmosTR#,atmosTG#,atmosTB#

;////////////////////////////////////////////////////////
;--------------- PARTICLE EFFECTS -----------------------
;////////////////////////////////////////////////////////
.Particles
Global fader,fadeAlpha#,fadeTarget#
;particles
Global no_particles=500
Dim part(1000)
Dim partType(1000)
Dim partTexture(1000)
Dim partX#(1000)
Dim partY#(1000)
Dim partZ#(1000)
Dim partA#(1000)
Dim partGravity#(1000)
Dim partWeight#(1000)
Dim partFlight#(1000)
Dim partSize#(1000)
Dim partAlpha#(1000)
Dim partFade#(1000)
Dim partTim(1000)
Dim partState(1000)
;explosions
Global no_explodes=10
Dim exType(no_explodes)
Dim exX#(no_explodes)
Dim exY#(no_explodes)
Dim exZ#(no_explodes)
Dim exTim(no_explodes)
Dim exSting(no_explodes,optPlayLim)
;pools
Global no_pools=50
Dim pool(no_pools)
Dim poolType(no_pools)
Dim poolX#(no_pools)
Dim poolY#(no_pools)
Dim poolZ#(no_pools)
Dim poolA#(no_pools)
Dim poolSize#(no_pools)
Dim poolAlpha#(no_pools)
Dim poolState(no_pools)