;//////////////////////////////////////////////////////////////////////////////
;-------------------------- WRESTLING MPIRE 2008: TEXTS -----------------------
;//////////////////////////////////////////////////////////////////////////////

;//////////////////////////////////////////////////////
;------------------- KEY NAMES ------------------------
;//////////////////////////////////////////////////////
Dim Key$(255)
For count=0 To 255
 Key$(count)="?" 
Next
Key$(2)="1" : Key$(3)="2" : Key$(4)="3" : Key$(5)="4" : Key$(6)="5"
Key$(7)="6" : Key$(8)="7" : Key$(9)="8" : Key$(10)="9" : Key$(11)="0"
Key$(12)="-" : Key$(13)="+" : Key$(14)="Backspace" : Key$(15)="Tab"
Key$(16)="Q" : Key$(17)="W" : Key$(18)="E" : Key$(19)="R" : Key$(20)="T" : Key$(21)="Y"
Key$(22)="U" : Key$(23)="I" : Key$(24)="O" : Key$(25)="P" : Key$(26)="[" : Key$(27)="]"
Key$(29)="Left Ctrl" : Key$(30)="A" : Key$(31)="S" : Key$(32)="D" : Key$(33)="F" : Key$(34)="G"
Key$(35)="H" : Key$(36)="J" : Key$(37)="K" : Key$(38)="L" : Key$(39)=";" : Key$(40)="'" : Key$(41)="#"
Key$(42)="Left Shift" : Key$(43)="\" : Key$(44)="Z" : Key$(45)="X" : Key$(46)="C" : Key$(47)="V"
Key$(48)="B" : Key$(49)="N" : Key$(50)="M" : Key$(51)="," : Key$(52)="." : Key$(53)="/"
Key$(54)="Right Shift" : Key$(56)="Left Alt" : Key$(157)="Right Ctrl" : Key$(184)="Right Alt"
Key$(57)="Space" : Key$(200)="Cursor Up" : Key$(208)="Cursor Down" 
Key$(203)="Cursor Left" : Key$(205)="Cursor Right"

;//////////////////////////////////////////////////////
;-------------------- STATUS --------------------------
;//////////////////////////////////////////////////////
;numbers
Dim textNumber$(100)
For count=1 To 100
 textNumber$(count)=count+"th" 
Next
textNumber$(1)="1st"
textNumber$(2)="2nd"
textNumber$(3)="3rd"
;weeks
Dim textWeek$(4)
textWeek$(1)="1st week"
textWeek$(2)="2nd week"
textWeek$(3)="3rd week"
textWeek$(4)="4th week"
;months
Dim textMonth$(12)
textMonth$(1)="January"
textMonth$(2)="February"
textMonth$(3)="March"
textMonth$(4)="April"
textMonth$(5)="May"
textMonth$(6)="June"
textMonth$(7)="July"
textMonth$(8)="August"
textMonth$(9)="September"
textMonth$(10)="October"
textMonth$(11)="November"
textMonth$(12)="December"
;events
Dim textEvent$(10)
textEvent$(1)="TV Taping"
textEvent$(2)="Pay-Per-View" 
textEvent$(3)="Tournament"
textEvent$(4)="Inter-Promotional Contest"
textEvent$(5)="Charity Event" 
textEvent$(6)="Memorial Show"
;contract clauses
Dim textClause$(4,2)
textClause$(1,0)="No say"
textClause$(1,1)="Must be heard"
textClause$(1,2)="Full control!"
textClause$(2,0)="Not paid if not used"
textClause$(2,1)="Paid 50% less if not used"
textClause$(2,2)="Unconditional pay!"
textClause$(3,0)="No compensation"
textClause$(3,1)="Basic compensation"
textClause$(3,2)="Full compensation!"
;race
Dim textRace$(5)
textRace$(0)="white"
textRace$(1)="white"
textRace$(2)="Asian"
textRace$(3)="black"
;injuries
Dim textInjury$(5)
textInjury$(0)=""
textInjury$(1)="hand"
textInjury$(2)="arm"
textInjury$(3)="rib"
textInjury$(4)="leg"
textInjury$(5)="head"
;training courses
Dim textTrainCourse$(6)
textTrainCourse$(0)="Rest"
textTrainCourse$(1)="Strength"
textTrainCourse$(2)="Skill"
textTrainCourse$(3)="Agility"
textTrainCourse$(4)="Stamina"
textTrainCourse$(5)="Toughness"
textTrainCourse$(6)="Everything"
;training levels
Dim textTrainLevel$(3)
textTrainLevel$(0)="None"
textTrainLevel$(1)="Casual"
textTrainLevel$(2)="Average"
textTrainLevel$(3)="Intense"
;court issues
Dim textCourtCase$(20)
textCourtCase$(1)="Sexual Harassment"
textCourtCase$(2)="Unfair Dismissal"
textCourtCase$(3)="False Imprisonment"
textCourtCase$(4)="Career Sabotage"
textCourtCase$(5)="Bodily Harm"
textCourtCase$(6)="Drug Abuse"
textCourtCase$(7)="Fraud"
textCourtCase$(8)="Irresponsible Broadcasting"
textCourtCase$(9)="Plaguerism"
;reviews
Dim textReview$(5,5)
textReview$(1,1)="A terrible" : textReview$(2,1)="A pathetic" : textReview$(3,1)="An awful" : textReview$(4,1)="A pitiful" : textReview$(5,1)="A laughable" 
textReview$(1,2)="A disappointing" : textReview$(2,2)="A forgettable" : textReview$(3,2)="A poor" : textReview$(4,2)="A weak" : textReview$(5,2)="A flawed"
textReview$(1,3)="A decent" : textReview$(2,3)="A solid" : textReview$(3,3)="An average" : textReview$(4,3)="An alright" : textReview$(5,3)="A fine"
textReview$(1,4)="A great" : textReview$(2,4)="A good" : textReview$(3,4)="A nice" : textReview$(4,4)="An absorbing" : textReview$(5,4)="An entertaining"
textReview$(1,5)="A fantastic" : textReview$(2,5)="A mesmerizing" : textReview$(3,5)="An excellent" : textReview$(4,5)="A superb" : textReview$(5,5)="An outstanding"
;gender contexts
Global g
Dim He$(1)
He$(0)="He"
He$(1)="She"
Dim Him$(1)
Him$(0)="him"
Him$(1)="her"
Dim His$(1)
His$(0)="His"
His$(1)="Her"
Dim Guy$(1)
Guy$(0)="guy"
Guy$(1)="gal"
Dim Man$(1)
Man$(0)="man"
Man$(1)="woman"

;//////////////////////////////////////////////////////
;------------------- OPTIONS --------------------------
;//////////////////////////////////////////////////////
;PREFERENCES
;on/off
Dim textOnOff$(1)
textOnOff$(0)="Off"
textOnOff$(1)="On"
;CPU difficulty
Dim textLevel$(6)
textLevel$(0)="Extremely Easy"
textLevel$(1)="Very Easy"
textLevel$(2)="Easy"
textLevel$(3)="Average"
textLevel$(4)="Hard"
textLevel$(5)="Very Hard"
textLevel$(6)="Extremely Hard"
;ratings difficulty
Dim textRatingsLevel$(5)
textRatingsLevel$(0)="Very Easy"
textRatingsLevel$(1)="Easy"
textRatingsLevel$(2)="Average"
textRatingsLevel$(3)="Hard"
textRatingsLevel$(4)="Very Hard"
;match length
Dim textLength$(3)
textLength$(1)="Short"
textLength$(2)="Average"
textLength$(3)="Long"
;game speed
Dim textSpeed$(5)
textSpeed$(1)="Normal"
textSpeed$(2)="Swift"
textSpeed$(3)="Fast"
textSpeed$(4)="Turbo!"
;entrances
Dim textEntrances$(2)
textEntrances$(0)="Never Used"
textEntrances$(1)="Only In Small Matches"
textEntrances$(2)="Always Used"
;referees
Dim textReferees$(5)
textReferees$(0)="Never Present"
textReferees$(1)="Only In Small Matches"
textReferees$(2)="Only In Standard Matches"
textReferees$(3)="Always Present"
;managers
Dim textManagers$(5)
textManagers$(0)="Never Present"
textManagers$(1)="Only In Small Matches"
textManagers$(2)="Only In Standard Matches"
textManagers$(3)="Always Present"
;intruders
Dim textIntruders$(5)
textIntruders$(0)="Never Possible"
textIntruders$(1)="Only In Small Matches"
textIntruders$(2)="Only In Standard Matches"
textIntruders$(3)="Always Possible"
;eliminated wrestlers
Dim textEliminations$(2)
textEliminations$(0)="Never Remove"
textEliminations$(1)="Remove From Large Matches"
textEliminations$(2)="Always Remove"
;tag control
Dim textTagControl$(2)
textTagControl$(0)="Specific Wrestler"
textTagControl$(1)="Whole Team"
;DISPLAY SETTINGS
;resolution
Global no_resolutions=10
Dim textResX$(no_resolutions),textResY$(no_resolutions)
textResX$(0)="320" : textResY$(0)="240"
textResX$(1)="640" : textResY$(1)="480"
textResX$(2)="800" : textResY$(2)="600"
textResX$(3)="1024" : textResY$(3)="768"
textResX$(4)="1280" : textResY$(4)="800"
textResX$(5)="1280" : textResY$(5)="1024"
textResX$(6)="1366" : textResY$(6)="768"
textResX$(7)="1600" : textResY$(7)="900"
textResX$(8)="1600" : textResY$(8)="1200"
textResX$(9)="1900" : textResY$(9)="1200"
textResX$(10)="1920" : textResY$(10)="1080"
;arena detail
Dim textDetail$(3)
textDetail$(0)="Minimal"
textDetail$(1)="Moderate"
textDetail$(2)="Maximum"
;crowd animation
Dim textCrowdAnim$(2)
textCrowdAnim$(0)="Static"
textCrowdAnim$(1)="Animated"
textCrowdAnim$(2)="Animated With Signs"
;shadows
Dim textShadows$(3)
textShadows$(0)="None"
textShadows$(1)="Basic"
textShadows$(2)="Animated"
;particle effects
Dim textFX$(3)
textFX$(0)="None"
textFX$(1)="Minimum"
textFX$(2)="Maximum"
;gore
Dim textGore$(4)
textGore$(0)="None"
textGore$(1)="Scarring"
textGore$(2)="Blood Spurts"
textGore$(3)="Severed Limbs"
textGore$(4)="Bloodbath!"
;health meters
Dim textMeters$(3)
textMeters$(0)="None"
textMeters$(1)="Show Essentials"
textMeters$(2)="Show All"
;entertainment display
Dim textEntertainment$(4)
textEntertainment$(0)="None"
textEntertainment$(1)="Icons Only"
textEntertainment$(2)="Icons & Points"
textEntertainment$(3)="All Details"
;character selection
Dim textGrid$(2)
textGrid$(0)="List Of Names"
textGrid$(1)="Grid Of Names"

;//////////////////////////////////////////////////////
;------------------- MATCHES --------------------------
;//////////////////////////////////////////////////////
;match types
Dim textMatch$(30)
textMatch$(0)="Backstage Brawl"
textMatch$(1)="Confrontation"
textMatch$(2)="Traditional Match"
textMatch$(3)="Best Of Three Match"
textMatch$(4)="Iron Man Match"
textMatch$(5)="24/7 Challenge"
textMatch$(6)="Submission Match"
textMatch$(7)="Shoot Fight"
textMatch$(8)="Last Man Standing"
textMatch$(9)="First Blood Match"
textMatch$(10)="Triple Threat Match"
textMatch$(11)="Handicap Match"
textMatch$(12)="Tag Team Match"
textMatch$(13)="Team Battle"
textMatch$(14)="Royal Brawl"
textMatch$(15)="Battle Royal"
textMatch$(16)="Timed Battle Royal"
textMatch$(17)="Sumo Match"
textMatch$(18)="Escape Challenge"
textMatch$(19)="Six-Man Tag"
textMatch$(20)="Eight-Man Elimination"
;match gimmicks
Dim textGimmick$(20)
textGimmick$(0)="None"
textGimmick$(1)="Hardcore"
textGimmick$(2)="Steel Cage"
textGimmick$(3)="Barbed Wire"
textGimmick$(4)="Electrified"
textGimmick$(5)="Inferno"
textGimmick$(6)="Exploding"
textGimmick$(7)="Minefield"
textGimmick$(8)="Hall Of Mirrors"
textGimmick$(9)="Hair Vs Hair"
textGimmick$(10)="Loser Leaves Town"
textGimmick$(11)="Empty Arena"
textGimmick$(12)="Race Against Time"
textGimmick$(13)="Multiple Referee"
;count/ignore stipulation
Dim textCount$(1)
textCount$(0)="Ignore"
textCount$(1)="Count"
;match type
Dim textAim$(5)
textAim$(0)="Aimless"
textAim$(1)="First Fall Wins"
textAim$(2)="Best Of Three"
textAim$(3)="Most Falls Wins"
textAim$(4)="Last Fall Wins"
textAim$(5)="Elimination"
;match rules
Dim textRules$(5)
textRules$(0)="Hardcore"
textRules$(1)="Lenient"
textRules$(2)="Strict"
;count-out's
Dim textCountOuts$(5)
textCountOuts$(0)="Ignore"
textCountOuts$(1)="Swift 10 Count"
textCountOuts$(2)="Slow 10 Count"
textCountOuts$(3)="Instant Elimination"
;teams
Dim textTeams$(2)
textTeams$(0)="Individuals"
textTeams$(1)="Teams"
textTeams$(2)="Tag Teams"
;rewards
Dim textReward$(10)
textReward$(0)="Ignore Titles"
textReward$(1)="Acknowledge Titles"
textReward$(2)="New World Champion"
textReward$(3)="New Inter Champion"
textReward$(4)="New Tag Champions"
textReward$(5)="Trophy"
textReward$(6)="Hair Vs Hair"
textReward$(7)="Loser Leaves Town"
;location
Dim textLocation$(2)
textLocation$(0)="Ring"
textLocation$(1)="Locker Room"
textLocation$(2)="Backstage Lounge"
;cages
Dim textCage$(5)
textCage$(0)="None"
textCage$(1)="Wire Mesh"
textCage$(2)="Steel Bars"
textCage$(3)="Blue Bars"
textCage$(4)="Black Bars"
;rope types
Dim textRopeType$(5)
textRopeType$(0)="Normal"
textRopeType$(1)="Barbed Wire"
textRopeType$(2)="Electrified"
textRopeType$(3)="Hot Wire"
;item selections
Dim textSelection$(5)
textSelection$(0)="Random Mixture"
textSelection$(1)="Standard Mixture"
;item layouts
Dim textLayout$(5)
textLayout$(0)="Random Positions"
textLayout$(1)="Standard Positions"
textLayout$(2)="Mostly Backstage" 
textLayout$(3)="Mostly Ringside" 
textLayout$(4)="Mostly Inside" 
textLayout$(5)="Specifics Inside"
;cup rewards
Dim textCupReward$(5)
textCupReward$(0)="Trophy"
textCupReward$(1)="Crown New World"
textCupReward$(2)="Crown New Inter"
textCupReward$(3)="Crown New Tags"
;cup selections
Dim textCupSelect$(5)
textCupSelect$(0)="Hand Picked"
textCupSelect$(1)="Random"
;cup control methods
Dim textCupControl$(5)
textCupControl$(0)="All Simulated"
textCupControl$(1)="One Scattered Player"
textCupControl$(2)="Two Scattered Players"
textCupControl$(3)="Always One Player"
textCupControl$(4)="Always Two Players"
textCupControl$(5)="Two Players On Same Team"

;//////////////////////////////////////////////////////
;-------------------- ARENAS --------------------------
;//////////////////////////////////////////////////////
;arenas
Dim textArena$(30)
textArena$(1)="Small White Hall"
textArena$(2)="Small Grey Hall"
textArena$(3)="Small Dingy Hall"
textArena$(4)="Small Classy Hall"
textArena$(5)="Small Wooden Hall"
textArena$(6)="Small Brick Hall"
textArena$(7)="Small Yellow Hall"
textArena$(8)="Small Dark Hall"
textArena$(9)="Small Concrete Hall"
textArena$(10)="Small Metal Hall"
textArena$(11)="Large White Hall"
textArena$(12)="Large Grey Hall"
textArena$(13)="Large Dingy Hall"
textArena$(14)="Large Classy Hall"
textArena$(15)="Large Wooden Hall"
textArena$(16)="Large Brick Hall"
textArena$(17)="Large Yellow Hall"
textArena$(18)="Large Dark Hall"
textArena$(19)="Large Concrete Hall"
textArena$(20)="Large Metal Hall"
textArena$(21)="Starry Stadium"
textArena$(22)="Classic Stadium"
textArena$(23)="Regular Stadium"
textArena$(24)="Vegas Stadium"
textArena$(25)="Outdoor Stadium"
textArena$(26)="Beach Stadium"
;atmospherics
Dim textAtmos$(10)
textAtmos$(0)="None"
textAtmos$(1)="Black"
textAtmos$(2)="White"
textAtmos$(3)="Cream"
textAtmos$(4)="Daylight"
textAtmos$(5)="Purple"
textAtmos$(6)="Red"
textAtmos$(7)="Green"
textAtmos$(8)="Blue"
textAtmos$(9)="Random"
;ropes
Dim textRopes$(no_ropes)
textRopes$(0)="Wire"
textRopes$(1)="White"
textRopes$(2)="Black"
textRopes$(3)="Red"
textRopes$(4)="Blue"
textRopes$(5)="Green"
textRopes$(6)="Yellow"
textRopes$(7)="Orange"
textRopes$(8)="Turquoise"
textRopes$(9)="Straw"
textRopes$(10)="Black & White"
textRopes$(11)="MDickie (Dark)"
textRopes$(12)="MDickie (Light)"
textRopes$(13)="Federation Online"
textRopes$(14)="American"
textRopes$(15)="British"
textRopes$(16)="Scottish"
textRopes$(17)="Irish"
textRopes$(18)="United Kingdom Wrestling"
textRopes$(19)="Rising Sun Puroresu"
textRopes$(20)="Japanese"
textRopes$(21)="Canadian"
textRopes$(22)="Mexican"
;turnbuckles
Dim textBuckles$(4)
textBuckles$(1)="Black"
textBuckles$(2)="Blue"
textBuckles$(3)="White"
;canvases
Dim textCanvas$(no_canvases)
textCanvas$(1)="Plain (Blue)"
textCanvas$(2)="Plain (Cream)"
textCanvas$(3)="Plain (Grey)"
textCanvas$(4)="Plain (Black)"
textCanvas$(5)="MDickie (Blue)"
textCanvas$(6)="MDickie (Cream)"
textCanvas$(7)="MDickie (Grey)"
textCanvas$(8)="MDickie (Black)"
textCanvas$(9)="Promotion Specific"
textCanvas$(10)="Wrestling MPire"
;apron
Dim textApron$(30)
textApron$(1)="Plain (Black)"
textApron$(2)="Plain (Blue)"
textApron$(3)="Plain (White)"
textApron$(4)="TV Taping (Black)"
textApron$(5)="TV Taping (Blue)"
textApron$(6)="TV Taping (White)"
textApron$(7)="Pay-Per-View (Black)"
textApron$(8)="Pay-Per-View (Blue)"
textApron$(9)="Pay-Per-View (White)"
textApron$(10)="Tournament (Black)"
textApron$(11)="Tournament (Blue)"
textApron$(12)="Tournament (White)"
textApron$(13)="Charity (Black)"
textApron$(14)="Charity (Blue)"
textApron$(15)="Charity (White)"
textApron$(16)="Tribute (Black)"
textApron$(17)="Tribute (Blue)"
textApron$(18)="Tribute (White)"
;mats
Dim textMatting$(5)
textMatting$(0)="None"
textMatting$(1)="Black"
textMatting$(2)="Blue"
textMatting$(3)="Brown"

;//////////////////////////////////////////////////////
;-------------------- CITIES --------------------------
;//////////////////////////////////////////////////////
Dim textCity$(9,30)
;All American Wrestling
For count=0 To 9
 textCity$(count,1)="Boston"
 textCity$(count,2)="Orlando"
 textCity$(count,3)="Los Angeles"
 textCity$(count,4)="New Orleans"
 textCity$(count,5)="Minneapolis"
 textCity$(count,6)="Washington"
 textCity$(count,7)="Atlanta"
 textCity$(count,8)="Pittsburgh"
 textCity$(count,9)="Detroit"
 textCity$(count,10)="Philadelphia"
 textCity$(count,11)="Dallas" 
 textCity$(count,12)="Richmond"
 textCity$(count,13)="Chicago"
 textCity$(count,14)="New York"
 textCity$(count,15)="San Francisco"
 textCity$(count,16)="Houston"
 textCity$(count,17)="Phoenix"
 textCity$(count,18)="Cincinnati"
 textCity$(count,19)="Baltimore"
 textCity$(count,20)="Seattle"
 textCity$(count,21)="Denver"
 textCity$(count,22)="Los Angeles"
 textCity$(count,23)="Kansas"
 textCity$(count,24)="Las Vegas"
 textCity$(count,25)="Chicago"
 textCity$(count,26)="Miami"
Next
;UK Of Wrestling
textCity$(3,1)="Dublin"
textCity$(3,2)="Nottingham"
textCity$(3,3)="Kent"
textCity$(3,4)="Lincoln"
textCity$(3,5)="York"
textCity$(3,6)="Liverpool"
textCity$(3,7)="Edinburgh"
textCity$(3,8)="Glasgow"
textCity$(3,9)="Scunthorpe"
textCity$(3,10)="Doncaster"
textCity$(3,11)="Sheffield"
textCity$(3,12)="Birmingham"
textCity$(3,13)="Manchester"
textCity$(3,14)="Birmingham"
textCity$(3,15)="Grimsby"
textCity$(3,16)="Bournemouth"
textCity$(3,17)="Cardiff"
textCity$(3,18)="Hull"
textCity$(3,19)="Devon"
textCity$(3,20)="Cornwall"
textCity$(3,21)="Milton Keynes"
textCity$(3,22)="Reading"
textCity$(3,23)="London"
textCity$(3,24)="Blackpool"
textCity$(3,25)="Manchester"
textCity$(3,26)="Brighton"
;Rising Sun Puroresu
textCity$(4,1)="Edo"
textCity$(4,2)="Kozuke"
textCity$(4,3)="Shimonoseki"
textCity$(4,4)="Kagoshima"
textCity$(4,5)="Izumo"
textCity$(4,6)="Matsuyama"
textCity$(4,7)="Hakodate"
textCity$(4,8)="Fukushima"
textCity$(4,9)="Toyama"
textCity$(4,10)="Osaka"
textCity$(4,11)="Yokohama"
textCity$(4,12)="Nagasaki"
textCity$(4,13)="Kyoto"
textCity$(4,14)="Tokyo"
textCity$(4,15)="Nagoya"
textCity$(4,16)="Satsuma"
textCity$(4,17)="Echigo"
textCity$(4,18)="Edo"
textCity$(4,19)="Kozuke"
textCity$(4,20)="Shimonoseki"
textCity$(4,21)="Kagoshima"
textCity$(4,22)="Izumo"
textCity$(4,23)="Matsuyama"
textCity$(4,24)="Tokyo"
textCity$(4,25)="Fukushima"
textCity$(4,26)="Toyama"
;Maple Leaf Grappling
textCity$(5,1)="Whitehorse"
textCity$(5,2)="Saskatoon"
textCity$(5,3)="Yellowknife"
textCity$(5,4)="Victoria"
textCity$(5,5)="Halifax"
textCity$(5,6)="Charlottetown"
textCity$(5,7)="Fredericton"
textCity$(5,8)="Regina"
textCity$(5,9)="Iqaluit"
textCity$(5,10)="Calgary"
textCity$(5,11)="Ottawa"
textCity$(5,12)="Edmonton"
textCity$(5,13)="Montreal"
textCity$(5,14)="Toronto"
textCity$(5,15)="Edmonton"
textCity$(5,16)="Vancouver"
textCity$(5,17)="Winnipeg"
textCity$(5,18)="Whitehorse"
textCity$(5,19)="Saskatoon"
textCity$(5,20)="Yellowknife"
textCity$(5,21)="Victoria"
textCity$(5,22)="Halifax"
textCity$(5,23)="Charlottetown"
textCity$(5,24)="Fredericton"
textCity$(5,25)="Regina"
textCity$(5,26)="Iqaluit"
;Super Lucha Libre
textCity$(6,1)="Leon"
textCity$(6,2)="Tampico"
textCity$(6,3)="Merida"
textCity$(6,4)="Chihuahua"
textCity$(6,5)="Ensenada"
textCity$(6,6)="Topolobampa"
textCity$(6,7)="Manzanillo"
textCity$(6,8)="Durango"
textCity$(6,9)="Tuxpan"
textCity$(6,10)="Monterrey"
textCity$(6,11)="Salina Cruz"
textCity$(6,12)="Nuevo Laredo"
textCity$(6,13)="Cancun"
textCity$(6,14)="Tijuana"
textCity$(6,15)="Matamoros"
textCity$(6,16)="Acapulco"
textCity$(6,17)="Guadalajara"
textCity$(6,18)="Leon"
textCity$(6,19)="Tampico"
textCity$(6,20)="Merida"
textCity$(6,21)="Chihuahua"
textCity$(6,22)="Ensenada"
textCity$(6,23)="Topolobampa"
textCity$(6,24)="Manzanillo"
textCity$(6,25)="Durango"
textCity$(6,26)="Tuxpan"

;//////////////////////////////////////////////////////
;-------------------- EDITOR --------------------------
;//////////////////////////////////////////////////////
;gender
Dim textGender$(2)
textGender$(0)="Male"
textGender$(1)="Female"
;role (character)
Dim textRole$(3)
textRole$(1)="Wrestler"
textRole$(2)="Manager"
textRole$(3)="Referee"
;allegiance
Dim textHeel$(3)
textHeel$(0)="Face"
textHeel$(1)="Heel"
;eyes
Dim textEyes$(3)
textEyes$(1)="Angry"
textEyes$(2)="Neutral"
textEyes$(3)="Docile"
;build
Dim textBuild$(10)
textBuild$(1)="Slim"
textBuild$(2)="Normal"
textBuild$(3)="Muscular"
textBuild$(4)="Chubby"
textBuild$(5)="Fat"
textBuild$(6)="Obese"
;bagginess
Dim textBaggy$(3)
textBaggy$(0)="Tight"
textBaggy$(1)="Baggy Top"
textBaggy$(2)="Baggy Pants"
textBaggy$(3)="All Baggy"
;hats
Dim textHat$(no_hatstyles)
textHat$(0)="None"
textHat$(1)="Headband"
textHat$(2)="Bandana"
textHat$(3)="Straight Cap"
textHat$(4)="Tipped Cap"
textHat$(5)="Raised Cap"
textHat$(6)="Reversed Cap"
textHat$(7)="Straight Hat"
textHat$(8)="Tipped Hat"
textHat$(9)="Raised Hat"
textHat$(10)="Horns"
;specs
Dim textSpecs$(no_specs)
textSpecs$(0)="None"
textSpecs$(1)="Gold Spectacles"
textSpecs$(2)="Silver Spectacles"
textSpecs$(3)="Black Spectacles"
textSpecs$(4)="Amber Lenses"
textSpecs$(5)="Yellow Lenses"
textSpecs$(6)="Black Shades"
textSpecs$(7)="Gold Shades"
textSpecs$(8)="Eye Patch"
;hair styles
Dim textHair$(no_hairstyles)
textHair$(0)="Bald"
textHair$(1)="Bald Shave"
textHair$(2)="Receding Shave"
textHair$(3)="Light Shave"
textHair$(4)="Dark Shave"
textHair$(5)="Balding"
textHair$(6)="Lengthy Balding"
textHair$(7)="Receding"
textHair$(8)="Short"
textHair$(9)="Raised"
textHair$(10)="Quiff"
textHair$(11)="Side Parting"
textHair$(12)="Centre Parting"
textHair$(13)="Fringe"
textHair$(14)="Thick"
textHair$(15)="Lengthy"
textHair$(16)="Small Afro"
textHair$(17)="Big Afro"
textHair$(18)="Wedge"
textHair$(19)="Spikey"
textHair$(20)="Mohican"
textHair$(21)="Corn Rows"
textHair$(22)="Balding w/ Ponytail"
textHair$(23)="Receding w/ Ponytail"
textHair$(24)="Short w/ Ponytail"
textHair$(25)="Raised w/ Ponytail"
textHair$(26)="Quiff w/ Ponytail"
textHair$(27)="Side Parting w/ Ponytail"
textHair$(28)="Centre Parting w/ Ponytail"
textHair$(29)="Fringe w/ Ponytail"
textHair$(30)="Thick w/ Ponytail"
textHair$(31)="Small Afro w/ Ponytail"
textHair$(32)="Mohican w/ Ponytail"
textHair$(33)="Corn Rows w/ Ponytail"
textHair$(34)="Balding w/ Length"
textHair$(35)="Receding w/ Length"
textHair$(36)="Short w/ Length"
textHair$(37)="Raised w/ Length"
textHair$(38)="Quiff w/ Length"
textHair$(39)="Side Parting w/ Length"
textHair$(40)="Centre Parting w/ Length"
textHair$(41)="Fringe w/ Length"
textHair$(42)="Thick w/ Length"
textHair$(43)="Small Afro w/ Length"
textHair$(44)="Mohican w/ Length"
textHair$(45)="Corn Rows w/ Length"
textHair$(46)="Slicked Back"
textHair$(47)="Pointed Shave"
textHair$(48)="Wedge Shave"
;hair references
Dim hairFile$(30)
hairFile$(1)="H_Short"
hairFile$(2)="H_Part"
hairFile$(3)="H_Mop"
hairFile$(4)="H_Recede"
hairFile$(5)="H_Bald"
hairFile$(6)="H_Pony"
hairFile$(7)="H_Long"
hairFile$(8)="H_Spikey"
hairFile$(9)="H_Afro"
hairFile$(10)="H_Rows"
hairFile$(11)="H_Full"
hairFile$(12)="H_Oaf"
hairFile$(13)="H_Raised"
hairFile$(14)="H_Quiff"
hairFile$(15)="H_Buzz"
hairFile$(16)="H_Thick"
hairFile$(17)="H_Curly"
hairFile$(18)="H_Punk"
hairFile$(19)="H_Side"
hairFile$(20)="H_Slick"
;tattoos
Dim textTattoos$(no_tattoos)
textTattoos$(0)="None"
textTattoos$(1)="Everywhere"
textTattoos$(2)="Chest"
textTattoos$(3)="Sleeves"
textTattoos$(4)="Left Sleeve"
textTattoos$(5)="Right Sleeve"
textTattoos$(6)="Left Bicep"
textTattoos$(7)="Right Bicep"
textTattoos$(8)="Left Forearm"
textTattoos$(9)="Right Forearm"
;belt styles
Dim textBeltStyle$(2)
textBeltStyle$(1)="Over The Shoulder"
textBeltStyle$(2)="Around The Waist"
;lighting
Dim textLight$(no_lightshows)
textLight$(0)="Darkness"
textLight$(1)="None"
textLight$(2)="Smooth Murkiness"
textLight$(3)="Fast Murkiness"
textLight$(4)="Smooth Disco"
textLight$(5)="Fast Disco"
textLight$(6)="Red Shades"
textLight$(7)="Green Shades"
textLight$(8)="Blue Shades"
textLight$(9)="Pink Shades"
textLight$(10)="Yellow Shades"
textLight$(11)="Dark Light"

;//////////////////////////////////////////////////////
;------------------------ NAMES -----------------------
;//////////////////////////////////////////////////////
;first names
Dim textFirstName$(200)
textFirstName$(0)="Vic"
textFirstName$(1)="Eddie"
textFirstName$(2)="Matt"
textFirstName$(3)="Liam"
textFirstName$(4)="Stuart"
textFirstName$(5)="Scott"
textFirstName$(6)="Mike"
textFirstName$(7)="Gez"
textFirstName$(8)="Adam"
textFirstName$(9)="Joe"
textFirstName$(10)="Lee"
textFirstName$(11)="Alan"
textFirstName$(12)="Dennis"
textFirstName$(13)="Peter"
textFirstName$(14)="Leon"
textFirstName$(15)="Andy"
textFirstName$(16)="Theo"
textFirstName$(17)="Dan"
textFirstName$(18)="Henry"
textFirstName$(19)="Grant"
textFirstName$(20)="Anton"
textFirstName$(21)="Des"
textFirstName$(22)="Arnie"
textFirstName$(23)="Tom"
textFirstName$(24)="Paul"
textFirstName$(25)="Tony"
textFirstName$(26)="Nick"
textFirstName$(27)="Steve"
textFirstName$(28)="Vince"
textFirstName$(29)="John"
textFirstName$(30)="Gordon"
textFirstName$(31)="Chris"
textFirstName$(32)="Rob"
textFirstName$(33)="Ray"
textFirstName$(34)="Mick"
textFirstName$(35)="Rick"
textFirstName$(36)="Abe"
textFirstName$(37)="Nate"
textFirstName$(38)="Dave"
textFirstName$(39)="David"
textFirstName$(40)="Ian"
textFirstName$(41)="Trent"
textFirstName$(42)="Fred"
textFirstName$(43)="Kanye"
textFirstName$(44)="Sean"
textFirstName$(45)="Shawn"
textFirstName$(46)="Nasir"
textFirstName$(47)="George"
textFirstName$(48)="Obie"
textFirstName$(49)="Robin"
textFirstName$(50)="Keith"
textFirstName$(51)="Sgt"
textFirstName$(52)="Dr"
textFirstName$(53)="Mr"
textFirstName$(54)="Tim"
textFirstName$(55)="Jerry"
textFirstName$(56)="Larry"
textFirstName$(57)="Ted"
textFirstName$(58)="Lance"
textFirstName$(59)="Gaz"
textFirstName$(60)="Kevin"
textFirstName$(61)="Frank"
textFirstName$(62)="Bruce"
textFirstName$(63)="Gavin"
textFirstName$(64)="Cody"
textFirstName$(65)="Noel"
textFirstName$(66)="Simon"
textFirstName$(67)="Raul"
textFirstName$(68)="Patrick"
textFirstName$(69)="Demontra"
textFirstName$(70)="Gogo"
textFirstName$(71)="Eric"
textFirstName$(72)="James"
textFirstName$(73)="Pedro"
textFirstName$(74)="Jordan"
textFirstName$(75)="Kristoff"
textFirstName$(76)="Tello"
textFirstName$(77)="Henrique"
textFirstName$(78)="Nuno"
textFirstName$(79)="Sero"
textFirstName$(80)="Jose" 
textFirstName$(81)="Everton"
textFirstName$(82)="Santi"
textFirstName$(83)="Luvy"
textFirstName$(84)="Aanjan"
textFirstName$(85)="Emre"
Global no_firstnames=85
;surnames
Dim textSurName$(200)
textSurName$(0)="Aceveda"
textSurName$(1)="Sanders"
textSurName$(2)="Grimm"
textSurName$(3)="Clark"
textSurName$(4)="Evans"
textSurName$(5)="Bryant"
textSurName$(6)="Madison"
textSurName$(7)="Jackson"
textSurName$(8)="Mackey"
textSurName$(9)="Rooney"
textSurName$(10)="Gaunt"
textSurName$(11)="Collins"
textSurName$(12)="Dickin"
textSurName$(13)="Loveday"
textSurName$(14)="Atkins"
textSurName$(15)="Luther"
textSurName$(16)="Walsch"
textSurName$(17)="Vessey"
textSurName$(18)="Osborne"
textSurName$(19)="Diaz"
textSurName$(20)="Sipowicz"
textSurName$(21)="Taylor"
textSurName$(22)="Jones"
textSurName$(23)="Smith"
textSurName$(24)="McCall"
textSurName$(25)="Neeson"
textSurName$(26)="Samson"
textSurName$(27)="Simpson"
textSurName$(28)="McMahon"
textSurName$(29)="Hardass"
textSurName$(30)="Compton"
textSurName$(31)="Clapson"
textSurName$(32)="Walker"
textSurName$(33)="Kiljoy"
textSurName$(34)="Cameron"
textSurName$(35)="Blair"
textSurName$(36)="Hawksbee"
textSurName$(37)="Galloway"
textSurName$(38)="Madden"
textSurName$(39)="Austin"
textSurName$(40)="Simmons"
textSurName$(41)="Medavoy"
textSurName$(42)="Lister"
textSurName$(43)="Rimmer"
textSurName$(44)="Bishop"
textSurName$(45)="Hogan"
textSurName$(46)="Duggan"
textSurName$(47)="Lawler"
textSurName$(48)="Brown"
textSurName$(49)="Keaton"
textSurName$(50)="Steiner"
textSurName$(51)="Combs"
textSurName$(52)="Carter"
textSurName$(53)="Bush"
textSurName$(54)="Nixon"
textSurName$(55)="Mathers"
textSurName$(56)="Schwarz"
textSurName$(57)="Rajah"
textSurName$(58)="Foster"
textSurName$(59)="Robson"
textSurName$(60)="Manson"
textSurName$(61)="Pearce"
textSurName$(62)="Epton"
textSurName$(63)="Dearden"
textSurName$(64)="Mitchell"
textSurName$(65)="Mendoza"
textSurName$(66)="Kazan"
textSurName$(67)="Sayers"
textSurName$(68)="Wicker"
textSurName$(69)="Silva"
textSurName$(70)="Calhoun"
textSurName$(71)="Koik"
textSurName$(72)="Claudio"
textSurName$(73)="Malinov"
textSurName$(74)="Swanson"
textSurName$(75)="Granlet"
textSurName$(76)="Game"
textSurName$(78)="Dickie"
textSurName$(79)="James"
textSurName$(80)="Swaroop"
textSurName$(81)="Zhmayev"
textSurName$(82)="Beattie"
textSurName$(83)="Milan"
textSurName$(84)="MacDonald"
textSurName$(85)="Radu"
textSurName$(86)="Wishnowski"
textSurName$(87)="Santos"
textSurName$(88)="Zimmermann"
textSurName$(89)="Saxena"
textSurName$(90)="Hafeez"
textSurName$(91)="Zoulias"
textSurName$(92)="Lindley"
textSurName$(93)="Furrier"
textSurName$(94)="Dones"
textSurName$(95)="Asplund"
textSurName$(96)="Martins"
textSurName$(97)="Pedroso"
textSurName$(98)="Law"
textSurName$(99)="Corrigan"
textSurName$(100)="Wright"
textSurName$(101)="Kadir"
textSurName$(102)="Guedes"
textSurName$(103)="Tarvainen"
textSurName$(104)="Perry"
textSurName$(105)="Birdie"
textSurName$(106)="Lopez"
textSurName$(107)="Da Silveira"
textSurName$(108)="Soloparasara"
textSurName$(109)="Jawanda"
textSurName$(110)="Ravi"
Global no_surnames=110
;nicknames
Dim textNickName$(200)
textNickName$(0)="Lemonhead"
textNickName$(1)="Sugar Tits"
textNickName$(2)="Hat Trick"
textNickName$(3)="Deep Throat"
textNickName$(4)="Big Hit"
textNickName$(5)="Super Lucha"
textNickName$(6)="Machoman"
textNickName$(7)="Heavyweight"
textNickName$(8)="Thug Angel"
textNickName$(9)="God's Son"
textNickName$(10)="Escobar"
textNickName$(11)="Young Boy"
textNickName$(12)="Wide Boy"
textNickName$(13)="Mr Tickle"
textNickName$(14)="Handyman"
textNickName$(15)="Lyracist"
textNickName$(16)="Maitreya"
textNickName$(17)="Piston Pecker"
textNickName$(18)="Kampas Krismas"
textNickName$(19)="Baby Bull"
textNickName$(20)="Fast Eddie"
textNickName$(21)="Slick Rick"
textNickName$(22)="Toadfish"
textNickName$(23)="Octogon"
textNickName$(24)="Riverside"
textNickName$(25)="Wussy Lee"
textNickName$(26)="Scotbird"
textNickName$(27)="Thunder Lips"
textNickName$(28)="Agony Aunt"
textNickName$(29)="Downtown"
textNickName$(30)="Boomtown"
textNickName$(31)="Voodoo Child"
textNickName$(32)="Little Voice"
textNickName$(33)="Brother Bear"
textNickName$(34)="Maverick"
textNickName$(35)="Sure Shank"
textNickName$(36)="Needles"
textNickName$(37)="Iceman"
textNickName$(38)="Crazy Jew"
textNickName$(39)="Scally"
textNickName$(40)="Wise Len"
textNickName$(41)="Sunshine"
textNickName$(42)="Terminator"
textNickName$(43)="Safe Hands"
textNickName$(44)="Fairytale"
textNickName$(45)="Original G"
textNickName$(46)="Deep Impact"
textNickName$(47)="Road Pig"
textNickName$(48)="X-Factor"
textNickName$(49)="Spacker"
textNickName$(50)="Fabulous M"
textNickName$(51)="Menace"
textNickName$(52)="Nasty Nas"
textNickName$(53)="King Carter"
textNickName$(54)="Sure Shot"
textNickName$(55)="Major Merc"
textNickName$(56)="Messiah"
textNickName$(57)="King Sin"
textNickName$(58)="Farrenheit"
textNickName$(59)="Roughcock"
textNickName$(60)="Syntax Error"
textNickName$(61)="Muhammad"
textNickName$(62)="Zansibar"
textNickName$(63)="Bent Rat"
textNickName$(64)="Kid Gloves"
textNickName$(65)="Third Eye"
textNickName$(66)="Tin Ear"
textNickName$(67)="Iron Mic"
textNickName$(68)="Ghetto Child"
textNickName$(69)="Bang Bang"
textNickName$(70)="Apocolypto"
textNickName$(71)="Warrior"
textNickName$(72)="Big Pussy"
textNickName$(73)="Duke Nukem"
textNickName$(74)="Body Bag"
textNickName$(75)="Cum Bucket"
textNickName$(76)="Steroid Roy"
textNickName$(77)="Bulletproof"
textNickName$(78)="Stone Malone"
textNickName$(79)="Assassin"
textNickName$(80)="Nightmare"
Global no_nicknames=80
;team names
Dim textTeamName$(100)
textTeamName$(0)="Forces Of Evil"
textTeamName$(1)="Sugar Tits"
textTeamName$(2)="The Apocalypse"
textTeamName$(3)="No Surrender"
textTeamName$(4)="Home Team"
textTeamName$(5)="The Super Powers"
textTeamName$(6)="The Heavyweights"
textTeamName$(7)="System Shock"
textTeamName$(8)="Shadow Runners"
textTeamName$(9)="The Suns Of God"
textTeamName$(10)="Avatars Of Allah"
textTeamName$(11)="The Dark Side"
textTeamName$(12)="The Powers That Be"
textTeamName$(13)="The Gladiators"
textTeamName$(14)="The Peaks"
textTeamName$(15)="Juice Freaks"
textTeamName$(16)="Roid Rage"
textTeamName$(17)="Divide & Conquer"
textTeamName$(18)="Ying Yang"
textTeamName$(19)="Group Therapy"
textTeamName$(20)="Family Business"
textTeamName$(21)="Tap Out"
textTeamName$(22)="All Fall Down"
textTeamName$(23)="Gang Green"
textTeamName$(24)="Jive Soul Bros"
textTeamName$(25)="Murder In Mind"
textTeamName$(26)="No Way Out"
textTeamName$(27)="The Naturals"
textTeamName$(28)="Kings Of The Ring"
textTeamName$(29)="Fists Of Fun"
textTeamName$(30)="Too Many Cooks"
textTeamName$(31)="Night To Remember"
textTeamName$(32)="Cop Killers"
textTeamName$(33)="Rocky Fellas"
textTeamName$(34)="Good Fellas"
textTeamName$(35)="The Hitmen"
textTeamName$(36)="Mother Suckas"
textTeamName$(37)="Mother Thuggers"
textTeamName$(38)="Thug Life"
textTeamName$(39)="Brave Hearts"
textTeamName$(40)="Never Say Never"
textTeamName$(41)="Most Valuable Players"
textTeamName$(42)="The Shot Callers"
textTeamName$(43)="Generation-X"
textTeamName$(44)="Suck It"
textTeamName$(45)="The Disciples"
textTeamName$(46)="Death Proof"
textTeamName$(47)="One Giant Leap"
textTeamName$(48)="Bad Boys"
textTeamName$(49)="Ladies Night"
textTeamName$(50)="Mirror Image"
Global no_teamnames=50